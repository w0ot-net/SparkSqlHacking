package org.apache.derby.iapi.tools.i18n;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import org.apache.derby.shared.common.i18n.MessageService;

public final class LocalizedResource {
   private ResourceBundle res;
   private Locale locale;
   private String encode;
   public static final String MESSAGE_FILE = "org.apache.derby.loc.tools.toolsmessages";
   public static final String SYSINFO_MESSAGE_FILE = "org.apache.derby.loc.tools.sysinfoMessages";
   public static final String ENV_CODESET = "derby.ui.codeset";
   public static final String ENV_LOCALE = "derby.ui.locale";
   private String messageFileName;
   private String resourceKey;
   private LocalizedOutput out;
   private LocalizedInput in;
   private boolean enableLocalized;
   private static LocalizedResource local;
   private int dateSize;
   private int timeSize;
   private int timestampSize;
   private DateFormat formatDate;
   private DateFormat formatTime;
   private DateFormat formatTimestamp;
   private NumberFormat formatNumber;
   private DecimalFormat formatDecimal;

   private LocalizedResource() {
      this.init();
   }

   public LocalizedResource(String var1) {
      this.init((String)null, (String)null, var1, true);
   }

   public LocalizedResource(String var1, String var2, String var3) {
      this.init(var1, var2, var3, false);
   }

   public static LocalizedResource getInstance() {
      if (local == null) {
         local = new LocalizedResource();
      }

      return local;
   }

   public static void resetLocalizedResourceCache() {
      local = null;
   }

   public void init() {
      this.init((String)null, (String)null, (String)null);
   }

   public void init(String var1, String var2, String var3) {
      this.init(var1, var2, var3, true);
   }

   private void init(String var1, String var2, String var3, boolean var4) {
      if (var1 != null) {
         this.encode = var1;
      }

      if (this.encode == null && var4) {
         String var5 = this.getEnvProperty("derby.ui.codeset");
         if (var5 != null) {
            this.encode = var5;
         }
      }

      this.locale = this.getNewLocale(var2);
      if (this.locale == null && var4) {
         String var6 = this.getEnvProperty("derby.ui.locale");
         this.locale = this.getNewLocale(var6);
      }

      if (this.locale == null) {
         this.locale = Locale.getDefault();
      }

      if (var3 != null) {
         this.messageFileName = var3;
      } else {
         this.messageFileName = "org.apache.derby.loc.tools.toolsmessages";
      }

      this.out = this.getNewOutput(System.out);
      this.in = this.getNewInput(System.in);
      if (this.enableLocalized && this.locale != null) {
         this.formatDecimal = (DecimalFormat)DecimalFormat.getInstance(this.locale);
         this.formatNumber = NumberFormat.getInstance(this.locale);
         this.formatDate = DateFormat.getDateInstance(1, this.locale);
         this.formatTime = DateFormat.getTimeInstance(1, this.locale);
         this.formatTimestamp = DateFormat.getDateTimeInstance(1, 1, this.locale);
      } else {
         this.formatDecimal = (DecimalFormat)DecimalFormat.getInstance();
         this.formatNumber = NumberFormat.getInstance();
         this.formatDate = DateFormat.getDateInstance(1);
         this.formatTime = DateFormat.getTimeInstance(1);
         this.formatTimestamp = DateFormat.getDateTimeInstance(1, 1);
      }

      this.initMaxSizes2();
   }

   private void setResource() {
      if (this.res == null) {
         this.res = MessageService.getBundleWithEnDefault(this.messageFileName, this.locale);
      }
   }

