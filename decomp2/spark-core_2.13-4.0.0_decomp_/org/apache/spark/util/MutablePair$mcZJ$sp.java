package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcZJ$sp extends MutablePair {
   public boolean _1$mcZ$sp;
   public long _2$mcJ$sp;

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

   public MutablePair update(final boolean n1, final long n2) {
      return this.update$mcZJ$sp(n1, n2);
   }

   public MutablePair update$mcZJ$sp(final boolean n1, final long n2) {
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

   public long copy$default$2() {
      return this.copy$default$2$mcJ$sp();
   }

   public long copy$default$2$mcJ$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcZJ$sp(final boolean _1$mcZ$sp, final long _2$mcJ$sp) {
      this._1$mcZ$sp = _1$mcZ$sp;
      this._2$mcJ$sp = _2$mcJ$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcZJ$sp() {
      this(BoxesRunTime.unboxToBoolean((Object)null), BoxesRunTime.unboxToLong((Object)null));
   }
}
