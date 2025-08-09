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
   bytes = "\u0006\u0005\u0005me\u0001B\u0010!\u0001&B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u0005\"A1\n\u0001BK\u0002\u0013\u0005A\n\u0003\u0005Q\u0001\tE\t\u0015!\u0003N\u0011!\t\u0006A!f\u0001\n\u0003a\u0005\u0002\u0003*\u0001\u0005#\u0005\u000b\u0011B'\t\u0011M\u0003!Q3A\u0005\u00021C\u0001\u0002\u0016\u0001\u0003\u0012\u0003\u0006I!\u0014\u0005\u0006+\u0002!\tA\u0016\u0005\u00069\u0002!\t%\u0011\u0005\b;\u0002\t\t\u0011\"\u0001_\u0011\u001d\u0019\u0007!%A\u0005\u0002\u0011Dqa\u001c\u0001\u0012\u0002\u0013\u0005\u0001\u000fC\u0004s\u0001E\u0005I\u0011\u00019\t\u000fM\u0004\u0011\u0013!C\u0001a\"9A\u000fAA\u0001\n\u0003*\bbB?\u0001\u0003\u0003%\t\u0001\u0014\u0005\b}\u0002\t\t\u0011\"\u0001\u0000\u0011%\tY\u0001AA\u0001\n\u0003\ni\u0001C\u0005\u0002\u001c\u0001\t\t\u0011\"\u0001\u0002\u001e!I\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0006\u0005\n\u0003[\u0001\u0011\u0011!C!\u0003_A\u0011\"!\r\u0001\u0003\u0003%\t%a\r\b\u0013\u0005=\u0003%!A\t\u0002\u0005Ec\u0001C\u0010!\u0003\u0003E\t!a\u0015\t\rUKB\u0011AA6\u0011%\ti'GA\u0001\n\u000b\ny\u0007C\u0005\u0002re\t\t\u0011\"!\u0002t!I\u0011QP\r\u0002\u0002\u0013\u0005\u0015q\u0010\u0005\n\u0003#K\u0012\u0011!C\u0005\u0003'\u0013\u0001d\u00155vM\u001adW-T3sO\u0016$G)\u0019;b\u00052|7m[%e\u0015\t\t#%A\u0004ti>\u0014\u0018mZ3\u000b\u0005\r\"\u0013!B:qCJ\\'BA\u0013'\u0003\u0019\t\u0007/Y2iK*\tq%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001U9\"\u0004CA\u0016-\u001b\u0005\u0001\u0013BA\u0017!\u0005\u001d\u0011En\\2l\u0013\u0012\u0004\"a\f\u001a\u000e\u0003AR\u0011!M\u0001\u0006g\u000e\fG.Y\u0005\u0003gA\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00026{9\u0011ag\u000f\b\u0003oij\u0011\u0001\u000f\u0006\u0003s!\na\u0001\u0010:p_Rt\u0014\"A\u0019\n\u0005q\u0002\u0014a\u00029bG.\fw-Z\u0005\u0003}}\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0010\u0019\u0002\u000b\u0005\u0004\b/\u00133\u0016\u0003\t\u0003\"aQ$\u000f\u0005\u0011+\u0005CA\u001c1\u0013\t1\u0005'\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0011&\u0013aa\u0015;sS:<'B\u0001$1\u0003\u0019\t\u0007\u000f]%eA\u0005I1\u000f[;gM2,\u0017\nZ\u000b\u0002\u001bB\u0011qFT\u0005\u0003\u001fB\u00121!\u00138u\u0003)\u0019\b.\u001e4gY\u0016LE\rI\u0001\u000fg\",hM\u001a7f\u001b\u0016\u0014x-Z%e\u0003=\u0019\b.\u001e4gY\u0016lUM]4f\u0013\u0012\u0004\u0013\u0001\u0003:fIV\u001cW-\u00133\u0002\u0013I,G-^2f\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0003X1fS6\f\u0005\u0002,\u0001!)\u0001)\u0003a\u0001\u0005\")1*\u0003a\u0001\u001b\")\u0011+\u0003a\u0001\u001b\")1+\u0003a\u0001\u001b\u0006!a.Y7f\u0003\u0011\u0019w\u000e]=\u0015\u000b]{\u0006-\u00192\t\u000f\u0001[\u0001\u0013!a\u0001\u0005\"91j\u0003I\u0001\u0002\u0004i\u0005bB)\f!\u0003\u0005\r!\u0014\u0005\b'.\u0001\n\u00111\u0001N\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u001a\u0016\u0003\u0005\u001a\\\u0013a\u001a\t\u0003Q6l\u0011!\u001b\u0006\u0003U.\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00051\u0004\u0014AC1o]>$\u0018\r^5p]&\u0011a.\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002c*\u0012QJZ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001<\u0011\u0005]dX\"\u0001=\u000b\u0005eT\u0018\u0001\u00027b]\u001eT\u0011a_\u0001\u0005U\u00064\u0018-\u0003\u0002Iq\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0001\u0003\u000f\u00012aLA\u0002\u0013\r\t)\u0001\r\u0002\u0004\u0003:L\b\u0002CA\u0005%\u0005\u0005\t\u0019A'\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0001\u0005\u0004\u0002\u0012\u0005]\u0011\u0011A\u0007\u0003\u0003'Q1!!\u00061\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00033\t\u0019B\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0010\u0003K\u00012aLA\u0011\u0013\r\t\u0019\u0003\r\u0002\b\u0005>|G.Z1o\u0011%\tI\u0001FA\u0001\u0002\u0004\t\t!\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001<\u0002,!A\u0011\u0011B\u000b\u0002\u0002\u0003\u0007Q*\u0001\u0005iCND7i\u001c3f)\u0005i\u0015AB3rk\u0006d7\u000f\u0006\u0003\u0002 \u0005U\u0002\"CA\u0005/\u0005\u0005\t\u0019AA\u0001Q\u0015\u0001\u0011\u0011HA\"!\u0011\tY$a\u0010\u000e\u0005\u0005u\"B\u00017#\u0013\u0011\t\t%!\u0010\u0003\u000bMKgnY3\"\u0005\u0005\u0015\u0013!B\u001a/e9\u0002\u0004f\u0001\u0001\u0002JA!\u00111HA&\u0013\u0011\ti%!\u0010\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u00021MCWO\u001a4mK6+'oZ3e\t\u0006$\u0018M\u00117pG.LE\r\u0005\u0002,3M)\u0011$!\u0016\u0002bAI\u0011qKA/\u00056kUjV\u0007\u0003\u00033R1!a\u00171\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0018\u0002Z\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001b\u0011\t\u0005\r\u0014\u0011N\u0007\u0003\u0003KR1!a\u001a{\u0003\tIw.C\u0002?\u0003K\"\"!!\u0015\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A^\u0001\u0006CB\u0004H.\u001f\u000b\n/\u0006U\u0014qOA=\u0003wBQ\u0001\u0011\u000fA\u0002\tCQa\u0013\u000fA\u00025CQ!\u0015\u000fA\u00025CQa\u0015\u000fA\u00025\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0002\u00065\u0005#B\u0018\u0002\u0004\u0006\u001d\u0015bAACa\t1q\n\u001d;j_:\u0004raLAE\u00056kU*C\u0002\u0002\fB\u0012a\u0001V;qY\u0016$\u0004\u0002CAH;\u0005\u0005\t\u0019A,\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0016B\u0019q/a&\n\u0007\u0005e\u0005P\u0001\u0004PE*,7\r\u001e"
)
public class ShuffleMergedDataBlockId extends BlockId implements Product, Serializable {
   private final String appId;
   private final int shuffleId;
   private final int shuffleMergeId;
   private final int reduceId;

