package cats.kernel;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb!\u0002\u0006\f\u0003\u0003\u0001\u0002\"\u0002\u0017\u0001\t\u0003i\u0003\"B\u0018\u0001\t\u0003\u0001\u0004\"\u0002#\u0001\t\u0003)\u0005\"B+\u0001\t\u00031\u0006\"B1\u0001\t\u0003\u0011\u0007\"B7\u0001\t\u0003q\u0007\"B>\u0001\t\u0003a\bbBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\b\u0003G\u0001A\u0011AA\u0013\u0005U\u0001\u0016M\u001d;jC2|%\u000fZ3s\rVt7\r^5p]NT!\u0001D\u0007\u0002\r-,'O\\3m\u0015\u0005q\u0011\u0001B2biN\u001c\u0001!\u0006\u0002\u00121M\u0011\u0001A\u0005\t\u0004'Q1R\"A\u0006\n\u0005UY!aC#r\rVt7\r^5p]N\u0004\"a\u0006\r\r\u0001\u0011)\u0011\u0004\u0001b\u00015\t\t\u0001+\u0006\u0002\u001cME\u0011AD\t\t\u0003;\u0001j\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\b\u001d>$\b.\u001b8h!\r\u00192%J\u0005\u0003I-\u0011A\u0002U1si&\fGn\u0014:eKJ\u0004\"a\u0006\u0014\u0005\u000b\u001dB\"\u0019\u0001\u0015\u0003\u0003Q\u000b\"\u0001H\u0015\u0011\u0005uQ\u0013BA\u0016\u001f\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00039\u00022a\u0005\u0001\u0017\u00039\u0001\u0018M\u001d;jC2\u001cu.\u001c9be\u0016,\"!\r\u001e\u0015\u0007I\u0002%\t\u0006\u00024mA\u0011Q\u0004N\u0005\u0003ky\u0011a\u0001R8vE2,\u0007\"B\u001c\u0003\u0001\bA\u0014AA3w!\r9\u0002$\u000f\t\u0003/i\"\u0011b\u000f\u0002!\u0002\u0003\u0005)\u0019\u0001\u0015\u0003\u0003\u0005C#AO\u001f\u0011\u0005uq\u0014BA \u001f\u0005-\u0019\b/Z2jC2L'0\u001a3\t\u000b\u0005\u0013\u0001\u0019A\u001d\u0002\u0003aDQa\u0011\u0002A\u0002e\n\u0011!_\u0001\u000biJL8i\\7qCJ,WC\u0001$R)\r95\u000b\u0016\u000b\u0003\u0011:\u00032!H%L\u0013\tQeD\u0001\u0004PaRLwN\u001c\t\u0003;1K!!\u0014\u0010\u0003\u0007%sG\u000fC\u00038\u0007\u0001\u000fq\nE\u0002\u00181A\u0003\"aF)\u0005\u0013m\u001a\u0001\u0015!A\u0001\u0006\u0004A\u0003FA)>\u0011\u0015\t5\u00011\u0001Q\u0011\u0015\u00195\u00011\u0001Q\u0003\u0011\u0001X.\u001b8\u0016\u0005][Fc\u0001-`AR\u0011\u0011,\u0018\t\u0004;%S\u0006CA\f\\\t%YD\u0001)A\u0001\u0002\u000b\u0007\u0001\u0006\u000b\u0002\\{!)q\u0007\u0002a\u0002=B\u0019q\u0003\u0007.\t\u000b\u0005#\u0001\u0019\u0001.\t\u000b\r#\u0001\u0019\u0001.\u0002\tAl\u0017\r_\u000b\u0003G\u001e$2\u0001Z6m)\t)\u0017\u000eE\u0002\u001e\u0013\u001a\u0004\"aF4\u0005\u0013m*\u0001\u0015!A\u0001\u0006\u0004A\u0003FA4>\u0011\u00159T\u0001q\u0001k!\r9\u0002D\u001a\u0005\u0006\u0003\u0016\u0001\rA\u001a\u0005\u0006\u0007\u0016\u0001\rAZ\u0001\u0006YR,\u0017O^\u000b\u0003_^$2\u0001]={)\t\tH\u000f\u0005\u0002\u001ee&\u00111O\b\u0002\b\u0005>|G.Z1o\u0011\u00159d\u0001q\u0001v!\r9\u0002D\u001e\t\u0003/]$\u0011b\u000f\u0004!\u0002\u0003\u0005)\u0019\u0001\u0015)\u0005]l\u0004\"B!\u0007\u0001\u00041\b\"B\"\u0007\u0001\u00041\u0018A\u00017u+\ri\u0018Q\u0001\u000b\u0006}\u0006%\u00111\u0002\u000b\u0003c~DaaN\u0004A\u0004\u0005\u0005\u0001\u0003B\f\u0019\u0003\u0007\u00012aFA\u0003\t%Yt\u0001)A\u0001\u0002\u000b\u0007\u0001\u0006K\u0002\u0002\u0006uBa!Q\u0004A\u0002\u0005\r\u0001BB\"\b\u0001\u0004\t\u0019!A\u0003hi\u0016\fh/\u0006\u0003\u0002\u0012\u0005mACBA\n\u0003?\t\t\u0003F\u0002r\u0003+Aaa\u000e\u0005A\u0004\u0005]\u0001\u0003B\f\u0019\u00033\u00012aFA\u000e\t%Y\u0004\u0002)A\u0001\u0002\u000b\u0007\u0001\u0006K\u0002\u0002\u001cuBa!\u0011\u0005A\u0002\u0005e\u0001BB\"\t\u0001\u0004\tI\"\u0001\u0002hiV!\u0011qEA\u0019)\u0019\tI#!\u000e\u00028Q\u0019\u0011/a\u000b\t\r]J\u00019AA\u0017!\u00119\u0002$a\f\u0011\u0007]\t\t\u0004B\u0005<\u0013\u0001\u0006\t\u0011!b\u0001Q!\u001a\u0011\u0011G\u001f\t\r\u0005K\u0001\u0019AA\u0018\u0011\u0019\u0019\u0015\u00021\u0001\u00020\u0001"
)
public abstract class PartialOrderFunctions extends EqFunctions {
   public double partialCompare(final Object x, final Object y, final PartialOrder ev) {
      return ev.partialCompare(x, y);
   }

