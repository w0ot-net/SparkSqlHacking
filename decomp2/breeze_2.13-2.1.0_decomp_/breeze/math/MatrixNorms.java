package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3q!\u0002\u0004\u0011\u0002G\u00051\u0002C\u0003\u0014\u0001\u0019\rA\u0003C\u0003<\u0001\u0019\rA\bC\u0003D\u0001\u0019\rA\tC\u0003I\u0001\u0019\r\u0011JA\u0006NCR\u0014\u0018\u000e\u001f(pe6\u001c(BA\u0004\t\u0003\u0011i\u0017\r\u001e5\u000b\u0003%\taA\u0019:fKj,7\u0001A\u000b\u0004\u0019\u0011\n6C\u0001\u0001\u000e!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fM\u0006Y1-\u00198O_Jlw,\u00138u)\t)2\u0007E\u0003\u00179\tj\u0003G\u0004\u0002\u001855\t\u0001D\u0003\u0002\u001a\u0011\u00051A.\u001b8bY\u001eL!a\u0007\r\u0002\t9|'/\\\u0005\u0003;y\u0011Q!S7qYJJ!a\b\u0011\u0003\u000bU3UO\\2\u000b\u0005\u0005B\u0011aB4f]\u0016\u0014\u0018n\u0019\t\u0003G\u0011b\u0001\u0001B\u0003&\u0001\t\u0007aEA\u0001N#\t9#\u0006\u0005\u0002\u000fQ%\u0011\u0011f\u0004\u0002\b\u001d>$\b.\u001b8h!\tq1&\u0003\u0002-\u001f\t\u0019\u0011I\\=\u0011\u00059q\u0013BA\u0018\u0010\u0005\rIe\u000e\u001e\t\u0003\u001dEJ!AM\b\u0003\r\u0011{WO\u00197f\u0011\u0015!\u0014\u0001q\u00016\u0003\u0011IG/\u001a:\u0011\tYJ$%L\u0007\u0002o)\u0011\u0001\bG\u0001\bgV\u0004\bo\u001c:u\u0013\tQtGA\tDC:$&/\u0019<feN,g+\u00197vKN\fQbY1o\u001d>\u0014Xn\u0018$m_\u0006$HCA\u001fB!\u00151BD\t 1!\tqq(\u0003\u0002A\u001f\t)a\t\\8bi\")AG\u0001a\u0002\u0005B!a'\u000f\u0012?\u00039\u0019\u0017M\u001c(pe6|Fi\\;cY\u0016$\"!\u0012$\u0011\u000bYa\"\u0005\r\u0019\t\u000bQ\u001a\u00019A$\u0011\tYJ$\u0005M\u0001\u000eG\u0006tgj\u001c:n?\u001aKW\r\u001c3\u0015\u0005\u0015S\u0005\"B&\u0005\u0001\ba\u0015!\u00024jK2$\u0007cA'O!6\ta!\u0003\u0002P\r\t)a)[3mIB\u00111%\u0015\u0003\u0006%\u0002\u0011\rA\n\u0002\u0002'\u0002"
)
public interface MatrixNorms {
   UFunc.UImpl2 canNorm_Int(final CanTraverseValues iter);

   UFunc.UImpl2 canNorm_Float(final CanTraverseValues iter);

   UFunc.UImpl2 canNorm_Double(final CanTraverseValues iter);

   UFunc.UImpl2 canNorm_Field(final Field field);
}
