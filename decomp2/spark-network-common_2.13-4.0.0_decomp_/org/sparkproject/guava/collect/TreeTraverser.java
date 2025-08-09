package org.sparkproject.guava.collect;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.Beta;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Preconditions;

/** @deprecated */
@Deprecated
@ElementTypesAreNonnullByDefault
@Beta
@GwtCompatible
public abstract class TreeTraverser {
   /** @deprecated */
   @Deprecated
   public static TreeTraverser using(final Function nodeToChildrenFunction) {
      Preconditions.checkNotNull(nodeToChildrenFunction);
      return new TreeTraverser() {
         public Iterable children(Object root) {
            return (Iterable)nodeToChildrenFunction.apply(root);
         }
      };
   }

   public abstract Iterable children(Object root);

   /** @deprecated */
   @Deprecated
   public final FluentIterable preOrderTraversal(final Object root) {
      Preconditions.checkNotNull(root);
      return new FluentIterable() {
         public UnmodifiableIterator iterator() {
            return TreeTraverser.this.preOrderIterator(root);
         }

         public void forEach(final Consumer action) {
            Preconditions.checkNotNull(action);
            (new Consumer() {
               public void accept(Object t) {
                  action.accept(t);
                  TreeTraverser.this.children(t).forEach(this);
               }
            }).accept(root);
         }
      };
   }

   UnmodifiableIterator preOrderIterator(Object root) {
      return new PreOrderIterator(root);
   }

   /** @deprecated */
   @Deprecated
   public final FluentIterable postOrderTraversal(final Object root) {
      Preconditions.checkNotNull(root);
      return new FluentIterable() {
         public UnmodifiableIterator iterator() {
            return TreeTraverser.this.postOrderIterator(root);
         }

         public void forEach(final Consumer action) {
            Preconditions.checkNotNull(action);
            (new Consumer() {
               public void accept(Object t) {
                  TreeTraverser.this.children(t).forEach(this);
                  action.accept(t);
               }
            }).accept(root);
         }
      };
   }

   UnmodifiableIterator postOrderIterator(Object root) {
      return new PostOrderIterator(root);
   }

   /** @deprecated */
   @Deprecated
   public final FluentIterable breadthFirstTraversal(final Object root) {
      Preconditions.checkNotNull(root);
      return new FluentIterable() {
         public UnmodifiableIterator iterator() {
            return TreeTraverser.this.new BreadthFirstIterator(root);
         }
      };
   }

   private final class PreOrderIterator extends UnmodifiableIterator {
      private final Deque stack = new ArrayDeque();

      PreOrderIterator(Object root) {
         this.stack.addLast(Iterators.singletonIterator(Preconditions.checkNotNull(root)));
      }

      public boolean hasNext() {
         return !this.stack.isEmpty();
      }

      public Object next() {
         Iterator<T> itr = (Iterator)this.stack.getLast();
         T result = (T)Preconditions.checkNotNull(itr.next());
         if (!itr.hasNext()) {
            this.stack.removeLast();
         }

         Iterator<T> childItr = TreeTraverser.this.children(result).iterator();
         if (childItr.hasNext()) {
            this.stack.addLast(childItr);
         }

         return result;
      }
   }

   private static final class PostOrderNode {
      final Object root;
      final Iterator childIterator;

      PostOrderNode(Object root, Iterator childIterator) {
         this.root = Preconditions.checkNotNull(root);
         this.childIterator = (Iterator)Preconditions.checkNotNull(childIterator);
      }
   }

   private final class PostOrderIterator extends AbstractIterator {
      private final ArrayDeque stack = new ArrayDeque();

      PostOrderIterator(Object root) {
         this.stack.addLast(this.expand(root));
      }

      @CheckForNull
      protected Object computeNext() {
         while(true) {
            if (!this.stack.isEmpty()) {
               PostOrderNode<T> top = (PostOrderNode)this.stack.getLast();
               if (top.childIterator.hasNext()) {
                  T child = (T)top.childIterator.next();
                  this.stack.addLast(this.expand(child));
                  continue;
               }

               this.stack.removeLast();
               return top.root;
            }

            return this.endOfData();
         }
      }

      private PostOrderNode expand(Object t) {
         return new PostOrderNode(t, TreeTraverser.this.children(t).iterator());
      }
   }

   private final class BreadthFirstIterator extends UnmodifiableIterator implements PeekingIterator {
      private final Queue queue = new ArrayDeque();

      BreadthFirstIterator(Object root) {
         this.queue.add(root);
      }

      public boolean hasNext() {
         return !this.queue.isEmpty();
      }

      public Object peek() {
         return this.queue.element();
      }

      public Object next() {
         T result = (T)this.queue.remove();
         Iterables.addAll(this.queue, TreeTraverser.this.children(result));
         return result;
      }
   }
}
