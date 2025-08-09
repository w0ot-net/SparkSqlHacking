package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005O!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005F\u0001\tE\t\u0015!\u0003C\u0011!1\u0005A!f\u0001\n\u00039\u0005\u0002\u0003(\u0001\u0005#\u0005\u000b\u0011\u0002%\t\u000b=\u0003A\u0011\u0001)\t\u000fU\u0003\u0011\u0011!C\u0001-\"9!\fAI\u0001\n\u0003Y\u0006b\u00024\u0001#\u0003%\ta\u001a\u0005\bS\u0002\t\n\u0011\"\u0001k\u0011\u001da\u0007!!A\u0005B5DqA\u001e\u0001\u0002\u0002\u0013\u0005q\u000fC\u0004y\u0001\u0005\u0005I\u0011A=\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0004\u0001\u0003\u0003%\t!!\u0005\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0011\u0001\u0005\u0005I\u0011IA\u0012\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002*\u0001\t\t\u0011\"\u0011\u0002,\u001dI\u0011qF\u000f\u0002\u0002#\u0005\u0011\u0011\u0007\u0004\t9u\t\t\u0011#\u0001\u00024!1qJ\u0006C\u0001\u0003\u0017B\u0011\"!\n\u0017\u0003\u0003%)%a\n\t\u0013\u00055c#!A\u0005\u0002\u0006=\u0003\"CA,-\u0005\u0005I\u0011QA-\u0011%\tYGFA\u0001\n\u0013\tiGA\u000bB]:|G/\u0019;fI^KG\u000f[*fY\u001a$\u0016\u0010]3\u000b\u0005yy\u0012\u0001C:dC2\f7/[4\u000b\u0005\u0001\n\u0013AB:dC2\f\u0007O\u0003\u0002#G\u00051!n]8oiMT\u0011\u0001J\u0001\u0004_J<7\u0001A\n\u0005\u0001\u001dZ\u0013\u0007\u0005\u0002)S5\tQ$\u0003\u0002+;\t!A+\u001f9f!\tas&D\u0001.\u0015\u0005q\u0013!B:dC2\f\u0017B\u0001\u0019.\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\r\u001e\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c&\u0003\u0019a$o\\8u}%\ta&\u0003\u0002:[\u00059\u0001/Y2lC\u001e,\u0017BA\u001e=\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tIT&A\u0004usB,'+\u001a4\u0016\u0003\u001d\n\u0001\u0002^=qKJ+g\rI\u0001\u0007gfl'm\u001c7\u0016\u0003\t\u0003\"\u0001K\"\n\u0005\u0011k\"AB*z[\n|G.A\u0004ts6\u0014w\u000e\u001c\u0011\u0002\u001d\u0005$HO]5c)J,WMU3ggV\t\u0001\nE\u00023\u0013.K!A\u0013\u001f\u0003\t1K7\u000f\u001e\t\u0003Y1K!!T\u0017\u0003\u0007%sG/A\bbiR\u0014\u0018N\u0019+sK\u0016\u0014VMZ:!\u0003\u0019a\u0014N\\5u}Q!\u0011KU*U!\tA\u0003\u0001C\u0003>\u000f\u0001\u0007q\u0005C\u0003A\u000f\u0001\u0007!\tC\u0003G\u000f\u0001\u0007\u0001*\u0001\u0003d_BLH\u0003B)X1fCq!\u0010\u0005\u0011\u0002\u0003\u0007q\u0005C\u0004A\u0011A\u0005\t\u0019\u0001\"\t\u000f\u0019C\u0001\u0013!a\u0001\u0011\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001/+\u0005\u001dj6&\u00010\u0011\u0005}#W\"\u00011\u000b\u0005\u0005\u0014\u0017!C;oG\",7m[3e\u0015\t\u0019W&\u0001\u0006b]:|G/\u0019;j_:L!!\u001a1\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003!T#AQ/\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\t1N\u000b\u0002I;\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001c\t\u0003_Rl\u0011\u0001\u001d\u0006\u0003cJ\fA\u0001\\1oO*\t1/\u0001\u0003kCZ\f\u0017BA;q\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t1*\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005il\bC\u0001\u0017|\u0013\taXFA\u0002B]fDqA \b\u0002\u0002\u0003\u00071*A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0007\u0001R!!\u0002\u0002\fil!!a\u0002\u000b\u0007\u0005%Q&\u0001\u0006d_2dWm\u0019;j_:LA!!\u0004\u0002\b\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019\"!\u0007\u0011\u00071\n)\"C\u0002\u0002\u00185\u0012qAQ8pY\u0016\fg\u000eC\u0004\u007f!\u0005\u0005\t\u0019\u0001>\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004]\u0006}\u0001b\u0002@\u0012\u0003\u0003\u0005\raS\u0001\tQ\u0006\u001c\bnQ8eKR\t1*\u0001\u0005u_N#(/\u001b8h)\u0005q\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0014\u00055\u0002b\u0002@\u0015\u0003\u0003\u0005\rA_\u0001\u0016\u0003:tw\u000e^1uK\u0012<\u0016\u000e\u001e5TK24G+\u001f9f!\tAccE\u0003\u0017\u0003k\t\t\u0005\u0005\u0005\u00028\u0005urE\u0011%R\u001b\t\tIDC\u0002\u0002<5\nqA];oi&lW-\u0003\u0003\u0002@\u0005e\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA!\u00111IA%\u001b\t\t)EC\u0002\u0002HI\f!![8\n\u0007m\n)\u0005\u0006\u0002\u00022\u0005)\u0011\r\u001d9msR9\u0011+!\u0015\u0002T\u0005U\u0003\"B\u001f\u001a\u0001\u00049\u0003\"\u0002!\u001a\u0001\u0004\u0011\u0005\"\u0002$\u001a\u0001\u0004A\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u00037\n9\u0007E\u0003-\u0003;\n\t'C\u0002\u0002`5\u0012aa\u00149uS>t\u0007C\u0002\u0017\u0002d\u001d\u0012\u0005*C\u0002\u0002f5\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CA55\u0005\u0005\t\u0019A)\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002pA\u0019q.!\u001d\n\u0007\u0005M\u0004O\u0001\u0004PE*,7\r\u001e"
)
public class AnnotatedWithSelfType extends Type implements Product, Serializable {
   private final Type typeRef;
   private final Symbol symbol;
   private final List attribTreeRefs;

