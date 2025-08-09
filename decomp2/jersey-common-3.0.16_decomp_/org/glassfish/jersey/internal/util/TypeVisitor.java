package org.glassfish.jersey.internal.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

abstract class TypeVisitor {
   public final Object visit(Type type) {
      assert type != null;

      if (type instanceof Class) {
         return this.onClass((Class)type);
      } else if (type instanceof ParameterizedType) {
         return this.onParameterizedType((ParameterizedType)type);
      } else if (type instanceof GenericArrayType) {
         return this.onGenericArray((GenericArrayType)type);
      } else if (type instanceof WildcardType) {
         return this.onWildcard((WildcardType)type);
      } else if (type instanceof TypeVariable) {
         return this.onVariable((TypeVariable)type);
      } else {
         assert false;

         throw this.createError(type);
      }
   }

   protected abstract Object onClass(Class var1);

   protected abstract Object onParameterizedType(ParameterizedType var1);

   protected abstract Object onGenericArray(GenericArrayType var1);

   protected abstract Object onVariable(TypeVariable var1);

   protected abstract Object onWildcard(WildcardType var1);

   protected RuntimeException createError(Type type) {
      throw new IllegalArgumentException();
   }
}
