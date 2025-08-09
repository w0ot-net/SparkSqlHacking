package com.ibm.icu.message2;

import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.number.FormattedNumber;
import com.ibm.icu.number.LocalizedNumberFormatter;
import com.ibm.icu.number.Notation;
import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.number.Precision;
import com.ibm.icu.number.Scale;
import com.ibm.icu.number.UnlocalizedNumberFormatter;
import com.ibm.icu.text.FormattedValue;
import com.ibm.icu.text.NumberingSystem;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.CurrencyAmount;
import com.ibm.icu.util.Measure;
import com.ibm.icu.util.MeasureUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

class NumberFormatterFactory implements FormatterFactory, SelectorFactory {
   private final String kind;

   public NumberFormatterFactory(String kind) {
      switch (kind) {
         default:
            kind = "number";
         case "number":
         case "integer":
            this.kind = kind;
      }
   }

   public Formatter createFormatter(Locale locale, Map fixedOptions) {
      return new NumberFormatterImpl(locale, fixedOptions, this.kind);
   }

   public Selector createSelector(Locale locale, Map fixedOptions) {
      PluralRules.PluralType pluralType;
      switch (OptUtils.getString(fixedOptions, "select", "")) {
         case "ordinal":
            pluralType = PluralRules.PluralType.ORDINAL;
            break;
         case "cardinal":
         default:
            pluralType = PluralRules.PluralType.CARDINAL;
      }

      PluralRules rules = PluralRules.forLocale(locale, pluralType);
      return new PluralSelectorImpl(locale, rules, fixedOptions, this.kind);
   }

   private static LocalizedNumberFormatter formatterForOptions(Locale locale, Map fixedOptions, String kind) {
      String skeleton = OptUtils.getString(fixedOptions, "icu:skeleton");
      if (skeleton != null) {
         return NumberFormatter.forSkeleton(skeleton).locale(locale);
      } else {
         UnlocalizedNumberFormatter nf = NumberFormatter.with();
         if ("number".equals(kind)) {
            Notation notation;
            label119:
            switch (OptUtils.getString(fixedOptions, "notation", "standard")) {
               case "scientific":
                  notation = Notation.scientific();
                  break;
               case "engineering":
                  notation = Notation.engineering();
                  break;
               case "compact":
                  switch (OptUtils.getString(fixedOptions, "compactDisplay", "short")) {
                     case "long":
                        notation = Notation.compactLong();
                        break label119;
                     case "short":
                     default:
                        notation = Notation.compactShort();
                        break label119;
                  }
               case "standard":
               default:
                  notation = Notation.simple();
            }

            nf = (UnlocalizedNumberFormatter)nf.notation(notation);
            String strOption = OptUtils.getString(fixedOptions, "style", "decimal");
            if (strOption.equals("percent")) {
               nf = (UnlocalizedNumberFormatter)((UnlocalizedNumberFormatter)nf.unit(MeasureUnit.PERCENT)).scale(Scale.powerOfTen(2));
            }

            Integer option = OptUtils.getInteger(fixedOptions, "minimumFractionDigits");
            if (option != null) {
               nf = (UnlocalizedNumberFormatter)nf.precision(Precision.minFraction(option));
            }

            option = OptUtils.getInteger(fixedOptions, "maximumFractionDigits");
            if (option != null) {
               nf = (UnlocalizedNumberFormatter)nf.precision(Precision.maxFraction(option));
            }

            option = OptUtils.getInteger(fixedOptions, "minimumSignificantDigits");
            if (option != null) {
               nf = (UnlocalizedNumberFormatter)nf.precision(Precision.minSignificantDigits(option));
            }
         }

         String strOption = OptUtils.getString(fixedOptions, "numberingSystem", "");
         if (!strOption.isEmpty()) {
            strOption = strOption.toLowerCase(Locale.US);
            NumberingSystem ns = NumberingSystem.getInstanceByName(strOption);
            nf = (UnlocalizedNumberFormatter)nf.symbols(ns);
         }

         Integer option = OptUtils.getInteger(fixedOptions, "minimumIntegerDigits");
         if (option != null) {
         }

         option = OptUtils.getInteger(fixedOptions, "maximumSignificantDigits");
         if (option != null) {
            nf = (UnlocalizedNumberFormatter)nf.precision(Precision.maxSignificantDigits(option));
         }

         NumberFormatter.SignDisplay signDisplay;
         switch (OptUtils.getString(fixedOptions, "signDisplay", "auto")) {
            case "always":
               signDisplay = NumberFormatter.SignDisplay.ALWAYS;
               break;
            case "exceptZero":
               signDisplay = NumberFormatter.SignDisplay.EXCEPT_ZERO;
               break;
            case "negative":
               signDisplay = NumberFormatter.SignDisplay.NEGATIVE;
               break;
            case "never":
               signDisplay = NumberFormatter.SignDisplay.NEVER;
               break;
            case "auto":
            default:
               signDisplay = NumberFormatter.SignDisplay.AUTO;
         }

         nf = (UnlocalizedNumberFormatter)nf.sign(signDisplay);
         NumberFormatter.GroupingStrategy grp;
         switch (OptUtils.getString(fixedOptions, "useGrouping", "auto")) {
            case "always":
               grp = NumberFormatter.GroupingStrategy.ON_ALIGNED;
               break;
            case "never":
               grp = NumberFormatter.GroupingStrategy.OFF;
               break;
            case "min2":
               grp = NumberFormatter.GroupingStrategy.MIN2;
               break;
            case "auto":
            default:
               grp = NumberFormatter.GroupingStrategy.AUTO;
         }

         nf = (UnlocalizedNumberFormatter)nf.grouping(grp);
         if (kind.equals("integer")) {
            nf = (UnlocalizedNumberFormatter)nf.precision(Precision.integer());
         }

         return nf.locale(locale);
      }
   }

