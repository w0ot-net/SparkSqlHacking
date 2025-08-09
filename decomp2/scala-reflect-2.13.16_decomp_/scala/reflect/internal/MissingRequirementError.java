package scala.reflect.internal;

import scala.Option;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054A!\u0004\b\u0001+!I!\u0004\u0001B\u0001B\u0003%1D\n\u0005\u0006O\u0001!I\u0001\u000b\u0005\u0006W\u0001!\t\u0001L\u0004\u0006[9A\tA\f\u0004\u0006\u001b9A\ta\f\u0005\u0006O\u0015!\t\u0001\u0010\u0005\b{\u0015\u0011\r\u0011\"\u0003?\u0011\u0019!U\u0001)A\u0005\u007f!)Q)\u0002C\u0001\r\")1*\u0002C\u0001\u0019\")a*\u0002C\u0001\u001f\"9A,BA\u0001\n\u0013i&aF'jgNLgn\u001a*fcVL'/Z7f]R,%O]8s\u0015\ty\u0001#\u0001\u0005j]R,'O\\1m\u0015\t\t\"#A\u0004sK\u001adWm\u0019;\u000b\u0003M\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001-A\u0011q\u0003G\u0007\u0002\u001d%\u0011\u0011D\u0004\u0002\u000b\r\u0006$\u0018\r\\#se>\u0014\u0018aA7tOB\u0011Ad\t\b\u0003;\u0005\u0002\"A\b\n\u000e\u0003}Q!\u0001\t\u000b\u0002\rq\u0012xn\u001c;?\u0013\t\u0011##\u0001\u0004Qe\u0016$WMZ\u0005\u0003I\u0015\u0012aa\u0015;sS:<'B\u0001\u0012\u0013\u0013\tQ\u0002$\u0001\u0004=S:LGO\u0010\u000b\u0003S)\u0002\"a\u0006\u0001\t\u000bi\u0011\u0001\u0019A\u000e\u0002\u0007I,\u0017/F\u0001\u001c\u0003]i\u0015n]:j]\u001e\u0014V-];je\u0016lWM\u001c;FeJ|'\u000f\u0005\u0002\u0018\u000bM\u0019Q\u0001\r\u001b\u0011\u0005E\u0012T\"\u0001\n\n\u0005M\u0012\"AB!osJ+g\r\u0005\u00026u5\taG\u0003\u00028q\u0005\u0011\u0011n\u001c\u0006\u0002s\u0005!!.\u0019<b\u0013\tYdG\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001/\u0003\u0019\u0019XO\u001a4jqV\tq\b\u0005\u0002A\u00076\t\u0011I\u0003\u0002Cq\u0005!A.\u00198h\u0013\t!\u0013)A\u0004tk\u001a4\u0017\u000e\u001f\u0011\u0002\rMLwM\\1m)\t9%\n\u0005\u00022\u0011&\u0011\u0011J\u0005\u0002\b\u001d>$\b.\u001b8h\u0011\u0015Q\u0012\u00021\u0001\u001c\u0003!qw\u000e\u001e$pk:$GCA$N\u0011\u0015Y#\u00021\u0001\u001c\u0003\u001d)h.\u00199qYf$\"\u0001U*\u0011\u0007E\n6$\u0003\u0002S%\t1q\n\u001d;j_:DQ\u0001V\u0006A\u0002U\u000b\u0011\u0001\u001f\t\u0003-fs!!M,\n\u0005a\u0013\u0012a\u00029bG.\fw-Z\u0005\u00035n\u0013\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0005a\u0013\u0012\u0001D<sSR,'+\u001a9mC\u000e,G#\u00010\u0011\u0005\u0001{\u0016B\u00011B\u0005\u0019y%M[3di\u0002"
)
public class MissingRequirementError extends FatalError {
   public static Option unapply(final Throwable x) {
      return MissingRequirementError$.MODULE$.unapply(x);
   }

   public static Nothing notFound(final String req) {
      return MissingRequirementError$.MODULE$.notFound(req);
   }

   public static Nothing signal(final String msg) {
      MissingRequirementError$ var10000 = MissingRequirementError$.MODULE$;
      throw new MissingRequirementError(msg);
   }

   public String req() {
      return super.msg().endsWith(MissingRequirementError$.MODULE$.scala$reflect$internal$MissingRequirementError$$suffix()) ? .MODULE$.dropRight$extension(super.msg(), MissingRequirementError$.MODULE$.scala$reflect$internal$MissingRequirementError$$suffix().length()) : super.msg();
   }

   public MissingRequirementError(final String msg) {
      super(msg);
   }
}
