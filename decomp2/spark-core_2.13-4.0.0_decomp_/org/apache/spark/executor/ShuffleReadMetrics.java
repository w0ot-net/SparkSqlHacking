package org.apache.spark.executor;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.LongAccumulator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\tMd\u0001\u0002.\\\u0001\u0011Daa\u001e\u0001\u0005\u0002uC\b\u0002C>\u0001\u0005\u0004%\ta\u0017?\t\u000f\u0005\u001d\u0001\u0001)A\u0005{\"I\u0011\u0011\u0002\u0001C\u0002\u0013\u00051\f \u0005\b\u0003\u0017\u0001\u0001\u0015!\u0003~\u0011%\ti\u0001\u0001b\u0001\n\u0003YF\u0010C\u0004\u0002\u0010\u0001\u0001\u000b\u0011B?\t\u0013\u0005E\u0001A1A\u0005\u0002mc\bbBA\n\u0001\u0001\u0006I! \u0005\n\u0003+\u0001!\u0019!C\u00017rDq!a\u0006\u0001A\u0003%Q\u0010C\u0005\u0002\u001a\u0001\u0011\r\u0011\"\u0001\\y\"9\u00111\u0004\u0001!\u0002\u0013i\b\"CA\u000f\u0001\t\u0007I\u0011A.}\u0011\u001d\ty\u0002\u0001Q\u0001\nuD\u0011\"!\t\u0001\u0005\u0004%\ta\u0017?\t\u000f\u0005\r\u0002\u0001)A\u0005{\"I\u0011Q\u0005\u0001C\u0002\u0013\u00051\f \u0005\b\u0003O\u0001\u0001\u0015!\u0003~\u0011%\tI\u0003\u0001b\u0001\n\u0003YF\u0010C\u0004\u0002,\u0001\u0001\u000b\u0011B?\t\u0013\u00055\u0002A1A\u0005\u0002mc\bbBA\u0018\u0001\u0001\u0006I! \u0005\n\u0003c\u0001!\u0019!C\u00017rDq!a\r\u0001A\u0003%Q\u0010C\u0005\u00026\u0001\u0011\r\u0011\"\u0001\\y\"9\u0011q\u0007\u0001!\u0002\u0013i\b\"CA\u001d\u0001\t\u0007I\u0011A.}\u0011\u001d\tY\u0004\u0001Q\u0001\nuD\u0011\"!\u0010\u0001\u0005\u0004%\ta\u0017?\t\u000f\u0005}\u0002\u0001)A\u0005{\"I\u0011\u0011\t\u0001C\u0002\u0013\u00051\f \u0005\b\u0003\u0007\u0002\u0001\u0015!\u0003~\u0011%\t)\u0005\u0001b\u0001\n\u0003YF\u0010C\u0004\u0002H\u0001\u0001\u000b\u0011B?\t\u000f\u0005%\u0003\u0001\"\u0001\u0002L!9\u00111\u000b\u0001\u0005\u0002\u0005-\u0003bBA+\u0001\u0011\u0005\u00111\n\u0005\b\u0003/\u0002A\u0011AA&\u0011\u001d\tI\u0006\u0001C\u0001\u0003\u0017Bq!a\u0017\u0001\t\u0003\tY\u0005C\u0004\u0002^\u0001!\t!a\u0013\t\u000f\u0005}\u0003\u0001\"\u0001\u0002L!9\u0011\u0011\r\u0001\u0005\u0002\u0005-\u0003bBA2\u0001\u0011\u0005\u00111\n\u0005\b\u0003K\u0002A\u0011AA&\u0011\u001d\t9\u0007\u0001C\u0001\u0003\u0017Bq!!\u001b\u0001\t\u0003\tY\u0005C\u0004\u0002l\u0001!\t!a\u0013\t\u000f\u00055\u0004\u0001\"\u0001\u0002L!9\u0011q\u000e\u0001\u0005\u0002\u0005-\u0003bBA9\u0001\u0011\u0005\u00111\n\u0005\b\u0003g\u0002A\u0011AA&\u0011\u001d\t)\b\u0001C\u0001\u0003\u0017B\u0001\"a\u001e\u0001\t\u0003i\u0016\u0011\u0010\u0005\t\u0003\u000b\u0003A\u0011A/\u0002\b\"A\u00111\u0012\u0001\u0005\u0002u\u000bi\t\u0003\u0005\u0002\u0012\u0002!\t!XAJ\u0011!\t9\n\u0001C\u0001;\u0006e\u0005\u0002CAO\u0001\u0011\u0005Q,a(\t\u0011\u0005\r\u0006\u0001\"\u0001^\u0003KC\u0001\"!+\u0001\t\u0003i\u00161\u0016\u0005\t\u0003_\u0003A\u0011A/\u00022\"A\u0011Q\u0017\u0001\u0005\u0002u\u000b9\f\u0003\u0005\u0002<\u0002!\t!XA_\u0011!\t\t\r\u0001C\u0001;\u0006\r\u0007\u0002CAd\u0001\u0011\u0005Q,!3\t\u0011\u00055\u0007\u0001\"\u0001^\u0003\u001fD\u0001\"a5\u0001\t\u0003i\u0016Q\u001b\u0005\t\u00033\u0004A\u0011A/\u0002\\\"A\u0011q\u001c\u0001\u0005\u0002u\u000b\t\u000f\u0003\u0005\u0002f\u0002!\t!XAt\u0011!\t\t\u0010\u0001C\u0001;\u0006M\b\u0002CA|\u0001\u0011\u0005Q,!?\t\u0011\u0005u\b\u0001\"\u0001^\u0003\u007fD\u0001Ba\u0001\u0001\t\u0003i&Q\u0001\u0005\t\u0005\u0013\u0001A\u0011A/\u0003\f!A!q\u0002\u0001\u0005\u0002u\u0013\t\u0002\u0003\u0005\u0003\u0016\u0001!\t!\u0018B\f\u0011!\u0011Y\u0002\u0001C\u0001;\nu\u0001\u0002\u0003B\u0011\u0001\u0011\u0005QLa\t\t\u0011\t\u001d\u0002\u0001\"\u0001^\u0005SA\u0001B!\f\u0001\t\u0003i&q\u0006\u0005\t\u0005g\u0001A\u0011A/\u00036!A!\u0011\b\u0001\u0005\u0002u\u0013Y\u0004\u0003\u0005\u0003@\u0001!\t!\u0018B!\u0011!\u0011)\u0005\u0001C\u0001;\n\u001d\u0003\u0002\u0003B&\u0001\u0011\u0005QL!\u0014\t\u0011\tE\u0003\u0001\"\u0001^\u0005'\u0012!c\u00155vM\u001adWMU3bI6+GO]5dg*\u0011A,X\u0001\tKb,7-\u001e;pe*\u0011alX\u0001\u0006gB\f'o\u001b\u0006\u0003A\u0006\fa!\u00199bG\",'\"\u00012\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001)7\u000e\u0005\u0002gS6\tqMC\u0001i\u0003\u0015\u00198-\u00197b\u0013\tQwM\u0001\u0004B]f\u0014VM\u001a\t\u0003YRt!!\u001c:\u000f\u00059\fX\"A8\u000b\u0005A\u001c\u0017A\u0002\u001fs_>$h(C\u0001i\u0013\t\u0019x-A\u0004qC\u000e\\\u0017mZ3\n\u0005U4(\u0001D*fe&\fG.\u001b>bE2,'BA:h\u0003\u0019a\u0014N\\5u}Q\t\u0011\u0010\u0005\u0002{\u00015\t1,\u0001\u000b`e\u0016lw\u000e^3CY>\u001c7n\u001d$fi\u000eDW\rZ\u000b\u0002{B\u0019a0a\u0001\u000e\u0003}T1!!\u0001^\u0003\u0011)H/\u001b7\n\u0007\u0005\u0015qPA\bM_:<\u0017iY2v[Vd\u0017\r^8s\u0003Uy&/Z7pi\u0016\u0014En\\2lg\u001a+Go\u00195fI\u0002\n1c\u00187pG\u0006d'\t\\8dWN4U\r^2iK\u0012\fAc\u00187pG\u0006d'\t\\8dWN4U\r^2iK\u0012\u0004\u0013\u0001E0sK6|G/\u001a\"zi\u0016\u001c(+Z1e\u0003Ey&/Z7pi\u0016\u0014\u0015\u0010^3t%\u0016\fG\rI\u0001\u0017?J,Wn\u001c;f\u0005f$Xm\u001d*fC\u0012$v\u000eR5tW\u00069rL]3n_R,')\u001f;fgJ+\u0017\r\u001a+p\t&\u001c8\u000eI\u0001\u0010?2|7-\u00197CsR,7OU3bI\u0006\u0001r\f\\8dC2\u0014\u0015\u0010^3t%\u0016\fG\rI\u0001\u000f?\u001a,Go\u00195XC&$H+[7f\u0003=yf-\u001a;dQ^\u000b\u0017\u000e\u001e+j[\u0016\u0004\u0013\u0001D0sK\u000e|'\u000fZ:SK\u0006$\u0017!D0sK\u000e|'\u000fZ:SK\u0006$\u0007%A\r`G>\u0014(/\u001e9u\u001b\u0016\u0014x-\u001a3CY>\u001c7n\u00115v].\u001c\u0018AG0d_J\u0014X\u000f\u001d;NKJ<W\r\u001a\"m_\u000e\\7\t[;oWN\u0004\u0013!G0nKJ<W\r\u001a$fi\u000eDg)\u00197mE\u0006\u001c7nQ8v]R\f!dX7fe\u001e,GMR3uG\"4\u0015\r\u001c7cC\u000e\\7i\\;oi\u0002\n!d\u0018:f[>$X-T3sO\u0016$'\t\\8dWN4U\r^2iK\u0012\f1d\u0018:f[>$X-T3sO\u0016$'\t\\8dWN4U\r^2iK\u0012\u0004\u0013!G0m_\u000e\fG.T3sO\u0016$'\t\\8dWN4U\r^2iK\u0012\f!d\u00187pG\u0006dW*\u001a:hK\u0012\u0014En\\2lg\u001a+Go\u00195fI\u0002\n!d\u0018:f[>$X-T3sO\u0016$7\t[;oWN4U\r^2iK\u0012\f1d\u0018:f[>$X-T3sO\u0016$7\t[;oWN4U\r^2iK\u0012\u0004\u0013!G0m_\u000e\fG.T3sO\u0016$7\t[;oWN4U\r^2iK\u0012\f!d\u00187pG\u0006dW*\u001a:hK\u0012\u001c\u0005.\u001e8lg\u001a+Go\u00195fI\u0002\nac\u0018:f[>$X-T3sO\u0016$')\u001f;fgJ+\u0017\rZ\u0001\u0018?J,Wn\u001c;f\u001b\u0016\u0014x-\u001a3CsR,7OU3bI\u0002\nQc\u00187pG\u0006dW*\u001a:hK\u0012\u0014\u0015\u0010^3t%\u0016\fG-\u0001\f`Y>\u001c\u0017\r\\'fe\u001e,GMQ=uKN\u0014V-\u00193!\u0003My&/Z7pi\u0016\u0014V-]:EkJ\fG/[8o\u0003Qy&/Z7pi\u0016\u0014V-]:EkJ\fG/[8oA\u0005IrL]3n_R,W*\u001a:hK\u0012\u0014V-]:EkJ\fG/[8o\u0003iy&/Z7pi\u0016lUM]4fIJ+\u0017o\u001d#ve\u0006$\u0018n\u001c8!\u0003M\u0011X-\\8uK\ncwnY6t\r\u0016$8\r[3e+\t\ti\u0005E\u0002g\u0003\u001fJ1!!\u0015h\u0005\u0011auN\\4\u0002%1|7-\u00197CY>\u001c7n\u001d$fi\u000eDW\rZ\u0001\u0010e\u0016lw\u000e^3CsR,7OU3bI\u0006)\"/Z7pi\u0016\u0014\u0015\u0010^3t%\u0016\fG\rV8ESN\\\u0017A\u00047pG\u0006d')\u001f;fgJ+\u0017\rZ\u0001\u000eM\u0016$8\r[,bSR$\u0016.\\3\u0002\u0017I,7m\u001c:egJ+\u0017\rZ\u0001\u000fi>$\u0018\r\u001c\"zi\u0016\u001c(+Z1e\u0003I!x\u000e^1m\u00052|7m[:GKR\u001c\u0007.\u001a3\u00021\r|'O];qi6+'oZ3e\u00052|7m[\"ik:\\7/\u0001\rnKJ<W\r\u001a$fi\u000eDg)\u00197mE\u0006\u001c7nQ8v]R\f\u0011D]3n_R,W*\u001a:hK\u0012\u0014En\\2lg\u001a+Go\u00195fI\u0006ABn\\2bY6+'oZ3e\u00052|7m[:GKR\u001c\u0007.\u001a3\u00023I,Wn\u001c;f\u001b\u0016\u0014x-\u001a3DQVt7n\u001d$fi\u000eDW\rZ\u0001\u0019Y>\u001c\u0017\r\\'fe\u001e,Gm\u00115v].\u001ch)\u001a;dQ\u0016$\u0017!\u0006:f[>$X-T3sO\u0016$')\u001f;fgJ+\u0017\rZ\u0001\u0015Y>\u001c\u0017\r\\'fe\u001e,GMQ=uKN\u0014V-\u00193\u0002%I,Wn\u001c;f%\u0016\f8\u000fR;sCRLwN\\\u0001\u0019e\u0016lw\u000e^3NKJ<W\r\u001a*fcN$UO]1uS>t\u0017AF5oGJ+Wn\u001c;f\u00052|7m[:GKR\u001c\u0007.\u001a3\u0015\t\u0005m\u0014\u0011\u0011\t\u0004M\u0006u\u0014bAA@O\n!QK\\5u\u0011\u001d\t\u0019i\u000ea\u0001\u0003\u001b\n\u0011A^\u0001\u0016S:\u001cGj\\2bY\ncwnY6t\r\u0016$8\r[3e)\u0011\tY(!#\t\u000f\u0005\r\u0005\b1\u0001\u0002N\u0005\u0011\u0012N\\2SK6|G/\u001a\"zi\u0016\u001c(+Z1e)\u0011\tY(a$\t\u000f\u0005\r\u0015\b1\u0001\u0002N\u0005A\u0012N\\2SK6|G/\u001a\"zi\u0016\u001c(+Z1e)>$\u0015n]6\u0015\t\u0005m\u0014Q\u0013\u0005\b\u0003\u0007S\u0004\u0019AA'\u0003EIgn\u0019'pG\u0006d')\u001f;fgJ+\u0017\r\u001a\u000b\u0005\u0003w\nY\nC\u0004\u0002\u0004n\u0002\r!!\u0014\u0002!%t7MR3uG\"<\u0016-\u001b;US6,G\u0003BA>\u0003CCq!a!=\u0001\u0004\ti%\u0001\bj]\u000e\u0014VmY8sIN\u0014V-\u00193\u0015\t\u0005m\u0014q\u0015\u0005\b\u0003\u0007k\u0004\u0019AA'\u0003mIgnY\"peJ,\b\u000f^'fe\u001e,GM\u00117pG.\u001c\u0005.\u001e8lgR!\u00111PAW\u0011\u001d\t\u0019I\u0010a\u0001\u0003\u001b\n1$\u001b8d\u001b\u0016\u0014x-\u001a3GKR\u001c\u0007NR1mY\n\f7m[\"pk:$H\u0003BA>\u0003gCq!a!@\u0001\u0004\ti%\u0001\u000fj]\u000e\u0014V-\\8uK6+'oZ3e\u00052|7m[:GKR\u001c\u0007.\u001a3\u0015\t\u0005m\u0014\u0011\u0018\u0005\b\u0003\u0007\u0003\u0005\u0019AA'\u0003mIgn\u0019'pG\u0006dW*\u001a:hK\u0012\u0014En\\2lg\u001a+Go\u00195fIR!\u00111PA`\u0011\u001d\t\u0019)\u0011a\u0001\u0003\u001b\nA$\u001b8d%\u0016lw\u000e^3NKJ<W\rZ\"ik:\\7OR3uG\",G\r\u0006\u0003\u0002|\u0005\u0015\u0007bBAB\u0005\u0002\u0007\u0011QJ\u0001\u001cS:\u001cGj\\2bY6+'oZ3e\u0007\",hn[:GKR\u001c\u0007.\u001a3\u0015\t\u0005m\u00141\u001a\u0005\b\u0003\u0007\u001b\u0005\u0019AA'\u0003aIgn\u0019*f[>$X-T3sO\u0016$')\u001f;fgJ+\u0017\r\u001a\u000b\u0005\u0003w\n\t\u000eC\u0004\u0002\u0004\u0012\u0003\r!!\u0014\u0002/%t7\rT8dC2lUM]4fI\nKH/Z:SK\u0006$G\u0003BA>\u0003/Dq!a!F\u0001\u0004\ti%A\u000bj]\u000e\u0014V-\\8uKJ+\u0017o\u001d#ve\u0006$\u0018n\u001c8\u0015\t\u0005m\u0014Q\u001c\u0005\b\u0003\u00073\u0005\u0019AA'\u0003mIgn\u0019*f[>$X-T3sO\u0016$'+Z9t\tV\u0014\u0018\r^5p]R!\u00111PAr\u0011\u001d\t\u0019i\u0012a\u0001\u0003\u001b\nac]3u%\u0016lw\u000e^3CY>\u001c7n\u001d$fi\u000eDW\r\u001a\u000b\u0005\u0003w\nI\u000fC\u0004\u0002\u0004\"\u0003\r!a;\u0011\u0007\u0019\fi/C\u0002\u0002p\u001e\u00141!\u00138u\u0003U\u0019X\r\u001e'pG\u0006d'\t\\8dWN4U\r^2iK\u0012$B!a\u001f\u0002v\"9\u00111Q%A\u0002\u0005-\u0018AE:fiJ+Wn\u001c;f\u0005f$Xm\u001d*fC\u0012$B!a\u001f\u0002|\"9\u00111\u0011&A\u0002\u00055\u0013\u0001G:fiJ+Wn\u001c;f\u0005f$Xm\u001d*fC\u0012$v\u000eR5tWR!\u00111\u0010B\u0001\u0011\u001d\t\u0019i\u0013a\u0001\u0003\u001b\n\u0011c]3u\u0019>\u001c\u0017\r\u001c\"zi\u0016\u001c(+Z1e)\u0011\tYHa\u0002\t\u000f\u0005\rE\n1\u0001\u0002N\u0005\u00012/\u001a;GKR\u001c\u0007nV1jiRKW.\u001a\u000b\u0005\u0003w\u0012i\u0001C\u0004\u0002\u00046\u0003\r!!\u0014\u0002\u001dM,GOU3d_J$7OU3bIR!\u00111\u0010B\n\u0011\u001d\t\u0019I\u0014a\u0001\u0003\u001b\n1d]3u\u0007>\u0014(/\u001e9u\u001b\u0016\u0014x-\u001a3CY>\u001c7n\u00115v].\u001cH\u0003BA>\u00053Aq!a!P\u0001\u0004\ti%A\u000etKRlUM]4fI\u001a+Go\u00195GC2d'-Y2l\u0007>,h\u000e\u001e\u000b\u0005\u0003w\u0012y\u0002C\u0004\u0002\u0004B\u0003\r!!\u0014\u00029M,GOU3n_R,W*\u001a:hK\u0012\u0014En\\2lg\u001a+Go\u00195fIR!\u00111\u0010B\u0013\u0011\u001d\t\u0019)\u0015a\u0001\u0003\u001b\n1d]3u\u0019>\u001c\u0017\r\\'fe\u001e,GM\u00117pG.\u001ch)\u001a;dQ\u0016$G\u0003BA>\u0005WAq!a!S\u0001\u0004\ti%\u0001\u000ftKR\u0014V-\\8uK6+'oZ3e\u0007\",hn[:GKR\u001c\u0007.\u001a3\u0015\t\u0005m$\u0011\u0007\u0005\b\u0003\u0007\u001b\u0006\u0019AA'\u0003m\u0019X\r\u001e'pG\u0006dW*\u001a:hK\u0012\u001c\u0005.\u001e8lg\u001a+Go\u00195fIR!\u00111\u0010B\u001c\u0011\u001d\t\u0019\t\u0016a\u0001\u0003\u001b\n\u0001d]3u%\u0016lw\u000e^3NKJ<W\r\u001a\"zi\u0016\u001c(+Z1e)\u0011\tYH!\u0010\t\u000f\u0005\rU\u000b1\u0001\u0002N\u000592/\u001a;M_\u000e\fG.T3sO\u0016$')\u001f;fgJ+\u0017\r\u001a\u000b\u0005\u0003w\u0012\u0019\u0005C\u0004\u0002\u0004Z\u0003\r!!\u0014\u0002+M,GOU3n_R,'+Z9t\tV\u0014\u0018\r^5p]R!\u00111\u0010B%\u0011\u001d\t\u0019i\u0016a\u0001\u0003\u001b\n1d]3u%\u0016lw\u000e^3NKJ<W\r\u001a*fcN$UO]1uS>tG\u0003BA>\u0005\u001fBq!a!Y\u0001\u0004\ti%\u0001\btKRlUM]4f-\u0006dW/Z:\u0015\t\u0005m$Q\u000b\u0005\b\u0005/J\u0006\u0019\u0001B-\u0003\u001diW\r\u001e:jGN\u0004R\u0001\u001cB.\u0005?J1A!\u0018w\u0005\r\u0019V-\u001d\t\u0004u\n\u0005\u0014b\u0001B27\n1B+Z7q'\",hM\u001a7f%\u0016\fG-T3ue&\u001c7\u000fK\u0002\u0001\u0005O\u0002BA!\u001b\u0003p5\u0011!1\u000e\u0006\u0004\u0005[j\u0016AC1o]>$\u0018\r^5p]&!!\u0011\u000fB6\u00051!UM^3m_B,'/\u00119j\u0001"
)
public class ShuffleReadMetrics implements Serializable {
   private final LongAccumulator _remoteBlocksFetched = new LongAccumulator();
   private final LongAccumulator _localBlocksFetched = new LongAccumulator();
   private final LongAccumulator _remoteBytesRead = new LongAccumulator();
   private final LongAccumulator _remoteBytesReadToDisk = new LongAccumulator();
   private final LongAccumulator _localBytesRead = new LongAccumulator();
   private final LongAccumulator _fetchWaitTime = new LongAccumulator();
   private final LongAccumulator _recordsRead = new LongAccumulator();
   private final LongAccumulator _corruptMergedBlockChunks = new LongAccumulator();
   private final LongAccumulator _mergedFetchFallbackCount = new LongAccumulator();
   private final LongAccumulator _remoteMergedBlocksFetched = new LongAccumulator();
   private final LongAccumulator _localMergedBlocksFetched = new LongAccumulator();
   private final LongAccumulator _remoteMergedChunksFetched = new LongAccumulator();
   private final LongAccumulator _localMergedChunksFetched = new LongAccumulator();
   private final LongAccumulator _remoteMergedBytesRead = new LongAccumulator();
   private final LongAccumulator _localMergedBytesRead = new LongAccumulator();
   private final LongAccumulator _remoteReqsDuration = new LongAccumulator();
   private final LongAccumulator _remoteMergedReqsDuration = new LongAccumulator();

