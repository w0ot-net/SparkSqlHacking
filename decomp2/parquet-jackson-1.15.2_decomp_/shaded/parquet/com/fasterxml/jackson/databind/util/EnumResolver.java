package shaded.parquet.com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.EnumNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedMember;

public class EnumResolver implements Serializable {
   private static final long serialVersionUID = 1L;
   protected final Class _enumClass;
   protected final Enum[] _enums;
   protected final HashMap _enumsById;
   protected final Enum _defaultValue;
   protected final boolean _isIgnoreCase;
   protected final boolean _isFromIntValue;

   protected EnumResolver(Class enumClass, Enum[] enums, HashMap map, Enum defaultValue, boolean isIgnoreCase, boolean isFromIntValue) {
      this._enumClass = enumClass;
      this._enums = enums;
      this._enumsById = map;
      this._defaultValue = defaultValue;
      this._isIgnoreCase = isIgnoreCase;
      this._isFromIntValue = isFromIntValue;
   }

   public static EnumResolver constructFor(DeserializationConfig config, AnnotatedClass annotatedClass) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<?> enumCls0 = annotatedClass.getRawType();
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      String[] names = ai.findEnumValues(config, annotatedClass, enumConstants, new String[enumConstants.length]);
      String[][] allAliases = new String[names.length][];
      ai.findEnumAliases(config, annotatedClass, enumConstants, allAliases);
      HashMap<String, Enum<?>> map = new HashMap();
      int i = 0;

      for(int len = enumConstants.length; i < len; ++i) {
         Enum<?> enumValue = enumConstants[i];
         String name = names[i];
         if (name == null) {
            name = enumValue.name();
         }

         map.put(name, enumValue);
         String[] aliases = allAliases[i];
         if (aliases != null) {
            for(String alias : aliases) {
               map.putIfAbsent(alias, enumValue);
            }
         }
      }

