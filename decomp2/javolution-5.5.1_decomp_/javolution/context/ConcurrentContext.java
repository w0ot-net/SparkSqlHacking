package javolution.context;

import javolution.lang.Configurable;
import javolution.lang.MathLib;
import javolution.lang.Reflection;

public abstract class ConcurrentContext extends Context {
   public static final Configurable MAXIMUM_CONCURRENCY = new Configurable(new Integer(availableProcessors() - 1)) {
      protected void notifyChange(Object oldValue, Object newValue) {
         ConcurrentContext.CONCURRENCY.setDefault(newValue);
      }
   };
   public static final Configurable DEFAULT = new Configurable(Default.class) {
   };
   private static final LocalContext.Reference CONCURRENCY;

   private static int availableProcessors() {
      Reflection.Method availableProcessors = Reflection.getInstance().getMethod("java.lang.Runtime.availableProcessors()");
      if (availableProcessors != null) {
         Integer processors = (Integer)availableProcessors.invoke(Runtime.getRuntime());
         return processors;
      } else {
         return 1;
      }
   }

   protected ConcurrentContext() {
   }

   public static void enter() {
      Context.enter((Class)DEFAULT.get());
   }

   public static void enter(boolean condition) {
      if (condition) {
         enter();
      }

   }

   public static void exit() {
      Context.exit(ConcurrentContext.class);
   }

   public static void exit(boolean condition) {
      if (condition) {
         exit();
      }

   }

   public static void setConcurrency(int concurrency) {
      concurrency = MathLib.max(0, concurrency);
      concurrency = MathLib.min((Integer)MAXIMUM_CONCURRENCY.get(), concurrency);
      CONCURRENCY.set(new Integer(concurrency));
   }

   public static int getConcurrency() {
      return (Integer)CONCURRENCY.get();
   }

   public static void execute(Runnable logic) {
      ConcurrentContext ctx = (ConcurrentContext)Context.getCurrentContext();
      ctx.executeAction(logic);
   }

   public static void execute(Runnable... logics) {
      enter();
      ConcurrentContext ctx = (ConcurrentContext)getCurrentContext();

      try {
         for(int i = 0; i < logics.length; ++i) {
            ctx.executeAction(logics[i]);
         }
      } finally {
         exit();
      }

   }

   protected abstract void executeAction(Runnable var1);

   static {
      CONCURRENCY = new LocalContext.Reference(MAXIMUM_CONCURRENCY.get());
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Default();
         }
      }, Default.class);
   }

   static final class Default extends ConcurrentContext {
      private static final ConcurrentThread[] _Executors;
      private int _concurrency;
      private volatile Throwable _error;
      private int _initiated;
      private int _completed;

      protected void enterAction() {
         this._concurrency = ConcurrentContext.getConcurrency();
      }

      protected void executeAction(Runnable logic) {
         if (this._error == null) {
            int i = this._concurrency;

            do {
               --i;
               if (i < 0) {
                  logic.run();
                  return;
               }
            } while(!_Executors[i].execute(logic, this));

            ++this._initiated;
         }
      }

      protected void exitAction() {
         try {
            if (this._initiated != 0) {
               synchronized(this) {
                  while(this._initiated != this._completed) {
                     try {
                        this.wait();
                     } catch (InterruptedException e) {
                        throw new ConcurrentException(e);
                     }
                  }
               }
            }

            if (this._error != null) {
               if (this._error instanceof RuntimeException) {
                  throw (RuntimeException)this._error;
               }

               if (this._error instanceof Error) {
                  throw (Error)this._error;
               }

               throw new ConcurrentException(this._error);
            }
         } finally {
            this._error = null;
            this._initiated = 0;
            this._completed = 0;
         }

      }

      void started() {
         Context.setConcurrentContext(this);
      }

      void completed() {
         synchronized(this) {
            ++this._completed;
            this.notify();
         }

         AllocatorContext.getCurrentAllocatorContext().deactivate();
      }

      void error(Throwable error) {
         synchronized(this) {
            if (this._error == null) {
               this._error = error;
            }

         }
      }

      static {
         _Executors = new ConcurrentThread[(Integer)MAXIMUM_CONCURRENCY.get()];

         for(int i = 0; i < _Executors.length; ++i) {
            _Executors[i] = new ConcurrentThread();
            _Executors[i].start();
         }

      }
   }
}
