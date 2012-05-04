(ns model_thinking.test.economic_growth
  (:use [model_thinking economic_growth]
        [midje sweet]))

(fact
  (Rule-of-72 0.02) => 36.0)

(fact
  (Rule-of-72 0.06) => 12.0)

(let [growth-rate {:country-one 0.08
                   :country-two 0.02}
      more-years (- (Rule-of-72 (growth-rate :country-two))
                    (Rule-of-72 (growth-rate :country-one)))]
  (fact
    more-years => 27.0))

(let [L_t 10000
      M_t 10000
      s 0.2
      d 0.1]
  
  (fact (investment L_t M_t s) => 2000.0)
  (fact (depreciation M_t d) => 1000.0)
  (fact (net-output L_t M_t s d) => 11000.0)
  (fact (GDP L_t M_t s d) => >= 10450.0)
  (fact (growth-rate L_t M_t s d) => >= 0.045))