      return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, annotatedClass, enumConstants), isIgnoreCase, false);
   }

   /** @deprecated */
   @Deprecated
   public static EnumResolver constructFor(DeserializationConfig config, Class enumCls0) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      String[] names = ai.findEnumValues(enumCls, enumConstants, new String[enumConstants.length]);
      String[][] allAliases = new String[names.length][];
      ai.findEnumAliases(enumCls, enumConstants, allAliases);
      HashMap<String, Enum<?>> map = new HashMap();
      int i = 0;

      for(int len = enumConstants.length; i < len; ++i) {
         Enum<?> enumValue = enumConstants[i];
         String name = names[i];
         if (name == null) {
            name = enumValue.name();
         }

         map.put(name, enumValue);
         String[] aliases = allAliases[i];
         if (aliases != null) {
            for(String alias : aliases) {
               map.putIfAbsent(alias, enumValue);
            }
         }
      }

      return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, enumCls), isIgnoreCase, false);
   }

   public static EnumResolver constructUsingToString(DeserializationConfig config, AnnotatedClass annotatedClass) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<?> enumCls0 = annotatedClass.getRawType();
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      String[] names = new String[enumConstants.length];
      String[][] allAliases = new String[enumConstants.length][];
      if (ai != null) {
         ai.findEnumValues(config, annotatedClass, enumConstants, names);
         ai.findEnumAliases(config, annotatedClass, enumConstants, allAliases);
      }

      HashMap<String, Enum<?>> map = new HashMap();
      int i = enumConstants.length;

      while(true) {
         --i;
         if (i < 0) {
            return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, annotatedClass, enumConstants), isIgnoreCase, false);
         }

         Enum<?> enumValue = enumConstants[i];
         String name = names[i];
         if (name == null) {
            name = enumValue.toString();
         }

         map.put(name, enumValue);
         String[] aliases = allAliases[i];
         if (aliases != null) {
            for(String alias : aliases) {
               map.putIfAbsent(alias, enumValue);
            }
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static EnumResolver constructUsingToString(DeserializationConfig config, Class enumCls0) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      HashMap<String, Enum<?>> map = new HashMap();
      String[][] allAliases = new String[enumConstants.length][];
      if (ai != null) {
         ai.findEnumAliases(enumCls, enumConstants, allAliases);
      }

      int i = enumConstants.length;

      while(true) {
         --i;
         if (i < 0) {
            return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, enumCls), isIgnoreCase, false);
         }

         Enum<?> enumValue = enumConstants[i];
         map.put(enumValue.toString(), enumValue);
         String[] aliases = allAliases[i];
         if (aliases != null) {
            for(String alias : aliases) {
               map.putIfAbsent(alias, enumValue);
            }
         }
      }
   }

   public static EnumResolver constructUsingIndex(DeserializationConfig config, AnnotatedClass annotatedClass) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<?> enumCls0 = annotatedClass.getRawType();
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      HashMap<String, Enum<?>> map = new HashMap();
      int i = enumConstants.length;

      while(true) {
         --i;
         if (i < 0) {
            return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, annotatedClass, enumConstants), isIgnoreCase, false);
         }

         Enum<?> enumValue = enumConstants[i];
         map.put(String.valueOf(i), enumValue);
      }
   }

   /** @deprecated */
   @Deprecated
   public static EnumResolver constructUsingIndex(DeserializationConfig config, Class enumCls0) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      HashMap<String, Enum<?>> map = new HashMap();
      int i = enumConstants.length;

      while(true) {
         --i;
         if (i < 0) {
            return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, enumCls), isIgnoreCase, false);
         }

         Enum<?> enumValue = enumConstants[i];
         map.put(String.valueOf(i), enumValue);
      }
   }

   /** @deprecated */
   @Deprecated
   public static EnumResolver constructUsingEnumNamingStrategy(DeserializationConfig config, Class enumCls0, EnumNamingStrategy enumNamingStrategy) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      HashMap<String, Enum<?>> map = new HashMap();
      String[] names = new String[enumConstants.length];
      String[][] allAliases = new String[enumConstants.length][];
      if (ai != null) {
         ai.findEnumValues(enumCls, enumConstants, names);
         ai.findEnumAliases(enumCls, enumConstants, allAliases);
      }

      int i = enumConstants.length;

      while(true) {
         --i;
         if (i < 0) {
            return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, enumCls), isIgnoreCase, false);
         }

         Enum<?> anEnum = enumConstants[i];
         String name = names[i];
         if (name == null) {
            name = enumNamingStrategy.convertEnumToExternalName(anEnum.name());
         }

         map.put(name, anEnum);
         String[] aliases = allAliases[i];
         if (aliases != null) {
            for(String alias : aliases) {
               map.putIfAbsent(alias, anEnum);
            }
         }
      }
   }

   public static EnumResolver constructUsingEnumNamingStrategy(DeserializationConfig config, AnnotatedClass annotatedClass, EnumNamingStrategy enumNamingStrategy) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<?> enumCls0 = annotatedClass.getRawType();
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      String[] names = new String[enumConstants.length];
      String[][] allAliases = new String[enumConstants.length][];
      if (ai != null) {
         ai.findEnumValues(config, annotatedClass, enumConstants, names);
         ai.findEnumAliases(config, annotatedClass, enumConstants, allAliases);
      }

      HashMap<String, Enum<?>> map = new HashMap();
      int i = enumConstants.length;

      while(true) {
         --i;
         if (i < 0) {
            return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, annotatedClass, enumConstants), isIgnoreCase, false);
         }

         Enum<?> anEnum = enumConstants[i];
         String name = names[i];
         if (name == null) {
            name = enumNamingStrategy.convertEnumToExternalName(anEnum.name());
         }

         map.put(name, anEnum);
         String[] aliases = allAliases[i];
         if (aliases != null) {
            for(String alias : aliases) {
               map.putIfAbsent(alias, anEnum);
            }
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static EnumResolver constructUsingMethod(DeserializationConfig config, Class enumCls0, AnnotatedMember accessor) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      HashMap<String, Enum<?>> map = new HashMap();
      int i = enumConstants.length;

      while(true) {
         --i;
         if (i < 0) {
            return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, enumCls), isIgnoreCase, _isIntType(accessor.getRawType()));
         }

         Enum<?> en = enumConstants[i];

         try {
            Object o = accessor.getValue(en);
            if (o != null) {
               map.put(o.toString(), en);
            }
         } catch (Exception e) {
            throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + en + ": " + e.getMessage());
         }
      }
   }

   public static EnumResolver constructUsingMethod(DeserializationConfig config, AnnotatedClass annotatedClass, AnnotatedMember accessor) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean isIgnoreCase = config.isEnabled((MapperFeature)MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      Class<?> enumCls0 = annotatedClass.getRawType();
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      HashMap<String, Enum<?>> map = new HashMap();
      int i = enumConstants.length;

      while(true) {
         --i;
         if (i < 0) {
            return new EnumResolver(enumCls, enumConstants, map, _enumDefault(ai, annotatedClass, enumConstants), isIgnoreCase, _isIntType(accessor.getRawType()));
         }

         Enum<?> en = enumConstants[i];

         try {
            Object o = accessor.getValue(en);
            if (o != null) {
               map.put(o.toString(), en);
            }
         } catch (Exception e) {
            throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + en + ": " + e.getMessage());
         }
      }
   }

   public CompactStringObjectMap constructLookup() {
      return CompactStringObjectMap.construct(this._enumsById);
   }

   protected static Class _enumClass(Class enumCls0) {
      return enumCls0;
   }

   protected static Enum[] _enumConstants(Class enumCls) {
      Enum<?>[] enumValues = (Enum[])_enumClass(enumCls).getEnumConstants();
      if (enumValues == null) {
         throw new IllegalArgumentException("No enum constants for class " + enumCls.getName());
      } else {
         return enumValues;
      }
   }

   protected static Enum _enumDefault(AnnotationIntrospector intr, AnnotatedClass annotatedClass, Enum[] enums) {
      return intr != null ? intr.findDefaultEnumValue(annotatedClass, enums) : null;
   }

   /** @deprecated */
   @Deprecated
   protected static Enum _enumDefault(AnnotationIntrospector intr, Class enumCls) {
      return intr != null ? intr.findDefaultEnumValue(_enumClass(enumCls)) : null;
   }

   protected static boolean _isIntType(Class erasedType) {
      if (erasedType.isPrimitive()) {
         erasedType = ClassUtil.wrapperType(erasedType);
      }

      return erasedType == Long.class || erasedType == Integer.class || erasedType == Short.class || erasedType == Byte.class;
   }

   public Enum findEnum(String key) {
      Enum<?> en = (Enum)this._enumsById.get(key);
      return en == null && this._isIgnoreCase ? this._findEnumCaseInsensitive(key) : en;
   }

   protected Enum _findEnumCaseInsensitive(String key) {
      for(Map.Entry entry : this._enumsById.entrySet()) {
         if (key.equalsIgnoreCase((String)entry.getKey())) {
            return (Enum)entry.getValue();
         }
      }

      return null;
   }

   public Enum getEnum(int index) {
      return index >= 0 && index < this._enums.length ? this._enums[index] : null;
   }

   public Enum getDefaultValue() {
      return this._defaultValue;
   }

   public Enum[] getRawEnums() {
      return this._enums;
   }

   public List getEnums() {
      ArrayList<Enum<?>> enums = new ArrayList(this._enums.length);

      for(Enum e : this._enums) {
         enums.add(e);
      }

      return enums;
   }

   public Collection getEnumIds() {
      return this._enumsById.keySet();
   }

   public Class getEnumClass() {
      return this._enumClass;
   }

   public int lastValidIndex() {
      return this._enums.length - 1;
   }

   public boolean isFromIntValue() {
      return this._isFromIntValue;
   }
}
