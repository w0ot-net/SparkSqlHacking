package jodd.util;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleUtil {
   protected static Map locales = new HashMap();

   protected static LocaleData lookupLocaleData(String code) {
      LocaleData localeData = (LocaleData)locales.get(code);
      if (localeData == null) {
         String[] data = decodeLocaleCode(code);
         localeData = new LocaleData(new Locale(data[0], data[1], data[2]));
         locales.put(code, localeData);
      }

      return localeData;
   }

   protected static LocaleData lookupLocaleData(Locale locale) {
      return lookupLocaleData(resolveLocaleCode(locale));
   }

   public static Locale getLocale(String language, String country, String variant) {
      LocaleData localeData = lookupLocaleData(resolveLocaleCode(language, country, variant));
      return localeData.locale;
   }

   public static Locale getLocale(String language, String country) {
      return getLocale(language, country, (String)null);
   }

   public static Locale getLocale(String languageCode) {
      LocaleData localeData = lookupLocaleData(languageCode);
      return localeData.locale;
   }

   public static String resolveLocaleCode(String lang, String country, String variant) {
      StringBuilder code = new StringBuilder(lang);
      if (!StringUtil.isEmpty(country)) {
         code.append('_').append(country);
         if (!StringUtil.isEmpty(variant)) {
            code.append('_').append(variant);
         }
      }

      return code.toString();
   }

   public static String resolveLocaleCode(Locale locale) {
      return resolveLocaleCode(locale.getLanguage(), locale.getCountry(), locale.getVariant());
   }

   public static String[] decodeLocaleCode(String localeCode) {
      String[] result = new String[3];
      String[] data = StringUtil.splitc(localeCode, '_');
      result[0] = data[0];
      result[1] = result[2] = "";
      if (data.length >= 2) {
         result[1] = data[1];
         if (data.length >= 3) {
            result[2] = data[2];
         }
      }

      return result;
   }

   public static DateFormatSymbolsEx getDateFormatSymbols(Locale locale) {
      LocaleData localeData = lookupLocaleData(locale);
      DateFormatSymbolsEx dfs = localeData.dateFormatSymbols;
      if (dfs == null) {
         dfs = new DateFormatSymbolsEx(locale);
         localeData.dateFormatSymbols = dfs;
      }

      return dfs;
   }

   public static NumberFormat getNumberFormat(Locale locale) {
      LocaleData localeData = lookupLocaleData(locale);
      NumberFormat nf = localeData.numberFormat;
      if (nf == null) {
         nf = NumberFormat.getInstance(locale);
         localeData.numberFormat = nf;
      }

      return nf;
   }

   static class LocaleData {
      final Locale locale;
      DateFormatSymbolsEx dateFormatSymbols;
      NumberFormat numberFormat;

      LocaleData(Locale locale) {
         this.locale = locale;
      }
   }
}
