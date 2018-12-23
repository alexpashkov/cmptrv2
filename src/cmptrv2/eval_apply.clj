(ns cmptrv2.eval-apply
  (:refer-clojure :exclude [eval])
  (:require [cmptrv2.parse :refer [parse]]
            ))

(defmulti eval-tree (fn [tree _] (first tree)))

(defmethod eval-tree :assn [[_ sym expr] scope]
  (let [{val :val} (eval-tree expr scope)]
    {
     :scope (assoc scope sym val)
     :val   val
     }))

(defmethod eval-tree :var [[_ sym] scope]
  (get scope sym))

(defmethod eval-tree :num [[_ num] scope]
  {
   :scope scope
   :val   (read-string num)})

(defmethod eval-tree :default [[_ child] scope]
  (when child
    (eval-tree child scope)))

(defn eval-apply
  ([expr]
   (eval-apply expr {}))
  ([expr scope]
   (-> (parse expr)
       (eval-tree scope))))

