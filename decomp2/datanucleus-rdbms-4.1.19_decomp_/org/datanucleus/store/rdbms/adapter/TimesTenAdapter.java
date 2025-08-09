package org.datanucleus.store.rdbms.adapter;

import java.sql.DatabaseMetaData;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.mapping.RDBMSMappingManager;
import org.datanucleus.store.rdbms.mapping.datastore.TimesTenVarBinaryRDBMSMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.StoreSchemaHandler;
import org.datanucleus.util.StringUtils;

public class TimesTenAdapter extends BaseDatastoreAdapter {
   public static final String RESERVED_WORDS = "AGING,                  CROSS,           GROUP,ALL,                    CURRENT_SCHEMA,  HAVING,ANY,                    CURRENT_USER,    INNER,AS,                     CURSOR,          INT,BETWEEN,                DATASTORE_OWNER, INTEGER,BIGINT,                 DATE,            INTERSECT,BINARY,                 DEC,             INTERVAL,BINARY_DOUBLE_INFINITY, DECIMAL,         INTO,BINARY_DOUBLE_NAN,      DEFAULT,         IS,BINARY_FLOAT_INFINITY,  DESTROY,         JOIN,BINARY_FLOAT_NAN,       DISTINCT,        LEFT,CASE,                   DOUBLE,          LIKE,CHAR,                   FIRST,           LONG,CHARACTER,              FLOAT,           MINUS,COLUMN,                 FOR,             NATIONAL,CONNECTION,             FOREIGN,         NCHAR,CONSTRAINT,             FROM,            NO,NULL,                   RIGHT,           TINYINT,NUMERIC,                ROWNUM,          TT_SYSDATE,NVARCHAR,               ROWS,            UNION,ON,                     SELECT,          UNIQUE,ORA_SYSDATE,            SELF,            UPDATE,ORDER,                  SESSION_USER,    USER,PRIMARY,                SET,             USING,PROPAGATE,              SMALLINT,        VARBINARY,PUBLIC,                 SOME,            VARCHAR,READONLY,               SYSDATE,         VARYING,REAL,                   SYSTEM_USER,     WHEN,RETURN,                 TIME,            WHERE";

   public TimesTenAdapter(DatabaseMetaData metadata) {
      super(metadata);
      this.reservedKeywords.addAll(StringUtils.convertCommaSeparatedStringToSet("AGING,                  CROSS,           GROUP,ALL,                    CURRENT_SCHEMA,  HAVING,ANY,                    CURRENT_USER,    INNER,AS,                     CURSOR,          INT,BETWEEN,                DATASTORE_OWNER, INTEGER,BIGINT,                 DATE,            INTERSECT,BINARY,                 DEC,             INTERVAL,BINARY_DOUBLE_INFINITY, DECIMAL,         INTO,BINARY_DOUBLE_NAN,      DEFAULT,         IS,BINARY_FLOAT_INFINITY,  DESTROY,         JOIN,BINARY_FLOAT_NAN,       DISTINCT,        LEFT,CASE,                   DOUBLE,          LIKE,CHAR,                   FIRST,           LONG,CHARACTER,              FLOAT,           MINUS,COLUMN,                 FOR,             NATIONAL,CONNECTION,             FOREIGN,         NCHAR,CONSTRAINT,             FROM,            NO,NULL,                   RIGHT,           TINYINT,NUMERIC,                ROWNUM,          TT_SYSDATE,NVARCHAR,               ROWS,            UNION,ON,                     SELECT,          UNIQUE,ORA_SYSDATE,            SELF,            UPDATE,ORDER,                  SESSION_USER,    USER,PRIMARY,                SET,             USING,PROPAGATE,              SMALLINT,        VARBINARY,PUBLIC,                 SOME,            VARCHAR,READONLY,               SYSDATE,         VARYING,REAL,                   SYSTEM_USER,     WHEN,RETURN,                 TIME,            WHERE"));
      this.supportedOptions.remove("DeferredConstraints");
      this.supportedOptions.add("UniqueInEndCreateStatements");
      this.supportedOptions.remove("CheckInCreateStatements");
      this.supportedOptions.remove("ColumnOptions_NullsKeyword");
      this.supportedOptions.remove("ANSI_Join_Syntax");
      this.supportedOptions.remove("FkDeleteActionNull");
      this.supportedOptions.remove("FkDeleteActionCascade");
      this.supportedOptions.remove("FkDeleteActionDefault");
      this.supportedOptions.remove("FkDeleteActionRestrict");
      this.supportedOptions.remove("FkUpdateActionDefault");
      this.supportedOptions.remove("FkUpdateActionRestrict");
      this.supportedOptions.remove("FkUpdateActionNull");
      this.supportedOptions.remove("FkUpdateActionCascade");
      this.supportedOptions.remove("TxIsolationReadUncommitted");
      this.supportedOptions.remove("TxIsolationReadRepeatableRead");
      this.supportedOptions.remove("TxIsolationNone");
   }

   public String getVendorID() {
      return "timesten";
   }

   public void initialiseTypes(StoreSchemaHandler handler, ManagedConnection mconn) {
      super.initialiseTypes(handler, mconn);
      RDBMSStoreManager storeMgr = (RDBMSStoreManager)handler.getStoreManager();
      MappingManager mapMgr = storeMgr.getMappingManager();
      if (mapMgr instanceof RDBMSMappingManager) {
         RDBMSMappingManager rdbmsMapMgr = (RDBMSMappingManager)mapMgr;
         rdbmsMapMgr.deregisterDatastoreMappingsForJDBCType("VARBINARY");
         rdbmsMapMgr.registerDatastoreMapping("java.io.Serializable", TimesTenVarBinaryRDBMSMapping.class, "VARBINARY", "VARBINARY", true);
      }

   }

   public String getAddCandidateKeyStatement(CandidateKey ck, IdentifierFactory factory) {
      Index idx = new Index(ck);
      idx.setName(ck.getName());
      return this.getCreateIndexStatement(idx, factory);
   }

   public String getAddColumnStatement(Table table, Column col) {
      String stmnt = super.getAddColumnStatement(table, col);
      return stmnt.replaceAll("NOT NULL", "");
   }

   public String getAddForeignKeyStatement(ForeignKey fk, IdentifierFactory factory) {
      return isSelfReferencingForeignKey(fk) ? this.getDatastoreDateStatement() : super.getAddForeignKeyStatement(fk, factory);
   }

   private static boolean isSelfReferencingForeignKey(ForeignKey fk) {
      if (fk != null) {
         String sql = fk.toString();
         Table obj = fk.getTable();
         if (obj != null) {
            String container = obj.toString();
            return isSelfReferencingForeignKey(sql, container);
         }
      }

      return false;
   }

   private static boolean isSelfReferencingForeignKey(String sql, String ref) {
      if (sql != null && ref != null) {
         String REFERENCES = "REFERENCES";
         int refi = sql.indexOf("REFERENCES");
         if (refi != -1) {
            String cut = sql.substring(refi + "REFERENCES".length());
            int spacei = cut.trim().indexOf(" ");
            if (spacei != -1) {
               return cut.substring(0, spacei + 1).trim().equalsIgnoreCase(ref);
            }

            return cut.trim().equalsIgnoreCase(ref);
         }
      }

      return false;
   }

   public String getDatastoreDateStatement() {
      return "select tt_sysdate from dual";
   }
}
