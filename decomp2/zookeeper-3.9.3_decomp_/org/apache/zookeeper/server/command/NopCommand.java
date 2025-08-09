package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;

public class NopCommand extends AbstractFourLetterCommand {
   private String msg;

   public NopCommand(PrintWriter pw, ServerCnxn serverCnxn, String msg) {
      super(pw, serverCnxn);
      this.msg = msg;
   }

   public void commandRun() {
      this.pw.println(this.msg);
   }
}
