package com.ibm.icu.text;

import com.ibm.icu.impl.ICUCache;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.ICUException;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Set;

public class DateIntervalInfo implements Cloneable, Freezable, Serializable {
   static final int currentSerialVersion = 1;
   static final String[] CALENDAR_FIELD_TO_PATTERN_LETTER = new String[]{"G", "y", "M", "w", "W", "d", "D", "E", "F", "a", "h", "H", "m", "s", "S", "z", " ", "Y", "e", "u", "g", "A", " ", " "};
   private static final long serialVersionUID = 1L;
   private static final int MINIMUM_SUPPORTED_CALENDAR_FIELD = 14;
   private static String CALENDAR_KEY = "calendar";
   private static String INTERVAL_FORMATS_KEY = "intervalFormats";
   private static String FALLBACK_STRING = "fallback";
   private static String LATEST_FIRST_PREFIX = "latestFirst:";
   private static String EARLIEST_FIRST_PREFIX = "earliestFirst:";
   private static final ICUCache DIICACHE = new SimpleCache();
   private String fFallbackIntervalPattern;
   private boolean fFirstDateInPtnIsLaterDate;
   private Map fIntervalPatterns;
   private transient volatile boolean frozen;
   private transient boolean fIntervalPatternsReadOnly;

   /** @deprecated */
   @Deprecated
   public DateIntervalInfo() {
      this.fFirstDateInPtnIsLaterDate = false;
      this.fIntervalPatterns = null;
      this.frozen = false;
      this.fIntervalPatternsReadOnly = false;
      this.fIntervalPatterns = new HashMap();
      this.fFallbackIntervalPattern = "{0} – {1}";
   }

   public DateIntervalInfo(ULocale locale) {
      this.fFirstDateInPtnIsLaterDate = false;
      this.fIntervalPatterns = null;
      this.frozen = false;
      this.fIntervalPatternsReadOnly = false;
      this.initializeData(locale);
   }

   public DateIntervalInfo(Locale locale) {
      this(ULocale.forLocale(locale));
   }

   private void initializeData(ULocale locale) {
      String key = locale.toString();
      DateIntervalInfo dii = (DateIntervalInfo)DIICACHE.get(key);
      if (dii == null) {
         this.setup(locale);
         this.fIntervalPatternsReadOnly = true;
         DIICACHE.put(key, ((DateIntervalInfo)this.clone()).freeze());
      } else {
         this.initializeFromReadOnlyPatterns(dii);
      }

   }

   private void initializeFromReadOnlyPatterns(DateIntervalInfo dii) {
      this.fFallbackIntervalPattern = dii.fFallbackIntervalPattern;
      this.fFirstDateInPtnIsLaterDate = dii.fFirstDateInPtnIsLaterDate;
      this.fIntervalPatterns = dii.fIntervalPatterns;
      this.fIntervalPatternsReadOnly = true;
   }

   private void setup(ULocale locale) {
      int DEFAULT_HASH_SIZE = 19;
      this.fIntervalPatterns = new HashMap(DEFAULT_HASH_SIZE);
      this.fFallbackIntervalPattern = "{0} – {1}";

      try {
         String calendarTypeToUse = locale.getKeywordValue("calendar");
         if (calendarTypeToUse == null) {
            String[] preferredCalendarTypes = Calendar.getKeywordValuesForLocale("calendar", locale, true);
            calendarTypeToUse = preferredCalendarTypes[0];
         }

         if (calendarTypeToUse == null) {
            calendarTypeToUse = "gregorian";
         }

         DateIntervalSink sink = new DateIntervalSink(this);
         ICUResourceBundle resource = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", locale);
         String fallbackPattern = resource.getStringWithFallback(CALENDAR_KEY + "/" + calendarTypeToUse + "/" + INTERVAL_FORMATS_KEY + "/" + FALLBACK_STRING);
         this.setFallbackIntervalPattern(fallbackPattern);

         for(Set<String> loadedCalendarTypes = new HashSet(); calendarTypeToUse != null; calendarTypeToUse = sink.getAndResetNextCalendarType()) {
            if (loadedCalendarTypes.contains(calendarTypeToUse)) {
               throw new ICUException("Loop in calendar type fallback: " + calendarTypeToUse);
            }

            loadedCalendarTypes.add(calendarTypeToUse);
            String pathToIntervalFormats = CALENDAR_KEY + "/" + calendarTypeToUse;
            resource.getAllItemsWithFallback(pathToIntervalFormats, sink);
         }
      } catch (MissingResourceException var9) {
      }

   }

