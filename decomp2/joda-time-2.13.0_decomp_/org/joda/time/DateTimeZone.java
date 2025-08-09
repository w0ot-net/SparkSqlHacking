package org.joda.time;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;

public abstract class DateTimeZone implements Serializable {
   private static final long serialVersionUID = 5546345482340108586L;
   public static final DateTimeZone UTC;
   private static final int MAX_MILLIS = 86399999;
   private static final AtomicReference cProvider;
   private static final AtomicReference cNameProvider;
   private static final AtomicReference cDefault;
   public static final String DEFAULT_TZ_DATA_PATH = "org/joda/time/tz/data";
   private final String iID;

   public static DateTimeZone getDefault() {
      DateTimeZone var0 = (DateTimeZone)cDefault.get();
      if (var0 == null) {
         try {
            try {
               String var1 = System.getProperty("org.joda.time.DateTimeZone.Timezone");
               if (var1 != null) {
                  var0 = forID(var1);
               }
            } catch (RuntimeException var2) {
            }

            if (var0 == null) {
               var0 = forTimeZone(TimeZone.getDefault());
            }
         } catch (IllegalArgumentException var3) {
         }

         if (var0 == null) {
            var0 = UTC;
         }

         if (!cDefault.compareAndSet((Object)null, var0)) {
            var0 = (DateTimeZone)cDefault.get();
         }
      }

      return var0;
   }

   public static void setDefault(DateTimeZone var0) throws SecurityException {
      SecurityManager var1 = System.getSecurityManager();
      if (var1 != null) {
         var1.checkPermission(new JodaTimePermission("DateTimeZone.setDefault"));
      }

      if (var0 == null) {
         throw new IllegalArgumentException("The datetime zone must not be null");
      } else {
         cDefault.set(var0);
      }
   }

   @FromString
   public static DateTimeZone forID(String var0) {
      if (var0 == null) {
         return getDefault();
      } else if (var0.equals("UTC")) {
         return UTC;
      } else {
         DateTimeZone var1 = getProvider().getZone(var0);
         if (var1 != null) {
            return var1;
         } else if (!var0.equals("UT") && !var0.equals("GMT") && !var0.equals("Z")) {
            String var2 = var0;
            if (!var0.startsWith("UTC+") && !var0.startsWith("UTC-") && !var0.startsWith("GMT+") && !var0.startsWith("GMT-")) {
               if (var0.startsWith("UT+") || var0.startsWith("UT-")) {
                  var2 = var0.substring(2);
               }
            } else {
               var2 = var0.substring(3);
            }

            if (!var2.startsWith("+") && !var2.startsWith("-")) {
               throw new IllegalArgumentException("The datetime zone id '" + var0 + "' is not recognised");
            } else {
               int var3 = parseOffset(var2);
               if ((long)var3 == 0L) {
                  return UTC;
               } else {
                  var2 = printOffset(var3);
                  return fixedOffsetZone(var2, var3);
               }
            }
         } else {
            return UTC;
         }
      }
   }

   public static DateTimeZone forOffsetHours(int var0) throws IllegalArgumentException {
      return forOffsetHoursMinutes(var0, 0);
   }

   public static DateTimeZone forOffsetHoursMinutes(int var0, int var1) throws IllegalArgumentException {
      if (var0 == 0 && var1 == 0) {
         return UTC;
      } else if (var0 >= -23 && var0 <= 23) {
         if (var1 >= -59 && var1 <= 59) {
            if (var0 > 0 && var1 < 0) {
               throw new IllegalArgumentException("Positive hours must not have negative minutes: " + var1);
            } else {
               int var2 = 0;

               try {
                  int var3 = var0 * 60;
                  if (var3 < 0) {
                     var1 = var3 - Math.abs(var1);
                  } else {
                     var1 = var3 + var1;
                  }

                  var2 = FieldUtils.safeMultiply(var1, 60000);
               } catch (ArithmeticException var4) {
                  throw new IllegalArgumentException("Offset is too large");
               }

               return forOffsetMillis(var2);
            }
         } else {
            throw new IllegalArgumentException("Minutes out of range: " + var1);
         }
      } else {
         throw new IllegalArgumentException("Hours out of range: " + var0);
      }
   }

