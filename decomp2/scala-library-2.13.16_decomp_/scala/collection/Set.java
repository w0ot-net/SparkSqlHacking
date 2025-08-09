package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Equals;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005=4q\u0001D\u0007\u0011\u0002\u0007\u0005!\u0003C\u00033\u0001\u0011\u00051\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003?\u0001\u0011\u0005s\bC\u0003B\u0001\u0011\u0005#\tC\u0003G\u0001\u0011\u0005s\t\u0003\u0004L\u0001\u0001&\t\u0006\u0014\u0005\u0006+\u0002!\tEV\u0004\u0006/6A\t\u0001\u0017\u0004\u0006\u00195A\t!\u0017\u0005\u0006C&!\tA\u0019\u0005\bG&\t\t\u0011\"\u0003e\u0005\r\u0019V\r\u001e\u0006\u0003\u001d=\t!bY8mY\u0016\u001cG/[8o\u0015\u0005\u0001\u0012!B:dC2\f7\u0001A\u000b\u0003'y\u0019b\u0001\u0001\u000b\u0019O1z\u0003CA\u000b\u0017\u001b\u0005y\u0011BA\f\u0010\u0005\u0019\te.\u001f*fMB\u0019\u0011D\u0007\u000f\u000e\u00035I!aG\u0007\u0003\u0011%#XM]1cY\u0016\u0004\"!\b\u0010\r\u0001\u0011)q\u0004\u0001b\u0001A\t\t\u0011)\u0005\u0002\"IA\u0011QCI\u0005\u0003G=\u0011qAT8uQ&tw\r\u0005\u0002\u0016K%\u0011ae\u0004\u0002\u0004\u0003:L\b#B\r)9)Z\u0013BA\u0015\u000e\u0005\u0019\u0019V\r^(qgB\u0011\u0011\u0004\u0001\t\u00043\u0001a\u0002CA\u000b.\u0013\tqsB\u0001\u0004FcV\fGn\u001d\t\u00053Ab\"&\u0003\u00022\u001b\t9\u0012\n^3sC\ndWMR1di>\u0014\u0018\u0010R3gCVdGo]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Q\u0002\"!F\u001b\n\u0005Yz!\u0001B+oSR\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003sq\u0002\"!\u0006\u001e\n\u0005mz!a\u0002\"p_2,\u0017M\u001c\u0005\u0006{\t\u0001\r\u0001J\u0001\u0005i\"\fG/\u0001\u0004fcV\fGn\u001d\u000b\u0003s\u0001CQ!P\u0002A\u0002\u0011\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u0007B\u0011Q\u0003R\u0005\u0003\u000b>\u00111!\u00138u\u0003=IG/\u001a:bE2,g)Y2u_JLX#\u0001%\u0011\u0007eI%&\u0003\u0002K\u001b\ty\u0011\n^3sC\ndWMR1di>\u0014\u00180\u0001\u0007tiJLgn\u001a)sK\u001aL\u00070F\u0001N!\tq5+D\u0001P\u0015\t\u0001\u0016+\u0001\u0003mC:<'\"\u0001*\u0002\t)\fg/Y\u0005\u0003)>\u0013aa\u0015;sS:<\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00035\u000b1aU3u!\tI\u0012b\u0005\u0002\n5B\u00191L\u0018\u0016\u000f\u0005ea\u0016BA/\u000e\u0003=IE/\u001a:bE2,g)Y2u_JL\u0018BA0a\u0005!!U\r\\3hCR,'BA/\u000e\u0003\u0019a\u0014N\\5u}Q\t\u0001,\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001f!\tqe-\u0003\u0002h\u001f\n1qJ\u00196fGRDC!C5m[B\u0011QC[\u0005\u0003W>\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\rAC\u0001C5m[\u0002"
)
public interface Set extends Iterable, SetOps, Equals {
   static Builder newBuilder() {
      return Set$.MODULE$.newBuilder();
   }

   static Object from(final IterableOnce it) {
      return Set$.MODULE$.from(it);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      IterableFactory.Delegate tabulate_this = Set$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$7$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      IterableFactory.Delegate tabulate_this = Set$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$5$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      IterableFactory.Delegate tabulate_this = Set$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$3$adapted);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      IterableFactory.Delegate tabulate_this = Set$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$1$adapted);
   }

   static Object tabulate(final int n, final Function1 f) {
      return Set$.MODULE$.from(new View.Tabulate(n, f));
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      IterableFactory.Delegate fill_this = Set$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$4);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      IterableFactory.Delegate fill_this = Set$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$3);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      IterableFactory.Delegate fill_this = Set$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$2);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      IterableFactory.Delegate fill_this = Set$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$1);
   }

   static Object fill(final int n, final Function0 elem) {
      return Set$.MODULE$.from(new View.Fill(n, elem));
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Set$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Set$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      return Set$.MODULE$.from(new View.Unfold(init, f));
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      return Set$.MODULE$.from(new View.Iterate(start, len, f));
   }

   // $FF: synthetic method
   static boolean canEqual$(final Set $this, final Object that) {
      return $this.canEqual(that);
   }

   default boolean canEqual(final Object that) {
      return true;
   }

   // $FF: synthetic method
   static boolean equals$(final Set $this, final Object that) {
      return $this.equals(that);
   }

   default boolean equals(final Object that) {
      if (this != that) {
         boolean var10000;
         label29: {
            if (that instanceof Set) {
               Set var2 = (Set)that;
               if (var2.canEqual(this)) {
                  if (this.size() == var2.size()) {
                     try {
                        var10000 = this.subsetOf(var2);
                     } catch (ClassCastException var3) {
                        var10000 = false;
                     }

                     if (var10000) {
                        var10000 = true;
                        break label29;
                     }
                  }

                  var10000 = false;
                  break label29;
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
   static int hashCode$(final Set $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return MurmurHash3$.MODULE$.setHash(this);
   }

   // $FF: synthetic method
   static IterableFactory iterableFactory$(final Set $this) {
      return $this.iterableFactory();
   }

   default IterableFactory iterableFactory() {
      return Set$.MODULE$;
   }

   // $FF: synthetic method
   static String stringPrefix$(final Set $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "Set";
   }

   // $FF: synthetic method
   static String toString$(final Set $this) {
      return $this.toString();
   }

   default String toString() {
      return Iterable.toString$(this);
   }

   static void $init$(final Set $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
