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
   bytes = "\u0006\u0005\u0005]e\u0001B\u0010!\u0001&B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005H\u0001\tE\t\u0015!\u0003C\u0011!A\u0005A!f\u0001\n\u0003\t\u0005\u0002C%\u0001\u0005#\u0005\u000b\u0011\u0002\"\t\u0011)\u0003!Q3A\u0005\u0002\u0005C\u0001b\u0013\u0001\u0003\u0012\u0003\u0006IA\u0011\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006'\u0002!\t\u0005\u0016\u0005\b;\u0002\t\t\u0011\"\u0001_\u0011\u001d\u0019\u0007!%A\u0005\u0002\u0011Dqa\u001c\u0001\u0012\u0002\u0013\u0005A\rC\u0004q\u0001E\u0005I\u0011\u00013\t\u000fE\u0004\u0011\u0013!C\u0001I\"9!\u000fAA\u0001\n\u0003\u001a\bbB>\u0001\u0003\u0003%\t!\u0011\u0005\by\u0002\t\t\u0011\"\u0001~\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0001\u0002\u001a!I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003S\u0001\u0011\u0011!C!\u0003WA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\b\u0013\u0005-\u0003%!A\t\u0002\u00055c\u0001C\u0010!\u0003\u0003E\t!a\u0014\t\r1KB\u0011AA4\u0011%\tI'GA\u0001\n\u000b\nY\u0007C\u0005\u0002ne\t\t\u0011\"!\u0002p!I\u0011\u0011P\r\u0002\u0002\u0013\u0005\u00151\u0010\u0005\n\u0003\u001bK\u0012\u0011!C\u0005\u0003\u001f\u0013!c\u00155vM\u001adW\rU;tQ\ncwnY6JI*\u0011\u0011EI\u0001\bgR|'/Y4f\u0015\t\u0019C%A\u0003ta\u0006\u00148N\u0003\u0002&M\u00051\u0011\r]1dQ\u0016T\u0011aJ\u0001\u0004_J<7\u0001A\n\u0005\u0001)rC\u0007\u0005\u0002,Y5\t\u0001%\u0003\u0002.A\t9!\t\\8dW&#\u0007CA\u00183\u001b\u0005\u0001$\"A\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0002$a\u0002)s_\u0012,8\r\u001e\t\u0003kur!AN\u001e\u000f\u0005]RT\"\u0001\u001d\u000b\u0005eB\u0013A\u0002\u001fs_>$h(C\u00012\u0013\ta\u0004'A\u0004qC\u000e\\\u0017mZ3\n\u0005yz$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001f1\u0003%\u0019\b.\u001e4gY\u0016LE-F\u0001C!\ty3)\u0003\u0002Ea\t\u0019\u0011J\u001c;\u0002\u0015MDWO\u001a4mK&#\u0007%\u0001\btQV4g\r\\3NKJ<W-\u00133\u0002\u001fMDWO\u001a4mK6+'oZ3JI\u0002\n\u0001\"\\1q\u0013:$W\r_\u0001\n[\u0006\u0004\u0018J\u001c3fq\u0002\n\u0001B]3ek\u000e,\u0017\nZ\u0001\ne\u0016$WoY3JI\u0002\na\u0001P5oSRtD#\u0002(P!F\u0013\u0006CA\u0016\u0001\u0011\u0015\u0001\u0015\u00021\u0001C\u0011\u00151\u0015\u00021\u0001C\u0011\u0015A\u0015\u00021\u0001C\u0011\u0015Q\u0015\u00021\u0001C\u0003\u0011q\u0017-\\3\u0016\u0003U\u0003\"A\u0016.\u000f\u0005]C\u0006CA\u001c1\u0013\tI\u0006'\u0001\u0004Qe\u0016$WMZ\u0005\u00037r\u0013aa\u0015;sS:<'BA-1\u0003\u0011\u0019w\u000e]=\u0015\u000b9{\u0006-\u00192\t\u000f\u0001[\u0001\u0013!a\u0001\u0005\"9ai\u0003I\u0001\u0002\u0004\u0011\u0005b\u0002%\f!\u0003\u0005\rA\u0011\u0005\b\u0015.\u0001\n\u00111\u0001C\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u001a\u0016\u0003\u0005\u001a\\\u0013a\u001a\t\u0003Q6l\u0011!\u001b\u0006\u0003U.\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00051\u0004\u0014AC1o]>$\u0018\r^5p]&\u0011a.\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001;\u0011\u0005UTX\"\u0001<\u000b\u0005]D\u0018\u0001\u00027b]\u001eT\u0011!_\u0001\u0005U\u00064\u0018-\u0003\u0002\\m\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$Hc\u0001@\u0002\u0004A\u0011qf`\u0005\u0004\u0003\u0003\u0001$aA!os\"A\u0011Q\u0001\n\u0002\u0002\u0003\u0007!)A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0017\u0001R!!\u0004\u0002\u0014yl!!a\u0004\u000b\u0007\u0005E\u0001'\u0001\u0006d_2dWm\u0019;j_:LA!!\u0006\u0002\u0010\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY\"!\t\u0011\u0007=\ni\"C\u0002\u0002 A\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0006Q\t\t\u00111\u0001\u007f\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007Q\f9\u0003\u0003\u0005\u0002\u0006U\t\t\u00111\u0001C\u0003!A\u0017m\u001d5D_\u0012,G#\u0001\"\u0002\r\u0015\fX/\u00197t)\u0011\tY\"!\r\t\u0011\u0005\u0015q#!AA\u0002yDS\u0001AA\u001b\u0003\u007f\u0001B!a\u000e\u0002<5\u0011\u0011\u0011\b\u0006\u0003Y\nJA!!\u0010\u0002:\t)1+\u001b8dK\u0006\u0012\u0011\u0011I\u0001\u0006g9\u0012d\u0006\r\u0015\u0004\u0001\u0005\u0015\u0003\u0003BA\u001c\u0003\u000fJA!!\u0013\u0002:\taA)\u001a<fY>\u0004XM]!qS\u0006\u00112\u000b[;gM2,\u0007+^:i\u00052|7m[%e!\tY\u0013dE\u0003\u001a\u0003#\ni\u0006E\u0005\u0002T\u0005e#I\u0011\"C\u001d6\u0011\u0011Q\u000b\u0006\u0004\u0003/\u0002\u0014a\u0002:v]RLW.Z\u0005\u0005\u00037\n)FA\tBEN$(/Y2u\rVt7\r^5p]R\u0002B!a\u0018\u0002f5\u0011\u0011\u0011\r\u0006\u0004\u0003GB\u0018AA5p\u0013\rq\u0014\u0011\r\u000b\u0003\u0003\u001b\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002i\u0006)\u0011\r\u001d9msRIa*!\u001d\u0002t\u0005U\u0014q\u000f\u0005\u0006\u0001r\u0001\rA\u0011\u0005\u0006\rr\u0001\rA\u0011\u0005\u0006\u0011r\u0001\rA\u0011\u0005\u0006\u0015r\u0001\rAQ\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti(!#\u0011\u000b=\ny(a!\n\u0007\u0005\u0005\u0005G\u0001\u0004PaRLwN\u001c\t\b_\u0005\u0015%I\u0011\"C\u0013\r\t9\t\r\u0002\u0007)V\u0004H.\u001a\u001b\t\u0011\u0005-U$!AA\u00029\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\nE\u0002v\u0003'K1!!&w\u0005\u0019y%M[3di\u0002"
)
public class ShufflePushBlockId extends BlockId implements Product, Serializable {
   private final int shuffleId;
   private final int shuffleMergeId;
   private final int mapIndex;
   private final int reduceId;

