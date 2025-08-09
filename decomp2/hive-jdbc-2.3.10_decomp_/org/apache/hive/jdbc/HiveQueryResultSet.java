package org.apache.hive.jdbc;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.RowSetFactory;
import org.apache.hive.service.cli.TableSchema;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.rpc.thrift.TCLIServiceConstants;
import org.apache.hive.service.rpc.thrift.TCloseOperationReq;
import org.apache.hive.service.rpc.thrift.TCloseOperationResp;
import org.apache.hive.service.rpc.thrift.TColumnDesc;
import org.apache.hive.service.rpc.thrift.TFetchOrientation;
import org.apache.hive.service.rpc.thrift.TFetchResultsReq;
import org.apache.hive.service.rpc.thrift.TFetchResultsResp;
import org.apache.hive.service.rpc.thrift.TGetResultSetMetadataReq;
import org.apache.hive.service.rpc.thrift.TGetResultSetMetadataResp;
import org.apache.hive.service.rpc.thrift.TOperationHandle;
import org.apache.hive.service.rpc.thrift.TPrimitiveTypeEntry;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TSessionHandle;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.hive.service.rpc.thrift.TTypeEntry;
import org.apache.hive.service.rpc.thrift.TTypeQualifierValue;
import org.apache.hive.service.rpc.thrift.TTypeQualifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveQueryResultSet extends HiveBaseResultSet {
   public static final Logger LOG = LoggerFactory.getLogger(HiveQueryResultSet.class);
   private TCLIService.Iface client;
   private TOperationHandle stmtHandle;
   private TSessionHandle sessHandle;
   private int maxRows;
   private int fetchSize;
   private int rowsFetched = 0;
   private RowSet fetchedRows;
   private Iterator fetchedRowsItr;
   private boolean isClosed = false;
   private boolean emptyResultSet = false;
   private boolean isScrollable = false;
   private boolean fetchFirst = false;
   private final TProtocolVersion protocol;

   protected HiveQueryResultSet(Builder builder) throws SQLException {
      this.statement = builder.statement;
      this.client = builder.client;
      this.stmtHandle = builder.stmtHandle;
      this.sessHandle = builder.sessHandle;
      this.fetchSize = builder.fetchSize;
      this.columnNames = new ArrayList();
      this.normalizedColumnNames = new ArrayList();
      this.columnTypes = new ArrayList();
      this.columnAttributes = new ArrayList();
      if (builder.retrieveSchema) {
         this.retrieveSchema();
      } else {
         this.setSchema(builder.colNames, builder.colTypes, builder.colAttributes);
      }

      this.emptyResultSet = builder.emptyResultSet;
      if (builder.emptyResultSet) {
         this.maxRows = 0;
      } else {
         this.maxRows = builder.maxRows;
      }

      this.isScrollable = builder.isScrollable;
      this.protocol = builder.getProtocolVersion();
   }

   private static JdbcColumnAttributes getColumnAttributes(TPrimitiveTypeEntry primitiveTypeEntry) {
      JdbcColumnAttributes ret = null;
      if (primitiveTypeEntry.isSetTypeQualifiers()) {
         TTypeQualifiers tq = primitiveTypeEntry.getTypeQualifiers();
         switch (primitiveTypeEntry.getType()) {
            case CHAR_TYPE:
            case VARCHAR_TYPE:
               TTypeQualifierValue val = (TTypeQualifierValue)tq.getQualifiers().get("characterMaximumLength");
               if (val != null) {
                  ret = new JdbcColumnAttributes(val.getI32Value(), 0);
               }
               break;
            case DECIMAL_TYPE:
               TTypeQualifierValue prec = (TTypeQualifierValue)tq.getQualifiers().get("precision");
               TTypeQualifierValue scale = (TTypeQualifierValue)tq.getQualifiers().get("scale");
               ret = new JdbcColumnAttributes(prec == null ? 10 : prec.getI32Value(), scale == null ? 0 : scale.getI32Value());
         }
      }

      return ret;
   }

   private void retrieveSchema() throws SQLException {
      try {
         TGetResultSetMetadataReq metadataReq = new TGetResultSetMetadataReq(this.stmtHandle);
         TGetResultSetMetadataResp metadataResp = this.client.GetResultSetMetadata(metadataReq);
         Utils.verifySuccess(metadataResp.getStatus());
         StringBuilder namesSb = new StringBuilder();
         StringBuilder typesSb = new StringBuilder();
         TTableSchema schema = metadataResp.getSchema();
         if (schema != null && schema.isSetColumns()) {
            this.setSchema(new TableSchema(schema));
            List<TColumnDesc> columns = schema.getColumns();

            for(int pos = 0; pos < schema.getColumnsSize(); ++pos) {
               if (pos != 0) {
                  namesSb.append(",");
                  typesSb.append(",");
               }

               String columnName = ((TColumnDesc)columns.get(pos)).getColumnName();
               this.columnNames.add(columnName);
               this.normalizedColumnNames.add(columnName.toLowerCase());
               TPrimitiveTypeEntry primitiveTypeEntry = ((TTypeEntry)((TColumnDesc)columns.get(pos)).getTypeDesc().getTypes().get(0)).getPrimitiveEntry();
               String columnTypeName = (String)TCLIServiceConstants.TYPE_NAMES.get(primitiveTypeEntry.getType());
               this.columnTypes.add(columnTypeName);
               this.columnAttributes.add(getColumnAttributes(primitiveTypeEntry));
            }

         }
      } catch (SQLException eS) {
         throw eS;
      } catch (Exception ex) {
         ex.printStackTrace();
         throw new SQLException("Could not create ResultSet: " + ex.getMessage(), ex);
      }
   }

   private void setSchema(List colNames, List colTypes, List colAttributes) {
      this.columnNames.addAll(colNames);
      this.columnTypes.addAll(colTypes);
      this.columnAttributes.addAll(colAttributes);

      for(String colName : colNames) {
         this.normalizedColumnNames.add(colName.toLowerCase());
      }

   }

   public void close() throws SQLException {
      if (this.statement != null && this.statement instanceof HiveStatement) {
         HiveStatement s = (HiveStatement)this.statement;
         s.closeClientOperation();
      } else {
         this.closeOperationHandle(this.stmtHandle);
      }

      this.client = null;
      this.stmtHandle = null;
      this.sessHandle = null;
      this.isClosed = true;
   }

   private void closeOperationHandle(TOperationHandle stmtHandle) throws SQLException {
      try {
         if (stmtHandle != null) {
            TCloseOperationReq closeReq = new TCloseOperationReq(stmtHandle);
            TCloseOperationResp closeResp = this.client.CloseOperation(closeReq);
            Utils.verifySuccessWithInfo(closeResp.getStatus());
         }

      } catch (SQLException e) {
         throw e;
      } catch (Exception e) {
         throw new SQLException(e.toString(), "08S01", e);
      }
   }

   public boolean next() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Resultset is closed");
      } else if (!this.emptyResultSet && (this.maxRows <= 0 || this.rowsFetched < this.maxRows)) {
         if (this.statement != null && this.statement instanceof HiveStatement) {
            ((HiveStatement)this.statement).waitForOperationToComplete();
         }

         try {
            TFetchOrientation orientation = TFetchOrientation.FETCH_NEXT;
            if (this.fetchFirst) {
               orientation = TFetchOrientation.FETCH_FIRST;
               this.fetchedRows = null;
               this.fetchedRowsItr = null;
               this.fetchFirst = false;
            }

            if (this.fetchedRows == null || !this.fetchedRowsItr.hasNext()) {
               TFetchResultsReq fetchReq = new TFetchResultsReq(this.stmtHandle, orientation, (long)this.fetchSize);
               TFetchResultsResp fetchResp = this.client.FetchResults(fetchReq);
               Utils.verifySuccessWithInfo(fetchResp.getStatus());
               TRowSet results = fetchResp.getResults();
               this.fetchedRows = RowSetFactory.create(results, this.protocol);
               this.fetchedRowsItr = this.fetchedRows.iterator();
            }

            if (this.fetchedRowsItr.hasNext()) {
               this.row = this.fetchedRowsItr.next();
               ++this.rowsFetched;
               return true;
            } else {
               return false;
            }
         } catch (SQLException eS) {
            throw eS;
         } catch (Exception ex) {
            ex.printStackTrace();
            throw new SQLException("Error retrieving next row", ex);
         }
      } else {
         return false;
      }
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Resultset is closed");
      } else {
         return super.getMetaData();
      }
   }

   public void setFetchSize(int rows) throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Resultset is closed");
      } else {
         this.fetchSize = rows;
      }
   }

   public int getType() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Resultset is closed");
      } else {
         return this.isScrollable ? 1004 : 1003;
      }
   }

   public int getFetchSize() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Resultset is closed");
      } else {
         return this.fetchSize;
      }
   }

   public Object getObject(String columnLabel, Class type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Object getObject(int columnIndex, Class type) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void beforeFirst() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Resultset is closed");
      } else if (!this.isScrollable) {
         throw new SQLException("Method not supported for TYPE_FORWARD_ONLY resultset");
      } else {
         this.fetchFirst = true;
         this.rowsFetched = 0;
      }
   }

   public boolean isBeforeFirst() throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Resultset is closed");
      } else {
         return this.rowsFetched == 0;
      }
   }

   public int getRow() throws SQLException {
      return this.rowsFetched;
   }

   public boolean isClosed() {
      return this.isClosed;
   }

   public static class Builder {
      private final Connection connection;
      private final Statement statement;
      private TCLIService.Iface client = null;
      private TOperationHandle stmtHandle = null;
      private TSessionHandle sessHandle = null;
      private int maxRows = 0;
      private boolean retrieveSchema = true;
      private List colNames;
      private List colTypes;
      private List colAttributes;
      private int fetchSize = 50;
      private boolean emptyResultSet = false;
      private boolean isScrollable = false;
      private ReentrantLock transportLock = null;

      public Builder(Statement statement) throws SQLException {
         this.statement = statement;
         this.connection = statement.getConnection();
      }

      public Builder(Connection connection) {
         this.statement = null;
         this.connection = connection;
      }

      public Builder setClient(TCLIService.Iface client) {
         this.client = client;
         return this;
      }

      public Builder setStmtHandle(TOperationHandle stmtHandle) {
         this.stmtHandle = stmtHandle;
         return this;
      }

      public Builder setSessionHandle(TSessionHandle sessHandle) {
         this.sessHandle = sessHandle;
         return this;
      }

      public Builder setMaxRows(int maxRows) {
         this.maxRows = maxRows;
         return this;
      }

      public Builder setSchema(List colNames, List colTypes) {
         List<JdbcColumnAttributes> colAttributes = new ArrayList();

         for(int idx = 0; idx < colTypes.size(); ++idx) {
            colAttributes.add((Object)null);
         }

         return this.setSchema(colNames, colTypes, colAttributes);
      }

      public Builder setSchema(List colNames, List colTypes, List colAttributes) {
         this.colNames = new ArrayList();
         this.colNames.addAll(colNames);
         this.colTypes = new ArrayList();
         this.colTypes.addAll(colTypes);
         this.colAttributes = new ArrayList();
         this.colAttributes.addAll(colAttributes);
         this.retrieveSchema = false;
         return this;
      }

      public Builder setFetchSize(int fetchSize) {
         this.fetchSize = fetchSize;
         return this;
      }

      public Builder setEmptyResultSet(boolean emptyResultSet) {
         this.emptyResultSet = emptyResultSet;
         return this;
      }

      public Builder setScrollable(boolean setScrollable) {
         this.isScrollable = setScrollable;
         return this;
      }

      public Builder setTransportLock(ReentrantLock transportLock) {
         this.transportLock = transportLock;
         return this;
      }

      public HiveQueryResultSet build() throws SQLException {
         return new HiveQueryResultSet(this);
      }

      public TProtocolVersion getProtocolVersion() throws SQLException {
         return ((HiveConnection)this.connection).getProtocol();
      }
   }
}