   public static Option unapply(final AnnotatedWithSelfType x$0) {
      return AnnotatedWithSelfType$.MODULE$.unapply(x$0);
   }

   public static AnnotatedWithSelfType apply(final Type typeRef, final Symbol symbol, final List attribTreeRefs) {
      return AnnotatedWithSelfType$.MODULE$.apply(typeRef, symbol, attribTreeRefs);
   }

   public static Function1 tupled() {
      return AnnotatedWithSelfType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AnnotatedWithSelfType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type typeRef() {
      return this.typeRef;
   }

   public Symbol symbol() {
      return this.symbol;
   }

   public List attribTreeRefs() {
      return this.attribTreeRefs;
   }

   public AnnotatedWithSelfType copy(final Type typeRef, final Symbol symbol, final List attribTreeRefs) {
      return new AnnotatedWithSelfType(typeRef, symbol, attribTreeRefs);
   }

   public Type copy$default$1() {
      return this.typeRef();
   }

   public Symbol copy$default$2() {
      return this.symbol();
   }

   public List copy$default$3() {
      return this.attribTreeRefs();
   }

   public String productPrefix() {
      return "AnnotatedWithSelfType";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.typeRef();
            break;
         case 1:
            var10000 = this.symbol();
            break;
         case 2:
            var10000 = this.attribTreeRefs();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof AnnotatedWithSelfType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "typeRef";
            break;
         case 1:
            var10000 = "symbol";
            break;
         case 2:
            var10000 = "attribTreeRefs";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var11;
      if (this != x$1) {
         label72: {
            boolean var2;
            if (x$1 instanceof AnnotatedWithSelfType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label54: {
                  label63: {
                     AnnotatedWithSelfType var4 = (AnnotatedWithSelfType)x$1;
                     Type var10000 = this.typeRef();
                     Type var5 = var4.typeRef();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label63;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label63;
                     }

                     Symbol var8 = this.symbol();
                     Symbol var6 = var4.symbol();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label63;
                        }
                     } else if (!var8.equals(var6)) {
                        break label63;
                     }

                     List var9 = this.attribTreeRefs();
                     List var7 = var4.attribTreeRefs();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label63;
                        }
                     } else if (!var9.equals(var7)) {
                        break label63;
                     }

                     if (var4.canEqual(this)) {
                        var11 = true;
                        break label54;
                     }
                  }

                  var11 = false;
               }

               if (var11) {
                  break label72;
               }
            }

            var11 = false;
            return var11;
         }
      }

      var11 = true;
      return var11;
   }

   public AnnotatedWithSelfType(final Type typeRef, final Symbol symbol, final List attribTreeRefs) {
      this.typeRef = typeRef;
      this.symbol = symbol;
      this.attribTreeRefs = attribTreeRefs;
      Product.$init$(this);
   }
}
