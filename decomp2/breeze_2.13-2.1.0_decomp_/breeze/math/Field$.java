package breeze.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Field$ implements Serializable {
   public static final Field$ MODULE$ = new Field$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(Field$.class);
   }

   private Field$() {
   }
}
