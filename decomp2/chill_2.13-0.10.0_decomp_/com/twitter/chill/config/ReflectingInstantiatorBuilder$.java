package com.twitter.chill.config;

import com.esotericsoftware.kryo.Kryo;
import java.io.Serializable;
import org.objenesis.strategy.StdInstantiatorStrategy;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.collection.Iterable;
import scala.package.;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ReflectingInstantiatorBuilder$ extends AbstractFunction7 implements Serializable {
   public static final ReflectingInstantiatorBuilder$ MODULE$ = new ReflectingInstantiatorBuilder$();

   public Class $lessinit$greater$default$1() {
      return Kryo.class;
   }

   public Class $lessinit$greater$default$2() {
      return StdInstantiatorStrategy.class;
   }

   public Iterable $lessinit$greater$default$3() {
      return .MODULE$.Nil();
   }

   public Iterable $lessinit$greater$default$4() {
      return .MODULE$.Nil();
   }

   public Iterable $lessinit$greater$default$5() {
      return .MODULE$.Nil();
   }

   public boolean $lessinit$greater$default$6() {
      return false;
   }

   public boolean $lessinit$greater$default$7() {
      return false;
   }

   public final String toString() {
      return "ReflectingInstantiatorBuilder";
   }

   public ReflectingInstantiatorBuilder apply(final Class kryoClass, final Class instantiatorStrategyClass, final Iterable classes, final Iterable serializers, final Iterable defaults, final boolean registrationRequired, final boolean skipMissing) {
      return new ReflectingInstantiatorBuilder(kryoClass, instantiatorStrategyClass, classes, serializers, defaults, registrationRequired, skipMissing);
   }

   public Class apply$default$1() {
      return Kryo.class;
   }

   public Class apply$default$2() {
      return StdInstantiatorStrategy.class;
   }

   public Iterable apply$default$3() {
      return .MODULE$.Nil();
   }

   public Iterable apply$default$4() {
      return .MODULE$.Nil();
   }

   public Iterable apply$default$5() {
      return .MODULE$.Nil();
   }

   public boolean apply$default$6() {
      return false;
   }

   public boolean apply$default$7() {
      return false;
   }

   public Option unapply(final ReflectingInstantiatorBuilder x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple7(x$0.kryoClass(), x$0.instantiatorStrategyClass(), x$0.classes(), x$0.serializers(), x$0.defaults(), BoxesRunTime.boxToBoolean(x$0.registrationRequired()), BoxesRunTime.boxToBoolean(x$0.skipMissing()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReflectingInstantiatorBuilder$.class);
   }

   private ReflectingInstantiatorBuilder$() {
   }
}
