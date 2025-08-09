package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ConstructorParameter$ extends AbstractFunction3 implements Serializable {
   public static final ConstructorParameter$ MODULE$ = new ConstructorParameter$();

   public final String toString() {
      return "ConstructorParameter";
   }

   public ConstructorParameter apply(final Constructor constructor, final int index, final Option defaultValue) {
      return new ConstructorParameter(constructor, index, defaultValue);
   }

   public Option unapply(final ConstructorParameter x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.constructor(), BoxesRunTime.boxToInteger(x$0.index()), x$0.defaultValue())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ConstructorParameter$.class);
   }

   private ConstructorParameter$() {
   }
}
