(ns model_thinking.test.aggregation
  (:use [model_thinking aggregation]
        [midje sweet]))

;;; An airline sells 400 tickets for a plane which has only 380 seats.  On
;;; average, only 90% of the people who purchase tickets actually show up for
;;; the flight.  Given that the mean is 360 people who purchase tickets show up
;;; for their flight, since 68% of the time, there will be within 1 standard
;;; deviation of the mean of people who purchase tickets and happen to show up,
;;; there will typically be between 354 and 366 people who show up for their
;;; flight; since 95% of the time, there will be within 2 standard deviations of
;;; the mean of people who purchase tickets and happen to show up, there will be
;;; between 348 and 372 people who show up; finally, because 99.75% of the time,
;;; there will be within 3 standard deviations of people who purchase and happen
;;; to show up, between 342 and 378 people will show up.  There are 380 seats on
;;; the plane, which means that more than 99.75% of the time, everyone who
;;; purchases tickets and shows up will have a seat.  This is an important
;;; figure to know so that they neither overbook nor underbook the tickets.

(fact
  (mean 0.9 400) => (roughly 360))

(fact
  (sd 0.9 400) => (roughly 6))

;;; If 10,000 people each wear blue jeans independently with probability 0.8,
;;; what's the standard deviation of the resulting distribution of people
;;; wearing blue jeans?

(fact
  (sd 0.8 10000) => (roughly 40))

;;; Average Banana Sales (μ):
;;; 
;;;   μ = 500 lbs
;;;
;;; Standard Deviation (σ):
;;;
;;;   σ = 10 lbs
;;;
;;; On any given day within 6σ, we do not want to run out of bananas.
;;;
;;;   σ = 10 lbs ⇔ 6σ = 60 lbs
;;;
;;; Therefore, we need to maintain ≥ 560 lbs of bananas.
(let [μ 500
      σ 10
      β 440
      α 560]
  (fact
    (six-sigma μ σ) => (contains [β α])))

;;; Suppose I'm making some metal part, and this metal part has to be between
;;; β = 500 mm and α = 560 mm in thickness.
;;;
;;; Mean:
;;;   μ = 530 mm
;;;
(let [β 500
      α 560
      μ 530
      σ 5]
  (fact
    (six-sigma β α μ) => σ))

;;; Suppose that a production process creates tires with a diameter of 20 inches
;;; with a standard deviation of 1/10 of an inch.  What's the six sigma range?
(let [μ 20
      σ (/ 1 10)
      β (/ 19 4)
      α (/ 20 6)
      [β' α'] (six-sigma μ σ)]
  (fact
    (and (roughly β' β)
         (roughly α' α))))

