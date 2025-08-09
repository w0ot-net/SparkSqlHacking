package breeze.signal;

import breeze.util.Opt;
import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-b!\u0002\u0014(\u0003\u0003a\u0003\"B\u001a\u0001\t\u0003!t!B\u001c(\u0011\u0003Ad!\u0002\u0014(\u0011\u0003I\u0004\"B\u001a\u0004\t\u0003\u0001u!B!\u0004\u0011\u0003\u0013e!\u0002#\u0004\u0011\u0003+\u0005\"B\u001a\u0007\t\u0003)\u0006b\u0002,\u0007\u0003\u0003%\te\u0016\u0005\bA\u001a\t\t\u0011\"\u0001b\u0011\u001d)g!!A\u0005\u0002\u0019Dq\u0001\u001c\u0004\u0002\u0002\u0013\u0005S\u000eC\u0004u\r\u0005\u0005I\u0011A;\t\u000fi4\u0011\u0011!C!w\"9APBA\u0001\n\u0003j\bb\u0002@\u0007\u0003\u0003%Ia`\u0004\b\u0003\u000f\u0019\u0001\u0012QA\u0005\r\u001d\tYa\u0001EA\u0003\u001bAaaM\t\u0005\u0002\u0005=\u0001b\u0002,\u0012\u0003\u0003%\te\u0016\u0005\bAF\t\t\u0011\"\u0001b\u0011!)\u0017#!A\u0005\u0002\u0005E\u0001b\u00027\u0012\u0003\u0003%\t%\u001c\u0005\tiF\t\t\u0011\"\u0001\u0002\u0016!9!0EA\u0001\n\u0003Z\bb\u0002?\u0012\u0003\u0003%\t% \u0005\b}F\t\t\u0011\"\u0003\u0000\u000f\u001d\tIb\u0001EA\u000371q!!\b\u0004\u0011\u0003\u000by\u0002\u0003\u000449\u0011\u0005\u0011\u0011\u0005\u0005\b-r\t\t\u0011\"\u0011X\u0011\u001d\u0001G$!A\u0005\u0002\u0005D\u0001\"\u001a\u000f\u0002\u0002\u0013\u0005\u00111\u0005\u0005\bYr\t\t\u0011\"\u0011n\u0011!!H$!A\u0005\u0002\u0005\u001d\u0002b\u0002>\u001d\u0003\u0003%\te\u001f\u0005\byr\t\t\u0011\"\u0011~\u0011\u001dqH$!A\u0005\n}\u00141b\u00149u\u001fZ,'\u000f[1oO*\u0011\u0001&K\u0001\u0007g&<g.\u00197\u000b\u0003)\naA\u0019:fKj,7\u0001A\n\u0003\u00015\u0002\"AL\u0019\u000e\u0003=R!\u0001M\u0015\u0002\tU$\u0018\u000e\\\u0005\u0003e=\u00121a\u00149u\u0003\u0019a\u0014N\\5u}Q\tQ\u0007\u0005\u00027\u00015\tq%A\u0006PaR|e/\u001a:iC:<\u0007C\u0001\u001c\u0004'\t\u0019!\b\u0005\u0002<}5\tAHC\u0001>\u0003\u0015\u00198-\u00197b\u0013\tyDH\u0001\u0004B]f\u0014VM\u001a\u000b\u0002q\u0005!aj\u001c8f!\t\u0019e!D\u0001\u0004\u0005\u0011quN\\3\u0014\t\u0019)d)\u0013\t\u0003w\u001dK!\u0001\u0013\u001f\u0003\u000fA\u0013x\u000eZ;diB\u0011!J\u0015\b\u0003\u0017Bs!\u0001T(\u000e\u00035S!AT\u0016\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0014BA)=\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0015+\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005EcD#\u0001\"\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\u0006CA-_\u001b\u0005Q&BA.]\u0003\u0011a\u0017M\\4\u000b\u0003u\u000bAA[1wC&\u0011qL\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\t\u0004\"aO2\n\u0005\u0011d$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA4k!\tY\u0004.\u0003\u0002jy\t\u0019\u0011I\\=\t\u000f-T\u0011\u0011!a\u0001E\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A\u001c\t\u0004_J<W\"\u00019\u000b\u0005Ed\u0014AC2pY2,7\r^5p]&\u00111\u000f\u001d\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002wsB\u00111h^\u0005\u0003qr\u0012qAQ8pY\u0016\fg\u000eC\u0004l\u0019\u0005\u0005\t\u0019A4\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AY\u0001\ti>\u001cFO]5oOR\t\u0001,\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0002A\u0019\u0011,a\u0001\n\u0007\u0005\u0015!L\u0001\u0004PE*,7\r^\u0001\u0005\rVdG\u000e\u0005\u0002D#\t!a)\u001e7m'\u0011\tRGR%\u0015\u0005\u0005%AcA4\u0002\u0014!91.FA\u0001\u0002\u0004\u0011Gc\u0001<\u0002\u0018!91nFA\u0001\u0002\u00049\u0017A\u0004)sKN,'O^3MK:<G\u000f\u001b\t\u0003\u0007r\u0011a\u0002\u0015:fg\u0016\u0014h/\u001a'f]\u001e$\bn\u0005\u0003\u001dk\u0019KECAA\u000e)\r9\u0017Q\u0005\u0005\bW\u0002\n\t\u00111\u0001c)\r1\u0018\u0011\u0006\u0005\bW\n\n\t\u00111\u0001h\u0001"
)
public abstract class OptOverhang extends Opt {
   public static class None$ extends OptOverhang implements Product, Serializable {
      public static final None$ MODULE$ = new None$();

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
         return "None";
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
         return x$1 instanceof None$;
      }

      public int hashCode() {
         return 2433880;
      }

      public String toString() {
         return "None";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(None$.class);
      }
   }

   public static class Full$ extends OptOverhang implements Product, Serializable {
      public static final Full$ MODULE$ = new Full$();

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
         return "Full";
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
         return x$1 instanceof Full$;
      }

      public int hashCode() {
         return 2201263;
      }

      public String toString() {
         return "Full";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Full$.class);
      }
   }

   public static class PreserveLength$ extends OptOverhang implements Product, Serializable {
      public static final PreserveLength$ MODULE$ = new PreserveLength$();

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
         return "PreserveLength";
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
         return x$1 instanceof PreserveLength$;
      }

      public int hashCode() {
         return 1728100466;
      }

      public String toString() {
         return "PreserveLength";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(PreserveLength$.class);
      }
   }
}
