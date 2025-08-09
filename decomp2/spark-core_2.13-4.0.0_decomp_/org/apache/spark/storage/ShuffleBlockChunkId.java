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
   bytes = "\u0006\u0005\u0005]e\u0001B\u0010!\u0001&B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005H\u0001\tE\t\u0015!\u0003C\u0011!A\u0005A!f\u0001\n\u0003\t\u0005\u0002C%\u0001\u0005#\u0005\u000b\u0011\u0002\"\t\u0011)\u0003!Q3A\u0005\u0002\u0005C\u0001b\u0013\u0001\u0003\u0012\u0003\u0006IA\u0011\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006'\u0002!\t\u0005\u0016\u0005\b;\u0002\t\t\u0011\"\u0001_\u0011\u001d\u0019\u0007!%A\u0005\u0002\u0011Dqa\u001c\u0001\u0012\u0002\u0013\u0005A\rC\u0004q\u0001E\u0005I\u0011\u00013\t\u000fE\u0004\u0011\u0013!C\u0001I\"9!\u000fAA\u0001\n\u0003\u001a\bbB>\u0001\u0003\u0003%\t!\u0011\u0005\by\u0002\t\t\u0011\"\u0001~\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0001\u0002\u001a!I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003S\u0001\u0011\u0011!C!\u0003WA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\b\u0013\u0005-\u0003%!A\t\u0002\u00055c\u0001C\u0010!\u0003\u0003E\t!a\u0014\t\r1KB\u0011AA4\u0011%\tI'GA\u0001\n\u000b\nY\u0007C\u0005\u0002ne\t\t\u0011\"!\u0002p!I\u0011\u0011P\r\u0002\u0002\u0013\u0005\u00151\u0010\u0005\n\u0003\u001bK\u0012\u0011!C\u0005\u0003\u001f\u00131c\u00155vM\u001adWM\u00117pG.\u001c\u0005.\u001e8l\u0013\u0012T!!\t\u0012\u0002\u000fM$xN]1hK*\u00111\u0005J\u0001\u0006gB\f'o\u001b\u0006\u0003K\u0019\na!\u00199bG\",'\"A\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001Qc\u0006\u000e\t\u0003W1j\u0011\u0001I\u0005\u0003[\u0001\u0012qA\u00117pG.LE\r\u0005\u00020e5\t\u0001GC\u00012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0004GA\u0004Qe>$Wo\u0019;\u0011\u0005UjdB\u0001\u001c<\u001d\t9$(D\u00019\u0015\tI\u0004&\u0001\u0004=e>|GOP\u0005\u0002c%\u0011A\bM\u0001\ba\u0006\u001c7.Y4f\u0013\tqtH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002=a\u0005I1\u000f[;gM2,\u0017\nZ\u000b\u0002\u0005B\u0011qfQ\u0005\u0003\tB\u00121!\u00138u\u0003)\u0019\b.\u001e4gY\u0016LE\rI\u0001\u000fg\",hM\u001a7f\u001b\u0016\u0014x-Z%e\u0003=\u0019\b.\u001e4gY\u0016lUM]4f\u0013\u0012\u0004\u0013\u0001\u0003:fIV\u001cW-\u00133\u0002\u0013I,G-^2f\u0013\u0012\u0004\u0013aB2ik:\\\u0017\nZ\u0001\tG\",hn[%eA\u00051A(\u001b8jiz\"RAT(Q#J\u0003\"a\u000b\u0001\t\u000b\u0001K\u0001\u0019\u0001\"\t\u000b\u0019K\u0001\u0019\u0001\"\t\u000b!K\u0001\u0019\u0001\"\t\u000b)K\u0001\u0019\u0001\"\u0002\t9\fW.Z\u000b\u0002+B\u0011aK\u0017\b\u0003/b\u0003\"a\u000e\u0019\n\u0005e\u0003\u0014A\u0002)sK\u0012,g-\u0003\u0002\\9\n11\u000b\u001e:j]\u001eT!!\u0017\u0019\u0002\t\r|\u0007/\u001f\u000b\u0006\u001d~\u0003\u0017M\u0019\u0005\b\u0001.\u0001\n\u00111\u0001C\u0011\u001d15\u0002%AA\u0002\tCq\u0001S\u0006\u0011\u0002\u0003\u0007!\tC\u0004K\u0017A\u0005\t\u0019\u0001\"\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tQM\u000b\u0002CM.\nq\r\u0005\u0002i[6\t\u0011N\u0003\u0002kW\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003YB\n!\"\u00198o_R\fG/[8o\u0013\tq\u0017NA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%i\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001e\t\u0003kjl\u0011A\u001e\u0006\u0003ob\fA\u0001\\1oO*\t\u00110\u0001\u0003kCZ\f\u0017BA.w\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$2A`A\u0002!\tys0C\u0002\u0002\u0002A\u00121!\u00118z\u0011!\t)AEA\u0001\u0002\u0004\u0011\u0015a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\fA)\u0011QBA\n}6\u0011\u0011q\u0002\u0006\u0004\u0003#\u0001\u0014AC2pY2,7\r^5p]&!\u0011QCA\b\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005m\u0011\u0011\u0005\t\u0004_\u0005u\u0011bAA\u0010a\t9!i\\8mK\u0006t\u0007\u0002CA\u0003)\u0005\u0005\t\u0019\u0001@\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004i\u0006\u001d\u0002\u0002CA\u0003+\u0005\u0005\t\u0019\u0001\"\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AQ\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005m\u0011\u0011\u0007\u0005\t\u0003\u000b9\u0012\u0011!a\u0001}\"*\u0001!!\u000e\u0002@A!\u0011qGA\u001e\u001b\t\tID\u0003\u0002mE%!\u0011QHA\u001d\u0005\u0015\u0019\u0016N\\2fC\t\t\t%A\u00034]Ir\u0003\u0007K\u0002\u0001\u0003\u000b\u0002B!a\u000e\u0002H%!\u0011\u0011JA\u001d\u00051!UM^3m_B,'/\u00119j\u0003M\u0019\u0006.\u001e4gY\u0016\u0014En\\2l\u0007\",hn[%e!\tY\u0013dE\u0003\u001a\u0003#\ni\u0006E\u0005\u0002T\u0005e#I\u0011\"C\u001d6\u0011\u0011Q\u000b\u0006\u0004\u0003/\u0002\u0014a\u0002:v]RLW.Z\u0005\u0005\u00037\n)FA\tBEN$(/Y2u\rVt7\r^5p]R\u0002B!a\u0018\u0002f5\u0011\u0011\u0011\r\u0006\u0004\u0003GB\u0018AA5p\u0013\rq\u0014\u0011\r\u000b\u0003\u0003\u001b\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002i\u0006)\u0011\r\u001d9msRIa*!\u001d\u0002t\u0005U\u0014q\u000f\u0005\u0006\u0001r\u0001\rA\u0011\u0005\u0006\rr\u0001\rA\u0011\u0005\u0006\u0011r\u0001\rA\u0011\u0005\u0006\u0015r\u0001\rAQ\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti(!#\u0011\u000b=\ny(a!\n\u0007\u0005\u0005\u0005G\u0001\u0004PaRLwN\u001c\t\b_\u0005\u0015%I\u0011\"C\u0013\r\t9\t\r\u0002\u0007)V\u0004H.\u001a\u001b\t\u0011\u0005-U$!AA\u00029\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\nE\u0002v\u0003'K1!!&w\u0005\u0019y%M[3di\u0002"
)
public class ShuffleBlockChunkId extends BlockId implements Product, Serializable {
   private final int shuffleId;
   private final int shuffleMergeId;
   private final int reduceId;
   private final int chunkId;

