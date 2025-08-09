package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCreateZeros;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.EntrywiseMatrixNorms$;
import breeze.math.Field;
import breeze.math.MatrixInnerProduct;
import breeze.math.MutableFiniteCoordinateField;
import breeze.math.MutableFiniteCoordinateField$;
import breeze.math.Semiring;
import breeze.stats.distributions.Rand;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import breeze.util.ReflectionUtil$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Builder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\ra\u0001B%K\u0005=C!\"!\b\u0001\u0005\u000b\u0007I\u0011AA\u0010\u0011)\t9\u0003\u0001B\u0001B\u0003%\u0011\u0011\u0005\u0005\u000b\u0003S\u0001!Q1A\u0005\u0002\u0005}\u0001BCA\u0016\u0001\t\u0005\t\u0015!\u0003\u0002\"!Q\u0011Q\u0006\u0001\u0003\u0006\u0004%\t!a\f\t\u0015\u0005]\u0002A!A!\u0002\u0013\t\t\u0004\u0003\u0006\u0002:\u0001\u0011)\u0019!C\u0001\u0003?A!\"a\u000f\u0001\u0005\u0003\u0005\u000b\u0011BA\u0011\u0011)\ti\u0004\u0001BC\u0002\u0013\u0005\u0011q\u0004\u0005\u000b\u0003\u007f\u0001!\u0011!Q\u0001\n\u0005\u0005\u0002BCA!\u0001\t\u0015\r\u0011\"\u0001\u0002D!Q\u00111\n\u0001\u0003\u0002\u0003\u0006I!!\u0012\t\u000f\u00055\u0003\u0001\"\u0001\u0002P!9\u0011Q\n\u0001\u0005\u0002\u0005u\u0003bBA'\u0001\u0011\u0005\u0011Q\u000f\u0005\b\u0003\u001b\u0002A\u0011AA@\u0011\u001d\ti\u0005\u0001C\u0001\u0003\u000fCq!a$\u0001\t\u0003\t\t\nC\u0004\u0002\u001c\u0002!\t!!(\t\u000f\u0005\r\u0006\u0001\"\u0001\u0002&\"9\u0011\u0011\u0017\u0001\u0005\u0002\u0005M\u0006bBAb\u0001\u0011\u0005\u0011q\u0006\u0005\b\u0003\u000b\u0004A\u0011AAd\u0011\u001d\ty\r\u0001C\u0001\u0003#D\u0011\"!8\u0001#\u0003%\t!a8\t\u000f\u0005U\b\u0001\"\u0003\u0002D!9\u0011q\u001f\u0001\u0005\n\u0005\r\u0003bBA}\u0001\u0011\u0005\u00111 \u0005\n\u0005\u0007\u0001\u0011\u0013!C\u0001\u0003?DqA!\u0002\u0001\t\u0003\u00119\u0001C\u0004\u0003\n\u0001!\tAa\u0003\t\u000f\tU\u0001\u0001\"\u0001\u0003\u0018!9!1\u0004\u0001\u0005\u0002\tu\u0001b\u0002B\u0011\u0001\u0011\u0005\u0011q\u0004\u0005\b\u0005G\u0001A\u0011\u0001B\u0013\u0011\u001d\u0011\u0019\u0003\u0001C\u0001\u0005WAqA!\r\u0001\t\u0003\u0011\u0019\u0004C\u0004\u00038\u0001!\tA!\u000f\t\u000f\tu\u0002\u0001\"\u0001\u0002D!9!q\b\u0001\u0005B\t\u0005\u0003b\u0002B,\u0001\u0011\u0005!q\u0001\u0005\b\u00053\u0002A1\u0002B.\u0011\u001d\u0011)\u0007\u0001C\u0001\u0005OBqA!\u001a\u0001\t\u0003\u0011i\bC\u0004\u0003f\u0001!\tA!#\t\u000f\t\u0015\u0004\u0001\"\u0001\u0003\u0016\"A!1\u0014\u0001\u0005\u0002)\u000by\u0002\u0003\u0005\u0003\u001e\u0002!\tASA\u0010\u0011\u001d\u0011y\n\u0001C\u0005\u0003?AqA!)\u0001\t\u0003\t\u0019\u0005\u0003\u0005\u0003$\u0002!\tA\u0013BS\u0011\u001d\u0011Y\u000b\u0001C\u0005\u0005[;qAa/K\u0011\u0003\u0011iL\u0002\u0004J\u0015\"\u0005!q\u0018\u0005\b\u0003\u001b2D\u0011\u0001Bl\u0011\u001d\u0011IN\u000eC\u0001\u00057Dqa!\u00037\t\u0003\u0019Y\u0001C\u0004\u0004\nY\"\taa\u000e\t\u000f\r\u0005d\u0007\"\u0011\u0004d!91\u0011\u0015\u001c\u0005\u0002\r\r\u0006bBBlm\u0011\u00051\u0011\u001c\u0005\b\tS1D\u0011\u0001C\u0016\u0011\u001d!9E\u000eC\u0002\t\u0013Bq\u0001\"\u00197\t\u0007!\u0019gB\u0004\u0005\u0000YB\t\u0001\"!\u0007\u000f\u0011\u0015e\u0007#\u0001\u0005\b\"9\u0011Q\n\"\u0005\u0002\u0011%\u0005b\u0002CF\u0005\u0012\rAQ\u0012\u0005\b\tk3D\u0011\u0002BW\u0011%!yLNI\u0001\n\u0003!\t\rC\u0005\u0005\\Z\n\n\u0011\"\u0001\u0005^\"IA1\u001f\u001c\u0002\u0002\u0013%AQ\u001f\u0002\f\t\u0016t7/Z'biJL\u0007P\u0003\u0002L\u0019\u00061A.\u001b8bY\u001eT\u0011!T\u0001\u0007EJ,WM_3\u0004\u0001U\u0011\u0001+X\n\b\u0001E;\u0016qAA\b!\t\u0011V+D\u0001T\u0015\u0005!\u0016!B:dC2\f\u0017B\u0001,T\u0005\u0019\te.\u001f*fMB\u0019\u0001,W.\u000e\u0003)K!A\u0017&\u0003\r5\u000bGO]5y!\taV\f\u0004\u0001\u0005\u0013y\u0003\u0001\u0015!A\u0001\u0006\u0004y&!\u0001,\u0012\u0005\u0001\u001c\u0007C\u0001*b\u0013\t\u00117KA\u0004O_RD\u0017N\\4\u0011\u0005I#\u0017BA3T\u0005\r\te.\u001f\u0015\u0007;\u001eTG/\u001f@\u0011\u0005IC\u0017BA5T\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rZGN\\7\u000f\u0005Ic\u0017BA7T\u0003\u0019!u.\u001e2mKF\"Ae\\:U\u001d\t\u00018/D\u0001r\u0015\t\u0011h*\u0001\u0004=e>|GOP\u0005\u0002)F*1%\u001e<yo:\u0011!K^\u0005\u0003oN\u000b1!\u00138uc\u0011!sn\u001d+2\u000b\rR80 ?\u000f\u0005I[\u0018B\u0001?T\u0003\u00151En\\1uc\u0011!sn\u001d+2\u0011\rz\u0018\u0011AA\u0003\u0003\u0007q1AUA\u0001\u0013\r\t\u0019aU\u0001\u0005\u0019>tw-\r\u0003%_N$\u0006C\u0002-\u0002\nm\u000bi!C\u0002\u0002\f)\u0013!\"T1ue&DH*[6f!\rA\u0006a\u0017\t\u0005\u0003#\t9BD\u0002p\u0003'I1!!\u0006T\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0007\u0002\u001c\ta1+\u001a:jC2L'0\u00192mK*\u0019\u0011QC*\u0002\tI|wo]\u000b\u0003\u0003C\u00012AUA\u0012\u0013\r\t)c\u0015\u0002\u0004\u0013:$\u0018!\u0002:poN\u0004\u0013\u0001B2pYN\fQaY8mg\u0002\nA\u0001Z1uCV\u0011\u0011\u0011\u0007\t\u0005%\u0006M2,C\u0002\u00026M\u0013Q!\u0011:sCf\fQ\u0001Z1uC\u0002\naa\u001c4gg\u0016$\u0018aB8gMN,G\u000fI\u0001\f[\u0006TwN]*ue&$W-\u0001\u0007nC*|'o\u0015;sS\u0012,\u0007%A\u0006jgR\u0013\u0018M\\:q_N,WCAA#!\r\u0011\u0016qI\u0005\u0004\u0003\u0013\u001a&a\u0002\"p_2,\u0017M\\\u0001\rSN$&/\u00198ta>\u001cX\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u001d\u00055\u0011\u0011KA*\u0003+\n9&!\u0017\u0002\\!9\u0011QD\u0007A\u0002\u0005\u0005\u0002bBA\u0015\u001b\u0001\u0007\u0011\u0011\u0005\u0005\b\u0003[i\u0001\u0019AA\u0019\u0011\u001d\tI$\u0004a\u0001\u0003CAq!!\u0010\u000e\u0001\u0004\t\t\u0003C\u0005\u0002B5\u0001\n\u00111\u0001\u0002FQ1\u0011qLA9\u0003g\"B!!\u0004\u0002b!9\u00111\r\bA\u0004\u0005\u0015\u0014aA7b]B)\u0011qMA776\u0011\u0011\u0011\u000e\u0006\u0004\u0003W\u001a\u0016a\u0002:fM2,7\r^\u0005\u0005\u0003_\nIG\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u001d\tiB\u0004a\u0001\u0003CAq!!\u000b\u000f\u0001\u0004\t\t\u0003\u0006\u0006\u0002\u000e\u0005]\u0014\u0011PA>\u0003{Bq!!\b\u0010\u0001\u0004\t\t\u0003C\u0004\u0002*=\u0001\r!!\t\t\u000f\u00055r\u00021\u0001\u00022!9\u0011\u0011H\bA\u0002\u0005\u0005B\u0003CA\u0007\u0003\u0003\u000b\u0019)!\"\t\u000f\u0005u\u0001\u00031\u0001\u0002\"!9\u0011\u0011\u0006\tA\u0002\u0005\u0005\u0002bBA\u0017!\u0001\u0007\u0011\u0011\u0007\u000b\t\u0003\u001b\tI)a#\u0002\u000e\"9\u0011QD\tA\u0002\u0005\u0005\u0002bBA\u0017#\u0001\u0007\u0011\u0011\u0007\u0005\b\u0003s\t\u0002\u0019AA\u0011\u0003\u0015\t\u0007\u000f\u001d7z)\u0015Y\u00161SAL\u0011\u001d\t)J\u0005a\u0001\u0003C\t1A]8x\u0011\u001d\tIJ\u0005a\u0001\u0003C\t1aY8m\u0003-a\u0017N\\3be&sG-\u001a=\u0015\r\u0005\u0005\u0012qTAQ\u0011\u001d\t)j\u0005a\u0001\u0003CAq!!'\u0014\u0001\u0004\t\t#\u0001\rs_^\u001cu\u000e\\;n]\u001a\u0013x.\u001c'j]\u0016\f'/\u00138eKb$B!a*\u0002.B9!+!+\u0002\"\u0005\u0005\u0012bAAV'\n1A+\u001e9mKJBq!a,\u0015\u0001\u0004\t\t#A\u0003j]\u0012,\u00070\u0001\u0004va\u0012\fG/\u001a\u000b\t\u0003k\u000bY,!0\u0002@B\u0019!+a.\n\u0007\u0005e6K\u0001\u0003V]&$\bbBAK+\u0001\u0007\u0011\u0011\u0005\u0005\b\u00033+\u0002\u0019AA\u0011\u0011\u0019\t\t-\u0006a\u00017\u0006\ta/A\u0004u_\u0006\u0013(/Y=\u0002\u001bQ|G)\u001a8tKZ+7\r^8s+\t\tI\r\u0005\u0003Y\u0003\u0017\\\u0016bAAg\u0015\nYA)\u001a8tKZ+7\r^8s\u0003\u001d1G.\u0019;uK:$B!!3\u0002T\"I\u0011Q\u001b\r\u0011\u0002\u0003\u0007\u0011q[\u0001\u0005m&,w\u000fE\u0002Y\u00033L1!a7K\u0005\u00111\u0016.Z<\u0002#\u0019d\u0017\r\u001e;f]\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002b*\"\u0011q[ArW\t\t)\u000f\u0005\u0003\u0002h\u0006EXBAAu\u0015\u0011\tY/!<\u0002\u0013Ut7\r[3dW\u0016$'bAAx'\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005M\u0018\u0011\u001e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2b]\u001ac\u0017\r\u001e;f]ZKWm^\u0001\u000fG\u0006t'+Z:iCB,g+[3x\u0003\u001d\u0011Xm\u001d5ba\u0016$\u0002\"!\u0004\u0002~\u0006}(\u0011\u0001\u0005\b\u0003;a\u0002\u0019AA\u0011\u0011\u001d\tI\u0003\ba\u0001\u0003CA\u0011\"!6\u001d!\u0003\u0005\r!a6\u0002#I,7\u000f[1qK\u0012\"WMZ1vYR$3'\u0001\u0003sKB\u0014XCAA\u0007\u00039\t7\r^5wK&#XM]1u_J,\"A!\u0004\u0011\r\u0005E!q\u0002B\n\u0013\u0011\u0011\t\"a\u0007\u0003\u0011%#XM]1u_J\u0004bAUAU\u0003O[\u0016\u0001F1di&4XMV1mk\u0016\u001c\u0018\n^3sCR|'/\u0006\u0002\u0003\u001aA)\u0011\u0011\u0003B\b7\u0006\u0011\u0012m\u0019;jm\u0016\\U-_:Ji\u0016\u0014\u0018\r^8s+\t\u0011y\u0002\u0005\u0004\u0002\u0012\t=\u0011qU\u0001\u000bC\u000e$\u0018N^3TSj,\u0017a\u0002<bYV,\u0017\t\u001e\u000b\u00047\n\u001d\u0002b\u0002B\u0015G\u0001\u0007\u0011\u0011E\u0001\u0002SR)1L!\f\u00030!9\u0011Q\u0013\u0013A\u0002\u0005\u0005\u0002bBAMI\u0001\u0007\u0011\u0011E\u0001\bS:$W\r_!u)\u0011\t\tC!\u000e\t\u000f\t%R\u00051\u0001\u0002\"\u0005A\u0011n]!di&4X\r\u0006\u0003\u0002F\tm\u0002b\u0002B\u0015M\u0001\u0007\u0011\u0011E\u0001\u001aC2dg+[:ji\u0006\u0014G.Z%oI&\u001cWm]!di&4X-A\u0007u_\u0012+gn]3NCR\u0014\u0018\u000e\u001f\u000b\u0007\u0003\u001b\u0011\u0019Ea\u0012\t\u000f\t\u0015\u0003\u0006q\u0001\u0002f\u0005\u00111-\u001c\u0005\b\u0005\u0013B\u00039\u0001B&\u0003\u0011QXM]8\u0011\u000b\t5#1K.\u000e\u0005\t=#b\u0001B)\u0019\u000691\u000f^8sC\u001e,\u0017\u0002\u0002B+\u0005\u001f\u0012AAW3s_\u0006!1m\u001c9z\u00031!wN\u001c;OK\u0016$',\u001a:p+\u0011\u0011iFa\u0019\u0016\u0005\t}\u0003C\u0002B'\u0005'\u0012\t\u0007E\u0002]\u0005G\"QA\u0018\u0016C\u0002}\u000ba\u0001Z3mKR,GCBA\u0007\u0005S\u0012Y\u0007C\u0004\u0002\u0016.\u0002\r!!\t\t\u000f\t54\u00061\u0001\u0003p\u0005!\u0011\r_5t\u001d\u0011\u0011\tHa\u001e\u000f\u0007a\u0013\u0019(C\u0002\u0003v)\u000bA!\u0011=jg&!!\u0011\u0010B>\u0003\ty\u0006GC\u0002\u0003v)#b!!\u0004\u0003\u0000\t\u0005\u0005bBAMY\u0001\u0007\u0011\u0011\u0005\u0005\b\u0005[b\u0003\u0019\u0001BB\u001d\u0011\u0011\tH!\"\n\t\t\u001d%1P\u0001\u0003?F\"b!!\u0004\u0003\f\nM\u0005bBA\u000f[\u0001\u0007!Q\u0012\t\u0007\u0003#\u0011y)!\t\n\t\tE\u00151\u0004\u0002\u0004'\u0016\f\bb\u0002B7[\u0001\u0007!q\u000e\u000b\u0007\u0003\u001b\u00119J!'\t\u000f\u0005%b\u00061\u0001\u0003\u000e\"9!Q\u000e\u0018A\u0002\t\r\u0015!C7bU>\u00148+\u001b>f\u0003%i\u0017N\\8s'&TX-A\u0005g_>$\bO]5oi\u0006a\u0011n]\"p]RLw-^8vg\u0006AqN^3sY\u0006\u00048\u000f\u0006\u0003\u0002F\t\u001d\u0006b\u0002BUg\u0001\u0007\u0011QB\u0001\u0006_RDWM]\u0001\u0013G\",7m[%t'B,7-[1mSj,G\r\u0006\u0002\u00026\":\u0001A!-\u00038\ne\u0006c\u0001*\u00034&\u0019!QW*\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0001\u0002\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u00031Z\u001abAN)\u0003B\n%\u0007#\u0002-\u0003D\n\u001d\u0017b\u0001Bc\u0015\n\u0011R*\u0019;sSb\u001cuN\\:ueV\u001cGo\u001c:t!\tA\u0006\u0001\u0005\u0003\u0003L\nUWB\u0001Bg\u0015\u0011\u0011yM!5\u0002\u0005%|'B\u0001Bj\u0003\u0011Q\u0017M^1\n\t\u0005e!Q\u001a\u000b\u0003\u0005{\u000bQA_3s_N,BA!8\u0003fR1!q\\B\u0003\u0007\u000f!bA!9\u0003z\n}\b\u0003\u0002-\u0001\u0005G\u00042\u0001\u0018Bs\t%q\u0006\b)A\u0001\u0002\u000b\u0007q\fK\u0006\u0003f\u001e\u0014IO!<\u0003r\nU\u0018GB\u0012lY\n-X.\r\u0003%_N$\u0016GB\u0012vm\n=x/\r\u0003%_N$\u0016GB\u0012{w\nMH0\r\u0003%_N$\u0016\u0007C\u0012\u0000\u0003\u0003\u001190a\u00012\t\u0011z7\u000f\u0016\u0005\n\u0005wD\u0014\u0011!a\u0002\u0005{\f!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\t9'!\u001c\u0003d\"I1\u0011\u0001\u001d\u0002\u0002\u0003\u000f11A\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004C\u0002B'\u0005'\u0012\u0019\u000fC\u0004\u0002\u001ea\u0002\r!!\t\t\u000f\u0005%\u0002\b1\u0001\u0002\"\u000511M]3bi\u0016,Ba!\u0004\u0004\u0016QA1qBB\u0018\u0007c\u0019\u0019\u0004\u0006\u0003\u0004\u0012\r%\u0002\u0003\u0002-\u0001\u0007'\u00012\u0001XB\u000b\t%q\u0016\b)A\u0001\u0002\u000b\u0007q\fK\u0006\u0004\u0016\u001d\u001cIb!\b\u0004\"\r\u0015\u0012GB\u0012lY\u000emQ.\r\u0003%_N$\u0016GB\u0012vm\u000e}q/\r\u0003%_N$\u0016GB\u0012{w\u000e\rB0\r\u0003%_N$\u0016\u0007C\u0012\u0000\u0003\u0003\u00199#a\u00012\t\u0011z7\u000f\u0016\u0005\n\u0007WI\u0014\u0011!a\u0002\u0007[\t!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\u0011iEa\u0015\u0004\u0014!9\u0011QD\u001dA\u0002\u0005\u0005\u0002bBA\u0015s\u0001\u0007\u0011\u0011\u0005\u0005\b\u0003[I\u0004\u0019AB\u001b!\u0015\u0011\u00161GB\n+\u0011\u0019Ida\u0010\u0015\u001d\rm21KB+\u0007/\u001aYf!\u0018\u0004`A!\u0001\fAB\u001f!\ra6q\b\u0003\n=j\u0002\u000b\u0011!AC\u0002}C3ba\u0010h\u0007\u0007\u001a9ea\u0013\u0004PE21e\u001b7\u0004F5\fD\u0001J8t)F21%\u001e<\u0004J]\fD\u0001J8t)F21E_>\u0004Nq\fD\u0001J8t)FB1e`A\u0001\u0007#\n\u0019!\r\u0003%_N$\u0006bBA\u000fu\u0001\u0007\u0011\u0011\u0005\u0005\b\u0003SQ\u0004\u0019AA\u0011\u0011\u001d\tiC\u000fa\u0001\u00073\u0002RAUA\u001a\u0007{Aq!!\u000f;\u0001\u0004\t\t\u0003C\u0004\u0002>i\u0002\r!!\t\t\u0013\u0005\u0005#\b%AA\u0002\u0005\u0015\u0013\u0001B8oKN,Ba!\u001a\u0004nQ11qMBO\u0007?#\u0002b!\u001b\u0004\u0002\u000e\u001d5Q\u0012\t\u00051\u0002\u0019Y\u0007E\u0002]\u0007[\"\u0011BX\u001e!\u0002\u0003\u0005)\u0019A0)\u0017\r5tm!\u001d\u0004v\re4QP\u0019\u0007GU481O<2\t\u0011z7\u000fV\u0019\u0007Gi\\8q\u000f?2\t\u0011z7\u000fV\u0019\u0007G-d71P72\t\u0011z7\u000fV\u0019\tG}\f\taa \u0002\u0004E\"Ae\\:U\u0011%\u0019\u0019iOA\u0001\u0002\b\u0019))\u0001\u0006fm&$WM\\2fIQ\u0002b!a\u001a\u0002n\r-\u0004\"CBEw\u0005\u0005\t9ABF\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0007\u0005\u001b\u0012\u0019fa\u001b\t\u0013\r=5(!AA\u0004\rE\u0015AC3wS\u0012,gnY3%mA111SBM\u0007Wj!a!&\u000b\u0007\r]E*\u0001\u0003nCRD\u0017\u0002BBN\u0007+\u0013\u0001bU3nSJLgn\u001a\u0005\b\u0003;Y\u0004\u0019AA\u0011\u0011\u001d\tIc\u000fa\u0001\u0003C\t1!Z=f+\u0011\u0019)k!,\u0015\t\r\u001d61\u001b\u000b\t\u0007S\u001b\tma2\u0004NB!\u0001\fABV!\ra6Q\u0016\u0003\n=r\u0002\u000b\u0011!AC\u0002}C3b!,h\u0007c\u001b)l!/\u0004>F21e\u001b7\u000446\fD\u0001J8t)F21%\u001e<\u00048^\fD\u0001J8t)F21E_>\u0004<r\fD\u0001J8t)FB1e`A\u0001\u0007\u007f\u000b\u0019!\r\u0003%_N$\u0006\"CBby\u0005\u0005\t9ABc\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u0007\u0003O\niga+\t\u0013\r%G(!AA\u0004\r-\u0017AC3wS\u0012,gnY3%qA1!Q\nB*\u0007WC\u0011ba4=\u0003\u0003\u0005\u001da!5\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\b\u0005\u0004\u0004\u0014\u000ee51\u0016\u0005\b\u0007+d\u0004\u0019AA\u0011\u0003\r!\u0017.\\\u0001\bQ>\u0014(pY1u+\u0019\u0019Yn!=\u0004dR!1Q\u001cC\u0010))\u0019yn!:\u0004x\u0012UA1\u0004\t\u00051\u0002\u0019\t\u000fE\u0002]\u0007G$QAX\u001fC\u0002}Cqaa:>\u0001\b\u0019I/\u0001\u0002fmB9!ka;\u0004p\u000eU\u0018bABw'\n\u0001B\u0005\\3tg\u0012\u001aw\u000e\\8oI1,7o\u001d\t\u00049\u000eEHABBz{\t\u0007qLA\u0001N!\u0011A\u0016l!9\t\u000f\reX\bq\u0001\u0004|\u0006)q\u000e]:fiBA1Q C\u0005\u0007?\u001cyO\u0004\u0003\u0004\u0000\u0012\u0015QB\u0001C\u0001\u0015\r!\u0019AS\u0001\n_B,'/\u0019;peNLA\u0001b\u0002\u0005\u0002\u0005)q\n]*fi&!A1\u0002C\u0007\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d73\u0013\u0011!y\u0001\"\u0005\u0003\u000bU3UO\\2\u000b\u0007\u0011MA*A\u0004hK:,'/[2\t\u000f\u0011]Q\bq\u0001\u0005\u001a\u0005!a/\\1o!\u0019\t9'!\u001c\u0004b\"9!\u0011J\u001fA\u0004\u0011u\u0001C\u0002B'\u0005'\u001a\t\u000fC\u0004\u0005\"u\u0002\r\u0001b\t\u0002\u00115\fGO]5dKN\u0004RA\u0015C\u0013\u0007_L1\u0001b\nT\u0005)a$/\u001a9fCR,GMP\u0001\bm\u0016\u0014HoY1u+\u0011!i\u0003\"\u000e\u0015\t\u0011=B1\t\u000b\t\tc!9\u0004b\u000f\u0005@A!\u0001\f\u0001C\u001a!\raFQ\u0007\u0003\u0006=z\u0012\ra\u0018\u0005\b\u0007st\u00049\u0001C\u001d!!\u0019i\u0010\"\u0003\u00052\u0011E\u0002b\u0002C\f}\u0001\u000fAQ\b\t\u0007\u0003O\ni\u0007b\r\t\u000f\t%c\bq\u0001\u0005BA1!Q\nB*\tgAq\u0001\"\t?\u0001\u0004!)\u0005E\u0003S\tK!\t$\u0001\u0005tG\u0006d\u0017M](g+\u0011!Y\u0005\"\u0018\u0016\u0005\u00115\u0003\u0003\u0003C(\t+\"I\u0006b\u0017\u000e\u0005\u0011E#b\u0001C*\u0015\u000691/\u001e9q_J$\u0018\u0002\u0002C,\t#\u0012\u0001bU2bY\u0006\u0014xJ\u001a\t\u00051\u0002!Y\u0006E\u0002]\t;\"a\u0001b\u0018@\u0005\u0004y&!\u0001+\u0002%\r\fgn\u0011:fCR,',\u001a:pg2K7.Z\u000b\u0005\tK\"\t\b\u0006\u0004\u0005h\u0011MD\u0011\u0010\t\t\t\u001f\"I\u0007\"\u001c\u0005n%!A1\u000eC)\u0005I\u0019\u0015M\\\"sK\u0006$XMW3s_Nd\u0015n[3\u0011\ta\u0003Aq\u000e\t\u00049\u0012ED!\u00020A\u0005\u0004y\u0006\"\u0003C;\u0001\u0006\u0005\t9\u0001C<\u0003-)g/\u001b3f]\u000e,G%\r\u0019\u0011\r\u0005\u001d\u0014Q\u000eC8\u0011%!Y\bQA\u0001\u0002\b!i(A\u0006fm&$WM\\2fIE\n\u0004C\u0002B'\u0005'\"y'A\u0013Ge>\u0014WM\\5vg&sg.\u001a:Qe>$Wo\u0019;EK:\u001cX-T1ue&D8\u000b]1dKB\u0019A1\u0011\"\u000e\u0003Y\u0012QE\u0012:pE\u0016t\u0017.^:J]:,'\u000f\u0015:pIV\u001cG\u000fR3og\u0016l\u0015\r\u001e:jqN\u0003\u0018mY3\u0014\u0005\t\u000bFC\u0001CA\u0003\u0015\u0019\b/Y2f+\u0011!y\tb'\u0015\u0011\u0011EEq\u0014CU\t_\u0003\"ba%\u0005\u0014\u0012]\u0015q\u0015CM\u0013\u0011!)j!&\u000395+H/\u00192mK\u001aKg.\u001b;f\u0007>|'\u000fZ5oCR,g)[3mIB!\u0001\f\u0001CM!\raF1\u0014\u0003\u0007\t;#%\u0019A0\u0003\u0003MC\u0011\u0002\")E\u0003\u0003\u0005\u001d\u0001b)\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\r\t\u0007\u0007'#)\u000b\"'\n\t\u0011\u001d6Q\u0013\u0002\u0006\r&,G\u000e\u001a\u0005\n\tW#\u0015\u0011!a\u0002\t[\u000b1\"\u001a<jI\u0016t7-\u001a\u00132gA1!Q\nB*\t3C\u0011\u0002\"-E\u0003\u0003\u0005\u001d\u0001b-\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000e\t\u0007\u0003O\ni\u0007\"'\u0002\t%t\u0017\u000e\u001e\u0015\u0004\u000b\u0012e\u0006c\u0001*\u0005<&\u0019AQX*\u0003\u00119|\u0017N\u001c7j]\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u00122T\u0003\u0002Cb\t\u000f,\"\u0001\"2+\t\u0005\u0015\u00131\u001d\u0003\n=\u001a\u0003\u000b\u0011!AC\u0002}C3\u0002b2h\t\u0017$y\rb5\u0005XF21e\u001b7\u0005N6\fD\u0001J8t)F21%\u001e<\u0005R^\fD\u0001J8t)F21E_>\u0005Vr\fD\u0001J8t)FB1e`A\u0001\t3\f\u0019!\r\u0003%_N$\u0016\u0001E2sK\u0006$X\r\n3fM\u0006,H\u000e\u001e\u00137+\u0011!\u0019\rb8\u0005\u0013y;\u0005\u0015!A\u0001\u0006\u0004y\u0006f\u0003CpO\u0012\rHq\u001dCv\t_\fdaI6m\tKl\u0017\u0007\u0002\u0013pgR\u000bdaI;w\tS<\u0018\u0007\u0002\u0013pgR\u000bda\t>|\t[d\u0018\u0007\u0002\u0013pgR\u000b\u0004bI@\u0002\u0002\u0011E\u00181A\u0019\u0005I=\u001cH+\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0005xB!A\u0011 C\u0000\u001b\t!YP\u0003\u0003\u0005~\nE\u0017\u0001\u00027b]\u001eLA!\"\u0001\u0005|\n1qJ\u00196fGR\u0004"
)
public class DenseMatrix implements Matrix, Serializable {
   private static final long serialVersionUID = 1L;
   private final int rows;
   private final int cols;
   public final Object data;
   private final int offset;
   private final int majorStride;
   private final boolean isTranspose;

