package org.apache.spark.ml.util;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d;aAB\u0004\t\u0002%\tbAB\n\b\u0011\u0003IA\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0004\u001f\u0003\t\u0007I\u0011A\u0010\t\rY\n\u0001\u0015!\u0003!\u0011\u0015q\u0014\u0001\"\u0001@\u0003MiE*\u00117m_^d\u0015n\u001d;fI2{\u0017\rZ3s\u0015\tA\u0011\"\u0001\u0003vi&d'B\u0001\u0006\f\u0003\tiGN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h!\t\u0011\u0012!D\u0001\b\u0005MiE*\u00117m_^d\u0015n\u001d;fI2{\u0017\rZ3s'\t\tQ\u0003\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0011#A\ttC\u001a,W\nT\"mCN\u001cHj\\1eKJ,\u0012\u0001\t\t\u0005-\u0005\u001ac&\u0003\u0002#/\tIa)\u001e8di&|g.\r\t\u0003I-r!!J\u0015\u0011\u0005\u0019:R\"A\u0014\u000b\u0005!b\u0012A\u0002\u001fs_>$h(\u0003\u0002+/\u00051\u0001K]3eK\u001aL!\u0001L\u0017\u0003\rM#(/\u001b8h\u0015\tQs\u0003\r\u00020iA\u0019A\u0005\r\u001a\n\u0005Ej#!B\"mCN\u001c\bCA\u001a5\u0019\u0001!\u0011\"\u000e\u0003\u0002\u0002\u0003\u0005)\u0011A\u001c\u0003\u0007}#\u0013'\u0001\ntC\u001a,W\nT\"mCN\u001cHj\\1eKJ\u0004\u0013C\u0001\u001d<!\t1\u0012(\u0003\u0002;/\t9aj\u001c;iS:<\u0007C\u0001\f=\u0013\titCA\u0002B]f\fA\u0001\\8bIR\u0011\u0001)\u0012\u0019\u0003\u0003\u000e\u00032\u0001\n\u0019C!\t\u00194\tB\u0005E\u000b\u0005\u0005\t\u0011!B\u0001o\t\u0019q\fJ\u001a\t\u000b\u0019+\u0001\u0019A\u0012\u0002\u0013\rd\u0017m]:OC6,\u0007"
)
public final class MLAllowListedLoader {
   public static Class load(final String className) {
      return MLAllowListedLoader$.MODULE$.load(className);
   }

   public static Function1 safeMLClassLoader() {
      return MLAllowListedLoader$.MODULE$.safeMLClassLoader();
   }
}
