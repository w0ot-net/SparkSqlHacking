package javolution.context;

import javolution.lang.Configurable;
import javolution.lang.ValueType;

public abstract class AllocatorContext extends Context {
   private static volatile AllocatorContext _Default = new HeapContext();
   public static final Configurable DEFAULT = new Configurable(HeapContext.class) {
      protected void notifyChange(Object oldValue, Object newValue) {
         AllocatorContext._Default = (AllocatorContext)ObjectFactory.getInstance((Class)newValue).object();
      }
   };

   protected AllocatorContext() {
   }

   public static AllocatorContext getCurrentAllocatorContext() {
      return Context.getCurrentContext().getAllocatorContext();
   }

   public static AllocatorContext getDefault() {
      return _Default;
   }

   protected abstract Allocator getAllocator(ObjectFactory var1);

   protected abstract void deactivate();

   public static ValueType outerCopy(ValueType value) {
      Context.enter(OuterContext.class);

      ValueType var2;
      try {
         Object copy = value.copy();
         var2 = (ValueType)copy;
      } finally {
         Context.exit(OuterContext.class);
      }

      return var2;
   }

   public static void outerCopy(ValueType[] values) {
      Context.enter(OuterContext.class);

      try {
         for(int i = 0; i < values.length; ++i) {
            values[i] = (ValueType)values[i].copy();
         }
      } finally {
         Context.exit(OuterContext.class);
      }

   }

   public static void outerExecute(Runnable logic) {
      Context.enter(OuterContext.class);

      try {
         logic.run();
      } finally {
         Context.exit(OuterContext.class);
      }

   }

   static {
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new OuterContext();
         }
      }, OuterContext.class);
   }

   public static class Reference implements javolution.lang.Reference {
      private static final ObjectFactory FACTORY = new ObjectFactory() {
         protected Object create() {
            return new Reference();
         }

         protected void cleanup(Object obj) {
            ((Reference)obj)._value = null;
         }
      };
      private Object _value;

      public static Reference newInstance() {
         return (Reference)FACTORY.object();
      }

      public String toString() {
         return String.valueOf(this._value);
      }

      public final Object get() {
         return this._value;
      }

      public final void set(Object value) {
         this._value = value;
      }
   }

   private static class OuterContext extends AllocatorContext {
      private AllocatorContext _outer;
      private AllocatorContext _outerOuter;

      private OuterContext() {
      }

      protected Allocator getAllocator(ObjectFactory factory) {
         return this._outerOuter.getAllocator(factory);
      }

      protected void deactivate() {
         this._outerOuter.deactivate();
      }

      protected void enterAction() {
         this._outer = this.getOuter().getAllocatorContext();
         Context outer = this._outer.getOuter();
         if (outer == null) {
            this._outerOuter = this._outer;
         } else {
            this._outerOuter = outer.getAllocatorContext();
            this._outer.deactivate();
         }

      }

      protected void exitAction() {
         if (this._outer != this._outerOuter) {
            this._outerOuter.deactivate();
         }

         this._outer = null;
         this._outerOuter = null;
      }
   }
}
