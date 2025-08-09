package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import java.security.GeneralSecurityException;

public abstract class PrimitiveConstructor {
   private final Class keyClass;
   private final Class primitiveClass;

   private PrimitiveConstructor(Class keyClass, Class primitiveClass) {
      this.keyClass = keyClass;
      this.primitiveClass = primitiveClass;
   }

   public abstract Object constructPrimitive(Key key) throws GeneralSecurityException;

   public Class getKeyClass() {
      return this.keyClass;
   }

   public Class getPrimitiveClass() {
      return this.primitiveClass;
   }

   public static PrimitiveConstructor create(final PrimitiveConstructionFunction function, Class keyClass, Class primitiveClass) {
      return new PrimitiveConstructor(keyClass, primitiveClass) {
         public Object constructPrimitive(Key key) throws GeneralSecurityException {
            return function.constructPrimitive(key);
         }
      };
   }

   public interface PrimitiveConstructionFunction {
      Object constructPrimitive(Key key) throws GeneralSecurityException;
   }
}
