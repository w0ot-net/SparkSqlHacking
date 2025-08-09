package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class ObjectType$ extends AbstractDataType implements Serializable {
   public static final ObjectType$ MODULE$ = new ObjectType$();

   public DataType defaultConcreteType() {
      throw DataTypeErrors$.MODULE$.nullLiteralsCannotBeCastedError(this.simpleString());
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof ObjectType;
   }

   public String simpleString() {
      return "Object";
   }

   public ObjectType apply(final Class cls) {
      return new ObjectType(cls);
   }

   public Option unapply(final ObjectType x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.cls()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ObjectType$.class);
   }

   private ObjectType$() {
   }
}
