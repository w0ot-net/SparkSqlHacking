package scala.util.parsing.combinator;

import java.util.stream.IntStream;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153QAC\u0006\u0001\u0017MA\u0001b\b\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tC\u0001\u0011\t\u0011)A\u0005E!Aa\u0005\u0001BC\u0002\u0013\u0005q\u0005\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003#\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u0015I\u0003\u0001\"\u00011\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u0015Q\u0004\u0001\"\u0001<\u0011\u0015\u0001\u0005\u0001\"\u0011B\u0005-\u0019VOY*fcV,gnY3\u000b\u00051i\u0011AC2p[\nLg.\u0019;pe*\u0011abD\u0001\ba\u0006\u00148/\u001b8h\u0015\t\u0001\u0012#\u0001\u0003vi&d'\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\u0014\u0007\u0001!B\u0004\u0005\u0002\u001655\taC\u0003\u0002\u00181\u0005!A.\u00198h\u0015\u0005I\u0012\u0001\u00026bm\u0006L!a\u0007\f\u0003\r=\u0013'.Z2u!\t)R$\u0003\u0002\u001f-\ta1\t[1s'\u0016\fX/\u001a8dK\u0006\t1o\u0001\u0001\u0002\u000bM$\u0018M\u001d;\u0011\u0005\r\"S\"A\t\n\u0005\u0015\n\"aA%oi\u00061A.\u001a8hi\",\u0012AI\u0001\bY\u0016tw\r\u001e5!\u0003\u0019a\u0014N\\5u}Q!1&\f\u00180!\ta\u0003!D\u0001\f\u0011\u0015yR\u00011\u0001\u001d\u0011\u0015\tS\u00011\u0001#\u0011\u00151S\u00011\u0001#)\rY\u0013G\r\u0005\u0006?\u0019\u0001\r\u0001\b\u0005\u0006C\u0019\u0001\rAI\u0001\u0007G\"\f'/\u0011;\u0015\u0005UB\u0004CA\u00127\u0013\t9\u0014C\u0001\u0003DQ\u0006\u0014\b\"B\u001d\b\u0001\u0004\u0011\u0013!A5\u0002\u0017M,(mU3rk\u0016t7-\u001a\u000b\u0004Wqr\u0004\"B\u001f\t\u0001\u0004\u0011\u0013AB0ti\u0006\u0014H\u000fC\u0003@\u0011\u0001\u0007!%\u0001\u0003`K:$\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\t\u0003\"!F\"\n\u0005\u00113\"AB*ue&tw\r"
)
public class SubSequence implements CharSequence {
   private final CharSequence s;
   private final int start;
   private final int length;

   public IntStream chars() {
      return super.chars();
   }

   public IntStream codePoints() {
      return super.codePoints();
   }

   public int length() {
      return this.length;
   }

   public char charAt(final int i) {
      if (i >= 0 && i < this.length()) {
         return this.s.charAt(this.start + i);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(17)).append("index: ").append(i).append(", length: ").append(this.length()).toString());
      }
   }

   public SubSequence subSequence(final int _start, final int _end) {
      if (_start >= 0 && _end >= 0 && _end <= this.length() && _start <= _end) {
         return new SubSequence(this.s, this.start + _start, _end - _start);
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(24)).append("start: ").append(_start).append(", end: ").append(_end).append(", length: ").append(this.length()).toString());
      }
   }

   public String toString() {
      return this.s.subSequence(this.start, this.start + this.length()).toString();
   }

   public SubSequence(final CharSequence s, final int start, final int length) {
      this.s = s;
      this.start = start;
      this.length = length;
   }

   public SubSequence(final CharSequence s, final int start) {
      this(s, start, s.length() - start);
   }
}