   public static DateTimeZone forOffsetMillis(int var0) {
      if (var0 >= -86399999 && var0 <= 86399999) {
         String var1 = printOffset(var0);
         return fixedOffsetZone(var1, var0);
      } else {
         throw new IllegalArgumentException("Millis out of range: " + var0);
      }
   }

   public static DateTimeZone forTimeZone(TimeZone var0) {
      if (var0 == null) {
         return getDefault();
      } else {
         String var1 = var0.getID();
         if (var1 == null) {
            throw new IllegalArgumentException("The TimeZone id must not be null");
         } else if (var1.equals("UTC")) {
            return UTC;
         } else {
            DateTimeZone var2 = null;
            String var3 = getConvertedId(var1);
            Provider var4 = getProvider();
            if (var3 != null) {
               var2 = var4.getZone(var3);
            }

            if (var2 == null) {
               var2 = var4.getZone(var1);
            }

            if (var2 != null) {
               return var2;
            } else if (var3 == null && (var1.startsWith("GMT+") || var1.startsWith("GMT-"))) {
               var3 = var1.substring(3);
               if (var3.length() > 2) {
                  char var5 = var3.charAt(1);
                  if (var5 > '9' && Character.isDigit(var5)) {
                     var3 = convertToAsciiNumber(var3);
                  }
               }

               int var8 = parseOffset(var3);
               if ((long)var8 == 0L) {
                  return UTC;
               } else {
                  var3 = printOffset(var8);
                  return fixedOffsetZone(var3, var8);
               }
            } else {
               throw new IllegalArgumentException("The datetime zone id '" + var1 + "' is not recognised");
            }
         }
      }
   }

   private static String convertToAsciiNumber(String var0) {
      StringBuilder var1 = new StringBuilder(var0);

      for(int var2 = 0; var2 < var1.length(); ++var2) {
         char var3 = var1.charAt(var2);
         int var4 = Character.digit(var3, 10);
         if (var4 >= 0) {
            var1.setCharAt(var2, (char)(48 + var4));
         }
      }

      return var1.toString();
   }

   private static DateTimeZone fixedOffsetZone(String var0, int var1) {
      return (DateTimeZone)(var1 == 0 ? UTC : new FixedDateTimeZone(var0, (String)null, var1, var1));
   }

   public static Set getAvailableIDs() {
      return getProvider().getAvailableIDs();
   }

   public static Provider getProvider() {
      Provider var0 = (Provider)cProvider.get();
      if (var0 == null) {
         var0 = getDefaultProvider();
         if (!cProvider.compareAndSet((Object)null, var0)) {
            var0 = (Provider)cProvider.get();
         }
      }

      return var0;
   }

   public static void setProvider(Provider var0) throws SecurityException {
      SecurityManager var1 = System.getSecurityManager();
      if (var1 != null) {
         var1.checkPermission(new JodaTimePermission("DateTimeZone.setProvider"));
      }

      if (var0 == null) {
         var0 = getDefaultProvider();
      } else {
         validateProvider(var0);
      }

      cProvider.set(var0);
   }

   private static Provider validateProvider(Provider var0) {
      Set var1 = var0.getAvailableIDs();
      if (var1 != null && var1.size() != 0) {
         if (!var1.contains("UTC")) {
            throw new IllegalArgumentException("The provider doesn't support UTC");
         } else if (!UTC.equals(var0.getZone("UTC"))) {
            throw new IllegalArgumentException("Invalid UTC zone provided");
         } else {
            return var0;
         }
      } else {
         throw new IllegalArgumentException("The provider doesn't have any available ids");
      }
   }

   private static Provider getDefaultProvider() {
      try {
         String var0 = System.getProperty("org.joda.time.DateTimeZone.Provider");
         if (var0 != null) {
            try {
               Class var10 = Class.forName(var0, false, DateTimeZone.class.getClassLoader());
               if (!Provider.class.isAssignableFrom(var10)) {
                  throw new IllegalArgumentException("System property referred to class that does not implement " + Provider.class);
               }

               Provider var2 = (Provider)var10.asSubclass(Provider.class).getConstructor().newInstance();
               return validateProvider(var2);
            } catch (Exception var6) {
               throw new RuntimeException(var6);
            }
         }
      } catch (SecurityException var7) {
      }

      try {
         String var8 = System.getProperty("org.joda.time.DateTimeZone.Folder");
         if (var8 != null) {
            try {
               ZoneInfoProvider var1 = new ZoneInfoProvider(new File(var8));
               return validateProvider(var1);
            } catch (Exception var4) {
               throw new RuntimeException(var4);
            }
         }
      } catch (SecurityException var5) {
      }

      try {
         ZoneInfoProvider var9 = new ZoneInfoProvider("org/joda/time/tz/data");
         return validateProvider(var9);
      } catch (Exception var3) {
         var3.printStackTrace();
         return new UTCProvider();
      }
   }

