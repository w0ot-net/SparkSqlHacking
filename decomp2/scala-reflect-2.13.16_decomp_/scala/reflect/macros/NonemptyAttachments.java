package scala.reflect.macros;

import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513AAC\u0006\u0007%!A\u0001\u0004\u0001BC\u0002\u0013\u0005\u0013\u0004\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003\u001b\u0011!9\u0003A!b\u0001\n\u0003B\u0003\u0002\u0003\u001b\u0001\u0005\u0003\u0005\u000b\u0011B\u0015\t\u000bU\u0002A\u0011\u0001\u001c\u0006\ti\u0002\u0001A\u0007\u0005\u0006w\u0001!\t\u0001\u0010\u0005\u0006\u0003\u0002!\tE\u0011\u0005\u0006\r\u0002!\te\u0012\u0002\u0014\u001d>tW-\u001c9us\u0006#H/Y2i[\u0016tGo\u001d\u0006\u0003\u00195\ta!\\1de>\u001c(B\u0001\b\u0010\u0003\u001d\u0011XM\u001a7fGRT\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0002\u0001+\t\u0019Bd\u0005\u0002\u0001)A\u0011QCF\u0007\u0002\u0017%\u0011qc\u0003\u0002\f\u0003R$\u0018m\u00195nK:$8/A\u0002q_N,\u0012A\u0007\t\u00037qa\u0001\u0001B\u0003\u001e\u0001\t\u0007aDA\u0001Q#\ty2\u0005\u0005\u0002!C5\tq\"\u0003\u0002#\u001f\t!a*\u001e7m!\t\u0001C%\u0003\u0002&\u001f\t\u0019\u0011I\\=\u0002\tA|7\u000fI\u0001\u0004C2dW#A\u0015\u0011\u0007)\n4E\u0004\u0002,_A\u0011AfD\u0007\u0002[)\u0011a&E\u0001\u0007yI|w\u000e\u001e \n\u0005Az\u0011A\u0002)sK\u0012,g-\u0003\u00023g\t\u00191+\u001a;\u000b\u0005Az\u0011\u0001B1mY\u0002\na\u0001P5oSRtDcA\u001c9sA\u0019Q\u0003\u0001\u000e\t\u000ba)\u0001\u0019\u0001\u000e\t\u000b\u001d*\u0001\u0019A\u0015\u0003\u0007A{7/A\u0004xSRD\u0007k\\:\u0015\u0005]j\u0004\"\u0002 \b\u0001\u0004y\u0014A\u00028foB{7\u000f\u0005\u0002A\r5\t\u0001!A\u0004jg\u0016k\u0007\u000f^=\u0016\u0003\r\u0003\"\u0001\t#\n\u0005\u0015{!a\u0002\"p_2,\u0017M\\\u0001\u0011G2|g.Z!ui\u0006\u001c\u0007.\\3oiN,\u0012\u0001\u0013\n\u0003\u0013R1AA\u0013\u0001\u0001\u0011\naAH]3gS:,W.\u001a8u}\u0015!!(\u0013\u0011\u001b\u0001"
)
public final class NonemptyAttachments extends Attachments {
   private final Object pos;
   private final Set all;

   public Object pos() {
      return this.pos;
   }

   public Set all() {
      return this.all;
   }

   public NonemptyAttachments withPos(final Object newPos) {
      return new NonemptyAttachments(newPos, this.all());
   }

   public boolean isEmpty() {
      return false;
   }

   public Attachments cloneAttachments() {
      return new NonemptyAttachments(this.pos(), this.all());
   }

   public NonemptyAttachments(final Object pos, final Set all) {
      this.pos = pos;
      this.all = all;
   }
}
