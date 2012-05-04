(ns model_thinking.peer_effects)

(defn- granovetter' [collective index collected]
  (if (>= index (count collective))
    (->> (sort-by first collective) (map last) (vec))
    (let [[index' threshold] (nth collective index)
          activated? (<= threshold collected)
          model [index' activated?]]
      (recur
        (assoc collective index model)
        (inc index)
        (if activated?
          (inc collected)
          collected)))))

(defn granovetter [thresholds]
  "A simple thresholding model for collective behavior, as proposed by Mark
  Granovetter.  This function accepts a list of thresholds and returns a vector
  containing whether the corresponding agent would have been collectively
  activated."
  (let [collective (->> (map-indexed vector thresholds) (sort-by last) (vec))]
    (granovetter' collective 0 0)))

