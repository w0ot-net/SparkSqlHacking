package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@Stable
public final class MapType$ extends AbstractDataType implements Serializable {
   public static final MapType$ MODULE$ = new MapType$();

   public DataType defaultConcreteType() {
      return this.apply(NullType$.MODULE$, NullType$.MODULE$);
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof MapType;
   }

   public String simpleString() {
      return "map";
   }

   public MapType apply(final DataType keyType, final DataType valueType) {
      return new MapType(keyType, valueType, true);
   }

   public MapType apply(final DataType keyType, final DataType valueType, final boolean valueContainsNull) {
      return new MapType(keyType, valueType, valueContainsNull);
   }

   public Option unapply(final MapType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.keyType(), x$0.valueType(), BoxesRunTime.boxToBoolean(x$0.valueContainsNull()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MapType$.class);
   }

   private MapType$() {
   }
}
