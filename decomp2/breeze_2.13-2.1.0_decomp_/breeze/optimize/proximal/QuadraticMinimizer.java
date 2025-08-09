package breeze.optimize.proximal;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.max$;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.optimize.DiffFunction;
import breeze.optimize.StochasticDiffFunction;
import breeze.storage.Zero$;
import breeze.util.Isomorphism;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import dev.ludovic.netlib.lapack.LAPACK;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.netlib.util.intW;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple12;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction12;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011}haBA\u0015\u0003W\u0001\u0011\u0011\b\u0005\u000b\u0003'\u0002!\u0011!Q\u0001\n\u0005U\u0003BCA\u0017\u0001\t\u0005\t\u0015!\u0003\u0002\\!Q\u00111\r\u0001\u0003\u0002\u0003\u0006I!!\u001a\t\u0015\u0005]\u0004A!A!\u0002\u0013\tI\b\u0003\u0006\u0002\u0000\u0001\u0011\t\u0011)A\u0005\u0003+B!\"!!\u0001\u0005\u0003\u0005\u000b\u0011BA9\u0011)\t\u0019\t\u0001B\u0001B\u0003%\u0011\u0011\u000f\u0005\u000b\u0003\u000b\u0003!\u0011!Q\u0001\n\u0005E\u0004bBAD\u0001\u0011\u0005\u0011\u0011R\u0003\u0007\u0003;\u0003\u0001!!\u001a\u0006\r\u0005}\u0005\u0001AA=\r\u0019\t\t\u000b\u0001!\u0002$\"Q\u00111\u0019\u0007\u0003\u0016\u0004%\t!!2\t\u0015\u0005-GB!E!\u0002\u0013\t9\r\u0003\u0006\u0002N2\u0011)\u001a!C\u0001\u0003\u000bD!\"a4\r\u0005#\u0005\u000b\u0011BAd\u0011)\t\t\u000e\u0004BK\u0002\u0013\u0005\u0011Q\u0019\u0005\u000b\u0003'd!\u0011#Q\u0001\n\u0005\u001d\u0007BCAk\u0019\tU\r\u0011\"\u0001\u0002F\"Q\u0011q\u001b\u0007\u0003\u0012\u0003\u0006I!a2\t\u0015\u0005eGB!f\u0001\n\u0003\tY\u000e\u0003\u0006\u0002`2\u0011\t\u0012)A\u0005\u0003;D!\"!9\r\u0005+\u0007I\u0011AAr\u0011)\tY\u000f\u0004B\tB\u0003%\u0011Q\u001d\u0005\u000b\u0003[d!Q3A\u0005\u0002\u0005\u0015\u0007BCAx\u0019\tE\t\u0015!\u0003\u0002H\"Q\u0011\u0011\u001f\u0007\u0003\u0016\u0004%\t!!2\t\u0015\u0005MHB!E!\u0002\u0013\t9\r\u0003\u0006\u0002v2\u0011)\u001a!C\u0001\u0003\u000bD!\"a>\r\u0005#\u0005\u000b\u0011BAd\u0011)\tI\u0010\u0004BK\u0002\u0013\u0005\u0011Q\u0019\u0005\u000b\u0003wd!\u0011#Q\u0001\n\u0005\u001d\u0007BCA\u007f\u0019\tU\r\u0011\"\u0001\u0002\u0000\"Q!\u0011\u0001\u0007\u0003\u0012\u0003\u0006I!!\u0016\t\u0015\t\rAB!f\u0001\n\u0003\u0011)\u0001\u0003\u0006\u0003\u000e1\u0011\t\u0012)A\u0005\u0005\u000fA\u0001\"a\"\r\t\u0003\u0001!q\u0002\u0005\n\u0005Wa\u0011\u0011!C\u0001\u0005[A\u0011Ba\u0012\r#\u0003%\tA!\u0013\t\u0013\t}C\"%A\u0005\u0002\t%\u0003\"\u0003B1\u0019E\u0005I\u0011\u0001B%\u0011%\u0011\u0019\u0007DI\u0001\n\u0003\u0011I\u0005C\u0005\u0003f1\t\n\u0011\"\u0001\u0003h!I!1\u000e\u0007\u0012\u0002\u0013\u0005!Q\u000e\u0005\n\u0005cb\u0011\u0013!C\u0001\u0005\u0013B\u0011Ba\u001d\r#\u0003%\tA!\u0013\t\u0013\tUD\"%A\u0005\u0002\t%\u0003\"\u0003B<\u0019E\u0005I\u0011\u0001B%\u0011%\u0011I\bDI\u0001\n\u0003\u0011Y\bC\u0005\u0003\u00001\t\n\u0011\"\u0001\u0003\u0002\"I!Q\u0011\u0007\u0002\u0002\u0013\u0005#q\u0011\u0005\n\u00053c\u0011\u0011!C\u0001\u0003\u007fD\u0011Ba'\r\u0003\u0003%\tA!(\t\u0013\t%F\"!A\u0005B\t-\u0006\"\u0003B]\u0019\u0005\u0005I\u0011\u0001B^\u0011%\u0011y\fDA\u0001\n\u0003\u0012\t\rC\u0005\u0003F2\t\t\u0011\"\u0011\u0003H\"I!\u0011\u001a\u0007\u0002\u0002\u0013\u0005#1\u001a\u0005\n\u0005\u001bd\u0011\u0011!C!\u0005\u001f<\u0011Ba5\u0001\u0003\u0003E\tA!6\u0007\u0013\u0005\u0005\u0006!!A\t\u0002\t]\u0007bBAD{\u0011\u0005!q\u001e\u0005\n\u0005\u0013l\u0014\u0011!C#\u0005\u0017D\u0011B!=>\u0003\u0003%\tIa=\t\u0013\r5Q(!A\u0005\u0002\u000e=\u0001\"CB\u0011\u0001\t\u0007I\u0011AA\u0000\u0011!\u0019\u0019\u0003\u0001Q\u0001\n\u0005U\u0003\"CB\u0013\u0001\t\u0007I\u0011AA\u0000\u0011!\u00199\u0003\u0001Q\u0001\n\u0005U\u0003\"CB\u0015\u0001\t\u0007I\u0011AA\u0000\u0011!\u0019Y\u0003\u0001Q\u0001\n\u0005U\u0003\"CB\u0017\u0001\t\u0007I\u0011AA\u0000\u0011!\u0019y\u0003\u0001Q\u0001\n\u0005U\u0003\"CB\u0019\u0001\t\u0007I\u0011BB\u001a\u0011!\u0019)\u0004\u0001Q\u0001\n\u0005\u0015\u0004\"CB\u001c\u0001\t\u0007I\u0011AB\u001a\u0011!\u0019I\u0004\u0001Q\u0001\n\u0005\u0015\u0004\"CB\u001e\u0001\t\u0007I\u0011AA\u0000\u0011!\u0019i\u0004\u0001Q\u0001\n\u0005U\u0003bBB \u0001\u0011\u00051\u0011\t\u0005\b\u0007\u0007\u0002A\u0011BB#\u0011\u001d\u00199\u0005\u0001C\u0001\u0007\u0013Bqaa\u0012\u0001\t\u0003\u0019)\u0006C\u0004\u0004^\u0001!\taa\u0018\t\u000f\r\u0005\u0004\u0001\"\u0003\u0004d!91Q\u000e\u0001\u0005\n\r=\u0004bBBB\u0001\u0011\u00051Q\u0011\u0005\n\u0007'\u0003\u0011\u0013!C\u0001\u0005\u0003Cqa!&\u0001\t\u0013\u00199\nC\u0004\u0004\u001c\u0002!Ia!(\t\u000f\r\r\u0005\u0001\"\u0001\u0004\"\"911\u0011\u0001\u0005\u0002\r\u001d\u0006bBBB\u0001\u0011\u00051q\u0016\u0005\b\u0007o\u0003A\u0011AB]\u0011\u001d\u00199\f\u0001C\u0001\u0007\u007fCqaa.\u0001\t\u0003\u00199\rC\u0004\u0004\u0004\u0002!\taa4\t\u000f\r\r\u0005\u0001\"\u0001\u0004V\"91q\u0017\u0001\u0005\u0002\re\u0007bBB\\\u0001\u0011\u00051q\\\u0004\t\u0007G\fY\u0003#\u0001\u0004f\u001aA\u0011\u0011FA\u0016\u0011\u0003\u00199\u000fC\u0004\u0002\b\u001a$\ta!;\t\u000f\r-h\r\"\u0001\u0004n\"91q 4\u0005\u0002\u0011\u0005\u0001b\u0002C\u0005M\u0012\u0005A1\u0002\u0005\b\t#1G\u0011\u0001C\n\u0011\u001d!9B\u001aC\u0001\t3Aq\u0001\"\bg\t\u0003!y\u0002C\u0004\u0003r\u001a$\t\u0001b\t\t\u0013\u0011-c-%A\u0005\u0002\u00115\u0003b\u0002C)M\u0012\u0005A1\u000b\u0004\u0007\t;2\u0007\tb\u0018\t\u0015\rM\u0013O!f\u0001\n\u0003\u0019\u0019\u0004\u0003\u0006\u0005jE\u0014\t\u0012)A\u0005\u0003KB!ba\u001ar\u0005+\u0007I\u0011\u0001C6\u0011)!i'\u001dB\tB\u0003%\u0011\u0011\u0010\u0005\b\u0003\u000f\u000bH\u0011\u0001C8\u0011\u001d!I(\u001dC\u0001\twB\u0011Ba\u000br\u0003\u0003%\t\u0001\"\"\t\u0013\t\u001d\u0013/%A\u0005\u0002\u0011-\u0005\"\u0003B0cF\u0005I\u0011\u0001CH\u0011%\u0011))]A\u0001\n\u0003\u00129\tC\u0005\u0003\u001aF\f\t\u0011\"\u0001\u0002\u0000\"I!1T9\u0002\u0002\u0013\u0005A1\u0013\u0005\n\u0005S\u000b\u0018\u0011!C!\u0005WC\u0011B!/r\u0003\u0003%\t\u0001b&\t\u0013\t}\u0016/!A\u0005B\u0011m\u0005\"\u0003Bcc\u0006\u0005I\u0011\tBd\u0011%\u0011i-]A\u0001\n\u0003\"yjB\u0005\u0005$\u001a\f\t\u0011#\u0001\u0005&\u001aIAQ\f4\u0002\u0002#\u0005Aq\u0015\u0005\t\u0003\u000f\u000bI\u0001\"\u0001\u00050\"Q!\u0011ZA\u0005\u0003\u0003%)Ea3\t\u0015\tE\u0018\u0011BA\u0001\n\u0003#\t\f\u0003\u0006\u0004\u000e\u0005%\u0011\u0011!CA\toC!\u0002b0\u0002\n\u0005\u0005I\u0011\u0002Ca\u0011\u001d!IM\u001aC\u0001\t\u0017Dq\u0001\"6g\t\u0003!9\u000eC\u0005\u0005n\u001a\f\n\u0011\"\u0001\u0005p\"IA1\u001f4\u0012\u0002\u0013\u0005A1\u0012\u0005\n\tk4\u0017\u0013!C\u0001\t\u001fC\u0011\u0002b>g#\u0003%\tAa\u001f\t\u0013\u0011eh-%A\u0005\u0002\u00115\u0003\"\u0003C~MF\u0005I\u0011\u0001C'\u0011%!iPZI\u0001\n\u0003!i\u0005C\u0005\u0005@\u001a\f\t\u0011\"\u0003\u0005B\n\u0011\u0012+^1ee\u0006$\u0018nY'j]&l\u0017N_3s\u0015\u0011\ti#a\f\u0002\u0011A\u0014x\u000e_5nC2TA!!\r\u00024\u0005Aq\u000e\u001d;j[&TXM\u0003\u0002\u00026\u00051!M]3fu\u0016\u001c\u0001aE\u0003\u0001\u0003w\t9\u0005\u0005\u0003\u0002>\u0005\rSBAA \u0015\t\t\t%A\u0003tG\u0006d\u0017-\u0003\u0003\u0002F\u0005}\"AB!osJ+g\r\u0005\u0003\u0002J\u0005=SBAA&\u0015\u0011\ti%a\r\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003#\nYEA\nTKJL\u0017\r\\5{C\ndW\rT8hO&tw-A\u0003o\u000fJ\fW\u000e\u0005\u0003\u0002>\u0005]\u0013\u0002BA-\u0003\u007f\u00111!\u00138u!\u0011\ti&a\u0018\u000e\u0005\u0005-\u0012\u0002BA1\u0003W\u0011\u0001\u0002\u0015:pq&l\u0017\r\\\u0001\u0004\u0003\u0016\f\bCBA4\u0003[\n\t(\u0004\u0002\u0002j)!\u00111NA\u001a\u0003\u0019a\u0017N\\1mO&!\u0011qNA5\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\t\u0005u\u00121O\u0005\u0005\u0003k\nyD\u0001\u0004E_V\u0014G.Z\u0001\u0004E\u0016\f\bCBA4\u0003w\n\t(\u0003\u0003\u0002~\u0005%$a\u0003#f]N,g+Z2u_J\f\u0001\"\\1y\u0013R,'o]\u0001\u0007C\n\u001cHo\u001c7\u0002\rI,G\u000e^8m\u0003\u0015\tG\u000e\u001d5b\u0003\u0019a\u0014N\\5u}Q\u0011\u00121RAG\u0003\u001f\u000b\t*a%\u0002\u0016\u0006]\u0015\u0011TAN!\r\ti\u0006\u0001\u0005\b\u0003'J\u0001\u0019AA+\u0011%\ti#\u0003I\u0001\u0002\u0004\tY\u0006C\u0005\u0002d%\u0001\n\u00111\u0001\u0002f!I\u0011qO\u0005\u0011\u0002\u0003\u0007\u0011\u0011\u0010\u0005\n\u0003\u007fJ\u0001\u0013!a\u0001\u0003+B\u0011\"!!\n!\u0003\u0005\r!!\u001d\t\u0013\u0005\r\u0015\u0002%AA\u0002\u0005E\u0004\"CAC\u0013A\u0005\t\u0019AA9\u0005\r\u0011E)\u0014\u0002\u0004\u0005\u00123&!B*uCR,7c\u0002\u0007\u0002<\u0005\u0015\u00161\u0016\t\u0005\u0003{\t9+\u0003\u0003\u0002*\u0006}\"a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003[\u000biL\u0004\u0003\u00020\u0006ef\u0002BAY\u0003ok!!a-\u000b\t\u0005U\u0016qG\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005\u0005\u0013\u0002BA^\u0003\u007f\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002@\u0006\u0005'\u0001D*fe&\fG.\u001b>bE2,'\u0002BA^\u0003\u007f\t\u0011\u0001_\u000b\u0003\u0003\u000f\u00042!!3\f\u001b\u0005\u0001\u0011A\u0001=!\u0003\u0005)\u0018AA;!\u0003\u0005Q\u0018A\u0001>!\u0003\u0015\u00198-\u00197f\u0003\u0019\u00198-\u00197fA\u0005\t!+\u0006\u0002\u0002^B\u0019\u0011\u0011\u001a\u0006\u0002\u0005I\u0003\u0013!\u00029jm>$XCAAs!\u0019\ti$a:\u0002V%!\u0011\u0011^A \u0005\u0015\t%O]1z\u0003\u0019\u0001\u0018N^8uA\u0005!\u0001\u0010S1u\u0003\u0015A\b*\u0019;!\u0003\u0011Qx\n\u001c3\u0002\u000bi|E\u000e\u001a\u0011\u0002\u0011I,7/\u001b3vC2\f\u0011B]3tS\u0012,\u0018\r\u001c\u0011\u0002\u0003M\f!a\u001d\u0011\u0002\t%$XM]\u000b\u0003\u0003+\nQ!\u001b;fe\u0002\n\u0011bY8om\u0016\u0014x-\u001a3\u0016\u0005\t\u001d\u0001\u0003BA\u001f\u0005\u0013IAAa\u0003\u0002@\t9!i\\8mK\u0006t\u0017AC2p]Z,'oZ3eAQQ\"\u0011\u0003B\n\u0005+\u00119B!\u0007\u0003\u001c\tu!q\u0004B\u0011\u0005G\u0011)Ca\n\u0003*A\u0019\u0011\u0011\u001a\u0007\t\u000f\u0005\rW\u00051\u0001\u0002H\"9\u0011QZ\u0013A\u0002\u0005\u001d\u0007bBAiK\u0001\u0007\u0011q\u0019\u0005\b\u0003+,\u0003\u0019AAd\u0011\u001d\tI.\na\u0001\u0003;Dq!!9&\u0001\u0004\t)\u000fC\u0004\u0002n\u0016\u0002\r!a2\t\u000f\u0005EX\u00051\u0001\u0002H\"9\u0011Q_\u0013A\u0002\u0005\u001d\u0007bBA}K\u0001\u0007\u0011q\u0019\u0005\b\u0003{,\u0003\u0019AA+\u0011\u001d\u0011\u0019!\na\u0001\u0005\u000f\tAaY8qsRQ\"\u0011\u0003B\u0018\u0005c\u0011\u0019D!\u000e\u00038\te\"1\bB\u001f\u0005\u007f\u0011\tEa\u0011\u0003F!I\u00111\u0019\u0014\u0011\u0002\u0003\u0007\u0011q\u0019\u0005\n\u0003\u001b4\u0003\u0013!a\u0001\u0003\u000fD\u0011\"!5'!\u0003\u0005\r!a2\t\u0013\u0005Ug\u0005%AA\u0002\u0005\u001d\u0007\"CAmMA\u0005\t\u0019AAo\u0011%\t\tO\nI\u0001\u0002\u0004\t)\u000fC\u0005\u0002n\u001a\u0002\n\u00111\u0001\u0002H\"I\u0011\u0011\u001f\u0014\u0011\u0002\u0003\u0007\u0011q\u0019\u0005\n\u0003k4\u0003\u0013!a\u0001\u0003\u000fD\u0011\"!?'!\u0003\u0005\r!a2\t\u0013\u0005uh\u0005%AA\u0002\u0005U\u0003\"\u0003B\u0002MA\u0005\t\u0019\u0001B\u0004\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"Aa\u0013+\t\u0005\u001d'QJ\u0016\u0003\u0005\u001f\u0002BA!\u0015\u0003\\5\u0011!1\u000b\u0006\u0005\u0005+\u00129&A\u0005v]\u000eDWmY6fI*!!\u0011LA \u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005;\u0012\u0019FA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*TC\u0001B5U\u0011\tiN!\u0014\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%mU\u0011!q\u000e\u0016\u0005\u0003K\u0014i%\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%q\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012J\u0014aD2paf$C-\u001a4bk2$H%\r\u0019\u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cE*\"A! +\t\u0005U#QJ\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132eU\u0011!1\u0011\u0016\u0005\u0005\u000f\u0011i%A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005\u0013\u0003BAa#\u0003\u00166\u0011!Q\u0012\u0006\u0005\u0005\u001f\u0013\t*\u0001\u0003mC:<'B\u0001BJ\u0003\u0011Q\u0017M^1\n\t\t]%Q\u0012\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!!q\u0014BS!\u0011\tiD!)\n\t\t\r\u0016q\b\u0002\u0004\u0003:L\b\"\u0003BTk\u0005\u0005\t\u0019AA+\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!Q\u0016\t\u0007\u0005_\u0013)La(\u000e\u0005\tE&\u0002\u0002BZ\u0003\u007f\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\u00119L!-\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005\u000f\u0011i\fC\u0005\u0003(^\n\t\u00111\u0001\u0003 \u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011IIa1\t\u0013\t\u001d\u0006(!AA\u0002\u0005U\u0013\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005U\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\t%\u0015AB3rk\u0006d7\u000f\u0006\u0003\u0003\b\tE\u0007\"\u0003BTw\u0005\u0005\t\u0019\u0001BP\u0003\u0015\u0019F/\u0019;f!\r\tI-P\n\u0006{\te'Q\u001d\t\u001f\u00057\u0014\t/a2\u0002H\u0006\u001d\u0017qYAo\u0003K\f9-a2\u0002H\u0006\u001d\u0017Q\u000bB\u0004\u0005#i!A!8\u000b\t\t}\u0017qH\u0001\beVtG/[7f\u0013\u0011\u0011\u0019O!8\u0003%\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017G\r\t\u0005\u0005O\u0014i/\u0004\u0002\u0003j*!!1\u001eBI\u0003\tIw.\u0003\u0003\u0002@\n%HC\u0001Bk\u0003\u0015\t\u0007\u000f\u001d7z)i\u0011\tB!>\u0003x\ne(1 B\u007f\u0005\u007f\u001c\taa\u0001\u0004\u0006\r\u001d1\u0011BB\u0006\u0011\u001d\t\u0019\r\u0011a\u0001\u0003\u000fDq!!4A\u0001\u0004\t9\rC\u0004\u0002R\u0002\u0003\r!a2\t\u000f\u0005U\u0007\t1\u0001\u0002H\"9\u0011\u0011\u001c!A\u0002\u0005u\u0007bBAq\u0001\u0002\u0007\u0011Q\u001d\u0005\b\u0003[\u0004\u0005\u0019AAd\u0011\u001d\t\t\u0010\u0011a\u0001\u0003\u000fDq!!>A\u0001\u0004\t9\rC\u0004\u0002z\u0002\u0003\r!a2\t\u000f\u0005u\b\t1\u0001\u0002V!9!1\u0001!A\u0002\t\u001d\u0011aB;oCB\u0004H.\u001f\u000b\u0005\u0007#\u0019i\u0002\u0005\u0004\u0002>\rM1qC\u0005\u0005\u0007+\tyD\u0001\u0004PaRLwN\u001c\t\u001d\u0003{\u0019I\"a2\u0002H\u0006\u001d\u0017qYAo\u0003K\f9-a2\u0002H\u0006\u001d\u0017Q\u000bB\u0004\u0013\u0011\u0019Y\"a\u0010\u0003\u000fQ+\b\u000f\\32e!I1qD!\u0002\u0002\u0003\u0007!\u0011C\u0001\u0004q\u0012\u0002\u0014A\u00047j]\u0016\f'/R9vC2LG/_\u0001\u0010Y&tW-\u0019:FcV\fG.\u001b;zA\u0005\ta.\u0001\u0002oA\u0005!a-\u001e7m\u0003\u00151W\u000f\u001c7!\u0003%)\b\u000f]3s'&TX-\u0001\u0006vaB,'oU5{K\u0002\n1a^:I+\t\t)'\u0001\u0003xg\"\u0003\u0013\u0001\u0003;sC:\u001c\u0018)Z9\u0002\u0013Q\u0014\u0018M\\:BKF\u0004\u0013!C1e[6LE/\u001a:t\u0003)\tG-\\7Ji\u0016\u00148\u000fI\u0001\fO\u0016$\bK]8yS6\fG.\u0006\u0002\u0002\\\u0005\u0019R\u000f\u001d3bi\u0016\fV/Y:j\t\u00164\u0017N\\5uKV\u0011!qT\u0001\u000bkB$\u0017\r^3He\u0006lG\u0003BB&\u0007#\u0002B!!\u0010\u0004N%!1qJA \u0005\u0011)f.\u001b;\t\u000f\rM#\u000b1\u0001\u0002f\u0005\t\u0001\n\u0006\u0003\u0004L\r]\u0003bBB-'\u0002\u000711L\u0001\u0006kB\u0004XM\u001d\t\u0007\u0003{\t9/!\u001d\u0002\u0015%t\u0017\u000e^5bY&TX-\u0006\u0002\u0003\u0012\u0005)!/Z:fiR1!\u0011CB3\u0007SBqaa\u001aV\u0001\u0004\tI(A\u0001r\u0011\u001d\u0019Y'\u0016a\u0001\u0005#\tQa\u001d;bi\u0016\fA\"\u001e9eCR,\u0007K]5nC2$\"ca\u0013\u0004r\rM4QOB<\u0007s\u001aYha \u0004\u0002\"91q\r,A\u0002\u0005\u001d\u0007bBAb-\u0002\u0007\u0011q\u0019\u0005\b\u0003\u001b4\u0006\u0019AAd\u0011\u001d\t\tN\u0016a\u0001\u0003\u000fDq!!6W\u0001\u0004\t9\rC\u0004\u0004~Y\u0003\r!!\u001d\u0002\u0007IDw\u000eC\u0004\u0002ZZ\u0003\r!!8\t\u000f\u0005\u0005h\u000b1\u0001\u0002f\u00061R.\u001b8j[&TX-\u00118e%\u0016$XO\u001d8Ti\u0006$X\r\u0006\u0006\u0003\u0012\r\u001d5\u0011RBF\u0007\u001fCqaa\u001aX\u0001\u0004\tI\bC\u0004\u0004~]\u0003\r!!\u001d\t\u000f\r5u\u000b1\u0001\u0003\u0012\u0005a\u0011N\\5uS\u0006d7\u000b^1uK\"I1\u0011S,\u0011\u0002\u0003\u0007!qA\u0001\u000be\u0016\u001cX\r^*uCR,\u0017\u0001I7j]&l\u0017N_3B]\u0012\u0014V\r^;s]N#\u0018\r^3%I\u00164\u0017-\u001e7uIQ\n\u0001cY8naV$XM\u00155p'B\f'o]3\u0015\t\u0005E4\u0011\u0014\u0005\b\u0007'J\u0006\u0019AA3\u0003)\u0019w.\u001c9vi\u0016\u0014\u0006n\u001c\u000b\u0005\u0003c\u001ay\nC\u0004\u0004Ti\u0003\r!!\u001a\u0015\r\tE11UBS\u0011\u001d\u00199g\u0017a\u0001\u0003sBqa!$\\\u0001\u0004\u0011\t\u0002\u0006\u0005\u0003\u0012\r%61VBW\u0011\u001d\u0019\u0019\u0006\u0018a\u0001\u0003KBqaa\u001a]\u0001\u0004\tI\bC\u0004\u0004\u000er\u0003\rA!\u0005\u0015\u0011\tE1\u0011WBZ\u0007kCqa!\u0017^\u0001\u0004\u0019Y\u0006C\u0004\u0004hu\u0003\r!!\u001f\t\u000f\r5U\f1\u0001\u0003\u0012\u0005AQ.\u001b8j[&TX\r\u0006\u0004\u0002z\rm6Q\u0018\u0005\b\u0007Or\u0006\u0019AA=\u0011\u001d\u0019iI\u0018a\u0001\u0005#!\u0002\"!\u001f\u0004B\u000e\r7Q\u0019\u0005\b\u0007'z\u0006\u0019AA3\u0011\u001d\u00199g\u0018a\u0001\u0003sBqa!$`\u0001\u0004\u0011\t\u0002\u0006\u0005\u0002z\r%71ZBg\u0011\u001d\u0019I\u0006\u0019a\u0001\u00077Bqaa\u001aa\u0001\u0004\tI\bC\u0004\u0004\u000e\u0002\u0004\rA!\u0005\u0015\r\tE1\u0011[Bj\u0011\u001d\u0019\u0019&\u0019a\u0001\u0003KBqaa\u001ab\u0001\u0004\tI\b\u0006\u0003\u0003\u0012\r]\u0007bBB4E\u0002\u0007\u0011\u0011\u0010\u000b\u0007\u0003s\u001aYn!8\t\u000f\rM3\r1\u0001\u0002f!91qM2A\u0002\u0005eD\u0003BA=\u0007CDqaa\u001ae\u0001\u0004\tI(\u0001\nRk\u0006$'/\u0019;jG6Kg.[7ju\u0016\u0014\bcAA/MN)a-a\u000f\u0003fR\u00111Q]\u0001\u0005O\u0016lg\u000f\u0006\u0007\u0004L\r=8\u0011_B{\u0007o\u001cY\u0010C\u0004\u0002\u0006\"\u0004\r!!\u001d\t\u000f\rM\b\u000e1\u0001\u0002f\u0005\t\u0011\tC\u0004\u0002D\"\u0004\r!!\u001f\t\u000f\re\b\u000e1\u0001\u0002r\u0005!!-\u001a;b\u0011\u001d\u0019i\u0010\u001ba\u0001\u0003s\n\u0011!_\u0001\u0007I\u001e,GO]:\u0015\u0011\r-C1\u0001C\u0003\t\u000fAqaa=j\u0001\u0004\t)\u0007C\u0004\u0002b&\u0004\r!!:\t\u000f\u0005\r\u0017\u000e1\u0001\u0002z\u00051A\r]8ueN$baa\u0013\u0005\u000e\u0011=\u0001bBBzU\u0002\u0007\u0011Q\r\u0005\b\u0003\u0007T\u0007\u0019AA=\u0003)qwN]7D_2,XN\u001c\u000b\u0005\u0003c\")\u0002C\u0004\u0004T-\u0004\r!!\u001a\u0002'\u0005\u0004\bO]8yS6\fG/Z'bq\u0016Kw-\u001a8\u0015\t\u0005ED1\u0004\u0005\b\u0007'b\u0007\u0019AA3\u0003M\t\u0007\u000f\u001d:pq&l\u0017\r^3NS:,\u0015nZ3o)\u0011\t\t\b\"\t\t\u000f\rMS\u000e1\u0001\u0002fQA\u00111\u0012C\u0013\tS!9\u0005C\u0004\u0005(9\u0004\r!!\u0016\u0002\tI\fgn\u001b\u0005\b\tWq\u0007\u0019\u0001C\u0017\u0003)\u0019wN\\:ue\u0006Lg\u000e\u001e\t\u0005\t_!\tE\u0004\u0003\u00052\u0011ub\u0002\u0002C\u001a\twqA\u0001\"\u000e\u0005:9!\u0011\u0011\u0017C\u001c\u0013\t\t)$\u0003\u0003\u00022\u0005M\u0012\u0002BA\u0017\u0003_IA\u0001b\u0010\u0002,\u0005Q1i\u001c8tiJ\f\u0017N\u001c;\n\t\u0011\rCQ\t\u0002\u000b\u0007>t7\u000f\u001e:bS:$(\u0002\u0002C \u0003WA\u0011\u0002\"\u0013o!\u0003\u0005\r!!\u001d\u0002\r1\fWN\u00193b\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u001aTC\u0001C(U\u0011\t\tH!\u0014\u0002!\r|W\u000e];uK>\u0013'.Z2uSZ,G\u0003CA9\t+\"I\u0006b\u0017\t\u000f\u0011]\u0003\u000f1\u0001\u0002f\u0005\t\u0001\u000eC\u0004\u0004hA\u0004\r!!\u001f\t\u000f\u0005\r\u0007\u000f1\u0001\u0002z\t!1i\\:u'%\t\u00181\bC1\u0003K\u000bY\u000b\u0005\u0004\u0005d\u0011\u0015\u0014\u0011P\u0007\u0003\u0003_IA\u0001b\u001a\u00020\taA)\u001b4g\rVt7\r^5p]\u0006\u0011\u0001\nI\u000b\u0003\u0003s\n!!\u001d\u0011\u0015\r\u0011EDQ\u000fC<!\r!\u0019(]\u0007\u0002M\"911\u000b<A\u0002\u0005\u0015\u0004bBB4m\u0002\u0007\u0011\u0011P\u0001\nG\u0006d7-\u001e7bi\u0016$B\u0001\" \u0005\u0004BA\u0011Q\bC@\u0003c\nI(\u0003\u0003\u0005\u0002\u0006}\"A\u0002+va2,'\u0007C\u0004\u0002D^\u0004\r!!\u001f\u0015\r\u0011EDq\u0011CE\u0011%\u0019\u0019\u0006\u001fI\u0001\u0002\u0004\t)\u0007C\u0005\u0004ha\u0004\n\u00111\u0001\u0002zU\u0011AQ\u0012\u0016\u0005\u0003K\u0012i%\u0006\u0002\u0005\u0012*\"\u0011\u0011\u0010B')\u0011\u0011y\n\"&\t\u0013\t\u001dV0!AA\u0002\u0005UC\u0003\u0002B\u0004\t3C\u0011Ba*\u0000\u0003\u0003\u0005\rAa(\u0015\t\t%EQ\u0014\u0005\u000b\u0005O\u000b\t!!AA\u0002\u0005UC\u0003\u0002B\u0004\tCC!Ba*\u0002\u0006\u0005\u0005\t\u0019\u0001BP\u0003\u0011\u0019un\u001d;\u0011\t\u0011M\u0014\u0011B\n\u0007\u0003\u0013!IK!:\u0011\u0015\tmG1VA3\u0003s\"\t(\u0003\u0003\u0005.\nu'!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeQ\u0011AQ\u0015\u000b\u0007\tc\"\u0019\f\".\t\u0011\rM\u0013q\u0002a\u0001\u0003KB\u0001ba\u001a\u0002\u0010\u0001\u0007\u0011\u0011\u0010\u000b\u0005\ts#i\f\u0005\u0004\u0002>\rMA1\u0018\t\t\u0003{!y(!\u001a\u0002z!Q1qDA\t\u0003\u0003\u0005\r\u0001\"\u001d\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0011\r\u0007\u0003\u0002BF\t\u000bLA\u0001b2\u0003\u000e\n1qJ\u00196fGR\f\u0011c\u001c9uS6L'0Z,ji\"d%IR$T)!\tI\b\"4\u0005R\u0012M\u0007\u0002\u0003Ch\u0003+\u0001\r!!\u001f\u0002\t%t\u0017\u000e\u001e\u0005\t\u0007'\n)\u00021\u0001\u0002f!A1qMA\u000b\u0001\u0004\tI(\u0001\u0003nC&tG\u0003BB&\t3D\u0001\u0002b7\u0002\u0018\u0001\u0007AQ\\\u0001\u0005CJ<7\u000f\u0005\u0004\u0002>\u0005\u001dHq\u001c\t\u0005\tC$IO\u0004\u0003\u0005d\u0012\u0015\b\u0003BAY\u0003\u007fIA\u0001b:\u0002@\u00051\u0001K]3eK\u001aLAAa&\u0005l*!Aq]A \u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%eU\u0011A\u0011\u001f\u0016\u0005\u00037\u0012i%A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00136\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%m\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uI]\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012B\u0004"
)
public class QuadraticMinimizer implements SerializableLogging {
   private volatile State$ State$module;
   private final int nGram;
   private final Proximal proximal;
   private final DenseMatrix Aeq;
   private final DenseVector beq;
   private final double abstol;
   private final double reltol;
   private final double alpha;
   private final int linearEquality;
   private final int n;
   private final int full;
   private final int upperSize;
   private final DenseMatrix wsH;
   private final DenseMatrix transAeq;
   private final int admmIters;
   private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   public static double $lessinit$greater$default$8() {
      return QuadraticMinimizer$.MODULE$.$lessinit$greater$default$8();
   }

