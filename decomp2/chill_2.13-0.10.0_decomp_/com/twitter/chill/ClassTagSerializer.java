package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005M3A\u0001B\u0003\u0001\u0019!)!\u0006\u0001C\u0001W!)Q\u0006\u0001C\u0001]!)a\b\u0001C\u0001\u007f\t\u00112\t\\1tgR\u000bwmU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0016\u00055\u00013C\u0001\u0001\u000f!\ry1C\u0006\b\u0003!Ei\u0011!B\u0005\u0003%\u0015\tq\u0001]1dW\u0006<W-\u0003\u0002\u0015+\tY1jU3sS\u0006d\u0017N_3s\u0015\t\u0011R\u0001E\u0002\u00189yi\u0011\u0001\u0007\u0006\u00033i\tqA]3gY\u0016\u001cGOC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\u0002D\u0001\u0005DY\u0006\u001c8\u000fV1h!\ty\u0002\u0005\u0004\u0001\u0005\u000b\u0005\u0002!\u0019\u0001\u0012\u0003\u0003Q\u000b\"aI\u0014\u0011\u0005\u0011*S\"\u0001\u000e\n\u0005\u0019R\"a\u0002(pi\"Lgn\u001a\t\u0003I!J!!\u000b\u000e\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0002YA\u0019\u0001\u0003\u0001\u0010\u0002\u000b]\u0014\u0018\u000e^3\u0015\t=\u0012t\u0007\u0010\t\u0003IAJ!!\r\u000e\u0003\tUs\u0017\u000e\u001e\u0005\u0006g\t\u0001\r\u0001N\u0001\u0005WN,'\u000f\u0005\u0002\u0010k%\u0011a'\u0006\u0002\u0005\u0017JLx\u000eC\u00039\u0005\u0001\u0007\u0011(A\u0002pkR\u0004\"a\u0004\u001e\n\u0005m*\"AB(viB,H\u000fC\u0003>\u0005\u0001\u0007a#A\u0002pE*\fAA]3bIR!a\u0003Q!G\u0011\u0015\u00194\u00011\u00015\u0011\u0015\u00115\u00011\u0001D\u0003\tIg\u000e\u0005\u0002\u0010\t&\u0011Q)\u0006\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u0006\u000f\u000e\u0001\r\u0001S\u0001\u0004G2\u001c\bcA%Q-9\u0011!J\u0014\t\u0003\u0017ji\u0011\u0001\u0014\u0006\u0003\u001b.\ta\u0001\u0010:p_Rt\u0014BA(\u001b\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011K\u0015\u0002\u0006\u00072\f7o\u001d\u0006\u0003\u001fj\u0001"
)
public class ClassTagSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final ClassTag obj) {
      kser.writeObject(out, obj.runtimeClass());
   }

   public ClassTag read(final Kryo kser, final Input in, final Class cls) {
      Class clazz = (Class)kser.readObject(in, Class.class);
      return .MODULE$.apply(clazz);
   }
}
