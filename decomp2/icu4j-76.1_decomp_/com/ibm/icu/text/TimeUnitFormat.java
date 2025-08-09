package com.ibm.icu.text;

import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.number.LocalizedNumberFormatter;
import com.ibm.icu.util.TimeUnit;
import com.ibm.icu.util.TimeUnitAmount;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.ObjectStreamException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeMap;

/** @deprecated */
@Deprecated
public class TimeUnitFormat extends MeasureFormat {
   /** @deprecated */
   @Deprecated
   public static final int FULL_NAME = 0;
   /** @deprecated */
   @Deprecated
   public static final int ABBREVIATED_NAME = 1;
   private static final int TOTAL_STYLES = 2;
   private static final long serialVersionUID = -3707773153184971529L;
   private NumberFormat format;
   private ULocale locale;
   private int style;
   private transient Map timeUnitToCountToPatterns;
   private transient PluralRules pluralRules;
   private transient boolean isReady;
   private static final String DEFAULT_PATTERN_FOR_SECOND = "{0} s";
   private static final String DEFAULT_PATTERN_FOR_MINUTE = "{0} min";
   private static final String DEFAULT_PATTERN_FOR_HOUR = "{0} h";
   private static final String DEFAULT_PATTERN_FOR_DAY = "{0} d";
   private static final String DEFAULT_PATTERN_FOR_WEEK = "{0} w";
   private static final String DEFAULT_PATTERN_FOR_MONTH = "{0} m";
   private static final String DEFAULT_PATTERN_FOR_YEAR = "{0} y";

   /** @deprecated */
   @Deprecated
   public TimeUnitFormat() {
      this((ULocale)ULocale.getDefault(), 0);
   }

   /** @deprecated */
   @Deprecated
   public TimeUnitFormat(ULocale locale) {
      this((ULocale)locale, 0);
   }

   /** @deprecated */
   @Deprecated
   public TimeUnitFormat(Locale locale) {
      this((Locale)locale, 0);
   }

   /** @deprecated */
   @Deprecated
   public TimeUnitFormat(ULocale locale, int style) {
      super(locale, style == 0 ? MeasureFormat.FormatWidth.WIDE : MeasureFormat.FormatWidth.SHORT);
      this.format = super.getNumberFormatInternal();
      if (style >= 0 && style < 2) {
         this.style = style;
         this.isReady = false;
      } else {
         throw new IllegalArgumentException("style should be either FULL_NAME or ABBREVIATED_NAME style");
      }
   }

   private TimeUnitFormat(ULocale locale, int style, NumberFormat numberFormat) {
      this(locale, style);
      if (numberFormat != null) {
         this.setNumberFormat((NumberFormat)numberFormat.clone());
      }

   }

   /** @deprecated */
   @Deprecated
   public TimeUnitFormat(Locale locale, int style) {
      this(ULocale.forLocale(locale), style);
   }

   /** @deprecated */
   @Deprecated
   public TimeUnitFormat setLocale(ULocale locale) {
      this.setLocale(locale, locale);
      this.clearCache();
      return this;
   }

   /** @deprecated */
   @Deprecated
   public TimeUnitFormat setLocale(Locale locale) {
      return this.setLocale(ULocale.forLocale(locale));
   }

   /** @deprecated */
   @Deprecated
   public TimeUnitFormat setNumberFormat(NumberFormat format) {
      if (format == this.format) {
         return this;
      } else {
         if (format == null) {
            if (this.locale == null) {
               this.isReady = false;
            } else {
               this.format = NumberFormat.getNumberInstance(this.locale);
            }
         } else {
            this.format = format;
         }

         this.clearCache();
         return this;
      }
   }

   /** @deprecated */
   @Deprecated
   public NumberFormat getNumberFormat() {
      return (NumberFormat)this.format.clone();
   }

   NumberFormat getNumberFormatInternal() {
      return this.format;
   }

   LocalizedNumberFormatter getNumberFormatter() {
      return ((DecimalFormat)this.format).toNumberFormatter();
   }

