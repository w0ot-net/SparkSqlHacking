package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;

public class ConsCommand extends AbstractFourLetterCommand {
   public ConsCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.println("This ZooKeeper instance is not currently serving requests");
      } else {
         for(ServerCnxn c : this.factory.getConnections()) {
            c.dumpConnectionInfo(this.pw, false);
            this.pw.println();
         }

         this.pw.println();
      }

   }
}
