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
   bytes = "\u0006\u0005\u0005uc!B\r\u001b\u0001j\u0001\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011\u0001\u0003!\u0011#Q\u0001\nuB\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\u0007\")!\n\u0001C\u0001\u0017\"9q\nAA\u0001\n\u0003\u0001\u0006bB*\u0001#\u0003%\t\u0001\u0016\u0005\b?\u0002\t\n\u0011\"\u0001a\u0011\u001d\u0011\u0007!!A\u0005B\rDq\u0001\u001c\u0001\u0002\u0002\u0013\u0005A\bC\u0004n\u0001\u0005\u0005I\u0011\u00018\t\u000fQ\u0004\u0011\u0011!C!k\"9A\u0010AA\u0001\n\u0003i\b\"CA\u0003\u0001\u0005\u0005I\u0011IA\u0004\u0011%\tY\u0001AA\u0001\n\u0003\ni\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0011\u0002\u0012!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013QC\u0004\u000b\u00033Q\u0012\u0011!E\u00015\u0005ma!C\r\u001b\u0003\u0003E\tAGA\u000f\u0011\u0019Q5\u0003\"\u0001\u00026!I\u0011qB\n\u0002\u0002\u0013\u0015\u0013\u0011\u0003\u0005\n\u0003o\u0019\u0012\u0011!CA\u0003sA\u0011\"a\u0010\u0014\u0003\u0003%\t)!\u0011\t\u0013\u0005M3#!A\u0005\n\u0005U#\u0001H$fiNCWO\u001a4mKB+8\u000f['fe\u001e,'o]'fgN\fw-\u001a\u0006\u00037q\tQa\u001d9be.T!!\b\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0012aA8sON)\u0001!I\u0014,]A\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1\u0011I\\=SK\u001a\u0004\"\u0001K\u0015\u000e\u0003iI!A\u000b\u000e\u0003;5\u000b\u0007oT;uaV$HK]1dW\u0016\u0014X*Y:uKJlUm]:bO\u0016\u0004\"A\t\u0017\n\u00055\u001a#a\u0002)s_\u0012,8\r\u001e\t\u0003_ar!\u0001\r\u001c\u000f\u0005E*T\"\u0001\u001a\u000b\u0005M\"\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0011J!aN\u0012\u0002\u000fA\f7m[1hK&\u0011\u0011H\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003o\r\n\u0011b\u001d5vM\u001adW-\u00133\u0016\u0003u\u0002\"A\t \n\u0005}\u001a#aA%oi\u0006Q1\u000f[;gM2,\u0017\n\u001a\u0011\u0002\u000f\r|g\u000e^3yiV\t1\t\u0005\u0002E\u000f6\tQI\u0003\u0002G5\u0005\u0019!\u000f]2\n\u0005!+%A\u0004*qG\u000e\u000bG\u000e\\\"p]R,\u0007\u0010^\u0001\tG>tG/\u001a=uA\u00051A(\u001b8jiz\"2\u0001T'O!\tA\u0003\u0001C\u0003<\u000b\u0001\u0007Q\bC\u0003B\u000b\u0001\u00071)\u0001\u0003d_BLHc\u0001'R%\"91H\u0002I\u0001\u0002\u0004i\u0004bB!\u0007!\u0003\u0005\raQ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005)&FA\u001fWW\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003%)hn\u00195fG.,GM\u0003\u0002]G\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005yK&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A1+\u0005\r3\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001e!\t)'.D\u0001g\u0015\t9\u0007.\u0001\u0003mC:<'\"A5\u0002\t)\fg/Y\u0005\u0003W\u001a\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003_J\u0004\"A\t9\n\u0005E\u001c#aA!os\"91oCA\u0001\u0002\u0004i\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001w!\r9(p\\\u0007\u0002q*\u0011\u0011pI\u0001\u000bG>dG.Z2uS>t\u0017BA>y\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007y\f\u0019\u0001\u0005\u0002#\u007f&\u0019\u0011\u0011A\u0012\u0003\u000f\t{w\u000e\\3b]\"91/DA\u0001\u0002\u0004y\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001ZA\u0005\u0011\u001d\u0019h\"!AA\u0002u\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002{\u0005AAo\\*ue&tw\rF\u0001e\u0003\u0019)\u0017/^1mgR\u0019a0a\u0006\t\u000fM\f\u0012\u0011!a\u0001_\u0006ar)\u001a;TQV4g\r\\3QkNDW*\u001a:hKJ\u001cX*Z:tC\u001e,\u0007C\u0001\u0015\u0014'\u0015\u0019\u0012qDA\u0016!\u001d\t\t#a\n>\u00072k!!a\t\u000b\u0007\u0005\u00152%A\u0004sk:$\u0018.\\3\n\t\u0005%\u00121\u0005\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u0017\u0003gi!!a\f\u000b\u0007\u0005E\u0002.\u0001\u0002j_&\u0019\u0011(a\f\u0015\u0005\u0005m\u0011!B1qa2LH#\u0002'\u0002<\u0005u\u0002\"B\u001e\u0017\u0001\u0004i\u0004\"B!\u0017\u0001\u0004\u0019\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0007\ny\u0005E\u0003#\u0003\u000b\nI%C\u0002\u0002H\r\u0012aa\u00149uS>t\u0007#\u0002\u0012\u0002Lu\u001a\u0015bAA'G\t1A+\u001e9mKJB\u0001\"!\u0015\u0018\u0003\u0003\u0005\r\u0001T\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA,!\r)\u0017\u0011L\u0005\u0004\u000372'AB(cU\u0016\u001cG\u000f"
)
public class GetShufflePushMergersMessage implements MapOutputTrackerMasterMessage, Product, Serializable {
   private final int shuffleId;
   private final RpcCallContext context;

   public static Option unapply(final GetShufflePushMergersMessage x$0) {
      return GetShufflePushMergersMessage$.MODULE$.unapply(x$0);
   }

   public static GetShufflePushMergersMessage apply(final int shuffleId, final RpcCallContext context) {
      return GetShufflePushMergersMessage$.MODULE$.apply(shuffleId, context);
   }

   public static Function1 tupled() {
      return GetShufflePushMergersMessage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return GetShufflePushMergersMessage$.MODULE$.curried();
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

   public GetShufflePushMergersMessage copy(final int shuffleId, final RpcCallContext context) {
      return new GetShufflePushMergersMessage(shuffleId, context);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public RpcCallContext copy$default$2() {
      return this.context();
   }

   public String productPrefix() {
      return "GetShufflePushMergersMessage";
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
      return x$1 instanceof GetShufflePushMergersMessage;
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
            if (x$1 instanceof GetShufflePushMergersMessage) {
               GetShufflePushMergersMessage var4 = (GetShufflePushMergersMessage)x$1;
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

   public GetShufflePushMergersMessage(final int shuffleId, final RpcCallContext context) {
      this.shuffleId = shuffleId;
      this.context = context;
      Product.$init$(this);
   }
}
