(ns cmptrv2.eval-apply
  (:require [instaparse.core :as insta]
            [cmptrv2.parse :refer [parse]]
            [cmptrv2.validators :as v])
  (:import [clojure.lang ExceptionInfo]))

(declare ^:dynamic *scope*)

(defmulti eval-tree (fn [tree] (first tree)))

(defmethod eval-tree :assn [[_ sym expr]]
  (let [val (eval-tree expr)]
    (set! *scope* (assoc *scope* sym val))
    val))

(defmethod eval-tree :matrix [[_ rows]]
  (if (v/matrix? rows)
    rows
    (throw (IllegalStateException. "invalid matrix"))))

(defmethod eval-tree :var [[_ sym]]
  (get *scope* sym))

(defmethod eval-tree :num [[_ num]]
  (read-string num))

(defmethod eval-tree :default [[_ child]]
  (when child
    (eval-tree child)))

(defn eval-apply
  ([expr]
   (eval-apply expr {}))
  ([expr scope]
   (binding [*scope* scope]
     (try (-> (parse expr)
              (#(if (insta/failure? %)
                  (throw (ex-info "failed to parse" %))
                  %))
              (eval-tree)
              (#(assoc {:scope *scope*} :val %)))
          (catch ExceptionInfo e {:scope scope
                                  :err (.getMessage e)
                                  :data (.getData e)})
          (catch Exception e {:scope scope
                              :err (.getMessage e)})))))
