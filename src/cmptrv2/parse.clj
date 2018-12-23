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
   row = <'['> num (<','> num )* <']'>
   ")

(def parse (comp
            (fn [tree]
              (insta/transform {:sym identity} tree))

            (insta/parser rules)))

(parse "asdf=1")


