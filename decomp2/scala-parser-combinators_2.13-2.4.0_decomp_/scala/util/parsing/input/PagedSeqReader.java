package scala.util.parsing.input;

import scala.Predef;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005};QAE\n\t\u0002q1QAH\n\t\u0002}AQ\u0001J\u0001\u0005\u0002\u0015BqAJ\u0001C\u0002\u0013\u0015q\u0005\u0003\u0004+\u0003\u0001\u0006i\u0001\u000b\u0004\u0005=M\u00011\u0006\u0003\u00053\u000b\t\u0005\t\u0015!\u00034\u0011!1TA!b\u0001\n\u0003:\u0004\u0002C\u001e\u0006\u0005\u0003\u0005\u000b\u0011\u0002\u001d\t\u000b\u0011*A\u0011\u0001\u001f\t\u000f\u0001+!\u0019!C!\u0003\"1!*\u0002Q\u0001\n\tCQ\u0001J\u0003\u0005\u0002-CQ!T\u0003\u0005\u00029CQaT\u0003\u0005\u0002ACQ!U\u0003\u0005\u0002ICQAV\u0003\u0005\u0002]CQaW\u0003\u0005Bq\u000ba\u0002U1hK\u0012\u001cV-\u001d*fC\u0012,'O\u0003\u0002\u0015+\u0005)\u0011N\u001c9vi*\u0011acF\u0001\ba\u0006\u00148/\u001b8h\u0015\tA\u0012$\u0001\u0003vi&d'\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011Q$A\u0007\u0002'\tq\u0001+Y4fIN+\u0017OU3bI\u0016\u00148CA\u0001!!\t\t#%D\u0001\u001a\u0013\t\u0019\u0013D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003q\tQ!R8g\u0007\",\u0012\u0001K\b\u0002Sq\t!$\u0001\u0004F_\u001a\u001c\u0005\u000eI\n\u0003\u000b1\u00022!H\u00170\u0013\tq3C\u0001\u0004SK\u0006$WM\u001d\t\u0003CAJ!!M\r\u0003\t\rC\u0017M]\u0001\u0004g\u0016\f\bcA\u000f5_%\u0011Qg\u0005\u0002\t!\u0006<W\rZ*fc\u00061qN\u001a4tKR,\u0012\u0001\u000f\t\u0003CeJ!AO\r\u0003\u0007%sG/A\u0004pM\u001a\u001cX\r\u001e\u0011\u0015\u0007urt\b\u0005\u0002\u001e\u000b!)!'\u0003a\u0001g!)a'\u0003a\u0001q\u000511o\\;sG\u0016,\u0012A\u0011\t\u0003\u0007\"k\u0011\u0001\u0012\u0006\u0003\u000b\u001a\u000bA\u0001\\1oO*\tq)\u0001\u0003kCZ\f\u0017BA%E\u00051\u0019\u0005.\u0019:TKF,XM\\2f\u0003\u001d\u0019x.\u001e:dK\u0002\"\"!\u0010'\t\u000bIb\u0001\u0019A\u001a\u0002\u000b\u0019L'o\u001d;\u0016\u0003=\nAA]3tiV\tQ(A\u0002q_N,\u0012a\u0015\t\u0003;QK!!V\n\u0003\u0011A{7/\u001b;j_:\fQ!\u0019;F]\u0012,\u0012\u0001\u0017\t\u0003CeK!AW\r\u0003\u000f\t{w\u000e\\3b]\u0006!AM]8q)\tiT\fC\u0003_#\u0001\u0007\u0001(A\u0001o\u0001"
)
public class PagedSeqReader extends Reader {
   public final PagedSeq scala$util$parsing$input$PagedSeqReader$$seq;
   private final int offset;
   private final CharSequence source;

   public static char EofCh() {
      return PagedSeqReader$.MODULE$.EofCh();
   }

   public int offset() {
      return this.offset;
   }

   public CharSequence source() {
      return this.source;
   }

   public char first() {
      return this.scala$util$parsing$input$PagedSeqReader$$seq.isDefinedAt(this.offset()) ? BoxesRunTime.unboxToChar(this.scala$util$parsing$input$PagedSeqReader$$seq.apply(this.offset())) : '\u001a';
   }

   public PagedSeqReader rest() {
      return this.scala$util$parsing$input$PagedSeqReader$$seq.isDefinedAt(this.offset()) ? new PagedSeqReader() {
         private final CharSequence source = PagedSeqReader.this.source();

         public CharSequence source() {
            return this.source;
         }
      } : this;
   }

   public Position pos() {
      return new OffsetPosition(this.source(), this.offset());
   }

   public boolean atEnd() {
      return !this.scala$util$parsing$input$PagedSeqReader$$seq.isDefinedAt(this.offset());
   }

   public PagedSeqReader drop(final int n) {
      return new PagedSeqReader(n) {
         private final CharSequence source = PagedSeqReader.this.source();

         public CharSequence source() {
            return this.source;
         }
      };
   }

   public PagedSeqReader(final PagedSeq seq, final int offset) {
      this.scala$util$parsing$input$PagedSeqReader$$seq = seq;
      this.offset = offset;
      this.source = new Predef.SeqCharSequence(seq);
   }

   public PagedSeqReader(final PagedSeq seq) {
      this(seq, 0);
   }
}
