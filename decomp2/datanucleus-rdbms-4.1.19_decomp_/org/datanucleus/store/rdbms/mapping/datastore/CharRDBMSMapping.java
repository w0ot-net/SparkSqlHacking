package org.datanucleus.store.rdbms.mapping.datastore;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NullValueException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.SingleFieldMapping;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class CharRDBMSMapping extends AbstractDatastoreMapping {
   private static final ThreadLocal formatterThreadInfo = new ThreadLocal() {
      protected FormatterInfo initialValue() {
         return new FormatterInfo();
      }
   };

   public CharRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   protected void initialize() {
      if (this.column != null) {
         if (this.getJavaTypeMapping() instanceof SingleFieldMapping && this.column.getColumnMetaData().getLength() == null) {
            SingleFieldMapping m = (SingleFieldMapping)this.getJavaTypeMapping();
            if (m.getDefaultLength(0) > 0) {
               this.column.getColumnMetaData().setLength(m.getDefaultLength(0));
            }
         }

         this.column.getColumnMetaData().setJdbcType("CHAR");
         this.column.checkString();
         if (this.getJavaTypeMapping() instanceof SingleFieldMapping) {
            Object[] validValues = ((SingleFieldMapping)this.getJavaTypeMapping()).getValidValues(0);
            if (validValues != null) {
               String constraints = this.getDatastoreAdapter().getCheckConstraintForValues(this.column.getIdentifier(), validValues, this.column.isNullable());
               this.column.setConstraints(constraints);
            }
         }

         if (this.getJavaTypeMapping().getJavaType() == Boolean.class) {
            this.column.getColumnMetaData().setLength(1);
            StringBuilder constraints = new StringBuilder("CHECK (" + this.column.getIdentifier() + " IN ('Y','N')");
            if (this.column.isNullable()) {
               constraints.append(" OR " + this.column.getIdentifier() + " IS NULL");
            }

            constraints.append(')');
            this.column.setConstraints(constraints.toString());
         }

         SQLTypeInfo typeInfo = this.getTypeInfo();
         int maxlength = typeInfo.getPrecision();
         if ((this.column.getColumnMetaData().getLength() <= 0 || this.column.getColumnMetaData().getLength() > maxlength) && typeInfo.isAllowsPrecisionSpec()) {
            throw new NucleusUserException("String max length of " + this.column.getColumnMetaData().getLength() + " is outside the acceptable range [0, " + maxlength + "] for column \"" + this.column.getIdentifier() + "\"");
         }
      }

      this.initTypeInfo();
   }

   public boolean isStringBased() {
      return true;
   }

   public int getJDBCType() {
      return 1;
   }

   public void setChar(PreparedStatement ps, int param, char value) {
      try {
         if (value == 0 && !this.getDatastoreAdapter().supportsOption("PersistOfUnassignedChar")) {
            value = ' ';
            NucleusLogger.DATASTORE.warn(Localiser.msg("055008"));
         }

         ps.setString(param, Character.valueOf(value).toString());
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"char", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public char getChar(ResultSet rs, int param) {
      try {
         String str = rs.getString(param);
         if (str == null) {
            return '\u0000';
         } else {
            char value = str.charAt(0);
            return value;
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"char", "" + param, this.column, e.getMessage()}), e);
      }
   }

   public void setString(PreparedStatement ps, int param, String value) {
      try {
         if (value == null) {
            if (this.column != null && this.column.isDefaultable() && this.column.getDefaultValue() != null) {
               ps.setString(param, this.column.getDefaultValue().toString().trim());
            } else {
               ps.setNull(param, this.getJDBCType());
            }
         } else if (value.length() == 0) {
            if (this.storeMgr.getBooleanProperty("datanucleus.rdbms.persistEmptyStringAsNull")) {
               ps.setString(param, (String)null);
            } else {
               if (this.getDatastoreAdapter().supportsOption("NullEqualsEmptyString")) {
                  value = this.getDatastoreAdapter().getSurrogateForEmptyStrings();
               }

               ps.setString(param, value);
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

            ps.setString(param, value);
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public String getString(ResultSet rs, int param) {
      try {
         String value = rs.getString(param);
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
         ps.setString(param, value ? "Y" : "N");
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"boolean", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public boolean getBoolean(ResultSet rs, int param) {
      try {
         String s = rs.getString(param);
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
            ps.setString(param, (Boolean)value ? "Y" : "N");
         } else if (value instanceof Time) {
            ps.setString(param, ((Time)value).toString());
         } else if (value instanceof Date) {
            ps.setString(param, ((Date)value).toString());
         } else if (value instanceof Timestamp) {
            ps.setString(param, ((Timestamp)value).toString());
         } else if (value instanceof java.util.Date) {
            ps.setString(param, this.getJavaUtilDateFormat().format((java.util.Date)value));
         } else if (value instanceof String) {
            ps.setString(param, (String)value);
         } else {
            ps.setString(param, value.toString());
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"Object", "" + value, this.column, e.getMessage()}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      try {
         String s = rs.getString(param);
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
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_SQL_TIMESTAMP)) {
            value = Timestamp.valueOf(s);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_SQL_DATE)) {
            value = Date.valueOf(s);
         } else if (this.getJavaTypeMapping().getJavaType().getName().equals(ClassNameConstants.JAVA_UTIL_DATE)) {
            value = this.getJavaUtilDateFormat().parse(s);
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

   public SimpleDateFormat getJavaUtilDateFormat() {
      FormatterInfo formatInfo = (FormatterInfo)formatterThreadInfo.get();
      if (formatInfo.formatter == null) {
         Calendar cal = this.storeMgr.getCalendarForDateTimezone();
         formatInfo.formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
         if (cal != null) {
            formatInfo.formatter.setTimeZone(cal.getTimeZone());
         }
      }

      return formatInfo.formatter;
   }

   static class FormatterInfo {
      SimpleDateFormat formatter;
   }
}
