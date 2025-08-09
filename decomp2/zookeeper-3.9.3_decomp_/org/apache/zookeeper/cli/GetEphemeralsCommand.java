package org.apache.zookeeper.cli;

import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;

public class GetEphemeralsCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;

   public GetEphemeralsCommand() {
      super("getEphemerals", "path");
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
      return this;
   }

   public boolean exec() throws CliException {
      List<String> ephemerals;
      try {
         if (this.args.length < 2) {
            ephemerals = this.zk.getEphemerals();
         } else {
            String path = this.args[1];
            ephemerals = this.zk.getEphemerals(path);
         }
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }

      this.out.println(ephemerals);
      return false;
   }
}
