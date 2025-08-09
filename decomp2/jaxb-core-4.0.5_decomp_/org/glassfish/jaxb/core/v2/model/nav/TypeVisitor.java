package org.glassfish.jaxb.core.v2.model.nav;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

abstract class TypeVisitor {
   public final Object visit(Type t, Object param) {
      assert t != null;

      if (t instanceof Class) {
         return this.onClass((Class)t, param);
      } else if (t instanceof ParameterizedType) {
         return this.onParameterizdType((ParameterizedType)t, param);
      } else if (t instanceof GenericArrayType) {
         return this.onGenericArray((GenericArrayType)t, param);
      } else if (t instanceof WildcardType) {
         return this.onWildcard((WildcardType)t, param);
      } else if (t instanceof TypeVariable) {
         return this.onVariable((TypeVariable)t, param);
      } else {
         assert false;

         throw new IllegalArgumentException();
      }
   }

   protected abstract Object onClass(Class var1, Object var2);

   protected abstract Object onParameterizdType(ParameterizedType var1, Object var2);

   protected abstract Object onGenericArray(GenericArrayType var1, Object var2);

   protected abstract Object onVariable(TypeVariable var1, Object var2);

   protected abstract Object onWildcard(WildcardType var1, Object var2);
}
