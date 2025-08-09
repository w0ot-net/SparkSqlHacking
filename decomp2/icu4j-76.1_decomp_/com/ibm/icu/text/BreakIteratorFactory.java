package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.ICULocaleService;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.ICUService;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.CharacterIterator;
import java.util.Locale;
import java.util.MissingResourceException;

final class BreakIteratorFactory extends BreakIterator.BreakIteratorServiceShim {
   static final ICULocaleService service = new BFService();
   private static final String[] KIND_NAMES = new String[]{"grapheme", "word", "line", "sentence", "title"};

   public Object registerInstance(BreakIterator iter, ULocale locale, int kind) {
      iter.setText((CharacterIterator)(new java.text.StringCharacterIterator("")));
      return service.registerObject(iter, locale, kind);
   }

   public boolean unregister(Object key) {
      return service.isDefault() ? false : service.unregisterFactory((ICUService.Factory)key);
   }

   public Locale[] getAvailableLocales() {
      return service == null ? ICUResourceBundle.getAvailableLocales() : service.getAvailableLocales();
   }

   public ULocale[] getAvailableULocales() {
      return service == null ? ICUResourceBundle.getAvailableULocales() : service.getAvailableULocales();
   }

   public BreakIterator createBreakIterator(ULocale locale, int kind) {
      if (service.isDefault()) {
         return createBreakInstance(locale, kind);
      } else {
         ULocale[] actualLoc = new ULocale[1];
         BreakIterator iter = (BreakIterator)service.get(locale, kind, actualLoc);
         iter.setLocale(actualLoc[0], actualLoc[0]);
         return iter;
      }
   }

   private static BreakIterator createBreakInstance(ULocale locale, int kind) {
      RuleBasedBreakIterator iter = null;
      ICUResourceBundle rb = ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata/brkitr", locale, ICUResourceBundle.OpenType.LOCALE_ROOT);
      ByteBuffer bytes = null;
      String typeKeyExt = "";
      if (kind == 2) {
         String keyValue = locale.getKeywordValue("lb");
         if (keyValue != null && (keyValue.equals("strict") || keyValue.equals("normal") || keyValue.equals("loose"))) {
            typeKeyExt = "_" + keyValue;
         }

         String language = locale.getLanguage();
         if (language != null && (language.equals("ja") || language.equals("ko"))) {
            keyValue = locale.getKeywordValue("lw");
            if (keyValue != null && keyValue.equals("phrase")) {
               typeKeyExt = typeKeyExt + "_" + keyValue;
            }
         }
      }

      String brkfname;
      try {
         String typeKey = typeKeyExt.isEmpty() ? KIND_NAMES[kind] : KIND_NAMES[kind] + typeKeyExt;
         brkfname = rb.getStringWithFallback("boundaries/" + typeKey);
         String rulesFileName = "brkitr/" + brkfname;
         bytes = ICUBinary.getData(rulesFileName);
      } catch (Exception e) {
         throw new MissingResourceException(e.toString(), "", "");
      }

      try {
         boolean isPhraseBreaking = brkfname != null && brkfname.contains("phrase");
         iter = RuleBasedBreakIterator.getInstanceFromCompiledRules(bytes, isPhraseBreaking);
      } catch (IOException e) {
         Assert.fail((Exception)e);
      }

      ULocale uloc = ULocale.forLocale(rb.getLocale());
      iter.setLocale(uloc, uloc);
      if (kind == 3) {
         String ssKeyword = locale.getKeywordValue("ss");
         if (ssKeyword != null && ssKeyword.equals("standard")) {
            ULocale base = new ULocale(locale.getBaseName());
            return FilteredBreakIteratorBuilder.getInstance(base).wrapIteratorWithFilter(iter);
         }
      }

      return iter;
   }

   private static class BFService extends ICULocaleService {
      BFService() {
         super("BreakIterator");

         class RBBreakIteratorFactory extends ICULocaleService.ICUResourceBundleFactory {
            protected Object handleCreate(ULocale loc, int kind, ICUService srvc) {
               return BreakIteratorFactory.createBreakInstance(loc, kind);
            }
         }

         this.registerFactory(new RBBreakIteratorFactory());
         this.markDefault();
      }

      public String validateFallbackLocale() {
         return "";
      }
   }
}
