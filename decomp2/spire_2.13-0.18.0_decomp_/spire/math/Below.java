package spire.math;

import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.interval.Closed;
import spire.math.interval.Open;
import spire.math.interval.Unbound;
import spire.math.interval.ValueBound;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=e\u0001\u0002\u000e\u001c\u0001\u0002B\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005M!Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005L\u0001\tE\t\u0015!\u0003I\u0011\u0019a\u0005\u0001\"\u0001\u001e\u001b\")\u0011\u000b\u0001C\u0001%\")\u0011\f\u0001C\u00015\"9a\fAA\u0001\n\u0003y\u0006b\u00024\u0001#\u0003%\ta\u001a\u0005\bi\u0002\t\n\u0011\"\u0001v\u0011\u001dI\b!!A\u0005BiD\u0001\"a\u0002\u0001\u0003\u0003%\ta\u0012\u0005\n\u0003\u0013\u0001\u0011\u0011!C\u0001\u0003\u0017A\u0011\"!\u0005\u0001\u0003\u0003%\t%a\u0005\t\u0013\u0005\u0005\u0002!!A\u0005\u0002\u0005\r\u0002\"CA\u0017\u0001\u0005\u0005I\u0011IA\u0018\u0011%\t\u0019\u0004AA\u0001\n\u0003\n)\u0004C\u0005\u00028\u0001\t\t\u0011\"\u0011\u0002:\u001dI\u0011QH\u000e\u0002\u0002#\u0005\u0011q\b\u0004\t5m\t\t\u0011#\u0001\u0002B!1A\n\u0006C\u0001\u0003'B\u0011\"!\u0016\u0015\u0003\u0003%)%a\u0016\t\u0013\u0005eC#!A\u0005\u0002\u0006m\u0003\"CA5)\u0005\u0005I\u0011QA6\u0011%\t)\tFA\u0001\n\u0013\t9IA\u0003CK2|wO\u0003\u0002\u001d;\u0005!Q.\u0019;i\u0015\u0005q\u0012!B:qSJ,7\u0001A\u000b\u0003C!\u001aB\u0001\u0001\u00125oA\u00191\u0005\n\u0014\u000e\u0003mI!!J\u000e\u0003\u0011%sG/\u001a:wC2\u0004\"a\n\u0015\r\u0001\u0011)\u0011\u0006\u0001b\u0001U\t\t\u0011)\u0005\u0002,cA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t9aj\u001c;iS:<\u0007C\u0001\u00173\u0013\t\u0019TFA\u0002B]f\u0004\"\u0001L\u001b\n\u0005Yj#a\u0002)s_\u0012,8\r\u001e\t\u0003q\u0001s!!\u000f \u000f\u0005ijT\"A\u001e\u000b\u0005qz\u0012A\u0002\u001fs_>$h(C\u0001/\u0013\tyT&A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0005\u0013%\u0001D*fe&\fG.\u001b>bE2,'BA .\u0003\u0015)\b\u000f]3s+\u00051\u0013AB;qa\u0016\u0014\b%A\u0003gY\u0006<7/F\u0001I!\ta\u0013*\u0003\u0002K[\t\u0019\u0011J\u001c;\u0002\r\u0019d\u0017mZ:!\u0003\u0019a\u0014N\\5u}Q\u0019aj\u0014)\u0011\u0007\r\u0002a\u0005C\u0003D\u000b\u0001\u0007a\u0005C\u0003G\u000b\u0001\u0007\u0001*\u0001\u0006m_^,'OQ8v]\u0012,\u0012a\u0015\t\u0004)^3S\"A+\u000b\u0005Y[\u0012\u0001C5oi\u0016\u0014h/\u00197\n\u0005a+&aB+oE>,h\u000eZ\u0001\u000bkB\u0004XM\u001d\"pk:$W#A.\u0011\u0007Qcf%\u0003\u0002^+\nQa+\u00197vK\n{WO\u001c3\u0002\t\r|\u0007/_\u000b\u0003A\u000e$2!\u00193f!\r\u0019\u0003A\u0019\t\u0003O\r$Q!\u000b\u0005C\u0002)Bqa\u0011\u0005\u0011\u0002\u0003\u0007!\rC\u0004G\u0011A\u0005\t\u0019\u0001%\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0001n]\u000b\u0002S*\u0012aE[\u0016\u0002WB\u0011A.]\u0007\u0002[*\u0011an\\\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001]\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002s[\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b%J!\u0019\u0001\u0016\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011a\u000f_\u000b\u0002o*\u0012\u0001J\u001b\u0003\u0006S)\u0011\rAK\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003m\u00042\u0001`A\u0002\u001b\u0005i(B\u0001@\u0000\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u0005\u0011\u0001\u00026bm\u0006L1!!\u0002~\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA\u0019\u0002\u000e!A\u0011qB\u0007\u0002\u0002\u0003\u0007\u0001*A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003+\u0001R!a\u0006\u0002\u001eEj!!!\u0007\u000b\u0007\u0005mQ&\u0001\u0006d_2dWm\u0019;j_:LA!a\b\u0002\u001a\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t)#a\u000b\u0011\u00071\n9#C\u0002\u0002*5\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0010=\t\t\u00111\u00012\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007m\f\t\u0004\u0003\u0005\u0002\u0010A\t\t\u00111\u0001I\u0003!A\u0017m\u001d5D_\u0012,G#\u0001%\u0002\r\u0015\fX/\u00197t)\u0011\t)#a\u000f\t\u0011\u0005=!#!AA\u0002E\nQAQ3m_^\u0004\"a\t\u000b\u0014\u000bQ\t\u0019%!\u0013\u0011\u00071\n)%C\u0002\u0002H5\u0012a!\u00118z%\u00164\u0007\u0003BA&\u0003#j!!!\u0014\u000b\u0007\u0005=s0\u0001\u0002j_&\u0019\u0011)!\u0014\u0015\u0005\u0005}\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003m\fQ!\u00199qYf,B!!\u0018\u0002dQ1\u0011qLA3\u0003O\u0002Ba\t\u0001\u0002bA\u0019q%a\u0019\u0005\u000b%:\"\u0019\u0001\u0016\t\r\r;\u0002\u0019AA1\u0011\u00151u\u00031\u0001I\u0003\u001d)h.\u00199qYf,B!!\u001c\u0002~Q!\u0011qNA@!\u0015a\u0013\u0011OA;\u0013\r\t\u0019(\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r1\n9(a\u001fI\u0013\r\tI(\f\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007\u001d\ni\bB\u0003*1\t\u0007!\u0006C\u0005\u0002\u0002b\t\t\u00111\u0001\u0002\u0004\u0006\u0019\u0001\u0010\n\u0019\u0011\t\r\u0002\u00111P\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0013\u00032\u0001`AF\u0013\r\ti) \u0002\u0007\u001f\nTWm\u0019;"
)
public class Below extends Interval implements Product {
   private final Object upper;
   private final int flags;

