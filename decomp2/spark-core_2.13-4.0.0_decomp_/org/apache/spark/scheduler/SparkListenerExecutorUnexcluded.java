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
   bytes = "\u0006\u0005\u0005]d\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005N\u0001\tE\t\u0015!\u0003F\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u001d\u0019\u0006!!A\u0005\u0002QCqa\u0016\u0001\u0012\u0002\u0013\u0005\u0001\fC\u0004d\u0001E\u0005I\u0011\u00013\t\u000f\u0019\u0004\u0011\u0011!C!O\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002;\u0001\u0003\u0003%\t!\u001e\u0005\bw\u0002\t\t\u0011\"\u0011}\u0011%\t9\u0001AA\u0001\n\u0003\tI\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003;\u0001\u0011\u0011!C!\u0003?A\u0011\"!\t\u0001\u0003\u0003%\t%a\t\b\u0013\u0005M\"$!A\t\u0002\u0005Ub\u0001C\r\u001b\u0003\u0003E\t!a\u000e\t\r9\u001bB\u0011AA(\u0011%\tibEA\u0001\n\u000b\ny\u0002C\u0005\u0002RM\t\t\u0011\"!\u0002T!I\u0011\u0011L\n\u0002\u0002\u0013\u0005\u00151\f\u0005\n\u0003[\u001a\u0012\u0011!C\u0005\u0003_\u0012qd\u00159be.d\u0015n\u001d;f]\u0016\u0014X\t_3dkR|'/\u00168fq\u000edW\u000fZ3e\u0015\tYB$A\u0005tG\",G-\u001e7fe*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001!#FL\u0019\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\r\u0005s\u0017PU3g!\tYC&D\u0001\u001b\u0013\ti#D\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bCA\u00130\u0013\t\u0001dEA\u0004Qe>$Wo\u0019;\u0011\u0005IRdBA\u001a9\u001d\t!t'D\u00016\u0015\t1$%\u0001\u0004=e>|GOP\u0005\u0002O%\u0011\u0011HJ\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:M\u0005!A/[7f+\u0005y\u0004CA\u0013A\u0013\t\teE\u0001\u0003M_:<\u0017!\u0002;j[\u0016\u0004\u0013AC3yK\u000e,Ho\u001c:JIV\tQ\t\u0005\u0002G\u0015:\u0011q\t\u0013\t\u0003i\u0019J!!\u0013\u0014\u0002\rA\u0013X\rZ3g\u0013\tYEJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0013\u001a\n1\"\u001a=fGV$xN]%eA\u00051A(\u001b8jiz\"2\u0001U)S!\tY\u0003\u0001C\u0003>\u000b\u0001\u0007q\bC\u0003D\u000b\u0001\u0007Q)\u0001\u0003d_BLHc\u0001)V-\"9QH\u0002I\u0001\u0002\u0004y\u0004bB\"\u0007!\u0003\u0005\r!R\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005I&FA [W\u0005Y\u0006C\u0001/b\u001b\u0005i&B\u00010`\u0003%)hn\u00195fG.,GM\u0003\u0002aM\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\tl&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A3+\u0005\u0015S\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001i!\tIg.D\u0001k\u0015\tYG.\u0001\u0003mC:<'\"A7\u0002\t)\fg/Y\u0005\u0003\u0017*\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u001d\t\u0003KIL!a\u001d\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005YL\bCA\u0013x\u0013\tAhEA\u0002B]fDqA_\u0006\u0002\u0002\u0003\u0007\u0011/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002{B!a0a\u0001w\u001b\u0005y(bAA\u0001M\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0007\u0005\u0015qP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0006\u0003#\u00012!JA\u0007\u0013\r\tyA\n\u0002\b\u0005>|G.Z1o\u0011\u001dQX\"!AA\u0002Y\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0001.a\u0006\t\u000fit\u0011\u0011!a\u0001c\u0006A\u0001.Y:i\u0007>$W\rF\u0001r\u0003!!xn\u0015;sS:<G#\u00015\u0002\r\u0015\fX/\u00197t)\u0011\tY!!\n\t\u000fi\f\u0012\u0011!a\u0001m\"\u001a\u0001!!\u000b\u0011\t\u0005-\u0012qF\u0007\u0003\u0003[Q!\u0001\u0019\u000f\n\t\u0005E\u0012Q\u0006\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001 'B\f'o\u001b'jgR,g.\u001a:Fq\u0016\u001cW\u000f^8s+:,\u0007p\u00197vI\u0016$\u0007CA\u0016\u0014'\u0015\u0019\u0012\u0011HA#!\u001d\tY$!\u0011@\u000bBk!!!\u0010\u000b\u0007\u0005}b%A\u0004sk:$\u0018.\\3\n\t\u0005\r\u0013Q\b\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA$\u0003\u001bj!!!\u0013\u000b\u0007\u0005-C.\u0001\u0002j_&\u00191(!\u0013\u0015\u0005\u0005U\u0012!B1qa2LH#\u0002)\u0002V\u0005]\u0003\"B\u001f\u0017\u0001\u0004y\u0004\"B\"\u0017\u0001\u0004)\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003;\nI\u0007E\u0003&\u0003?\n\u0019'C\u0002\u0002b\u0019\u0012aa\u00149uS>t\u0007#B\u0013\u0002f}*\u0015bAA4M\t1A+\u001e9mKJB\u0001\"a\u001b\u0018\u0003\u0003\u0005\r\u0001U\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA9!\rI\u00171O\u0005\u0004\u0003kR'AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerExecutorUnexcluded implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String executorId;

   public static Option unapply(final SparkListenerExecutorUnexcluded x$0) {
      return SparkListenerExecutorUnexcluded$.MODULE$.unapply(x$0);
   }

   public static SparkListenerExecutorUnexcluded apply(final long time, final String executorId) {
      return SparkListenerExecutorUnexcluded$.MODULE$.apply(time, executorId);
   }

   public static Function1 tupled() {
      return SparkListenerExecutorUnexcluded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerExecutorUnexcluded$.MODULE$.curried();
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

   public String executorId() {
      return this.executorId;
   }

   public SparkListenerExecutorUnexcluded copy(final long time, final String executorId) {
      return new SparkListenerExecutorUnexcluded(time, executorId);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.executorId();
   }

   public String productPrefix() {
      return "SparkListenerExecutorUnexcluded";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 1 -> {
            return this.executorId();
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
      return x$1 instanceof SparkListenerExecutorUnexcluded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "executorId";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkListenerExecutorUnexcluded) {
               SparkListenerExecutorUnexcluded var4 = (SparkListenerExecutorUnexcluded)x$1;
               if (this.time() == var4.time()) {
                  label44: {
                     String var10000 = this.executorId();
                     String var5 = var4.executorId();
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

   public SparkListenerExecutorUnexcluded(final long time, final String executorId) {
      this.time = time;
      this.executorId = executorId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
