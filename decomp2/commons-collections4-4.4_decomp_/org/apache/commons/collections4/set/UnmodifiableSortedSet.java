package org.apache.commons.collections4.set;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.function.Predicate;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

public final class UnmodifiableSortedSet extends AbstractSortedSetDecorator implements Unmodifiable {
   private static final long serialVersionUID = -725356885467962424L;

   public static SortedSet unmodifiableSortedSet(SortedSet set) {
      return (SortedSet)(set instanceof Unmodifiable ? set : new UnmodifiableSortedSet(set));
   }

   private UnmodifiableSortedSet(SortedSet set) {
      super(set);
   }

   public Iterator iterator() {
      return UnmodifiableIterator.unmodifiableIterator(this.decorated().iterator());
   }

   public boolean add(Object object) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(Collection coll) {
      throw new UnsupportedOperationException();
   }

   public void clear() {
      throw new UnsupportedOperationException();
   }

   public boolean remove(Object object) {
      throw new UnsupportedOperationException();
   }

   public boolean removeIf(Predicate filter) {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Collection coll) {
      throw new UnsupportedOperationException();
   }

   public boolean retainAll(Collection coll) {
      throw new UnsupportedOperationException();
   }

   public SortedSet subSet(Object fromElement, Object toElement) {
      SortedSet<E> sub = this.decorated().subSet(fromElement, toElement);
      return unmodifiableSortedSet(sub);
   }

   public SortedSet headSet(Object toElement) {
      SortedSet<E> head = this.decorated().headSet(toElement);
      return unmodifiableSortedSet(head);
   }

   public SortedSet tailSet(Object fromElement) {
      SortedSet<E> tail = this.decorated().tailSet(fromElement);
      return unmodifiableSortedSet(tail);
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      out.defaultWriteObject();
      out.writeObject(this.decorated());
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.setCollection((Collection)in.readObject());
   }
}
