package jodd.util.collection;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

public abstract class SetMapAdapter extends AbstractSet {
   protected Map map;
   private static final Object DUMMY_VALUE = new Object();

   protected SetMapAdapter(Map mapImplementation) {
      this.map = mapImplementation;
   }

   public Iterator iterator() {
      return this.map.keySet().iterator();
   }

   public int size() {
      return this.map.size();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public boolean contains(Object o) {
      return this.map.containsKey(o);
   }

   public boolean add(Object o) {
      return this.map.put(o, DUMMY_VALUE) == null;
   }

   public boolean remove(Object o) {
      return this.map.remove(o) == DUMMY_VALUE;
   }

   public void clear() {
      this.map.clear();
   }
}
