package org.apache.spark.ml.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.Params;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple8;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction8;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r-b!\u0002!B\u0001\r[\u0005\"\u00021\u0001\t\u0003\t\u0007\"B2\u0001\t\u0003\"wA\u0002:B\u0011\u0003\u00195O\u0002\u0004A\u0003\"\u00051\t\u001e\u0005\u0006A\u0012!\t\u0001\u001f\u0004\u0005s\u0012\u0001%\u0010\u0003\u0006\u0002\u0010\u0019\u0011)\u001a!C\u0001\u0003#A\u0011\"a\u0005\u0007\u0005#\u0005\u000b\u0011B4\t\u0015\u0005UaA!f\u0001\n\u0003\t\t\u0002C\u0005\u0002\u0018\u0019\u0011\t\u0012)A\u0005O\"Q\u0011\u0011\u0004\u0004\u0003\u0016\u0004%\t!a\u0007\t\u0015\u0005\rbA!E!\u0002\u0013\ti\u0002\u0003\u0006\u0002&\u0019\u0011)\u001a!C\u0001\u0003#A\u0011\"a\n\u0007\u0005#\u0005\u000b\u0011B4\t\u0015\u0005%bA!f\u0001\n\u0003\tY\u0003\u0003\u0006\u0002:\u0019\u0011\t\u0012)A\u0005\u0003[A!\"a\u000f\u0007\u0005+\u0007I\u0011AA\u0016\u0011)\tiD\u0002B\tB\u0003%\u0011Q\u0006\u0005\u000b\u0003\u007f1!Q3A\u0005\u0002\u0005-\u0002BCA!\r\tE\t\u0015!\u0003\u0002.!Q\u00111\t\u0004\u0003\u0016\u0004%\t!!\u0005\t\u0013\u0005\u0015cA!E!\u0002\u00139\u0007B\u00021\u0007\t\u0003\t9\u0005C\u0004\u0002^\u0019!I!a\u0018\t\u000f\u0005=d\u0001\"\u0001\u0002r!9\u0011q\u000f\u0004\u0005\u0002\u0005e\u0004\"CAQ\rE\u0005I\u0011AAR\u0011\u001d\tIL\u0002C\u0005\u0003wC\u0011\"a3\u0007\u0003\u0003%\t!!4\t\u0013\u0005}g!%A\u0005\u0002\u0005\u0005\b\"CAs\rE\u0005I\u0011AAq\u0011%\t9OBI\u0001\n\u0003\tI\u000fC\u0005\u0002n\u001a\t\n\u0011\"\u0001\u0002b\"I\u0011q\u001e\u0004\u0012\u0002\u0013\u0005\u0011\u0011\u001f\u0005\n\u0003k4\u0011\u0013!C\u0001\u0003cD\u0011\"a>\u0007#\u0003%\t!!=\t\u0013\u0005eh!%A\u0005\u0002\u0005\u0005\b\"CA~\r\u0005\u0005I\u0011IA\u007f\u0011%\u0011iABA\u0001\n\u0003\u0011y\u0001C\u0005\u0003\u0018\u0019\t\t\u0011\"\u0001\u0003\u001a!I!q\u0004\u0004\u0002\u0002\u0013\u0005#\u0011\u0005\u0005\n\u0005_1\u0011\u0011!C\u0001\u0005cA\u0011B!\u000e\u0007\u0003\u0003%\tEa\u000e\t\u0013\tmb!!A\u0005B\tu\u0002\"\u0003B \r\u0005\u0005I\u0011\tB!\u0011%\u0011\u0019EBA\u0001\n\u0003\u0012)eB\u0005\u0003J\u0011\t\t\u0011#\u0001\u0003L\u0019A\u0011\u0010BA\u0001\u0012\u0003\u0011i\u0005\u0003\u0004aa\u0011\u0005!Q\r\u0005\n\u0005\u007f\u0001\u0014\u0011!C#\u0005\u0003B\u0011Ba\u001a1\u0003\u0003%\tI!\u001b\t\u0013\tm\u0004'!A\u0005\u0002\nu\u0004\"\u0003BFa\u0005\u0005I\u0011\u0002BG\u0011\u001d\u0011)\n\u0002C\u0001\u0005/C\u0011Ba0\u0005#\u0003%\t!!9\t\u000f\tUE\u0001\"\u0001\u0003B\"9!Q\u0013\u0003\u0005\u0002\tU\u0007b\u0002Bn\t\u0011\u0005!Q\u001c\u0005\n\u0005K$\u0011\u0013!C\u0001\u0003CDqAa:\u0005\t\u0003\u0011I\u000fC\u0004\u0003h\u0012!\tAa?\t\u000f\r\u001dA\u0001\"\u0001\u0004\n!91q\u0001\u0003\u0005\u0002\ru!a\u0005#fM\u0006,H\u000e\u001e)be\u0006l7OU3bI\u0016\u0014(B\u0001\"D\u0003\u0011)H/\u001b7\u000b\u0005\u0011+\u0015AA7m\u0015\t1u)A\u0003ta\u0006\u00148N\u0003\u0002I\u0013\u00061\u0011\r]1dQ\u0016T\u0011AS\u0001\u0004_J<WC\u0001'T'\t\u0001Q\nE\u0002O\u001fFk\u0011!Q\u0005\u0003!\u0006\u0013\u0001\"\u0014'SK\u0006$WM\u001d\t\u0003%Nc\u0001\u0001B\u0003U\u0001\t\u0007aKA\u0001U\u0007\u0001\t\"aV/\u0011\u0005a[V\"A-\u000b\u0003i\u000bQa]2bY\u0006L!\u0001X-\u0003\u000f9{G\u000f[5oOB\u0011\u0001LX\u0005\u0003?f\u00131!\u00118z\u0003\u0019a\u0014N\\5u}Q\t!\rE\u0002O\u0001E\u000bA\u0001\\8bIR\u0011\u0011+\u001a\u0005\u0006M\n\u0001\raZ\u0001\u0005a\u0006$\b\u000e\u0005\u0002i_:\u0011\u0011.\u001c\t\u0003Ufk\u0011a\u001b\u0006\u0003YV\u000ba\u0001\u0010:p_Rt\u0014B\u00018Z\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001/\u001d\u0002\u0007'R\u0014\u0018N\\4\u000b\u00059L\u0016a\u0005#fM\u0006,H\u000e\u001e)be\u0006l7OU3bI\u0016\u0014\bC\u0001(\u0005'\t!Q\u000f\u0005\u0002Ym&\u0011q/\u0017\u0002\u0007\u0003:L(+\u001a4\u0015\u0003M\u0014\u0001\"T3uC\u0012\fG/Y\n\u0005\rU\\h\u0010\u0005\u0002Yy&\u0011Q0\u0017\u0002\b!J|G-^2u!\ry\u0018\u0011\u0002\b\u0005\u0003\u0003\t)AD\u0002k\u0003\u0007I\u0011AW\u0005\u0004\u0003\u000fI\u0016a\u00029bG.\fw-Z\u0005\u0005\u0003\u0017\tiA\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u0002\be\u000b\u0011b\u00197bgNt\u0015-\\3\u0016\u0003\u001d\f!b\u00197bgNt\u0015-\\3!\u0003\r)\u0018\u000eZ\u0001\u0005k&$\u0007%A\u0005uS6,7\u000f^1naV\u0011\u0011Q\u0004\t\u00041\u0006}\u0011bAA\u00113\n!Aj\u001c8h\u0003)!\u0018.\\3ti\u0006l\u0007\u000fI\u0001\rgB\f'o\u001b,feNLwN\\\u0001\u000egB\f'o\u001b,feNLwN\u001c\u0011\u0002\rA\f'/Y7t+\t\ti\u0003\u0005\u0003\u00020\u0005URBAA\u0019\u0015\r\t\u0019$S\u0001\u0007UN|g\u000eN:\n\t\u0005]\u0012\u0011\u0007\u0002\u0007\u0015Z\u000bG.^3\u0002\u000fA\f'/Y7tA\u0005iA-\u001a4bk2$\b+\u0019:b[N\fa\u0002Z3gCVdG\u000fU1sC6\u001c\b%\u0001\u0005nKR\fG-\u0019;b\u0003%iW\r^1eCR\f\u0007%\u0001\u0007nKR\fG-\u0019;b\u0015N|g.A\u0007nKR\fG-\u0019;b\u0015N|g\u000e\t\u000b\u0013\u0003\u0013\ni%a\u0014\u0002R\u0005M\u0013QKA,\u00033\nY\u0006E\u0002\u0002L\u0019i\u0011\u0001\u0002\u0005\u0007\u0003\u001f9\u0002\u0019A4\t\r\u0005Uq\u00031\u0001h\u0011\u001d\tIb\u0006a\u0001\u0003;Aa!!\n\u0018\u0001\u00049\u0007bBA\u0015/\u0001\u0007\u0011Q\u0006\u0005\b\u0003w9\u0002\u0019AA\u0017\u0011\u001d\tyd\u0006a\u0001\u0003[Aa!a\u0011\u0018\u0001\u00049\u0017AE4fiZ\u000bG.^3Ge>l\u0007+\u0019:b[N$B!!\u0019\u0002nA)q0a\u0019\u0002h%!\u0011QMA\u0007\u0005\r\u0019V-\u001d\t\u00071\u0006%t-!\f\n\u0007\u0005-\u0014L\u0001\u0004UkBdWM\r\u0005\b\u0003SA\u0002\u0019AA\u0017\u000359W\r\u001e)be\u0006lg+\u00197vKR!\u0011QFA:\u0011\u0019\t)(\u0007a\u0001O\u0006I\u0001/\u0019:b[:\u000bW.Z\u0001\u0010O\u0016$\u0018I\u001c3TKR\u0004\u0016M]1ngR1\u00111PAA\u0003#\u00032\u0001WA?\u0013\r\ty(\u0017\u0002\u0005+:LG\u000fC\u0004\u0002\u0004j\u0001\r!!\"\u0002\u0011%t7\u000f^1oG\u0016\u0004B!a\"\u0002\u000e6\u0011\u0011\u0011\u0012\u0006\u0004\u0003\u0017\u001b\u0015!\u00029be\u0006l\u0017\u0002BAH\u0003\u0013\u0013a\u0001U1sC6\u001c\b\"CAJ5A\u0005\t\u0019AAK\u0003)\u00198.\u001b9QCJ\fWn\u001d\t\u00061\u0006]\u00151T\u0005\u0004\u00033K&AB(qi&|g\u000e\u0005\u0003\u0000\u0003;;\u0017\u0002BAP\u0003\u001b\u0011A\u0001T5ti\u0006Ir-\u001a;B]\u0012\u001cV\r\u001e)be\u0006l7\u000f\n3fM\u0006,H\u000e\u001e\u00133+\t\t)K\u000b\u0003\u0002\u0016\u0006\u001d6FAAU!\u0011\tY+!.\u000e\u0005\u00055&\u0002BAX\u0003c\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005M\u0016,\u0001\u0006b]:|G/\u0019;j_:LA!a.\u0002.\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0013M,G\u000fU1sC6\u001cH\u0003CA>\u0003{\u000by,!1\t\u000f\u0005\rE\u00041\u0001\u0002\u0006\"9\u00111\u0013\u000fA\u0002\u0005U\u0005bBAb9\u0001\u0007\u0011QY\u0001\nSN$UMZ1vYR\u00042\u0001WAd\u0013\r\tI-\u0017\u0002\b\u0005>|G.Z1o\u0003\u0011\u0019w\u000e]=\u0015%\u0005%\u0013qZAi\u0003'\f).a6\u0002Z\u0006m\u0017Q\u001c\u0005\t\u0003\u001fi\u0002\u0013!a\u0001O\"A\u0011QC\u000f\u0011\u0002\u0003\u0007q\rC\u0005\u0002\u001au\u0001\n\u00111\u0001\u0002\u001e!A\u0011QE\u000f\u0011\u0002\u0003\u0007q\rC\u0005\u0002*u\u0001\n\u00111\u0001\u0002.!I\u00111H\u000f\u0011\u0002\u0003\u0007\u0011Q\u0006\u0005\n\u0003\u007fi\u0002\u0013!a\u0001\u0003[A\u0001\"a\u0011\u001e!\u0003\u0005\raZ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019OK\u0002h\u0003O\u000babY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005-(\u0006BA\u000f\u0003O\u000babY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005M(\u0006BA\u0017\u0003O\u000babY8qs\u0012\"WMZ1vYR$c'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%q\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a@\u0011\t\t\u0005!1B\u0007\u0003\u0005\u0007QAA!\u0002\u0003\b\u0005!A.\u00198h\u0015\t\u0011I!\u0001\u0003kCZ\f\u0017b\u00019\u0003\u0004\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011!\u0011\u0003\t\u00041\nM\u0011b\u0001B\u000b3\n\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019QLa\u0007\t\u0013\tu\u0001&!AA\u0002\tE\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003$A)!Q\u0005B\u0016;6\u0011!q\u0005\u0006\u0004\u0005SI\u0016AC2pY2,7\r^5p]&!!Q\u0006B\u0014\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0015'1\u0007\u0005\t\u0005;Q\u0013\u0011!a\u0001;\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\tyP!\u000f\t\u0013\tu1&!AA\u0002\tE\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\tE\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005}\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002F\n\u001d\u0003\u0002\u0003B\u000f]\u0005\u0005\t\u0019A/\u0002\u00115+G/\u00193bi\u0006\u00042!a\u00131'\u0015\u0001$q\nB.!I\u0011\tFa\u0016hO\u0006uq-!\f\u0002.\u00055r-!\u0013\u000e\u0005\tM#b\u0001B+3\u00069!/\u001e8uS6,\u0017\u0002\u0002B-\u0005'\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c89!\u0011\u0011iFa\u0019\u000e\u0005\t}#\u0002\u0002B1\u0005\u000f\t!![8\n\t\u0005-!q\f\u000b\u0003\u0005\u0017\nQ!\u00199qYf$\"#!\u0013\u0003l\t5$q\u000eB9\u0005g\u0012)Ha\u001e\u0003z!1\u0011qB\u001aA\u0002\u001dDa!!\u00064\u0001\u00049\u0007bBA\rg\u0001\u0007\u0011Q\u0004\u0005\u0007\u0003K\u0019\u0004\u0019A4\t\u000f\u0005%2\u00071\u0001\u0002.!9\u00111H\u001aA\u0002\u00055\u0002bBA g\u0001\u0007\u0011Q\u0006\u0005\u0007\u0003\u0007\u001a\u0004\u0019A4\u0002\u000fUt\u0017\r\u001d9msR!!q\u0010BD!\u0015A\u0016q\u0013BA!=A&1Q4h\u0003;9\u0017QFA\u0017\u0003[9\u0017b\u0001BC3\n1A+\u001e9mKbB\u0011B!#5\u0003\u0003\u0005\r!!\u0013\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u0010B!!\u0011\u0001BI\u0013\u0011\u0011\u0019Ja\u0001\u0003\r=\u0013'.Z2u\u00031aw.\u00193NKR\fG-\u0019;b)!\tIE!'\u0003\u001c\n\u001d\u0006\"\u000247\u0001\u00049\u0007b\u0002BOm\u0001\u0007!qT\u0001\u0003g\u000e\u0004BA!)\u0003$6\tQ)C\u0002\u0003&\u0016\u0013Ab\u00159be.\u001cuN\u001c;fqRD\u0001B!+7!\u0003\u0005\raZ\u0001\u0012Kb\u0004Xm\u0019;fI\u000ec\u0017m]:OC6,\u0007f\u0003\u001c\u0003.\nM&Q\u0017B]\u0005w\u00032\u0001\u0017BX\u0013\r\u0011\t,\u0017\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u0005o\u000b!%^:fA1|\u0017\rZ'fi\u0006$\u0017\r^1!o&$\b\u000eI*qCJ\\7+Z:tS>t\u0017!B:j]\u000e,\u0017E\u0001B_\u0003\u0015!d\u0006\r\u00181\u0003Yaw.\u00193NKR\fG-\u0019;bI\u0011,g-Y;mi\u0012\u001aD\u0003CA%\u0005\u0007\u0014)Ma5\t\u000b\u0019D\u0004\u0019A4\t\r\u0019C\u0004\u0019\u0001Bd!\u0011\u0011IMa4\u000e\u0005\t-'b\u0001Bg\u000b\u0006\u00191/\u001d7\n\t\tE'1\u001a\u0002\r'B\f'o[*fgNLwN\u001c\u0005\u0007\u0005SC\u0004\u0019A4\u0015\r\u0005%#q\u001bBm\u0011\u00151\u0017\b1\u0001h\u0011\u00191\u0015\b1\u0001\u0003H\u0006i\u0001/\u0019:tK6+G/\u00193bi\u0006$b!!\u0013\u0003`\n\r\bB\u0002Bqu\u0001\u0007q-A\u0006nKR\fG-\u0019;b'R\u0014\b\u0002\u0003BUuA\u0005\t\u0019A4\u0002/A\f'o]3NKR\fG-\u0019;bI\u0011,g-Y;mi\u0012\u0012\u0014A\u00057pC\u0012\u0004\u0016M]1ng&s7\u000f^1oG\u0016,BAa;\u0003pR1!Q\u001eBy\u0005g\u00042A\u0015Bx\t\u0015!FH1\u0001W\u0011\u00151G\b1\u0001h\u0011\u001d\u0011i\n\u0010a\u0001\u0005?C3\u0002\u0010BW\u0005g\u00139P!/\u0003<\u0006\u0012!\u0011`\u0001)kN,\u0007\u0005\\8bIB\u000b'/Y7t\u0013:\u001cH/\u00198dK\u0002:\u0018\u000e\u001e5!'B\f'o[*fgNLwN\\\u000b\u0005\u0005{\u001c\t\u0001\u0006\u0004\u0003\u0000\u000e\r1Q\u0001\t\u0004%\u000e\u0005A!\u0002+>\u0005\u00041\u0006\"\u00024>\u0001\u00049\u0007B\u0002$>\u0001\u0004\u00119-\u0001\rm_\u0006$\u0007+\u0019:b[NLen\u001d;b]\u000e,'+Z1eKJ,Baa\u0003\u0004\u0012Q11QBB\n\u0007+\u0001BAT(\u0004\u0010A\u0019!k!\u0005\u0005\u000bQs$\u0019\u0001,\t\u000b\u0019t\u0004\u0019A4\t\u000f\tue\b1\u0001\u0003 \"ZaH!,\u00034\u000ee!\u0011\u0018B^C\t\u0019Y\"\u0001\u0018vg\u0016\u0004Cn\\1e!\u0006\u0014\u0018-\\:J]N$\u0018M\\2f%\u0016\fG-\u001a:!o&$\b\u000eI*qCJ\\7+Z:tS>tW\u0003BB\u0010\u0007K!ba!\t\u0004(\r%\u0002\u0003\u0002(P\u0007G\u00012AUB\u0013\t\u0015!vH1\u0001W\u0011\u00151w\b1\u0001h\u0011\u00191u\b1\u0001\u0003H\u0002"
)
public class DefaultParamsReader extends MLReader {
   public static MLReader loadParamsInstanceReader(final String path, final SparkSession spark) {
      return DefaultParamsReader$.MODULE$.loadParamsInstanceReader(path, spark);
   }

