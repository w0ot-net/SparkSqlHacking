package org.apache.zookeeper.server;

import java.util.Set;

public interface NodeHashMap {
   DataNode put(String var1, DataNode var2);

   DataNode putWithoutDigest(String var1, DataNode var2);

   DataNode get(String var1);

   DataNode remove(String var1);

   Set entrySet();

   void clear();

   int size();

   void preChange(String var1, DataNode var2);

   void postChange(String var1, DataNode var2);

   long getDigest();
}
