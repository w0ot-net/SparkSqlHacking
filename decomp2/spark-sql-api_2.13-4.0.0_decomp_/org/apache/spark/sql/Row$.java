package org.apache.spark.sql;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.expressions.GenericRow;
import scala.Product;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.runtime.ModuleSerializationProxy;

@Stable
public final class Row$ implements Serializable {
   public static final Row$ MODULE$ = new Row$();
   private static final Row empty;

   static {
      empty = MODULE$.apply(.MODULE$);
   }

   public Some unapplySeq(final Row row) {
      return new Some(row.toSeq());
   }

   public Row apply(final Seq values) {
      return new GenericRow(values.toArray(scala.reflect.ClassTag..MODULE$.Any()));
   }

   public Row fromSeq(final Seq values) {
      return new GenericRow(values.toArray(scala.reflect.ClassTag..MODULE$.Any()));
   }

   public Row fromTuple(final Product tuple) {
      return this.fromSeq(tuple.productIterator().toSeq());
   }

   /** @deprecated */
   public Row merge(final Seq rows) {
      return new GenericRow(((IterableOnceOps)rows.flatMap((x$1) -> x$1.toSeq())).toArray(scala.reflect.ClassTag..MODULE$.Any()));
   }

   public Row empty() {
      return empty;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Row$.class);
   }

   private Row$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
