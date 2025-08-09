package org.json4s.reflect;

import java.io.Serializable;
import java.lang.reflect.Field;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class PropertyDescriptor$ extends AbstractFunction4 implements Serializable {
   public static final PropertyDescriptor$ MODULE$ = new PropertyDescriptor$();

   public final String toString() {
      return "PropertyDescriptor";
   }

   public PropertyDescriptor apply(final String name, final String mangledName, final ScalaType returnType, final Field field) {
      return new PropertyDescriptor(name, mangledName, returnType, field);
   }

   public Option unapply(final PropertyDescriptor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.name(), x$0.mangledName(), x$0.returnType(), x$0.field())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PropertyDescriptor$.class);
   }

   private PropertyDescriptor$() {
   }
}
