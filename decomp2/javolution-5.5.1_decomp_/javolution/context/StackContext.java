package javolution.context;

import javolution.lang.Configurable;
import javolution.util.FastMap;
import javolution.util.FastTable;

public abstract class StackContext extends AllocatorContext {
   public static final Configurable DEFAULT = new Configurable(Default.class) {
   };

   public static void enter() {
      Context.enter((Class)DEFAULT.get());
   }

   public static void enter(boolean condition) {
      if (condition) {
         enter();
      }

   }

   public static void exit() {
      Context.exit(StackContext.class);
   }

   public static void exit(boolean condition) {
      if (condition) {
         exit();
      }

   }

   static {
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Default();
         }
      }, Default.class);
   }

   private static final class Default extends StackContext {
      private final ThreadLocal _factoryToAllocator;
      private final ThreadLocal _activeAllocators;
      private final FastTable _ownerUsedAllocators;
      private final FastTable _nonOwnerUsedAllocators;

      private Default() {
         this._factoryToAllocator = new ThreadLocal() {
            protected Object initialValue() {
               return new FastMap();
            }
         };
         this._activeAllocators = new ThreadLocal() {
            protected Object initialValue() {
               return new FastTable();
            }
         };
         this._ownerUsedAllocators = new FastTable();
         this._nonOwnerUsedAllocators = new FastTable();
      }

      protected void deactivate() {
         FastTable allocators = (FastTable)this._activeAllocators.get();
         int i = 0;

         for(int n = allocators.size(); i < n; ((Allocator)allocators.get(i++)).user = null) {
         }

         allocators.clear();
      }

      protected Allocator getAllocator(ObjectFactory factory) {
         FastMap factoryToAllocator = (FastMap)this._factoryToAllocator.get();
         StackAllocator allocator = (StackAllocator)factoryToAllocator.get(factory);
         if (allocator == null) {
            allocator = new StackAllocator(factory);
            factoryToAllocator.put(factory, allocator);
         }

         if (allocator.user == null) {
            allocator.user = Thread.currentThread();
            FastTable activeAllocators = (FastTable)this._activeAllocators.get();
            activeAllocators.add(allocator);
         }

         if (!allocator._inUse) {
            allocator._inUse = true;
            if (Thread.currentThread() == this.getOwner()) {
               this._ownerUsedAllocators.add(allocator);
            } else {
               synchronized(this._nonOwnerUsedAllocators) {
                  this._nonOwnerUsedAllocators.add(allocator);
               }
            }
         }

         return allocator;
      }

      protected void enterAction() {
         this.getOuter().getAllocatorContext().deactivate();
      }

      protected void exitAction() {
         this.deactivate();

         for(int i = 0; i < this._ownerUsedAllocators.size(); ++i) {
            StackAllocator allocator = (StackAllocator)this._ownerUsedAllocators.get(i);
            allocator.reset();
         }

         this._ownerUsedAllocators.clear();

         for(int i = 0; i < this._nonOwnerUsedAllocators.size(); ++i) {
            StackAllocator allocator = (StackAllocator)this._nonOwnerUsedAllocators.get(i);
            allocator.reset();
         }

         this._nonOwnerUsedAllocators.clear();
      }
   }

   private static final class StackAllocator extends Allocator {
      private final ObjectFactory _factory;
      private boolean _inUse;
      private int _queueLimit;

      public StackAllocator(ObjectFactory factory) {
         this._factory = factory;
      }

      protected Object allocate() {
         if (this._queueLimit >= this.queue.length) {
            this.resize();
         }

         Object obj = this._factory.create();
         this.queue[this._queueLimit++] = obj;
         return obj;
      }

      protected void recycle(Object object) {
         if (this._factory.doCleanup()) {
            this._factory.cleanup(object);
         }

         for(int i = this.queueSize; i < this._queueLimit; ++i) {
            if (this.queue[i] == object) {
               this.queue[i] = this.queue[this.queueSize];
               this.queue[this.queueSize++] = object;
               return;
            }
         }

         throw new UnsupportedOperationException("Cannot recycle to the stack an object which has not been allocated from the stack");
      }

      protected void reset() {
         this._inUse = false;

         while(this._factory.doCleanup() && this.queueSize != this._queueLimit) {
            Object obj = this.queue[this.queueSize++];
            this._factory.cleanup(obj);
         }

         this.queueSize = this._queueLimit;
      }

      public String toString() {
         return "Stack allocator for " + this._factory.getClass();
      }
   }
}
