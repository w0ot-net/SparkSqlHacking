package org.apache.spark.status;

import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2Qa\u0002\u0005\u0001\u0015AA\u0001b\u0006\u0001\u0003\u0006\u0004%\t!\u0007\u0005\t;\u0001\u0011\t\u0011)A\u00055!Aa\u0004\u0001BC\u0002\u0013\u0005\u0011\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001b\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u00151\u0003\u0001\"\u0001(\u0005)\t\u0005\u000f]*v[6\f'/\u001f\u0006\u0003\u0013)\taa\u001d;biV\u001c(BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0014\u0005\u0001\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\tok6\u001cu.\u001c9mKR,GMS8cg\u000e\u0001Q#\u0001\u000e\u0011\u0005IY\u0012B\u0001\u000f\u0014\u0005\rIe\u000e^\u0001\u0012]Vl7i\\7qY\u0016$X\r\u001a&pEN\u0004\u0013A\u00058v[\u000e{W\u000e\u001d7fi\u0016$7\u000b^1hKN\f1C\\;n\u0007>l\u0007\u000f\\3uK\u0012\u001cF/Y4fg\u0002\na\u0001P5oSRtDc\u0001\u0012%KA\u00111\u0005A\u0007\u0002\u0011!)q#\u0002a\u00015!)a$\u0002a\u00015\u0005\u0011\u0011\u000eZ\u000b\u0002QA\u0011\u0011\u0006\r\b\u0003U9\u0002\"aK\n\u000e\u00031R!!\f\r\u0002\rq\u0012xn\u001c;?\u0013\ty3#\u0001\u0004Qe\u0016$WMZ\u0005\u0003cI\u0012aa\u0015;sS:<'BA\u0018\u0014Q\t1A\u0007\u0005\u00026u5\taG\u0003\u00028q\u000591N^:u_J,'BA\u001d\u000b\u0003\u0011)H/\u001b7\n\u0005m2$aB&W\u0013:$W\r\u001f"
)
public class AppSummary {
   private final int numCompletedJobs;
   private final int numCompletedStages;

   public int numCompletedJobs() {
      return this.numCompletedJobs;
   }

   public int numCompletedStages() {
      return this.numCompletedStages;
   }

   @KVIndex
   public String id() {
      return AppSummary.class.getName();
   }

   public AppSummary(final int numCompletedJobs, final int numCompletedStages) {
      this.numCompletedJobs = numCompletedJobs;
      this.numCompletedStages = numCompletedStages;
   }
}
