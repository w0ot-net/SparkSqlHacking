package org.apache.spark.rdd;

import java.io.Serializable;
import java.util.StringTokenizer;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.ModuleSerializationProxy;

public final class PipedRDD$ implements Serializable {
   public static final PipedRDD$ MODULE$ = new PipedRDD$();
   private static final String STDIN_WRITER_THREAD_PREFIX = "stdin writer for";
   private static final String STDERR_READER_THREAD_PREFIX = "stderr reader for";

   public Seq tokenize(final String command) {
      ArrayBuffer buf = new ArrayBuffer();
      StringTokenizer tok = new StringTokenizer(command);

      while(tok.hasMoreElements()) {
         buf.$plus$eq(tok.nextToken());
      }

      return buf.toSeq();
   }

   public String STDIN_WRITER_THREAD_PREFIX() {
      return STDIN_WRITER_THREAD_PREFIX;
   }

   public String STDERR_READER_THREAD_PREFIX() {
      return STDERR_READER_THREAD_PREFIX;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PipedRDD$.class);
   }

   private PipedRDD$() {
   }
}
