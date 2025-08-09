package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.math.Numeric.LongIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005eh\u0001B\u0015+\u0001VB\u0001b\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t#\u0002\u0011\t\u0012)A\u0005\u001b\"A!\u000b\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005d\u0001\tE\t\u0015!\u0003U\u0011!!\u0007A!f\u0001\n\u0003)\u0007\u0002C5\u0001\u0005#\u0005\u000b\u0011\u00024\t\u0011)\u0004!Q3A\u0005\u0002-D\u0001b\u001c\u0001\u0003\u0012\u0003\u0006I\u0001\u001c\u0005\ta\u0002\u0011)\u001a!C\u0001W\"A\u0011\u000f\u0001B\tB\u0003%A\u000e\u0003\u0005s\u0001\tU\r\u0011\"\u0001t\u0011!A\bA!E!\u0002\u0013!\b\"B=\u0001\t\u0003Q\bBBA\u0003\u0001\u0011\u00051\u000e\u0003\u0004\u0002\b\u0001!\ta\u001b\u0005\u0007\u0003\u0013\u0001A\u0011A6\t\r\u0005-\u0001\u0001\"\u0001f\u0011%\ti\u0001AA\u0001\n\u0003\ty\u0001C\u0005\u0002\u001e\u0001\t\n\u0011\"\u0001\u0002 !I\u0011Q\u0007\u0001\u0012\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003w\u0001\u0011\u0013!C\u0001\u0003{A\u0011\"!\u0011\u0001#\u0003%\t!a\u0011\t\u0013\u0005\u001d\u0003!%A\u0005\u0002\u0005\r\u0003\"CA%\u0001E\u0005I\u0011AA&\u0011%\ty\u0005AA\u0001\n\u0003\n\t\u0006C\u0005\u0002d\u0001\t\t\u0011\"\u0001\u0002f!I\u0011q\r\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u000e\u0005\n\u0003k\u0002\u0011\u0011!C!\u0003oB\u0011\"!\"\u0001\u0003\u0003%\t!a\"\t\u0013\u0005E\u0005!!A\u0005B\u0005M\u0005\"CAL\u0001\u0005\u0005I\u0011IAM\u0011%\tY\nAA\u0001\n\u0003\ni\nC\u0005\u0002 \u0002\t\t\u0011\"\u0011\u0002\"\u001eI\u0011\u0011\u0017\u0016\u0002\u0002#\u0005\u00111\u0017\u0004\tS)\n\t\u0011#\u0001\u00026\"1\u0011p\tC\u0001\u0003\u001bD\u0011\"a'$\u0003\u0003%)%!(\t\u0013\u0005=7%!A\u0005\u0002\u0006E\u0007\"CApG\u0005\u0005I\u0011QAq\u0011%\tyoIA\u0001\n\u0013\t\tPA\u0005CCR\u001c\u0007.\u00138g_*\u00111\u0006L\u0001\ng\u000eDW\rZ;mKJT!!\f\u0018\u0002\u0013M$(/Z1nS:<'BA\u00181\u0003\u0015\u0019\b/\u0019:l\u0015\t\t$'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002g\u0005\u0019qN]4\u0004\u0001M!\u0001A\u000e\u001f@!\t9$(D\u00019\u0015\u0005I\u0014!B:dC2\f\u0017BA\u001e9\u0005\u0019\te.\u001f*fMB\u0011q'P\u0005\u0003}a\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002A\u0011:\u0011\u0011I\u0012\b\u0003\u0005\u0016k\u0011a\u0011\u0006\u0003\tR\na\u0001\u0010:p_Rt\u0014\"A\u001d\n\u0005\u001dC\u0014a\u00029bG.\fw-Z\u0005\u0003\u0013*\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!a\u0012\u001d\u0002\u0013\t\fGo\u00195US6,W#A'\u0011\u00059{U\"\u0001\u0017\n\u0005Ac#\u0001\u0002+j[\u0016\f!BY1uG\"$\u0016.\\3!\u0003M\u0019HO]3b[&#Gk\\%oaV$\u0018J\u001c4p+\u0005!\u0006\u0003B+Z9~s!AV,\u0011\u0005\tC\u0014B\u0001-9\u0003\u0019\u0001&/\u001a3fM&\u0011!l\u0017\u0002\u0004\u001b\u0006\u0004(B\u0001-9!\t9T,\u0003\u0002_q\t\u0019\u0011J\u001c;\u0011\u0005\u0001\fW\"\u0001\u0016\n\u0005\tT#aD*ue\u0016\fW.\u00138qkRLeNZ8\u0002)M$(/Z1n\u0013\u0012$v.\u00138qkRLeNZ8!\u00039\u0019XOY7jgNLwN\u001c+j[\u0016,\u0012A\u001a\t\u0003o\u001dL!\u0001\u001b\u001d\u0003\t1{gnZ\u0001\u0010gV\u0014W.[:tS>tG+[7fA\u0005\u0019\u0002O]8dKN\u001c\u0018N\\4Ti\u0006\u0014H\u000fV5nKV\tA\u000eE\u00028[\u001aL!A\u001c\u001d\u0003\r=\u0003H/[8o\u0003Q\u0001(o\\2fgNLgnZ*uCJ$H+[7fA\u0005\t\u0002O]8dKN\u001c\u0018N\\4F]\u0012$\u0016.\\3\u0002%A\u0014xnY3tg&tw-\u00128e)&lW\rI\u0001\u0015_V$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]&sgm\\:\u0016\u0003Q\u0004B!V-]kB\u0011\u0001M^\u0005\u0003o*\u00121cT;uaV$x\n]3sCRLwN\\%oM>\fQc\\;uaV$x\n]3sCRLwN\\%oM>\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\nwrlhp`A\u0001\u0003\u0007\u0001\"\u0001\u0019\u0001\t\u000b-k\u0001\u0019A'\t\u000bIk\u0001\u0019\u0001+\t\u000b\u0011l\u0001\u0019\u00014\t\u000b)l\u0001\u0019\u00017\t\u000bAl\u0001\u0019\u00017\t\u000bIl\u0001\u0019\u0001;\u0002\u001fM\u001c\u0007.\u001a3vY&tw\rR3mCf\fq\u0002\u001d:pG\u0016\u001c8/\u001b8h\t\u0016d\u0017-_\u0001\u000bi>$\u0018\r\u001c#fY\u0006L\u0018A\u00038v[J+7m\u001c:eg\u0006!1m\u001c9z)5Y\u0018\u0011CA\n\u0003+\t9\"!\u0007\u0002\u001c!91J\u0005I\u0001\u0002\u0004i\u0005b\u0002*\u0013!\u0003\u0005\r\u0001\u0016\u0005\bIJ\u0001\n\u00111\u0001g\u0011\u001dQ'\u0003%AA\u00021Dq\u0001\u001d\n\u0011\u0002\u0003\u0007A\u000eC\u0004s%A\u0005\t\u0019\u0001;\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\u0005\u0016\u0004\u001b\u0006\r2FAA\u0013!\u0011\t9#!\r\u000e\u0005\u0005%\"\u0002BA\u0016\u0003[\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005=\u0002(\u0001\u0006b]:|G/\u0019;j_:LA!a\r\u0002*\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011\u0011\b\u0016\u0004)\u0006\r\u0012AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003\u007fQ3AZA\u0012\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\"!!\u0012+\u00071\f\u0019#\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%mU\u0011\u0011Q\n\u0016\u0004i\u0006\r\u0012!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002TA!\u0011QKA0\u001b\t\t9F\u0003\u0003\u0002Z\u0005m\u0013\u0001\u00027b]\u001eT!!!\u0018\u0002\t)\fg/Y\u0005\u0005\u0003C\n9F\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u00029\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA6\u0003c\u00022aNA7\u0013\r\ty\u0007\u000f\u0002\u0004\u0003:L\b\u0002CA:7\u0005\u0005\t\u0019\u0001/\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\b\u0005\u0004\u0002|\u0005\u0005\u00151N\u0007\u0003\u0003{R1!a 9\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0007\u000biH\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAE\u0003\u001f\u00032aNAF\u0013\r\ti\t\u000f\u0002\b\u0005>|G.Z1o\u0011%\t\u0019(HA\u0001\u0002\u0004\tY'\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA*\u0003+C\u0001\"a\u001d\u001f\u0003\u0003\u0005\r\u0001X\u0001\tQ\u0006\u001c\bnQ8eKR\tA,\u0001\u0005u_N#(/\u001b8h)\t\t\u0019&\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0013\u000b\u0019\u000bC\u0005\u0002t\u0005\n\t\u00111\u0001\u0002l!\u001a\u0001!a*\u0011\t\u0005%\u0016QV\u0007\u0003\u0003WS1!a\f/\u0013\u0011\ty+a+\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u0013\t\u000bGo\u00195J]\u001a|\u0007C\u00011$'\u0015\u0019\u0013qWAb!-\tI,a0N)\u001adG\u000e^>\u000e\u0005\u0005m&bAA_q\u00059!/\u001e8uS6,\u0017\u0002BAa\u0003w\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c87!\u0011\t)-a3\u000e\u0005\u0005\u001d'\u0002BAe\u00037\n!![8\n\u0007%\u000b9\r\u0006\u0002\u00024\u0006)\u0011\r\u001d9msRi10a5\u0002V\u0006]\u0017\u0011\\An\u0003;DQa\u0013\u0014A\u00025CQA\u0015\u0014A\u0002QCQ\u0001\u001a\u0014A\u0002\u0019DQA\u001b\u0014A\u00021DQ\u0001\u001d\u0014A\u00021DQA\u001d\u0014A\u0002Q\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002d\u0006-\b\u0003B\u001cn\u0003K\u0004\u0012bNAt\u001bR3G\u000e\u001c;\n\u0007\u0005%\bH\u0001\u0004UkBdWM\u000e\u0005\t\u0003[<\u0013\u0011!a\u0001w\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005M\b\u0003BA+\u0003kLA!a>\u0002X\t1qJ\u00196fGR\u0004"
)
public class BatchInfo implements Product, Serializable {
   private final Time batchTime;
   private final Map streamIdToInputInfo;
   private final long submissionTime;
   private final Option processingStartTime;
   private final Option processingEndTime;
   private final Map outputOperationInfos;

