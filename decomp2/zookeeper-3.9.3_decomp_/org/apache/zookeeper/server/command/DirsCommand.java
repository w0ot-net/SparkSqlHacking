package org.apache.zookeeper.server.command;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;

public class DirsCommand extends AbstractFourLetterCommand {
   public DirsCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() throws IOException {
      if (!this.isZKServerRunning()) {
         this.pw.println("This ZooKeeper instance is not currently serving requests");
      } else {
         this.pw.println("datadir_size: " + this.zkServer.getDataDirSize());
         this.pw.println("logdir_size: " + this.zkServer.getLogDirSize());
      }
   }
}
