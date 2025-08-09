package scala.collection.parallel.immutable;

import java.io.Serializable;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range.;
import scala.runtime.ModuleSerializationProxy;

public final class ParRange$ implements Serializable {
   public static final ParRange$ MODULE$ = new ParRange$();

   public ParRange apply(final int start, final int end, final int step, final boolean inclusive) {
      return new ParRange((Range)(inclusive ? .MODULE$.inclusive(start, end, step) : .MODULE$.apply(start, end, step)));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParRange$.class);
   }

   private ParRange$() {
   }
}
