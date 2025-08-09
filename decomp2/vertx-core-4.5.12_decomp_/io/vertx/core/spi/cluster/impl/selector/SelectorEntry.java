package io.vertx.core.spi.cluster.impl.selector;

import io.vertx.core.Promise;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SelectorEntry {
   final RoundRobinSelector selector;
   final Promise selectorPromise;
   final int counter;

   SelectorEntry() {
      this.selector = null;
      this.selectorPromise = Promise.promise();
      this.counter = 0;
   }

   private SelectorEntry(RoundRobinSelector selector, Promise selectorPromise, int counter) {
      this.selector = selector;
      this.selectorPromise = selectorPromise;
      this.counter = counter;
   }

   SelectorEntry increment() {
      return new SelectorEntry((RoundRobinSelector)null, this.selectorPromise, this.counter + 1);
   }

   SelectorEntry data(List nodeIds) {
      if (nodeIds != null && !nodeIds.isEmpty()) {
         Map<String, Weight> weights = this.computeWeights(nodeIds);
         RoundRobinSelector selector;
         if (this.isEvenlyDistributed(weights)) {
            selector = new SimpleRoundRobinSelector(new ArrayList(weights.keySet()));
         } else {
            selector = new WeightedRoundRobinSelector(weights);
         }

         return new SelectorEntry(selector, this.selectorPromise, this.counter);
      } else {
         return null;
      }
   }

   private Map computeWeights(List nodeIds) {
      Map<String, Weight> weights = new HashMap();

      for(String nodeId : nodeIds) {
         weights.compute(nodeId, (s, weight) -> weight == null ? new Weight(0) : weight.increment());
      }

      return weights;
   }

   private boolean isEvenlyDistributed(Map weights) {
      if (weights.size() > 1) {
         Weight previous = null;

         for(Weight weight : weights.values()) {
            if (previous != null && previous.value() != weight.value()) {
               return false;
            }

            previous = weight;
         }
      }

      return true;
   }

   boolean shouldInitialize() {
      return this.counter == 0;
   }

   boolean isNotReady() {
      return this.selector == null;
   }
}
