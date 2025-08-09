package scala.collection.generic;

import scala.Function1;
import scala.collection.MapOps;
import scala.collection.MapView;
import scala.collection.immutable.IntMap;
import scala.collection.immutable.LongMap;
import scala.collection.mutable.AnyRefMap;
import scala.runtime.Statics;

public final class IsMap$ {
   public static final IsMap$ MODULE$ = new IsMap$();

   public IsMap mapOpsIsMap() {
      return new IsMap() {
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

         public MapOps apply(final MapOps c) {
            return c;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   public IsMap mapViewIsMap() {
      return new IsMap() {
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

         public MapOps apply(final MapView c) {
            return c;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   /** @deprecated */
   public IsMap anyRefMapIsMap() {
      return new IsMap() {
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

         public MapOps apply(final AnyRefMap c) {
            return c;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   public IsMap intMapIsMap() {
      return new IsMap() {
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

         public MapOps apply(final IntMap c) {
            return c;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   public IsMap longMapIsMap() {
      return new IsMap() {
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

         public MapOps apply(final LongMap c) {
            return c;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   public IsMap mutableLongMapIsMap() {
      return new IsMap() {
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

         public MapOps apply(final scala.collection.mutable.LongMap c) {
            return c;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   private IsMap$() {
   }
}
