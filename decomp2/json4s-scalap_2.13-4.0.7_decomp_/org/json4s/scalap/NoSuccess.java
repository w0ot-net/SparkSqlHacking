package org.json4s.scalap;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u000554QAC\u0006\u0002\"IAQA\n\u0001\u0005\u0002\u001dBQ!\u000b\u0001\u0005\u0002)BQa\u000b\u0001\u0005\u0002)BQ\u0001\f\u0001\u0005\u00025BQ!\r\u0001\u0005\u0002IBQ\u0001\u0010\u0001\u0005\u0002uBQ!\r\u0001\u0005\u0002\u0011CQ!\u0015\u0001\u0005\u0002ICQa\u0017\u0001\u0005\u0002q\u0013\u0011BT8Tk\u000e\u001cWm]:\u000b\u00051i\u0011AB:dC2\f\u0007O\u0003\u0002\u000f\u001f\u00051!n]8oiMT\u0011\u0001E\u0001\u0004_J<7\u0001A\u000b\u0003'\u0001\u001a\"\u0001\u0001\u000b\u0011\u000bU1\u0002\u0004\u0007\u0010\u000e\u0003-I!aF\u0006\u0003\rI+7/\u001e7u!\tIB$D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\b\u0011\r\u0001\u00111\u0011\u0005\u0001CC\u0002\t\u0012\u0011\u0001W\t\u00031\r\u0002\"!\u0007\u0013\n\u0005\u0015R\"aA!os\u00061A(\u001b8jiz\"\u0012\u0001\u000b\t\u0004+\u0001q\u0012aA8viV\t\u0001$A\u0003wC2,X-\u0001\u0005u_>\u0003H/[8o+\u0005q\u0003CA\r0\u0015\t\u0001$$\u0001\u0003O_:,\u0017aA7baV\u00111G\u000f\u000b\u0003QQBQ!N\u0003A\u0002Y\n\u0011A\u001a\t\u00053]B\u0012(\u0003\u000295\tIa)\u001e8di&|g.\r\t\u0003?i\"QaO\u0003C\u0002\t\u0012\u0011AQ\u0001\u0007[\u0006\u0004x*\u001e;\u0016\u0005y\u0012EC\u0001\u0015@\u0011\u0015)d\u00011\u0001A!\u0011Ir\u0007G!\u0011\u0005}\u0011E!B\"\u0007\u0005\u0004\u0011#\u0001B(viJ*2!\u0012(Q)\tAc\tC\u00036\u000f\u0001\u0007q\tE\u0003\u001a\u0011bA\"*\u0003\u0002J5\tIa)\u001e8di&|gN\r\t\u00053-ku*\u0003\u0002M5\t1A+\u001e9mKJ\u0002\"a\b(\u0005\u000b\r;!\u0019\u0001\u0012\u0011\u0005}\u0001F!B\u001e\b\u0005\u0004\u0011\u0013a\u00024mCRl\u0015\r]\u000b\u0004'bSFC\u0001\u0015U\u0011\u0015)\u0004\u00021\u0001V!\u0015I\u0002\n\u0007\rW!\u0015)bcV-\u0019!\ty\u0002\fB\u0003D\u0011\t\u0007!\u0005\u0005\u0002 5\u0012)1\b\u0003b\u0001E\u00051qN]#mg\u0016,2!\u00181c)\tq6\rE\u0003\u0016-}\u000b\u0007\u0004\u0005\u0002 A\u0012)1)\u0003b\u0001EA\u0011qD\u0019\u0003\u0006w%\u0011\rA\t\u0005\u0007I&!\t\u0019A3\u0002\u000b=$\b.\u001a:\u0011\u0007e1g,\u0003\u0002h5\tAAHY=oC6,g(K\u0002\u0001S.L!A[\u0006\u0003\u000b\u0015\u0013(o\u001c:\u000b\u00051\\\u0011a\u0002$bS2,(/\u001a"
)
public abstract class NoSuccess extends Result {
   public Nothing out() {
      throw new ScalaSigParserError("No output");
   }

   public Nothing value() {
      throw new ScalaSigParserError("No value");
   }

   public None toOption() {
      return .MODULE$;
   }

   public NoSuccess map(final Function1 f) {
      return this;
   }

   public NoSuccess mapOut(final Function1 f) {
      return this;
   }

   public NoSuccess map(final Function2 f) {
      return this;
   }

   public NoSuccess flatMap(final Function2 f) {
      return this;
   }

   public Result orElse(final Function0 other) {
      return (Result)other.apply();
   }
}
