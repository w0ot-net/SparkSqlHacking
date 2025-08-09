package org.apache.curator.shaded.com.google.common.graph;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.AbstractIterator;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Iterators;
import org.apache.curator.shaded.com.google.common.collect.UnmodifiableIterator;

@ElementTypesAreNonnullByDefault
final class DirectedGraphConnections implements GraphConnections {
   private static final Object PRED = new Object();
   private final Map adjacentNodeValues;
   @CheckForNull
   private final List orderedNodeConnections;
   private int predecessorCount;
   private int successorCount;

   private DirectedGraphConnections(Map adjacentNodeValues, @CheckForNull List orderedNodeConnections, int predecessorCount, int successorCount) {
      this.adjacentNodeValues = (Map)Preconditions.checkNotNull(adjacentNodeValues);
      this.orderedNodeConnections = orderedNodeConnections;
      this.predecessorCount = Graphs.checkNonNegative(predecessorCount);
      this.successorCount = Graphs.checkNonNegative(successorCount);
      Preconditions.checkState(predecessorCount <= adjacentNodeValues.size() && successorCount <= adjacentNodeValues.size());
   }

   static DirectedGraphConnections of(ElementOrder incidentEdgeOrder) {
      int initialCapacity = 4;
      List<NodeConnection<N>> orderedNodeConnections;
      switch (incidentEdgeOrder.type()) {
         case UNORDERED:
            orderedNodeConnections = null;
            break;
         case STABLE:
            orderedNodeConnections = new ArrayList();
            break;
         default:
            throw new AssertionError(incidentEdgeOrder.type());
      }

      return new DirectedGraphConnections(new HashMap(initialCapacity, 1.0F), orderedNodeConnections, 0, 0);
   }

   static DirectedGraphConnections ofImmutable(Object thisNode, Iterable incidentEdges, Function successorNodeToValueFn) {
      Preconditions.checkNotNull(thisNode);
      Preconditions.checkNotNull(successorNodeToValueFn);
      Map<N, Object> adjacentNodeValues = new HashMap();
      ImmutableList.Builder<NodeConnection<N>> orderedNodeConnectionsBuilder = ImmutableList.builder();
      int predecessorCount = 0;
      int successorCount = 0;

      for(EndpointPair incidentEdge : incidentEdges) {
         if (incidentEdge.nodeU().equals(thisNode) && incidentEdge.nodeV().equals(thisNode)) {
            adjacentNodeValues.put(thisNode, new PredAndSucc(successorNodeToValueFn.apply(thisNode)));
            orderedNodeConnectionsBuilder.add((Object)(new NodeConnection.Pred(thisNode)));
            orderedNodeConnectionsBuilder.add((Object)(new NodeConnection.Succ(thisNode)));
            ++predecessorCount;
            ++successorCount;
         } else if (incidentEdge.nodeV().equals(thisNode)) {
            N predecessor = (N)incidentEdge.nodeU();
            Object existingValue = adjacentNodeValues.put(predecessor, PRED);
            if (existingValue != null) {
               adjacentNodeValues.put(predecessor, new PredAndSucc(existingValue));
            }

            orderedNodeConnectionsBuilder.add((Object)(new NodeConnection.Pred(predecessor)));
            ++predecessorCount;
         } else {
            Preconditions.checkArgument(incidentEdge.nodeU().equals(thisNode));
            N successor = (N)incidentEdge.nodeV();
            V value = (V)successorNodeToValueFn.apply(successor);
            Object existingValue = adjacentNodeValues.put(successor, value);
            if (existingValue != null) {
               Preconditions.checkArgument(existingValue == PRED);
               adjacentNodeValues.put(successor, new PredAndSucc(value));
            }

            orderedNodeConnectionsBuilder.add((Object)(new NodeConnection.Succ(successor)));
            ++successorCount;
         }
      }

      return new DirectedGraphConnections(adjacentNodeValues, orderedNodeConnectionsBuilder.build(), predecessorCount, successorCount);
   }

   public Set adjacentNodes() {
      return (Set)(this.orderedNodeConnections == null ? Collections.unmodifiableSet(this.adjacentNodeValues.keySet()) : new AbstractSet() {
         public UnmodifiableIterator iterator() {
            final Iterator<NodeConnection<N>> nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
            final Set<N> seenNodes = new HashSet();
            return new AbstractIterator() {
               @CheckForNull
               protected Object computeNext() {
                  while(true) {
                     if (nodeConnections.hasNext()) {
                        NodeConnection<N> nodeConnection = (NodeConnection)nodeConnections.next();
                        boolean added = seenNodes.add(nodeConnection.node);
                        if (!added) {
                           continue;
                        }

                        return nodeConnection.node;
                     }

                     return this.endOfData();
                  }
               }
            };
         }

         public int size() {
            return DirectedGraphConnections.this.adjacentNodeValues.size();
         }

         public boolean contains(@CheckForNull Object obj) {
            return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
         }
      });
   }

