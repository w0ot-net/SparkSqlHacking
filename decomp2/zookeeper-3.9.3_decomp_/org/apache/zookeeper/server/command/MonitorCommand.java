package org.apache.zookeeper.server.command;

import java.io.PrintWriter;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerMetrics;

public class MonitorCommand extends AbstractFourLetterCommand {
   MonitorCommand(PrintWriter pw, ServerCnxn serverCnxn) {
      super(pw, serverCnxn);
   }

   public void commandRun() {
      if (!this.isZKServerRunning()) {
         this.pw.println("This ZooKeeper instance is not currently serving requests");
      } else {
         this.zkServer.dumpMonitorValues(this::print);
         ServerMetrics.getMetrics().getMetricsProvider().dump(this::print);
      }
   }

   private void print(String key, Object value) {
      if (value == null) {
         this.output(key, (String)null);
      } else if (!(value instanceof Long) && !(value instanceof Integer)) {
         if (value instanceof Number) {
            this.output(key, ((Number)value).doubleValue() + "");
         } else {
            this.output(key, value.toString());
         }
      } else {
         this.output(key, value + "");
      }

   }

   private void output(String key, String value) {
      this.pw.print("zk_");
      this.pw.print(key);
      this.pw.print("\t");
      this.pw.println(value);
   }
}