   private static int splitPatternInto2Part(String intervalPattern) {
      boolean inQuote = false;
      char prevCh = 0;
      int count = 0;
      int[] patternRepeated = new int[58];
      int PATTERN_CHAR_BASE = 65;
      boolean foundRepetition = false;

      int i;
      for(i = 0; i < intervalPattern.length(); ++i) {
         char ch = intervalPattern.charAt(i);
         if (ch != prevCh && count > 0) {
            int repeated = patternRepeated[prevCh - PATTERN_CHAR_BASE];
            if (repeated != 0) {
               foundRepetition = true;
               break;
            }

            patternRepeated[prevCh - PATTERN_CHAR_BASE] = 1;
            count = 0;
         }

         if (ch == '\'') {
            if (i + 1 < intervalPattern.length() && intervalPattern.charAt(i + 1) == '\'') {
               ++i;
            } else {
               inQuote = !inQuote;
            }
         } else if (!inQuote && (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
            prevCh = ch;
            ++count;
         }
      }

      if (count > 0 && !foundRepetition && patternRepeated[prevCh - PATTERN_CHAR_BASE] == 0) {
         count = 0;
      }

      return i - count;
   }

   public void setIntervalPattern(String skeleton, int lrgDiffCalUnit, String intervalPattern) {
      if (this.frozen) {
         throw new UnsupportedOperationException("no modification is allowed after DII is frozen");
      } else if (lrgDiffCalUnit > 14) {
         throw new IllegalArgumentException("calendar field is larger than MINIMUM_SUPPORTED_CALENDAR_FIELD");
      } else {
         if (this.fIntervalPatternsReadOnly) {
            this.fIntervalPatterns = cloneIntervalPatterns(this.fIntervalPatterns);
            this.fIntervalPatternsReadOnly = false;
         }

         PatternInfo ptnInfo = this.setIntervalPatternInternally(skeleton, CALENDAR_FIELD_TO_PATTERN_LETTER[lrgDiffCalUnit], intervalPattern);
         if (lrgDiffCalUnit == 11) {
            this.setIntervalPattern(skeleton, CALENDAR_FIELD_TO_PATTERN_LETTER[9], ptnInfo);
            this.setIntervalPattern(skeleton, CALENDAR_FIELD_TO_PATTERN_LETTER[10], ptnInfo);
         } else if (lrgDiffCalUnit == 5 || lrgDiffCalUnit == 7) {
            this.setIntervalPattern(skeleton, CALENDAR_FIELD_TO_PATTERN_LETTER[5], ptnInfo);
         }

      }
   }

   private PatternInfo setIntervalPatternInternally(String skeleton, String lrgDiffCalUnit, String intervalPattern) {
      Map<String, PatternInfo> patternsOfOneSkeleton = (Map)this.fIntervalPatterns.get(skeleton);
      boolean emptyHash = false;
      if (patternsOfOneSkeleton == null) {
         patternsOfOneSkeleton = new HashMap();
         emptyHash = true;
      }

      boolean order = this.fFirstDateInPtnIsLaterDate;
      if (intervalPattern.startsWith(LATEST_FIRST_PREFIX)) {
         order = true;
         int prefixLength = LATEST_FIRST_PREFIX.length();
         intervalPattern = intervalPattern.substring(prefixLength, intervalPattern.length());
      } else if (intervalPattern.startsWith(EARLIEST_FIRST_PREFIX)) {
         order = false;
         int earliestFirstLength = EARLIEST_FIRST_PREFIX.length();
         intervalPattern = intervalPattern.substring(earliestFirstLength, intervalPattern.length());
      }

      PatternInfo itvPtnInfo = genPatternInfo(intervalPattern, order);
      patternsOfOneSkeleton.put(lrgDiffCalUnit, itvPtnInfo);
      if (emptyHash) {
         this.fIntervalPatterns.put(skeleton, patternsOfOneSkeleton);
      }

      return itvPtnInfo;
   }

   private void setIntervalPattern(String skeleton, String lrgDiffCalUnit, PatternInfo ptnInfo) {
      Map<String, PatternInfo> patternsOfOneSkeleton = (Map)this.fIntervalPatterns.get(skeleton);
      patternsOfOneSkeleton.put(lrgDiffCalUnit, ptnInfo);
   }

   /** @deprecated */
   @Deprecated
   public static PatternInfo genPatternInfo(String intervalPattern, boolean laterDateFirst) {
      int splitPoint = splitPatternInto2Part(intervalPattern);
      String firstPart = intervalPattern.substring(0, splitPoint);
      String secondPart = null;
      if (splitPoint < intervalPattern.length()) {
         secondPart = intervalPattern.substring(splitPoint, intervalPattern.length());
      }

      return new PatternInfo(firstPart, secondPart, laterDateFirst);
   }

   public PatternInfo getIntervalPattern(String skeleton, int field) {
      if (field > 14) {
         throw new IllegalArgumentException("no support for field less than MILLISECOND");
      } else {
         Map<String, PatternInfo> patternsOfOneSkeleton = (Map)this.fIntervalPatterns.get(skeleton);
         if (patternsOfOneSkeleton != null) {
            PatternInfo intervalPattern = (PatternInfo)patternsOfOneSkeleton.get(CALENDAR_FIELD_TO_PATTERN_LETTER[field]);
            if (intervalPattern != null) {
               return intervalPattern;
            }
         }

         return null;
      }
   }

   public String getFallbackIntervalPattern() {
      return this.fFallbackIntervalPattern;
   }

   public void setFallbackIntervalPattern(String fallbackPattern) {
      if (this.frozen) {
         throw new UnsupportedOperationException("no modification is allowed after DII is frozen");
      } else {
         int firstPatternIndex = fallbackPattern.indexOf("{0}");
         int secondPatternIndex = fallbackPattern.indexOf("{1}");
         if (firstPatternIndex != -1 && secondPatternIndex != -1) {
            if (firstPatternIndex > secondPatternIndex) {
               this.fFirstDateInPtnIsLaterDate = true;
            }

            this.fFallbackIntervalPattern = fallbackPattern;
         } else {
            throw new IllegalArgumentException("no pattern {0} or pattern {1} in fallbackPattern");
         }
      }
   }

   public boolean getDefaultOrder() {
      return this.fFirstDateInPtnIsLaterDate;
   }

   public Object clone() {
      return this.frozen ? this : this.cloneUnfrozenDII();
   }

   private Object cloneUnfrozenDII() {
      try {
         DateIntervalInfo other = (DateIntervalInfo)super.clone();
         other.fFallbackIntervalPattern = this.fFallbackIntervalPattern;
         other.fFirstDateInPtnIsLaterDate = this.fFirstDateInPtnIsLaterDate;
         if (this.fIntervalPatternsReadOnly) {
            other.fIntervalPatterns = this.fIntervalPatterns;
            other.fIntervalPatternsReadOnly = true;
         } else {
            other.fIntervalPatterns = cloneIntervalPatterns(this.fIntervalPatterns);
            other.fIntervalPatternsReadOnly = false;
         }

         other.frozen = false;
         return other;
      } catch (CloneNotSupportedException e) {
         throw new ICUCloneNotSupportedException("clone is not supported", e);
      }
   }

   private static Map cloneIntervalPatterns(Map patterns) {
      Map<String, Map<String, PatternInfo>> result = new HashMap();

      for(Map.Entry skeletonEntry : patterns.entrySet()) {
         String skeleton = (String)skeletonEntry.getKey();
         Map<String, PatternInfo> patternsOfOneSkeleton = (Map)skeletonEntry.getValue();
         Map<String, PatternInfo> oneSetPtn = new HashMap();

         for(Map.Entry calEntry : patternsOfOneSkeleton.entrySet()) {
            String calField = (String)calEntry.getKey();
            PatternInfo value = (PatternInfo)calEntry.getValue();
            oneSetPtn.put(calField, value);
         }

         result.put(skeleton, oneSetPtn);
      }

      return result;
   }

   public boolean isFrozen() {
      return this.frozen;
   }

   public DateIntervalInfo freeze() {
      this.fIntervalPatternsReadOnly = true;
      this.frozen = true;
      return this;
   }

   public DateIntervalInfo cloneAsThawed() {
      DateIntervalInfo result = (DateIntervalInfo)this.cloneUnfrozenDII();
      return result;
   }

   static void parseSkeleton(String skeleton, int[] skeletonFieldWidth) {
      int PATTERN_CHAR_BASE = 65;

      for(int i = 0; i < skeleton.length(); ++i) {
         ++skeletonFieldWidth[skeleton.charAt(i) - PATTERN_CHAR_BASE];
      }

   }

   private static boolean stringNumeric(int fieldWidth, int anotherFieldWidth, char patternLetter) {
      return patternLetter == 'M' && (fieldWidth <= 2 && anotherFieldWidth > 2 || fieldWidth > 2 && anotherFieldWidth <= 2);
   }

   DateIntervalFormat.BestMatchInfo getBestSkeleton(String inputSkeleton) {
      String bestSkeleton = inputSkeleton;
      int[] inputSkeletonFieldWidth = new int[58];
      int[] skeletonFieldWidth = new int[58];
      int DIFFERENT_FIELD = 4096;
      int STRING_NUMERIC_DIFFERENCE = 256;
      int BASE = 65;
      boolean replacedAlternateChars = false;
      if (inputSkeleton.indexOf(122) != -1 || inputSkeleton.indexOf(107) != -1 || inputSkeleton.indexOf(75) != -1 || inputSkeleton.indexOf(97) != -1 || inputSkeleton.indexOf(98) != -1) {
         inputSkeleton = inputSkeleton.replace('z', 'v');
         inputSkeleton = inputSkeleton.replace('k', 'H');
         inputSkeleton = inputSkeleton.replace('K', 'h');
         inputSkeleton = inputSkeleton.replace("a", "");
         inputSkeleton = inputSkeleton.replace("b", "");
         replacedAlternateChars = true;
      }

      parseSkeleton(inputSkeleton, inputSkeletonFieldWidth);
      int bestDistance = Integer.MAX_VALUE;
      int bestFieldDifference = 0;

      for(String skeleton : this.fIntervalPatterns.keySet()) {
         for(int i = 0; i < skeletonFieldWidth.length; ++i) {
            skeletonFieldWidth[i] = 0;
         }

         parseSkeleton(skeleton, skeletonFieldWidth);
         int distance = 0;
         int fieldDifference = 1;

         for(int i = 0; i < inputSkeletonFieldWidth.length; ++i) {
            int inputFieldWidth = inputSkeletonFieldWidth[i];
            int fieldWidth = skeletonFieldWidth[i];
            if (inputFieldWidth != fieldWidth) {
               if (inputFieldWidth == 0) {
                  fieldDifference = -1;
                  distance += 4096;
               } else if (fieldWidth == 0) {
                  fieldDifference = -1;
                  distance += 4096;
               } else if (stringNumeric(inputFieldWidth, fieldWidth, (char)(i + 65))) {
                  distance += 256;
               } else {
                  distance += Math.abs(inputFieldWidth - fieldWidth);
               }
            }
         }

         if (distance < bestDistance) {
            bestSkeleton = skeleton;
            bestDistance = distance;
            bestFieldDifference = fieldDifference;
         }

         if (distance == 0) {
            bestFieldDifference = 0;
            break;
         }
      }

      if (replacedAlternateChars && bestFieldDifference != -1) {
         bestFieldDifference = 2;
      }

      return new DateIntervalFormat.BestMatchInfo(bestSkeleton, bestFieldDifference);
   }

   public boolean equals(Object a) {
      if (a instanceof DateIntervalInfo) {
         DateIntervalInfo dtInfo = (DateIntervalInfo)a;
         return this.fIntervalPatterns.equals(dtInfo.fIntervalPatterns);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.fIntervalPatterns.hashCode();
   }

   /** @deprecated */
   @Deprecated
   public Map getPatterns() {
      LinkedHashMap<String, Set<String>> result = new LinkedHashMap();

      for(Map.Entry entry : this.fIntervalPatterns.entrySet()) {
         result.put(entry.getKey(), new LinkedHashSet(((Map)entry.getValue()).keySet()));
      }

      return result;
   }

   /** @deprecated */
   @Deprecated
   public Map getRawPatterns() {
      LinkedHashMap<String, Map<String, PatternInfo>> result = new LinkedHashMap();

      for(Map.Entry entry : this.fIntervalPatterns.entrySet()) {
         result.put(entry.getKey(), new LinkedHashMap((Map)entry.getValue()));
      }

      return result;
   }

   public static final class PatternInfo implements Cloneable, Serializable {
      static final int currentSerialVersion = 1;
      private static final long serialVersionUID = 1L;
      private final String fIntervalPatternFirstPart;
      private final String fIntervalPatternSecondPart;
      private final boolean fFirstDateInPtnIsLaterDate;

      public PatternInfo(String firstPart, String secondPart, boolean firstDateInPtnIsLaterDate) {
         this.fIntervalPatternFirstPart = firstPart;
         this.fIntervalPatternSecondPart = secondPart;
         this.fFirstDateInPtnIsLaterDate = firstDateInPtnIsLaterDate;
      }

      public String getFirstPart() {
         return this.fIntervalPatternFirstPart;
      }

      public String getSecondPart() {
         return this.fIntervalPatternSecondPart;
      }

      public boolean firstDateInPtnIsLaterDate() {
         return this.fFirstDateInPtnIsLaterDate;
      }

      public boolean equals(Object a) {
         if (!(a instanceof PatternInfo)) {
            return false;
         } else {
            PatternInfo patternInfo = (PatternInfo)a;
            return Objects.equals(this.fIntervalPatternFirstPart, patternInfo.fIntervalPatternFirstPart) && Objects.equals(this.fIntervalPatternSecondPart, patternInfo.fIntervalPatternSecondPart) && this.fFirstDateInPtnIsLaterDate == patternInfo.fFirstDateInPtnIsLaterDate;
         }
      }

      public int hashCode() {
         int hash = this.fIntervalPatternFirstPart != null ? this.fIntervalPatternFirstPart.hashCode() : 0;
         if (this.fIntervalPatternSecondPart != null) {
            hash ^= this.fIntervalPatternSecondPart.hashCode();
         }

         if (this.fFirstDateInPtnIsLaterDate) {
            hash = ~hash;
         }

         return hash;
      }

      public String toString() {
         return "{first=«" + this.fIntervalPatternFirstPart + "», second=«" + this.fIntervalPatternSecondPart + "», reversed:" + this.fFirstDateInPtnIsLaterDate + "}";
      }
   }

   private static final class DateIntervalSink extends UResource.Sink {
      private static final String ACCEPTED_PATTERN_LETTERS = "GyMdahHmsS";
      DateIntervalInfo dateIntervalInfo;
      String nextCalendarType;
      private static final String DATE_INTERVAL_PATH_PREFIX;
      private static final String DATE_INTERVAL_PATH_SUFFIX;

      public DateIntervalSink(DateIntervalInfo dateIntervalInfo) {
         this.dateIntervalInfo = dateIntervalInfo;
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         UResource.Table dateIntervalData = value.getTable();

         for(int i = 0; dateIntervalData.getKeyAndValue(i, key, value); ++i) {
            if (key.contentEquals(DateIntervalInfo.INTERVAL_FORMATS_KEY)) {
               if (value.getType() == 3) {
                  this.nextCalendarType = this.getCalendarTypeFromPath(value.getAliasString());
                  break;
               }

               if (value.getType() == 2) {
                  UResource.Table skeletonData = value.getTable();

                  for(int j = 0; skeletonData.getKeyAndValue(j, key, value); ++j) {
                     if (value.getType() == 2) {
                        this.processSkeletonTable(key, value);
                     }
                  }
                  break;
               }
            }
         }

      }

      public void processSkeletonTable(UResource.Key key, UResource.Value value) {
         String currentSkeleton = key.toString();
         UResource.Table patternData = value.getTable();

         for(int k = 0; patternData.getKeyAndValue(k, key, value); ++k) {
            if (value.getType() == 0) {
               CharSequence patternLetter = this.validateAndProcessPatternLetter(key);
               if (patternLetter != null) {
                  String lrgDiffCalUnit = patternLetter.toString();
                  this.setIntervalPatternIfAbsent(currentSkeleton, lrgDiffCalUnit, value);
               }
            }
         }

      }

      public String getAndResetNextCalendarType() {
         String tmpCalendarType = this.nextCalendarType;
         this.nextCalendarType = null;
         return tmpCalendarType;
      }

      private String getCalendarTypeFromPath(String path) {
         if (path.startsWith(DATE_INTERVAL_PATH_PREFIX) && path.endsWith(DATE_INTERVAL_PATH_SUFFIX)) {
            return path.substring(DATE_INTERVAL_PATH_PREFIX.length(), path.length() - DATE_INTERVAL_PATH_SUFFIX.length());
         } else {
            throw new ICUException("Malformed 'intervalFormat' alias path: " + path);
         }
      }

      private CharSequence validateAndProcessPatternLetter(CharSequence patternLetter) {
         if (patternLetter.length() != 1) {
            return null;
         } else {
            char letter = patternLetter.charAt(0);
            if ("GyMdahHmsS".indexOf(letter) < 0 && letter != 'B') {
               return null;
            } else {
               if (letter == DateIntervalInfo.CALENDAR_FIELD_TO_PATTERN_LETTER[11].charAt(0)) {
                  patternLetter = DateIntervalInfo.CALENDAR_FIELD_TO_PATTERN_LETTER[10];
               }

               if (letter == 'B') {
                  patternLetter = DateIntervalInfo.CALENDAR_FIELD_TO_PATTERN_LETTER[9];
               }

               return patternLetter;
            }
         }
      }

      private void setIntervalPatternIfAbsent(String currentSkeleton, String lrgDiffCalUnit, UResource.Value intervalPattern) {
         Map<String, PatternInfo> patternsOfOneSkeleton = (Map)this.dateIntervalInfo.fIntervalPatterns.get(currentSkeleton);
         if (patternsOfOneSkeleton == null || !patternsOfOneSkeleton.containsKey(lrgDiffCalUnit)) {
            this.dateIntervalInfo.setIntervalPatternInternally(currentSkeleton, lrgDiffCalUnit, intervalPattern.toString());
         }

      }

      static {
         DATE_INTERVAL_PATH_PREFIX = "/LOCALE/" + DateIntervalInfo.CALENDAR_KEY + "/";
         DATE_INTERVAL_PATH_SUFFIX = "/" + DateIntervalInfo.INTERVAL_FORMATS_KEY;
      }
   }
}
