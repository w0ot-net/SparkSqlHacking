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
   bytes = "\u0006\u0005\u0005Me!\u0002\u0012$\u0001\u0016j\u0003\u0002\u0003#\u0001\u0005+\u0007I\u0011A#\t\u00119\u0003!\u0011#Q\u0001\n\u0019C\u0001b\u0014\u0001\u0003\u0016\u0004%\t!\u0012\u0005\t!\u0002\u0011\t\u0012)A\u0005\r\"A\u0011\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005W\u0001\tE\t\u0015!\u0003T\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u001dq\u0006!!A\u0005\u0002}Cqa\u0019\u0001\u0012\u0002\u0013\u0005A\rC\u0004p\u0001E\u0005I\u0011\u00013\t\u000fA\u0004\u0011\u0013!C\u0001c\"91\u000fAA\u0001\n\u0003\"\bb\u0002?\u0001\u0003\u0003%\t! \u0005\n\u0003\u0007\u0001\u0011\u0011!C\u0001\u0003\u000bA\u0011\"!\u0005\u0001\u0003\u0003%\t%a\u0005\t\u0013\u0005\u0005\u0002!!A\u0005\u0002\u0005\r\u0002\"CA\u0017\u0001\u0005\u0005I\u0011IA\u0018\u0011%\t\u0019\u0004AA\u0001\n\u0003\n)\u0004C\u0005\u00028\u0001\t\t\u0011\"\u0011\u0002:!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0013QH\u0004\u000b\u0003\u0003\u001a\u0013\u0011!E\u0001K\u0005\rc!\u0003\u0012$\u0003\u0003E\t!JA#\u0011\u00199f\u0003\"\u0001\u0002^!I\u0011q\u0007\f\u0002\u0002\u0013\u0015\u0013\u0011\b\u0005\n\u0003?2\u0012\u0011!CA\u0003CB\u0001\"!\u001b\u0017#\u0003%\t\u0001\u001a\u0005\t\u0003W2\u0012\u0013!C\u0001I\"A\u0011Q\u000e\f\u0012\u0002\u0013\u0005\u0011\u000fC\u0005\u0002pY\t\t\u0011\"!\u0002r!A\u00111\u0011\f\u0012\u0002\u0013\u0005A\r\u0003\u0005\u0002\u0006Z\t\n\u0011\"\u0001e\u0011!\t9IFI\u0001\n\u0003\t\b\"CAE-\u0005\u0005I\u0011BAF\u0005E\u0011VmY3jm\u0016\u0014XI\u001d:pe&sgm\u001c\u0006\u0003I\u0015\n\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u0019:\u0013!C:ue\u0016\fW.\u001b8h\u0015\tA\u0013&A\u0003ta\u0006\u00148N\u0003\u0002+W\u00051\u0011\r]1dQ\u0016T\u0011\u0001L\u0001\u0004_J<7\u0003\u0002\u0001/i]\u0002\"a\f\u001a\u000e\u0003AR\u0011!M\u0001\u0006g\u000e\fG.Y\u0005\u0003gA\u0012a!\u00118z%\u00164\u0007CA\u00186\u0013\t1\u0004GA\u0004Qe>$Wo\u0019;\u0011\u0005a\neBA\u001d@\u001d\tQd(D\u0001<\u0015\taT(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\t\u0014B\u0001!1\u0003\u001d\u0001\u0018mY6bO\u0016L!AQ\"\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0001\u0003\u0014\u0001\u00057bgR,%O]8s\u001b\u0016\u001c8/Y4f+\u00051\u0005CA$L\u001d\tA\u0015\n\u0005\u0002;a%\u0011!\nM\u0001\u0007!J,G-\u001a4\n\u00051k%AB*ue&twM\u0003\u0002Ka\u0005\tB.Y:u\u000bJ\u0014xN]'fgN\fw-\u001a\u0011\u0002\u00131\f7\u000f^#se>\u0014\u0018A\u00037bgR,%O]8sA\u0005iA.Y:u\u000bJ\u0014xN\u001d+j[\u0016,\u0012a\u0015\t\u0003_QK!!\u0016\u0019\u0003\t1{gnZ\u0001\u000fY\u0006\u001cH/\u0012:s_J$\u0016.\\3!\u0003\u0019a\u0014N\\5u}Q!\u0011l\u0017/^!\tQ\u0006!D\u0001$\u0011\u001d!u\u0001%AA\u0002\u0019CqaT\u0004\u0011\u0002\u0003\u0007a\tC\u0004R\u000fA\u0005\t\u0019A*\u0002\t\r|\u0007/\u001f\u000b\u00053\u0002\f'\rC\u0004E\u0011A\u0005\t\u0019\u0001$\t\u000f=C\u0001\u0013!a\u0001\r\"9\u0011\u000b\u0003I\u0001\u0002\u0004\u0019\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002K*\u0012aIZ\u0016\u0002OB\u0011\u0001.\\\u0007\u0002S*\u0011!n[\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u001c\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002oS\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT#\u0001:+\u0005M3\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001v!\t180D\u0001x\u0015\tA\u00180\u0001\u0003mC:<'\"\u0001>\u0002\t)\fg/Y\u0005\u0003\u0019^\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A \t\u0003_}L1!!\u00011\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t9!!\u0004\u0011\u0007=\nI!C\u0002\u0002\fA\u00121!\u00118z\u0011!\tyADA\u0001\u0002\u0004q\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0016A1\u0011qCA\u000f\u0003\u000fi!!!\u0007\u000b\u0007\u0005m\u0001'\u0001\u0006d_2dWm\u0019;j_:LA!a\b\u0002\u001a\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t)#a\u000b\u0011\u0007=\n9#C\u0002\u0002*A\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u0010A\t\t\u00111\u0001\u0002\b\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r)\u0018\u0011\u0007\u0005\t\u0003\u001f\t\u0012\u0011!a\u0001}\u0006A\u0001.Y:i\u0007>$W\rF\u0001\u007f\u0003!!xn\u0015;sS:<G#A;\u0002\r\u0015\fX/\u00197t)\u0011\t)#a\u0010\t\u0013\u0005=A#!AA\u0002\u0005\u001d\u0011!\u0005*fG\u0016Lg/\u001a:FeJ|'/\u00138g_B\u0011!LF\n\u0006-\u0005\u001d\u00131\u000b\t\t\u0003\u0013\nyE\u0012$T36\u0011\u00111\n\u0006\u0004\u0003\u001b\u0002\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003#\nYEA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!!\u0016\u0002\\5\u0011\u0011q\u000b\u0006\u0004\u00033J\u0018AA5p\u0013\r\u0011\u0015q\u000b\u000b\u0003\u0003\u0007\nQ!\u00199qYf$r!WA2\u0003K\n9\u0007C\u0004E3A\u0005\t\u0019\u0001$\t\u000f=K\u0002\u0013!a\u0001\r\"9\u0011+\u0007I\u0001\u0002\u0004\u0019\u0016aD1qa2LH\u0005Z3gCVdG\u000fJ\u0019\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uII\nq\"\u00199qYf$C-\u001a4bk2$HeM\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\u0019(a \u0011\u000b=\n)(!\u001f\n\u0007\u0005]\u0004G\u0001\u0004PaRLwN\u001c\t\u0007_\u0005mdIR*\n\u0007\u0005u\u0004G\u0001\u0004UkBdWm\r\u0005\t\u0003\u0003k\u0012\u0011!a\u00013\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!$\u0011\u0007Y\fy)C\u0002\u0002\u0012^\u0014aa\u00142kK\u000e$\b"
)
public class ReceiverErrorInfo implements Product, Serializable {
   private final String lastErrorMessage;
   private final String lastError;
   private final long lastErrorTime;

