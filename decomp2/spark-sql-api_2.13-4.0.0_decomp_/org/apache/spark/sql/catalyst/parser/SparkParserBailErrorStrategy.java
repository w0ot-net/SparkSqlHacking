package org.apache.spark.sql.catalyst.parser;

import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4A!\u0002\u0004\u0001'!)\u0001\u0004\u0001C\u00013!)1\u0004\u0001C!9!)A\u0007\u0001C!k!)A\r\u0001C!K\na2\u000b]1sWB\u000b'o]3s\u0005\u0006LG.\u0012:s_J\u001cFO]1uK\u001eL(BA\u0004\t\u0003\u0019\u0001\u0018M]:fe*\u0011\u0011BC\u0001\tG\u0006$\u0018\r\\=ti*\u00111\u0002D\u0001\u0004gFd'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0006\t\u0003+Yi\u0011AB\u0005\u0003/\u0019\u0011\u0001d\u00159be.\u0004\u0016M]:fe\u0016\u0013(o\u001c:TiJ\fG/Z4z\u0003\u0019a\u0014N\\5u}Q\t!\u0004\u0005\u0002\u0016\u0001\u00059!/Z2pm\u0016\u0014HcA\u000f$_A\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t!QK\\5u\u0011\u0015!#\u00011\u0001&\u0003)\u0011XmY8h]&TXM\u001d\t\u0003M5j\u0011a\n\u0006\u0003Q%\nqA];oi&lWM\u0003\u0002+W\u0005\u0011a\u000f\u000e\u0006\u0003YA\tQ!\u00198uYJL!AL\u0014\u0003\rA\u000b'o]3s\u0011\u0015\u0001$\u00011\u00012\u0003\u0005)\u0007C\u0001\u00143\u0013\t\u0019tE\u0001\u000bSK\u000e|wM\\5uS>tW\t_2faRLwN\\\u0001\u000ee\u0016\u001cwN^3s\u0013:d\u0017N\\3\u0015\u0005YJ\u0004C\u0001\u00148\u0013\tAtEA\u0003U_.,g\u000eC\u0003%\u0007\u0001\u0007Q\u0005K\u0002\u0004wy\u00022A\b\u001f2\u0013\titD\u0001\u0004uQJ|wo]\u0019\u0005=}R5\r\u0005\u0002A\u000f:\u0011\u0011)\u0012\t\u0003\u0005~i\u0011a\u0011\u0006\u0003\tJ\ta\u0001\u0010:p_Rt\u0014B\u0001$ \u0003\u0019\u0001&/\u001a3fM&\u0011\u0001*\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019{\u0012'B\u0012L\u001fz\u0003VC\u0001'N+\u0005yD!\u0002(\u0013\u0005\u0004\u0019&!\u0001+\n\u0005A\u000b\u0016a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013G\u0003\u0002S?\u00051A\u000f\u001b:poN\f\"\u0001V,\u0011\u0005y)\u0016B\u0001, \u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001W.\u000f\u0005yI\u0016B\u0001. \u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001X/\u0003\u0013QC'o\\<bE2,'B\u0001. c\u0015\u0019s\fY1S\u001d\tq\u0002-\u0003\u0002S?E\"!EH\u0010c\u0005\u0015\u00198-\u00197bc\t1\u0013'\u0001\u0003ts:\u001cGCA\u000fg\u0011\u0015!C\u00011\u0001&\u0001"
)
public class SparkParserBailErrorStrategy extends SparkParserErrorStrategy {
   public void recover(final Parser recognizer, final RecognitionException e) {
      for(ParserRuleContext context = recognizer.getContext(); context != null; context = context.getParent()) {
         context.exception = e;
      }

      throw new ParseCancellationException(e);
   }

   public Token recoverInline(final Parser recognizer) throws RecognitionException {
      InputMismatchException e = new InputMismatchException(recognizer);

      for(ParserRuleContext context = recognizer.getContext(); context != null; context = context.getParent()) {
         context.exception = e;
      }

      throw new ParseCancellationException(e);
   }

   public void sync(final Parser recognizer) {
   }
}
