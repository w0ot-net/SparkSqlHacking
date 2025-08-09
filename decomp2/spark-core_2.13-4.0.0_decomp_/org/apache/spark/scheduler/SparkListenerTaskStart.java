package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005ed\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005H\u0001\tE\t\u0015!\u0003C\u0011!A\u0005A!f\u0001\n\u0003I\u0005\u0002C'\u0001\u0005#\u0005\u000b\u0011\u0002&\t\u000b9\u0003A\u0011A(\t\u000fQ\u0003\u0011\u0011!C\u0001+\"9\u0011\fAI\u0001\n\u0003Q\u0006bB3\u0001#\u0003%\tA\u0017\u0005\bM\u0002\t\n\u0011\"\u0001h\u0011\u001dI\u0007!!A\u0005B)Dqa\u001d\u0001\u0002\u0002\u0013\u0005\u0011\tC\u0004u\u0001\u0005\u0005I\u0011A;\t\u000fm\u0004\u0011\u0011!C!y\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0002\u0005\n\u0003'\u0001\u0011\u0011!C!\u0003+A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\t\u0013\u0005u\u0001!!A\u0005B\u0005}\u0001\"CA\u0011\u0001\u0005\u0005I\u0011IA\u0012\u000f%\t\u0019$HA\u0001\u0012\u0003\t)D\u0002\u0005\u001d;\u0005\u0005\t\u0012AA\u001c\u0011\u0019qe\u0003\"\u0001\u0002P!I\u0011Q\u0004\f\u0002\u0002\u0013\u0015\u0013q\u0004\u0005\n\u0003#2\u0012\u0011!CA\u0003'B\u0011\"a\u0017\u0017\u0003\u0003%\t)!\u0018\t\u0013\u0005=d#!A\u0005\n\u0005E$AF*qCJ\\G*[:uK:,'\u000fV1tWN#\u0018M\u001d;\u000b\u0005yy\u0012!C:dQ\u0016$W\u000f\\3s\u0015\t\u0001\u0013%A\u0003ta\u0006\u00148N\u0003\u0002#G\u00051\u0011\r]1dQ\u0016T\u0011\u0001J\u0001\u0004_J<7\u0001A\n\u0006\u0001\u001dj\u0013\u0007\u000e\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u00059zS\"A\u000f\n\u0005Aj\"AE*qCJ\\G*[:uK:,'/\u0012<f]R\u0004\"\u0001\u000b\u001a\n\u0005MJ#a\u0002)s_\u0012,8\r\u001e\t\u0003kur!AN\u001e\u000f\u0005]RT\"\u0001\u001d\u000b\u0005e*\u0013A\u0002\u001fs_>$h(C\u0001+\u0013\ta\u0014&A\u0004qC\u000e\\\u0017mZ3\n\u0005yz$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001f*\u0003\u001d\u0019H/Y4f\u0013\u0012,\u0012A\u0011\t\u0003Q\rK!\u0001R\u0015\u0003\u0007%sG/\u0001\u0005ti\u0006<W-\u00133!\u00039\u0019H/Y4f\u0003R$X-\u001c9u\u0013\u0012\fqb\u001d;bO\u0016\fE\u000f^3naRLE\rI\u0001\ti\u0006\u001c8.\u00138g_V\t!\n\u0005\u0002/\u0017&\u0011A*\b\u0002\t)\u0006\u001c8.\u00138g_\u0006IA/Y:l\u0013:4w\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tA\u000b&k\u0015\t\u0003]\u0001AQ\u0001Q\u0004A\u0002\tCQAR\u0004A\u0002\tCQ\u0001S\u0004A\u0002)\u000bAaY8qsR!\u0001KV,Y\u0011\u001d\u0001\u0005\u0002%AA\u0002\tCqA\u0012\u0005\u0011\u0002\u0003\u0007!\tC\u0004I\u0011A\u0005\t\u0019\u0001&\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t1L\u000b\u0002C9.\nQ\f\u0005\u0002_G6\tqL\u0003\u0002aC\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003E&\n!\"\u00198o_R\fG/[8o\u0013\t!wLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003!T#A\u0013/\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Y\u0007C\u00017r\u001b\u0005i'B\u00018p\u0003\u0011a\u0017M\\4\u000b\u0003A\fAA[1wC&\u0011!/\u001c\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011a/\u001f\t\u0003Q]L!\u0001_\u0015\u0003\u0007\u0005s\u0017\u0010C\u0004{\u001d\u0005\u0005\t\u0019\u0001\"\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005i\b\u0003\u0002@\u0002\u0004Yl\u0011a \u0006\u0004\u0003\u0003I\u0013AC2pY2,7\r^5p]&\u0019\u0011QA@\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0017\t\t\u0002E\u0002)\u0003\u001bI1!a\u0004*\u0005\u001d\u0011un\u001c7fC:DqA\u001f\t\u0002\u0002\u0003\u0007a/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA6\u0002\u0018!9!0EA\u0001\u0002\u0004\u0011\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\t\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002W\u00061Q-];bYN$B!a\u0003\u0002&!9!\u0010FA\u0001\u0002\u00041\bf\u0001\u0001\u0002*A!\u00111FA\u0018\u001b\t\tiC\u0003\u0002c?%!\u0011\u0011GA\u0017\u00051!UM^3m_B,'/\u00119j\u0003Y\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8feR\u000b7o[*uCJ$\bC\u0001\u0018\u0017'\u00151\u0012\u0011HA#!!\tY$!\u0011C\u0005*\u0003VBAA\u001f\u0015\r\ty$K\u0001\beVtG/[7f\u0013\u0011\t\u0019%!\u0010\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002H\u00055SBAA%\u0015\r\tYe\\\u0001\u0003S>L1APA%)\t\t)$A\u0003baBd\u0017\u0010F\u0004Q\u0003+\n9&!\u0017\t\u000b\u0001K\u0002\u0019\u0001\"\t\u000b\u0019K\u0002\u0019\u0001\"\t\u000b!K\u0002\u0019\u0001&\u0002\u000fUt\u0017\r\u001d9msR!\u0011qLA6!\u0015A\u0013\u0011MA3\u0013\r\t\u0019'\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r!\n9G\u0011\"K\u0013\r\tI'\u000b\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u00055$$!AA\u0002A\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\bE\u0002m\u0003kJ1!a\u001en\u0005\u0019y%M[3di\u0002"
)
public class SparkListenerTaskStart implements SparkListenerEvent, Product, Serializable {
   private final int stageId;
   private final int stageAttemptId;
   private final TaskInfo taskInfo;

