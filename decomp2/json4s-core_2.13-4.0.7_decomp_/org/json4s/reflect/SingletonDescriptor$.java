package org.json4s.reflect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction5;
import scala.runtime.ModuleSerializationProxy;

public final class SingletonDescriptor$ extends AbstractFunction5 implements Serializable {
   public static final SingletonDescriptor$ MODULE$ = new SingletonDescriptor$();

   public final String toString() {
      return "SingletonDescriptor";
   }

   public SingletonDescriptor apply(final String simpleName, final String fullName, final ScalaType erasure, final Object instance, final Seq properties) {
      return new SingletonDescriptor(simpleName, fullName, erasure, instance, properties);
   }

   public Option unapply(final SingletonDescriptor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.simpleName(), x$0.fullName(), x$0.erasure(), x$0.instance(), x$0.properties())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SingletonDescriptor$.class);
   }

   private SingletonDescriptor$() {
   }
}
