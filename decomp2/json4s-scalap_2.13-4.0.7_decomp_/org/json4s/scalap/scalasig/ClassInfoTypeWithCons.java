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
   bytes = "\u0006\u0005\u0005\re\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005I\u0001\tE\t\u0015!\u0003F\u0011!I\u0005A!f\u0001\n\u0003Q\u0005\u0002C*\u0001\u0005#\u0005\u000b\u0011B&\t\u000bQ\u0003A\u0011A+\t\u000fi\u0003\u0011\u0011!C\u00017\"9q\fAI\u0001\n\u0003\u0001\u0007bB6\u0001#\u0003%\t\u0001\u001c\u0005\b]\u0002\t\n\u0011\"\u0001p\u0011\u001d\t\b!!A\u0005BIDqA\u001f\u0001\u0002\u0002\u0013\u00051\u0010\u0003\u0005\u0000\u0001\u0005\u0005I\u0011AA\u0001\u0011%\ti\u0001AA\u0001\n\u0003\ny\u0001C\u0005\u0002\u001e\u0001\t\t\u0011\"\u0001\u0002 !I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131\u0006\u0005\n\u0003_\u0001\u0011\u0011!C!\u0003cA\u0011\"a\r\u0001\u0003\u0003%\t%!\u000e\t\u0013\u0005]\u0002!!A\u0005B\u0005er!CA\u001f;\u0005\u0005\t\u0012AA \r!aR$!A\t\u0002\u0005\u0005\u0003B\u0002+\u0017\t\u0003\tI\u0006C\u0005\u00024Y\t\t\u0011\"\u0012\u00026!I\u00111\f\f\u0002\u0002\u0013\u0005\u0015Q\f\u0005\n\u0003K2\u0012\u0011!CA\u0003OB\u0011\"!\u001f\u0017\u0003\u0003%I!a\u001f\u0003+\rc\u0017m]:J]\u001a|G+\u001f9f/&$\bnQ8og*\u0011adH\u0001\tg\u000e\fG.Y:jO*\u0011\u0001%I\u0001\u0007g\u000e\fG.\u00199\u000b\u0005\t\u001a\u0013A\u00026t_:$4OC\u0001%\u0003\ry'oZ\u0002\u0001'\u0011\u0001qeK\u0019\u0011\u0005!JS\"A\u000f\n\u0005)j\"\u0001\u0002+za\u0016\u0004\"\u0001L\u0018\u000e\u00035R\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003a5\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00023u9\u00111\u0007\u000f\b\u0003i]j\u0011!\u000e\u0006\u0003m\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0018\n\u0005ej\u0013a\u00029bG.\fw-Z\u0005\u0003wq\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!O\u0017\u0002\rMLXNY8m+\u0005y\u0004C\u0001\u0015A\u0013\t\tUD\u0001\u0004Ts6\u0014w\u000e\\\u0001\bgfl'm\u001c7!\u0003!!\u0018\u0010]3SK\u001a\u001cX#A#\u0011\u0007I2u%\u0003\u0002Hy\t\u00191+Z9\u0002\u0013QL\b/\u001a*fMN\u0004\u0013\u0001B2p]N,\u0012a\u0013\t\u0003\u0019Bs!!\u0014(\u0011\u0005Qj\u0013BA(.\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011K\u0015\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005=k\u0013!B2p]N\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003W/bK\u0006C\u0001\u0015\u0001\u0011\u0015it\u00011\u0001@\u0011\u0015\u0019u\u00011\u0001F\u0011\u0015Iu\u00011\u0001L\u0003\u0011\u0019w\u000e]=\u0015\tYcVL\u0018\u0005\b{!\u0001\n\u00111\u0001@\u0011\u001d\u0019\u0005\u0002%AA\u0002\u0015Cq!\u0013\u0005\u0011\u0002\u0003\u00071*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u0005T#a\u00102,\u0003\r\u0004\"\u0001Z5\u000e\u0003\u0015T!AZ4\u0002\u0013Ut7\r[3dW\u0016$'B\u00015.\u0003)\tgN\\8uCRLwN\\\u0005\u0003U\u0016\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012!\u001c\u0016\u0003\u000b\n\fabY8qs\u0012\"WMZ1vYR$3'F\u0001qU\tY%-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002gB\u0011A/_\u0007\u0002k*\u0011ao^\u0001\u0005Y\u0006twMC\u0001y\u0003\u0011Q\u0017M^1\n\u0005E+\u0018\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001?\u0011\u00051j\u0018B\u0001@.\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\u0019!!\u0003\u0011\u00071\n)!C\u0002\u0002\b5\u00121!\u00118z\u0011!\tYADA\u0001\u0002\u0004a\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0012A1\u00111CA\r\u0003\u0007i!!!\u0006\u000b\u0007\u0005]Q&\u0001\u0006d_2dWm\u0019;j_:LA!a\u0007\u0002\u0016\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\t#a\n\u0011\u00071\n\u0019#C\u0002\u0002&5\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\fA\t\t\u00111\u0001\u0002\u0004\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\u0019\u0018Q\u0006\u0005\t\u0003\u0017\t\u0012\u0011!a\u0001y\u0006A\u0001.Y:i\u0007>$W\rF\u0001}\u0003!!xn\u0015;sS:<G#A:\u0002\r\u0015\fX/\u00197t)\u0011\t\t#a\u000f\t\u0013\u0005-A#!AA\u0002\u0005\r\u0011!F\"mCN\u001c\u0018J\u001c4p)f\u0004XmV5uQ\u000e{gn\u001d\t\u0003QY\u0019RAFA\"\u0003\u001f\u0002\u0002\"!\u0012\u0002L}*5JV\u0007\u0003\u0003\u000fR1!!\u0013.\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0014\u0002H\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005E\u0013qK\u0007\u0003\u0003'R1!!\u0016x\u0003\tIw.C\u0002<\u0003'\"\"!a\u0010\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fY\u000by&!\u0019\u0002d!)Q(\u0007a\u0001\u007f!)1)\u0007a\u0001\u000b\")\u0011*\u0007a\u0001\u0017\u00069QO\\1qa2LH\u0003BA5\u0003k\u0002R\u0001LA6\u0003_J1!!\u001c.\u0005\u0019y\u0005\u000f^5p]B1A&!\u001d@\u000b.K1!a\u001d.\u0005\u0019!V\u000f\u001d7fg!A\u0011q\u000f\u000e\u0002\u0002\u0003\u0007a+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!! \u0011\u0007Q\fy(C\u0002\u0002\u0002V\u0014aa\u00142kK\u000e$\b"
)
public class ClassInfoTypeWithCons extends Type implements Product, Serializable {
   private final Symbol symbol;
   private final Seq typeRefs;
   private final String cons;

