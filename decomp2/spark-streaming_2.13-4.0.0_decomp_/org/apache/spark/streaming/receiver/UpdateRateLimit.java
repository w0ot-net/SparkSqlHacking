package org.apache.spark.streaming.receiver;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0005\u0003!\u0011#Q\u0001\nyBQA\u0011\u0001\u0005\u0002\rCqA\u0012\u0001\u0002\u0002\u0013\u0005q\tC\u0004J\u0001E\u0005I\u0011\u0001&\t\u000fU\u0003\u0011\u0011!C!-\"9q\fAA\u0001\n\u0003\u0001\u0007b\u00023\u0001\u0003\u0003%\t!\u001a\u0005\bW\u0002\t\t\u0011\"\u0011m\u0011\u001d\u0019\b!!A\u0005\u0002QDq!\u001f\u0001\u0002\u0002\u0013\u0005#\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\"I\u0011\u0011\u0001\u0001\u0002\u0002\u0013\u0005\u00131A\u0004\u000b\u0003\u000f9\u0012\u0011!E\u00013\u0005%a!\u0003\f\u0018\u0003\u0003E\t!GA\u0006\u0011\u0019\u0011\u0005\u0003\"\u0001\u0002$!9a\u0010EA\u0001\n\u000bz\b\"CA\u0013!\u0005\u0005I\u0011QA\u0014\u0011%\tY\u0003EA\u0001\n\u0003\u000bi\u0003C\u0005\u0002:A\t\t\u0011\"\u0003\u0002<\tyQ\u000b\u001d3bi\u0016\u0014\u0016\r^3MS6LGO\u0003\u0002\u00193\u0005A!/Z2fSZ,'O\u0003\u0002\u001b7\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sON)\u0001A\t\u0015-_A\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u0004\"!\u000b\u0016\u000e\u0003]I!aK\f\u0003\u001fI+7-Z5wKJlUm]:bO\u0016\u0004\"aI\u0017\n\u00059\"#a\u0002)s_\u0012,8\r\u001e\t\u0003aer!!M\u001c\u000f\u0005I2T\"A\u001a\u000b\u0005Q*\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0015J!\u0001\u000f\u0013\u0002\u000fA\f7m[1hK&\u0011!h\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003q\u0011\n\u0011#\u001a7f[\u0016tGo\u001d)feN+7m\u001c8e+\u0005q\u0004CA\u0012@\u0013\t\u0001EE\u0001\u0003M_:<\u0017AE3mK6,g\u000e^:QKJ\u001cVmY8oI\u0002\na\u0001P5oSRtDC\u0001#F!\tI\u0003\u0001C\u0003=\u0007\u0001\u0007a(\u0001\u0003d_BLHC\u0001#I\u0011\u001daD\u0001%AA\u0002y\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001LU\tqDjK\u0001N!\tq5+D\u0001P\u0015\t\u0001\u0016+A\u0005v]\u000eDWmY6fI*\u0011!\u000bJ\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001+P\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003]\u0003\"\u0001W/\u000e\u0003eS!AW.\u0002\t1\fgn\u001a\u0006\u00029\u0006!!.\u0019<b\u0013\tq\u0016L\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002CB\u00111EY\u0005\u0003G\u0012\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"AZ5\u0011\u0005\r:\u0017B\u00015%\u0005\r\te.\u001f\u0005\bU\"\t\t\u00111\u0001b\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tQ\u000eE\u0002oc\u001al\u0011a\u001c\u0006\u0003a\u0012\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0011xN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA;y!\t\u0019c/\u0003\u0002xI\t9!i\\8mK\u0006t\u0007b\u00026\u000b\u0003\u0003\u0005\rAZ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Xw\"9!nCA\u0001\u0002\u0004\t\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0005\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002/\u00061Q-];bYN$2!^A\u0003\u0011\u001dQg\"!AA\u0002\u0019\fq\"\u00169eCR,'+\u0019;f\u0019&l\u0017\u000e\u001e\t\u0003SA\u0019R\u0001EA\u0007\u00033\u0001b!a\u0004\u0002\u0016y\"UBAA\t\u0015\r\t\u0019\u0002J\u0001\beVtG/[7f\u0013\u0011\t9\"!\u0005\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u001c\u0005\u0005RBAA\u000f\u0015\r\tybW\u0001\u0003S>L1AOA\u000f)\t\tI!A\u0003baBd\u0017\u0010F\u0002E\u0003SAQ\u0001P\nA\u0002y\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u00020\u0005U\u0002\u0003B\u0012\u00022yJ1!a\r%\u0005\u0019y\u0005\u000f^5p]\"A\u0011q\u0007\u000b\u0002\u0002\u0003\u0007A)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0010\u0011\u0007a\u000by$C\u0002\u0002Be\u0013aa\u00142kK\u000e$\b"
)
public class UpdateRateLimit implements ReceiverMessage, Product {
   private final long elementsPerSecond;

   public static Option unapply(final UpdateRateLimit x$0) {
      return UpdateRateLimit$.MODULE$.unapply(x$0);
   }

   public static UpdateRateLimit apply(final long elementsPerSecond) {
      return UpdateRateLimit$.MODULE$.apply(elementsPerSecond);
   }

   public static Function1 andThen(final Function1 g) {
      return UpdateRateLimit$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return UpdateRateLimit$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long elementsPerSecond() {
      return this.elementsPerSecond;
   }

   public UpdateRateLimit copy(final long elementsPerSecond) {
      return new UpdateRateLimit(elementsPerSecond);
   }

   public long copy$default$1() {
      return this.elementsPerSecond();
   }

   public String productPrefix() {
      return "UpdateRateLimit";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.elementsPerSecond());
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
      return x$1 instanceof UpdateRateLimit;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "elementsPerSecond";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.elementsPerSecond()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof UpdateRateLimit) {
               UpdateRateLimit var4 = (UpdateRateLimit)x$1;
               if (this.elementsPerSecond() == var4.elementsPerSecond() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public UpdateRateLimit(final long elementsPerSecond) {
      this.elementsPerSecond = elementsPerSecond;
      Product.$init$(this);
   }
}
