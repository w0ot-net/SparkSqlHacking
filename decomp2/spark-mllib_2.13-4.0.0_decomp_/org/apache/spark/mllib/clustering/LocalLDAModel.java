package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.package;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.JValue;
import scala.MatchError;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\ruc\u0001\u0002#F\u0001AC\u0001b\u0019\u0001\u0003\u0006\u0004%\t\u0001\u001a\u0005\ti\u0002\u0011\t\u0011)A\u0005K\"Aa\u000f\u0001BC\u0002\u0013\u0005s\u000f\u0003\u0005\u007f\u0001\t\u0005\t\u0015!\u0003y\u0011)\t\t\u0001\u0001BC\u0002\u0013\u0005\u00131\u0001\u0005\u000b\u0003\u001f\u0001!\u0011!Q\u0001\n\u0005\u0015\u0001bCA\n\u0001\t\u0015\r\u0011\"\u0015J\u0003\u0007A!\"!\u0006\u0001\u0005\u0003\u0005\u000b\u0011BA\u0003\u0011!\t9\u0002\u0001C\u0001\u0013\u0006e\u0001BCA\u0016\u0001\u0001\u0007I\u0011A%\u0002.!Q\u0011Q\u0007\u0001A\u0002\u0013\u0005\u0011*a\u000e\t\u0011\u0005\r\u0003\u0001)Q\u0005\u0003_Aq!!\u0012\u0001\t\u0003\n9\u0005C\u0004\u0002R\u0001!\t%a\u0012\t\r\u0005U\u0003\u0001\"\u0011e\u0011\u001d\tI\u0006\u0001C!\u00037Bq!a\u001d\u0001\t\u0003\ti\u0003C\u0004\u0002|\u0001!\t!! \t\u000f\u0005\u0015\u0005\u0001\"\u0011\u0002\b\"9\u00111\u0016\u0001\u0005\u0002\u00055\u0006bBAV\u0001\u0011\u0005\u00111\u0019\u0005\b\u0003K\u0004A\u0011AAt\u0011\u001d\t)\u000f\u0001C\u0001\u0003[Dq!a=\u0001\t\u0013\t)\u0010C\u0004\u0003\u001a\u0001!\tAa\u0007\t\u000f\t\u0005\u0002\u0001\"\u0001\u0003$!9!\u0011\u0004\u0001\u0005\u0002\t=ra\u0002B\u001e\u000b\"\u0005!Q\b\u0004\u0007\t\u0016C\tAa\u0010\t\u000f\u0005]Q\u0004\"\u0001\u0003^\u001d9!qL\u000f\t\n\t\u0005da\u0002B3;!%!q\r\u0005\b\u0003/\u0001C\u0011\u0001B5\u0011%\u0011Y\u0007\tb\u0001\n\u0003\u0011i\u0007\u0003\u0005\u0003t\u0001\u0002\u000b\u0011\u0002B8\u0011%\u0011)\b\tb\u0001\n\u0003\u0011i\u0007\u0003\u0005\u0003x\u0001\u0002\u000b\u0011\u0002B8\r\u0019\u0011I\b\t!\u0003|!I!1\u0011\u0014\u0003\u0016\u0004%\ta\u001e\u0005\n\u0005\u000b3#\u0011#Q\u0001\naD!Ba\"'\u0005+\u0007I\u0011AA$\u0011)\u0011II\nB\tB\u0003%\u0011\u0011\n\u0005\b\u0003/1C\u0011\u0001BF\u0011%\u0011)JJA\u0001\n\u0003\u00119\nC\u0005\u0003\u001e\u001a\n\n\u0011\"\u0001\u0003 \"I!1\u0017\u0014\u0012\u0002\u0013\u0005!Q\u0017\u0005\n\u0005s3\u0013\u0011!C!\u0005[B\u0011Ba/'\u0003\u0003%\t!a\u0012\t\u0013\tuf%!A\u0005\u0002\t}\u0006\"\u0003BeM\u0005\u0005I\u0011\tBf\u0011%\u0011INJA\u0001\n\u0003\u0011Y\u000eC\u0005\u0003f\u001a\n\t\u0011\"\u0011\u0003h\"I!1\u001e\u0014\u0002\u0002\u0013\u0005#Q\u001e\u0005\n\u0005_4\u0013\u0011!C!\u0005cD\u0011Ba='\u0003\u0003%\tE!>\b\u0013\te\b%!A\t\u0002\tmh!\u0003B=A\u0005\u0005\t\u0012\u0001B\u007f\u0011\u001d\t9\"\u000fC\u0001\u0007\u0017A\u0011Ba<:\u0003\u0003%)E!=\t\u0013\r5\u0011(!A\u0005\u0002\u000e=\u0001\"CB\u000bs\u0005\u0005I\u0011QB\f\u0011%\u0019)#OA\u0001\n\u0013\u00199\u0003C\u0004\u0002\u0006\u0002\"\taa\f\t\u000f\ru\u0002\u0005\"\u0001\u0004@!91QH\u000f\u0005B\r-\u0003BCB*;E\u0005I\u0011A%\u0004V!I1QE\u000f\u0002\u0002\u0013%1q\u0005\u0002\u000e\u0019>\u001c\u0017\r\u001c'E\u00036{G-\u001a7\u000b\u0005\u0019;\u0015AC2mkN$XM]5oO*\u0011\u0001*S\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u0015.\u000bQa\u001d9be.T!\u0001T'\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0015aA8sO\u000e\u00011c\u0001\u0001R+B\u0011!kU\u0007\u0002\u000b&\u0011A+\u0012\u0002\t\u0019\u0012\u000bUj\u001c3fYB\u0011a\u000b\u0019\b\u0003/vs!\u0001W.\u000e\u0003eS!AW(\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0016!B:dC2\f\u0017B\u00010`\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011\u0001X\u0005\u0003C\n\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!AX0\u0002\rQ|\u0007/[2t+\u0005)\u0007C\u00014j\u001b\u00059'B\u00015H\u0003\u0019a\u0017N\\1mO&\u0011!n\u001a\u0002\u0007\u001b\u0006$(/\u001b=)\u0007\u0005a'\u000f\u0005\u0002na6\taN\u0003\u0002p\u0013\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Et'!B*j]\u000e,\u0017%A:\u0002\u000bEr3G\f\u0019\u0002\u000fQ|\u0007/[2tA!\u001a!\u0001\u001c:\u0002!\u0011|7mQ8oG\u0016tGO]1uS>tW#\u0001=\u0011\u0005\u0019L\u0018B\u0001>h\u0005\u00191Vm\u0019;pe\"\u001a1\u0001\u001c?\"\u0003u\fQ!\r\u00186]A\n\u0011\u0003Z8d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8!Q\r!A\u000e`\u0001\u0013i>\u0004\u0018nY\"p]\u000e,g\u000e\u001e:bi&|g.\u0006\u0002\u0002\u0006A!\u0011qAA\u0005\u001b\u0005y\u0016bAA\u0006?\n1Ai\\;cY\u0016D3!\u00027}\u0003M!x\u000e]5d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8!Q\r1A\u000e`\u0001\u000bO\u0006lW.Y*iCB,\u0017aC4b[6\f7\u000b[1qK\u0002\na\u0001P5oSRtDCCA\u000e\u0003;\t\t#!\n\u0002*A\u0011!\u000b\u0001\u0005\u0006G&\u0001\r!\u001a\u0015\u0005\u0003;a'\u000fC\u0003w\u0013\u0001\u0007\u0001\u0010\u000b\u0003\u0002\"1d\bbBA\u0001\u0013\u0001\u0007\u0011Q\u0001\u0015\u0005\u0003KaG\u0010C\u0005\u0002\u0014%\u0001\n\u00111\u0001\u0002\u0006\u0005!1/Z3e+\t\ty\u0003\u0005\u0003\u0002\b\u0005E\u0012bAA\u001a?\n!Aj\u001c8h\u0003!\u0019X-\u001a3`I\u0015\fH\u0003BA\u001d\u0003\u007f\u0001B!a\u0002\u0002<%\u0019\u0011QH0\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003\u0003Z\u0011\u0011!a\u0001\u0003_\t1\u0001\u001f\u00132\u0003\u0015\u0019X-\u001a3!\u0003\u0005YWCAA%!\u0011\t9!a\u0013\n\u0007\u00055sLA\u0002J]RD3!\u00047s\u0003%1xnY1c'&TX\rK\u0002\u000fYJ\fA\u0002^8qS\u000e\u001cX*\u0019;sSbD3a\u00047s\u00039!Wm]2sS\n,Gk\u001c9jGN$B!!\u0018\u0002nA1\u0011qAA0\u0003GJ1!!\u0019`\u0005\u0015\t%O]1z!!\t9!!\u001a\u0002j\u0005-\u0014bAA4?\n1A+\u001e9mKJ\u0002b!a\u0002\u0002`\u0005%\u0003CBA\u0004\u0003?\n)\u0001C\u0004\u0002pA\u0001\r!!\u0013\u0002!5\f\u0007\u0010V3s[N\u0004VM\u001d+pa&\u001c\u0007f\u0001\tme\u00069q-\u001a;TK\u0016$\u0007\u0006B\tm\u0003o\n#!!\u001f\u0002\u000bIrCG\f\u0019\u0002\u000fM,GoU3fIR!\u0011qPAA\u001b\u0005\u0001\u0001bBA\u0016%\u0001\u0007\u0011q\u0006\u0015\u0005%1\f9(\u0001\u0003tCZ,GCBA\u001d\u0003\u0013\u000b)\nC\u0004\u0002\fN\u0001\r!!$\u0002\u0005M\u001c\u0007\u0003BAH\u0003#k\u0011!S\u0005\u0004\u0003'K%\u0001D*qCJ\\7i\u001c8uKb$\bbBAL'\u0001\u0007\u0011\u0011T\u0001\u0005a\u0006$\b\u000e\u0005\u0003\u0002\u001c\u0006\rf\u0002BAO\u0003?\u0003\"\u0001W0\n\u0007\u0005\u0005v,\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003K\u000b9K\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003C{\u0006fA\nmy\u0006iAn\\4MS.,G.\u001b5p_\u0012$B!!\u0002\u00020\"9\u0011\u0011\u0017\u000bA\u0002\u0005M\u0016!\u00033pGVlWM\u001c;t!\u0019\t),a/\u0002@6\u0011\u0011q\u0017\u0006\u0004\u0003sK\u0015a\u0001:eI&!\u0011QXA\\\u0005\r\u0011F\t\u0012\t\b\u0003\u000f\t)'a\fyQ\r!B\u000e \u000b\u0005\u0003\u000b\t)\rC\u0004\u00022V\u0001\r!a2\u0011\u000f\u0005%\u00171[Alq6\u0011\u00111\u001a\u0006\u0005\u0003\u001b\fy-\u0001\u0003kCZ\f'bAAi\u0013\u0006\u0019\u0011\r]5\n\t\u0005U\u00171\u001a\u0002\f\u0015\u00064\u0018\rU1jeJ#E\t\u0005\u0003\u0002Z\u0006\u0005XBAAn\u0015\u0011\ti.a8\u0002\t1\fgn\u001a\u0006\u0003\u0003\u001bLA!a\r\u0002\\\"\u001aQ\u0003\u001c?\u0002\u001b1|w\rU3sa2,\u00070\u001b;z)\u0011\t)!!;\t\u000f\u0005Ef\u00031\u0001\u00024\"\u001aa\u0003\u001c?\u0015\t\u0005\u0015\u0011q\u001e\u0005\b\u0003c;\u0002\u0019AAdQ\r9B\u000e`\u0001\u0013Y><G*[6fY&Dwn\u001c3C_VtG\r\u0006\t\u0002\u0006\u0005]\u0018\u0011`A\u007f\u0005\u0003\u0011\u0019B!\u0006\u0003\u0018!9\u0011\u0011\u0017\rA\u0002\u0005M\u0006BBA~1\u0001\u0007\u00010A\u0003bYBD\u0017\rC\u0004\u0002\u0000b\u0001\r!!\u0002\u0002\u0007\u0015$\u0018\rC\u0004\u0003\u0004a\u0001\rA!\u0002\u0002\r1\fWN\u00193b!\u0019\u00119Aa\u0004\u0002\u00065\u0011!\u0011\u0002\u0006\u0004Q\n-!B\u0001B\u0007\u0003\u0019\u0011'/Z3{K&!!\u0011\u0003B\u0005\u0005-!UM\\:f\u001b\u0006$(/\u001b=\t\u000f\u0005M\u0001\u00041\u0001\u0002\u0006!9\u0011Q\t\rA\u0002\u0005%\u0003bBA)1\u0001\u0007\u0011qF\u0001\u0013i>\u0004\u0018n\u0019#jgR\u0014\u0018NY;uS>t7\u000f\u0006\u0003\u00024\nu\u0001bBAY3\u0001\u0007\u00111\u0017\u0015\u000431\u0014\u0018!\u0005;pa&\u001cG)[:ue&\u0014W\u000f^5p]R\u0019\u0001P!\n\t\r\t\u001d\"\u00041\u0001y\u0003!!wnY;nK:$\b\u0006\u0002\u000em\u0005W\t#A!\f\u0002\u000bIr\u0003G\f\u0019\u0015\t\u0005\u001d'\u0011\u0007\u0005\b\u0003c[\u0002\u0019AAdQ\u0011YBN!\u000e\"\u0005\t]\u0012!B\u0019/i9\n\u0004f\u0001\u0001me\u0006iAj\\2bY2#\u0015)T8eK2\u0004\"AU\u000f\u0014\u000fu\u0011\tEa\u0012\u0003TA!\u0011q\u0001B\"\u0013\r\u0011)e\u0018\u0002\u0007\u0003:L(+\u001a4\u0011\r\t%#qJA\u000e\u001b\t\u0011YEC\u0002\u0003N\u001d\u000bA!\u001e;jY&!!\u0011\u000bB&\u0005\u0019au.\u00193feB!!Q\u000bB.\u001b\t\u00119F\u0003\u0003\u0003Z\u0005}\u0017AA5p\u0013\r\t'q\u000b\u000b\u0003\u0005{\tAbU1wK2{\u0017\r\u001a,2?B\u00022Aa\u0019!\u001b\u0005i\"\u0001D*bm\u0016du.\u00193Wc}\u00034c\u0001\u0011\u0003BQ\u0011!\u0011M\u0001\u0012i\"L7OR8s[\u0006$h+\u001a:tS>tWC\u0001B8!\u0011\tIN!\u001d\n\t\u0005\u0015\u00161\\\u0001\u0013i\"L7OR8s[\u0006$h+\u001a:tS>t\u0007%A\u0007uQ&\u001c8\t\\1tg:\u000bW.Z\u0001\u000fi\"L7o\u00117bgNt\u0015-\\3!\u0005\u0011!\u0015\r^1\u0014\r\u0019\u0012\tE! V!\u0011\t9Aa \n\u0007\t\u0005uLA\u0004Qe>$Wo\u0019;\u0002\u000bQ|\u0007/[2\u0002\rQ|\u0007/[2!\u0003\u0015Ig\u000eZ3y\u0003\u0019Ig\u000eZ3yAQ1!Q\u0012BI\u0005'\u00032Aa$'\u001b\u0005\u0001\u0003B\u0002BBW\u0001\u0007\u0001\u0010C\u0004\u0003\b.\u0002\r!!\u0013\u0002\t\r|\u0007/\u001f\u000b\u0007\u0005\u001b\u0013IJa'\t\u0011\t\rE\u0006%AA\u0002aD\u0011Ba\"-!\u0003\u0005\r!!\u0013\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!\u0011\u0015\u0016\u0004q\n\r6F\u0001BS!\u0011\u00119Ka,\u000e\u0005\t%&\u0002\u0002BV\u0005[\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005=|\u0016\u0002\u0002BY\u0005S\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"Aa.+\t\u0005%#1U\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!!\u0011\u0019Bd!\u0011\t9Aa1\n\u0007\t\u0015wLA\u0002B]fD\u0011\"!\u00112\u0003\u0003\u0005\r!!\u0013\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!4\u0011\r\t='Q\u001bBa\u001b\t\u0011\tNC\u0002\u0003T~\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u00119N!5\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005;\u0014\u0019\u000f\u0005\u0003\u0002\b\t}\u0017b\u0001Bq?\n9!i\\8mK\u0006t\u0007\"CA!g\u0005\u0005\t\u0019\u0001Ba\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\t=$\u0011\u001e\u0005\n\u0003\u0003\"\u0014\u0011!a\u0001\u0003\u0013\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0013\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005_\na!Z9vC2\u001cH\u0003\u0002Bo\u0005oD\u0011\"!\u00118\u0003\u0003\u0005\rA!1\u0002\t\u0011\u000bG/\u0019\t\u0004\u0005\u001fK4#B\u001d\u0003\u0000\nM\u0003#CB\u0001\u0007\u000fA\u0018\u0011\nBG\u001b\t\u0019\u0019AC\u0002\u0004\u0006}\u000bqA];oi&lW-\u0003\u0003\u0004\n\r\r!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeQ\u0011!1`\u0001\u0006CB\u0004H.\u001f\u000b\u0007\u0005\u001b\u001b\tba\u0005\t\r\t\rE\b1\u0001y\u0011\u001d\u00119\t\u0010a\u0001\u0003\u0013\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0004\u001a\r\u0005\u0002CBA\u0004\u00077\u0019y\"C\u0002\u0004\u001e}\u0013aa\u00149uS>t\u0007cBA\u0004\u0003KB\u0018\u0011\n\u0005\n\u0007Gi\u0014\u0011!a\u0001\u0005\u001b\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019I\u0003\u0005\u0003\u0002Z\u000e-\u0012\u0002BB\u0017\u00037\u0014aa\u00142kK\u000e$HCDA\u001d\u0007c\u0019\u0019d!\u000e\u00048\re21\b\u0005\b\u0003\u0017{\u0004\u0019AAG\u0011\u001d\t9j\u0010a\u0001\u00033Ca!!\u0016@\u0001\u0004)\u0007\"\u0002<@\u0001\u0004A\bbBA\u0001\u007f\u0001\u0007\u0011Q\u0001\u0005\b\u0003'y\u0004\u0019AA\u0003\u0003\u0011aw.\u00193\u0015\u0019\u0005m1\u0011IB\"\u0007\u000b\u001a9e!\u0013\t\u000f\u0005-\u0005\t1\u0001\u0002\u000e\"9\u0011q\u0013!A\u0002\u0005e\u0005\"\u0002<A\u0001\u0004A\bbBA\u0001\u0001\u0002\u0007\u0011Q\u0001\u0005\b\u0003'\u0001\u0005\u0019AA\u0003)\u0019\tYb!\u0014\u0004P!9\u00111R!A\u0002\u00055\u0005bBAL\u0003\u0002\u0007\u0011\u0011\u0014\u0015\u0004\u00032d\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0002\u0004X)\"\u0011Q\u0001BRQ\riB\u000e \u0015\u000491d\b"
)
public class LocalLDAModel extends LDAModel implements Serializable {
   private final Matrix topics;
   private final Vector docConcentration;
   private final double topicConcentration;
   private final double gammaShape;
   private long seed;

