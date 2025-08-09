package org.apache.spark.scheduler;

import java.io.Serializable;
import javax.annotation.Nullable;
import org.apache.spark.TaskEndReason;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.executor.TaskMetrics;
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
   bytes = "\u0006\u0005\tEa\u0001\u0002\u0015*\u0001JB\u0001\u0002\u0014\u0001\u0003\u0016\u0004%\t!\u0014\u0005\t#\u0002\u0011\t\u0012)A\u0005\u001d\"A!\u000b\u0001BK\u0002\u0013\u0005Q\n\u0003\u0005T\u0001\tE\t\u0015!\u0003O\u0011!!\u0006A!f\u0001\n\u0003)\u0006\u0002\u00030\u0001\u0005#\u0005\u000b\u0011\u0002,\t\u0011}\u0003!Q3A\u0005\u0002\u0001D\u0001\"\u001a\u0001\u0003\u0012\u0003\u0006I!\u0019\u0005\tM\u0002\u0011)\u001a!C\u0001O\"A1\u000e\u0001B\tB\u0003%\u0001\u000e\u0003\u0005m\u0001\tU\r\u0011\"\u0001n\u0011!!\bA!E!\u0002\u0013q\u0007\u0002C;\u0001\u0005+\u0007I\u0011\u0001<\t\u0011i\u0004!\u0011#Q\u0001\n]DQa\u001f\u0001\u0005\u0002qD\u0011\"!\b\u0001\u0003\u0003%\t!a\b\t\u0013\u0005=\u0002!%A\u0005\u0002\u0005E\u0002\"CA#\u0001E\u0005I\u0011AA\u0019\u0011%\t9\u0005AI\u0001\n\u0003\tI\u0005C\u0005\u0002N\u0001\t\n\u0011\"\u0001\u0002P!I\u00111\u000b\u0001\u0012\u0002\u0013\u0005\u0011Q\u000b\u0005\n\u00033\u0002\u0011\u0013!C\u0001\u00037B\u0011\"a\u0018\u0001#\u0003%\t!!\u0019\t\u0013\u0005\u0015\u0004!!A\u0005B\u0005\u001d\u0004\u0002CA<\u0001\u0005\u0005I\u0011A'\t\u0013\u0005e\u0004!!A\u0005\u0002\u0005m\u0004\"CAD\u0001\u0005\u0005I\u0011IAE\u0011%\t9\nAA\u0001\n\u0003\tI\nC\u0005\u0002$\u0002\t\t\u0011\"\u0011\u0002&\"I\u0011\u0011\u0016\u0001\u0002\u0002\u0013\u0005\u00131\u0016\u0005\n\u0003[\u0003\u0011\u0011!C!\u0003_C\u0011\"!-\u0001\u0003\u0003%\t%a-\b\u0013\u0005\r\u0017&!A\t\u0002\u0005\u0015g\u0001\u0003\u0015*\u0003\u0003E\t!a2\t\rm\u0014C\u0011AAp\u0011%\tiKIA\u0001\n\u000b\ny\u000bC\u0005\u0002b\n\n\t\u0011\"!\u0002d\"I\u00111\u001f\u0012\u0002\u0002\u0013\u0005\u0015Q\u001f\u0005\n\u0005\u000f\u0011\u0013\u0011!C\u0005\u0005\u0013\u0011Ac\u00159be.d\u0015n\u001d;f]\u0016\u0014H+Y:l\u000b:$'B\u0001\u0016,\u0003%\u00198\r[3ek2,'O\u0003\u0002-[\u0005)1\u000f]1sW*\u0011afL\u0001\u0007CB\f7\r[3\u000b\u0003A\n1a\u001c:h\u0007\u0001\u0019R\u0001A\u001a:{\u0001\u0003\"\u0001N\u001c\u000e\u0003UR\u0011AN\u0001\u0006g\u000e\fG.Y\u0005\u0003qU\u0012a!\u00118z%\u00164\u0007C\u0001\u001e<\u001b\u0005I\u0013B\u0001\u001f*\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005Qr\u0014BA 6\u0005\u001d\u0001&o\u001c3vGR\u0004\"!Q%\u000f\u0005\t;eBA\"G\u001b\u0005!%BA#2\u0003\u0019a$o\\8u}%\ta'\u0003\u0002Ik\u00059\u0001/Y2lC\u001e,\u0017B\u0001&L\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tAU'A\u0004ti\u0006<W-\u00133\u0016\u00039\u0003\"\u0001N(\n\u0005A+$aA%oi\u0006A1\u000f^1hK&#\u0007%\u0001\bti\u0006<W-\u0011;uK6\u0004H/\u00133\u0002\u001fM$\u0018mZ3BiR,W\u000e\u001d;JI\u0002\n\u0001\u0002^1tWRK\b/Z\u000b\u0002-B\u0011qk\u0017\b\u00031f\u0003\"aQ\u001b\n\u0005i+\u0014A\u0002)sK\u0012,g-\u0003\u0002];\n11\u000b\u001e:j]\u001eT!AW\u001b\u0002\u0013Q\f7o\u001b+za\u0016\u0004\u0013A\u0002:fCN|g.F\u0001b!\t\u00117-D\u0001,\u0013\t!7FA\u0007UCN\\WI\u001c3SK\u0006\u001cxN\\\u0001\be\u0016\f7o\u001c8!\u0003!!\u0018m]6J]\u001a|W#\u00015\u0011\u0005iJ\u0017B\u00016*\u0005!!\u0016m]6J]\u001a|\u0017!\u0003;bg.LeNZ8!\u0003M!\u0018m]6Fq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t+\u0005q\u0007CA8s\u001b\u0005\u0001(BA9,\u0003!)\u00070Z2vi>\u0014\u0018BA:q\u0005=)\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001c\u0018\u0001\u0006;bg.,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001c\b%A\u0006uCN\\W*\u001a;sS\u000e\u001cX#A<\u0011\u0005=D\u0018BA=q\u0005-!\u0016m]6NKR\u0014\u0018nY:\u0002\u0019Q\f7o['fiJL7m\u001d\u0011\u0002\rqJg.\u001b;?)5ihp`A\u0001\u0003\u0007\t)!a\u0002\u0002\nA\u0011!\b\u0001\u0005\u0006\u0019>\u0001\rA\u0014\u0005\u0006%>\u0001\rA\u0014\u0005\u0006)>\u0001\rA\u0016\u0005\u0006?>\u0001\r!\u0019\u0005\u0006M>\u0001\r\u0001\u001b\u0005\u0006Y>\u0001\rA\u001c\u0005\u0006k>\u0001\ra\u001e\u0015\u0005\u0003\u0013\ti\u0001\u0005\u0003\u0002\u0010\u0005eQBAA\t\u0015\u0011\t\u0019\"!\u0006\u0002\u0015\u0005tgn\u001c;bi&|gN\u0003\u0002\u0002\u0018\u0005)!.\u0019<bq&!\u00111DA\t\u0005!qU\u000f\u001c7bE2,\u0017\u0001B2paf$r\"`A\u0011\u0003G\t)#a\n\u0002*\u0005-\u0012Q\u0006\u0005\b\u0019B\u0001\n\u00111\u0001O\u0011\u001d\u0011\u0006\u0003%AA\u00029Cq\u0001\u0016\t\u0011\u0002\u0003\u0007a\u000bC\u0004`!A\u0005\t\u0019A1\t\u000f\u0019\u0004\u0002\u0013!a\u0001Q\"9A\u000e\u0005I\u0001\u0002\u0004q\u0007bB;\u0011!\u0003\u0005\ra^\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019DK\u0002O\u0003kY#!a\u000e\u0011\t\u0005e\u0012\u0011I\u0007\u0003\u0003wQA!!\u0010\u0002@\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003')\u0014\u0002BA\"\u0003w\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002L)\u001aa+!\u000e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011\u0011\u000b\u0016\u0004C\u0006U\u0012AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0003/R3\u0001[A\u001b\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY*\"!!\u0018+\u00079\f)$\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\u0005\r$fA<\u00026\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u001b\u0011\t\u0005-\u0014QO\u0007\u0003\u0003[RA!a\u001c\u0002r\u0005!A.\u00198h\u0015\t\t\u0019(\u0001\u0003kCZ\f\u0017b\u0001/\u0002n\u0005a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA?\u0003\u0007\u00032\u0001NA@\u0013\r\t\t)\u000e\u0002\u0004\u0003:L\b\u0002CAC5\u0005\u0005\t\u0019\u0001(\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\t\u0005\u0004\u0002\u000e\u0006M\u0015QP\u0007\u0003\u0003\u001fS1!!%6\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003+\u000byI\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAN\u0003C\u00032\u0001NAO\u0013\r\ty*\u000e\u0002\b\u0005>|G.Z1o\u0011%\t)\tHA\u0001\u0002\u0004\ti(\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA5\u0003OC\u0001\"!\"\u001e\u0003\u0003\u0005\rAT\u0001\tQ\u0006\u001c\bnQ8eKR\ta*\u0001\u0005u_N#(/\u001b8h)\t\tI'\u0001\u0004fcV\fGn\u001d\u000b\u0005\u00037\u000b)\fC\u0005\u0002\u0006\u0002\n\t\u00111\u0001\u0002~!\u001a\u0001!!/\u0011\t\u0005m\u0016qX\u0007\u0003\u0003{S1!a\u0005,\u0013\u0011\t\t-!0\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002)M\u0003\u0018M]6MSN$XM\\3s)\u0006\u001c8.\u00128e!\tQ$eE\u0003#\u0003\u0013\f)\u000e\u0005\u0007\u0002L\u0006EgJ\u0014,bQ:<X0\u0004\u0002\u0002N*\u0019\u0011qZ\u001b\u0002\u000fI,h\u000e^5nK&!\u00111[Ag\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\u000e\t\u0005\u0003/\fi.\u0004\u0002\u0002Z*!\u00111\\A9\u0003\tIw.C\u0002K\u00033$\"!!2\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u001fu\f)/a:\u0002j\u0006-\u0018Q^Ax\u0003cDQ\u0001T\u0013A\u00029CQAU\u0013A\u00029CQ\u0001V\u0013A\u0002YCQaX\u0013A\u0002\u0005DQAZ\u0013A\u0002!DQ\u0001\\\u0013A\u00029DQ!^\u0013A\u0002]\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002x\n\r\u0001#\u0002\u001b\u0002z\u0006u\u0018bAA~k\t1q\n\u001d;j_:\u0004\"\u0002NA\u0000\u001d:3\u0016\r\u001b8x\u0013\r\u0011\t!\u000e\u0002\u0007)V\u0004H.Z\u001c\t\u0011\t\u0015a%!AA\u0002u\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011Y\u0001\u0005\u0003\u0002l\t5\u0011\u0002\u0002B\b\u0003[\u0012aa\u00142kK\u000e$\b"
)
public class SparkListenerTaskEnd implements SparkListenerEvent, Product, Serializable {
   private final int stageId;
   private final int stageAttemptId;
   private final String taskType;
   private final TaskEndReason reason;
   private final TaskInfo taskInfo;
   private final ExecutorMetrics taskExecutorMetrics;
   private final TaskMetrics taskMetrics;

