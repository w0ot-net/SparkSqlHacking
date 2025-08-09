package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d\u0001B\u0010!\u0001&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u0003\"AQ\t\u0001BK\u0002\u0013\u0005\u0001\t\u0003\u0005G\u0001\tE\t\u0015!\u0003B\u0011!9\u0005A!f\u0001\n\u0003\u0001\u0005\u0002\u0003%\u0001\u0005#\u0005\u000b\u0011B!\t\u0011%\u0003!Q3A\u0005\u0002)C\u0001B\u0015\u0001\u0003\u0012\u0003\u0006Ia\u0013\u0005\u0006'\u0002!\t\u0001\u0016\u0005\b5\u0002\t\t\u0011\"\u0001\\\u0011\u001d\u0001\u0007!%A\u0005\u0002\u0005Dq\u0001\u001c\u0001\u0012\u0002\u0013\u0005\u0011\rC\u0004n\u0001E\u0005I\u0011A1\t\u000f9\u0004\u0011\u0013!C\u0001_\"9\u0011\u000fAA\u0001\n\u0003\u0012\bbB>\u0001\u0003\u0003%\t\u0001\u0011\u0005\by\u0002\t\t\u0011\"\u0001~\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0001\u0002\u001a!I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003S\u0001\u0011\u0011!C!\u0003WA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\t\u0013\u0005E\u0002!!A\u0005B\u0005Mr!CA\u001cA\u0005\u0005\t\u0012AA\u001d\r!y\u0002%!A\t\u0002\u0005m\u0002BB*\u001a\t\u0003\t\u0019\u0006C\u0005\u0002.e\t\t\u0011\"\u0012\u00020!I\u0011QK\r\u0002\u0002\u0013\u0005\u0015q\u000b\u0005\n\u0003CJ\u0012\u0011!CA\u0003GB\u0011\"!\u001e\u001a\u0003\u0003%I!a\u001e\u0003\r5+G\u000f[8e\u0015\t\t#%\u0001\u0005tG\u0006d\u0017m]5h\u0015\t\u0019C%\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u0003K\u0019\naA[:p]R\u001a(\"A\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001Q\u0003g\r\t\u0003W9j\u0011\u0001\f\u0006\u0002[\u0005)1oY1mC&\u0011q\u0006\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-\n\u0014B\u0001\u001a-\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u000e\u001f\u000f\u0005URdB\u0001\u001c:\u001b\u00059$B\u0001\u001d)\u0003\u0019a$o\\8u}%\tQ&\u0003\u0002<Y\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tYD&A\u0003gY\u0006<7/F\u0001B!\tY#)\u0003\u0002DY\t\u0019\u0011J\u001c;\u0002\r\u0019d\u0017mZ:!\u0003%q\u0017-\\3J]\u0012,\u00070\u0001\u0006oC6,\u0017J\u001c3fq\u0002\nq\u0002Z3tGJL\u0007\u000f^8s\u0013:$W\r_\u0001\u0011I\u0016\u001c8M]5qi>\u0014\u0018J\u001c3fq\u0002\n!\"\u0019;ue&\u0014W\u000f^3t+\u0005Y\u0005c\u0001\u001bM\u001d&\u0011QJ\u0010\u0002\u0004'\u0016\f\bCA(Q\u001b\u0005\u0001\u0013BA)!\u0005%\tE\u000f\u001e:jEV$X-A\u0006biR\u0014\u0018NY;uKN\u0004\u0013A\u0002\u001fj]&$h\bF\u0003V-^C\u0016\f\u0005\u0002P\u0001!)q(\u0003a\u0001\u0003\")Q)\u0003a\u0001\u0003\")q)\u0003a\u0001\u0003\")\u0011*\u0003a\u0001\u0017\u0006!1m\u001c9z)\u0015)F,\u00180`\u0011\u001dy$\u0002%AA\u0002\u0005Cq!\u0012\u0006\u0011\u0002\u0003\u0007\u0011\tC\u0004H\u0015A\u0005\t\u0019A!\t\u000f%S\u0001\u0013!a\u0001\u0017\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00012+\u0005\u0005\u001b7&\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017!C;oG\",7m[3e\u0015\tIG&\u0001\u0006b]:|G/\u0019;j_:L!a\u001b4\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"T#\u00019+\u0005-\u001b\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001t!\t!\u00180D\u0001v\u0015\t1x/\u0001\u0003mC:<'\"\u0001=\u0002\t)\fg/Y\u0005\u0003uV\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004}\u0006\r\u0001CA\u0016\u0000\u0013\r\t\t\u0001\f\u0002\u0004\u0003:L\b\u0002CA\u0003#\u0005\u0005\t\u0019A!\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u0001E\u0003\u0002\u000e\u0005Ma0\u0004\u0002\u0002\u0010)\u0019\u0011\u0011\u0003\u0017\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0016\u0005=!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0007\u0002\"A\u00191&!\b\n\u0007\u0005}AFA\u0004C_>dW-\u00198\t\u0011\u0005\u00151#!AA\u0002y\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u00191/a\n\t\u0011\u0005\u0015A#!AA\u0002\u0005\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u0003\u0006AAo\\*ue&tw\rF\u0001t\u0003\u0019)\u0017/^1mgR!\u00111DA\u001b\u0011!\t)aFA\u0001\u0002\u0004q\u0018AB'fi\"|G\r\u0005\u0002P3M)\u0011$!\u0010\u0002JAI\u0011qHA#\u0003\u0006\u000b5*V\u0007\u0003\u0003\u0003R1!a\u0011-\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0012\u0002B\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001b\u0011\t\u0005-\u0013\u0011K\u0007\u0003\u0003\u001bR1!a\u0014x\u0003\tIw.C\u0002>\u0003\u001b\"\"!!\u000f\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0013U\u000bI&a\u0017\u0002^\u0005}\u0003\"B \u001d\u0001\u0004\t\u0005\"B#\u001d\u0001\u0004\t\u0005\"B$\u001d\u0001\u0004\t\u0005\"B%\u001d\u0001\u0004Y\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003K\n\t\bE\u0003,\u0003O\nY'C\u0002\u0002j1\u0012aa\u00149uS>t\u0007cB\u0016\u0002n\u0005\u000b\u0015iS\u0005\u0004\u0003_b#A\u0002+va2,G\u0007\u0003\u0005\u0002tu\t\t\u00111\u0001V\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003s\u00022\u0001^A>\u0013\r\ti(\u001e\u0002\u0007\u001f\nTWm\u0019;"
)
public class Method implements Product, Serializable {
   private final int flags;
   private final int nameIndex;
   private final int descriptorIndex;
   private final Seq attributes;

