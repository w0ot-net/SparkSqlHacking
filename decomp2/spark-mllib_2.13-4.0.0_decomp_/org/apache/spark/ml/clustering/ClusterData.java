package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c\u0001B\r\u001b\t\u0016B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005{!A\u0011\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005J\u0001\tE\t\u0015!\u0003D\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001d\u0001\u0006!!A\u0005\u0002ECq\u0001\u0016\u0001\u0012\u0002\u0013\u0005Q\u000bC\u0004a\u0001E\u0005I\u0011A1\t\u000f\r\u0004\u0011\u0011!C!I\"9Q\u000eAA\u0001\n\u0003a\u0004b\u00028\u0001\u0003\u0003%\ta\u001c\u0005\bk\u0002\t\t\u0011\"\u0011w\u0011\u001di\b!!A\u0005\u0002yD\u0011\"a\u0002\u0001\u0003\u0003%\t%!\u0003\t\u0013\u00055\u0001!!A\u0005B\u0005=\u0001\"CA\t\u0001\u0005\u0005I\u0011IA\n\u0011%\t)\u0002AA\u0001\n\u0003\n9bB\u0005\u0002\u001ci\t\t\u0011#\u0003\u0002\u001e\u0019A\u0011DGA\u0001\u0012\u0013\ty\u0002\u0003\u0004K'\u0011\u0005\u0011q\u0007\u0005\n\u0003#\u0019\u0012\u0011!C#\u0003'A\u0011\"!\u000f\u0014\u0003\u0003%\t)a\u000f\t\u0013\u0005\u00053#!A\u0005\u0002\u0006\r\u0003\"CA+'\u0005\u0005I\u0011BA,\u0005-\u0019E.^:uKJ$\u0015\r^1\u000b\u0005ma\u0012AC2mkN$XM]5oO*\u0011QDH\u0001\u0003[2T!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\u0002\u0001'\u0011\u0001a\u0005L\u0018\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\r\u0005s\u0017PU3g!\t9S&\u0003\u0002/Q\t9\u0001K]8ek\u000e$\bC\u0001\u00199\u001d\t\tdG\u0004\u00023k5\t1G\u0003\u00025I\u00051AH]8pizJ\u0011!K\u0005\u0003o!\nq\u0001]1dW\u0006<W-\u0003\u0002:u\ta1+\u001a:jC2L'0\u00192mK*\u0011q\u0007K\u0001\u000bG2,8\u000f^3s\u0013\u0012DX#A\u001f\u0011\u0005\u001dr\u0014BA )\u0005\rIe\u000e^\u0001\fG2,8\u000f^3s\u0013\u0012D\b%A\u0007dYV\u001cH/\u001a:DK:$XM]\u000b\u0002\u0007B\u0011AiR\u0007\u0002\u000b*\u0011a\tH\u0001\u0007Y&t\u0017\r\\4\n\u0005!+%A\u0002,fGR|'/\u0001\bdYV\u001cH/\u001a:DK:$XM\u001d\u0011\u0002\rqJg.\u001b;?)\raej\u0014\t\u0003\u001b\u0002i\u0011A\u0007\u0005\u0006w\u0015\u0001\r!\u0010\u0005\u0006\u0003\u0016\u0001\raQ\u0001\u0005G>\u0004\u0018\u0010F\u0002M%NCqa\u000f\u0004\u0011\u0002\u0003\u0007Q\bC\u0004B\rA\u0005\t\u0019A\"\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\taK\u000b\u0002>/.\n\u0001\f\u0005\u0002Z=6\t!L\u0003\u0002\\9\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003;\"\n!\"\u00198o_R\fG/[8o\u0013\ty&LA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001cU\t\u0019u+A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002KB\u0011am[\u0007\u0002O*\u0011\u0001.[\u0001\u0005Y\u0006twMC\u0001k\u0003\u0011Q\u0017M^1\n\u00051<'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005A\u001c\bCA\u0014r\u0013\t\u0011\bFA\u0002B]fDq\u0001^\u0006\u0002\u0002\u0003\u0007Q(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002oB\u0019\u0001p\u001f9\u000e\u0003eT!A\u001f\u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002}s\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\ry\u0018Q\u0001\t\u0004O\u0005\u0005\u0011bAA\u0002Q\t9!i\\8mK\u0006t\u0007b\u0002;\u000e\u0003\u0003\u0005\r\u0001]\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002f\u0003\u0017Aq\u0001\u001e\b\u0002\u0002\u0003\u0007Q(\u0001\u0005iCND7i\u001c3f)\u0005i\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0015\fa!Z9vC2\u001cHcA@\u0002\u001a!9A/EA\u0001\u0002\u0004\u0001\u0018aC\"mkN$XM\u001d#bi\u0006\u0004\"!T\n\u0014\u000bM\t\t#!\f\u0011\u000f\u0005\r\u0012\u0011F\u001fD\u00196\u0011\u0011Q\u0005\u0006\u0004\u0003OA\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003W\t)CA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\f\u000265\u0011\u0011\u0011\u0007\u0006\u0004\u0003gI\u0017AA5p\u0013\rI\u0014\u0011\u0007\u000b\u0003\u0003;\tQ!\u00199qYf$R\u0001TA\u001f\u0003\u007fAQa\u000f\fA\u0002uBQ!\u0011\fA\u0002\r\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002F\u0005E\u0003#B\u0014\u0002H\u0005-\u0013bAA%Q\t1q\n\u001d;j_:\u0004RaJA'{\rK1!a\u0014)\u0005\u0019!V\u000f\u001d7fe!A\u00111K\f\u0002\u0002\u0003\u0007A*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0017\u0011\u0007\u0019\fY&C\u0002\u0002^\u001d\u0014aa\u00142kK\u000e$\b"
)
public class ClusterData implements Product, Serializable {
   private final int clusterIdx;
   private final Vector clusterCenter;

   public static Option unapply(final ClusterData x$0) {
      return ClusterData$.MODULE$.unapply(x$0);
   }

   public static ClusterData apply(final int clusterIdx, final Vector clusterCenter) {
      return ClusterData$.MODULE$.apply(clusterIdx, clusterCenter);
   }

   public static Function1 tupled() {
      return ClusterData$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ClusterData$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int clusterIdx() {
      return this.clusterIdx;
   }

   public Vector clusterCenter() {
      return this.clusterCenter;
   }

   public ClusterData copy(final int clusterIdx, final Vector clusterCenter) {
      return new ClusterData(clusterIdx, clusterCenter);
   }

   public int copy$default$1() {
      return this.clusterIdx();
   }

   public Vector copy$default$2() {
      return this.clusterCenter();
   }

   public String productPrefix() {
      return "ClusterData";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.clusterIdx());
         }
         case 1 -> {
            return this.clusterCenter();
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
      return x$1 instanceof ClusterData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "clusterIdx";
         }
         case 1 -> {
            return "clusterCenter";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.clusterIdx());
      var1 = Statics.mix(var1, Statics.anyHash(this.clusterCenter()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof ClusterData) {
               ClusterData var4 = (ClusterData)x$1;
               if (this.clusterIdx() == var4.clusterIdx()) {
                  label44: {
                     Vector var10000 = this.clusterCenter();
                     Vector var5 = var4.clusterCenter();
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

   public ClusterData(final int clusterIdx, final Vector clusterCenter) {
      this.clusterIdx = clusterIdx;
      this.clusterCenter = clusterCenter;
      Product.$init$(this);
   }
}
