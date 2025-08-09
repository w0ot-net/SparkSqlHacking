package org.apache.spark.util;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class MutablePair$ implements Serializable {
   public static final MutablePair$ MODULE$ = new MutablePair$();

   public final String toString() {
      return "MutablePair";
   }

   public MutablePair apply(final Object _1, final Object _2) {
      return new MutablePair(_1, _2);
   }

   public Option unapply(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0._1(), x$0._2())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MutablePair$.class);
   }

   public MutablePair apply$mZZc$sp(final boolean _1, final boolean _2) {
      return new MutablePair$mcZZ$sp(_1, _2);
   }

   public MutablePair apply$mZCc$sp(final boolean _1, final char _2) {
      return new MutablePair$mcZC$sp(_1, _2);
   }

   public MutablePair apply$mZDc$sp(final boolean _1, final double _2) {
      return new MutablePair$mcZD$sp(_1, _2);
   }

   public MutablePair apply$mZIc$sp(final boolean _1, final int _2) {
      return new MutablePair$mcZI$sp(_1, _2);
   }

   public MutablePair apply$mZJc$sp(final boolean _1, final long _2) {
      return new MutablePair$mcZJ$sp(_1, _2);
   }

   public MutablePair apply$mCZc$sp(final char _1, final boolean _2) {
      return new MutablePair$mcCZ$sp(_1, _2);
   }

   public MutablePair apply$mCCc$sp(final char _1, final char _2) {
      return new MutablePair$mcCC$sp(_1, _2);
   }

   public MutablePair apply$mCDc$sp(final char _1, final double _2) {
      return new MutablePair$mcCD$sp(_1, _2);
   }

   public MutablePair apply$mCIc$sp(final char _1, final int _2) {
      return new MutablePair$mcCI$sp(_1, _2);
   }

   public MutablePair apply$mCJc$sp(final char _1, final long _2) {
      return new MutablePair$mcCJ$sp(_1, _2);
   }

   public MutablePair apply$mDZc$sp(final double _1, final boolean _2) {
      return new MutablePair$mcDZ$sp(_1, _2);
   }

   public MutablePair apply$mDCc$sp(final double _1, final char _2) {
      return new MutablePair$mcDC$sp(_1, _2);
   }

   public MutablePair apply$mDDc$sp(final double _1, final double _2) {
      return new MutablePair$mcDD$sp(_1, _2);
   }

   public MutablePair apply$mDIc$sp(final double _1, final int _2) {
      return new MutablePair$mcDI$sp(_1, _2);
   }

   public MutablePair apply$mDJc$sp(final double _1, final long _2) {
      return new MutablePair$mcDJ$sp(_1, _2);
   }

   public MutablePair apply$mIZc$sp(final int _1, final boolean _2) {
      return new MutablePair$mcIZ$sp(_1, _2);
   }

   public MutablePair apply$mICc$sp(final int _1, final char _2) {
      return new MutablePair$mcIC$sp(_1, _2);
   }

   public MutablePair apply$mIDc$sp(final int _1, final double _2) {
      return new MutablePair$mcID$sp(_1, _2);
   }

   public MutablePair apply$mIIc$sp(final int _1, final int _2) {
      return new MutablePair$mcII$sp(_1, _2);
   }

   public MutablePair apply$mIJc$sp(final int _1, final long _2) {
      return new MutablePair$mcIJ$sp(_1, _2);
   }

   public MutablePair apply$mJZc$sp(final long _1, final boolean _2) {
      return new MutablePair$mcJZ$sp(_1, _2);
   }

   public MutablePair apply$mJCc$sp(final long _1, final char _2) {
      return new MutablePair$mcJC$sp(_1, _2);
   }

   public MutablePair apply$mJDc$sp(final long _1, final double _2) {
      return new MutablePair$mcJD$sp(_1, _2);
   }

   public MutablePair apply$mJIc$sp(final long _1, final int _2) {
      return new MutablePair$mcJI$sp(_1, _2);
   }

   public MutablePair apply$mJJc$sp(final long _1, final long _2) {
      return new MutablePair$mcJJ$sp(_1, _2);
   }

   public Option unapply$mZZc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcZZ.sp(x$0._1$mcZ$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mZCc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcZC.sp(x$0._1$mcZ$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mZDc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcZD.sp(x$0._1$mcZ$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mZIc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcZI.sp(x$0._1$mcZ$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mZJc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcZJ.sp(x$0._1$mcZ$sp(), x$0._2$mcJ$sp())));
   }

   public Option unapply$mCZc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcCZ.sp(x$0._1$mcC$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mCCc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcCC.sp(x$0._1$mcC$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mCDc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcCD.sp(x$0._1$mcC$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mCIc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcCI.sp(x$0._1$mcC$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mCJc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcCJ.sp(x$0._1$mcC$sp(), x$0._2$mcJ$sp())));
   }

   public Option unapply$mDZc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDZ.sp(x$0._1$mcD$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mDCc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDC.sp(x$0._1$mcD$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mDDc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDD.sp(x$0._1$mcD$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mDIc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDI.sp(x$0._1$mcD$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mDJc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcDJ.sp(x$0._1$mcD$sp(), x$0._2$mcJ$sp())));
   }

   public Option unapply$mIZc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcIZ.sp(x$0._1$mcI$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mICc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcIC.sp(x$0._1$mcI$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mIDc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcID.sp(x$0._1$mcI$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mIIc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcII.sp(x$0._1$mcI$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mIJc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcIJ.sp(x$0._1$mcI$sp(), x$0._2$mcJ$sp())));
   }

   public Option unapply$mJZc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcJZ.sp(x$0._1$mcJ$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mJCc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcJC.sp(x$0._1$mcJ$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mJDc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcJD.sp(x$0._1$mcJ$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mJIc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcJI.sp(x$0._1$mcJ$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mJJc$sp(final MutablePair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcJJ.sp(x$0._1$mcJ$sp(), x$0._2$mcJ$sp())));
   }

   private MutablePair$() {
   }
}
