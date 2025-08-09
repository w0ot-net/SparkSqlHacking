package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003,\u0001\u0011\u0005A\u0006\u0003\u00041\u0001\u0001&\t&\r\u0005\u0006{\u0001!\tEP\u0004\u0006\u0005&A\ta\u0011\u0004\u0006\u0011%A\t\u0001\u0012\u0005\u0006\u0019\u0016!\t!\u0014\u0005\b\u001d\u0016\t\t\u0011\"\u0003P\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0006\u0003\u0015-\t!bY8mY\u0016\u001cG/[8o\u0015\u0005a\u0011!B:dC2\f7\u0001A\u000b\u0003\u001fi\u0019R\u0001\u0001\t\u0015G!\u0002\"!\u0005\n\u000e\u0003-I!aE\u0006\u0003\r\u0005s\u0017PU3g!\r)b\u0003G\u0007\u0002\u0013%\u0011q#\u0003\u0002\u0004'\u0016\f\bCA\r\u001b\u0019\u0001!aa\u0007\u0001\u0005\u0006\u0004a\"!A!\u0012\u0005u\u0001\u0003CA\t\u001f\u0013\ty2BA\u0004O_RD\u0017N\\4\u0011\u0005E\t\u0013B\u0001\u0012\f\u0005\r\te.\u001f\t\u0006+\u0011BbeJ\u0005\u0003K%\u0011Q\"\u00138eKb,GmU3r\u001fB\u001c\bCA\u000b\u0001!\r)\u0002\u0001\u0007\t\u0005+%Bb%\u0003\u0002+\u0013\t9\u0012\n^3sC\ndWMR1di>\u0014\u0018\u0010R3gCVdGo]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00035\u0002\"!\u0005\u0018\n\u0005=Z!\u0001B+oSR\fAb\u001d;sS:<\u0007K]3gSb,\u0012A\r\t\u0003gir!\u0001\u000e\u001d\u0011\u0005UZQ\"\u0001\u001c\u000b\u0005]j\u0011A\u0002\u001fs_>$h(\u0003\u0002:\u0017\u00051\u0001K]3eK\u001aL!a\u000f\u001f\u0003\rM#(/\u001b8h\u0015\tI4\"A\bji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z+\u0005y\u0004cA\u000bAM%\u0011\u0011)\u0003\u0002\u000b'\u0016\fh)Y2u_JL\u0018AC%oI\u0016DX\rZ*fcB\u0011Q#B\n\u0003\u000b\u0015\u00032AR%'\u001d\t)r)\u0003\u0002I\u0013\u0005Q1+Z9GC\u000e$xN]=\n\u0005)[%\u0001\u0003#fY\u0016<\u0017\r^3\u000b\u0005!K\u0011A\u0002\u001fj]&$h\bF\u0001D\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005\u0001\u0006CA)W\u001b\u0005\u0011&BA*U\u0003\u0011a\u0017M\\4\u000b\u0003U\u000bAA[1wC&\u0011qK\u0015\u0002\u0007\u001f\nTWm\u0019;)\t\u0015IF,\u0018\t\u0003#iK!aW\u0006\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0002)\t\u0011IF,\u0018"
)
public interface IndexedSeq extends Seq, IndexedSeqOps {
   static Builder newBuilder() {
      return IndexedSeq$.MODULE$.newBuilder();
   }

   static SeqOps from(final IterableOnce it) {
      return IndexedSeq$.MODULE$.from(it);
   }

   static SeqOps unapplySeq(final SeqOps x) {
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
   static String stringPrefix$(final IndexedSeq $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "IndexedSeq";
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
