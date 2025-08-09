package scala.reflect.internal.util;

import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eh!\u0003\u001c8!\u0003\r\taN o\u0011\u0015!\u0005\u0001\"\u0001G\u0011\u0015Q\u0005A\"\u0001L\u0011\u0015y\u0005A\"\u0001L\u0011\u0015\u0001\u0006A\"\u0001R\u0011\u00151\u0006A\"\u0001X\u0011\u0015Y\u0006A\"\u0001X\u0011\u0015a\u0006A\"\u0001X\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u0015)\u0007\u0001\"\u0001L\u0011\u00151\u0007\u0001\"\u0001L\u0011\u00159\u0007\u0001\"\u0001L\u0011\u0015A\u0007\u0001\"\u0001j\u0011\u0015a\u0007\u0001\"\u0001n\u0011\u0015y\u0007\u0001\"\u0002q\u0011\u0015\u0019\b\u0001\"\u0001u\u0011\u00151\b\u0001\"\u0001x\u0011\u0015I\b\u0001\"\u0001{\u0011\u0015a\b\u0001\"\u0001~\u0011\u0019y\b\u0001\"\u0001\u0002\u0002!1\u0011q\u0001\u0001\u0005\u00025Da!!\u0003\u0001\t\u0003i\u0007BBA\u0006\u0001\u0011\u0005Q\u000eC\u0004\u0002\u000e\u0001!\t!a\u0004\t\u000f\u0005U\u0001\u0001\"\u0001\u0002\u0018!9\u00111\u0004\u0001\u0005\u0002\u0005u\u0001bBA\u0011\u0001\u0011\u0005\u00111\u0005\u0005\b\u0003O\u0001A\u0011AA\u0015\u0011\u001d\ty\u0003\u0001C\u0001\u0003cAq!!\u000e\u0001\t\u0003\t9\u0004C\u0004\u0002<\u0001!\t!!\u0010\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002D!9\u0011q\t\u0001\u0005\u0002\u0005%\u0003bBA'\u0001\u0011\u0005\u0011q\n\u0005\u0007\u0003'\u0002A\u0011B,\t\r\u0005U\u0003\u0001\"\u0003X\u0011\u0019\t9\u0006\u0001C\u0001/\"1\u0011\u0011\f\u0001\u0005\u0002]Cq!a\u0017\u0001\t\u0003\ti\u0006C\u0004\u0002v\u0001!\t!!\u0018\t\u000f\u0005]\u0004\u0001\"\u0001\u0002^!9\u0011Q\u0012\u0001\u0005\u0002\u0005=\u0005bBAK\u0001\u0011\u0005\u0011Q\f\u0005\b\u0003/\u0003A\u0011AAM\u0011\u001d\tI\u000b\u0001C\u0001\u0003WCq!a,\u0001\t\u0013\t\t\fC\u0004\u00026\u0002!I!a.\t\u0013\u0005\u0005\u0007!%A\u0005\n\u0005\r\u0007\"CAm\u0001E\u0005I\u0011BAn\u0011%\ty\u000eAI\u0001\n\u0013\tY\u000eC\u0005\u0002b\u0002\t\n\u0011\"\u0003\u0002\\\"1\u00111\u001d\u0001\u0005\n-Cq!!:\u0001\t\u0013\t9\u000fC\u0004\u0002l\u0002!I!!<\u0003)%sG/\u001a:oC2\u0004vn]5uS>t\u0017*\u001c9m\u0015\tA\u0014(\u0001\u0003vi&d'B\u0001\u001e<\u0003!Ig\u000e^3s]\u0006d'B\u0001\u001f>\u0003\u001d\u0011XM\u001a7fGRT\u0011AP\u0001\u0006g\u000e\fG.Y\n\u0003\u0001\u0001\u0003\"!\u0011\"\u000e\u0003uJ!aQ\u001f\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#A$\u0011\u0005\u0005C\u0015BA%>\u0005\u0011)f.\u001b;\u0002\u0013%\u001cH)\u001a4j]\u0016$W#\u0001'\u0011\u0005\u0005k\u0015B\u0001(>\u0005\u001d\u0011un\u001c7fC:\fq![:SC:<W-\u0001\u0004t_V\u00148-Z\u000b\u0002%B\u00111\u000bV\u0007\u0002o%\u0011Qk\u000e\u0002\u000b'>,(oY3GS2,\u0017!B:uCJ$X#\u0001-\u0011\u0005\u0005K\u0016B\u0001.>\u0005\rIe\u000e^\u0001\u0006a>Lg\u000e^\u0001\u0004K:$\u0017!\u00044j]\u0006d\u0007k\\:ji&|g.F\u0001`!\t\u0001\u0017-D\u0001\u0001\u0013\t\u00117MA\u0002Q_NL!\u0001Z\u001c\u0003\u0011A{7/\u001b;j_:\fQ\"[:Ue\u0006t7\u000f]1sK:$\u0018\u0001C5t\u001f\u001a47/\u001a;\u0002\u001b%\u001cx\n]1rk\u0016\u0014\u0016M\\4f\u0003-\u0001x.\u001b8u\u001fJ,En]3\u0015\u0005aS\u0007\"B6\r\u0001\u0004A\u0016aA1mi\u0006yQ.Y6f)J\fgn\u001d9be\u0016tG/F\u0001o!\t\u00196-A\tnC.,GK]1ogB\f'/\u001a8u\u0013\u001a$\"A\\9\t\u000bIt\u0001\u0019\u0001'\u0002\t\r|g\u000eZ\u0001\no&$\bn\u0015;beR$\"A\\;\t\u000bY{\u0001\u0019\u0001-\u0002\u0013]LG\u000f\u001b)pS:$HC\u00018y\u0011\u0015Y\u0006\u00031\u0001Y\u0003\u001d9\u0018\u000e\u001e5F]\u0012$\"A\\>\t\u000bq\u000b\u0002\u0019\u0001-\u0002\u0015]LG\u000f[*pkJ\u001cW\r\u0006\u0002o}\")\u0001K\u0005a\u0001%\u0006Iq/\u001b;i'\"Lg\r\u001e\u000b\u0004]\u0006\r\u0001BBA\u0003'\u0001\u0007\u0001,A\u0003tQ&4G/\u0001\u0006g_\u000e,8o\u0015;beR\fQAZ8dkN\f\u0001BZ8dkN,e\u000eZ\u0001\u0005I\t\f'\u000fF\u0002o\u0003#Aa!a\u0005\u0018\u0001\u0004q\u0017\u0001\u0002;iCR\f1\u0001J;q)\rq\u0017\u0011\u0004\u0005\u00067b\u0001\r\u0001W\u0001\bI\t\f'\u000fJ;q)\rq\u0017q\u0004\u0005\u0007\u0003'I\u0002\u0019\u00018\u0002\u000f\u0011*\b\u000f\n2beR\u0019a.!\n\t\r\u0005M!\u00041\u0001o\u0003\u0015)h.[8o)\rq\u00171\u0006\u0005\u0007\u0003[Y\u0002\u0019\u00018\u0002\u0007A|7/\u0001\u0005j]\u000edW\u000fZ3t)\ra\u00151\u0007\u0005\u0007\u0003[a\u0002\u0019\u00018\u0002!A\u0014x\u000e]3sYfLen\u00197vI\u0016\u001cHc\u0001'\u0002:!1\u0011QF\u000fA\u00029\f\u0001\u0002\u001d:fG\u0016$Wm\u001d\u000b\u0004\u0019\u0006}\u0002BBA\u0017=\u0001\u0007a.\u0001\tqe>\u0004XM\u001d7z!J,7-\u001a3fgR\u0019A*!\u0012\t\r\u00055r\u00041\u0001o\u0003%\u0019\u0018-\\3SC:<W\rF\u0002M\u0003\u0017Ba!!\f!\u0001\u0004q\u0017\u0001C8wKJd\u0017\r]:\u0015\u00071\u000b\t\u0006\u0003\u0004\u0002.\u0005\u0002\rA\\\u0001\u0006Y&tW\rM\u0001\u000bY&tWm\u00144gg\u0016$\u0018\u0001\u00027j]\u0016\faaY8mk6t\u0017a\u00037j]\u0016\u001cuN\u001c;f]R,\"!a\u0018\u0011\t\u0005\u0005\u0014q\u000e\b\u0005\u0003G\nY\u0007E\u0002\u0002fuj!!a\u001a\u000b\u0007\u0005%T)\u0001\u0004=e>|GOP\u0005\u0004\u0003[j\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002r\u0005M$AB*ue&twMC\u0002\u0002nu\n\u0011\u0002\\5oK\u000e\u000b'/\u001a;\u0002\u00131Lg.Z\"be\u0006$\bf\u0003\u0015\u0002|\u0005\u0005\u00151QAD\u0003\u0013\u00032!QA?\u0013\r\ty(\u0010\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u0003\u000b\u000bq\"^:fA\u0001d\u0017N\\3DCJ,G\u000fY\u0001\u0006g&t7-Z\u0011\u0003\u0003\u0017\u000baA\r\u00182c9\u0002\u0014!C:i_^,%O]8s)\u0011\ty&!%\t\u000f\u0005M\u0015\u00061\u0001\u0002`\u0005\u0019Qn]4\u0002\u0013MDwn\u001e#fEV<\u0017\u0001B:i_^,\"!a'\u0011\t\u0005u\u0015qU\u0007\u0003\u0003?SA!!)\u0002$\u0006!A.\u00198h\u0015\t\t)+\u0001\u0003kCZ\f\u0017\u0002BA9\u0003?\u000b1b]1nKB{\u0017N\u001c;BgR\u0019A*!,\t\r\u0005MA\u00061\u0001o\u0003!\t7o\u00144gg\u0016$Hc\u00018\u00024\")1,\fa\u00011\u0006I1m\u001c9z%\u0006tw-\u001a\u000b\n]\u0006e\u00161XA_\u0003\u007fCq\u0001\u0015\u0018\u0011\u0002\u0003\u0007!\u000bC\u0004W]A\u0005\t\u0019\u0001-\t\u000fms\u0003\u0013!a\u00011\"9AL\fI\u0001\u0002\u0004A\u0016aE2paf\u0014\u0016M\\4fI\u0011,g-Y;mi\u0012\nTCAAcU\r\u0011\u0016qY\u0016\u0003\u0003\u0013\u0004B!a3\u0002V6\u0011\u0011Q\u001a\u0006\u0005\u0003\u001f\f\t.A\u0005v]\u000eDWmY6fI*\u0019\u00111[\u001f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002X\u00065'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u00192m\u001c9z%\u0006tw-\u001a\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011Q\u001c\u0016\u00041\u0006\u001d\u0017aE2paf\u0014\u0016M\\4fI\u0011,g-Y;mi\u0012\u001a\u0014aE2paf\u0014\u0016M\\4fI\u0011,g-Y;mi\u0012\"\u0014!\u00035bgN{WO]2f\u0003)\u0011w\u000e\u001e5SC:<Wm\u001d\u000b\u0004\u0019\u0006%\bBBA\ni\u0001\u0007a.A\u0006c_RDG)\u001a4j]\u0016$Gc\u0001'\u0002p\"1\u00111C\u001bA\u00029\u0004"
)
public interface InternalPositionImpl {
   boolean isDefined();

