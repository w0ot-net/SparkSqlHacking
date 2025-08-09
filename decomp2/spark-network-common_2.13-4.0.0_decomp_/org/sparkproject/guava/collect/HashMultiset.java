package org.sparkproject.guava.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public final class HashMultiset extends AbstractMapBasedMultiset {
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   public static HashMultiset create() {
      return new HashMultiset();
   }

   public static HashMultiset create(int distinctElements) {
      return new HashMultiset(distinctElements);
   }

   public static HashMultiset create(Iterable elements) {
      HashMultiset<E> multiset = create(Multisets.inferDistinctElements(elements));
      Iterables.addAll(multiset, elements);
      return multiset;
   }

   private HashMultiset() {
      super(new HashMap());
   }

   private HashMultiset(int distinctElements) {
      super(Maps.newHashMapWithExpectedSize(distinctElements));
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      Serialization.writeMultiset(this, stream);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      int distinctElements = Serialization.readCount(stream);
      this.setBackingMap(Maps.newHashMap());
      Serialization.populateMultiset(this, stream, distinctElements);
   }
}
