package shaded.parquet.com.fasterxml.jackson.databind.module;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import shaded.parquet.com.fasterxml.jackson.databind.AbstractTypeResolver;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.type.ClassKey;

public class SimpleAbstractTypeResolver extends AbstractTypeResolver implements Serializable {
   private static final long serialVersionUID = 1L;
   protected final HashMap _mappings = new HashMap();

   public SimpleAbstractTypeResolver addMapping(Class superType, Class subType) {
      if (superType == subType) {
         throw new IllegalArgumentException("Cannot add mapping from class to itself");
      } else if (!superType.isAssignableFrom(subType)) {
         throw new IllegalArgumentException("Cannot add mapping from class " + superType.getName() + " to " + subType.getName() + ", as latter is not a subtype of former");
      } else if (!Modifier.isAbstract(superType.getModifiers())) {
         throw new IllegalArgumentException("Cannot add mapping from class " + superType.getName() + " since it is not abstract");
      } else {
         this._mappings.put(new ClassKey(superType), subType);
         return this;
      }
   }

   public JavaType findTypeMapping(DeserializationConfig config, JavaType type) {
      Class<?> src = type.getRawClass();
      Class<?> dst = (Class)this._mappings.get(new ClassKey(src));
      return dst == null ? null : config.getTypeFactory().constructSpecializedType(type, dst);
   }

   /** @deprecated */
   @Deprecated
   public JavaType resolveAbstractType(DeserializationConfig config, JavaType type) {
      return null;
   }

   public JavaType resolveAbstractType(DeserializationConfig config, BeanDescription typeDesc) {
      return null;
   }
}
