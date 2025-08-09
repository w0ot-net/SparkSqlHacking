package com.ibm.icu.text;

import com.ibm.icu.impl.CurrencyData;
import com.ibm.icu.util.ULocale;
import java.util.Locale;
import java.util.Map;

public abstract class CurrencyDisplayNames {
   public static CurrencyDisplayNames getInstance(ULocale locale) {
      return CurrencyData.provider.getInstance(locale, true);
   }

   public static CurrencyDisplayNames getInstance(Locale locale) {
      return getInstance(locale, false);
   }

   public static CurrencyDisplayNames getInstance(ULocale locale, boolean noSubstitute) {
      return CurrencyData.provider.getInstance(locale, !noSubstitute);
   }

   public static CurrencyDisplayNames getInstance(Locale locale, boolean noSubstitute) {
      return getInstance(ULocale.forLocale(locale), noSubstitute);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasData() {
      return CurrencyData.provider.hasData();
   }

   public abstract ULocale getULocale();

   public abstract String getSymbol(String var1);

   public abstract String getNarrowSymbol(String var1);

   public abstract String getFormalSymbol(String var1);

   public abstract String getVariantSymbol(String var1);

   public abstract String getName(String var1);

   public abstract String getPluralName(String var1, String var2);

   public abstract Map symbolMap();

   public abstract Map nameMap();

   /** @deprecated */
   @Deprecated
   protected CurrencyDisplayNames() {
   }
}
