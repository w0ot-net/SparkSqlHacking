package org.apache.spark.storage;

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
   bytes = "\u0006\u0005\u0005Ee\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005I\u0001\tE\t\u0015!\u0003F\u0011!I\u0005A!f\u0001\n\u0003q\u0004\u0002\u0003&\u0001\u0005#\u0005\u000b\u0011B \t\u000b-\u0003A\u0011\u0001'\t\u000bE\u0003A\u0011\t*\t\u000fm\u0003\u0011\u0011!C\u00019\"9\u0001\rAI\u0001\n\u0003\t\u0007b\u00027\u0001#\u0003%\t!\u001c\u0005\b_\u0002\t\n\u0011\"\u0001b\u0011\u001d\u0001\b!!A\u0005BEDq!\u001f\u0001\u0002\u0002\u0013\u0005a\bC\u0004{\u0001\u0005\u0005I\u0011A>\t\u0013\u0005\r\u0001!!A\u0005B\u0005\u0015\u0001\"CA\n\u0001\u0005\u0005I\u0011AA\u000b\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131F\u0004\n\u0003\u000fj\u0012\u0011!E\u0001\u0003\u00132\u0001\u0002H\u000f\u0002\u0002#\u0005\u00111\n\u0005\u0007\u0017Z!\t!a\u0019\t\u0013\u0005\u0015d#!A\u0005F\u0005\u001d\u0004\"CA5-\u0005\u0005I\u0011QA6\u0011%\t\u0019HFA\u0001\n\u0003\u000b)\bC\u0005\u0002\bZ\t\t\u0011\"\u0003\u0002\n\n12\u000b[;gM2,7\t[3dWN,XN\u00117pG.LEM\u0003\u0002\u001f?\u000591\u000f^8sC\u001e,'B\u0001\u0011\"\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00113%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002I\u0005\u0019qN]4\u0004\u0001M!\u0001aJ\u00162!\tA\u0013&D\u0001\u001e\u0013\tQSDA\u0004CY>\u001c7.\u00133\u0011\u00051zS\"A\u0017\u000b\u00039\nQa]2bY\u0006L!\u0001M\u0017\u0003\u000fA\u0013x\u000eZ;diB\u0011!G\u000f\b\u0003gar!\u0001N\u001c\u000e\u0003UR!AN\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0013BA\u001d.\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000f\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005ej\u0013!C:ik\u001a4G.Z%e+\u0005y\u0004C\u0001\u0017A\u0013\t\tUFA\u0002J]R\f!b\u001d5vM\u001adW-\u00133!\u0003\u0015i\u0017\r]%e+\u0005)\u0005C\u0001\u0017G\u0013\t9UF\u0001\u0003M_:<\u0017AB7ba&#\u0007%\u0001\u0005sK\u0012,8-Z%e\u0003%\u0011X\rZ;dK&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005\u001b:{\u0005\u000b\u0005\u0002)\u0001!)Qh\u0002a\u0001\u007f!)1i\u0002a\u0001\u000b\")\u0011j\u0002a\u0001\u007f\u0005!a.Y7f+\u0005\u0019\u0006C\u0001+Y\u001d\t)f\u000b\u0005\u00025[%\u0011q+L\u0001\u0007!J,G-\u001a4\n\u0005eS&AB*ue&twM\u0003\u0002X[\u0005!1m\u001c9z)\u0011iULX0\t\u000fuJ\u0001\u0013!a\u0001\u007f!91)\u0003I\u0001\u0002\u0004)\u0005bB%\n!\u0003\u0005\raP\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0011'FA dW\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003%)hn\u00195fG.,GM\u0003\u0002j[\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005-4'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00018+\u0005\u0015\u001b\u0017AD2paf$C-\u001a4bk2$HeM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003I\u0004\"a\u001d=\u000e\u0003QT!!\u001e<\u0002\t1\fgn\u001a\u0006\u0002o\u0006!!.\u0019<b\u0013\tIF/\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005q|\bC\u0001\u0017~\u0013\tqXFA\u0002B]fD\u0001\"!\u0001\u0010\u0003\u0003\u0005\raP\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u001d\u0001#BA\u0005\u0003\u001faXBAA\u0006\u0015\r\ti!L\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\t\u0003\u0017\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qCA\u000f!\ra\u0013\u0011D\u0005\u0004\u00037i#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003\u0003\t\u0012\u0011!a\u0001y\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\u0011\u00181\u0005\u0005\t\u0003\u0003\u0011\u0012\u0011!a\u0001\u007f\u0005A\u0001.Y:i\u0007>$W\rF\u0001@\u0003\u0019)\u0017/^1mgR!\u0011qCA\u0017\u0011!\t\t\u0001FA\u0001\u0002\u0004a\b&\u0002\u0001\u00022\u0005m\u0002\u0003BA\u001a\u0003oi!!!\u000e\u000b\u0005%|\u0012\u0002BA\u001d\u0003k\u0011QaU5oG\u0016\f#!!\u0010\u0002\u000bMr#G\f\u0019)\u0007\u0001\t\t\u0005\u0005\u0003\u00024\u0005\r\u0013\u0002BA#\u0003k\u0011A\u0002R3wK2|\u0007/\u001a:Ba&\fac\u00155vM\u001adWm\u00115fG.\u001cX/\u001c\"m_\u000e\\\u0017\n\u001a\t\u0003QY\u0019RAFA'\u00033\u0002\u0002\"a\u0014\u0002V}*u(T\u0007\u0003\u0003#R1!a\u0015.\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0016\u0002R\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005m\u0013\u0011M\u0007\u0003\u0003;R1!a\u0018w\u0003\tIw.C\u0002<\u0003;\"\"!!\u0013\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A]\u0001\u0006CB\u0004H.\u001f\u000b\b\u001b\u00065\u0014qNA9\u0011\u0015i\u0014\u00041\u0001@\u0011\u0015\u0019\u0015\u00041\u0001F\u0011\u0015I\u0015\u00041\u0001@\u0003\u001d)h.\u00199qYf$B!a\u001e\u0002\u0004B)A&!\u001f\u0002~%\u0019\u00111P\u0017\u0003\r=\u0003H/[8o!\u0019a\u0013qP F\u007f%\u0019\u0011\u0011Q\u0017\u0003\rQ+\b\u000f\\34\u0011!\t)IGA\u0001\u0002\u0004i\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0012\t\u0004g\u00065\u0015bAAHi\n1qJ\u00196fGR\u0004"
)
public class ShuffleChecksumBlockId extends BlockId implements Product, Serializable {
   private final int shuffleId;
   private final long mapId;
   private final int reduceId;

