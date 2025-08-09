package org.apache.spark.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015e\u0001B\r\u001b\u0001\u000eBQA\u0010\u0001\u0005\u0002}B\u0011\u0002\u0014\u0001A\u0002\u0003\u0007I\u0011A'\t\u0013Q\u0003\u0001\u0019!a\u0001\n\u0003)\u0006\"C.\u0001\u0001\u0004\u0005\t\u0015)\u0003O\u0011%I\u0007\u00011AA\u0002\u0013\u0005!\u000eC\u0005l\u0001\u0001\u0007\t\u0019!C\u0001Y\"Ia\u000e\u0001a\u0001\u0002\u0003\u0006K!\u0011\u0005\ba\u0002\t\t\u0011\"\u0001r\u0011\u001d1\b!!A\u0005B]D\u0011\"!\u0001\u0001\u0003\u0003%\t!a\u0001\t\u0013\u0005-\u0001!!A\u0005\u0002\u00055\u0001\"CA\t\u0001\u0005\u0005I\u0011IA\n\u0011%\t\t\u0003AA\u0001\n\u0003\t\u0019\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u00111\u0007\u0001\u0002\u0002\u0013\u0005\u0013Q\u0007\u0005\n\u0003o\u0001\u0011\u0011!C!\u0003sA\u0011\"a\u000f\u0001\u0003\u0003%\t%!\u0010\b\u0013\u00055#$!A\t\u0002\u0005=c\u0001C\r\u001b\u0003\u0003E\t!!\u0015\t\ry\u001aB\u0011AA/\u0011%\t9dEA\u0001\n\u000b\nI\u0004C\u0005\u0002`M\t\t\u0011\"!\u0002b!I\u00111N\n\u0002\u0002\u0013\u0005\u0015Q\u000e\u0005\n\u0003w\u001a\u0012\u0011!C\u0005\u0003{\u0012q\u0002T8bI&s7\u000f^1oG\u0016,e\u000e\u001a\u0006\u00037q\t!!\u001c7\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001c\u0001!\u0006\u0002%\u0007N)\u0001!J\u00160eA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1\u0011I\\=SK\u001a\u0004\"\u0001L\u0017\u000e\u0003iI!A\f\u000e\u0003\u000f5cUI^3oiB\u0011a\u0005M\u0005\u0003c\u001d\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00024w9\u0011A'\u000f\b\u0003kaj\u0011A\u000e\u0006\u0003o\t\na\u0001\u0010:p_Rt\u0014\"\u0001\u0015\n\u0005i:\u0013a\u00029bG.\fw-Z\u0005\u0003yu\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!AO\u0014\u0002\rqJg.\u001b;?)\u0005\u0001\u0005c\u0001\u0017\u0001\u0003B\u0011!i\u0011\u0007\u0001\t\u0015!\u0005A1\u0001F\u0005\u0005!\u0016C\u0001$J!\t1s)\u0003\u0002IO\t9aj\u001c;iS:<\u0007C\u0001\u0014K\u0013\tYuEA\u0002B]f\faA]3bI\u0016\u0014X#\u0001(\u0011\u0007=\u0013\u0016)D\u0001Q\u0015\t\t&$\u0001\u0003vi&d\u0017BA*Q\u0005!iEJU3bI\u0016\u0014\u0018A\u0003:fC\u0012,'o\u0018\u0013fcR\u0011a+\u0017\t\u0003M]K!\u0001W\u0014\u0003\tUs\u0017\u000e\u001e\u0005\b5\u000e\t\t\u00111\u0001O\u0003\rAH%M\u0001\be\u0016\fG-\u001a:!Q\t!Q\f\u0005\u0002_O6\tqL\u0003\u0002aC\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0005\t\u001c\u0017a\u00026bG.\u001cxN\u001c\u0006\u0003I\u0016\f\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003\u0019\f1aY8n\u0013\tAwL\u0001\u0006Kg>t\u0017j\u001a8pe\u0016\f\u0001\"\u001b8ti\u0006t7-Z\u000b\u0002\u0003\u0006a\u0011N\\:uC:\u001cWm\u0018\u0013fcR\u0011a+\u001c\u0005\b5\u001a\t\t\u00111\u0001B\u0003%Ign\u001d;b]\u000e,\u0007\u0005\u000b\u0002\b;\u0006!1m\u001c9z+\t\u0011X\u000fF\u0001t!\ra\u0003\u0001\u001e\t\u0003\u0005V$Q\u0001\u0012\u0005C\u0002\u0015\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001=\u0011\u0005etX\"\u0001>\u000b\u0005md\u0018\u0001\u00027b]\u001eT\u0011!`\u0001\u0005U\u00064\u0018-\u0003\u0002\u0000u\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u0002\u0011\u0007\u0019\n9!C\u0002\u0002\n\u001d\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2!SA\b\u0011!Q6\"!AA\u0002\u0005\u0015\u0011a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005U\u0001#BA\f\u0003;IUBAA\r\u0015\r\tYbJ\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0010\u00033\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QEA\u0016!\r1\u0013qE\u0005\u0004\u0003S9#a\u0002\"p_2,\u0017M\u001c\u0005\b56\t\t\u00111\u0001J\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007a\f\t\u0004\u0003\u0005[\u001d\u0005\u0005\t\u0019AA\u0003\u0003!A\u0017m\u001d5D_\u0012,GCAA\u0003\u0003!!xn\u0015;sS:<G#\u0001=\u0002\r\u0015\fX/\u00197t)\u0011\t)#a\u0010\t\u000fi\u000b\u0012\u0011!a\u0001\u0013\"\u001a\u0001!a\u0011\u0011\t\u0005\u0015\u0013\u0011J\u0007\u0003\u0003\u000fR!\u0001\u0019\u000f\n\t\u0005-\u0013q\t\u0002\t\u000bZ|GN^5oO\u0006yAj\\1e\u0013:\u001cH/\u00198dK\u0016sG\r\u0005\u0002-'M!1#JA*!\u0011\t)&a\u0017\u000e\u0005\u0005]#bAA-y\u0006\u0011\u0011n\\\u0005\u0004y\u0005]CCAA(\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\t\u0019'!\u001b\u0015\u0005\u0005\u0015\u0004\u0003\u0002\u0017\u0001\u0003O\u00022AQA5\t\u0015!eC1\u0001F\u0003\u001d)h.\u00199qYf,B!a\u001c\u0002zQ!\u0011QEA9\u0011%\t\u0019hFA\u0001\u0002\u0004\t)(A\u0002yIA\u0002B\u0001\f\u0001\u0002xA\u0019!)!\u001f\u0005\u000b\u0011;\"\u0019A#\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0004cA=\u0002\u0002&\u0019\u00111\u0011>\u0003\r=\u0013'.Z2u\u0001"
)
public class LoadInstanceEnd implements MLEvent, Product, Serializable {
   @JsonIgnore
   private MLReader reader;
   @JsonIgnore
   private Object instance;

   public static boolean unapply(final LoadInstanceEnd x$0) {
      return LoadInstanceEnd$.MODULE$.unapply(x$0);
   }

   public static LoadInstanceEnd apply() {
      return LoadInstanceEnd$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return MLEvent.logEvent$(this);
   }

   public MLReader reader() {
      return this.reader;
   }

   public void reader_$eq(final MLReader x$1) {
      this.reader = x$1;
   }

   public Object instance() {
      return this.instance;
   }

   public void instance_$eq(final Object x$1) {
      this.instance = x$1;
   }

   public LoadInstanceEnd copy() {
      return new LoadInstanceEnd();
   }

   public String productPrefix() {
      return "LoadInstanceEnd";
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
      return x$1 instanceof LoadInstanceEnd;
   }

   public String productElementName(final int x$1) {
      return (String)Statics.ioobe(x$1);
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      return x$1 instanceof LoadInstanceEnd && ((LoadInstanceEnd)x$1).canEqual(this);
   }

   public LoadInstanceEnd() {
      SparkListenerEvent.$init$(this);
      MLEvent.$init$(this);
      Product.$init$(this);
   }
}
