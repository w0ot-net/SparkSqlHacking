package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;

@ScalaSignature(
   bytes = "\u0006\u0005Y;QAD\b\t\u0002a1QAG\b\t\u0002mAQ\u0001I\u0001\u0005\u0002\u0005BQAI\u0001\u0005\u0002\rBQaL\u0001\u0005\u0002ABQaL\u0001\u0005\u0002Q3AAG\b\u0001e!AaG\u0002B\u0001B\u0003%1\u0007\u0003\u00058\r\t\u0005\t\u0015!\u0003*\u0011!AdA!b\u0001\n\u0003J\u0004\u0002\u0003\u001e\u0007\u0005\u0003\u0005\u000b\u0011\u0002\u0013\t\u000b\u00012A\u0011A\u001e\t\u000b}2A\u0011\t!\t\u000b\u00113A\u0011I#\u0002!M\u001b'/\u001b9u'>,(oY3GS2,'B\u0001\t\u0012\u0003\u0011)H/\u001b7\u000b\u0005I\u0019\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005Q)\u0012a\u0002:fM2,7\r\u001e\u0006\u0002-\u0005)1oY1mC\u000e\u0001\u0001CA\r\u0002\u001b\u0005y!\u0001E*de&\u0004HoU8ve\u000e,g)\u001b7f'\t\tA\u0004\u0005\u0002\u001e=5\tQ#\u0003\u0002 +\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\r\u0002\u0019!,\u0017\rZ3s\u0019\u0016tw\r\u001e5\u0015\u0005\u0011:\u0003CA\u000f&\u0013\t1SCA\u0002J]RDQ\u0001K\u0002A\u0002%\n!aY:\u0011\u0007uQC&\u0003\u0002,+\t)\u0011I\u001d:bsB\u0011Q$L\u0005\u0003]U\u0011Aa\u00115be\u0006)\u0011\r\u001d9msR\u0019\u0011gS*\u0011\u0005e11C\u0001\u00044!\tIB'\u0003\u00026\u001f\ty!)\u0019;dQN{WO]2f\r&dW-\u0001\u0006v]\u0012,'\u000f\\=j]\u001e\fqaY8oi\u0016tG/A\u0003ti\u0006\u0014H/F\u0001%\u0003\u0019\u0019H/\u0019:uAQ!\u0011\u0007P\u001f?\u0011\u001514\u00021\u00014\u0011\u001594\u00021\u0001*\u0011\u0015A4\u00021\u0001%\u0003=I7oU3mM\u000e{g\u000e^1j]\u0016$W#A!\u0011\u0005u\u0011\u0015BA\"\u0016\u0005\u001d\u0011un\u001c7fC:\f\u0001\u0004]8tSRLwN\\%o+2$\u0018.\\1uKN{WO]2f)\t1\u0015\n\u0005\u0002\u001a\u000f&\u0011\u0001j\u0004\u0002\t!>\u001c\u0018\u000e^5p]\")!*\u0004a\u0001\r\u0006\u0019\u0001o\\:\t\u000b1#\u0001\u0019A'\u0002\t\u0019LG.\u001a\t\u0003\u001dFk\u0011a\u0014\u0006\u0003!N\t!![8\n\u0005I{%\u0001D!cgR\u0014\u0018m\u0019;GS2,\u0007\"B\u001c\u0005\u0001\u0004ICCA\u0019V\u0011\u00151T\u00011\u00014\u0001"
)
public class ScriptSourceFile extends BatchSourceFile {
   private final BatchSourceFile underlying;
   private final int start;

   public static ScriptSourceFile apply(final BatchSourceFile underlying) {
      return ScriptSourceFile$.MODULE$.apply(underlying);
   }

   public static ScriptSourceFile apply(final AbstractFile file, final char[] content) {
      return ScriptSourceFile$.MODULE$.apply(file, content);
   }

   public static int headerLength(final char[] cs) {
      return ScriptSourceFile$.MODULE$.headerLength(cs);
   }

   public int start() {
      return this.start;
   }

   public boolean isSelfContained() {
      return false;
   }

   public Position positionInUltimateSource(final Position pos) {
      return !pos.isDefined() ? super.positionInUltimateSource(pos) : pos.withSource(this.underlying).withShift(this.start());
   }

   public ScriptSourceFile(final BatchSourceFile underlying, final char[] content, final int start) {
      super(underlying.file(), content);
      this.underlying = underlying;
      this.start = start;
   }
}
