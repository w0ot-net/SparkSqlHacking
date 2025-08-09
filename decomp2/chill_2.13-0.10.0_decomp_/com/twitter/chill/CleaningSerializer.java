package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3A!\u0002\u0004\u0001\u001b!AQ\u0005\u0001B\u0001B\u0003%q\u0002C\u0003'\u0001\u0011\u0005q\u0005C\u0003+\u0001\u0011\u00051\u0006C\u0003<\u0001\u0011\u0005AH\u0001\nDY\u0016\fg.\u001b8h'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0004\t\u0003\u0015\u0019\u0007.\u001b7m\u0015\tI!\"A\u0004uo&$H/\u001a:\u000b\u0003-\t1aY8n\u0007\u0001)\"AD\r\u0014\u0005\u0001y\u0001c\u0001\t\u0015/9\u0011\u0011CE\u0007\u0002\r%\u00111CB\u0001\ba\u0006\u001c7.Y4f\u0013\t)bCA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\n\u0007!\tA\u0012\u0004\u0004\u0001\u0005\u000bi\u0001!\u0019A\u000e\u0003\u0003Q\u000b\"\u0001\b\u0012\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\u000f9{G\u000f[5oOB\u0011QdI\u0005\u0003Iy\u0011a!\u00118z%\u00164\u0017aB<sCB\u0004X\rZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005!J\u0003cA\t\u0001/!)QE\u0001a\u0001\u001f\u0005)qO]5uKR!Af\f\u001b:!\tiR&\u0003\u0002/=\t!QK\\5u\u0011\u0015\u00014\u00011\u00012\u0003\u0011Y7/\u001a:\u0011\u0005A\u0011\u0014BA\u001a\u0017\u0005\u0011Y%/_8\t\u000bU\u001a\u0001\u0019\u0001\u001c\u0002\u0007=,H\u000f\u0005\u0002\u0011o%\u0011\u0001H\u0006\u0002\u0007\u001fV$\b/\u001e;\t\u000bi\u001a\u0001\u0019A\f\u0002\t%$X-\\\u0001\u0005e\u0016\fG\r\u0006\u0003\u0018{y\u001a\u0005\"\u0002\u0019\u0005\u0001\u0004\t\u0004\"B \u0005\u0001\u0004\u0001\u0015AA5o!\t\u0001\u0012)\u0003\u0002C-\t)\u0011J\u001c9vi\")A\t\u0002a\u0001\u000b\u0006\u00191\r\\:\u0011\u0007\u0019kuC\u0004\u0002H\u0017B\u0011\u0001JH\u0007\u0002\u0013*\u0011!\nD\u0001\u0007yI|w\u000e\u001e \n\u00051s\u0012A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n)1\t\\1tg*\u0011AJ\b"
)
public class CleaningSerializer extends Serializer {
   private final Serializer wrapped;

   public void write(final Kryo kser, final Output out, final Object item) {
      ClosureCleaner$.MODULE$.apply(item);
      this.wrapped.write(kser, out, item);
   }

   public Object read(final Kryo kser, final Input in, final Class cls) {
      return this.wrapped.read(kser, in, cls);
   }

   public CleaningSerializer(final Serializer wrapped) {
      this.wrapped = wrapped;
   }
}
