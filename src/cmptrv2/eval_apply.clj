(ns cmptrv2.eval-apply
  (:require [cmptrv2.parse :refer [parse]]
            [clojure.spec.alpha :as s]
            ))

(declare ^:dynamic *scope*)

(defmulti eval-tree (fn [tree] (first tree)))

(defmethod eval-tree :assn [[_ sym expr]]
  (let [val (eval-tree expr)]
    (set! *scope* (assoc *scope* sym val))
    val))

(defmethod eval-tree :matrix [[_ rows]]
  rows)

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
     (-> (parse expr)
          (eval-tree)
          (#(assoc {:scope *scope*} :val %))))))