   public static double $lessinit$greater$default$7() {
      return QuadraticMinimizer$.MODULE$.$lessinit$greater$default$7();
   }

   public static double $lessinit$greater$default$6() {
      return QuadraticMinimizer$.MODULE$.$lessinit$greater$default$6();
   }

   public static int $lessinit$greater$default$5() {
      return QuadraticMinimizer$.MODULE$.$lessinit$greater$default$5();
   }

   public static DenseVector $lessinit$greater$default$4() {
      return QuadraticMinimizer$.MODULE$.$lessinit$greater$default$4();
   }

   public static DenseMatrix $lessinit$greater$default$3() {
      return QuadraticMinimizer$.MODULE$.$lessinit$greater$default$3();
   }

   public static Proximal $lessinit$greater$default$2() {
      return QuadraticMinimizer$.MODULE$.$lessinit$greater$default$2();
   }

   public static void main(final String[] args) {
      QuadraticMinimizer$.MODULE$.main(args);
   }

   public static DenseVector optimizeWithLBFGS(final DenseVector init, final DenseMatrix H, final DenseVector q) {
      return QuadraticMinimizer$.MODULE$.optimizeWithLBFGS(init, H, q);
   }

   public static double computeObjective(final DenseMatrix h, final DenseVector q, final DenseVector x) {
      return QuadraticMinimizer$.MODULE$.computeObjective(h, q, x);
   }

