package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.table.Table;

public class GregorianCalendarMapping extends SingleFieldMultiMapping {
   public void initialize(AbstractMemberMetaData fmd, Table table, ClassLoaderResolver clr) {
      super.initialize(fmd, table, clr);
      this.addColumns();
   }

   public void initialize(RDBMSStoreManager storeMgr, String type) {
      super.initialize(storeMgr, type);
      this.addColumns();
   }

   protected void addColumns() {
      boolean singleColumn = true;
      if (this.mmd != null) {
         ColumnMetaData[] colmds = this.mmd.getColumnMetaData();
         if (colmds != null && colmds.length == 2) {
            singleColumn = false;
         } else if (this.mmd.hasExtension("calendar-one-column") && this.mmd.getValueForExtension("calendar-one-column").equals("false")) {
            singleColumn = false;
         }
      }

      if (singleColumn) {
         this.addColumns(ClassNameConstants.JAVA_SQL_TIMESTAMP);
      } else {
         this.addColumns(ClassNameConstants.LONG);
         this.addColumns(ClassNameConstants.JAVA_LANG_STRING);
      }

   }

   public Class getJavaType() {
      return GregorianCalendar.class;
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      if (this.getNumberOfDatastoreMappings() == 1) {
         return ClassNameConstants.JAVA_SQL_TIMESTAMP;
      } else if (index == 0) {
         return ClassNameConstants.LONG;
      } else {
         return index == 1 ? ClassNameConstants.JAVA_LANG_STRING : null;
      }
   }

   public Object getValueForDatastoreMapping(NucleusContext nucleusCtx, int index, Object value) {
      if (this.getNumberOfDatastoreMappings() == 1) {
         return value;
      } else if (index == 0) {
         return ((Calendar)value).getTime().getTime();
      } else if (index == 1) {
         return ((Calendar)value).getTimeZone().getID();
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      GregorianCalendar cal = (GregorianCalendar)value;
      if (this.getNumberOfDatastoreMappings() == 1) {
         Timestamp ts = null;
         if (cal != null) {
            ts = new Timestamp(cal.getTimeInMillis());
         }

         this.getDatastoreMapping(0).setObject(ps, exprIndex[0], ts);
      } else if (cal == null) {
         this.getDatastoreMapping(0).setObject(ps, exprIndex[0], (Object)null);
         this.getDatastoreMapping(1).setObject(ps, exprIndex[1], (Object)null);
      } else {
         this.getDatastoreMapping(0).setLong(ps, exprIndex[0], cal.getTime().getTime());
         this.getDatastoreMapping(1).setString(ps, exprIndex[1], cal.getTimeZone().getID());
      }

   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      try {
         if (this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]) == null) {
            return null;
         }
      } catch (Exception var8) {
      }

      if (this.getNumberOfDatastoreMappings() == 1) {
         Date date = (Date)this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]);
         GregorianCalendar cal = new GregorianCalendar();
         cal.setTimeInMillis(date.getTime());
         String timezoneID = ec.getNucleusContext().getConfiguration().getStringProperty("datanucleus.ServerTimeZoneID");
         if (timezoneID != null) {
            cal.setTimeZone(TimeZone.getTimeZone(timezoneID));
         }

         return cal;
      } else {
         long millisecs = this.getDatastoreMapping(0).getLong(resultSet, exprIndex[0]);
         GregorianCalendar cal = new GregorianCalendar();
         cal.setTime(new Date(millisecs));
         String timezoneId = this.getDatastoreMapping(1).getString(resultSet, exprIndex[1]);
         if (timezoneId != null) {
            cal.setTimeZone(TimeZone.getTimeZone(timezoneId));
         }

         return cal;
      }
   }
}
