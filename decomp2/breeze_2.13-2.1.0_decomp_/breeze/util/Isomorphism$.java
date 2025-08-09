package breeze.util;

import java.io.Serializable;
import scala.Function1;
import scala.runtime.ModuleSerializationProxy;

public final class Isomorphism$ implements Serializable {
   public static final Isomorphism$ MODULE$ = new Isomorphism$();

   public Isomorphism apply(final Function1 tu, final Function1 ut) {
      return new Isomorphism(tu, ut) {
         private final Function1 tu$1;
         private final Function1 ut$1;

         public Isomorphism reverse() {
            return Isomorphism.reverse$(this);
         }

         public Object forward(final Object t) {
            return this.tu$1.apply(t);
         }

         public Object backward(final Object t) {
            return this.ut$1.apply(t);
         }

         public {
            this.tu$1 = tu$1;
            this.ut$1 = ut$1;
            Isomorphism.$init$(this);
         }
      };
   }

   public Isomorphism identity() {
      return new Isomorphism() {
         public Isomorphism reverse() {
            return Isomorphism.reverse$(this);
         }

         public Object forward(final Object t) {
            return t;
         }

         public Object backward(final Object u) {
            return u;
         }

         public {
            Isomorphism.$init$(this);
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Isomorphism$.class);
   }

   private Isomorphism$() {
   }
}
