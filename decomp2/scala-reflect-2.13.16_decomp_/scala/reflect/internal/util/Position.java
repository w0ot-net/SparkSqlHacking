package scala.reflect.internal.util;

import scala.Option;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.macros.Attachments;
import scala.reflect.macros.EmptyAttachments;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0002\f\u0018\u0001\u0001BQa\r\u0001\u0005\u0002Q*AA\u000e\u0001\u0001k!)q\u0007\u0001C\u0001q!)\u0011\b\u0001C\u0001u!)a\t\u0001C\t\u000f\")\u0011\f\u0001C\u00015\")a\f\u0001C\u00015\")q\f\u0001C\u0001A\")A\r\u0001C\u0001K\")\u0011\u000e\u0001C\u0001K\")!\u000e\u0001C\u0001K\u001e)1n\u0006E\u0001Y\u001a)ac\u0006E\u0001[\")1'\u0004C\u0001c\"9!/\u0004b\u0001\n\u000b\u0019\bB\u0002<\u000eA\u00035A\u000fC\u0003x\u001b\u0011%\u0001\u0010C\u0004\u0002\u00025!\t!a\u0001\t\u000f\u0005EQ\u0002\"\u0001\u0002\u0014!9\u0011\u0011D\u0007\u0005\u0002\u0005m\u0001bBA\u0013\u001b\u0011\u0005\u0011q\u0005\u0002\t!>\u001c\u0018\u000e^5p]*\u0011\u0001$G\u0001\u0005kRLGN\u0003\u0002\u001b7\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u001d;\u00059!/\u001a4mK\u000e$(\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M)\u0001!I\u0014-aA\u0011!%J\u0007\u0002G)\u0011AeG\u0001\u0007[\u0006\u001c'o\\:\n\u0005\u0019\u001a#\u0001E#naRL\u0018\t\u001e;bG\"lWM\u001c;t!\tA3&D\u0001*\u0015\tQ3$A\u0002ba&L!AF\u0015\u0011\u00055rS\"A\f\n\u0005=:\"\u0001F%oi\u0016\u0014h.\u00197Q_NLG/[8o\u00136\u0004H\u000e\u0005\u0002.c%\u0011!g\u0006\u0002\u0013\t\u0016\u0004(/Z2bi\u0016$\u0007k\\:ji&|g.\u0001\u0004=S:LGO\u0010\u000b\u0002kA\u0011Q\u0006\u0001\u0002\u0004!>\u001c\u0018a\u00019pgV\tQ'A\u0004xSRD\u0007k\\:\u0015\u0005m\"%C\u0001\u001f?\r\u0011i\u0004\u0001A\u001e\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005\tz\u0014B\u0001!$\u0005-\tE\u000f^1dQ6,g\u000e^:\u0006\tYb\u0004E\u0011\t\u0003\u0007\ni\u0011\u0001\u0001\u0005\u0006\u000b\u0012\u0001\r!N\u0001\u0007]\u0016<\bk\\:\u0002\t\u0019\f\u0017\u000e\u001c\u000b\u0003\u00112\u0003\"!\u0013&\u000e\u0003uI!aS\u000f\u0003\u000f9{G\u000f[5oO\")Q*\u0002a\u0001\u001d\u0006!q\u000f[1u!\tyeK\u0004\u0002Q)B\u0011\u0011+H\u0007\u0002%*\u00111kH\u0001\u0007yI|w\u000e\u001e \n\u0005Uk\u0012A\u0002)sK\u0012,g-\u0003\u0002X1\n11\u000b\u001e:j]\u001eT!!V\u000f\u0002\u0013%\u001cH)\u001a4j]\u0016$W#A.\u0011\u0005%c\u0016BA/\u001e\u0005\u001d\u0011un\u001c7fC:\fq![:SC:<W-\u0001\u0004t_V\u00148-Z\u000b\u0002CB\u0011QFY\u0005\u0003G^\u0011!bU8ve\u000e,g)\u001b7f\u0003\u0015\u0019H/\u0019:u+\u00051\u0007CA%h\u0013\tAWDA\u0002J]R\fQ\u0001]8j]R\f1!\u001a8e\u0003!\u0001vn]5uS>t\u0007CA\u0017\u000e'\tia\u000e\u0005\u0002J_&\u0011\u0001/\b\u0002\u0007\u0003:L(+\u001a4\u0015\u00031\fa\u0001^1c\u0013:\u001cW#\u0001;\u0010\u0003Ul\u0012\u0001C\u0001\bi\u0006\u0014\u0017J\\2!\u0003!1\u0018\r\\5eCR,WCA=})\tQx\u0010\u0005\u0002|y2\u0001A!B?\u0012\u0005\u0004q(!\u0001+\u0012\u0005!+\u0004\"B\u001c\u0012\u0001\u0004Q\u0018!\u00044pe6\fG/T3tg\u0006<W\rF\u0004O\u0003\u000b\tI!!\u0004\t\r\u0005\u001d!\u00031\u00016\u0003\u0015\u0001xn]%o\u0011\u0019\tYA\u0005a\u0001\u001d\u0006\u0019Qn]4\t\r\u0005=!\u00031\u0001\\\u0003-\u0019\bn\u001c:uK:4\u0015\u000e\\3\u0002\r=4gm]3u)\u0015)\u0014QCA\f\u0011\u0015y6\u00031\u0001b\u0011\u0015I7\u00031\u0001g\u0003\u0015\u0011\u0018M\\4f)%)\u0014QDA\u0010\u0003C\t\u0019\u0003C\u0003`)\u0001\u0007\u0011\rC\u0003e)\u0001\u0007a\rC\u0003j)\u0001\u0007a\rC\u0003k)\u0001\u0007a-A\u0006ue\u0006t7\u000f]1sK:$H#C\u001b\u0002*\u0005-\u0012QFA\u0018\u0011\u0015yV\u00031\u0001b\u0011\u0015!W\u00031\u0001g\u0011\u0015IW\u00031\u0001g\u0011\u0015QW\u00031\u0001g\u0001"
)
public class Position extends EmptyAttachments implements scala.reflect.api.Position, InternalPositionImpl, DeprecatedPosition {
   public static Position transparent(final SourceFile source, final int start, final int point, final int end) {
      return Position$.MODULE$.transparent(source, start, point, end);
   }

