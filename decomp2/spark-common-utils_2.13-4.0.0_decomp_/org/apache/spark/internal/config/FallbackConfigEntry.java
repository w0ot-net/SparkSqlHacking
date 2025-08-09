package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514Q!\u0004\b\u0001%aA\u0011\"\f\u0001\u0003\u0002\u0003\u0006IAL\u001d\t\u0013i\u0002!\u0011!Q\u0001\nmr\u0004\"C \u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018A\u0011%\t\u0005A!A!\u0002\u0013\u00115\nC\u0005M\u0001\t\u0005\t\u0015!\u0003/\u001b\"Ia\n\u0001B\u0001B\u0003%qJ\u0015\u0005\n'\u0002\u0011\t\u0011)A\u0005]QC\u0001\"\u0016\u0001\u0003\u0006\u0004%\tA\u0016\u0005\t/\u0002\u0011\t\u0011)A\u00055!)\u0001\f\u0001C\u00013\")1\r\u0001C!I\")Q\r\u0001C!M\n\u0019b)\u00197mE\u0006\u001c7nQ8oM&<WI\u001c;ss*\u0011q\u0002E\u0001\u0007G>tg-[4\u000b\u0005E\u0011\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005M!\u0012!B:qCJ\\'BA\u000b\u0017\u0003\u0019\t\u0007/Y2iK*\tq#A\u0002pe\u001e,\"!\u0007\u0011\u0014\u0005\u0001Q\u0002cA\u000e\u001d=5\ta\"\u0003\u0002\u001e\u001d\tY1i\u001c8gS\u001e,e\u000e\u001e:z!\ty\u0002\u0005\u0004\u0001\u0005\u000b\u0005\u0002!\u0019A\u0012\u0003\u0003Q\u001b\u0001!\u0005\u0002%UA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t9aj\u001c;iS:<\u0007CA\u0013,\u0013\tacEA\u0002B]f\f1a[3z!\tycG\u0004\u00021iA\u0011\u0011GJ\u0007\u0002e)\u00111GI\u0001\u0007yI|w\u000e\u001e \n\u0005U2\u0013A\u0002)sK\u0012,g-\u0003\u00028q\t11\u000b\u001e:j]\u001eT!!\u000e\u0014\n\u00055b\u0012\u0001\u00049sKB,g\u000eZ3e\u0017\u0016L\bcA\u0013=]%\u0011QH\n\u0002\u0007\u001fB$\u0018n\u001c8\n\u0005ib\u0012\u0001\u00059sKB,g\u000eZ*fa\u0006\u0014\u0018\r^8s\u0013\tyD$\u0001\u0007bYR,'O\\1uSZ,7\u000fE\u0002D\u0011:r!\u0001\u0012$\u000f\u0005E*\u0015\"A\u0014\n\u0005\u001d3\u0013a\u00029bG.\fw-Z\u0005\u0003\u0013*\u0013A\u0001T5ti*\u0011qIJ\u0005\u0003\u0003r\t1\u0001Z8d\u0013\taE$\u0001\u0005jgB+(\r\\5d!\t)\u0003+\u0003\u0002RM\t9!i\\8mK\u0006t\u0017B\u0001(\u001d\u0003\u001d1XM]:j_:L!a\u0015\u000f\u0002\u0011\u0019\fG\u000e\u001c2bG.,\u0012AG\u0001\nM\u0006dGNY1dW\u0002\na\u0001P5oSRtD#\u0003.\\9vsv\fY1c!\rY\u0002A\b\u0005\u0006[)\u0001\rA\f\u0005\u0006u)\u0001\ra\u000f\u0005\u0006\u007f)\u0001\rA\f\u0005\u0006\u0003*\u0001\rA\u0011\u0005\u0006\u0019*\u0001\rA\f\u0005\u0006\u001d*\u0001\ra\u0014\u0005\u0006'*\u0001\rA\f\u0005\u0006+*\u0001\rAG\u0001\u0013I\u00164\u0017-\u001e7u-\u0006dW/Z*ue&tw-F\u0001/\u0003!\u0011X-\u00193Ge>lGC\u0001\u0010h\u0011\u0015AG\u00021\u0001j\u0003\u0019\u0011X-\u00193feB\u00111D[\u0005\u0003W:\u0011AbQ8oM&<'+Z1eKJ\u0004"
)
public class FallbackConfigEntry extends ConfigEntry {
   private final ConfigEntry fallback;

   public ConfigEntry fallback() {
      return this.fallback;
   }

   public String defaultValueString() {
      return "<value of " + this.fallback().key() + ">";
   }

   public Object readFrom(final ConfigReader reader) {
      return this.readString(reader).map(this.valueConverter()).getOrElse(() -> this.fallback().readFrom(reader));
   }

   public FallbackConfigEntry(final String key, final Option prependedKey, final String prependSeparator, final List alternatives, final String doc, final boolean isPublic, final String version, final ConfigEntry fallback) {
      super(key, prependedKey, prependSeparator, alternatives, fallback.valueConverter(), fallback.stringConverter(), doc, isPublic, version);
      this.fallback = fallback;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
