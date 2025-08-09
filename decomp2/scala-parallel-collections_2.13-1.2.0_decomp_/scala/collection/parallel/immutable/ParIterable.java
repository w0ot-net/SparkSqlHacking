package scala.collection.parallel.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.GenericParCompanion;
import scala.collection.immutable.Seq;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4qa\u0003\u0007\u0011\u0002\u0007\u0005Q\u0003C\u0003;\u0001\u0011\u00051\bC\u0003@\u0001\u0011\u0005\u0003\tC\u0003E\u0001\u0011\u0005S\tC\u0003G\u0001\u0011\u0005siB\u0003L\u0019!\u0005AJB\u0003\f\u0019!\u0005Q\nC\u0003R\r\u0011\u0005!\u000bC\u0003T\r\u0011\rA\u000bC\u0003a\r\u0011\u0005\u0011\rC\u0003j\r\u0011\u0005!NA\u0006QCJLE/\u001a:bE2,'BA\u0007\u000f\u0003%IW.\\;uC\ndWM\u0003\u0002\u0010!\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u0012%\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003M\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u0017AM)\u0001aF\u000e*cA\u0011\u0001$G\u0007\u0002%%\u0011!D\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007qib$D\u0001\u000f\u0013\tYa\u0002\u0005\u0002 A1\u0001AAB\u0011\u0001\t\u000b\u0007!EA\u0001U#\t\u0019c\u0005\u0005\u0002\u0019I%\u0011QE\u0005\u0002\b\u001d>$\b.\u001b8h!\tAr%\u0003\u0002)%\t\u0019\u0011I\\=\u0011\t)jcdL\u0007\u0002W)\u0011A\u0006E\u0001\bO\u0016tWM]5d\u0013\tq3F\u0001\nHK:,'/[2QCJ$V-\u001c9mCR,\u0007C\u0001\u0019\u0001\u001b\u0005a\u0001C\u0002\u000f3==\"T'\u0003\u00024\u001d\ty\u0001+\u0019:Ji\u0016\u0014\u0018M\u00197f\u0019&\\W\rE\u00021\u0001y\u00012A\u000e\u001d\u001f\u001b\u00059$BA\u0007\u0011\u0013\tItG\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0003\u0019!\u0013N\\5uIQ\tA\b\u0005\u0002\u0019{%\u0011aH\u0005\u0002\u0005+:LG/A\u0005d_6\u0004\u0018M\\5p]V\t\u0011\tE\u0002+\u0005>J!aQ\u0016\u0003'\u001d+g.\u001a:jGB\u000b'oQ8na\u0006t\u0017n\u001c8\u0002\u0015Q|\u0017\n^3sC\ndW-F\u00015\u0003\u0015!xnU3r+\u0005A\u0005c\u0001\u0019J=%\u0011!\n\u0004\u0002\u0007!\u0006\u00148+Z9\u0002\u0017A\u000b'/\u0013;fe\u0006\u0014G.\u001a\t\u0003a\u0019\u0019\"A\u0002(\u0011\u0007)zu&\u0003\u0002QW\tQ\u0001+\u0019:GC\u000e$xN]=\u0002\rqJg.\u001b;?)\u0005a\u0015\u0001D2b]\n+\u0018\u000e\u001c3Ge>lWcA+\\=V\ta\u000bE\u0003+/fkv,\u0003\u0002YW\tq1)\u00198D_6\u0014\u0017N\\3Ge>l\u0007c\u0001\u0019\u00015B\u0011qd\u0017\u0003\u00069\"\u0011\rA\t\u0002\u0002'B\u0011qD\u0018\u0003\u0006C!\u0011\rA\t\t\u0004a\u0001i\u0016A\u00038fo\n+\u0018\u000e\u001c3feV\u0011!mZ\u000b\u0002GB!A\u0004\u001a4i\u0013\t)gB\u0001\u0005D_6\u0014\u0017N\\3s!\tyr\rB\u0003\"\u0013\t\u0007!\u0005E\u00021\u0001\u0019\f1B\\3x\u0007>l'-\u001b8feV\u00111N\\\u000b\u0002YB!A\u0004Z7p!\tyb\u000eB\u0003\"\u0015\t\u0007!\u0005E\u00021\u00015\u0004"
)
public interface ParIterable extends scala.collection.parallel.ParIterable {
   static CanCombineFrom canBuildFrom() {
      return ParIterable$.MODULE$.canBuildFrom();
   }

   static scala.collection.parallel.ParIterable iterate(final Object start, final int len, final Function1 f) {
      return ParIterable$.MODULE$.iterate(start, len, f);
   }

   static scala.collection.parallel.ParIterable range(final Object start, final Object end, final Object step, final Integral evidence$2) {
      return ParIterable$.MODULE$.range(start, end, step, evidence$2);
   }

   static scala.collection.parallel.ParIterable range(final Object start, final Object end, final Integral evidence$1) {
      return ParIterable$.MODULE$.range(start, end, evidence$1);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return ParIterable$.MODULE$.tabulate(n1, n2, n3, n4, n5, f);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return ParIterable$.MODULE$.tabulate(n1, n2, n3, n4, f);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return ParIterable$.MODULE$.tabulate(n1, n2, n3, f);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final Function2 f) {
      return ParIterable$.MODULE$.tabulate(n1, n2, f);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n, final Function1 f) {
      return ParIterable$.MODULE$.tabulate(n, f);
   }

   static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n1, n2, n3, n4, n5, elem);
   }

   static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n1, n2, n3, n4, elem);
   }

   static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n1, n2, n3, elem);
   }

   static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n1, n2, elem);
   }

   static scala.collection.parallel.ParIterable fill(final int n, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n, elem);
   }

   static scala.collection.parallel.ParIterable concat(final Seq xss) {
      return ParIterable$.MODULE$.concat(xss);
   }

   static Factory toFactory() {
      return ParIterable$.MODULE$.toFactory();
   }

   static scala.collection.parallel.ParIterable apply(final Seq elems) {
      return ParIterable$.MODULE$.apply(elems);
   }

   static scala.collection.parallel.ParIterable empty() {
      return ParIterable$.MODULE$.empty();
   }

   // $FF: synthetic method
   static GenericParCompanion companion$(final ParIterable $this) {
      return $this.companion();
   }

   default GenericParCompanion companion() {
      return ParIterable$.MODULE$;
   }

   // $FF: synthetic method
   static ParIterable toIterable$(final ParIterable $this) {
      return $this.toIterable();
   }

   default ParIterable toIterable() {
      return this;
   }

   // $FF: synthetic method
   static ParSeq toSeq$(final ParIterable $this) {
      return $this.toSeq();
   }

   default ParSeq toSeq() {
      return (ParSeq)this.toParCollection(() -> ParSeq$.MODULE$.newCombiner());
   }

   static void $init$(final ParIterable $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
