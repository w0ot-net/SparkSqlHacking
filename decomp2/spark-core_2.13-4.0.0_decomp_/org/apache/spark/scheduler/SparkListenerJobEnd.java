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
   bytes = "\u0006\u0005\u0005\u0015e\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005L\u0001\tE\t\u0015!\u0003I\u0011!a\u0005A!f\u0001\n\u0003i\u0005\u0002C)\u0001\u0005#\u0005\u000b\u0011\u0002(\t\u000bI\u0003A\u0011A*\t\u000fa\u0003\u0011\u0011!C\u00013\"9Q\fAI\u0001\n\u0003q\u0006bB5\u0001#\u0003%\tA\u001b\u0005\bY\u0002\t\n\u0011\"\u0001n\u0011\u001dy\u0007!!A\u0005BADq!\u001f\u0001\u0002\u0002\u0013\u0005\u0011\tC\u0004{\u0001\u0005\u0005I\u0011A>\t\u0013\u0005\r\u0001!!A\u0005B\u0005\u0015\u0001\"CA\n\u0001\u0005\u0005I\u0011AA\u000b\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131\u0006\u0005\n\u0003[\u0001\u0011\u0011!C!\u0003_9\u0011\"a\u0010\u001e\u0003\u0003E\t!!\u0011\u0007\u0011qi\u0012\u0011!E\u0001\u0003\u0007BaA\u0015\f\u0005\u0002\u0005m\u0003\"CA\u0015-\u0005\u0005IQIA\u0016\u0011%\tiFFA\u0001\n\u0003\u000by\u0006C\u0005\u0002hY\t\t\u0011\"!\u0002j!I\u00111\u0010\f\u0002\u0002\u0013%\u0011Q\u0010\u0002\u0014'B\f'o\u001b'jgR,g.\u001a:K_\n,e\u000e\u001a\u0006\u0003=}\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001c\u0001aE\u0003\u0001O5\nD\u0007\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013F\u0001\u0004B]f\u0014VM\u001a\t\u0003]=j\u0011!H\u0005\u0003au\u0011!c\u00159be.d\u0015n\u001d;f]\u0016\u0014XI^3oiB\u0011\u0001FM\u0005\u0003g%\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00026{9\u0011ag\u000f\b\u0003oij\u0011\u0001\u000f\u0006\u0003s\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0016\n\u0005qJ\u0013a\u00029bG.\fw-Z\u0005\u0003}}\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001P\u0015\u0002\u000b)|'-\u00133\u0016\u0003\t\u0003\"\u0001K\"\n\u0005\u0011K#aA%oi\u00061!n\u001c2JI\u0002\nA\u0001^5nKV\t\u0001\n\u0005\u0002)\u0013&\u0011!*\u000b\u0002\u0005\u0019>tw-A\u0003uS6,\u0007%A\u0005k_\n\u0014Vm];miV\ta\n\u0005\u0002/\u001f&\u0011\u0001+\b\u0002\n\u0015>\u0014'+Z:vYR\f!B[8c%\u0016\u001cX\u000f\u001c;!\u0003\u0019a\u0014N\\5u}Q!A+\u0016,X!\tq\u0003\u0001C\u0003A\u000f\u0001\u0007!\tC\u0003G\u000f\u0001\u0007\u0001\nC\u0003M\u000f\u0001\u0007a*\u0001\u0003d_BLH\u0003\u0002+[7rCq\u0001\u0011\u0005\u0011\u0002\u0003\u0007!\tC\u0004G\u0011A\u0005\t\u0019\u0001%\t\u000f1C\u0001\u0013!a\u0001\u001d\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A0+\u0005\t\u00037&A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017!C;oG\",7m[3e\u0015\t1\u0017&\u0001\u0006b]:|G/\u0019;j_:L!\u0001[2\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003-T#\u0001\u00131\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\taN\u000b\u0002OA\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u001d\t\u0003e^l\u0011a\u001d\u0006\u0003iV\fA\u0001\\1oO*\ta/\u0001\u0003kCZ\f\u0017B\u0001=t\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001?\u0000!\tAS0\u0003\u0002\u007fS\t\u0019\u0011I\\=\t\u0011\u0005\u0005a\"!AA\u0002\t\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0004!\u0015\tI!a\u0004}\u001b\t\tYAC\u0002\u0002\u000e%\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\t\"a\u0003\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003/\ti\u0002E\u0002)\u00033I1!a\u0007*\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u0001\u0011\u0003\u0003\u0005\r\u0001`\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002r\u0003GA\u0001\"!\u0001\u0012\u0003\u0003\u0005\rAQ\u0001\tQ\u0006\u001c\bnQ8eKR\t!)\u0001\u0005u_N#(/\u001b8h)\u0005\t\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0018\u0005E\u0002\u0002CA\u0001)\u0005\u0005\t\u0019\u0001?)\u0007\u0001\t)\u0004\u0005\u0003\u00028\u0005mRBAA\u001d\u0015\t1w$\u0003\u0003\u0002>\u0005e\"\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017aE*qCJ\\G*[:uK:,'OS8c\u000b:$\u0007C\u0001\u0018\u0017'\u00151\u0012QIA)!!\t9%!\u0014C\u0011:#VBAA%\u0015\r\tY%K\u0001\beVtG/[7f\u0013\u0011\ty%!\u0013\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002T\u0005eSBAA+\u0015\r\t9&^\u0001\u0003S>L1APA+)\t\t\t%A\u0003baBd\u0017\u0010F\u0004U\u0003C\n\u0019'!\u001a\t\u000b\u0001K\u0002\u0019\u0001\"\t\u000b\u0019K\u0002\u0019\u0001%\t\u000b1K\u0002\u0019\u0001(\u0002\u000fUt\u0017\r\u001d9msR!\u00111NA<!\u0015A\u0013QNA9\u0013\r\ty'\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r!\n\u0019H\u0011%O\u0013\r\t)(\u000b\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u0005e$$!AA\u0002Q\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\bE\u0002s\u0003\u0003K1!a!t\u0005\u0019y%M[3di\u0002"
)
public class SparkListenerJobEnd implements SparkListenerEvent, Product, Serializable {
   private final int jobId;
   private final long time;
   private final JobResult jobResult;

