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
   bytes = "\u0006\u0005\u0005=c\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0005\u0002\u0014]\t\t\u0011#\u0001\u0002\u0016\u0019AacFA\u0001\u0012\u0003\t9\u0002\u0003\u0004C!\u0011\u0005\u0011q\u0006\u0005\b}B\t\t\u0011\"\u0012\u0000\u0011%\t\t\u0004EA\u0001\n\u0003\u000b\u0019\u0004C\u0005\u00028A\t\t\u0011\"!\u0002:!I\u0011Q\t\t\u0002\u0002\u0013%\u0011q\t\u0002\u001f'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014(+Z2fSZ,'/\u0012:s_JT!\u0001G\r\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\u000e\u001c\u0003%\u0019HO]3b[&twM\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h\u0007\u0001\u0019R\u0001A\u0012*[A\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0007C\u0001\u0016,\u001b\u00059\u0012B\u0001\u0017\u0018\u0005Y\u0019FO]3b[&tw\rT5ti\u0016tWM]#wK:$\bC\u0001\u0013/\u0013\tySEA\u0004Qe>$Wo\u0019;\u0011\u0005EJdB\u0001\u001a8\u001d\t\u0019d'D\u00015\u0015\t)\u0014%\u0001\u0004=e>|GOP\u0005\u0002M%\u0011\u0001(J\u0001\ba\u0006\u001c7.Y4f\u0013\tQ4H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00029K\u0005a!/Z2fSZ,'/\u00138g_V\ta\b\u0005\u0002+\u007f%\u0011\u0001i\u0006\u0002\r%\u0016\u001cW-\u001b<fe&sgm\\\u0001\u000ee\u0016\u001cW-\u001b<fe&sgm\u001c\u0011\u0002\rqJg.\u001b;?)\t!U\t\u0005\u0002+\u0001!)Ah\u0001a\u0001}\u0005!1m\u001c9z)\t!\u0005\nC\u0004=\tA\u0005\t\u0019\u0001 \u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t1J\u000b\u0002?\u0019.\nQ\n\u0005\u0002O'6\tqJ\u0003\u0002Q#\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003%\u0016\n!\"\u00198o_R\fG/[8o\u0013\t!vJA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A,\u0011\u0005akV\"A-\u000b\u0005i[\u0016\u0001\u00027b]\u001eT\u0011\u0001X\u0001\u0005U\u00064\u0018-\u0003\u0002_3\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u0019\t\u0003I\tL!aY\u0013\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\u0019L\u0007C\u0001\u0013h\u0013\tAWEA\u0002B]fDqA\u001b\u0005\u0002\u0002\u0003\u0007\u0011-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002[B\u0019a.\u001d4\u000e\u0003=T!\u0001]\u0013\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002s_\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t)\b\u0010\u0005\u0002%m&\u0011q/\n\u0002\b\u0005>|G.Z1o\u0011\u001dQ'\"!AA\u0002\u0019\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011qk\u001f\u0005\bU.\t\t\u00111\u0001b\u0003!A\u0017m\u001d5D_\u0012,G#A1\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aV\u0001\u0007KF,\u0018\r\\:\u0015\u0007U\f)\u0001C\u0004k\u001d\u0005\u0005\t\u0019\u00014)\u0007\u0001\tI\u0001\u0005\u0003\u0002\f\u0005=QBAA\u0007\u0015\t\u00116$\u0003\u0003\u0002\u0012\u00055!\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017AH*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8feJ+7-Z5wKJ,%O]8s!\tQ\u0003cE\u0003\u0011\u00033\t)\u0003\u0005\u0004\u0002\u001c\u0005\u0005b\bR\u0007\u0003\u0003;Q1!a\b&\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\t\u0002\u001e\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005\u001d\u0012QF\u0007\u0003\u0003SQ1!a\u000b\\\u0003\tIw.C\u0002;\u0003S!\"!!\u0006\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0011\u000b)\u0004C\u0003='\u0001\u0007a(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005m\u0012\u0011\t\t\u0005I\u0005ub(C\u0002\u0002@\u0015\u0012aa\u00149uS>t\u0007\u0002CA\")\u0005\u0005\t\u0019\u0001#\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002JA\u0019\u0001,a\u0013\n\u0007\u00055\u0013L\u0001\u0004PE*,7\r\u001e"
)
public class StreamingListenerReceiverError implements StreamingListenerEvent, Product, Serializable {
   private final ReceiverInfo receiverInfo;

   public static Option unapply(final StreamingListenerReceiverError x$0) {
      return StreamingListenerReceiverError$.MODULE$.unapply(x$0);
   }

   public static StreamingListenerReceiverError apply(final ReceiverInfo receiverInfo) {
      return StreamingListenerReceiverError$.MODULE$.apply(receiverInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return StreamingListenerReceiverError$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StreamingListenerReceiverError$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ReceiverInfo receiverInfo() {
      return this.receiverInfo;
   }

   public StreamingListenerReceiverError copy(final ReceiverInfo receiverInfo) {
      return new StreamingListenerReceiverError(receiverInfo);
   }

   public ReceiverInfo copy$default$1() {
      return this.receiverInfo();
   }

   public String productPrefix() {
      return "StreamingListenerReceiverError";
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
      return x$1 instanceof StreamingListenerReceiverError;
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
            if (x$1 instanceof StreamingListenerReceiverError) {
               label40: {
                  StreamingListenerReceiverError var4 = (StreamingListenerReceiverError)x$1;
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

   public StreamingListenerReceiverError(final ReceiverInfo receiverInfo) {
      this.receiverInfo = receiverInfo;
      Product.$init$(this);
   }
}
