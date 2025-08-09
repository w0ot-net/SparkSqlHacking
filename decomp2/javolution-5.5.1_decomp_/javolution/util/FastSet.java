package javolution.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Set;
import javolution.context.ObjectFactory;
import javolution.lang.Reusable;

public class FastSet extends FastCollection implements Set, Reusable {
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      public Object create() {
         return new FastSet();
      }
   };
   private transient FastMap _map;
   private static final long serialVersionUID = 1L;

   public FastSet() {
      this(new FastMap());
   }

   public FastSet(String id) {
      this(new FastMap(id));
   }

   public FastSet(int capacity) {
      this(new FastMap(capacity));
   }

   public FastSet(Set elements) {
      this(new FastMap(elements.size()));
      this.addAll(elements);
   }

   private FastSet(FastMap map) {
      this._map = map;
   }

   public static FastSet newInstance() {
      return (FastSet)FACTORY.object();
   }

   public static void recycle(FastSet instance) {
      FACTORY.recycle(instance);
   }

   public final int size() {
      return this._map.size();
   }

   public final boolean add(Object value) {
      return this._map.put(value, value) == null;
   }

   public Iterator iterator() {
      return this._map.keySet().iterator();
   }

   public Set unmodifiable() {
      return (Set)super.unmodifiable();
   }

   public final void clear() {
      this._map.clear();
   }

   public final boolean contains(Object o) {
      return this._map.containsKey(o);
   }

   public final boolean remove(Object o) {
      return this._map.remove(o) != null;
   }

   public FastSet setValueComparator(FastComparator comparator) {
      this._map.setKeyComparator(comparator);
      return this;
   }

   public FastComparator getValueComparator() {
      return this._map.getKeyComparator();
   }

   public void reset() {
      this._map.reset();
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      FastComparator cmp = (FastComparator)stream.readObject();
      int size = stream.readInt();
      this._map = new FastMap(size);
      this.setValueComparator(cmp);
      int i = size;

      while(i-- != 0) {
         Object key = stream.readObject();
         this._map.put(key, key);
      }

   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.writeObject(this.getValueComparator());
      stream.writeInt(this.size());
      FastMap.Entry e = this._map.head();
      FastMap.Entry end = this._map.tail();

      while((e = e.getNext()) != end) {
         stream.writeObject(e.getKey());
      }

   }

   public final FastCollection.Record head() {
      return this._map.head();
   }

   public final FastCollection.Record tail() {
      return this._map.tail();
   }

   public final Object valueOf(FastCollection.Record record) {
      return ((FastMap.Entry)record).getKey();
   }

   public final void delete(FastCollection.Record record) {
      this._map.remove(((FastMap.Entry)record).getKey());
   }
}
