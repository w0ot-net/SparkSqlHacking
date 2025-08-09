package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f!\u0002\u0012$\u0001\u0016j\u0003\u0002\u0003%\u0001\u0005+\u0007I\u0011A%\t\u00115\u0003!\u0011#Q\u0001\n)C\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t1\u0002\u0011\t\u0012)A\u0005!\"A\u0011\f\u0001BK\u0002\u0013\u0005q\n\u0003\u0005[\u0001\tE\t\u0015!\u0003Q\u0011!Y\u0006A!f\u0001\n\u0003y\u0005\u0002\u0003/\u0001\u0005#\u0005\u000b\u0011\u0002)\t\u0011u\u0003!Q3A\u0005\u0002yC\u0001\"\u001a\u0001\u0003\u0012\u0003\u0006Ia\u0018\u0005\u0006M\u0002!\ta\u001a\u0005\b]\u0002\t\t\u0011\"\u0001p\u0011\u001d)\b!%A\u0005\u0002YD\u0011\"a\u0001\u0001#\u0003%\t!!\u0002\t\u0013\u0005%\u0001!%A\u0005\u0002\u0005\u0015\u0001\"CA\u0006\u0001E\u0005I\u0011AA\u0003\u0011%\ti\u0001AI\u0001\n\u0003\ty\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!A\u0011Q\u0005\u0001\u0002\u0002\u0013\u0005\u0011\nC\u0005\u0002(\u0001\t\t\u0011\"\u0001\u0002*!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0013q\u0007\u0005\n\u0003\u000b\u0002\u0011\u0011!C\u0001\u0003\u000fB\u0011\"!\u0015\u0001\u0003\u0003%\t%a\u0015\t\u0013\u0005]\u0003!!A\u0005B\u0005e\u0003\"CA.\u0001\u0005\u0005I\u0011IA/\u0011%\ty\u0006AA\u0001\n\u0003\n\tg\u0002\u0006\u0002f\r\n\t\u0011#\u0001&\u0003O2\u0011BI\u0012\u0002\u0002#\u0005Q%!\u001b\t\r\u0019dB\u0011AAA\u0011%\tY\u0006HA\u0001\n\u000b\ni\u0006C\u0005\u0002\u0004r\t\t\u0011\"!\u0002\u0006\"I\u0011\u0011\u0013\u000f\u0002\u0002\u0013\u0005\u00151\u0013\u0005\n\u0003Kc\u0012\u0011!C\u0005\u0003O\u0013\u0001CU3hSN$XM\u001d*fG\u0016Lg/\u001a:\u000b\u0005\u0011*\u0013!C:dQ\u0016$W\u000f\\3s\u0015\t1s%A\u0005tiJ,\u0017-\\5oO*\u0011\u0001&K\u0001\u0006gB\f'o\u001b\u0006\u0003U-\na!\u00199bG\",'\"\u0001\u0017\u0002\u0007=\u0014xmE\u0003\u0001]QB4\b\u0005\u00020e5\t\u0001GC\u00012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0004G\u0001\u0004B]f\u0014VM\u001a\t\u0003kYj\u0011aI\u0005\u0003o\r\u0012aCU3dK&4XM\u001d+sC\u000e\\WM]'fgN\fw-\u001a\t\u0003_eJ!A\u000f\u0019\u0003\u000fA\u0013x\u000eZ;diB\u0011A(\u0012\b\u0003{\rs!A\u0010\"\u000e\u0003}R!\u0001Q!\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!M\u0005\u0003\tB\nq\u0001]1dW\u0006<W-\u0003\u0002G\u000f\na1+\u001a:jC2L'0\u00192mK*\u0011A\tM\u0001\tgR\u0014X-Y7JIV\t!\n\u0005\u00020\u0017&\u0011A\n\r\u0002\u0004\u0013:$\u0018!C:ue\u0016\fW.\u00133!\u0003\r!\u0018\u0010]\u000b\u0002!B\u0011\u0011+\u0016\b\u0003%N\u0003\"A\u0010\u0019\n\u0005Q\u0003\u0014A\u0002)sK\u0012,g-\u0003\u0002W/\n11\u000b\u001e:j]\u001eT!\u0001\u0016\u0019\u0002\tQL\b\u000fI\u0001\u0005Q>\u001cH/A\u0003i_N$\b%\u0001\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\f1\"\u001a=fGV$xN]%eA\u0005\u0001\"/Z2fSZ,'/\u00128ea>Lg\u000e^\u000b\u0002?B\u0011\u0001mY\u0007\u0002C*\u0011!mJ\u0001\u0004eB\u001c\u0017B\u00013b\u00059\u0011\u0006oY#oIB|\u0017N\u001c;SK\u001a\f\u0011C]3dK&4XM]#oIB|\u0017N\u001c;!\u0003\u0019a\u0014N\\5u}Q1\u0001.\u001b6lY6\u0004\"!\u000e\u0001\t\u000b![\u0001\u0019\u0001&\t\u000b9[\u0001\u0019\u0001)\t\u000be[\u0001\u0019\u0001)\t\u000bm[\u0001\u0019\u0001)\t\u000bu[\u0001\u0019A0\u0002\t\r|\u0007/\u001f\u000b\u0007QB\f(o\u001d;\t\u000f!c\u0001\u0013!a\u0001\u0015\"9a\n\u0004I\u0001\u0002\u0004\u0001\u0006bB-\r!\u0003\u0005\r\u0001\u0015\u0005\b72\u0001\n\u00111\u0001Q\u0011\u001diF\u0002%AA\u0002}\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001xU\tQ\u0005pK\u0001z!\tQx0D\u0001|\u0015\taX0A\u0005v]\u000eDWmY6fI*\u0011a\u0010M\u0001\u000bC:tw\u000e^1uS>t\u0017bAA\u0001w\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u0001\u0016\u0003!b\fabY8qs\u0012\"WMZ1vYR$3'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u0011\u0011\u0003\u0016\u0003?b\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\f!\u0011\tI\"a\t\u000e\u0005\u0005m!\u0002BA\u000f\u0003?\tA\u0001\\1oO*\u0011\u0011\u0011E\u0001\u0005U\u00064\u0018-C\u0002W\u00037\tA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002,\u0005E\u0002cA\u0018\u0002.%\u0019\u0011q\u0006\u0019\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u00024Q\t\t\u00111\u0001K\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\b\t\u0007\u0003w\t\t%a\u000b\u000e\u0005\u0005u\"bAA a\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\r\u0013Q\b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002J\u0005=\u0003cA\u0018\u0002L%\u0019\u0011Q\n\u0019\u0003\u000f\t{w\u000e\\3b]\"I\u00111\u0007\f\u0002\u0002\u0003\u0007\u00111F\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\u0018\u0005U\u0003\u0002CA\u001a/\u0005\u0005\t\u0019\u0001&\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AS\u0001\ti>\u001cFO]5oOR\u0011\u0011qC\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005%\u00131\r\u0005\n\u0003gQ\u0012\u0011!a\u0001\u0003W\t\u0001CU3hSN$XM\u001d*fG\u0016Lg/\u001a:\u0011\u0005Ub2#\u0002\u000f\u0002l\u0005]\u0004CCA7\u0003gR\u0005\u000b\u0015)`Q6\u0011\u0011q\u000e\u0006\u0004\u0003c\u0002\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003k\nyGA\tBEN$(/Y2u\rVt7\r^5p]V\u0002B!!\u001f\u0002\u00005\u0011\u00111\u0010\u0006\u0005\u0003{\ny\"\u0001\u0002j_&\u0019a)a\u001f\u0015\u0005\u0005\u001d\u0014!B1qa2LHc\u00035\u0002\b\u0006%\u00151RAG\u0003\u001fCQ\u0001S\u0010A\u0002)CQAT\u0010A\u0002ACQ!W\u0010A\u0002ACQaW\u0010A\u0002ACQ!X\u0010A\u0002}\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0016\u0006\u0005\u0006#B\u0018\u0002\u0018\u0006m\u0015bAAMa\t1q\n\u001d;j_:\u0004\u0002bLAO\u0015B\u0003\u0006kX\u0005\u0004\u0003?\u0003$A\u0002+va2,W\u0007\u0003\u0005\u0002$\u0002\n\t\u00111\u0001i\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003S\u0003B!!\u0007\u0002,&!\u0011QVA\u000e\u0005\u0019y%M[3di\u0002"
)
public class RegisterReceiver implements ReceiverTrackerMessage, Product, Serializable {
   private final int streamId;
   private final String typ;
   private final String host;
   private final String executorId;
   private final RpcEndpointRef receiverEndpoint;

