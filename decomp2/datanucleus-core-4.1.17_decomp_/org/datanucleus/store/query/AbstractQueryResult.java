package org.datanucleus.store.query;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.connection.ManagedConnectionResourceListener;
import org.datanucleus.util.Localiser;

public abstract class AbstractQueryResult extends AbstractList implements QueryResult, Serializable {
   private static final long serialVersionUID = -4600803916251436835L;
   protected boolean closed = false;
   protected Query query;
   protected List connectionListeners = null;
   protected ApiAdapter api;
   protected int size = -1;
   protected String resultSizeMethod = "last";
   protected boolean loadResultsAtCommit = true;

   public AbstractQueryResult(Query query) {
      this.query = query;
      if (query != null) {
         this.api = query.getExecutionContext().getApiAdapter();
         this.resultSizeMethod = query.getStringExtensionProperty("datanucleus.query.resultSizeMethod", "last");
         this.loadResultsAtCommit = query.getBooleanExtensionProperty("datanucleus.query.loadResultsAtCommit", true);
      }

   }

   public void disconnect() {
      if (this.query != null) {
         try {
            this.closingConnection();
            this.closeResults();
         } finally {
            this.query = null;
         }

      }
   }

   protected abstract void closingConnection();

   protected abstract void closeResults();

   public synchronized void close() {
      if (!this.closed) {
         this.closeResults();
         this.query = null;
         this.closed = true;
         if (this.connectionListeners != null) {
            Iterator<ManagedConnectionResourceListener> iter = this.connectionListeners.iterator();

            while(iter.hasNext()) {
               ((ManagedConnectionResourceListener)iter.next()).resourcePostClose();
            }

            this.connectionListeners.clear();
            this.connectionListeners = null;
         }

      }
   }

   public void addConnectionListener(ManagedConnectionResourceListener listener) {
      if (this.connectionListeners == null) {
         this.connectionListeners = new ArrayList();
      }

      this.connectionListeners.add(listener);
   }

   protected boolean isOpen() {
      return !this.closed;
   }

   protected void assertIsOpen() {
      if (!this.isOpen()) {
         throw this.api.getUserExceptionForException(Localiser.msg("052600"), (Exception)null);
      }
   }

   public void add(int index, Object element) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean add(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean addAll(int index, Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public void clear() {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public boolean contains(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public boolean containsAll(Collection c) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public abstract boolean equals(Object var1);

   public abstract Object get(int var1);

   public int hashCode() {
      return this.query != null ? this.query.hashCode() : super.hashCode();
   }

   public int indexOf(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public boolean isEmpty() {
      return this.size() < 1;
   }

   public abstract Iterator iterator();

   public int lastIndexOf(Object o) {
      throw new UnsupportedOperationException(Localiser.msg("052604"));
   }

   public abstract ListIterator listIterator();

   public Object remove(int index) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public Object set(int index, Object element) {
      throw new UnsupportedOperationException(Localiser.msg("052603"));
   }

   public int size() {
      this.assertIsOpen();
      if (this.size < 0) {
         this.size = this.getSizeUsingMethod();
      }

      return this.size;
   }

   public List subList(int fromIndex, int toIndex) {
      int subListLength = toIndex - fromIndex;
      List<E> subList = new ArrayList(subListLength);

      for(int i = fromIndex; i < toIndex; ++i) {
         subList.add(this.get(i));
      }

      return subList;
   }

   public Object[] toArray() {
      Object[] array = new Object[this.size()];

      for(int i = 0; i < array.length; ++i) {
         array[i] = this.get(i);
      }

      return array;
   }

   public Object[] toArray(Object[] a) {
      int theSize = this.size();
      if (a.length >= theSize) {
         for(int i = 0; i < a.length; ++i) {
            if (i < theSize) {
               a[i] = this.get(i);
            } else {
               a[i] = null;
            }
         }

         return a;
      } else {
         return this.toArray();
      }
   }

   protected int getSizeUsingMethod() {
      if (this.resultSizeMethod.equalsIgnoreCase("COUNT")) {
         if (this.query != null && this.query.getCompilation() != null) {
            ExecutionContext ec = this.query.getExecutionContext();
            if (this.query.getCompilation().getQueryLanguage().equalsIgnoreCase("JDOQL")) {
               Query countQuery = this.query.getStoreManager().getQueryManager().newQuery("JDOQL", ec, this.query);
               if (this.query.getResultDistinct()) {
                  countQuery.setResult("COUNT(DISTINCT this)");
               } else {
                  countQuery.setResult("count(this)");
               }

               countQuery.setOrdering((String)null);
               countQuery.setRange((String)null);
               Map queryParams = this.query.getInputParameters();
               long count;
               if (queryParams != null) {
                  count = (Long)countQuery.executeWithMap(queryParams);
               } else {
                  count = (Long)countQuery.execute();
               }

               if (this.query.getRange() != null) {
                  long rangeStart = this.query.getRangeFromIncl();
                  long rangeEnd = this.query.getRangeToExcl();
                  count -= rangeStart;
                  if (count > rangeEnd - rangeStart) {
                     count = rangeEnd - rangeStart;
                  }
               }

               countQuery.closeAll();
               return (int)count;
            }

            if (this.query.getCompilation().getQueryLanguage().equalsIgnoreCase("JPQL")) {
               Query countQuery = this.query.getStoreManager().getQueryManager().newQuery("JPQL", ec, this.query);
               countQuery.setResult("count(" + this.query.getCompilation().getCandidateAlias() + ")");
               countQuery.setOrdering((String)null);
               countQuery.setRange((String)null);
               Map queryParams = this.query.getInputParameters();
               long count;
               if (queryParams != null) {
                  count = (Long)countQuery.executeWithMap(queryParams);
               } else {
                  count = (Long)countQuery.execute();
               }

               if (this.query.getRange() != null) {
                  long rangeStart = this.query.getRangeFromIncl();
                  long rangeEnd = this.query.getRangeToExcl();
                  count -= rangeStart;
                  if (count > rangeEnd - rangeStart) {
                     count = rangeEnd - rangeStart;
                  }
               }

               countQuery.closeAll();
               return (int)count;
            }
         }

         throw new NucleusUserException("datanucleus.query.resultSizeMethod of \"COUNT\" is only valid for use with JDOQL or JPQL currently");
      } else {
         throw new NucleusUserException("DataNucleus doesnt currently support any method \"" + this.resultSizeMethod + "\" for determining the size of the query results");
      }
   }
}
