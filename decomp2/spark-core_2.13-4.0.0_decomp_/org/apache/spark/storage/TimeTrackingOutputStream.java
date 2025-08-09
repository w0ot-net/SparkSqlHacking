package org.apache.spark.storage;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.spark.annotation.Private;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;

@Private
public final class TimeTrackingOutputStream extends OutputStream {
   private final ShuffleWriteMetricsReporter writeMetrics;
   private final OutputStream outputStream;

   public TimeTrackingOutputStream(ShuffleWriteMetricsReporter writeMetrics, OutputStream outputStream) {
      this.writeMetrics = writeMetrics;
      this.outputStream = outputStream;
   }

   public void write(int b) throws IOException {
      long startTime = System.nanoTime();
      this.outputStream.write(b);
      this.writeMetrics.incWriteTime(System.nanoTime() - startTime);
   }

   public void write(byte[] b) throws IOException {
      long startTime = System.nanoTime();
      this.outputStream.write(b);
      this.writeMetrics.incWriteTime(System.nanoTime() - startTime);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      long startTime = System.nanoTime();
      this.outputStream.write(b, off, len);
      this.writeMetrics.incWriteTime(System.nanoTime() - startTime);
   }

   public void flush() throws IOException {
      long startTime = System.nanoTime();
      this.outputStream.flush();
      this.writeMetrics.incWriteTime(System.nanoTime() - startTime);
   }

   public void close() throws IOException {
      long startTime = System.nanoTime();
      this.outputStream.close();
      this.writeMetrics.incWriteTime(System.nanoTime() - startTime);
   }
}
