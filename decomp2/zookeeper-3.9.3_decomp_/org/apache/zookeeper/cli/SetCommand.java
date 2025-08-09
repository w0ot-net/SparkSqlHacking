package org.apache.zookeeper.cli;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

public class SetCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public SetCommand() {
      super("set", "path data [-s] [-v version] [-b]", options);
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
      byte[] data;
      if (this.cl.hasOption("b")) {
         data = Base64.getDecoder().decode(this.args[2]);
      } else {
         data = this.args[2].getBytes(StandardCharsets.UTF_8);
      }

      int version;
      if (this.cl.hasOption("v")) {
         version = Integer.parseInt(this.cl.getOptionValue("v"));
      } else {
         version = -1;
      }

      try {
         Stat stat = this.zk.setData(path, data, version);
         if (this.cl.hasOption("s")) {
            (new StatPrinter(this.out)).print(stat);
         }

         return false;
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }
   }

   static {
      options.addOption("s", false, "Print znode stats additionally");
      options.addOption("v", true, "Set with an expected version");
      options.addOption("b", false, "Supply data in base64 format");
   }
}
