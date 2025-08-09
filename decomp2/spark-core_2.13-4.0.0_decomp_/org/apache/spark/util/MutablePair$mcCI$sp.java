package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcCI$sp extends MutablePair {
   public char _1$mcC$sp;
   public int _2$mcI$sp;

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

   public MutablePair update(final char n1, final int n2) {
      return this.update$mcCI$sp(n1, n2);
   }

   public MutablePair update$mcCI$sp(final char n1, final int n2) {
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

   public int copy$default$2() {
      return this.copy$default$2$mcI$sp();
   }

   public int copy$default$2$mcI$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcCI$sp(final char _1$mcC$sp, final int _2$mcI$sp) {
      this._1$mcC$sp = _1$mcC$sp;
      this._2$mcI$sp = _2$mcI$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcCI$sp() {
      this(BoxesRunTime.unboxToChar((Object)null), BoxesRunTime.unboxToInt((Object)null));
   }
}
