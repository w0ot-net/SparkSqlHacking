package com.ibm.icu.text;

import com.ibm.icu.impl.ICULocaleService;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.ICUService;
import com.ibm.icu.impl.coll.CollationLoader;
import com.ibm.icu.impl.coll.CollationTailoring;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.Output;
import com.ibm.icu.util.ULocale;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

final class CollatorServiceShim extends Collator.ServiceShim {
   private static ICULocaleService service = new CService();

   Collator getInstance(ULocale locale) {
      try {
         ULocale[] actualLoc = new ULocale[1];
         Collator coll = (Collator)service.get(locale, actualLoc);
         if (coll == null) {
            throw new MissingResourceException("Could not locate Collator data", "", "");
         } else {
            return (Collator)coll.clone();
         }
      } catch (CloneNotSupportedException e) {
         throw new ICUCloneNotSupportedException(e);
      }
   }

   Object registerInstance(Collator collator, ULocale locale) {
      collator.setLocale(locale, locale);
      return service.registerObject(collator, locale);
   }

   Object registerFactory(Collator.CollatorFactory f) {
      class CFactory extends ICULocaleService.LocaleKeyFactory {
         Collator.CollatorFactory delegate;

         CFactory(Collator.CollatorFactory fctry) {
            super(fctry.visible());
            this.delegate = fctry;
         }

         public Object handleCreate(ULocale loc, int kind, ICUService srvc) {
            Object coll = this.delegate.createCollator(loc);
            return coll;
         }

         public String getDisplayName(String id, ULocale displayLocale) {
            ULocale objectLocale = new ULocale(id);
            return this.delegate.getDisplayName(objectLocale, displayLocale);
         }

         public Set getSupportedIDs() {
            return this.delegate.getSupportedLocaleIDs();
         }
      }

      return service.registerFactory(new CFactory(f));
   }

   boolean unregister(Object registryKey) {
      return service.unregisterFactory((ICUService.Factory)registryKey);
   }

   Locale[] getAvailableLocales() {
      Locale[] result;
      if (service.isDefault()) {
         result = ICUResourceBundle.getAvailableLocales("com/ibm/icu/impl/data/icudata/coll", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
      } else {
         result = service.getAvailableLocales();
      }

      return result;
   }

   ULocale[] getAvailableULocales() {
      ULocale[] result;
      if (service.isDefault()) {
         result = ICUResourceBundle.getAvailableULocales("com/ibm/icu/impl/data/icudata/coll", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
      } else {
         result = service.getAvailableULocales();
      }

      return result;
   }

   String getDisplayName(ULocale objectLocale, ULocale displayLocale) {
      String id = objectLocale.getName();
      return service.getDisplayName(id, displayLocale);
   }

   private static final Collator makeInstance(ULocale desiredLocale) {
      Output<ULocale> validLocale = new Output(ULocale.ROOT);
      CollationTailoring t = CollationLoader.loadTailoring(desiredLocale, validLocale);
      return new RuleBasedCollator(t, (ULocale)validLocale.value);
   }

   private static class CService extends ICULocaleService {
      CService() {
         super("Collator");

         class CollatorFactory extends ICULocaleService.ICUResourceBundleFactory {
            CollatorFactory() {
               super("com/ibm/icu/impl/data/icudata/coll");
            }

            protected Object handleCreate(ULocale uloc, int kind, ICUService srvc) {
               return CollatorServiceShim.makeInstance(uloc);
            }
         }

         this.registerFactory(new CollatorFactory());
         this.markDefault();
      }

      public String validateFallbackLocale() {
         return "";
      }

      protected Object handleDefault(ICUService.Key key, String[] actualIDReturn) {
         if (actualIDReturn != null) {
            actualIDReturn[0] = "root";
         }

         try {
            return CollatorServiceShim.makeInstance(ULocale.ROOT);
         } catch (MissingResourceException var4) {
            return null;
         }
      }
   }
}
