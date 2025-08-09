package scala.collection.generic;

import scala.Function1;
import scala.collection.BitSet;
import scala.collection.IterableOps;
import scala.runtime.Statics;

public final class IsIterable$ implements IsIterableLowPriority {
   public static final IsIterable$ MODULE$ = new IsIterable$();

   static {
      IsIterable$ var10000 = MODULE$;
   }

   public IsIterable isSeqLikeIsIterable(final IsSeq isSeqLike) {
      return IsIterableLowPriority.isSeqLikeIsIterable$(this, isSeqLike);
   }

   public IsIterable isMapLikeIsIterable(final IsMap isMapLike) {
      return IsIterableLowPriority.isMapLikeIsIterable$(this, isMapLike);
   }

   public IsIterable iterableOpsIsIterable() {
      return new IsIterable() {
         /** @deprecated */
         private Function1 conversion;

         /** @deprecated */
         public Function1 conversion() {
            return this.conversion;
         }

         public void scala$collection$generic$IsIterable$_setter_$conversion_$eq(final Function1 x$1) {
            this.conversion = x$1;
         }

         public void scala$collection$generic$IsIterableOnce$_setter_$conversion_$eq(final Function1 x$1) {
         }

         public IterableOps apply(final IterableOps coll) {
            return coll;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   public IsIterable bitSetOpsIsIterable() {
      return new IsIterable() {
         /** @deprecated */
         private Function1 conversion;

         /** @deprecated */
         public Function1 conversion() {
            return this.conversion;
         }

         public void scala$collection$generic$IsIterable$_setter_$conversion_$eq(final Function1 x$1) {
            this.conversion = x$1;
         }

         public void scala$collection$generic$IsIterableOnce$_setter_$conversion_$eq(final Function1 x$1) {
         }

         public IterableOps apply(final BitSet coll) {
            return coll;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   private IsIterable$() {
   }
}
