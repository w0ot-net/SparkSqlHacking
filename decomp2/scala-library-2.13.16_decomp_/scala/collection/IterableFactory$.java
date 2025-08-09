package scala.collection;

import java.io.Serializable;
import scala.collection.mutable.Builder;
import scala.runtime.ModuleSerializationProxy;

public final class IterableFactory$ implements Serializable {
   public static final IterableFactory$ MODULE$ = new IterableFactory$();

   public Factory toFactory(final IterableFactory factory) {
      return new IterableFactory.ToFactory(factory);
   }

   public BuildFrom toBuildFrom(final IterableFactory factory) {
      return new BuildFrom(factory) {
         private final IterableFactory factory$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Object fromSpecific(final Object from, final IterableOnce it) {
            return this.factory$1.from(it);
         }

         public Builder newBuilder(final Object from) {
            return this.factory$1.newBuilder();
         }

         public {
            this.factory$1 = factory$1;
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IterableFactory$.class);
   }

   private IterableFactory$() {
   }
}
