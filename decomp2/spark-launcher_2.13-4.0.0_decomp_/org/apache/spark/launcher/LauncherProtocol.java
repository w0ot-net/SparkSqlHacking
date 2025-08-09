package org.apache.spark.launcher;

import java.io.Serializable;

final class LauncherProtocol {
   static final String ENV_LAUNCHER_PORT = "_SPARK_LAUNCHER_PORT";
   static final String ENV_LAUNCHER_SECRET = "_SPARK_LAUNCHER_SECRET";
   static final String CONF_LAUNCHER_PORT = "spark.launcher.port";
   static final String CONF_LAUNCHER_SECRET = "spark.launcher.secret";

   static class Message implements Serializable {
   }

   static class Hello extends Message {
      final String secret;
      final String sparkVersion;

      Hello(String secret, String version) {
         this.secret = secret;
         this.sparkVersion = version;
      }
   }

   static class SetAppId extends Message {
      final String appId;

      SetAppId(String appId) {
         this.appId = appId;
      }
   }

   static class SetState extends Message {
      final SparkAppHandle.State state;

      SetState(SparkAppHandle.State state) {
         this.state = state;
      }
   }

   static class Stop extends Message {
   }
}
