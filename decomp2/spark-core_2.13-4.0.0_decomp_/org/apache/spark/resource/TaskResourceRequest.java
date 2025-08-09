package org.apache.spark.resource;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Evolving;
import scala.collection.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005m3A!\u0003\u0006\u0001'!Aa\u0005\u0001BC\u0002\u0013\u0005q\u0005\u0003\u00051\u0001\t\u0005\t\u0015!\u0003)\u0011!\t\u0004A!b\u0001\n\u0003\u0011\u0004\u0002\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011B\u001a\t\u000b]\u0002A\u0011\u0001\u001d\t\u000bu\u0002A\u0011\t \t\u000b\u001d\u0003A\u0011\t%\t\u000b1\u0003A\u0011I'\u0003'Q\u000b7o\u001b*fg>,(oY3SKF,Xm\u001d;\u000b\u0005-a\u0011\u0001\u0003:fg>,(oY3\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001)i\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0007CA\u000e$\u001d\ta\u0012E\u0004\u0002\u001eA5\taD\u0003\u0002 %\u00051AH]8pizJ\u0011aF\u0005\u0003EY\tq\u0001]1dW\u0006<W-\u0003\u0002%K\ta1+\u001a:jC2L'0\u00192mK*\u0011!EF\u0001\re\u0016\u001cx.\u001e:dK:\u000bW.Z\u000b\u0002QA\u0011\u0011&\f\b\u0003U-\u0002\"!\b\f\n\u000512\u0012A\u0002)sK\u0012,g-\u0003\u0002/_\t11\u000b\u001e:j]\u001eT!\u0001\f\f\u0002\u001bI,7o\\;sG\u0016t\u0015-\\3!\u0003\u0019\tWn\\;oiV\t1\u0007\u0005\u0002\u0016i%\u0011QG\u0006\u0002\u0007\t>,(\r\\3\u0002\u000f\u0005lw.\u001e8uA\u00051A(\u001b8jiz\"2!O\u001e=!\tQ\u0004!D\u0001\u000b\u0011\u00151S\u00011\u0001)\u0011\u0015\tT\u00011\u00014\u0003\u0019)\u0017/^1mgR\u0011qH\u0011\t\u0003+\u0001K!!\u0011\f\u0003\u000f\t{w\u000e\\3b]\")1I\u0002a\u0001\t\u0006\u0019qN\u00196\u0011\u0005U)\u0015B\u0001$\u0017\u0005\r\te._\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011\n\u0005\u0002\u0016\u0015&\u00111J\u0006\u0002\u0004\u0013:$\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003!B#\u0001A(\u0011\u0005A\u001bV\"A)\u000b\u0005Ic\u0011AC1o]>$\u0018\r^5p]&\u0011A+\u0015\u0002\t\u000bZ|GN^5oO\"\u001a\u0001AV-\u0011\u0005A;\u0016B\u0001-R\u0005\u0015\u0019\u0016N\\2fC\u0005Q\u0016!B\u001a/c9\u0002\u0004"
)
public class TaskResourceRequest implements Serializable {
   private final String resourceName;
   private final double amount;

   public String resourceName() {
      return this.resourceName;
   }

   public double amount() {
      return this.amount;
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof TaskResourceRequest var4)) {
         return false;
      } else {
         boolean var8;
         label36: {
            Class var10000 = var4.getClass();
            Class var5 = this.getClass();
            if (var10000 == null) {
               if (var5 != null) {
                  break label36;
               }
            } else if (!var10000.equals(var5)) {
               break label36;
            }

            String var7 = var4.resourceName();
            String var6 = this.resourceName();
            if (var7 == null) {
               if (var6 != null) {
                  break label36;
               }
            } else if (!var7.equals(var6)) {
               break label36;
            }

            if (var4.amount() == this.amount()) {
               var8 = true;
               return var8;
            }
         }

         var8 = false;
         return var8;
      }
   }

   public int hashCode() {
      return ((Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.resourceName(), BoxesRunTime.boxToDouble(this.amount())}))).hashCode();
   }

   public String toString() {
      String var10000 = this.resourceName();
      return "name: " + var10000 + ", amount: " + this.amount();
   }

   public TaskResourceRequest(final String resourceName, final double amount) {
      this.resourceName = resourceName;
      this.amount = amount;
      scala.Predef..MODULE$.assert(amount <= (double)1.0F || amount % (double)1 == (double)0, () -> "The resource amount " + this.amount() + " must be either <= 1.0, or a whole number.");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
