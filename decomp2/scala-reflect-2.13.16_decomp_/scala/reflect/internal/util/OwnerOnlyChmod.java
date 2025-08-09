package scala.reflect.internal.util;

import java.nio.file.Path;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=;Q!\u0003\u0006\t\u0002M1Q!\u0006\u0006\t\u0002YAQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\nyAaAL\u0001!\u0002\u0013y\u0003B\u0002\u001e\u0002A\u0003%q\u0006C\u0003<\u0003\u0011\u0005A\bC\u0003B\u0003\u0011\u0005!\tC\u0003E\u0003\u0011\u0005Q)\u0001\bPo:,'o\u00148ms\u000eCWn\u001c3\u000b\u0005-a\u0011\u0001B;uS2T!!\u0004\b\u0002\u0011%tG/\u001a:oC2T!a\u0004\t\u0002\u000fI,g\r\\3di*\t\u0011#A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005Q\tQ\"\u0001\u0006\u0003\u001d=;h.\u001a:P]2L8\t[7pIN\u0011\u0011a\u0006\t\u00031ei\u0011\u0001E\u0005\u00035A\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0014\u0003!\u0019\u0017M\u001c)pg&DHCA\u0010#!\tA\u0002%\u0003\u0002\"!\t9!i\\8mK\u0006t\u0007\"B\u0012\u0004\u0001\u0004!\u0013\u0001\u00029bi\"\u0004\"!\n\u0017\u000e\u0003\u0019R!a\n\u0015\u0002\t\u0019LG.\u001a\u0006\u0003S)\n1A\\5p\u0015\u0005Y\u0013\u0001\u00026bm\u0006L!!\f\u0014\u0003\tA\u000bG\u000f[\u0001\ta>\u001c\u0018\u000e\u001f#jeB\u0019\u0001G\r\u001b\u000e\u0003ER!a\u0003\u0016\n\u0005M\n$aB#ok6\u001cV\r\u001e\t\u0003kaj\u0011A\u000e\u0006\u0003o\u0019\n\u0011\"\u0019;ue&\u0014W\u000f^3\n\u0005e2$a\u0005)pg&Dh)\u001b7f!\u0016\u0014X.[:tS>t\u0017!\u00039pg&Dh)\u001b7f\u0003\u0015\u0019\u0007.\\8e)\ti\u0004\t\u0005\u0002\u0019}%\u0011q\b\u0005\u0002\u0005+:LG\u000fC\u0003$\r\u0001\u0007A%\u0001\fdQ6|GMR5mK>\u00138I]3bi\u0016,U\u000e\u001d;z)\ti4\tC\u0003$\u000f\u0001\u0007A%A\tdQ6|GMR5mK\u0006sGm\u0016:ji\u0016$2!\u0010$H\u0011\u0015\u0019\u0003\u00021\u0001%\u0011\u0015A\u0005\u00021\u0001J\u0003!\u0019wN\u001c;f]R\u001c\bc\u0001\rK\u0019&\u00111\n\u0005\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u000315K!A\u0014\t\u0003\t\tKH/\u001a"
)
public final class OwnerOnlyChmod {
   public static void chmodFileAndWrite(final Path path, final byte[] contents) {
      OwnerOnlyChmod$.MODULE$.chmodFileAndWrite(path, contents);
   }

   public static void chmodFileOrCreateEmpty(final Path path) {
      OwnerOnlyChmod$.MODULE$.chmodFileOrCreateEmpty(path);
   }

   public static void chmod(final Path path) {
      OwnerOnlyChmod$.MODULE$.chmod(path);
   }
}
