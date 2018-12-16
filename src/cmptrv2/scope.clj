(ns cmptrv2.scope
  (:refer-clojure :exclude [assoc get set]))

(def ^:private scope (atom {}))

(defn get [name]
  (@scope name))

(def assoc
  (partial swap! scope clojure.core/assoc))