   static class NumberFormatterImpl implements Formatter {
      private final Locale locale;
      private final Map fixedOptions;
      private final LocalizedNumberFormatter icuFormatter;
      private final String kind;
      final boolean advanced;

      NumberFormatterImpl(Locale locale, Map fixedOptions, String kind) {
         this.locale = locale;
         this.fixedOptions = new HashMap(fixedOptions);
         String skeleton = OptUtils.getString(fixedOptions, "icu:skeleton");
         boolean fancy = skeleton != null;
         this.icuFormatter = NumberFormatterFactory.formatterForOptions(locale, fixedOptions, kind);
         this.advanced = fancy;
         this.kind = kind;
      }

      LocalizedNumberFormatter getIcuFormatter() {
         return this.icuFormatter;
      }

      public String formatToString(Object toFormat, Map variableOptions) {
         return this.format(toFormat, variableOptions).toString();
      }

      public FormattedPlaceholder format(Object toFormat, Map variableOptions) {
         LocalizedNumberFormatter realFormatter;
         if (variableOptions.isEmpty()) {
            realFormatter = this.icuFormatter;
         } else {
            Map<String, Object> mergedOptions = new HashMap(this.fixedOptions);
            mergedOptions.putAll(variableOptions);
            realFormatter = NumberFormatterFactory.formatterForOptions(this.locale, mergedOptions, this.kind);
         }

         Integer offset = OptUtils.getInteger(variableOptions, "icu:offset");
         if (offset == null && this.fixedOptions != null) {
            offset = OptUtils.getInteger(this.fixedOptions, "icu:offset");
         }

         if (offset == null) {
            offset = 0;
         }

         FormattedValue result = null;
         if (toFormat == null) {
            throw new NullPointerException("Argument to format can't be null");
         } else {
            if (toFormat instanceof Double) {
               result = realFormatter.format((Double)toFormat - (double)offset);
            } else if (toFormat instanceof Long) {
               result = realFormatter.format((Long)toFormat - (long)offset);
            } else if (toFormat instanceof Integer) {
               result = realFormatter.format((long)((Integer)toFormat - offset));
            } else if (toFormat instanceof BigDecimal) {
               BigDecimal bd = (BigDecimal)toFormat;
               result = realFormatter.format((Number)bd.subtract(BigDecimal.valueOf((long)offset)));
            } else if (toFormat instanceof Number) {
               result = realFormatter.format(((Number)toFormat).doubleValue() - (double)offset);
            } else if (toFormat instanceof CurrencyAmount) {
               result = realFormatter.format((Measure)((CurrencyAmount)toFormat));
            } else {
               String strValue = Objects.toString(toFormat);
               Number nrValue = OptUtils.asNumber(strValue);
               if (nrValue != null) {
                  result = realFormatter.format(nrValue.doubleValue() - (double)offset);
               } else {
                  result = new PlainStringFormattedValue("{|" + strValue + "|}");
               }
            }

            return new FormattedPlaceholder(toFormat, result);
         }
      }
   }