   public LongAccumulator _remoteBlocksFetched() {
      return this._remoteBlocksFetched;
   }

   public LongAccumulator _localBlocksFetched() {
      return this._localBlocksFetched;
   }

   public LongAccumulator _remoteBytesRead() {
      return this._remoteBytesRead;
   }

   public LongAccumulator _remoteBytesReadToDisk() {
      return this._remoteBytesReadToDisk;
   }

   public LongAccumulator _localBytesRead() {
      return this._localBytesRead;
   }

   public LongAccumulator _fetchWaitTime() {
      return this._fetchWaitTime;
   }

   public LongAccumulator _recordsRead() {
      return this._recordsRead;
   }

   public LongAccumulator _corruptMergedBlockChunks() {
      return this._corruptMergedBlockChunks;
   }

   public LongAccumulator _mergedFetchFallbackCount() {
      return this._mergedFetchFallbackCount;
   }

   public LongAccumulator _remoteMergedBlocksFetched() {
      return this._remoteMergedBlocksFetched;
   }

   public LongAccumulator _localMergedBlocksFetched() {
      return this._localMergedBlocksFetched;
   }

   public LongAccumulator _remoteMergedChunksFetched() {
      return this._remoteMergedChunksFetched;
   }

   public LongAccumulator _localMergedChunksFetched() {
      return this._localMergedChunksFetched;
   }