   public static Option unapply(final SparkListenerTaskEnd x$0) {
      return SparkListenerTaskEnd$.MODULE$.unapply(x$0);
   }

   public static SparkListenerTaskEnd apply(final int stageId, final int stageAttemptId, final String taskType, final TaskEndReason reason, final TaskInfo taskInfo, final ExecutorMetrics taskExecutorMetrics, final TaskMetrics taskMetrics) {
      return SparkListenerTaskEnd$.MODULE$.apply(stageId, stageAttemptId, taskType, reason, taskInfo, taskExecutorMetrics, taskMetrics);
   }

   public static Function1 tupled() {
      return SparkListenerTaskEnd$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerTaskEnd$.MODULE$.curried();
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

   public String taskType() {
      return this.taskType;
   }

   public TaskEndReason reason() {
      return this.reason;
   }

   public TaskInfo taskInfo() {
      return this.taskInfo;
   }

   public ExecutorMetrics taskExecutorMetrics() {
      return this.taskExecutorMetrics;
   }

   public TaskMetrics taskMetrics() {
      return this.taskMetrics;
   }

   public SparkListenerTaskEnd copy(final int stageId, final int stageAttemptId, final String taskType, final TaskEndReason reason, final TaskInfo taskInfo, final ExecutorMetrics taskExecutorMetrics, final TaskMetrics taskMetrics) {
      return new SparkListenerTaskEnd(stageId, stageAttemptId, taskType, reason, taskInfo, taskExecutorMetrics, taskMetrics);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public int copy$default$2() {
      return this.stageAttemptId();
   }

   public String copy$default$3() {
      return this.taskType();
   }

   public TaskEndReason copy$default$4() {
      return this.reason();
   }

   public TaskInfo copy$default$5() {
      return this.taskInfo();
   }

   public ExecutorMetrics copy$default$6() {
      return this.taskExecutorMetrics();
   }

   public TaskMetrics copy$default$7() {
      return this.taskMetrics();
   }

   public String productPrefix() {
      return "SparkListenerTaskEnd";
   }

   public int productArity() {
      return 7;
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
            return this.taskType();
         }
         case 3 -> {
            return this.reason();
         }
         case 4 -> {
            return this.taskInfo();
         }
         case 5 -> {
            return this.taskExecutorMetrics();
         }
         case 6 -> {
            return this.taskMetrics();
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
      return x$1 instanceof SparkListenerTaskEnd;
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
            return "taskType";
         }
         case 3 -> {
            return "reason";
         }
         case 4 -> {
            return "taskInfo";
         }
         case 5 -> {
            return "taskExecutorMetrics";
         }
         case 6 -> {
            return "taskMetrics";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.taskType()));
      var1 = Statics.mix(var1, Statics.anyHash(this.reason()));
      var1 = Statics.mix(var1, Statics.anyHash(this.taskInfo()));
      var1 = Statics.mix(var1, Statics.anyHash(this.taskExecutorMetrics()));
      var1 = Statics.mix(var1, Statics.anyHash(this.taskMetrics()));
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label87: {
            if (x$1 instanceof SparkListenerTaskEnd) {
               SparkListenerTaskEnd var4 = (SparkListenerTaskEnd)x$1;
               if (this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId()) {
                  label80: {
                     String var10000 = this.taskType();
                     String var5 = var4.taskType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label80;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label80;
                     }

                     TaskEndReason var10 = this.reason();
                     TaskEndReason var6 = var4.reason();
                     if (var10 == null) {
                        if (var6 != null) {
                           break label80;
                        }
                     } else if (!var10.equals(var6)) {
                        break label80;
                     }

                     TaskInfo var11 = this.taskInfo();
                     TaskInfo var7 = var4.taskInfo();
                     if (var11 == null) {
                        if (var7 != null) {
                           break label80;
                        }
                     } else if (!var11.equals(var7)) {
                        break label80;
                     }

                     ExecutorMetrics var12 = this.taskExecutorMetrics();
                     ExecutorMetrics var8 = var4.taskExecutorMetrics();
                     if (var12 == null) {
                        if (var8 != null) {
                           break label80;
                        }
                     } else if (!var12.equals(var8)) {
                        break label80;
                     }

                     TaskMetrics var13 = this.taskMetrics();
                     TaskMetrics var9 = var4.taskMetrics();
                     if (var13 == null) {
                        if (var9 != null) {
                           break label80;
                        }
                     } else if (!var13.equals(var9)) {
                        break label80;
                     }

                     if (var4.canEqual(this)) {
                        break label87;
                     }
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public SparkListenerTaskEnd(final int stageId, final int stageAttemptId, final String taskType, final TaskEndReason reason, final TaskInfo taskInfo, final ExecutorMetrics taskExecutorMetrics, @Nullable final TaskMetrics taskMetrics) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.taskType = taskType;
      this.reason = reason;
      this.taskInfo = taskInfo;
      this.taskExecutorMetrics = taskExecutorMetrics;
      this.taskMetrics = taskMetrics;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
