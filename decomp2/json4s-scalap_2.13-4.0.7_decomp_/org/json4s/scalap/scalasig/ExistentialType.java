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
   bytes = "\u0006\u0005\u0005uc\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005I!AQ\b\u0001BK\u0002\u0013\u0005a\b\u0003\u0005F\u0001\tE\t\u0015!\u0003@\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001dY\u0005!!A\u0005\u00021Cqa\u0014\u0001\u0012\u0002\u0013\u0005\u0001\u000bC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000fy\u0003\u0011\u0011!C!?\"9\u0001\u000eAA\u0001\n\u0003I\u0007bB7\u0001\u0003\u0003%\tA\u001c\u0005\bi\u0002\t\t\u0011\"\u0011v\u0011\u001da\b!!A\u0005\u0002uD\u0011\"!\u0002\u0001\u0003\u0003%\t%a\u0002\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\b\u0001\u0005\u0005I\u0011IA\t\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)bB\u0005\u0002\u001ai\t\t\u0011#\u0001\u0002\u001c\u0019A\u0011DGA\u0001\u0012\u0003\ti\u0002\u0003\u0004G'\u0011\u0005\u0011Q\u0007\u0005\n\u0003\u001f\u0019\u0012\u0011!C#\u0003#A\u0011\"a\u000e\u0014\u0003\u0003%\t)!\u000f\t\u0013\u0005}2#!A\u0005\u0002\u0006\u0005\u0003\"CA*'\u0005\u0005I\u0011BA+\u0005=)\u00050[:uK:$\u0018.\u00197UsB,'BA\u000e\u001d\u0003!\u00198-\u00197bg&<'BA\u000f\u001f\u0003\u0019\u00198-\u00197ba*\u0011q\u0004I\u0001\u0007UN|g\u000eN:\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0013)]A\u0011QEJ\u0007\u00025%\u0011qE\u0007\u0002\u0005)f\u0004X\r\u0005\u0002*Y5\t!FC\u0001,\u0003\u0015\u00198-\u00197b\u0013\ti#FA\u0004Qe>$Wo\u0019;\u0011\u0005=:dB\u0001\u00196\u001d\t\tD'D\u00013\u0015\t\u0019$%\u0001\u0004=e>|GOP\u0005\u0002W%\u0011aGK\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0014H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00027U\u00059A/\u001f9f%\u00164W#\u0001\u0013\u0002\u0011QL\b/\u001a*fM\u0002\nqa]=nE>d7/F\u0001@!\ry\u0003IQ\u0005\u0003\u0003f\u00121aU3r!\t)3)\u0003\u0002E5\t11+_7c_2\f\u0001b]=nE>d7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007!K%\n\u0005\u0002&\u0001!)!(\u0002a\u0001I!)Q(\u0002a\u0001\u007f\u0005!1m\u001c9z)\rAUJ\u0014\u0005\bu\u0019\u0001\n\u00111\u0001%\u0011\u001did\u0001%AA\u0002}\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001RU\t!#kK\u0001T!\t!\u0016,D\u0001V\u0015\t1v+A\u0005v]\u000eDWmY6fI*\u0011\u0001LK\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001.V\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005i&FA S\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0001\r\u0005\u0002bM6\t!M\u0003\u0002dI\u0006!A.\u00198h\u0015\u0005)\u0017\u0001\u00026bm\u0006L!a\u001a2\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Q\u0007CA\u0015l\u0013\ta'FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002peB\u0011\u0011\u0006]\u0005\u0003c*\u00121!\u00118z\u0011\u001d\u00198\"!AA\u0002)\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001<\u0011\u0007]Tx.D\u0001y\u0015\tI(&\u0001\u0006d_2dWm\u0019;j_:L!a\u001f=\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004}\u0006\r\u0001CA\u0015\u0000\u0013\r\t\tA\u000b\u0002\b\u0005>|G.Z1o\u0011\u001d\u0019X\"!AA\u0002=\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0001-!\u0003\t\u000fMt\u0011\u0011!a\u0001U\u0006A\u0001.Y:i\u0007>$W\rF\u0001k\u0003!!xn\u0015;sS:<G#\u00011\u0002\r\u0015\fX/\u00197t)\rq\u0018q\u0003\u0005\bgF\t\t\u00111\u0001p\u0003=)\u00050[:uK:$\u0018.\u00197UsB,\u0007CA\u0013\u0014'\u0015\u0019\u0012qDA\u0016!\u001d\t\t#a\n%\u007f!k!!a\t\u000b\u0007\u0005\u0015\"&A\u0004sk:$\u0018.\\3\n\t\u0005%\u00121\u0005\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u0017\u0003gi!!a\f\u000b\u0007\u0005EB-\u0001\u0002j_&\u0019\u0001(a\f\u0015\u0005\u0005m\u0011!B1qa2LH#\u0002%\u0002<\u0005u\u0002\"\u0002\u001e\u0017\u0001\u0004!\u0003\"B\u001f\u0017\u0001\u0004y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0007\ny\u0005E\u0003*\u0003\u000b\nI%C\u0002\u0002H)\u0012aa\u00149uS>t\u0007#B\u0015\u0002L\u0011z\u0014bAA'U\t1A+\u001e9mKJB\u0001\"!\u0015\u0018\u0003\u0003\u0005\r\u0001S\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA,!\r\t\u0017\u0011L\u0005\u0004\u00037\u0012'AB(cU\u0016\u001cG\u000f"
)
public class ExistentialType extends Type implements Product, Serializable {
   private final Type typeRef;
   private final Seq symbols;

   public static Option unapply(final ExistentialType x$0) {
      return ExistentialType$.MODULE$.unapply(x$0);
   }

   public static ExistentialType apply(final Type typeRef, final Seq symbols) {
      return ExistentialType$.MODULE$.apply(typeRef, symbols);
   }

   public static Function1 tupled() {
      return ExistentialType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExistentialType$.MODULE$.curried();
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

   public ExistentialType copy(final Type typeRef, final Seq symbols) {
      return new ExistentialType(typeRef, symbols);
   }

   public Type copy$default$1() {
      return this.typeRef();
   }

   public Seq copy$default$2() {
      return this.symbols();
   }

   public String productPrefix() {
      return "ExistentialType";
   }

   public int productArity() {
      return 2;
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
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ExistentialType;
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
            if (x$1 instanceof ExistentialType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     ExistentialType var4 = (ExistentialType)x$1;
                     Type var10000 = this.typeRef();
                     Type var5 = var4.typeRef();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Seq var7 = this.symbols();
                     Seq var6 = var4.symbols();
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

   public ExistentialType(final Type typeRef, final Seq symbols) {
      this.typeRef = typeRef;
      this.symbols = symbols;
      Product.$init$(this);
   }
}
