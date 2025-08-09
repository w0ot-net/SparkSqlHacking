package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.Product2;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcJD$sp extends MutablePair implements Product2.mcJD.sp {
   public long _1$mcJ$sp;
   public double _2$mcD$sp;

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

   public double _2$mcD$sp() {
      return this._2$mcD$sp;
   }

   public double _2() {
      return this._2$mcD$sp();
   }

   public void _2$mcD$sp_$eq(final double x$1) {
      this._2$mcD$sp = x$1;
   }

   public void _2_$eq(final double x$1) {
      this._2$mcD$sp_$eq(x$1);
   }

   public MutablePair update(final long n1, final double n2) {
      return this.update$mcJD$sp(n1, n2);
   }

   public MutablePair update$mcJD$sp(final long n1, final double n2) {
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

   public double copy$default$2() {
      return this.copy$default$2$mcD$sp();
   }

   public double copy$default$2$mcD$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcJD$sp(final long _1$mcJ$sp, final double _2$mcD$sp) {
      this._1$mcJ$sp = _1$mcJ$sp;
      this._2$mcD$sp = _2$mcD$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcJD$sp() {
      this(BoxesRunTime.unboxToLong((Object)null), BoxesRunTime.unboxToDouble((Object)null));
   }
}
