package org.apache.spark.paths;

import java.io.Serializable;
import java.net.URI;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e\u0001\u0002\u0010 \u0001\"B\u0001B\u0010\u0001\u0003\u0006\u0004%Ia\u0010\u0005\t\u0011\u0002\u0011\t\u0012)A\u0005\u0001\")\u0011\n\u0001C\u0005\u0015\")a\n\u0001C\u0001\u007f!)q\n\u0001C\u0001!\")\u0011\f\u0001C\u00015\")1\r\u0001C!I\"9Q\rAA\u0001\n\u00031\u0007b\u00025\u0001#\u0003%\t!\u001b\u0005\bi\u0002Y\t\u0011\"\u0001@\u0011\u001d)\b!!A\u0005BYDq\u0001 \u0001\u0002\u0002\u0013\u0005Q\u0010C\u0005\u0002\u0004\u0001\t\t\u0011\"\u0001\u0002\u0006!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003C\u0001\u0011\u0011!C\u0001\u0003GA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\t\u0013\u0005M\u0002!!A\u0005B\u0005U\u0002\"CA\u001c\u0001\u0005\u0005I\u0011IA\u001d\u000f\u001d\tid\bE\u0001\u0003\u007f1aAH\u0010\t\u0002\u0005\u0005\u0003BB%\u0015\t\u0003\ti\u0005C\u0004\u0002PQ!\t!!\u0015\t\u000f\u0005]C\u0003\"\u0001\u0002Z!9\u0011q\f\u000b\u0005\u0002\u0005\u0005\u0004bBA6)\u0011\u0005\u0011Q\u000e\u0005\b\u0003c\"B\u0011AA:\u0011%\tI\bFA\u0001\n\u0003\u000bY\bC\u0005\u0002\u0000Q\t\t\u0011\"!\u0002\u0002\"I\u0011Q\u0012\u000b\u0002\u0002\u0013%\u0011q\u0012\u0002\n'B\f'o\u001b)bi\"T!\u0001I\u0011\u0002\u000bA\fG\u000f[:\u000b\u0005\t\u001a\u0013!B:qCJ\\'B\u0001\u0013&\u0003\u0019\t\u0007/Y2iK*\ta%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001S=\u0012\u0004C\u0001\u0016.\u001b\u0005Y#\"\u0001\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u00059Z#AB!osJ+g\r\u0005\u0002+a%\u0011\u0011g\u000b\u0002\b!J|G-^2u!\t\u00194H\u0004\u00025s9\u0011Q\u0007O\u0007\u0002m)\u0011qgJ\u0001\u0007yI|w\u000e\u001e \n\u00031J!AO\u0016\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003u-\n!\"\u001e8eKJd\u00170\u001b8h+\u0005\u0001\u0005CA!F\u001d\t\u00115\t\u0005\u00026W%\u0011AiK\u0001\u0007!J,G-\u001a4\n\u0005\u0019;%AB*ue&twM\u0003\u0002EW\u0005YQO\u001c3fe2L\u0018N\\4!\u0003\u0019a\u0014N\\5u}Q\u00111*\u0014\t\u0003\u0019\u0002i\u0011a\b\u0005\u0006}\r\u0001\r\u0001Q\u0001\u000bkJdWI\\2pI\u0016$\u0017!\u0002;p+JLW#A)\u0011\u0005I;V\"A*\u000b\u0005Q+\u0016a\u00018fi*\ta+\u0001\u0003kCZ\f\u0017B\u0001-T\u0005\r)&+S\u0001\u0007i>\u0004\u0016\r\u001e5\u0016\u0003m\u0003\"\u0001X1\u000e\u0003uS!AX0\u0002\u0005\u0019\u001c(B\u00011$\u0003\u0019A\u0017\rZ8pa&\u0011!-\u0018\u0002\u0005!\u0006$\b.\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0015\u0001B2paf$\"aS4\t\u000fyB\u0001\u0013!a\u0001\u0001\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00016+\u0005\u0001[7&\u00017\u0011\u00055\u0014X\"\u00018\u000b\u0005=\u0004\u0018!C;oG\",7m[3e\u0015\t\t8&\u0001\u0006b]:|G/\u0019;j_:L!a\u001d8\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\nv]\u0012,'\u000f\\=j]\u001e$\u0013mY2fgN$\u0003'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002oB\u0011\u0001p_\u0007\u0002s*\u0011!0V\u0001\u0005Y\u0006tw-\u0003\u0002Gs\u0006a\u0001O]8ek\u000e$\u0018I]5usV\ta\u0010\u0005\u0002+\u007f&\u0019\u0011\u0011A\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u001d\u0011Q\u0002\t\u0004U\u0005%\u0011bAA\u0006W\t\u0019\u0011I\\=\t\u0011\u0005=Q\"!AA\u0002y\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u000b!\u0019\t9\"!\b\u0002\b5\u0011\u0011\u0011\u0004\u0006\u0004\u00037Y\u0013AC2pY2,7\r^5p]&!\u0011qDA\r\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0015\u00121\u0006\t\u0004U\u0005\u001d\u0012bAA\u0015W\t9!i\\8mK\u0006t\u0007\"CA\b\u001f\u0005\u0005\t\u0019AA\u0004\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007]\f\t\u0004\u0003\u0005\u0002\u0010A\t\t\u00111\u0001\u007f\u0003!A\u0017m\u001d5D_\u0012,G#\u0001@\u0002\r\u0015\fX/\u00197t)\u0011\t)#a\u000f\t\u0013\u0005=!#!AA\u0002\u0005\u001d\u0011!C*qCJ\\\u0007+\u0019;i!\taEc\u0005\u0003\u0015S\u0005\r\u0003\u0003BA#\u0003\u0017j!!a\u0012\u000b\u0007\u0005%S+\u0001\u0002j_&\u0019A(a\u0012\u0015\u0005\u0005}\u0012A\u00044s_6\u0004\u0016\r\u001e5TiJLgn\u001a\u000b\u0004\u0017\u0006M\u0003BBA+-\u0001\u0007\u0001)A\u0002tiJ\f\u0001B\u001a:p[B\u000bG\u000f\u001b\u000b\u0004\u0017\u0006m\u0003BBA//\u0001\u00071,\u0001\u0003qCRD\u0017A\u00044s_64\u0015\u000e\\3Ti\u0006$Xo\u001d\u000b\u0004\u0017\u0006\r\u0004B\u00020\u0019\u0001\u0004\t)\u0007E\u0002]\u0003OJ1!!\u001b^\u0005)1\u0015\u000e\\3Ti\u0006$Xo]\u0001\u000eMJ|W.\u0016:m'R\u0014\u0018N\\4\u0015\u0007-\u000by\u0007\u0003\u0004\u0002Ve\u0001\r\u0001Q\u0001\bMJ|W.\u0016:j)\rY\u0015Q\u000f\u0005\u0007\u0003oR\u0002\u0019A)\u0002\u0007U\u0014\u0018.A\u0003baBd\u0017\u0010F\u0002L\u0003{BQAP\u000eA\u0002\u0001\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0004\u0006%\u0005\u0003\u0002\u0016\u0002\u0006\u0002K1!a\",\u0005\u0019y\u0005\u000f^5p]\"A\u00111\u0012\u000f\u0002\u0002\u0003\u00071*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!%\u0011\u0007a\f\u0019*C\u0002\u0002\u0016f\u0014aa\u00142kK\u000e$\b"
)
public class SparkPath implements Product, Serializable {
   private final String underlying;