   /** @deprecated */
   @Deprecated
   public TimeUnitAmount parseObject(String source, ParsePosition pos) {
      if (!this.isReady) {
         this.setup();
      }

      Number resultNumber = null;
      TimeUnit resultTimeUnit = null;
      int oldPos = pos.getIndex();
      int newPos = -1;
      int longestParseDistance = 0;
      String countOfLongestMatch = null;

      for(TimeUnit timeUnit : this.timeUnitToCountToPatterns.keySet()) {
         Map<String, Object[]> countToPattern = (Map)this.timeUnitToCountToPatterns.get(timeUnit);

         for(Map.Entry patternEntry : countToPattern.entrySet()) {
            String count = (String)patternEntry.getKey();

            for(int styl = 0; styl < 2; ++styl) {
               MessageFormat pattern = (MessageFormat)((Object[])patternEntry.getValue())[styl];
               pos.setErrorIndex(-1);
               pos.setIndex(oldPos);
               Object parsed = pattern.parseObject(source, pos);
               if (pos.getErrorIndex() == -1 && pos.getIndex() != oldPos) {
                  Number temp = null;
                  if (((Object[])((Object[])parsed)).length != 0) {
                     Object tempObj = ((Object[])((Object[])parsed))[0];
                     if (tempObj instanceof Number) {
                        temp = (Number)tempObj;
                     } else {
                        try {
                           temp = this.format.parse(tempObj.toString());
                        } catch (ParseException var21) {
                           continue;
                        }
                     }
                  }

                  int parseDistance = pos.getIndex() - oldPos;
                  if (parseDistance > longestParseDistance) {
                     resultNumber = temp;
                     resultTimeUnit = timeUnit;
                     newPos = pos.getIndex();
                     longestParseDistance = parseDistance;
                     countOfLongestMatch = count;
                  }
               }
            }
         }
      }

      if (resultNumber == null && longestParseDistance != 0) {
         if (countOfLongestMatch.equals("zero")) {
            resultNumber = 0;
         } else if (countOfLongestMatch.equals("one")) {
            resultNumber = 1;
         } else if (countOfLongestMatch.equals("two")) {
            resultNumber = 2;
         } else {
            resultNumber = 3;
         }
      }

      if (longestParseDistance == 0) {
         pos.setIndex(oldPos);
         pos.setErrorIndex(0);
         return null;
      } else {
         pos.setIndex(newPos);
         pos.setErrorIndex(-1);
         return new TimeUnitAmount(resultNumber, resultTimeUnit);
      }
   }

   private void setup() {
      if (this.locale == null) {
         if (this.format != null) {
            this.locale = this.format.getLocale((ULocale.Type)null);
         } else {
            this.locale = ULocale.getDefault(ULocale.Category.FORMAT);
         }

         this.setLocale(this.locale, this.locale);
      }

      if (this.format == null) {
         this.format = NumberFormat.getNumberInstance(this.locale);
      }

      this.pluralRules = PluralRules.forLocale(this.locale);
      this.timeUnitToCountToPatterns = new HashMap();
      Set<String> pluralKeywords = this.pluralRules.getKeywords();
      this.setup("units/duration", this.timeUnitToCountToPatterns, 0, pluralKeywords);
      this.setup("unitsShort/duration", this.timeUnitToCountToPatterns, 1, pluralKeywords);
      this.isReady = true;
   }

   private void setup(String resourceKey, Map timeUnitToCountToPatterns, int style, Set pluralKeywords) {
      try {
         ICUResourceBundle resource = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata/unit", this.locale);
         TimeUnitFormatSetupSink sink = new TimeUnitFormatSetupSink(timeUnitToCountToPatterns, style, pluralKeywords, this.locale);
         resource.getAllItemsWithFallback(resourceKey, sink);
      } catch (MissingResourceException var12) {
      }

      TimeUnit[] timeUnits = TimeUnit.values();
      Set<String> keywords = this.pluralRules.getKeywords();

      for(int i = 0; i < timeUnits.length; ++i) {
         TimeUnit timeUnit = timeUnits[i];
         Map<String, Object[]> countToPatterns = (Map)timeUnitToCountToPatterns.get(timeUnit);
         if (countToPatterns == null) {
            countToPatterns = new TreeMap();
            timeUnitToCountToPatterns.put(timeUnit, countToPatterns);
         }

         for(String pluralCount : keywords) {
            if (countToPatterns.get(pluralCount) == null || ((Object[])countToPatterns.get(pluralCount))[style] == null) {
               this.searchInTree(resourceKey, style, timeUnit, pluralCount, pluralCount, countToPatterns);
            }
         }
      }

   }

