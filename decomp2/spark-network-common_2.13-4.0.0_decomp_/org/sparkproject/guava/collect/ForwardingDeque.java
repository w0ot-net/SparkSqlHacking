package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Deque;
import java.util.Iterator;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class ForwardingDeque extends ForwardingQueue implements Deque {
   protected ForwardingDeque() {
   }

   protected abstract Deque delegate();

   public void addFirst(@ParametricNullness Object e) {
      this.delegate().addFirst(e);
   }

   public void addLast(@ParametricNullness Object e) {
      this.delegate().addLast(e);
   }

   public Iterator descendingIterator() {
      return this.delegate().descendingIterator();
   }

   @ParametricNullness
   public Object getFirst() {
      return this.delegate().getFirst();
   }

   @ParametricNullness
   public Object getLast() {
      return this.delegate().getLast();
   }

   @CanIgnoreReturnValue
   public boolean offerFirst(@ParametricNullness Object e) {
      return this.delegate().offerFirst(e);
   }

   @CanIgnoreReturnValue
   public boolean offerLast(@ParametricNullness Object e) {
      return this.delegate().offerLast(e);
   }

   @CheckForNull
   public Object peekFirst() {
      return this.delegate().peekFirst();
   }

   @CheckForNull
   public Object peekLast() {
      return this.delegate().peekLast();
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object pollFirst() {
      return this.delegate().pollFirst();
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object pollLast() {
      return this.delegate().pollLast();
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object pop() {
      return this.delegate().pop();
   }

   public void push(@ParametricNullness Object e) {
      this.delegate().push(e);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object removeFirst() {
      return this.delegate().removeFirst();
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object removeLast() {
      return this.delegate().removeLast();
   }

   @CanIgnoreReturnValue
   public boolean removeFirstOccurrence(@CheckForNull Object o) {
      return this.delegate().removeFirstOccurrence(o);
   }

   @CanIgnoreReturnValue
   public boolean removeLastOccurrence(@CheckForNull Object o) {
      return this.delegate().removeLastOccurrence(o);
   }
}
