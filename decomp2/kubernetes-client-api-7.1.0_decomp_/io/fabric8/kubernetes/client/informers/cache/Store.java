package io.fabric8.kubernetes.client.informers.cache;

import java.util.List;

public interface Store {
   List list();

   List listKeys();

   Object get(Object var1);

   Object getByKey(String var1);

   String getKey(Object var1);
}
