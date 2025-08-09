package org.apache.commons.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class LocaleUtils {
   private static List cAvailableLocaleList;
   private static Set cAvailableLocaleSet;
   private static final Map cLanguagesByCountry = Collections.synchronizedMap(new HashMap());
   private static final Map cCountriesByLanguage = Collections.synchronizedMap(new HashMap());

   public static Locale toLocale(String str) {
      if (str == null) {
         return null;
      } else {
         int len = str.length();
         if (len != 2 && len != 5 && len < 7) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
         } else {
            char ch0 = str.charAt(0);
            char ch1 = str.charAt(1);
            if (ch0 >= 'a' && ch0 <= 'z' && ch1 >= 'a' && ch1 <= 'z') {
               if (len == 2) {
                  return new Locale(str, "");
               } else if (str.charAt(2) != '_') {
                  throw new IllegalArgumentException("Invalid locale format: " + str);
               } else {
                  char ch3 = str.charAt(3);
                  if (ch3 == '_') {
                     return new Locale(str.substring(0, 2), "", str.substring(4));
                  } else {
                     char ch4 = str.charAt(4);
                     if (ch3 >= 'A' && ch3 <= 'Z' && ch4 >= 'A' && ch4 <= 'Z') {
                        if (len == 5) {
                           return new Locale(str.substring(0, 2), str.substring(3, 5));
                        } else if (str.charAt(5) != '_') {
                           throw new IllegalArgumentException("Invalid locale format: " + str);
                        } else {
                           return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
                        }
                     } else {
                        throw new IllegalArgumentException("Invalid locale format: " + str);
                     }
                  }
               }
            } else {
               throw new IllegalArgumentException("Invalid locale format: " + str);
            }
         }
      }
   }

   public static List localeLookupList(Locale locale) {
      return localeLookupList(locale, locale);
   }

   public static List localeLookupList(Locale locale, Locale defaultLocale) {
      List list = new ArrayList(4);
      if (locale != null) {
         list.add(locale);
         if (locale.getVariant().length() > 0) {
            list.add(new Locale(locale.getLanguage(), locale.getCountry()));
         }

         if (locale.getCountry().length() > 0) {
            list.add(new Locale(locale.getLanguage(), ""));
         }

         if (!list.contains(defaultLocale)) {
            list.add(defaultLocale);
         }
      }

      return Collections.unmodifiableList(list);
   }

   public static List availableLocaleList() {
      if (cAvailableLocaleList == null) {
         initAvailableLocaleList();
      }

      return cAvailableLocaleList;
   }

   private static synchronized void initAvailableLocaleList() {
      if (cAvailableLocaleList == null) {
         List list = Arrays.asList(Locale.getAvailableLocales());
         cAvailableLocaleList = Collections.unmodifiableList(list);
      }

   }

   public static Set availableLocaleSet() {
      if (cAvailableLocaleSet == null) {
         initAvailableLocaleSet();
      }

      return cAvailableLocaleSet;
   }

   private static synchronized void initAvailableLocaleSet() {
      if (cAvailableLocaleSet == null) {
         cAvailableLocaleSet = Collections.unmodifiableSet(new HashSet(availableLocaleList()));
      }

   }

   public static boolean isAvailableLocale(Locale locale) {
      return availableLocaleList().contains(locale);
   }

   public static List languagesByCountry(String countryCode) {
      List langs = (List)cLanguagesByCountry.get(countryCode);
      if (langs == null) {
         if (countryCode == null) {
            langs = Collections.EMPTY_LIST;
         } else {
            langs = new ArrayList();
            List locales = availableLocaleList();

            for(int i = 0; i < locales.size(); ++i) {
               Locale locale = (Locale)locales.get(i);
               if (countryCode.equals(locale.getCountry()) && locale.getVariant().length() == 0) {
                  langs.add(locale);
               }
            }

            langs = Collections.unmodifiableList(langs);
         }

         cLanguagesByCountry.put(countryCode, langs);
      }

      return langs;
   }

   public static List countriesByLanguage(String languageCode) {
      List countries = (List)cCountriesByLanguage.get(languageCode);
      if (countries == null) {
         if (languageCode == null) {
            countries = Collections.EMPTY_LIST;
         } else {
            countries = new ArrayList();
            List locales = availableLocaleList();

            for(int i = 0; i < locales.size(); ++i) {
               Locale locale = (Locale)locales.get(i);
               if (languageCode.equals(locale.getLanguage()) && locale.getCountry().length() != 0 && locale.getVariant().length() == 0) {
                  countries.add(locale);
               }
            }

            countries = Collections.unmodifiableList(countries);
         }

         cCountriesByLanguage.put(languageCode, countries);
      }

      return countries;
   }
}
