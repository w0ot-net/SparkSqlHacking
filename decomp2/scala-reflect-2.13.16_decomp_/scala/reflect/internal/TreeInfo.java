package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple5;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedLinearSeqOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Position;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015}d\u0001CA\u0017\u0003_\t\t!!\u0010\t\u000f\u0005\u001d\u0003\u0001\"\u0001\u0002J!I\u0011q\n\u0001C\u0002\u001b\u0005\u0011\u0011\u000b\u0005\b\u00033\u0002A\u0011AA.\u0011\u001d\t)\b\u0001C\u0001\u0003oBq!a\u001f\u0001\t\u0003\ti\bC\u0004\u0002\u0004\u0002!\t!!\"\t\u000f\u0005%\u0005\u0001\"\u0001\u0002\f\"9\u00111\u0013\u0001\u0005\u0002\u0005U\u0005bBAN\u0001\u0011%\u0011Q\u0014\u0005\b\u0003[\u0003A\u0011BAX\u0011\u001d\ty\f\u0001C\u0005\u0003\u0003Dq!!2\u0001\t\u0003\t9\rC\u0004\u0002P\u0002!I!!5\t\u000f\u0005u\u0007\u0001\"\u0001\u0002`\"9\u00111\u001d\u0001\u0005\u0002\u0005\u0015\bbBAu\u0001\u0011\u0005\u00111\u001e\u0005\b\u0003_\u0004A\u0011AAy\u0011\u001d\t)\u0010\u0001C\u0003\u0003oDqA!\u0003\u0001\t\u000b\u0011Y\u0001C\u0004\u0003\u0012\u0001!\tAa\u0005\t\u000f\tM\u0003\u0001\"\u0001\u0003V!9!q\r\u0001\u0005\u0002\t%\u0004b\u0002B7\u0001\u0011\u0005!q\u000e\u0005\b\u0005g\u0002A\u0011\u0001B;\u0011\u001d\u0011I\b\u0001C\u0001\u0005wBqAa \u0001\t\u0003\u0011\t\tC\u0004\u0003\u0012\u0002!\tAa%\t\u000f\t]\u0005\u0001\"\u0001\u0003\u001a\"9!Q\u0014\u0001\u0005\u0002\t}\u0005b\u0002BR\u0001\u0011\u0005!Q\u0015\u0005\b\u0005S\u0003A\u0011\u0001BV\u0011\u001d\u0011y\u000b\u0001C\u0003\u0005c;qAa.\u0001\u0011\u0003\u0011ILB\u0004\u0003<\u0002A\tA!0\t\u000f\u0005\u001d#\u0005\"\u0001\u0003@\"9!\u0011\u0019\u0012\u0005\u0002\t\r\u0007b\u0002Bg\u0001\u0011\u0005!q\u001a\u0005\b\u0005'\u0004A\u0011\u0001Bk\u0011\u001d\u0011I\u000e\u0001C\u0001\u00057DqA!9\u0001\t\u0003\u0011\u0019\u000fC\u0005\u0003h\u0002!\t!a\f\u0003j\"I!Q\u001e\u0001\u0005\u0002\u0005=\"q\u001e\u0005\n\u0007\u0013\u0001A\u0011AA\u0018\u0007\u0017A\u0011ba\u0006\u0001\t\u0003\tyc!\u0007\t\u000f\r\u0005\u0002\u0001\"\u0001\u0004$!91\u0011\u0006\u0001\u0005\u0002\r-\u0002bBB\u0018\u0001\u0011\u00051\u0011\u0007\u0005\b\u0007w\u0001A\u0011AB\u001f\u0011\u001d\u0019\u0019\u0005\u0001C\u0001\u0007\u000bBqa!\u0013\u0001\t\u0003\u0019Y\u0005C\u0004\u0004P\u0001!\ta!\u0015\t\u000f\rU\u0003\u0001\"\u0001\u0004X!91Q\f\u0001\u0005\u0002\r}\u0003bBB2\u0001\u0011\u00051Q\r\u0005\b\u0007S\u0002A\u0011AB6\u0011\u001d\u0019\t\b\u0001C\u0003\u0007gBqaa\u001e\u0001\t\u0003\u0019IhB\u0004\u0004~\u0001A\taa \u0007\u000f\r\u0005\u0005\u0001#\u0001\u0004\u0004\"9\u0011qI\u001e\u0005\u0002\r\u0015\u0005b\u0002Baw\u0011\u00051q\u0011\u0005\b\u0007#\u0003A\u0011ABJ\u0011\u001d\u0019y\n\u0001C\u0001\u0007CCqaa*\u0001\t\u0003\u0019I\u000bC\u0004\u0004.\u0002!\taa,\t\u000f\rM\u0006\u0001\"\u0001\u00046\"91\u0011\u0019\u0001\u0005\n\r\r\u0007bBBd\u0001\u0011\u00051\u0011\u001a\u0005\b\u0007\u001b\u0004A\u0011ABh\u0011\u001d\u0019\u0019\u000e\u0001C\u0001\u0007+Dqa!7\u0001\t\u0003\u0019Y\u000eC\u0004\u0004`\u0002!Ia!9\t\u000f\r\u0015\b\u0001\"\u0001\u0004h\"911\u001e\u0001\u0005\u0002\r5\bbBBy\u0001\u0011\u001511\u001f\u0005\b\u0007w\u0004A\u0011AB\u007f\u0011\u001d!\t\u0001\u0001C\u0001\t\u0007Aq\u0001\"\u0004\u0001\t\u0003!y\u0001C\u0005\u0005\u0014\u0001\u0011\r\u0011\"\u0002\u0005\u0016!AA1\u0004\u0001!\u0002\u001b!9\u0002C\u0004\u0005\u001e\u0001!\t\u0001b\b\t\u000f\u0011\r\u0002\u0001\"\u0001\u0005&!9A\u0011\u0006\u0001\u0005\u0002\u0011-\u0002b\u0002C\u0018\u0001\u0011\u0005A\u0011\u0007\u0004\u0007\tk\u0001!\u0001b\u000e\t\u0015\u0005\u0015TK!b\u0001\n\u0003!I\u0004\u0003\u0006\u0005<U\u0013\t\u0011)A\u0005\u0003OBq!a\u0012V\t\u0003!i\u0004C\u0004\u0005DU#\t\u0001\"\u000f\t\u000f\u0011\u0015S\u000b\"\u0001\u0005:!9AqI+\u0005\u0002\u0011%\u0003b\u0002C&+\u0012\u0005AQ\n\u0005\b\t#\u0002AQ\u0001C*\u0011\u001d!9\u0006\u0001C\u0003\t3:q\u0001b\u0018\u0001\u0011\u0003!\tGB\u0004\u00056\u0001A\t\u0001b\u0019\t\u000f\u0005\u001d\u0003\r\"\u0001\u0005f!9Aq\r1\u0005\u0002\u0011%\u0004b\u0002BaA\u0012\u0005AQ\u000e\u0005\b\u0005\u0003\u0004G\u0011\u0001C>\u000f\u001d!y\b\u0001E\u0001\t\u00033q\u0001b!\u0001\u0011\u0003!)\tC\u0004\u0002H\u0019$\t\u0001b\"\t\u000f\t\u0005g\r\"\u0001\u0005\n\"9Aq\u0012\u0001\u0005\u0006\u0011Eua\u0002CS\u0001!\u0005Aq\u0015\u0004\b\tS\u0003\u0001\u0012\u0001CV\u0011\u001d\t9e\u001bC\u0001\t[CqA!1l\t\u0003!y\u000bC\u0004\u00056\u0002!\t\u0001b.\t\u000f\u0011m\u0006\u0001\"\u0001\u0005>\u001a9A\u0011\u0019\u0001\u0002\u0002\u0011\r\u0007bBA$a\u0012\u0005Aq\u0019\u0005\b\t#\u0004h\u0011\u0003Cj\u0011\u001d\u0011\t\r\u001dC\u0001\t/<q\u0001b7\u0001\u0011\u0003!iNB\u0004\u0005`\u0002A\t\u0001\"9\t\u000f\u0005\u001dS\u000f\"\u0001\u0005f\"9A\u0011[;\u0005\u0012\u0011\u001dxa\u0002Cv\u0001!\u0005AQ\u001e\u0004\b\t_\u0004\u0001\u0012\u0001Cy\u0011\u001d\t9%\u001fC\u0001\tgDq\u0001\"5z\t#!)\u0010C\u0004\u0005z\u0002!\t\u0001b?\b\u000f\u0011}\b\u0001#\u0003\u0006\u0002\u00199Q1\u0001\u0001\t\n\u0015\u0015\u0001bBA$}\u0012\u0005Qq\u0001\u0005\b\u0005\u0003tH\u0011AC\u0005\r\u0019)y\u0001\u0001\u0001\u0006\u0012!YQ1CA\u0002\u0005\u0003\u0005\u000b\u0011BC\u000b\u0011!\t9%a\u0001\u0005\u0002\u0015m\u0001\u0002\u0003Ba\u0003\u0007!\t!\"\t\b\u000f\u00155\u0002\u0001#\u0001\u00060\u00199Q\u0011\u0007\u0001\t\u0002\u0015M\u0002\u0002CA$\u0003\u001b!\t!\"\u000e\b\u000f\u0015]\u0002\u0001#\u0001\u0006:\u00199Q1\b\u0001\t\u0002\u0015u\u0002\u0002CA$\u0003'!\t!b\u0010\b\u000f\u0015\u0005\u0003\u0001#\u0001\u0006D\u00199QQ\t\u0001\t\u0002\u0015\u001d\u0003\u0002CA$\u00033!\t!\"\u0013\b\u000f\u0015-\u0003\u0001#\u0001\u0006N\u00199Qq\n\u0001\t\u0002\u0015E\u0003\u0002CA$\u0003?!\t!b\u0015\t\u0011\u0015U\u0013q\u0004C\u0005\u000b/B\u0001B!1\u0002 \u0011\u0005QQ\f\u0005\b\u000bS\u0002AQAC6\u0011\u001d)\t\b\u0001C\u0001\u000bgBq!b\u001e\u0001\t\u000b)IH\u0001\u0005Ue\u0016,\u0017J\u001c4p\u0015\u0011\t\t$a\r\u0002\u0011%tG/\u001a:oC2TA!!\u000e\u00028\u00059!/\u001a4mK\u000e$(BAA\u001d\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001AA !\u0011\t\t%a\u0011\u000e\u0005\u0005]\u0012\u0002BA#\u0003o\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\b\u0006\u0002\u0002LA\u0019\u0011Q\n\u0001\u000e\u0005\u0005=\u0012AB4m_\n\fG.\u0006\u0002\u0002TA!\u0011QJA+\u0013\u0011\t9&a\f\u0003\u0017MKXNY8m)\u0006\u0014G.Z\u0001\u0017SN$Um\u00197be\u0006$\u0018n\u001c8PeRK\b/\u001a#fMR!\u0011QLA2!\u0011\t\t%a\u0018\n\t\u0005\u0005\u0014q\u0007\u0002\b\u0005>|G.Z1o\u0011\u001d\t)g\u0001a\u0001\u0003O\nA\u0001\u001e:fKB!\u0011\u0011NA7\u001d\r\tYGA\u0007\u0002\u0001%!\u0011qNA9\u0005\u0011!&/Z3\n\t\u0005M\u0014q\u0006\u0002\u0006)J,Wm]\u0001\u0012SNLe\u000e^3sM\u0006\u001cW-T3nE\u0016\u0014H\u0003BA/\u0003sBq!!\u001a\u0005\u0001\u0004\t9'\u0001\rjg\u000e{gn\u001d;sk\u000e$xN],ji\"$UMZ1vYR$B!!\u0018\u0002\u0000!9\u0011\u0011Q\u0003A\u0002\u0005\u001d\u0014!\u0001;\u0002\u0013%\u001c\b+\u001e:f\t\u00164G\u0003BA/\u0003\u000fCq!!\u001a\u0007\u0001\u0004\t9'\u0001\u0004jgB\u000bG\u000f\u001b\u000b\u0007\u0003;\ni)a$\t\u000f\u0005\u0015t\u00011\u0001\u0002h!9\u0011\u0011S\u0004A\u0002\u0005u\u0013!D1mY><hk\u001c7bi&dW-\u0001\njgN#\u0018M\u00197f\u0013\u0012,g\u000e^5gS\u0016\u0014HCBA/\u0003/\u000bI\nC\u0004\u0002f!\u0001\r!a\u001a\t\u000f\u0005E\u0005\u00021\u0001\u0002^\u0005)1/_7PWR!\u0011QLAP\u0011\u001d\t\t+\u0003a\u0001\u0003G\u000b1a]=n!\u0011\tI'!*\n\t\u0005\u001d\u0016\u0011\u0016\u0002\u0007'fl'm\u001c7\n\t\u0005-\u0016q\u0006\u0002\b'fl'm\u001c7t\u0003\u0019!\u0018\u0010]3PWR!\u0011QLAY\u0011\u001d\t\u0019L\u0003a\u0001\u0003k\u000b!\u0001\u001e9\u0011\t\u0005%\u0014qW\u0005\u0005\u0003s\u000bYL\u0001\u0003UsB,\u0017\u0002BA_\u0003_\u0011Q\u0001V=qKN\f\u0011#[:V]\u000eDWmY6fIN#\u0018M\u00197f)\u0011\ti&a1\t\u000f\u0005\u00056\u00021\u0001\u0002$\u0006\u0001\u0012n]*uC\ndW-T3nE\u0016\u0014xJ\u001a\u000b\t\u0003;\nI-a3\u0002N\"9\u0011\u0011\u0015\u0007A\u0002\u0005\r\u0006bBA3\u0019\u0001\u0007\u0011q\r\u0005\b\u0003#c\u0001\u0019AA/\u00035I7o\u0015;bE2,\u0017\nZ3oiR1\u0011QLAj\u00037Dq!!\u001a\u000e\u0001\u0004\t)\u000e\u0005\u0003\u0002j\u0005]\u0017\u0002BAm\u0003c\u0012Q!\u00133f]RDq!!%\u000e\u0001\u0004\ti&A\biCN4v\u000e\\1uS2,G+\u001f9f)\u0011\ti&!9\t\u000f\u0005\u0015d\u00021\u0001\u0002h\u0005\u0019\u0012\rZ7jiN$\u0016\u0010]3TK2,7\r^5p]R!\u0011QLAt\u0011\u001d\t)g\u0004a\u0001\u0003O\n\u0011$[:Ti\u0006\u0014G.Z%eK:$\u0018NZ5feB\u000bG\u000f^3s]R!\u0011QLAw\u0011\u001d\t)\u0007\u0005a\u0001\u0003O\na#[:Rk\u0006d\u0017NZ5feN\u000bg-\u001a+p\u000b2LG-\u001a\u000b\u0005\u0003;\n\u0019\u0010C\u0004\u0002fE\u0001\r!a\u001a\u0002%%\u001cX\t\u001f9s'\u00064W\rV8J]2Lg.\u001a\u000b\u0005\u0003;\nI\u0010C\u0004\u0002fI\u0001\r!a\u001a)\u0007I\ti\u0010\u0005\u0003\u0002\u0000\n\u0015QB\u0001B\u0001\u0015\u0011\u0011\u0019!a\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003\b\t\u0005!a\u0002;bS2\u0014XmY\u0001\u001dSN\u0004VO]3FqB\u0014hi\u001c:XCJt\u0017N\\4QkJ\u0004xn]3t)\u0011\tiF!\u0004\t\u000f\u0005\u00154\u00031\u0001\u0002h!\u001a1#!@\u0002-5\f\u0007/T3uQ>$\u0007+\u0019:b[N\fe\u000eZ!sON,BA!\u0006\u0003,Q1!q\u0003B$\u0005\u001b\"BA!\u0007\u0003>A1!1\u0004B\u0011\u0005OqA!!\u0011\u0003\u001e%!!qDA\u001c\u0003\u001d\u0001\u0018mY6bO\u0016LAAa\t\u0003&\t!A*[:u\u0015\u0011\u0011y\"a\u000e\u0011\t\t%\"1\u0006\u0007\u0001\t\u001d\u0011i\u0003\u0006b\u0001\u0005_\u0011\u0011AU\t\u0005\u0005c\u00119\u0004\u0005\u0003\u0002B\tM\u0012\u0002\u0002B\u001b\u0003o\u0011qAT8uQ&tw\r\u0005\u0003\u0002B\te\u0012\u0002\u0002B\u001e\u0003o\u00111!\u00118z\u0011\u001d\u0011y\u0004\u0006a\u0001\u0005\u0003\n\u0011A\u001a\t\u000b\u0003\u0003\u0012\u0019%a)\u0002h\t\u001d\u0012\u0002\u0002B#\u0003o\u0011\u0011BR;oGRLwN\u001c\u001a\t\u000f\t%C\u00031\u0001\u0003L\u00051\u0001/\u0019:b[N\u0004bAa\u0007\u0003\"\u0005\r\u0006b\u0002B()\u0001\u0007!\u0011K\u0001\u0005CJ<7\u000f\u0005\u0004\u0003\u001c\t\u0005\u0012qM\u0001\u0019M>\u0014X-Y2i\u001b\u0016$\bn\u001c3QCJ\fW.\u00118e\u0003J<GC\u0002B,\u0005G\u0012)\u0007\u0006\u0003\u0002^\te\u0003b\u0002B +\u0001\u0007!1\f\t\u000b\u0003\u0003\u0012\u0019%a)\u0002h\tu\u0003\u0003BA!\u0005?JAA!\u0019\u00028\t!QK\\5u\u0011\u001d\u0011I%\u0006a\u0001\u0005\u0017BqAa\u0014\u0016\u0001\u0004\u0011\t&\u0001\u000ejg\u001a+hn\u0019;j_:l\u0015n]:j]\u001e\u0004\u0016M]1n)f\u0004X\r\u0006\u0003\u0002^\t-\u0004bBA3-\u0001\u0007\u0011qM\u0001\"SN\u0004\u0016M\u001d;jC24UO\\2uS>tW*[:tS:<\u0007+\u0019:b[RK\b/\u001a\u000b\u0005\u0003;\u0012\t\bC\u0004\u0002f]\u0001\r!a\u001a\u0002\u001d5\f\u0017PQ3WCJ<U\r\u001e;feR!\u0011Q\fB<\u0011\u001d\t\t\u000b\u0007a\u0001\u0003G\u000b!#[:WCJL\u0017M\u00197f\u001fJ<U\r\u001e;feR!\u0011Q\fB?\u0011\u001d\t)'\u0007a\u0001\u0003O\n!B\\8GS\u0016dGMR8s)\u0019\tiFa!\u0003\u000e\"9!Q\u0011\u000eA\u0002\t\u001d\u0015A\u0001<e!\u0011\tIG!#\n\t\t-\u0015\u0011\u000f\u0002\u0007-\u0006dG)\u001a4\t\u000f\t=%\u00041\u0001\u0002$\u0006)qn\u001e8fe\u0006y\u0011n\u001d#fM\u0006,H\u000e^$fiR,'\u000f\u0006\u0003\u0002^\tU\u0005bBA37\u0001\u0007\u0011qM\u0001\u0011SN\u001cV\r\u001c4D_:\u001cHO]\"bY2$B!!\u0018\u0003\u001c\"9\u0011Q\r\u000fA\u0002\u0005\u001d\u0014!E5t'V\u0004XM]\"p]N$(oQ1mYR!\u0011Q\fBQ\u0011\u001d\t)'\ba\u0001\u0003O\n\u0001#[:UQ&\u001cH+\u001f9f%\u0016\u001cX\u000f\u001c;\u0015\t\u0005u#q\u0015\u0005\b\u0003Kr\u0002\u0019AA4\u0003Q\u0019HO]5q\u001d\u0006lW\rZ!qa2L(\t\\8dWR!\u0011q\rBW\u0011\u001d\t)g\ba\u0001\u0003O\n\u0011b\u001d;sSB\u001c\u0015m\u001d;\u0015\t\u0005\u001d$1\u0017\u0005\b\u0003K\u0002\u0003\u0019AA4Q\r\u0001\u0013Q`\u0001\n'R\u0014\u0018\u000e]\"bgR\u00042!a\u001b#\u0005%\u0019FO]5q\u0007\u0006\u001cHoE\u0002#\u0003\u007f!\"A!/\u0002\u000fUt\u0017\r\u001d9msR!!Q\u0019Bf!\u0019\t\tEa2\u0002h%!!\u0011ZA\u001c\u0005\u0011\u0019v.\\3\t\u000f\u0005\u0015D\u00051\u0001\u0002h\u00059\u0012n]*fY\u001a|%oU;qKJ\u001cuN\\:ue\u000e\u000bG\u000e\u001c\u000b\u0005\u0003;\u0012\t\u000eC\u0004\u0002f\u0015\u0002\r!a\u001a\u0002!%\u001ch+\u0019:QCR$XM\u001d8EK\u0016\u0004H\u0003BA/\u0005/Dq!!\u001a'\u0001\u0004\t9'\u0001\u0007jgZ\u000b'\u000fU1ui\u0016\u0014h\u000e\u0006\u0003\u0002^\tu\u0007b\u0002BpO\u0001\u0007\u0011qM\u0001\u0004a\u0006$\u0018aD5t\u0019&$XM]1m'R\u0014\u0018N\\4\u0015\t\u0005u#Q\u001d\u0005\b\u0003\u0003C\u0003\u0019AA4\u0003U!W\r^3diRK\b/Z2iK\u000e\\W\r\u001a+sK\u0016$B!!\u0018\u0003l\"9\u0011QM\u0015A\u0002\u0005\u001d\u0014AF;oif\u0004Xm\u00195fG.,G\rV3na2\u0014u\u000eZ=\u0015\t\tE(q \t\u0007\u0005g\u0014i0a\u001a\u000e\u0005\tU(\u0002\u0002B|\u0005s\f\u0011\"[7nkR\f'\r\\3\u000b\t\tm\u0018qG\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B\u0012\u0005kDqa!\u0001+\u0001\u0004\u0019\u0019!A\u0003uK6\u0004H\u000e\u0005\u0003\u0002j\r\u0015\u0011\u0002BB\u0004\u0003c\u0012\u0001\u0002V3na2\fG/Z\u0001\u0017k:$\u0018\u0010]3dQ\u0016\u001c7.\u001a3CY>\u001c7NQ8esR!!\u0011_B\u0007\u0011\u001d\u0019ya\u000ba\u0001\u0007#\tQA\u00197pG.\u0004B!!\u001b\u0004\u0014%!1QCA9\u0005\u0015\u0011En\\2l\u0003U)h\u000e^=qK\u000eDWmY6fIR\u0013X-\u001a\"pIf$bA!=\u0004\u001c\ru\u0001bBA3Y\u0001\u0007\u0011q\r\u0005\b\u0007?a\u0003\u0019\u0001B)\u0003\u0015!(m\u001c3z\u0003A1\u0017N]:u\u0007>t7\u000f\u001e:vGR|'\u000f\u0006\u0003\u0002h\r\u0015\u0002bBB\u0014[\u0001\u0007!\u0011K\u0001\u0006gR\fGo]\u0001\u0015M&\u00148\u000f^\"p]N$(/^2u_J\f%oZ:\u0015\t\tE3Q\u0006\u0005\b\u0007Oq\u0003\u0019\u0001B)\u0003Q1\u0017N]:u\u0007>t7\u000f\u001e:vGR|'/T8egR!11GB\u001d!\u0011\tIg!\u000e\n\t\r]\u0012\u0011\u000f\u0002\n\u001b>$\u0017NZ5feNDqaa\n0\u0001\u0004\u0011\t&\u0001\bqe\u0016\u001cV\u000f]3s\r&,G\u000eZ:\u0015\t\r}2\u0011\t\t\u0007\u00057\u0011\tCa\"\t\u000f\r\u001d\u0002\u00071\u0001\u0003R\u0005A\u0002.Y:V]RL\b/\u001a3Qe\u0016\u001cV\u000f]3s\r&,G\u000eZ:\u0015\t\u0005u3q\t\u0005\b\u0007O\t\u0004\u0019\u0001B)\u0003)I7/R1sYf$UM\u001a\u000b\u0005\u0003;\u001ai\u0005C\u0004\u0002fI\u0002\r!a\u001a\u0002\u001b%\u001cX)\u0019:msZ\u000bG\u000eR3g)\u0011\tifa\u0015\t\u000f\u0005\u00154\u00071\u0001\u0002h\u0005\u0019\u0012n\u001d*fa\u0016\fG/\u001a3QCJ\fW\u000eV=qKR!\u0011QLB-\u0011\u001d\u0019Y\u0006\u000ea\u0001\u0003O\n1\u0001\u001e9u\u0003EI7OQ=OC6,\u0007+\u0019:b[RK\b/\u001a\u000b\u0005\u0003;\u001a\t\u0007C\u0004\u0004\\U\u0002\r!a\u001a\u00023\u0005\u001c8/[4o[\u0016tG\u000fV8NCf\u0014WMT1nK\u0012\f%o\u001a\u000b\u0005\u0003O\u001a9\u0007C\u0004\u0002fY\u0002\r!a\u001a\u0002%%\u001c8k^5uG\"\feN\\8uCRLwN\u001c\u000b\u0005\u0003;\u001ai\u0007C\u0004\u0004p]\u0002\r!!.\u0002\u0007Q\u0004X-\u0001\u0007nCf\u0014U\rV=qKB\u000bG\u000f\u0006\u0003\u0002^\rU\u0004bBA3q\u0001\u0007\u0011qM\u0001\u0012SN<\u0016\u000e\u001c3dCJ$7\u000b^1s\u0003J<G\u0003BA/\u0007wBq!!\u001a:\u0001\u0004\t9'A\bXS2$7-\u0019:e'R\f'/\u0011:h!\r\tYg\u000f\u0002\u0010/&dGmY1sIN#\u0018M]!sON\u00191(a\u0010\u0015\u0005\r}D\u0003BBE\u0007\u001f\u0003b!!\u0011\u0004\f\u0006\u001d\u0014\u0002BBG\u0003o\u0011aa\u00149uS>t\u0007bBA3{\u0001\u0007\u0011qM\u0001\u000fif\u0004X\rU1sC6,G/\u001a:t)\u0011\u0019)j!(\u0011\r\tm!\u0011EBL!\u0011\tIg!'\n\t\rm\u0015\u0011\u000f\u0002\b)f\u0004X\rR3g\u0011\u001d\t)G\u0010a\u0001\u0003O\nQ#[:XS2$7-\u0019:e'R\f'/\u0011:h\u0019&\u001cH\u000f\u0006\u0003\u0002^\r\r\u0006bBBS\u007f\u0001\u0007!\u0011K\u0001\u0006iJ,Wm]\u0001\u000eSN<\u0016\u000e\u001c3dCJ$\u0017I]4\u0015\t\u0005u31\u0016\u0005\b\u0003K\u0002\u0005\u0019AA4\u0003II7oV5mI\u000e\f'\u000fZ*uCJ$\u0016\u0010]3\u0015\t\u0005u3\u0011\u0017\u0005\b\u0003K\n\u0005\u0019AA4\u00035I7\u000fR3gCVdGoQ1tKR!\u0011QLB\\\u0011\u001d\u0019IL\u0011a\u0001\u0007w\u000bAa\u00193fMB!\u0011\u0011NB_\u0013\u0011\u0019y,!\u001d\u0003\u000f\r\u000b7/\u001a#fM\u0006Y\u0001.Y:O_NKXNY8m)\u0011\tif!2\t\u000f\u0005\u00055\t1\u0001\u0002h\u00051\u0012n]*z]RDW\r^5d\t\u00164\u0017-\u001e7u\u0007\u0006\u001cX\r\u0006\u0003\u0002^\r-\u0007bBB]\t\u0002\u000711X\u0001\u0011G\u0006$8\r[3t)\"\u0014xn^1cY\u0016$B!!\u0018\u0004R\"91\u0011X#A\u0002\rm\u0016aD5t'ftG\u000f[3uS\u000e\u001c\u0015m]3\u0015\t\u0005u3q\u001b\u0005\b\u0007s3\u0005\u0019AB^\u0003-I7oQ1uG\"\u001c\u0015m]3\u0015\t\u0005u3Q\u001c\u0005\b\u0007s;\u0005\u0019AB^\u0003EI7oU5na2,G\u000b\u001b:po\u0006\u0014G.\u001a\u000b\u0005\u0003;\u001a\u0019\u000fC\u0004\u00024\"\u0003\r!!.\u0002\u001b%\u001cx)^1sI\u0016$7)Y:f)\u0011\tif!;\t\u000f\re\u0016\n1\u0001\u0004<\u0006\u0001\u0012n]*fcV,gnY3WC2,X\r\u001a\u000b\u0005\u0003;\u001ay\u000fC\u0004\u0002f)\u0003\r!a\u001a\u0002\rUt'-\u001b8e)\u0011\t9g!>\t\u000f\r]8\n1\u0001\u0002h\u0005\t\u0001\u0010K\u0002L\u0003{\fa![:Ti\u0006\u0014H\u0003BA/\u0007\u007fDqaa>M\u0001\u0004\t9'A\u000bfM\u001a,7\r^5wKB\u000bG\u000f^3s]\u0006\u0013\u0018\u000e^=\u0015\t\u0011\u0015A1\u0002\t\u0005\u0003\u0003\"9!\u0003\u0003\u0005\n\u0005]\"aA%oi\"9!qJ'A\u0002\tE\u0013\u0001\u00064mCR$XM\\3e!\u0006$H/\u001a:o\u0003J<7\u000f\u0006\u0003\u0003R\u0011E\u0001b\u0002B(\u001d\u0002\u0007!\u0011K\u0001\u0011'fsE\u000bS0D\u0003N+uL\u0012'B\u000fN+\"\u0001b\u0006\u0010\u0005\u0011ead\u0001\u0011\t\u0001\u0005\t2+\u0017(U\u0011~\u001b\u0015iU#`\r2\u000bui\u0015\u0011\u0002#%\u001c8+\u001f8uQ\u000e\u000b7/Z*z[\n|G\u000e\u0006\u0003\u0002^\u0011\u0005\u0002bBAQ#\u0002\u0007\u00111U\u0001\u0013Q\u0006\u001c8+\u001f8uQ\u000e\u000b7/Z*z[\n|G\u000e\u0006\u0003\u0002^\u0011\u001d\u0002bBAA%\u0002\u0007\u0011qM\u0001\u000bSN$&/Y5u%\u00164G\u0003BA/\t[Aq!!\u001aT\u0001\u0004\t9'A\biCN,\u0005\u0010\u001d7jG&$XK\\5u)\u0011\ti\u0006b\r\t\u000f\u0005\u0015D\u000b1\u0001\u0002h\t9\u0011\t\u001d9mS\u0016$7cA+\u0002@U\u0011\u0011qM\u0001\u0006iJ,W\r\t\u000b\u0005\t\u007f!\t\u0005E\u0002\u0002lUCq!!\u001aY\u0001\u0004\t9'\u0001\u0004dC2dW-Z\u0001\u0005G>\u0014X-A\u0003uCJ<7/\u0006\u0002\u0003R\u0005)\u0011M]4tgV\u0011Aq\n\t\u0007\u00057\u0011\tC!\u0015\u0002\u001d\u0011L7o]3di\u0006\u0003\b\u000f\\5fIR!Aq\bC+\u0011\u001d\t)'\u0018a\u0001\u0003O\n1\u0002Z5tg\u0016\u001cGoQ8sKR!\u0011q\rC.\u0011\u001d\t)G\u0018a\u0001\u0003OB3AXA\u007f\u0003\u001d\t\u0005\u000f\u001d7jK\u0012\u00042!a\u001ba'\r\u0001\u0017q\b\u000b\u0003\tC\nQ!\u00199qYf$B\u0001b\u0010\u0005l!9\u0011Q\r2A\u0002\u0005\u001dD\u0003\u0002C8\to\u0002b!!\u0011\u0003H\u0012E\u0004CCA!\tg\n9G!\u0015\u0005P%!AQOA\u001c\u0005\u0019!V\u000f\u001d7fg!9A\u0011P2A\u0002\u0011}\u0012aB1qa2LW\r\u001a\u000b\u0005\t_\"i\bC\u0004\u0002f\u0011\u0004\r!a\u001a\u0002\u0017\u0005\u0003\b\u000f\\5dCRLwN\u001c\t\u0004\u0003W2'aC!qa2L7-\u0019;j_:\u001c2AZA )\t!\t\t\u0006\u0003\u0005\f\u00125\u0005CBA!\u0007\u0017#\t\bC\u0004\u0002f!\u0004\r!a\u001a\u00023\u0019L'o\u001d;EK\u001aLg.Z:DY\u0006\u001c8o\u0014:PE*,7\r\u001e\u000b\u0007\u0003;\"\u0019\n\"&\t\u000f\r\u0015\u0016\u000e1\u0001\u0003R!9AqS5A\u0002\u0011e\u0015\u0001\u00028b[\u0016\u0004B!!\u001b\u0005\u001c&!AQ\u0014CP\u0005\u0011q\u0015-\\3\n\t\u0011\u0005\u0016q\u0006\u0002\u0006\u001d\u0006lWm\u001d\u0015\u0004S\u0006u\u0018!C+oCB\u0004H.[3e!\r\tYg\u001b\u0002\n+:\f\u0007\u000f\u001d7jK\u0012\u001c2a[A )\t!9\u000b\u0006\u0003\u0004\n\u0012E\u0006bBA3[\u0002\u0007\u0011q\r\u0015\u0004[\u0006u\u0018\u0001D5t\u0003\n\u001cH+\u001f9f\t\u00164G\u0003BA/\tsCq!!\u001ao\u0001\u0004\t9'\u0001\bjg\u0006c\u0017.Y:UsB,G)\u001a4\u0015\t\u0005uCq\u0018\u0005\b\u0003Kz\u0007\u0019AA4\u0005A\u0019V-\u001a+ie>,x\r\u001b\"m_\u000e\\7/\u0006\u0003\u0005F\u001257c\u00019\u0002@Q\u0011A\u0011\u001a\t\u0006\u0003W\u0002H1\u001a\t\u0005\u0005S!i\rB\u0004\u0005PB\u0014\rAa\f\u0003\u0003Q\u000b1\"\u001e8baBd\u00170S7qYR!A1\u001aCk\u0011\u001d\u00199P\u001da\u0001\u0003O\"B\u0001b3\u0005Z\"91q_:A\u0002\u0005\u001d\u0014AB%t)J,X\rE\u0002\u0002lU\u0014a!S:UeV,7cA;\u0005dB)\u00111\u000e9\u0002^Q\u0011AQ\u001c\u000b\u0005\u0003;\"I\u000fC\u0004\u0004x^\u0004\r!a\u001a\u0002\u000f%\u001bh)\u00197tKB\u0019\u00111N=\u0003\u000f%\u001bh)\u00197tKN\u0019\u0011\u0010b9\u0015\u0005\u00115H\u0003BA/\toDqaa>|\u0001\u0004\t9'\u0001\njg\u0006\u0003\b\u000f\\=Es:\fW.[2OC6,G\u0003BA/\t{Dq\u0001b&}\u0001\u0004!I*\u0001\u000bMSR,'/\u00197OC6,wJ]!eCB$X\r\u001a\t\u0004\u0003Wr(\u0001\u0006'ji\u0016\u0014\u0018\r\u001c(b[\u0016|%/\u00113baR,GmE\u0002\u007f\u0003\u007f!\"!\"\u0001\u0015\t\u0015-QQ\u0002\t\u0007\u0003\u0003\u001aYIa\u000e\t\u0011\u0005\u0015\u0014\u0011\u0001a\u0001\u0003O\u00121\u0004R=oC6L7-\u00119qY&\u001c\u0017\r^5p]\u0016CHO]1di>\u00148\u0003BA\u0002\u0003\u007f\t\u0001B\\1nKR+7\u000f\u001e\t\t\u0003\u0003*9\u0002\"'\u0002^%!Q\u0011DA\u001c\u0005%1UO\\2uS>t\u0017\u0007\u0006\u0003\u0006\u001e\u0015}\u0001\u0003BA6\u0003\u0007A\u0001\"b\u0005\u0002\b\u0001\u0007QQ\u0003\u000b\u0005\u000bG)Y\u0003\u0005\u0004\u0002B\r-UQ\u0005\t\t\u0003\u0003*9#a\u001a\u00038%!Q\u0011FA\u001c\u0005\u0019!V\u000f\u001d7fe!A\u0011QMA\u0005\u0001\u0004\t9'A\u0007Es:\fW.[2Va\u0012\fG/\u001a\t\u0005\u0003W\niAA\u0007Es:\fW.[2Va\u0012\fG/Z\n\u0005\u0003\u001b)i\u0002\u0006\u0002\u00060\u0005\u0011B)\u001f8b[&\u001c\u0017\t\u001d9mS\u000e\fG/[8o!\u0011\tY'a\u0005\u0003%\u0011Kh.Y7jG\u0006\u0003\b\u000f\\5dCRLwN\\\n\u0005\u0003')i\u0002\u0006\u0002\u0006:\u00059B)\u001f8b[&\u001c\u0017\t\u001d9mS\u000e\fG/[8o\u001d\u0006lW\r\u001a\t\u0005\u0003W\nIBA\fEs:\fW.[2BaBd\u0017nY1uS>tg*Y7fIN!\u0011\u0011DC\u000f)\t)\u0019%\u0001\nNC\u000e\u0014x.S7qYJ+g-\u001a:f]\u000e,\u0007\u0003BA6\u0003?\u0011!#T1de>LU\u000e\u001d7SK\u001a,'/\u001a8dKN!\u0011qDA )\t)i%A\u0004sK\u001a\u0004\u0016M\u001d;\u0015\t\u0005\u001dT\u0011\f\u0005\t\u0003K\n\u0019\u00031\u0001\u0002h!\"\u00111EA\u007f)\u0011)y&b\u001a\u0011\r\u0005\u000531RC1!9\t\t%b\u0019\u0002^\u0005u\u00131UAR\u0005#JA!\"\u001a\u00028\t1A+\u001e9mKVB\u0001\"!\u001a\u0002&\u0001\u0007\u0011qM\u0001\u0014SNtU\u000f\u001c7befLeN^8dCRLwN\u001c\u000b\u0005\u0003;*i\u0007\u0003\u0005\u0002f\u0005\u001d\u0002\u0019AA4Q\u0011\t9#!@\u0002%%\u001cX*Y2s_\u0006\u0003\b\u000f\\5dCRLwN\u001c\u000b\u0005\u0003;*)\b\u0003\u0005\u0002f\u0005%\u0002\u0019AA4\u0003eI7/T1de>\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8Pe\ncwnY6\u0015\t\u0005uS1\u0010\u0005\t\u0003K\nY\u00031\u0001\u0002h!\"\u00111FA\u007f\u0001"
)
public abstract class TreeInfo {
   private volatile StripCast$ StripCast$module;
   private volatile WildcardStarArg$ WildcardStarArg$module;
   private volatile Applied$ Applied$module;
   private volatile Application$ Application$module;
   private volatile Unapplied$ Unapplied$module;
   private volatile IsTrue$ IsTrue$module;
   private volatile IsFalse$ IsFalse$module;
   private volatile LiteralNameOrAdapted$ LiteralNameOrAdapted$module;
   private volatile DynamicUpdate$ DynamicUpdate$module;
   private volatile DynamicApplication$ DynamicApplication$module;
   private volatile DynamicApplicationNamed$ DynamicApplicationNamed$module;
   private volatile MacroImplReference$ MacroImplReference$module;