   public static Option unapply(final ShuffleChecksumBlockId x$0) {
      return ShuffleChecksumBlockId$.MODULE$.unapply(x$0);
   }

   public static ShuffleChecksumBlockId apply(final int shuffleId, final long mapId, final int reduceId) {
      return ShuffleChecksumBlockId$.MODULE$.apply(shuffleId, mapId, reduceId);
   }

   public static Function1 tupled() {
      return ShuffleChecksumBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleChecksumBlockId$.MODULE$.curried();
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

   public int reduceId() {
      return this.reduceId;
   }

   public String name() {
      int var10000 = this.shuffleId();
      return "shuffle_" + var10000 + "_" + this.mapId() + "_" + this.reduceId() + ".checksum";
   }

   public ShuffleChecksumBlockId copy(final int shuffleId, final long mapId, final int reduceId) {
      return new ShuffleChecksumBlockId(shuffleId, mapId, reduceId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public long copy$default$2() {
      return this.mapId();
   }

   public int copy$default$3() {
      return this.reduceId();
   }

   public String productPrefix() {
      return "ShuffleChecksumBlockId";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.mapId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.reduceId());
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
      return x$1 instanceof ShuffleChecksumBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         case 1 -> {
            return "mapId";
         }
         case 2 -> {
            return "reduceId";
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
      var1 = Statics.mix(var1, this.reduceId());
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof ShuffleChecksumBlockId) {
               ShuffleChecksumBlockId var4 = (ShuffleChecksumBlockId)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.mapId() == var4.mapId() && this.reduceId() == var4.reduceId() && var4.canEqual(this)) {
                  break label40;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ShuffleChecksumBlockId(final int shuffleId, final long mapId, final int reduceId) {
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.reduceId = reduceId;
      Product.$init$(this);
   }
}
