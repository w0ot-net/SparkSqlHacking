package org.datanucleus.store.rdbms.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.KeyMetaData;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.metadata.ValueMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedKeyPCMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedValuePCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class MapTable extends JoinTable implements DatastoreMap {
   private JavaTypeMapping keyMapping;
   private JavaTypeMapping valueMapping;
   private JavaTypeMapping orderMapping;
   protected Map embeddedKeyMappingsMap;
   protected Map embeddedValueMappingsMap;

   public MapTable(DatastoreIdentifier tableName, AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr) {
      super(tableName, mmd, storeMgr);
   }

   public void initialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
      MapMetaData mapmd = this.mmd.getMap();
      if (mapmd == null) {
         throw new NucleusUserException(Localiser.msg("057017", new Object[]{this.mmd}));
      } else {
         PrimaryKeyMetaData pkmd = this.mmd.getJoinMetaData() != null ? this.mmd.getJoinMetaData().getPrimaryKeyMetaData() : null;
         boolean pkColsSpecified = pkmd != null && pkmd.getColumnMetaData() != null;
         boolean pkRequired = this.requiresPrimaryKey();
         ColumnMetaData[] ownerColmd = null;
         if (this.mmd.getJoinMetaData() != null && this.mmd.getJoinMetaData().getColumnMetaData() != null && this.mmd.getJoinMetaData().getColumnMetaData().length > 0) {
            ownerColmd = this.mmd.getJoinMetaData().getColumnMetaData();
         }

         this.ownerMapping = ColumnCreator.createColumnsForJoinTables(clr.classForName(this.ownerType), this.mmd, ownerColmd, this.storeMgr, this, pkRequired, false, FieldRole.ROLE_OWNER, clr);
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            this.logMapping(this.mmd.getFullFieldName() + ".[OWNER]", this.ownerMapping);
         }

         String keyValueFieldName = this.mmd.getKeyMetaData() != null ? this.mmd.getKeyMetaData().getMappedBy() : null;
         String valueKeyFieldName = this.mmd.getValueMetaData() != null ? this.mmd.getValueMetaData().getMappedBy() : null;
         boolean keyPC = this.mmd.hasMap() && this.mmd.getMap().keyIsPersistent();
         Class keyCls = clr.classForName(mapmd.getKeyType());
         if (keyValueFieldName == null || !this.isEmbeddedValuePC()) {
            if (!this.isSerialisedKey() && !this.isEmbeddedKeyPC() && (!this.isEmbeddedKey() || keyPC) && !ClassUtils.isReferenceType(keyCls)) {
               ColumnMetaData[] keyColmd = null;
               KeyMetaData keymd = this.mmd.getKeyMetaData();
               if (keymd != null && keymd.getColumnMetaData() != null && keymd.getColumnMetaData().length > 0) {
                  keyColmd = keymd.getColumnMetaData();
               }

               this.keyMapping = ColumnCreator.createColumnsForJoinTables(keyCls, this.mmd, keyColmd, this.storeMgr, this, false, false, FieldRole.ROLE_MAP_KEY, clr);
               if (this.mmd.getContainer().allowNulls() == Boolean.TRUE) {
                  for(int i = 0; i < this.keyMapping.getNumberOfDatastoreMappings(); ++i) {
                     Column elementCol = this.keyMapping.getDatastoreMapping(i).getColumn();
                     elementCol.setNullable(true);
                  }
               }

               if (NucleusLogger.DATASTORE.isDebugEnabled()) {
                  this.logMapping(this.mmd.getFullFieldName() + ".[KEY]", this.keyMapping);
               }
            } else {
               this.keyMapping = this.storeMgr.getMappingManager().getMapping(this, this.mmd, clr, FieldRole.ROLE_MAP_KEY);
               if (Boolean.TRUE.equals(this.mmd.getContainer().allowNulls())) {
                  for(int i = 0; i < this.keyMapping.getNumberOfDatastoreMappings(); ++i) {
                     Column elementCol = this.keyMapping.getDatastoreMapping(i).getColumn();
                     elementCol.setNullable(true);
                  }
               }

               if (NucleusLogger.DATASTORE.isDebugEnabled()) {
                  this.logMapping(this.mmd.getFullFieldName() + ".[KEY]", this.keyMapping);
               }

               if (valueKeyFieldName != null && this.isEmbeddedKeyPC()) {
                  EmbeddedKeyPCMapping embMapping = (EmbeddedKeyPCMapping)this.keyMapping;
                  this.valueMapping = embMapping.getJavaTypeMapping(valueKeyFieldName);
               }
            }
         }

         boolean valuePC = this.mmd.hasMap() && this.mmd.getMap().valueIsPersistent();
         Class valueCls = clr.classForName(mapmd.getValueType());
         if (valueKeyFieldName == null || !this.isEmbeddedKeyPC()) {
            if (!this.isSerialisedValue() && !this.isEmbeddedValuePC() && (!this.isEmbeddedValue() || valuePC) && !ClassUtils.isReferenceType(valueCls)) {
               ColumnMetaData[] valueColmd = null;
               ValueMetaData valuemd = this.mmd.getValueMetaData();
               if (valuemd != null && valuemd.getColumnMetaData() != null && valuemd.getColumnMetaData().length > 0) {
                  valueColmd = valuemd.getColumnMetaData();
               }

               this.valueMapping = ColumnCreator.createColumnsForJoinTables(clr.classForName(mapmd.getValueType()), this.mmd, valueColmd, this.storeMgr, this, false, true, FieldRole.ROLE_MAP_VALUE, clr);
               if (this.mmd.getContainer().allowNulls() == Boolean.TRUE) {
                  for(int i = 0; i < this.valueMapping.getNumberOfDatastoreMappings(); ++i) {
                     Column elementCol = this.valueMapping.getDatastoreMapping(i).getColumn();
                     elementCol.setNullable(true);
                  }
               }

               if (NucleusLogger.DATASTORE.isDebugEnabled()) {
                  this.logMapping(this.mmd.getFullFieldName() + ".[VALUE]", this.valueMapping);
               }
            } else {
               this.valueMapping = this.storeMgr.getMappingManager().getMapping(this, this.mmd, clr, FieldRole.ROLE_MAP_VALUE);
               if (this.mmd.getContainer().allowNulls() == Boolean.TRUE) {
                  for(int i = 0; i < this.valueMapping.getNumberOfDatastoreMappings(); ++i) {
                     Column elementCol = this.valueMapping.getDatastoreMapping(i).getColumn();
                     elementCol.setNullable(true);
                  }
               }

               if (NucleusLogger.DATASTORE.isDebugEnabled()) {
                  this.logMapping(this.mmd.getFullFieldName() + ".[VALUE]", this.valueMapping);
               }

               if (keyValueFieldName != null && this.isEmbeddedValuePC()) {
                  EmbeddedValuePCMapping embMapping = (EmbeddedValuePCMapping)this.valueMapping;
                  this.keyMapping = embMapping.getJavaTypeMapping(keyValueFieldName);
               }
            }
         }

         boolean orderRequired = false;
         if (this.mmd.getOrderMetaData() != null) {
            orderRequired = true;
         } else if (this.requiresPrimaryKey() && !pkColsSpecified) {
            if (this.isEmbeddedKeyPC()) {
               if (this.mmd.getMap().getKeyClassMetaData(clr, this.storeMgr.getMetaDataManager()).getIdentityType() != IdentityType.APPLICATION) {
                  orderRequired = true;
               }
            } else if (this.isSerialisedKey()) {
               orderRequired = true;
            } else if (this.keyMapping instanceof ReferenceMapping) {
               ReferenceMapping refMapping = (ReferenceMapping)this.keyMapping;
               if (refMapping.getJavaTypeMapping().length > 1) {
                  orderRequired = true;
               }
            } else if (!(this.keyMapping instanceof PersistableMapping)) {
               Column elementCol = this.keyMapping.getDatastoreMapping(0).getColumn();
               if (!this.storeMgr.getDatastoreAdapter().isValidPrimaryKeyType(elementCol.getJdbcType())) {
                  orderRequired = true;
               }
            }
         }

         if (orderRequired) {
            ColumnMetaData orderColmd = null;
            if (this.mmd.getOrderMetaData() != null && this.mmd.getOrderMetaData().getColumnMetaData() != null && this.mmd.getOrderMetaData().getColumnMetaData().length > 0) {
               orderColmd = this.mmd.getOrderMetaData().getColumnMetaData()[0];
               if (orderColmd.getName() == null) {
                  orderColmd = new ColumnMetaData(orderColmd);
                  DatastoreIdentifier id = this.storeMgr.getIdentifierFactory().newIndexFieldIdentifier(this.mmd);
                  orderColmd.setName(id.getName());
               }
            } else {
               DatastoreIdentifier id = this.storeMgr.getIdentifierFactory().newIndexFieldIdentifier(this.mmd);
               orderColmd = new ColumnMetaData();
               orderColmd.setName(id.getName());
            }

            this.orderMapping = this.storeMgr.getMappingManager().getMapping(Integer.TYPE);
            ColumnCreator.createIndexColumn(this.orderMapping, this.storeMgr, clr, this, orderColmd, pkRequired && !pkColsSpecified);
            if (NucleusLogger.DATASTORE.isDebugEnabled()) {
               this.logMapping(this.mmd.getFullFieldName() + ".[ORDER]", this.orderMapping);
            }
         }

         if (pkRequired) {
            if (pkColsSpecified) {
               this.applyUserPrimaryKeySpecification(pkmd);
            } else if (orderRequired) {
               this.orderMapping.getDatastoreMapping(0).getColumn().setPrimaryKey();
            } else {
               for(int i = 0; i < this.keyMapping.getNumberOfDatastoreMappings(); ++i) {
                  this.keyMapping.getDatastoreMapping(i).getColumn().setPrimaryKey();
               }
            }
         }

         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057023", new Object[]{this}));
         }

         this.storeMgr.registerTableInitialized(this);
         this.state = 2;
      }
   }

   protected void applyUserPrimaryKeySpecification(PrimaryKeyMetaData pkmd) {
      ColumnMetaData[] pkCols = pkmd.getColumnMetaData();

      for(int i = 0; i < pkCols.length; ++i) {
         String colName = pkCols[i].getName();
         boolean found = false;

         for(int j = 0; j < this.ownerMapping.getNumberOfDatastoreMappings(); ++j) {
            if (this.ownerMapping.getDatastoreMapping(j).getColumn().getIdentifier().getName().equals(colName)) {
               this.ownerMapping.getDatastoreMapping(j).getColumn().setPrimaryKey();
               found = true;
            }
         }

         if (!found) {
            for(int j = 0; j < this.keyMapping.getNumberOfDatastoreMappings(); ++j) {
               if (this.keyMapping.getDatastoreMapping(j).getColumn().getIdentifier().getName().equals(colName)) {
                  this.keyMapping.getDatastoreMapping(j).getColumn().setPrimaryKey();
                  found = true;
               }
            }
         }

         if (!found) {
            for(int j = 0; j < this.valueMapping.getNumberOfDatastoreMappings(); ++j) {
               if (this.valueMapping.getDatastoreMapping(j).getColumn().getIdentifier().getName().equals(colName)) {
                  this.valueMapping.getDatastoreMapping(j).getColumn().setPrimaryKey();
                  found = true;
               }
            }
         }

         if (!found) {
            throw new NucleusUserException(Localiser.msg("057040", new Object[]{this.toString(), colName}));
         }
      }

   }

   public boolean isEmbeddedKey() {
      if (this.mmd.getMap() != null && this.mmd.getMap().isSerializedKey()) {
         return false;
      } else {
         return this.mmd.getMap() != null && this.mmd.getMap().isEmbeddedKey();
      }
   }

   public boolean isSerialisedKey() {
      return this.mmd.getMap() != null && this.mmd.getMap().isSerializedKey();
   }

   public boolean isSerialisedKeyPC() {
      return this.mmd.getMap() != null && this.mmd.getMap().isSerializedKey() && this.mmd.getMap().keyIsPersistent();
   }

   public boolean isEmbeddedKeyPC() {
      if (this.mmd.getMap() != null && this.mmd.getMap().isSerializedKey()) {
         return false;
      } else {
         return this.mmd.getKeyMetaData() != null && this.mmd.getKeyMetaData().getEmbeddedMetaData() != null;
      }
   }

   public boolean isEmbeddedValue() {
      if (this.mmd.getMap() != null && this.mmd.getMap().isSerializedValue()) {
         return false;
      } else {
         return this.mmd.getMap() != null && this.mmd.getMap().isEmbeddedValue();
      }
   }

   public boolean isSerialisedValue() {
      return this.mmd.getMap() != null && this.mmd.getMap().isSerializedValue();
   }

   public boolean isSerialisedValuePC() {
      return this.mmd.getMap() != null && this.mmd.getMap().isSerializedValue() && this.mmd.getMap().valueIsPersistent();
   }

   public boolean isEmbeddedValuePC() {
      if (this.mmd.getMap() != null && this.mmd.getMap().isSerializedValue()) {
         return false;
      } else {
         return this.mmd.getValueMetaData() != null && this.mmd.getValueMetaData().getEmbeddedMetaData() != null;
      }
   }

   public JavaTypeMapping getKeyMapping() {
      this.assertIsInitialized();
      return this.keyMapping;
   }

   public JavaTypeMapping getValueMapping() {
      this.assertIsInitialized();
      return this.valueMapping;
   }

   public String getKeyType() {
      return this.mmd.getMap().getKeyType();
   }

   public String getValueType() {
      return this.mmd.getMap().getValueType();
   }

   public JavaTypeMapping getOrderMapping() {
      this.assertIsInitialized();
      return this.orderMapping;
   }

   public List getExpectedForeignKeys(ClassLoaderResolver clr) {
      this.assertIsInitialized();
      boolean autoMode = false;
      if (this.storeMgr.getStringProperty("datanucleus.rdbms.constraintCreateMode").equals("DataNucleus")) {
         autoMode = true;
      }

      ArrayList foreignKeys = new ArrayList();

      try {
         DatastoreClass referencedTable = this.storeMgr.getDatastoreClass(this.ownerType, clr);
         if (referencedTable != null) {
            ForeignKeyMetaData fkmd = null;
            if (this.mmd.getJoinMetaData() != null) {
               fkmd = this.mmd.getJoinMetaData().getForeignKeyMetaData();
            }

            if (fkmd != null || autoMode) {
               ForeignKey fk = new ForeignKey(this.ownerMapping, this.dba, referencedTable, true);
               fk.setForMetaData(fkmd);
               foreignKeys.add(fk);
            }
         }

         if (!this.isSerialisedValuePC()) {
            if (!this.isEmbeddedValuePC()) {
               if (this.mmd.getMap().valueIsPersistent()) {
                  referencedTable = this.storeMgr.getDatastoreClass(this.mmd.getMap().getValueType(), clr);
                  if (referencedTable != null) {
                     ForeignKeyMetaData fkmd = null;
                     if (this.mmd.getValueMetaData() != null) {
                        fkmd = this.mmd.getValueMetaData().getForeignKeyMetaData();
                     }

                     if (fkmd != null || autoMode) {
                        ForeignKey fk = new ForeignKey(this.valueMapping, this.dba, referencedTable, true);
                        fk.setForMetaData(fkmd);
                        foreignKeys.add(fk);
                     }
                  }
               }
            } else {
               EmbeddedValuePCMapping embMapping = (EmbeddedValuePCMapping)this.valueMapping;

               for(int i = 0; i < embMapping.getNumberOfJavaTypeMappings(); ++i) {
                  JavaTypeMapping embFieldMapping = embMapping.getJavaTypeMapping(i);
                  AbstractMemberMetaData embFmd = embFieldMapping.getMemberMetaData();
                  if (ClassUtils.isReferenceType(embFmd.getType()) && embFieldMapping instanceof ReferenceMapping) {
                     Collection fks = TableUtils.getForeignKeysForReferenceField(embFieldMapping, embFmd, autoMode, this.storeMgr, clr);
                     foreignKeys.addAll(fks);
                  } else if (this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(embFmd.getType(), clr) != null && embFieldMapping.getNumberOfDatastoreMappings() > 0 && embFieldMapping instanceof PersistableMapping) {
                     ForeignKey fk = TableUtils.getForeignKeyForPCField(embFieldMapping, embFmd, autoMode, this.storeMgr, clr);
                     if (fk != null) {
                        foreignKeys.add(fk);
                     }
                  }
               }
            }
         }

         if (!this.isSerialisedKeyPC()) {
            if (this.isEmbeddedKeyPC()) {
               EmbeddedKeyPCMapping embMapping = (EmbeddedKeyPCMapping)this.keyMapping;

               for(int i = 0; i < embMapping.getNumberOfJavaTypeMappings(); ++i) {
                  JavaTypeMapping embFieldMapping = embMapping.getJavaTypeMapping(i);
                  AbstractMemberMetaData embFmd = embFieldMapping.getMemberMetaData();
                  if (ClassUtils.isReferenceType(embFmd.getType()) && embFieldMapping instanceof ReferenceMapping) {
                     Collection fks = TableUtils.getForeignKeysForReferenceField(embFieldMapping, embFmd, autoMode, this.storeMgr, clr);
                     foreignKeys.addAll(fks);
                  } else if (this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(embFmd.getType(), clr) != null && embFieldMapping.getNumberOfDatastoreMappings() > 0 && embFieldMapping instanceof PersistableMapping) {
                     ForeignKey fk = TableUtils.getForeignKeyForPCField(embFieldMapping, embFmd, autoMode, this.storeMgr, clr);
                     if (fk != null) {
                        foreignKeys.add(fk);
                     }
                  }
               }
            } else if (this.mmd.getMap().keyIsPersistent()) {
               referencedTable = this.storeMgr.getDatastoreClass(this.mmd.getMap().getKeyType(), clr);
               if (referencedTable != null) {
                  ForeignKeyMetaData fkmd = null;
                  if (this.mmd.getKeyMetaData() != null) {
                     fkmd = this.mmd.getKeyMetaData().getForeignKeyMetaData();
                  }

                  if (fkmd != null || autoMode) {
                     ForeignKey fk = new ForeignKey(this.keyMapping, this.dba, referencedTable, true);
                     fk.setForMetaData(fkmd);
                     foreignKeys.add(fk);
                  }
               }
            }
         }
      } catch (NoTableManagedException var10) {
      }

      return foreignKeys;
   }

   protected Set getExpectedIndices(ClassLoaderResolver clr) {
      Set indices = new HashSet();
      if (this.mmd.getIndexMetaData() != null) {
         Index index = TableUtils.getIndexForField(this, this.mmd.getIndexMetaData(), this.ownerMapping);
         if (index != null) {
            indices.add(index);
         }
      } else if (this.mmd.getJoinMetaData() != null && this.mmd.getJoinMetaData().getIndexMetaData() != null) {
         Index index = TableUtils.getIndexForField(this, this.mmd.getJoinMetaData().getIndexMetaData(), this.ownerMapping);
         if (index != null) {
            indices.add(index);
         }
      } else {
         Index index = TableUtils.getIndexForField(this, (IndexMetaData)null, this.ownerMapping);
         if (index != null) {
            indices.add(index);
         }
      }

      if (this.keyMapping instanceof EmbeddedKeyPCMapping) {
         EmbeddedKeyPCMapping embMapping = (EmbeddedKeyPCMapping)this.keyMapping;

         for(int i = 0; i < embMapping.getNumberOfJavaTypeMappings(); ++i) {
            JavaTypeMapping embFieldMapping = embMapping.getJavaTypeMapping(i);
            IndexMetaData imd = embFieldMapping.getMemberMetaData().getIndexMetaData();
            if (imd != null) {
               Index index = TableUtils.getIndexForField(this, imd, embFieldMapping);
               if (index != null) {
                  indices.add(index);
               }
            }
         }
      } else {
         KeyMetaData keymd = this.mmd.getKeyMetaData();
         if (keymd != null && keymd.getIndexMetaData() != null) {
            IndexMetaData idxmd = this.mmd.getKeyMetaData().getIndexMetaData();
            Index index = TableUtils.getIndexForField(this, idxmd, this.keyMapping);
            if (index != null) {
               indices.add(index);
            }
         } else if (this.keyMapping instanceof PersistableMapping) {
            Index index = TableUtils.getIndexForField(this, (IndexMetaData)null, this.keyMapping);
            if (index != null) {
               indices.add(index);
            }
         }
      }

      if (this.valueMapping instanceof EmbeddedValuePCMapping) {
         EmbeddedValuePCMapping embMapping = (EmbeddedValuePCMapping)this.valueMapping;

         for(int i = 0; i < embMapping.getNumberOfJavaTypeMappings(); ++i) {
            JavaTypeMapping embFieldMapping = embMapping.getJavaTypeMapping(i);
            IndexMetaData imd = embFieldMapping.getMemberMetaData().getIndexMetaData();
            if (imd != null) {
               Index index = TableUtils.getIndexForField(this, imd, embFieldMapping);
               if (index != null) {
                  indices.add(index);
               }
            }
         }
      } else {
         ValueMetaData valmd = this.mmd.getValueMetaData();
         if (valmd != null && valmd.getIndexMetaData() != null) {
            IndexMetaData idxmd = this.mmd.getValueMetaData().getIndexMetaData();
            Index index = TableUtils.getIndexForField(this, idxmd, this.valueMapping);
            if (index != null) {
               indices.add(index);
            }
         } else if (this.valueMapping instanceof PersistableMapping) {
            Index index = TableUtils.getIndexForField(this, (IndexMetaData)null, this.valueMapping);
            if (index != null) {
               indices.add(index);
            }
         }
      }

      return indices;
   }

   protected List getExpectedCandidateKeys() {
      List candidateKeys = super.getExpectedCandidateKeys();
      if (this.keyMapping instanceof EmbeddedKeyPCMapping) {
         EmbeddedKeyPCMapping embMapping = (EmbeddedKeyPCMapping)this.keyMapping;

         for(int i = 0; i < embMapping.getNumberOfJavaTypeMappings(); ++i) {
            JavaTypeMapping embFieldMapping = embMapping.getJavaTypeMapping(i);
            UniqueMetaData umd = embFieldMapping.getMemberMetaData().getUniqueMetaData();
            if (umd != null) {
               CandidateKey ck = TableUtils.getCandidateKeyForField(this, umd, embFieldMapping);
               if (ck != null) {
                  candidateKeys.add(ck);
               }
            }
         }
      } else if (this.mmd.getKeyMetaData() != null) {
         UniqueMetaData unimd = this.mmd.getKeyMetaData().getUniqueMetaData();
         if (unimd != null) {
            CandidateKey ck = TableUtils.getCandidateKeyForField(this, unimd, this.keyMapping);
            if (ck != null) {
               candidateKeys.add(ck);
            }
         }
      }

      if (this.valueMapping instanceof EmbeddedValuePCMapping) {
         EmbeddedValuePCMapping embMapping = (EmbeddedValuePCMapping)this.valueMapping;

         for(int i = 0; i < embMapping.getNumberOfJavaTypeMappings(); ++i) {
            JavaTypeMapping embFieldMapping = embMapping.getJavaTypeMapping(i);
            UniqueMetaData umd = embFieldMapping.getMemberMetaData().getUniqueMetaData();
            if (umd != null) {
               CandidateKey ck = TableUtils.getCandidateKeyForField(this, umd, embFieldMapping);
               if (ck != null) {
                  candidateKeys.add(ck);
               }
            }
         }
      } else if (this.mmd.getValueMetaData() != null) {
         UniqueMetaData unimd = this.mmd.getValueMetaData().getUniqueMetaData();
         if (unimd != null) {
            CandidateKey ck = TableUtils.getCandidateKeyForField(this, unimd, this.valueMapping);
            if (ck != null) {
               candidateKeys.add(ck);
            }
         }
      }

      return candidateKeys;
   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      return null;
   }
}
