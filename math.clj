(ns cmptrv2.core)

(defn expt [x n]
  "Exponentiates x to the power n"
  (loop [acc 1 n n]
    (if (> n 0)
      (recur (* acc x) (dec n))
      acc)))
