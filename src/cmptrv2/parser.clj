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

(def scope (atom {}))
(def set-var! (partial swap! scope update))

(defn- handle-assignment [[_ [_ var] val]]
  (set-var var val))

(swap! scope update :a 1)

@scope

(def parse (comp
            (partial transform {:num read-string
                                :matrix (fn [& rows]
                                          [:matrix
                                           (into []
                                                 (map #(into [] (rest %)))
                                                 rows)])})
            (parser rules)))

(parse "asdfas=(((10)))")