   boolean isRange();

   SourceFile source();

   int start();

   int point();

   int end();

   // $FF: synthetic method
   static Position finalPosition$(final InternalPositionImpl $this) {
      return $this.finalPosition();
   }

   default Position finalPosition() {
      return ((Position)this).source().positionInUltimateSource((Position)this);
   }

   // $FF: synthetic method
   static boolean isTransparent$(final InternalPositionImpl $this) {
      return $this.isTransparent();
   }

   default boolean isTransparent() {
      return false;
   }

   // $FF: synthetic method
   static boolean isOffset$(final InternalPositionImpl $this) {
      return $this.isOffset();
   }

   default boolean isOffset() {
      return ((Position)this).isDefined() && !((Position)this).isRange();
   }

   // $FF: synthetic method
   static boolean isOpaqueRange$(final InternalPositionImpl $this) {
      return $this.isOpaqueRange();
   }

   default boolean isOpaqueRange() {
      return ((Position)this).isRange() && !this.isTransparent();
   }

   // $FF: synthetic method
   static int pointOrElse$(final InternalPositionImpl $this, final int alt) {
      return $this.pointOrElse(alt);
   }

   default int pointOrElse(final int alt) {
      return ((Position)this).isDefined() ? ((Position)this).point() : alt;
   }

