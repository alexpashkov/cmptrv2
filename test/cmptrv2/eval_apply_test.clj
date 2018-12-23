(ns cmptrv2.eval-apply-test
  (:require [clojure.test :refer :all]
            [cmptrv2.eval-apply :refer :all]))

(deftest eval-apply-test
  (testing "simple assignment"
    (is (= (eval-apply "a = 1" {}) {
                                    :state {
                                            "a" 1
                                            }
                                    :val   1
                                    }))))
