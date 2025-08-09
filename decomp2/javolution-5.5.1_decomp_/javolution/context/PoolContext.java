package javolution.context;

import javolution.util.FastMap;
import javolution.util.FastTable;

public class PoolContext extends AllocatorContext {
   private static final ThreadLocal FACTORY_TO_ALLOCATOR = new ThreadLocal() {
      protected Object initialValue() {
         return new FastMap();
      }
   };
   private static final ThreadLocal ACTIVE_ALLOCATORS = new ThreadLocal() {
      protected Object initialValue() {
         return new FastTable();
      }
   };

   public static void enter() {
      Context.enter(PoolContext.class);
   }

   public static void exit() {
      Context.exit(PoolContext.class);
   }

   protected void deactivate() {
      FastTable allocators = (FastTable)ACTIVE_ALLOCATORS.get();
      int i = 0;

      for(int n = allocators.size(); i < n; ((Allocator)allocators.get(i++)).user = null) {
      }

      allocators.clear();
   }

   protected Allocator getAllocator(ObjectFactory factory) {
      FastMap factoryToAllocator = (FastMap)FACTORY_TO_ALLOCATOR.get();
      PoolAllocator allocator = (PoolAllocator)factoryToAllocator.get(factory);
      if (allocator == null) {
         allocator = new PoolAllocator(factory);
         factoryToAllocator.put(factory, allocator);
      }

      if (allocator.user == null) {
         allocator.user = Thread.currentThread();
         FastTable activeAllocators = (FastTable)ACTIVE_ALLOCATORS.get();
         activeAllocators.add(allocator);
      }

      return allocator;
   }

   protected void enterAction() {
      this.getOuter().getAllocatorContext().deactivate();
   }

   protected void exitAction() {
      this.deactivate();
   }

   private static final class PoolAllocator extends Allocator {
      private static final FastMap FACTORY_TO_POOL = new FastMap();
      private final ObjectFactory _factory;
      private final FastTable _recycled;

      public PoolAllocator(ObjectFactory factory) {
         this._factory = factory;
         synchronized(FACTORY_TO_POOL) {
            FastTable recycled = (FastTable)FACTORY_TO_POOL.get(factory);
            if (recycled == null) {
               recycled = new FastTable();
               FACTORY_TO_POOL.put(factory, recycled);
            }

            this._recycled = recycled;
         }
      }

      protected Object allocate() {
         if (this._recycled.isEmpty()) {
            return this._factory.create();
         } else {
            synchronized(this._recycled) {
               return this._recycled.removeLast();
            }
         }
      }

      protected void recycle(Object object) {
         if (this._factory.doCleanup()) {
            this._factory.cleanup(object);
         }

         synchronized(this._recycled) {
            this._recycled.addLast(object);
         }
      }

      public String toString() {
         return "Pool allocator for " + this._factory.getClass();
      }
   }
}
