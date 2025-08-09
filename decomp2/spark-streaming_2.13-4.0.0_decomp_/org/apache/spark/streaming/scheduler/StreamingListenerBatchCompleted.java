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
   bytes = "\u0006\u0005\u0005=c\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0005\u0002\u0014]\t\t\u0011#\u0001\u0002\u0016\u0019AacFA\u0001\u0012\u0003\t9\u0002\u0003\u0004C!\u0011\u0005\u0011q\u0006\u0005\b}B\t\t\u0011\"\u0012\u0000\u0011%\t\t\u0004EA\u0001\n\u0003\u000b\u0019\u0004C\u0005\u00028A\t\t\u0011\"!\u0002:!I\u0011Q\t\t\u0002\u0002\u0013%\u0011q\t\u0002 'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014()\u0019;dQ\u000e{W\u000e\u001d7fi\u0016$'B\u0001\r\u001a\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001b7\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sO\u000e\u00011#\u0002\u0001$S5\u0002\u0004C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#AB!osJ+g\r\u0005\u0002+W5\tq#\u0003\u0002-/\t12\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u0002%]%\u0011q&\n\u0002\b!J|G-^2u!\t\t\u0014H\u0004\u00023o9\u00111GN\u0007\u0002i)\u0011Q'I\u0001\u0007yI|w\u000e\u001e \n\u0003\u0019J!\u0001O\u0013\u0002\u000fA\f7m[1hK&\u0011!h\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003q\u0015\n\u0011BY1uG\"LeNZ8\u0016\u0003y\u0002\"AK \n\u0005\u0001;\"!\u0003\"bi\u000eD\u0017J\u001c4p\u0003)\u0011\u0017\r^2i\u0013:4w\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0011+\u0005C\u0001\u0016\u0001\u0011\u0015a4\u00011\u0001?\u0003\u0011\u0019w\u000e]=\u0015\u0005\u0011C\u0005b\u0002\u001f\u0005!\u0003\u0005\rAP\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005Y%F\u0001 MW\u0005i\u0005C\u0001(T\u001b\u0005y%B\u0001)R\u0003%)hn\u00195fG.,GM\u0003\u0002SK\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Q{%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\u0016\t\u00031vk\u0011!\u0017\u0006\u00035n\u000bA\u0001\\1oO*\tA,\u0001\u0003kCZ\f\u0017B\u00010Z\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0011\r\u0005\u0002%E&\u00111-\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003M&\u0004\"\u0001J4\n\u0005!,#aA!os\"9!\u000eCA\u0001\u0002\u0004\t\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001n!\rq\u0017OZ\u0007\u0002_*\u0011\u0001/J\u0001\u000bG>dG.Z2uS>t\u0017B\u0001:p\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005UD\bC\u0001\u0013w\u0013\t9XEA\u0004C_>dW-\u00198\t\u000f)T\u0011\u0011!a\u0001M\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\t96\u0010C\u0004k\u0017\u0005\u0005\t\u0019A1\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!Y\u0001\ti>\u001cFO]5oOR\tq+\u0001\u0004fcV\fGn\u001d\u000b\u0004k\u0006\u0015\u0001b\u00026\u000f\u0003\u0003\u0005\rA\u001a\u0015\u0004\u0001\u0005%\u0001\u0003BA\u0006\u0003\u001fi!!!\u0004\u000b\u0005I[\u0012\u0002BA\t\u0003\u001b\u0011A\u0002R3wK2|\u0007/\u001a:Ba&\fqd\u0015;sK\u0006l\u0017N\\4MSN$XM\\3s\u0005\u0006$8\r[\"p[BdW\r^3e!\tQ\u0003cE\u0003\u0011\u00033\t)\u0003\u0005\u0004\u0002\u001c\u0005\u0005b\bR\u0007\u0003\u0003;Q1!a\b&\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\t\u0002\u001e\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005\u001d\u0012QF\u0007\u0003\u0003SQ1!a\u000b\\\u0003\tIw.C\u0002;\u0003S!\"!!\u0006\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0011\u000b)\u0004C\u0003='\u0001\u0007a(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005m\u0012\u0011\t\t\u0005I\u0005ub(C\u0002\u0002@\u0015\u0012aa\u00149uS>t\u0007\u0002CA\")\u0005\u0005\t\u0019\u0001#\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002JA\u0019\u0001,a\u0013\n\u0007\u00055\u0013L\u0001\u0004PE*,7\r\u001e"
)
public class StreamingListenerBatchCompleted implements StreamingListenerEvent, Product, Serializable {
   private final BatchInfo batchInfo;

   public static Option unapply(final StreamingListenerBatchCompleted x$0) {
      return StreamingListenerBatchCompleted$.MODULE$.unapply(x$0);
   }

   public static StreamingListenerBatchCompleted apply(final BatchInfo batchInfo) {
      return StreamingListenerBatchCompleted$.MODULE$.apply(batchInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return StreamingListenerBatchCompleted$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StreamingListenerBatchCompleted$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BatchInfo batchInfo() {
      return this.batchInfo;
   }

   public StreamingListenerBatchCompleted copy(final BatchInfo batchInfo) {
      return new StreamingListenerBatchCompleted(batchInfo);
   }

   public BatchInfo copy$default$1() {
      return this.batchInfo();
   }

   public String productPrefix() {
      return "StreamingListenerBatchCompleted";
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
      return x$1 instanceof StreamingListenerBatchCompleted;
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
            if (x$1 instanceof StreamingListenerBatchCompleted) {
               label40: {
                  StreamingListenerBatchCompleted var4 = (StreamingListenerBatchCompleted)x$1;
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

   public StreamingListenerBatchCompleted(final BatchInfo batchInfo) {
      this.batchInfo = batchInfo;
      Product.$init$(this);
   }
}
