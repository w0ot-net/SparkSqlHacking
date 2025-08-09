package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ClassHolder$ extends AbstractFunction1 implements Serializable {
   public static final ClassHolder$ MODULE$ = new ClassHolder$();

   public Option $lessinit$greater$default$1() {
      return .MODULE$;
   }

   public final String toString() {
      return "ClassHolder";
   }

   public ClassHolder apply(final Option valueClass) {
      return new ClassHolder(valueClass);
   }

   public Option apply$default$1() {
      return .MODULE$;
   }

   public Option unapply(final ClassHolder x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.valueClass()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassHolder$.class);
   }

   private ClassHolder$() {
   }
}
