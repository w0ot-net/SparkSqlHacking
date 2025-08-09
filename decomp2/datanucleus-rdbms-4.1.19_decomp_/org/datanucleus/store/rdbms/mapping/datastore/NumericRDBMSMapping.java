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
import org.datanucleus.store.rdbms.mapping.java.SingleFieldMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class NumericRDBMSMapping extends AbstractDatastoreMapping {
   private static final int INT_MAX_DECIMAL_DIGITS = 10;

   public NumericRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      if (this.column != null) {
         if (this.getJavaTypeMapping() instanceof SingleFieldMapping) {
            Object[] validValues = ((SingleFieldMapping)this.getJavaTypeMapping()).getValidValues(0);
            if (validValues != null) {
               String constraints = this.storeMgr.getDatastoreAdapter().getCheckConstraintForValues(this.column.getIdentifier(), validValues, this.column.isNullable());
               this.column.setConstraints(constraints);
            }
         }

         if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_INTEGER)) {
            if (this.column.getColumnMetaData().getLength() == null) {
               this.column.getColumnMetaData().setLength(10);
            }
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_BOOLEAN)) {
            this.column.getColumnMetaData().setLength(1);
            StringBuilder constraints = new StringBuilder("CHECK (" + this.column.getIdentifier() + " IN (1,0)");
            if (this.column.isNullable()) {
               constraints.append(" OR " + this.column.getIdentifier() + " IS NULL");
            }

            constraints.append(')');
            this.column.setConstraints(constraints.toString());
            this.column.checkDecimal();
         }
      }

      this.initTypeInfo();
   }

   public boolean isIntegerBased() {
      return true;
   }

   public int getJDBCType() {
      return 2;
   }

   public void setChar(PreparedStatement ps, int param, char value) {
      try {
         ps.setInt(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"char", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public char getChar(ResultSet rs, int param) {
      try {
         char value = (char)rs.getInt(param);
         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"char", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setInt(PreparedStatement ps, int param, int value) {
      try {
         ps.setInt(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"int", "" + value, this.column, e.getMessage()}), e);
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

   public void setByte(PreparedStatement ps, int param, byte value) {
      try {
         ps.setInt(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"byte", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public byte getByte(ResultSet rs, int param) {
      try {
         byte value = rs.getByte(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"byte", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setBoolean(PreparedStatement ps, int param, boolean value) {
      try {
         ps.setBoolean(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"boolean", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public boolean getBoolean(ResultSet rs, int param) {
      try {
         boolean value = rs.getBoolean(param);
         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"boolean", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setDouble(PreparedStatement ps, int param, double value) {
      try {
         ps.setDouble(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"double", "" + value, this.column, e.getMessage()}), e);
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

   public void setFloat(PreparedStatement ps, int param, float value) {
      try {
         ps.setDouble(param, (double)value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"float", "" + value, this.column, e.getMessage()}), e);
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

   public void setShort(PreparedStatement ps, int param, short value) {
      try {
         ps.setShort(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"short", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public short getShort(ResultSet rs, int param) {
      try {
         short value = rs.getShort(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"short", "" + param, this.column, e.getMessage()}), e);
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
            ps.setNull(param, this.getJDBCType());
         } else if (value instanceof Byte) {
            ps.setInt(param, (Byte)value);
         } else if (value instanceof Integer) {
            ps.setInt(param, (Integer)value);
         } else if (value instanceof Character) {
            String s = value.toString();
            ps.setInt(param, s.charAt(0));
         } else if (value instanceof String) {
            String s = (String)value;
            ps.setInt(param, s.charAt(0));
         } else if (value instanceof Long) {
            ps.setLong(param, (Long)value);
         } else if (value instanceof Float) {
            ps.setFloat(param, (Float)value);
         } else if (value instanceof Double) {
            ps.setDouble(param, (Double)value);
         } else if (value instanceof Short) {
            ps.setShort(param, (Short)value);
         } else if (value instanceof BigDecimal) {
            ps.setBigDecimal(param, (BigDecimal)value);
         } else if (value instanceof Boolean) {
            ps.setBoolean(param, (Boolean)value);
         } else {
            ps.setBigDecimal(param, new BigDecimal((BigInteger)value));
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Numeric", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         BigDecimal value = rs.getBigDecimal(param);
         if (value == null) {
            return null;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(Number.class.getName())) {
            return value;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_MATH_BIGINTEGER)) {
            return value.toBigInteger();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_INTEGER)) {
            return value.intValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_LONG)) {
            return value.longValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_BOOLEAN)) {
            return value.intValue() == 1;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_BYTE)) {
            return value.byteValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_SHORT)) {
            return value.shortValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_FLOAT)) {
            return value.floatValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_DOUBLE)) {
            return value.doubleValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_CHARACTER)) {
            return (char)value.intValue();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_STRING)) {
            return Character.valueOf((char)value.intValue()).toString();
         } else {
            return this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_MATH_BIGDECIMAL) ? value : value.longValue();
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Numeric", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
