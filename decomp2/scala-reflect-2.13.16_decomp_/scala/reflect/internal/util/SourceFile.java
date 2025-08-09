package scala.reflect.internal.util;

import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.ArrayOps;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Chars;
import scala.reflect.internal.Chars$;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar;
import scala.runtime.RichChar.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc!B\r\u001b\u0003\u0003\u0019\u0003\"\u0002\u0015\u0001\t\u0003I\u0003\"\u0002\u0017\u0001\r\u0003i\u0003\"\u0002\u001b\u0001\r\u0003)\u0004\"\u0002\u001f\u0001\r\u0003i\u0004\"\u0002$\u0001\r\u00039\u0005\"B%\u0001\r\u0003Q\u0005\"B&\u0001\r\u0003a\u0005\"B'\u0001\r\u0003a\u0005\"\u0002(\u0001\t\u0003y\u0005\"B+\u0001\r\u00031\u0006\"\u0002-\u0001\r\u0003I\u0006\"\u0002/\u0001\t\u0003i\u0006\"B0\u0001\t\u0003\u0002\u0007\"\u00027\u0001\t\u0003i\u0007\"\u00028\u0001\t\u0003y\u0007\"B9\u0001\t\u000b\u0011\b\"B>\u0001\t\u0003a\bbBA\u0003\u0001\u0011\u0005\u0011q\u0001\u0005\b\u0003\u0017\u0001A\u0011AA\u0007\u0011%\t\t\u0003AI\u0001\n\u0003\t\u0019\u0003C\u0004\u00026\u00011\t!a\u000e\t\u0013\u00055\u0003!%A\u0005\u0002\u0005\r\u0002\"CA(\u0001E\u0005I\u0011AA\u0012\u0011\u0019\t\t\u0006\u0001C\u0003\u0015\nQ1k\\;sG\u00164\u0015\u000e\\3\u000b\u0005ma\u0012\u0001B;uS2T!!\b\u0010\u0002\u0011%tG/\u001a:oC2T!a\b\u0011\u0002\u000fI,g\r\\3di*\t\u0011%A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001!\u0003CA\u0013'\u001b\u0005\u0001\u0013BA\u0014!\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012A\u000b\t\u0003W\u0001i\u0011AG\u0001\bG>tG/\u001a8u+\u0005q\u0003cA\u00130c%\u0011\u0001\u0007\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003KIJ!a\r\u0011\u0003\t\rC\u0017M]\u0001\u0005M&dW-F\u00017!\t9$(D\u00019\u0015\tId$\u0001\u0002j_&\u00111\b\u000f\u0002\r\u0003\n\u001cHO]1di\u001aKG.Z\u0001\fSNd\u0015N\\3Ce\u0016\f7\u000e\u0006\u0002?\u0003B\u0011QeP\u0005\u0003\u0001\u0002\u0012qAQ8pY\u0016\fg\u000eC\u0003C\t\u0001\u00071)A\u0002jIb\u0004\"!\n#\n\u0005\u0015\u0003#aA%oi\u0006Y\u0011n]#oI>3G*\u001b8f)\tq\u0004\nC\u0003C\u000b\u0001\u00071)A\bjgN+GNZ\"p]R\f\u0017N\\3e+\u0005q\u0014A\u00027f]\u001e$\b.F\u0001D\u0003%a\u0017N\\3D_VtG/\u0001\u0005q_NLG/[8o)\t\u00016\u000b\u0005\u0002,#&\u0011!K\u0007\u0002\t!>\u001c\u0018\u000e^5p]\")A+\u0003a\u0001\u0007\u00061qN\u001a4tKR\fAb\u001c4gg\u0016$Hk\u001c'j]\u0016$\"aQ,\t\u000bQS\u0001\u0019A\"\u0002\u00191Lg.\u001a+p\u001f\u001a47/\u001a;\u0015\u0005\rS\u0006\"B.\f\u0001\u0004\u0019\u0015!B5oI\u0016D\u0018\u0001\u00079pg&$\u0018n\u001c8J]VcG/[7bi\u0016\u001cv.\u001e:dKR\u0011\u0001K\u0018\u0005\u0006\u001d2\u0001\r\u0001U\u0001\ti>\u001cFO]5oOR\t\u0011\r\u0005\u0002cS:\u00111m\u001a\t\u0003I\u0002j\u0011!\u001a\u0006\u0003M\n\na\u0001\u0010:p_Rt\u0014B\u00015!\u0003\u0019\u0001&/\u001a3fM&\u0011!n\u001b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!\u0004\u0013\u0001\u00029bi\",\u0012!Y\u0001\rY&tW\rV8TiJLgn\u001a\u000b\u0003CBDQaW\bA\u0002\r\u000bab]6ja^C\u0017\u000e^3ta\u0006\u001cW\r\u0006\u0002Dg\")A\u000b\u0005a\u0001\u0007\"\u0012\u0001#\u001e\t\u0003mfl\u0011a\u001e\u0006\u0003q\u0002\n!\"\u00198o_R\fG/[8o\u0013\tQxOA\u0004uC&d'/Z2\u0002\u0013%$WM\u001c;Ge>lGcA?\u0002\u0002A\u0019QE`1\n\u0005}\u0004#AB(qi&|g\u000e\u0003\u0004\u0002\u0004E\u0001\r\u0001U\u0001\u0004a>\u001c\u0018\u0001C:pkJ\u001cW-\u0011;\u0015\u0007\u0005\fI\u0001\u0003\u0004\u0002\u0004I\u0001\r\u0001U\u0001\u000bS:$W\r_,iKJ,GcB\"\u0002\u0010\u0005e\u0011Q\u0004\u0005\b\u0003#\u0019\u0002\u0019AA\n\u0003\u0005\u0001\b#B\u0013\u0002\u0016Er\u0014bAA\fA\tIa)\u001e8di&|g.\r\u0005\u0007\u00037\u0019\u0002\u0019A\"\u0002\u000bM$\u0018M\u001d;\t\u0011\u0005}1\u0003%AA\u0002\r\u000bAa\u001d;fa\u0006!\u0012N\u001c3fq^CWM]3%I\u00164\u0017-\u001e7uIM*\"!!\n+\u0007\r\u000b9c\u000b\u0002\u0002*A!\u00111FA\u0019\u001b\t\tiCC\u0002\u00020]\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\n\t\u0005M\u0012Q\u0006\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00027j]\u0016\u001cHCBA\u001d\u0003\u000f\nI\u0005E\u0003\u0002<\u0005\u0005\u0013MD\u0002&\u0003{I1!a\u0010!\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0011\u0002F\tA\u0011\n^3sCR|'OC\u0002\u0002@\u0001B\u0001\"a\u0007\u0016!\u0003\u0005\ra\u0011\u0005\t\u0003\u0017*\u0002\u0013!a\u0001\u0007\u0006\u0019QM\u001c3\u0002\u001f1Lg.Z:%I\u00164\u0017-\u001e7uIE\nq\u0002\\5oKN$C-\u001a4bk2$HEM\u0001\u0007SNT\u0015M^1"
)
public abstract class SourceFile {
   public abstract char[] content();

