(ns cmptrv2.parse
  (:require [instaparse.core :as insta]))

(def rules
  "S = assn | expr
   assn = sym<#'\\s*''='#'\\s*'>expr
   expr = term '+' expr | term '-' expr | term
   term = factor '*' term | factor '/' term | factor '%' term | factor '^' term | factor
   factor = <'('> expr <')'> | var | num | matrix
   sym = #'\\w+'
   var = #'\\w+'
   num = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'>
   matrix = <'['> row (<';'> row )* <']'>
   row = <'['> cell (<','> cell )* <']'>
   cell = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'>
   ")

(def parse (comp
            (fn [tree]
              (insta/transform {:sym    identity
                                :matrix (fn [& rows]
                                          [:matrix (into [] rows)
                                           ])
                                :row    #(into [] %&)
                                :cell   read-string
                                } tree))

            (insta/parser rules)))


