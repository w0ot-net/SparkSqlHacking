package scala.util.parsing.input;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\u000f\u001f\u0001\u001eB\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\u0003\"A!\n\u0001BK\u0002\u0013\u00051\n\u0003\u0005P\u0001\tE\t\u0015!\u0003M\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011!)\u0006\u0001#b\u0001\n\u00131\u0006\"\u0002.\u0001\t\u00131\u0006\"B.\u0001\t\u0003Y\u0005\"\u0002/\u0001\t\u0003Y\u0005\"B/\u0001\t\u0003q\u0006\"B4\u0001\t\u0003B\u0007\"B6\u0001\t\u0003b\u0007b\u0002:\u0001\u0003\u0003%\ta\u001d\u0005\bm\u0002\t\n\u0011\"\u0001x\u0011%\t)\u0001AI\u0001\n\u0003\t9\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!A\u0011q\u0002\u0001\u0002\u0002\u0013\u00051\nC\u0005\u0002\u0012\u0001\t\t\u0011\"\u0001\u0002\u0014!I\u0011q\u0004\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003_\u0001\u0011\u0011!C\u0001\u0003cA\u0011\"!\u000e\u0001\u0003\u0003%\t%a\u000e\t\u0013\u0005m\u0002!!A\u0005B\u0005uraBA =!\u0005\u0011\u0011\t\u0004\u0007;yA\t!a\u0011\t\rACB\u0011AA1\u0011%\t\u0019\u0007GA\u0001\n\u0003\u000b)\u0007C\u0005\u0002la\t\t\u0011\"!\u0002n!I\u0011q\u0010\r\u0002\u0002\u0013%\u0011\u0011\u0011\u0002\u000f\u001f\u001a47/\u001a;Q_NLG/[8o\u0015\ty\u0002%A\u0003j]B,HO\u0003\u0002\"E\u00059\u0001/\u0019:tS:<'BA\u0012%\u0003\u0011)H/\u001b7\u000b\u0003\u0015\nQa]2bY\u0006\u001c\u0001aE\u0003\u0001Q1\u00024\u0007\u0005\u0002*U5\tA%\u0003\u0002,I\t1\u0011I\\=SK\u001a\u0004\"!\f\u0018\u000e\u0003yI!a\f\u0010\u0003\u0011A{7/\u001b;j_:\u0004\"!K\u0019\n\u0005I\"#a\u0002)s_\u0012,8\r\u001e\t\u0003iqr!!\u000e\u001e\u000f\u0005YJT\"A\u001c\u000b\u0005a2\u0013A\u0002\u001fs_>$h(C\u0001&\u0013\tYD%A\u0004qC\u000e\\\u0017mZ3\n\u0005ur$\u0001D*fe&\fG.\u001b>bE2,'BA\u001e%\u0003\u0019\u0019x.\u001e:dKV\t\u0011\t\u0005\u0002C\u000f6\t1I\u0003\u0002E\u000b\u0006!A.\u00198h\u0015\u00051\u0015\u0001\u00026bm\u0006L!\u0001S\"\u0003\u0019\rC\u0017M]*fcV,gnY3\u0002\u000fM|WO]2fA\u00051qN\u001a4tKR,\u0012\u0001\u0014\t\u0003S5K!A\u0014\u0013\u0003\u0007%sG/A\u0004pM\u001a\u001cX\r\u001e\u0011\u0002\rqJg.\u001b;?)\r\u00116\u000b\u0016\t\u0003[\u0001AQaP\u0003A\u0002\u0005CQAS\u0003A\u00021\u000bQ!\u001b8eKb,\u0012a\u0016\t\u0004Sac\u0015BA-%\u0005\u0015\t%O]1z\u0003!9WM\\%oI\u0016D\u0018\u0001\u00027j]\u0016\faaY8mk6t\u0017\u0001\u00047j]\u0016\u001cuN\u001c;f]R\u001cX#A0\u0011\u0005\u0001$gBA1c!\t1D%\u0003\u0002dI\u00051\u0001K]3eK\u001aL!!\u001a4\u0003\rM#(/\u001b8h\u0015\t\u0019G%\u0001\u0005u_N#(/\u001b8h)\u0005I\u0007C\u0001\"k\u0013\t)7)A\u0003%Y\u0016\u001c8\u000f\u0006\u0002naB\u0011\u0011F\\\u0005\u0003_\u0012\u0012qAQ8pY\u0016\fg\u000eC\u0003r\u0019\u0001\u0007A&\u0001\u0003uQ\u0006$\u0018\u0001B2paf$2A\u0015;v\u0011\u001dyT\u0002%AA\u0002\u0005CqAS\u0007\u0011\u0002\u0003\u0007A*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003aT#!Q=,\u0003i\u00042a_A\u0001\u001b\u0005a(BA?\u007f\u0003%)hn\u00195fG.,GM\u0003\u0002\u0000I\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005\rAPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\n)\u0012A*_\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003%\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0016\u0005m\u0001cA\u0015\u0002\u0018%\u0019\u0011\u0011\u0004\u0013\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\u001eI\t\t\u00111\u0001M\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u0005\t\u0007\u0003K\tY#!\u0006\u000e\u0005\u0005\u001d\"bAA\u0015I\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u00055\u0012q\u0005\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002n\u0003gA\u0011\"!\b\u0015\u0003\u0003\u0005\r!!\u0006\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004S\u0006e\u0002\u0002CA\u000f+\u0005\u0005\t\u0019\u0001'\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001T\u0001\u000f\u001f\u001a47/\u001a;Q_NLG/[8o!\ti\u0003dE\u0004\u0019\u0003\u000b\n\t&a\u0016\u0011\u000f\u0005\u001d\u0013QJ!M%6\u0011\u0011\u0011\n\u0006\u0004\u0003\u0017\"\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u001f\nIEA\tBEN$(/Y2u\rVt7\r^5p]J\u00022!LA*\u0013\r\t)F\b\u0002\u000e!>\u001c\u0018\u000e^5p]\u000e\u000b7\r[3\u0011\t\u0005e\u0013qL\u0007\u0003\u00037R1!!\u0018F\u0003\tIw.C\u0002>\u00037\"\"!!\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000bI\u000b9'!\u001b\t\u000b}R\u0002\u0019A!\t\u000b)S\u0002\u0019\u0001'\u0002\u000fUt\u0017\r\u001d9msR!\u0011qNA>!\u0015I\u0013\u0011OA;\u0013\r\t\u0019\b\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b%\n9(\u0011'\n\u0007\u0005eDE\u0001\u0004UkBdWM\r\u0005\t\u0003{Z\u0012\u0011!a\u0001%\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\r\u0005c\u0001\"\u0002\u0006&\u0019\u0011qQ\"\u0003\r=\u0013'.Z2u\u0001"
)
public class OffsetPosition implements Position, Product, Serializable {
   private int[] index;
   private final CharSequence source;
   private final int offset;
   private volatile boolean bitmap$0;

