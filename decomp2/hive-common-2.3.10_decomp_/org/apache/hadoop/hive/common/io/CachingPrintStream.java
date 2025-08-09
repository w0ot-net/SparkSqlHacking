package org.apache.hadoop.hive.common.io;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CachingPrintStream extends PrintStream {
   List output = new ArrayList();

   public CachingPrintStream(OutputStream out, boolean autoFlush, String encoding) throws FileNotFoundException, UnsupportedEncodingException {
      super(out, autoFlush, encoding);
   }

   public CachingPrintStream(OutputStream out) {
      super(out);
   }

   public void println(String out) {
      this.output.add(out);
      super.println(out);
   }

   public void flush() {
      this.output = new ArrayList();
      super.flush();
   }

   public List getOutput() {
      return this.output;
   }
}
