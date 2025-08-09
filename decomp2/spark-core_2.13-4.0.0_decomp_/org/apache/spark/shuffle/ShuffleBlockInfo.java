package org.apache.spark.shuffle;

import java.io.Serializable;
import org.apache.spark.annotation.Experimental;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed\u0001B\r\u001b\u0001\u000eB\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t}\u0001\u0011\t\u0012)A\u0005w!Aq\b\u0001BK\u0002\u0013\u0005\u0001\t\u0003\u0005E\u0001\tE\t\u0015!\u0003B\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u0015Y\u0005\u0001\"\u0011M\u0011\u001d)\u0006!!A\u0005\u0002YCq!\u0017\u0001\u0012\u0002\u0013\u0005!\fC\u0004f\u0001E\u0005I\u0011\u00014\t\u000f!\u0004\u0011\u0011!C!S\"9\u0011\u000fAA\u0001\n\u0003Q\u0004b\u0002:\u0001\u0003\u0003%\ta\u001d\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011%\t\u0019\u0001AA\u0001\n\u0003\t)\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0011\u0002\u0012!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\n\u00033\u0001\u0011\u0011!C!\u000379\u0011\"a\u000b\u001b\u0003\u0003E\t!!\f\u0007\u0011eQ\u0012\u0011!E\u0001\u0003_Aa!R\n\u0005\u0002\u0005\u001d\u0003\u0002C&\u0014\u0003\u0003%)%!\u0013\t\u0013\u0005-3#!A\u0005\u0002\u00065\u0003\"CA*'\u0005\u0005I\u0011QA+\u0011%\t9gEA\u0001\n\u0013\tIG\u0001\tTQV4g\r\\3CY>\u001c7.\u00138g_*\u00111\u0004H\u0001\bg\",hM\u001a7f\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0011RS\u0006\u0005\u0002&Q5\taEC\u0001(\u0003\u0015\u00198-\u00197b\u0013\tIcE\u0001\u0004B]f\u0014VM\u001a\t\u0003K-J!\u0001\f\u0014\u0003\u000fA\u0013x\u000eZ;diB\u0011aF\u000e\b\u0003_Qr!\u0001M\u001a\u000e\u0003ER!A\r\u0012\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0013BA\u001b'\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000e\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005U2\u0013!C:ik\u001a4G.Z%e+\u0005Y\u0004CA\u0013=\u0013\tidEA\u0002J]R\f!b\u001d5vM\u001adW-\u00133!\u0003\u0015i\u0017\r]%e+\u0005\t\u0005CA\u0013C\u0013\t\u0019eE\u0001\u0003M_:<\u0017AB7ba&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004\u000f&S\u0005C\u0001%\u0001\u001b\u0005Q\u0002\"B\u001d\u0006\u0001\u0004Y\u0004\"B \u0006\u0001\u0004\t\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00035\u0003\"A\u0014*\u000f\u0005=\u0003\u0006C\u0001\u0019'\u0013\t\tf%\u0001\u0004Qe\u0016$WMZ\u0005\u0003'R\u0013aa\u0015;sS:<'BA)'\u0003\u0011\u0019w\u000e]=\u0015\u0007\u001d;\u0006\fC\u0004:\u000fA\u0005\t\u0019A\u001e\t\u000f}:\u0001\u0013!a\u0001\u0003\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A.+\u0005mb6&A/\u0011\u0005y\u001bW\"A0\u000b\u0005\u0001\f\u0017!C;oG\",7m[3e\u0015\t\u0011g%\u0001\u0006b]:|G/\u0019;j_:L!\u0001Z0\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003\u001dT#!\u0011/\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Q\u0007CA6q\u001b\u0005a'BA7o\u0003\u0011a\u0017M\\4\u000b\u0003=\fAA[1wC&\u00111\u000b\\\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t!x\u000f\u0005\u0002&k&\u0011aO\n\u0002\u0004\u0003:L\bb\u0002=\r\u0003\u0003\u0005\raO\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003m\u00042\u0001`@u\u001b\u0005i(B\u0001@'\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0004\u0003\u0003i(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0002\u0002\u000eA\u0019Q%!\u0003\n\u0007\u0005-aEA\u0004C_>dW-\u00198\t\u000fat\u0011\u0011!a\u0001i\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rQ\u00171\u0003\u0005\bq>\t\t\u00111\u0001<\u0003!A\u0017m\u001d5D_\u0012,G#A\u001e\u0002\r\u0015\fX/\u00197t)\u0011\t9!!\b\t\u000fa\f\u0012\u0011!a\u0001i\"\u001a\u0001!!\t\u0011\t\u0005\r\u0012qE\u0007\u0003\u0003KQ!A\u0019\u000f\n\t\u0005%\u0012Q\u0005\u0002\r\u000bb\u0004XM]5nK:$\u0018\r\\\u0001\u0011'\",hM\u001a7f\u00052|7m[%oM>\u0004\"\u0001S\n\u0014\u000bM\t\t$!\u0010\u0011\u000f\u0005M\u0012\u0011H\u001eB\u000f6\u0011\u0011Q\u0007\u0006\u0004\u0003o1\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003w\t)DA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\u0010\u0002F5\u0011\u0011\u0011\t\u0006\u0004\u0003\u0007r\u0017AA5p\u0013\r9\u0014\u0011\t\u000b\u0003\u0003[!\u0012A[\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u000f\u0006=\u0013\u0011\u000b\u0005\u0006sY\u0001\ra\u000f\u0005\u0006\u007fY\u0001\r!Q\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9&a\u0019\u0011\u000b\u0015\nI&!\u0018\n\u0007\u0005mcE\u0001\u0004PaRLwN\u001c\t\u0006K\u0005}3(Q\u0005\u0004\u0003C2#A\u0002+va2,'\u0007\u0003\u0005\u0002f]\t\t\u00111\u0001H\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003W\u00022a[A7\u0013\r\ty\u0007\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class ShuffleBlockInfo implements Product, Serializable {
   private final int shuffleId;
   private final long mapId;

   public static Option unapply(final ShuffleBlockInfo x$0) {
      return ShuffleBlockInfo$.MODULE$.unapply(x$0);
   }

   public static ShuffleBlockInfo apply(final int shuffleId, final long mapId) {
      return ShuffleBlockInfo$.MODULE$.apply(shuffleId, mapId);
   }

   public static Function1 tupled() {
      return ShuffleBlockInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleBlockInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public long mapId() {
      return this.mapId;
   }

   public String toString() {
      int var10000 = this.shuffleId();
      return "migrate_shuffle_" + var10000 + "_" + this.mapId();
   }

   public ShuffleBlockInfo copy(final int shuffleId, final long mapId) {
      return new ShuffleBlockInfo(shuffleId, mapId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public long copy$default$2() {
      return this.mapId();
   }

   public String productPrefix() {
      return "ShuffleBlockInfo";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.mapId());
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
      return x$1 instanceof ShuffleBlockInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         case 1 -> {
            return "mapId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.shuffleId());
      var1 = Statics.mix(var1, Statics.longHash(this.mapId()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof ShuffleBlockInfo) {
               ShuffleBlockInfo var4 = (ShuffleBlockInfo)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.mapId() == var4.mapId() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ShuffleBlockInfo(final int shuffleId, final long mapId) {
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      Product.$init$(this);
   }
}
