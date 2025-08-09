package scala.collection.immutable;

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
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4q!\u0003\u0006\u0011\u0002\u0007\u0005\u0011\u0003C\u00032\u0001\u0011\u0005!\u0007C\u00037\u0001\u0011\u0015s\u0007C\u0003:\u0001\u0011\u0005#hB\u0003?\u0015!\u0005qHB\u0003\n\u0015!\u0005\u0001\tC\u0003I\u000b\u0011\u0005\u0011\nC\u0003K\u000b\u0011\u00053\nC\u0004W\u000b\u0005\u0005I\u0011B,\u0003\u0007M+\u0017O\u0003\u0002\f\u0019\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\u001b9\t!bY8mY\u0016\u001cG/[8o\u0015\u0005y\u0011!B:dC2\f7\u0001A\u000b\u0003%u\u0019b\u0001A\n\u0018M%r\u0003C\u0001\u000b\u0016\u001b\u0005q\u0011B\u0001\f\u000f\u0005\u0019\te.\u001f*fMB\u0019\u0001$G\u000e\u000e\u0003)I!A\u0007\u0006\u0003\u0011%#XM]1cY\u0016\u0004\"\u0001H\u000f\r\u0001\u00111a\u0004\u0001CC\u0002}\u0011\u0011!Q\t\u0003A\r\u0002\"\u0001F\u0011\n\u0005\tr!a\u0002(pi\"Lgn\u001a\t\u0003)\u0011J!!\n\b\u0003\u0007\u0005s\u0017\u0010E\u0002(Qmi\u0011\u0001D\u0005\u0003\u00131\u0001R\u0001\u0007\u0016\u001cY5J!a\u000b\u0006\u0003\rM+\u0017o\u00149t!\tA\u0002\u0001E\u0002\u0019\u0001m\u0001BaJ\u0018\u001cY%\u0011\u0001\u0007\u0004\u0002\u0018\u0013R,'/\u00192mK\u001a\u000b7\r^8ss\u0012+g-Y;miN\fa\u0001J5oSR$C#A\u001a\u0011\u0005Q!\u0014BA\u001b\u000f\u0005\u0011)f.\u001b;\u0002\u000bQ|7+Z9\u0016\u0003aj\u0011\u0001A\u0001\u0010SR,'/\u00192mK\u001a\u000b7\r^8ssV\t1\bE\u0002(y1J!!\u0010\u0007\u0003\u0015M+\u0017OR1di>\u0014\u00180A\u0002TKF\u0004\"\u0001G\u0003\u0014\u0005\u0015\t\u0005c\u0001\"FY9\u0011qeQ\u0005\u0003\t2\t!bU3r\r\u0006\u001cGo\u001c:z\u0013\t1uI\u0001\u0005EK2,w-\u0019;f\u0015\t!E\"\u0001\u0004=S:LGO\u0010\u000b\u0002\u007f\u0005!aM]8n+\tau\n\u0006\u0002N#B\u0019\u0001\u0004\u0001(\u0011\u0005qyE!\u0002)\b\u0005\u0004y\"!A#\t\u000bI;\u0001\u0019A*\u0002\u0005%$\bcA\u0014U\u001d&\u0011Q\u000b\u0004\u0002\r\u0013R,'/\u00192mK>s7-Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u00021B\u0011\u0011LX\u0007\u00025*\u00111\fX\u0001\u0005Y\u0006twMC\u0001^\u0003\u0011Q\u0017M^1\n\u0005}S&AB(cU\u0016\u001cG\u000f\u000b\u0003\u0006C\u0012,\u0007C\u0001\u000bc\u0013\t\u0019gB\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t1\u0001\u000b\u0003\u0005C\u0012,\u0007"
)
public interface Seq extends Iterable, scala.collection.Seq, SeqOps {
   static Seq from(final IterableOnce it) {
      return Seq$.MODULE$.from(it);
   }

   static Builder newBuilder() {
      return Seq$.MODULE$.newBuilder();
   }

   static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      Seq$ var10000 = Seq$.MODULE$;
      return x;
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      SeqFactory.Delegate tabulate_this = Seq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$7$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      SeqFactory.Delegate tabulate_this = Seq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$5$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      SeqFactory.Delegate tabulate_this = Seq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$3$adapted);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      SeqFactory.Delegate tabulate_this = Seq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$1$adapted);
   }

   static Object tabulate(final int n, final Function1 f) {
      SeqFactory.Delegate tabulate_this = Seq$.MODULE$;
      IterableOnce from_source = new View.Tabulate(n, f);
      return ((Seq$)tabulate_this).from(from_source);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      SeqFactory.Delegate fill_this = Seq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$4);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      SeqFactory.Delegate fill_this = Seq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$3);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      SeqFactory.Delegate fill_this = Seq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$2);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      SeqFactory.Delegate fill_this = Seq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$1);
   }

   static Object fill(final int n, final Function0 elem) {
      SeqFactory.Delegate fill_this = Seq$.MODULE$;
      IterableOnce from_source = new View.Fill(n, elem);
      return ((Seq$)fill_this).from(from_source);
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Seq$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Seq$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      SeqFactory.Delegate unfold_this = Seq$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return ((Seq$)unfold_this).from(from_source);
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      SeqFactory.Delegate iterate_this = Seq$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return ((Seq$)iterate_this).from(from_source);
   }

   // $FF: synthetic method
   static Seq toSeq$(final Seq $this) {
      return $this.toSeq();
   }

   default Seq toSeq() {
      return this;
   }

   // $FF: synthetic method
   static SeqFactory iterableFactory$(final Seq $this) {
      return $this.iterableFactory();
   }

   default SeqFactory iterableFactory() {
      return Seq$.MODULE$;
   }

   static void $init$(final Seq $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
