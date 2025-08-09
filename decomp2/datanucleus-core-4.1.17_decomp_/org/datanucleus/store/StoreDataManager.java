package org.datanucleus.store;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class StoreDataManager {
   protected Map storeDataByClass = new ConcurrentHashMap();
   protected Map savedStoreDataByClass;

   public void clear() {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("032002"));
      }

      this.storeDataByClass.clear();
   }

   public void deregisterClass(String className) {
      this.storeDataByClass.remove(className);
   }

   protected void registerStoreData(StoreData data) {
      if (data.isFCO()) {
         if (this.storeDataByClass.containsKey(data.getName())) {
            return;
         }

         this.storeDataByClass.put(data.getName(), data);
      } else {
         if (this.storeDataByClass.containsKey(data.getMetaData())) {
            return;
         }

         this.storeDataByClass.put(data.getMetaData(), data);
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("032001", data));
      }

   }

   public StoreData[] getStoreDataForProperties(String key1, Object value1, String key2, Object value2) {
      Collection<StoreData> results = null;

      for(StoreData data : this.storeDataByClass.values()) {
         if (data.getProperties() != null) {
            Object prop1Value = data.getProperties().get(key1);
            Object prop2Value = data.getProperties().get(key2);
            if (prop1Value != null && prop1Value.equals(value1) && prop2Value != null && prop2Value.equals(value2)) {
               if (results == null) {
                  results = new HashSet();
               }

               results.add(data);
            }
         }
      }

      if (results != null) {
         return (StoreData[])results.toArray(new StoreData[results.size()]);
      } else {
         return null;
      }
   }

   public boolean managesClass(String className) {
      return this.storeDataByClass.containsKey(className);
   }

   public Collection getManagedStoreData() {
      return Collections.unmodifiableCollection(this.storeDataByClass.values());
   }

   public StoreData get(String className) {
      return (StoreData)this.storeDataByClass.get(className);
   }

   public StoreData get(AbstractMemberMetaData mmd) {
      return (StoreData)this.storeDataByClass.get(mmd);
   }

   public int size() {
      return this.storeDataByClass.size();
   }

   public void begin() {
      this.savedStoreDataByClass = new ConcurrentHashMap(this.storeDataByClass);
   }

   public void rollback() {
      this.storeDataByClass = this.savedStoreDataByClass;
      this.savedStoreDataByClass = null;
   }

   public void commit() {
      this.savedStoreDataByClass = null;
   }
}
