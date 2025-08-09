package org.apache.spark.sql.catalyst.streaming;

import java.io.Serializable;
import org.apache.spark.sql.streaming.OutputMode;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dsAB\u0013'\u0011\u0003Q#G\u0002\u00045M!\u0005!&\u000e\u0005\u0006y\u0005!\tAP\u0004\u0006\u007f\u0005A\t\t\u0011\u0004\u0006\u0005\u0006A\ti\u0011\u0005\u0006y\u0011!\t\u0001\u0017\u0005\b3\u0012\t\t\u0011\"\u0011[\u0011\u001d\u0019G!!A\u0005\u0002\u0011Dq\u0001\u001b\u0003\u0002\u0002\u0013\u0005\u0011\u000eC\u0004p\t\u0005\u0005I\u0011\t9\t\u000f]$\u0011\u0011!C\u0001q\"9Q\u0010BA\u0001\n\u0003r\b\u0002C@\u0005\u0003\u0003%\t%!\u0001\t\u0013\u0005\rA!!A\u0005\n\u0005\u0015qaBA\u0007\u0003!\u0005\u0015q\u0002\u0004\b\u0003#\t\u0001\u0012QA\n\u0011\u0019at\u0002\"\u0001\u0002\u0016!9\u0011lDA\u0001\n\u0003R\u0006bB2\u0010\u0003\u0003%\t\u0001\u001a\u0005\tQ>\t\t\u0011\"\u0001\u0002\u0018!9qnDA\u0001\n\u0003\u0002\b\u0002C<\u0010\u0003\u0003%\t!a\u0007\t\u000fu|\u0011\u0011!C!}\"AqpDA\u0001\n\u0003\n\t\u0001C\u0005\u0002\u0004=\t\t\u0011\"\u0003\u0002\u0006\u001d9\u0011qD\u0001\t\u0002\u0006\u0005baBA\u0012\u0003!\u0005\u0015Q\u0005\u0005\u0007yi!\t!a\n\t\u000feS\u0012\u0011!C!5\"91MGA\u0001\n\u0003!\u0007\u0002\u00035\u001b\u0003\u0003%\t!!\u000b\t\u000f=T\u0012\u0011!C!a\"AqOGA\u0001\n\u0003\ti\u0003C\u0004~5\u0005\u0005I\u0011\t@\t\u0011}T\u0012\u0011!C!\u0003\u0003A\u0011\"a\u0001\u001b\u0003\u0003%I!!\u0002\t\u000f\u0005E\u0012\u0001\"\u0001\u00024\u0005\u0019\u0012J\u001c;fe:\fGnT;uaV$Xj\u001c3fg*\u0011q\u0005K\u0001\ngR\u0014X-Y7j]\u001eT!!\u000b\u0016\u0002\u0011\r\fG/\u00197zgRT!a\u000b\u0017\u0002\u0007M\fHN\u0003\u0002.]\u0005)1\u000f]1sW*\u0011q\u0006M\u0001\u0007CB\f7\r[3\u000b\u0003E\n1a\u001c:h!\t\u0019\u0014!D\u0001'\u0005MIe\u000e^3s]\u0006dw*\u001e;qkRlu\u000eZ3t'\t\ta\u0007\u0005\u00028u5\t\u0001HC\u0001:\u0003\u0015\u00198-\u00197b\u0013\tY\u0004H\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t!'\u0001\u0004BaB,g\u000e\u001a\t\u0003\u0003\u0012i\u0011!\u0001\u0002\u0007\u0003B\u0004XM\u001c3\u0014\t\u0011!\u0015\n\u0014\t\u0003\u000b\u001ek\u0011A\u0012\u0006\u0003O)J!\u0001\u0013$\u0003\u0015=+H\u000f];u\u001b>$W\r\u0005\u00028\u0015&\u00111\n\u000f\u0002\b!J|G-^2u!\tiUK\u0004\u0002O':\u0011qJU\u0007\u0002!*\u0011\u0011+P\u0001\u0007yI|w\u000e\u001e \n\u0003eJ!\u0001\u0016\u001d\u0002\u000fA\f7m[1hK&\u0011ak\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003)b\"\u0012\u0001Q\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003m\u0003\"\u0001X1\u000e\u0003uS!AX0\u0002\t1\fgn\u001a\u0006\u0002A\u0006!!.\u0019<b\u0013\t\u0011WL\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002KB\u0011qGZ\u0005\u0003Ob\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A[7\u0011\u0005]Z\u0017B\u000179\u0005\r\te.\u001f\u0005\b]\"\t\t\u00111\u0001f\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t\u0011\u000fE\u0002sk*l\u0011a\u001d\u0006\u0003ib\n!bY8mY\u0016\u001cG/[8o\u0013\t18O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA=}!\t9$0\u0003\u0002|q\t9!i\\8mK\u0006t\u0007b\u00028\u000b\u0003\u0003\u0005\rA[\u0001\tQ\u0006\u001c\bnQ8eKR\tQ-\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0016\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u0004!\ra\u0016\u0011B\u0005\u0004\u0003\u0017i&AB(cU\u0016\u001cG/\u0001\u0005D_6\u0004H.\u001a;f!\t\tuB\u0001\u0005D_6\u0004H.\u001a;f'\u0011yA)\u0013'\u0015\u0005\u0005=Ac\u00016\u0002\u001a!9anEA\u0001\u0002\u0004)GcA=\u0002\u001e!9a.FA\u0001\u0002\u0004Q\u0017AB+qI\u0006$X\r\u0005\u0002B5\t1Q\u000b\u001d3bi\u0016\u001cBA\u0007#J\u0019R\u0011\u0011\u0011\u0005\u000b\u0004U\u0006-\u0002b\u00028\u001f\u0003\u0003\u0005\r!\u001a\u000b\u0004s\u0006=\u0002b\u00028!\u0003\u0003\u0005\rA[\u0001\u0006CB\u0004H.\u001f\u000b\u0004\t\u0006U\u0002bBA\u001cI\u0001\u0007\u0011\u0011H\u0001\u000b_V$\b/\u001e;N_\u0012,\u0007\u0003BA\u001e\u0003\u0007rA!!\u0010\u0002@A\u0011q\nO\u0005\u0004\u0003\u0003B\u0014A\u0002)sK\u0012,g-C\u0002c\u0003\u000bR1!!\u00119\u0001"
)
public final class InternalOutputModes {
   public static OutputMode apply(final String outputMode) {
      return InternalOutputModes$.MODULE$.apply(outputMode);
   }

   public static class Append$ extends OutputMode implements Product, Serializable {
      public static final Append$ MODULE$ = new Append$();

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
         return "Append";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Append$;
      }

      public int hashCode() {
         return 1967766330;
      }

      public String toString() {
         return "Append";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Append$.class);
      }
   }

   public static class Complete$ extends OutputMode implements Product, Serializable {
      public static final Complete$ MODULE$ = new Complete$();

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
         return "Complete";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Complete$;
      }

      public int hashCode() {
         return -534801063;
      }

      public String toString() {
         return "Complete";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Complete$.class);
      }
   }

   public static class Update$ extends OutputMode implements Product, Serializable {
      public static final Update$ MODULE$ = new Update$();

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
         return "Update";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Update$;
      }

      public int hashCode() {
         return -1754979095;
      }

      public String toString() {
         return "Update";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Update$.class);
      }
   }
}
