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
   bytes = "\u0006\u0005\u00055d\u0001B\r\u001b\u0001\u0006B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005q!A\u0011\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005G\u0001\tE\t\u0015!\u0003D\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015a\u0005\u0001\"\u0011N\u0011\u001d!\u0006!!A\u0005\u0002UCq\u0001\u0017\u0001\u0012\u0002\u0013\u0005\u0011\fC\u0004e\u0001E\u0005I\u0011A3\t\u000f\u001d\u0004\u0011\u0011!C!Q\"9\u0001\u000fAA\u0001\n\u0003\t\bbB;\u0001\u0003\u0003%\tA\u001e\u0005\by\u0002\t\t\u0011\"\u0011~\u0011%\tI\u0001AA\u0001\n\u0003\tY\u0001C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003?\u0001\u0011\u0011!C!\u0003C9\u0011\"!\n\u001b\u0003\u0003E\t!a\n\u0007\u0011eQ\u0012\u0011!E\u0001\u0003SAaaR\n\u0005\u0002\u0005\u0005\u0003\"CA\"'\u0005\u0005IQIA#\u0011%\t9eEA\u0001\n\u0003\u000bI\u0005C\u0005\u0002PM\t\t\u0011\"!\u0002R!I\u00111M\n\u0002\u0002\u0013%\u0011Q\r\u0002\u0011!\u0006\u00148/\u001a3F]RLG/\u001f#fG2T!a\u0007\u000f\u0002\u0007\u0011$HM\u0003\u0002\u001e=\u0005\u0019\u00010\u001c7\u000b\u0003}\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001E\u0019R\u0003CA\u0012%\u001b\u0005Q\u0012BA\u0013\u001b\u0005))e\u000e^5us\u0012+7\r\u001c\t\u0003O!j\u0011AH\u0005\u0003Sy\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002,g9\u0011A&\r\b\u0003[Aj\u0011A\f\u0006\u0003_\u0001\na\u0001\u0010:p_Rt\u0014\"A\u0010\n\u0005Ir\u0012a\u00029bG.\fw-Z\u0005\u0003iU\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\r\u0010\u0002\t9\fW.Z\u000b\u0002qA\u0011\u0011(\u0010\b\u0003um\u0002\"!\f\u0010\n\u0005qr\u0012A\u0002)sK\u0012,g-\u0003\u0002?\u007f\t11\u000b\u001e:j]\u001eT!\u0001\u0010\u0010\u0002\u000b9\fW.\u001a\u0011\u0002\r\u0015tG\u000fZ3g+\u0005\u0019\u0005CA\u0012E\u0013\t)%DA\u0005F]RLG/\u001f#fM\u00069QM\u001c;eK\u001a\u0004\u0013A\u0002\u001fj]&$h\bF\u0002J\u0015.\u0003\"a\t\u0001\t\u000bY*\u0001\u0019\u0001\u001d\t\u000b\u0005+\u0001\u0019A\"\u0002\u0017\t,\u0018\u000e\u001c3TiJLgn\u001a\u000b\u0003\u001dJ\u0003\"a\u0014)\u000f\u0005\u001d\n\u0014BA)6\u00055\u0019FO]5oO\n+\u0018\u000e\u001c3fe\")1K\u0002a\u0001\u001d\u0006\u00111OY\u0001\u0005G>\u0004\u0018\u0010F\u0002J-^CqAN\u0004\u0011\u0002\u0003\u0007\u0001\bC\u0004B\u000fA\u0005\t\u0019A\"\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t!L\u000b\u000297.\nA\f\u0005\u0002^E6\taL\u0003\u0002`A\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003Cz\t!\"\u00198o_R\fG/[8o\u0013\t\u0019gLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001gU\t\u00195,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002SB\u0011!n\\\u0007\u0002W*\u0011A.\\\u0001\u0005Y\u0006twMC\u0001o\u0003\u0011Q\u0017M^1\n\u0005yZ\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001:\u0011\u0005\u001d\u001a\u0018B\u0001;\u001f\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t9(\u0010\u0005\u0002(q&\u0011\u0011P\b\u0002\u0004\u0003:L\bbB>\r\u0003\u0003\u0005\rA]\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003y\u0004Ba`A\u0003o6\u0011\u0011\u0011\u0001\u0006\u0004\u0003\u0007q\u0012AC2pY2,7\r^5p]&!\u0011qAA\u0001\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u00055\u00111\u0003\t\u0004O\u0005=\u0011bAA\t=\t9!i\\8mK\u0006t\u0007bB>\u000f\u0003\u0003\u0005\ra^\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002j\u00033Aqa_\b\u0002\u0002\u0003\u0007!/\u0001\u0005iCND7i\u001c3f)\u0005\u0011\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u000e\u0005\r\u0002bB>\u0012\u0003\u0003\u0005\ra^\u0001\u0011!\u0006\u00148/\u001a3F]RLG/\u001f#fG2\u0004\"aI\n\u0014\u000bM\tY#a\u000e\u0011\u000f\u00055\u00121\u0007\u001dD\u00136\u0011\u0011q\u0006\u0006\u0004\u0003cq\u0012a\u0002:v]RLW.Z\u0005\u0005\u0003k\tyCA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!!\u000f\u0002@5\u0011\u00111\b\u0006\u0004\u0003{i\u0017AA5p\u0013\r!\u00141\b\u000b\u0003\u0003O\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002S\u0006)\u0011\r\u001d9msR)\u0011*a\u0013\u0002N!)aG\u0006a\u0001q!)\u0011I\u0006a\u0001\u0007\u00069QO\\1qa2LH\u0003BA*\u0003?\u0002RaJA+\u00033J1!a\u0016\u001f\u0005\u0019y\u0005\u000f^5p]B)q%a\u00179\u0007&\u0019\u0011Q\f\u0010\u0003\rQ+\b\u000f\\33\u0011!\t\tgFA\u0001\u0002\u0004I\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\r\t\u0004U\u0006%\u0014bAA6W\n1qJ\u00196fGR\u0004"
)
public class ParsedEntityDecl extends EntityDecl implements Product, Serializable {
   private final String name;
   private final EntityDef entdef;

   public static Option unapply(final ParsedEntityDecl x$0) {
      return ParsedEntityDecl$.MODULE$.unapply(x$0);
   }

   public static ParsedEntityDecl apply(final String name, final EntityDef entdef) {
      return ParsedEntityDecl$.MODULE$.apply(name, entdef);
   }

   public static Function1 tupled() {
      return ParsedEntityDecl$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ParsedEntityDecl$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public EntityDef entdef() {
      return this.entdef;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      sb.append((new java.lang.StringBuilder(10)).append("<!ENTITY ").append(this.name()).append(" ").toString());
      return this.entdef().buildString(sb).append('>');
   }

   public ParsedEntityDecl copy(final String name, final EntityDef entdef) {
      return new ParsedEntityDecl(name, entdef);
   }

   public String copy$default$1() {
      return this.name();
   }

   public EntityDef copy$default$2() {
      return this.entdef();
   }

   public String productPrefix() {
      return "ParsedEntityDecl";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.entdef();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ParsedEntityDecl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "entdef";
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
            if (x$1 instanceof ParsedEntityDecl) {
               label48: {
                  ParsedEntityDecl var4 = (ParsedEntityDecl)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  EntityDef var7 = this.entdef();
                  EntityDef var6 = var4.entdef();
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

   public ParsedEntityDecl(final String name, final EntityDef entdef) {
      this.name = name;
      this.entdef = entdef;
      Product.$init$(this);
   }
}