   public LongAccumulator _remoteMergedBytesRead() {
      return this._remoteMergedBytesRead;
   }

   public LongAccumulator _localMergedBytesRead() {
      return this._localMergedBytesRead;
   }

   public LongAccumulator _remoteReqsDuration() {
      return this._remoteReqsDuration;
   }

   public LongAccumulator _remoteMergedReqsDuration() {
      return this._remoteMergedReqsDuration;
   }

   public long remoteBlocksFetched() {
      return this._remoteBlocksFetched().sum();
   }

   public long localBlocksFetched() {
      return this._localBlocksFetched().sum();
   }

   public long remoteBytesRead() {
      return this._remoteBytesRead().sum();
   }

   public long remoteBytesReadToDisk() {
      return this._remoteBytesReadToDisk().sum();
   }

   public long localBytesRead() {
      return this._localBytesRead().sum();
   }

   public long fetchWaitTime() {
      return this._fetchWaitTime().sum();
   }

   public long recordsRead() {
      return this._recordsRead().sum();
   }

   public long totalBytesRead() {
      return this.remoteBytesRead() + this.localBytesRead();
   }

   public long totalBlocksFetched() {
      return this.remoteBlocksFetched() + this.localBlocksFetched();
   }

   public long corruptMergedBlockChunks() {
      return this._corruptMergedBlockChunks().sum();
   }

