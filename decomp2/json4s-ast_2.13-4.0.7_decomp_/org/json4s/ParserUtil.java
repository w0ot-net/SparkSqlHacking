package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.Tuple3;
import scala.collection.immutable.List;
import scala.collection.immutable.Vector;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%u!B\u0013'\u0011\u0003Yc!B\u0017'\u0011\u0003q\u0003\"B\u001b\u0002\t\u00031d\u0001B\u001c\u0002\u0001aB\u0001\"R\u0002\u0003\u0002\u0003\u0006IA\u0012\u0005\t\u001d\u000e\u0011\t\u0011)A\u0005s!)Qg\u0001C\u0001\u001f\"1A+\u0001Q\u0001\nUCQ\u0001W\u0001\u0005\u0002eCa\u0001W\u0001\u0005\u0002\u0019\n\u0007\"B<\u0002\t\u0003A\bBB<\u0002\t\u000313P\u0002\u0004\u0000\u0003\u00011\u0013\u0011\u0001\u0005\u000b\u0003\u0007a!\u0011!Q\u0001\n\u0005\u0015\u0001\"CA\t\u0019\t\u0005\t\u0015!\u0003_\u0011\u0019)D\u0002\"\u0001\u0002\u0014!A\u0011\u0011\u0004\u0007!B\u0013\tY\u0002\u0003\u0005\u0002\"1\u0001\u000b\u0015BA\u000e\u0011!\t\u0019\u0003\u0004Q!\n\u0005m\u0001\"CA\u0013\u0019\u0001\u0007I\u0011AA\u0014\u0011%\tI\u0003\u0004a\u0001\n\u0003\tY\u0003C\u0004\u000281\u0001\u000b\u0015\u00020\t\u0011\u0005eB\u0002)Q\u0005\u0003wA\u0001\"a\u0012\rA\u0003&\u0011\u0011\n\u0005\t\u0003\u001fb\u0001\u0015)\u0003\u0002\u001c!A\u0011\u0011\u000b\u0007!B\u0013\tY\u0002C\u0004\u0002T1!\t!!\u0016\t\u000f\u0005]C\u0002\"\u0001\u0002V!9\u0011\u0011\f\u0007\u0005\u0002\u0005m\u0003bBA/\u0019\u0011\u0005\u0011q\f\u0005\b\u0003CbA\u0011AA0\u0011\u001d\t\u0019\u0007\u0004C\u0001\u0003+B\u0001\"!\u001a\r\t\u00031\u0013Q\u000b\u0005\t\u0003Ob\u0001\u0015\"\u0003\u0002j!A\u00111N\u0001\u0005\u0002\u0019\ni\u0007\u0003\u0005\u0002p\u0005\u0001\u000b\u0011BA9\u0011!\ti(\u0001C\u0001M\u0005}\u0014A\u0003)beN,'/\u0016;jY*\u0011q\u0005K\u0001\u0007UN|g\u000eN:\u000b\u0003%\n1a\u001c:h\u0007\u0001\u0001\"\u0001L\u0001\u000e\u0003\u0019\u0012!\u0002U1sg\u0016\u0014X\u000b^5m'\t\tq\u0006\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u0012a\u0002U1sg\u0016,\u0005pY3qi&|gn\u0005\u0002\u0004sA\u0011!H\u0011\b\u0003w\u0001s!\u0001P \u000e\u0003uR!A\u0010\u0016\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0014BA!2\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0011#\u0003\u0013\u0015C8-\u001a9uS>t'BA!2\u0003\u001diWm]:bO\u0016\u0004\"aR&\u000f\u0005!K\u0005C\u0001\u001f2\u0013\tQ\u0015'\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&2\u0003\u0015\u0019\u0017-^:f)\r\u0001&k\u0015\t\u0003#\u000ei\u0011!\u0001\u0005\u0006\u000b\u001a\u0001\rA\u0012\u0005\u0006\u001d\u001a\u0001\r!O\u0001\u0004\u000b>3\u0005C\u0001\u0019W\u0013\t9\u0016G\u0001\u0003DQ\u0006\u0014\u0018!B9v_R,Gc\u0001$[9\")1\f\u0003a\u0001\r\u0006\t1\u000fC\u0003^\u0011\u0001\u0007a,A\nbY^\f\u0017p]#tG\u0006\u0004X-\u00168jG>$W\r\u0005\u00021?&\u0011\u0001-\r\u0002\b\u0005>|G.Z1o+\t\u0011W\r\u0006\u0003dgR4\bC\u00013f\u0019\u0001!QAZ\u0005C\u0002\u001d\u0014\u0011\u0001V\t\u0003Q.\u0004\"\u0001M5\n\u0005)\f$a\u0002(pi\"Lgn\u001a\t\u0003YFl\u0011!\u001c\u0006\u0003]>\fA\u0001\\1oO*\t\u0001/\u0001\u0003kCZ\f\u0017B\u0001:n\u0005)\t\u0005\u000f]3oI\u0006\u0014G.\u001a\u0005\u00067&\u0001\rA\u0012\u0005\u0006k&\u0001\raY\u0001\tCB\u0004XM\u001c3fe\")Q,\u0003a\u0001=\u00069QO\\9v_R,GC\u0001$z\u0011\u0015Q(\u00021\u0001G\u0003\u0019\u0019HO]5oOR\u0011a\t \u0005\u0006{.\u0001\rA`\u0001\u0004EV4\u0007CA)\r\u0005\u0019\u0011UO\u001a4feN\u0011AbL\u0001\u0003S:\u0004B!a\u0002\u0002\u000e5\u0011\u0011\u0011\u0002\u0006\u0004\u0003\u0017y\u0017AA5p\u0013\u0011\ty!!\u0003\u0003\rI+\u0017\rZ3s\u0003I\u0019Gn\\:f\u0003V$x.\\1uS\u000e\fG\u000e\\=\u0015\u000by\f)\"a\u0006\t\u000f\u0005\rq\u00021\u0001\u0002\u0006!1\u0011\u0011C\bA\u0002y\u000baa\u001c4gg\u0016$\bc\u0001\u0019\u0002\u001e%\u0019\u0011qD\u0019\u0003\u0007%sG/A\u0004dkJl\u0015M]6\u0002\u001d\r,(/T1sWN+w-\\3oi\u0006aQm\u001c4Jg\u001a\u000b\u0017\u000e\\;sKV\ta,\u0001\tf_\u001aL5OR1jYV\u0014Xm\u0018\u0013fcR!\u0011QFA\u001a!\r\u0001\u0014qF\u0005\u0004\u0003c\t$\u0001B+oSRD\u0001\"!\u000e\u0015\u0003\u0003\u0005\rAX\u0001\u0004q\u0012\n\u0014!D3pM&\u001bh)Y5mkJ,\u0007%\u0001\u0005tK\u001elWM\u001c;t!\u0015Q\u0014QHA!\u0013\r\ty\u0004\u0012\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u00071\n\u0019%C\u0002\u0002F\u0019\u0012qaU3h[\u0016tG/A\u0004tK\u001elWM\u001c;\u0011\tA\nY%V\u0005\u0004\u0003\u001b\n$!B!se\u0006L\u0018aA2ve\u0006i1-\u001e:TK\u001elWM\u001c;JIb\fA!\\1sWR\u0011\u0011QF\u0001\u0005E\u0006\u001c7.\u0001\u0003oKb$H#A+\u0002\u0013M,(m\u001d;sS:<W#\u0001$\u0002\t9,\u0017M]\u0001\be\u0016dW-Y:f\u00039\tW\u000f^8nCRL7m\u00117pg\u0016\fAA]3bIR\u0011\u00111D\u0001\u0013I\u00164\u0017-\u001e7u'\u0016<W.\u001a8u'&TX-\u0006\u0002\u0002\u001c\u0005a!I]8lK:$u.\u001e2mKB!\u00111OA=\u001b\t\t)HC\u0002\u0002xE\nA!\\1uQ&!\u00111PA;\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\\\u0001\fa\u0006\u00148/\u001a#pk\ndW\r\u0006\u0003\u0002\u0002\u0006\u001d\u0005c\u0001\u0019\u0002\u0004&\u0019\u0011QQ\u0019\u0003\r\u0011{WO\u00197f\u0011\u0015YF\u00051\u0001G\u0001"
)
public final class ParserUtil {
   public static String unquote(final String string) {
      return ParserUtil$.MODULE$.unquote(string);
   }

