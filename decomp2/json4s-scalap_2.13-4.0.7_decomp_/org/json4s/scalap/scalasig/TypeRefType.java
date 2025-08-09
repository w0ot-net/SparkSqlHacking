package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005O!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005F\u0001\tE\t\u0015!\u0003C\u0011!1\u0005A!f\u0001\n\u00039\u0005\u0002C&\u0001\u0005#\u0005\u000b\u0011\u0002%\t\u000b1\u0003A\u0011A'\t\u000fI\u0003\u0011\u0011!C\u0001'\"9q\u000bAI\u0001\n\u0003A\u0006bB2\u0001#\u0003%\t\u0001\u001a\u0005\bM\u0002\t\n\u0011\"\u0001h\u0011\u001dI\u0007!!A\u0005B)Dqa\u001d\u0001\u0002\u0002\u0013\u0005A\u000fC\u0004y\u0001\u0005\u0005I\u0011A=\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0004\u0001\u0003\u0003%\t!!\u0005\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0011\u0001\u0005\u0005I\u0011IA\u0012\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002*\u0001\t\t\u0011\"\u0011\u0002,\u001dI\u0011qF\u000f\u0002\u0002#\u0005\u0011\u0011\u0007\u0004\t9u\t\t\u0011#\u0001\u00024!1AJ\u0006C\u0001\u0003\u0017B\u0011\"!\n\u0017\u0003\u0003%)%a\n\t\u0013\u00055c#!A\u0005\u0002\u0006=\u0003\"CA,-\u0005\u0005I\u0011QA-\u0011%\tYGFA\u0001\n\u0013\tiGA\u0006UsB,'+\u001a4UsB,'B\u0001\u0010 \u0003!\u00198-\u00197bg&<'B\u0001\u0011\"\u0003\u0019\u00198-\u00197ba*\u0011!eI\u0001\u0007UN|g\u000eN:\u000b\u0003\u0011\n1a\u001c:h\u0007\u0001\u0019B\u0001A\u0014,cA\u0011\u0001&K\u0007\u0002;%\u0011!&\b\u0002\u0005)f\u0004X\r\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TFA\u0004Qe>$Wo\u0019;\u0011\u0005IRdBA\u001a9\u001d\t!t'D\u00016\u0015\t1T%\u0001\u0004=e>|GOP\u0005\u0002]%\u0011\u0011(L\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:[\u00051\u0001O]3gSb,\u0012aJ\u0001\baJ,g-\u001b=!\u0003\u0019\u0019\u00180\u001c2pYV\t!\t\u0005\u0002)\u0007&\u0011A)\b\u0002\u0007'fl'm\u001c7\u0002\u000fMLXNY8mA\u0005AA/\u001f9f\u0003J<7/F\u0001I!\r\u0011\u0014jJ\u0005\u0003\u0015r\u00121aU3r\u0003%!\u0018\u0010]3Be\u001e\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0005\u001d>\u0003\u0016\u000b\u0005\u0002)\u0001!)Qh\u0002a\u0001O!)\u0001i\u0002a\u0001\u0005\")ai\u0002a\u0001\u0011\u0006!1m\u001c9z)\u0011qE+\u0016,\t\u000fuB\u0001\u0013!a\u0001O!9\u0001\t\u0003I\u0001\u0002\u0004\u0011\u0005b\u0002$\t!\u0003\u0005\r\u0001S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005I&FA\u0014[W\u0005Y\u0006C\u0001/b\u001b\u0005i&B\u00010`\u0003%)hn\u00195fG.,GM\u0003\u0002a[\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\tl&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A3+\u0005\tS\u0016AD2paf$C-\u001a4bk2$HeM\u000b\u0002Q*\u0012\u0001JW\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003-\u0004\"\u0001\\9\u000e\u00035T!A\\8\u0002\t1\fgn\u001a\u0006\u0002a\u0006!!.\u0019<b\u0013\t\u0011XN\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002kB\u0011AF^\u0005\u0003o6\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A_?\u0011\u00051Z\u0018B\u0001?.\u0005\r\te.\u001f\u0005\b}:\t\t\u00111\u0001v\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u0001\t\u0006\u0003\u000b\tYA_\u0007\u0003\u0003\u000fQ1!!\u0003.\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u001b\t9A\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\n\u00033\u00012\u0001LA\u000b\u0013\r\t9\"\f\u0002\b\u0005>|G.Z1o\u0011\u001dq\b#!AA\u0002i\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u00191.a\b\t\u000fy\f\u0012\u0011!a\u0001k\u0006A\u0001.Y:i\u0007>$W\rF\u0001v\u0003!!xn\u0015;sS:<G#A6\u0002\r\u0015\fX/\u00197t)\u0011\t\u0019\"!\f\t\u000fy$\u0012\u0011!a\u0001u\u0006YA+\u001f9f%\u00164G+\u001f9f!\tAccE\u0003\u0017\u0003k\t\t\u0005\u0005\u0005\u00028\u0005urE\u0011%O\u001b\t\tIDC\u0002\u0002<5\nqA];oi&lW-\u0003\u0003\u0002@\u0005e\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA!\u00111IA%\u001b\t\t)EC\u0002\u0002H=\f!![8\n\u0007m\n)\u0005\u0006\u0002\u00022\u0005)\u0011\r\u001d9msR9a*!\u0015\u0002T\u0005U\u0003\"B\u001f\u001a\u0001\u00049\u0003\"\u0002!\u001a\u0001\u0004\u0011\u0005\"\u0002$\u001a\u0001\u0004A\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u00037\n9\u0007E\u0003-\u0003;\n\t'C\u0002\u0002`5\u0012aa\u00149uS>t\u0007C\u0002\u0017\u0002d\u001d\u0012\u0005*C\u0002\u0002f5\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CA55\u0005\u0005\t\u0019\u0001(\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002pA\u0019A.!\u001d\n\u0007\u0005MTN\u0001\u0004PE*,7\r\u001e"
)
public class TypeRefType extends Type implements Product, Serializable {
   private final Type prefix;
   private final Symbol symbol;
   private final Seq typeArgs;