   public long mergedFetchFallbackCount() {
      return this._mergedFetchFallbackCount().sum();
   }

   public long remoteMergedBlocksFetched() {
      return this._remoteMergedBlocksFetched().sum();
   }

   public long localMergedBlocksFetched() {
      return this._localMergedBlocksFetched().sum();
   }

   public long remoteMergedChunksFetched() {
      return this._remoteMergedChunksFetched().sum();
   }

   public long localMergedChunksFetched() {
      return this._localMergedChunksFetched().sum();
   }

   public long remoteMergedBytesRead() {
      return this._remoteMergedBytesRead().sum();
   }

   public long localMergedBytesRead() {
      return this._localMergedBytesRead().sum();
   }

   public long remoteReqsDuration() {
      return this._remoteReqsDuration().sum();
   }

   public long remoteMergedReqsDuration() {
      return this._remoteMergedReqsDuration().sum();
   }

   public void incRemoteBlocksFetched(final long v) {
      this._remoteBlocksFetched().add(v);
   }

   public void incLocalBlocksFetched(final long v) {
      this._localBlocksFetched().add(v);
   }

   public void incRemoteBytesRead(final long v) {
      this._remoteBytesRead().add(v);
   }

   public void incRemoteBytesReadToDisk(final long v) {
      this._remoteBytesReadToDisk().add(v);
   }

