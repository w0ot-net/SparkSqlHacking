package org.datanucleus.query.evaluator;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.store.query.QueryResult;
import org.datanucleus.util.Localiser;

public class InMemoryQueryResult extends AbstractList implements QueryResult, Serializable {
   private static final long serialVersionUID = 9111768802939625736L;
   ApiAdapter api;
   List results = null;
   protected boolean closed = false;

   public InMemoryQueryResult(List results, ApiAdapter api) {
      this.results = results;
      this.api = api;
   }

   public void close() {
      if (!this.closed) {
         this.closed = true;
      }
   }

   public void disconnect() {
   }

   public boolean contains(Object o) {
      this.assertIsOpen();
      return this.results.contains(o);
   }

   public boolean containsAll(Collection c) {
      this.assertIsOpen();
      return this.results.containsAll(c);
   }

   public Object get(int index) {
      this.assertIsOpen();
      return this.results.get(index);
   }

   public int indexOf(Object o) {
      this.assertIsOpen();
      return this.results.indexOf(o);
   }

   public boolean isEmpty() {
      this.assertIsOpen();
      return this.results.isEmpty();
   }

   public int lastIndexOf(Object o) {
      this.assertIsOpen();
      return this.results.lastIndexOf(o);
   }

   public List subList(int fromIndex, int toIndex) {
      this.assertIsOpen();
      return this.results.subList(fromIndex, toIndex);
   }

   public Object[] toArray() {
      this.assertIsOpen();
      return this.results.toArray();
   }

   public Object[] toArray(Object[] a) {
      this.assertIsOpen();
      return this.results.toArray(a);
   }

   public int size() {
      this.assertIsOpen();
      return this.results.size();
   }

   public Iterator iterator() {
      Iterator resultIter = this.results.iterator();
      return new ResultIterator(resultIter);
   }

   public ListIterator listIterator() {
      ListIterator resultIter = this.results.listIterator();
      return new ResultIterator(resultIter);
   }

   public ListIterator listIterator(int index) {
      ListIterator resultIter = this.results.listIterator(index);
      return new ResultIterator(resultIter);
   }

   protected void assertIsOpen() {
      if (this.closed) {
         String msg = Localiser.msg("052600");
         throw this.api.getUserExceptionForException(msg, (Exception)null);
      }
   }

   public boolean addAll(int index, Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public boolean addAll(Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public boolean add(Object e) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public void add(int index, Object element) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public void clear() {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public Object remove(int index) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public boolean remove(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public boolean removeAll(Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public boolean retainAll(Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public Object set(int index, Object element) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   private class ResultIterator implements ListIterator {
      Iterator resultIter = null;

      public ResultIterator(Iterator iter) {
         this.resultIter = iter;
      }

      public boolean hasNext() {
         return InMemoryQueryResult.this.closed ? false : this.resultIter.hasNext();
      }

      public Object next() {
         if (InMemoryQueryResult.this.closed) {
            throw new NoSuchElementException();
         } else {
            return this.resultIter.next();
         }
      }

      public boolean hasPrevious() {
         return InMemoryQueryResult.this.closed ? false : ((ListIterator)this.resultIter).hasPrevious();
      }

      public Object previous() {
         if (InMemoryQueryResult.this.closed) {
            throw new NoSuchElementException();
         } else {
            return ((ListIterator)this.resultIter).previous();
         }
      }

      public int nextIndex() {
         return ((ListIterator)this.resultIter).nextIndex();
      }

      public int previousIndex() {
         return ((ListIterator)this.resultIter).previousIndex();
      }

      public void remove() {
         throw new UnsupportedOperationException(Localiser.msg("052604"));
      }

      public void set(Object e) {
         throw new UnsupportedOperationException(Localiser.msg("052604"));
      }

      public void add(Object e) {
         throw new UnsupportedOperationException(Localiser.msg("052604"));
      }
   }
}
