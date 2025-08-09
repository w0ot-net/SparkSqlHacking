package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDDOperationScope;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b!B\n\u0015\u0003\u0003y\u0002\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u0011a\u0002!1!Q\u0001\feBQa\u0010\u0001\u0005\u0002\u0001C\u0001\"\u0012\u0001A\u0002\u0013\u0005aC\u0012\u0005\t\u0015\u0002\u0001\r\u0011\"\u0001\u0017\u0017\"1\u0011\u000b\u0001Q!\n\u001dCqA\u0015\u0001C\u0002\u0013\u00051\u000b\u0003\u0004X\u0001\u0001\u0006I\u0001\u0016\u0005\t1\u0002\u0011\r\u0011\"\u0005\u00173\"11\r\u0001Q\u0001\niCa\u0001\u001a\u0001\u0005\u0002Y)\u0007\u0002C9\u0001\u0005\u0004%\tF\u0006:\t\rQ\u0004\u0001\u0015!\u0003t\u0011\u0019)\b\u0001\"\u0011\u0017m\")A\u0010\u0001C!{\"9\u0011\u0011\u0004\u0001\u0005B\u0005m\u0001bBA\u0012\u0001\u0019\u0005\u0011Q\u0005\u0005\b\u0003O\u0001a\u0011AA\u0013\u00051Ie\u000e];u\tN#(/Z1n\u0015\t)b#A\u0004egR\u0014X-Y7\u000b\u0005]A\u0012!C:ue\u0016\fW.\u001b8h\u0015\tI\"$A\u0003ta\u0006\u00148N\u0003\u0002\u001c9\u00051\u0011\r]1dQ\u0016T\u0011!H\u0001\u0004_J<7\u0001A\u000b\u0003A\u001d\u001a\"\u0001A\u0011\u0011\u0007\t\u001aS%D\u0001\u0015\u0013\t!CCA\u0004E'R\u0014X-Y7\u0011\u0005\u0019:C\u0002\u0001\u0003\u0006Q\u0001\u0011\r!\u000b\u0002\u0002)F\u0011!\u0006\r\t\u0003W9j\u0011\u0001\f\u0006\u0002[\u0005)1oY1mC&\u0011q\u0006\f\u0002\b\u001d>$\b.\u001b8h!\tY\u0013'\u0003\u00023Y\t\u0019\u0011I\\=\u0002\t}\u001b8o\u0019\t\u0003kYj\u0011AF\u0005\u0003oY\u0011\u0001c\u0015;sK\u0006l\u0017N\\4D_:$X\r\u001f;\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002;{\u0015j\u0011a\u000f\u0006\u0003y1\nqA]3gY\u0016\u001cG/\u0003\u0002?w\tA1\t\\1tgR\u000bw-\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\u0012#\"AQ\"\u0011\u0007\t\u0002Q\u0005C\u00039\u0007\u0001\u000f\u0011\bC\u00034\u0007\u0001\u0007A'A\u0007mCN$h+\u00197jIRKW.Z\u000b\u0002\u000fB\u0011Q\u0007S\u0005\u0003\u0013Z\u0011A\u0001V5nK\u0006\tB.Y:u-\u0006d\u0017\u000e\u001a+j[\u0016|F%Z9\u0015\u00051{\u0005CA\u0016N\u0013\tqEF\u0001\u0003V]&$\bb\u0002)\u0006\u0003\u0003\u0005\raR\u0001\u0004q\u0012\n\u0014A\u00047bgR4\u0016\r\\5e)&lW\rI\u0001\u0003S\u0012,\u0012\u0001\u0016\t\u0003WUK!A\u0016\u0017\u0003\u0007%sG/A\u0002jI\u0002\naB]1uK\u000e{g\u000e\u001e:pY2,'/F\u0001[!\rY3,X\u0005\u000392\u0012aa\u00149uS>t\u0007C\u00010b\u001b\u0005y&B\u00011\u0017\u0003%\u00198\r[3ek2,'/\u0003\u0002c?\nq!+\u0019;f\u0007>tGO]8mY\u0016\u0014\u0018a\u0004:bi\u0016\u001cuN\u001c;s_2dWM\u001d\u0011\u0002\t9\fW.Z\u000b\u0002MB\u0011qM\u001c\b\u0003Q2\u0004\"!\u001b\u0017\u000e\u0003)T!a\u001b\u0010\u0002\rq\u0012xn\u001c;?\u0013\tiG&\u0001\u0004Qe\u0016$WMZ\u0005\u0003_B\u0014aa\u0015;sS:<'BA7-\u0003%\u0011\u0017m]3TG>\u0004X-F\u0001t!\rY3LZ\u0001\u000bE\u0006\u001cXmU2pa\u0016\u0004\u0013aC5t)&lWMV1mS\u0012$\"a\u001e>\u0011\u0005-B\u0018BA=-\u0005\u001d\u0011un\u001c7fC:DQa\u001f\bA\u0002\u001d\u000bA\u0001^5nK\u0006aA-\u001a9f]\u0012,gnY5fgV\ta\u0010E\u0003\u0000\u0003\u0013\tyA\u0004\u0003\u0002\u0002\u0005\u0015abA5\u0002\u0004%\tQ&C\u0002\u0002\b1\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\f\u00055!\u0001\u0002'jgRT1!a\u0002-a\u0011\t\t\"!\u0006\u0011\t\t\u001a\u00131\u0003\t\u0004M\u0005UAACA\f\u001f\u0005\u0005\t\u0011!B\u0001S\t\u0019q\fJ\u0019\u0002\u001bMd\u0017\u000eZ3EkJ\fG/[8o+\t\ti\u0002E\u00026\u0003?I1!!\t\u0017\u0005!!UO]1uS>t\u0017!B:uCJ$H#\u0001'\u0002\tM$x\u000e\u001d"
)
public abstract class InputDStream extends DStream {
   private Time lastValidTime = null;
   private final int id;
   private final Option rateController;
   private final Option baseScope;

