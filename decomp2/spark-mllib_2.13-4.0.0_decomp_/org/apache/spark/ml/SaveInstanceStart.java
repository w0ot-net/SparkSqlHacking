package org.apache.spark.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=e\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\u007f!)\u0001\n\u0001C\u0001\u0013\"IA\n\u0001a\u0001\u0002\u0004%\t!\u0014\u0005\n)\u0002\u0001\r\u00111A\u0005\u0002UC\u0011b\u0017\u0001A\u0002\u0003\u0005\u000b\u0015\u0002(\t\u000f%\u0004\u0011\u0011!C\u0001U\"9A\u000eAI\u0001\n\u0003i\u0007bB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\n\u0003\u0003\u0001\u0011\u0011!C\u0001\u0003\u0007A\u0011\"a\u0003\u0001\u0003\u0003%\t!!\u0004\t\u0013\u0005]\u0001!!A\u0005B\u0005e\u0001\"CA\u0014\u0001\u0005\u0005I\u0011AA\u0015\u0011%\t\u0019\u0004AA\u0001\n\u0003\n)\u0004C\u0005\u0002:\u0001\t\t\u0011\"\u0011\u0002<!I\u0011Q\b\u0001\u0002\u0002\u0013\u0005\u0013q\b\u0005\n\u0003\u0003\u0002\u0011\u0011!C!\u0003\u0007:\u0011\"a\u0015\u001b\u0003\u0003E\t!!\u0016\u0007\u0011eQ\u0012\u0011!E\u0001\u0003/Ba\u0001S\n\u0005\u0002\u0005=\u0004\"CA\u001f'\u0005\u0005IQIA \u0011%\t\thEA\u0001\n\u0003\u000b\u0019\bC\u0005\u0002xM\t\t\u0011\"!\u0002z!I\u0011QQ\n\u0002\u0002\u0013%\u0011q\u0011\u0002\u0012'\u00064X-\u00138ti\u0006t7-Z*uCJ$(BA\u000e\u001d\u0003\tiGN\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019R\u0001\u0001\u0013+]E\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0007CA\u0016-\u001b\u0005Q\u0012BA\u0017\u001b\u0005\u001diE*\u0012<f]R\u0004\"!J\u0018\n\u0005A2#a\u0002)s_\u0012,8\r\u001e\t\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005Y\u0012\u0013A\u0002\u001fs_>$h(C\u0001(\u0013\tId%A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d'\u0003\u0011\u0001\u0018\r\u001e5\u0016\u0003}\u0002\"\u0001\u0011#\u000f\u0005\u0005\u0013\u0005C\u0001\u001b'\u0013\t\u0019e%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000b\u001a\u0013aa\u0015;sS:<'BA\"'\u0003\u0015\u0001\u0018\r\u001e5!\u0003\u0019a\u0014N\\5u}Q\u0011!j\u0013\t\u0003W\u0001AQ!P\u0002A\u0002}\naa\u001e:ji\u0016\u0014X#\u0001(\u0011\u0005=\u0013V\"\u0001)\u000b\u0005ES\u0012\u0001B;uS2L!a\u0015)\u0003\u00115cuK]5uKJ\f!b\u001e:ji\u0016\u0014x\fJ3r)\t1\u0016\f\u0005\u0002&/&\u0011\u0001L\n\u0002\u0005+:LG\u000fC\u0004[\u000b\u0005\u0005\t\u0019\u0001(\u0002\u0007a$\u0013'A\u0004xe&$XM\u001d\u0011)\u0005\u0019i\u0006C\u00010h\u001b\u0005y&B\u00011b\u0003)\tgN\\8uCRLwN\u001c\u0006\u0003E\u000e\fqA[1dWN|gN\u0003\u0002eK\u0006Ia-Y:uKJDX\u000e\u001c\u0006\u0002M\u0006\u00191m\\7\n\u0005!|&A\u0003&t_:LuM\\8sK\u0006!1m\u001c9z)\tQ5\u000eC\u0004>\u000fA\u0005\t\u0019A \u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\taN\u000b\u0002@_.\n\u0001\u000f\u0005\u0002rk6\t!O\u0003\u0002ti\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003A\u001aJ!A\u001e:\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002sB\u0011!p`\u0007\u0002w*\u0011A0`\u0001\u0005Y\u0006twMC\u0001\u007f\u0003\u0011Q\u0017M^1\n\u0005\u0015[\u0018\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0003!\r)\u0013qA\u0005\u0004\u0003\u00131#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\b\u0003+\u00012!JA\t\u0013\r\t\u0019B\n\u0002\u0004\u0003:L\b\u0002\u0003.\f\u0003\u0003\u0005\r!!\u0002\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0007\u0011\r\u0005u\u00111EA\b\u001b\t\tyBC\u0002\u0002\"\u0019\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)#a\b\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003W\t\t\u0004E\u0002&\u0003[I1!a\f'\u0005\u001d\u0011un\u001c7fC:D\u0001BW\u0007\u0002\u0002\u0003\u0007\u0011qB\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002z\u0003oA\u0001B\u0017\b\u0002\u0002\u0003\u0007\u0011QA\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QA\u0001\ti>\u001cFO]5oOR\t\u00110\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003W\t)\u0005\u0003\u0005[#\u0005\u0005\t\u0019AA\bQ\r\u0001\u0011\u0011\n\t\u0005\u0003\u0017\ny%\u0004\u0002\u0002N)\u0011\u0001\rH\u0005\u0005\u0003#\niE\u0001\u0005Fm>dg/\u001b8h\u0003E\u0019\u0016M^3J]N$\u0018M\\2f'R\f'\u000f\u001e\t\u0003WM\u0019RaEA-\u0003K\u0002b!a\u0017\u0002b}RUBAA/\u0015\r\tyFJ\u0001\beVtG/[7f\u0013\u0011\t\u0019'!\u0018\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002h\u00055TBAA5\u0015\r\tY'`\u0001\u0003S>L1aOA5)\t\t)&A\u0003baBd\u0017\u0010F\u0002K\u0003kBQ!\u0010\fA\u0002}\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002|\u0005\u0005\u0005\u0003B\u0013\u0002~}J1!a '\u0005\u0019y\u0005\u000f^5p]\"A\u00111Q\f\u0002\u0002\u0003\u0007!*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!#\u0011\u0007i\fY)C\u0002\u0002\u000en\u0014aa\u00142kK\u000e$\b"
)
public class SaveInstanceStart implements MLEvent, Product, Serializable {
   private final String path;
   @JsonIgnore
   private MLWriter writer;

   public static Option unapply(final SaveInstanceStart x$0) {
      return SaveInstanceStart$.MODULE$.unapply(x$0);
   }

   public static SaveInstanceStart apply(final String path) {
      return SaveInstanceStart$.MODULE$.apply(path);
   }

   public static Function1 andThen(final Function1 g) {
      return SaveInstanceStart$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SaveInstanceStart$.MODULE$.compose(g);
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

   public MLWriter writer() {
      return this.writer;
   }

   public void writer_$eq(final MLWriter x$1) {
      this.writer = x$1;
   }

   public SaveInstanceStart copy(final String path) {
      return new SaveInstanceStart(path);
   }

   public String copy$default$1() {
      return this.path();
   }

   public String productPrefix() {
      return "SaveInstanceStart";
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
      return x$1 instanceof SaveInstanceStart;
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
            if (x$1 instanceof SaveInstanceStart) {
               label40: {
                  SaveInstanceStart var4 = (SaveInstanceStart)x$1;
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

   public SaveInstanceStart(final String path) {
      this.path = path;
      SparkListenerEvent.$init$(this);
      MLEvent.$init$(this);
      Product.$init$(this);
   }
}
