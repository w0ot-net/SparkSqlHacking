package breeze.optimize;

import breeze.math.InnerProductVectorSpace;
import breeze.math.VectorSpace;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}aa\u0002\u0007\u000e!\u0003\r\tA\u0005\u0005\u0006S\u0001!\tA\u000b\u0005\u0006]\u0001!\ta\f\u0005\u0006q\u00011\t!O\u0004\u0006\u00036A\tA\u0011\u0004\u0006\u00195A\ta\u0011\u0005\u0006\t\u0016!\t!\u0012\u0005\u0006\r\u0016!\ta\u0012\u0005\b?\u0016\t\n\u0011\"\u0001a\u0011\u0015qW\u0001\"\u0001p\u0011%\tY!BI\u0001\n\u0003\ti\u0001C\u0005\u0002\u0014\u0015\t\n\u0011\"\u0001\u0002\u0016\t\u00192+Z2p]\u0012|%\u000fZ3s\rVt7\r^5p]*\u0011abD\u0001\t_B$\u0018.\\5{K*\t\u0001#\u0001\u0004ce\u0016,'0Z\u0002\u0001+\r\u0019\u0002EP\n\u0004\u0001QQ\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\rE\u0002\u001c9yi\u0011!D\u0005\u0003;5\u0011A\u0002R5gM\u001a+hn\u0019;j_:\u0004\"a\b\u0011\r\u0001\u0011)\u0011\u0005\u0001b\u0001E\t\tA+\u0005\u0002$MA\u0011Q\u0003J\u0005\u0003KY\u0011qAT8uQ&tw\r\u0005\u0002\u0016O%\u0011\u0001F\u0006\u0002\u0004\u0003:L\u0018A\u0002\u0013j]&$H\u0005F\u0001,!\t)B&\u0003\u0002.-\t!QK\\5u\u0003%\u0019\u0017\r\\2vY\u0006$X\r\u0006\u00021mA!Q#M\u001a\u001f\u0013\t\u0011dC\u0001\u0004UkBdWM\r\t\u0003+QJ!!\u000e\f\u0003\r\u0011{WO\u00197f\u0011\u00159$\u00011\u0001\u001f\u0003\u0005A\u0018AC2bY\u000e,H.\u0019;feQ\u0011!\b\u0011\t\u0006+m\u001ad$P\u0005\u0003yY\u0011a\u0001V;qY\u0016\u001c\u0004CA\u0010?\t\u0015y\u0004A1\u0001#\u0005\u0005A\u0005\"B\u001c\u0004\u0001\u0004q\u0012aE*fG>tGm\u0014:eKJ4UO\\2uS>t\u0007CA\u000e\u0006'\t)A#\u0001\u0004=S:LGO\u0010\u000b\u0002\u0005\u0006IQ-\u001c9je&\u001c\u0017\r\\\u000b\u0004\u00112kFcA%Y7R\u0011!\n\u0015\t\u00057\u0001YU\n\u0005\u0002 \u0019\u0012)\u0011e\u0002b\u0001EA\u00191DT&\n\u0005=k!\u0001E#na&\u0014\u0018nY1m\u0011\u0016\u001c8/[1o\u0011\u0015\tv\u0001q\u0001S\u0003\t18\u000f\u0005\u0003T-.\u001bT\"\u0001+\u000b\u0005U{\u0011\u0001B7bi\"L!a\u0016+\u0003\u0017Y+7\r^8s'B\f7-\u001a\u0005\u00063\u001e\u0001\rAW\u0001\u0002MB\u00191\u0004H&\t\u000fq;\u0001\u0013!a\u0001g\u0005\u0019Q\r]:\u0005\u000by;!\u0019\u0001\u0012\u0003\u0003%\u000b1#Z7qSJL7-\u00197%I\u00164\u0017-\u001e7uII*2!\u00197n+\u0005\u0011'FA\u001adW\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003%)hn\u00195fG.,GM\u0003\u0002j-\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005-4'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)\u0011\u0005\u0003b\u0001E\u0011)a\f\u0003b\u0001E\u0005\u0011R.\u001b8jE\u0006$8\r[#na&\u0014\u0018nY1m+\u0011\u0001H/!\u0003\u0015\tEThp \u000b\u0003eZ\u0004Ba\u0007\u0001tkB\u0011q\u0004\u001e\u0003\u0006C%\u0011\rA\t\t\u000479\u001b\b\"B)\n\u0001\b9\b\u0003B*ygNJ!!\u001f+\u0003/%sg.\u001a:Qe>$Wo\u0019;WK\u000e$xN]*qC\u000e,\u0007\"B-\n\u0001\u0004Y\bcA\u000e}g&\u0011Q0\u0004\u0002\u0012\u0005\u0006$8\r\u001b#jM\u001a4UO\\2uS>t\u0007b\u0002/\n!\u0003\u0005\ra\r\u0005\n\u0003\u0003I\u0001\u0013!a\u0001\u0003\u0007\t\u0011BY1uG\"\u001c\u0016N_3\u0011\u0007U\t)!C\u0002\u0002\bY\u00111!\u00138u\t\u0015q\u0016B1\u0001#\u0003qi\u0017N\\5cCR\u001c\u0007.R7qSJL7-\u00197%I\u00164\u0017-\u001e7uII*R!YA\b\u0003#!Q!\t\u0006C\u0002\t\"QA\u0018\u0006C\u0002\t\nA$\\5oS\n\fGo\u00195F[BL'/[2bY\u0012\"WMZ1vYR$3'\u0006\u0004\u0002\u0018\u0005m\u0011QD\u000b\u0003\u00033Q3!a\u0001d\t\u0015\t3B1\u0001#\t\u0015q6B1\u0001#\u0001"
)
public interface SecondOrderFunction extends DiffFunction {
   static int minibatchEmpirical$default$3() {
      return SecondOrderFunction$.MODULE$.minibatchEmpirical$default$3();
   }

   static double minibatchEmpirical$default$2() {
      return SecondOrderFunction$.MODULE$.minibatchEmpirical$default$2();
   }

   static SecondOrderFunction minibatchEmpirical(final BatchDiffFunction f, final double eps, final int batchSize, final InnerProductVectorSpace vs) {
      return SecondOrderFunction$.MODULE$.minibatchEmpirical(f, eps, batchSize, vs);
   }

   static double empirical$default$2() {
      return SecondOrderFunction$.MODULE$.empirical$default$2();
   }

   static SecondOrderFunction empirical(final DiffFunction f, final double eps, final VectorSpace vs) {
      return SecondOrderFunction$.MODULE$.empirical(f, eps, vs);
   }

   // $FF: synthetic method
   static Tuple2 calculate$(final SecondOrderFunction $this, final Object x) {
      return $this.calculate(x);
   }

   default Tuple2 calculate(final Object x) {
      Tuple3 var4 = this.calculate2(x);
      if (var4 != null) {
         double v = BoxesRunTime.unboxToDouble(var4._1());
         Object g = var4._2();
         Tuple3 var2 = new Tuple3(var4, BoxesRunTime.boxToDouble(v), g);
         Tuple3 var8 = (Tuple3)var2._1();
         double v = BoxesRunTime.unboxToDouble(var2._2());
         Object g = var2._3();
         return new Tuple2(BoxesRunTime.boxToDouble(v), g);
      } else {
         throw new MatchError(var4);
      }
   }

   Tuple3 calculate2(final Object x);

   static void $init$(final SecondOrderFunction $this) {
   }
}
