package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcZZ$sp extends MutablePair {
   public boolean _1$mcZ$sp;
   public boolean _2$mcZ$sp;

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

   public MutablePair update(final boolean n1, final boolean n2) {
      return this.update$mcZZ$sp(n1, n2);
   }

   public MutablePair update$mcZZ$sp(final boolean n1, final boolean n2) {
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

   public boolean copy$default$2() {
      return this.copy$default$2$mcZ$sp();
   }

   public boolean copy$default$2$mcZ$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcZZ$sp(final boolean _1$mcZ$sp, final boolean _2$mcZ$sp) {
      this._1$mcZ$sp = _1$mcZ$sp;
      this._2$mcZ$sp = _2$mcZ$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcZZ$sp() {
      this(BoxesRunTime.unboxToBoolean((Object)null), BoxesRunTime.unboxToBoolean((Object)null));
   }
}
