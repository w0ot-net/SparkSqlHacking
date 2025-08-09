package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Attribute$ extends AbstractFunction2 implements Serializable {
   public static final Attribute$ MODULE$ = new Attribute$();

   public final String toString() {
      return "Attribute";
   }

   public Attribute apply(final int nameIndex, final ByteCode byteCode) {
      return new Attribute(nameIndex, byteCode);
   }

   public Option unapply(final Attribute x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.nameIndex()), x$0.byteCode())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Attribute$.class);
   }

   private Attribute$() {
   }
}
