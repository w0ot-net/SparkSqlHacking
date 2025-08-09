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
   bytes = "\u0006\u0005\u0005-f\u0001B\u0010!\u0001&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0003\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005L\u0001\tE\t\u0015!\u0003I\u0011!a\u0005A!f\u0001\n\u0003i\u0005\u0002\u0003+\u0001\u0005#\u0005\u000b\u0011\u0002(\t\u0011U\u0003!Q3A\u0005\u0002YC\u0001B\u001a\u0001\u0003\u0012\u0003\u0006Ia\u0016\u0005\u0006O\u0002!\t\u0001\u001b\u0005\b]\u0002\t\t\u0011\"\u0001p\u0011\u001d!\b!%A\u0005\u0002UD\u0011\"!\u0001\u0001#\u0003%\t!a\u0001\t\u0013\u0005\u001d\u0001!%A\u0005\u0002\u0005%\u0001\"CA\u0007\u0001E\u0005I\u0011AA\b\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002&\u0001\t\t\u0011\"\u0001\u0002(!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0007\u0005\n\u0003o\u0001\u0011\u0011!C!\u0003sA\u0011\"a\u0012\u0001\u0003\u0003%\t!!\u0013\t\u0013\u0005M\u0003!!A\u0005B\u0005U\u0003\"CA-\u0001\u0005\u0005I\u0011IA.\u0011%\ti\u0006AA\u0001\n\u0003\ny\u0006C\u0005\u0002b\u0001\t\t\u0011\"\u0011\u0002d\u001dI\u0011q\r\u0011\u0002\u0002#\u0005\u0011\u0011\u000e\u0004\t?\u0001\n\t\u0011#\u0001\u0002l!1q-\u0007C\u0001\u0003\u0007C\u0011\"!\u0018\u001a\u0003\u0003%)%a\u0018\t\u0013\u0005\u0015\u0015$!A\u0005\u0002\u0006\u001d\u0005\"CAI3\u0005\u0005I\u0011QAJ\u0011%\t\t+GA\u0001\n\u0013\t\u0019KA\u0007BiR\u0014\u0018NY;uK&sgm\u001c\u0006\u0003C\t\n\u0001b]2bY\u0006\u001c\u0018n\u001a\u0006\u0003G\u0011\naa]2bY\u0006\u0004(BA\u0013'\u0003\u0019Q7o\u001c85g*\tq%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001UA\u001a\u0004CA\u0016/\u001b\u0005a#\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=b#AB!osJ+g\r\u0005\u0002,c%\u0011!\u0007\f\u0002\b!J|G-^2u!\t!DH\u0004\u00026u9\u0011a'O\u0007\u0002o)\u0011\u0001\bK\u0001\u0007yI|w\u000e\u001e \n\u00035J!a\u000f\u0017\u0002\u000fA\f7m[1hK&\u0011QH\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003w1\naa]=nE>dW#A!\u0011\u0005\t\u001bU\"\u0001\u0011\n\u0005\u0011\u0003#AB*z[\n|G.A\u0004ts6\u0014w\u000e\u001c\u0011\u0002\u000fQL\b/\u001a*fMV\t\u0001\n\u0005\u0002C\u0013&\u0011!\n\t\u0002\u0005)f\u0004X-\u0001\u0005usB,'+\u001a4!\u0003\u00151\u0018\r\\;f+\u0005q\u0005cA\u0016P#&\u0011\u0001\u000b\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005-\u0012\u0016BA*-\u0005\r\te._\u0001\u0007m\u0006dW/\u001a\u0011\u0002\rY\fG.^3t+\u00059\u0006c\u0001\u001bY5&\u0011\u0011L\u0010\u0002\u0004'\u0016\f\b\u0003B.]=Fk\u0011AI\u0005\u0003;\n\u0012a\u0001\n;jY\u0012,\u0007CA0d\u001d\t\u0001\u0017\r\u0005\u00027Y%\u0011!\rL\u0001\u0007!J,G-\u001a4\n\u0005\u0011,'AB*ue&twM\u0003\u0002cY\u00059a/\u00197vKN\u0004\u0013A\u0002\u001fj]&$h\bF\u0003jU.dW\u000e\u0005\u0002C\u0001!)q(\u0003a\u0001\u0003\")a)\u0003a\u0001\u0011\")A*\u0003a\u0001\u001d\")Q+\u0003a\u0001/\u0006!1m\u001c9z)\u0015I\u0007/\u001d:t\u0011\u001dy$\u0002%AA\u0002\u0005CqA\u0012\u0006\u0011\u0002\u0003\u0007\u0001\nC\u0004M\u0015A\u0005\t\u0019\u0001(\t\u000fUS\u0001\u0013!a\u0001/\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001<+\u0005\u0005;8&\u0001=\u0011\u0005etX\"\u0001>\u000b\u0005md\u0018!C;oG\",7m[3e\u0015\tiH&\u0001\u0006b]:|G/\u0019;j_:L!a >\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005\u0015!F\u0001%x\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!a\u0003+\u00059;\u0018AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003#Q#aV<\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t9\u0002\u0005\u0003\u0002\u001a\u0005\rRBAA\u000e\u0015\u0011\ti\"a\b\u0002\t1\fgn\u001a\u0006\u0003\u0003C\tAA[1wC&\u0019A-a\u0007\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005%\u0002cA\u0016\u0002,%\u0019\u0011Q\u0006\u0017\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007E\u000b\u0019\u0004C\u0005\u00026E\t\t\u00111\u0001\u0002*\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u000f\u0011\u000b\u0005u\u00121I)\u000e\u0005\u0005}\"bAA!Y\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0015\u0013q\b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002L\u0005E\u0003cA\u0016\u0002N%\u0019\u0011q\n\u0017\u0003\u000f\t{w\u000e\\3b]\"A\u0011QG\n\u0002\u0002\u0003\u0007\u0011+\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\f\u0003/B\u0011\"!\u000e\u0015\u0003\u0003\u0005\r!!\u000b\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u000b\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0006\u0002\r\u0015\fX/\u00197t)\u0011\tY%!\u001a\t\u0011\u0005Ur#!AA\u0002E\u000bQ\"\u0011;ue&\u0014W\u000f^3J]\u001a|\u0007C\u0001\"\u001a'\u0015I\u0012QNA=!%\ty'!\u001eB\u0011:;\u0016.\u0004\u0002\u0002r)\u0019\u00111\u000f\u0017\u0002\u000fI,h\u000e^5nK&!\u0011qOA9\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g\u000e\u000e\t\u0005\u0003w\n\t)\u0004\u0002\u0002~)!\u0011qPA\u0010\u0003\tIw.C\u0002>\u0003{\"\"!!\u001b\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0013%\fI)a#\u0002\u000e\u0006=\u0005\"B \u001d\u0001\u0004\t\u0005\"\u0002$\u001d\u0001\u0004A\u0005\"\u0002'\u001d\u0001\u0004q\u0005\"B+\u001d\u0001\u00049\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0003+\u000bi\n\u0005\u0003,\u001f\u0006]\u0005cB\u0016\u0002\u001a\u0006CejV\u0005\u0004\u00037c#A\u0002+va2,G\u0007\u0003\u0005\u0002 v\t\t\u00111\u0001j\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003K\u0003B!!\u0007\u0002(&!\u0011\u0011VA\u000e\u0005\u0019y%M[3di\u0002"
)
public class AttributeInfo implements Product, Serializable {
   private final Symbol symbol;
   private final Type typeRef;
   private final Option value;
   private final Seq values;

