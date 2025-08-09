package scala.xml.dtd;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001B\r\u001b\u0001\u0006B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005q!A\u0011\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005G\u0001\tE\t\u0015!\u0003D\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015a\u0005\u0001\"\u0011N\u0011\u001d!\u0006!!A\u0005\u0002UCq\u0001\u0017\u0001\u0012\u0002\u0013\u0005\u0011\fC\u0004e\u0001E\u0005I\u0011A3\t\u000f\u001d\u0004\u0011\u0011!C!Q\"9\u0001\u000fAA\u0001\n\u0003\t\bbB;\u0001\u0003\u0003%\tA\u001e\u0005\by\u0002\t\t\u0011\"\u0011~\u0011%\tI\u0001AA\u0001\n\u0003\tY\u0001C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003?\u0001\u0011\u0011!C!\u0003C9\u0011\"!\n\u001b\u0003\u0003E\t!a\n\u0007\u0011eQ\u0012\u0011!E\u0001\u0003SAaaR\n\u0005\u0002\u0005\u0005\u0003\"CA\"'\u0005\u0005IQIA#\u0011%\t9eEA\u0001\n\u0003\u000bI\u0005C\u0005\u0002PM\t\t\u0011\"!\u0002R!I\u00111M\n\u0002\u0002\u0013%\u0011Q\r\u0002\t\u000b2,W\u000eR3dY*\u00111\u0004H\u0001\u0004IR$'BA\u000f\u001f\u0003\rAX\u000e\u001c\u0006\u0002?\u0005)1oY1mC\u000e\u00011\u0003\u0002\u0001#M)\u0002\"a\t\u0013\u000e\u0003iI!!\n\u000e\u0003\u00155\u000b'o[;q\t\u0016\u001cG\u000e\u0005\u0002(Q5\ta$\u0003\u0002*=\t9\u0001K]8ek\u000e$\bCA\u00164\u001d\ta\u0013G\u0004\u0002.a5\taF\u0003\u00020A\u00051AH]8pizJ\u0011aH\u0005\u0003ey\tq\u0001]1dW\u0006<W-\u0003\u00025k\ta1+\u001a:jC2L'0\u00192mK*\u0011!GH\u0001\u0005]\u0006lW-F\u00019!\tITH\u0004\u0002;wA\u0011QFH\u0005\u0003yy\ta\u0001\u0015:fI\u00164\u0017B\u0001 @\u0005\u0019\u0019FO]5oO*\u0011AHH\u0001\u0006]\u0006lW\rI\u0001\rG>tG/\u001a8u\u001b>$W\r\\\u000b\u0002\u0007B\u00111\u0005R\u0005\u0003\u000bj\u0011AbQ8oi\u0016tG/T8eK2\fQbY8oi\u0016tG/T8eK2\u0004\u0013A\u0002\u001fj]&$h\bF\u0002J\u0015.\u0003\"a\t\u0001\t\u000bY*\u0001\u0019\u0001\u001d\t\u000b\u0005+\u0001\u0019A\"\u0002\u0017\t,\u0018\u000e\u001c3TiJLgn\u001a\u000b\u0003\u001dJ\u0003\"a\u0014)\u000f\u0005\u001d\n\u0014BA)6\u00055\u0019FO]5oO\n+\u0018\u000e\u001c3fe\")1K\u0002a\u0001\u001d\u0006\u00111OY\u0001\u0005G>\u0004\u0018\u0010F\u0002J-^CqAN\u0004\u0011\u0002\u0003\u0007\u0001\bC\u0004B\u000fA\u0005\t\u0019A\"\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t!L\u000b\u000297.\nA\f\u0005\u0002^E6\taL\u0003\u0002`A\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003Cz\t!\"\u00198o_R\fG/[8o\u0013\t\u0019gLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001gU\t\u00195,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002SB\u0011!n\\\u0007\u0002W*\u0011A.\\\u0001\u0005Y\u0006twMC\u0001o\u0003\u0011Q\u0017M^1\n\u0005yZ\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001:\u0011\u0005\u001d\u001a\u0018B\u0001;\u001f\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t9(\u0010\u0005\u0002(q&\u0011\u0011P\b\u0002\u0004\u0003:L\bbB>\r\u0003\u0003\u0005\rA]\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003y\u0004Ba`A\u0003o6\u0011\u0011\u0011\u0001\u0006\u0004\u0003\u0007q\u0012AC2pY2,7\r^5p]&!\u0011qAA\u0001\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u00055\u00111\u0003\t\u0004O\u0005=\u0011bAA\t=\t9!i\\8mK\u0006t\u0007bB>\u000f\u0003\u0003\u0005\ra^\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002j\u00033Aqa_\b\u0002\u0002\u0003\u0007!/\u0001\u0005iCND7i\u001c3f)\u0005\u0011\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u000e\u0005\r\u0002bB>\u0012\u0003\u0003\u0005\ra^\u0001\t\u000b2,W\u000eR3dYB\u00111eE\n\u0006'\u0005-\u0012q\u0007\t\b\u0003[\t\u0019\u0004O\"J\u001b\t\tyCC\u0002\u00022y\tqA];oi&lW-\u0003\u0003\u00026\u0005=\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011\u0011HA \u001b\t\tYDC\u0002\u0002>5\f!![8\n\u0007Q\nY\u0004\u0006\u0002\u0002(\u0005AAo\\*ue&tw\rF\u0001j\u0003\u0015\t\u0007\u000f\u001d7z)\u0015I\u00151JA'\u0011\u00151d\u00031\u00019\u0011\u0015\te\u00031\u0001D\u0003\u001d)h.\u00199qYf$B!a\u0015\u0002`A)q%!\u0016\u0002Z%\u0019\u0011q\u000b\u0010\u0003\r=\u0003H/[8o!\u00159\u00131\f\u001dD\u0013\r\tiF\b\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005\u0005t#!AA\u0002%\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u0007E\u0002k\u0003SJ1!a\u001bl\u0005\u0019y%M[3di\u0002"
)
public class ElemDecl extends MarkupDecl implements Product, Serializable {
   private final String name;
   private final ContentModel contentModel;

   public static Option unapply(final ElemDecl x$0) {
      return ElemDecl$.MODULE$.unapply(x$0);
   }

   public static ElemDecl apply(final String name, final ContentModel contentModel) {
      return ElemDecl$.MODULE$.apply(name, contentModel);
   }

   public static Function1 tupled() {
      return ElemDecl$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ElemDecl$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public ContentModel contentModel() {
      return this.contentModel;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      sb.append((new java.lang.StringBuilder(11)).append("<!ELEMENT ").append(this.name()).append(" ").toString());
      ContentModel$.MODULE$.buildString(this.contentModel(), sb);
      return sb.append('>');
   }

   public ElemDecl copy(final String name, final ContentModel contentModel) {
      return new ElemDecl(name, contentModel);
   }

   public String copy$default$1() {
      return this.name();
   }

   public ContentModel copy$default$2() {
      return this.contentModel();
   }

   public String productPrefix() {
      return "ElemDecl";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.contentModel();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ElemDecl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "contentModel";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ElemDecl) {
               label48: {
                  ElemDecl var4 = (ElemDecl)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  ContentModel var7 = this.contentModel();
                  ContentModel var6 = var4.contentModel();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
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

   public ElemDecl(final String name, final ContentModel contentModel) {
      this.name = name;
      this.contentModel = contentModel;
      Product.$init$(this);
   }
}
