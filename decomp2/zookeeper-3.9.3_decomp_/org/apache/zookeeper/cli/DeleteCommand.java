package org.apache.zookeeper.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;

public class DeleteCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public DeleteCommand() {
      super("delete", "[-v version] path");
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
         this.retainCompatibility(cmdArgs);
         return this;
      }
   }

   private void retainCompatibility(String[] cmdArgs) throws CliParseException {
      if (this.args.length > 2) {
         this.err.println("'delete path [version]' has been deprecated. Please use 'delete [-v version] path' instead.");
         DefaultParser parser = new DefaultParser();

         try {
            this.cl = parser.parse(options, cmdArgs);
         } catch (ParseException ex) {
            throw new CliParseException(ex);
         }

         this.args = this.cl.getArgs();
      }

   }

   public boolean exec() throws CliException {
      String path = this.args[1];
      int version;
      if (this.cl.hasOption("v")) {
         version = Integer.parseInt(this.cl.getOptionValue("v"));
      } else {
         version = -1;
      }

      try {
         this.zk.delete(path, version);
         return false;
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }
   }

   static {
      options.addOption("v", true, "version");
   }
}
