package org.glassfish.jersey.internal.inject;

import jakarta.inject.Provider;
import java.util.function.Supplier;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.internal.util.collection.Refs;

public abstract class ReferencingFactory implements Supplier {
   private final Provider referenceFactory;

   public ReferencingFactory(Provider referenceFactory) {
      this.referenceFactory = referenceFactory;
   }

   public Object get() {
      return ((Ref)this.referenceFactory.get()).get();
   }

   public static Supplier referenceFactory() {
      return new EmptyReferenceFactory();
   }

   public static Supplier referenceFactory(Object initialValue) {
      return (Supplier)(initialValue == null ? new EmptyReferenceFactory() : new InitializedReferenceFactory(initialValue));
   }

   private static class EmptyReferenceFactory implements Supplier {
      private EmptyReferenceFactory() {
      }

      public Ref get() {
         return Refs.emptyRef();
      }
   }

   private static class InitializedReferenceFactory implements Supplier {
      private final Object initialValue;

      public InitializedReferenceFactory(Object initialValue) {
         this.initialValue = initialValue;
      }

      public Ref get() {
         return Refs.of(this.initialValue);
      }
   }
}
