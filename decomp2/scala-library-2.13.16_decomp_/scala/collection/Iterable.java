package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Some;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mba\u0002\t\u0012!\u0003\r\tA\u0006\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006q\u0001!)!\u000f\u0005\u0006\u000b\u0002!)\"\u000f\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006\u0017\u0002!\t!\u000f\u0005\u0007#\u0002\u0001K\u0011\u0003*\t\ry\u0003AQA\nS\u0011\u0019y\u0006\u0001)C\t%\")a\r\u0001C!O\")\u0001\u000e\u0001C\u0001S\u001e)A/\u0005E\u0001k\u001a)\u0001#\u0005E\u0001m\")a\u0010\u0004C\u0001\u007f\"9\u0011\u0011\u0001\u0007\u0005\u0002\u0005\r\u0001\"CA\t\u0019\u0005\u0005I\u0011BA\n\u0005!IE/\u001a:bE2,'B\u0001\n\u0014\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002)\u0005)1oY1mC\u000e\u0001QCA\f#'\u0015\u0001\u0001\u0004H\u00161!\tI\"$D\u0001\u0014\u0013\tY2C\u0001\u0004B]f\u0014VM\u001a\t\u0004;y\u0001S\"A\t\n\u0005}\t\"\u0001D%uKJ\f'\r\\3P]\u000e,\u0007CA\u0011#\u0019\u0001!aa\t\u0001\u0005\u0006\u0004!#!A!\u0012\u0005\u0015B\u0003CA\r'\u0013\t93CA\u0004O_RD\u0017N\\4\u0011\u0005eI\u0013B\u0001\u0016\u0014\u0005\r\te.\u001f\t\u0006;1\u0002cfL\u0005\u0003[E\u00111\"\u0013;fe\u0006\u0014G.Z(qgB\u0011Q\u0004\u0001\t\u0004;\u0001\u0001\u0003\u0003B\u000f2A9J!AM\t\u0003/%#XM]1cY\u00164\u0015m\u0019;pef$UMZ1vYR\u001c\u0018A\u0002\u0013j]&$H\u0005F\u00016!\tIb'\u0003\u00028'\t!QK\\5u\u0003)!x.\u0013;fe\u0006\u0014G.Z\u000b\u0002u5\t\u0001\u0001\u000b\u0004\u0003y}\u0002%i\u0011\t\u00033uJ!AP\n\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0003\u0005\u000b\u0011\u0011\u0004;p\u0013R,'/\u00192mK\u0002J7\u000fI5oi\u0016\u0014h.\u00197!C:$\u0007e^5mY\u0002\u0012W\rI7bI\u0016\u0004\u0003O]8uK\u000e$X\rZ\u001e!SR\u001c\bE\\1nK\u0002J7\u000fI:j[&d\u0017M\u001d\u0011u_\u0002\u0002Go\u001c'jgR\u0004\u0007e\u001c:!AR|7+Z9aY\u0001\u0012W\u000f\u001e\u0011ji\u0002\"w.Z:oOQ\u00043m\u001c9zA9|g.L5n[V$\u0018M\u00197fA\r|G\u000e\\3di&|gn]\u0001\u0006g&t7-Z\u0011\u0002\t\u00061!GL\u00194]]\nAaY8mY\u0006y\u0011\u000e^3sC\ndWMR1di>\u0014\u00180F\u0001I!\ri\u0012JL\u0005\u0003\u0015F\u0011q\"\u0013;fe\u0006\u0014G.\u001a$bGR|'/_\u0001\u0004g\u0016\f\bFB\u0003=\u007f5\u0013u*I\u0001O\u0003=JE/\u001a:bE2,gf]3rA\u0005dw/Y=tAI,G/\u001e:og\u0002\"\b.\u001a\u0011ji\u0016\u0014\u0018M\u00197fA%$8/\u001a7gC\u0005\u0001\u0016A\u0002\u001a/cMr\u0003'A\u0005dY\u0006\u001c8OT1nKV\t1\u000b\u0005\u0002U7:\u0011Q+\u0017\t\u0003-Ni\u0011a\u0016\u0006\u00031V\ta\u0001\u0010:p_Rt\u0014B\u0001.\u0014\u0003\u0019\u0001&/\u001a3fM&\u0011A,\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005i\u001b\u0012aE2pY2,7\r^5p]\u000ec\u0017m]:OC6,\u0017\u0001D:ue&tw\r\u0015:fM&D\bF\u0002\u0005b\u007f\u0011\u0014u\n\u0005\u0002\u001aE&\u00111m\u0005\u0002\u0015I\u0016\u0004(/Z2bi\u0016$wJ^3se&$\u0017N\\4\"\u0003\u0015\f!d\u0014<feJLG-\u001a\u0011dY\u0006\u001c8OT1nK\u0002Jgn\u001d;fC\u0012\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002'\u00069A.\u0019>z5&\u0004XC\u00016p)\tY\u0017\u000fE\u0003\u001eY\u0002r'(\u0003\u0002n#\tAA*\u0019>z5&\u0004(\u0007\u0005\u0002\"_\u0012)\u0001O\u0003b\u0001I\t\t!\tC\u0003s\u0015\u0001\u00071/\u0001\u0003uQ\u0006$\bcA\u000f\u0001]\u0006A\u0011\n^3sC\ndW\r\u0005\u0002\u001e\u0019M\u0011Ab\u001e\t\u0004qntcBA\u000fz\u0013\tQ\u0018#A\bJi\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z\u0013\taXP\u0001\u0005EK2,w-\u0019;f\u0015\tQ\u0018#\u0001\u0004=S:LGO\u0010\u000b\u0002k\u000611/\u001b8hY\u0016,B!!\u0002\u0002\fQ!\u0011qAA\u0007!\u0011i\u0002!!\u0003\u0011\u0007\u0005\nY\u0001B\u0003$\u001d\t\u0007A\u0005C\u0004\u0002\u00109\u0001\r!!\u0003\u0002\u0003\u0005\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0006\u0011\t\u0005]\u0011\u0011E\u0007\u0003\u00033QA!a\u0007\u0002\u001e\u0005!A.\u00198h\u0015\t\ty\"\u0001\u0003kCZ\f\u0017\u0002BA\u0012\u00033\u0011aa\u00142kK\u000e$\bf\u0002\u0007\u0002(\u00055\u0012q\u0006\t\u00043\u0005%\u0012bAA\u0016'\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0007!:1\"a\n\u0002.\u0005=\u0002"
)
public interface Iterable extends IterableFactoryDefaults {
   static Iterable single(final Object a) {
      Iterable$ var10000 = Iterable$.MODULE$;
      return new AbstractIterable(a) {
         private final Object a$1;

         public Iterator iterator() {
            Iterator$ var10000 = Iterator$.MODULE$;
            Object single_a = this.a$1;
            return new AbstractIterator(single_a) {
               private boolean consumed;
               private final Object a$1;

               public boolean hasNext() {
                  return !this.consumed;
               }

               public Object next() {
                  if (this.consumed) {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return Iterator$.scala$collection$Iterator$$_empty.next();
                  } else {
                     this.consumed = true;
                     return this.a$1;
                  }
               }

               public Iterator sliceIterator(final int from, final int until) {
                  if (!this.consumed && from <= 0 && until != 0) {
                     return this;
                  } else {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return Iterator$.scala$collection$Iterator$$_empty;
                  }
               }

               public {
                  this.a$1 = a$1;
                  this.consumed = false;
               }
            };
         }

         public int knownSize() {
            return 1;
         }

         public Object head() {
            return this.a$1;
         }

         public Some headOption() {
            return new Some(this.a$1);
         }

         public Object last() {
            return this.a$1;
         }

         public Some lastOption() {
            return new Some(this.a$1);
         }

         public View.Single view() {
            return new View.Single(this.a$1);
         }

         public Iterable take(final int n) {
            return (Iterable)(n > 0 ? this : (Iterable)Iterable$.MODULE$.empty());
         }

         public Iterable takeRight(final int n) {
            return (Iterable)(n > 0 ? this : (Iterable)Iterable$.MODULE$.empty());
         }

         public Iterable drop(final int n) {
            return (Iterable)(n > 0 ? (Iterable)Iterable$.MODULE$.empty() : this);
         }

         public Iterable dropRight(final int n) {
            return (Iterable)(n > 0 ? (Iterable)Iterable$.MODULE$.empty() : this);
         }

         public Iterable tail() {
            return (Iterable)Iterable$.MODULE$.empty();
         }

         public Iterable init() {
            return (Iterable)Iterable$.MODULE$.empty();
         }

         public {
            this.a$1 = a$1;
         }
      };
   }

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
   static Iterable toIterable$(final Iterable $this) {
      return $this.toIterable();
   }

