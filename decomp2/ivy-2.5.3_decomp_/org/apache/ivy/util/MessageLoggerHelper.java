package org.apache.ivy.util;

import java.util.ArrayList;
import java.util.List;

public final class MessageLoggerHelper {
   public static void sumupProblems(MessageLogger logger) {
      if (!logger.getProblems().isEmpty()) {
         List<String> warns = new ArrayList(logger.getWarns());
         List<String> errors = new ArrayList(logger.getErrors());
         logger.info("");
         if (!errors.isEmpty()) {
            logger.log(":: problems summary ::", 0);
         } else {
            logger.log(":: problems summary ::", 1);
         }

         if (warns.size() > 0) {
            logger.log(":::: WARNINGS", 1);

            for(String msg : warns) {
               logger.log("\t" + msg + "\n", 1);
            }
         }

         if (errors.size() > 0) {
            logger.log(":::: ERRORS", 0);

            for(String msg : errors) {
               logger.log("\t" + msg + "\n", 0);
            }
         }

         logger.info("\n:: USE VERBOSE OR DEBUG MESSAGE LEVEL FOR MORE DETAILS");
      }
   }

   private MessageLoggerHelper() {
   }
}
