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
   bytes = "\u0006\u0005\u0005\u0005f\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003I\u0011!\t\u0006A!f\u0001\n\u0003\u0011\u0006\u0002\u0003,\u0001\u0005#\u0005\u000b\u0011B*\t\u000b]\u0003A\u0011\u0001-\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAI\u0001\n\u0003\u0019\u0007b\u00028\u0001#\u0003%\ta\u001c\u0005\bc\u0002\t\n\u0011\"\u0001s\u0011\u001d!\b!!A\u0005BUDq! \u0001\u0002\u0002\u0013\u0005!\u000bC\u0004\u007f\u0001\u0005\u0005I\u0011A@\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\u000e\u0001\u0005\u0005I\u0011AA\u000f\u0011%\t9\u0003AA\u0001\n\u0003\nI\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00131\u0007\u0005\n\u0003k\u0001\u0011\u0011!C!\u0003o9\u0011\"a\u0017\u001e\u0003\u0003E\t!!\u0018\u0007\u0011qi\u0012\u0011!E\u0001\u0003?Baa\u0016\f\u0005\u0002\u0005]\u0004\"CA\u0019-\u0005\u0005IQIA\u001a\u0011%\tIHFA\u0001\n\u0003\u000bY\bC\u0005\u0002\u0004Z\t\t\u0011\"!\u0002\u0006\"I\u0011q\u0013\f\u0002\u0002\u0013%\u0011\u0011\u0014\u0002\u001d'B\f'o\u001b'jgR,g.\u001a:O_\u0012,'\t\\1dW2L7\u000f^3e\u0015\tqr$A\u0005tG\",G-\u001e7fe*\u0011\u0001%I\u0001\u0006gB\f'o\u001b\u0006\u0003E\r\na!\u00199bG\",'\"\u0001\u0013\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u00019S&\r\u001b\u0011\u0005!ZS\"A\u0015\u000b\u0003)\nQa]2bY\u0006L!\u0001L\u0015\u0003\r\u0005s\u0017PU3g!\tqs&D\u0001\u001e\u0013\t\u0001TD\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bC\u0001\u00153\u0013\t\u0019\u0014FA\u0004Qe>$Wo\u0019;\u0011\u0005UjdB\u0001\u001c<\u001d\t9$(D\u00019\u0015\tIT%\u0001\u0004=e>|GOP\u0005\u0002U%\u0011A(K\u0001\ba\u0006\u001c7.Y4f\u0013\tqtH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002=S\u0005!A/[7f+\u0005\u0011\u0005C\u0001\u0015D\u0013\t!\u0015F\u0001\u0003M_:<\u0017!\u0002;j[\u0016\u0004\u0013A\u00025pgRLE-F\u0001I!\tIUJ\u0004\u0002K\u0017B\u0011q'K\u0005\u0003\u0019&\na\u0001\u0015:fI\u00164\u0017B\u0001(P\u0005\u0019\u0019FO]5oO*\u0011A*K\u0001\bQ>\u001cH/\u00133!\u0003A)\u00070Z2vi>\u0014h)Y5mkJ,7/F\u0001T!\tAC+\u0003\u0002VS\t\u0019\u0011J\u001c;\u0002#\u0015DXmY;u_J4\u0015-\u001b7ve\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u00053j[F\f\u0005\u0002/\u0001!)\u0001i\u0002a\u0001\u0005\")ai\u0002a\u0001\u0011\")\u0011k\u0002a\u0001'\u0006!1m\u001c9z)\u0011Iv\fY1\t\u000f\u0001C\u0001\u0013!a\u0001\u0005\"9a\t\u0003I\u0001\u0002\u0004A\u0005bB)\t!\u0003\u0005\raU\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005!'F\u0001\"fW\u00051\u0007CA4m\u001b\u0005A'BA5k\u0003%)hn\u00195fG.,GM\u0003\u0002lS\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00055D'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00019+\u0005!+\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0002g*\u00121+Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003Y\u0004\"a\u001e?\u000e\u0003aT!!\u001f>\u0002\t1\fgn\u001a\u0006\u0002w\u0006!!.\u0019<b\u0013\tq\u00050\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0005\u0011q\u0001\t\u0004Q\u0005\r\u0011bAA\u0003S\t\u0019\u0011I\\=\t\u0011\u0005%a\"!AA\u0002M\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\b!\u0019\t\t\"a\u0006\u0002\u00025\u0011\u00111\u0003\u0006\u0004\u0003+I\u0013AC2pY2,7\r^5p]&!\u0011\u0011DA\n\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005}\u0011Q\u0005\t\u0004Q\u0005\u0005\u0012bAA\u0012S\t9!i\\8mK\u0006t\u0007\"CA\u0005!\u0005\u0005\t\u0019AA\u0001\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007Y\fY\u0003\u0003\u0005\u0002\nE\t\t\u00111\u0001T\u0003!A\u0017m\u001d5D_\u0012,G#A*\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A^\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005}\u0011\u0011\b\u0005\n\u0003\u0013!\u0012\u0011!a\u0001\u0003\u0003A3\u0002AA\u001f\u0003\u0007\n)%!\u0013\u0002LA\u0019\u0001&a\u0010\n\u0007\u0005\u0005\u0013F\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u0002H\u0005)So]3!'B\f'o\u001b'jgR,g.\u001a:O_\u0012,W\t_2mk\u0012,G\rI5ogR,\u0017\rZ\u0001\u0006g&t7-Z\u0011\u0003\u0003\u001b\nQa\r\u00182]AB3\u0001AA)!\u0011\t\u0019&a\u0016\u000e\u0005\u0005U#BA6 \u0013\u0011\tI&!\u0016\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u00029M\u0003\u0018M]6MSN$XM\\3s\u001d>$WM\u00117bG.d\u0017n\u001d;fIB\u0011aFF\n\u0006-\u0005\u0005\u0014Q\u000e\t\t\u0003G\nIG\u0011%T36\u0011\u0011Q\r\u0006\u0004\u0003OJ\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003W\n)GA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!a\u001c\u0002v5\u0011\u0011\u0011\u000f\u0006\u0004\u0003gR\u0018AA5p\u0013\rq\u0014\u0011\u000f\u000b\u0003\u0003;\nQ!\u00199qYf$r!WA?\u0003\u007f\n\t\tC\u0003A3\u0001\u0007!\tC\u0003G3\u0001\u0007\u0001\nC\u0003R3\u0001\u00071+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u001d\u00151\u0013\t\u0006Q\u0005%\u0015QR\u0005\u0004\u0003\u0017K#AB(qi&|g\u000e\u0005\u0004)\u0003\u001f\u0013\u0005jU\u0005\u0004\u0003#K#A\u0002+va2,7\u0007\u0003\u0005\u0002\u0016j\t\t\u00111\u0001Z\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u00037\u00032a^AO\u0013\r\ty\n\u001f\u0002\u0007\u001f\nTWm\u0019;"
)
public class SparkListenerNodeBlacklisted implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String hostId;
   private final int executorFailures;

   public static Option unapply(final SparkListenerNodeBlacklisted x$0) {
      return SparkListenerNodeBlacklisted$.MODULE$.unapply(x$0);
   }

   public static SparkListenerNodeBlacklisted apply(final long time, final String hostId, final int executorFailures) {
      return SparkListenerNodeBlacklisted$.MODULE$.apply(time, hostId, executorFailures);
   }

   public static Function1 tupled() {
      return SparkListenerNodeBlacklisted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerNodeBlacklisted$.MODULE$.curried();
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

   public SparkListenerNodeBlacklisted copy(final long time, final String hostId, final int executorFailures) {
      return new SparkListenerNodeBlacklisted(time, hostId, executorFailures);
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
      return "SparkListenerNodeBlacklisted";
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
      return x$1 instanceof SparkListenerNodeBlacklisted;
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
            if (x$1 instanceof SparkListenerNodeBlacklisted) {
               SparkListenerNodeBlacklisted var4 = (SparkListenerNodeBlacklisted)x$1;
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

   public SparkListenerNodeBlacklisted(final long time, final String hostId, final int executorFailures) {
      this.time = time;
      this.hostId = hostId;
      this.executorFailures = executorFailures;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
