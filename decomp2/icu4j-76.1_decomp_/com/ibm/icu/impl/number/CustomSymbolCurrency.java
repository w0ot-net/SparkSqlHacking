package com.ibm.icu.impl.number;

import com.ibm.icu.text.DecimalFormatSymbols;
import com.ibm.icu.util.Currency;
import com.ibm.icu.util.ULocale;

public class CustomSymbolCurrency extends Currency {
   private static final long serialVersionUID = 2497493016770137670L;
   private String symbol1;
   private String symbol2;

   public static Currency resolve(Currency currency, ULocale locale, DecimalFormatSymbols symbols) {
      if (currency == null) {
         currency = symbols.getCurrency();
      }

      if (currency == null) {
         return Currency.getInstance("XXX");
      } else if (!currency.equals(symbols.getCurrency())) {
         return currency;
      } else {
         String currency1Sym = symbols.getCurrencySymbol();
         String currency2Sym = symbols.getInternationalCurrencySymbol();
         String currency1 = currency.getName((ULocale)symbols.getULocale(), 0, (boolean[])null);
         String currency2 = currency.getCurrencyCode();
         return (Currency)(currency1.equals(currency1Sym) && currency2.equals(currency2Sym) ? currency : new CustomSymbolCurrency(currency2, currency1Sym, currency2Sym));
      }
   }

   public CustomSymbolCurrency(String isoCode, String currency1Sym, String currency2Sym) {
      super(isoCode);
      this.symbol1 = currency1Sym;
      this.symbol2 = currency2Sym;
   }

   public String getName(ULocale locale, int nameStyle, boolean[] isChoiceFormat) {
      if (nameStyle == 0) {
         if (isChoiceFormat != null) {
            isChoiceFormat[0] = false;
         }

         return this.symbol1;
      } else {
         return super.getName(locale, nameStyle, isChoiceFormat);
      }
   }

   public String getName(ULocale locale, int nameStyle, String pluralCount, boolean[] isChoiceFormat) {
      return super.getName(locale, nameStyle, pluralCount, isChoiceFormat);
   }

   public String getCurrencyCode() {
      return this.symbol2;
   }

   public int hashCode() {
      return super.hashCode() ^ this.symbol1.hashCode() ^ this.symbol2.hashCode();
   }

   public boolean equals(Object other) {
      return super.equals(other) && ((CustomSymbolCurrency)other).symbol1.equals(this.symbol1) && ((CustomSymbolCurrency)other).symbol2.equals(this.symbol2);
   }
}
