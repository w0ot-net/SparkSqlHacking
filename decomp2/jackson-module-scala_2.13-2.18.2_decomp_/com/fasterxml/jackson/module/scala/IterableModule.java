package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.module.scala.ser.IterableSerializerModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003$\u0001\u0011\u0005CE\u0001\bJi\u0016\u0014\u0018M\u00197f\u001b>$W\u000f\\3\u000b\u0005\u00151\u0011!B:dC2\f'BA\u0004\t\u0003\u0019iw\u000eZ;mK*\u0011\u0011BC\u0001\bU\u0006\u001c7n]8o\u0015\tYA\"A\u0005gCN$XM\u001d=nY*\tQ\"A\u0002d_6\u001c\u0001aE\u0002\u0001!Y\u0001\"!\u0005\u000b\u000e\u0003IQ!a\u0005\u0005\u0002\u0011\u0011\fG/\u00192j]\u0012L!!\u0006\n\u0003\r5{G-\u001e7f!\t9\"$D\u0001\u0019\u0015\tIB!A\u0002tKJL!a\u0007\r\u00031%#XM]1cY\u0016\u001cVM]5bY&TXM]'pIVdW-\u0001\u0004%S:LG\u000f\n\u000b\u0002=A\u0011q$I\u0007\u0002A)\tQ!\u0003\u0002#A\t!QK\\5u\u000359W\r^'pIVdWMT1nKR\tQ\u0005\u0005\u0002'[9\u0011qe\u000b\t\u0003Q\u0001j\u0011!\u000b\u0006\u0003U9\ta\u0001\u0010:p_Rt\u0014B\u0001\u0017!\u0003\u0019\u0001&/\u001a3fM&\u0011af\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051\u0002\u0003"
)
public interface IterableModule extends IterableSerializerModule {
   // $FF: synthetic method
   static String getModuleName$(final IterableModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "IterableModule";
   }

   static void $init$(final IterableModule $this) {
   }
}
