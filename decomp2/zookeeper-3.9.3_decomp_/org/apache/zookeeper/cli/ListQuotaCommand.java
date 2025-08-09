package org.apache.zookeeper.cli;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Quotas;
import org.apache.zookeeper.StatsTrack;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ListQuotaCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;

   public ListQuotaCommand() {
      super("listquota", "path");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      CommandLine cl;
      try {
         cl = parser.parse(options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      this.args = cl.getArgs();
      if (this.args.length < 2) {
         throw new CliParseException(this.getUsageStr());
      } else {
         return this;
      }
   }

   public boolean exec() throws CliException {
      String path = this.args[1];
      String absolutePath = Quotas.limitPath(path);

      try {
         this.err.println("absolute path is " + absolutePath);
         List<StatsTrack> statsTracks = listQuota(this.zk, path);

         for(int i = 0; i < statsTracks.size(); ++i) {
            StatsTrack st = (StatsTrack)statsTracks.get(i);
            if (i == 0) {
               this.out.println("Output quota for " + path + " " + st.toString());
            } else {
               this.out.println("Output stat for " + path + " " + st.toString());
            }
         }
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (KeeperException.NoNodeException var7) {
         this.err.println("quota for " + path + " does not exist.");
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }

      return false;
   }

   public static List listQuota(ZooKeeper zk, String path) throws KeeperException, InterruptedException {
      List<StatsTrack> statsTracks = new ArrayList();
      Stat stat = new Stat();
      byte[] data = zk.getData(Quotas.limitPath(path), false, stat);
      StatsTrack st = new StatsTrack(data);
      statsTracks.add(st);
      data = zk.getData(Quotas.statPath(path), false, stat);
      st = new StatsTrack(data);
      statsTracks.add(st);
      return statsTracks;
   }
}
