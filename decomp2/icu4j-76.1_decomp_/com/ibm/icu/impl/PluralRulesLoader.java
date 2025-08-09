package com.ibm.icu.impl;

import com.ibm.icu.impl.number.range.StandardPluralRanges;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.TreeMap;

public class PluralRulesLoader extends PluralRules.Factory {
   private final Map pluralRulesCache = new HashMap();
   private Map localeIdToCardinalRulesId;
   private Map localeIdToOrdinalRulesId;
   private Map rulesIdToEquivalentULocale;
   public static final PluralRulesLoader loader = new PluralRulesLoader();

   private PluralRulesLoader() {
   }

   public ULocale[] getAvailableULocales() {
      Set<String> keys = this.getLocaleIdToRulesIdMap(PluralRules.PluralType.CARDINAL).keySet();
      Set<ULocale> locales = new LinkedHashSet(keys.size());
      Iterator<String> iter = keys.iterator();

      while(iter.hasNext()) {
         locales.add(ULocale.createCanonical((String)iter.next()));
      }

      return (ULocale[])locales.toArray(new ULocale[0]);
   }

   public ULocale getFunctionalEquivalent(ULocale locale, boolean[] isAvailable) {
      if (isAvailable != null && isAvailable.length > 0) {
         String localeId = ULocale.canonicalize(locale.getBaseName());
         Map<String, String> idMap = this.getLocaleIdToRulesIdMap(PluralRules.PluralType.CARDINAL);
         isAvailable[0] = idMap.containsKey(localeId);
      }

      String rulesId = this.getRulesIdForLocale(locale, PluralRules.PluralType.CARDINAL);
      if (rulesId != null && rulesId.trim().length() != 0) {
         ULocale result = (ULocale)this.getRulesIdToEquivalentULocaleMap().get(rulesId);
         return result == null ? ULocale.ROOT : result;
      } else {
         return ULocale.ROOT;
      }
   }

   private Map getLocaleIdToRulesIdMap(PluralRules.PluralType type) {
      this.checkBuildRulesIdMaps();
      return type == PluralRules.PluralType.CARDINAL ? this.localeIdToCardinalRulesId : this.localeIdToOrdinalRulesId;
   }

   private Map getRulesIdToEquivalentULocaleMap() {
      this.checkBuildRulesIdMaps();
      return this.rulesIdToEquivalentULocale;
   }

   private void checkBuildRulesIdMaps() {
      boolean haveMap;
      synchronized(this) {
         haveMap = this.localeIdToCardinalRulesId != null;
      }

      if (!haveMap) {
         Map<String, String> tempLocaleIdToCardinalRulesId;
         Map<String, String> tempLocaleIdToOrdinalRulesId;
         Map<String, ULocale> tempRulesIdToEquivalentULocale;
         try {
            UResourceBundle pluralb = this.getPluralBundle();
            UResourceBundle localeb = pluralb.get("locales");
            tempLocaleIdToCardinalRulesId = new TreeMap();
            tempRulesIdToEquivalentULocale = new HashMap();

            for(int i = 0; i < localeb.getSize(); ++i) {
               UResourceBundle b = localeb.get(i);
               String id = b.getKey();
               String value = b.getString().intern();
               tempLocaleIdToCardinalRulesId.put(id, value);
               if (!tempRulesIdToEquivalentULocale.containsKey(value)) {
                  tempRulesIdToEquivalentULocale.put(value, new ULocale(id));
               }
            }

            localeb = pluralb.get("locales_ordinals");
            tempLocaleIdToOrdinalRulesId = new TreeMap();

            for(int i = 0; i < localeb.getSize(); ++i) {
               UResourceBundle b = localeb.get(i);
               String id = b.getKey();
               String value = b.getString().intern();
               tempLocaleIdToOrdinalRulesId.put(id, value);
            }
         } catch (MissingResourceException var14) {
            tempLocaleIdToCardinalRulesId = Collections.emptyMap();
            tempLocaleIdToOrdinalRulesId = Collections.emptyMap();
            tempRulesIdToEquivalentULocale = Collections.emptyMap();
         }

         synchronized(this) {
            if (this.localeIdToCardinalRulesId == null) {
               this.localeIdToCardinalRulesId = tempLocaleIdToCardinalRulesId;
               this.localeIdToOrdinalRulesId = tempLocaleIdToOrdinalRulesId;
               this.rulesIdToEquivalentULocale = tempRulesIdToEquivalentULocale;
            }
         }
      }

   }

   public String getRulesIdForLocale(ULocale locale, PluralRules.PluralType type) {
      Map<String, String> idMap = this.getLocaleIdToRulesIdMap(type);
      String localeId = ULocale.canonicalize(locale.getBaseName());

      int ix;
      String rulesId;
      for(rulesId = null; null == (rulesId = (String)idMap.get(localeId)); localeId = localeId.substring(0, ix)) {
         ix = localeId.lastIndexOf("_");
         if (ix == -1) {
            break;
         }
      }

      return rulesId;
   }

   public PluralRules getOrCreateRulesForLocale(ULocale locale, PluralRules.PluralType type) {
      String rulesId = this.getRulesIdForLocale(locale, type);
      if (rulesId != null && rulesId.trim().length() != 0) {
         String rangesId = StandardPluralRanges.getSetForLocale(locale);
         String cacheKey = rulesId + "/" + rangesId;
         PluralRules rules = null;
         boolean hasRules;
         synchronized(this.pluralRulesCache) {
            hasRules = this.pluralRulesCache.containsKey(cacheKey);
            if (hasRules) {
               rules = (PluralRules)this.pluralRulesCache.get(cacheKey);
            }
         }

         if (!hasRules) {
            try {
               UResourceBundle pluralb = this.getPluralBundle();
               UResourceBundle rulesb = pluralb.get("rules");
               UResourceBundle setb = rulesb.get(rulesId);
               StringBuilder sb = new StringBuilder();

               for(int i = 0; i < setb.getSize(); ++i) {
                  UResourceBundle b = setb.get(i);
                  if (i > 0) {
                     sb.append("; ");
                  }

                  sb.append(b.getKey());
                  sb.append(": ");
                  sb.append(b.getString());
               }

               StandardPluralRanges ranges = StandardPluralRanges.forSet(rangesId);
               rules = PluralRules.newInternal(sb.toString(), ranges);
            } catch (ParseException var16) {
            } catch (MissingResourceException var17) {
            }

            synchronized(this.pluralRulesCache) {
               if (this.pluralRulesCache.containsKey(cacheKey)) {
                  rules = (PluralRules)this.pluralRulesCache.get(cacheKey);
               } else {
                  this.pluralRulesCache.put(cacheKey, rules);
               }
            }
         }

         return rules;
      } else {
         return null;
      }
   }

   public UResourceBundle getPluralBundle() throws MissingResourceException {
      return ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", "plurals", ICUResourceBundle.ICU_DATA_CLASS_LOADER, true);
   }

   public PluralRules forLocale(ULocale locale, PluralRules.PluralType type) {
      PluralRules rules = this.getOrCreateRulesForLocale(locale, type);
      if (rules == null) {
         rules = PluralRules.DEFAULT;
      }

      return rules;
   }

   public boolean hasOverride(ULocale locale) {
      return false;
   }
}
