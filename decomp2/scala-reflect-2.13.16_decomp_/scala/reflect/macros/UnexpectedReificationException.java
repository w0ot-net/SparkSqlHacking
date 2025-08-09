package scala.reflect.macros;

import scala.Function1;
import scala.Function3;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\u000f\u001f\u0001\u0016B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005y!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005N\u0001\tE\t\u0015!\u0003F\u0011!q\u0005A!f\u0001\n\u0003y\u0005\u0002C*\u0001\u0005#\u0005\u000b\u0011\u0002)\t\u000bQ\u0003A\u0011A+\t\u000fm\u0003\u0011\u0011!C\u00019\"9\u0001\rAI\u0001\n\u0003\t\u0007b\u00027\u0001#\u0003%\t!\u001c\u0005\b_\u0002\t\n\u0011\"\u0001q\u0011\u001d\u0011\b!!A\u0005BMDqa\u001f\u0001\u0002\u0002\u0013\u0005A\u0010C\u0005\u0002\u0002\u0001\t\t\u0011\"\u0001\u0002\u0004!I\u0011q\u0002\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0003\u0005\n\u0003?\u0001\u0011\u0011!C\u0001\u0003CA\u0011\"a\u000b\u0001\u0003\u0003%\t%!\f\t\u0013\u0005E\u0002!!A\u0005B\u0005M\u0002\"CA\u001b\u0001\u0005\u0005I\u0011IA\u001c\u000f%\tYDHA\u0001\u0012\u0003\tiD\u0002\u0005\u001e=\u0005\u0005\t\u0012AA \u0011\u0019!V\u0003\"\u0001\u0002X!I\u0011\u0011L\u000b\u0002\u0002\u0013\u0015\u00131\f\u0005\n\u0003;*\u0012\u0011!CA\u0003?B\u0001\"a\u001a\u0016#\u0003%\t\u0001\u001d\u0005\n\u0003S*\u0012\u0011!CA\u0003WB\u0001\"! \u0016#\u0003%\t\u0001\u001d\u0005\n\u0003\u007f*\u0012\u0011!C\u0005\u0003\u0003\u0013a$\u00168fqB,7\r^3e%\u0016Lg-[2bi&|g.\u0012=dKB$\u0018n\u001c8\u000b\u0005}\u0001\u0013AB7bGJ|7O\u0003\u0002\"E\u00059!/\u001a4mK\u000e$(\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001A\n\u00182!\t93F\u0004\u0002)S5\t!%\u0003\u0002+E\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0017.\u0005%)\u0005pY3qi&|gN\u0003\u0002+EA\u0011\u0001fL\u0005\u0003a\t\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00023q9\u00111'\u000b\b\u0003i]j\u0011!\u000e\u0006\u0003m\u0011\na\u0001\u0010:p_Rt\u0014\"A\u0012\n\u0005ej#\u0001D*fe&\fG.\u001b>bE2,\u0017a\u00019pgV\tA\b\u0005\u0002>\u00016\taH\u0003\u0002@A\u0005\u0019\u0011\r]5\n\u0005\u0005s$\u0001\u0003)pg&$\u0018n\u001c8\u0002\tA|7\u000fI\u0001\u0004[N<W#A#\u0011\u0005\u0019SeBA$I!\t!$%\u0003\u0002JE\u00051\u0001K]3eK\u001aL!a\u0013'\u0003\rM#(/\u001b8h\u0015\tI%%\u0001\u0003ng\u001e\u0004\u0013!B2bkN,W#\u0001)\u0011\u0005\u001d\n\u0016B\u0001*.\u0005%!\u0006N]8xC\ndW-\u0001\u0004dCV\u001cX\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tYC\u0016L\u0017\t\u0003/\u0002i\u0011A\b\u0005\u0006u\u001d\u0001\r\u0001\u0010\u0005\u0006\u0007\u001e\u0001\r!\u0012\u0005\b\u001d\u001e\u0001\n\u00111\u0001Q\u0003\u0011\u0019w\u000e]=\u0015\tYkfl\u0018\u0005\bu!\u0001\n\u00111\u0001=\u0011\u001d\u0019\u0005\u0002%AA\u0002\u0015CqA\u0014\u0005\u0011\u0002\u0003\u0007\u0001+\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\tT#\u0001P2,\u0003\u0011\u0004\"!\u001a6\u000e\u0003\u0019T!a\u001a5\u0002\u0013Ut7\r[3dW\u0016$'BA5#\u0003)\tgN\\8uCRLwN\\\u0005\u0003W\u001a\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012A\u001c\u0016\u0003\u000b\u000e\fabY8qs\u0012\"WMZ1vYR$3'F\u0001rU\t\u00016-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002iB\u0011QO_\u0007\u0002m*\u0011q\u000f_\u0001\u0005Y\u0006twMC\u0001z\u0003\u0011Q\u0017M^1\n\u0005-3\u0018\u0001\u00049s_\u0012,8\r^!sSRLX#A?\u0011\u0005!r\u0018BA@#\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t)!a\u0003\u0011\u0007!\n9!C\u0002\u0002\n\t\u00121!\u00118z\u0011!\tiADA\u0001\u0002\u0004i\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0014A1\u0011QCA\u000e\u0003\u000bi!!a\u0006\u000b\u0007\u0005e!%\u0001\u0006d_2dWm\u0019;j_:LA!!\b\u0002\u0018\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019#!\u000b\u0011\u0007!\n)#C\u0002\u0002(\t\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u000eA\t\t\u00111\u0001\u0002\u0006\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r!\u0018q\u0006\u0005\t\u0003\u001b\t\u0012\u0011!a\u0001{\u0006A\u0001.Y:i\u0007>$W\rF\u0001~\u0003\u0019)\u0017/^1mgR!\u00111EA\u001d\u0011%\tiaEA\u0001\u0002\u0004\t)!\u0001\u0010V]\u0016D\b/Z2uK\u0012\u0014V-\u001b4jG\u0006$\u0018n\u001c8Fq\u000e,\u0007\u000f^5p]B\u0011q+F\n\u0006+\u0005\u0005\u0013Q\n\t\t\u0003\u0007\nI\u0005P#Q-6\u0011\u0011Q\t\u0006\u0004\u0003\u000f\u0012\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0017\n)EA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!a\u0014\u0002V5\u0011\u0011\u0011\u000b\u0006\u0004\u0003'B\u0018AA5p\u0013\rI\u0014\u0011\u000b\u000b\u0003\u0003{\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002i\u0006)\u0011\r\u001d9msR9a+!\u0019\u0002d\u0005\u0015\u0004\"\u0002\u001e\u0019\u0001\u0004a\u0004\"B\"\u0019\u0001\u0004)\u0005b\u0002(\u0019!\u0003\u0005\r\u0001U\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u00059QO\\1qa2LH\u0003BA7\u0003s\u0002R\u0001KA8\u0003gJ1!!\u001d#\u0005\u0019y\u0005\u000f^5p]B1\u0001&!\u001e=\u000bBK1!a\u001e#\u0005\u0019!V\u000f\u001d7fg!A\u00111\u0010\u000e\u0002\u0002\u0003\u0007a+A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001a\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAB!\r)\u0018QQ\u0005\u0004\u0003\u000f3(AB(cU\u0016\u001cG\u000f"
)
public class UnexpectedReificationException extends Exception implements Product {
   private final Position pos;
   private final String msg;
   private final Throwable cause;