   public static Position range(final SourceFile source, final int start, final int point, final int end) {
      return Position$.MODULE$.range(source, start, point, end);
   }

   public static String formatMessage(final Position posIn, final String msg, final boolean shortenFile) {
      return Position$.MODULE$.formatMessage(posIn, msg, shortenFile);
   }

   public static int tabInc() {
      Position$ var10000 = Position$.MODULE$;
      return 8;
   }

   /** @deprecated */
   public Option offset() {
      return DeprecatedPosition.offset$(this);
   }

   /** @deprecated */
   public Position toSingleLine() {
      return DeprecatedPosition.toSingleLine$(this);
   }

   /** @deprecated */
   public int safeLine() {
      return DeprecatedPosition.safeLine$(this);
   }

   /** @deprecated */
   public String dbgString() {
      return DeprecatedPosition.dbgString$(this);
   }

   /** @deprecated */
   public Position inUltimateSource(final SourceFile source) {
      return DeprecatedPosition.inUltimateSource$(this, source);
   }

   /** @deprecated */
   public Tuple2 lineWithCarat(final int maxWidth) {
      return DeprecatedPosition.lineWithCarat$(this, maxWidth);
   }

   /** @deprecated */
   public Position withSource(final SourceFile source, final int shift) {
      return DeprecatedPosition.withSource$(this, source, shift);
   }

   /** @deprecated */
   public int startOrPoint() {
      return DeprecatedPosition.startOrPoint$(this);
   }

   /** @deprecated */
   public int endOrPoint() {
      return DeprecatedPosition.endOrPoint$(this);
   }

   public Position finalPosition() {
      return InternalPositionImpl.finalPosition$(this);
   }

