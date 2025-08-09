package org.apache.zookeeper.server.metric;

import java.util.Map;

public abstract class Metric {
   public void add(long value) {
   }

   public void add(int key, long value) {
   }

   public void add(String key, long value) {
   }

   public void reset() {
   }

   public abstract Map values();
}
