package cats.kernel;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114QAB\u0004\u0002\u00021AQ\u0001\u000b\u0001\u0005\u0002%BQa\u000b\u0001\u0005\u00021BQ\u0001\u0011\u0001\u0005\u0002\u0005CQa\u0013\u0001\u0005\u00021CQA\u0016\u0001\u0005\u0002]\u0013ab\u0014:eKJ4UO\\2uS>t7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u00055!2C\u0001\u0001\u000f!\ry\u0001CE\u0007\u0002\u000f%\u0011\u0011c\u0002\u0002\u0016!\u0006\u0014H/[1m\u001fJ$WM\u001d$v]\u000e$\u0018n\u001c8t!\t\u0019B\u0003\u0004\u0001\u0005\u000bU\u0001!\u0019\u0001\f\u0003\u0003=+\"a\u0006\u0012\u0012\u0005aq\u0002CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"a\u0002(pi\"Lgn\u001a\t\u0004\u001f}\t\u0013B\u0001\u0011\b\u0005\u0015y%\u000fZ3s!\t\u0019\"\u0005B\u0003$)\t\u0007AEA\u0001U#\tAR\u0005\u0005\u0002\u001aM%\u0011qE\u0007\u0002\u0004\u0003:L\u0018A\u0002\u001fj]&$h\bF\u0001+!\ry\u0001AE\u0001\bG>l\u0007/\u0019:f+\tic\u0007F\u0002/yy\"\"a\f\u001a\u0011\u0005e\u0001\u0014BA\u0019\u001b\u0005\rIe\u000e\u001e\u0005\u0006g\t\u0001\u001d\u0001N\u0001\u0003KZ\u00042a\u0005\u000b6!\t\u0019b\u0007B\u00058\u0005\u0001\u0006\t\u0011!b\u0001I\t\t\u0011\t\u000b\u00027sA\u0011\u0011DO\u0005\u0003wi\u00111b\u001d9fG&\fG.\u001b>fI\")QH\u0001a\u0001k\u0005\t\u0001\u0010C\u0003@\u0005\u0001\u0007Q'A\u0001z\u0003\ri\u0017N\\\u000b\u0003\u0005\u0016#2aQ%K)\t!u\t\u0005\u0002\u0014\u000b\u0012Iqg\u0001Q\u0001\u0002\u0003\u0015\r\u0001\n\u0015\u0003\u000bfBQaM\u0002A\u0004!\u00032a\u0005\u000bE\u0011\u0015i4\u00011\u0001E\u0011\u0015y4\u00011\u0001E\u0003\ri\u0017\r_\u000b\u0003\u001bB#2A\u0014+V)\ty%\u000b\u0005\u0002\u0014!\u0012Iq\u0007\u0002Q\u0001\u0002\u0003\u0015\r\u0001\n\u0015\u0003!fBQa\r\u0003A\u0004M\u00032a\u0005\u000bP\u0011\u0015iD\u00011\u0001P\u0011\u0015yD\u00011\u0001P\u0003)\u0019w.\u001c9be&\u001cxN\\\u000b\u00031\u0002$2!\u00172d)\tQV\f\u0005\u0002\u00107&\u0011Al\u0002\u0002\u000b\u0007>l\u0007/\u0019:jg>t\u0007\"B\u001a\u0006\u0001\bq\u0006cA\n\u0015?B\u00111\u0003\u0019\u0003\no\u0015\u0001\u000b\u0011!AC\u0002\u0011B#\u0001Y\u001d\t\u000bu*\u0001\u0019A0\t\u000b}*\u0001\u0019A0"
)
public abstract class OrderFunctions extends PartialOrderFunctions {
   public int compare(final Object x, final Object y, final Order ev) {
      return ev.compare(x, y);
   }

   public Object min(final Object x, final Object y, final Order ev) {
      return ev.min(x, y);
   }

   public Object max(final Object x, final Object y, final Order ev) {
      return ev.max(x, y);
   }

   public Comparison comparison(final Object x, final Object y, final Order ev) {
      return ev.comparison(x, y);
   }

   public int compare$mZc$sp(final boolean x, final boolean y, final Order ev) {
      return ev.compare$mcZ$sp(x, y);
   }

   public int compare$mBc$sp(final byte x, final byte y, final Order ev) {
      return ev.compare$mcB$sp(x, y);
   }

   public int compare$mCc$sp(final char x, final char y, final Order ev) {
      return ev.compare$mcC$sp(x, y);
   }

