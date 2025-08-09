package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import java.util.List;
import org.apache.zookeeper.Environment;
import org.apache.zookeeper.server.ServerCnxn;

public class EnvCommand extends AbstractFourLetterCommand {
   EnvCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      List<Environment.Entry> env = Environment.list();
      this.pw.println("Environment:");

      for(Environment.Entry e : env) {
         this.pw.print(e.getKey());
         this.pw.print("=");
         this.pw.println(e.getValue());
      }

   }
}
