package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.View;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3qa\u0002\u0005\u0011\u0002\u0007\u0005q\u0002C\u00030\u0001\u0011\u0005\u0001\u0007C\u00035\u0001\u0011\u0005SgB\u0003:\u0011!\u0005!HB\u0003\b\u0011!\u00051\bC\u0003D\t\u0011\u0005A\tC\u0004F\t\u0005\u0005I\u0011\u0002$\u0003\u0015%sG-\u001a=fIN+\u0017O\u0003\u0002\n\u0015\u00059Q.\u001e;bE2,'BA\u0006\r\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u001b\u0005)1oY1mC\u000e\u0001QC\u0001\t\u001c'\u0019\u0001\u0011#\u0006\u0013(YA\u0011!cE\u0007\u0002\u0019%\u0011A\u0003\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Y9\u0012$D\u0001\t\u0013\tA\u0002BA\u0002TKF\u0004\"AG\u000e\r\u0001\u0011)A\u0004\u0001b\u0001;\t\tA+\u0005\u0002\u001fCA\u0011!cH\u0005\u0003A1\u0011qAT8uQ&tw\r\u0005\u0002\u0013E%\u00111\u0005\u0004\u0002\u0004\u0003:L\bcA\u0013'35\t!\"\u0003\u0002\b\u0015A)a\u0003K\r+W%\u0011\u0011\u0006\u0003\u0002\u000e\u0013:$W\r_3e'\u0016\fx\n]:\u0011\u0005Y\u0001\u0001c\u0001\f\u00013A!Q%L\r+\u0013\tq#BA\fJi\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z\t\u00164\u0017-\u001e7ug\u00061A%\u001b8ji\u0012\"\u0012!\r\t\u0003%IJ!a\r\u0007\u0003\tUs\u0017\u000e^\u0001\u0010SR,'/\u00192mK\u001a\u000b7\r^8ssV\ta\u0007E\u0002&o)J!\u0001\u000f\u0006\u0003\u0015M+\u0017OR1di>\u0014\u00180\u0001\u0006J]\u0012,\u00070\u001a3TKF\u0004\"A\u0006\u0003\u0014\u0005\u0011a\u0004cA\u001fAU9\u0011QEP\u0005\u0003\u007f)\t!bU3r\r\u0006\u001cGo\u001c:z\u0013\t\t%I\u0001\u0005EK2,w-\u0019;f\u0015\ty$\"\u0001\u0004=S:LGO\u0010\u000b\u0002u\u0005aqO]5uKJ+\u0007\u000f\\1dKR\tq\t\u0005\u0002I\u001b6\t\u0011J\u0003\u0002K\u0017\u0006!A.\u00198h\u0015\u0005a\u0015\u0001\u00026bm\u0006L!AT%\u0003\r=\u0013'.Z2uQ\u0011!\u0001k\u0015+\u0011\u0005I\t\u0016B\u0001*\r\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0004Q\u0011\u0019\u0001k\u0015+"
)
public interface IndexedSeq extends Seq, scala.collection.IndexedSeq, IndexedSeqOps {
   static Builder newBuilder() {
      return IndexedSeq$.MODULE$.newBuilder();
   }

   static scala.collection.SeqOps from(final IterableOnce it) {
      return IndexedSeq$.MODULE$.from(it);
   }

   static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      IndexedSeq$ var10000 = IndexedSeq$.MODULE$;
      return x;
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$7$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$5$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$3$adapted);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$1$adapted);
   }

   static Object tabulate(final int n, final Function1 f) {
      return IndexedSeq$.MODULE$.from(new View.Tabulate(n, f));
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$4);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$3);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$2);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$1);
   }

   static Object fill(final int n, final Function0 elem) {
      return IndexedSeq$.MODULE$.from(new View.Fill(n, elem));
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(IndexedSeq$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(IndexedSeq$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      return IndexedSeq$.MODULE$.from(new View.Unfold(init, f));
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      return IndexedSeq$.MODULE$.from(new View.Iterate(start, len, f));
   }

   // $FF: synthetic method
   static SeqFactory iterableFactory$(final IndexedSeq $this) {
      return $this.iterableFactory();
   }

   default SeqFactory iterableFactory() {
      return IndexedSeq$.MODULE$;
   }

   static void $init$(final IndexedSeq $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
