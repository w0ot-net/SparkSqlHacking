package com.ibm.icu.impl.locale;

import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.util.Output;
import com.ibm.icu.util.UResourceBundle;
import com.ibm.icu.util.UResourceBundleIterator;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.regex.Pattern;

public class KeyTypeData {
   static Set DEPRECATED_KEYS = Collections.emptySet();
   static Map VALUE_TYPES = Collections.emptyMap();
   static Map DEPRECATED_KEY_TYPES = Collections.emptyMap();
   private static final Object[][] KEY_DATA = new Object[0][];
   private static final Map KEYMAP = new HashMap();
   private static Map BCP47_KEYS;

   public static String toBcpKey(String key) {
      key = AsciiUtil.toLowerString(key);
      KeyData keyData = (KeyData)KEYMAP.get(key);
      return keyData != null ? keyData.bcpId : null;
   }

   public static String toLegacyKey(String key) {
      key = AsciiUtil.toLowerString(key);
      KeyData keyData = (KeyData)KEYMAP.get(key);
      return keyData != null ? keyData.legacyId : null;
   }

   public static String toBcpType(String key, String type, Output isKnownKey, Output isSpecialType) {
      if (isKnownKey != null) {
         isKnownKey.value = false;
      }

      if (isSpecialType != null) {
         isSpecialType.value = false;
      }

      key = AsciiUtil.toLowerString(key);
      type = AsciiUtil.toLowerString(type);
      KeyData keyData = (KeyData)KEYMAP.get(key);
      if (keyData != null) {
         if (isKnownKey != null) {
            isKnownKey.value = Boolean.TRUE;
         }

         Type t = (Type)keyData.typeMap.get(type);
         if (t != null) {
            return t.bcpId;
         }

         if (keyData.specialTypes != null) {
            for(SpecialType st : keyData.specialTypes) {
               if (st.handler.isWellFormed(type)) {
                  if (isSpecialType != null) {
                     isSpecialType.value = true;
                  }

                  return st.handler.canonicalize(type);
               }
            }
         }
      }

      return null;
   }

   public static String toLegacyType(String key, String type, Output isKnownKey, Output isSpecialType) {
      if (isKnownKey != null) {
         isKnownKey.value = false;
      }

      if (isSpecialType != null) {
         isSpecialType.value = false;
      }

      key = AsciiUtil.toLowerString(key);
      type = AsciiUtil.toLowerString(type);
      KeyData keyData = (KeyData)KEYMAP.get(key);
      if (keyData != null) {
         if (isKnownKey != null) {
            isKnownKey.value = Boolean.TRUE;
         }

         Type t = (Type)keyData.typeMap.get(type);
         if (t != null) {
            return t.legacyId;
         }

         if (keyData.specialTypes != null) {
            for(SpecialType st : keyData.specialTypes) {
               if (st.handler.isWellFormed(type)) {
                  if (isSpecialType != null) {
                     isSpecialType.value = true;
                  }

                  return st.handler.canonicalize(type);
               }
            }
         }
      }

      return null;
   }

