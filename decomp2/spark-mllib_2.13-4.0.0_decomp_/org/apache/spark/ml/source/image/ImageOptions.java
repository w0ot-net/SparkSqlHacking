package org.apache.spark.ml.source.image;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3Qa\u0002\u0005\u0001\u0011QA\u0001\u0002\u000b\u0001\u0003\u0006\u0004%I!\u000b\u0005\ty\u0001\u0011\t\u0011)A\u0005U!)\u0011\t\u0001C\u0001\u0005\")\u0011\t\u0001C\u0001\r\"91\n\u0001b\u0001\n\u0003a\u0005B\u0002)\u0001A\u0003%QJ\u0001\u0007J[\u0006<Wm\u00149uS>t7O\u0003\u0002\n\u0015\u0005)\u0011.\\1hK*\u00111\u0002D\u0001\u0007g>,(oY3\u000b\u00055q\u0011AA7m\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7c\u0001\u0001\u00167A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\u0004\"\u0001H\u0013\u000f\u0005u\u0019cB\u0001\u0010#\u001b\u0005y\"B\u0001\u0011\"\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\r\n\u0005\u0011:\u0012a\u00029bG.\fw-Z\u0005\u0003M\u001d\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001J\f\u0002\u0015A\f'/Y7fi\u0016\u00148/F\u0001+!\rY#\u0007N\u0007\u0002Y)\u0011QFL\u0001\u0005kRLGN\u0003\u00020a\u0005A1-\u0019;bYf\u001cHO\u0003\u00022\u001d\u0005\u00191/\u001d7\n\u0005Mb#AE\"bg\u0016Len]3og&$\u0018N^3NCB\u0004\"!N\u001d\u000f\u0005Y:\u0004C\u0001\u0010\u0018\u0013\tAt#\u0001\u0004Qe\u0016$WMZ\u0005\u0003um\u0012aa\u0015;sS:<'B\u0001\u001d\u0018\u0003-\u0001\u0018M]1nKR,'o\u001d\u0011)\u0005\tq\u0004C\u0001\f@\u0013\t\u0001uCA\u0005ue\u0006t7/[3oi\u00061A(\u001b8jiz\"\"aQ#\u0011\u0005\u0011\u0003Q\"\u0001\u0005\t\u000b!\u001a\u0001\u0019\u0001\u0016\u0015\u0005\r;\u0005\"\u0002\u0015\u0005\u0001\u0004A\u0005\u0003B\u001bJiQJ!AS\u001e\u0003\u00075\u000b\u0007/A\u0006ee>\u0004\u0018J\u001c<bY&$W#A'\u0011\u0005Yq\u0015BA(\u0018\u0005\u001d\u0011un\u001c7fC:\fA\u0002\u001a:pa&sg/\u00197jI\u0002\u0002"
)
public class ImageOptions implements Serializable {
   private final transient CaseInsensitiveMap parameters;
   private final boolean dropInvalid;

   private CaseInsensitiveMap parameters() {
      return this.parameters;
   }

   public boolean dropInvalid() {
      return this.dropInvalid;
   }

   public ImageOptions(final CaseInsensitiveMap parameters) {
      this.parameters = parameters;
      this.dropInvalid = .MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString((String)parameters.getOrElse("dropInvalid", () -> "false")));
   }

   public ImageOptions(final Map parameters) {
      this(org.apache.spark.sql.catalyst.util.CaseInsensitiveMap..MODULE$.apply(parameters));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
