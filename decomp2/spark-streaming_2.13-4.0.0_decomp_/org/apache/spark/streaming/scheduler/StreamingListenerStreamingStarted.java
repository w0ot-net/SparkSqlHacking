package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0005\u0002\u0014]\t\t\u0011#\u0001\u0002\u0016\u0019AacFA\u0001\u0012\u0003\t9\u0002\u0003\u0004C!\u0011\u0005\u0011q\u0006\u0005\b}B\t\t\u0011\"\u0012\u0000\u0011%\t\t\u0004EA\u0001\n\u0003\u000b\u0019\u0004C\u0005\u00028A\t\t\u0011\"!\u0002:!I\u0011Q\t\t\u0002\u0002\u0013%\u0011q\t\u0002\"'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u00148\u000b\u001e:fC6LgnZ*uCJ$X\r\u001a\u0006\u00031e\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005iY\u0012!C:ue\u0016\fW.\u001b8h\u0015\taR$A\u0003ta\u0006\u00148N\u0003\u0002\u001f?\u00051\u0011\r]1dQ\u0016T\u0011\u0001I\u0001\u0004_J<7\u0001A\n\u0006\u0001\rJS\u0006\r\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005)ZS\"A\f\n\u00051:\"AF*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005\u0011r\u0013BA\u0018&\u0005\u001d\u0001&o\u001c3vGR\u0004\"!M\u001d\u000f\u0005I:dBA\u001a7\u001b\u0005!$BA\u001b\"\u0003\u0019a$o\\8u}%\ta%\u0003\u00029K\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001e<\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tAT%\u0001\u0003uS6,W#\u0001 \u0011\u0005\u0011z\u0014B\u0001!&\u0005\u0011auN\\4\u0002\u000bQLW.\u001a\u0011\u0002\rqJg.\u001b;?)\t!U\t\u0005\u0002+\u0001!)Ah\u0001a\u0001}\u0005!1m\u001c9z)\t!\u0005\nC\u0004=\tA\u0005\t\u0019\u0001 \u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t1J\u000b\u0002?\u0019.\nQ\n\u0005\u0002O'6\tqJ\u0003\u0002Q#\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003%\u0016\n!\"\u00198o_R\fG/[8o\u0013\t!vJA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A,\u0011\u0005akV\"A-\u000b\u0005i[\u0016\u0001\u00027b]\u001eT\u0011\u0001X\u0001\u0005U\u00064\u0018-\u0003\u0002_3\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u0019\t\u0003I\tL!aY\u0013\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\u0019L\u0007C\u0001\u0013h\u0013\tAWEA\u0002B]fDqA\u001b\u0005\u0002\u0002\u0003\u0007\u0011-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002[B\u0019a.\u001d4\u000e\u0003=T!\u0001]\u0013\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002s_\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t)\b\u0010\u0005\u0002%m&\u0011q/\n\u0002\b\u0005>|G.Z1o\u0011\u001dQ'\"!AA\u0002\u0019\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011qk\u001f\u0005\bU.\t\t\u00111\u0001b\u0003!A\u0017m\u001d5D_\u0012,G#A1\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aV\u0001\u0007KF,\u0018\r\\:\u0015\u0007U\f)\u0001C\u0004k\u001d\u0005\u0005\t\u0019\u00014)\u0007\u0001\tI\u0001\u0005\u0003\u0002\f\u0005=QBAA\u0007\u0015\t\u00116$\u0003\u0003\u0002\u0012\u00055!\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017!I*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8feN#(/Z1nS:<7\u000b^1si\u0016$\u0007C\u0001\u0016\u0011'\u0015\u0001\u0012\u0011DA\u0013!\u0019\tY\"!\t?\t6\u0011\u0011Q\u0004\u0006\u0004\u0003?)\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003G\tiBA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a\n\u0002.5\u0011\u0011\u0011\u0006\u0006\u0004\u0003WY\u0016AA5p\u0013\rQ\u0014\u0011\u0006\u000b\u0003\u0003+\tQ!\u00199qYf$2\u0001RA\u001b\u0011\u0015a4\u00031\u0001?\u0003\u001d)h.\u00199qYf$B!a\u000f\u0002BA!A%!\u0010?\u0013\r\ty$\n\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005\rC#!AA\u0002\u0011\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0005E\u0002Y\u0003\u0017J1!!\u0014Z\u0005\u0019y%M[3di\u0002"
)
public class StreamingListenerStreamingStarted implements StreamingListenerEvent, Product, Serializable {
   private final long time;

   public static Option unapply(final StreamingListenerStreamingStarted x$0) {
      return StreamingListenerStreamingStarted$.MODULE$.unapply(x$0);
   }

   public static StreamingListenerStreamingStarted apply(final long time) {
      return StreamingListenerStreamingStarted$.MODULE$.apply(time);
   }

   public static Function1 andThen(final Function1 g) {
      return StreamingListenerStreamingStarted$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StreamingListenerStreamingStarted$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long time() {
      return this.time;
   }

   public StreamingListenerStreamingStarted copy(final long time) {
      return new StreamingListenerStreamingStarted(time);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String productPrefix() {
      return "StreamingListenerStreamingStarted";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
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
      return x$1 instanceof StreamingListenerStreamingStarted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.time()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof StreamingListenerStreamingStarted) {
               StreamingListenerStreamingStarted var4 = (StreamingListenerStreamingStarted)x$1;
               if (this.time() == var4.time() && var4.canEqual(this)) {
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

   public StreamingListenerStreamingStarted(final long time) {
      this.time = time;
      Product.$init$(this);
   }
}
