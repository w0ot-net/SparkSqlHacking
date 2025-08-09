package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ClassOverrides$ extends AbstractFunction1 implements Serializable {
   public static final ClassOverrides$ MODULE$ = new ClassOverrides$();

   public Map $lessinit$greater$default$1() {
      return (Map).MODULE$.empty();
   }

   public final String toString() {
      return "ClassOverrides";
   }

   public ClassOverrides apply(final Map overrides) {
      return new ClassOverrides(overrides);
   }

   public Map apply$default$1() {
      return (Map).MODULE$.empty();
   }

   public Option unapply(final ClassOverrides x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.overrides()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassOverrides$.class);
   }

   private ClassOverrides$() {
   }
}
