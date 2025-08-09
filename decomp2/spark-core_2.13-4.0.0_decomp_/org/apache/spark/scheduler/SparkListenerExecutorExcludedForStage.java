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
   bytes = "\u0006\u0005\u0005uf\u0001\u0002\u0012$\u00012B\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0011\"AA\n\u0001BK\u0002\u0013\u0005Q\n\u0003\u0005W\u0001\tE\t\u0015!\u0003O\u0011!9\u0006A!f\u0001\n\u0003A\u0006\u0002\u0003/\u0001\u0005#\u0005\u000b\u0011B-\t\u0011u\u0003!Q3A\u0005\u0002aC\u0001B\u0018\u0001\u0003\u0012\u0003\u0006I!\u0017\u0005\t?\u0002\u0011)\u001a!C\u00011\"A\u0001\r\u0001B\tB\u0003%\u0011\fC\u0003b\u0001\u0011\u0005!\rC\u0004j\u0001\u0005\u0005I\u0011\u00016\t\u000fA\u0004\u0011\u0013!C\u0001c\"9A\u0010AI\u0001\n\u0003i\b\u0002C@\u0001#\u0003%\t!!\u0001\t\u0013\u0005\u0015\u0001!%A\u0005\u0002\u0005\u0005\u0001\"CA\u0004\u0001E\u0005I\u0011AA\u0001\u0011%\tI\u0001AA\u0001\n\u0003\nY\u0001\u0003\u0005\u0002\u001c\u0001\t\t\u0011\"\u0001Y\u0011%\ti\u0002AA\u0001\n\u0003\ty\u0002C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u000f\u0002\u0011\u0011!C!\u0003\u0013B\u0011\"!\u0014\u0001\u0003\u0003%\t%a\u0014\t\u0013\u0005E\u0003!!A\u0005B\u0005M\u0003\"CA+\u0001\u0005\u0005I\u0011IA,\u000f%\t\u0019hIA\u0001\u0012\u0003\t)H\u0002\u0005#G\u0005\u0005\t\u0012AA<\u0011\u0019\tG\u0004\"\u0001\u0002\u0010\"I\u0011\u0011\u000b\u000f\u0002\u0002\u0013\u0015\u00131\u000b\u0005\n\u0003#c\u0012\u0011!CA\u0003'C\u0011\"a(\u001d\u0003\u0003%\t)!)\t\u0013\u0005MF$!A\u0005\n\u0005U&!J*qCJ\\G*[:uK:,'/\u0012=fGV$xN]#yG2,H-\u001a3G_J\u001cF/Y4f\u0015\t!S%A\u0005tG\",G-\u001e7fe*\u0011aeJ\u0001\u0006gB\f'o\u001b\u0006\u0003Q%\na!\u00199bG\",'\"\u0001\u0016\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001i3g\u000e\u001e\u0011\u00059\nT\"A\u0018\u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0003\r\u0005s\u0017PU3g!\t!T'D\u0001$\u0013\t14E\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bC\u0001\u00189\u0013\tItFA\u0004Qe>$Wo\u0019;\u0011\u0005m\u001aeB\u0001\u001fB\u001d\ti\u0004)D\u0001?\u0015\ty4&\u0001\u0004=e>|GOP\u0005\u0002a%\u0011!iL\u0001\ba\u0006\u001c7.Y4f\u0013\t!UI\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002C_\u0005!A/[7f+\u0005A\u0005C\u0001\u0018J\u0013\tQuF\u0001\u0003M_:<\u0017!\u0002;j[\u0016\u0004\u0013AC3yK\u000e,Ho\u001c:JIV\ta\n\u0005\u0002P':\u0011\u0001+\u0015\t\u0003{=J!AU\u0018\u0002\rA\u0013X\rZ3g\u0013\t!VK\u0001\u0004TiJLgn\u001a\u0006\u0003%>\n1\"\u001a=fGV$xN]%eA\u0005aA/Y:l\r\u0006LG.\u001e:fgV\t\u0011\f\u0005\u0002/5&\u00111l\f\u0002\u0004\u0013:$\u0018!\u0004;bg.4\u0015-\u001b7ve\u0016\u001c\b%A\u0004ti\u0006<W-\u00133\u0002\u0011M$\u0018mZ3JI\u0002\nab\u001d;bO\u0016\fE\u000f^3naRLE-A\bti\u0006<W-\u0011;uK6\u0004H/\u00133!\u0003\u0019a\u0014N\\5u}Q11\rZ3gO\"\u0004\"\u0001\u000e\u0001\t\u000b\u0019[\u0001\u0019\u0001%\t\u000b1[\u0001\u0019\u0001(\t\u000b][\u0001\u0019A-\t\u000bu[\u0001\u0019A-\t\u000b}[\u0001\u0019A-\u0002\t\r|\u0007/\u001f\u000b\u0007G.dWN\\8\t\u000f\u0019c\u0001\u0013!a\u0001\u0011\"9A\n\u0004I\u0001\u0002\u0004q\u0005bB,\r!\u0003\u0005\r!\u0017\u0005\b;2\u0001\n\u00111\u0001Z\u0011\u001dyF\u0002%AA\u0002e\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001sU\tA5oK\u0001u!\t)(0D\u0001w\u0015\t9\b0A\u0005v]\u000eDWmY6fI*\u0011\u0011pL\u0001\u000bC:tw\u000e^1uS>t\u0017BA>w\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005q(F\u0001(t\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!a\u0001+\u0005e\u001b\u0018AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u0002\t\u0005\u0003\u001f\tI\"\u0004\u0002\u0002\u0012)!\u00111CA\u000b\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0011\u0001\u00026bm\u0006L1\u0001VA\t\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\t\u0002(A\u0019a&a\t\n\u0007\u0005\u0015rFA\u0002B]fD\u0001\"!\u000b\u0015\u0003\u0003\u0005\r!W\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005=\u0002CBA\u0019\u0003o\t\t#\u0004\u0002\u00024)\u0019\u0011QG\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002:\u0005M\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0010\u0002FA\u0019a&!\u0011\n\u0007\u0005\rsFA\u0004C_>dW-\u00198\t\u0013\u0005%b#!AA\u0002\u0005\u0005\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0004\u0002L!A\u0011\u0011F\f\u0002\u0002\u0003\u0007\u0011,\u0001\u0005iCND7i\u001c3f)\u0005I\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u00055\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0002@\u0005e\u0003\"CA\u00155\u0005\u0005\t\u0019AA\u0011Q\r\u0001\u0011Q\f\t\u0005\u0003?\n\u0019'\u0004\u0002\u0002b)\u0011\u00110J\u0005\u0005\u0003K\n\tG\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000eK\u0003\u0001\u0003S\ny\u0007\u0005\u0003\u0002`\u0005-\u0014\u0002BA7\u0003C\u0012QaU5oG\u0016\f#!!\u001d\u0002\u000bMr\u0013G\f\u0019\u0002KM\u0003\u0018M]6MSN$XM\\3s\u000bb,7-\u001e;pe\u0016C8\r\\;eK\u00124uN]*uC\u001e,\u0007C\u0001\u001b\u001d'\u0015a\u0012\u0011PAC!)\tY(!!I\u001dfK\u0016lY\u0007\u0003\u0003{R1!a 0\u0003\u001d\u0011XO\u001c;j[\u0016LA!a!\u0002~\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001b\u0011\t\u0005\u001d\u0015QR\u0007\u0003\u0003\u0013SA!a#\u0002\u0016\u0005\u0011\u0011n\\\u0005\u0004\t\u0006%ECAA;\u0003\u0015\t\u0007\u000f\u001d7z)-\u0019\u0017QSAL\u00033\u000bY*!(\t\u000b\u0019{\u0002\u0019\u0001%\t\u000b1{\u0002\u0019\u0001(\t\u000b]{\u0002\u0019A-\t\u000bu{\u0002\u0019A-\t\u000b}{\u0002\u0019A-\u0002\u000fUt\u0017\r\u001d9msR!\u00111UAX!\u0015q\u0013QUAU\u0013\r\t9k\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u00119\nY\u000b\u0013(Z3fK1!!,0\u0005\u0019!V\u000f\u001d7fk!A\u0011\u0011\u0017\u0011\u0002\u0002\u0003\u00071-A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a.\u0011\t\u0005=\u0011\u0011X\u0005\u0005\u0003w\u000b\tB\u0001\u0004PE*,7\r\u001e"
)
public class SparkListenerExecutorExcludedForStage implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String executorId;
   private final int taskFailures;
   private final int stageId;
   private final int stageAttemptId;

   public static Option unapply(final SparkListenerExecutorExcludedForStage x$0) {
      return SparkListenerExecutorExcludedForStage$.MODULE$.unapply(x$0);
   }

   public static SparkListenerExecutorExcludedForStage apply(final long time, final String executorId, final int taskFailures, final int stageId, final int stageAttemptId) {
      return SparkListenerExecutorExcludedForStage$.MODULE$.apply(time, executorId, taskFailures, stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return SparkListenerExecutorExcludedForStage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerExecutorExcludedForStage$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public long time() {
      return this.time;
   }

   public String executorId() {
      return this.executorId;
   }

   public int taskFailures() {
      return this.taskFailures;
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public SparkListenerExecutorExcludedForStage copy(final long time, final String executorId, final int taskFailures, final int stageId, final int stageAttemptId) {
      return new SparkListenerExecutorExcludedForStage(time, executorId, taskFailures, stageId, stageAttemptId);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.executorId();
   }

   public int copy$default$3() {
      return this.taskFailures();
   }

   public int copy$default$4() {
      return this.stageId();
   }

   public int copy$default$5() {
      return this.stageAttemptId();
   }

   public String productPrefix() {
      return "SparkListenerExecutorExcludedForStage";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 1 -> {
            return this.executorId();
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.taskFailures());
         }
         case 3 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 4 -> {
            return BoxesRunTime.boxToInteger(this.stageAttemptId());
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
      return x$1 instanceof SparkListenerExecutorExcludedForStage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "executorId";
         }
         case 2 -> {
            return "taskFailures";
         }
         case 3 -> {
            return "stageId";
         }
         case 4 -> {
            return "stageAttemptId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.time()));
      var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
      var1 = Statics.mix(var1, this.taskFailures());
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, this.stageAttemptId());
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof SparkListenerExecutorExcludedForStage) {
               SparkListenerExecutorExcludedForStage var4 = (SparkListenerExecutorExcludedForStage)x$1;
               if (this.time() == var4.time() && this.taskFailures() == var4.taskFailures() && this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId()) {
                  label56: {
                     String var10000 = this.executorId();
                     String var5 = var4.executorId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
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

   public SparkListenerExecutorExcludedForStage(final long time, final String executorId, final int taskFailures, final int stageId, final int stageAttemptId) {
      this.time = time;
      this.executorId = executorId;
      this.taskFailures = taskFailures;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
