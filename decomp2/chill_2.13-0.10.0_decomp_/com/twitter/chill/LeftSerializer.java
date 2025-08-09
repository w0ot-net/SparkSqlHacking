package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.util.Left;

@ScalaSignature(
   bytes = "\u0006\u0005a3A\u0001B\u0003\u0001\u0019!)!\u0007\u0001C\u0001g!)Q\u0007\u0001C\u0001m!)a\t\u0001C\u0001\u000f\nqA*\u001a4u'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0004\b\u0003\u0015\u0019\u0007.\u001b7m\u0015\tA\u0011\"A\u0004uo&$H/\u001a:\u000b\u0003)\t1aY8n\u0007\u0001)2!D\u00131'\t\u0001a\u0002E\u0002\u0010'Yq!\u0001E\t\u000e\u0003\u0015I!AE\u0003\u0002\u000fA\f7m[1hK&\u0011A#\u0006\u0002\f\u0017N+'/[1mSj,'O\u0003\u0002\u0013\u000bA!q\u0003I\u00120\u001d\tAbD\u0004\u0002\u001a95\t!D\u0003\u0002\u001c\u0017\u00051AH]8pizJ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003%}Q\u0011!H\u0005\u0003C\t\u0012A\u0001T3gi*\u0011!c\b\t\u0003I\u0015b\u0001\u0001B\u0003'\u0001\t\u0007qEA\u0001B#\tAC\u0006\u0005\u0002*U5\tq$\u0003\u0002,?\t9aj\u001c;iS:<\u0007CA\u0015.\u0013\tqsDA\u0002B]f\u0004\"\u0001\n\u0019\u0005\u000bE\u0002!\u0019A\u0014\u0003\u0003\t\u000ba\u0001P5oSRtD#\u0001\u001b\u0011\tA\u00011eL\u0001\u0006oJLG/\u001a\u000b\u0005oizD\t\u0005\u0002*q%\u0011\u0011h\b\u0002\u0005+:LG\u000fC\u0003<\u0005\u0001\u0007A(\u0001\u0003lg\u0016\u0014\bCA\b>\u0013\tqTC\u0001\u0003Lef|\u0007\"\u0002!\u0003\u0001\u0004\t\u0015aA8viB\u0011qBQ\u0005\u0003\u0007V\u0011aaT;uaV$\b\"B#\u0003\u0001\u00041\u0012\u0001\u00027fMR\fAA]3bIR!a\u0003S%O\u0011\u0015Y4\u00011\u0001=\u0011\u0015Q5\u00011\u0001L\u0003\tIg\u000e\u0005\u0002\u0010\u0019&\u0011Q*\u0006\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u0006\u001f\u000e\u0001\r\u0001U\u0001\u0004G2\u001c\bcA)V-9\u0011!k\u0015\t\u00033}I!\u0001V\u0010\u0002\rA\u0013X\rZ3g\u0013\t1vKA\u0003DY\u0006\u001c8O\u0003\u0002U?\u0001"
)
public class LeftSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final Left left) {
      kser.writeClassAndObject(out, left.left().get());
   }

   public Left read(final Kryo kser, final Input in, final Class cls) {
      return .MODULE$.Left().apply(kser.readClassAndObject(in));
   }
}
