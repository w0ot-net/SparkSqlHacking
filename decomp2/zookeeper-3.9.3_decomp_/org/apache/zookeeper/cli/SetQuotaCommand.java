package org.apache.zookeeper.cli;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Quotas;
import org.apache.zookeeper.StatsTrack;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetQuotaCommand extends CliCommand {
   private static final Logger LOG = LoggerFactory.getLogger(SetQuotaCommand.class);
   private Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public SetQuotaCommand() {
      super("setquota", "-n|-b|-N|-B val path");
      OptionGroup og1 = new OptionGroup();
      og1.addOption(new Option("n", true, "num soft quota"));
      og1.addOption(new Option("b", true, "bytes soft quota"));
      og1.addOption(new Option("N", true, "num hard quota"));
      og1.addOption(new Option("B", true, "bytes hard quota"));
      og1.setRequired(true);
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
      if (path.startsWith("/zookeeper/quota")) {
         this.err.println("cannot set a quota under the path: /zookeeper/quota");
         return false;
      } else {
         StatsTrack quota = new StatsTrack();
         quota.setCount(-1L);
         quota.setBytes(-1L);
         quota.setCountHardLimit(-1L);
         quota.setByteHardLimit(-1L);
         if (!this.checkOptionValue(quota)) {
            return false;
         } else {
            boolean flagSet = this.cl.hasOption("n") || this.cl.hasOption("N") || this.cl.hasOption("b") || this.cl.hasOption("B");
            if (flagSet) {
               try {
                  createQuota(this.zk, path, quota);
               } catch (IllegalArgumentException ex) {
                  throw new MalformedPathException(ex.getMessage());
               } catch (InterruptedException | KeeperException ex) {
                  throw new CliWrapperException(ex);
               }
            } else {
               this.err.println(this.getUsageStr());
            }

            return false;
         }
      }
   }

   private boolean checkOptionValue(StatsTrack quota) {
      try {
         if (this.cl.hasOption("n")) {
            int count = Integer.parseInt(this.cl.getOptionValue("n"));
            if (count <= 0) {
               this.err.println("the num quota must be greater than zero");
               return false;
            }

            quota.setCount((long)count);
         }

         if (this.cl.hasOption("b")) {
            long bytes = Long.parseLong(this.cl.getOptionValue("b"));
            if (bytes < 0L) {
               this.err.println("the bytes quota must be greater than or equal to zero");
               return false;
            }

            quota.setBytes(bytes);
         }

         if (this.cl.hasOption("N")) {
            int count = Integer.parseInt(this.cl.getOptionValue("N"));
            if (count <= 0) {
               this.err.println("the num quota must be greater than zero");
               return false;
            }

            quota.setCountHardLimit((long)count);
         }

         if (this.cl.hasOption("B")) {
            long bytes = Long.parseLong(this.cl.getOptionValue("B"));
            if (bytes < 0L) {
               this.err.println("the bytes quota must be greater than or equal to zero");
               return false;
            }

            quota.setByteHardLimit(bytes);
         }

         return true;
      } catch (NumberFormatException var4) {
         this.err.println("NumberFormatException happens when parsing the option value");
         return false;
      }
   }

   public static boolean createQuota(ZooKeeper zk, String path, StatsTrack quota) throws KeeperException, InterruptedException, MalformedPathException {
      Stat initStat;
      try {
         initStat = zk.exists(path, false);
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      }

      if (initStat == null) {
         throw new IllegalArgumentException(path + " does not exist.");
      } else {
         String quotaPath = "/zookeeper/quota";
         checkIfChildQuota(zk, path);
         checkIfParentQuota(zk, path);
         if (zk.exists(quotaPath, false) == null) {
            try {
               zk.create("/zookeeper", (byte[])null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
               zk.create("/zookeeper/quota", (byte[])null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } catch (KeeperException.NodeExistsException var13) {
            }
         }

         String[] splits = path.split("/");
         StringBuilder sb = new StringBuilder();
         sb.append(quotaPath);

         for(int i = 1; i < splits.length; ++i) {
            sb.append("/").append(splits[i]);
            quotaPath = sb.toString();
            if (zk.exists(quotaPath, false) == null) {
               try {
                  zk.create(quotaPath, (byte[])null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
               } catch (KeeperException.NodeExistsException var12) {
               }
            }
         }

         String statPath = quotaPath + "/" + "zookeeper_stats";
         quotaPath = quotaPath + "/" + "zookeeper_limits";
         if (zk.exists(quotaPath, false) == null) {
            zk.create(quotaPath, quota.getStatsBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            StatsTrack stats = new StatsTrack();
            stats.setCount(0L);
            stats.setBytes(0L);
            zk.create(statPath, stats.getStatsBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            byte[] data = zk.getData(quotaPath, false, new Stat());
            StatsTrack quotaStrack = new StatsTrack(data);
            data = zk.getData(statPath, false, new Stat());
            StatsTrack statStrack = new StatsTrack(data);
            checkQuota(quotaStrack, statStrack);
         } else {
            byte[] data = zk.getData(quotaPath, false, new Stat());
            StatsTrack quotaStrack = new StatsTrack(data);
            if (quota.getCount() > -1L) {
               quotaStrack.setCount(quota.getCount());
            }

            if (quota.getBytes() > -1L) {
               quotaStrack.setBytes(quota.getBytes());
            }

            if (quota.getCountHardLimit() > -1L) {
               quotaStrack.setCountHardLimit(quota.getCountHardLimit());
            }

            if (quota.getByteHardLimit() > -1L) {
               quotaStrack.setByteHardLimit(quota.getByteHardLimit());
            }

            data = zk.getData(statPath, false, new Stat());
            StatsTrack statStrack = new StatsTrack(data);
            checkQuota(quotaStrack, statStrack);
            zk.setData(quotaPath, quotaStrack.getStatsBytes(), -1);
         }

         return true;
      }
   }

   private static void checkQuota(StatsTrack quotaStrack, StatsTrack statStrack) {
      if (quotaStrack.getCount() > -1L && quotaStrack.getCount() < statStrack.getCount() || quotaStrack.getCountHardLimit() > -1L && quotaStrack.getCountHardLimit() < statStrack.getCount()) {
         System.out.println("[Warning]: the count quota you create is less than the existing count:" + statStrack.getCount());
      }

      if (quotaStrack.getBytes() > -1L && quotaStrack.getBytes() < statStrack.getBytes() || quotaStrack.getByteHardLimit() > -1L && quotaStrack.getByteHardLimit() < statStrack.getBytes()) {
         System.out.println("[Warning]: the bytes quota you create is less than the existing bytes:" + statStrack.getBytes());
      }

   }

   private static void checkIfChildQuota(ZooKeeper zk, String path) throws KeeperException, InterruptedException {
      String realPath = Quotas.quotaPath(path);

      try {
         ZKUtil.visitSubTreeDFS(zk, realPath, false, (rc, quotaPath, ctx, name) -> {
            List<String> children = new ArrayList();

            try {
               children = zk.getChildren(quotaPath, false);
            } catch (KeeperException.NoNodeException ne) {
               LOG.debug("child removed during quota check", ne);
               return;
            } catch (KeeperException | InterruptedException e) {
               ((Exception)e).printStackTrace();
            }

            if (children.size() != 0) {
               for(String child : children) {
                  if (!quotaPath.equals("/zookeeper/quota" + path) && "zookeeper_limits".equals(child)) {
                     throw new IllegalArgumentException(path + " has a child " + Quotas.trimQuotaPath(quotaPath) + " which has a quota");
                  }
               }

            }
         });
      } catch (KeeperException.NoNodeException var4) {
      }

   }

   private static void checkIfParentQuota(ZooKeeper zk, String path) throws InterruptedException, KeeperException {
      String[] splits = path.split("/");
      String quotaPath = "/zookeeper/quota";
      StringBuilder sb = new StringBuilder();
      sb.append(quotaPath);

      for(int i = 1; i < splits.length - 1; ++i) {
         sb.append("/");
         sb.append(splits[i]);
         quotaPath = sb.toString();
         List<String> children = null;

         try {
            children = zk.getChildren(quotaPath, false);
         } catch (KeeperException.NoNodeException ne) {
            LOG.debug("child removed during quota check", ne);
            return;
         }

         if (children.size() == 0) {
            return;
         }

         for(String child : children) {
            if (!quotaPath.equals(Quotas.quotaPath(path)) && "zookeeper_limits".equals(child)) {
               throw new IllegalArgumentException(path + " has a parent " + Quotas.trimQuotaPath(quotaPath) + " which has a quota");
            }
         }
      }

   }
}
