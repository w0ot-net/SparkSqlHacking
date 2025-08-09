package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0005\u0003!\u0011#Q\u0001\nyBQA\u0011\u0001\u0005\u0002\rCqA\u0012\u0001\u0002\u0002\u0013\u0005q\tC\u0004J\u0001E\u0005I\u0011\u0001&\t\u000fU\u0003\u0011\u0011!C!-\"9q\fAA\u0001\n\u0003\u0001\u0007b\u00023\u0001\u0003\u0003%\t!\u001a\u0005\bW\u0002\t\t\u0011\"\u0011m\u0011\u001d\u0019\b!!A\u0005\u0002QDq!\u001f\u0001\u0002\u0002\u0013\u0005#\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\"I\u0011\u0011\u0001\u0001\u0002\u0002\u0013\u0005\u00131A\u0004\u000b\u0003\u000f9\u0012\u0011!E\u00013\u0005%a!\u0003\f\u0018\u0003\u0003E\t!GA\u0006\u0011\u0019\u0011\u0005\u0003\"\u0001\u0002$!9a\u0010EA\u0001\n\u000bz\b\"CA\u0013!\u0005\u0005I\u0011QA\u0014\u0011%\tY\u0003EA\u0001\n\u0003\u000bi\u0003C\u0005\u0002:A\t\t\u0011\"\u0003\u0002<\t\u0011\"\t\\8dW\u0006#G-\u001b;j_:,e/\u001a8u\u0015\tA\u0012$A\u0005tG\",G-\u001e7fe*\u0011!dG\u0001\ngR\u0014X-Y7j]\u001eT!\u0001H\u000f\u0002\u000bM\u0004\u0018M]6\u000b\u0005yy\u0012AB1qC\u000eDWMC\u0001!\u0003\ry'oZ\n\u0006\u0001\tBCf\f\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005%RS\"A\f\n\u0005-:\"\u0001\b*fG\u0016Lg/\u001a3CY>\u001c7\u000e\u0016:bG.,'\u000fT8h\u000bZ,g\u000e\u001e\t\u0003G5J!A\f\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001'\u000f\b\u0003c]r!A\r\u001c\u000e\u0003MR!\u0001N\u001b\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!J\u0005\u0003q\u0011\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001\bJ\u0001\u0012e\u0016\u001cW-\u001b<fI\ncwnY6J]\u001a|W#\u0001 \u0011\u0005%z\u0014B\u0001!\u0018\u0005E\u0011VmY3jm\u0016$'\t\\8dW&sgm\\\u0001\u0013e\u0016\u001cW-\u001b<fI\ncwnY6J]\u001a|\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\t\u0016\u0003\"!\u000b\u0001\t\u000bq\u001a\u0001\u0019\u0001 \u0002\t\r|\u0007/\u001f\u000b\u0003\t\"Cq\u0001\u0010\u0003\u0011\u0002\u0003\u0007a(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003-S#A\u0010',\u00035\u0003\"AT*\u000e\u0003=S!\u0001U)\u0002\u0013Ut7\r[3dW\u0016$'B\u0001*%\u0003)\tgN\\8uCRLwN\\\u0005\u0003)>\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\u000b\u0005\u0002Y;6\t\u0011L\u0003\u0002[7\u0006!A.\u00198h\u0015\u0005a\u0016\u0001\u00026bm\u0006L!AX-\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\t\u0007CA\u0012c\u0013\t\u0019GEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002gSB\u00111eZ\u0005\u0003Q\u0012\u00121!\u00118z\u0011\u001dQ\u0007\"!AA\u0002\u0005\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A7\u0011\u00079\fh-D\u0001p\u0015\t\u0001H%\u0001\u0006d_2dWm\u0019;j_:L!A]8\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003kb\u0004\"a\t<\n\u0005]$#a\u0002\"p_2,\u0017M\u001c\u0005\bU*\t\t\u00111\u0001g\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005][\bb\u00026\f\u0003\u0003\u0005\r!Y\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011-\u0001\u0005u_N#(/\u001b8h)\u00059\u0016AB3rk\u0006d7\u000fF\u0002v\u0003\u000bAqA\u001b\b\u0002\u0002\u0003\u0007a-\u0001\nCY>\u001c7.\u00113eSRLwN\\#wK:$\bCA\u0015\u0011'\u0015\u0001\u0012QBA\r!\u0019\ty!!\u0006?\t6\u0011\u0011\u0011\u0003\u0006\u0004\u0003'!\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003/\t\tBA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a\u0007\u0002\"5\u0011\u0011Q\u0004\u0006\u0004\u0003?Y\u0016AA5p\u0013\rQ\u0014Q\u0004\u000b\u0003\u0003\u0013\tQ!\u00199qYf$2\u0001RA\u0015\u0011\u0015a4\u00031\u0001?\u0003\u001d)h.\u00199qYf$B!a\f\u00026A!1%!\r?\u0013\r\t\u0019\u0004\n\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005]B#!AA\u0002\u0011\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0004E\u0002Y\u0003\u007fI1!!\u0011Z\u0005\u0019y%M[3di\u0002"
)
public class BlockAdditionEvent implements ReceivedBlockTrackerLogEvent, Product, Serializable {
   private final ReceivedBlockInfo receivedBlockInfo;

   public static Option unapply(final BlockAdditionEvent x$0) {
      return BlockAdditionEvent$.MODULE$.unapply(x$0);
   }

   public static BlockAdditionEvent apply(final ReceivedBlockInfo receivedBlockInfo) {
      return BlockAdditionEvent$.MODULE$.apply(receivedBlockInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return BlockAdditionEvent$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return BlockAdditionEvent$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ReceivedBlockInfo receivedBlockInfo() {
      return this.receivedBlockInfo;
   }

   public BlockAdditionEvent copy(final ReceivedBlockInfo receivedBlockInfo) {
      return new BlockAdditionEvent(receivedBlockInfo);
   }

   public ReceivedBlockInfo copy$default$1() {
      return this.receivedBlockInfo();
   }

   public String productPrefix() {
      return "BlockAdditionEvent";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.receivedBlockInfo();
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
      return x$1 instanceof BlockAdditionEvent;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "receivedBlockInfo";
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
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof BlockAdditionEvent) {
               label40: {
                  BlockAdditionEvent var4 = (BlockAdditionEvent)x$1;
                  ReceivedBlockInfo var10000 = this.receivedBlockInfo();
                  ReceivedBlockInfo var5 = var4.receivedBlockInfo();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public BlockAdditionEvent(final ReceivedBlockInfo receivedBlockInfo) {
      this.receivedBlockInfo = receivedBlockInfo;
      Product.$init$(this);
   }
}