   public StripCast$ StripCast() {
      if (this.StripCast$module == null) {
         this.StripCast$lzycompute$1();
      }

      return this.StripCast$module;
   }

   public WildcardStarArg$ WildcardStarArg() {
      if (this.WildcardStarArg$module == null) {
         this.WildcardStarArg$lzycompute$1();
      }

      return this.WildcardStarArg$module;
   }

   public Applied$ Applied() {
      if (this.Applied$module == null) {
         this.Applied$lzycompute$1();
      }

      return this.Applied$module;
   }

   public Application$ Application() {
      if (this.Application$module == null) {
         this.Application$lzycompute$1();
      }

      return this.Application$module;
   }

   public Unapplied$ Unapplied() {
      if (this.Unapplied$module == null) {
         this.Unapplied$lzycompute$1();
      }

      return this.Unapplied$module;
   }

   public IsTrue$ IsTrue() {
      if (this.IsTrue$module == null) {
         this.IsTrue$lzycompute$1();
      }

      return this.IsTrue$module;
   }

   public IsFalse$ IsFalse() {
      if (this.IsFalse$module == null) {
         this.IsFalse$lzycompute$1();
      }

      return this.IsFalse$module;
   }

   public LiteralNameOrAdapted$ scala$reflect$internal$TreeInfo$$LiteralNameOrAdapted() {
      if (this.LiteralNameOrAdapted$module == null) {
         this.LiteralNameOrAdapted$lzycompute$1();
      }

      return this.LiteralNameOrAdapted$module;
   }

