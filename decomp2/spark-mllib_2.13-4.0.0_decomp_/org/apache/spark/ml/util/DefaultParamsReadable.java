package org.apache.spark.ml.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003'\u0001\u0011\u0005q\u0005C\u0003,\u0001\u0011\u0005CFA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012\f'\r\\3\u000b\u0005\u00151\u0011\u0001B;uS2T!a\u0002\u0005\u0002\u00055d'BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0004\u0001U\u0011\u0001#H\n\u0004\u0001E9\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\rE\u0002\u00193mi\u0011\u0001B\u0005\u00035\u0011\u0011!\"\u0014'SK\u0006$\u0017M\u00197f!\taR\u0004\u0004\u0001\u0005\u000by\u0001!\u0019A\u0010\u0003\u0003Q\u000b\"\u0001I\u0012\u0011\u0005I\t\u0013B\u0001\u0012\u0014\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0005\u0013\n\u0005\u0015\u001a\"aA!os\u00061A%\u001b8ji\u0012\"\u0012\u0001\u000b\t\u0003%%J!AK\n\u0003\tUs\u0017\u000e^\u0001\u0005e\u0016\fG-F\u0001.!\rAbfG\u0005\u0003_\u0011\u0011\u0001\"\u0014'SK\u0006$WM\u001d"
)
public interface DefaultParamsReadable extends MLReadable {
   // $FF: synthetic method
   static MLReader read$(final DefaultParamsReadable $this) {
      return $this.read();
   }

   default MLReader read() {
      return new DefaultParamsReader();
   }

   static void $init$(final DefaultParamsReadable $this) {
   }
}
