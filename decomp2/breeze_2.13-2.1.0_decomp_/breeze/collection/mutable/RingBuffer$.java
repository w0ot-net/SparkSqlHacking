package breeze.collection.mutable;

import java.io.Serializable;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.GrowableBuilder;
import scala.runtime.ModuleSerializationProxy;

public final class RingBuffer$ implements Serializable {
   public static final RingBuffer$ MODULE$ = new RingBuffer$();

   public RingBuffer apply(final int capacity, final Seq elems) {
      return (RingBuffer)(new RingBuffer(capacity)).$plus$plus$eq(elems);
   }

   public BuildFrom canBuildFrom() {
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final RingBuffer from) {
            return new GrowableBuilder(new RingBuffer(from.capacity()));
         }

         public RingBuffer fromSpecific(final RingBuffer from, final IterableOnce it) {
            return (RingBuffer)((Builder)this.newBuilder(from).$plus$plus$eq(it)).result();
         }

         public {
            BuildFrom.$init$(this);
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RingBuffer$.class);
   }

   private RingBuffer$() {
   }
}
