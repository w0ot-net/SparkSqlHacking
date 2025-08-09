package org.apache.hive.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.apache.hadoop.io.IOUtils;

public class StreamPrinter extends Thread {
   InputStream is;
   String type;
   PrintStream[] outputStreams;

   public StreamPrinter(InputStream is, String type, PrintStream... outputStreams) {
      this.is = is;
      this.type = type;
      this.outputStreams = outputStreams;
   }

   public void run() {
      BufferedReader br = null;

      try {
         InputStreamReader isr = new InputStreamReader(this.is);
         br = new BufferedReader(isr);
         String line = null;
         if (this.type != null) {
            while((line = br.readLine()) != null) {
               for(PrintStream os : this.outputStreams) {
                  os.println(this.type + ">" + line);
               }
            }
         } else {
            while((line = br.readLine()) != null) {
               for(PrintStream os : this.outputStreams) {
                  os.println(line);
               }
            }
         }

         br.close();
         br = null;
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } finally {
         IOUtils.closeStream(br);
      }

   }
}