   public abstract AbstractFile file();

   public abstract boolean isLineBreak(final int idx);

   public abstract boolean isEndOfLine(final int idx);

   public abstract boolean isSelfContained();

   public abstract int length();

   public abstract int lineCount();

   public Position position(final int offset) {
      if (offset >= this.length()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$position$1(this, offset)).toString());
      } else {
         return Position$.MODULE$.offset(this, offset);
      }
   }

   public abstract int offsetToLine(final int offset);

   public abstract int lineToOffset(final int index);

   public Position positionInUltimateSource(final Position position) {
      return position;
   }

   public String toString() {
      return this.file().name();
   }

   public String path() {
      return this.file().path();
   }

   public String lineToString(final int index) {
      int start = this.lineToOffset(index);

      int end;
      for(end = start; end < this.length() && !this.isEndOfLine(end); ++end) {
      }

      return new String(this.content(), start, end - start);
   }

   public final int skipWhitespace(final int offset) {
      while(true) {
         RichChar var10000 = .MODULE$;
         if (!Character.isWhitespace(this.content()[offset])) {
            return offset;
         }

         ++offset;
      }
   }

   public Option identFrom(final Position pos) {
      Option var10000 = scala.Option..MODULE$;
      boolean var3;
      if (pos.isDefined()) {
         SourceFile var2 = pos.source();
         if (var2 != null) {
            if (var2.equals(this) && pos.point() != -1) {
               var3 = true;
               return (Option)(var3 ? new Some($anonfun$identFrom$1(this, pos)) : scala.None..MODULE$);
            }
         }
      }

      var3 = false;
      return (Option)(var3 ? new Some($anonfun$identFrom$1(this, pos)) : scala.None..MODULE$);
   }

   public String sourceAt(final Position pos) {
      return pos.start() < pos.end() ? new String((char[])scala.collection.ArrayOps..MODULE$.slice$extension(this.content(), pos.start(), pos.end())) : "";
   }

   public int indexWhere(final Function1 p, final int start, final int step) {
      for(int i = start; i >= 0 && i < this.content().length; i += step) {
         if (BoxesRunTime.unboxToBoolean(p.apply(this.content()[i]))) {
            return i;
         }
      }

      return -1;
   }

   public int indexWhere$default$3() {
      return 1;
   }

   public abstract Iterator lines(final int start, final int end);

   public int lines$default$1() {
      return 0;
   }

   public int lines$default$2() {
      return this.lineCount();
   }

   public final boolean isJava() {
      return this.file().name().endsWith(".java");
   }

   // $FF: synthetic method
   public static final String $anonfun$position$1(final SourceFile $this, final int offset$1) {
      return (new StringBuilder(6)).append($this.file().toString()).append(": ").append(offset$1).append(" >= ").append($this.length()).toString();
   }

   private static final boolean isOK$1(final char c) {
      return Chars.isIdentifierPart$(Chars$.MODULE$, (char)c) || Chars.isOperatorPart$(Chars$.MODULE$, (char)c);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$identFrom$2(final char c) {
      return isOK$1(c);
   }

   // $FF: synthetic method
   public static final String $anonfun$identFrom$1(final SourceFile $this, final Position pos$1) {
      String var10000 = new String;
      ArrayOps var10002 = scala.collection.ArrayOps..MODULE$;
      char[] charArrayOps_xs = (char[])scala.collection.ArrayOps..MODULE$.drop$extension($this.content(), pos$1.point());
      Object var8 = null;
      char[] takeWhile$extension_$this = charArrayOps_xs;
      ArrayOps takeWhile$extension_this = var10002;
      int takeWhile$extension_indexWhere$extension_i = 0;

      while(true) {
         if (takeWhile$extension_indexWhere$extension_i >= takeWhile$extension_$this.length) {
            var11 = -1;
            break;
         }

         if (!isOK$1(takeWhile$extension_$this[takeWhile$extension_indexWhere$extension_i])) {
            var11 = takeWhile$extension_indexWhere$extension_i;
            break;
         }

         ++takeWhile$extension_indexWhere$extension_i;
      }

      int takeWhile$extension_i = var11;
      int takeWhile$extension_hi = takeWhile$extension_i < 0 ? takeWhile$extension_$this.length : takeWhile$extension_i;
      Object var12 = takeWhile$extension_this.slice$extension(takeWhile$extension_$this, 0, takeWhile$extension_hi);
      Object var9 = null;
      Object var10 = null;
      var10000.<init>((char[])var12);
      return var10000;
   }

   // $FF: synthetic method
   public static final Object $anonfun$identFrom$2$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$identFrom$2(BoxesRunTime.unboxToChar(c)));
   }
}
