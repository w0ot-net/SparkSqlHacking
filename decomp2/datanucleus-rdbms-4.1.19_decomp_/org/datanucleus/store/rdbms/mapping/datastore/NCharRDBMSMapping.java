package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NullValueException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.TypeConversionHelper;

public class NCharRDBMSMapping extends CharRDBMSMapping {
   public NCharRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
   }

   public int getJDBCType() {
      return -15;
   }

   public void setChar(PreparedStatement ps, int param, char value) {
      try {
         if (value == 0 && !this.getDatastoreAdapter().supportsOption("PersistOfUnassignedChar")) {
            value = ' ';
            NucleusLogger.DATASTORE.warn(Localiser.msg("055008"));
         }

         ps.setNString(param, Character.valueOf(value).toString());
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"char", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public char getChar(ResultSet rs, int param) {
      try {
         char value = rs.getNString(param).charAt(0);
         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"char", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setString(PreparedStatement ps, int param, String value) {
      try {
         if (value == null) {
            if (this.column != null && this.column.isDefaultable() && this.column.getDefaultValue() != null) {
               ps.setNString(param, this.column.getDefaultValue().toString().trim());
            } else {
               ps.setNull(param, this.getJDBCType());
            }
         } else if (value.length() == 0) {
            if (this.storeMgr.getBooleanProperty("datanucleus.rdbms.persistEmptyStringAsNull")) {
               ps.setNString(param, (String)null);
            } else {
               if (this.getDatastoreAdapter().supportsOption("NullEqualsEmptyString")) {
                  value = this.getDatastoreAdapter().getSurrogateForEmptyStrings();
               }

               ps.setNString(param, value);
            }
         } else {
            if (this.column != null) {
               Integer colLength = this.column.getColumnMetaData().getLength();
               if (colLength != null && colLength < value.length()) {
                  String action = this.storeMgr.getStringProperty("datanucleus.rdbms.stringLengthExceededAction");
                  if (action.equals("EXCEPTION")) {
                     throw (new NucleusUserException(Localiser.msg("055007", new Object[]{value, this.column.getIdentifier().toString(), "" + colLength}))).setFatal();
                  }

                  if (action.equals("TRUNCATE")) {
                     value = value.substring(0, colLength);
                  }
               }
            }

            ps.setNString(param, value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public String getString(ResultSet rs, int param) {
      try {
         String value = rs.getNString(param);
         if (value == null) {
            return value;
         } else if (this.getDatastoreAdapter().supportsOption("NullEqualsEmptyString") && value.equals(this.getDatastoreAdapter().getSurrogateForEmptyStrings())) {
            return "";
         } else {
            if (this.column.getJdbcType() == JdbcType.CHAR && this.getDatastoreAdapter().supportsOption("CharColumnsPaddedWithSpaces")) {
               int numPaddingChars = 0;

               for(int i = value.length() - 1; i >= 0 && value.charAt(i) == ' '; --i) {
                  ++numPaddingChars;
               }

               if (numPaddingChars > 0) {
                  value = value.substring(0, value.length() - numPaddingChars);
               }
            }

            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setBoolean(PreparedStatement ps, int param, boolean value) {
      try {
         ps.setNString(param, value ? "Y" : "N");
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"boolean", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public boolean getBoolean(ResultSet rs, int param) {
      try {
         String s = rs.getNString(param);
         if (s != null) {
            boolean value;
            if (s.equals("Y")) {
               value = true;
            } else {
               if (!s.equals("N")) {
                  throw new NucleusDataStoreException(Localiser.msg("055003", new Object[]{this.column}));
               }

               value = false;
            }

            return value;
         } else if ((this.column == null || this.column.getColumnMetaData() == null || !this.column.getColumnMetaData().isAllowsNull()) && rs.wasNull()) {
            throw new NullValueException(Localiser.msg("055003", new Object[]{this.column}));
         } else {
            return false;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"boolean", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setObject(PreparedStatement ps, int param, Object value) {
      try {
         if (value == null) {
            ps.setNull(param, this.getJDBCType());
         } else if (value instanceof Boolean) {
            ps.setNString(param, (Boolean)value ? "Y" : "N");
         } else if (value instanceof Time) {
            ps.setNString(param, ((Time)value).toString());
         } else if (value instanceof Date) {
            ps.setNString(param, ((Date)value).toString());
         } else if (value instanceof java.util.Date) {
            ps.setNString(param, this.getJavaUtilDateFormat().format((java.util.Date)value));
         } else if (value instanceof Timestamp) {
            Calendar cal = this.storeMgr.getCalendarForDateTimezone();
            if (cal != null) {
               ps.setTimestamp(param, (Timestamp)value, cal);
            } else {
               ps.setTimestamp(param, (Timestamp)value);
            }
         } else if (value instanceof String) {
            ps.setNString(param, (String)value);
         } else {
            ps.setNString(param, value.toString());
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         String s = rs.getNString(param);
         Object value;
         if (s == null) {
            value = null;
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_BOOLEAN)) {
            if (s.equals("Y")) {
               value = Boolean.TRUE;
            } else {
               if (!s.equals("N")) {
                  throw new NucleusDataStoreException(Localiser.msg("055003", new Object[]{this.column}));
               }

               value = Boolean.FALSE;
            }
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_LANG_CHARACTER)) {
            value = s.charAt(0);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_SQL_TIME)) {
            value = Time.valueOf(s);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_SQL_DATE)) {
            value = Date.valueOf(s);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_UTIL_DATE)) {
            value = this.getJavaUtilDateFormat().parse(s);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_SQL_TIMESTAMP)) {
            Calendar cal = this.storeMgr.getCalendarForDateTimezone();
            value = TypeConversionHelper.stringToTimestamp(s, cal);
         } else {
            value = s;
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      } catch (ParseException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, e.getMessage()}), e);
      }
   }
}
