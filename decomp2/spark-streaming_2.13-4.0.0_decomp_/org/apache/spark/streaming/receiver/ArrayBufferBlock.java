package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011-\u0003!\u0011#Q\u0001\nyBQa\u0015\u0001\u0005\u0002QCqa\u0017\u0001\u0002\u0002\u0013\u0005A\fC\u0004_\u0001E\u0005I\u0011A0\t\u000f9\u0004\u0011\u0011!C!_\"9\u0001\u0010AA\u0001\n\u0003I\bbB?\u0001\u0003\u0003%\tA \u0005\n\u0003\u0007\u0001\u0011\u0011!C!\u0003\u000bA\u0011\"a\u0004\u0001\u0003\u0003%\t!!\u0005\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0011\u0001\u0005\u0005I\u0011IA\u0012\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002*\u0001\t\t\u0011\"\u0011\u0002,\u001dQ\u0011qF\f\u0002\u0002#\u0005\u0011$!\r\u0007\u0013Y9\u0012\u0011!E\u00013\u0005M\u0002BB*\u0011\t\u0003\t\u0019\u0006C\u0005\u0002&A\t\t\u0011\"\u0012\u0002(!I\u0011Q\u000b\t\u0002\u0002\u0013\u0005\u0015q\u000b\u0005\n\u0003G\u0002\u0012\u0011!CA\u0003KB\u0011\"!\u001f\u0011\u0003\u0003%I!a\u001f\u0003!\u0005\u0013(/Y=Ck\u001a4WM\u001d\"m_\u000e\\'B\u0001\r\u001a\u0003!\u0011XmY3jm\u0016\u0014(B\u0001\u000e\u001c\u0003%\u0019HO]3b[&twM\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h'\u0015\u0001!\u0005\u000b\u00170!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fMB\u0011\u0011FK\u0007\u0002/%\u00111f\u0006\u0002\u000e%\u0016\u001cW-\u001b<fI\ncwnY6\u0011\u0005\rj\u0013B\u0001\u0018%\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001M\u001d\u000f\u0005E:dB\u0001\u001a7\u001b\u0005\u0019$B\u0001\u001b6\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0013\n\u0005a\"\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000f\u0013\u0002\u0017\u0005\u0014(/Y=Ck\u001a4WM]\u000b\u0002}A\u0012q(\u0013\t\u0004\u0001\u0016;U\"A!\u000b\u0005\t\u001b\u0015aB7vi\u0006\u0014G.\u001a\u0006\u0003\t\u0012\n!bY8mY\u0016\u001cG/[8o\u0013\t1\u0015IA\u0006BeJ\f\u0017PQ;gM\u0016\u0014\bC\u0001%J\u0019\u0001!\u0011B\u0013\u0002\u0002\u0002\u0003\u0005)\u0011\u0001'\u0003\u0007}#\u0013'\u0001\u0007beJ\f\u0017PQ;gM\u0016\u0014\b%\u0005\u0002N!B\u00111ET\u0005\u0003\u001f\u0012\u0012qAT8uQ&tw\r\u0005\u0002$#&\u0011!\u000b\n\u0002\u0004\u0003:L\u0018A\u0002\u001fj]&$h\b\u0006\u0002V-B\u0011\u0011\u0006\u0001\u0005\u0006y\r\u0001\ra\u0016\u0019\u00031j\u00032\u0001Q#Z!\tA%\fB\u0005K-\u0006\u0005\t\u0011!B\u0001\u0019\u0006!1m\u001c9z)\t)V\fC\u0004=\tA\u0005\t\u0019A,\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0001\r\r\u0002bI*\u0012!-\u001a\t\u0004\u0001\u0016\u001b\u0007C\u0001%e\t%QU!!A\u0001\u0002\u000b\u0005AjK\u0001g!\t9G.D\u0001i\u0015\tI'.A\u0005v]\u000eDWmY6fI*\u00111\u000eJ\u0001\u000bC:tw\u000e^1uS>t\u0017BA7i\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003A\u0004\"!\u001d<\u000e\u0003IT!a\u001d;\u0002\t1\fgn\u001a\u0006\u0002k\u0006!!.\u0019<b\u0013\t9(O\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002uB\u00111e_\u0005\u0003y\u0012\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001U@\t\u0011\u0005\u0005\u0001\"!AA\u0002i\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0004!\u0015\tI!a\u0003Q\u001b\u0005\u0019\u0015bAA\u0007\u0007\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019\"!\u0007\u0011\u0007\r\n)\"C\u0002\u0002\u0018\u0011\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0002)\t\t\u00111\u0001Q\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007A\fy\u0002\u0003\u0005\u0002\u0002-\t\t\u00111\u0001{\u0003!A\u0017m\u001d5D_\u0012,G#\u0001>\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001]\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005M\u0011Q\u0006\u0005\t\u0003\u0003q\u0011\u0011!a\u0001!\u0006\u0001\u0012I\u001d:bs\n+hMZ3s\u00052|7m\u001b\t\u0003SA\u0019R\u0001EA\u001b\u0003\u0013\u0002r!a\u000e\u0002>\u0005\u0005S+\u0004\u0002\u0002:)\u0019\u00111\b\u0013\u0002\u000fI,h\u000e^5nK&!\u0011qHA\u001d\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\u0019\u0005\u0003\u0007\n9\u0005\u0005\u0003A\u000b\u0006\u0015\u0003c\u0001%\u0002H\u0011I!\nEA\u0001\u0002\u0003\u0015\t\u0001\u0014\t\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)\u0019\u0011q\n;\u0002\u0005%|\u0017b\u0001\u001e\u0002NQ\u0011\u0011\u0011G\u0001\u0006CB\u0004H.\u001f\u000b\u0004+\u0006e\u0003B\u0002\u001f\u0014\u0001\u0004\tY\u0006\r\u0003\u0002^\u0005\u0005\u0004\u0003\u0002!F\u0003?\u00022\u0001SA1\t)Q\u0015\u0011LA\u0001\u0002\u0003\u0015\t\u0001T\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9'!\u001e\u0011\u000b\r\nI'!\u001c\n\u0007\u0005-DE\u0001\u0004PaRLwN\u001c\u0019\u0005\u0003_\n\u0019\b\u0005\u0003A\u000b\u0006E\u0004c\u0001%\u0002t\u0011I!\nFA\u0001\u0002\u0003\u0015\t\u0001\u0014\u0005\t\u0003o\"\u0012\u0011!a\u0001+\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0004cA9\u0002\u0000%\u0019\u0011\u0011\u0011:\u0003\r=\u0013'.Z2u\u0001"
)
public class ArrayBufferBlock implements ReceivedBlock, Product, Serializable {
   private final ArrayBuffer arrayBuffer;

