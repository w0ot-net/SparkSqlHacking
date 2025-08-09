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
   bytes = "\u0006\u0005\u0005\rd\u0001\u0002\r\u001a\u0001\u0002B\u0001\"\u000e\u0001\u0003\u0016\u0004%\tA\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005o!Aa\b\u0001BK\u0002\u0013\u0005q\b\u0003\u0005I\u0001\tE\t\u0015!\u0003A\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u001dy\u0005!!A\u0005\u0002ACqa\u0015\u0001\u0012\u0002\u0013\u0005A\u000bC\u0004`\u0001E\u0005I\u0011\u00011\t\u000f\t\u0004\u0011\u0011!C!G\"91\u000eAA\u0001\n\u0003a\u0007b\u00029\u0001\u0003\u0003%\t!\u001d\u0005\bo\u0002\t\t\u0011\"\u0011y\u0011!y\b!!A\u0005\u0002\u0005\u0005\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019\u0002C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018\u001dI\u00111D\r\u0002\u0002#\u0005\u0011Q\u0004\u0004\t1e\t\t\u0011#\u0001\u0002 !1\u0011J\u0005C\u0001\u0003oA\u0011\"!\u000f\u0013\u0003\u0003%)%a\u000f\t\u0013\u0005u\"#!A\u0005\u0002\u0006}\u0002\"CA#%\u0005\u0005I\u0011QA$\u0011%\tIFEA\u0001\n\u0013\tYF\u0001\nUsB,7\r[3dW\u0016C8-\u001a9uS>t'B\u0001\u000e\u001c\u0003\u0019i\u0017m\u0019:pg*\u0011A$H\u0001\be\u00164G.Z2u\u0015\u0005q\u0012!B:dC2\f7\u0001A\n\u0005\u0001\u0005JC\u0006\u0005\u0002#M9\u00111\u0005J\u0007\u0002;%\u0011Q%H\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0003FA\u0005Fq\u000e,\u0007\u000f^5p]*\u0011Q%\b\t\u0003G)J!aK\u000f\u0003\u000fA\u0013x\u000eZ;diB\u0011Qf\r\b\u0003]\u0011r!a\f\u001a\u000e\u0003AR!!M\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0012B\u0001\u001b)\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\r\u0001xn]\u000b\u0002oA\u0011\u0001hO\u0007\u0002s)\u0011!hG\u0001\u0004CBL\u0017B\u0001\u001f:\u0005!\u0001vn]5uS>t\u0017\u0001\u00029pg\u0002\n1!\\:h+\u0005\u0001\u0005CA!F\u001d\t\u00115\t\u0005\u00020;%\u0011A)H\u0001\u0007!J,G-\u001a4\n\u0005\u0019;%AB*ue&twM\u0003\u0002E;\u0005!Qn]4!\u0003\u0019a\u0014N\\5u}Q\u00191*\u0014(\u0011\u00051\u0003Q\"A\r\t\u000bU*\u0001\u0019A\u001c\t\u000by*\u0001\u0019\u0001!\u0002\t\r|\u0007/\u001f\u000b\u0004\u0017F\u0013\u0006bB\u001b\u0007!\u0003\u0005\ra\u000e\u0005\b}\u0019\u0001\n\u00111\u0001A\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0016\u0016\u0003oY[\u0013a\u0016\t\u00031vk\u0011!\u0017\u0006\u00035n\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005qk\u0012AC1o]>$\u0018\r^5p]&\u0011a,\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002C*\u0012\u0001IV\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0011\u0004\"!\u001a6\u000e\u0003\u0019T!a\u001a5\u0002\t1\fgn\u001a\u0006\u0002S\u0006!!.\u0019<b\u0013\t1e-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001n!\t\u0019c.\u0003\u0002p;\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!/\u001e\t\u0003GML!\u0001^\u000f\u0003\u0007\u0005s\u0017\u0010C\u0004w\u0017\u0005\u0005\t\u0019A7\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005I\bc\u0001>~e6\t1P\u0003\u0002};\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005y\\(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0001\u0002\nA\u00191%!\u0002\n\u0007\u0005\u001dQDA\u0004C_>dW-\u00198\t\u000fYl\u0011\u0011!a\u0001e\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r!\u0017q\u0002\u0005\bm:\t\t\u00111\u0001n\u0003!A\u0017m\u001d5D_\u0012,G#A7\u0002\r\u0015\fX/\u00197t)\u0011\t\u0019!!\u0007\t\u000fY\u0004\u0012\u0011!a\u0001e\u0006\u0011B+\u001f9fG\",7m[#yG\u0016\u0004H/[8o!\ta%cE\u0003\u0013\u0003C\ti\u0003E\u0004\u0002$\u0005%r\u0007Q&\u000e\u0005\u0005\u0015\"bAA\u0014;\u00059!/\u001e8uS6,\u0017\u0002BA\u0016\u0003K\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\ty#!\u000e\u000e\u0005\u0005E\"bAA\u001aQ\u0006\u0011\u0011n\\\u0005\u0004i\u0005EBCAA\u000f\u0003!!xn\u0015;sS:<G#\u00013\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b-\u000b\t%a\u0011\t\u000bU*\u0002\u0019A\u001c\t\u000by*\u0002\u0019\u0001!\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011JA+!\u0015\u0019\u00131JA(\u0013\r\ti%\b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\r\n\tf\u000e!\n\u0007\u0005MSD\u0001\u0004UkBdWM\r\u0005\t\u0003/2\u0012\u0011!a\u0001\u0017\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0003cA3\u0002`%\u0019\u0011\u0011\r4\u0003\r=\u0013'.Z2u\u0001"
)
public class TypecheckException extends Exception implements Product {
   private final Position pos;
   private final String msg;

   public static Option unapply(final TypecheckException x$0) {
      return TypecheckException$.MODULE$.unapply(x$0);
   }

   public static TypecheckException apply(final Position pos, final String msg) {
      TypecheckException$ var10000 = TypecheckException$.MODULE$;
      return new TypecheckException(pos, msg);
   }

   public static Function1 tupled() {
      return Function2.tupled$(TypecheckException$.MODULE$);
   }

   public static Function1 curried() {
      return Function2.curried$(TypecheckException$.MODULE$);
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

   public TypecheckException copy(final Position pos, final String msg) {
      return new TypecheckException(pos, msg);
   }

   public Position copy$default$1() {
      return this.pos();
   }

   public String copy$default$2() {
      return this.msg();
   }

   public String productPrefix() {
      return "TypecheckException";
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
      return x$1 instanceof TypecheckException;
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
         if (x$1 instanceof TypecheckException) {
            TypecheckException var2 = (TypecheckException)x$1;
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

   public TypecheckException(final Position pos, final String msg) {
      super(msg);
      this.pos = pos;
      this.msg = msg;
   }
}
