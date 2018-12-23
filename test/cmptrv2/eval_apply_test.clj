(ns cmptrv2.eval-apply-test
  (:require [clojure.test :refer :all]
            [cmptrv2.eval-apply :refer :all]))

(deftest eval-apply-test
  (testing "numbers"
    (are [expr val]
      (= (eval-apply expr {}) {:val val :scope {}})
      "0" 0
      "1" 1
      "4" 4
      "0.5" 0.5
      )))
