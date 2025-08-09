package org.apache.zookeeper.cli;

import java.nio.charset.StandardCharsets;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.util.ConfigUtils;

public class GetConfigCommand extends CliCommand {
   private static Options options = new Options();
   private String[] args;
   private CommandLine cl;

   public GetConfigCommand() {
      super("config", "[-c] [-w] [-s]");
   }

   public CliCommand parse(String[] cmdArgs) throws CliParseException {
      DefaultParser parser = new DefaultParser();

      try {
         this.cl = parser.parse(options, cmdArgs);
      } catch (ParseException ex) {
         throw new CliParseException(ex);
      }

      this.args = this.cl.getArgs();
      if (this.args.length < 1) {
         throw new CliParseException(this.getUsageStr());
      } else {
         return this;
      }
   }

   public boolean exec() throws CliException {
      boolean watch = this.cl.hasOption("w");
      Stat stat = new Stat();

      byte[] data;
      try {
         data = this.zk.getConfig(watch, stat);
      } catch (InterruptedException | KeeperException ex) {
         throw new CliWrapperException(ex);
      }

      data = data == null ? "null".getBytes(StandardCharsets.UTF_8) : data;
      if (this.cl.hasOption("c")) {
         this.out.println(ConfigUtils.getClientConfigStr(new String(data, StandardCharsets.UTF_8)));
      } else {
         this.out.println(new String(data, StandardCharsets.UTF_8));
      }

      if (this.cl.hasOption("s")) {
         (new StatPrinter(this.out)).print(stat);
      }

      return watch;
   }

   static {
      options.addOption("s", false, "stats");
      options.addOption("w", false, "watch");
      options.addOption("c", false, "client connection string");
   }
}
