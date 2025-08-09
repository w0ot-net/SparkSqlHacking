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
   bytes = "\u0006\u0005\u0005uf\u0001\u0002\u0012$\u00012B\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0011\"AA\n\u0001BK\u0002\u0013\u0005Q\n\u0003\u0005W\u0001\tE\t\u0015!\u0003O\u0011!9\u0006A!f\u0001\n\u0003A\u0006\u0002\u0003/\u0001\u0005#\u0005\u000b\u0011B-\t\u0011u\u0003!Q3A\u0005\u0002aC\u0001B\u0018\u0001\u0003\u0012\u0003\u0006I!\u0017\u0005\t?\u0002\u0011)\u001a!C\u00011\"A\u0001\r\u0001B\tB\u0003%\u0011\fC\u0003b\u0001\u0011\u0005!\rC\u0004j\u0001\u0005\u0005I\u0011\u00016\t\u000fA\u0004\u0011\u0013!C\u0001c\"9A\u0010AI\u0001\n\u0003i\b\u0002C@\u0001#\u0003%\t!!\u0001\t\u0013\u0005\u0015\u0001!%A\u0005\u0002\u0005\u0005\u0001\"CA\u0004\u0001E\u0005I\u0011AA\u0001\u0011%\tI\u0001AA\u0001\n\u0003\nY\u0001\u0003\u0005\u0002\u001c\u0001\t\t\u0011\"\u0001Y\u0011%\ti\u0002AA\u0001\n\u0003\ty\u0002C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u000f\u0002\u0011\u0011!C!\u0003\u0013B\u0011\"!\u0014\u0001\u0003\u0003%\t%a\u0014\t\u0013\u0005E\u0003!!A\u0005B\u0005M\u0003\"CA+\u0001\u0005\u0005I\u0011IA,\u000f%\t\u0019hIA\u0001\u0012\u0003\t)H\u0002\u0005#G\u0005\u0005\t\u0012AA<\u0011\u0019\tG\u0004\"\u0001\u0002\u0010\"I\u0011\u0011\u000b\u000f\u0002\u0002\u0013\u0015\u00131\u000b\u0005\n\u0003#c\u0012\u0011!CA\u0003'C\u0011\"a(\u001d\u0003\u0003%\t)!)\t\u0013\u0005MF$!A\u0005\n\u0005U&!I*qCJ\\G*[:uK:,'OT8eK\u0016C8\r\\;eK\u00124uN]*uC\u001e,'B\u0001\u0013&\u0003%\u00198\r[3ek2,'O\u0003\u0002'O\u0005)1\u000f]1sW*\u0011\u0001&K\u0001\u0007CB\f7\r[3\u000b\u0003)\n1a\u001c:h\u0007\u0001\u0019R\u0001A\u00174oi\u0002\"AL\u0019\u000e\u0003=R\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\u0012a!\u00118z%\u00164\u0007C\u0001\u001b6\u001b\u0005\u0019\u0013B\u0001\u001c$\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u00059B\u0014BA\u001d0\u0005\u001d\u0001&o\u001c3vGR\u0004\"aO\"\u000f\u0005q\neBA\u001fA\u001b\u0005q$BA ,\u0003\u0019a$o\\8u}%\t\u0001'\u0003\u0002C_\u00059\u0001/Y2lC\u001e,\u0017B\u0001#F\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0011u&\u0001\u0003uS6,W#\u0001%\u0011\u00059J\u0015B\u0001&0\u0005\u0011auN\\4\u0002\u000bQLW.\u001a\u0011\u0002\r!|7\u000f^%e+\u0005q\u0005CA(T\u001d\t\u0001\u0016\u000b\u0005\u0002>_%\u0011!kL\u0001\u0007!J,G-\u001a4\n\u0005Q+&AB*ue&twM\u0003\u0002S_\u00059\u0001n\\:u\u0013\u0012\u0004\u0013\u0001E3yK\u000e,Ho\u001c:GC&dWO]3t+\u0005I\u0006C\u0001\u0018[\u0013\tYvFA\u0002J]R\f\u0011#\u001a=fGV$xN\u001d$bS2,(/Z:!\u0003\u001d\u0019H/Y4f\u0013\u0012\f\u0001b\u001d;bO\u0016LE\rI\u0001\u000fgR\fw-Z!ui\u0016l\u0007\u000f^%e\u0003=\u0019H/Y4f\u0003R$X-\u001c9u\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0004dI\u00164w\r\u001b\t\u0003i\u0001AQAR\u0006A\u0002!CQ\u0001T\u0006A\u00029CQaV\u0006A\u0002eCQ!X\u0006A\u0002eCQaX\u0006A\u0002e\u000bAaY8qsR11m\u001b7n]>DqA\u0012\u0007\u0011\u0002\u0003\u0007\u0001\nC\u0004M\u0019A\u0005\t\u0019\u0001(\t\u000f]c\u0001\u0013!a\u00013\"9Q\f\u0004I\u0001\u0002\u0004I\u0006bB0\r!\u0003\u0005\r!W\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0011(F\u0001%tW\u0005!\bCA;{\u001b\u00051(BA<y\u0003%)hn\u00195fG.,GM\u0003\u0002z_\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005m4(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u0001@+\u00059\u001b\u0018AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003\u0007Q#!W:\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\u000eA!\u0011qBA\r\u001b\t\t\tB\u0003\u0003\u0002\u0014\u0005U\u0011\u0001\u00027b]\u001eT!!a\u0006\u0002\t)\fg/Y\u0005\u0004)\u0006E\u0011\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003C\t9\u0003E\u0002/\u0003GI1!!\n0\u0005\r\te.\u001f\u0005\t\u0003S!\u0012\u0011!a\u00013\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\f\u0011\r\u0005E\u0012qGA\u0011\u001b\t\t\u0019DC\u0002\u00026=\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI$a\r\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u007f\t)\u0005E\u0002/\u0003\u0003J1!a\u00110\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\u000b\u0017\u0003\u0003\u0005\r!!\t\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u001b\tY\u0005\u0003\u0005\u0002*]\t\t\u00111\u0001Z\u0003!A\u0017m\u001d5D_\u0012,G#A-\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0004\u0002\r\u0015\fX/\u00197t)\u0011\ty$!\u0017\t\u0013\u0005%\"$!AA\u0002\u0005\u0005\u0002f\u0001\u0001\u0002^A!\u0011qLA2\u001b\t\t\tG\u0003\u0002zK%!\u0011QMA1\u00051!UM^3m_B,'/\u00119jQ\u0015\u0001\u0011\u0011NA8!\u0011\ty&a\u001b\n\t\u00055\u0014\u0011\r\u0002\u0006'&t7-Z\u0011\u0003\u0003c\nQa\r\u00182]A\n\u0011e\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f\u000bb\u001cG.\u001e3fI\u001a{'o\u0015;bO\u0016\u0004\"\u0001\u000e\u000f\u0014\u000bq\tI(!\"\u0011\u0015\u0005m\u0014\u0011\u0011%O3fK6-\u0004\u0002\u0002~)\u0019\u0011qP\u0018\u0002\u000fI,h\u000e^5nK&!\u00111QA?\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\u000e\t\u0005\u0003\u000f\u000bi)\u0004\u0002\u0002\n*!\u00111RA\u000b\u0003\tIw.C\u0002E\u0003\u0013#\"!!\u001e\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0017\r\f)*a&\u0002\u001a\u0006m\u0015Q\u0014\u0005\u0006\r~\u0001\r\u0001\u0013\u0005\u0006\u0019~\u0001\rA\u0014\u0005\u0006/~\u0001\r!\u0017\u0005\u0006;~\u0001\r!\u0017\u0005\u0006?~\u0001\r!W\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\u0019+a,\u0011\u000b9\n)+!+\n\u0007\u0005\u001dvF\u0001\u0004PaRLwN\u001c\t\t]\u0005-\u0006JT-Z3&\u0019\u0011QV\u0018\u0003\rQ+\b\u000f\\36\u0011!\t\t\fIA\u0001\u0002\u0004\u0019\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0017\t\u0005\u0003\u001f\tI,\u0003\u0003\u0002<\u0006E!AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerNodeExcludedForStage implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String hostId;
   private final int executorFailures;
   private final int stageId;
   private final int stageAttemptId;

   public static Option unapply(final SparkListenerNodeExcludedForStage x$0) {
      return SparkListenerNodeExcludedForStage$.MODULE$.unapply(x$0);
   }

   public static SparkListenerNodeExcludedForStage apply(final long time, final String hostId, final int executorFailures, final int stageId, final int stageAttemptId) {
      return SparkListenerNodeExcludedForStage$.MODULE$.apply(time, hostId, executorFailures, stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return SparkListenerNodeExcludedForStage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerNodeExcludedForStage$.MODULE$.curried();
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

   public String hostId() {
      return this.hostId;
   }

   public int executorFailures() {
      return this.executorFailures;
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public SparkListenerNodeExcludedForStage copy(final long time, final String hostId, final int executorFailures, final int stageId, final int stageAttemptId) {
      return new SparkListenerNodeExcludedForStage(time, hostId, executorFailures, stageId, stageAttemptId);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.hostId();
   }

   public int copy$default$3() {
      return this.executorFailures();
   }

   public int copy$default$4() {
      return this.stageId();
   }

   public int copy$default$5() {
      return this.stageAttemptId();
   }

   public String productPrefix() {
      return "SparkListenerNodeExcludedForStage";
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
            return this.hostId();
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.executorFailures());
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
      return x$1 instanceof SparkListenerNodeExcludedForStage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "hostId";
         }
         case 2 -> {
            return "executorFailures";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.hostId()));
      var1 = Statics.mix(var1, this.executorFailures());
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
            if (x$1 instanceof SparkListenerNodeExcludedForStage) {
               SparkListenerNodeExcludedForStage var4 = (SparkListenerNodeExcludedForStage)x$1;
               if (this.time() == var4.time() && this.executorFailures() == var4.executorFailures() && this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId()) {
                  label56: {
                     String var10000 = this.hostId();
                     String var5 = var4.hostId();
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

   public SparkListenerNodeExcludedForStage(final long time, final String hostId, final int executorFailures, final int stageId, final int stageAttemptId) {
      this.time = time;
      this.hostId = hostId;
      this.executorFailures = executorFailures;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
