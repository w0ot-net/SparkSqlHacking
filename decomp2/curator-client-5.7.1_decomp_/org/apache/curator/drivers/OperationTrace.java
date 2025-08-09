package org.apache.curator.drivers;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

public class OperationTrace {
   private final String name;
   private final TracerDriver driver;
   private int returnCode;
   private long latencyMs;
   private long requestBytesLength;
   private long responseBytesLength;
   private String path;
   private boolean withWatcher;
   private long sessionId;
   private Stat stat;
   private final long startTimeNanos;

   public OperationTrace(String name, TracerDriver driver) {
      this(name, driver, -1L);
   }

   public OperationTrace(String name, TracerDriver driver, long sessionId) {
      this.returnCode = Code.OK.intValue();
      this.startTimeNanos = System.nanoTime();
      this.name = name;
      this.driver = driver;
      this.sessionId = sessionId;
   }

   public OperationTrace setReturnCode(int returnCode) {
      this.returnCode = returnCode;
      return this;
   }

   public OperationTrace setRequestBytesLength(long length) {
      this.requestBytesLength = length;
      return this;
   }

   public OperationTrace setRequestBytesLength(String data) {
      if (data == null) {
         return this;
      } else {
         try {
            this.setRequestBytesLength((long)data.getBytes("UTF-8").length);
         } catch (UnsupportedEncodingException var3) {
         }

         return this;
      }
   }

   public OperationTrace setRequestBytesLength(byte[] data) {
      return data == null ? this : this.setRequestBytesLength((long)data.length);
   }

   public OperationTrace setResponseBytesLength(long length) {
      this.responseBytesLength = length;
      return this;
   }

   public OperationTrace setResponseBytesLength(byte[] data) {
      return data == null ? this : this.setResponseBytesLength((long)data.length);
   }

   public OperationTrace setPath(String path) {
      this.path = path;
      return this;
   }

   public OperationTrace setWithWatcher(boolean withWatcher) {
      this.withWatcher = withWatcher;
      return this;
   }

   public OperationTrace setStat(Stat stat) {
      this.stat = stat;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public int getReturnCode() {
      return this.returnCode;
   }

   public long getLatencyMs() {
      return this.latencyMs;
   }

   public long getRequestBytesLength() {
      return this.requestBytesLength;
   }

   public long getResponseBytesLength() {
      return this.responseBytesLength;
   }

   public long getSessionId() {
      return this.sessionId;
   }

   public String getPath() {
      return this.path;
   }

   public boolean isWithWatcher() {
      return this.withWatcher;
   }

   public Stat getStat() {
      return this.stat;
   }

   public void commit() {
      long elapsed = System.nanoTime() - this.startTimeNanos;
      this.latencyMs = TimeUnit.MILLISECONDS.convert(elapsed, TimeUnit.NANOSECONDS);
      if (this.driver instanceof AdvancedTracerDriver) {
         ((AdvancedTracerDriver)this.driver).addTrace(this);
      } else {
         this.driver.addTrace(this.name, elapsed, TimeUnit.NANOSECONDS);
      }

   }
}
