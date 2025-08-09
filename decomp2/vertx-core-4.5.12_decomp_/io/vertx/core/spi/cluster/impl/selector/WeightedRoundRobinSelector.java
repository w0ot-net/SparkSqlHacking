package io.vertx.core.spi.cluster.impl.selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

class WeightedRoundRobinSelector implements RoundRobinSelector {
   private final List uniqueIds;
   private final TreeMap offsets = new TreeMap();
   private final Index index;

   WeightedRoundRobinSelector(Map weights) {
      List<String> uniqueIds = new ArrayList(weights.size());
      List<Map.Entry<String, Weight>> sorted = new ArrayList(weights.entrySet());
      sorted.sort(Entry.comparingByValue());
      int totalWeight = 0;

      for(int i = 0; i < sorted.size(); ++i) {
         Map.Entry<String, Weight> current = (Map.Entry)sorted.get(i);
         uniqueIds.add(current.getKey());
         int weight = ((Weight)current.getValue()).value();
         totalWeight += weight;
         if (i < sorted.size() - 1) {
            int increment = weight - (i == 0 ? 0 : ((Weight)((Map.Entry)sorted.get(i - 1)).getValue()).value());
            int limit = (i == 0 ? 0 : (Integer)this.offsets.lastKey()) + (weights.size() - i) * increment;
            this.offsets.put(limit, i + 1);
         }
      }

      this.uniqueIds = Collections.unmodifiableList(uniqueIds);
      this.index = new Index(totalWeight);
   }

   public String selectForSend() {
      int idx = this.index.nextVal();
      Map.Entry<Integer, Integer> entry = this.offsets.floorEntry(idx);
      if (entry == null) {
         return (String)this.uniqueIds.get(idx % this.uniqueIds.size());
      } else {
         int offset = (Integer)entry.getValue();
         return offset == this.uniqueIds.size() - 1 ? (String)this.uniqueIds.get(offset) : (String)this.uniqueIds.get(offset + idx % (this.uniqueIds.size() - offset));
      }
   }

   public Iterable selectForPublish() {
      return this.uniqueIds;
   }
}
