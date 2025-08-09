package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.Field;
import breeze.math.MutableModule;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ArrayUtil$;
import breeze.util.ReflectionUtil$;
import breeze.util.Sorting$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015md\u0001B(Q\u0001UC!\"a\t\u0001\u0005\u0003\u0007I\u0011BA\u0013\u0011)\t\u0019\u0004\u0001BA\u0002\u0013%\u0011Q\u0007\u0005\u000b\u0003\u0003\u0002!\u0011!Q!\n\u0005\u001d\u0002BCA\"\u0001\t\u0005\r\u0011\"\u0003\u0002F!Q\u0011\u0011\n\u0001\u0003\u0002\u0004%I!a\u0013\t\u0015\u0005=\u0003A!A!B\u0013\t9\u0005\u0003\u0006\u0002R\u0001\u0011\t\u0019!C\u0005\u0003'B!\"!\u0016\u0001\u0005\u0003\u0007I\u0011BA,\u0011)\tY\u0006\u0001B\u0001B\u0003&\u0011Q\u0006\u0005\u000b\u0003;\u0002!\u00111A\u0005\u0002\u0005M\u0003BCA0\u0001\t\u0005\r\u0011\"\u0001\u0002b!Q\u0011Q\r\u0001\u0003\u0002\u0003\u0006K!!\f\t\u0015\u0005\u001d\u0004A!A!\u0002\u0017\tI\u0007C\u0004\u0002v\u0001!\t!a\u001e\t\u000f\u0005U\u0004\u0001\"\u0001\u0002\u0006\"9\u0011Q\u000f\u0001\u0005\u0002\u0005\u0005\u0006bBA]\u0001\u0011\u0005\u00111\u000b\u0005\b\u0003w\u0003A\u0011AA#\u0011\u001d\ti\f\u0001C\u0001\u0003KAq!a0\u0001\t\u0003\t\u0019\u0006C\u0004\u0002B\u0002!\t!a1\t\u000f\u0005\u0015\u0007\u0001\"\u0001\u0002H\"9\u00111\u001b\u0001\u0005\u0002\u0005U\u0007bBAm\u0001\u0011%\u00111\u001c\u0005\b\u0003?\u0004A\u0011AAq\u0011\u001d\tI\u000f\u0001C\u0001\u0003WDq!!=\u0001\t\u0003\t\u0019\u0010C\u0004\u0003\u0002\u0001!\tAa\u0001\t\u000f\t\u001d\u0001\u0001\"\u0001\u0003\n!9!Q\u0002\u0001\u0005\u0002\t=\u0001b\u0002B\t\u0001\u0011\u0005!1\u0003\u0005\b\u00053\u0001A\u0011\tB\u000e\u0011\u001d\u0011i\u0003\u0001C\u0001\u0003\u0007DqAa\f\u0001\t\u0003\t\u0019\rC\u0004\u00032\u0001!\tAa\r\t\u000f\te\u0002\u0001\"\u0003\u0003<!9!q\b\u0001\u0005\u0002\t\u0005\u0003b\u0002B%\u0001\u0011%!1\n\u0005\b\u0005\u001b\u0002A\u0011\u0001B(\u0011\u001d\u00119\u0006\u0001C\u0001\u00053BqAa\u0016\u0001\t\u0003\u0011\t\u0007C\u0005\u0003l\u0001\t\n\u0011\"\u0001\u0003n!I!1\u0011\u0001\u0012\u0002\u0013\u0005!Q\u000e\u0005\b\u0005\u000b\u0003A\u0011\u0001B&\u0011\u001d\u00119\t\u0001C\u0001\u0005\u0017BqA!#\u0001\t\u0003\u0012Y\tC\u0004\u0003\u0012\u0002!\tAa%\t\u000f\tm\u0005\u0001\"\u0001\u0003\u001e\"9!\u0011\u0015\u0001\u0005\u0002\t\r\u0006b\u0002BT\u0001\u0011\u0005!\u0011\u0016\u0005\b\u0005W\u0003A\u0011\u0001BW\u000f\u001d\u0011\t\r\u0015E\u0001\u0005\u00074aa\u0014)\t\u0002\t\u0015\u0007bBA;k\u0011\u0005!\u0011\u001d\u0005\b\u0005G,D\u0011\u0001Bs\u0011\u001d\t\u0019.\u000eC\u0001\u00077Aq!a56\t\u0003\u0019Y\u0005C\u0004\u0004rU\"\taa\u001d\t\u000f\r=V\u0007\"\u0001\u00042\"9\u00111[\u001b\u0005\u0002\r=hA\u0002C\fk\u0001!I\u0002\u0003\u0006\u0005Bu\u0012\u0019\u0011)A\u0006\t\u0007B!\u0002\"\u0012>\u0005\u0007\u0005\u000b1\u0002C$\u0011)!I%\u0010B\u0002B\u0003-A1\n\u0005\b\u0003kjD\u0011\u0001C'\u0011\u001d\t\u0019.\u0010C\u0001\t72a\u0001\"\u00196\u0001\u0011\r\u0004B\u0003CC\u0007\n\r\t\u0015a\u0003\u0005\b\"QA\u0011R\"\u0003\u0004\u0003\u0006Y\u0001b#\t\u0015\u001155IaA!\u0002\u0017!y\tC\u0004\u0002v\r#\t\u0001\"%\t\u000f\u0005M7\t\"\u0001\u0005\u001e\"9A\u0011U\u001b\u0005\u0004\u0011\r\u0006b\u0002Cik\u0011\rA1\u001b\u0005\b\u000b\u0003)D1AC\u0002\u0011%)9$NI\u0001\n\u0003)I\u0004C\u0005\u0006TU\n\n\u0011\"\u0001\u0006V!IQ1N\u001b\u0002\u0002\u0013%QQ\u000e\u0002\u000e-\u0016\u001cGo\u001c:Ck&dG-\u001a:\u000b\u0005E\u0013\u0016A\u00027j]\u0006dwMC\u0001T\u0003\u0019\u0011'/Z3{K\u000e\u0001QC\u0001,e'\u0015\u0001q+XA\u000b!\tA6,D\u0001Z\u0015\u0005Q\u0016!B:dC2\f\u0017B\u0001/Z\u0005\u0019\te.\u001f*fMB\u0019alX1\u000e\u0003AK!\u0001\u0019)\u0003\u00159+X.\u001a:jG>\u00038\u000fE\u0002_\u0001\t\u0004\"a\u00193\r\u0001\u0011IQ\r\u0001Q\u0001\u0002\u0003\u0015\rA\u001a\u0002\u0002\u000bF\u0011qM\u001b\t\u00031\"L!![-\u0003\u000f9{G\u000f[5oOB\u0011\u0001l[\u0005\u0003Yf\u00131!\u00118zQ!!g.]>\u0002\u0002\u0005-\u0001C\u0001-p\u0013\t\u0001\u0018LA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012sgV$hB\u0001-t\u0013\t!\u0018,\u0001\u0004E_V\u0014G.Z\u0019\u0005IYT(L\u0004\u0002xu6\t\u0001P\u0003\u0002z)\u00061AH]8pizJ\u0011AW\u0019\u0006GqlxP \b\u00031vL!A`-\u0002\u0007%sG/\r\u0003%mjT\u0016'C\u0012\u0002\u0004\u0005\u0015\u0011\u0011BA\u0004\u001d\rA\u0016QA\u0005\u0004\u0003\u000fI\u0016!\u0002$m_\u0006$\u0018\u0007\u0002\u0013wuj\u000b\u0014bIA\u0007\u0003\u001f\t\u0019\"!\u0005\u000f\u0007a\u000by!C\u0002\u0002\u0012e\u000bA\u0001T8oOF\"AE\u001e>[!\u0011\t9\"!\b\u000f\u0007Y\fI\"C\u0002\u0002\u001ce\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002 \u0005\u0005\"\u0001D*fe&\fG.\u001b>bE2,'bAA\u000e3\u00061q,\u001b8eKb,\"!a\n\u0011\u000ba\u000bI#!\f\n\u0007\u0005-\u0012LA\u0003BeJ\f\u0017\u0010E\u0002Y\u0003_I1!!\rZ\u0005\rIe\u000e^\u0001\u000b?&tG-\u001a=`I\u0015\fH\u0003BA\u001c\u0003{\u00012\u0001WA\u001d\u0013\r\tY$\u0017\u0002\u0005+:LG\u000fC\u0005\u0002@\t\t\t\u00111\u0001\u0002(\u0005\u0019\u0001\u0010J\u0019\u0002\u000f}Kg\u000eZ3yA\u0005)q\fZ1uCV\u0011\u0011q\t\t\u00051\u0006%\"-A\u0005`I\u0006$\u0018m\u0018\u0013fcR!\u0011qGA'\u0011%\ty$BA\u0001\u0002\u0004\t9%\u0001\u0004`I\u0006$\u0018\rI\u0001\u0005kN,G-\u0006\u0002\u0002.\u0005AQo]3e?\u0012*\u0017\u000f\u0006\u0003\u00028\u0005e\u0003\"CA \u0011\u0005\u0005\t\u0019AA\u0017\u0003\u0015)8/\u001a3!\u0003\u0019aWM\\4uQ\u0006QA.\u001a8hi\"|F%Z9\u0015\t\u0005]\u00121\r\u0005\n\u0003\u007fY\u0011\u0011!a\u0001\u0003[\tq\u0001\\3oORD\u0007%\u0001\u0003sS:<\u0007#BA6\u0003c\u0012WBAA7\u0015\r\tyGU\u0001\u0005[\u0006$\b.\u0003\u0003\u0002t\u00055$\u0001C*f[&\u0014\u0018N\\4\u0002\rqJg.\u001b;?))\tI(! \u0002\u0000\u0005\u0005\u00151\u0011\u000b\u0004C\u0006m\u0004bBA4\u001d\u0001\u000f\u0011\u0011\u000e\u0005\b\u0003Gq\u0001\u0019AA\u0014\u0011\u001d\t\u0019E\u0004a\u0001\u0003\u000fBq!!\u0015\u000f\u0001\u0004\ti\u0003C\u0004\u0002^9\u0001\r!!\f\u0015\r\u0005\u001d\u00151TAO)\u0015\t\u0017\u0011RAF\u0011\u001d\t9g\u0004a\u0002\u0003SBq!!$\u0010\u0001\b\ty)A\u0002nC:\u0004R!!%\u0002\u0018\nl!!a%\u000b\u0007\u0005U\u0015,A\u0004sK\u001adWm\u0019;\n\t\u0005e\u00151\u0013\u0002\t\u00072\f7o\u001d+bO\"9\u0011QL\bA\u0002\u00055\u0002\"CAP\u001fA\u0005\t\u0019AA\u0017\u00039Ig.\u001b;jC2tuN\u001c.fe>$\"!a)\u0015\u000f\u0005\f)+a*\u0002*\"9\u0011q\r\tA\u0004\u0005%\u0004bBAG!\u0001\u000f\u0011q\u0012\u0005\b\u0003W\u0003\u00029AAW\u0003\u0011QXM]8\u0011\u000b\u0005=\u0016Q\u00172\u000e\u0005\u0005E&bAAZ%\u000691\u000f^8sC\u001e,\u0017\u0002BA\\\u0003c\u0013AAW3s_\u0006!1/\u001b>f\u0003\u0011!\u0017\r^1\u0002\u000b%tG-\u001a=\u0002\u0015\u0005\u001cG/\u001b<f'&TX-\u0001\u0003sKB\u0014X#A1\u0002\u0011\r|g\u000e^1j]N$B!!3\u0002PB\u0019\u0001,a3\n\u0007\u00055\u0017LA\u0004C_>dW-\u00198\t\u000f\u0005Eg\u00031\u0001\u0002.\u0005\t\u0011.A\u0003baBd\u0017\u0010F\u0002c\u0003/Dq!!5\u0018\u0001\u0004\ti#A\u0006c_VtGm]\"iK\u000e\\G\u0003BA\u001c\u0003;Dq!!5\u0019\u0001\u0004\ti#\u0001\u0004va\u0012\fG/\u001a\u000b\u0007\u0003o\t\u0019/!:\t\u000f\u0005E\u0017\u00041\u0001\u0002.!1\u0011q]\rA\u0002\t\f\u0011A^\u0001\u0004C\u0012$GCBA\u001c\u0003[\fy\u000fC\u0004\u0002Rj\u0001\r!!\f\t\r\u0005\u001d(\u00041\u0001c\u00039\t7\r^5wK&#XM]1u_J,\"!!>\u0011\r\u0005]\u0011q_A~\u0013\u0011\tI0!\t\u0003\u0011%#XM]1u_J\u0004b\u0001WA\u007f\u0003[\u0011\u0017bAA\u00003\n1A+\u001e9mKJ\nA#Y2uSZ,g+\u00197vKNLE/\u001a:bi>\u0014XC\u0001B\u0003!\u0015\t9\"a>c\u0003I\t7\r^5wK.+\u0017p]%uKJ\fGo\u001c:\u0016\u0005\t-\u0001CBA\f\u0003o\fi#A\u0004eK\u001a\fW\u000f\u001c;\u0016\u0003\t\f\u0001\"[:BGRLg/\u001a\u000b\u0005\u0003\u0013\u0014)\u0002C\u0004\u0003\u0018}\u0001\r!!\f\u0002\u0011I\fw/\u00138eKb\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005;\u0001BAa\b\u0003(9!!\u0011\u0005B\u0012!\t9\u0018,C\u0002\u0003&e\u000ba\u0001\u0015:fI\u00164\u0017\u0002\u0002B\u0015\u0005W\u0011aa\u0015;sS:<'b\u0001B\u00133\u0006!1m\u001c9z\u0003%QXM]8t\u0019&\\W-A\u0004sKN,'O^3\u0015\t\u0005]\"Q\u0007\u0005\b\u0005o\u0019\u0003\u0019AA\u0017\u0003\rqgN_\u0001\u000be\u0016\fG\u000e\\8dCR,G\u0003BA\u001c\u0005{AqAa\u000e%\u0001\u0004\ti#\u0001\u0007u_\"\u000b7\u000f\u001b,fGR|'/\u0006\u0002\u0003DA!aL!\u0012c\u0013\r\u00119\u0005\u0015\u0002\u000b\u0011\u0006\u001c\bNV3di>\u0014\u0018!\u0006:fcVL'/\u001a)pg&$\u0018N^3MK:<G\u000f\u001b\u000b\u0003\u0003o\tQ\u0002^8EK:\u001cXMV3di>\u0014XC\u0001B)!\u0011q&1\u000b2\n\u0007\tU\u0003KA\u0006EK:\u001cXMV3di>\u0014\u0018A\u0004;p'B\f'o]3WK\u000e$xN]\u000b\u0003\u00057\u0002BA\u0018B/E&\u0019!q\f)\u0003\u0019M\u0003\u0018M]:f-\u0016\u001cGo\u001c:\u0015\r\tm#1\rB4\u0011%\u0011)'\u000bI\u0001\u0002\u0004\tI-A\u0007bYJ,\u0017\rZ=T_J$X\r\u001a\u0005\n\u0005SJ\u0003\u0013!a\u0001\u0003\u0013\f\u0011c[3zg\u0006c'/Z1esVs\u0017.];f\u0003a!xn\u00159beN,g+Z2u_J$C-\u001a4bk2$H%M\u000b\u0003\u0005_RC!!3\u0003r-\u0012!1\u000f\t\u0005\u0005k\u0012y(\u0004\u0002\u0003x)!!\u0011\u0010B>\u0003%)hn\u00195fG.,GMC\u0002\u0003~e\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\u0011\tIa\u001e\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\ru_N\u0003\u0018M]:f-\u0016\u001cGo\u001c:%I\u00164\u0017-\u001e7uII\nqaY8na\u0006\u001cG/A\u0003dY\u0016\f'/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0013\u0014i\t\u0003\u0004\u0003\u0010:\u0002\rA[\u0001\u0003aF\n1!^:f)!\t9D!&\u0003\u0018\ne\u0005bBA__\u0001\u0007\u0011q\u0005\u0005\b\u0003w{\u0003\u0019AA$\u0011\u001d\tyl\fa\u0001\u0003[\tqA^1mk\u0016\fE\u000fF\u0002c\u0005?Cq!!51\u0001\u0004\ti#A\u0004j]\u0012,\u00070\u0011;\u0015\t\u00055\"Q\u0015\u0005\b\u0003#\f\u0004\u0019AA\u0017\u0003e\tG\u000e\u001c,jg&$\u0018M\u00197f\u0013:$\u0017nY3t\u0003\u000e$\u0018N^3\u0016\u0005\u0005%\u0017\u0001\u0003;p-\u0016\u001cGo\u001c:\u0016\u0005\t=\u0006\u0003\u00020\u00032\nL1Aa-Q\u0005\u00191Vm\u0019;pe\":\u0001Aa.\u0003>\n}\u0006c\u0001-\u0003:&\u0019!1X-\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0001\u0002\u001bY+7\r^8s\u0005VLG\u000eZ3s!\tqVg\u0005\u00046/\n\u001d'1\u001b\t\u0005\u0005\u0013\u0014y-\u0004\u0002\u0003L*\u0019!Q\u001a)\u0002\u0013=\u0004XM]1u_J\u001c\u0018\u0002\u0002Bi\u0005\u0017\u0014\u0001CV3di>\u0014()^5mI\u0016\u0014x\n]:\u0011\t\tU'q\\\u0007\u0003\u0005/TAA!7\u0003\\\u0006\u0011\u0011n\u001c\u0006\u0003\u0005;\fAA[1wC&!\u0011q\u0004Bl)\t\u0011\u0019-A\u0003{KJ|7/\u0006\u0003\u0003h\n=HC\u0002Bu\u0007/\u0019I\u0002\u0006\u0005\u0003l\u000e\u001511BB\t!\u0011q\u0006A!<\u0011\u0007\r\u0014y\u000f\u0002\u0006\u0003r^\u0002\u000b\u0011!AC\u0002\u0019\u0014\u0011A\u0016\u0015\f\u0005_t'Q\u001fB}\u0005{\u001c\t!\r\u0004$eN\u00149\u0010^\u0019\u0005IYT(,\r\u0004$yv\u0014YP`\u0019\u0005IYT(,M\u0005$\u0003\u0007\t)Aa@\u0002\bE\"AE\u001e>[c%\u0019\u0013QBA\b\u0007\u0007\t\t\"\r\u0003%mjT\u0006\"CB\u0004o\u0005\u0005\t9AB\u0005\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003#\u000b9J!<\t\u0013\r5q'!AA\u0004\r=\u0011AC3wS\u0012,gnY3%eA1\u00111NA9\u0005[D\u0011ba\u00058\u0003\u0003\u0005\u001da!\u0006\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\u00020\u0006U&Q\u001e\u0005\b\u0003s;\u0004\u0019AA\u0017\u0011%\tyj\u000eI\u0001\u0002\u0004\ti#\u0006\u0003\u0004\u001e\r\u0015B\u0003BB\u0010\u0007\u000b\"ba!\t\u0004:\r}\u0002\u0003\u00020\u0001\u0007G\u00012aYB\u0013\t)\u0011\t\u0010\u000fQ\u0001\u0002\u0003\u0015\rA\u001a\u0015\f\u0007Kq7\u0011FB\u0017\u0007c\u0019)$\r\u0004$eN\u001cY\u0003^\u0019\u0005IYT(,\r\u0004$yv\u001cyC`\u0019\u0005IYT(,M\u0005$\u0003\u0007\t)aa\r\u0002\bE\"AE\u001e>[c%\u0019\u0013QBA\b\u0007o\t\t\"\r\u0003%mjT\u0006\"CB\u001eq\u0005\u0005\t9AB\u001f\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0007\u0003W\n\tha\t\t\u0013\r\u0005\u0003(!AA\u0004\r\r\u0013AC3wS\u0012,gnY3%kA1\u0011qVA[\u0007GAqaa\u00129\u0001\u0004\u0019I%\u0001\u0004wC2,Xm\u001d\t\u00061\u0006%21E\u000b\u0005\u0007\u001b\u001a)\u0006\u0006\u0003\u0004P\r%D\u0003CB)\u0007/\u001aifa\u0019\u0011\ty\u000311\u000b\t\u0004G\u000eUCA\u0002Bys\t\u0007a\rC\u0005\u0004Ze\n\t\u0011q\u0001\u0004\\\u0005QQM^5eK:\u001cW\r\n\u001c\u0011\r\u0005E\u0015qSB*\u0011%\u0019y&OA\u0001\u0002\b\u0019\t'\u0001\u0006fm&$WM\\2fI]\u0002b!a\u001b\u0002r\rM\u0003\"CB3s\u0005\u0005\t9AB4\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0007\u0003_\u000b)la\u0015\t\u000f\r\u001d\u0013\b1\u0001\u0004lA)\u0001l!\u001c\u0004T%\u00191qN-\u0003\u0015q\u0012X\r]3bi\u0016$g(\u0001\u0003gS2dW\u0003BB;\u0007\u007f\"Baa\u001e\u0004.R!1\u0011PBS)!\u0019Yha%\u0004\u001a\u000e}\u0005\u0003\u00020\u0001\u0007{\u00022aYB@\t)\u0011\tP\u000fQ\u0001\u0002\u0003\u0015\rA\u001a\u0015\f\u0007\u007fr71QBD\u0007\u0017\u001by)\r\u0004$eN\u001c)\t^\u0019\u0005IYT(,\r\u0004$yv\u001cII`\u0019\u0005IYT(,M\u0005$\u0003\u0007\t)a!$\u0002\bE\"AE\u001e>[c%\u0019\u0013QBA\b\u0007#\u000b\t\"\r\u0003%mjT\u0006\"CBKu\u0005\u0005\t9ABL\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0007\u0003#\u000b9j! \t\u0013\rm%(!AA\u0004\ru\u0015aC3wS\u0012,gnY3%cA\u0002b!a\u001b\u0002r\ru\u0004\"CBQu\u0005\u0005\t9ABR\u0003-)g/\u001b3f]\u000e,G%M\u0019\u0011\r\u0005=\u0016QWB?\u0011!\t9O\u000fCA\u0002\r\u001d\u0006#\u0002-\u0004*\u000eu\u0014bABV3\nAAHY=oC6,g\bC\u0004\u0002:j\u0002\r!!\f\u0002\u0011Q\f'-\u001e7bi\u0016,Baa-\u0004>R!1QWBw)\u0011\u00199la9\u0015\u0011\re6\u0011[Bl\u0007;\u0004BA\u0018\u0001\u0004<B\u00191m!0\u0005\u0015\tE8\b)A\u0001\u0002\u000b\u0007a\rK\u0006\u0004>:\u001c\tm!2\u0004J\u000e5\u0017GB\u0012sg\u000e\rG/\r\u0003%mjT\u0016GB\u0012}{\u000e\u001dg0\r\u0003%mjT\u0016'C\u0012\u0002\u0004\u0005\u001511ZA\u0004c\u0011!cO\u001f.2\u0013\r\ni!a\u0004\u0004P\u0006E\u0011\u0007\u0002\u0013wujC\u0011ba5<\u0003\u0003\u0005\u001da!6\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u0007\u0003#\u000b9ja/\t\u0013\re7(!AA\u0004\rm\u0017aC3wS\u0012,gnY3%cM\u0002b!a\u001b\u0002r\rm\u0006\"CBpw\u0005\u0005\t9ABq\u0003-)g/\u001b3f]\u000e,G%\r\u001b\u0011\r\u0005=\u0016QWB^\u0011\u001d\u0019)o\u000fa\u0001\u0007O\f\u0011A\u001a\t\b1\u000e%\u0018QFB^\u0013\r\u0019Y/\u0017\u0002\n\rVt7\r^5p]FBq!!/<\u0001\u0004\ti#\u0006\u0003\u0004r\u000emH\u0003BBz\t+!Ba!>\u0005\u0010QA1q_B\u007f\t\u0007!I\u0001\u0005\u0003_\u0001\re\bcA2\u0004|\u00121!\u0011\u001f\u001fC\u0002\u0019D\u0011ba@=\u0003\u0003\u0005\u001d\u0001\"\u0001\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\u000e\t\u0007\u0003#\u000b9j!?\t\u0013\u0011\u0015A(!AA\u0004\u0011\u001d\u0011aC3wS\u0012,gnY3%cY\u0002b!a\u001b\u0002r\re\b\"\u0003C\u0006y\u0005\u0005\t9\u0001C\u0007\u0003-)g/\u001b3f]\u000e,G%M\u001c\u0011\r\u0005=\u0016QWB}\u0011\u001d\u00199\u0005\u0010a\u0001\t#\u0001R\u0001WB7\t'\u0001r\u0001WA\u007f\u0003[\u0019I\u0010C\u0004\u0002^q\u0002\r!!\f\u0003\u001d\r\u000bgnQ8qs\n+\u0018\u000e\u001c3feV!A1\u0004C\u0017'\u0011it\u000b\"\b\u0011\r\u0011}AQ\u0005C\u0015\u001b\t!\tCC\u0002\u0005$A\u000bqa];qa>\u0014H/\u0003\u0003\u0005(\u0011\u0005\"aB\"b]\u000e{\u0007/\u001f\t\u0005=\u0002!Y\u0003E\u0002d\t[!!B!=>A\u0003\u0005\tQ1\u0001gQ-!iC\u001cC\u0019\tk!I\u0004\"\u00102\r\r\u00128\u000fb\ruc\u0011!cO\u001f.2\r\rbX\u0010b\u000e\u007fc\u0011!cO\u001f.2\u0013\r\n\u0019!!\u0002\u0005<\u0005\u001d\u0011\u0007\u0002\u0013wuj\u000b\u0014bIA\u0007\u0003\u001f!y$!\u00052\t\u00112(PW\u0001\fKZLG-\u001a8dK\u0012\n\u0004\b\u0005\u0004\u0002\u0012\u0006]E1F\u0001\fKZLG-\u001a8dK\u0012\n\u0014\b\u0005\u0004\u0002l\u0005ED1F\u0001\fKZLG-\u001a8dK\u0012\u0012\u0004\u0007\u0005\u0004\u00020\u0006UF1\u0006\u000b\u0003\t\u001f\"\u0002\u0002\"\u0015\u0005V\u0011]C\u0011\f\t\u0006\t'jD1F\u0007\u0002k!9A\u0011I!A\u0004\u0011\r\u0003b\u0002C#\u0003\u0002\u000fAq\t\u0005\b\t\u0013\n\u00059\u0001C&)\u0011!I\u0003\"\u0018\t\u000f\u0011}#\t1\u0001\u0005*\u0005\u0011a/\r\u0002\u0010\u0007\u0006t',\u001a:pg\n+\u0018\u000e\u001c3feV!AQ\rC9'\u0011\u0019u\u000bb\u001a\u0011\u0011\u0011}A\u0011\u000eC7\t[JA\u0001b\u001b\u0005\"\t\u00112)\u00198De\u0016\fG/\u001a.fe>\u001cH*[6f!\u0011q\u0006\u0001b\u001c\u0011\u0007\r$\t\b\u0002\u0006\u0003r\u000e\u0003\u000b\u0011!AC\u0002\u0019D3\u0002\"\u001do\tk\"I\b\" \u0005\u0002F21E]:\u0005xQ\fD\u0001\n<{5F21\u0005`?\u0005|y\fD\u0001\n<{5FJ1%a\u0001\u0002\u0006\u0011}\u0014qA\u0019\u0005IYT(,M\u0005$\u0003\u001b\ty\u0001b!\u0002\u0012E\"AE\u001e>[\u0003-)g/\u001b3f]\u000e,GEM\u0019\u0011\r\u0005E\u0015q\u0013C8\u0003-)g/\u001b3f]\u000e,GE\r\u001a\u0011\r\u0005-\u0014\u0011\u000fC8\u0003-)g/\u001b3f]\u000e,GEM\u001a\u0011\r\u0005=\u0016Q\u0017C8)\t!\u0019\n\u0006\u0005\u0005\u0016\u0012]E\u0011\u0014CN!\u0015!\u0019f\u0011C8\u0011\u001d!)i\u0012a\u0002\t\u000fCq\u0001\"#H\u0001\b!Y\tC\u0004\u0005\u000e\u001e\u0003\u001d\u0001b$\u0015\t\u00115Dq\u0014\u0005\b\t?B\u0005\u0019\u0001C7\u00039\u0019\u0017M\\\"paf\u0014U/\u001b7eKJ,B\u0001\"*\u0005,RAAq\u0015C`\t\u000b$Y\rE\u0003\u0005Tu\"I\u000bE\u0002d\tW#!B!=JA\u0003\u0005\tQ1\u0001gQ-!YK\u001cCX\tg#9\fb/2\r\r\u00128\u000f\"-uc\u0011!cO\u001f.2\r\rbX\u0010\".\u007fc\u0011!cO\u001f.2\u0013\r\n\u0019!!\u0002\u0005:\u0006\u001d\u0011\u0007\u0002\u0013wuj\u000b\u0014bIA\u0007\u0003\u001f!i,!\u00052\t\u00112(P\u0017\u0005\n\t\u0003L\u0015\u0011!a\u0002\t\u0007\f1\"\u001a<jI\u0016t7-\u001a\u00133iA1\u0011\u0011SAL\tSC\u0011\u0002b2J\u0003\u0003\u0005\u001d\u0001\"3\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#'\u000e\t\u0007\u0003W\n\t\b\"+\t\u0013\u00115\u0017*!AA\u0004\u0011=\u0017aC3wS\u0012,gnY3%eY\u0002b!a,\u00026\u0012%\u0016aD2b]j+'o\\:Ck&dG-\u001a:\u0016\t\u0011UG1\u001c\u000b\t\t/$y\u000f\">\u0005|B)A1K\"\u0005ZB\u00191\rb7\u0005\u0015\tE(\n)A\u0001\u0002\u000b\u0007a\rK\u0006\u0005\\:$y\u000eb9\u0005h\u0012-\u0018GB\u0012sg\u0012\u0005H/\r\u0003%mjT\u0016GB\u0012}{\u0012\u0015h0\r\u0003%mjT\u0016'C\u0012\u0002\u0004\u0005\u0015A\u0011^A\u0004c\u0011!cO\u001f.2\u0013\r\ni!a\u0004\u0005n\u0006E\u0011\u0007\u0002\u0013wujC\u0011\u0002\"=K\u0003\u0003\u0005\u001d\u0001b=\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#g\u000e\t\u0007\u0003#\u000b9\n\"7\t\u0013\u0011](*!AA\u0004\u0011e\u0018aC3wS\u0012,gnY3%ea\u0002b!a\u001b\u0002r\u0011e\u0007\"\u0003C\u007f\u0015\u0006\u0005\t9\u0001C\u0000\u0003-)g/\u001b3f]\u000e,GEM\u001d\u0011\r\u0005=\u0016Q\u0017Cm\u00039\u0019\u0017M\u001c.fe>\u0014U/\u001b7eKJ,B!\"\u0002\u0006\u0012QAQqAC\u0013\u000bW)\t\u0004\u0005\u0005\u0005 \u0015%QQBA\u0017\u0013\u0011)Y\u0001\"\t\u0003\u001d\r\u000bgn\u0011:fCR,',\u001a:pgB!a\fAC\b!\r\u0019W\u0011\u0003\u0003\u000b\u0005c\\\u0005\u0015!A\u0001\u0006\u00041\u0007fCC\t]\u0016UQ\u0011DC\u000f\u000bC\tda\t:t\u000b/!\u0018\u0007\u0002\u0013wuj\u000bda\t?~\u000b7q\u0018\u0007\u0002\u0013wuj\u000b\u0014bIA\u0002\u0003\u000b)y\"a\u00022\t\u00112(PW\u0019\nG\u00055\u0011qBC\u0012\u0003#\tD\u0001\n<{5\"IQqE&\u0002\u0002\u0003\u000fQ\u0011F\u0001\fKZLG-\u001a8dK\u0012\u001a\u0004\u0007\u0005\u0004\u0002l\u0005ETq\u0002\u0005\n\u000b[Y\u0015\u0011!a\u0002\u000b_\t1\"\u001a<jI\u0016t7-\u001a\u00134cA1\u0011qVA[\u000b\u001fA\u0011\"b\rL\u0003\u0003\u0005\u001d!\"\u000e\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$3G\r\t\u0007\u0003#\u000b9*b\u0004\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0011)Y$b\u0010\u0016\u0005\u0015u\"\u0006BA\u0017\u0005c\"\u0011\"\u001a'!\u0002\u0003\u0005)\u0019\u00014)\u0017\u0015}b.b\u0011\u0006H\u0015-SqJ\u0019\u0007GI\u001cXQ\t;2\t\u00112(PW\u0019\u0007GqlX\u0011\n@2\t\u00112(PW\u0019\nG\u0005\r\u0011QAC'\u0003\u000f\tD\u0001\n<{5FJ1%!\u0004\u0002\u0010\u0015E\u0013\u0011C\u0019\u0005IYT(,A\b{KJ|7\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0011)Y$b\u0016\u0005\u0015\tEX\n)A\u0001\u0002\u000b\u0007a\rK\u0006\u0006X9,Y&b\u0018\u0006d\u0015\u001d\u0014GB\u0012sg\u0016uC/\r\u0003%mjT\u0016GB\u0012}{\u0016\u0005d0\r\u0003%mjT\u0016'C\u0012\u0002\u0004\u0005\u0015QQMA\u0004c\u0011!cO\u001f.2\u0013\r\ni!a\u0004\u0006j\u0005E\u0011\u0007\u0002\u0013wuj\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!b\u001c\u0011\t\u0015ETqO\u0007\u0003\u000bgRA!\"\u001e\u0003\\\u0006!A.\u00198h\u0013\u0011)I(b\u001d\u0003\r=\u0013'.Z2u\u0001"
)
public class VectorBuilder implements NumericOps, Serializable {
   private static final long serialVersionUID = 1L;
   private int[] breeze$linalg$VectorBuilder$$_index;
   public Object _data;
   private int breeze$linalg$VectorBuilder$$used;
   private int length;
   public final Semiring ring;

