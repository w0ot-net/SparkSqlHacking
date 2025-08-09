package io.vertx.ext.auth.impl;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.auth.HashString;
import io.vertx.ext.auth.HashingAlgorithm;
import io.vertx.ext.auth.HashingStrategy;
import java.util.HashMap;
import java.util.Map;

public class HashingStrategyImpl implements HashingStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(HashingStrategyImpl.class);
   private final Map algorithms = new HashMap();

   public void add(HashingAlgorithm algorithm) {
      this.algorithms.put(algorithm.id(), algorithm);
   }

   public String hash(String id, Map params, String salt, String password) {
      HashingAlgorithm algorithm = (HashingAlgorithm)this.algorithms.get(id);
      if (algorithm == null) {
         throw new RuntimeException(id + " algorithm is not available.");
      } else {
         HashString hashString = new HashString(id, params, salt);
         String hash = algorithm.hash(hashString, password);
         return HashString.encode(algorithm, hashString.params(), hashString.salt(), hash);
      }
   }

   public boolean verify(String hash, String password) {
      if (hash != null && password != null) {
         HashString hashString = new HashString(hash);
         HashingAlgorithm algorithm = (HashingAlgorithm)this.algorithms.get(hashString.id());
         if (algorithm == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("No hash strategy for algorithm: " + hashString.id());
            }

            return false;
         } else if (hashString.hash() == null) {
            return false;
         } else {
            String hasha = hashString.hash();
            String hashb = algorithm.hash(hashString, password);
            int diff = hasha.length() ^ hashb.length();

            for(int i = 0; i < hasha.length() && i < hashb.length(); ++i) {
               diff |= hasha.charAt(i) ^ hashb.charAt(i);
            }

            return diff == 0;
         }
      } else {
         return false;
      }
   }

   public HashingAlgorithm get(String id) {
      return (HashingAlgorithm)this.algorithms.get(id);
   }

   public HashingStrategy put(String id, HashingAlgorithm algorithm) {
      if (this.algorithms.containsKey(id)) {
         LOG.warn("Existing algorithm: " + id + " will be replaced!");
      }

      this.algorithms.put(id, algorithm);
      return this;
   }
}
