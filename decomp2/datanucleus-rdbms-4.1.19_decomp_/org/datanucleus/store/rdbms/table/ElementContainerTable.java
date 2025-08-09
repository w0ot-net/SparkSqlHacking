package org.datanucleus.store.rdbms.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ElementMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedElementPCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class ElementContainerTable extends JoinTable {
   protected JavaTypeMapping elementMapping;
   protected JavaTypeMapping orderMapping;
   protected JavaTypeMapping relationDiscriminatorMapping;
   protected String relationDiscriminatorValue;

   public ElementContainerTable(DatastoreIdentifier tableName, AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr) {
      super(tableName, mmd, storeMgr);
   }

   public void initialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
      boolean pkRequired = this.requiresPrimaryKey();
      AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(clr);
      ColumnMetaData[] columnMetaData = null;
      if (this.mmd.getJoinMetaData() != null && this.mmd.getJoinMetaData().getColumnMetaData() != null && this.mmd.getJoinMetaData().getColumnMetaData().length > 0) {
         columnMetaData = this.mmd.getJoinMetaData().getColumnMetaData();
      } else if (relatedMmds != null && relatedMmds[0].getElementMetaData() != null && relatedMmds[0].getElementMetaData().getColumnMetaData() != null && relatedMmds[0].getElementMetaData().getColumnMetaData().length > 0) {
         columnMetaData = relatedMmds[0].getElementMetaData().getColumnMetaData();
      }

      try {
         this.ownerMapping = ColumnCreator.createColumnsForJoinTables(clr.classForName(this.ownerType), this.mmd, columnMetaData, this.storeMgr, this, pkRequired, false, FieldRole.ROLE_OWNER, clr);
      } catch (NoTableManagedException var8) {
         throw new NucleusUserException("Table " + this.toString() + " for member=" + this.mmd.getFullFieldName() + " needs a column to link back to its owner, yet the owner type (" + this.ownerType + ") has no table of its own (embedded?)");
      }

      if (NucleusLogger.DATASTORE.isDebugEnabled()) {
         this.logMapping(this.mmd.getFullFieldName() + ".[OWNER]", this.ownerMapping);
      }

      if (this.mmd.hasExtension("relation-discriminator-column") || this.mmd.hasExtension("relation-discriminator-value")) {
         String colName = this.mmd.getValueForExtension("relation-discriminator-column");
         if (colName == null) {
            colName = "RELATION_DISCRIM";
         }

         ColumnMetaData colmd = new ColumnMetaData();
         colmd.setName(colName);
         boolean relationDiscriminatorPk = false;
         if (this.mmd.hasExtension("relation-discriminator-pk") && this.mmd.getValueForExtension("relation-discriminator-pk").equalsIgnoreCase("true")) {
            relationDiscriminatorPk = true;
         }

         if (!relationDiscriminatorPk) {
            colmd.setAllowsNull(Boolean.TRUE);
         }

         this.relationDiscriminatorMapping = this.storeMgr.getMappingManager().getMapping(String.class);
         ColumnCreator.createIndexColumn(this.relationDiscriminatorMapping, this.storeMgr, clr, this, colmd, relationDiscriminatorPk);
         this.relationDiscriminatorValue = this.mmd.getValueForExtension("relation-discriminator-value");
         if (this.relationDiscriminatorValue == null) {
            this.relationDiscriminatorValue = this.mmd.getFullFieldName();
         }
      }

   }

   public abstract String getElementType();

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
            for(int j = 0; j < this.elementMapping.getNumberOfDatastoreMappings(); ++j) {
               if (this.elementMapping.getDatastoreMapping(j).getColumn().getIdentifier().getName().equals(colName)) {
                  this.elementMapping.getDatastoreMapping(j).getColumn().setPrimaryKey();
                  found = true;
               }
            }
         }

         if (!found) {
            throw new NucleusUserException(Localiser.msg("057040", new Object[]{this.toString(), colName}));
         }
      }

   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      return null;
   }

   public JavaTypeMapping getElementMapping() {
      this.assertIsInitialized();
      return this.elementMapping;
   }

   public JavaTypeMapping getOrderMapping() {
      this.assertIsInitialized();
      return this.orderMapping;
   }

   public JavaTypeMapping getRelationDiscriminatorMapping() {
      this.assertIsInitialized();
      return this.relationDiscriminatorMapping;
   }

   public String getRelationDiscriminatorValue() {
      this.assertIsInitialized();
      return this.relationDiscriminatorValue;
   }

   protected ForeignKey getForeignKeyToOwner(DatastoreClass ownerTable, boolean autoMode) {
      ForeignKey fk = null;
      if (ownerTable != null) {
         ForeignKeyMetaData fkmd = null;
         if (this.mmd.getJoinMetaData() != null) {
            fkmd = this.mmd.getJoinMetaData().getForeignKeyMetaData();
         }

         if (fkmd != null || autoMode) {
            fk = new ForeignKey(this.ownerMapping, this.dba, ownerTable, true);
            fk.setForMetaData(fkmd);
         }
      }

      return fk;
   }

   protected ForeignKey getForeignKeyToElement(DatastoreClass elementTable, boolean autoMode, JavaTypeMapping m) {
      ForeignKey fk = null;
      if (elementTable != null) {
         ForeignKeyMetaData fkmd = this.mmd.getForeignKeyMetaData();
         if (fkmd == null && this.mmd.getElementMetaData() != null) {
            fkmd = this.mmd.getElementMetaData().getForeignKeyMetaData();
         }

         if (fkmd != null || autoMode) {
            fk = new ForeignKey(m, this.dba, elementTable, true);
            fk.setForMetaData(fkmd);
         }
      }

      return fk;
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
            ForeignKey fk = this.getForeignKeyToOwner(referencedTable, autoMode);
            if (fk != null) {
               foreignKeys.add(fk);
            }
         }

         if (!(this.elementMapping instanceof SerialisedPCMapping)) {
            if (this.elementMapping instanceof EmbeddedElementPCMapping) {
               EmbeddedElementPCMapping embMapping = (EmbeddedElementPCMapping)this.elementMapping;

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
            } else if (this.elementMapping instanceof ReferenceMapping) {
               JavaTypeMapping[] implJavaTypeMappings = ((ReferenceMapping)this.elementMapping).getJavaTypeMapping();

               for(int i = 0; i < implJavaTypeMappings.length; ++i) {
                  JavaTypeMapping implMapping = implJavaTypeMappings[i];
                  if (this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(implMapping.getType(), clr) != null && implMapping.getNumberOfDatastoreMappings() > 0) {
                     referencedTable = this.storeMgr.getDatastoreClass(implMapping.getType(), clr);
                     if (referencedTable != null) {
                        ForeignKey fk = this.getForeignKeyToElement(referencedTable, autoMode, implMapping);
                        if (fk != null) {
                           foreignKeys.add(fk);
                        }
                     }
                  }
               }
            } else {
               referencedTable = this.storeMgr.getDatastoreClass(this.getElementType(), clr);
               if (referencedTable != null) {
                  ForeignKey fk = this.getForeignKeyToElement(referencedTable, autoMode, this.elementMapping);
                  if (fk != null) {
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
      this.assertIsInitialized();
      Set<Index> indices = new HashSet();
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

      if (this.elementMapping instanceof EmbeddedElementPCMapping) {
         EmbeddedElementPCMapping embMapping = (EmbeddedElementPCMapping)this.elementMapping;

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
         ElementMetaData elemmd = this.mmd.getElementMetaData();
         if (elemmd != null && elemmd.getIndexMetaData() != null) {
            Index index = TableUtils.getIndexForField(this, elemmd.getIndexMetaData(), this.elementMapping);
            if (index != null) {
               indices.add(index);
            }
         } else if (this.elementMapping instanceof PersistableMapping) {
            Index index = TableUtils.getIndexForField(this, (IndexMetaData)null, this.elementMapping);
            if (index != null) {
               indices.add(index);
            }
         }
      }

      if (this.orderMapping != null && this.mmd.getOrderMetaData() != null && this.mmd.getOrderMetaData().getIndexMetaData() != null) {
         Index index = TableUtils.getIndexForField(this, this.mmd.getOrderMetaData().getIndexMetaData(), this.orderMapping);
         if (index != null) {
            indices.add(index);
         }
      }

      return indices;
   }

   protected List getExpectedCandidateKeys() {
      List candidateKeys = super.getExpectedCandidateKeys();
      if (this.elementMapping instanceof EmbeddedElementPCMapping) {
         EmbeddedElementPCMapping embMapping = (EmbeddedElementPCMapping)this.elementMapping;

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
      }

      if (this.mmd.getJoinMetaData() != null && this.mmd.getJoinMetaData().getUniqueMetaData() != null) {
         UniqueMetaData unimd = this.mmd.getJoinMetaData().getUniqueMetaData();
         if (unimd.getNumberOfColumns() > 0) {
            String[] columnNames = unimd.getColumnNames();
            CandidateKey uniKey = new CandidateKey(this);
            String unimdName = unimd.getName();
            if (!StringUtils.isWhitespace(unimdName)) {
               uniKey.setName(unimdName);
            }

            IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();

            for(String columnName : columnNames) {
               Column col = this.getColumn(idFactory.newColumnIdentifier(columnName));
               if (col == null) {
                  throw new NucleusUserException("Unique key on join-table " + this + " has column " + columnName + " that is not found");
               }

               uniKey.addColumn(col);
            }

            candidateKeys.add(uniKey);
         }
      }

      return candidateKeys;
   }
}
