package scala;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Tuple2$ implements Serializable {
   public static final Tuple2$ MODULE$ = new Tuple2$();

   public final String toString() {
      return "Tuple2";
   }

   public Tuple2 apply(final Object _1, final Object _2) {
      return new Tuple2(_1, _2);
   }

   public Option unapply(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2(x$0._1(), x$0._2())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Tuple2$.class);
   }

   public Tuple2 apply$mZZc$sp(final boolean _1, final boolean _2) {
      return new Tuple2$mcZZ$sp(_1, _2);
   }

   public Tuple2 apply$mZCc$sp(final boolean _1, final char _2) {
      return new Tuple2$mcZC$sp(_1, _2);
   }

   public Tuple2 apply$mZDc$sp(final boolean _1, final double _2) {
      return new Tuple2$mcZD$sp(_1, _2);
   }

   public Tuple2 apply$mZIc$sp(final boolean _1, final int _2) {
      return new Tuple2$mcZI$sp(_1, _2);
   }

   public Tuple2 apply$mZJc$sp(final boolean _1, final long _2) {
      return new Tuple2$mcZJ$sp(_1, _2);
   }

   public Tuple2 apply$mCZc$sp(final char _1, final boolean _2) {
      return new Tuple2$mcCZ$sp(_1, _2);
   }

   public Tuple2 apply$mCCc$sp(final char _1, final char _2) {
      return new Tuple2$mcCC$sp(_1, _2);
   }

   public Tuple2 apply$mCDc$sp(final char _1, final double _2) {
      return new Tuple2$mcCD$sp(_1, _2);
   }

   public Tuple2 apply$mCIc$sp(final char _1, final int _2) {
      return new Tuple2$mcCI$sp(_1, _2);
   }

   public Tuple2 apply$mCJc$sp(final char _1, final long _2) {
      return new Tuple2$mcCJ$sp(_1, _2);
   }

   public Tuple2 apply$mDZc$sp(final double _1, final boolean _2) {
      return new Tuple2$mcDZ$sp(_1, _2);
   }

   public Tuple2 apply$mDCc$sp(final double _1, final char _2) {
      return new Tuple2$mcDC$sp(_1, _2);
   }

   public Tuple2 apply$mDDc$sp(final double _1, final double _2) {
      return new Tuple2$mcDD$sp(_1, _2);
   }

   public Tuple2 apply$mDIc$sp(final double _1, final int _2) {
      return new Tuple2$mcDI$sp(_1, _2);
   }

   public Tuple2 apply$mDJc$sp(final double _1, final long _2) {
      return new Tuple2$mcDJ$sp(_1, _2);
   }

   public Tuple2 apply$mIZc$sp(final int _1, final boolean _2) {
      return new Tuple2$mcIZ$sp(_1, _2);
   }

   public Tuple2 apply$mICc$sp(final int _1, final char _2) {
      return new Tuple2$mcIC$sp(_1, _2);
   }

   public Tuple2 apply$mIDc$sp(final int _1, final double _2) {
      return new Tuple2$mcID$sp(_1, _2);
   }

   public Tuple2 apply$mIIc$sp(final int _1, final int _2) {
      return new Tuple2$mcII$sp(_1, _2);
   }

   public Tuple2 apply$mIJc$sp(final int _1, final long _2) {
      return new Tuple2$mcIJ$sp(_1, _2);
   }

   public Tuple2 apply$mJZc$sp(final long _1, final boolean _2) {
      return new Tuple2$mcJZ$sp(_1, _2);
   }

   public Tuple2 apply$mJCc$sp(final long _1, final char _2) {
      return new Tuple2$mcJC$sp(_1, _2);
   }

   public Tuple2 apply$mJDc$sp(final long _1, final double _2) {
      return new Tuple2$mcJD$sp(_1, _2);
   }

   public Tuple2 apply$mJIc$sp(final long _1, final int _2) {
      return new Tuple2$mcJI$sp(_1, _2);
   }

   public Tuple2 apply$mJJc$sp(final long _1, final long _2) {
      return new Tuple2$mcJJ$sp(_1, _2);
   }

   public Option unapply$mZZc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcZZ$sp(x$0._1$mcZ$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mZCc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcZC$sp(x$0._1$mcZ$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mZDc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcZD$sp(x$0._1$mcZ$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mZIc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcZI$sp(x$0._1$mcZ$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mZJc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcZJ$sp(x$0._1$mcZ$sp(), x$0._2$mcJ$sp())));
   }

   public Option unapply$mCZc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcCZ$sp(x$0._1$mcC$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mCCc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcCC$sp(x$0._1$mcC$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mCDc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcCD$sp(x$0._1$mcC$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mCIc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcCI$sp(x$0._1$mcC$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mCJc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcCJ$sp(x$0._1$mcC$sp(), x$0._2$mcJ$sp())));
   }

   public Option unapply$mDZc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcDZ$sp(x$0._1$mcD$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mDCc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcDC$sp(x$0._1$mcD$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mDDc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcDD$sp(x$0._1$mcD$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mDIc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcDI$sp(x$0._1$mcD$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mDJc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcDJ$sp(x$0._1$mcD$sp(), x$0._2$mcJ$sp())));
   }

   public Option unapply$mIZc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcIZ$sp(x$0._1$mcI$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mICc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcIC$sp(x$0._1$mcI$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mIDc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcID$sp(x$0._1$mcI$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mIIc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcII$sp(x$0._1$mcI$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mIJc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcIJ$sp(x$0._1$mcI$sp(), x$0._2$mcJ$sp())));
   }

   public Option unapply$mJZc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcJZ$sp(x$0._1$mcJ$sp(), x$0._2$mcZ$sp())));
   }

   public Option unapply$mJCc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcJC$sp(x$0._1$mcJ$sp(), x$0._2$mcC$sp())));
   }

   public Option unapply$mJDc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcJD$sp(x$0._1$mcJ$sp(), x$0._2$mcD$sp())));
   }

   public Option unapply$mJIc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcJI$sp(x$0._1$mcJ$sp(), x$0._2$mcI$sp())));
   }

   public Option unapply$mJJc$sp(final Tuple2 x$0) {
      return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2$mcJJ$sp(x$0._1$mcJ$sp(), x$0._2$mcJ$sp())));
   }

   private Tuple2$() {
   }
}