   private void initMaxSizes2() {
      this.dateSize = 0;
      this.timeSize = 0;
      this.timestampSize = 0;
      Date var2 = new Date(60907276800000L);
      Timestamp var3 = new Timestamp(var2.getTime());
      int var4 = 0;

      while(var4 <= 11) {
         int var1 = this.getDateAsString(var2).length();
         if (var1 > this.dateSize) {
            this.dateSize = var1;
         }

         var3.setTime(var2.getTime() + 79199L);
         var1 = this.getTimestampAsString(var3).length();
         if (var1 > this.timestampSize) {
            this.timestampSize = var1;
         }

         ++var4;
         var2.setTime(var2.getTime() + 2592000000L);
      }

      int var12 = 18;

      for(int var13 = 0; var13 < 24; ++var13) {
         long var5 = (long)var13 * 3600L + 3540L + 59L;
         long var7 = var5 * 1000L;
         Date var9 = new Date(var7);
         String var10 = this.formatTime.format(var9);
         if (var10.length() > var12) {
            var12 = var10.length();
         }
      }

      this.timeSize = var12;
   }

   public LocalizedInput getNewInput(InputStream var1) {
      try {
         if (this.encode != null) {
            return new LocalizedInput(var1, this.encode);
         }
      } catch (UnsupportedEncodingException var3) {
      }

      return new LocalizedInput(var1);
   }

   public LocalizedInput getNewEncodedInput(InputStream var1, String var2) {
      try {
         return new LocalizedInput(var1, var2);
      } catch (UnsupportedEncodingException var4) {
         return new LocalizedInput(var1);
      }
   }

   public LocalizedOutput getNewOutput(OutputStream var1) {
      try {
         if (this.encode != null) {
            return new LocalizedOutput(var1, this.encode);
         }
      } catch (UnsupportedEncodingException var3) {
      }

      return new LocalizedOutput(var1);
   }

   public LocalizedOutput getNewEncodedOutput(OutputStream var1, String var2) throws UnsupportedEncodingException {
      this.out = new LocalizedOutput(var1, var2);
      return this.out;
   }

   private Locale getNewLocale(String var1) {
      String var2 = "";
      String var3 = "";
      String var4 = "";
      if (var1 == null) {
         return null;
      } else {
         StringTokenizer var5 = new StringTokenizer(var1, "_");

         try {
            var2 = var5.nextToken();
            if (var5.hasMoreTokens()) {
               var3 = var5.nextToken();
            }

            if (var5.hasMoreTokens()) {
               var4 = var5.nextToken();
            }

            return new Locale(var2, var3, var4);
         } catch (Exception var7) {
            return null;
         }
      }
   }

   public String getTextMessage(String var1, Object... var2) {
      if (this.res == null) {
         this.setResource();
      }

      try {
         return MessageFormat.format(this.res.getString(var1), var2);
      } catch (Exception var6) {
         String var4 = var1;

         for(int var5 = 0; var2 != null && var5 < var2.length; ++var5) {
            var4 = var4 + ", <{" + var5 + "}>";
         }

         return MessageFormat.format(var4, var2);
      }
   }

   public String getLocalizedString(ResultSet var1, ResultSetMetaData var2, int var3) throws SQLException {
      if (!this.enableLocalized) {
         return var1.getString(var3);
      } else {
         int var4 = var2.getColumnType(var3);
         if (var4 == 91) {
            return this.getDateAsString(var1.getDate(var3));
         } else if (var4 != 4 && var4 != 5 && var4 != -5 && var4 != -6) {
            if (var4 != 7 && var4 != 6 && var4 != 8) {
               if (var4 != 2 && var4 != 3) {
                  if (var4 == 92) {
                     return this.getTimeAsString(var1.getTime(var3));
                  } else {
                     return var4 == 93 ? this.getTimestampAsString(var1.getTimestamp(var3)) : var1.getString(var3);
                  }
               } else {
                  return this.getNumberAsString(var1.getBigDecimal(var3));
               }
            } else {
               return this.getNumberAsString(var1.getDouble(var3));
            }
         } else {
            return this.getNumberAsString(var1.getLong(var3));
         }
      }
   }

   public String getDateAsString(Date var1) {
      return !this.enableLocalized ? var1.toString() : this.formatDate.format(var1);
   }