   public static Option unapply(final SparkListenerTaskStart x$0) {
      return SparkListenerTaskStart$.MODULE$.unapply(x$0);
   }

   public static SparkListenerTaskStart apply(final int stageId, final int stageAttemptId, final TaskInfo taskInfo) {
      return SparkListenerTaskStart$.MODULE$.apply(stageId, stageAttemptId, taskInfo);
   }

   public static Function1 tupled() {
      return SparkListenerTaskStart$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerTaskStart$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public TaskInfo taskInfo() {
      return this.taskInfo;
   }

   public SparkListenerTaskStart copy(final int stageId, final int stageAttemptId, final TaskInfo taskInfo) {
      return new SparkListenerTaskStart(stageId, stageAttemptId, taskInfo);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public int copy$default$2() {
      return this.stageAttemptId();
   }

   public TaskInfo copy$default$3() {
      return this.taskInfo();
   }

   public String productPrefix() {
      return "SparkListenerTaskStart";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.stageAttemptId());
         }
         case 2 -> {
            return this.taskInfo();
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
      return x$1 instanceof SparkListenerTaskStart;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stageId";
         }
         case 1 -> {
            return "stageAttemptId";
         }
         case 2 -> {
            return "taskInfo";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, this.stageAttemptId());
      var1 = Statics.mix(var1, Statics.anyHash(this.taskInfo()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof SparkListenerTaskStart) {
               SparkListenerTaskStart var4 = (SparkListenerTaskStart)x$1;
               if (this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId()) {
                  label48: {
                     TaskInfo var10000 = this.taskInfo();
                     TaskInfo var5 = var4.taskInfo();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public SparkListenerTaskStart(final int stageId, final int stageAttemptId, final TaskInfo taskInfo) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.taskInfo = taskInfo;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