   public static NameProvider getNameProvider() {
      NameProvider var0 = (NameProvider)cNameProvider.get();
      if (var0 == null) {
         var0 = getDefaultNameProvider();
         if (!cNameProvider.compareAndSet((Object)null, var0)) {
            var0 = (NameProvider)cNameProvider.get();
         }
      }

      return var0;
   }

   public static void setNameProvider(NameProvider var0) throws SecurityException {
      SecurityManager var1 = System.getSecurityManager();
      if (var1 != null) {
         var1.checkPermission(new JodaTimePermission("DateTimeZone.setNameProvider"));
      }

      if (var0 == null) {
         var0 = getDefaultNameProvider();
      }

      cNameProvider.set(var0);
   }

   private static NameProvider getDefaultNameProvider() {
      Object var0 = null;

      try {
         String var1 = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
         if (var1 != null) {
            try {
               Class var2 = Class.forName(var1, false, DateTimeZone.class.getClassLoader());
               if (!NameProvider.class.isAssignableFrom(var2)) {
                  throw new IllegalArgumentException("System property referred to class that does not implement " + NameProvider.class);
               }

               var0 = (NameProvider)var2.asSubclass(NameProvider.class).getConstructor().newInstance();
            } catch (Exception var3) {
               throw new RuntimeException(var3);
            }
         }
      } catch (SecurityException var4) {
      }

      if (var0 == null) {
         var0 = new DefaultNameProvider();
      }

      return (NameProvider)var0;
   }

   private static String getConvertedId(String var0) {
      return (String)DateTimeZone.LazyInit.CONVERSION_MAP.get(var0);
   }

   private static int parseOffset(String var0) {
      return -((int)DateTimeZone.LazyInit.OFFSET_FORMATTER.parseMillis(var0));
   }

   private static String printOffset(int var0) {
      StringBuffer var1 = new StringBuffer();
      if (var0 >= 0) {
         var1.append('+');
      } else {
         var1.append('-');
         var0 = -var0;
      }

      int var2 = var0 / 3600000;
      FormatUtils.appendPaddedInteger((StringBuffer)var1, var2, 2);
      var0 -= var2 * 3600000;
      int var3 = var0 / '\uea60';
      var1.append(':');
      FormatUtils.appendPaddedInteger((StringBuffer)var1, var3, 2);
      var0 -= var3 * '\uea60';
      if (var0 == 0) {
         return var1.toString();
      } else {
         int var4 = var0 / 1000;
         var1.append(':');
         FormatUtils.appendPaddedInteger((StringBuffer)var1, var4, 2);
         var0 -= var4 * 1000;
         if (var0 == 0) {
            return var1.toString();
         } else {
            var1.append('.');
            FormatUtils.appendPaddedInteger((StringBuffer)var1, var0, 3);
            return var1.toString();
         }
      }
   }