   public String getTimeAsString(Date var1) {
      return !this.enableLocalized ? var1.toString() : this.formatTime.format(var1, new StringBuffer(), new FieldPosition(0)).toString();
   }

   public String getNumberAsString(int var1) {
      return this.enableLocalized ? this.formatNumber.format((long)var1) : String.valueOf(var1);
   }

   public String getNumberAsString(long var1) {
      return this.enableLocalized ? this.formatNumber.format(var1) : String.valueOf(var1);
   }

   public String getNumberAsString(Object var1) {
      return this.enableLocalized ? this.formatNumber.format(var1, new StringBuffer(), new FieldPosition(0)).toString() : var1.toString();
   }

   public String getNumberAsString(double var1) {
      return !this.enableLocalized ? String.valueOf(var1) : this.formatDecimal.format(var1);
   }

   public String getTimestampAsString(Timestamp var1) {
      return !this.enableLocalized ? var1.toString() : this.formatTimestamp.format(var1, new StringBuffer(), new FieldPosition(0)).toString();
   }

   public int getColumnDisplaySize(ResultSetMetaData var1, int var2) throws SQLException {
      if (!this.enableLocalized) {
         return var1.getColumnDisplaySize(var2);
      } else {
         int var3 = var1.getColumnType(var2);
         if (var3 == 91) {
            return this.dateSize;
         } else if (var3 == 92) {
            return this.timeSize;
         } else {
            return var3 == 93 ? this.timestampSize : var1.getColumnDisplaySize(var2);
         }
      }
   }

   public String getStringFromDate(String var1) throws ParseException {
      if (!this.enableLocalized) {
         return var1;
      } else {
         Date var2 = this.formatDate.parse(var1);
         return (new java.sql.Date(var2.getTime())).toString();
      }
   }

   public String getStringFromTime(String var1) throws ParseException {
      if (!this.enableLocalized) {
         return var1;
      } else {
         Date var2 = this.formatTime.parse(var1);
         return (new Time(var2.getTime())).toString();
      }
   }

   public String getStringFromValue(String var1) throws ParseException {
      return !this.enableLocalized ? var1 : this.formatNumber.parse(var1).toString();
   }

   public String getStringFromTimestamp(String var1) throws ParseException {
      if (!this.enableLocalized) {
         return var1;
      } else {
         Date var2 = this.formatTimestamp.parse(var1);
         return (new Timestamp(var2.getTime())).toString();
      }
   }

   public Locale getLocale() {
      return this.locale;
   }

   private final synchronized String getEnvProperty(String var1) {
      this.resourceKey = var1;
      String var2 = this.run();
      return var2;
   }

   public final String run() {
      String var1 = System.getProperty(this.resourceKey);
      return var1;
   }

   public static boolean enableLocalization(boolean var0) {
      getInstance().enableLocalized = var0;
      getInstance().init();
      return var0;
   }

   public boolean isLocalized() {
      return getInstance().enableLocalized;
   }

   public static String getMessage(String var0, Object... var1) {
      return getInstance().getTextMessage(var0, var1);
   }

   public static LocalizedOutput OutputWriter() {
      return getInstance().out;
   }

   public static LocalizedInput InputReader() {
      return getInstance().in;
   }

   public static String getNumber(long var0) {
      return getInstance().getNumberAsString(var0);
   }

   public static String getNumber(int var0) {
      return getInstance().getNumberAsString(var0);
   }

   public String toString() {
      String var10000 = this.locale == null ? "null" : this.locale.toString();
      String var1 = "toString(){\nlocale=" + var10000 + "\nencode=" + this.encode + "\nmessageFile=" + this.messageFileName + "\nresourceKey=" + this.resourceKey + "\nenableLocalized=" + this.enableLocalized + " \ndateSize=" + this.dateSize + "\ntimeSize=" + this.timeSize + "\ntimestampSize=" + this.timestampSize + "\n}";
      return var1;
   }
}
