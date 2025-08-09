package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PrimitiveRegistry {
   private final Map primitiveConstructorMap;
   private final Map primitiveWrapperMap;

   public static Builder builder() {
      return new Builder();
   }

   public static Builder builder(PrimitiveRegistry registry) {
      return new Builder(registry);
   }

   private PrimitiveRegistry(Builder builder) {
      this.primitiveConstructorMap = new HashMap(builder.primitiveConstructorMap);
      this.primitiveWrapperMap = new HashMap(builder.primitiveWrapperMap);
   }

   public Object getPrimitive(Key key, Class primitiveClass) throws GeneralSecurityException {
      PrimitiveConstructorIndex index = new PrimitiveConstructorIndex(key.getClass(), primitiveClass);
      if (!this.primitiveConstructorMap.containsKey(index)) {
         throw new GeneralSecurityException("No PrimitiveConstructor for " + index + " available");
      } else {
         PrimitiveConstructor<KeyT, PrimitiveT> primitiveConstructor = (PrimitiveConstructor)this.primitiveConstructorMap.get(index);
         return primitiveConstructor.constructPrimitive(key);
      }
   }

   public Class getInputPrimitiveClass(Class wrapperClassObject) throws GeneralSecurityException {
      if (!this.primitiveWrapperMap.containsKey(wrapperClassObject)) {
         throw new GeneralSecurityException("No input primitive class for " + wrapperClassObject + " available");
      } else {
         return ((PrimitiveWrapper)this.primitiveWrapperMap.get(wrapperClassObject)).getInputPrimitiveClass();
      }
   }

   public Object wrap(PrimitiveSet primitives, Class wrapperClassObject) throws GeneralSecurityException {
      if (!this.primitiveWrapperMap.containsKey(wrapperClassObject)) {
         throw new GeneralSecurityException("No wrapper found for " + wrapperClassObject);
      } else {
         PrimitiveWrapper<?, WrapperPrimitiveT> wrapper = (PrimitiveWrapper)this.primitiveWrapperMap.get(wrapperClassObject);
         if (primitives.getPrimitiveClass().equals(wrapper.getInputPrimitiveClass()) && wrapper.getInputPrimitiveClass().equals(primitives.getPrimitiveClass())) {
            return wrapper.wrap(primitives);
         } else {
            throw new GeneralSecurityException("Input primitive type of the wrapper doesn't match the type of primitives in the provided PrimitiveSet");
         }
      }
   }

   public static final class Builder {
      private final Map primitiveConstructorMap;
      private final Map primitiveWrapperMap;

      private Builder() {
         this.primitiveConstructorMap = new HashMap();
         this.primitiveWrapperMap = new HashMap();
      }

      private Builder(PrimitiveRegistry registry) {
         this.primitiveConstructorMap = new HashMap(registry.primitiveConstructorMap);
         this.primitiveWrapperMap = new HashMap(registry.primitiveWrapperMap);
      }

      @CanIgnoreReturnValue
      public Builder registerPrimitiveConstructor(PrimitiveConstructor primitiveConstructor) throws GeneralSecurityException {
         if (primitiveConstructor == null) {
            throw new NullPointerException("primitive constructor must be non-null");
         } else {
            PrimitiveConstructorIndex index = new PrimitiveConstructorIndex(primitiveConstructor.getKeyClass(), primitiveConstructor.getPrimitiveClass());
            if (this.primitiveConstructorMap.containsKey(index)) {
               PrimitiveConstructor<?, ?> existingConstructor = (PrimitiveConstructor)this.primitiveConstructorMap.get(index);
               if (!existingConstructor.equals(primitiveConstructor) || !primitiveConstructor.equals(existingConstructor)) {
                  throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: " + index);
               }
            } else {
               this.primitiveConstructorMap.put(index, primitiveConstructor);
            }

            return this;
         }
      }

      @CanIgnoreReturnValue
      public Builder registerPrimitiveWrapper(PrimitiveWrapper wrapper) throws GeneralSecurityException {
         if (wrapper == null) {
            throw new NullPointerException("wrapper must be non-null");
         } else {
            Class<WrapperPrimitiveT> wrapperClassObject = wrapper.getPrimitiveClass();
            if (this.primitiveWrapperMap.containsKey(wrapperClassObject)) {
               PrimitiveWrapper<?, ?> existingPrimitiveWrapper = (PrimitiveWrapper)this.primitiveWrapperMap.get(wrapperClassObject);
               if (!existingPrimitiveWrapper.equals(wrapper) || !wrapper.equals(existingPrimitiveWrapper)) {
                  throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type" + wrapperClassObject);
               }
            } else {
               this.primitiveWrapperMap.put(wrapperClassObject, wrapper);
            }

            return this;
         }
      }

      public PrimitiveRegistry build() {
         return new PrimitiveRegistry(this);
      }
   }

   private static final class PrimitiveConstructorIndex {
      private final Class keyClass;
      private final Class primitiveClass;

      private PrimitiveConstructorIndex(Class keyClass, Class primitiveClass) {
         this.keyClass = keyClass;
         this.primitiveClass = primitiveClass;
      }

      public boolean equals(Object o) {
         if (!(o instanceof PrimitiveConstructorIndex)) {
            return false;
         } else {
            PrimitiveConstructorIndex other = (PrimitiveConstructorIndex)o;
            return other.keyClass.equals(this.keyClass) && other.primitiveClass.equals(this.primitiveClass);
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.keyClass, this.primitiveClass});
      }

      public String toString() {
         return this.keyClass.getSimpleName() + " with primitive type: " + this.primitiveClass.getSimpleName();
      }
   }
}
