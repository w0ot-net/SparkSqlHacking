package breeze.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3A\u0001C\u0005\u0001\u001d!A\u0011\u0005\u0001B\u0001B\u0003%!\u0005C\u0003+\u0001\u0011\u00051fB\u0004/\u0013\u0005\u0005\t\u0012A\u0018\u0007\u000f!I\u0011\u0011!E\u0001a!)!\u0006\u0002C\u0001{!9a\bBI\u0001\n\u0003y\u0004b\u0002&\u0005\u0003\u0003%Ia\u0013\u0002\u0018\u001b\u0006$(/\u001b=TS:<W\u000f\\1s\u000bb\u001cW\r\u001d;j_:T!AC\u0006\u0002\r1Lg.\u00197h\u0015\u0005a\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001yQ\u0004\u0005\u0002\u001159\u0011\u0011c\u0006\b\u0003%Ui\u0011a\u0005\u0006\u0003)5\ta\u0001\u0010:p_Rt\u0014\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005aI\u0012a\u00029bG.\fw-\u001a\u0006\u0002-%\u00111\u0004\b\u0002\u0011%VtG/[7f\u000bb\u001cW\r\u001d;j_:T!\u0001G\r\u0011\u0005yyR\"A\u0005\n\u0005\u0001J!A\u0006'j]\u0016\f'/\u00117hK\n\u0014\u0018-\u0012=dKB$\u0018n\u001c8\u0002\u00075\u001cx\r\u0005\u0002$O9\u0011A%\n\t\u0003%eI!AJ\r\u0002\rA\u0013X\rZ3g\u0013\tA\u0013F\u0001\u0004TiJLgn\u001a\u0006\u0003Me\ta\u0001P5oSRtDC\u0001\u0017.!\tq\u0002\u0001C\u0004\"\u0005A\u0005\t\u0019\u0001\u0012\u0002/5\u000bGO]5y'&tw-\u001e7be\u0016C8-\u001a9uS>t\u0007C\u0001\u0010\u0005'\r!\u0011'\u000e\t\u0003eMj\u0011!G\u0005\u0003ie\u0011a!\u00118z%\u00164\u0007C\u0001\u001c<\u001b\u00059$B\u0001\u001d:\u0003\tIwNC\u0001;\u0003\u0011Q\u0017M^1\n\u0005q:$\u0001D*fe&\fG.\u001b>bE2,G#A\u0018\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0001%F\u0001\u0012BW\u0005\u0011\u0005CA\"I\u001b\u0005!%BA#G\u0003%)hn\u00195fG.,GM\u0003\u0002H3\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005%#%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\tA\n\u0005\u0002N!6\taJ\u0003\u0002Ps\u0005!A.\u00198h\u0013\t\tfJ\u0001\u0004PE*,7\r\u001e"
)
public class MatrixSingularException extends RuntimeException implements LinearAlgebraException {
   public static String $lessinit$greater$default$1() {
      return MatrixSingularException$.MODULE$.$lessinit$greater$default$1();
   }

   public MatrixSingularException(final String msg) {
      super(msg);
   }
}
