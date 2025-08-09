package org.datanucleus.store.rdbms.query;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.query.AbstractQueryResultIterator;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.SoftValueMap;
import org.datanucleus.util.WeakValueMap;

public final class ScrollableQueryResult extends AbstractRDBMSQueryResult implements Serializable {
   private Map resultsObjsByIndex = null;
   protected Map resultIds = null;
   int startIndex = 0;
   int endIndex = -1;
   boolean applyRangeChecks = false;

   public ScrollableQueryResult(Query query, ResultObjectFactory rof, ResultSet rs, Collection candidates) {
      super(query, rof, rs);
      if (candidates != null) {
         throw (new NucleusException("Unsupported Feature: Candidate Collection is only allowed using ForwardQueryResult")).setFatal();
      } else {
         if (query.useResultsCaching()) {
            this.resultIds = new HashMap();
         }

         String ext = (String)query.getExtension("datanucleus.query.resultCacheType");
         if (ext != null) {
            if (ext.equalsIgnoreCase("soft")) {
               this.resultsObjsByIndex = new SoftValueMap();
            } else if (ext.equalsIgnoreCase("weak")) {
               this.resultsObjsByIndex = new WeakValueMap();
            } else if (ext.equalsIgnoreCase("strong")) {
               this.resultsObjsByIndex = new HashMap();
            } else if (ext.equalsIgnoreCase("none")) {
               this.resultsObjsByIndex = null;
            } else {
               this.resultsObjsByIndex = new WeakValueMap();
            }
         } else {
            this.resultsObjsByIndex = new WeakValueMap();
         }

         this.applyRangeChecks = !query.processesRangeInDatastoreQuery();
         if (this.applyRangeChecks) {
            this.startIndex = (int)query.getRangeFromIncl();
         }

      }
   }

   public void initialise() {
      if (this.resultsObjsByIndex != null) {
         int fetchSize = this.query.getFetchPlan().getFetchSize();
         if (fetchSize == -1) {
            this.loadObjects(this.startIndex, -1);
            this.cacheQueryResults();
         } else if (fetchSize > 0) {
            this.loadObjects(this.startIndex, fetchSize);
         }
      }

   }

   protected void loadObjects(int start, int maxNumber) {
      int index = start;
      boolean hasMoreResults = true;

      while(hasMoreResults) {
         if (maxNumber >= 0 && index == maxNumber + start) {
            hasMoreResults = false;
         } else if (this.applyRangeChecks && (long)index >= this.query.getRangeToExcl()) {
            this.size = (int)(this.query.getRangeToExcl() - this.query.getRangeFromIncl());
            hasMoreResults = false;
         } else {
            try {
               boolean rowExists = this.rs.absolute(index + 1);
               if (!rowExists) {
                  hasMoreResults = false;
                  this.size = index;
                  if (this.applyRangeChecks && (long)index < this.query.getRangeToExcl()) {
                     this.size = (int)((long)index - this.query.getRangeFromIncl());
                  }

                  this.endIndex = index - 1;
               } else {
                  this.getObjectForIndex(index);
                  ++index;
               }
            } catch (SQLException var6) {
            }
         }
      }

   }

   protected Object getObjectForIndex(int index) {
      if (this.resultsObjsByIndex != null) {
         E obj = (E)this.resultsObjsByIndex.get(index);
         if (obj != null) {
            return obj;
         }
      }

      if (this.rs == null) {
         throw new NucleusUserException("Results for query have already been closed. Perhaps you called flush(), closed the query, or ended a transaction");
      } else {
         try {
            this.rs.absolute(index + 1);
            E obj = (E)this.rof.getObject(this.query.getExecutionContext(), this.rs);
            JDBCUtils.logWarnings(this.rs);
            if (this.bulkLoadedValueByMemberNumber != null) {
               ExecutionContext ec = this.query.getExecutionContext();
               Map<Integer, Object> memberValues = (Map)this.bulkLoadedValueByMemberNumber.get(this.api.getIdForObject(obj));
               if (memberValues != null) {
                  ObjectProvider op = ec.findObjectProvider(obj);

                  for(Map.Entry memberValueEntry : memberValues.entrySet()) {
                     op.replaceField((Integer)memberValueEntry.getKey(), memberValueEntry.getValue());
                  }

                  op.replaceAllLoadedSCOFieldsWithWrappers();
               }
            }

            if (this.resultsObjsByIndex != null) {
               this.resultsObjsByIndex.put(index, obj);
               if (this.resultIds != null) {
                  this.resultIds.put(index, this.api.getIdForObject(obj));
               }
            }

            return obj;
         } catch (SQLException sqe) {
            throw this.api.getDataStoreExceptionForException(Localiser.msg("052601", new Object[]{sqe.getMessage()}), sqe);
         }
      }
   }

