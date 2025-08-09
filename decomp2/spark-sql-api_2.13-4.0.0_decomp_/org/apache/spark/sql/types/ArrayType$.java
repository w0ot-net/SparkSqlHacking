package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@Stable
public final class ArrayType$ extends AbstractDataType implements Serializable {
   public static final ArrayType$ MODULE$ = new ArrayType$();

   public ArrayType apply(final DataType elementType) {
      return new ArrayType(elementType, true);
   }

   public DataType defaultConcreteType() {
      return new ArrayType(NullType$.MODULE$, true);
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof ArrayType;
   }

   public String simpleString() {
      return "array";
   }

   public ArrayType apply(final DataType elementType, final boolean containsNull) {
      return new ArrayType(elementType, containsNull);
   }

   public Option unapply(final ArrayType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.elementType(), BoxesRunTime.boxToBoolean(x$0.containsNull()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArrayType$.class);
   }

   private ArrayType$() {
   }
}
