package com.ibm.icu.number;

import com.ibm.icu.impl.FormattedStringBuilder;
import com.ibm.icu.impl.FormattedValueStringBuilderImpl;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.impl.number.MacroProps;
import com.ibm.icu.impl.number.MicroProps;
import com.ibm.icu.impl.number.Modifier;
import com.ibm.icu.impl.number.SimpleModifier;
import com.ibm.icu.impl.number.range.PrefixInfixSuffixLengthHelper;
import com.ibm.icu.impl.number.range.RangeMacroProps;
import com.ibm.icu.impl.number.range.StandardPluralRanges;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.text.Format;
import java.util.MissingResourceException;

class NumberRangeFormatterImpl {
   final NumberFormatterImpl formatterImpl1;
   final NumberFormatterImpl formatterImpl2;
   final boolean fSameFormatters;
   final NumberRangeFormatter.RangeCollapse fCollapse;
   final NumberRangeFormatter.RangeIdentityFallback fIdentityFallback;
   String fRangePattern;
   final NumberFormatterImpl fApproximatelyFormatter;
   final StandardPluralRanges fPluralRanges;

   int identity2d(NumberRangeFormatter.RangeIdentityFallback a, NumberRangeFormatter.RangeIdentityResult b) {
      return a.ordinal() | b.ordinal() << 4;
   }

   private static void getNumberRangeData(ULocale locale, String nsName, NumberRangeFormatterImpl out) {
      StringBuilder sb = new StringBuilder();
      NumberRangeDataSink sink = new NumberRangeDataSink(sb);
      ICUResourceBundle resource = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", locale);
      sb.append("NumberElements/");
      sb.append(nsName);
      sb.append("/miscPatterns");
      String key = sb.toString();

      try {
         resource.getAllItemsWithFallback(key, sink);
      } catch (MissingResourceException var8) {
      }

      if (!sink.isComplete()) {
         resource.getAllItemsWithFallback("NumberElements/latn/miscPatterns", sink);
      }

      sink.fillInDefaults();
      out.fRangePattern = sink.rangePattern;
   }

   public NumberRangeFormatterImpl(RangeMacroProps macros) {
      LocalizedNumberFormatter formatter1 = macros.formatter1 != null ? macros.formatter1.locale(macros.loc) : NumberFormatter.withLocale(macros.loc);
      LocalizedNumberFormatter formatter2 = macros.formatter2 != null ? macros.formatter2.locale(macros.loc) : NumberFormatter.withLocale(macros.loc);
      this.formatterImpl1 = new NumberFormatterImpl(formatter1.resolve());
      this.formatterImpl2 = new NumberFormatterImpl(formatter2.resolve());
      this.fSameFormatters = macros.sameFormatters != 0;
      this.fCollapse = macros.collapse != null ? macros.collapse : NumberRangeFormatter.RangeCollapse.AUTO;
      this.fIdentityFallback = macros.identityFallback != null ? macros.identityFallback : NumberRangeFormatter.RangeIdentityFallback.APPROXIMATELY;
      String nsName = this.formatterImpl1.getRawMicroProps().nsName;
      if (nsName != null && (this.fSameFormatters || nsName.equals(this.formatterImpl2.getRawMicroProps().nsName))) {
         getNumberRangeData(macros.loc, nsName, this);
         if (!this.fSameFormatters || this.fIdentityFallback != NumberRangeFormatter.RangeIdentityFallback.APPROXIMATELY && this.fIdentityFallback != NumberRangeFormatter.RangeIdentityFallback.APPROXIMATELY_OR_SINGLE_VALUE) {
            this.fApproximatelyFormatter = null;
         } else {
            MacroProps approximatelyMacros = new MacroProps();
            approximatelyMacros.approximately = true;
            this.fApproximatelyFormatter = new NumberFormatterImpl(((LocalizedNumberFormatter)formatter1.macros(approximatelyMacros)).resolve());
         }

         this.fPluralRanges = StandardPluralRanges.forLocale(macros.loc);
      } else {
         throw new IllegalArgumentException("Both formatters must have same numbering system");
      }
   }

