package org.sparkproject.guava.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public final class HashMultimap extends HashMultimapGwtSerializationDependencies {
   private static final int DEFAULT_VALUES_PER_KEY = 2;
   @VisibleForTesting
   transient int expectedValuesPerKey;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   public static HashMultimap create() {
      return new HashMultimap();
   }

   public static HashMultimap create(int expectedKeys, int expectedValuesPerKey) {
      return new HashMultimap(expectedKeys, expectedValuesPerKey);
   }

   public static HashMultimap create(Multimap multimap) {
      return new HashMultimap(multimap);
   }

   private HashMultimap() {
      this(12, 2);
   }

   private HashMultimap(int expectedKeys, int expectedValuesPerKey) {
      super(Platform.newHashMapWithExpectedSize(expectedKeys));
      this.expectedValuesPerKey = 2;
      Preconditions.checkArgument(expectedValuesPerKey >= 0);
      this.expectedValuesPerKey = expectedValuesPerKey;
   }

   private HashMultimap(Multimap multimap) {
      super(Platform.newHashMapWithExpectedSize(multimap.keySet().size()));
      this.expectedValuesPerKey = 2;
      this.putAll(multimap);
   }

   Set createCollection() {
      return Platform.newHashSetWithExpectedSize(this.expectedValuesPerKey);
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
      this.expectedValuesPerKey = 2;
      int distinctKeys = Serialization.readCount(stream);
      Map<K, Collection<V>> map = Platform.newHashMapWithExpectedSize(12);
      this.setMap(map);
      Serialization.populateMultimap(this, stream, distinctKeys);
   }
}
