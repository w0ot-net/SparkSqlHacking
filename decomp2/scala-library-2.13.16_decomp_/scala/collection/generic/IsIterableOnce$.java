package scala.collection.generic;

import scala.Function1;
import scala.collection.IterableOnce;
import scala.runtime.Statics;

public final class IsIterableOnce$ implements IsIterableOnceLowPriority {
   public static final IsIterableOnce$ MODULE$ = new IsIterableOnce$();

   static {
      IsIterableOnce$ var10000 = MODULE$;
   }

   public IsIterableOnce isIterableLikeIsIterableOnce(final IsIterable isIterableLike) {
      return IsIterableOnceLowPriority.isIterableLikeIsIterableOnce$(this, isIterableLike);
   }

   public IsIterableOnce iterableOnceIsIterableOnce() {
      return new IsIterableOnce() {
         /** @deprecated */
         private Function1 conversion;

         /** @deprecated */
         public Function1 conversion() {
            return this.conversion;
         }

         public void scala$collection$generic$IsIterableOnce$_setter_$conversion_$eq(final Function1 x$1) {
            this.conversion = x$1;
         }

         public IterableOnce apply(final IterableOnce coll) {
            return coll;
         }

         public {
            IsIterableOnce.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   private IsIterableOnce$() {
   }
}