   // $FF: synthetic method
   static Position makeTransparent$(final InternalPositionImpl $this) {
      return $this.makeTransparent();
   }

   default Position makeTransparent() {
      return this.isOpaqueRange() ? Position$.MODULE$.transparent(((Position)this).source(), ((Position)this).start(), ((Position)this).point(), ((Position)this).end()) : (Position)this;
   }

   // $FF: synthetic method
   static Position makeTransparentIf$(final InternalPositionImpl $this, final boolean cond) {
      return $this.makeTransparentIf(cond);
   }

   default Position makeTransparentIf(final boolean cond) {
      return cond && this.isOpaqueRange() ? Position$.MODULE$.transparent(((Position)this).source(), ((Position)this).start(), ((Position)this).point(), ((Position)this).end()) : (Position)this;
   }

   // $FF: synthetic method
   static Position withStart$(final InternalPositionImpl $this, final int start) {
      return $this.withStart(start);
   }

   default Position withStart(final int start) {
      SourceFile x$2 = ((Position)this).source();
      int x$3 = ((Position)this).point();
      int x$4 = ((Position)this).end();
      return Position$.MODULE$.range(x$2, start, x$3, x$4);
   }

   // $FF: synthetic method
   static Position withPoint$(final InternalPositionImpl $this, final int point) {
      return $this.withPoint(point);
   }

