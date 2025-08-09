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
   bytes = "\u0006\u0005\u0005\u0015e\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005a\b\u0003\u0005E\u0001\tE\t\u0015!\u0003@\u0011!)\u0005A!f\u0001\n\u0003q\u0004\u0002\u0003$\u0001\u0005#\u0005\u000b\u0011B \t\u000b\u001d\u0003A\u0011\u0001%\t\u000b5\u0003A\u0011\t(\t\u000f]\u0003\u0011\u0011!C\u00011\"9A\fAI\u0001\n\u0003i\u0006b\u00025\u0001#\u0003%\t!\u0018\u0005\bS\u0002\t\n\u0011\"\u0001^\u0011\u001dQ\u0007!!A\u0005B-Dqa\u001d\u0001\u0002\u0002\u0013\u0005a\bC\u0004u\u0001\u0005\u0005I\u0011A;\t\u000fm\u0004\u0011\u0011!C!y\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0002\u0005\n\u0003'\u0001\u0011\u0011!C!\u0003+A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\t\u0013\u0005u\u0001!!A\u0005B\u0005}q!CA\u001e;\u0005\u0005\t\u0012AA\u001f\r!aR$!A\t\u0002\u0005}\u0002BB$\u0017\t\u0003\t9\u0006C\u0005\u0002ZY\t\t\u0011\"\u0012\u0002\\!I\u0011Q\f\f\u0002\u0002\u0013\u0005\u0015q\f\u0005\n\u0003O2\u0012\u0011!CA\u0003SB\u0011\"a\u001f\u0017\u0003\u0003%I!! \u0003)MCWO\u001a4mK6+'oZ3e\u00052|7m[%e\u0015\tqr$A\u0004ti>\u0014\u0018mZ3\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001O-\n\u0004C\u0001\u0015*\u001b\u0005i\u0012B\u0001\u0016\u001e\u0005\u001d\u0011En\\2l\u0013\u0012\u0004\"\u0001L\u0018\u000e\u00035R\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003a5\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00023u9\u00111\u0007\u000f\b\u0003i]j\u0011!\u000e\u0006\u0003m\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0018\n\u0005ej\u0013a\u00029bG.\fw-Z\u0005\u0003wq\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!O\u0017\u0002\u0013MDWO\u001a4mK&#W#A \u0011\u00051\u0002\u0015BA!.\u0005\rIe\u000e^\u0001\u000bg\",hM\u001a7f\u0013\u0012\u0004\u0013AD:ik\u001a4G.Z'fe\u001e,\u0017\nZ\u0001\u0010g\",hM\u001a7f\u001b\u0016\u0014x-Z%eA\u0005A!/\u001a3vG\u0016LE-A\u0005sK\u0012,8-Z%eA\u00051A(\u001b8jiz\"B!\u0013&L\u0019B\u0011\u0001\u0006\u0001\u0005\u0006{\u001d\u0001\ra\u0010\u0005\u0006\u0007\u001e\u0001\ra\u0010\u0005\u0006\u000b\u001e\u0001\raP\u0001\u0005]\u0006lW-F\u0001P!\t\u0001FK\u0004\u0002R%B\u0011A'L\u0005\u0003'6\na\u0001\u0015:fI\u00164\u0017BA+W\u0005\u0019\u0019FO]5oO*\u00111+L\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003J3j[\u0006bB\u001f\n!\u0003\u0005\ra\u0010\u0005\b\u0007&\u0001\n\u00111\u0001@\u0011\u001d)\u0015\u0002%AA\u0002}\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001_U\tytlK\u0001a!\t\tg-D\u0001c\u0015\t\u0019G-A\u0005v]\u000eDWmY6fI*\u0011Q-L\u0001\u000bC:tw\u000e^1uS>t\u0017BA4c\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00017\u0011\u00055\u0014X\"\u00018\u000b\u0005=\u0004\u0018\u0001\u00027b]\u001eT\u0011!]\u0001\u0005U\u00064\u0018-\u0003\u0002V]\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001<z!\tas/\u0003\u0002y[\t\u0019\u0011I\\=\t\u000fi|\u0011\u0011!a\u0001\u007f\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012! \t\u0005}\u0006\ra/D\u0001\u0000\u0015\r\t\t!L\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0003\u007f\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY!!\u0005\u0011\u00071\ni!C\u0002\u0002\u00105\u0012qAQ8pY\u0016\fg\u000eC\u0004{#\u0005\u0005\t\u0019\u0001<\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004Y\u0006]\u0001b\u0002>\u0013\u0003\u0003\u0005\raP\u0001\tQ\u0006\u001c\bnQ8eKR\tq(\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0017\t\t\u0003C\u0004{)\u0005\u0005\t\u0019\u0001<)\u000b\u0001\t)#a\f\u0011\t\u0005\u001d\u00121F\u0007\u0003\u0003SQ!!Z\u0010\n\t\u00055\u0012\u0011\u0006\u0002\u0006'&t7-Z\u0011\u0003\u0003c\tQa\r\u00183]AB3\u0001AA\u001b!\u0011\t9#a\u000e\n\t\u0005e\u0012\u0011\u0006\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001\u0015'\",hM\u001a7f\u001b\u0016\u0014x-\u001a3CY>\u001c7.\u00133\u0011\u0005!22#\u0002\f\u0002B\u00055\u0003\u0003CA\"\u0003\u0013zthP%\u000e\u0005\u0005\u0015#bAA$[\u00059!/\u001e8uS6,\u0017\u0002BA&\u0003\u000b\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\ty%!\u0016\u000e\u0005\u0005E#bAA*a\u0006\u0011\u0011n\\\u0005\u0004w\u0005ECCAA\u001f\u0003!!xn\u0015;sS:<G#\u00017\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000f%\u000b\t'a\u0019\u0002f!)Q(\u0007a\u0001\u007f!)1)\u0007a\u0001\u007f!)Q)\u0007a\u0001\u007f\u00059QO\\1qa2LH\u0003BA6\u0003o\u0002R\u0001LA7\u0003cJ1!a\u001c.\u0005\u0019y\u0005\u000f^5p]B1A&a\u001d@\u007f}J1!!\u001e.\u0005\u0019!V\u000f\u001d7fg!A\u0011\u0011\u0010\u000e\u0002\u0002\u0003\u0007\u0011*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a \u0011\u00075\f\t)C\u0002\u0002\u0004:\u0014aa\u00142kK\u000e$\b"
)
public class ShuffleMergedBlockId extends BlockId implements Product, Serializable {
   private final int shuffleId;
   private final int shuffleMergeId;
   private final int reduceId;

