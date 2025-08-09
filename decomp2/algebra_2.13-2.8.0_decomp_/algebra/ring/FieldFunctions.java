package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0011G\u0001\bGS\u0016dGMR;oGRLwN\\:\u000b\u0005\u00151\u0011\u0001\u0002:j]\u001eT\u0011aB\u0001\bC2<WM\u0019:b\u0007\u0001)\"AC\f\u0014\t\u0001Y\u0011\u0003\u000b\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007I\u0019R#D\u0001\u0005\u0013\t!BA\u0001\fFk\u000ed\u0017\u000eZ3b]JKgn\u001a$v]\u000e$\u0018n\u001c8t!\t1r\u0003\u0004\u0001\u0005\u000ba\u0001!\u0019A\r\u0003\u0003\u0019+\"A\u0007\u0012\u0012\u0005mq\u0002C\u0001\u0007\u001d\u0013\tiRBA\u0004O_RD\u0017N\\4\u0011\u0007Iy\u0012%\u0003\u0002!\t\t)a)[3mIB\u0011aC\t\u0003\u0006G]\u0011\r\u0001\n\u0002\u0002)F\u00111$\n\t\u0003\u0019\u0019J!aJ\u0007\u0003\u0007\u0005s\u0017\u0010E\u0002\u0013SUI!A\u000b\u0003\u000395+H\u000e^5qY&\u001c\u0017\r^5wK\u001e\u0013x.\u001e9Gk:\u001cG/[8og\u00061A%\u001b8ji\u0012\"\u0012!\f\t\u0003\u00199J!aL\u0007\u0003\tUs\u0017\u000e^\u0001\u000bMJ|W\u000eR8vE2,WC\u0001\u001a6)\t\u0019t\u000b\u0006\u00025)B\u0011a#\u000e\u0003\nm\t\u0001\u000b\u0011!AC\u0002\u0011\u0012\u0011!\u0011\u0015\u0007kaZTIS(\u0011\u00051I\u0014B\u0001\u001e\u000e\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rbTh\u0010 \u000f\u00051i\u0014B\u0001 \u000e\u0003\rIe\u000e^\u0019\u0005I\u0001#eB\u0004\u0002B\t6\t!I\u0003\u0002D\u0011\u00051AH]8pizJ\u0011AD\u0019\u0006G\u0019;\u0015\n\u0013\b\u0003\u0019\u001dK!\u0001S\u0007\u0002\t1{gnZ\u0019\u0005I\u0001#e\"M\u0003$\u00172sUJ\u0004\u0002\r\u0019&\u0011Q*D\u0001\u0006\r2|\u0017\r^\u0019\u0005I\u0001#e\"M\u0003$!F\u001b&K\u0004\u0002\r#&\u0011!+D\u0001\u0007\t>,(\r\\32\t\u0011\u0002EI\u0004\u0005\u0006+\n\u0001\u001dAV\u0001\u0003KZ\u00042AF\f5\u0011\u0015A&\u00011\u0001Z\u0003\u0005q\u0007C\u0001\u0007[\u0013\tYVB\u0001\u0004E_V\u0014G.\u001a"
)
public interface FieldFunctions extends EuclideanRingFunctions, MultiplicativeGroupFunctions {
   // $FF: synthetic method
   static Object fromDouble$(final FieldFunctions $this, final double n, final Field ev) {
      return $this.fromDouble(n, ev);
   }

   default Object fromDouble(final double n, final Field ev) {
      return ev.fromDouble(n);
   }

   // $FF: synthetic method
   static double fromDouble$mDc$sp$(final FieldFunctions $this, final double n, final Field ev) {
      return $this.fromDouble$mDc$sp(n, ev);
   }

   default double fromDouble$mDc$sp(final double n, final Field ev) {
      return ev.fromDouble$mcD$sp(n);
   }

   // $FF: synthetic method
   static float fromDouble$mFc$sp$(final FieldFunctions $this, final double n, final Field ev) {
      return $this.fromDouble$mFc$sp(n, ev);
   }

   default float fromDouble$mFc$sp(final double n, final Field ev) {
      return ev.fromDouble$mcF$sp(n);
   }

   // $FF: synthetic method
   static int fromDouble$mIc$sp$(final FieldFunctions $this, final double n, final Field ev) {
      return $this.fromDouble$mIc$sp(n, ev);
   }

   default int fromDouble$mIc$sp(final double n, final Field ev) {
      return ev.fromDouble$mcI$sp(n);
   }

   // $FF: synthetic method
   static long fromDouble$mJc$sp$(final FieldFunctions $this, final double n, final Field ev) {
      return $this.fromDouble$mJc$sp(n, ev);
   }

   default long fromDouble$mJc$sp(final double n, final Field ev) {
      return ev.fromDouble$mcJ$sp(n);
   }

   static void $init$(final FieldFunctions $this) {
   }
}