   public int compare$mDc$sp(final double x, final double y, final Order ev) {
      return ev.compare$mcD$sp(x, y);
   }

   public int compare$mFc$sp(final float x, final float y, final Order ev) {
      return ev.compare$mcF$sp(x, y);
   }

   public int compare$mIc$sp(final int x, final int y, final Order ev) {
      return ev.compare$mcI$sp(x, y);
   }

   public int compare$mJc$sp(final long x, final long y, final Order ev) {
      return ev.compare$mcJ$sp(x, y);
   }

   public int compare$mSc$sp(final short x, final short y, final Order ev) {
      return ev.compare$mcS$sp(x, y);
   }

   public int compare$mVc$sp(final BoxedUnit x, final BoxedUnit y, final Order ev) {
      return ev.compare$mcV$sp(x, y);
   }

   public boolean min$mZc$sp(final boolean x, final boolean y, final Order ev) {
      return ev.min$mcZ$sp(x, y);
   }

   public byte min$mBc$sp(final byte x, final byte y, final Order ev) {
      return ev.min$mcB$sp(x, y);
   }

   public char min$mCc$sp(final char x, final char y, final Order ev) {
      return ev.min$mcC$sp(x, y);
   }

   public double min$mDc$sp(final double x, final double y, final Order ev) {
      return ev.min$mcD$sp(x, y);
   }

   public float min$mFc$sp(final float x, final float y, final Order ev) {
      return ev.min$mcF$sp(x, y);
   }

   public int min$mIc$sp(final int x, final int y, final Order ev) {
      return ev.min$mcI$sp(x, y);
   }

   public long min$mJc$sp(final long x, final long y, final Order ev) {
      return ev.min$mcJ$sp(x, y);
   }

   public short min$mSc$sp(final short x, final short y, final Order ev) {
      return ev.min$mcS$sp(x, y);
   }

   public void min$mVc$sp(final BoxedUnit x, final BoxedUnit y, final Order ev) {
      ev.min$mcV$sp(x, y);
   }

   public boolean max$mZc$sp(final boolean x, final boolean y, final Order ev) {
      return ev.max$mcZ$sp(x, y);
   }

   public byte max$mBc$sp(final byte x, final byte y, final Order ev) {
      return ev.max$mcB$sp(x, y);
   }

   public char max$mCc$sp(final char x, final char y, final Order ev) {
      return ev.max$mcC$sp(x, y);
   }

   public double max$mDc$sp(final double x, final double y, final Order ev) {
      return ev.max$mcD$sp(x, y);
   }

   public float max$mFc$sp(final float x, final float y, final Order ev) {
      return ev.max$mcF$sp(x, y);
   }

   public int max$mIc$sp(final int x, final int y, final Order ev) {
      return ev.max$mcI$sp(x, y);
   }

   public long max$mJc$sp(final long x, final long y, final Order ev) {
      return ev.max$mcJ$sp(x, y);
   }

   public short max$mSc$sp(final short x, final short y, final Order ev) {
      return ev.max$mcS$sp(x, y);
   }

   public void max$mVc$sp(final BoxedUnit x, final BoxedUnit y, final Order ev) {
      ev.max$mcV$sp(x, y);
   }

   public Comparison comparison$mZc$sp(final boolean x, final boolean y, final Order ev) {
      return ev.comparison$mcZ$sp(x, y);
   }

   public Comparison comparison$mBc$sp(final byte x, final byte y, final Order ev) {
      return ev.comparison$mcB$sp(x, y);
   }

   public Comparison comparison$mCc$sp(final char x, final char y, final Order ev) {
      return ev.comparison$mcC$sp(x, y);
   }

   public Comparison comparison$mDc$sp(final double x, final double y, final Order ev) {
      return ev.comparison$mcD$sp(x, y);
   }

   public Comparison comparison$mFc$sp(final float x, final float y, final Order ev) {
      return ev.comparison$mcF$sp(x, y);
   }

   public Comparison comparison$mIc$sp(final int x, final int y, final Order ev) {
      return ev.comparison$mcI$sp(x, y);
   }

   public Comparison comparison$mJc$sp(final long x, final long y, final Order ev) {
      return ev.comparison$mcJ$sp(x, y);
   }

   public Comparison comparison$mSc$sp(final short x, final short y, final Order ev) {
      return ev.comparison$mcS$sp(x, y);
   }

   public Comparison comparison$mVc$sp(final BoxedUnit x, final BoxedUnit y, final Order ev) {
      return ev.comparison$mcV$sp(x, y);
   }
}
