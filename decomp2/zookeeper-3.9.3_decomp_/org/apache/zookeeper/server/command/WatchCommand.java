package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.ServerCnxn;

public class WatchCommand extends AbstractFourLetterCommand {
   int len = 0;

   public WatchCommand(PrintWriter pw, ServerCnxn serverCnxn, int len) {
      super(pw, serverCnxn);
      this.len = len;
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.println("This ZooKeeper instance is not currently serving requests");
      } else {
         DataTree dt = this.zkServer.getZKDatabase().getDataTree();
         if (this.len == FourLetterCommands.wchsCmd) {
            dt.dumpWatchesSummary(this.pw);
         } else if (this.len == FourLetterCommands.wchpCmd) {
            dt.dumpWatches(this.pw, true);
         } else {
            dt.dumpWatches(this.pw, false);
         }

         this.pw.println();
      }

   }
}
