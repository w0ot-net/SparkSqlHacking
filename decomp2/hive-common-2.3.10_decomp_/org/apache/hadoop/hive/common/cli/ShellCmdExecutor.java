package org.apache.hadoop.hive.common.cli;

import java.io.IOException;
import java.io.PrintStream;
import org.apache.hive.common.util.StreamPrinter;

public class ShellCmdExecutor {
   private String cmd;
   private PrintStream out;
   private PrintStream err;

   public ShellCmdExecutor(String cmd, PrintStream out, PrintStream err) {
      this.cmd = cmd;
      this.out = out;
      this.err = err;
   }

   public int execute() throws Exception {
      try {
         Process executor = Runtime.getRuntime().exec(this.cmd);
         StreamPrinter outPrinter = new StreamPrinter(executor.getInputStream(), (String)null, new PrintStream[]{this.out});
         StreamPrinter errPrinter = new StreamPrinter(executor.getErrorStream(), (String)null, new PrintStream[]{this.err});
         outPrinter.start();
         errPrinter.start();
         int ret = executor.waitFor();
         outPrinter.join();
         errPrinter.join();
         return ret;
      } catch (IOException ex) {
         throw new Exception("Failed to execute " + this.cmd, ex);
      }
   }
}
