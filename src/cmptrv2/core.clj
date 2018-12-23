(ns cmptrv2.core
  (:gen-class)
  (:require [cmptrv2.eval-apply :refer [eval-apply]]))

(defn -main [& _]
  (reduce (fn [scope line]
            (let [{:keys [err val scope]} (eval-apply line scope)]
              (println (or err val))
              scope))
          {}
          (line-seq (java.io.BufferedReader. *in*))))

