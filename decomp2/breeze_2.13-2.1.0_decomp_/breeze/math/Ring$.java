package breeze.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Ring$ implements Serializable {
   public static final Ring$ MODULE$ = new Ring$();

   public Ring ringD() {
      return Field.fieldDouble$.MODULE$;
   }

   public Ring ringFloat() {
      return Field.fieldFloat$.MODULE$;
   }

   public Ring ringInt() {
      return Field.fieldInt$.MODULE$;
   }

   public Ring ringLong() {
      return Field.fieldLong$.MODULE$;
   }

   public Ring ringBigInt() {
      return Field.fieldBigInt$.MODULE$;
   }

   public Ring ringShort() {
      return Field.fieldShort$.MODULE$;
   }

   public Ring ringComplex() {
      return Complex.scalar$.MODULE$;
   }

   public Ring ringFromField(final Field field) {
      return field;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Ring$.class);
   }

   private Ring$() {
   }
}
