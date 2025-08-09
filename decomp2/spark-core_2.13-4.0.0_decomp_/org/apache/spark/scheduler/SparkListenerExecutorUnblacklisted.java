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
   bytes = "\u0006\u0005\u0005-e\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005N\u0001\tE\t\u0015!\u0003F\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u001d\u0019\u0006!!A\u0005\u0002QCqa\u0016\u0001\u0012\u0002\u0013\u0005\u0001\fC\u0004d\u0001E\u0005I\u0011\u00013\t\u000f\u0019\u0004\u0011\u0011!C!O\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002;\u0001\u0003\u0003%\t!\u001e\u0005\bw\u0002\t\t\u0011\"\u0011}\u0011%\t9\u0001AA\u0001\n\u0003\tI\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003;\u0001\u0011\u0011!C!\u0003?A\u0011\"!\t\u0001\u0003\u0003%\t%a\t\b\u0013\u0005\u001d#$!A\t\u0002\u0005%c\u0001C\r\u001b\u0003\u0003E\t!a\u0013\t\r9\u001bB\u0011AA2\u0011%\tibEA\u0001\n\u000b\ny\u0002C\u0005\u0002fM\t\t\u0011\"!\u0002h!I\u0011QN\n\u0002\u0002\u0013\u0005\u0015q\u000e\u0005\n\u0003\u0003\u001b\u0012\u0011!C\u0005\u0003\u0007\u0013!e\u00159be.d\u0015n\u001d;f]\u0016\u0014X\t_3dkR|'/\u00168cY\u0006\u001c7\u000e\\5ti\u0016$'BA\u000e\u001d\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019R\u0001\u0001\u0013+]E\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0007CA\u0016-\u001b\u0005Q\u0012BA\u0017\u001b\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005\u0015z\u0013B\u0001\u0019'\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\r\u001e\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c#\u0003\u0019a$o\\8u}%\tq%\u0003\u0002:M\u00059\u0001/Y2lC\u001e,\u0017BA\u001e=\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tId%\u0001\u0003uS6,W#A \u0011\u0005\u0015\u0002\u0015BA!'\u0005\u0011auN\\4\u0002\u000bQLW.\u001a\u0011\u0002\u0015\u0015DXmY;u_JLE-F\u0001F!\t1%J\u0004\u0002H\u0011B\u0011AGJ\u0005\u0003\u0013\u001a\na\u0001\u0015:fI\u00164\u0017BA&M\u0005\u0019\u0019FO]5oO*\u0011\u0011JJ\u0001\fKb,7-\u001e;pe&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004!F\u0013\u0006CA\u0016\u0001\u0011\u0015iT\u00011\u0001@\u0011\u0015\u0019U\u00011\u0001F\u0003\u0011\u0019w\u000e]=\u0015\u0007A+f\u000bC\u0004>\rA\u0005\t\u0019A \t\u000f\r3\u0001\u0013!a\u0001\u000b\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A-+\u0005}R6&A.\u0011\u0005q\u000bW\"A/\u000b\u0005y{\u0016!C;oG\",7m[3e\u0015\t\u0001g%\u0001\u0006b]:|G/\u0019;j_:L!AY/\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003\u0015T#!\u0012.\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\u0007CA5o\u001b\u0005Q'BA6m\u0003\u0011a\u0017M\\4\u000b\u00035\fAA[1wC&\u00111J[\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002cB\u0011QE]\u0005\u0003g\u001a\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A^=\u0011\u0005\u0015:\u0018B\u0001='\u0005\r\te.\u001f\u0005\bu.\t\t\u00111\u0001r\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tQ\u0010\u0005\u0003\u007f\u0003\u00071X\"A@\u000b\u0007\u0005\u0005a%\u0001\u0006d_2dWm\u0019;j_:L1!!\u0002\u0000\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005-\u0011\u0011\u0003\t\u0004K\u00055\u0011bAA\bM\t9!i\\8mK\u0006t\u0007b\u0002>\u000e\u0003\u0003\u0005\rA^\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002i\u0003/AqA\u001f\b\u0002\u0002\u0003\u0007\u0011/\u0001\u0005iCND7i\u001c3f)\u0005\t\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003!\fa!Z9vC2\u001cH\u0003BA\u0006\u0003KAqA_\t\u0002\u0002\u0003\u0007a\u000fK\u0006\u0001\u0003S\ty#!\r\u00026\u0005]\u0002cA\u0013\u0002,%\u0019\u0011Q\u0006\u0014\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005M\u0012aK;tK\u0002\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J,f.\u001a=dYV$W\r\u001a\u0011j]N$X-\u00193\u0002\u000bMLgnY3\"\u0005\u0005e\u0012!B\u001a/c9\u0002\u0004f\u0001\u0001\u0002>A!\u0011qHA\"\u001b\t\t\tE\u0003\u0002a9%!\u0011QIA!\u00051!UM^3m_B,'/\u00119j\u0003\t\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J,fN\u00197bG.d\u0017n\u001d;fIB\u00111fE\n\u0006'\u00055\u0013\u0011\f\t\b\u0003\u001f\n)fP#Q\u001b\t\t\tFC\u0002\u0002T\u0019\nqA];oi&lW-\u0003\u0003\u0002X\u0005E#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u00111LA1\u001b\t\tiFC\u0002\u0002`1\f!![8\n\u0007m\ni\u0006\u0006\u0002\u0002J\u0005)\u0011\r\u001d9msR)\u0001+!\u001b\u0002l!)QH\u0006a\u0001\u007f!)1I\u0006a\u0001\u000b\u00069QO\\1qa2LH\u0003BA9\u0003{\u0002R!JA:\u0003oJ1!!\u001e'\u0005\u0019y\u0005\u000f^5p]B)Q%!\u001f@\u000b&\u0019\u00111\u0010\u0014\u0003\rQ+\b\u000f\\33\u0011!\tyhFA\u0001\u0002\u0004\u0001\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0011\t\u0004S\u0006\u001d\u0015bAAEU\n1qJ\u00196fGR\u0004"
)
public class SparkListenerExecutorUnblacklisted implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String executorId;

   public static Option unapply(final SparkListenerExecutorUnblacklisted x$0) {
      return SparkListenerExecutorUnblacklisted$.MODULE$.unapply(x$0);
   }

   public static SparkListenerExecutorUnblacklisted apply(final long time, final String executorId) {
      return SparkListenerExecutorUnblacklisted$.MODULE$.apply(time, executorId);
   }

   public static Function1 tupled() {
      return SparkListenerExecutorUnblacklisted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerExecutorUnblacklisted$.MODULE$.curried();
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

   public SparkListenerExecutorUnblacklisted copy(final long time, final String executorId) {
      return new SparkListenerExecutorUnblacklisted(time, executorId);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.executorId();
   }

   public String productPrefix() {
      return "SparkListenerExecutorUnblacklisted";
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
      return x$1 instanceof SparkListenerExecutorUnblacklisted;
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
            if (x$1 instanceof SparkListenerExecutorUnblacklisted) {
               SparkListenerExecutorUnblacklisted var4 = (SparkListenerExecutorUnblacklisted)x$1;
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

   public SparkListenerExecutorUnblacklisted(final long time, final String executorId) {
      this.time = time;
      this.executorId = executorId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
