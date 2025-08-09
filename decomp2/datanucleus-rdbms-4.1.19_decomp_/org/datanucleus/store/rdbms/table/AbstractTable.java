package org.datanucleus.store.rdbms.table;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.exceptions.DuplicateColumnException;
import org.datanucleus.store.rdbms.exceptions.MissingTableException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.RDBMSSchemaHandler;
import org.datanucleus.store.rdbms.schema.RDBMSSchemaInfo;
import org.datanucleus.store.schema.table.MemberColumnMapping;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractTable implements Table {
   protected static final int TABLE_STATE_NEW = 0;
   protected static final int TABLE_STATE_PK_INITIALIZED = 1;
   protected static final int TABLE_STATE_INITIALIZED = 2;
   protected static final int TABLE_STATE_INITIALIZED_MODIFIED = 3;
   protected static final int TABLE_STATE_VALIDATED = 4;
   protected final RDBMSStoreManager storeMgr;
   protected final DatastoreAdapter dba;
   protected final DatastoreIdentifier identifier;
   protected int state = 0;
   protected List columns = new ArrayList();
   protected Map columnsByIdentifier = new HashMap();
   private String fullyQualifiedName;
   private final int hashCode;
   protected Boolean existsInDatastore = null;

   public AbstractTable(DatastoreIdentifier identifier, RDBMSStoreManager storeMgr) {
      this.storeMgr = storeMgr;
      this.dba = storeMgr.getDatastoreAdapter();
      this.identifier = identifier;
      this.hashCode = identifier.hashCode() ^ storeMgr.hashCode();
   }

   public boolean isInitialized() {
      return this.state >= 2;
   }

   public boolean isPKInitialized() {
      return this.state >= 1;
   }

   public boolean isValidated() {
      return this.state == 4;
   }

   public boolean isInitializedModified() {
      return this.state == 3;
   }

   public RDBMSStoreManager getStoreManager() {
      return this.storeMgr;
   }

   public String getName() {
      return this.identifier.toString();
   }

   public String getCatalogName() {
      return this.identifier.getCatalogName();
   }

   public String getSchemaName() {
      return this.identifier.getSchemaName();
   }

   public DatastoreIdentifier getIdentifier() {
      return this.identifier;
   }

   public AbstractClassMetaData getClassMetaData() {
      return null;
   }

   public int getNumberOfColumns() {
      return this.columns.size();
   }

   public List getColumns() {
      return this.columns;
   }

   public org.datanucleus.store.schema.table.Column getColumnForPosition(int pos) {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public org.datanucleus.store.schema.table.Column getDatastoreIdColumn() {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public org.datanucleus.store.schema.table.Column getVersionColumn() {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public org.datanucleus.store.schema.table.Column getDiscriminatorColumn() {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public org.datanucleus.store.schema.table.Column getMultitenancyColumn() {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public org.datanucleus.store.schema.table.Column getColumnForName(String name) {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public MemberColumnMapping getMemberColumnMappingForMember(AbstractMemberMetaData mmd) {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public MemberColumnMapping getMemberColumnMappingForEmbeddedMember(List mmds) {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public Set getMemberColumnMappings() {
      throw new UnsupportedOperationException("Not supported on this table");
   }

   public DiscriminatorMetaData getDiscriminatorMetaData() {
      return null;
   }

   public JavaTypeMapping getDiscriminatorMapping(boolean allowSuperclasses) {
      return null;
   }

   public JavaTypeMapping getMultitenancyMapping() {
      return null;
   }

   public VersionMetaData getVersionMetaData() {
      return null;
   }

   public JavaTypeMapping getVersionMapping(boolean allowSuperclasses) {
      return null;
   }

   public synchronized Column addColumn(String storedJavaType, DatastoreIdentifier name, JavaTypeMapping mapping, ColumnMetaData colmd) {
      boolean duplicateName = false;
      if (this.hasColumnName(name)) {
         duplicateName = true;
      }

      Column col = new ColumnImpl(this, storedJavaType, name, colmd);
      if (duplicateName && colmd != null) {
         Column existingCol = (Column)this.columnsByIdentifier.get(name);

         MetaData md;
         for(md = existingCol.getColumnMetaData().getParent(); !(md instanceof AbstractClassMetaData); md = md.getParent()) {
            if (md == null) {
               throw new NucleusUserException(Localiser.msg("057043", new Object[]{name.getName(), this.getDatastoreIdentifierFullyQualified(), colmd.toString()}));
            }
         }

         MetaData dupMd = colmd.getParent();

         while(!(dupMd instanceof AbstractClassMetaData)) {
            dupMd = dupMd.getParent();
            if (dupMd == null) {
               throw new NucleusUserException(Localiser.msg("057044", new Object[]{name.getName(), this.getDatastoreIdentifierFullyQualified(), colmd.toString()}));
            }
         }

         boolean reuseColumns = this.storeMgr.getBooleanProperty("datanucleus.rdbms.allowColumnReuse");
         if (!reuseColumns) {
            if (((AbstractClassMetaData)md).getFullClassName().equals(((AbstractClassMetaData)dupMd).getFullClassName())) {
               throw new DuplicateColumnException(this.toString(), existingCol, col);
            }

            if (mapping != null && !mapping.getClass().isAssignableFrom(existingCol.getJavaTypeMapping().getClass()) && !existingCol.getJavaTypeMapping().getClass().isAssignableFrom(mapping.getClass())) {
               throw new DuplicateColumnException(this.toString(), existingCol, col);
            }
         } else if (mapping != null && mapping.getMemberMetaData() != null) {
            NucleusLogger.DATASTORE_SCHEMA.warn("Column " + existingCol + " has already been defined but needing to reuse it for " + mapping.getMemberMetaData().getFullFieldName());
         } else {
            NucleusLogger.DATASTORE_SCHEMA.warn("Column " + existingCol + " has already been defined but needing to reuse it");
         }

         Class fieldStoredJavaTypeClass = null;
         Class existingColStoredJavaTypeClass = null;

         try {
            ClassLoaderResolver clr = this.storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
            fieldStoredJavaTypeClass = clr.classForName(storedJavaType);
            existingColStoredJavaTypeClass = clr.classForName(col.getStoredJavaType());
         } catch (RuntimeException var14) {
         }

         if (fieldStoredJavaTypeClass != null && existingColStoredJavaTypeClass != null && !fieldStoredJavaTypeClass.isAssignableFrom(existingColStoredJavaTypeClass) && !existingColStoredJavaTypeClass.isAssignableFrom(fieldStoredJavaTypeClass)) {
            throw new DuplicateColumnException(this.toString(), existingCol, col);
         }
      }

      if (!duplicateName) {
         this.addColumnInternal(col);
      }

      if (this.isInitialized()) {
         this.state = 3;
      }

      return col;
   }

   public boolean hasColumn(DatastoreIdentifier identifier) {
      return this.hasColumnName(identifier);
   }

   public Column getColumn(DatastoreIdentifier identifier) {
      return (Column)this.columnsByIdentifier.get(identifier);
   }

   public boolean create(Connection conn) throws SQLException {
      this.assertIsInitialized();
      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057029", new Object[]{this}));
      }

      if (this.storeMgr.getSchemaHandler().isAutoCreateSchema() && (this.identifier.getSchemaName() != null || this.identifier.getCatalogName() != null)) {
         RDBMSSchemaInfo info = (RDBMSSchemaInfo)this.storeMgr.getSchemaHandler().getSchemaData(conn, "schema", new Object[]{this.getSchemaName(), this.getCatalogName()});
         NucleusLogger.DATASTORE_SCHEMA.debug("Check of existence of catalog=" + this.identifier.getCatalogName() + " schema=" + this.identifier.getSchemaName() + " returned " + (info != null));
         if (info == null) {
            this.storeMgr.getSchemaHandler().createSchema(this.identifier.getSchemaName(), (Properties)null, conn);
         }
      }

      List createStmts = this.getSQLCreateStatements((Properties)null);
      this.executeDdlStatementList(createStmts, conn);
      return !createStmts.isEmpty();
   }

   public void drop(Connection conn) throws SQLException {
      this.assertIsInitialized();
      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057030", new Object[]{this}));
      }

      this.executeDdlStatementList(this.getSQLDropStatements(), conn);
   }

   public boolean exists(Connection conn, boolean auto_create) throws SQLException {
      this.assertIsInitialized();
      String type = ((RDBMSSchemaHandler)this.storeMgr.getSchemaHandler()).getTableType(conn, this);
      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         if (type == null) {
            NucleusLogger.DATASTORE_SCHEMA.debug("Check of existence of " + this + " returned no table");
         } else {
            NucleusLogger.DATASTORE_SCHEMA.debug("Check of existence of " + this + " returned table type of " + type);
         }
      }

      if (type == null || this.allowDDLOutput() && this.storeMgr.getDdlWriter() != null && this.storeMgr.getCompleteDDL()) {
         if (!auto_create) {
            this.existsInDatastore = Boolean.FALSE;
            throw new MissingTableException(this.getCatalogName(), this.getSchemaName(), this.toString());
         } else {
            boolean created = this.create(conn);
            String tableType = ((RDBMSSchemaHandler)this.storeMgr.getSchemaHandler()).getTableType(conn, this);
            if (this.storeMgr.getDdlWriter() == null || tableType != null) {
               this.existsInDatastore = Boolean.TRUE;
            }

            this.state = 4;
            return created;
         }
      } else {
         this.existsInDatastore = Boolean.TRUE;
         return false;
      }
   }

   public final boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof AbstractTable)) {
         return false;
      } else {
         AbstractTable t = (AbstractTable)obj;
         return this.getClass().equals(t.getClass()) && this.identifier.equals(t.identifier) && this.storeMgr.equals(t.storeMgr);
      }
   }

   public final int hashCode() {
      return this.hashCode;
   }

   public final String toString() {
      if (this.fullyQualifiedName != null) {
         return this.fullyQualifiedName;
      } else {
         this.fullyQualifiedName = this.identifier.getFullyQualifiedName(false);
         return this.fullyQualifiedName;
      }
   }

   public DatastoreIdentifier getDatastoreIdentifierFullyQualified() {
      String catalog = this.identifier.getCatalogName();
      if (catalog != null) {
         catalog = catalog.replace(this.dba.getIdentifierQuoteString(), "");
      }

      String schema = this.identifier.getSchemaName();
      if (schema != null) {
         schema = schema.replace(this.dba.getIdentifierQuoteString(), "");
      }

      String table = this.identifier.getName();
      table = table.replace(this.dba.getIdentifierQuoteString(), "");
      DatastoreIdentifier di = this.storeMgr.getIdentifierFactory().newTableIdentifier(table);
      di.setCatalogName(catalog);
      di.setSchemaName(schema);
      return di;
   }

   protected synchronized void addColumnInternal(Column col) {
      DatastoreIdentifier colName = col.getIdentifier();
      this.columns.add(col);
      this.columnsByIdentifier.put(colName, col);
      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057034", new Object[]{col}));
      }

   }

   protected boolean hasColumnName(DatastoreIdentifier colName) {
      return this.columnsByIdentifier.get(colName) != null;
   }

   protected abstract List getSQLCreateStatements(Properties var1);

   protected abstract List getSQLDropStatements();

   protected void assertIsPKUninitialized() {
      if (this.isPKInitialized()) {
         throw new IllegalStateException(Localiser.msg("057000", new Object[]{this}));
      }
   }

   protected void assertIsUninitialized() {
      if (this.isInitialized()) {
         throw new IllegalStateException(Localiser.msg("057000", new Object[]{this}));
      }
   }

   protected void assertIsInitialized() {
      if (!this.isInitialized()) {
         throw new IllegalStateException(Localiser.msg("057001", new Object[]{this}));
      }
   }

   protected void assertIsInitializedModified() {
      if (!this.isInitializedModified()) {
         throw new IllegalStateException(Localiser.msg("RDBMS.Table.UnmodifiedError", new Object[]{this}));
      }
   }

   protected void assertIsPKInitialized() {
      if (!this.isPKInitialized()) {
         throw new IllegalStateException(Localiser.msg("057001", new Object[]{this}));
      }
   }

   protected void assertIsValidated() {
      if (!this.isValidated()) {
         throw new IllegalStateException(Localiser.msg("057002", new Object[]{this}));
      }
   }

   protected boolean allowDDLOutput() {
      return true;
   }

   protected void executeDdlStatementList(List stmts, Connection conn) throws SQLException {
      Statement stmt = conn.createStatement();
      String stmtText = null;

      try {
         for(String var11 : stmts) {
            this.executeDdlStatement(stmt, var11);
         }
      } catch (SQLException sqe) {
         NucleusLogger.DATASTORE.error(Localiser.msg("057028", new Object[]{stmtText, sqe}));
         throw sqe;
      } finally {
         stmt.close();
      }

   }

   protected void executeDdlStatement(Statement stmt, String stmtText) throws SQLException {
      Writer ddlWriter = this.storeMgr.getDdlWriter();
      if (ddlWriter != null && this.allowDDLOutput()) {
         try {
            if (!this.storeMgr.hasWrittenDdlStatement(stmtText)) {
               ddlWriter.write(stmtText + ";\n\n");
               this.storeMgr.addWrittenDdlStatement(stmtText);
            }
         } catch (IOException e) {
            NucleusLogger.DATASTORE_SCHEMA.error("error writing DDL into file for table " + this.toString() + " and statement=" + stmtText, e);
         }
      } else {
         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(stmtText);
         }

         long startTime = System.currentTimeMillis();
         stmt.execute(stmtText);
         if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("045000", System.currentTimeMillis() - startTime));
         }
      }

      JDBCUtils.logWarnings(stmt);
   }

   protected boolean tableExistsInDatastore(Connection conn) throws SQLException {
      if (this.existsInDatastore == null) {
         try {
            this.exists(conn, false);
         } catch (MissingTableException var3) {
         }
      }

      return this.existsInDatastore;
   }
}
