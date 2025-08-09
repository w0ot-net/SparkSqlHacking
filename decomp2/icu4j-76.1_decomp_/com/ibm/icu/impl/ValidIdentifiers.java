package com.ibm.icu.impl;

import com.ibm.icu.impl.locale.AsciiUtil;
import com.ibm.icu.util.UResourceBundle;
import com.ibm.icu.util.UResourceBundleIterator;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidIdentifiers {
   public static Map getData() {
      return ValidIdentifiers.ValidityData.data;
   }

   public static Datasubtype isValid(Datatype datatype, Set datasubtypes, String code) {
      Map<Datasubtype, ValiditySet> subtable = (Map)ValidIdentifiers.ValidityData.data.get(datatype);
      if (subtable != null) {
         for(Datasubtype datasubtype : datasubtypes) {
            ValiditySet validitySet = (ValiditySet)subtable.get(datasubtype);
            if (validitySet != null && validitySet.contains(AsciiUtil.toLowerString(code))) {
               return datasubtype;
            }
         }
      }

      return null;
   }

   public static Datasubtype isValid(Datatype datatype, Set datasubtypes, String code, String value) {
      Map<Datasubtype, ValiditySet> subtable = (Map)ValidIdentifiers.ValidityData.data.get(datatype);
      if (subtable != null) {
         code = AsciiUtil.toLowerString(code);
         value = AsciiUtil.toLowerString(value);

         for(Datasubtype datasubtype : datasubtypes) {
            ValiditySet validitySet = (ValiditySet)subtable.get(datasubtype);
            if (validitySet != null && validitySet.contains(code, value)) {
               return datasubtype;
            }
         }
      }

      return null;
   }

   public static enum Datatype {
      currency,
      language,
      region,
      script,
      subdivision,
      unit,
      variant,
      u,
      t,
      x,
      illegal;
   }

   public static enum Datasubtype {
      deprecated,
      private_use,
      regular,
      special,
      unknown,
      macroregion,
      reserved;
   }

   public static class ValiditySet {
      public final Set regularData;
      public final Map subdivisionData;

      public ValiditySet(Set plainData, boolean makeMap) {
         if (makeMap) {
            HashMap<String, Set<String>> _subdivisionData = new HashMap();

            for(String s : plainData) {
               int pos = s.indexOf(45);
               int pos2 = pos + 1;
               if (pos < 0) {
                  pos2 = pos = s.charAt(0) < 'A' ? 3 : 2;
               }

               String key = s.substring(0, pos);
               String subdivision = s.substring(pos2);
               Set<String> oldSet = (Set)_subdivisionData.get(key);
               if (oldSet == null) {
                  _subdivisionData.put(key, oldSet = new HashSet());
               }

               oldSet.add(subdivision);
            }

            this.regularData = null;
            HashMap<String, Set<String>> _subdivisionData2 = new HashMap();

            for(Map.Entry e : _subdivisionData.entrySet()) {
               Set<String> value = (Set)e.getValue();
               Set<String> set = value.size() == 1 ? Collections.singleton(value.iterator().next()) : Collections.unmodifiableSet(value);
               _subdivisionData2.put(e.getKey(), set);
            }

            this.subdivisionData = Collections.unmodifiableMap(_subdivisionData2);
         } else {
            this.regularData = Collections.unmodifiableSet(plainData);
            this.subdivisionData = null;
         }

      }

      public boolean contains(String code) {
         if (this.regularData != null) {
            return this.regularData.contains(code);
         } else {
            int pos = code.indexOf(45);
            String key = code.substring(0, pos);
            String value = code.substring(pos + 1);
            return this.contains(key, value);
         }
      }

      public boolean contains(String key, String value) {
         Set<String> oldSet = (Set)this.subdivisionData.get(key);
         return oldSet != null && oldSet.contains(value);
      }

      public String toString() {
         return this.regularData != null ? this.regularData.toString() : this.subdivisionData.toString();
      }
   }

   private static class ValidityData {
      static final Map data;

      private static void addRange(String string, Set subvalues) {
         string = AsciiUtil.toLowerString(string);
         int pos = string.indexOf(126);
         if (pos < 0) {
            subvalues.add(string);
         } else {
            StringRange.expand(string.substring(0, pos), string.substring(pos + 1), false, subvalues);
         }

      }

      static {
         Map<Datatype, Map<Datasubtype, ValiditySet>> _data = new EnumMap(Datatype.class);
         UResourceBundle suppData = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
         UResourceBundle validityInfo = suppData.get("idValidity");
         UResourceBundleIterator datatypeIterator = validityInfo.getIterator();

         while(datatypeIterator.hasNext()) {
            UResourceBundle datatype = datatypeIterator.next();
            String rawKey = datatype.getKey();
            Datatype key = ValidIdentifiers.Datatype.valueOf(rawKey);
            Map<Datasubtype, ValiditySet> values = new EnumMap(Datasubtype.class);

            Datasubtype subkey;
            Set<String> subvalues;
            for(UResourceBundleIterator datasubtypeIterator = datatype.getIterator(); datasubtypeIterator.hasNext(); values.put(subkey, new ValiditySet(subvalues, key == ValidIdentifiers.Datatype.subdivision))) {
               UResourceBundle datasubtype = datasubtypeIterator.next();
               String rawsubkey = datasubtype.getKey();
               subkey = ValidIdentifiers.Datasubtype.valueOf(rawsubkey);
               subvalues = new HashSet();
               if (datasubtype.getType() == 0) {
                  addRange(datasubtype.getString(), subvalues);
               } else {
                  for(String string : datasubtype.getStringArray()) {
                     addRange(string, subvalues);
                  }
               }
            }

            _data.put(key, Collections.unmodifiableMap(values));
         }

         data = Collections.unmodifiableMap(_data);
      }
   }
}
