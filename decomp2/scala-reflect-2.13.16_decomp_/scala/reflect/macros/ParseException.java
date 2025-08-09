package scala.reflect.macros;

import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd\u0001\u0002\r\u001a\u0001\u0002B\u0001\"\u000e\u0001\u0003\u0016\u0004%\tA\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005o!Aa\b\u0001BK\u0002\u0013\u0005q\b\u0003\u0005I\u0001\tE\t\u0015!\u0003A\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u001dy\u0005!!A\u0005\u0002ACqa\u0015\u0001\u0012\u0002\u0013\u0005A\u000bC\u0004`\u0001E\u0005I\u0011\u00011\t\u000f\t\u0004\u0011\u0011!C!G\"91\u000eAA\u0001\n\u0003a\u0007b\u00029\u0001\u0003\u0003%\t!\u001d\u0005\bo\u0002\t\t\u0011\"\u0011y\u0011!y\b!!A\u0005\u0002\u0005\u0005\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019\u0002C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018\u001dI\u00111D\r\u0002\u0002#\u0005\u0011Q\u0004\u0004\t1e\t\t\u0011#\u0001\u0002 !1\u0011J\u0005C\u0001\u0003oA\u0011\"!\u000f\u0013\u0003\u0003%)%a\u000f\t\u0013\u0005u\"#!A\u0005\u0002\u0006}\u0002\"CA#%\u0005\u0005I\u0011QA$\u0011%\tIFEA\u0001\n\u0013\tYF\u0001\bQCJ\u001cX-\u0012=dKB$\u0018n\u001c8\u000b\u0005iY\u0012AB7bGJ|7O\u0003\u0002\u001d;\u00059!/\u001a4mK\u000e$(\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001!I\u0015-!\t\u0011cE\u0004\u0002$I5\tQ$\u0003\u0002&;\u00059\u0001/Y2lC\u001e,\u0017BA\u0014)\u0005%)\u0005pY3qi&|gN\u0003\u0002&;A\u00111EK\u0005\u0003Wu\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002.g9\u0011a\u0006\n\b\u0003_Ij\u0011\u0001\r\u0006\u0003c}\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0010\n\u0005QB#\u0001D*fe&\fG.\u001b>bE2,\u0017a\u00019pgV\tq\u0007\u0005\u00029w5\t\u0011H\u0003\u0002;7\u0005\u0019\u0011\r]5\n\u0005qJ$\u0001\u0003)pg&$\u0018n\u001c8\u0002\tA|7\u000fI\u0001\u0004[N<W#\u0001!\u0011\u0005\u0005+eB\u0001\"D!\tyS$\u0003\u0002E;\u00051\u0001K]3eK\u001aL!AR$\u0003\rM#(/\u001b8h\u0015\t!U$\u0001\u0003ng\u001e\u0004\u0013A\u0002\u001fj]&$h\bF\u0002L\u001b:\u0003\"\u0001\u0014\u0001\u000e\u0003eAQ!N\u0003A\u0002]BQAP\u0003A\u0002\u0001\u000bAaY8qsR\u00191*\u0015*\t\u000fU2\u0001\u0013!a\u0001o!9aH\u0002I\u0001\u0002\u0004\u0001\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002+*\u0012qGV\u0016\u0002/B\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001X\u000f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002_3\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t\u0011M\u000b\u0002A-\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\fA\u0001\\1oO*\t\u0011.\u0001\u0003kCZ\f\u0017B\u0001$g\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005i\u0007CA\u0012o\u0013\tyWDA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002skB\u00111e]\u0005\u0003iv\u00111!\u00118z\u0011\u001d18\"!AA\u00025\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A=\u0011\u0007il(/D\u0001|\u0015\taX$\u0001\u0006d_2dWm\u0019;j_:L!A`>\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0007\tI\u0001E\u0002$\u0003\u000bI1!a\u0002\u001e\u0005\u001d\u0011un\u001c7fC:DqA^\u0007\u0002\u0002\u0003\u0007!/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00013\u0002\u0010!9aODA\u0001\u0002\u0004i\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u00035\fa!Z9vC2\u001cH\u0003BA\u0002\u00033AqA\u001e\t\u0002\u0002\u0003\u0007!/\u0001\bQCJ\u001cX-\u0012=dKB$\u0018n\u001c8\u0011\u00051\u00132#\u0002\n\u0002\"\u00055\u0002cBA\u0012\u0003S9\u0004iS\u0007\u0003\u0003KQ1!a\n\u001e\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u000b\u0002&\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005=\u0012QG\u0007\u0003\u0003cQ1!a\ri\u0003\tIw.C\u00025\u0003c!\"!!\b\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001Z\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u0017\u0006\u0005\u00131\t\u0005\u0006kU\u0001\ra\u000e\u0005\u0006}U\u0001\r\u0001Q\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tI%!\u0016\u0011\u000b\r\nY%a\u0014\n\u0007\u00055SD\u0001\u0004PaRLwN\u001c\t\u0006G\u0005Es\u0007Q\u0005\u0004\u0003'j\"A\u0002+va2,'\u0007\u0003\u0005\u0002XY\t\t\u00111\u0001L\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003;\u00022!ZA0\u0013\r\t\tG\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class ParseException extends Exception implements Product {
   private final Position pos;
   private final String msg;

   public static Option unapply(final ParseException x$0) {
      return ParseException$.MODULE$.unapply(x$0);
   }

   public static ParseException apply(final Position pos, final String msg) {
      ParseException$ var10000 = ParseException$.MODULE$;
      return new ParseException(pos, msg);
   }

   public static Function1 tupled() {
      return Function2.tupled$(ParseException$.MODULE$);
   }

   public static Function1 curried() {
      return Function2.curried$(ParseException$.MODULE$);
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

   public ParseException copy(final Position pos, final String msg) {
      return new ParseException(pos, msg);
   }

   public Position copy$default$1() {
      return this.pos();
   }

   public String copy$default$2() {
      return this.msg();
   }

   public String productPrefix() {
      return "ParseException";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.pos();
         case 1:
            return this.msg();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ParseException;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "pos";
         case 1:
            return "msg";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$.productHash(this, -889275714, false);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof ParseException) {
            ParseException var2 = (ParseException)x$1;
            Position var10000 = this.pos();
            Position var3 = var2.pos();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            String var5 = this.msg();
            String var4 = var2.msg();
            if (var5 == null) {
               if (var4 != null) {
                  return false;
               }
            } else if (!var5.equals(var4)) {
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

   public ParseException(final Position pos, final String msg) {
      super(msg);
      this.pos = pos;
      this.msg = msg;
   }
}