   public static Throwable $lessinit$greater$default$3() {
      UnexpectedReificationException$ var10000 = UnexpectedReificationException$.MODULE$;
      return null;
   }

   public static Option unapply(final UnexpectedReificationException x$0) {
      return UnexpectedReificationException$.MODULE$.unapply(x$0);
   }

   public static Throwable apply$default$3() {
      UnexpectedReificationException$ var10000 = UnexpectedReificationException$.MODULE$;
      return null;
   }

   public static UnexpectedReificationException apply(final Position pos, final String msg, final Throwable cause) {
      UnexpectedReificationException$ var10000 = UnexpectedReificationException$.MODULE$;
      return new UnexpectedReificationException(pos, msg, cause);
   }

   public static Function1 tupled() {
      return Function3.tupled$(UnexpectedReificationException$.MODULE$);
   }

   public static Function1 curried() {
      return Function3.curried$(UnexpectedReificationException$.MODULE$);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Position pos() {
      return this.pos;
   }

   public String msg() {
      return this.msg;
   }

   public Throwable cause() {
      return this.cause;
   }

   public UnexpectedReificationException copy(final Position pos, final String msg, final Throwable cause) {
      return new UnexpectedReificationException(pos, msg, cause);
   }

   public Position copy$default$1() {
      return this.pos();
   }

   public String copy$default$2() {
      return this.msg();
   }

   public Throwable copy$default$3() {
      return this.cause();
   }

   public String productPrefix() {
      return "UnexpectedReificationException";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.pos();
         case 1:
            return this.msg();
         case 2:
            return this.cause();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof UnexpectedReificationException;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "pos";
         case 1:
            return "msg";
         case 2:
            return "cause";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$.productHash(this, -889275714, false);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof UnexpectedReificationException) {
            UnexpectedReificationException var2 = (UnexpectedReificationException)x$1;
            Position var10000 = this.pos();
            Position var3 = var2.pos();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            String var6 = this.msg();
            String var4 = var2.msg();
            if (var6 == null) {
               if (var4 != null) {
                  return false;
               }
            } else if (!var6.equals(var4)) {
               return false;
            }

            Throwable var7 = this.cause();
            Throwable var5 = var2.cause();
            if (var7 == null) {
               if (var5 != null) {
                  return false;
               }
            } else if (!var7.equals(var5)) {
               return false;
            }

            if (var2.canEqual(this)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public UnexpectedReificationException(final Position pos, final String msg, final Throwable cause) {
      super(msg, cause);
      this.pos = pos;
      this.msg = msg;
      this.cause = cause;
   }
}