   public static double apply$default$3() {
      return QuadraticMinimizer$.MODULE$.apply$default$3();
   }

   public static QuadraticMinimizer apply(final int rank, final Enumeration.Value constraint, final double lambda) {
      return QuadraticMinimizer$.MODULE$.apply(rank, constraint, lambda);
   }

   public static double approximateMinEigen(final DenseMatrix H) {
      return QuadraticMinimizer$.MODULE$.approximateMinEigen(H);
   }

   public static double approximateMaxEigen(final DenseMatrix H) {
      return QuadraticMinimizer$.MODULE$.approximateMaxEigen(H);
   }

   public static double normColumn(final DenseMatrix H) {
      return QuadraticMinimizer$.MODULE$.normColumn(H);
   }

   public static void dpotrs(final DenseMatrix A, final DenseVector x) {
      QuadraticMinimizer$.MODULE$.dpotrs(A, x);
   }

   public static void dgetrs(final DenseMatrix A, final int[] pivot, final DenseVector x) {
      QuadraticMinimizer$.MODULE$.dgetrs(A, pivot, x);
   }

   public static void gemv(final double alpha, final DenseMatrix A, final DenseVector x, final double beta, final DenseVector y) {
      QuadraticMinimizer$.MODULE$.gemv(alpha, A, x, beta, y);
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public State$ State() {
      if (this.State$module == null) {
         this.State$lzycompute$1();
      }

      return this.State$module;
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return this.breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      this.breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public int linearEquality() {
      return this.linearEquality;
   }

   public int n() {
      return this.n;
   }

   public int full() {
      return this.full;
   }

   public int upperSize() {
      return this.upperSize;
   }

   private DenseMatrix wsH() {
      return this.wsH;
   }

   public DenseMatrix transAeq() {
      return this.transAeq;
   }

   public int admmIters() {
      return this.admmIters;
   }

   public Proximal getProximal() {
      return this.proximal;
   }

   private Object updateQuasiDefinite() {
      Object var10000;
      if (this.linearEquality() > 0) {
         ((NumericOps)this.wsH().apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(this.nGram), this.nGram + this.Aeq.rows()), .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.Aeq.cols()), HasOps$.MODULE$.canSliceColsAndRows())).$colon$eq(this.Aeq, HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet());
         ((NumericOps)this.wsH().apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.nGram), .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(this.nGram), this.nGram + this.Aeq.rows()), HasOps$.MODULE$.canSliceColsAndRows())).$colon$eq(this.transAeq(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet());
         var10000 = ((NumericOps)this.wsH().apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(this.nGram), this.nGram + this.Aeq.rows()), .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(this.nGram), this.nGram + this.Aeq.rows()), HasOps$.MODULE$.canSliceColsAndRows())).$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.dm_s_UpdateOp_Double_OpSet());
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return var10000;
   }

   public void updateGram(final DenseMatrix H) {
      ((NumericOps)this.wsH().apply(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.nGram), .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.nGram), HasOps$.MODULE$.canSliceColsAndRows())).$colon$eq(H, HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet());
      this.updateQuasiDefinite();
   }

   public void updateGram(final double[] upper) {
      int left$macro$1 = upper.length;
      int right$macro$2 = this.upperSize();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(78)).append("requirement failed: ").append("QuadraticMinimizer:updateGram upper triangular size mismatch").append(": ").append("upper.length == QuadraticMinimizer.this.upperSize (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         int i = 0;
         int pos = 0;

         for(double h = (double)0.0F; i < this.nGram; ++i) {
            for(int j = 0; j <= i; ++j) {
               h = upper[pos];
               this.wsH().update$mcD$sp(i, j, h);
               this.wsH().update$mcD$sp(j, i, h);
               ++pos;
            }
         }

         this.updateQuasiDefinite();
      }
   }

   public State initialize() {
      int[] pivot = null;
      if (this.linearEquality() > 0) {
         pivot = (int[])scala.Array..MODULE$.fill(this.n(), (JFunction0.mcI.sp)() -> 0, scala.reflect.ClassTag..MODULE$.Int());
      }

      DenseVector x = DenseVector$.MODULE$.zeros$mDc$sp(this.nGram, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector z = DenseVector$.MODULE$.zeros$mDc$sp(this.nGram, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector u = DenseVector$.MODULE$.zeros$mDc$sp(this.nGram, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector scale = DenseVector$.MODULE$.zeros$mDc$sp(this.n(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      State var10000;
      if (this.proximal == null) {
         var10000 = this.State().apply(x, u, z, scale, (DenseMatrix)null, pivot, (DenseVector)null, (DenseVector)null, (DenseVector)null, (DenseVector)null, 0, false);
      } else {
         DenseVector xHat = DenseVector$.MODULE$.zeros$mDc$sp(this.nGram, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseVector zOld = DenseVector$.MODULE$.zeros$mDc$sp(this.nGram, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseVector residual = DenseVector$.MODULE$.zeros$mDc$sp(this.nGram, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseVector s = DenseVector$.MODULE$.zeros$mDc$sp(this.nGram, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         var10000 = this.State().apply(x, u, z, scale, (DenseMatrix)null, pivot, xHat, zOld, residual, s, 0, false);
      }

      return var10000;
   }

   private State reset(final DenseVector q, final State state) {
      int nlinear = q.length();
      intW info = new intW(0);
      if (this.linearEquality() > 0) {
         int equality = nlinear + this.beq.length();
         boolean cond$macro$1 = this.wsH().rows() == equality && this.wsH().cols() == equality;
         if (!cond$macro$1) {
            throw new IllegalArgumentException((new StringBuilder(117)).append("requirement failed: ").append("QuadraticMinimizer:reset quasi definite and linear size mismatch").append(": ").append("QuadraticMinimizer.this.wsH.rows.==(equality).&&(QuadraticMinimizer.this.wsH.cols.==(equality))").toString());
         }

         LAPACK.getInstance().dgetrf(this.n(), this.n(), this.wsH().data$mcD$sp(), scala.math.package..MODULE$.max(1, this.n()), state.pivot(), info);
      } else {
         boolean cond$macro$2 = this.wsH().rows() == nlinear && this.wsH().cols() == nlinear;
         if (!cond$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(115)).append("requirement failed: ").append("QuadraticMinimizer:reset cholesky and linear size mismatch").append(": ").append("QuadraticMinimizer.this.wsH.rows.==(nlinear).&&(QuadraticMinimizer.this.wsH.cols.==(nlinear))").toString());
         }

         LAPACK.getInstance().dpotrf("L", this.n(), this.wsH().data$mcD$sp(), scala.math.package..MODULE$.max(1, this.n()), info);
      }

      state.x().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
      state.u().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
      state.z().$colon$eq(BoxesRunTime.boxToDouble((double)0.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpSet());
      return this.State().apply(state.x(), state.u(), state.z(), state.scale(), this.wsH(), state.pivot(), state.xHat(), state.zOld(), state.residual(), state.s(), 0, false);
   }

   private void updatePrimal(final DenseVector q, final DenseVector x, final DenseVector u, final DenseVector z, final DenseVector scale, final double rho, final DenseMatrix R, final int[] pivot) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = z.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         double entryScale = rho * (z.apply$mcD$sp(index$macro$2) - u.apply$mcD$sp(index$macro$2)) - q.apply$mcD$sp(index$macro$2);
         scale.update$mcD$sp(index$macro$2, entryScale);
      }

      if (this.linearEquality() > 0) {
         int index$macro$7 = 0;

         for(int limit$macro$9 = this.beq.length(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            scale.update$mcD$sp(this.nGram + index$macro$7, this.beq.apply$mcD$sp(index$macro$7));
         }
      }

      if (this.linearEquality() > 0) {
         QuadraticMinimizer$.MODULE$.dgetrs(R, pivot, scale);
      } else {
         QuadraticMinimizer$.MODULE$.dpotrs(R, scale);
      }

      int index$macro$12 = 0;

      for(int limit$macro$14 = x.length(); index$macro$12 < limit$macro$14; ++index$macro$12) {
         x.update$mcD$sp(index$macro$12, scale.apply$mcD$sp(index$macro$12));
      }

   }

   public State minimizeAndReturnState(final DenseVector q, final double rho, final State initialState, final boolean resetState) {
      State startState = resetState ? this.reset(q, initialState) : initialState;
      if (this.proximal == null) {
         this.updatePrimal(q, startState.x(), startState.u(), startState.z(), startState.scale(), rho, startState.R(), startState.pivot());
         startState.z().$colon$eq(startState.x(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
         return this.State().apply(startState.x(), startState.u(), startState.z(), startState.scale(), startState.R(), startState.pivot(), startState.xHat(), startState.zOld(), startState.residual(), startState.s(), 1, true);
      } else {
         double convergenceScale = scala.math.package..MODULE$.sqrt((double)this.n());

         int nextIter;
         for(nextIter = 0; nextIter <= this.admmIters(); ++nextIter) {
            this.updatePrimal(q, startState.x(), startState.u(), startState.z(), startState.scale(), rho, startState.R(), startState.pivot());
            startState.zOld().$colon$eq(startState.z(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            startState.zOld().$times$eq(BoxesRunTime.boxToDouble((double)1 - this.alpha), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
            startState.xHat().$colon$eq(startState.x(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            startState.xHat().$times$eq(BoxesRunTime.boxToDouble(this.alpha), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
            startState.xHat().$plus$eq(startState.zOld(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            startState.zOld().$colon$eq(startState.z(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            startState.z().$colon$eq(startState.xHat(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            startState.z().$plus$eq(startState.u(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            this.proximal.prox(startState.z(), rho);
            startState.xHat().$minus$eq(startState.z(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double());
            startState.u().$plus$eq(startState.xHat(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
            startState.residual().$colon$eq(startState.x(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            startState.residual().$minus$eq(startState.z(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double());
            double residualNorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(startState.residual(), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
            startState.s().$colon$eq(startState.z(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            startState.s().$minus$eq(startState.zOld(), HasOps$.MODULE$.impl_OpSub_InPlace_DV_DV_Double());
            startState.s().$times$eq(BoxesRunTime.boxToDouble(-rho), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
            double sNorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(startState.s(), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
            startState.residual().$colon$eq(startState.z(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            startState.residual().$times$eq(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
            startState.s().$colon$eq(startState.u(), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
            startState.s().$times$eq(BoxesRunTime.boxToDouble(rho), HasOps$.MODULE$.impl_Op_InPlace_DV_S_Double_OpMulScalar());
            double epsPrimal = convergenceScale * this.abstol + this.reltol * max$.MODULE$.apply$mDDDc$sp(BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(startState.x(), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))), BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(startState.residual(), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))), max$.MODULE$.maxImpl2_Double());
            double epsDual = convergenceScale * this.abstol + this.reltol * BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(startState.s(), BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
            if (residualNorm < epsPrimal && sNorm < epsDual) {
               return this.State().apply(startState.x(), startState.u(), startState.z(), startState.scale(), startState.R(), startState.pivot(), startState.xHat(), startState.zOld(), startState.residual(), startState.s(), nextIter, true);
            }
         }

         return this.State().apply(startState.x(), startState.u(), startState.z(), startState.scale(), startState.R(), startState.pivot(), startState.xHat(), startState.zOld(), startState.residual(), startState.s(), nextIter, false);
      }
   }

   private double computeRhoSparse(final DenseMatrix H) {
      double eigenMax = QuadraticMinimizer$.MODULE$.normColumn(H);
      boolean cond$macro$1 = this.linearEquality() <= 0;
      if (!cond$macro$1) {
         throw new IllegalArgumentException((new StringBuilder(66)).append("requirement failed: ").append("QuadraticMinimizer:computeRho L1 with affine not supported").append(": ").append("QuadraticMinimizer.this.linearEquality.<=(0)").toString());
      } else {
         double eigenMin = QuadraticMinimizer$.MODULE$.approximateMinEigen(H);
         return scala.math.package..MODULE$.sqrt(eigenMin * eigenMax);
      }
   }

   private double computeRho(final DenseMatrix H) {
      Proximal var4 = this.proximal;
      double var2;
      if (var4 == null) {
         var2 = (double)0.0F;
      } else if (var4 instanceof ProximalL1 && true) {
         var2 = this.computeRhoSparse(H);
      } else if (var4 instanceof ProjectProbabilitySimplex && true) {
         var2 = this.computeRhoSparse(H);
      } else {
         var2 = scala.math.package..MODULE$.sqrt(QuadraticMinimizer$.MODULE$.normColumn(H));
      }

      return var2;
   }

   public State minimizeAndReturnState(final DenseVector q, final State initialState) {
      double rho = this.computeRho(this.wsH());
      int index$macro$2 = 0;

      for(int limit$macro$4 = q.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         this.wsH().update$mcD$sp(index$macro$2, index$macro$2, this.wsH().apply$mcD$sp(index$macro$2, index$macro$2) + rho);
      }

      return this.minimizeAndReturnState(q, rho, initialState, this.minimizeAndReturnState$default$4());
   }

   public State minimizeAndReturnState(final DenseMatrix H, final DenseVector q, final State initialState) {
      this.updateGram(H);
      return this.minimizeAndReturnState(q, initialState);
   }

   public State minimizeAndReturnState(final double[] upper, final DenseVector q, final State initialState) {
      this.updateGram(upper);
      return this.minimizeAndReturnState(q, initialState);
   }

   public DenseVector minimize(final DenseVector q, final State initialState) {
      return this.minimizeAndReturnState(q, initialState).z();
   }

   public DenseVector minimize(final DenseMatrix H, final DenseVector q, final State initialState) {
      return this.minimizeAndReturnState(H, q, initialState).z();
   }

   public DenseVector minimize(final double[] upper, final DenseVector q, final State initialState) {
      return this.minimizeAndReturnState(upper, q, initialState).z();
   }

   public State minimizeAndReturnState(final DenseMatrix H, final DenseVector q) {
      return this.minimizeAndReturnState(H, q, this.initialize());
   }

   public State minimizeAndReturnState(final DenseVector q) {
      return this.minimizeAndReturnState(q, this.initialize());
   }

   public DenseVector minimize(final DenseMatrix H, final DenseVector q) {
      return this.minimize(H, q, this.initialize());
   }

   public DenseVector minimize(final DenseVector q) {
      return this.minimize(q, this.initialize());
   }

   public boolean minimizeAndReturnState$default$4() {
      return true;
   }

   private final void State$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.State$module == null) {
            this.State$module = new State$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public QuadraticMinimizer(final int nGram, final Proximal proximal, final DenseMatrix Aeq, final DenseVector beq, final int maxIters, final double abstol, final double reltol, final double alpha) {
      this.nGram = nGram;
      this.proximal = proximal;
      this.Aeq = Aeq;
      this.beq = beq;
      this.abstol = abstol;
      this.reltol = reltol;
      this.alpha = alpha;
      SerializableLogging.$init$(this);
      this.linearEquality = Aeq != null ? Aeq.rows() : 0;
      if (this.linearEquality() > 0) {
         int left$macro$1 = beq.length();
         int right$macro$2 = this.linearEquality();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(105)).append("requirement failed: ").append("QuadraticMinimizer linear equalities should match beq vector").append(": ").append("QuadraticMinimizer.this.beq.length == QuadraticMinimizer.this.linearEquality (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         }
      }

      this.n = nGram + this.linearEquality();
      this.full = this.n() * this.n();
      this.upperSize = nGram * (nGram + 1) / 2;
      this.wsH = DenseMatrix$.MODULE$.zeros$mDc$sp(this.n(), this.n(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      this.transAeq = this.linearEquality() > 0 ? (DenseMatrix)Aeq.t(HasOps$.MODULE$.canTranspose_DM()) : null;
      this.admmIters = maxIters < 0 ? Math.max(400, 20 * this.n()) : maxIters;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class State implements Product, Serializable {
      private final DenseVector x;
      private final DenseVector u;
      private final DenseVector z;
      private final DenseVector scale;
      private final DenseMatrix R;
      private final int[] pivot;
      private final DenseVector xHat;
      private final DenseVector zOld;
      private final DenseVector residual;
      private final DenseVector s;
      private final int iter;
      private final boolean converged;
      // $FF: synthetic field
      public final QuadraticMinimizer $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public DenseVector x() {
         return this.x;
      }

      public DenseVector u() {
         return this.u;
      }

      public DenseVector z() {
         return this.z;
      }

      public DenseVector scale() {
         return this.scale;
      }

      public DenseMatrix R() {
         return this.R;
      }

      public int[] pivot() {
         return this.pivot;
      }

      public DenseVector xHat() {
         return this.xHat;
      }

      public DenseVector zOld() {
         return this.zOld;
      }

      public DenseVector residual() {
         return this.residual;
      }

      public DenseVector s() {
         return this.s;
      }

      public int iter() {
         return this.iter;
      }

      public boolean converged() {
         return this.converged;
      }

      public State copy(final DenseVector x, final DenseVector u, final DenseVector z, final DenseVector scale, final DenseMatrix R, final int[] pivot, final DenseVector xHat, final DenseVector zOld, final DenseVector residual, final DenseVector s, final int iter, final boolean converged) {
         return this.breeze$optimize$proximal$QuadraticMinimizer$State$$$outer().new State(x, u, z, scale, R, pivot, xHat, zOld, residual, s, iter, converged);
      }

      public DenseVector copy$default$1() {
         return this.x();
      }

      public DenseVector copy$default$10() {
         return this.s();
      }

      public int copy$default$11() {
         return this.iter();
      }

      public boolean copy$default$12() {
         return this.converged();
      }

      public DenseVector copy$default$2() {
         return this.u();
      }

      public DenseVector copy$default$3() {
         return this.z();
      }

      public DenseVector copy$default$4() {
         return this.scale();
      }

      public DenseMatrix copy$default$5() {
         return this.R();
      }

      public int[] copy$default$6() {
         return this.pivot();
      }

      public DenseVector copy$default$7() {
         return this.xHat();
      }

      public DenseVector copy$default$8() {
         return this.zOld();
      }

      public DenseVector copy$default$9() {
         return this.residual();
      }

      public String productPrefix() {
         return "State";
      }

      public int productArity() {
         return 12;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.x();
               break;
            case 1:
               var10000 = this.u();
               break;
            case 2:
               var10000 = this.z();
               break;
            case 3:
               var10000 = this.scale();
               break;
            case 4:
               var10000 = this.R();
               break;
            case 5:
               var10000 = this.pivot();
               break;
            case 6:
               var10000 = this.xHat();
               break;
            case 7:
               var10000 = this.zOld();
               break;
            case 8:
               var10000 = this.residual();
               break;
            case 9:
               var10000 = this.s();
               break;
            case 10:
               var10000 = BoxesRunTime.boxToInteger(this.iter());
               break;
            case 11:
               var10000 = BoxesRunTime.boxToBoolean(this.converged());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof State;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "x";
               break;
            case 1:
               var10000 = "u";
               break;
            case 2:
               var10000 = "z";
               break;
            case 3:
               var10000 = "scale";
               break;
            case 4:
               var10000 = "R";
               break;
            case 5:
               var10000 = "pivot";
               break;
            case 6:
               var10000 = "xHat";
               break;
            case 7:
               var10000 = "zOld";
               break;
            case 8:
               var10000 = "residual";
               break;
            case 9:
               var10000 = "s";
               break;
            case 10:
               var10000 = "iter";
               break;
            case 11:
               var10000 = "converged";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.x()));
         var1 = Statics.mix(var1, Statics.anyHash(this.u()));
         var1 = Statics.mix(var1, Statics.anyHash(this.z()));
         var1 = Statics.mix(var1, Statics.anyHash(this.scale()));
         var1 = Statics.mix(var1, Statics.anyHash(this.R()));
         var1 = Statics.mix(var1, Statics.anyHash(this.pivot()));
         var1 = Statics.mix(var1, Statics.anyHash(this.xHat()));
         var1 = Statics.mix(var1, Statics.anyHash(this.zOld()));
         var1 = Statics.mix(var1, Statics.anyHash(this.residual()));
         var1 = Statics.mix(var1, Statics.anyHash(this.s()));
         var1 = Statics.mix(var1, this.iter());
         var1 = Statics.mix(var1, this.converged() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 12);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var23;
         if (this != x$1) {
            label138: {
               boolean var2;
               if (x$1 instanceof State && ((State)x$1).breeze$optimize$proximal$QuadraticMinimizer$State$$$outer() == this.breeze$optimize$proximal$QuadraticMinimizer$State$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label113: {
                     State var4 = (State)x$1;
                     if (this.iter() == var4.iter() && this.converged() == var4.converged()) {
                        label127: {
                           DenseVector var10000 = this.x();
                           DenseVector var5 = var4.x();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label127;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label127;
                           }

                           var10000 = this.u();
                           DenseVector var6 = var4.u();
                           if (var10000 == null) {
                              if (var6 != null) {
                                 break label127;
                              }
                           } else if (!var10000.equals(var6)) {
                              break label127;
                           }

                           var10000 = this.z();
                           DenseVector var7 = var4.z();
                           if (var10000 == null) {
                              if (var7 != null) {
                                 break label127;
                              }
                           } else if (!var10000.equals(var7)) {
                              break label127;
                           }

                           var10000 = this.scale();
                           DenseVector var8 = var4.scale();
                           if (var10000 == null) {
                              if (var8 != null) {
                                 break label127;
                              }
                           } else if (!var10000.equals(var8)) {
                              break label127;
                           }

                           DenseMatrix var17 = this.R();
                           DenseMatrix var9 = var4.R();
                           if (var17 == null) {
                              if (var9 != null) {
                                 break label127;
                              }
                           } else if (!var17.equals(var9)) {
                              break label127;
                           }

                           if (this.pivot() == var4.pivot()) {
                              label128: {
                                 DenseVector var18 = this.xHat();
                                 DenseVector var10 = var4.xHat();
                                 if (var18 == null) {
                                    if (var10 != null) {
                                       break label128;
                                    }
                                 } else if (!var18.equals(var10)) {
                                    break label128;
                                 }

                                 var18 = this.zOld();
                                 DenseVector var11 = var4.zOld();
                                 if (var18 == null) {
                                    if (var11 != null) {
                                       break label128;
                                    }
                                 } else if (!var18.equals(var11)) {
                                    break label128;
                                 }

                                 var18 = this.residual();
                                 DenseVector var12 = var4.residual();
                                 if (var18 == null) {
                                    if (var12 != null) {
                                       break label128;
                                    }
                                 } else if (!var18.equals(var12)) {
                                    break label128;
                                 }

                                 var18 = this.s();
                                 DenseVector var13 = var4.s();
                                 if (var18 == null) {
                                    if (var13 != null) {
                                       break label128;
                                    }
                                 } else if (!var18.equals(var13)) {
                                    break label128;
                                 }

                                 if (var4.canEqual(this)) {
                                    var23 = true;
                                    break label113;
                                 }
                              }
                           }
                        }
                     }

                     var23 = false;
                  }

                  if (var23) {
                     break label138;
                  }
               }

               var23 = false;
               return var23;
            }
         }

         var23 = true;
         return var23;
      }

      // $FF: synthetic method
      public QuadraticMinimizer breeze$optimize$proximal$QuadraticMinimizer$State$$$outer() {
         return this.$outer;
      }

      public State(final DenseVector x, final DenseVector u, final DenseVector z, final DenseVector scale, final DenseMatrix R, final int[] pivot, final DenseVector xHat, final DenseVector zOld, final DenseVector residual, final DenseVector s, final int iter, final boolean converged) {
         this.x = x;
         this.u = u;
         this.z = z;
         this.scale = scale;
         this.R = R;
         this.pivot = pivot;
         this.xHat = xHat;
         this.zOld = zOld;
         this.residual = residual;
         this.s = s;
         this.iter = iter;
         this.converged = converged;
         if (QuadraticMinimizer.this == null) {
            throw null;
         } else {
            this.$outer = QuadraticMinimizer.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class State$ extends AbstractFunction12 implements Serializable {
      // $FF: synthetic field
      private final QuadraticMinimizer $outer;

      public final String toString() {
         return "State";
      }

      public State apply(final DenseVector x, final DenseVector u, final DenseVector z, final DenseVector scale, final DenseMatrix R, final int[] pivot, final DenseVector xHat, final DenseVector zOld, final DenseVector residual, final DenseVector s, final int iter, final boolean converged) {
         return this.$outer.new State(x, u, z, scale, R, pivot, xHat, zOld, residual, s, iter, converged);
      }

      public Option unapply(final State x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple12(x$0.x(), x$0.u(), x$0.z(), x$0.scale(), x$0.R(), x$0.pivot(), x$0.xHat(), x$0.zOld(), x$0.residual(), x$0.s(), BoxesRunTime.boxToInteger(x$0.iter()), BoxesRunTime.boxToBoolean(x$0.converged()))));
      }

      public State$() {
         if (QuadraticMinimizer.this == null) {
            throw null;
         } else {
            this.$outer = QuadraticMinimizer.this;
            super();
         }
      }
   }

   public static class Cost implements DiffFunction, Product, Serializable {
      private final DenseMatrix H;
      private final DenseVector q;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public DiffFunction repr() {
         return DiffFunction.repr$(this);
      }

      public DiffFunction cached(final CanCopy copy) {
         return DiffFunction.cached$(this, copy);
      }

      public DiffFunction throughLens(final Isomorphism l) {
         return DiffFunction.throughLens$(this, l);
      }

      public Object gradientAt(final Object x) {
         return StochasticDiffFunction.gradientAt$(this, x);
      }

      public double valueAt(final Object x) {
         return StochasticDiffFunction.valueAt$(this, x);
      }

      public final double apply(final Object x) {
         return StochasticDiffFunction.apply$(this, x);
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

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public DenseMatrix H() {
         return this.H;
      }

      public DenseVector q() {
         return this.q;
      }

      public Tuple2 calculate(final DenseVector x) {
         return new Tuple2(BoxesRunTime.boxToDouble(QuadraticMinimizer$.MODULE$.computeObjective(this.H(), this.q(), x)), ((NumericOps)this.H().$times(x, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$plus(this.q(), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double()));
      }

      public Cost copy(final DenseMatrix H, final DenseVector q) {
         return new Cost(H, q);
      }

      public DenseMatrix copy$default$1() {
         return this.H();
      }

      public DenseVector copy$default$2() {
         return this.q();
      }

      public String productPrefix() {
         return "Cost";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.H();
               break;
            case 1:
               var10000 = this.q();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Cost;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "H";
               break;
            case 1:
               var10000 = "q";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var9;
         if (this != x$1) {
            label63: {
               boolean var2;
               if (x$1 instanceof Cost) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label45: {
                     label54: {
                        Cost var4 = (Cost)x$1;
                        DenseMatrix var10000 = this.H();
                        DenseMatrix var5 = var4.H();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label54;
                        }

                        DenseVector var7 = this.q();
                        DenseVector var6 = var4.q();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label54;
                           }
                        } else if (!var7.equals(var6)) {
                           break label54;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label45;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label63;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      public Cost(final DenseMatrix H, final DenseVector q) {
         this.H = H;
         this.q = q;
         Function1.$init$(this);
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         StochasticDiffFunction.$init$(this);
         DiffFunction.$init$(this);
         Product.$init$(this);
      }
   }

   public static class Cost$ extends AbstractFunction2 implements Serializable {
      public static final Cost$ MODULE$ = new Cost$();

      public final String toString() {
         return "Cost";
      }

      public Cost apply(final DenseMatrix H, final DenseVector q) {
         return new Cost(H, q);
      }

      public Option unapply(final Cost x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.H(), x$0.q())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Cost$.class);
      }
   }
}
