package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3A\u0001B\u0003\u0001\u0019!)q\u0005\u0001C\u0001Q!)!\u0006\u0001C\u0001W!)1\b\u0001C\u0001y\tq1k\\7f'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0004\b\u0003\u0015\u0019\u0007.\u001b7m\u0015\tA\u0011\"A\u0004uo&$H/\u001a:\u000b\u0003)\t1aY8n\u0007\u0001)\"!\u0004\u0010\u0014\u0005\u0001q\u0001cA\b\u0014-9\u0011\u0001#E\u0007\u0002\u000b%\u0011!#B\u0001\ba\u0006\u001c7.Y4f\u0013\t!RCA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\n\u0006!\r9\"\u0004H\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t!1k\\7f!\tib\u0004\u0004\u0001\u0005\u000b}\u0001!\u0019\u0001\u0011\u0003\u0003Q\u000b\"!\t\u0013\u0011\u0005]\u0011\u0013BA\u0012\u0019\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aF\u0013\n\u0005\u0019B\"aA!os\u00061A(\u001b8jiz\"\u0012!\u000b\t\u0004!\u0001a\u0012!B<sSR,G\u0003\u0002\u00170ie\u0002\"aF\u0017\n\u00059B\"\u0001B+oSRDQ\u0001\r\u0002A\u0002E\nAa[:feB\u0011qBM\u0005\u0003gU\u0011Aa\u0013:z_\")QG\u0001a\u0001m\u0005\u0019q.\u001e;\u0011\u0005=9\u0014B\u0001\u001d\u0016\u0005\u0019yU\u000f\u001e9vi\")!H\u0001a\u0001-\u0005!\u0011\u000e^3n\u0003\u0011\u0011X-\u00193\u0015\tYidh\u0011\u0005\u0006a\r\u0001\r!\r\u0005\u0006\u007f\r\u0001\r\u0001Q\u0001\u0003S:\u0004\"aD!\n\u0005\t+\"!B%oaV$\b\"\u0002#\u0004\u0001\u0004)\u0015aA2mgB\u0019a)\u0014\f\u000f\u0005\u001d[\u0005C\u0001%\u0019\u001b\u0005I%B\u0001&\f\u0003\u0019a$o\\8u}%\u0011A\nG\u0001\u0007!J,G-\u001a4\n\u00059{%!B\"mCN\u001c(B\u0001'\u0019\u0001"
)
public class SomeSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final Some item) {
      kser.writeClassAndObject(out, item.get());
   }

   public Some read(final Kryo kser, final Input in, final Class cls) {
      return new Some(kser.readClassAndObject(in));
   }
}