   public static Option unapply(final Method x$0) {
      return Method$.MODULE$.unapply(x$0);
   }

   public static Method apply(final int flags, final int nameIndex, final int descriptorIndex, final Seq attributes) {
      return Method$.MODULE$.apply(flags, nameIndex, descriptorIndex, attributes);
   }

   public static Function1 tupled() {
      return Method$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Method$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int flags() {
      return this.flags;
   }

   public int nameIndex() {
      return this.nameIndex;
   }

   public int descriptorIndex() {
      return this.descriptorIndex;
   }

   public Seq attributes() {
      return this.attributes;
   }

   public Method copy(final int flags, final int nameIndex, final int descriptorIndex, final Seq attributes) {
      return new Method(flags, nameIndex, descriptorIndex, attributes);
   }

   public int copy$default$1() {
      return this.flags();
   }

   public int copy$default$2() {
      return this.nameIndex();
   }

   public int copy$default$3() {
      return this.descriptorIndex();
   }

   public Seq copy$default$4() {
      return this.attributes();
   }

   public String productPrefix() {
      return "Method";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.flags());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.nameIndex());
            break;
         case 2:
            var10000 = BoxesRunTime.boxToInteger(this.descriptorIndex());
            break;
         case 3:
            var10000 = this.attributes();
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
      return x$1 instanceof Method;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "flags";
            break;
         case 1:
            var10000 = "nameIndex";
            break;
         case 2:
            var10000 = "descriptorIndex";
            break;
         case 3:
            var10000 = "attributes";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.flags());
      var1 = Statics.mix(var1, this.nameIndex());
      var1 = Statics.mix(var1, this.descriptorIndex());
      var1 = Statics.mix(var1, Statics.anyHash(this.attributes()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label59: {
            boolean var2;
            if (x$1 instanceof Method) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label42: {
                  Method var4 = (Method)x$1;
                  if (this.flags() == var4.flags() && this.nameIndex() == var4.nameIndex() && this.descriptorIndex() == var4.descriptorIndex()) {
                     label38: {
                        Seq var10000 = this.attributes();
                        Seq var5 = var4.attributes();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label38;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label38;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label42;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label59;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public Method(final int flags, final int nameIndex, final int descriptorIndex, final Seq attributes) {
      this.flags = flags;
      this.nameIndex = nameIndex;
      this.descriptorIndex = descriptorIndex;
      this.attributes = attributes;
      Product.$init$(this);
   }
}
