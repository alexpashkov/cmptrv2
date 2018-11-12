(ns cmptrv2.core
  (:require [instaparse.core :as insta]))

(def parse
  (insta/parser
   "expr = term '+' expr | term '-' expr | term
    term = factor '*' term | factor '/' term | factor '%' term | factor '^' term | factor
    factor = <'('> expr <')'> | num
    num = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'> 
   "))

(def ops {"+" +
          "-" -
          "*" *
          "^" expt
          "/" /
          "%" mod})

(defmulti eval-expr first)

(defmethod eval-expr :factor [[_ x]]
  (eval-expr x))

(defmethod eval-expr :num [[_ x]]
  (read-string x))

(defmethod eval-expr :default [[_ x op y :as args]]
  (if (= 2 (count args))
    (eval-expr x)
    (apply (ops op) (map eval-expr [x y]))))

(eval-expr (parse "3^10"))
