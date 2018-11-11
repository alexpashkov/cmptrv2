(ns cmptrv2.core
  (:require [instaparse.core :as insta]))

(def parse
  (insta/parser
   "stmt = assn | func | expr 
    assn = name <eq> expr
    func = name params <eq> expr
    name = #\"\\w+\"
    expr = <' '* '('> expr <')' ' '*> | arithm | name | val
    arithm = expr ( op expr )* 
    op = <' '*> ( '*' | '/' | '%' | '+' | '_' ) <' '*>
    params = <'('> ( name (<','> name)* )* <')'>
    eq = #\"\\s+=\\s+\"
    val = num | matrix
    matrix = <'['> matrix-row (<';'> matrix-row)* <']'>
    matrix-row = <'['> num ( <','> num )* <']'>
    num = #\"-?\\d+(\\.\\d+)*\"
   "))

(parse "5")
