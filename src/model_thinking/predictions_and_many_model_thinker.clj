(ns model_thinking.predictions_and_many_model_thinker)

"Crowd's Error = Average Error - Diversity (of the crowd)

Let \\theta := true value
Let c := crowd's average value
Let s_i := Individual i's prediction

(c - \\theta)^2 = \\left( \\frac {1} {n} \\sum_{i=1}^{n} (s_i - \\theta)^2 \\right) - \\left( \\frac {1} {n} \\sum_{i=1}^{n} (s_i - c)^2 \\right)"

(defn Crowd-Squared-Error [S θ]
  (defn average [S]
    (/ (reduce + S)
       (count S)))

  (defn square [x] (* x x))

  (let [c (average S)
        n (count S)]

    ;(- (* (/ 1 n)
          ;(apply + (map #(square (- % θ)) S)))
       ;(* (/ 1 n)
          ;(apply + (map #(square (- % c)) S))))

    (square (- c θ))))

