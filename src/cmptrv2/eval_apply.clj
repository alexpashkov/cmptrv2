(ns cmptrv2.eval-apply
  (:refer-clojure :exclude [eval])
  (:require [cmptrv2.parse :refer [parse]]
            ))

(defmulti eval-tree (fn [tree _] (first tree)))

(defmethod eval-tree :num [[_ num] scope]
  {
   :scope scope
   :val   (read-string num)})

(defmethod eval-tree :default [[_ child] scope]
  (eval-tree child scope))

(defn eval-apply
  ([expr]
   (eval-apply expr {}))

  ([expr scope]
   (-> (parse expr)
       (eval-tree scope))
    ))