   public static Option unapply(final BatchInfo x$0) {
      return BatchInfo$.MODULE$.unapply(x$0);
   }

   public static BatchInfo apply(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final Option processingStartTime, final Option processingEndTime, final Map outputOperationInfos) {
      return BatchInfo$.MODULE$.apply(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, outputOperationInfos);
   }

   public static Function1 tupled() {
      return BatchInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BatchInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time batchTime() {
      return this.batchTime;
   }

   public Map streamIdToInputInfo() {
      return this.streamIdToInputInfo;
   }

   public long submissionTime() {
      return this.submissionTime;
   }

   public Option processingStartTime() {
      return this.processingStartTime;
   }

   public Option processingEndTime() {
      return this.processingEndTime;
   }

   public Map outputOperationInfos() {
      return this.outputOperationInfos;
   }

   public Option schedulingDelay() {
      return this.processingStartTime().map((JFunction1.mcJJ.sp)(x$1) -> x$1 - this.submissionTime());
   }

   public Option processingDelay() {
      return this.processingEndTime().zip(this.processingStartTime()).map((x) -> BoxesRunTime.boxToLong($anonfun$processingDelay$1(x)));
   }

   public Option totalDelay() {
      return this.schedulingDelay().zip(this.processingDelay()).map((x) -> BoxesRunTime.boxToLong($anonfun$totalDelay$1(x)));
   }

   public long numRecords() {
      return BoxesRunTime.unboxToLong(((IterableOnceOps)this.streamIdToInputInfo().values().map((x$2) -> BoxesRunTime.boxToLong($anonfun$numRecords$1(x$2)))).sum(.MODULE$));
   }

   public BatchInfo copy(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final Option processingStartTime, final Option processingEndTime, final Map outputOperationInfos) {
      return new BatchInfo(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, outputOperationInfos);
   }

   public Time copy$default$1() {
      return this.batchTime();
   }

   public Map copy$default$2() {
      return this.streamIdToInputInfo();
   }

   public long copy$default$3() {
      return this.submissionTime();
   }

   public Option copy$default$4() {
      return this.processingStartTime();
   }

   public Option copy$default$5() {
      return this.processingEndTime();
   }

   public Map copy$default$6() {
      return this.outputOperationInfos();
   }

   public String productPrefix() {
      return "BatchInfo";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.batchTime();
         }
         case 1 -> {
            return this.streamIdToInputInfo();
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.submissionTime());
         }
         case 3 -> {
            return this.processingStartTime();
         }
         case 4 -> {
            return this.processingEndTime();
         }
         case 5 -> {
            return this.outputOperationInfos();
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
      return x$1 instanceof BatchInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "batchTime";
         }
         case 1 -> {
            return "streamIdToInputInfo";
         }
         case 2 -> {
            return "submissionTime";
         }
         case 3 -> {
            return "processingStartTime";
         }
         case 4 -> {
            return "processingEndTime";
         }
         case 5 -> {
            return "outputOperationInfos";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.batchTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.streamIdToInputInfo()));
      var1 = Statics.mix(var1, Statics.longHash(this.submissionTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.processingStartTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.processingEndTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.outputOperationInfos()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof BatchInfo) {
               BatchInfo var4 = (BatchInfo)x$1;
               if (this.submissionTime() == var4.submissionTime()) {
                  label76: {
                     Time var10000 = this.batchTime();
                     Time var5 = var4.batchTime();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     Map var10 = this.streamIdToInputInfo();
                     Map var6 = var4.streamIdToInputInfo();
                     if (var10 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var10.equals(var6)) {
                        break label76;
                     }

                     Option var11 = this.processingStartTime();
                     Option var7 = var4.processingStartTime();
                     if (var11 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var11.equals(var7)) {
                        break label76;
                     }

                     var11 = this.processingEndTime();
                     Option var8 = var4.processingEndTime();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var11.equals(var8)) {
                        break label76;
                     }

                     Map var13 = this.outputOperationInfos();
                     Map var9 = var4.outputOperationInfos();
                     if (var13 == null) {
                        if (var9 != null) {
                           break label76;
                        }
                     } else if (!var13.equals(var9)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
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

   // $FF: synthetic method
   public static final long $anonfun$processingDelay$1(final Tuple2 x) {
      return x._1$mcJ$sp() - x._2$mcJ$sp();
   }

   // $FF: synthetic method
   public static final long $anonfun$totalDelay$1(final Tuple2 x) {
      return x._1$mcJ$sp() + x._2$mcJ$sp();
   }

   // $FF: synthetic method
   public static final long $anonfun$numRecords$1(final StreamInputInfo x$2) {
      return x$2.numRecords();
   }

   public BatchInfo(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final Option processingStartTime, final Option processingEndTime, final Map outputOperationInfos) {
      this.batchTime = batchTime;
      this.streamIdToInputInfo = streamIdToInputInfo;
      this.submissionTime = submissionTime;
      this.processingStartTime = processingStartTime;
      this.processingEndTime = processingEndTime;
      this.outputOperationInfos = outputOperationInfos;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
