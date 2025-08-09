package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005\u0015d!B\r\u001b\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\t\u0003!\u0011#Q\u0001\n}B\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t!\u0002\u0011\t\u0012)A\u0005\u000b\")\u0011\u000b\u0001C\u0001%\"9a\u000bAA\u0001\n\u00039\u0006b\u0002.\u0001#\u0003%\ta\u0017\u0005\bM\u0002\t\n\u0011\"\u0001h\u0011\u001dI\u0007!!A\u0005B)DqA\u001d\u0001\u0002\u0002\u0013\u0005a\bC\u0004t\u0001\u0005\u0005I\u0011\u0001;\t\u000fi\u0004\u0011\u0011!C!w\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003#\u0001\u0011\u0011!C!\u0003'A\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u000f)\t)CGA\u0001\u0012\u0003Q\u0012q\u0005\u0004\n3i\t\t\u0011#\u0001\u001b\u0003SAa!U\n\u0005\u0002\u0005\u0005\u0003\"CA\u000e'\u0005\u0005IQIA\u000f\u0011%\t\u0019eEA\u0001\n\u0003\u000b)\u0005C\u0005\u0002LM\t\t\u0011\"!\u0002N!I\u00111L\n\u0002\u0002\u0013%\u0011Q\f\u0002\r\u0015>\u00147)\u00198dK2dW\r\u001a\u0006\u00037q\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001cR\u0001A\u0012*[A\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0007C\u0001\u0016,\u001b\u0005Q\u0012B\u0001\u0017\u001b\u0005E!\u0015iR*dQ\u0016$W\u000f\\3s\u000bZ,g\u000e\u001e\t\u0003I9J!aL\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011G\u000f\b\u0003ear!aM\u001c\u000e\u0003QR!!\u000e\u001c\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AJ\u0005\u0003s\u0015\nq\u0001]1dW\u0006<W-\u0003\u0002<y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011(J\u0001\u0006U>\u0014\u0017\nZ\u000b\u0002\u007fA\u0011A\u0005Q\u0005\u0003\u0003\u0016\u00121!\u00138u\u0003\u0019QwNY%eA\u00051!/Z1t_:,\u0012!\u0012\t\u0004I\u0019C\u0015BA$&\u0005\u0019y\u0005\u000f^5p]B\u0011\u0011*\u0014\b\u0003\u0015.\u0003\"aM\u0013\n\u00051+\u0013A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001T\u0013\u0002\u000fI,\u0017m]8oA\u00051A(\u001b8jiz\"2a\u0015+V!\tQ\u0003\u0001C\u0003>\u000b\u0001\u0007q\bC\u0003D\u000b\u0001\u0007Q)\u0001\u0003d_BLHcA*Y3\"9QH\u0002I\u0001\u0002\u0004y\u0004bB\"\u0007!\u0003\u0005\r!R\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005a&FA ^W\u0005q\u0006CA0e\u001b\u0005\u0001'BA1c\u0003%)hn\u00195fG.,GM\u0003\u0002dK\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0015\u0004'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00015+\u0005\u0015k\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001l!\ta\u0017/D\u0001n\u0015\tqw.\u0001\u0003mC:<'\"\u00019\u0002\t)\fg/Y\u0005\u0003\u001d6\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002vqB\u0011AE^\u0005\u0003o\u0016\u00121!\u00118z\u0011\u001dI8\"!AA\u0002}\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001?\u0011\tu\f\t!^\u0007\u0002}*\u0011q0J\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0002}\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tI!a\u0004\u0011\u0007\u0011\nY!C\u0002\u0002\u000e\u0015\u0012qAQ8pY\u0016\fg\u000eC\u0004z\u001b\u0005\u0005\t\u0019A;\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004W\u0006U\u0001bB=\u000f\u0003\u0003\u0005\raP\u0001\tQ\u0006\u001c\bnQ8eKR\tq(\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\n\u0005\r\u0002bB=\u0012\u0003\u0003\u0005\r!^\u0001\r\u0015>\u00147)\u00198dK2dW\r\u001a\t\u0003UM\u0019RaEA\u0016\u0003o\u0001r!!\f\u00024}*5+\u0004\u0002\u00020)\u0019\u0011\u0011G\u0013\u0002\u000fI,h\u000e^5nK&!\u0011QGA\u0018\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003s\ty$\u0004\u0002\u0002<)\u0019\u0011QH8\u0002\u0005%|\u0017bA\u001e\u0002<Q\u0011\u0011qE\u0001\u0006CB\u0004H.\u001f\u000b\u0006'\u0006\u001d\u0013\u0011\n\u0005\u0006{Y\u0001\ra\u0010\u0005\u0006\u0007Z\u0001\r!R\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ty%a\u0016\u0011\t\u00112\u0015\u0011\u000b\t\u0006I\u0005Ms(R\u0005\u0004\u0003+*#A\u0002+va2,'\u0007\u0003\u0005\u0002Z]\t\t\u00111\u0001T\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003?\u00022\u0001\\A1\u0013\r\t\u0019'\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class JobCancelled implements DAGSchedulerEvent, Product, Serializable {
   private final int jobId;
   private final Option reason;

   public static Option unapply(final JobCancelled x$0) {
      return JobCancelled$.MODULE$.unapply(x$0);
   }

   public static JobCancelled apply(final int jobId, final Option reason) {
      return JobCancelled$.MODULE$.apply(jobId, reason);
   }

   public static Function1 tupled() {
      return JobCancelled$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JobCancelled$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int jobId() {
      return this.jobId;
   }

   public Option reason() {
      return this.reason;
   }

   public JobCancelled copy(final int jobId, final Option reason) {
      return new JobCancelled(jobId, reason);
   }

   public int copy$default$1() {
      return this.jobId();
   }

   public Option copy$default$2() {
      return this.reason();
   }

   public String productPrefix() {
      return "JobCancelled";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.jobId());
         }
         case 1 -> {
            return this.reason();
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
      return x$1 instanceof JobCancelled;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "jobId";
         }
         case 1 -> {
            return "reason";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.reason()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof JobCancelled) {
               JobCancelled var4 = (JobCancelled)x$1;
               if (this.jobId() == var4.jobId()) {
                  label44: {
                     Option var10000 = this.reason();
                     Option var5 = var4.reason();
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

   public JobCancelled(final int jobId, final Option reason) {
      this.jobId = jobId;
      this.reason = reason;
      Product.$init$(this);
   }
}