   public static String quote(final String s, final boolean alwaysEscapeUnicode) {
      return ParserUtil$.MODULE$.quote(s, alwaysEscapeUnicode);
   }

   public static class ParseException extends Exception {
      public ParseException(final String message, final Exception cause) {
         super(message, cause);
      }
   }

   public static class Buffer {
      private final java.io.Reader in;
      private final boolean closeAutomatically;
      private int offset;
      private int curMark;
      private int curMarkSegment;
      private boolean eofIsFailure;
      private Vector segments;
      private char[] segment;
      private int cur;
      private int curSegmentIdx;

      public boolean eofIsFailure() {
         return this.eofIsFailure;
      }

      public void eofIsFailure_$eq(final boolean x$1) {
         this.eofIsFailure = x$1;
      }

      public void mark() {
         this.curMark = this.cur;
         this.curMarkSegment = this.curSegmentIdx;
      }

      public void back() {
         --this.cur;
      }

      public char next() {
         char var10000;
         if (this.cur == this.offset && this.read() < 0) {
            if (this.eofIsFailure()) {
               throw new ParseException("unexpected eof", (Exception)null);
            }

            var10000 = ParserUtil$.org$json4s$ParserUtil$$EOF;
         } else {
            char c = this.segment[this.cur];
            ++this.cur;
            var10000 = c;
         }

         return var10000;
      }

