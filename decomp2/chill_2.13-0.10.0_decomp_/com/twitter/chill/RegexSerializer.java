package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005%3A\u0001B\u0003\u0001\u0019!)q\u0004\u0001C\u0001A!)!\u0005\u0001C\u0001G!)A\u0007\u0001C\u0001k\ty!+Z4fqN+'/[1mSj,'O\u0003\u0002\u0007\u000f\u0005)1\r[5mY*\u0011\u0001\"C\u0001\bi^LG\u000f^3s\u0015\u0005Q\u0011aA2p[\u000e\u00011C\u0001\u0001\u000e!\rq!#\u0006\b\u0003\u001fAi\u0011!B\u0005\u0003#\u0015\tq\u0001]1dW\u0006<W-\u0003\u0002\u0014)\tY1jU3sS\u0006d\u0017N_3s\u0015\t\tR\u0001\u0005\u0002\u0017;5\tqC\u0003\u0002\u00193\u0005AQ.\u0019;dQ&twM\u0003\u0002\u001b7\u0005!Q\u000f^5m\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u0018\u0005\u0015\u0011VmZ3y\u0003\u0019a\u0014N\\5u}Q\t\u0011\u0005\u0005\u0002\u0010\u0001\u0005)qO]5uKR!A\u0005K\u00173!\t)c%D\u0001\u001c\u0013\t93D\u0001\u0003V]&$\b\"B\u0015\u0003\u0001\u0004Q\u0013\u0001B6tKJ\u0004\"AD\u0016\n\u00051\"\"\u0001B&ss>DQA\f\u0002A\u0002=\n1a\\;u!\tq\u0001'\u0003\u00022)\t1q*\u001e;qkRDQa\r\u0002A\u0002U\t1a\u001c2k\u0003\u0011\u0011X-\u00193\u0015\tU1t\u0007\u0010\u0005\u0006S\r\u0001\rA\u000b\u0005\u0006q\r\u0001\r!O\u0001\u0003S:\u0004\"A\u0004\u001e\n\u0005m\"\"!B%oaV$\b\"B\u001f\u0004\u0001\u0004q\u0014aA2mgB\u0019qHR\u000b\u000f\u0005\u0001#\u0005CA!\u001c\u001b\u0005\u0011%BA\"\f\u0003\u0019a$o\\8u}%\u0011QiG\u0001\u0007!J,G-\u001a4\n\u0005\u001dC%!B\"mCN\u001c(BA#\u001c\u0001"
)
public class RegexSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final Regex obj) {
      out.writeString(obj.pattern().pattern());
   }

   public Regex read(final Kryo kser, final Input in, final Class cls) {
      return new Regex(in.readString(), .MODULE$);
   }
}
