package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.generic.UFunc$InPlaceImpl2$mcD$sp;
import breeze.generic.UFunc$InPlaceImpl2$mcF$sp;
import breeze.generic.UFunc$InPlaceImpl2$mcI$sp;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.HashVector;
import breeze.linalg.HashVector$;
import breeze.linalg.QuasiTensor;
import breeze.linalg.StorageVector;
import breeze.linalg.TensorLike;
import breeze.linalg.Vector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.norm$;
import breeze.linalg.package$;
import breeze.math.Field;
import breeze.math.MutableModule;
import breeze.math.MutableModule$;
import breeze.math.MutableVectorField;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Storage;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\r]f!C\u00181!\u0003\r\taNBY\u0011\u0015q\u0004\u0001\"\u0001@\u0011\u001d\u0019\u0005A1A\u0005\u0004\u0011Cqa\u0016\u0001C\u0002\u0013\r\u0001\fC\u0004^\u0001\t\u0007I1\u00010\t\u000f\u0011\u0004!\u0019!C\u0002K\"9q\r\u0001b\u0001\n\u0007A\u0007b\u00028\u0001\u0005\u0004%\u0019a\u001c\u0005\bc\u0002\u0011\r\u0011b\u0001s\u0011\u001dA\bA1A\u0005\u0004eDQa\u001f\u0001\u0005\u0004qDq!a\u000e\u0001\t\u0007\tI\u0004C\u0005\u0002V\u0001\u0011\r\u0011b\u0001\u0002X!I\u0011\u0011\r\u0001C\u0002\u0013\r\u00111\r\u0005\n\u0003[\u0002!\u0019!C\u0002\u0003_B\u0011\"a\u001d\u0001\u0005\u0004%\u0019!!\u001e\t\u0013\u0005e\u0004A1A\u0005\u0004\u0005m\u0004\"CA@\u0001\t\u0007I1AAA\u0011%\t)\t\u0001b\u0001\n\u0007\t9\tC\u0005\u0002\f\u0002\u0011\r\u0011b\u0001\u0002\u000e\"9\u0011\u0011\u0013\u0001\u0005\u0004\u0005M\u0005bBAX\u0001\u0011\r\u0011\u0011\u0017\u0005\b\u0003\u0013\u0004A1AAf\u0011\u001d\t\u0019\u000f\u0001C\u0002\u0003KD\u0011\"!@\u0001\u0005\u0004%\u0019!a@\t\u0013\t%\u0001A1A\u0005\u0004\t-\u0001\"\u0003B\b\u0001\t\u0007I1\u0001B\t\u0011%\u0011)\u0002\u0001b\u0001\n\u0007\u00119\u0002C\u0004\u0003\u001c\u0001!\u0019A!\b\t\u0013\t%\u0002A1A\u0005\u0004\t-\u0002\"\u0003B\u001d\u0001\t\u0007I1\u0001B\u001e\u0011%\u0011y\u0004\u0001b\u0001\n\u0007\u0011\t\u0005C\u0005\u0003F\u0001\u0011\r\u0011b\u0001\u0003H!9!1\n\u0001\u0005\u0004\t5\u0003b\u0002B3\u0001\u0011\r!q\r\u0005\b\u0005\u0007\u0003A1\u0001BC\u0011\u001d\u0011y\u000b\u0001C\u0002\u0005cCqAa3\u0001\t\u0007\u0011i\rC\u0004\u0003d\u0002!\u0019A!:\t\u000f\r\u0005\u0001\u0001b\u0001\u0004\u0004!91q\u0005\u0001\u0005\u0004\r%\u0002bBB\"\u0001\u0011\r1Q\t\u0005\n\u0007?\u0002!\u0019!C\u0002\u0007CB\u0011ba\u001e\u0001\u0005\u0004%\u0019a!\u001f\t\u0013\r\u0005\u0005A1A\u0005\u0004\r\r\u0005\"CBF\u0001\t\u0007I1ABG\u0011\u001d\u0019)\n\u0001C\u0002\u0007/\u0013\u0001CV3di>\u0014()^5mI\u0016\u0014x\n]:\u000b\u0005E\u0012\u0014!C8qKJ\fGo\u001c:t\u0015\t\u0019D'\u0001\u0004mS:\fGn\u001a\u0006\u0002k\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0002\u0001qA\u0011\u0011\bP\u0007\u0002u)\t1(A\u0003tG\u0006d\u0017-\u0003\u0002>u\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001!\u0011\u0005e\n\u0015B\u0001\";\u0005\u0011)f.\u001b;\u0002A\r\fgn\u00149J]R|wLV0T?>\u0003X*\u001e7TG\u0006d\u0017M]0E_V\u0014G.Z\u000b\u0002\u000bB!aI\u0013)U\u001d\t9\u0005*D\u00011\u0013\tI\u0005'A\u0006Pa6+HnU2bY\u0006\u0014\u0018BA&M\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d73\u0013\tieJA\u0003V\rVt7M\u0003\u0002Pi\u00059q-\u001a8fe&\u001c\u0007cA)S)6\t!'\u0003\u0002Te\tia+Z2u_J\u0014U/\u001b7eKJ\u0004\"!O+\n\u0005YS$A\u0002#pk\ndW-\u0001\u000edC:|\u0005/\u00138u_~3vlU0Pa\u0012Kgo\u0018#pk\ndW-F\u0001Z!\u0011Q&\n\u0015+\u000f\u0005\u001d[\u0016B\u0001/1\u0003\u0015y\u0005\u000fR5w\u0003y\u0019\u0017M\\(q\u0013:$xn\u0018,`'~{\u0005/T;m'\u000e\fG.\u0019:`\u0019>tw-F\u0001`!\u00111%\nY1\u0011\u0007E\u0013\u0016\r\u0005\u0002:E&\u00111M\u000f\u0002\u0005\u0019>tw-\u0001\rdC:|\u0005/\u00138u_~3vlU0Pa\u0012Kgo\u0018'p]\u001e,\u0012A\u001a\t\u00055*\u0003\u0017-A\u0010dC:|\u0005/\u00138u_~3vlU0Pa6+HnU2bY\u0006\u0014xL\u00127pCR,\u0012!\u001b\t\u0005\r*S7\u000eE\u0002R%.\u0004\"!\u000f7\n\u00055T$!\u0002$m_\u0006$\u0018!G2b]>\u0003\u0018J\u001c;p?Z{6kX(q\t&4xL\u00127pCR,\u0012\u0001\u001d\t\u00055*S7.A\u000fdC:|\u0005/\u00138u_~3vlU0Pa6+HnU2bY\u0006\u0014x,\u00138u+\u0005\u0019\b\u0003\u0002$KiV\u00042!\u0015*v!\tId/\u0003\u0002xu\t\u0019\u0011J\u001c;\u0002/\r\fgn\u00149J]R|wLV0T?>\u0003H)\u001b<`\u0013:$X#\u0001>\u0011\tiSE/^\u0001\u000fG\u0006tW*\u001e7J]R|wLV0T+\ri\u0018Q\u0001\u000b\u0006}\u0006]\u0011q\u0005\t\u0006\r*{\u0018\u0011\u0001\t\u0005#J\u000b\t\u0001\u0005\u0003\u0002\u0004\u0005\u0015A\u0002\u0001\u0003\b\u0003\u000fQ!\u0019AA\u0005\u0005\u0005!\u0016\u0003BA\u0006\u0003#\u00012!OA\u0007\u0013\r\tyA\u000f\u0002\b\u001d>$\b.\u001b8h!\rI\u00141C\u0005\u0004\u0003+Q$aA!os\"I\u0011\u0011\u0004\u0006\u0002\u0002\u0003\u000f\u00111D\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBA\u000f\u0003G\t\t!\u0004\u0002\u0002 )\u0019\u0011\u0011\u0005\u001b\u0002\t5\fG\u000f[\u0005\u0005\u0003K\tyB\u0001\u0005TK6L'/\u001b8h\u0011%\tICCA\u0001\u0002\b\tY#\u0001\u0006fm&$WM\\2fII\u0002b!!\f\u00024\u0005\u0005QBAA\u0018\u0015\r\t\tDO\u0001\be\u00164G.Z2u\u0013\u0011\t)$a\f\u0003\u0011\rc\u0017m]:UC\u001e\fabY1o\t&4\u0018J\u001c;p?Z{6+\u0006\u0003\u0002<\u0005\rCCBA\u001f\u0003\u000b\ny\u0005\u0005\u0004[\u0015\u0006}\u0012\u0011\t\t\u0005#J\u000b\t\u0005\u0005\u0003\u0002\u0004\u0005\rCaBA\u0004\u0017\t\u0007\u0011\u0011\u0002\u0005\n\u0003\u000fZ\u0011\u0011!a\u0002\u0003\u0013\n!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\ti\"a\u0013\u0002B%!\u0011QJA\u0010\u0005\u00151\u0015.\u001a7e\u0011%\t\tfCA\u0001\u0002\b\t\u0019&\u0001\u0006fm&$WM\\2fIQ\u0002b!!\f\u00024\u0005\u0005\u0013AG2b]>\u0003\u0018J\u001c;p?Z{fkX(q\u0003\u0012$w\fR8vE2,WCAA-!\u0015\tYF\u0013)Q\u001d\r9\u0015QL\u0005\u0004\u0003?\u0002\u0014!B(q\u0003\u0012$\u0017AG2b]>\u0003\u0018J\u001c;p?Z{fkX(q'V\u0014w\fR8vE2,WCAA3!\u0015\t9G\u0013)Q\u001d\r9\u0015\u0011N\u0005\u0004\u0003W\u0002\u0014!B(q'V\u0014\u0017\u0001G2b]>\u0003\u0018J\u001c;p?Z{fkX(q\u0003\u0012$w\fT8oOV\u0011\u0011\u0011\u000f\t\u0006\u00037R\u0005\rY\u0001\u0019G\u0006tw\n]%oi>|fk\u0018,`\u001fB\u001cVOY0M_:<WCAA<!\u0015\t9G\u00131a\u0003e\u0019\u0017M\\(q\u0013:$xn\u0018,`-~{\u0005/\u00113e?\u001acw.\u0019;\u0016\u0005\u0005u\u0004#BA.\u0015*T\u0017!G2b]>\u0003\u0018J\u001c;p?Z{fkX(q'V\u0014wL\u00127pCR,\"!a!\u0011\u000b\u0005\u001d$J\u001b6\u0002/\r\fgn\u00149J]R|wLV0W?>\u0003\u0018\t\u001a3`\u0013:$XCAAE!\u0015\tYF\u0013;u\u0003]\u0019\u0017M\\(q\u0013:$xn\u0018,`-~{\u0005oU;c?&sG/\u0006\u0002\u0002\u0010B)\u0011q\r&ui\u0006\u00192-\u00198Pa&sGo\\0W?Z{v\n]!eIV!\u0011QSAO)\u0019\t9*a(\u0002*B9\u00111\f&\u0002\u001a\u0006e\u0005\u0003B)S\u00037\u0003B!a\u0001\u0002\u001e\u00129\u0011q\u0001\u000bC\u0002\u0005%\u0001\"CAQ)\u0005\u0005\t9AAR\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0007\u0003;\t)+a'\n\t\u0005\u001d\u0016q\u0004\u0002\u0005%&tw\rC\u0005\u0002,R\t\t\u0011q\u0001\u0002.\u0006QQM^5eK:\u001cW\r\n\u001c\u0011\r\u00055\u00121GAN\u0003M\u0019\u0017M\\(q\u0013:$xn\u0018,`-~{\u0005oU;c+\u0011\t\u0019,a/\u0015\r\u0005U\u0016QXAb!\u001d\t9GSA\\\u0003o\u0003B!\u0015*\u0002:B!\u00111AA^\t\u001d\t9!\u0006b\u0001\u0003\u0013A\u0011\"a0\u0016\u0003\u0003\u0005\u001d!!1\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$s\u0007\u0005\u0004\u0002\u001e\u0005\u0015\u0016\u0011\u0018\u0005\n\u0003\u000b,\u0012\u0011!a\u0002\u0003\u000f\f!\"\u001a<jI\u0016t7-\u001a\u00139!\u0019\ti#a\r\u0002:\u0006\u00192-\u00198Pa&sGo\\0W?N{v\n]!eIV!\u0011QZAk)\u0019\ty-a6\u0002^B9\u00111\f&\u0002R\u0006M\u0007\u0003B)S\u0003'\u0004B!a\u0001\u0002V\u00129\u0011q\u0001\fC\u0002\u0005%\u0001\"CAm-\u0005\u0005\t9AAn\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0007\u0003;\t)+a5\t\u0013\u0005}g#!AA\u0004\u0005\u0005\u0018aC3wS\u0012,gnY3%cA\u0002b!!\f\u00024\u0005M\u0017aE2b]>\u0003\u0018J\u001c;p?Z{6kX(q'V\u0014W\u0003BAt\u0003_$b!!;\u0002r\u0006]\bcBA4\u0015\u0006-\u0018Q\u001e\t\u0005#J\u000bi\u000f\u0005\u0003\u0002\u0004\u0005=HaBA\u0004/\t\u0007\u0011\u0011\u0002\u0005\n\u0003g<\u0012\u0011!a\u0002\u0003k\f1\"\u001a<jI\u0016t7-\u001a\u00132cA1\u0011QDAS\u0003[D\u0011\"!?\u0018\u0003\u0003\u0005\u001d!a?\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u0007\u0003[\t\u0019$!<\u0002\u001b\r\fgnU3u?\u0012{WO\u00197f+\t\u0011\t\u0001E\u0003\u0003\u0004)\u0003\u0006KD\u0002H\u0005\u000bI1Aa\u00021\u0003\u0015y\u0005oU3u\u0003-\u0019\u0017M\\*fi~cuN\\4\u0016\u0005\t5\u0001#\u0002B\u0002\u0015\u0002\u0004\u0017\u0001D2b]N+Go\u0018$m_\u0006$XC\u0001B\n!\u0015\u0011\u0019A\u00136k\u0003)\u0019\u0017M\\*fi~Ke\u000e^\u000b\u0003\u00053\u0001RAa\u0001KiR\faaY1o'\u0016$X\u0003\u0002B\u0010\u0005O)\"A!\t\u0011\u000f\t\r!Ja\t\u0003$A!\u0011K\u0015B\u0013!\u0011\t\u0019Aa\n\u0005\u000f\u0005\u001dAD1\u0001\u0002\n\u0005q1-\u00198BqBLx\fR8vE2,WC\u0001B\u0017!\u001d\u0011yC!\u000eQ)Bs1!\u0015B\u0019\u0013\r\u0011\u0019DM\u0001\tg\u000e\fG.Z!eI&\u0019!q\u0007'\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\\\u001a\u0002\u0019\r\fg.\u0011=qs~cuN\\4\u0016\u0005\tu\u0002c\u0002B\u0018\u0005k\u0001\u0017\rY\u0001\u000eG\u0006t\u0017\t\u001f9z?\u001acw.\u0019;\u0016\u0005\t\r\u0003c\u0002B\u0018\u0005kQ7N[\u0001\fG\u0006t\u0017\t\u001f9z?&sG/\u0006\u0002\u0003JA9!q\u0006B\u001biV$\u0018aB2b]\u0006C\b/_\u000b\u0005\u0005\u001f\u00129\u0006\u0006\u0004\u0003R\te#q\f\t\u000b\u0005_\u0011)Da\u0015\u0003V\tM\u0003\u0003B)S\u0005+\u0002B!a\u0001\u0003X\u00119\u0011qA\u0011C\u0002\u0005%\u0001\"\u0003B.C\u0005\u0005\t9\u0001B/\u0003-)g/\u001b3f]\u000e,G%M\u001a\u0011\r\u0005u\u00111\u0005B+\u0011%\u0011\t'IA\u0001\u0002\b\u0011\u0019'A\u0006fm&$WM\\2fIE\"\u0004CBA\u0017\u0003g\u0011)&A\u0003ta\u0006\u001cW-\u0006\u0003\u0003j\tUDC\u0002B6\u0005o\u0012i\b\u0005\u0005\u0002\u001e\t5$\u0011\u000fB:\u0013\u0011\u0011y'a\b\u0003\u001b5+H/\u00192mK6{G-\u001e7f!\u0011\t&Ka\u001d\u0011\t\u0005\r!Q\u000f\u0003\b\u0003\u000f\u0011#\u0019AA\u0005\u0011%\u0011IHIA\u0001\u0002\b\u0011Y(A\u0006fm&$WM\\2fIE*\u0004CBA\u000f\u0003\u0017\u0012\u0019\bC\u0005\u0003\u0000\t\n\t\u0011q\u0001\u0003\u0002\u0006YQM^5eK:\u001cW\rJ\u00197!\u0019\ti#a\r\u0003t\u0005y1-\u00198BI\u0012Le\u000e^8`-~3&)\u0006\u0004\u0003\b\nU%Q\u0012\u000b\u0007\u0005\u0013\u0013IJ!+\u0011\u000f\u0005m#Ja#\u0003\u0012B!\u00111\u0001BG\t\u001d\u0011yi\tb\u0001\u0003\u0013\u00111AV3d!\u0011\t&Ka%\u0011\t\u0005\r!Q\u0013\u0003\b\u0005/\u001b#\u0019AA\u0005\u0005\u00051\u0006b\u0002BNG\u0001\u000f!QT\u0001\u0003KZ\u0004r!\u000fBP\u0005\u0017\u0013\u0019+C\u0002\u0003\"j\u0012\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0011\u000bE\u0013)Ka%\n\u0007\t\u001d&G\u0001\u0004WK\u000e$xN\u001d\u0005\b\u0005W\u001b\u00039\u0001BW\u0003\u0011\u0019X-\\5\u0011\r\u0005u\u00111\u0005BJ\u0003=\u0019\u0017M\\*vE&sGo\\0W?Z\u0013UC\u0002BZ\u0005\u007f\u0013I\f\u0006\u0004\u00036\n\u0005'q\u0019\t\b\u0003OR%q\u0017B^!\u0011\t\u0019A!/\u0005\u000f\t=EE1\u0001\u0002\nA!\u0011K\u0015B_!\u0011\t\u0019Aa0\u0005\u000f\t]EE1\u0001\u0002\n!9!1\u0014\u0013A\u0004\t\r\u0007cB\u001d\u0003 \n]&Q\u0019\t\u0006#\n\u0015&Q\u0018\u0005\b\u0005W#\u00039\u0001Be!\u0019\ti\"!*\u0003>\u0006y1-\u00198BI\u0012Le\u000e^8`-Z{f+\u0006\u0004\u0003P\n]'1\u001c\u000b\u0005\u0005#\u0014i\u000eE\u0004\u0002\\)\u0013\u0019N!7\u0011\tE\u0013&Q\u001b\t\u0005\u0003\u0007\u00119\u000eB\u0004\u0003\u0018\u0016\u0012\r!!\u0003\u0011\t\u0005\r!1\u001c\u0003\b\u0005\u001f+#\u0019AA\u0005\u0011\u001d\u0011Y*\na\u0002\u0005?\u0004r!\u000fBP\u00053\u0014\t\u000fE\u0003R\u0005K\u0013).A\bdC:\u001cVOY%oi>|fKV0W+\u0019\u00119Oa<\u0003tR1!\u0011\u001eB{\u0005w\u0004r!a\u001aK\u0005W\u0014\t\u0010\u0005\u0003R%\n5\b\u0003BA\u0002\u0005_$qAa&'\u0005\u0004\tI\u0001\u0005\u0003\u0002\u0004\tMHa\u0002BHM\t\u0007\u0011\u0011\u0002\u0005\b\u000573\u00039\u0001B|!\u001dI$q\u0014By\u0005s\u0004R!\u0015BS\u0005[DqA!@'\u0001\b\u0011y0\u0001\u0003sS:<\u0007CBA\u000f\u0003K\u0013i/A\u0006dC:$u\u000e^0W?Z\u0013UCBB\u0003\u0007+\u0019Y\u0002\u0006\u0004\u0004\b\ru11\u0005\t\u000b\u0007\u0013\u0019yaa\u0005\u0004\u0018\reabA$\u0004\f%\u00191Q\u0002\u0019\u0002\u0015=\u0003X*\u001e7J]:,'/C\u0002\u0004\u00121\u0013Q!S7qYJ\u0002B!a\u0001\u0004\u0016\u00119!qR\u0014C\u0002\u0005%\u0001\u0003B)S\u00073\u0001B!a\u0001\u0004\u001c\u00119!qS\u0014C\u0002\u0005%\u0001b\u0002BNO\u0001\u000f1q\u0004\t\bs\t}51CB\u0011!\u0015\t&QUB\r\u0011\u001d\u0011Yk\na\u0002\u0007K\u0001b!!\b\u0002$\re\u0011!E2b]\u0006C\b/_0W?Z\u0013ulU3nSV111FB\u001b\u0007c!ba!\f\u0004:\r}\u0002C\u0003B\u0018\u0005k\u0019yca\r\u00048A!\u00111AB\u0019\t\u001d\u0011y\t\u000bb\u0001\u0003\u0013\u0001B!a\u0001\u00046\u00119!q\u0013\u0015C\u0002\u0005%\u0001\u0003B)S\u0007gAqAa')\u0001\b\u0019Y\u0004E\u0004:\u0005?\u001byc!\u0010\u0011\u000bE\u0013)ka\r\t\u000f\t-\u0006\u0006q\u0001\u0004BA1\u0011QDA\u0012\u0007g\t1bY1o\t>$xL\u0016\"`-V11qIB*\u0007\u001f\"ba!\u0013\u0004V\rm\u0003CCB\u0005\u0007\u001f\u0019Ye!\u0015\u0004NA!\u0011KUB'!\u0011\t\u0019aa\u0014\u0005\u000f\t]\u0015F1\u0001\u0002\nA!\u00111AB*\t\u001d\u0011y)\u000bb\u0001\u0003\u0013AqAa'*\u0001\b\u00199\u0006E\u0004:\u0005?\u001b\tf!\u0017\u0011\u000bE\u0013)k!\u0014\t\u000f\t-\u0016\u0006q\u0001\u0004^A1\u0011QDA\u0012\u0007\u001b\n\u0011cY1o\u001bVdG)\u0014,C?\u0012{WO\u00197f+\t\u0019\u0019\u0007E\u0005\u0004f\r=11\u000e)\u0004r9\u0019qia\u001a\n\u0007\r%\u0004'A\u0006Pa6+H.T1ue&D\b\u0003B)\u0004nQK1aa\u001c3\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\tE\u001b\u0019\bV\u0005\u0004\u0007k\u0012$a\u0003#f]N,g+Z2u_J\fabY1o\u001bVdG)\u0014,C?&sG/\u0006\u0002\u0004|AI1QMB\b\u0007{\"8q\u0010\t\u0005#\u000e5T\u000f\u0005\u0003R\u0007g*\u0018\u0001E2b]6+H\u000eR'W\u0005~3En\\1u+\t\u0019)\tE\u0005\u0004f\r=1q\u00116\u0004\nB!\u0011k!\u001cl!\u0011\t61O6\u0002\u001f\r\fg.T;m\t63&i\u0018'p]\u001e,\"aa$\u0011\u0013\r\u00154qBBIA\u000eM\u0005\u0003B)\u0004n\u0005\u0004B!UB:C\u0006y1-\u00198Nk2$UJ\u0016\"`'\u0016l\u0017.\u0006\u0003\u0004\u001a\u000e\u0005FCBBN\u0007O\u001bi\u000b\u0005\u0006\u0004f\r=1QTBR\u0007K\u0003R!UB7\u0007?\u0003B!a\u0001\u0004\"\u00129\u0011q\u0001\u0018C\u0002\u0005%\u0001\u0003B)S\u0007?\u0003R!UB:\u0007?C\u0011b!+/\u0003\u0003\u0005\u001daa+\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0007\u0003[\t\u0019da(\t\u000f\t-f\u0006q\u0001\u00040B1\u0011QDA\u0012\u0007?s1!UBZ\u0013\r\u0019)LM\u0001\u000e-\u0016\u001cGo\u001c:Ck&dG-\u001a:"
)
public interface VectorBuilderOps {
   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Double_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Long_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Float_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Int_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Double_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Float_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Long_$eq(final UFunc.UImpl2 x$1);

   UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Double();

   UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Double();

   UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Long();

   UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Long();

   UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Float();

   UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Float();

   UFunc.InPlaceImpl2 canOpInto_V_S_OpMulScalar_Int();

   UFunc.InPlaceImpl2 canOpInto_V_S_OpDiv_Int();

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canMulInto_V_S$(final VectorBuilderOps $this, final Semiring evidence$1, final ClassTag evidence$2) {
      return $this.canMulInto_V_S(evidence$1, evidence$2);
   }

   default UFunc.InPlaceImpl2 canMulInto_V_S(final Semiring evidence$1, final ClassTag evidence$2) {
      return new UFunc.InPlaceImpl2(evidence$1) {
         private final Semiring sr;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Semiring sr() {
            return this.sr;
         }

         public void apply(final VectorBuilder a, final Object b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               .MODULE$.array_update(a.data(), i, this.sr().$times(.MODULE$.array_apply(a.data(), i), b));
            }

         }

         public {
            this.sr = (Semiring)scala.Predef..MODULE$.implicitly(evidence$1$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canDivInto_V_S$(final VectorBuilderOps $this, final Field evidence$3, final ClassTag evidence$4) {
      return $this.canDivInto_V_S(evidence$3, evidence$4);
   }

   default UFunc.InPlaceImpl2 canDivInto_V_S(final Field evidence$3, final ClassTag evidence$4) {
      return new UFunc.InPlaceImpl2(evidence$3) {
         private final Field f;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Field f() {
            return this.f;
         }

         public void apply(final VectorBuilder a, final Object b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               .MODULE$.array_update(a.data(), i, this.f().$div(.MODULE$.array_apply(a.data(), i), b));
            }

         }

         public {
            this.f = (Field)scala.Predef..MODULE$.implicitly(evidence$3$1);
         }
      };
   }

   UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Double();

   UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Double();

   UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Long();

   UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Long();

   UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Float();

   UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Float();

   UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd_Int();

   UFunc.InPlaceImpl2 canOpInto_V_V_OpSub_Int();

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd$(final VectorBuilderOps $this, final Ring evidence$5, final ClassTag evidence$6) {
      return $this.canOpInto_V_V_OpAdd(evidence$5, evidence$6);
   }

   default UFunc.InPlaceImpl2 canOpInto_V_V_OpAdd(final Ring evidence$5, final ClassTag evidence$6) {
      return new UFunc.InPlaceImpl2(evidence$5) {
         private final Ring r;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Ring r() {
            return this.r;
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add(b.index()[i], .MODULE$.array_apply(b.data(), i));
               }

            }
         }

         public {
            this.r = (Ring)scala.Predef..MODULE$.implicitly(evidence$5$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canOpInto_V_V_OpSub$(final VectorBuilderOps $this, final Ring evidence$7, final ClassTag evidence$8) {
      return $this.canOpInto_V_V_OpSub(evidence$7, evidence$8);
   }

   default UFunc.InPlaceImpl2 canOpInto_V_V_OpSub(final Ring evidence$7, final ClassTag evidence$8) {
      return new UFunc.InPlaceImpl2(evidence$7) {
         private final Ring r;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Ring r() {
            return this.r;
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add(b.index()[i], this.r().negate(.MODULE$.array_apply(b.data(), i)));
               }

            }
         }

         public {
            this.r = (Ring)scala.Predef..MODULE$.implicitly(evidence$7$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canOpInto_V_S_OpAdd$(final VectorBuilderOps $this, final Ring evidence$9, final ClassTag evidence$10) {
      return $this.canOpInto_V_S_OpAdd(evidence$9, evidence$10);
   }

   default UFunc.InPlaceImpl2 canOpInto_V_S_OpAdd(final Ring evidence$9, final ClassTag evidence$10) {
      return new UFunc.InPlaceImpl2(evidence$9) {
         private final Ring r;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Ring r() {
            return this.r;
         }

         public void apply(final VectorBuilder a, final Object b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.add(i, b);
            }

         }

         public {
            this.r = (Ring)scala.Predef..MODULE$.implicitly(evidence$9$1);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canOpInto_V_S_OpSub$(final VectorBuilderOps $this, final Ring evidence$11, final ClassTag evidence$12) {
      return $this.canOpInto_V_S_OpSub(evidence$11, evidence$12);
   }

   default UFunc.InPlaceImpl2 canOpInto_V_S_OpSub(final Ring evidence$11, final ClassTag evidence$12) {
      return new UFunc.InPlaceImpl2(evidence$11) {
         private final Ring r;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         private Ring r() {
            return this.r;
         }

         public void apply(final VectorBuilder a, final Object b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.add(i, this.r().negate(b));
            }

         }

         public {
            this.r = (Ring)scala.Predef..MODULE$.implicitly(evidence$11$1);
         }
      };
   }

   UFunc.InPlaceImpl2 canSet_Double();

   UFunc.InPlaceImpl2 canSet_Long();

   UFunc.InPlaceImpl2 canSet_Float();

   UFunc.InPlaceImpl2 canSet_Int();

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canSet$(final VectorBuilderOps $this) {
      return $this.canSet();
   }

   default UFunc.InPlaceImpl2 canSet() {
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

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            if (a != b) {
               a.clear();
               a.reserve(b.activeSize());

               for(int i = 0; i < b.activeSize(); ++i) {
                  a.add(b.index()[i], .MODULE$.array_apply(b.data(), i));
               }

            }
         }
      };
   }

   UFunc.InPlaceImpl3 canAxpy_Double();

   UFunc.InPlaceImpl3 canAxpy_Long();

   UFunc.InPlaceImpl3 canAxpy_Float();

   UFunc.InPlaceImpl3 canAxpy_Int();

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 canAxpy$(final VectorBuilderOps $this, final Semiring evidence$13, final ClassTag evidence$14) {
      return $this.canAxpy(evidence$13, evidence$14);
   }

   default UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$13, final ClassTag evidence$14) {
      return new UFunc.InPlaceImpl3(evidence$13, evidence$14) {
         private final Semiring sr;
         // $FF: synthetic field
         private final VectorBuilder$ $outer;
         private final Semiring evidence$13$1;
         private final ClassTag evidence$14$1;

         private Semiring sr() {
            return this.sr;
         }

         public void apply(final VectorBuilder a, final Object s, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               if (a == b) {
                  a.$colon$times$eq(this.sr().$plus(this.sr().one(), s), this.$outer.canMulInto_V_S(this.evidence$13$1, this.evidence$14$1));
               } else {
                  int bActiveSize = b.activeSize();
                  a.reserve(bActiveSize + a.activeSize());
                  int i = 0;

                  for(Object bd = b.data(); i < bActiveSize; ++i) {
                     a.add(b.index()[i], this.sr().$times(s, .MODULE$.array_apply(bd, i)));
                  }
               }

            }
         }

         public {
            if (VectorBuilderOps.this == null) {
               throw null;
            } else {
               this.$outer = VectorBuilderOps.this;
               this.evidence$13$1 = evidence$13$1;
               this.evidence$14$1 = evidence$14$1;
               this.sr = (Semiring)scala.Predef..MODULE$.implicitly(evidence$13$1);
            }
         }
      };
   }

   // $FF: synthetic method
   static MutableModule space$(final VectorBuilderOps $this, final Field evidence$15, final ClassTag evidence$16) {
      return $this.space(evidence$15, evidence$16);
   }

   default MutableModule space(final Field evidence$15, final ClassTag evidence$16) {
      return MutableModule$.MODULE$.make((a, b, tolerance) -> BoxesRunTime.boxToBoolean($anonfun$space$1(evidence$15, evidence$16, a, b, BoxesRunTime.unboxToDouble(tolerance))), evidence$15, ((VectorBuilder$)this).canZerosBuilder(evidence$16, evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$15)), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.pureFromUpdate(this.canMulInto_V_S(evidence$15, evidence$16), ((VectorBuilder$)this).canCopyBuilder(evidence$16, evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$15))), HasOps$.MODULE$.pureFromUpdate(this.canOpInto_V_V_OpAdd(evidence$15, evidence$16), ((VectorBuilder$)this).canCopyBuilder(evidence$16, evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$15))), HasOps$.MODULE$.pureFromUpdate(this.canOpInto_V_V_OpSub(evidence$15, evidence$16), ((VectorBuilder$)this).canCopyBuilder(evidence$16, evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$15))), ((VectorBuilder$)this).canCopyBuilder(evidence$16, evidence$15, Zero$.MODULE$.zeroFromSemiring(evidence$15)), this.canMulInto_V_S(evidence$15, evidence$16), this.canOpInto_V_V_OpAdd(evidence$15, evidence$16), this.canOpInto_V_V_OpSub(evidence$15, evidence$16), this.canSet(), this.canAxpy(evidence$15, evidence$16));
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canAddInto_V_VB$(final VectorBuilderOps $this, final scala..less.colon.less ev, final Semiring semi) {
      return $this.canAddInto_V_VB(ev, semi);
   }

