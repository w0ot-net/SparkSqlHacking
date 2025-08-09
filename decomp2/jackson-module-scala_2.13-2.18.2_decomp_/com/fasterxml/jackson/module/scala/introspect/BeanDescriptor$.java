package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class BeanDescriptor$ extends AbstractFunction2 implements Serializable {
   public static final BeanDescriptor$ MODULE$ = new BeanDescriptor$();

   public final String toString() {
      return "BeanDescriptor";
   }

   public BeanDescriptor apply(final Class beanType, final Seq properties) {
      return new BeanDescriptor(beanType, properties);
   }

   public Option unapply(final BeanDescriptor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.beanType(), x$0.properties())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BeanDescriptor$.class);
   }

   private BeanDescriptor$() {
   }
}
