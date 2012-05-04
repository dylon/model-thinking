(ns model_thinking.categorical_linear
  (:use [incanter core]))

(defn mean
  "Determines the statistical mean of a population of values."
  ([population]
   (/
     (reduce + (map first population))
     (count population))))

(defn variance
  "Determines the statistical variance of a population of values."
  ([population]
   (let [x (mean population)]
     (reduce + (map #(* (- % x) (- % x)) (map first population))))))

(defn squared-error
  "Determines the error of the values and their corresponding approximations by
  finding the square of the difference of each pair, to amplify the error."
  ([values-and-approximations]
   (reduce + (map #(* (reduce - %) (reduce - %)) values-and-approximations))))

(defn R-squared
  "Determines how much of the data you explain with your categorization.  The
  more variation you explain, the better your categorization.
  
  This function accepts one parameter: labeled-data.  As its name implies, each
  datum in labeled-data should be flagged, numerically, according to the
  category in which it belongs.  Each element of the list should follow the
  following convention:
  
      [label value]
  
  , where `value' is the numerical value of the datum and `label' is the
  integral value of its categorization."
  
  ([labeled-data total-model group-model]
   (let [groups (group-by first labeled-data)
         s**2 (total-model (map rest labeled-data))
         s**2' (reduce + (map group-model
                              (map #(map rest %) (map last groups))))]
     (- 1 (/ s**2' s**2))))
  
  ([labeled-data total-model]
   (R-squared labeled-data total-model #'variance))
  
  ([labeled-data]
   (R-squared labeled-data #'variance)))

(defn linear-model
  "Returns a linear model constructed from the given multiplier and offset.
  Linear models tell us two things about coefficients:
  
      1. Sign: Does the image increase or decrease in the input?
      2. Magnitude: How much does the image increase for each one unit increase
         in the input?
  
  These, in turn, help us with \"Big Coefficient\" thinking, in which we make
  educated decisions based on the models we've developed according to our data.
  (Such as deciding how to improve education: smaller class sizes versus better
  quality teachers.)"

  ([m b]
   (fn [x] ($= m * x + b))))

