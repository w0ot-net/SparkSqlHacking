package org.json4s;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.math.BigDecimal;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b\u0001\u0002\r\u001a\u0001zA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f\u0015!!\t\u0001\u0001;\u0011\u0015\u0019\u0005\u0001\"\u0001:\u0011\u001d!\u0005!!A\u0005\u0002\u0015Cqa\u0012\u0001\u0012\u0002\u0013\u0005\u0001\nC\u0004T\u0001\u0005\u0005I\u0011\t+\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAA\u0001\n\u0003\u0019\u0007bB5\u0001\u0003\u0003%\tE\u001b\u0005\bc\u0002\t\t\u0011\"\u0001s\u0011\u001d9\b!!A\u0005BaDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\u001eI\u00111A\r\u0002\u0002#\u0005\u0011Q\u0001\u0004\t1e\t\t\u0011#\u0001\u0002\b!1aH\u0005C\u0001\u0003?Aq\u0001 \n\u0002\u0002\u0013\u0015S\u0010C\u0005\u0002\"I\t\t\u0011\"!\u0002$!I\u0011q\u0005\n\u0002\u0002\u0013\u0005\u0015\u0011\u0006\u0005\n\u0003k\u0011\u0012\u0011!C\u0005\u0003o\u0011\u0001B\u0013#fG&l\u0017\r\u001c\u0006\u00035m\taA[:p]R\u001a(\"\u0001\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001y2E\n\u0017\u0011\u0005\u0001\nS\"A\r\n\u0005\tJ\"A\u0002&WC2,X\r\u0005\u0002!I%\u0011Q%\u0007\u0002\b\u0015:+XNY3s!\t9#&D\u0001)\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0005\u001d\u0001&o\u001c3vGR\u0004\"!L\u001b\u000f\u00059\u001adBA\u00183\u001b\u0005\u0001$BA\u0019\u001e\u0003\u0019a$o\\8u}%\t\u0011&\u0003\u00025Q\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001c8\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t!\u0004&A\u0002ok6,\u0012A\u000f\t\u0003[mJ!\u0001P\u001c\u0003\u0015\tKw\rR3dS6\fG.\u0001\u0003ok6\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002A\u0003B\u0011\u0001\u0005\u0001\u0005\u0006q\r\u0001\rA\u000f\u0002\u0007-\u0006dW/Z:\u0002\rY\fG.^3t\u0003\u0011\u0019w\u000e]=\u0015\u0005\u00013\u0005b\u0002\u001d\u0007!\u0003\u0005\rAO\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005I%F\u0001\u001eKW\u0005Y\u0005C\u0001'R\u001b\u0005i%B\u0001(P\u0003%)hn\u00195fG.,GM\u0003\u0002QQ\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Ik%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u0016\t\u0003-nk\u0011a\u0016\u0006\u00031f\u000bA\u0001\\1oO*\t!,\u0001\u0003kCZ\f\u0017B\u0001/X\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\f\u0005\u0002(A&\u0011\u0011\r\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003I\u001e\u0004\"aJ3\n\u0005\u0019D#aA!os\"9\u0001NCA\u0001\u0002\u0004y\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001l!\raw\u000eZ\u0007\u0002[*\u0011a\u000eK\u0001\u000bG>dG.Z2uS>t\u0017B\u00019n\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005M4\bCA\u0014u\u0013\t)\bFA\u0004C_>dW-\u00198\t\u000f!d\u0011\u0011!a\u0001I\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\t)\u0016\u0010C\u0004i\u001b\u0005\u0005\t\u0019A0\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012aX\u0001\ti>\u001cFO]5oOR\tQ+\u0001\u0004fcV\fGn\u001d\u000b\u0004g\u0006\u0005\u0001b\u00025\u0011\u0003\u0003\u0005\r\u0001Z\u0001\t\u0015\u0012+7-[7bYB\u0011\u0001EE\n\u0006%\u0005%\u0011Q\u0003\t\u0007\u0003\u0017\t\tB\u000f!\u000e\u0005\u00055!bAA\bQ\u00059!/\u001e8uS6,\u0017\u0002BA\n\u0003\u001b\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t9\"!\b\u000e\u0005\u0005e!bAA\u000e3\u0006\u0011\u0011n\\\u0005\u0004m\u0005eACAA\u0003\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0001\u0015Q\u0005\u0005\u0006qU\u0001\rAO\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tY#!\r\u0011\t\u001d\niCO\u0005\u0004\u0003_A#AB(qi&|g\u000e\u0003\u0005\u00024Y\t\t\u00111\u0001A\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003s\u00012AVA\u001e\u0013\r\tid\u0016\u0002\u0007\u001f\nTWm\u0019;"
)
public class JDecimal extends JValue implements JNumber {
   private final BigDecimal num;

   public static Option unapply(final JDecimal x$0) {
      return JDecimal$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return JDecimal$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JDecimal$.MODULE$.compose(g);
   }

   public BigDecimal num() {
      return this.num;
   }

   public BigDecimal values() {
      return this.num();
   }

   public JDecimal copy(final BigDecimal num) {
      return new JDecimal(num);
   }

   public BigDecimal copy$default$1() {
      return this.num();
   }

   public String productPrefix() {
      return "JDecimal";
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
      return x$1 instanceof JDecimal;
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
            if (x$1 instanceof JDecimal) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     JDecimal var4 = (JDecimal)x$1;
                     BigDecimal var10000 = this.num();
                     BigDecimal var5 = var4.num();
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

   public JDecimal(final BigDecimal num) {
      this.num = num;
   }
}
