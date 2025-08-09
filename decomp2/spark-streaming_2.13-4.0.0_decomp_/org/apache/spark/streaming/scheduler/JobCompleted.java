package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd!B\r\u001b\u0001j!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u0011\u0003!\u0011#Q\u0001\n\u0005C\u0001\"\u0012\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u000f\")1\n\u0001C\u0001\u0019\"9\u0001\u000bAA\u0001\n\u0003\t\u0006b\u0002+\u0001#\u0003%\t!\u0016\u0005\bA\u0002\t\n\u0011\"\u0001b\u0011\u001d\u0019\u0007!!A\u0005B\u0011Dq!\u001c\u0001\u0002\u0002\u0013\u0005a\u000eC\u0004s\u0001\u0005\u0005I\u0011A:\t\u000fe\u0004\u0011\u0011!C!u\"I\u00111\u0001\u0001\u0002\u0002\u0013\u0005\u0011Q\u0001\u0005\n\u0003\u001f\u0001\u0011\u0011!C!\u0003#A\u0011\"!\u0006\u0001\u0003\u0003%\t%a\u0006\t\u0013\u0005e\u0001!!A\u0005B\u0005m\u0001\"CA\u000f\u0001\u0005\u0005I\u0011IA\u0010\u000f)\t\u0019CGA\u0001\u0012\u0003Q\u0012Q\u0005\u0004\n3i\t\t\u0011#\u0001\u001b\u0003OAaaS\n\u0005\u0002\u0005}\u0002\"CA\r'\u0005\u0005IQIA\u000e\u0011%\t\teEA\u0001\n\u0003\u000b\u0019\u0005C\u0005\u0002JM\t\t\u0011\"!\u0002L!I\u0011QL\n\u0002\u0002\u0013%\u0011q\f\u0002\r\u0015>\u00147i\\7qY\u0016$X\r\u001a\u0006\u00037q\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005uq\u0012!C:ue\u0016\fW.\u001b8h\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7#\u0002\u0001&W=\u0012\u0004C\u0001\u0014*\u001b\u00059#\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):#AB!osJ+g\r\u0005\u0002-[5\t!$\u0003\u0002/5\t\t\"j\u001c2TG\",G-\u001e7fe\u00163XM\u001c;\u0011\u0005\u0019\u0002\u0014BA\u0019(\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\r\u001f\u000f\u0005QRdBA\u001b:\u001b\u00051$BA\u001c9\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0015\n\u0005m:\u0013a\u00029bG.\fw-Z\u0005\u0003{y\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aO\u0014\u0002\u0007)|'-F\u0001B!\ta#)\u0003\u0002D5\t\u0019!j\u001c2\u0002\t)|'\rI\u0001\u000eG>l\u0007\u000f\\3uK\u0012$\u0016.\\3\u0016\u0003\u001d\u0003\"A\n%\n\u0005%;#\u0001\u0002'p]\u001e\fabY8na2,G/\u001a3US6,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004\u001b:{\u0005C\u0001\u0017\u0001\u0011\u0015yT\u00011\u0001B\u0011\u0015)U\u00011\u0001H\u0003\u0011\u0019w\u000e]=\u0015\u00075\u00136\u000bC\u0004@\rA\u0005\t\u0019A!\t\u000f\u00153\u0001\u0013!a\u0001\u000f\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001,+\u0005\u0005;6&\u0001-\u0011\u0005esV\"\u0001.\u000b\u0005mc\u0016!C;oG\",7m[3e\u0015\tiv%\u0001\u0006b]:|G/\u0019;j_:L!a\u0018.\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003\tT#aR,\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005)\u0007C\u00014l\u001b\u00059'B\u00015j\u0003\u0011a\u0017M\\4\u000b\u0003)\fAA[1wC&\u0011An\u001a\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003=\u0004\"A\n9\n\u0005E<#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001;x!\t1S/\u0003\u0002wO\t\u0019\u0011I\\=\t\u000fa\\\u0011\u0011!a\u0001_\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a\u001f\t\u0004y~$X\"A?\u000b\u0005y<\u0013AC2pY2,7\r^5p]&\u0019\u0011\u0011A?\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u000f\ti\u0001E\u0002'\u0003\u0013I1!a\u0003(\u0005\u001d\u0011un\u001c7fC:Dq\u0001_\u0007\u0002\u0002\u0003\u0007A/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA3\u0002\u0014!9\u0001PDA\u0001\u0002\u0004y\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003=\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002K\u00061Q-];bYN$B!a\u0002\u0002\"!9\u00010EA\u0001\u0002\u0004!\u0018\u0001\u0004&pE\u000e{W\u000e\u001d7fi\u0016$\u0007C\u0001\u0017\u0014'\u0015\u0019\u0012\u0011FA\u001b!\u001d\tY#!\rB\u000f6k!!!\f\u000b\u0007\u0005=r%A\u0004sk:$\u0018.\\3\n\t\u0005M\u0012Q\u0006\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u001c\u0003{i!!!\u000f\u000b\u0007\u0005m\u0012.\u0001\u0002j_&\u0019Q(!\u000f\u0015\u0005\u0005\u0015\u0012!B1qa2LH#B'\u0002F\u0005\u001d\u0003\"B \u0017\u0001\u0004\t\u0005\"B#\u0017\u0001\u00049\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u001b\nI\u0006E\u0003'\u0003\u001f\n\u0019&C\u0002\u0002R\u001d\u0012aa\u00149uS>t\u0007#\u0002\u0014\u0002V\u0005;\u0015bAA,O\t1A+\u001e9mKJB\u0001\"a\u0017\u0018\u0003\u0003\u0005\r!T\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA1!\r1\u00171M\u0005\u0004\u0003K:'AB(cU\u0016\u001cG\u000f"
)
public class JobCompleted implements JobSchedulerEvent, Product, Serializable {
   private final Job job;
   private final long completedTime;

   public static Option unapply(final JobCompleted x$0) {
      return JobCompleted$.MODULE$.unapply(x$0);
   }

   public static JobCompleted apply(final Job job, final long completedTime) {
      return JobCompleted$.MODULE$.apply(job, completedTime);
   }

   public static Function1 tupled() {
      return JobCompleted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JobCompleted$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Job job() {
      return this.job;
   }

   public long completedTime() {
      return this.completedTime;
   }

   public JobCompleted copy(final Job job, final long completedTime) {
      return new JobCompleted(job, completedTime);
   }

   public Job copy$default$1() {
      return this.job();
   }

   public long copy$default$2() {
      return this.completedTime();
   }

   public String productPrefix() {
      return "JobCompleted";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.job();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.completedTime());
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
      return x$1 instanceof JobCompleted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "job";
         }
         case 1 -> {
            return "completedTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.job()));
      var1 = Statics.mix(var1, Statics.longHash(this.completedTime()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof JobCompleted) {
               JobCompleted var4 = (JobCompleted)x$1;
               if (this.completedTime() == var4.completedTime()) {
                  label44: {
                     Job var10000 = this.job();
                     Job var5 = var4.job();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
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

   public JobCompleted(final Job job, final long completedTime) {
      this.job = job;
      this.completedTime = completedTime;
      Product.$init$(this);
   }
}
