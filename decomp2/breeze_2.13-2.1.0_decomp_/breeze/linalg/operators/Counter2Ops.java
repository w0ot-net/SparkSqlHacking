package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Counter;
import breeze.linalg.Counter$;
import breeze.linalg.Counter2;
import breeze.linalg.Counter2$;
import breeze.linalg.CounterLike;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanZipMapValues;
import breeze.math.Field;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0005fa\u0002\u0012$!\u0003\r\tA\u000b\u0005\u0006c\u0001!\tA\r\u0005\u0006m\u0001!\u0019a\u000e\u0005\u0006I\u0002!I!\u001a\u0005\b\u0003S\u0001A1AA\u0016\u0011\u001d\t\t\u0006\u0001C\u0002\u0003'Bq!a\u001e\u0001\t\u0007\tI\bC\u0004\u0002\u001e\u0002!\u0019!a(\t\u000f\u0005e\u0006\u0001b\u0001\u0002<\"9\u00111\u001c\u0001\u0005\u0004\u0005u\u0007b\u0002B\u0001\u0001\u0011\r!1\u0001\u0005\b\u0005G\u0001A1\u0001B\u0013\u0011\u001d\u0011y\u0004\u0001C\u0002\u0005\u0003BqA!\u0019\u0001\t\u0007\u0011\u0019\u0007C\u0004\u0003\u0004\u0002!\u0019A!\"\t\u000f\t}\u0005\u0001b\u0001\u0003\"\"9!1\u0018\u0001\u0005\u0004\tu\u0006b\u0002Bo\u0001\u0011\r!q\u001c\u0005\b\u0005o\u0004A1\u0001B}\u0011\u001d\u0019\t\u0002\u0001C\u0002\u0007'Aqaa\u000e\u0001\t\u0007\u0019I\u0004C\u0004\u0004V\u0001!\u0019aa\u0016\t\u000f\rM\u0004\u0001b\u0001\u0004v!91q\u0012\u0001\u0005\u0004\rE\u0005bBBV\u0001\u0011\r1Q\u0016\u0005\b\u0007\u0003\u0004A1ABb\u0011\u001d\u00199\u000f\u0001C\u0002\u0007SDq\u0001\"\u0003\u0001\t\u0007!YA\u0002\u0004\u0005.\u0001\u0001Aq\u0006\u0005\u000b\t\u001fb\"1!Q\u0001\f\u0011E\u0003B\u0003C*9\t\r\t\u0015a\u0003\u0005V!9Aq\u000b\u000f\u0005\u0002\u0011e\u0003b\u0002C39\u0011\u0005Aq\r\u0005\b\tw\u0002A1\u0001C?\u0005-\u0019u.\u001e8uKJ\u0014t\n]:\u000b\u0005\u0011*\u0013!C8qKJ\fGo\u001c:t\u0015\t1s%\u0001\u0004mS:\fGn\u001a\u0006\u0002Q\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0002\u0001WA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\u001a\u0011\u00051\"\u0014BA\u001b.\u0005\u0011)f.\u001b;\u0002\u000f\r\fgnQ8qsV!\u0001(R(S)\rID\u000b\u0018\t\u0004uuzT\"A\u001e\u000b\u0005q*\u0013aB:vaB|'\u000f^\u0005\u0003}m\u0012qaQ1o\u0007>\u0004\u0018\u0010E\u0003A\u0003\u000es\u0015+D\u0001&\u0013\t\u0011UE\u0001\u0005D_VtG/\u001a:3!\t!U\t\u0004\u0001\u0005\u000b\u0019\u0013!\u0019A$\u0003\u0005-\u000b\u0014C\u0001%L!\ta\u0013*\u0003\u0002K[\t9aj\u001c;iS:<\u0007C\u0001\u0017M\u0013\tiUFA\u0002B]f\u0004\"\u0001R(\u0005\u000bA\u0013!\u0019A$\u0003\u0005-\u0013\u0004C\u0001#S\t\u0015\u0019&A1\u0001H\u0005\u00051\u0006bB+\u0003\u0003\u0003\u0005\u001dAV\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004cA,[#6\t\u0001L\u0003\u0002ZO\u000591\u000f^8sC\u001e,\u0017BA.Y\u0005\u0011QVM]8\t\u000fu\u0013\u0011\u0011!a\u0002=\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007}\u0013\u0017+D\u0001a\u0015\t\tw%\u0001\u0003nCRD\u0017BA2a\u0005!\u0019V-\\5sS:<\u0017A\u00072j]\u0006\u0014\u0018p\u00149Ge>l')\u001b8bef,\u0006\u000fZ1uK>\u0003XC\u00034\u0002\n\u00055\u0011\u0011CA\u000bwR)q-!\u0007\u0002 I\u0019\u0001n\u000b6\u0007\t%\u001c\u0001a\u001a\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\nW^T\u0018QAA\n\u0003\u000bq!\u0001\u001c;\u000f\u00055\u0014hB\u00018r\u001b\u0005y'B\u00019*\u0003\u0019a$o\\8u}%\t\u0001&\u0003\u0002tO\u00059q-\u001a8fe&\u001c\u0017BA;w\u0003\u0015)f)\u001e8d\u0015\t\u0019x%\u0003\u0002ys\n1Q+S7qYJR!!\u001e<\u0011\u0005\u0011[H!\u0002?\u0004\u0005\u0004i(AA(q#\tAe\u0010E\u0002\u0000\u0003\u0003i\u0011aI\u0005\u0004\u0003\u0007\u0019#AB(q)f\u0004X\r\u0005\u0005A\u0003\u0006\u001d\u00111BA\b!\r!\u0015\u0011\u0002\u0003\u0006\r\u000e\u0011\ra\u0012\t\u0004\t\u00065A!\u0002)\u0004\u0005\u00049\u0005c\u0001#\u0002\u0012\u0011)1k\u0001b\u0001\u000fB\u0019A)!\u0006\u0005\r\u0005]1A1\u0001H\u0005\u0015yE\u000f[3s\u0011\u001d\tYb\u0001a\u0002\u0003;\tAaY8qsB!!(PA\u0003\u0011\u001d\t\tc\u0001a\u0002\u0003G\t!a\u001c9\u0011\u0011-\f)C_A\u0003\u0003'I1!a\nz\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d73\u0003%\tG\rZ%oi>4f+\u0006\u0005\u0002.\u0005\u0005\u0013QIA%)\u0011\ty#a\u0013\u0011\u0011\u0005E\u0012qGA\u001f\u0003{q1a`A\u001a\u0013\r\t)dI\u0001\u0006\u001fB\fE\rZ\u0005\u0005\u0003O\tI$C\u0002\u0002<Y\u0014Q!\u0016$v]\u000e\u0004\u0002\u0002Q!\u0002@\u0005\r\u0013q\t\t\u0004\t\u0006\u0005C!\u0002$\u0005\u0005\u00049\u0005c\u0001#\u0002F\u0011)\u0001\u000b\u0002b\u0001\u000fB\u0019A)!\u0013\u0005\u000bM#!\u0019A$\t\u0013\u00055C!!AA\u0004\u0005=\u0013AC3wS\u0012,gnY3%gA!qLYA$\u0003\u001d\u0019\u0017M\\!yaf,\u0002\"!\u0016\u0002h\u0005-\u0014q\u000e\u000b\u0005\u0003/\n\t\b\u0005\u0006\u0002Z\u0005}\u00131MA7\u0003Gr1\u0001QA.\u0013\r\ti&J\u0001\tg\u000e\fG.Z!eI&!\u0011\u0011MA\u001d\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d74!!\u0001\u0015)!\u001a\u0002j\u00055\u0004c\u0001#\u0002h\u0011)a)\u0002b\u0001\u000fB\u0019A)a\u001b\u0005\u000bA+!\u0019A$\u0011\u0007\u0011\u000by\u0007B\u0003T\u000b\t\u0007q\tC\u0005\u0002t\u0015\t\t\u0011q\u0001\u0002v\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\t}\u0013\u0017QN\u0001\u0006C\u0012$gKV\u000b\t\u0003w\n9)a#\u0002\u0010R1\u0011QPAI\u0003/\u0003\"\"!\r\u0002\u0000\u0005\r\u00151QAB\u0013\u0011\t\t)!\u000f\u0003\u000b%k\u0007\u000f\u001c\u001a\u0011\u0011\u0001\u000b\u0015QQAE\u0003\u001b\u00032\u0001RAD\t\u00151eA1\u0001H!\r!\u00151\u0012\u0003\u0006!\u001a\u0011\ra\u0012\t\u0004\t\u0006=E!B*\u0007\u0005\u00049\u0005\"CAJ\r\u0005\u0005\t9AAK\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0005?\n\fi\tC\u0005\u0002\u001a\u001a\t\t\u0011q\u0001\u0002\u001c\u0006QQM^5eK:\u001cW\r\n\u001c\u0011\t]S\u0016QR\u0001\nC\u0012$\u0017J\u001c;p-N+\u0002\"!)\u0002*\u00065\u0016\u0011\u0017\u000b\u0005\u0003G\u000b\u0019\f\u0005\u0005\u00022\u0005]\u0012QUAX!!\u0001\u0015)a*\u0002,\u0006=\u0006c\u0001#\u0002*\u0012)ai\u0002b\u0001\u000fB\u0019A)!,\u0005\u000bA;!\u0019A$\u0011\u0007\u0011\u000b\t\fB\u0003T\u000f\t\u0007q\tC\u0005\u00026\u001e\t\t\u0011q\u0001\u00028\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\t}\u0013\u0017qV\u0001\u0006C\u0012$gkU\u000b\t\u0003{\u000b)-!3\u0002NR1\u0011qXAh\u0003+\u0004\"\"!\r\u0002\u0000\u0005\u0005\u00171ZAa!!\u0001\u0015)a1\u0002H\u0006-\u0007c\u0001#\u0002F\u0012)a\t\u0003b\u0001\u000fB\u0019A)!3\u0005\u000bAC!\u0019A$\u0011\u0007\u0011\u000bi\rB\u0003T\u0011\t\u0007q\tC\u0005\u0002R\"\t\t\u0011q\u0001\u0002T\u0006QQM^5eK:\u001cW\r\n\u001d\u0011\t}\u0013\u00171\u001a\u0005\n\u0003/D\u0011\u0011!a\u0002\u00033\f!\"\u001a<jI\u0016t7-\u001a\u0013:!\u00119&,a3\u0002\u0013M,(-\u00138u_Z3V\u0003CAp\u0003[\f\t0!>\u0015\t\u0005\u0005\u0018q\u001f\t\t\u0003G\f9$!;\u0002j:\u0019q0!:\n\u0007\u0005\u001d8%A\u0003PaN+(\r\u0005\u0005A\u0003\u0006-\u0018q^Az!\r!\u0015Q\u001e\u0003\u0006\r&\u0011\ra\u0012\t\u0004\t\u0006EH!\u0002)\n\u0005\u00049\u0005c\u0001#\u0002v\u0012)1+\u0003b\u0001\u000f\"I\u0011\u0011`\u0005\u0002\u0002\u0003\u000f\u00111`\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007E\u0003`\u0003{\f\u00190C\u0002\u0002\u0000\u0002\u0014AAU5oO\u0006)1/\u001e2W-VA!Q\u0001B\u0007\u0005#\u0011)\u0002\u0006\u0004\u0003\b\t]!Q\u0004\t\u000b\u0003G\fyH!\u0003\u0003\n\t%\u0001\u0003\u0003!B\u0005\u0017\u0011yAa\u0005\u0011\u0007\u0011\u0013i\u0001B\u0003G\u0015\t\u0007q\tE\u0002E\u0005#!Q\u0001\u0015\u0006C\u0002\u001d\u00032\u0001\u0012B\u000b\t\u0015\u0019&B1\u0001H\u0011%\u0011IBCA\u0001\u0002\b\u0011Y\"A\u0006fm&$WM\\2fIE\n\u0004#B0\u0002~\nM\u0001\"\u0003B\u0010\u0015\u0005\u0005\t9\u0001B\u0011\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\t]S&1C\u0001\ngV\u0014\u0017J\u001c;p-N+\u0002Ba\n\u00030\tM\"q\u0007\u000b\u0005\u0005S\u0011I\u0004\u0005\u0005\u0002d\u0006]\"1\u0006B\u001b!!\u0001\u0015I!\f\u00032\tU\u0002c\u0001#\u00030\u0011)ai\u0003b\u0001\u000fB\u0019AIa\r\u0005\u000bA[!\u0019A$\u0011\u0007\u0011\u00139\u0004B\u0003T\u0017\t\u0007q\tC\u0005\u0003<-\t\t\u0011q\u0001\u0003>\u0005YQM^5eK:\u001cW\rJ\u00194!\u0015y\u0016Q B\u001b\u0003\u0015\u0019XO\u0019,T+!\u0011\u0019Ea\u0013\u0003P\tMCC\u0002B#\u0005+\u0012Y\u0006\u0005\u0006\u0002d\u0006}$q\tB)\u0005\u000f\u0002\u0002\u0002Q!\u0003J\t5#\u0011\u000b\t\u0004\t\n-C!\u0002$\r\u0005\u00049\u0005c\u0001#\u0003P\u0011)\u0001\u000b\u0004b\u0001\u000fB\u0019AIa\u0015\u0005\u000bMc!\u0019A$\t\u0013\t]C\"!AA\u0004\te\u0013aC3wS\u0012,gnY3%cQ\u0002RaXA\u007f\u0005#B\u0011B!\u0018\r\u0003\u0003\u0005\u001dAa\u0018\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\u000e\t\u0005/j\u0013\t&\u0001\u0007dC:lU\u000f\\%oi>4f+\u0006\u0005\u0003f\tM$q\u000fB>)\u0011\u00119G! \u0011\u0011\t%\u0014q\u0007B8\u0005_r1a B6\u0013\r\u0011igI\u0001\f\u001fBlU\u000f\\*dC2\f'\u000f\u0005\u0005A\u0003\nE$Q\u000fB=!\r!%1\u000f\u0003\u0006\r6\u0011\ra\u0012\t\u0004\t\n]D!\u0002)\u000e\u0005\u00049\u0005c\u0001#\u0003|\u0011)1+\u0004b\u0001\u000f\"I!qP\u0007\u0002\u0002\u0003\u000f!\u0011Q\u0001\fKZLG-\u001a8dK\u0012\nd\u0007\u0005\u0003`E\ne\u0014\u0001C2b]6+HN\u0016,\u0016\u0011\t\u001d%q\u0012BJ\u0005/#BA!#\u0003\u001aBQ!\u0011NA@\u0005\u0017\u0013YIa#\u0011\u0011\u0001\u000b%Q\u0012BI\u0005+\u00032\u0001\u0012BH\t\u00151eB1\u0001H!\r!%1\u0013\u0003\u0006!:\u0011\ra\u0012\t\u0004\t\n]E!B*\u000f\u0005\u00049\u0005b\u0002BN\u001d\u0001\u000f!QT\u0001\tg\u0016l\u0017N]5oOB!qL\u0019BK\u00031\u0019\u0017M\\'vY&sGo\u001c,T+!\u0011\u0019Ka+\u00030\nMF\u0003\u0002BS\u0005k\u0003\u0002B!\u001b\u00028\t\u001d&\u0011\u0017\t\t\u0001\u0006\u0013IK!,\u00032B\u0019AIa+\u0005\u000b\u0019{!\u0019A$\u0011\u0007\u0011\u0013y\u000bB\u0003Q\u001f\t\u0007q\tE\u0002E\u0005g#QaU\bC\u0002\u001dC\u0011Ba.\u0010\u0003\u0003\u0005\u001dA!/\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0005?\n\u0014\t,\u0001\bdC:lU\u000f\\%oi>46kX'\u0016\u0011\t}&Q\u001aBi\u0005+$BA!1\u0003XBA!1YA\u001c\u0005\u0013\u0014\u0019ND\u0002\u0000\u0005\u000bL1Aa2$\u0003-y\u0005/T;m\u001b\u0006$(/\u001b=\u0011\u0011\u0001\u000b%1\u001aBh\u0005'\u00042\u0001\u0012Bg\t\u00151\u0005C1\u0001H!\r!%\u0011\u001b\u0003\u0006!B\u0011\ra\u0012\t\u0004\t\nUG!B*\u0011\u0005\u00049\u0005\"\u0003Bm!\u0005\u0005\t9\u0001Bn\u0003-)g/\u001b3f]\u000e,G%\r\u001d\u0011\t}\u0013'1[\u0001\tG\u0006tW*\u001e7W'VA!\u0011\u001dBu\u0005[\u0014\t\u0010\u0006\u0003\u0003d\nM\bC\u0003B5\u0003\u007f\u0012)Oa<\u0003fBA\u0001)\u0011Bt\u0005W\u0014y\u000fE\u0002E\u0005S$QAR\tC\u0002\u001d\u00032\u0001\u0012Bw\t\u0015\u0001\u0016C1\u0001H!\r!%\u0011\u001f\u0003\u0006'F\u0011\ra\u0012\u0005\b\u00057\u000b\u00029\u0001B{!\u0011y&Ma<\u0002\u0015\r\fg.T;m-N{V*\u0006\u0005\u0003|\u000e\r1qAB\u0006)\u0011\u0011ip!\u0004\u0011\u0015\t\r\u0017q\u0010B\u0000\u0007\u0013\u0011y\u0010\u0005\u0005A\u0003\u000e\u00051QAB\u0005!\r!51\u0001\u0003\u0006\rJ\u0011\ra\u0012\t\u0004\t\u000e\u001dA!\u0002)\u0013\u0005\u00049\u0005c\u0001#\u0004\f\u0011)1K\u0005b\u0001\u000f\"9!1\u0014\nA\u0004\r=\u0001\u0003B0c\u0007\u0013\tAbY1o\t&4\u0018J\u001c;p-Z+\u0002b!\u0006\u0004$\r\u001d21\u0006\u000b\u0005\u0007/\u0019i\u0003\u0005\u0005\u0004\u001a\u0005]2qDB\u0010\u001d\ry81D\u0005\u0004\u0007;\u0019\u0013!B(q\t&4\b\u0003\u0003!B\u0007C\u0019)c!\u000b\u0011\u0007\u0011\u001b\u0019\u0003B\u0003G'\t\u0007q\tE\u0002E\u0007O!Q\u0001U\nC\u0002\u001d\u00032\u0001RB\u0016\t\u0015\u00196C1\u0001H\u0011%\u0019ycEA\u0001\u0002\b\u0019\t$A\u0006fm&$WM\\2fIEJ\u0004#B0\u00044\r%\u0012bAB\u001bA\n)a)[3mI\u0006A1-\u00198ESZ4f+\u0006\u0005\u0004<\r\r3qIB&)\u0019\u0019id!\u0014\u0004RAQ1\u0011DA@\u0007\u007f\u0019yda\u0010\u0011\u0011\u0001\u000b5\u0011IB#\u0007\u0013\u00022\u0001RB\"\t\u00151EC1\u0001H!\r!5q\t\u0003\u0006!R\u0011\ra\u0012\t\u0004\t\u000e-C!B*\u0015\u0005\u00049\u0005bBA\u000e)\u0001\u000f1q\n\t\u0005uu\u001ay\u0004C\u0004\u0003\u001cR\u0001\u001daa\u0015\u0011\u000b}\u001b\u0019d!\u0013\u0002\u0011\r\fg\u000eR5w-N+\u0002b!\u0017\u0004b\r\u00154\u0011\u000e\u000b\u0007\u00077\u001aYga\u001c\u0011\u0015\re\u0011qPB/\u0007O\u001ai\u0006\u0005\u0005A\u0003\u000e}31MB4!\r!5\u0011\r\u0003\u0006\rV\u0011\ra\u0012\t\u0004\t\u000e\u0015D!\u0002)\u0016\u0005\u00049\u0005c\u0001#\u0004j\u0011)1+\u0006b\u0001\u000f\"9\u00111D\u000bA\u0004\r5\u0004\u0003\u0002\u001e>\u0007;BqAa'\u0016\u0001\b\u0019\t\bE\u0003`\u0007g\u00199'\u0001\u0007dC:$\u0015N^%oi>46+\u0006\u0005\u0004x\r}41QBD)\u0011\u0019Ih!#\u0011\u0011\re\u0011qGB>\u0007\u000b\u0003\u0002\u0002Q!\u0004~\r\u00055Q\u0011\t\u0004\t\u000e}D!\u0002$\u0017\u0005\u00049\u0005c\u0001#\u0004\u0004\u0012)\u0001K\u0006b\u0001\u000fB\u0019Aia\"\u0005\u000bM3\"\u0019A$\t\u0013\r-e#!AA\u0004\r5\u0015aC3wS\u0012,gnY3%eA\u0002RaXB\u001a\u0007\u000b\u000bAbY1o'\u0016$\u0018J\u001c;p-Z+\u0002ba%\u0004\"\u000e\u00156\u0011V\u000b\u0003\u0007+\u0003\u0002ba&\u00028\ru5Q\u0014\b\u0004\u007f\u000ee\u0015bABNG\u0005)q\n]*fiBA\u0001)QBP\u0007G\u001b9\u000bE\u0002E\u0007C#QAR\fC\u0002\u001d\u00032\u0001RBS\t\u0015\u0001vC1\u0001H!\r!5\u0011\u0016\u0003\u0006'^\u0011\raR\u0001\rG\u0006t7+\u001a;J]R|gkU\u000b\t\u0007_\u001b9la/\u0004@V\u00111\u0011\u0017\t\t\u0007/\u000b9da-\u0004>BA\u0001)QB[\u0007s\u001bi\fE\u0002E\u0007o#QA\u0012\rC\u0002\u001d\u00032\u0001RB^\t\u0015\u0001\u0006D1\u0001H!\r!5q\u0018\u0003\u0006'b\u0011\raR\u0001\nG\u0006tg*Z4bi\u0016,\u0002b!2\u0004X\u000em7q\u001c\u000b\u0005\u0007\u000f\u001c\t\u000f\u0005\u0005\u0004J\u000e=71[Bj\u001d\ry81Z\u0005\u0004\u0007\u001b\u001c\u0013!B(q\u001d\u0016<\u0017\u0002BBi\u0003s\u0011A!S7qYBA\u0001)QBk\u00073\u001ci\u000eE\u0002E\u0007/$QAR\rC\u0002\u001d\u00032\u0001RBn\t\u0015\u0001\u0016D1\u0001H!\r!5q\u001c\u0003\u0006'f\u0011\ra\u0012\u0005\b\u0007GL\u00029ABs\u0003\u0011\u0011\u0018N\\4\u0011\u000b}\u000bip!8\u0002\u001f\r\fg.T;mi&\u0004H._\"3\u0007F*\u0002ba;\u0004t\u000e]81 \u000b\u0005\u0007[$)\u0001\u0005\u0006\u0003D\u0006}4q^B\u007f\t\u0007\u0001\u0002\u0002Q!\u0004r\u000eU8\u0011 \t\u0004\t\u000eMH!\u0002$\u001b\u0005\u00049\u0005c\u0001#\u0004x\u0012)\u0001K\u0007b\u0001\u000fB\u0019Aia?\u0005\u000bMS\"\u0019A$\u0011\u000f\u0001\u001byp!>\u0004z&\u0019A\u0011A\u0013\u0003\u000f\r{WO\u001c;feB9\u0001ia@\u0004r\u000ee\bb\u0002BN5\u0001\u000fAq\u0001\t\u0005?\n\u001cI0A\bdC:lU\u000f\u001c;ja2L8IM\"3+)!i\u0001\"\u0006\u0005\u001a\u0011\rBQ\u0004\u000b\u0005\t\u001f!I\u0003\u0005\u0006\u0003D\u0006}D\u0011\u0003C\u0010\tO\u0001\u0002\u0002Q!\u0005\u0014\u0011]A1\u0004\t\u0004\t\u0012UA!\u0002$\u001c\u0005\u00049\u0005c\u0001#\u0005\u001a\u0011)\u0001k\u0007b\u0001\u000fB\u0019A\t\"\b\u0005\u000bM[\"\u0019A$\u0011\u0011\u0001\u000bEq\u0003C\u0011\t7\u00012\u0001\u0012C\u0012\t\u0019!)c\u0007b\u0001\u000f\n\u00111j\r\t\t\u0001\u0006#\u0019\u0002\"\t\u0005\u001c!9!1T\u000eA\u0004\u0011-\u0002\u0003B0c\t7\u0011qcQ1o5&\u0004X*\u00199WC2,Xm]\"pk:$XM\u001d\u001a\u0016\u0015\u0011EBQ\bC!\t\u000b\"Ie\u0005\u0003\u001dW\u0011M\u0002c\u0003\u001e\u00056\u0011eB1\tC$\t\u001bJ1\u0001b\u000e<\u0005=\u0019\u0015M\u001c.ja6\u000b\u0007OV1mk\u0016\u001c\b\u0003\u0003!B\tw!y\u0004b\u0011\u0011\u0007\u0011#i\u0004B\u0003G9\t\u0007q\tE\u0002E\t\u0003\"Q\u0001\u0015\u000fC\u0002\u001d\u00032\u0001\u0012C#\t\u0015\u0019FD1\u0001H!\r!E\u0011\n\u0003\u0007\t\u0017b\"\u0019A$\u0003\u0005I3\u0006\u0003\u0003!B\tw!y\u0004b\u0012\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#'\r\t\u0005/j#9%A\u0006fm&$WM\\2fII\u0012\u0004\u0003B0c\t\u000f\na\u0001P5oSRtDC\u0001C.)\u0019!i\u0006\"\u0019\u0005dAYAq\f\u000f\u0005<\u0011}B1\tC$\u001b\u0005\u0001\u0001b\u0002C(?\u0001\u000fA\u0011\u000b\u0005\b\t'z\u00029\u0001C+\u0003\ri\u0017\r\u001d\u000b\t\t\u001b\"I\u0007\"\u001c\u0005r!9A1\u000e\u0011A\u0002\u0011e\u0012\u0001\u00024s_6Dq\u0001b\u001c!\u0001\u0004!I$A\u0003ge>l'\u0007C\u0004\u0005t\u0001\u0002\r\u0001\"\u001e\u0002\u0005\u0019t\u0007#\u0003\u0017\u0005x\u0011\rC1\tC$\u0013\r!I(\f\u0002\n\rVt7\r^5p]J\naA_5q\u001b\u0006\u0004XC\u0003C@\t\u000b#I\t\"$\u0005\u0012R1A\u0011\u0011CK\t7\u00032\u0002b\u0018\u001d\t\u0007#9\tb#\u0005\u0010B\u0019A\t\"\"\u0005\u000b\u0019\u000b#\u0019A$\u0011\u0007\u0011#I\tB\u0003QC\t\u0007q\tE\u0002E\t\u001b#QaU\u0011C\u0002\u001d\u00032\u0001\u0012CI\t\u0019!\u0019*\tb\u0001\u000f\n\t!\u000bC\u0005\u0005\u0018\u0006\n\t\u0011q\u0001\u0005\u001a\u0006YQM^5eK:\u001cW\r\n\u001a4!\u00119&\fb$\t\u0013\u0011u\u0015%!AA\u0004\u0011}\u0015aC3wS\u0012,gnY3%eQ\u0002Ba\u00182\u0005\u0010\u0002"
)
public interface Counter2Ops {
   // $FF: synthetic method
   static CanCopy canCopy$(final Counter2Ops $this, final Zero evidence$1, final Semiring evidence$2) {
      return $this.canCopy(evidence$1, evidence$2);
   }

