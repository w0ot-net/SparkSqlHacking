package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class AttributeInfo$ extends AbstractFunction4 implements Serializable {
   public static final AttributeInfo$ MODULE$ = new AttributeInfo$();

   public final String toString() {
      return "AttributeInfo";
   }

   public AttributeInfo apply(final Symbol symbol, final Type typeRef, final Option value, final Seq values) {
      return new AttributeInfo(symbol, typeRef, value, values);
   }

   public Option unapply(final AttributeInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.symbol(), x$0.typeRef(), x$0.value(), x$0.values())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AttributeInfo$.class);
   }

   private AttributeInfo$() {
   }
}
