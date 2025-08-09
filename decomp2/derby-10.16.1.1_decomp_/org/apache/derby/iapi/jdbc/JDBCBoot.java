package org.apache.derby.iapi.jdbc;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.Properties;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.shared.common.error.StandardException;

public class JDBCBoot {
   private Properties bootProperties = new Properties();
   private static final String NETWORK_SERVER_AUTOSTART_CLASS_NAME = "org.apache.derby.iapi.jdbc.DRDAServerStarter";

   void addProperty(String var1, String var2) {
      this.bootProperties.put(var1, var2);
   }

   public static void boot() {
      PrintWriter var0 = DriverManager.getLogWriter();
      if (var0 == null) {
         var0 = new PrintWriter(System.err, true);
      }

      try {
         (new JDBCBoot()).boot("jdbc:derby:", var0);
      } catch (Throwable var2) {
         var2.printStackTrace(var0);
         if (var2 instanceof RuntimeException) {
            throw (RuntimeException)var2;
         }
      }

   }

   public void boot(String var1, PrintWriter var2) {
      synchronized("org.apache.derby.iapi.jdbc.DRDAServerStarter") {
         if (InternalDriver.activeDriver() == null) {
            this.addProperty("derby.service.jdbc", InternalDriver.class.getName());
            this.addProperty("derby.service.authentication", "org.apache.derby.iapi.jdbc.AuthenticationService");
            boot(this.bootProperties, var2);
         }

      }
   }

   private static void boot(Properties var0, PrintWriter var1) {
      Monitor.startMonitor(var0, var1);
      if (Boolean.valueOf(PropertyUtil.getSystemProperty("derby.drda.startNetworkServer"))) {
         try {
            Monitor.startSystemModule("org.apache.derby.iapi.jdbc.DRDAServerStarter");
         } catch (StandardException var3) {
            Monitor.logTextMessage("J102", var3.getMessage());
         }
      }

   }
}
