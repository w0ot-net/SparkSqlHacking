package javolution.context;

import javolution.util.FastMap;
import javolution.util.FastTable;

public class HeapContext extends AllocatorContext {
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
      Context.enter(HeapContext.class);
   }

   public static void exit() {
      Context.exit(HeapContext.class);
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
      HeapAllocator allocator = (HeapAllocator)factoryToAllocator.get(factory);
      if (allocator == null) {
         allocator = new HeapAllocator(factory);
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

   private static final class HeapAllocator extends Allocator {
      private final ObjectFactory _factory;
      private final FastTable _recycled = new FastTable();

      public HeapAllocator(ObjectFactory factory) {
         this._factory = factory;
      }

      protected Object allocate() {
         return this._recycled.isEmpty() ? this._factory.create() : this._recycled.removeLast();
      }

      protected void recycle(Object object) {
         if (this._factory.doCleanup()) {
            this._factory.cleanup(object);
         }

         this._recycled.addLast(object);
      }

      public String toString() {
         return "Heap allocator for " + this._factory.getClass();
      }
   }
}
