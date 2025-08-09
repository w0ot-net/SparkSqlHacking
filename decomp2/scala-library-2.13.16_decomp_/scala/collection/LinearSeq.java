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
   bytes = "\u0006\u0005}3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003,\u0001\u0011\u0005A\u0006\u0003\u00041\u0001\u0001&\t&\r\u0005\u0006{\u0001!\tEP\u0004\u0006\u0005&A\ta\u0011\u0004\u0006\u0011%A\t\u0001\u0012\u0005\u0006\u0019\u0016!\t!\u0014\u0005\b\u001d\u0016\t\t\u0011\"\u0003P\u0005%a\u0015N\\3beN+\u0017O\u0003\u0002\u000b\u0017\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u00031\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u00105M)\u0001\u0001\u0005\u000b$QA\u0011\u0011CE\u0007\u0002\u0017%\u00111c\u0003\u0002\u0007\u0003:L(+\u001a4\u0011\u0007U1\u0002$D\u0001\n\u0013\t9\u0012BA\u0002TKF\u0004\"!\u0007\u000e\r\u0001\u001111\u0004\u0001CC\u0002q\u0011\u0011!Q\t\u0003;\u0001\u0002\"!\u0005\u0010\n\u0005}Y!a\u0002(pi\"Lgn\u001a\t\u0003#\u0005J!AI\u0006\u0003\u0007\u0005s\u0017\u0010E\u0003\u0016Ia1s%\u0003\u0002&\u0013\taA*\u001b8fCJ\u001cV-](qgB\u0011Q\u0003\u0001\t\u0004+\u0001A\u0002\u0003B\u000b*1\u0019J!AK\u0005\u0003/%#XM]1cY\u00164\u0015m\u0019;pef$UMZ1vYR\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001.!\t\tb&\u0003\u00020\u0017\t!QK\\5u\u00031\u0019HO]5oOB\u0013XMZ5y+\u0005\u0011\u0004CA\u001a;\u001d\t!\u0004\b\u0005\u00026\u00175\taG\u0003\u00028\u001b\u00051AH]8pizJ!!O\u0006\u0002\rA\u0013X\rZ3g\u0013\tYDH\u0001\u0004TiJLgn\u001a\u0006\u0003s-\tq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0002\u007fA\u0019Q\u0003\u0011\u0014\n\u0005\u0005K!AC*fc\u001a\u000b7\r^8ss\u0006IA*\u001b8fCJ\u001cV-\u001d\t\u0003+\u0015\u0019\"!B#\u0011\u0007\u0019KeE\u0004\u0002\u0016\u000f&\u0011\u0001*C\u0001\u000b'\u0016\fh)Y2u_JL\u0018B\u0001&L\u0005!!U\r\\3hCR,'B\u0001%\n\u0003\u0019a\u0014N\\5u}Q\t1)\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001Q!\t\tf+D\u0001S\u0015\t\u0019F+\u0001\u0003mC:<'\"A+\u0002\t)\fg/Y\u0005\u0003/J\u0013aa\u00142kK\u000e$\b\u0006B\u0003Z9v\u0003\"!\u0005.\n\u0005m[!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0001\u0006\u0002\u0003Z9v\u0003"
)
public interface LinearSeq extends Seq, LinearSeqOps {
   static Builder newBuilder() {
      return LinearSeq$.MODULE$.newBuilder();
   }

   static SeqOps from(final IterableOnce it) {
      return LinearSeq$.MODULE$.from(it);
   }

   static SeqOps unapplySeq(final SeqOps x) {
      LinearSeq$ var10000 = LinearSeq$.MODULE$;
      return x;
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      SeqFactory.Delegate tabulate_this = LinearSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$7$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      SeqFactory.Delegate tabulate_this = LinearSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$5$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      SeqFactory.Delegate tabulate_this = LinearSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$3$adapted);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      SeqFactory.Delegate tabulate_this = LinearSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$1$adapted);
   }

   static Object tabulate(final int n, final Function1 f) {
      return LinearSeq$.MODULE$.from(new View.Tabulate(n, f));
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      SeqFactory.Delegate fill_this = LinearSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$4);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      SeqFactory.Delegate fill_this = LinearSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$3);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      SeqFactory.Delegate fill_this = LinearSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$2);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      SeqFactory.Delegate fill_this = LinearSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$1);
   }

   static Object fill(final int n, final Function0 elem) {
      return LinearSeq$.MODULE$.from(new View.Fill(n, elem));
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(LinearSeq$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(LinearSeq$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      return LinearSeq$.MODULE$.from(new View.Unfold(init, f));
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      return LinearSeq$.MODULE$.from(new View.Iterate(start, len, f));
   }

   // $FF: synthetic method
   static String stringPrefix$(final LinearSeq $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "LinearSeq";
   }

   // $FF: synthetic method
   static SeqFactory iterableFactory$(final LinearSeq $this) {
      return $this.iterableFactory();
   }

   default SeqFactory iterableFactory() {
      return LinearSeq$.MODULE$;
   }

   static void $init$(final LinearSeq $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
