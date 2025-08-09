package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.derby.iapi.db.DatabaseContext;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.LocaleFinder;

public final class SQLTimestamp extends DataType implements DateTimeDataValue {
   static final int MAX_FRACTION_DIGITS = 9;
   static final int FRACTION_TO_NANO = 1;
   static final int ONE_BILLION = 1000000000;
   private int encodedDate;
   private int encodedTime;
   private int nanos;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLTimestamp.class);
   static final char DATE_SEPARATOR = '-';
   private static final char[] DATE_SEPARATORS = new char[]{'-'};
   private static final char IBM_DATE_TIME_SEPARATOR = '-';
   private static final char ODBC_DATE_TIME_SEPARATOR = ' ';
   private static final char[] DATE_TIME_SEPARATORS = new char[]{'-', ' '};
   private static final char[] DATE_TIME_SEPARATORS_OR_END = new char[]{'-', ' ', '\u0000'};
   private static final char IBM_TIME_SEPARATOR = '.';
   private static final char ODBC_TIME_SEPARATOR = ':';
   private static final char[] TIME_SEPARATORS = new char[]{'.', ':'};
   private static final char[] TIME_SEPARATORS_OR_END = new char[]{'.', ':', '\u0000'};
   private static final char[] END_OF_STRING = new char[]{'\u0000'};

   public int estimateMemoryUsage() {
      int var1 = BASE_MEMORY_USAGE;
      return var1;
   }

   public String getString() {
      if (this.isNull()) {
         return null;
      } else {
         String var1 = this.getTimestamp((Calendar)null).toString();
         int var2 = var1.indexOf(45);
         if (var2 >= 0 && var2 < 4) {
            StringBuffer var3;
            for(var3 = new StringBuffer(); var2 < 4; ++var2) {
               var3.append('0');
            }

            var3.append(var1);
            var1 = var3.toString();
         }

         return var1;
      }
   }

   public Date getDate(Calendar var1) throws StandardException {
      if (this.isNull()) {
         return null;
      } else {
         if (var1 == null) {
            var1 = new GregorianCalendar();
         }

         ((Calendar)var1).clear();
         SQLDate.setDateInCalendar((Calendar)var1, this.encodedDate);
         return new Date(((Calendar)var1).getTimeInMillis());
      }
   }

   public Time getTime(Calendar var1) throws StandardException {
      return this.isNull() ? null : SQLTime.getTime(var1, this.encodedTime, this.nanos);
   }

   public Object getObject() {
      return this.getTimestamp((Calendar)null);
   }

   public int getLength() {
      return 12;
   }

   public String getTypeName() {
      return "TIMESTAMP";
   }

   public int getTypeFormatId() {
      return 31;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.encodedDate);
      var1.writeInt(this.encodedTime);
      var1.writeInt(this.nanos);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.encodedDate = var1.readInt();
      this.encodedTime = var1.readInt();
      this.nanos = var1.readInt();
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new SQLTimestamp(this.encodedDate, this.encodedTime, this.nanos);
   }

   public DataValueDescriptor getNewNull() {
      return new SQLTimestamp();
   }

   public void restoreToNull() {
      this.encodedDate = 0;
      this.encodedTime = 0;
      this.nanos = 0;
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException, StandardException {
      this.setValue(var1.getTimestamp(var2), (Calendar)null);
   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      if (this.typePrecedence() < var1.typePrecedence()) {
         return -var1.compare(this);
      } else {
         boolean var2 = this.isNull();
         boolean var3 = var1.isNull();
         if (!var2 && !var3) {
            int var5 = 0;
            int var6 = 0;
            int var7 = 0;
            if (var1 instanceof SQLTimestamp) {
               SQLTimestamp var8 = (SQLTimestamp)var1;
               var5 = var8.encodedDate;
               var6 = var8.encodedTime;
               var7 = var8.nanos;
            } else {
               GregorianCalendar var13 = new GregorianCalendar();
               Timestamp var9 = var1.getTimestamp(var13);
               var5 = computeEncodedDate(var9, var13);
               var6 = computeEncodedTime(var9, var13);
               var7 = var9.getNanos();
            }

            byte var4;
            if (this.encodedDate < var5) {
               var4 = -1;
            } else if (this.encodedDate > var5) {
               var4 = 1;
            } else if (this.encodedTime < var6) {
               var4 = -1;
            } else if (this.encodedTime > var6) {
               var4 = 1;
            } else if (this.nanos < var7) {
               var4 = -1;
            } else if (this.nanos > var7) {
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

   public SQLTimestamp() {
   }

   public SQLTimestamp(Timestamp var1, Calendar var2) throws StandardException {
      this.setValue(var1, var2);
   }

   public SQLTimestamp(Timestamp var1) throws StandardException {
      this(var1, (Calendar)null);
   }

   SQLTimestamp(int var1, int var2, int var3) {
      this.encodedDate = var1;
      this.encodedTime = var2;
      this.nanos = var3;
   }

   public SQLTimestamp(DataValueDescriptor var1, DataValueDescriptor var2) throws StandardException {
      GregorianCalendar var3 = null;
      if (var1 != null && !var1.isNull() && var2 != null && !var2.isNull()) {
         if (var1 instanceof SQLDate) {
            SQLDate var4 = (SQLDate)var1;
            this.encodedDate = var4.getEncodedDate();
         } else {
            var3 = new GregorianCalendar();
            this.encodedDate = computeEncodedDate(var1.getDate(var3), var3);
         }

         if (var2 instanceof SQLTime) {
            SQLTime var5 = (SQLTime)var2;
            this.encodedTime = var5.getEncodedTime();
         } else {
            if (var3 == null) {
               var3 = new GregorianCalendar();
            }

            this.encodedTime = computeEncodedTime(var2.getTime(var3), var3);
         }

      }
   }

   public SQLTimestamp(String var1, boolean var2, LocaleFinder var3) throws StandardException {
      this.parseTimestamp(var1, var2, var3, (Calendar)null);
   }

   public SQLTimestamp(String var1, boolean var2, LocaleFinder var3, Calendar var4) throws StandardException {
      this.parseTimestamp(var1, var2, var3, var4);
   }

   private void parseTimestamp(String var1, boolean var2, LocaleFinder var3, Calendar var4) throws StandardException {
      Object var5 = null;
      DateTimeParser var6 = new DateTimeParser(var1);

      try {
         int[] var12 = parseDateOrTimestamp(var6, true);
         this.encodedDate = var12[0];
         this.encodedTime = var12[1];
         this.nanos = var12[2];
      } catch (StandardException var10) {
         try {
            var1 = StringUtil.trimTrailing(var1);
            int[] var7 = parseLocalTimestamp(var1, var3, var4);
            this.encodedDate = var7[0];
            this.encodedTime = var7[1];
            return;
         } catch (ParseException var8) {
         } catch (StandardException var9) {
         }

         if (var10 != null) {
            throw var10;
         } else {
            throw StandardException.newException("22007.S.181", new Object[0]);
         }
      }
   }

   static int[] parseLocalTimestamp(String var0, LocaleFinder var1, Calendar var2) throws StandardException, ParseException {
      Object var3 = null;
      DateFormat var5;
      if (var1 == null) {
         var5 = DateFormat.getDateTimeInstance();
      } else if (var2 == null) {
         var5 = var1.getTimestampFormat();
      } else {
         var5 = (DateFormat)var1.getTimestampFormat().clone();
      }

      if (var2 == null) {
         var2 = new GregorianCalendar();
      } else {
         var5.setCalendar((Calendar)var2);
      }

      java.util.Date var4 = var5.parse(var0);
      return new int[]{computeEncodedDate(var4, (Calendar)var2), computeEncodedTime(var4, (Calendar)var2)};
   }

   static int[] parseDateOrTimestamp(DateTimeParser var0, boolean var1) throws StandardException {
      int var2 = var0.parseInt(4, false, DATE_SEPARATORS, false);
      int var3 = var0.parseInt(2, true, DATE_SEPARATORS, false);
      int var4 = var0.parseInt(2, true, var1 ? DATE_TIME_SEPARATORS : DATE_TIME_SEPARATORS_OR_END, false);
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      if (var0.getCurrentSeparator() != 0) {
         int var9 = var0.getCurrentSeparator() == ' ' ? 58 : 46;
         var5 = var0.parseInt(2, true, TIME_SEPARATORS, false);
         if (var9 == var0.getCurrentSeparator()) {
            var6 = var0.parseInt(2, false, TIME_SEPARATORS, false);
            if (var9 == var0.getCurrentSeparator()) {
               var7 = var0.parseInt(2, false, TIME_SEPARATORS_OR_END, false);
               if (var0.getCurrentSeparator() == '.') {
                  var8 = var0.parseInt(9, true, END_OF_STRING, true) * 1;
               }
            }
         }
      }

      var0.checkEnd();
      return new int[]{SQLDate.computeEncodedDate(var2, var3, var4), SQLTime.computeEncodedTime(var5, var6, var7), var8};
   }

   void setObject(Object var1) throws StandardException {
      this.setValue((Timestamp)var1);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      if (var1 instanceof SQLTimestamp) {
         this.restoreToNull();
         SQLTimestamp var2 = (SQLTimestamp)var1;
         this.encodedDate = var2.encodedDate;
         this.encodedTime = var2.encodedTime;
         this.nanos = var2.nanos;
      } else {
         GregorianCalendar var3 = new GregorianCalendar();
         this.setValue((Timestamp)var1.getTimestamp(var3), var3);
      }

   }

   public void setValue(Date var1, Calendar var2) throws StandardException {
      this.restoreToNull();
      if (var1 != null) {
         if (var2 == null) {
            var2 = new GregorianCalendar();
         }

         this.encodedDate = computeEncodedDate(var1, (Calendar)var2);
      }

   }

   public void setValue(Timestamp var1, Calendar var2) throws StandardException {
      this.restoreToNull();
      this.setNumericTimestamp(var1, var2);
   }

   public void setValue(String var1) throws StandardException {
      this.restoreToNull();
      if (var1 != null) {
         DatabaseContext var2 = (DatabaseContext)DataValueFactoryImpl.getContext("Database");
         this.parseTimestamp(var1, false, var2 == null ? null : var2.getDatabase(), (Calendar)null);
      }

   }

   NumberDataValue nullValueInt() {
      return new SQLInteger();
   }

   NumberDataValue nullValueDouble() {
      return new SQLDouble();
   }

   public NumberDataValue getYear(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : SQLDate.setSource(SQLDate.getYear(this.encodedDate), var1);
   }

   public NumberDataValue getMonth(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : SQLDate.setSource(SQLDate.getMonth(this.encodedDate), var1);
   }

   public NumberDataValue getDate(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : SQLDate.setSource(SQLDate.getDay(this.encodedDate), var1);
   }

   public NumberDataValue getHours(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : SQLDate.setSource(SQLTime.getHour(this.encodedTime), var1);
   }

   public NumberDataValue getMinutes(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : SQLDate.setSource(SQLTime.getMinute(this.encodedTime), var1);
   }

   public NumberDataValue getSeconds(NumberDataValue var1) throws StandardException {
      if (this.isNull()) {
         return this.nullValueDouble();
      } else {
         Object var2;
         if (var1 != null) {
            var2 = var1;
         } else {
            var2 = new SQLDouble();
         }

         ((NumberDataValue)var2).setValue((double)SQLTime.getSecond(this.encodedTime) + (double)this.nanos / (double)1.0E9F);
         return (NumberDataValue)var2;
      }
   }

   public String toString() {
      return this.isNull() ? "NULL" : this.getTimestamp((Calendar)null).toString();
   }

   public int hashCode() {
      return this.isNull() ? 0 : this.encodedDate + this.encodedTime + this.nanos;
   }

   public int typePrecedence() {
      return 110;
   }

   public final boolean isNull() {
      return this.encodedDate == 0;
   }

   public Timestamp getTimestamp(Calendar var1) {
      if (this.isNull()) {
         return null;
      } else {
         if (var1 == null) {
            var1 = new GregorianCalendar();
         }

         this.setCalendar((Calendar)var1);
         Timestamp var2 = new Timestamp(((Calendar)var1).getTimeInMillis());
         var2.setNanos(this.nanos);
         return var2;
      }
   }

   private void setCalendar(Calendar var1) {
      var1.clear();
      SQLDate.setDateInCalendar(var1, this.encodedDate);
      SQLTime.setTimeInCalendar(var1, this.encodedTime);
      var1.set(14, 0);
   }

   private void setNumericTimestamp(Timestamp var1, Calendar var2) throws StandardException {
      if (var1 != null) {
         if (var2 == null) {
            var2 = new GregorianCalendar();
         }

         this.encodedDate = computeEncodedDate(var1, (Calendar)var2);
         this.encodedTime = computeEncodedTime(var1, (Calendar)var2);
         this.nanos = var1.getNanos();
      }

   }

   private static int computeEncodedDate(java.util.Date var0, Calendar var1) throws StandardException {
      if (var0 == null) {
         return 0;
      } else {
         var1.setTime(var0);
         return SQLDate.computeEncodedDate(var1);
      }
   }

   private static int computeEncodedTime(java.util.Date var0, Calendar var1) throws StandardException {
      var1.setTime(var0);
      return SQLTime.computeEncodedTime(var1);
   }

   public void setInto(PreparedStatement var1, int var2) throws SQLException, StandardException {
      var1.setTimestamp(var2, this.getTimestamp((Calendar)null));
   }

   public static DateTimeDataValue computeTimestampFunction(DataValueDescriptor var0, DataValueFactory var1) throws StandardException {
      try {
         if (var0.isNull()) {
            return new SQLTimestamp();
         } else if (var0 instanceof SQLTimestamp) {
            return (SQLTimestamp)var0.cloneValue(false);
         } else {
            String var2 = var0.getString();
            if (var2.length() == 14) {
               int var3 = parseDateTimeInteger(var2, 0, 4);
               int var4 = parseDateTimeInteger(var2, 4, 2);
               int var5 = parseDateTimeInteger(var2, 6, 2);
               int var6 = parseDateTimeInteger(var2, 8, 2);
               int var7 = parseDateTimeInteger(var2, 10, 2);
               int var8 = parseDateTimeInteger(var2, 12, 2);
               return new SQLTimestamp(SQLDate.computeEncodedDate(var3, var4, var5), SQLTime.computeEncodedTime(var6, var7, var8), 0);
            } else {
               return var1.getTimestampValue(var2, false);
            }
         }
      } catch (StandardException var9) {
         if ("22007.S.181".startsWith(var9.getSQLState())) {
            throw StandardException.newException("22008.S", new Object[]{var0.getString(), "timestamp"});
         } else {
            throw var9;
         }
      }
   }

   static int parseDateTimeInteger(String var0, int var1, int var2) throws StandardException {
      int var3 = var1 + var2;
      int var4 = 0;

      for(int var5 = var1; var5 < var3; ++var5) {
         char var6 = var0.charAt(var5);
         if (!Character.isDigit(var6)) {
            throw StandardException.newException("22007.S.181", new Object[0]);
         }

         var4 = 10 * var4 + Character.digit(var6, 10);
      }

      return var4;
   }

   public DateTimeDataValue timestampAdd(int var1, NumberDataValue var2, Date var3, DateTimeDataValue var4) throws StandardException {
      if (var4 == null) {
         var4 = new SQLTimestamp();
      }

      SQLTimestamp var5 = (SQLTimestamp)var4;
      if (!this.isNull() && !var2.isNull()) {
         var5.setFrom(this);
         int var6 = var2.getInt();
         switch (var1) {
            case 0:
               long var7 = (long)(this.nanos + var6);
               if (var7 >= 0L && var7 < 1000000000L) {
                  var5.nanos = (int)var7;
               } else {
                  int var9 = (int)(var7 / 1000000000L);
                  if (var7 >= 0L) {
                     var5.nanos = (int)(var7 % 1000000000L);
                  } else {
                     --var9;
                     var7 -= (long)var9 * 1000000000L;
                     var5.nanos = (int)var7;
                  }

                  this.addInternal(13, var9, var5);
               }
               break;
            case 1:
               this.addInternal(13, var6, var5);
               break;
            case 2:
               this.addInternal(12, var6, var5);
               break;
            case 3:
               this.addInternal(10, var6, var5);
               break;
            case 4:
               this.addInternal(5, var6, var5);
               break;
            case 5:
               this.addInternal(5, var6 * 7, var5);
               break;
            case 6:
               this.addInternal(2, var6, var5);
               break;
            case 7:
               this.addInternal(2, var6 * 3, var5);
               break;
            case 8:
               this.addInternal(1, var6, var5);
               break;
            default:
               throw StandardException.newException("22008.S", new Object[]{var1, "TIMESTAMPADD"});
         }

         return var5;
      } else {
         var5.restoreToNull();
         return (DateTimeDataValue)var4;
      }
   }

   private void addInternal(int var1, int var2, SQLTimestamp var3) throws StandardException {
      GregorianCalendar var4 = new GregorianCalendar();
      this.setCalendar(var4);

      try {
         ((Calendar)var4).add(var1, var2);
         var3.encodedTime = SQLTime.computeEncodedTime((Calendar)var4);
         var3.encodedDate = SQLDate.computeEncodedDate((Calendar)var4);
      } catch (StandardException var7) {
         String var6 = var7.getSQLState();
         if (var6 != null && var6.length() > 0 && "22007.S.180".startsWith(var6)) {
            throw StandardException.newException("22003", new Object[]{"TIMESTAMP"});
         } else {
            throw var7;
         }
      }
   }

   public NumberDataValue timestampDiff(int var1, DateTimeDataValue var2, Date var3, NumberDataValue var4) throws StandardException {
      if (var4 == null) {
         var4 = new SQLLongint();
      }

      if (!this.isNull() && !var2.isNull()) {
         SQLTimestamp var5 = promote(var2, var3);
         GregorianCalendar var6 = new GregorianCalendar();
         this.setCalendar(var6);
         long var7 = ((Calendar)var6).getTime().getTime() / 1000L;
         var5.setCalendar(var6);
         long var9 = ((Calendar)var6).getTime().getTime() / 1000L;
         long var11 = var7 - var9;
         int var13 = this.nanos - var5.nanos;
         if (var13 < 0 && var11 > 0L) {
            --var11;
            var13 += 1000000000;
         } else if (var13 > 0 && var11 < 0L) {
            ++var11;
            var13 -= 1000000000;
         }

         long var14 = 0L;
         switch (var1) {
            case 0:
               var14 = var11 * 1000000000L + (long)var13;
               break;
            case 1:
               var14 = var11;
               break;
            case 2:
               var14 = var11 / 60L;
               break;
            case 3:
               var14 = var11 / 3600L;
               break;
            case 4:
               var14 = var11 / 86400L;
               break;
            case 5:
               var14 = var11 / 604800L;
               break;
            case 6:
            case 7:
               if (Math.abs(var11) > 31622400L) {
                  var14 = 12L * (var11 / 31622400L);
               } else {
                  var14 = var11 / 2678400L;
               }

               if (var11 >= 0L) {
                  if (var14 >= 2147483647L) {
                     throw StandardException.newException("22003", new Object[]{"INTEGER"});
                  }

                  ((Calendar)var6).add(2, (int)(var14 + 1L));

                  while(((Calendar)var6).getTime().getTime() / 1000L <= var7) {
                     ((Calendar)var6).add(2, 1);
                     ++var14;
                  }
               } else {
                  if (var14 <= -2147483648L) {
                     throw StandardException.newException("22003", new Object[]{"INTEGER"});
                  }

                  ((Calendar)var6).add(2, (int)(var14 - 1L));

                  while(((Calendar)var6).getTime().getTime() / 1000L >= var7) {
                     ((Calendar)var6).add(2, -1);
                     --var14;
                  }
               }

               if (var1 == 7) {
                  var14 /= 3L;
               }
               break;
            case 8:
               var14 = var11 / 31622400L;
               if (var11 >= 0L) {
                  if (var14 >= 2147483647L) {
                     throw StandardException.newException("22003", new Object[]{"INTEGER"});
                  }

                  ((Calendar)var6).add(1, (int)(var14 + 1L));

                  while(((Calendar)var6).getTime().getTime() / 1000L <= var7) {
                     ((Calendar)var6).add(1, 1);
                     ++var14;
                  }
               } else {
                  if (var14 <= -2147483648L) {
                     throw StandardException.newException("22003", new Object[]{"INTEGER"});
                  }

                  ((Calendar)var6).add(1, (int)(var14 - 1L));

                  while(((Calendar)var6).getTime().getTime() / 1000L >= var7) {
                     ((Calendar)var6).add(1, -1);
                     --var14;
                  }
               }
               break;
            default:
               throw StandardException.newException("22008.S", new Object[]{var1, "TIMESTAMPDIFF"});
         }

         ((NumberDataValue)var4).setValue(var14);
         return (NumberDataValue)var4;
      } else {
         ((NumberDataValue)var4).setToNull();
         return (NumberDataValue)var4;
      }
   }

   static SQLTimestamp promote(DateTimeDataValue var0, Date var1) throws StandardException {
      if (var0 instanceof SQLTimestamp) {
         return (SQLTimestamp)var0;
      } else if (var0 instanceof SQLTime) {
         return new SQLTimestamp(SQLDate.computeEncodedDate(var1, (Calendar)null), ((SQLTime)var0).getEncodedTime(), 0);
      } else {
         return var0 instanceof SQLDate ? new SQLTimestamp(((SQLDate)var0).getEncodedDate(), 0, 0) : new SQLTimestamp(var0.getTimestamp(new GregorianCalendar()));
      }
   }
}
