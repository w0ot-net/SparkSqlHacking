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
   bytes = "\u0006\u0005\u0005Md!\u0002\u000f\u001e\u0001~9\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011\r\u0003!\u0011#Q\u0001\n\u0001C\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\t!\u0012\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\r\"Aq\n\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003G\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u001dA\u0006!!A\u0005\u0002eCq!\u0018\u0001\u0012\u0002\u0013\u0005a\fC\u0004j\u0001E\u0005I\u0011\u00016\t\u000f1\u0004\u0011\u0013!C\u0001U\"9Q\u000eAA\u0001\n\u0003r\u0007b\u0002<\u0001\u0003\u0003%\ta\u0010\u0005\bo\u0002\t\t\u0011\"\u0001y\u0011\u001dq\b!!A\u0005B}D\u0011\"!\u0004\u0001\u0003\u0003%\t!a\u0004\t\u0013\u0005e\u0001!!A\u0005B\u0005m\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u0011%\t\u0019\u0003AA\u0001\n\u0003\n)\u0003C\u0005\u0002(\u0001\t\t\u0011\"\u0011\u0002*\u001dQ\u0011QF\u000f\u0002\u0002#\u0005q$a\f\u0007\u0013qi\u0012\u0011!E\u0001?\u0005E\u0002BB)\u0017\t\u0003\tI\u0005C\u0005\u0002$Y\t\t\u0011\"\u0012\u0002&!I\u00111\n\f\u0002\u0002\u0013\u0005\u0015Q\n\u0005\n\u0003+2\u0012\u0011!CA\u0003/B\u0011\"!\u001b\u0017\u0003\u0003%I!a\u001b\u0003\u0017I+\u0007o\u001c:u\u000bJ\u0014xN\u001d\u0006\u0003=}\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u0001\n\u0013!C:ue\u0016\fW.\u001b8h\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7\u0003\u0002\u0001)]E\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007CA\u00150\u0013\t\u0001$FA\u0004Qe>$Wo\u0019;\u0011\u0005IZdBA\u001a:\u001d\t!\u0004(D\u00016\u0015\t1t'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0013B\u0001\u001e+\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005iR\u0013\u0001C:ue\u0016\fW.\u00133\u0016\u0003\u0001\u0003\"!K!\n\u0005\tS#aA%oi\u0006I1\u000f\u001e:fC6LE\rI\u0001\b[\u0016\u001c8/Y4f+\u00051\u0005CA$L\u001d\tA\u0015\n\u0005\u00025U%\u0011!JK\u0001\u0007!J,G-\u001a4\n\u00051k%AB*ue&twM\u0003\u0002KU\u0005AQ.Z:tC\u001e,\u0007%A\u0003feJ|'/\u0001\u0004feJ|'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tM+fk\u0016\t\u0003)\u0002i\u0011!\b\u0005\u0006}\u001d\u0001\r\u0001\u0011\u0005\u0006\t\u001e\u0001\rA\u0012\u0005\u0006\u001f\u001e\u0001\rAR\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003T5nc\u0006b\u0002 \t!\u0003\u0005\r\u0001\u0011\u0005\b\t\"\u0001\n\u00111\u0001G\u0011\u001dy\u0005\u0002%AA\u0002\u0019\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001`U\t\u0001\u0005mK\u0001b!\t\u0011w-D\u0001d\u0015\t!W-A\u0005v]\u000eDWmY6fI*\u0011aMK\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00015d\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005Y'F\u0001$a\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A8\u0011\u0005A,X\"A9\u000b\u0005I\u001c\u0018\u0001\u00027b]\u001eT\u0011\u0001^\u0001\u0005U\u00064\u0018-\u0003\u0002Mc\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA=}!\tI#0\u0003\u0002|U\t\u0019\u0011I\\=\t\u000fut\u0011\u0011!a\u0001\u0001\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0001\u0011\u000b\u0005\r\u0011\u0011B=\u000e\u0005\u0005\u0015!bAA\u0004U\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0011Q\u0001\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0012\u0005]\u0001cA\u0015\u0002\u0014%\u0019\u0011Q\u0003\u0016\u0003\u000f\t{w\u000e\\3b]\"9Q\u0010EA\u0001\u0002\u0004I\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2a\\A\u000f\u0011\u001di\u0018#!AA\u0002\u0001\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u0001\u0006AAo\\*ue&tw\rF\u0001p\u0003\u0019)\u0017/^1mgR!\u0011\u0011CA\u0016\u0011\u001diH#!AA\u0002e\f1BU3q_J$XI\u001d:peB\u0011AKF\n\u0006-\u0005M\u0012q\b\t\t\u0003k\tY\u0004\u0011$G'6\u0011\u0011q\u0007\u0006\u0004\u0003sQ\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003{\t9DA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!!\u0011\u0002H5\u0011\u00111\t\u0006\u0004\u0003\u000b\u001a\u0018AA5p\u0013\ra\u00141\t\u000b\u0003\u0003_\tQ!\u00199qYf$raUA(\u0003#\n\u0019\u0006C\u0003?3\u0001\u0007\u0001\tC\u0003E3\u0001\u0007a\tC\u0003P3\u0001\u0007a)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005e\u0013Q\r\t\u0006S\u0005m\u0013qL\u0005\u0004\u0003;R#AB(qi&|g\u000e\u0005\u0004*\u0003C\u0002eIR\u0005\u0004\u0003GR#A\u0002+va2,7\u0007\u0003\u0005\u0002hi\t\t\u00111\u0001T\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003[\u00022\u0001]A8\u0013\r\t\t(\u001d\u0002\u0007\u001f\nTWm\u0019;"
)
public class ReportError implements Product, Serializable {
   private final int streamId;
   private final String message;
   private final String error;

   public static Option unapply(final ReportError x$0) {
      return ReportError$.MODULE$.unapply(x$0);
   }

   public static ReportError apply(final int streamId, final String message, final String error) {
      return ReportError$.MODULE$.apply(streamId, message, error);
   }

   public static Function1 tupled() {
      return ReportError$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ReportError$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamId() {
      return this.streamId;
   }

   public String message() {
      return this.message;
   }

   public String error() {
      return this.error;
   }

   public ReportError copy(final int streamId, final String message, final String error) {
      return new ReportError(streamId, message, error);
   }

   public int copy$default$1() {
      return this.streamId();
   }

   public String copy$default$2() {
      return this.message();
   }

   public String copy$default$3() {
      return this.error();
   }

   public String productPrefix() {
      return "ReportError";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamId());
         }
         case 1 -> {
            return this.message();
         }
         case 2 -> {
            return this.error();
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
      return x$1 instanceof ReportError;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamId";
         }
         case 1 -> {
            return "message";
         }
         case 2 -> {
            return "error";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.message()));
      var1 = Statics.mix(var1, Statics.anyHash(this.error()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ReportError) {
               ReportError var4 = (ReportError)x$1;
               if (this.streamId() == var4.streamId()) {
                  label52: {
                     String var10000 = this.message();
                     String var5 = var4.message();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     var10000 = this.error();
                     String var6 = var4.error();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public ReportError(final int streamId, final String message, final String error) {
      this.streamId = streamId;
      this.message = message;
      this.error = error;
      Product.$init$(this);
   }
}
