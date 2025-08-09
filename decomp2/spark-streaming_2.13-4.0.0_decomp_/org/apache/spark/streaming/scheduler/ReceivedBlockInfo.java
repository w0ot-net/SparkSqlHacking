package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.streaming.receiver.ReceivedBlockStoreResult;
import org.apache.spark.streaming.receiver.WriteAheadLogBasedStoreResult;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-h!\u0002\u0014(\u0001&\n\u0004\u0002\u0003%\u0001\u0005+\u0007I\u0011A%\t\u00115\u0003!\u0011#Q\u0001\n)C\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t-\u0002\u0011\t\u0012)A\u0005!\"Aq\u000b\u0001BK\u0002\u0013\u0005\u0001\f\u0003\u0005^\u0001\tE\t\u0015!\u0003Z\u0011!q\u0006A!f\u0001\n\u0003y\u0006\u0002\u00034\u0001\u0005#\u0005\u000b\u0011\u00021\t\u000b\u001d\u0004A\u0011\u00015\t\u000f=\u0004\u0001\u0019!C\u0005a\"9A\u000f\u0001a\u0001\n\u0013)\bBB>\u0001A\u0003&\u0011\u000fC\u0004\u0002\u0002\u0001!\t!a\u0001\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u00111\u0005\u0001\u0005\u0002\u0005\u0015\u0002bBA\u0014\u0001\u0011\u0005\u0011\u0011\u0006\u0005\n\u0003W\u0001\u0011\u0011!C\u0001\u0003[A\u0011\"a\u000e\u0001#\u0003%\t!!\u000f\t\u0013\u0005=\u0003!%A\u0005\u0002\u0005E\u0003\"CA+\u0001E\u0005I\u0011AA,\u0011%\tY\u0006AI\u0001\n\u0003\ti\u0006C\u0005\u0002b\u0001\t\t\u0011\"\u0011\u0002d!A\u0011Q\u000f\u0001\u0002\u0002\u0013\u0005\u0011\nC\u0005\u0002x\u0001\t\t\u0011\"\u0001\u0002z!I\u0011Q\u0010\u0001\u0002\u0002\u0013\u0005\u0013q\u0010\u0005\n\u0003\u001b\u0003\u0011\u0011!C\u0001\u0003\u001fC\u0011\"a%\u0001\u0003\u0003%\t%!&\t\u0013\u0005e\u0005!!A\u0005B\u0005m\u0005\"CAO\u0001\u0005\u0005I\u0011IAP\u0011%\t\t\u000bAA\u0001\n\u0003\n\u0019k\u0002\u0006\u0002(\u001e\n\t\u0011#\u0001*\u0003S3\u0011BJ\u0014\u0002\u0002#\u0005\u0011&a+\t\r\u001d\u0004C\u0011AAb\u0011%\ti\nIA\u0001\n\u000b\ny\nC\u0005\u0002F\u0002\n\t\u0011\"!\u0002H\"I\u0011\u0011\u001b\u0011\u0002\u0002\u0013\u0005\u00151\u001b\u0005\n\u0003C\u0004\u0013\u0011!C\u0005\u0003G\u0014\u0011CU3dK&4X\r\u001a\"m_\u000e\\\u0017J\u001c4p\u0015\tA\u0013&A\u0005tG\",G-\u001e7fe*\u0011!fK\u0001\ngR\u0014X-Y7j]\u001eT!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\n\u0005\u0001IB4\b\u0005\u00024m5\tAGC\u00016\u0003\u0015\u00198-\u00197b\u0013\t9DG\u0001\u0004B]f\u0014VM\u001a\t\u0003geJ!A\u000f\u001b\u0003\u000fA\u0013x\u000eZ;diB\u0011A(\u0012\b\u0003{\rs!A\u0010\"\u000e\u0003}R!\u0001Q!\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!N\u0005\u0003\tR\nq\u0001]1dW\u0006<W-\u0003\u0002G\u000f\na1+\u001a:jC2L'0\u00192mK*\u0011A\tN\u0001\tgR\u0014X-Y7JIV\t!\n\u0005\u00024\u0017&\u0011A\n\u000e\u0002\u0004\u0013:$\u0018!C:ue\u0016\fW.\u00133!\u0003)qW/\u001c*fG>\u0014Hm]\u000b\u0002!B\u00191'U*\n\u0005I#$AB(qi&|g\u000e\u0005\u00024)&\u0011Q\u000b\u000e\u0002\u0005\u0019>tw-A\u0006ok6\u0014VmY8sIN\u0004\u0013AD7fi\u0006$\u0017\r^1PaRLwN\\\u000b\u00023B\u00191'\u0015.\u0011\u0005MZ\u0016B\u0001/5\u0005\r\te._\u0001\u0010[\u0016$\u0018\rZ1uC>\u0003H/[8oA\u0005\u0001\"\r\\8dWN#xN]3SKN,H\u000e^\u000b\u0002AB\u0011\u0011\rZ\u0007\u0002E*\u00111-K\u0001\te\u0016\u001cW-\u001b<fe&\u0011QM\u0019\u0002\u0019%\u0016\u001cW-\u001b<fI\ncwnY6Ti>\u0014XMU3tk2$\u0018!\u00052m_\u000e\\7\u000b^8sKJ+7/\u001e7uA\u00051A(\u001b8jiz\"R![6m[:\u0004\"A\u001b\u0001\u000e\u0003\u001dBQ\u0001S\u0005A\u0002)CQAT\u0005A\u0002ACQaV\u0005A\u0002eCQAX\u0005A\u0002\u0001\fqbX5t\u00052|7m[%e-\u0006d\u0017\u000eZ\u000b\u0002cB\u00111G]\u0005\u0003gR\u0012qAQ8pY\u0016\fg.A\n`SN\u0014En\\2l\u0013\u00124\u0016\r\\5e?\u0012*\u0017\u000f\u0006\u0002wsB\u00111g^\u0005\u0003qR\u0012A!\u00168ji\"9!pCA\u0001\u0002\u0004\t\u0018a\u0001=%c\u0005\u0001r,[:CY>\u001c7.\u00133WC2LG\r\t\u0015\u0003\u0019u\u0004\"a\r@\n\u0005}$$\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u000f\tdwnY6JIV\u0011\u0011Q\u0001\t\u0005\u0003\u000f\ti!\u0004\u0002\u0002\n)\u0019\u00111B\u0016\u0002\u000fM$xN]1hK&!\u0011qBA\u0005\u00055\u0019FO]3b[\ncwnY6JI\u0006)r/\u00197SK\u000e|'\u000f\u001a%b]\u0012dWm\u00149uS>tWCAA\u000b!\u0011\u0019\u0014+a\u0006\u0011\t\u0005e\u0011qD\u0007\u0003\u00037Q1!!\b*\u0003\u0011)H/\u001b7\n\t\u0005\u0005\u00121\u0004\u0002\u001a/JLG/Z!iK\u0006$Gj\\4SK\u000e|'\u000f\u001a%b]\u0012dW-\u0001\bjg\ncwnY6JIZ\u000bG.\u001b3\u0015\u0003E\f\u0011c]3u\u00052|7m[%e\u0013:4\u0018\r\\5e)\u00051\u0018\u0001B2paf$\u0012\"[A\u0018\u0003c\t\u0019$!\u000e\t\u000f!\u000b\u0002\u0013!a\u0001\u0015\"9a*\u0005I\u0001\u0002\u0004\u0001\u0006bB,\u0012!\u0003\u0005\r!\u0017\u0005\b=F\u0001\n\u00111\u0001a\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a\u000f+\u0007)\u000bid\u000b\u0002\u0002@A!\u0011\u0011IA&\u001b\t\t\u0019E\u0003\u0003\u0002F\u0005\u001d\u0013!C;oG\",7m[3e\u0015\r\tI\u0005N\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA'\u0003\u0007\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!a\u0015+\u0007A\u000bi$\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005e#fA-\u0002>\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAA0U\r\u0001\u0017QH\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\u0015\u0004\u0003BA4\u0003cj!!!\u001b\u000b\t\u0005-\u0014QN\u0001\u0005Y\u0006twM\u0003\u0002\u0002p\u0005!!.\u0019<b\u0013\u0011\t\u0019(!\u001b\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$2AWA>\u0011\u001dQ\b$!AA\u0002)\u000bq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0003\u0003R!a!\u0002\njk!!!\"\u000b\u0007\u0005\u001dE'\u0001\u0006d_2dWm\u0019;j_:LA!a#\u0002\u0006\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r\t\u0018\u0011\u0013\u0005\buj\t\t\u00111\u0001[\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005\u0015\u0014q\u0013\u0005\bun\t\t\u00111\u0001K\u0003!A\u0017m\u001d5D_\u0012,G#\u0001&\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u001a\u0002\r\u0015\fX/\u00197t)\r\t\u0018Q\u0015\u0005\buz\t\t\u00111\u0001[\u0003E\u0011VmY3jm\u0016$'\t\\8dW&sgm\u001c\t\u0003U\u0002\u001aR\u0001IAW\u0003s\u0003\u0012\"a,\u00026*\u0003\u0016\fY5\u000e\u0005\u0005E&bAAZi\u00059!/\u001e8uS6,\u0017\u0002BA\\\u0003c\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c85!\u0011\tY,!1\u000e\u0005\u0005u&\u0002BA`\u0003[\n!![8\n\u0007\u0019\u000bi\f\u0006\u0002\u0002*\u0006)\u0011\r\u001d9msRI\u0011.!3\u0002L\u00065\u0017q\u001a\u0005\u0006\u0011\u000e\u0002\rA\u0013\u0005\u0006\u001d\u000e\u0002\r\u0001\u0015\u0005\u0006/\u000e\u0002\r!\u0017\u0005\u0006=\u000e\u0002\r\u0001Y\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t).!8\u0011\tM\n\u0016q\u001b\t\bg\u0005e'\nU-a\u0013\r\tY\u000e\u000e\u0002\u0007)V\u0004H.\u001a\u001b\t\u0011\u0005}G%!AA\u0002%\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u000f\u0005\u0003\u0002h\u0005\u001d\u0018\u0002BAu\u0003S\u0012aa\u00142kK\u000e$\b"
)
public class ReceivedBlockInfo implements Product, Serializable {
   private final int streamId;
   private final Option numRecords;
   private final Option metadataOption;
   private final ReceivedBlockStoreResult blockStoreResult;
   private volatile boolean _isBlockIdValid;

