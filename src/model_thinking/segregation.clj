(ns model_thinking.segregation
  (:use [incanter core]))

(defn measure-of-dissimilarity 
  "Determines the measure, or index, of dissimilarity among the populations of
  a district of blocks.  This is a simple measure, and it is important to note
  that there must be exactly two classes among the population.  The index can be
  calculated as follows:

  \\[
    {1 \\over 2} \\sum_{i = 1}^N {\\left| {b_i \\over B} - {w_i \\over W} \\right|}
  \\]

  where (comparing a Caucasian and African American population, for example):

  - $b_i$ $:=$ the Caucasian population of the $i^{th}$ area (e.g. census tract)
  - $B$ $:=$ the total Caucasian population of the large geographic entity for
             which the index of dissimilarity is being calculated.
  - $w_i$ $:=$ the African American population of the $i^{th}$ area
  - $W$ $:=$ the total African American population of the large geographic
             entity for which the index of dissimilarity is being calculated.

  For example: Consider a city consisting of four city blocks of equal
  population.  One block consists of all rich people.  Onee block consists of
  all poor people, and two blocks consist of half rich and half poor people.
  What is the index of dissimilarity?
  
  Well, there are equal numbers of rich and poor people.  For the block of all
  rich people, the contribution to the index of dissimilarity equals
  $|1 - 0.5| = 0.5|$.  For the block of all poor people, the contribution to the
  index equals $|0 - 0.5| = 0.5$.  For the other two blocks the contribution
  equals $|0.5 - 0.5| = 0$.  Therefore, the index of dissimilarity equals
  ${(0.5 + 0.5) \\over 2} = 0.5$.
  
  In other words,
  
  <code>
  (measure-of-dissimilarity (matrix [
    [2 0] ; All rich people
    [0 2] ; All poor people
    [1 1] ; Half rich, half poor
    [1 1] ; Half rich, half poor
  ])) ; => 0.5
  </code>"
  
  ([blocks]
   (let [total-populations (reduce plus blocks)]
     (/ (reduce + (map #(->> ($= % / total-populations) (reduce -) abs) blocks))
        2.0)))
  
  ([block & blocks]
   (measure-of-dissimilarity (matrix (conj blocks block)))))