   /** @deprecated */
   default Iterable toIterable() {
      return this;
   }

   // $FF: synthetic method
   static Iterable coll$(final Iterable $this) {
      return $this.coll();
   }

   default Iterable coll() {
      return this;
   }

   // $FF: synthetic method
   static IterableFactory iterableFactory$(final Iterable $this) {
      return $this.iterableFactory();
   }

   default IterableFactory iterableFactory() {
      return Iterable$.MODULE$;
   }

   // $FF: synthetic method
   static Iterable seq$(final Iterable $this) {
      return $this.seq();
   }

   /** @deprecated */
   default Iterable seq() {
      return this;
   }

   // $FF: synthetic method
   static String className$(final Iterable $this) {
      return $this.className();
   }

   default String className() {
      return this.stringPrefix();
   }

   // $FF: synthetic method
   static String collectionClassName$(final Iterable $this) {
      return $this.collectionClassName();
   }

   default String collectionClassName() {
      return this.className();
   }

   // $FF: synthetic method
   static String stringPrefix$(final Iterable $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "Iterable";
   }

   // $FF: synthetic method
   static String toString$(final Iterable $this) {
      return $this.toString();
   }

   default String toString() {
      return this.mkString((new StringBuilder(1)).append(this.className()).append("(").toString(), ", ", ")");
   }

   // $FF: synthetic method
   static LazyZip2 lazyZip$(final Iterable $this, final Iterable that) {
      return $this.lazyZip(that);
   }

   default LazyZip2 lazyZip(final Iterable that) {
      return new LazyZip2(this, this, that);
   }

   static void $init$(final Iterable $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