   public static Option unapply(final ShuffleBlockChunkId x$0) {
      return ShuffleBlockChunkId$.MODULE$.unapply(x$0);
   }

   public static ShuffleBlockChunkId apply(final int shuffleId, final int shuffleMergeId, final int reduceId, final int chunkId) {
      return ShuffleBlockChunkId$.MODULE$.apply(shuffleId, shuffleMergeId, reduceId, chunkId);
   }

   public static Function1 tupled() {
      return ShuffleBlockChunkId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleBlockChunkId$.MODULE$.curried();
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

   public int reduceId() {
      return this.reduceId;
   }

   public int chunkId() {
      return this.chunkId;
   }

   public String name() {
      int var10000 = this.shuffleId();
      return "shuffleChunk_" + var10000 + "_" + this.shuffleMergeId() + "_" + this.reduceId() + "_" + this.chunkId();
   }

   public ShuffleBlockChunkId copy(final int shuffleId, final int shuffleMergeId, final int reduceId, final int chunkId) {
      return new ShuffleBlockChunkId(shuffleId, shuffleMergeId, reduceId, chunkId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public int copy$default$2() {
      return this.shuffleMergeId();
   }

   public int copy$default$3() {
      return this.reduceId();
   }

   public int copy$default$4() {
      return this.chunkId();
   }

   public String productPrefix() {
      return "ShuffleBlockChunkId";
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
            return BoxesRunTime.boxToInteger(this.reduceId());
         }
         case 3 -> {
            return BoxesRunTime.boxToInteger(this.chunkId());
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
      return x$1 instanceof ShuffleBlockChunkId;
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
            return "reduceId";
         }
         case 3 -> {
            return "chunkId";
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
      var1 = Statics.mix(var1, this.reduceId());
      var1 = Statics.mix(var1, this.chunkId());
      return Statics.finalizeHash(var1, 4);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label42: {
            if (x$1 instanceof ShuffleBlockChunkId) {
               ShuffleBlockChunkId var4 = (ShuffleBlockChunkId)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.shuffleMergeId() == var4.shuffleMergeId() && this.reduceId() == var4.reduceId() && this.chunkId() == var4.chunkId() && var4.canEqual(this)) {
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

   public ShuffleBlockChunkId(final int shuffleId, final int shuffleMergeId, final int reduceId, final int chunkId) {
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.reduceId = reduceId;
      this.chunkId = chunkId;
      Product.$init$(this);
   }
}