   default Position withPoint(final int point) {
      if (((Position)this).isRange()) {
         SourceFile x$2 = ((Position)this).source();
         int x$3 = ((Position)this).start();
         int x$4 = ((Position)this).end();
         return Position$.MODULE$.range(x$2, x$3, point, x$4);
      } else {
         return Position$.MODULE$.offset(((Position)this).source(), point);
      }
   }

   // $FF: synthetic method
   static Position withEnd$(final InternalPositionImpl $this, final int end) {
      return $this.withEnd(end);
   }

   default Position withEnd(final int end) {
      SourceFile x$2 = ((Position)this).source();
      int x$3 = ((Position)this).start();
      int x$4 = ((Position)this).point();
      return Position$.MODULE$.range(x$2, x$3, x$4, end);
   }

   // $FF: synthetic method
   static Position withSource$(final InternalPositionImpl $this, final SourceFile source) {
      return $this.withSource(source);
   }

   default Position withSource(final SourceFile source) {
      int var10000 = ((Position)this).start();
      int var10001 = ((Position)this).point();
      int copyRange_end = ((Position)this).end();
      int copyRange_point = var10001;
      int copyRange_start = var10000;
      return Position$.MODULE$.range(source, copyRange_start, copyRange_point, copyRange_end);
   }

   // $FF: synthetic method
   static Position withShift$(final InternalPositionImpl $this, final int shift) {
      return $this.withShift(shift);
   }

