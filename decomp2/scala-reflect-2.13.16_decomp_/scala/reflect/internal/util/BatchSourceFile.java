package scala.reflect.internal.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Predef.;
import scala.collection.ArrayOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ManifestFactory;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Chars;
import scala.reflect.internal.Chars$;
import scala.reflect.io.AbstractFile;
import scala.reflect.io.VirtualFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar;
import scala.runtime.RichInt;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ub\u0001B\r\u001b\u0001\rB\u0001\u0002\u000b\u0001\u0003\u0006\u0004%\t!\u000b\u0005\ta\u0001\u0011\t\u0011)A\u0005U!A\u0011\u0007\u0001B\u0001B\u0003%!\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003:\u0001\u0011\u0005a\bC\u0003:\u0001\u0011\u0005\u0011\tC\u0003:\u0001\u0011\u0005\u0001\fC\u0004\\\u0001\t\u0007I\u0011\u0001/\t\ru\u0003\u0001\u0015!\u00033\u0011\u0015q\u0006\u0001\"\u0001`\u0011\u0015\u0019\u0007\u0001\"\u0001`\u0011\u0015!\u0007\u0001\"\u0001`\u0011\u0015)\u0007\u0001\"\u0001g\u0011\u0015Q\u0007\u0001\"\u0003l\u0011\u0015!\b\u0001\"\u0001v\u0011\u00159\b\u0001\"\u0001y\u0011\u0015Q\b\u0001\"\u0001|\u0011!i\b\u0001#b\u0001\n\u0013q\bbBA\u0001\u0001\u0011\u0005\u00111\u0001\u0005\b\u0003\u0013\u0001\u0001\u0015)\u0003a\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!a\u0005\u0001\t\u0003\n)\u0002C\u0004\u0002$\u0001!\t%!\n\t\u000f\u0005E\u0002\u0001\"\u0011\u00024\ty!)\u0019;dQN{WO]2f\r&dWM\u0003\u0002\u001c9\u0005!Q\u000f^5m\u0015\tib$\u0001\u0005j]R,'O\\1m\u0015\ty\u0002%A\u0004sK\u001adWm\u0019;\u000b\u0003\u0005\nQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001IA\u0011QEJ\u0007\u00025%\u0011qE\u0007\u0002\u000b'>,(oY3GS2,\u0017\u0001\u00024jY\u0016,\u0012A\u000b\t\u0003W9j\u0011\u0001\f\u0006\u0003[y\t!![8\n\u0005=b#\u0001D!cgR\u0014\u0018m\u0019;GS2,\u0017!\u00024jY\u0016\u0004\u0013\u0001C2p]R,g\u000e\u001e\u0019\u0011\u0007M\"d'D\u0001!\u0013\t)\u0004EA\u0003BeJ\f\u0017\u0010\u0005\u00024o%\u0011\u0001\b\t\u0002\u0005\u0007\"\f'/\u0001\u0004=S:LGO\u0010\u000b\u0004wqj\u0004CA\u0013\u0001\u0011\u0015AC\u00011\u0001+\u0011\u0015\tD\u00011\u00013)\tYt\bC\u0003A\u000b\u0001\u0007!&A\u0003`M&dW\rF\u0002<\u0005>CQa\u0011\u0004A\u0002\u0011\u000b!b]8ve\u000e,g*Y7f!\t)EJ\u0004\u0002G\u0015B\u0011q\tI\u0007\u0002\u0011*\u0011\u0011JI\u0001\u0007yI|w\u000e\u001e \n\u0005-\u0003\u0013A\u0002)sK\u0012,g-\u0003\u0002N\u001d\n11\u000b\u001e:j]\u001eT!a\u0013\u0011\t\u000bA3\u0001\u0019A)\u0002\u0005\r\u001c\bc\u0001*Vm9\u00111gU\u0005\u0003)\u0002\nq\u0001]1dW\u0006<W-\u0003\u0002W/\n\u00191+Z9\u000b\u0005Q\u0003CcA\u001eZ5\")\u0001f\u0002a\u0001U!)\u0001k\u0002a\u0001#\u000691m\u001c8uK:$X#\u0001\u001a\u0002\u0011\r|g\u000e^3oi\u0002\na\u0001\\3oORDW#\u00011\u0011\u0005M\n\u0017B\u00012!\u0005\rIe\u000e^\u0001\nY&tWmQ8v]R\fQa\u001d;beR\fq\"[:TK247i\u001c8uC&tW\rZ\u000b\u0002OB\u00111\u0007[\u0005\u0003S\u0002\u0012qAQ8pY\u0016\fg.A\u0006dQ\u0006\u0014\u0018\t^%t\u000b>cEC\u00017s)\t9W\u000eC\u0003o\u001d\u0001\u0007q.A\u0001q!\u0011\u0019\u0004ON4\n\u0005E\u0004#!\u0003$v]\u000e$\u0018n\u001c82\u0011\u0015\u0019h\u00021\u0001a\u0003\rIG\r_\u0001\fSNd\u0015N\\3Ce\u0016\f7\u000e\u0006\u0002hm\")1o\u0004a\u0001A\u0006Y\u0011n]#oI>3G*\u001b8f)\t9\u0017\u0010C\u0003t!\u0001\u0007\u0001-A\u0007jg\u0006#XI\u001c3PM2Kg.\u001a\u000b\u0003OrDQa]\tA\u0002\u0001\f1\u0002\\5oK&sG-[2fgV\tq\u0010E\u00024i\u0001\fA\u0002\\5oKR{wJ\u001a4tKR$2\u0001YA\u0003\u0011\u0019\t9a\u0005a\u0001A\u0006)\u0011N\u001c3fq\u0006AA.Y:u\u0019&tW-\u0001\u0007pM\u001a\u001cX\r\u001e+p\u0019&tW\rF\u0002a\u0003\u001fAa!!\u0005\u0016\u0001\u0004\u0001\u0017AB8gMN,G/A\u0003mS:,7\u000f\u0006\u0004\u0002\u0018\u0005u\u0011q\u0004\t\u0005%\u0006eA)C\u0002\u0002\u001c]\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0005\bIZ\u0001\n\u00111\u0001a\u0011!\t\tC\u0006I\u0001\u0002\u0004\u0001\u0017aA3oI\u00061Q-];bYN$2aZA\u0014\u0011\u001d\tIc\u0006a\u0001\u0003W\tA\u0001\u001e5biB\u00191'!\f\n\u0007\u0005=\u0002EA\u0002B]f\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002A\u0002"
)
public class BatchSourceFile extends SourceFile {
   private int[] lineIndices;
   private final AbstractFile file;
   private final char[] content;
   private int lastLine;
   private volatile boolean bitmap$0;

