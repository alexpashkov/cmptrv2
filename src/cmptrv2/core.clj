(ns cmptrv2.core
  (:gen-class)
  (:require [cmptrv2.parser :refer [parse]]))

(parse "2+3")
