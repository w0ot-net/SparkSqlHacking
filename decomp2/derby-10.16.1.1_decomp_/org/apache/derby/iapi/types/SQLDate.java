package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public final class SQLDate extends DataType implements DateTimeDataValue {
   private int encodedDate;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(SQLDate.class);
   static final char ISO_SEPARATOR = '-';
   private static final char[] ISO_SEPARATOR_ONLY = new char[]{'-'};
   private static final char IBM_USA_SEPARATOR = '/';
   private static final char[] IBM_USA_SEPARATOR_ONLY = new char[]{'/'};
   private static final char IBM_EUR_SEPARATOR = '.';
   private static final char[] IBM_EUR_SEPARATOR_ONLY = new char[]{'.'};
   private static final char[] END_OF_STRING = new char[]{'\u0000'};

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }

   int getEncodedDate() {
      return this.encodedDate;
   }

   public String getString() {
      return !this.isNull() ? encodedDateToString(this.encodedDate) : null;
   }

   public Timestamp getTimestamp(Calendar var1) {
      return this.isNull() ? null : new Timestamp(this.getTimeInMillis(var1));
   }

   private long getTimeInMillis(Calendar var1) {
      if (var1 == null) {
         var1 = new GregorianCalendar();
      }

      ((Calendar)var1).clear();
      setDateInCalendar((Calendar)var1, this.encodedDate);
      return ((Calendar)var1).getTimeInMillis();
   }

   static void setDateInCalendar(Calendar var0, int var1) {
      var0.set(getYear(var1), getMonth(var1) - 1, getDay(var1));
   }

   public Object getObject() {
      return this.getDate((Calendar)null);
   }

   public int getLength() {
      return 4;
   }

   public String getTypeName() {
      return "DATE";
   }

   public int getTypeFormatId() {
      return 298;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.encodedDate);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.encodedDate = var1.readInt();
   }

   public DataValueDescriptor cloneValue(boolean var1) {
      return new SQLDate(this.encodedDate);
   }

   public DataValueDescriptor getNewNull() {
      return new SQLDate();
   }

   public void restoreToNull() {
      this.encodedDate = 0;
   }

   public void setValueFromResultSet(ResultSet var1, int var2, boolean var3) throws SQLException, StandardException {
      this.setValue(var1.getDate(var2), (Calendar)null);
   }

   public int compare(DataValueDescriptor var1) throws StandardException {
      if (this.typePrecedence() < var1.typePrecedence()) {
         return -var1.compare(this);
      } else {
         boolean var2 = this.isNull();
         boolean var3 = var1.isNull();
         if (!var2 && !var3) {
            int var5 = 0;
            if (var1 instanceof SQLDate) {
               var5 = ((SQLDate)var1).encodedDate;
            } else {
               var5 = computeEncodedDate((Date)var1.getDate(new GregorianCalendar()));
            }

            byte var4;
            if (this.encodedDate > var5) {
               var4 = 1;
            } else if (this.encodedDate < var5) {
               var4 = -1;
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

   public SQLDate() {
   }

   public SQLDate(java.sql.Date var1) throws StandardException {
      this.parseDate(var1);
   }

   private void parseDate(Date var1) throws StandardException {
      this.encodedDate = computeEncodedDate(var1);
   }

   private SQLDate(int var1) {
      this.encodedDate = var1;
   }

   public SQLDate(String var1, boolean var2, LocaleFinder var3) throws StandardException {
      this.parseDate(var1, var2, var3, (Calendar)null);
   }

   public SQLDate(String var1, boolean var2, LocaleFinder var3, Calendar var4) throws StandardException {
      this.parseDate(var1, var2, var3, var4);
   }

   private void parseDate(String var1, boolean var2, LocaleFinder var3, Calendar var4) throws StandardException {
      boolean var5 = true;
      DateTimeParser var6 = new DateTimeParser(var1);
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;
      StandardException var10 = null;

      try {
         switch (var6.nextSeparator()) {
            case '-':
               this.encodedDate = SQLTimestamp.parseDateOrTimestamp(var6, false)[0];
               return;
            case '.':
               if (var2) {
                  var5 = false;
               } else {
                  var9 = var6.parseInt(2, true, IBM_EUR_SEPARATOR_ONLY, false);
                  var8 = var6.parseInt(2, true, IBM_EUR_SEPARATOR_ONLY, false);
                  var7 = var6.parseInt(4, false, END_OF_STRING, false);
               }
               break;
            case '/':
               if (var2) {
                  var5 = false;
               } else {
                  var8 = var6.parseInt(2, true, IBM_USA_SEPARATOR_ONLY, false);
                  var9 = var6.parseInt(2, true, IBM_USA_SEPARATOR_ONLY, false);
                  var7 = var6.parseInt(4, false, END_OF_STRING, false);
               }
               break;
            default:
               var5 = false;
         }
      } catch (StandardException var14) {
         var5 = false;
         var10 = var14;
      }

      if (var5) {
         this.encodedDate = computeEncodedDate(var7, var8, var9);
      } else {
         var1 = StringUtil.trimTrailing(var1);
         Object var11 = null;
         DateFormat var18;
         if (var3 == null) {
            var18 = DateFormat.getDateInstance();
         } else if (var4 == null) {
            var18 = var3.getDateFormat();
         } else {
            var18 = (DateFormat)var3.getDateFormat().clone();
         }

         if (var4 != null) {
            var18.setCalendar(var4);
         }

         try {
            this.encodedDate = computeEncodedDate(var18.parse(var1), var4);
         } catch (ParseException var16) {
            try {
               this.encodedDate = SQLTimestamp.parseLocalTimestamp(var1, var3, var4)[0];
            } catch (ParseException var15) {
               if (var10 != null) {
                  throw var10;
               }

               throw StandardException.newException("22007.S.181", new Object[0]);
            }
         }
      }

   }

   void setObject(Object var1) throws StandardException {
      this.setValue((java.sql.Date)var1);
   }

   protected void setFrom(DataValueDescriptor var1) throws StandardException {
      if (var1 instanceof SQLDate) {
         this.restoreToNull();
         this.encodedDate = ((SQLDate)var1).encodedDate;
      } else {
         GregorianCalendar var2 = new GregorianCalendar();
         this.setValue((java.sql.Date)var1.getDate(var2), var2);
      }

   }

   public void setValue(java.sql.Date var1, Calendar var2) throws StandardException {
      this.restoreToNull();
      this.encodedDate = computeEncodedDate(var1, var2);
   }

   public void setValue(Timestamp var1, Calendar var2) throws StandardException {
      this.restoreToNull();
      this.encodedDate = computeEncodedDate(var1, var2);
   }

   public void setValue(String var1) throws StandardException {
      this.restoreToNull();
      if (var1 != null) {
         DatabaseContext var2 = (DatabaseContext)DataValueFactoryImpl.getContext("Database");
         this.parseDate(var1, false, var2 == null ? null : var2.getDatabase(), (Calendar)null);
      }

   }

   NumberDataValue nullValueInt() {
      return new SQLInteger();
   }

   public NumberDataValue getYear(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : setSource(getYear(this.encodedDate), var1);
   }

   public NumberDataValue getMonth(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : setSource(getMonth(this.encodedDate), var1);
   }

   public NumberDataValue getDate(NumberDataValue var1) throws StandardException {
      return this.isNull() ? this.nullValueInt() : setSource(getDay(this.encodedDate), var1);
   }

   public NumberDataValue getHours(NumberDataValue var1) throws StandardException {
      throw StandardException.newException("42X25", new Object[]{"getHours", "Date"});
   }

   public NumberDataValue getMinutes(NumberDataValue var1) throws StandardException {
      throw StandardException.newException("42X25", new Object[]{"getMinutes", "Date"});
   }

   public NumberDataValue getSeconds(NumberDataValue var1) throws StandardException {
      throw StandardException.newException("42X25", new Object[]{"getSeconds", "Date"});
   }

   public String toString() {
      return this.isNull() ? "NULL" : this.getDate((Calendar)null).toString();
   }

   public int hashCode() {
      return this.encodedDate;
   }

   public int typePrecedence() {
      return 100;
   }

   public final boolean isNull() {
      return this.encodedDate == 0;
   }

   public java.sql.Date getDate(Calendar var1) {
      return this.isNull() ? null : new java.sql.Date(this.getTimeInMillis(var1));
   }

   static int getYear(int var0) {
      return var0 >>> 16;
   }

   static int getMonth(int var0) {
      return var0 >>> 8 & 255;
   }

   static int getDay(int var0) {
      return var0 & 255;
   }

   static int computeEncodedDate(Calendar var0) throws StandardException {
      return computeEncodedDate(var0.get(1), var0.get(2) + 1, var0.get(5));
   }

   static int computeEncodedDate(int var0, int var1, int var2) throws StandardException {
      int var3 = 31;
      switch (var1) {
         case 2:
            var3 = var0 % 4 != 0 || var0 % 100 == 0 && var0 % 400 != 0 ? 28 : 29;
         case 3:
         case 5:
         case 7:
         case 8:
         case 10:
         default:
            break;
         case 4:
         case 6:
         case 9:
         case 11:
            var3 = 30;
      }

      if (var0 >= 1 && var0 <= 9999 && var1 >= 1 && var1 <= 12 && var2 >= 1 && var2 <= var3) {
         return (var0 << 16) + (var1 << 8) + var2;
      } else {
         throw StandardException.newException("22007.S.180", new Object[0]);
      }
   }

   static void dateToString(int var0, int var1, int var2, StringBuffer var3) {
      String var4 = Integer.toString(var0);

      for(int var5 = var4.length(); var5 < 4; ++var5) {
         var3.append('0');
      }

      var3.append(var4);
      var3.append('-');
      String var7 = Integer.toString(var1);
      String var6 = Integer.toString(var2);
      if (var7.length() == 1) {
         var3.append('0');
      }

      var3.append(var7);
      var3.append('-');
      if (var6.length() == 1) {
         var3.append('0');
      }

      var3.append(var6);
   }

   static String encodedDateToString(int var0) {
      StringBuffer var1 = new StringBuffer();
      dateToString(getYear(var0), getMonth(var0), getDay(var0), var1);
      return var1.toString();
   }

   static NumberDataValue setSource(int var0, NumberDataValue var1) throws StandardException {
      if (var1 == null) {
         var1 = new SQLInteger();
      }

      ((NumberDataValue)var1).setValue(var0);
      return (NumberDataValue)var1;
   }

   private static int computeEncodedDate(Date var0) throws StandardException {
      return computeEncodedDate(var0, (Calendar)null);
   }

   static int computeEncodedDate(Date var0, Calendar var1) throws StandardException {
      if (var0 == null) {
         return 0;
      } else {
         if (var1 == null) {
            var1 = new GregorianCalendar();
         }

         ((Calendar)var1).setTime(var0);
         return computeEncodedDate((Calendar)var1);
      }
   }

   public static DateTimeDataValue computeDateFunction(DataValueDescriptor var0, DataValueFactory var1) throws StandardException {
      try {
         if (var0.isNull()) {
            return new SQLDate();
         } else if (var0 instanceof SQLDate) {
            return (SQLDate)var0.cloneValue(false);
         } else if (var0 instanceof SQLTimestamp) {
            SQLDate var9 = new SQLDate();
            var9.setValue(var0);
            return var9;
         } else if (var0 instanceof NumberDataValue) {
            int var8 = var0.getInt();
            if (var8 > 0 && var8 <= 3652059) {
               GregorianCalendar var10 = new GregorianCalendar(1970, 0, 1, 12, 0, 0);
               ((Calendar)var10).add(5, var8 - 1);
               return new SQLDate(computeEncodedDate(((Calendar)var10).get(1), ((Calendar)var10).get(2) + 1, ((Calendar)var10).get(5)));
            } else {
               throw StandardException.newException("22008.S", new Object[]{var0.getString(), "date"});
            }
         } else {
            String var2 = var0.getString();
            if (var2.length() == 7) {
               int var3 = SQLTimestamp.parseDateTimeInteger(var2, 0, 4);
               int var4 = SQLTimestamp.parseDateTimeInteger(var2, 4, 3);
               if (var4 >= 1 && var4 <= 366) {
                  GregorianCalendar var5 = new GregorianCalendar(var3, 0, 1, 2, 0, 0);
                  ((Calendar)var5).add(6, var4 - 1);
                  int var6 = ((Calendar)var5).get(1);
                  if (var6 != var3) {
                     throw StandardException.newException("22008.S", new Object[]{var0.getString(), "date"});
                  } else {
                     return new SQLDate(computeEncodedDate(var3, ((Calendar)var5).get(2) + 1, ((Calendar)var5).get(5)));
                  }
               } else {
                  throw StandardException.newException("22008.S", new Object[]{var0.getString(), "date"});
               }
            } else {
               return var1.getDateValue(var2, false);
            }
         }
      } catch (StandardException var7) {
         if ("22007.S.181".startsWith(var7.getSQLState())) {
            throw StandardException.newException("22008.S", new Object[]{var0.getString(), "date"});
         } else {
            throw var7;
         }
      }
   }

   public void setInto(PreparedStatement var1, int var2) throws SQLException, StandardException {
      var1.setDate(var2, this.getDate((Calendar)null));
   }

   public DateTimeDataValue timestampAdd(int var1, NumberDataValue var2, java.sql.Date var3, DateTimeDataValue var4) throws StandardException {
      return this.toTimestamp().timestampAdd(var1, var2, var3, var4);
   }

   private SQLTimestamp toTimestamp() throws StandardException {
      return new SQLTimestamp(this.getEncodedDate(), 0, 0);
   }

   public NumberDataValue timestampDiff(int var1, DateTimeDataValue var2, java.sql.Date var3, NumberDataValue var4) throws StandardException {
      return this.toTimestamp().timestampDiff(var1, var2, var3, var4);
   }
}
