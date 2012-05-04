(ns model_thinking.replicator_dynamics)

"Replicator Dynamics are used to model learning and evolution in three different
disciplines:
  1. Psychology, to model learning
  2. Economics, to model populations of people learning
  3. Ecology, to model evolution

Properties:
  1. Set of types {1,2,...,N}
     a. These are actions or strategies, etc.
  2. Payoff/Fitness for each type π(i)
  3. Proportion of each type Pr(i)

Rational Model: Choose the highest payoff.

Rule (Sociological) Model: Copy someone else.

The weight can be chosen in one of two ways:
  1. weight(i) = π(i) + Pr(i)
  2. weight(i) = π(i) Pr(i)
(we shall use 2.)

To determine how many individuals will use a particular strategy i in the next
period, use the following formula (this is the replicator equation):

  Pr_{t+1}(i) = \\frac {Pr_t(i) π(i)} {\\sum_{j=1}^N Pr_t(j) π(j)}

Fisher's Theorem (in a Nut Shell):
  Higher variance increases rate of adaptation.

Fisher's Theorem:
  The change in average fitness due to selection will be proportional to the
  variance.

If the world is churning, you want a model based on Replicator Dynamics.  If the
world is fixed, you want a model based on Six Sigma.  In the former, the
population is moving towards a peak, while in the latter, the population is
already at a peak."

(defn Average [π Pr]
  (reduce + (map * π Pr)))

(defn Pr [π Pr i]
  (defn- weight_1 [i]
    (+ (π i) (Pr i)))

  (defn- weight_2 [i]
    (* (π i) (Pr i)))

  (def weight weight_2)

  (let [N (count Pr)]
    (/
      (weight i)
      (reduce + (map weight (range N))))))

