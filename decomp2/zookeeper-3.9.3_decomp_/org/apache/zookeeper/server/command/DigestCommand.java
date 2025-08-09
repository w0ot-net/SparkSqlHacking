package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.ServerCnxn;

public class DigestCommand extends AbstractFourLetterCommand {
   public DigestCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.print("This ZooKeeper instance is not currently serving requests");
      } else {
         for(DataTree.ZxidDigest zd : this.zkServer.getZKDatabase().getDataTree().getDigestLog()) {
            this.pw.println(Long.toHexString(zd.getZxid()) + ": " + zd.getDigest());
         }
      }

   }
}
