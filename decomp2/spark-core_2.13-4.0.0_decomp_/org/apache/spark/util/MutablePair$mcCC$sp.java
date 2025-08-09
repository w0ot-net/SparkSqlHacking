package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcCC$sp extends MutablePair {
   public char _1$mcC$sp;
   public char _2$mcC$sp;

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

   public MutablePair update(final char n1, final char n2) {
      return this.update$mcCC$sp(n1, n2);
   }

   public MutablePair update$mcCC$sp(final char n1, final char n2) {
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

   public char copy$default$2() {
      return this.copy$default$2$mcC$sp();
   }

   public char copy$default$2$mcC$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcCC$sp(final char _1$mcC$sp, final char _2$mcC$sp) {
      this._1$mcC$sp = _1$mcC$sp;
      this._2$mcC$sp = _2$mcC$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcCC$sp() {
      this(BoxesRunTime.unboxToChar((Object)null), BoxesRunTime.unboxToChar((Object)null));
   }
}
