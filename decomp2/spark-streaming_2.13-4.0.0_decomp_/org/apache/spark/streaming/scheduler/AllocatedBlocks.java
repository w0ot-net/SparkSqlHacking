package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc!B\f\u0019\u0001j\u0011\u0003\u0002C\u001d\u0001\u0005+\u0007I\u0011\u0001\u001e\t\u00115\u0003!\u0011#Q\u0001\nmBQA\u0014\u0001\u0005\u0002=CQA\u0015\u0001\u0005\u0002MCqA\u0016\u0001\u0002\u0002\u0013\u0005q\u000bC\u0004Z\u0001E\u0005I\u0011\u0001.\t\u000f\u0015\u0004\u0011\u0011!C!M\"9q\u000eAA\u0001\n\u0003\u0001\bbB9\u0001\u0003\u0003%\tA\u001d\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011%\t\t\u0001AA\u0001\n\u0003\t\u0019\u0001C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0011\u0002\u0010!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013Q\u0003\u0005\n\u0003/\u0001\u0011\u0011!C!\u00033A\u0011\"a\u0007\u0001\u0003\u0003%\t%!\b\b\u0015\u0005\u0005\u0002$!A\t\u0002i\t\u0019CB\u0005\u00181\u0005\u0005\t\u0012\u0001\u000e\u0002&!1a*\u0005C\u0001\u0003{A\u0011\"a\u0006\u0012\u0003\u0003%)%!\u0007\t\u0013\u0005}\u0012#!A\u0005\u0002\u0006\u0005\u0003\"CA##\u0005\u0005I\u0011QA$\u0011%\t\u0019&EA\u0001\n\u0013\t)FA\bBY2|7-\u0019;fI\ncwnY6t\u0015\tI\"$A\u0005tG\",G-\u001e7fe*\u00111\u0004H\u0001\ngR\u0014X-Y7j]\u001eT!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\n\u0005\u0001\rJC\u0006\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003I)J!aK\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011QF\u000e\b\u0003]Qr!aL\u001a\u000e\u0003AR!!\r\u001a\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AJ\u0005\u0003k\u0015\nq\u0001]1dW\u0006<W-\u0003\u00028q\ta1+\u001a:jC2L'0\u00192mK*\u0011Q'J\u0001\u001agR\u0014X-Y7JIR{\u0017\t\u001c7pG\u0006$X\r\u001a\"m_\u000e\\7/F\u0001<!\u0011a\u0004i\u0011$\u000f\u0005ur\u0004CA\u0018&\u0013\tyT%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0003\n\u00131!T1q\u0015\tyT\u0005\u0005\u0002%\t&\u0011Q)\n\u0002\u0004\u0013:$\bcA\u0017H\u0013&\u0011\u0001\n\u000f\u0002\u0004'\u0016\f\bC\u0001&L\u001b\u0005A\u0012B\u0001'\u0019\u0005E\u0011VmY3jm\u0016$'\t\\8dW&sgm\\\u0001\u001bgR\u0014X-Y7JIR{\u0017\t\u001c7pG\u0006$X\r\u001a\"m_\u000e\\7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005A\u000b\u0006C\u0001&\u0001\u0011\u0015I4\u00011\u0001<\u0003E9W\r\u001e\"m_\u000e\\7o\u00144TiJ,\u0017-\u001c\u000b\u0003\rRCQ!\u0016\u0003A\u0002\r\u000b\u0001b\u001d;sK\u0006l\u0017\nZ\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002Q1\"9\u0011(\u0002I\u0001\u0002\u0004Y\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u00027*\u00121\bX\u0016\u0002;B\u0011alY\u0007\u0002?*\u0011\u0001-Y\u0001\nk:\u001c\u0007.Z2lK\u0012T!AY\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002e?\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\u0007C\u00015n\u001b\u0005I'B\u00016l\u0003\u0011a\u0017M\\4\u000b\u00031\fAA[1wC&\u0011a.\u001b\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\r\u000ba\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002tmB\u0011A\u0005^\u0005\u0003k\u0016\u00121!\u00118z\u0011\u001d9\u0018\"!AA\u0002\r\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001>\u0011\u0007mt8/D\u0001}\u0015\tiX%\u0001\u0006d_2dWm\u0019;j_:L!a ?\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u000b\tY\u0001E\u0002%\u0003\u000fI1!!\u0003&\u0005\u001d\u0011un\u001c7fC:Dqa^\u0006\u0002\u0002\u0003\u00071/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA4\u0002\u0012!9q\u000fDA\u0001\u0002\u0004\u0019\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\r\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002O\u00061Q-];bYN$B!!\u0002\u0002 !9qoDA\u0001\u0002\u0004\u0019\u0018aD!mY>\u001c\u0017\r^3e\u00052|7m[:\u0011\u0005)\u000b2#B\t\u0002(\u0005M\u0002CBA\u0015\u0003_Y\u0004+\u0004\u0002\u0002,)\u0019\u0011QF\u0013\u0002\u000fI,h\u000e^5nK&!\u0011\u0011GA\u0016\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003k\tY$\u0004\u0002\u00028)\u0019\u0011\u0011H6\u0002\u0005%|\u0017bA\u001c\u00028Q\u0011\u00111E\u0001\u0006CB\u0004H.\u001f\u000b\u0004!\u0006\r\u0003\"B\u001d\u0015\u0001\u0004Y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0013\ny\u0005\u0005\u0003%\u0003\u0017Z\u0014bAA'K\t1q\n\u001d;j_:D\u0001\"!\u0015\u0016\u0003\u0003\u0005\r\u0001U\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA,!\rA\u0017\u0011L\u0005\u0004\u00037J'AB(cU\u0016\u001cG\u000f"
)
public class AllocatedBlocks implements Product, Serializable {
   private final Map streamIdToAllocatedBlocks;

   public static Option unapply(final AllocatedBlocks x$0) {
      return AllocatedBlocks$.MODULE$.unapply(x$0);
   }

   public static AllocatedBlocks apply(final Map streamIdToAllocatedBlocks) {
      return AllocatedBlocks$.MODULE$.apply(streamIdToAllocatedBlocks);
   }

   public static Function1 andThen(final Function1 g) {
      return AllocatedBlocks$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return AllocatedBlocks$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Map streamIdToAllocatedBlocks() {
      return this.streamIdToAllocatedBlocks;
   }

   public Seq getBlocksOfStream(final int streamId) {
      return (Seq)this.streamIdToAllocatedBlocks().getOrElse(BoxesRunTime.boxToInteger(streamId), () -> (Seq).MODULE$.Seq().empty());
   }

   public AllocatedBlocks copy(final Map streamIdToAllocatedBlocks) {
      return new AllocatedBlocks(streamIdToAllocatedBlocks);
   }

   public Map copy$default$1() {
      return this.streamIdToAllocatedBlocks();
   }

   public String productPrefix() {
      return "AllocatedBlocks";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.streamIdToAllocatedBlocks();
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
      return x$1 instanceof AllocatedBlocks;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamIdToAllocatedBlocks";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof AllocatedBlocks) {
               label40: {
                  AllocatedBlocks var4 = (AllocatedBlocks)x$1;
                  Map var10000 = this.streamIdToAllocatedBlocks();
                  Map var5 = var4.streamIdToAllocatedBlocks();
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

   public AllocatedBlocks(final Map streamIdToAllocatedBlocks) {
      this.streamIdToAllocatedBlocks = streamIdToAllocatedBlocks;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