   public static Option unapply(final SparkPath x$0) {
      return SparkPath$.MODULE$.unapply(x$0);
   }

   public static SparkPath apply(final String underlying) {
      return SparkPath$.MODULE$.apply(underlying);
   }

   public static SparkPath fromUri(final URI uri) {
      return SparkPath$.MODULE$.fromUri(uri);
   }

   public static SparkPath fromUrlString(final String str) {
      return SparkPath$.MODULE$.fromUrlString(str);
   }

   public static SparkPath fromFileStatus(final FileStatus fs) {
      return SparkPath$.MODULE$.fromFileStatus(fs);
   }

   public static SparkPath fromPath(final Path path) {
      return SparkPath$.MODULE$.fromPath(path);
   }

   public static SparkPath fromPathString(final String str) {
      return SparkPath$.MODULE$.fromPathString(str);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String underlying$access$0() {
      return this.underlying;
   }

   private String underlying() {
      return this.underlying;
   }

   public String urlEncoded() {
      return this.underlying();
   }

   public URI toUri() {
      return new URI(this.underlying());
   }

   public Path toPath() {
      return new Path(this.toUri());
   }

   public String toString() {
      return this.underlying();
   }

   public SparkPath copy(final String underlying) {
      return new SparkPath(underlying);
   }

   public String copy$default$1() {
      return this.underlying();
   }

   public String productPrefix() {
      return "SparkPath";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.underlying$access$0();
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
      return x$1 instanceof SparkPath;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "underlying";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof SparkPath) {
               label40: {
                  SparkPath var4 = (SparkPath)x$1;
                  String var10000 = this.underlying$access$0();
                  String var5 = var4.underlying$access$0();
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

   public SparkPath(final String underlying) {
      this.underlying = underlying;
      Product.$init$(this);
   }
}
