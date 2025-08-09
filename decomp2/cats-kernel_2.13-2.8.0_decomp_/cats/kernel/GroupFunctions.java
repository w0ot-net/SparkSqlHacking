package cats.kernel;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Q\u0001B\u0003\u0002\u0002)AQA\n\u0001\u0005\u0002\u001dBQ!\u000b\u0001\u0005\u0002)BQA\u0015\u0001\u0005\u0002M\u0013ab\u0012:pkB4UO\\2uS>t7O\u0003\u0002\u0007\u000f\u000511.\u001a:oK2T\u0011\u0001C\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u0005-\u00112C\u0001\u0001\r!\ria\u0002E\u0007\u0002\u000b%\u0011q\"\u0002\u0002\u0010\u001b>tw.\u001b3Gk:\u001cG/[8ogB\u0011\u0011C\u0005\u0007\u0001\t\u0015\u0019\u0002A1\u0001\u0015\u0005\u00059UCA\u000b!#\t1B\u0004\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002DA\u0004O_RD\u0017N\\4\u0011\u00075ir$\u0003\u0002\u001f\u000b\t)qI]8vaB\u0011\u0011\u0003\t\u0003\u0006CI\u0011\rA\t\u0002\u0002)F\u0011ac\t\t\u0003/\u0011J!!\n\r\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0002QA\u0019Q\u0002\u0001\t\u0002\u000f%tg/\u001a:tKV\u00111F\f\u000b\u0003YA#\"!L'\u0011\u0005EqC!C\u0018\u0003A\u0003\u0005\tQ1\u0001#\u0005\u0005\t\u0005F\u0002\u00182iy\u001a\u0005\n\u0005\u0002\u0018e%\u00111\u0007\u0007\u0002\fgB,7-[1mSj,G-M\u0003$kYBtG\u0004\u0002\u0018m%\u0011q\u0007G\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013:{eq!AO\u001f\u000e\u0003mR!\u0001P\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0012'B\u0012@\u0001\n\u000beBA\fA\u0013\t\t\u0005$\u0001\u0003M_:<\u0017\u0007\u0002\u0013:{e\tTa\t#F\u000f\u001as!aF#\n\u0005\u0019C\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013:{e\tTaI%K\u0019.s!a\u0006&\n\u0005-C\u0012A\u0002#pk\ndW-\r\u0003%suJ\u0002\"\u0002(\u0003\u0001\by\u0015AA3w!\r\t\"#\f\u0005\u0006#\n\u0001\r!L\u0001\u0002C\u00061!/Z7pm\u0016,\"\u0001V,\u0015\u0007U\u001bW\r\u0006\u0002WCB\u0011\u0011c\u0016\u0003\n_\r\u0001\u000b\u0011!AC\u0002\tBcaV\u0019Z7v{\u0016'B\u00126mi;\u0014\u0007\u0002\u0013:{e\tTaI A9\u0006\u000bD\u0001J\u001d>3E*1\u0005R#_\rF\"A%O\u001f\u001ac\u0015\u0019\u0013J\u00131Lc\u0011!\u0013(P\r\t\u000b9\u001b\u00019\u00012\u0011\u0007E\u0011b\u000bC\u0003e\u0007\u0001\u0007a+A\u0001y\u0011\u001517\u00011\u0001W\u0003\u0005I\b"
)
public abstract class GroupFunctions extends MonoidFunctions {
   public Object inverse(final Object a, final Group ev) {
      return ev.inverse(a);
   }

   public Object remove(final Object x, final Object y, final Group ev) {
      return ev.remove(x, y);
   }

   public double inverse$mDc$sp(final double a, final Group ev) {
      return ev.inverse$mcD$sp(a);
   }

   public float inverse$mFc$sp(final float a, final Group ev) {
      return ev.inverse$mcF$sp(a);
   }

   public int inverse$mIc$sp(final int a, final Group ev) {
      return ev.inverse$mcI$sp(a);
   }

   public long inverse$mJc$sp(final long a, final Group ev) {
      return ev.inverse$mcJ$sp(a);
   }

   public double remove$mDc$sp(final double x, final double y, final Group ev) {
      return ev.remove$mcD$sp(x, y);
   }

   public float remove$mFc$sp(final float x, final float y, final Group ev) {
      return ev.remove$mcF$sp(x, y);
   }

   public int remove$mIc$sp(final int x, final int y, final Group ev) {
      return ev.remove$mcI$sp(x, y);
   }

   public long remove$mJc$sp(final long x, final long y, final Group ev) {
      return ev.remove$mcJ$sp(x, y);
   }
}
