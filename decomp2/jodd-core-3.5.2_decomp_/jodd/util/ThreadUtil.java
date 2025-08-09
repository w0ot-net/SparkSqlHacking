package jodd.util;

public class ThreadUtil {
   public static void sleep(long ms) {
      try {
         Thread.sleep(ms);
      } catch (InterruptedException var3) {
      }

   }

   public static void sleep() {
      try {
         Thread.sleep(Long.MAX_VALUE);
      } catch (InterruptedException var1) {
      }

   }

   public static void wait(Object obj) {
      synchronized(obj) {
         try {
            obj.wait();
         } catch (InterruptedException var4) {
         }

      }
   }

   public static void wait(Object obj, long timeout) {
      synchronized(obj) {
         try {
            obj.wait(timeout);
         } catch (InterruptedException var6) {
         }

      }
   }

   public static void notify(Object obj) {
      synchronized(obj) {
         obj.notify();
      }
   }

   public static void notifyAll(Object obj) {
      synchronized(obj) {
         obj.notifyAll();
      }
   }

   public static void join(Thread thread) {
      try {
         thread.join();
      } catch (InterruptedException var2) {
      }

   }

   public static void join(Thread thread, long millis) {
      try {
         thread.join(millis);
      } catch (InterruptedException var4) {
      }

   }

   public static void join(Thread thread, long millis, int nanos) {
      try {
         thread.join(millis, nanos);
      } catch (InterruptedException var5) {
      }

   }
}
