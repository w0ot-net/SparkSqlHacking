package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import org.apache.spark.storage.StreamBlockId;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=d!B\r\u001b\u0001r!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u001d\u0003!\u0011#Q\u0001\n\u0005C\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\t!\u0013\u0005\t!\u0002\u0011\t\u0012)A\u0005\u0015\")\u0011\u000b\u0001C\u0001%\"9a\u000bAA\u0001\n\u00039\u0006b\u0002.\u0001#\u0003%\ta\u0017\u0005\bM\u0002\t\n\u0011\"\u0001h\u0011\u001dI\u0007!!A\u0005B)Dqa\u001d\u0001\u0002\u0002\u0013\u0005A\u000fC\u0004y\u0001\u0005\u0005I\u0011A=\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0004\u0001\u0003\u0003%\t!!\u0005\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0011\u0001\u0005\u0005I\u0011IA\u0012\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002*\u0001\t\t\u0011\"\u0011\u0002,\u001dQ\u0011q\u0006\u000e\u0002\u0002#\u0005A$!\r\u0007\u0013eQ\u0012\u0011!E\u00019\u0005M\u0002BB)\u0014\t\u0003\tY\u0005C\u0005\u0002&M\t\t\u0011\"\u0012\u0002(!I\u0011QJ\n\u0002\u0002\u0013\u0005\u0015q\n\u0005\n\u0003+\u001a\u0012\u0011!CA\u0003/B\u0011\"!\u001a\u0014\u0003\u0003%I!a\u001a\u00039\tcwnY6NC:\fw-\u001a:CCN,Gm\u0015;pe\u0016\u0014Vm];mi*\u00111\u0004H\u0001\te\u0016\u001cW-\u001b<fe*\u0011QDH\u0001\ngR\u0014X-Y7j]\u001eT!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\n\u0006\u0001\u0015ZsF\r\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00051jS\"\u0001\u000e\n\u00059R\"\u0001\u0007*fG\u0016Lg/\u001a3CY>\u001c7n\u0015;pe\u0016\u0014Vm];miB\u0011a\u0005M\u0005\u0003c\u001d\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00024y9\u0011AG\u000f\b\u0003kej\u0011A\u000e\u0006\u0003oa\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002Q%\u00111hJ\u0001\ba\u0006\u001c7.Y4f\u0013\tidH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002<O\u00059!\r\\8dW&#W#A!\u0011\u0005\t+U\"A\"\u000b\u0005\u0011s\u0012aB:u_J\fw-Z\u0005\u0003\r\u000e\u0013Qb\u0015;sK\u0006l'\t\\8dW&#\u0017\u0001\u00032m_\u000e\\\u0017\n\u001a\u0011\u0002\u00159,XNU3d_J$7/F\u0001K!\r13*T\u0005\u0003\u0019\u001e\u0012aa\u00149uS>t\u0007C\u0001\u0014O\u0013\tyuE\u0001\u0003M_:<\u0017a\u00038v[J+7m\u001c:eg\u0002\na\u0001P5oSRtDcA*U+B\u0011A\u0006\u0001\u0005\u0006\u007f\u0015\u0001\r!\u0011\u0005\u0006\u0011\u0016\u0001\rAS\u0001\u0005G>\u0004\u0018\u0010F\u0002T1fCqa\u0010\u0004\u0011\u0002\u0003\u0007\u0011\tC\u0004I\rA\u0005\t\u0019\u0001&\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAL\u000b\u0002B;.\na\f\u0005\u0002`I6\t\u0001M\u0003\u0002bE\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003G\u001e\n!\"\u00198o_R\fG/[8o\u0013\t)\u0007MA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001iU\tQU,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002WB\u0011A.]\u0007\u0002[*\u0011an\\\u0001\u0005Y\u0006twMC\u0001q\u0003\u0011Q\u0017M^1\n\u0005Il'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001v!\t1c/\u0003\u0002xO\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!0 \t\u0003MmL!\u0001`\u0014\u0003\u0007\u0005s\u0017\u0010C\u0004\u007f\u0017\u0005\u0005\t\u0019A;\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\u0019\u0001E\u0003\u0002\u0006\u0005-!0\u0004\u0002\u0002\b)\u0019\u0011\u0011B\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u000e\u0005\u001d!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0005\u0002\u001aA\u0019a%!\u0006\n\u0007\u0005]qEA\u0004C_>dW-\u00198\t\u000fyl\u0011\u0011!a\u0001u\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rY\u0017q\u0004\u0005\b}:\t\t\u00111\u0001v\u0003!A\u0017m\u001d5D_\u0012,G#A;\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a[\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005M\u0011Q\u0006\u0005\b}F\t\t\u00111\u0001{\u0003q\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0005\u0006\u001cX\rZ*u_J,'+Z:vYR\u0004\"\u0001L\n\u0014\u000bM\t)$!\u0011\u0011\u000f\u0005]\u0012QH!K'6\u0011\u0011\u0011\b\u0006\u0004\u0003w9\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u007f\tIDA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\u0011\u0002J5\u0011\u0011Q\t\u0006\u0004\u0003\u000fz\u0017AA5p\u0013\ri\u0014Q\t\u000b\u0003\u0003c\tQ!\u00199qYf$RaUA)\u0003'BQa\u0010\fA\u0002\u0005CQ\u0001\u0013\fA\u0002)\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002Z\u0005\u0005\u0004\u0003\u0002\u0014L\u00037\u0002RAJA/\u0003*K1!a\u0018(\u0005\u0019!V\u000f\u001d7fe!A\u00111M\f\u0002\u0002\u0003\u00071+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001b\u0011\u00071\fY'C\u0002\u0002n5\u0014aa\u00142kK\u000e$\b"
)
public class BlockManagerBasedStoreResult implements ReceivedBlockStoreResult, Product, Serializable {
   private final StreamBlockId blockId;
   private final Option numRecords;

   public static Option unapply(final BlockManagerBasedStoreResult x$0) {
      return BlockManagerBasedStoreResult$.MODULE$.unapply(x$0);
   }

   public static BlockManagerBasedStoreResult apply(final StreamBlockId blockId, final Option numRecords) {
      return BlockManagerBasedStoreResult$.MODULE$.apply(blockId, numRecords);
   }

   public static Function1 tupled() {
      return BlockManagerBasedStoreResult$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BlockManagerBasedStoreResult$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public StreamBlockId blockId() {
      return this.blockId;
   }

   public Option numRecords() {
      return this.numRecords;
   }

   public BlockManagerBasedStoreResult copy(final StreamBlockId blockId, final Option numRecords) {
      return new BlockManagerBasedStoreResult(blockId, numRecords);
   }

   public StreamBlockId copy$default$1() {
      return this.blockId();
   }

   public Option copy$default$2() {
      return this.numRecords();
   }

   public String productPrefix() {
      return "BlockManagerBasedStoreResult";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.blockId();
         }
         case 1 -> {
            return this.numRecords();
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
      return x$1 instanceof BlockManagerBasedStoreResult;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "blockId";
         }
         case 1 -> {
            return "numRecords";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof BlockManagerBasedStoreResult) {
               label48: {
                  BlockManagerBasedStoreResult var4 = (BlockManagerBasedStoreResult)x$1;
                  StreamBlockId var10000 = this.blockId();
                  StreamBlockId var5 = var4.blockId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Option var7 = this.numRecords();
                  Option var6 = var4.numRecords();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public BlockManagerBasedStoreResult(final StreamBlockId blockId, final Option numRecords) {
      this.blockId = blockId;
      this.numRecords = numRecords;
      Product.$init$(this);
   }
}
