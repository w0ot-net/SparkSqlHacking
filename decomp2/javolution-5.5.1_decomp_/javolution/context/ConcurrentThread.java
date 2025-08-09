package javolution.context;

import javax.realtime.MemoryArea;
import javax.realtime.RealtimeThread;
import javolution.lang.Reflection;

class ConcurrentThread extends RealtimeThread {
   private volatile Runnable _logic;
   private MemoryArea _memoryArea;
   private int _priority;
   private ConcurrentContext.Default _context;
   private boolean _terminate;
   private String _name = "ConcurrentThread-" + this.getCount();
   private Thread _parent;
   private static int _Count;
   private static final Reflection.Method SET_NAME = Reflection.getInstance().getMethod("java.lang.Thread.setName(String)");
   private static final Reflection.Method SET_DAEMON = Reflection.getInstance().getMethod("java.lang.Thread.setDaemon(boolean)");

   public ConcurrentThread() {
      if (SET_NAME != null) {
         SET_NAME.invoke(this, (Object)this._name);
      }

      if (SET_DAEMON != null) {
         SET_DAEMON.invoke(this, (Object)Boolean.TRUE);
      }

   }

   private synchronized int getCount() {
      return _Count++;
   }

   public void run() {
      while(true) {
         synchronized(this) {
            try {
               while(this._logic == null && !this._terminate) {
                  this.wait();
               }
            } catch (InterruptedException e) {
               throw new ConcurrentException(e);
            }
         }

         if (this._terminate) {
            return;
         }

         try {
            Thread current = Thread.currentThread();
            if (current.getPriority() != this._priority) {
               current.setPriority(this._priority);
            }

            this._context.started();
            this._memoryArea.executeInArea(this._logic);
         } catch (Throwable error) {
            this._context.error(error);
         } finally {
            this._context.completed();
            this._parent = null;
            this._context = null;
            this._logic = null;
         }
      }
   }

   public boolean execute(Runnable logic, ConcurrentContext.Default context) {
      if (this._logic != null) {
         return false;
      } else {
         synchronized(this) {
            if (this._logic != null) {
               return false;
            } else {
               this._memoryArea = RealtimeThread.getCurrentMemoryArea();
               this._parent = Thread.currentThread();
               this._priority = this._parent.getPriority();
               this._context = context;
               this._logic = logic;
               this.notify();
               return true;
            }
         }
      }
   }

   public void terminate() {
      synchronized(this) {
         this._terminate = true;
         this.notify();
      }
   }

   public String toString() {
      return this._name + " from " + this.getSource();
   }

   public Thread getSource() {
      return this._parent instanceof ConcurrentThread ? ((ConcurrentThread)this._parent).getSource() : this._parent;
   }
}
