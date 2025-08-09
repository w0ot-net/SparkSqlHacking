package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.util.kvstore.KVIndex;
import scala.Option;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154QAC\u0006\u0001\u001bMA\u0001B\u0007\u0001\u0003\u0006\u0004%\t\u0001\b\u0005\tK\u0001\u0011\t\u0011)A\u0005;!Aa\u0005\u0001BC\u0002\u0013\u0005q\u0005\u0003\u00057\u0001\t\u0005\t\u0015!\u0003)\u0011!9\u0004A!b\u0001\n\u0003A\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u000b\u0001\u0003A\u0011A!\t\u000b\u001d\u0003A\u0011\u0002%\t\u000b}\u0003A\u0011\u00021\u0003\u001d){'\rR1uC^\u0013\u0018\r\u001d9fe*\u0011A\"D\u0001\u0007gR\fG/^:\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\"\u0001\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0011IgNZ8\u0004\u0001U\tQ\u0004\u0005\u0002\u001fG5\tqD\u0003\u0002!C\u0005\u0011a/\r\u0006\u0003E-\t1!\u00199j\u0013\t!sDA\u0004K_\n$\u0015\r^1\u0002\u000b%tgm\u001c\u0011\u0002\u001bM\\\u0017\u000e\u001d9fIN#\u0018mZ3t+\u0005A\u0003cA\u00151g9\u0011!F\f\t\u0003WYi\u0011\u0001\f\u0006\u0003[m\ta\u0001\u0010:p_Rt\u0014BA\u0018\u0017\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011G\r\u0002\u0004'\u0016$(BA\u0018\u0017!\t)B'\u0003\u00026-\t\u0019\u0011J\u001c;\u0002\u001dM\\\u0017\u000e\u001d9fIN#\u0018mZ3tA\u0005q1/\u001d7Fq\u0016\u001cW\u000f^5p]&#W#A\u001d\u0011\u0007UQD(\u0003\u0002<-\t1q\n\u001d;j_:\u0004\"!F\u001f\n\u0005y2\"\u0001\u0002'p]\u001e\fqb]9m\u000bb,7-\u001e;j_:LE\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\t#UI\u0012\t\u0003\u0007\u0002i\u0011a\u0003\u0005\u00065\u001d\u0001\r!\b\u0005\u0006M\u001d\u0001\r\u0001\u000b\u0005\u0006o\u001d\u0001\r!O\u0001\u0003S\u0012,\u0012a\r\u0015\u0003\u0011)\u0003\"a\u0013+\u000e\u00031S!!\u0014(\u0002\u0015\u0005tgn\u001c;bi&|gN\u0003\u0002P!\u00069!.Y2lg>t'BA)S\u0003%1\u0017m\u001d;feblGNC\u0001T\u0003\r\u0019w.\\\u0005\u0003+2\u0013!BS:p]&;gn\u001c:fQ\tAq\u000b\u0005\u0002Y;6\t\u0011L\u0003\u0002[7\u000691N^:u_J,'B\u0001/\u000e\u0003\u0011)H/\u001b7\n\u0005yK&aB&W\u0013:$W\r_\u0001\u000fG>l\u0007\u000f\\3uS>tG+[7f+\u0005a\u0004FA\u0005KQ\u0011Iqk\u00193\u0002\u000bY\fG.^3\"\u0003}\u0003"
)
public class JobDataWrapper {
   private final JobData info;
   private final Set skippedStages;
   private final Option sqlExecutionId;

   public JobData info() {
      return this.info;
   }

   public Set skippedStages() {
      return this.skippedStages;
   }

   public Option sqlExecutionId() {
      return this.sqlExecutionId;
   }

   @JsonIgnore
   @KVIndex
   private int id() {
      return this.info().jobId();
   }

   @JsonIgnore
   @KVIndex("completionTime")
   private long completionTime() {
      return BoxesRunTime.unboxToLong(this.info().completionTime().map((x$1) -> BoxesRunTime.boxToLong($anonfun$completionTime$1(x$1))).getOrElse((JFunction0.mcJ.sp)() -> -1L));
   }

   // $FF: synthetic method
   public static final long $anonfun$completionTime$1(final Date x$1) {
      return x$1.getTime();
   }

   public JobDataWrapper(final JobData info, final Set skippedStages, final Option sqlExecutionId) {
      this.info = info;
      this.skippedStages = skippedStages;
      this.sqlExecutionId = sqlExecutionId;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
