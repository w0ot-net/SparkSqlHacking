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
   bytes = "\u0006\u0005\u0005\u0015e\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005I\u0001\tE\t\u0015!\u0003F\u0011!I\u0005A!f\u0001\n\u0003q\u0004\u0002\u0003&\u0001\u0005#\u0005\u000b\u0011B \t\u000b-\u0003A\u0011\u0001'\t\u000bE\u0003A\u0011\t*\t\u000fm\u0003\u0011\u0011!C\u00019\"9\u0001\rAI\u0001\n\u0003\t\u0007b\u00027\u0001#\u0003%\t!\u001c\u0005\b_\u0002\t\n\u0011\"\u0001b\u0011\u001d\u0001\b!!A\u0005BEDq!\u001f\u0001\u0002\u0002\u0013\u0005a\bC\u0004{\u0001\u0005\u0005I\u0011A>\t\u0013\u0005\r\u0001!!A\u0005B\u0005\u0015\u0001\"CA\n\u0001\u0005\u0005I\u0011AA\u000b\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131F\u0004\n\u0003wi\u0012\u0011!E\u0001\u0003{1\u0001\u0002H\u000f\u0002\u0002#\u0005\u0011q\b\u0005\u0007\u0017Z!\t!a\u0016\t\u0013\u0005ec#!A\u0005F\u0005m\u0003\"CA/-\u0005\u0005I\u0011QA0\u0011%\t9GFA\u0001\n\u0003\u000bI\u0007C\u0005\u0002|Y\t\t\u0011\"\u0003\u0002~\t\u00192\u000b[;gM2,\u0017J\u001c3fq\ncwnY6JI*\u0011adH\u0001\bgR|'/Y4f\u0015\t\u0001\u0013%A\u0003ta\u0006\u00148N\u0003\u0002#G\u00051\u0011\r]1dQ\u0016T\u0011\u0001J\u0001\u0004_J<7\u0001A\n\u0005\u0001\u001dZ\u0013\u0007\u0005\u0002)S5\tQ$\u0003\u0002+;\t9!\t\\8dW&#\u0007C\u0001\u00170\u001b\u0005i#\"\u0001\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Aj#a\u0002)s_\u0012,8\r\u001e\t\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005Y*\u0013A\u0002\u001fs_>$h(C\u0001/\u0013\tIT&A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d.\u0003%\u0019\b.\u001e4gY\u0016LE-F\u0001@!\ta\u0003)\u0003\u0002B[\t\u0019\u0011J\u001c;\u0002\u0015MDWO\u001a4mK&#\u0007%A\u0003nCBLE-F\u0001F!\tac)\u0003\u0002H[\t!Aj\u001c8h\u0003\u0019i\u0017\r]%eA\u0005A!/\u001a3vG\u0016LE-A\u0005sK\u0012,8-Z%eA\u00051A(\u001b8jiz\"B!\u0014(P!B\u0011\u0001\u0006\u0001\u0005\u0006{\u001d\u0001\ra\u0010\u0005\u0006\u0007\u001e\u0001\r!\u0012\u0005\u0006\u0013\u001e\u0001\raP\u0001\u0005]\u0006lW-F\u0001T!\t!\u0006L\u0004\u0002V-B\u0011A'L\u0005\u0003/6\na\u0001\u0015:fI\u00164\u0017BA-[\u0005\u0019\u0019FO]5oO*\u0011q+L\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003N;z{\u0006bB\u001f\n!\u0003\u0005\ra\u0010\u0005\b\u0007&\u0001\n\u00111\u0001F\u0011\u001dI\u0015\u0002%AA\u0002}\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001cU\ty4mK\u0001e!\t)'.D\u0001g\u0015\t9\u0007.A\u0005v]\u000eDWmY6fI*\u0011\u0011.L\u0001\u000bC:tw\u000e^1uS>t\u0017BA6g\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005q'FA#d\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001:\u0011\u0005MDX\"\u0001;\u000b\u0005U4\u0018\u0001\u00027b]\u001eT\u0011a^\u0001\u0005U\u00064\u0018-\u0003\u0002Zi\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001?\u0000!\taS0\u0003\u0002\u007f[\t\u0019\u0011I\\=\t\u0011\u0005\u0005q\"!AA\u0002}\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0004!\u0015\tI!a\u0004}\u001b\t\tYAC\u0002\u0002\u000e5\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\t\"a\u0003\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003/\ti\u0002E\u0002-\u00033I1!a\u0007.\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u0001\u0012\u0003\u0003\u0005\r\u0001`\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002s\u0003GA\u0001\"!\u0001\u0013\u0003\u0003\u0005\raP\u0001\tQ\u0006\u001c\bnQ8eKR\tq(\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003/\ti\u0003\u0003\u0005\u0002\u0002Q\t\t\u00111\u0001}Q\r\u0001\u0011\u0011\u0007\t\u0005\u0003g\t9$\u0004\u0002\u00026)\u0011\u0011nH\u0005\u0005\u0003s\t)D\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018.A\nTQV4g\r\\3J]\u0012,\u0007P\u00117pG.LE\r\u0005\u0002)-M)a#!\u0011\u0002NAA\u00111IA%\u007f\u0015{T*\u0004\u0002\u0002F)\u0019\u0011qI\u0017\u0002\u000fI,h\u000e^5nK&!\u00111JA#\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003\u001f\n)&\u0004\u0002\u0002R)\u0019\u00111\u000b<\u0002\u0005%|\u0017bA\u001e\u0002RQ\u0011\u0011QH\u0001\ti>\u001cFO]5oOR\t!/A\u0003baBd\u0017\u0010F\u0004N\u0003C\n\u0019'!\u001a\t\u000buJ\u0002\u0019A \t\u000b\rK\u0002\u0019A#\t\u000b%K\u0002\u0019A \u0002\u000fUt\u0017\r\u001d9msR!\u00111NA<!\u0015a\u0013QNA9\u0013\r\ty'\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r1\n\u0019hP#@\u0013\r\t)(\f\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u0005e$$!AA\u00025\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\bE\u0002t\u0003\u0003K1!a!u\u0005\u0019y%M[3di\u0002"
)
public class ShuffleIndexBlockId extends BlockId implements Product, Serializable {
   private final int shuffleId;
   private final long mapId;
   private final int reduceId;

   public static Option unapply(final ShuffleIndexBlockId x$0) {
      return ShuffleIndexBlockId$.MODULE$.unapply(x$0);
   }

   public static ShuffleIndexBlockId apply(final int shuffleId, final long mapId, final int reduceId) {
      return ShuffleIndexBlockId$.MODULE$.apply(shuffleId, mapId, reduceId);
   }

   public static Function1 tupled() {
      return ShuffleIndexBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleIndexBlockId$.MODULE$.curried();
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
      return "shuffle_" + var10000 + "_" + this.mapId() + "_" + this.reduceId() + ".index";
   }

   public ShuffleIndexBlockId copy(final int shuffleId, final long mapId, final int reduceId) {
      return new ShuffleIndexBlockId(shuffleId, mapId, reduceId);
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
      return "ShuffleIndexBlockId";
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
      return x$1 instanceof ShuffleIndexBlockId;
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
            if (x$1 instanceof ShuffleIndexBlockId) {
               ShuffleIndexBlockId var4 = (ShuffleIndexBlockId)x$1;
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

   public ShuffleIndexBlockId(final int shuffleId, final long mapId, final int reduceId) {
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.reduceId = reduceId;
      Product.$init$(this);
   }
}
