package org.datanucleus.store.rdbms.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class PersistableJoinTable extends JoinTable {
   protected JavaTypeMapping relatedMapping;

   public PersistableJoinTable(DatastoreIdentifier tableName, AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr) {
      super(tableName, mmd, storeMgr);
   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      return null;
   }

   public void initialize(ClassLoaderResolver clr) {
      boolean pkRequired = this.requiresPrimaryKey();
      ColumnMetaData[] ownerColmd = null;
      if (this.mmd.getColumnMetaData() != null && this.mmd.getColumnMetaData().length > 0) {
         ownerColmd = this.mmd.getColumnMetaData();
      }

      this.ownerMapping = ColumnCreator.createColumnsForJoinTables(clr.classForName(this.mmd.getClassName(true)), this.mmd, ownerColmd, this.storeMgr, this, pkRequired, false, FieldRole.ROLE_OWNER, clr);
      if (NucleusLogger.DATASTORE.isDebugEnabled()) {
         this.logMapping(this.mmd.getFullFieldName() + ".[OWNER]", this.ownerMapping);
      }

      ColumnMetaData[] relatedColmd = null;
      if (this.mmd.getJoinMetaData().getColumnMetaData() != null && this.mmd.getJoinMetaData().getColumnMetaData().length > 0) {
         relatedColmd = this.mmd.getJoinMetaData().getColumnMetaData();
      }

      this.relatedMapping = ColumnCreator.createColumnsForJoinTables(this.mmd.getType(), this.mmd, relatedColmd, this.storeMgr, this, pkRequired, false, FieldRole.ROLE_PERSISTABLE_RELATION, clr);
      if (NucleusLogger.DATASTORE.isDebugEnabled()) {
         this.logMapping(this.mmd.getFullFieldName() + ".[RELATED]", this.relatedMapping);
      }

      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057023", new Object[]{this}));
      }

      this.storeMgr.registerTableInitialized(this);
      this.state = 2;
   }

   public List getExpectedForeignKeys(ClassLoaderResolver clr) {
      this.assertIsInitialized();
      boolean autoMode = false;
      if (this.storeMgr.getStringProperty("datanucleus.rdbms.constraintCreateMode").equals("DataNucleus")) {
         autoMode = true;
      }

      ArrayList foreignKeys = new ArrayList();

      try {
         DatastoreClass referencedTable = this.storeMgr.getDatastoreClass(this.mmd.getClassName(true), clr);
         if (referencedTable != null) {
            ForeignKey fk = null;
            ForeignKeyMetaData fkmd = null;
            if (this.mmd.getJoinMetaData() != null) {
               fkmd = this.mmd.getJoinMetaData().getForeignKeyMetaData();
            }

            if (fkmd != null || autoMode) {
               fk = new ForeignKey(this.ownerMapping, this.dba, referencedTable, true);
               fk.setForMetaData(fkmd);
            }

            if (fk != null) {
               foreignKeys.add(fk);
            }
         }

         referencedTable = this.storeMgr.getDatastoreClass(this.mmd.getTypeName(), clr);
         if (referencedTable != null) {
            ForeignKey fk = null;
            ForeignKeyMetaData fkmd = this.mmd.getForeignKeyMetaData();
            if (fkmd != null || autoMode) {
               fk = new ForeignKey(this.relatedMapping, this.dba, referencedTable, true);
               fk.setForMetaData(fkmd);
            }

            if (fk != null) {
               foreignKeys.add(fk);
            }
         }
      } catch (NoTableManagedException var7) {
      }

      return foreignKeys;
   }

   protected Set getExpectedIndices(ClassLoaderResolver clr) {
      Set indices = super.getExpectedIndices(clr);
      return indices;
   }

   protected List getExpectedCandidateKeys() {
      List candidateKeys = super.getExpectedCandidateKeys();
      if (this.mmd.getJoinMetaData() != null && this.mmd.getJoinMetaData().getUniqueMetaData() != null) {
         UniqueMetaData unimd = this.mmd.getJoinMetaData().getUniqueMetaData();
         if (unimd.getNumberOfColumns() > 0) {
            String[] columnNames = unimd.getColumnNames();
            CandidateKey uniKey = new CandidateKey(this);
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

   public JavaTypeMapping getRelatedMapping() {
      this.assertIsInitialized();
      return this.relatedMapping;
   }
}