   /** @deprecated */
   public static MLReader loadParamsInstanceReader(final String path, final SparkContext sc) {
      return DefaultParamsReader$.MODULE$.loadParamsInstanceReader(path, sc);
   }

   public static Object loadParamsInstance(final String path, final SparkSession spark) {
      return DefaultParamsReader$.MODULE$.loadParamsInstance(path, spark);
   }

   /** @deprecated */
   public static Object loadParamsInstance(final String path, final SparkContext sc) {
      return DefaultParamsReader$.MODULE$.loadParamsInstance(path, sc);
   }

   public static String parseMetadata$default$2() {
      return DefaultParamsReader$.MODULE$.parseMetadata$default$2();
   }

   public static Metadata parseMetadata(final String metadataStr, final String expectedClassName) {
      return DefaultParamsReader$.MODULE$.parseMetadata(metadataStr, expectedClassName);
   }

   public static Metadata loadMetadata(final String path, final SparkSession spark) {
      return DefaultParamsReader$.MODULE$.loadMetadata(path, spark);
   }

   public static Metadata loadMetadata(final String path, final SparkSession spark, final String expectedClassName) {
      return DefaultParamsReader$.MODULE$.loadMetadata(path, spark, expectedClassName);
   }

   public static String loadMetadata$default$3() {
      return DefaultParamsReader$.MODULE$.loadMetadata$default$3();
   }

