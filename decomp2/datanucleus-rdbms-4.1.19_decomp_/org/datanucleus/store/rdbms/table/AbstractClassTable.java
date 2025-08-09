package org.datanucleus.store.rdbms.table;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.DatastoreId;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ColumnMetaDataContainer;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.mapping.MappingConsumer;
import org.datanucleus.store.rdbms.mapping.java.DatastoreIdMapping;
import org.datanucleus.store.rdbms.mapping.java.IntegerMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.StringMapping;
import org.datanucleus.store.valuegenerator.AbstractGenerator;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractClassTable extends TableImpl {
   protected Map memberMappingsMap = new LinkedHashMap();
   protected JavaTypeMapping datastoreIDMapping;
   protected JavaTypeMapping[] pkMappings;
   protected JavaTypeMapping idMapping;
   protected JavaTypeMapping versionMapping;
   protected VersionMetaData versionMetaData;
   protected DiscriminatorMetaData discriminatorMetaData;
   protected JavaTypeMapping discriminatorMapping;
   protected int highestMemberNumber = 0;
   protected JavaTypeMapping tenantMapping;

   public AbstractClassTable(DatastoreIdentifier tableName, RDBMSStoreManager storeMgr) {
      super(tableName, storeMgr);
   }

   public Table getPrimaryTable() {
      return this;
   }

   protected abstract void initializePK(ClassLoaderResolver var1);

   public boolean managesMember(String memberName) {
      if (memberName == null) {
         return false;
      } else {
         return this.getMappingForMemberName(memberName) != null;
      }
   }

   protected JavaTypeMapping getMappingForMemberName(String memberName) {
      for(AbstractMemberMetaData mmd : this.memberMappingsMap.keySet()) {
         if (mmd.getFullFieldName().equals(memberName)) {
            return (JavaTypeMapping)this.memberMappingsMap.get(mmd);
         }
      }

      return null;
   }

   public boolean managesMapping(JavaTypeMapping mapping) {
      Collection<JavaTypeMapping> mappings = this.memberMappingsMap.values();
      if (mappings.contains(mapping)) {
         return true;
      } else if (mapping == this.discriminatorMapping) {
         return true;
      } else if (mapping == this.versionMapping) {
         return true;
      } else if (mapping == this.datastoreIDMapping) {
         return true;
      } else if (mapping == this.idMapping) {
         return true;
      } else {
         return mapping == this.tenantMapping;
      }
   }

   final void addApplicationIdUsingClassTableId(ColumnMetaDataContainer columnContainer, DatastoreClass refTable, ClassLoaderResolver clr, AbstractClassMetaData cmd) {
      ColumnMetaData[] userdefinedCols = null;
      int nextUserdefinedCol = 0;
      if (columnContainer != null) {
         userdefinedCols = columnContainer.getColumnMetaData();
      }

      this.pkMappings = new JavaTypeMapping[cmd.getPKMemberPositions().length];

      for(int i = 0; i < cmd.getPKMemberPositions().length; ++i) {
         AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(cmd.getPKMemberPositions()[i]);
         JavaTypeMapping mapping = refTable.getMemberMapping(mmd);
         if (mapping == null) {
            throw new NucleusUserException("Cannot find mapping for field " + mmd.getFullFieldName() + " in table " + refTable.toString() + " " + StringUtils.collectionToString(refTable.getColumns()));
         }

         JavaTypeMapping masterMapping = this.storeMgr.getMappingManager().getMapping(clr.classForName(mapping.getType()));
         masterMapping.setMemberMetaData(mmd);
         masterMapping.setTable(this);
         this.pkMappings[i] = masterMapping;

         for(int j = 0; j < mapping.getNumberOfDatastoreMappings(); ++j) {
            JavaTypeMapping m = masterMapping;
            Column refColumn = mapping.getDatastoreMapping(j).getColumn();
            if (mapping instanceof PersistableMapping) {
               m = this.storeMgr.getMappingManager().getMapping(clr.classForName(refColumn.getJavaTypeMapping().getType()));
               ((PersistableMapping)masterMapping).addJavaTypeMapping(m);
            }

            ColumnMetaData userdefinedColumn = null;
            if (userdefinedCols != null) {
               for(int k = 0; k < userdefinedCols.length; ++k) {
                  if (refColumn.getIdentifier().toString().equals(userdefinedCols[k].getTarget())) {
                     userdefinedColumn = userdefinedCols[k];
                     break;
                  }
               }

               if (userdefinedColumn == null && nextUserdefinedCol < userdefinedCols.length) {
                  userdefinedColumn = userdefinedCols[nextUserdefinedCol++];
               }
            }

            Column idColumn = null;
            if (userdefinedColumn != null) {
               idColumn = this.addColumn(refColumn.getStoredJavaType(), this.storeMgr.getIdentifierFactory().newIdentifier(IdentifierType.COLUMN, userdefinedColumn.getName()), m, refColumn.getColumnMetaData());
            } else {
               idColumn = this.addColumn(refColumn.getStoredJavaType(), refColumn.getIdentifier(), m, refColumn.getColumnMetaData());
            }

            if (mapping.getDatastoreMapping(j).getColumn().getColumnMetaData() != null) {
               refColumn.copyConfigurationTo(idColumn);
            }

            idColumn.setPrimaryKey();
            this.getStoreManager().getMappingManager().createDatastoreMapping(m, idColumn, refColumn.getJavaTypeMapping().getType());
         }

         int absoluteFieldNumber = mmd.getAbsoluteFieldNumber();
         if (absoluteFieldNumber > this.highestMemberNumber) {
            this.highestMemberNumber = absoluteFieldNumber;
         }
      }

   }

   void addDatastoreId(ColumnMetaData columnMetaData, DatastoreClass refTable, AbstractClassMetaData cmd) {
      this.datastoreIDMapping = new DatastoreIdMapping();
      this.datastoreIDMapping.setTable(this);
      this.datastoreIDMapping.initialize(this.storeMgr, cmd.getFullClassName());
      ColumnMetaData colmd = null;
      if (columnMetaData == null) {
         colmd = new ColumnMetaData();
      } else {
         colmd = columnMetaData;
      }

      if (colmd.getName() == null) {
         if (refTable != null) {
            colmd.setName(this.storeMgr.getIdentifierFactory().newColumnIdentifier(refTable.getIdentifier().getName(), this.storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(DatastoreId.class), FieldRole.ROLE_OWNER, false).getName());
         } else {
            colmd.setName(this.storeMgr.getIdentifierFactory().newColumnIdentifier(this.identifier.getName(), this.storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(DatastoreId.class), FieldRole.ROLE_NONE, false).getName());
         }
      }

      Column idColumn = this.addColumn(DatastoreId.class.getName(), this.storeMgr.getIdentifierFactory().newIdentifier(IdentifierType.COLUMN, colmd.getName()), this.datastoreIDMapping, colmd);
      idColumn.setPrimaryKey();
      String strategyName = cmd.getIdentityMetaData().getValueStrategy().toString();
      if (cmd.getIdentityMetaData().getValueStrategy().equals(IdentityStrategy.CUSTOM)) {
         strategyName = cmd.getIdentityMetaData().getValueStrategy().getCustomName();
      }

      if (strategyName != null && IdentityStrategy.NATIVE.toString().equals(strategyName)) {
         strategyName = this.storeMgr.getStrategyForNative(cmd, -1);
      }

      Class valueGeneratedType = Long.class;
      if (strategyName != null && IdentityStrategy.IDENTITY.toString().equals(strategyName)) {
         valueGeneratedType = this.dba.getAutoIncrementJavaTypeForType(valueGeneratedType);
         if (valueGeneratedType != Long.class) {
            NucleusLogger.DATASTORE_SCHEMA.debug("Class " + cmd.getFullClassName() + " uses IDENTITY strategy and rather than using BIGINT  for the column type, using " + valueGeneratedType.getName() + " since the datastore requires that");
         }
      }

      try {
         AbstractGenerator generator = (AbstractGenerator)this.storeMgr.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store_valuegenerator", new String[]{"name", "unique"}, new String[]{strategyName, "true"}, "class-name", new Class[]{String.class, Properties.class}, new Object[]{null, null});
         if (generator == null) {
            generator = (AbstractGenerator)this.storeMgr.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store_valuegenerator", new String[]{"name", "datastore"}, new String[]{strategyName, this.storeMgr.getStoreManagerKey()}, "class-name", new Class[]{String.class, Properties.class}, new Object[]{null, null});
         }

         try {
            if (generator != null) {
               valueGeneratedType = (Class)generator.getClass().getMethod("getStorageClass").invoke((Object)null);
            }
         } catch (Exception var10) {
         }
      } catch (Exception e) {
         NucleusLogger.VALUEGENERATION.warn("Error obtaining generator for strategy=" + strategyName, e);
      }

      this.storeMgr.getMappingManager().createDatastoreMapping(this.datastoreIDMapping, idColumn, valueGeneratedType.getName());
      this.logMapping("DATASTORE_ID", this.datastoreIDMapping);
      if (this.isObjectIdDatastoreAttributed() && this instanceof DatastoreClass && ((DatastoreClass)this).isBaseDatastoreClass()) {
         idColumn.setIdentity(true);
      }

      if (idColumn.isIdentity() && !this.dba.supportsOption("IdentityColumns")) {
         throw (new NucleusException(Localiser.msg("057020", new Object[]{cmd.getFullClassName(), "datastore-identity"}))).setFatal();
      }
   }

   protected void addMultitenancyMapping(ColumnMetaData colmd) {
      String colName = "TENANT_ID";
      if (colmd != null && colmd.getName() != null) {
         colName = colmd.getName();
      }

      String typeName = String.class.getName();
      if (colmd != null && colmd.getJdbcType() != null && colmd.getJdbcType() == JdbcType.INTEGER) {
         typeName = Integer.class.getName();
      }

      if (typeName.equals(Integer.class.getName())) {
         this.tenantMapping = new IntegerMapping();
      } else {
         this.tenantMapping = new StringMapping();
      }

      this.tenantMapping.setTable(this);
      this.tenantMapping.initialize(this.storeMgr, typeName);
      Column tenantColumn = this.addColumn(typeName, this.storeMgr.getIdentifierFactory().newIdentifier(IdentifierType.COLUMN, colName), this.tenantMapping, colmd);
      this.storeMgr.getMappingManager().createDatastoreMapping(this.tenantMapping, tenantColumn, typeName);
      this.logMapping("MULTITENANCY", this.tenantMapping);
   }

   protected void addMemberMapping(JavaTypeMapping fieldMapping) {
      AbstractMemberMetaData mmd = fieldMapping.getMemberMetaData();
      this.logMapping(mmd.getFullFieldName(), fieldMapping);
      this.memberMappingsMap.put(mmd, fieldMapping);
      int absoluteFieldNumber = mmd.getAbsoluteFieldNumber();
      if (absoluteFieldNumber > this.highestMemberNumber) {
         this.highestMemberNumber = absoluteFieldNumber;
      }

   }

   public abstract IdentityType getIdentityType();

   public abstract boolean isObjectIdDatastoreAttributed();

   public JavaTypeMapping getDatastoreIdMapping() {
      this.assertIsInitialized();
      return this.datastoreIDMapping;
   }

   public org.datanucleus.store.schema.table.Column getDatastoreIdColumn() {
      this.assertIsInitialized();
      return this.datastoreIDMapping != null ? this.datastoreIDMapping.getDatastoreMapping(0).getColumn() : null;
   }

   public JavaTypeMapping getVersionMapping(boolean allowSuperclasses) {
      return this.versionMapping;
   }

   public org.datanucleus.store.schema.table.Column getVersionColumn() {
      return this.versionMapping != null ? this.versionMapping.getDatastoreMapping(0).getColumn() : null;
   }

   public JavaTypeMapping getDiscriminatorMapping(boolean allowSuperclasses) {
      return this.discriminatorMapping;
   }

   public org.datanucleus.store.schema.table.Column getDiscriminatorColumn() {
      return this.discriminatorMapping != null ? this.discriminatorMapping.getDatastoreMapping(0).getColumn() : null;
   }

   public JavaTypeMapping getMultitenancyMapping() {
      return this.tenantMapping;
   }

   public org.datanucleus.store.schema.table.Column getMultitenancyColumn() {
      return this.tenantMapping != null ? this.tenantMapping.getDatastoreMapping(0).getColumn() : null;
   }

   public final void provideDatastoreIdMappings(MappingConsumer consumer) {
      consumer.preConsumeMapping(this.highestMemberNumber + 1);
      if (this.getIdentityType() == IdentityType.DATASTORE) {
         consumer.consumeMapping(this.getDatastoreIdMapping(), 2);
      }

   }

   public abstract void providePrimaryKeyMappings(MappingConsumer var1);

   public final void provideNonPrimaryKeyMappings(MappingConsumer consumer) {
      consumer.preConsumeMapping(this.highestMemberNumber + 1);

      for(AbstractMemberMetaData mmd : this.memberMappingsMap.keySet()) {
         JavaTypeMapping memberMapping = (JavaTypeMapping)this.memberMappingsMap.get(mmd);
         if (memberMapping != null && !mmd.isPrimaryKey()) {
            consumer.consumeMapping(memberMapping, mmd);
         }
      }

   }

   public void provideMappingsForMembers(MappingConsumer consumer, AbstractMemberMetaData[] mmds, boolean includeSecondaryTables) {
      consumer.preConsumeMapping(this.highestMemberNumber + 1);

      for(int i = 0; i < mmds.length; ++i) {
         JavaTypeMapping fieldMapping = (JavaTypeMapping)this.memberMappingsMap.get(mmds[i]);
         if (fieldMapping != null && !mmds[i].isPrimaryKey()) {
            consumer.consumeMapping(fieldMapping, mmds[i]);
         }
      }

   }

   public final void provideVersionMappings(MappingConsumer consumer) {
      consumer.preConsumeMapping(this.highestMemberNumber + 1);
      if (this.getVersionMapping(false) != null) {
         consumer.consumeMapping(this.getVersionMapping(false), 1);
      }

   }

   public final void provideDiscriminatorMappings(MappingConsumer consumer) {
      consumer.preConsumeMapping(this.highestMemberNumber + 1);
      if (this.getDiscriminatorMapping(false) != null) {
         consumer.consumeMapping(this.getDiscriminatorMapping(false), 3);
      }

   }

   public final void provideMultitenancyMapping(MappingConsumer consumer) {
      consumer.preConsumeMapping(this.highestMemberNumber + 1);
      if (this.tenantMapping != null) {
         consumer.consumeMapping(this.tenantMapping, 7);
      }

   }
}
