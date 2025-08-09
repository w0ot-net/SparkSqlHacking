package org.json4s;

import java.io.InputStream;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0002\f\u0018\u0001rA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005k!)a\b\u0001C\u0001\u007f!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dI\u0006!!A\u0005\u0002iCqA\u0018\u0001\u0002\u0002\u0013\u0005q\fC\u0004f\u0001\u0005\u0005I\u0011\t4\t\u000f5\u0004\u0011\u0011!C\u0001]\"91\u000fAA\u0001\n\u0003\"\bb\u0002<\u0001\u0003\u0003%\te\u001e\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011\u001dQ\b!!A\u0005Bm<q!`\f\u0002\u0002#\u0005aPB\u0004\u0017/\u0005\u0005\t\u0012A@\t\ry\u0002B\u0011AA\t\u0011\u001dA\b#!A\u0005FeD\u0011\"a\u0005\u0011\u0003\u0003%\t)!\u0006\t\u0013\u0005e\u0001#!A\u0005\u0002\u0006m\u0001\"CA\u0014!\u0005\u0005I\u0011BA\u0015\u0005-\u0019FO]3b[&s\u0007/\u001e;\u000b\u0005aI\u0012A\u00026t_:$4OC\u0001\u001b\u0003\ry'oZ\u0002\u0001'\u0011\u0001Q$I\u0014\u0011\u0005yyR\"A\f\n\u0005\u0001:\"!\u0003&t_:Le\u000e];u!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u000b\u0019\u000f\u0005%rcB\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u001c\u0003\u0019a$o\\8u}%\tA%\u0003\u00020G\u00059\u0001/Y2lC\u001e,\u0017BA\u00193\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ty3%\u0001\u0004tiJ,\u0017-\\\u000b\u0002kA\u0011agO\u0007\u0002o)\u0011\u0001(O\u0001\u0003S>T\u0011AO\u0001\u0005U\u00064\u0018-\u0003\u0002=o\tY\u0011J\u001c9viN#(/Z1n\u0003\u001d\u0019HO]3b[\u0002\na\u0001P5oSRtDC\u0001!B!\tq\u0002\u0001C\u00034\u0007\u0001\u0007Q'\u0001\u0003d_BLHC\u0001!E\u0011\u001d\u0019D\u0001%AA\u0002U\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001HU\t)\u0004jK\u0001J!\tQu*D\u0001L\u0015\taU*A\u0005v]\u000eDWmY6fI*\u0011ajI\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001)L\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003M\u0003\"\u0001V,\u000e\u0003US!AV\u001d\u0002\t1\fgnZ\u0005\u00031V\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A.\u0011\u0005\tb\u0016BA/$\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\u00017\r\u0005\u0002#C&\u0011!m\t\u0002\u0004\u0003:L\bb\u00023\t\u0003\u0003\u0005\raW\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003\u001d\u00042\u0001[6a\u001b\u0005I'B\u00016$\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003Y&\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011qN\u001d\t\u0003EAL!!]\u0012\u0003\u000f\t{w\u000e\\3b]\"9AMCA\u0001\u0002\u0004\u0001\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$\"aU;\t\u000f\u0011\\\u0011\u0011!a\u00017\u0006A\u0001.Y:i\u0007>$W\rF\u0001\\\u0003!!xn\u0015;sS:<G#A*\u0002\r\u0015\fX/\u00197t)\tyG\u0010C\u0004e\u001d\u0005\u0005\t\u0019\u00011\u0002\u0017M#(/Z1n\u0013:\u0004X\u000f\u001e\t\u0003=A\u0019R\u0001EA\u0001\u0003\u001b\u0001b!a\u0001\u0002\nU\u0002UBAA\u0003\u0015\r\t9aI\u0001\beVtG/[7f\u0013\u0011\tY!!\u0002\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007E\u00027\u0003\u001fI!!M\u001c\u0015\u0003y\fQ!\u00199qYf$2\u0001QA\f\u0011\u0015\u00194\u00031\u00016\u0003\u001d)h.\u00199qYf$B!!\b\u0002$A!!%a\b6\u0013\r\t\tc\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005\u0015B#!AA\u0002\u0001\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\u0003E\u0002U\u0003[I1!a\fV\u0005\u0019y%M[3di\u0002"
)
public class StreamInput extends JsonInput {
   private final InputStream stream;

   public static Option unapply(final StreamInput x$0) {
      return StreamInput$.MODULE$.unapply(x$0);
   }

   public static StreamInput apply(final InputStream stream) {
      return StreamInput$.MODULE$.apply(stream);
   }

   public static Function1 andThen(final Function1 g) {
      return StreamInput$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StreamInput$.MODULE$.compose(g);
   }

   public InputStream stream() {
      return this.stream;
   }

   public StreamInput copy(final InputStream stream) {
      return new StreamInput(stream);
   }

   public InputStream copy$default$1() {
      return this.stream();
   }

   public String productPrefix() {
      return "StreamInput";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.stream();
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
      return x$1 instanceof StreamInput;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "stream";
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
            if (x$1 instanceof StreamInput) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     StreamInput var4 = (StreamInput)x$1;
                     InputStream var10000 = this.stream();
                     InputStream var5 = var4.stream();
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

   public StreamInput(final InputStream stream) {
      this.stream = stream;
   }
}
