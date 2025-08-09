package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcZI$sp extends MutablePair {
   public boolean _1$mcZ$sp;
   public int _2$mcI$sp;

   public boolean _1$mcZ$sp() {
      return this._1$mcZ$sp;
   }

   public boolean _1() {
      return this._1$mcZ$sp();
   }

   public void _1$mcZ$sp_$eq(final boolean x$1) {
      this._1$mcZ$sp = x$1;
   }

   public void _1_$eq(final boolean x$1) {
      this._1$mcZ$sp_$eq(x$1);
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

   public MutablePair update(final boolean n1, final int n2) {
      return this.update$mcZI$sp(n1, n2);
   }

   public MutablePair update$mcZI$sp(final boolean n1, final int n2) {
      this._1_$eq(n1);
      this._2_$eq(n2);
      return this;
   }

   public boolean copy$default$1() {
      return this.copy$default$1$mcZ$sp();
   }

   public boolean copy$default$1$mcZ$sp() {
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

   public MutablePair$mcZI$sp(final boolean _1$mcZ$sp, final int _2$mcI$sp) {
      this._1$mcZ$sp = _1$mcZ$sp;
      this._2$mcI$sp = _2$mcI$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcZI$sp() {
      this(BoxesRunTime.unboxToBoolean((Object)null), BoxesRunTime.unboxToInt((Object)null));
   }
}
