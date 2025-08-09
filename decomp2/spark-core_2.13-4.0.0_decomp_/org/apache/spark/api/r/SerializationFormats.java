package org.apache.spark.api.r;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A:a!\u0003\u0006\t\u00029!bA\u0002\f\u000b\u0011\u0003qq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0004\"\u0003\t\u0007I\u0011\u0001\u0012\t\r-\n\u0001\u0015!\u0003$\u0011\u001da\u0013A1A\u0005\u0002\tBa!L\u0001!\u0002\u0013\u0019\u0003b\u0002\u0018\u0002\u0005\u0004%\tA\t\u0005\u0007_\u0005\u0001\u000b\u0011B\u0012\u0002)M+'/[1mSj\fG/[8o\r>\u0014X.\u0019;t\u0015\tYA\"A\u0001s\u0015\tia\"A\u0002ba&T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'o\u001a\t\u0003+\u0005i\u0011A\u0003\u0002\u0015'\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8G_Jl\u0017\r^:\u0014\u0005\u0005A\u0002CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005!\u0012\u0001\u0002\"Z)\u0016+\u0012a\t\t\u0003I%j\u0011!\n\u0006\u0003M\u001d\nA\u0001\\1oO*\t\u0001&\u0001\u0003kCZ\f\u0017B\u0001\u0016&\u0005\u0019\u0019FO]5oO\u0006)!)\u0017+FA\u000511\u000b\u0016*J\u001d\u001e\u000bqa\u0015+S\u0013:;\u0005%A\u0002S\u001f^\u000bAAU(XA\u0001"
)
public final class SerializationFormats {
   public static String ROW() {
      return SerializationFormats$.MODULE$.ROW();
   }

   public static String STRING() {
      return SerializationFormats$.MODULE$.STRING();
   }

   public static String BYTE() {
      return SerializationFormats$.MODULE$.BYTE();
   }
}
