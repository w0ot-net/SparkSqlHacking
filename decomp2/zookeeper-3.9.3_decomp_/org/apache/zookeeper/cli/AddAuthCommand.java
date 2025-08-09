package org.apache.zookeeper.cli;

import java.nio.charset.StandardCharsets;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class AddAuthCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;

   public AddAuthCommand() {
      super("addauth", "scheme auth");
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
      byte[] b = null;
      if (this.args.length >= 3) {
         b = this.args[2].getBytes(StandardCharsets.UTF_8);
      }

      this.zk.addAuthInfo(this.args[1], b);
      return false;
   }
}
