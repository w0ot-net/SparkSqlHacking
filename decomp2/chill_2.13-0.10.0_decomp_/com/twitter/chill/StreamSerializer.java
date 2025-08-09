package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3A\u0001B\u0003\u0001\u0019!)q\u0006\u0001C\u0001a!)!\u0007\u0001C\u0001g!)1\t\u0001C\u0001\t\n\u00012\u000b\u001e:fC6\u001cVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!\u0006\u0002\u000eKM\u0011\u0001A\u0004\t\u0004\u001fM1bB\u0001\t\u0012\u001b\u0005)\u0011B\u0001\n\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001F\u000b\u0003\u0017-\u001bVM]5bY&TXM\u001d\u0006\u0003%\u0015\u00012a\u0006\u0011$\u001d\tAbD\u0004\u0002\u001a95\t!D\u0003\u0002\u001c\u0017\u00051AH]8pizJ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003%}Q\u0011!H\u0005\u0003C\t\u0012aa\u0015;sK\u0006l'B\u0001\n !\t!S\u0005\u0004\u0001\u0005\u000b\u0019\u0002!\u0019A\u0014\u0003\u0003Q\u000b\"\u0001\u000b\u0017\u0011\u0005%RS\"A\u0010\n\u0005-z\"a\u0002(pi\"Lgn\u001a\t\u0003S5J!AL\u0010\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0002cA\u0019\u0001\u0003A\u0012\u0002\u000b]\u0014\u0018\u000e^3\u0015\tQ:D(\u0011\t\u0003SUJ!AN\u0010\u0003\tUs\u0017\u000e\u001e\u0005\u0006q\t\u0001\r!O\u0001\u0005WN,'\u000f\u0005\u0002\u0010u%\u00111(\u0006\u0002\u0005\u0017JLx\u000eC\u0003>\u0005\u0001\u0007a(A\u0002pkR\u0004\"aD \n\u0005\u0001+\"AB(viB,H\u000fC\u0003C\u0005\u0001\u0007a#\u0001\u0004tiJ,\u0017-\\\u0001\u0005e\u0016\fG\r\u0006\u0003\u0017\u000b\u001a[\u0005\"\u0002\u001d\u0004\u0001\u0004I\u0004\"B$\u0004\u0001\u0004A\u0015AA5o!\ty\u0011*\u0003\u0002K+\t)\u0011J\u001c9vi\")Aj\u0001a\u0001\u001b\u0006\u00191\r\\:\u0011\u00079\u0013fC\u0004\u0002P!B\u0011\u0011dH\u0005\u0003#~\ta\u0001\u0015:fI\u00164\u0017BA*U\u0005\u0015\u0019E.Y:t\u0015\t\tv\u0004"
)
public class StreamSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final Stream stream) {
      kser.writeClassAndObject(out, stream.toList());
   }

   public Stream read(final Kryo kser, final Input in, final Class cls) {
      return ((List)kser.readClassAndObject(in)).toStream();
   }
}
