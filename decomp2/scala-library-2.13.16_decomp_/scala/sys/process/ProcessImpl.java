package scala.sys.process;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.LinkedBlockingQueue;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;
import scala.runtime.java8.JFunction0$mcV$sp;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\rMeAC:u!\u0003\r\t\u0001\u001e>\u0004\u000e\"1q\u0010\u0001C\u0001\u0003\u00079\u0001\"a\u0003\u0001\u0011\u0003!\u0018Q\u0002\u0004\t\u0003#\u0001\u0001\u0012\u0001;\u0002\u0014!9\u0011QC\u0002\u0005\u0002\u0005]\u0001bBA\r\u0007\u0011\u0005\u00111\u0004\u0005\n\u0003;\u001a\u0011\u0013!C\u0001\u0003?:\u0001\"!\u001e\u0001\u0011\u0003!\u0018q\u000f\u0004\t\u0003s\u0002\u0001\u0012\u0001;\u0002|!9\u0011Q\u0003\u0005\u0005\u0002\u0005u\u0004bBA\r\u0011\u0011\u0005\u0011q\u0010\u0004\b\u0003S\u0003\u0001\u0001^AV\u0011)\u0011Yb\u0003B\u0001B\u0003%!Q\u0004\u0005\u000b\u0005GY!\u0011!Q\u0001\n\tu\u0001B\u0003B\u0013\u0017\t\u0005\t\u0015!\u0003\u0003(!9\u0011QC\u0006\u0005\u0002\t}ba\u0002B%\u0001\u0001!(1\n\u0005\u000b\u00057\u0001\"\u0011!Q\u0001\n\tu\u0001B\u0003B\u0012!\t\u0005\t\u0015!\u0003\u0003\u001e!Q!Q\u0005\t\u0003\u0002\u0003\u0006IAa\n\t\u000f\u0005U\u0001\u0003\"\u0001\u0003N\u00199!q\u000b\u0001\u0001i\ne\u0003B\u0003B\u000e+\t\u0005\t\u0015!\u0003\u0003\u001e!Q!1E\u000b\u0003\u0002\u0003\u0006IA!\b\t\u0015\t\u0015RC!A!\u0002\u0013\u00119\u0003C\u0004\u0002\u0016U!\tAa\u0017\u0007\u000f\u0005=\u0006\u0001\u0001;\u00022\"Q!1\u0004\u000e\u0003\u0002\u0003\u0006IA!\b\t\u0015\t\r\"D!A!\u0002\u0013\u0011i\u0002\u0003\u0006\u0003&i\u0011\t\u0011)A\u0005\u0005OA!B!\f\u001b\u0005\u0003\u0005\u000b\u0011\u0002B\u0018\u0011\u001d\t)B\u0007C\u0001\u0005kA\u0001\"a@\u001bA\u0013E#\u0011\u0001\u0004\t\u0003w\u0003\u0011\u0011\u0001;\u0002>\"9\u0011QC\u0011\u0005\u0002\u0005\u001d\u0007bBAeC\u0019\u0005\u00111\u0001\u0004\t\u0003k\u0003\u0011\u0011\u0001;\u00028\"9\u0011Q\u0003\u0013\u0005\u0002\u0005-\u0007bBAgI\u0011\u0005\u0011q\u001a\u0005\b\u0003#$C\u0011AA\u0002\u0011\u001d\t\u0019\u000e\nC\u0001\u0003+Dq!!3%\t\u0003\t\u0019\u0001\u0003\u0007\u0002^\u0012\u0002\n\u0011cb!\n\u0013\ty\u000e\u0003\u0006\u0002r\u0012B)\u0019!C\t\u0003gD!\"!>%\u0011\u000b\u0007I\u0011CAz\u0011)\t9\u0010\nEC\u0002\u0013E\u0011\u0011 \u0005\u000b\u0003w$\u0003R1A\u0005\u0012\u0005u\b\u0002CA\u0000I\u00016\tB!\u0001\t\u0011\t\rA\u0005)C\t\u0005\u000b1qA!\u001a\u0001\u0001Q\u00149\u0007\u0003\u0006\u0003\u001cE\u0012\t\u0011)A\u0005\u0005;A!Ba\t2\u0005\u0003\u0005\u000b\u0011\u0002B\u000f\u0011)\u0011I'\rB\u0001B\u0003%!q\u0005\u0005\u000b\u0005W\n$\u0011!Q\u0001\n\u0005]\u0003bBA\u000bc\u0011\u0005!Q\u000e\u0005\b\u0005s\nD\u0011\u0003B>\u0011\u001d\u0019y#\rC\t\u0007cA\u0001\"a@2A\u0013E#\u0011\u0001\u0005\t\u0003\u007f\f\u0004\u0015\"\u0005\u00044\u0019A!Q\u0011\u0001\u0002\u0002Q\u00149\t\u0003\u0006\u0003\nn\u0012\t\u0011)A\u0005\u0003/B!Ba#<\u0005\u0003\u0005\u000b\u0011\u0002BG\u0011\u001d\t)b\u000fC\u0001\u0005\u001fCqA!&<\r\u0003\t\u0019\u0001\u0003\u0005\u0003\u0018n\"\t\u0001\u001eBM\u0011\u001d\u00119l\u000fC\u0005\u0005s3qAa \u0001\u0001Q\u0014\t\t\u0003\u0006\u0003F\n\u0013\t\u0011*A\u0005\u0005\u000fDq!!\u0006C\t\u0003\u0011I\rC\u0005\u0003N\n\u0013\r\u0015\"\u0005\u0003P\"A!1\u001c\"!\u0002\u0013\u0011\t\u000eC\u0005\u0003^\n\u0013\r\u0015\"\u0005\u0003`\"A!1\u001f\"!\u0002\u0013\u0011\t\u000fC\u0004\u0003\u0016\n#)%a\u0001\t\u000f\tU(\t\"\u0001\u0003x\"9!Q \"\u0005\u0002\t}\bbBB\u0016\u0005\u0012\u0005\u00111\u0001\u0005\b\u0007[\u0011E\u0011AA\u0002\r\u001d\u00199\u0001\u0001\u0001u\u0007\u0013A!B!2O\u0005\u0003%\u000b\u0011\u0002Bd\u0011\u001d\t)B\u0014C\u0001\u0007\u0017A\u0011B!4O\u0005\u0004&\tba\u0004\t\u0011\tmg\n)A\u0005\u0007#A\u0011ba\u0001O\u0005\u0004&\tba\u0006\t\u0011\rua\n)A\u0005\u00073AqA!&O\t\u0003\n\u0019\u0001C\u0004\u0003~:#\taa\b\t\u000f\tUh\n\"\u0001\u0004&!911\u0006(\u0005\u0002\u0005\r\u0001bBB\u0017\u001d\u0012\u0005\u00111\u0001\u0004\b\u0007s\u0001\u0001\u0001^B\u001e\u0011)\u00119B\u0017B\u0001J\u0003%1Q\b\u0005\b\u0003+QF\u0011AB \u00111\u0019)E\u0017I\u0001\u0002\u0007\u0005\u000b\u0011BB$\u0011!\u0019YE\u0017Q\u0001\n\u0005}\u0001\u0002CB'5\u0002\u0006Ia!\u0013\t\u000f\u00055'\f\"\u0011\u0002P\"9\u00111\u001b.\u0005B\u0005U\u0007bBAi5\u0012\u0005\u00131\u0001\u0004\b\u0007\u001f\u0002\u0001\u0001^B)\u0011)\u0019\u0019f\u0019B\u0001B\u0003%1Q\u000b\u0005\u000b\u00077\u001a'\u0011!Q\u0001\n\u0005}\u0001BCB/G\n\u0005\t\u0015!\u0003\u0004`!9\u0011QC2\u0005\u0002\rE\u0004bBAgG\u0012\u0005\u0013q\u001a\u0005\b\u0003'\u001cG\u0011IAk\u0011\u001d\t\tn\u0019C!\u0003\u0007A\u0001ba\u001fdA\u0013%\u00111\u0001\u0004\b\u0007{\u0002!\u0001^B@\u0011)\u0019Y\u0005\u001cB\u0001B\u0003%\u0011q\u0004\u0005\u000b\u0007\u0003c'\u0011!Q\u0001\n\r\r\u0005bBA\u000bY\u0012\u00051Q\u0011\u0005\b\u0003\u001bdG\u0011IAh\u0011\u001d\t\u0019\u000e\u001cC!\u0003+Dq!!5m\t\u0003\n\u0019AA\u0006Qe>\u001cWm]:J[Bd'BA;w\u0003\u001d\u0001(o\\2fgNT!a\u001e=\u0002\u0007ML8OC\u0001z\u0003\u0015\u00198-\u00197b'\t\u00011\u0010\u0005\u0002}{6\t\u00010\u0003\u0002\u007fq\n1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0003\u0003\u000b\u00012\u0001`A\u0004\u0013\r\tI\u0001\u001f\u0002\u0005+:LG/A\u0003Ta\u0006<h\u000eE\u0002\u0002\u0010\ri\u0011\u0001\u0001\u0002\u0006'B\fwO\\\n\u0003\u0007m\fa\u0001P5oSRtDCAA\u0007\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\ti\"!\u000f\u0002TQ!\u0011qDA\u0018!\u0011\t\t#a\u000b\u000e\u0005\u0005\r\"\u0002BA\u0013\u0003O\tA\u0001\\1oO*\u0011\u0011\u0011F\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002.\u0005\r\"A\u0002+ie\u0016\fG\r\u0003\u0005\u00022\u0015!\t\u0019AA\u001a\u0003\u00051\u0007#\u0002?\u00026\u0005\u0015\u0011bAA\u001cq\nAAHY=oC6,g\bC\u0004\u0002<\u0015\u0001\r!!\u0010\u0002\rA\u0014XMZ5y!\u0011\ty$!\u0014\u000f\t\u0005\u0005\u0013\u0011\n\t\u0004\u0003\u0007BXBAA#\u0015\u0011\t9%!\u0001\u0002\rq\u0012xn\u001c;?\u0013\r\tY\u0005_\u0001\u0007!J,G-\u001a4\n\t\u0005=\u0013\u0011\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005-\u0003\u0010C\u0005\u0002V\u0015\u0001\n\u00111\u0001\u0002X\u00051A-Y3n_:\u00042\u0001`A-\u0013\r\tY\u0006\u001f\u0002\b\u0005>|G.Z1o\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012TCAA1U\u0011\t9&a\u0019,\u0005\u0005\u0015\u0004\u0003BA4\u0003cj!!!\u001b\u000b\t\u0005-\u0014QN\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u001cy\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003g\nIGA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\faAR;ukJ,\u0007cAA\b\u0011\t1a)\u001e;ve\u0016\u001c\"\u0001C>\u0015\u0005\u0005]T\u0003BAA\u0003'#B!a!\u0002&B9A0!\"\u0002 \u0005%\u0015bAADq\n1A+\u001e9mKJ\u0002R\u0001`AF\u0003\u001fK1!!$y\u0005%1UO\\2uS>t\u0007\u0007\u0005\u0003\u0002\u0012\u0006ME\u0002\u0001\u0003\b\u0003+S!\u0019AAL\u0005\u0005!\u0016\u0003BAM\u0003?\u00032\u0001`AN\u0013\r\ti\n\u001f\u0002\b\u001d>$\b.\u001b8h!\ra\u0018\u0011U\u0005\u0004\u0003GC(aA!os\"A\u0011\u0011\u0007\u0006\u0005\u0002\u0004\t9\u000bE\u0003}\u0003k\tyI\u0001\u0006B]\u0012\u0004&o\\2fgN\u001c2aCAW!\r\tyA\u0007\u0002\u0012'\u0016\fX/\u001a8uS\u0006d\u0007K]8dKN\u001c8c\u0001\u000e\u00024B\u0019\u0011q\u0002\u0013\u0003\u001f\r{W\u000e]8v]\u0012\u0004&o\\2fgN\u001c2\u0001JA]!\r\ty!\t\u0002\r\u0005\u0006\u001c\u0018n\u0019)s_\u000e,7o]\n\u0005Cm\fy\f\u0005\u0003\u0002B\u0006\rW\"\u0001;\n\u0007\u0005\u0015GOA\u0004Qe>\u001cWm]:\u0015\u0005\u0005e\u0016!B:uCJ$HCAAZ\u0003\u001dI7/\u00117jm\u0016$\"!a\u0016\u0002\u000f\u0011,7\u000f\u001e:ps\u0006IQ\r_5u-\u0006dW/\u001a\u000b\u0003\u0003/\u00042\u0001`Am\u0013\r\tY\u000e\u001f\u0002\u0004\u0013:$\u0018a\u0001=%iU\u0011\u0011\u0011\u001d\t\fy\u0006\r\u0018qDA\u0010\u0003O\fy/C\u0002\u0002fb\u0014a\u0001V;qY\u0016$\u0004#\u0002?\u0002\f\u0006%\b#\u0002?\u0002l\u0006]\u0017bAAwq\n1q\n\u001d;j_:\u0004R\u0001`AF\u0003\u000b\tQ\u0002\u001d:pG\u0016\u001c8\u000f\u00165sK\u0006$WCAA\u0010\u000311W\u000f^;sKRC'/Z1e\u0003-1W\u000f^;sKZ\u000bG.^3\u0016\u0005\u0005\u001d\u0018!\u00033fgR\u0014x._3s+\t\ty/A\bsk:\fe\u000eZ#ySR4\u0016\r\\;f)\t\tI/\u0001\tsk:Le\u000e^3seV\u0004H/\u001b2mKV!!q\u0001B\b)\u0011\u0011IA!\u0006\u0015\t\t-!\u0011\u0003\t\u0006y\u0006-(Q\u0002\t\u0005\u0003#\u0013y\u0001B\u0004\u0002\u0016B\u0012\r!a&\t\u0011\tM\u0001\u0007\"a\u0001\u0003g\t1\u0002Z3tiJ|\u00170S7qY\"A!q\u0003\u0019\u0005\u0002\u0004\u0011I\"\u0001\u0004bGRLwN\u001c\t\u0006y\u0006U\"QB\u0001\u0002CB!\u0011\u0011\u0019B\u0010\u0013\r\u0011\t\u0003\u001e\u0002\u000f!J|7-Z:t\u0005VLG\u000eZ3s\u0003\u0005\u0011\u0017AA5p!\u0011\t\tM!\u000b\n\u0007\t-BOA\u0005Qe>\u001cWm]:J\u001f\u0006)RM^1mk\u0006$XmU3d_:$\u0007K]8dKN\u001c\bc\u0002?\u00032\u0005]\u0017qK\u0005\u0004\u0005gA(!\u0003$v]\u000e$\u0018n\u001c82))\tiKa\u000e\u0003:\tm\"Q\b\u0005\b\u00057y\u0002\u0019\u0001B\u000f\u0011\u001d\u0011\u0019c\ba\u0001\u0005;AqA!\n \u0001\u0004\u00119\u0003C\u0004\u0003.}\u0001\rAa\f\u0015\u0011\t\u0005#1\tB#\u0005\u000f\u00022!a\u0004\f\u0011\u001d\u0011Yb\u0004a\u0001\u0005;AqAa\t\u0010\u0001\u0004\u0011i\u0002C\u0004\u0003&=\u0001\rAa\n\u0003\u0013=\u0013\bK]8dKN\u001c8c\u0001\t\u0002.RA!q\nB)\u0005'\u0012)\u0006E\u0002\u0002\u0010AAqAa\u0007\u0015\u0001\u0004\u0011i\u0002C\u0004\u0003$Q\u0001\rA!\b\t\u000f\t\u0015B\u00031\u0001\u0003(\ty\u0001K]8dKN\u001c8+Z9vK:\u001cWmE\u0002\u0016\u0003[#\u0002B!\u0018\u0003`\t\u0005$1\r\t\u0004\u0003\u001f)\u0002b\u0002B\u000e3\u0001\u0007!Q\u0004\u0005\b\u0005GI\u0002\u0019\u0001B\u000f\u0011\u001d\u0011)#\u0007a\u0001\u0005O\u0011a\u0002U5qK\u0012\u0004&o\\2fgN,7oE\u00022\u0003g\u000b\u0011\u0002Z3gCVdG/S(\u0002\u000fQ|WI\u001d:peRQ!q\u000eB9\u0005g\u0012)Ha\u001e\u0011\u0007\u0005=\u0011\u0007C\u0004\u0003\u001cY\u0002\rA!\b\t\u000f\t\rb\u00071\u0001\u0003\u001e!9!\u0011\u000e\u001cA\u0002\t\u001d\u0002b\u0002B6m\u0001\u0007\u0011qK\u0001\n]\u0016<8k\\;sG\u0016,\"A! \u0011\u0007\u0005=!I\u0001\u0006QSB,7k\\;sG\u0016\u001c2A\u0011BB!\r\tya\u000f\u0002\u000b!&\u0004X\r\u00165sK\u0006$7cA\u001e\u0002 \u00051\u0011n]*j].\fq\u0001\\1cK24e\u000eE\u0003}\u0003\u0017\u000bi\u0004\u0006\u0004\u0003\u0004\nE%1\u0013\u0005\b\u0005\u0013s\u0004\u0019AA,\u0011\u001d\u0011YI\u0010a\u0001\u0005\u001b\u000b1A];o\u0003\u001d\u0011XO\u001c7p_B$b!!\u0002\u0003\u001c\n5\u0006b\u0002BO\u0001\u0002\u0007!qT\u0001\u0004gJ\u001c\u0007\u0003\u0002BQ\u0005OsA!!1\u0003$&\u0019!Q\u0015;\u0002\u001fA\u0014xnY3tg&sG/\u001a:oC2LAA!+\u0003,\nY\u0011J\u001c9viN#(/Z1n\u0015\r\u0011)\u000b\u001e\u0005\b\u0005_\u0003\u0005\u0019\u0001BY\u0003\r!7\u000f\u001e\t\u0005\u0005C\u0013\u0019,\u0003\u0003\u00036\n-&\u0001D(viB,Ho\u0015;sK\u0006l\u0017!C5p\u0011\u0006tG\r\\3s)\u0011\t)Aa/\t\u000f\tu\u0016\t1\u0001\u0003@\u0006\tQ\r\u0005\u0003\u0003\"\n\u0005\u0017\u0002\u0002Bb\u0005W\u00131\"S(Fq\u000e,\u0007\u000f^5p]\u0006)A.\u00192fYB)A0!\u000e\u0002>Q!!Q\u0010Bf\u0011!\u0011)\r\u0012CA\u0002\t\u001d\u0017\u0001\u00029ja\u0016,\"A!5\u0011\t\tM'q[\u0007\u0003\u0005+TAA!\n\u0002(%!!\u0011\u001cBk\u0005E\u0001\u0016\u000e]3e\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\u0006a&\u0004X\rI\u0001\u0007g>,(oY3\u0016\u0005\t\u0005\bC\u0002Br\u0005[\u0014\t0\u0004\u0002\u0003f*!!q\u001dBu\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0005\u0005W\f9#\u0001\u0003vi&d\u0017\u0002\u0002Bx\u0005K\u00141\u0003T5oW\u0016$'\t\\8dW&tw-U;fk\u0016\u0004R\u0001`Av\u0005?\u000bqa]8ve\u000e,\u0007%A\u0005d_:tWm\u0019;J]R!\u0011Q\u0001B}\u0011\u001d\u0011YP\u0013a\u0001\u0005?\u000b!!\u001b8\u0002\u0015\r|gN\\3di>+H\u000f\u0006\u0003\u0002\u0006\r\u0005\u0001bBB\u0002\u0017\u0002\u00071QA\u0001\u0005g&t7\u000eE\u0002\u0002\u00109\u0013\u0001\u0002U5qKNKgn[\n\u0004\u001d\n\rE\u0003BB\u0003\u0007\u001bA\u0001B!2Q\t\u0003\u0007!qY\u000b\u0003\u0007#\u0001BAa5\u0004\u0014%!1Q\u0003Bk\u0005A\u0001\u0016\u000e]3e\u0013:\u0004X\u000f^*ue\u0016\fW.\u0006\u0002\u0004\u001aA1!1\u001dBw\u00077\u0001R\u0001`Av\u0005c\u000bQa]5oW\u0002\"B!!\u0002\u0004\"!911\u0005,A\u0002\tE\u0016aA8viR!\u0011QAB\u0014\u0011\u001d\u0019Ic\u0016a\u0001\u0005#\fq\u0001]5qK>+H/A\u0004sK2,\u0017m]3\u0002\t\u0011|g.Z\u0001\b]\u0016<8+\u001b8l+\t\u0019)\u0001\u0006\u0004\u0002j\u000eU2q\u0007\u0005\b\u0005;T\u0004\u0019\u0001B?\u0011\u001d\u0019\u0019A\u000fa\u0001\u0007\u000b\u0011A\u0002R;n[f\u0004&o\\2fgN\u001cBAW>\u0002@B)A0!\u000e\u0002XR!1\u0011IB\"!\r\tyA\u0017\u0005\t\u0005/aF\u00111\u0001\u0004>\u0005\u0019\u0001\u0010\n\u001c\u0011\u000fq\f))a\b\u0004JA)A0a#\u0002X\u00061A\u000f\u001b:fC\u0012\fQA^1mk\u0016\u0014QbU5na2,\u0007K]8dKN\u001c8\u0003B2|\u0003\u007f\u000b\u0011\u0001\u001d\t\u0005\u0005C\u001b9&\u0003\u0003\u0004Z\t-&\u0001\u0003&Qe>\u001cWm]:\u0002\u0017%t\u0007/\u001e;UQJ,\u0017\rZ\u0001\u000e_V$\b/\u001e;UQJ,\u0017\rZ:\u0011\r\r\u000541NA\u0010\u001d\u0011\u0019\u0019ga\u001a\u000f\t\u0005\r3QM\u0005\u0002s&\u00191\u0011\u000e=\u0002\u000fA\f7m[1hK&!1QNB8\u0005\u0011a\u0015n\u001d;\u000b\u0007\r%\u0004\u0010\u0006\u0005\u0004t\rU4qOB=!\r\tya\u0019\u0005\b\u0007':\u0007\u0019AB+\u0011\u001d\u0019Yf\u001aa\u0001\u0003?Aqa!\u0018h\u0001\u0004\u0019y&A\u0005j]R,'O];qi\niA\u000b\u001b:fC\u0012\u0004&o\\2fgN\u001cB\u0001\\>\u0002@\u000691/^2dKN\u001c\bC\u0002Br\u0005[\f9\u0006\u0006\u0004\u0004\b\u000e%51\u0012\t\u0004\u0003\u001fa\u0007bBB&_\u0002\u0007\u0011q\u0004\u0005\b\u0007\u0003{\u0007\u0019ABB\u001d\u0011\t\tma$\n\u0007\rEE/A\u0004Qe>\u001cWm]:"
)
public interface ProcessImpl {
   Spawn$ Spawn();