   public static Option unapply(final ShuffleMergedDataBlockId x$0) {
      return ShuffleMergedDataBlockId$.MODULE$.unapply(x$0);
   }

   public static ShuffleMergedDataBlockId apply(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId) {
      return ShuffleMergedDataBlockId$.MODULE$.apply(appId, shuffleId, shuffleMergeId, reduceId);
   }

   public static Function1 tupled() {
      return ShuffleMergedDataBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleMergedDataBlockId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String appId() {
      return this.appId;
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

   public String name() {
      String var10000 = this.appId();
      return "shuffleMerged_" + var10000 + "_" + this.shuffleId() + "_" + this.shuffleMergeId() + "_" + this.reduceId() + ".data";
   }

   public ShuffleMergedDataBlockId copy(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId) {
      return new ShuffleMergedDataBlockId(appId, shuffleId, shuffleMergeId, reduceId);
   }

   public String copy$default$1() {
      return this.appId();
   }

   public int copy$default$2() {
      return this.shuffleId();
   }

   public int copy$default$3() {
      return this.shuffleMergeId();
   }

   public int copy$default$4() {
      return this.reduceId();
   }

   public String productPrefix() {
      return "ShuffleMergedDataBlockId";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.appId();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.shuffleMergeId());
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
      return x$1 instanceof ShuffleMergedDataBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "appId";
         }
         case 1 -> {
            return "shuffleId";
         }
         case 2 -> {
            return "shuffleMergeId";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.appId()));
      var1 = Statics.mix(var1, this.shuffleId());
      var1 = Statics.mix(var1, this.shuffleMergeId());
      var1 = Statics.mix(var1, this.reduceId());
      return Statics.finalizeHash(var1, 4);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ShuffleMergedDataBlockId) {
               ShuffleMergedDataBlockId var4 = (ShuffleMergedDataBlockId)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.shuffleMergeId() == var4.shuffleMergeId() && this.reduceId() == var4.reduceId()) {
                  label52: {
                     String var10000 = this.appId();
                     String var5 = var4.appId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
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

   public ShuffleMergedDataBlockId(final String appId, final int shuffleId, final int shuffleMergeId, final int reduceId) {
      this.appId = appId;
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.reduceId = reduceId;
      Product.$init$(this);
   }
}
