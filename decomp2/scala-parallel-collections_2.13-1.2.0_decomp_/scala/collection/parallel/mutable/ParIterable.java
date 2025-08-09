package scala.collection.parallel.mutable;

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
import scala.collection.mutable.Iterable;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4q\u0001D\u0007\u0011\u0002\u0007\u0005a\u0003C\u0003;\u0001\u0011\u00051\bC\u0003@\u0001\u0011\u0005\u0003\tC\u0003E\u0001\u0011\u0005S\tC\u0003G\u0001\u0011\u0005s\tC\u0003L\u0001\u0019\u0005AjB\u0003R\u001b!\u0005!KB\u0003\r\u001b!\u00051\u000bC\u0003X\u000f\u0011\u0005\u0001\fC\u0003Z\u000f\u0011\r!\fC\u0003g\u000f\u0011\u0005q\rC\u0003p\u000f\u0011\u0005\u0001OA\u0006QCJLE/\u001a:bE2,'B\u0001\b\u0010\u0003\u001diW\u000f^1cY\u0016T!\u0001E\t\u0002\u0011A\f'/\u00197mK2T!AE\n\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0007\u0001)\"aF\u0011\u0014\u000b\u0001ABD\u000b\u001a\u0011\u0005eQR\"A\n\n\u0005m\u0019\"AB!osJ+g\rE\u0002\u001e=}i\u0011aD\u0005\u0003\u0019=\u0001\"\u0001I\u0011\r\u0001\u0011)!\u0005\u0001b\u0001G\t\tA+\u0005\u0002%OA\u0011\u0011$J\u0005\u0003MM\u0011qAT8uQ&tw\r\u0005\u0002\u001aQ%\u0011\u0011f\u0005\u0002\u0004\u0003:L\b\u0003B\u0016/?Aj\u0011\u0001\f\u0006\u0003[E\tqaZ3oKJL7-\u0003\u00020Y\t\u0011r)\u001a8fe&\u001c\u0007+\u0019:UK6\u0004H.\u0019;f!\t\t\u0004!D\u0001\u000e!\u0019i2g\b\u00196m%\u0011Ag\u0004\u0002\u0010!\u0006\u0014\u0018\n^3sC\ndW\rT5lKB\u0019\u0011\u0007A\u0010\u0011\u0007]Bt$D\u0001\u0012\u0013\tI\u0014C\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0003\u0019!\u0013N\\5uIQ\tA\b\u0005\u0002\u001a{%\u0011ah\u0005\u0002\u0005+:LG/A\u0005d_6\u0004\u0018M\\5p]V\t\u0011\tE\u0002,\u0005BJ!a\u0011\u0017\u0003'\u001d+g.\u001a:jGB\u000b'oQ8na\u0006t\u0017n\u001c8\u0002\u0015Q|\u0017\n^3sC\ndW-F\u00016\u0003\u0015!xnU3r+\u0005A\u0005cA\u0019J?%\u0011!*\u0004\u0002\u0007!\u0006\u00148+Z9\u0002\u0007M,\u0017/F\u0001N!\rq\u0005kH\u0007\u0002\u001f*\u0011a\"E\u0005\u0003s=\u000b1\u0002U1s\u0013R,'/\u00192mKB\u0011\u0011gB\n\u0003\u000fQ\u00032aK+1\u0013\t1FF\u0001\u0006QCJ4\u0015m\u0019;pef\fa\u0001P5oSRtD#\u0001*\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\u0007m\u000bG-F\u0001]!\u0015YSlX2f\u0013\tqFF\u0001\bDC:\u001cu.\u001c2j]\u00164%o\\7\u0011\u0007E\u0002\u0001\r\u0005\u0002!C\u0012)!-\u0003b\u0001G\t\t1\u000b\u0005\u0002!I\u0012)!%\u0003b\u0001GA\u0019\u0011\u0007A2\u0002\u00159,wOQ;jY\u0012,'/\u0006\u0002i[V\t\u0011\u000e\u0005\u0003\u001eU2t\u0017BA6\u0010\u0005!\u0019u.\u001c2j]\u0016\u0014\bC\u0001\u0011n\t\u0015\u0011#B1\u0001$!\r\t\u0004\u0001\\\u0001\f]\u0016<8i\\7cS:,'/\u0006\u0002riV\t!\u000f\u0005\u0003\u001eUN,\bC\u0001\u0011u\t\u0015\u00113B1\u0001$!\r\t\u0004a\u001d"
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

   Iterable seq();

   static void $init$(final ParIterable $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