   public static int zeros$default$2() {
      return VectorBuilder$.MODULE$.zeros$default$2();
   }

   public static int $lessinit$greater$default$2() {
      return VectorBuilder$.MODULE$.$lessinit$greater$default$2();
   }

   public static CanCreateZeros canZeroBuilder(final Semiring evidence$30, final Zero evidence$31, final ClassTag evidence$32) {
      return VectorBuilder$.MODULE$.canZeroBuilder(evidence$30, evidence$31, evidence$32);
   }

   public static CanZerosBuilder canZerosBuilder(final ClassTag evidence$27, final Semiring evidence$28, final Zero evidence$29) {
      return VectorBuilder$.MODULE$.canZerosBuilder(evidence$27, evidence$28, evidence$29);
   }

   public static CanCopyBuilder canCopyBuilder(final ClassTag evidence$24, final Semiring evidence$25, final Zero evidence$26) {
      return VectorBuilder$.MODULE$.canCopyBuilder(evidence$24, evidence$25, evidence$26);
   }

   public static VectorBuilder tabulate(final int size, final Function1 f, final ClassTag evidence$12, final Semiring evidence$13, final Zero evidence$14) {
      return VectorBuilder$.MODULE$.tabulate(size, f, evidence$12, evidence$13, evidence$14);
   }

