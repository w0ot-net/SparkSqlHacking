package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcDC$sp extends MutablePair {
   public double _1$mcD$sp;
   public char _2$mcC$sp;

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

   public char _2$mcC$sp() {
      return this._2$mcC$sp;
   }

   public char _2() {
      return this._2$mcC$sp();
   }

   public void _2$mcC$sp_$eq(final char x$1) {
      this._2$mcC$sp = x$1;
   }

   public void _2_$eq(final char x$1) {
      this._2$mcC$sp_$eq(x$1);
   }

   public MutablePair update(final double n1, final char n2) {
      return this.update$mcDC$sp(n1, n2);
   }

   public MutablePair update$mcDC$sp(final double n1, final char n2) {
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

   public char copy$default$2() {
      return this.copy$default$2$mcC$sp();
   }

   public char copy$default$2$mcC$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcDC$sp(final double _1$mcD$sp, final char _2$mcC$sp) {
      this._1$mcD$sp = _1$mcD$sp;
      this._2$mcC$sp = _2$mcC$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcDC$sp() {
      this(BoxesRunTime.unboxToDouble((Object)null), BoxesRunTime.unboxToChar((Object)null));
   }
}
