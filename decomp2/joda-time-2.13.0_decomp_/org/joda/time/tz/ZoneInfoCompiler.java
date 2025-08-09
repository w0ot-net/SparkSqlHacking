package org.joda.time.tz;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.LenientChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class ZoneInfoCompiler {
   static DateTimeOfYear cStartOfYear;
   static Chronology cLenientISO;
   static final Set RULE_LOOKUP = expand("rule", "r");
   static final Set ZONE_LOOKUP = expand("zone", "z");
   static final Set LINK_LOOKUP = expand("link", "l");
   static final Set MIN_YEAR_LOOKUP = expand("minimum", "mi");
   static final Set MAX_YEAR_LOOKUP = expand("maximum", "ma");
   static final Set ONLY_YEAR_LOOKUP = expand("only", "o");
   static final Map MONTH_LOOKUP = new HashMap();
   static final Map DOW_LOOKUP;
   private Map iRuleSets = new HashMap();
   private List iZones = new ArrayList();
   private List iGoodLinks = new ArrayList();
   private List iBackLinks = new ArrayList();

   private static void put(Set var0, int var1, Map var2) {
      Iterator var3 = var0.iterator();

      while(var3.hasNext()) {
         var2.put(var3.next(), var1);
      }

   }

   private static Set expand(String var0, String var1) {
      HashSet var2 = new HashSet();

      String var3;
      for(var3 = var0; !var3.equals(var1); var3 = var3.substring(0, var3.length() - 1)) {
         var2.add(var3);
      }

      var2.add(var3);
      return var2;
   }

   public static void main(String[] var0) throws Exception {
      if (var0.length == 0) {
         printUsage();
      } else {
         File var1 = null;
         File var2 = null;
         boolean var3 = false;

         int var4;
         for(var4 = 0; var4 < var0.length; ++var4) {
            if ("-src".equals(var0[var4])) {
               ++var4;
               if (var4 >= var0.length) {
                  printUsage();
                  return;
               }

               var1 = new File(var0[var4]);
            } else if ("-dst".equals(var0[var4])) {
               ++var4;
               if (var4 >= var0.length) {
                  printUsage();
                  return;
               }

               var2 = new File(var0[var4]);
            } else {
               if (!"-verbose".equals(var0[var4])) {
                  if ("-?".equals(var0[var4])) {
                     printUsage();
                     return;
                  }
                  break;
               }

               var3 = true;
            }
         }

         if (var4 >= var0.length) {
            printUsage();
         } else {
            File[] var5 = new File[var0.length - var4];

            for(int var6 = 0; var4 < var0.length; ++var6) {
               var5[var6] = var1 == null ? new File(var0[var4]) : new File(var1, var0[var4]);
               ++var4;
            }

            ZoneInfoLogger.set(var3);
            ZoneInfoCompiler var7 = new ZoneInfoCompiler();
            var7.compile(var2, var5);
         }
      }
   }

   private static void printUsage() {
      System.out.println("Usage: java org.joda.time.tz.ZoneInfoCompiler <options> <source files>");
      System.out.println("where possible options include:");
      System.out.println("  -src <directory>    Specify where to read source files");
      System.out.println("  -dst <directory>    Specify where to write generated files");
      System.out.println("  -verbose            Output verbosely (default false)");
   }

   static DateTimeOfYear getStartOfYear() {
      if (cStartOfYear == null) {
         cStartOfYear = new DateTimeOfYear();
      }

      return cStartOfYear;
   }

   static Chronology getLenientISOChronology() {
      if (cLenientISO == null) {
         cLenientISO = LenientChronology.getInstance(ISOChronology.getInstanceUTC());
      }

      return cLenientISO;
   }

   static void writeZoneInfoMap(DataOutputStream var0, Map var1) throws IOException {
      if (var0 == null) {
         throw new IllegalArgumentException("DataOutputStream must not be null.");
      } else {
         HashMap var2 = new HashMap(var1.size());
         TreeMap var3 = new TreeMap();
         short var4 = 0;

         for(Map.Entry var6 : var1.entrySet()) {
            String var7 = (String)var6.getKey();
            if (!var2.containsKey(var7)) {
               Short var8 = var4;
               var2.put(var7, var8);
               var3.put(var8, var7);
               ++var4;
               if (var4 == 32767) {
                  throw new InternalError("Too many time zone ids");
               }
            }

            var7 = ((DateTimeZone)var6.getValue()).getID();
            if (!var2.containsKey(var7)) {
               Short var16 = var4;
               var2.put(var7, var16);
               var3.put(var16, var7);
               ++var4;
               if (var4 == 32767) {
                  throw new InternalError("Too many time zone ids");
               }
            }
         }

         var0.writeShort(var3.size());

         for(String var11 : var3.values()) {
            var0.writeUTF(var11);
         }

         var0.writeShort(var1.size());

         for(Map.Entry var12 : var1.entrySet()) {
            String var14 = (String)var12.getKey();
            var0.writeShort((Short)var2.get(var14));
            var14 = ((DateTimeZone)var12.getValue()).getID();
            var0.writeShort((Short)var2.get(var14));
         }

      }
   }

   static int parseYear(String var0, int var1) {
      String var2 = var0.toLowerCase(Locale.ENGLISH);
      if (MIN_YEAR_LOOKUP.contains(var2)) {
         return Integer.MIN_VALUE;
      } else if (MAX_YEAR_LOOKUP.contains(var2)) {
         return Integer.MAX_VALUE;
      } else {
         return ONLY_YEAR_LOOKUP.contains(var2) ? var1 : Integer.parseInt(var0);
      }
   }

   static int parseMonth(String var0) {
      Integer var1 = (Integer)MONTH_LOOKUP.get(var0.toLowerCase(Locale.ENGLISH));
      if (var1 == null) {
         throw new IllegalArgumentException("Unknown month: " + var0);
      } else {
         return var1;
      }
   }

   static int parseDayOfWeek(String var0) {
      Integer var1 = (Integer)DOW_LOOKUP.get(var0.toLowerCase(Locale.ENGLISH));
      if (var1 == null) {
         throw new IllegalArgumentException("Unknown day-of-week: " + var0);
      } else {
         return var1;
      }
   }

   static String parseOptional(String var0) {
      return var0.equals("-") ? null : var0;
   }

   static int parseTime(String var0) {
      if (var0.equals("-")) {
         return 0;
      } else {
         DateTimeFormatter var1 = ISODateTimeFormat.hourMinuteSecondFraction();
         MutableDateTime var2 = new MutableDateTime(0L, getLenientISOChronology());
         byte var3 = 0;
         if (var0.startsWith("-")) {
            var3 = 1;
         }

         int var4 = var1.parseInto(var2, var0, var3);
         if (var4 == ~var3) {
            throw new IllegalArgumentException(var0);
         } else {
            int var5 = (int)var2.getMillis();
            if (var3 == 1) {
               var5 = -var5;
            }

            return var5;
         }
      }
   }

   static char parseZoneChar(char var0) {
      switch (var0) {
         case 'G':
         case 'U':
         case 'Z':
         case 'g':
         case 'u':
         case 'z':
            return 'u';
         case 'S':
         case 's':
            return 's';
         case 'W':
         case 'w':
         default:
            return 'w';
      }
   }

   static boolean test(String var0, DateTimeZone var1) {
      if (!var0.equals(var1.getID())) {
         return true;
      } else {
         long var2 = ISOChronology.getInstanceUTC().year().set(0L, 1850);
         long var4 = ISOChronology.getInstanceUTC().year().set(0L, 2050);
         int var6 = var1.getOffset(var2);
         int var7 = var1.getStandardOffset(var2);
         String var8 = var1.getNameKey(var2);
         ArrayList var9 = new ArrayList();

         while(true) {
            long var10 = var1.nextTransition(var2);
            if (var10 != var2 && var10 <= var4) {
               var2 = var10;
               int var12 = var1.getOffset(var10);
               int var18 = var1.getStandardOffset(var10);
               String var14 = var1.getNameKey(var10);
               if (var6 == var12 && var7 == var18 && var8.equals(var14)) {
                  System.out.println("*d* Error in " + var1.getID() + " " + new DateTime(var10, ISOChronology.getInstanceUTC()));
                  return false;
               }

               if (var14 != null && (var14.length() >= 3 || "??".equals(var14) || "%z".equals(var14))) {
                  var9.add(var10);
                  var6 = var12;
                  var8 = var14;
                  continue;
               }

               System.out.println("*s* Error in " + var1.getID() + " " + new DateTime(var10, ISOChronology.getInstanceUTC()) + ", nameKey=" + var14);
               return false;
            }

            var2 = ISOChronology.getInstanceUTC().year().set(0L, 2050);
            var4 = ISOChronology.getInstanceUTC().year().set(0L, 1850);
            int var17 = var9.size();

            while(true) {
               --var17;
               if (var17 < 0) {
                  break;
               }

               long var11 = var1.previousTransition(var2);
               if (var11 == var2 || var11 < var4) {
                  break;
               }

               var2 = var11;
               long var13 = (Long)var9.get(var17);
               if (var13 - 1L != var11) {
                  System.out.println("*r* Error in " + var1.getID() + " " + new DateTime(var11, ISOChronology.getInstanceUTC()) + " != " + new DateTime(var13 - 1L, ISOChronology.getInstanceUTC()));
                  return false;
               }
            }

            return true;
         }
      }
   }

   public Map compile(File var1, File[] var2) throws IOException {
      if (var2 != null) {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            BufferedReader var4 = null;

            try {
               var4 = new BufferedReader(new FileReader(var2[var3]));
               this.parseDataFile(var4, "backward".equals(var2[var3].getName()));
            } finally {
               if (var4 != null) {
                  var4.close();
               }

            }
         }
      }

      if (var1 != null) {
         if (!var1.exists() && !var1.mkdirs()) {
            throw new IOException("Destination directory doesn't exist and cannot be created: " + var1);
         }

         if (!var1.isDirectory()) {
            throw new IOException("Destination is not a directory: " + var1);
         }
      }

      TreeMap var18 = new TreeMap();
      TreeMap var19 = new TreeMap();
      System.out.println("Writing zoneinfo files");

      for(int var5 = 0; var5 < this.iZones.size(); ++var5) {
         Zone var6 = (Zone)this.iZones.get(var5);
         DateTimeZoneBuilder var7 = new DateTimeZoneBuilder();
         var6.addToBuilder(var7, this.iRuleSets);
         DateTimeZone var8 = var7.toDateTimeZone(var6.iName, true);
         if (test(var8.getID(), var8)) {
            var18.put(var8.getID(), var8);
            var19.put(var8.getID(), var6);
            if (var1 != null) {
               this.writeZone(var1, var7, var8);
            }
         }
      }

      for(int var20 = 0; var20 < this.iGoodLinks.size(); var20 += 2) {
         String var23 = (String)this.iGoodLinks.get(var20);
         String var26 = (String)this.iGoodLinks.get(var20 + 1);
         Zone var29 = (Zone)var19.get(var23);
         if (var29 == null) {
            System.out.println("Cannot find source zone '" + var23 + "' to link alias '" + var26 + "' to");
         } else {
            DateTimeZoneBuilder var9 = new DateTimeZoneBuilder();
            var29.addToBuilder(var9, this.iRuleSets);
            DateTimeZone var10 = var9.toDateTimeZone(var26, true);
            if (test(var10.getID(), var10)) {
               var18.put(var10.getID(), var10);
               if (var1 != null) {
                  this.writeZone(var1, var9, var10);
               }
            }

            var18.put(var10.getID(), var10);
            if (ZoneInfoLogger.verbose()) {
               System.out.println("Good link: " + var26 + " -> " + var23 + " revived");
            }
         }
      }

      for(int var21 = 0; var21 < 2; ++var21) {
         for(int var24 = 0; var24 < this.iBackLinks.size(); var24 += 2) {
            String var27 = (String)this.iBackLinks.get(var24);
            String var30 = (String)this.iBackLinks.get(var24 + 1);
            DateTimeZone var32 = (DateTimeZone)var18.get(var27);
            if (var32 == null) {
               if (var21 > 0) {
                  System.out.println("Cannot find time zone '" + var27 + "' to link alias '" + var30 + "' to");
               }
            } else {
               var18.put(var30, var32);
               if (ZoneInfoLogger.verbose()) {
                  System.out.println("Back link: " + var30 + " -> " + var32.getID());
               }
            }
         }
      }

      if (var1 != null) {
         System.out.println("Writing ZoneInfoMap");
         File var22 = new File(var1, "ZoneInfoMap");
         if (!var22.getParentFile().exists()) {
            var22.getParentFile().mkdirs();
         }

         FileOutputStream var25 = new FileOutputStream(var22);
         DataOutputStream var28 = new DataOutputStream(var25);

         try {
            TreeMap var31 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            var31.putAll(var18);
            writeZoneInfoMap(var28, var31);
         } finally {
            var28.close();
         }
      }

      return var18;
   }

   private void writeZone(File var1, DateTimeZoneBuilder var2, DateTimeZone var3) throws IOException {
      if (ZoneInfoLogger.verbose()) {
         System.out.println("Writing " + var3.getID());
      }

      File var4 = new File(var1, var3.getID());
      if (!var4.getParentFile().exists()) {
         var4.getParentFile().mkdirs();
      }

      FileOutputStream var5 = new FileOutputStream(var4);

      try {
         var2.writeTo(var3.getID(), (OutputStream)var5);
      } finally {
         ((OutputStream)var5).close();
      }

      FileInputStream var6 = new FileInputStream(var4);
      DateTimeZone var7 = DateTimeZoneBuilder.readFrom((InputStream)var6, var3.getID());
      ((InputStream)var6).close();
      if (!var3.equals(var7)) {
         System.out.println("*e* Error in " + var3.getID() + ": Didn't read properly from file");
      }

   }

   public void parseDataFile(BufferedReader var1, boolean var2) throws IOException {
      Zone var3 = null;

      String var4;
      while((var4 = var1.readLine()) != null) {
         String var5 = var4.trim();
         if (var5.length() != 0 && var5.charAt(0) != '#') {
            int var6 = var4.indexOf(35);
            if (var6 >= 0) {
               var4 = var4.substring(0, var6);
            }

            StringTokenizer var7 = new StringTokenizer(var4, " \f\r\t\u000b");
            if (Character.isWhitespace(var4.charAt(0)) && var7.hasMoreTokens()) {
               if (var3 != null) {
                  var3.chain(var7);
               }
            } else {
               if (var3 != null) {
                  this.iZones.add(var3);
               }

               var3 = null;
               if (var7.hasMoreTokens()) {
                  String var8 = var7.nextToken().toLowerCase(Locale.ENGLISH);
                  if (RULE_LOOKUP.contains(var8)) {
                     Rule var11 = new Rule(var7);
                     RuleSet var12 = (RuleSet)this.iRuleSets.get(var11.iName);
                     if (var12 == null) {
                        var12 = new RuleSet(var11);
                        this.iRuleSets.put(var11.iName, var12);
                     } else {
                        var12.addRule(var11);
                     }
                  } else if (ZONE_LOOKUP.contains(var8)) {
                     if (var7.countTokens() < 4) {
                        throw new IllegalArgumentException("Attempting to create a Zone from an incomplete tokenizer");
                     }

                     var3 = new Zone(var7);
                  } else if (LINK_LOOKUP.contains(var8)) {
                     String var9 = var7.nextToken();
                     String var10 = var7.nextToken();
                     if (!var10.equals("WET") && !var10.equals("CET") && !var10.equals("EET")) {
                        if (var10.equals("MET")) {
                           this.iBackLinks.add("CET");
                           this.iBackLinks.add(var10);
                        } else if (!var2 && !var10.equals("US/Pacific-New") && !var10.startsWith("Etc/") && !var10.equals("GMT")) {
                           this.iGoodLinks.add(var9);
                           this.iGoodLinks.add(var10);
                        } else {
                           this.iBackLinks.add(var9);
                           this.iBackLinks.add(var10);
                        }
                     } else {
                        this.iGoodLinks.add(var9);
                        this.iGoodLinks.add(var10);
                     }
                  } else {
                     System.out.println("Unknown line: " + var4);
                  }
               }
            }
         }
      }

      if (var3 != null) {
         this.iZones.add(var3);
      }

   }

   static {
      put(expand("january", "ja"), 1, MONTH_LOOKUP);
      put(expand("february", "f"), 2, MONTH_LOOKUP);
      put(expand("march", "mar"), 3, MONTH_LOOKUP);
      put(expand("april", "ap"), 4, MONTH_LOOKUP);
      put(expand("may", "may"), 5, MONTH_LOOKUP);
      put(expand("june", "jun"), 6, MONTH_LOOKUP);
      put(expand("july", "jul"), 7, MONTH_LOOKUP);
      put(expand("august", "au"), 8, MONTH_LOOKUP);
      put(expand("september", "s"), 9, MONTH_LOOKUP);
      put(expand("october", "o"), 10, MONTH_LOOKUP);
      put(expand("november", "n"), 11, MONTH_LOOKUP);
      put(expand("december", "d"), 12, MONTH_LOOKUP);
      DOW_LOOKUP = new HashMap();
      put(expand("monday", "m"), 1, DOW_LOOKUP);
      put(expand("tuesday", "tu"), 2, DOW_LOOKUP);
      put(expand("wednesday", "w"), 3, DOW_LOOKUP);
      put(expand("thursday", "th"), 4, DOW_LOOKUP);
      put(expand("friday", "f"), 5, DOW_LOOKUP);
      put(expand("saturday", "sa"), 6, DOW_LOOKUP);
      put(expand("sunday", "su"), 7, DOW_LOOKUP);
   }

   static class DateTimeOfYear {
      public final int iMonthOfYear;
      public final int iDayOfMonth;
      public final int iDayOfWeek;
      public final boolean iAdvanceDayOfWeek;
      public final int iMillisOfDay;
      public final char iZoneChar;

      DateTimeOfYear() {
         this.iMonthOfYear = 1;
         this.iDayOfMonth = 1;
         this.iDayOfWeek = 0;
         this.iAdvanceDayOfWeek = false;
         this.iMillisOfDay = 0;
         this.iZoneChar = 'w';
      }

      DateTimeOfYear(StringTokenizer var1) {
         int var2 = 1;
         int var3 = 1;
         int var4 = 0;
         int var5 = 0;
         boolean var6 = false;
         char var7 = 'w';
         if (var1.hasMoreTokens()) {
            var2 = ZoneInfoCompiler.parseMonth(var1.nextToken());
            if (var1.hasMoreTokens()) {
               String var8 = var1.nextToken();
               if (var8.toLowerCase(Locale.ENGLISH).startsWith("last")) {
                  var3 = -1;
                  var4 = ZoneInfoCompiler.parseDayOfWeek(var8.substring(4));
                  var6 = false;
               } else {
                  try {
                     var3 = Integer.parseInt(var8);
                     var4 = 0;
                     var6 = false;
                  } catch (NumberFormatException var11) {
                     int var10 = var8.indexOf(">=");
                     if (var10 > 0) {
                        var3 = Integer.parseInt(var8.substring(var10 + 2));
                        var4 = ZoneInfoCompiler.parseDayOfWeek(var8.substring(0, var10));
                        var6 = true;
                     } else {
                        var10 = var8.indexOf("<=");
                        if (var10 <= 0) {
                           throw new IllegalArgumentException(var8);
                        }

                        var3 = Integer.parseInt(var8.substring(var10 + 2));
                        var4 = ZoneInfoCompiler.parseDayOfWeek(var8.substring(0, var10));
                        var6 = false;
                     }
                  }
               }

               if (var1.hasMoreTokens()) {
                  var8 = var1.nextToken();
                  var7 = ZoneInfoCompiler.parseZoneChar(var8.charAt(var8.length() - 1));
                  if (var8.equals("24:00")) {
                     if (var2 == 12 && var3 == 31) {
                        var5 = ZoneInfoCompiler.parseTime("23:59:59.999");
                     } else {
                        LocalDate var9 = var3 == -1 ? (new LocalDate(2001, var2, 1)).plusMonths(1) : (new LocalDate(2001, var2, var3)).plusDays(1);
                        var6 = var3 != -1 && var4 != 0;
                        var2 = var9.getMonthOfYear();
                        var3 = var9.getDayOfMonth();
                        if (var4 != 0) {
                           var4 = (var4 - 1 + 1) % 7 + 1;
                        }
                     }
                  } else {
                     var5 = ZoneInfoCompiler.parseTime(var8);
                  }
               }
            }
         }

         this.iMonthOfYear = var2;
         this.iDayOfMonth = var3;
         this.iDayOfWeek = var4;
         this.iAdvanceDayOfWeek = var6;
         this.iMillisOfDay = var5;
         this.iZoneChar = var7;
      }

      public void addRecurring(DateTimeZoneBuilder var1, String var2, int var3, int var4, int var5) {
         var1.addRecurringSavings(var2, var3, var4, var5, this.iZoneChar, this.iMonthOfYear, this.iDayOfMonth, this.iDayOfWeek, this.iAdvanceDayOfWeek, this.iMillisOfDay);
      }

      public void addCutover(DateTimeZoneBuilder var1, int var2) {
         var1.addCutover(var2, this.iZoneChar, this.iMonthOfYear, this.iDayOfMonth, this.iDayOfWeek, this.iAdvanceDayOfWeek, this.iMillisOfDay);
      }

      public String toString() {
         return "MonthOfYear: " + this.iMonthOfYear + "\nDayOfMonth: " + this.iDayOfMonth + "\nDayOfWeek: " + this.iDayOfWeek + "\nAdvanceDayOfWeek: " + this.iAdvanceDayOfWeek + "\nMillisOfDay: " + this.iMillisOfDay + "\nZoneChar: " + this.iZoneChar + "\n";
      }
   }

   static class Rule {
      public final String iName;
      public final int iFromYear;
      public final int iToYear;
      public final String iType;
      public final DateTimeOfYear iDateTimeOfYear;
      public final int iSaveMillis;
      public final String iLetterS;

      Rule(StringTokenizer var1) {
         if (var1.countTokens() < 6) {
            throw new IllegalArgumentException("Attempting to create a Rule from an incomplete tokenizer");
         } else {
            this.iName = var1.nextToken().intern();
            this.iFromYear = ZoneInfoCompiler.parseYear(var1.nextToken(), 0);
            this.iToYear = ZoneInfoCompiler.parseYear(var1.nextToken(), this.iFromYear);
            if (this.iToYear < this.iFromYear) {
               throw new IllegalArgumentException();
            } else {
               this.iType = ZoneInfoCompiler.parseOptional(var1.nextToken());
               this.iDateTimeOfYear = new DateTimeOfYear(var1);
               this.iSaveMillis = ZoneInfoCompiler.parseTime(var1.nextToken());
               this.iLetterS = ZoneInfoCompiler.parseOptional(var1.nextToken());
            }
         }
      }

      Rule(Rule var1) {
         this.iName = var1.iName;
         this.iFromYear = 1800;
         this.iToYear = var1.iFromYear;
         this.iType = null;
         this.iDateTimeOfYear = var1.iDateTimeOfYear;
         this.iSaveMillis = 0;
         this.iLetterS = var1.iLetterS;
      }

      public void addRecurring(DateTimeZoneBuilder var1, int var2, int var3, String var4) {
         int var5 = this.iSaveMillis + -var3;
         String var6 = formatName(var4, var2, var5, this.iLetterS);
         this.iDateTimeOfYear.addRecurring(var1, var6, var5, this.iFromYear, this.iToYear);
      }

      static String formatName(String var0, int var1, int var2, String var3) {
         int var4 = var0.indexOf(47);
         if (var4 > 0) {
            return var2 == 0 ? var0.substring(0, var4).intern() : var0.substring(var4 + 1).intern();
         } else {
            var4 = var0.indexOf("%s");
            if (var4 >= 0) {
               String var5 = var0.substring(0, var4);
               String var6 = var0.substring(var4 + 2);
               String var7 = var5 + (var3 == null ? "" : var3) + var6;
               return var7.intern();
            } else if (var0.equals("%z")) {
               return var2 == 0 ? formatOffset(var1).intern() : formatOffset(var1 + var2).intern();
            } else {
               return var0;
            }
         }
      }

      private static String formatOffset(int var0) {
         String var1 = var0 < 0 ? "-" : "+";
         int var2 = Math.abs(var0) / 1000;
         int var3 = var2 / 3600;
         int var4 = var2 / 60 % 60;
         int var5 = var2 % 60;
         if (var5 == 0) {
            return var4 == 0 ? var1 + twoDigitString(var3) : var1 + twoDigitString(var3) + twoDigitString(var4);
         } else {
            return var1 + twoDigitString(var3) + twoDigitString(var4) + twoDigitString(var5);
         }
      }

      private static String twoDigitString(int var0) {
         return Integer.toString(var0 + 100).substring(1);
      }

      public String toString() {
         return "[Rule]\nName: " + this.iName + "\nFromYear: " + this.iFromYear + "\nToYear: " + this.iToYear + "\nType: " + this.iType + "\n" + this.iDateTimeOfYear + "SaveMillis: " + this.iSaveMillis + "\nLetterS: " + this.iLetterS + "\n";
      }
   }

   private static class RuleSet {
      private List iRules = new ArrayList();

      RuleSet(Rule var1) {
         this.iRules.add(var1);
      }

      void addRule(Rule var1) {
         if (!var1.iName.equals(((Rule)this.iRules.get(0)).iName)) {
            throw new IllegalArgumentException("Rule name mismatch");
         } else {
            this.iRules.add(var1);
         }
      }

      public void addRecurring(DateTimeZoneBuilder var1, int var2, String var3) {
         int var4 = 0;

         for(int var5 = 0; var5 < this.iRules.size(); ++var5) {
            Rule var6 = (Rule)this.iRules.get(var5);
            if (var6.iSaveMillis < 0) {
               var4 = Math.min(var4, var6.iSaveMillis);
            }
         }

         if (var4 < 0) {
            if (ZoneInfoLogger.verbose()) {
               System.out.println("Fixed negative save values for rule '" + ((Rule)this.iRules.get(0)).iName + "'");
            }

            var2 += var4;
            int var7 = var3.indexOf("/");
            if (var7 > 0) {
               var3 = var3.substring(var7 + 1) + "/" + var3.substring(0, var7);
            }
         }

         var1.setStandardOffset(var2);
         if (var4 < 0) {
            Rule var8 = new Rule((Rule)this.iRules.get(0));
            var8.addRecurring(var1, var2, var4, var3);
         }

         for(int var9 = 0; var9 < this.iRules.size(); ++var9) {
            Rule var10 = (Rule)this.iRules.get(var9);
            var10.addRecurring(var1, var2, var4, var3);
         }

      }
   }

   private static class Zone {
      public final String iName;
      public final int iOffsetMillis;
      public final String iRules;
      public final String iFormat;
      public final int iUntilYear;
      public final DateTimeOfYear iUntilDateTimeOfYear;
      private Zone iNext;

      Zone(StringTokenizer var1) {
         this(var1.nextToken(), var1);
      }

      private Zone(String var1, StringTokenizer var2) {
         this.iName = var1.intern();
         this.iOffsetMillis = ZoneInfoCompiler.parseTime(var2.nextToken());
         this.iRules = ZoneInfoCompiler.parseOptional(var2.nextToken());
         this.iFormat = var2.nextToken().intern();
         int var3 = Integer.MAX_VALUE;
         DateTimeOfYear var4 = ZoneInfoCompiler.getStartOfYear();
         if (var2.hasMoreTokens()) {
            var3 = Integer.parseInt(var2.nextToken());
            if (var2.hasMoreTokens()) {
               var4 = new DateTimeOfYear(var2);
            }
         }

         this.iUntilYear = var3;
         this.iUntilDateTimeOfYear = var4;
      }

      void chain(StringTokenizer var1) {
         if (this.iNext != null) {
            this.iNext.chain(var1);
         } else {
            this.iNext = new Zone(this.iName, var1);
         }

      }

      public void addToBuilder(DateTimeZoneBuilder var1, Map var2) {
         addToBuilder(this, var1, var2);
      }

      private static void addToBuilder(Zone var0, DateTimeZoneBuilder var1, Map var2) {
         while(var0 != null) {
            if (var0.iRules == null) {
               var1.setStandardOffset(var0.iOffsetMillis);
               var1.setFixedSavings(var0.iFormat, 0);
            } else {
               try {
                  int var3 = ZoneInfoCompiler.parseTime(var0.iRules);
                  var1.setStandardOffset(var0.iOffsetMillis);
                  var1.setFixedSavings(var0.iFormat, var3);
               } catch (Exception var5) {
                  RuleSet var4 = (RuleSet)var2.get(var0.iRules);
                  if (var4 == null) {
                     throw new IllegalArgumentException("Rules not found: " + var0.iRules);
                  }

                  var4.addRecurring(var1, var0.iOffsetMillis, var0.iFormat);
               }
            }

            if (var0.iUntilYear == Integer.MAX_VALUE) {
               break;
            }

            var0.iUntilDateTimeOfYear.addCutover(var1, var0.iUntilYear);
            var0 = var0.iNext;
         }

      }

      public String toString() {
         String var1 = "[Zone]\nName: " + this.iName + "\nOffsetMillis: " + this.iOffsetMillis + "\nRules: " + this.iRules + "\nFormat: " + this.iFormat + "\nUntilYear: " + this.iUntilYear + "\n" + this.iUntilDateTimeOfYear;
         return this.iNext == null ? var1 : var1 + "...\n" + this.iNext.toString();
      }
   }
}
