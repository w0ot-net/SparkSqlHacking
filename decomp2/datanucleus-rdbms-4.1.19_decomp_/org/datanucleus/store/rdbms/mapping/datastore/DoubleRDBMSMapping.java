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

public class DoubleRDBMSMapping extends AbstractDatastoreMapping {
   public DoubleRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      if (this.column != null) {
         this.column.checkPrimitive();
      }

      this.initTypeInfo();
   }

   public boolean isDecimalBased() {
      return true;
   }

   public int getJDBCType() {
      return 8;
   }

   public void setInt(PreparedStatement ps, int param, int value) {
      try {
         ps.setDouble(param, (double)value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"int", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public int getInt(ResultSet rs, int param) {
      try {
         int value = (int)rs.getDouble(param);
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

   public void setFloat(PreparedStatement ps, int param, float value) {
      try {
         ps.setDouble(param, (double)value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"float", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else if (value instanceof Integer) {
            ps.setDouble(param, ((Integer)value).doubleValue());
         } else if (value instanceof Long) {
            ps.setDouble(param, ((Long)value).doubleValue());
         } else if (value instanceof Short) {
            ps.setDouble(param, ((Short)value).doubleValue());
         } else if (value instanceof Float) {
            ps.setDouble(param, ((Float)value).doubleValue());
         } else if (value instanceof Character) {
            ps.setDouble(param, (double)value.toString().charAt(0));
         } else if (value instanceof BigInteger) {
            ps.setDouble(param, ((BigInteger)value).doubleValue());
         } else if (value instanceof BigDecimal) {
            ps.setDouble(param, ((BigDecimal)value).doubleValue());
         } else {
            ps.setDouble(param, (Double)value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         double d = rs.getDouble(param);
         Object value;
         if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_INTEGER)) {
            value = rs.wasNull() ? null : (int)d;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_LONG)) {
            value = rs.wasNull() ? null : (long)d;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_FLOAT)) {
            value = rs.wasNull() ? null : (float)d;
         } else {
            value = rs.wasNull() ? null : d;
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
