package org.json4s;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001\u0002\f\u0018\u0001rA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\t{\u0001\u0011\t\u0012)A\u0005k!)a\b\u0001C\u0001\u007f!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dQ\u0006!!A\u0005\u0002mCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004g\u0001\u0005\u0005I\u0011I4\t\u000f9\u0004\u0011\u0011!C\u0001_\"9A\u000fAA\u0001\n\u0003*\bbB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011\u001dY\b!!A\u0005Bq<qA`\f\u0002\u0002#\u0005qP\u0002\u0005\u0017/\u0005\u0005\t\u0012AA\u0001\u0011\u0019q\u0004\u0003\"\u0001\u0002\u001a!9\u0011\u0010EA\u0001\n\u000bR\b\"CA\u000e!\u0005\u0005I\u0011QA\u000f\u0011%\t\t\u0003EA\u0001\n\u0003\u000b\u0019\u0003C\u0005\u00020A\t\t\u0011\"\u0003\u00022\tY1\u000b\u001e:j]\u001eLe\u000e];u\u0015\tA\u0012$\u0001\u0004kg>tGg\u001d\u0006\u00025\u0005\u0019qN]4\u0004\u0001M!\u0001!H\u0011(!\tqr$D\u0001\u0018\u0013\t\u0001sCA\u0005Kg>t\u0017J\u001c9viB\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t9\u0001K]8ek\u000e$\bC\u0001\u00151\u001d\tIcF\u0004\u0002+[5\t1F\u0003\u0002-7\u00051AH]8pizJ\u0011\u0001J\u0005\u0003_\r\nq\u0001]1dW\u0006<W-\u0003\u00022e\ta1+\u001a:jC2L'0\u00192mK*\u0011qfI\u0001\u0007gR\u0014\u0018N\\4\u0016\u0003U\u0002\"A\u000e\u001e\u000f\u0005]B\u0004C\u0001\u0016$\u0013\tI4%\u0001\u0004Qe\u0016$WMZ\u0005\u0003wq\u0012aa\u0015;sS:<'BA\u001d$\u0003\u001d\u0019HO]5oO\u0002\na\u0001P5oSRtDC\u0001!B!\tq\u0002\u0001C\u00034\u0007\u0001\u0007Q'\u0001\u0003d_BLHC\u0001!E\u0011\u001d\u0019D\u0001%AA\u0002U\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001HU\t)\u0004jK\u0001J!\tQu*D\u0001L\u0015\taU*A\u0005v]\u000eDWmY6fI*\u0011ajI\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001)L\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003M\u0003\"\u0001V-\u000e\u0003US!AV,\u0002\t1\fgn\u001a\u0006\u00021\u0006!!.\u0019<b\u0013\tYT+\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001]!\t\u0011S,\u0003\u0002_G\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011\u0011\r\u001a\t\u0003E\tL!aY\u0012\u0003\u0007\u0005s\u0017\u0010C\u0004f\u0011\u0005\u0005\t\u0019\u0001/\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005A\u0007cA5mC6\t!N\u0003\u0002lG\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u00055T'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"\u0001]:\u0011\u0005\t\n\u0018B\u0001:$\u0005\u001d\u0011un\u001c7fC:Dq!\u001a\u0006\u0002\u0002\u0003\u0007\u0011-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GCA*w\u0011\u001d)7\"!AA\u0002q\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00029\u0006AAo\\*ue&tw\rF\u0001T\u0003\u0019)\u0017/^1mgR\u0011\u0001/ \u0005\bK:\t\t\u00111\u0001b\u0003-\u0019FO]5oO&s\u0007/\u001e;\u0011\u0005y\u00012#\u0002\t\u0002\u0004\u0005=\u0001CBA\u0003\u0003\u0017)\u0004)\u0004\u0002\u0002\b)\u0019\u0011\u0011B\u0012\u0002\u000fI,h\u000e^5nK&!\u0011QBA\u0004\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003#\t9\"\u0004\u0002\u0002\u0014)\u0019\u0011QC,\u0002\u0005%|\u0017bA\u0019\u0002\u0014Q\tq0A\u0003baBd\u0017\u0010F\u0002A\u0003?AQaM\nA\u0002U\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002&\u0005-\u0002\u0003\u0002\u0012\u0002(UJ1!!\u000b$\u0005\u0019y\u0005\u000f^5p]\"A\u0011Q\u0006\u000b\u0002\u0002\u0003\u0007\u0001)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\r\u0011\u0007Q\u000b)$C\u0002\u00028U\u0013aa\u00142kK\u000e$\b"
)
public class StringInput extends JsonInput {
   private final String string;

   public static Option unapply(final StringInput x$0) {
      return StringInput$.MODULE$.unapply(x$0);
   }

   public static StringInput apply(final String string) {
      return StringInput$.MODULE$.apply(string);
   }

   public static Function1 andThen(final Function1 g) {
      return StringInput$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StringInput$.MODULE$.compose(g);
   }

   public String string() {
      return this.string;
   }

   public StringInput copy(final String string) {
      return new StringInput(string);
   }

   public String copy$default$1() {
      return this.string();
   }

   public String productPrefix() {
      return "StringInput";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.string();
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
      return x$1 instanceof StringInput;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "string";
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
            if (x$1 instanceof StringInput) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     StringInput var4 = (StringInput)x$1;
                     String var10000 = this.string();
                     String var5 = var4.string();
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

   public StringInput(final String string) {
      this.string = string;
   }
}
