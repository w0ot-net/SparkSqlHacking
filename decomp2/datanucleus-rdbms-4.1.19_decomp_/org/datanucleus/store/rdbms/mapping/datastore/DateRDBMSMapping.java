package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class DateRDBMSMapping extends AbstractDatastoreMapping {
   public DateRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
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

   public int getJDBCType() {
      return 91;
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else if (value instanceof Calendar) {
            ps.setDate(param, new Date(((Calendar)value).getTime().getTime()));
         } else if (value instanceof Date) {
            ps.setDate(param, (Date)value);
         } else if (value instanceof java.util.Date) {
            ps.setDate(param, new Date(((java.util.Date)value).getTime()));
         } else {
            ps.setDate(param, (Date)value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"java.sql.Date", "" + value}), e);
      }
   }

   protected Date getDate(ResultSet rs, int param) {
      try {
         return rs.getDate(param);
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"java.sql.Date", "" + param}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      Date value = this.getDate(rs, param);
      if (value == null) {
         return null;
      } else {
         return this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_UTIL_DATE) ? new java.util.Date(value.getTime()) : value;
      }
   }
}
