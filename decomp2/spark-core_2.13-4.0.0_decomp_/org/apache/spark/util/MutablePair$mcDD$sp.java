package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.Product2;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcDD$sp extends MutablePair implements Product2.mcDD.sp {
   public double _1$mcD$sp;
   public double _2$mcD$sp;

   public double _1$mcD$sp() {
      return this._1$mcD$sp;
   }

   public double _1() {
      return this._1$mcD$sp();
   }

   public void _1$mcD$sp_$eq(final double x$1) {
      this._1$mcD$sp = x$1;
   }

   public void _1_$eq(final double x$1) {
      this._1$mcD$sp_$eq(x$1);
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

   public MutablePair update(final double n1, final double n2) {
      return this.update$mcDD$sp(n1, n2);
   }

   public MutablePair update$mcDD$sp(final double n1, final double n2) {
      this._1_$eq(n1);
      this._2_$eq(n2);
      return this;
   }

   public double copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public double copy$default$1$mcD$sp() {
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

   public MutablePair$mcDD$sp(final double _1$mcD$sp, final double _2$mcD$sp) {
      this._1$mcD$sp = _1$mcD$sp;
      this._2$mcD$sp = _2$mcD$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcDD$sp() {
      this(BoxesRunTime.unboxToDouble((Object)null), BoxesRunTime.unboxToDouble((Object)null));
   }
}
