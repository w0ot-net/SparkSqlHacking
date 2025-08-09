package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.module.scala.deser.OptionDeserializerModule;
import com.fasterxml.jackson.module.scala.ser.OptionSerializerModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003*\u0001\u0011\u0005#F\u0001\u0007PaRLwN\\'pIVdWM\u0003\u0002\u0006\r\u0005)1oY1mC*\u0011q\u0001C\u0001\u0007[>$W\u000f\\3\u000b\u0005%Q\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003\u00171\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u00035\t1aY8n\u0007\u0001\u0019B\u0001\u0001\t\u00179A\u0011\u0011\u0003F\u0007\u0002%)\u00111\u0003C\u0001\tI\u0006$\u0018MY5oI&\u0011QC\u0005\u0002\u0007\u001b>$W\u000f\\3\u0011\u0005]QR\"\u0001\r\u000b\u0005e!\u0011aA:fe&\u00111\u0004\u0007\u0002\u0017\u001fB$\u0018n\u001c8TKJL\u0017\r\\5{KJlu\u000eZ;mKB\u0011Q\u0004I\u0007\u0002=)\u0011q\u0004B\u0001\u0006I\u0016\u001cXM]\u0005\u0003Cy\u0011\u0001d\u00149uS>tG)Z:fe&\fG.\u001b>fe6{G-\u001e7f\u0003\u0019!\u0013N\\5uIQ\tA\u0005\u0005\u0002&O5\taEC\u0001\u0006\u0013\tAcE\u0001\u0003V]&$\u0018!D4fi6{G-\u001e7f\u001d\u0006lW\rF\u0001,!\ta3G\u0004\u0002.cA\u0011aFJ\u0007\u0002_)\u0011\u0001GD\u0001\u0007yI|w\u000e\u001e \n\u0005I2\u0013A\u0002)sK\u0012,g-\u0003\u00025k\t11\u000b\u001e:j]\u001eT!A\r\u0014"
)
public interface OptionModule extends OptionSerializerModule, OptionDeserializerModule {
   // $FF: synthetic method
   static String getModuleName$(final OptionModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "OptionModule";
   }

   static void $init$(final OptionModule $this) {
   }
}
