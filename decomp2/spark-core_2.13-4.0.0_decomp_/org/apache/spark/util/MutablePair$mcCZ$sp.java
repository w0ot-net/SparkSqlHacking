package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcCZ$sp extends MutablePair {
   public char _1$mcC$sp;
   public boolean _2$mcZ$sp;

   public char _1$mcC$sp() {
      return this._1$mcC$sp;
   }

   public char _1() {
      return this._1$mcC$sp();
   }

   public void _1$mcC$sp_$eq(final char x$1) {
      this._1$mcC$sp = x$1;
   }

   public void _1_$eq(final char x$1) {
      this._1$mcC$sp_$eq(x$1);
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

   public MutablePair update(final char n1, final boolean n2) {
      return this.update$mcCZ$sp(n1, n2);
   }

   public MutablePair update$mcCZ$sp(final char n1, final boolean n2) {
      this._1_$eq(n1);
      this._2_$eq(n2);
      return this;
   }

   public char copy$default$1() {
      return this.copy$default$1$mcC$sp();
   }

   public char copy$default$1$mcC$sp() {
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

   public MutablePair$mcCZ$sp(final char _1$mcC$sp, final boolean _2$mcZ$sp) {
      this._1$mcC$sp = _1$mcC$sp;
      this._2$mcZ$sp = _2$mcZ$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcCZ$sp() {
      this(BoxesRunTime.unboxToChar((Object)null), BoxesRunTime.unboxToBoolean((Object)null));
   }
}