   public boolean isTransparent() {
      return InternalPositionImpl.isTransparent$(this);
   }

   public boolean isOffset() {
      return InternalPositionImpl.isOffset$(this);
   }

   public boolean isOpaqueRange() {
      return InternalPositionImpl.isOpaqueRange$(this);
   }

   public int pointOrElse(final int alt) {
      return InternalPositionImpl.pointOrElse$(this, alt);
   }

   public Position makeTransparent() {
      return InternalPositionImpl.makeTransparent$(this);
   }

   public final Position makeTransparentIf(final boolean cond) {
      return InternalPositionImpl.makeTransparentIf$(this, cond);
   }

   public Position withStart(final int start) {
      return InternalPositionImpl.withStart$(this, start);
   }

   public Position withPoint(final int point) {
      return InternalPositionImpl.withPoint$(this, point);
   }

   public Position withEnd(final int end) {
      return InternalPositionImpl.withEnd$(this, end);
   }

   public Position withSource(final SourceFile source) {
      return InternalPositionImpl.withSource$(this, source);
   }

   public Position withShift(final int shift) {
      return InternalPositionImpl.withShift$(this, shift);
   }

   public Position focusStart() {
      return InternalPositionImpl.focusStart$(this);
   }

   public Position focus() {
      return InternalPositionImpl.focus$(this);
   }

   public Position focusEnd() {
      return InternalPositionImpl.focusEnd$(this);
   }

   public Position $bar(final Position that) {
      return InternalPositionImpl.$bar$(this, that);
   }

   public Position $up(final int point) {
      return InternalPositionImpl.$up$(this, point);
   }

   public Position $bar$up(final Position that) {
      return InternalPositionImpl.$bar$up$(this, that);
   }

   public Position $up$bar(final Position that) {
      return InternalPositionImpl.$up$bar$(this, that);
   }

   public Position union(final Position pos) {
      return InternalPositionImpl.union$(this, pos);
   }

   public boolean includes(final Position pos) {
      return InternalPositionImpl.includes$(this, pos);
   }

   public boolean properlyIncludes(final Position pos) {
      return InternalPositionImpl.properlyIncludes$(this, pos);
   }

   public boolean precedes(final Position pos) {
      return InternalPositionImpl.precedes$(this, pos);
   }

   public boolean properlyPrecedes(final Position pos) {
      return InternalPositionImpl.properlyPrecedes$(this, pos);
   }

   public boolean sameRange(final Position pos) {
      return InternalPositionImpl.sameRange$(this, pos);
   }

   public boolean overlaps(final Position pos) {
      return InternalPositionImpl.overlaps$(this, pos);
   }

   public int line() {
      return InternalPositionImpl.line$(this);
   }

   public int column() {
      return InternalPositionImpl.column$(this);
   }

   public String lineContent() {
      return InternalPositionImpl.lineContent$(this);
   }

   public String lineCaret() {
      return InternalPositionImpl.lineCaret$(this);
   }

   /** @deprecated */
   public String lineCarat() {
      return InternalPositionImpl.lineCarat$(this);
   }

   public String showError(final String msg) {
      return InternalPositionImpl.showError$(this, msg);
   }

   public String showDebug() {
      return InternalPositionImpl.showDebug$(this);
   }

   public String show() {
      return InternalPositionImpl.show$(this);
   }

   public boolean samePointAs(final Position that) {
      return InternalPositionImpl.samePointAs$(this, that);
   }

   public Position pos() {
      return this;
   }

   public Attachments withPos(final Position newPos) {
      return newPos;
   }

   public Nothing fail(final String what) {
      throw new UnsupportedOperationException((new StringBuilder(13)).append("Position.").append(what).append(" on ").append(this).toString());
   }

   public boolean isDefined() {
      return false;
   }

   public boolean isRange() {
      return false;
   }

   public SourceFile source() {
      return NoSourceFile$.MODULE$;
   }

   public int start() {
      throw this.fail("start");
   }

   public int point() {
      throw this.fail("point");
   }

   public int end() {
      throw this.fail("end");
   }
}
