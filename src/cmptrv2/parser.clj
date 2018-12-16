(ns cmptrv2.parser
  (:require [instaparse.core :refer [parser transform]]))

(def rules
  "assn = var<#'\\s*''='#'\\s*'>expr
   expr = term '+' expr | term '-' expr | term
   term = factor '*' term | factor '/' term | factor '%' term | factor '^' term | factor
   factor = <'('> expr <')'> | num | matrix
   var = #'\\w+'
   num = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'> 
   matrix = <'['> row (<';'> row )* <']'>
   row = <'['> num (<','> num )* <']'>
   ")

(def parse (comp
            (partial transform {:num read-string
                                :matrix (fn [& rows]
                                          [:matrix
                                           (into []
                                                 (map #(into [] (rest %)))
                                                 rows)])})
            (parser rules)))

(parse "asdfas=1+10")
