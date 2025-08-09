package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class DEFAULT$ extends AbstractFunction2 implements Serializable {
   public static final DEFAULT$ MODULE$ = new DEFAULT$();

   public final String toString() {
      return "DEFAULT";
   }

   public DEFAULT apply(final boolean fixed, final String attValue) {
      return new DEFAULT(fixed, attValue);
   }

   public Option unapply(final DEFAULT x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToBoolean(x$0.fixed()), x$0.attValue())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DEFAULT$.class);
   }

   private DEFAULT$() {
   }
}
