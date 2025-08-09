package spire.macros.fpf;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed\u0001C\u001e=!\u0003\r\n\u0003\u0011\"\b\u000f\u0005=D\b#\u0001A\u001f\u001a11\b\u0010E\u0001\u00012CQ!\u0014\u0002\u0005\u00029;Q!\u0015\u0002\t\u0002J3Q\u0001\u0016\u0002\t\u0002VCQ!T\u0003\u0005\u0002\u0019DqaZ\u0003\u0002\u0002\u0013\u0005\u0003\u000eC\u0004r\u000b\u0005\u0005I\u0011\u0001:\t\u000fY,\u0011\u0011!C\u0001o\"9Q0BA\u0001\n\u0003r\b\"CA\u0006\u000b\u0005\u0005I\u0011AA\u0007\u0011%\t9\"BA\u0001\n\u0003\nI\u0002C\u0005\u0002\u001c\u0015\t\t\u0011\"\u0011\u0002\u001e!I\u0011qD\u0003\u0002\u0002\u0013%\u0011\u0011E\u0004\b\u0003S\u0011\u0001\u0012QA\u0016\r\u001d\tiC\u0001EA\u0003_Aa!\u0014\t\u0005\u0002\u0005E\u0002bB4\u0011\u0003\u0003%\t\u0005\u001b\u0005\bcB\t\t\u0011\"\u0001s\u0011!1\b#!A\u0005\u0002\u0005M\u0002bB?\u0011\u0003\u0003%\tE \u0005\n\u0003\u0017\u0001\u0012\u0011!C\u0001\u0003oA\u0011\"a\u0006\u0011\u0003\u0003%\t%!\u0007\t\u0013\u0005m\u0001#!A\u0005B\u0005u\u0001\"CA\u0010!\u0005\u0005I\u0011BA\u0011\u000f\u001d\tYD\u0001EA\u0003{1q!a\u0010\u0003\u0011\u0003\u000b\t\u0005\u0003\u0004N7\u0011\u0005\u00111\t\u0005\bOn\t\t\u0011\"\u0011i\u0011\u001d\t8$!A\u0005\u0002ID\u0001B^\u000e\u0002\u0002\u0013\u0005\u0011Q\t\u0005\b{n\t\t\u0011\"\u0011\u007f\u0011%\tYaGA\u0001\n\u0003\tI\u0005C\u0005\u0002\u0018m\t\t\u0011\"\u0011\u0002\u001a!I\u00111D\u000e\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003?Y\u0012\u0011!C\u0005\u0003C9q!!\u0014\u0003\u0011\u0003\u000byEB\u0004\u0002R\tA\t)a\u0015\t\r53C\u0011AA+\u0011\u001d9g%!A\u0005B!Dq!\u001d\u0014\u0002\u0002\u0013\u0005!\u000f\u0003\u0005wM\u0005\u0005I\u0011AA,\u0011\u001dih%!A\u0005ByD\u0011\"a\u0003'\u0003\u0003%\t!a\u0017\t\u0013\u0005]a%!A\u0005B\u0005e\u0001\"CA\u000eM\u0005\u0005I\u0011IA\u000f\u0011%\tyBJA\u0001\n\u0013\t\tcB\u0004\u0002`\tA\t)!\u0019\u0007\r-\u0013\u0001\u0012QA2\u0011\u0019i\u0015\u0007\"\u0001\u0002f!9q-MA\u0001\n\u0003B\u0007bB92\u0003\u0003%\tA\u001d\u0005\tmF\n\t\u0011\"\u0001\u0002h!9Q0MA\u0001\n\u0003r\b\"CA\u0006c\u0005\u0005I\u0011AA6\u0011%\t9\"MA\u0001\n\u0003\nI\u0002C\u0005\u0002\u001cE\n\t\u0011\"\u0011\u0002\u001e!I\u0011qD\u0019\u0002\u0002\u0013%\u0011\u0011\u0005\u0002\u0004\u00076\u0004(BA\u001f?\u0003\r1\u0007O\u001a\u0006\u0003\u007f\u0001\u000ba!\\1de>\u001c(\"A!\u0002\u000bM\u0004\u0018N]3\u0014\u0005\u0001\u0019\u0005C\u0001#H\u001b\u0005)%\"\u0001$\u0002\u000bM\u001c\u0017\r\\1\n\u0005!+%AB!osJ+gm\u0001\u0001*\r\u0001\t\u0004CJ\u0003\u001c\u0005\t)\u0015o\u0005\u0002\u0003\u0007\u00061A(\u001b8jiz\"\u0012a\u0014\t\u0003!\ni\u0011\u0001P\u0001\u0003\u0019R\u0004\"aU\u0003\u000e\u0003\t\u0011!\u0001\u0014;\u0014\u000b\u0015\u0019ek\u0016.\u0011\u0005A\u0003\u0001C\u0001#Y\u0013\tIVIA\u0004Qe>$Wo\u0019;\u0011\u0005m\u001bgB\u0001/b\u001d\ti\u0006-D\u0001_\u0015\ty\u0016*\u0001\u0004=e>|GOP\u0005\u0002\r&\u0011!-R\u0001\ba\u0006\u001c7.Y4f\u0013\t!WM\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002c\u000bR\t!+A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002SB\u0011!n\\\u0007\u0002W*\u0011A.\\\u0001\u0005Y\u0006twMC\u0001o\u0003\u0011Q\u0017M^1\n\u0005A\\'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001t!\t!E/\u0003\u0002v\u000b\n\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011\u0001p\u001f\t\u0003\tfL!A_#\u0003\u0007\u0005s\u0017\u0010C\u0004}\u0013\u0005\u0005\t\u0019A:\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005y\b#BA\u0001\u0003\u000fAXBAA\u0002\u0015\r\t)!R\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0005\u0003\u0007\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qBA\u000b!\r!\u0015\u0011C\u0005\u0004\u0003')%a\u0002\"p_2,\u0017M\u001c\u0005\by.\t\t\u00111\u0001y\u0003!A\u0017m\u001d5D_\u0012,G#A:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012![\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003G\u00012A[A\u0013\u0013\r\t9c\u001b\u0002\u0007\u001f\nTWm\u0019;\u0002\u0005\u001d#\bCA*\u0011\u0005\t9EoE\u0003\u0011\u0007Z;&\f\u0006\u0002\u0002,Q\u0019\u00010!\u000e\t\u000fq$\u0012\u0011!a\u0001gR!\u0011qBA\u001d\u0011\u001dah#!AA\u0002a\fA\u0001\u0014;FcB\u00111k\u0007\u0002\u0005\u0019R,\u0015oE\u0003\u001c\u0007Z;&\f\u0006\u0002\u0002>Q\u0019\u00010a\u0012\t\u000fq|\u0012\u0011!a\u0001gR!\u0011qBA&\u0011\u001da\u0018%!AA\u0002a\fAa\u0012;FcB\u00111K\n\u0002\u0005\u000fR,\u0015oE\u0003'\u0007Z;&\f\u0006\u0002\u0002PQ\u0019\u00010!\u0017\t\u000fqT\u0013\u0011!a\u0001gR!\u0011qBA/\u0011\u001daH&!AA\u0002a\f!!R9\u0011\u0005M\u000b4#B\u0019D-^SFCAA1)\rA\u0018\u0011\u000e\u0005\byV\n\t\u00111\u0001t)\u0011\ty!!\u001c\t\u000fq<\u0014\u0011!a\u0001q\u0006\u00191)\u001c9"
)
public interface Cmp {
   public static class Lt$ implements Cmp, Product, Serializable {
      public static final Lt$ MODULE$ = new Lt$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Lt";
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
         return x$1 instanceof Lt$;
      }

