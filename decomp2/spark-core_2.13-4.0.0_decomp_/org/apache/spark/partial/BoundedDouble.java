package org.apache.spark.partial;

import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000593A!\u0004\b\u0001/!Aa\u0004\u0001BC\u0002\u0013\u0005q\u0004\u0003\u0005$\u0001\t\u0005\t\u0015!\u0003!\u0011!!\u0003A!b\u0001\n\u0003y\u0002\u0002C\u0013\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0011\t\u0011\u0019\u0002!Q1A\u0005\u0002}A\u0001b\n\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\tQ\u0001\u0011)\u0019!C\u0001?!A\u0011\u0006\u0001B\u0001B\u0003%\u0001\u0005C\u0003+\u0001\u0011\u00051\u0006C\u00033\u0001\u0011\u00053\u0007C\u0003@\u0001\u0011\u0005\u0003\tC\u0003E\u0001\u0011\u0005SIA\u0007C_VtG-\u001a3E_V\u0014G.\u001a\u0006\u0003\u001fA\tq\u0001]1si&\fGN\u0003\u0002\u0012%\u0005)1\u000f]1sW*\u00111\u0003F\u0001\u0007CB\f7\r[3\u000b\u0003U\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\r\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g\u0003\u0011iW-\u00198\u0016\u0003\u0001\u0002\"!G\u0011\n\u0005\tR\"A\u0002#pk\ndW-A\u0003nK\u0006t\u0007%\u0001\u0006d_:4\u0017\u000eZ3oG\u0016\f1bY8oM&$WM\\2fA\u0005\u0019An\\<\u0002\t1|w\u000fI\u0001\u0005Q&<\u0007.A\u0003iS\u001eD\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006Y9z\u0003'\r\t\u0003[\u0001i\u0011A\u0004\u0005\u0006=%\u0001\r\u0001\t\u0005\u0006I%\u0001\r\u0001\t\u0005\u0006M%\u0001\r\u0001\t\u0005\u0006Q%\u0001\r\u0001I\u0001\ti>\u001cFO]5oOR\tA\u0007\u0005\u00026y9\u0011aG\u000f\t\u0003oii\u0011\u0001\u000f\u0006\u0003sY\ta\u0001\u0010:p_Rt\u0014BA\u001e\u001b\u0003\u0019\u0001&/\u001a3fM&\u0011QH\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005mR\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0005\u0003\"!\u0007\"\n\u0005\rS\"aA%oi\u00061Q-];bYN$\"AR%\u0011\u0005e9\u0015B\u0001%\u001b\u0005\u001d\u0011un\u001c7fC:DQA\u0013\u0007A\u0002-\u000bA\u0001\u001e5biB\u0011\u0011\u0004T\u0005\u0003\u001bj\u00111!\u00118z\u0001"
)
public class BoundedDouble {
   private final double mean;
   private final double confidence;
   private final double low;
   private final double high;

   public double mean() {
      return this.mean;
   }

   public double confidence() {
      return this.confidence;
   }

   public double low() {
      return this.low;
   }

   public double high() {
      return this.high;
   }

   public String toString() {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("[%.3f, %.3f]"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(this.low()), BoxesRunTime.boxToDouble(this.high())}));
   }

   public int hashCode() {
      return Double.hashCode(this.mean()) ^ Double.hashCode(this.confidence()) ^ Double.hashCode(this.low()) ^ Double.hashCode(this.high());
   }

   public boolean equals(final Object that) {
      if (!(that instanceof BoundedDouble var4)) {
         return false;
      } else {
         return this.mean() == var4.mean() && this.confidence() == var4.confidence() && this.low() == var4.low() && this.high() == var4.high();
      }
   }

   public BoundedDouble(final double mean, final double confidence, final double low, final double high) {
      this.mean = mean;
      this.confidence = confidence;
      this.low = low;
      this.high = high;
   }
}
