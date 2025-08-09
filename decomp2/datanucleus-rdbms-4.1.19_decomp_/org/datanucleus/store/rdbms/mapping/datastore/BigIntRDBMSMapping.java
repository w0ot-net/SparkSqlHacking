package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NullValueException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.SingleFieldMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class BigIntRDBMSMapping extends AbstractDatastoreMapping {
   public BigIntRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      if (this.column != null) {
         this.column.checkPrimitive();
         if (this.getJavaTypeMapping() instanceof SingleFieldMapping) {
            Object[] validValues = ((SingleFieldMapping)this.getJavaTypeMapping()).getValidValues(0);
            if (validValues != null) {
               String constraints = this.storeMgr.getDatastoreAdapter().getCheckConstraintForValues(this.column.getIdentifier(), validValues, this.column.isNullable());
               this.column.setConstraints(constraints);
            }
         }
      }

      this.initTypeInfo();
   }

   public int getJDBCType() {
      return -5;
   }

   public void setInt(PreparedStatement ps, int param, int value) {
      try {
         ps.setLong(param, (long)value);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"int", "" + value}), e);
      }
   }

   public int getInt(ResultSet rs, int param) {
      try {
         int value = (int)rs.getLong(param);
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

   public void setString(PreparedStatement ps, int exprIndex, String value) {
      this.setLong(ps, exprIndex, Long.parseLong(value));
   }

   public String getString(ResultSet resultSet, int exprIndex) {
      return Long.toString(this.getLong(resultSet, exprIndex));
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            if (this.column != null && this.column.isDefaultable() && this.column.getDefaultValue() != null && !StringUtils.isWhitespace(this.column.getDefaultValue().toString())) {
               ps.setLong(param, Long.valueOf(this.column.getDefaultValue().toString().trim()));
            } else {
               ps.setNull(param, this.getJDBCType());
            }
         } else if (value instanceof Character) {
            ps.setInt(param, value.toString().charAt(0));
         } else if (value instanceof String) {
            ps.setLong(param, Long.valueOf((String)value));
         } else if (value instanceof Date) {
            ps.setLong(param, ((Date)value).getTime());
         } else {
            ps.setLong(param, ((Number)value).longValue());
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Long", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         String str = rs.getString(param);
         Object value;
         if (rs.wasNull()) {
            value = null;
         } else {
            try {
               value = Long.valueOf(str);
            } catch (NumberFormatException var6) {
               value = (new Double(str)).longValue();
            }

            if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_UTIL_DATE)) {
               value = new Date((Long)value);
            }
         }

         return value;
      } catch (SQLException e) {
         String msg = Localiser.msg("055002", new Object[]{"Long", "" + param, this.column, e.getMessage()});
         throw new NucleusDataStoreException(msg, e);
      }
   }
}
