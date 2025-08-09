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

/** @deprecated */
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g\u0001\u0002\u0012$\u00012B\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0011\"AA\n\u0001BK\u0002\u0013\u0005Q\n\u0003\u0005W\u0001\tE\t\u0015!\u0003O\u0011!9\u0006A!f\u0001\n\u0003A\u0006\u0002\u0003/\u0001\u0005#\u0005\u000b\u0011B-\t\u0011u\u0003!Q3A\u0005\u0002aC\u0001B\u0018\u0001\u0003\u0012\u0003\u0006I!\u0017\u0005\t?\u0002\u0011)\u001a!C\u00011\"A\u0001\r\u0001B\tB\u0003%\u0011\fC\u0003b\u0001\u0011\u0005!\rC\u0004j\u0001\u0005\u0005I\u0011\u00016\t\u000fA\u0004\u0011\u0013!C\u0001c\"9A\u0010AI\u0001\n\u0003i\b\u0002C@\u0001#\u0003%\t!!\u0001\t\u0013\u0005\u0015\u0001!%A\u0005\u0002\u0005\u0005\u0001\"CA\u0004\u0001E\u0005I\u0011AA\u0001\u0011%\tI\u0001AA\u0001\n\u0003\nY\u0001\u0003\u0005\u0002\u001c\u0001\t\t\u0011\"\u0001Y\u0011%\ti\u0002AA\u0001\n\u0003\ty\u0002C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u000f\u0002\u0011\u0011!C!\u0003\u0013B\u0011\"!\u0014\u0001\u0003\u0003%\t%a\u0014\t\u0013\u0005E\u0003!!A\u0005B\u0005M\u0003\"CA+\u0001\u0005\u0005I\u0011IA,\u000f%\tYhIA\u0001\u0012\u0003\tiH\u0002\u0005#G\u0005\u0005\t\u0012AA@\u0011\u0019\tG\u0004\"\u0001\u0002\u0018\"I\u0011\u0011\u000b\u000f\u0002\u0002\u0013\u0015\u00131\u000b\u0005\n\u00033c\u0012\u0011!CA\u00037C\u0011\"a*\u001d\u0003\u0003%\t)!+\t\u0013\u0005mF$!A\u0005\n\u0005u&\u0001J*qCJ\\G*[:uK:,'OT8eK\nc\u0017mY6mSN$X\r\u001a$peN#\u0018mZ3\u000b\u0005\u0011*\u0013!C:dQ\u0016$W\u000f\\3s\u0015\t1s%A\u0003ta\u0006\u00148N\u0003\u0002)S\u00051\u0011\r]1dQ\u0016T\u0011AK\u0001\u0004_J<7\u0001A\n\u0006\u00015\u001atG\u000f\t\u0003]Ej\u0011a\f\u0006\u0002a\u0005)1oY1mC&\u0011!g\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q*T\"A\u0012\n\u0005Y\u001a#AE*qCJ\\G*[:uK:,'/\u0012<f]R\u0004\"A\f\u001d\n\u0005ez#a\u0002)s_\u0012,8\r\u001e\t\u0003w\rs!\u0001P!\u000f\u0005u\u0002U\"\u0001 \u000b\u0005}Z\u0013A\u0002\u001fs_>$h(C\u00011\u0013\t\u0011u&A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0011+%\u0001D*fe&\fG.\u001b>bE2,'B\u0001\"0\u0003\u0011!\u0018.\\3\u0016\u0003!\u0003\"AL%\n\u0005){#\u0001\u0002'p]\u001e\fQ\u0001^5nK\u0002\na\u0001[8ti&#W#\u0001(\u0011\u0005=\u001bfB\u0001)R!\tit&\u0003\u0002S_\u00051\u0001K]3eK\u001aL!\u0001V+\u0003\rM#(/\u001b8h\u0015\t\u0011v&A\u0004i_N$\u0018\n\u001a\u0011\u0002!\u0015DXmY;u_J4\u0015-\u001b7ve\u0016\u001cX#A-\u0011\u00059R\u0016BA.0\u0005\rIe\u000e^\u0001\u0012Kb,7-\u001e;pe\u001a\u000b\u0017\u000e\\;sKN\u0004\u0013aB:uC\u001e,\u0017\nZ\u0001\tgR\fw-Z%eA\u0005q1\u000f^1hK\u0006#H/Z7qi&#\u0017aD:uC\u001e,\u0017\t\u001e;f[B$\u0018\n\u001a\u0011\u0002\rqJg.\u001b;?)\u0019\u0019G-\u001a4hQB\u0011A\u0007\u0001\u0005\u0006\r.\u0001\r\u0001\u0013\u0005\u0006\u0019.\u0001\rA\u0014\u0005\u0006/.\u0001\r!\u0017\u0005\u0006;.\u0001\r!\u0017\u0005\u0006?.\u0001\r!W\u0001\u0005G>\u0004\u0018\u0010\u0006\u0004dW2lgn\u001c\u0005\b\r2\u0001\n\u00111\u0001I\u0011\u001daE\u0002%AA\u00029Cqa\u0016\u0007\u0011\u0002\u0003\u0007\u0011\fC\u0004^\u0019A\u0005\t\u0019A-\t\u000f}c\u0001\u0013!a\u00013\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001:+\u0005!\u001b8&\u0001;\u0011\u0005UTX\"\u0001<\u000b\u0005]D\u0018!C;oG\",7m[3e\u0015\tIx&\u0001\u0006b]:|G/\u0019;j_:L!a\u001f<\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003yT#AT:\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u00111\u0001\u0016\u00033N\fabY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ti\u0001\u0005\u0003\u0002\u0010\u0005eQBAA\t\u0015\u0011\t\u0019\"!\u0006\u0002\t1\fgn\u001a\u0006\u0003\u0003/\tAA[1wC&\u0019A+!\u0005\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011EA\u0014!\rq\u00131E\u0005\u0004\u0003Ky#aA!os\"A\u0011\u0011\u0006\u000b\u0002\u0002\u0003\u0007\u0011,A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003_\u0001b!!\r\u00028\u0005\u0005RBAA\u001a\u0015\r\t)dL\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u001d\u0003g\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qHA#!\rq\u0013\u0011I\u0005\u0004\u0003\u0007z#a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003S1\u0012\u0011!a\u0001\u0003C\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QBA&\u0011!\tIcFA\u0001\u0002\u0004I\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003e\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u001b\ta!Z9vC2\u001cH\u0003BA \u00033B\u0011\"!\u000b\u001b\u0003\u0003\u0005\r!!\t)\u0017\u0001\ti&a\u0019\u0002f\u0005%\u00141\u000e\t\u0004]\u0005}\u0013bAA1_\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u0012\u0011qM\u0001.kN,\u0007e\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f\u000bb\u001cG.\u001e3fI\u001a{'o\u0015;bO\u0016\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017EAA7\u0003\u0015\u0019d&\r\u00181Q\r\u0001\u0011\u0011\u000f\t\u0005\u0003g\n9(\u0004\u0002\u0002v)\u0011\u00110J\u0005\u0005\u0003s\n)H\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018.\u0001\u0013Ta\u0006\u00148\u000eT5ti\u0016tWM\u001d(pI\u0016\u0014E.Y2lY&\u001cH/\u001a3G_J\u001cF/Y4f!\t!DdE\u0003\u001d\u0003\u0003\u000bi\t\u0005\u0006\u0002\u0004\u0006%\u0005JT-Z3\u000el!!!\"\u000b\u0007\u0005\u001du&A\u0004sk:$\u0018.\\3\n\t\u0005-\u0015Q\u0011\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:,\u0004\u0003BAH\u0003+k!!!%\u000b\t\u0005M\u0015QC\u0001\u0003S>L1\u0001RAI)\t\ti(A\u0003baBd\u0017\u0010F\u0006d\u0003;\u000by*!)\u0002$\u0006\u0015\u0006\"\u0002$ \u0001\u0004A\u0005\"\u0002' \u0001\u0004q\u0005\"B, \u0001\u0004I\u0006\"B/ \u0001\u0004I\u0006\"B0 \u0001\u0004I\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0003W\u000b9\fE\u0003/\u0003[\u000b\t,C\u0002\u00020>\u0012aa\u00149uS>t\u0007\u0003\u0003\u0018\u00024\"s\u0015,W-\n\u0007\u0005UvF\u0001\u0004UkBdW-\u000e\u0005\t\u0003s\u0003\u0013\u0011!a\u0001G\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0006\u0003BA\b\u0003\u0003LA!a1\u0002\u0012\t1qJ\u00196fGR\u0004"
)
public class SparkListenerNodeBlacklistedForStage implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String hostId;
   private final int executorFailures;
   private final int stageId;
   private final int stageAttemptId;

   public static Option unapply(final SparkListenerNodeBlacklistedForStage x$0) {
      return SparkListenerNodeBlacklistedForStage$.MODULE$.unapply(x$0);
   }

   public static SparkListenerNodeBlacklistedForStage apply(final long time, final String hostId, final int executorFailures, final int stageId, final int stageAttemptId) {
      return SparkListenerNodeBlacklistedForStage$.MODULE$.apply(time, hostId, executorFailures, stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return SparkListenerNodeBlacklistedForStage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerNodeBlacklistedForStage$.MODULE$.curried();
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

   public SparkListenerNodeBlacklistedForStage copy(final long time, final String hostId, final int executorFailures, final int stageId, final int stageAttemptId) {
      return new SparkListenerNodeBlacklistedForStage(time, hostId, executorFailures, stageId, stageAttemptId);
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
      return "SparkListenerNodeBlacklistedForStage";
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
      return x$1 instanceof SparkListenerNodeBlacklistedForStage;
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
            if (x$1 instanceof SparkListenerNodeBlacklistedForStage) {
               SparkListenerNodeBlacklistedForStage var4 = (SparkListenerNodeBlacklistedForStage)x$1;
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

   public SparkListenerNodeBlacklistedForStage(final long time, final String hostId, final int executorFailures, final int stageId, final int stageAttemptId) {
      this.time = time;
      this.hostId = hostId;
      this.executorFailures = executorFailures;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