   public static VectorBuilder fill(final int size, final Function0 v, final ClassTag evidence$9, final Semiring evidence$10, final Zero evidence$11) {
      return VectorBuilder$.MODULE$.fill(size, v, evidence$9, evidence$10, evidence$11);
   }

   public static VectorBuilder zeros(final int size, final int initialNonZero, final ClassTag evidence$1, final Semiring evidence$2, final Zero evidence$3) {
      return VectorBuilder$.MODULE$.zeros(size, initialNonZero, evidence$1, evidence$2, evidence$3);
   }

   public static UFunc.UImpl2 canMulDMVB_Semi(final ClassTag evidence$17, final Semiring semi) {
      return VectorBuilder$.MODULE$.canMulDMVB_Semi(evidence$17, semi);
   }

   public static UFunc.UImpl2 canMulDMVB_Long() {
      return VectorBuilder$.MODULE$.canMulDMVB_Long();
   }

   public static UFunc.UImpl2 canMulDMVB_Float() {
      return VectorBuilder$.MODULE$.canMulDMVB_Float();
   }

   public static UFunc.UImpl2 canMulDMVB_Int() {
      return VectorBuilder$.MODULE$.canMulDMVB_Int();
   }

   public static UFunc.UImpl2 canMulDMVB_Double() {
      return VectorBuilder$.MODULE$.canMulDMVB_Double();
   }