   /** @deprecated */
   public static Metadata loadMetadata(final String path, final SparkContext sc, final String expectedClassName) {
      return DefaultParamsReader$.MODULE$.loadMetadata(path, sc, expectedClassName);
   }

   public Object load(final String path) {
      Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession());
      Class cls = MLAllowListedLoader$.MODULE$.load(metadata.className());
      Params instance = (Params)cls.getConstructor(String.class).newInstance(metadata.uid());
      metadata.getAndSetParams(instance, metadata.getAndSetParams$default$2());
      return instance;
   }

   public static class Metadata implements Product, Serializable {
      private final String className;
      private final String uid;
      private final long timestamp;
      private final String sparkVersion;
      private final JValue params;
      private final JValue defaultParams;
      private final JValue metadata;
      private final String metadataJson;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String className() {
         return this.className;
      }

      public String uid() {
         return this.uid;
      }

      public long timestamp() {
         return this.timestamp;
      }

      public String sparkVersion() {
         return this.sparkVersion;
      }

      public JValue params() {
         return this.params;
      }

      public JValue defaultParams() {
         return this.defaultParams;
      }

      public JValue metadata() {
         return this.metadata;
      }

      public String metadataJson() {
         return this.metadataJson;
      }

      private Seq getValueFromParams(final JValue params) {
         if (params instanceof JObject var4) {
            List pairs = var4.obj();
            return pairs;
         } else {
            throw new IllegalArgumentException("Cannot recognize JSON metadata: " + this.metadataJson() + ".");
         }
      }

      public JValue getParamValue(final String paramName) {
         DefaultFormats format = .MODULE$;
         ObjectRef pairs = ObjectRef.create(this.getValueFromParams(this.params()));
         ObjectRef foundPairs = ObjectRef.create((Seq)((Seq)pairs.elem).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getParamValue$1(paramName, x0$1))));
         if (((Seq)foundPairs.elem).length() == 0) {
            pairs.elem = this.getValueFromParams(this.defaultParams());
            foundPairs.elem = (Seq)((Seq)pairs.elem).filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$getParamValue$2(paramName, x0$2)));
         }

         scala.Predef..MODULE$.assert(((Seq)foundPairs.elem).length() == 1, () -> "Expected one instance of Param '" + paramName + "' but found " + ((Seq)foundPairs.elem).length() + " in JSON Params: " + ((IterableOnceOps)((Seq)pairs.elem).map((x$3) -> x$3.toString())).mkString(", "));
         return (JValue)((IterableOps)((Seq)foundPairs.elem).map((x$4) -> (JValue)x$4._2())).head();
      }

      public void getAndSetParams(final Params instance, final Option skipParams) {
         this.setParams(instance, skipParams, false);
         Tuple2 var5 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(this.sparkVersion());
         if (var5 == null) {
            throw new MatchError(var5);
         } else {
            int major = var5._1$mcI$sp();
            int minor = var5._2$mcI$sp();
            Tuple2.mcII.sp var4 = new Tuple2.mcII.sp(major, minor);
            int major = ((Tuple2)var4)._1$mcI$sp();
            int minor = ((Tuple2)var4)._2$mcI$sp();
            if (major > 2 || major == 2 && minor >= 4) {
               this.setParams(instance, skipParams, true);
            }
         }
      }

      public Option getAndSetParams$default$2() {
         return scala.None..MODULE$;
      }

      private void setParams(final Params instance, final Option skipParams, final boolean isDefault) {
         DefaultFormats format = .MODULE$;
         JValue paramsToSet = isDefault ? this.defaultParams() : this.params();
         if (paramsToSet instanceof JObject var8) {
            List pairs = var8.obj();
            pairs.foreach((x0$1) -> {
               if (x0$1 == null) {
                  throw new MatchError(x0$1);
               } else {
                  String paramName;
                  JValue jsonValue;
                  label24: {
                     paramName = (String)x0$1._1();
                     jsonValue = (JValue)x0$1._2();
                     None var8 = scala.None..MODULE$;
                     if (skipParams == null) {
                        if (var8 == null) {
                           break label24;
                        }
                     } else if (skipParams.equals(var8)) {
                        break label24;
                     }

                     if (((List)skipParams.get()).contains(paramName)) {
                        return BoxedUnit.UNIT;
                     }
                  }

                  Param param = instance.getParam(paramName);
                  Object value = param.jsonDecode(org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jsonValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())));
                  return isDefault ? instance.setDefault(param, value) : instance.set(param, value);
               }
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new IllegalArgumentException("Cannot recognize JSON metadata: " + this.metadataJson() + ".");
         }
      }

      public Metadata copy(final String className, final String uid, final long timestamp, final String sparkVersion, final JValue params, final JValue defaultParams, final JValue metadata, final String metadataJson) {
         return new Metadata(className, uid, timestamp, sparkVersion, params, defaultParams, metadata, metadataJson);
      }

      public String copy$default$1() {
         return this.className();
      }

      public String copy$default$2() {
         return this.uid();
      }

      public long copy$default$3() {
         return this.timestamp();
      }

      public String copy$default$4() {
         return this.sparkVersion();
      }

      public JValue copy$default$5() {
         return this.params();
      }

      public JValue copy$default$6() {
         return this.defaultParams();
      }

      public JValue copy$default$7() {
         return this.metadata();
      }

      public String copy$default$8() {
         return this.metadataJson();
      }

      public String productPrefix() {
         return "Metadata";
      }

      public int productArity() {
         return 8;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.className();
            }
            case 1 -> {
               return this.uid();
            }
            case 2 -> {
               return BoxesRunTime.boxToLong(this.timestamp());
            }
            case 3 -> {
               return this.sparkVersion();
            }
            case 4 -> {
               return this.params();
            }
            case 5 -> {
               return this.defaultParams();
            }
            case 6 -> {
               return this.metadata();
            }
            case 7 -> {
               return this.metadataJson();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Metadata;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "className";
            }
            case 1 -> {
               return "uid";
            }
            case 2 -> {
               return "timestamp";
            }
            case 3 -> {
               return "sparkVersion";
            }
            case 4 -> {
               return "params";
            }
            case 5 -> {
               return "defaultParams";
            }
            case 6 -> {
               return "metadata";
            }
            case 7 -> {
               return "metadataJson";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.className()));
         var1 = Statics.mix(var1, Statics.anyHash(this.uid()));
         var1 = Statics.mix(var1, Statics.longHash(this.timestamp()));
         var1 = Statics.mix(var1, Statics.anyHash(this.sparkVersion()));
         var1 = Statics.mix(var1, Statics.anyHash(this.params()));
         var1 = Statics.mix(var1, Statics.anyHash(this.defaultParams()));
         var1 = Statics.mix(var1, Statics.anyHash(this.metadata()));
         var1 = Statics.mix(var1, Statics.anyHash(this.metadataJson()));
         return Statics.finalizeHash(var1, 8);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var18;
         if (this != x$1) {
            label99: {
               if (x$1 instanceof Metadata) {
                  Metadata var4 = (Metadata)x$1;
                  if (this.timestamp() == var4.timestamp()) {
                     label92: {
                        String var10000 = this.className();
                        String var5 = var4.className();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label92;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label92;
                        }

                        var10000 = this.uid();
                        String var6 = var4.uid();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label92;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label92;
                        }

                        var10000 = this.sparkVersion();
                        String var7 = var4.sparkVersion();
                        if (var10000 == null) {
                           if (var7 != null) {
                              break label92;
                           }
                        } else if (!var10000.equals(var7)) {
                           break label92;
                        }

                        JValue var14 = this.params();
                        JValue var8 = var4.params();
                        if (var14 == null) {
                           if (var8 != null) {
                              break label92;
                           }
                        } else if (!var14.equals(var8)) {
                           break label92;
                        }

                        var14 = this.defaultParams();
                        JValue var9 = var4.defaultParams();
                        if (var14 == null) {
                           if (var9 != null) {
                              break label92;
                           }
                        } else if (!var14.equals(var9)) {
                           break label92;
                        }

                        var14 = this.metadata();
                        JValue var10 = var4.metadata();
                        if (var14 == null) {
                           if (var10 != null) {
                              break label92;
                           }
                        } else if (!var14.equals(var10)) {
                           break label92;
                        }

                        String var17 = this.metadataJson();
                        String var11 = var4.metadataJson();
                        if (var17 == null) {
                           if (var11 != null) {
                              break label92;
                           }
                        } else if (!var17.equals(var11)) {
                           break label92;
                        }

                        if (var4.canEqual(this)) {
                           break label99;
                        }
                     }
                  }
               }

               var18 = false;
               return var18;
            }
         }

         var18 = true;
         return var18;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$getParamValue$1(final String paramName$1, final Tuple2 x0$1) {
         if (x0$1 == null) {
            throw new MatchError(x0$1);
         } else {
            boolean var10000;
            label30: {
               String pName = (String)x0$1._1();
               if (pName == null) {
                  if (paramName$1 == null) {
                     break label30;
                  }
               } else if (pName.equals(paramName$1)) {
                  break label30;
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$getParamValue$2(final String paramName$1, final Tuple2 x0$2) {
         if (x0$2 == null) {
            throw new MatchError(x0$2);
         } else {
            boolean var10000;
            label30: {
               String pName = (String)x0$2._1();
               if (pName == null) {
                  if (paramName$1 == null) {
                     break label30;
                  }
               } else if (pName.equals(paramName$1)) {
                  break label30;
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      public Metadata(final String className, final String uid, final long timestamp, final String sparkVersion, final JValue params, final JValue defaultParams, final JValue metadata, final String metadataJson) {
         this.className = className;
         this.uid = uid;
         this.timestamp = timestamp;
         this.sparkVersion = sparkVersion;
         this.params = params;
         this.defaultParams = defaultParams;
         this.metadata = metadata;
         this.metadataJson = metadataJson;
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Metadata$ extends AbstractFunction8 implements Serializable {
      public static final Metadata$ MODULE$ = new Metadata$();

      public final String toString() {
         return "Metadata";
      }

      public Metadata apply(final String className, final String uid, final long timestamp, final String sparkVersion, final JValue params, final JValue defaultParams, final JValue metadata, final String metadataJson) {
         return new Metadata(className, uid, timestamp, sparkVersion, params, defaultParams, metadata, metadataJson);
      }

      public Option unapply(final Metadata x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple8(x$0.className(), x$0.uid(), BoxesRunTime.boxToLong(x$0.timestamp()), x$0.sparkVersion(), x$0.params(), x$0.defaultParams(), x$0.metadata(), x$0.metadataJson())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Metadata$.class);
      }
   }
}
