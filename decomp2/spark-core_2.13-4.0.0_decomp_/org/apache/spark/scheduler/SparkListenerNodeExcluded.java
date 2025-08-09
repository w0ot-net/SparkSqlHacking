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
   bytes = "\u0006\u0005\u0005ee\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003I\u0011!\t\u0006A!f\u0001\n\u0003\u0011\u0006\u0002\u0003,\u0001\u0005#\u0005\u000b\u0011B*\t\u000b]\u0003A\u0011\u0001-\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAI\u0001\n\u0003\u0019\u0007b\u00028\u0001#\u0003%\ta\u001c\u0005\bc\u0002\t\n\u0011\"\u0001s\u0011\u001d!\b!!A\u0005BUDq! \u0001\u0002\u0002\u0013\u0005!\u000bC\u0004\u007f\u0001\u0005\u0005I\u0011A@\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\u000e\u0001\u0005\u0005I\u0011AA\u000f\u0011%\t9\u0003AA\u0001\n\u0003\nI\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00131\u0007\u0005\n\u0003k\u0001\u0011\u0011!C!\u0003o9\u0011\"a\u0015\u001e\u0003\u0003E\t!!\u0016\u0007\u0011qi\u0012\u0011!E\u0001\u0003/Baa\u0016\f\u0005\u0002\u0005=\u0004\"CA\u0019-\u0005\u0005IQIA\u001a\u0011%\t\tHFA\u0001\n\u0003\u000b\u0019\bC\u0005\u0002|Y\t\t\u0011\"!\u0002~!I\u0011q\u0012\f\u0002\u0002\u0013%\u0011\u0011\u0013\u0002\u001a'B\f'o\u001b'jgR,g.\u001a:O_\u0012,W\t_2mk\u0012,GM\u0003\u0002\u001f?\u0005I1o\u00195fIVdWM\u001d\u0006\u0003A\u0005\nQa\u001d9be.T!AI\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0013aA8sO\u000e\u00011#\u0002\u0001([E\"\u0004C\u0001\u0015,\u001b\u0005I#\"\u0001\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00051J#AB!osJ+g\r\u0005\u0002/_5\tQ$\u0003\u00021;\t\u00112\u000b]1sW2K7\u000f^3oKJ,e/\u001a8u!\tA#'\u0003\u00024S\t9\u0001K]8ek\u000e$\bCA\u001b>\u001d\t14H\u0004\u00028u5\t\u0001H\u0003\u0002:K\u00051AH]8pizJ\u0011AK\u0005\u0003y%\nq\u0001]1dW\u0006<W-\u0003\u0002?\u007f\ta1+\u001a:jC2L'0\u00192mK*\u0011A(K\u0001\u0005i&lW-F\u0001C!\tA3)\u0003\u0002ES\t!Aj\u001c8h\u0003\u0015!\u0018.\\3!\u0003\u0019Awn\u001d;JIV\t\u0001\n\u0005\u0002J\u001b:\u0011!j\u0013\t\u0003o%J!\u0001T\u0015\u0002\rA\u0013X\rZ3g\u0013\tquJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0019&\nq\u0001[8ti&#\u0007%\u0001\tfq\u0016\u001cW\u000f^8s\r\u0006LG.\u001e:fgV\t1\u000b\u0005\u0002))&\u0011Q+\u000b\u0002\u0004\u0013:$\u0018!E3yK\u000e,Ho\u001c:GC&dWO]3tA\u00051A(\u001b8jiz\"B!\u0017.\\9B\u0011a\u0006\u0001\u0005\u0006\u0001\u001e\u0001\rA\u0011\u0005\u0006\r\u001e\u0001\r\u0001\u0013\u0005\u0006#\u001e\u0001\raU\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003Z?\u0002\f\u0007b\u0002!\t!\u0003\u0005\rA\u0011\u0005\b\r\"\u0001\n\u00111\u0001I\u0011\u001d\t\u0006\u0002%AA\u0002M\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001eU\t\u0011UmK\u0001g!\t9G.D\u0001i\u0015\tI'.A\u0005v]\u000eDWmY6fI*\u00111.K\u0001\u000bC:tw\u000e^1uS>t\u0017BA7i\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005\u0001(F\u0001%f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\u0012a\u001d\u0016\u0003'\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001<\u0011\u0005]dX\"\u0001=\u000b\u0005eT\u0018\u0001\u00027b]\u001eT\u0011a_\u0001\u0005U\u00064\u0018-\u0003\u0002Oq\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0001\u0003\u000f\u00012\u0001KA\u0002\u0013\r\t)!\u000b\u0002\u0004\u0003:L\b\u0002CA\u0005\u001d\u0005\u0005\t\u0019A*\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0001\u0005\u0004\u0002\u0012\u0005]\u0011\u0011A\u0007\u0003\u0003'Q1!!\u0006*\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00033\t\u0019B\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0010\u0003K\u00012\u0001KA\u0011\u0013\r\t\u0019#\u000b\u0002\b\u0005>|G.Z1o\u0011%\tI\u0001EA\u0001\u0002\u0004\t\t!\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001<\u0002,!A\u0011\u0011B\t\u0002\u0002\u0003\u00071+\u0001\u0005iCND7i\u001c3f)\u0005\u0019\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Y\fa!Z9vC2\u001cH\u0003BA\u0010\u0003sA\u0011\"!\u0003\u0015\u0003\u0003\u0005\r!!\u0001)\u0007\u0001\ti\u0004\u0005\u0003\u0002@\u0005\rSBAA!\u0015\tYw$\u0003\u0003\u0002F\u0005\u0005#\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007&\u0002\u0001\u0002J\u0005=\u0003\u0003BA \u0003\u0017JA!!\u0014\u0002B\t)1+\u001b8dK\u0006\u0012\u0011\u0011K\u0001\u0006g9\nd\u0006M\u0001\u001a'B\f'o\u001b'jgR,g.\u001a:O_\u0012,W\t_2mk\u0012,G\r\u0005\u0002/-M)a#!\u0017\u0002fAA\u00111LA1\u0005\"\u001b\u0016,\u0004\u0002\u0002^)\u0019\u0011qL\u0015\u0002\u000fI,h\u000e^5nK&!\u00111MA/\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003O\ni'\u0004\u0002\u0002j)\u0019\u00111\u000e>\u0002\u0005%|\u0017b\u0001 \u0002jQ\u0011\u0011QK\u0001\u0006CB\u0004H.\u001f\u000b\b3\u0006U\u0014qOA=\u0011\u0015\u0001\u0015\u00041\u0001C\u0011\u00151\u0015\u00041\u0001I\u0011\u0015\t\u0016\u00041\u0001T\u0003\u001d)h.\u00199qYf$B!a \u0002\fB)\u0001&!!\u0002\u0006&\u0019\u00111Q\u0015\u0003\r=\u0003H/[8o!\u0019A\u0013q\u0011\"I'&\u0019\u0011\u0011R\u0015\u0003\rQ+\b\u000f\\34\u0011!\tiIGA\u0001\u0002\u0004I\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0013\t\u0004o\u0006U\u0015bAALq\n1qJ\u00196fGR\u0004"
)
public class SparkListenerNodeExcluded implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String hostId;
   private final int executorFailures;

   public static Option unapply(final SparkListenerNodeExcluded x$0) {
      return SparkListenerNodeExcluded$.MODULE$.unapply(x$0);
   }

   public static SparkListenerNodeExcluded apply(final long time, final String hostId, final int executorFailures) {
      return SparkListenerNodeExcluded$.MODULE$.apply(time, hostId, executorFailures);
   }

   public static Function1 tupled() {
      return SparkListenerNodeExcluded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerNodeExcluded$.MODULE$.curried();
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

   public String hostId() {
      return this.hostId;
   }

   public int executorFailures() {
      return this.executorFailures;
   }

   public SparkListenerNodeExcluded copy(final long time, final String hostId, final int executorFailures) {
      return new SparkListenerNodeExcluded(time, hostId, executorFailures);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.hostId();
   }

   public int copy$default$3() {
      return this.executorFailures();
   }

   public String productPrefix() {
      return "SparkListenerNodeExcluded";
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
            return this.hostId();
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.executorFailures());
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
      return x$1 instanceof SparkListenerNodeExcluded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "hostId";
         }
         case 2 -> {
            return "executorFailures";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.hostId()));
      var1 = Statics.mix(var1, this.executorFailures());
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof SparkListenerNodeExcluded) {
               SparkListenerNodeExcluded var4 = (SparkListenerNodeExcluded)x$1;
               if (this.time() == var4.time() && this.executorFailures() == var4.executorFailures()) {
                  label48: {
                     String var10000 = this.hostId();
                     String var5 = var4.hostId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
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

   public SparkListenerNodeExcluded(final long time, final String hostId, final int executorFailures) {
      this.time = time;
      this.hostId = hostId;
      this.executorFailures = executorFailures;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
