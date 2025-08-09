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
   bytes = "\u0006\u0005\u0005\re\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005O!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005I\u0001\tE\t\u0015!\u0003C\u0011!I\u0005A!f\u0001\n\u0003Q\u0005\u0002C*\u0001\u0005#\u0005\u000b\u0011B&\t\u000bQ\u0003A\u0011A+\t\u000fi\u0003\u0011\u0011!C\u00017\"9q\fAI\u0001\n\u0003\u0001\u0007bB6\u0001#\u0003%\t\u0001\u001c\u0005\b]\u0002\t\n\u0011\"\u0001p\u0011\u001d\t\b!!A\u0005BIDqA\u001f\u0001\u0002\u0002\u0013\u00051\u0010\u0003\u0005\u0000\u0001\u0005\u0005I\u0011AA\u0001\u0011%\ti\u0001AA\u0001\n\u0003\ny\u0001C\u0005\u0002\u001e\u0001\t\t\u0011\"\u0001\u0002 !I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131\u0006\u0005\n\u0003_\u0001\u0011\u0011!C!\u0003cA\u0011\"a\r\u0001\u0003\u0003%\t%!\u000e\t\u0013\u0005]\u0002!!A\u0005B\u0005er!CA\u001f;\u0005\u0005\t\u0012AA \r!aR$!A\t\u0002\u0005\u0005\u0003B\u0002+\u0017\t\u0003\tI\u0006C\u0005\u00024Y\t\t\u0011\"\u0012\u00026!I\u00111\f\f\u0002\u0002\u0013\u0005\u0015Q\f\u0005\n\u0003K2\u0012\u0011!CA\u0003OB\u0011\"!\u001f\u0017\u0003\u0003%I!a\u001f\u0003!A{G.\u001f+za\u0016<\u0016\u000e\u001e5D_:\u001c(B\u0001\u0010 \u0003!\u00198-\u00197bg&<'B\u0001\u0011\"\u0003\u0019\u00198-\u00197ba*\u0011!eI\u0001\u0007UN|g\u000eN:\u000b\u0003\u0011\n1a\u001c:h\u0007\u0001\u0019B\u0001A\u0014,cA\u0011\u0001&K\u0007\u0002;%\u0011!&\b\u0002\u0005)f\u0004X\r\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TFA\u0004Qe>$Wo\u0019;\u0011\u0005IRdBA\u001a9\u001d\t!t'D\u00016\u0015\t1T%\u0001\u0004=e>|GOP\u0005\u0002]%\u0011\u0011(L\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:[\u00059A/\u001f9f%\u00164W#A\u0014\u0002\u0011QL\b/\u001a*fM\u0002\nqa]=nE>d7/F\u0001C!\r\u00114)R\u0005\u0003\tr\u00121aU3r!\tAc)\u0003\u0002H;\tQA+\u001f9f'fl'm\u001c7\u0002\u0011MLXNY8mg\u0002\nAaY8ogV\t1\n\u0005\u0002M!:\u0011QJ\u0014\t\u0003i5J!aT\u0017\u0002\rA\u0013X\rZ3g\u0013\t\t&K\u0001\u0004TiJLgn\u001a\u0006\u0003\u001f6\nQaY8og\u0002\na\u0001P5oSRtD\u0003\u0002,X1f\u0003\"\u0001\u000b\u0001\t\u000bu:\u0001\u0019A\u0014\t\u000b\u0001;\u0001\u0019\u0001\"\t\u000b%;\u0001\u0019A&\u0002\t\r|\u0007/\u001f\u000b\u0005-rkf\fC\u0004>\u0011A\u0005\t\u0019A\u0014\t\u000f\u0001C\u0001\u0013!a\u0001\u0005\"9\u0011\n\u0003I\u0001\u0002\u0004Y\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002C*\u0012qEY\u0016\u0002GB\u0011A-[\u0007\u0002K*\u0011amZ\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001[\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002kK\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tQN\u000b\u0002CE\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT#\u00019+\u0005-\u0013\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001t!\t!\u00180D\u0001v\u0015\t1x/\u0001\u0003mC:<'\"\u0001=\u0002\t)\fg/Y\u0005\u0003#V\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012\u0001 \t\u0003YuL!A`\u0017\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\r\u0011\u0011\u0002\t\u0004Y\u0005\u0015\u0011bAA\u0004[\t\u0019\u0011I\\=\t\u0011\u0005-a\"!AA\u0002q\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\t!\u0019\t\u0019\"!\u0007\u0002\u00045\u0011\u0011Q\u0003\u0006\u0004\u0003/i\u0013AC2pY2,7\r^5p]&!\u00111DA\u000b\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0005\u0012q\u0005\t\u0004Y\u0005\r\u0012bAA\u0013[\t9!i\\8mK\u0006t\u0007\"CA\u0006!\u0005\u0005\t\u0019AA\u0002\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007M\fi\u0003\u0003\u0005\u0002\fE\t\t\u00111\u0001}\u0003!A\u0017m\u001d5D_\u0012,G#\u0001?\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a]\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0005\u00121\b\u0005\n\u0003\u0017!\u0012\u0011!a\u0001\u0003\u0007\t\u0001\u0003U8msRK\b/Z,ji\"\u001cuN\\:\u0011\u0005!22#\u0002\f\u0002D\u0005=\u0003\u0003CA#\u0003\u0017:#i\u0013,\u000e\u0005\u0005\u001d#bAA%[\u00059!/\u001e8uS6,\u0017\u0002BA'\u0003\u000f\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\t\t&a\u0016\u000e\u0005\u0005M#bAA+o\u0006\u0011\u0011n\\\u0005\u0004w\u0005MCCAA \u0003\u0015\t\u0007\u000f\u001d7z)\u001d1\u0016qLA1\u0003GBQ!P\rA\u0002\u001dBQ\u0001Q\rA\u0002\tCQ!S\rA\u0002-\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002j\u0005U\u0004#\u0002\u0017\u0002l\u0005=\u0014bAA7[\t1q\n\u001d;j_:\u0004b\u0001LA9O\t[\u0015bAA:[\t1A+\u001e9mKNB\u0001\"a\u001e\u001b\u0003\u0003\u0005\rAV\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA?!\r!\u0018qP\u0005\u0004\u0003\u0003+(AB(cU\u0016\u001cG\u000f"
)
public class PolyTypeWithCons extends Type implements Product, Serializable {
   private final Type typeRef;
   private final Seq symbols;
   private final String cons;

