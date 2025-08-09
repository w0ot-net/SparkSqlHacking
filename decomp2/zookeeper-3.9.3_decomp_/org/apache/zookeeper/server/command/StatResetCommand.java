package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerStats;
import org.apache.zookeeper.server.quorum.LeaderZooKeeperServer;

public class StatResetCommand extends AbstractFourLetterCommand {
   public StatResetCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.println("This ZooKeeper instance is not currently serving requests");
      } else {
         ServerStats serverStats = this.zkServer.serverStats();
         serverStats.reset();
         if (serverStats.getServerState().equals("leader")) {
            ((LeaderZooKeeperServer)this.zkServer).getLeader().getProposalStats().reset();
         }

         this.pw.println("Server stats reset.");
      }

   }
}