   public void incLocalBytesRead(final long v) {
      this._localBytesRead().add(v);
   }

   public void incFetchWaitTime(final long v) {
      this._fetchWaitTime().add(v);
   }

   public void incRecordsRead(final long v) {
      this._recordsRead().add(v);
   }

   public void incCorruptMergedBlockChunks(final long v) {
      this._corruptMergedBlockChunks().add(v);
   }

   public void incMergedFetchFallbackCount(final long v) {
      this._mergedFetchFallbackCount().add(v);
   }

   public void incRemoteMergedBlocksFetched(final long v) {
      this._remoteMergedBlocksFetched().add(v);
   }

   public void incLocalMergedBlocksFetched(final long v) {
      this._localMergedBlocksFetched().add(v);
   }

   public void incRemoteMergedChunksFetched(final long v) {
      this._remoteMergedChunksFetched().add(v);
   }

   public void incLocalMergedChunksFetched(final long v) {
      this._localMergedChunksFetched().add(v);
   }

   public void incRemoteMergedBytesRead(final long v) {
      this._remoteMergedBytesRead().add(v);
   }

   public void incLocalMergedBytesRead(final long v) {
      this._localMergedBytesRead().add(v);
   }

   public void incRemoteReqsDuration(final long v) {
      this._remoteReqsDuration().add(v);
   }

