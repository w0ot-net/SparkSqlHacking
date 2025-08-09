package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class $less$colon$less$ implements Serializable {
   public static final $less$colon$less$ MODULE$ = new $less$colon$less$();
   private static final $eq$colon$eq singleton = new $eq$colon$eq() {
      public Object substituteBoth(final Object ftf) {
         return ftf;
      }

      public Object substituteCo(final Object ff) {
         return ff;
      }

      public Object substituteContra(final Object ff) {
         return ff;
      }

      public Object apply(final Object x) {
         return x;
      }

      public $eq$colon$eq flip() {
         return this;
      }

      public Function1 compose(final Function1 r) {
         return r;
      }

      public $less$colon$less compose(final $less$colon$less r) {
         return r;
      }

      public $eq$colon$eq compose(final $eq$colon$eq r) {
         return r;
      }

      public Function1 andThen(final Function1 r) {
         return r;
      }

      public $less$colon$less andThen(final $less$colon$less r) {
         return r;
      }

      public $eq$colon$eq andThen(final $eq$colon$eq r) {
         return r;
      }

      public $eq$colon$eq liftCo() {
         return this;
      }

      public $eq$colon$eq liftContra() {
         return this;
      }

      public String toString() {
         return "generalized constraint";
      }
   };

   private $eq$colon$eq singleton() {
      return singleton;
   }

   public $eq$colon$eq refl() {
      return this.singleton();
   }

   public $eq$colon$eq antisymm(final $less$colon$less l, final $less$colon$less r) {
      return this.singleton();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy($less$colon$less$.class);
   }

   private $less$colon$less$() {
   }
}