   public static UFunc.UImpl2 canDot_VB_V(final .less.colon.less ev, final Semiring semi) {
      return VectorBuilder$.MODULE$.canDot_VB_V(ev, semi);
   }

   public static UFunc.InPlaceImpl3 canAxpy_V_VB_Semi(final .less.colon.less ev, final Semiring semi) {
      return VectorBuilder$.MODULE$.canAxpy_V_VB_Semi(ev, semi);
   }

   public static UFunc.UImpl2 canDot_V_VB(final .less.colon.less ev, final Semiring semi) {
      return VectorBuilder$.MODULE$.canDot_V_VB(ev, semi);
   }

   public static UFunc.InPlaceImpl2 canSubInto_VV_V(final .less.colon.less ev, final Ring ring) {
      return VectorBuilder$.MODULE$.canSubInto_VV_V(ev, ring);
   }

   public static UFunc.InPlaceImpl2 canAddInto_VV_V(final .less.colon.less ev) {
      return VectorBuilder$.MODULE$.canAddInto_VV_V(ev);
   }

   public static UFunc.InPlaceImpl2 canSubInto_V_VB(final .less.colon.less ev, final Ring semi) {
      return VectorBuilder$.MODULE$.canSubInto_V_VB(ev, semi);
   }

   public static UFunc.InPlaceImpl2 canAddInto_V_VB(final .less.colon.less ev, final Semiring semi) {
      return VectorBuilder$.MODULE$.canAddInto_V_VB(ev, semi);
   }

