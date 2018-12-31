(ns cmptrv2.complex)

(defn abs [x]
  (if (pos? x) x (- x)))

(defrecord Complex [c i]
  Object
  (toString [this]
    (let [c (:c this)
          i (:i this)
          render-c? (not (zero? c))
          render-i? (not (zero? i))
          render-sign-in-between? (and render-c? render-i?)]
      (apply str (->> (list (and render-c? c)
                            (and render-sign-in-between?
                                 (str " "
                                      (if (pos? i) "+" "-")
                                      " "))
                            (and render-i? (str (abs i) "i")))
                      (filter identity))))))

(format "%s" (->Complex 0 2))