   default CanCopy canCopy(final Zero evidence$1, final Semiring evidence$2) {
      return new CanCopy(evidence$2, evidence$1) {
         private final Semiring evidence$2$1;
         private final Zero evidence$1$1;

         public Counter2 apply(final Counter2 t) {
            return Counter2$.MODULE$.apply((IterableOnce)t.iterator().map((x0$1) -> {
               if (x0$1 != null) {
                  Tuple2 var3 = (Tuple2)x0$1._1();
                  Object v = x0$1._2();
                  if (var3 != null) {
                     Object k1 = var3._1();
                     Object k2 = var3._2();
                     Tuple3 var1 = new Tuple3(k1, k2, v);
                     return var1;
                  }
               }

               throw new MatchError(x0$1);
            }), this.evidence$2$1, this.evidence$1$1);
         }

         public {
            this.evidence$2$1 = evidence$2$1;
            this.evidence$1$1 = evidence$1$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private UFunc.UImpl2 binaryOpFromBinaryUpdateOp(final CanCopy copy, final UFunc.InPlaceImpl2 op) {
      return new UFunc.UImpl2(copy, op) {
         private final CanCopy copy$1;
         private final UFunc.InPlaceImpl2 op$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Counter2 apply(final Counter2 a, final Object b) {
            Counter2 c = (Counter2)this.copy$1.apply(a);
            this.op$1.apply(c, b);
            return c;
         }

         public {
            this.copy$1 = copy$1;
            this.op$1 = op$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 addIntoVV$(final Counter2Ops $this, final Semiring evidence$3) {
      return $this.addIntoVV(evidence$3);
   }

   default UFunc.InPlaceImpl2 addIntoVV(final Semiring evidence$3) {
      return new UFunc.InPlaceImpl2(evidence$3) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Counter2 b) {
            b.activeIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$2(check$ifrefutable$1))).foreach((x$1) -> {
               $anonfun$apply$3(this, a, x$1);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$2(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$3(final Object $this, final Counter2 a$1, final Tuple2 x$1) {
            if (x$1 != null) {
               Tuple2 k = (Tuple2)x$1._1();
               Object v = x$1._2();
               a$1.update(k, $this.field().$plus(a$1.apply(k), v));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$1);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$3$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 canAxpy$(final Counter2Ops $this, final Semiring evidence$4) {
      return $this.canAxpy(evidence$4);
   }

   default UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$4) {
      return new UFunc.InPlaceImpl3(evidence$4) {
         private final Semiring field;

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Object s, final Counter2 b) {
            b.activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$4(check$ifrefutable$2))).foreach((x$2) -> {
               $anonfun$apply$5(this, a, s, x$2);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$4(final Tuple2 check$ifrefutable$2) {
            boolean var1;
            if (check$ifrefutable$2 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$5(final Object $this, final Counter2 a$2, final Object s$1, final Tuple2 x$2) {
            if (x$2 != null) {
               Tuple2 k = (Tuple2)x$2._1();
               Object v = x$2._2();
               a$2.update(k, $this.field().$plus(a$2.apply(k), $this.field().$times(s$1, v)));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$2);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$4$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 addVV$(final Counter2Ops $this, final Semiring evidence$5, final Zero evidence$6) {
      return $this.addVV(evidence$5, evidence$6);
   }

   default UFunc.UImpl2 addVV(final Semiring evidence$5, final Zero evidence$6) {
      return this.binaryOpFromBinaryUpdateOp(this.canCopy(evidence$6, evidence$5), this.addIntoVV(evidence$5));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 addIntoVS$(final Counter2Ops $this, final Semiring evidence$7) {
      return $this.addIntoVS(evidence$7);
   }

   default UFunc.InPlaceImpl2 addIntoVS(final Semiring evidence$7) {
      return new UFunc.InPlaceImpl2(evidence$7) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Object b) {
            a.activeIterator().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$apply$6(check$ifrefutable$3))).foreach((x$3) -> {
               $anonfun$apply$7(this, a, b, x$3);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$6(final Tuple2 check$ifrefutable$3) {
            boolean var1;
            if (check$ifrefutable$3 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$7(final Object $this, final Counter2 a$3, final Object b$1, final Tuple2 x$3) {
            if (x$3 != null) {
               Tuple2 k = (Tuple2)x$3._1();
               Object v = x$3._2();
               a$3.update(k, $this.field().$plus(v, b$1));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$3);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$7$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 addVS$(final Counter2Ops $this, final Semiring evidence$8, final Zero evidence$9) {
      return $this.addVS(evidence$8, evidence$9);
   }

   default UFunc.UImpl2 addVS(final Semiring evidence$8, final Zero evidence$9) {
      return this.binaryOpFromBinaryUpdateOp(this.canCopy(evidence$9, evidence$8), this.addIntoVS(evidence$8));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 subIntoVV$(final Counter2Ops $this, final Ring evidence$10) {
      return $this.subIntoVV(evidence$10);
   }

   default UFunc.InPlaceImpl2 subIntoVV(final Ring evidence$10) {
      return new UFunc.InPlaceImpl2(evidence$10) {
         private final Ring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Ring field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Counter2 b) {
            b.activeIterator().withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$apply$8(check$ifrefutable$4))).foreach((x$4) -> {
               $anonfun$apply$9(this, a, x$4);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$8(final Tuple2 check$ifrefutable$4) {
            boolean var1;
            if (check$ifrefutable$4 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$9(final Object $this, final Counter2 a$4, final Tuple2 x$4) {
            if (x$4 != null) {
               Tuple2 k = (Tuple2)x$4._1();
               Object v = x$4._2();
               a$4.update(k, $this.field().$minus(a$4.apply(k), v));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$4);
            }
         }

         public {
            this.field = (Ring).MODULE$.implicitly(evidence$10$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 subVV$(final Counter2Ops $this, final Ring evidence$11, final Zero evidence$12) {
      return $this.subVV(evidence$11, evidence$12);
   }

   default UFunc.UImpl2 subVV(final Ring evidence$11, final Zero evidence$12) {
      return this.binaryOpFromBinaryUpdateOp(this.canCopy(evidence$12, evidence$11), this.subIntoVV(evidence$11));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 subIntoVS$(final Counter2Ops $this, final Ring evidence$13) {
      return $this.subIntoVS(evidence$13);
   }

   default UFunc.InPlaceImpl2 subIntoVS(final Ring evidence$13) {
      return new UFunc.InPlaceImpl2(evidence$13) {
         private final Ring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Ring field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Object b) {
            a.activeIterator().withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$10(check$ifrefutable$5))).foreach((x$5) -> {
               $anonfun$apply$11(this, a, b, x$5);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$10(final Tuple2 check$ifrefutable$5) {
            boolean var1;
            if (check$ifrefutable$5 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$11(final Object $this, final Counter2 a$5, final Object b$2, final Tuple2 x$5) {
            if (x$5 != null) {
               Tuple2 k = (Tuple2)x$5._1();
               Object v = x$5._2();
               a$5.update(k, $this.field().$minus(v, b$2));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$5);
            }
         }

         public {
            this.field = (Ring).MODULE$.implicitly(evidence$13$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 subVS$(final Counter2Ops $this, final Ring evidence$14, final Zero evidence$15) {
      return $this.subVS(evidence$14, evidence$15);
   }

   default UFunc.UImpl2 subVS(final Ring evidence$14, final Zero evidence$15) {
      return this.binaryOpFromBinaryUpdateOp(this.canCopy(evidence$15, evidence$14), this.subIntoVS(evidence$14));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canMulIntoVV$(final Counter2Ops $this, final Semiring evidence$16) {
      return $this.canMulIntoVV(evidence$16);
   }

   default UFunc.InPlaceImpl2 canMulIntoVV(final Semiring evidence$16) {
      return new UFunc.InPlaceImpl2(evidence$16) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Counter2 b) {
            a.activeIterator().withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$apply$12(check$ifrefutable$6))).foreach((x$6) -> {
               $anonfun$apply$13(this, a, b, x$6);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$12(final Tuple2 check$ifrefutable$6) {
            boolean var1;
            if (check$ifrefutable$6 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$13(final Object $this, final Counter2 a$6, final Counter2 b$3, final Tuple2 x$6) {
            if (x$6 != null) {
               Tuple2 k = (Tuple2)x$6._1();
               Object v = x$6._2();
               a$6.update(k, $this.field().$times(v, b$3.apply(k)));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$6);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$16$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulVV$(final Counter2Ops $this, final Semiring semiring) {
      return $this.canMulVV(semiring);
   }

   default UFunc.UImpl2 canMulVV(final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Semiring semiring$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Counter2 apply(final Counter2 a, final Counter2 b) {
            Counter2 r = Counter2$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$1));
            a.activeIterator().withFilter((check$ifrefutable$7) -> BoxesRunTime.boxToBoolean($anonfun$apply$14(check$ifrefutable$7))).foreach((x$7) -> {
               $anonfun$apply$15(this, b, r, x$7);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$14(final Tuple2 check$ifrefutable$7) {
            boolean var1;
            if (check$ifrefutable$7 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$15(final Object $this, final Counter2 b$4, final Counter2 r$1, final Tuple2 x$7) {
            if (x$7 != null) {
               Tuple2 k = (Tuple2)x$7._1();
               Object v = x$7._2();
               Object vr = $this.semiring$1.$times(v, b$4.apply(k));
               if (!BoxesRunTime.equals(vr, $this.semiring$1.zero())) {
                  r$1.update(k, vr);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var9 = BoxedUnit.UNIT;
               }

            } else {
               throw new MatchError(x$7);
            }
         }

         public {
            this.semiring$1 = semiring$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canMulIntoVS$(final Counter2Ops $this, final Semiring evidence$17) {
      return $this.canMulIntoVS(evidence$17);
   }

   default UFunc.InPlaceImpl2 canMulIntoVS(final Semiring evidence$17) {
      return new UFunc.InPlaceImpl2(evidence$17) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Object b) {
            a.activeIterator().withFilter((check$ifrefutable$8) -> BoxesRunTime.boxToBoolean($anonfun$apply$16(check$ifrefutable$8))).foreach((x$8) -> {
               $anonfun$apply$17(this, a, b, x$8);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$16(final Tuple2 check$ifrefutable$8) {
            boolean var1;
            if (check$ifrefutable$8 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$17(final Object $this, final Counter2 a$7, final Object b$5, final Tuple2 x$8) {
            if (x$8 != null) {
               Tuple2 k = (Tuple2)x$8._1();
               Object v = x$8._2();
               a$7.update(k, $this.field().$times(v, b$5));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$8);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$17$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canMulIntoVS_M$(final Counter2Ops $this, final Semiring evidence$18) {
      return $this.canMulIntoVS_M(evidence$18);
   }

   default UFunc.InPlaceImpl2 canMulIntoVS_M(final Semiring evidence$18) {
      return new UFunc.InPlaceImpl2(evidence$18) {
         private final Semiring field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Object b) {
            a.activeIterator().withFilter((check$ifrefutable$9) -> BoxesRunTime.boxToBoolean($anonfun$apply$18(check$ifrefutable$9))).foreach((x$9) -> {
               $anonfun$apply$19(this, a, b, x$9);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$18(final Tuple2 check$ifrefutable$9) {
            boolean var1;
            if (check$ifrefutable$9 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$19(final Object $this, final Counter2 a$8, final Object b$6, final Tuple2 x$9) {
            if (x$9 != null) {
               Tuple2 k = (Tuple2)x$9._1();
               Object v = x$9._2();
               a$8.update(k, $this.field().$times(v, b$6));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$9);
            }
         }

         public {
            this.field = (Semiring).MODULE$.implicitly(evidence$18$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulVS$(final Counter2Ops $this, final Semiring semiring) {
      return $this.canMulVS(semiring);
   }

   default UFunc.UImpl2 canMulVS(final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Semiring semiring$2;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Counter2 apply(final Counter2 a, final Object b) {
            Counter2 r = Counter2$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$2));
            a.activeIterator().withFilter((check$ifrefutable$10) -> BoxesRunTime.boxToBoolean($anonfun$apply$20(check$ifrefutable$10))).foreach((x$10) -> {
               $anonfun$apply$21(this, b, r, x$10);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$20(final Tuple2 check$ifrefutable$10) {
            boolean var1;
            if (check$ifrefutable$10 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$21(final Object $this, final Object b$7, final Counter2 r$2, final Tuple2 x$10) {
            if (x$10 != null) {
               Tuple2 k = (Tuple2)x$10._1();
               Object v = x$10._2();
               Object vr = $this.semiring$2.$times(v, b$7);
               r$2.update(k, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$10);
            }
         }

         public {
            this.semiring$2 = semiring$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMulVS_M$(final Counter2Ops $this, final Semiring semiring) {
      return $this.canMulVS_M(semiring);
   }

   default UFunc.UImpl2 canMulVS_M(final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Semiring semiring$3;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Counter2 apply(final Counter2 a, final Object b) {
            Counter2 r = Counter2$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$3));
            a.activeIterator().withFilter((check$ifrefutable$11) -> BoxesRunTime.boxToBoolean($anonfun$apply$22(check$ifrefutable$11))).foreach((x$11) -> {
               $anonfun$apply$23(this, b, r, x$11);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$22(final Tuple2 check$ifrefutable$11) {
            boolean var1;
            if (check$ifrefutable$11 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$23(final Object $this, final Object b$8, final Counter2 r$3, final Tuple2 x$11) {
            if (x$11 != null) {
               Tuple2 k = (Tuple2)x$11._1();
               Object v = x$11._2();
               Object vr = $this.semiring$3.$times(v, b$8);
               r$3.update(k, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$11);
            }
         }

         public {
            this.semiring$3 = semiring$3;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canDivIntoVV$(final Counter2Ops $this, final Field evidence$19) {
      return $this.canDivIntoVV(evidence$19);
   }

   default UFunc.InPlaceImpl2 canDivIntoVV(final Field evidence$19) {
      return new UFunc.InPlaceImpl2(evidence$19) {
         private final Field field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Field field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Counter2 b) {
            a.activeIterator().withFilter((check$ifrefutable$12) -> BoxesRunTime.boxToBoolean($anonfun$apply$24(check$ifrefutable$12))).foreach((x$12) -> {
               $anonfun$apply$25(this, a, b, x$12);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$24(final Tuple2 check$ifrefutable$12) {
            boolean var1;
            if (check$ifrefutable$12 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$25(final Object $this, final Counter2 a$9, final Counter2 b$9, final Tuple2 x$12) {
            if (x$12 != null) {
               Tuple2 k = (Tuple2)x$12._1();
               Object v = x$12._2();
               a$9.update(k, $this.field().$div(v, b$9.apply(k)));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$12);
            }
         }

         public {
            this.field = (Field).MODULE$.implicitly(evidence$19$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canDivVV$(final Counter2Ops $this, final CanCopy copy, final Field semiring) {
      return $this.canDivVV(copy, semiring);
   }

   default UFunc.UImpl2 canDivVV(final CanCopy copy, final Field semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Field semiring$4;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Counter2 apply(final Counter2 a, final Counter2 b) {
            Counter2 r = Counter2$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$4));
            a.activeIterator().withFilter((check$ifrefutable$13) -> BoxesRunTime.boxToBoolean($anonfun$apply$26(check$ifrefutable$13))).foreach((x$13) -> {
               $anonfun$apply$27(this, b, r, x$13);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$26(final Tuple2 check$ifrefutable$13) {
            boolean var1;
            if (check$ifrefutable$13 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$27(final Object $this, final Counter2 b$10, final Counter2 r$4, final Tuple2 x$13) {
            if (x$13 != null) {
               Tuple2 k = (Tuple2)x$13._1();
               Object v = x$13._2();
               Object vr = $this.semiring$4.$div(v, b$10.apply(k));
               r$4.update(k, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$13);
            }
         }

         public {
            this.semiring$4 = semiring$4;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canDivVS$(final Counter2Ops $this, final CanCopy copy, final Field semiring) {
      return $this.canDivVS(copy, semiring);
   }

   default UFunc.UImpl2 canDivVS(final CanCopy copy, final Field semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Field semiring$5;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Counter2 apply(final Counter2 a, final Object b) {
            Counter2 r = Counter2$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$5));
            a.activeIterator().withFilter((check$ifrefutable$14) -> BoxesRunTime.boxToBoolean($anonfun$apply$28(check$ifrefutable$14))).foreach((x$14) -> {
               $anonfun$apply$29(this, b, r, x$14);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$28(final Tuple2 check$ifrefutable$14) {
            boolean var1;
            if (check$ifrefutable$14 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$29(final Object $this, final Object b$11, final Counter2 r$5, final Tuple2 x$14) {
            if (x$14 != null) {
               Tuple2 k = (Tuple2)x$14._1();
               Object v = x$14._2();
               Object vr = $this.semiring$5.$div(v, b$11);
               r$5.update(k, vr);
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$14);
            }
         }

         public {
            this.semiring$5 = semiring$5;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canDivIntoVS$(final Counter2Ops $this, final Field evidence$20) {
      return $this.canDivIntoVS(evidence$20);
   }

   default UFunc.InPlaceImpl2 canDivIntoVS(final Field evidence$20) {
      return new UFunc.InPlaceImpl2(evidence$20) {
         private final Field field;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Field field() {
            return this.field;
         }

         public void apply(final Counter2 a, final Object b) {
            a.activeIterator().withFilter((check$ifrefutable$15) -> BoxesRunTime.boxToBoolean($anonfun$apply$30(check$ifrefutable$15))).foreach((x$15) -> {
               $anonfun$apply$31(this, a, b, x$15);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$30(final Tuple2 check$ifrefutable$15) {
            boolean var1;
            if (check$ifrefutable$15 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$31(final Object $this, final Counter2 a$10, final Object b$12, final Tuple2 x$15) {
            if (x$15 != null) {
               Tuple2 k = (Tuple2)x$15._1();
               Object v = x$15._2();
               a$10.update(k, $this.field().$div(v, b$12));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$15);
            }
         }

         public {
            this.field = (Field).MODULE$.implicitly(evidence$20$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canSetIntoVV$(final Counter2Ops $this) {
      return $this.canSetIntoVV();
   }

   default UFunc.InPlaceImpl2 canSetIntoVV() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Counter2 a, final Counter2 b) {
            a.data().clear();
            b.activeIterator().withFilter((check$ifrefutable$16) -> BoxesRunTime.boxToBoolean($anonfun$apply$32(check$ifrefutable$16))).foreach((x$16) -> {
               $anonfun$apply$33(a, x$16);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$32(final Tuple2 check$ifrefutable$16) {
            boolean var1;
            if (check$ifrefutable$16 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$33(final Counter2 a$11, final Tuple2 x$16) {
            if (x$16 != null) {
               Tuple2 k = (Tuple2)x$16._1();
               Object v = x$16._2();
               a$11.update(k, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$16);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canSetIntoVS$(final Counter2Ops $this) {
      return $this.canSetIntoVS();
   }

   default UFunc.InPlaceImpl2 canSetIntoVS() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Counter2 a, final Object b) {
            a.keysIterator().foreach((k) -> {
               $anonfun$apply$34(a, b, k);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$34(final Counter2 a$12, final Object b$13, final Tuple2 k) {
            a$12.update(k, b$13);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl canNegate$(final Counter2Ops $this, final Ring ring) {
      return $this.canNegate(ring);
   }

   default UFunc.UImpl canNegate(final Ring ring) {
      return new UFunc.UImpl(ring) {
         private final Ring ring$1;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Counter2 apply(final Counter2 a) {
            Counter2 result = Counter2$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.ring$1));
            a.activeIterator().withFilter((check$ifrefutable$17) -> BoxesRunTime.boxToBoolean($anonfun$apply$35(check$ifrefutable$17))).foreach((x$17) -> {
               $anonfun$apply$36(this, result, x$17);
               return BoxedUnit.UNIT;
            });
            return result;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$35(final Tuple2 check$ifrefutable$17) {
            boolean var1;
            if (check$ifrefutable$17 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$36(final Object $this, final Counter2 result$1, final Tuple2 x$17) {
            if (x$17 != null) {
               Tuple2 k = (Tuple2)x$17._1();
               Object v = x$17._2();
               Object vr = $this.ring$1.negate(v);
               result$1.update(k, vr);
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$17);
            }
         }

         public {
            this.ring$1 = ring$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMultiplyC2C1$(final Counter2Ops $this, final Semiring semiring) {
      return $this.canMultiplyC2C1(semiring);
   }

   default UFunc.UImpl2 canMultiplyC2C1(final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Semiring semiring$6;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Counter apply(final Counter2 a, final Counter b) {
            Counter r = Counter$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$6));
            a.data().iterator().withFilter((check$ifrefutable$18) -> BoxesRunTime.boxToBoolean($anonfun$apply$37(check$ifrefutable$18))).foreach((x$18) -> {
               $anonfun$apply$38(this, r, b, x$18);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$37(final Tuple2 check$ifrefutable$18) {
            boolean var1;
            if (check$ifrefutable$18 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$38(final Object $this, final Counter r$6, final Counter b$14, final Tuple2 x$18) {
            if (x$18 != null) {
               Object row = x$18._1();
               Counter ctr = (Counter)x$18._2();
               r$6.update(row, ctr.dot(b$14, Counter$.MODULE$.canMulInner(Counter$.MODULE$.canCopy(Zero$.MODULE$.zeroFromSemiring($this.semiring$6), $this.semiring$6), $this.semiring$6)));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$18);
            }
         }

         public {
            this.semiring$6 = semiring$6;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canMultiplyC2C2$(final Counter2Ops $this, final Semiring semiring) {
      return $this.canMultiplyC2C2(semiring);
   }

   default UFunc.UImpl2 canMultiplyC2C2(final Semiring semiring) {
      return new UFunc.UImpl2(semiring) {
         private final Semiring semiring$7;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Counter2 apply(final Counter2 a, final Counter2 b) {
            Counter2 r = Counter2$.MODULE$.apply(Zero$.MODULE$.zeroFromSemiring(this.semiring$7));
            a.data().iterator().withFilter((check$ifrefutable$19) -> BoxesRunTime.boxToBoolean($anonfun$apply$39(check$ifrefutable$19))).foreach((x$21) -> {
               $anonfun$apply$40(this, b, r, x$21);
               return BoxedUnit.UNIT;
            });
            return r;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$39(final Tuple2 check$ifrefutable$19) {
            boolean var1;
            if (check$ifrefutable$19 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$41(final Tuple2 check$ifrefutable$20) {
            boolean var1;
            if (check$ifrefutable$20 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$43(final Tuple2 check$ifrefutable$21) {
            boolean var1;
            if (check$ifrefutable$21 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$44(final Object $this, final Counter2 r$7, final Object row$1, final Object v$1, final Tuple2 x$19) {
            if (x$19 != null) {
               Object k3 = x$19._1();
               Object v2 = x$19._2();
               r$7.update(row$1, k3, $this.semiring$7.$plus(r$7.apply(row$1, k3), $this.semiring$7.$times(v$1, v2)));
               BoxedUnit var5 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$19);
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$42(final Object $this, final Counter2 b$15, final Counter2 r$7, final Object row$1, final Tuple2 x$20) {
            if (x$20 != null) {
               Object k2 = x$20._1();
               Object v = x$20._2();
               ((CounterLike)b$15.apply(k2, scala.package..MODULE$.$colon$colon(), Counter2$.MODULE$.canSliceRow())).data().withFilter((check$ifrefutable$21) -> BoxesRunTime.boxToBoolean($anonfun$apply$43(check$ifrefutable$21))).foreach((x$19) -> {
                  $anonfun$apply$44($this, r$7, row$1, v, x$19);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var5 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$20);
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$40(final Object $this, final Counter2 b$15, final Counter2 r$7, final Tuple2 x$21) {
            if (x$21 != null) {
               Object row = x$21._1();
               Counter ctr = (Counter)x$21._2();
               ctr.activeIterator().withFilter((check$ifrefutable$20) -> BoxesRunTime.boxToBoolean($anonfun$apply$41(check$ifrefutable$20))).foreach((x$20) -> {
                  $anonfun$apply$42($this, b$15, r$7, row, x$20);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$21);
            }
         }

         public {
            this.semiring$7 = semiring$7;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanZipMapValuesCounter2 zipMap$(final Counter2Ops $this, final Zero evidence$23, final Semiring evidence$24) {
      return $this.zipMap(evidence$23, evidence$24);
   }

   default CanZipMapValuesCounter2 zipMap(final Zero evidence$23, final Semiring evidence$24) {
      return new CanZipMapValuesCounter2(evidence$23, evidence$24);
   }

   static void $init$(final Counter2Ops $this) {
   }

   public class CanZipMapValuesCounter2 implements CanZipMapValues {
      private final Zero evidence$21;
      // $FF: synthetic field
      public final Counter2Ops $outer;

      public Object map$mcDD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDD$sp$(this, from, from2, fn);
      }

      public Object map$mcFD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFD$sp$(this, from, from2, fn);
      }

      public Object map$mcID$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcID$sp$(this, from, from2, fn);
      }

      public Object map$mcJD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJD$sp$(this, from, from2, fn);
      }

      public Object map$mcDF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDF$sp$(this, from, from2, fn);
      }

      public Object map$mcFF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFF$sp$(this, from, from2, fn);
      }

      public Object map$mcIF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIF$sp$(this, from, from2, fn);
      }

      public Object map$mcJF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJF$sp$(this, from, from2, fn);
      }

      public Object map$mcDI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDI$sp$(this, from, from2, fn);
      }

      public Object map$mcFI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFI$sp$(this, from, from2, fn);
      }

      public Object map$mcII$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcII$sp$(this, from, from2, fn);
      }

      public Object map$mcJI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJI$sp$(this, from, from2, fn);
      }

      public Object map$mcDJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDJ$sp$(this, from, from2, fn);
      }

      public Object map$mcFJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFJ$sp$(this, from, from2, fn);
      }

      public Object map$mcIJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIJ$sp$(this, from, from2, fn);
      }

      public Object map$mcJJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJJ$sp$(this, from, from2, fn);
      }

      public Counter2 map(final Counter2 from, final Counter2 from2, final Function2 fn) {
         Counter2 result = Counter2$.MODULE$.apply(this.evidence$21);
         from.keySet().$plus$plus(from2.keySet()).foreach((k) -> {
            $anonfun$map$1(result, fn, from, from2, k);
            return BoxedUnit.UNIT;
         });
         return result;
      }

      // $FF: synthetic method
      public Counter2Ops breeze$linalg$operators$Counter2Ops$CanZipMapValuesCounter2$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$map$1(final Counter2 result$2, final Function2 fn$1, final Counter2 from$1, final Counter2 from2$1, final Tuple2 k) {
         result$2.update(k, fn$1.apply(from$1.apply(k), from2$1.apply(k)));
      }

      public CanZipMapValuesCounter2(final Zero evidence$21, final Semiring evidence$22) {
         this.evidence$21 = evidence$21;
         if (Counter2Ops.this == null) {
            throw null;
         } else {
            this.$outer = Counter2Ops.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
