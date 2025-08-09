package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonCreator;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.KeyDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.deser.KeyDeserializers;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedAndMetadata;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;
import shaded.parquet.com.fasterxml.jackson.databind.util.EnumResolver;

public class StdKeyDeserializers implements KeyDeserializers, Serializable {
   private static final long serialVersionUID = 1L;

   public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver enumResolver) {
      return new StdKeyDeserializer.EnumKD(enumResolver, (AnnotatedMethod)null);
   }

   public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver enumResolver, AnnotatedMethod factory) {
      return new StdKeyDeserializer.EnumKD(enumResolver, factory);
   }

   public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver enumRes, EnumResolver byEnumNamingResolver, EnumResolver byToStringResolver, EnumResolver byIndexResolver) {
      return new StdKeyDeserializer.EnumKD(enumRes, (AnnotatedMethod)null, byEnumNamingResolver, byToStringResolver, byIndexResolver);
   }

   public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver enumResolver, AnnotatedMethod factory, EnumResolver enumNamingResolver, EnumResolver byToStringResolver, EnumResolver byIndexResolver) {
      return new StdKeyDeserializer.EnumKD(enumResolver, factory, enumNamingResolver, byToStringResolver, byIndexResolver);
   }

   public static KeyDeserializer constructDelegatingKeyDeserializer(DeserializationConfig config, JavaType type, JsonDeserializer deser) {
      return new StdKeyDeserializer.DelegatingKD(type.getRawClass(), deser);
   }

   public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig config, JavaType type) throws JsonMappingException {
      BeanDescription beanDesc = config.introspectForCreation(type);
      AnnotatedAndMetadata<AnnotatedConstructor, JsonCreator.Mode> ctorInfo = _findStringConstructor(beanDesc);
      if (ctorInfo != null && ctorInfo.metadata != null) {
         return _constructCreatorKeyDeserializer(config, (AnnotatedMember)ctorInfo.annotated);
      } else {
         List<AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode>> factoryCandidates = beanDesc.getFactoryMethodsWithMode();
         factoryCandidates.removeIf((m) -> ((AnnotatedMethod)m.annotated).getParameterCount() != 1 || ((AnnotatedMethod)m.annotated).getRawParameterType(0) != String.class || m.metadata == JsonCreator.Mode.PROPERTIES);
         AnnotatedMethod explicitFactory = _findExplicitStringFactoryMethod(factoryCandidates);
         if (explicitFactory != null) {
            return _constructCreatorKeyDeserializer(config, explicitFactory);
         } else if (ctorInfo != null) {
            return _constructCreatorKeyDeserializer(config, (AnnotatedMember)ctorInfo.annotated);
         } else {
            return !factoryCandidates.isEmpty() ? _constructCreatorKeyDeserializer(config, (AnnotatedMember)((AnnotatedAndMetadata)factoryCandidates.get(0)).annotated) : null;
         }
      }
   }

   private static KeyDeserializer _constructCreatorKeyDeserializer(DeserializationConfig config, AnnotatedMember creator) {
      if (creator instanceof AnnotatedConstructor) {
         Constructor<?> rawCtor = ((AnnotatedConstructor)creator).getAnnotated();
         if (config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(rawCtor, config.isEnabled((MapperFeature)MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
         }

         return new StdKeyDeserializer.StringCtorKeyDeserializer(rawCtor);
      } else {
         Method m = ((AnnotatedMethod)creator).getAnnotated();
         if (config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(m, config.isEnabled((MapperFeature)MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
         }

         return new StdKeyDeserializer.StringFactoryKeyDeserializer(m);
      }
   }

   private static AnnotatedAndMetadata _findStringConstructor(BeanDescription beanDesc) {
      for(AnnotatedAndMetadata entry : beanDesc.getConstructorsWithMode()) {
         AnnotatedConstructor ctor = (AnnotatedConstructor)entry.annotated;
         if (ctor.getParameterCount() == 1 && String.class == ctor.getRawParameterType(0)) {
            return entry;
         }
      }

      return null;
   }

   private static AnnotatedMethod _findExplicitStringFactoryMethod(List candidates) throws JsonMappingException {
      AnnotatedMethod match = null;

      for(AnnotatedAndMetadata entry : candidates) {
         if (entry.metadata != null) {
            if (match != null) {
               Class<?> rawKeyType = ((AnnotatedMethod)entry.annotated).getDeclaringClass();
               throw new IllegalArgumentException("Multiple suitable annotated Creator factory methods to be used as the Key deserializer for type " + ClassUtil.nameOf(rawKeyType));
            }

            match = (AnnotatedMethod)entry.annotated;
         }
      }

      return match;
   }

   public KeyDeserializer findKeyDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
      Class<?> raw = type.getRawClass();
      if (raw.isPrimitive()) {
         raw = ClassUtil.wrapperType(raw);
      }

      return StdKeyDeserializer.forType(raw);
   }
}
