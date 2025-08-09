package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ClassInfoType$ extends AbstractFunction2 implements Serializable {
   public static final ClassInfoType$ MODULE$ = new ClassInfoType$();

   public final String toString() {
      return "ClassInfoType";
   }

   public ClassInfoType apply(final Symbol symbol, final Seq typeRefs) {
      return new ClassInfoType(symbol, typeRefs);
   }

   public Option unapply(final ClassInfoType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.symbol(), x$0.typeRefs())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassInfoType$.class);
   }

   private ClassInfoType$() {
   }
}
