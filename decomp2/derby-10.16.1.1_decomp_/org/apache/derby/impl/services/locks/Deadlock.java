package org.apache.derby.impl.services.locks;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.TransactionInfo;
import org.apache.derby.shared.common.error.StandardException;

class Deadlock {
   private Deadlock() {
   }

   static Object[] look(AbstractPool var0, LockTable var1, LockControl var2, ActiveLock var3, byte var4) {
      Hashtable var5 = getWaiters(var1);
      Stack var6 = new Stack();
      var6.push(var3.getCompatabilitySpace());
      var6.push(var2.getGrants());

      label71:
      while(!var6.isEmpty()) {
         List var7 = (List)var6.peek();
         if (!var7.isEmpty()) {
            int var8 = var7.size() - 1;
            CompatibilitySpace var9 = ((Lock)var7.get(var8)).getCompatabilitySpace();

            for(int var10 = 0; var10 < var8; ++var10) {
               if (var9.equals(((Lock)var7.get(var10)).getCompatabilitySpace())) {
                  var6.push(var9);
                  rollback(var6);
                  continue label71;
               }
            }

            while(true) {
               int var14 = var6.indexOf(var9);
               if (var14 != -1) {
                  if (var14 != var6.size() - 1 && (var14 != var6.size() - 2 || var14 != var6.indexOf(var7) - 1)) {
                     return handle(var0, var6, var14, var5, var4);
                  }

                  ActiveLock var15 = (ActiveLock)((Dictionary)var5).get(var9);
                  if (!var15.canSkip) {
                     return handle(var0, var6, var14, var5, var4);
                  }

                  var6.push(var9);
                  rollback(var6);
                  break;
               }

               var6.push(var9);

               while(true) {
                  Lock var11 = (Lock)((Dictionary)var5).get(var9);
                  if (var11 == null) {
                     rollback(var6);
                     continue label71;
                  }

                  Object var12 = ((Dictionary)var5).get(var11);
                  if (var12 instanceof LockControl) {
                     LockControl var16 = (LockControl)var12;
                     if (var16.isUnlocked()) {
                        rollback(var6);
                     } else {
                        var6.push(var16.getGrants());
                     }
                     continue label71;
                  }

                  ActiveLock var13 = (ActiveLock)var12;
                  var9 = var13.getCompatabilitySpace();
                  if (!var11.getLockable().requestCompatible(var11.getQualifier(), var13.getQualifier())) {
                     break;
                  }
               }
            }
         } else {
            rollback(var6);
         }
      }

      return null;
   }

   private static void rollback(Stack var0) {
      do {
         var0.pop();
         if (var0.isEmpty()) {
            return;
         }
      } while(!(var0.peek() instanceof List));

      List var1 = (List)var0.peek();
      var1.remove(var1.size() - 1);
   }

   private static Hashtable getWaiters(LockTable var0) {
      Hashtable var1 = new Hashtable();
      var0.addWaiters(var1);
      return var1;
   }

   private static Object[] handle(AbstractPool var0, Stack var1, int var2, Dictionary var3, byte var4) {
      Object var5 = var1.elementAt(0);
      int var6 = Integer.MAX_VALUE;
      Object var7 = null;

      for(int var8 = var2; var8 < var1.size(); ++var8) {
         Object var9 = var1.elementAt(var8);
         if (!(var9 instanceof List)) {
            if (var5.equals(var9) && var4 == 2) {
               var7 = var5;
               break;
            }

            LockSpace var10 = (LockSpace)var9;
            int var11 = var10.deadlockCount(var6);
            if (var11 <= var6) {
               var7 = var9;
               var6 = var11;
            }
         }
      }

      if (var5.equals(var7)) {
         Object[] var13 = new Object[]{var1, var3};
         return var13;
      } else {
         ActiveLock var12 = (ActiveLock)var3.get(var7);
         var12.wakeUp((byte)2);
         return null;
      }
   }

   static StandardException buildException(AbstractPool var0, Object[] var1) {
      Stack var2 = (Stack)var1[0];
      Dictionary var3 = (Dictionary)var1[1];
      LanguageConnectionContext var4 = (LanguageConnectionContext)getContext("LanguageConnectionContext");
      TableNameInfo var5 = null;
      TransactionInfo[] var6 = null;
      TransactionController var7 = null;
      if (var4 != null) {
         try {
            var7 = var4.getTransactionExecute();
            var5 = new TableNameInfo(var4, false);
            var6 = var7.getAccessManager().getTransactionInfo();
         } catch (StandardException var19) {
         }
      }

      StringBuffer var8 = new StringBuffer(200);
      Hashtable var9 = new Hashtable(17);
      String var10 = null;

      for(int var11 = 0; var11 < var2.size(); ++var11) {
         Object var12 = var2.elementAt(var11);
         if (var12 instanceof List var21) {
            if (var21.size() != 0) {
               var8.append("  Granted XID : ");

               for(int var23 = 0; var23 < var21.size(); ++var23) {
                  if (var23 != 0) {
                     var8.append(", ");
                  }

                  Lock var25 = (Lock)var21.get(var23);
                  var8.append("{");
                  var8.append(var25.getCompatabilitySpace().getOwner());
                  var8.append(", ");
                  var8.append(var25.getQualifier());
                  var8.append("} ");
               }

               var8.append('\n');
            }
         } else {
            Lock var13 = (Lock)var3.get(var12);
            var13.getLockable().lockAttributes(-1, var9);
            addInfo(var8, "Lock : ", var9.get("TYPE"));
            if (var5 != null) {
               Long var14 = (Long)var9.get("CONGLOMID");
               if (var14 == null) {
                  Long var15 = (Long)var9.get("CONTAINERID");

                  try {
                     var14 = var7.findConglomid(var15);
                  } catch (StandardException var18) {
                  }
               }

               addInfo(var8, ", ", var5.getTableName(var14));
            }

            addInfo(var8, ", ", var9.get("LOCKNAME"));
            var8.append('\n');
            String var22 = String.valueOf(var13.getCompatabilitySpace().getOwner());
            if (var11 == 0) {
               var10 = var22;
            }

            addInfo(var8, "  Waiting XID : {", var22);
            addInfo(var8, ", ", var13.getQualifier());
            var8.append("} ");
            if (var6 != null) {
               for(int var24 = var6.length - 1; var24 >= 0; --var24) {
                  TransactionInfo var16 = var6[var24];
                  if (var16 != null) {
                     String var17 = var16.getTransactionIdString();
                     if (var17 != null && var17.equals(var22)) {
                        addInfo(var8, ", ", var16.getUsernameString());
                        addInfo(var8, ", ", var16.getStatementTextString());
                        break;
                     }
                  }
               }
            }

            var8.append('\n');
            var9.clear();
         }
      }

      StandardException var20 = StandardException.newException("40001", new Object[]{var8.toString(), var10});
      var20.setReport(var0.deadlockMonitor);
      return var20;
   }

   private static void addInfo(StringBuffer var0, String var1, Object var2) {
      var0.append(var1);
      if (var2 == null) {
         var2 = "?";
      }

      var0.append(var2);
   }

   static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
