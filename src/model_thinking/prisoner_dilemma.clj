(ns model_thinking.prisoner_dilemma)

"Each person can take an action which has a cost of cooperation, c, and a
benifit to others, b.  We are going to assume that the benifit to others is
larger than the cost: b > c.  Therefore, socially, it is better to cooperate,
but individually, it is better not to cooperate.

Direct Reciprocity:
  Two individuals cooperate in the present because they plan to cooperate again
  in the future.

  Let p := probability that we meet again.
  Let our payoff if we deviate be 0.
  Let our payoff if we cooperate be -c + pb

  Then, we should cooperate if

    (-c + pb > 0) <=> (p > c/b)

  This is a model for direct reciprocity, based on whether an individual
  believes that he will meet the other again, and will then receive a payoff
  from him.

Indirect Reciprocity:
  A model for indirect reciprocity is the following:
  
    (-c + qb > 0) <=> (q > c/b)

  , where q is the probability of the other individual's reputation.  This
  differs from direct reciprocity because the individual is cooperating with the
  hope of receiving a good reputation that will spread far and wide regarding
  how cooperative he is.  In this case, other individuals are more likely to
  cooperate with him because of his reputation of cooperation (he's such a nice
  person).

Network Reciprocity:
  If each person has k neighbors, then cooperation is likely to result if:
  
    k < b/c

  This can be derived as follows: Suppose an individual has k neighbors.  Then,
  his payoff if he is surrounded by cooperators is given by:

    k (b - c)

  And his payoff if he is a boundary defector is:

    (k - 1) b

  Therefore, he will choose to cooperate if:

    (k (b - c) > (k - 1) b) <=> (b > kc) <=> (b/c > k) <=> (k < b/c)

When one considers reputation, then the denser one's network, the better it will
be for cooperation.  When one considers network reciprocity, the denser one's
network, the worse it will be for cooperation.

Group Selection:
  Within groups, the defectors do better, but between groups, groups that have
  more cooperators win more wars.

Kin Selection:
  Players are related and you care about other people based on their
  relatedness, r, such that:

    cooperation = rb > c"

(defn- probability? [x]
  (and (>= x 0) (<= x 1)))

(defn- bounded-by? [X i]
  (and (>= i 0) (<= i (count X))))

(defn directly-cooperate? [c b p]
  (> p (/ c b)))

(defn indirectly-cooperate? [c b q]
  (> q (/ c b)))

(defn network-cooperate? [c b k]
  (< k (/ b c)))

(defn collective-payoff [j β X]
  {:pre [(bounded-by? X j)
         (probability? β)
         (every? probability? X)]}
  (+ (- (nth X j)) (* β (apply + X))))

(defn collectively-cooperate? [j β X]
  (let [cooperation-payoff (collective-payoff j β (assoc X j 1))
        deffection-payoff (collective-payoff j β (assoc X j 0))]
    (> cooperation-payoff deffection-payoff)))

(defn- square [x]
  (* x x))

(defn- natural? [x]
  (and (integer? x) (>= x 0)))

(comment
  This model can help predict over-fishing, as one example)
(defn common-pool-resource [resource-count resource-consumed]
  {:pre [(natural? resource-count)
         (natural? resource-consumed)
         (>= resource-count 0)
         (<= resource-consumed resource-count)]}
  (let [resource-remaining (- resource-count resource-consumed)]
    (square resource-remaining)))

