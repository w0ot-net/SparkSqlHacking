package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.streaming.util.WriteAheadLogRecordHandle;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Me!\u0002\u000f\u001e\u0001~9\u0003\u0002\u0003\"\u0001\u0005+\u0007I\u0011A\"\t\u0011)\u0003!\u0011#Q\u0001\n\u0011C\u0001b\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t'\u0002\u0011\t\u0012)A\u0005\u001b\"AA\u000b\u0001BK\u0002\u0013\u0005Q\u000b\u0003\u0005]\u0001\tE\t\u0015!\u0003W\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u001d\u0019\u0007!!A\u0005\u0002\u0011Dq\u0001\u001b\u0001\u0012\u0002\u0013\u0005\u0011\u000eC\u0004u\u0001E\u0005I\u0011A;\t\u000f]\u0004\u0011\u0013!C\u0001q\"9!\u0010AA\u0001\n\u0003Z\b\"CA\u0005\u0001\u0005\u0005I\u0011AA\u0006\u0011%\t\u0019\u0002AA\u0001\n\u0003\t)\u0002C\u0005\u0002\"\u0001\t\t\u0011\"\u0011\u0002$!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003{\u0001\u0011\u0011!C!\u0003\u007fA\u0011\"a\u0011\u0001\u0003\u0003%\t%!\u0012\t\u0013\u0005\u001d\u0003!!A\u0005B\u0005%\u0003\"CA&\u0001\u0005\u0005I\u0011IA'\u000f)\t\t&HA\u0001\u0012\u0003y\u00121\u000b\u0004\n9u\t\t\u0011#\u0001 \u0003+Ba!\u0018\f\u0005\u0002\u00055\u0004\"CA$-\u0005\u0005IQIA%\u0011%\tyGFA\u0001\n\u0003\u000b\t\bC\u0005\u0002zY\t\t\u0011\"!\u0002|!I\u0011\u0011\u0012\f\u0002\u0002\u0013%\u00111\u0012\u0002\u001e/JLG/Z!iK\u0006$Gj\\4CCN,Gm\u0015;pe\u0016\u0014Vm];mi*\u0011adH\u0001\te\u0016\u001cW-\u001b<fe*\u0011\u0001%I\u0001\ngR\u0014X-Y7j]\u001eT!AI\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011*\u0013AB1qC\u000eDWMC\u0001'\u0003\ry'oZ\n\u0006\u0001!r#'\u000e\t\u0003S1j\u0011A\u000b\u0006\u0002W\u0005)1oY1mC&\u0011QF\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005=\u0002T\"A\u000f\n\u0005Ej\"\u0001\u0007*fG\u0016Lg/\u001a3CY>\u001c7n\u0015;pe\u0016\u0014Vm];miB\u0011\u0011fM\u0005\u0003i)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00027\u007f9\u0011q'\u0010\b\u0003qqj\u0011!\u000f\u0006\u0003um\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002W%\u0011aHK\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0015I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002?U\u00059!\r\\8dW&#W#\u0001#\u0011\u0005\u0015CU\"\u0001$\u000b\u0005\u001d\u000b\u0013aB:u_J\fw-Z\u0005\u0003\u0013\u001a\u0013Qb\u0015;sK\u0006l'\t\\8dW&#\u0017\u0001\u00032m_\u000e\\\u0017\n\u001a\u0011\u0002\u00159,XNU3d_J$7/F\u0001N!\rIc\nU\u0005\u0003\u001f*\u0012aa\u00149uS>t\u0007CA\u0015R\u0013\t\u0011&F\u0001\u0003M_:<\u0017a\u00038v[J+7m\u001c:eg\u0002\nqb^1m%\u0016\u001cwN\u001d3IC:$G.Z\u000b\u0002-B\u0011qKW\u0007\u00021*\u0011\u0011lH\u0001\u0005kRLG.\u0003\u0002\\1\nIrK]5uK\u0006CW-\u00193M_\u001e\u0014VmY8sI\"\u000bg\u000e\u001a7f\u0003A9\u0018\r\u001c*fG>\u0014H\rS1oI2,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005?\u0002\f'\r\u0005\u00020\u0001!)!i\u0002a\u0001\t\")1j\u0002a\u0001\u001b\")Ak\u0002a\u0001-\u0006!1m\u001c9z)\u0011yVMZ4\t\u000f\tC\u0001\u0013!a\u0001\t\"91\n\u0003I\u0001\u0002\u0004i\u0005b\u0002+\t!\u0003\u0005\rAV\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005Q'F\u0001#lW\u0005a\u0007CA7s\u001b\u0005q'BA8q\u0003%)hn\u00195fG.,GM\u0003\u0002rU\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Mt'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u0001<+\u00055[\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0002s*\u0012ak[\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003q\u00042!`A\u0003\u001b\u0005q(bA@\u0002\u0002\u0005!A.\u00198h\u0015\t\t\u0019!\u0001\u0003kCZ\f\u0017bAA\u0004}\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u0004\u0011\u0007%\ny!C\u0002\u0002\u0012)\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0006\u0002\u001eA\u0019\u0011&!\u0007\n\u0007\u0005m!FA\u0002B]fD\u0011\"a\b\u000f\u0003\u0003\u0005\r!!\u0004\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t)\u0003\u0005\u0004\u0002(\u00055\u0012qC\u0007\u0003\u0003SQ1!a\u000b+\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003_\tIC\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u001b\u0003w\u00012!KA\u001c\u0013\r\tID\u000b\u0002\b\u0005>|G.Z1o\u0011%\ty\u0002EA\u0001\u0002\u0004\t9\"\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001?\u0002B!I\u0011qD\t\u0002\u0002\u0003\u0007\u0011QB\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QB\u0001\ti>\u001cFO]5oOR\tA0\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003k\ty\u0005C\u0005\u0002 Q\t\t\u00111\u0001\u0002\u0018\u0005irK]5uK\u0006CW-\u00193M_\u001e\u0014\u0015m]3e'R|'/\u001a*fgVdG\u000f\u0005\u00020-M)a#a\u0016\u0002dAA\u0011\u0011LA0\t63v,\u0004\u0002\u0002\\)\u0019\u0011Q\f\u0016\u0002\u000fI,h\u000e^5nK&!\u0011\u0011MA.\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003K\nY'\u0004\u0002\u0002h)!\u0011\u0011NA\u0001\u0003\tIw.C\u0002A\u0003O\"\"!a\u0015\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000f}\u000b\u0019(!\u001e\u0002x!)!)\u0007a\u0001\t\")1*\u0007a\u0001\u001b\")A+\u0007a\u0001-\u00069QO\\1qa2LH\u0003BA?\u0003\u000b\u0003B!\u000b(\u0002\u0000A1\u0011&!!E\u001bZK1!a!+\u0005\u0019!V\u000f\u001d7fg!A\u0011q\u0011\u000e\u0002\u0002\u0003\u0007q,A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!$\u0011\u0007u\fy)C\u0002\u0002\u0012z\u0014aa\u00142kK\u000e$\b"
)
public class WriteAheadLogBasedStoreResult implements ReceivedBlockStoreResult, Product, Serializable {
   private final StreamBlockId blockId;
   private final Option numRecords;
   private final WriteAheadLogRecordHandle walRecordHandle;

