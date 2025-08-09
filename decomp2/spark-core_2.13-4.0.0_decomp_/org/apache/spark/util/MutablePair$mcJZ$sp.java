package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcJZ$sp extends MutablePair {
   public long _1$mcJ$sp;
   public boolean _2$mcZ$sp;

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

   public MutablePair update(final long n1, final boolean n2) {
      return this.update$mcJZ$sp(n1, n2);
   }

   public MutablePair update$mcJZ$sp(final long n1, final boolean n2) {
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

   public boolean copy$default$2() {
      return this.copy$default$2$mcZ$sp();
   }

   public boolean copy$default$2$mcZ$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcJZ$sp(final long _1$mcJ$sp, final boolean _2$mcZ$sp) {
      this._1$mcJ$sp = _1$mcJ$sp;
      this._2$mcZ$sp = _2$mcZ$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcJZ$sp() {
      this(BoxesRunTime.unboxToLong((Object)null), BoxesRunTime.unboxToBoolean((Object)null));
   }
}
