(ns cmptrv2.parse
  (:require [instaparse.core :as insta]))

(def parse (comp
            (partial insta/transform {:sym    identity
                                      :matrix (fn [& rows]
                                                [:matrix (into [] rows)])
                                      :row    #(into [] %&)
                                      :cell   read-string})

            (insta/parser
             "S = assn | expr
             assn = sym<#'\\s*''='#'\\s*'>expr
             expr = term '+' expr | term '-' expr | term
             term = factor '*' term | factor '/' term | factor '%' term | factor '^' term | factor
             factor = <'('> expr <')'> | var | num | matrix
             var = #'\\w+'
             sym = #'\\w+'
             num = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'>
             matrix = <'['> row (<';'> row )* <']'>
             row = <'['> cell (<','> cell )* <']'>
             cell = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'>
             ")))