   protected DateTimeZone(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Id must not be null");
      } else {
         this.iID = var1;
      }
   }

   @ToString
   public final String getID() {
      return this.iID;
   }

   public abstract String getNameKey(long var1);

   public final String getShortName(long var1) {
      return this.getShortName(var1, (Locale)null);
   }

   public String getShortName(long var1, Locale var3) {
      if (var3 == null) {
         var3 = Locale.getDefault();
      }

      String var4 = this.getNameKey(var1);
      if (var4 == null) {
         return this.iID;
      } else {
         NameProvider var6 = getNameProvider();
         String var5;
         if (var6 instanceof DefaultNameProvider) {
            var5 = ((DefaultNameProvider)var6).getShortName(var3, this.iID, var4, this.isStandardOffset(var1));
         } else {
            var5 = var6.getShortName(var3, this.iID, var4);
         }

         return var5 != null ? var5 : printOffset(this.getOffset(var1));
      }
   }

   public final String getName(long var1) {
      return this.getName(var1, (Locale)null);
   }

   public String getName(long var1, Locale var3) {
      if (var3 == null) {
         var3 = Locale.getDefault();
      }

      String var4 = this.getNameKey(var1);
      if (var4 == null) {
         return this.iID;
      } else {
         NameProvider var6 = getNameProvider();
         String var5;
         if (var6 instanceof DefaultNameProvider) {
            var5 = ((DefaultNameProvider)var6).getName(var3, this.iID, var4, this.isStandardOffset(var1));
         } else {
            var5 = var6.getName(var3, this.iID, var4);
         }

         return var5 != null ? var5 : printOffset(this.getOffset(var1));
      }
   }

   public abstract int getOffset(long var1);

   public final int getOffset(ReadableInstant var1) {
      return var1 == null ? this.getOffset(DateTimeUtils.currentTimeMillis()) : this.getOffset(var1.getMillis());
   }

   public abstract int getStandardOffset(long var1);

   public boolean isStandardOffset(long var1) {
      return this.getOffset(var1) == this.getStandardOffset(var1);
   }

   public int getOffsetFromLocal(long var1) {
      int var3 = this.getOffset(var1);
      long var4 = var1 - (long)var3;
      int var6 = this.getOffset(var4);
      if (var3 != var6) {
         if (var3 - var6 < 0) {
            long var7 = this.nextTransition(var4);
            if (var7 == var1 - (long)var3) {
               var7 = Long.MAX_VALUE;
            }

            long var9 = this.nextTransition(var1 - (long)var6);
            if (var9 == var1 - (long)var6) {
               var9 = Long.MAX_VALUE;
            }

            if (var7 != var9) {
               return var3;
            }
         }
      } else if (var3 >= 0) {
         long var11 = this.previousTransition(var4);
         if (var11 < var4) {
            int var12 = this.getOffset(var11);
            int var10 = var12 - var3;
            if (var4 - var11 <= (long)var10) {
               return var12;
            }
         }
      }

      return var6;
   }

   public long convertUTCToLocal(long var1) {
      int var3 = this.getOffset(var1);
      long var4 = var1 + (long)var3;
      if ((var1 ^ var4) < 0L && (var1 ^ (long)var3) >= 0L) {
         throw new ArithmeticException("Adding time zone offset caused overflow");
      } else {
         return var4;
      }
   }

   public long convertLocalToUTC(long var1, boolean var3, long var4) {
      int var6 = this.getOffset(var4);
      long var7 = var1 - (long)var6;
      int var9 = this.getOffset(var7);
      return var9 == var6 ? var7 : this.convertLocalToUTC(var1, var3);
   }

   public long convertLocalToUTC(long var1, boolean var3) {
      int var4 = this.getOffset(var1);
      int var5 = this.getOffset(var1 - (long)var4);
      if (var4 != var5 && (var3 || var4 < 0)) {
         long var6 = this.nextTransition(var1 - (long)var4);
         if (var6 == var1 - (long)var4) {
            var6 = Long.MAX_VALUE;
         }

         long var8 = this.nextTransition(var1 - (long)var5);
         if (var8 == var1 - (long)var5) {
            var8 = Long.MAX_VALUE;
         }

         if (var6 != var8) {
            if (var3) {
               throw new IllegalInstantException(var1, this.getID());
            }

            var5 = var4;
         }
      }

      long var10 = var1 - (long)var5;
      if ((var1 ^ var10) < 0L && (var1 ^ (long)var5) < 0L) {
         throw new ArithmeticException("Subtracting time zone offset caused overflow");
      } else {
         return var10;
      }
   }

   public long getMillisKeepLocal(DateTimeZone var1, long var2) {
      if (var1 == null) {
         var1 = getDefault();
      }

      if (var1 == this) {
         return var2;
      } else {
         long var4 = this.convertUTCToLocal(var2);
         return var1.convertLocalToUTC(var4, false, var2);
      }
   }

   public boolean isLocalDateTimeGap(LocalDateTime var1) {
      if (this.isFixed()) {
         return false;
      } else {
         try {
            var1.toDateTime(this);
            return false;
         } catch (IllegalInstantException var3) {
            return true;
         }
      }
   }

   public long adjustOffset(long var1, boolean var3) {
      long var4 = var1 - 10800000L;
      long var6 = var1 + 10800000L;
      long var8 = (long)this.getOffset(var4);
      long var10 = (long)this.getOffset(var6);
      if (var8 <= var10) {
         return var1;
      } else {
         long var12 = var8 - var10;
         long var14 = this.nextTransition(var4);
         long var16 = var14 - var12;
         long var18 = var14 + var12;
         if (var1 >= var16 && var1 < var18) {
            long var20 = var1 - var16;
            if (var20 >= var12) {
               return var3 ? var1 : var1 - var12;
            } else {
               return var3 ? var1 + var12 : var1;
            }
         } else {
            return var1;
         }
      }
   }

   public abstract boolean isFixed();

   public abstract long nextTransition(long var1);

   public abstract long previousTransition(long var1);

   public TimeZone toTimeZone() {
      return TimeZone.getTimeZone(this.iID);
   }

   public abstract boolean equals(Object var1);

   public int hashCode() {
      return 57 + this.getID().hashCode();
   }

   public String toString() {
      return this.getID();
   }

   protected Object writeReplace() throws ObjectStreamException {
      return new Stub(this.iID);
   }

   static {
      UTC = UTCDateTimeZone.INSTANCE;
      cProvider = new AtomicReference();
      cNameProvider = new AtomicReference();
      cDefault = new AtomicReference();
   }

   private static final class Stub implements Serializable {
      private static final long serialVersionUID = -6471952376487863581L;
      private transient String iID;

      Stub(String var1) {
         this.iID = var1;
      }

      private void writeObject(ObjectOutputStream var1) throws IOException {
         var1.writeUTF(this.iID);
      }

      private void readObject(ObjectInputStream var1) throws IOException {
         this.iID = var1.readUTF();
      }

      private Object readResolve() throws ObjectStreamException {
         return DateTimeZone.forID(this.iID);
      }
   }

   static final class LazyInit {
      static final Map CONVERSION_MAP = buildMap();
      static final DateTimeFormatter OFFSET_FORMATTER = buildFormatter();

      private static DateTimeFormatter buildFormatter() {
         BaseChronology var0 = new BaseChronology() {
            private static final long serialVersionUID = -3128740902654445468L;

            public DateTimeZone getZone() {
               return null;
            }

            public Chronology withUTC() {
               return this;
            }

            public Chronology withZone(DateTimeZone var1) {
               return this;
            }

            public String toString() {
               return this.getClass().getName();
            }
         };
         return (new DateTimeFormatterBuilder()).appendTimeZoneOffset((String)null, true, 2, 4).toFormatter().withChronology(var0);
      }

      private static Map buildMap() {
         HashMap var0 = new HashMap();
         var0.put("GMT", "UTC");
         var0.put("WET", "WET");
         var0.put("CET", "CET");
         var0.put("MET", "CET");
         var0.put("ECT", "CET");
         var0.put("EET", "EET");
         var0.put("MIT", "Pacific/Apia");
         var0.put("HST", "Pacific/Honolulu");
         var0.put("AST", "America/Anchorage");
         var0.put("PST", "America/Los_Angeles");
         var0.put("MST", "America/Denver");
         var0.put("PNT", "America/Phoenix");
         var0.put("CST", "America/Chicago");
         var0.put("EST", "America/New_York");
         var0.put("IET", "America/Indiana/Indianapolis");
         var0.put("PRT", "America/Puerto_Rico");
         var0.put("CNT", "America/St_Johns");
         var0.put("AGT", "America/Argentina/Buenos_Aires");
         var0.put("BET", "America/Sao_Paulo");
         var0.put("ART", "Africa/Cairo");
         var0.put("CAT", "Africa/Harare");
         var0.put("EAT", "Africa/Addis_Ababa");
         var0.put("NET", "Asia/Yerevan");
         var0.put("PLT", "Asia/Karachi");
         var0.put("IST", "Asia/Kolkata");
         var0.put("BST", "Asia/Dhaka");
         var0.put("VST", "Asia/Ho_Chi_Minh");
         var0.put("CTT", "Asia/Shanghai");
         var0.put("JST", "Asia/Tokyo");
         var0.put("ACT", "Australia/Darwin");
         var0.put("AET", "Australia/Sydney");
         var0.put("SST", "Pacific/Guadalcanal");
         var0.put("NST", "Pacific/Auckland");
         return Collections.unmodifiableMap(var0);
      }
   }
}
