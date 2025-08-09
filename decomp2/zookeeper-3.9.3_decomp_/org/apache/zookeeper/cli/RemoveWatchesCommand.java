package org.apache.zookeeper.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;

public class RemoveWatchesCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public RemoveWatchesCommand() {
      super("removewatches", "path [-c|-d|-a] [-l]");
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

   public boolean exec() throws CliWrapperException, MalformedPathException {
      String path = this.args[1];
      Watcher.WatcherType wtype = Watcher.WatcherType.Any;
      if (this.cl.hasOption("c")) {
         wtype = Watcher.WatcherType.Children;
      } else if (this.cl.hasOption("d")) {
         wtype = Watcher.WatcherType.Data;
      } else if (this.cl.hasOption("p")) {
         wtype = Watcher.WatcherType.Persistent;
      } else if (this.cl.hasOption("r")) {
         wtype = Watcher.WatcherType.PersistentRecursive;
      } else if (this.cl.hasOption("a")) {
         wtype = Watcher.WatcherType.Any;
      }

      boolean local = this.cl.hasOption("l");

      try {
         this.zk.removeAllWatches(path, wtype, local);
         return true;
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }
   }

   static {
      options.addOption("c", false, "child watcher type");
      options.addOption("d", false, "data watcher type");
      options.addOption("p", false, "persistent watcher type");
      options.addOption("r", false, "persistent recursive watcher type");
      options.addOption("a", false, "any watcher type");
      options.addOption("l", false, "remove locally when there is no server connection");
   }
}