   public Time lastValidTime() {
      return this.lastValidTime;
   }

   public void lastValidTime_$eq(final Time x$1) {
      this.lastValidTime = x$1;
   }

   public int id() {
      return this.id;
   }

   public Option rateController() {
      return this.rateController;
   }

   public String name() {
      String newName = .MODULE$.capitalize$extension(scala.Predef..MODULE$.augmentString(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])org.apache.spark.util.Utils..MODULE$.getFormattedClassName(this).replaceAll("InputDStream", "Stream").split("(?=[A-Z])")), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$name$1(x$1)))).mkString(" ").toLowerCase(Locale.ROOT)));
      return newName + " [" + this.id() + "]";
   }

   public Option baseScope() {
      return this.baseScope;
   }

   public boolean isTimeValid(final Time time) {
      if (!super.isTimeValid(time)) {
         return false;
      } else {
         if (this.lastValidTime() != null && time.$less(this.lastValidTime())) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"isTimeValid called with ", " whereas the last valid time "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LAST_VALID_TIME..MODULE$, this.lastValidTime())}))))));
         }

         this.lastValidTime_$eq(time);
         return true;
      }
   }

   public List dependencies() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public Duration slideDuration() {
      if (this.ssc() == null) {
         throw new Exception("ssc is null");
      } else if (this.ssc().graph().batchDuration() == null) {
         throw new Exception("batchDuration is null");
      } else {
         return this.ssc().graph().batchDuration();
      }
   }

   public abstract void start();

   public abstract void stop();

   // $FF: synthetic method
   public static final boolean $anonfun$name$1(final String x$1) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   public InputDStream(final StreamingContext _ssc, final ClassTag evidence$1) {
      super(_ssc, evidence$1);
      this.ssc().graph().addInputStream(this);
      this.id = this.ssc().getNewInputStreamId();
      this.rateController = scala.None..MODULE$;
      String scopeName = (String)scala.Option..MODULE$.apply(this.ssc().sc().getLocalProperty(org.apache.spark.SparkContext..MODULE$.RDD_SCOPE_KEY())).map((json) -> {
         String var10000 = org.apache.spark.rdd.RDDOperationScope..MODULE$.fromJson(json).name();
         return var10000 + " [" + this.id() + "]";
      }).getOrElse(() -> this.name().toLowerCase(Locale.ROOT));
      this.baseScope = new Some((new RDDOperationScope(scopeName, org.apache.spark.rdd.RDDOperationScope..MODULE$.$lessinit$greater$default$2(), org.apache.spark.rdd.RDDOperationScope..MODULE$.$lessinit$greater$default$3())).toJson());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
