package org.datanucleus.store.rdbms.scostore;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.scostore.ArrayStore;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractArrayStore extends ElementContainerStore implements ArrayStore {
   protected AbstractArrayStore(RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
   }

   public List getArray(ObjectProvider op) {
      Iterator<E> iter = this.iterator(op);
      List elements = new ArrayList();

      while(iter.hasNext()) {
         Object obj = iter.next();
         elements.add(obj);
      }

      return elements;
   }

   public void clear(ObjectProvider op) {
      Collection dependentElements = null;
      if (this.ownerMemberMetaData.getArray().isDependentElement()) {
         dependentElements = new HashSet();
         Iterator iter = this.iterator(op);

         while(iter.hasNext()) {
            Object elem = iter.next();
            if (op.getExecutionContext().getApiAdapter().isPersistable(elem)) {
               dependentElements.add(elem);
            }
         }
      }

      this.clearInternal(op);
      if (dependentElements != null && dependentElements.size() > 0) {
         op.getExecutionContext().deleteObjects(dependentElements.toArray());
      }

   }

   public boolean set(ObjectProvider op, Object array) {
      if (array != null && Array.getLength(array) != 0) {
         ExecutionContext ec = op.getExecutionContext();
         int length = Array.getLength(array);

         for(int i = 0; i < length; ++i) {
            Object obj = Array.get(array, i);
            this.validateElementForWriting(ec, obj, (FieldValues)null);
         }

         boolean modified = false;
         List exceptions = new ArrayList();
         boolean batched = this.allowsBatching() && length > 1;

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);

            try {
               this.processBatchedWrites(mconn);
               E element = (E)null;

               for(int i = 0; i < length; ++i) {
                  element = (E)Array.get(array, i);

                  try {
                     int[] rc = this.internalAdd(op, element, mconn, batched, i, i == length - 1);
                     if (rc != null) {
                        for(int j = 0; j < rc.length; ++j) {
                           if (rc[j] > 0) {
                              modified = true;
                           }
                        }
                     }
                  } catch (MappedDatastoreException mde) {
                     exceptions.add(mde);
                     NucleusLogger.DATASTORE.error("Exception thrown in set of element", mde);
                  }
               }
            } finally {
               mconn.release();
            }
         } catch (MappedDatastoreException e) {
            exceptions.add(e);
            NucleusLogger.DATASTORE.error("Exception thrown in set of element", e);
         }

         if (!exceptions.isEmpty()) {
            String msg = Localiser.msg("056009", new Object[]{((Exception)exceptions.get(0)).getMessage()});
            NucleusLogger.DATASTORE.error(msg);
            throw new NucleusDataStoreException(msg, (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]), op.getObject());
         } else {
            return modified;
         }
      } else {
         return true;
      }
   }

   public boolean add(ObjectProvider op, Object element, int position) {
      ExecutionContext ec = op.getExecutionContext();
      this.validateElementForWriting(ec, element, (FieldValues)null);
      boolean modified = false;

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);

         try {
            int[] returnCode = this.internalAdd(op, element, mconn, false, position, true);
            if (returnCode[0] > 0) {
               modified = true;
            }
         } finally {
            mconn.release();
         }

         return modified;
      } catch (MappedDatastoreException e) {
         throw new NucleusDataStoreException(Localiser.msg("056009", new Object[]{e.getMessage()}), e.getCause());
      }
   }

   public abstract Iterator iterator(ObjectProvider var1);

   public void clearInternal(ObjectProvider ownerOP) {
      String clearStmt = this.getClearStmt();

      try {
         ExecutionContext ec = ownerOP.getExecutionContext();
         ManagedConnection mconn = this.getStoreManager().getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, clearStmt, false);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
               }

               sqlControl.executeStatementUpdate(ec, mconn, clearStmt, ps, true);
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056013", new Object[]{clearStmt}), e);
      }
   }

   public int[] internalAdd(ObjectProvider op, Object element, ManagedConnection conn, boolean batched, int orderId, boolean executeNow) throws MappedDatastoreException {
      ExecutionContext ec = op.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();
      String addStmt = this.getAddStmtForJoinTable();

      try {
         PreparedStatement ps = sqlControl.getStatementForUpdate(conn, addStmt, false);
         boolean notYetFlushedError = false;

         int[] var13;
         try {
            int jdbcPosition = 1;
            jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
            jdbcPosition = BackingStoreHelper.populateElementInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
            jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, orderId, jdbcPosition, this.orderMapping);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
            }

            var13 = sqlControl.executeStatementUpdate(ec, conn, addStmt, ps, executeNow);
         } catch (NotYetFlushedException nfe) {
            notYetFlushedError = true;
            throw nfe;
         } finally {
            if (notYetFlushedError) {
               sqlControl.abortStatementForConnection(conn, ps);
            } else {
               sqlControl.closeStatement(conn, ps);
            }

         }

         return var13;
      } catch (SQLException e) {
         throw new MappedDatastoreException(addStmt, e);
      }
   }

   public void processBatchedWrites(ManagedConnection mconn) throws MappedDatastoreException {
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         sqlControl.processStatementsForConnection(mconn);
      } catch (SQLException e) {
         throw new MappedDatastoreException("SQLException", e);
      }
   }
}
