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
   bytes = "\u0006\u0005\u0005\rc!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0005\u0003!\u0011#Q\u0001\nyBQA\u0011\u0001\u0005\u0002\rCqA\u0012\u0001\u0002\u0002\u0013\u0005q\tC\u0004J\u0001E\u0005I\u0011\u0001&\t\u000fU\u0003\u0011\u0011!C!-\"9q\fAA\u0001\n\u0003\u0001\u0007b\u00023\u0001\u0003\u0003%\t!\u001a\u0005\bW\u0002\t\t\u0011\"\u0011m\u0011\u001d\u0019\b!!A\u0005\u0002QDq!\u001f\u0001\u0002\u0002\u0013\u0005#\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\"I\u0011\u0011\u0001\u0001\u0002\u0002\u0013\u0005\u00131A\u0004\u000b\u0003\u000f9\u0012\u0011!E\u00013\u0005%a!\u0003\f\u0018\u0003\u0003E\t!GA\u0006\u0011\u0019\u0011\u0005\u0003\"\u0001\u0002$!9a\u0010EA\u0001\n\u000bz\b\"CA\u0013!\u0005\u0005I\u0011QA\u0014\u0011%\tY\u0003EA\u0001\n\u0003\u000bi\u0003C\u0005\u0002:A\t\t\u0011\"\u0003\u0002<\tA\u0011\t\u001a3CY>\u001c7N\u0003\u0002\u00193\u0005I1o\u00195fIVdWM\u001d\u0006\u00035m\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005qi\u0012!B:qCJ\\'B\u0001\u0010 \u0003\u0019\t\u0007/Y2iK*\t\u0001%A\u0002pe\u001e\u001cR\u0001\u0001\u0012)Y=\u0002\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u0012a!\u00118z%\u00164\u0007CA\u0015+\u001b\u00059\u0012BA\u0016\u0018\u0005Y\u0011VmY3jm\u0016\u0014HK]1dW\u0016\u0014X*Z:tC\u001e,\u0007CA\u0012.\u0013\tqCEA\u0004Qe>$Wo\u0019;\u0011\u0005AJdBA\u00198\u001d\t\u0011d'D\u00014\u0015\t!T'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005)\u0013B\u0001\u001d%\u0003\u001d\u0001\u0018mY6bO\u0016L!AO\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005a\"\u0013!\u0005:fG\u0016Lg/\u001a3CY>\u001c7.\u00138g_V\ta\b\u0005\u0002*\u007f%\u0011\u0001i\u0006\u0002\u0012%\u0016\u001cW-\u001b<fI\ncwnY6J]\u001a|\u0017A\u0005:fG\u0016Lg/\u001a3CY>\u001c7.\u00138g_\u0002\na\u0001P5oSRtDC\u0001#F!\tI\u0003\u0001C\u0003=\u0007\u0001\u0007a(\u0001\u0003d_BLHC\u0001#I\u0011\u001daD\u0001%AA\u0002y\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001LU\tqDjK\u0001N!\tq5+D\u0001P\u0015\t\u0001\u0016+A\u0005v]\u000eDWmY6fI*\u0011!\u000bJ\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001+P\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003]\u0003\"\u0001W/\u000e\u0003eS!AW.\u0002\t1\fgn\u001a\u0006\u00029\u0006!!.\u0019<b\u0013\tq\u0016L\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002CB\u00111EY\u0005\u0003G\u0012\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"AZ5\u0011\u0005\r:\u0017B\u00015%\u0005\r\te.\u001f\u0005\bU\"\t\t\u00111\u0001b\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tQ\u000eE\u0002oc\u001al\u0011a\u001c\u0006\u0003a\u0012\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0011xN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA;y!\t\u0019c/\u0003\u0002xI\t9!i\\8mK\u0006t\u0007b\u00026\u000b\u0003\u0003\u0005\rAZ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Xw\"9!nCA\u0001\u0002\u0004\t\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0005\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002/\u00061Q-];bYN$2!^A\u0003\u0011\u001dQg\"!AA\u0002\u0019\f\u0001\"\u00113e\u00052|7m\u001b\t\u0003SA\u0019R\u0001EA\u0007\u00033\u0001b!a\u0004\u0002\u0016y\"UBAA\t\u0015\r\t\u0019\u0002J\u0001\beVtG/[7f\u0013\u0011\t9\"!\u0005\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u001c\u0005\u0005RBAA\u000f\u0015\r\tybW\u0001\u0003S>L1AOA\u000f)\t\tI!A\u0003baBd\u0017\u0010F\u0002E\u0003SAQ\u0001P\nA\u0002y\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u00020\u0005U\u0002\u0003B\u0012\u00022yJ1!a\r%\u0005\u0019y\u0005\u000f^5p]\"A\u0011q\u0007\u000b\u0002\u0002\u0003\u0007A)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0010\u0011\u0007a\u000by$C\u0002\u0002Be\u0013aa\u00142kK\u000e$\b"
)
public class AddBlock implements ReceiverTrackerMessage, Product, Serializable {
   private final ReceivedBlockInfo receivedBlockInfo;

   public static Option unapply(final AddBlock x$0) {
      return AddBlock$.MODULE$.unapply(x$0);
   }

   public static AddBlock apply(final ReceivedBlockInfo receivedBlockInfo) {
      return AddBlock$.MODULE$.apply(receivedBlockInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return AddBlock$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return AddBlock$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ReceivedBlockInfo receivedBlockInfo() {
      return this.receivedBlockInfo;
   }

   public AddBlock copy(final ReceivedBlockInfo receivedBlockInfo) {
      return new AddBlock(receivedBlockInfo);
   }

   public ReceivedBlockInfo copy$default$1() {
      return this.receivedBlockInfo();
   }

   public String productPrefix() {
      return "AddBlock";
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
      return x$1 instanceof AddBlock;
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
            if (x$1 instanceof AddBlock) {
               label40: {
                  AddBlock var4 = (AddBlock)x$1;
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

   public AddBlock(final ReceivedBlockInfo receivedBlockInfo) {
      this.receivedBlockInfo = receivedBlockInfo;
      Product.$init$(this);
   }
}
