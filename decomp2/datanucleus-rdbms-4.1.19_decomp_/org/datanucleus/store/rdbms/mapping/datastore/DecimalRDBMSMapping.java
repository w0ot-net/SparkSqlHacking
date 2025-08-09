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

public class DecimalRDBMSMapping extends AbstractDatastoreMapping {
   private static final int INT_MAX_DECIMAL_DIGITS = 10;
   private static final int LONG_MAX_DECIMAL_DIGITS = 19;

   public DecimalRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      if (this.column != null && this.column.getColumnMetaData().getLength() == null) {
         if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_INTEGER)) {
            this.column.getColumnMetaData().setLength(10);
            this.column.checkDecimal();
         } else {
            this.column.getColumnMetaData().setLength(Math.min(this.getTypeInfo().getPrecision(), 19));
            this.column.checkDecimal();
         }
      }

      this.initTypeInfo();
   }

   public boolean isDecimalBased() {
      return true;
   }

   public int getJDBCType() {
      return 3;
   }

   public void setDouble(PreparedStatement ps, int param, double value) {
      try {
         ps.setDouble(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"double", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public void setFloat(PreparedStatement ps, int param, float value) {
      try {
         ps.setDouble(param, (double)value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"float", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public void setInt(PreparedStatement ps, int param, int value) {
      try {
         ps.setInt(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"int", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public double getDouble(ResultSet rs, int param) {
      try {
         double value = rs.getDouble(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"double", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public float getFloat(ResultSet rs, int param) {
      try {
         float value = (float)rs.getDouble(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"float", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public int getInt(ResultSet rs, int param) {
      try {
         int value = rs.getInt(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"int", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setLong(PreparedStatement ps, int param, long value) {
      try {
         ps.setLong(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"long", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public long getLong(ResultSet rs, int param) {
      try {
         long value = rs.getLong(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"long", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            if (this.column != null && this.column.isDefaultable() && this.column.getDefaultValue() != null) {
               ps.setInt(param, Integer.valueOf(this.column.getDefaultValue().toString()));
            } else {
               ps.setNull(param, this.getJDBCType());
            }
         } else if (value instanceof Integer) {
            ps.setBigDecimal(param, BigDecimal.valueOf(((Integer)value).longValue()));
         } else if (value instanceof Long) {
            ps.setBigDecimal(param, new BigDecimal((Long)value));
         } else if (value instanceof BigDecimal) {
            ps.setBigDecimal(param, (BigDecimal)value);
         } else if (value instanceof Float) {
            ps.setDouble(param, ((Float)value).doubleValue());
         } else if (value instanceof Double) {
            ps.setDouble(param, (Double)value);
         } else if (value instanceof BigInteger) {
            ps.setBigDecimal(param, new BigDecimal((BigInteger)value));
         } else {
            ps.setInt(param, (Integer)value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         Object value;
         if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_INTEGER)) {
            value = rs.getBigDecimal(param);
            value = value == null ? null : ((BigDecimal)value).toBigInteger().intValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_LONG)) {
            value = rs.getBigDecimal(param);
            value = value == null ? null : ((BigDecimal)value).toBigInteger().longValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_MATH_BIGINTEGER)) {
            value = rs.getBigDecimal(param);
            value = value == null ? null : ((BigDecimal)value).toBigInteger();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_MATH_BIGDECIMAL)) {
            value = rs.getBigDecimal(param);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_FLOAT)) {
            double d = rs.getDouble(param);
            value = rs.wasNull() ? null : (float)d;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_DOUBLE)) {
            double d = rs.getDouble(param);
            value = rs.wasNull() ? null : d;
         } else {
            int i = rs.getInt(param);
            value = rs.wasNull() ? null : i;
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
