package cats.kernel;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f!B\u001b7\u0003CY\u0004\u0002C)\u0001\u0005\u000b\u0007I\u0011\u0001*\t\u0011Y\u0003!\u0011!Q\u0001\nMC\u0001b\u0016\u0001\u0003\u0006\u0004%\t\u0001\u0017\u0005\t9\u0002\u0011\t\u0011)A\u00053\")Q\f\u0001C\u0001=\u001e1\u0011Q\f\u001c\t\u000294Q!\u000e\u001c\t\u0002\u0015DQ!X\u0004\u0005\u00025<Qa\\\u0004\t\u0002B4QA]\u0004\t\u0002NDQ!\u0018\u0006\u0005\u0002QDq!\u001e\u0006\u0002\u0002\u0013\u0005c\u000fC\u0004~\u0015\u0005\u0005I\u0011\u0001*\t\u000fyT\u0011\u0011!C\u0001\u007f\"I\u00111\u0002\u0006\u0002\u0002\u0013\u0005\u0013Q\u0002\u0005\n\u00037Q\u0011\u0011!C\u0001\u0003;A\u0011\"a\n\u000b\u0003\u0003%\t%!\u000b\t\u0013\u0005-\"\"!A\u0005B\u00055\u0002\"CA\u0018\u0015\u0005\u0005I\u0011BA\u0019\u000f\u001d\tId\u0002EA\u0003w1a\u0001Z\u0004\t\u0002\u0006\r\u0006BB/\u0016\t\u0003\t)\u000bC\u0004v+\u0005\u0005I\u0011\t<\t\u000fu,\u0012\u0011!C\u0001%\"Aa0FA\u0001\n\u0003\t9\u000bC\u0005\u0002\fU\t\t\u0011\"\u0011\u0002\u000e!I\u00111D\u000b\u0002\u0002\u0013\u0005\u00111\u0016\u0005\n\u0003O)\u0012\u0011!C!\u0003SA\u0011\"a\u000b\u0016\u0003\u0003%\t%!\f\t\u0013\u0005=R#!A\u0005\n\u0005EraBA\u001f\u000f!\u0005\u0015q\b\u0004\b\u0003\u0003:\u0001\u0012QA\"\u0011\u0019i\u0006\u0005\"\u0001\u0002F!9Q\u000fIA\u0001\n\u00032\bbB?!\u0003\u0003%\tA\u0015\u0005\t}\u0002\n\t\u0011\"\u0001\u0002H!I\u00111\u0002\u0011\u0002\u0002\u0013\u0005\u0013Q\u0002\u0005\n\u00037\u0001\u0013\u0011!C\u0001\u0003\u0017B\u0011\"a\n!\u0003\u0003%\t%!\u000b\t\u0013\u0005-\u0002%!A\u0005B\u00055\u0002\"CA\u0018A\u0005\u0005I\u0011BA\u0019\u0011%\tye\u0002b\u0001\n\u0013\t\t\u0006\u0003\u0005\u0002`\u001d\u0001\u000b\u0011BA*\u0011%\t\tg\u0002b\u0001\n\u0013\t\u0019\u0007\u0003\u0005\u0002j\u001d\u0001\u000b\u0011BA3\u0011%\tYg\u0002b\u0001\n\u0013\ti\u0007\u0003\u0005\u0002t\u001d\u0001\u000b\u0011BA8\u0011\u001d\t)h\u0002C\u0001\u0003oBq!! \b\t\u0003\ty\bC\u0005\u0002\f\u001e\u0011\r\u0011b\u0001\u0002\u000e\"A\u0011\u0011U\u0004!\u0002\u0013\ty\tC\u0005\u00020\u001d\t\t\u0011\"\u0003\u00022\tQ1i\\7qCJL7o\u001c8\u000b\u0005]B\u0014AB6fe:,GNC\u0001:\u0003\u0011\u0019\u0017\r^:\u0004\u0001M!\u0001\u0001\u0010\"F!\ti\u0004)D\u0001?\u0015\u0005y\u0014!B:dC2\f\u0017BA!?\u0005\u0019\te.\u001f*fMB\u0011QhQ\u0005\u0003\tz\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002G\u001d:\u0011q\t\u0014\b\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015j\na\u0001\u0010:p_Rt\u0014\"A \n\u00055s\u0014a\u00029bG.\fw-Z\u0005\u0003\u001fB\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!\u0014 \u0002\u000bQ|\u0017J\u001c;\u0016\u0003M\u0003\"!\u0010+\n\u0005Us$aA%oi\u00061Ao\\%oi\u0002\n\u0001\u0002^8E_V\u0014G.Z\u000b\u00023B\u0011QHW\u0005\u00037z\u0012a\u0001R8vE2,\u0017!\u0003;p\t>,(\r\\3!\u0003\u0019a\u0014N\\5u}Q\u0019q,\u00192\u0011\u0005\u0001\u0004Q\"\u0001\u001c\t\u000bE+\u0001\u0019A*\t\u000b]+\u0001\u0019A-*\t\u0001)\"\u0002\t\u0002\b\u000bF,\u0018\r\u001c+p'\r9AH\u001a\t\u0003O2l\u0011\u0001\u001b\u0006\u0003S*\f!![8\u000b\u0003-\fAA[1wC&\u0011q\n\u001b\u000b\u0002]B\u0011\u0001mB\u0001\f\u000fJ,\u0017\r^3s)\"\fg\u000e\u0005\u0002r\u00155\tqAA\u0006He\u0016\fG/\u001a:UQ\u0006t7\u0003\u0002\u0006`\u0005\u0016#\u0012\u0001]\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003]\u0004\"\u0001_>\u000e\u0003eT!A\u001f6\u0002\t1\fgnZ\u0005\u0003yf\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u0003\t9\u0001E\u0002>\u0003\u0007I1!!\u0002?\u0005\r\te.\u001f\u0005\t\u0003\u0013q\u0011\u0011!a\u0001'\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0004\u0011\r\u0005E\u0011qCA\u0001\u001b\t\t\u0019BC\u0002\u0002\u0016y\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI\"a\u0005\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003?\t)\u0003E\u0002>\u0003CI1!a\t?\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\u0003\u0011\u0003\u0003\u0005\r!!\u0001\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012aU\u0001\ti>\u001cFO]5oOR\tq/\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00024A\u0019\u00010!\u000e\n\u0007\u0005]\u0012P\u0001\u0004PE*,7\r^\u0001\b\u000bF,\u0018\r\u001c+p!\t\tX#\u0001\u0005MKN\u001cH\u000b[1o!\t\t\bE\u0001\u0005MKN\u001cH\u000b[1o'\u0011\u0001sLQ#\u0015\u0005\u0005}B\u0003BA\u0001\u0003\u0013B\u0001\"!\u0003%\u0003\u0003\u0005\ra\u0015\u000b\u0005\u0003?\ti\u0005C\u0005\u0002\n\u0019\n\t\u00111\u0001\u0002\u0002\u000511k\\7f\u000fR,\"!a\u0015\u0011\u000bu\n)&!\u0017\n\u0007\u0005]cH\u0001\u0003T_6,gbAA.\u00139\u0011\u0001MB\u0001\u000b\u0007>l\u0007/\u0019:jg>t\u0017aB*p[\u0016<E\u000fI\u0001\u0007'>lW-R9\u0016\u0005\u0005\u0015\u0004#B\u001f\u0002V\u0005\u001ddbAA.)\u000591k\\7f\u000bF\u0004\u0013AB*p[\u0016dE/\u0006\u0002\u0002pA)Q(!\u0016\u0002r9\u0019\u00111L\u0010\u0002\u000fM{W.\u001a'uA\u00059aM]8n\u0013:$HcA0\u0002z!1\u00111\u0010\u0019A\u0002M\u000b1!\u001b8u\u0003)1'o\\7E_V\u0014G.\u001a\u000b\u0005\u0003\u0003\u000b9\t\u0005\u0003>\u0003\u0007{\u0016bAAC}\t1q\n\u001d;j_:Da!!#2\u0001\u0004I\u0016A\u00023pk\ndW-A\rdCR\u001c8*\u001a:oK2,\u0015OR8s\u0007>l\u0007/\u0019:jg>tWCAAH%\u0019\t\t*!&\u0002\u001c\u001a1\u00111S\u0004\u0001\u0003\u001f\u0013A\u0002\u0010:fM&tW-\\3oiz\u0002B\u0001YAL?&\u0019\u0011\u0011\u0014\u001c\u0003\u0005\u0015\u000b\b\u0003\u00021\u0002\u001e~K1!a(7\u0005\u0019iuN\\8jI\u0006Q2-\u0019;t\u0017\u0016\u0014h.\u001a7Fc\u001a{'oQ8na\u0006\u0014\u0018n]8oAM!Qc\u0018\"F)\t\tY\u0004\u0006\u0003\u0002\u0002\u0005%\u0006\u0002CA\u00053\u0005\u0005\t\u0019A*\u0015\t\u0005}\u0011Q\u0016\u0005\n\u0003\u0013Y\u0012\u0011!a\u0001\u0003\u0003\u0001"
)
public abstract class Comparison implements Product, Serializable {
   private final int toInt;
   private final double toDouble;

