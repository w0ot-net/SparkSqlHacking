package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import java.nio.ByteBuffer;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0019\u0003!\u0011#Q\u0001\nyBQa\u0012\u0001\u0005\u0002!Cqa\u0013\u0001\u0002\u0002\u0013\u0005A\nC\u0004O\u0001E\u0005I\u0011A(\t\u000fi\u0003\u0011\u0011!C!7\"9!\rAA\u0001\n\u0003\u0019\u0007bB4\u0001\u0003\u0003%\t\u0001\u001b\u0005\b]\u0002\t\t\u0011\"\u0011p\u0011\u001d1\b!!A\u0005\u0002]Dq\u0001 \u0001\u0002\u0002\u0013\u0005S\u0010\u0003\u0005\u0000\u0001\u0005\u0005I\u0011IA\u0001\u0011%\t\u0019\u0001AA\u0001\n\u0003\n)\u0001C\u0005\u0002\b\u0001\t\t\u0011\"\u0011\u0002\n\u001dQ\u0011QB\f\u0002\u0002#\u0005\u0011$a\u0004\u0007\u0013Y9\u0012\u0011!E\u00013\u0005E\u0001BB$\u0011\t\u0003\tI\u0003C\u0005\u0002\u0004A\t\t\u0011\"\u0012\u0002\u0006!I\u00111\u0006\t\u0002\u0002\u0013\u0005\u0015Q\u0006\u0005\n\u0003c\u0001\u0012\u0011!CA\u0003gA\u0011\"a\u0010\u0011\u0003\u0003%I!!\u0011\u0003\u001f\tKH/\u001a\"vM\u001a,'O\u00117pG.T!\u0001G\r\u0002\u0011I,7-Z5wKJT!AG\u000e\u0002\u0013M$(/Z1nS:<'B\u0001\u000f\u001e\u0003\u0015\u0019\b/\u0019:l\u0015\tqr$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002A\u0005\u0019qN]4\u0014\u000b\u0001\u0011\u0003\u0006L\u0018\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g!\tI#&D\u0001\u0018\u0013\tYsCA\u0007SK\u000e,\u0017N^3e\u00052|7m\u001b\t\u0003G5J!A\f\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001'\u000f\b\u0003c]r!A\r\u001c\u000e\u0003MR!\u0001N\u001b\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!J\u0005\u0003q\u0011\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001\bJ\u0001\u000bEf$XMQ;gM\u0016\u0014X#\u0001 \u0011\u0005}\"U\"\u0001!\u000b\u0005\u0005\u0013\u0015a\u00018j_*\t1)\u0001\u0003kCZ\f\u0017BA#A\u0005)\u0011\u0015\u0010^3Ck\u001a4WM]\u0001\fEf$XMQ;gM\u0016\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0013*\u0003\"!\u000b\u0001\t\u000bq\u001a\u0001\u0019\u0001 \u0002\t\r|\u0007/\u001f\u000b\u0003\u00136Cq\u0001\u0010\u0003\u0011\u0002\u0003\u0007a(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003AS#AP),\u0003I\u0003\"a\u0015-\u000e\u0003QS!!\u0016,\u0002\u0013Ut7\r[3dW\u0016$'BA,%\u0003)\tgN\\8uCRLwN\\\u0005\u00033R\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\f\u0005\u0002^A6\taL\u0003\u0002`\u0005\u0006!A.\u00198h\u0013\t\tgL\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002IB\u00111%Z\u0005\u0003M\u0012\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001b7\u0011\u0005\rR\u0017BA6%\u0005\r\te.\u001f\u0005\b[\"\t\t\u00111\u0001e\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t\u0001\u000fE\u0002ri&l\u0011A\u001d\u0006\u0003g\u0012\n!bY8mY\u0016\u001cG/[8o\u0013\t)(O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u0001=|!\t\u0019\u00130\u0003\u0002{I\t9!i\\8mK\u0006t\u0007bB7\u000b\u0003\u0003\u0005\r![\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002]}\"9QnCA\u0001\u0002\u0004!\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0011\f\u0001\u0002^8TiJLgn\u001a\u000b\u00029\u00061Q-];bYN$2\u0001_A\u0006\u0011\u001dig\"!AA\u0002%\fqBQ=uK\n+hMZ3s\u00052|7m\u001b\t\u0003SA\u0019R\u0001EA\n\u0003?\u0001b!!\u0006\u0002\u001cyJUBAA\f\u0015\r\tI\u0002J\u0001\beVtG/[7f\u0013\u0011\ti\"a\u0006\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\"\u0005\u001dRBAA\u0012\u0015\r\t)CQ\u0001\u0003S>L1AOA\u0012)\t\ty!A\u0003baBd\u0017\u0010F\u0002J\u0003_AQ\u0001P\nA\u0002y\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u00026\u0005m\u0002\u0003B\u0012\u00028yJ1!!\u000f%\u0005\u0019y\u0005\u000f^5p]\"A\u0011Q\b\u000b\u0002\u0002\u0003\u0007\u0011*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0011\u0011\u0007u\u000b)%C\u0002\u0002Hy\u0013aa\u00142kK\u000e$\b"
)
public class ByteBufferBlock implements ReceivedBlock, Product, Serializable {
   private final ByteBuffer byteBuffer;

   public static Option unapply(final ByteBufferBlock x$0) {
      return ByteBufferBlock$.MODULE$.unapply(x$0);
   }

   public static ByteBufferBlock apply(final ByteBuffer byteBuffer) {
      return ByteBufferBlock$.MODULE$.apply(byteBuffer);
   }

   public static Function1 andThen(final Function1 g) {
      return ByteBufferBlock$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ByteBufferBlock$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ByteBuffer byteBuffer() {
      return this.byteBuffer;
   }

   public ByteBufferBlock copy(final ByteBuffer byteBuffer) {
      return new ByteBufferBlock(byteBuffer);
   }

   public ByteBuffer copy$default$1() {
      return this.byteBuffer();
   }

   public String productPrefix() {
      return "ByteBufferBlock";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.byteBuffer();
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
      return x$1 instanceof ByteBufferBlock;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "byteBuffer";
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
            if (x$1 instanceof ByteBufferBlock) {
               label40: {
                  ByteBufferBlock var4 = (ByteBufferBlock)x$1;
                  ByteBuffer var10000 = this.byteBuffer();
                  ByteBuffer var5 = var4.byteBuffer();
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

   public ByteBufferBlock(final ByteBuffer byteBuffer) {
      this.byteBuffer = byteBuffer;
      Product.$init$(this);
   }
}
