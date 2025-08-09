package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005a3A\u0001B\u0003\u0001\u0019!)!\u0007\u0001C\u0001g!)Q\u0007\u0001C\u0001m!)a\t\u0001C\u0001\u000f\ny!+[4iiN+'/[1mSj,'O\u0003\u0002\u0007\u000f\u0005)1\r[5mY*\u0011\u0001\"C\u0001\bi^LG\u000f^3s\u0015\u0005Q\u0011aA2p[\u000e\u0001QcA\u0007&aM\u0011\u0001A\u0004\t\u0004\u001fM1bB\u0001\t\u0012\u001b\u0005)\u0011B\u0001\n\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001F\u000b\u0003\u0017-\u001bVM]5bY&TXM\u001d\u0006\u0003%\u0015\u0001Ba\u0006\u0011$_9\u0011\u0001D\b\b\u00033qi\u0011A\u0007\u0006\u00037-\ta\u0001\u0010:p_Rt\u0014\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy\"\"A\u000f\n\u0005\u0005\u0012#!\u0002*jO\"$(B\u0001\n !\t!S\u0005\u0004\u0001\u0005\u000b\u0019\u0002!\u0019A\u0014\u0003\u0003\u0005\u000b\"\u0001\u000b\u0017\u0011\u0005%RS\"A\u0010\n\u0005-z\"a\u0002(pi\"Lgn\u001a\t\u0003S5J!AL\u0010\u0003\u0007\u0005s\u0017\u0010\u0005\u0002%a\u0011)\u0011\u0007\u0001b\u0001O\t\t!)\u0001\u0004=S:LGO\u0010\u000b\u0002iA!\u0001\u0003A\u00120\u0003\u00159(/\u001b;f)\u00119$h\u0010#\u0011\u0005%B\u0014BA\u001d \u0005\u0011)f.\u001b;\t\u000bm\u0012\u0001\u0019\u0001\u001f\u0002\t-\u001cXM\u001d\t\u0003\u001fuJ!AP\u000b\u0003\t-\u0013\u0018p\u001c\u0005\u0006\u0001\n\u0001\r!Q\u0001\u0004_V$\bCA\bC\u0013\t\u0019UC\u0001\u0004PkR\u0004X\u000f\u001e\u0005\u0006\u000b\n\u0001\rAF\u0001\u0006e&<\u0007\u000e^\u0001\u0005e\u0016\fG\r\u0006\u0003\u0017\u0011&s\u0005\"B\u001e\u0004\u0001\u0004a\u0004\"\u0002&\u0004\u0001\u0004Y\u0015AA5o!\tyA*\u0003\u0002N+\t)\u0011J\u001c9vi\")qj\u0001a\u0001!\u0006\u00191\r\\:\u0011\u0007E+fC\u0004\u0002S'B\u0011\u0011dH\u0005\u0003)~\ta\u0001\u0015:fI\u00164\u0017B\u0001,X\u0005\u0015\u0019E.Y:t\u0015\t!v\u0004"
)
public class RightSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final Right right) {
      kser.writeClassAndObject(out, right.right().get());
   }

   public Right read(final Kryo kser, final Input in, final Class cls) {
      return .MODULE$.Right().apply(kser.readClassAndObject(in));
   }
}
