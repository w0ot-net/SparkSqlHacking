package scala.reflect.internal.util;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U4\u0011b\u0003\u0007\u0011\u0002\u0007\u0005A\u0002F\u001a\t\u000be\u0001A\u0011A\u000e\t\u000b}\u0001A\u0011\u0001\u0011\t\u000bE\u0002A\u0011\u0001\u001a\t\u000bq\u0002A\u0011A\u001f\t\u000b\u0005\u0003A\u0011\u0001\"\t\u000bE\u0003A\u0011\u0001*\t\u000bm\u0003A\u0011\u0001/\t\u000b\u0015\u0004A\u0011\u00014\t\u000b5\u0004A\u0011A\u001f\t\u000bE\u0004A\u0011A\u001f\u0003%\u0011+\u0007O]3dCR,G\rU8tSRLwN\u001c\u0006\u0003\u001b9\tA!\u001e;jY*\u0011q\u0002E\u0001\tS:$XM\u001d8bY*\u0011\u0011CE\u0001\be\u00164G.Z2u\u0015\u0005\u0019\u0012!B:dC2\f7C\u0001\u0001\u0016!\t1r#D\u0001\u0013\u0013\tA\"C\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tA\u0004\u0005\u0002\u0017;%\u0011aD\u0005\u0002\u0005+:LG/\u0001\u0004pM\u001a\u001cX\r^\u000b\u0002CA\u0019aC\t\u0013\n\u0005\r\u0012\"AB(qi&|g\u000e\u0005\u0002\u0017K%\u0011aE\u0005\u0002\u0004\u0013:$\bF\u0002\u0002)W1rs\u0006\u0005\u0002\u0017S%\u0011!F\u0005\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0002[\u0005YQo]3!AB|\u0017N\u001c;a\u0003\u0015\u0019\u0018N\\2fC\u0005\u0001\u0014!\u0002\u001a/s9\u0002\u0014\u0001\u0004;p'&tw\r\\3MS:,W#A\u001a\u0011\u0005Q*T\"\u0001\u0007\n\u0005Yb!\u0001\u0003)pg&$\u0018n\u001c8)\r\rA3\u0006\u000f\u0018;C\u0005I\u0014aC;tK\u0002\u0002gm\\2vg\u0002\f\u0013aO\u0001\u0007e9\n\u0014G\f\u0019\u0002\u0011M\fg-\u001a'j]\u0016,\u0012\u0001\n\u0015\u0007\t!ZsH\f\u001e\"\u0003\u0001\u000b!\"^:fA\u0001d\u0017N\\3a\u0003%!'mZ*ue&tw-F\u0001D!\t!5J\u0004\u0002F\u0013B\u0011aIE\u0007\u0002\u000f*\u0011\u0001JG\u0001\u0007yI|w\u000e\u001e \n\u0005)\u0013\u0012A\u0002)sK\u0012,g-\u0003\u0002M\u001b\n11\u000b\u001e:j]\u001eT!A\u0013\n)\r\u0015A3f\u0014\u0018;C\u0005\u0001\u0016aD;tK\u0002\u00027\u000f[8x\t\u0016\u0014Wo\u001a1\u0002!%tW\u000b\u001c;j[\u0006$XmU8ve\u000e,GCA\u001aT\u0011\u0015!f\u00011\u0001V\u0003\u0019\u0019x.\u001e:dKB\u0011AGV\u0005\u0003/2\u0011!bU8ve\u000e,g)\u001b7fQ\u00191\u0001fK-/u\u0005\n!,A\nvg\u0016\u0004\u0003MZ5oC2\u0004vn]5uS>t\u0007-A\u0007mS:,w+\u001b;i\u0007\u0006\u0014\u0018\r\u001e\u000b\u0003;\u0002\u0004BA\u00060D\u0007&\u0011qL\u0005\u0002\u0007)V\u0004H.\u001a\u001a\t\u000b\u0005<\u0001\u0019\u0001\u0013\u0002\u00115\f\u0007pV5ei\"Dca\u0002\u0015,G:R\u0014%\u00013\u0002\u001fU\u001cX\r\t1mS:,7)\u0019:fi\u0002\f!b^5uQN{WO]2f)\r\u0019t\r\u001b\u0005\u0006)\"\u0001\r!\u0016\u0005\u0006S\"\u0001\r\u0001J\u0001\u0006g\"Lg\r\u001e\u0015\u0007\u0011!Z3N\f\u001e\"\u00031\f\u0001&^:fA\u0001<\u0018\u000e\u001e5T_V\u00148-\u001a\u0015t_V\u00148-Z\u0015aA\u0005tG\r\t1xSRD7\u000b[5gi\u0002\fAb\u001d;beR|%\u000fU8j]RDc!\u0003\u0015,_:R\u0014%\u00019\u0002'U\u001cX\r\t1ti\u0006\u0014H\u000f\u0019\u0011j]N$X-\u00193\u0002\u0015\u0015tGm\u0014:Q_&tG\u000f\u000b\u0004\u000bQ-\u001ahFO\u0011\u0002i\u0006\tRo]3!A\u0016tG\r\u0019\u0011j]N$X-\u00193"
)
public interface DeprecatedPosition {
   // $FF: synthetic method
   static Option offset$(final DeprecatedPosition $this) {
      return $this.offset();
   }

