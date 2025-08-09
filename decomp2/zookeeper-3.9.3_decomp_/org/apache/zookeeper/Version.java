package org.apache.zookeeper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.util.ServiceUtils;
import org.apache.zookeeper.version.Info;

public class Version implements Info {
   /** @deprecated */
   @Deprecated
   public static int getRevision() {
      return -1;
   }

   public static String getRevisionHash() {
      return "c26634f34490bb0ea7a09cc51e05ede3b4e320ee";
   }

   public static String getBuildDate() {
      return "2024-10-17 23:21 UTC";
   }

   @SuppressFBWarnings(
      value = {"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"},
      justification = "Missing QUALIFIER causes redundant null-check"
   )
   public static String getVersion() {
      return "3.9.3" + (QUALIFIER == null ? "" : "-" + QUALIFIER);
   }

   public static String getVersionRevision() {
      return getVersion() + "-" + getRevisionHash();
   }

   public static String getFullVersion() {
      return getVersionRevision() + ", built on " + getBuildDate();
   }

   public static void printUsage() {
      System.out.print("Usage:\tjava -cp ... org.apache.zookeeper.Version [--full | --short | --revision],\n\tPrints --full version info if no arg specified.");
      ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
   }

   public static void main(String[] args) {
      if (args.length > 1) {
         printUsage();
      }

      if (args.length == 0 || args.length == 1 && args[0].equals("--full")) {
         System.out.println(getFullVersion());
         ServiceUtils.requestSystemExit(ExitCode.EXECUTION_FINISHED.getValue());
      }

      if (args[0].equals("--short")) {
         System.out.println(getVersion());
      } else if (args[0].equals("--revision")) {
         System.out.println(getVersionRevision());
      } else {
         printUsage();
      }

      ServiceUtils.requestSystemExit(ExitCode.EXECUTION_FINISHED.getValue());
   }
}
