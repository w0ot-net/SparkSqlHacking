package org.apache.zookeeper.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Objects;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServiceUtils {
   private static final Logger LOG = LoggerFactory.getLogger(ServiceUtils.class);
   @SuppressFBWarnings({"DM_EXIT"})
   public static final Consumer SYSTEM_EXIT = (code) -> {
      String msg = "Exiting JVM with code {}";
      if (code == 0) {
         LOG.info(msg, code);
      } else {
         LOG.error(msg, code);
      }

      System.exit(code);
   };
   public static final Consumer LOG_ONLY = (code) -> {
      if (code != 0) {
         LOG.error("Fatal error, JVM should exit with code {}. Actually System.exit is disabled", code);
      } else {
         LOG.info("JVM should exit with code {}. Actually System.exit is disabled", code);
      }

   };
   private static volatile Consumer systemExitProcedure;

   private ServiceUtils() {
   }

   public static void setSystemExitProcedure(Consumer systemExitProcedure) {
      Objects.requireNonNull(systemExitProcedure);
      ServiceUtils.systemExitProcedure = systemExitProcedure;
   }

   public static void requestSystemExit(int code) {
      systemExitProcedure.accept(code);
   }

   static {
      systemExitProcedure = SYSTEM_EXIT;
   }
}
