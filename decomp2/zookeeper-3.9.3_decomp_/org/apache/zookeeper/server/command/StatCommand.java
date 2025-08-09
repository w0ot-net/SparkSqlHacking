package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.Version;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerStats;
import org.apache.zookeeper.server.quorum.BufferStats;
import org.apache.zookeeper.server.quorum.Leader;
import org.apache.zookeeper.server.quorum.LeaderZooKeeperServer;
import org.apache.zookeeper.server.quorum.ReadOnlyZooKeeperServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatCommand extends AbstractFourLetterCommand {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractFourLetterCommand.class);
   private int len;

   public StatCommand(PrintWriter pw, ServerCnxn serverCnxn, int len) {
      super(pw, serverCnxn);
      this.len = len;
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.println("This ZooKeeper instance is not currently serving requests");
      } else {
         this.pw.print("Zookeeper version: ");
         this.pw.println(Version.getFullVersion());
         if (this.zkServer instanceof ReadOnlyZooKeeperServer) {
            this.pw.println("READ-ONLY mode; serving only read-only clients");
         }

         if (this.len == FourLetterCommands.statCmd) {
            LOG.info("Stat command output");
            this.pw.println("Clients:");

            for(ServerCnxn c : this.factory.getConnections()) {
               c.dumpConnectionInfo(this.pw, true);
               this.pw.println();
            }

            this.pw.println();
         }

         ServerStats serverStats = this.zkServer.serverStats();
         this.pw.print(serverStats.toString());
         this.pw.print("Node count: ");
         this.pw.println(this.zkServer.getZKDatabase().getNodeCount());
         if (serverStats.getServerState().equals("leader")) {
            Leader leader = ((LeaderZooKeeperServer)this.zkServer).getLeader();
            BufferStats proposalStats = leader.getProposalStats();
            this.pw.printf("Proposal sizes last/min/max: %s%n", proposalStats.toString());
         }
      }

   }
}
