(ns cmptrv2.eval-apply
  (:require [cmptrv2.parse :refer [parse]]
            ))

(defn eval-apply
  ([expr state]
   {
    :state {"a" 1}
    :val   1
    }))