   public static Option unapply(final ClassInfoTypeWithCons x$0) {
      return ClassInfoTypeWithCons$.MODULE$.unapply(x$0);
   }

   public static ClassInfoTypeWithCons apply(final Symbol symbol, final Seq typeRefs, final String cons) {
      return ClassInfoTypeWithCons$.MODULE$.apply(symbol, typeRefs, cons);
   }

   public static Function1 tupled() {
      return ClassInfoTypeWithCons$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ClassInfoTypeWithCons$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Symbol symbol() {
      return this.symbol;
   }

   public Seq typeRefs() {
      return this.typeRefs;
   }

   public String cons() {
      return this.cons;
   }

   public ClassInfoTypeWithCons copy(final Symbol symbol, final Seq typeRefs, final String cons) {
      return new ClassInfoTypeWithCons(symbol, typeRefs, cons);
   }

   public Symbol copy$default$1() {
      return this.symbol();
   }

   public Seq copy$default$2() {
      return this.typeRefs();
   }

   public String copy$default$3() {
      return this.cons();
   }

   public String productPrefix() {
      return "ClassInfoTypeWithCons";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.symbol();
            break;
         case 1:
            var10000 = this.typeRefs();
            break;
         case 2:
            var10000 = this.cons();
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
      return x$1 instanceof ClassInfoTypeWithCons;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "symbol";
            break;
         case 1:
            var10000 = "typeRefs";
            break;
         case 2:
            var10000 = "cons";
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
            if (x$1 instanceof ClassInfoTypeWithCons) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label54: {
                  label63: {
                     ClassInfoTypeWithCons var4 = (ClassInfoTypeWithCons)x$1;
                     Symbol var10000 = this.symbol();
                     Symbol var5 = var4.symbol();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label63;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label63;
                     }

                     Seq var8 = this.typeRefs();
                     Seq var6 = var4.typeRefs();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label63;
                        }
                     } else if (!var8.equals(var6)) {
                        break label63;
                     }

                     String var9 = this.cons();
                     String var7 = var4.cons();
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

   public ClassInfoTypeWithCons(final Symbol symbol, final Seq typeRefs, final String cons) {
      this.symbol = symbol;
      this.typeRefs = typeRefs;
      this.cons = cons;
      Product.$init$(this);
   }
}
