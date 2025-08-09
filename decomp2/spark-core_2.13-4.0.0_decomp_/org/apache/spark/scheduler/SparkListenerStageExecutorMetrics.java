package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.executor.ExecutorMetrics;
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
   bytes = "\u0006\u0005\u0005\u0015f\u0001B\u0010!\u0001&B\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005\u000b\"Aa\n\u0001BK\u0002\u0013\u0005q\n\u0003\u0005T\u0001\tE\t\u0015!\u0003Q\u0011!!\u0006A!f\u0001\n\u0003y\u0005\u0002C+\u0001\u0005#\u0005\u000b\u0011\u0002)\t\u0011Y\u0003!Q3A\u0005\u0002]C\u0001B\u0018\u0001\u0003\u0012\u0003\u0006I\u0001\u0017\u0005\u0006?\u0002!\t\u0001\u0019\u0005\bM\u0002\t\t\u0011\"\u0001h\u0011\u001da\u0007!%A\u0005\u00025Dq\u0001\u001f\u0001\u0012\u0002\u0013\u0005\u0011\u0010C\u0004|\u0001E\u0005I\u0011A=\t\u000fq\u0004\u0011\u0013!C\u0001{\"Aq\u0010AA\u0001\n\u0003\n\t\u0001\u0003\u0005\u0002\u0012\u0001\t\t\u0011\"\u0001P\u0011%\t\u0019\u0002AA\u0001\n\u0003\t)\u0002C\u0005\u0002\"\u0001\t\t\u0011\"\u0011\u0002$!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003{\u0001\u0011\u0011!C!\u0003\u007fA\u0011\"a\u0011\u0001\u0003\u0003%\t%!\u0012\t\u0013\u0005\u001d\u0003!!A\u0005B\u0005%\u0003\"CA&\u0001\u0005\u0005I\u0011IA'\u000f%\ti\u0006IA\u0001\u0012\u0003\tyF\u0002\u0005 A\u0005\u0005\t\u0012AA1\u0011\u0019y\u0016\u0004\"\u0001\u0002z!I\u0011qI\r\u0002\u0002\u0013\u0015\u0013\u0011\n\u0005\n\u0003wJ\u0012\u0011!CA\u0003{B\u0011\"a\"\u001a\u0003\u0003%\t)!#\t\u0013\u0005m\u0015$!A\u0005\n\u0005u%!I*qCJ\\G*[:uK:,'o\u0015;bO\u0016,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001c(BA\u0011#\u0003%\u00198\r[3ek2,'O\u0003\u0002$I\u0005)1\u000f]1sW*\u0011QEJ\u0001\u0007CB\f7\r[3\u000b\u0003\u001d\n1a\u001c:h\u0007\u0001\u0019R\u0001\u0001\u00161i]\u0002\"a\u000b\u0018\u000e\u00031R\u0011!L\u0001\u0006g\u000e\fG.Y\u0005\u0003_1\u0012a!\u00118z%\u00164\u0007CA\u00193\u001b\u0005\u0001\u0013BA\u001a!\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005-*\u0014B\u0001\u001c-\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u000f!\u000f\u0005erdB\u0001\u001e>\u001b\u0005Y$B\u0001\u001f)\u0003\u0019a$o\\8u}%\tQ&\u0003\u0002@Y\u00059\u0001/Y2lC\u001e,\u0017BA!C\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tyD&\u0001\u0004fq\u0016\u001c\u0017\nZ\u000b\u0002\u000bB\u0011aI\u0013\b\u0003\u000f\"\u0003\"A\u000f\u0017\n\u0005%c\u0013A\u0002)sK\u0012,g-\u0003\u0002L\u0019\n11\u000b\u001e:j]\u001eT!!\u0013\u0017\u0002\u000f\u0015DXmY%eA\u000591\u000f^1hK&#W#\u0001)\u0011\u0005-\n\u0016B\u0001*-\u0005\rIe\u000e^\u0001\tgR\fw-Z%eA\u0005q1\u000f^1hK\u0006#H/Z7qi&#\u0017aD:uC\u001e,\u0017\t\u001e;f[B$\u0018\n\u001a\u0011\u0002\u001f\u0015DXmY;u_JlU\r\u001e:jGN,\u0012\u0001\u0017\t\u00033rk\u0011A\u0017\u0006\u00037\n\n\u0001\"\u001a=fGV$xN]\u0005\u0003;j\u0013q\"\u0012=fGV$xN]'fiJL7m]\u0001\u0011Kb,7-\u001e;pe6+GO]5dg\u0002\na\u0001P5oSRtD#B1cG\u0012,\u0007CA\u0019\u0001\u0011\u0015\u0019\u0015\u00021\u0001F\u0011\u0015q\u0015\u00021\u0001Q\u0011\u0015!\u0016\u00021\u0001Q\u0011\u00151\u0016\u00021\u0001Y\u0003\u0011\u0019w\u000e]=\u0015\u000b\u0005D\u0017N[6\t\u000f\rS\u0001\u0013!a\u0001\u000b\"9aJ\u0003I\u0001\u0002\u0004\u0001\u0006b\u0002+\u000b!\u0003\u0005\r\u0001\u0015\u0005\b-*\u0001\n\u00111\u0001Y\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001c\u0016\u0003\u000b>\\\u0013\u0001\u001d\t\u0003cZl\u0011A\u001d\u0006\u0003gR\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005Ud\u0013AC1o]>$\u0018\r^5p]&\u0011qO\u001d\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002u*\u0012\u0001k\\\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\u0012A \u0016\u00031>\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0002!\u0011\t)!a\u0004\u000e\u0005\u0005\u001d!\u0002BA\u0005\u0003\u0017\tA\u0001\\1oO*\u0011\u0011QB\u0001\u0005U\u00064\u0018-C\u0002L\u0003\u000f\tA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0018\u0005u\u0001cA\u0016\u0002\u001a%\u0019\u00111\u0004\u0017\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002 E\t\t\u00111\u0001Q\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u0005\t\u0007\u0003O\ti#a\u0006\u000e\u0005\u0005%\"bAA\u0016Y\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005=\u0012\u0011\u0006\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00026\u0005m\u0002cA\u0016\u00028%\u0019\u0011\u0011\b\u0017\u0003\u000f\t{w\u000e\\3b]\"I\u0011qD\n\u0002\u0002\u0003\u0007\u0011qC\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\u0004\u0005\u0005\u0003\u0002CA\u0010)\u0005\u0005\t\u0019\u0001)\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001U\u0001\ti>\u001cFO]5oOR\u0011\u00111A\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005U\u0012q\n\u0005\n\u0003?9\u0012\u0011!a\u0001\u0003/A3\u0001AA*!\u0011\t)&!\u0017\u000e\u0005\u0005]#BA;#\u0013\u0011\tY&a\u0016\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002CM\u0003\u0018M]6MSN$XM\\3s'R\fw-Z#yK\u000e,Ho\u001c:NKR\u0014\u0018nY:\u0011\u0005EJ2#B\r\u0002d\u0005=\u0004#CA3\u0003W*\u0005\u000b\u0015-b\u001b\t\t9GC\u0002\u0002j1\nqA];oi&lW-\u0003\u0003\u0002n\u0005\u001d$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oiA!\u0011\u0011OA<\u001b\t\t\u0019H\u0003\u0003\u0002v\u0005-\u0011AA5p\u0013\r\t\u00151\u000f\u000b\u0003\u0003?\nQ!\u00199qYf$\u0012\"YA@\u0003\u0003\u000b\u0019)!\"\t\u000b\rc\u0002\u0019A#\t\u000b9c\u0002\u0019\u0001)\t\u000bQc\u0002\u0019\u0001)\t\u000bYc\u0002\u0019\u0001-\u0002\u000fUt\u0017\r\u001d9msR!\u00111RAL!\u0015Y\u0013QRAI\u0013\r\ty\t\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f-\n\u0019*\u0012)Q1&\u0019\u0011Q\u0013\u0017\u0003\rQ+\b\u000f\\35\u0011!\tI*HA\u0001\u0002\u0004\t\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0014\t\u0005\u0003\u000b\t\t+\u0003\u0003\u0002$\u0006\u001d!AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerStageExecutorMetrics implements SparkListenerEvent, Product, Serializable {
   private final String execId;
   private final int stageId;
   private final int stageAttemptId;
   private final ExecutorMetrics executorMetrics;

   public static Option unapply(final SparkListenerStageExecutorMetrics x$0) {
      return SparkListenerStageExecutorMetrics$.MODULE$.unapply(x$0);
   }

   public static SparkListenerStageExecutorMetrics apply(final String execId, final int stageId, final int stageAttemptId, final ExecutorMetrics executorMetrics) {
      return SparkListenerStageExecutorMetrics$.MODULE$.apply(execId, stageId, stageAttemptId, executorMetrics);
   }

   public static Function1 tupled() {
      return SparkListenerStageExecutorMetrics$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerStageExecutorMetrics$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String execId() {
      return this.execId;
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public ExecutorMetrics executorMetrics() {
      return this.executorMetrics;
   }

   public SparkListenerStageExecutorMetrics copy(final String execId, final int stageId, final int stageAttemptId, final ExecutorMetrics executorMetrics) {
      return new SparkListenerStageExecutorMetrics(execId, stageId, stageAttemptId, executorMetrics);
   }

   public String copy$default$1() {
      return this.execId();
   }

   public int copy$default$2() {
      return this.stageId();
   }

   public int copy$default$3() {
      return this.stageAttemptId();
   }

   public ExecutorMetrics copy$default$4() {
      return this.executorMetrics();
   }

   public String productPrefix() {
      return "SparkListenerStageExecutorMetrics";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.execId();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.stageAttemptId());
         }
         case 3 -> {
            return this.executorMetrics();
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
      return x$1 instanceof SparkListenerStageExecutorMetrics;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "execId";
         }
         case 1 -> {
            return "stageId";
         }
         case 2 -> {
            return "stageAttemptId";
         }
         case 3 -> {
            return "executorMetrics";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.execId()));
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, this.stageAttemptId());
      var1 = Statics.mix(var1, Statics.anyHash(this.executorMetrics()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof SparkListenerStageExecutorMetrics) {
               SparkListenerStageExecutorMetrics var4 = (SparkListenerStageExecutorMetrics)x$1;
               if (this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId()) {
                  label56: {
                     String var10000 = this.execId();
                     String var5 = var4.execId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     ExecutorMetrics var7 = this.executorMetrics();
                     ExecutorMetrics var6 = var4.executorMetrics();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var7.equals(var6)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public SparkListenerStageExecutorMetrics(final String execId, final int stageId, final int stageAttemptId, final ExecutorMetrics executorMetrics) {
      this.execId = execId;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.executorMetrics = executorMetrics;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