   public static Option unapply(final ShufflePushBlockId x$0) {
      return ShufflePushBlockId$.MODULE$.unapply(x$0);
   }

   public static ShufflePushBlockId apply(final int shuffleId, final int shuffleMergeId, final int mapIndex, final int reduceId) {
      return ShufflePushBlockId$.MODULE$.apply(shuffleId, shuffleMergeId, mapIndex, reduceId);
   }

   public static Function1 tupled() {
      return ShufflePushBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShufflePushBlockId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public int shuffleMergeId() {
      return this.shuffleMergeId;
   }

   public int mapIndex() {
      return this.mapIndex;
   }

   public int reduceId() {
      return this.reduceId;
   }

   public String name() {
      int var10000 = this.shuffleId();
      return "shufflePush_" + var10000 + "_" + this.shuffleMergeId() + "_" + this.mapIndex() + "_" + this.reduceId();
   }

   public ShufflePushBlockId copy(final int shuffleId, final int shuffleMergeId, final int mapIndex, final int reduceId) {
      return new ShufflePushBlockId(shuffleId, shuffleMergeId, mapIndex, reduceId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public int copy$default$2() {
      return this.shuffleMergeId();
   }

   public int copy$default$3() {
      return this.mapIndex();
   }

   public int copy$default$4() {
      return this.reduceId();
   }

   public String productPrefix() {
      return "ShufflePushBlockId";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.shuffleMergeId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.mapIndex());
         }
         case 3 -> {
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
      return x$1 instanceof ShufflePushBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         case 1 -> {
            return "shuffleMergeId";
         }
         case 2 -> {
            return "mapIndex";
         }
         case 3 -> {
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
      var1 = Statics.mix(var1, this.shuffleMergeId());
      var1 = Statics.mix(var1, this.mapIndex());
      var1 = Statics.mix(var1, this.reduceId());
      return Statics.finalizeHash(var1, 4);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label42: {
            if (x$1 instanceof ShufflePushBlockId) {
               ShufflePushBlockId var4 = (ShufflePushBlockId)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.shuffleMergeId() == var4.shuffleMergeId() && this.mapIndex() == var4.mapIndex() && this.reduceId() == var4.reduceId() && var4.canEqual(this)) {
                  break label42;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ShufflePushBlockId(final int shuffleId, final int shuffleMergeId, final int mapIndex, final int reduceId) {
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.mapIndex = mapIndex;
      this.reduceId = reduceId;
      Product.$init$(this);
   }
}
