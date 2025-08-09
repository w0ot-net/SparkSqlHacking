package org.apache.zookeeper.cli;

import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class SetAclCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public SetAclCommand() {
      super("setAcl", "[-s] [-v version] [-R] path acl");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      try {
         this.cl = parser.parse(options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      this.args = this.cl.getArgs();
      if (this.args.length < 3) {
         throw new CliParseException(this.getUsageStr());
      } else {
         return this;
      }
   }

   public boolean exec() throws CliException {
      String path = this.args[1];
      String aclStr = this.args[2];
      List<ACL> acl = AclParser.parse(aclStr);
      int version;
      if (this.cl.hasOption("v")) {
         version = Integer.parseInt(this.cl.getOptionValue("v"));
      } else {
         version = -1;
      }

      try {
         if (this.cl.hasOption("R")) {
            ZKUtil.visitSubTreeDFS(this.zk, path, false, (rc, p, ctx, name) -> {
               try {
                  this.zk.setACL(p, acl, version);
               } catch (InterruptedException | KeeperException e) {
                  this.out.print(((Exception)e).getMessage());
               }

            });
         } else {
            Stat stat = this.zk.setACL(path, acl, version);
            if (this.cl.hasOption("s")) {
               (new StatPrinter(this.out)).print(stat);
            }
         }

         return false;
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }
   }

   static {
      options.addOption("s", false, "stats");
      options.addOption("v", true, "version");
      options.addOption("R", false, "recursive");
   }
}
