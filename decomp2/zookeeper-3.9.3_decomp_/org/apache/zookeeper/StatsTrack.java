package org.apache.zookeeper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import org.apache.zookeeper.common.StringUtils;

public class StatsTrack {
   private static final String countStr = "count";
   private static final String countHardLimitStr = "countHardLimit";
   private static final String byteStr = "bytes";
   private static final String byteHardLimitStr = "byteHardLimit";
   private final Map stats;
   private static final Pattern PAIRS_SEPARATOR = Pattern.compile("[,;]+");

   public StatsTrack() {
      this("");
   }

   public StatsTrack(byte[] stat) {
      this(new String(stat, StandardCharsets.UTF_8));
   }

   public StatsTrack(String stat) {
      this.stats = new HashMap();
      this.stats.clear();
      if (stat != null && stat.length() != 0) {
         String[] keyValuePairs = PAIRS_SEPARATOR.split(stat);

         for(String keyValuePair : keyValuePairs) {
            String[] kv = keyValuePair.split("=");
            this.stats.put(kv[0], Long.parseLong(StringUtils.isEmpty(kv[1]) ? "-1" : kv[1]));
         }

      }
   }

   public long getCount() {
      return this.getValue("count");
   }

   public void setCount(long count) {
      this.setValue("count", count);
   }

   public long getCountHardLimit() {
      return this.getValue("countHardLimit");
   }

   public void setCountHardLimit(long count) {
      this.setValue("countHardLimit", count);
   }

   public long getBytes() {
      return this.getValue("bytes");
   }

   public void setBytes(long bytes) {
      this.setValue("bytes", bytes);
   }

   public long getByteHardLimit() {
      return this.getValue("byteHardLimit");
   }

   public void setByteHardLimit(long bytes) {
      this.setValue("byteHardLimit", bytes);
   }

   private long getValue(String key) {
      Long val = (Long)this.stats.get(key);
      return val == null ? -1L : val;
   }

   private void setValue(String key, long value) {
      this.stats.put(key, value);
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      ArrayList<String> keys = new ArrayList(this.stats.keySet());
      keys.remove("count");
      keys.remove("bytes");
      buf.append("count");
      buf.append("=");
      buf.append(this.getCount());
      buf.append(",");
      buf.append("bytes");
      buf.append("=");
      buf.append(this.getBytes());
      if (!keys.isEmpty()) {
         buf.append("=");
         Collections.sort(keys);

         for(String key : keys) {
            buf.append(";");
            buf.append(key);
            buf.append("=");
            buf.append(this.stats.get(key));
         }
      }

      return buf.toString();
   }

   public byte[] getStatsBytes() {
      return this.toString().getBytes(StandardCharsets.UTF_8);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         StatsTrack that = (StatsTrack)o;
         return Objects.equals(this.stats, that.stats);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.stats});
   }
}
