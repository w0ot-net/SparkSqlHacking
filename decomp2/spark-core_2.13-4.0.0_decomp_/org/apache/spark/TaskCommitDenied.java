package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re\u0001\u0002\u0010 \u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005H\u0001\tE\t\u0015!\u0003C\u0011!A\u0005A!f\u0001\n\u0003\t\u0005\u0002C%\u0001\u0005#\u0005\u000b\u0011\u0002\"\t\u000b)\u0003A\u0011A&\t\u000bA\u0003A\u0011I)\t\u000bi\u0003A\u0011I.\t\u000f}\u0003\u0011\u0011!C\u0001A\"9A\rAI\u0001\n\u0003)\u0007b\u00029\u0001#\u0003%\t!\u001a\u0005\bc\u0002\t\n\u0011\"\u0001f\u0011\u001d\u0011\b!!A\u0005BMDqa\u001f\u0001\u0002\u0002\u0013\u0005\u0011\tC\u0004}\u0001\u0005\u0005I\u0011A?\t\u0013\u0005\u001d\u0001!!A\u0005B\u0005%\u0001\"CA\f\u0001\u0005\u0005I\u0011AA\r\u0011%\ti\u0002AA\u0001\n\u0003\ny\u0002C\u0005\u0002$\u0001\t\t\u0011\"\u0011\u0002&!I\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0006\u0005\n\u0003W\u0001\u0011\u0011!C!\u0003[9\u0011\"!\u0010 \u0003\u0003E\t!a\u0010\u0007\u0011yy\u0012\u0011!E\u0001\u0003\u0003BaA\u0013\r\u0005\u0002\u0005e\u0003\"CA\u00141\u0005\u0005IQIA\u0015\u0011%\tY\u0006GA\u0001\n\u0003\u000bi\u0006C\u0005\u0002fa\t\t\u0011\"!\u0002h!I\u0011\u0011\u0010\r\u0002\u0002\u0013%\u00111\u0010\u0002\u0011)\u0006\u001c8nQ8n[&$H)\u001a8jK\u0012T!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\u0002\u0001'\u0015\u0001q%L\u00195!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u0019\te.\u001f*fMB\u0011afL\u0007\u0002?%\u0011\u0001g\b\u0002\u0011)\u0006\u001c8NR1jY\u0016$'+Z1t_:\u0004\"\u0001\u000b\u001a\n\u0005MJ#a\u0002)s_\u0012,8\r\u001e\t\u0003kur!AN\u001e\u000f\u0005]RT\"\u0001\u001d\u000b\u0005e*\u0013A\u0002\u001fs_>$h(C\u0001+\u0013\ta\u0014&A\u0004qC\u000e\\\u0017mZ3\n\u0005yz$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001f*\u0003\u0015QwNY%E+\u0005\u0011\u0005C\u0001\u0015D\u0013\t!\u0015FA\u0002J]R\faA[8c\u0013\u0012\u0003\u0013a\u00039beRLG/[8o\u0013\u0012\u000bA\u0002]1si&$\u0018n\u001c8J\t\u0002\nQ\"\u0019;uK6\u0004HOT;nE\u0016\u0014\u0018AD1ui\u0016l\u0007\u000f\u001e(v[\n,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t1kej\u0014\t\u0003]\u0001AQ\u0001Q\u0004A\u0002\tCQAR\u0004A\u0002\tCQ\u0001S\u0004A\u0002\t\u000bQ\u0002^8FeJ|'o\u0015;sS:<W#\u0001*\u0011\u0005M;fB\u0001+V!\t9\u0014&\u0003\u0002WS\u00051\u0001K]3eK\u001aL!\u0001W-\u0003\rM#(/\u001b8h\u0015\t1\u0016&\u0001\rd_VtG\u000fV8xCJ$7\u000fV1tW\u001a\u000b\u0017\u000e\\;sKN,\u0012\u0001\u0018\t\u0003QuK!AX\u0015\u0003\u000f\t{w\u000e\\3b]\u0006!1m\u001c9z)\u0011a\u0015MY2\t\u000f\u0001S\u0001\u0013!a\u0001\u0005\"9aI\u0003I\u0001\u0002\u0004\u0011\u0005b\u0002%\u000b!\u0003\u0005\rAQ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00051'F\u0001\"hW\u0005A\u0007CA5o\u001b\u0005Q'BA6m\u0003%)hn\u00195fG.,GM\u0003\u0002nS\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005=T'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003Q\u0004\"!\u001e>\u000e\u0003YT!a\u001e=\u0002\t1\fgn\u001a\u0006\u0002s\u0006!!.\u0019<b\u0013\tAf/\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007y\f\u0019\u0001\u0005\u0002)\u007f&\u0019\u0011\u0011A\u0015\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\u0006A\t\t\u00111\u0001C\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u0002\t\u0006\u0003\u001b\t\u0019B`\u0007\u0003\u0003\u001fQ1!!\u0005*\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003+\tyA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001/\u0002\u001c!A\u0011Q\u0001\n\u0002\u0002\u0003\u0007a0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001;\u0002\"!A\u0011QA\n\u0002\u0002\u0003\u0007!)\u0001\u0005iCND7i\u001c3f)\u0005\u0011\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Q\fa!Z9vC2\u001cHc\u0001/\u00020!A\u0011Q\u0001\f\u0002\u0002\u0003\u0007a\u0010K\u0002\u0001\u0003g\u0001B!!\u000e\u0002:5\u0011\u0011q\u0007\u0006\u0003[~IA!a\u000f\u00028\taA)\u001a<fY>\u0004XM]!qS\u0006\u0001B+Y:l\u0007>lW.\u001b;EK:LW\r\u001a\t\u0003]a\u0019R\u0001GA\"\u0003\u001f\u0002\u0002\"!\u0012\u0002L\t\u0013%\tT\u0007\u0003\u0003\u000fR1!!\u0013*\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0014\u0002H\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005E\u0013qK\u0007\u0003\u0003'R1!!\u0016y\u0003\tIw.C\u0002?\u0003'\"\"!a\u0010\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000f1\u000by&!\u0019\u0002d!)\u0001i\u0007a\u0001\u0005\")ai\u0007a\u0001\u0005\")\u0001j\u0007a\u0001\u0005\u00069QO\\1qa2LH\u0003BA5\u0003k\u0002R\u0001KA6\u0003_J1!!\u001c*\u0005\u0019y\u0005\u000f^5p]B1\u0001&!\u001dC\u0005\nK1!a\u001d*\u0005\u0019!V\u000f\u001d7fg!A\u0011q\u000f\u000f\u0002\u0002\u0003\u0007A*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!! \u0011\u0007U\fy(C\u0002\u0002\u0002Z\u0014aa\u00142kK\u000e$\b"
)
public class TaskCommitDenied implements TaskFailedReason, Product, Serializable {
   private final int jobID;
   private final int partitionID;
   private final int attemptNumber;

