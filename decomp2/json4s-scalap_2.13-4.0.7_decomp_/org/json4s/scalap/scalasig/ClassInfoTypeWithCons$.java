package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class ClassInfoTypeWithCons$ extends AbstractFunction3 implements Serializable {
   public static final ClassInfoTypeWithCons$ MODULE$ = new ClassInfoTypeWithCons$();

   public final String toString() {
      return "ClassInfoTypeWithCons";
   }

   public ClassInfoTypeWithCons apply(final Symbol symbol, final Seq typeRefs, final String cons) {
      return new ClassInfoTypeWithCons(symbol, typeRefs, cons);
   }

   public Option unapply(final ClassInfoTypeWithCons x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.symbol(), x$0.typeRefs(), x$0.cons())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassInfoTypeWithCons$.class);
   }

   private ClassInfoTypeWithCons$() {
   }
}
