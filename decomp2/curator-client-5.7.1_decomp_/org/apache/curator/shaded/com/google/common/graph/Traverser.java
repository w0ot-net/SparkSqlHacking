package org.apache.curator.shaded.com.google.common.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.AbstractIterator;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSet;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Call forGraph or forTree, passing a lambda or a Graph with the desired edges (built with GraphBuilder)")
@ElementTypesAreNonnullByDefault
@Beta
public abstract class Traverser {
   private final SuccessorsFunction successorFunction;

   private Traverser(SuccessorsFunction successorFunction) {
      this.successorFunction = (SuccessorsFunction)Preconditions.checkNotNull(successorFunction);
   }

   public static Traverser forGraph(final SuccessorsFunction graph) {
      return new Traverser(graph) {
         Traversal newTraversal() {
            return Traverser.Traversal.inGraph(graph);
         }
      };
   }

   public static Traverser forTree(final SuccessorsFunction tree) {
      if (tree instanceof BaseGraph) {
         Preconditions.checkArgument(((BaseGraph)tree).isDirected(), "Undirected graphs can never be trees.");
      }

      if (tree instanceof Network) {
         Preconditions.checkArgument(((Network)tree).isDirected(), "Undirected networks can never be trees.");
      }

      return new Traverser(tree) {
         Traversal newTraversal() {
            return Traverser.Traversal.inTree(tree);
         }
      };
   }

   public final Iterable breadthFirst(Object startNode) {
      return this.breadthFirst((Iterable)ImmutableSet.of(startNode));
   }

   public final Iterable breadthFirst(Iterable startNodes) {
      final ImmutableSet<N> validated = this.validate(startNodes);
      return new Iterable() {
         public Iterator iterator() {
            return Traverser.this.newTraversal().breadthFirst(validated.iterator());
         }
      };
   }

   public final Iterable depthFirstPreOrder(Object startNode) {
      return this.depthFirstPreOrder((Iterable)ImmutableSet.of(startNode));
   }

   public final Iterable depthFirstPreOrder(Iterable startNodes) {
      final ImmutableSet<N> validated = this.validate(startNodes);
      return new Iterable() {
         public Iterator iterator() {
            return Traverser.this.newTraversal().preOrder(validated.iterator());
         }
      };
   }

   public final Iterable depthFirstPostOrder(Object startNode) {
      return this.depthFirstPostOrder((Iterable)ImmutableSet.of(startNode));
   }

   public final Iterable depthFirstPostOrder(Iterable startNodes) {
      final ImmutableSet<N> validated = this.validate(startNodes);
      return new Iterable() {
         public Iterator iterator() {
            return Traverser.this.newTraversal().postOrder(validated.iterator());
         }
      };
   }

   abstract Traversal newTraversal();

   private ImmutableSet validate(Iterable startNodes) {
      ImmutableSet<N> copy = ImmutableSet.copyOf(startNodes);

      for(Object node : copy) {
         this.successorFunction.successors(node);
      }

      return copy;
   }

   private abstract static class Traversal {
      final SuccessorsFunction successorFunction;

      Traversal(SuccessorsFunction successorFunction) {
         this.successorFunction = successorFunction;
      }

      static Traversal inGraph(SuccessorsFunction graph) {
         final Set<N> visited = new HashSet();
         return new Traversal(graph) {
            @CheckForNull
            Object visitNext(Deque horizon) {
               Iterator<? extends N> top = (Iterator)horizon.getFirst();

               while(top.hasNext()) {
                  N element = (N)top.next();
                  Objects.requireNonNull(element);
                  if (visited.add(element)) {
                     return element;
                  }
               }

               horizon.removeFirst();
               return null;
            }
         };
      }

      static Traversal inTree(SuccessorsFunction tree) {
         return new Traversal(tree) {
            @CheckForNull
            Object visitNext(Deque horizon) {
               Iterator<? extends N> top = (Iterator)horizon.getFirst();
               if (top.hasNext()) {
                  return Preconditions.checkNotNull(top.next());
               } else {
                  horizon.removeFirst();
                  return null;
               }
            }
         };
      }

      final Iterator breadthFirst(Iterator startNodes) {
         return this.topDown(startNodes, Traverser.InsertionOrder.BACK);
      }

      final Iterator preOrder(Iterator startNodes) {
         return this.topDown(startNodes, Traverser.InsertionOrder.FRONT);
      }

      private Iterator topDown(Iterator startNodes, final InsertionOrder order) {
         final Deque<Iterator<? extends N>> horizon = new ArrayDeque();
         horizon.add(startNodes);
         return new AbstractIterator() {
            @CheckForNull
            protected Object computeNext() {
               do {
                  N next = (N)Traversal.this.visitNext(horizon);
                  if (next != null) {
                     Iterator<? extends N> successors = Traversal.this.successorFunction.successors(next).iterator();
                     if (successors.hasNext()) {
                        order.insertInto(horizon, successors);
                     }

                     return next;
                  }
               } while(!horizon.isEmpty());

               return this.endOfData();
            }
         };
      }

      final Iterator postOrder(Iterator startNodes) {
         final Deque<N> ancestorStack = new ArrayDeque();
         final Deque<Iterator<? extends N>> horizon = new ArrayDeque();
         horizon.add(startNodes);
         return new AbstractIterator() {
            @CheckForNull
            protected Object computeNext() {
               for(N next = (N)Traversal.this.visitNext(horizon); next != null; next = (N)Traversal.this.visitNext(horizon)) {
                  Iterator<? extends N> successors = Traversal.this.successorFunction.successors(next).iterator();
                  if (!successors.hasNext()) {
                     return next;
                  }

                  horizon.addFirst(successors);
                  ancestorStack.push(next);
               }

               if (!ancestorStack.isEmpty()) {
                  return ancestorStack.pop();
               } else {
                  return this.endOfData();
               }
            }
         };
      }

      @CheckForNull
      abstract Object visitNext(Deque horizon);
   }

   private static enum InsertionOrder {
      FRONT {
         void insertInto(Deque deque, Object value) {
            deque.addFirst(value);
         }
      },
      BACK {
         void insertInto(Deque deque, Object value) {
            deque.addLast(value);
         }
      };

      private InsertionOrder() {
      }

      abstract void insertInto(Deque deque, Object value);

      // $FF: synthetic method
      private static InsertionOrder[] $values() {
         return new InsertionOrder[]{FRONT, BACK};
      }
   }
}
