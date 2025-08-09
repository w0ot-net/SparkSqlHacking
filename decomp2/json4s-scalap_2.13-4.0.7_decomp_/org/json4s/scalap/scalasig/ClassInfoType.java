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
   bytes = "\u0006\u0005\u0005uc\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005F\u0001\tE\t\u0015!\u0003C\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001dY\u0005!!A\u0005\u00021Cqa\u0014\u0001\u0012\u0002\u0013\u0005\u0001\u000bC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000fy\u0003\u0011\u0011!C!?\"9\u0001\u000eAA\u0001\n\u0003I\u0007bB7\u0001\u0003\u0003%\tA\u001c\u0005\bi\u0002\t\t\u0011\"\u0011v\u0011\u001da\b!!A\u0005\u0002uD\u0011\"!\u0002\u0001\u0003\u0003%\t%a\u0002\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\b\u0001\u0005\u0005I\u0011IA\t\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)bB\u0005\u0002\u001ai\t\t\u0011#\u0001\u0002\u001c\u0019A\u0011DGA\u0001\u0012\u0003\ti\u0002\u0003\u0004G'\u0011\u0005\u0011Q\u0007\u0005\n\u0003\u001f\u0019\u0012\u0011!C#\u0003#A\u0011\"a\u000e\u0014\u0003\u0003%\t)!\u000f\t\u0013\u0005}2#!A\u0005\u0002\u0006\u0005\u0003\"CA*'\u0005\u0005I\u0011BA+\u00055\u0019E.Y:t\u0013:4w\u000eV=qK*\u00111\u0004H\u0001\tg\u000e\fG.Y:jO*\u0011QDH\u0001\u0007g\u000e\fG.\u00199\u000b\u0005}\u0001\u0013A\u00026t_:$4OC\u0001\"\u0003\ry'oZ\u0002\u0001'\u0011\u0001A\u0005\u000b\u0018\u0011\u0005\u00152S\"\u0001\u000e\n\u0005\u001dR\"\u0001\u0002+za\u0016\u0004\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00020o9\u0011\u0001'\u000e\b\u0003cQj\u0011A\r\u0006\u0003g\t\na\u0001\u0010:p_Rt\u0014\"A\u0016\n\u0005YR\u0013a\u00029bG.\fw-Z\u0005\u0003qe\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\u000e\u0016\u0002\rMLXNY8m+\u0005a\u0004CA\u0013>\u0013\tq$D\u0001\u0004Ts6\u0014w\u000e\\\u0001\bgfl'm\u001c7!\u0003!!\u0018\u0010]3SK\u001a\u001cX#\u0001\"\u0011\u0007=\u001aE%\u0003\u0002Es\t\u00191+Z9\u0002\u0013QL\b/\u001a*fMN\u0004\u0013A\u0002\u001fj]&$h\bF\u0002I\u0013*\u0003\"!\n\u0001\t\u000bi*\u0001\u0019\u0001\u001f\t\u000b\u0001+\u0001\u0019\u0001\"\u0002\t\r|\u0007/\u001f\u000b\u0004\u00116s\u0005b\u0002\u001e\u0007!\u0003\u0005\r\u0001\u0010\u0005\b\u0001\u001a\u0001\n\u00111\u0001C\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0015\u0016\u0003yI[\u0013a\u0015\t\u0003)fk\u0011!\u0016\u0006\u0003-^\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005aS\u0013AC1o]>$\u0018\r^5p]&\u0011!,\u0016\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002;*\u0012!IU\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0001\u0004\"!\u00194\u000e\u0003\tT!a\u00193\u0002\t1\fgn\u001a\u0006\u0002K\u0006!!.\u0019<b\u0013\t9'M\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002UB\u0011\u0011f[\u0005\u0003Y*\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u001c:\u0011\u0005%\u0002\u0018BA9+\u0005\r\te.\u001f\u0005\bg.\t\t\u00111\u0001k\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\ta\u000fE\u0002xu>l\u0011\u0001\u001f\u0006\u0003s*\n!bY8mY\u0016\u001cG/[8o\u0013\tY\bP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001@\u0002\u0004A\u0011\u0011f`\u0005\u0004\u0003\u0003Q#a\u0002\"p_2,\u0017M\u001c\u0005\bg6\t\t\u00111\u0001p\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007\u0001\fI\u0001C\u0004t\u001d\u0005\u0005\t\u0019\u00016\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A[\u0001\ti>\u001cFO]5oOR\t\u0001-\u0001\u0004fcV\fGn\u001d\u000b\u0004}\u0006]\u0001bB:\u0012\u0003\u0003\u0005\ra\\\u0001\u000e\u00072\f7o]%oM>$\u0016\u0010]3\u0011\u0005\u0015\u001a2#B\n\u0002 \u0005-\u0002cBA\u0011\u0003Oa$\tS\u0007\u0003\u0003GQ1!!\n+\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u000b\u0002$\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u00055\u00121G\u0007\u0003\u0003_Q1!!\re\u0003\tIw.C\u00029\u0003_!\"!a\u0007\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b!\u000bY$!\u0010\t\u000bi2\u0002\u0019\u0001\u001f\t\u000b\u00013\u0002\u0019\u0001\"\u0002\u000fUt\u0017\r\u001d9msR!\u00111IA(!\u0015I\u0013QIA%\u0013\r\t9E\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b%\nY\u0005\u0010\"\n\u0007\u00055#F\u0001\u0004UkBdWM\r\u0005\t\u0003#:\u0012\u0011!a\u0001\u0011\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0003cA1\u0002Z%\u0019\u00111\f2\u0003\r=\u0013'.Z2u\u0001"
)
public class ClassInfoType extends Type implements Product, Serializable {
   private final Symbol symbol;
   private final Seq typeRefs;

   public static Option unapply(final ClassInfoType x$0) {
      return ClassInfoType$.MODULE$.unapply(x$0);
   }

   public static ClassInfoType apply(final Symbol symbol, final Seq typeRefs) {
      return ClassInfoType$.MODULE$.apply(symbol, typeRefs);
   }

   public static Function1 tupled() {
      return ClassInfoType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ClassInfoType$.MODULE$.curried();
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

   public ClassInfoType copy(final Symbol symbol, final Seq typeRefs) {
      return new ClassInfoType(symbol, typeRefs);
   }

   public Symbol copy$default$1() {
      return this.symbol();
   }

   public Seq copy$default$2() {
      return this.typeRefs();
   }

   public String productPrefix() {
      return "ClassInfoType";
   }

   public int productArity() {
      return 2;
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
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ClassInfoType;
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
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof ClassInfoType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     ClassInfoType var4 = (ClassInfoType)x$1;
                     Symbol var10000 = this.symbol();
                     Symbol var5 = var4.symbol();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Seq var7 = this.typeRefs();
                     Seq var6 = var4.typeRefs();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public ClassInfoType(final Symbol symbol, final Seq typeRefs) {
      this.symbol = symbol;
      this.typeRefs = typeRefs;
      Product.$init$(this);
   }
}
