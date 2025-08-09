package org.apache.derby.catalog;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.impl.jdbc.EmbedResultSetMetaData;
import org.apache.derby.vti.VTITemplate;

public class GetProcedureColumns extends VTITemplate {
   private boolean isProcedure;
   private boolean isFunction;
   private int rowCount;
   private int returnedTableColumnCount;
   private TypeDescriptor tableFunctionReturnType;
   private RoutineAliasInfo procedure;
   private int paramCursor;
   private short method_count;
   private short param_number;
   private TypeDescriptor sqlType;
   private String columnName;
   private short columnType;
   private final short nullable;
   private static final ResultColumnDescriptor[] columnInfo = new ResultColumnDescriptor[]{EmbedResultSetMetaData.getResultColumnDescriptor("COLUMN_NAME", 12, false, 128), EmbedResultSetMetaData.getResultColumnDescriptor("COLUMN_TYPE", 5, false), EmbedResultSetMetaData.getResultColumnDescriptor("DATA_TYPE", 4, false), EmbedResultSetMetaData.getResultColumnDescriptor("TYPE_NAME", 12, false, 22), EmbedResultSetMetaData.getResultColumnDescriptor("PRECISION", 4, false), EmbedResultSetMetaData.getResultColumnDescriptor("LENGTH", 4, false), EmbedResultSetMetaData.getResultColumnDescriptor("SCALE", 5, false), EmbedResultSetMetaData.getResultColumnDescriptor("RADIX", 5, false), EmbedResultSetMetaData.getResultColumnDescriptor("NULLABLE", 5, false), EmbedResultSetMetaData.getResultColumnDescriptor("REMARKS", 12, true, 22), EmbedResultSetMetaData.getResultColumnDescriptor("METHOD_ID", 5, false), EmbedResultSetMetaData.getResultColumnDescriptor("PARAMETER_ID", 5, false)};
   private static final ResultSetMetaData metadata;

   private int translate(int var1) {
      if (!this.isFunction) {
         return var1;
      } else {
         switch (var1) {
            case 0:
               return 0;
            case 1:
               return 1;
            case 2:
               return 2;
            case 3:
            default:
               return 0;
            case 4:
               return 3;
            case 5:
               return 4;
         }
      }
   }

   public ResultSetMetaData getMetaData() {
      return metadata;
   }

   public GetProcedureColumns(AliasInfo var1, String var2) throws SQLException {
      byte var3 = -2;
      if (var1 != null) {
         this.isProcedure = var2.equals("P");
         this.isFunction = var2.equals("F");
         this.procedure = (RoutineAliasInfo)var1;
         this.method_count = (short)this.procedure.getParameterCount();
         this.rowCount = this.procedure.getParameterCount();
         if (this.procedure.isTableFunction()) {
            this.tableFunctionReturnType = this.procedure.getReturnType();
            this.returnedTableColumnCount = this.tableFunctionReturnType.getRowColumnNames().length;
            this.rowCount += this.returnedTableColumnCount;
            var3 = -1;
         }
      }

      if (var2 == null) {
         this.nullable = 0;
      } else if (this.isFunction) {
         this.nullable = 1;
         this.sqlType = this.procedure.getReturnType();
         this.columnName = "";
         this.columnType = 4;
         this.paramCursor = var3;
      } else {
         this.nullable = 1;
         this.paramCursor = -1;
      }
   }

   public boolean next() throws SQLException {
      if (++this.paramCursor >= this.rowCount) {
         return false;
      } else {
         if (this.procedure.isTableFunction() && this.paramCursor >= this.procedure.getParameterCount()) {
            int var1 = this.paramCursor - this.procedure.getParameterCount();
            this.sqlType = this.tableFunctionReturnType.getRowTypes()[var1];
            this.columnName = this.tableFunctionReturnType.getRowColumnNames()[var1];
            this.columnType = 5;
         } else if (this.paramCursor > -1) {
            this.sqlType = this.procedure.getParameterTypes()[this.paramCursor];
            this.columnName = this.procedure.getParameterNames()[this.paramCursor];
            this.columnType = (short)this.translate(this.procedure.getParameterModes()[this.paramCursor]);
         }

         this.param_number = (short)this.paramCursor;
         return true;
      }
   }

   public String getString(int var1) throws SQLException {
      switch (var1) {
         case 1 -> {
            return this.columnName;
         }
         case 4 -> {
            return this.sqlType.getTypeName();
         }
         case 10 -> {
            return null;
         }
         default -> {
            return super.getString(var1);
         }
      }
   }

   public int getInt(int var1) throws SQLException {
      switch (var1) {
         case 3:
            if (this.sqlType != null) {
               return this.sqlType.getJDBCTypeId();
            }

            return 2000;
         case 4:
         default:
            return super.getInt(var1);
         case 5:
            if (this.sqlType != null) {
               int var2 = this.sqlType.getJDBCTypeId();
               if (DataTypeDescriptor.isNumericType(var2)) {
                  return this.sqlType.getPrecision();
               }

               if (var2 != 91 && var2 != 92 && var2 != 93) {
                  return this.sqlType.getMaximumWidth();
               }

               return DataTypeUtilities.getColumnDisplaySize(var2, -1);
            }

            return 0;
         case 6:
            return this.sqlType != null ? this.sqlType.getMaximumWidthInBytes() : 0;
      }
   }

   public short getShort(int var1) throws SQLException {
      switch (var1) {
         case 2:
            return this.columnType;
         case 3:
         case 4:
         case 5:
         case 6:
         case 10:
         default:
            return super.getShort(var1);
         case 7:
            if (this.sqlType != null) {
               return (short)this.sqlType.getScale();
            }

            return 0;
         case 8:
            if (this.sqlType != null) {
               int var2 = this.sqlType.getJDBCTypeId();
               if (var2 != 7 && var2 != 6 && var2 != 8) {
                  return 10;
               }

               return 2;
            }

            return 0;
         case 9:
            return this.nullable;
         case 11:
            return this.method_count;
         case 12:
            return this.param_number;
      }
   }

   public void close() {
   }

   static {
      metadata = new EmbedResultSetMetaData(columnInfo);
   }
}