   public void incRemoteMergedReqsDuration(final long v) {
      this._remoteMergedReqsDuration().add(v);
   }

   public void setRemoteBlocksFetched(final int v) {
      this._remoteBlocksFetched().setValue((long)v);
   }

   public void setLocalBlocksFetched(final int v) {
      this._localBlocksFetched().setValue((long)v);
   }

   public void setRemoteBytesRead(final long v) {
      this._remoteBytesRead().setValue(v);
   }

   public void setRemoteBytesReadToDisk(final long v) {
      this._remoteBytesReadToDisk().setValue(v);
   }

   public void setLocalBytesRead(final long v) {
      this._localBytesRead().setValue(v);
   }

   public void setFetchWaitTime(final long v) {
      this._fetchWaitTime().setValue(v);
   }

   public void setRecordsRead(final long v) {
      this._recordsRead().setValue(v);
   }

   public void setCorruptMergedBlockChunks(final long v) {
      this._corruptMergedBlockChunks().setValue(v);
   }

   public void setMergedFetchFallbackCount(final long v) {
      this._mergedFetchFallbackCount().setValue(v);
   }

   public void setRemoteMergedBlocksFetched(final long v) {
      this._remoteMergedBlocksFetched().setValue(v);
   }

   public void setLocalMergedBlocksFetched(final long v) {
      this._localMergedBlocksFetched().setValue(v);
   }

