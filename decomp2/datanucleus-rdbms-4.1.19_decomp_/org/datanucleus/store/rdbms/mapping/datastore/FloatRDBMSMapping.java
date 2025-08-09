package org.datanucleus.store.rdbms.mapping.datastore;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NullValueException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class FloatRDBMSMapping extends DoubleRDBMSMapping {
   public FloatRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   public int getJDBCType() {
      return 6;
   }

   public float getFloat(ResultSet rs, int param) {
      try {
         float value = rs.getFloat(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"float", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setFloat(PreparedStatement ps, int param, float value) {
      try {
         ps.setFloat(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"float", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else if (value instanceof Integer) {
            ps.setFloat(param, ((Integer)value).floatValue());
         } else if (value instanceof Long) {
            ps.setFloat(param, ((Long)value).floatValue());
         } else if (value instanceof Short) {
            ps.setFloat(param, ((Short)value).floatValue());
         } else if (value instanceof BigInteger) {
            ps.setFloat(param, ((BigInteger)value).floatValue());
         } else if (value instanceof BigDecimal) {
            ps.setFloat(param, ((BigDecimal)value).floatValue());
         } else if (value instanceof Character) {
            ps.setFloat(param, (float)value.toString().charAt(0));
         } else if (value instanceof Float) {
            ps.setFloat(param, (Float)value);
         } else if (value instanceof Double) {
            ps.setDouble(param, (Double)value);
         } else {
            ps.setFloat(param, ((Double)value).floatValue());
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         float d = rs.getFloat(param);
         Object value;
         if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_INTEGER)) {
            value = rs.wasNull() ? null : (int)d;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_LONG)) {
            value = rs.wasNull() ? null : (long)d;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_FLOAT)) {
            value = rs.wasNull() ? null : new Float(d);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_DOUBLE)) {
            double dbl = rs.getDouble(param);
            value = rs.wasNull() ? null : dbl;
         } else {
            value = rs.wasNull() ? null : (double)d;
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
