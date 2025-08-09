package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0005\u0002\u0014]\t\t\u0011#\u0001\u0002\u0016\u0019AacFA\u0001\u0012\u0003\t9\u0002\u0003\u0004C!\u0011\u0005\u0011q\u0006\u0005\b}B\t\t\u0011\"\u0012\u0000\u0011%\t\t\u0004EA\u0001\n\u0003\u000b\u0019\u0004C\u0005\u00028A\t\t\u0011\"!\u0002:!I\u0011Q\t\t\u0002\u0002\u0013%\u0011q\t\u0002\u001e'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014()\u0019;dQN#\u0018M\u001d;fI*\u0011\u0001$G\u0001\ng\u000eDW\rZ;mKJT!AG\u000e\u0002\u0013M$(/Z1nS:<'B\u0001\u000f\u001e\u0003\u0015\u0019\b/\u0019:l\u0015\tqr$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002A\u0005\u0019qN]4\u0004\u0001M)\u0001aI\u0015.aA\u0011AeJ\u0007\u0002K)\ta%A\u0003tG\u0006d\u0017-\u0003\u0002)K\t1\u0011I\\=SK\u001a\u0004\"AK\u0016\u000e\u0003]I!\u0001L\f\u0003-M#(/Z1nS:<G*[:uK:,'/\u0012<f]R\u0004\"\u0001\n\u0018\n\u0005=*#a\u0002)s_\u0012,8\r\u001e\t\u0003cer!AM\u001c\u000f\u0005M2T\"\u0001\u001b\u000b\u0005U\n\u0013A\u0002\u001fs_>$h(C\u0001'\u0013\tAT%A\u0004qC\u000e\\\u0017mZ3\n\u0005iZ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001d&\u0003%\u0011\u0017\r^2i\u0013:4w.F\u0001?!\tQs(\u0003\u0002A/\tI!)\u0019;dQ&sgm\\\u0001\u000bE\u0006$8\r[%oM>\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002E\u000bB\u0011!\u0006\u0001\u0005\u0006y\r\u0001\rAP\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002E\u0011\"9A\b\u0002I\u0001\u0002\u0004q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0017*\u0012a\bT\u0016\u0002\u001bB\u0011ajU\u0007\u0002\u001f*\u0011\u0001+U\u0001\nk:\u001c\u0007.Z2lK\u0012T!AU\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002U\u001f\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011a,\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0005\u0004\"\u0001\n2\n\u0005\r,#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00014j!\t!s-\u0003\u0002iK\t\u0019\u0011I\\=\t\u000f)D\u0011\u0011!a\u0001C\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001c\t\u0004]F4W\"A8\u000b\u0005A,\u0013AC2pY2,7\r^5p]&\u0011!o\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002vqB\u0011AE^\u0005\u0003o\u0016\u0012qAQ8pY\u0016\fg\u000eC\u0004k\u0015\u0005\u0005\t\u0019\u00014\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003/nDqA[\u0006\u0002\u0002\u0003\u0007\u0011-\u0001\u0005iCND7i\u001c3f)\u0005\t\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003]\u000ba!Z9vC2\u001cHcA;\u0002\u0006!9!NDA\u0001\u0002\u00041\u0007f\u0001\u0001\u0002\nA!\u00111BA\b\u001b\t\tiA\u0003\u0002S7%!\u0011\u0011CA\u0007\u00051!UM^3m_B,'/\u00119j\u0003u\u0019FO]3b[&tw\rT5ti\u0016tWM\u001d\"bi\u000eD7\u000b^1si\u0016$\u0007C\u0001\u0016\u0011'\u0015\u0001\u0012\u0011DA\u0013!\u0019\tY\"!\t?\t6\u0011\u0011Q\u0004\u0006\u0004\u0003?)\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003G\tiBA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a\n\u0002.5\u0011\u0011\u0011\u0006\u0006\u0004\u0003WY\u0016AA5p\u0013\rQ\u0014\u0011\u0006\u000b\u0003\u0003+\tQ!\u00199qYf$2\u0001RA\u001b\u0011\u0015a4\u00031\u0001?\u0003\u001d)h.\u00199qYf$B!a\u000f\u0002BA!A%!\u0010?\u0013\r\ty$\n\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005\rC#!AA\u0002\u0011\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0005E\u0002Y\u0003\u0017J1!!\u0014Z\u0005\u0019y%M[3di\u0002"
)
public class StreamingListenerBatchStarted implements StreamingListenerEvent, Product, Serializable {
   private final BatchInfo batchInfo;

   public static Option unapply(final StreamingListenerBatchStarted x$0) {
      return StreamingListenerBatchStarted$.MODULE$.unapply(x$0);
   }

   public static StreamingListenerBatchStarted apply(final BatchInfo batchInfo) {
      return StreamingListenerBatchStarted$.MODULE$.apply(batchInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return StreamingListenerBatchStarted$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StreamingListenerBatchStarted$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BatchInfo batchInfo() {
      return this.batchInfo;
   }

   public StreamingListenerBatchStarted copy(final BatchInfo batchInfo) {
      return new StreamingListenerBatchStarted(batchInfo);
   }

   public BatchInfo copy$default$1() {
      return this.batchInfo();
   }

   public String productPrefix() {
      return "StreamingListenerBatchStarted";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.batchInfo();
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
      return x$1 instanceof StreamingListenerBatchStarted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "batchInfo";
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
            if (x$1 instanceof StreamingListenerBatchStarted) {
               label40: {
                  StreamingListenerBatchStarted var4 = (StreamingListenerBatchStarted)x$1;
                  BatchInfo var10000 = this.batchInfo();
                  BatchInfo var5 = var4.batchInfo();
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

   public StreamingListenerBatchStarted(final BatchInfo batchInfo) {
      this.batchInfo = batchInfo;
      Product.$init$(this);
   }
}
