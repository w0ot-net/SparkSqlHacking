package org.apache.derby.impl.jdbc;

import java.sql.ParameterMetaData;
import java.sql.SQLException;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataTypeUtilities;

public class EmbedParameterSetMetaData implements ParameterMetaData {
   private final ParameterValueSet pvs;
   private final DataTypeDescriptor[] types;
   private final int paramCount;

   protected EmbedParameterSetMetaData(ParameterValueSet var1, DataTypeDescriptor[] var2) {
      int var3 = var1.getParameterCount();
      this.pvs = var1;
      this.paramCount = var3;
      this.types = var2;
   }

   public int getParameterCount() {
      return this.paramCount;
   }

   public int isNullable(int var1) throws SQLException {
      this.checkPosition(var1);
      return this.types[var1 - 1].isNullable() ? 1 : 0;
   }

   public boolean isSigned(int var1) throws SQLException {
      this.checkPosition(var1);
      return this.types[var1 - 1].getTypeId().isNumericTypeId();
   }

   public int getPrecision(int var1) throws SQLException {
      this.checkPosition(var1);
      int var2 = -1;
      if (var1 == 1 && this.pvs.hasReturnOutputParameter()) {
         var2 = this.pvs.getPrecision(var1);
      }

      return var2 == -1 ? DataTypeUtilities.getPrecision(this.types[var1 - 1]) : var2;
   }

   public int getScale(int var1) throws SQLException {
      this.checkPosition(var1);
      return var1 == 1 && this.pvs.hasReturnOutputParameter() ? this.pvs.getScale(var1) : this.types[var1 - 1].getScale();
   }

   public int getParameterType(int var1) throws SQLException {
      this.checkPosition(var1);
      return this.types[var1 - 1].getTypeId().getJDBCTypeId();
   }

   public String getParameterTypeName(int var1) throws SQLException {
      this.checkPosition(var1);
      return this.types[var1 - 1].getTypeId().getSQLTypeName();
   }

   public String getParameterClassName(int var1) throws SQLException {
      this.checkPosition(var1);
      return this.types[var1 - 1].getTypeId().getResultSetMetaDataTypeName();
   }

   public int getParameterMode(int var1) throws SQLException {
      this.checkPosition(var1);
      return var1 == 1 && this.pvs.hasReturnOutputParameter() ? 4 : this.pvs.getParameterMode(var1);
   }

   private void checkPosition(int var1) throws SQLException {
      if (var1 < 1 || var1 > this.paramCount) {
         throw Util.generateCsSQLException("XCL13.S", var1, this.paramCount);
      }
   }

   public boolean isWrapperFor(Class var1) throws SQLException {
      return var1.isInstance(this);
   }

   public Object unwrap(Class var1) throws SQLException {
      try {
         return var1.cast(this);
      } catch (ClassCastException var3) {
         throw Util.generateCsSQLException("XJ128.S", var1);
      }
   }
}