      public String substring() {
         String var10000;
         if (this.curSegmentIdx == this.curMarkSegment) {
            var10000 = new String(this.segment, this.curMark, this.cur - this.curMark - 1);
         } else {
            List parts = .MODULE$.Nil();

            for(int i = this.curSegmentIdx; i >= this.curMarkSegment; --i) {
               char[] s = ((Segment)this.segments.apply(i)).seg();
               int start = i == this.curMarkSegment ? this.curMark : 0;
               int end = i == this.curSegmentIdx ? this.cur : s.length + 1;
               Tuple3 var6 = new Tuple3(BoxesRunTime.boxToInteger(start), BoxesRunTime.boxToInteger(end), s);
               parts = parts.$colon$colon(var6);
            }

            int len = BoxesRunTime.unboxToInt(parts.map((p) -> BoxesRunTime.boxToInteger($anonfun$substring$1(p))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
            char[] chars = new char[len];
            this.loop$1(parts, 0, chars);
            var10000 = new String(chars);
         }

         return var10000;
      }

      public String near() {
         return new String(this.segment, scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(this.cur - 20), 0), scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(20), this.cur));
      }

      public void release() {
         this.segments.foreach((s) -> {
            $anonfun$release$1(s);
            return BoxedUnit.UNIT;
         });
      }

      public void automaticClose() {
         if (this.closeAutomatically) {
            this.in.close();
         }

      }

      private int read() {
         if (this.offset >= this.segment.length) {
            Segment newSegment = Segments$.MODULE$.apply();
            this.offset = 0;
            this.segment = newSegment.seg();
            this.segments = (Vector)this.segments.$colon$plus(newSegment);
            this.curSegmentIdx = this.segments.length() - 1;
         }

         int length = this.in.read(this.segment, this.offset, this.segment.length - this.offset);
         this.cur = this.offset;
         this.offset += length;
         return length;
      }

      // $FF: synthetic method
      public static final int $anonfun$substring$1(final Tuple3 p) {
         return BoxesRunTime.unboxToInt(p._2()) - BoxesRunTime.unboxToInt(p._1()) - 1;
      }

      private final void loop$1(final List xs, final int pos, final char[] chars$1) {
         while(true) {
            if (xs instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)xs;
               Tuple3 var8 = (Tuple3)var7.head();
               List tail = var7.next$access$1();
               if (var8 != null) {
                  int start = BoxesRunTime.unboxToInt(var8._1());
                  int end = BoxesRunTime.unboxToInt(var8._2());
                  char[] b = (char[])var8._3();
                  int partLen = end - start - 1;
                  System.arraycopy(b, start, chars$1, pos, partLen);
                  pos += partLen;
                  xs = tail;
                  continue;
               }
            }

            BoxedUnit var5 = BoxedUnit.UNIT;
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$release$1(final Segment s) {
         Segments$.MODULE$.release(s);
      }

      public Buffer(final java.io.Reader in, final boolean closeAutomatically) {
         this.in = in;
         this.closeAutomatically = closeAutomatically;
         this.offset = 0;
         this.curMark = -1;
         this.curMarkSegment = -1;
         this.eofIsFailure = false;
         this.segments = (Vector).MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Segment[]{Segments$.MODULE$.apply()}));
         this.segment = ((Segment)this.segments.head()).seg();
         this.cur = 0;
         this.curSegmentIdx = 0;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
