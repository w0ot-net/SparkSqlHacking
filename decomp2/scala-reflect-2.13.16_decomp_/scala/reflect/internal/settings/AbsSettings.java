package scala.reflect.internal.settings;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0002\u0005\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u0017\u0001\u0011\u0005q\u0003B\u0003\u001c\u0001\t\u0005ADB\u0004#\u0001A\u0005\u0019\u0013A\u0012\u0005\u000b\u0011\u001a!\u0011A\u0013\t\u000b%\u001aa\u0011\u0001\u0016\t\u000b5\u001aa\u0011\u0001\u0018\u0003\u0017\u0005\u00137oU3ui&twm\u001d\u0006\u0003\u0013)\t\u0001b]3ui&twm\u001d\u0006\u0003\u00171\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u001b9\tqA]3gY\u0016\u001cGOC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\n\u0011\u0005M!R\"\u0001\b\n\u0005Uq!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u00021A\u00111#G\u0005\u000359\u0011A!\u00168ji\n91+\u001a;uS:<\u0017CA\u000f!!\t\u0019b$\u0003\u0002 \u001d\t9aj\u001c;iS:<\u0007CA\u0011\u0004\u001b\u0005\u0001!aD!cgN+G\u000f^5oOZ\u000bG.^3\u0014\u0005\r\u0011\"!\u0001+\u0012\u0005u1\u0003CA\n(\u0013\tAcBA\u0002B]f\fQA^1mk\u0016,\u0012a\u000b\t\u0003Y\u0011i\u0011aA\u0001\nSN$UMZ1vYR,\u0012a\f\t\u0003'AJ!!\r\b\u0003\u000f\t{w\u000e\\3b]\u0002"
)
public interface AbsSettings {
   static void $init$(final AbsSettings $this) {
   }

   public interface AbsSettingValue {
      Object value();

      boolean isDefault();
   }
}
