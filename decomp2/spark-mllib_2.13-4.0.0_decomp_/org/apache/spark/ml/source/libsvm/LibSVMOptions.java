package org.apache.spark.ml.source.libsvm;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap;
import scala.Option;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a!B\u000b\u0017\u0001Y\u0011\u0003\u0002\u0003\u001c\u0001\u0005\u000b\u0007I\u0011B\u001c\t\u0011)\u0003!\u0011!Q\u0001\naBQa\u0014\u0001\u0005\u0002ACQa\u0014\u0001\u0005\u0002QCq!\u0017\u0001C\u0002\u0013\u0005!\f\u0003\u0004b\u0001\u0001\u0006Ia\u0017\u0005\bE\u0002\u0011\r\u0011\"\u0001d\u0011\u00199\u0007\u0001)A\u0005I\u001e1\u0001N\u0006E\u0001-%4a!\u0006\f\t\u0002YQ\u0007\"B(\u000b\t\u0003\u0011\bbB:\u000b\u0005\u0004%\t\u0001\u001e\u0005\u0007u*\u0001\u000b\u0011B;\t\u000fmT!\u0019!C\u0001i\"1AP\u0003Q\u0001\nUDq! \u0006C\u0002\u0013\u0005A\u000f\u0003\u0004\u007f\u0015\u0001\u0006I!\u001e\u0005\b\u007f*\u0011\r\u0011\"\u0001u\u0011\u001d\t\tA\u0003Q\u0001\nUD\u0011\"a\u0001\u000b\u0003\u0003%I!!\u0002\u0003\u001b1K'm\u0015,N\u001fB$\u0018n\u001c8t\u0015\t9\u0002$\u0001\u0004mS\n\u001ch/\u001c\u0006\u00033i\taa]8ve\u000e,'BA\u000e\u001d\u0003\tiGN\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h'\r\u00011%\u000b\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005)\u001adBA\u00162\u001d\ta\u0003'D\u0001.\u0015\tqs&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u00051\u0013B\u0001\u001a&\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001N\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005I*\u0013A\u00039be\u0006lW\r^3sgV\t\u0001\bE\u0002:\u0001\nk\u0011A\u000f\u0006\u0003wq\nA!\u001e;jY*\u0011QHP\u0001\tG\u0006$\u0018\r\\=ti*\u0011q\bH\u0001\u0004gFd\u0017BA!;\u0005I\u0019\u0015m]3J]N,gn]5uSZ,W*\u00199\u0011\u0005\r;eB\u0001#F!\taS%\u0003\u0002GK\u00051\u0001K]3eK\u001aL!\u0001S%\u0003\rM#(/\u001b8h\u0015\t1U%A\u0006qCJ\fW.\u001a;feN\u0004\u0003F\u0001\u0002M!\t!S*\u0003\u0002OK\tIAO]1og&,g\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005E\u001b\u0006C\u0001*\u0001\u001b\u00051\u0002\"\u0002\u001c\u0004\u0001\u0004ADCA)V\u0011\u00151D\u00011\u0001W!\u0011\u0019uK\u0011\"\n\u0005aK%aA'ba\u0006Ya.^7GK\u0006$XO]3t+\u0005Y\u0006c\u0001\u0013]=&\u0011Q,\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0011z\u0016B\u00011&\u0005\rIe\u000e^\u0001\r]Vlg)Z1ukJ,7\u000fI\u0001\tSN\u001c\u0006/\u0019:tKV\tA\r\u0005\u0002%K&\u0011a-\n\u0002\b\u0005>|G.Z1o\u0003%I7o\u00159beN,\u0007%A\u0007MS\n\u001cf+T(qi&|gn\u001d\t\u0003%*\u00192AC\u0012l!\ta\u0017/D\u0001n\u0015\tqw.\u0001\u0002j_*\t\u0001/\u0001\u0003kCZ\f\u0017B\u0001\u001bn)\u0005I\u0017\u0001\u0004(V\u001b~3U)\u0011+V%\u0016\u001bV#A;\u0011\u0005YLX\"A<\u000b\u0005a|\u0017\u0001\u00027b]\u001eL!\u0001S<\u0002\u001b9+Vj\u0018$F\u0003R+&+R*!\u0003-1Vi\u0011+P%~#\u0016\fU#\u0002\u0019Y+5\tV(S?RK\u0006+\u0012\u0011\u0002#\u0011+ejU#`-\u0016\u001bEk\u0014*`)f\u0003V)\u0001\nE\u000b:\u001bVi\u0018,F\u0007R{%k\u0018+Z!\u0016\u0003\u0013AE*Q\u0003J\u001bVi\u0018,F\u0007R{%k\u0018+Z!\u0016\u000b1c\u0015)B%N+uLV#D)>\u0013v\fV-Q\u000b\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0002\u0011\u0007Y\fI!C\u0002\u0002\f]\u0014aa\u00142kK\u000e$\b"
)
public class LibSVMOptions implements Serializable {
   private final transient CaseInsensitiveMap parameters;
   private final Option numFeatures;
   private final boolean isSparse;

   public static String SPARSE_VECTOR_TYPE() {
      return LibSVMOptions$.MODULE$.SPARSE_VECTOR_TYPE();
   }

   public static String DENSE_VECTOR_TYPE() {
      return LibSVMOptions$.MODULE$.DENSE_VECTOR_TYPE();
   }

   public static String VECTOR_TYPE() {
      return LibSVMOptions$.MODULE$.VECTOR_TYPE();
   }

   public static String NUM_FEATURES() {
      return LibSVMOptions$.MODULE$.NUM_FEATURES();
   }

   private CaseInsensitiveMap parameters() {
      return this.parameters;
   }

   public Option numFeatures() {
      return this.numFeatures;
   }

   public boolean isSparse() {
      return this.isSparse;
   }

   // $FF: synthetic method
   public static final int $anonfun$numFeatures$1(final String x$1) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   public LibSVMOptions(final CaseInsensitiveMap parameters) {
      boolean var7;
      label29: {
         label32: {
            this.parameters = parameters;
            super();
            this.numFeatures = parameters.get(LibSVMOptions$.MODULE$.NUM_FEATURES()).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$numFeatures$1(x$1))).filter((JFunction1.mcZI.sp)(x$2) -> x$2 > 0);
            String var3 = (String)parameters.getOrElse(LibSVMOptions$.MODULE$.VECTOR_TYPE(), () -> LibSVMOptions$.MODULE$.SPARSE_VECTOR_TYPE());
            String var10001 = LibSVMOptions$.MODULE$.SPARSE_VECTOR_TYPE();
            if (var10001 == null) {
               if (var3 == null) {
                  break label32;
               }
            } else if (var10001.equals(var3)) {
               break label32;
            }

            var10001 = LibSVMOptions$.MODULE$.DENSE_VECTOR_TYPE();
            if (var10001 == null) {
               if (var3 != null) {
                  throw new IllegalArgumentException("Invalid value `" + var3 + "` for parameter `" + LibSVMOptions$.MODULE$.VECTOR_TYPE() + "`. Expected types are `sparse` and `dense`.");
               }
            } else if (!var10001.equals(var3)) {
               throw new IllegalArgumentException("Invalid value `" + var3 + "` for parameter `" + LibSVMOptions$.MODULE$.VECTOR_TYPE() + "`. Expected types are `sparse` and `dense`.");
            }

            var7 = false;
            break label29;
         }

         var7 = true;
      }

      this.isSparse = var7;
   }

   public LibSVMOptions(final Map parameters) {
      this(org.apache.spark.sql.catalyst.util.CaseInsensitiveMap..MODULE$.apply(parameters));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
