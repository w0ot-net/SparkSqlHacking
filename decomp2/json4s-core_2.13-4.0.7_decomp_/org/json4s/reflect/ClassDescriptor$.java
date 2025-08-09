package org.json4s.reflect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction6;
import scala.runtime.ModuleSerializationProxy;

public final class ClassDescriptor$ extends AbstractFunction6 implements Serializable {
   public static final ClassDescriptor$ MODULE$ = new ClassDescriptor$();

   public final String toString() {
      return "ClassDescriptor";
   }

   public ClassDescriptor apply(final String simpleName, final String fullName, final ScalaType erasure, final Option companion, final Seq constructors, final Seq properties) {
      return new ClassDescriptor(simpleName, fullName, erasure, companion, constructors, properties);
   }

   public Option unapply(final ClassDescriptor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.simpleName(), x$0.fullName(), x$0.erasure(), x$0.companion(), x$0.constructors(), x$0.properties())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassDescriptor$.class);
   }

   private ClassDescriptor$() {
   }
}
