package org.datanucleus.store.rdbms.mapping.datastore;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NullValueException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.SingleFieldMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class SmallIntRDBMSMapping extends AbstractDatastoreMapping {
   public SmallIntRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      if (this.column != null) {
         this.column.checkPrimitive();
         JavaTypeMapping m = this.getJavaTypeMapping();
         if (m instanceof SingleFieldMapping) {
            Object[] validValues = ((SingleFieldMapping)m).getValidValues(0);
            if (validValues != null) {
               String constraints = this.storeMgr.getDatastoreAdapter().getCheckConstraintForValues(this.column.getIdentifier(), validValues, this.column.isNullable());
               this.column.setConstraints(constraints);
            }
         }

         if (this.getJavaTypeMapping().getJavaType() == Boolean.class) {
            StringBuilder constraints = new StringBuilder("CHECK (" + this.column.getIdentifier() + " IN (0,1)");
            if (this.column.isNullable()) {
               constraints.append(" OR " + this.column.getIdentifier() + " IS NULL");
            }

            constraints.append(')');
            this.column.setConstraints(constraints.toString());
         }
      }

      this.initTypeInfo();
   }

   public boolean isIntegerBased() {
      return true;
   }

   public int getJDBCType() {
      return 5;
   }

   public void setBoolean(PreparedStatement ps, int param, boolean value) {
      try {
         ps.setInt(param, value ? 1 : 0);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"boolean", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public boolean getBoolean(ResultSet rs, int param) {
      try {
         int intValue = rs.getInt(param);
         boolean value;
         if (intValue == 0) {
            value = false;
         } else {
            if (intValue != 1) {
               throw new NucleusDataStoreException(Localiser.msg("055006", new Object[]{"Types.SMALLINT", "" + intValue}));
            }

            value = true;
         }

         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Boolean", "" + param, this.column, e.getMessage()}), e);
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

   public void setLong(PreparedStatement ps, int param, long value) {
      try {
         ps.setLong(param, value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"short", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public long getLong(ResultSet rs, int param) {
      try {
         long value = (long)rs.getShort(param);
         if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"short", "" + param, this.column, e.getMessage()}), e);
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

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            if (this.column != null && this.column.isDefaultable() && this.column.getDefaultValue() != null && !StringUtils.isWhitespace(this.column.getDefaultValue().toString())) {
               ps.setInt(param, Integer.valueOf(this.column.getDefaultValue().toString()));
            } else {
               ps.setNull(param, this.getJDBCType());
            }
         } else {
            Class type = value.getClass();
            if (type == Integer.class) {
               ps.setShort(param, ((Integer)value).shortValue());
            } else if (type == Short.class) {
               ps.setShort(param, (Short)value);
            } else if (type == Byte.class) {
               ps.setShort(param, ((Byte)value).shortValue());
            } else if (type == Character.class) {
               ps.setShort(param, (short)(Character)value);
            } else if (type == Boolean.class) {
               ps.setShort(param, (short)((Boolean)value ? 1 : 0));
            } else if (type == BigInteger.class) {
               ps.setShort(param, ((BigInteger)value).shortValue());
            } else {
               if (type != Long.class) {
                  throw new NucleusException("SmallIntRDBMSMapping.setObject called for " + StringUtils.toJVMIDString(value) + " but not supported");
               }

               ps.setShort(param, ((Long)value).shortValue());
            }
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         short d = rs.getShort(param);
         Class type = this.getJavaTypeMapping().getJavaType();
         Object value;
         if (type == Short.class) {
            value = rs.wasNull() ? null : d;
         } else if (type == Integer.class) {
            value = rs.wasNull() ? null : Integer.valueOf(d);
         } else if (type == Byte.class) {
            value = rs.wasNull() ? null : (byte)d;
         } else if (type == BigInteger.class) {
            value = rs.wasNull() ? null : BigInteger.valueOf((long)d);
         } else if (type == Boolean.class) {
            value = rs.wasNull() ? null : (d == 1 ? Boolean.TRUE : Boolean.FALSE);
         } else {
            value = rs.wasNull() ? null : d;
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
