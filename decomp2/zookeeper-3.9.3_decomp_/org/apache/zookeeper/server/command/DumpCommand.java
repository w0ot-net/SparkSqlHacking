package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ServerCnxn;

public class DumpCommand extends AbstractFourLetterCommand {
   public DumpCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.println("This ZooKeeper instance is not currently serving requests");
      } else {
         this.pw.println("SessionTracker dump:");
         this.zkServer.getSessionTracker().dumpSessions(this.pw);
         this.pw.println("ephemeral nodes dump:");
         this.zkServer.dumpEphemerals(this.pw);
         this.pw.println("Connections dump:");
         if (this.factory instanceof NIOServerCnxnFactory) {
            ((NIOServerCnxnFactory)this.factory).dumpConnections(this.pw);
         }
      }

   }
}
