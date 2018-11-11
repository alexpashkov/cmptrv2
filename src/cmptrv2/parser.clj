(ns cmptrv2.core
  (:require [instaparse.core :as insta]))

(def parse
  (insta/parser
   "expr = term '+' expr | term '-' expr | term
    term = factor '*' term |factor '/' term | factor
    factor = <'('> expr <')'> | num
    num = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'> 
   "))
;;expr = num | expr (op expr)* | <#'\\s*\\(\\s*'> expr <#'\\s*\\)\\s*'> 
;; op = <#'\\s*'> ( '*' | '**' | '^' | '/' | '%' | '+' | '-' ) <#'\\s*'>
;; num = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'> 

(->> "1*(1+(((100))))"
     (parse)
     (insta/transform {:expr (fn [& [v :as args]]
                               (if (= 1 (count args)) v args))
                       :num read-string}))
