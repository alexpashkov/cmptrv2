(ns cmptrv2.core
  (:gen-class)
  (:require [cmptrv2.eval-apply :refer [eval-apply]]))

(defn -main [& _]
  (reduce (fn [state line]
            (let [{:keys [err val state]} (eval-apply state line)]
              (println (or err val))
              state))
          {}
          (line-seq (java.io.BufferedReader. *in*))))