   public synchronized void close() {
      if (this.resultsObjsByIndex != null) {
         this.resultsObjsByIndex.clear();
      }

      super.close();
   }

   protected void closingConnection() {
      if (this.loadResultsAtCommit && this.isOpen()) {
         NucleusLogger.QUERY.debug(Localiser.msg("052606", new Object[]{this.query.toString()}));
         if (this.endIndex < 0) {
            this.endIndex = this.size() - 1;
            if (this.applyRangeChecks) {
               this.endIndex = (int)this.query.getRangeToExcl() - 1;
            }
         }

         for(int i = this.startIndex; i < this.endIndex + 1; ++i) {
            this.getObjectForIndex(i);
         }

         this.cacheQueryResults();
      }

   }

   protected void cacheQueryResults() {
      if (this.resultIds != null) {
         List ids = new ArrayList();

         for(Integer position : this.resultIds.keySet()) {
            Object resultId = this.resultIds.get(position);
            ids.add(resultId);
         }

         this.query.getQueryManager().addQueryResult(this.query, this.query.getInputParameters(), ids);
      }

      this.resultIds = null;
   }

   public Iterator iterator() {
      return new QueryResultIterator();
   }

   public ListIterator listIterator() {
      return new QueryResultIterator();
   }

   public boolean equals(Object o) {
      return o != null && o instanceof ScrollableQueryResult ? super.equals(o) : false;
   }

   public int hashCode() {
      return super.hashCode();
   }

