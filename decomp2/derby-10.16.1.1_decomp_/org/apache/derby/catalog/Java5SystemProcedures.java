package org.apache.derby.catalog;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassFactoryContext;
import org.apache.derby.iapi.sql.dictionary.OptionalTool;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

public class Java5SystemProcedures {
   private static final int TOOL_NAME = 0;
   private static final int TOOL_CLASS_NAME = 1;
   private static final String CUSTOM_TOOL_CLASS_NAME = "customTool";
   private static final String[][] OPTIONAL_TOOLS = new String[][]{{"databaseMetaData", "org.apache.derby.impl.tools.optional.DBMDWrapper"}, {"foreignViews", "org.apache.derby.impl.tools.optional.ForeignDBViews"}, {"luceneSupport", "org.apache.derby.optional.lucene.LuceneSupport"}, {"optimizerTracing", "org.apache.derby.impl.sql.compile.OptimizerTracer"}, {"optimizerTracingViews", "org.apache.derby.impl.sql.compile.OptTraceViewer"}, {"rawDBReader", "org.apache.derby.optional.dump.RawDBReader"}, {"simpleJson", "org.apache.derby.optional.json.SimpleJsonTool"}};

   public static void SYSCS_REGISTER_TOOL(String var0, boolean var1, String... var2) throws SQLException {
      try {
         ClassFactoryContext var3 = (ClassFactoryContext)getContext("ClassFactoryContext");
         ClassFactory var4 = var3.getClassFactory();
         String var5 = findToolClassName(var0, var2);
         OptionalTool var6 = null;

         Class var7;
         try {
            var7 = var4.loadApplicationClass(var5);
         } catch (ClassNotFoundException var13) {
            throw wrap(var13);
         }

         if (!OptionalTool.class.isAssignableFrom(var7)) {
            throw badCustomTool(var5);
         } else {
            try {
               var6 = (OptionalTool)var7.getConstructor().newInstance();
            } catch (InstantiationException var9) {
               throw wrap(var9);
            } catch (IllegalAccessException var10) {
               throw wrap(var10);
            } catch (NoSuchMethodException var11) {
               throw wrap(var11);
            } catch (InvocationTargetException var12) {
               throw wrap(var12);
            }

            if ("customTool".equals(var0)) {
               var2 = stripCustomClassName(var2);
            }

            if (var1) {
               var6.loadTool(var2);
            } else {
               var6.unloadTool(var2);
            }

         }
      } catch (StandardException var14) {
         throw PublicAPI.wrapStandardException(var14);
      }
   }

   private static String findToolClassName(String var0, String... var1) throws StandardException {
      if ("customTool".equals(var0)) {
         if (var1 != null && var1.length != 0) {
            return var1[0];
         } else {
            throw badTool("null");
         }
      } else {
         for(String[] var5 : OPTIONAL_TOOLS) {
            if (var5[0].equals(var0)) {
               return var5[1];
            }
         }

         throw badTool(var0);
      }
   }

   private static StandardException badTool(String var0) {
      return StandardException.newException("X0Y88.S", new Object[]{var0});
   }

   private static StandardException badCustomTool(String var0) {
      return StandardException.newException("X0Y88.S.1", new Object[]{var0});
   }

   private static String[] stripCustomClassName(String... var0) {
      int var1 = var0.length - 1;
      String[] var2 = new String[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = var0[var3 + 1];
      }

      return var2;
   }

   private static StandardException wrap(Throwable var0) {
      return StandardException.plainWrapException(var0);
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