   public static Option unapply(final ArrayBufferBlock x$0) {
      return ArrayBufferBlock$.MODULE$.unapply(x$0);
   }

   public static ArrayBufferBlock apply(final ArrayBuffer arrayBuffer) {
      return ArrayBufferBlock$.MODULE$.apply(arrayBuffer);
   }

   public static Function1 andThen(final Function1 g) {
      return ArrayBufferBlock$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ArrayBufferBlock$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ArrayBuffer arrayBuffer() {
      return this.arrayBuffer;
   }

   public ArrayBufferBlock copy(final ArrayBuffer arrayBuffer) {
      return new ArrayBufferBlock(arrayBuffer);
   }

   public ArrayBuffer copy$default$1() {
      return this.arrayBuffer();
   }

   public String productPrefix() {
      return "ArrayBufferBlock";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.arrayBuffer();
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
      return x$1 instanceof ArrayBufferBlock;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "arrayBuffer";
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
            if (x$1 instanceof ArrayBufferBlock) {
               label40: {
                  ArrayBufferBlock var4 = (ArrayBufferBlock)x$1;
                  ArrayBuffer var10000 = this.arrayBuffer();
                  ArrayBuffer var5 = var4.arrayBuffer();
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

   public ArrayBufferBlock(final ArrayBuffer arrayBuffer) {
      this.arrayBuffer = arrayBuffer;
      Product.$init$(this);
   }
}
