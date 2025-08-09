package breeze.util;

import scala.Function1;
import scala.Function2;

public final class Lens$ {
   public static final Lens$ MODULE$ = new Lens$();

   public Lens apply(final Function1 get, final Function2 set) {
      return new Lens(get, set) {
         private final Function1 g$1;
         private final Function2 s$1;

         public Object apply(final Object t) {
            return Lens.apply$(this, t);
         }

         public Object get(final Object t) {
            return this.g$1.apply(t);
         }

         public Object set(final Object t, final Object u) {
            return this.s$1.apply(t, u);
         }

         public {
            this.g$1 = g$1;
            this.s$1 = s$1;
            Lens.$init$(this);
         }
      };
   }

   public Lens identity() {
      return new Lens() {
         public Object apply(final Object t) {
            return Lens.apply$(this, t);
         }

         public Object get(final Object t) {
            return t;
         }

         public Object set(final Object t, final Object u) {
            return u;
         }

         public {
            Lens.$init$(this);
         }
      };
   }

   public Lens isomorphismYieldsLens(final Isomorphism iso) {
      return new Lens(iso) {
         private final Isomorphism iso$1;

         public Object apply(final Object t) {
            return Lens.apply$(this, t);
         }

         public Object get(final Object t) {
            return this.iso$1.forward(t);
         }

         public Object set(final Object t, final Object u) {
            return this.iso$1.backward(u);
         }

         public {
            this.iso$1 = iso$1;
            Lens.$init$(this);
         }
      };
   }

   private Lens$() {
   }
}
