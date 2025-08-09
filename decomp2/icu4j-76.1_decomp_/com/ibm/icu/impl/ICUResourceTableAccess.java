package com.ibm.icu.impl;

import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;

public class ICUResourceTableAccess {
   public static String getTableString(String path, ULocale locale, String tableName, String itemName, String defaultValue) {
      ICUResourceBundle bundle = (ICUResourceBundle)UResourceBundle.getBundleInstance(path, locale.getBaseName());
      return getTableString((ICUResourceBundle)bundle, (String)tableName, (String)null, itemName, defaultValue);
   }

   public static String getTableString(ICUResourceBundle bundle, String tableName, String subtableName, String item, String defaultValue) {
      String result = null;

      try {
         while(true) {
            ICUResourceBundle table = bundle.findWithFallback(tableName);
            if (table == null) {
               return defaultValue;
            }

            ICUResourceBundle stable = table;
            if (subtableName != null) {
               stable = table.findWithFallback(subtableName);
            }

            if (stable != null) {
               result = stable.findStringWithFallback(item);
               if (result != null) {
                  break;
               }
            }

            if (subtableName == null) {
               String currentName = null;
               if (tableName.equals("Countries")) {
                  currentName = LocaleIDs.getCurrentCountryID(item);
               } else if (tableName.equals("Languages")) {
                  currentName = LocaleIDs.getCurrentLanguageID(item);
               }

               if (currentName != null) {
                  result = table.findStringWithFallback(currentName);
                  if (result != null) {
                     break;
                  }
               }
            }

            String fallbackLocale = table.findStringWithFallback("Fallback");
            if (fallbackLocale == null) {
               return defaultValue;
            }

            if (fallbackLocale.length() == 0) {
               fallbackLocale = "root";
            }

            if (fallbackLocale.equals(table.getULocale().getName())) {
               return defaultValue;
            }

            bundle = (ICUResourceBundle)UResourceBundle.getBundleInstance(bundle.getBaseName(), fallbackLocale);
         }
      } catch (Exception var9) {
      }

      return result != null && result.length() > 0 ? result : defaultValue;
   }
}