   public static Eq catsKernelEqForComparison() {
      return Comparison$.MODULE$.catsKernelEqForComparison();
   }

   public static Option fromDouble(final double double) {
      return Comparison$.MODULE$.fromDouble(double);
   }

   public static Comparison fromInt(final int int) {
      return Comparison$.MODULE$.fromInt(int);
   }

   public Iterator productIterator() {
      return Product.productIterator$(this);
   }

   public String productPrefix() {
      return Product.productPrefix$(this);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int toInt() {
      return this.toInt;
   }

   public double toDouble() {
      return this.toDouble;
   }

   public Comparison(final int toInt, final double toDouble) {
      this.toInt = toInt;
      this.toDouble = toDouble;
      Product.$init$(this);
   }

   public static class GreaterThan$ extends Comparison {
      public static final GreaterThan$ MODULE$ = new GreaterThan$();

      public String productPrefix() {
         return "GreaterThan";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof GreaterThan$;
      }

      public int hashCode() {
         return -1701951333;
      }

      public String toString() {
         return "GreaterThan";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(GreaterThan$.class);
      }

      public GreaterThan$() {
         super(1, (double)1.0F);
      }
   }

   public static class EqualTo$ extends Comparison {
      public static final EqualTo$ MODULE$ = new EqualTo$();

      public String productPrefix() {
         return "EqualTo";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof EqualTo$;
      }

      public int hashCode() {
         return 159386799;
      }

      public String toString() {
         return "EqualTo";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EqualTo$.class);
      }

      public EqualTo$() {
         super(0, (double)0.0F);
      }
   }

   public static class LessThan$ extends Comparison {
      public static final LessThan$ MODULE$ = new LessThan$();

      public String productPrefix() {
         return "LessThan";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LessThan$;
      }

      public int hashCode() {
         return -2140646662;
      }

      public String toString() {
         return "LessThan";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(LessThan$.class);
      }

      public LessThan$() {
         super(-1, (double)-1.0F);
      }
   }
}