   private void searchInTree(String resourceKey, int styl, TimeUnit timeUnit, String srcPluralCount, String searchPluralCount, Map countToPatterns) {
      ULocale parentLocale = this.locale;

      for(String srcTimeUnitName = timeUnit.toString(); parentLocale != null; parentLocale = parentLocale.getFallback()) {
         try {
            ICUResourceBundle unitsRes = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata/unit", parentLocale);
            unitsRes = unitsRes.getWithFallback(resourceKey);
            ICUResourceBundle oneUnitRes = unitsRes.getWithFallback(srcTimeUnitName);
            String pattern = oneUnitRes.getStringWithFallback(searchPluralCount);
            MessageFormat messageFormat = new MessageFormat(pattern, this.locale);
            Object[] pair = countToPatterns.get(srcPluralCount);
            if (pair == null) {
               pair = new Object[2];
               countToPatterns.put(srcPluralCount, pair);
            }

            pair[styl] = messageFormat;
            return;
         }
      }

      if (parentLocale == null && resourceKey.equals("unitsShort")) {
         this.searchInTree("units", styl, timeUnit, srcPluralCount, searchPluralCount, countToPatterns);
         if (countToPatterns.get(srcPluralCount) != null && ((Object[])countToPatterns.get(srcPluralCount))[styl] != null) {
            return;
         }
      }

      if (searchPluralCount.equals("other")) {
         MessageFormat messageFormat = null;
         if (timeUnit == TimeUnit.SECOND) {
            messageFormat = new MessageFormat("{0} s", this.locale);
         } else if (timeUnit == TimeUnit.MINUTE) {
            messageFormat = new MessageFormat("{0} min", this.locale);
         } else if (timeUnit == TimeUnit.HOUR) {
            messageFormat = new MessageFormat("{0} h", this.locale);
         } else if (timeUnit == TimeUnit.WEEK) {
            messageFormat = new MessageFormat("{0} w", this.locale);
         } else if (timeUnit == TimeUnit.DAY) {
            messageFormat = new MessageFormat("{0} d", this.locale);
         } else if (timeUnit == TimeUnit.MONTH) {
            messageFormat = new MessageFormat("{0} m", this.locale);
         } else if (timeUnit == TimeUnit.YEAR) {
            messageFormat = new MessageFormat("{0} y", this.locale);
         }

         Object[] pair = countToPatterns.get(srcPluralCount);
         if (pair == null) {
            pair = new Object[2];
            countToPatterns.put(srcPluralCount, pair);
         }

         pair[styl] = messageFormat;
      } else {
         this.searchInTree(resourceKey, styl, timeUnit, srcPluralCount, "other", countToPatterns);
      }

   }

   /** @deprecated */
   @Deprecated
   public Object clone() {
      TimeUnitFormat result = (TimeUnitFormat)super.clone();
      result.format = (NumberFormat)this.format.clone();
      return result;
   }

   private Object writeReplace() throws ObjectStreamException {
      return super.toTimeUnitProxy();
   }

   private Object readResolve() throws ObjectStreamException {
      return new TimeUnitFormat(this.locale, this.style, this.format);
   }

   private static final class TimeUnitFormatSetupSink extends UResource.Sink {
      Map timeUnitToCountToPatterns;
      int style;
      Set pluralKeywords;
      ULocale locale;
      boolean beenHere;

      TimeUnitFormatSetupSink(Map timeUnitToCountToPatterns, int style, Set pluralKeywords, ULocale locale) {
         this.timeUnitToCountToPatterns = timeUnitToCountToPatterns;
         this.style = style;
         this.pluralKeywords = pluralKeywords;
         this.locale = locale;
         this.beenHere = false;
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         if (!this.beenHere) {
            this.beenHere = true;
            UResource.Table units = value.getTable();

            for(int i = 0; units.getKeyAndValue(i, key, value); ++i) {
               String timeUnitName = key.toString();
               TimeUnit timeUnit = null;
               if (timeUnitName.equals("year")) {
                  timeUnit = TimeUnit.YEAR;
               } else if (timeUnitName.equals("month")) {
                  timeUnit = TimeUnit.MONTH;
               } else if (timeUnitName.equals("day")) {
                  timeUnit = TimeUnit.DAY;
               } else if (timeUnitName.equals("hour")) {
                  timeUnit = TimeUnit.HOUR;
               } else if (timeUnitName.equals("minute")) {
                  timeUnit = TimeUnit.MINUTE;
               } else if (timeUnitName.equals("second")) {
                  timeUnit = TimeUnit.SECOND;
               } else {
                  if (!timeUnitName.equals("week")) {
                     continue;
                  }

                  timeUnit = TimeUnit.WEEK;
               }

               Map<String, Object[]> countToPatterns = (Map)this.timeUnitToCountToPatterns.get(timeUnit);
               if (countToPatterns == null) {
                  countToPatterns = new TreeMap();
                  this.timeUnitToCountToPatterns.put(timeUnit, countToPatterns);
               }

               UResource.Table countsToPatternTable = value.getTable();

               for(int j = 0; countsToPatternTable.getKeyAndValue(j, key, value); ++j) {
                  String pluralCount = key.toString();
                  if (this.pluralKeywords.contains(pluralCount)) {
                     Object[] pair = countToPatterns.get(pluralCount);
                     if (pair == null) {
                        pair = new Object[2];
                        countToPatterns.put(pluralCount, pair);
                     }

                     if (pair[this.style] == null) {
                        String pattern = value.getString();
                        MessageFormat messageFormat = new MessageFormat(pattern, this.locale);
                        pair[this.style] = messageFormat;
                     }
                  }
               }
            }

         }
      }
   }
}
