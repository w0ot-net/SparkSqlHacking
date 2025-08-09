package breeze.gymnastics;

import scala.Predef.;

public final class NotGiven$ {
   public static final NotGiven$ MODULE$ = new NotGiven$();
   private static final NotGiven inst = new NotGiven() {
   };

   private NotGiven inst() {
      return inst;
   }

   public NotGiven neq() {
      return this.inst();
   }

   public NotGiven neqAmbig1(final Object t) {
      throw .MODULE$.$qmark$qmark$qmark();
   }

   public NotGiven neqAmbig2(final Object t) {
      throw .MODULE$.$qmark$qmark$qmark();
   }

   private NotGiven$() {
   }
}
