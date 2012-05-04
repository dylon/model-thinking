(ns model_thinking.mechanism_design)

"Revenue Equivalence Theorem:
  With rational bidders, a wide class of auction mechanisms --- including sealed
  auctions, second highest price auctions, and ascending price auctions ---
  produce identical expected outcomes (again, IF the voters are rational)."

(defn incentive-compatible-income? [c p M]
  "Returns whether the amount one is paid for doing a good job is sufficient to
  get them to put forth a strong effort.

    M := Amount of money one receives for doing a good job.
    c := Cost to one for doing a good job.
    p := Probability that the individual will do a good job without much effort.
  
  The inequality below has been derived from the following:
  
    (M - c >= p M) <=> ((1 - p) M >= c) <=> (M >= c / (1 - p))
  
  The difference between the amount one is paid for doing a good job and the
  cost to one of putting forth a good effort must be at least the product of the
  amount of money one is paid for doing a good job and the probability one will
  do a good job without putting forth much effort."
  (>= M (/ c (- 1 p))))

(defn incentive-compative-hours? [K M C]
  "This model will weed out the low ability workers because they will have to
  put forth more hours to receive the same compensation as the high ability
  workers.  Therefore, low ability workers are unlikely to take the job while
  high ability workers are.
  
    K := Number of hours required
    M := Salary, etc.
    C := Cost of putting forth the effort to get the job done
  
  This model will reveal the hidden information of who is a high ability worker
  and who is not, because only the high ability workers will take the job."
  (>= K (/ M C)))

(defn net-value-of-second-highest-bid [other-bid your-bid your-value]
  (if (> your-bid other-bid)
    (- your-value other-bid)
    0))

(defn expected-winnings
  "This model can help determine one's expected winnings at a fixed price
  auction, in which the highest bidder wins the good but pays his bid:

    V := Your value of the good
    B := The value you bid; it is also your probability of winning (if the other
         bidder bids her true value)
    R := The ratio of the amount of the other's bid to his true value,
         respectively
  
  Your expected winnings can be modeled as B (V - B), which can be expanded to
  BV - B^2.  The derivative of this, with respect to B, is V - 2B.  To find your
  optimum bid, set the derivative to 0 and solve for B:
  
    V - 2B = 0
    B = V / 2
  
  In this case, your optimum bid will be 1/2 of what you value the good at.  If
  the other bidder is rational, and bids only 1/2 of her true value, then
  bidding your true value doubles your probability of winning.  Taking the
  derivative and finding the optimum solution again is:
  
    d/dB 2B (V - B) = d/dB 2BV - 2B^2 = V - 2B = 0 => B = V/2
  
  Again, the optimum bid is 1/2 of the value you place on the good.  The outcome
  is that the highest bidder will win the item, and he will win it at half its
  value to him."
  ([V B R]
   (let [surplus (- V B R)]
     (* (/ 1 R) B surplus)))
  ([V B]
   (expected-winnings V B 1)))

(defn Clarke-Groves-Vickery-Pivot-Mechanism [Cost V i]
  "Each person has an incentive to give their true value, because if they shade
  it by underbidding, the project may not get done, but if they overbid they may
  be down more money than they believe the project is worth.  Therefore it is in
  his best benefit to bid exactly what he values the project as.
  
    Cost := Cost of the project
       V := Collection of values of the project corresponding to each individual
       i := Index of the individual to evaluate"
  (max 0 (- Cost (apply + (assoc V i 0)))))

(defn do-project? [Cost Clarke-Groves-Vickrey-Costs-per-Individual]
  (<= Cost (apply + Clarke-Groves-Vickrey-Costs-per-Individual)))

