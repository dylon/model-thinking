(ns model_thinking.aggregation)

(defn- neighborhood [μ ϵ]
  "Returns the ϵ-neighborhood of μ"
  [(- μ ϵ) (+ μ ϵ)])

(defn mean [p N]
  "Returns the mean of according to the formula: $\\text{Mean} = p N$"
  (* p N))

(defn sd [p N]
  "Returns the standard deviation according to the formula: $\\text{S.D.} = \\sqrt {p (1 - p) N}$"
  (Math/sqrt (* p (- 1 p) N)))

(defn six-sigma
  "Six Sigma was developed by Motorola about a decade ago to make production
  processes more predictable to reduce quality errors.
  
  let μ = mean
  let σ = standard deviation
  let β = lower bound
  let α = upper bound"
  
  ([μ σ]
   "Accepts the mean and the standard deviation and returns the corresponding 6σ
   value."
   (let [ϵ (* 6 σ)]
     (neighborhood μ ϵ)))
  
  ([β α μ]
   "Accepts the lower and upper bounds, and the mean and returns the standard
   deviation from the corresponding 6σ."
   (let [σ (/ (- α μ) 6)]
     σ)))

