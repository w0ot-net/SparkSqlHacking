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
   bytes = "\u0006\u0005\u0005\u0015e\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005I\u0001\tE\t\u0015!\u0003F\u0011!I\u0005A!f\u0001\n\u0003q\u0004\u0002\u0003&\u0001\u0005#\u0005\u000b\u0011B \t\u000b-\u0003A\u0011\u0001'\t\u000bE\u0003A\u0011\t*\t\u000fm\u0003\u0011\u0011!C\u00019\"9\u0001\rAI\u0001\n\u0003\t\u0007b\u00027\u0001#\u0003%\t!\u001c\u0005\b_\u0002\t\n\u0011\"\u0001b\u0011\u001d\u0001\b!!A\u0005BEDq!\u001f\u0001\u0002\u0002\u0013\u0005a\bC\u0004{\u0001\u0005\u0005I\u0011A>\t\u0013\u0005\r\u0001!!A\u0005B\u0005\u0015\u0001\"CA\n\u0001\u0005\u0005I\u0011AA\u000b\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131F\u0004\n\u0003wi\u0012\u0011!E\u0001\u0003{1\u0001\u0002H\u000f\u0002\u0002#\u0005\u0011q\b\u0005\u0007\u0017Z!\t!a\u0016\t\u0013\u0005ec#!A\u0005F\u0005m\u0003\"CA/-\u0005\u0005I\u0011QA0\u0011%\t9GFA\u0001\n\u0003\u000bI\u0007C\u0005\u0002|Y\t\t\u0011\"\u0003\u0002~\t\u00112\u000b[;gM2,G)\u0019;b\u00052|7m[%e\u0015\tqr$A\u0004ti>\u0014\u0018mZ3\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001O-\n\u0004C\u0001\u0015*\u001b\u0005i\u0012B\u0001\u0016\u001e\u0005\u001d\u0011En\\2l\u0013\u0012\u0004\"\u0001L\u0018\u000e\u00035R\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003a5\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00023u9\u00111\u0007\u000f\b\u0003i]j\u0011!\u000e\u0006\u0003m\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0018\n\u0005ej\u0013a\u00029bG.\fw-Z\u0005\u0003wq\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!O\u0017\u0002\u0013MDWO\u001a4mK&#W#A \u0011\u00051\u0002\u0015BA!.\u0005\rIe\u000e^\u0001\u000bg\",hM\u001a7f\u0013\u0012\u0004\u0013!B7ba&#W#A#\u0011\u000512\u0015BA$.\u0005\u0011auN\\4\u0002\r5\f\u0007/\u00133!\u0003!\u0011X\rZ;dK&#\u0017!\u0003:fIV\u001cW-\u00133!\u0003\u0019a\u0014N\\5u}Q!QJT(Q!\tA\u0003\u0001C\u0003>\u000f\u0001\u0007q\bC\u0003D\u000f\u0001\u0007Q\tC\u0003J\u000f\u0001\u0007q(\u0001\u0003oC6,W#A*\u0011\u0005QCfBA+W!\t!T&\u0003\u0002X[\u00051\u0001K]3eK\u001aL!!\u0017.\u0003\rM#(/\u001b8h\u0015\t9V&\u0001\u0003d_BLH\u0003B'^=~Cq!P\u0005\u0011\u0002\u0003\u0007q\bC\u0004D\u0013A\u0005\t\u0019A#\t\u000f%K\u0001\u0013!a\u0001\u007f\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00012+\u0005}\u001a7&\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017!C;oG\",7m[3e\u0015\tIW&\u0001\u0006b]:|G/\u0019;j_:L!a\u001b4\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u00039T#!R2\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001d\t\u0003gbl\u0011\u0001\u001e\u0006\u0003kZ\fA\u0001\\1oO*\tq/\u0001\u0003kCZ\f\u0017BA-u\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001`@\u0011\u00051j\u0018B\u0001@.\u0005\r\te.\u001f\u0005\t\u0003\u0003y\u0011\u0011!a\u0001\u007f\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0002\u0011\u000b\u0005%\u0011q\u0002?\u000e\u0005\u0005-!bAA\u0007[\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00111\u0002\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0018\u0005u\u0001c\u0001\u0017\u0002\u001a%\u0019\u00111D\u0017\u0003\u000f\t{w\u000e\\3b]\"A\u0011\u0011A\t\u0002\u0002\u0003\u0007A0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001:\u0002$!A\u0011\u0011\u0001\n\u0002\u0002\u0003\u0007q(\u0001\u0005iCND7i\u001c3f)\u0005y\u0014AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0018\u00055\u0002\u0002CA\u0001)\u0005\u0005\t\u0019\u0001?)\u0007\u0001\t\t\u0004\u0005\u0003\u00024\u0005]RBAA\u001b\u0015\tIw$\u0003\u0003\u0002:\u0005U\"\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017AE*ik\u001a4G.\u001a#bi\u0006\u0014En\\2l\u0013\u0012\u0004\"\u0001\u000b\f\u0014\u000bY\t\t%!\u0014\u0011\u0011\u0005\r\u0013\u0011J F\u007f5k!!!\u0012\u000b\u0007\u0005\u001dS&A\u0004sk:$\u0018.\\3\n\t\u0005-\u0013Q\t\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA(\u0003+j!!!\u0015\u000b\u0007\u0005Mc/\u0001\u0002j_&\u00191(!\u0015\u0015\u0005\u0005u\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003I\fQ!\u00199qYf$r!TA1\u0003G\n)\u0007C\u0003>3\u0001\u0007q\bC\u0003D3\u0001\u0007Q\tC\u0003J3\u0001\u0007q(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u0014q\u000f\t\u0006Y\u00055\u0014\u0011O\u0005\u0004\u0003_j#AB(qi&|g\u000e\u0005\u0004-\u0003gzTiP\u0005\u0004\u0003kj#A\u0002+va2,7\u0007\u0003\u0005\u0002zi\t\t\u00111\u0001N\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u007f\u00022a]AA\u0013\r\t\u0019\t\u001e\u0002\u0007\u001f\nTWm\u0019;"
)
public class ShuffleDataBlockId extends BlockId implements Product, Serializable {
   private final int shuffleId;
   private final long mapId;
   private final int reduceId;

   public static Option unapply(final ShuffleDataBlockId x$0) {
      return ShuffleDataBlockId$.MODULE$.unapply(x$0);
   }

   public static ShuffleDataBlockId apply(final int shuffleId, final long mapId, final int reduceId) {
      return ShuffleDataBlockId$.MODULE$.apply(shuffleId, mapId, reduceId);
   }

   public static Function1 tupled() {
      return ShuffleDataBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleDataBlockId$.MODULE$.curried();
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
      return "shuffle_" + var10000 + "_" + this.mapId() + "_" + this.reduceId() + ".data";
   }

   public ShuffleDataBlockId copy(final int shuffleId, final long mapId, final int reduceId) {
      return new ShuffleDataBlockId(shuffleId, mapId, reduceId);
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
      return "ShuffleDataBlockId";
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
      return x$1 instanceof ShuffleDataBlockId;
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
            if (x$1 instanceof ShuffleDataBlockId) {
               ShuffleDataBlockId var4 = (ShuffleDataBlockId)x$1;
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

   public ShuffleDataBlockId(final int shuffleId, final long mapId, final int reduceId) {
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.reduceId = reduceId;
      Product.$init$(this);
   }
}