   Future$ Future();

   static void $init$(final ProcessImpl $this) {
   }

   public class Spawn$ {
      public Thread apply(final String prefix, final boolean daemon, final Function0 f) {
         Thread thread = new Thread(f) {
            private final Function0 f$1;

            public void run() {
               this.f$1.apply$mcV$sp();
            }

            public {
               this.f$1 = f$1;
            }
         };
         thread.setName((new StringBuilder(7)).append(prefix).append("-spawn-").append(thread.getName()).toString());
         thread.setDaemon(daemon);
         thread.start();
         return thread;
      }

      public boolean apply$default$2() {
         return false;
      }
   }

   public class Future$ {
      // $FF: synthetic field
      private final Process$ $outer;

      public Tuple2 apply(final Function0 f) {
         LinkedBlockingQueue result = new LinkedBlockingQueue(1);
         Spawn$ var10000 = this.$outer.Spawn();
         if (this.$outer.Spawn() == null) {
            throw null;
         } else {
            JFunction0$mcV$sp apply_f = () -> run$1(f, result);
            boolean apply_daemon = false;
            String apply_prefix = "Future";
            if (var10000 == null) {
               throw null;
            } else {
               Thread apply_thread = new Thread(apply_f) {
                  private final Function0 f$1;

                  public void run() {
                     this.f$1.apply$mcV$sp();
                  }

                  public {
                     this.f$1 = f$1;
                  }
               };
               apply_thread.setName((new StringBuilder(7)).append(apply_prefix).append("-spawn-").append(apply_thread.getName()).toString());
               apply_thread.setDaemon(apply_daemon);
               apply_thread.start();
               Thread var11 = apply_thread;
               Object var8 = null;
               apply_f = null;
               Object var10 = null;
               Thread t = var11;
               return new Tuple2(t, (Function0)() -> {
                  Either var1 = (Either)result.take();
                  if (var1 instanceof Right) {
                     return ((Right)var1).value();
                  } else if (var1 instanceof Left) {
                     throw (Throwable)((Left)var1).value();
                  } else {
                     throw new MatchError(var1);
                  }
               });
            }
         }
      }