   public static Option unapply(final Below x$0) {
      return Below$.MODULE$.unapply(x$0);
   }

   public static Below apply(final Object upper, final int flags) {
      return Below$.MODULE$.apply(upper, flags);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object upper() {
      return this.upper;
   }

   public int flags() {
      return this.flags;
   }

   public Unbound lowerBound() {
      return new Unbound();
   }

   public ValueBound upperBound() {
      return (ValueBound)(this.isOpenUpper(this.flags()) ? new Open(this.upper()) : new Closed(this.upper()));
   }

   public Below copy(final Object upper, final int flags) {
      return new Below(upper, flags);
   }

   public Object copy$default$1() {
      return this.upper();
   }

   public int copy$default$2() {
      return this.flags();
   }

   public String productPrefix() {
      return "Below";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.upper();
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.flags());
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
      return x$1 instanceof Below;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "upper";
            break;
         case 1:
            var10000 = "flags";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.upper()));
      var1 = Statics.mix(var1, this.flags());
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Below) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Below var4 = (Below)x$1;
               if (this.flags() == var4.flags() && BoxesRunTime.equals(this.upper(), var4.upper()) && var4.canEqual(this)) {
                  break label51;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Below(final Object upper, final int flags) {
      this.upper = upper;
      this.flags = flags;
      Product.$init$(this);
   }
}
