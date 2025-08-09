package org.datanucleus.store.rdbms.sql.expression;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.mapping.datastore.CharRDBMSMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.types.converters.TypeConverter;

public class TemporalLiteral extends TemporalExpression implements SQLLiteral {
   private final Date value;
   private String jdbcEscapeValue;

   public TemporalLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else if (value instanceof Date) {
         this.value = (Date)value;
      } else if (value instanceof Calendar) {
         this.value = ((Calendar)value).getTime();
      } else if (value instanceof String) {
         this.value = null;
         this.jdbcEscapeValue = (String)value;
      } else {
         Class type = value.getClass();
         if (mapping != null) {
            type = mapping.getJavaType();
         }

         TypeConverter converter = stmt.getRDBMSManager().getNucleusContext().getTypeManager().getTypeConverterForType(type, Time.class);
         if (converter != null) {
            this.value = (Time)converter.toDatastoreType(value);
         } else {
            converter = stmt.getRDBMSManager().getNucleusContext().getTypeManager().getTypeConverterForType(type, java.sql.Date.class);
            if (converter != null) {
               this.value = (java.sql.Date)converter.toDatastoreType(value);
            } else {
               converter = stmt.getRDBMSManager().getNucleusContext().getTypeManager().getTypeConverterForType(type, Timestamp.class);
               if (converter != null) {
                  this.value = (Timestamp)converter.toDatastoreType(value);
               } else {
                  converter = stmt.getRDBMSManager().getNucleusContext().getTypeManager().getTypeConverterForType(type, Date.class);
                  if (converter == null) {
                     throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
                  }

                  this.value = (Date)converter.toDatastoreType(value);
               }
            }
         }
      }

      if (parameterName != null) {
         this.st.appendParameter(parameterName, mapping, this.value);
      } else {
         this.setStatement();
      }

   }

   public String toString() {
      if (this.jdbcEscapeValue != null) {
         return this.jdbcEscapeValue;
      } else {
         return this.value == null ? "null" : this.value.toString();
      }
   }

   public SQLExpression invoke(String methodName, List args) {
      if (this.jdbcEscapeValue != null) {
         throw new NucleusUserException("Cannot invoke methods on TemporalLiteral using JDBC escape syntax - not supported");
      } else {
         if (this.parameterName == null) {
            if (methodName.equals("getDay")) {
               Calendar cal = Calendar.getInstance();
               cal.setTime(this.value);
               JavaTypeMapping m = this.stmt.getRDBMSManager().getMappingManager().getMapping(Integer.class);
               return new IntegerLiteral(this.stmt, m, cal.get(5), (String)null);
            }

            if (methodName.equals("getMonth")) {
               Calendar cal = Calendar.getInstance();
               cal.setTime(this.value);
               JavaTypeMapping m = this.stmt.getRDBMSManager().getMappingManager().getMapping(Integer.class);
               return new IntegerLiteral(this.stmt, m, cal.get(2), (String)null);
            }

            if (methodName.equals("getYear")) {
               Calendar cal = Calendar.getInstance();
               cal.setTime(this.value);
               JavaTypeMapping m = this.stmt.getRDBMSManager().getMappingManager().getMapping(Integer.class);
               return new IntegerLiteral(this.stmt, m, cal.get(1), (String)null);
            }

            if (methodName.equals("getHour")) {
               Calendar cal = Calendar.getInstance();
               cal.setTime(this.value);
               JavaTypeMapping m = this.stmt.getRDBMSManager().getMappingManager().getMapping(Integer.class);
               return new IntegerLiteral(this.stmt, m, cal.get(11), (String)null);
            }

            if (methodName.equals("getMinutes")) {
               Calendar cal = Calendar.getInstance();
               cal.setTime(this.value);
               JavaTypeMapping m = this.stmt.getRDBMSManager().getMappingManager().getMapping(Integer.class);
               return new IntegerLiteral(this.stmt, m, cal.get(12), (String)null);
            }

            if (methodName.equals("getSeconds")) {
               Calendar cal = Calendar.getInstance();
               cal.setTime(this.value);
               JavaTypeMapping m = this.stmt.getRDBMSManager().getMappingManager().getMapping(Integer.class);
               return new IntegerLiteral(this.stmt, m, cal.get(13), (String)null);
            }
         }

         return super.invoke(methodName, args);
      }
   }

   public Object getValue() {
      return this.jdbcEscapeValue != null ? this.jdbcEscapeValue : this.value;
   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
         this.st.clearStatement();
         this.setStatement();
      }
   }

   protected void setStatement() {
      if (this.jdbcEscapeValue != null) {
         this.st.append(this.jdbcEscapeValue);
      } else {
         String formatted;
         if (!(this.value instanceof Time) && !(this.value instanceof java.sql.Date) && !(this.value instanceof Timestamp)) {
            if (this.mapping.getDatastoreMapping(0) instanceof CharRDBMSMapping) {
               SimpleDateFormat fmt = ((CharRDBMSMapping)this.mapping.getDatastoreMapping(0)).getJavaUtilDateFormat();
               formatted = fmt.format(this.value);
            } else {
               formatted = (new Timestamp(this.value.getTime())).toString();
            }
         } else {
            formatted = this.value.toString();
         }

         this.st.append('\'').append(formatted).append('\'');
      }

   }
}
