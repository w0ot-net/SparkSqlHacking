package org.apache.spark.storage;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.MapOps;
import scala.math.Numeric.LongIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005a!\u0002/^\u0001}+\u0007\u0002\u00037\u0001\u0005\u000b\u0007I\u0011\u00018\t\u0011M\u0004!\u0011!Q\u0001\n=D\u0001\u0002\u001e\u0001\u0003\u0006\u0004%\t!\u001e\u0005\ts\u0002\u0011\t\u0011)A\u0005m\"A!\u0010\u0001BC\u0002\u0013\u00051\u0010\u0003\u0005\u0000\u0001\t\u0005\t\u0015!\u0003}\u0011%\t\t\u0001\u0001BC\u0002\u0013\u00051\u0010C\u0005\u0002\u0004\u0001\u0011\t\u0011)A\u0005y\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001\"CA\n\u0001\t\u0007I\u0011BA\u000b\u0011!\ty\u0004\u0001Q\u0001\n\u0005]\u0001\"CA!\u0001\t\u0007I\u0011BA\"\u0011!\t9\u0005\u0001Q\u0001\n\u0005\u0015cABA%\u0001\u0011\u000bY\u0005C\u0005\u0002l9\u0011)\u001a!C\u0001k\"I\u0011Q\u000e\b\u0003\u0012\u0003\u0006IA\u001e\u0005\n\u0003_r!Q3A\u0005\u0002UD\u0011\"!\u001d\u000f\u0005#\u0005\u000b\u0011\u0002<\t\u0015\u0005MdB!f\u0001\n\u0003\t)\b\u0003\u0006\u0002~9\u0011\t\u0012)A\u0005\u0003oBq!!\u0002\u000f\t\u0003\ty\bC\u0005\u0002\f:\t\t\u0011\"\u0001\u0002\u000e\"I\u0011Q\u0013\b\u0012\u0002\u0013\u0005\u0011q\u0013\u0005\n\u0003[s\u0011\u0013!C\u0001\u0003/C\u0011\"a,\u000f#\u0003%\t!!-\t\u0013\u0005Uf\"!A\u0005B\u0005]\u0006\"CAe\u001d\u0005\u0005I\u0011AAf\u0011%\tiMDA\u0001\n\u0003\ty\rC\u0005\u0002\\:\t\t\u0011\"\u0011\u0002^\"I\u0011q\u001d\b\u0002\u0002\u0013\u0005\u0011\u0011\u001e\u0005\n\u0003gt\u0011\u0011!C!\u0003kD\u0011\"!?\u000f\u0003\u0003%\t%a?\t\u0013\u0005uh\"!A\u0005B\u0005}\b\"\u0003B\u0001\u001d\u0005\u0005I\u0011\tB\u0002\u000f%\u00119\u0001AA\u0001\u0012\u0013\u0011IAB\u0005\u0002J\u0001\t\t\u0011#\u0003\u0003\f!9\u0011Q\u0001\u0013\u0005\u0002\t\r\u0002\"CA\u007fI\u0005\u0005IQIA\u0000\u0011%\u0011)\u0003JA\u0001\n\u0003\u00139\u0003C\u0005\u00030\u0011\n\t\u0011\"!\u00032!I!q\b\u0001C\u0002\u0013%!\u0011\t\u0005\t\u0005\u000b\u0002\u0001\u0015!\u0003\u0003D\u00191!q\t\u0001E\u0005\u0013B\u0011Ba\u0013,\u0005#\u0007I\u0011A;\t\u0015\t53F!a\u0001\n\u0003\u0011y\u0005C\u0005\u0003Z-\u0012\t\u0012)Q\u0005m\"I!1L\u0016\u0003\u0012\u0004%\t!\u001e\u0005\u000b\u0005;Z#\u00111A\u0005\u0002\t}\u0003\"\u0003B2W\tE\t\u0015)\u0003w\u0011%\tyg\u000bBI\u0002\u0013\u0005Q\u000f\u0003\u0006\u0003f-\u0012\t\u0019!C\u0001\u0005OB\u0011\"!\u001d,\u0005#\u0005\u000b\u0015\u0002<\t\u000f\u0005\u00151\u0006\"\u0001\u0003l!I\u00111R\u0016\u0002\u0002\u0013\u0005!Q\u000f\u0005\n\u0003+[\u0013\u0013!C\u0001\u0003/C\u0011\"!,,#\u0003%\t!a&\t\u0013\u0005=6&%A\u0005\u0002\u0005]\u0005\"CA[W\u0005\u0005I\u0011IA\\\u0011%\tImKA\u0001\n\u0003\tY\rC\u0005\u0002N.\n\t\u0011\"\u0001\u0003~!I\u00111\\\u0016\u0002\u0002\u0013\u0005\u0013Q\u001c\u0005\n\u0003O\\\u0013\u0011!C\u0001\u0005\u0003C\u0011\"a=,\u0003\u0003%\tE!\"\t\u0013\u0005e8&!A\u0005B\u0005m\b\"CA\u007fW\u0005\u0005I\u0011IA\u0000\u0011%\u0011\taKA\u0001\n\u0003\u0012IiB\u0005\u0003\u000e\u0002\t\t\u0011#\u0003\u0003\u0010\u001aI!q\t\u0001\u0002\u0002#%!\u0011\u0013\u0005\b\u0003\u000b!E\u0011\u0001BK\u0011%\ti\u0010RA\u0001\n\u000b\ny\u0010C\u0005\u0003&\u0011\u000b\t\u0011\"!\u0003\u0018\"I!q\u0006#\u0002\u0002\u0013\u0005%q\u0014\u0005\n\u0005O\u0003!\u0019!C\u0005\u0005SC\u0001Ba+\u0001A\u0003%!Q\u000e\u0005\b\u0003\u000b\u0001A\u0011\u0001BW\u0011\u001d\u0011\t\r\u0001C\u0001\u0005\u0007DqA!2\u0001\t\u0003\u0011\u0019\r\u0003\u0005\u0003H\u0002!\ta\u0018Be\u0011\u001d\u0011\u0019\u000e\u0001C\u0001\u0005+DaAa7\u0001\t\u0003)\bB\u0002Bo\u0001\u0011\u0005Q\u000f\u0003\u0004\u0003`\u0002!\t!\u001e\u0005\u0007\u0005C\u0004A\u0011A>\t\r\t\r\b\u0001\"\u0001|\u0011\u0019\u0011)\u000f\u0001C\u0001w\"1!q\u001d\u0001\u0005\u0002mDaA!;\u0001\t\u0003Y\bB\u0002Bv\u0001\u0011\u00051\u0010\u0003\u0004\u0003n\u0002!\t!\u001e\u0005\b\u0005_\u0004A\u0011\u0001By\u0011\u001d\u00119\u0010\u0001C\u0005\u0005s\u0014Qb\u0015;pe\u0006<Wm\u0015;biV\u001c(B\u00010`\u0003\u001d\u0019Ho\u001c:bO\u0016T!\u0001Y1\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001c\u0017AB1qC\u000eDWMC\u0001e\u0003\ry'oZ\n\u0003\u0001\u0019\u0004\"a\u001a6\u000e\u0003!T\u0011![\u0001\u0006g\u000e\fG.Y\u0005\u0003W\"\u0014a!\u00118z%\u00164\u0017A\u00042m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\u0002\u0001+\u0005y\u0007C\u00019r\u001b\u0005i\u0016B\u0001:^\u00059\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012\fqB\u00197pG.l\u0015M\\1hKJLE\rI\u0001\n[\u0006DX*Z7pef,\u0012A\u001e\t\u0003O^L!\u0001\u001f5\u0003\t1{gnZ\u0001\u000b[\u0006DX*Z7pef\u0004\u0013\u0001D7bq>s\u0007*Z1q\u001b\u0016lW#\u0001?\u0011\u0007\u001dlh/\u0003\u0002\u007fQ\n1q\n\u001d;j_:\fQ\"\\1y\u001f:DU-\u00199NK6\u0004\u0013!D7bq>3g\rS3ba6+W.\u0001\bnCb|eM\u001a%fCBlU-\u001c\u0011\u0002\rqJg.\u001b;?))\tI!a\u0003\u0002\u000e\u0005=\u0011\u0011\u0003\t\u0003a\u0002AQ\u0001\\\u0005A\u0002=DQ\u0001^\u0005A\u0002YDQA_\u0005A\u0002qDa!!\u0001\n\u0001\u0004a\u0018AC0sI\u0012\u0014En\\2lgV\u0011\u0011q\u0003\t\t\u00033\t\u0019#a\n\u0002.5\u0011\u00111\u0004\u0006\u0005\u0003;\ty\"A\u0004nkR\f'\r\\3\u000b\u0007\u0005\u0005\u0002.\u0001\u0006d_2dWm\u0019;j_:LA!!\n\u0002\u001c\t9\u0001*Y:i\u001b\u0006\u0004\bcA4\u0002*%\u0019\u00111\u00065\u0003\u0007%sG\u000f\u0005\u0005\u0002\u001a\u0005=\u00121GA\u001d\u0013\u0011\t\t$a\u0007\u0003\u00075\u000b\u0007\u000fE\u0002q\u0003kI1!a\u000e^\u0005\u001d\u0011En\\2l\u0013\u0012\u00042\u0001]A\u001e\u0013\r\ti$\u0018\u0002\f\u00052|7m[*uCR,8/A\u0006`e\u0012$'\t\\8dWN\u0004\u0013!D0o_:\u0014F\r\u001a\"m_\u000e\\7/\u0006\u0002\u0002FAA\u0011\u0011DA\u0012\u0003g\tI$\u0001\b`]>t'\u000b\u001a3CY>\u001c7n\u001d\u0011\u0003\u001dI#Gm\u0015;pe\u0006<W-\u00138g_N1aBZA'\u0003'\u00022aZA(\u0013\r\t\t\u0006\u001b\u0002\b!J|G-^2u!\u0011\t)&!\u001a\u000f\t\u0005]\u0013\u0011\r\b\u0005\u00033\ny&\u0004\u0002\u0002\\)\u0019\u0011QL7\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0017bAA2Q\u00069\u0001/Y2lC\u001e,\u0017\u0002BA4\u0003S\u0012AbU3sS\u0006d\u0017N_1cY\u0016T1!a\u0019i\u0003-iW-\\8ssV\u001b\u0018mZ3\u0002\u00195,Wn\u001c:z+N\fw-\u001a\u0011\u0002\u0013\u0011L7o[+tC\u001e,\u0017A\u00033jg.,6/Y4fA\u0005)A.\u001a<fYV\u0011\u0011q\u000f\t\u0004a\u0006e\u0014bAA>;\na1\u000b^8sC\u001e,G*\u001a<fY\u00061A.\u001a<fY\u0002\"\u0002\"!!\u0002\u0006\u0006\u001d\u0015\u0011\u0012\t\u0004\u0003\u0007sQ\"\u0001\u0001\t\r\u0005-T\u00031\u0001w\u0011\u0019\ty'\u0006a\u0001m\"9\u00111O\u000bA\u0002\u0005]\u0014\u0001B2paf$\u0002\"!!\u0002\u0010\u0006E\u00151\u0013\u0005\t\u0003W2\u0002\u0013!a\u0001m\"A\u0011q\u000e\f\u0011\u0002\u0003\u0007a\u000fC\u0005\u0002tY\u0001\n\u00111\u0001\u0002x\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAAMU\r1\u00181T\u0016\u0003\u0003;\u0003B!a(\u0002*6\u0011\u0011\u0011\u0015\u0006\u0005\u0003G\u000b)+A\u0005v]\u000eDWmY6fI*\u0019\u0011q\u00155\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002,\u0006\u0005&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003gSC!a\u001e\u0002\u001c\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!/\u0011\t\u0005m\u0016QY\u0007\u0003\u0003{SA!a0\u0002B\u0006!A.\u00198h\u0015\t\t\u0019-\u0001\u0003kCZ\f\u0017\u0002BAd\u0003{\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0014\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!5\u0002XB\u0019q-a5\n\u0007\u0005U\u0007NA\u0002B]fD\u0011\"!7\u001d\u0003\u0003\u0005\r!a\n\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u000e\u0005\u0004\u0002b\u0006\r\u0018\u0011[\u0007\u0003\u0003?IA!!:\u0002 \tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY/!=\u0011\u0007\u001d\fi/C\u0002\u0002p\"\u0014qAQ8pY\u0016\fg\u000eC\u0005\u0002Zz\t\t\u00111\u0001\u0002R\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\tI,a>\t\u0013\u0005ew$!AA\u0002\u0005\u001d\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u001d\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005e\u0016AB3rk\u0006d7\u000f\u0006\u0003\u0002l\n\u0015\u0001\"CAmE\u0005\u0005\t\u0019AAi\u00039\u0011F\rZ*u_J\fw-Z%oM>\u00042!a!%'\u0015!#Q\u0002B\r!)\u0011yA!\u0006wm\u0006]\u0014\u0011Q\u0007\u0003\u0005#Q1Aa\u0005i\u0003\u001d\u0011XO\u001c;j[\u0016LAAa\u0006\u0003\u0012\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\tm!\u0011E\u0007\u0003\u0005;QAAa\b\u0002B\u0006\u0011\u0011n\\\u0005\u0005\u0003O\u0012i\u0002\u0006\u0002\u0003\n\u0005)\u0011\r\u001d9msRA\u0011\u0011\u0011B\u0015\u0005W\u0011i\u0003\u0003\u0004\u0002l\u001d\u0002\rA\u001e\u0005\u0007\u0003_:\u0003\u0019\u0001<\t\u000f\u0005Mt\u00051\u0001\u0002x\u00059QO\\1qa2LH\u0003\u0002B\u001a\u0005w\u0001BaZ?\u00036A9qMa\u000ewm\u0006]\u0014b\u0001B\u001dQ\n1A+\u001e9mKNB\u0011B!\u0010)\u0003\u0003\u0005\r!!!\u0002\u0007a$\u0003'A\b`e\u0012$7\u000b^8sC\u001e,\u0017J\u001c4p+\t\u0011\u0019\u0005\u0005\u0005\u0002\u001a\u0005\r\u0012qEAA\u0003Ay&\u000f\u001a3Ti>\u0014\u0018mZ3J]\u001a|\u0007EA\tO_:\u0014F\rZ*u_J\fw-Z%oM>\u001cba\u000b4\u0002N\u0005M\u0013aC8o\u0011\u0016\f\u0007/V:bO\u0016\fqb\u001c8IK\u0006\u0004Xk]1hK~#S-\u001d\u000b\u0005\u0005#\u00129\u0006E\u0002h\u0005'J1A!\u0016i\u0005\u0011)f.\u001b;\t\u0011\u0005eW&!AA\u0002Y\fAb\u001c8IK\u0006\u0004Xk]1hK\u0002\nAb\u001c4g\u0011\u0016\f\u0007/V:bO\u0016\f\u0001c\u001c4g\u0011\u0016\f\u0007/V:bO\u0016|F%Z9\u0015\t\tE#\u0011\r\u0005\t\u00033\u0004\u0014\u0011!a\u0001m\u0006iqN\u001a4IK\u0006\u0004Xk]1hK\u0002\nQ\u0002Z5tWV\u001b\u0018mZ3`I\u0015\fH\u0003\u0002B)\u0005SB\u0001\"!74\u0003\u0003\u0005\rA\u001e\u000b\t\u0005[\u0012yG!\u001d\u0003tA\u0019\u00111Q\u0016\t\r\t-S\u00071\u0001w\u0011\u0019\u0011Y&\u000ea\u0001m\"1\u0011qN\u001bA\u0002Y$\u0002B!\u001c\u0003x\te$1\u0010\u0005\t\u0005\u00172\u0004\u0013!a\u0001m\"A!1\f\u001c\u0011\u0002\u0003\u0007a\u000f\u0003\u0005\u0002pY\u0002\n\u00111\u0001w)\u0011\t\tNa \t\u0013\u0005eG(!AA\u0002\u0005\u001dB\u0003BAv\u0005\u0007C\u0011\"!7?\u0003\u0003\u0005\r!!5\u0015\t\u0005e&q\u0011\u0005\n\u00033|\u0014\u0011!a\u0001\u0003O!B!a;\u0003\f\"I\u0011\u0011\u001c\"\u0002\u0002\u0003\u0007\u0011\u0011[\u0001\u0012\u001d>t'\u000b\u001a3Ti>\u0014\u0018mZ3J]\u001a|\u0007cAAB\tN)AIa%\u0003\u001aAI!q\u0002B\u000bmZ4(Q\u000e\u000b\u0003\u0005\u001f#\u0002B!\u001c\u0003\u001a\nm%Q\u0014\u0005\u0007\u0005\u0017:\u0005\u0019\u0001<\t\r\tms\t1\u0001w\u0011\u0019\tyg\u0012a\u0001mR!!\u0011\u0015BS!\u00119WPa)\u0011\r\u001d\u00149D\u001e<w\u0011%\u0011i\u0004SA\u0001\u0002\u0004\u0011i'\u0001\n`]>t'\u000b\u001a3Ti>\u0014\u0018mZ3J]\u001a|WC\u0001B7\u0003Myfn\u001c8SI\u0012\u001cFo\u001c:bO\u0016LeNZ8!)1\tIAa,\u00034\nU&q\u0017B]\u0011\u0019\u0011\tl\u0013a\u0001_\u0006!!-\\5e\u0011\u0015!8\n1\u0001w\u0011\u0015Q8\n1\u0001}\u0011\u0019\t\ta\u0013a\u0001y\"9!1X&A\u0002\tu\u0016!D5oSRL\u0017\r\u001c\"m_\u000e\\7\u000f\u0005\u0005\u0002b\n}\u00161GA\u001d\u0013\u0011\t\t$a\b\u0002\r\tdwnY6t+\t\u0011i,A\u0005sI\u0012\u0014En\\2lg\u0006A\u0011\r\u001a3CY>\u001c7\u000e\u0006\u0004\u0003R\t-'q\u001a\u0005\b\u0005\u001bt\u0005\u0019AA\u001a\u0003\u001d\u0011Gn\\2l\u0013\u0012DqA!5O\u0001\u0004\tI$A\u0006cY>\u001c7n\u0015;biV\u001c\u0018\u0001C4fi\ncwnY6\u0015\t\t]'\u0011\u001c\t\u0005Ov\fI\u0004C\u0004\u0003N>\u0003\r!a\r\u0002\r5\f\u00070T3n\u00031iW-\u001c*f[\u0006Lg.\u001b8h\u0003\u001diW-\\+tK\u0012\f!c\u001c8IK\u0006\u0004X*Z7SK6\f\u0017N\\5oO\u0006\u0019rN\u001a4IK\u0006\u0004X*Z7SK6\f\u0017N\\5oO\u0006iqN\u001c%fCBlU-\\+tK\u0012\fab\u001c4g\u0011\u0016\f\u0007/T3n+N,G-A\bp]\"+\u0017\r]\"bG\",7+\u001b>f\u0003AygM\u001a%fCB\u001c\u0015m\u00195f'&TX-\u0001\u0005eSN\\Wk]3e\u00035!\u0017n]6Vg\u0016$')\u001f*eIR\u0019aOa=\t\u000f\tU(\f1\u0001\u0002(\u0005)!\u000f\u001a3JI\u0006\tR\u000f\u001d3bi\u0016\u001cFo\u001c:bO\u0016LeNZ8\u0015\r\tE#1 B\u007f\u0011\u001d\u0011im\u0017a\u0001\u0003gAqAa@\\\u0001\u0004\tI$\u0001\boK^\u0014En\\2l'R\fG/^:"
)
public class StorageStatus {
   private volatile RddStorageInfo$ RddStorageInfo$module;
   private volatile NonRddStorageInfo$ NonRddStorageInfo$module;
   private final BlockManagerId blockManagerId;
   private final long maxMemory;
   private final Option maxOnHeapMem;
   private final Option maxOffHeapMem;
   private final HashMap _rddBlocks;
   private final HashMap _nonRddBlocks;
   private final HashMap _rddStorageInfo;
   private final NonRddStorageInfo _nonRddStorageInfo;