   public FormattedNumberRange format(DecimalQuantity quantity1, DecimalQuantity quantity2, boolean equalBeforeRounding) {
      FormattedStringBuilder string = new FormattedStringBuilder();
      MicroProps micros1 = this.formatterImpl1.preProcess(quantity1);
      MicroProps micros2;
      if (this.fSameFormatters) {
         micros2 = this.formatterImpl1.preProcess(quantity2);
      } else {
         micros2 = this.formatterImpl2.preProcess(quantity2);
      }

      if (micros1.modInner.semanticallyEquivalent(micros2.modInner) && micros1.modMiddle.semanticallyEquivalent(micros2.modMiddle) && micros1.modOuter.semanticallyEquivalent(micros2.modOuter)) {
         NumberRangeFormatter.RangeIdentityResult identityResult;
         if (equalBeforeRounding) {
            identityResult = NumberRangeFormatter.RangeIdentityResult.EQUAL_BEFORE_ROUNDING;
         } else if (quantity1.equals(quantity2)) {
            identityResult = NumberRangeFormatter.RangeIdentityResult.EQUAL_AFTER_ROUNDING;
         } else {
            identityResult = NumberRangeFormatter.RangeIdentityResult.NOT_EQUAL;
         }

         switch (this.identity2d(this.fIdentityFallback, identityResult)) {
            case 0:
            case 1:
            case 16:
               this.formatSingleValue(quantity1, quantity2, string, micros1, micros2);
               break;
            case 2:
            case 17:
            case 18:
               this.formatApproximately(quantity1, quantity2, string, micros1, micros2);
               break;
            case 3:
            case 19:
            case 32:
            case 33:
            case 34:
            case 35:
               this.formatRange(quantity1, quantity2, string, micros1, micros2);
               break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            default:
               assert false;
         }

         return new FormattedNumberRange(string, quantity1, quantity2, identityResult);
      } else {
         this.formatRange(quantity1, quantity2, string, micros1, micros2);
         return new FormattedNumberRange(string, quantity1, quantity2, NumberRangeFormatter.RangeIdentityResult.NOT_EQUAL);
      }
   }

   private void formatSingleValue(DecimalQuantity quantity1, DecimalQuantity quantity2, FormattedStringBuilder string, MicroProps micros1, MicroProps micros2) {
      if (this.fSameFormatters) {
         int length = NumberFormatterImpl.writeNumber(micros1, quantity1, string, 0);
         NumberFormatterImpl.writeAffixes(micros1, string, 0, length);
      } else {
         this.formatRange(quantity1, quantity2, string, micros1, micros2);
      }

   }

   private void formatApproximately(DecimalQuantity quantity1, DecimalQuantity quantity2, FormattedStringBuilder string, MicroProps micros1, MicroProps micros2) {
      if (this.fSameFormatters) {
         quantity1.resetExponent();
         MicroProps microsAppx = this.fApproximatelyFormatter.preProcess(quantity1);
         int length = NumberFormatterImpl.writeNumber(microsAppx, quantity1, string, 0);
         length += microsAppx.modInner.apply(string, 0, length);
         length += microsAppx.modMiddle.apply(string, 0, length);
         microsAppx.modOuter.apply(string, 0, length);
      } else {
         this.formatRange(quantity1, quantity2, string, micros1, micros2);
      }

   }

