package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;

public class RuokCommand extends AbstractFourLetterCommand {
   public RuokCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      this.pw.print("imok");
   }
}
