package breeze.optimize.flow;

import breeze.collection.compat.package$;
import breeze.collection.mutable.AutoUpdater;
import breeze.collection.mutable.AutoUpdater$;
import breeze.optimize.linear.LinearProgram;
import breeze.optimize.linear.LinearProgram$;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Map;
import scala.collection.mutable.Queue;
import scala.collection.mutable.Set;
import scala.collection.mutable.Queue.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u3Aa\u0002\u0005\u0001\u001f!Aq\u0003\u0001BC\u0002\u0013\u0005\u0001\u0004\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003\u001a\u0011\u0015I\u0003\u0001\"\u0001+\u0011!i\u0003\u0001#b\u0001\n\u0003q\u0003\"B#\u0001\t\u00031\u0005bB)\u0001#\u0003%\tA\u0015\u0002\n\u0019Bk\u0015\r\u001f$m_^T!!\u0003\u0006\u0002\t\u0019dwn\u001e\u0006\u0003\u00171\t\u0001b\u001c9uS6L'0\u001a\u0006\u0002\u001b\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0002\u0011?M\u0011\u0001!\u0005\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\u0003\u001d,\u0012!\u0007\t\u00045miR\"\u0001\u0005\n\u0005qA!!\u0003$m_^<%/\u00199i!\tqr\u0004\u0004\u0001\u0005\u000b\u0001\u0002!\u0019A\u0011\u0003\u00039\u000b\"AI\u0013\u0011\u0005I\u0019\u0013B\u0001\u0013\u0014\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0005\u0014\n\u0005\u001d\u001a\"aA!os\u0006\u0011q\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005-b\u0003c\u0001\u000e\u0001;!)qc\u0001a\u00013\u00059Q.\u0019=GY><X#A\u0018\u0011\tI\u0001$GQ\u0005\u0003cM\u0011a\u0001V;qY\u0016\u0014\u0004\u0003B\u001a;{\ts!\u0001\u000e\u001d\u0011\u0005U\u001aR\"\u0001\u001c\u000b\u0005]r\u0011A\u0002\u001fs_>$h(\u0003\u0002:'\u00051\u0001K]3eK\u001aL!a\u000f\u001f\u0003\u00075\u000b\u0007O\u0003\u0002:'A\u0011a\b\u0011\b\u0003\u007f\u0005i\u0011\u0001A\u0005\u0003\u0003n\u0011A!\u00123hKB\u0011!cQ\u0005\u0003\tN\u0011a\u0001R8vE2,\u0017aC7j]\u000e{7\u000f\u001e$m_^$\"aR(\u0011\tI\u0001\u0004J\u0011\t\u0005\u0013:k$)D\u0001K\u0015\tYE*A\u0005j[6,H/\u00192mK*\u0011QjE\u0001\u000bG>dG.Z2uS>t\u0017BA\u001eK\u0011\u001d\u0001V\u0001%AA\u0002\t\u000b1\"\\5oS6,XN\u00127po\u0006)R.\u001b8D_N$h\t\\8xI\u0011,g-Y;mi\u0012\nT#A*+\u0005\t#6&A+\u0011\u0005Y[V\"A,\u000b\u0005aK\u0016!C;oG\",7m[3e\u0015\tQ6#\u0001\u0006b]:|G/\u0019;j_:L!\u0001X,\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public class LPMaxFlow {
   private Tuple2 maxFlow;
   private final FlowGraph g;
   private volatile boolean bitmap$0;

   public FlowGraph g() {
      return this.g;
   }

   private Tuple2 maxFlow$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            Queue queue = (Queue).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            queue.$plus$eq(this.g().source());
            Set visited = (Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            LinearProgram lp = new LinearProgram();
            Builder constraints = package$.MODULE$.arraySeqBuilder(scala.reflect.ClassTag..MODULE$.apply(LinearProgram.Constraint.class));
            AutoUpdater incoming = AutoUpdater$.MODULE$.apply(() -> new ArrayBuffer());
            AutoUpdater outgoing = AutoUpdater$.MODULE$.apply(() -> new ArrayBuffer());
            Map edgeMap = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);

            while(queue.nonEmpty()) {
               Object n = queue.dequeue();
               if (!visited.apply(n)) {
                  visited.$plus$eq(n);
                  if (!BoxesRunTime.equals(n, this.g().sink())) {
                     this.g().edgesFrom(n).foreach((e) -> {
                        LinearProgram.Real f_e = lp.new Real((new StringBuilder(2)).append(e.head()).append("->").append(e.tail()).toString());
                        edgeMap.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(e), f_e));
                        constraints.$plus$eq(f_e.$less$eq(e.capacity()));
                        constraints.$plus$eq(f_e.$greater$eq((double)0.0F));
                        ((Growable)incoming.apply(e.tail())).$plus$eq(f_e.$times(e.gain()));
                        ((Growable)outgoing.apply(e.head())).$plus$eq(f_e);
                        return !visited.apply(e.tail()) ? queue.$plus$eq(e.tail()) : BoxedUnit.UNIT;
                     });
                  }
               }
            }

            visited.foreach((nx) -> {
               $anonfun$maxFlow$4(incoming, outgoing, constraints, nx);
               return BoxedUnit.UNIT;
            });
            LinearProgram.Expression total = (LinearProgram.Expression)((IterableOnceOps)incoming.apply(this.g().sink())).reduceLeft((x$9, x$10) -> x$9.$plus(x$10));
            LinearProgram.Result solution = lp.maximize(total.subjectTo((Seq)constraints.result()), LinearProgram$.MODULE$.mySolver());
            this.maxFlow = new Tuple2(scala.Predef..MODULE$.Map().empty().$plus$plus(edgeMap.mapValues((x$11) -> BoxesRunTime.boxToDouble($anonfun$maxFlow$12(solution, x$11)))), BoxesRunTime.boxToDouble(solution.value()));
            this.bitmap$0 = true;
         }
      } catch (Throwable var13) {
         throw var13;
      }

      return this.maxFlow;
   }

   public Tuple2 maxFlow() {
      return !this.bitmap$0 ? this.maxFlow$lzycompute() : this.maxFlow;
   }

   public Tuple2 minCostFlow(final double minimumFlow) {
      double mf = minimumFlow < (double)0 ? this.maxFlow()._2$mcD$sp() : minimumFlow;
      Queue queue = (Queue).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      queue.$plus$eq(this.g().source());
      Set visited = (Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      LinearProgram lp = new LinearProgram();
      ArrayBuffer costs = new ArrayBuffer();
      Builder constraints = package$.MODULE$.arraySeqBuilder(scala.reflect.ClassTag..MODULE$.apply(LinearProgram.Constraint.class));
      AutoUpdater incoming = AutoUpdater$.MODULE$.apply(() -> (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      AutoUpdater outgoing = AutoUpdater$.MODULE$.apply(() -> (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      Map edgeMap = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);

      while(queue.nonEmpty()) {
         Object n = queue.dequeue();
         if (!visited.apply(n)) {
            visited.$plus$eq(n);
            if (!BoxesRunTime.equals(n, this.g().sink())) {
               this.g().edgesFrom(n).foreach((e) -> {
                  LinearProgram.Real f_e = lp.new Real((new StringBuilder(2)).append(e.head()).append("->").append(e.tail()).toString());
                  edgeMap.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(e), f_e));
                  constraints.$plus$eq(f_e.$less$eq(e.capacity()));
                  constraints.$plus$eq(f_e.$greater$eq((double)0.0F));
                  costs.$plus$eq(f_e.$times(e.cost()));
                  ((Growable)incoming.apply(e.tail())).$plus$eq(f_e.$times(e.gain()));
                  ((Growable)outgoing.apply(e.head())).$plus$eq(f_e);
                  return !visited.apply(e.tail()) ? queue.$plus$eq(e.tail()) : BoxedUnit.UNIT;
               });
            }
         }
      }

      visited.foreach((nx) -> {
         $anonfun$minCostFlow$4(incoming, outgoing, constraints, nx);
         return BoxedUnit.UNIT;
      });
      LinearProgram.Expression flowTotal = (LinearProgram.Expression)((IterableOnceOps)incoming.apply(this.g().sink())).reduceLeft((x$20, x$21) -> x$20.$plus(x$21));
      constraints.$plus$eq(flowTotal.$greater$eq(mf));
      LinearProgram.Expression total = (LinearProgram.Expression)costs.reduceLeft((x$22, x$23) -> x$22.$plus(x$23));
      LinearProgram.Result solution = lp.maximize(total.$times((double)-1.0F).subjectTo((Seq)constraints.result()), LinearProgram$.MODULE$.mySolver());
      return new Tuple2(scala.Predef..MODULE$.Map().empty().$plus$plus(edgeMap.mapValues((x$24) -> BoxesRunTime.boxToDouble($anonfun$minCostFlow$13(solution, x$24)))), BoxesRunTime.boxToDouble(-solution.value()));
   }

   public double minCostFlow$default$1() {
      return (double)-1.0F;
   }

   // $FF: synthetic method
   public static final void $anonfun$maxFlow$5(final AutoUpdater outgoing$1, final Object n$1, final Builder constraints$1, final ArrayBuffer inc) {
      outgoing$1.get(n$1).foreach((out) -> {
         constraints$1.$plus$eq(((LinearProgram.Expression)inc.reduceLeft((x$1, x$2) -> x$1.$plus(x$2))).$less$eq((LinearProgram.Expression)out.reduceLeft((x$3, x$4) -> x$3.$plus(x$4))));
         return (Builder)constraints$1.$plus$eq(((LinearProgram.Expression)out.reduceLeft((x$5, x$6) -> x$5.$plus(x$6))).$less$eq((LinearProgram.Expression)inc.reduceLeft((x$7, x$8) -> x$7.$plus(x$8))));
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$maxFlow$4(final AutoUpdater incoming$1, final AutoUpdater outgoing$1, final Builder constraints$1, final Object n) {
      incoming$1.get(n).foreach((inc) -> {
         $anonfun$maxFlow$5(outgoing$1, n, constraints$1, inc);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final double $anonfun$maxFlow$12(final LinearProgram.Result solution$1, final LinearProgram.Variable x$11) {
      return solution$1.valueOf(x$11);
   }

   // $FF: synthetic method
   public static final void $anonfun$minCostFlow$5(final AutoUpdater outgoing$2, final Object n$2, final Builder constraints$2, final ArrayBuffer inc) {
      outgoing$2.get(n$2).foreach((out) -> {
         constraints$2.$plus$eq(((LinearProgram.Expression)inc.reduceLeft((x$12, x$13) -> x$12.$plus(x$13))).$less$eq((LinearProgram.Expression)out.reduceLeft((x$14, x$15) -> x$14.$plus(x$15))));
         return (Builder)constraints$2.$plus$eq(((LinearProgram.Expression)out.reduceLeft((x$16, x$17) -> x$16.$plus(x$17))).$less$eq((LinearProgram.Expression)inc.reduceLeft((x$18, x$19) -> x$18.$plus(x$19))));
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$minCostFlow$4(final AutoUpdater incoming$2, final AutoUpdater outgoing$2, final Builder constraints$2, final Object n) {
      incoming$2.get(n).foreach((inc) -> {
         $anonfun$minCostFlow$5(outgoing$2, n, constraints$2, inc);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final double $anonfun$minCostFlow$13(final LinearProgram.Result solution$2, final LinearProgram.Variable x$24) {
      return solution$2.valueOf(x$24);
   }

   public LPMaxFlow(final FlowGraph g) {
      this.g = g;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
