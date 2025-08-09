package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class AnnotInfo$ extends AbstractFunction1 implements Serializable {
   public static final AnnotInfo$ MODULE$ = new AnnotInfo$();

   public final String toString() {
      return "AnnotInfo";
   }

   public AnnotInfo apply(final Seq refs) {
      return new AnnotInfo(refs);
   }

   public Option unapply(final AnnotInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.refs()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AnnotInfo$.class);
   }

   private AnnotInfo$() {
   }
}
