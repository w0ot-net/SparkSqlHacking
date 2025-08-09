package org.apache.zookeeper.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZKUtil;

public class DeleteAllCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public DeleteAllCommand() {
      this("deleteall");
   }

   public DeleteAllCommand(String cmdStr) {
      super(cmdStr, "path [-b batch size]");
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
      int batchSize;
      try {
         batchSize = this.cl.hasOption("b") ? Integer.parseInt(this.cl.getOptionValue("b")) : 1000;
      } catch (NumberFormatException var6) {
         throw new MalformedCommandException("-b argument must be an int value");
      }

      String path = this.args[1];

      try {
         boolean success = ZKUtil.deleteRecursive(this.zk, path, batchSize);
         if (!success) {
            this.err.println("Failed to delete some node(s) in the subtree!");
         }

         return false;
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }
   }

   static {
      options.addOption(new Option("b", true, "batch size"));
   }
}
