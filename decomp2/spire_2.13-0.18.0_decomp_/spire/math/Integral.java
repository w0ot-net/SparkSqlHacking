package spire.math;

import algebra.ring.EuclideanRing;
import scala.reflect.ScalaSignature;
import spire.algebra.IsReal;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=ca\u0002\n\u0014!\u0003\r\n\u0001G\u0004\u0006-NA\ta\u0016\u0004\u0006%MA\t\u0001\u0017\u0005\u0006I\n!\t!\u001a\u0005\bM\n\u0011\r\u0011b\u0002h\u0011\u0019a'\u0001)A\u0007Q\"9QN\u0001b\u0001\n\u000fq\u0007BB:\u0003A\u00035q\u000eC\u0004u\u0005\t\u0007IqA;\t\ri\u0014\u0001\u0015!\u0004w\u0011\u001dY(A1A\u0005\bqDq!a\u0001\u0003A\u00035Q\u0010C\u0005\u0002\u0006\t\u0011\r\u0011b\u0002\u0002\b!A\u0011q\u0003\u0002!\u0002\u001b\tI\u0001C\u0005\u0002\u001a\t\u0011\r\u0011b\u0002\u0002\u001c!A\u0011Q\u0005\u0002!\u0002\u001b\ti\u0002C\u0004\u0002(\t!)!!\u000b\t\u0013\u0005}\"!!A\u0005\n\u0005\u0005#\u0001C%oi\u0016<'/\u00197\u000b\u0005Q)\u0012\u0001B7bi\"T\u0011AF\u0001\u0006gBL'/Z\u0002\u0001+\tI\"gE\u0004\u00015\u0001BEjT*\u0011\u0005mqR\"\u0001\u000f\u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0003\u0007\u0005s\u0017\u0010E\u0002\"[Ar!A\t\u0016\u000f\u0005\rBcB\u0001\u0013(\u001b\u0005)#B\u0001\u0014\u0018\u0003\u0019a$o\\8u}%\ta#\u0003\u0002*+\u00059\u0011\r\\4fEJ\f\u0017BA\u0016-\u0003\u001d\u0001\u0018mY6bO\u0016T!!K\u000b\n\u00059z#!D#vG2LG-Z1o%&twM\u0003\u0002,YA\u0011\u0011G\r\u0007\u0001\t%\u0019\u0004\u0001)A\u0001\u0002\u000b\u0007AGA\u0001B#\t)$\u0004\u0005\u0002\u001cm%\u0011q\u0007\b\u0002\b\u001d>$\b.\u001b8hQ\u0011\u0011\u0014\bP\"\u0011\u0005mQ\u0014BA\u001e\u001d\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rjd\bQ \u000f\u0005mq\u0014BA \u001d\u0003\rIe\u000e^\u0019\u0005I\u0005\u0013UD\u0004\u0002%\u0005&\tQ$M\u0003$\t\u0016;eI\u0004\u0002\u001c\u000b&\u0011a\tH\u0001\u0005\u0019>tw-\r\u0003%\u0003\nk\u0002cA%Ka5\t1#\u0003\u0002L'\ty1i\u001c8wKJ$\u0018M\u00197f\rJ|W\u000eE\u0002J\u001bBJ!AT\n\u0003\u001b\r{gN^3si\u0006\u0014G.\u001a+p!\r\u0001\u0016\u000bM\u0007\u0002Y%\u0011!\u000b\f\u0002\u0007\u0013N\u0014V-\u00197\u0011\u0007\u0005\"\u0006'\u0003\u0002V_\t)qJ\u001d3fe\u0006A\u0011J\u001c;fOJ\fG\u000e\u0005\u0002J\u0005M\u0019!!\u0017/\u0011\u0005mQ\u0016BA.\u001d\u0005\u0019\te.\u001f*fMB\u0011QLY\u0007\u0002=*\u0011q\fY\u0001\u0003S>T\u0011!Y\u0001\u0005U\u00064\u0018-\u0003\u0002d=\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012aV\u0001\u000f\u0005f$X-S:J]R,wM]1m+\u0005A\u0007cA%\u0001SB\u00111D[\u0005\u0003Wr\u0011AAQ=uK\u0006y!)\u001f;f\u0013NLe\u000e^3he\u0006d\u0007%A\bTQ>\u0014H/S:J]R,wM]1m+\u0005y\u0007cA%\u0001aB\u00111$]\u0005\u0003er\u0011Qa\u00155peR\f\u0001c\u00155peRL5/\u00138uK\u001e\u0014\u0018\r\u001c\u0011\u0002\u001b%sG/S:J]R,wM]1m+\u00051\bcA%\u0001oB\u00111\u0004_\u0005\u0003sr\u00111!\u00138u\u00039Ie\u000e^%t\u0013:$Xm\u001a:bY\u0002\na\u0002T8oO&\u001b\u0018J\u001c;fOJ\fG.F\u0001~!\rI\u0005A \t\u00037}L1!!\u0001\u001d\u0005\u0011auN\\4\u0002\u001f1{gnZ%t\u0013:$Xm\u001a:bY\u0002\n\u0001CQ5h\u0013:$\u0018j]%oi\u0016<'/\u00197\u0016\u0005\u0005%\u0001\u0003B%\u0001\u0003\u0017\u0001B!!\u0004\u0002\u00129\u0019\u0011)a\u0004\n\u0005-b\u0012\u0002BA\n\u0003+\u0011aAQ5h\u0013:$(BA\u0016\u001d\u0003E\u0011\u0015nZ%oi&\u001b\u0018J\u001c;fOJ\fG\u000eI\u0001\u0013'\u00064W\rT8oO&\u001b\u0018J\u001c;fOJ\fG.\u0006\u0002\u0002\u001eA!\u0011\nAA\u0010!\rI\u0015\u0011E\u0005\u0004\u0003G\u0019\"\u0001C*bM\u0016duN\\4\u0002'M\u000bg-\u001a'p]\u001eL5/\u00138uK\u001e\u0014\u0018\r\u001c\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005-\u0012\u0011\u0007\u000b\u0005\u0003[\t\u0019\u0004\u0005\u0003J\u0001\u0005=\u0002cA\u0019\u00022\u0011)1\u0007\u0005b\u0001i!9\u0011Q\u0007\tA\u0004\u00055\u0012AA3wQ\r\u0001\u0012\u0011\b\t\u00047\u0005m\u0012bAA\u001f9\t1\u0011N\u001c7j]\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0011\u0011\t\u0005\u0015\u00131J\u0007\u0003\u0003\u000fR1!!\u0013a\u0003\u0011a\u0017M\\4\n\t\u00055\u0013q\t\u0002\u0007\u001f\nTWm\u0019;"
)
public interface Integral extends EuclideanRing, ConvertableFrom, ConvertableTo, IsReal {
   static Integral apply(final Integral ev) {
      return Integral$.MODULE$.apply(ev);
   }

   static Integral SafeLongIsIntegral() {
      return Integral$.MODULE$.SafeLongIsIntegral();
   }

   static Integral BigIntIsIntegral() {
      return Integral$.MODULE$.BigIntIsIntegral();
   }

   static Integral LongIsIntegral() {
      return Integral$.MODULE$.LongIsIntegral();
   }

   static Integral IntIsIntegral() {
      return Integral$.MODULE$.IntIsIntegral();
   }

   static Integral ShortIsIntegral() {
      return Integral$.MODULE$.ShortIsIntegral();
   }

   static Integral ByteIsIntegral() {
      return Integral$.MODULE$.ByteIsIntegral();
   }
}
