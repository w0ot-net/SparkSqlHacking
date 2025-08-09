package org.apache.derby.impl.services.monitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.derby.shared.common.info.ProductVersionHolder;

public final class FileMonitor extends BaseMonitor {
   private File home;
   private ProductVersionHolder engineVersion;
   private static final Map securityProperties = new HashMap();

   public FileMonitor() {
      this.initialize(true);
      this.applicationProperties = this.readApplicationProperties();
   }

   public FileMonitor(Properties var1, PrintWriter var2) {
      this.runWithState(var1, var2);
   }

   private InputStream PBapplicationPropertiesStream() throws IOException {
      File var1 = new File(this.home, "derby.properties");
      return !var1.exists() ? null : new FileInputStream(var1);
   }

   public Object getEnvironment() {
      return this.home;
   }

   private ThreadGroup createDaemonGroup() {
      ThreadGroup var1 = new ThreadGroup("derby.daemons");
      var1.setDaemon(true);
      return var1;
   }

   private boolean PBinitialize(boolean var1) {
      if (!var1) {
         this.daemonGroup = this.createDaemonGroup();
      }

      InputStream var2 = this.getClass().getResourceAsStream("/org/apache/derby/info/engine/info.properties");
      this.engineVersion = ProductVersionHolder.getProductVersionHolderFromMyEnv(var2);
      String var3 = System.getProperty("derby.system.home");
      if (var3 != null) {
         this.home = new File(var3);
         if (this.home.exists()) {
            if (!this.home.isDirectory()) {
               this.report("derby.system.home=" + var3 + " does not represent a directory");
               return false;
            }
         } else if (!var1) {
            try {
               boolean var4 = false;
               var4 = this.home.mkdir() || this.home.mkdirs();
               if (var4) {
                  FileUtil.limitAccessToOwner(this.home);
               }
            } catch (IOException var5) {
               return false;
            }
         }
      }

      return true;
   }

   private String PBgetJVMProperty(String var1) {
      return System.getProperty(var1);
   }

   final boolean initialize(boolean var1) {
      return Boolean.valueOf(this.PBinitialize(var1));
   }

   final Properties getDefaultModuleProperties() {
      return access$001(this);
   }

   public final String getJVMProperty(String var1) {
      return !var1.startsWith("derby.") ? this.PBgetJVMProperty(var1) : this.PBgetJVMProperty(var1);
   }

   public final synchronized Thread getDaemonThread(Runnable var1, String var2, boolean var3) {
      try {
         return access$101(this, var1, var2, var3);
      } catch (IllegalThreadStateException var5) {
         if (this.daemonGroup != null && this.daemonGroup.isDestroyed()) {
            this.daemonGroup = this.createDaemonGroup();
            return access$201(this, var1, var2, var3);
         } else {
            throw var5;
         }
      }
   }

   final InputStream applicationPropertiesStream() throws IOException {
      return this.PBapplicationPropertiesStream();
   }

   public final ProductVersionHolder getEngineVersion() {
      return this.engineVersion;
   }

   // $FF: synthetic method
   static Properties access$001(FileMonitor var0) {
      return var0.getDefaultModuleProperties();
   }

   // $FF: synthetic method
   static Thread access$101(FileMonitor var0, Runnable var1, String var2, boolean var3) {
      return var0.getDaemonThread(var1, var2, var3);
   }

   // $FF: synthetic method
   static Thread access$201(FileMonitor var0, Runnable var1, String var2, boolean var3) {
      return var0.getDaemonThread(var1, var2, var3);
   }

   static {
      securityProperties.put("derby.authentication.builtin.algorithm", (Object)null);
      securityProperties.put("derby.authentication.provider", (Object)null);
      securityProperties.put("derby.database.fullAccessUsers", (Object)null);
      securityProperties.put("derby.database.readOnlyAccessUsers", (Object)null);
      securityProperties.put("derby.database.sqlAuthorization", (Object)null);
      securityProperties.put("derby.connection.requireAuthentication", (Object)null);
      securityProperties.put("derby.database.defaultConnectionMode", (Object)null);
      securityProperties.put("derby.storage.useDefaultFilePermissions", (Object)null);
      securityProperties.put("derby.system.home", (Object)null);
   }
}
