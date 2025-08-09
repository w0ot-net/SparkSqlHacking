package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcIZ$sp extends MutablePair {
   public int _1$mcI$sp;
   public boolean _2$mcZ$sp;

   public int _1$mcI$sp() {
      return this._1$mcI$sp;
   }

   public int _1() {
      return this._1$mcI$sp();
   }

   public void _1$mcI$sp_$eq(final int x$1) {
      this._1$mcI$sp = x$1;
   }

   public void _1_$eq(final int x$1) {
      this._1$mcI$sp_$eq(x$1);
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

   public MutablePair update(final int n1, final boolean n2) {
      return this.update$mcIZ$sp(n1, n2);
   }

   public MutablePair update$mcIZ$sp(final int n1, final boolean n2) {
      this._1_$eq(n1);
      this._2_$eq(n2);
      return this;
   }

   public int copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public int copy$default$1$mcI$sp() {
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

   public MutablePair$mcIZ$sp(final int _1$mcI$sp, final boolean _2$mcZ$sp) {
      this._1$mcI$sp = _1$mcI$sp;
      this._2$mcZ$sp = _2$mcZ$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcIZ$sp() {
      this(BoxesRunTime.unboxToInt((Object)null), BoxesRunTime.unboxToBoolean((Object)null));
   }
}
