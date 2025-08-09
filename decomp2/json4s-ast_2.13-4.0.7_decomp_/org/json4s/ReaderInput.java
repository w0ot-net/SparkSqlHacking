package org.json4s;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0002\f\u0018\u0001rA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005k!)a\b\u0001C\u0001\u007f!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dI\u0006!!A\u0005\u0002iCqA\u0018\u0001\u0002\u0002\u0013\u0005q\fC\u0004f\u0001\u0005\u0005I\u0011\t4\t\u000f5\u0004\u0011\u0011!C\u0001]\"91\u000fAA\u0001\n\u0003\"\bb\u0002<\u0001\u0003\u0003%\te\u001e\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011\u001dQ\b!!A\u0005Bm<q!`\f\u0002\u0002#\u0005aPB\u0004\u0017/\u0005\u0005\t\u0012A@\t\ry\u0002B\u0011AA\t\u0011\u001dA\b#!A\u0005FeD\u0011\"a\u0005\u0011\u0003\u0003%\t)!\u0006\t\u0013\u0005e\u0001#!A\u0005\u0002\u0006m\u0001\"CA\u0014!\u0005\u0005I\u0011BA\u0015\u0005-\u0011V-\u00193fe&s\u0007/\u001e;\u000b\u0005aI\u0012A\u00026t_:$4OC\u0001\u001b\u0003\ry'oZ\u0002\u0001'\u0011\u0001Q$I\u0014\u0011\u0005yyR\"A\f\n\u0005\u0001:\"!\u0003&t_:Le\u000e];u!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u000b\u0019\u000f\u0005%rcB\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u001c\u0003\u0019a$o\\8u}%\tA%\u0003\u00020G\u00059\u0001/Y2lC\u001e,\u0017BA\u00193\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ty3%\u0001\u0004sK\u0006$WM]\u000b\u0002kA\u0011agO\u0007\u0002o)\u0011\u0001(O\u0001\u0003S>T\u0011AO\u0001\u0005U\u00064\u0018-\u0003\u0002=o\t1!+Z1eKJ\fqA]3bI\u0016\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0001\u0006\u0003\"A\b\u0001\t\u000bM\u001a\u0001\u0019A\u001b\u0002\t\r|\u0007/\u001f\u000b\u0003\u0001\u0012Cqa\r\u0003\u0011\u0002\u0003\u0007Q'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u001dS#!\u000e%,\u0003%\u0003\"AS(\u000e\u0003-S!\u0001T'\u0002\u0013Ut7\r[3dW\u0016$'B\u0001($\u0003)\tgN\\8uCRLwN\\\u0005\u0003!.\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\u000b\u0005\u0002U/6\tQK\u0003\u0002Ws\u0005!A.\u00198h\u0013\tAVK\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u00027B\u0011!\u0005X\u0005\u0003;\u000e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001Y2\u0011\u0005\t\n\u0017B\u00012$\u0005\r\te.\u001f\u0005\bI\"\t\t\u00111\u0001\\\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tq\rE\u0002iW\u0002l\u0011!\u001b\u0006\u0003U\u000e\n!bY8mY\u0016\u001cG/[8o\u0013\ta\u0017N\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA8s!\t\u0011\u0003/\u0003\u0002rG\t9!i\\8mK\u0006t\u0007b\u00023\u000b\u0003\u0003\u0005\r\u0001Y\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Tk\"9AmCA\u0001\u0002\u0004Y\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003m\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002'\u00061Q-];bYN$\"a\u001c?\t\u000f\u0011t\u0011\u0011!a\u0001A\u0006Y!+Z1eKJLe\u000e];u!\tq\u0002cE\u0003\u0011\u0003\u0003\ti\u0001\u0005\u0004\u0002\u0004\u0005%Q\u0007Q\u0007\u0003\u0003\u000bQ1!a\u0002$\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0003\u0002\u0006\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\u0007Y\ny!\u0003\u00022oQ\ta0A\u0003baBd\u0017\u0010F\u0002A\u0003/AQaM\nA\u0002U\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u001e\u0005\r\u0002\u0003\u0002\u0012\u0002 UJ1!!\t$\u0005\u0019y\u0005\u000f^5p]\"A\u0011Q\u0005\u000b\u0002\u0002\u0003\u0007\u0001)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u000b\u0011\u0007Q\u000bi#C\u0002\u00020U\u0013aa\u00142kK\u000e$\b"
)
public class ReaderInput extends JsonInput {
   private final java.io.Reader reader;

   public static Option unapply(final ReaderInput x$0) {
      return ReaderInput$.MODULE$.unapply(x$0);
   }

   public static ReaderInput apply(final java.io.Reader reader) {
      return ReaderInput$.MODULE$.apply(reader);
   }

   public static Function1 andThen(final Function1 g) {
      return ReaderInput$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ReaderInput$.MODULE$.compose(g);
   }

   public java.io.Reader reader() {
      return this.reader;
   }

   public ReaderInput copy(final java.io.Reader reader) {
      return new ReaderInput(reader);
   }

   public java.io.Reader copy$default$1() {
      return this.reader();
   }

   public String productPrefix() {
      return "ReaderInput";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.reader();
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
      return x$1 instanceof ReaderInput;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "reader";
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
            if (x$1 instanceof ReaderInput) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     ReaderInput var4 = (ReaderInput)x$1;
                     java.io.Reader var10000 = this.reader();
                     java.io.Reader var5 = var4.reader();
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

   public ReaderInput(final java.io.Reader reader) {
      this.reader = reader;
   }
}
