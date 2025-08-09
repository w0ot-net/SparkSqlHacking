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
   bytes = "\u0006\u0005\u0005=c\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0005\u0002\u0014]\t\t\u0011#\u0001\u0002\u0016\u0019AacFA\u0001\u0012\u0003\t9\u0002\u0003\u0004C!\u0011\u0005\u0011q\u0006\u0005\b}B\t\t\u0011\"\u0012\u0000\u0011%\t\t\u0004EA\u0001\n\u0003\u000b\u0019\u0004C\u0005\u00028A\t\t\u0011\"!\u0002:!I\u0011Q\t\t\u0002\u0002\u0013%\u0011q\t\u0002!'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014(+Z2fSZ,'o\u0015;beR,GM\u0003\u0002\u00193\u0005I1o\u00195fIVdWM\u001d\u0006\u00035m\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005qi\u0012!B:qCJ\\'B\u0001\u0010 \u0003\u0019\t\u0007/Y2iK*\t\u0001%A\u0002pe\u001e\u001c\u0001aE\u0003\u0001G%j\u0003\u0007\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003U-j\u0011aF\u0005\u0003Y]\u0011ac\u0015;sK\u0006l\u0017N\\4MSN$XM\\3s\u000bZ,g\u000e\u001e\t\u0003I9J!aL\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011'\u000f\b\u0003e]r!a\r\u001c\u000e\u0003QR!!N\u0011\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0013B\u0001\u001d&\u0003\u001d\u0001\u0018mY6bO\u0016L!AO\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005a*\u0013\u0001\u0004:fG\u0016Lg/\u001a:J]\u001a|W#\u0001 \u0011\u0005)z\u0014B\u0001!\u0018\u00051\u0011VmY3jm\u0016\u0014\u0018J\u001c4p\u00035\u0011XmY3jm\u0016\u0014\u0018J\u001c4pA\u00051A(\u001b8jiz\"\"\u0001R#\u0011\u0005)\u0002\u0001\"\u0002\u001f\u0004\u0001\u0004q\u0014\u0001B2paf$\"\u0001\u0012%\t\u000fq\"\u0001\u0013!a\u0001}\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A&+\u0005yb5&A'\u0011\u00059\u001bV\"A(\u000b\u0005A\u000b\u0016!C;oG\",7m[3e\u0015\t\u0011V%\u0001\u0006b]:|G/\u0019;j_:L!\u0001V(\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002/B\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\u0005Y\u0006twMC\u0001]\u0003\u0011Q\u0017M^1\n\u0005yK&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001b!\t!#-\u0003\u0002dK\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011a-\u001b\t\u0003I\u001dL!\u0001[\u0013\u0003\u0007\u0005s\u0017\u0010C\u0004k\u0011\u0005\u0005\t\u0019A1\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005i\u0007c\u00018rM6\tqN\u0003\u0002qK\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005I|'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"!\u001e=\u0011\u0005\u00112\u0018BA<&\u0005\u001d\u0011un\u001c7fC:DqA\u001b\u0006\u0002\u0002\u0003\u0007a-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GCA,|\u0011\u001dQ7\"!AA\u0002\u0005\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002C\u0006AAo\\*ue&tw\rF\u0001X\u0003\u0019)\u0017/^1mgR\u0019Q/!\u0002\t\u000f)t\u0011\u0011!a\u0001M\"\u001a\u0001!!\u0003\u0011\t\u0005-\u0011qB\u0007\u0003\u0003\u001bQ!AU\u000e\n\t\u0005E\u0011Q\u0002\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001!'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014(+Z2fSZ,'o\u0015;beR,G\r\u0005\u0002+!M)\u0001#!\u0007\u0002&A1\u00111DA\u0011}\u0011k!!!\b\u000b\u0007\u0005}Q%A\u0004sk:$\u0018.\\3\n\t\u0005\r\u0012Q\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u0014\u0003[i!!!\u000b\u000b\u0007\u0005-2,\u0001\u0002j_&\u0019!(!\u000b\u0015\u0005\u0005U\u0011!B1qa2LHc\u0001#\u00026!)Ah\u0005a\u0001}\u00059QO\\1qa2LH\u0003BA\u001e\u0003\u0003\u0002B\u0001JA\u001f}%\u0019\u0011qH\u0013\u0003\r=\u0003H/[8o\u0011!\t\u0019\u0005FA\u0001\u0002\u0004!\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\n\t\u00041\u0006-\u0013bAA'3\n1qJ\u00196fGR\u0004"
)
public class StreamingListenerReceiverStarted implements StreamingListenerEvent, Product, Serializable {
   private final ReceiverInfo receiverInfo;

   public static Option unapply(final StreamingListenerReceiverStarted x$0) {
      return StreamingListenerReceiverStarted$.MODULE$.unapply(x$0);
   }

   public static StreamingListenerReceiverStarted apply(final ReceiverInfo receiverInfo) {
      return StreamingListenerReceiverStarted$.MODULE$.apply(receiverInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return StreamingListenerReceiverStarted$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StreamingListenerReceiverStarted$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ReceiverInfo receiverInfo() {
      return this.receiverInfo;
   }

   public StreamingListenerReceiverStarted copy(final ReceiverInfo receiverInfo) {
      return new StreamingListenerReceiverStarted(receiverInfo);
   }

   public ReceiverInfo copy$default$1() {
      return this.receiverInfo();
   }

   public String productPrefix() {
      return "StreamingListenerReceiverStarted";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.receiverInfo();
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
      return x$1 instanceof StreamingListenerReceiverStarted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "receiverInfo";
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
            if (x$1 instanceof StreamingListenerReceiverStarted) {
               label40: {
                  StreamingListenerReceiverStarted var4 = (StreamingListenerReceiverStarted)x$1;
                  ReceiverInfo var10000 = this.receiverInfo();
                  ReceiverInfo var5 = var4.receiverInfo();
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

   public StreamingListenerReceiverStarted(final ReceiverInfo receiverInfo) {
      this.receiverInfo = receiverInfo;
      Product.$init$(this);
   }
}