   public void setRemoteMergedChunksFetched(final long v) {
      this._remoteMergedChunksFetched().setValue(v);
   }

   public void setLocalMergedChunksFetched(final long v) {
      this._localMergedChunksFetched().setValue(v);
   }

   public void setRemoteMergedBytesRead(final long v) {
      this._remoteMergedBytesRead().setValue(v);
   }

   public void setLocalMergedBytesRead(final long v) {
      this._localMergedBytesRead().setValue(v);
   }

   public void setRemoteReqsDuration(final long v) {
      this._remoteReqsDuration().setValue(v);
   }

   public void setRemoteMergedReqsDuration(final long v) {
      this._remoteMergedReqsDuration().setValue(v);
   }

   public void setMergeValues(final Seq metrics) {
      this._remoteBlocksFetched().setValue(0L);
      this._localBlocksFetched().setValue(0L);
      this._remoteBytesRead().setValue(0L);
      this._remoteBytesReadToDisk().setValue(0L);
      this._localBytesRead().setValue(0L);
      this._fetchWaitTime().setValue(0L);
      this._recordsRead().setValue(0L);
      this._corruptMergedBlockChunks().setValue(0L);
      this._mergedFetchFallbackCount().setValue(0L);
      this._remoteMergedBlocksFetched().setValue(0L);
      this._localMergedBlocksFetched().setValue(0L);
      this._remoteMergedChunksFetched().setValue(0L);
      this._localMergedChunksFetched().setValue(0L);
      this._remoteMergedBytesRead().setValue(0L);
      this._localMergedBytesRead().setValue(0L);
      this._remoteReqsDuration().setValue(0L);
      this._remoteMergedReqsDuration().setValue(0L);
      metrics.foreach((metric) -> {
         $anonfun$setMergeValues$1(this, metric);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$setMergeValues$1(final ShuffleReadMetrics $this, final TempShuffleReadMetrics metric) {
      $this._remoteBlocksFetched().add(metric.remoteBlocksFetched());
      $this._localBlocksFetched().add(metric.localBlocksFetched());
      $this._remoteBytesRead().add(metric.remoteBytesRead());
      $this._remoteBytesReadToDisk().add(metric.remoteBytesReadToDisk());
      $this._localBytesRead().add(metric.localBytesRead());
      $this._fetchWaitTime().add(metric.fetchWaitTime());
      $this._recordsRead().add(metric.recordsRead());
      $this._corruptMergedBlockChunks().add(metric.corruptMergedBlockChunks());
      $this._mergedFetchFallbackCount().add(metric.mergedFetchFallbackCount());
      $this._remoteMergedBlocksFetched().add(metric.remoteMergedBlocksFetched());
      $this._localMergedBlocksFetched().add(metric.localMergedBlocksFetched());
      $this._remoteMergedChunksFetched().add(metric.remoteMergedChunksFetched());
      $this._localMergedChunksFetched().add(metric.localMergedChunksFetched());
      $this._remoteMergedBytesRead().add(metric.remoteMergedBytesRead());
      $this._localMergedBytesRead().add(metric.localMergedBytesRead());
      $this._remoteReqsDuration().add(metric.remoteReqsDuration());
      $this._remoteMergedReqsDuration().add(metric.remoteMergedReqsDuration());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
