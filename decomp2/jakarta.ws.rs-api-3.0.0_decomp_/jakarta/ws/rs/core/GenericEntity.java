package jakarta.ws.rs.core;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericEntity {
   private final Class rawType;
   private final Type type;
   private final Object entity;

   protected GenericEntity(Object entity) {
      if (entity == null) {
         throw new IllegalArgumentException("The entity must not be null");
      } else {
         this.entity = entity;
         this.type = GenericType.getTypeArgument(this.getClass(), GenericEntity.class);
         this.rawType = entity.getClass();
      }
   }

   public GenericEntity(Object entity, Type genericType) {
      if (entity != null && genericType != null) {
         this.entity = entity;
         this.rawType = entity.getClass();
         this.checkTypeCompatibility(this.rawType, genericType);
         this.type = genericType;
      } else {
         throw new IllegalArgumentException("Arguments must not be null.");
      }
   }

   private void checkTypeCompatibility(Class c, Type t) {
      if (t instanceof Class) {
         Class<?> ct = (Class)t;
         if (ct.isAssignableFrom(c)) {
            return;
         }
      } else {
         if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType)t;
            Type rt = pt.getRawType();
            this.checkTypeCompatibility(c, rt);
            return;
         }

         if (c.isArray() && t instanceof GenericArrayType) {
            GenericArrayType at = (GenericArrayType)t;
            Type rt = at.getGenericComponentType();
            this.checkTypeCompatibility(c.getComponentType(), rt);
            return;
         }
      }

      throw new IllegalArgumentException("The type is incompatible with the class of the entity.");
   }

   public final Class getRawType() {
      return this.rawType;
   }

   public final Type getType() {
      return this.type;
   }

   public final Object getEntity() {
      return this.entity;
   }

   public boolean equals(Object obj) {
      boolean result = this == obj;
      if (!result && obj instanceof GenericEntity) {
         GenericEntity<?> that = (GenericEntity)obj;
         return this.type.equals(that.type) && this.entity.equals(that.entity);
      } else {
         return result;
      }
   }

   public int hashCode() {
      return this.entity.hashCode() + this.type.hashCode() * 37 + 5;
   }

   public String toString() {
      return "GenericEntity{" + this.entity.toString() + ", " + this.type.toString() + "}";
   }
}
