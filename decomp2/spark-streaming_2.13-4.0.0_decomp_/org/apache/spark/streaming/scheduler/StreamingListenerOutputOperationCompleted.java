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
   bytes = "\u0006\u0005\u0005=c\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0005\u0002\u0014]\t\t\u0011#\u0001\u0002\u0016\u0019AacFA\u0001\u0012\u0003\t9\u0002\u0003\u0004C!\u0011\u0005\u0011q\u0006\u0005\b}B\t\t\u0011\"\u0012\u0000\u0011%\t\t\u0004EA\u0001\n\u0003\u000b\u0019\u0004C\u0005\u00028A\t\t\u0011\"!\u0002:!I\u0011Q\t\t\u0002\u0002\u0013%\u0011q\t\u0002*'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014x*\u001e;qkR|\u0005/\u001a:bi&|gnQ8na2,G/\u001a3\u000b\u0005aI\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tQ2$A\u0005tiJ,\u0017-\\5oO*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\u0019\u0013&\f\u0019\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\r\u0005s\u0017PU3g!\tQ3&D\u0001\u0018\u0013\tasC\u0001\fTiJ,\u0017-\\5oO2K7\u000f^3oKJ,e/\u001a8u!\t!c&\u0003\u00020K\t9\u0001K]8ek\u000e$\bCA\u0019:\u001d\t\u0011tG\u0004\u00024m5\tAG\u0003\u00026C\u00051AH]8pizJ\u0011AJ\u0005\u0003q\u0015\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001(J\u0001\u0014_V$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]&sgm\\\u000b\u0002}A\u0011!fP\u0005\u0003\u0001^\u00111cT;uaV$x\n]3sCRLwN\\%oM>\fAc\\;uaV$x\n]3sCRLwN\\%oM>\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002E\u000bB\u0011!\u0006\u0001\u0005\u0006y\r\u0001\rAP\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002E\u0011\"9A\b\u0002I\u0001\u0002\u0004q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0017*\u0012a\bT\u0016\u0002\u001bB\u0011ajU\u0007\u0002\u001f*\u0011\u0001+U\u0001\nk:\u001c\u0007.Z2lK\u0012T!AU\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002U\u001f\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011a,\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0005\u0004\"\u0001\n2\n\u0005\r,#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00014j!\t!s-\u0003\u0002iK\t\u0019\u0011I\\=\t\u000f)D\u0011\u0011!a\u0001C\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001c\t\u0004]F4W\"A8\u000b\u0005A,\u0013AC2pY2,7\r^5p]&\u0011!o\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002vqB\u0011AE^\u0005\u0003o\u0016\u0012qAQ8pY\u0016\fg\u000eC\u0004k\u0015\u0005\u0005\t\u0019\u00014\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003/nDqA[\u0006\u0002\u0002\u0003\u0007\u0011-\u0001\u0005iCND7i\u001c3f)\u0005\t\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003]\u000ba!Z9vC2\u001cHcA;\u0002\u0006!9!NDA\u0001\u0002\u00041\u0007f\u0001\u0001\u0002\nA!\u00111BA\b\u001b\t\tiA\u0003\u0002S7%!\u0011\u0011CA\u0007\u00051!UM^3m_B,'/\u00119j\u0003%\u001aFO]3b[&tw\rT5ti\u0016tWM](viB,Ho\u00149fe\u0006$\u0018n\u001c8D_6\u0004H.\u001a;fIB\u0011!\u0006E\n\u0006!\u0005e\u0011Q\u0005\t\u0007\u00037\t\tC\u0010#\u000e\u0005\u0005u!bAA\u0010K\u00059!/\u001e8uS6,\u0017\u0002BA\u0012\u0003;\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t9#!\f\u000e\u0005\u0005%\"bAA\u00167\u0006\u0011\u0011n\\\u0005\u0004u\u0005%BCAA\u000b\u0003\u0015\t\u0007\u000f\u001d7z)\r!\u0015Q\u0007\u0005\u0006yM\u0001\rAP\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tY$!\u0011\u0011\t\u0011\niDP\u0005\u0004\u0003\u007f)#AB(qi&|g\u000e\u0003\u0005\u0002DQ\t\t\u00111\u0001E\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0013\u00022\u0001WA&\u0013\r\ti%\u0017\u0002\u0007\u001f\nTWm\u0019;"
)
public class StreamingListenerOutputOperationCompleted implements StreamingListenerEvent, Product, Serializable {
   private final OutputOperationInfo outputOperationInfo;

   public static Option unapply(final StreamingListenerOutputOperationCompleted x$0) {
      return StreamingListenerOutputOperationCompleted$.MODULE$.unapply(x$0);
   }

   public static StreamingListenerOutputOperationCompleted apply(final OutputOperationInfo outputOperationInfo) {
      return StreamingListenerOutputOperationCompleted$.MODULE$.apply(outputOperationInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return StreamingListenerOutputOperationCompleted$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StreamingListenerOutputOperationCompleted$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public OutputOperationInfo outputOperationInfo() {
      return this.outputOperationInfo;
   }

   public StreamingListenerOutputOperationCompleted copy(final OutputOperationInfo outputOperationInfo) {
      return new StreamingListenerOutputOperationCompleted(outputOperationInfo);
   }

   public OutputOperationInfo copy$default$1() {
      return this.outputOperationInfo();
   }

   public String productPrefix() {
      return "StreamingListenerOutputOperationCompleted";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.outputOperationInfo();
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
      return x$1 instanceof StreamingListenerOutputOperationCompleted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "outputOperationInfo";
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
            if (x$1 instanceof StreamingListenerOutputOperationCompleted) {
               label40: {
                  StreamingListenerOutputOperationCompleted var4 = (StreamingListenerOutputOperationCompleted)x$1;
                  OutputOperationInfo var10000 = this.outputOperationInfo();
                  OutputOperationInfo var5 = var4.outputOperationInfo();
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

   public StreamingListenerOutputOperationCompleted(final OutputOperationInfo outputOperationInfo) {
      this.outputOperationInfo = outputOperationInfo;
      Product.$init$(this);
   }
}
