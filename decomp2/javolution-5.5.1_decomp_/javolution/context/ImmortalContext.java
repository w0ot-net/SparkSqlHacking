package javolution.context;

import javax.realtime.MemoryArea;
import javolution.util.FastMap;
import javolution.util.FastTable;

public final class ImmortalContext extends AllocatorContext {
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
      Context.enter(ImmortalContext.class);
   }

   public static void exit() {
      Context.exit(ImmortalContext.class);
   }

   private ImmortalContext() {
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
      ImmortalAllocator allocator = (ImmortalAllocator)factoryToAllocator.get(factory);
      if (allocator == null) {
         allocator = new ImmortalAllocator(factory);
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

   static {
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new ImmortalContext();
         }
      }, ImmortalContext.class);
   }

   private static final class ImmortalAllocator extends Allocator {
      private static final MemoryArea IMMORTAL = MemoryArea.getMemoryArea("");
      private final ObjectFactory _factory;
      private Object _allocated;
      private final Runnable _allocate = new Runnable() {
         public void run() {
            ImmortalAllocator.this._allocated = ImmortalAllocator.this._factory.create();
         }
      };
      private final Runnable _resize = new Runnable() {
         public void run() {
            ImmortalAllocator.this.resize();
         }
      };

      public ImmortalAllocator(ObjectFactory factory) {
         this._factory = factory;
      }

      protected Object allocate() {
         IMMORTAL.executeInArea(this._allocate);
         return this._allocated;
      }

      protected void recycle(Object object) {
         if (this._factory.doCleanup()) {
            this._factory.cleanup(object);
         }

         if (this.queueSize >= this.queue.length) {
            IMMORTAL.executeInArea(this._resize);
         }

         this.queue[this.queueSize++] = object;
      }

      public String toString() {
         return "Immortal allocator for " + this._factory.getClass();
      }
   }
}