   public static Option unapply(final ReceivedBlockInfo x$0) {
      return ReceivedBlockInfo$.MODULE$.unapply(x$0);
   }

   public static ReceivedBlockInfo apply(final int streamId, final Option numRecords, final Option metadataOption, final ReceivedBlockStoreResult blockStoreResult) {
      return ReceivedBlockInfo$.MODULE$.apply(streamId, numRecords, metadataOption, blockStoreResult);
   }

   public static Function1 tupled() {
      return ReceivedBlockInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ReceivedBlockInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamId() {
      return this.streamId;
   }

   public Option numRecords() {
      return this.numRecords;
   }

   public Option metadataOption() {
      return this.metadataOption;
   }

   public ReceivedBlockStoreResult blockStoreResult() {
      return this.blockStoreResult;
   }

   private boolean _isBlockIdValid() {
      return this._isBlockIdValid;
   }

   private void _isBlockIdValid_$eq(final boolean x$1) {
      this._isBlockIdValid = x$1;
   }

   public StreamBlockId blockId() {
      return this.blockStoreResult().blockId();
   }

   public Option walRecordHandleOption() {
      ReceivedBlockStoreResult var2 = this.blockStoreResult();
      if (var2 instanceof WriteAheadLogBasedStoreResult var3) {
         return new Some(var3.walRecordHandle());
      } else {
         return .MODULE$;
      }
   }

   public boolean isBlockIdValid() {
      return this._isBlockIdValid();
   }

   public void setBlockIdInvalid() {
      this._isBlockIdValid_$eq(false);
   }

   public ReceivedBlockInfo copy(final int streamId, final Option numRecords, final Option metadataOption, final ReceivedBlockStoreResult blockStoreResult) {
      return new ReceivedBlockInfo(streamId, numRecords, metadataOption, blockStoreResult);
   }

   public int copy$default$1() {
      return this.streamId();
   }

   public Option copy$default$2() {
      return this.numRecords();
   }

   public Option copy$default$3() {
      return this.metadataOption();
   }

   public ReceivedBlockStoreResult copy$default$4() {
      return this.blockStoreResult();
   }

   public String productPrefix() {
      return "ReceivedBlockInfo";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamId());
         }
         case 1 -> {
            return this.numRecords();
         }
         case 2 -> {
            return this.metadataOption();
         }
         case 3 -> {
            return this.blockStoreResult();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ReceivedBlockInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamId";
         }
         case 1 -> {
            return "numRecords";
         }
         case 2 -> {
            return "metadataOption";
         }
         case 3 -> {
            return "blockStoreResult";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.streamId());
      var1 = Statics.mix(var1, Statics.anyHash(this.numRecords()));
      var1 = Statics.mix(var1, Statics.anyHash(this.metadataOption()));
      var1 = Statics.mix(var1, Statics.anyHash(this.blockStoreResult()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof ReceivedBlockInfo) {
               ReceivedBlockInfo var4 = (ReceivedBlockInfo)x$1;
               if (this.streamId() == var4.streamId()) {
                  label60: {
                     Option var10000 = this.numRecords();
                     Option var5 = var4.numRecords();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label60;
                     }

                     var10000 = this.metadataOption();
                     Option var6 = var4.metadataOption();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label60;
                     }

                     ReceivedBlockStoreResult var9 = this.blockStoreResult();
                     ReceivedBlockStoreResult var7 = var4.blockStoreResult();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label60;
                        }
                     } else if (!var9.equals(var7)) {
                        break label60;
                     }

                     if (var4.canEqual(this)) {
                        break label67;
                     }
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

   public ReceivedBlockInfo(final int streamId, final Option numRecords, final Option metadataOption, final ReceivedBlockStoreResult blockStoreResult) {
      this.streamId = streamId;
      this.numRecords = numRecords;
      this.metadataOption = metadataOption;
      this.blockStoreResult = blockStoreResult;
      Product.$init$(this);
      scala.Predef..MODULE$.require(numRecords.isEmpty() || BoxesRunTime.unboxToLong(numRecords.get()) >= 0L, () -> "numRecords must not be negative");
      this._isBlockIdValid = true;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
