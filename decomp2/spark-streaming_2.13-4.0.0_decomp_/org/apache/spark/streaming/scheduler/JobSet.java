package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tma!B\u00181\u0001JR\u0004\u0002C)\u0001\u0005+\u0007I\u0011\u0001*\t\u0011]\u0003!\u0011#Q\u0001\nMC\u0001\u0002\u0017\u0001\u0003\u0016\u0004%\t!\u0017\u0005\tC\u0002\u0011\t\u0012)A\u00055\"A!\r\u0001BK\u0002\u0013\u00051\r\u0003\u0005s\u0001\tE\t\u0015!\u0003e\u0011\u0015\u0019\b\u0001\"\u0001u\u0011\u001dI\bA1A\u0005\niDq!a\u0002\u0001A\u0003%1\u0010C\u0005\u0002\n\u0001\u0011\r\u0011\"\u0003\u0002\f!A\u00111\u0003\u0001!\u0002\u0013\ti\u0001C\u0005\u0002\u0016\u0001\u0001\r\u0011\"\u0003\u0002\f!I\u0011q\u0003\u0001A\u0002\u0013%\u0011\u0011\u0004\u0005\t\u0003K\u0001\u0001\u0015)\u0003\u0002\u000e!I\u0011q\u0005\u0001A\u0002\u0013%\u00111\u0002\u0005\n\u0003S\u0001\u0001\u0019!C\u0005\u0003WA\u0001\"a\f\u0001A\u0003&\u0011Q\u0002\u0005\b\u0003c\u0001A\u0011AA\u001a\u0011\u001d\tI\u0004\u0001C\u0001\u0003wAq!a\u0010\u0001\t\u0003\t\t\u0005C\u0004\u0002J\u0001!\t!!\u0011\t\u000f\u0005-\u0003\u0001\"\u0001\u0002\f!9\u0011Q\n\u0001\u0005\u0002\u0005-\u0001bBA(\u0001\u0011\u0005\u0011\u0011\u000b\u0005\n\u00033\u0002\u0011\u0011!C\u0001\u00037B\u0011\"a\u0019\u0001#\u0003%\t!!\u001a\t\u0013\u0005m\u0004!%A\u0005\u0002\u0005u\u0004\"CAA\u0001E\u0005I\u0011AAB\u0011%\t9\tAA\u0001\n\u0003\nI\tC\u0005\u0002\u001c\u0002\t\t\u0011\"\u0001\u0002\u001e\"I\u0011q\u0014\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0015\u0005\n\u0003W\u0003\u0011\u0011!C!\u0003[C\u0011\"a.\u0001\u0003\u0003%\t!!/\t\u0013\u0005u\u0006!!A\u0005B\u0005}\u0006\"CAb\u0001\u0005\u0005I\u0011IAc\u0011%\t9\rAA\u0001\n\u0003\nI\rC\u0005\u0002L\u0002\t\t\u0011\"\u0011\u0002N\u001eQ\u0011\u0011\u001b\u0019\u0002\u0002#\u0005!'a5\u0007\u0013=\u0002\u0014\u0011!E\u0001e\u0005U\u0007BB:(\t\u0003\ti\u000fC\u0005\u0002H\u001e\n\t\u0011\"\u0012\u0002J\"I\u0011q^\u0014\u0002\u0002\u0013\u0005\u0015\u0011\u001f\u0005\n\u0003s<\u0013\u0013!C\u0001\u0003\u0007C\u0011\"a?(\u0003\u0003%\t)!@\t\u0013\t=q%%A\u0005\u0002\u0005\r\u0005\"\u0003B\tO\u0005\u0005I\u0011\u0002B\n\u0005\u0019QuNY*fi*\u0011\u0011GM\u0001\ng\u000eDW\rZ;mKJT!a\r\u001b\u0002\u0013M$(/Z1nS:<'BA\u001b7\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0004(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002s\u0005\u0019qN]4\u0014\t\u0001Y\u0014\t\u0012\t\u0003y}j\u0011!\u0010\u0006\u0002}\u0005)1oY1mC&\u0011\u0001)\u0010\u0002\u0007\u0003:L(+\u001a4\u0011\u0005q\u0012\u0015BA\">\u0005\u001d\u0001&o\u001c3vGR\u0004\"!\u0012(\u000f\u0005\u0019ceBA$L\u001b\u0005A%BA%K\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001 \n\u00055k\u0014a\u00029bG.\fw-Z\u0005\u0003\u001fB\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!T\u001f\u0002\tQLW.Z\u000b\u0002'B\u0011A+V\u0007\u0002e%\u0011aK\r\u0002\u0005)&lW-A\u0003uS6,\u0007%\u0001\u0003k_\n\u001cX#\u0001.\u0011\u0007\u0015[V,\u0003\u0002]!\n\u00191+Z9\u0011\u0005y{V\"\u0001\u0019\n\u0005\u0001\u0004$a\u0001&pE\u0006)!n\u001c2tA\u0005\u00192\u000f\u001e:fC6LE\rV8J]B,H/\u00138g_V\tA\r\u0005\u0003fS2|gB\u00014h!\t9U(\u0003\u0002i{\u00051\u0001K]3eK\u001aL!A[6\u0003\u00075\u000b\u0007O\u0003\u0002i{A\u0011A(\\\u0005\u0003]v\u00121!\u00138u!\tq\u0006/\u0003\u0002ra\ty1\u000b\u001e:fC6Le\u000e];u\u0013:4w.\u0001\u000btiJ,\u0017-\\%e)>Le\u000e];u\u0013:4w\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tU4x\u000f\u001f\t\u0003=\u0002AQ!U\u0004A\u0002MCQ\u0001W\u0004A\u0002iCqAY\u0004\u0011\u0002\u0003\u0007A-\u0001\bj]\u000e|W\u000e\u001d7fi\u0016TuNY:\u0016\u0003m\u0004B\u0001`A\u0002;6\tQP\u0003\u0002\u007f\u007f\u00069Q.\u001e;bE2,'bAA\u0001{\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0007\u0005\u0015QPA\u0004ICND7+\u001a;\u0002\u001f%t7m\\7qY\u0016$XMS8cg\u0002\nab];c[&\u001c8/[8o)&lW-\u0006\u0002\u0002\u000eA\u0019A(a\u0004\n\u0007\u0005EQH\u0001\u0003M_:<\u0017aD:vE6L7o]5p]RKW.\u001a\u0011\u0002'A\u0014xnY3tg&twm\u0015;beR$\u0016.\\3\u0002/A\u0014xnY3tg&twm\u0015;beR$\u0016.\\3`I\u0015\fH\u0003BA\u000e\u0003C\u00012\u0001PA\u000f\u0013\r\ty\"\u0010\u0002\u0005+:LG\u000fC\u0005\u0002$5\t\t\u00111\u0001\u0002\u000e\u0005\u0019\u0001\u0010J\u0019\u0002)A\u0014xnY3tg&twm\u0015;beR$\u0016.\\3!\u0003E\u0001(o\\2fgNLgnZ#oIRKW.Z\u0001\u0016aJ|7-Z:tS:<WI\u001c3US6,w\fJ3r)\u0011\tY\"!\f\t\u0013\u0005\r\u0002#!AA\u0002\u00055\u0011A\u00059s_\u000e,7o]5oO\u0016sG\rV5nK\u0002\na\u0002[1oI2,'j\u001c2Ti\u0006\u0014H\u000f\u0006\u0003\u0002\u001c\u0005U\u0002BBA\u001c%\u0001\u0007Q,A\u0002k_\n\f1\u0003[1oI2,'j\u001c2D_6\u0004H.\u001a;j_:$B!a\u0007\u0002>!1\u0011qG\nA\u0002u\u000b!\u0002[1t'R\f'\u000f^3e+\t\t\u0019\u0005E\u0002=\u0003\u000bJ1!a\u0012>\u0005\u001d\u0011un\u001c7fC:\fA\u0002[1t\u0007>l\u0007\u000f\\3uK\u0012\fq\u0002\u001d:pG\u0016\u001c8/\u001b8h\t\u0016d\u0017-_\u0001\u000bi>$\u0018\r\u001c#fY\u0006L\u0018a\u0003;p\u0005\u0006$8\r[%oM>,\"!a\u0015\u0011\u0007y\u000b)&C\u0002\u0002XA\u0012\u0011BQ1uG\"LeNZ8\u0002\t\r|\u0007/\u001f\u000b\bk\u0006u\u0013qLA1\u0011\u001d\t\u0016\u0004%AA\u0002MCq\u0001W\r\u0011\u0002\u0003\u0007!\fC\u0004c3A\u0005\t\u0019\u00013\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\r\u0016\u0004'\u0006%4FAA6!\u0011\ti'a\u001e\u000e\u0005\u0005=$\u0002BA9\u0003g\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005UT(\u0001\u0006b]:|G/\u0019;j_:LA!!\u001f\u0002p\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u0010\u0016\u00045\u0006%\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003\u000bS3\u0001ZA5\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\u0012\t\u0005\u0003\u001b\u000b9*\u0004\u0002\u0002\u0010*!\u0011\u0011SAJ\u0003\u0011a\u0017M\\4\u000b\u0005\u0005U\u0015\u0001\u00026bm\u0006LA!!'\u0002\u0010\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012\u0001\\\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\u0019+!+\u0011\u0007q\n)+C\u0002\u0002(v\u00121!\u00118z\u0011!\t\u0019cHA\u0001\u0002\u0004a\u0017a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005=\u0006CBAY\u0003g\u000b\u0019+D\u0001\u0000\u0013\r\t)l \u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002D\u0005m\u0006\"CA\u0012C\u0005\u0005\t\u0019AAR\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005-\u0015\u0011\u0019\u0005\t\u0003G\u0011\u0013\u0011!a\u0001Y\u0006A\u0001.Y:i\u0007>$W\rF\u0001m\u0003!!xn\u0015;sS:<GCAAF\u0003\u0019)\u0017/^1mgR!\u00111IAh\u0011%\t\u0019#JA\u0001\u0002\u0004\t\u0019+\u0001\u0004K_\n\u001cV\r\u001e\t\u0003=\u001e\u001aRaJAl\u0003G\u0004\u0002\"!7\u0002`NSF-^\u0007\u0003\u00037T1!!8>\u0003\u001d\u0011XO\u001c;j[\u0016LA!!9\u0002\\\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005\u0015\u00181^\u0007\u0003\u0003OTA!!;\u0002\u0014\u0006\u0011\u0011n\\\u0005\u0004\u001f\u0006\u001dHCAAj\u0003\u0015\t\u0007\u000f\u001d7z)\u001d)\u00181_A{\u0003oDQ!\u0015\u0016A\u0002MCQ\u0001\u0017\u0016A\u0002iCqA\u0019\u0016\u0011\u0002\u0003\u0007A-A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00134\u0003\u001d)h.\u00199qYf$B!a@\u0003\fA)AH!\u0001\u0003\u0006%\u0019!1A\u001f\u0003\r=\u0003H/[8o!\u0019a$qA*[I&\u0019!\u0011B\u001f\u0003\rQ+\b\u000f\\34\u0011!\u0011i\u0001LA\u0001\u0002\u0004)\u0018a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!\u0006\u0011\t\u00055%qC\u0005\u0005\u00053\tyI\u0001\u0004PE*,7\r\u001e"
)
public class JobSet implements Product, Serializable {
   private final Time time;
   private final Seq jobs;
   private final Map streamIdToInputInfo;
   private final HashSet incompleteJobs;
   private final long submissionTime;
   private long processingStartTime;
   private long processingEndTime;

