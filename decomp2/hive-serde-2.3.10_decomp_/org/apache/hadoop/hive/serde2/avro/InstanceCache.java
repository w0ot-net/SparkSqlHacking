package org.apache.hadoop.hive.serde2.avro;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InstanceCache {
   private static final Logger LOG = LoggerFactory.getLogger(InstanceCache.class);
   Map cache = new HashMap();

   public Object retrieve(Object hv) throws AvroSerdeException {
      return this.retrieve(hv, (Set)null);
   }

   public synchronized Object retrieve(Object hv, Set seenSchemas) throws AvroSerdeException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Checking for hv: " + hv.toString());
      }

      if (this.cache.containsKey(hv)) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Returning cache result.");
         }

         return this.cache.get(hv);
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Creating new instance and storing in cache");
         }

         Instance instance = (Instance)this.makeInstance(hv, seenSchemas);
         this.cache.put(hv, instance);
         return instance;
      }
   }

   protected abstract Object makeInstance(Object var1, Set var2) throws AvroSerdeException;
}
