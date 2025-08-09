package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcDZ$sp extends MutablePair {
   public double _1$mcD$sp;
   public boolean _2$mcZ$sp;

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

   public boolean _2$mcZ$sp() {
      return this._2$mcZ$sp;
   }

   public boolean _2() {
      return this._2$mcZ$sp();
   }

   public void _2$mcZ$sp_$eq(final boolean x$1) {
      this._2$mcZ$sp = x$1;
   }

   public void _2_$eq(final boolean x$1) {
      this._2$mcZ$sp_$eq(x$1);
   }

   public MutablePair update(final double n1, final boolean n2) {
      return this.update$mcDZ$sp(n1, n2);
   }

   public MutablePair update$mcDZ$sp(final double n1, final boolean n2) {
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

   public boolean copy$default$2() {
      return this.copy$default$2$mcZ$sp();
   }

   public boolean copy$default$2$mcZ$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcDZ$sp(final double _1$mcD$sp, final boolean _2$mcZ$sp) {
      this._1$mcD$sp = _1$mcD$sp;
      this._2$mcZ$sp = _2$mcZ$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcDZ$sp() {
      this(BoxesRunTime.unboxToDouble((Object)null), BoxesRunTime.unboxToBoolean((Object)null));
   }
}