      private static final void run$1(final Function0 f$2, final LinkedBlockingQueue result$1) {
         Object var10000;
         try {
            var10000 = new Right(f$2.apply());
         } catch (Exception var4) {
            var10000 = new Left(var4);
         }

         Either value = (Either)var10000;
         result$1.put(value);
      }

      public Future$() {
         if (ProcessImpl.this == null) {
            throw null;
         } else {
            this.$outer = ProcessImpl.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class AndProcess extends SequentialProcess {
      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$AndProcess$$$outer() {
         return this.$outer;
      }

      public AndProcess(final ProcessBuilder a, final ProcessBuilder b, final ProcessIO io) {
         super(a, b, io, new Serializable() {
            private static final long serialVersionUID = 0L;

            public final boolean apply(final int x$1) {
               return this.apply$mcZI$sp(x$1);
            }

            public final boolean apply$mcZI$sp(final int x$1) {
               return x$1 == 0;
            }
         });
      }
   }

   public class OrProcess extends SequentialProcess {
      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$OrProcess$$$outer() {
         return this.$outer;
      }

      public OrProcess(final ProcessBuilder a, final ProcessBuilder b, final ProcessIO io) {
         super(a, b, io, new Serializable() {
            private static final long serialVersionUID = 0L;

            public final boolean apply(final int x$2) {
               return this.apply$mcZI$sp(x$2);
            }

            public final boolean apply$mcZI$sp(final int x$2) {
               return x$2 != 0;
            }
         });
      }
   }

   public class ProcessSequence extends SequentialProcess {
      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$ProcessSequence$$$outer() {
         return this.$outer;
      }

      public ProcessSequence(final ProcessBuilder a, final ProcessBuilder b, final ProcessIO io) {
         super(a, b, io, new Serializable() {
            private static final long serialVersionUID = 0L;

            public final boolean apply(final int x$3) {
               return true;
            }

            public final boolean apply$mcZI$sp(final int x$3) {
               return true;
            }
         });
      }
   }

   public class SequentialProcess extends CompoundProcess {
      private final ProcessBuilder a;
      private final ProcessBuilder b;
      private final ProcessIO io;
      private final Function1 evaluateSecondProcess;

      public Option runAndExitValue() {
         Process first = this.a.run(this.io);
         Option var10000 = this.runInterruptible(() -> first.exitValue(), () -> first.destroy());
         if (var10000 == null) {
            throw null;
         } else {
            Option flatMap_this = var10000;
            if (flatMap_this.isEmpty()) {
               return None$.MODULE$;
            } else {
               Object var3 = flatMap_this.get();
               return $anonfun$runAndExitValue$3(this, BoxesRunTime.unboxToInt(var3));
            }
         }
      }

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$SequentialProcess$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Option $anonfun$runAndExitValue$3(final SequentialProcess $this, final int codeA) {
         if ($this.evaluateSecondProcess.apply$mcZI$sp(codeA)) {
            Process second = $this.b.run($this.io);
            return $this.runInterruptible(() -> second.exitValue(), () -> second.destroy());
         } else {
            return new Some(codeA);
         }
      }

      public SequentialProcess(final ProcessBuilder a, final ProcessBuilder b, final ProcessIO io, final Function1 evaluateSecondProcess) {
         this.a = a;
         this.b = b;
         this.io = io;
         this.evaluateSecondProcess = evaluateSecondProcess;
      }

      // $FF: synthetic method
      public static final Option $anonfun$runAndExitValue$3$adapted(final SequentialProcess $this, final Object codeA) {
         return $anonfun$runAndExitValue$3($this, BoxesRunTime.unboxToInt(codeA));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public abstract class BasicProcess implements Process {
      // $FF: synthetic field
      public final Process$ $outer;

      public abstract void start();

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$BasicProcess$$$outer() {
         return this.$outer;
      }

      public BasicProcess() {
         if (ProcessImpl.this == null) {
            throw null;
         } else {
            this.$outer = ProcessImpl.this;
            super();
         }
      }
   }

   public abstract class CompoundProcess extends BasicProcess {
      private Tuple4 x$4;
      private Thread processThread;
      private Thread futureThread;
      private Function0 futureValue;
      private Function0 destroyer;
      private volatile byte bitmap$0;

      public boolean isAlive() {
         return this.processThread().isAlive();
      }

      public void destroy() {
         this.destroyer().apply$mcV$sp();
      }

      public int exitValue() {
         Option var10000 = (Option)this.futureValue().apply();
         if (var10000 == null) {
            throw null;
         } else {
            Option getOrElse_this = var10000;
            if (getOrElse_this.isEmpty()) {
               scala.sys.package$ var5 = scala.sys.package$.MODULE$;
               String error_message = "No exit code: process destroyed.";
               throw new RuntimeException(error_message);
            } else {
               var10000 = (Option)getOrElse_this.get();
               Object var3 = null;
               return BoxesRunTime.unboxToInt(var10000);
            }
         }
      }

      public void start() {
         this.futureThread();
      }

      private Tuple4 x$4$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               LinkedBlockingQueue code = new LinkedBlockingQueue(1);
               Spawn$ var10001 = this.scala$sys$process$ProcessImpl$CompoundProcess$$$outer().Spawn();
               if (this.scala$sys$process$ProcessImpl$CompoundProcess$$$outer().Spawn() == null) {
                  throw null;
               }

               JFunction0$mcV$sp apply_f = () -> {
                  Option value = None$.MODULE$;

                  try {
                     value = this.runAndExitValue();
                  } catch (Throwable var7) {
                     if (!(var7 instanceof IndexOutOfBoundsException ? true : (var7 instanceof IOException ? true : (var7 instanceof NullPointerException ? true : (var7 instanceof SecurityException ? true : var7 instanceof UnsupportedOperationException))))) {
                        throw var7;
                     }

                     value = new Some(-1);
                  } finally {
                     code.put(value);
                  }

               };
               boolean apply_daemon = false;
               String apply_prefix = "CompoundProcess";
               if (var10001 == null) {
                  throw null;
               }

               Thread apply_thread = new Thread(apply_f) {
                  private final Function0 f$1;

                  public void run() {
                     this.f$1.apply$mcV$sp();
                  }

                  public {
                     this.f$1 = f$1;
                  }
               };
               apply_thread.setName((new StringBuilder(7)).append(apply_prefix).append("-spawn-").append(apply_thread.getName()).toString());
               apply_thread.setDaemon(apply_daemon);
               apply_thread.start();
               Thread var17 = apply_thread;
               Object var14 = null;
               apply_f = null;
               Object var16 = null;
               Thread thread = var17;
               Tuple2 var10003 = this.scala$sys$process$ProcessImpl$CompoundProcess$$$outer().Future().apply(() -> (Option)code.take());
               JFunction0$mcV$sp var11 = () -> thread.interrupt();
               Tuple2 var10 = var10003;
               Tuple3 var1 = new Tuple3(thread, var10, var11);
               if (var10 == null) {
                  throw new MatchError(var1);
               }

               Thread futureThread = (Thread)var10._1();
               Function0 futureValue = (Function0)var10._2();
               this.x$4 = new Tuple4(thread, futureThread, futureValue, var11);
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
         } catch (Throwable var13) {
            throw var13;
         }

         return this.x$4;
      }

      // $FF: synthetic method
      private Tuple4 x$4() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.x$4$lzycompute() : this.x$4;
      }

      private Thread processThread$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.processThread = (Thread)this.x$4()._1();
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.processThread;
      }

      public Thread processThread() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.processThread$lzycompute() : this.processThread;
      }

