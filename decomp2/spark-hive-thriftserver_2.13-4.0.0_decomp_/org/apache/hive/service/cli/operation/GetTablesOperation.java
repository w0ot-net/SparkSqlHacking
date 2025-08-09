package org.apache.hive.service.cli.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.TableMeta;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObject;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObjectUtils;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.OperationType;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.RowSetFactory;
import org.apache.hive.service.cli.TableSchema;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;

public class GetTablesOperation extends MetadataOperation {
   private final String catalogName;
   private final String schemaName;
   private final String tableName;
   private final List tableTypeList;
   protected final RowSet rowSet;
   private final TableTypeMapping tableTypeMapping;
   private static final TableSchema RESULT_SET_SCHEMA = (new TableSchema()).addStringColumn("TABLE_CAT", "Catalog name. NULL if not applicable.").addStringColumn("TABLE_SCHEM", "Schema name.").addStringColumn("TABLE_NAME", "Table name.").addStringColumn("TABLE_TYPE", "The table type, e.g. \"TABLE\", \"VIEW\", etc.").addStringColumn("REMARKS", "Comments about the table.").addStringColumn("TYPE_CAT", "The types catalog.").addStringColumn("TYPE_SCHEM", "The types schema.").addStringColumn("TYPE_NAME", "Type name.").addStringColumn("SELF_REFERENCING_COL_NAME", "Name of the designated \"identifier\" column of a typed table.").addStringColumn("REF_GENERATION", "Specifies how values in SELF_REFERENCING_COL_NAME are created.");

   protected GetTablesOperation(HiveSession parentSession, String catalogName, String schemaName, String tableName, List tableTypes) {
      super(parentSession, OperationType.GET_TABLES);
      this.catalogName = catalogName;
      this.schemaName = schemaName;
      this.tableName = tableName;
      String tableMappingStr = this.getParentSession().getHiveConf().getVar(ConfVars.HIVE_SERVER2_TABLE_TYPE_MAPPING);
      this.tableTypeMapping = TableTypeMappingFactory.getTableTypeMapping(tableMappingStr);
      if (tableTypes != null) {
         this.tableTypeList = new ArrayList();

         for(String tableType : tableTypes) {
            this.tableTypeList.addAll(Arrays.asList(this.tableTypeMapping.mapToHiveType(tableType.trim())));
         }
      } else {
         this.tableTypeList = null;
      }

      this.rowSet = RowSetFactory.create(RESULT_SET_SCHEMA, this.getProtocolVersion(), false);
   }

   public void runInternal() throws HiveSQLException {
      this.setState(OperationState.RUNNING);

      try {
         IMetaStoreClient metastoreClient = this.getParentSession().getMetaStoreClient();
         String schemaPattern = this.convertSchemaPattern(this.schemaName);
         List<String> matchingDbs = metastoreClient.getDatabases(schemaPattern);
         if (this.isAuthV2Enabled()) {
            List<HivePrivilegeObject> privObjs = HivePrivilegeObjectUtils.getHivePrivDbObjects(matchingDbs);
            String cmdStr = "catalog : " + this.catalogName + ", schemaPattern : " + this.schemaName;
            this.authorizeMetaGets(HiveOperationType.GET_TABLES, privObjs, cmdStr);
         }

         String tablePattern = this.convertIdentifierPattern(this.tableName, true);

         for(TableMeta tableMeta : metastoreClient.getTableMeta(schemaPattern, tablePattern, this.tableTypeList)) {
            this.rowSet.addRow(new Object[]{"", tableMeta.getDbName(), tableMeta.getTableName(), this.tableTypeMapping.mapToClientType(tableMeta.getTableType()), tableMeta.getComments(), null, null, null, null, null});
         }

         this.setState(OperationState.FINISHED);
      } catch (Exception e) {
         this.setState(OperationState.ERROR);
         throw new HiveSQLException(e);
      }
   }

   public TTableSchema getResultSetSchema() throws HiveSQLException {
      this.assertState(OperationState.FINISHED);
      return RESULT_SET_SCHEMA.toTTableSchema();
   }

   public TRowSet getNextRowSet(FetchOrientation orientation, long maxRows) throws HiveSQLException {
      this.assertState(OperationState.FINISHED);
      this.validateDefaultFetchOrientation(orientation);
      if (orientation.equals(FetchOrientation.FETCH_FIRST)) {
         this.rowSet.setStartOffset(0L);
      }

      return this.rowSet.extractSubset((int)maxRows).toTRowSet();
   }
}
