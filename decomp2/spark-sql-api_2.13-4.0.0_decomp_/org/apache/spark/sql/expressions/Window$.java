package org.apache.spark.sql.expressions;

import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.Column;
import scala.collection.immutable.Seq;
import scala.runtime.ScalaRunTime.;

@Stable
public final class Window$ {
   public static final Window$ MODULE$ = new Window$();

   public WindowSpec partitionBy(final String colName, final String... colNames) {
      return this.partitionBy(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public WindowSpec partitionBy(final Column... cols) {
      return this.partitionBy((Seq).MODULE$.wrapRefArray(cols));
   }

   public WindowSpec orderBy(final String colName, final String... colNames) {
      return this.orderBy(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public WindowSpec orderBy(final Column... cols) {
      return this.orderBy((Seq).MODULE$.wrapRefArray(cols));
   }

   public WindowSpec partitionBy(final String colName, final Seq colNames) {
      return this.spec().partitionBy(colName, colNames);
   }

   public WindowSpec partitionBy(final Seq cols) {
      return this.spec().partitionBy(cols);
   }

   public WindowSpec orderBy(final String colName, final Seq colNames) {
      return this.spec().orderBy(colName, colNames);
   }

   public WindowSpec orderBy(final Seq cols) {
      return this.spec().orderBy(cols);
   }

   public long unboundedPreceding() {
      return Long.MIN_VALUE;
   }

   public long unboundedFollowing() {
      return Long.MAX_VALUE;
   }

   public long currentRow() {
      return 0L;
   }

   public WindowSpec rowsBetween(final long start, final long end) {
      return this.spec().rowsBetween(start, end);
   }

   public WindowSpec rangeBetween(final long start, final long end) {
      return this.spec().rangeBetween(start, end);
   }

   public WindowSpec spec() {
      return new WindowSpec((Seq)scala.package..MODULE$.Seq().empty(), (Seq)scala.package..MODULE$.Seq().empty(), scala.None..MODULE$);
   }

   private Window$() {
   }
}