   public static long $lessinit$greater$default$3() {
      return ReceiverErrorInfo$.MODULE$.$lessinit$greater$default$3();
   }

   public static String $lessinit$greater$default$2() {
      return ReceiverErrorInfo$.MODULE$.$lessinit$greater$default$2();
   }

   public static String $lessinit$greater$default$1() {
      return ReceiverErrorInfo$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final ReceiverErrorInfo x$0) {
      return ReceiverErrorInfo$.MODULE$.unapply(x$0);
   }

   public static long apply$default$3() {
      return ReceiverErrorInfo$.MODULE$.apply$default$3();
   }

   public static String apply$default$2() {
      return ReceiverErrorInfo$.MODULE$.apply$default$2();
   }

   public static String apply$default$1() {
      return ReceiverErrorInfo$.MODULE$.apply$default$1();
   }

   public static ReceiverErrorInfo apply(final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return ReceiverErrorInfo$.MODULE$.apply(lastErrorMessage, lastError, lastErrorTime);
   }

   public static Function1 tupled() {
      return ReceiverErrorInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ReceiverErrorInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String lastErrorMessage() {
      return this.lastErrorMessage;
   }

   public String lastError() {
      return this.lastError;
   }

   public long lastErrorTime() {
      return this.lastErrorTime;
   }

   public ReceiverErrorInfo copy(final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      return new ReceiverErrorInfo(lastErrorMessage, lastError, lastErrorTime);
   }

   public String copy$default$1() {
      return this.lastErrorMessage();
   }

   public String copy$default$2() {
      return this.lastError();
   }

   public long copy$default$3() {
      return this.lastErrorTime();
   }

   public String productPrefix() {
      return "ReceiverErrorInfo";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.lastErrorMessage();
         }
         case 1 -> {
            return this.lastError();
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.lastErrorTime());
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
      return x$1 instanceof ReceiverErrorInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "lastErrorMessage";
         }
         case 1 -> {
            return "lastError";
         }
         case 2 -> {
            return "lastErrorTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.lastErrorMessage()));
      var1 = Statics.mix(var1, Statics.anyHash(this.lastError()));
      var1 = Statics.mix(var1, Statics.longHash(this.lastErrorTime()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ReceiverErrorInfo) {
               ReceiverErrorInfo var4 = (ReceiverErrorInfo)x$1;
               if (this.lastErrorTime() == var4.lastErrorTime()) {
                  label52: {
                     String var10000 = this.lastErrorMessage();
                     String var5 = var4.lastErrorMessage();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     var10000 = this.lastError();
                     String var6 = var4.lastError();
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

   public ReceiverErrorInfo(final String lastErrorMessage, final String lastError, final long lastErrorTime) {
      this.lastErrorMessage = lastErrorMessage;
      this.lastError = lastError;
      this.lastErrorTime = lastErrorTime;
      Product.$init$(this);
   }
}