   private static void initFromResourceBundle() {
      UResourceBundle keyTypeDataRes = ICUResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", "keyTypeData", ICUResourceBundle.ICU_DATA_CLASS_LOADER, ICUResourceBundle.OpenType.DIRECT);
      getKeyInfo(keyTypeDataRes.get("keyInfo"));
      getTypeInfo(keyTypeDataRes.get("typeInfo"));
      UResourceBundle keyMapRes = keyTypeDataRes.get("keyMap");
      UResourceBundle typeMapRes = keyTypeDataRes.get("typeMap");
      UResourceBundle typeAliasRes = null;
      UResourceBundle bcpTypeAliasRes = null;

      try {
         typeAliasRes = keyTypeDataRes.get("typeAlias");
      } catch (MissingResourceException var32) {
      }

      try {
         bcpTypeAliasRes = keyTypeDataRes.get("bcpTypeAlias");
      } catch (MissingResourceException var31) {
      }

      UResourceBundleIterator keyMapItr = keyMapRes.getIterator();
      Map<String, Set<String>> _Bcp47Keys = new LinkedHashMap();

      while(keyMapItr.hasNext()) {
         UResourceBundle keyMapEntry = keyMapItr.next();
         String legacyKeyId = keyMapEntry.getKey();
         String bcpKeyId = keyMapEntry.getString();
         boolean hasSameKey = false;
         if (bcpKeyId.length() == 0) {
            bcpKeyId = legacyKeyId;
            hasSameKey = true;
         }

         LinkedHashSet<String> _bcp47Types = new LinkedHashSet();
         _Bcp47Keys.put(bcpKeyId, Collections.unmodifiableSet(_bcp47Types));
         boolean isTZ = legacyKeyId.equals("timezone");
         Map<String, Set<String>> typeAliasMap = null;
         if (typeAliasRes != null) {
            UResourceBundle typeAliasResByKey = null;

            try {
               typeAliasResByKey = typeAliasRes.get(legacyKeyId);
            } catch (MissingResourceException var29) {
            }

            if (typeAliasResByKey != null) {
               typeAliasMap = new HashMap();

               String from;
               Set<String> aliasSet;
               for(UResourceBundleIterator typeAliasResItr = typeAliasResByKey.getIterator(); typeAliasResItr.hasNext(); aliasSet.add(from)) {
                  UResourceBundle typeAliasDataEntry = typeAliasResItr.next();
                  from = typeAliasDataEntry.getKey();
                  String to = typeAliasDataEntry.getString();
                  if (isTZ) {
                     from = from.replace(':', '/');
                  }

                  aliasSet = (Set)typeAliasMap.get(to);
                  if (aliasSet == null) {
                     aliasSet = new HashSet();
                     typeAliasMap.put(to, aliasSet);
                  }
               }
            }
         }

         Map<String, Set<String>> bcpTypeAliasMap = null;
         if (bcpTypeAliasRes != null) {
            UResourceBundle bcpTypeAliasResByKey = null;

            try {
               bcpTypeAliasResByKey = bcpTypeAliasRes.get(bcpKeyId);
            } catch (MissingResourceException var30) {
            }

            if (bcpTypeAliasResByKey != null) {
               bcpTypeAliasMap = new HashMap();

               Set<String> aliasSet;
               String from;
               for(UResourceBundleIterator bcpTypeAliasResItr = bcpTypeAliasResByKey.getIterator(); bcpTypeAliasResItr.hasNext(); aliasSet.add(from)) {
                  UResourceBundle bcpTypeAliasDataEntry = bcpTypeAliasResItr.next();
                  from = bcpTypeAliasDataEntry.getKey();
                  String to = bcpTypeAliasDataEntry.getString();
                  aliasSet = (Set)bcpTypeAliasMap.get(to);
                  if (aliasSet == null) {
                     aliasSet = new HashSet();
                     bcpTypeAliasMap.put(to, aliasSet);
                  }
               }
            }
         }

         Map<String, Type> typeDataMap = new HashMap();
         EnumSet<SpecialType> specialTypeSet = null;
         UResourceBundle typeMapResByKey = null;

         try {
            typeMapResByKey = typeMapRes.get(legacyKeyId);
         } catch (MissingResourceException var33) {
            assert false;
         }

         if (typeMapResByKey != null) {
            UResourceBundleIterator typeMapResByKeyItr = typeMapResByKey.getIterator();

            while(typeMapResByKeyItr.hasNext()) {
               UResourceBundle typeMapEntry = typeMapResByKeyItr.next();
               String legacyTypeId = typeMapEntry.getKey();
               String bcpTypeId = typeMapEntry.getString();
               char first = legacyTypeId.charAt(0);
               boolean isSpecialType = '9' < first && first < 'a' && bcpTypeId.length() == 0;
               if (isSpecialType) {
                  if (specialTypeSet == null) {
                     specialTypeSet = EnumSet.noneOf(SpecialType.class);
                  }

                  specialTypeSet.add(KeyTypeData.SpecialType.valueOf(legacyTypeId));
                  _bcp47Types.add(legacyTypeId);
               } else {
                  if (isTZ) {
                     legacyTypeId = legacyTypeId.replace(':', '/');
                  }

                  boolean hasSameType = false;
                  if (bcpTypeId.length() == 0) {
                     bcpTypeId = legacyTypeId;
                     hasSameType = true;
                  }

                  _bcp47Types.add(bcpTypeId);
                  Type t = new Type(legacyTypeId, bcpTypeId);
                  typeDataMap.put(AsciiUtil.toLowerString(legacyTypeId), t);
                  if (!hasSameType) {
                     typeDataMap.put(AsciiUtil.toLowerString(bcpTypeId), t);
                  }

                  if (typeAliasMap != null) {
                     Set<String> typeAliasSet = (Set)typeAliasMap.get(legacyTypeId);
                     if (typeAliasSet != null) {
                        for(String alias : typeAliasSet) {
                           typeDataMap.put(AsciiUtil.toLowerString(alias), t);
                        }
                     }
                  }

                  if (bcpTypeAliasMap != null) {
                     Set<String> bcpTypeAliasSet = (Set)bcpTypeAliasMap.get(bcpTypeId);
                     if (bcpTypeAliasSet != null) {
                        for(String alias : bcpTypeAliasSet) {
                           typeDataMap.put(AsciiUtil.toLowerString(alias), t);
                        }
                     }
                  }
               }
            }
         }

         KeyData keyData = new KeyData(legacyKeyId, bcpKeyId, typeDataMap, specialTypeSet);
         KEYMAP.put(AsciiUtil.toLowerString(legacyKeyId), keyData);
         if (!hasSameKey) {
            KEYMAP.put(AsciiUtil.toLowerString(bcpKeyId), keyData);
         }
      }

      BCP47_KEYS = Collections.unmodifiableMap(_Bcp47Keys);
   }

