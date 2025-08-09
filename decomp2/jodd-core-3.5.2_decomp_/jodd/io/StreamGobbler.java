package jodd.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class StreamGobbler extends Thread {
   protected final InputStream is;
   protected final String type;
   protected final OutputStream os;

   public StreamGobbler(InputStream is, String type) {
      this(is, type, (OutputStream)null);
   }

   public StreamGobbler(InputStream is) {
      this(is, (String)null, (OutputStream)null);
   }

   public StreamGobbler(InputStream is, OutputStream output) {
      this(is, (String)null, output);
   }

   public StreamGobbler(InputStream is, String type, OutputStream output) {
      this.is = is;
      this.type = type;
      this.os = output;
   }

   public void run() {
      try {
         PrintWriter pw = null;
         if (this.os != null) {
            pw = new PrintWriter(this.os);
         }

         InputStreamReader isr = new InputStreamReader(this.is);
         BufferedReader br = new BufferedReader(isr);

         String line;
         while((line = br.readLine()) != null) {
            if (pw != null) {
               if (this.type != null) {
                  pw.print(this.type + "> ");
               }

               pw.println(line);
            }
         }

         if (pw != null) {
            pw.flush();
         }
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

   }
}