   default Position withShift(final int shift) {
      return Position$.MODULE$.range(((Position)this).source(), ((Position)this).start() + shift, ((Position)this).point() + shift, ((Position)this).end() + shift);
   }

   // $FF: synthetic method
   static Position focusStart$(final InternalPositionImpl $this) {
      return $this.focusStart();
   }

   default Position focusStart() {
      return ((Position)this).isRange() ? this.asOffset(((Position)this).start()) : (Position)this;
   }

   // $FF: synthetic method
   static Position focus$(final InternalPositionImpl $this) {
      return $this.focus();
   }

   default Position focus() {
      return ((Position)this).isRange() ? this.asOffset(((Position)this).point()) : (Position)this;
   }

   // $FF: synthetic method
   static Position focusEnd$(final InternalPositionImpl $this) {
      return $this.focusEnd();
   }

   default Position focusEnd() {
      return ((Position)this).isRange() ? this.asOffset(((Position)this).end()) : (Position)this;
   }

   // $FF: synthetic method
   static Position $bar$(final InternalPositionImpl $this, final Position that) {
      return $this.$bar(that);
   }

   default Position $bar(final Position that) {
      return this.union(that);
   }

   // $FF: synthetic method
   static Position $up$(final InternalPositionImpl $this, final int point) {
      return $this.$up(point);
   }

   default Position $up(final int point) {
      return this.withPoint(point);
   }

   // $FF: synthetic method
   static Position $bar$up$(final InternalPositionImpl $this, final Position that) {
      return $this.$bar$up(that);
   }

   default Position $bar$up(final Position that) {
      return this.$bar(that).$up(that.point());
   }

   // $FF: synthetic method
   static Position $up$bar$(final InternalPositionImpl $this, final Position that) {
      return $this.$up$bar(that);
   }

   default Position $up$bar(final Position that) {
      return this.$bar(that).$up(((Position)this).point());
   }

   // $FF: synthetic method
   static Position union$(final InternalPositionImpl $this, final Position pos) {
      return $this.union(pos);
   }

   default Position union(final Position pos) {
      if (!pos.isRange()) {
         return (Position)this;
      } else if (((Position)this).isRange()) {
         RichInt var10000 = .MODULE$;
         int var6 = ((Position)this).start();
         int min$extension_that = pos.start();
         scala.math.package var10 = scala.math.package..MODULE$;
         int x$1 = Math.min(var6, min$extension_that);
         RichInt var11 = .MODULE$;
         int var7 = ((Position)this).end();
         int max$extension_that = pos.end();
         scala.math.package var12 = scala.math.package..MODULE$;
         int x$2 = Math.max(var7, max$extension_that);
         SourceFile x$3 = ((Position)this).source();
         int x$4 = ((Position)this).point();
         return Position$.MODULE$.range(x$3, x$1, x$4, x$2);
      } else {
         return pos;
      }
   }

   // $FF: synthetic method
   static boolean includes$(final InternalPositionImpl $this, final Position pos) {
      return $this.includes(pos);
   }

   default boolean includes(final Position pos) {
      return ((Position)this).isRange() && pos.isDefined() && ((Position)this).start() <= pos.start() && pos.end() <= ((Position)this).end();
   }

   // $FF: synthetic method
   static boolean properlyIncludes$(final InternalPositionImpl $this, final Position pos) {
      return $this.properlyIncludes(pos);
   }

   default boolean properlyIncludes(final Position pos) {
      return this.includes(pos) && (((Position)this).start() < pos.start() || pos.end() < ((Position)this).end());
   }

   // $FF: synthetic method
   static boolean precedes$(final InternalPositionImpl $this, final Position pos) {
      return $this.precedes(pos);
   }

   default boolean precedes(final Position pos) {
      return this.bothDefined(pos) && ((Position)this).end() <= pos.start();
   }

   // $FF: synthetic method
   static boolean properlyPrecedes$(final InternalPositionImpl $this, final Position pos) {
      return $this.properlyPrecedes(pos);
   }

