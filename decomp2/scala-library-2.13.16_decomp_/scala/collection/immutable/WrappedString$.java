package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SpecificIterableFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.StringBuilder;
import scala.runtime.ModuleSerializationProxy;

public final class WrappedString$ implements SpecificIterableFactory, Serializable {
   public static final WrappedString$ MODULE$ = new WrappedString$();
   private static final long serialVersionUID = 3L;
   private static final WrappedString empty;

   static {
      WrappedString$ var10000 = MODULE$;
      empty = new WrappedString("");
   }

   public Object apply(final Seq xs) {
      return SpecificIterableFactory.apply$(this, xs);
   }

   public Object fill(final int n, final Function0 elem) {
      return SpecificIterableFactory.fill$(this, n, elem);
   }

   public Factory specificIterableFactory() {
      return SpecificIterableFactory.specificIterableFactory$(this);
   }

   public WrappedString fromSpecific(final IterableOnce it) {
      Builder b = this.newBuilder();
      if (b == null) {
         throw null;
      } else {
         b.sizeHint(it, 0);
         b.addAll(it);
         return (WrappedString)b.result();
      }
   }

   public WrappedString empty() {
      return empty;
   }

   public Builder newBuilder() {
      StringBuilder var10000 = new StringBuilder();
      Function1 mapResult_f = (x) -> new WrappedString(x);
      StringBuilder mapResult_this = var10000;
      return new Builder(mapResult_f) {
         // $FF: synthetic field
         private final Builder $outer;
         private final Function1 f$1;

         public final void sizeHint(final IterableOnce coll, final int delta) {
            Builder.sizeHint$(this, coll, delta);
         }

         public final int sizeHint$default$2() {
            return Builder.sizeHint$default$2$(this);
         }

         public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
            Builder.sizeHintBounded$(this, size, boundingColl);
         }

         public Builder mapResult(final Function1 f) {
            return Builder.mapResult$(this, f);
         }

         public final Growable $plus$eq(final Object elem) {
            return Growable.$plus$eq$(this, elem);
         }

         /** @deprecated */
         public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
            return Growable.$plus$eq$(this, elem1, elem2, elems);
         }

         public final Growable $plus$plus$eq(final IterableOnce elems) {
            return Growable.$plus$plus$eq$(this, elems);
         }

         public <undefinedtype> addOne(final Object x) {
            Builder var10000 = this.$outer;
            if (var10000 == null) {
               throw null;
            } else {
               var10000.addOne(x);
               return this;
            }
         }

         public void clear() {
            this.$outer.clear();
         }

         public <undefinedtype> addAll(final IterableOnce xs) {
            Builder var10000 = this.$outer;
            if (var10000 == null) {
               throw null;
            } else {
               var10000.addAll(xs);
               return this;
            }
         }

         public void sizeHint(final int size) {
            this.$outer.sizeHint(size);
         }

         public Object result() {
            return this.f$1.apply(this.$outer.result());
         }

         public int knownSize() {
            return this.$outer.knownSize();
         }

         public {
            if (WrappedString$.this == null) {
               throw null;
            } else {
               this.$outer = WrappedString$.this;
               this.f$1 = f$1;
            }
         }
      };
   }

   public WrappedString UnwrapOp(final WrappedString value) {
      return value;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WrappedString$.class);
   }

   private WrappedString$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
