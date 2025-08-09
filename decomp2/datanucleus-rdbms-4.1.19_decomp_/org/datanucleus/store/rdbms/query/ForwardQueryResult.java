package org.datanucleus.store.rdbms.query;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.query.AbstractQueryResultIterator;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public final class ForwardQueryResult extends AbstractRDBMSQueryResult implements Serializable {
   protected boolean moreResultSetRows;
   protected List resultObjs = new ArrayList();
   protected List resultIds = null;
   private Collection candidates;
   private boolean applyRangeChecks = false;

   public ForwardQueryResult(Query query, ResultObjectFactory rof, ResultSet rs, Collection candidates) throws SQLException {
      super(query, rof, rs);
      if (query.useResultsCaching()) {
         this.resultIds = new ArrayList();
      }

      this.applyRangeChecks = !query.processesRangeInDatastoreQuery();
      if (candidates != null) {
         this.candidates = new ArrayList(candidates);
      }

   }

   public void initialise() throws SQLException {
      this.moreResultSetRows = this.rs.next();
      if (this.applyRangeChecks) {
         for(int i = 0; (long)i < this.query.getRangeFromIncl(); ++i) {
            this.moreResultSetRows = this.rs.next();
            if (!this.moreResultSetRows) {
               break;
            }
         }
      }

      int fetchSize = this.query.getFetchPlan().getFetchSize();
      if (!this.moreResultSetRows) {
         this.closeResults();
      } else if (fetchSize == -1) {
         this.advanceToEndOfResultSet();
      } else if (fetchSize > 0) {
         this.processNumberOfResults(fetchSize);
      }

   }

   private void processNumberOfResults(int number) {
      Iterator iter = this.iterator();
      if (number < 0) {
         while(iter.hasNext()) {
            iter.next();
         }
      } else {
         for(int i = 0; i < number; ++i) {
            if (iter.hasNext()) {
               iter.next();
            }
         }
      }

   }

   private void advanceToEndOfResultSet() {
      if (this.rs != null) {
         this.processNumberOfResults(-1);
      }
   }

   protected Object nextResultSetElement() {
      if (this.rof == null) {
         return null;
      } else {
         ExecutionContext ec = this.query.getExecutionContext();
         E nextElement = (E)this.rof.getObject(ec, this.rs);
         JDBCUtils.logWarnings(this.rs);
         this.resultObjs.add(nextElement);
         if (this.resultIds != null) {
            this.resultIds.add(this.api.getIdForObject(nextElement));
         }

         if (this.bulkLoadedValueByMemberNumber != null) {
            Map<Integer, Object> memberValues = (Map)this.bulkLoadedValueByMemberNumber.get(this.api.getIdForObject(nextElement));
            if (memberValues != null) {
               ObjectProvider op = ec.findObjectProvider(nextElement);

               for(Map.Entry memberValueEntry : memberValues.entrySet()) {
                  op.replaceField((Integer)memberValueEntry.getKey(), memberValueEntry.getValue());
               }

               op.replaceAllLoadedSCOFieldsWithWrappers();
            }
         }

         if (this.rs == null) {
            throw new NucleusUserException("Results for query have already been closed. Perhaps you called flush(), closed the query, or ended a transaction");
         } else {
            try {
               this.moreResultSetRows = this.rs.next();
               if (this.applyRangeChecks) {
                  int maxElements = (int)(this.query.getRangeToExcl() - this.query.getRangeFromIncl());
                  if (this.resultObjs.size() == maxElements) {
                     this.moreResultSetRows = false;
                  }
               }

               if (!this.moreResultSetRows) {
                  this.closeResults();
               }

               return nextElement;
            } catch (SQLException e) {
               throw this.api.getDataStoreExceptionForException(Localiser.msg("052601", new Object[]{e.getMessage()}), e);
            }
         }
      }
   }

   protected void closeResults() {
      if (this.rs != null) {
         super.closeResults();
         if (this.resultIds != null) {
            this.query.getQueryManager().addQueryResult(this.query, this.query.getInputParameters(), this.resultIds);
            this.resultIds = null;
         }

         this.applyRangeChecks = false;
      }
   }

   public synchronized void close() {
      this.moreResultSetRows = false;
      this.resultObjs.clear();
      if (this.resultIds != null) {
         this.resultIds.clear();
      }

      super.close();
   }

   protected void closingConnection() {
      if (this.loadResultsAtCommit && this.isOpen() && this.moreResultSetRows) {
         NucleusLogger.QUERY.debug(Localiser.msg("052606", new Object[]{this.query.toString()}));

         try {
            this.advanceToEndOfResultSet();
         } catch (RuntimeException re) {
            if (!(re instanceof NucleusUserException)) {
               throw this.api.getUserExceptionForException("Exception thrown while loading remaining rows of query", re);
            }

            NucleusLogger.QUERY.warn("Exception thrown while loading remaining rows of query : " + re.getMessage());
         }
      }

   }

   public Iterator iterator() {
      return new QueryResultIterator();
   }

   public ListIterator listIterator() {
      return new QueryResultIterator();
   }

   public synchronized boolean contains(Object o) {
      this.assertIsOpen();
      this.advanceToEndOfResultSet();
      return this.resultObjs.contains(o);
   }

   public synchronized boolean containsAll(Collection c) {
      this.assertIsOpen();
      this.advanceToEndOfResultSet();
      return this.resultObjs.containsAll(c);
   }

   public boolean equals(Object o) {
      return o != null && o instanceof ForwardQueryResult ? super.equals(o) : false;
   }

   public int hashCode() {
      return super.hashCode();
   }

   public synchronized Object get(int index) {
      this.assertIsOpen();
      this.advanceToEndOfResultSet();
      if (index >= 0 && index < this.resultObjs.size()) {
         return this.resultObjs.get(index);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public synchronized boolean isEmpty() {
      this.assertIsOpen();
      return this.resultObjs.isEmpty() && !this.moreResultSetRows;
   }

   protected int getSizeUsingMethod() {
      if (this.resultSizeMethod.equalsIgnoreCase("LAST")) {
         this.advanceToEndOfResultSet();
         return this.resultObjs.size();
      } else {
         return super.getSizeUsingMethod();
      }
   }

   public synchronized Object[] toArray() {
      this.assertIsOpen();
      this.advanceToEndOfResultSet();
      return this.resultObjs.toArray();
   }

   public synchronized Object[] toArray(Object[] a) {
      this.assertIsOpen();
      this.advanceToEndOfResultSet();
      return this.resultObjs.toArray(a);
   }

   protected Object writeReplace() throws ObjectStreamException {
      this.disconnect();
      return new ArrayList(this.resultObjs);
   }

   private class QueryResultIterator extends AbstractQueryResultIterator {
      private int nextRowNum;
      Object currentElement;

      private QueryResultIterator() {
         this.nextRowNum = 0;
         this.currentElement = null;
      }

      public boolean hasNext() {
         synchronized(ForwardQueryResult.this) {
            if (!ForwardQueryResult.this.isOpen()) {
               return false;
            } else {
               if (ForwardQueryResult.this.applyRangeChecks) {
                  int maxElements = (int)(ForwardQueryResult.this.query.getRangeToExcl() - ForwardQueryResult.this.query.getRangeFromIncl());
                  if (this.nextRowNum == maxElements) {
                     ForwardQueryResult.this.moreResultSetRows = false;
                     ForwardQueryResult.this.closeResults();
                     return false;
                  }
               }

               if (this.nextRowNum < ForwardQueryResult.this.resultObjs.size()) {
                  return true;
               } else {
                  return ForwardQueryResult.this.candidates != null && this.currentElement != null && !ForwardQueryResult.this.moreResultSetRows ? ForwardQueryResult.this.candidates.contains(this.currentElement) : ForwardQueryResult.this.moreResultSetRows;
               }
            }
         }
      }

      public boolean hasPrevious() {
         throw new UnsupportedOperationException("Not yet implemented");
      }

      public Object next() {
         synchronized(ForwardQueryResult.this) {
            if (!ForwardQueryResult.this.isOpen()) {
               throw new NoSuchElementException(Localiser.msg("052600"));
            } else if (ForwardQueryResult.this.candidates != null && this.currentElement != null && ForwardQueryResult.this.candidates.remove(this.currentElement)) {
               ForwardQueryResult.this.resultObjs.add(this.currentElement);
               return this.currentElement;
            } else if (this.nextRowNum < ForwardQueryResult.this.resultObjs.size()) {
               this.currentElement = ForwardQueryResult.this.resultObjs.get(this.nextRowNum);
               ++this.nextRowNum;
               return this.currentElement;
            } else if (ForwardQueryResult.this.moreResultSetRows) {
               this.currentElement = ForwardQueryResult.this.nextResultSetElement();
               ++this.nextRowNum;
               if (ForwardQueryResult.this.candidates != null) {
                  ForwardQueryResult.this.candidates.remove(this.currentElement);
               }

               return this.currentElement;
            } else {
               throw new NoSuchElementException(Localiser.msg("052602"));
            }
         }
      }

      public int nextIndex() {
         throw new UnsupportedOperationException("Not yet implemented");
      }

      public Object previous() {
         throw new UnsupportedOperationException("Not yet implemented");
      }

      public int previousIndex() {
         throw new UnsupportedOperationException("Not yet implemented");
      }
   }
}
