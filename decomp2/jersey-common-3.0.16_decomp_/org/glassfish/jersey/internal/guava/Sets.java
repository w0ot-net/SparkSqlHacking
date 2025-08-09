package org.glassfish.jersey.internal.guava;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;

public final class Sets {
   private Sets() {
   }

   public static HashSet newHashSet() {
      return new HashSet();
   }

   public static HashSet newHashSetWithExpectedSize(int expectedSize) {
      return new HashSet(Maps.capacity(expectedSize));
   }

   static int hashCodeImpl(Set s) {
      int hashCode = 0;

      for(Object o : s) {
         hashCode += o != null ? o.hashCode() : 0;
         hashCode = ~(~hashCode);
      }

      return hashCode;
   }

   static boolean equalsImpl(Set s, Object object) {
      if (s == object) {
         return true;
      } else if (object instanceof Set) {
         Set<?> o = (Set)object;

         try {
            return s.size() == o.size() && s.containsAll(o);
         } catch (NullPointerException var4) {
            return false;
         } catch (ClassCastException var5) {
            return false;
         }
      } else {
         return false;
      }
   }

   public static NavigableSet unmodifiableNavigableSet(NavigableSet set) {
      return new UnmodifiableNavigableSet(set);
   }

   static boolean removeAllImpl(Set set, Iterator iterator) {
      boolean changed;
      for(changed = false; iterator.hasNext(); changed |= set.remove(iterator.next())) {
      }

      return changed;
   }

   static boolean removeAllImpl(Set set, Collection collection) {
      Preconditions.checkNotNull(collection);
      return collection instanceof Set && collection.size() > set.size() ? Iterators.removeAll(set.iterator(), collection) : removeAllImpl(set, collection.iterator());
   }

   abstract static class ImprovedAbstractSet extends AbstractSet {
      public boolean removeAll(Collection c) {
         return Sets.removeAllImpl(this, (Collection)c);
      }

      public boolean retainAll(Collection c) {
         return super.retainAll((Collection)Preconditions.checkNotNull(c));
      }
   }

   static final class UnmodifiableNavigableSet extends ForwardingSortedSet implements NavigableSet, Serializable {
      private static final long serialVersionUID = 0L;
      private final NavigableSet delegate;
      private transient UnmodifiableNavigableSet descendingSet;

      UnmodifiableNavigableSet(NavigableSet delegate) {
         this.delegate = (NavigableSet)Preconditions.checkNotNull(delegate);
      }

      protected SortedSet delegate() {
         return Collections.unmodifiableSortedSet(this.delegate);
      }

      public Object lower(Object e) {
         return this.delegate.lower(e);
      }

      public Object floor(Object e) {
         return this.delegate.floor(e);
      }

      public Object ceiling(Object e) {
         return this.delegate.ceiling(e);
      }

      public Object higher(Object e) {
         return this.delegate.higher(e);
      }

      public Object pollFirst() {
         throw new UnsupportedOperationException();
      }

      public Object pollLast() {
         throw new UnsupportedOperationException();
      }

      public NavigableSet descendingSet() {
         UnmodifiableNavigableSet<E> result = this.descendingSet;
         if (result == null) {
            result = this.descendingSet = new UnmodifiableNavigableSet(this.delegate.descendingSet());
            result.descendingSet = this;
         }

         return result;
      }

      public Iterator descendingIterator() {
         return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
      }

      public NavigableSet subSet(Object fromElement, boolean fromInclusive, Object toElement, boolean toInclusive) {
         return Sets.unmodifiableNavigableSet(this.delegate.subSet(fromElement, fromInclusive, toElement, toInclusive));
      }

      public NavigableSet headSet(Object toElement, boolean inclusive) {
         return Sets.unmodifiableNavigableSet(this.delegate.headSet(toElement, inclusive));
      }

      public NavigableSet tailSet(Object fromElement, boolean inclusive) {
         return Sets.unmodifiableNavigableSet(this.delegate.tailSet(fromElement, inclusive));
      }
   }
}