   public Option tryCompare(final Object x, final Object y, final PartialOrder ev) {
      return ev.tryCompare(x, y);
   }

   public Option pmin(final Object x, final Object y, final PartialOrder ev) {
      return ev.pmin(x, y);
   }

   public Option pmax(final Object x, final Object y, final PartialOrder ev) {
      return ev.pmax(x, y);
   }

   public boolean lteqv(final Object x, final Object y, final PartialOrder ev) {
      return ev.lteqv(x, y);
   }

   public boolean lt(final Object x, final Object y, final PartialOrder ev) {
      return ev.lt(x, y);
   }

   public boolean gteqv(final Object x, final Object y, final PartialOrder ev) {
      return ev.gteqv(x, y);
   }

   public boolean gt(final Object x, final Object y, final PartialOrder ev) {
      return ev.gt(x, y);
   }

   public double partialCompare$mZc$sp(final boolean x, final boolean y, final PartialOrder ev) {
      return ev.partialCompare$mcZ$sp(x, y);
   }

   public double partialCompare$mBc$sp(final byte x, final byte y, final PartialOrder ev) {
      return ev.partialCompare$mcB$sp(x, y);
   }

   public double partialCompare$mCc$sp(final char x, final char y, final PartialOrder ev) {
      return ev.partialCompare$mcC$sp(x, y);
   }

   public double partialCompare$mDc$sp(final double x, final double y, final PartialOrder ev) {
      return ev.partialCompare$mcD$sp(x, y);
   }

   public double partialCompare$mFc$sp(final float x, final float y, final PartialOrder ev) {
      return ev.partialCompare$mcF$sp(x, y);
   }

   public double partialCompare$mIc$sp(final int x, final int y, final PartialOrder ev) {
      return ev.partialCompare$mcI$sp(x, y);
   }

   public double partialCompare$mJc$sp(final long x, final long y, final PartialOrder ev) {
      return ev.partialCompare$mcJ$sp(x, y);
   }

   public double partialCompare$mSc$sp(final short x, final short y, final PartialOrder ev) {
      return ev.partialCompare$mcS$sp(x, y);
   }

   public double partialCompare$mVc$sp(final BoxedUnit x, final BoxedUnit y, final PartialOrder ev) {
      return ev.partialCompare$mcV$sp(x, y);
   }

   public Option tryCompare$mZc$sp(final boolean x, final boolean y, final PartialOrder ev) {
      return ev.tryCompare$mcZ$sp(x, y);
   }

   public Option tryCompare$mBc$sp(final byte x, final byte y, final PartialOrder ev) {
      return ev.tryCompare$mcB$sp(x, y);
   }

   public Option tryCompare$mCc$sp(final char x, final char y, final PartialOrder ev) {
      return ev.tryCompare$mcC$sp(x, y);
   }

   public Option tryCompare$mDc$sp(final double x, final double y, final PartialOrder ev) {
      return ev.tryCompare$mcD$sp(x, y);
   }

