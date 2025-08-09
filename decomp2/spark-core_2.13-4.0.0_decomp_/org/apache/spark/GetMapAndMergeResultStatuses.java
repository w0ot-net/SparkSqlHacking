package org.apache.spark;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb!\u0002\f\u0018\u0001^i\u0002\u0002\u0003\u001d\u0001\u0005+\u0007I\u0011A\u001d\t\u0011u\u0002!\u0011#Q\u0001\niBQA\u0010\u0001\u0005\u0002}BqA\u0011\u0001\u0002\u0002\u0013\u00051\tC\u0004F\u0001E\u0005I\u0011\u0001$\t\u000fE\u0003\u0011\u0011!C!%\"91\fAA\u0001\n\u0003I\u0004b\u0002/\u0001\u0003\u0003%\t!\u0018\u0005\bG\u0002\t\t\u0011\"\u0011e\u0011\u001dY\u0007!!A\u0005\u00021Dq!\u001d\u0001\u0002\u0002\u0013\u0005#\u000fC\u0004u\u0001\u0005\u0005I\u0011I;\t\u000fY\u0004\u0011\u0011!C!o\"9\u0001\u0010AA\u0001\n\u0003Jx\u0001C>\u0018\u0003\u0003E\ta\u0006?\u0007\u0011Y9\u0012\u0011!E\u0001/uDaA\u0010\t\u0005\u0002\u0005M\u0001b\u0002<\u0011\u0003\u0003%)e\u001e\u0005\n\u0003+\u0001\u0012\u0011!CA\u0003/A\u0011\"a\u0007\u0011\u0003\u0003%\t)!\b\t\u0013\u0005%\u0002#!A\u0005\n\u0005-\"\u0001H$fi6\u000b\u0007/\u00118e\u001b\u0016\u0014x-\u001a*fgVdGo\u0015;biV\u001cXm\u001d\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sON)\u0001A\b\u0013)WA\u0011qDI\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1\u0011I\\=SK\u001a\u0004\"!\n\u0014\u000e\u0003]I!aJ\f\u0003/5\u000b\u0007oT;uaV$HK]1dW\u0016\u0014X*Z:tC\u001e,\u0007CA\u0010*\u0013\tQ\u0003EA\u0004Qe>$Wo\u0019;\u0011\u00051*dBA\u00174\u001d\tq#'D\u00010\u0015\t\u0001\u0014'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\t\u0013B\u0001\u001b!\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Q\u0002\u0013!C:ik\u001a4G.Z%e+\u0005Q\u0004CA\u0010<\u0013\ta\u0004EA\u0002J]R\f!b\u001d5vM\u001adW-\u00133!\u0003\u0019a\u0014N\\5u}Q\u0011\u0001)\u0011\t\u0003K\u0001AQ\u0001O\u0002A\u0002i\nAaY8qsR\u0011\u0001\t\u0012\u0005\bq\u0011\u0001\n\u00111\u0001;\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0012\u0016\u0003u![\u0013!\u0013\t\u0003\u0015>k\u0011a\u0013\u0006\u0003\u00196\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00059\u0003\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001k\u0013\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001T!\t!\u0016,D\u0001V\u0015\t1v+\u0001\u0003mC:<'\"\u0001-\u0002\t)\fg/Y\u0005\u00035V\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003=\u0006\u0004\"aH0\n\u0005\u0001\u0004#aA!os\"9!\rCA\u0001\u0002\u0004Q\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001f!\r1\u0017NX\u0007\u0002O*\u0011\u0001\u000eI\u0001\u000bG>dG.Z2uS>t\u0017B\u00016h\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u00055\u0004\bCA\u0010o\u0013\ty\u0007EA\u0004C_>dW-\u00198\t\u000f\tT\u0011\u0011!a\u0001=\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\t\u00196\u000fC\u0004c\u0017\u0005\u0005\t\u0019\u0001\u001e\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AO\u0001\ti>\u001cFO]5oOR\t1+\u0001\u0004fcV\fGn\u001d\u000b\u0003[jDqA\u0019\b\u0002\u0002\u0003\u0007a,\u0001\u000fHKRl\u0015\r]!oI6+'oZ3SKN,H\u000e^*uCR,8/Z:\u0011\u0005\u0015\u00022\u0003\u0002\t\u007f\u0003\u0013\u0001Ra`A\u0003u\u0001k!!!\u0001\u000b\u0007\u0005\r\u0001%A\u0004sk:$\u0018.\\3\n\t\u0005\u001d\u0011\u0011\u0001\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u0006\u0003#i!!!\u0004\u000b\u0007\u0005=q+\u0001\u0002j_&\u0019a'!\u0004\u0015\u0003q\fQ!\u00199qYf$2\u0001QA\r\u0011\u0015A4\u00031\u0001;\u0003\u001d)h.\u00199qYf$B!a\b\u0002&A!q$!\t;\u0013\r\t\u0019\u0003\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005\u001dB#!AA\u0002\u0001\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0003E\u0002U\u0003_I1!!\rV\u0005\u0019y%M[3di\u0002"
)
public class GetMapAndMergeResultStatuses implements MapOutputTrackerMessage, Product, Serializable {
   private final int shuffleId;

   public static Option unapply(final GetMapAndMergeResultStatuses x$0) {
      return GetMapAndMergeResultStatuses$.MODULE$.unapply(x$0);
   }

   public static GetMapAndMergeResultStatuses apply(final int shuffleId) {
      return GetMapAndMergeResultStatuses$.MODULE$.apply(shuffleId);
   }

   public static Function1 andThen(final Function1 g) {
      return GetMapAndMergeResultStatuses$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return GetMapAndMergeResultStatuses$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public GetMapAndMergeResultStatuses copy(final int shuffleId) {
      return new GetMapAndMergeResultStatuses(shuffleId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public String productPrefix() {
      return "GetMapAndMergeResultStatuses";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
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
      return x$1 instanceof GetMapAndMergeResultStatuses;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.shuffleId());
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof GetMapAndMergeResultStatuses) {
               GetMapAndMergeResultStatuses var4 = (GetMapAndMergeResultStatuses)x$1;
               if (this.shuffleId() == var4.shuffleId() && var4.canEqual(this)) {
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

   public GetMapAndMergeResultStatuses(final int shuffleId) {
      this.shuffleId = shuffleId;
      Product.$init$(this);
   }
}