   public synchronized Object get(int index) {
      this.assertIsOpen();
      if (index >= 0 && index < this.size()) {
         return this.getObjectForIndex(index + this.startIndex);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   protected int getSizeUsingMethod() {
      int theSize = 0;
      if (this.resultSizeMethod.equalsIgnoreCase("LAST")) {
         if (this.rs == null) {
            throw new NucleusUserException("Results for query have already been closed. Perhaps you called flush(), closed the query, or ended a transaction");
         }

         try {
            boolean hasLast = this.rs.last();
            if (!hasLast) {
               theSize = 0;
            } else {
               theSize = this.rs.getRow();
            }
         } catch (SQLException sqle) {
            throw this.api.getDataStoreExceptionForException(Localiser.msg("052601", new Object[]{sqle.getMessage()}), sqle);
         }

         if (this.applyRangeChecks) {
            if ((long)theSize > this.query.getRangeToExcl()) {
               this.endIndex = (int)(this.query.getRangeToExcl() - 1L);
               theSize = (int)(this.query.getRangeToExcl() - this.query.getRangeFromIncl());
            } else {
               this.endIndex = theSize - 1;
               theSize = (int)((long)theSize - this.query.getRangeFromIncl());
            }
         }
      } else {
         theSize = super.getSizeUsingMethod();
      }

      return theSize;
   }

   public Object[] toArray() {
      return this.toArrayInternal((Object[])null);
   }

   public Object[] toArray(Object[] a) {
      if (a == null) {
         throw new NullPointerException("null argument is illegal!");
      } else {
         return this.toArrayInternal(a);
      }
   }

   private Object[] toArrayInternal(Object[] a) {
      Object[] result = a;
      ArrayList resultList = null;
      int size = -1;

      try {
         size = this.size();
      } catch (Exception x) {
         size = -1;
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug("toArray: Could not determine size.", x);
         }
      }

      if (size >= 0 && (a == null || a.length < size)) {
         result = null;
         resultList = new ArrayList(size);
      }

      Iterator iterator = null;
      if (result != null) {
         iterator = this.iterator();

         int idx;
         for(idx = -1; iterator.hasNext(); result[idx] = iterator.next()) {
            ++idx;
            if (idx >= result.length) {
               int capacity = result.length * 3 / 2 + 1;
               if (capacity < result.length) {
                  capacity = result.length;
               }

               resultList = new ArrayList(capacity);

               for(int i = 0; i < result.length; ++i) {
                  resultList.add(result[i]);
               }

               result = null;
               break;
            }
         }

         ++idx;
         if (result != null && idx < result.length) {
            result[idx] = null;
         }
      }

      if (result == null) {
         if (resultList == null) {
            resultList = new ArrayList();
         }

         if (iterator == null) {
            iterator = this.iterator();
         }

         while(iterator.hasNext()) {
            resultList.add(iterator.next());
         }

         result = a == null ? resultList.toArray() : resultList.toArray(a);
      }

      return result;
   }

   protected Object writeReplace() throws ObjectStreamException {
      this.disconnect();
      List results = new ArrayList();

      for(int i = 0; i < this.resultsObjsByIndex.size(); ++i) {
         results.add(this.resultsObjsByIndex.get(i));
      }

      return results;
   }

   private class QueryResultIterator extends AbstractQueryResultIterator {
      private int iterRowNum = 0;

      public QueryResultIterator() {
         if (ScrollableQueryResult.this.applyRangeChecks) {
            this.iterRowNum = (int)ScrollableQueryResult.this.query.getRangeFromIncl();
         }

      }

      public boolean hasNext() {
         synchronized(ScrollableQueryResult.this) {
            if (!ScrollableQueryResult.this.isOpen()) {
               return false;
            } else {
               int theSize = ScrollableQueryResult.this.size();
               if (ScrollableQueryResult.this.applyRangeChecks) {
                  if ((long)theSize < ScrollableQueryResult.this.query.getRangeToExcl() - ScrollableQueryResult.this.query.getRangeFromIncl()) {
                     return (long)this.iterRowNum <= ScrollableQueryResult.this.query.getRangeFromIncl() + (long)theSize - 1L;
                  } else {
                     if ((long)this.iterRowNum == ScrollableQueryResult.this.query.getRangeToExcl() - 1L) {
                        ScrollableQueryResult.this.endIndex = this.iterRowNum;
                     }

                     return (long)this.iterRowNum <= ScrollableQueryResult.this.query.getRangeToExcl() - 1L;
                  }
               } else {
                  return this.iterRowNum <= theSize - 1;
               }
            }
         }
      }

      public boolean hasPrevious() {
         synchronized(ScrollableQueryResult.this) {
            if (!ScrollableQueryResult.this.isOpen()) {
               return false;
            } else if (ScrollableQueryResult.this.applyRangeChecks) {
               return (long)this.iterRowNum > ScrollableQueryResult.this.query.getRangeFromIncl();
            } else {
               return this.iterRowNum > 0;
            }
         }
      }

      public Object next() {
         synchronized(ScrollableQueryResult.this) {
            if (!ScrollableQueryResult.this.isOpen()) {
               throw new NoSuchElementException(Localiser.msg("052600"));
            } else if (!this.hasNext()) {
               throw new NoSuchElementException("No next element");
            } else {
               E obj = (E)ScrollableQueryResult.this.getObjectForIndex(this.iterRowNum);
               ++this.iterRowNum;
               return obj;
            }
         }
      }

      public int nextIndex() {
         if (this.hasNext()) {
            return ScrollableQueryResult.this.applyRangeChecks ? this.iterRowNum - (int)ScrollableQueryResult.this.query.getRangeFromIncl() : this.iterRowNum;
         } else {
            return ScrollableQueryResult.this.size();
         }
      }

      public Object previous() {
         synchronized(ScrollableQueryResult.this) {
            if (!ScrollableQueryResult.this.isOpen()) {
               throw new NoSuchElementException(Localiser.msg("052600"));
            } else if (!this.hasPrevious()) {
               throw new NoSuchElementException("No previous element");
            } else {
               --this.iterRowNum;
               return ScrollableQueryResult.this.getObjectForIndex(this.iterRowNum);
            }
         }
      }

      public int previousIndex() {
         if (ScrollableQueryResult.this.applyRangeChecks) {
            return (int)((long)this.iterRowNum == ScrollableQueryResult.this.query.getRangeFromIncl() ? -1L : (long)this.iterRowNum - ScrollableQueryResult.this.query.getRangeFromIncl() - 1L);
         } else {
            return this.iterRowNum == 0 ? -1 : this.iterRowNum - 1;
         }
      }
   }
}