   default boolean properlyPrecedes(final Position pos) {
      return this.bothDefined(pos) && ((Position)this).end() < pos.start();
   }

   // $FF: synthetic method
   static boolean sameRange$(final InternalPositionImpl $this, final Position pos) {
      return $this.sameRange(pos);
   }

   default boolean sameRange(final Position pos) {
      return this.bothRanges(pos) && ((Position)this).start() == pos.start() && ((Position)this).end() == pos.end();
   }

   // $FF: synthetic method
   static boolean overlaps$(final InternalPositionImpl $this, final Position pos) {
      return $this.overlaps(pos);
   }

   default boolean overlaps(final Position pos) {
      return this.bothRanges(pos) && ((Position)this).start() < pos.end() && pos.start() < ((Position)this).end();
   }

   private int line0() {
      return ((Position)this).source().offsetToLine(((Position)this).point());
   }

   private int lineOffset() {
      return ((Position)this).source().lineToOffset(this.line0());
   }

   // $FF: synthetic method
   static int line$(final InternalPositionImpl $this) {
      return $this.line();
   }

   default int line() {
      return this.hasSource() ? this.line0() + 1 : 0;
   }

   // $FF: synthetic method
   static int column$(final InternalPositionImpl $this) {
      return $this.column();
   }

   default int column() {
      if (!this.hasSource()) {
         return 0;
      } else {
         int idx = this.lineOffset();

         int col;
         for(col = 0; idx != ((Position)this).point(); ++idx) {
            col += ((Position)this).source().content()[idx] == '\t' ? 8 - col % 8 : 1;
         }

         return col + 1;
      }
   }

   // $FF: synthetic method
   static String lineContent$(final InternalPositionImpl $this) {
      return $this.lineContent();
   }

   default String lineContent() {
      return this.hasSource() ? ((Position)this).source().lineToString(this.line0()) : "";
   }

   // $FF: synthetic method
   static String lineCaret$(final InternalPositionImpl $this) {
      return $this.lineCaret();
   }

   default String lineCaret() {
      if (!this.hasSource()) {
         return "";
      } else {
         StringBuilder buf = new StringBuilder();

         for(int idx = this.lineOffset(); idx < ((Position)this).point(); ++idx) {
            buf.append((char)(((Position)this).source().content()[idx] == '\t' ? '\t' : ' '));
         }

         buf.append('^');
         return buf.result();
      }
   }

   // $FF: synthetic method
   static String lineCarat$(final InternalPositionImpl $this) {
      return $this.lineCarat();
   }

   /** @deprecated */
   default String lineCarat() {
      return this.lineCaret();
   }

   // $FF: synthetic method
   static String showError$(final InternalPositionImpl $this, final String msg) {
      return $this.showError(msg);
   }

   default String showError(final String msg) {
      Position var2 = this.finalPosition();
      if (var2 instanceof FakePos) {
         String fmsg = ((FakePos)var2).msg();
         return (new java.lang.StringBuilder(1)).append(fmsg).append(" ").append(msg).toString();
      } else {
         return NoPosition$.MODULE$.equals(var2) ? msg : (new java.lang.StringBuilder(2)).append(var2.line()).append(": ").append(msg).append(System.lineSeparator()).append(escaped$1(var2.lineContent())).append(System.lineSeparator()).append(var2.lineCaret()).toString();
      }
   }

   // $FF: synthetic method
   static String showDebug$(final InternalPositionImpl $this) {
      return $this.showDebug();
   }

   default String showDebug() {
      return this.toString();
   }

   // $FF: synthetic method
   static String show$(final InternalPositionImpl $this) {
      return $this.show();
   }

