(ns cmptrv2.core)

(defmulti eval-stmt first)

(defmethod eval-stmt :stmt [[_ x]]
  (println "evaling stmt" x)
  (eval-stmt x))

(defmethod eval-stmt :expr [[_ x]]
  (println "evaling expr")
  (eval-stmt x))

(def op-preced {"/" 1
                "*" 1
                "**" 1
                "%" 1
                "+" 0
                "-" 0})

(defn order-ops [[A x B y C & rest :as args]]
  (cond (even? (count args)) :error
        (= 1 (count args)) A
        :else (if (>= (op-preced x) (op-preced y)))))

(order-ops [1 "+" 1 "*" 5])

(defmethod eval-stmt :arithm [[_ x & rest]]
  (println "evaling oper")
  (if (empty? rest)
    (eval-stmt x)
    ()))

(defmethod eval-stmt :val [[_ x]]
  (println "evaling val")
  (eval-stmt x))

(defmethod eval-stmt :num [[_ num]]
  (println "evaling num")
  (read-string num))
