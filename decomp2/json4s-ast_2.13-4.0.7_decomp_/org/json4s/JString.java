package org.json4s;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001\u0002\r\u001a\u0001zA\u0001\"\u000e\u0001\u0003\u0016\u0004%\tA\u000e\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005o!)\u0001\t\u0001C\u0001\u0003\u0016!A\t\u0001\u00018\u0011\u0015)\u0005\u0001\"\u00017\u0011\u001d1\u0005!!A\u0005\u0002\u001dCq!\u0013\u0001\u0012\u0002\u0013\u0005!\nC\u0004V\u0001\u0005\u0005I\u0011\t,\t\u000fy\u0003\u0011\u0011!C\u0001?\"91\rAA\u0001\n\u0003!\u0007b\u00026\u0001\u0003\u0003%\te\u001b\u0005\be\u0002\t\t\u0011\"\u0001t\u0011\u001dA\b!!A\u0005BeDqa\u001f\u0001\u0002\u0002\u0013\u0005C\u0010C\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0011}\u0004\u0011\u0011!C!\u0003\u00039\u0011\"!\u0002\u001a\u0003\u0003E\t!a\u0002\u0007\u0011aI\u0012\u0011!E\u0001\u0003\u0013Aa\u0001\u0011\n\u0005\u0002\u0005\u0005\u0002bB?\u0013\u0003\u0003%)E \u0005\n\u0003G\u0011\u0012\u0011!CA\u0003KA\u0011\"!\u000b\u0013\u0003\u0003%\t)a\u000b\t\u0013\u0005]\"#!A\u0005\n\u0005e\"a\u0002&TiJLgn\u001a\u0006\u00035m\taA[:p]R\u001a(\"\u0001\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001y2%\u000b\t\u0003A\u0005j\u0011!G\u0005\u0003Ee\u0011aA\u0013,bYV,\u0007C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#a\u0002)s_\u0012,8\r\u001e\t\u0003UIr!a\u000b\u0019\u000f\u00051zS\"A\u0017\u000b\u00059j\u0012A\u0002\u001fs_>$h(C\u0001'\u0013\t\tT%A\u0004qC\u000e\\\u0017mZ3\n\u0005M\"$\u0001D*fe&\fG.\u001b>bE2,'BA\u0019&\u0003\u0005\u0019X#A\u001c\u0011\u0005abdBA\u001d;!\taS%\u0003\u0002<K\u00051\u0001K]3eK\u001aL!!\u0010 \u0003\rM#(/\u001b8h\u0015\tYT%\u0001\u0002tA\u00051A(\u001b8jiz\"\"AQ\"\u0011\u0005\u0001\u0002\u0001\"B\u001b\u0004\u0001\u00049$A\u0002,bYV,7/\u0001\u0004wC2,Xm]\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002C\u0011\"9QG\u0002I\u0001\u0002\u00049\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0017*\u0012q\u0007T\u0016\u0002\u001bB\u0011ajU\u0007\u0002\u001f*\u0011\u0001+U\u0001\nk:\u001c\u0007.Z2lK\u0012T!AU\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002U\u001f\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011Q(W\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002AB\u0011A%Y\u0005\u0003E\u0016\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001a5\u0011\u0005\u00112\u0017BA4&\u0005\r\te.\u001f\u0005\bS*\t\t\u00111\u0001a\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA\u000eE\u0002na\u0016l\u0011A\u001c\u0006\u0003_\u0016\n!bY8mY\u0016\u001cG/[8o\u0013\t\thN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u0001;x!\t!S/\u0003\u0002wK\t9!i\\8mK\u0006t\u0007bB5\r\u0003\u0003\u0005\r!Z\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Xu\"9\u0011.DA\u0001\u0002\u0004\u0001\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0001\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002/\u00061Q-];bYN$2\u0001^A\u0002\u0011\u001dI\u0007#!AA\u0002\u0015\fqAS*ue&tw\r\u0005\u0002!%M)!#a\u0003\u0002\u0018A1\u0011QBA\no\tk!!a\u0004\u000b\u0007\u0005EQ%A\u0004sk:$\u0018.\\3\n\t\u0005U\u0011q\u0002\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\r\u0003?i!!a\u0007\u000b\u0007\u0005u1,\u0001\u0002j_&\u00191'a\u0007\u0015\u0005\u0005\u001d\u0011!B1qa2LHc\u0001\"\u0002(!)Q'\u0006a\u0001o\u00059QO\\1qa2LH\u0003BA\u0017\u0003g\u0001B\u0001JA\u0018o%\u0019\u0011\u0011G\u0013\u0003\r=\u0003H/[8o\u0011!\t)DFA\u0001\u0002\u0004\u0011\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\b\t\u00041\u0006u\u0012bAA 3\n1qJ\u00196fGR\u0004"
)
public class JString extends JValue {
   private final String s;

   public static Option unapply(final JString x$0) {
      return JString$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return JString$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JString$.MODULE$.compose(g);
   }

   public String s() {
      return this.s;
   }

   public String values() {
      return this.s();
   }

   public JString copy(final String s) {
      return new JString(s);
   }

   public String copy$default$1() {
      return this.s();
   }

   public String productPrefix() {
      return "JString";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.s();
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
      return x$1 instanceof JString;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "s";
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
            if (x$1 instanceof JString) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     JString var4 = (JString)x$1;
                     String var10000 = this.s();
                     String var5 = var4.s();
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

   public JString(final String s) {
      this.s = s;
   }
}
