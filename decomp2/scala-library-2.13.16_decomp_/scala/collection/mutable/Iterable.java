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
import scala.collection.View;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3qa\u0002\u0005\u0011\u0002\u0007\u0005q\u0002C\u0003-\u0001\u0011\u0005Q\u0006C\u00032\u0001\u0011\u0005#gB\u00037\u0011!\u0005qGB\u0003\b\u0011!\u0005\u0001\bC\u0003A\t\u0011\u0005\u0011\tC\u0004C\t\u0005\u0005I\u0011B\"\u0003\u0011%#XM]1cY\u0016T!!\u0003\u0006\u0002\u000f5,H/\u00192mK*\u00111\u0002D\u0001\u000bG>dG.Z2uS>t'\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0001CG\n\u0006\u0001E)2%\u000b\t\u0003%Mi\u0011\u0001D\u0005\u0003)1\u0011a!\u00118z%\u00164\u0007c\u0001\f\u001815\t!\"\u0003\u0002\b\u0015A\u0011\u0011D\u0007\u0007\u0001\t\u0015Y\u0002A1\u0001\u001d\u0005\u0005\t\u0015CA\u000f!!\t\u0011b$\u0003\u0002 \u0019\t9aj\u001c;iS:<\u0007C\u0001\n\"\u0013\t\u0011CBA\u0002B]f\u0004RA\u0006\u0013\u0019M!J!!\n\u0006\u0003\u0017%#XM]1cY\u0016|\u0005o\u001d\t\u0003O\u0001i\u0011\u0001\u0003\t\u0004O\u0001A\u0002\u0003\u0002\f+1\u0019J!a\u000b\u0006\u0003/%#XM]1cY\u00164\u0015m\u0019;pef$UMZ1vYR\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001/!\t\u0011r&\u0003\u00021\u0019\t!QK\\5u\u0003=IG/\u001a:bE2,g)Y2u_JLX#A\u001a\u0011\u0007Y!d%\u0003\u00026\u0015\ty\u0011\n^3sC\ndWMR1di>\u0014\u00180\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\t9Ca\u0005\u0002\u0005sA\u0019!(\u0010\u0014\u000f\u0005YY\u0014B\u0001\u001f\u000b\u0003=IE/\u001a:bE2,g)Y2u_JL\u0018B\u0001 @\u0005!!U\r\\3hCR,'B\u0001\u001f\u000b\u0003\u0019a\u0014N\\5u}Q\tq'\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001E!\t)%*D\u0001G\u0015\t9\u0005*\u0001\u0003mC:<'\"A%\u0002\t)\fg/Y\u0005\u0003\u0017\u001a\u0013aa\u00142kK\u000e$\b\u0006\u0002\u0003N!F\u0003\"A\u0005(\n\u0005=c!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0001\u0006B\u0002N!F\u0003"
)
public interface Iterable extends scala.collection.Iterable {
   static Builder newBuilder() {
      return Iterable$.MODULE$.newBuilder();
   }

   static Object from(final IterableOnce it) {
      return Iterable$.MODULE$.from(it);
   }

   static Object apply(final scala.collection.immutable.Seq elems) {
      return Iterable$.MODULE$.apply(elems);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      IterableFactory.Delegate tabulate_this = Iterable$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$7$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      IterableFactory.Delegate tabulate_this = Iterable$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$5$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      IterableFactory.Delegate tabulate_this = Iterable$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$3$adapted);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      IterableFactory.Delegate tabulate_this = Iterable$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$1$adapted);
   }

   static Object tabulate(final int n, final Function1 f) {
      return Iterable$.MODULE$.from(new View.Tabulate(n, f));
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      IterableFactory.Delegate fill_this = Iterable$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$4);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      IterableFactory.Delegate fill_this = Iterable$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$3);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      IterableFactory.Delegate fill_this = Iterable$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$2);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      IterableFactory.Delegate fill_this = Iterable$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$1);
   }

   static Object fill(final int n, final Function0 elem) {
      return Iterable$.MODULE$.from(new View.Fill(n, elem));
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Iterable$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Iterable$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      return Iterable$.MODULE$.from(new View.Unfold(init, f));
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      return Iterable$.MODULE$.from(new View.Iterate(start, len, f));
   }

   // $FF: synthetic method
   static IterableFactory iterableFactory$(final Iterable $this) {
      return $this.iterableFactory();
   }

   default IterableFactory iterableFactory() {
      return Iterable$.MODULE$;
   }

   static void $init$(final Iterable $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
