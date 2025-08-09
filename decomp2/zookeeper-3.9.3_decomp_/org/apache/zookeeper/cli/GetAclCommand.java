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

public class GetAclCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public GetAclCommand() {
      super("getAcl", "[-s] path");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      try {
         this.cl = parser.parse(options, cmdArgs);
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
      Stat stat = new Stat();

      List<ACL> acl;
      try {
         acl = this.zk.getACL(path, stat);
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }

      for(ACL a : acl) {
         this.out.println(a.getId() + ": " + ZKUtil.getPermString(a.getPerms()));
      }

      if (this.cl.hasOption("s")) {
         (new StatPrinter(this.out)).print(stat);
      }

      return false;
   }

   static {
      options.addOption("s", false, "stats");
   }
}
