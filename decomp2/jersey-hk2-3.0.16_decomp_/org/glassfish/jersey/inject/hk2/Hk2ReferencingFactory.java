package org.glassfish.jersey.inject.hk2;

import jakarta.inject.Provider;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.internal.util.collection.Refs;

public abstract class Hk2ReferencingFactory implements Factory {
   private final Provider referenceFactory;

   public Hk2ReferencingFactory(Provider referenceFactory) {
      this.referenceFactory = referenceFactory;
   }

   public Object provide() {
      return ((Ref)this.referenceFactory.get()).get();
   }

   public void dispose(Object instance) {
   }

   public static Factory referenceFactory() {
      return new EmptyReferenceFactory();
   }

   public static Factory referenceFactory(Object initialValue) {
      return (Factory)(initialValue == null ? new EmptyReferenceFactory() : new InitializedReferenceFactory(initialValue));
   }

   private static class EmptyReferenceFactory implements Factory {
      private EmptyReferenceFactory() {
      }

      public Ref provide() {
         return Refs.emptyRef();
      }

      public void dispose(Ref instance) {
      }
   }

   private static class InitializedReferenceFactory implements Factory {
      private final Object initialValue;

      public InitializedReferenceFactory(Object initialValue) {
         this.initialValue = initialValue;
      }

      public Ref provide() {
         return Refs.of(this.initialValue);
      }

      public void dispose(Ref instance) {
      }
   }
}
