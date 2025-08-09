package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.CallSite;
import scala.Function2;
import scala.Option;
import scala.Option.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc!\u0002\n\u0014\u0001UY\u0002\"\u0003\u0011\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0012)\u0011%I\u0003A!A!\u0002\u0013Q3\b\u0003\u0005=\u0001\t\u0015\r\u0011\"\u0001>\u0011!1\u0006A!A!\u0002\u0013q\u0004\u0002\u0003.\u0001\u0005\u000b\u0007I\u0011A.\t\u0011}\u0003!\u0011!Q\u0001\nqC\u0011\u0002\u0019\u0001\u0003\u0002\u0003\u0006I!\u00193\t\u0013\u0015\u0004!\u0011!Q\u0001\n\t2\u0007\"C4\u0001\u0005\u0003\u0005\u000b\u0011\u00025o\u0011%y\u0007A!A!\u0002\u0013\u0011\u0003\u000fC\u0003r\u0001\u0011\u0005!\u000f\u0003\u0005\u0002\u0012\u0001\u0001\u000b\u0015BA\n\u0011\u001d\ty\u0002\u0001C\u0001\u0003CAq!a\t\u0001\t\u0003\t)\u0003C\u0004\u00022\u0001!\t!a\r\t\u000f\u0005U\u0002\u0001\"\u0011\u00028!9\u0011q\b\u0001\u0005B\u0005\u0005#a\u0003*fgVdGo\u0015;bO\u0016T!\u0001F\u000b\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0014\u0005\u0001a\u0002CA\u000f\u001f\u001b\u0005\u0019\u0012BA\u0010\u0014\u0005\u0015\u0019F/Y4f\u0003\tIGm\u0001\u0001\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\u0007%sG/\u0003\u0002!=\u0005\u0019!\u000f\u001a31\u0005-\u0012\u0004c\u0001\u0017/a5\tQF\u0003\u0002*+%\u0011q&\f\u0002\u0004%\u0012#\u0005CA\u00193\u0019\u0001!\u0011b\r\u0002\u0002\u0002\u0003\u0005)\u0011\u0001\u001b\u0003\u0007}#\u0013'\u0005\u00026qA\u00111EN\u0005\u0003o\u0011\u0012qAT8uQ&tw\r\u0005\u0002$s%\u0011!\b\n\u0002\u0004\u0003:L\u0018BA\u0015\u001f\u0003\u00111WO\\2\u0016\u0003y\u0002$a\u0010-\u0011\u000b\r\u0002%IR,\n\u0005\u0005##!\u0003$v]\u000e$\u0018n\u001c83!\t\u0019E)D\u0001\u0016\u0013\t)UCA\u0006UCN\\7i\u001c8uKb$\bGA$U!\rA\u0005k\u0015\b\u0003\u0013:s!AS'\u000e\u0003-S!\u0001T\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0013BA(%\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0015*\u0003\u0011%#XM]1u_JT!a\u0014\u0013\u0011\u0005E\"F!C+\u0005\u0003\u0003\u0005\tQ!\u00015\u0005\ryFEM\u0001\u0006MVt7\r\t\t\u0003ca#\u0011\"\u0017\u0003\u0002\u0002\u0003\u0005)\u0011\u0001\u001b\u0003\u0007}#3'\u0001\u0006qCJ$\u0018\u000e^5p]N,\u0012\u0001\u0018\t\u0004Gu\u0013\u0013B\u00010%\u0005\u0015\t%O]1z\u0003-\u0001\u0018M\u001d;ji&|gn\u001d\u0011\u0002\u000fA\f'/\u001a8ugB\u0019\u0001J\u0019\u000f\n\u0005\r\u0014&\u0001\u0002'jgRL!\u0001\u0019\u0010\u0002\u0015\u0019L'o\u001d;K_\nLE-\u0003\u0002f=\u0005A1-\u00197m'&$X\r\u0005\u0002jY6\t!N\u0003\u0002l+\u0005!Q\u000f^5m\u0013\ti'N\u0001\u0005DC2d7+\u001b;f\u0013\t9g$A\tsKN|WO]2f!J|g-\u001b7f\u0013\u0012L!a\u001c\u0010\u0002\rqJg.\u001b;?)9\u0019H/\u001e>\u0002\b\u0005%\u00111BA\u0007\u0003\u001f\u0001\"!\b\u0001\t\u000b\u0001Z\u0001\u0019\u0001\u0012\t\u000b%Z\u0001\u0019\u0001<1\u0005]L\bc\u0001\u0017/qB\u0011\u0011'\u001f\u0003\ngU\f\t\u0011!A\u0003\u0002QBQ\u0001P\u0006A\u0002m\u00044\u0001`A\u0003!\u0019\u0019\u0003IQ?\u0002\u0004A\u001aa0!\u0001\u0011\u0007!\u0003v\u0010E\u00022\u0003\u0003!\u0011\"\u0016>\u0002\u0002\u0003\u0005)\u0011\u0001\u001b\u0011\u0007E\n)\u0001B\u0005Zu\u0006\u0005\t\u0011!B\u0001i!)!l\u0003a\u00019\")\u0001m\u0003a\u0001C\")Qm\u0003a\u0001E!)qm\u0003a\u0001Q\")qn\u0003a\u0001E\u0005Qq,Y2uSZ,'j\u001c2\u0011\u000b\r\n)\"!\u0007\n\u0007\u0005]AE\u0001\u0004PaRLwN\u001c\t\u0004;\u0005m\u0011bAA\u000f'\tI\u0011i\u0019;jm\u0016TuNY\u0001\nC\u000e$\u0018N^3K_\n,\"!a\u0005\u0002\u0019M,G/Q2uSZ,'j\u001c2\u0015\t\u0005\u001d\u0012Q\u0006\t\u0004G\u0005%\u0012bAA\u0016I\t!QK\\5u\u0011\u001d\tyC\u0004a\u0001\u00033\t1A[8c\u0003=\u0011X-\\8wK\u0006\u001bG/\u001b<f\u0015>\u0014GCAA\u0014\u0003U1\u0017N\u001c3NSN\u001c\u0018N\\4QCJ$\u0018\u000e^5p]N$\"!!\u000f\u0011\t!\u000bYDI\u0005\u0004\u0003{\u0011&aA*fc\u0006AAo\\*ue&tw\r\u0006\u0002\u0002DA!\u0011QIA'\u001d\u0011\t9%!\u0013\u0011\u0005)#\u0013bAA&I\u00051\u0001K]3eK\u001aLA!a\u0014\u0002R\t11\u000b\u001e:j]\u001eT1!a\u0013%\u0001"
)
public class ResultStage extends Stage {
   private final Function2 func;
   private final int[] partitions;
   private Option _activeJob;

   public Function2 func() {
      return this.func;
   }

   public int[] partitions() {
      return this.partitions;
   }

   public Option activeJob() {
      return this._activeJob;
   }

   public void setActiveJob(final ActiveJob job) {
      this._activeJob = .MODULE$.apply(job);
   }

   public void removeActiveJob() {
      this._activeJob = scala.None..MODULE$;
   }

   public Seq findMissingPartitions() {
      ActiveJob job = (ActiveJob)this.activeJob().get();
      return (Seq)scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), job.numPartitions()).filter((JFunction1.mcZI.sp)(id) -> !job.finished()[id]);
   }

   public String toString() {
      return "ResultStage " + super.id();
   }

   public ResultStage(final int id, final RDD rdd, final Function2 func, final int[] partitions, final List parents, final int firstJobId, final CallSite callSite, final int resourceProfileId) {
      super(id, rdd, partitions.length, parents, firstJobId, callSite, resourceProfileId);
      this.func = func;
      this.partitions = partitions;
      this._activeJob = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
