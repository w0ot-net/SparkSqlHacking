package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.Set;

public abstract class AbstractObjectSet extends AbstractObjectCollection implements Cloneable, ObjectSet {
   protected AbstractObjectSet() {
   }

   public abstract ObjectIterator iterator();

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Set)) {
         return false;
      } else {
         Set<?> s = (Set)o;
         return s.size() != this.size() ? false : this.containsAll(s);
      }
   }

   public int hashCode() {
      int h = 0;
      int n = this.size();

      K k;
      for(ObjectIterator<K> i = this.iterator(); n-- != 0; h += k == null ? 0 : k.hashCode()) {
         k = (K)i.next();
      }

      return h;
   }
}
