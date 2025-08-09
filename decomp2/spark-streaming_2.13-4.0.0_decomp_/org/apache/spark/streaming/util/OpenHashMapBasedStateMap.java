package org.apache.spark.streaming.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.serializer.KryoInputObjectInputBridge;
import org.apache.spark.serializer.KryoOutputObjectOutputBridge;
import org.apache.spark.util.collection.OpenHashMap;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.StringOps;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011=b!B/_\u0001\u0001D\u0007BCA\u000b\u0001\t\u0005\r\u0011\"\u0001\u0002\u0018!Q\u0011\u0011\u0004\u0001\u0003\u0002\u0004%\t!a\u0007\t\u0013\u0005\u001d\u0002A!A!B\u0013Q\u0007BCA\u001d\u0001\t\u0005\r\u0011\"\u0003\u0002<!Q\u00111\t\u0001\u0003\u0002\u0004%I!!\u0012\t\u0015\u0005%\u0003A!A!B\u0013\ti\u0004\u0003\u0006\u0002L\u0001\u0011\t\u0019!C\u0005\u0003wA!\"!\u0014\u0001\u0005\u0003\u0007I\u0011BA(\u0011)\t\u0019\u0006\u0001B\u0001B\u0003&\u0011Q\b\u0005\u000b\u0003+\u0002!\u00111A\u0005\f\u0005]\u0003BCA3\u0001\t\u0005\r\u0011\"\u0003\u0002h!Q\u00111\u000e\u0001\u0003\u0002\u0003\u0006K!!\u0017\t\u0015\u00055\u0004A!a\u0001\n\u0017\ty\u0007\u0003\u0006\u0002t\u0001\u0011\t\u0019!C\u0005\u0003kB!\"!\u001f\u0001\u0005\u0003\u0005\u000b\u0015BA9\u0011\u001d\tY\b\u0001C\u0001\u0003{Bq!a\u001f\u0001\t\u0003\ti\tC\u0004\u0002|\u0001!\t!!'\t\u000f\u0005m\u0004\u0001\"\u0001\u0002$\"I\u00111\u0016\u0001A\u0002\u0013%\u0011Q\u0016\u0005\n\u0007{\u0002\u0001\u0019!C\u0005\u0007\u007fB\u0001ba!\u0001A\u0003&\u0011q\u0016\u0005\b\u0007\u0013\u0003A\u0011IBF\u0011\u001d\u0019\u0019\n\u0001C!\u0007+Cqa!)\u0001\t\u0003\u001a\u0019\u000bC\u0004\u0004&\u0002!\tea*\t\u000f\rE\u0006\u0001\"\u0011\u00044\"9!Q\r\u0001\u0005B\r]\u0006bBB]\u0001\u0011\u0005!\u0011\b\u0005\b\u0007w\u0003A\u0011AA\u001e\u0011\u001d\u0019i\f\u0001C\u0001\u0003wAqaa0\u0001\t\u0003\u001a\t\rC\u0004\u0003^\u0002!\te!1\t\u000f\rE\u0007\u0001\"\u0003\u0004T\"91q\u001c\u0001\u0005\n\r\u0005\bbBBw\u0001\u0011%1q\u001e\u0005\b\u0007s\u0004A\u0011BB~\u0011\u001d!)\u0001\u0001C!\t\u000fAq\u0001b\b\u0001\t\u0003\"\tc\u0002\u0005\u0002\\zC\t\u0001YAo\r\u001dif\f#\u0001a\u0003?Dq!a\u001f*\t\u0003\t9P\u0002\u0004\u0002z&\u0002\u00151 \u0005\u000b\u0005+Y#\u00113A\u0005\u0002\t]\u0001B\u0003B\u000fW\t\u0005\r\u0011\"\u0001\u0003 !Q!1E\u0016\u0003\u0012\u0003\u0006KA!\u0007\t\u0015\t\u00152F!e\u0001\n\u0003\u00119\u0003\u0003\u0006\u00030-\u0012\t\u0019!C\u0001\u0005cA!B!\u000e,\u0005#\u0005\u000b\u0015\u0002B\u0015\u0011)\u00119d\u000bBI\u0002\u0013\u0005!\u0011\b\u0005\u000b\u0005\u0003Z#\u00111A\u0005\u0002\t\r\u0003B\u0003B$W\tE\t\u0015)\u0003\u0003<!9\u00111P\u0016\u0005\u0002\t%\u0003b\u0002B+W\u0011\u0005!q\u000b\u0005\b\u00053ZC\u0011\u0001B.\u0011%\u0011)gKA\u0001\n\u0003\u00119\u0007C\u0005\u0003x-\n\n\u0011\"\u0001\u0003z!I!1S\u0016\u0012\u0002\u0013\u0005!Q\u0013\u0005\n\u0005;[\u0013\u0013!C\u0001\u0005?C\u0011Ba*,\u0003\u0003%\tE!+\t\u0013\t]6&!A\u0005\u0002\u0005m\u0002\"\u0003B]W\u0005\u0005I\u0011\u0001B^\u0011%\u0011ylKA\u0001\n\u0003\u0012\t\rC\u0005\u0003N.\n\t\u0011\"\u0001\u0003P\"I!1[\u0016\u0002\u0002\u0013\u0005#Q\u001b\u0005\n\u00053\\\u0013\u0011!C!\u00057D\u0011B!8,\u0003\u0003%\tEa8\t\u0013\t\u00058&!A\u0005B\t\rx!\u0003BtS\u0005\u0005\t\u0012\u0001Bu\r%\tI0KA\u0001\u0012\u0003\u0011Y\u000fC\u0004\u0002|\u0019#\tA!<\t\u0013\tug)!A\u0005F\t}\u0007\"\u0003Bx\r\u0006\u0005I\u0011\u0011By\u0011%\u0019\tARI\u0001\n\u0003\u0019\u0019\u0001C\u0005\u0004\u000e\u0019\u000b\n\u0011\"\u0001\u0004\u0010!I11\u0003$\u0012\u0002\u0013\u00051Q\u0003\u0005\n\u000731\u0015\u0011!CA\u00077A\u0011b!\u000eG#\u0003%\taa\u000e\t\u0013\r\u0005c)%A\u0005\u0002\r\r\u0003\"CB$\rF\u0005I\u0011AB%\u0011%\u0019iERA\u0001\n\u0013\u0019yE\u0002\u0004\u0004X%\u00021\u0011\f\u0005\u000b\u00077\u0012&Q1A\u0005\u0002\u0005m\u0002BCB/%\n\u0005\t\u0015!\u0003\u0002>!9\u00111\u0010*\u0005\u0002\r}\u0003\"CB3S\t\u0007I\u0011AA\u001e\u0011!\u00199'\u000bQ\u0001\n\u0005u\u0002\"CB5S\t\u0007I\u0011AA\u001e\u0011!\u0019Y'\u000bQ\u0001\n\u0005u\u0002\"CB!SE\u0005I\u0011AB7\u0011%\u00199%KI\u0001\n\u0003\u00199\bC\u0005\u0004N%\n\t\u0011\"\u0003\u0004P\tAr\n]3o\u0011\u0006\u001c\b.T1q\u0005\u0006\u001cX\rZ*uCR,W*\u00199\u000b\u0005}\u0003\u0017\u0001B;uS2T!!\u00192\u0002\u0013M$(/Z1nS:<'BA2e\u0003\u0015\u0019\b/\u0019:l\u0015\t)g-\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002O\u0006\u0019qN]4\u0016\u0007%\u0004hp\u0005\u0003\u0001U\u0006\u0005\u0001\u0003B6m]vl\u0011AX\u0005\u0003[z\u0013\u0001b\u0015;bi\u0016l\u0015\r\u001d\t\u0003_Bd\u0001\u0001B\u0003r\u0001\t\u00071OA\u0001L\u0007\u0001\t\"\u0001\u001e>\u0011\u0005UDX\"\u0001<\u000b\u0003]\fQa]2bY\u0006L!!\u001f<\u0003\u000f9{G\u000f[5oOB\u0011Qo_\u0005\u0003yZ\u00141!\u00118z!\tyg\u0010B\u0003\u0000\u0001\t\u00071OA\u0001T!\u0011\t\u0019!!\u0005\u000e\u0005\u0005\u0015!\u0002BA\u0004\u0003\u0013\tAa\u001b:z_*!\u00111BA\u0007\u0003A)7o\u001c;fe&\u001c7o\u001c4uo\u0006\u0014XM\u0003\u0002\u0002\u0010\u0005\u00191m\\7\n\t\u0005M\u0011Q\u0001\u0002\u0011\u0017JLxnU3sS\u0006d\u0017N_1cY\u0016\fa\u0002]1sK:$8\u000b^1uK6\u000b\u0007/F\u0001k\u0003I\u0001\u0018M]3oiN#\u0018\r^3NCB|F%Z9\u0015\t\u0005u\u00111\u0005\t\u0004k\u0006}\u0011bAA\u0011m\n!QK\\5u\u0011!\t)CAA\u0001\u0002\u0004Q\u0017a\u0001=%c\u0005y\u0001/\u0019:f]R\u001cF/\u0019;f\u001b\u0006\u0004\b\u0005K\u0002\u0004\u0003W\u00012!^A\u0017\u0013\r\tyC\u001e\u0002\niJ\fgn]5f]RD3aAA\u001a!\r)\u0018QG\u0005\u0004\u0003o1(\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u001f%t\u0017\u000e^5bY\u000e\u000b\u0007/Y2jif,\"!!\u0010\u0011\u0007U\fy$C\u0002\u0002BY\u00141!\u00138u\u0003MIg.\u001b;jC2\u001c\u0015\r]1dSRLx\fJ3r)\u0011\ti\"a\u0012\t\u0013\u0005\u0015R!!AA\u0002\u0005u\u0012\u0001E5oSRL\u0017\r\\\"ba\u0006\u001c\u0017\u000e^=!\u0003M!W\r\u001c;b\u0007\"\f\u0017N\u001c+ie\u0016\u001c\bn\u001c7e\u0003]!W\r\u001c;b\u0007\"\f\u0017N\u001c+ie\u0016\u001c\bn\u001c7e?\u0012*\u0017\u000f\u0006\u0003\u0002\u001e\u0005E\u0003\"CA\u0013\u0011\u0005\u0005\t\u0019AA\u001f\u0003Q!W\r\u001c;b\u0007\"\f\u0017N\u001c+ie\u0016\u001c\bn\u001c7eA\u0005Y1.Z=DY\u0006\u001c8\u000fV1h+\t\tI\u0006E\u0003\u0002\\\u0005\u0005d.\u0004\u0002\u0002^)\u0019\u0011q\f<\u0002\u000fI,g\r\\3di&!\u00111MA/\u0005!\u0019E.Y:t)\u0006<\u0017aD6fs\u000ec\u0017m]:UC\u001e|F%Z9\u0015\t\u0005u\u0011\u0011\u000e\u0005\n\u0003KY\u0011\u0011!a\u0001\u00033\nAb[3z\u00072\f7o\u001d+bO\u0002\nQb\u001d;bi\u0016\u001cE.Y:t)\u0006<WCAA9!\u0015\tY&!\u0019~\u0003E\u0019H/\u0019;f\u00072\f7o\u001d+bO~#S-\u001d\u000b\u0005\u0003;\t9\bC\u0005\u0002&9\t\t\u00111\u0001\u0002r\u0005q1\u000f^1uK\u000ec\u0017m]:UC\u001e\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0005\u0002\u0000\u0005\u001d\u0015\u0011RAF)\u0019\t\t)a!\u0002\u0006B!1\u000e\u00018~\u0011\u001d\t)\u0006\u0005a\u0002\u00033Bq!!\u001c\u0011\u0001\b\t\t\b\u0003\u0004\u0002\u0016A\u0001\rA\u001b\u0005\n\u0003s\u0001\u0002\u0013!a\u0001\u0003{A\u0011\"a\u0013\u0011!\u0003\u0005\r!!\u0010\u0015\r\u0005=\u0015QSAL)\u0019\t\t)!%\u0002\u0014\"9\u0011QK\tA\u0004\u0005e\u0003bBA7#\u0001\u000f\u0011\u0011\u000f\u0005\b\u0003s\t\u0002\u0019AA\u001f\u0011\u001d\tY%\u0005a\u0001\u0003{!B!a'\u0002\"R1\u0011\u0011QAO\u0003?Cq!!\u0016\u0013\u0001\b\tI\u0006C\u0004\u0002nI\u0001\u001d!!\u001d\t\u000f\u0005-#\u00031\u0001\u0002>Q\u0011\u0011Q\u0015\u000b\u0007\u0003\u0003\u000b9+!+\t\u000f\u0005U3\u0003q\u0001\u0002Z!9\u0011QN\nA\u0004\u0005E\u0014\u0001\u00033fYR\fW*\u00199\u0016\u0005\u0005=\u0006cBAY\u0003ss\u0017QX\u0007\u0003\u0003gSA!!.\u00028\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0005}\u0013\u0017\u0002BA^\u0003g\u00131b\u00149f]\"\u000b7\u000f['baB!\u0011qX\u0016~\u001d\r\t\t\r\u000b\b\u0005\u0003\u0007\fIN\u0004\u0003\u0002F\u0006]g\u0002BAd\u0003+tA!!3\u0002T:!\u00111ZAi\u001b\t\tiMC\u0002\u0002PJ\fa\u0001\u0010:p_Rt\u0014\"A4\n\u0005\u00154\u0017BA2e\u0013\t\t'-\u0003\u0002`A\u0006Ar\n]3o\u0011\u0006\u001c\b.T1q\u0005\u0006\u001cX\rZ*uCR,W*\u00199\u0011\u0005-L3#B\u0015\u0002b\u0006\u001d\bcA;\u0002d&\u0019\u0011Q\u001d<\u0003\r\u0005s\u0017PU3g!\u0011\tI/a=\u000e\u0005\u0005-(\u0002BAw\u0003_\f!![8\u000b\u0005\u0005E\u0018\u0001\u00026bm\u0006LA!!>\u0002l\na1+\u001a:jC2L'0\u00192mKR\u0011\u0011Q\u001c\u0002\n'R\fG/Z%oM>,B!!@\u0003\u001cM91&!9\u0002\u0000\n\u0015\u0001cA;\u0003\u0002%\u0019!1\u0001<\u0003\u000fA\u0013x\u000eZ;diB!!q\u0001B\t\u001d\u0011\u0011IA!\u0004\u000f\t\u0005-'1B\u0005\u0002o&\u0019!q\u0002<\u0002\u000fA\f7m[1hK&!\u0011Q\u001fB\n\u0015\r\u0011yA^\u0001\u0005I\u0006$\u0018-\u0006\u0002\u0003\u001aA\u0019qNa\u0007\u0005\u000b}\\#\u0019A:\u0002\u0011\u0011\fG/Y0%KF$B!!\b\u0003\"!I\u0011QE\u0017\u0002\u0002\u0003\u0007!\u0011D\u0001\u0006I\u0006$\u0018\rI\u0001\u000bkB$\u0017\r^3US6,WC\u0001B\u0015!\r)(1F\u0005\u0004\u0005[1(\u0001\u0002'p]\u001e\fa\"\u001e9eCR,G+[7f?\u0012*\u0017\u000f\u0006\u0003\u0002\u001e\tM\u0002\"CA\u0013a\u0005\u0005\t\u0019\u0001B\u0015\u0003-)\b\u000fZ1uKRKW.\u001a\u0011\u0002\u000f\u0011,G.\u001a;fIV\u0011!1\b\t\u0004k\nu\u0012b\u0001B m\n9!i\\8mK\u0006t\u0017a\u00033fY\u0016$X\rZ0%KF$B!!\b\u0003F!I\u0011QE\u001a\u0002\u0002\u0003\u0007!1H\u0001\tI\u0016dW\r^3eAQA!1\nB(\u0005#\u0012\u0019\u0006E\u0003\u0003N-\u0012I\"D\u0001*\u0011%\u0011)\"\u000eI\u0001\u0002\u0004\u0011I\u0002C\u0005\u0003&U\u0002\n\u00111\u0001\u0003*!I!qG\u001b\u0011\u0002\u0003\u0007!1H\u0001\f[\u0006\u00148\u000eR3mKR,G\r\u0006\u0002\u0002\u001e\u00051Q\u000f\u001d3bi\u0016$b!!\b\u0003^\t\u0005\u0004b\u0002B0o\u0001\u0007!\u0011D\u0001\b]\u0016<H)\u0019;b\u0011\u001d\u0011\u0019g\u000ea\u0001\u0005S\tQB\\3x+B$\u0017\r^3US6,\u0017\u0001B2paf,BA!\u001b\u0003pQA!1\u000eB9\u0005g\u0012)\bE\u0003\u0003N-\u0012i\u0007E\u0002p\u0005_\"Qa \u001dC\u0002MD\u0011B!\u00069!\u0003\u0005\rA!\u001c\t\u0013\t\u0015\u0002\b%AA\u0002\t%\u0002\"\u0003B\u001cqA\u0005\t\u0019\u0001B\u001e\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*BAa\u001f\u0003\u0012V\u0011!Q\u0010\u0016\u0005\u00053\u0011yh\u000b\u0002\u0003\u0002B!!1\u0011BG\u001b\t\u0011)I\u0003\u0003\u0003\b\n%\u0015!C;oG\",7m[3e\u0015\r\u0011YI^\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002BH\u0005\u000b\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015y\u0018H1\u0001t\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*BAa&\u0003\u001cV\u0011!\u0011\u0014\u0016\u0005\u0005S\u0011y\bB\u0003\u0000u\t\u00071/\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\t\t\u0005&QU\u000b\u0003\u0005GSCAa\u000f\u0003\u0000\u0011)qp\u000fb\u0001g\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"Aa+\u0011\t\t5&1W\u0007\u0003\u0005_SAA!-\u0002p\u0006!A.\u00198h\u0013\u0011\u0011)La,\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$2A\u001fB_\u0011%\t)CPA\u0001\u0002\u0004\ti$A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011\u0019\rE\u0003\u0003F\n%'0\u0004\u0002\u0003H*\u0019\u0011Q\u0017<\n\t\t-'q\u0019\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0003<\tE\u0007\u0002CA\u0013\u0001\u0006\u0005\t\u0019\u0001>\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005W\u00139\u000eC\u0005\u0002&\u0005\u000b\t\u00111\u0001\u0002>\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002>\u0005AAo\\*ue&tw\r\u0006\u0002\u0003,\u00061Q-];bYN$BAa\u000f\u0003f\"A\u0011Q\u0005#\u0002\u0002\u0003\u0007!0A\u0005Ti\u0006$X-\u00138g_B\u0019!Q\n$\u0014\u000b\u0019\u000b\t/a:\u0015\u0005\t%\u0018!B1qa2LX\u0003\u0002Bz\u0005s$\u0002B!>\u0003|\nu(q \t\u0006\u0005\u001bZ#q\u001f\t\u0004_\neH!B@J\u0005\u0004\u0019\b\"\u0003B\u000b\u0013B\u0005\t\u0019\u0001B|\u0011%\u0011)#\u0013I\u0001\u0002\u0004\u0011I\u0003C\u0005\u00038%\u0003\n\u00111\u0001\u0003<\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0004\u0006\r-QCAB\u0004U\u0011\u0019IAa \u0011\u0007=\u001cY\u0001B\u0003\u0000\u0015\n\u00071/A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\u00119j!\u0005\u0005\u000b}\\%\u0019A:\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIM*BA!)\u0004\u0018\u0011)q\u0010\u0014b\u0001g\u00069QO\\1qa2LX\u0003BB\u000f\u0007[!Baa\b\u00040A)Qo!\t\u0004&%\u001911\u0005<\u0003\r=\u0003H/[8o!%)8qEB\u0016\u0005S\u0011Y$C\u0002\u0004*Y\u0014a\u0001V;qY\u0016\u001c\u0004cA8\u0004.\u0011)q0\u0014b\u0001g\"I1\u0011G'\u0002\u0002\u0003\u000711G\u0001\u0004q\u0012\u0002\u0004#\u0002B'W\r-\u0012a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0004:\r}RCAB\u001eU\u0011\u0019iDa \u0011\u0007=\u001cy\u0004B\u0003\u0000\u001d\n\u00071/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0005\u0005/\u001b)\u0005B\u0003\u0000\u001f\n\u00071/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0005\u0005C\u001bY\u0005B\u0003\u0000!\n\u00071/\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004RA!!QVB*\u0013\u0011\u0019)Fa,\u0003\r=\u0013'.Z2u\u0005-a\u0015.\\5u\u001b\u0006\u00148.\u001a:\u0014\u000bI\u000b\t/a:\u0002\u00079,X.\u0001\u0003ok6\u0004C\u0003BB1\u0007G\u00022A!\u0014S\u0011\u001d\u0019Y&\u0016a\u0001\u0003{\tA\u0004R#M)\u0006{6\tS!J\u001d~cUIT$U\u0011~#\u0006JU#T\u0011>cE)A\u000fE\u000b2#\u0016iX\"I\u0003&su\fT#O\u000fRCu\f\u0016%S\u000bNCu\n\u0014#!\u0003a!UIR!V\u0019R{\u0016JT%U\u0013\u0006culQ!Q\u0003\u000eKE+W\u0001\u001a\t\u00163\u0015)\u0016'U?&s\u0015\nV%B\u0019~\u001b\u0015\tU!D\u0013RK\u0006%\u0006\u0004\u0004p\rM4QO\u000b\u0003\u0007cRC!!\u0010\u0003\u0000\u0011)\u0011O\u0017b\u0001g\u0012)qP\u0017b\u0001gV11qNB=\u0007w\"Q!].C\u0002M$Qa`.C\u0002M\fA\u0002Z3mi\u0006l\u0015\r]0%KF$B!!\b\u0004\u0002\"I\u0011QE\u000b\u0002\u0002\u0003\u0007\u0011qV\u0001\nI\u0016dG/Y'ba\u0002B3AFA\u0016Q\r1\u00121G\u0001\u0004O\u0016$H\u0003BBG\u0007\u001f\u0003B!^B\u0011{\"11\u0011S\fA\u00029\f1a[3z\u0003%9W\r\u001e\"z)&lW\r\u0006\u0003\u0004\u0018\u000eu\u0005C\u0002B\u0004\u00073\u001bY*\u0003\u0003\u0003L\nM\u0001cB;\u0004(9l(\u0011\u0006\u0005\b\u0007?C\u0002\u0019\u0001B\u0015\u0003E!\bN]3tQV\u0003H-\u0019;fIRKW.Z\u0001\u0007O\u0016$\u0018\t\u001c7\u0015\u0005\r]\u0015a\u00019viRA\u0011QDBU\u0007W\u001by\u000b\u0003\u0004\u0004\u0012j\u0001\rA\u001c\u0005\u0007\u0007[S\u0002\u0019A?\u0002\u000bM$\u0018\r^3\t\u000f\t\u0015\"\u00041\u0001\u0003*\u00051!/Z7pm\u0016$B!!\b\u00046\"11\u0011S\u000eA\u00029$\u0012A[\u0001\u000eg\"|W\u000f\u001c3D_6\u0004\u0018m\u0019;\u0002!\u0011,G\u000e^1DQ\u0006Lg\u000eT3oORD\u0017AC1qaJ|\u0007pU5{K\u0006iAo\u001c#fEV<7\u000b\u001e:j]\u001e$\"aa1\u0011\t\r\u00157Q\u001a\b\u0005\u0007\u000f\u001cI\rE\u0002\u0002LZL1aa3w\u0003\u0019\u0001&/\u001a3fM&!!QWBh\u0015\r\u0019YM^\u0001\u0014oJLG/Z(cU\u0016\u001cG/\u00138uKJt\u0017\r\u001c\u000b\u0005\u0003;\u0019)\u000eC\u0004\u0004X\n\u0002\ra!7\u0002\u0019=,H\u000f];u'R\u0014X-Y7\u0011\t\u0005%81\\\u0005\u0005\u0007;\fYO\u0001\u0007PE*,7\r^(viB,H/\u0001\nsK\u0006$wJ\u00196fGRLe\u000e^3s]\u0006dG\u0003BA\u000f\u0007GDqa!:$\u0001\u0004\u00199/A\u0006j]B,Ho\u0015;sK\u0006l\u0007\u0003BAu\u0007SLAaa;\u0002l\nYqJ\u00196fGRLe\u000e];u\u0003-9(/\u001b;f\u001f\nTWm\u0019;\u0015\t\u0005u1\u0011\u001f\u0005\b\u0007/$\u0003\u0019ABz!\u0011\tIo!>\n\t\r]\u00181\u001e\u0002\u0013\u001f\nTWm\u0019;PkR\u0004X\u000f^*ue\u0016\fW.\u0001\u0006sK\u0006$wJ\u00196fGR$B!!\b\u0004~\"91Q]\u0013A\u0002\r}\b\u0003BAu\t\u0003IA\u0001b\u0001\u0002l\n\trJ\u00196fGRLe\u000e];u'R\u0014X-Y7\u0002\u000b]\u0014\u0018\u000e^3\u0015\r\u0005uA\u0011\u0002C\t\u0011\u001d\t9A\na\u0001\t\u0017\u0001B!a\u0001\u0005\u000e%!AqBA\u0003\u0005\u0011Y%/_8\t\u000f\u0011Ma\u00051\u0001\u0005\u0016\u00051q.\u001e;qkR\u0004B\u0001b\u0006\u0005\u001c5\u0011A\u0011\u0004\u0006\u0005\u0003[\f)!\u0003\u0003\u0005\u001e\u0011e!AB(viB,H/\u0001\u0003sK\u0006$GCBA\u000f\tG!)\u0003C\u0004\u0002\b\u001d\u0002\r\u0001b\u0003\t\u000f\u0011\u001dr\u00051\u0001\u0005*\u0005)\u0011N\u001c9viB!Aq\u0003C\u0016\u0013\u0011!i\u0003\"\u0007\u0003\u000b%s\u0007/\u001e;"
)
public class OpenHashMapBasedStateMap extends StateMap implements KryoSerializable {
   private transient volatile StateMap parentStateMap;
   private int initialCapacity;
   private int deltaChainThreshold;
   private ClassTag keyClassTag;
   private ClassTag stateClassTag;
   private transient volatile OpenHashMap deltaMap;