      public int hashCode() {
         return 2472;
      }

      public String toString() {
         return "Lt";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Lt$.class);
      }
   }

   public static class Gt$ implements Cmp, Product, Serializable {
      public static final Gt$ MODULE$ = new Gt$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Gt";
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
         return x$1 instanceof Gt$;
      }

      public int hashCode() {
         return 2317;
      }

      public String toString() {
         return "Gt";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Gt$.class);
      }
   }

   public static class LtEq$ implements Cmp, Product, Serializable {
      public static final LtEq$ MODULE$ = new LtEq$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "LtEq";
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
         return x$1 instanceof LtEq$;
      }

      public int hashCode() {
         return 2377844;
      }

      public String toString() {
         return "LtEq";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(LtEq$.class);
      }
   }

   public static class GtEq$ implements Cmp, Product, Serializable {
      public static final GtEq$ MODULE$ = new GtEq$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "GtEq";
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
         return x$1 instanceof GtEq$;
      }

      public int hashCode() {
         return 2228889;
      }

      public String toString() {
         return "GtEq";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(GtEq$.class);
      }
   }

   public static class Eq$ implements Cmp, Product, Serializable {
      public static final Eq$ MODULE$ = new Eq$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Eq";
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
         return x$1 instanceof Eq$;
      }

      public int hashCode() {
         return 2252;
      }

      public String toString() {
         return "Eq";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Eq$.class);
      }
   }
}
