(ns model_thinking.test.replicator_dynamics
  (:use model_thinking.replicator_dynamics
        midje.sweet))

(let [Payoffs [2 4 5]
      Proportions [1/3 1/6 1/2]
      Pr (partial Pr Payoffs Proportions)]
  (fact
    (Pr 0) => 4/23)
  (fact
    (Pr 1) => 4/23)
  (fact
    (Pr 2) => 15/23))

"Low Variance: Average increases by 1/6, Gain is 1, Variance is 2"
(let [Fitnesses [3 4 5]
      Proportions [1/3 1/3 1/3]
      N (count Proportions)]
  (fact
    (Average Fitnesses Proportions) => 4)
  (let [Proportions (mapv (partial Pr Fitnesses Proportions) (range N))]
    (fact
      (Proportions 0) => 3/12)
    (fact
      (Proportions 1) => 4/12)
    (fact
      (Proportions 2) => 5/12)
    (fact
      (Average Fitnesses Proportions) => (+ 4 1/6))))

"Medium Variance: Average increases by 4/6, Gain is 4, Variance is 8"
(let [Fitnesses [2 4 6]
      Proportions [1/3 1/3 1/3]
      N (count Proportions)]
  (fact
    (Average Fitnesses Proportions) => 4)
  (let [Proportions (mapv (partial Pr Fitnesses Proportions) (range N))]
    (fact
      (Proportions 0) => 2/12)
    (fact
      (Proportions 1) => 4/12)
    (fact
      (Proportions 2) => 6/12)
    (fact
      (Average Fitnesses Proportions) => (+ 4 4/6))))

"High Variance: Average increases by 2 4/6, Gain is 16, Variance is 32"
(let [Fitnesses [0 4 8]
      Proportions [1/3 1/3 1/3]
      N (count Proportions)]
  (fact
    (Average Fitnesses Proportions) => 4)
  (let [Proportions (mapv (partial Pr Fitnesses Proportions) (range N))]
    (fact
      (Proportions 0) => 0)
    (fact
      (Proportions 1) => 1/3)
    (fact
      (Proportions 2) => 2/3)
    (fact
      (Average Fitnesses Proportions) => (+ 6 2/3))))

"A population with a higher variance will increase faster than one with a lower
variance."
(let [Fitnesses_1 [5 10 15 20]
      Proportions_1 [1/4 1/4 1/4 1/4]
      N_1 (count Proportions_1)

      Fitnessts_2 [11 12 13 14]
      Proportions_2 [1/4 1/4 1/4 1/4]
      N_2 (count Proportions_2)]

  (let [Proportions_1 (mapv (partial Pr Fitnesses_1 Proportions_1) (range N_1))
        Average_1 (Average Fitnesses_1 Proportions_1)

        Proportions_2 (mapv (partial Pr Fitnessts_2 Proportions_2) (range N_2))
        Average_2 (Average Fitnessts_2 Proportions_2)]

    (fact
      (> Average_1 Average_2))))

