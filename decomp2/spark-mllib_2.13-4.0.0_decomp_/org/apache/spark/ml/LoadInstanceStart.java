package org.apache.spark.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ef\u0001B\r\u001b\u0001\u000eB\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u0011\u0002\u0011\t\u0012)A\u0005\u0001\")\u0011\n\u0001C\u0001\u0015\"I\u0001\f\u0001a\u0001\u0002\u0004%\t!\u0017\u0005\nA\u0002\u0001\r\u00111A\u0005\u0002\u0005D\u0011b\u001a\u0001A\u0002\u0003\u0005\u000b\u0015\u0002.\t\u000fU\u0004\u0011\u0011!C\u0001m\"9A\u0010AI\u0001\n\u0003i\b\"CA\n\u0001\u0005\u0005I\u0011IA\u000b\u0011%\t)\u0003AA\u0001\n\u0003\t9\u0003C\u0005\u00020\u0001\t\t\u0011\"\u0001\u00022!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0013q\u0007\u0005\n\u0003\u000b\u0002\u0011\u0011!C\u0001\u0003\u000fB\u0011\"!\u0015\u0001\u0003\u0003%\t%a\u0015\t\u0013\u0005]\u0003!!A\u0005B\u0005e\u0003\"CA.\u0001\u0005\u0005I\u0011IA/\u0011%\ty\u0006AA\u0001\n\u0003\n\tgB\u0005\u0002ri\t\t\u0011#\u0001\u0002t\u0019A\u0011DGA\u0001\u0012\u0003\t)\b\u0003\u0004J'\u0011\u0005\u0011\u0011\u0011\u0005\n\u00037\u001a\u0012\u0011!C#\u0003;B\u0011\"a!\u0014\u0003\u0003%\t)!\"\t\u0013\u0005E5#!A\u0005\u0002\u0006M\u0005\"CAT'\u0005\u0005I\u0011BAU\u0005Eau.\u00193J]N$\u0018M\\2f'R\f'\u000f\u001e\u0006\u00037q\t!!\u001c7\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001c\u0001!\u0006\u0002%\u001dN)\u0001!J\u00160eA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1\u0011I\\=SK\u001a\u0004\"\u0001L\u0017\u000e\u0003iI!A\f\u000e\u0003\u000f5cUI^3oiB\u0011a\u0005M\u0005\u0003c\u001d\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00024w9\u0011A'\u000f\b\u0003kaj\u0011A\u000e\u0006\u0003o\t\na\u0001\u0010:p_Rt\u0014\"\u0001\u0015\n\u0005i:\u0013a\u00029bG.\fw-Z\u0005\u0003yu\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!AO\u0014\u0002\tA\fG\u000f[\u000b\u0002\u0001B\u0011\u0011)\u0012\b\u0003\u0005\u000e\u0003\"!N\u0014\n\u0005\u0011;\u0013A\u0002)sK\u0012,g-\u0003\u0002G\u000f\n11\u000b\u001e:j]\u001eT!\u0001R\u0014\u0002\u000bA\fG\u000f\u001b\u0011\u0002\rqJg.\u001b;?)\tYu\u000bE\u0002-\u00011\u0003\"!\u0014(\r\u0001\u0011)q\n\u0001b\u0001!\n\tA+\u0005\u0002R)B\u0011aEU\u0005\u0003'\u001e\u0012qAT8uQ&tw\r\u0005\u0002'+&\u0011ak\n\u0002\u0004\u0003:L\b\"\u0002 \u0004\u0001\u0004\u0001\u0015A\u0002:fC\u0012,'/F\u0001[!\rYf\fT\u0007\u00029*\u0011QLG\u0001\u0005kRLG.\u0003\u0002`9\nAQ\n\u0014*fC\u0012,'/\u0001\u0006sK\u0006$WM]0%KF$\"AY3\u0011\u0005\u0019\u001a\u0017B\u00013(\u0005\u0011)f.\u001b;\t\u000f\u0019,\u0011\u0011!a\u00015\u0006\u0019\u0001\u0010J\u0019\u0002\u000fI,\u0017\rZ3sA!\u0012a!\u001b\t\u0003UNl\u0011a\u001b\u0006\u0003Y6\f!\"\u00198o_R\fG/[8o\u0015\tqw.A\u0004kC\u000e\\7o\u001c8\u000b\u0005A\f\u0018!\u00034bgR,'\u000f_7m\u0015\u0005\u0011\u0018aA2p[&\u0011Ao\u001b\u0002\u000b\u0015N|g.S4o_J,\u0017\u0001B2paf,\"a\u001e>\u0015\u0005a\\\bc\u0001\u0017\u0001sB\u0011QJ\u001f\u0003\u0006\u001f\u001e\u0011\r\u0001\u0015\u0005\b}\u001d\u0001\n\u00111\u0001A\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*2A`A\t+\u0005y(f\u0001!\u0002\u0002-\u0012\u00111\u0001\t\u0005\u0003\u000b\ti!\u0004\u0002\u0002\b)!\u0011\u0011BA\u0006\u0003%)hn\u00195fG.,GM\u0003\u0002mO%!\u0011qBA\u0004\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006\u001f\"\u0011\r\u0001U\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005]\u0001\u0003BA\r\u0003Gi!!a\u0007\u000b\t\u0005u\u0011qD\u0001\u0005Y\u0006twM\u0003\u0002\u0002\"\u0005!!.\u0019<b\u0013\r1\u00151D\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003S\u00012AJA\u0016\u0013\r\tic\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004)\u0006M\u0002\u0002\u00034\f\u0003\u0003\u0005\r!!\u000b\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u000f\u0011\u000b\u0005m\u0012\u0011\t+\u000e\u0005\u0005u\"bAA O\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\r\u0013Q\b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002J\u0005=\u0003c\u0001\u0014\u0002L%\u0019\u0011QJ\u0014\u0003\u000f\t{w\u000e\\3b]\"9a-DA\u0001\u0002\u0004!\u0016A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u0006\u0002V!AaMDA\u0001\u0002\u0004\tI#\u0001\u0005iCND7i\u001c3f)\t\tI#\u0001\u0005u_N#(/\u001b8h)\t\t9\"\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0013\n\u0019\u0007C\u0004g#\u0005\u0005\t\u0019\u0001+)\u0007\u0001\t9\u0007\u0005\u0003\u0002j\u00055TBAA6\u0015\taG$\u0003\u0003\u0002p\u0005-$\u0001C#w_24\u0018N\\4\u0002#1{\u0017\rZ%ogR\fgnY3Ti\u0006\u0014H\u000f\u0005\u0002-'M!1#JA<!\u0011\tI(a \u000e\u0005\u0005m$\u0002BA?\u0003?\t!![8\n\u0007q\nY\b\u0006\u0002\u0002t\u0005)\u0011\r\u001d9msV!\u0011qQAG)\u0011\tI)a$\u0011\t1\u0002\u00111\u0012\t\u0004\u001b\u00065E!B(\u0017\u0005\u0004\u0001\u0006\"\u0002 \u0017\u0001\u0004\u0001\u0015aB;oCB\u0004H._\u000b\u0005\u0003+\u000b)\u000b\u0006\u0003\u0002\u0018\u0006u\u0005\u0003\u0002\u0014\u0002\u001a\u0002K1!a'(\u0005\u0019y\u0005\u000f^5p]\"I\u0011qT\f\u0002\u0002\u0003\u0007\u0011\u0011U\u0001\u0004q\u0012\u0002\u0004\u0003\u0002\u0017\u0001\u0003G\u00032!TAS\t\u0015yuC1\u0001Q\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\u000b\u0005\u0003\u0002\u001a\u00055\u0016\u0002BAX\u00037\u0011aa\u00142kK\u000e$\b"
)
public class LoadInstanceStart implements MLEvent, Product, Serializable {
   private final String path;
   @JsonIgnore
   private MLReader reader;

   public static Option unapply(final LoadInstanceStart x$0) {
      return LoadInstanceStart$.MODULE$.unapply(x$0);
   }

   public static LoadInstanceStart apply(final String path) {
      return LoadInstanceStart$.MODULE$.apply(path);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return MLEvent.logEvent$(this);
   }

   public String path() {
      return this.path;
   }

   public MLReader reader() {
      return this.reader;
   }

   public void reader_$eq(final MLReader x$1) {
      this.reader = x$1;
   }

   public LoadInstanceStart copy(final String path) {
      return new LoadInstanceStart(path);
   }

   public String copy$default$1() {
      return this.path();
   }

   public String productPrefix() {
      return "LoadInstanceStart";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.path();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof LoadInstanceStart;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "path";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof LoadInstanceStart) {
               label40: {
                  LoadInstanceStart var4 = (LoadInstanceStart)x$1;
                  String var10000 = this.path();
                  String var5 = var4.path();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public LoadInstanceStart(final String path) {
      this.path = path;
      SparkListenerEvent.$init$(this);
      MLEvent.$init$(this);
      Product.$init$(this);
   }
}
