package org.datanucleus.store.rdbms.fieldmanager;

import java.util.Collection;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.fieldmanager.AbstractFieldManager;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.InterfaceMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.CollectionTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.rdbms.table.TableImpl;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class DynamicSchemaFieldManager extends AbstractFieldManager {
   RDBMSStoreManager rdbmsMgr;
   ObjectProvider op;
   boolean schemaUpdatesPerformed = false;

   public DynamicSchemaFieldManager(RDBMSStoreManager rdbmsMgr, ObjectProvider op) {
      this.rdbmsMgr = rdbmsMgr;
      this.op = op;
   }

   public boolean hasPerformedSchemaUpdates() {
      return this.schemaUpdatesPerformed;
   }

   public void storeObjectField(int fieldNumber, Object value) {
      if (value != null) {
         ExecutionContext ec = this.op.getExecutionContext();
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         DatastoreClass table = this.rdbmsMgr.getDatastoreClass(this.op.getObject().getClass().getName(), clr);
         JavaTypeMapping fieldMapping = table.getMemberMapping(mmd);
         if (fieldMapping != null) {
            if (fieldMapping instanceof InterfaceMapping) {
               InterfaceMapping intfMapping = (InterfaceMapping)fieldMapping;
               if (mmd != null && (mmd.getFieldTypes() != null || mmd.hasExtension("implementation-classes"))) {
                  return;
               }

               this.processInterfaceMappingForValue(intfMapping, value, mmd, ec);
            } else if (mmd.hasCollection()) {
               boolean hasJoin = false;
               if (mmd.getJoinMetaData() != null) {
                  hasJoin = true;
               } else {
                  AbstractMemberMetaData[] relMmds = mmd.getRelatedMemberMetaData(clr);
                  if (relMmds != null && relMmds[0].getJoinMetaData() != null) {
                     hasJoin = true;
                  }
               }

               if (!hasJoin) {
                  return;
               }

               Collection coll = (Collection)value;
               if (coll.isEmpty()) {
                  return;
               }

               Table joinTbl = fieldMapping.getStoreManager().getTable(mmd);
               CollectionTable collTbl = (CollectionTable)joinTbl;
               JavaTypeMapping elemMapping = collTbl.getElementMapping();
               if (elemMapping instanceof InterfaceMapping) {
                  InterfaceMapping intfMapping = (InterfaceMapping)elemMapping;
                  this.processInterfaceMappingForValue(intfMapping, coll.iterator().next(), mmd, ec);
               }
            } else if (mmd.hasMap()) {
               NucleusLogger.DATASTORE_SCHEMA.debug("TODO : Support dynamic schema updates for Map field " + mmd.getFullFieldName());
            }
         }

      }
   }

   public void storeBooleanField(int fieldNumber, boolean value) {
   }

   public void storeByteField(int fieldNumber, byte value) {
   }

   public void storeCharField(int fieldNumber, char value) {
   }

   public void storeDoubleField(int fieldNumber, double value) {
   }

   public void storeFloatField(int fieldNumber, float value) {
   }

   public void storeIntField(int fieldNumber, int value) {
   }

   public void storeLongField(int fieldNumber, long value) {
   }

   public void storeShortField(int fieldNumber, short value) {
   }

   public void storeStringField(int fieldNumber, String value) {
   }

   protected void processInterfaceMappingForValue(InterfaceMapping intfMapping, Object value, AbstractMemberMetaData mmd, ExecutionContext ec) {
      if (intfMapping.getMappingStrategy() == 0) {
         int intfImplMappingNumber = intfMapping.getMappingNumberForValue(ec, value);
         if (intfImplMappingNumber == -1) {
            if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug("Dynamic schema updates : field=" + mmd.getFullFieldName() + " has an interface mapping yet " + StringUtils.toJVMIDString(value) + " is not a known implementation - trying to update the schema ...");
            }

            MetaDataManager mmgr = ec.getNucleusContext().getMetaDataManager();
            ClassLoaderResolver clr = ec.getClassLoaderResolver();
            mmgr.getMetaDataForClass(value.getClass(), clr);
            String[] impls = ec.getMetaDataManager().getClassesImplementingInterface(intfMapping.getType(), clr);
            if (ClassUtils.stringArrayContainsValue(impls, value.getClass().getName())) {
               try {
                  if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
                     NucleusLogger.DATASTORE_SCHEMA.debug("Dynamic schema updates : field=" + mmd.getFullFieldName() + " has a new implementation available so reinitialising its mapping");
                  }

                  intfMapping.initialize(mmd, intfMapping.getTable(), clr);
                  intfMapping.getStoreManager().validateTable((TableImpl)intfMapping.getTable(), clr);
               } catch (Exception e) {
                  NucleusLogger.DATASTORE_SCHEMA.debug("Exception thrown trying to create missing columns for implementation", e);
                  throw new NucleusException("Exception thrown performing dynamic update of schema", e);
               }

               this.schemaUpdatesPerformed = true;
            }
         }
      }

   }
}