      private Thread futureThread$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 4) == 0) {
               this.futureThread = (Thread)this.x$4()._2();
               this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.futureThread;
      }

      public Thread futureThread() {
         return (byte)(this.bitmap$0 & 4) == 0 ? this.futureThread$lzycompute() : this.futureThread;
      }

      private Function0 futureValue$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 8) == 0) {
               this.futureValue = (Function0)this.x$4()._3();
               this.bitmap$0 = (byte)(this.bitmap$0 | 8);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.futureValue;
      }

      public Function0 futureValue() {
         return (byte)(this.bitmap$0 & 8) == 0 ? this.futureValue$lzycompute() : this.futureValue;
      }

      private Function0 destroyer$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 16) == 0) {
               this.destroyer = (Function0)this.x$4()._4();
               this.bitmap$0 = (byte)(this.bitmap$0 | 16);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.destroyer;
      }

      public Function0 destroyer() {
         return (byte)(this.bitmap$0 & 16) == 0 ? this.destroyer$lzycompute() : this.destroyer;
      }

      public abstract Option runAndExitValue();

      public Option runInterruptible(final Function0 action, final Function0 destroyImpl) {
         try {
            return new Some(action.apply());
         } catch (Throwable var6) {
            processInternal$ var10000 = processInternal$.MODULE$;
            Function0 onInterrupt_handler = () -> {
               destroyImpl.apply$mcV$sp();
               return None$.MODULE$;
            };
            Serializable var8 = new Serializable(onInterrupt_handler) {
               private static final long serialVersionUID = 0L;
               private final Function0 handler$3;

               public final Object applyOrElse(final Throwable x1, final Function1 default) {
                  return x1 instanceof InterruptedException ? this.handler$3.apply() : default.apply(x1);
               }

               public final boolean isDefinedAt(final Throwable x1) {
                  return x1 instanceof InterruptedException;
               }

               public {
                  this.handler$3 = handler$3;
               }
            };
            onInterrupt_handler = null;
            PartialFunction catchExpr$1 = var8;
            if (((<undefinedtype>)catchExpr$1).isDefinedAt(var6)) {
               return (Option)catchExpr$1.apply(var6);
            } else {
               throw var6;
            }
         }
      }

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$CompoundProcess$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Nothing$ $anonfun$exitValue$1() {
         return scala.sys.package$.MODULE$.error("No exit code: process destroyed.");
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class PipedProcesses extends CompoundProcess {
      private final ProcessBuilder a;
      private final ProcessBuilder b;
      private final ProcessIO defaultIO;
      private final boolean toError;

      public PipeSource newSource() {
         return this.scala$sys$process$ProcessImpl$PipedProcesses$$$outer().new PipeSource(() -> this.a.toString());
      }

      public PipeSink newSink() {
         return this.scala$sys$process$ProcessImpl$PipedProcesses$$$outer().new PipeSink(() -> this.b.toString());
      }

      public Option runAndExitValue() {
         return this.runAndExitValue(this.newSource(), this.newSink());
      }

      public Option runAndExitValue(final PipeSource source, final PipeSink sink) {
         source.connectOut(sink);
         source.start();
         sink.start();
         ProcessIO var27;
         if (this.toError) {
            var27 = this.defaultIO;
            Function1 withError_process = (in) -> {
               $anonfun$runAndExitValue$7(source, in);
               return BoxedUnit.UNIT;
            };
            if (var27 == null) {
               throw null;
            }

            ProcessIO withError_this = var27;
            var27 = new ProcessIO(withError_this.writeInput(), withError_this.processOutput(), withError_process, withError_this.daemonizeThreads());
            Object var19 = null;
            withError_process = null;
         } else {
            var27 = this.defaultIO;
            Function1 withOutput_process = (in) -> {
               $anonfun$runAndExitValue$8(source, in);
               return BoxedUnit.UNIT;
            };
            if (var27 == null) {
               throw null;
            }

            ProcessIO withOutput_this = var27;
            var27 = new ProcessIO(withOutput_this.writeInput(), withOutput_process, withOutput_this.processError(), withOutput_this.daemonizeThreads());
            Object var21 = null;
            withOutput_process = null;
         }

         ProcessIO firstIO = var27;
         var27 = this.defaultIO;
         Function1 withInput_write = (out) -> {
            $anonfun$runAndExitValue$9(sink, out);
            return BoxedUnit.UNIT;
         };
         if (var27 == null) {
            throw null;
         } else {
            ProcessIO withInput_this = var27;
            var27 = new ProcessIO(withInput_write, withInput_this.processOutput(), withInput_this.processError(), withInput_this.daemonizeThreads());
            Object var23 = null;
            withInput_write = null;
            ProcessIO secondIO = var27;

            try {
               var33 = this.b.run(secondIO);
            } catch (Throwable var18) {
               processInternal$ var31 = processInternal$.MODULE$;
               Function1 onError_handler = (err) -> {
                  releaseResources$1(source, sink, Nil$.MODULE$);
                  throw err;
               };
               Serializable var32 = new Serializable(onError_handler) {
                  private static final long serialVersionUID = 0L;
                  private final Function1 handler$1;

                  public final Object applyOrElse(final Throwable x1, final Function1 default) {
                     return this.handler$1.apply(x1);
                  }

                  public final boolean isDefinedAt(final Throwable x1) {
                     return true;
                  }

                  public {
                     this.handler$1 = handler$1;
                  }
               };
               onError_handler = null;
               var33 = (Process)var32.apply(var18);
            }

            Process second = var33;

            try {
               var36 = this.a.run(firstIO);
            } catch (Throwable var17) {
               processInternal$ var34 = processInternal$.MODULE$;
               Function1 onError_handler = (err) -> {
                  releaseResources$1(source, sink, ScalaRunTime$.MODULE$.wrapRefArray(new Process[]{second}));
                  throw err;
               };
               Serializable var35 = new Serializable(onError_handler) {
                  private static final long serialVersionUID = 0L;
                  private final Function1 handler$1;

                  public final Object applyOrElse(final Throwable x1, final Function1 default) {
                     return this.handler$1.apply(x1);
                  }

                  public final boolean isDefinedAt(final Throwable x1) {
                     return true;
                  }

                  public {
                     this.handler$1 = handler$1;
                  }
               };
               onError_handler = null;
               var36 = (Process)var35.apply(var17);
            }

            Process first = var36;
            return this.runInterruptible(() -> {
               int exit1 = first.exitValue();
               source.done();
               source.join();
               int exit2 = second.exitValue();
               sink.done();
               return this.b.hasExitValue() ? exit2 : exit1;
            }, () -> releaseResources$1(source, sink, ScalaRunTime$.MODULE$.wrapRefArray(new Process[]{first, second})));
         }
      }

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$PipedProcesses$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$runAndExitValue$6(final Process x$5) {
         x$5.destroy();
      }

      private static final void releaseResources$1(final PipeSource so, final PipeSink sk, final Seq ps) {
         so.release();
         sk.release();
         ps.foreach((x$5) -> {
            $anonfun$runAndExitValue$6(x$5);
            return BoxedUnit.UNIT;
         });
      }

      // $FF: synthetic method
      public static final void $anonfun$runAndExitValue$7(final PipeSource source$1, final InputStream in) {
         source$1.connectIn(in);
      }

      // $FF: synthetic method
      public static final void $anonfun$runAndExitValue$8(final PipeSource source$1, final InputStream in) {
         source$1.connectIn(in);
      }

      // $FF: synthetic method
      public static final void $anonfun$runAndExitValue$9(final PipeSink sink$1, final OutputStream out) {
         sink$1.connectOut(out);
      }

      public PipedProcesses(final ProcessBuilder a, final ProcessBuilder b, final ProcessIO defaultIO, final boolean toError) {
         this.a = a;
         this.b = b;
         this.defaultIO = defaultIO;
         this.toError = toError;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public abstract class PipeThread extends Thread {
      private final boolean isSink;
      // $FF: synthetic field
      public final Process$ $outer;

      public abstract void run();

      public void runloop(final InputStream src, final OutputStream dst) {
         try {
            BasicIO$.MODULE$.transferFully(src, dst);
         } catch (Throwable var9) {
            processInternal$ var10000 = processInternal$.MODULE$;
            Function1 ioFailure_handler = (e) -> {
               $anonfun$runloop$1(this, e);
               return BoxedUnit.UNIT;
            };
            Serializable var12 = new Serializable(ioFailure_handler) {
               private static final long serialVersionUID = 0L;
               private final Function1 handler$4;

               public final Object applyOrElse(final Throwable x1, final Function1 default) {
                  if (x1 instanceof IOException) {
                     IOException var3 = (IOException)x1;
                     return this.handler$4.apply(var3);
                  } else {
                     return default.apply(x1);
                  }
               }

               public final boolean isDefinedAt(final Throwable x1) {
                  return x1 instanceof IOException;
               }

               public {
                  this.handler$4 = handler$4;
               }
            };
            ioFailure_handler = null;
            PartialFunction catchExpr$4 = var12;
            if (!((<undefinedtype>)catchExpr$4).isDefinedAt(var9)) {
               throw var9;
            }

            catchExpr$4.apply(var9);
         } finally {
            BasicIO$.MODULE$.close((Closeable)(this.isSink ? dst : src));
         }

      }

      private void ioHandler(final IOException e) {
         e.printStackTrace();
      }

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$PipeThread$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$runloop$1(final PipeThread $this, final IOException e) {
         $this.ioHandler(e);
      }

      public PipeThread(final boolean isSink, final Function0 labelFn) {
         this.isSink = isSink;
         if (ProcessImpl.this == null) {
            throw null;
         } else {
            this.$outer = ProcessImpl.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class PipeSource extends PipeThread {
      private final PipedOutputStream pipe;
      private final LinkedBlockingQueue source;

      public PipedOutputStream pipe() {
         return this.pipe;
      }

      public LinkedBlockingQueue source() {
         return this.source;
      }

      public final void run() {
         try {
            this.go$1();
         } catch (Throwable var7) {
            processInternal$ var10000 = processInternal$.MODULE$;
            Function0 onInterrupt_handler = () -> {
            };
            Serializable var10 = new Serializable(onInterrupt_handler) {
               private static final long serialVersionUID = 0L;
               private final Function0 handler$3;

               public final Object applyOrElse(final Throwable x1, final Function1 default) {
                  return x1 instanceof InterruptedException ? this.handler$3.apply() : default.apply(x1);
               }

               public final boolean isDefinedAt(final Throwable x1) {
                  return x1 instanceof InterruptedException;
               }

               public {
                  this.handler$3 = handler$3;
               }
            };
            onInterrupt_handler = null;
            PartialFunction catchExpr$5 = var10;
            if (!((<undefinedtype>)catchExpr$5).isDefinedAt(var7)) {
               throw var7;
            }

            catchExpr$5.apply(var7);
         } finally {
            BasicIO$.MODULE$.close(this.pipe());
         }

      }

      public void connectIn(final InputStream in) {
         this.source().put(new Some(in));
      }

      public void connectOut(final PipeSink sink) {
         sink.connectIn(this.pipe());
      }

      public void release() {
         this.interrupt();
         this.done();
         this.join();
      }

      public void done() {
         this.source().put(None$.MODULE$);
      }

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$PipeSource$$$outer() {
         return this.$outer;
      }

      private final void go$1() {
         while(true) {
            Option var1 = (Option)this.source().take();
            if (!(var1 instanceof Some)) {
               if (None$.MODULE$.equals(var1)) {
                  return;
               }

               throw new MatchError(var1);
            }

            InputStream in = (InputStream)((Some)var1).value();
            this.runloop(in, this.pipe());
         }
      }

      public PipeSource(final Function0 label) {
         super(false, label);
         this.setName((new StringBuilder(13)).append("PipeSource(").append(label.apply()).append(")-").append(this.getName()).toString());
         this.pipe = new PipedOutputStream();
         this.source = new LinkedBlockingQueue(1);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class PipeSink extends PipeThread {
      private final PipedInputStream pipe;
      private final LinkedBlockingQueue sink;

      public PipedInputStream pipe() {
         return this.pipe;
      }

      public LinkedBlockingQueue sink() {
         return this.sink;
      }

      public void run() {
         try {
            this.go$2();
         } catch (Throwable var7) {
            processInternal$ var10000 = processInternal$.MODULE$;
            Function0 onInterrupt_handler = () -> {
            };
            Serializable var10 = new Serializable(onInterrupt_handler) {
               private static final long serialVersionUID = 0L;
               private final Function0 handler$3;

               public final Object applyOrElse(final Throwable x1, final Function1 default) {
                  return x1 instanceof InterruptedException ? this.handler$3.apply() : default.apply(x1);
               }

               public final boolean isDefinedAt(final Throwable x1) {
                  return x1 instanceof InterruptedException;
               }

               public {
                  this.handler$3 = handler$3;
               }
            };
            onInterrupt_handler = null;
            PartialFunction catchExpr$6 = var10;
            if (!((<undefinedtype>)catchExpr$6).isDefinedAt(var7)) {
               throw var7;
            }

            catchExpr$6.apply(var7);
         } finally {
            BasicIO$.MODULE$.close(this.pipe());
         }

      }

      public void connectOut(final OutputStream out) {
         this.sink().put(new Some(out));
      }

      public void connectIn(final PipedOutputStream pipeOut) {
         this.pipe().connect(pipeOut);
      }

      public void release() {
         this.interrupt();
         this.done();
         this.join();
      }

      public void done() {
         this.sink().put(None$.MODULE$);
      }

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$PipeSink$$$outer() {
         return this.$outer;
      }

      private final void go$2() {
         while(true) {
            Option var1 = (Option)this.sink().take();
            if (!(var1 instanceof Some)) {
               if (None$.MODULE$.equals(var1)) {
                  return;
               }

               throw new MatchError(var1);
            }

            OutputStream out = (OutputStream)((Some)var1).value();
            this.runloop(this.pipe(), out);
         }
      }

      public PipeSink(final Function0 label) {
         super(true, label);
         this.setName((new StringBuilder(11)).append("PipeSink(").append(label.apply()).append(")-").append(this.getName()).toString());
         this.pipe = new PipedInputStream();
         this.sink = new LinkedBlockingQueue(1);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class DummyProcess implements Process {
      // $FF: synthetic field
      private final Tuple2 x$6;
      private final Thread thread;
      private final Function0 value;
      // $FF: synthetic field
      public final Process$ $outer;

      public boolean isAlive() {
         return this.thread.isAlive();
      }

      public int exitValue() {
         return this.value.apply$mcI$sp();
      }

      public void destroy() {
      }

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$DummyProcess$$$outer() {
         return this.$outer;
      }

      public DummyProcess(final Function0 action) {
         if (ProcessImpl.this == null) {
            throw null;
         } else {
            this.$outer = ProcessImpl.this;
            super();
            Tuple2 var3 = ProcessImpl.this.Future().apply(action);
            if (var3 != null) {
               Thread thread = (Thread)var3._1();
               Function0 value = (Function0)var3._2();
               this.x$6 = new Tuple2(thread, value);
               this.thread = (Thread)this.x$6._1();
               this.value = (Function0)this.x$6._2();
            } else {
               throw new MatchError((Object)null);
            }
         }
      }
   }

   public class SimpleProcess implements Process {
      private final java.lang.Process p;
      private final Thread inputThread;
      private final List outputThreads;
      // $FF: synthetic field
      public final Process$ $outer;

      public boolean isAlive() {
         return this.p.isAlive();
      }

      public int exitValue() {
         try {
            this.p.waitFor();
         } finally {
            this.interrupt();
         }

         List var10000 = this.outputThreads;
         if (var10000 == null) {
            throw null;
         } else {
            for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               ((Thread)foreach_these.head()).join();
            }

            Object var5 = null;
            return this.p.exitValue();
         }
      }

      public void destroy() {
         try {
            List var10000 = this.outputThreads;
            if (var10000 == null) {
               throw null;
            }

            for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               ((Thread)foreach_these.head()).interrupt();
            }

            Object var5 = null;
            this.p.destroy();
         } finally {
            this.interrupt();
         }

      }

      private void interrupt() {
         if (this.inputThread != null) {
            this.inputThread.interrupt();
         }
      }

      // $FF: synthetic method
      public Process$ scala$sys$process$ProcessImpl$SimpleProcess$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$exitValue$2(final Thread x$7) {
         x$7.join();
      }

      // $FF: synthetic method
      public static final void $anonfun$destroy$1(final Thread x$8) {
         x$8.interrupt();
      }

      public SimpleProcess(final java.lang.Process p, final Thread inputThread, final List outputThreads) {
         this.p = p;
         this.inputThread = inputThread;
         this.outputThreads = outputThreads;
         if (ProcessImpl.this == null) {
            throw null;
         } else {
            this.$outer = ProcessImpl.this;
            super();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$exitValue$2$adapted(final Thread x$7) {
         $anonfun$exitValue$2(x$7);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$destroy$1$adapted(final Thread x$8) {
         $anonfun$destroy$1(x$8);
         return BoxedUnit.UNIT;
      }
   }

   public final class ThreadProcess implements Process {
      private final Thread thread;
      private final LinkedBlockingQueue success;

      public boolean isAlive() {
         return this.thread.isAlive();
      }

      public int exitValue() {
         return BoxesRunTime.unboxToBoolean(this.success.take()) ? 0 : 1;
      }

      public void destroy() {
         this.thread.interrupt();
      }

      public ThreadProcess(final Thread thread, final LinkedBlockingQueue success) {
         this.thread = thread;
         this.success = success;
      }
   }
}
