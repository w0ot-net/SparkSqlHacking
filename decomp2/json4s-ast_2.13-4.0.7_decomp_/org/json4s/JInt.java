package org.json4s;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b\u0001\u0002\r\u001a\u0001zA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f\u0015!!\t\u0001\u0001;\u0011\u0015\u0019\u0005\u0001\"\u0001:\u0011\u001d!\u0005!!A\u0005\u0002\u0015Cqa\u0012\u0001\u0012\u0002\u0013\u0005\u0001\nC\u0004T\u0001\u0005\u0005I\u0011\t+\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAA\u0001\n\u0003\u0019\u0007bB5\u0001\u0003\u0003%\tE\u001b\u0005\bc\u0002\t\t\u0011\"\u0001s\u0011\u001d9\b!!A\u0005BaDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\u001eI\u00111A\r\u0002\u0002#\u0005\u0011Q\u0001\u0004\t1e\t\t\u0011#\u0001\u0002\b!1aH\u0005C\u0001\u0003?Aq\u0001 \n\u0002\u0002\u0013\u0015S\u0010C\u0005\u0002\"I\t\t\u0011\"!\u0002$!I\u0011q\u0005\n\u0002\u0002\u0013\u0005\u0015\u0011\u0006\u0005\n\u0003k\u0011\u0012\u0011!C\u0005\u0003o\u0011AAS%oi*\u0011!dG\u0001\u0007UN|g\u000eN:\u000b\u0003q\t1a\u001c:h\u0007\u0001\u0019R\u0001A\u0010$M1\u0002\"\u0001I\u0011\u000e\u0003eI!AI\r\u0003\r)3\u0016\r\\;f!\t\u0001C%\u0003\u0002&3\t9!JT;nE\u0016\u0014\bCA\u0014+\u001b\u0005A#\"A\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005-B#a\u0002)s_\u0012,8\r\u001e\t\u0003[Ur!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005Ej\u0012A\u0002\u001fs_>$h(C\u0001*\u0013\t!\u0004&A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b)\u0003\rqW/\\\u000b\u0002uA\u0011QfO\u0005\u0003y]\u0012aAQ5h\u0013:$\u0018\u0001\u00028v[\u0002\na\u0001P5oSRtDC\u0001!B!\t\u0001\u0003\u0001C\u00039\u0007\u0001\u0007!H\u0001\u0004WC2,Xm]\u0001\u0007m\u0006dW/Z:\u0002\t\r|\u0007/\u001f\u000b\u0003\u0001\u001aCq\u0001\u000f\u0004\u0011\u0002\u0003\u0007!(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003%S#A\u000f&,\u0003-\u0003\"\u0001T)\u000e\u00035S!AT(\u0002\u0013Ut7\r[3dW\u0016$'B\u0001))\u0003)\tgN\\8uCRLwN\\\u0005\u0003%6\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tQ\u000b\u0005\u0002W76\tqK\u0003\u0002Y3\u0006!A.\u00198h\u0015\u0005Q\u0016\u0001\u00026bm\u0006L!\u0001X,\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005y\u0006CA\u0014a\u0013\t\t\u0007FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002eOB\u0011q%Z\u0005\u0003M\"\u00121!\u00118z\u0011\u001dA'\"!AA\u0002}\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A6\u0011\u00071|G-D\u0001n\u0015\tq\u0007&\u0001\u0006d_2dWm\u0019;j_:L!\u0001]7\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003gZ\u0004\"a\n;\n\u0005UD#a\u0002\"p_2,\u0017M\u001c\u0005\bQ2\t\t\u00111\u0001e\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005UK\bb\u00025\u000e\u0003\u0003\u0005\raX\u0001\tQ\u0006\u001c\bnQ8eKR\tq,\u0001\u0005u_N#(/\u001b8h)\u0005)\u0016AB3rk\u0006d7\u000fF\u0002t\u0003\u0003Aq\u0001\u001b\t\u0002\u0002\u0003\u0007A-\u0001\u0003K\u0013:$\bC\u0001\u0011\u0013'\u0015\u0011\u0012\u0011BA\u000b!\u0019\tY!!\u0005;\u00016\u0011\u0011Q\u0002\u0006\u0004\u0003\u001fA\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003'\tiAA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a\u0006\u0002\u001e5\u0011\u0011\u0011\u0004\u0006\u0004\u00037I\u0016AA5p\u0013\r1\u0014\u0011\u0004\u000b\u0003\u0003\u000b\tQ!\u00199qYf$2\u0001QA\u0013\u0011\u0015AT\u00031\u0001;\u0003\u001d)h.\u00199qYf$B!a\u000b\u00022A!q%!\f;\u0013\r\ty\u0003\u000b\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005Mb#!AA\u0002\u0001\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0004E\u0002W\u0003wI1!!\u0010X\u0005\u0019y%M[3di\u0002"
)
public class JInt extends JValue implements JNumber {
   private final BigInt num;

   public static Option unapply(final JInt x$0) {
      return JInt$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return JInt$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JInt$.MODULE$.compose(g);
   }

   public BigInt num() {
      return this.num;
   }

   public BigInt values() {
      return this.num();
   }

   public JInt copy(final BigInt num) {
      return new JInt(num);
   }

   public BigInt copy$default$1() {
      return this.num();
   }

   public String productPrefix() {
      return "JInt";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.num();
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
      return x$1 instanceof JInt;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "num";
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
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof JInt) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     JInt var4 = (JInt)x$1;
                     BigInt var10000 = this.num();
                     BigInt var5 = var4.num();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public JInt(final BigInt num) {
      this.num = num;
   }
}
