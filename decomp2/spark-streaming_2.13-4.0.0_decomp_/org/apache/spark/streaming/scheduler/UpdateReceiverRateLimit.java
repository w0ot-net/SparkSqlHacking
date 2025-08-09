package org.apache.spark.streaming.scheduler;

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
   bytes = "\u0006\u0005\u0005}c!B\r\u001b\u0001r!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u0011\u0003!\u0011#Q\u0001\n\u0005C\u0001\"\u0012\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u000f\")1\n\u0001C\u0001\u0019\"9\u0001\u000bAA\u0001\n\u0003\t\u0006b\u0002+\u0001#\u0003%\t!\u0016\u0005\bA\u0002\t\n\u0011\"\u0001b\u0011\u001d\u0019\u0007!!A\u0005B\u0011Dq!\u001c\u0001\u0002\u0002\u0013\u0005\u0001\tC\u0004o\u0001\u0005\u0005I\u0011A8\t\u000fU\u0004\u0011\u0011!C!m\"9Q\u0010AA\u0001\n\u0003q\b\"CA\u0004\u0001\u0005\u0005I\u0011IA\u0005\u0011%\ti\u0001AA\u0001\n\u0003\ny\u0001C\u0005\u0002\u0012\u0001\t\t\u0011\"\u0011\u0002\u0014!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013qC\u0004\u000b\u00037Q\u0012\u0011!E\u00019\u0005ua!C\r\u001b\u0003\u0003E\t\u0001HA\u0010\u0011\u0019Y5\u0003\"\u0001\u00028!I\u0011\u0011C\n\u0002\u0002\u0013\u0015\u00131\u0003\u0005\n\u0003s\u0019\u0012\u0011!CA\u0003wA\u0011\"!\u0011\u0014\u0003\u0003%\t)a\u0011\t\u0013\u0005U3#!A\u0005\n\u0005]#aF+qI\u0006$XMU3dK&4XM\u001d*bi\u0016d\u0015.\\5u\u0015\tYB$A\u0005tG\",G-\u001e7fe*\u0011QDH\u0001\ngR\u0014X-Y7j]\u001eT!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\n\u0006\u0001\u0015ZsF\r\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00051jS\"\u0001\u000e\n\u00059R\"a\u0007*fG\u0016Lg/\u001a:Ue\u0006\u001c7.\u001a:M_\u000e\fG.T3tg\u0006<W\r\u0005\u0002'a%\u0011\u0011g\n\u0002\b!J|G-^2u!\t\u0019DH\u0004\u00025u9\u0011Q'O\u0007\u0002m)\u0011q\u0007O\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001&\u0003\u0002<O\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tYt%A\u0005tiJ,\u0017-\\+J\tV\t\u0011\t\u0005\u0002'\u0005&\u00111i\n\u0002\u0004\u0013:$\u0018AC:ue\u0016\fW.V%EA\u00059a.Z<SCR,W#A$\u0011\u0005\u0019B\u0015BA%(\u0005\u0011auN\\4\u0002\u00119,wOU1uK\u0002\na\u0001P5oSRtDcA'O\u001fB\u0011A\u0006\u0001\u0005\u0006\u007f\u0015\u0001\r!\u0011\u0005\u0006\u000b\u0016\u0001\raR\u0001\u0005G>\u0004\u0018\u0010F\u0002N%NCqa\u0010\u0004\u0011\u0002\u0003\u0007\u0011\tC\u0004F\rA\u0005\t\u0019A$\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\taK\u000b\u0002B/.\n\u0001\f\u0005\u0002Z=6\t!L\u0003\u0002\\9\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003;\u001e\n!\"\u00198o_R\fG/[8o\u0013\ty&LA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001cU\t9u+A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002KB\u0011am[\u0007\u0002O*\u0011\u0001.[\u0001\u0005Y\u0006twMC\u0001k\u0003\u0011Q\u0017M^1\n\u00051<'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005A\u001c\bC\u0001\u0014r\u0013\t\u0011xEA\u0002B]fDq\u0001^\u0006\u0002\u0002\u0003\u0007\u0011)A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002oB\u0019\u0001p\u001f9\u000e\u0003eT!A_\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002}s\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\ry\u0018Q\u0001\t\u0004M\u0005\u0005\u0011bAA\u0002O\t9!i\\8mK\u0006t\u0007b\u0002;\u000e\u0003\u0003\u0005\r\u0001]\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002f\u0003\u0017Aq\u0001\u001e\b\u0002\u0002\u0003\u0007\u0011)\u0001\u0005iCND7i\u001c3f)\u0005\t\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0015\fa!Z9vC2\u001cHcA@\u0002\u001a!9A/EA\u0001\u0002\u0004\u0001\u0018aF+qI\u0006$XMU3dK&4XM\u001d*bi\u0016d\u0015.\\5u!\ta3cE\u0003\u0014\u0003C\ti\u0003E\u0004\u0002$\u0005%\u0012iR'\u000e\u0005\u0005\u0015\"bAA\u0014O\u00059!/\u001e8uS6,\u0017\u0002BA\u0016\u0003K\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\ty#!\u000e\u000e\u0005\u0005E\"bAA\u001aS\u0006\u0011\u0011n\\\u0005\u0004{\u0005EBCAA\u000f\u0003\u0015\t\u0007\u000f\u001d7z)\u0015i\u0015QHA \u0011\u0015yd\u00031\u0001B\u0011\u0015)e\u00031\u0001H\u0003\u001d)h.\u00199qYf$B!!\u0012\u0002RA)a%a\u0012\u0002L%\u0019\u0011\u0011J\u0014\u0003\r=\u0003H/[8o!\u00151\u0013QJ!H\u0013\r\tye\n\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005Ms#!AA\u00025\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0006E\u0002g\u00037J1!!\u0018h\u0005\u0019y%M[3di\u0002"
)
public class UpdateReceiverRateLimit implements ReceiverTrackerLocalMessage, Product, Serializable {
   private final int streamUID;
   private final long newRate;

   public static Option unapply(final UpdateReceiverRateLimit x$0) {
      return UpdateReceiverRateLimit$.MODULE$.unapply(x$0);
   }

   public static UpdateReceiverRateLimit apply(final int streamUID, final long newRate) {
      return UpdateReceiverRateLimit$.MODULE$.apply(streamUID, newRate);
   }

   public static Function1 tupled() {
      return UpdateReceiverRateLimit$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UpdateReceiverRateLimit$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamUID() {
      return this.streamUID;
   }

   public long newRate() {
      return this.newRate;
   }

   public UpdateReceiverRateLimit copy(final int streamUID, final long newRate) {
      return new UpdateReceiverRateLimit(streamUID, newRate);
   }

   public int copy$default$1() {
      return this.streamUID();
   }

   public long copy$default$2() {
      return this.newRate();
   }

   public String productPrefix() {
      return "UpdateReceiverRateLimit";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamUID());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.newRate());
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
      return x$1 instanceof UpdateReceiverRateLimit;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamUID";
         }
         case 1 -> {
            return "newRate";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.streamUID());
      var1 = Statics.mix(var1, Statics.longHash(this.newRate()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof UpdateReceiverRateLimit) {
               UpdateReceiverRateLimit var4 = (UpdateReceiverRateLimit)x$1;
               if (this.streamUID() == var4.streamUID() && this.newRate() == var4.newRate() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public UpdateReceiverRateLimit(final int streamUID, final long newRate) {
      this.streamUID = streamUID;
      this.newRate = newRate;
      Product.$init$(this);
   }
}
