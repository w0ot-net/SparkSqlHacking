package org.apache.zookeeper.cli;

import java.util.Arrays;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.KeeperException;

public class AddWatchCommand extends CliCommand {
   private static final Options options = new Options();
   private static final AddWatchMode defaultMode;
   private CommandLine cl;
   private AddWatchMode mode;

   public AddWatchCommand() {
      super("addWatch", "[-m mode] path # optional mode is one of " + Arrays.toString(AddWatchMode.values()) + " - default is " + defaultMode.name());
      this.mode = defaultMode;
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      try {
         this.cl = parser.parse(options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      if (this.cl.getArgs().length != 2) {
         throw new CliParseException(this.getUsageStr());
      } else {
         if (this.cl.hasOption("m")) {
            try {
               this.mode = AddWatchMode.valueOf(this.cl.getOptionValue("m").toUpperCase());
            } catch (IllegalArgumentException var4) {
               throw new CliParseException(this.getUsageStr());
            }
         }

         return this;
      }
   }

   public boolean exec() throws CliException {
      String path = this.cl.getArgs()[1];

      try {
         this.zk.addWatch(path, this.mode);
         return false;
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }
   }

   static {
      defaultMode = AddWatchMode.PERSISTENT_RECURSIVE;
      options.addOption("m", true, "");
   }
}
