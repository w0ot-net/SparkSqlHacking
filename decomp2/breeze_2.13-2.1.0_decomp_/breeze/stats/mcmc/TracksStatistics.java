package breeze.stats.mcmc;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013\u0001\u0002C\u0005\u0011\u0002\u0007\u0005\u0001C\u000b\u0005\u0006/\u0001!\t\u0001\u0007\u0005\u00069\u00011\t!\b\u0005\u0006C\u00011\t!\b\u0005\u0006E\u00011\t!\b\u0005\u0006G\u0001!\t!\b\u0005\u0006I\u0001!\t!\n\u0005\u0006S\u0001!\t!\n\u0002\u0011)J\f7m[:Ti\u0006$\u0018n\u001d;jGNT!AC\u0006\u0002\t5\u001cWn\u0019\u0006\u0003\u00195\tQa\u001d;biNT\u0011AD\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001!\u0005\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005I\u0002C\u0001\n\u001b\u0013\tY2C\u0001\u0003V]&$\u0018!\u0002;pi\u0006dW#\u0001\u0010\u0011\u0005Iy\u0012B\u0001\u0011\u0014\u0005\u0011auN\\4\u0002\u001f\u0005\u001c7-\u001a9uC:\u001cWmQ8v]R\fQ\"\u00192pm\u0016|e.Z\"pk:$\u0018A\u0004:fU\u0016\u001cG/[8o\u0007>,h\u000e^\u0001\rC\n|g/Z(oK\u001a\u0013\u0018mY\u000b\u0002MA\u0011!cJ\u0005\u0003QM\u0011a\u0001R8vE2,\u0017!\u0004:fU\u0016\u001cG/[8o\rJ\f7ME\u0002,_E2A\u0001\f\u0001\u0001U\taAH]3gS:,W.\u001a8u})\u0011afD\u0001\u0007yI|w\u000e\u001e \u0011\u0005A\u0002Q\"A\u00051\u0005I:\u0004c\u0001\u00194k%\u0011A'\u0003\u0002\u0013\u001b\u0016$(o\u001c9pY&\u001c\b*Y:uS:<7\u000f\u0005\u00027o1\u0001A!\u0003\u001d\u0001\u0003\u0003\u0005\tQ!\u0001:\u0005\ryF%M\t\u0003uu\u0002\"AE\u001e\n\u0005q\u001a\"a\u0002(pi\"Lgn\u001a\t\u0003%yJ!aP\n\u0003\u0007\u0005s\u0017\u0010"
)
public interface TracksStatistics {
   long total();

   long acceptanceCount();

   long aboveOneCount();

   // $FF: synthetic method
   static long rejectionCount$(final TracksStatistics $this) {
      return $this.rejectionCount();
   }

   default long rejectionCount() {
      return this.total() - this.acceptanceCount();
   }

   // $FF: synthetic method
   static double aboveOneFrac$(final TracksStatistics $this) {
      return $this.aboveOneFrac();
   }

   default double aboveOneFrac() {
      return (double)this.aboveOneCount() / (double)this.total();
   }

   // $FF: synthetic method
   static double rejectionFrac$(final TracksStatistics $this) {
      return $this.rejectionFrac();
   }

   default double rejectionFrac() {
      return (double)this.rejectionCount() / (double)this.total();
   }

   static void $init$(final TracksStatistics $this) {
   }
}
