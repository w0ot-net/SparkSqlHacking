package scala.util.parsing.input;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!<QAE\n\t\u0002q1QAH\n\t\u0002}AQ\u0001J\u0001\u0005\u0002\u0015BqAJ\u0001C\u0002\u0013\u0015q\u0005\u0003\u0004+\u0003\u0001\u0006i\u0001\u000b\u0004\u0005=M\u00011\u0006\u0003\u00053\u000b\t\u0015\r\u0011\"\u00114\u0011!aTA!A!\u0002\u0013!\u0004\u0002C\u001f\u0006\u0005\u000b\u0007I\u0011\t \t\u0011\t+!\u0011!Q\u0001\n}BQ\u0001J\u0003\u0005\u0002\rCQ\u0001J\u0003\u0005\u0002\u001dCQ!S\u0003\u0005\u0002)CQaS\u0003\u0005\u00021CQ!T\u0003\u0005\u00029CQAU\u0003\u0005\u0002MCQaV\u0003\u0005BaCQaW\u0003\u0005Bq\u000b!c\u00115beN+\u0017/^3oG\u0016\u0014V-\u00193fe*\u0011A#F\u0001\u0006S:\u0004X\u000f\u001e\u0006\u0003-]\tq\u0001]1sg&twM\u0003\u0002\u00193\u0005!Q\u000f^5m\u0015\u0005Q\u0012!B:dC2\f7\u0001\u0001\t\u0003;\u0005i\u0011a\u0005\u0002\u0013\u0007\"\f'oU3rk\u0016t7-\u001a*fC\u0012,'o\u0005\u0002\u0002AA\u0011\u0011EI\u0007\u00023%\u00111%\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005a\u0012!B#pM\u000eCW#\u0001\u0015\u0010\u0003%b\u0012AG\u0001\u0007\u000b>47\t\u001b\u0011\u0014\u0005\u0015a\u0003cA\u000f._%\u0011af\u0005\u0002\u0007%\u0016\fG-\u001a:\u0011\u0005\u0005\u0002\u0014BA\u0019\u001a\u0005\u0011\u0019\u0005.\u0019:\u0002\rM|WO]2f+\u0005!\u0004CA\u001b;\u001b\u00051$BA\u001c9\u0003\u0011a\u0017M\\4\u000b\u0003e\nAA[1wC&\u00111H\u000e\u0002\r\u0007\"\f'oU3rk\u0016t7-Z\u0001\bg>,(oY3!\u0003\u0019ygMZ:fiV\tq\b\u0005\u0002\"\u0001&\u0011\u0011)\u0007\u0002\u0004\u0013:$\u0018aB8gMN,G\u000f\t\u000b\u0004\t\u00163\u0005CA\u000f\u0006\u0011\u0015\u0011$\u00021\u00015\u0011\u0015i$\u00021\u0001@)\t!\u0005\nC\u00033\u0017\u0001\u0007A'A\u0003gSJ\u001cH/F\u00010\u0003\u0011\u0011Xm\u001d;\u0016\u0003\u0011\u000b1\u0001]8t+\u0005y\u0005CA\u000fQ\u0013\t\t6C\u0001\u0005Q_NLG/[8o\u0003\u0015\tG/\u00128e+\u0005!\u0006CA\u0011V\u0013\t1\u0016DA\u0004C_>dW-\u00198\u0002\t\u0011\u0014x\u000e\u001d\u000b\u0003\tfCQA\u0017\tA\u0002}\n\u0011A\\\u0001\ti>\u001cFO]5oOR\tQ\f\u0005\u0002_K:\u0011ql\u0019\t\u0003Afi\u0011!\u0019\u0006\u0003En\ta\u0001\u0010:p_Rt\u0014B\u00013\u001a\u0003\u0019\u0001&/\u001a3fM&\u0011am\u001a\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0011L\u0002"
)
public class CharSequenceReader extends Reader {
   private final CharSequence source;
   private final int offset;

   public static char EofCh() {
      return CharSequenceReader$.MODULE$.EofCh();
   }

   public CharSequence source() {
      return this.source;
   }

   public int offset() {
      return this.offset;
   }

   public char first() {
      return this.offset() < this.source().length() ? this.source().charAt(this.offset()) : '\u001a';
   }

   public CharSequenceReader rest() {
      return this.offset() < this.source().length() ? new CharSequenceReader(this.source(), this.offset() + 1) : this;
   }

   public Position pos() {
      return new OffsetPosition(this.source(), this.offset());
   }

   public boolean atEnd() {
      return this.offset() >= this.source().length();
   }

   public CharSequenceReader drop(final int n) {
      return new CharSequenceReader(this.source(), this.offset() + n);
   }

   public String toString() {
      String c = this.atEnd() ? "" : (new StringBuilder(7)).append("'").append(this.first()).append("', ...").toString();
      return (new StringBuilder(20)).append("CharSequenceReader(").append(c).append(")").toString();
   }

   public CharSequenceReader(final CharSequence source, final int offset) {
      this.source = source;
      this.offset = offset;
   }

   public CharSequenceReader(final CharSequence source) {
      this(source, 0);
   }
}
