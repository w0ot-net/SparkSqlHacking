package org.json4s.reflect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ConstructorParamDescriptor$ extends AbstractFunction5 implements Serializable {
   public static final ConstructorParamDescriptor$ MODULE$ = new ConstructorParamDescriptor$();

   public final String toString() {
      return "ConstructorParamDescriptor";
   }

   public ConstructorParamDescriptor apply(final String name, final String mangledName, final int argIndex, final ScalaType argType, final Option defaultValue) {
      return new ConstructorParamDescriptor(name, mangledName, argIndex, argType, defaultValue);
   }

   public Option unapply(final ConstructorParamDescriptor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.name(), x$0.mangledName(), BoxesRunTime.boxToInteger(x$0.argIndex()), x$0.argType(), x$0.defaultValue())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ConstructorParamDescriptor$.class);
   }

   private ConstructorParamDescriptor$() {
   }
}