   private static class PluralSelectorImpl implements Selector {
      private static final String NO_MATCH = "�NO_MATCH\ufffe";
      private final PluralRules rules;
      private Map fixedOptions;
      private LocalizedNumberFormatter icuFormatter;

      private PluralSelectorImpl(Locale locale, PluralRules rules, Map fixedOptions, String kind) {
         this.rules = rules;
         this.fixedOptions = fixedOptions;
         this.icuFormatter = NumberFormatterFactory.formatterForOptions(locale, fixedOptions, kind);
      }

      public List matches(Object value, List keys, Map variableOptions) {
         List<String> result = new ArrayList();
         if (value == null) {
            return result;
         } else {
            for(String key : keys) {
               if (this.matches(value, key, variableOptions)) {
                  result.add(key);
               } else {
                  result.add("�NO_MATCH\ufffe");
               }
            }

            result.sort(PluralSelectorImpl::pluralComparator);
            return result;
         }
      }

      private static int pluralComparator(String o1, String o2) {
         if (o1.equals(o2)) {
            return 0;
         } else if ("�NO_MATCH\ufffe".equals(o1)) {
            return 1;
         } else if ("�NO_MATCH\ufffe".equals(o2)) {
            return -1;
         } else if ("*".equals(o1)) {
            return 1;
         } else if ("*".equals(o2)) {
            return -1;
         } else if (OptUtils.asNumber(o1) != null) {
            return -1;
         } else {
            return OptUtils.asNumber(o2) != null ? 1 : o1.compareTo(o2);
         }
      }

      private boolean matches(Object value, String key, Map variableOptions) {
         if ("*".equals(key)) {
            return true;
         } else {
            Integer offset = OptUtils.getInteger(variableOptions, "icu:offset");
            if (offset == null && this.fixedOptions != null) {
               offset = OptUtils.getInteger(this.fixedOptions, "icu:offset");
            }

            if (offset == null) {
               offset = 0;
            }

            Number valToCheck = Double.MIN_VALUE;
            if (value instanceof FormattedPlaceholder) {
               FormattedPlaceholder fph = (FormattedPlaceholder)value;
               value = fph.getInput();
            }

            if (value instanceof Number) {
               Number var9 = ((Number)value).doubleValue();
               Number keyNrVal = OptUtils.asNumber(key);
               if (keyNrVal != null && ((Number)var9).doubleValue() == keyNrVal.doubleValue()) {
                  return true;
               } else {
                  FormattedNumber formatted = this.icuFormatter.format(((Number)var9).doubleValue() - (double)offset);
                  String match = this.rules.select(formatted);
                  if (match.equals("other")) {
                     match = "*";
                  }

                  return match.equals(key);
               }
            } else {
               return false;
            }
         }
      }
   }
}
