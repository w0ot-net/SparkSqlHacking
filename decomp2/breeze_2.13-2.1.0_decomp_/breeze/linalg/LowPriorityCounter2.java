package breeze.linalg;

import breeze.linalg.support.CanCollapseAxis;
import breeze.math.Semiring;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\rq\u0003C\u0003[\u0001\u0011\r1LA\nM_^\u0004&/[8sSRL8i\\;oi\u0016\u0014(G\u0003\u0002\u0007\u000f\u00051A.\u001b8bY\u001eT\u0011\u0001C\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001a\u0003\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0002C\u0001\u0007\u0015\u0013\t)RB\u0001\u0003V]&$\u0018aD2b]\u000e{G\u000e\\1qg\u0016\u0014vn^:\u0016\u000ba)sFM \u0015\te\u0011%J\u0015\t\b5uyBg\u000f B\u001b\u0005Y\"B\u0001\u000f\u0006\u0003\u001d\u0019X\u000f\u001d9peRL!AH\u000e\u0003\u001f\r\u000bgnQ8mY\u0006\u00048/Z!ySN\u0004R\u0001I\u0011$]Ej\u0011!B\u0005\u0003E\u0015\u0011\u0001bQ8v]R,'O\r\t\u0003I\u0015b\u0001\u0001B\u0003'\u0005\t\u0007qE\u0001\u0002LcE\u0011\u0001f\u000b\t\u0003\u0019%J!AK\u0007\u0003\u000f9{G\u000f[5oOB\u0011A\u0002L\u0005\u0003[5\u00111!\u00118z!\t!s\u0006B\u00031\u0005\t\u0007qE\u0001\u0002LeA\u0011AE\r\u0003\u0006g\t\u0011\ra\n\u0002\u0002-:\u0011Q\u0007\u000f\b\u0003AYJ!aN\u0003\u0002\t\u0005C\u0018n]\u0005\u0003si\n!a\u0018\u0019\u000b\u0005]*\u0001\u0003\u0002\u0011=GEJ!!P\u0003\u0003\u000f\r{WO\u001c;feB\u0011Ae\u0010\u0003\u0006\u0001\n\u0011\ra\n\u0002\u0002%B!\u0001\u0005\u0010\u0018?\u0011\u001d\u0019%!!AA\u0004\u0011\u000b1\"\u001a<jI\u0016t7-\u001a\u00132iA\u0019Q\t\u0013 \u000e\u0003\u0019S!aR\u0007\u0002\u000fI,g\r\\3di&\u0011\u0011J\u0012\u0002\t\u00072\f7o\u001d+bO\"91JAA\u0001\u0002\ba\u0015aC3wS\u0012,gnY3%cU\u00022!\u0014)?\u001b\u0005q%BA(\b\u0003\u001d\u0019Ho\u001c:bO\u0016L!!\u0015(\u0003\ti+'o\u001c\u0005\b'\n\t\t\u0011q\u0001U\u0003-)g/\u001b3f]\u000e,G%\r\u001c\u0011\u0007UCf(D\u0001W\u0015\t9v!\u0001\u0003nCRD\u0017BA-W\u0005!\u0019V-\\5sS:<\u0017aD2b]\u000e{G\u000e\\1qg\u0016\u001cu\u000e\\:\u0016\u000bq\u0003'\r\u001a6\u0015\tucwN\u001d\t\b5uqV\r[5l!\u0015\u0001\u0013eX1d!\t!\u0003\rB\u0003'\u0007\t\u0007q\u0005\u0005\u0002%E\u0012)\u0001g\u0001b\u0001OA\u0011A\u0005\u001a\u0003\u0006g\r\u0011\ra\n\b\u0003k\u0019L!a\u001a\u001e\u0002\u0005}\u000b\u0004\u0003\u0002\u0011=C\u000e\u0004\"\u0001\n6\u0005\u000b\u0001\u001b!\u0019A\u0014\u0011\t\u0001bt,\u001b\u0005\b[\u000e\t\t\u0011q\u0001o\u0003-)g/\u001b3f]\u000e,G%M\u001c\u0011\u0007\u0015C\u0015\u000eC\u0004q\u0007\u0005\u0005\t9A9\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000f\t\u0004\u001bBK\u0007bB:\u0004\u0003\u0003\u0005\u001d\u0001^\u0001\fKZLG-\u001a8dK\u0012\n\u0014\bE\u0002V1&\u0004"
)
public interface LowPriorityCounter2 {
   // $FF: synthetic method
   static CanCollapseAxis canCollapseRows$(final LowPriorityCounter2 $this, final ClassTag evidence$14, final Zero evidence$15, final Semiring evidence$16) {
      return $this.canCollapseRows(evidence$14, evidence$15, evidence$16);
   }

   default CanCollapseAxis canCollapseRows(final ClassTag evidence$14, final Zero evidence$15, final Semiring evidence$16) {
      return new CanCollapseAxis(evidence$15) {
         private final Zero evidence$15$1;

         public Counter apply(final Counter2 from, final Axis._0$ axis, final Function1 f) {
            Counter result = Counter$.MODULE$.apply(this.evidence$15$1);
            ((IterableOnceOps)from.keySet().map((x$8) -> x$8._2())).foreach((dom) -> {
               $anonfun$apply$9(result, f, from, dom);
               return BoxedUnit.UNIT;
            });
            return result;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$9(final Counter result$3, final Function1 f$3, final Counter2 from$3, final Object dom) {
            result$3.update(dom, f$3.apply(from$3.apply(.MODULE$.$colon$colon(), dom, Counter2$.MODULE$.canSliceCol())));
         }

         public {
            this.evidence$15$1 = evidence$15$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanCollapseAxis canCollapseCols$(final LowPriorityCounter2 $this, final ClassTag evidence$17, final Zero evidence$18, final Semiring evidence$19) {
      return $this.canCollapseCols(evidence$17, evidence$18, evidence$19);
   }

   default CanCollapseAxis canCollapseCols(final ClassTag evidence$17, final Zero evidence$18, final Semiring evidence$19) {
      return new CanCollapseAxis(evidence$18) {
         private final Zero evidence$18$1;

         public Counter apply(final Counter2 from, final Axis._1$ axis, final Function1 f) {
            Counter result = Counter$.MODULE$.apply(this.evidence$18$1);
            from.data().withFilter((check$ifrefutable$7) -> BoxesRunTime.boxToBoolean($anonfun$apply$10(check$ifrefutable$7))).foreach((x$9) -> {
               $anonfun$apply$11(result, f, x$9);
               return BoxedUnit.UNIT;
            });
            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$10(final Tuple2 check$ifrefutable$7) {
            boolean var1;
            if (check$ifrefutable$7 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$11(final Counter result$4, final Function1 f$4, final Tuple2 x$9) {
            if (x$9 != null) {
               Object dom = x$9._1();
               Counter c = (Counter)x$9._2();
               result$4.update(dom, f$4.apply(c));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$9);
            }
         }

         public {
            this.evidence$18$1 = evidence$18$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final LowPriorityCounter2 $this) {
   }
}
