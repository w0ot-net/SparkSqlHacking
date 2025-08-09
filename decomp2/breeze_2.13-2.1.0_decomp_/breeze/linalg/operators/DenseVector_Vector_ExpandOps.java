package breeze.linalg.operators;

import breeze.generic.MMRegistry2;
import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.Vector;
import breeze.math.PowImplicits$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.MapView;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t%fa\u0002 @!\u0003\r\tA\u0012\u0005\u0006/\u0002!\t\u0001\u0017\u0005\b9\u0002\u0011\r\u0011b\u0001^\u0011\u001dY\bA1A\u0005\u0004qD\u0011\"a\u0002\u0001\u0005\u0004%\u0019!!\u0003\t\u0013\u0005]\u0001A1A\u0005\u0004\u0005e\u0001\"CA\u0014\u0001\t\u0007I1AA\u0015\u0011%\t9\u0004\u0001b\u0001\n\u0007\tI\u0004C\u0005\u0002>\u0001\u0011\r\u0011b\u0001\u0002@!I\u00111\t\u0001C\u0002\u0013\r\u0011Q\t\u0005\n\u0003\u0013\u0002!\u0019!C\u0002\u0003\u0017B\u0011\"!\u0016\u0001\u0005\u0004%\u0019!a\u0016\t\u0013\u0005m\u0003A1A\u0005\u0004\u0005u\u0003\"CA1\u0001\t\u0007I1AA2\u0011%\t9\u0007\u0001b\u0001\n\u0007\tI\u0007C\u0005\u0002t\u0001\u0011\r\u0011b\u0001\u0002v!I\u0011\u0011\u0010\u0001C\u0002\u0013\r\u00111\u0010\u0005\n\u0003\u007f\u0002!\u0019!C\u0002\u0003\u0003C\u0011\"!\"\u0001\u0005\u0004%\u0019!a\"\t\u0013\u0005E\u0005A1A\u0005\u0004\u0005M\u0005\"CAL\u0001\t\u0007I1AAM\u0011%\ti\n\u0001b\u0001\n\u0007\ty\nC\u0005\u0002$\u0002\u0011\r\u0011b\u0001\u0002&\"I\u0011q\u0016\u0001C\u0002\u0013\r\u0011\u0011\u0017\u0005\n\u0003k\u0003!\u0019!C\u0002\u0003oC\u0011\"a/\u0001\u0005\u0004%\u0019!!0\t\u0013\u0005\u0005\u0007A1A\u0005\u0004\u0005\r\u0007\"CAg\u0001\t\u0007I1AAh\u0011%\t\u0019\u000e\u0001b\u0001\n\u0007\t)\u000eC\u0005\u0002Z\u0002\u0011\r\u0011b\u0001\u0002\\\"I\u0011q\u001c\u0001C\u0002\u0013\r\u0011\u0011\u001d\u0005\n\u0003W\u0004!\u0019!C\u0002\u0003[D\u0011\"!=\u0001\u0005\u0004%\u0019!a=\t\u0013\u0005]\bA1A\u0005\u0004\u0005e\b\"CA\u007f\u0001\t\u0007I1AA\u0000\u0011%\u00119\u0001\u0001b\u0001\n\u0007\u0011I\u0001C\u0005\u0003\u000e\u0001\u0011\r\u0011b\u0001\u0003\u0010!I!1\u0003\u0001C\u0002\u0013\r!Q\u0003\u0005\n\u00053\u0001!\u0019!C\u0002\u00057A\u0011Ba\b\u0001\u0005\u0004%\u0019A!\t\t\u0013\t\u0015\u0002A1A\u0005\u0004\t\u001d\u0002\"\u0003B\u0016\u0001\t\u0007I1\u0001B\u0017\u0011%\u0011\t\u0004\u0001b\u0001\n\u0007\u0011\u0019\u0004C\u0005\u00038\u0001\u0011\r\u0011b\u0001\u0003:!I!Q\b\u0001C\u0002\u0013\r!q\b\u0005\n\u0005\u0007\u0002!\u0019!C\u0002\u0005\u000bB\u0011B!\u0013\u0001\u0005\u0004%\u0019Aa\u0013\t\u0013\t=\u0003A1A\u0005\u0004\tE\u0003\"\u0003B+\u0001\t\u0007I1\u0001B,\u0011%\u0011Y\u0006\u0001b\u0001\n\u0007\u0011i\u0006C\u0005\u0003b\u0001\u0011\r\u0011b\u0001\u0003d!I!q\r\u0001C\u0002\u0013\r!\u0011\u000e\u0005\n\u0005[\u0002!\u0019!C\u0002\u0005_B\u0011Ba\u001d\u0001\u0005\u0004%\u0019A!\u001e\t\u0013\te\u0004A1A\u0005\u0004\tm\u0004\"\u0003B@\u0001\t\u0007I1\u0001BA\u0011%\u0011)\t\u0001b\u0001\n\u0007\u00119\tC\u0005\u0003\f\u0002\u0011\r\u0011b\u0001\u0003\u000e\"I!\u0011\u0013\u0001C\u0002\u0013\r!1\u0013\u0005\n\u0005/\u0003!\u0019!C\u0002\u00053C\u0011B!(\u0001\u0005\u0004%\u0019Aa(\t\u0013\t\r\u0006A1A\u0005\u0004\t\u0015&\u0001\b#f]N,g+Z2u_J|f+Z2u_J|V\t\u001f9b]\u0012|\u0005o\u001d\u0006\u0003\u0001\u0006\u000b\u0011b\u001c9fe\u0006$xN]:\u000b\u0005\t\u001b\u0015A\u00027j]\u0006dwMC\u0001E\u0003\u0019\u0011'/Z3{K\u000e\u00011#\u0002\u0001H\u001bF#\u0006C\u0001%L\u001b\u0005I%\"\u0001&\u0002\u000bM\u001c\u0017\r\\1\n\u00051K%AB!osJ+g\r\u0005\u0002O\u001f6\tq(\u0003\u0002Q\u007f\tIa+Z2u_J|\u0005o\u001d\t\u0003\u001dJK!aU \u00031\u0011+gn]3WK\u000e$xN]0Ue\u00064XM]:bY>\u00038\u000f\u0005\u0002O+&\u0011ak\u0010\u0002\u0017\t\u0016t7/\u001a,fGR|'oX*mS\u000eLgnZ(qg\u00061A%\u001b8ji\u0012\"\u0012!\u0017\t\u0003\u0011jK!aW%\u0003\tUs\u0017\u000e^\u0001\u001eS6\u0004HnX(q\u001bVd\u0017J\u001c8fe~#ek\u0018,`KF|6kX%oiV\ta\fE\u0003`WFDXO\u0004\u0002aS:\u0011\u0011\r\u001b\b\u0003E\u001et!a\u00194\u000e\u0003\u0011T!!Z#\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0015B\u0001\"D\u0013\t\u0001\u0015)\u0003\u0002k\u007f\u0005Qq\n]'vY&sg.\u001a:\n\u00051l'!B%na2\u0014\u0014B\u00018p\u0005\u0015)f)\u001e8d\u0015\t\u00018)A\u0004hK:,'/[2\u0011\u0007I\u001cX/D\u0001B\u0013\t!\u0018IA\u0006EK:\u001cXMV3di>\u0014\bC\u0001%w\u0013\t9\u0018JA\u0002J]R\u00042A]=v\u0013\tQ\u0018I\u0001\u0004WK\u000e$xN]\u0001!S6\u0004HnX(q\u001bVd\u0017J\u001c8fe~#ek\u0018,`KF|6k\u0018#pk\ndW-F\u0001~!\u0019y6N`A\u0003\u007fB\u0019!o]@\u0011\u0007!\u000b\t!C\u0002\u0002\u0004%\u0013a\u0001R8vE2,\u0007c\u0001:z\u007f\u0006y\u0012.\u001c9m?>\u0003X*\u001e7J]:,'o\u0018#W?Z{V-]0T?\u001acw.\u0019;\u0016\u0005\u0005-\u0001\u0003C0l\u0003\u001b\t)\"a\u0004\u0011\tI\u001c\u0018q\u0002\t\u0004\u0011\u0006E\u0011bAA\n\u0013\n)a\t\\8biB!!/_A\b\u0003yIW\u000e\u001d7`\u001fBlU\u000f\\%o]\u0016\u0014x\f\u0012,`-~+\u0017oX*`\u0019>tw-\u0006\u0002\u0002\u001cAAql[A\u000f\u0003K\ty\u0002\u0005\u0003sg\u0006}\u0001c\u0001%\u0002\"%\u0019\u00111E%\u0003\t1{gn\u001a\t\u0005ef\fy\"A\u000ej[Bdwl\u00149`\tZ{fkX3r?Z{\u0016J\u001c;`\u001fB\fE\rZ\u000b\u0003\u0003W\u0001\u0002BTA\u0017cb\f\t$]\u0005\u0004\u0003_y$A\u0004\"j]\u0006\u0014\u0018PU3hSN$(/\u001f\b\u0004\u001d\u0006M\u0012bAA\u001b\u007f\u0005)q\n]!eI\u0006q\u0012.\u001c9m?>\u0003x\f\u0012,`-~+\u0017o\u0018,`\t>,(\r\\3`\u001fB\fE\rZ\u000b\u0003\u0003w\u0001\u0012BTA\u0017}\u0006\u0015\u0011\u0011\u0007@\u0002;%l\u0007\u000f\\0Pa~#ek\u0018,`KF|fk\u0018$m_\u0006$xl\u00149BI\u0012,\"!!\u0011\u0011\u00179\u000bi#!\u0004\u0002\u0016\u0005E\u0012QB\u0001\u001dS6\u0004HnX(q?\u00123vLV0fc~3v\fT8oO~{\u0005/\u00113e+\t\t9\u0005E\u0006O\u0003[\ti\"!\n\u00022\u0005u\u0011aG5na2|v\n]0E-~3v,Z9`-~Ke\u000e^0PaN+(-\u0006\u0002\u0002NAAa*!\frq\u0006=\u0013OD\u0002O\u0003#J1!a\u0015@\u0003\u0015y\u0005oU;c\u0003yIW\u000e\u001d7`\u001fB|FIV0W?\u0016\fxLV0E_V\u0014G.Z0PaN+(-\u0006\u0002\u0002ZAIa*!\f\u007f\u0003\u000b\tyE`\u0001\u001eS6\u0004HnX(q?\u00123vLV0fc~3vL\u00127pCR|v\n]*vEV\u0011\u0011q\f\t\f\u001d\u00065\u0012QBA\u000b\u0003\u001f\ni!\u0001\u000fj[Bdwl\u00149`\tZ{fkX3r?Z{Fj\u001c8h?>\u00038+\u001e2\u0016\u0005\u0005\u0015\u0004c\u0003(\u0002.\u0005u\u0011QEA(\u0003;\t\u0011%[7qY~{\u0005o\u0018#W?Z{V-]0W?&sGoX(q\u001bVd7kY1mCJ,\"!a\u001b\u0011\u00119\u000bi#\u001d=\u0002nEt1ATA8\u0013\r\t\thP\u0001\f\u001fBlU\u000f\\*dC2\f'/\u0001\u0013j[Bdwl\u00149`\tZ{fkX3r?Z{Fi\\;cY\u0016|v\n]'vYN\u001b\u0017\r\\1s+\t\t9\bE\u0005O\u0003[q\u0018QAA7}\u0006\u0019\u0013.\u001c9m?>\u0003x\f\u0012,`-~+\u0017o\u0018,`\r2|\u0017\r^0Pa6+HnU2bY\u0006\u0014XCAA?!-q\u0015QFA\u0007\u0003+\ti'!\u0004\u0002E%l\u0007\u000f\\0Pa~#ek\u0018,`KF|fk\u0018'p]\u001e|v\n]'vYN\u001b\u0017\r\\1s+\t\t\u0019\tE\u0006O\u0003[\ti\"!\n\u0002n\u0005u\u0011aG5na2|v\n]0E-~3v,Z9`-~Ke\u000e^0Pa\u0012Kg/\u0006\u0002\u0002\nBAa*!\frq\u0006-\u0015OD\u0002O\u0003\u001bK1!a$@\u0003\u0015y\u0005\u000fR5w\u0003yIW\u000e\u001d7`\u001fB|FIV0W?\u0016\fxLV0E_V\u0014G.Z0Pa\u0012Kg/\u0006\u0002\u0002\u0016BIa*!\f\u007f\u0003\u000b\tYI`\u0001\u001eS6\u0004HnX(q?\u00123vLV0fc~3vL\u00127pCR|v\n\u001d#jmV\u0011\u00111\u0014\t\f\u001d\u00065\u0012QBA\u000b\u0003\u0017\u000bi!\u0001\u000fj[Bdwl\u00149`\tZ{fkX3r?Z{Fj\u001c8h?>\u0003H)\u001b<\u0016\u0005\u0005\u0005\u0006c\u0003(\u0002.\u0005u\u0011QEAF\u0003;\t1$[7qY~{\u0005o\u0018#W?Z{V-]0W?&sGoX(q'\u0016$XCAAT!!q\u0015QF9y\u0003S\u000bhb\u0001(\u0002,&\u0019\u0011QV \u0002\u000b=\u00038+\u001a;\u0002=%l\u0007\u000f\\0Pa~#ek\u0018,`KF|fk\u0018#pk\ndWmX(q'\u0016$XCAAZ!%q\u0015Q\u0006@\u0002\u0006\u0005%f0A\u000fj[Bdwl\u00149`\tZ{fkX3r?Z{f\t\\8bi~{\u0005oU3u+\t\tI\fE\u0006O\u0003[\ti!!\u0006\u0002*\u00065\u0011\u0001H5na2|v\n]0E-~3v,Z9`-~cuN\\4`\u001fB\u001cV\r^\u000b\u0003\u0003\u007f\u00032BTA\u0017\u0003;\t)#!+\u0002\u001e\u0005Y\u0012.\u001c9m?>\u0003x\f\u0012,`-~+\u0017o\u0018,`\u0013:$xl\u00149N_\u0012,\"!!2\u0011\u00119\u000bi#\u001d=\u0002HFt1ATAe\u0013\r\tYmP\u0001\u0006\u001fBlu\u000eZ\u0001\u001fS6\u0004HnX(q?\u00123vLV0fc~3v\fR8vE2,wl\u00149N_\u0012,\"!!5\u0011\u00139\u000biC`A\u0003\u0003\u000ft\u0018!H5na2|v\n]0E-~3v,Z9`-~3En\\1u?>\u0003Xj\u001c3\u0016\u0005\u0005]\u0007c\u0003(\u0002.\u00055\u0011QCAd\u0003\u001b\tA$[7qY~{\u0005o\u0018#W?Z{V-]0W?2{gnZ0Pa6{G-\u0006\u0002\u0002^BYa*!\f\u0002\u001e\u0005\u0015\u0012qYA\u000f\u0003mIW\u000e\u001d7`\u001fB|FIV0W?\u0016\fxLV0J]R|v\n\u001d)poV\u0011\u00111\u001d\t\t\u001d\u00065\u0012\u000f_Asc:\u0019a*a:\n\u0007\u0005%x(A\u0003PaB{w/\u0001\u0010j[Bdwl\u00149`\tZ{fkX3r?Z{Fi\\;cY\u0016|v\n\u001d)poV\u0011\u0011q\u001e\t\n\u001d\u00065b0!\u0002\u0002fz\fQ$[7qY~{\u0005o\u0018#W?Z{V-]0W?\u001acw.\u0019;`\u001fB\u0004vn^\u000b\u0003\u0003k\u00042BTA\u0017\u0003\u001b\t)\"!:\u0002\u000e\u0005a\u0012.\u001c9m?>\u0003x\f\u0012,`-~+\u0017o\u0018,`\u0019>twmX(q!><XCAA~!-q\u0015QFA\u000f\u0003K\t)/!\b\u0002I%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0W?&sGoX(q\u001bVd7kY1mCJ,\"A!\u0001\u0011\u000f9\u0013\u0019!\u001d=\u0002n%\u0019!QA \u0003)\tKg.\u0019:z+B$\u0017\r^3SK\u001eL7\u000f\u001e:z\u0003\u001dJW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`-~#u.\u001e2mK~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\t-\u0001\u0003\u0003(\u0003\u0004y\f)!!\u001c\u0002M%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0W?\u001acw.\u0019;`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0003\u0012AIaJa\u0001\u0002\u000e\u0005U\u0011QN\u0001&S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018,`\u0019>twmX(q\u001bVd7kY1mCJ,\"Aa\u0006\u0011\u00139\u0013\u0019!!\b\u0002&\u00055\u0014AH5na2|v\n]0J]Bc\u0017mY3`\tZ{fkX%oi~{\u0005\u000fR5w+\t\u0011i\u0002E\u0004O\u0005\u0007\t\b0a#\u0002C%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0W?\u0012{WO\u00197f?>\u0003H)\u001b<\u0016\u0005\t\r\u0002\u0003\u0003(\u0003\u0004y\f)!a#\u0002A%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0W?\u001acw.\u0019;`\u001fB$\u0015N^\u000b\u0003\u0005S\u0001\u0012B\u0014B\u0002\u0003\u001b\t)\"a#\u0002?%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0W?2{gnZ0Pa\u0012Kg/\u0006\u0002\u00030AIaJa\u0001\u0002\u001e\u0005\u0015\u00121R\u0001\u001fS6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018,`\u0013:$xl\u00149TKR,\"A!\u000e\u0011\u000f9\u0013\u0019!\u001d=\u0002*\u0006\t\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?Z{Fi\\;cY\u0016|v\n]*fiV\u0011!1\b\t\t\u001d\n\ra0!\u0002\u0002*\u0006\u0001\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?Z{f\t\\8bi~{\u0005oU3u+\t\u0011\t\u0005E\u0005O\u0005\u0007\ti!!\u0006\u0002*\u0006y\u0012.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018#W?Z{Fj\u001c8h?>\u00038+\u001a;\u0016\u0005\t\u001d\u0003#\u0003(\u0003\u0004\u0005u\u0011QEAU\u0003yIW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`-~Ke\u000e^0Pa6{G-\u0006\u0002\u0003NA9aJa\u0001rq\u0006\u001d\u0017!I5na2|v\n]0J]Bc\u0017mY3`\tZ{fk\u0018#pk\ndWmX(q\u001b>$WC\u0001B*!!q%1\u0001@\u0002\u0006\u0005\u001d\u0017\u0001I5na2|v\n]0J]Bc\u0017mY3`\tZ{fk\u0018$m_\u0006$xl\u00149N_\u0012,\"A!\u0017\u0011\u00139\u0013\u0019!!\u0004\u0002\u0016\u0005\u001d\u0017aH5na2|v\n]0J]Bc\u0017mY3`\tZ{fk\u0018'p]\u001e|v\n]'pIV\u0011!q\f\t\n\u001d\n\r\u0011QDA\u0013\u0003\u000f\fa$[7qY~{\u0005oX%o!2\f7-Z0E-~3v,\u00138u?>\u0003\bk\\<\u0016\u0005\t\u0015\u0004c\u0002(\u0003\u0004ED\u0018Q]\u0001\"S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018,`\t>,(\r\\3`\u001fB\u0004vn^\u000b\u0003\u0005W\u0002\u0002B\u0014B\u0002}\u0006\u0015\u0011Q]\u0001!S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018,`\r2|\u0017\r^0PaB{w/\u0006\u0002\u0003rAIaJa\u0001\u0002\u000e\u0005U\u0011Q]\u0001 S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018,`\u0019>twmX(q!><XC\u0001B<!%q%1AA\u000f\u0003K\t)/\u0001\u0018j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123vLV0{KJ|w,\u001b3f[B|G/\u001a8u?&sGoX(q\u0003\u0012$WC\u0001B?!\u001dq%1A9y\u0003c\t\u0011'[7qY~{\u0005oX%o!2\f7-Z0E-~3vL_3s_~KG-Z7q_R,g\u000e^0E_V\u0014G.Z0Pa\u0006#G-\u0006\u0002\u0003\u0004BAaJa\u0001\u007f\u0003\u000b\t\t$\u0001\u0019j[Bdwl\u00149`\u0013:\u0004F.Y2f?\u00123vLV0{KJ|w,\u001b3f[B|G/\u001a8u?\u001acw.\u0019;`\u001fB\fE\rZ\u000b\u0003\u0005\u0013\u0003\u0012B\u0014B\u0002\u0003\u001b\t)\"!\r\u0002_%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|FIV0W?j,'o\\0jI\u0016l\u0007o\u001c;f]R|Fj\u001c8h?>\u0003\u0018\t\u001a3\u0016\u0005\t=\u0005#\u0003(\u0003\u0004\u0005u\u0011QEA\u0019\u00039JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`-~SXM]8`S\u0012,W\u000e]8uK:$x,\u00138u?>\u00038+\u001e2\u0016\u0005\tU\u0005c\u0002(\u0003\u0004ED\u0018qJ\u00012S6\u0004HnX(q?&s\u0007\u000b\\1dK~#ek\u0018,`u\u0016\u0014xnX5eK6\u0004x\u000e^3oi~#u.\u001e2mK~{\u0005oU;c+\t\u0011Y\n\u0005\u0005O\u0005\u0007q\u0018QAA(\u0003AJW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0012,`-~SXM]8`S\u0012,W\u000e]8uK:$xL\u00127pCR|v\n]*vEV\u0011!\u0011\u0015\t\n\u001d\n\r\u0011QBA\u000b\u0003\u001f\nq&[7qY~{\u0005oX%o!2\f7-Z0E-~3vL_3s_~KG-Z7q_R,g\u000e^0M_:<wl\u00149Tk\n,\"Aa*\u0011\u00139\u0013\u0019!!\b\u0002&\u0005=\u0003"
)
public interface DenseVector_Vector_ExpandOps extends VectorOps, DenseVector_TraversalOps, DenseVector_SlicingOps {
   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpAdd_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpAdd_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpAdd_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpAdd_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpSub_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpSub_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpSub_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpSub_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpMulScalar_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpMulScalar_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpMulScalar_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpMulScalar_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpDiv_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpDiv_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpDiv_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpDiv_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpSet_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpSet_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpSet_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpSet_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpMod_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpMod_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpMod_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpMod_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpPow_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpPow_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpPow_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpPow_$eq(final BinaryRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpMulScalar_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpMulScalar_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpMulScalar_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpMulScalar_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpDiv_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpDiv_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpDiv_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpDiv_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpSet_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpSet_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpSet_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpSet_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpMod_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpMod_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpMod_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpMod_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpPow_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpPow_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpPow_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpPow_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub_$eq(final BinaryUpdateRegistry x$1);

   void breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub_$eq(final BinaryUpdateRegistry x$1);

   UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Int();

   UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Double();

   UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Float();

   UFunc.UImpl2 impl_OpMulInner_DV_V_eq_S_Long();

   BinaryRegistry impl_Op_DV_V_eq_V_Int_OpAdd();

   BinaryRegistry impl_Op_DV_V_eq_V_Double_OpAdd();

   BinaryRegistry impl_Op_DV_V_eq_V_Float_OpAdd();

   BinaryRegistry impl_Op_DV_V_eq_V_Long_OpAdd();

   BinaryRegistry impl_Op_DV_V_eq_V_Int_OpSub();

   BinaryRegistry impl_Op_DV_V_eq_V_Double_OpSub();

   BinaryRegistry impl_Op_DV_V_eq_V_Float_OpSub();

   BinaryRegistry impl_Op_DV_V_eq_V_Long_OpSub();

   BinaryRegistry impl_Op_DV_V_eq_V_Int_OpMulScalar();

   BinaryRegistry impl_Op_DV_V_eq_V_Double_OpMulScalar();

   BinaryRegistry impl_Op_DV_V_eq_V_Float_OpMulScalar();

   BinaryRegistry impl_Op_DV_V_eq_V_Long_OpMulScalar();

   BinaryRegistry impl_Op_DV_V_eq_V_Int_OpDiv();

   BinaryRegistry impl_Op_DV_V_eq_V_Double_OpDiv();

   BinaryRegistry impl_Op_DV_V_eq_V_Float_OpDiv();

   BinaryRegistry impl_Op_DV_V_eq_V_Long_OpDiv();

   BinaryRegistry impl_Op_DV_V_eq_V_Int_OpSet();

   BinaryRegistry impl_Op_DV_V_eq_V_Double_OpSet();

   BinaryRegistry impl_Op_DV_V_eq_V_Float_OpSet();

   BinaryRegistry impl_Op_DV_V_eq_V_Long_OpSet();

   BinaryRegistry impl_Op_DV_V_eq_V_Int_OpMod();

   BinaryRegistry impl_Op_DV_V_eq_V_Double_OpMod();

   BinaryRegistry impl_Op_DV_V_eq_V_Float_OpMod();

   BinaryRegistry impl_Op_DV_V_eq_V_Long_OpMod();

   BinaryRegistry impl_Op_DV_V_eq_V_Int_OpPow();

   BinaryRegistry impl_Op_DV_V_eq_V_Double_OpPow();

   BinaryRegistry impl_Op_DV_V_eq_V_Float_OpPow();

   BinaryRegistry impl_Op_DV_V_eq_V_Long_OpPow();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpMulScalar();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpMulScalar();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpMulScalar();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpMulScalar();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpDiv();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpDiv();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpDiv();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpDiv();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpSet();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpSet();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpSet();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpSet();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpMod();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpMod();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpMod();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpMod();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Int_OpPow();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Double_OpPow();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Float_OpPow();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_Long_OpPow();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub();

   BinaryUpdateRegistry impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub();

   static void $init$(final DenseVector_Vector_ExpandOps $this) {
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final DenseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int aoff = a.offset();
               int result = 0;
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += ad[aoff] * b.apply$mcII$sp(index$macro$4);
                  aoff += a.stride();
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_OpMulInner_V_V_eq_S_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Double_$eq(new UFunc.UImpl2() {
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

         public double apply(final DenseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               int aoff = a.offset();
               double result = (double)0.0F;
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += ad[aoff] * b.apply$mcID$sp(index$macro$4);
                  aoff += a.stride();
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_OpMulInner_V_V_eq_S_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Float_$eq(new UFunc.UImpl2() {
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

         public float apply(final DenseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               int aoff = a.offset();
               float result = 0.0F;
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += ad[aoff] * b.apply$mcIF$sp(index$macro$4);
                  aoff += a.stride();
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_OpMulInner_V_V_eq_S_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_OpMulInner_DV_V_eq_S_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final DenseVector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               int aoff = a.offset();
               long result = 0L;
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  result += ad[aoff] * b.apply$mcIJ$sp(index$macro$4);
                  aoff += a.stride();
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_OpMulInner_V_V_eq_S_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpAdd_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] + b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpAdd_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] + b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpAdd_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] + b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpAdd_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] + b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpSub_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] - b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpSub_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] - b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpSub_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] - b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpSub_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] - b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpMulScalar_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] * b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpMulScalar_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] * b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpMulScalar_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] * b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpMulScalar_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] * b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpDiv_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] / b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpDiv_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] / b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpDiv_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] / b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpDiv_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] / b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpSet_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpSet_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpSet_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpSet_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpMod_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] % b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpMod_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] % b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpMod_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] % b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpMod_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = ad[aoff] % b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Int_OpPow_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());
            int[] rd = result.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = PowImplicits$.MODULE$.IntPow(ad[aoff]).pow(b.apply$mcII$sp(index$macro$2));
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Double_OpPow_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
            double[] rd = result.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = PowImplicits$.MODULE$.DoublePow(ad[aoff]).pow(b.apply$mcID$sp(index$macro$2));
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Float_OpPow_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());
            float[] rd = result.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = PowImplicits$.MODULE$.FloatPow(ad[aoff]).pow(b.apply$mcIF$sp(index$macro$2));
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_DV_V_eq_V_Long_OpPow_$eq(new BinaryRegistry() {
         private ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache;
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Nothing multipleOptions(final Object a, final Object b, final Map m) {
            return BinaryRegistry.multipleOptions$(this, a, b, m);
         }

         public Object apply(final Object a, final Object b) {
            return BinaryRegistry.apply$(this, a, b);
         }

         public UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

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

         public ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache() {
            return this.breeze$linalg$operators$BinaryRegistry$$l1cache;
         }

         public final void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1) {
            this.breeze$linalg$operators$BinaryRegistry$$l1cache = x$1;
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public DenseVector bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());
            long[] rd = result.data$mcJ$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               rd[index$macro$2] = PowImplicits$.MODULE$.LongPow(ad[aoff]).pow(b.apply$mcIJ$sp(index$macro$2));
               aoff += a.stride();
            }

            return result;
         }

         public {
            MMRegistry2.$init$(this);
            BinaryRegistry.$init$(this);
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpMulScalar_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] *= b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Int_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpMulScalar_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] *= b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Double_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpMulScalar_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] *= b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Float_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpMulScalar_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] *= b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Long_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpDiv_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] /= b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpDiv_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] /= b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpDiv_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] /= b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpDiv_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] /= b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpSet_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] = b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpSet_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] = b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpSet_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] = b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpSet_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] = b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpMod_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] %= b.apply$mcII$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpMod_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] %= b.apply$mcID$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpMod_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] %= b.apply$mcIF$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpMod_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] %= b.apply$mcIJ$sp(index$macro$2);
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Int_OpPow_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] = PowImplicits$.MODULE$.IntPow(ad[aoff]).pow(b.apply$mcII$sp(index$macro$2));
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Double_OpPow_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] = PowImplicits$.MODULE$.DoublePow(ad[aoff]).pow(b.apply$mcID$sp(index$macro$2));
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Float_OpPow_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] = PowImplicits$.MODULE$.FloatPow(ad[aoff]).pow(b.apply$mcIF$sp(index$macro$2));
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_Long_OpPow_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               ad[aoff] = PowImplicits$.MODULE$.LongPow(ad[aoff]).pow(b.apply$mcIJ$sp(index$macro$2));
               aoff += a.stride();
            }

         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(b)) {
               b.activeIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$bindingMissing$1(check$ifrefutable$1))).foreach((x$1) -> {
                  $anonfun$bindingMissing$2(a, x$1);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update$mcI$sp(index$macro$2, a.apply$mcI$sp(index$macro$2) + b.apply$mcII$sp(index$macro$2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$bindingMissing$1(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$bindingMissing$2(final DenseVector a$1, final Tuple2 x$1) {
            if (x$1 != null) {
               int i = x$1._1$mcI$sp();
               int v = x$1._2$mcI$sp();
               a$1.update$mcI$sp(i, a$1.apply$mcI$sp(i) + v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$1);
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(b)) {
               b.activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$bindingMissing$3(check$ifrefutable$2))).foreach((x$2) -> {
                  $anonfun$bindingMissing$4(a, x$2);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update$mcD$sp(index$macro$2, a.apply$mcD$sp(index$macro$2) + b.apply$mcID$sp(index$macro$2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$bindingMissing$3(final Tuple2 check$ifrefutable$2) {
            boolean var1;
            if (check$ifrefutable$2 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$bindingMissing$4(final DenseVector a$2, final Tuple2 x$2) {
            if (x$2 != null) {
               int i = x$2._1$mcI$sp();
               double v = x$2._2$mcD$sp();
               a$2.update$mcD$sp(i, a$2.apply$mcD$sp(i) + v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$2);
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(b)) {
               b.activeIterator().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$bindingMissing$5(check$ifrefutable$3))).foreach((x$3) -> {
                  $anonfun$bindingMissing$6(a, x$3);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update$mcF$sp(index$macro$2, a.apply$mcF$sp(index$macro$2) + b.apply$mcIF$sp(index$macro$2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$bindingMissing$5(final Tuple2 check$ifrefutable$3) {
            boolean var1;
            if (check$ifrefutable$3 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$bindingMissing$6(final DenseVector a$3, final Tuple2 x$3) {
            if (x$3 != null) {
               int i = x$3._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$3._2());
               a$3.update$mcF$sp(i, a$3.apply$mcF$sp(i) + v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$3);
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(b)) {
               b.activeIterator().withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$bindingMissing$7(check$ifrefutable$4))).foreach((x$4) -> {
                  $anonfun$bindingMissing$8(a, x$4);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update$mcJ$sp(index$macro$2, a.apply$mcJ$sp(index$macro$2) + b.apply$mcIJ$sp(index$macro$2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$bindingMissing$7(final Tuple2 check$ifrefutable$4) {
            boolean var1;
            if (check$ifrefutable$4 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$bindingMissing$8(final DenseVector a$4, final Tuple2 x$4) {
            if (x$4 != null) {
               int i = x$4._1$mcI$sp();
               long v = x$4._2$mcJ$sp();
               a$4.update$mcJ$sp(i, a$4.apply$mcJ$sp(i) + v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$4);
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            int[] ad = a.data$mcI$sp();
            int aoff = a.offset();
            if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(b)) {
               b.activeIterator().withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$bindingMissing$9(check$ifrefutable$5))).foreach((x$5) -> {
                  $anonfun$bindingMissing$10(a, x$5);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update$mcI$sp(index$macro$2, a.apply$mcI$sp(index$macro$2) - b.apply$mcII$sp(index$macro$2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$bindingMissing$9(final Tuple2 check$ifrefutable$5) {
            boolean var1;
            if (check$ifrefutable$5 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$bindingMissing$10(final DenseVector a$5, final Tuple2 x$5) {
            if (x$5 != null) {
               int i = x$5._1$mcI$sp();
               int v = x$5._2$mcI$sp();
               a$5.update$mcI$sp(i, a$5.apply$mcI$sp(i) - v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$5);
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            double[] ad = a.data$mcD$sp();
            int aoff = a.offset();
            if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(b)) {
               b.activeIterator().withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$bindingMissing$11(check$ifrefutable$6))).foreach((x$6) -> {
                  $anonfun$bindingMissing$12(a, x$6);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update$mcD$sp(index$macro$2, a.apply$mcD$sp(index$macro$2) - b.apply$mcID$sp(index$macro$2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$bindingMissing$11(final Tuple2 check$ifrefutable$6) {
            boolean var1;
            if (check$ifrefutable$6 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$bindingMissing$12(final DenseVector a$6, final Tuple2 x$6) {
            if (x$6 != null) {
               int i = x$6._1$mcI$sp();
               double v = x$6._2$mcD$sp();
               a$6.update$mcD$sp(i, a$6.apply$mcD$sp(i) - v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$6);
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            float[] ad = a.data$mcF$sp();
            int aoff = a.offset();
            if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(b)) {
               b.activeIterator().withFilter((check$ifrefutable$7) -> BoxesRunTime.boxToBoolean($anonfun$bindingMissing$13(check$ifrefutable$7))).foreach((x$7) -> {
                  $anonfun$bindingMissing$14(a, x$7);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update$mcF$sp(index$macro$2, a.apply$mcF$sp(index$macro$2) - b.apply$mcIF$sp(index$macro$2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$bindingMissing$13(final Tuple2 check$ifrefutable$7) {
            boolean var1;
            if (check$ifrefutable$7 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$bindingMissing$14(final DenseVector a$7, final Tuple2 x$7) {
            if (x$7 != null) {
               int i = x$7._1$mcI$sp();
               float v = BoxesRunTime.unboxToFloat(x$7._2());
               a$7.update$mcF$sp(i, a$7.apply$mcF$sp(i) - v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$7);
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_Vector_ExpandOps$_setter_$impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub_$eq(new BinaryUpdateRegistry() {
         private HashMap ops;
         private ConcurrentHashMap cache;

         // $FF: synthetic method
         public void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public void multipleOptions(final Object a, final Object b, final Map m) {
            BinaryUpdateRegistry.multipleOptions$(this, a, b, m);
         }

         public void apply(final Object a, final Object b) {
            BinaryUpdateRegistry.apply$(this, a, b);
         }

         public UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
            return BinaryUpdateRegistry.register$(this, op, cA, cB);
         }

         public void register(final Class a, final Class b, final Object op) {
            MMRegistry2.register$(this, a, b, op);
         }

         public Map resolve(final Class a, final Class b) {
            return MMRegistry2.resolve$(this, a, b);
         }

         public MapView selectBestOption(final Map options) {
            return MMRegistry2.selectBestOption$(this, options);
         }

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public HashMap ops() {
            return this.ops;
         }

         public ConcurrentHashMap cache() {
            return this.cache;
         }

         public void breeze$generic$MMRegistry2$_setter_$ops_$eq(final HashMap x$1) {
            this.ops = x$1;
         }

         public void breeze$generic$MMRegistry2$_setter_$cache_$eq(final ConcurrentHashMap x$1) {
            this.cache = x$1;
         }

         public void bindingMissing(final DenseVector a, final Vector b) {
            long[] ad = a.data$mcJ$sp();
            int aoff = a.offset();
            if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(b)) {
               b.activeIterator().withFilter((check$ifrefutable$8) -> BoxesRunTime.boxToBoolean($anonfun$bindingMissing$15(check$ifrefutable$8))).foreach((x$8) -> {
                  $anonfun$bindingMissing$16(a, x$8);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  a.update$mcJ$sp(index$macro$2, a.apply$mcJ$sp(index$macro$2) - b.apply$mcIJ$sp(index$macro$2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$bindingMissing$15(final Tuple2 check$ifrefutable$8) {
            boolean var1;
            if (check$ifrefutable$8 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$bindingMissing$16(final DenseVector a$8, final Tuple2 x$8) {
            if (x$8 != null) {
               int i = x$8._1$mcI$sp();
               long v = x$8._2$mcJ$sp();
               a$8.update$mcJ$sp(i, a$8.apply$mcJ$sp(i) - v);
               BoxedUnit var2 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$8);
            }
         }

         public {
            MMRegistry2.$init$(this);
            BinaryUpdateRegistry.$init$(this);
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_Vector_ExpandOps.this.impl_Op_InPlace_V_V_Idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            Statics.releaseFence();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }
}
