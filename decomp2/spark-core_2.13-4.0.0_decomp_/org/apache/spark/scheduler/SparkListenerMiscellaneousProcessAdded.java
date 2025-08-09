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
   bytes = "\u0006\u0005\u0005\u0005f\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003I\u0011!\t\u0006A!f\u0001\n\u0003\u0011\u0006\u0002\u0003,\u0001\u0005#\u0005\u000b\u0011B*\t\u000b]\u0003A\u0011\u0001-\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAI\u0001\n\u0003\u0019\u0007b\u00028\u0001#\u0003%\ta\u001c\u0005\bc\u0002\t\n\u0011\"\u0001s\u0011\u001d!\b!!A\u0005BUDq! \u0001\u0002\u0002\u0013\u0005a\u0010C\u0005\u0002\u0006\u0001\t\t\u0011\"\u0001\u0002\b!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013Q\u0003\u0005\n\u0003G\u0001\u0011\u0011!C\u0001\u0003KA\u0011\"a\f\u0001\u0003\u0003%\t%!\r\t\u0013\u0005U\u0002!!A\u0005B\u0005]\u0002\"CA\u001d\u0001\u0005\u0005I\u0011IA\u001e\u0011%\ti\u0004AA\u0001\n\u0003\nydB\u0005\u0002\\u\t\t\u0011#\u0001\u0002^\u0019AA$HA\u0001\u0012\u0003\ty\u0006\u0003\u0004X-\u0011\u0005\u0011q\u000f\u0005\n\u0003s1\u0012\u0011!C#\u0003wA\u0011\"!\u001f\u0017\u0003\u0003%\t)a\u001f\t\u0013\u0005\re#!A\u0005\u0002\u0006\u0015\u0005\"CAL-\u0005\u0005I\u0011BAM\u0005\u0019\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe6K7oY3mY\u0006tWm\\;t!J|7-Z:t\u0003\u0012$W\r\u001a\u0006\u0003=}\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001c\u0001aE\u0003\u0001O5\nD\u0007\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013F\u0001\u0004B]f\u0014VM\u001a\t\u0003]=j\u0011!H\u0005\u0003au\u0011!c\u00159be.d\u0015n\u001d;f]\u0016\u0014XI^3oiB\u0011\u0001FM\u0005\u0003g%\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00026{9\u0011ag\u000f\b\u0003oij\u0011\u0001\u000f\u0006\u0003s\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0016\n\u0005qJ\u0013a\u00029bG.\fw-Z\u0005\u0003}}\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001P\u0015\u0002\tQLW.Z\u000b\u0002\u0005B\u0011\u0001fQ\u0005\u0003\t&\u0012A\u0001T8oO\u0006)A/[7fA\u0005I\u0001O]8dKN\u001c\u0018\nZ\u000b\u0002\u0011B\u0011\u0011*\u0014\b\u0003\u0015.\u0003\"aN\u0015\n\u00051K\u0013A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001T\u0015\u0002\u0015A\u0014xnY3tg&#\u0007%\u0001\u0003j]\u001a|W#A*\u0011\u00059\"\u0016BA+\u001e\u0005mi\u0015n]2fY2\fg.Z8vgB\u0013xnY3tg\u0012+G/Y5mg\u0006)\u0011N\u001c4pA\u00051A(\u001b8jiz\"B!\u0017.\\9B\u0011a\u0006\u0001\u0005\u0006\u0001\u001e\u0001\rA\u0011\u0005\u0006\r\u001e\u0001\r\u0001\u0013\u0005\u0006#\u001e\u0001\raU\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003Z?\u0002\f\u0007b\u0002!\t!\u0003\u0005\rA\u0011\u0005\b\r\"\u0001\n\u00111\u0001I\u0011\u001d\t\u0006\u0002%AA\u0002M\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001eU\t\u0011UmK\u0001g!\t9G.D\u0001i\u0015\tI'.A\u0005v]\u000eDWmY6fI*\u00111.K\u0001\u000bC:tw\u000e^1uS>t\u0017BA7i\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005\u0001(F\u0001%f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\u0012a\u001d\u0016\u0003'\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001<\u0011\u0005]dX\"\u0001=\u000b\u0005eT\u0018\u0001\u00027b]\u001eT\u0011a_\u0001\u0005U\u00064\u0018-\u0003\u0002Oq\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\u0010E\u0002)\u0003\u0003I1!a\u0001*\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tI!a\u0004\u0011\u0007!\nY!C\u0002\u0002\u000e%\u00121!\u00118z\u0011!\t\tBDA\u0001\u0002\u0004y\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0018A1\u0011\u0011DA\u0010\u0003\u0013i!!a\u0007\u000b\u0007\u0005u\u0011&\u0001\u0006d_2dWm\u0019;j_:LA!!\t\u0002\u001c\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9#!\f\u0011\u0007!\nI#C\u0002\u0002,%\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u0012A\t\t\u00111\u0001\u0002\n\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r1\u00181\u0007\u0005\t\u0003#\t\u0012\u0011!a\u0001\u007f\u0006A\u0001.Y:i\u0007>$W\rF\u0001\u0000\u0003!!xn\u0015;sS:<G#\u0001<\u0002\r\u0015\fX/\u00197t)\u0011\t9#!\u0011\t\u0013\u0005EA#!AA\u0002\u0005%\u0001f\u0001\u0001\u0002FA!\u0011qIA&\u001b\t\tIE\u0003\u0002l?%!\u0011QJA%\u00051!UM^3m_B,'/\u00119jQ\u0015\u0001\u0011\u0011KA,!\u0011\t9%a\u0015\n\t\u0005U\u0013\u0011\n\u0002\u0006'&t7-Z\u0011\u0003\u00033\nQa\r\u00183]A\nae\u00159be.d\u0015n\u001d;f]\u0016\u0014X*[:dK2d\u0017M\\3pkN\u0004&o\\2fgN\fE\rZ3e!\tqccE\u0003\u0017\u0003C\ni\u0007\u0005\u0005\u0002d\u0005%$\tS*Z\u001b\t\t)GC\u0002\u0002h%\nqA];oi&lW-\u0003\u0003\u0002l\u0005\u0015$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA!\u0011qNA;\u001b\t\t\tHC\u0002\u0002ti\f!![8\n\u0007y\n\t\b\u0006\u0002\u0002^\u0005)\u0011\r\u001d9msR9\u0011,! \u0002\u0000\u0005\u0005\u0005\"\u0002!\u001a\u0001\u0004\u0011\u0005\"\u0002$\u001a\u0001\u0004A\u0005\"B)\u001a\u0001\u0004\u0019\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u000f\u000b\u0019\nE\u0003)\u0003\u0013\u000bi)C\u0002\u0002\f&\u0012aa\u00149uS>t\u0007C\u0002\u0015\u0002\u0010\nC5+C\u0002\u0002\u0012&\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CAK5\u0005\u0005\t\u0019A-\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001cB\u0019q/!(\n\u0007\u0005}\u0005P\u0001\u0004PE*,7\r\u001e"
)
public class SparkListenerMiscellaneousProcessAdded implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String processId;
   private final MiscellaneousProcessDetails info;

   public static Option unapply(final SparkListenerMiscellaneousProcessAdded x$0) {
      return SparkListenerMiscellaneousProcessAdded$.MODULE$.unapply(x$0);
   }

   public static SparkListenerMiscellaneousProcessAdded apply(final long time, final String processId, final MiscellaneousProcessDetails info) {
      return SparkListenerMiscellaneousProcessAdded$.MODULE$.apply(time, processId, info);
   }

   public static Function1 tupled() {
      return SparkListenerMiscellaneousProcessAdded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerMiscellaneousProcessAdded$.MODULE$.curried();
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

   public String processId() {
      return this.processId;
   }

   public MiscellaneousProcessDetails info() {
      return this.info;
   }

   public SparkListenerMiscellaneousProcessAdded copy(final long time, final String processId, final MiscellaneousProcessDetails info) {
      return new SparkListenerMiscellaneousProcessAdded(time, processId, info);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.processId();
   }

   public MiscellaneousProcessDetails copy$default$3() {
      return this.info();
   }

   public String productPrefix() {
      return "SparkListenerMiscellaneousProcessAdded";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 1 -> {
            return this.processId();
         }
         case 2 -> {
            return this.info();
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
      return x$1 instanceof SparkListenerMiscellaneousProcessAdded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "processId";
         }
         case 2 -> {
            return "info";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.processId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.info()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof SparkListenerMiscellaneousProcessAdded) {
               SparkListenerMiscellaneousProcessAdded var4 = (SparkListenerMiscellaneousProcessAdded)x$1;
               if (this.time() == var4.time()) {
                  label52: {
                     String var10000 = this.processId();
                     String var5 = var4.processId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     MiscellaneousProcessDetails var7 = this.info();
                     MiscellaneousProcessDetails var6 = var4.info();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public SparkListenerMiscellaneousProcessAdded(final long time, final String processId, final MiscellaneousProcessDetails info) {
      this.time = time;
      this.processId = processId;
      this.info = info;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
