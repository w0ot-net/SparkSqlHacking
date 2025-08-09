package org.apache.zookeeper.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class AtomicFileWritingIdiom {
   public AtomicFileWritingIdiom(File targetFile, OutputStreamStatement osStmt) throws IOException {
      this(targetFile, osStmt, (WriterStatement)null);
   }

   public AtomicFileWritingIdiom(File targetFile, WriterStatement wStmt) throws IOException {
      this(targetFile, (OutputStreamStatement)null, wStmt);
   }

   private AtomicFileWritingIdiom(File targetFile, OutputStreamStatement osStmt, WriterStatement wStmt) throws IOException {
      AtomicFileOutputStream out = null;
      boolean triedToClose = false;

      try {
         out = new AtomicFileOutputStream(targetFile);
         if (wStmt == null) {
            osStmt.write(out);
         } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
            wStmt.write(bw);
            bw.flush();
         }

         triedToClose = true;
         out.close();
      } finally {
         if (out != null && !triedToClose) {
            out.abort();
         }

      }

   }

   public interface OutputStreamStatement {
      void write(OutputStream var1) throws IOException;
   }

   public interface WriterStatement {
      void write(Writer var1) throws IOException;
   }
}