   public AbstractFile file() {
      return this.file;
   }

   public char[] content() {
      return this.content;
   }

   public int length() {
      return this.content().length;
   }

   public int lineCount() {
      return this.lineIndices().length - 1;
   }

   public int start() {
      return 0;
   }

   public boolean isSelfContained() {
      return true;
   }

   private boolean charAtIsEOL(final int idx, final Function1 p) {
      return idx < this.length() && this.notCRLF0$1(idx) && BoxesRunTime.unboxToBoolean(p.apply(this.content()[idx]));
   }

   public boolean isLineBreak(final int idx) {
      if (idx < this.length() && this.notCRLF0$1(idx)) {
         char charAtIsEOL_boxToCharacter_c = this.content()[idx];
         if (Chars.isLineBreakChar$(Chars$.MODULE$, charAtIsEOL_boxToCharacter_c)) {
            return true;
         }
      }

      return false;
   }

   public boolean isEndOfLine(final int idx) {
      ArraySeq.ofChar var10000 = .MODULE$.wrapCharArray(this.content());
      if (var10000 == null) {
         throw null;
      } else {
         return SeqOps.isDefinedAt$(var10000, idx) && scala.PartialFunction..MODULE$.cond(this.content()[idx], new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Object applyOrElse(final char x1, final Function1 default) {
               return ('\r' == x1 ? true : '\n' == x1) ? true : default.apply(x1);
            }

            public final boolean isDefinedAt(final char x1) {
               return '\r' == x1 ? true : '\n' == x1;
            }
         });
      }
   }

   public boolean isAtEndOfLine(final int idx) {
      return idx < this.length() && this.notCRLF0$1(idx) && $anonfun$isAtEndOfLine$1(this.content()[idx]);
   }

   private int[] lineIndices$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.lineIndices = this.calculateLineIndices$1(this.content());
            this.bitmap$0 = true;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.lineIndices;
   }

   private int[] lineIndices() {
      return !this.bitmap$0 ? this.lineIndices$lzycompute() : this.lineIndices;
   }

   public int lineToOffset(final int index) {
      int var2 = this.lineIndices()[index];
      switch (var2) {
         default:
            if (var2 < this.length()) {
               return var2;
            } else {
               throw new IndexOutOfBoundsException(Integer.toString(index));
            }
      }
   }

   public int offsetToLine(final int offset) {
      int[] lines = this.lineIndices();
      if (lines.length != 0 && offset >= BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.head$extension(lines)) && offset < BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.last$extension(lines))) {
         this.lastLine = this.findLine$1(0, lines.length, this.lastLine, offset, lines);
         return this.lastLine;
      } else {
         throw new IndexOutOfBoundsException(Integer.toString(offset));
      }
   }

   public Iterator lines(final int start, final int end) {
      RichInt var10000 = scala.runtime.RichInt..MODULE$;
      var10000 = scala.runtime.RichInt..MODULE$;
      int max$extension_that = 0;
      scala.math.package var8 = scala.math.package..MODULE$;
      int var3 = Math.max(start, max$extension_that);
      RichInt var9 = scala.runtime.RichInt..MODULE$;
      int min$extension_that = this.lineCount();
      scala.math.package var10 = scala.math.package..MODULE$;
      int until$extension_end = Math.min(end, min$extension_that);
      Range var11 = scala.collection.immutable.Range..MODULE$;
      return (new Range.Exclusive(var3, until$extension_end, 1)).iterator().map((ix) -> $anonfun$lines$1(this, BoxesRunTime.unboxToInt(ix)));
   }

   public boolean equals(final Object that) {
      if (that instanceof BatchSourceFile) {
         BatchSourceFile var2 = (BatchSourceFile)that;
         if (!this.file().isVirtual() && !var2.file().isVirtual()) {
            String var10000 = this.file().path();
            String var3 = var2.file().path();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            if (this.start() == var2.start()) {
               return true;
            }

            return false;
         }
      }

      return super.equals(that);
   }

   public int hashCode() {
      return !this.file().isVirtual() ? Statics.anyHash(this.file().path()) + this.start() : super.hashCode();
   }

   private final boolean notCRLF0$1(final int idx$1) {
      if (this.content()[idx$1] == '\r') {
         ArraySeq.ofChar var10000 = .MODULE$.wrapCharArray(this.content());
         int isDefinedAt_idx = idx$1 + 1;
         if (var10000 == null) {
            throw null;
         }

         if (SeqOps.isDefinedAt$(var10000, isDefinedAt_idx) && this.content()[idx$1 + 1] == '\n') {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isLineBreak$1(final char c) {
      return Chars$.MODULE$.isLineBreakChar(c);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isAtEndOfLine$1(final char c) {
      return c == '\r' || c == '\n';
   }

   private final void fillLines$1(final int i, final char[] cs$2, final ArrayBuilder.ofInt buf$1) {
      for(; i < cs$2.length; ++i) {
         if (this.isAtEndOfLine(i)) {
            buf$1.addOne(i + 1);
         }
      }

   }

   private final int[] calculateLineIndices$1(final char[] cs) {
      ArrayBuilder.ofInt buf = new ArrayBuilder.ofInt();
      buf.sizeHint(cs.length / 30);
      buf.addOne(0);
      this.fillLines$1(0, cs, buf);
      buf.addOne(cs.length);
      return buf.result();
   }

   private final int findLine$1(final int lo, final int hi, final int mid, final int offset$2, final int[] lines$1) {
      while(true) {
         if (mid >= lo && hi >= mid) {
            if (offset$2 < lines$1[mid]) {
               int var10001 = mid - 1;
               mid = (lo + mid - 1) / 2;
               hi = var10001;
               lo = lo;
               continue;
            }

            if (offset$2 >= lines$1[mid + 1]) {
               int var10000 = mid + 1;
               mid = (mid + 1 + hi) / 2;
               hi = hi;
               lo = var10000;
               continue;
            }

            return mid;
         }

         return mid;
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$lines$1(final BatchSourceFile $this, final int ix) {
      int off = $this.lineIndices()[ix];
      RichInt var10000 = scala.runtime.RichInt..MODULE$;
      byte var4 = 0;
      int max$extension_that = $this.lineIndices()[ix + 1] - off - 1;
      scala.math.package var6 = scala.math.package..MODULE$;
      int len = Math.max(var4, max$extension_that);
      return String.valueOf($this.content(), off, len);
   }

   public BatchSourceFile(final AbstractFile file, final char[] content0) {
      char[] var9;
      label12: {
         this.file = file;
         super();
         if (content0.length != 0) {
            RichChar var10001 = scala.runtime.RichChar..MODULE$;
            if (Character.isWhitespace(BoxesRunTime.unboxToChar(scala.collection.ArrayOps..MODULE$.last$extension(content0)))) {
               var9 = content0;
               break label12;
            }
         }

         ArrayOps var7 = scala.collection.ArrayOps..MODULE$;
         Character var10002 = '\n';
         ManifestFactory.CharManifest $colon$plus$extension_evidence$11 = scala.reflect.ClassTag..MODULE$.Char();
         Object $colon$plus$extension_x = var10002;
         Object var8 = var7.appended$extension(content0, $colon$plus$extension_x, $colon$plus$extension_evidence$11);
         $colon$plus$extension_x = null;
         Object var6 = null;
         var9 = (char[])var8;
      }

      this.content = var9;
      this.lastLine = 0;
   }

   public BatchSourceFile(final AbstractFile _file) {
      this(_file, _file.toCharArray());
   }

   public BatchSourceFile(final String sourceName, final Seq cs) {
      this((AbstractFile)(new VirtualFile(sourceName)), (char[])((char[])cs.toArray(scala.reflect.ClassTag..MODULE$.Char())));
   }

   public BatchSourceFile(final AbstractFile file, final Seq cs) {
      this(file, (char[])cs.toArray(scala.reflect.ClassTag..MODULE$.Char()));
   }

   // $FF: synthetic method
   public static final Object $anonfun$isLineBreak$1$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$isLineBreak$1(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$isAtEndOfLine$1$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$isAtEndOfLine$1(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
