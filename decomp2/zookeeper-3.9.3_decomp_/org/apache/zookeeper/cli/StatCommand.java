package org.apache.zookeeper.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

public class StatCommand extends CliCommand {
   private static final Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public StatCommand() {
      super("stat", "[-w] path");
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
         cmdArgs[2] = "-w";
         this.err.println("'stat path [watch]' has been deprecated. Please use 'stat [-w] path' instead.");
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
      boolean watch = this.cl.hasOption("w");

      Stat stat;
      try {
         stat = this.zk.exists(path, watch);
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }

      if (stat == null) {
         throw new CliWrapperException(new KeeperException.NoNodeException(path));
      } else {
         (new StatPrinter(this.out)).print(stat);
         return watch;
      }
   }

   static {
      options.addOption("w", false, "watch");
   }
}
