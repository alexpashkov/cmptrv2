(ns cmptrv2.parser
  (:require [instaparse.core :refer [parser transform]]))

(def ^:private rules
  "expr = term '+' expr | term '-' expr | term
   term = factor '*' term | factor '/' term | factor '%' term | factor '^' term | factor
   factor = <'('> expr <')'> | num | matrix
   var = #'\\w+'
   num = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'> 
   matrix = <'['> row (<';'> row )* <']'>
   row = <'['> num (<','> num )* <']'>
   ")

(def parse (comp (fn [tree]
                   (transform {:num read-string
                               :matrix (fn [& rows]
                                         [:matrix (into []
                                                        (map #(into [] (rest %)))
                                                        rows)])} tree))
                 (parser rules)))

(parse "[[1,2];[1,2]]")
