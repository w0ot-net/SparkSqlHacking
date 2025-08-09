package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.primitives.Primitives;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class MutableClassToInstanceMap extends ForwardingMap implements ClassToInstanceMap, Serializable {
   private final Map delegate;

   public static MutableClassToInstanceMap create() {
      return new MutableClassToInstanceMap(new HashMap());
   }

   public static MutableClassToInstanceMap create(Map backingMap) {
      return new MutableClassToInstanceMap(backingMap);
   }

   private MutableClassToInstanceMap(Map delegate) {
      this.delegate = (Map)Preconditions.checkNotNull(delegate);
   }

   protected Map delegate() {
      return this.delegate;
   }

   private static Map.Entry checkedEntry(final Map.Entry entry) {
      return new ForwardingMapEntry() {
         protected Map.Entry delegate() {
            return entry;
         }

         @ParametricNullness
         public Object setValue(@ParametricNullness Object value) {
            MutableClassToInstanceMap.cast((Class)this.getKey(), value);
            return super.setValue(value);
         }
      };
   }

   public Set entrySet() {
      return new ForwardingSet() {
         protected Set delegate() {
            return MutableClassToInstanceMap.this.delegate().entrySet();
         }

         public Spliterator spliterator() {
            return CollectSpliterators.map(this.delegate().spliterator(), (x$0) -> MutableClassToInstanceMap.checkedEntry(x$0));
         }

         public Iterator iterator() {
            return new TransformedIterator(this.delegate().iterator()) {
               Map.Entry transform(Map.Entry from) {
                  return MutableClassToInstanceMap.checkedEntry(from);
               }
            };
         }

         public Object[] toArray() {
            Object[] result = this.standardToArray();
            return result;
         }

         public Object[] toArray(Object[] array) {
            return this.standardToArray(array);
         }
      };
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(Class key, @ParametricNullness Object value) {
      cast(key, value);
      return super.put(key, value);
   }

   public void putAll(Map map) {
      Map<Class<? extends B>, B> copy = new LinkedHashMap(map);

      for(Map.Entry entry : copy.entrySet()) {
         cast((Class)entry.getKey(), entry.getValue());
      }

      super.putAll(copy);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object putInstance(Class type, @ParametricNullness Object value) {
      return cast(type, this.put(type, value));
   }

   @CheckForNull
   public Object getInstance(Class type) {
      return cast(type, this.get(type));
   }

   @CheckForNull
   @CanIgnoreReturnValue
   private static Object cast(Class type, @CheckForNull Object value) {
      return Primitives.wrap(type).cast(value);
   }

   private Object writeReplace() {
      return new SerializedForm(this.delegate());
   }

   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   private static final class SerializedForm implements Serializable {
      private final Map backingMap;
      private static final long serialVersionUID = 0L;

      SerializedForm(Map backingMap) {
         this.backingMap = backingMap;
      }

      Object readResolve() {
         return MutableClassToInstanceMap.create(this.backingMap);
      }
   }
}