   public static Option unapply(final TaskCommitDenied x$0) {
      return TaskCommitDenied$.MODULE$.unapply(x$0);
   }

   public static TaskCommitDenied apply(final int jobID, final int partitionID, final int attemptNumber) {
      return TaskCommitDenied$.MODULE$.apply(jobID, partitionID, attemptNumber);
   }

   public static Function1 tupled() {
      return TaskCommitDenied$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return TaskCommitDenied$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int jobID() {
      return this.jobID;
   }

   public int partitionID() {
      return this.partitionID;
   }

   public int attemptNumber() {
      return this.attemptNumber;
   }

   public String toErrorString() {
      int var10000 = this.jobID();
      return "TaskCommitDenied (Driver denied task commit) for job: " + var10000 + ", partition: " + this.partitionID() + ", attemptNumber: " + this.attemptNumber();
   }

   public boolean countTowardsTaskFailures() {
      return false;
   }

   public TaskCommitDenied copy(final int jobID, final int partitionID, final int attemptNumber) {
      return new TaskCommitDenied(jobID, partitionID, attemptNumber);
   }

   public int copy$default$1() {
      return this.jobID();
   }

   public int copy$default$2() {
      return this.partitionID();
   }

   public int copy$default$3() {
      return this.attemptNumber();
   }

   public String productPrefix() {
      return "TaskCommitDenied";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.jobID());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.partitionID());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.attemptNumber());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof TaskCommitDenied;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "jobID";
         }
         case 1 -> {
            return "partitionID";
         }
         case 2 -> {
            return "attemptNumber";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.jobID());
      var1 = Statics.mix(var1, this.partitionID());
      var1 = Statics.mix(var1, this.attemptNumber());
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof TaskCommitDenied) {
               TaskCommitDenied var4 = (TaskCommitDenied)x$1;
               if (this.jobID() == var4.jobID() && this.partitionID() == var4.partitionID() && this.attemptNumber() == var4.attemptNumber() && var4.canEqual(this)) {
                  break label40;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public TaskCommitDenied(final int jobID, final int partitionID, final int attemptNumber) {
      this.jobID = jobID;
      this.partitionID = partitionID;
      this.attemptNumber = attemptNumber;
      TaskFailedReason.$init$(this);
      Product.$init$(this);
   }
}
