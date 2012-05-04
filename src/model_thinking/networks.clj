(ns model_thinking.networks)

"Network:
  A set of vertices and edges. Each edge can be directed or undirected, weighted
  or not.

Degree (of Vertex):
  Number of edges attached to a vertex.

Degree (of Network):
  Average degree of all vertices.

Neighbors (of Vertex):
  All other vertices connected by an edge to the vertex of interest.

Theorem:
  The average degree of neighbors of nodes will be at least as large as the
  average degree of the network.

Path Length (of Vertex A to B):
  Minimal number of edges of the graph that must be traversed to go from vertex
  A to vertex B.

Average Path Length (of Graph):
  Average path length between all pairs of nodes in a network.

Connectedness:
  A graph is connected if you can get from any vertex to any other.

Clustering Coefficient:
  Percentage of triples of vertices that have edges between all three vertices.
  This can be used to determine how robust/redundant a network is, social
  capital, and innovation adoption (triangles).

Random Networks:
  For a large N (where N is the number of vertices), the network almost always
  becomes connected when P > 1/(N-1), where P is the probability of being
  connected. It is at this point that we get a tipping point from a disconnected
  graph to a connected graph.

Small Worlds Network:
  People have some percentage of \"local\" or \"clique\" friends and some
  percentage of random friends.

Preferential Attachment:
  The greater the number of neighbors of a vertex, the more likely a newly
  introduced (to the graph) vertex will attach to it.

  Large tail distributions always occur with preferential attachment networks.

Six Degrees (of Separation):
  On average, there are six degrees of separation between any two people around
  the world.

K-Neighbor:
  All nodes that are of path length K to a node but not of any shorter path
  length."

(defn- natural? [number]
  (and
    (integer? number)
    (>= number 0)))

(defn average-degree-of-network [edges vertices]
  {:pre [(natural? edges) (natural? vertices)]}
  (/ (* 2 edges) vertices))

