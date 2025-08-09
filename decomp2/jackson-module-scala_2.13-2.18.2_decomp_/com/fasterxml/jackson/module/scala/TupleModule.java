package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.module.scala.deser.TupleDeserializerModule;
import com.fasterxml.jackson.module.scala.ser.TupleSerializerModule;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003*\u0001\u0011\u0005#FA\u0006UkBdW-T8ek2,'BA\u0003\u0007\u0003\u0015\u00198-\u00197b\u0015\t9\u0001\"\u0001\u0004n_\u0012,H.\u001a\u0006\u0003\u0013)\tqA[1dWN|gN\u0003\u0002\f\u0019\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002\u001b\u0005\u00191m\\7\u0004\u0001M!\u0001\u0001\u0005\f\u001d!\t\tB#D\u0001\u0013\u0015\t\u0019\u0002\"\u0001\u0005eCR\f'-\u001b8e\u0013\t)\"C\u0001\u0004N_\u0012,H.\u001a\t\u0003/ii\u0011\u0001\u0007\u0006\u00033\u0011\t1a]3s\u0013\tY\u0002DA\u000bUkBdWmU3sS\u0006d\u0017N_3s\u001b>$W\u000f\\3\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0005}!\u0011!\u00023fg\u0016\u0014\u0018BA\u0011\u001f\u0005]!V\u000f\u001d7f\t\u0016\u001cXM]5bY&TXM]'pIVdW-\u0001\u0004%S:LG\u000f\n\u000b\u0002IA\u0011QeJ\u0007\u0002M)\tQ!\u0003\u0002)M\t!QK\\5u\u000359W\r^'pIVdWMT1nKR\t1\u0006\u0005\u0002-g9\u0011Q&\r\t\u0003]\u0019j\u0011a\f\u0006\u0003a9\ta\u0001\u0010:p_Rt\u0014B\u0001\u001a'\u0003\u0019\u0001&/\u001a3fM&\u0011A'\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005I2\u0003"
)
public interface TupleModule extends TupleSerializerModule, TupleDeserializerModule {
   // $FF: synthetic method
   static String getModuleName$(final TupleModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "TupleModule";
   }

   static void $init$(final TupleModule $this) {
   }
}
