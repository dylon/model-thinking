(ns model_thinking.coordination_and_culture)

"Tylor (1871, Father of Modern Culture):
  Culture := complex whole which includes knowledge, belief, art, law, morals,
  and customs.

Boaz (1911):
  Culture := Totality of mental and physical reactions and activities that
  characterize behavioral responses to environment, others, and to oneself.

Trilling (1955):
  Culture := When we look at a people in the degree of abstraction which the
  idea of culture implies, we cannot but be touched and impressed by what we
  see, we cannot help but be awed by something mysterious at work, some creative
  power which seems to transcend any particular act or habit or quality that may
  be observed.  To make a coherent life, to confront the terrors of the outer
  and inner world, to establish the ritual and art, the pieties and duties which
  make possible the life of the group and the individual --- these are culture,
  and to contemplate these various enterprises which constitute a culture is
  inevitably moving.

In order for a group to have a culture, three things must hold:
  1. There must be differences between cultures.
  2. There must be similarities within a culture (among its individuals)
  3. Each culture must be interesting in its own right (it may have practicies
     that make no sense to individuals who are not members of the culture).

There are generally two dimensions that separate cultures:
(www.worldvaluessurvey.org)
  1. Survival Values / Self Expression Values
  2. Traditional Values / Secular-Rational Values

Hofstede's Dimensions (of Culture):
  1. Power Distance
  2. Uncertainty Avoidance
  3. Individualism / Collectivism
  4. Masculinity / Feminity
  5. Confucian / Dynamism

Kenneth Arrow, \"Gifts and Exchanges\":
  Virtually every commercial transaction has within itself an element of trust,
  certainly any transaction conducted over a period of time.  It can be
  plausibly argued that much of the economic backward-ness in the world can be
  explained by the lack of mutual confidence.

Solow (1995):
  Social capital and trust have to be \"measurable\", they cannot just be
  buzzwords.

High levels of trust correlate well with a high GDP.  The more the members of a
culture trust each other, the better they function economically.

Coordination Game (individuals coordinate their actions with others):
  Measurable difference in payoffs, no one would choose not to coordinate.  If
  one chooses not to coordinate, it would be like driving on the left side of
  the road while everyone else drives on the right --- the payoff of such an
  action is very measurable. 

Standing Ovation:
  Could be more \"psychological\", it's okay to differ.  It doesn't really
  matter whether one chooses to stand or not.

Individuals can be classified into cultures by evaluating their traits with
respect to a set of features.
  1. Feature := Some circumstance which requires a reaction.
  2. Trait := How an individual reacts to a feature.
  3. Person := An individual comprising of a set of traits.

Thick Boundaries:
  As described by Axelrod's model, people near each other will either be exactly
  the same or differ by a lot.  He uses the three characteristics of cultures
  mentioned above (features, traits, and people).

Consistency Rule := Pick two attributes, set the value of the second equal to
the value of the first.  Then, when an individual would have reacted with a
particular trait to a particular feature before he coordinated with another
individual on that feature, he will act consistently and react with the
coordinated trait rather than his old one.

Coordination explains many differences.

There are three ways individuals generally coordinate on \"wrong\" actions:
  1. They could idiosyncratically coordinate on the wrong action.
  2. Payoffs to behaviors can change over time.
  3. An individual may choose a sub-optimal trait to ramain consistent in
     other dimensions.

Small amounts of innovation/error lead to large amounts of within-culture
heterogeneity.  From a stable state, a small amount of innovation/error can move
the system from the stable state to an unstable state.  As such, cultures can be
described by both Lyapunov models and Markov models."

(defn probability-of-interaction [leader follower]
  "Determines the probability of interaction between a leader and follower by
  determining the ratio of the number of traits they share to the total number
  of traits being evaluated."

  (defn share
    "Calculates the number of sharerd traits between a leader and a follower."

    ([leader follower shared]
      (if (empty? leader) shared
        (let [leading (first leader)
              following (first follower)]
          (recur (rest leader) (rest follower)
                 (if (= leading following) (inc shared) shared)))))

    ([leader follower]
     (share leader follower 0)))

  (/ (share leader follower) (count leader)))