   default String show() {
      if (this.isOpaqueRange()) {
         return (new java.lang.StringBuilder(3)).append("[").append(((Position)this).start()).append(":").append(((Position)this).end()).append("]").toString();
      } else if (this.isTransparent()) {
         return (new java.lang.StringBuilder(3)).append("<").append(((Position)this).start()).append(":").append(((Position)this).end()).append(">").toString();
      } else {
         return ((Position)this).isDefined() ? (new java.lang.StringBuilder(2)).append("[").append(((Position)this).point()).append("]").toString() : "[NoPosition]";
      }
   }

   // $FF: synthetic method
   static boolean samePointAs$(final InternalPositionImpl $this, final Position that) {
      return $this.samePointAs(that);
   }

   default boolean samePointAs(final Position that) {
      if (that.isDefined() && ((Position)this).point() == that.point()) {
         AbstractFile var10000 = ((Position)this).source().file();
         AbstractFile var2 = that.source().file();
         if (var10000 == null) {
            if (var2 == null) {
               return true;
            }
         } else if (var10000.equals(var2)) {
            return true;
         }
      }

      return false;
   }

   private Position asOffset(final int point) {
      return Position$.MODULE$.offset(((Position)this).source(), point);
   }

   private Position copyRange(final SourceFile source, final int start, final int point, final int end) {
      return Position$.MODULE$.range(source, start, point, end);
   }

   private SourceFile copyRange$default$1() {
      return ((Position)this).source();
   }

   private int copyRange$default$2() {
      return ((Position)this).start();
   }

   private int copyRange$default$3() {
      return ((Position)this).point();
   }

   private int copyRange$default$4() {
      return ((Position)this).end();
   }

   private boolean hasSource() {
      return ((Position)this).source() != NoSourceFile$.MODULE$;
   }

   private boolean bothRanges(final Position that) {
      return ((Position)this).isRange() && that.isRange();
   }

   private boolean bothDefined(final Position that) {
      return ((Position)this).isDefined() && that.isDefined();
   }

   private static String u$1(final int c) {
      return scala.collection.StringOps..MODULE$.format$extension("\\u%04x", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{c}));
   }

   private static boolean uable$1(final int c) {
      return c < 32 && c != 9 || c == 127;
   }

   // $FF: synthetic method
   static boolean $anonfun$showError$1(final char c) {
      return uable$1(c);
   }

   // $FF: synthetic method
   static StringBuilder $anonfun$showError$2(final StringBuilder sb$1, final char c) {
      return sb$1.append(uable$1(c) ? u$1(c) : c);
   }

   private static String escaped$1(final String s) {
      int exists$extension_indexWhere$extension_from = 0;
      int exists$extension_indexWhere$extension_len = s.length();
      int exists$extension_indexWhere$extension_i = exists$extension_indexWhere$extension_from;

      int var10000;
      while(true) {
         if (exists$extension_indexWhere$extension_i >= exists$extension_indexWhere$extension_len) {
            var10000 = -1;
            break;
         }

         if (uable$1(s.charAt(exists$extension_indexWhere$extension_i))) {
            var10000 = exists$extension_indexWhere$extension_i;
            break;
         }

         ++exists$extension_indexWhere$extension_i;
      }

      if (var10000 == -1) {
         return s;
      } else {
         StringBuilder sb = new StringBuilder();
         int foreach$extension_len = s.length();

         for(int foreach$extension_i = 0; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            char var7 = s.charAt(foreach$extension_i);
            $anonfun$showError$2(sb, var7);
         }

         return sb.result();
      }
   }

   static void $init$(final InternalPositionImpl $this) {
   }

   // $FF: synthetic method
   static Object $anonfun$showError$1$adapted(final Object c) {
      return BoxesRunTime.boxToBoolean($anonfun$showError$1(BoxesRunTime.unboxToChar(c)));
   }

   // $FF: synthetic method
   static StringBuilder $anonfun$showError$2$adapted(final StringBuilder sb$1, final Object c) {
      return $anonfun$showError$2(sb$1, BoxesRunTime.unboxToChar(c));
   }
}