   public static boolean create$default$6() {
      return DenseMatrix$.MODULE$.create$default$6();
   }

   public static boolean $lessinit$greater$default$6() {
      return DenseMatrix$.MODULE$.$lessinit$greater$default$6();
   }

   public static CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$10, final Zero evidence$11) {
      return DenseMatrix$.MODULE$.canCreateZerosLike(evidence$10, evidence$11);
   }

   public static ScalarOf scalarOf() {
      return DenseMatrix$.MODULE$.scalarOf();
   }

   public static DenseMatrix vertcat(final Seq matrices, final UFunc.InPlaceImpl2 opset, final ClassTag vman, final Zero zero) {
      return DenseMatrix$.MODULE$.vertcat(matrices, opset, vman, zero);
   }

   public static DenseMatrix horzcat(final Seq matrices, final .less.colon.less ev, final UFunc.InPlaceImpl2 opset, final ClassTag vman, final Zero zero) {
      return DenseMatrix$.MODULE$.horzcat(matrices, ev, opset, vman, zero);
   }

   public static DenseMatrix eye(final int dim, final ClassTag evidence$7, final Zero evidence$8, final Semiring evidence$9) {
      return DenseMatrix$.MODULE$.eye(dim, evidence$7, evidence$8, evidence$9);
   }

   public static DenseMatrix ones(final int rows, final int cols, final ClassTag evidence$4, final Zero evidence$5, final Semiring evidence$6) {
      return DenseMatrix$.MODULE$.ones(rows, cols, evidence$4, evidence$5, evidence$6);
   }

   public static DenseMatrix create(final int rows, final int cols, final Object data, final int offset, final int majorStride, final boolean isTranspose) {
      return DenseMatrix$.MODULE$.create(rows, cols, data, offset, majorStride, isTranspose);
   }

   public static DenseMatrix create(final int rows, final int cols, final Object data, final Zero evidence$3) {
      return DenseMatrix$.MODULE$.create(rows, cols, data, evidence$3);
   }

   public static DenseMatrix zeros(final int rows, final int cols, final ClassTag evidence$1, final Zero evidence$2) {
      return DenseMatrix$.MODULE$.zeros(rows, cols, evidence$1, evidence$2);
   }

   public static CanCreateZeros canCreateZeros(final ClassTag evidence$19, final Zero evidence$20) {
      return DenseMatrix$.MODULE$.canCreateZeros(evidence$19, evidence$20);
   }

   public static Rand rand$default$3() {
      return DenseMatrix$.MODULE$.rand$default$3();
   }

   public static Matrix rand(final int rows, final int cols, final Rand rand, final ClassTag evidence$17, final Zero evidence$18) {
      return DenseMatrix$.MODULE$.rand(rows, cols, rand, evidence$17, evidence$18);
   }

   public static Matrix tabulate(final int rows, final int cols, final Function2 f, final ClassTag evidence$15, final Zero evidence$16) {
      return DenseMatrix$.MODULE$.tabulate(rows, cols, f, evidence$15, evidence$16);
   }

   public static Matrix fill(final int rows, final int cols, final Function0 v, final ClassTag evidence$13, final Zero evidence$14) {
      return DenseMatrix$.MODULE$.fill(rows, cols, v, evidence$13, evidence$14);
   }

   public Object apply(final Tuple2 i) {
      return Matrix.apply$(this, i);
   }

   public double apply$mcD$sp(final Tuple2 i) {
      return Matrix.apply$mcD$sp$(this, i);
   }

   public float apply$mcF$sp(final Tuple2 i) {
      return Matrix.apply$mcF$sp$(this, i);
   }

   public int apply$mcI$sp(final Tuple2 i) {
      return Matrix.apply$mcI$sp$(this, i);
   }

   public long apply$mcJ$sp(final Tuple2 i) {
      return Matrix.apply$mcJ$sp$(this, i);
   }

   public void update(final Tuple2 i, final Object e) {
      Matrix.update$(this, i, e);
   }

   public void update$mcD$sp(final Tuple2 i, final double e) {
      Matrix.update$mcD$sp$(this, i, e);
   }

   public void update$mcF$sp(final Tuple2 i, final float e) {
      Matrix.update$mcF$sp$(this, i, e);
   }

   public void update$mcI$sp(final Tuple2 i, final int e) {
      Matrix.update$mcI$sp$(this, i, e);
   }

   public void update$mcJ$sp(final Tuple2 i, final long e) {
      Matrix.update$mcJ$sp$(this, i, e);
   }

   public int size() {
      return Matrix.size$(this);
   }

   public Set keySet() {
      return Matrix.keySet$(this);
   }

   public Iterator iterator() {
      return Matrix.iterator$(this);
   }

   public Iterator valuesIterator() {
      return Matrix.valuesIterator$(this);
   }

   public Iterator keysIterator() {
      return Matrix.keysIterator$(this);
   }

   public String toString(final int maxLines, final int maxWidth) {
      return Matrix.toString$(this, maxLines, maxWidth);
   }

   public int toString$default$1() {
      return Matrix.toString$default$1$(this);
   }

   public int toString$default$2() {
      return Matrix.toString$default$2$(this);
   }

   public String toString() {
      return Matrix.toString$(this);
   }

   public boolean equals(final Object p1) {
      return Matrix.equals$(this, p1);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcD$sp$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcF$sp$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcI$sp$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcJ$sp$(this, fn, canMapValues);
   }

   public double apply$mcID$sp(final int i) {
      return TensorLike.apply$mcID$sp$(this, i);
   }

   public float apply$mcIF$sp(final int i) {
      return TensorLike.apply$mcIF$sp$(this, i);
   }

   public int apply$mcII$sp(final int i) {
      return TensorLike.apply$mcII$sp$(this, i);
   }

   public long apply$mcIJ$sp(final int i) {
      return TensorLike.apply$mcIJ$sp$(this, i);
   }

   public void update$mcID$sp(final int i, final double v) {
      TensorLike.update$mcID$sp$(this, i, v);
   }

   public void update$mcIF$sp(final int i, final float v) {
      TensorLike.update$mcIF$sp$(this, i, v);
   }

   public void update$mcII$sp(final int i, final int v) {
      TensorLike.update$mcII$sp$(this, i, v);
   }

   public void update$mcIJ$sp(final int i, final long v) {
      TensorLike.update$mcIJ$sp$(this, i, v);
   }

   public TensorKeys keys() {
      return TensorLike.keys$(this);
   }

   public TensorValues values() {
      return TensorLike.values$(this);
   }

   public TensorPairs pairs() {
      return TensorLike.pairs$(this);
   }

   public TensorActive active() {
      return TensorLike.active$(this);
   }

   public Object apply(final Object slice, final CanSlice canSlice) {
      return TensorLike.apply$(this, slice, canSlice);
   }

   public Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
      return TensorLike.apply$(this, a, b, c, slice, canSlice);
   }

   public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return TensorLike.apply$mcI$sp$(this, a, b, c, slice, canSlice);
   }

   public Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
      return TensorLike.apply$(this, slice1, slice2, canSlice);
   }

   public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$(this, f, bf);
   }

   public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcID$sp$(this, f, bf);
   }

   public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcIF$sp$(this, f, bf);
   }

   public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcII$sp$(this, f, bf);
   }

   public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapPairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$(this, f, bf);
   }

   public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcID$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcIF$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcII$sp$(this, f, bf);
   }

   public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return TensorLike.mapActivePairs$mcIJ$sp$(this, f, bf);
   }

   public Object mapValues(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$(this, f, bf);
   }

   public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcD$sp$(this, f, bf);
   }

   public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcF$sp$(this, f, bf);
   }

   public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcI$sp$(this, f, bf);
   }

   public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapValues$mcJ$sp$(this, f, bf);
   }

   public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$(this, f, bf);
   }

   public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcD$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcF$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcI$sp$(this, f, bf);
   }

   public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return TensorLike.mapActiveValues$mcJ$sp$(this, f, bf);
   }

   public void foreachKey(final Function1 fn) {
      TensorLike.foreachKey$(this, fn);
   }

   public void foreachKey$mcI$sp(final Function1 fn) {
      TensorLike.foreachKey$mcI$sp$(this, fn);
   }

   public void foreachPair(final Function2 fn) {
      TensorLike.foreachPair$(this, fn);
   }

   public void foreachPair$mcID$sp(final Function2 fn) {
      TensorLike.foreachPair$mcID$sp$(this, fn);
   }

   public void foreachPair$mcIF$sp(final Function2 fn) {
      TensorLike.foreachPair$mcIF$sp$(this, fn);
   }

   public void foreachPair$mcII$sp(final Function2 fn) {
      TensorLike.foreachPair$mcII$sp$(this, fn);
   }

   public void foreachPair$mcIJ$sp(final Function2 fn) {
      TensorLike.foreachPair$mcIJ$sp$(this, fn);
   }

   public void foreachValue(final Function1 fn) {
      TensorLike.foreachValue$(this, fn);
   }

   public void foreachValue$mcD$sp(final Function1 fn) {
      TensorLike.foreachValue$mcD$sp$(this, fn);
   }

   public void foreachValue$mcF$sp(final Function1 fn) {
      TensorLike.foreachValue$mcF$sp$(this, fn);
   }

   public void foreachValue$mcI$sp(final Function1 fn) {
      TensorLike.foreachValue$mcI$sp$(this, fn);
   }

   public void foreachValue$mcJ$sp(final Function1 fn) {
      TensorLike.foreachValue$mcJ$sp$(this, fn);
   }

   public boolean forall(final Function2 fn) {
      return TensorLike.forall$(this, (Function2)fn);
   }

   public boolean forall$mcID$sp(final Function2 fn) {
      return TensorLike.forall$mcID$sp$(this, fn);
   }

   public boolean forall$mcIF$sp(final Function2 fn) {
      return TensorLike.forall$mcIF$sp$(this, fn);
   }

   public boolean forall$mcII$sp(final Function2 fn) {
      return TensorLike.forall$mcII$sp$(this, fn);
   }

   public boolean forall$mcIJ$sp(final Function2 fn) {
      return TensorLike.forall$mcIJ$sp$(this, fn);
   }

   public boolean forall(final Function1 fn) {
      return TensorLike.forall$(this, (Function1)fn);
   }

   public boolean forall$mcD$sp(final Function1 fn) {
      return TensorLike.forall$mcD$sp$(this, fn);
   }

   public boolean forall$mcF$sp(final Function1 fn) {
      return TensorLike.forall$mcF$sp$(this, fn);
   }

   public boolean forall$mcI$sp(final Function1 fn) {
      return TensorLike.forall$mcI$sp$(this, fn);
   }

   public boolean forall$mcJ$sp(final Function1 fn) {
      return TensorLike.forall$mcJ$sp$(this, fn);
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

   public IndexedSeq findAll(final Function1 f) {
      return QuasiTensor.findAll$(this, f);
   }

   public IndexedSeq findAll$mcD$sp(final Function1 f) {
      return QuasiTensor.findAll$mcD$sp$(this, f);
   }

   public IndexedSeq findAll$mcF$sp(final Function1 f) {
      return QuasiTensor.findAll$mcF$sp$(this, f);
   }

   public IndexedSeq findAll$mcI$sp(final Function1 f) {
      return QuasiTensor.findAll$mcI$sp$(this, f);
   }

   public IndexedSeq findAll$mcJ$sp(final Function1 f) {
      return QuasiTensor.findAll$mcJ$sp$(this, f);
   }

   public int hashCode() {
      return QuasiTensor.hashCode$(this);
   }

   public int rows() {
      return this.rows;
   }

   public int cols() {
      return this.cols;
   }

   public Object data() {
      return this.data;
   }

   public int offset() {
      return this.offset;
   }

   public int majorStride() {
      return this.majorStride;
   }

   public boolean isTranspose() {
      return this.isTranspose;
   }

   public Object apply(final int row, final int col) {
      if (row >= -this.rows() && row < this.rows()) {
         if (col >= -this.cols() && col < this.cols()) {
            int trueRow = row < 0 ? row + this.rows() : row;
            int trueCol = col < 0 ? col + this.cols() : col;
            return scala.runtime.ScalaRunTime..MODULE$.array_apply(this.data(), this.linearIndex(trueRow, trueCol));
         } else {
            throw new IndexOutOfBoundsException((new StringBuilder(19)).append(new Tuple2.mcII.sp(row, col)).append(" not in [-").append(this.rows()).append(",").append(this.rows()).append(") x [-").append(this.cols()).append(",").append(this.cols()).append(")").toString());
         }
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(19)).append(new Tuple2.mcII.sp(row, col)).append(" not in [-").append(this.rows()).append(",").append(this.rows()).append(") x [-").append(this.cols()).append(",").append(this.cols()).append(")").toString());
      }
   }

   public int linearIndex(final int row, final int col) {
      return this.isTranspose() ? this.offset() + col + row * this.majorStride() : this.offset() + row + col * this.majorStride();
   }

   public Tuple2 rowColumnFromLinearIndex(final int index) {
      int r = (index - this.offset()) % this.majorStride();
      int c = (index - this.offset()) / this.majorStride();
      return this.isTranspose() ? new Tuple2.mcII.sp(c, r) : new Tuple2.mcII.sp(r, c);
   }

   public void update(final int row, final int col, final Object v) {
      if (row >= -this.rows() && row < this.rows()) {
         if (col >= -this.cols() && col < this.cols()) {
            int trueRow = row < 0 ? row + this.rows() : row;
            int trueCol = col < 0 ? col + this.cols() : col;
            scala.runtime.ScalaRunTime..MODULE$.array_update(this.data(), this.linearIndex(trueRow, trueCol), v);
         } else {
            throw new IndexOutOfBoundsException((new StringBuilder(19)).append(new Tuple2.mcII.sp(row, col)).append(" not in [-").append(this.rows()).append(",").append(this.rows()).append(") x [-").append(this.cols()).append(",").append(this.cols()).append(")").toString());
         }
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(19)).append(new Tuple2.mcII.sp(row, col)).append(" not in [-").append(this.rows()).append(",").append(this.rows()).append(") x [-").append(this.cols()).append(",").append(this.cols()).append(")").toString());
      }
   }

   public Object toArray() {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      Object var10000;
      if (this.isContiguous() && !this.isTranspose()) {
         var10000 = ArrayUtil$.MODULE$.copyOfRange(this.data(), this.offset(), this.offset() + this.size());
      } else {
         Object ret = man.newArray(this.rows() * this.cols());
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = this.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ((i, j) -> scala.runtime.ScalaRunTime..MODULE$.array_update(ret, i * this.rows() + j, scala.runtime.ScalaRunTime..MODULE$.array_apply(this.data(), this.linearIndex(j, i)))).apply$mcVII$sp(index$macro$7, index$macro$2);
            }
         }

         var10000 = ret;
      }

      return var10000;
   }

   public DenseVector toDenseVector() {
      return DenseVector$.MODULE$.apply(this.toArray());
   }

   public DenseVector flatten(final View view) {
      while(true) {
         DenseVector var3;
         if (View.Require$.MODULE$.equals(view)) {
            if (!this.breeze$linalg$DenseMatrix$$canFlattenView()) {
               throw new UnsupportedOperationException("Cannot make a view of this matrix.");
            }

            var3 = DenseVector$.MODULE$.create(this.data(), this.offset(), 1, this.rows() * this.cols());
         } else {
            if (!View.Copy$.MODULE$.equals(view)) {
               if (View.Prefer$.MODULE$.equals(view)) {
                  view = View$.MODULE$.viewPreferenceFromBoolean(this.breeze$linalg$DenseMatrix$$canFlattenView());
                  continue;
               }

               throw new MatchError(view);
            }

            var3 = this.toDenseVector();
         }

         return var3;
      }
   }

   public View flatten$default$1() {
      return View.Prefer$.MODULE$;
   }

   public boolean breeze$linalg$DenseMatrix$$canFlattenView() {
      return !this.isTranspose() && this.majorStride() == this.rows();
   }

   public boolean breeze$linalg$DenseMatrix$$canReshapeView() {
      return this.breeze$linalg$DenseMatrix$$canFlattenView();
   }

   public DenseMatrix reshape(final int rows, final int cols, final View view) {
      while(true) {
         int left$macro$1 = rows * cols;
         int right$macro$2 = this.size();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(67)).append("requirement failed: ").append(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Cannot reshape a (%d,%d) matrix to a (%d,%d) matrix!"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.rows()), BoxesRunTime.boxToInteger(this.cols()), BoxesRunTime.boxToInteger(rows), BoxesRunTime.boxToInteger(cols)}))).append(": ").append("rows.*(_cols) == DenseMatrix.this.size (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         }

         if (View.Require$.MODULE$.equals(view)) {
            if (!this.breeze$linalg$DenseMatrix$$canReshapeView()) {
               throw new UnsupportedOperationException("Cannot make a view of this matrix.");
            }

            DenseMatrix var5 = new DenseMatrix(rows, cols, this.data(), this.offset(), this.isTranspose() ? cols : rows, this.isTranspose());
            return var5;
         }

         if (View.Copy$.MODULE$.equals(view)) {
            DenseMatrix result = new DenseMatrix(this.rows(), this.cols(), ArrayUtil$.MODULE$.newArrayLike(this.data(), this.size()));
            result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
            view = View.Require$.MODULE$;
            cols = cols;
            rows = rows;
            this = result;
         } else {
            if (!View.Prefer$.MODULE$.equals(view)) {
               throw new MatchError(view);
            }

            view = View$.MODULE$.viewPreferenceFromBoolean(this.breeze$linalg$DenseMatrix$$canReshapeView());
            cols = cols;
            rows = rows;
         }
      }
   }

   public View reshape$default$3() {
      return View.Prefer$.MODULE$;
   }

   public DenseMatrix repr() {
      return this;
   }

   public Iterator activeIterator() {
      return this.iterator();
   }

   public Iterator activeValuesIterator() {
      return this.valuesIterator();
   }

   public Iterator activeKeysIterator() {
      return this.keysIterator();
   }

   public int activeSize() {
      return scala.runtime.ScalaRunTime..MODULE$.array_length(this.data());
   }

   public Object valueAt(final int i) {
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(this.data(), i);
   }

   public Object valueAt(final int row, final int col) {
      return this.apply(row, col);
   }

   public int indexAt(final int i) {
      return i;
   }

   public boolean isActive(final int i) {
      return true;
   }

   public boolean allVisitableIndicesActive() {
      return true;
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      DenseMatrix result = DenseMatrix$.MODULE$.create(this.rows(), this.cols(), cm.newArray(this.size()), zero);
      result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
      return result;
   }

   public DenseMatrix copy() {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix result = DenseMatrix$.MODULE$.create(this.rows(), this.cols(), man.newArray(this.size()), this.breeze$linalg$DenseMatrix$$dontNeedZero());
      result.$colon$eq(this, HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM());
      return result;
   }

   public Zero breeze$linalg$DenseMatrix$$dontNeedZero() {
      return null;
   }

   public DenseMatrix delete(final int row, final Axis._0$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      boolean cond$macro$1 = row >= 0 && row < this.rows();
      if (!cond$macro$1) {
         throw new IllegalArgumentException((new StringBuilder(64)).append("requirement failed: ").append((new StringBuilder(28)).append("row ").append(row).append(" is not in bounds: [0, ").append(this.rows()).append(")").toString()).append(": ").append("row.>=(0).&&(row.<(DenseMatrix.this.rows))").toString());
      } else {
         return row == 0 ? ((DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.rows()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())).copy() : (row == this.rows() - 1 ? ((DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.rows() - 1), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())).copy() : DenseMatrix$.MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), row), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()), (DenseMatrix)this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(row + 1), this.rows()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows())}), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero()));
      }
   }

   public DenseMatrix delete(final int col, final Axis._1$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      boolean cond$macro$1 = col >= 0 && col < this.cols();
      if (!cond$macro$1) {
         throw new IllegalArgumentException((new StringBuilder(64)).append("requirement failed: ").append((new StringBuilder(28)).append("col ").append(col).append(" is not in bounds: [0, ").append(this.cols()).append(")").toString()).append(": ").append("col.>=(0).&&(col.<(DenseMatrix.this.cols))").toString());
      } else {
         return col == 0 ? ((DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.cols()), HasOps$.MODULE$.canSliceCols())).copy() : (col == this.cols() - 1 ? ((DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.cols() - 1), HasOps$.MODULE$.canSliceCols())).copy() : DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), col), HasOps$.MODULE$.canSliceCols()), (DenseMatrix)this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(col + 1), this.cols()), HasOps$.MODULE$.canSliceCols())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero()));
      }
   }

   public DenseMatrix delete(final Seq rows, final Axis._0$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix var10000;
      if (rows.isEmpty()) {
         var10000 = this.copy();
      } else if (rows.size() == 1) {
         var10000 = this.delete(BoxesRunTime.unboxToInt(rows.apply(0)), axis);
      } else {
         Seq sorted = (Seq)rows.sorted(scala.math.Ordering.Int..MODULE$);
         boolean cond$macro$1 = BoxesRunTime.unboxToInt(sorted.head()) >= 0 && BoxesRunTime.unboxToInt(sorted.last()) < this.rows();
         if (!cond$macro$1) {
            throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: ").append((new StringBuilder(29)).append("row ").append(rows).append(" are not in bounds: [0, ").append(this.rows()).append(")").toString()).append(": ").append("sorted.head.>=(0).&&(sorted.last.<(this.rows))").toString());
         }

         IntRef last = IntRef.create(0);
         Builder matrices = breeze.collection.compat.package$.MODULE$.arraySeqBuilder(scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         sorted.foreach((JFunction1.mcVI.sp)(index) -> {
            boolean cond$macro$2 = index >= last.elem;
            if (!cond$macro$2) {
               throw new AssertionError("assertion failed: index.>=(last)");
            } else {
               if (index != last.elem) {
                  matrices.$plus$eq(this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(last.elem), index), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               last.elem = index + 1;
            }
         });
         if (last.elem != this.rows()) {
            matrices.$plus$eq(this.apply(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(last.elem), this.rows()), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRows()));
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }

         var10000 = DenseMatrix$.MODULE$.vertcat((Seq)matrices.result(), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero());
      }

      return var10000;
   }

   public DenseMatrix delete(final Seq cols, final Axis._1$ axis) {
      ClassTag man = ReflectionUtil$.MODULE$.elemClassTagFromArray(this.data());
      DenseMatrix var10000;
      if (cols.isEmpty()) {
         var10000 = this.copy();
      } else if (cols.size() == 1) {
         var10000 = this.delete(BoxesRunTime.unboxToInt(cols.apply(0)), axis);
      } else {
         Seq sorted = (Seq)cols.sorted(scala.math.Ordering.Int..MODULE$);
         boolean cond$macro$1 = BoxesRunTime.unboxToInt(sorted.head()) >= 0 && BoxesRunTime.unboxToInt(sorted.last()) < this.cols();
         if (!cond$macro$1) {
            throw new IllegalArgumentException((new StringBuilder(68)).append("requirement failed: ").append((new StringBuilder(29)).append("col ").append(cols).append(" are not in bounds: [0, ").append(this.cols()).append(")").toString()).append(": ").append("sorted.head.>=(0).&&(sorted.last.<(this.cols))").toString());
         }

         IntRef last = IntRef.create(0);
         Builder matrices = breeze.collection.compat.package$.MODULE$.arraySeqBuilder(scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         sorted.foreach((JFunction1.mcVI.sp)(index) -> {
            boolean cond$macro$2 = index >= last.elem;
            if (!cond$macro$2) {
               throw new AssertionError("assertion failed: index.>=(last)");
            } else {
               if (index != last.elem) {
                  matrices.$plus$eq(this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(last.elem), index), HasOps$.MODULE$.canSliceCols()));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               last.elem = index + 1;
            }
         });
         if (last.elem != this.cols()) {
            matrices.$plus$eq(this.apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(last.elem), this.cols()), HasOps$.MODULE$.canSliceCols()));
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }

         var10000 = DenseMatrix$.MODULE$.horzcat((Seq)matrices.result(), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), man, this.breeze$linalg$DenseMatrix$$dontNeedZero());
      }

      return var10000;
   }

   public int majorSize() {
      return this.isTranspose() ? this.rows() : this.cols();
   }

   public int minorSize() {
      return this.isTranspose() ? this.cols() : this.rows();
   }

   public int breeze$linalg$DenseMatrix$$footprint() {
      return this.majorSize() * this.majorStride();
   }

   public boolean isContiguous() {
      return this.majorSize() == this.majorStride();
   }

   public boolean overlaps(final DenseMatrix other) {
      boolean var10000;
      if (this.data() == other.data()) {
         int astart = this.offset();
         int aend = this.offset() + this.breeze$linalg$DenseMatrix$$footprint();
         int bstart = other.offset();
         int bend = other.offset() + other.breeze$linalg$DenseMatrix$$footprint();
         if (scala.package..MODULE$.Range().apply(astart, aend).contains(bstart) || scala.package..MODULE$.Range().apply(astart, aend).contains(bend) || scala.package..MODULE$.Range().apply(bstart, bend).contains(astart) || scala.package..MODULE$.Range().apply(bstart, bend).contains(aend)) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   private void checkIsSpecialized() {
      if (this.data() instanceof double[]) {
         String var10000 = this.getClass().getName();
         String var1 = "breeze.linalg.DenseMatrix";
         if (var10000 == null) {
            if (var1 == null) {
               throw new Exception("...");
            }
         } else if (var10000.equals(var1)) {
            throw new Exception("...");
         }
      }

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

   public double apply$mcD$sp(final int row, final int col) {
      return BoxesRunTime.unboxToDouble(this.apply(row, col));
   }

   public float apply$mcF$sp(final int row, final int col) {
      return BoxesRunTime.unboxToFloat(this.apply(row, col));
   }

   public int apply$mcI$sp(final int row, final int col) {
      return BoxesRunTime.unboxToInt(this.apply(row, col));
   }

   public long apply$mcJ$sp(final int row, final int col) {
      return BoxesRunTime.unboxToLong(this.apply(row, col));
   }

   public void update$mcD$sp(final int row, final int col, final double v) {
      this.update(row, col, BoxesRunTime.boxToDouble(v));
   }

   public void update$mcF$sp(final int row, final int col, final float v) {
      this.update(row, col, BoxesRunTime.boxToFloat(v));
   }

   public void update$mcI$sp(final int row, final int col, final int v) {
      this.update(row, col, BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJ$sp(final int row, final int col, final long v) {
      this.update(row, col, BoxesRunTime.boxToLong(v));
   }

   public double[] toArray$mcD$sp() {
      return (double[])this.toArray();
   }

   public float[] toArray$mcF$sp() {
      return (float[])this.toArray();
   }

   public int[] toArray$mcI$sp() {
      return (int[])this.toArray();
   }

   public long[] toArray$mcJ$sp() {
      return (long[])this.toArray();
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

   public DenseVector flatten$mcD$sp(final View view) {
      return this.flatten(view);
   }

   public DenseVector flatten$mcF$sp(final View view) {
      return this.flatten(view);
   }

   public DenseVector flatten$mcI$sp(final View view) {
      return this.flatten(view);
   }

   public DenseVector flatten$mcJ$sp(final View view) {
      return this.flatten(view);
   }

   public DenseMatrix reshape$mcD$sp(final int rows, final int cols, final View view) {
      return this.reshape(rows, cols, view);
   }

   public DenseMatrix reshape$mcF$sp(final int rows, final int cols, final View view) {
      return this.reshape(rows, cols, view);
   }

   public DenseMatrix reshape$mcI$sp(final int rows, final int cols, final View view) {
      return this.reshape(rows, cols, view);
   }

   public DenseMatrix reshape$mcJ$sp(final int rows, final int cols, final View view) {
      return this.reshape(rows, cols, view);
   }

   public DenseMatrix repr$mcD$sp() {
      return this.repr();
   }

   public DenseMatrix repr$mcF$sp() {
      return this.repr();
   }

   public DenseMatrix repr$mcI$sp() {
      return this.repr();
   }

   public DenseMatrix repr$mcJ$sp() {
      return this.repr();
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

   public double valueAt$mcD$sp(final int row, final int col) {
      return BoxesRunTime.unboxToDouble(this.valueAt(row, col));
   }

   public float valueAt$mcF$sp(final int row, final int col) {
      return BoxesRunTime.unboxToFloat(this.valueAt(row, col));
   }

   public int valueAt$mcI$sp(final int row, final int col) {
      return BoxesRunTime.unboxToInt(this.valueAt(row, col));
   }

   public long valueAt$mcJ$sp(final int row, final int col) {
      return BoxesRunTime.unboxToLong(this.valueAt(row, col));
   }

   public DenseMatrix toDenseMatrix$mcD$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcF$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
      return this.toDenseMatrix(cm, zero);
   }

   public DenseMatrix copy$mcD$sp() {
      return this.copy();
   }

   public DenseMatrix copy$mcF$sp() {
      return this.copy();
   }

   public DenseMatrix copy$mcI$sp() {
      return this.copy();
   }

   public DenseMatrix copy$mcJ$sp() {
      return this.copy();
   }

   public DenseMatrix delete$mcD$sp(final int row, final Axis._0$ axis) {
      return this.delete(row, axis);
   }

   public DenseMatrix delete$mcF$sp(final int row, final Axis._0$ axis) {
      return this.delete(row, axis);
   }

   public DenseMatrix delete$mcI$sp(final int row, final Axis._0$ axis) {
      return this.delete(row, axis);
   }

   public DenseMatrix delete$mcJ$sp(final int row, final Axis._0$ axis) {
      return this.delete(row, axis);
   }

   public DenseMatrix delete$mcD$sp(final int col, final Axis._1$ axis) {
      return this.delete(col, axis);
   }

   public DenseMatrix delete$mcF$sp(final int col, final Axis._1$ axis) {
      return this.delete(col, axis);
   }

   public DenseMatrix delete$mcI$sp(final int col, final Axis._1$ axis) {
      return this.delete(col, axis);
   }

   public DenseMatrix delete$mcJ$sp(final int col, final Axis._1$ axis) {
      return this.delete(col, axis);
   }

   public DenseMatrix delete$mcD$sp(final Seq rows, final Axis._0$ axis) {
      return this.delete(rows, axis);
   }

   public DenseMatrix delete$mcF$sp(final Seq rows, final Axis._0$ axis) {
      return this.delete(rows, axis);
   }

   public DenseMatrix delete$mcI$sp(final Seq rows, final Axis._0$ axis) {
      return this.delete(rows, axis);
   }

   public DenseMatrix delete$mcJ$sp(final Seq rows, final Axis._0$ axis) {
      return this.delete(rows, axis);
   }

   public DenseMatrix delete$mcD$sp(final Seq cols, final Axis._1$ axis) {
      return this.delete(cols, axis);
   }

   public DenseMatrix delete$mcF$sp(final Seq cols, final Axis._1$ axis) {
      return this.delete(cols, axis);
   }

   public DenseMatrix delete$mcI$sp(final Seq cols, final Axis._1$ axis) {
      return this.delete(cols, axis);
   }

   public DenseMatrix delete$mcJ$sp(final Seq cols, final Axis._1$ axis) {
      return this.delete(cols, axis);
   }

   public boolean overlaps$mcD$sp(final DenseMatrix other) {
      return this.overlaps(other);
   }

   public boolean overlaps$mcF$sp(final DenseMatrix other) {
      return this.overlaps(other);
   }

   public boolean overlaps$mcI$sp(final DenseMatrix other) {
      return this.overlaps(other);
   }

   public boolean overlaps$mcJ$sp(final DenseMatrix other) {
      return this.overlaps(other);
   }

   public boolean specInstance$() {
      return false;
   }

   public DenseMatrix(final int rows, final int cols, final Object data, final int offset, final int majorStride, final boolean isTranspose) {
      this.rows = rows;
      this.cols = cols;
      this.data = data;
      this.offset = offset;
      this.majorStride = majorStride;
      this.isTranspose = isTranspose;
      QuasiTensor.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      TensorLike.$init$(this);
      MatrixLike.$init$(this);
      Matrix.$init$(this);
      if (!this.specInstance$()) {
         if (isTranspose && scala.math.package..MODULE$.abs(majorStride) < cols && majorStride != 0) {
            throw new IndexOutOfBoundsException((new StringBuilder(61)).append("MajorStride == ").append(majorStride).append(" is smaller than cols == ").append(cols).append(", which is impossible").toString());
         }

         if (!isTranspose && scala.math.package..MODULE$.abs(majorStride) < rows && majorStride != 0) {
            throw new IndexOutOfBoundsException((new StringBuilder(61)).append("MajorStride == ").append(majorStride).append(" is smaller than rows == ").append(rows).append(", which is impossible").toString());
         }

         if (rows < 0) {
            throw new IndexOutOfBoundsException((new StringBuilder(38)).append("Rows must be larger than zero. It was ").append(rows).toString());
         }

         if (cols < 0) {
            throw new IndexOutOfBoundsException((new StringBuilder(38)).append("Cols must be larger than zero. It was ").append(cols).toString());
         }

         if (offset < 0) {
            throw new IndexOutOfBoundsException((new StringBuilder(40)).append("Offset must be larger than zero. It was ").append(offset).toString());
         }

         if (majorStride > 0) {
            if (scala.runtime.ScalaRunTime..MODULE$.array_length(this.data()) < this.linearIndex(rows - 1, cols - 1)) {
               throw new IndexOutOfBoundsException((new StringBuilder(57)).append("Storage array has size ").append(scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.genericArrayOps(this.data()))).append(" but indices can grow as large as ").append(this.linearIndex(rows - 1, cols - 1)).toString());
            }
         } else if (majorStride < 0) {
            if (scala.runtime.ScalaRunTime..MODULE$.array_length(this.data()) < this.linearIndex(rows - 1, 0)) {
               throw new IndexOutOfBoundsException((new StringBuilder(57)).append("Storage array has size ").append(scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.genericArrayOps(this.data()))).append(" but indices can grow as large as ").append(this.linearIndex(rows - 1, cols - 1)).toString());
            }

            if (this.linearIndex(0, cols - 1) < 0) {
               throw new IndexOutOfBoundsException((new StringBuilder(84)).append("Storage array has negative stride ").append(majorStride).append(" and offset ").append(offset).append(" which can result in negative indices.").toString());
            }
         }

         DenseMatrix$.MODULE$.breeze$linalg$DenseMatrix$$init();
      }

   }

   public DenseMatrix(final int rows, final int cols, final ClassTag man) {
      this(rows, cols, man.newArray(rows * cols), 0, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix(final int rows, final int cols, final Object data, final int offset) {
      this(rows, cols, data, offset, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix(final int rows, final int cols, final Object data) {
      this(rows, cols, data, 0, rows, DenseMatrix$.MODULE$.$lessinit$greater$default$6());
   }

   public DenseMatrix(final int rows, final Object data, final int offset) {
      int left$macro$1 = scala.runtime.ScalaRunTime..MODULE$.array_length(data) % rows;
      int right$macro$2 = 0;
      if (left$macro$1 != 0) {
         throw new AssertionError((new StringBuilder(60)).append("assertion failed: ").append("data.length.%(rows) == 0 (").append(left$macro$1).append(" ").append("!=").append(" ").append(0).append(")").toString());
      } else {
         this(rows, scala.runtime.ScalaRunTime..MODULE$.array_length(data) / rows, data, offset);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class FrobeniusInnerProductDenseMatrixSpace$ {
      public static final FrobeniusInnerProductDenseMatrixSpace$ MODULE$ = new FrobeniusInnerProductDenseMatrixSpace$();

      public MutableFiniteCoordinateField space(final Field evidence$12, final Zero evidence$13, final ClassTag evidence$14) {
         MatrixInnerProduct norms = EntrywiseMatrixNorms$.MODULE$.make(evidence$12, HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.dm_dm_UpdateOp_OpMulScalar(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.canCopy_DM(evidence$14)), HasOps$.MODULE$.canTraverseValues());
         return MutableFiniteCoordinateField$.MODULE$.make(norms.canNorm_Field(evidence$12), norms.canInnerProductNorm_Ring(evidence$12), evidence$12, HasOps$.MODULE$.op_DM_S_OpAdd(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.op_DM_S_OpSub(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.dm_dm_UpdateOp_OpMulScalar(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.canCopy_DM(evidence$14)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.dm_dm_UpdateOp_OpDiv(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.canCopy_DM(evidence$14)), HasOps$.MODULE$.canCopy_DM(evidence$14), HasOps$.MODULE$.opUpdate_DM_S_OpMulScalar(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.opUpdate_DM_S_OpDiv(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.dm_dm_UpdateOp_OpAdd(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.dm_dm_UpdateOp_OpSub(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.opUpdate_DM_S_OpAdd(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.opUpdate_DM_S_OpSub(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.dm_dm_UpdateOp_OpMulScalar(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.dm_dm_UpdateOp_OpDiv(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_DM(), HasOps$.MODULE$.impl_OpMulSet_InPlace_DM_S(), HasOps$.MODULE$.impl_scaleAdd_InPlace_DM_T_DM(evidence$12), DenseMatrix$.MODULE$.canCreateZerosLike(evidence$14, evidence$13), DenseMatrix$.MODULE$.canCreateZeros(evidence$14, evidence$13), HasOps$.MODULE$.canDim_DM(), HasOps$.MODULE$.op_DM_S_OpMulScalar(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.op_DM_S_OpDiv(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.dm_dm_UpdateOp_OpAdd(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.canCopy_DM(evidence$14)), HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.dm_dm_UpdateOp_OpSub(evidence$12, evidence$13, evidence$14), HasOps$.MODULE$.canCopy_DM(evidence$14)), HasOps$.MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(DenseMatrix$.MODULE$.scalarOf(), evidence$12, HasOps$.MODULE$.op_DM_S_OpMulScalar(evidence$12, evidence$13, evidence$14)), scala..less.colon.less..MODULE$.refl(), norms.canInnerProduct(), HasOps$.MODULE$.zipMap_DM(evidence$14), HasOps$.MODULE$.zipMapKV_DM(evidence$14), HasOps$.MODULE$.canTraverseValues(), HasOps$.MODULE$.canMapValues_DM(evidence$14), DenseMatrix$.MODULE$.scalarOf());
      }
   }
}
