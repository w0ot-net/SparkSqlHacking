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
   bytes = "\u0006\u0005\u0005Md\u0001B\u000e\u001d\u0001\u0016B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u0003\"AQ\t\u0001BK\u0002\u0013\u0005a\t\u0003\u0005N\u0001\tE\t\u0015!\u0003H\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u001d\u0019\u0006!!A\u0005\u0002QCqa\u0016\u0001\u0012\u0002\u0013\u0005\u0001\fC\u0004d\u0001E\u0005I\u0011\u00013\t\u000f\u0019\u0004\u0011\u0011!C!O\"9\u0001\u000fAA\u0001\n\u0003\t\bb\u0002:\u0001\u0003\u0003%\ta\u001d\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011%\t\u0019\u0001AA\u0001\n\u0003\t)\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0011\u0002\u0012!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\n\u00033\u0001\u0011\u0011!C!\u00037A\u0011\"!\b\u0001\u0003\u0003%\t%a\b\b\u0013\u0005=B$!A\t\u0002\u0005Eb\u0001C\u000e\u001d\u0003\u0003E\t!a\r\t\r9\u001bB\u0011AA&\u0011%\tIbEA\u0001\n\u000b\nY\u0002C\u0005\u0002NM\t\t\u0011\"!\u0002P!A\u0011QK\n\u0012\u0002\u0013\u0005A\rC\u0005\u0002XM\t\t\u0011\"!\u0002Z!A\u0011qM\n\u0012\u0002\u0013\u0005A\rC\u0005\u0002jM\t\t\u0011\"\u0003\u0002l\tY2\u000b]1sW2K7\u000f^3oKJ\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8F]\u0012T!!\b\u0010\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001M)\u0001A\n\u00171gA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t1\u0011I\\=SK\u001a\u0004\"!\f\u0018\u000e\u0003qI!a\f\u000f\u0003%M\u0003\u0018M]6MSN$XM\\3s\u000bZ,g\u000e\u001e\t\u0003OEJ!A\r\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001\u000f\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0013BA\u001e)\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mB\u0013\u0001\u0002;j[\u0016,\u0012!\u0011\t\u0003O\tK!a\u0011\u0015\u0003\t1{gnZ\u0001\u0006i&lW\rI\u0001\tKbLGoQ8eKV\tq\tE\u0002(\u0011*K!!\u0013\u0015\u0003\r=\u0003H/[8o!\t93*\u0003\u0002MQ\t\u0019\u0011J\u001c;\u0002\u0013\u0015D\u0018\u000e^\"pI\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u0002Q#J\u0003\"!\f\u0001\t\u000b}*\u0001\u0019A!\t\u000f\u0015+\u0001\u0013!a\u0001\u000f\u0006!1m\u001c9z)\r\u0001VK\u0016\u0005\b\u007f\u0019\u0001\n\u00111\u0001B\u0011\u001d)e\u0001%AA\u0002\u001d\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001ZU\t\t%lK\u0001\\!\ta\u0016-D\u0001^\u0015\tqv,A\u0005v]\u000eDWmY6fI*\u0011\u0001\rK\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00012^\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005)'FA$[\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0001\u000e\u0005\u0002j]6\t!N\u0003\u0002lY\u0006!A.\u00198h\u0015\u0005i\u0017\u0001\u00026bm\u0006L!a\u001c6\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Q\u0015A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003i^\u0004\"aJ;\n\u0005YD#aA!os\"9\u0001pCA\u0001\u0002\u0004Q\u0015a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001|!\rax\u0010^\u0007\u0002{*\u0011a\u0010K\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0001{\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9!!\u0004\u0011\u0007\u001d\nI!C\u0002\u0002\f!\u0012qAQ8pY\u0016\fg\u000eC\u0004y\u001b\u0005\u0005\t\u0019\u0001;\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004Q\u0006M\u0001b\u0002=\u000f\u0003\u0003\u0005\rAS\u0001\tQ\u0006\u001c\bnQ8eKR\t!*\u0001\u0005u_N#(/\u001b8h)\u0005A\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\b\u0005\u0005\u0002b\u0002=\u0012\u0003\u0003\u0005\r\u0001\u001e\u0015\u0004\u0001\u0005\u0015\u0002\u0003BA\u0014\u0003Wi!!!\u000b\u000b\u0005\u0001t\u0012\u0002BA\u0017\u0003S\u0011A\u0002R3wK2|\u0007/\u001a:Ba&\f1d\u00159be.d\u0015n\u001d;f]\u0016\u0014\u0018\t\u001d9mS\u000e\fG/[8o\u000b:$\u0007CA\u0017\u0014'\u0015\u0019\u0012QGA!!\u001d\t9$!\u0010B\u000fBk!!!\u000f\u000b\u0007\u0005m\u0002&A\u0004sk:$\u0018.\\3\n\t\u0005}\u0012\u0011\b\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\"\u0003\u0013j!!!\u0012\u000b\u0007\u0005\u001dC.\u0001\u0002j_&\u0019Q(!\u0012\u0015\u0005\u0005E\u0012!B1qa2LH#\u0002)\u0002R\u0005M\u0003\"B \u0017\u0001\u0004\t\u0005bB#\u0017!\u0003\u0005\raR\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u00059QO\\1qa2LH\u0003BA.\u0003G\u0002Ba\n%\u0002^A)q%a\u0018B\u000f&\u0019\u0011\u0011\r\u0015\u0003\rQ+\b\u000f\\33\u0011!\t)\u0007GA\u0001\u0002\u0004\u0001\u0016a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001c\u0011\u0007%\fy'C\u0002\u0002r)\u0014aa\u00142kK\u000e$\b"
)
public class SparkListenerApplicationEnd implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final Option exitCode;

   public static Option $lessinit$greater$default$2() {
      return SparkListenerApplicationEnd$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final SparkListenerApplicationEnd x$0) {
      return SparkListenerApplicationEnd$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$2() {
      return SparkListenerApplicationEnd$.MODULE$.apply$default$2();
   }

   public static SparkListenerApplicationEnd apply(final long time, final Option exitCode) {
      return SparkListenerApplicationEnd$.MODULE$.apply(time, exitCode);
   }

   public static Function1 tupled() {
      return SparkListenerApplicationEnd$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerApplicationEnd$.MODULE$.curried();
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

   public Option exitCode() {
      return this.exitCode;
   }

   public SparkListenerApplicationEnd copy(final long time, final Option exitCode) {
      return new SparkListenerApplicationEnd(time, exitCode);
   }

   public long copy$default$1() {
      return this.time();
   }

   public Option copy$default$2() {
      return this.exitCode();
   }

   public String productPrefix() {
      return "SparkListenerApplicationEnd";
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
            return this.exitCode();
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
      return x$1 instanceof SparkListenerApplicationEnd;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "exitCode";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.exitCode()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkListenerApplicationEnd) {
               SparkListenerApplicationEnd var4 = (SparkListenerApplicationEnd)x$1;
               if (this.time() == var4.time()) {
                  label44: {
                     Option var10000 = this.exitCode();
                     Option var5 = var4.exitCode();
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

   public SparkListenerApplicationEnd(final long time, final Option exitCode) {
      this.time = time;
      this.exitCode = exitCode;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
