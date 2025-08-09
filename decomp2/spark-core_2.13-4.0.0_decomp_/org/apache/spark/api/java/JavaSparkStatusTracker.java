package org.apache.spark.api.java;

import org.apache.spark.SparkContext;
import org.apache.spark.SparkJobInfo;
import org.apache.spark.SparkStageInfo;
import scala..less.colon.less.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3A\u0001C\u0005\u0001)!A1\u0004\u0001B\u0001B\u0003%A\u0004\u0003\u0004!\u0001\u0011\u0005Q\"\t\u0005\u0006K\u0001!\tA\n\u0005\u0006u\u0001!\ta\u000f\u0005\u0006y\u0001!\ta\u000f\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\t\u0002!\t!\u0012\u0002\u0017\u0015\u00064\u0018m\u00159be.\u001cF/\u0019;vgR\u0013\u0018mY6fe*\u0011!bC\u0001\u0005U\u00064\u0018M\u0003\u0002\r\u001b\u0005\u0019\u0011\r]5\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001+A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\f!a]2\u0011\u0005uqR\"A\u0007\n\u0005}i!\u0001D*qCJ\\7i\u001c8uKb$\u0018A\u0002\u001fj]&$h\b\u0006\u0002#IA\u00111\u0005A\u0007\u0002\u0013!)1D\u0001a\u00019\u0005\tr-\u001a;K_\nLEm\u001d$pe\u001e\u0013x.\u001e9\u0015\u0005\u001dj\u0003c\u0001\f)U%\u0011\u0011f\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003--J!\u0001L\f\u0003\u0007%sG\u000fC\u0003/\u0007\u0001\u0007q&\u0001\u0005k_\n<%o\\;q!\t\u0001tG\u0004\u00022kA\u0011!gF\u0007\u0002g)\u0011AgE\u0001\u0007yI|w\u000e\u001e \n\u0005Y:\u0012A\u0002)sK\u0012,g-\u0003\u00029s\t11\u000b\u001e:j]\u001eT!AN\f\u0002#\u001d,G/Q2uSZ,7\u000b^1hK&#7\u000fF\u0001(\u0003=9W\r^!di&4XMS8c\u0013\u0012\u001c\u0018AC4fi*{'-\u00138g_R\u0011qH\u0011\t\u0003;\u0001K!!Q\u0007\u0003\u0019M\u0003\u0018M]6K_\nLeNZ8\t\u000b\r3\u0001\u0019\u0001\u0016\u0002\u000b)|'-\u00133\u0002\u0019\u001d,Go\u0015;bO\u0016LeNZ8\u0015\u0005\u0019K\u0005CA\u000fH\u0013\tAUB\u0001\bTa\u0006\u00148n\u0015;bO\u0016LeNZ8\t\u000b);\u0001\u0019\u0001\u0016\u0002\u000fM$\u0018mZ3JI\u0002"
)
public class JavaSparkStatusTracker {
   private final SparkContext sc;

   public int[] getJobIdsForGroup(final String jobGroup) {
      return this.sc.statusTracker().getJobIdsForGroup(jobGroup);
   }

   public int[] getActiveStageIds() {
      return this.sc.statusTracker().getActiveStageIds();
   }

   public int[] getActiveJobIds() {
      return this.sc.statusTracker().getActiveJobIds();
   }

   public SparkJobInfo getJobInfo(final int jobId) {
      return (SparkJobInfo)this.sc.statusTracker().getJobInfo(jobId).orNull(.MODULE$.refl());
   }

   public SparkStageInfo getStageInfo(final int stageId) {
      return (SparkStageInfo)this.sc.statusTracker().getStageInfo(stageId).orNull(.MODULE$.refl());
   }

   public JavaSparkStatusTracker(final SparkContext sc) {
      this.sc = sc;
   }
}
