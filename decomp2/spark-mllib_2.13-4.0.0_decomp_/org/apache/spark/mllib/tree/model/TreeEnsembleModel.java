package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.EnsembleCombiningStrategy$;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.Enumeration;
import scala.Function1;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple1;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\rMe!\u0002-Z!m+\u0007\u0002C=\u0001\u0005\u000b\u0007I\u0011\u0003>\t\u0013\u0005\u0005\u0002A!A!\u0002\u0013Y\bBCA\u0012\u0001\t\u0015\r\u0011\"\u0005\u0002&!Q\u0011Q\u0007\u0001\u0003\u0002\u0003\u0006I!a\n\t\u0015\u0005]\u0002A!b\u0001\n#\tI\u0004\u0003\u0006\u0002D\u0001\u0011\t\u0011)A\u0005\u0003wA!\"!\u0012\u0001\u0005\u000b\u0007I\u0011CA$\u0011)\t9\u0006\u0001B\u0001B\u0003%\u0011\u0011\n\u0005\b\u00033\u0002A\u0011AA.\u0011%\t9\u0007\u0001b\u0001\n\u0013\tI\u0007\u0003\u0005\u0002l\u0001\u0001\u000b\u0011BA\u001f\u0011\u001d\ti\u0007\u0001C\u0005\u0003_Bq!!!\u0001\t\u0013\t\u0019\tC\u0004\u0002\b\u0002!\t!!#\t\u000f\u0005\u001d\u0005\u0001\"\u0001\u0002\u000e\"9\u0011q\u0011\u0001\u0005\u0002\u0005}\u0005bBAa\u0001\u0011\u0005\u00131\u0019\u0005\b\u0003+\u0004A\u0011AAl\u0011\u001d\tI\u000e\u0001C\u0001\u00037Dq!a9\u0001\t\u0003\tYn\u0002\u0005\u0002pfC\taWAy\r\u001dA\u0016\f#\u0001\\\u0003gDq!!\u0017\u0017\t\u0003\u0011YaB\u0004\u0003\u000eYA\tAa\u0004\u0007\u000f\tMa\u0003#\u0001\u0003\u0016!9\u0011\u0011L\r\u0005\u0002\t]\u0001b\u0002B\r3\u0011\u0005\u0011q\u001b\u0004\u0007\u00057I\u0002I!\b\t\u0013ed\"Q3A\u0005\u0002\u0005]\u0007BCA\u00119\tE\t\u0015!\u0003\u0002F\"Q!Q\u0005\u000f\u0003\u0016\u0004%\t!a6\t\u0015\t\u001dBD!E!\u0002\u0013\t)\r\u0003\u0006\u0002Fq\u0011)\u001a!C\u0001\u0003/D!\"a\u0016\u001d\u0005#\u0005\u000b\u0011BAc\u0011)\t9\u0004\bBK\u0002\u0013\u0005\u0011\u0011\b\u0005\u000b\u0003\u0007b\"\u0011#Q\u0001\n\u0005m\u0002bBA-9\u0011\u0005!\u0011\u0006\u0005\n\u0005oa\u0012\u0011!C\u0001\u0005sA\u0011Ba\u0011\u001d#\u0003%\tA!\u0012\t\u0013\tmC$%A\u0005\u0002\t\u0015\u0003\"\u0003B/9E\u0005I\u0011\u0001B#\u0011%\u0011y\u0006HI\u0001\n\u0003\u0011\t\u0007C\u0005\u0003fq\t\t\u0011\"\u0011\u0003h!I!Q\u000e\u000f\u0002\u0002\u0013\u0005\u00111\u001c\u0005\n\u0005_b\u0012\u0011!C\u0001\u0005cB\u0011B! \u001d\u0003\u0003%\tEa \t\u0013\t5E$!A\u0005\u0002\t=\u0005\"\u0003BM9\u0005\u0005I\u0011\tBN\u0011%\u0011y\nHA\u0001\n\u0003\u0012\t\u000bC\u0005\u0002Br\t\t\u0011\"\u0011\u0003$\"I!Q\u0015\u000f\u0002\u0002\u0013\u0005#qU\u0004\n\u0005WK\u0012\u0011!E\u0001\u0005[3\u0011Ba\u0007\u001a\u0003\u0003E\tAa,\t\u000f\u0005eS\u0007\"\u0001\u0003>\"I\u0011\u0011Y\u001b\u0002\u0002\u0013\u0015#1\u0015\u0005\n\u0005\u007f+\u0014\u0011!CA\u0005\u0003D\u0011Ba36\u0003\u0003%\tI!4\t\u0013\t}W'!A\u0005\n\t\u0005hA\u0002Bu3\u0001\u0013Y\u000f\u0003\u0006\u0003nn\u0012)\u001a!C\u0001\u00037D!Ba<<\u0005#\u0005\u000b\u0011BAo\u0011)\u0011\tp\u000fBK\u0002\u0013\u0005!1\u001f\u0005\u000b\u0007\u001bY$\u0011#Q\u0001\n\tU\bbBA-w\u0011\u00051q\u0002\u0005\n\u0005oY\u0014\u0011!C\u0001\u0007/A\u0011Ba\u0011<#\u0003%\ta!\b\t\u0013\tm3(%A\u0005\u0002\r\u0005\u0002\"\u0003B3w\u0005\u0005I\u0011\tB4\u0011%\u0011igOA\u0001\n\u0003\tY\u000eC\u0005\u0003pm\n\t\u0011\"\u0001\u0004&!I!QP\u001e\u0002\u0002\u0013\u0005#q\u0010\u0005\n\u0005\u001b[\u0014\u0011!C\u0001\u0007SA\u0011B!'<\u0003\u0003%\te!\f\t\u0013\t}5(!A\u0005B\t\u0005\u0006\"CAaw\u0005\u0005I\u0011\tBR\u0011%\u0011)kOA\u0001\n\u0003\u001a\tdB\u0005\u00046e\t\t\u0011#\u0001\u00048\u0019I!\u0011^\r\u0002\u0002#\u00051\u0011\b\u0005\b\u00033rE\u0011AB!\u0011%\t\tMTA\u0001\n\u000b\u0012\u0019\u000bC\u0005\u0003@:\u000b\t\u0011\"!\u0004D!I!1\u001a(\u0002\u0002\u0013\u00055\u0011\n\u0005\n\u0005?t\u0015\u0011!C\u0005\u0005CDqa!\u0016\u001a\t\u0003\u00199\u0006C\u0004\u0004ve!\taa\u001e\t\u000f\r%\u0015\u0004\"\u0001\u0004\f\"I!q\u001c\f\u0002\u0002\u0013%!\u0011\u001d\u0002\u0012)J,W-\u00128tK6\u0014G.Z'pI\u0016d'B\u0001.\\\u0003\u0015iw\u000eZ3m\u0015\taV,\u0001\u0003ue\u0016,'B\u00010`\u0003\u0015iG\u000e\\5c\u0015\t\u0001\u0017-A\u0003ta\u0006\u00148N\u0003\u0002cG\u00061\u0011\r]1dQ\u0016T\u0011\u0001Z\u0001\u0004_J<7c\u0001\u0001gYB\u0011qM[\u0007\u0002Q*\t\u0011.A\u0003tG\u0006d\u0017-\u0003\u0002lQ\n1\u0011I\\=SK\u001a\u0004\"!\u001c<\u000f\u00059$hBA8t\u001b\u0005\u0001(BA9s\u0003\u0019a$o\\8u}\r\u0001\u0011\"A5\n\u0005UD\u0017a\u00029bG.\fw-Z\u0005\u0003ob\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!!\u001e5\u0002\t\u0005dwm\\\u000b\u0002wB\u0019A0a\u0007\u000f\u0007u\f)BD\u0002\u007f\u0003#q1a`A\b\u001d\u0011\t\t!!\u0004\u000f\t\u0005\r\u00111\u0002\b\u0005\u0003\u000b\tIAD\u0002p\u0003\u000fI\u0011\u0001Z\u0005\u0003E\u000eL!\u0001Y1\n\u0005y{\u0016B\u0001/^\u0013\r\t\u0019bW\u0001\u000eG>tg-[4ve\u0006$\u0018n\u001c8\n\t\u0005]\u0011\u0011D\u0001\u0005\u00032<wNC\u0002\u0002\u0014mKA!!\b\u0002 \t!\u0011\t\\4p\u0015\u0011\t9\"!\u0007\u0002\u000b\u0005dwm\u001c\u0011\u0002\u000bQ\u0014X-Z:\u0016\u0005\u0005\u001d\u0002#B4\u0002*\u00055\u0012bAA\u0016Q\n)\u0011I\u001d:bsB!\u0011qFA\u0019\u001b\u0005I\u0016bAA\u001a3\n\tB)Z2jg&|g\u000e\u0016:fK6{G-\u001a7\u0002\rQ\u0014X-Z:!\u0003-!(/Z3XK&<\u0007\u000e^:\u0016\u0005\u0005m\u0002#B4\u0002*\u0005u\u0002cA4\u0002@%\u0019\u0011\u0011\t5\u0003\r\u0011{WO\u00197f\u00031!(/Z3XK&<\u0007\u000e^:!\u0003E\u0019w.\u001c2j]&twm\u0015;sCR,w-_\u000b\u0003\u0003\u0013\u0002B!a\u0013\u0002R9\u0019Q0!\u0014\n\t\u0005=\u0013\u0011D\u0001\u001a\u000b:\u001cX-\u001c2mK\u000e{WNY5oS:<7\u000b\u001e:bi\u0016<\u00170\u0003\u0003\u0002T\u0005U#!G#og\u0016l'\r\\3D_6\u0014\u0017N\\5oON#(/\u0019;fOfTA!a\u0014\u0002\u001a\u0005\u00112m\\7cS:LgnZ*ue\u0006$XmZ=!\u0003\u0019a\u0014N\\5u}QQ\u0011QLA0\u0003C\n\u0019'!\u001a\u0011\u0007\u0005=\u0002\u0001C\u0003z\u0013\u0001\u00071\u0010C\u0004\u0002$%\u0001\r!a\n\t\u000f\u0005]\u0012\u00021\u0001\u0002<!9\u0011QI\u0005A\u0002\u0005%\u0013AC:v[^+\u0017n\u001a5ugV\u0011\u0011QH\u0001\fgVlw+Z5hQR\u001c\b%\u0001\tqe\u0016$\u0017n\u0019;CsN+X.\\5oOR!\u0011QHA9\u0011\u001d\t\u0019\b\u0004a\u0001\u0003k\n\u0001BZ3biV\u0014Xm\u001d\t\u0005\u0003o\ni(\u0004\u0002\u0002z)\u0019\u00111P/\u0002\r1Lg.\u00197h\u0013\u0011\ty(!\u001f\u0003\rY+7\r^8s\u0003=\u0001(/\u001a3jGR\u0014\u0015PV8uS:<G\u0003BA\u001f\u0003\u000bCq!a\u001d\u000e\u0001\u0004\t)(A\u0004qe\u0016$\u0017n\u0019;\u0015\t\u0005u\u00121\u0012\u0005\b\u0003gr\u0001\u0019AA;)\u0011\ty)a'\u0011\r\u0005E\u0015qSA\u001f\u001b\t\t\u0019JC\u0002\u0002\u0016~\u000b1A\u001d3e\u0013\u0011\tI*a%\u0003\u0007I#E\tC\u0004\u0002t=\u0001\r!!(\u0011\r\u0005E\u0015qSA;)\u0011\t\t+!0\u0011\r\u0005\r\u0016QVAY\u001b\t\t)K\u0003\u0003\u0002(\u0006%\u0016\u0001\u00026bm\u0006T1!a+`\u0003\r\t\u0007/[\u0005\u0005\u0003_\u000b)KA\u0004KCZ\f'\u000b\u0012#\u0011\t\u0005M\u00161X\u0007\u0003\u0003kSA!a.\u0002:\u0006!A.\u00198h\u0015\t\t9+\u0003\u0003\u0002B\u0005U\u0006bBA:!\u0001\u0007\u0011q\u0018\t\u0007\u0003G\u000bi+!\u001e\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!2\u0011\t\u0005\u001d\u0017q\u001a\b\u0005\u0003\u0013\fY\r\u0005\u0002pQ&\u0019\u0011Q\u001a5\u0002\rA\u0013X\rZ3g\u0013\u0011\t\t.a5\u0003\rM#(/\u001b8h\u0015\r\ti\r[\u0001\u000ei>$UMY;h'R\u0014\u0018N\\4\u0016\u0005\u0005\u0015\u0017\u0001\u00038v[R\u0013X-Z:\u0016\u0005\u0005u\u0007cA4\u0002`&\u0019\u0011\u0011\u001d5\u0003\u0007%sG/A\u0007u_R\fGNT;n\u001d>$Wm]\u0015\u0006\u0001\u0005\u001d\u00181^\u0005\u0004\u0003SL&!G$sC\u0012LWM\u001c;C_>\u001cH/\u001a3Ue\u0016,7/T8eK2L1!!<Z\u0005E\u0011\u0016M\u001c3p[\u001a{'/Z:u\u001b>$W\r\\\u0001\u0012)J,W-\u00128tK6\u0014G.Z'pI\u0016d\u0007cAA\u0018-M1aCZA{\u0005\u0003\u0001B!a>\u0002~6\u0011\u0011\u0011 \u0006\u0004\u0003w|\u0016\u0001C5oi\u0016\u0014h.\u00197\n\t\u0005}\u0018\u0011 \u0002\b\u0019><w-\u001b8h!\u0011\u0011\u0019A!\u0003\u000e\u0005\t\u0015!\u0002\u0002B\u0004\u0003s\u000b!![8\n\u0007]\u0014)\u0001\u0006\u0002\u0002r\u0006a1+\u0019<f\u0019>\fGMV\u0019`aA\u0019!\u0011C\r\u000e\u0003Y\u0011AbU1wK2{\u0017\r\u001a,2?B\u001a\"!\u00074\u0015\u0005\t=\u0011!\u0005;iSN4uN]7biZ+'o]5p]\nAQ*\u001a;bI\u0006$\u0018mE\u0003\u001dM\n}A\u000eE\u0002h\u0005CI1Aa\ti\u0005\u001d\u0001&o\u001c3vGR\f\u0001\u0002\u001e:fK\u0006cwm\\\u0001\niJ,W-\u00117h_\u0002\"\"Ba\u000b\u00030\tE\"1\u0007B\u001b!\r\u0011i\u0003H\u0007\u00023!1\u00110\na\u0001\u0003\u000bDqA!\n&\u0001\u0004\t)\rC\u0004\u0002F\u0015\u0002\r!!2\t\u000f\u0005]R\u00051\u0001\u0002<\u0005!1m\u001c9z))\u0011YCa\u000f\u0003>\t}\"\u0011\t\u0005\ts\u001a\u0002\n\u00111\u0001\u0002F\"I!Q\u0005\u0014\u0011\u0002\u0003\u0007\u0011Q\u0019\u0005\n\u0003\u000b2\u0003\u0013!a\u0001\u0003\u000bD\u0011\"a\u000e'!\u0003\u0005\r!a\u000f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!q\t\u0016\u0005\u0003\u000b\u0014Ie\u000b\u0002\u0003LA!!Q\nB,\u001b\t\u0011yE\u0003\u0003\u0003R\tM\u0013!C;oG\",7m[3e\u0015\r\u0011)\u0006[\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B-\u0005\u001f\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\t\r$\u0006BA\u001e\u0005\u0013\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001B5!\u0011\t\u0019La\u001b\n\t\u0005E\u0017QW\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\u0011\u0019H!\u001f\u0011\u0007\u001d\u0014)(C\u0002\u0003x!\u00141!\u00118z\u0011%\u0011Y(LA\u0001\u0002\u0004\ti.A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005\u0003\u0003bAa!\u0003\n\nMTB\u0001BC\u0015\r\u00119\t[\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002BF\u0005\u000b\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!!\u0011\u0013BL!\r9'1S\u0005\u0004\u0005+C'a\u0002\"p_2,\u0017M\u001c\u0005\n\u0005wz\u0013\u0011!a\u0001\u0005g\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!\u0011\u000eBO\u0011%\u0011Y\bMA\u0001\u0002\u0004\ti.\u0001\u0005iCND7i\u001c3f)\t\ti\u000e\u0006\u0002\u0003j\u00051Q-];bYN$BA!%\u0003*\"I!1P\u001a\u0002\u0002\u0003\u0007!1O\u0001\t\u001b\u0016$\u0018\rZ1uCB\u0019!QF\u001b\u0014\u000bU\u0012\tL!\u0001\u0011\u001d\tM&\u0011XAc\u0003\u000b\f)-a\u000f\u0003,5\u0011!Q\u0017\u0006\u0004\u0005oC\u0017a\u0002:v]RLW.Z\u0005\u0005\u0005w\u0013)LA\tBEN$(/Y2u\rVt7\r^5p]R\"\"A!,\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0015\t-\"1\u0019Bc\u0005\u000f\u0014I\r\u0003\u0004zq\u0001\u0007\u0011Q\u0019\u0005\b\u0005KA\u0004\u0019AAc\u0011\u001d\t)\u0005\u000fa\u0001\u0003\u000bDq!a\u000e9\u0001\u0004\tY$A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t='1\u001c\t\u0006O\nE'Q[\u0005\u0004\u0005'D'AB(qi&|g\u000eE\u0006h\u0005/\f)-!2\u0002F\u0006m\u0012b\u0001BmQ\n1A+\u001e9mKRB\u0011B!8:\u0003\u0003\u0005\rAa\u000b\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003dB!\u00111\u0017Bs\u0013\u0011\u00119/!.\u0003\r=\u0013'.Z2u\u0005A)en]3nE2,gj\u001c3f\t\u0006$\u0018mE\u0003<M\n}A.\u0001\u0004ue\u0016,\u0017\nZ\u0001\biJ,W-\u00133!\u0003\u0011qw\u000eZ3\u0016\u0005\tU\b\u0003\u0002B|\u0007\u000fqAA!?\u0004\u00049!!1 B\u0000\u001d\rq(Q`\u0005\u00035nK1a!\u0001Z\u0003E!UmY5tS>tGK]3f\u001b>$W\r\\\u0005\u0005\u0005\u001b\u0019)AC\u0002\u0004\u0002eKAa!\u0003\u0004\f\tAaj\u001c3f\t\u0006$\u0018M\u0003\u0003\u0003\u000e\r\u0015\u0011!\u00028pI\u0016\u0004CCBB\t\u0007'\u0019)\u0002E\u0002\u0003.mBqA!<A\u0001\u0004\ti\u000eC\u0004\u0003r\u0002\u0003\rA!>\u0015\r\rE1\u0011DB\u000e\u0011%\u0011i/\u0011I\u0001\u0002\u0004\ti\u000eC\u0005\u0003r\u0006\u0003\n\u00111\u0001\u0003vV\u00111q\u0004\u0016\u0005\u0003;\u0014I%\u0006\u0002\u0004$)\"!Q\u001fB%)\u0011\u0011\u0019ha\n\t\u0013\tmd)!AA\u0002\u0005uG\u0003\u0002BI\u0007WA\u0011Ba\u001fI\u0003\u0003\u0005\rAa\u001d\u0015\t\t%4q\u0006\u0005\n\u0005wJ\u0015\u0011!a\u0001\u0003;$BA!%\u00044!I!1\u0010'\u0002\u0002\u0003\u0007!1O\u0001\u0011\u000b:\u001cX-\u001c2mK:{G-\u001a#bi\u0006\u00042A!\fO'\u0015q51\bB\u0001!)\u0011\u0019l!\u0010\u0002^\nU8\u0011C\u0005\u0005\u0007\u007f\u0011)LA\tBEN$(/Y2u\rVt7\r^5p]J\"\"aa\u000e\u0015\r\rE1QIB$\u0011\u001d\u0011i/\u0015a\u0001\u0003;DqA!=R\u0001\u0004\u0011)\u0010\u0006\u0003\u0004L\rM\u0003#B4\u0003R\u000e5\u0003cB4\u0004P\u0005u'Q_\u0005\u0004\u0007#B'A\u0002+va2,'\u0007C\u0005\u0003^J\u000b\t\u00111\u0001\u0004\u0012\u0005!1/\u0019<f))\u0019Ifa\u0018\u0004l\r=4\u0011\u000f\t\u0004O\u000em\u0013bAB/Q\n!QK\\5u\u0011\u001d\u0019\t\u0007\u0016a\u0001\u0007G\n!a]2\u0011\t\r\u00154qM\u0007\u0002?&\u00191\u0011N0\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000f\r5D\u000b1\u0001\u0002F\u0006!\u0001/\u0019;i\u0011\u0019QF\u000b1\u0001\u0002^!911\u000f+A\u0002\u0005\u0015\u0017!C2mCN\u001ch*Y7f\u00031\u0011X-\u00193NKR\fG-\u0019;b)\u0011\u0011Yc!\u001f\t\u000f\rmT\u000b1\u0001\u0004~\u0005AQ.\u001a;bI\u0006$\u0018\r\u0005\u0003\u0004\u0000\r\u0015UBABA\u0015\r\u0019\u0019iY\u0001\u0007UN|g\u000eN:\n\t\r\u001d5\u0011\u0011\u0002\u0007\u0015Z\u000bG.^3\u0002\u00131|\u0017\r\u001a+sK\u0016\u001cH\u0003CA\u0014\u0007\u001b\u001byi!%\t\u000f\r\u0005d\u000b1\u0001\u0004d!91Q\u000e,A\u0002\u0005\u0015\u0007b\u0002B\u0013-\u0002\u0007\u0011Q\u0019"
)
public class TreeEnsembleModel implements Serializable {
   private final Enumeration.Value algo;
   private final DecisionTreeModel[] trees;
   private final double[] treeWeights;
   private final Enumeration.Value combiningStrategy;
   private final double sumWeights;

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return TreeEnsembleModel$.MODULE$.LogStringContext(sc);
   }

   public Enumeration.Value algo() {
      return this.algo;
   }

   public DecisionTreeModel[] trees() {
      return this.trees;
   }

   public double[] treeWeights() {
      return this.treeWeights;
   }

   public Enumeration.Value combiningStrategy() {
      return this.combiningStrategy;
   }

   private double sumWeights() {
      return this.sumWeights;
   }

   private double predictBySumming(final Vector features) {
      double[] treePredictions = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.trees()), (x$9) -> BoxesRunTime.boxToDouble($anonfun$predictBySumming$1(features, x$9)), scala.reflect.ClassTag..MODULE$.Double());
      return org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(this.numTrees(), treePredictions, 1, this.treeWeights(), 1);
   }

   private double predictByVoting(final Vector features) {
      Map votes = (Map)scala.collection.mutable.Map..MODULE$.empty();
      .MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(this.trees())).zip(.MODULE$.iterator$extension(scala.Predef..MODULE$.doubleArrayOps(this.treeWeights()))).foreach((x0$1) -> {
         $anonfun$predictByVoting$1(features, votes, x0$1);
         return BoxedUnit.UNIT;
      });
      return (double)((Tuple2)votes.maxBy((x$10) -> BoxesRunTime.boxToDouble($anonfun$predictByVoting$3(x$10)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$))._1$mcI$sp();
   }

   public double predict(final Vector features) {
      Tuple2 var4 = new Tuple2(this.algo(), this.combiningStrategy());
      if (var4 != null) {
         label119: {
            Enumeration.Value var5 = (Enumeration.Value)var4._1();
            Enumeration.Value var6 = (Enumeration.Value)var4._2();
            Enumeration.Value var10000 = Algo$.MODULE$.Regression();
            if (var10000 == null) {
               if (var5 != null) {
                  break label119;
               }
            } else if (!var10000.equals(var5)) {
               break label119;
            }

            var10000 = EnsembleCombiningStrategy$.MODULE$.Sum();
            if (var10000 == null) {
               if (var6 == null) {
                  return this.predictBySumming(features);
               }
            } else if (var10000.equals(var6)) {
               return this.predictBySumming(features);
            }
         }
      }

      if (var4 != null) {
         label120: {
            Enumeration.Value var9 = (Enumeration.Value)var4._1();
            Enumeration.Value var10 = (Enumeration.Value)var4._2();
            Enumeration.Value var24 = Algo$.MODULE$.Regression();
            if (var24 == null) {
               if (var9 != null) {
                  break label120;
               }
            } else if (!var24.equals(var9)) {
               break label120;
            }

            var24 = EnsembleCombiningStrategy$.MODULE$.Average();
            if (var24 == null) {
               if (var10 == null) {
                  return this.predictBySumming(features) / this.sumWeights();
               }
            } else if (var24.equals(var10)) {
               return this.predictBySumming(features) / this.sumWeights();
            }
         }
      }

      label139: {
         if (var4 != null) {
            label121: {
               Enumeration.Value var13 = (Enumeration.Value)var4._1();
               Enumeration.Value var14 = (Enumeration.Value)var4._2();
               Enumeration.Value var26 = Algo$.MODULE$.Classification();
               if (var26 == null) {
                  if (var13 != null) {
                     break label121;
                  }
               } else if (!var26.equals(var13)) {
                  break label121;
               }

               var26 = EnsembleCombiningStrategy$.MODULE$.Sum();
               if (var26 == null) {
                  if (var14 == null) {
                     break label139;
                  }
               } else if (var26.equals(var14)) {
                  break label139;
               }
            }
         }

         if (var4 != null) {
            label122: {
               Enumeration.Value var19 = (Enumeration.Value)var4._1();
               Enumeration.Value var20 = (Enumeration.Value)var4._2();
               Enumeration.Value var28 = Algo$.MODULE$.Classification();
               if (var28 == null) {
                  if (var19 != null) {
                     break label122;
                  }
               } else if (!var28.equals(var19)) {
                  break label122;
               }

               var28 = EnsembleCombiningStrategy$.MODULE$.Vote();
               if (var28 == null) {
                  if (var20 == null) {
                     return this.predictByVoting(features);
                  }
               } else if (var28.equals(var20)) {
                  return this.predictByVoting(features);
               }
            }
         }

         Enumeration.Value var10002 = this.algo();
         throw new IllegalArgumentException("TreeEnsembleModel given unsupported (algo, combiningStrategy) combination: (" + var10002 + ", " + this.combiningStrategy() + ").");
      }

      double prediction = this.predictBySumming(features);
      if (prediction > (double)0.0F) {
         return (double)1.0F;
      } else {
         return (double)0.0F;
      }
   }

   public RDD predict(final RDD features) {
      return features.map((x) -> BoxesRunTime.boxToDouble($anonfun$predict$1(this, x)), scala.reflect.ClassTag..MODULE$.Double());
   }

   public JavaRDD predict(final JavaRDD features) {
      return this.predict(features.rdd()).toJavaRDD();
   }

   public String toString() {
      Enumeration.Value var2 = this.algo();
      Enumeration.Value var10000 = Algo$.MODULE$.Classification();
      if (var10000 == null) {
         if (var2 == null) {
            return "TreeEnsembleModel classifier with " + this.numTrees() + " trees\n";
         }
      } else if (var10000.equals(var2)) {
         return "TreeEnsembleModel classifier with " + this.numTrees() + " trees\n";
      }

      var10000 = Algo$.MODULE$.Regression();
      if (var10000 == null) {
         if (var2 == null) {
            return "TreeEnsembleModel regressor with " + this.numTrees() + " trees\n";
         }
      } else if (var10000.equals(var2)) {
         return "TreeEnsembleModel regressor with " + this.numTrees() + " trees\n";
      }

      throw new IllegalArgumentException("TreeEnsembleModel given unknown algo parameter: " + this.algo() + ".");
   }

   public String toDebugString() {
      String header = this.toString() + "\n";
      return header + .MODULE$.fold$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(this.trees()))), (x0$1) -> {
         if (x0$1 != null) {
            DecisionTreeModel tree = (DecisionTreeModel)x0$1._1();
            int treeIndex = x0$1._2$mcI$sp();
            return "  Tree " + treeIndex + ":\n" + tree.topNode().subtreeToString(4);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))), "", (x$11, x$12) -> x$11 + x$12);
   }

   public int numTrees() {
      return this.trees().length;
   }

   public int totalNumNodes() {
      return BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.trees()), (x$13) -> BoxesRunTime.boxToInteger($anonfun$totalNumNodes$1(x$13)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final double $anonfun$predictBySumming$1(final Vector features$1, final DecisionTreeModel x$9) {
      return x$9.predict(features$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$predictByVoting$1(final Vector features$2, final Map votes$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         DecisionTreeModel tree = (DecisionTreeModel)x0$1._1();
         double weight = x0$1._2$mcD$sp();
         int prediction = (int)tree.predict(features$2);
         votes$1.update(BoxesRunTime.boxToInteger(prediction), BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(votes$1.getOrElse(BoxesRunTime.boxToInteger(prediction), (JFunction0.mcD.sp)() -> (double)0.0F)) + weight));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$predictByVoting$3(final Tuple2 x$10) {
      return x$10._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$predict$1(final TreeEnsembleModel $this, final Vector x) {
      return $this.predict(x);
   }

   // $FF: synthetic method
   public static final int $anonfun$totalNumNodes$1(final DecisionTreeModel x$13) {
      return x$13.numNodes();
   }

   public TreeEnsembleModel(final Enumeration.Value algo, final DecisionTreeModel[] trees, final double[] treeWeights, final Enumeration.Value combiningStrategy) {
      this.algo = algo;
      this.trees = trees;
      this.treeWeights = treeWeights;
      this.combiningStrategy = combiningStrategy;
      scala.Predef..MODULE$.require(this.numTrees() > 0, () -> "TreeEnsembleModel cannot be created without trees.");
      this.sumWeights = scala.math.package..MODULE$.max(BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(treeWeights).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)), 1.0E-15);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();

      public String thisFormatVersion() {
         return "1.0";
      }

      public void save(final SparkContext sc, final String path, final TreeEnsembleModel model, final String className) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         int memThreshold = 768;
         if (sc.isLocal()) {
            int driverMemory = BoxesRunTime.unboxToInt(sc.getReadOnlyConf().getOption("spark.driver.memory").orElse(() -> scala.Option..MODULE$.apply(System.getenv("SPARK_DRIVER_MEMORY"))).map((str) -> BoxesRunTime.boxToInteger($anonfun$save$2(str))).getOrElse((JFunction0.mcI.sp)() -> org.apache.spark.util.Utils..MODULE$.DEFAULT_DRIVER_MEM_MB()));
            if (driverMemory <= memThreshold) {
               TreeEnsembleModel$.MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ".save() was called, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, className)}))).$plus(TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"but it may fail because of too little driver memory "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "m). If failure occurs, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_MEMORY_SIZE..MODULE$, BoxesRunTime.boxToInteger(driverMemory))})))).$plus(TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"try setting driver-memory ", "m "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_THRESHOLD_SIZE..MODULE$, BoxesRunTime.boxToInteger(memThreshold))})))).$plus(TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(or larger)."})))).log(scala.collection.immutable.Nil..MODULE$))));
            }
         } else if (sc.executorMemory() <= memThreshold) {
            TreeEnsembleModel$.MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ".save() was called, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, className)}))).$plus(TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"but it may fail because of too little executor memory "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "m). If failure occurs, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_MEMORY_SIZE..MODULE$, BoxesRunTime.boxToInteger(sc.executorMemory()))})))).$plus(TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"try setting executor-memory ", "m "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_THRESHOLD_SIZE..MODULE$, BoxesRunTime.boxToInteger(memThreshold))})))).$plus(TreeEnsembleModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(or larger)."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         TreeEnsembleModel$SaveLoadV1_0$Metadata ensembleMetadata = new TreeEnsembleModel$SaveLoadV1_0$Metadata(model.algo().toString(), model.trees()[0].algo().toString(), model.combiningStrategy().toString(), model.treeWeights());
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), className), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("metadata"), org.json4s.Extraction..MODULE$.decompose(ensembleMetadata, format))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
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
         RDD dataRDD = sc.parallelize(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(model.trees()))).toImmutableArraySeq(), sc.parallelize$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).flatMap((x0$1) -> {
            if (x0$1 != null) {
               DecisionTreeModel tree = (DecisionTreeModel)x0$1._1();
               int treeId = x0$1._2$mcI$sp();
               return (Seq)tree.topNode().subtreeIterator().toSeq().map((node) -> DecisionTreeModel$SaveLoadV1_0$NodeData$.MODULE$.apply(treeId, node));
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(DecisionTreeModel$SaveLoadV1_0$NodeData.class));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("org")), $m$untyped.staticPackage("org.apache")), $m$untyped.staticPackage("org.apache.spark")), $m$untyped.staticPackage("org.apache.spark.mllib")), $m$untyped.staticPackage("org.apache.spark.mllib.tree")), $m$untyped.staticPackage("org.apache.spark.mllib.tree.model")), $m$untyped.staticModule("org.apache.spark.mllib.tree.model.DecisionTreeModel")), $m$untyped.staticModule("org.apache.spark.mllib.tree.model.DecisionTreeModel.SaveLoadV1_0")), $m$untyped.staticClass("org.apache.spark.mllib.tree.model.DecisionTreeModel.SaveLoadV1_0.NodeData"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(dataRDD, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public TreeEnsembleModel$SaveLoadV1_0$Metadata readMetadata(final JValue metadata) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         return (TreeEnsembleModel$SaveLoadV1_0$Metadata)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "metadata")), formats, scala.reflect.ManifestFactory..MODULE$.classType(TreeEnsembleModel$SaveLoadV1_0$Metadata.class));
      }

      public DecisionTreeModel[] loadTrees(final SparkContext sc, final String path, final String treeAlgo) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset var10000 = spark.read().parquet(Loader$.MODULE$.dataPath(path));
         Function1 var10001 = (r) -> DecisionTreeModel$SaveLoadV1_0$NodeData$.MODULE$.apply(r);
         SQLImplicits var10002 = spark.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator5$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("org")), $m$untyped.staticPackage("org.apache")), $m$untyped.staticPackage("org.apache.spark")), $m$untyped.staticPackage("org.apache.spark.mllib")), $m$untyped.staticPackage("org.apache.spark.mllib.tree")), $m$untyped.staticPackage("org.apache.spark.mllib.tree.model")), $m$untyped.staticModule("org.apache.spark.mllib.tree.model.DecisionTreeModel")), $m$untyped.staticModule("org.apache.spark.mllib.tree.model.DecisionTreeModel.SaveLoadV1_0")), $m$untyped.staticClass("org.apache.spark.mllib.tree.model.DecisionTreeModel.SaveLoadV1_0.NodeData"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator5$1() {
            }
         }

         Dataset nodes = var10000.map(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1())));
         Node[] trees = DecisionTreeModel.SaveLoadV1_0$.MODULE$.constructTrees(nodes.rdd());
         return (DecisionTreeModel[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(trees), (x$14) -> new DecisionTreeModel(x$14, Algo$.MODULE$.fromString(treeAlgo)), scala.reflect.ClassTag..MODULE$.apply(DecisionTreeModel.class));
      }

      // $FF: synthetic method
      public static final int $anonfun$save$2(final String str) {
         return org.apache.spark.util.Utils..MODULE$.memoryStringToMb(str);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