   private void formatRange(DecimalQuantity quantity1, DecimalQuantity quantity2, FormattedStringBuilder string, MicroProps micros1, MicroProps micros2) {
      boolean collapseOuter;
      boolean collapseMiddle;
      boolean collapseInner;
      switch (this.fCollapse) {
         case ALL:
         case AUTO:
         case UNIT:
            collapseOuter = micros1.modOuter.semanticallyEquivalent(micros2.modOuter);
            if (!collapseOuter) {
               collapseMiddle = false;
               collapseInner = false;
            } else {
               collapseMiddle = micros1.modMiddle.semanticallyEquivalent(micros2.modMiddle);
               if (!collapseMiddle) {
                  collapseInner = false;
               } else {
                  Modifier mm = micros1.modMiddle;
                  if (this.fCollapse == NumberRangeFormatter.RangeCollapse.UNIT) {
                     if (!mm.containsField(NumberFormat.Field.CURRENCY) && !mm.containsField(NumberFormat.Field.PERCENT)) {
                        collapseMiddle = false;
                     }
                  } else if (this.fCollapse == NumberRangeFormatter.RangeCollapse.AUTO && mm.getCodePointCount() <= 1) {
                     collapseMiddle = false;
                  }

                  if (collapseMiddle && this.fCollapse == NumberRangeFormatter.RangeCollapse.ALL) {
                     collapseInner = micros1.modInner.semanticallyEquivalent(micros2.modInner);
                  } else {
                     collapseInner = false;
                  }
               }
            }
            break;
         default:
            collapseOuter = false;
            collapseMiddle = false;
            collapseInner = false;
      }

      PrefixInfixSuffixLengthHelper h = new PrefixInfixSuffixLengthHelper();
      SimpleModifier.formatTwoArgPattern(this.fRangePattern, string, 0, h, (Format.Field)null);

      assert h.lengthInfix > 0;

      boolean repeatInner = !collapseInner && micros1.modInner.getCodePointCount() > 0;
      boolean repeatMiddle = !collapseMiddle && micros1.modMiddle.getCodePointCount() > 0;
      boolean repeatOuter = !collapseOuter && micros1.modOuter.getCodePointCount() > 0;
      if (repeatInner || repeatMiddle || repeatOuter) {
         if (!PatternProps.isWhiteSpace(string.charAt(h.index1()))) {
            h.lengthInfix += string.insertCodePoint(h.index1(), 32, (Object)null);
         }

         if (!PatternProps.isWhiteSpace(string.charAt(h.index2() - 1))) {
            h.lengthInfix += string.insertCodePoint(h.index2(), 32, (Object)null);
         }
      }

      h.length1 += NumberFormatterImpl.writeNumber(micros1, quantity1, string, h.index0());
      FormattedStringBuilder tempString = new FormattedStringBuilder();
      NumberFormatterImpl.writeNumber(micros2, quantity2, tempString, 0);
      h.length2 += string.insert(h.index2(), tempString);
      if (collapseInner) {
         Modifier mod = this.resolveModifierPlurals(micros1.modInner, micros2.modInner);
         h.lengthSuffix += mod.apply(string, h.index0(), h.index4());
         h.lengthPrefix += mod.getPrefixLength();
         h.lengthSuffix -= mod.getPrefixLength();
      } else {
         h.length1 += micros1.modInner.apply(string, h.index0(), h.index1());
         h.length2 += micros2.modInner.apply(string, h.index2(), h.index4());
      }

      if (collapseMiddle) {
         Modifier mod = this.resolveModifierPlurals(micros1.modMiddle, micros2.modMiddle);
         h.lengthSuffix += mod.apply(string, h.index0(), h.index4());
         h.lengthPrefix += mod.getPrefixLength();
         h.lengthSuffix -= mod.getPrefixLength();
      } else {
         h.length1 += micros1.modMiddle.apply(string, h.index0(), h.index1());
         h.length2 += micros2.modMiddle.apply(string, h.index2(), h.index4());
      }

      if (collapseOuter) {
         Modifier mod = this.resolveModifierPlurals(micros1.modOuter, micros2.modOuter);
         h.lengthSuffix += mod.apply(string, h.index0(), h.index4());
         h.lengthPrefix += mod.getPrefixLength();
         h.lengthSuffix -= mod.getPrefixLength();
      } else {
         h.length1 += micros1.modOuter.apply(string, h.index0(), h.index1());
         h.length2 += micros2.modOuter.apply(string, h.index2(), h.index4());
      }

      FormattedValueStringBuilderImpl.applySpanRange(string, NumberRangeFormatter.SpanField.NUMBER_RANGE_SPAN, 0, h.index0(), h.index1());
      FormattedValueStringBuilderImpl.applySpanRange(string, NumberRangeFormatter.SpanField.NUMBER_RANGE_SPAN, 1, h.index2(), h.index3());
   }

   Modifier resolveModifierPlurals(Modifier first, Modifier second) {
      Modifier.Parameters firstParameters = first.getParameters();
      if (firstParameters == null) {
         return first;
      } else {
         Modifier.Parameters secondParameters = second.getParameters();
         if (secondParameters == null) {
            return first;
         } else {
            StandardPlural resultPlural = this.fPluralRanges.resolve(firstParameters.plural, secondParameters.plural);
            Modifier mod = firstParameters.obj.getModifier(firstParameters.signum, resultPlural);

            assert mod != null;

            return mod;
         }
      }
   }

   private static final class NumberRangeDataSink extends UResource.Sink {
      String rangePattern;
      StringBuilder sb;

      NumberRangeDataSink(StringBuilder sb) {
         this.sb = sb;
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         UResource.Table miscTable = value.getTable();

         for(int i = 0; miscTable.getKeyAndValue(i, key, value); ++i) {
            if (key.contentEquals("range") && !this.hasRangeData()) {
               String pattern = value.getString();
               this.rangePattern = SimpleFormatterImpl.compileToStringMinMaxArguments(pattern, this.sb, 2, 2);
            }
         }

      }

      private boolean hasRangeData() {
         return this.rangePattern != null;
      }

      public boolean isComplete() {
         return this.hasRangeData();
      }

      public void fillInDefaults() {
         if (!this.hasRangeData()) {
            this.rangePattern = SimpleFormatterImpl.compileToStringMinMaxArguments("{0}–{1}", this.sb, 2, 2);
         }

      }
   }
}
