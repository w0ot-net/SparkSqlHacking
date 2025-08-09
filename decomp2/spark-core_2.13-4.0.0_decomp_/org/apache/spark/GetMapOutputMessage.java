package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.rpc.RpcCallContext;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc!B\r\u001b\u0001j\u0001\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011\u0001\u0003!\u0011#Q\u0001\nuB\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\u0007\")!\n\u0001C\u0001\u0017\"9q\nAA\u0001\n\u0003\u0001\u0006bB*\u0001#\u0003%\t\u0001\u0016\u0005\b?\u0002\t\n\u0011\"\u0001a\u0011\u001d\u0011\u0007!!A\u0005B\rDq\u0001\u001c\u0001\u0002\u0002\u0013\u0005A\bC\u0004n\u0001\u0005\u0005I\u0011\u00018\t\u000fQ\u0004\u0011\u0011!C!k\"9A\u0010AA\u0001\n\u0003i\b\"CA\u0003\u0001\u0005\u0005I\u0011IA\u0004\u0011%\tY\u0001AA\u0001\n\u0003\ni\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0011\u0002\u0012!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013QC\u0004\u000b\u00033Q\u0012\u0011!E\u00015\u0005ma!C\r\u001b\u0003\u0003E\tAGA\u000f\u0011\u0019Q5\u0003\"\u0001\u00026!I\u0011qB\n\u0002\u0002\u0013\u0015\u0013\u0011\u0003\u0005\n\u0003o\u0019\u0012\u0011!CA\u0003sA\u0011\"a\u0010\u0014\u0003\u0003%\t)!\u0011\t\u0013\u0005M3#!A\u0005\n\u0005U#aE$fi6\u000b\u0007oT;uaV$X*Z:tC\u001e,'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0014\u000b\u0001\tse\u000b\u0018\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\r\u0005s\u0017PU3g!\tA\u0013&D\u0001\u001b\u0013\tQ#DA\u000fNCB|U\u000f\u001e9viR\u0013\u0018mY6fe6\u000b7\u000f^3s\u001b\u0016\u001c8/Y4f!\t\u0011C&\u0003\u0002.G\t9\u0001K]8ek\u000e$\bCA\u00189\u001d\t\u0001dG\u0004\u00022k5\t!G\u0003\u00024i\u00051AH]8piz\u001a\u0001!C\u0001%\u0013\t94%A\u0004qC\u000e\\\u0017mZ3\n\u0005eR$\u0001D*fe&\fG.\u001b>bE2,'BA\u001c$\u0003%\u0019\b.\u001e4gY\u0016LE-F\u0001>!\t\u0011c(\u0003\u0002@G\t\u0019\u0011J\u001c;\u0002\u0015MDWO\u001a4mK&#\u0007%A\u0004d_:$X\r\u001f;\u0016\u0003\r\u0003\"\u0001R$\u000e\u0003\u0015S!A\u0012\u000e\u0002\u0007I\u00048-\u0003\u0002I\u000b\nq!\u000b]2DC2d7i\u001c8uKb$\u0018\u0001C2p]R,\u0007\u0010\u001e\u0011\u0002\rqJg.\u001b;?)\raUJ\u0014\t\u0003Q\u0001AQaO\u0003A\u0002uBQ!Q\u0003A\u0002\r\u000bAaY8qsR\u0019A*\u0015*\t\u000fm2\u0001\u0013!a\u0001{!9\u0011I\u0002I\u0001\u0002\u0004\u0019\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002+*\u0012QHV\u0016\u0002/B\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001X\u0012\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002_3\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t\u0011M\u000b\u0002D-\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\fA\u0001\\1oO*\t\u0011.\u0001\u0003kCZ\f\u0017BA6g\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA8s!\t\u0011\u0003/\u0003\u0002rG\t\u0019\u0011I\\=\t\u000fM\\\u0011\u0011!a\u0001{\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A\u001e\t\u0004oj|W\"\u0001=\u000b\u0005e\u001c\u0013AC2pY2,7\r^5p]&\u00111\u0010\u001f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002\u007f\u0003\u0007\u0001\"AI@\n\u0007\u0005\u00051EA\u0004C_>dW-\u00198\t\u000fMl\u0011\u0011!a\u0001_\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r!\u0017\u0011\u0002\u0005\bg:\t\t\u00111\u0001>\u0003!A\u0017m\u001d5D_\u0012,G#A\u001f\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001Z\u0001\u0007KF,\u0018\r\\:\u0015\u0007y\f9\u0002C\u0004t#\u0005\u0005\t\u0019A8\u0002'\u001d+G/T1q\u001fV$\b/\u001e;NKN\u001c\u0018mZ3\u0011\u0005!\u001a2#B\n\u0002 \u0005-\u0002cBA\u0011\u0003Oi4\tT\u0007\u0003\u0003GQ1!!\n$\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u000b\u0002$\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u00055\u00121G\u0007\u0003\u0003_Q1!!\ri\u0003\tIw.C\u0002:\u0003_!\"!a\u0007\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b1\u000bY$!\u0010\t\u000bm2\u0002\u0019A\u001f\t\u000b\u00053\u0002\u0019A\"\u0002\u000fUt\u0017\r\u001d9msR!\u00111IA(!\u0015\u0011\u0013QIA%\u0013\r\t9e\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\t\nY%P\"\n\u0007\u000553E\u0001\u0004UkBdWM\r\u0005\t\u0003#:\u0012\u0011!a\u0001\u0019\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0003cA3\u0002Z%\u0019\u00111\f4\u0003\r=\u0013'.Z2u\u0001"
)
public class GetMapOutputMessage implements MapOutputTrackerMasterMessage, Product, Serializable {
   private final int shuffleId;
   private final RpcCallContext context;

   public static Option unapply(final GetMapOutputMessage x$0) {
      return GetMapOutputMessage$.MODULE$.unapply(x$0);
   }

   public static GetMapOutputMessage apply(final int shuffleId, final RpcCallContext context) {
      return GetMapOutputMessage$.MODULE$.apply(shuffleId, context);
   }

   public static Function1 tupled() {
      return GetMapOutputMessage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return GetMapOutputMessage$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public RpcCallContext context() {
      return this.context;
   }

   public GetMapOutputMessage copy(final int shuffleId, final RpcCallContext context) {
      return new GetMapOutputMessage(shuffleId, context);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public RpcCallContext copy$default$2() {
      return this.context();
   }

   public String productPrefix() {
      return "GetMapOutputMessage";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 1 -> {
            return this.context();
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
      return x$1 instanceof GetMapOutputMessage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         case 1 -> {
            return "context";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.context()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof GetMapOutputMessage) {
               GetMapOutputMessage var4 = (GetMapOutputMessage)x$1;
               if (this.shuffleId() == var4.shuffleId()) {
                  label44: {
                     RpcCallContext var10000 = this.context();
                     RpcCallContext var5 = var4.context();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
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

   public GetMapOutputMessage(final int shuffleId, final RpcCallContext context) {
      this.shuffleId = shuffleId;
      this.context = context;
      Product.$init$(this);
   }
}