   /** @deprecated */
   default Option offset() {
      return (Option)(((Position)this).isDefined() ? new Some(((Position)this).point()) : .MODULE$);
   }

   // $FF: synthetic method
   static Position toSingleLine$(final DeprecatedPosition $this) {
      return $this.toSingleLine();
   }

   /** @deprecated */
   default Position toSingleLine() {
      return (Position)this;
   }

   // $FF: synthetic method
   static int safeLine$(final DeprecatedPosition $this) {
      return $this.safeLine();
   }

   /** @deprecated */
   default int safeLine() {
      return ((InternalPositionImpl)this).line();
   }

   // $FF: synthetic method
   static String dbgString$(final DeprecatedPosition $this) {
      return $this.dbgString();
   }

   /** @deprecated */
   default String dbgString() {
      return ((InternalPositionImpl)this).showDebug();
   }

   // $FF: synthetic method
   static Position inUltimateSource$(final DeprecatedPosition $this, final SourceFile source) {
      return $this.inUltimateSource(source);
   }

   /** @deprecated */
   default Position inUltimateSource(final SourceFile source) {
      return source.positionInUltimateSource((Position)this);
   }

   // $FF: synthetic method
   static Tuple2 lineWithCarat$(final DeprecatedPosition $this, final int maxWidth) {
      return $this.lineWithCarat(maxWidth);
   }

   /** @deprecated */
   default Tuple2 lineWithCarat(final int maxWidth) {
      return new Tuple2("", "");
   }

   // $FF: synthetic method
   static Position withSource$(final DeprecatedPosition $this, final SourceFile source, final int shift) {
      return $this.withSource(source, shift);
   }

   /** @deprecated */
   default Position withSource(final SourceFile source, final int shift) {
      return ((InternalPositionImpl)this).withSource(source).withShift(shift);
   }

   // $FF: synthetic method
   static int startOrPoint$(final DeprecatedPosition $this) {
      return $this.startOrPoint();
   }

   /** @deprecated */
   default int startOrPoint() {
      return ((Position)this).isRange() ? ((Position)this).start() : ((Position)this).point();
   }

   // $FF: synthetic method
   static int endOrPoint$(final DeprecatedPosition $this) {
      return $this.endOrPoint();
   }

   /** @deprecated */
   default int endOrPoint() {
      return ((Position)this).isRange() ? ((Position)this).end() : ((Position)this).point();
   }

   static void $init$(final DeprecatedPosition $this) {
   }
}
