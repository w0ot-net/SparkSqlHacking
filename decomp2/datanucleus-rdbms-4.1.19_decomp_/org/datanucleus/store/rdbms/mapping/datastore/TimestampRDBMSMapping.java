package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.TypeConversionHelper;

public class TimestampRDBMSMapping extends AbstractDatastoreMapping {
   public TimestampRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
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
      return 93;
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         Calendar cal = this.storeMgr.getCalendarForDateTimezone();
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else if (value instanceof Timestamp) {
            if (cal != null) {
               ps.setTimestamp(param, (Timestamp)value, cal);
            } else {
               ps.setTimestamp(param, (Timestamp)value);
            }
         } else if (value instanceof Time) {
            if (cal != null) {
               ps.setTimestamp(param, new Timestamp(((Time)value).getTime()), cal);
            } else {
               ps.setTimestamp(param, new Timestamp(((Time)value).getTime()));
            }
         } else if (value instanceof Date) {
            if (cal != null) {
               ps.setTimestamp(param, new Timestamp(((Date)value).getTime()), cal);
            } else {
               ps.setTimestamp(param, new Timestamp(((Date)value).getTime()));
            }
         } else if (value instanceof Calendar) {
            if (cal != null) {
               ps.setTimestamp(param, new Timestamp(((Calendar)value).getTime().getTime()), cal);
            } else {
               ps.setTimestamp(param, new Timestamp(((Calendar)value).getTime().getTime()));
            }
         } else if (value instanceof java.util.Date) {
            if (cal != null) {
               ps.setTimestamp(param, new Timestamp(((java.util.Date)value).getTime()), cal);
            } else {
               ps.setTimestamp(param, new Timestamp(((java.util.Date)value).getTime()));
            }
         } else if (cal != null) {
            ps.setTimestamp(param, (Timestamp)value, cal);
         } else {
            ps.setTimestamp(param, (Timestamp)value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Timestamp", "" + value, this.column, e.getMessage()}), e);
      }
   }

   protected Timestamp getTimestamp(ResultSet rs, int param) {
      Calendar cal = this.storeMgr.getCalendarForDateTimezone();

      Timestamp value;
      try {
         if (cal != null) {
            value = rs.getTimestamp(param, cal);
         } else {
            value = rs.getTimestamp(param);
         }
      } catch (SQLException e) {
         try {
            String s = rs.getString(param);
            if (rs.wasNull()) {
               value = null;
            } else {
               value = s == null ? null : TypeConversionHelper.stringToTimestamp(s, cal);
            }
         } catch (SQLException nestedEx) {
            throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Timestamp", "" + param, this.column, e.getMessage()}), nestedEx);
         }
      }

      return value;
   }

   public Object getObject(ResultSet rs, int param) {
      Timestamp value = this.getTimestamp(rs, param);
      if (value == null) {
         return null;
      } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_UTIL_DATE)) {
         return new java.util.Date(this.getDatastoreAdapter().getAdapterTime(value));
      } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_SQL_DATE)) {
         return new Date(this.getDatastoreAdapter().getAdapterTime(value));
      } else {
         return this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_SQL_TIME) ? new Time(this.getDatastoreAdapter().getAdapterTime(value)) : value;
      }
   }
}
