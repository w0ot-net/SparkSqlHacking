package org.apache.zookeeper.cli;

import java.util.Collections;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZKUtil;
import org.apache.zookeeper.data.Stat;

public class LsCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public LsCommand() {
      super("ls", "[-s] [-w] [-R] path");
   }

   private void printHelp() {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("ls [options] path", options);
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      try {
         this.cl = parser.parse(options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      this.args = this.cl.getArgs();
      if (this.cl.hasOption("?")) {
         this.printHelp();
      }

      this.retainCompatibility(cmdArgs);
      return this;
   }

   private void retainCompatibility(String[] cmdArgs) throws CliParseException {
      if (this.args.length > 2) {
         cmdArgs[2] = "-w";
         this.err.println("'ls path [watch]' has been deprecated. Please use 'ls [-w] path' instead.");
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
      if (this.args.length < 2) {
         throw new MalformedCommandException(this.getUsageStr());
      } else {
         String path = this.args[1];
         boolean watch = this.cl.hasOption("w");
         boolean withStat = this.cl.hasOption("s");
         boolean recursive = this.cl.hasOption("R");

         try {
            if (recursive) {
               ZKUtil.visitSubTreeDFS(this.zk, path, watch, (rc, path1, ctx, name) -> this.out.println(path1));
            } else {
               Stat stat = withStat ? new Stat() : null;
               List<String> children = this.zk.getChildren(path, watch, stat);
               this.printChildren(children, stat);
            }

            return watch;
         } catch (IllegalArgumentException ex) {
            throw new MalformedPathException(ex.getMessage());
         } catch (InterruptedException | KeeperException ex) {
            throw new CliWrapperException(ex);
         }
      }
   }

   private void printChildren(List children, Stat stat) {
      Collections.sort(children);
      this.out.append("[");
      boolean first = true;

      for(String child : children) {
         if (!first) {
            this.out.append(", ");
         } else {
            first = false;
         }

         this.out.append(child);
      }

      this.out.append("]\n");
      if (stat != null) {
         (new StatPrinter(this.out)).print(stat);
      }

   }

   static {
      options.addOption("?", false, "help");
      options.addOption("s", false, "stat");
      options.addOption("w", false, "watch");
      options.addOption("R", false, "recurse");
   }
}
