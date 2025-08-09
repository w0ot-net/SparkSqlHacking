package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.storage.BlockManagerId;
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
   bytes = "\u0006\u0005\u0005Ud\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005L\u0001\tE\t\u0015!\u0003F\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u001d\t\u0006!!A\u0005\u0002ICq!\u0016\u0001\u0012\u0002\u0013\u0005a\u000bC\u0004b\u0001E\u0005I\u0011\u00012\t\u000f\u0011\u0004\u0011\u0011!C!K\"9a\u000eAA\u0001\n\u0003y\u0007bB:\u0001\u0003\u0003%\t\u0001\u001e\u0005\bu\u0002\t\t\u0011\"\u0011|\u0011%\t)\u0001AA\u0001\n\u0003\t9\u0001C\u0005\u0002\u0012\u0001\t\t\u0011\"\u0011\u0002\u0014!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0004\u0005\n\u00037\u0001\u0011\u0011!C!\u0003;A\u0011\"a\b\u0001\u0003\u0003%\t%!\t\b\u0013\u0005E\"$!A\t\u0002\u0005Mb\u0001C\r\u001b\u0003\u0003E\t!!\u000e\t\r1\u001bB\u0011AA'\u0011%\tYbEA\u0001\n\u000b\ni\u0002C\u0005\u0002PM\t\t\u0011\"!\u0002R!I\u0011qK\n\u0002\u0002\u0013\u0005\u0015\u0011\f\u0005\n\u0003W\u001a\u0012\u0011!C\u0005\u0003[\u0012\u0001e\u00159be.d\u0015n\u001d;f]\u0016\u0014(\t\\8dW6\u000bg.Y4feJ+Wn\u001c<fI*\u00111\u0004H\u0001\ng\u000eDW\rZ;mKJT!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001'\u0015\u0001AE\u000b\u00182!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fMB\u00111\u0006L\u0007\u00025%\u0011QF\u0007\u0002\u0013'B\f'o\u001b'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u0002&_%\u0011\u0001G\n\u0002\b!J|G-^2u!\t\u0011$H\u0004\u00024q9\u0011AgN\u0007\u0002k)\u0011aGI\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dJ!!\u000f\u0014\u0002\u000fA\f7m[1hK&\u00111\b\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003s\u0019\nA\u0001^5nKV\tq\b\u0005\u0002&\u0001&\u0011\u0011I\n\u0002\u0005\u0019>tw-A\u0003uS6,\u0007%\u0001\bcY>\u001c7.T1oC\u001e,'/\u00133\u0016\u0003\u0015\u0003\"AR%\u000e\u0003\u001dS!\u0001\u0013\u000f\u0002\u000fM$xN]1hK&\u0011!j\u0012\u0002\u000f\u00052|7m['b]\u0006<WM]%e\u0003=\u0011Gn\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0002O\u001fB\u0003\"a\u000b\u0001\t\u000bu*\u0001\u0019A \t\u000b\r+\u0001\u0019A#\u0002\t\r|\u0007/\u001f\u000b\u0004\u001dN#\u0006bB\u001f\u0007!\u0003\u0005\ra\u0010\u0005\b\u0007\u001a\u0001\n\u00111\u0001F\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0016\u0016\u0003\u007fa[\u0013!\u0017\t\u00035~k\u0011a\u0017\u0006\u00039v\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005y3\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001m\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002G*\u0012Q\tW\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0019\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\t1\fgn\u001a\u0006\u0002W\u0006!!.\u0019<b\u0013\ti\u0007N\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002aB\u0011Q%]\u0005\u0003e\u001a\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001e=\u0011\u0005\u00152\u0018BA<'\u0005\r\te.\u001f\u0005\bs.\t\t\u00111\u0001q\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA\u0010\u0005\u0003~\u0003\u0003)X\"\u0001@\u000b\u0005}4\u0013AC2pY2,7\r^5p]&\u0019\u00111\u0001@\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0013\ty\u0001E\u0002&\u0003\u0017I1!!\u0004'\u0005\u001d\u0011un\u001c7fC:Dq!_\u0007\u0002\u0002\u0003\u0007Q/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00014\u0002\u0016!9\u0011PDA\u0001\u0002\u0004\u0001\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003A\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002M\u00061Q-];bYN$B!!\u0003\u0002$!9\u00110EA\u0001\u0002\u0004)\bf\u0001\u0001\u0002(A!\u0011\u0011FA\u0017\u001b\t\tYC\u0003\u0002_9%!\u0011qFA\u0016\u00051!UM^3m_B,'/\u00119j\u0003\u0001\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\ncwnY6NC:\fw-\u001a:SK6|g/\u001a3\u0011\u0005-\u001a2#B\n\u00028\u0005\r\u0003cBA\u001d\u0003\u007fyTIT\u0007\u0003\u0003wQ1!!\u0010'\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0011\u0002<\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005\u0015\u00131J\u0007\u0003\u0003\u000fR1!!\u0013k\u0003\tIw.C\u0002<\u0003\u000f\"\"!a\r\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b9\u000b\u0019&!\u0016\t\u000bu2\u0002\u0019A \t\u000b\r3\u0002\u0019A#\u0002\u000fUt\u0017\r\u001d9msR!\u00111LA4!\u0015)\u0013QLA1\u0013\r\tyF\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u0015\n\u0019gP#\n\u0007\u0005\u0015dE\u0001\u0004UkBdWM\r\u0005\t\u0003S:\u0012\u0011!a\u0001\u001d\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005=\u0004cA4\u0002r%\u0019\u00111\u000f5\u0003\r=\u0013'.Z2u\u0001"
)
public class SparkListenerBlockManagerRemoved implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final BlockManagerId blockManagerId;

   public static Option unapply(final SparkListenerBlockManagerRemoved x$0) {
      return SparkListenerBlockManagerRemoved$.MODULE$.unapply(x$0);
   }

   public static SparkListenerBlockManagerRemoved apply(final long time, final BlockManagerId blockManagerId) {
      return SparkListenerBlockManagerRemoved$.MODULE$.apply(time, blockManagerId);
   }

   public static Function1 tupled() {
      return SparkListenerBlockManagerRemoved$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerBlockManagerRemoved$.MODULE$.curried();
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

   public BlockManagerId blockManagerId() {
      return this.blockManagerId;
   }

   public SparkListenerBlockManagerRemoved copy(final long time, final BlockManagerId blockManagerId) {
      return new SparkListenerBlockManagerRemoved(time, blockManagerId);
   }

   public long copy$default$1() {
      return this.time();
   }

   public BlockManagerId copy$default$2() {
      return this.blockManagerId();
   }

   public String productPrefix() {
      return "SparkListenerBlockManagerRemoved";
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
            return this.blockManagerId();
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
      return x$1 instanceof SparkListenerBlockManagerRemoved;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "blockManagerId";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.blockManagerId()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkListenerBlockManagerRemoved) {
               SparkListenerBlockManagerRemoved var4 = (SparkListenerBlockManagerRemoved)x$1;
               if (this.time() == var4.time()) {
                  label44: {
                     BlockManagerId var10000 = this.blockManagerId();
                     BlockManagerId var5 = var4.blockManagerId();
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

   public SparkListenerBlockManagerRemoved(final long time, final BlockManagerId blockManagerId) {
      this.time = time;
      this.blockManagerId = blockManagerId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
