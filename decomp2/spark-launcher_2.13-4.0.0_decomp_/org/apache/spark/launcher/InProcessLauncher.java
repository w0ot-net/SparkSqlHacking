package org.apache.spark.launcher;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.logging.Logger;

public class InProcessLauncher extends AbstractLauncher {
   private static final Logger LOG = Logger.getLogger(InProcessLauncher.class.getName());

   public SparkAppHandle startApplication(SparkAppHandle.Listener... listeners) throws IOException {
      if (this.builder.isClientMode(this.builder.getEffectiveConfig())) {
         LOG.warning("It's not recommended to run client-mode applications using InProcessLauncher.");
      }

      Method main = this.findSparkSubmit();
      LauncherServer server = LauncherServer.getOrCreateServer();
      InProcessAppHandle handle = new InProcessAppHandle(server);

      for(SparkAppHandle.Listener l : listeners) {
         handle.addListener(l);
      }

      String secret = server.registerHandle(handle);
      this.setConf("spark.launcher.port", String.valueOf(server.getPort()));
      this.setConf("spark.launcher.secret", secret);
      List<String> sparkArgs = this.builder.buildSparkSubmitArgs();
      String[] argv = (String[])sparkArgs.toArray(new String[sparkArgs.size()]);
      String appName = CommandBuilderUtils.firstNonEmpty(this.builder.appName, this.builder.mainClass, "<unknown>");
      handle.start(appName, main, argv);
      return handle;
   }

   InProcessLauncher self() {
      return this;
   }

   Method findSparkSubmit() throws IOException {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      if (cl == null) {
         cl = this.getClass().getClassLoader();
      }

      Class<?> sparkSubmit;
      try {
         sparkSubmit = cl.loadClass("org.apache.spark.deploy.InProcessSparkSubmit");
      } catch (Exception var7) {
         try {
            sparkSubmit = cl.loadClass("org.apache.spark.deploy.SparkSubmit");
         } catch (Exception e2) {
            throw new IOException("Cannot find SparkSubmit; make sure necessary jars are available.", e2);
         }
      }

      Method main;
      try {
         main = sparkSubmit.getMethod("main", String[].class);
      } catch (Exception e) {
         throw new IOException("Cannot find SparkSubmit main method.", e);
      }

      CommandBuilderUtils.checkState(Modifier.isStatic(main.getModifiers()), "main method is not static.");
      return main;
   }
}
