package org.apache.hive.beeline;

import java.sql.SQLException;
import java.sql.Statement;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class SunSignalHandler implements BeeLineSignalHandler, SignalHandler {
   private Statement stmt = null;
   private final BeeLine beeLine;

   SunSignalHandler(BeeLine beeLine) {
      this.beeLine = beeLine;
      Signal.handle(new Signal("INT"), this);
   }

   public void setStatement(Statement stmt) {
      this.stmt = stmt;
   }

   public void handle(Signal signal) {
      try {
         if (this.stmt != null && !this.stmt.isClosed()) {
            this.beeLine.info(this.beeLine.loc("interrupt-ctrl-c"));
            this.stmt.cancel();
         } else {
            System.exit(127);
         }
      } catch (SQLException var3) {
      }

   }
}