   default UFunc.InPlaceImpl2 canAddInto_V_VB(final scala..less.colon.less ev, final Semiring semi) {
      return new UFunc.InPlaceImpl2(ev, semi) {
         private final scala..less.colon.less ev$1;
         private final Semiring semi$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Object a, final VectorBuilder b) {
            boolean cond$macro$1 = b.length() < 0 || ((Vector)this.ev$1.apply(a)).length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: b.length.<(0).||(ev.apply(a).length.==(b.length))");
            } else {
               int i = 0;

               for(Object bd = b.data(); i < b.activeSize(); ++i) {
                  ((TensorLike)this.ev$1.apply(a)).update(BoxesRunTime.boxToInteger(b.index()[i]), this.semi$1.$plus(((TensorLike)this.ev$1.apply(a)).apply(BoxesRunTime.boxToInteger(b.index()[i])), .MODULE$.array_apply(bd, i)));
               }

            }
         }

         public {
            this.ev$1 = ev$1;
            this.semi$1 = semi$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canSubInto_V_VB$(final VectorBuilderOps $this, final scala..less.colon.less ev, final Ring semi) {
      return $this.canSubInto_V_VB(ev, semi);
   }

   default UFunc.InPlaceImpl2 canSubInto_V_VB(final scala..less.colon.less ev, final Ring semi) {
      return new UFunc.InPlaceImpl2(ev, semi) {
         private final scala..less.colon.less ev$2;
         private final Ring semi$2;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Object a, final VectorBuilder b) {
            boolean cond$macro$1 = b.length() < 0 || ((Vector)this.ev$2.apply(a)).length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: b.length.<(0).||(ev.apply(a).length.==(b.length))");
            } else {
               int i = 0;

               for(Object bd = b.data(); i < b.activeSize(); ++i) {
                  ((TensorLike)this.ev$2.apply(a)).update(BoxesRunTime.boxToInteger(b.index()[i]), this.semi$2.$minus(((TensorLike)this.ev$2.apply(a)).apply(BoxesRunTime.boxToInteger(b.index()[i])), .MODULE$.array_apply(bd, i)));
               }

            }
         }

         public {
            this.ev$2 = ev$2;
            this.semi$2 = semi$2;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canAddInto_VV_V$(final VectorBuilderOps $this, final scala..less.colon.less ev) {
      return $this.canAddInto_VV_V(ev);
   }

   default UFunc.InPlaceImpl2 canAddInto_VV_V(final scala..less.colon.less ev) {
      return new UFunc.InPlaceImpl2(ev) {
         private final scala..less.colon.less ev$3;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final Object b) {
            boolean cond$macro$1 = a.length() < 0 || a.length() == ((Vector)this.ev$3.apply(b)).length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(a.length.==(ev.apply(b).length))");
            } else {
               Vector var5 = (Vector)this.ev$3.apply(b);
               if (var5 instanceof StorageVector) {
                  StorageVector var6 = (StorageVector)var5;
                  int i = 0;

                  for(Object bd = var6.data(); i < var6.iterableSize(); ++i) {
                     if (var6.isActive(i)) {
                        a.add(var6.indexAt(i), .MODULE$.array_apply(bd, i));
                     }
                  }

                  BoxedUnit var3 = BoxedUnit.UNIT;
               } else {
                  a.reserve(a.activeSize() + ((TensorLike)this.ev$3.apply(b)).activeSize());
                  int left$macro$2 = a.length();
                  int right$macro$3 = ((Vector)this.ev$3.apply(b)).length();
                  if (left$macro$2 != right$macro$3) {
                     throw new IllegalArgumentException((new StringBuilder(78)).append("requirement failed: Dimension mismatch!: ").append("a.length == ev.apply(b).length (").append(left$macro$2).append(" ").append("!=").append(" ").append(right$macro$3).append(")").toString());
                  }

                  ((QuasiTensor)this.ev$3.apply(b)).activeIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(check$ifrefutable$1))).foreach((x$1) -> {
                     $anonfun$apply$2(a, x$1);
                     return BoxedUnit.UNIT;
                  });
                  BoxedUnit var11 = BoxedUnit.UNIT;
               }

            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$1(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$2(final VectorBuilder a$1, final Tuple2 x$1) {
            if (x$1 != null) {
               int i = x$1._1$mcI$sp();
               Object v = x$1._2();
               a$1.add(i, v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$1);
            }
         }

         public {
            this.ev$3 = ev$3;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 canSubInto_VV_V$(final VectorBuilderOps $this, final scala..less.colon.less ev, final Ring ring) {
      return $this.canSubInto_VV_V(ev, ring);
   }

   default UFunc.InPlaceImpl2 canSubInto_VV_V(final scala..less.colon.less ev, final Ring ring) {
      return new UFunc.InPlaceImpl2(ev, ring) {
         private final scala..less.colon.less ev$4;
         private final Ring ring$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final Object b) {
            boolean cond$macro$1 = a.length() < 0 || a.length() == ((Vector)this.ev$4.apply(b)).length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(a.length.==(ev.apply(b).length))");
            } else {
               if (b instanceof StorageVector) {
                  Object var6 = b;
                  int i = 0;

                  for(Object[] bd = ((Storage)b).data(); i < ((Storage)var6).iterableSize(); ++i) {
                     if (((Storage)var6).isActive(i)) {
                        a.add(((Storage)var6).indexAt(i), this.ring$1.negate(bd[i]));
                     }
                  }

                  BoxedUnit var3 = BoxedUnit.UNIT;
               } else {
                  a.reserve(a.activeSize() + ((TensorLike)this.ev$4.apply(b)).activeSize());
                  int left$macro$2 = a.length();
                  int right$macro$3 = ((Vector)this.ev$4.apply(b)).length();
                  if (left$macro$2 != right$macro$3) {
                     throw new IllegalArgumentException((new StringBuilder(78)).append("requirement failed: Dimension mismatch!: ").append("a.length == ev.apply(b).length (").append(left$macro$2).append(" ").append("!=").append(" ").append(right$macro$3).append(")").toString());
                  }

                  ((QuasiTensor)this.ev$4.apply(b)).activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$3(check$ifrefutable$2))).foreach((x$2) -> {
                     $anonfun$apply$4(this, a, x$2);
                     return BoxedUnit.UNIT;
                  });
                  BoxedUnit var11 = BoxedUnit.UNIT;
               }

            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$3(final Tuple2 check$ifrefutable$2) {
            boolean var1;
            if (check$ifrefutable$2 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$4(final Object $this, final VectorBuilder a$2, final Tuple2 x$2) {
            if (x$2 != null) {
               int i = x$2._1$mcI$sp();
               Object v = x$2._2();
               a$2.add(i, $this.ring$1.negate(v));
               BoxedUnit var3 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$2);
            }
         }

         public {
            this.ev$4 = ev$4;
            this.ring$1 = ring$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canDot_V_VB$(final VectorBuilderOps $this, final scala..less.colon.less ev, final Semiring semi) {
      return $this.canDot_V_VB(ev, semi);
   }

   default UFunc.UImpl2 canDot_V_VB(final scala..less.colon.less ev, final Semiring semi) {
      return new UFunc.UImpl2(ev, semi) {
         private final scala..less.colon.less ev$5;
         private final Semiring semi$3;

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

         public Object apply(final Object a, final VectorBuilder b) {
            boolean cond$macro$1 = b.length() < 0 || ((Vector)this.ev$5.apply(a)).length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: b.length.<(0).||(ev.apply(a).length.==(b.length))");
            } else {
               Object result = this.semi$3.zero();
               int i = 0;

               for(Object bd = b.data(); i < b.activeSize(); ++i) {
                  result = this.semi$3.$plus(result, this.semi$3.$times(((TensorLike)this.ev$5.apply(a)).apply(BoxesRunTime.boxToInteger(b.index()[i])), .MODULE$.array_apply(bd, i)));
               }

               return result;
            }
         }

         public {
            this.ev$5 = ev$5;
            this.semi$3 = semi$3;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 canAxpy_V_VB_Semi$(final VectorBuilderOps $this, final scala..less.colon.less ev, final Semiring semi) {
      return $this.canAxpy_V_VB_Semi(ev, semi);
   }

   default UFunc.InPlaceImpl3 canAxpy_V_VB_Semi(final scala..less.colon.less ev, final Semiring semi) {
      return new UFunc.InPlaceImpl3(ev, semi) {
         private final scala..less.colon.less ev$6;
         private final Semiring semi$4;

         public void apply(final Object a, final Object s, final VectorBuilder b) {
            boolean cond$macro$1 = b.length() < 0 || ((Vector)this.ev$6.apply(a)).length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: b.length.<(0).||(ev.apply(a).length.==(b.length))");
            } else {
               int i = 0;

               for(Object bd = b.data(); i < b.activeSize(); ++i) {
                  ((TensorLike)this.ev$6.apply(a)).update(BoxesRunTime.boxToInteger(b.index()[i]), this.semi$4.$plus(((TensorLike)this.ev$6.apply(a)).apply(BoxesRunTime.boxToInteger(b.index()[i])), this.semi$4.$times(s, .MODULE$.array_apply(bd, i))));
               }

            }
         }

         public {
            this.ev$6 = ev$6;
            this.semi$4 = semi$4;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 canDot_VB_V$(final VectorBuilderOps $this, final scala..less.colon.less ev, final Semiring semi) {
      return $this.canDot_VB_V(ev, semi);
   }

   default UFunc.UImpl2 canDot_VB_V(final scala..less.colon.less ev, final Semiring semi) {
      return new UFunc.UImpl2(ev, semi) {
         private final scala..less.colon.less ev$7;
         private final Semiring semi$5;

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

         public Object apply(final VectorBuilder b, final Object a) {
            boolean cond$macro$1 = b.length() < 0 || ((Vector)this.ev$7.apply(a)).length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: b.length.<(0).||(ev.apply(a).length.==(b.length))");
            } else {
               Object result = this.semi$5.zero();
               int i = 0;

               for(Object bd = b.data(); i < b.activeSize(); ++i) {
                  result = this.semi$5.$plus(result, this.semi$5.$times(.MODULE$.array_apply(bd, i), ((TensorLike)this.ev$7.apply(a)).apply(BoxesRunTime.boxToInteger(b.index()[i]))));
               }

               return result;
            }
         }

         public {
            this.ev$7 = ev$7;
            this.semi$5 = semi$5;
         }
      };
   }

   UFunc.UImpl2 canMulDMVB_Double();

   UFunc.UImpl2 canMulDMVB_Int();

   UFunc.UImpl2 canMulDMVB_Float();

   UFunc.UImpl2 canMulDMVB_Long();

   // $FF: synthetic method
   static UFunc.UImpl2 canMulDMVB_Semi$(final VectorBuilderOps $this, final ClassTag evidence$17, final Semiring semi) {
      return $this.canMulDMVB_Semi(evidence$17, semi);
   }

   default UFunc.UImpl2 canMulDMVB_Semi(final ClassTag evidence$17, final Semiring semi) {
      return new UFunc.UImpl2(evidence$17, semi) {
         private final ClassTag evidence$17$1;
         private final Semiring semi$6;

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

         public DenseVector apply(final DenseMatrix a, final VectorBuilder b) {
            DenseVector result = DenseVector$.MODULE$.zeros(a.rows(), this.evidence$17$1, Zero$.MODULE$.zeroFromSemiring(this.semi$6));
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.activeSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               package$.MODULE$.axpy(.MODULE$.array_apply(b.data(), index$macro$2), a.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(b.index()[index$macro$2]), HasOps$.MODULE$.canSliceCol()), result, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Generic(this.semi$6));
            }

            return result;
         }

         public {
            this.evidence$17$1 = evidence$17$1;
            this.semi$6 = semi$6;
         }
      };
   }

   // $FF: synthetic method
   static boolean $anonfun$space$1(final Field evidence$15$1, final ClassTag evidence$16$1, final VectorBuilder a, final VectorBuilder b, final double tolerance) {
      HashVector aHV = a.toHashVector();
      MutableVectorField hvSpace = HashVector$.MODULE$.space(evidence$15$1, evidence$16$1, Zero$.MODULE$.zeroFromSemiring(evidence$15$1));
      double diff = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(a.toHashVector().$minus(b.toHashVector(), hvSpace.subVV()), hvSpace.normImpl()));
      return diff < tolerance;
   }

   static void $init$(final VectorBuilderOps $this) {
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Double_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final VectorBuilder a, final double b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.data$mcD$sp()[i] *= b;
            }

         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Double_$eq(new UFunc$InPlaceImpl2$mcD$sp() {
         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final double b) {
            this.apply$mcD$sp(a, b);
         }

         public void apply$mcD$sp(final VectorBuilder a, final double b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.data$mcD$sp()[i] /= b;
            }

         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final long b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.data$mcJ$sp()[i] *= b;
            }

         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final long b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.data$mcJ$sp()[i] /= b;
            }

         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Float_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final VectorBuilder a, final float b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.data$mcF$sp()[i] *= b;
            }

         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Float_$eq(new UFunc$InPlaceImpl2$mcF$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final float b) {
            this.apply$mcF$sp(a, b);
         }

         public void apply$mcF$sp(final VectorBuilder a, final float b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.data$mcF$sp()[i] /= b;
            }

         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpMulScalar_Int_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final VectorBuilder a, final int b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.data$mcI$sp()[i] *= b;
            }

         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_S_OpDiv_Int_$eq(new UFunc$InPlaceImpl2$mcI$sp() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final int b) {
            this.apply$mcI$sp(a, b);
         }

         public void apply$mcI$sp(final VectorBuilder a, final int b) {
            for(int i = 0; i < a.activeSize(); ++i) {
               a.data$mcI$sp()[i] /= b;
            }

         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Double_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add$mcD$sp(b.index()[i], b.data$mcD$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Double_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add$mcD$sp(b.index()[i], -b.data$mcD$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add$mcJ$sp(b.index()[i], b.data$mcJ$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add$mcJ$sp(b.index()[i], -b.data$mcJ$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Float_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add$mcF$sp(b.index()[i], b.data$mcF$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Float_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add$mcF$sp(b.index()[i], -b.data$mcF$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpAdd_Int_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add$mcI$sp(b.index()[i], b.data$mcI$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canOpInto_V_V_OpSub_Int_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               a.reserve(a.activeSize() + b.activeSize());
               int i = 0;

               for(int bActiveSize = b.activeSize(); i < bActiveSize; ++i) {
                  a.add$mcI$sp(b.index()[i], -b.data$mcI$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Double_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            if (a != b) {
               a.clear();
               a.reserve(b.activeSize());

               for(int i = 0; i < b.activeSize(); ++i) {
                  a.add$mcD$sp(b.index()[i], b.data$mcD$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Long_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            if (a != b) {
               a.clear();
               a.reserve(b.activeSize());

               for(int i = 0; i < b.activeSize(); ++i) {
                  a.add$mcJ$sp(b.index()[i], b.data$mcJ$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Float_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            if (a != b) {
               a.clear();
               a.reserve(b.activeSize());

               for(int i = 0; i < b.activeSize(); ++i) {
                  a.add$mcF$sp(b.index()[i], b.data$mcF$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canSet_Int_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final VectorBuilder a, final VectorBuilder b) {
            if (a != b) {
               a.clear();
               a.reserve(b.activeSize());

               for(int i = 0; i < b.activeSize(); ++i) {
                  a.add$mcI$sp(b.index()[i], b.data$mcI$sp()[i]);
               }

            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Double_$eq(new UFunc.InPlaceImpl3() {
         // $FF: synthetic field
         private final VectorBuilder$ $outer;

         public void apply(final VectorBuilder a, final double s, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               if (a == b) {
                  a.$colon$times$eq(BoxesRunTime.boxToDouble((double)1 + s), this.$outer.canOpInto_V_S_OpMulScalar_Double());
               } else {
                  int bActiveSize = b.activeSize();
                  a.reserve(bActiveSize + a.activeSize());
                  int i = 0;

                  for(double[] bd = b.data$mcD$sp(); i < bActiveSize; ++i) {
                     a.add$mcD$sp(b.index()[i], s * bd[i]);
                  }
               }

            }
         }

         public {
            if (VectorBuilderOps.this == null) {
               throw null;
            } else {
               this.$outer = VectorBuilderOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Long_$eq(new UFunc.InPlaceImpl3() {
         // $FF: synthetic field
         private final VectorBuilder$ $outer;

         public void apply(final VectorBuilder a, final long s, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               if (a == b) {
                  a.$colon$times$eq(BoxesRunTime.boxToLong(1L + s), this.$outer.canOpInto_V_S_OpMulScalar_Long());
               } else {
                  int bActiveSize = b.activeSize();
                  a.reserve(bActiveSize + a.activeSize());
                  int i = 0;

                  for(long[] bd = b.data$mcJ$sp(); i < bActiveSize; ++i) {
                     a.add$mcJ$sp(b.index()[i], s * bd[i]);
                  }
               }

            }
         }

         public {
            if (VectorBuilderOps.this == null) {
               throw null;
            } else {
               this.$outer = VectorBuilderOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Float_$eq(new UFunc.InPlaceImpl3() {
         // $FF: synthetic field
         private final VectorBuilder$ $outer;

         public void apply(final VectorBuilder a, final float s, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               if (a == b) {
                  a.$colon$times$eq(BoxesRunTime.boxToFloat((float)1 + s), this.$outer.canOpInto_V_S_OpMulScalar_Float());
               } else {
                  int bActiveSize = b.activeSize();
                  a.reserve(bActiveSize + a.activeSize());
                  int i = 0;

                  for(float[] bd = b.data$mcF$sp(); i < bActiveSize; ++i) {
                     a.add$mcF$sp(b.index()[i], s * bd[i]);
                  }
               }

            }
         }

         public {
            if (VectorBuilderOps.this == null) {
               throw null;
            } else {
               this.$outer = VectorBuilderOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canAxpy_Int_$eq(new UFunc.InPlaceImpl3() {
         // $FF: synthetic field
         private final VectorBuilder$ $outer;

         public void apply(final VectorBuilder a, final int s, final VectorBuilder b) {
            boolean cond$macro$1 = a.length() < 0 || b.length() < 0 || a.length() == b.length();
            if (!cond$macro$1) {
               throw new IllegalArgumentException("requirement failed: Dimension mismatch!: a.length.<(0).||(b.length.<(0)).||(a.length.==(b.length))");
            } else {
               if (a == b) {
                  a.$colon$times$eq(BoxesRunTime.boxToInteger(1 + s), this.$outer.canOpInto_V_S_OpMulScalar_Int());
               } else {
                  int bActiveSize = b.activeSize();
                  a.reserve(bActiveSize + a.activeSize());
                  int i = 0;

                  for(int[] bd = b.data$mcI$sp(); i < bActiveSize; ++i) {
                     a.add$mcI$sp(b.index()[i], s * bd[i]);
                  }
               }

            }
         }

         public {
            if (VectorBuilderOps.this == null) {
               throw null;
            } else {
               this.$outer = VectorBuilderOps.this;
            }
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Double_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseMatrix a, final VectorBuilder b) {
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.activeSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               package$.MODULE$.axpy(BoxesRunTime.boxToDouble(b.data$mcD$sp()[index$macro$2]), a.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(b.index()[index$macro$2]), HasOps$.MODULE$.canSliceCol()), result, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_T_DV_Double());
            }

            return result;
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Int_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseMatrix a, final VectorBuilder b) {
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.activeSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               package$.MODULE$.axpy(BoxesRunTime.boxToInteger(b.data$mcI$sp()[index$macro$2]), a.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(b.index()[index$macro$2]), HasOps$.MODULE$.canSliceCol()), result, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Int());
            }

            return result;
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Float_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseMatrix a, final VectorBuilder b) {
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.activeSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               package$.MODULE$.axpy(BoxesRunTime.boxToFloat(b.data$mcF$sp()[index$macro$2]), a.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(b.index()[index$macro$2]), HasOps$.MODULE$.canSliceCol()), result, HasOps$.MODULE$.impl_scaledAdd_InPlace_DV_S_DV_Float());
            }

            return result;
         }
      });
      $this.breeze$linalg$operators$VectorBuilderOps$_setter_$canMulDMVB_Long_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final DenseMatrix a, final VectorBuilder b) {
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.rows(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            int index$macro$2 = 0;

            for(int limit$macro$4 = b.activeSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               package$.MODULE$.axpy(BoxesRunTime.boxToLong(b.data$mcJ$sp()[index$macro$2]), a.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(b.index()[index$macro$2]), HasOps$.MODULE$.canSliceCol()), result, HasOps$.MODULE$.impl_scaleAdd_InPlace_DV_S_DV_Long());
            }

            return result;
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
