package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.rpc.RpcCallContext;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc!B\r\u001b\u0001j\u0001\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011\u0001\u0003!\u0011#Q\u0001\nuB\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\u0007\")!\n\u0001C\u0001\u0017\"9q\nAA\u0001\n\u0003\u0001\u0006bB*\u0001#\u0003%\t\u0001\u0016\u0005\b?\u0002\t\n\u0011\"\u0001a\u0011\u001d\u0011\u0007!!A\u0005B\rDq\u0001\u001c\u0001\u0002\u0002\u0013\u0005A\bC\u0004n\u0001\u0005\u0005I\u0011\u00018\t\u000fQ\u0004\u0011\u0011!C!k\"9A\u0010AA\u0001\n\u0003i\b\"CA\u0003\u0001\u0005\u0005I\u0011IA\u0004\u0011%\tY\u0001AA\u0001\n\u0003\ni\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0011\u0002\u0012!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013QC\u0004\u000b\u00033Q\u0012\u0011!E\u00015\u0005ma!C\r\u001b\u0003\u0003E\tAGA\u000f\u0011\u0019Q5\u0003\"\u0001\u00026!I\u0011qB\n\u0002\u0002\u0013\u0015\u0013\u0011\u0003\u0005\n\u0003o\u0019\u0012\u0011!CA\u0003sA\u0011\"a\u0010\u0014\u0003\u0003%\t)!\u0011\t\u0013\u0005M3#!A\u0005\n\u0005U#aG$fi6\u000b\u0007/\u00118e\u001b\u0016\u0014x-Z(viB,H/T3tg\u0006<WM\u0003\u0002\u001c9\u0005)1\u000f]1sW*\u0011QDH\u0001\u0007CB\f7\r[3\u000b\u0003}\t1a\u001c:h'\u0015\u0001\u0011eJ\u0016/!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fMB\u0011\u0001&K\u0007\u00025%\u0011!F\u0007\u0002\u001e\u001b\u0006\u0004x*\u001e;qkR$&/Y2lKJl\u0015m\u001d;fe6+7o]1hKB\u0011!\u0005L\u0005\u0003[\r\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00020q9\u0011\u0001G\u000e\b\u0003cUj\u0011A\r\u0006\u0003gQ\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002I%\u0011qgI\u0001\ba\u0006\u001c7.Y4f\u0013\tI$H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00028G\u0005I1\u000f[;gM2,\u0017\nZ\u000b\u0002{A\u0011!EP\u0005\u0003\u007f\r\u00121!\u00138u\u0003)\u0019\b.\u001e4gY\u0016LE\rI\u0001\bG>tG/\u001a=u+\u0005\u0019\u0005C\u0001#H\u001b\u0005)%B\u0001$\u001b\u0003\r\u0011\boY\u0005\u0003\u0011\u0016\u0013aB\u00159d\u0007\u0006dGnQ8oi\u0016DH/\u0001\u0005d_:$X\r\u001f;!\u0003\u0019a\u0014N\\5u}Q\u0019A*\u0014(\u0011\u0005!\u0002\u0001\"B\u001e\u0006\u0001\u0004i\u0004\"B!\u0006\u0001\u0004\u0019\u0015\u0001B2paf$2\u0001T)S\u0011\u001dYd\u0001%AA\u0002uBq!\u0011\u0004\u0011\u0002\u0003\u00071)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003US#!\u0010,,\u0003]\u0003\"\u0001W/\u000e\u0003eS!AW.\u0002\u0013Ut7\r[3dW\u0016$'B\u0001/$\u0003)\tgN\\8uCRLwN\\\u0005\u0003=f\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012!\u0019\u0016\u0003\u0007Z\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017\u0001\u00027b]\u001eT\u0011![\u0001\u0005U\u00064\u0018-\u0003\u0002lM\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002peB\u0011!\u0005]\u0005\u0003c\u000e\u00121!\u00118z\u0011\u001d\u00198\"!AA\u0002u\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001<\u0011\u0007]Tx.D\u0001y\u0015\tI8%\u0001\u0006d_2dWm\u0019;j_:L!a\u001f=\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004}\u0006\r\u0001C\u0001\u0012\u0000\u0013\r\t\ta\t\u0002\b\u0005>|G.Z1o\u0011\u001d\u0019X\"!AA\u0002=\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019A-!\u0003\t\u000fMt\u0011\u0011!a\u0001{\u0005A\u0001.Y:i\u0007>$W\rF\u0001>\u0003!!xn\u0015;sS:<G#\u00013\u0002\r\u0015\fX/\u00197t)\rq\u0018q\u0003\u0005\bgF\t\t\u00111\u0001p\u0003m9U\r^'ba\u0006sG-T3sO\u0016|U\u000f\u001e9vi6+7o]1hKB\u0011\u0001fE\n\u0006'\u0005}\u00111\u0006\t\b\u0003C\t9#P\"M\u001b\t\t\u0019CC\u0002\u0002&\r\nqA];oi&lW-\u0003\u0003\u0002*\u0005\r\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011QFA\u001a\u001b\t\tyCC\u0002\u00022!\f!![8\n\u0007e\ny\u0003\u0006\u0002\u0002\u001c\u0005)\u0011\r\u001d9msR)A*a\u000f\u0002>!)1H\u0006a\u0001{!)\u0011I\u0006a\u0001\u0007\u00069QO\\1qa2LH\u0003BA\"\u0003\u001f\u0002RAIA#\u0003\u0013J1!a\u0012$\u0005\u0019y\u0005\u000f^5p]B)!%a\u0013>\u0007&\u0019\u0011QJ\u0012\u0003\rQ+\b\u000f\\33\u0011!\t\tfFA\u0001\u0002\u0004a\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u000b\t\u0004K\u0006e\u0013bAA.M\n1qJ\u00196fGR\u0004"
)
public class GetMapAndMergeOutputMessage implements MapOutputTrackerMasterMessage, Product, Serializable {
   private final int shuffleId;
   private final RpcCallContext context;

   public static Option unapply(final GetMapAndMergeOutputMessage x$0) {
      return GetMapAndMergeOutputMessage$.MODULE$.unapply(x$0);
   }

   public static GetMapAndMergeOutputMessage apply(final int shuffleId, final RpcCallContext context) {
      return GetMapAndMergeOutputMessage$.MODULE$.apply(shuffleId, context);
   }

   public static Function1 tupled() {
      return GetMapAndMergeOutputMessage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return GetMapAndMergeOutputMessage$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public RpcCallContext context() {
      return this.context;
   }

   public GetMapAndMergeOutputMessage copy(final int shuffleId, final RpcCallContext context) {
      return new GetMapAndMergeOutputMessage(shuffleId, context);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public RpcCallContext copy$default$2() {
      return this.context();
   }

   public String productPrefix() {
      return "GetMapAndMergeOutputMessage";
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
            return this.context();
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
      return x$1 instanceof GetMapAndMergeOutputMessage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         case 1 -> {
            return "context";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.context()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof GetMapAndMergeOutputMessage) {
               GetMapAndMergeOutputMessage var4 = (GetMapAndMergeOutputMessage)x$1;
               if (this.shuffleId() == var4.shuffleId()) {
                  label44: {
                     RpcCallContext var10000 = this.context();
                     RpcCallContext var5 = var4.context();
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

   public GetMapAndMergeOutputMessage(final int shuffleId, final RpcCallContext context) {
      this.shuffleId = shuffleId;
      this.context = context;
      Product.$init$(this);
   }
}
