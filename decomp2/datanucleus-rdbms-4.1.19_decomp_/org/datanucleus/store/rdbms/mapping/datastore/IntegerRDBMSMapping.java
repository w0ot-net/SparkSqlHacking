package org.datanucleus.store.rdbms.mapping.datastore;

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
import org.datanucleus.util.StringUtils;

public class IntegerRDBMSMapping extends AbstractDatastoreMapping {
   public IntegerRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
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
      return 4;
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
            if (this.column != null && this.column.isDefaultable() && this.column.getDefaultValue() != null && !StringUtils.isWhitespace(this.column.getDefaultValue().toString())) {
               ps.setInt(param, Integer.valueOf(this.column.getDefaultValue().toString()));
            } else {
               ps.setNull(param, this.getJDBCType());
            }
         } else if (value instanceof Character) {
            String s = value.toString();
            ps.setInt(param, s.charAt(0));
         } else if (value instanceof String) {
            String s = (String)value;
            ps.setInt(param, s.charAt(0));
         } else if (value instanceof Long) {
            ps.setLong(param, (Long)value);
         } else {
            ps.setLong(param, ((Number)value).longValue());
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         long i = rs.getLong(param);
         Object value;
         if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_CHARACTER)) {
            value = rs.wasNull() ? null : (char)((int)i);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_STRING)) {
            value = rs.wasNull() ? null : Character.valueOf((char)((int)i)).toString();
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_LONG)) {
            value = rs.wasNull() ? null : i;
         } else {
            value = rs.wasNull() ? null : (int)i;
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
