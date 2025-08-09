package org.apache.zookeeper.server;

import java.util.Objects;
import org.apache.zookeeper.common.Time;
import org.slf4j.Logger;

public class RateLogger {
   private final long LOG_INTERVAL;
   private final Logger LOG;
   private String msg;
   private long timestamp;
   private int count;
   private String value;

   public RateLogger(Logger log) {
      this(log, 100L);
   }

   public RateLogger(Logger log, long interval) {
      this.msg = null;
      this.count = 0;
      this.value = null;
      this.LOG = log;
      this.LOG_INTERVAL = interval;
   }

   public void flush() {
      if (this.msg != null && this.count > 0) {
         String log = "";
         if (this.count > 1) {
            log = "[" + this.count + " times] ";
         }

         log = log + "Message: " + this.msg;
         if (this.value != null) {
            log = log + " Last value:" + this.value;
         }

         this.LOG.warn(log);
      }

      this.msg = null;
      this.value = null;
      this.count = 0;
   }

   public void rateLimitLog(String newMsg) {
      this.rateLimitLog(newMsg, (String)null);
   }

   public void rateLimitLog(String newMsg, String newValue) {
      long now = Time.currentElapsedTime();
      if (Objects.equals(newMsg, this.msg)) {
         ++this.count;
         this.value = newValue;
         if (now - this.timestamp >= this.LOG_INTERVAL) {
            this.flush();
            this.msg = newMsg;
            this.timestamp = now;
            this.value = newValue;
         }
      } else {
         this.flush();
         this.msg = newMsg;
         this.value = newValue;
         this.timestamp = now;
         this.LOG.warn("Message:{} Value:{}", this.msg, this.value);
      }

   }
}
