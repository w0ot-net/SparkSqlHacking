package com.ibm.icu.impl.number;

import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.text.CurrencyPluralInfo;

public class CurrencyPluralInfoAffixProvider implements AffixPatternProvider {
   private final PropertiesAffixPatternProvider[] affixesByPlural;

   public CurrencyPluralInfoAffixProvider(CurrencyPluralInfo cpi, DecimalFormatProperties properties) {
      this.affixesByPlural = new PropertiesAffixPatternProvider[StandardPlural.COUNT];
      DecimalFormatProperties pluralProperties = new DecimalFormatProperties();
      pluralProperties.copyFrom(properties);

      for(StandardPlural plural : StandardPlural.VALUES) {
         String pattern = cpi.getCurrencyPluralPattern(plural.getKeyword());
         PatternStringParser.parseToExistingProperties(pattern, pluralProperties);
         this.affixesByPlural[plural.ordinal()] = new PropertiesAffixPatternProvider(pluralProperties);
      }

   }

   public char charAt(int flags, int i) {
      int pluralOrdinal = flags & 255;
      return this.affixesByPlural[pluralOrdinal].charAt(flags, i);
   }

   public int length(int flags) {
      int pluralOrdinal = flags & 255;
      return this.affixesByPlural[pluralOrdinal].length(flags);
   }

   public String getString(int flags) {
      int pluralOrdinal = flags & 255;
      return this.affixesByPlural[pluralOrdinal].getString(flags);
   }

   public boolean positiveHasPlusSign() {
      return this.affixesByPlural[StandardPlural.OTHER.ordinal()].positiveHasPlusSign();
   }

   public boolean hasNegativeSubpattern() {
      return this.affixesByPlural[StandardPlural.OTHER.ordinal()].hasNegativeSubpattern();
   }

   public boolean negativeHasMinusSign() {
      return this.affixesByPlural[StandardPlural.OTHER.ordinal()].negativeHasMinusSign();
   }

   public boolean hasCurrencySign() {
      return this.affixesByPlural[StandardPlural.OTHER.ordinal()].hasCurrencySign();
   }

   public boolean containsSymbolType(int type) {
      return this.affixesByPlural[StandardPlural.OTHER.ordinal()].containsSymbolType(type);
   }

   public boolean hasBody() {
      return this.affixesByPlural[StandardPlural.OTHER.ordinal()].hasBody();
   }

   public boolean currencyAsDecimal() {
      return this.affixesByPlural[StandardPlural.OTHER.ordinal()].currencyAsDecimal();
   }
}
