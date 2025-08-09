package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ZooTrace;

public class TraceMaskCommand extends AbstractFourLetterCommand {
   TraceMaskCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      long traceMask = ZooTrace.getTextTraceLevel();
      this.pw.print(traceMask);
   }
}