   public static Option unapply(final PolyTypeWithCons x$0) {
      return PolyTypeWithCons$.MODULE$.unapply(x$0);
   }

   public static PolyTypeWithCons apply(final Type typeRef, final Seq symbols, final String cons) {
      return PolyTypeWithCons$.MODULE$.apply(typeRef, symbols, cons);
   }

   public static Function1 tupled() {
      return PolyTypeWithCons$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PolyTypeWithCons$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type typeRef() {
      return this.typeRef;
   }

   public Seq symbols() {
      return this.symbols;
   }

   public String cons() {
      return this.cons;
   }

   public PolyTypeWithCons copy(final Type typeRef, final Seq symbols, final String cons) {
      return new PolyTypeWithCons(typeRef, symbols, cons);
   }

   public Type copy$default$1() {
      return this.typeRef();
   }

   public Seq copy$default$2() {
      return this.symbols();
   }

   public String copy$default$3() {
      return this.cons();
   }

   public String productPrefix() {
      return "PolyTypeWithCons";
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
            var10000 = this.symbols();
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
      return x$1 instanceof PolyTypeWithCons;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "typeRef";
            break;
         case 1:
            var10000 = "symbols";
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
            if (x$1 instanceof PolyTypeWithCons) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label54: {
                  label63: {
                     PolyTypeWithCons var4 = (PolyTypeWithCons)x$1;
                     Type var10000 = this.typeRef();
                     Type var5 = var4.typeRef();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label63;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label63;
                     }

                     Seq var8 = this.symbols();
                     Seq var6 = var4.symbols();
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

   public PolyTypeWithCons(final Type typeRef, final Seq symbols, final String cons) {
      this.typeRef = typeRef;
      this.symbols = symbols;
      this.cons = cons;
      Product.$init$(this);
   }
}