   private static void getKeyInfo(UResourceBundle keyInfoRes) {
      Set<String> _deprecatedKeys = new LinkedHashSet();
      Map<String, ValueType> _valueTypes = new LinkedHashMap();
      UResourceBundleIterator keyInfoIt = keyInfoRes.getIterator();

      while(keyInfoIt.hasNext()) {
         UResourceBundle keyInfoEntry = keyInfoIt.next();
         String key = keyInfoEntry.getKey();
         KeyInfoType keyInfo = KeyTypeData.KeyInfoType.valueOf(key);
         UResourceBundleIterator keyInfoIt2 = keyInfoEntry.getIterator();

         while(keyInfoIt2.hasNext()) {
            UResourceBundle keyInfoEntry2 = keyInfoIt2.next();
            String key2 = keyInfoEntry2.getKey();
            String value2 = keyInfoEntry2.getString();
            switch (keyInfo) {
               case deprecated:
                  _deprecatedKeys.add(key2);
                  break;
               case valueType:
                  _valueTypes.put(key2, KeyTypeData.ValueType.valueOf(value2));
            }
         }
      }

      DEPRECATED_KEYS = Collections.unmodifiableSet(_deprecatedKeys);
      VALUE_TYPES = Collections.unmodifiableMap(_valueTypes);
   }

   private static void getTypeInfo(UResourceBundle typeInfoRes) {
      Map<String, Set<String>> _deprecatedKeyTypes = new LinkedHashMap();
      UResourceBundleIterator keyInfoIt = typeInfoRes.getIterator();

      while(keyInfoIt.hasNext()) {
         UResourceBundle keyInfoEntry = keyInfoIt.next();
         String key = keyInfoEntry.getKey();
         TypeInfoType typeInfo = KeyTypeData.TypeInfoType.valueOf(key);
         UResourceBundleIterator keyInfoIt2 = keyInfoEntry.getIterator();

         while(keyInfoIt2.hasNext()) {
            UResourceBundle keyInfoEntry2 = keyInfoIt2.next();
            String key2 = keyInfoEntry2.getKey();
            Set<String> _deprecatedTypes = new LinkedHashSet();
            UResourceBundleIterator keyInfoIt3 = keyInfoEntry2.getIterator();

            while(keyInfoIt3.hasNext()) {
               UResourceBundle keyInfoEntry3 = keyInfoIt3.next();
               String key3 = keyInfoEntry3.getKey();
               switch (typeInfo) {
                  case deprecated:
                     _deprecatedTypes.add(key3);
               }
            }

            _deprecatedKeyTypes.put(key2, Collections.unmodifiableSet(_deprecatedTypes));
         }
      }

      DEPRECATED_KEY_TYPES = Collections.unmodifiableMap(_deprecatedKeyTypes);
   }

