package io.fabric8.kubernetes.client.informers.cache;

import java.util.List;
import java.util.Map;

public interface Indexer extends Store {
   List index(String var1, Object var2);

   List indexKeys(String var1, String var2);

   List byIndex(String var1, String var2);

   Map getIndexers();

   void addIndexers(Map var1);

   void removeIndexer(String var1);
}