   public static Option unapply(final RegisterReceiver x$0) {
      return RegisterReceiver$.MODULE$.unapply(x$0);
   }

   public static RegisterReceiver apply(final int streamId, final String typ, final String host, final String executorId, final RpcEndpointRef receiverEndpoint) {
      return RegisterReceiver$.MODULE$.apply(streamId, typ, host, executorId, receiverEndpoint);
   }

   public static Function1 tupled() {
      return RegisterReceiver$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RegisterReceiver$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamId() {
      return this.streamId;
   }

   public String typ() {
      return this.typ;
   }

   public String host() {
      return this.host;
   }

   public String executorId() {
      return this.executorId;
   }

   public RpcEndpointRef receiverEndpoint() {
      return this.receiverEndpoint;
   }

   public RegisterReceiver copy(final int streamId, final String typ, final String host, final String executorId, final RpcEndpointRef receiverEndpoint) {
      return new RegisterReceiver(streamId, typ, host, executorId, receiverEndpoint);
   }

   public int copy$default$1() {
      return this.streamId();
   }

   public String copy$default$2() {
      return this.typ();
   }

   public String copy$default$3() {
      return this.host();
   }

   public String copy$default$4() {
      return this.executorId();
   }

   public RpcEndpointRef copy$default$5() {
      return this.receiverEndpoint();
   }

   public String productPrefix() {
      return "RegisterReceiver";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamId());
         }
         case 1 -> {
            return this.typ();
         }
         case 2 -> {
            return this.host();
         }
         case 3 -> {
            return this.executorId();
         }
         case 4 -> {
            return this.receiverEndpoint();
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
      return x$1 instanceof RegisterReceiver;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamId";
         }
         case 1 -> {
            return "typ";
         }
         case 2 -> {
            return "host";
         }
         case 3 -> {
            return "executorId";
         }
         case 4 -> {
            return "receiverEndpoint";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.streamId());
      var1 = Statics.mix(var1, Statics.anyHash(this.typ()));
      var1 = Statics.mix(var1, Statics.anyHash(this.host()));
      var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.receiverEndpoint()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label75: {
            if (x$1 instanceof RegisterReceiver) {
               RegisterReceiver var4 = (RegisterReceiver)x$1;
               if (this.streamId() == var4.streamId()) {
                  label68: {
                     String var10000 = this.typ();
                     String var5 = var4.typ();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label68;
                     }

                     var10000 = this.host();
                     String var6 = var4.host();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label68;
                     }

                     var10000 = this.executorId();
                     String var7 = var4.executorId();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label68;
                     }

                     RpcEndpointRef var11 = this.receiverEndpoint();
                     RpcEndpointRef var8 = var4.receiverEndpoint();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label68;
                        }
                     } else if (!var11.equals(var8)) {
                        break label68;
                     }

                     if (var4.canEqual(this)) {
                        break label75;
                     }
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public RegisterReceiver(final int streamId, final String typ, final String host, final String executorId, final RpcEndpointRef receiverEndpoint) {
      this.streamId = streamId;
      this.typ = typ;
      this.host = host;
      this.executorId = executorId;
      this.receiverEndpoint = receiverEndpoint;
      Product.$init$(this);
   }
}
