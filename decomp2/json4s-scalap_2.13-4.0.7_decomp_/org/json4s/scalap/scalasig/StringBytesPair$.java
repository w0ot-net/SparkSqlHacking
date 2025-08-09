package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class StringBytesPair$ extends AbstractFunction2 implements Serializable {
   public static final StringBytesPair$ MODULE$ = new StringBytesPair$();

   public final String toString() {
      return "StringBytesPair";
   }

   public StringBytesPair apply(final String string, final byte[] bytes) {
      return new StringBytesPair(string, bytes);
   }

   public Option unapply(final StringBytesPair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.string(), x$0.bytes())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringBytesPair$.class);
   }

   private StringBytesPair$() {
   }
}
