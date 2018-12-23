(ns cmptrv2.parse
  (:require [instaparse.core :as insta]))

(def rules
  "S = assn | expr
   assn = var<#'\\s*''='#'\\s*'>expr
   expr = term '+' expr | term '-' expr | term
   term = factor '*' term | factor '/' term | factor '%' term | factor '^' term | factor
   factor = <'('> expr <')'> | var | num | matrix
   var = #'\\w+'
   num = <#'\\s*'> #'-?\\d+(\\.\\d+)*' <#'\\s*'>
   matrix = <'['> row (<';'> row )* <']'>
   row = <'['> num (<','> num )* <']'>
   ")

(def parse (comp
             ;#(if (failure? %) (throw (ex-info "Failed to parse" %)) %)
             (fn [tree]
               (insta/transform {
                                 :var identity
                                 } tree))

             (insta/parser rules)
             ))


(parse "asdf=1")