   public static Option unapply(final WriteAheadLogBasedStoreResult x$0) {
      return WriteAheadLogBasedStoreResult$.MODULE$.unapply(x$0);
   }

   public static WriteAheadLogBasedStoreResult apply(final StreamBlockId blockId, final Option numRecords, final WriteAheadLogRecordHandle walRecordHandle) {
      return WriteAheadLogBasedStoreResult$.MODULE$.apply(blockId, numRecords, walRecordHandle);
   }

   public static Function1 tupled() {
      return WriteAheadLogBasedStoreResult$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return WriteAheadLogBasedStoreResult$.MODULE$.curried();
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

   public WriteAheadLogRecordHandle walRecordHandle() {
      return this.walRecordHandle;
   }

   public WriteAheadLogBasedStoreResult copy(final StreamBlockId blockId, final Option numRecords, final WriteAheadLogRecordHandle walRecordHandle) {
      return new WriteAheadLogBasedStoreResult(blockId, numRecords, walRecordHandle);
   }

   public StreamBlockId copy$default$1() {
      return this.blockId();
   }

   public Option copy$default$2() {
      return this.numRecords();
   }

   public WriteAheadLogRecordHandle copy$default$3() {
      return this.walRecordHandle();
   }

   public String productPrefix() {
      return "WriteAheadLogBasedStoreResult";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.blockId();
         }
         case 1 -> {
            return this.numRecords();
         }
         case 2 -> {
            return this.walRecordHandle();
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
      return x$1 instanceof WriteAheadLogBasedStoreResult;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "blockId";
         }
         case 1 -> {
            return "numRecords";
         }
         case 2 -> {
            return "walRecordHandle";
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
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof WriteAheadLogBasedStoreResult) {
               label56: {
                  WriteAheadLogBasedStoreResult var4 = (WriteAheadLogBasedStoreResult)x$1;
                  StreamBlockId var10000 = this.blockId();
                  StreamBlockId var5 = var4.blockId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Option var8 = this.numRecords();
                  Option var6 = var4.numRecords();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  WriteAheadLogRecordHandle var9 = this.walRecordHandle();
                  WriteAheadLogRecordHandle var7 = var4.walRecordHandle();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public WriteAheadLogBasedStoreResult(final StreamBlockId blockId, final Option numRecords, final WriteAheadLogRecordHandle walRecordHandle) {
      this.blockId = blockId;
      this.numRecords = numRecords;
      this.walRecordHandle = walRecordHandle;
      Product.$init$(this);
   }
}
