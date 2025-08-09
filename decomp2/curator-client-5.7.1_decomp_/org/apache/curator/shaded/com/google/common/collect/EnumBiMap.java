package org.apache.curator.shaded.com.google.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EnumMap;
import java.util.Map;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
@J2ktIncompatible
public final class EnumBiMap extends AbstractBiMap {
   transient Class keyTypeOrObjectUnderJ2cl;
   transient Class valueTypeOrObjectUnderJ2cl;
   @GwtIncompatible
   private static final long serialVersionUID = 0L;

   public static EnumBiMap create(Class keyType, Class valueType) {
      return new EnumBiMap(keyType, valueType);
   }

   public static EnumBiMap create(Map map) {
      EnumBiMap<K, V> bimap = create(inferKeyTypeOrObjectUnderJ2cl(map), inferValueTypeOrObjectUnderJ2cl(map));
      bimap.putAll(map);
      return bimap;
   }

   private EnumBiMap(Class keyTypeOrObjectUnderJ2cl, Class valueTypeOrObjectUnderJ2cl) {
      super(new EnumMap(keyTypeOrObjectUnderJ2cl), (Map)(new EnumMap(valueTypeOrObjectUnderJ2cl)));
      this.keyTypeOrObjectUnderJ2cl = keyTypeOrObjectUnderJ2cl;
      this.valueTypeOrObjectUnderJ2cl = valueTypeOrObjectUnderJ2cl;
   }

   static Class inferKeyTypeOrObjectUnderJ2cl(Map map) {
      if (map instanceof EnumBiMap) {
         return ((EnumBiMap)map).keyTypeOrObjectUnderJ2cl;
      } else if (map instanceof EnumHashBiMap) {
         return ((EnumHashBiMap)map).keyTypeOrObjectUnderJ2cl;
      } else {
         Preconditions.checkArgument(!map.isEmpty());
         return Platform.getDeclaringClassOrObjectForJ2cl((Enum)map.keySet().iterator().next());
      }
   }

   private static Class inferValueTypeOrObjectUnderJ2cl(Map map) {
      if (map instanceof EnumBiMap) {
         return ((EnumBiMap)map).valueTypeOrObjectUnderJ2cl;
      } else {
         Preconditions.checkArgument(!map.isEmpty());
         return Platform.getDeclaringClassOrObjectForJ2cl((Enum)map.values().iterator().next());
      }
   }

   @GwtIncompatible
   public Class keyType() {
      return this.keyTypeOrObjectUnderJ2cl;
   }

   @GwtIncompatible
   public Class valueType() {
      return this.valueTypeOrObjectUnderJ2cl;
   }

   Enum checkKey(Enum key) {
      return (Enum)Preconditions.checkNotNull(key);
   }

   Enum checkValue(Enum value) {
      return (Enum)Preconditions.checkNotNull(value);
   }

   @GwtIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.keyTypeOrObjectUnderJ2cl);
      stream.writeObject(this.valueTypeOrObjectUnderJ2cl);
      Serialization.writeMap(this, stream);
   }

   @GwtIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.keyTypeOrObjectUnderJ2cl = (Class)stream.readObject();
      this.valueTypeOrObjectUnderJ2cl = (Class)stream.readObject();
      this.setDelegates(new EnumMap(this.keyTypeOrObjectUnderJ2cl), new EnumMap(this.valueTypeOrObjectUnderJ2cl));
      Serialization.populateMap(this, stream);
   }
}
