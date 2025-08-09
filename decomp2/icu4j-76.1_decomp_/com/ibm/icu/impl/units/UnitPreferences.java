package com.ibm.icu.impl.units;

import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.UResource;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnitPreferences {
   private static final Map measurementSystem;
   private HashMap mapToUnitPreferences = new HashMap();

   public UnitPreferences() {
      ICUResourceBundle resource = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", "units");
      UnitPreferencesSink sink = new UnitPreferencesSink();
      resource.getAllItemsWithFallback("unitPreferenceData", sink);
      this.mapToUnitPreferences = sink.getMapToUnitPreferences();
   }

   public static String formMapKey(String category, String usage) {
      return category + "++" + usage;
   }

   private static String[] getAllUsages(String usage) {
      ArrayList<String> result = new ArrayList();
      result.add(usage);

      for(int i = usage.length() - 1; i >= 0; --i) {
         if (usage.charAt(i) == '-') {
            result.add(usage.substring(0, i));
         }
      }

      if (!usage.equals("default")) {
         result.add("default");
      }

      return (String[])result.toArray(new String[0]);
   }

   public UnitPreference[] getPreferencesFor(String category, String usage, ULocale locale, UnitsData data) {
      if ("temperature".equals(category)) {
         String localeUnit = locale.getKeywordValue("mu");
         if ("fahrenhe".equals(localeUnit)) {
            localeUnit = "fahrenheit";
         }

         String localeUnitCategory;
         try {
            localeUnitCategory = localeUnit == null ? null : data.getCategory(MeasureUnitImpl.forIdentifier(localeUnit));
         } catch (Exception var25) {
            localeUnitCategory = null;
         }

         if (localeUnitCategory != null && category.equals(localeUnitCategory)) {
            UnitPreference[] preferences = new UnitPreference[]{new UnitPreference(localeUnit, (String)null, (String)null)};
            return preferences;
         }
      }

      String region = ULocale.getRegionForSupplementalData(locale, true);
      String localeSystem = locale.getKeywordValue("measure");
      boolean isLocaleSystem = measurementSystem.containsKey(localeSystem);
      String[] subUsages = getAllUsages(usage);
      UnitPreference[] result = null;

      for(String subUsage : subUsages) {
         result = this.getUnitPreferences(category, subUsage, region);
         if (result != null && isLocaleSystem) {
            ConversionRates rates = new ConversionRates();
            boolean unitsMatchSystem = true;

            for(UnitPreference unitPref : result) {
               MeasureUnitImpl measureUnit = MeasureUnitImpl.forIdentifier(unitPref.getUnit());

               for(SingleUnitImpl singleUnit : new ArrayList(measureUnit.getSingleUnits())) {
                  String systems = rates.extractSystems(singleUnit);
                  if (!systems.contains("metric_adjacent") && !systems.contains(localeSystem)) {
                     unitsMatchSystem = false;
                  }
               }
            }

            if (!unitsMatchSystem) {
               String newRegion = (String)measurementSystem.get(localeSystem);
               result = this.getUnitPreferences(category, subUsage, newRegion);
            }
         }

         if (result != null) {
            break;
         }
      }

      assert result != null : "At least the category must be exist";

      return result;
   }

   private UnitPreference[] getUnitPreferences(String category, String usage, String region) {
      String key = formMapKey(category, usage);
      if (this.mapToUnitPreferences.containsKey(key)) {
         HashMap<String, UnitPreference[]> unitPreferencesMap = (HashMap)this.mapToUnitPreferences.get(key);
         UnitPreference[] result = unitPreferencesMap.containsKey(region) ? (UnitPreference[])unitPreferencesMap.get(region) : (UnitPreference[])unitPreferencesMap.get("001");

         assert result != null;

         return result;
      } else {
         return null;
      }
   }

   static {
      Map<String, String> tempMS = new HashMap();
      tempMS.put("metric", "001");
      tempMS.put("ussystem", "US");
      tempMS.put("uksystem", "GB");
      measurementSystem = Collections.unmodifiableMap(tempMS);
   }

   public static class UnitPreference {
      private final String unit;
      private final BigDecimal geq;
      private final String skeleton;

      public UnitPreference(String unit, String geq, String skeleton) {
         this.unit = unit;
         this.geq = geq == null ? BigDecimal.valueOf(Double.MIN_VALUE) : new BigDecimal(geq);
         this.skeleton = skeleton == null ? "" : skeleton;
      }

      public String getUnit() {
         return this.unit;
      }

      public BigDecimal getGeq() {
         return this.geq;
      }

      public String getSkeleton() {
         return this.skeleton;
      }
   }

   public static class UnitPreferencesSink extends UResource.Sink {
      private HashMap mapToUnitPreferences = new HashMap();

      public HashMap getMapToUnitPreferences() {
         return this.mapToUnitPreferences;
      }

      public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
         assert "unitPreferenceData".equals(key.toString());

         UResource.Table categoryTable = value.getTable();

         for(int i = 0; categoryTable.getKeyAndValue(i, key, value); ++i) {
            assert value.getType() == 2;

            String category = key.toString();
            UResource.Table usageTable = value.getTable();

            for(int j = 0; usageTable.getKeyAndValue(j, key, value); ++j) {
               assert value.getType() == 2;

               String usage = key.toString();
               UResource.Table regionTable = value.getTable();

               for(int k = 0; regionTable.getKeyAndValue(k, key, value); ++k) {
                  assert value.getType() == 8;

                  String region = key.toString();
                  UResource.Array preferencesTable = value.getArray();
                  ArrayList<UnitPreference> unitPreferences = new ArrayList();

                  for(int l = 0; preferencesTable.getValue(l, value); ++l) {
                     assert value.getType() == 2;

                     UResource.Table singlePrefTable = value.getTable();
                     String unit = null;
                     String geq = "1";
                     String skeleton = "";

                     for(int m = 0; singlePrefTable.getKeyAndValue(m, key, value); ++m) {
                        assert value.getType() == 0;

                        String keyString = key.toString();
                        if ("unit".equals(keyString)) {
                           unit = value.getString();
                        } else if ("geq".equals(keyString)) {
                           geq = value.getString();
                        } else if ("skeleton".equals(keyString)) {
                           skeleton = value.getString();
                        } else {
                           assert false : "key must be unit, geq or skeleton";
                        }
                     }

                     assert unit != null;

                     unitPreferences.add(new UnitPreference(unit, geq, skeleton));
                  }

                  assert !unitPreferences.isEmpty();

                  this.insertUnitPreferences(category, usage, region, (UnitPreference[])unitPreferences.toArray(new UnitPreference[0]));
               }
            }
         }

      }

      private void insertUnitPreferences(String category, String usage, String region, UnitPreference[] unitPreferences) {
         String key = UnitPreferences.formMapKey(category, usage);
         HashMap<String, UnitPreference[]> shouldInsert;
         if (this.mapToUnitPreferences.containsKey(key)) {
            shouldInsert = (HashMap)this.mapToUnitPreferences.get(key);
         } else {
            shouldInsert = new HashMap();
            this.mapToUnitPreferences.put(key, shouldInsert);
         }

         shouldInsert.put(region, unitPreferences);
      }
   }
}