   public static Option unapply(final TypeRefType x$0) {
      return TypeRefType$.MODULE$.unapply(x$0);
   }

   public static TypeRefType apply(final Type prefix, final Symbol symbol, final Seq typeArgs) {
      return TypeRefType$.MODULE$.apply(prefix, symbol, typeArgs);
   }

   public static Function1 tupled() {
      return TypeRefType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return TypeRefType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type prefix() {
      return this.prefix;
   }

   public Symbol symbol() {
      return this.symbol;
   }

   public Seq typeArgs() {
      return this.typeArgs;
   }

   public TypeRefType copy(final Type prefix, final Symbol symbol, final Seq typeArgs) {
      return new TypeRefType(prefix, symbol, typeArgs);
   }

   public Type copy$default$1() {
      return this.prefix();
   }

   public Symbol copy$default$2() {
      return this.symbol();
   }

   public Seq copy$default$3() {
      return this.typeArgs();
   }

   public String productPrefix() {
      return "TypeRefType";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.prefix();
            break;
         case 1:
            var10000 = this.symbol();
            break;
         case 2:
            var10000 = this.typeArgs();
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
      return x$1 instanceof TypeRefType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "prefix";
            break;
         case 1:
            var10000 = "symbol";
            break;
         case 2:
            var10000 = "typeArgs";
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
            if (x$1 instanceof TypeRefType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label54: {
                  label63: {
                     TypeRefType var4 = (TypeRefType)x$1;
                     Type var10000 = this.prefix();
                     Type var5 = var4.prefix();
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

                     Seq var9 = this.typeArgs();
                     Seq var7 = var4.typeArgs();
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

   public TypeRefType(final Type prefix, final Symbol symbol, final Seq typeArgs) {
      this.prefix = prefix;
      this.symbol = symbol;
      this.typeArgs = typeArgs;
      Product.$init$(this);
   }
}
