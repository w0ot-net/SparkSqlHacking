package org.apache.derby.shared.common.reference;

import java.io.InputStream;
import java.util.HashMap;
import java.util.ServiceLoader;
import org.apache.derby.shared.api.DerbyModuleAPI;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.info.JVMInfo;

public class ModuleUtil {
   public static final String ENGINE_MODULE_NAME = "org.apache.derby.engine";
   public static final String CLIENT_MODULE_NAME = "org.apache.derby.client";
   public static final String SERVER_MODULE_NAME = "org.apache.derby.server";
   public static final String RUNNER_MODULE_NAME = "org.apache.derby.runner";
   public static final String SHARED_MODULE_NAME = "org.apache.derby.commons";
   public static final String TOOLS_MODULE_NAME = "org.apache.derby.tools";
   public static final String OPTIONALTOOLS_MODULE_NAME = "org.apache.derby.optionaltools";
   public static final String TESTING_MODULE_NAME = "org.apache.derby.tests";
   public static final String LOCALE_MODULE_NAME_PREFIX = "org.apache.derby.locale_";
   private static HashMap _derbyModules;

   public static java.lang.Module derbyModule(String var0) {
      if (!JVMInfo.isModuleAware()) {
         return null;
      } else {
         initModuleInfo();
         return (java.lang.Module)_derbyModules.get(var0);
      }
   }

   public static String localizationModuleName(String var0) {
      return "org.apache.derby.locale_" + var0;
   }

   public static InputStream getResourceAsStream(String var0) throws StandardException {
      initModuleInfo();
      InputStream var1 = null;

      for(java.lang.Module var3 : _derbyModules.values()) {
         var1 = getResourceAsStream(var3, var0);
         if (var1 != null) {
            break;
         }
      }

      return var1;
   }

   private static void initModuleInfo() {
      if (_derbyModules == null) {
         HashMap var0 = new HashMap();

         for(DerbyModuleAPI var3 : ServiceLoader.load(DerbyModuleAPI.class)) {
            Class var4 = var3.getClass();
            java.lang.Module var5 = var4.getModule();
            var0.put(var5.getName(), var5);
         }

         _derbyModules = var0;
      }
   }

   private static InputStream getResourceAsStream(java.lang.Module var0, String var1) throws StandardException {
      InputStream var2 = null;
      Exception var3 = null;

      try {
         var2 = var0.getResourceAsStream(var1);
      } catch (Exception var5) {
         var3 = var5;
      }

      if (var3 != null) {
         throw StandardException.plainWrapException(var3);
      } else {
         return var2;
      }
   }
}
