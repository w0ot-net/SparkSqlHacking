package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d!\u0002\u000f\u001e\u0001v)\u0003\u0002\u0003!\u0001\u0005+\u0007I\u0011A!\t\u0011\u0015\u0003!\u0011#Q\u0001\n\tC\u0001B\u0012\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\u0005\"A\u0001\n\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005J\u0001\tE\t\u0015!\u0003C\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001d\u0001\u0006!!A\u0005\u0002ECq!\u0016\u0001\u0012\u0002\u0013\u0005a\u000bC\u0004b\u0001E\u0005I\u0011\u0001,\t\u000f\t\u0004\u0011\u0013!C\u0001-\"91\rAA\u0001\n\u0003\"\u0007bB7\u0001\u0003\u0003%\t!\u0011\u0005\b]\u0002\t\t\u0011\"\u0001p\u0011\u001d)\b!!A\u0005BYDq! \u0001\u0002\u0002\u0013\u0005a\u0010C\u0005\u0002\b\u0001\t\t\u0011\"\u0011\u0002\n!I\u0011Q\u0002\u0001\u0002\u0002\u0013\u0005\u0013q\u0002\u0005\n\u0003#\u0001\u0011\u0011!C!\u0003'A\u0011\"!\u0006\u0001\u0003\u0003%\t%a\u0006\b\u0015\u0005mQ$!A\t\u0002u\tiBB\u0005\u001d;\u0005\u0005\t\u0012A\u000f\u0002 !1!J\u0006C\u0001\u0003oA\u0011\"!\u0005\u0017\u0003\u0003%)%a\u0005\t\u0013\u0005eb#!A\u0005\u0002\u0006m\u0002\"CA\"-\u0005\u0005I\u0011QA#\u0011%\t9FFA\u0001\n\u0013\tIF\u0001\u000bTQV4g\r\\3QkND7i\\7qY\u0016$X\r\u001a\u0006\u0003=}\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001cR\u0001\u0001\u0014-aM\u0002\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u0012a!\u00118z%\u00164\u0007CA\u0017/\u001b\u0005i\u0012BA\u0018\u001e\u0005E!\u0015iR*dQ\u0016$W\u000f\\3s\u000bZ,g\u000e\u001e\t\u0003OEJ!A\r\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011A'\u0010\b\u0003kmr!A\u000e\u001e\u000e\u0003]R!\u0001O\u001d\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!K\u0005\u0003y!\nq\u0001]1dW\u0006<W-\u0003\u0002?\u007f\ta1+\u001a:jC2L'0\u00192mK*\u0011A\bK\u0001\ng\",hM\u001a7f\u0013\u0012,\u0012A\u0011\t\u0003O\rK!\u0001\u0012\u0015\u0003\u0007%sG/\u0001\u0006tQV4g\r\\3JI\u0002\nab\u001d5vM\u001adW-T3sO\u0016LE-A\btQV4g\r\\3NKJ<W-\u00133!\u0003!i\u0017\r]%oI\u0016D\u0018!C7ba&sG-\u001a=!\u0003\u0019a\u0014N\\5u}Q!A*\u0014(P!\ti\u0003\u0001C\u0003A\u000f\u0001\u0007!\tC\u0003G\u000f\u0001\u0007!\tC\u0003I\u000f\u0001\u0007!)\u0001\u0003d_BLH\u0003\u0002'S'RCq\u0001\u0011\u0005\u0011\u0002\u0003\u0007!\tC\u0004G\u0011A\u0005\t\u0019\u0001\"\t\u000f!C\u0001\u0013!a\u0001\u0005\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A,+\u0005\tC6&A-\u0011\u0005i{V\"A.\u000b\u0005qk\u0016!C;oG\",7m[3e\u0015\tq\u0006&\u0001\u0006b]:|G/\u0019;j_:L!\u0001Y.\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u001a\t\u0003M.l\u0011a\u001a\u0006\u0003Q&\fA\u0001\\1oO*\t!.\u0001\u0003kCZ\f\u0017B\u00017h\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00019t!\t9\u0013/\u0003\u0002sQ\t\u0019\u0011I\\=\t\u000fQt\u0011\u0011!a\u0001\u0005\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a\u001e\t\u0004qn\u0004X\"A=\u000b\u0005iD\u0013AC2pY2,7\r^5p]&\u0011A0\u001f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002\u0000\u0003\u000b\u00012aJA\u0001\u0013\r\t\u0019\u0001\u000b\u0002\b\u0005>|G.Z1o\u0011\u001d!\b#!AA\u0002A\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019Q-a\u0003\t\u000fQ\f\u0012\u0011!a\u0001\u0005\u0006A\u0001.Y:i\u0007>$W\rF\u0001C\u0003!!xn\u0015;sS:<G#A3\u0002\r\u0015\fX/\u00197t)\ry\u0018\u0011\u0004\u0005\biR\t\t\u00111\u0001q\u0003Q\u0019\u0006.\u001e4gY\u0016\u0004Vo\u001d5D_6\u0004H.\u001a;fIB\u0011QFF\n\u0006-\u0005\u0005\u0012Q\u0006\t\t\u0003G\tIC\u0011\"C\u00196\u0011\u0011Q\u0005\u0006\u0004\u0003OA\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003W\t)CA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!a\f\u000265\u0011\u0011\u0011\u0007\u0006\u0004\u0003gI\u0017AA5p\u0013\rq\u0014\u0011\u0007\u000b\u0003\u0003;\tQ!\u00199qYf$r\u0001TA\u001f\u0003\u007f\t\t\u0005C\u0003A3\u0001\u0007!\tC\u0003G3\u0001\u0007!\tC\u0003I3\u0001\u0007!)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u001d\u00131\u000b\t\u0006O\u0005%\u0013QJ\u0005\u0004\u0003\u0017B#AB(qi&|g\u000e\u0005\u0004(\u0003\u001f\u0012%IQ\u0005\u0004\u0003#B#A\u0002+va2,7\u0007\u0003\u0005\u0002Vi\t\t\u00111\u0001M\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u00037\u00022AZA/\u0013\r\tyf\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class ShufflePushCompleted implements DAGSchedulerEvent, Product, Serializable {
   private final int shuffleId;
   private final int shuffleMergeId;
   private final int mapIndex;

   public static Option unapply(final ShufflePushCompleted x$0) {
      return ShufflePushCompleted$.MODULE$.unapply(x$0);
   }

   public static ShufflePushCompleted apply(final int shuffleId, final int shuffleMergeId, final int mapIndex) {
      return ShufflePushCompleted$.MODULE$.apply(shuffleId, shuffleMergeId, mapIndex);
   }

   public static Function1 tupled() {
      return ShufflePushCompleted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShufflePushCompleted$.MODULE$.curried();
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

   public ShufflePushCompleted copy(final int shuffleId, final int shuffleMergeId, final int mapIndex) {
      return new ShufflePushCompleted(shuffleId, shuffleMergeId, mapIndex);
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

   public String productPrefix() {
      return "ShufflePushCompleted";
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
            return BoxesRunTime.boxToInteger(this.shuffleMergeId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.mapIndex());
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
      return x$1 instanceof ShufflePushCompleted;
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
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof ShufflePushCompleted) {
               ShufflePushCompleted var4 = (ShufflePushCompleted)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.shuffleMergeId() == var4.shuffleMergeId() && this.mapIndex() == var4.mapIndex() && var4.canEqual(this)) {
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

   public ShufflePushCompleted(final int shuffleId, final int shuffleMergeId, final int mapIndex) {
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.mapIndex = mapIndex;
      Product.$init$(this);
   }
}