   public Option tryCompare$mFc$sp(final float x, final float y, final PartialOrder ev) {
      return ev.tryCompare$mcF$sp(x, y);
   }

   public Option tryCompare$mIc$sp(final int x, final int y, final PartialOrder ev) {
      return ev.tryCompare$mcI$sp(x, y);
   }

   public Option tryCompare$mJc$sp(final long x, final long y, final PartialOrder ev) {
      return ev.tryCompare$mcJ$sp(x, y);
   }

   public Option tryCompare$mSc$sp(final short x, final short y, final PartialOrder ev) {
      return ev.tryCompare$mcS$sp(x, y);
   }

   public Option tryCompare$mVc$sp(final BoxedUnit x, final BoxedUnit y, final PartialOrder ev) {
      return ev.tryCompare$mcV$sp(x, y);
   }

   public Option pmin$mZc$sp(final boolean x, final boolean y, final PartialOrder ev) {
      return ev.pmin$mcZ$sp(x, y);
   }

   public Option pmin$mBc$sp(final byte x, final byte y, final PartialOrder ev) {
      return ev.pmin$mcB$sp(x, y);
   }

   public Option pmin$mCc$sp(final char x, final char y, final PartialOrder ev) {
      return ev.pmin$mcC$sp(x, y);
   }

   public Option pmin$mDc$sp(final double x, final double y, final PartialOrder ev) {
      return ev.pmin$mcD$sp(x, y);
   }

   public Option pmin$mFc$sp(final float x, final float y, final PartialOrder ev) {
      return ev.pmin$mcF$sp(x, y);
   }

   public Option pmin$mIc$sp(final int x, final int y, final PartialOrder ev) {
      return ev.pmin$mcI$sp(x, y);
   }

   public Option pmin$mJc$sp(final long x, final long y, final PartialOrder ev) {
      return ev.pmin$mcJ$sp(x, y);
   }

   public Option pmin$mSc$sp(final short x, final short y, final PartialOrder ev) {
      return ev.pmin$mcS$sp(x, y);
   }

   public Option pmin$mVc$sp(final BoxedUnit x, final BoxedUnit y, final PartialOrder ev) {
      return ev.pmin$mcV$sp(x, y);
   }

   public Option pmax$mZc$sp(final boolean x, final boolean y, final PartialOrder ev) {
      return ev.pmax$mcZ$sp(x, y);
   }

   public Option pmax$mBc$sp(final byte x, final byte y, final PartialOrder ev) {
      return ev.pmax$mcB$sp(x, y);
   }

   public Option pmax$mCc$sp(final char x, final char y, final PartialOrder ev) {
      return ev.pmax$mcC$sp(x, y);
   }

   public Option pmax$mDc$sp(final double x, final double y, final PartialOrder ev) {
      return ev.pmax$mcD$sp(x, y);
   }

   public Option pmax$mFc$sp(final float x, final float y, final PartialOrder ev) {
      return ev.pmax$mcF$sp(x, y);
   }

   public Option pmax$mIc$sp(final int x, final int y, final PartialOrder ev) {
      return ev.pmax$mcI$sp(x, y);
   }

   public Option pmax$mJc$sp(final long x, final long y, final PartialOrder ev) {
      return ev.pmax$mcJ$sp(x, y);
   }

   public Option pmax$mSc$sp(final short x, final short y, final PartialOrder ev) {
      return ev.pmax$mcS$sp(x, y);
   }

   public Option pmax$mVc$sp(final BoxedUnit x, final BoxedUnit y, final PartialOrder ev) {
      return ev.pmax$mcV$sp(x, y);
   }

   public boolean lteqv$mZc$sp(final boolean x, final boolean y, final PartialOrder ev) {
      return ev.lteqv$mcZ$sp(x, y);
   }

   public boolean lteqv$mBc$sp(final byte x, final byte y, final PartialOrder ev) {
      return ev.lteqv$mcB$sp(x, y);
   }

   public boolean lteqv$mCc$sp(final char x, final char y, final PartialOrder ev) {
      return ev.lteqv$mcC$sp(x, y);
   }

   public boolean lteqv$mDc$sp(final double x, final double y, final PartialOrder ev) {
      return ev.lteqv$mcD$sp(x, y);
   }

   public boolean lteqv$mFc$sp(final float x, final float y, final PartialOrder ev) {
      return ev.lteqv$mcF$sp(x, y);
   }

   public boolean lteqv$mIc$sp(final int x, final int y, final PartialOrder ev) {
      return ev.lteqv$mcI$sp(x, y);
   }

