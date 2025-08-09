package org.apache.spark.sql.internal.types;

import java.io.Serializable;
import org.apache.spark.sql.types.AbstractDataType;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class AbstractMapType$ extends AbstractFunction2 implements Serializable {
   public static final AbstractMapType$ MODULE$ = new AbstractMapType$();

   public final String toString() {
      return "AbstractMapType";
   }

   public AbstractMapType apply(final AbstractDataType keyType, final AbstractDataType valueType) {
      return new AbstractMapType(keyType, valueType);
   }

   public Option unapply(final AbstractMapType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.keyType(), x$0.valueType())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AbstractMapType$.class);
   }

   private AbstractMapType$() {
   }
}
