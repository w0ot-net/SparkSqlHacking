package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;

public class CnxnStatResetCommand extends AbstractFourLetterCommand {
   public CnxnStatResetCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.println("This ZooKeeper instance is not currently serving requests");
      } else {
         this.factory.resetAllConnectionStats();
         this.pw.println("Connection stats reset.");
      }

   }
}
