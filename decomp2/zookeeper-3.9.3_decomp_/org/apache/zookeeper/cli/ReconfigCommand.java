package org.apache.zookeeper.cli;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.admin.ZooKeeperAdmin;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

public class ReconfigCommand extends CliCommand {
   private static Options options = new Options();
   private String joining;
   private String leaving;
   private String members;
   long version = -1L;
   private CommandLine cl;

   public ReconfigCommand() {
      super("reconfig", "[-s] [-v version] [[-file path] | [-members serverID=host:port1:port2;port3[,...]*]] | [-add serverId=host:port1:port2;port3[,...]]* [-remove serverId[,...]*]");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      this.joining = null;
      this.leaving = null;
      this.members = null;
      DefaultParser parser = new DefaultParser();

      try {
         this.cl = parser.parse(options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      if (!this.cl.hasOption("file") && !this.cl.hasOption("members") && !this.cl.hasOption("add") && !this.cl.hasOption("remove")) {
         throw new CliParseException(this.getUsageStr());
      } else {
         if (this.cl.hasOption("v")) {
            try {
               this.version = Long.parseLong(this.cl.getOptionValue("v"), 16);
            } catch (NumberFormatException var10) {
               throw new CliParseException("-v must be followed by a long (configuration version)");
            }
         } else {
            this.version = -1L;
         }

         if (!this.cl.hasOption("file") && !this.cl.hasOption("members") || !this.cl.hasOption("add") && !this.cl.hasOption("remove")) {
            if (this.cl.hasOption("file") && this.cl.hasOption("members")) {
               throw new CliParseException("Can't use -file and -members together (conflicting non-incremental modes)");
            } else {
               if (this.cl.hasOption("add")) {
                  this.joining = this.cl.getOptionValue("add").toLowerCase();
               }

               if (this.cl.hasOption("remove")) {
                  this.leaving = this.cl.getOptionValue("remove").toLowerCase();
               }

               if (this.cl.hasOption("members")) {
                  this.members = this.cl.getOptionValue("members").toLowerCase();
               }

               if (this.cl.hasOption("file")) {
                  try {
                     Properties dynamicCfg = new Properties();
                     FileInputStream inConfig = new FileInputStream(this.cl.getOptionValue("file"));

                     try {
                        dynamicCfg.load(inConfig);
                     } catch (Throwable var8) {
                        try {
                           inConfig.close();
                        } catch (Throwable var7) {
                           var8.addSuppressed(var7);
                        }

                        throw var8;
                     }

                     inConfig.close();
                     this.members = QuorumPeerConfig.parseDynamicConfig(dynamicCfg, 0, true, false, (String)null).toString();
                  } catch (Exception e) {
                     throw new CliParseException("Error processing " + this.cl.getOptionValue("file") + e.getMessage());
                  }
               }

               return this;
            }
         } else {
            throw new CliParseException("Can't use -file or -members together with -add or -remove (mixing incremental and non-incremental modes is not allowed)");
         }
      }
   }

   public boolean exec() throws CliException {
      try {
         Stat stat = new Stat();
         if (!(this.zk instanceof ZooKeeperAdmin)) {
            return false;
         } else {
            byte[] curConfig = ((ZooKeeperAdmin)this.zk).reconfigure(this.joining, this.leaving, this.members, this.version, stat);
            this.out.println("Committed new configuration:\n" + new String(curConfig, StandardCharsets.UTF_8));
            if (this.cl.hasOption("s")) {
               (new StatPrinter(this.out)).print(stat);
            }

            return false;
         }
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }
   }

   static {
      options.addOption("s", false, "stats");
      options.addOption("v", true, "required current config version");
      options.addOption("file", true, "path of config file to parse for membership");
      options.addOption("members", true, "comma-separated list of config strings for non-incremental reconfig");
      options.addOption("add", true, "comma-separated list of config strings for new servers");
      options.addOption("remove", true, "comma-separated list of server IDs to remove");
   }
}