   public static LocalLDAModel load(final SparkContext sc, final String path) {
      return LocalLDAModel$.MODULE$.load(sc, path);
   }

   public Matrix topics() {
      return this.topics;
   }

   public Vector docConcentration() {
      return this.docConcentration;
   }

   public double topicConcentration() {
      return this.topicConcentration;
   }

   public double gammaShape() {
      return this.gammaShape;
   }

   public long seed() {
      return this.seed;
   }

   public void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   public int k() {
      return this.topics().numCols();
   }

   public int vocabSize() {
      return this.topics().numRows();
   }

   public Matrix topicsMatrix() {
      return this.topics();
   }

   public Tuple2[] describeTopics(final int maxTermsPerTopic) {
      DenseMatrix brzTopics = this.topics().asBreeze().toDenseMatrix$mcD$sp(.MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      return (Tuple2[])scala.package..MODULE$.Range().apply(0, this.k()).map((topicIndex) -> $anonfun$describeTopics$1(brzTopics, maxTermsPerTopic, BoxesRunTime.unboxToInt(topicIndex))).toArray(.MODULE$.apply(Tuple2.class));
   }

   public long getSeed() {
      return this.seed();
   }

   public LocalLDAModel setSeed(final long seed) {
      this.seed_$eq(seed);
      return this;
   }

   public void save(final SparkContext sc, final String path) {
      LocalLDAModel.SaveLoadV1_0$.MODULE$.save(sc, path, this.topicsMatrix(), this.docConcentration(), this.topicConcentration(), this.gammaShape());
   }

   public double logLikelihood(final RDD documents) {
      return this.logLikelihoodBound(documents, this.docConcentration(), this.topicConcentration(), this.topicsMatrix().asBreeze().toDenseMatrix$mcD$sp(.MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()), this.gammaShape(), this.k(), (long)this.vocabSize());
   }

   public double logLikelihood(final JavaPairRDD documents) {
      return this.logLikelihood(documents.rdd());
   }

   public double logPerplexity(final RDD documents) {
      double corpusTokenCount = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(documents.map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$logPerplexity$1(x0$1)), .MODULE$.Double())).sum();
      return -this.logLikelihood(documents) / corpusTokenCount;
   }

   public double logPerplexity(final JavaPairRDD documents) {
      return this.logPerplexity(documents.rdd());
   }

   private double logLikelihoodBound(final RDD documents, final Vector alpha, final double eta, final DenseMatrix lambda, final double gammaShape, final int k, final long vocabSize) {
      DenseVector brzAlpha = alpha.asBreeze().toDenseVector$mcD$sp(.MODULE$.Double());
      DenseMatrix Elogbeta = (DenseMatrix)LDAUtils$.MODULE$.dirichletExpectation((DenseMatrix)lambda.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM())).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM());
      Broadcast ElogbetaBc = documents.sparkContext().broadcast(Elogbeta, .MODULE$.apply(DenseMatrix.class));
      long gammaSeed = this.seed();
      double corpusPart = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(documents.filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$logLikelihoodBound$1(x$3))).map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$logLikelihoodBound$2(ElogbetaBc, brzAlpha, gammaShape, k, gammaSeed, x0$1)), .MODULE$.Double())).sum();
      ElogbetaBc.destroy();
      double sumEta = eta * (double)vocabSize;
      double topicsPart = BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(((ImmutableNumericOps)(new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(eta)))).$minus(lambda, breeze.linalg.operators.HasOps..MODULE$.s_dm_op_Double_OpSub())).$times$colon$times(Elogbeta, breeze.linalg.operators.HasOps..MODULE$.op_DM_DM_Double_OpMulScalar()), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.canTraverseValues()))) + BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(((ImmutableNumericOps)breeze.numerics.package.lgamma..MODULE$.apply(lambda, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseMatrix..MODULE$.scalarOf(), breeze.numerics.package.lgamma.lgammaImplDouble..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_DM$mDDc$sp(.MODULE$.Double())))).$minus(BoxesRunTime.boxToDouble(breeze.numerics.package.lgamma..MODULE$.apply$mDDc$sp(eta, breeze.numerics.package.lgamma.lgammaImplDouble..MODULE$)), breeze.linalg.operators.HasOps..MODULE$.op_DM_S_Double_OpSub()), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.canTraverseValues()))) + BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply((new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(breeze.numerics.package.lgamma..MODULE$.apply$mDDc$sp(sumEta, breeze.numerics.package.lgamma.lgammaImplDouble..MODULE$))))).$minus(breeze.numerics.package.lgamma..MODULE$.apply(breeze.linalg.sum..MODULE$.apply(lambda.apply(scala.package..MODULE$.$colon$colon(), breeze.linalg..times..MODULE$, breeze.linalg.Broadcaster..MODULE$.canBroadcastColumns(breeze.linalg.operators.HasOps..MODULE$.handholdCanMapRows_DM())), breeze.linalg.sum..MODULE$.vectorizeCols_Double(breeze.linalg.sum..MODULE$.helper_Double())), breeze.linalg.operators.HasOps..MODULE$.liftUFunc(breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.lgamma.lgammaImplDouble..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double())), breeze.linalg.operators.HasOps..MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()))), breeze.linalg.operators.HasOps..MODULE$.impl_Op_LHS_DVt_eq_R_cast(breeze.linalg.operators.HasOps..MODULE$.s_dm_op_Double_OpSub())), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.canTraverseValues())));
      return corpusPart + topicsPart;
   }

   public RDD topicDistributions(final RDD documents) {
      DenseMatrix expElogbeta = (DenseMatrix)breeze.numerics.package.exp..MODULE$.apply(LDAUtils$.MODULE$.dirichletExpectation((DenseMatrix)this.topicsMatrix().asBreeze().toDenseMatrix$mcD$sp(.MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM())).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM()), breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseMatrix..MODULE$.scalarOf(), breeze.numerics.package.exp.expDoubleImpl..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_DM$mDDc$sp(.MODULE$.Double())));
      Broadcast expElogbetaBc = documents.sparkContext().broadcast(expElogbeta, .MODULE$.apply(DenseMatrix.class));
      breeze.linalg.Vector docConcentrationBrz = this.docConcentration().asBreeze();
      double gammaShape = this.gammaShape();
      int k = this.k();
      long gammaSeed = this.seed();
      return documents.map((x0$1) -> {
         if (x0$1 != null) {
            long id = x0$1._1$mcJ$sp();
            Vector termCounts = (Vector)x0$1._2();
            if (true && termCounts != null) {
               if (termCounts.numNonzeros() == 0) {
                  return new Tuple2(BoxesRunTime.boxToLong(id), Vectors$.MODULE$.zeros(k));
               }

               Tuple3 var18 = OnlineLDAOptimizer$.MODULE$.variationalTopicInference(termCounts, (DenseMatrix)expElogbetaBc.value(), docConcentrationBrz, gammaShape, k, gammaSeed + id);
               if (var18 != null) {
                  DenseVector gamma = (DenseVector)var18._1();
                  return new Tuple2(BoxesRunTime.boxToLong(id), Vectors$.MODULE$.dense(((DenseVector)breeze.linalg.normalize..MODULE$.apply(gamma, BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.normalize..MODULE$.normalizeDoubleImpl(breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())))).toArray$mcD$sp(.MODULE$.Double())));
               }

               throw new MatchError(var18);
            }
         }

         throw new MatchError(x0$1);
      }, .MODULE$.apply(Tuple2.class));
   }

   public Vector topicDistribution(final Vector document) {
      long gammaSeed = this.seed();
      DenseMatrix expElogbeta = (DenseMatrix)breeze.numerics.package.exp..MODULE$.apply(LDAUtils$.MODULE$.dirichletExpectation((DenseMatrix)this.topicsMatrix().asBreeze().toDenseMatrix$mcD$sp(.MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM())).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM()), breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseMatrix..MODULE$.scalarOf(), breeze.numerics.package.exp.expDoubleImpl..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_DM$mDDc$sp(.MODULE$.Double())));
      if (document.numNonzeros() == 0) {
         return Vectors$.MODULE$.zeros(this.k());
      } else {
         Tuple3 var7 = OnlineLDAOptimizer$.MODULE$.variationalTopicInference(document, expElogbeta, this.docConcentration().asBreeze(), this.gammaShape(), this.k(), gammaSeed);
         if (var7 != null) {
            DenseVector gamma = (DenseVector)var7._1();
            return Vectors$.MODULE$.dense(((DenseVector)breeze.linalg.normalize..MODULE$.apply(gamma, BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.normalize..MODULE$.normalizeDoubleImpl(breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())))).toArray$mcD$sp(.MODULE$.Double()));
         } else {
            throw new MatchError(var7);
         }
      }
   }

   public JavaPairRDD topicDistributions(final JavaPairRDD documents) {
      RDD distributions = this.topicDistributions(documents.rdd());
      return org.apache.spark.api.java.JavaPairRDD..MODULE$.fromRDD(distributions, .MODULE$.apply(Long.class), .MODULE$.apply(Vector.class));
   }

   // $FF: synthetic method
   public static final double $anonfun$describeTopics$2(final Tuple2 x$1) {
      return -x$1._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$describeTopics$1(final DenseMatrix brzTopics$1, final int maxTermsPerTopic$1, final int topicIndex) {
      DenseVector topic = (DenseVector)breeze.linalg.normalize..MODULE$.apply(brzTopics$1.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(topicIndex), breeze.linalg.operators.HasOps..MODULE$.canSliceCol()), BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.normalize..MODULE$.normalizeDoubleImpl(breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())));
      Tuple2 var6 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(topic.toArray$mcD$sp(.MODULE$.Double())))), (x$1) -> BoxesRunTime.boxToDouble($anonfun$describeTopics$2(x$1)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)), maxTermsPerTopic$1)), scala.Predef..MODULE$.$conforms(), .MODULE$.Double(), .MODULE$.Int());
      if (var6 != null) {
         double[] termWeights = (double[])var6._1();
         int[] terms = (int[])var6._2();
         Tuple2 var5 = new Tuple2(termWeights, terms);
         double[] termWeights = (double[])var5._1();
         int[] terms = (int[])var5._2();
         return new Tuple2(terms, termWeights);
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$logPerplexity$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Vector termCounts = (Vector)x0$1._2();
         return BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(termCounts.toArray()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$logLikelihoodBound$1(final Tuple2 x$3) {
      return ((Vector)x$3._2()).numNonzeros() > 0;
   }

   // $FF: synthetic method
   public static final double $anonfun$logLikelihoodBound$2(final Broadcast ElogbetaBc$1, final DenseVector brzAlpha$1, final double gammaShape$1, final int k$1, final long gammaSeed$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long id = x0$1._1$mcJ$sp();
         Vector termCounts = (Vector)x0$1._2();
         if (true && termCounts != null) {
            DenseMatrix localElogbeta = (DenseMatrix)ElogbetaBc$1.value();
            DoubleRef docBound = DoubleRef.create((double)0.0F);
            Tuple3 var21 = OnlineLDAOptimizer$.MODULE$.variationalTopicInference(termCounts, (DenseMatrix)breeze.numerics.package.exp..MODULE$.apply(localElogbeta, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseMatrix..MODULE$.scalarOf(), breeze.numerics.package.exp.expDoubleImpl..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_DM$mDDc$sp(.MODULE$.Double()))), brzAlpha$1, gammaShape$1, k$1, gammaSeed$1 + id);
            if (var21 != null) {
               DenseVector gammad = (DenseVector)var21._1();
               if (gammad != null) {
                  DenseVector Elogthetad = LDAUtils$.MODULE$.dirichletExpectation(gammad);
                  termCounts.foreachNonZero((JFunction2.mcVID.sp)(x0$2, x1$1) -> {
                     Tuple2.mcID.sp var7 = new Tuple2.mcID.sp(x0$2, x1$1);
                     if (var7 != null) {
                        int idx = ((Tuple2)var7)._1$mcI$sp();
                        double count = ((Tuple2)var7)._2$mcD$sp();
                        docBound.elem += count * LDAUtils$.MODULE$.logSumExp((DenseVector)Elogthetad.$plus(((ImmutableNumericOps)localElogbeta.apply(BoxesRunTime.boxToInteger(idx), scala.package..MODULE$.$colon$colon(), breeze.linalg.operators.HasOps..MODULE$.canSliceRow())).t(breeze.linalg.operators.HasOps..MODULE$.canUntranspose()), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_DV_DV_eq_DV_Double()));
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     } else {
                        throw new MatchError(var7);
                     }
                  });
                  docBound.elem += BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(((ImmutableNumericOps)brzAlpha$1.$minus(gammad, breeze.linalg.operators.HasOps..MODULE$.impl_OpSub_DV_DV_eq_DV_Double())).$times$colon$times(Elogthetad, breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_DV_eq_DV_Double_OpMulScalar()), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues())));
                  docBound.elem += BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(((ImmutableNumericOps)breeze.numerics.package.lgamma..MODULE$.apply(gammad, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.lgamma.lgammaImplDouble..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double())))).$minus(breeze.numerics.package.lgamma..MODULE$.apply(brzAlpha$1, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.lgamma.lgammaImplDouble..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()))), breeze.linalg.operators.HasOps..MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues())));
                  docBound.elem += breeze.numerics.package.lgamma..MODULE$.apply$mDDc$sp(BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(brzAlpha$1, breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))), breeze.numerics.package.lgamma.lgammaImplDouble..MODULE$) - breeze.numerics.package.lgamma..MODULE$.apply$mDDc$sp(BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(gammad, breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))), breeze.numerics.package.lgamma.lgammaImplDouble..MODULE$);
                  return docBound.elem;
               }
            }

            throw new MatchError(var21);
         }
      }

      throw new MatchError(x0$1);
   }

   public LocalLDAModel(final Matrix topics, final Vector docConcentration, final double topicConcentration, final double gammaShape) {
      this.topics = topics;
      this.docConcentration = docConcentration;
      this.topicConcentration = topicConcentration;
      this.gammaShape = gammaShape;
      this.seed = org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.clustering.LocalLDAModel";

      public String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final String path, final Matrix topicsMatrix, final Vector docConcentration, final double topicConcentration, final double gammaShape) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         int k = topicsMatrix.numCols();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("k"), BoxesRunTime.boxToInteger(k)), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("vocabSize"), BoxesRunTime.boxToInteger(topicsMatrix.numRows())), (x) -> $anonfun$save$5(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("docConcentration"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(docConcentration.toArray()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$save$7(BoxesRunTime.unboxToDouble(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("topicConcentration"), BoxesRunTime.boxToDouble(topicConcentration)), (x) -> $anonfun$save$8(BoxesRunTime.unboxToDouble(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("gammaShape"), BoxesRunTime.boxToDouble(gammaShape)), (x) -> $anonfun$save$9(BoxesRunTime.unboxToDouble(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         DenseMatrix topicsDenseMatrix = topicsMatrix.asBreeze().toDenseMatrix$mcD$sp(.MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
         IndexedSeq topics = scala.package..MODULE$.Range().apply(0, k).map((topicInd) -> $anonfun$save$10(topicsDenseMatrix, BoxesRunTime.unboxToInt(topicInd)));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.LocalLDAModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(topics, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).repartition(1).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public LocalLDAModel load(final SparkContext sc, final String path, final Vector docConcentration, final double topicConcentration, final double gammaShape) {
         String dataPath = Loader$.MODULE$.dataPath(path);
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataFrame = spark.read().parquet(dataPath);
         Loader$ var10000 = Loader$.MODULE$;
         StructType var10001 = dataFrame.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.LocalLDAModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator1$2() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
         Row[] topics = (Row[])dataFrame.collect();
         int vocabSize = ((Vector)topics[0].getAs(0)).size();
         int k = topics.length;
         DenseMatrix brzTopics = breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(vocabSize, k, .MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])topics), (x0$1) -> {
            if (x0$1 != null) {
               Some var4 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var4.isEmpty() && var4.get() != null && ((SeqOps)var4.get()).lengthCompare(2) == 0) {
                  Object vec = ((SeqOps)var4.get()).apply(0);
                  Object ind = ((SeqOps)var4.get()).apply(1);
                  if (vec instanceof Vector) {
                     Vector var7 = (Vector)vec;
                     if (ind instanceof Integer) {
                        int var8 = BoxesRunTime.unboxToInt(ind);
                        return (DenseVector)((NumericOps)brzTopics.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(var8), breeze.linalg.operators.HasOps..MODULE$.canSliceCol())).$colon$eq(var7.asBreeze(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_V_Double_OpSet());
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         });
         Matrix topicsMat = Matrices$.MODULE$.fromBreeze(brzTopics);
         return new LocalLDAModel(topicsMat, docConcentration, topicConcentration, gammaShape);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$5(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$7(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$8(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$9(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final LocalLDAModel$SaveLoadV1_0$Data $anonfun$save$10(final DenseMatrix topicsDenseMatrix$1, final int topicInd) {
         return new LocalLDAModel$SaveLoadV1_0$Data(Vectors$.MODULE$.dense(((DenseVector)topicsDenseMatrix$1.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(topicInd), breeze.linalg.operators.HasOps..MODULE$.canSliceCol())).toArray$mcD$sp(.MODULE$.Double())), topicInd);
      }

      public SaveLoadV1_0$() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
