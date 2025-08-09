package org.apache.zookeeper.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

public class GetCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public GetCommand() {
      super("get", "[-s] [-w] [-b] [-x] path", options);
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
         this.err.println("'get path [watch]' has been deprecated. Please use 'get [-s] [-w] path' instead.");
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
      boolean watch = this.cl.hasOption("w");
      String path = this.args[1];
      Stat stat = new Stat();

      byte[] data;
      try {
         data = this.zk.getData(path, watch, stat);
      } catch (IllegalArgumentException ex) {
         throw new MalformedPathException(ex.getMessage());
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }

      OutputFormatter formatter = PlainOutputFormatter.INSTANCE;
      if (this.cl.hasOption("b")) {
         formatter = Base64OutputFormatter.INSTANCE;
      }

      if (this.cl.hasOption("x")) {
         formatter = HexDumpOutputFormatter.INSTANCE;
      }

      data = data == null ? "null".getBytes() : data;
      this.out.println(formatter.format(data));
      if (this.cl.hasOption("s")) {
         (new StatPrinter(this.out)).print(stat);
      }

      return watch;
   }

   static {
      options.addOption("s", false, "Print znode stats additionally");
      options.addOption("w", false, "Watch for changes on the znode");
      options.addOption("b", false, "Output data in base64 format");
      options.addOption("x", false, "Output data in hexdump format");
   }
}
