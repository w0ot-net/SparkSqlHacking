package jakarta.ws.rs.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultivaluedHashMap extends AbstractMultivaluedMap implements Serializable {
   private static final long serialVersionUID = -6052320403766368902L;

   public MultivaluedHashMap() {
      super(new HashMap());
   }

   public MultivaluedHashMap(int initialCapacity) {
      super(new HashMap(initialCapacity));
   }

   public MultivaluedHashMap(int initialCapacity, float loadFactor) {
      super(new HashMap(initialCapacity, loadFactor));
   }

   public MultivaluedHashMap(MultivaluedMap map) {
      this();
      this.putAll(map);
   }

   private void putAll(MultivaluedMap map) {
      for(Map.Entry e : map.entrySet()) {
         this.store.put(e.getKey(), new ArrayList((Collection)e.getValue()));
      }

   }

   public MultivaluedHashMap(Map map) {
      this();

      for(Map.Entry e : map.entrySet()) {
         this.putSingle(e.getKey(), e.getValue());
      }

   }
}
