package breeze.math;

import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2q!\u0002\u0004\u0011\u0002G\u00051\u0002C\u0003\u0014\u0001\u0019\rA\u0003C\u0003,\u0001\u0019\rA\u0006C\u00031\u0001\u0019\r\u0011\u0007C\u00036\u0001\u0019\raGA\u0006D_>\u0014H-\u001b8bi\u0016$'BA\u0004\t\u0003\u0011i\u0017\r\u001e5\u000b\u0003%\taA\u0019:fKj,7\u0001A\u000b\u0004\u0019}I3C\u0001\u0001\u000e!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fM\u0006A1oY1mCJ|e-F\u0001\u0016!\u001112$\b\u0015\u000e\u0003]Q!\u0001G\r\u0002\u000fM,\b\u000f]8si*\u0011!\u0004C\u0001\u0007Y&t\u0017\r\\4\n\u0005q9\"\u0001C*dC2\f'o\u00144\u0011\u0005yyB\u0002\u0001\u0003\u0006A\u0001\u0011\r!\t\u0002\u0002-F\u0011!%\n\t\u0003\u001d\rJ!\u0001J\b\u0003\u000f9{G\u000f[5oOB\u0011aBJ\u0005\u0003O=\u00111!\u00118z!\tq\u0012\u0006B\u0003+\u0001\t\u0007\u0011EA\u0001T\u0003%i\u0017\r\u001d,bYV,7/F\u0001.!\u00191b&\b\u0015);%\u0011qf\u0006\u0002\r\u0007\u0006tW*\u00199WC2,Xm]\u0001\ru&\u0004X*\u00199WC2,Xm]\u000b\u0002eA1acM\u000f)QuI!\u0001N\f\u0003\u001f\r\u000bgNW5q\u001b\u0006\u0004h+\u00197vKN\fQ\"\u001b;fe\u0006$XMV1mk\u0016\u001cX#A\u001c\u0011\tYAT\u0004K\u0005\u0003s]\u0011\u0011cQ1o)J\fg/\u001a:tKZ\u000bG.^3t\u0001"
)
public interface Coordinated {
   ScalarOf scalarOf();

   CanMapValues mapValues();

   CanZipMapValues zipMapValues();

   CanTraverseValues iterateValues();
}