   public DynamicUpdate$ DynamicUpdate() {
      if (this.DynamicUpdate$module == null) {
         this.DynamicUpdate$lzycompute$1();
      }

      return this.DynamicUpdate$module;
   }

   public DynamicApplication$ DynamicApplication() {
      if (this.DynamicApplication$module == null) {
         this.DynamicApplication$lzycompute$1();
      }

      return this.DynamicApplication$module;
   }

   public DynamicApplicationNamed$ DynamicApplicationNamed() {
      if (this.DynamicApplicationNamed$module == null) {
         this.DynamicApplicationNamed$lzycompute$1();
      }

      return this.DynamicApplicationNamed$module;
   }

   public MacroImplReference$ MacroImplReference() {
      if (this.MacroImplReference$module == null) {
         this.MacroImplReference$lzycompute$1();
      }

      return this.MacroImplReference$module;
   }

   public abstract SymbolTable global();

   public boolean isDeclarationOrTypeDef(final Trees.Tree tree) {
      if (tree instanceof Trees.ValOrDefDef) {
         return ((Trees.ValOrDefDef)tree).rhs() == this.global().EmptyTree();
      } else {
         return tree instanceof Trees.TypeDef;
      }
   }

   public boolean isInterfaceMember(final Trees.Tree tree) {
      if (this.global().EmptyTree().equals(tree)) {
         return true;
      } else if (tree instanceof Trees.Import) {
         return true;
      } else if (tree instanceof Trees.TypeDef) {
         return true;
      } else if (tree instanceof Trees.DefDef) {
         return ((Trees.DefDef)tree).mods().isDeferred();
      } else {
         return tree instanceof Trees.ValDef ? ((Trees.ValDef)tree).mods().isDeferred() : false;
      }
   }