   public boolean lteqv$mJc$sp(final long x, final long y, final PartialOrder ev) {
      return ev.lteqv$mcJ$sp(x, y);
   }

   public boolean lteqv$mSc$sp(final short x, final short y, final PartialOrder ev) {
      return ev.lteqv$mcS$sp(x, y);
   }

   public boolean lteqv$mVc$sp(final BoxedUnit x, final BoxedUnit y, final PartialOrder ev) {
      return ev.lteqv$mcV$sp(x, y);
   }

   public boolean lt$mZc$sp(final boolean x, final boolean y, final PartialOrder ev) {
      return ev.lt$mcZ$sp(x, y);
   }

   public boolean lt$mBc$sp(final byte x, final byte y, final PartialOrder ev) {
      return ev.lt$mcB$sp(x, y);
   }

   public boolean lt$mCc$sp(final char x, final char y, final PartialOrder ev) {
      return ev.lt$mcC$sp(x, y);
   }

   public boolean lt$mDc$sp(final double x, final double y, final PartialOrder ev) {
      return ev.lt$mcD$sp(x, y);
   }

   public boolean lt$mFc$sp(final float x, final float y, final PartialOrder ev) {
      return ev.lt$mcF$sp(x, y);
   }

   public boolean lt$mIc$sp(final int x, final int y, final PartialOrder ev) {
      return ev.lt$mcI$sp(x, y);
   }

   public boolean lt$mJc$sp(final long x, final long y, final PartialOrder ev) {
      return ev.lt$mcJ$sp(x, y);
   }

   public boolean lt$mSc$sp(final short x, final short y, final PartialOrder ev) {
      return ev.lt$mcS$sp(x, y);
   }

   public boolean lt$mVc$sp(final BoxedUnit x, final BoxedUnit y, final PartialOrder ev) {
      return ev.lt$mcV$sp(x, y);
   }

   public boolean gteqv$mZc$sp(final boolean x, final boolean y, final PartialOrder ev) {
      return ev.gteqv$mcZ$sp(x, y);
   }

   public boolean gteqv$mBc$sp(final byte x, final byte y, final PartialOrder ev) {
      return ev.gteqv$mcB$sp(x, y);
   }

   public boolean gteqv$mCc$sp(final char x, final char y, final PartialOrder ev) {
      return ev.gteqv$mcC$sp(x, y);
   }

   public boolean gteqv$mDc$sp(final double x, final double y, final PartialOrder ev) {
      return ev.gteqv$mcD$sp(x, y);
   }

   public boolean gteqv$mFc$sp(final float x, final float y, final PartialOrder ev) {
      return ev.gteqv$mcF$sp(x, y);
   }

   public boolean gteqv$mIc$sp(final int x, final int y, final PartialOrder ev) {
      return ev.gteqv$mcI$sp(x, y);
   }

   public boolean gteqv$mJc$sp(final long x, final long y, final PartialOrder ev) {
      return ev.gteqv$mcJ$sp(x, y);
   }

   public boolean gteqv$mSc$sp(final short x, final short y, final PartialOrder ev) {
      return ev.gteqv$mcS$sp(x, y);
   }

   public boolean gteqv$mVc$sp(final BoxedUnit x, final BoxedUnit y, final PartialOrder ev) {
      return ev.gteqv$mcV$sp(x, y);
   }

   public boolean gt$mZc$sp(final boolean x, final boolean y, final PartialOrder ev) {
      return ev.gt$mcZ$sp(x, y);
   }

   public boolean gt$mBc$sp(final byte x, final byte y, final PartialOrder ev) {
      return ev.gt$mcB$sp(x, y);
   }

   public boolean gt$mCc$sp(final char x, final char y, final PartialOrder ev) {
      return ev.gt$mcC$sp(x, y);
   }

   public boolean gt$mDc$sp(final double x, final double y, final PartialOrder ev) {
      return ev.gt$mcD$sp(x, y);
   }

   public boolean gt$mFc$sp(final float x, final float y, final PartialOrder ev) {
      return ev.gt$mcF$sp(x, y);
   }

   public boolean gt$mIc$sp(final int x, final int y, final PartialOrder ev) {
      return ev.gt$mcI$sp(x, y);
   }

   public boolean gt$mJc$sp(final long x, final long y, final PartialOrder ev) {
      return ev.gt$mcJ$sp(x, y);
   }

   public boolean gt$mSc$sp(final short x, final short y, final PartialOrder ev) {
      return ev.gt$mcS$sp(x, y);
   }

   public boolean gt$mVc$sp(final BoxedUnit x, final BoxedUnit y, final PartialOrder ev) {
      return ev.gt$mcV$sp(x, y);
   }
}
