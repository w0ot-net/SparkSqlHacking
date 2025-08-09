package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public final class ArrayListMultimap extends ArrayListMultimapGwtSerializationDependencies {
   private static final int DEFAULT_VALUES_PER_KEY = 3;
   @VisibleForTesting
   transient int expectedValuesPerKey;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   public static ArrayListMultimap create() {
      return new ArrayListMultimap();
   }

   public static ArrayListMultimap create(int expectedKeys, int expectedValuesPerKey) {
      return new ArrayListMultimap(expectedKeys, expectedValuesPerKey);
   }

   public static ArrayListMultimap create(Multimap multimap) {
      return new ArrayListMultimap(multimap);
   }

   private ArrayListMultimap() {
      this(12, 3);
   }

   private ArrayListMultimap(int expectedKeys, int expectedValuesPerKey) {
      super(Platform.newHashMapWithExpectedSize(expectedKeys));
      CollectPreconditions.checkNonnegative(expectedValuesPerKey, "expectedValuesPerKey");
      this.expectedValuesPerKey = expectedValuesPerKey;
   }

   private ArrayListMultimap(Multimap multimap) {
      this(multimap.keySet().size(), multimap instanceof ArrayListMultimap ? ((ArrayListMultimap)multimap).expectedValuesPerKey : 3);
      this.putAll(multimap);
   }

   List createCollection() {
      return new ArrayList(this.expectedValuesPerKey);
   }

   /** @deprecated */
   @Deprecated
   public void trimToSize() {
      for(Collection collection : this.backingMap().values()) {
         ArrayList<V> arrayList = (ArrayList)collection;
         arrayList.trimToSize();
      }

   }

   @GwtIncompatible
   @J2ktIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      Serialization.writeMultimap(this, stream);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.expectedValuesPerKey = 3;
      int distinctKeys = Serialization.readCount(stream);
      Map<K, Collection<V>> map = Maps.newHashMap();
      this.setMap(map);
      Serialization.populateMultimap(this, stream, distinctKeys);
   }
}
