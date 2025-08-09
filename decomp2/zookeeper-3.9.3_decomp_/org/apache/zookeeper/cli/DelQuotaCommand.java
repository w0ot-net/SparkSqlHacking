package org.apache.zookeeper.cli;

import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Quotas;
import org.apache.zookeeper.StatsTrack;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class DelQuotaCommand extends CliCommand {
   private Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public DelQuotaCommand() {
      super("delquota", "[-n|-b|-N|-B] path");
      OptionGroup og1 = new OptionGroup();
      og1.addOption(new Option("n", false, "num soft quota"));
      og1.addOption(new Option("b", false, "bytes soft quota"));
      og1.addOption(new Option("N", false, "num hard quota"));
      og1.addOption(new Option("B", false, "bytes hard quota"));
      this.options.addOptionGroup(og1);
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      try {
         this.cl = parser.parse(this.options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      this.args = this.cl.getArgs();
      if (this.args.length < 2) {
         throw new CliParseException(this.getUsageStr());
      } else {
         return this;
      }
   }

   public boolean exec() throws CliException {
      String path = this.args[1];
      StatsTrack quota = new StatsTrack();
      if (this.cl.hasOption("n")) {
         quota.setCount(1L);
      }

      if (this.cl.hasOption("b")) {
         quota.setBytes(1L);
      }

      if (this.cl.hasOption("N")) {
         quota.setCountHardLimit(1L);
      }

      if (this.cl.hasOption("B")) {
         quota.setByteHardLimit(1L);
      }

      boolean flagSet = this.cl.hasOption("n") || this.cl.hasOption("N") || this.cl.hasOption("b") || this.cl.hasOption("B");

      try {
         delQuota(this.zk, path, flagSet ? quota : null);
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (KeeperException.NoNodeException var6) {
         this.err.println("quota for " + path + " does not exist.");
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }

      return false;
   }

   public static boolean delQuota(ZooKeeper zk, String path, StatsTrack quota) throws KeeperException, InterruptedException, MalformedPathException {
      String parentPath = Quotas.quotaPath(path);
      String quotaPath = Quotas.limitPath(path);
      if (zk.exists(quotaPath, false) == null) {
         System.out.println("Quota does not exist for " + path);
         return true;
      } else {
         byte[] data = null;

         try {
            data = zk.getData(quotaPath, false, new Stat());
         } catch (IllegalArgumentException ex) {
            throw new MalformedPathException(ex.getMessage());
         } catch (KeeperException.NoNodeException ne) {
            throw new KeeperException.NoNodeException(ne.getMessage());
         }

         StatsTrack strack = new StatsTrack(data);
         if (quota == null) {
            for(String child : zk.getChildren(parentPath, false)) {
               zk.delete(parentPath + "/" + child, -1);
            }

            trimProcQuotas(zk, parentPath);
         } else {
            if (quota.getCount() > 0L) {
               strack.setCount(-1L);
            }

            if (quota.getBytes() > 0L) {
               strack.setBytes(-1L);
            }

            if (quota.getCountHardLimit() > 0L) {
               strack.setCountHardLimit(-1L);
            }

            if (quota.getByteHardLimit() > 0L) {
               strack.setByteHardLimit(-1L);
            }

            zk.setData(quotaPath, strack.getStatsBytes(), -1);
         }

         return true;
      }
   }

   private static boolean trimProcQuotas(ZooKeeper zk, String path) throws KeeperException, InterruptedException {
      if ("/zookeeper/quota".equals(path)) {
         return true;
      } else {
         List<String> children = zk.getChildren(path, false);
         if (children.size() == 0) {
            zk.delete(path, -1);
            String parent = path.substring(0, path.lastIndexOf(47));
            return trimProcQuotas(zk, parent);
         } else {
            return true;
         }
      }
   }
}
