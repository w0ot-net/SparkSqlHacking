package javolution.context;

import javolution.xml.XMLSerializable;

public abstract class Context implements XMLSerializable {
   public static final Context ROOT = new Root();
   private static final ThreadLocal CURRENT = new ThreadLocal() {
      protected Object initialValue() {
         return Context.ROOT;
      }
   };
   private Thread _owner;
   private Context _outer;
   private ObjectFactory _factory;
   private AllocatorContext _allocator;

   protected Context() {
   }

   public static Context getCurrentContext() {
      return (Context)CURRENT.get();
   }

   public final Thread getOwner() {
      return this._owner;
   }

   public final Context getOuter() {
      return this._outer;
   }

   public String toString() {
      return "Instance of " + this.getClass().getName();
   }

   protected abstract void enterAction();

   protected abstract void exitAction();

   public static final void enter(Context context) {
      if (context._owner != null) {
         throw new IllegalStateException("Context is currently in use");
      } else {
         Context current = getCurrentContext();
         context._outer = current;
         context._owner = Thread.currentThread();
         context._allocator = context instanceof AllocatorContext ? (AllocatorContext)context : current._allocator;
         CURRENT.set(context);
         context.enterAction();
      }
   }

   public static final void exit(Context context) {
      if (getCurrentContext() != context) {
         throw new IllegalArgumentException("The specified context is not the current context");
      } else {
         exit(context.getClass());
      }
   }

   public static final void enter(Class contextType) {
      ObjectFactory factory = ObjectFactory.getInstance(contextType);
      Context context = (Context)factory.object();
      context._factory = factory;
      enter(context);
   }

   public static void exit(Class contextType) {
      Context context = getCurrentContext();
      Context outer = context._outer;
      if (outer == null) {
         throw new IllegalStateException("Cannot exit root context");
      } else if (context._owner != Thread.currentThread()) {
         throw new IllegalStateException("The current thread is not the context owner");
      } else if (!contextType.isInstance(context)) {
         throw new ClassCastException("Current context is an instance of " + context.getClass().getName());
      } else {
         try {
            context.exitAction();
         } finally {
            CURRENT.set(outer);
            context._outer = null;
            context._owner = null;
            context._allocator = null;
            if (context._factory != null) {
               context._factory.recycle(context);
               context._factory = null;
            }

         }

      }
   }

   protected static void setConcurrentContext(ConcurrentContext context) {
      CURRENT.set(context);
   }

   final AllocatorContext getAllocatorContext() {
      return this._allocator == null ? AllocatorContext.getDefault() : this._allocator;
   }

   private static final class Root extends Context {
      private Root() {
      }

      protected void enterAction() {
         throw new UnsupportedOperationException("Cannot enter the root context");
      }

      protected void exitAction() {
         throw new UnsupportedOperationException("Cannot enter the root context");
      }
   }
}
