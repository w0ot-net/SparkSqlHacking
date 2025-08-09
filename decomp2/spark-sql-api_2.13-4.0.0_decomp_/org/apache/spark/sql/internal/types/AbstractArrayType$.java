package org.apache.spark.sql.internal.types;

import java.io.Serializable;
import org.apache.spark.sql.types.AbstractDataType;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class AbstractArrayType$ extends AbstractFunction1 implements Serializable {
   public static final AbstractArrayType$ MODULE$ = new AbstractArrayType$();

   public final String toString() {
      return "AbstractArrayType";
   }

   public AbstractArrayType apply(final AbstractDataType elementType) {
      return new AbstractArrayType(elementType);
   }

   public Option unapply(final AbstractArrayType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.elementType()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AbstractArrayType$.class);
   }

   private AbstractArrayType$() {
   }
}
