package shaded.parquet.com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.core.SerializableString;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.EnumNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.EnumFeature;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.MapperConfig;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedClass;

public final class EnumValues implements Serializable {
   private static final long serialVersionUID = 1L;
   private final Class _enumClass;
   private final Enum[] _values;
   private final SerializableString[] _textual;
   private transient EnumMap _asMap;

   private EnumValues(Class enumClass, SerializableString[] textual) {
      this._enumClass = enumClass;
      this._values = (Enum[])enumClass.getEnumConstants();
      this._textual = textual;
   }

   public static EnumValues construct(SerializationConfig config, AnnotatedClass annotatedClass) {
      return config.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING) ? constructFromToString(config, (AnnotatedClass)annotatedClass) : constructFromName(config, (AnnotatedClass)annotatedClass);
   }

   /** @deprecated */
   @Deprecated
   public static EnumValues constructFromName(MapperConfig config, Class enumClass) {
      Class<? extends Enum<?>> enumCls = ClassUtil.findEnumType(enumClass);
      Enum<?>[] enumValues = (Enum[])enumCls.getEnumConstants();
      if (enumValues == null) {
         throw new IllegalArgumentException("Cannot determine enum constants for Class " + enumClass.getName());
      } else {
         String[] names = config.getAnnotationIntrospector().findEnumValues(enumCls, enumValues, new String[enumValues.length]);
         SerializableString[] textual = new SerializableString[enumValues.length];
         int i = 0;

         for(int len = enumValues.length; i < len; ++i) {
            Enum<?> en = enumValues[i];
            String name = names[i];
            if (name == null) {
               name = en.name();
            }

            if (config.isEnabled((DatatypeFeature)EnumFeature.WRITE_ENUMS_TO_LOWERCASE)) {
               name = name.toLowerCase();
            }

            textual[en.ordinal()] = config.compileString(name);
         }

         return construct(enumClass, textual);
      }
   }

   public static EnumValues constructFromName(MapperConfig config, AnnotatedClass annotatedClass) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean useLowerCase = config.isEnabled((DatatypeFeature)EnumFeature.WRITE_ENUMS_TO_LOWERCASE);
      Class<?> enumCls0 = annotatedClass.getRawType();
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      String[] names = ai.findEnumValues(config, annotatedClass, enumConstants, new String[enumConstants.length]);
      SerializableString[] textual = new SerializableString[enumConstants.length];
      int i = 0;

      for(int len = enumConstants.length; i < len; ++i) {
         Enum<?> enumValue = enumConstants[i];
         String name = names[i];
         if (name == null) {
            name = enumValue.name();
         }

         if (useLowerCase) {
            name = name.toLowerCase();
         }

         textual[enumValue.ordinal()] = config.compileString(name);
      }

      return construct(enumCls, textual);
   }

   public static EnumValues constructFromToString(MapperConfig config, AnnotatedClass annotatedClass) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean useLowerCase = config.isEnabled((DatatypeFeature)EnumFeature.WRITE_ENUMS_TO_LOWERCASE);
      Class<?> enumCls0 = annotatedClass.getRawType();
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      String[] names = new String[enumConstants.length];
      if (ai != null) {
         ai.findEnumValues(config, annotatedClass, enumConstants, names);
      }

      SerializableString[] textual = new SerializableString[enumConstants.length];

      for(int i = 0; i < enumConstants.length; ++i) {
         String name = names[i];
         if (name == null) {
            Enum<?> en = enumConstants[i];
            name = en.toString();
            if (name == null) {
               name = "";
            }
         }

         if (useLowerCase) {
            name = name.toLowerCase();
         }

         textual[i] = config.compileString(name);
      }

      return construct(enumCls, textual);
   }

   /** @deprecated */
   @Deprecated
   public static EnumValues constructFromToString(MapperConfig config, Class enumClass) {
      Class<? extends Enum<?>> cls = ClassUtil.findEnumType(enumClass);
      Enum<?>[] values = (Enum[])cls.getEnumConstants();
      if (values == null) {
         throw new IllegalArgumentException("Cannot determine enum constants for Class " + enumClass.getName());
      } else {
         ArrayList<String> external = new ArrayList(values.length);

         for(Enum en : values) {
            external.add(en.toString());
         }

         return construct(config, enumClass, external);
      }
   }

   public static EnumValues constructUsingEnumNamingStrategy(MapperConfig config, AnnotatedClass annotatedClass, EnumNamingStrategy namingStrategy) {
      AnnotationIntrospector ai = config.getAnnotationIntrospector();
      boolean useLowerCase = config.isEnabled((DatatypeFeature)EnumFeature.WRITE_ENUMS_TO_LOWERCASE);
      Class<?> enumCls0 = annotatedClass.getRawType();
      Class<Enum<?>> enumCls = _enumClass(enumCls0);
      Enum<?>[] enumConstants = _enumConstants(enumCls0);
      String[] names = new String[enumConstants.length];
      if (ai != null) {
         ai.findEnumValues(config, annotatedClass, enumConstants, names);
      }

      SerializableString[] textual = new SerializableString[enumConstants.length];
      int i = 0;

      for(int len = enumConstants.length; i < len; ++i) {
         Enum<?> enumValue = enumConstants[i];
         String name = names[i];
         if (name == null) {
            name = namingStrategy.convertEnumToExternalName(enumValue.name());
         }

         if (useLowerCase) {
            name = name.toLowerCase();
         }

         textual[i] = config.compileString(name);
      }

      return construct(enumCls, textual);
   }

   /** @deprecated */
   @Deprecated
   public static EnumValues constructUsingEnumNamingStrategy(MapperConfig config, Class enumClass, EnumNamingStrategy namingStrategy) {
      Class<? extends Enum<?>> cls = ClassUtil.findEnumType(enumClass);
      Enum<?>[] values = (Enum[])cls.getEnumConstants();
      if (values == null) {
         throw new IllegalArgumentException("Cannot determine enum constants for Class " + enumClass.getName());
      } else {
         ArrayList<String> external = new ArrayList(values.length);

         for(Enum en : values) {
            external.add(namingStrategy.convertEnumToExternalName(en.name()));
         }

         return construct(config, enumClass, external);
      }
   }

   public static EnumValues construct(MapperConfig config, Class enumClass, List externalValues) {
      int len = externalValues.size();
      SerializableString[] textual = new SerializableString[len];

      for(int i = 0; i < len; ++i) {
         textual[i] = config.compileString((String)externalValues.get(i));
      }

      return construct(enumClass, textual);
   }

   public static EnumValues construct(Class enumClass, SerializableString[] externalValues) {
      return new EnumValues(enumClass, externalValues);
   }

   protected static Class _enumClass(Class enumCls0) {
      return enumCls0;
   }

   protected static Enum[] _enumConstants(Class enumCls) {
      Enum<?>[] enumValues = (Enum[])ClassUtil.findEnumType(enumCls).getEnumConstants();
      if (enumValues == null) {
         throw new IllegalArgumentException("No enum constants for class " + enumCls.getName());
      } else {
         return enumValues;
      }
   }

   public SerializableString serializedValueFor(Enum key) {
      return this._textual[key.ordinal()];
   }

   public Collection values() {
      return Arrays.asList(this._textual);
   }

   public List enums() {
      return Arrays.asList(this._values);
   }

   public EnumMap internalMap() {
      EnumMap<?, SerializableString> result = this._asMap;
      if (result == null) {
         Map<Enum<?>, SerializableString> map = new LinkedHashMap();

         for(Enum en : this._values) {
            map.put(en, this._textual[en.ordinal()]);
         }

         this._asMap = result = new EnumMap(map);
      }

      return result;
   }

   public Class getEnumClass() {
      return this._enumClass;
   }
}
