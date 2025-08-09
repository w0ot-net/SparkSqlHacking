package org.datanucleus.store.rdbms.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ForeignKeyAction;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.JoinMetaData;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.mapping.MappingConsumer;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class SecondaryTable extends AbstractClassTable implements SecondaryDatastoreClass {
   private ClassTable primaryTable;
   private JoinMetaData joinMetaData;

   SecondaryTable(DatastoreIdentifier tableName, RDBMSStoreManager storeMgr, ClassTable primaryTable, JoinMetaData jmd, ClassLoaderResolver clr) {
      super(tableName, storeMgr);
      if (primaryTable == null) {
         throw new NucleusUserException(Localiser.msg("057045", new Object[]{tableName.getName()}));
      } else {
         this.primaryTable = primaryTable;
         this.joinMetaData = jmd;
         if (this.joinMetaData == null) {
            JoinMetaData[] joins = this.primaryTable.getClassMetaData().getJoinMetaData();

            for(int i = 0; i < joins.length; ++i) {
               if (tableName.getName().equals(joins[i].getTable())) {
                  this.joinMetaData = joins[i];
                  break;
               }
            }
         }

      }
   }

   public void preInitialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
      if (!this.isPKInitialized()) {
         this.initializePK(clr);
      }

   }

   public void initialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057023", new Object[]{this}));
      }

      this.state = 2;
   }

   public void postInitialize(ClassLoaderResolver clr) {
      this.assertIsInitialized();
   }

   protected void initializePK(ClassLoaderResolver clr) {
      this.assertIsPKUninitialized();
      if (this.primaryTable.getIdentityType() == IdentityType.APPLICATION) {
         this.addApplicationIdUsingClassTableId(this.joinMetaData, this.primaryTable, clr, this.primaryTable.getClassMetaData());
      } else if (this.primaryTable.getIdentityType() == IdentityType.DATASTORE) {
         ColumnMetaData colmd = null;
         if (this.joinMetaData != null && this.joinMetaData.getColumnMetaData() != null && this.joinMetaData.getColumnMetaData().length > 0) {
            colmd = this.joinMetaData.getColumnMetaData()[0];
         }

         this.addDatastoreId(colmd, this.primaryTable, this.primaryTable.getClassMetaData());
      }

      this.state = 1;
   }

   public PrimaryKey getPrimaryKey() {
      PrimaryKey pk = super.getPrimaryKey();
      if (this.joinMetaData == null) {
         throw new NucleusUserException("A relationship to a secondary table requires a <join> specification. The secondary table is " + this.getDatastoreIdentifierFullyQualified() + " and the primary table is " + this.getPrimaryTable() + ". The fields mapped to this secondary table are: " + this.memberMappingsMap.keySet().toString());
      } else {
         PrimaryKeyMetaData pkmd = this.joinMetaData.getPrimaryKeyMetaData();
         if (pkmd != null && pkmd.getName() != null) {
            pk.setName(pkmd.getName());
         }

         return pk;
      }
   }

   public DatastoreClass getPrimaryDatastoreClass() {
      return this.primaryTable;
   }

   public JoinMetaData getJoinMetaData() {
      return this.joinMetaData;
   }

   public IdentityType getIdentityType() {
      return this.primaryTable.getIdentityType();
   }

   public String getType() {
      return this.primaryTable.getType();
   }

   public boolean isObjectIdDatastoreAttributed() {
      return false;
   }

   public boolean isBaseDatastoreClass() {
      return this.primaryTable.isBaseDatastoreClass();
   }

   public DatastoreClass getBaseDatastoreClass() {
      return this.primaryTable.getBaseDatastoreClass();
   }

   public DatastoreClass getBaseDatastoreClassWithMember(AbstractMemberMetaData mmd) {
      return this.primaryTable.getBaseDatastoreClassWithMember(mmd);
   }

   public DatastoreClass getSuperDatastoreClass() {
      return null;
   }

   public boolean isSuperDatastoreClass(DatastoreClass table) {
      return false;
   }

   public Collection getSecondaryDatastoreClasses() {
      return null;
   }

   public boolean managesClass(String className) {
      return false;
   }

   public String[] getManagedClasses() {
      return null;
   }

   protected List getExpectedForeignKeys() {
      this.assertIsInitialized();
      boolean autoMode = false;
      if (this.storeMgr.getStringProperty("datanucleus.rdbms.constraintCreateMode").equals("DataNucleus")) {
         autoMode = true;
      }

      ArrayList foreignKeys = new ArrayList();
      ForeignKeyMetaData fkmd = this.joinMetaData != null ? this.joinMetaData.getForeignKeyMetaData() : null;
      if (autoMode || fkmd != null && fkmd.getDeleteAction() != ForeignKeyAction.NONE) {
         ForeignKey fk = new ForeignKey(this.getIdMapping(), this.dba, this.primaryTable, fkmd != null && fkmd.isDeferred());
         if (fkmd != null && fkmd.getName() != null) {
            fk.setName(fkmd.getName());
         }

         foreignKeys.add(0, fk);
      }

      return foreignKeys;
   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      this.assertIsInitialized();
      JavaTypeMapping m = (JavaTypeMapping)this.memberMappingsMap.get(mmd);
      return m != null ? m : null;
   }

   public JavaTypeMapping getMemberMappingInDatastoreClass(AbstractMemberMetaData mmd) {
      return this.getMemberMapping(mmd);
   }

   public JavaTypeMapping getMemberMapping(String fieldName) {
      return this.getMemberMapping(this.primaryTable.getMetaDataForMember(fieldName));
   }

   public JavaTypeMapping getIdMapping() {
      if (this.idMapping != null) {
         return this.idMapping;
      } else {
         PersistableMapping mapping = new PersistableMapping();
         mapping.initialize(this.getStoreManager(), this.primaryTable.getClassMetaData().getFullClassName());
         if (this.getIdentityType() == IdentityType.DATASTORE) {
            mapping.addJavaTypeMapping(this.datastoreIDMapping);
         } else if (this.getIdentityType() == IdentityType.APPLICATION) {
            for(int i = 0; i < this.pkMappings.length; ++i) {
               mapping.addJavaTypeMapping(this.pkMappings[i]);
            }
         }

         this.idMapping = mapping;
         return mapping;
      }
   }

   public void providePrimaryKeyMappings(MappingConsumer consumer) {
      consumer.preConsumeMapping(this.highestMemberNumber + 1);
      ClassMetaData cmd = this.primaryTable.getClassMetaData();
      if (this.pkMappings != null) {
         int[] primaryKeyFieldNumbers = cmd.getPKMemberPositions();

         for(int i = 0; i < this.pkMappings.length; ++i) {
            AbstractMemberMetaData fmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(primaryKeyFieldNumbers[i]);
            consumer.consumeMapping(this.pkMappings[i], fmd);
         }
      } else {
         int[] primaryKeyFieldNumbers = cmd.getPKMemberPositions();
         int countPkFields = cmd.getNoOfPrimaryKeyMembers();

         for(int i = 0; i < countPkFields; ++i) {
            AbstractMemberMetaData pkfmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(primaryKeyFieldNumbers[i]);
            consumer.consumeMapping(this.getMemberMapping(pkfmd), pkfmd);
         }
      }

   }

   public void provideExternalMappings(MappingConsumer consumer, int mappingType) {
   }

   public void provideUnmappedColumns(MappingConsumer consumer) {
   }

   public JavaTypeMapping getExternalMapping(AbstractMemberMetaData fmd, int mappingType) {
      throw (new NucleusException("N/A")).setFatal();
   }

   public AbstractMemberMetaData getMetaDataForExternalMapping(JavaTypeMapping mapping, int mappingType) {
      throw (new NucleusException("N/A")).setFatal();
   }
}
