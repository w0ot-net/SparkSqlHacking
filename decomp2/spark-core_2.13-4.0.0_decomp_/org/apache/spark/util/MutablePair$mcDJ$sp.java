package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.Product2;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcDJ$sp extends MutablePair implements Product2.mcDJ.sp {
   public double _1$mcD$sp;
   public long _2$mcJ$sp;

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

   public long _2$mcJ$sp() {
      return this._2$mcJ$sp;
   }

   public long _2() {
      return this._2$mcJ$sp();
   }

   public void _2$mcJ$sp_$eq(final long x$1) {
      this._2$mcJ$sp = x$1;
   }

   public void _2_$eq(final long x$1) {
      this._2$mcJ$sp_$eq(x$1);
   }

   public MutablePair update(final double n1, final long n2) {
      return this.update$mcDJ$sp(n1, n2);
   }

   public MutablePair update$mcDJ$sp(final double n1, final long n2) {
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

   public long copy$default$2() {
      return this.copy$default$2$mcJ$sp();
   }

   public long copy$default$2$mcJ$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcDJ$sp(final double _1$mcD$sp, final long _2$mcJ$sp) {
      this._1$mcD$sp = _1$mcD$sp;
      this._2$mcJ$sp = _2$mcJ$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcDJ$sp() {
      this(BoxesRunTime.unboxToDouble((Object)null), BoxesRunTime.unboxToLong((Object)null));
   }
}
