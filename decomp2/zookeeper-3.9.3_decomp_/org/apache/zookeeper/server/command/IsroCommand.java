package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.quorum.ReadOnlyZooKeeperServer;

public class IsroCommand extends AbstractFourLetterCommand {
   public IsroCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.print("null");
      } else if (this.zkServer instanceof ReadOnlyZooKeeperServer) {
         this.pw.print("ro");
      } else {
         this.pw.print("rw");
      }

   }
}