   public static Option unapply(final ShuffleMergedBlockId x$0) {
      return ShuffleMergedBlockId$.MODULE$.unapply(x$0);
   }

   public static ShuffleMergedBlockId apply(final int shuffleId, final int shuffleMergeId, final int reduceId) {
      return ShuffleMergedBlockId$.MODULE$.apply(shuffleId, shuffleMergeId, reduceId);
   }

   public static Function1 tupled() {
      return ShuffleMergedBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleMergedBlockId$.MODULE$.curried();
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

   public String name() {
      int var10000 = this.shuffleId();
      return "shuffleMerged_" + var10000 + "_" + this.shuffleMergeId() + "_" + this.reduceId();
   }

   public ShuffleMergedBlockId copy(final int shuffleId, final int shuffleMergeId, final int reduceId) {
      return new ShuffleMergedBlockId(shuffleId, shuffleMergeId, reduceId);
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

   public String productPrefix() {
      return "ShuffleMergedBlockId";
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
      return x$1 instanceof ShuffleMergedBlockId;
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
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof ShuffleMergedBlockId) {
               ShuffleMergedBlockId var4 = (ShuffleMergedBlockId)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.shuffleMergeId() == var4.shuffleMergeId() && this.reduceId() == var4.reduceId() && var4.canEqual(this)) {
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

   public ShuffleMergedBlockId(final int shuffleId, final int shuffleMergeId, final int reduceId) {
      this.shuffleId = shuffleId;
      this.shuffleMergeId = shuffleMergeId;
      this.reduceId = reduceId;
      Product.$init$(this);
   }
}
