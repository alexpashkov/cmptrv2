(ns cmptrv2.parse
  (:require [instaparse.core :refer [parser failure?]]))

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
             (parser rules)
             ))



