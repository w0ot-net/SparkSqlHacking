package org.apache.derby.iapi.services.context;

import java.util.HashSet;
import java.util.Stack;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.shared.common.error.ShutdownException;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;

public final class ContextService {
   private static ContextService factory;
   private HeaderPrintWriter errorStream = Monitor.getStream();
   private ThreadLocal threadContextList = new ThreadLocal();
   private HashSet allContexts;

   public ContextService() {
      factory = this;
      this.allContexts = new HashSet();
   }

   public static void stop() {
      SecurityUtil.checkDerbyInternalsPrivilege();
      ContextService var0 = factory;
      if (var0 != null) {
         synchronized(var0) {
            var0.allContexts = null;
            var0.threadContextList = null;
            factory = null;
         }
      }

   }

   public static ContextService getFactory() {
      SecurityUtil.checkDerbyInternalsPrivilege();
      ContextService var0 = factory;
      if (var0 == null) {
         throw new ShutdownException();
      } else {
         return var0;
      }
   }

   public static Context getContext(String var0) {
      SecurityUtil.checkDerbyInternalsPrivilege();
      ContextManager var1 = getFactory().getCurrentContextManager();
      return var1 == null ? null : var1.getContext(var0);
   }

   public static Context getContextOrNull(String var0) {
      SecurityUtil.checkDerbyInternalsPrivilege();
      ContextService var1 = factory;
      if (var1 == null) {
         return null;
      } else {
         ContextManager var2 = var1.getCurrentContextManager();
         return var2 == null ? null : var2.getContext(var0);
      }
   }

   public ContextManager getCurrentContextManager() {
      ThreadLocal var1 = this.threadContextList;
      if (var1 == null) {
         return null;
      } else {
         Object var2 = var1.get();
         if (var2 instanceof ContextManager) {
            Thread var3 = Thread.currentThread();
            ContextManager var4 = (ContextManager)var2;
            return var4.activeThread == var3 ? var4 : null;
         } else {
            return var2 == null ? null : (ContextManager)((ContextManagerStack)var2).peek();
         }
      }
   }

   public void resetCurrentContextManager(ContextManager var1) {
      ThreadLocal var2 = this.threadContextList;
      if (var2 != null) {
         if (var1.activeCount != -1) {
            if (--var1.activeCount == 0) {
               var1.activeThread = null;
               if (var1.isEmpty()) {
                  var2.set((Object)null);
               }
            }

         } else {
            ContextManagerStack var3 = (ContextManagerStack)var2.get();
            var3.pop();
            ContextManager var4 = (ContextManager)var3.peek();
            boolean var5 = false;
            boolean var6 = false;

            for(ContextManager var8 : var3) {
               if (var8 != var4) {
                  var5 = true;
               }

               if (var8 == var1) {
                  var6 = true;
               }
            }

            if (!var6) {
               var1.activeThread = null;
               var1.activeCount = 0;
            }

            if (!var5) {
               var4.activeCount = var3.size();
               var2.set(var4);
            }

         }
      }
   }

   private boolean addToThreadList(Thread var1, ContextManager var2) {
      ThreadLocal var3 = this.threadContextList;
      if (var3 == null) {
         return false;
      } else {
         Object var4 = var3.get();
         if (var2 == var4) {
            return true;
         } else if (var4 == null) {
            var3.set(var2);
            return true;
         } else {
            ContextManagerStack var5;
            if (var4 instanceof ContextManager) {
               ContextManager var6 = (ContextManager)var4;
               if (var1 == null) {
                  var1 = Thread.currentThread();
               }

               if (var6.activeThread != var1) {
                  var3.set(var2);
                  return true;
               }

               var5 = new ContextManagerStack();
               var3.set(var5);

               for(int var7 = 0; var7 < var6.activeCount; ++var7) {
                  var5.push(var6);
               }

               var6.activeCount = -1;
            } else {
               var5 = (ContextManagerStack)var4;
            }

            var5.push(var2);
            var2.activeCount = -1;
            return false;
         }
      }
   }

   public void setCurrentContextManager(ContextManager var1) {
      Thread var2 = null;
      if (var1.activeThread == null) {
         var1.activeThread = var2 = Thread.currentThread();
      }

      if (this.addToThreadList(var2, var1)) {
         ++var1.activeCount;
      }

   }

   public ContextManager newContextManager() {
      ContextManager var1 = new ContextManager(this, this.errorStream);
      new SystemContext(var1);
      synchronized(this) {
         this.allContexts.add(var1);
         return var1;
      }
   }

   public void notifyAllActiveThreads(Context var1) {
      Thread var2 = Thread.currentThread();
      synchronized(this) {
         for(ContextManager var5 : this.allContexts) {
            Thread var6 = var5.activeThread;
            if (var6 != var2 && var6 != null && var5.setInterrupted(var1)) {
               var6.interrupt();
            }
         }

      }
   }

   synchronized void removeContext(ContextManager var1) {
      if (this.allContexts != null) {
         this.allContexts.remove(var1);
      }

   }

   private static class ContextManagerStack extends Stack {
   }
}
