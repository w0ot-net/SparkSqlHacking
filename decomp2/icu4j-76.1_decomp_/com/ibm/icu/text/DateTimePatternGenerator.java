package com.ibm.icu.text;

import com.ibm.icu.impl.ICUCache;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.PatternTokenizer;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.Region;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DateTimePatternGenerator implements Freezable, Cloneable {
   private static final boolean DEBUG = false;
   private static final String[] LAST_RESORT_ALLOWED_HOUR_FORMAT = new String[]{"H"};
   static final Map LOCALE_TO_ALLOWED_HOUR;
   public static final int ERA = 0;
   public static final int YEAR = 1;
   public static final int QUARTER = 2;
   public static final int MONTH = 3;
   public static final int WEEK_OF_YEAR = 4;
   public static final int WEEK_OF_MONTH = 5;
   public static final int WEEKDAY = 6;
   public static final int DAY = 7;
   public static final int DAY_OF_YEAR = 8;
   public static final int DAY_OF_WEEK_IN_MONTH = 9;
   public static final int DAYPERIOD = 10;
   public static final int HOUR = 11;
   public static final int MINUTE = 12;
   public static final int SECOND = 13;
   public static final int FRACTIONAL_SECOND = 14;
   public static final int ZONE = 15;
   /** @deprecated */
   @Deprecated
   public static final int TYPE_LIMIT = 16;
   private static final DisplayWidth APPENDITEM_WIDTH;
   private static final int APPENDITEM_WIDTH_INT;
   private static final DisplayWidth[] CLDR_FIELD_WIDTH;
   public static final int MATCH_NO_OPTIONS = 0;
   public static final int MATCH_HOUR_FIELD_LENGTH = 2048;
   /** @deprecated */
   @Deprecated
   public static final int MATCH_MINUTE_FIELD_LENGTH = 4096;
   /** @deprecated */
   @Deprecated
   public static final int MATCH_SECOND_FIELD_LENGTH = 8192;
   public static final int MATCH_ALL_FIELDS_LENGTH = 65535;
   private TreeMap skeleton2pattern = new TreeMap();
   private TreeMap basePattern_pattern = new TreeMap();
   private String decimal = "?";
   private String[] dateTimeFormats = new String[]{"{1} {0}", "{1} {0}", "{1} {0}", "{1} {0}"};
   private String[] appendItemFormats = new String[16];
   private String[][] fieldDisplayNames;
   private char defaultHourFormatChar;
   private volatile boolean frozen;
   private transient DateTimeMatcher current;
   private transient FormatParser fp;
   private transient DistanceInfo _distanceInfo;
   private String[] allowedHourFormats;
   private static final int FRACTIONAL_MASK = 16384;
   private static final int SECOND_AND_FRACTIONAL_MASK = 24576;
   private static ICUCache DTPNG_CACHE;
   private static final String[] CLDR_FIELD_APPEND;
   private static final String[] CLDR_FIELD_NAME;
   private static final String[] FIELD_NAME;
   private static final String[] CANONICAL_ITEMS;
   private static final Set CANONICAL_SET;
   private Set cldrAvailableFormatKeys;
   private static final int DATE_MASK = 1023;
   private static final int TIME_MASK = 64512;
   private static final int DELTA = 16;
   private static final int NUMERIC = 256;
   private static final int NONE = 0;
   private static final int NARROW = -257;
   private static final int SHORTER = -258;
   private static final int SHORT = -259;
   private static final int LONG = -260;
   private static final int EXTRA_FIELD = 65536;
   private static final int MISSING_FIELD = 4096;
   private static final int[][] types;

   public static DateTimePatternGenerator getEmptyInstance() {
      DateTimePatternGenerator instance = new DateTimePatternGenerator();
      instance.addCanonicalItems();
      instance.fillInMissing();
      return instance;
   }

   protected DateTimePatternGenerator() {
      this.fieldDisplayNames = new String[16][DateTimePatternGenerator.DisplayWidth.COUNT];
      this.defaultHourFormatChar = 'H';
      this.frozen = false;
      this.current = new DateTimeMatcher();
      this.fp = new FormatParser();
      this._distanceInfo = new DistanceInfo();
      this.cldrAvailableFormatKeys = new HashSet(20);
   }

   public static DateTimePatternGenerator getInstance() {
      return getInstance(ULocale.getDefault(ULocale.Category.FORMAT));
   }

   public static DateTimePatternGenerator getInstance(ULocale uLocale) {
      return getFrozenInstance(uLocale).cloneAsThawed();
   }

   public static DateTimePatternGenerator getInstance(Locale locale) {
      return getInstance(ULocale.forLocale(locale));
   }

   /** @deprecated */
   @Deprecated
   public static DateTimePatternGenerator getFrozenInstance(ULocale uLocale) {
      String localeKey = uLocale.toString();
      DateTimePatternGenerator result = (DateTimePatternGenerator)DTPNG_CACHE.get(localeKey);
      if (result != null) {
         return result;
      } else {
         result = new DateTimePatternGenerator();
         result.initData(uLocale, false);
         result.freeze();
         DTPNG_CACHE.put(localeKey, result);
         return result;
      }
   }

   /** @deprecated */
   @Deprecated
   public static DateTimePatternGenerator getInstanceNoStdPat(ULocale uLocale) {
      DateTimePatternGenerator result = new DateTimePatternGenerator();
      result.initData(uLocale, true);
      return result;
   }

   private void initData(ULocale uLocale, boolean skipStdPatterns) {
      PatternInfo returnInfo = new PatternInfo();
      this.addCanonicalItems();
      if (!skipStdPatterns) {
         this.addICUPatterns(returnInfo, uLocale);
      }

      this.addCLDRData(returnInfo, uLocale);
      if (!skipStdPatterns) {
         this.setDateTimeFromCalendar(uLocale);
      } else {
         this.setDateTimeFormat("{1} {0}");
      }

      this.setDecimalSymbols(uLocale);
      this.getAllowedHourFormats(uLocale);
      this.fillInMissing();
   }

   private void addICUPatterns(PatternInfo returnInfo, ULocale uLocale) {
      ICUResourceBundle rb = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", uLocale);
      String calendarTypeToUse = this.getCalendarTypeToUse(uLocale);
      ICUResourceBundle dateTimePatterns = rb.getWithFallback("calendar/" + calendarTypeToUse + "/DateTimePatterns");
      if (dateTimePatterns.getType() == 8 && dateTimePatterns.getSize() >= 8) {
         for(int i = 0; i < 8; ++i) {
            UResourceBundle patternRes = dateTimePatterns.get(i);
            String pattern;
            switch (patternRes.getType()) {
               case 0:
                  pattern = patternRes.getString();
                  break;
               case 8:
                  pattern = patternRes.getString(0);
                  break;
               default:
                  throw new MissingResourceException("Resource in wrong format", "ICUResourceBundle", "calendar/" + calendarTypeToUse + "/DateTimePatterns");
            }

            this.addPattern(pattern, false, returnInfo);
         }

      } else {
         throw new MissingResourceException("Resource in wrong format", "ICUResourceBundle", "calendar/" + calendarTypeToUse + "/DateTimePatterns");
      }
   }

   private String getCalendarTypeToUse(ULocale uLocale) {
      String calendarTypeToUse = uLocale.getKeywordValue("calendar");
      if (calendarTypeToUse == null) {
         String[] preferredCalendarTypes = Calendar.getKeywordValuesForLocale("calendar", uLocale, true);
         calendarTypeToUse = preferredCalendarTypes[0];
      }

      if (calendarTypeToUse == null) {
         calendarTypeToUse = "gregorian";
      }

      return calendarTypeToUse;
   }

   private void consumeShortTimePattern(String shortTimePattern, PatternInfo returnInfo) {
      this.hackTimes(returnInfo, shortTimePattern);
   }

   private void fillInMissing() {
      for(int i = 0; i < 16; ++i) {
         if (this.getAppendItemFormat(i) == null) {
            this.setAppendItemFormat(i, "{0} ├{2}: {1}┤");
         }

         if (this.getFieldDisplayName(i, DateTimePatternGenerator.DisplayWidth.WIDE) == null) {
            this.setFieldDisplayName(i, DateTimePatternGenerator.DisplayWidth.WIDE, "F" + i);
         }

         if (this.getFieldDisplayName(i, DateTimePatternGenerator.DisplayWidth.ABBREVIATED) == null) {
            this.setFieldDisplayName(i, DateTimePatternGenerator.DisplayWidth.ABBREVIATED, this.getFieldDisplayName(i, DateTimePatternGenerator.DisplayWidth.WIDE));
         }

         if (this.getFieldDisplayName(i, DateTimePatternGenerator.DisplayWidth.NARROW) == null) {
            this.setFieldDisplayName(i, DateTimePatternGenerator.DisplayWidth.NARROW, this.getFieldDisplayName(i, DateTimePatternGenerator.DisplayWidth.ABBREVIATED));
         }
      }

   }

   private void addCLDRData(PatternInfo returnInfo, ULocale uLocale) {
      ICUResourceBundle rb = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", uLocale);
      String calendarTypeToUse = this.getCalendarTypeToUse(uLocale);
      AppendItemFormatsSink appendItemFormatsSink = new AppendItemFormatsSink();

      try {
         rb.getAllChildrenWithFallback("calendar/" + calendarTypeToUse + "/appendItems", appendItemFormatsSink);
      } catch (MissingResourceException var11) {
      }

      AppendItemNamesSink appendItemNamesSink = new AppendItemNamesSink();

      try {
         rb.getAllChildrenWithFallback("fields", appendItemNamesSink);
      } catch (MissingResourceException var10) {
      }

      AvailableFormatsSink availableFormatsSink = new AvailableFormatsSink(returnInfo);

      try {
         rb.getAllChildrenWithFallback("calendar/" + calendarTypeToUse + "/availableFormats", availableFormatsSink);
      } catch (MissingResourceException var9) {
      }

   }

   private void setDateTimeFromCalendar(ULocale uLocale) {
      Calendar cal = Calendar.getInstance(uLocale);

      for(int style = 0; style <= 3; ++style) {
         String dateTimeFormat = Calendar.getDateAtTimePattern(cal, uLocale, style);
         this.setDateTimeFormat(style, dateTimeFormat);
      }

   }

   private void setDecimalSymbols(ULocale uLocale) {
      DecimalFormatSymbols dfs = new DecimalFormatSymbols(uLocale);
      this.setDecimal(String.valueOf(dfs.getDecimalSeparator()));
   }

   private String[] getAllowedHourFormatsLangCountry(String language, String country) {
      String langCountry = language + "_" + country;
      String[] list = (String[])LOCALE_TO_ALLOWED_HOUR.get(langCountry);
      if (list == null) {
         list = (String[])LOCALE_TO_ALLOWED_HOUR.get(country);
      }

      return list;
   }

   private void getAllowedHourFormats(ULocale uLocale) {
      String language = uLocale.getLanguage();
      String country = ULocale.getRegionForSupplementalData(uLocale, false);
      if (language.isEmpty() || country.isEmpty()) {
         ULocale max = ULocale.addLikelySubtags(uLocale);
         language = max.getLanguage();
         country = max.getCountry();
      }

      if (language.isEmpty()) {
         language = "und";
      }

      if (country.isEmpty()) {
         country = "001";
      }

      String[] list = this.getAllowedHourFormatsLangCountry(language, country);
      Character defaultCharFromLocale = null;
      String hourCycle = uLocale.getKeywordValue("hours");
      if (hourCycle != null) {
         switch (hourCycle) {
            case "h24":
               defaultCharFromLocale = 'k';
               break;
            case "h23":
               defaultCharFromLocale = 'H';
               break;
            case "h12":
               defaultCharFromLocale = 'h';
               break;
            case "h11":
               defaultCharFromLocale = 'K';
         }
      }

      if (list == null) {
         try {
            Region region = Region.getInstance(country);
            country = region.toString();
            list = this.getAllowedHourFormatsLangCountry(language, country);
         } catch (IllegalArgumentException var9) {
         }
      }

      if (list != null) {
         this.defaultHourFormatChar = defaultCharFromLocale != null ? defaultCharFromLocale : list[0].charAt(0);
         this.allowedHourFormats = (String[])Arrays.copyOfRange(list, 1, list.length - 1);
      } else {
         this.allowedHourFormats = LAST_RESORT_ALLOWED_HOUR_FORMAT;
         this.defaultHourFormatChar = defaultCharFromLocale != null ? defaultCharFromLocale : this.allowedHourFormats[0].charAt(0);
      }

   }

   /** @deprecated */
   @Deprecated
   public char getDefaultHourFormatChar() {
      return this.defaultHourFormatChar;
   }

   /** @deprecated */
   @Deprecated
   public void setDefaultHourFormatChar(char defaultHourFormatChar) {
      this.defaultHourFormatChar = defaultHourFormatChar;
   }

   private void hackTimes(PatternInfo returnInfo, String shortTimePattern) {
      this.fp.set(shortTimePattern);
      StringBuilder mmss = new StringBuilder();
      boolean gotMm = false;

      for(int i = 0; i < this.fp.items.size(); ++i) {
         Object item = this.fp.items.get(i);
         if (item instanceof String) {
            if (gotMm) {
               mmss.append(this.fp.quoteLiteral(item.toString()));
            }
         } else {
            char ch = item.toString().charAt(0);
            if (ch == 'm') {
               gotMm = true;
               mmss.append(item);
            } else {
               if (ch == 's') {
                  if (gotMm) {
                     mmss.append(item);
                     this.addPattern(mmss.toString(), false, returnInfo);
                  }
                  break;
               }

               if (gotMm || ch == 'z' || ch == 'Z' || ch == 'v' || ch == 'V') {
                  break;
               }
            }
         }
      }

      BitSet variables = new BitSet();
      BitSet nuke = new BitSet();

      for(int i = 0; i < this.fp.items.size(); ++i) {
         Object item = this.fp.items.get(i);
         if (item instanceof VariableField) {
            variables.set(i);
            char ch = item.toString().charAt(0);
            if (ch == 's' || ch == 'S') {
               nuke.set(i);

               for(int j = i - 1; j >= 0 && !variables.get(j); ++j) {
                  nuke.set(i);
               }
            }
         }
      }

      String hhmm = getFilteredPattern(this.fp, nuke);
      this.addPattern(hhmm, false, returnInfo);
   }

   private static String getFilteredPattern(FormatParser fp, BitSet nuke) {
      StringBuilder result = new StringBuilder();

      for(int i = 0; i < fp.items.size(); ++i) {
         if (!nuke.get(i)) {
            Object item = fp.items.get(i);
            if (item instanceof String) {
               result.append(fp.quoteLiteral(item.toString()));
            } else {
               result.append(item.toString());
            }
         }
      }

      return result.toString();
   }

   /** @deprecated */
   @Deprecated
   public static int getAppendFormatNumber(UResource.Key key) {
      for(int i = 0; i < CLDR_FIELD_APPEND.length; ++i) {
         if (key.contentEquals(CLDR_FIELD_APPEND[i])) {
            return i;
         }
      }

      return -1;
   }

   /** @deprecated */
   @Deprecated
   public static int getAppendFormatNumber(String string) {
      for(int i = 0; i < CLDR_FIELD_APPEND.length; ++i) {
         if (CLDR_FIELD_APPEND[i].equals(string)) {
            return i;
         }
      }

      return -1;
   }

   private static int getCLDRFieldAndWidthNumber(UResource.Key key) {
      for(int i = 0; i < CLDR_FIELD_NAME.length; ++i) {
         for(int j = 0; j < DateTimePatternGenerator.DisplayWidth.COUNT; ++j) {
            String fullKey = CLDR_FIELD_NAME[i].concat(CLDR_FIELD_WIDTH[j].cldrKey());
            if (key.contentEquals(fullKey)) {
               return i * DateTimePatternGenerator.DisplayWidth.COUNT + j;
            }
         }
      }

      return -1;
   }

   public String getBestPattern(String skeleton) {
      return this.getBestPattern(skeleton, (DateTimeMatcher)null, 0);
   }

   public String getBestPattern(String skeleton, int options) {
      return this.getBestPattern(skeleton, (DateTimeMatcher)null, options);
   }

   private String getBestPattern(String skeleton, DateTimeMatcher skipMatcher, int options) {
      EnumSet<DTPGflags> flags = EnumSet.noneOf(DTPGflags.class);
      String skeletonMapped = this.mapSkeletonMetacharacters(skeleton, flags);
      String datePattern;
      String timePattern;
      synchronized(this) {
         this.current.set(skeletonMapped, this.fp, false);
         PatternWithMatcher bestWithMatcher = this.getBestRaw(this.current, -1, this._distanceInfo, skipMatcher);
         if (this._distanceInfo.missingFieldMask == 0 && this._distanceInfo.extraFieldMask == 0) {
            return this.adjustFieldTypes(bestWithMatcher, this.current, flags, options);
         }

         int neededFields = this.current.getFieldMask();
         datePattern = this.getBestAppending(this.current, neededFields & 1023, this._distanceInfo, skipMatcher, flags, options);
         timePattern = this.getBestAppending(this.current, neededFields & 'ﰀ', this._distanceInfo, skipMatcher, flags, options);
      }

      if (datePattern == null) {
         return timePattern == null ? "" : timePattern;
      } else if (timePattern == null) {
         return datePattern;
      } else {
         String canonicalSkeleton = this.current.toCanonicalString();
         int style = 3;
         int monthFieldLen = 0;
         int monthFieldOffset = canonicalSkeleton.indexOf(77);
         if (monthFieldOffset >= 0) {
            monthFieldLen = 1 + canonicalSkeleton.lastIndexOf(77) - monthFieldOffset;
         }

         if (monthFieldLen == 4) {
            if (canonicalSkeleton.indexOf(69) >= 0) {
               style = 0;
            } else {
               style = 1;
            }
         } else if (monthFieldLen == 3) {
            style = 2;
         }

         return SimpleFormatterImpl.formatRawPattern(this.getDateTimeFormat(style), 2, 2, timePattern, datePattern);
      }
   }

   private String mapSkeletonMetacharacters(String skeleton, EnumSet flags) {
      StringBuilder skeletonCopy = new StringBuilder();
      boolean inQuoted = false;

      for(int patPos = 0; patPos < skeleton.length(); ++patPos) {
         char patChr = skeleton.charAt(patPos);
         if (patChr == '\'') {
            inQuoted = !inQuoted;
         } else if (!inQuoted) {
            if (patChr != 'j' && patChr != 'C') {
               if (patChr == 'J') {
                  skeletonCopy.append('H');
                  flags.add(DateTimePatternGenerator.DTPGflags.SKELETON_USES_CAP_J);
               } else {
                  skeletonCopy.append(patChr);
               }
            } else {
               int extraLen;
               for(extraLen = 0; patPos + 1 < skeleton.length() && skeleton.charAt(patPos + 1) == patChr; ++patPos) {
                  ++extraLen;
               }

               int hourLen = 1 + (extraLen & 1);
               int dayPeriodLen = extraLen < 2 ? 1 : 3 + (extraLen >> 1);
               char hourChar = 'h';
               char dayPeriodChar = 'a';
               if (patChr == 'j') {
                  hourChar = this.defaultHourFormatChar;
               } else {
                  String bestAllowed = this.allowedHourFormats[0];
                  hourChar = bestAllowed.charAt(0);
                  char last = bestAllowed.charAt(bestAllowed.length() - 1);
                  if (last == 'b' || last == 'B') {
                     dayPeriodChar = last;
                  }
               }

               if (hourChar == 'H' || hourChar == 'k') {
                  dayPeriodLen = 0;
               }

               while(dayPeriodLen-- > 0) {
                  skeletonCopy.append(dayPeriodChar);
               }

               while(hourLen-- > 0) {
                  skeletonCopy.append(hourChar);
               }
            }
         }
      }

      return skeletonCopy.toString();
   }

   public DateTimePatternGenerator addPattern(String pattern, boolean override, PatternInfo returnInfo) {
      return this.addPatternWithSkeleton(pattern, (String)null, override, returnInfo);
   }

   /** @deprecated */
   @Deprecated
   public DateTimePatternGenerator addPatternWithSkeleton(String pattern, String skeletonToUse, boolean override, PatternInfo returnInfo) {
      this.checkFrozen();
      DateTimeMatcher matcher;
      if (skeletonToUse == null) {
         matcher = (new DateTimeMatcher()).set(pattern, this.fp, false);
      } else {
         matcher = (new DateTimeMatcher()).set(skeletonToUse, this.fp, false);
      }

      String basePattern = matcher.getBasePattern();
      PatternWithSkeletonFlag previousPatternWithSameBase = (PatternWithSkeletonFlag)this.basePattern_pattern.get(basePattern);
      if (previousPatternWithSameBase != null && (!previousPatternWithSameBase.skeletonWasSpecified || skeletonToUse != null && !override)) {
         returnInfo.status = 1;
         returnInfo.conflictingPattern = previousPatternWithSameBase.pattern;
         if (!override) {
            return this;
         }
      }

      PatternWithSkeletonFlag previousValue = (PatternWithSkeletonFlag)this.skeleton2pattern.get(matcher);
      if (previousValue != null) {
         returnInfo.status = 2;
         returnInfo.conflictingPattern = previousValue.pattern;
         if (!override || skeletonToUse != null && previousValue.skeletonWasSpecified) {
            return this;
         }
      }

      returnInfo.status = 0;
      returnInfo.conflictingPattern = "";
      PatternWithSkeletonFlag patWithSkelFlag = new PatternWithSkeletonFlag(pattern, skeletonToUse != null);
      this.skeleton2pattern.put(matcher, patWithSkelFlag);
      this.basePattern_pattern.put(basePattern, patWithSkelFlag);
      return this;
   }

   public String getSkeleton(String pattern) {
      synchronized(this) {
         this.current.set(pattern, this.fp, false);
         return this.current.toString();
      }
   }

   /** @deprecated */
   @Deprecated
   public String getSkeletonAllowingDuplicates(String pattern) {
      synchronized(this) {
         this.current.set(pattern, this.fp, true);
         return this.current.toString();
      }
   }

   /** @deprecated */
   @Deprecated
   public String getCanonicalSkeletonAllowingDuplicates(String pattern) {
      synchronized(this) {
         this.current.set(pattern, this.fp, true);
         return this.current.toCanonicalString();
      }
   }

   public String getBaseSkeleton(String pattern) {
      synchronized(this) {
         this.current.set(pattern, this.fp, false);
         return this.current.getBasePattern();
      }
   }

   public Map getSkeletons(Map result) {
      if (result == null) {
         result = new LinkedHashMap();
      }

      for(DateTimeMatcher item : this.skeleton2pattern.keySet()) {
         PatternWithSkeletonFlag patternWithSkelFlag = (PatternWithSkeletonFlag)this.skeleton2pattern.get(item);
         String pattern = patternWithSkelFlag.pattern;
         if (!CANONICAL_SET.contains(pattern)) {
            result.put(item.toString(), pattern);
         }
      }

      return result;
   }

   public Set getBaseSkeletons(Set result) {
      if (result == null) {
         result = new HashSet();
      }

      result.addAll(this.basePattern_pattern.keySet());
      return result;
   }

   public String replaceFieldTypes(String pattern, String skeleton) {
      return this.replaceFieldTypes(pattern, skeleton, 0);
   }

   public String replaceFieldTypes(String pattern, String skeleton, int options) {
      synchronized(this) {
         PatternWithMatcher patternNoMatcher = new PatternWithMatcher(pattern, (DateTimeMatcher)null);
         return this.adjustFieldTypes(patternNoMatcher, this.current.set(skeleton, this.fp, false), EnumSet.noneOf(DTPGflags.class), options);
      }
   }

   public void setDateTimeFormat(String dateTimeFormat) {
      this.checkFrozen();

      for(int style = 0; style <= 3; ++style) {
         this.setDateTimeFormat(style, dateTimeFormat);
      }

   }

   public String getDateTimeFormat() {
      return this.getDateTimeFormat(2);
   }

   public void setDateTimeFormat(int style, String dateTimeFormat) {
      if (style >= 0 && style <= 3) {
         this.checkFrozen();
         this.dateTimeFormats[style] = dateTimeFormat;
      } else {
         throw new IllegalArgumentException("Illegal style here: " + style);
      }
   }

   public String getDateTimeFormat(int style) {
      if (style >= 0 && style <= 3) {
         return this.dateTimeFormats[style];
      } else {
         throw new IllegalArgumentException("Illegal style here: " + style);
      }
   }

   public void setDecimal(String decimal) {
      this.checkFrozen();
      this.decimal = decimal;
   }

   public String getDecimal() {
      return this.decimal;
   }

   /** @deprecated */
   @Deprecated
   public Collection getRedundants(Collection output) {
      synchronized(this) {
         if (output == null) {
            output = new LinkedHashSet();
         }

         for(DateTimeMatcher cur : this.skeleton2pattern.keySet()) {
            PatternWithSkeletonFlag patternWithSkelFlag = (PatternWithSkeletonFlag)this.skeleton2pattern.get(cur);
            String pattern = patternWithSkelFlag.pattern;
            if (!CANONICAL_SET.contains(pattern)) {
               String trial = this.getBestPattern(cur.toString(), cur, 0);
               if (trial.equals(pattern)) {
                  output.add(pattern);
               }
            }
         }

         return output;
      }
   }

   public void setAppendItemFormat(int field, String value) {
      this.checkFrozen();
      this.appendItemFormats[field] = value;
   }

   public String getAppendItemFormat(int field) {
      return this.appendItemFormats[field];
   }

   public void setAppendItemName(int field, String value) {
      this.setFieldDisplayName(field, APPENDITEM_WIDTH, value);
   }

   public String getAppendItemName(int field) {
      return this.getFieldDisplayName(field, APPENDITEM_WIDTH);
   }

   public DateFormat.HourCycle getDefaultHourCycle() {
      switch (this.getDefaultHourFormatChar()) {
         case 'H':
            return DateFormat.HourCycle.HOUR_CYCLE_23;
         case 'K':
            return DateFormat.HourCycle.HOUR_CYCLE_11;
         case 'h':
            return DateFormat.HourCycle.HOUR_CYCLE_12;
         case 'k':
            return DateFormat.HourCycle.HOUR_CYCLE_24;
         default:
            throw new AssertionError("should be unreachable");
      }
   }

   /** @deprecated */
   @Deprecated
   private void setFieldDisplayName(int field, DisplayWidth width, String value) {
      this.checkFrozen();
      if (field < 16 && field >= 0) {
         this.fieldDisplayNames[field][width.ordinal()] = value;
      }

   }

   public String getFieldDisplayName(int field, DisplayWidth width) {
      return field < 16 && field >= 0 ? this.fieldDisplayNames[field][width.ordinal()] : "";
   }

   /** @deprecated */
   @Deprecated
   public static boolean isSingleField(String skeleton) {
      char first = skeleton.charAt(0);

      for(int i = 1; i < skeleton.length(); ++i) {
         if (skeleton.charAt(i) != first) {
            return false;
         }
      }

      return true;
   }

   private void setAvailableFormat(String key) {
      this.checkFrozen();
      this.cldrAvailableFormatKeys.add(key);
   }

   private boolean isAvailableFormatSet(String key) {
      return this.cldrAvailableFormatKeys.contains(key);
   }

   public boolean isFrozen() {
      return this.frozen;
   }

   public DateTimePatternGenerator freeze() {
      this.frozen = true;
      return this;
   }

   public DateTimePatternGenerator cloneAsThawed() {
      DateTimePatternGenerator result = (DateTimePatternGenerator)this.clone();
      this.frozen = false;
      return result;
   }

   public Object clone() {
      try {
         DateTimePatternGenerator result = (DateTimePatternGenerator)super.clone();
         result.skeleton2pattern = (TreeMap)this.skeleton2pattern.clone();
         result.basePattern_pattern = (TreeMap)this.basePattern_pattern.clone();
         result.dateTimeFormats = (String[])this.dateTimeFormats.clone();
         result.appendItemFormats = (String[])this.appendItemFormats.clone();
         result.fieldDisplayNames = (String[][])this.fieldDisplayNames.clone();
         result.current = new DateTimeMatcher();
         result.fp = new FormatParser();
         result._distanceInfo = new DistanceInfo();
         result.frozen = false;
         return result;
      } catch (CloneNotSupportedException e) {
         throw new ICUCloneNotSupportedException("Internal Error", e);
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean skeletonsAreSimilar(String id, String skeleton) {
      if (id.equals(skeleton)) {
         return true;
      } else {
         TreeSet<String> parser1 = this.getSet(id);
         TreeSet<String> parser2 = this.getSet(skeleton);
         if (parser1.size() != parser2.size()) {
            return false;
         } else {
            Iterator<String> it2 = parser2.iterator();

            for(String item : parser1) {
               int index1 = getCanonicalIndex(item, false);
               String item2 = (String)it2.next();
               int index2 = getCanonicalIndex(item2, false);
               if (types[index1][1] != types[index2][1]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private TreeSet getSet(String id) {
      List<Object> items = this.fp.set(id).getItems();
      TreeSet<String> result = new TreeSet();

      for(Object obj : items) {
         String item = obj.toString();
         if (!item.startsWith("G") && !item.startsWith("a")) {
            result.add(item);
         }
      }

      return result;
   }

   private void checkFrozen() {
      if (this.isFrozen()) {
         throw new UnsupportedOperationException("Attempt to modify frozen object");
      }
   }

   private String getBestAppending(DateTimeMatcher source, int missingFields, DistanceInfo distInfo, DateTimeMatcher skipMatcher, EnumSet flags, int options) {
      String resultPattern = null;
      if (missingFields != 0) {
         PatternWithMatcher resultPatternWithMatcher = this.getBestRaw(source, missingFields, distInfo, skipMatcher);
         resultPattern = this.adjustFieldTypes(resultPatternWithMatcher, source, flags, options);

         while(distInfo.missingFieldMask != 0) {
            if ((distInfo.missingFieldMask & 24576) == 16384 && (missingFields & 24576) == 24576) {
               resultPatternWithMatcher.pattern = resultPattern;
               flags = EnumSet.copyOf(flags);
               flags.add(DateTimePatternGenerator.DTPGflags.FIX_FRACTIONAL_SECONDS);
               resultPattern = this.adjustFieldTypes(resultPatternWithMatcher, source, flags, options);
               distInfo.missingFieldMask &= -16385;
            } else {
               int startingMask = distInfo.missingFieldMask;
               PatternWithMatcher tempWithMatcher = this.getBestRaw(source, distInfo.missingFieldMask, distInfo, skipMatcher);
               String temp = this.adjustFieldTypes(tempWithMatcher, source, flags, options);
               int foundMask = startingMask & ~distInfo.missingFieldMask;
               int topField = this.getTopBitNumber(foundMask);
               resultPattern = SimpleFormatterImpl.formatRawPattern(this.getAppendFormat(topField), 2, 3, resultPattern, temp, this.getAppendName(topField));
            }
         }
      }

      return resultPattern;
   }

   private String getAppendName(int foundMask) {
      return "'" + this.fieldDisplayNames[foundMask][APPENDITEM_WIDTH_INT] + "'";
   }

   private String getAppendFormat(int foundMask) {
      return this.appendItemFormats[foundMask];
   }

   private int getTopBitNumber(int foundMask) {
      int i;
      for(i = 0; foundMask != 0; ++i) {
         foundMask >>>= 1;
      }

      return i - 1;
   }

   private void addCanonicalItems() {
      PatternInfo patternInfo = new PatternInfo();

      for(int i = 0; i < CANONICAL_ITEMS.length; ++i) {
         this.addPattern(String.valueOf(CANONICAL_ITEMS[i]), false, patternInfo);
      }

   }

   private PatternWithMatcher getBestRaw(DateTimeMatcher source, int includeMask, DistanceInfo missingFields, DateTimeMatcher skipMatcher) {
      int bestDistance = Integer.MAX_VALUE;
      int bestMissingFieldMask = Integer.MIN_VALUE;
      PatternWithMatcher bestPatternWithMatcher = new PatternWithMatcher("", (DateTimeMatcher)null);
      DistanceInfo tempInfo = new DistanceInfo();

      for(DateTimeMatcher trial : this.skeleton2pattern.keySet()) {
         if (!trial.equals(skipMatcher)) {
            int distance = source.getDistance(trial, includeMask, tempInfo);
            if (distance < bestDistance || distance == bestDistance && bestMissingFieldMask < tempInfo.missingFieldMask) {
               bestDistance = distance;
               bestMissingFieldMask = tempInfo.missingFieldMask;
               PatternWithSkeletonFlag patternWithSkelFlag = (PatternWithSkeletonFlag)this.skeleton2pattern.get(trial);
               bestPatternWithMatcher.pattern = patternWithSkelFlag.pattern;
               if (patternWithSkelFlag.skeletonWasSpecified) {
                  bestPatternWithMatcher.matcherWithSkeleton = trial;
               } else {
                  bestPatternWithMatcher.matcherWithSkeleton = null;
               }

               missingFields.setTo(tempInfo);
               if (distance == 0) {
                  break;
               }
            }
         }
      }

      return bestPatternWithMatcher;
   }

   private String adjustFieldTypes(PatternWithMatcher patternWithMatcher, DateTimeMatcher inputRequest, EnumSet flags, int options) {
      this.fp.set(patternWithMatcher.pattern);
      StringBuilder newPattern = new StringBuilder();

      for(Object item : this.fp.getItems()) {
         if (item instanceof String) {
            newPattern.append(this.fp.quoteLiteral((String)item));
         } else {
            VariableField variableField = (VariableField)item;
            StringBuilder fieldBuilder = new StringBuilder(variableField.toString());
            int type = variableField.getType();
            if (flags.contains(DateTimePatternGenerator.DTPGflags.FIX_FRACTIONAL_SECONDS) && type == 13) {
               fieldBuilder.append(this.decimal);
               inputRequest.original.appendFieldTo(14, fieldBuilder);
            } else if (inputRequest.type[type] != 0) {
               char reqFieldChar = inputRequest.original.getFieldChar(type);
               int reqFieldLen = inputRequest.original.getFieldLength(type);
               if (reqFieldChar == 'E' && reqFieldLen < 3) {
                  reqFieldLen = 3;
               }

               int adjFieldLen = reqFieldLen;
               DateTimeMatcher matcherWithSkeleton = patternWithMatcher.matcherWithSkeleton;
               if ((type != 11 || (options & 2048) != 0) && (type != 12 || (options & 4096) != 0) && (type != 13 || (options & 8192) != 0)) {
                  if (matcherWithSkeleton != null && reqFieldChar != 'c' && reqFieldChar != 'e') {
                     int skelFieldLen = matcherWithSkeleton.original.getFieldLength(type);
                     boolean patFieldIsNumeric = variableField.isNumeric();
                     boolean skelFieldIsNumeric = matcherWithSkeleton.fieldIsNumeric(type);
                     if (skelFieldLen == reqFieldLen || patFieldIsNumeric && !skelFieldIsNumeric || skelFieldIsNumeric && !patFieldIsNumeric) {
                        adjFieldLen = fieldBuilder.length();
                     }
                  }
               } else {
                  adjFieldLen = fieldBuilder.length();
               }

               char c = type == 11 || type == 3 || type == 6 || type == 1 && reqFieldChar != 'Y' ? fieldBuilder.charAt(0) : reqFieldChar;
               if (c == 'E' && adjFieldLen < 3) {
                  c = 'e';
               }

               if (type == 11) {
                  if (!flags.contains(DateTimePatternGenerator.DTPGflags.SKELETON_USES_CAP_J) && reqFieldChar != this.defaultHourFormatChar) {
                     if (reqFieldChar == 'h' && this.defaultHourFormatChar == 'K') {
                        c = 'K';
                     } else if (reqFieldChar == 'H' && this.defaultHourFormatChar == 'k') {
                        c = 'k';
                     } else if (reqFieldChar == 'k' && this.defaultHourFormatChar == 'H') {
                        c = 'H';
                     } else if (reqFieldChar == 'K' && this.defaultHourFormatChar == 'h') {
                        c = 'h';
                     }
                  } else {
                     c = this.defaultHourFormatChar;
                  }
               }

               fieldBuilder = new StringBuilder();

               for(int i = adjFieldLen; i > 0; --i) {
                  fieldBuilder.append(c);
               }
            }

            newPattern.append(fieldBuilder);
         }
      }

      return newPattern.toString();
   }

   /** @deprecated */
   @Deprecated
   public String getFields(String pattern) {
      this.fp.set(pattern);
      StringBuilder newPattern = new StringBuilder();

      for(Object item : this.fp.getItems()) {
         if (item instanceof String) {
            newPattern.append(this.fp.quoteLiteral((String)item));
         } else {
            newPattern.append("{" + getName(item.toString()) + "}");
         }
      }

      return newPattern.toString();
   }

   private static String showMask(int mask) {
      StringBuilder result = new StringBuilder();

      for(int i = 0; i < 16; ++i) {
         if ((mask & 1 << i) != 0) {
            if (result.length() != 0) {
               result.append(" | ");
            }

            result.append(FIELD_NAME[i]);
            result.append(" ");
         }
      }

      return result.toString();
   }

   private static String getName(String s) {
      int i = getCanonicalIndex(s, true);
      String name = FIELD_NAME[types[i][1]];
      if (types[i][2] < 0) {
         name = name + ":S";
      } else {
         name = name + ":N";
      }

      return name;
   }

   private static int getCanonicalIndex(String s, boolean strict) {
      int len = s.length();
      if (len == 0) {
         return -1;
      } else {
         int ch = s.charAt(0);

         for(int i = 1; i < len; ++i) {
            if (s.charAt(i) != ch) {
               return -1;
            }
         }

         int bestRow = -1;

         for(int i = 0; i < types.length; ++i) {
            int[] row = types[i];
            if (row[0] == ch) {
               bestRow = i;
               if (row[3] <= len && row[row.length - 1] >= len) {
                  return i;
               }
            }
         }

         return strict ? -1 : bestRow;
      }
   }

   private static char getCanonicalChar(int field, char reference) {
      if (reference != 'h' && reference != 'K') {
         for(int i = 0; i < types.length; ++i) {
            int[] row = types[i];
            if (row[1] == field) {
               return (char)row[0];
            }
         }

         throw new IllegalArgumentException("Could not find field " + field);
      } else {
         return 'h';
      }
   }

   static {
      HashMap<String, String[]> temp = new HashMap();
      ICUResourceBundle suppData = (ICUResourceBundle)ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
      DayPeriodAllowedHoursSink allowedHoursSink = new DayPeriodAllowedHoursSink(temp);
      suppData.getAllItemsWithFallback("timeData", allowedHoursSink);
      LOCALE_TO_ALLOWED_HOUR = Collections.unmodifiableMap(temp);
      APPENDITEM_WIDTH = DateTimePatternGenerator.DisplayWidth.WIDE;
      APPENDITEM_WIDTH_INT = APPENDITEM_WIDTH.ordinal();
      CLDR_FIELD_WIDTH = DateTimePatternGenerator.DisplayWidth.values();
      DTPNG_CACHE = new SimpleCache();
      CLDR_FIELD_APPEND = new String[]{"Era", "Year", "Quarter", "Month", "Week", "*", "Day-Of-Week", "Day", "*", "*", "*", "Hour", "Minute", "Second", "*", "Timezone"};
      CLDR_FIELD_NAME = new String[]{"era", "year", "quarter", "month", "week", "weekOfMonth", "weekday", "day", "dayOfYear", "weekdayOfMonth", "dayperiod", "hour", "minute", "second", "*", "zone"};
      FIELD_NAME = new String[]{"Era", "Year", "Quarter", "Month", "Week_in_Year", "Week_in_Month", "Weekday", "Day", "Day_Of_Year", "Day_of_Week_in_Month", "Dayperiod", "Hour", "Minute", "Second", "Fractional_Second", "Zone"};
      CANONICAL_ITEMS = new String[]{"G", "y", "Q", "M", "w", "W", "E", "d", "D", "F", "a", "H", "m", "s", "S", "v"};
      CANONICAL_SET = new HashSet(Arrays.asList(CANONICAL_ITEMS));
      types = new int[][]{{71, 0, -259, 1, 3}, {71, 0, -260, 4}, {71, 0, -257, 5}, {121, 1, 256, 1, 20}, {89, 1, 272, 1, 20}, {117, 1, 288, 1, 20}, {114, 1, 304, 1, 20}, {85, 1, -259, 1, 3}, {85, 1, -260, 4}, {85, 1, -257, 5}, {81, 2, 256, 1, 2}, {81, 2, -259, 3}, {81, 2, -260, 4}, {81, 2, -257, 5}, {113, 2, 272, 1, 2}, {113, 2, -275, 3}, {113, 2, -276, 4}, {113, 2, -273, 5}, {77, 3, 256, 1, 2}, {77, 3, -259, 3}, {77, 3, -260, 4}, {77, 3, -257, 5}, {76, 3, 272, 1, 2}, {76, 3, -275, 3}, {76, 3, -276, 4}, {76, 3, -273, 5}, {108, 3, 272, 1, 1}, {119, 4, 256, 1, 2}, {87, 5, 256, 1}, {69, 6, -259, 1, 3}, {69, 6, -260, 4}, {69, 6, -257, 5}, {69, 6, -258, 6}, {99, 6, 288, 1, 2}, {99, 6, -291, 3}, {99, 6, -292, 4}, {99, 6, -289, 5}, {99, 6, -290, 6}, {101, 6, 272, 1, 2}, {101, 6, -275, 3}, {101, 6, -276, 4}, {101, 6, -273, 5}, {101, 6, -274, 6}, {100, 7, 256, 1, 2}, {103, 7, 272, 1, 20}, {68, 8, 256, 1, 3}, {70, 9, 256, 1}, {97, 10, -259, 1, 3}, {97, 10, -260, 4}, {97, 10, -257, 5}, {98, 10, -275, 1, 3}, {98, 10, -276, 4}, {98, 10, -273, 5}, {66, 10, -307, 1, 3}, {66, 10, -308, 4}, {66, 10, -305, 5}, {72, 11, 416, 1, 2}, {107, 11, 432, 1, 2}, {104, 11, 256, 1, 2}, {75, 11, 272, 1, 2}, {109, 12, 256, 1, 2}, {115, 13, 256, 1, 2}, {65, 13, 272, 1, 1000}, {83, 14, 256, 1, 1000}, {118, 15, -291, 1}, {118, 15, -292, 4}, {122, 15, -259, 1, 3}, {122, 15, -260, 4}, {90, 15, -273, 1, 3}, {90, 15, -276, 4}, {90, 15, -275, 5}, {79, 15, -275, 1}, {79, 15, -276, 4}, {86, 15, -275, 1}, {86, 15, -276, 2}, {86, 15, -277, 3}, {86, 15, -278, 4}, {88, 15, -273, 1}, {88, 15, -275, 2}, {88, 15, -276, 4}, {120, 15, -273, 1}, {120, 15, -275, 2}, {120, 15, -276, 4}};
   }

   private class AppendItemFormatsSink extends UResource.Sink {
      private AppendItemFormatsSink() {
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         int field = DateTimePatternGenerator.getAppendFormatNumber(key);
         if (field >= 0) {
            if (DateTimePatternGenerator.this.getAppendItemFormat(field) == null) {
               DateTimePatternGenerator.this.setAppendItemFormat(field, value.toString());
            }

         }
      }
   }

   private class AppendItemNamesSink extends UResource.Sink {
      private AppendItemNamesSink() {
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         int fieldAndWidth = DateTimePatternGenerator.getCLDRFieldAndWidthNumber(key);
         if (fieldAndWidth != -1) {
            int field = fieldAndWidth / DateTimePatternGenerator.DisplayWidth.COUNT;
            DisplayWidth width = DateTimePatternGenerator.CLDR_FIELD_WIDTH[fieldAndWidth % DateTimePatternGenerator.DisplayWidth.COUNT];
            UResource.Table detailsTable = value.getTable();
            if (detailsTable.findValue("dn", value) && DateTimePatternGenerator.this.getFieldDisplayName(field, width) == null) {
               DateTimePatternGenerator.this.setFieldDisplayName(field, width, value.toString());
            }

         }
      }
   }

   private class AvailableFormatsSink extends UResource.Sink {
      PatternInfo returnInfo;

      public AvailableFormatsSink(PatternInfo returnInfo) {
         this.returnInfo = returnInfo;
      }

      public void put(UResource.Key key, UResource.Value value, boolean isRoot) {
         String formatKey = key.toString();
         if (!DateTimePatternGenerator.this.isAvailableFormatSet(formatKey)) {
            DateTimePatternGenerator.this.setAvailableFormat(formatKey);
            String formatValue = value.toString();
            DateTimePatternGenerator.this.addPatternWithSkeleton(formatValue, formatKey, true, this.returnInfo);
         }

      }
   }

   private static class DayPeriodAllowedHoursSink extends UResource.Sink {
      HashMap tempMap;

      private DayPeriodAllowedHoursSink(HashMap tempMap) {
         this.tempMap = tempMap;
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         UResource.Table timeData = value.getTable();

         for(int i = 0; timeData.getKeyAndValue(i, key, value); ++i) {
            String regionOrLocale = key.toString();
            UResource.Table formatList = value.getTable();
            String[] allowed = null;
            String preferred = null;

            for(int j = 0; formatList.getKeyAndValue(j, key, value); ++j) {
               if (key.contentEquals("allowed")) {
                  allowed = value.getStringArrayOrStringAsArray();
               } else if (key.contentEquals("preferred")) {
                  preferred = value.getString();
               }
            }

            String[] list = null;
            if (allowed != null && allowed.length > 0) {
               list = new String[allowed.length + 1];
               list[0] = preferred != null ? preferred : allowed[0];
               System.arraycopy(allowed, 0, list, 1, allowed.length);
            } else {
               list = new String[]{preferred != null ? preferred : DateTimePatternGenerator.LAST_RESORT_ALLOWED_HOUR_FORMAT[0], null};
               list[1] = list[0];
            }

            this.tempMap.put(regionOrLocale, list);
         }

      }
   }

   public static final class PatternInfo {
      public static final int OK = 0;
      public static final int BASE_CONFLICT = 1;
      public static final int CONFLICT = 2;
      public int status;
      public String conflictingPattern;
   }

   public static enum DisplayWidth {
      WIDE(""),
      ABBREVIATED("-short"),
      NARROW("-narrow");

      /** @deprecated */
      @Deprecated
      private static int COUNT = values().length;
      private final String cldrKey;

      private DisplayWidth(String cldrKey) {
         this.cldrKey = cldrKey;
      }

      private String cldrKey() {
         return this.cldrKey;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class VariableField {
      private final String string;
      private final int canonicalIndex;

      /** @deprecated */
      @Deprecated
      public VariableField(String string) {
         this(string, false);
      }

      /** @deprecated */
      @Deprecated
      public VariableField(String string, boolean strict) {
         this.canonicalIndex = DateTimePatternGenerator.getCanonicalIndex(string, strict);
         if (this.canonicalIndex < 0) {
            throw new IllegalArgumentException("Illegal datetime field:\t" + string);
         } else {
            this.string = string;
         }
      }

      /** @deprecated */
      @Deprecated
      public int getType() {
         return DateTimePatternGenerator.types[this.canonicalIndex][1];
      }

      /** @deprecated */
      @Deprecated
      public static String getCanonicalCode(int type) {
         try {
            return DateTimePatternGenerator.CANONICAL_ITEMS[type];
         } catch (Exception var2) {
            return String.valueOf(type);
         }
      }

      /** @deprecated */
      @Deprecated
      public boolean isNumeric() {
         return DateTimePatternGenerator.types[this.canonicalIndex][2] > 0;
      }

      private int getCanonicalIndex() {
         return this.canonicalIndex;
      }

      /** @deprecated */
      @Deprecated
      public String toString() {
         return this.string;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class FormatParser {
      private static final UnicodeSet SYNTAX_CHARS = (new UnicodeSet("[a-zA-Z]")).freeze();
      private static final UnicodeSet QUOTING_CHARS = (new UnicodeSet("[[[:script=Latn:][:script=Cyrl:]]&[[:L:][:M:]]]")).freeze();
      private transient PatternTokenizer tokenizer;
      private List items;

      /** @deprecated */
      @Deprecated
      public FormatParser() {
         this.tokenizer = (new PatternTokenizer()).setSyntaxCharacters(SYNTAX_CHARS).setExtraQuotingCharacters(QUOTING_CHARS).setUsingQuote(true);
         this.items = new ArrayList();
      }

      /** @deprecated */
      @Deprecated
      public final FormatParser set(String string) {
         return this.set(string, false);
      }

      /** @deprecated */
      @Deprecated
      public FormatParser set(String string, boolean strict) {
         this.items.clear();
         if (string.length() == 0) {
            return this;
         } else {
            this.tokenizer.setPattern(string);
            StringBuffer buffer = new StringBuffer();
            StringBuffer variable = new StringBuffer();

            while(true) {
               buffer.setLength(0);
               int status = this.tokenizer.next(buffer);
               if (status == 0) {
                  this.addVariable(variable, false);
                  return this;
               }

               if (status == 1) {
                  if (variable.length() != 0 && buffer.charAt(0) != variable.charAt(0)) {
                     this.addVariable(variable, false);
                  }

                  variable.append(buffer);
               } else {
                  this.addVariable(variable, false);
                  this.items.add(buffer.toString());
               }
            }
         }
      }

      private void addVariable(StringBuffer variable, boolean strict) {
         if (variable.length() != 0) {
            this.items.add(new VariableField(variable.toString(), strict));
            variable.setLength(0);
         }

      }

      /** @deprecated */
      @Deprecated
      public List getItems() {
         return this.items;
      }

      /** @deprecated */
      @Deprecated
      public String toString() {
         return this.toString(0, this.items.size());
      }

      /** @deprecated */
      @Deprecated
      public String toString(int start, int limit) {
         StringBuilder result = new StringBuilder();

         for(int i = start; i < limit; ++i) {
            Object item = this.items.get(i);
            if (item instanceof String) {
               String itemString = (String)item;
               result.append(this.tokenizer.quoteLiteral(itemString));
            } else {
               result.append(this.items.get(i).toString());
            }
         }

         return result.toString();
      }

      /** @deprecated */
      @Deprecated
      public boolean hasDateAndTimeFields() {
         int foundMask = 0;

         for(Object item : this.items) {
            if (item instanceof VariableField) {
               int type = ((VariableField)item).getType();
               foundMask |= 1 << type;
            }
         }

         boolean isDate = (foundMask & 1023) != 0;
         boolean isTime = (foundMask & 'ﰀ') != 0;
         return isDate && isTime;
      }

      /** @deprecated */
      @Deprecated
      public Object quoteLiteral(String string) {
         return this.tokenizer.quoteLiteral(string);
      }
   }

   private static class PatternWithMatcher {
      public String pattern;
      public DateTimeMatcher matcherWithSkeleton;

      public PatternWithMatcher(String pat, DateTimeMatcher matcher) {
         this.pattern = pat;
         this.matcherWithSkeleton = matcher;
      }
   }

   private static class PatternWithSkeletonFlag {
      public String pattern;
      public boolean skeletonWasSpecified;

      public PatternWithSkeletonFlag(String pat, boolean skelSpecified) {
         this.pattern = pat;
         this.skeletonWasSpecified = skelSpecified;
      }

      public String toString() {
         return this.pattern + "," + this.skeletonWasSpecified;
      }
   }

   private static enum DTPGflags {
      FIX_FRACTIONAL_SECONDS,
      SKELETON_USES_CAP_J;
   }

   private static class SkeletonFields {
      private byte[] chars;
      private byte[] lengths;
      private static final byte DEFAULT_CHAR = 0;
      private static final byte DEFAULT_LENGTH = 0;

      private SkeletonFields() {
         this.chars = new byte[16];
         this.lengths = new byte[16];
      }

      public void clear() {
         Arrays.fill(this.chars, (byte)0);
         Arrays.fill(this.lengths, (byte)0);
      }

      void copyFieldFrom(SkeletonFields other, int field) {
         this.chars[field] = other.chars[field];
         this.lengths[field] = other.lengths[field];
      }

      void clearField(int field) {
         this.chars[field] = 0;
         this.lengths[field] = 0;
      }

      char getFieldChar(int field) {
         return (char)this.chars[field];
      }

      int getFieldLength(int field) {
         return this.lengths[field];
      }

      void populate(int field, String value) {
         for(char ch : value.toCharArray()) {
            assert ch == value.charAt(0);
         }

         this.populate(field, value.charAt(0), value.length());
      }

      void populate(int field, char ch, int length) {
         assert ch <= 127;

         assert length <= 127;

         this.chars[field] = (byte)ch;
         this.lengths[field] = (byte)length;
      }

      public boolean isFieldEmpty(int field) {
         return this.lengths[field] == 0;
      }

      public String toString() {
         return this.appendTo(new StringBuilder(), false, false).toString();
      }

      public String toString(boolean skipDayPeriod) {
         return this.appendTo(new StringBuilder(), false, skipDayPeriod).toString();
      }

      public String toCanonicalString() {
         return this.appendTo(new StringBuilder(), true, false).toString();
      }

      public String toCanonicalString(boolean skipDayPeriod) {
         return this.appendTo(new StringBuilder(), true, skipDayPeriod).toString();
      }

      public StringBuilder appendTo(StringBuilder sb) {
         return this.appendTo(sb, false, false);
      }

      private StringBuilder appendTo(StringBuilder sb, boolean canonical, boolean skipDayPeriod) {
         for(int i = 0; i < 16; ++i) {
            if (!skipDayPeriod || i != 10) {
               this.appendFieldTo(i, sb, canonical);
            }
         }

         return sb;
      }

      public StringBuilder appendFieldTo(int field, StringBuilder sb) {
         return this.appendFieldTo(field, sb, false);
      }

      private StringBuilder appendFieldTo(int field, StringBuilder sb, boolean canonical) {
         char ch = (char)this.chars[field];
         int length = this.lengths[field];
         if (canonical) {
            ch = DateTimePatternGenerator.getCanonicalChar(field, ch);
         }

         for(int i = 0; i < length; ++i) {
            sb.append(ch);
         }

         return sb;
      }

      public int compareTo(SkeletonFields other) {
         for(int i = 0; i < 16; ++i) {
            int charDiff = this.chars[i] - other.chars[i];
            if (charDiff != 0) {
               return charDiff;
            }

            int lengthDiff = this.lengths[i] - other.lengths[i];
            if (lengthDiff != 0) {
               return lengthDiff;
            }
         }

         return 0;
      }

      public boolean equals(Object other) {
         return this == other || other != null && other instanceof SkeletonFields && this.compareTo((SkeletonFields)other) == 0;
      }

      public int hashCode() {
         return Arrays.hashCode(this.chars) ^ Arrays.hashCode(this.lengths);
      }
   }

   private static class DateTimeMatcher implements Comparable {
      private int[] type;
      private SkeletonFields original;
      private SkeletonFields baseOriginal;
      private boolean addedDefaultDayPeriod;

      private DateTimeMatcher() {
         this.type = new int[16];
         this.original = new SkeletonFields();
         this.baseOriginal = new SkeletonFields();
         this.addedDefaultDayPeriod = false;
      }

      public boolean fieldIsNumeric(int field) {
         return this.type[field] > 0;
      }

      public String toString() {
         return this.original.toString(this.addedDefaultDayPeriod);
      }

      public String toCanonicalString() {
         return this.original.toCanonicalString(this.addedDefaultDayPeriod);
      }

      String getBasePattern() {
         return this.baseOriginal.toString(this.addedDefaultDayPeriod);
      }

      DateTimeMatcher set(String pattern, FormatParser fp, boolean allowDuplicateFields) {
         Arrays.fill(this.type, 0);
         this.original.clear();
         this.baseOriginal.clear();
         this.addedDefaultDayPeriod = false;
         fp.set(pattern);

         for(Object obj : fp.getItems()) {
            if (obj instanceof VariableField) {
               VariableField item = (VariableField)obj;
               String value = item.toString();
               int canonicalIndex = item.getCanonicalIndex();
               int[] row = DateTimePatternGenerator.types[canonicalIndex];
               int field = row[1];
               if (!this.original.isFieldEmpty(field)) {
                  char ch1 = this.original.getFieldChar(field);
                  char ch2 = value.charAt(0);
                  if (!allowDuplicateFields && (ch1 != 'r' || ch2 != 'U' && ch2 != 'y') && (ch1 != 'U' && ch1 != 'y' || ch2 != 'r')) {
                     throw new IllegalArgumentException("Conflicting fields:\t" + ch1 + ", " + value + "\t in " + pattern);
                  }
               } else {
                  this.original.populate(field, value);
                  char repeatChar = (char)row[0];
                  int repeatCount = row[3];
                  if ("GEzvQ".indexOf(repeatChar) >= 0) {
                     repeatCount = 1;
                  }

                  this.baseOriginal.populate(field, repeatChar, repeatCount);
                  int subField = row[2];
                  if (subField > 0) {
                     subField += value.length();
                  }

                  this.type[field] = subField;
               }
            }
         }

         if (!this.original.isFieldEmpty(12) && !this.original.isFieldEmpty(14) && this.original.isFieldEmpty(13)) {
            for(int i = 0; i < DateTimePatternGenerator.types.length; ++i) {
               int[] row = DateTimePatternGenerator.types[i];
               if (row[1] == 13) {
                  this.original.populate(13, (char)row[0], row[3]);
                  this.baseOriginal.populate(13, (char)row[0], row[3]);
                  int subField = row[2];
                  this.type[13] = subField > 0 ? subField + 1 : subField;
                  break;
               }
            }
         }

         if (!this.original.isFieldEmpty(11)) {
            if (this.original.getFieldChar(11) != 'h' && this.original.getFieldChar(11) != 'K') {
               if (!this.original.isFieldEmpty(10)) {
                  this.original.clearField(10);
                  this.baseOriginal.clearField(10);
                  this.type[10] = 0;
               }
            } else if (this.original.isFieldEmpty(10)) {
               for(int i = 0; i < DateTimePatternGenerator.types.length; ++i) {
                  int[] row = DateTimePatternGenerator.types[i];
                  if (row[1] == 10) {
                     this.original.populate(10, (char)row[0], row[3]);
                     this.baseOriginal.populate(10, (char)row[0], row[3]);
                     this.type[10] = row[2];
                     this.addedDefaultDayPeriod = true;
                     break;
                  }
               }
            }
         }

         return this;
      }

      int getFieldMask() {
         int result = 0;

         for(int i = 0; i < this.type.length; ++i) {
            if (this.type[i] != 0) {
               result |= 1 << i;
            }
         }

         return result;
      }

      void extractFrom(DateTimeMatcher source, int fieldMask) {
         for(int i = 0; i < this.type.length; ++i) {
            if ((fieldMask & 1 << i) != 0) {
               this.type[i] = source.type[i];
               this.original.copyFieldFrom(source.original, i);
            } else {
               this.type[i] = 0;
               this.original.clearField(i);
            }
         }

      }

      int getDistance(DateTimeMatcher other, int includeMask, DistanceInfo distanceInfo) {
         int result = 0;
         distanceInfo.clear();

         for(int i = 0; i < 16; ++i) {
            int myType = (includeMask & 1 << i) == 0 ? 0 : this.type[i];
            int otherType = other.type[i];
            if (myType != otherType) {
               if (myType == 0) {
                  result += 65536;
                  distanceInfo.addExtra(i);
               } else if (otherType == 0) {
                  result += 4096;
                  distanceInfo.addMissing(i);
               } else {
                  result += Math.abs(myType - otherType);
               }
            }
         }

         return result;
      }

      public int compareTo(DateTimeMatcher that) {
         int result = this.original.compareTo(that.original);
         return result > 0 ? -1 : (result < 0 ? 1 : 0);
      }

      public boolean equals(Object other) {
         return this == other || other != null && other instanceof DateTimeMatcher && this.original.equals(((DateTimeMatcher)other).original);
      }

      public int hashCode() {
         return this.original.hashCode();
      }
   }

   private static class DistanceInfo {
      int missingFieldMask;
      int extraFieldMask;

      private DistanceInfo() {
      }

      void clear() {
         this.missingFieldMask = this.extraFieldMask = 0;
      }

      void setTo(DistanceInfo other) {
         this.missingFieldMask = other.missingFieldMask;
         this.extraFieldMask = other.extraFieldMask;
      }

      void addMissing(int field) {
         this.missingFieldMask |= 1 << field;
      }

      void addExtra(int field) {
         this.extraFieldMask |= 1 << field;
      }

      public String toString() {
         return "missingFieldMask: " + DateTimePatternGenerator.showMask(this.missingFieldMask) + ", extraFieldMask: " + DateTimePatternGenerator.showMask(this.extraFieldMask);
      }
   }
}