   public static MutableModule space(final Field evidence$15, final ClassTag evidence$16) {
      return VectorBuilder$.MODULE$.space(evidence$15, evidence$16);
   }

   public static UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$13, final ClassTag evidence$14) {
      return VectorBuilder$.MODULE$.canAxpy(evidence$13, evidence$14);
   }

   public static UFunc.InPlaceImpl3 canAxpy_Int() {
      return VectorBuilder$.MODULE$.canAxpy_Int();
   }

   public static UFunc.InPlaceImpl3 canAxpy_Float() {
      return VectorBuilder$.MODULE$.canAxpy_Float();
   }

   public static UFunc.InPlaceImpl3 canAxpy_Long() {
      return VectorBuilder$.MODULE$.canAxpy_Long();
   }

   public static UFunc.InPlaceImpl3 canAxpy_Double() {
      return VectorBuilder$.MODULE$.canAxpy_Double();
   }

   public static UFunc.InPlaceImpl2 canSet() {
      return VectorBuilder$.MODULE$.canSet();
   }

   public static UFunc.InPlaceImpl2 canSet_Int() {
      return VectorBuilder$.MODULE$.canSet_Int();
   }

   public static UFunc.InPlaceImpl2 canSet_Float() {
      return VectorBuilder$.MODULE$.canSet_Float();
   }

   public static UFunc.InPlaceImpl2 canSet_Long() {
      return VectorBuilder$.MODULE$.canSet_Long();
   }

   public static UFunc.InPlaceImpl2 canSet_Double() {
      return VectorBuilder$.MODULE$.canSet_Double();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpSub(final Ring evidence$11, final ClassTag evidence$12) {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpSub(evidence$11, evidence$12);
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpAdd(final Ring evidence$9, final ClassTag evidence$10) {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpAdd(evidence$9, evidence$10);
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub(final Ring evidence$7, final ClassTag evidence$8) {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpSub(evidence$7, evidence$8);
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd(final Ring evidence$5, final ClassTag evidence$6) {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpAdd(evidence$5, evidence$6);
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Int() {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpSub_Int();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Int() {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpAdd_Int();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Float() {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpSub_Float();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Float() {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpAdd_Float();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Long() {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpSub_Long();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Long() {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpAdd_Long();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Double() {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpSub_Double();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Double() {
      return VectorBuilder$.MODULE$.canOpInto_V_V_OpAdd_Double();
   }

   public static UFunc.InPlaceImpl2 canDivInto_V_S(final Field evidence$3, final ClassTag evidence$4) {
      return VectorBuilder$.MODULE$.canDivInto_V_S(evidence$3, evidence$4);
   }

   public static UFunc.InPlaceImpl2 canMulInto_V_S(final Semiring evidence$1, final ClassTag evidence$2) {
      return VectorBuilder$.MODULE$.canMulInto_V_S(evidence$1, evidence$2);
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Int() {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpDiv_Int();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Int() {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpMulScalar_Int();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Float() {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpDiv_Float();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Float() {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpMulScalar_Float();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Long() {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpDiv_Long();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Long() {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpMulScalar_Long();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Double() {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpDiv_Double();
   }

   public static UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Double() {
      return VectorBuilder$.MODULE$.canOpInto_V_S_OpMulScalar_Double();
   }

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public int[] breeze$linalg$VectorBuilder$$_index() {
      return this.breeze$linalg$VectorBuilder$$_index;
   }

   public void breeze$linalg$VectorBuilder$$_index_$eq(final int[] x$1) {
      this.breeze$linalg$VectorBuilder$$_index = x$1;
   }

   public Object _data() {
      return this._data;
   }

   public void _data_$eq(final Object x$1) {
      this._data = x$1;
   }

   public int breeze$linalg$VectorBuilder$$used() {
      return this.breeze$linalg$VectorBuilder$$used;
   }

   public void breeze$linalg$VectorBuilder$$used_$eq(final int x$1) {
      this.breeze$linalg$VectorBuilder$$used = x$1;
   }

   public int length() {
      return this.length;
   }

   public void length_$eq(final int x$1) {
      this.length = x$1;
   }

   public int size() {
      return this.length();
   }

   public Object data() {
      return this._data();
   }

   public int[] index() {
      return this.breeze$linalg$VectorBuilder$$_index();
   }

   public int activeSize() {
      return this.breeze$linalg$VectorBuilder$$used();
   }

   public VectorBuilder repr() {
      return this;
   }

   public boolean contains(final int i) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.intArrayOps(this.breeze$linalg$VectorBuilder$$_index()), BoxesRunTime.boxToInteger(i));
   }

   public Object apply(final int i) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      int off = 0;

      Object acc;
      for(acc = this.ring.zero(); off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            acc = this.ring.$plus(acc, scala.runtime.ScalaRunTime..MODULE$.array_apply(this._data(), off));
         }
      }

      return acc;
   }

   public void breeze$linalg$VectorBuilder$$boundsCheck(final int i) {
      if (this.length() >= 0 && (i < 0 || i >= this.size())) {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" not in [0,").append(this.size()).append(")").toString());
      }
   }

   public void update(final int i, final Object v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      boolean marked = false;

      for(int off = 0; off < this.breeze$linalg$VectorBuilder$$used(); ++off) {
         if (this.breeze$linalg$VectorBuilder$$_index()[off] == i) {
            if (!marked) {
               scala.runtime.ScalaRunTime..MODULE$.array_update(this._data(), off, v);
            } else {
               scala.runtime.ScalaRunTime..MODULE$.array_update(this._data(), off, this.ring.zero());
            }

            marked = true;
         }
      }

   }

   public void add(final int i, final Object v) {
      this.breeze$linalg$VectorBuilder$$boundsCheck(i);
      if (scala.runtime.ScalaRunTime..MODULE$.array_length(this._data()) <= this.breeze$linalg$VectorBuilder$$used()) {
         this.breeze$linalg$VectorBuilder$$reallocate(scala.math.package..MODULE$.max(scala.runtime.ScalaRunTime..MODULE$.array_length(this._data()) * 2, 1));
      }

      scala.runtime.ScalaRunTime..MODULE$.array_update(this._data(), this.breeze$linalg$VectorBuilder$$used(), v);
      this.breeze$linalg$VectorBuilder$$_index()[this.breeze$linalg$VectorBuilder$$used()] = i;
      this.breeze$linalg$VectorBuilder$$used_$eq(this.breeze$linalg$VectorBuilder$$used() + 1);
   }

   public Iterator activeIterator() {
      return this.toHashVector().activeIterator();
   }

   public Iterator activeValuesIterator() {
      return this.toHashVector().activeValuesIterator();
   }

   public Iterator activeKeysIterator() {
      return this.toHashVector().activeKeysIterator();
   }

   public Object default() {
      return this.ring.zero();
   }

   public boolean isActive(final int rawIndex) {
      return rawIndex < this.breeze$linalg$VectorBuilder$$used() && rawIndex > 0;
   }

   public String toString() {
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.intArrayOps(this.index())).zip(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(this.data()))).take(this.breeze$linalg$VectorBuilder$$used()).mkString((new StringBuilder(16)).append("VectorBuilder(").append(this.length()).append(")(").toString(), ", ", ")");
   }

   public VectorBuilder copy() {
      return new VectorBuilder((int[])ArrayUtil$.MODULE$.copyOf(this.index(), this.index().length), ArrayUtil$.MODULE$.copyOf(this.data(), this.index().length), this.activeSize(), this.size(), this.ring);
   }

   public VectorBuilder zerosLike() {
      return new VectorBuilder(new int[0], ArrayUtil$.MODULE$.newArrayLike(this.data(), 0), 0, this.size(), this.ring);
   }

   public void reserve(final int nnz) {
      if (nnz < scala.runtime.ScalaRunTime..MODULE$.array_length(this._data())) {
         this.breeze$linalg$VectorBuilder$$reallocate(nnz);
      }

   }

   public void breeze$linalg$VectorBuilder$$reallocate(final int nnz) {
      this.breeze$linalg$VectorBuilder$$_index_$eq((int[])ArrayUtil$.MODULE$.copyOf(this.breeze$linalg$VectorBuilder$$_index(), nnz));
      this._data_$eq(ArrayUtil$.MODULE$.copyOf(this.data(), nnz));
   }

   public HashVector toHashVector() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      HashVector hv = HashVector$.MODULE$.zeros(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update(this.index()[i], this.ring.$plus(hv.apply(this.index()[i]), scala.runtime.ScalaRunTime..MODULE$.array_apply(this.data(), i)));
      }

      return hv;
   }

   public void breeze$linalg$VectorBuilder$$requirePositiveLength() {
      if (this.size() < 0) {
         throw new UnsupportedOperationException("Can't make a vector with a negative length!");
      }
   }

   public DenseVector toDenseVector() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseVector hv = DenseVector$.MODULE$.zeros(this.length(), man, Zero$.MODULE$.zeroFromSemiring(this.ring));

      for(int i = 0; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
         hv.update(this.index()[i], this.ring.$plus(hv.apply(this.index()[i]), scala.runtime.ScalaRunTime..MODULE$.array_apply(this.data(), i)));
      }

      return hv;
   }

   public SparseVector toSparseVector() {
      return this.toSparseVector(this.toSparseVector$default$1(), this.toSparseVector$default$2());
   }

   public SparseVector toSparseVector(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      int[] index = this.index();
      Object values = this.data();
      if (alreadySorted && keysAlreadyUnique) {
         return new SparseVector(index, values, this.breeze$linalg$VectorBuilder$$used(), this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring));
      } else {
         int[] outIndex = (int[])ArrayUtil$.MODULE$.copyOf(index, this.breeze$linalg$VectorBuilder$$used());
         Object outValues = ArrayUtil$.MODULE$.copyOf(values, this.breeze$linalg$VectorBuilder$$used());
         if (!alreadySorted) {
            Sorting$.MODULE$.indirectSort((int[])outIndex, outValues, 0, this.breeze$linalg$VectorBuilder$$used());
         }

         if (outIndex.length > 0) {
            if (outIndex[this.breeze$linalg$VectorBuilder$$used() - 1] >= this.length()) {
               throw new IndexOutOfBoundsException((new StringBuilder(25)).append("Index ").append(index[this.breeze$linalg$VectorBuilder$$used() - 1]).append(" exceeds dimension ").append(this.length()).toString());
            }

            if (outIndex[0] < 0) {
               throw new IndexOutOfBoundsException((new StringBuilder(21)).append("Index ").append(outIndex[0]).append(" is less than 0").toString());
            }
         }

         int i = 1;
         int out = 0;
         if (keysAlreadyUnique) {
            out = this.breeze$linalg$VectorBuilder$$used();
         } else {
            for(; i < this.breeze$linalg$VectorBuilder$$used(); ++i) {
               if (outIndex[out] == outIndex[i]) {
                  scala.runtime.ScalaRunTime..MODULE$.array_update(outValues, out, this.ring.$plus(scala.runtime.ScalaRunTime..MODULE$.array_apply(outValues, out), scala.runtime.ScalaRunTime..MODULE$.array_apply(outValues, i)));
               } else {
                  ++out;
                  outIndex[out] = outIndex[i];
                  scala.runtime.ScalaRunTime..MODULE$.array_update(outValues, out, scala.runtime.ScalaRunTime..MODULE$.array_apply(outValues, i));
               }
            }

            if (outIndex.length > 0) {
               ++out;
            }
         }

         return new SparseVector(outIndex, outValues, out, this.length(), Zero$.MODULE$.zeroFromSemiring(this.ring));
      }
   }

   public boolean toSparseVector$default$1() {
      return false;
   }

   public boolean toSparseVector$default$2() {
      return false;
   }

   public void compact() {
      SparseVector ah = this.toSparseVector();
      this.clear();
      this.breeze$linalg$VectorBuilder$$reallocate(ah.activeSize());

      for(int i = 0; i < ah.iterableSize(); ++i) {
         if (ah.isActive(i)) {
            this.add(ah.index()[i], scala.runtime.ScalaRunTime..MODULE$.array_apply(ah.data(), i));
         }
      }

   }

   public void clear() {
      this.breeze$linalg$VectorBuilder$$used_$eq(0);
   }

   public boolean equals(final Object p1) {
      boolean var7;
      if (this != p1) {
         boolean var2;
         if (!(p1 instanceof VectorBuilder)) {
            var2 = false;
         } else {
            label31: {
               label30: {
                  VectorBuilder var4 = (VectorBuilder)p1;
                  if (this.length() == var4.length()) {
                     HashVector var10000 = var4.toHashVector();
                     HashVector var5 = this.toHashVector();
                     if (var10000 == null) {
                        if (var5 == null) {
                           break label30;
                        }
                     } else if (var10000.equals(var5)) {
                        break label30;
                     }
                  }

                  var7 = false;
                  break label31;
               }

               var7 = true;
            }

            var2 = var7;
         }

         if (!var2) {
            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public void use(final int[] index, final Object data, final int activeSize) {
      scala.Predef..MODULE$.require(activeSize >= 0, () -> "activeSize must be non-negative");
      scala.Predef..MODULE$.require(scala.runtime.ScalaRunTime..MODULE$.array_length(data) >= activeSize, () -> "activeSize must be no greater than array length...");
      this._data_$eq(data);
      this.breeze$linalg$VectorBuilder$$_index_$eq(index);
      this.breeze$linalg$VectorBuilder$$used_$eq(activeSize);
   }

   public Object valueAt(final int i) {
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(this.data(), i);
   }

   public int indexAt(final int i) {
      return this.index()[i];
   }

   public boolean allVisitableIndicesActive() {
      return true;
   }

   public Vector toVector() {
      this.breeze$linalg$VectorBuilder$$requirePositiveLength();
      return (Vector)(this.size() >= 40 && this.activeSize() <= this.size() / 4 ? this.toSparseVector() : this.toDenseVector());
   }

   public double[] _data$mcD$sp() {
      return (double[])this._data();
   }

   public float[] _data$mcF$sp() {
      return (float[])this._data();
   }

   public int[] _data$mcI$sp() {
      return (int[])this._data();
   }

   public long[] _data$mcJ$sp() {
      return (long[])this._data();
   }

   public void _data$mcD$sp_$eq(final double[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcF$sp_$eq(final float[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcI$sp_$eq(final int[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcJ$sp_$eq(final long[] x$1) {
      this._data_$eq(x$1);
   }

   public double[] data$mcD$sp() {
      return (double[])this.data();
   }

   public float[] data$mcF$sp() {
      return (float[])this.data();
   }

   public int[] data$mcI$sp() {
      return (int[])this.data();
   }

   public long[] data$mcJ$sp() {
      return (long[])this.data();
   }

   public VectorBuilder repr$mcD$sp() {
      return this.repr();
   }

   public VectorBuilder repr$mcF$sp() {
      return this.repr();
   }

   public VectorBuilder repr$mcI$sp() {
      return this.repr();
   }

   public VectorBuilder repr$mcJ$sp() {
      return this.repr();
   }

   public double apply$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.apply(i));
   }

   public float apply$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.apply(i));
   }

   public int apply$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.apply(i));
   }

   public long apply$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.apply(i));
   }

   public void update$mcD$sp(final int i, final double v) {
      this.update(i, BoxesRunTime.boxToDouble(v));
   }

   public void update$mcF$sp(final int i, final float v) {
      this.update(i, BoxesRunTime.boxToFloat(v));
   }

   public void update$mcI$sp(final int i, final int v) {
      this.update(i, BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJ$sp(final int i, final long v) {
      this.update(i, BoxesRunTime.boxToLong(v));
   }

   public void add$mcD$sp(final int i, final double v) {
      this.add(i, BoxesRunTime.boxToDouble(v));
   }

   public void add$mcF$sp(final int i, final float v) {
      this.add(i, BoxesRunTime.boxToFloat(v));
   }

   public void add$mcI$sp(final int i, final int v) {
      this.add(i, BoxesRunTime.boxToInteger(v));
   }

   public void add$mcJ$sp(final int i, final long v) {
      this.add(i, BoxesRunTime.boxToLong(v));
   }

   public double default$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.default());
   }

   public float default$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.default());
   }

   public int default$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.default());
   }

   public long default$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.default());
   }

   public VectorBuilder copy$mcD$sp() {
      return this.copy();
   }

   public VectorBuilder copy$mcF$sp() {
      return this.copy();
   }

   public VectorBuilder copy$mcI$sp() {
      return this.copy();
   }

   public VectorBuilder copy$mcJ$sp() {
      return this.copy();
   }

   public VectorBuilder zerosLike$mcD$sp() {
      return this.zerosLike();
   }

   public VectorBuilder zerosLike$mcF$sp() {
      return this.zerosLike();
   }

   public VectorBuilder zerosLike$mcI$sp() {
      return this.zerosLike();
   }

   public VectorBuilder zerosLike$mcJ$sp() {
      return this.zerosLike();
   }

   public HashVector toHashVector$mcD$sp() {
      return this.toHashVector();
   }

   public HashVector toHashVector$mcF$sp() {
      return this.toHashVector();
   }

   public HashVector toHashVector$mcI$sp() {
      return this.toHashVector();
   }

   public HashVector toHashVector$mcJ$sp() {
      return this.toHashVector();
   }

   public DenseVector toDenseVector$mcD$sp() {
      return this.toDenseVector();
   }

   public DenseVector toDenseVector$mcF$sp() {
      return this.toDenseVector();
   }

   public DenseVector toDenseVector$mcI$sp() {
      return this.toDenseVector();
   }

   public DenseVector toDenseVector$mcJ$sp() {
      return this.toDenseVector();
   }

   public SparseVector toSparseVector$mcD$sp() {
      return this.toSparseVector();
   }

   public SparseVector toSparseVector$mcF$sp() {
      return this.toSparseVector();
   }

   public SparseVector toSparseVector$mcI$sp() {
      return this.toSparseVector();
   }

   public SparseVector toSparseVector$mcJ$sp() {
      return this.toSparseVector();
   }

   public SparseVector toSparseVector$mcD$sp(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      return this.toSparseVector(alreadySorted, keysAlreadyUnique);
   }

   public SparseVector toSparseVector$mcF$sp(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      return this.toSparseVector(alreadySorted, keysAlreadyUnique);
   }

   public SparseVector toSparseVector$mcI$sp(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      return this.toSparseVector(alreadySorted, keysAlreadyUnique);
   }

   public SparseVector toSparseVector$mcJ$sp(final boolean alreadySorted, final boolean keysAlreadyUnique) {
      return this.toSparseVector(alreadySorted, keysAlreadyUnique);
   }

   public void use$mcD$sp(final int[] index, final double[] data, final int activeSize) {
      this.use(index, data, activeSize);
   }

   public void use$mcF$sp(final int[] index, final float[] data, final int activeSize) {
      this.use(index, data, activeSize);
   }

   public void use$mcI$sp(final int[] index, final int[] data, final int activeSize) {
      this.use(index, data, activeSize);
   }

   public void use$mcJ$sp(final int[] index, final long[] data, final int activeSize) {
      this.use(index, data, activeSize);
   }

   public double valueAt$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.valueAt(i));
   }

   public float valueAt$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.valueAt(i));
   }

   public int valueAt$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.valueAt(i));
   }

   public long valueAt$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.valueAt(i));
   }

   public Vector toVector$mcD$sp() {
      return this.toVector();
   }

   public Vector toVector$mcF$sp() {
      return this.toVector();
   }

   public Vector toVector$mcI$sp() {
      return this.toVector();
   }

   public Vector toVector$mcJ$sp() {
      return this.toVector();
   }

   public boolean specInstance$() {
      return false;
   }

   public VectorBuilder(final int[] _index, final Object _data, final int used, final int length, final Semiring ring) {
      this.breeze$linalg$VectorBuilder$$_index = _index;
      this._data = _data;
      this.breeze$linalg$VectorBuilder$$used = used;
      this.length = length;
      this.ring = ring;
      super();
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
   }

   public VectorBuilder(final int length, final int initialNonZero, final Semiring ring, final ClassTag man) {
      this(new int[initialNonZero], man.newArray(initialNonZero), 0, length, ring);
   }

   public VectorBuilder(final Semiring ring, final ClassTag man, final Zero zero) {
      this(-1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), ring, man);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class CanCopyBuilder implements CanCopy {
      public final Semiring evidence$19;
      public final Zero evidence$20;

      public VectorBuilder apply(final VectorBuilder v1) {
         return v1.copy();
      }

      public VectorBuilder apply$mcD$sp(final VectorBuilder v1) {
         return this.apply(v1);
      }

      public VectorBuilder apply$mcF$sp(final VectorBuilder v1) {
         return this.apply(v1);
      }

      public VectorBuilder apply$mcI$sp(final VectorBuilder v1) {
         return this.apply(v1);
      }

      public VectorBuilder apply$mcJ$sp(final VectorBuilder v1) {
         return this.apply(v1);
      }

      public CanCopyBuilder(final ClassTag evidence$18, final Semiring evidence$19, final Zero evidence$20) {
         this.evidence$19 = evidence$19;
         this.evidence$20 = evidence$20;
      }
   }

   public static class CanZerosBuilder implements CanCreateZerosLike {
      public final Semiring evidence$22;
      public final Zero evidence$23;

      public VectorBuilder apply(final VectorBuilder v1) {
         return v1.zerosLike();
      }

      public VectorBuilder apply$mcD$sp(final VectorBuilder v1) {
         return this.apply(v1);
      }

      public VectorBuilder apply$mcF$sp(final VectorBuilder v1) {
         return this.apply(v1);
      }

      public VectorBuilder apply$mcI$sp(final VectorBuilder v1) {
         return this.apply(v1);
      }

      public VectorBuilder apply$mcJ$sp(final VectorBuilder v1) {
         return this.apply(v1);
      }

      public CanZerosBuilder(final ClassTag evidence$21, final Semiring evidence$22, final Zero evidence$23) {
         this.evidence$22 = evidence$22;
         this.evidence$23 = evidence$23;
      }
   }
}
