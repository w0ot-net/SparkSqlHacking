package org.json4s.reflect;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class TypeInfo$ extends AbstractFunction2 implements Serializable {
   public static final TypeInfo$ MODULE$ = new TypeInfo$();

   public final String toString() {
      return "TypeInfo";
   }

   public TypeInfo apply(final Class clazz, final Option parameterizedType) {
      return new TypeInfo(clazz, parameterizedType);
   }

   public Option unapply(final TypeInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.clazz(), x$0.parameterizedType())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TypeInfo$.class);
   }

   private TypeInfo$() {
   }
}
