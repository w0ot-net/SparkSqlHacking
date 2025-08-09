package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Equals;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.PartialFunction;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005m4q\u0001D\u0007\u0011\u0002\u0007\u0005!\u0003C\u00039\u0001\u0011\u0005\u0011\bC\u0003>\u0001\u0011\u0005c\bC\u0003C\u0001\u0011\u00051\tC\u0003J\u0001\u0011\u0005#\nC\u0003N\u0001\u0011\u0005c\nC\u0003P\u0001\u0011\u0005\u0003\u000b\u0003\u0004]\u0001\u0001&\t&X\u0004\u0006=6A\ta\u0018\u0004\u0006\u00195A\t\u0001\u0019\u0005\u0006Q&!\t!\u001b\u0005\bU&\t\t\u0011\"\u0003l\u0005\r\u0019V-\u001d\u0006\u0003\u001d=\t!bY8mY\u0016\u001cG/[8o\u0015\u0005\u0001\u0012!B:dC2\f7\u0001A\u000b\u0003'y\u0019r\u0001\u0001\u000b\u0019O5\u0012T\u0007\u0005\u0002\u0016-5\tq\"\u0003\u0002\u0018\u001f\t1\u0011I\\=SK\u001a\u00042!\u0007\u000e\u001d\u001b\u0005i\u0011BA\u000e\u000e\u0005!IE/\u001a:bE2,\u0007CA\u000f\u001f\u0019\u0001!aa\b\u0001\u0005\u0006\u0004\u0001#!A!\u0012\u0005\u0005\"\u0003CA\u000b#\u0013\t\u0019sBA\u0004O_RD\u0017N\\4\u0011\u0005U)\u0013B\u0001\u0014\u0010\u0005\r\te.\u001f\t\u0005+!RC$\u0003\u0002*\u001f\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0005\u0002\u0016W%\u0011Af\u0004\u0002\u0004\u0013:$\b#B\r/9A\n\u0014BA\u0018\u000e\u0005\u0019\u0019V-](qgB\u0011\u0011\u0004\u0001\t\u00043\u0001a\u0002\u0003B\r49AJ!\u0001N\u0007\u0003/%#XM]1cY\u00164\u0015m\u0019;pef$UMZ1vYR\u001c\bCA\u000b7\u0013\t9tB\u0001\u0004FcV\fGn]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0002\"!F\u001e\n\u0005qz!\u0001B+oSR\fq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0002\u007fA\u0019\u0011\u0004\u0011\u0019\n\u0005\u0005k!AC*fc\u001a\u000b7\r^8ss\u0006A1-\u00198FcV\fG\u000e\u0006\u0002E\u000fB\u0011Q#R\u0005\u0003\r>\u0011qAQ8pY\u0016\fg\u000eC\u0003I\u0007\u0001\u0007A%\u0001\u0003uQ\u0006$\u0018AB3rk\u0006d7\u000f\u0006\u0002E\u0017\")A\n\u0002a\u0001I\u0005\tq.\u0001\u0005iCND7i\u001c3f)\u0005Q\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003E\u0003\"AU-\u000f\u0005M;\u0006C\u0001+\u0010\u001b\u0005)&B\u0001,\u0012\u0003\u0019a$o\\8u}%\u0011\u0001lD\u0001\u0007!J,G-\u001a4\n\u0005i[&AB*ue&twM\u0003\u0002Y\u001f\u0005a1\u000f\u001e:j]\u001e\u0004&/\u001a4jqV\t\u0011+A\u0002TKF\u0004\"!G\u0005\u0014\u0005%\t\u0007c\u00012fa9\u0011\u0011dY\u0005\u0003I6\t!bU3r\r\u0006\u001cGo\u001c:z\u0013\t1wM\u0001\u0005EK2,w-\u0019;f\u0015\t!W\"\u0001\u0004=S:LGO\u0010\u000b\u0002?\u0006aqO]5uKJ+\u0007\u000f\\1dKR\tA\u000e\u0005\u0002ne6\taN\u0003\u0002pa\u0006!A.\u00198h\u0015\u0005\t\u0018\u0001\u00026bm\u0006L!a\u001d8\u0003\r=\u0013'.Z2uQ\u0011IQ\u000f_=\u0011\u0005U1\u0018BA<\u0010\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0004Q\u0011AQ\u000f_="
)
public interface Seq extends Iterable, PartialFunction, SeqOps, Equals {
   static Builder newBuilder() {
      return Seq$.MODULE$.newBuilder();
   }

   static SeqOps from(final IterableOnce it) {
      return Seq$.MODULE$.from(it);
   }

   static SeqOps unapplySeq(final SeqOps x) {
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
      return Seq$.MODULE$.from(new View.Tabulate(n, f));
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
      return Seq$.MODULE$.from(new View.Fill(n, elem));
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Seq$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Seq$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      return Seq$.MODULE$.from(new View.Unfold(init, f));
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      return Seq$.MODULE$.from(new View.Iterate(start, len, f));
   }

   // $FF: synthetic method
   static SeqFactory iterableFactory$(final Seq $this) {
      return $this.iterableFactory();
   }

   default SeqFactory iterableFactory() {
      return Seq$.MODULE$;
   }

   // $FF: synthetic method
   static boolean canEqual$(final Seq $this, final Object that) {
      return $this.canEqual(that);
   }

   default boolean canEqual(final Object that) {
      return true;
   }

   // $FF: synthetic method
   static boolean equals$(final Seq $this, final Object o) {
      return $this.equals(o);
   }

   default boolean equals(final Object o) {
      if (this != o) {
         boolean var10000;
         label16: {
            if (o instanceof Seq) {
               Seq var2 = (Seq)o;
               if (var2.canEqual(this)) {
                  var10000 = this.sameElements(var2);
                  break label16;
               }
            }

            var10000 = false;
         }

         if (!var10000) {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   static int hashCode$(final Seq $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return MurmurHash3$.MODULE$.seqHash(this);
   }

   // $FF: synthetic method
   static String toString$(final Seq $this) {
      return $this.toString();
   }

   default String toString() {
      return Iterable.toString$(this);
   }

   // $FF: synthetic method
   static String stringPrefix$(final Seq $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "Seq";
   }

   static void $init$(final Seq $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
