package org.apache.zookeeper.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;

public class GetAllChildrenNumberCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;

   public GetAllChildrenNumberCommand() {
      super("getAllChildrenNumber", "path");
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
      if (this.args.length < 2) {
         throw new MalformedCommandException(this.getUsageStr());
      } else {
         try {
            String path = this.args[1];
            int allChildrenNumber = this.zk.getAllChildrenNumber(path);
            this.out.println(allChildrenNumber);
            return false;
         } catch (IllegalArgumentException ex) {
            throw new MalformedPathException(ex.getMessage());
         } catch (InterruptedException | KeeperException ex) {
            throw new CliWrapperException(ex);
         }
      }
   }
}
