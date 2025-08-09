package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.CollectionMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.scostore.SetStore;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractSetStore extends AbstractCollectionStore implements SetStore {
   protected AbstractSetStore(RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
   }

   public abstract Iterator iterator(ObjectProvider var1);

   public boolean remove(ObjectProvider op, Object element, int size, boolean allowDependentField) {
      if (!this.validateElementForReading(op, element)) {
         NucleusLogger.DATASTORE.debug("Attempt to remove element=" + StringUtils.toJVMIDString(element) + " but doesn't exist in this Set.");
         return false;
      } else {
         Object elementToRemove = element;
         ExecutionContext ec = op.getExecutionContext();
         if (ec.getApiAdapter().isDetached(element)) {
            elementToRemove = ec.findObject(ec.getApiAdapter().getIdForObject(element), true, false, element.getClass().getName());
         }

         boolean modified = false;
         String removeStmt = this.getRemoveStmt(elementToRemove);

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, removeStmt, false);

               try {
                  int jdbcPosition = 1;
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                  jdbcPosition = BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, elementToRemove, jdbcPosition, this.elementMapping);
                  if (this.relationDiscriminatorMapping != null) {
                     BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                  }

                  int[] rowsDeleted = sqlControl.executeStatementUpdate(ec, mconn, removeStmt, ps, true);
                  modified = rowsDeleted[0] == 1;
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }
         } catch (SQLException e) {
            String msg = Localiser.msg("056012", new Object[]{removeStmt});
            NucleusLogger.DATASTORE.error(msg, e);
            throw new NucleusDataStoreException(msg, e);
         }

         CollectionMetaData collmd = this.ownerMemberMetaData.getCollection();
         boolean dependent = collmd.isDependentElement();
         if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
            dependent = true;
         }

         if (allowDependentField && dependent && !collmd.isEmbeddedElement()) {
            op.getExecutionContext().deleteObjectInternal(elementToRemove);
         }

         return modified;
      }
   }

   public boolean removeAll(ObjectProvider op, Collection elements, int size) {
      if (elements != null && elements.size() != 0) {
         boolean modified = false;
         List exceptions = new ArrayList();
         boolean batched = elements.size() > 1;

         for(Object element : elements) {
            if (!this.validateElementForReading(op, element)) {
               NucleusLogger.DATASTORE.debug("AbstractSetStore::removeAll element=" + element + " doesn't exist in this Set.");
               return false;
            }
         }

         try {
            ExecutionContext ec = op.getExecutionContext();
            ManagedConnection mconn = this.storeMgr.getConnection(ec);

            try {
               SQLController sqlControl = this.storeMgr.getSQLController();

               try {
                  sqlControl.processStatementsForConnection(mconn);
               } catch (SQLException e) {
                  throw new MappedDatastoreException("SQLException", e);
               }

               Iterator var37 = elements.iterator();

               while(var37.hasNext()) {
                  Object element = var37.next();

                  try {
                     int[] rc = null;
                     String removeStmt = this.getRemoveStmt(element);

                     try {
                        PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, removeStmt, batched);

                        try {
                           int jdbcPosition = 1;
                           jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                           jdbcPosition = BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
                           if (this.relationDiscriminatorMapping != null) {
                              BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                           }

                           rc = sqlControl.executeStatementUpdate(ec, mconn, removeStmt, ps, !batched || batched && !var37.hasNext());
                           if (rc != null) {
                              for(int i = 0; i < rc.length; ++i) {
                                 if (rc[i] > 0) {
                                    modified = true;
                                 }
                              }
                           }
                        } finally {
                           sqlControl.closeStatement(mconn, ps);
                        }
                     } catch (SQLException e) {
                        throw new MappedDatastoreException("SQLException", e);
                     }
                  } catch (MappedDatastoreException mde) {
                     exceptions.add(mde);
                     NucleusLogger.DATASTORE.error("Exception in remove", mde);
                  }
               }
            } finally {
               mconn.release();
            }
         } catch (MappedDatastoreException e) {
            exceptions.add(e);
            NucleusLogger.DATASTORE.error("Exception performing removeAll on set backing store", e);
         }

         if (!exceptions.isEmpty()) {
            String msg = Localiser.msg("056012", new Object[]{((Exception)exceptions.get(0)).getMessage()});
            NucleusLogger.DATASTORE.error(msg);
            throw new NucleusDataStoreException(msg, (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]), op.getObject());
         } else {
            return modified;
         }
      } else {
         return false;
      }
   }
}
