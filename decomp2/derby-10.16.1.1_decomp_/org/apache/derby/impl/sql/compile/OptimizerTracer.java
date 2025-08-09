package org.apache.derby.impl.sql.compile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import org.apache.derby.iapi.db.OptimizerTrace;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassFactoryContext;
import org.apache.derby.iapi.sql.compile.OptTrace;
import org.apache.derby.iapi.sql.dictionary.OptionalTool;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public class OptimizerTracer implements OptionalTool {
   public void loadTool(String... var1) throws SQLException {
      Object var2;
      if (var1 != null && var1.length != 0) {
         if ("xml".equals(var1[0])) {
            try {
               var2 = new XMLOptTrace();
            } catch (Throwable var13) {
               throw this.wrap(var13);
            }
         } else {
            if (!"custom".equals(var1[0])) {
               throw this.wrap(MessageService.getTextMessage("X0Y89.S", new Object[0]));
            }

            if (var1.length != 2) {
               throw this.wrap(MessageService.getTextMessage("X0Y89.S", new Object[0]));
            }

            String var3 = var1[1];

            try {
               ClassFactoryContext var4 = (ClassFactoryContext)getContext("ClassFactoryContext");
               ClassFactory var5 = var4.getClassFactory();
               Class var6 = var5.loadApplicationClass(var3);
               var2 = (OptTrace)var6.getConstructor().newInstance();
            } catch (InstantiationException var7) {
               throw this.cantInstantiate(var3);
            } catch (ClassNotFoundException var8) {
               throw this.cantInstantiate(var3);
            } catch (IllegalAccessException var9) {
               throw this.cantInstantiate(var3);
            } catch (NoSuchMethodException var10) {
               throw this.cantInstantiate(var3);
            } catch (InvocationTargetException var11) {
               throw this.cantInstantiate(var3);
            } catch (Throwable var12) {
               throw this.wrap(var12);
            }
         }
      } else {
         var2 = new DefaultOptTrace();
      }

      OptimizerTrace.setOptimizerTracer((OptTrace)var2);
   }

   private SQLException cantInstantiate(String var1) {
      return this.wrap(MessageService.getTextMessage("X0Y90.S", new Object[]{var1}));
   }

   public void unloadTool(String... var1) throws SQLException {
      try {
         OptTrace var2 = OptimizerTrace.getOptimizerTracer();
         boolean var3 = false;
         PrintWriter var4;
         if (var1 != null && var1.length > 0) {
            try {
               String var5 = var1[0];
               File var6 = new File(var5);
               if (var6.exists()) {
                  throw PublicAPI.wrapStandardException(StandardException.newException("XIE0S.S", new Object[]{var5}));
               }

               var4 = new PrintWriter(var6);
            } catch (IOException var10) {
               throw this.wrap((Throwable)(new IllegalArgumentException(var10.getMessage(), var10)));
            }

            var3 = true;
         } else {
            var4 = new PrintWriter(System.out);
         }

         if (var2 != null) {
            var2.printToWriter(var4);
            var4.flush();
         }

         if (var3) {
            var4.close();
         }
      } finally {
         OptimizerTrace.setOptimizerTracer((OptTrace)null);
      }

   }

   private SQLException wrap(Throwable var1) {
      return new SQLException(var1.getMessage(), var1);
   }

   private SQLException wrap(String var1) {
      String var2 = "XJ001.U".substring(0, 5);
      return new SQLException(var1, var2);
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
