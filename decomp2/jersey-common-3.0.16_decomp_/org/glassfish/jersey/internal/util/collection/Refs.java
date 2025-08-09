package org.glassfish.jersey.internal.util.collection;

public final class Refs {
   private Refs() {
   }

   public static Ref of(Object value) {
      return new DefaultRefImpl(value);
   }

   public static Ref emptyRef() {
      return new DefaultRefImpl();
   }

   public static Ref threadSafe() {
      return new ThreadSafeRefImpl();
   }

   public static Ref threadSafe(Object value) {
      return new ThreadSafeRefImpl(value);
   }

   public static Ref immutableRef(Object value) {
      return new ImmutableRefImpl(value);
   }

   private static final class ImmutableRefImpl implements Ref {
      private final Object reference;

      ImmutableRefImpl(Object value) {
         this.reference = value;
      }

      public Object get() {
         return this.reference;
      }

      public void set(Object value) throws IllegalStateException {
         throw new IllegalStateException("This implementation of Ref interface is immutable.");
      }

      public String toString() {
         return "ImmutableRefImpl{reference=" + this.reference + '}';
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         } else if (!(obj instanceof Ref)) {
            return false;
         } else {
            Object otherRef = ((Ref)obj).get();
            return this.reference == otherRef || this.reference != null && this.reference.equals(otherRef);
         }
      }

      public int hashCode() {
         int hash = 5;
         hash = 47 * hash + (this.reference != null ? this.reference.hashCode() : 0);
         return hash;
      }
   }

   private static final class DefaultRefImpl implements Ref {
      private Object reference;

      DefaultRefImpl() {
         this.reference = null;
      }

      DefaultRefImpl(Object value) {
         this.reference = value;
      }

      public Object get() {
         return this.reference;
      }

      public void set(Object value) throws IllegalStateException {
         this.reference = value;
      }

      public String toString() {
         return "DefaultRefImpl{reference=" + this.reference + '}';
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         } else if (!(obj instanceof Ref)) {
            return false;
         } else {
            Object otherRef = ((Ref)obj).get();
            T ref = (T)this.reference;
            return ref == otherRef || ref != null && ref.equals(otherRef);
         }
      }

      public int hashCode() {
         int hash = 5;
         hash = 47 * hash + (this.reference != null ? this.reference.hashCode() : 0);
         return hash;
      }
   }

   private static final class ThreadSafeRefImpl implements Ref {
      private volatile Object reference;

      ThreadSafeRefImpl() {
         this.reference = null;
      }

      ThreadSafeRefImpl(Object value) {
         this.reference = value;
      }

      public Object get() {
         return this.reference;
      }

      public void set(Object value) throws IllegalStateException {
         this.reference = value;
      }

      public String toString() {
         return "ThreadSafeRefImpl{reference=" + this.reference + '}';
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         } else if (!(obj instanceof Ref)) {
            return false;
         } else {
            Object otherRef = ((Ref)obj).get();
            T localRef = (T)this.reference;
            return localRef == otherRef || localRef != null && localRef.equals(otherRef);
         }
      }

      public int hashCode() {
         T localRef = (T)this.reference;
         int hash = 5;
         hash = 47 * hash + (localRef != null ? localRef.hashCode() : 0);
         return hash;
      }
   }
}
