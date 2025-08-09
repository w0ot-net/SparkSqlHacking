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
   bytes = "\u0006\u0005\u0005\rd\u0001\u0002\r\u001a\u0001\u0002B\u0001\"\u000e\u0001\u0003\u0016\u0004%\tA\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005o!Aa\b\u0001BK\u0002\u0013\u0005q\b\u0003\u0005I\u0001\tE\t\u0015!\u0003A\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u001dy\u0005!!A\u0005\u0002ACqa\u0015\u0001\u0012\u0002\u0013\u0005A\u000bC\u0004`\u0001E\u0005I\u0011\u00011\t\u000f\t\u0004\u0011\u0011!C!G\"91\u000eAA\u0001\n\u0003a\u0007b\u00029\u0001\u0003\u0003%\t!\u001d\u0005\bo\u0002\t\t\u0011\"\u0011y\u0011!y\b!!A\u0005\u0002\u0005\u0005\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019\u0002C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018\u001dI\u00111D\r\u0002\u0002#\u0005\u0011Q\u0004\u0004\t1e\t\t\u0011#\u0001\u0002 !1\u0011J\u0005C\u0001\u0003oA\u0011\"!\u000f\u0013\u0003\u0003%)%a\u000f\t\u0013\u0005u\"#!A\u0005\u0002\u0006}\u0002\"CA#%\u0005\u0005I\u0011QA$\u0011%\tIFEA\u0001\n\u0013\tYF\u0001\u000bSK&4\u0017nY1uS>tW\t_2faRLwN\u001c\u0006\u00035m\ta!\\1de>\u001c(B\u0001\u000f\u001e\u0003\u001d\u0011XM\u001a7fGRT\u0011AH\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u0001\u0011%\u000b\u0017\u0011\u0005\t2cBA\u0012%\u001b\u0005i\u0012BA\u0013\u001e\u0003\u001d\u0001\u0018mY6bO\u0016L!a\n\u0015\u0003\u0013\u0015C8-\u001a9uS>t'BA\u0013\u001e!\t\u0019#&\u0003\u0002,;\t9\u0001K]8ek\u000e$\bCA\u00174\u001d\tqCE\u0004\u00020e5\t\u0001G\u0003\u00022?\u00051AH]8pizJ\u0011AH\u0005\u0003i!\u0012AbU3sS\u0006d\u0017N_1cY\u0016\f1\u0001]8t+\u00059\u0004C\u0001\u001d<\u001b\u0005I$B\u0001\u001e\u001c\u0003\r\t\u0007/[\u0005\u0003ye\u0012\u0001\u0002U8tSRLwN\\\u0001\u0005a>\u001c\b%A\u0002ng\u001e,\u0012\u0001\u0011\t\u0003\u0003\u0016s!AQ\"\u0011\u0005=j\u0012B\u0001#\u001e\u0003\u0019\u0001&/\u001a3fM&\u0011ai\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0011k\u0012\u0001B7tO\u0002\na\u0001P5oSRtDcA&N\u001dB\u0011A\nA\u0007\u00023!)Q'\u0002a\u0001o!)a(\u0002a\u0001\u0001\u0006!1m\u001c9z)\rY\u0015K\u0015\u0005\bk\u0019\u0001\n\u00111\u00018\u0011\u001dqd\u0001%AA\u0002\u0001\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001VU\t9dkK\u0001X!\tAV,D\u0001Z\u0015\tQ6,A\u0005v]\u000eDWmY6fI*\u0011A,H\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00010Z\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005\t'F\u0001!W\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\r\u0005\u0002fU6\taM\u0003\u0002hQ\u0006!A.\u00198h\u0015\u0005I\u0017\u0001\u00026bm\u0006L!A\u00124\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u00035\u0004\"a\t8\n\u0005=l\"aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001:v!\t\u00193/\u0003\u0002u;\t\u0019\u0011I\\=\t\u000fY\\\u0011\u0011!a\u0001[\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001f\t\u0004uv\u0014X\"A>\u000b\u0005ql\u0012AC2pY2,7\r^5p]&\u0011ap\u001f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0004\u0005%\u0001cA\u0012\u0002\u0006%\u0019\u0011qA\u000f\u0003\u000f\t{w\u000e\\3b]\"9a/DA\u0001\u0002\u0004\u0011\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001ZA\b\u0011\u001d1h\"!AA\u00025\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002[\u00061Q-];bYN$B!a\u0001\u0002\u001a!9a\u000fEA\u0001\u0002\u0004\u0011\u0018\u0001\u0006*fS\u001aL7-\u0019;j_:,\u0005pY3qi&|g\u000e\u0005\u0002M%M)!#!\t\u0002.A9\u00111EA\u0015o\u0001[UBAA\u0013\u0015\r\t9#H\u0001\beVtG/[7f\u0013\u0011\tY#!\n\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u00020\u0005URBAA\u0019\u0015\r\t\u0019\u0004[\u0001\u0003S>L1\u0001NA\u0019)\t\ti\"\u0001\u0005u_N#(/\u001b8h)\u0005!\u0017!B1qa2LH#B&\u0002B\u0005\r\u0003\"B\u001b\u0016\u0001\u00049\u0004\"\u0002 \u0016\u0001\u0004\u0001\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0013\n)\u0006E\u0003$\u0003\u0017\ny%C\u0002\u0002Nu\u0011aa\u00149uS>t\u0007#B\u0012\u0002R]\u0002\u0015bAA*;\t1A+\u001e9mKJB\u0001\"a\u0016\u0017\u0003\u0003\u0005\raS\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA/!\r)\u0017qL\u0005\u0004\u0003C2'AB(cU\u0016\u001cG\u000f"
)
public class ReificationException extends Exception implements Product {
   private final Position pos;
   private final String msg;

   public static Option unapply(final ReificationException x$0) {
      return ReificationException$.MODULE$.unapply(x$0);
   }

   public static ReificationException apply(final Position pos, final String msg) {
      ReificationException$ var10000 = ReificationException$.MODULE$;
      return new ReificationException(pos, msg);
   }

   public static Function1 tupled() {
      return Function2.tupled$(ReificationException$.MODULE$);
   }

   public static Function1 curried() {
      return Function2.curried$(ReificationException$.MODULE$);
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

   public ReificationException copy(final Position pos, final String msg) {
      return new ReificationException(pos, msg);
   }

   public Position copy$default$1() {
      return this.pos();
   }

   public String copy$default$2() {
      return this.msg();
   }

   public String productPrefix() {
      return "ReificationException";
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
      return x$1 instanceof ReificationException;
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
         if (x$1 instanceof ReificationException) {
            ReificationException var2 = (ReificationException)x$1;
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

   public ReificationException(final Position pos, final String msg) {
      super(msg);
      this.pos = pos;
      this.msg = msg;
   }
}
