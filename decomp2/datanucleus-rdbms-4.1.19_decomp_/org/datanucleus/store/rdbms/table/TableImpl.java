package org.datanucleus.store.rdbms.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.MissingColumnException;
import org.datanucleus.store.rdbms.exceptions.MissingTableException;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.exceptions.NotATableException;
import org.datanucleus.store.rdbms.exceptions.UnexpectedColumnException;
import org.datanucleus.store.rdbms.exceptions.WrongPrimaryKeyException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.ForeignKeyInfo;
import org.datanucleus.store.rdbms.schema.IndexInfo;
import org.datanucleus.store.rdbms.schema.PrimaryKeyInfo;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.RDBMSSchemaHandler;
import org.datanucleus.store.rdbms.schema.RDBMSTableFKInfo;
import org.datanucleus.store.rdbms.schema.RDBMSTableIndexInfo;
import org.datanucleus.store.rdbms.schema.RDBMSTablePKInfo;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class TableImpl extends AbstractTable {
   public TableImpl(DatastoreIdentifier name, RDBMSStoreManager storeMgr) {
      super(name, storeMgr);
   }

   public void preInitialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
   }

   public void postInitialize(ClassLoaderResolver clr) {
      this.assertIsInitialized();
   }

   public PrimaryKey getPrimaryKey() {
      PrimaryKey pk = new PrimaryKey(this);

      for(Column col : this.columns) {
         if (col.isPrimaryKey()) {
            pk.addColumn(col);
         }
      }

      return pk;
   }

   public boolean validate(Connection conn, boolean validateColumnStructure, boolean autoCreate, Collection autoCreateErrors) throws SQLException {
      this.assertIsInitialized();
      RDBMSSchemaHandler handler = (RDBMSSchemaHandler)this.storeMgr.getSchemaHandler();
      String tableType = handler.getTableType(conn, this);
      if (tableType == null) {
         throw new MissingTableException(this.getCatalogName(), this.getSchemaName(), this.toString());
      } else if (!tableType.equals("TABLE")) {
         throw new NotATableException(this.toString(), tableType);
      } else {
         long startTime = System.currentTimeMillis();
         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057032", new Object[]{this}));
         }

         this.validateColumns(conn, validateColumnStructure, autoCreate, autoCreateErrors);

         try {
            this.validatePrimaryKey(conn);
         } catch (WrongPrimaryKeyException wpke) {
            if (autoCreateErrors == null) {
               throw wpke;
            }

            autoCreateErrors.add(wpke);
         }

         this.state = 4;
         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("045000", System.currentTimeMillis() - startTime));
         }

         return false;
      }
   }

   public boolean validateColumns(Connection conn, boolean validateColumnStructure, boolean autoCreate, Collection autoCreateErrors) throws SQLException {
      Map<DatastoreIdentifier, Column> unvalidated = new HashMap(this.columnsByIdentifier);

      for(RDBMSColumnInfo ci : this.storeMgr.getColumnInfoForTable(this, conn)) {
         DatastoreIdentifier colIdentifier = this.storeMgr.getIdentifierFactory().newColumnIdentifier(ci.getColumnName(), this.storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(String.class), (FieldRole)null, true);
         Column col = (Column)unvalidated.get(colIdentifier);
         if (col != null) {
            if (validateColumnStructure) {
               col.initializeColumnInfoFromDatastore(ci);
               col.validate(ci);
               unvalidated.remove(colIdentifier);
            } else {
               unvalidated.remove(colIdentifier);
            }
         }
      }

      if (unvalidated.size() > 0) {
         if (!autoCreate) {
            MissingColumnException mce = new MissingColumnException(this, unvalidated.values());
            if (autoCreateErrors == null) {
               throw mce;
            }

            autoCreateErrors.add(mce);
         } else {
            List stmts = new ArrayList();

            for(Map.Entry entry : unvalidated.entrySet()) {
               Column col = (Column)entry.getValue();
               String addColStmt = this.dba.getAddColumnStatement(this, col);
               stmts.add(addColStmt);
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057031", new Object[]{col.getIdentifier(), this.toString()}));
            }

            try {
               this.executeDdlStatementList(stmts, conn);
            } catch (SQLException sqle) {
               if (autoCreateErrors == null) {
                  throw sqle;
               }

               autoCreateErrors.add(sqle);
            }

            this.storeMgr.invalidateColumnInfoForTable(this);
         }
      }

      this.state = 4;
      return true;
   }

   public void initializeColumnInfoForPrimaryKeyColumns(Connection conn) throws SQLException {
      for(Column col : this.columnsByIdentifier.values()) {
         if (col.isPrimaryKey()) {
            RDBMSColumnInfo ci = this.storeMgr.getColumnInfoForColumnName(this, conn, col.getIdentifier());
            if (ci != null) {
               col.initializeColumnInfoFromDatastore(ci);
            }
         }
      }

   }

   public void initializeColumnInfoFromDatastore(Connection conn) throws SQLException {
      Map<DatastoreIdentifier, Column> columns = new HashMap(this.columnsByIdentifier);

      for(RDBMSColumnInfo ci : this.storeMgr.getColumnInfoForTable(this, conn)) {
         DatastoreIdentifier colName = this.storeMgr.getIdentifierFactory().newIdentifier(IdentifierType.COLUMN, ci.getColumnName());
         Column col = (Column)columns.get(colName);
         if (col != null) {
            col.initializeColumnInfoFromDatastore(ci);
         }
      }

   }

   protected boolean validatePrimaryKey(Connection conn) throws SQLException {
      Map actualPKs = this.getExistingPrimaryKeys(conn);
      PrimaryKey expectedPK = this.getPrimaryKey();
      if (expectedPK.size() == 0) {
         if (!actualPKs.isEmpty()) {
            throw new WrongPrimaryKeyException(this.toString(), expectedPK.toString(), StringUtils.collectionToString(actualPKs.values()));
         }
      } else if (actualPKs.size() != 1 || !actualPKs.values().contains(expectedPK)) {
         throw new WrongPrimaryKeyException(this.toString(), expectedPK.toString(), StringUtils.collectionToString(actualPKs.values()));
      }

      return true;
   }

   public boolean validateConstraints(Connection conn, boolean autoCreate, Collection autoCreateErrors, ClassLoaderResolver clr) throws SQLException {
      this.assertIsInitialized();
      boolean cksWereModified;
      boolean fksWereModified;
      boolean idxsWereModified;
      if (this.dba.supportsOption("CreateIndexesBeforeForeignKeys")) {
         idxsWereModified = this.validateIndices(conn, autoCreate, autoCreateErrors, clr);
         fksWereModified = this.validateForeignKeys(conn, autoCreate, autoCreateErrors, clr);
         cksWereModified = this.validateCandidateKeys(conn, autoCreate, autoCreateErrors);
      } else {
         cksWereModified = this.validateCandidateKeys(conn, autoCreate, autoCreateErrors);
         fksWereModified = this.validateForeignKeys(conn, autoCreate, autoCreateErrors, clr);
         idxsWereModified = this.validateIndices(conn, autoCreate, autoCreateErrors, clr);
      }

      return fksWereModified || idxsWereModified || cksWereModified;
   }

   public boolean createConstraints(Connection conn, Collection autoCreateErrors, ClassLoaderResolver clr) throws SQLException {
      this.assertIsInitialized();
      boolean cksWereModified;
      boolean fksWereModified;
      boolean idxsWereModified;
      if (this.dba.supportsOption("CreateIndexesBeforeForeignKeys")) {
         idxsWereModified = this.createIndices(conn, autoCreateErrors, clr, Collections.EMPTY_MAP);
         fksWereModified = this.createForeignKeys(conn, autoCreateErrors, clr, Collections.EMPTY_MAP);
         cksWereModified = this.createCandidateKeys(conn, autoCreateErrors, Collections.EMPTY_MAP);
      } else {
         cksWereModified = this.createCandidateKeys(conn, autoCreateErrors, Collections.EMPTY_MAP);
         fksWereModified = this.createForeignKeys(conn, autoCreateErrors, clr, Collections.EMPTY_MAP);
         idxsWereModified = this.createIndices(conn, autoCreateErrors, clr, Collections.EMPTY_MAP);
      }

      return fksWereModified || idxsWereModified || cksWereModified;
   }

   private boolean validateForeignKeys(Connection conn, boolean autoCreate, Collection autoCreateErrors, ClassLoaderResolver clr) throws SQLException {
      boolean dbWasModified = false;
      Map actualForeignKeysByName = null;
      int numActualFKs = 0;
      if (this.storeMgr.getCompleteDDL()) {
         actualForeignKeysByName = new HashMap();
      } else {
         actualForeignKeysByName = this.getExistingForeignKeys(conn);
         numActualFKs = actualForeignKeysByName.size();
         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058103", new Object[]{"" + numActualFKs, this}));
         }
      }

      if (autoCreate) {
         dbWasModified = this.createForeignKeys(conn, autoCreateErrors, clr, actualForeignKeysByName);
      } else {
         Map stmtsByFKName = this.getSQLAddFKStatements(actualForeignKeysByName, clr);
         if (stmtsByFKName.isEmpty()) {
            if (numActualFKs > 0 && NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058104", new Object[]{"" + numActualFKs, this}));
            }
         } else {
            NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058101", new Object[]{this, stmtsByFKName.values()}));
         }
      }

      return dbWasModified;
   }

   private boolean createForeignKeys(Connection conn, Collection autoCreateErrors, ClassLoaderResolver clr, Map actualForeignKeysByName) throws SQLException {
      Map stmtsByFKName = this.getSQLAddFKStatements(actualForeignKeysByName, clr);
      Statement stmt = conn.createStatement();

      try {
         for(Map.Entry e : stmtsByFKName.entrySet()) {
            String fkName = (String)e.getKey();
            String stmtText = (String)e.getValue();
            if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058100", new Object[]{fkName, this.getCatalogName(), this.getSchemaName()}));
            }

            try {
               this.executeDdlStatement(stmt, stmtText);
            } catch (SQLException sqle) {
               if (autoCreateErrors == null) {
                  throw sqle;
               }

               autoCreateErrors.add(sqle);
            }
         }
      } finally {
         stmt.close();
      }

      return !stmtsByFKName.isEmpty();
   }

   private boolean validateIndices(Connection conn, boolean autoCreate, Collection autoCreateErrors, ClassLoaderResolver clr) throws SQLException {
      boolean dbWasModified = false;
      Map actualIndicesByName = null;
      int numActualIdxs = 0;
      Object var11;
      if (this.storeMgr.getCompleteDDL()) {
         var11 = new HashMap();
      } else {
         var11 = this.getExistingIndices(conn);

         for(Map.Entry entry : ((Map)var11).entrySet()) {
            Index idx = (Index)entry.getValue();
            if (idx.getTable().getIdentifier().toString().equals(this.identifier.toString())) {
               ++numActualIdxs;
            }
         }

         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058004", new Object[]{"" + numActualIdxs, this}));
         }
      }

      if (autoCreate) {
         dbWasModified = this.createIndices(conn, autoCreateErrors, clr, (Map)var11);
      } else {
         Map stmtsByIdxName = this.getSQLCreateIndexStatements((Map)var11, clr);
         if (stmtsByIdxName.isEmpty()) {
            if (numActualIdxs > 0 && NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058005", new Object[]{"" + numActualIdxs, this}));
            }
         } else {
            NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058003", new Object[]{this, stmtsByIdxName.values()}));
         }
      }

      return dbWasModified;
   }

   private boolean createIndices(Connection conn, Collection autoCreateErrors, ClassLoaderResolver clr, Map actualIndicesByName) throws SQLException {
      Map stmtsByIdxName = this.getSQLCreateIndexStatements(actualIndicesByName, clr);
      Statement stmt = conn.createStatement();

      try {
         for(Map.Entry e : stmtsByIdxName.entrySet()) {
            String idxName = (String)e.getKey();
            String stmtText = (String)e.getValue();
            if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058000", new Object[]{idxName, this.getCatalogName(), this.getSchemaName()}));
            }

            try {
               this.executeDdlStatement(stmt, stmtText);
            } catch (SQLException sqle) {
               if (autoCreateErrors == null) {
                  throw sqle;
               }

               autoCreateErrors.add(sqle);
            }
         }
      } finally {
         stmt.close();
      }

      return !stmtsByIdxName.isEmpty();
   }

   private boolean validateCandidateKeys(Connection conn, boolean autoCreate, Collection autoCreateErrors) throws SQLException {
      boolean dbWasModified = false;
      Map actualCandidateKeysByName = null;
      int numActualCKs = 0;
      if (this.storeMgr.getCompleteDDL()) {
         actualCandidateKeysByName = new HashMap();
      } else {
         actualCandidateKeysByName = this.getExistingCandidateKeys(conn);
         numActualCKs = actualCandidateKeysByName.size();
         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058204", new Object[]{"" + numActualCKs, this}));
         }
      }

      if (autoCreate) {
         dbWasModified = this.createCandidateKeys(conn, autoCreateErrors, actualCandidateKeysByName);
      } else {
         Map stmtsByCKName = this.getSQLAddCandidateKeyStatements(actualCandidateKeysByName);
         if (stmtsByCKName.isEmpty()) {
            if (numActualCKs > 0 && NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058205", new Object[]{"" + numActualCKs, this}));
            }
         } else {
            NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("058201", new Object[]{this, stmtsByCKName.values()}));
         }
      }

      return dbWasModified;
   }

   private boolean createCandidateKeys(Connection conn, Collection autoCreateErrors, Map actualCandidateKeysByName) throws SQLException {
      Map stmtsByCKName = this.getSQLAddCandidateKeyStatements(actualCandidateKeysByName);
      Statement stmt = conn.createStatement();

      try {
         for(Map.Entry e : stmtsByCKName.entrySet()) {
            String ckName = (String)e.getKey();
            String stmtText = (String)e.getValue();
            if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058200", new Object[]{ckName, this.getCatalogName(), this.getSchemaName()}));
            }

            try {
               this.executeDdlStatement(stmt, stmtText);
            } catch (SQLException sqle) {
               if (autoCreateErrors == null) {
                  throw sqle;
               }

               autoCreateErrors.add(sqle);
            }
         }
      } finally {
         stmt.close();
      }

      return !stmtsByCKName.isEmpty();
   }

   public void dropConstraints(Connection conn) throws SQLException {
      this.assertIsInitialized();
      boolean drop_using_constraint = this.dba.supportsOption("AlterTableDropConstraint_Syntax");
      boolean drop_using_foreign_key = this.dba.supportsOption("AlterTableDropForeignKey_Syntax");
      if (drop_using_constraint || drop_using_foreign_key) {
         HashSet fkNames = new HashSet();
         StoreSchemaHandler handler = this.storeMgr.getSchemaHandler();
         RDBMSTableFKInfo fkInfo = (RDBMSTableFKInfo)handler.getSchemaData(conn, "foreign-keys", new Object[]{this});

         for(ForeignKeyInfo fki : fkInfo.getChildren()) {
            String fkName = (String)fki.getProperty("fk_name");
            if (fkName != null) {
               fkNames.add(fkName);
            }
         }

         int numFKs = fkNames.size();
         if (numFKs > 0) {
            if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
               NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("058102", new Object[]{"" + numFKs, this}));
            }

            Iterator var16 = fkNames.iterator();
            IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
            Statement stmt = conn.createStatement();

            String stmtText;
            try {
               for(; var16.hasNext(); this.executeDdlStatement(stmt, stmtText)) {
                  String constraintName = (String)var16.next();
                  stmtText = null;
                  if (drop_using_constraint) {
                     stmtText = "ALTER TABLE " + this.toString() + " DROP CONSTRAINT " + idFactory.getIdentifierInAdapterCase(constraintName);
                  } else {
                     stmtText = "ALTER TABLE " + this.toString() + " DROP FOREIGN KEY " + idFactory.getIdentifierInAdapterCase(constraintName);
                  }
               }
            } finally {
               stmt.close();
            }
         }

      }
   }

   public List getExpectedForeignKeys(ClassLoaderResolver clr) {
      this.assertIsInitialized();
      Set colsInFKs = new HashSet();
      ArrayList foreignKeys = new ArrayList();

      for(Column col : this.columns) {
         if (!colsInFKs.contains(col)) {
            try {
               DatastoreClass referencedTable = this.storeMgr.getDatastoreClass(col.getStoredJavaType(), clr);
               if (referencedTable != null) {
                  for(int j = 0; j < col.getJavaTypeMapping().getNumberOfDatastoreMappings(); ++j) {
                     colsInFKs.add(col.getJavaTypeMapping().getDatastoreMapping(j).getColumn());
                  }

                  ForeignKey fk = new ForeignKey(col.getJavaTypeMapping(), this.dba, referencedTable, true);
                  foreignKeys.add(fk);
               }
            } catch (NoTableManagedException var8) {
            }
         }
      }

      return foreignKeys;
   }

   protected List getExpectedCandidateKeys() {
      this.assertIsInitialized();
      return new ArrayList();
   }

   protected Set getExpectedIndices(ClassLoaderResolver clr) {
      this.assertIsInitialized();
      Set<Index> indices = new HashSet();
      PrimaryKey pk = this.getPrimaryKey();

      for(ForeignKey fk : this.getExpectedForeignKeys(clr)) {
         if (!pk.getColumnList().equals(fk.getColumnList())) {
            indices.add(new Index(fk));
         }
      }

      return indices;
   }

   private Map getExistingPrimaryKeys(Connection conn) throws SQLException {
      Map<DatastoreIdentifier, PrimaryKey> primaryKeysByName = new HashMap();
      if (this.tableExistsInDatastore(conn)) {
         StoreSchemaHandler handler = this.storeMgr.getSchemaHandler();
         RDBMSTablePKInfo tablePkInfo = (RDBMSTablePKInfo)handler.getSchemaData(conn, "primary-keys", new Object[]{this});
         IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();

         for(PrimaryKeyInfo pkInfo : tablePkInfo.getChildren()) {
            String pkName = (String)pkInfo.getProperty("pk_name");
            DatastoreIdentifier pkIdentifier;
            if (pkName == null) {
               pkIdentifier = idFactory.newPrimaryKeyIdentifier(this);
            } else {
               pkIdentifier = idFactory.newIdentifier(IdentifierType.COLUMN, pkName);
            }

            PrimaryKey pk = (PrimaryKey)primaryKeysByName.get(pkIdentifier);
            if (pk == null) {
               pk = new PrimaryKey(this);
               pk.setName(pkIdentifier.getName());
               primaryKeysByName.put(pkIdentifier, pk);
            }

            int keySeq = (Short)pkInfo.getProperty("key_seq") - 1;
            String colName = (String)pkInfo.getProperty("column_name");
            DatastoreIdentifier colIdentifier = idFactory.newIdentifier(IdentifierType.COLUMN, colName);
            Column col = (Column)this.columnsByIdentifier.get(colIdentifier);
            if (col == null) {
               throw new UnexpectedColumnException(this.toString(), colIdentifier.getName(), this.getSchemaName(), this.getCatalogName());
            }

            pk.setColumn(keySeq, col);
         }
      }

      return primaryKeysByName;
   }

   private Map getExistingForeignKeys(Connection conn) throws SQLException {
      HashMap foreignKeysByName = new HashMap();
      if (this.tableExistsInDatastore(conn)) {
         StoreSchemaHandler handler = this.storeMgr.getSchemaHandler();
         IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
         RDBMSTableFKInfo tableFkInfo = (RDBMSTableFKInfo)handler.getSchemaData(conn, "foreign-keys", new Object[]{this});

         for(ForeignKeyInfo fkInfo : tableFkInfo.getChildren()) {
            String fkName = (String)fkInfo.getProperty("fk_name");
            DatastoreIdentifier fkIdentifier;
            if (fkName == null) {
               fkIdentifier = idFactory.newForeignKeyIdentifier(this, foreignKeysByName.size());
            } else {
               fkIdentifier = idFactory.newIdentifier(IdentifierType.FOREIGN_KEY, fkName);
            }

            short deferrability = (Short)fkInfo.getProperty("deferrability");
            boolean initiallyDeferred = deferrability == 5;
            ForeignKey fk = (ForeignKey)foreignKeysByName.get(fkIdentifier);
            if (fk == null) {
               fk = new ForeignKey(initiallyDeferred);
               fk.setName(fkIdentifier.getName());
               foreignKeysByName.put(fkIdentifier, fk);
            }

            String pkTableName = (String)fkInfo.getProperty("pk_table_name");
            DatastoreClass refTable = this.storeMgr.getDatastoreClass(idFactory.newTableIdentifier(pkTableName));
            if (refTable != null) {
               String fkColumnName = (String)fkInfo.getProperty("fk_column_name");
               String pkColumnName = (String)fkInfo.getProperty("pk_column_name");
               DatastoreIdentifier colName = idFactory.newIdentifier(IdentifierType.COLUMN, fkColumnName);
               DatastoreIdentifier refColName = idFactory.newIdentifier(IdentifierType.COLUMN, pkColumnName);
               Column col = (Column)this.columnsByIdentifier.get(colName);
               Column refCol = refTable.getColumn(refColName);
               if (col != null && refCol != null) {
                  fk.addColumn(col, refCol);
               }
            }
         }
      }

      return foreignKeysByName;
   }

   private Map getExistingCandidateKeys(Connection conn) throws SQLException {
      HashMap candidateKeysByName = new HashMap();
      if (this.tableExistsInDatastore(conn)) {
         StoreSchemaHandler handler = this.storeMgr.getSchemaHandler();
         RDBMSTableIndexInfo tableIndexInfo = (RDBMSTableIndexInfo)handler.getSchemaData(conn, "indices", new Object[]{this});
         IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();

         for(IndexInfo indexInfo : tableIndexInfo.getChildren()) {
            boolean isUnique = !(Boolean)indexInfo.getProperty("non_unique");
            if (isUnique) {
               short idxType = (Short)indexInfo.getProperty("type");
               if (idxType != 0) {
                  String keyName = (String)indexInfo.getProperty("index_name");
                  DatastoreIdentifier idxName = idFactory.newIdentifier(IdentifierType.CANDIDATE_KEY, keyName);
                  CandidateKey key = (CandidateKey)candidateKeysByName.get(idxName);
                  if (key == null) {
                     key = new CandidateKey(this);
                     key.setName(keyName);
                     candidateKeysByName.put(idxName, key);
                  }

                  int colSeq = (Short)indexInfo.getProperty("ordinal_position") - 1;
                  DatastoreIdentifier colName = idFactory.newIdentifier(IdentifierType.COLUMN, (String)indexInfo.getProperty("column_name"));
                  Column col = (Column)this.columnsByIdentifier.get(colName);
                  if (col != null) {
                     key.setColumn(colSeq, col);
                  }
               }
            }
         }
      }

      return candidateKeysByName;
   }

   private Map getExistingIndices(Connection conn) throws SQLException {
      HashMap indicesByName = new HashMap();
      if (this.tableExistsInDatastore(conn)) {
         StoreSchemaHandler handler = this.storeMgr.getSchemaHandler();
         RDBMSTableIndexInfo tableIndexInfo = (RDBMSTableIndexInfo)handler.getSchemaData(conn, "indices", new Object[]{this});
         IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();

         for(IndexInfo indexInfo : tableIndexInfo.getChildren()) {
            short idxType = (Short)indexInfo.getProperty("type");
            if (idxType != 0) {
               String indexName = (String)indexInfo.getProperty("index_name");
               DatastoreIdentifier indexIdentifier = idFactory.newIdentifier(IdentifierType.CANDIDATE_KEY, indexName);
               Index idx = (Index)indicesByName.get(indexIdentifier);
               if (idx == null) {
                  boolean isUnique = !(Boolean)indexInfo.getProperty("non_unique");
                  idx = new Index(this, isUnique, (String)null);
                  idx.setName(indexName);
                  indicesByName.put(indexIdentifier, idx);
               }

               int colSeq = (Short)indexInfo.getProperty("ordinal_position") - 1;
               DatastoreIdentifier colName = idFactory.newIdentifier(IdentifierType.COLUMN, (String)indexInfo.getProperty("column_name"));
               Column col = (Column)this.columnsByIdentifier.get(colName);
               if (col != null) {
                  idx.setColumn(colSeq, col);
               }
            }
         }
      }

      return indicesByName;
   }

   protected List getSQLCreateStatements(Properties props) {
      this.assertIsInitialized();
      Column[] cols = null;

      for(Column col : this.columns) {
         ColumnMetaData colmd = col.getColumnMetaData();
         Integer colPos = colmd != null ? colmd.getPosition() : null;
         if (colPos != null) {
            int index = colPos;
            if (index < this.columns.size() && index >= 0) {
               if (cols == null) {
                  cols = new Column[this.columns.size()];
               }

               if (cols[index] != null) {
                  throw new NucleusUserException("Column index " + index + " has been specified multiple times : " + cols[index] + " and " + col);
               }

               cols[index] = col;
            }
         }
      }

      if (cols != null) {
         for(Column col : this.columns) {
            ColumnMetaData colmd = col.getColumnMetaData();
            Integer colPos = colmd != null ? colmd.getPosition() : null;
            if (colPos == null) {
               for(int i = 0; i < cols.length; ++i) {
                  if (cols[i] == null) {
                     cols[i] = col;
                  }
               }
            }
         }
      } else {
         cols = (Column[])this.columns.toArray(new Column[this.columns.size()]);
      }

      ArrayList stmts = new ArrayList();
      stmts.add(this.dba.getCreateTableStatement(this, cols, props, this.storeMgr.getIdentifierFactory()));
      PrimaryKey pk = this.getPrimaryKey();
      if (pk.size() > 0) {
         String pkStmt = this.dba.getAddPrimaryKeyStatement(pk, this.storeMgr.getIdentifierFactory());
         if (pkStmt != null) {
            stmts.add(pkStmt);
         }
      }

      return stmts;
   }

   protected Map getSQLAddFKStatements(Map actualForeignKeysByName, ClassLoaderResolver clr) {
      this.assertIsInitialized();
      HashMap stmtsByFKName = new HashMap();
      List<ForeignKey> expectedForeignKeys = this.getExpectedForeignKeys(clr);
      Iterator<ForeignKey> i = expectedForeignKeys.iterator();
      int n = 1;
      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();

      while(i.hasNext()) {
         ForeignKey fk = (ForeignKey)i.next();
         if (!actualForeignKeysByName.containsValue(fk)) {
            if (fk.getName() == null) {
               DatastoreIdentifier fkName;
               do {
                  fkName = idFactory.newForeignKeyIdentifier(this, n++);
               } while(actualForeignKeysByName.containsKey(fkName));

               fk.setName(fkName.getName());
            }

            String stmtText = this.dba.getAddForeignKeyStatement(fk, idFactory);
            if (stmtText != null) {
               stmtsByFKName.put(fk.getName(), stmtText);
            }
         }
      }

      return stmtsByFKName;
   }

   protected Map getSQLAddCandidateKeyStatements(Map actualCandidateKeysByName) {
      this.assertIsInitialized();
      HashMap stmtsByCKName = new HashMap();
      List<CandidateKey> expectedCandidateKeys = this.getExpectedCandidateKeys();
      Iterator<CandidateKey> i = expectedCandidateKeys.iterator();
      int n = 1;
      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();

      while(i.hasNext()) {
         CandidateKey ck = (CandidateKey)i.next();
         if (!actualCandidateKeysByName.containsValue(ck)) {
            if (ck.getName() == null) {
               DatastoreIdentifier ckName;
               do {
                  ckName = idFactory.newCandidateKeyIdentifier(this, n++);
               } while(actualCandidateKeysByName.containsKey(ckName));

               ck.setName(ckName.getName());
            }

            String stmtText = this.dba.getAddCandidateKeyStatement(ck, idFactory);
            if (stmtText != null) {
               stmtsByCKName.put(ck.getName(), stmtText);
            }
         }
      }

      return stmtsByCKName;
   }

   private boolean isIndexReallyNeeded(Index requiredIdx, Collection actualIndices) {
      Iterator i = actualIndices.iterator();
      if (requiredIdx.getName() != null) {
         IdentifierFactory idFactory = requiredIdx.getTable().getStoreManager().getIdentifierFactory();
         String reqdName = idFactory.getIdentifierInAdapterCase(requiredIdx.getName());

         while(i.hasNext()) {
            Index actualIdx = (Index)i.next();
            String actualName = idFactory.getIdentifierInAdapterCase(actualIdx.getName());
            if (actualName.equals(reqdName) && actualIdx.getTable().getIdentifier().toString().equals(requiredIdx.getTable().getIdentifier().toString())) {
               return false;
            }
         }
      } else {
         while(i.hasNext()) {
            Index actualIdx = (Index)i.next();
            if (actualIdx.toString().equals(requiredIdx.toString()) && actualIdx.getTable().getIdentifier().toString().equals(requiredIdx.getTable().getIdentifier().toString())) {
               return false;
            }
         }
      }

      return true;
   }

   protected Map getSQLCreateIndexStatements(Map actualIndicesByName, ClassLoaderResolver clr) {
      this.assertIsInitialized();
      HashMap stmtsByIdxName = new HashMap();
      Set<Index> expectedIndices = this.getExpectedIndices(clr);
      int n = 1;
      Iterator<Index> indexIter = expectedIndices.iterator();
      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();

      while(indexIter.hasNext()) {
         Index idx = (Index)indexIter.next();
         if (this.isIndexReallyNeeded(idx, actualIndicesByName.values())) {
            DatastoreIdentifier idxName;
            if (idx.getName() == null) {
               do {
                  idxName = idFactory.newIndexIdentifier(this, idx.getUnique(), n++);
                  idx.setName(idxName.getName());
               } while(actualIndicesByName.containsKey(idxName));
            }

            String stmtText = this.dba.getCreateIndexStatement(idx, idFactory);
            stmtsByIdxName.put(idx.getName(), stmtText);
         }
      }

      return stmtsByIdxName;
   }

   protected List getSQLDropStatements() {
      this.assertIsInitialized();
      ArrayList stmts = new ArrayList();
      stmts.add(this.dba.getDropTableStatement(this));
      return stmts;
   }

   protected void logMapping(String memberName, JavaTypeMapping mapping) {
      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         StringBuilder columnsStr = new StringBuilder();

         for(int i = 0; i < mapping.getNumberOfDatastoreMappings(); ++i) {
            if (i > 0) {
               columnsStr.append(",");
            }

            columnsStr.append(mapping.getDatastoreMapping(i).getColumn());
         }

         if (mapping.getNumberOfDatastoreMappings() == 0) {
            columnsStr.append("[none]");
         }

         StringBuilder datastoreMappingTypes = new StringBuilder();

         for(int i = 0; i < mapping.getNumberOfDatastoreMappings(); ++i) {
            if (i > 0) {
               datastoreMappingTypes.append(',');
            }

            datastoreMappingTypes.append(mapping.getDatastoreMapping(i).getClass().getName());
         }

         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057010", new Object[]{memberName, columnsStr.toString(), mapping.getClass().getName(), datastoreMappingTypes.toString()}));
      }

   }
}
