package org.apache.hadoop.hive.common.io;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public abstract class FetchConverter extends PrintStream {
   protected volatile boolean queryfound;
   protected volatile boolean fetchStarted;

   public FetchConverter(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException {
      super(out, autoFlush, encoding);
   }

   public void foundQuery(boolean queryfound) {
      this.queryfound = queryfound;
   }

   public void fetchStarted() {
      this.fetchStarted = true;
   }

   public void println(String out) {
      if (this.byPass()) {
         this.printDirect(out);
      } else {
         this.process(out);
      }

   }

   protected final void printDirect(String out) {
      super.println(out);
   }

   protected final boolean byPass() {
      return !this.queryfound || !this.fetchStarted;
   }

   protected abstract void process(String var1);

   protected abstract void processFinal();

   public void flush() {
      if (this.byPass()) {
         super.flush();
      }

   }

   public void fetchFinished() {
      if (!this.byPass()) {
         this.processFinal();
      }

      super.flush();
      this.fetchStarted = false;
   }
}
