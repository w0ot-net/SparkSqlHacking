package org.datanucleus.store.rdbms.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.query.AbstractQueryResult;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedElementPCMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.rdbms.scostore.ElementContainerStore;
import org.datanucleus.store.rdbms.scostore.IteratorStatement;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractRDBMSQueryResult extends AbstractQueryResult {
   private static final long serialVersionUID = 7264180157109169910L;
   protected ResultSet rs;
   protected ResultObjectFactory rof;
   protected Map bulkLoadedValueByMemberNumber;
   protected boolean closeStatementWithResultSet = true;

   public AbstractRDBMSQueryResult(Query query, ResultObjectFactory rof, ResultSet rs) {
      super(query);
      this.rof = rof;
      this.rs = rs;
   }

   public void setCloseStatementWithResultSet(boolean flag) {
      this.closeStatementWithResultSet = flag;
   }

   public void registerMemberBulkResultSet(IteratorStatement iterStmt, ResultSet rs) {
      if (this.bulkLoadedValueByMemberNumber == null) {
         this.bulkLoadedValueByMemberNumber = new HashMap();
      }

      try {
         ExecutionContext ec = this.query.getExecutionContext();
         AbstractMemberMetaData mmd = iterStmt.getBackingStore().getOwnerMemberMetaData();
         if (!mmd.hasCollection() && !mmd.hasArray()) {
            if (mmd.hasMap()) {
            }
         } else {
            ElementContainerStore backingStore = (ElementContainerStore)iterStmt.getBackingStore();
            if (!backingStore.isElementsAreEmbedded() && !backingStore.isElementsAreSerialised()) {
               if (backingStore.getElementMapping() instanceof ReferenceMapping) {
                  int[] param = new int[backingStore.getElementMapping().getNumberOfDatastoreMappings()];

                  for(int i = 0; i < param.length; ++i) {
                     param[i] = i + 1;
                  }

                  while(rs.next()) {
                     Object owner = iterStmt.getOwnerMapIndex().getMapping().getObject(ec, rs, iterStmt.getOwnerMapIndex().getColumnPositions());
                     Object element = backingStore.getElementMapping().getObject(ec, rs, param);
                     this.addOwnerMemberValue(mmd, owner, element);
                  }
               } else {
                  String elementType = mmd.hasCollection() ? backingStore.getOwnerMemberMetaData().getCollection().getElementType() : backingStore.getOwnerMemberMetaData().getArray().getElementType();
                  ResultObjectFactory<E> scoROF = new PersistentClassROF((RDBMSStoreManager)this.query.getStoreManager(), backingStore.getEmd(), iterStmt.getStatementClassMapping(), false, (FetchPlan)null, ec.getClassLoaderResolver().classForName(elementType));

                  while(rs.next()) {
                     Object owner = iterStmt.getOwnerMapIndex().getMapping().getObject(ec, rs, iterStmt.getOwnerMapIndex().getColumnPositions());
                     Object element = scoROF.getObject(ec, rs);
                     this.addOwnerMemberValue(mmd, owner, element);
                  }
               }
            } else {
               int[] param = new int[backingStore.getElementMapping().getNumberOfDatastoreMappings()];

               for(int i = 0; i < param.length; ++i) {
                  param[i] = i + 1;
               }

               if (!(backingStore.getElementMapping() instanceof SerialisedPCMapping) && !(backingStore.getElementMapping() instanceof SerialisedReferenceMapping) && !(backingStore.getElementMapping() instanceof EmbeddedElementPCMapping)) {
                  while(rs.next()) {
                     Object owner = iterStmt.getOwnerMapIndex().getMapping().getObject(ec, rs, iterStmt.getOwnerMapIndex().getColumnPositions());
                     Object element = backingStore.getElementMapping().getObject(ec, rs, param);
                     this.addOwnerMemberValue(mmd, owner, element);
                  }
               } else {
                  while(rs.next()) {
                     Object owner = iterStmt.getOwnerMapIndex().getMapping().getObject(ec, rs, iterStmt.getOwnerMapIndex().getColumnPositions());
                     Object element = backingStore.getElementMapping().getObject(ec, rs, param, ec.findObjectProvider(owner), backingStore.getOwnerMemberMetaData().getAbsoluteFieldNumber());
                     this.addOwnerMemberValue(mmd, owner, element);
                  }
               }
            }
         }
      } catch (SQLException sqle) {
         NucleusLogger.DATASTORE.error("Exception thrown processing bulk loaded field " + iterStmt.getBackingStore().getOwnerMemberMetaData().getFullFieldName(), sqle);
      } finally {
         try {
            Statement stmt = null;

            try {
               stmt = rs.getStatement();
               rs.close();
            } catch (SQLException e) {
               NucleusLogger.DATASTORE.error(Localiser.msg("052605", new Object[]{e}));
            } finally {
               try {
                  if (stmt != null) {
                     stmt.close();
                  }
               } catch (SQLException var144) {
               }

            }
         } finally {
            ResultSet var150 = null;
         }

      }

   }

   public abstract void initialise() throws SQLException;

   private void addOwnerMemberValue(AbstractMemberMetaData mmd, Object owner, Object element) {
      Object ownerId = this.api.getIdForObject(owner);
      Map<Integer, Object> fieldValuesForOwner = (Map)this.bulkLoadedValueByMemberNumber.get(ownerId);
      if (fieldValuesForOwner == null) {
         fieldValuesForOwner = new HashMap();
         this.bulkLoadedValueByMemberNumber.put(ownerId, fieldValuesForOwner);
      }

      Collection coll = (Collection)fieldValuesForOwner.get(mmd.getAbsoluteFieldNumber());
      if (coll == null) {
         try {
            Class instanceType = SCOUtils.getContainerInstanceType(mmd.getType(), mmd.getOrderMetaData() != null);
            coll = (Collection)instanceType.newInstance();
            fieldValuesForOwner.put(mmd.getAbsoluteFieldNumber(), coll);
         } catch (Exception e) {
            throw new NucleusDataStoreException(e.getMessage(), e);
         }
      }

      coll.add(element);
   }

   public void disconnect() {
      if (this.query != null) {
         super.disconnect();
         this.rof = null;
         this.rs = null;
      }
   }

   public synchronized void close() {
      super.close();
      this.rof = null;
      this.rs = null;
   }

   protected void closeResults() {
      if (this.rs != null) {
         try {
            Statement stmt = null;

            try {
               stmt = this.rs.getStatement();
               this.rs.close();
            } catch (SQLException e) {
               NucleusLogger.DATASTORE.error(Localiser.msg("052605", new Object[]{e}));
            } finally {
               try {
                  if (this.closeStatementWithResultSet && stmt != null) {
                     stmt.close();
                  }
               } catch (SQLException var18) {
               }

            }
         } finally {
            this.rs = null;
         }
      }

   }

   public boolean equals(Object o) {
      if (o != null && o instanceof AbstractRDBMSQueryResult) {
         AbstractRDBMSQueryResult other = (AbstractRDBMSQueryResult)o;
         if (this.rs != null) {
            return other.rs == this.rs;
         } else if (this.query != null) {
            return other.query == this.query;
         } else {
            return StringUtils.toJVMIDString(other).equals(StringUtils.toJVMIDString(this));
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      if (this.rs != null) {
         return this.rs.hashCode();
      } else {
         return this.query != null ? this.query.hashCode() : StringUtils.toJVMIDString(this).hashCode();
      }
   }
}
