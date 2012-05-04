(defproject model_thinking "0.0.1-ALPHA"
  :description "Formulae from Stanford's, \"Model Thinking\", class."
  :dependencies [[clojure "1.4.0"]
                 [incanter "1.3.0"]]
  :dev-dependencies [[marginalia "0.6.1"]
                     [org.clojars.flatland/cake-marginalia "0.6.3"]
                     [midje "1.3.1"]
                     [org.clojars.ibdknox/lein-nailgun "1.1.1"]]
  :tasks [cake-marginalia.tasks])
