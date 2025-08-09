package scala.collection.mutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.runtime.ModuleSerializationProxy;

public final class HashMap$ implements MapFactory {
   public static final HashMap$ MODULE$ = new HashMap$();
   private static final long serialVersionUID = 3L;

   static {
      HashMap$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public HashMap empty() {
      return new HashMap();
   }

   public HashMap from(final IterableOnce it) {
      int k = it.knownSize();
      int cap = k > 0 ? (int)((double)(k + 1) / (double)0.75F) : 16;
      return (new HashMap(cap, (double)0.75F)).addAll(it);
   }

   public Builder newBuilder() {
      double newBuilder_loadFactor = (double)0.75F;
      int newBuilder_initialCapacity = 16;
      return new GrowableBuilder(newBuilder_initialCapacity, newBuilder_loadFactor) {
         public void sizeHint(final int size) {
            ((HashMap)this.elems()).sizeHint(size);
         }
      };
   }

   public Builder newBuilder(final int initialCapacity, final double loadFactor) {
      return new GrowableBuilder(initialCapacity, loadFactor) {
         public void sizeHint(final int size) {
            ((HashMap)this.elems()).sizeHint(size);
         }
      };
   }

   public final double defaultLoadFactor() {
      return (double)0.75F;
   }

   public final int defaultInitialCapacity() {
      return 16;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HashMap$.class);
   }

   private HashMap$() {
   }
}
