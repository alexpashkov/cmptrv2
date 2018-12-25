(ns cmptrv2.validators)

(defn matrix? [m]
  (and (vector? m)
       (vector? (first m))
       (let [size (count (first m))]
         (every? (fn [r]
                   (and
                    (vector? r)
                    (= size (count r))
                    (every? number? r)))
                 m))))

