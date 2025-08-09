package org.glassfish.jersey.internal.util.collection;

public final class Values {
   private static final LazyValue EMPTY = new LazyValue() {
      public Object get() {
         return null;
      }

      public boolean isInitialized() {
         return true;
      }
   };
   private static final LazyUnsafeValue EMPTY_UNSAFE = new LazyUnsafeValue() {
      public Object get() {
         return null;
      }

      public boolean isInitialized() {
         return true;
      }
   };

   private Values() {
   }

   public static Value empty() {
      return EMPTY;
   }

   public static UnsafeValue emptyUnsafe() {
      return EMPTY_UNSAFE;
   }

   public static Value of(Object value) {
      return (Value)(value == null ? empty() : new InstanceValue(value));
   }

   public static UnsafeValue unsafe(Object value) {
      return (UnsafeValue)(value == null ? emptyUnsafe() : new InstanceUnsafeValue(value));
   }

   public static UnsafeValue throwing(Throwable throwable) {
      if (throwable == null) {
         throw new NullPointerException("Supplied throwable ");
      } else {
         return new ExceptionValue(throwable);
      }
   }

   public static LazyValue lazy(Value delegate) {
      return (LazyValue)(delegate == null ? EMPTY : new LazyValueImpl(delegate));
   }

   public static Value eager(Value delegate) {
      return (Value)(delegate == null ? empty() : new EagerValue(delegate));
   }

   public static LazyUnsafeValue lazy(UnsafeValue delegate) {
      return (LazyUnsafeValue)(delegate == null ? EMPTY_UNSAFE : new LazyUnsafeValueImpl(delegate));
   }

   private static class InstanceValue implements Value {
      private final Object value;

      public InstanceValue(Object value) {
         this.value = value;
      }

      public Object get() {
         return this.value;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() ? this.value.equals(((InstanceValue)o).value) : false;
         }
      }

      public int hashCode() {
         return this.value != null ? this.value.hashCode() : 0;
      }

      public String toString() {
         return "InstanceValue{value=" + this.value + '}';
      }
   }

   private static class InstanceUnsafeValue implements UnsafeValue {
      private final Object value;

      public InstanceUnsafeValue(Object value) {
         this.value = value;
      }

      public Object get() {
         return this.value;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() ? this.value.equals(((InstanceUnsafeValue)o).value) : false;
         }
      }

      public int hashCode() {
         return this.value != null ? this.value.hashCode() : 0;
      }

      public String toString() {
         return "InstanceUnsafeValue{value=" + this.value + '}';
      }
   }

   private static class ExceptionValue implements UnsafeValue {
      private final Throwable throwable;

      public ExceptionValue(Throwable throwable) {
         this.throwable = throwable;
      }

      public Object get() throws Throwable {
         throw this.throwable;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() ? this.throwable.equals(((ExceptionValue)o).throwable) : false;
         }
      }

      public int hashCode() {
         return this.throwable != null ? this.throwable.hashCode() : 0;
      }

      public String toString() {
         return "ExceptionValue{throwable=" + this.throwable + '}';
      }
   }

   private static class EagerValue implements Value {
      private final Object result;

      private EagerValue(Value value) {
         this.result = value.get();
      }

      public Object get() {
         return this.result;
      }
   }

   private static class LazyValueImpl implements LazyValue {
      private final Object lock;
      private final Value delegate;
      private volatile Value value;

      public LazyValueImpl(Value delegate) {
         this.delegate = delegate;
         this.lock = new Object();
      }

      public Object get() {
         Value<T> result = this.value;
         if (result == null) {
            synchronized(this.lock) {
               result = this.value;
               if (result == null) {
                  this.value = result = Values.of(this.delegate.get());
               }
            }
         }

         return result.get();
      }

      public boolean isInitialized() {
         return this.value != null;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() ? this.delegate.equals(((LazyValueImpl)o).delegate) : false;
         }
      }

      public int hashCode() {
         return this.delegate != null ? this.delegate.hashCode() : 0;
      }

      public String toString() {
         return "LazyValue{delegate=" + this.delegate.toString() + '}';
      }
   }

   private static class LazyUnsafeValueImpl implements LazyUnsafeValue {
      private final Object lock;
      private final UnsafeValue delegate;
      private volatile UnsafeValue value;

      public LazyUnsafeValueImpl(UnsafeValue delegate) {
         this.delegate = delegate;
         this.lock = new Object();
      }

      public Object get() throws Throwable {
         UnsafeValue<T, E> result = this.value;
         if (result == null) {
            synchronized(this.lock) {
               result = this.value;
               if (result == null) {
                  try {
                     result = Values.unsafe(this.delegate.get());
                  } catch (Throwable e) {
                     result = Values.throwing(e);
                  }

                  this.value = result;
               }
            }
         }

         return result.get();
      }

      public boolean isInitialized() {
         return this.value != null;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() ? this.delegate.equals(((LazyUnsafeValueImpl)o).delegate) : false;
         }
      }

      public int hashCode() {
         return this.delegate != null ? this.delegate.hashCode() : 0;
      }

      public String toString() {
         return "LazyValue{delegate=" + this.delegate.toString() + '}';
      }
   }
}
