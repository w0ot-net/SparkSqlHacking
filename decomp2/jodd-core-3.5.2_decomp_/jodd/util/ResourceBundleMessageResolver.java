package jodd.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourceBundleMessageResolver {
   protected Locale fallbackLocale = LocaleUtil.getLocale("en");
   protected String fallbackBundlename = "messages";
   protected List defaultBundles = new ArrayList();
   protected boolean cacheResourceBundles = true;
   protected final Set misses = new HashSet();
   protected final Map notmisses = new HashMap();

   public void addDefaultBundle(String bundleName) {
      this.defaultBundles.add(bundleName);
   }

   public void deleteAllDefaultBundles() {
      this.defaultBundles.clear();
   }

   private String calcIndexKey(String key) {
      String indexedKey = null;
      if (key.indexOf(91) != -1) {
         int i = -1;

         String a;
         String b;
         for(indexedKey = key; (i = indexedKey.indexOf(91, i + 1)) != -1; indexedKey = a + "[*" + b) {
            int j = indexedKey.indexOf(93, i);
            a = indexedKey.substring(0, i);
            b = indexedKey.substring(j);
         }
      }

      return indexedKey;
   }

   private String getMessage(String bundleName, Locale locale, String key, String indexedKey) {
      String msg = this.getMessage(bundleName, locale, key);
      if (msg != null) {
         return msg;
      } else {
         if (indexedKey != null) {
            msg = this.getMessage(bundleName, locale, indexedKey);
            if (msg != null) {
               return msg;
            }
         }

         return null;
      }
   }

   public String findMessage(String bundleName, Locale locale, String key) {
      String indexedKey = this.calcIndexKey(key);
      String name = bundleName;

      while(true) {
         String msg = this.getMessage(name, locale, key, indexedKey);
         if (msg != null) {
            return msg;
         }

         if (bundleName == null || bundleName.length() == 0) {
            for(String bname : this.defaultBundles) {
               String msg = this.getMessage(bname, locale, key, indexedKey);
               if (msg != null) {
                  return msg;
               }
            }

            return null;
         }

         int ndx = bundleName.lastIndexOf(46);
         if (ndx == -1) {
            bundleName = null;
            name = this.fallbackBundlename;
         } else {
            bundleName = bundleName.substring(0, ndx);
            name = bundleName + '.' + this.fallbackBundlename;
         }
      }
   }

   public String findDefaultMessage(Locale locale, String key) {
      String indexedKey = this.calcIndexKey(key);
      String msg = this.getMessage(this.fallbackBundlename, locale, key, indexedKey);
      if (msg != null) {
         return msg;
      } else {
         for(String bname : this.defaultBundles) {
            msg = this.getMessage(bname, locale, key, indexedKey);
            if (msg != null) {
               return msg;
            }
         }

         return null;
      }
   }

   public String getMessage(String bundleName, Locale locale, String key) {
      ResourceBundle bundle = this.findResourceBundle(bundleName, locale);
      if (bundle == null) {
         return null;
      } else {
         try {
            return bundle.getString(key);
         } catch (MissingResourceException var6) {
            return null;
         }
      }
   }

   public ResourceBundle findResourceBundle(String bundleName, Locale locale) {
      if (bundleName == null) {
         bundleName = this.fallbackBundlename;
      }

      if (locale == null) {
         locale = this.fallbackLocale;
      }

      if (!this.cacheResourceBundles) {
         try {
            return this.getBundle(bundleName, locale, ClassLoaderUtil.getDefaultClassLoader());
         } catch (MissingResourceException var5) {
            return null;
         }
      } else {
         String key = bundleName + '_' + LocaleUtil.resolveLocaleCode(locale);

         try {
            if (!this.misses.contains(key)) {
               ResourceBundle bundle = (ResourceBundle)this.notmisses.get(key);
               if (bundle == null) {
                  bundle = this.getBundle(bundleName, locale, ClassLoaderUtil.getDefaultClassLoader());
                  this.notmisses.put(key, bundle);
               }

               return bundle;
            }
         } catch (MissingResourceException var6) {
            this.misses.add(key);
         }

         return null;
      }
   }

   protected ResourceBundle getBundle(String bundleName, Locale locale, ClassLoader classLoader) {
      return ResourceBundle.getBundle(bundleName, locale, classLoader);
   }

   public String getFallbackBundlename() {
      return this.fallbackBundlename;
   }

   public void setFallbackBundlename(String fallbackBundlename) {
      this.fallbackBundlename = fallbackBundlename;
   }

   public Locale getFallbackLocale() {
      return this.fallbackLocale;
   }

   public void setFallbackLocale(Locale fallbackLocale) {
      this.fallbackLocale = fallbackLocale;
   }

   public void setFallbackLocale(String localeCode) {
      this.fallbackLocale = LocaleUtil.getLocale(localeCode);
   }

   public boolean isCacheResourceBundles() {
      return this.cacheResourceBundles;
   }

   public void setCacheResourceBundles(boolean cacheResourceBundles) {
      this.cacheResourceBundles = cacheResourceBundles;
   }
}
