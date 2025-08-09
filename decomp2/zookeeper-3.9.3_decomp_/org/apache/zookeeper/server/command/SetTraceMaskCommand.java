package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;

public class SetTraceMaskCommand extends AbstractFourLetterCommand {
   long trace = 0L;

   public SetTraceMaskCommand(PrintWriter pw, ServerCnxn serverCnxn, long trace) {
      super(pw, serverCnxn);
      this.trace = trace;
   }

   public void commandRun() {
      this.pw.print(this.trace);
   }
}
