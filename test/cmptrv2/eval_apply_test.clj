(ns cmptrv2.eval-apply-test
  (:require [clojure.test :refer :all]
            [cmptrv2.eval-apply :refer :all]))

(deftest eval-apply-test
  (testing "numbers"
    (are [expr val]
      (= (eval-apply expr) {:val val :scope {}})
      "0" 0
      "1" 1
      "4" 4
      "0.5" 0.5
      "-10" -10
      ))
  (testing "symbol resolution"
    (are [sym val]
      (= (eval-apply sym {
                          "foo" 1
                          "bar" 2
                          "baz" []
                          }) val)
      "foo" 1
      "bar" 2
      "baz" []
      "lol" nil
      )
    )
  (testing "assignment"
    (are [expr val]
      (= (eval-apply expr {}) {:val val :scope {"foo" val}})
      "foo=0" 0
      "foo=1" 1
      "foo=4" 4
      "foo=0.5" 0.5
      "foo=-10" -10
      )
    )
  )
