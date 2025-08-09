package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcJC$sp extends MutablePair {
   public long _1$mcJ$sp;
   public char _2$mcC$sp;

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

   public MutablePair update(final long n1, final char n2) {
      return this.update$mcJC$sp(n1, n2);
   }

   public MutablePair update$mcJC$sp(final long n1, final char n2) {
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

   public char copy$default$2() {
      return this.copy$default$2$mcC$sp();
   }

   public char copy$default$2$mcC$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcJC$sp(final long _1$mcJ$sp, final char _2$mcC$sp) {
      this._1$mcJ$sp = _1$mcJ$sp;
      this._2$mcC$sp = _2$mcC$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcJC$sp() {
      this(BoxesRunTime.unboxToLong((Object)null), BoxesRunTime.unboxToChar((Object)null));
   }
}