   public static int $lessinit$greater$default$3() {
      return OpenHashMapBasedStateMap$.MODULE$.$lessinit$greater$default$3();
   }

   public static int $lessinit$greater$default$2() {
      return OpenHashMapBasedStateMap$.MODULE$.$lessinit$greater$default$2();
   }

   public static int DEFAULT_INITIAL_CAPACITY() {
      return OpenHashMapBasedStateMap$.MODULE$.DEFAULT_INITIAL_CAPACITY();
   }

   public static int DELTA_CHAIN_LENGTH_THRESHOLD() {
      return OpenHashMapBasedStateMap$.MODULE$.DELTA_CHAIN_LENGTH_THRESHOLD();
   }

   public StateMap parentStateMap() {
      return this.parentStateMap;
   }

   public void parentStateMap_$eq(final StateMap x$1) {
      this.parentStateMap = x$1;
   }

   private int initialCapacity() {
      return this.initialCapacity;
   }

   private void initialCapacity_$eq(final int x$1) {
      this.initialCapacity = x$1;
   }

   private int deltaChainThreshold() {
      return this.deltaChainThreshold;
   }

   private void deltaChainThreshold_$eq(final int x$1) {
      this.deltaChainThreshold = x$1;
   }

   private ClassTag keyClassTag() {
      return this.keyClassTag;
   }

