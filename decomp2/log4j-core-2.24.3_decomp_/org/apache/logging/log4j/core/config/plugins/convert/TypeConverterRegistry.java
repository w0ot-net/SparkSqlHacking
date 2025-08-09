package org.apache.logging.log4j.core.config.plugins.convert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UnknownFormatConversionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.PluginManager;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.core.util.ReflectionUtil;
import org.apache.logging.log4j.core.util.TypeUtil;
import org.apache.logging.log4j.status.StatusLogger;

public class TypeConverterRegistry {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static volatile TypeConverterRegistry INSTANCE;
   private static final Object INSTANCE_LOCK = new Object();
   private final ConcurrentMap registry = new ConcurrentHashMap();

   public static TypeConverterRegistry getInstance() {
      TypeConverterRegistry result = INSTANCE;
      if (result == null) {
         synchronized(INSTANCE_LOCK) {
            result = INSTANCE;
            if (result == null) {
               INSTANCE = result = new TypeConverterRegistry();
            }
         }
      }

      return result;
   }

   public TypeConverter findCompatibleConverter(final Type type) {
      Objects.requireNonNull(type, "No type was provided");
      TypeConverter<?> primary = (TypeConverter)this.registry.get(type);
      if (primary != null) {
         return primary;
      } else {
         if (type instanceof Class) {
            Class<?> clazz = (Class)type;
            if (clazz.isEnum()) {
               EnumConverter<? extends Enum> converter = new EnumConverter(clazz.asSubclass(Enum.class));
               synchronized(INSTANCE_LOCK) {
                  return this.registerConverter(type, converter);
               }
            }
         }

         for(Map.Entry entry : this.registry.entrySet()) {
            Type key = (Type)entry.getKey();
            if (TypeUtil.isAssignable(type, key)) {
               LOGGER.debug("Found compatible TypeConverter<{}> for type [{}].", key, type);
               TypeConverter<?> value = (TypeConverter)entry.getValue();
               synchronized(INSTANCE_LOCK) {
                  return this.registerConverter(type, value);
               }
            }
         }

         throw new UnknownFormatConversionException(type.toString());
      }
   }

   private TypeConverterRegistry() {
      LOGGER.trace("TypeConverterRegistry initializing.");
      PluginManager manager = new PluginManager("TypeConverter");
      manager.collectPlugins();
      this.loadKnownTypeConverters(manager.getPlugins().values());
      this.registerPrimitiveTypes();
   }

   private void loadKnownTypeConverters(final Collection knownTypes) {
      for(PluginType knownType : knownTypes) {
         Class<?> clazz = knownType.getPluginClass();
         if (TypeConverter.class.isAssignableFrom(clazz)) {
            Class<? extends TypeConverter> pluginClass = clazz.asSubclass(TypeConverter.class);
            Type conversionType = getTypeConverterSupportedType(pluginClass);
            TypeConverter<?> converter = (TypeConverter)ReflectionUtil.instantiate(pluginClass);
            this.registerConverter(conversionType, converter);
         }
      }

   }

   private TypeConverter registerConverter(final Type conversionType, final TypeConverter converter) {
      TypeConverter<?> conflictingConverter = (TypeConverter)this.registry.get(conversionType);
      if (conflictingConverter != null) {
         boolean overridable;
         if (converter instanceof Comparable) {
            Comparable<TypeConverter<?>> comparableConverter = (Comparable)converter;
            overridable = comparableConverter.compareTo(conflictingConverter) < 0;
         } else if (conflictingConverter instanceof Comparable) {
            Comparable<TypeConverter<?>> comparableConflictingConverter = (Comparable)conflictingConverter;
            overridable = comparableConflictingConverter.compareTo(converter) > 0;
         } else {
            overridable = false;
         }

         if (overridable) {
            LOGGER.debug("Replacing TypeConverter [{}] for type [{}] with [{}] after comparison.", conflictingConverter, conversionType, converter);
            this.registry.put(conversionType, converter);
            return converter;
         } else {
            LOGGER.warn("Ignoring TypeConverter [{}] for type [{}] that conflicts with [{}], since they are not comparable.", converter, conversionType, conflictingConverter);
            return conflictingConverter;
         }
      } else {
         this.registry.put(conversionType, converter);
         return converter;
      }
   }

   private static Type getTypeConverterSupportedType(final Class typeConverterClass) {
      for(Type type : typeConverterClass.getGenericInterfaces()) {
         if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            if (TypeConverter.class.equals(pType.getRawType())) {
               return pType.getActualTypeArguments()[0];
            }
         }
      }

      return Void.TYPE;
   }

   private void registerPrimitiveTypes() {
      this.registerTypeAlias(Boolean.class, Boolean.TYPE);
      this.registerTypeAlias(Byte.class, Byte.TYPE);
      this.registerTypeAlias(Character.class, Character.TYPE);
      this.registerTypeAlias(Double.class, Double.TYPE);
      this.registerTypeAlias(Float.class, Float.TYPE);
      this.registerTypeAlias(Integer.class, Integer.TYPE);
      this.registerTypeAlias(Long.class, Long.TYPE);
      this.registerTypeAlias(Short.class, Short.TYPE);
   }

   private void registerTypeAlias(final Type knownType, final Type aliasType) {
      this.registry.putIfAbsent(aliasType, (TypeConverter)this.registry.get(knownType));
   }
}
