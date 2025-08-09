package org.apache.zookeeper.cli;

import java.io.PrintStream;
import java.util.Date;
import org.apache.zookeeper.data.Stat;

public class StatPrinter {
   protected PrintStream out;

   public StatPrinter(PrintStream out) {
      this.out = out;
   }

   public void print(Stat stat) {
      this.out.println("cZxid = 0x" + Long.toHexString(stat.getCzxid()));
      this.out.println("ctime = " + (new Date(stat.getCtime())).toString());
      this.out.println("mZxid = 0x" + Long.toHexString(stat.getMzxid()));
      this.out.println("mtime = " + (new Date(stat.getMtime())).toString());
      this.out.println("pZxid = 0x" + Long.toHexString(stat.getPzxid()));
      this.out.println("cversion = " + stat.getCversion());
      this.out.println("dataVersion = " + stat.getVersion());
      this.out.println("aclVersion = " + stat.getAversion());
      this.out.println("ephemeralOwner = 0x" + Long.toHexString(stat.getEphemeralOwner()));
      this.out.println("dataLength = " + stat.getDataLength());
      this.out.println("numChildren = " + stat.getNumChildren());
   }
}
