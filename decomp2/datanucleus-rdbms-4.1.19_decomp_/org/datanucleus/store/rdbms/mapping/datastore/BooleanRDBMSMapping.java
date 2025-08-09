package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class BooleanRDBMSMapping extends AbstractDatastoreMapping {
   public BooleanRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      this.initTypeInfo();
   }

   public boolean isBooleanBased() {
      return true;
   }

   public int getJDBCType() {
      return 16;
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
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Boolean", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setString(PreparedStatement ps, int param, String value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else {
            ps.setBoolean(param, value.equals("Y"));
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public String getString(ResultSet rs, int param) {
      try {
         String value = rs.getBoolean(param) ? "Y" : "N";
         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"String", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else if (value instanceof String) {
            ps.setBoolean(param, value.equals("Y"));
         } else {
            if (!(value instanceof Boolean)) {
               throw new NucleusUserException(Localiser.msg("055004", new Object[]{value, this.column}));
            }

            ps.setBoolean(param, (Boolean)value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         boolean b = rs.getBoolean(param);
         Object value;
         if (rs.wasNull()) {
            value = null;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_STRING)) {
            value = b ? "Y" : "N";
         } else {
            value = b ? Boolean.TRUE : Boolean.FALSE;
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
