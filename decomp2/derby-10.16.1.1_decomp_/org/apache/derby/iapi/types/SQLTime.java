package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.derby.iapi.db.DatabaseContext;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.LocaleFinder;

public final class SQLTime extends DataType implements DateTimeDataValue {
   private int encodedTime;
   private int encodedTimeFraction;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLTime.class);
   private static final char IBM_EUR_SEPARATOR = '.';
   private static final char[] IBM_EUR_SEPARATOR_OR_END = new char[]{'.', '\u0000'};
   static final char JIS_SEPARATOR = ':';
   private static final char[] US_OR_JIS_MINUTE_END = new char[]{':', ' ', '\u0000'};
   private static final char[] ANY_SEPARATOR = new char[]{'.', ':', ' '};
   private static final String[] AM_PM = new String[]{"AM", "PM"};
   private static final char[] END_OF_STRING = new char[]{'\u0000'};

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }

   public String getString() {
      return !this.isNull() ? encodedTimeToString(this.encodedTime) : null;
   }

   int getEncodedTime() {
      return this.encodedTime;
   }

   public Timestamp getTimestamp(Calendar var1) {
      if (this.isNull()) {
         return null;
      } else {
         if (var1 == null) {
            var1 = new GregorianCalendar();
         } else {
            ((Calendar)var1).clear();
            ((Calendar)var1).setTimeInMillis(System.currentTimeMillis());
         }

         setTimeInCalendar((Calendar)var1, this.encodedTime);
         ((Calendar)var1).set(14, 0);
         return new Timestamp(((Calendar)var1).getTimeInMillis());
      }
   }

   public Object getObject() {
      return this.getTime((Calendar)null);
   }

   public int getLength() {
      return 8;
   }

   public String getTypeName() {
      return "TIME";
   }

   public int getTypeFormatId() {
      return 299;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.encodedTime);
      var1.writeInt(this.encodedTimeFraction);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.encodedTime = var1.readInt();
      this.encodedTimeFraction = var1.readInt();
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new SQLTime(this.encodedTime, this.encodedTimeFraction);
   }

   public DataValueDescriptor getNewNull() {
      return new SQLTime();
   }

   public void restoreToNull() {
      this.encodedTime = -1;
      this.encodedTimeFraction = 0;
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException, StandardException {
      this.restoreToNull();
      this.encodedTime = this.computeEncodedTime((Date)var1.getTime(var2));
   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      if (this.typePrecedence() < var1.typePrecedence()) {
         return -var1.compare(this);
      } else {
         boolean var2 = this.isNull();
         boolean var3 = var1.isNull();
         if (!var2 && !var3) {
            int var5 = 0;
            if (var1 instanceof SQLTime) {
               var5 = ((SQLTime)var1).encodedTime;
            } else {
               var5 = this.computeEncodedTime((Date)var1.getTime((Calendar)null));
            }

            byte var4;
            if (this.encodedTime < var5) {
               var4 = -1;
            } else if (this.encodedTime > var5) {
               var4 = 1;
            } else {
               var4 = 0;
            }

            return var4;
         } else if (!var2) {
            return -1;
         } else {
            return !var3 ? 1 : 0;
         }
      }
   }

   public boolean compare(int var1, DataValueDescriptor var2, boolean var3, boolean var4) throws StandardException {
      return var3 || !this.isNull() && !var2.isNull() ? super.compare(var1, var2, var3, var4) : var4;
   }

   public SQLTime() {
      this.encodedTime = -1;
   }

   public SQLTime(Time var1) throws StandardException {
      this.parseTime(var1);
   }

   private void parseTime(Date var1) throws StandardException {
      this.encodedTime = this.computeEncodedTime(var1);
   }

   private SQLTime(int var1, int var2) {
      this.encodedTime = var1;
      this.encodedTimeFraction = var2;
   }

   public SQLTime(String var1, boolean var2, LocaleFinder var3) throws StandardException {
      this.parseTime(var1, var2, var3, (Calendar)null);
   }

   public SQLTime(String var1, boolean var2, LocaleFinder var3, Calendar var4) throws StandardException {
      this.parseTime(var1, var2, var3, var4);
   }

   private void parseTime(String var1, boolean var2, LocaleFinder var3, Calendar var4) throws StandardException {
      boolean var5 = true;
      DateTimeParser var6 = new DateTimeParser(var1);
      StandardException var7 = null;
      int var8 = 0;
      int var9 = 0;
      int var10 = 0;
      int var11 = -1;

      try {
         if (var6.nextSeparator() == '-') {
            this.encodedTime = SQLTimestamp.parseDateOrTimestamp(var6, true)[1];
            return;
         }

         var8 = var6.parseInt(2, true, ANY_SEPARATOR, false);
         label74:
         switch (var6.getCurrentSeparator()) {
            case ' ':
               if (var2) {
                  var5 = false;
               } else {
                  var11 = var6.parseChoice(AM_PM);
               }
               break;
            case '.':
               if (var2) {
                  var5 = false;
               } else {
                  var9 = var6.parseInt(2, false, IBM_EUR_SEPARATOR_OR_END, false);
                  if (var6.getCurrentSeparator() == '.') {
                     var10 = var6.parseInt(2, false, END_OF_STRING, false);
                  }
               }
               break;
            case ':':
               var9 = var6.parseInt(2, false, US_OR_JIS_MINUTE_END, false);
               switch (var6.getCurrentSeparator()) {
                  case ' ':
                     if (var2) {
                        var5 = false;
                     } else {
                        var11 = var6.parseChoice(AM_PM);
                        var6.checkEnd();
                     }
                     break label74;
                  case ':':
                     var10 = var6.parseInt(2, false, END_OF_STRING, false);
                  default:
                     break label74;
               }
            default:
               var5 = false;
         }
      } catch (StandardException var15) {
         var5 = false;
         var7 = var15;
      }

      if (var5) {
         if (var11 == 0) {
            if (var8 == 12) {
               if (var9 == 0 && var10 == 0) {
                  var8 = 24;
               } else {
                  var8 = 0;
               }
            } else if (var8 > 12) {
               throw StandardException.newException("22007.S.180", new Object[0]);
            }
         } else if (var11 == 1) {
            if (var8 < 12) {
               var8 += 12;
            } else if (var8 > 12) {
               throw StandardException.newException("22007.S.180", new Object[0]);
            }
         }

         var6.checkEnd();
         this.encodedTime = computeEncodedTime(var8, var9, var10);
      } else {
         var1 = StringUtil.trimTrailing(var1);
         Object var12 = null;
         DateFormat var19;
         if (var3 == null) {
            var19 = DateFormat.getTimeInstance();
         } else if (var4 == null) {
            var19 = var3.getTimeFormat();
         } else {
            var19 = (DateFormat)var3.getTimeFormat().clone();
         }

         if (var4 != null) {
            var19.setCalendar(var4);
         }

         try {
            this.encodedTime = computeEncodedTime(var19.parse(var1), var4);
         } catch (ParseException var17) {
            try {
               this.encodedTime = SQLTimestamp.parseLocalTimestamp(var1, var3, var4)[1];
            } catch (ParseException var16) {
               if (var7 != null) {
                  throw var7;
               }

               throw StandardException.newException("22007.S.181", new Object[0]);
            }
         }
      }

   }

   void setObject(Object var1) throws StandardException {
      this.setValue((Time)var1);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      if (var1 instanceof SQLTime) {
         this.restoreToNull();
         SQLTime var2 = (SQLTime)var1;
         this.encodedTime = var2.encodedTime;
         this.encodedTimeFraction = var2.encodedTimeFraction;
      } else {
         GregorianCalendar var3 = new GregorianCalendar();
         this.setValue((Time)var1.getTime(var3), var3);
      }

   }

   public void setValue(Time var1, Calendar var2) throws StandardException {
      this.restoreToNull();
      this.encodedTime = computeEncodedTime(var1, var2);
   }

   public void setValue(Timestamp var1, Calendar var2) throws StandardException {
      this.restoreToNull();
      this.encodedTime = computeEncodedTime(var1, var2);
   }

   public void setValue(String var1) throws StandardException {
      this.restoreToNull();
      if (var1 != null) {
         DatabaseContext var2 = (DatabaseContext)DataValueFactoryImpl.getContext("Database");
         this.parseTime(var1, false, var2 == null ? null : var2.getDatabase(), (Calendar)null);
      }

   }

   NumberDataValue nullValueInt() {
      return new SQLInteger();
   }

   public NumberDataValue getYear(NumberDataValue var1) throws StandardException {
      throw StandardException.newException("42X25", new Object[]{"getYear", "Time"});
   }

   public NumberDataValue getMonth(NumberDataValue var1) throws StandardException {
      throw StandardException.newException("42X25", new Object[]{"getMonth", "Time"});
   }

   public NumberDataValue getDate(NumberDataValue var1) throws StandardException {
      throw StandardException.newException("42X25", new Object[]{"getDate", "Time"});
   }

   public NumberDataValue getHours(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : SQLDate.setSource(getHour(this.encodedTime), var1);
   }

   public NumberDataValue getMinutes(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : SQLDate.setSource(getMinute(this.encodedTime), var1);
   }

   public NumberDataValue getSeconds(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : SQLDate.setSource(getSecond(this.encodedTime), var1);
   }

   public String toString() {
      return this.isNull() ? "NULL" : this.getTime((Calendar)null).toString();
   }

   public int hashCode() {
      return this.isNull() ? 0 : this.encodedTime + this.encodedTimeFraction + 1;
   }

   public int typePrecedence() {
      return 120;
   }

   public final boolean isNull() {
      return this.encodedTime == -1;
   }

   public Time getTime(Calendar var1) {
      return this.isNull() ? null : getTime(var1, this.encodedTime, 0);
   }

   static void setTimeInCalendar(Calendar var0, int var1) {
      var0.set(11, getHour(var1));
      var0.set(12, getMinute(var1));
      var0.set(13, getSecond(var1));
   }

   static Time getTime(Calendar var0, int var1, int var2) {
      if (var0 == null) {
         var0 = new GregorianCalendar();
      }

      ((Calendar)var0).clear();
      ((Calendar)var0).set(1970, 0, 1);
      setTimeInCalendar((Calendar)var0, var1);
      ((Calendar)var0).set(14, var2 / 1000000);
      return new Time(((Calendar)var0).getTimeInMillis());
   }

   protected static int getHour(int var0) {
      return var0 >>> 16 & 255;
   }

   protected static int getMinute(int var0) {
      return var0 >>> 8 & 255;
   }

   protected static int getSecond(int var0) {
      return var0 & 255;
   }

   static int computeEncodedTime(Calendar var0) throws StandardException {
      return computeEncodedTime(var0.get(11), var0.get(12), var0.get(13));
   }

   static int computeEncodedTime(int var0, int var1, int var2) throws StandardException {
      if (var0 == 24) {
         if (var1 != 0 || var2 != 0) {
            throw StandardException.newException("22007.S.180", new Object[0]);
         }
      } else if (var0 < 0 || var0 > 23 || var1 < 0 || var1 > 59 || var2 < 0 || var2 > 59) {
         throw StandardException.newException("22007.S.180", new Object[0]);
      }

      return (var0 << 16) + (var1 << 8) + var2;
   }

   static void timeToString(int var0, int var1, int var2, StringBuffer var3) {
      String var4 = Integer.toString(var0);
      String var5 = Integer.toString(var1);
      String var6 = Integer.toString(var2);
      if (var4.length() == 1) {
         var3.append("0");
      }

      var3.append(var4);
      var3.append(':');
      if (var5.length() == 1) {
         var3.append("0");
      }

      var3.append(var5);
      var3.append(':');
      if (var6.length() == 1) {
         var3.append("0");
      }

      var3.append(var6);
   }

   protected static String encodedTimeToString(int var0) {
      StringBuffer var1 = new StringBuffer();
      timeToString(getHour(var0), getMinute(var0), getSecond(var0), var1);
      return var1.toString();
   }

   private int computeEncodedTime(Date var1) throws StandardException {
      return computeEncodedTime(var1, (Calendar)null);
   }

   static int computeEncodedTime(Date var0, Calendar var1) throws StandardException {
      if (var0 == null) {
         return -1;
      } else {
         if (var1 == null) {
            var1 = new GregorianCalendar();
         }

         ((Calendar)var1).setTime(var0);
         return computeEncodedTime((Calendar)var1);
      }
   }

   public void setInto(PreparedStatement var1, int var2) throws SQLException, StandardException {
      var1.setTime(var2, this.getTime((Calendar)null));
   }

   public DateTimeDataValue timestampAdd(int var1, NumberDataValue var2, java.sql.Date var3, DateTimeDataValue var4) throws StandardException {
      return this.toTimestamp(var3).timestampAdd(var1, var2, var3, var4);
   }

   private SQLTimestamp toTimestamp(java.sql.Date var1) throws StandardException {
      return new SQLTimestamp(SQLDate.computeEncodedDate(var1, (Calendar)null), this.getEncodedTime(), 0);
   }

   public NumberDataValue timestampDiff(int var1, DateTimeDataValue var2, java.sql.Date var3, NumberDataValue var4) throws StandardException {
      return this.toTimestamp(var3).timestampDiff(var1, var2, var3, var4);
   }
}
