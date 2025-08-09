package org.apache.hive.beeline.cli;

import java.io.IOException;
import java.io.InputStream;
import org.apache.hive.beeline.BeeLine;

public class HiveCli {
   private BeeLine beeLine;

   public static void main(String[] args) throws IOException {
      int status = (new HiveCli()).runWithArgs(args, (InputStream)null);
      System.exit(status);
   }

   public int runWithArgs(String[] cmd, InputStream inputStream) throws IOException {
      this.beeLine = new BeeLine(false);

      int var3;
      try {
         var3 = this.beeLine.begin(cmd, inputStream);
      } finally {
         this.beeLine.close();
      }

      return var3;
   }
}
