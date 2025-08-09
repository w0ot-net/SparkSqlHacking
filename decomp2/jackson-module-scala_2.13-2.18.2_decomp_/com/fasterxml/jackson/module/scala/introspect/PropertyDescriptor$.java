package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple7;
import scala.None.;
import scala.runtime.AbstractFunction7;
import scala.runtime.ModuleSerializationProxy;

public final class PropertyDescriptor$ extends AbstractFunction7 implements Serializable {
   public static final PropertyDescriptor$ MODULE$ = new PropertyDescriptor$();

   public final String toString() {
      return "PropertyDescriptor";
   }

   public PropertyDescriptor apply(final String name, final Option param, final Option field, final Option getter, final Option setter, final Option beanGetter, final Option beanSetter) {
      return new PropertyDescriptor(name, param, field, getter, setter, beanGetter, beanSetter);
   }

   public Option unapply(final PropertyDescriptor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple7(x$0.name(), x$0.param(), x$0.field(), x$0.getter(), x$0.setter(), x$0.beanGetter(), x$0.beanSetter())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PropertyDescriptor$.class);
   }

   private PropertyDescriptor$() {
   }
}