   private void keyClassTag_$eq(final ClassTag x$1) {
      this.keyClassTag = x$1;
   }

   private ClassTag stateClassTag() {
      return this.stateClassTag;
   }

   private void stateClassTag_$eq(final ClassTag x$1) {
      this.stateClassTag = x$1;
   }

   private OpenHashMap deltaMap() {
      return this.deltaMap;
   }

   private void deltaMap_$eq(final OpenHashMap x$1) {
      this.deltaMap = x$1;
   }

   public Option get(final Object key) {
      StateInfo stateInfo = (StateInfo)this.deltaMap().apply(key);
      if (stateInfo != null) {
         return (Option)(!stateInfo.deleted() ? new Some(stateInfo.data()) : .MODULE$);
      } else {
         return this.parentStateMap().get(key);
      }
   }

   public Iterator getByTime(final long threshUpdatedTime) {
      Iterator oldStates = this.parentStateMap().getByTime(threshUpdatedTime).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getByTime$1(this, x0$1)));
      Iterator updatedStates = this.deltaMap().iterator().filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$getByTime$2(threshUpdatedTime, x0$2))).map((x0$3) -> {
         if (x0$3 != null) {
            Object key = x0$3._1();
            StateInfo stateInfo = (StateInfo)x0$3._2();
            return new Tuple3(key, stateInfo.data(), BoxesRunTime.boxToLong(stateInfo.updateTime()));
         } else {
            throw new MatchError(x0$3);
         }
      });
      return oldStates.$plus$plus(() -> updatedStates);
   }

   public Iterator getAll() {
      Iterator oldStates = this.parentStateMap().getAll().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getAll$1(this, x0$1)));
      Iterator updatedStates = this.deltaMap().iterator().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getAll$2(x$1))).map((x0$2) -> {
         if (x0$2 != null) {
            Object key = x0$2._1();
            StateInfo stateInfo = (StateInfo)x0$2._2();
            return new Tuple3(key, stateInfo.data(), BoxesRunTime.boxToLong(stateInfo.updateTime()));
         } else {
            throw new MatchError(x0$2);
         }
      });
      return oldStates.$plus$plus(() -> updatedStates);
   }

   public void put(final Object key, final Object state, final long updateTime) {
      StateInfo stateInfo = (StateInfo)this.deltaMap().apply(key);
      if (stateInfo != null) {
         stateInfo.update(state, updateTime);
      } else {
         this.deltaMap().update(key, new StateInfo(state, updateTime, OpenHashMapBasedStateMap.StateInfo$.MODULE$.$lessinit$greater$default$3()));
      }
   }

   public void remove(final Object key) {
      StateInfo stateInfo = (StateInfo)this.deltaMap().apply(key);
      if (stateInfo != null) {
         stateInfo.markDeleted();
      } else {
         boolean x$1 = true;
         Object x$2 = OpenHashMapBasedStateMap.StateInfo$.MODULE$.$lessinit$greater$default$1();
         long x$3 = OpenHashMapBasedStateMap.StateInfo$.MODULE$.$lessinit$greater$default$2();
         StateInfo newInfo = new StateInfo(x$2, x$3, true);
         this.deltaMap().update(key, newInfo);
      }
   }

   public StateMap copy() {
      int x$2 = this.deltaChainThreshold();
      int x$3 = OpenHashMapBasedStateMap$.MODULE$.$lessinit$greater$default$2();
      return new OpenHashMapBasedStateMap(this, x$3, x$2, this.keyClassTag(), this.stateClassTag());
   }

   public boolean shouldCompact() {
      return this.deltaChainLength() >= this.deltaChainThreshold();
   }

   public int deltaChainLength() {
      StateMap var2 = this.parentStateMap();
      if (var2 instanceof OpenHashMapBasedStateMap var3) {
         return var3.deltaChainLength() + 1;
      } else {
         return 0;
      }
   }

   public int approxSize() {
      int var10000 = this.deltaMap().size();
      StateMap var2 = this.parentStateMap();
      int var10001;
      if (var2 instanceof OpenHashMapBasedStateMap var3) {
         var10001 = var3.approxSize();
      } else {
         var10001 = 0;
      }

      return var10000 + var10001;
   }

   public String toDebugString() {
      String var2;
      if (this.deltaChainLength() > 0) {
         StringOps var10000 = scala.collection.StringOps..MODULE$;
         var2 = var10000.$times$extension(scala.Predef..MODULE$.augmentString("    "), this.deltaChainLength() - 1) + "+--- ";
      } else {
         var2 = "";
      }

      String tabs = var2;
      var2 = this.parentStateMap().toDebugString();
      return var2 + "\n" + this.deltaMap().iterator().mkString(tabs, "\n" + tabs, "");
   }

   public String toString() {
      int var10000 = System.identityHashCode(this);
      return "[" + var10000 + ", " + System.identityHashCode(this.parentStateMap()) + "]";
   }

   private void writeObjectInternal(final ObjectOutput outputStream) {
      outputStream.writeInt(this.deltaMap().size());
      Iterator deltaMapIterator = this.deltaMap().iterator();
      int deltaMapCount = 0;

      while(deltaMapIterator.hasNext()) {
         ++deltaMapCount;
         Tuple2 var7 = (Tuple2)deltaMapIterator.next();
         if (var7 == null) {
            throw new MatchError(var7);
         }

         Object key = var7._1();
         StateInfo stateInfo = (StateInfo)var7._2();
         Tuple2 var6 = new Tuple2(key, stateInfo);
         Object key = var6._1();
         StateInfo stateInfo = (StateInfo)var6._2();
         outputStream.writeObject(key);
         outputStream.writeObject(stateInfo);
      }

      scala.Predef..MODULE$.assert(deltaMapCount == this.deltaMap().size());
      boolean doCompaction = this.shouldCompact();
      OpenHashMapBasedStateMap var10000;
      if (doCompaction) {
         int initCapacity = this.approxSize() > 0 ? this.approxSize() : 64;
         var10000 = new OpenHashMapBasedStateMap(initCapacity, this.deltaChainThreshold(), this.keyClassTag(), this.stateClassTag());
      } else {
         var10000 = null;
      }

      OpenHashMapBasedStateMap newParentSessionStore = var10000;
      Iterator iterOfActiveSessions = this.parentStateMap().getAll();
      int parentSessionCount = 0;
      outputStream.writeInt(this.approxSize());

      while(iterOfActiveSessions.hasNext()) {
         ++parentSessionCount;
         Tuple3 var18 = (Tuple3)iterOfActiveSessions.next();
         if (var18 == null) {
            throw new MatchError(var18);
         }

         Object key = var18._1();
         Object state = var18._2();
         long updateTime = BoxesRunTime.unboxToLong(var18._3());
         Tuple3 var17 = new Tuple3(key, state, BoxesRunTime.boxToLong(updateTime));
         Object key = var17._1();
         Object state = var17._2();
         long updateTime = BoxesRunTime.unboxToLong(var17._3());
         outputStream.writeObject(key);
         outputStream.writeObject(state);
         outputStream.writeLong(updateTime);
         if (doCompaction) {
            newParentSessionStore.deltaMap().update(key, new StateInfo(state, updateTime, false));
         }
      }

      LimitMarker limiterObj = new LimitMarker(parentSessionCount);
      outputStream.writeObject(limiterObj);
      if (doCompaction) {
         this.parentStateMap_$eq(newParentSessionStore);
      }
   }

   private void readObjectInternal(final ObjectInput inputStream) {
      int deltaMapSize = inputStream.readInt();
      this.deltaMap_$eq(deltaMapSize != 0 ? new OpenHashMap(deltaMapSize, this.keyClassTag(), scala.reflect.ClassTag..MODULE$.apply(StateInfo.class)) : new OpenHashMap(this.initialCapacity(), this.keyClassTag(), scala.reflect.ClassTag..MODULE$.apply(StateInfo.class)));

      for(int deltaMapCount = 0; deltaMapCount < deltaMapSize; ++deltaMapCount) {
         Object key = inputStream.readObject();
         StateInfo sessionInfo = (StateInfo)inputStream.readObject();
         this.deltaMap().update(key, sessionInfo);
      }

      int parentStateMapSizeHint = inputStream.readInt();
      int newStateMapInitialCapacity = scala.math.package..MODULE$.max(parentStateMapSizeHint, OpenHashMapBasedStateMap$.MODULE$.DEFAULT_INITIAL_CAPACITY());
      OpenHashMapBasedStateMap newParentSessionStore = new OpenHashMapBasedStateMap(newStateMapInitialCapacity, this.deltaChainThreshold(), this.keyClassTag(), this.stateClassTag());
      boolean parentSessionLoopDone = false;

      while(!parentSessionLoopDone) {
         Object obj = inputStream.readObject();
         if (obj instanceof LimitMarker var13) {
            parentSessionLoopDone = true;
            int expectedCount = var13.num();
            scala.Predef..MODULE$.assert(expectedCount == newParentSessionStore.deltaMap().size());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            Object state = inputStream.readObject();
            long updateTime = inputStream.readLong();
            newParentSessionStore.deltaMap().update(obj, new StateInfo(state, updateTime, false));
            BoxedUnit var19 = BoxedUnit.UNIT;
         }
      }

      this.parentStateMap_$eq(newParentSessionStore);
   }

   private void writeObject(final ObjectOutputStream outputStream) {
      outputStream.defaultWriteObject();
      this.writeObjectInternal(outputStream);
   }

   private void readObject(final ObjectInputStream inputStream) {
      inputStream.defaultReadObject();
      this.readObjectInternal(inputStream);
   }

   public void write(final Kryo kryo, final Output output) {
      output.writeInt(this.initialCapacity());
      output.writeInt(this.deltaChainThreshold());
      kryo.writeClassAndObject(output, this.keyClassTag());
      kryo.writeClassAndObject(output, this.stateClassTag());
      this.writeObjectInternal(new KryoOutputObjectOutputBridge(kryo, output));
   }

   public void read(final Kryo kryo, final Input input) {
      this.initialCapacity_$eq(input.readInt());
      this.deltaChainThreshold_$eq(input.readInt());
      this.keyClassTag_$eq((ClassTag)kryo.readClassAndObject(input));
      this.stateClassTag_$eq((ClassTag)kryo.readClassAndObject(input));
      this.readObjectInternal(new KryoInputObjectInputBridge(kryo, input));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getByTime$1(final OpenHashMapBasedStateMap $this, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Object key = x0$1._1();
         return !$this.deltaMap().contains(key);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getByTime$2(final long threshUpdatedTime$1, final Tuple2 x0$2) {
      if (x0$2 == null) {
         throw new MatchError(x0$2);
      } else {
         StateInfo stateInfo = (StateInfo)x0$2._2();
         return !stateInfo.deleted() && stateInfo.updateTime() < threshUpdatedTime$1;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAll$1(final OpenHashMapBasedStateMap $this, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Object key = x0$1._1();
         return !$this.deltaMap().contains(key);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAll$2(final Tuple2 x$1) {
      return !((StateInfo)x$1._2()).deleted();
   }

   public OpenHashMapBasedStateMap(final StateMap parentStateMap, final int initialCapacity, final int deltaChainThreshold, final ClassTag keyClassTag, final ClassTag stateClassTag) {
      this.parentStateMap = parentStateMap;
      this.initialCapacity = initialCapacity;
      this.deltaChainThreshold = deltaChainThreshold;
      this.keyClassTag = keyClassTag;
      this.stateClassTag = stateClassTag;
      super();
      scala.Predef..MODULE$.require(this.initialCapacity() >= 1, () -> "Invalid initial capacity");
      scala.Predef..MODULE$.require(this.deltaChainThreshold() >= 1, () -> "Invalid delta chain threshold");
      this.deltaMap = new OpenHashMap(this.initialCapacity(), this.keyClassTag(), scala.reflect.ClassTag..MODULE$.apply(StateInfo.class));
   }

   public OpenHashMapBasedStateMap(final int initialCapacity, final int deltaChainThreshold, final ClassTag keyClassTag, final ClassTag stateClassTag) {
      this(new EmptyStateMap(), initialCapacity, deltaChainThreshold, keyClassTag, stateClassTag);
   }

   public OpenHashMapBasedStateMap(final int deltaChainThreshold, final ClassTag keyClassTag, final ClassTag stateClassTag) {
      this(OpenHashMapBasedStateMap$.MODULE$.DEFAULT_INITIAL_CAPACITY(), deltaChainThreshold, keyClassTag, stateClassTag);
   }

   public OpenHashMapBasedStateMap(final ClassTag keyClassTag, final ClassTag stateClassTag) {
      this(OpenHashMapBasedStateMap$.MODULE$.DELTA_CHAIN_LENGTH_THRESHOLD(), keyClassTag, stateClassTag);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class StateInfo implements Product, Serializable {
      private Object data;
      private long updateTime;
      private boolean deleted;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object data() {
         return this.data;
      }

      public void data_$eq(final Object x$1) {
         this.data = x$1;
      }

      public long updateTime() {
         return this.updateTime;
      }

      public void updateTime_$eq(final long x$1) {
         this.updateTime = x$1;
      }

      public boolean deleted() {
         return this.deleted;
      }

      public void deleted_$eq(final boolean x$1) {
         this.deleted = x$1;
      }

      public void markDeleted() {
         this.deleted_$eq(true);
      }

      public void update(final Object newData, final long newUpdateTime) {
         this.data_$eq(newData);
         this.updateTime_$eq(newUpdateTime);
         this.deleted_$eq(false);
      }

      public StateInfo copy(final Object data, final long updateTime, final boolean deleted) {
         return new StateInfo(data, updateTime, deleted);
      }

      public Object copy$default$1() {
         return this.data();
      }

      public long copy$default$2() {
         return this.updateTime();
      }

      public boolean copy$default$3() {
         return this.deleted();
      }

      public String productPrefix() {
         return "StateInfo";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.data();
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.updateTime());
            }
            case 2 -> {
               return BoxesRunTime.boxToBoolean(this.deleted());
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
         return x$1 instanceof StateInfo;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "data";
            }
            case 1 -> {
               return "updateTime";
            }
            case 2 -> {
               return "deleted";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.data()));
         var1 = Statics.mix(var1, Statics.longHash(this.updateTime()));
         var1 = Statics.mix(var1, this.deleted() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label40: {
               if (x$1 instanceof StateInfo) {
                  StateInfo var4 = (StateInfo)x$1;
                  if (this.updateTime() == var4.updateTime() && this.deleted() == var4.deleted() && BoxesRunTime.equals(this.data(), var4.data()) && var4.canEqual(this)) {
                     break label40;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public StateInfo(final Object data, final long updateTime, final boolean deleted) {
         this.data = data;
         this.updateTime = updateTime;
         this.deleted = deleted;
         super();
         Product.$init$(this);
      }
   }

   public static class StateInfo$ implements Serializable {
      public static final StateInfo$ MODULE$ = new StateInfo$();

      public Object $lessinit$greater$default$1() {
         return null;
      }

      public long $lessinit$greater$default$2() {
         return -1L;
      }

      public boolean $lessinit$greater$default$3() {
         return false;
      }

      public final String toString() {
         return "StateInfo";
      }

      public StateInfo apply(final Object data, final long updateTime, final boolean deleted) {
         return new StateInfo(data, updateTime, deleted);
      }

      public Object apply$default$1() {
         return null;
      }

      public long apply$default$2() {
         return -1L;
      }

      public boolean apply$default$3() {
         return false;
      }

      public Option unapply(final StateInfo x$0) {
         return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.data(), BoxesRunTime.boxToLong(x$0.updateTime()), BoxesRunTime.boxToBoolean(x$0.deleted()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(StateInfo$.class);
      }
   }

   public static class LimitMarker implements Serializable {
      private final int num;

      public int num() {
         return this.num;
      }

      public LimitMarker(final int num) {
         this.num = num;
      }
   }
}
