package org.apache.avro.mapred.tether;

import java.nio.ByteBuffer;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TetherOutputService implements OutputProtocol {
   private Reporter reporter;
   private OutputCollector collector;
   private int inputPort;
   private boolean complete;
   private String error;
   private static final Logger LOG = LoggerFactory.getLogger(TetherOutputService.class);
   public static final long TIMEOUT = 10000L;

   public TetherOutputService(OutputCollector collector, Reporter reporter) {
      this.reporter = reporter;
      this.collector = collector;
   }

   public synchronized void configure(int inputPort) {
      LOG.info("got input port from child: inputport=" + inputPort);
      this.inputPort = inputPort;
      this.notify();
   }

   public synchronized int inputPort() throws Exception {
      if (this.inputPort == 0) {
         LOG.info("waiting for input port from child");
         this.wait(10000L);
      }

      if (this.inputPort == 0) {
         LOG.error("Parent process timed out waiting for subprocess to send input port. Check the job log files for more info.");
         throw new Exception("Parent process timed out waiting for subprocess to send input port");
      } else {
         return this.inputPort;
      }
   }

   public void output(ByteBuffer datum) {
      try {
         this.collector.collect(new TetherData(datum), NullWritable.get());
      } catch (Throwable var6) {
         Throwable e = var6;
         LOG.warn("Error: " + var6, var6);
         synchronized(this) {
            this.error = e.toString();
         }
      }

   }

   public void outputPartitioned(int partition, ByteBuffer datum) {
      TetherPartitioner.setNextPartition(partition);
      this.output(datum);
   }

   public void status(String message) {
      this.reporter.setStatus(message.toString());
   }

   public void count(String group, String name, long amount) {
      this.reporter.getCounter(group.toString(), name.toString()).increment(amount);
   }

   public synchronized void fail(String message) {
      LOG.warn("Failing: " + message);
      this.error = message;
      this.notify();
   }

   public synchronized void complete() {
      LOG.info("got task complete");
      this.complete = true;
      this.notify();
   }

   public synchronized boolean isFinished() {
      return this.complete || this.error != null;
   }

   public String error() {
      return this.error;
   }

   public synchronized boolean waitForFinish() throws InterruptedException {
      while(!this.isFinished()) {
         this.wait();
      }

      return this.error != null;
   }
}
