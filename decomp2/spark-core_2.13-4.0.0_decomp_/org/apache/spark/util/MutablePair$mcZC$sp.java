package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcZC$sp extends MutablePair {
   public boolean _1$mcZ$sp;
   public char _2$mcC$sp;

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

   public MutablePair update(final boolean n1, final char n2) {
      return this.update$mcZC$sp(n1, n2);
   }

   public MutablePair update$mcZC$sp(final boolean n1, final char n2) {
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

   public char copy$default$2() {
      return this.copy$default$2$mcC$sp();
   }

   public char copy$default$2$mcC$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcZC$sp(final boolean _1$mcZ$sp, final char _2$mcC$sp) {
      this._1$mcZ$sp = _1$mcZ$sp;
      this._2$mcC$sp = _2$mcC$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcZC$sp() {
      this(BoxesRunTime.unboxToBoolean((Object)null), BoxesRunTime.unboxToChar((Object)null));
   }
}
