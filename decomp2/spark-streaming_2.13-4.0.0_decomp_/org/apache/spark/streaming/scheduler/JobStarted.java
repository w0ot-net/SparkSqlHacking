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
   bytes = "\u0006\u0005\u0005\u001dd!B\r\u001b\u0001j!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u0011\u0003!\u0011#Q\u0001\n\u0005C\u0001\"\u0012\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u000f\")1\n\u0001C\u0001\u0019\"9\u0001\u000bAA\u0001\n\u0003\t\u0006b\u0002+\u0001#\u0003%\t!\u0016\u0005\bA\u0002\t\n\u0011\"\u0001b\u0011\u001d\u0019\u0007!!A\u0005B\u0011Dq!\u001c\u0001\u0002\u0002\u0013\u0005a\u000eC\u0004s\u0001\u0005\u0005I\u0011A:\t\u000fe\u0004\u0011\u0011!C!u\"I\u00111\u0001\u0001\u0002\u0002\u0013\u0005\u0011Q\u0001\u0005\n\u0003\u001f\u0001\u0011\u0011!C!\u0003#A\u0011\"!\u0006\u0001\u0003\u0003%\t%a\u0006\t\u0013\u0005e\u0001!!A\u0005B\u0005m\u0001\"CA\u000f\u0001\u0005\u0005I\u0011IA\u0010\u000f)\t\u0019CGA\u0001\u0012\u0003Q\u0012Q\u0005\u0004\n3i\t\t\u0011#\u0001\u001b\u0003OAaaS\n\u0005\u0002\u0005}\u0002\"CA\r'\u0005\u0005IQIA\u000e\u0011%\t\teEA\u0001\n\u0003\u000b\u0019\u0005C\u0005\u0002JM\t\t\u0011\"!\u0002L!I\u0011QL\n\u0002\u0002\u0013%\u0011q\f\u0002\u000b\u0015>\u00147\u000b^1si\u0016$'BA\u000e\u001d\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001e=\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003?\u0001\nQa\u001d9be.T!!\t\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0013aA8sON)\u0001!J\u00160eA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1\u0011I\\=SK\u001a\u0004\"\u0001L\u0017\u000e\u0003iI!A\f\u000e\u0003#){'mU2iK\u0012,H.\u001a:Fm\u0016tG\u000f\u0005\u0002'a%\u0011\u0011g\n\u0002\b!J|G-^2u!\t\u0019DH\u0004\u00025u9\u0011Q'O\u0007\u0002m)\u0011q\u0007O\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001&\u0003\u0002<O\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tYt%A\u0002k_\n,\u0012!\u0011\t\u0003Y\tK!a\u0011\u000e\u0003\u0007){'-\u0001\u0003k_\n\u0004\u0013!C:uCJ$H+[7f+\u00059\u0005C\u0001\u0014I\u0013\tIuE\u0001\u0003M_:<\u0017AC:uCJ$H+[7fA\u00051A(\u001b8jiz\"2!\u0014(P!\ta\u0003\u0001C\u0003@\u000b\u0001\u0007\u0011\tC\u0003F\u000b\u0001\u0007q)\u0001\u0003d_BLHcA'S'\"9qH\u0002I\u0001\u0002\u0004\t\u0005bB#\u0007!\u0003\u0005\raR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00051&FA!XW\u0005A\u0006CA-_\u001b\u0005Q&BA.]\u0003%)hn\u00195fG.,GM\u0003\u0002^O\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005}S&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00012+\u0005\u001d;\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001f!\t17.D\u0001h\u0015\tA\u0017.\u0001\u0003mC:<'\"\u00016\u0002\t)\fg/Y\u0005\u0003Y\u001e\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A8\u0011\u0005\u0019\u0002\u0018BA9(\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t!x\u000f\u0005\u0002'k&\u0011ao\n\u0002\u0004\u0003:L\bb\u0002=\f\u0003\u0003\u0005\ra\\\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003m\u00042\u0001`@u\u001b\u0005i(B\u0001@(\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0004\u0003\u0003i(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0002\u0002\u000eA\u0019a%!\u0003\n\u0007\u0005-qEA\u0004C_>dW-\u00198\t\u000fal\u0011\u0011!a\u0001i\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r)\u00171\u0003\u0005\bq:\t\t\u00111\u0001p\u0003!A\u0017m\u001d5D_\u0012,G#A8\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!Z\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u001d\u0011\u0011\u0005\u0005\bqF\t\t\u00111\u0001u\u0003)QuNY*uCJ$X\r\u001a\t\u0003YM\u0019RaEA\u0015\u0003k\u0001r!a\u000b\u00022\u0005;U*\u0004\u0002\u0002.)\u0019\u0011qF\u0014\u0002\u000fI,h\u000e^5nK&!\u00111GA\u0017\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003o\ti$\u0004\u0002\u0002:)\u0019\u00111H5\u0002\u0005%|\u0017bA\u001f\u0002:Q\u0011\u0011QE\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u001b\u0006\u0015\u0013q\t\u0005\u0006\u007fY\u0001\r!\u0011\u0005\u0006\u000bZ\u0001\raR\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti%!\u0017\u0011\u000b\u0019\ny%a\u0015\n\u0007\u0005EsE\u0001\u0004PaRLwN\u001c\t\u0006M\u0005U\u0013iR\u0005\u0004\u0003/:#A\u0002+va2,'\u0007\u0003\u0005\u0002\\]\t\t\u00111\u0001N\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003C\u00022AZA2\u0013\r\t)g\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class JobStarted implements JobSchedulerEvent, Product, Serializable {
   private final Job job;
   private final long startTime;

   public static Option unapply(final JobStarted x$0) {
      return JobStarted$.MODULE$.unapply(x$0);
   }

   public static JobStarted apply(final Job job, final long startTime) {
      return JobStarted$.MODULE$.apply(job, startTime);
   }

   public static Function1 tupled() {
      return JobStarted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JobStarted$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Job job() {
      return this.job;
   }

   public long startTime() {
      return this.startTime;
   }

   public JobStarted copy(final Job job, final long startTime) {
      return new JobStarted(job, startTime);
   }

   public Job copy$default$1() {
      return this.job();
   }

   public long copy$default$2() {
      return this.startTime();
   }

   public String productPrefix() {
      return "JobStarted";
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
            return BoxesRunTime.boxToLong(this.startTime());
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
      return x$1 instanceof JobStarted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "job";
         }
         case 1 -> {
            return "startTime";
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
      var1 = Statics.mix(var1, Statics.longHash(this.startTime()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof JobStarted) {
               JobStarted var4 = (JobStarted)x$1;
               if (this.startTime() == var4.startTime()) {
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

   public JobStarted(final Job job, final long startTime) {
      this.job = job;
      this.startTime = startTime;
      Product.$init$(this);
   }
}