   public Set predecessors() {
      return new AbstractSet() {
         public UnmodifiableIterator iterator() {
            if (DirectedGraphConnections.this.orderedNodeConnections == null) {
               final Iterator<Map.Entry<N, Object>> entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
               return new AbstractIterator() {
                  @CheckForNull
                  protected Object computeNext() {
                     while(true) {
                        if (entries.hasNext()) {
                           Map.Entry<N, Object> entry = (Map.Entry)entries.next();
                           if (!DirectedGraphConnections.isPredecessor(entry.getValue())) {
                              continue;
                           }

                           return entry.getKey();
                        }

                        return this.endOfData();
                     }
                  }
               };
            } else {
               final Iterator<NodeConnection<N>> nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
               return new AbstractIterator() {
                  @CheckForNull
                  protected Object computeNext() {
                     while(true) {
                        if (nodeConnections.hasNext()) {
                           NodeConnection<N> nodeConnection = (NodeConnection)nodeConnections.next();
                           if (!(nodeConnection instanceof NodeConnection.Pred)) {
                              continue;
                           }

                           return nodeConnection.node;
                        }

                        return this.endOfData();
                     }
                  }
               };
            }
         }

         public int size() {
            return DirectedGraphConnections.this.predecessorCount;
         }

         public boolean contains(@CheckForNull Object obj) {
            return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
         }
      };
   }

   public Set successors() {
      return new AbstractSet() {
         public UnmodifiableIterator iterator() {
            if (DirectedGraphConnections.this.orderedNodeConnections == null) {
               final Iterator<Map.Entry<N, Object>> entries = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
               return new AbstractIterator() {
                  @CheckForNull
                  protected Object computeNext() {
                     while(true) {
                        if (entries.hasNext()) {
                           Map.Entry<N, Object> entry = (Map.Entry)entries.next();
                           if (!DirectedGraphConnections.isSuccessor(entry.getValue())) {
                              continue;
                           }

                           return entry.getKey();
                        }

                        return this.endOfData();
                     }
                  }
               };
            } else {
               final Iterator<NodeConnection<N>> nodeConnections = DirectedGraphConnections.this.orderedNodeConnections.iterator();
               return new AbstractIterator() {
                  @CheckForNull
                  protected Object computeNext() {
                     while(true) {
                        if (nodeConnections.hasNext()) {
                           NodeConnection<N> nodeConnection = (NodeConnection)nodeConnections.next();
                           if (!(nodeConnection instanceof NodeConnection.Succ)) {
                              continue;
                           }

                           return nodeConnection.node;
                        }

                        return this.endOfData();
                     }
                  }
               };
            }
         }

         public int size() {
            return DirectedGraphConnections.this.successorCount;
         }

         public boolean contains(@CheckForNull Object obj) {
            return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
         }
      };
   }

   public Iterator incidentEdgeIterator(Object thisNode) {
      Preconditions.checkNotNull(thisNode);
      final Iterator<EndpointPair<N>> resultWithDoubleSelfLoop;
      if (this.orderedNodeConnections == null) {
         resultWithDoubleSelfLoop = Iterators.concat(Iterators.transform(this.predecessors().iterator(), (predecessor) -> EndpointPair.ordered(predecessor, thisNode)), Iterators.transform(this.successors().iterator(), (successor) -> EndpointPair.ordered(thisNode, successor)));
      } else {
         resultWithDoubleSelfLoop = Iterators.transform(this.orderedNodeConnections.iterator(), (connection) -> connection instanceof NodeConnection.Succ ? EndpointPair.ordered(thisNode, connection.node) : EndpointPair.ordered(connection.node, thisNode));
      }

      final AtomicBoolean alreadySeenSelfLoop = new AtomicBoolean(false);
      return new AbstractIterator() {
         @CheckForNull
         protected EndpointPair computeNext() {
            while(true) {
               if (resultWithDoubleSelfLoop.hasNext()) {
                  EndpointPair<N> edge = (EndpointPair)resultWithDoubleSelfLoop.next();
                  if (edge.nodeU().equals(edge.nodeV())) {
                     if (alreadySeenSelfLoop.getAndSet(true)) {
                        continue;
                     }

                     return edge;
                  }

                  return edge;
               }

               return (EndpointPair)this.endOfData();
            }
         }
      };
   }

   @CheckForNull
   public Object value(Object node) {
      Preconditions.checkNotNull(node);
      Object value = this.adjacentNodeValues.get(node);
      if (value == PRED) {
         return null;
      } else {
         return value instanceof PredAndSucc ? ((PredAndSucc)value).successorValue : value;
      }
   }

