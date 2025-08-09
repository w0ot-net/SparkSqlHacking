package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.module.scala.modifiers.MapTypeModifierModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003&\u0001\u0011\u0005cEA\nNCB\u001cVM]5bY&TXM]'pIVdWM\u0003\u0002\u0006\r\u0005\u00191/\u001a:\u000b\u0005\u001dA\u0011!B:dC2\f'BA\u0005\u000b\u0003\u0019iw\u000eZ;mK*\u00111\u0002D\u0001\bU\u0006\u001c7n]8o\u0015\tia\"A\u0005gCN$XM\u001d=nY*\tq\"A\u0002d_6\u001c\u0001aE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ!!\u0006\u0006\u0002\u0011\u0011\fG/\u00192j]\u0012L!a\u0006\u000b\u0003\r5{G-\u001e7f!\tIB$D\u0001\u001b\u0015\tYb!A\u0005n_\u0012Lg-[3sg&\u0011QD\u0007\u0002\u0016\u001b\u0006\u0004H+\u001f9f\u001b>$\u0017NZ5fe6{G-\u001e7f\u0003\u0019!\u0013N\\5uIQ\t\u0001\u0005\u0005\u0002\"G5\t!EC\u0001\b\u0013\t!#E\u0001\u0003V]&$\u0018!D4fi6{G-\u001e7f\u001d\u0006lW\rF\u0001(!\tAsF\u0004\u0002*[A\u0011!FI\u0007\u0002W)\u0011A\u0006E\u0001\u0007yI|w\u000e\u001e \n\u00059\u0012\u0013A\u0002)sK\u0012,g-\u0003\u00021c\t11\u000b\u001e:j]\u001eT!A\f\u0012"
)
public interface MapSerializerModule extends MapTypeModifierModule {
   // $FF: synthetic method
   static String getModuleName$(final MapSerializerModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "MapSerializerModule";
   }

   static void $init$(final MapSerializerModule $this) {
      $this.$plus$eq(MapSerializerResolver$.MODULE$);
   }
}