   public static Option unapply(final OffsetPosition x$0) {
      return OffsetPosition$.MODULE$.unapply(x$0);
   }

   public static OffsetPosition apply(final CharSequence source, final int offset) {
      return OffsetPosition$.MODULE$.apply(source, offset);
   }

   public static Function1 tupled() {
      return OffsetPosition$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return OffsetPosition$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String longString() {
      return Position.longString$(this);
   }

   public boolean equals(final Object other) {
      return Position.equals$(this, other);
   }

   public CharSequence source() {
      return this.source;
   }

   public int offset() {
      return this.offset;
   }

   private int[] index$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            Option var3 = .MODULE$.apply(OffsetPosition$.MODULE$.indexCache().get(this.source()));
            int[] var10001;
            if (var3 instanceof Some) {
               Some var4 = (Some)var3;
               int[] index = (int[])var4.value();
               var10001 = index;
            } else {
               if (!scala.None..MODULE$.equals(var3)) {
                  throw new MatchError(var3);
               }

               int[] index = this.genIndex();
               OffsetPosition$.MODULE$.indexCache().put(this.source(), index);
               var10001 = index;
            }

            this.index = var10001;
            this.bitmap$0 = true;
         }
      } catch (Throwable var8) {
         throw var8;
      }

      return this.index;
   }

   private int[] index() {
      return !this.bitmap$0 ? this.index$lzycompute() : this.index;
   }

   private int[] genIndex() {
      ArrayBuffer lineStarts = new ArrayBuffer();
      lineStarts.$plus$eq(BoxesRunTime.boxToInteger(0));
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.source().length()).foreach((i) -> $anonfun$genIndex$1(this, lineStarts, BoxesRunTime.unboxToInt(i)));
      lineStarts.$plus$eq(BoxesRunTime.boxToInteger(this.source().length()));
      return (int[])lineStarts.toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   public int line() {
      int lo = 0;
      int hi = this.index().length - 1;

      while(lo + 1 < hi) {
         int mid = lo + (hi - lo) / 2;
         if (this.offset() < this.index()[mid]) {
            hi = mid;
         } else {
            lo = mid;
         }
      }

      return lo + 1;
   }

   public int column() {
      return this.offset() - this.index()[this.line() - 1] + 1;
   }

   public String lineContents() {
      int lineStart = this.index()[this.line() - 1];
      int lineEnd = this.index()[this.line()];
      int endIndex = lineStart < lineEnd - 1 && this.source().charAt(lineEnd - 2) == '\r' && this.source().charAt(lineEnd - 1) == '\n' ? lineEnd - 2 : (lineStart >= lineEnd || this.source().charAt(lineEnd - 1) != '\r' && this.source().charAt(lineEnd - 1) != '\n' ? lineEnd : lineEnd - 1);
      return this.source().subSequence(lineStart, endIndex).toString();
   }

   public String toString() {
      return (new StringBuilder(1)).append(this.line()).append(".").append(this.column()).toString();
   }

   public boolean $less(final Position that) {
      if (that instanceof OffsetPosition) {
         OffsetPosition var4 = (OffsetPosition)that;
         int that_offset = var4.offset();
         return this.offset() < that_offset;
      } else {
         return this.line() < that.line() || this.line() == that.line() && this.column() < that.column();
      }
   }

   public OffsetPosition copy(final CharSequence source, final int offset) {
      return new OffsetPosition(source, offset);
   }

   public CharSequence copy$default$1() {
      return this.source();
   }

   public int copy$default$2() {
      return this.offset();
   }

   public String productPrefix() {
      return "OffsetPosition";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.source();
         case 1:
            return BoxesRunTime.boxToInteger(this.offset());
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof OffsetPosition;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "source";
         case 1:
            return "offset";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.source()));
      var1 = Statics.mix(var1, this.offset());
      return Statics.finalizeHash(var1, 2);
   }

   // $FF: synthetic method
   public static final Object $anonfun$genIndex$1(final OffsetPosition $this, final ArrayBuffer lineStarts$1, final int i) {
      return $this.source().charAt(i) != '\n' && ($this.source().charAt(i) != '\r' || i != $this.source().length() - 1 && $this.source().charAt(i + 1) == '\n') ? BoxedUnit.UNIT : lineStarts$1.$plus$eq(BoxesRunTime.boxToInteger(i + 1));
   }

   public OffsetPosition(final CharSequence source, final int offset) {
      this.source = source;
      this.offset = offset;
      Position.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