   public void removePredecessor(Object node) {
      Preconditions.checkNotNull(node);
      Object previousValue = this.adjacentNodeValues.get(node);
      boolean removedPredecessor;
      if (previousValue == PRED) {
         this.adjacentNodeValues.remove(node);
         removedPredecessor = true;
      } else if (previousValue instanceof PredAndSucc) {
         this.adjacentNodeValues.put(node, ((PredAndSucc)previousValue).successorValue);
         removedPredecessor = true;
      } else {
         removedPredecessor = false;
      }

      if (removedPredecessor) {
         Graphs.checkNonNegative(--this.predecessorCount);
         if (this.orderedNodeConnections != null) {
            this.orderedNodeConnections.remove(new NodeConnection.Pred(node));
         }
      }

   }

   @CheckForNull
   public Object removeSuccessor(Object node) {
      Preconditions.checkNotNull(node);
      Object previousValue = this.adjacentNodeValues.get(node);
      Object removedValue;
      if (previousValue != null && previousValue != PRED) {
         if (previousValue instanceof PredAndSucc) {
            this.adjacentNodeValues.put(node, PRED);
            removedValue = ((PredAndSucc)previousValue).successorValue;
         } else {
            this.adjacentNodeValues.remove(node);
            removedValue = previousValue;
         }
      } else {
         removedValue = null;
      }

      if (removedValue != null) {
         Graphs.checkNonNegative(--this.successorCount);
         if (this.orderedNodeConnections != null) {
            this.orderedNodeConnections.remove(new NodeConnection.Succ(node));
         }
      }

      return removedValue == null ? null : removedValue;
   }

   public void addPredecessor(Object node, Object unused) {
      Object previousValue = this.adjacentNodeValues.put(node, PRED);
      boolean addedPredecessor;
      if (previousValue == null) {
         addedPredecessor = true;
      } else if (previousValue instanceof PredAndSucc) {
         this.adjacentNodeValues.put(node, previousValue);
         addedPredecessor = false;
      } else if (previousValue != PRED) {
         this.adjacentNodeValues.put(node, new PredAndSucc(previousValue));
         addedPredecessor = true;
      } else {
         addedPredecessor = false;
      }

      if (addedPredecessor) {
         Graphs.checkPositive(++this.predecessorCount);
         if (this.orderedNodeConnections != null) {
            this.orderedNodeConnections.add(new NodeConnection.Pred(node));
         }
      }

   }

   @CheckForNull
   public Object addSuccessor(Object node, Object value) {
      Object previousValue = this.adjacentNodeValues.put(node, value);
      Object previousSuccessor;
      if (previousValue == null) {
         previousSuccessor = null;
      } else if (previousValue instanceof PredAndSucc) {
         this.adjacentNodeValues.put(node, new PredAndSucc(value));
         previousSuccessor = ((PredAndSucc)previousValue).successorValue;
      } else if (previousValue == PRED) {
         this.adjacentNodeValues.put(node, new PredAndSucc(value));
         previousSuccessor = null;
      } else {
         previousSuccessor = previousValue;
      }

      if (previousSuccessor == null) {
         Graphs.checkPositive(++this.successorCount);
         if (this.orderedNodeConnections != null) {
            this.orderedNodeConnections.add(new NodeConnection.Succ(node));
         }
      }

      return previousSuccessor == null ? null : previousSuccessor;
   }

   private static boolean isPredecessor(@CheckForNull Object value) {
      return value == PRED || value instanceof PredAndSucc;
   }

   private static boolean isSuccessor(@CheckForNull Object value) {
      return value != PRED && value != null;
   }

   private static final class PredAndSucc {
      private final Object successorValue;

      PredAndSucc(Object successorValue) {
         this.successorValue = successorValue;
      }
   }

   private abstract static class NodeConnection {
      final Object node;

      NodeConnection(Object node) {
         this.node = Preconditions.checkNotNull(node);
      }

      static final class Pred extends NodeConnection {
         Pred(Object node) {
            super(node);
         }

         public boolean equals(@CheckForNull Object that) {
            return that instanceof Pred ? this.node.equals(((Pred)that).node) : false;
         }

         public int hashCode() {
            return Pred.class.hashCode() + this.node.hashCode();
         }
      }

      static final class Succ extends NodeConnection {
         Succ(Object node) {
            super(node);
         }

         public boolean equals(@CheckForNull Object that) {
            return that instanceof Succ ? this.node.equals(((Succ)that).node) : false;
         }

         public int hashCode() {
            return Succ.class.hashCode() + this.node.hashCode();
         }
      }
   }
}