   public static Map $lessinit$greater$default$3() {
      return JobSet$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final JobSet x$0) {
      return JobSet$.MODULE$.unapply(x$0);
   }

   public static Map apply$default$3() {
      return JobSet$.MODULE$.apply$default$3();
   }

   public static JobSet apply(final Time time, final Seq jobs, final Map streamIdToInputInfo) {
      return JobSet$.MODULE$.apply(time, jobs, streamIdToInputInfo);
   }

   public static Function1 tupled() {
      return JobSet$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JobSet$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time time() {
      return this.time;
   }

   public Seq jobs() {
      return this.jobs;
   }

   public Map streamIdToInputInfo() {
      return this.streamIdToInputInfo;
   }

   private HashSet incompleteJobs() {
      return this.incompleteJobs;
   }

   private long submissionTime() {
      return this.submissionTime;
   }

   private long processingStartTime() {
      return this.processingStartTime;
   }

   private void processingStartTime_$eq(final long x$1) {
      this.processingStartTime = x$1;
   }

   private long processingEndTime() {
      return this.processingEndTime;
   }

   private void processingEndTime_$eq(final long x$1) {
      this.processingEndTime = x$1;
   }

   public void handleJobStart(final Job job) {
      if (this.processingStartTime() < 0L) {
         this.processingStartTime_$eq(System.currentTimeMillis());
      }
   }

   public void handleJobCompletion(final Job job) {
      this.incompleteJobs().$minus$eq(job);
      if (this.hasCompleted()) {
         this.processingEndTime_$eq(System.currentTimeMillis());
      }
   }

   public boolean hasStarted() {
      return this.processingStartTime() > 0L;
   }

   public boolean hasCompleted() {
      return this.incompleteJobs().isEmpty();
   }

   public long processingDelay() {
      return this.processingEndTime() - this.processingStartTime();
   }

   public long totalDelay() {
      return this.processingEndTime() - this.time().milliseconds();
   }

   public BatchInfo toBatchInfo() {
      return new BatchInfo(this.time(), this.streamIdToInputInfo(), this.submissionTime(), (Option)(this.hasStarted() ? new Some(BoxesRunTime.boxToLong(this.processingStartTime())) : .MODULE$), (Option)(this.hasCompleted() ? new Some(BoxesRunTime.boxToLong(this.processingEndTime())) : .MODULE$), ((IterableOnceOps)this.jobs().map((job) -> new Tuple2(BoxesRunTime.boxToInteger(job.outputOpId()), job.toOutputOperationInfo()))).toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public JobSet copy(final Time time, final Seq jobs, final Map streamIdToInputInfo) {
      return new JobSet(time, jobs, streamIdToInputInfo);
   }

   public Time copy$default$1() {
      return this.time();
   }

   public Seq copy$default$2() {
      return this.jobs();
   }

   public Map copy$default$3() {
      return this.streamIdToInputInfo();
   }

   public String productPrefix() {
      return "JobSet";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.time();
         }
         case 1 -> {
            return this.jobs();
         }
         case 2 -> {
            return this.streamIdToInputInfo();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JobSet;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "jobs";
         }
         case 2 -> {
            return "streamIdToInputInfo";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof JobSet) {
               label56: {
                  JobSet var4 = (JobSet)x$1;
                  Time var10000 = this.time();
                  Time var5 = var4.time();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Seq var8 = this.jobs();
                  Seq var6 = var4.jobs();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Map var9 = this.streamIdToInputInfo();
                  Map var7 = var4.streamIdToInputInfo();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   // $FF: synthetic method
   public static final void $anonfun$new$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Job job = (Job)x0$1._1();
         int i = x0$1._2$mcI$sp();
         job.setOutputOpId(i);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public JobSet(final Time time, final Seq jobs, final Map streamIdToInputInfo) {
      this.time = time;
      this.jobs = jobs;
      this.streamIdToInputInfo = streamIdToInputInfo;
      Product.$init$(this);
      this.incompleteJobs = new HashSet();
      this.submissionTime = System.currentTimeMillis();
      this.processingStartTime = -1L;
      this.processingEndTime = -1L;
      ((IterableOnceOps)jobs.zipWithIndex()).foreach((x0$1) -> {
         $anonfun$new$1(x0$1);
         return BoxedUnit.UNIT;
      });
      this.incompleteJobs().$plus$plus$eq(jobs);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