   public static Option unapply(final SparkListenerJobEnd x$0) {
      return SparkListenerJobEnd$.MODULE$.unapply(x$0);
   }

   public static SparkListenerJobEnd apply(final int jobId, final long time, final JobResult jobResult) {
      return SparkListenerJobEnd$.MODULE$.apply(jobId, time, jobResult);
   }

   public static Function1 tupled() {
      return SparkListenerJobEnd$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerJobEnd$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public int jobId() {
      return this.jobId;
   }

   public long time() {
      return this.time;
   }

   public JobResult jobResult() {
      return this.jobResult;
   }

   public SparkListenerJobEnd copy(final int jobId, final long time, final JobResult jobResult) {
      return new SparkListenerJobEnd(jobId, time, jobResult);
   }

   public int copy$default$1() {
      return this.jobId();
   }

   public long copy$default$2() {
      return this.time();
   }

   public JobResult copy$default$3() {
      return this.jobResult();
   }

   public String productPrefix() {
      return "SparkListenerJobEnd";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.jobId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 2 -> {
            return this.jobResult();
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
      return x$1 instanceof SparkListenerJobEnd;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "jobId";
         }
         case 1 -> {
            return "time";
         }
         case 2 -> {
            return "jobResult";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.jobId());
      var1 = Statics.mix(var1, Statics.longHash(this.time()));
      var1 = Statics.mix(var1, Statics.anyHash(this.jobResult()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof SparkListenerJobEnd) {
               SparkListenerJobEnd var4 = (SparkListenerJobEnd)x$1;
               if (this.jobId() == var4.jobId() && this.time() == var4.time()) {
                  label48: {
                     JobResult var10000 = this.jobResult();
                     JobResult var5 = var4.jobResult();
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

   public SparkListenerJobEnd(final int jobId, final long time, final JobResult jobResult) {
      this.jobId = jobId;
      this.time = time;
      this.jobResult = jobResult;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