   public boolean isConstructorWithDefault(final Trees.Tree t) {
      if (t instanceof Trees.DefDef) {
         Trees.DefDef var2 = (Trees.DefDef)t;
         Names.TermName var3 = var2.name();
         List vparamss = var2.vparamss();
         Names.TermName var10000 = this.global().nme().CONSTRUCTOR();
         if (var10000 == null) {
            if (var3 != null) {
               return false;
            }
         } else if (!var10000.equals(var3)) {
            return false;
         }

         if (this.global() == null) {
            throw null;
         } else if (vparamss == null) {
            throw null;
         } else {
            for(List mexists_exists_these = vparamss; !mexists_exists_these.isEmpty(); mexists_exists_these = (List)mexists_exists_these.tail()) {
               List var6 = (List)mexists_exists_these.head();
               if (var6 == null) {
                  throw null;
               }

               List exists_these = var6;

               while(true) {
                  if (exists_these.isEmpty()) {
                     var9 = false;
                     break;
                  }

                  if ($anonfun$isConstructorWithDefault$1((Trees.ValDef)exists_these.head())) {
                     var9 = true;
                     break;
                  }

                  exists_these = (List)exists_these.tail();
               }

               Object var8 = null;
               if (var9) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   public boolean isPureDef(final Trees.Tree tree) {
      if (this.global().EmptyTree().equals(tree) ? true : (tree instanceof Trees.ClassDef ? true : (tree instanceof Trees.TypeDef ? true : (tree instanceof Trees.Import ? true : tree instanceof Trees.DefDef)))) {
         return true;
      } else if (tree instanceof Trees.ValDef) {
         Trees.ValDef var2 = (Trees.ValDef)tree;
         Trees.Modifiers mods = var2.mods();
         Trees.Tree rhs = var2.rhs();
         return !mods.isMutable() && this.isExprSafeToInline(rhs);
      } else {
         return false;
      }
   }

   public boolean isPath(final Trees.Tree tree, final boolean allowVolatile) {
      if (this.global().EmptyTree().equals(tree) ? true : tree instanceof Trees.Literal) {
         return true;
      } else {
         return (tree instanceof Trees.This ? true : tree instanceof Trees.Super) ? this.symOk(tree.symbol()) : this.isStableIdentifier(tree, allowVolatile);
      }
   }

   public boolean isStableIdentifier(final Trees.Tree tree, final boolean allowVolatile) {
      if (tree instanceof Trees.Ident) {
         Trees.Ident var3 = (Trees.Ident)tree;
         return this.isStableIdent(var3, allowVolatile);
      } else if (tree instanceof Trees.Select) {
         Trees.Tree qual = ((Trees.Select)tree).qualifier();
         return this.isStableMemberOf(tree.symbol(), qual, allowVolatile) && this.isPath(qual, allowVolatile);
      } else {
         if (tree instanceof Trees.Apply) {
            Trees.Tree var5 = ((Trees.Apply)tree).fun();
            if (var5 instanceof Trees.Select) {
               Trees.Select var6 = (Trees.Select)var5;
               Trees.Tree free = var6.qualifier();
               Names.Name var8 = var6.name();
               if (free instanceof Trees.Ident) {
                  Trees.Ident var9 = (Trees.Ident)free;
                  Names.TermName var10000 = this.global().nme().apply();
                  if (var10000 == null) {
                     if (var8 != null) {
                        return tree instanceof Trees.Literal;
                     }
                  } else if (!var10000.equals(var8)) {
                     return tree instanceof Trees.Literal;
                  }

                  if (var9.symbol().name().endsWith((Names.Name)this.global().nme().REIFY_FREE_VALUE_SUFFIX())) {
                     if (var9.symbol().hasStableFlag() && this.isPath(var9, allowVolatile)) {
                        return true;
                     }

                     return false;
                  }
               }
            }
         }

         return tree instanceof Trees.Literal;
      }
   }

   private boolean symOk(final Symbols.Symbol sym) {
      return sym != null && !sym.hasFlag(4294967296L) && !sym.equals(this.global().NoSymbol());
   }

   private boolean typeOk(final Types.Type tp) {
      return tp != null && !tp.isError();
   }

   private boolean isUncheckedStable(final Symbols.Symbol sym) {
      return sym.isTerm() && sym.hasAnnotation(this.global().definitions().uncheckedStableClass());
   }

   public boolean isStableMemberOf(final Symbols.Symbol sym, final Trees.Tree tree, final boolean allowVolatile) {
      return this.symOk(sym) && (!sym.isTerm() || (sym.isStable() || this.isUncheckedStable(sym)) && (allowVolatile || !sym.hasVolatileType())) && this.typeOk(tree.tpe()) && (allowVolatile || !this.hasVolatileType(tree)) && !this.global().definitions().isByNameParamType(tree.tpe());
   }

   private boolean isStableIdent(final Trees.Ident tree, final boolean allowVolatile) {
      return this.symOk(tree.symbol()) && (tree.symbol().isStable() || this.isUncheckedStable(tree.symbol())) && !this.global().definitions().isByNameParamType(tree.tpe()) && !this.global().definitions().isByName(tree.symbol()) && (allowVolatile || !tree.symbol().hasVolatileType());
   }

   public boolean hasVolatileType(final Trees.Tree tree) {
      return this.symOk(tree.symbol()) && tree.tpe().isVolatile() && !this.isUncheckedStable(tree.symbol());
   }

   public boolean admitsTypeSelection(final Trees.Tree tree) {
      return this.isPath(tree, false);
   }

   public boolean isStableIdentifierPattern(final Trees.Tree tree) {
      return this.isStableIdentifier(tree, true);
   }

   public boolean isQualifierSafeToElide(final Trees.Tree tree) {
      return this.isExprSafeToInline(tree);
   }

   public final boolean isExprSafeToInline(final Trees.Tree tree) {
      while(true) {
         boolean var2 = false;
         Trees.Select var3 = null;
         boolean var4 = false;
         Trees.Apply var5 = null;
         if (this.global().EmptyTree().equals(tree) ? true : (tree instanceof Trees.This ? true : (tree instanceof Trees.Super ? true : tree instanceof Trees.Literal))) {
            return true;
         }

         if (tree instanceof Trees.Ident) {
            return tree.symbol().isStable();
         }

         if (tree instanceof Trees.Select) {
            var2 = true;
            var3 = (Trees.Select)tree;
            Trees.Tree var6 = var3.qualifier();
            Names.Name name = var3.name();
            if (var6 instanceof Trees.Literal) {
               Constants.Constant var8 = ((Trees.Literal)var6).value();
               if (var8.isAnyVal()) {
                  Symbols.Symbol var34 = var8.tpe().member(name);
                  Symbols.NoSymbol var9 = this.global().NoSymbol();
                  if (var34 == null) {
                     if (var9 != null) {
                        return true;
                     }
                  } else if (!var34.equals(var9)) {
                     return true;
                  }
               }

               return false;
            }
         }

         if (var2) {
            Trees.Tree qual = var3.qualifier();
            if (!tree.symbol().isStable()) {
               return false;
            }

            tree = qual;
         } else if (tree instanceof Trees.TypeApply) {
            tree = ((Trees.TypeApply)tree).fun();
         } else {
            if (tree instanceof Trees.Apply) {
               var4 = true;
               var5 = (Trees.Apply)tree;
               Trees.Tree var11 = var5.fun();
               if (var11 instanceof Trees.Select) {
                  Trees.Select var12 = (Trees.Select)var11;
                  Trees.Tree free = var12.qualifier();
                  Names.Name var14 = var12.name();
                  if (free instanceof Trees.Ident) {
                     label120: {
                        Trees.Ident var15 = (Trees.Ident)free;
                        Names.TermName var10000 = this.global().nme().apply();
                        if (var10000 == null) {
                           if (var14 != null) {
                              break label120;
                           }
                        } else if (!var10000.equals(var14)) {
                           break label120;
                        }

                        if (var15.symbol().name().endsWith((Names.Name)this.global().nme().REIFY_FREE_VALUE_SUFFIX())) {
                           if (var15.symbol().hasStableFlag()) {
                              tree = var15;
                              continue;
                           }

                           return false;
                        }
                     }
                  }
               }
            }

            if (var4) {
               Trees.Tree fn = var5.fun();
               List var17 = var5.args();
               if (var17 != null) {
                  List var28 = .MODULE$.List();
                  if (var28 == null) {
                     throw null;
                  }

                  List unapplySeq_this = var28;
                  SeqOps var29 = SeqFactory.unapplySeq$(unapplySeq_this, var17);
                  Object var26 = null;
                  SeqOps var18 = var29;
                  SeqFactory.UnapplySeqWrapper var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var18);
                  var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int lengthCompare$extension_len = 0;
                  if (var18.lengthCompare(lengthCompare$extension_len) == 0) {
                     if (fn.symbol() != null && fn.symbol().isMethod() && !fn.symbol().isLazy()) {
                        tree = fn;
                        continue;
                     }

                     return false;
                  }
               }
            }

            if (tree instanceof Trees.Typed) {
               tree = ((Trees.Typed)tree).expr();
            } else {
               if (!(tree instanceof Trees.Block)) {
                  return false;
               }

               Trees.Block var19 = (Trees.Block)tree;
               List stats = var19.stats();
               Trees.Tree expr = var19.expr();
               if (stats == null) {
                  throw null;
               }

               List forall_these = stats;

               boolean var33;
               while(true) {
                  if (forall_these.isEmpty()) {
                     var33 = true;
                     break;
                  }

                  Trees.Tree var25 = (Trees.Tree)forall_these.head();
                  if (!this.isPureDef(var25)) {
                     var33 = false;
                     break;
                  }

                  forall_these = (List)forall_these.tail();
               }

               Object var27 = null;
               if (!var33) {
                  return false;
               }

               tree = expr;
            }
         }
      }
   }

   public final boolean isPureExprForWarningPurposes(final Trees.Tree tree) {
      while(tree instanceof Trees.Typed) {
         tree = ((Trees.Typed)tree).expr();
      }

      if (tree instanceof Trees.Function) {
         return true;
      } else {
         boolean var10000;
         if (this.global().EmptyTree().equals(tree)) {
            var10000 = true;
         } else {
            label44: {
               label43: {
                  if (tree instanceof Trees.Literal) {
                     Constants.Constant var2 = ((Trees.Literal)tree).value();
                     if (var2 != null) {
                        Object var3 = var2.value();
                        BoxedUnit var4 = BoxedUnit.UNIT;
                        if (var4 == null) {
                           if (var3 == null) {
                              break label43;
                           }
                        } else if (var4.equals(var3)) {
                           break label43;
                        }
                     }
                  }

                  var10000 = false;
                  break label44;
               }

               var10000 = true;
            }
         }

         if (var10000) {
            return false;
         } else if (!tree.isErrorTyped() && (this.isExprSafeToInline(tree) || this.isWarnableRefTree$1(tree)) && this.isWarnableSymbol$1(tree)) {
            return true;
         } else {
            return false;
         }
      }
   }

   public List mapMethodParamsAndArgs(final List params, final List args, final Function2 f) {
      if (.MODULE$.List() == null) {
         throw null;
      } else {
         Builder b = new ListBuffer();
         this.foreachMethodParamAndArg(params, args, (param, arg) -> {
            $anonfun$mapMethodParamsAndArgs$1(b, f, param, arg);
            return BoxedUnit.UNIT;
         });
         return (List)b.result();
      }
   }

   public boolean foreachMethodParamAndArg(final List params, final List args, final Function2 f) {
      int plen = params.length();
      int alen = args.length();
      if (plen == alen) {
         if (this.global() == null) {
            throw null;
         }

         List foreach2_ys1 = params;

         for(List foreach2_ys2 = args; !foreach2_ys1.isEmpty() && !foreach2_ys2.isEmpty(); foreach2_ys2 = (List)foreach2_ys2.tail()) {
            f.apply(foreach2_ys1.head(), foreach2_ys2.head());
            foreach2_ys1 = (List)foreach2_ys1.tail();
         }

         Object var31 = null;
         Object var32 = null;
      } else {
         if (params.isEmpty()) {
            return this.fail$1(params, args);
         }

         if (!this.global().definitions().isVarArgsList(params)) {
            return this.fail$1(params, args);
         }

         int plenInit = plen - 1;
         if (alen == plenInit) {
            if (alen == 0) {
               Nil var10000 = scala.collection.immutable.Nil..MODULE$;
            } else {
               SymbolTable var39 = this.global();
               List foreach2_xs1 = (List)params.init();
               if (var39 == null) {
                  throw null;
               }

               List foreach2_ys1 = foreach2_xs1;

               for(List foreach2_ys2 = args; !foreach2_ys1.isEmpty() && !foreach2_ys2.isEmpty(); foreach2_ys2 = (List)foreach2_ys2.tail()) {
                  f.apply(foreach2_ys1.head(), foreach2_ys2.head());
                  foreach2_ys1 = (List)foreach2_ys1.tail();
               }

               Object var33 = null;
               Object var34 = null;
               Object var24 = null;
            }
         } else {
            if (alen < plenInit) {
               return this.fail$1(params, args);
            }

            SymbolTable var40 = this.global();
            List var10001 = (List)params.init();
            List foreach2_xs2 = args.take(plenInit);
            List foreach2_xs1 = var10001;
            if (var40 == null) {
               throw null;
            }

            List foreach2_ys1 = foreach2_xs1;

            for(List foreach2_ys2 = foreach2_xs2; !foreach2_ys1.isEmpty() && !foreach2_ys2.isEmpty(); foreach2_ys2 = (List)foreach2_ys2.tail()) {
               f.apply(foreach2_ys1.head(), foreach2_ys2.head());
               foreach2_ys1 = (List)foreach2_ys1.tail();
            }

            Object var35 = null;
            Object var36 = null;
            Object var25 = null;
            Object var26 = null;
            List remainingArgs = (List)StrictOptimizedLinearSeqOps.drop$(args, plenInit);
            var40 = this.global();
            List var42 = .MODULE$.List();
            if (remainingArgs == null) {
               throw null;
            }

            int fill_n = SeqOps.size$(remainingArgs);
            if (var42 == null) {
               throw null;
            }

            Builder fill_b = new ListBuffer();
            fill_b.sizeHint(fill_n);

            for(int fill_i = 0; fill_i < fill_n; ++fill_i) {
               Object fill_$plus$eq_elem = (Symbols.Symbol)params.last();
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            SeqOps var43 = (SeqOps)fill_b.result();
            fill_b = null;
            Object var30 = null;
            List foreach2_xs1 = (List)var43;
            if (var40 == null) {
               throw null;
            }

            List foreach2_ys1 = foreach2_xs1;

            for(List foreach2_ys2 = remainingArgs; !foreach2_ys1.isEmpty() && !foreach2_ys2.isEmpty(); foreach2_ys2 = (List)foreach2_ys2.tail()) {
               f.apply(foreach2_ys1.head(), foreach2_ys2.head());
               foreach2_ys1 = (List)foreach2_ys1.tail();
            }

            Object var37 = null;
            Object var38 = null;
            Object var27 = null;
         }
      }

      return true;
   }

   public boolean isFunctionMissingParamType(final Trees.Tree tree) {
      if (tree instanceof Trees.Function) {
         List vparams = ((Trees.Function)tree).vparams();
         if (vparams == null) {
            throw null;
         } else {
            for(List exists_these = vparams; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
               if ($anonfun$isFunctionMissingParamType$1((Trees.ValDef)exists_these.head())) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   public boolean isPartialFunctionMissingParamType(final Trees.Tree tree) {
      if (tree instanceof Trees.Match) {
         Trees.Tree var2 = ((Trees.Match)tree).selector();
         if (this.global().EmptyTree().equals(var2)) {
            return true;
         }
      }

      return false;
   }

   public boolean mayBeVarGetter(final Symbols.Symbol sym) {
      boolean var2 = false;
      Types.PolyType var3 = null;
      Types.Type var4 = sym.info();
      if (var4 instanceof Types.NullaryMethodType) {
         return sym.owner().isClass() && !sym.isStable();
      } else {
         if (var4 instanceof Types.PolyType) {
            var2 = true;
            var3 = (Types.PolyType)var4;
            if (var3.resultType() instanceof Types.NullaryMethodType) {
               if (sym.owner().isClass() && !sym.isStable()) {
                  return true;
               }

               return false;
            }
         }

         if (var2) {
            Types.Type mt = var3.resultType();
            if (mt instanceof Types.MethodType) {
               if (((Types.MethodType)mt).isImplicit() && sym.owner().isClass() && !sym.isStable()) {
                  return true;
               }

               return false;
            }
         }

         if (var4 instanceof Types.MethodType) {
            return ((Types.MethodType)var4).isImplicit() && sym.owner().isClass() && !sym.isStable();
         } else {
            return false;
         }
      }
   }

   public boolean isVariableOrGetter(final Trees.Tree tree) {
      if (tree instanceof Trees.Ident) {
         return isVar$1(tree);
      } else if (tree instanceof Trees.Select) {
         Trees.Tree qual = ((Trees.Select)tree).qualifier();
         if (!isVar$1(tree)) {
            if (this.mayBeVarGetter(tree.symbol())) {
               Symbols.Symbol var11 = qual.tpe().member((Names.Name)tree.symbol().setterName());
               Symbols.NoSymbol var3 = this.global().NoSymbol();
               if (var11 == null) {
                  if (var3 != null) {
                     return true;
                  }
               } else if (!var11.equals(var3)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      } else {
         if (tree != null) {
            Some var4 = this.Applied().unapply(tree);
            if (!var4.isEmpty()) {
               Trees.Tree var5 = (Trees.Tree)((Tuple3)var4.value())._1();
               if (var5 instanceof Trees.Select) {
                  Trees.Select var6 = (Trees.Select)var5;
                  Trees.Tree qual = var6.qualifier();
                  Names.Name var8 = var6.name();
                  Names.TermName var10000 = this.global().nme().apply();
                  if (var10000 == null) {
                     if (var8 != null) {
                        return false;
                     }
                  } else if (!var10000.equals(var8)) {
                     return false;
                  }

                  Symbols.Symbol var10 = qual.tpe().member((Names.Name)this.global().nme().update());
                  Symbols.NoSymbol var9 = this.global().NoSymbol();
                  if (var10 == null) {
                     if (var9 != null) {
                        return true;
                     }
                  } else if (!var10.equals(var9)) {
                     return true;
                  }

                  return false;
               }
            }
         }

         return false;
      }
   }

   public boolean noFieldFor(final Trees.ValDef vd, final Symbols.Symbol owner) {
      return vd.mods().isDeferred() || vd.mods().isLazy() || owner.isTrait() && !vd.mods().hasFlag(137438953472L);
   }

   public boolean isDefaultGetter(final Trees.Tree tree) {
      return tree.symbol() != null && tree.symbol().isDefaultGetter();
   }

   public boolean isSelfConstrCall(final Trees.Tree tree) {
      Trees.Tree var2 = this.dissectCore(tree);
      if (var2 instanceof Trees.Ident) {
         Names.Name var3 = ((Trees.Ident)var2).name();
         Names.TermName var10000 = this.global().nme().CONSTRUCTOR();
         if (var10000 == null) {
            if (var3 == null) {
               return true;
            }
         } else if (var10000.equals(var3)) {
            return true;
         }
      }

      if (var2 instanceof Trees.Select) {
         Trees.Select var4 = (Trees.Select)var2;
         Trees.Tree var5 = var4.qualifier();
         Names.Name var6 = var4.name();
         if (var5 instanceof Trees.This) {
            Names.TermName var7 = this.global().nme().CONSTRUCTOR();
            if (var7 == null) {
               if (var6 == null) {
                  return true;
               }
            } else if (var7.equals(var6)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean isSuperConstrCall(final Trees.Tree tree) {
      Trees.Tree var2 = this.dissectCore(tree);
      if (var2 instanceof Trees.Select) {
         Trees.Select var3 = (Trees.Select)var2;
         Trees.Tree var4 = var3.qualifier();
         Names.Name var5 = var3.name();
         if (var4 instanceof Trees.Super) {
            Names.TermName var10000 = this.global().nme().CONSTRUCTOR();
            if (var10000 == null) {
               if (var5 == null) {
                  return true;
               }
            } else if (var10000.equals(var5)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean isThisTypeResult(final Trees.Tree tree) {
      if (tree != null) {
         Some var2 = this.Applied().unapply(tree);
         if (!var2.isEmpty()) {
            Trees.Tree fun = (Trees.Tree)((Tuple3)var2.value())._1();
            List argss = (List)((Tuple3)var2.value())._3();
            if (fun instanceof Trees.Select) {
               Trees.Select var5 = (Trees.Select)fun;
               Trees.Tree receiver = var5.qualifier();
               Names.Name op = var5.name();
               Types.Type var8 = tree.tpe();
               if (var8 instanceof Types.ThisType) {
                  Symbols.Symbol var15 = ((Types.ThisType)var8).sym();
                  Symbols.Symbol var9 = receiver.symbol();
                  if (var15 == null) {
                     if (var9 == null) {
                        return true;
                     }
                  } else if (var15.equals(var9)) {
                     return true;
                  }

                  return false;
               }

               if (!(var8 instanceof Types.SingleType)) {
                  if (var5.symbol() != null && this.loop$1(var5.symbol().info(), receiver, op)) {
                     return true;
                  }

                  return false;
               }

               Symbols.Symbol sym = ((Types.SingleType)var8).sym();
               Symbols.Symbol var11 = receiver.symbol();
               if (sym == null) {
                  if (var11 == null) {
                     return true;
                  }
               } else if (sym.equals(var11)) {
                  return true;
               }

               if (argss == null) {
                  throw null;
               }

               List exists_these = argss;

               boolean var10000;
               while(true) {
                  if (exists_these.isEmpty()) {
                     var10000 = false;
                     break;
                  }

                  List var13 = (List)exists_these.head();
                  if ($anonfun$isThisTypeResult$1(sym, var13)) {
                     var10000 = true;
                     break;
                  }

                  exists_these = (List)exists_these.tail();
               }

               Object var14 = null;
               if (!var10000) {
                  return false;
               }

               return true;
            }
         }
      }

      return tree.tpe() instanceof Types.ThisType;
   }

   public Trees.Tree stripNamedApplyBlock(final Trees.Tree tree) {
      if (tree instanceof Trees.Block) {
         Trees.Block var2 = (Trees.Block)tree;
         List stats = var2.stats();
         Trees.Tree expr = var2.expr();
         if (stats == null) {
            throw null;
         }

         List forall_these = stats;

         boolean var10000;
         while(true) {
            if (forall_these.isEmpty()) {
               var10000 = true;
               break;
            }

            if (!((Trees.Tree)forall_these.head() instanceof Trees.ValDef)) {
               var10000 = false;
               break;
            }

            forall_these = (List)forall_these.tail();
         }

         Object var6 = null;
         if (var10000) {
            return expr;
         }
      }

      return tree;
   }

   public final Trees.Tree stripCast(final Trees.Tree tree) {
      while(true) {
         if (tree instanceof Trees.TypeApply) {
            Trees.Tree sel = ((Trees.TypeApply)tree).fun();
            if (sel instanceof Trees.Select) {
               Trees.Select var3 = (Trees.Select)sel;
               Trees.Tree inner = var3.qualifier();
               if (this.global().definitions().isCastSymbol(var3.symbol())) {
                  tree = inner;
                  continue;
               }
            }
         }

         if (tree instanceof Trees.Apply) {
            Trees.Apply var5 = (Trees.Apply)tree;
            Trees.Tree var6 = var5.fun();
            List var7 = var5.args();
            if (var6 instanceof Trees.TypeApply) {
               Trees.Tree sel = ((Trees.TypeApply)var6).fun();
               if (sel instanceof Trees.Select) {
                  Trees.Select var9 = (Trees.Select)sel;
                  Trees.Tree inner = var9.qualifier();
                  if (scala.collection.immutable.Nil..MODULE$.equals(var7) && this.global().definitions().isCastSymbol(var9.symbol())) {
                     tree = inner;
                     continue;
                  }
               }
            }
         }

         return tree;
      }
   }

   public boolean isSelfOrSuperConstrCall(final Trees.Tree tree) {
      Trees.Tree tree1 = this.stripNamedApplyBlock(tree);
      return this.isSelfConstrCall(tree1) || this.isSuperConstrCall(tree1);
   }

   public boolean isVarPatternDeep(final Trees.Tree tree) {
      return tree instanceof Trees.Ident ? true : this.isVarPatternDeep0$1(tree);
   }

   public boolean isVarPattern(final Trees.Tree pat) {
      if (pat instanceof Trees.Ident) {
         Trees.Ident var2 = (Trees.Ident)pat;
         return !var2.isBackquoted() && this.global().nme().isVariableName(var2.name());
      } else {
         return false;
      }
   }

   public boolean isLiteralString(final Trees.Tree t) {
      if (t instanceof Trees.Literal) {
         Constants.Constant var2 = ((Trees.Literal)t).value();
         if (var2 != null && var2.value() instanceof String) {
            return true;
         }
      }

      return false;
   }

   public boolean detectTypecheckedTree(final Trees.Tree tree) {
      return tree.hasExistingSymbol() || tree.exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$detectTypecheckedTree$1(x0$1)));
   }

   public List untypecheckedTemplBody(final Trees.Template templ) {
      return this.untypecheckedTreeBody(templ, templ.body());
   }

   public List untypecheckedBlockBody(final Trees.Block block) {
      return this.untypecheckedTreeBody(block, block.stats());
   }

   public List untypecheckedTreeBody(final Trees.Tree tree, final List tbody) {
      return this.detectTypecheckedTree(tree) ? this.recoverBody$1(this.filterBody$1(tbody, tbody), tbody) : tbody;
   }

   public Trees.Tree firstConstructor(final List stats) {
      if (stats == null) {
         throw null;
      } else {
         List find_these = stats;

         Object var10000;
         while(true) {
            if (find_these.isEmpty()) {
               var10000 = scala.None..MODULE$;
               break;
            }

            Trees.Tree var4 = (Trees.Tree)find_these.head();
            if ($anonfun$firstConstructor$1(this, var4)) {
               var10000 = new Some(find_these.head());
               break;
            }

            find_these = (List)find_these.tail();
         }

         Object var5 = null;
         Option getOrElse_this = (Option)var10000;
         return (Trees.Tree)(getOrElse_this.isEmpty() ? $anonfun$firstConstructor$2(this) : getOrElse_this.get());
      }
   }

   public List firstConstructorArgs(final List stats) {
      Trees.Tree var2 = this.firstConstructor(stats);
      if (var2 instanceof Trees.DefDef) {
         List var3 = ((Trees.DefDef)var2).vparamss();
         if (var3 instanceof scala.collection.immutable..colon.colon) {
            return (List)((scala.collection.immutable..colon.colon)var3).head();
         }
      }

      return scala.collection.immutable.Nil..MODULE$;
   }

   public Trees.Modifiers firstConstructorMods(final List stats) {
      Trees.Tree var2 = this.firstConstructor(stats);
      return var2 instanceof Trees.DefDef ? ((Trees.DefDef)var2).mods() : (Trees.Modifiers)this.global().Modifiers().apply();
   }

   public List preSuperFields(final List stats) {
      return stats.collect(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final TreeInfo $outer;

         public final Object applyOrElse(final Trees.Tree x1, final Function1 default) {
            if (x1 instanceof Trees.ValDef) {
               Trees.ValDef var3 = (Trees.ValDef)x1;
               if (this.$outer.isEarlyValDef(var3)) {
                  return var3;
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final Trees.Tree x1) {
            if (x1 instanceof Trees.ValDef) {
               Trees.ValDef var2 = (Trees.ValDef)x1;
               if (this.$outer.isEarlyValDef(var2)) {
                  return true;
               }
            }

            return false;
         }

         public {
            if (TreeInfo.this == null) {
               throw null;
            } else {
               this.$outer = TreeInfo.this;
            }
         }
      });
   }

   public boolean hasUntypedPreSuperFields(final List stats) {
      List var10000 = this.preSuperFields(stats);
      if (var10000 == null) {
         throw null;
      } else {
         for(List exists_these = var10000; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
            if ($anonfun$hasUntypedPreSuperFields$1((Trees.ValDef)exists_these.head())) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isEarlyDef(final Trees.Tree tree) {
      if (tree instanceof Trees.TypeDef) {
         return ((Trees.TypeDef)tree).mods().hasFlag(137438953472L);
      } else {
         return tree instanceof Trees.ValDef ? ((Trees.ValDef)tree).mods().hasFlag(137438953472L) : false;
      }
   }

   public boolean isEarlyValDef(final Trees.Tree tree) {
      return tree instanceof Trees.ValDef ? ((Trees.ValDef)tree).mods().hasFlag(137438953472L) : false;
   }

   public boolean isRepeatedParamType(final Trees.Tree tpt) {
      boolean var2 = false;
      Trees.AppliedTypeTree var3 = null;
      if (tpt instanceof Trees.TypeTree) {
         return this.global().definitions().isRepeatedParamType(tpt.tpe());
      } else {
         if (tpt instanceof Trees.AppliedTypeTree) {
            var2 = true;
            var3 = (Trees.AppliedTypeTree)tpt;
            Trees.Tree var4 = var3.tpt();
            if (var4 instanceof Trees.Select) {
               Names.Name var5 = ((Trees.Select)var4).name();
               Names.TypeName var10000 = this.global().tpnme().REPEATED_PARAM_CLASS_NAME();
               if (var10000 == null) {
                  if (var5 == null) {
                     return true;
                  }
               } else if (var10000.equals(var5)) {
                  return true;
               }
            }
         }

         if (var2) {
            Trees.Tree var6 = var3.tpt();
            if (var6 instanceof Trees.Select) {
               Names.Name var7 = ((Trees.Select)var6).name();
               Names.TypeName var8 = this.global().tpnme().JAVA_REPEATED_PARAM_CLASS_NAME();
               if (var8 == null) {
                  if (var7 == null) {
                     return true;
                  }
               } else if (var8.equals(var7)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean isByNameParamType(final Trees.Tree tpt) {
      if (tpt instanceof Trees.TypeTree) {
         return this.global().definitions().isByNameParamType(tpt.tpe());
      } else {
         if (tpt instanceof Trees.AppliedTypeTree) {
            Trees.Tree var2 = ((Trees.AppliedTypeTree)tpt).tpt();
            if (var2 instanceof Trees.Select) {
               Names.Name var3 = ((Trees.Select)var2).name();
               Names.TypeName var10000 = this.global().tpnme().BYNAME_PARAM_CLASS_NAME();
               if (var10000 == null) {
                  if (var3 == null) {
                     return true;
                  }
               } else if (var10000.equals(var3)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public Trees.Tree assignmentToMaybeNamedArg(final Trees.Tree tree) {
      if (tree instanceof Trees.Assign) {
         Trees.Assign var2 = (Trees.Assign)tree;
         Trees.Tree id = var2.lhs();
         Trees.Tree rhs = var2.rhs();
         if (id instanceof Trees.Ident) {
            Trees.Ident var5 = (Trees.Ident)id;
            return this.global().atPos((Position)var2.pos(), (Trees.Tree)(this.global().new NamedArg(var5, rhs)));
         }
      }

      return tree;
   }

   public boolean isSwitchAnnotation(final Types.Type tpe) {
      return tpe.hasAnnotation(this.global().definitions().SwitchClass());
   }

   public final boolean mayBeTypePat(final Trees.Tree tree) {
      while(true) {
         if (tree instanceof Trees.CompoundTypeTree) {
            Trees.Template var2 = ((Trees.CompoundTypeTree)tree).templ();
            if (var2 != null) {
               List tps = var2.parents();
               List var4 = var2.body();
               if (scala.collection.immutable.Nil..MODULE$.equals(var4)) {
                  if (tps == null) {
                     throw null;
                  }

                  for(List exists_these = tps; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
                     Trees.Tree var10 = (Trees.Tree)exists_these.head();
                     if (this.mayBeTypePat(var10)) {
                        return true;
                     }
                  }

                  return false;
               }
            }
         }

         if (tree instanceof Trees.Annotated) {
            tree = ((Trees.Annotated)tree).arg();
         } else {
            if (!(tree instanceof Trees.AppliedTypeTree)) {
               if (tree instanceof Trees.SelectFromTypeTree) {
                  tree = ((Trees.SelectFromTypeTree)tree).qualifier();
                  continue;
               }

               return false;
            }

            Trees.AppliedTypeTree var5 = (Trees.AppliedTypeTree)tree;
            Trees.Tree constr = var5.tpt();
            List args = var5.args();
            if (!this.mayBeTypePat(constr)) {
               if (args == null) {
                  throw null;
               }

               List exists_these = args;

               boolean var10000;
               while(true) {
                  if (exists_these.isEmpty()) {
                     var10000 = false;
                     break;
                  }

                  if ((Trees.Tree)exists_these.head() instanceof Trees.Bind) {
                     var10000 = true;
                     break;
                  }

                  exists_these = (List)exists_these.tail();
               }

               Object var11 = null;
               if (!var10000) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public boolean isWildcardStarArg(final Trees.Tree tree) {
      return tree != null && !this.WildcardStarArg().unapply(tree).isEmpty();
   }

   public List typeParameters(final Trees.Tree tree) {
      if (tree instanceof Trees.DefDef) {
         return ((Trees.DefDef)tree).tparams();
      } else if (tree instanceof Trees.ClassDef) {
         return ((Trees.ClassDef)tree).tparams();
      } else {
         return (List)(tree instanceof Trees.TypeDef ? ((Trees.TypeDef)tree).tparams() : scala.collection.immutable.Nil..MODULE$);
      }
   }

   public boolean isWildcardStarArgList(final List trees) {
      return trees.nonEmpty() && this.isWildcardStarArg((Trees.Tree)trees.last());
   }

   public boolean isWildcardArg(final Trees.Tree tree) {
      Trees.Tree var2 = this.unbind(tree);
      if (var2 instanceof Trees.Ident) {
         Names.Name var3 = ((Trees.Ident)var2).name();
         Names.Name var10000 = this.global().nme().WILDCARD();
         if (var10000 == null) {
            if (var3 == null) {
               return true;
            }
         } else if (var10000.equals(var3)) {
            return true;
         }
      }

      return false;
   }

   public boolean isWildcardStarType(final Trees.Tree tree) {
      if (tree instanceof Trees.Ident) {
         Names.Name var2 = ((Trees.Ident)tree).name();
         Names.TypeName var10000 = this.global().tpnme().WILDCARD_STAR();
         if (var10000 == null) {
            if (var2 == null) {
               return true;
            }
         } else if (var10000.equals(var2)) {
            return true;
         }
      }

      return false;
   }

   public boolean isDefaultCase(final Trees.CaseDef cdef) {
      if (cdef != null) {
         Trees.Tree pat = cdef.pat();
         Trees.Tree var3 = cdef.guard();
         if (this.global().EmptyTree().equals(var3)) {
            return this.isWildcardArg(pat);
         }
      }

      return false;
   }

   private boolean hasNoSymbol(final Trees.Tree t) {
      if (t.symbol() != null) {
         Symbols.Symbol var10000 = t.symbol();
         Symbols.NoSymbol var2 = this.global().NoSymbol();
         if (var10000 == null) {
            if (var2 == null) {
               return true;
            }
         } else if (var10000.equals(var2)) {
            return true;
         }

         return false;
      } else {
         return true;
      }
   }

   public boolean isSyntheticDefaultCase(final Trees.CaseDef cdef) {
      if (cdef != null) {
         Trees.Tree var2 = cdef.pat();
         Trees.Tree var3 = cdef.guard();
         if (var2 instanceof Trees.Bind) {
            Names.Name var4 = ((Trees.Bind)var2).name();
            Names.TermName var10000 = this.global().nme().DEFAULT_CASE();
            if (var10000 == null) {
               if (var4 != null) {
                  return false;
               }
            } else if (!var10000.equals(var4)) {
               return false;
            }

            if (this.global().EmptyTree().equals(var3)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean catchesThrowable(final Trees.CaseDef cdef) {
      if (cdef.guard().isEmpty()) {
         boolean var6;
         label26: {
            label25: {
               boolean var2 = false;
               Trees.Ident var3 = null;
               Trees.Tree var4 = this.unbind(cdef.pat());
               if (var4 instanceof Trees.Ident) {
                  var2 = true;
                  var3 = (Trees.Ident)var4;
                  Names.Name var5 = var3.name();
                  Names.Name var10000 = this.global().nme().WILDCARD();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label25;
                     }
                  } else if (var10000.equals(var5)) {
                     break label25;
                  }
               }

               var6 = var2 ? this.hasNoSymbol(var3) : false;
               break label26;
            }

            var6 = true;
         }

         if (var6) {
            return true;
         }
      }

      return false;
   }

   public boolean isSyntheticCase(final Trees.CaseDef cdef) {
      return cdef.pat().exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$isSyntheticCase$1(x0$1)));
   }

   public boolean isCatchCase(final Trees.CaseDef cdef) {
      if (cdef != null) {
         Trees.Tree var2 = cdef.pat();
         Trees.Tree var3 = cdef.guard();
         if (var2 instanceof Trees.Typed) {
            Trees.Typed var4 = (Trees.Typed)var2;
            Trees.Tree var5 = var4.expr();
            Trees.Tree tpt = var4.tpt();
            if (var5 instanceof Trees.Ident) {
               label40: {
                  Names.Name var7 = ((Trees.Ident)var5).name();
                  Names.Name var10000 = this.global().nme().WILDCARD();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label40;
                  }

                  if (this.global().EmptyTree().equals(var3)) {
                     return this.isSimpleThrowable(tpt.tpe());
                  }
               }
            }
         }
      }

      if (cdef != null) {
         Trees.Tree var8 = cdef.pat();
         Trees.Tree var9 = cdef.guard();
         if (var8 instanceof Trees.Bind) {
            Trees.Tree var10 = ((Trees.Bind)var8).body();
            if (var10 instanceof Trees.Typed) {
               Trees.Typed var11 = (Trees.Typed)var10;
               Trees.Tree var12 = var11.expr();
               Trees.Tree tpt = var11.tpt();
               if (var12 instanceof Trees.Ident) {
                  Names.Name var14 = ((Trees.Ident)var12).name();
                  Names.Name var15 = this.global().nme().WILDCARD();
                  if (var15 == null) {
                     if (var14 != null) {
                        return this.isDefaultCase(cdef);
                     }
                  } else if (!var15.equals(var14)) {
                     return this.isDefaultCase(cdef);
                  }

                  if (this.global().EmptyTree().equals(var9)) {
                     return this.isSimpleThrowable(tpt.tpe());
                  }
               }
            }
         }
      }

      return this.isDefaultCase(cdef);
   }

   private boolean isSimpleThrowable(final Types.Type tp) {
      if (!(tp instanceof Types.TypeRef)) {
         return false;
      } else {
         Symbols.Symbol sym;
         label24: {
            Types.TypeRef var2 = (Types.TypeRef)tp;
            Types.Type pre = var2.pre();
            sym = var2.sym();
            Types.NoPrefix$ var5 = this.global().NoPrefix();
            if (pre == null) {
               if (var5 == null) {
                  break label24;
               }
            } else if (pre.equals(var5)) {
               break label24;
            }

            if (!pre.widen().typeSymbol().isStatic()) {
               return false;
            }
         }

         if (sym.isNonBottomSubClass(this.global().definitions().ThrowableClass()) && !sym.isTrait()) {
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean isGuardedCase(final Trees.CaseDef cdef) {
      Trees.Tree var10000 = cdef.guard();
      Trees.EmptyTree$ var2 = this.global().EmptyTree();
      if (var10000 == null) {
         if (var2 != null) {
            return true;
         }
      } else if (!var10000.equals(var2)) {
         return true;
      }

      return false;
   }

   public boolean isSequenceValued(final Trees.Tree tree) {
      Trees.Tree var2 = this.unbind(tree);
      if (var2 instanceof Trees.Alternative) {
         List ts = ((Trees.Alternative)var2).trees();
         if (ts == null) {
            throw null;
         } else {
            for(List exists_these = ts; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
               Trees.Tree var5 = (Trees.Tree)exists_these.head();
               if (this.isSequenceValued(var5)) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return var2 instanceof Trees.ArrayValue ? true : var2 instanceof Trees.Star;
      }
   }

   public final Trees.Tree unbind(final Trees.Tree x) {
      while(x instanceof Trees.Bind) {
         x = ((Trees.Bind)x).body();
      }

      return x;
   }

   public boolean isStar(final Trees.Tree x) {
      return this.unbind(x) instanceof Trees.Star;
   }

   public int effectivePatternArity(final List args) {
      return this.flattenedPatternArgs(args).length();
   }

   public List flattenedPatternArgs(final List args) {
      if (args == null) {
         throw null;
      } else {
         Object var10000;
         if (args == scala.collection.immutable.Nil..MODULE$) {
            var10000 = scala.collection.immutable.Nil..MODULE$;
         } else {
            Trees.Tree var12 = (Trees.Tree)args.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.unbind(var12), scala.collection.immutable.Nil..MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)args.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               var12 = (Trees.Tree)map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.unbind(var12), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var13 = null;
         Object var14 = null;
         Object var15 = null;
         Object var16 = null;
         Object var2 = var10000;
         if (var2 instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)var2;
            Trees.Tree var4 = (Trees.Tree)var3.head();
            List var5 = var3.next$access$1();
            if (var4 != null) {
               Option var6 = this.global().build().SyntacticTuple().unapply(var4);
               if (!var6.isEmpty()) {
                  List xs = (List)var6.get();
                  if (scala.collection.immutable.Nil..MODULE$.equals(var5)) {
                     return xs;
                  }
               }
            }
         }

         return (List)var2;
      }
   }

   public final long SYNTH_CASE_FLAGS() {
      return 2099200L;
   }

   public boolean isSynthCaseSymbol(final Symbols.Symbol sym) {
      return sym.hasAllFlags(2099200L);
   }

   public boolean hasSynthCaseSymbol(final Trees.Tree t) {
      return t.symbol() != null && this.isSynthCaseSymbol(t.symbol());
   }

   public boolean isTraitRef(final Trees.Tree tree) {
      Symbols.Symbol sym = tree.tpe() != null ? tree.tpe().typeSymbol() : null;
      return sym != null && sym.initialize().isTrait();
   }

   public boolean hasExplicitUnit(final Trees.Tree tree) {
      if (!this.global().explicitlyUnit(tree)) {
         boolean var10000;
         if (tree instanceof Trees.Apply) {
            Trees.Tree f = ((Trees.Apply)tree).fun();
            var10000 = this.hasExplicitUnit(f);
         } else if (tree instanceof Trees.TypeApply) {
            Trees.Tree f = ((Trees.TypeApply)tree).fun();
            var10000 = this.hasExplicitUnit(f);
         } else if (tree instanceof Trees.AppliedTypeTree) {
            Trees.Tree f = ((Trees.AppliedTypeTree)tree).tpt();
            var10000 = this.hasExplicitUnit(f);
         } else {
            var10000 = false;
         }

         if (!var10000) {
            return false;
         }
      }

      return true;
   }

   public final Applied dissectApplied(final Trees.Tree tree) {
      return new Applied(tree);
   }

   public final Trees.Tree dissectCore(final Trees.Tree tree) {
      while(true) {
         if (tree instanceof Trees.TypeApply) {
            tree = ((Trees.TypeApply)tree).fun();
         } else {
            if (!(tree instanceof Trees.Apply)) {
               return tree;
            }

            tree = ((Trees.Apply)tree).fun();
         }
      }
   }

   public final boolean firstDefinesClassOrObject(final List trees, final Names.Name name) {
      while(true) {
         boolean var3 = false;
         scala.collection.immutable..colon.colon var4 = null;
         if (trees instanceof scala.collection.immutable..colon.colon) {
            var3 = true;
            var4 = (scala.collection.immutable..colon.colon)trees;
            Trees.Tree var5 = (Trees.Tree)var4.head();
            List xs = var4.next$access$1();
            if (var5 instanceof Trees.Import) {
               name = name;
               trees = xs;
               continue;
            }
         }

         if (var3) {
            Trees.Tree var7 = (Trees.Tree)var4.head();
            if (var7 instanceof Trees.Annotated) {
               Trees.Tree tree1 = ((Trees.Annotated)var7).arg();
               scala.collection.immutable..colon.colon var10000 = new scala.collection.immutable..colon.colon(tree1, scala.collection.immutable.Nil..MODULE$);
               name = name;
               trees = var10000;
               continue;
            }
         }

         if (var3) {
            Trees.Tree var9 = (Trees.Tree)var4.head();
            if (var9 instanceof Trees.ModuleDef) {
               Names.TermName var10 = ((Trees.ModuleDef)var9).name();
               if (name == null) {
                  if (var10 == null) {
                     return true;
                  }
               } else if (name.equals(var10)) {
                  return true;
               }
            }
         }

         if (var3) {
            Trees.Tree var11 = (Trees.Tree)var4.head();
            if (var11 instanceof Trees.ClassDef) {
               Names.TypeName var12 = ((Trees.ClassDef)var11).name();
               if (name == null) {
                  if (var12 == null) {
                     return true;
                  }
               } else if (name.equals(var12)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean isAbsTypeDef(final Trees.Tree tree) {
      boolean var2 = false;
      Trees.TypeDef var3 = null;
      if (tree instanceof Trees.TypeDef) {
         var2 = true;
         var3 = (Trees.TypeDef)tree;
         if (var3.rhs() instanceof Trees.TypeBoundsTree) {
            return true;
         }
      }

      return var2 ? var3.rhs().tpe() instanceof Types.TypeBounds : false;
   }

   public boolean isAliasTypeDef(final Trees.Tree tree) {
      if (tree instanceof Trees.TypeDef) {
         return !this.isAbsTypeDef(tree);
      } else {
         return false;
      }
   }

   public boolean isApplyDynamicName(final Names.Name name) {
      Names.TermName var2 = this.global().nme().updateDynamic();
      if (name == null) {
         if (var2 == null) {
            return true;
         }
      } else if (name.equals(var2)) {
         return true;
      }

      Names.TermName var3 = this.global().nme().selectDynamic();
      if (name == null) {
         if (var3 == null) {
            return true;
         }
      } else if (name.equals(var3)) {
         return true;
      }

      Names.TermName var4 = this.global().nme().applyDynamic();
      if (name == null) {
         if (var4 == null) {
            return true;
         }
      } else if (name.equals(var4)) {
         return true;
      }

      Names.TermName var5 = this.global().nme().applyDynamicNamed();
      if (name == null) {
         if (var5 == null) {
            return true;
         }
      } else if (name.equals(var5)) {
         return true;
      }

      return false;
   }

   public final boolean isNullaryInvocation(final Trees.Tree tree) {
      while(true) {
         if (tree.symbol() != null && tree.symbol().isMethod()) {
            if (tree instanceof Trees.TypeApply) {
               tree = ((Trees.TypeApply)tree).fun();
               continue;
            }

            if (tree instanceof Trees.RefTree) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isMacroApplication(final Trees.Tree tree) {
      if (!tree.isDef()) {
         Symbols.Symbol sym = tree.symbol();
         if (sym != null && sym.isTermMacro() && !sym.isErroneous()) {
            return true;
         }
      }

      return false;
   }

   public final boolean isMacroApplicationOrBlock(final Trees.Tree tree) {
      while(tree instanceof Trees.Block) {
         tree = ((Trees.Block)tree).expr();
      }

      return this.isMacroApplication(tree);
   }

   private final void StripCast$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.StripCast$module == null) {
            this.StripCast$module = new StripCast$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void WildcardStarArg$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.WildcardStarArg$module == null) {
            this.WildcardStarArg$module = new WildcardStarArg$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Applied$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Applied$module == null) {
            this.Applied$module = new Applied$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Application$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Application$module == null) {
            this.Application$module = new Application$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Unapplied$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Unapplied$module == null) {
            this.Unapplied$module = new Unapplied$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void IsTrue$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.IsTrue$module == null) {
            this.IsTrue$module = new IsTrue$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void IsFalse$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.IsFalse$module == null) {
            this.IsFalse$module = new IsFalse$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LiteralNameOrAdapted$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LiteralNameOrAdapted$module == null) {
            this.LiteralNameOrAdapted$module = new LiteralNameOrAdapted$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void DynamicUpdate$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.DynamicUpdate$module == null) {
            this.DynamicUpdate$module = new DynamicUpdate$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void DynamicApplication$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.DynamicApplication$module == null) {
            this.DynamicApplication$module = new DynamicApplication$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void DynamicApplicationNamed$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.DynamicApplicationNamed$module == null) {
            this.DynamicApplicationNamed$module = new DynamicApplicationNamed$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void MacroImplReference$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.MacroImplReference$module == null) {
            this.MacroImplReference$module = new MacroImplReference$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$isConstructorWithDefault$1(final Trees.ValDef x$1) {
      return x$1.mods().hasDefault();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isExprSafeToInline$1(final TreeInfo $this, final Trees.Tree tree) {
      return $this.isPureDef(tree);
   }

   private final boolean isWarnableRefTree$1(final Trees.Tree tree$1) {
      if (tree$1 instanceof Trees.RefTree) {
         Trees.RefTree var2 = (Trees.RefTree)tree$1;
         return this.isExprSafeToInline(var2.qualifier()) && ((Trees.SymTree)var2).symbol() != null && ((Trees.SymTree)var2).symbol().isAccessor();
      } else {
         return false;
      }
   }

   private final boolean isWarnableSymbol$1(final Trees.Tree tree$1) {
      Symbols.Symbol sym = tree$1.symbol();
      if (sym == null || !sym.isModule() && !sym.isLazy() && !this.global().definitions().isByNameParamType(sym.tpe_$times())) {
         return true;
      } else {
         this.global().debuglog(() -> (new StringBuilder(60)).append("'Pure' but side-effecting expression in statement position: ").append(tree$1).toString());
         return false;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$mapMethodParamsAndArgs$1(final Builder b$1, final Function2 f$1, final Symbols.Symbol param, final Trees.Tree arg) {
      Object $plus$eq_elem = f$1.apply(param, arg);
      b$1.addOne($plus$eq_elem);
   }

   private final boolean fail$1(final List params$1, final List args$1) {
      this.global().devWarning(() -> scala.collection.StringOps..MODULE$.stripMargin$extension((new StringBuilder(110)).append("|Mismatch trying to zip method parameters and argument list:\n            |  params = ").append(params$1).append("\n            |    args = ").append(args$1).toString(), '|'));
      return false;
   }

   // $FF: synthetic method
   public static final Symbols.Symbol $anonfun$foreachMethodParamAndArg$2(final List params$1) {
      return (Symbols.Symbol)params$1.last();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isFunctionMissingParamType$1(final Trees.ValDef x$2) {
      return x$2.tpt().isEmpty();
   }

   private static final Symbols.Symbol sym$1(final Trees.Tree tree$2) {
      return tree$2.symbol();
   }

   private static final boolean isVar$1(final Trees.Tree tree$2) {
      return tree$2.symbol().isVariable();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isThisTypeResult$2(final Symbols.Symbol sym$2, final Trees.Tree x$4) {
      Symbols.Symbol var2 = x$4.symbol();
      if (sym$2 == null) {
         if (var2 == null) {
            return true;
         }
      } else if (sym$2.equals(var2)) {
         return true;
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isThisTypeResult$1(final Symbols.Symbol sym$2, final List x$3) {
      if (x$3 == null) {
         throw null;
      } else {
         for(List exists_these = x$3; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
            Trees.Tree var3 = (Trees.Tree)exists_these.head();
            if ($anonfun$isThisTypeResult$2(sym$2, var3)) {
               return true;
            }
         }

         return false;
      }
   }

   private static final boolean checkSingle$1(final Symbols.Symbol sym, final Trees.Tree receiver$1, final Names.Name op$1) {
      Symbols.Symbol var3 = receiver$1.symbol();
      if (sym == null) {
         if (var3 == null) {
            return true;
         }
      } else if (sym.equals(var3)) {
         return true;
      }

      boolean var10000;
      if (receiver$1 instanceof Trees.Apply) {
         Precedence$ var4 = Precedence$.MODULE$;
         if (op$1 == null) {
            throw null;
         }

         var10000 = var4.apply(op$1.decode()) == 0;
      } else {
         var10000 = receiver$1.symbol() != null && (receiver$1.symbol().isGetter() || receiver$1.symbol().isField());
      }

      if (!var10000) {
         return false;
      } else {
         return true;
      }
   }

   private final boolean loop$1(final Types.Type mt, final Trees.Tree receiver$1, final Names.Name op$1) {
      while(true) {
         if (mt instanceof Types.MethodType) {
            Types.Type restpe = ((Types.MethodType)mt).resultType();
            if (restpe instanceof Types.ThisType) {
               return checkSingle$1(((Types.ThisType)restpe).sym(), receiver$1, op$1);
            }

            if (restpe instanceof Types.SingleType) {
               return checkSingle$1(((Types.SingleType)restpe).sym(), receiver$1, op$1);
            }

            mt = restpe;
         } else {
            if (!(mt instanceof Types.PolyType)) {
               return false;
            }

            mt = ((Types.PolyType)mt).resultType();
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stripNamedApplyBlock$1(final Trees.Tree x$5) {
      return x$5 instanceof Trees.ValDef;
   }

   private final boolean isVarPatternDeep0$1(final Trees.Tree tree) {
      while(tree instanceof Trees.Bind) {
         tree = ((Trees.Bind)tree).body();
      }

      if (tree instanceof Trees.Ident) {
         return this.isVarPattern(tree);
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$detectTypecheckedTree$1(final Trees.Tree x0$1) {
      if (x0$1 instanceof Trees.DefDef) {
         Trees.DefDef var1 = (Trees.DefDef)x0$1;
         return var1.mods().hasAccessorFlag() || var1.mods().isSynthetic();
      } else {
         return x0$1 instanceof Trees.MemberDef ? ((Trees.MemberDef)x0$1).hasExistingSymbol() : false;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$untypecheckedTreeBody$2(final Trees.DefDef x4$1, final Trees.Tree x0$2) {
      if (!(x0$2 instanceof Trees.ValDef)) {
         return false;
      } else {
         Trees.ValDef var2 = (Trees.ValDef)x0$2;
         Names.TermName var10000 = x4$1.name();
         Names.TermName var3 = var2.name().dropLocal();
         if (var10000 == null) {
            if (var3 == null) {
               return true;
            }
         } else if (var10000.equals(var3)) {
            return true;
         }

         return false;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$untypecheckedTreeBody$1(final TreeInfo $this, final List tbody$1, final Trees.Tree x0$1) {
      if (x0$1 instanceof Trees.ValDef ? true : x0$1 instanceof Trees.TypeDef) {
         return true;
      } else {
         if (x0$1 instanceof Trees.DefDef) {
            Trees.DefDef var3 = (Trees.DefDef)x0$1;
            if (var3.mods().hasAccessorFlag()) {
               if (!$this.global().nme().isSetterName(var3.name())) {
                  if (tbody$1 == null) {
                     throw null;
                  }

                  List exists_these = tbody$1;

                  boolean var10000;
                  while(true) {
                     if (exists_these.isEmpty()) {
                        var10000 = false;
                        break;
                     }

                     Trees.Tree var5 = (Trees.Tree)exists_these.head();
                     if ($anonfun$untypecheckedTreeBody$2(var3, var5)) {
                        var10000 = true;
                        break;
                     }

                     exists_these = (List)exists_these.tail();
                  }

                  Object var6 = null;
                  if (!var10000) {
                     return true;
                  }
               }

               return false;
            }
         }

         if (x0$1 instanceof Trees.MemberDef) {
            return !((Trees.MemberDef)x0$1).mods().isSynthetic();
         } else {
            return true;
         }
      }
   }

   private final List filterBody$1(final List body, final List tbody$1) {
      if (body == null) {
         throw null;
      } else {
         boolean filter_filterCommon_isFlipped = false;
         List filter_filterCommon_noneIn$1_l = body;

         Object var10000;
         while(true) {
            if (filter_filterCommon_noneIn$1_l.isEmpty()) {
               var10000 = scala.collection.immutable.Nil..MODULE$;
               break;
            }

            Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
            List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
            Trees.Tree var18 = (Trees.Tree)filter_filterCommon_noneIn$1_h;
            if ($anonfun$untypecheckedTreeBody$1(this, tbody$1, var18) != filter_filterCommon_isFlipped) {
               List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

               while(true) {
                  if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                     var10000 = filter_filterCommon_noneIn$1_l;
                     break;
                  }

                  Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                  var18 = (Trees.Tree)filter_filterCommon_noneIn$1_allIn$1_x;
                  if ($anonfun$untypecheckedTreeBody$1(this, tbody$1, var18) == filter_filterCommon_isFlipped) {
                     scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                     List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                     scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                     for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                        filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                        filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                     }

                     List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                     while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                        Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                        var18 = (Trees.Tree)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                        if ($anonfun$untypecheckedTreeBody$1(this, tbody$1, var18) != filter_filterCommon_isFlipped) {
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        } else {
                           while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                              scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                           }

                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        }
                     }

                     if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                        filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                     }

                     var10000 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                     Object var26 = null;
                     Object var29 = null;
                     Object var32 = null;
                     Object var35 = null;
                     Object var38 = null;
                     Object var41 = null;
                     Object var44 = null;
                     Object var47 = null;
                     break;
                  }

                  filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
               }

               Object var22 = null;
               Object var24 = null;
               Object var27 = null;
               Object var30 = null;
               Object var33 = null;
               Object var36 = null;
               Object var39 = null;
               Object var42 = null;
               Object var45 = null;
               Object var48 = null;
               break;
            }

            filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
         }

         Object var19 = null;
         Object var20 = null;
         Object var21 = null;
         Object var23 = null;
         Object var25 = null;
         Object var28 = null;
         Object var31 = null;
         Object var34 = null;
         Object var37 = null;
         Object var40 = null;
         Object var43 = null;
         Object var46 = null;
         Object var49 = null;
         List filter_filterCommon_result = (List)var10000;
         Statics.releaseFence();
         return filter_filterCommon_result;
      }
   }

   public static final Trees.Tree scala$reflect$internal$TreeInfo$$lazyValDefRhs$1(final Trees.Tree body) {
      if (body instanceof Trees.Block) {
         List var1 = ((Trees.Block)body).stats();
         if (var1 != null) {
            List var10000 = .MODULE$.List();
            if (var10000 == null) {
               throw null;
            }

            List unapplySeq_this = var10000;
            SeqOps var8 = SeqFactory.unapplySeq$(unapplySeq_this, var1);
            Object var7 = null;
            SeqOps var2 = var8;
            SeqFactory.UnapplySeqWrapper var9 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            new SeqFactory.UnapplySeqWrapper(var2);
            var9 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            var9 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            int lengthCompare$extension_len = 1;
            if (var2.lengthCompare(lengthCompare$extension_len) == 0) {
               var9 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var9 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int apply$extension_i = 0;
               Trees.Tree var3 = (Trees.Tree)var2.apply(apply$extension_i);
               if (var3 instanceof Trees.Assign) {
                  return ((Trees.Assign)var3).rhs();
               }
            }
         }
      }

      return body;
   }

   // $FF: synthetic method
   public static final Trees.ValDef $anonfun$untypecheckedTreeBody$4(final Trees.ValDef x2$1) {
      return x2$1;
   }

   // $FF: synthetic method
   public static final Trees.Tree $anonfun$untypecheckedTreeBody$3(final TreeInfo $this, final List tbody$1, final Trees.Tree x0$1) {
      if (x0$1 instanceof Trees.ValDef) {
         Trees.ValDef var3 = (Trees.ValDef)x0$1;
         Trees.Modifiers vmods = var3.mods();
         Names.TermName vname = var3.name();
         Trees.Tree vrhs = var3.rhs();
         if ($this.global().nme() == null) {
            throw null;
         }

         if (((Names.Name)vname).endsWith(" ")) {
            Option var10000 = tbody$1.collectFirst(new Serializable(vname, vmods, vrhs, var3) {
               private static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final TreeInfo $outer;
               private final Names.TermName vname$1;
               private final Trees.Modifiers vmods$1;
               private final Trees.Tree vrhs$1;
               private final Trees.ValDef x2$1;

               public final Object applyOrElse(final Trees.Tree x1, final Function1 default) {
                  if (x1 instanceof Trees.DefDef) {
                     Trees.DefDef var3 = (Trees.DefDef)x1;
                     Trees.Modifiers dmods = var3.mods();
                     Names.TermName dname = var3.name();
                     Trees.Tree drhs = var3.rhs();
                     Names.TermName var7 = this.vname$1.dropLocal();
                     if (dname == null) {
                        if (var7 != null) {
                           return default.apply(x1);
                        }
                     } else if (!dname.equals(var7)) {
                        return default.apply(x1);
                     }

                     long defDefMask = 524823L;
                     Trees.Modifiers vdMods = this.vmods$1.$amp$tilde(defDefMask).$bar(dmods.$amp(defDefMask).flags());
                     Trees.Tree vdRhs = this.vmods$1.isLazy() ? TreeInfo.scala$reflect$internal$TreeInfo$$lazyValDefRhs$1(drhs) : this.vrhs$1;
                     Trees.ValDef x$1 = this.x2$1;
                     Trees.Tree x$5 = this.$outer.global().copyValDef$default$4(x$1);
                     return this.$outer.global().copyValDef(x$1, vdMods, dname, x$5, vdRhs);
                  } else {
                     return default.apply(x1);
                  }
               }

               public final boolean isDefinedAt(final Trees.Tree x1) {
                  if (x1 instanceof Trees.DefDef) {
                     Names.TermName var10000 = ((Trees.DefDef)x1).name();
                     Names.TermName var2 = this.vname$1.dropLocal();
                     if (var10000 == null) {
                        if (var2 == null) {
                           return true;
                        }
                     } else if (var10000.equals(var2)) {
                        return true;
                     }
                  }

                  return false;
               }

               public {
                  if (TreeInfo.this == null) {
                     throw null;
                  } else {
                     this.$outer = TreeInfo.this;
                     this.vname$1 = vname$1;
                     this.vmods$1 = vmods$1;
                     this.vrhs$1 = vrhs$1;
                     this.x2$1 = x2$1;
                  }
               }
            });
            if (var10000 == null) {
               throw null;
            }

            Option getOrElse_this = var10000;
            return (Trees.Tree)(getOrElse_this.isEmpty() ? var3 : getOrElse_this.get());
         }
      }

      if (x0$1 instanceof Trees.DefDef) {
         Trees.DefDef var7 = (Trees.DefDef)x0$1;
         Trees.Modifiers mods = var7.mods();
         Names.TermName name = var7.name();
         Trees.Tree tpt = var7.tpt();
         Trees.Tree rhs = var7.rhs();
         if (mods.hasAccessorFlag()) {
            Trees.Modifiers vdMods = (!mods.hasStableFlag() ? mods.$bar(4096L) : mods.$amp$tilde(4194304L)).$amp$tilde(134217728L);
            return $this.global().new ValDef(vdMods, name, tpt, rhs);
         }
      }

      return x0$1;
   }

   private final List recoverBody$1(final List body, final List tbody$1) {
      if (body == null) {
         throw null;
      } else if (body == scala.collection.immutable.Nil..MODULE$) {
         return scala.collection.immutable.Nil..MODULE$;
      } else {
         Trees.Tree var7 = (Trees.Tree)body.head();
         scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$untypecheckedTreeBody$3(this, tbody$1, var7), scala.collection.immutable.Nil..MODULE$);
         scala.collection.immutable..colon.colon map_t = map_h;

         for(List map_rest = (List)body.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
            var7 = (Trees.Tree)map_rest.head();
            scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$untypecheckedTreeBody$3(this, tbody$1, var7), scala.collection.immutable.Nil..MODULE$);
            map_t.next_$eq(map_nx);
            map_t = map_nx;
         }

         Statics.releaseFence();
         return map_h;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$firstConstructor$1(final TreeInfo $this, final Trees.Tree x0$1) {
      if (x0$1 instanceof Trees.DefDef) {
         Trees.DefDef var2 = (Trees.DefDef)x0$1;
         return $this.global().nme().isConstructorName(var2.name());
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   public static final Trees.EmptyTree$ $anonfun$firstConstructor$2(final TreeInfo $this) {
      return $this.global().EmptyTree();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasUntypedPreSuperFields$1(final Trees.ValDef x$6) {
      return x$6.tpt().isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mayBeTypePat$1(final TreeInfo $this, final Trees.Tree tree) {
      return $this.mayBeTypePat(tree);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mayBeTypePat$2(final Trees.Tree x$7) {
      return x$7 instanceof Trees.Bind;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isSyntheticCase$1(final Trees.Tree x0$1) {
      return x0$1 instanceof Trees.DefTree ? ((Trees.DefTree)x0$1).symbol().isSynthetic() : false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isSequenceValued$1(final TreeInfo $this, final Trees.Tree tree) {
      return $this.isSequenceValued(tree);
   }

   // $FF: synthetic method
   public static final Trees.Tree $anonfun$flattenedPatternArgs$1(final TreeInfo $this, final Trees.Tree x) {
      return $this.unbind(x);
   }

   // $FF: synthetic method
   public static final Object $anonfun$isConstructorWithDefault$1$adapted(final Trees.ValDef x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$isConstructorWithDefault$1(x$1));
   }

   // $FF: synthetic method
   public static final Object $anonfun$isExprSafeToInline$1$adapted(final TreeInfo $this, final Trees.Tree tree) {
      return BoxesRunTime.boxToBoolean($anonfun$isExprSafeToInline$1($this, tree));
   }

   // $FF: synthetic method
   public static final Object $anonfun$isFunctionMissingParamType$1$adapted(final Trees.ValDef x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$isFunctionMissingParamType$1(x$2));
   }

   // $FF: synthetic method
   public static final Object $anonfun$isThisTypeResult$1$adapted(final Symbols.Symbol sym$2, final List x$3) {
      return BoxesRunTime.boxToBoolean($anonfun$isThisTypeResult$1(sym$2, x$3));
   }

   // $FF: synthetic method
   public static final Object $anonfun$stripNamedApplyBlock$1$adapted(final Trees.Tree x$5) {
      return BoxesRunTime.boxToBoolean($anonfun$stripNamedApplyBlock$1(x$5));
   }

   // $FF: synthetic method
   public static final Object $anonfun$firstConstructor$1$adapted(final TreeInfo $this, final Trees.Tree x0$1) {
      return BoxesRunTime.boxToBoolean($anonfun$firstConstructor$1($this, x0$1));
   }

   // $FF: synthetic method
   public static final Object $anonfun$hasUntypedPreSuperFields$1$adapted(final Trees.ValDef x$6) {
      return BoxesRunTime.boxToBoolean($anonfun$hasUntypedPreSuperFields$1(x$6));
   }

   // $FF: synthetic method
   public static final Object $anonfun$mayBeTypePat$1$adapted(final TreeInfo $this, final Trees.Tree tree) {
      return BoxesRunTime.boxToBoolean($anonfun$mayBeTypePat$1($this, tree));
   }

   // $FF: synthetic method
   public static final Object $anonfun$mayBeTypePat$2$adapted(final Trees.Tree x$7) {
      return BoxesRunTime.boxToBoolean($anonfun$mayBeTypePat$2(x$7));
   }

   // $FF: synthetic method
   public static final Object $anonfun$isSequenceValued$1$adapted(final TreeInfo $this, final Trees.Tree tree) {
      return BoxesRunTime.boxToBoolean($anonfun$isSequenceValued$1($this, tree));
   }

   // $FF: synthetic method
   public static final Object $anonfun$isThisTypeResult$2$adapted(final Symbols.Symbol sym$2, final Trees.Tree x$4) {
      return BoxesRunTime.boxToBoolean($anonfun$isThisTypeResult$2(sym$2, x$4));
   }

   // $FF: synthetic method
   public static final Object $anonfun$untypecheckedTreeBody$2$adapted(final Trees.DefDef x4$1, final Trees.Tree x0$2) {
      return BoxesRunTime.boxToBoolean($anonfun$untypecheckedTreeBody$2(x4$1, x0$2));
   }

   // $FF: synthetic method
   public static final Object $anonfun$untypecheckedTreeBody$1$adapted(final TreeInfo $this, final List tbody$1, final Trees.Tree x0$1) {
      return BoxesRunTime.boxToBoolean($anonfun$untypecheckedTreeBody$1($this, tbody$1, x0$1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class StripCast$ {
      // $FF: synthetic field
      private final TreeInfo $outer;

      public Some unapply(final Trees.Tree tree) {
         return new Some(this.$outer.stripCast(tree));
      }

      public StripCast$() {
         if (TreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = TreeInfo.this;
            super();
         }
      }
   }

   public class WildcardStarArg$ {
      // $FF: synthetic field
      private final TreeInfo $outer;

      public Option unapply(final Trees.Tree tree) {
         if (tree instanceof Trees.Typed) {
            Trees.Typed var2 = (Trees.Typed)tree;
            Trees.Tree expr = var2.expr();
            Trees.Tree var4 = var2.tpt();
            if (var4 instanceof Trees.Ident) {
               Names.Name var5 = ((Trees.Ident)var4).name();
               Names.TypeName var10000 = this.$outer.global().tpnme().WILDCARD_STAR();
               if (var10000 == null) {
                  if (var5 == null) {
                     return new Some(expr);
                  }
               } else if (var10000.equals(var5)) {
                  return new Some(expr);
               }
            }
         }

         return scala.None..MODULE$;
      }

      public WildcardStarArg$() {
         if (TreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = TreeInfo.this;
            super();
         }
      }
   }

   public final class Applied {
      private final Trees.Tree tree;

      public Trees.Tree tree() {
         return this.tree;
      }

      public Trees.Tree callee() {
         return this.loop$2(this.tree());
      }

      public Trees.Tree core() {
         Trees.Tree var1 = this.callee();
         if (var1 instanceof Trees.TypeApply) {
            return ((Trees.TypeApply)var1).fun();
         } else {
            return var1 instanceof Trees.AppliedTypeTree ? ((Trees.AppliedTypeTree)var1).tpt() : var1;
         }
      }

      public List targs() {
         Trees.Tree var1 = this.callee();
         if (var1 instanceof Trees.TypeApply) {
            return ((Trees.TypeApply)var1).args();
         } else {
            return (List)(var1 instanceof Trees.AppliedTypeTree ? ((Trees.AppliedTypeTree)var1).args() : scala.collection.immutable.Nil..MODULE$);
         }
      }

      public List argss() {
         return loop$3(this.tree());
      }

      private final Trees.Tree loop$2(final Trees.Tree tree) {
         while(tree instanceof Trees.Apply) {
            tree = ((Trees.Apply)tree).fun();
         }

         return tree;
      }

      private static final List loop$3(final Trees.Tree tree) {
         if (tree instanceof Trees.Apply) {
            Trees.Apply var1 = (Trees.Apply)tree;
            Trees.Tree fn = var1.fun();
            List args = var1.args();
            List var10000 = loop$3(fn);
            if (var10000 == null) {
               throw null;
            } else {
               return (List)StrictOptimizedSeqOps.appended$(var10000, args);
            }
         } else {
            return scala.collection.immutable.Nil..MODULE$;
         }
      }

      public Applied(final Trees.Tree tree) {
         this.tree = tree;
      }
   }

   public class Applied$ {
      // $FF: synthetic field
      private final TreeInfo $outer;

      public Applied apply(final Trees.Tree tree) {
         return this.$outer.new Applied(tree);
      }

      public Some unapply(final Applied applied) {
         return new Some(new Tuple3(applied.core(), applied.targs(), applied.argss()));
      }

      public Some unapply(final Trees.Tree tree) {
         TreeInfo var10001 = this.$outer;
         if (var10001 == null) {
            throw null;
         } else {
            TreeInfo dissectApplied_this = var10001;
            Applied var4 = dissectApplied_this.new Applied(tree);
            dissectApplied_this = null;
            return this.unapply(var4);
         }
      }

      public Applied$() {
         if (TreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = TreeInfo.this;
            super();
         }
      }
   }

   public class Application$ {
      // $FF: synthetic field
      private final TreeInfo $outer;

      public Option unapply(final Trees.Tree tree) {
         Applied ap = this.$outer.new Applied(tree);
         Trees.Tree core = ap.core();
         return (Option)(core == tree ? scala.None..MODULE$ : new Some(new Tuple3(core, ap.targs(), ap.argss())));
      }

      public Application$() {
         if (TreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = TreeInfo.this;
            super();
         }
      }
   }

   public class Unapplied$ {
      // $FF: synthetic field
      private final TreeInfo $outer;

      public Option unapply(final Trees.Tree tree) {
         while(true) {
            boolean var2 = false;
            Trees.Apply var3 = null;
            if (tree instanceof Trees.Apply) {
               var2 = true;
               var3 = (Trees.Apply)tree;
               Trees.Tree fun = var3.fun();
               List var5 = var3.args();
               if (var5 instanceof scala.collection.immutable..colon.colon) {
                  List var8;
                  boolean var13;
                  label46: {
                     label53: {
                        scala.collection.immutable..colon.colon var6 = (scala.collection.immutable..colon.colon)var5;
                        Trees.Tree var7 = (Trees.Tree)var6.head();
                        var8 = var6.next$access$1();
                        if (var7 instanceof Trees.Ident) {
                           Names.Name var9 = ((Trees.Ident)var7).name();
                           Names.TermName var10000 = this.$outer.global().nme().SELECTOR_DUMMY();
                           if (var10000 == null) {
                              if (var9 == null) {
                                 break label53;
                              }
                           } else if (var10000.equals(var9)) {
                              break label53;
                           }
                        }

                        label38: {
                           if (var7 instanceof Trees.Select) {
                              Trees.Tree var10 = ((Trees.Select)var7).qualifier();
                              if (var10 instanceof Trees.Ident) {
                                 Names.Name var11 = ((Trees.Ident)var10).name();
                                 Names.TermName var12 = this.$outer.global().nme().SELECTOR_DUMMY();
                                 if (var12 == null) {
                                    if (var11 == null) {
                                       break label38;
                                    }
                                 } else if (var12.equals(var11)) {
                                    break label38;
                                 }
                              }
                           }

                           var13 = false;
                           break label46;
                        }

                        var13 = true;
                        break label46;
                     }

                     var13 = true;
                  }

                  if (var13 && scala.collection.immutable.Nil..MODULE$.equals(var8)) {
                     return new Some(fun);
                  }
               }
            }

            if (!var2) {
               return scala.None..MODULE$;
            }

            tree = var3.fun();
         }
      }

      public Unapplied$() {
         if (TreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = TreeInfo.this;
            super();
         }
      }
   }

   public abstract class SeeThroughBlocks {
      // $FF: synthetic field
      public final TreeInfo $outer;

      public abstract Object unapplyImpl(final Trees.Tree x);

      public Object unapply(final Trees.Tree x) {
         if (x instanceof Trees.Block) {
            Trees.Block var2 = (Trees.Block)x;
            List var3 = var2.stats();
            Trees.Tree expr = var2.expr();
            if (scala.collection.immutable.Nil..MODULE$.equals(var3)) {
               return this.unapply(expr);
            }
         }

         return this.unapplyImpl(x);
      }

      // $FF: synthetic method
      public TreeInfo scala$reflect$internal$TreeInfo$SeeThroughBlocks$$$outer() {
         return this.$outer;
      }

      public SeeThroughBlocks() {
         if (TreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = TreeInfo.this;
            super();
         }
      }
   }

   public class IsTrue$ extends SeeThroughBlocks {
      public boolean unapplyImpl(final Trees.Tree x) {
         if (x instanceof Trees.Literal) {
            Constants.Constant var2 = ((Trees.Literal)x).value();
            if (var2 != null) {
               Object var3 = var2.value();
               if (BoxesRunTime.equals(true, var3)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public class IsFalse$ extends SeeThroughBlocks {
      public boolean unapplyImpl(final Trees.Tree x) {
         if (x instanceof Trees.Literal) {
            Constants.Constant var2 = ((Trees.Literal)x).value();
            if (var2 != null) {
               Object var3 = var2.value();
               if (BoxesRunTime.equals(false, var3)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private class LiteralNameOrAdapted$ {
      public Option unapply(final Trees.Tree tree) {
         if (tree instanceof Trees.Literal) {
            Constants.Constant var2 = ((Trees.Literal)tree).value();
            if (var2 != null) {
               Object name = var2.value();
               return new Some(name);
            }
         }

         if (tree instanceof Trees.Apply) {
            List var4 = ((Trees.Apply)tree).args();
            if (var4 != null) {
               List var10000 = .MODULE$.List();
               if (var10000 == null) {
                  throw null;
               }

               List unapplySeq_this = var10000;
               SeqOps var13 = SeqFactory.unapplySeq$(unapplySeq_this, var4);
               Object var12 = null;
               SeqOps var5 = var13;
               SeqFactory.UnapplySeqWrapper var14 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var5);
               var14 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var14 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int lengthCompare$extension_len = 1;
               if (var5.lengthCompare(lengthCompare$extension_len) == 0) {
                  var14 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var14 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int apply$extension_i = 0;
                  Trees.Tree var6 = (Trees.Tree)var5.apply(apply$extension_i);
                  if (var6 instanceof Trees.Literal) {
                     Constants.Constant var7 = ((Trees.Literal)var6).value();
                     if (var7 != null) {
                        Object name = var7.value();
                        return new Some(name);
                     }
                  }
               }
            }
         }

         return scala.None..MODULE$;
      }

      public LiteralNameOrAdapted$() {
      }
   }

   public class DynamicApplicationExtractor {
      private final Function1 nameTest;
      // $FF: synthetic field
      public final TreeInfo $outer;

      public Option unapply(final Trees.Tree tree) {
         boolean var2 = false;
         Trees.Apply var3 = null;
         if (tree instanceof Trees.Apply) {
            var2 = true;
            var3 = (Trees.Apply)tree;
            Trees.Tree var4 = var3.fun();
            List var5 = var3.args();
            if (var4 instanceof Trees.TypeApply) {
               Trees.Tree var6 = ((Trees.TypeApply)var4).fun();
               if (var6 instanceof Trees.Select) {
                  Trees.Select var7 = (Trees.Select)var6;
                  Trees.Tree qual = var7.qualifier();
                  Names.Name oper = var7.name();
                  if (var5 != null) {
                     List var10000 = .MODULE$.List();
                     if (var10000 == null) {
                        throw null;
                     }

                     List unapplySeq_this = var10000;
                     SeqOps var42 = SeqFactory.unapplySeq$(unapplySeq_this, var5);
                     Object var39 = null;
                     SeqOps var10 = var42;
                     SeqFactory.UnapplySeqWrapper var43 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     new SeqFactory.UnapplySeqWrapper(var10);
                     var43 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var43 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int lengthCompare$extension_len = 1;
                     if (var10.lengthCompare(lengthCompare$extension_len) == 0) {
                        var43 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        var43 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        int apply$extension_i = 0;
                        Trees.Tree var11 = (Trees.Tree)var10.apply(apply$extension_i);
                        if (var11 != null) {
                           Option var12 = this.scala$reflect$internal$TreeInfo$DynamicApplicationExtractor$$$outer().scala$reflect$internal$TreeInfo$$LiteralNameOrAdapted().unapply(var11);
                           if (!var12.isEmpty()) {
                              Object name = var12.get();
                              if (BoxesRunTime.unboxToBoolean(this.nameTest.apply(oper))) {
                                 return new Some(new Tuple2(qual, name));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var2) {
            Trees.Tree var14 = var3.fun();
            List var15 = var3.args();
            if (var14 instanceof Trees.Select) {
               Trees.Select var16 = (Trees.Select)var14;
               Trees.Tree qual = var16.qualifier();
               Names.Name oper = var16.name();
               if (var15 != null) {
                  List var48 = .MODULE$.List();
                  if (var48 == null) {
                     throw null;
                  }

                  List unapplySeq_this = var48;
                  SeqOps var49 = SeqFactory.unapplySeq$(unapplySeq_this, var15);
                  Object var40 = null;
                  SeqOps var19 = var49;
                  SeqFactory.UnapplySeqWrapper var50 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var62 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var19);
                  var50 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var50 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int lengthCompare$extension_len = 1;
                  if (var19.lengthCompare(lengthCompare$extension_len) == 0) {
                     var50 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var50 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int apply$extension_i = 0;
                     Trees.Tree var20 = (Trees.Tree)var19.apply(apply$extension_i);
                     if (var20 != null) {
                        Option var21 = this.scala$reflect$internal$TreeInfo$DynamicApplicationExtractor$$$outer().scala$reflect$internal$TreeInfo$$LiteralNameOrAdapted().unapply(var20);
                        if (!var21.isEmpty()) {
                           Object name = var21.get();
                           if (BoxesRunTime.unboxToBoolean(this.nameTest.apply(oper))) {
                              return new Some(new Tuple2(qual, name));
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var2) {
            Trees.Tree var23 = var3.fun();
            List var24 = var3.args();
            if (var23 instanceof Trees.Ident) {
               Names.Name oper = ((Trees.Ident)var23).name();
               if (var24 != null) {
                  List var55 = .MODULE$.List();
                  if (var55 == null) {
                     throw null;
                  }

                  List unapplySeq_this = var55;
                  SeqOps var56 = SeqFactory.unapplySeq$(unapplySeq_this, var24);
                  Object var41 = null;
                  SeqOps var26 = var56;
                  SeqFactory.UnapplySeqWrapper var57 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var63 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var26);
                  var57 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var57 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int lengthCompare$extension_len = 1;
                  if (var26.lengthCompare(lengthCompare$extension_len) == 0) {
                     var57 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var57 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int apply$extension_i = 0;
                     Trees.Tree var27 = (Trees.Tree)var26.apply(apply$extension_i);
                     if (var27 != null) {
                        Option var28 = this.scala$reflect$internal$TreeInfo$DynamicApplicationExtractor$$$outer().scala$reflect$internal$TreeInfo$$LiteralNameOrAdapted().unapply(var27);
                        if (!var28.isEmpty()) {
                           Object name = var28.get();
                           if (BoxesRunTime.unboxToBoolean(this.nameTest.apply(oper))) {
                              return new Some(new Tuple2(this.scala$reflect$internal$TreeInfo$DynamicApplicationExtractor$$$outer().global().EmptyTree(), name));
                           }
                        }
                     }
                  }
               }
            }
         }

         return scala.None..MODULE$;
      }

      // $FF: synthetic method
      public TreeInfo scala$reflect$internal$TreeInfo$DynamicApplicationExtractor$$$outer() {
         return this.$outer;
      }

      public DynamicApplicationExtractor(final Function1 nameTest) {
         this.nameTest = nameTest;
         if (TreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = TreeInfo.this;
            super();
         }
      }
   }

   public class DynamicUpdate$ extends DynamicApplicationExtractor {
      public DynamicUpdate$() {
         super(new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final TreeInfo $outer;

            public final boolean apply(final Names.Name x$8) {
               Names.TermName var2 = this.$outer.global().nme().updateDynamic();
               if (x$8 == null) {
                  if (var2 == null) {
                     return true;
                  }
               } else if (x$8.equals(var2)) {
                  return true;
               }

               return false;
            }

            public {
               if (DynamicUpdate$.this == null) {
                  throw null;
               } else {
                  this.$outer = DynamicUpdate$.this;
               }
            }
         });
      }
   }

   public class DynamicApplication$ extends DynamicApplicationExtractor {
      public DynamicApplication$() {
         super(new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final TreeInfo $outer;

            public final boolean apply(final Names.Name name) {
               return this.$outer.isApplyDynamicName(name);
            }

            public {
               if (DynamicApplication$.this == null) {
                  throw null;
               } else {
                  this.$outer = DynamicApplication$.this;
               }
            }
         });
      }
   }

   public class DynamicApplicationNamed$ extends DynamicApplicationExtractor {
      public DynamicApplicationNamed$() {
         super(new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final TreeInfo $outer;

            public final boolean apply(final Names.Name x$9) {
               Names.TermName var2 = this.$outer.global().nme().applyDynamicNamed();
               if (x$9 == null) {
                  if (var2 == null) {
                     return true;
                  }
               } else if (x$9.equals(var2)) {
                  return true;
               }

               return false;
            }

            public {
               if (DynamicApplicationNamed$.this == null) {
                  throw null;
               } else {
                  this.$outer = DynamicApplicationNamed$.this;
               }
            }
         });
      }
   }

   public class MacroImplReference$ {
      // $FF: synthetic field
      private final TreeInfo $outer;

      private Trees.Tree refPart(final Trees.Tree tree) {
         while(tree instanceof Trees.TypeApply) {
            tree = ((Trees.TypeApply)tree).fun();
         }

         if (tree instanceof Trees.RefTree) {
            return (Trees.Tree)((Trees.RefTree)tree);
         } else {
            return this.$outer.global().EmptyTree();
         }
      }

      public Option unapply(final Trees.Tree tree) {
         Trees.Tree var2 = this.refPart(tree);
         if (!(var2 instanceof Trees.RefTree)) {
            return scala.None..MODULE$;
         } else {
            Trees.RefTree var3 = (Trees.RefTree)var2;
            Trees.Tree qual = var3.qualifier();
            boolean isBundle = this.$outer.global().definitions().isMacroBundleType(qual.tpe());
            boolean var10000;
            if (isBundle) {
               var10000 = this.$outer.global().definitions().isBlackboxMacroBundleType(qual.tpe());
            } else {
               label38: {
                  List var7 = ((Trees.SymTree)var3).symbol().paramss();
                  if (var7 instanceof scala.collection.immutable..colon.colon) {
                     List var8 = (List)((scala.collection.immutable..colon.colon)var7).head();
                     if (var8 instanceof scala.collection.immutable..colon.colon) {
                        scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)var8;
                        Symbols.Symbol c = (Symbols.Symbol)var9.head();
                        List var11 = var9.next$access$1();
                        if (scala.collection.immutable.Nil..MODULE$.equals(var11) && this.$outer.global().definitions().isWhiteboxContextType(c.info())) {
                           var10000 = false;
                           break label38;
                        }
                     }
                  }

                  var10000 = true;
               }
            }

            boolean isBlackbox = var10000;
            Object var16;
            if (isBundle) {
               var16 = qual.tpe().typeSymbol();
            } else {
               Symbols.Symbol qualSym = (Symbols.Symbol)(qual.hasSymbolField() ? qual.symbol() : this.$outer.global().NoSymbol());
               var16 = qualSym.isModule() ? qualSym.moduleClass() : qualSym;
            }

            Symbols.Symbol owner = (Symbols.Symbol)var16;
            Some var17 = new Some;
            Tuple5 var10002 = new Tuple5;
            Boolean var10004 = isBundle;
            Boolean var10005 = isBlackbox;
            Symbols.Symbol var10007 = ((Trees.SymTree)var3).symbol();
            TreeInfo var10008 = this.$outer;
            if (var10008 == null) {
               throw null;
            } else {
               TreeInfo dissectApplied_this = var10008;
               Applied var18 = dissectApplied_this.new Applied(tree);
               dissectApplied_this = null;
               var10002.<init>(var10004, var10005, owner, var10007, var18.targs());
               var17.<init>(var10002);
               return var17;
            }
         }
      }

      public MacroImplReference$() {
         if (TreeInfo.this == null) {
            throw null;
         } else {
            this.$outer = TreeInfo.this;
            super();
         }
      }
   }
}
