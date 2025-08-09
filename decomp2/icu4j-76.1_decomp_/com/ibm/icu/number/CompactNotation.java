package com.ibm.icu.number;

import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.impl.number.CompactData;
import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.impl.number.MicroProps;
import com.ibm.icu.impl.number.MicroPropsGenerator;
import com.ibm.icu.impl.number.MutablePatternModifier;
import com.ibm.icu.impl.number.PatternStringParser;
import com.ibm.icu.text.CompactDecimalFormat;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.ULocale;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CompactNotation extends Notation {
   final CompactDecimalFormat.CompactStyle compactStyle;
   final Map compactCustomData;

   /** @deprecated */
   @Deprecated
   public static CompactNotation forCustomData(Map compactCustomData) {
      return new CompactNotation(compactCustomData);
   }

   CompactNotation(CompactDecimalFormat.CompactStyle compactStyle) {
      this.compactCustomData = null;
      this.compactStyle = compactStyle;
   }

   CompactNotation(Map compactCustomData) {
      this.compactStyle = null;
      this.compactCustomData = compactCustomData;
   }

   MicroPropsGenerator withLocaleData(ULocale locale, String nsName, CompactData.CompactType compactType, PluralRules rules, MutablePatternModifier buildReference, boolean safe, MicroPropsGenerator parent) {
      return new CompactHandler(this, locale, nsName, compactType, rules, buildReference, safe, parent);
   }

   private static class CompactHandler implements MicroPropsGenerator {
      final PluralRules rules;
      final MicroPropsGenerator parent;
      final Map precomputedMods;
      final MutablePatternModifier unsafePatternModifier;
      final CompactData data;

      private CompactHandler(CompactNotation notation, ULocale locale, String nsName, CompactData.CompactType compactType, PluralRules rules, MutablePatternModifier buildReference, boolean safe, MicroPropsGenerator parent) {
         this.rules = rules;
         this.parent = parent;
         this.data = new CompactData();
         if (notation.compactStyle != null) {
            this.data.populate(locale, nsName, notation.compactStyle, compactType);
         } else {
            this.data.populate(notation.compactCustomData);
         }

         if (safe) {
            this.precomputedMods = new HashMap();
            this.precomputeAllModifiers(buildReference);
            this.unsafePatternModifier = null;
         } else {
            this.precomputedMods = null;
            this.unsafePatternModifier = buildReference;
         }

      }

      private void precomputeAllModifiers(MutablePatternModifier buildReference) {
         Set<String> allPatterns = new HashSet();
         this.data.getUniquePatterns(allPatterns);

         for(String patternString : allPatterns) {
            PatternStringParser.ParsedPatternInfo patternInfo = PatternStringParser.parseToPatternInfo(patternString);
            buildReference.setPatternInfo(patternInfo, NumberFormat.Field.COMPACT);
            this.precomputedMods.put(patternString, buildReference.createImmutable());
         }

      }

      public MicroProps processQuantity(DecimalQuantity quantity) {
         MicroProps micros = this.parent.processQuantity(quantity);

         assert micros.rounder != null;

         int multiplier = 0;
         int magnitude;
         if (quantity.isZeroish()) {
            magnitude = 0;
            micros.rounder.apply(quantity);
         } else {
            multiplier = micros.rounder.chooseMultiplierAndApply(quantity, this.data);
            magnitude = quantity.isZeroish() ? 0 : quantity.getMagnitude();
            magnitude -= multiplier;
         }

         String patternString = this.data.getPattern(magnitude, this.rules, quantity);
         if (patternString != null) {
            if (this.precomputedMods != null) {
               MutablePatternModifier.ImmutablePatternModifier mod = (MutablePatternModifier.ImmutablePatternModifier)this.precomputedMods.get(patternString);
               mod.applyToMicros(micros, quantity);
            } else {
               PatternStringParser.ParsedPatternInfo patternInfo = PatternStringParser.parseToPatternInfo(patternString);
               this.unsafePatternModifier.setPatternInfo(patternInfo, NumberFormat.Field.COMPACT);
               this.unsafePatternModifier.setNumberProperties(quantity.signum(), (StandardPlural)null);
               micros.modMiddle = this.unsafePatternModifier;
            }
         }

         quantity.adjustExponent(-1 * multiplier);
         micros.rounder = null;
         return micros;
      }
   }
}