   private static void initFromTables() {
      for(Object[] keyDataEntry : KEY_DATA) {
         String legacyKeyId = (String)keyDataEntry[0];
         String bcpKeyId = (String)keyDataEntry[1];
         String[][] typeData = (String[][])keyDataEntry[2];
         String[][] typeAliasData = (String[][])keyDataEntry[3];
         String[][] bcpTypeAliasData = (String[][])keyDataEntry[4];
         boolean hasSameKey = false;
         if (bcpKeyId == null) {
            bcpKeyId = legacyKeyId;
            hasSameKey = true;
         }

         Map<String, Set<String>> typeAliasMap = null;
         if (typeAliasData != null) {
            typeAliasMap = new HashMap();

            for(String[] typeAliasDataEntry : typeAliasData) {
               String from = typeAliasDataEntry[0];
               String to = typeAliasDataEntry[1];
               Set<String> aliasSet = (Set)typeAliasMap.get(to);
               if (aliasSet == null) {
                  aliasSet = new HashSet();
                  typeAliasMap.put(to, aliasSet);
               }

               aliasSet.add(from);
            }
         }

         Map<String, Set<String>> bcpTypeAliasMap = null;
         if (bcpTypeAliasData != null) {
            bcpTypeAliasMap = new HashMap();

            for(String[] bcpTypeAliasDataEntry : bcpTypeAliasData) {
               String from = bcpTypeAliasDataEntry[0];
               String to = bcpTypeAliasDataEntry[1];
               Set<String> aliasSet = (Set)bcpTypeAliasMap.get(to);
               if (aliasSet == null) {
                  aliasSet = new HashSet();
                  bcpTypeAliasMap.put(to, aliasSet);
               }

               aliasSet.add(from);
            }
         }

         assert typeData != null;

         Map<String, Type> typeDataMap = new HashMap();
         Set<SpecialType> specialTypeSet = null;

         for(String[] typeDataEntry : typeData) {
            String legacyTypeId = typeDataEntry[0];
            String bcpTypeId = typeDataEntry[1];
            boolean isSpecialType = false;

            for(SpecialType st : KeyTypeData.SpecialType.values()) {
               if (legacyTypeId.equals(st.toString())) {
                  isSpecialType = true;
                  if (specialTypeSet == null) {
                     specialTypeSet = new HashSet();
                  }

                  specialTypeSet.add(st);
                  break;
               }
            }

            if (!isSpecialType) {
               boolean hasSameType = false;
               if (bcpTypeId == null) {
                  bcpTypeId = legacyTypeId;
                  hasSameType = true;
               }

               Type t = new Type(legacyTypeId, bcpTypeId);
               typeDataMap.put(AsciiUtil.toLowerString(legacyTypeId), t);
               if (!hasSameType) {
                  typeDataMap.put(AsciiUtil.toLowerString(bcpTypeId), t);
               }

               Set<String> typeAliasSet = (Set)typeAliasMap.get(legacyTypeId);
               if (typeAliasSet != null) {
                  for(String alias : typeAliasSet) {
                     typeDataMap.put(AsciiUtil.toLowerString(alias), t);
                  }
               }

               Set<String> bcpTypeAliasSet = (Set)bcpTypeAliasMap.get(bcpTypeId);
               if (bcpTypeAliasSet != null) {
                  for(String alias : bcpTypeAliasSet) {
                     typeDataMap.put(AsciiUtil.toLowerString(alias), t);
                  }
               }
            }
         }

         EnumSet<SpecialType> specialTypes = null;
         if (specialTypeSet != null) {
            specialTypes = EnumSet.copyOf(specialTypeSet);
         }

         KeyData keyData = new KeyData(legacyKeyId, bcpKeyId, typeDataMap, specialTypes);
         KEYMAP.put(AsciiUtil.toLowerString(legacyKeyId), keyData);
         if (!hasSameKey) {
            KEYMAP.put(AsciiUtil.toLowerString(bcpKeyId), keyData);
         }
      }

   }

   public static Set getBcp47Keys() {
      return BCP47_KEYS.keySet();
   }

   public static Set getBcp47KeyTypes(String key) {
      return (Set)BCP47_KEYS.get(key);
   }

   public static boolean isDeprecated(String key) {
      return DEPRECATED_KEYS.contains(key);
   }