   public static Option unapply(final AttributeInfo x$0) {
      return AttributeInfo$.MODULE$.unapply(x$0);
   }

   public static AttributeInfo apply(final Symbol symbol, final Type typeRef, final Option value, final Seq values) {
      return AttributeInfo$.MODULE$.apply(symbol, typeRef, value, values);
   }

   public static Function1 tupled() {
      return AttributeInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AttributeInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Symbol symbol() {
      return this.symbol;
   }

   public Type typeRef() {
      return this.typeRef;
   }

   public Option value() {
      return this.value;
   }

   public Seq values() {
      return this.values;
   }

   public AttributeInfo copy(final Symbol symbol, final Type typeRef, final Option value, final Seq values) {
      return new AttributeInfo(symbol, typeRef, value, values);
   }

   public Symbol copy$default$1() {
      return this.symbol();
   }

   public Type copy$default$2() {
      return this.typeRef();
   }

   public Option copy$default$3() {
      return this.value();
   }

   public Seq copy$default$4() {
      return this.values();
   }

   public String productPrefix() {
      return "AttributeInfo";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.symbol();
            break;
         case 1:
            var10000 = this.typeRef();
            break;
         case 2:
            var10000 = this.value();
            break;
         case 3:
            var10000 = this.values();
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
      return x$1 instanceof AttributeInfo;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "symbol";
            break;
         case 1:
            var10000 = "typeRef";
            break;
         case 2:
            var10000 = "value";
            break;
         case 3:
            var10000 = "values";
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
      boolean var13;
      if (this != x$1) {
         label81: {
            boolean var2;
            if (x$1 instanceof AttributeInfo) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label63: {
                  label72: {
                     AttributeInfo var4 = (AttributeInfo)x$1;
                     Symbol var10000 = this.symbol();
                     Symbol var5 = var4.symbol();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label72;
                     }

                     Type var9 = this.typeRef();
                     Type var6 = var4.typeRef();
                     if (var9 == null) {
                        if (var6 != null) {
                           break label72;
                        }
                     } else if (!var9.equals(var6)) {
                        break label72;
                     }

                     Option var10 = this.value();
                     Option var7 = var4.value();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label72;
                        }
                     } else if (!var10.equals(var7)) {
                        break label72;
                     }

                     Seq var11 = this.values();
                     Seq var8 = var4.values();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label72;
                        }
                     } else if (!var11.equals(var8)) {
                        break label72;
                     }

                     if (var4.canEqual(this)) {
                        var13 = true;
                        break label63;
                     }
                  }

                  var13 = false;
               }

               if (var13) {
                  break label81;
               }
            }

            var13 = false;
            return var13;
         }
      }

      var13 = true;
      return var13;
   }

   public AttributeInfo(final Symbol symbol, final Type typeRef, final Option value, final Seq values) {
      this.symbol = symbol;
      this.typeRef = typeRef;
      this.value = value;
      this.values = values;
      Product.$init$(this);
   }
}
