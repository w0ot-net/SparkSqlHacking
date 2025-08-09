package org.apache.spark.sql.hive;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class HiveGenericUDTF$ extends AbstractFunction3 implements Serializable {
   public static final HiveGenericUDTF$ MODULE$ = new HiveGenericUDTF$();

   public final String toString() {
      return "HiveGenericUDTF";
   }

   public HiveGenericUDTF apply(final String name, final HiveShim.HiveFunctionWrapper funcWrapper, final Seq children) {
      return new HiveGenericUDTF(name, funcWrapper, children);
   }

   public Option unapply(final HiveGenericUDTF x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.name(), x$0.funcWrapper(), x$0.children())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HiveGenericUDTF$.class);
   }

   private HiveGenericUDTF$() {
   }
}