   public static boolean isDeprecated(String key, String type) {
      Set<String> deprecatedTypes = (Set)DEPRECATED_KEY_TYPES.get(key);
      return deprecatedTypes == null ? false : deprecatedTypes.contains(type);
   }

   public static ValueType getValueType(String key) {
      ValueType type = (ValueType)VALUE_TYPES.get(key);
      return type == null ? KeyTypeData.ValueType.single : type;
   }

   static {
      initFromResourceBundle();
   }

   public static enum ValueType {
      single,
      multiple,
      incremental,
      any;
   }

   private abstract static class SpecialTypeHandler {
      private SpecialTypeHandler() {
      }

      abstract boolean isWellFormed(String var1);

      String canonicalize(String value) {
         return AsciiUtil.toLowerString(value);
      }
   }

   private static class CodepointsTypeHandler extends SpecialTypeHandler {
      private static final Pattern pat = Pattern.compile("[0-9a-fA-F]{4,6}(-[0-9a-fA-F]{4,6})*");

      private CodepointsTypeHandler() {
      }

      boolean isWellFormed(String value) {
         return pat.matcher(value).matches();
      }
   }

   private static class ReorderCodeTypeHandler extends SpecialTypeHandler {
      private static final Pattern pat = Pattern.compile("[a-zA-Z]{3,8}(-[a-zA-Z]{3,8})*");

      private ReorderCodeTypeHandler() {
      }

      boolean isWellFormed(String value) {
         return pat.matcher(value).matches();
      }
   }

   private static class RgKeyValueTypeHandler extends SpecialTypeHandler {
      private static final Pattern pat = Pattern.compile("([a-zA-Z]{2}|[0-9]{3})[zZ]{4}");

      private RgKeyValueTypeHandler() {
      }

      boolean isWellFormed(String value) {
         return pat.matcher(value).matches();
      }
   }

   private static class ScriptCodeTypeHandler extends SpecialTypeHandler {
      private static final Pattern pat = Pattern.compile("[a-zA-Z]{4}(-[a-zA-Z]{4})*");

      private ScriptCodeTypeHandler() {
      }

      boolean isWellFormed(String value) {
         return pat.matcher(value).matches();
      }
   }

   private static class SubdivisionKeyValueTypeHandler extends SpecialTypeHandler {
      private static final Pattern pat = Pattern.compile("([a-zA-Z]{2}|[0-9]{3})");

      private SubdivisionKeyValueTypeHandler() {
      }

      boolean isWellFormed(String value) {
         return pat.matcher(value).matches();
      }
   }

   private static class PrivateUseKeyValueTypeHandler extends SpecialTypeHandler {
      private static final Pattern pat = Pattern.compile("[a-zA-Z0-9]{3,8}(-[a-zA-Z0-9]{3,8})*");

      private PrivateUseKeyValueTypeHandler() {
      }

      boolean isWellFormed(String value) {
         return pat.matcher(value).matches();
      }
   }

   private static enum SpecialType {
      CODEPOINTS(new CodepointsTypeHandler()),
      REORDER_CODE(new ReorderCodeTypeHandler()),
      RG_KEY_VALUE(new RgKeyValueTypeHandler()),
      SCRIPT_CODE(new ScriptCodeTypeHandler()),
      SUBDIVISION_CODE(new SubdivisionKeyValueTypeHandler()),
      PRIVATE_USE(new PrivateUseKeyValueTypeHandler());

      SpecialTypeHandler handler;

      private SpecialType(SpecialTypeHandler handler) {
         this.handler = handler;
      }
   }

   private static class KeyData {
      String legacyId;
      String bcpId;
      Map typeMap;
      EnumSet specialTypes;

      KeyData(String legacyId, String bcpId, Map typeMap, EnumSet specialTypes) {
         this.legacyId = legacyId;
         this.bcpId = bcpId;
         this.typeMap = typeMap;
         this.specialTypes = specialTypes;
      }
   }

   private static class Type {
      String legacyId;
      String bcpId;

      Type(String legacyId, String bcpId) {
         this.legacyId = legacyId;
         this.bcpId = bcpId;
      }
   }

   private static enum KeyInfoType {
      deprecated,
      valueType;
   }

   private static enum TypeInfoType {
      deprecated;
   }
}
