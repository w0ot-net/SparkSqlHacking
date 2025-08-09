package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005\u0015d!B\r\u001b\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\t\u0003!\u0011#Q\u0001\n}B\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t!\u0002\u0011\t\u0012)A\u0005\u000b\")\u0011\u000b\u0001C\u0001%\"9a\u000bAA\u0001\n\u00039\u0006b\u0002.\u0001#\u0003%\ta\u0017\u0005\bM\u0002\t\n\u0011\"\u0001h\u0011\u001dI\u0007!!A\u0005B)DqA\u001d\u0001\u0002\u0002\u0013\u0005a\bC\u0004t\u0001\u0005\u0005I\u0011\u0001;\t\u000fi\u0004\u0011\u0011!C!w\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003#\u0001\u0011\u0011!C!\u0003'A\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u000f)\t)CGA\u0001\u0012\u0003Q\u0012q\u0005\u0004\n3i\t\t\u0011#\u0001\u001b\u0003SAa!U\n\u0005\u0002\u0005\u0005\u0003\"CA\u000e'\u0005\u0005IQIA\u000f\u0011%\t\u0019eEA\u0001\n\u0003\u000b)\u0005C\u0005\u0002LM\t\t\u0011\"!\u0002N!I\u00111L\n\u0002\u0002\u0013%\u0011Q\f\u0002\u000f'R\fw-Z\"b]\u000e,G\u000e\\3e\u0015\tYB$A\u0005tG\",G-\u001e7fe*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014xmE\u0003\u0001G%j\u0003\u0007\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003U-j\u0011AG\u0005\u0003Yi\u0011\u0011\u0003R!H'\u000eDW\rZ;mKJ,e/\u001a8u!\t!c&\u0003\u00020K\t9\u0001K]8ek\u000e$\bCA\u0019;\u001d\t\u0011\u0004H\u0004\u00024o5\tAG\u0003\u00026m\u00051AH]8piz\u001a\u0001!C\u0001'\u0013\tIT%A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d&\u0003\u001d\u0019H/Y4f\u0013\u0012,\u0012a\u0010\t\u0003I\u0001K!!Q\u0013\u0003\u0007%sG/\u0001\u0005ti\u0006<W-\u00133!\u0003\u0019\u0011X-Y:p]V\tQ\tE\u0002%\r\"K!aR\u0013\u0003\r=\u0003H/[8o!\tIUJ\u0004\u0002K\u0017B\u00111'J\u0005\u0003\u0019\u0016\na\u0001\u0015:fI\u00164\u0017B\u0001(P\u0005\u0019\u0019FO]5oO*\u0011A*J\u0001\be\u0016\f7o\u001c8!\u0003\u0019a\u0014N\\5u}Q\u00191\u000bV+\u0011\u0005)\u0002\u0001\"B\u001f\u0006\u0001\u0004y\u0004\"B\"\u0006\u0001\u0004)\u0015\u0001B2paf$2a\u0015-Z\u0011\u001did\u0001%AA\u0002}Bqa\u0011\u0004\u0011\u0002\u0003\u0007Q)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003qS#aP/,\u0003y\u0003\"a\u00183\u000e\u0003\u0001T!!\u00192\u0002\u0013Ut7\r[3dW\u0016$'BA2&\u0003)\tgN\\8uCRLwN\\\u0005\u0003K\u0002\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012\u0001\u001b\u0016\u0003\u000bv\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A6\u0011\u00051\fX\"A7\u000b\u00059|\u0017\u0001\u00027b]\u001eT\u0011\u0001]\u0001\u0005U\u00064\u0018-\u0003\u0002O[\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA;y!\t!c/\u0003\u0002xK\t\u0019\u0011I\\=\t\u000fe\\\u0011\u0011!a\u0001\u007f\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012\u0001 \t\u0005{\u0006\u0005Q/D\u0001\u007f\u0015\tyX%\u0001\u0006d_2dWm\u0019;j_:L1!a\u0001\u007f\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005%\u0011q\u0002\t\u0004I\u0005-\u0011bAA\u0007K\t9!i\\8mK\u0006t\u0007bB=\u000e\u0003\u0003\u0005\r!^\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002l\u0003+Aq!\u001f\b\u0002\u0002\u0003\u0007q(\u0001\u0005iCND7i\u001c3f)\u0005y\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003-\fa!Z9vC2\u001cH\u0003BA\u0005\u0003GAq!_\t\u0002\u0002\u0003\u0007Q/\u0001\bTi\u0006<WmQ1oG\u0016dG.\u001a3\u0011\u0005)\u001a2#B\n\u0002,\u0005]\u0002cBA\u0017\u0003gyTiU\u0007\u0003\u0003_Q1!!\r&\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u000e\u00020\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005e\u0012qH\u0007\u0003\u0003wQ1!!\u0010p\u0003\tIw.C\u0002<\u0003w!\"!a\n\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000bM\u000b9%!\u0013\t\u000bu2\u0002\u0019A \t\u000b\r3\u0002\u0019A#\u0002\u000fUt\u0017\r\u001d9msR!\u0011qJA,!\u0011!c)!\u0015\u0011\u000b\u0011\n\u0019fP#\n\u0007\u0005USE\u0001\u0004UkBdWM\r\u0005\t\u00033:\u0012\u0011!a\u0001'\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0003c\u00017\u0002b%\u0019\u00111M7\u0003\r=\u0013'.Z2u\u0001"
)
public class StageCancelled implements DAGSchedulerEvent, Product, Serializable {
   private final int stageId;
   private final Option reason;

   public static Option unapply(final StageCancelled x$0) {
      return StageCancelled$.MODULE$.unapply(x$0);
   }

   public static StageCancelled apply(final int stageId, final Option reason) {
      return StageCancelled$.MODULE$.apply(stageId, reason);
   }

   public static Function1 tupled() {
      return StageCancelled$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return StageCancelled$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int stageId() {
      return this.stageId;
   }

   public Option reason() {
      return this.reason;
   }

   public StageCancelled copy(final int stageId, final Option reason) {
      return new StageCancelled(stageId, reason);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public Option copy$default$2() {
      return this.reason();
   }

   public String productPrefix() {
      return "StageCancelled";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 1 -> {
            return this.reason();
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
      return x$1 instanceof StageCancelled;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stageId";
         }
         case 1 -> {
            return "reason";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, Statics.anyHash(this.reason()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof StageCancelled) {
               StageCancelled var4 = (StageCancelled)x$1;
               if (this.stageId() == var4.stageId()) {
                  label44: {
                     Option var10000 = this.reason();
                     Option var5 = var4.reason();
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

   public StageCancelled(final int stageId, final Option reason) {
      this.stageId = stageId;
      this.reason = reason;
      Product.$init$(this);
   }
}
