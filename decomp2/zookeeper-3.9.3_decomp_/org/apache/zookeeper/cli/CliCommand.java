package org.apache.zookeeper.cli;

import java.io.PrintStream;
import java.util.Map;
import javax.annotation.Nullable;
import org.apache.commons.cli.Options;
import org.apache.zookeeper.ZooKeeper;

public abstract class CliCommand {
   protected ZooKeeper zk;
   protected PrintStream out;
   protected PrintStream err;
   private String cmdStr;
   private String optionStr;
   @Nullable
   private Options options;

   public CliCommand(String cmdStr, String optionStr) {
      this(cmdStr, optionStr, (Options)null);
   }

   public CliCommand(String cmdStr, String optionStr, Options options) {
      this.out = System.out;
      this.err = System.err;
      this.cmdStr = cmdStr;
      this.optionStr = optionStr;
      this.options = options;
   }

   public void setOut(PrintStream out) {
      this.out = out;
   }

   public void setErr(PrintStream err) {
      this.err = err;
   }

   public void setZk(ZooKeeper zk) {
      this.zk = zk;
   }

   public String getCmdStr() {
      return this.cmdStr;
   }

   public String getOptionStr() {
      return this.optionStr;
   }

   public String getUsageStr() {
      return CommandUsageHelper.getUsage(this.cmdStr + " " + this.optionStr, this.options);
   }

   public void addToMap(Map cmdMap) {
      cmdMap.put(this.cmdStr, this);
   }

   public abstract CliCommand parse(String[] var1) throws CliParseException;

   public abstract boolean exec() throws CliException;
}
