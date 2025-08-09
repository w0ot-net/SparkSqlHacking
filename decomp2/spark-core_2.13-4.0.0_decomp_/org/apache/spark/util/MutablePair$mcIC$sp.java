package org.apache.spark.util;

import org.apache.spark.annotation.DeveloperApi;
import scala.runtime.BoxesRunTime;

@DeveloperApi
public class MutablePair$mcIC$sp extends MutablePair {
   public int _1$mcI$sp;
   public char _2$mcC$sp;

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

   public MutablePair update(final int n1, final char n2) {
      return this.update$mcIC$sp(n1, n2);
   }

   public MutablePair update$mcIC$sp(final int n1, final char n2) {
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

   public char copy$default$2() {
      return this.copy$default$2$mcC$sp();
   }

   public char copy$default$2$mcC$sp() {
      return this._2();
   }

   public boolean specInstance$() {
      return true;
   }

   public MutablePair$mcIC$sp(final int _1$mcI$sp, final char _2$mcC$sp) {
      this._1$mcI$sp = _1$mcI$sp;
      this._2$mcC$sp = _2$mcC$sp;
      super((Object)null, (Object)null);
   }

   public MutablePair$mcIC$sp() {
      this(BoxesRunTime.unboxToInt((Object)null), BoxesRunTime.unboxToChar((Object)null));
   }
}
