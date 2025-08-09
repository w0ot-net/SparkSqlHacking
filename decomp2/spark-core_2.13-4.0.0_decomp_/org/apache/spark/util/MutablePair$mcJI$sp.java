package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.Product2;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcJI$sp extends MutablePair implements Product2.mcJI.sp {
   public long _1$mcJ$sp;
   public int _2$mcI$sp;

   public long _1$mcJ$sp() {
      return this._1$mcJ$sp;
   }

   public long _1() {
      return this._1$mcJ$sp();
   }

   public void _1$mcJ$sp_$eq(final long x$1) {
      this._1$mcJ$sp = x$1;
   }

   public void _1_$eq(final long x$1) {
      this._1$mcJ$sp_$eq(x$1);
   }

   public int _2$mcI$sp() {
      return this._2$mcI$sp;
   }

   public int _2() {
      return this._2$mcI$sp();
   }

   public void _2$mcI$sp_$eq(final int x$1) {
      this._2$mcI$sp = x$1;
   }

   public void _2_$eq(final int x$1) {
      this._2$mcI$sp_$eq(x$1);
   }

   public MutablePair update(final long n1, final int n2) {
      return this.update$mcJI$sp(n1, n2);
   }

   public MutablePair update$mcJI$sp(final long n1, final int n2) {
      this._1_$eq(n1);
      this._2_$eq(n2);
      return this;
   }

   public long copy$default$1() {
      return this.copy$default$1$mcJ$sp();
   }

   public long copy$default$1$mcJ$sp() {
      return this._1();
   }

   public int copy$default$2() {
      return this.copy$default$2$mcI$sp();
   }

   public int copy$default$2$mcI$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcJI$sp(final long _1$mcJ$sp, final int _2$mcI$sp) {
      this._1$mcJ$sp = _1$mcJ$sp;
      this._2$mcI$sp = _2$mcI$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcJI$sp() {
      this(BoxesRunTime.unboxToLong((Object)null), BoxesRunTime.unboxToInt((Object)null));
   }
}
