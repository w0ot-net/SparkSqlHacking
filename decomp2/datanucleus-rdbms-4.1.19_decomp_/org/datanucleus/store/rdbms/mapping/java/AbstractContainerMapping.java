package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.exceptions.NoDatastoreMappingException;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.types.SCO;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;

public abstract class AbstractContainerMapping extends SingleFieldMapping {
   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      super.initialize(mmd, table, clr);
      if (mmd.getContainer() == null) {
         throw new NucleusUserException(Localiser.msg("041023", new Object[]{mmd.getFullFieldName()}));
      } else {
         if (!this.containerIsStoredInSingleColumn()) {
            this.storeMgr.newJoinTable(mmd, clr);
         }

      }
   }

   public boolean hasSimpleDatastoreRepresentation() {
      return false;
   }

   protected void prepareDatastoreMapping() {
      if (this.containerIsStoredInSingleColumn()) {
         super.prepareDatastoreMapping();
      }

   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return this.containerIsStoredInSingleColumn() ? ClassNameConstants.JAVA_IO_SERIALIZABLE : super.getJavaTypeForDatastoreMapping(index);
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      if (this.mmd != null && this.containerIsStoredInSingleColumn()) {
         ObjectProvider[] sms = null;
         ApiAdapter api = ec.getApiAdapter();
         if (value != null) {
            Collection smsColl = null;
            if (value instanceof Collection) {
               for(Object elem : (Collection)value) {
                  if (api.isPersistable(elem)) {
                     ObjectProvider sm = ec.findObjectProvider(elem);
                     if (sm != null) {
                        if (smsColl == null) {
                           smsColl = new HashSet();
                        }

                        smsColl.add(sm);
                     }
                  }
               }
            } else if (value instanceof Map) {
               for(Map.Entry entry : ((Map)value).entrySet()) {
                  Object key = entry.getKey();
                  Object val = entry.getValue();
                  if (api.isPersistable(key)) {
                     ObjectProvider sm = ec.findObjectProvider(key);
                     if (sm != null) {
                        if (smsColl == null) {
                           smsColl = new HashSet();
                        }

                        smsColl.add(sm);
                     }
                  }

                  if (api.isPersistable(val)) {
                     ObjectProvider sm = ec.findObjectProvider(val);
                     if (sm != null) {
                        if (smsColl == null) {
                           smsColl = new HashSet();
                        }

                        smsColl.add(sm);
                     }
                  }
               }
            }

            if (smsColl != null) {
               sms = (ObjectProvider[])smsColl.toArray(new ObjectProvider[smsColl.size()]);
            }
         }

         if (sms != null) {
            for(int i = 0; i < sms.length; ++i) {
               sms[i].setStoringPC();
            }
         }

         this.getDatastoreMapping(0).setObject(ps, exprIndex[0], value);
         if (sms != null) {
            for(int i = 0; i < sms.length; ++i) {
               sms[i].unsetStoringPC();
            }
         }

      } else {
         throw (new NucleusException(this.failureMessage("setObject"))).setFatal();
      }
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      if (this.mmd != null && this.containerIsStoredInSingleColumn()) {
         return this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]);
      } else {
         throw (new NucleusException(this.failureMessage("getObject"))).setFatal();
      }
   }

   public Table getTable() {
      return this.containerIsStoredInSingleColumn() ? this.table : null;
   }

   public int getNumberOfDatastoreMappings() {
      return this.containerIsStoredInSingleColumn() ? super.getNumberOfDatastoreMappings() : 0;
   }

   public DatastoreMapping getDatastoreMapping(int index) {
      if (this.containerIsStoredInSingleColumn()) {
         return super.getDatastoreMapping(index);
      } else {
         throw new NoDatastoreMappingException(this.mmd.getName());
      }
   }

   public DatastoreMapping[] getDatastoreMappings() {
      if (this.containerIsStoredInSingleColumn()) {
         return super.getDatastoreMappings();
      } else {
         throw new NoDatastoreMappingException(this.mmd.getName());
      }
   }

   protected boolean containerIsStoredInSingleColumn() {
      if (this.mmd != null && this.mmd.isSerialized()) {
         return true;
      } else if (this.mmd != null && this.mmd.hasCollection() && SCOUtils.collectionHasSerialisedElements(this.mmd)) {
         return true;
      } else if (this.mmd != null && this.mmd.hasMap() && SCOUtils.mapHasSerialisedKeysAndValues(this.mmd)) {
         return true;
      } else if (this.mmd != null && this.mmd.hasArray() && SCOUtils.arrayIsStoredInSingleColumn(this.mmd, this.storeMgr.getMetaDataManager())) {
         return !MetaDataUtils.getInstance().arrayStorableAsByteArrayInSingleColumn(this.mmd);
      } else {
         return false;
      }
   }

   public boolean includeInFetchStatement() {
      return this.containerIsStoredInSingleColumn();
   }

   public boolean includeInUpdateStatement() {
      return this.containerIsStoredInSingleColumn();
   }

   public boolean includeInInsertStatement() {
      return this.containerIsStoredInSingleColumn();
   }

   protected SCO replaceFieldWithWrapper(ObjectProvider op, Object value) {
      Class type = this.mmd.getType();
      if (value != null) {
         type = value.getClass();
      } else if (this.mmd.getOrderMetaData() != null && type.isAssignableFrom(List.class)) {
         type = List.class;
      }

      return SCOUtils.newSCOInstance(op, this.mmd, type, value, true);
   }

   public void postFetch(ObjectProvider sm) {
      if (!this.containerIsStoredInSingleColumn()) {
         this.replaceFieldWithWrapper(sm, (Object)null);
      }
   }
}