   private RddStorageInfo$ RddStorageInfo() {
      if (this.RddStorageInfo$module == null) {
         this.RddStorageInfo$lzycompute$1();
      }

      return this.RddStorageInfo$module;
   }

   private NonRddStorageInfo$ NonRddStorageInfo() {
      if (this.NonRddStorageInfo$module == null) {
         this.NonRddStorageInfo$lzycompute$1();
      }

      return this.NonRddStorageInfo$module;
   }

   public BlockManagerId blockManagerId() {
      return this.blockManagerId;
   }

   public long maxMemory() {
      return this.maxMemory;
   }

   public Option maxOnHeapMem() {
      return this.maxOnHeapMem;
   }

   public Option maxOffHeapMem() {
      return this.maxOffHeapMem;
   }

   private HashMap _rddBlocks() {
      return this._rddBlocks;
   }

   private HashMap _nonRddBlocks() {
      return this._nonRddBlocks;
   }

   private HashMap _rddStorageInfo() {
      return this._rddStorageInfo;
   }

   private NonRddStorageInfo _nonRddStorageInfo() {
      return this._nonRddStorageInfo;
   }

   public Map blocks() {
      return (Map)this._nonRddBlocks().$plus$plus(this.rddBlocks());
   }

   public Map rddBlocks() {
      return (Map)this._rddBlocks().flatMap((x0$1) -> {
         if (x0$1 != null) {
            scala.collection.mutable.Map blocks = (scala.collection.mutable.Map)x0$1._2();
            return blocks;
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public void addBlock(final BlockId blockId, final BlockStatus blockStatus) {
      this.updateStorageInfo(blockId, blockStatus);
      if (blockId instanceof RDDBlockId var5) {
         int rddId = var5.rddId();
         ((MapOps)this._rddBlocks().getOrElseUpdate(BoxesRunTime.boxToInteger(rddId), () -> new HashMap())).update(blockId, blockStatus);
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         this._nonRddBlocks().update(blockId, blockStatus);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public Option getBlock(final BlockId blockId) {
      if (blockId instanceof RDDBlockId var4) {
         int rddId = var4.rddId();
         return this._rddBlocks().get(BoxesRunTime.boxToInteger(rddId)).flatMap((x$1) -> x$1.get(blockId));
      } else {
         return this._nonRddBlocks().get(blockId);
      }
   }

   public long maxMem() {
      return this.maxMemory();
   }

   public long memRemaining() {
      return this.maxMem() - this.memUsed();
   }

   public long memUsed() {
      return BoxesRunTime.unboxToLong(this.onHeapMemUsed().getOrElse((JFunction0.mcJ.sp)() -> 0L)) + BoxesRunTime.unboxToLong(this.offHeapMemUsed().getOrElse((JFunction0.mcJ.sp)() -> 0L));
   }

   public Option onHeapMemRemaining() {
      return this.maxOnHeapMem().flatMap((m) -> $anonfun$onHeapMemRemaining$1(this, BoxesRunTime.unboxToLong(m)));
   }

   public Option offHeapMemRemaining() {
      return this.maxOffHeapMem().flatMap((m) -> $anonfun$offHeapMemRemaining$1(this, BoxesRunTime.unboxToLong(m)));
   }

   public Option onHeapMemUsed() {
      return this.onHeapCacheSize().map((JFunction1.mcJJ.sp)(x$2) -> x$2 + this._nonRddStorageInfo().onHeapUsage());
   }

   public Option offHeapMemUsed() {
      return this.offHeapCacheSize().map((JFunction1.mcJJ.sp)(x$3) -> x$3 + this._nonRddStorageInfo().offHeapUsage());
   }

   public Option onHeapCacheSize() {
      return this.maxOnHeapMem().map((JFunction1.mcJJ.sp)(x$4) -> BoxesRunTime.unboxToLong(((IterableOnceOps)this._rddStorageInfo().collect(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
               if (x1 != null) {
                  RddStorageInfo storageInfo = (RddStorageInfo)x1._2();
                  if (!storageInfo.level().useOffHeap()) {
                     return BoxesRunTime.boxToLong(storageInfo.memoryUsage());
                  }
               }

               return default.apply(x1);
            }

            public final boolean isDefinedAt(final Tuple2 x1) {
               if (x1 != null) {
                  RddStorageInfo storageInfo = (RddStorageInfo)x1._2();
                  if (!storageInfo.level().useOffHeap()) {
                     return true;
                  }
               }

               return false;
            }
         })).sum(.MODULE$)));
   }

   public Option offHeapCacheSize() {
      return this.maxOffHeapMem().map((JFunction1.mcJJ.sp)(x$5) -> BoxesRunTime.unboxToLong(((IterableOnceOps)this._rddStorageInfo().collect(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
               if (x1 != null) {
                  RddStorageInfo storageInfo = (RddStorageInfo)x1._2();
                  if (storageInfo.level().useOffHeap()) {
                     return BoxesRunTime.boxToLong(storageInfo.memoryUsage());
                  }
               }

               return default.apply(x1);
            }

            public final boolean isDefinedAt(final Tuple2 x1) {
               if (x1 != null) {
                  RddStorageInfo storageInfo = (RddStorageInfo)x1._2();
                  if (storageInfo.level().useOffHeap()) {
                     return true;
                  }
               }

               return false;
            }
         })).sum(.MODULE$)));
   }

   public long diskUsed() {
      return this._nonRddStorageInfo().diskUsage() + BoxesRunTime.unboxToLong(((IterableOnceOps)this._rddBlocks().keys().toSeq().map((JFunction1.mcJI.sp)(rddId) -> this.diskUsedByRdd(rddId))).sum(.MODULE$));
   }

   public long diskUsedByRdd(final int rddId) {
      return BoxesRunTime.unboxToLong(this._rddStorageInfo().get(BoxesRunTime.boxToInteger(rddId)).map((x$6) -> BoxesRunTime.boxToLong($anonfun$diskUsedByRdd$1(x$6))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
   }

   private void updateStorageInfo(final BlockId blockId, final BlockStatus newBlockStatus) {
      BlockStatus oldBlockStatus = (BlockStatus)this.getBlock(blockId).getOrElse(() -> BlockStatus$.MODULE$.empty());
      long changeInMem = newBlockStatus.memSize() - oldBlockStatus.memSize();
      long changeInDisk = newBlockStatus.diskSize() - oldBlockStatus.diskSize();
      StorageLevel level = newBlockStatus.storageLevel();
      Object var10000;
      if (blockId instanceof RDDBlockId var15) {
         int rddId = var15.rddId();
         var10000 = (Tuple2)this._rddStorageInfo().get(BoxesRunTime.boxToInteger(rddId)).map((x0$1) -> {
            if (x0$1 != null) {
               long mem = x0$1.memoryUsage();
               long disk = x0$1.diskUsage();
               return new Tuple2.mcJJ.sp(mem, disk);
            } else {
               throw new MatchError(x0$1);
            }
         }).getOrElse(() -> new Tuple2.mcJJ.sp(0L, 0L));
      } else {
         var10000 = !level.useOffHeap() ? new Tuple2.mcJJ.sp(this._nonRddStorageInfo().onHeapUsage(), this._nonRddStorageInfo().diskUsage()) : new Tuple2.mcJJ.sp(this._nonRddStorageInfo().offHeapUsage(), this._nonRddStorageInfo().diskUsage());
      }

      Object var13 = var10000;
      if (var13 != null) {
         long oldMem = ((Tuple2)var13)._1$mcJ$sp();
         long oldDisk = ((Tuple2)var13)._2$mcJ$sp();
         Tuple2.mcJJ.sp var12 = new Tuple2.mcJJ.sp(oldMem, oldDisk);
         long oldMem = ((Tuple2)var12)._1$mcJ$sp();
         long oldDisk = ((Tuple2)var12)._2$mcJ$sp();
         long newMem = scala.math.package..MODULE$.max(oldMem + changeInMem, 0L);
         long newDisk = scala.math.package..MODULE$.max(oldDisk + changeInDisk, 0L);
         if (blockId instanceof RDDBlockId) {
            RDDBlockId var30 = (RDDBlockId)blockId;
            int rddId = var30.rddId();
            if (newMem + newDisk == 0L) {
               this._rddStorageInfo().remove(BoxesRunTime.boxToInteger(rddId));
               BoxedUnit var34 = BoxedUnit.UNIT;
            } else {
               this._rddStorageInfo().update(BoxesRunTime.boxToInteger(rddId), new RddStorageInfo(newMem, newDisk, level));
               BoxedUnit var33 = BoxedUnit.UNIT;
            }
         } else {
            if (!level.useOffHeap()) {
               this._nonRddStorageInfo().onHeapUsage_$eq(newMem);
            } else {
               this._nonRddStorageInfo().offHeapUsage_$eq(newMem);
            }

            this._nonRddStorageInfo().diskUsage_$eq(newDisk);
            BoxedUnit var32 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(var13);
      }
   }

   private final void RddStorageInfo$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.RddStorageInfo$module == null) {
            this.RddStorageInfo$module = new RddStorageInfo$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void NonRddStorageInfo$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NonRddStorageInfo$module == null) {
            this.NonRddStorageInfo$module = new NonRddStorageInfo$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$new$1(final StorageStatus $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         BlockId bid = (BlockId)x0$1._1();
         BlockStatus bstatus = (BlockStatus)x0$1._2();
         $this.addBlock(bid, bstatus);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final Option $anonfun$onHeapMemRemaining$1(final StorageStatus $this, final long m) {
      return $this.onHeapMemUsed().map((JFunction1.mcJJ.sp)(o) -> m - o);
   }

   // $FF: synthetic method
   public static final Option $anonfun$offHeapMemRemaining$1(final StorageStatus $this, final long m) {
      return $this.offHeapMemUsed().map((JFunction1.mcJJ.sp)(o) -> m - o);
   }

   // $FF: synthetic method
   public static final long $anonfun$diskUsedByRdd$1(final RddStorageInfo x$6) {
      return x$6.diskUsage();
   }

   public StorageStatus(final BlockManagerId blockManagerId, final long maxMemory, final Option maxOnHeapMem, final Option maxOffHeapMem) {
      this.blockManagerId = blockManagerId;
      this.maxMemory = maxMemory;
      this.maxOnHeapMem = maxOnHeapMem;
      this.maxOffHeapMem = maxOffHeapMem;
      this._rddBlocks = new HashMap();
      this._nonRddBlocks = new HashMap();
      this._rddStorageInfo = new HashMap();
      this._nonRddStorageInfo = new NonRddStorageInfo(0L, 0L, 0L);
   }

   public StorageStatus(final BlockManagerId bmid, final long maxMemory, final Option maxOnHeapMem, final Option maxOffHeapMem, final Map initialBlocks) {
      this(bmid, maxMemory, maxOnHeapMem, maxOffHeapMem);
      initialBlocks.foreach((x0$1) -> {
         $anonfun$new$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class RddStorageInfo implements Product, Serializable {
      private final long memoryUsage;
      private final long diskUsage;
      private final StorageLevel level;
      // $FF: synthetic field
      public final StorageStatus $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long memoryUsage() {
         return this.memoryUsage;
      }

      public long diskUsage() {
         return this.diskUsage;
      }

      public StorageLevel level() {
         return this.level;
      }

      public RddStorageInfo copy(final long memoryUsage, final long diskUsage, final StorageLevel level) {
         return this.org$apache$spark$storage$StorageStatus$RddStorageInfo$$$outer().new RddStorageInfo(memoryUsage, diskUsage, level);
      }

      public long copy$default$1() {
         return this.memoryUsage();
      }

      public long copy$default$2() {
         return this.diskUsage();
      }

      public StorageLevel copy$default$3() {
         return this.level();
      }

      public String productPrefix() {
         return "RddStorageInfo";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.memoryUsage());
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.diskUsage());
            }
            case 2 -> {
               return this.level();
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
         return x$1 instanceof RddStorageInfo;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "memoryUsage";
            }
            case 1 -> {
               return "diskUsage";
            }
            case 2 -> {
               return "level";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.memoryUsage()));
         var1 = Statics.mix(var1, Statics.longHash(this.diskUsage()));
         var1 = Statics.mix(var1, Statics.anyHash(this.level()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label60: {
               if (x$1 instanceof RddStorageInfo && ((RddStorageInfo)x$1).org$apache$spark$storage$StorageStatus$RddStorageInfo$$$outer() == this.org$apache$spark$storage$StorageStatus$RddStorageInfo$$$outer()) {
                  RddStorageInfo var4 = (RddStorageInfo)x$1;
                  if (this.memoryUsage() == var4.memoryUsage() && this.diskUsage() == var4.diskUsage()) {
                     label50: {
                        StorageLevel var10000 = this.level();
                        StorageLevel var5 = var4.level();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label50;
                        }

                        if (var4.canEqual(this)) {
                           break label60;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      // $FF: synthetic method
      public StorageStatus org$apache$spark$storage$StorageStatus$RddStorageInfo$$$outer() {
         return this.$outer;
      }

      public RddStorageInfo(final long memoryUsage, final long diskUsage, final StorageLevel level) {
         this.memoryUsage = memoryUsage;
         this.diskUsage = diskUsage;
         this.level = level;
         if (StorageStatus.this == null) {
            throw null;
         } else {
            this.$outer = StorageStatus.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class RddStorageInfo$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final StorageStatus $outer;

      public final String toString() {
         return "RddStorageInfo";
      }

      public RddStorageInfo apply(final long memoryUsage, final long diskUsage, final StorageLevel level) {
         return this.$outer.new RddStorageInfo(memoryUsage, diskUsage, level);
      }

      public Option unapply(final RddStorageInfo x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.memoryUsage()), BoxesRunTime.boxToLong(x$0.diskUsage()), x$0.level())));
      }

      public RddStorageInfo$() {
         if (StorageStatus.this == null) {
            throw null;
         } else {
            this.$outer = StorageStatus.this;
            super();
         }
      }
   }

   private class NonRddStorageInfo implements Product, Serializable {
      private long onHeapUsage;
      private long offHeapUsage;
      private long diskUsage;
      // $FF: synthetic field
      public final StorageStatus $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long onHeapUsage() {
         return this.onHeapUsage;
      }

      public void onHeapUsage_$eq(final long x$1) {
         this.onHeapUsage = x$1;
      }

      public long offHeapUsage() {
         return this.offHeapUsage;
      }

      public void offHeapUsage_$eq(final long x$1) {
         this.offHeapUsage = x$1;
      }

      public long diskUsage() {
         return this.diskUsage;
      }

      public void diskUsage_$eq(final long x$1) {
         this.diskUsage = x$1;
      }

      public NonRddStorageInfo copy(final long onHeapUsage, final long offHeapUsage, final long diskUsage) {
         return this.org$apache$spark$storage$StorageStatus$NonRddStorageInfo$$$outer().new NonRddStorageInfo(onHeapUsage, offHeapUsage, diskUsage);
      }

      public long copy$default$1() {
         return this.onHeapUsage();
      }

      public long copy$default$2() {
         return this.offHeapUsage();
      }

      public long copy$default$3() {
         return this.diskUsage();
      }

      public String productPrefix() {
         return "NonRddStorageInfo";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.onHeapUsage());
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.offHeapUsage());
            }
            case 2 -> {
               return BoxesRunTime.boxToLong(this.diskUsage());
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
         return x$1 instanceof NonRddStorageInfo;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "onHeapUsage";
            }
            case 1 -> {
               return "offHeapUsage";
            }
            case 2 -> {
               return "diskUsage";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.onHeapUsage()));
         var1 = Statics.mix(var1, Statics.longHash(this.offHeapUsage()));
         var1 = Statics.mix(var1, Statics.longHash(this.diskUsage()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label45: {
               if (x$1 instanceof NonRddStorageInfo && ((NonRddStorageInfo)x$1).org$apache$spark$storage$StorageStatus$NonRddStorageInfo$$$outer() == this.org$apache$spark$storage$StorageStatus$NonRddStorageInfo$$$outer()) {
                  NonRddStorageInfo var4 = (NonRddStorageInfo)x$1;
                  if (this.onHeapUsage() == var4.onHeapUsage() && this.offHeapUsage() == var4.offHeapUsage() && this.diskUsage() == var4.diskUsage() && var4.canEqual(this)) {
                     break label45;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public StorageStatus org$apache$spark$storage$StorageStatus$NonRddStorageInfo$$$outer() {
         return this.$outer;
      }

      public NonRddStorageInfo(final long onHeapUsage, final long offHeapUsage, final long diskUsage) {
         this.onHeapUsage = onHeapUsage;
         this.offHeapUsage = offHeapUsage;
         this.diskUsage = diskUsage;
         if (StorageStatus.this == null) {
            throw null;
         } else {
            this.$outer = StorageStatus.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class NonRddStorageInfo$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final StorageStatus $outer;

      public final String toString() {
         return "NonRddStorageInfo";
      }

      public NonRddStorageInfo apply(final long onHeapUsage, final long offHeapUsage, final long diskUsage) {
         return this.$outer.new NonRddStorageInfo(onHeapUsage, offHeapUsage, diskUsage);
      }

      public Option unapply(final NonRddStorageInfo x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.onHeapUsage()), BoxesRunTime.boxToLong(x$0.offHeapUsage()), BoxesRunTime.boxToLong(x$0.diskUsage()))));
      }

      public NonRddStorageInfo$() {
         if (StorageStatus.this == null) {
            throw null;
         } else {
            this.$outer = StorageStatus.this;
            super();
         }
      }
   }
}
