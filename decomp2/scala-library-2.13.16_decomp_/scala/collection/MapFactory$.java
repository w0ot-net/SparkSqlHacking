package scala.collection;

import java.io.Serializable;
import scala.collection.mutable.Builder;
import scala.runtime.ModuleSerializationProxy;

public final class MapFactory$ implements Serializable {
   public static final MapFactory$ MODULE$ = new MapFactory$();

   public Factory toFactory(final MapFactory factory) {
      return new MapFactory.ToFactory(factory);
   }

   public BuildFrom toBuildFrom(final MapFactory factory) {
      return new BuildFrom(factory) {
         private final MapFactory factory$2;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Object fromSpecific(final Object from, final IterableOnce it) {
            return this.factory$2.from(it);
         }

         public Builder newBuilder(final Object from) {
            return this.factory$2.newBuilder();
         }

         public {
            this.factory$2 = factory$2;
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MapFactory$.class);
   }

   private MapFactory$() {
   }
}
