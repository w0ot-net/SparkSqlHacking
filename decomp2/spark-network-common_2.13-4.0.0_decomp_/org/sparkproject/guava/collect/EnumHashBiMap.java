package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
@J2ktIncompatible
public final class EnumHashBiMap extends AbstractBiMap {
   transient Class keyTypeOrObjectUnderJ2cl;
   @GwtIncompatible
   private static final long serialVersionUID = 0L;

   public static EnumHashBiMap create(Class keyType) {
      return new EnumHashBiMap(keyType);
   }

   public static EnumHashBiMap create(Map map) {
      EnumHashBiMap<K, V> bimap = create(EnumBiMap.inferKeyTypeOrObjectUnderJ2cl(map));
      bimap.putAll(map);
      return bimap;
   }

   private EnumHashBiMap(Class keyType) {
      super(new EnumMap(keyType), (Map)(new HashMap()));
      this.keyTypeOrObjectUnderJ2cl = keyType;
   }

   Enum checkKey(Enum key) {
      return (Enum)Preconditions.checkNotNull(key);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(Enum key, @ParametricNullness Object value) {
      return super.put(key, value);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object forcePut(Enum key, @ParametricNullness Object value) {
      return super.forcePut(key, value);
   }

   @GwtIncompatible
   public Class keyType() {
      return this.keyTypeOrObjectUnderJ2cl;
   }

   @GwtIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeObject(this.keyTypeOrObjectUnderJ2cl);
      Serialization.writeMap(this, stream);
   }

   @GwtIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.keyTypeOrObjectUnderJ2cl = (Class)Objects.requireNonNull(stream.readObject());
      this.setDelegates(new EnumMap(this.keyTypeOrObjectUnderJ2cl), new HashMap());
      Serialization.populateMap(this, stream);
   }
}
