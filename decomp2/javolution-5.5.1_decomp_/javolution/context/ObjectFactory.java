package javolution.context;

import javolution.lang.Reflection;
import javolution.lang.Reusable;

public abstract class ObjectFactory {
   private boolean _doCleanup = true;
   private Allocator _allocator;
   private static final Allocator NULL_ALLOCATOR = new Allocator() {
      protected Object allocate() {
         return null;
      }

      protected void recycle(Object object) {
      }
   };
   private ThreadLocal _localAllocator;

   protected ObjectFactory() {
      // $FF: Couldn't be decompiled
   }

   public static ObjectFactory getInstance(Class forClass) {
      ObjectFactory factory = (ObjectFactory)Reflection.getInstance().getField(forClass, ObjectFactory.class, false);
      return (ObjectFactory)(factory != null ? factory : new Generic(forClass));
   }

   public static void setInstance(ObjectFactory factory, Class forClass) {
      Reflection.getInstance().setField(factory, forClass, ObjectFactory.class);
   }

   public final Object object() {
      Allocator<T> allocator = this._allocator;
      return allocator.user == Thread.currentThread() ? allocator.next() : this.currentAllocator().next();
   }

   public final void recycle(Object obj) {
      Allocator<T> allocator = this._allocator;
      if (allocator.user != Thread.currentThread()) {
         allocator = this.currentAllocator();
      }

      allocator.recycle(obj);
   }

   public final Allocator currentAllocator() {
      Allocator allocator = (Allocator)this._localAllocator.get();
      if (allocator.user != null) {
         return this._allocator = allocator;
      } else {
         allocator = Context.getCurrentContext().getAllocatorContext().getAllocator(this);
         this._localAllocator.set(allocator);
         this._allocator = allocator;
         return allocator;
      }
   }

   protected abstract Object create();

   protected void cleanup(Object obj) {
      if (obj instanceof Reusable) {
         ((Reusable)obj).reset();
      } else {
         this._doCleanup = false;
      }

   }

   protected final boolean doCleanup() {
      return this._doCleanup;
   }

   // $FF: synthetic method
   static Allocator access$100() {
      return NULL_ALLOCATOR;
   }

   private static class Generic extends ObjectFactory {
      private final Class _class;

      private Generic(Class cls) {
         this._class = cls;
      }

      protected Object create() {
         try {
            return this._class.newInstance();
         } catch (InstantiationException var2) {
            throw new Error("Cannot instantiate no-arg constructor for " + this._class.getName() + ", the factory should be set explicitly using ObjectFactory.setInstance");
         } catch (IllegalAccessException var3) {
            throw new Error("Cannot access no-arg constructor for " + this._class.getName() + ", the factory should be set explicitly using ObjectFactory.setInstance");
         }
      }
   }
}
