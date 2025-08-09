package scala.util.matching;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.package$;
import scala.collection.AbstractIterator;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.immutable.Seq;
import scala.collection.immutable.WrappedString;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rue\u0001B8q\u0001]D!\"!\u0005\u0001\u0005\u000b\u0007I\u0011AA\n\u0011)\t9\u0003\u0001B\u0001B\u0003%\u0011Q\u0003\u0005\u000b\u0003S\u0001!\u0011!Q\u0001\n\u0005-\u0002\u0002CA!\u0001\u0011\u0005\u0001/a\u0011\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002N!9\u00111\u000b\u0001\u0005\u0002\u0005U\u0003bBA*\u0001\u0011\u0005\u00111\u000f\u0005\b\u0003'\u0002A\u0011AAB\u0011\u001d\u00199\u0002\u0001C\t\u00073Aqa!\b\u0001\t\u0003\u0019y\u0002C\u0004\u0004&\u0001!\taa\n\t\u000f\r5\u0002\u0001\"\u0001\u00040!91Q\u0007\u0001\u0005\u0002\r]\u0002bBB\u001f\u0001\u0011\u00051q\b\u0005\b\u0007\u0007\u0002A\u0011AB#\u0011\u001d\u0019I\u0005\u0001C\u0001\u0007\u0017Bqaa\u0014\u0001\t\u0003\u0019\t\u0006C\u0004\u0004P\u0001!\taa\u0017\t\u000f\r%\u0004\u0001\"\u0001\u0004l!911\u000f\u0001\u0005\u0002\rU\u0004bBB>\u0001\u0011\u00051Q\u0010\u0005\b\u0007\u000b\u0003A\u0011ABD\u0011\u001d\u0019y\t\u0001C\u0001\u0005;Cq!a\u0007\u0001\t\u0003\t\t\u0010C\u0004\u0003\u001c\u0001!\tE!\b\b\u000f\u00055\u0005\u000f#\u0001\u0002\u0010\u001a1q\u000e\u001dE\u0001\u0003#Cq!!\u0011\u001c\t\u0003\tiJB\u0005\u0002 n\u0001\n1!\u0001\u0002\"\"9\u00111U\u000f\u0005\u0002\u0005\u0015\u0006bBAW;\u0019E\u0011q\u0016\u0005\n\u0003ok\"\u0019!D\u0001\u0003sC\u0011\"!\u000b\u001e\u0005\u00045\t!a/\t\u000f\u0005]WD\"\u0001\u0002Z\"9\u0011\u0011]\u000f\u0007\u0002\u0005e\u0007bBAq;\u0019\u0005\u00111\u001d\u0005\b\u0003Slb\u0011AAm\u0011\u001d\tI/\bD\u0001\u0003WDq!a<\u001e\t\u0003\t\t\u0010C\u0004\u0002tv!\t!!>\t\u000f\u0005eX\u0004\"\u0001\u0002|\"9\u0011Q`\u000f\u0005\u0002\u0005e\u0006bBA\u007f;\u0011\u0005\u0011q \u0005\b\u0005\u0007iB\u0011AA]\u0011\u001d\u0011\u0019!\bC\u0001\u0005\u000bAqA!\u0003\u001e\t\u0013\tY\f\u0003\u0006\u0003\fuA)\u0019)C\u0005\u0005\u001bAq!a=\u001e\t\u0003\u0011)\u0002C\u0004\u0003\u001cu!\tE!\b\u0007\r\t}1\u0004\u0001B\u0011\u0011)\t9L\rBC\u0002\u0013\u0005\u0011\u0011\u0018\u0005\u000b\u0005O\u0011$\u0011!Q\u0001\n\u0005\u001d\u0004bCAWe\t\u0015\r\u0011\"\u0005q\u0003_C!B!\u000b3\u0005\u0003\u0005\u000b\u0011BAY\u0011)\u0011YC\rB\u0001B\u0003%\u0011Q\u0018\u0005\b\u0003\u0003\u0012D\u0011\u0001B\u0017\u0011%\tIC\rb\u0001\n\u0003\tY\f\u0003\u0005\u0003:I\u0002\u000b\u0011BA_\u0011%\t\tO\rb\u0001\n\u0003\tI\u000e\u0003\u0005\u0003>I\u0002\u000b\u0011BAn\u0011%\tIO\rb\u0001\n\u0003\tI\u000e\u0003\u0005\u0003@I\u0002\u000b\u0011BAn\u0011\u001d\t9N\rC\u0001\u00033D!B!\u00113\u0011\u000b\u0007K\u0011\u0002B\"\u0011)\u0011YE\rECB\u0013%!1\t\u0005\b\u0003C\u0014D\u0011\u0001B'\u0011\u001d\tIO\rC\u0001\u0005#BqA!\u00163\t\u0003\u00119fB\u0004\u0003\\mA\tA!\u0018\u0007\u000f\t}1\u0004#\u0001\u0003`!9\u0011\u0011\t$\u0005\u0002\t\u0005\u0004b\u0002B2\r\u0012\u0005!QM\u0004\b\u0005_Z\u0002\u0012\u0001B9\r\u001d\u0011\u0019h\u0007E\u0001\u0005kBq!!\u0011K\t\u0003\u00119\bC\u0004\u0002T)#\tA!\u001f\t\u000f\t}4\u0004\"\u0003\u0003\u0002\u001a1!QR\u000e\u0001\u0005\u001fC!\"a.O\u0005\u000b\u0007I\u0011AA]\u0011)\u00119C\u0014B\u0001B\u0003%\u0011q\r\u0005\u000b\u00037q%Q1A\u0005\u0002\tu\u0005B\u0003BP\u001d\n\u0005\t\u0015!\u0003\u0002F!Y!1\u0006(\u0003\u0006\u0004%\taGA^\u0011)\u0011\tK\u0014B\u0001B\u0003%\u0011Q\u0018\u0005\b\u0003\u0003rE\u0011\u0001BR\u0011%\tIC\u0014b\u0001\n\u0003\tY\f\u0003\u0005\u0003:9\u0003\u000b\u0011BA_\u0011)\tiK\u0014b\u0001\n#Y\u0012q\u0016\u0005\t\u0005Sq\u0005\u0015!\u0003\u00022\"A!\u0011\u0017(!B\u0013\tY\u000eC\u0004\u00034:#\tA!.\t\u000f\tuf\n\"\u0001\u0003\u001e!9!1\u0004(\u0005B\tu\u0001\u0002\u0003B`\u001d\u0002&I!!*\t\u000f\u0005\u0005h\n\"\u0001\u0002Z\"9\u0011\u0011\u001d(\u0005\u0002\t\u0005\u0007bBAu\u001d\u0012\u0005\u0011\u0011\u001c\u0005\b\u0003StE\u0011\u0001Bc\u0011\u001d\t9N\u0014C\u0001\u00033DqA!3O\t\u0003\u0011Y\r\u0003\u0005\u0003T:#\t\u0001\u001dBk\r)\u0011\to\u0007I\u0001\u0004\u0003\u0001(1\u001d\u0005\b\u0003G3G\u0011AAS\u0011\u001d\tiK\u001aD\t\u0003_C\u0011B!:g\u0005\u0004&IAa:\t\u000f\t=h\r\"\u0001\u0003r\"9!q\u001f4\u0005\u0002\te\bb\u0002B\u00007\u0011\u00051\u0011\u0001\u0005\b\u0007\u000fYB\u0011AB\u0005\u0011%\u0019iaGA\u0001\n\u0013\u0019yAA\u0003SK\u001e,\u0007P\u0003\u0002re\u0006AQ.\u0019;dQ&twM\u0003\u0002ti\u0006!Q\u000f^5m\u0015\u0005)\u0018!B:dC2\f7\u0001A\n\u0004\u0001ad\bCA={\u001b\u0005!\u0018BA>u\u0005\u0019\te.\u001f*fMB\u0019Q0a\u0003\u000f\u0007y\f9AD\u0002\u0000\u0003\u000bi!!!\u0001\u000b\u0007\u0005\ra/\u0001\u0004=e>|GOP\u0005\u0002k&\u0019\u0011\u0011\u0002;\u0002\u000fA\f7m[1hK&!\u0011QBA\b\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\r\tI\u0001^\u0001\ba\u0006$H/\u001a:o+\t\t)\u0002\u0005\u0003\u0002\u0018\u0005\rRBAA\r\u0015\u0011\tY\"!\b\u0002\u000bI,w-\u001a=\u000b\u0007M\fyB\u0003\u0002\u0002\"\u0005!!.\u0019<b\u0013\u0011\t)#!\u0007\u0003\u000fA\u000bG\u000f^3s]\u0006A\u0001/\u0019;uKJt\u0007%\u0001\u0006he>,\bOT1nKN\u0004R!_A\u0017\u0003cI1!a\fu\u0005)a$/\u001a9fCR,GM\u0010\t\u0005\u0003g\tYD\u0004\u0003\u00026\u0005]\u0002CA@u\u0013\r\tI\u0004^\u0001\u0007!J,G-\u001a4\n\t\u0005u\u0012q\b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005eB/\u0001\u0004=S:LGO\u0010\u000b\u0007\u0003\u000b\nI%a\u0013\u0011\u0007\u0005\u001d\u0003!D\u0001q\u0011\u001d\t\t\u0002\u0002a\u0001\u0003+Aq!!\u000b\u0005\u0001\u0004\tY\u0003\u0006\u0004\u0002F\u0005=\u0013\u0011\u000b\u0005\b\u00037)\u0001\u0019AA\u0019\u0011\u001d\tI#\u0002a\u0001\u0003W\t!\"\u001e8baBd\u0017pU3r)\u0011\t9&a\u0019\u0011\u000be\fI&!\u0018\n\u0007\u0005mCO\u0001\u0004PaRLwN\u001c\t\u0006{\u0006}\u0013\u0011G\u0005\u0005\u0003C\nyA\u0001\u0003MSN$\bbBA3\r\u0001\u0007\u0011qM\u0001\u0002gB!\u0011\u0011NA8\u001b\t\tYG\u0003\u0003\u0002n\u0005}\u0011\u0001\u00027b]\u001eLA!!\u001d\u0002l\ta1\t[1s'\u0016\fX/\u001a8dKR!\u0011QOA@!\u0015I\u0018\u0011LA<!\u0015i\u0018qLA=!\rI\u00181P\u0005\u0004\u0003{\"(\u0001B\"iCJDq!!!\b\u0001\u0004\tI(A\u0001d)\u0011\t9&!\"\t\u000f\u0005\u001d\u0005\u00021\u0001\u0002\n\u0006\tQ\u000eE\u0002\u0002\fJr1!a\u0012\u001b\u0003\u0015\u0011VmZ3y!\r\t9eG\n\u00057a\f\u0019\n\u0005\u0003\u0002\u0016\u0006mUBAAL\u0015\u0011\tI*a\b\u0002\u0005%|\u0017\u0002BA\u0007\u0003/#\"!a$\u0003\u00135\u000bGo\u00195ECR\f7CA\u000fy\u0003\u0019!\u0013N\\5uIQ\u0011\u0011q\u0015\t\u0004s\u0006%\u0016bAAVi\n!QK\\5u\u0003\u001di\u0017\r^2iKJ,\"!!-\u0011\t\u0005]\u00111W\u0005\u0005\u0003k\u000bIBA\u0004NCR\u001c\u0007.\u001a:\u0002\rM|WO]2f+\t\t9'\u0006\u0002\u0002>B)Q0a0\u00022%!\u0011\u0011YA\b\u0005\r\u0019V-\u001d\u0015\fC\u0005\u0015\u00171ZAg\u0003#\f\u0019\u000eE\u0002z\u0003\u000fL1!!3u\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\ty-\u0001(he>,\bOT1nKN\u0004Cm\\3tA9|G\u000fI5oG2,H-\u001a\u0011j]2Lg.\u001a\u0011he>,\b\u000f\t8b[\u0016\u001cH\u0006I1oI\u0002\u001a\bn\\;mI\u0002rw\u000e\u001e\u0011cK\u0002*8/\u001a3!C:LXn\u001c:f\u0003\u0015\u0019\u0018N\\2fC\t\t).\u0001\u00043]E\u001adfN\u0001\u000bOJ|W\u000f]\"pk:$XCAAn!\rI\u0018Q\\\u0005\u0004\u0003?$(aA%oi\u0006)1\u000f^1siR!\u00111\\As\u0011\u001d\t9\u000f\na\u0001\u00037\f\u0011![\u0001\u0004K:$G\u0003BAn\u0003[Dq!a:'\u0001\u0004\tY.A\u0004nCR\u001c\u0007.\u001a3\u0016\u0005\u0005E\u0012!B4s_V\u0004H\u0003BA\u0019\u0003oDq!a:)\u0001\u0004\tY.A\u0005tk\n<'o\\;qgV\u0011\u0011QL\u0001\u0007E\u00164wN]3\u0015\t\u0005\u001d$\u0011\u0001\u0005\b\u0003O\\\u0003\u0019AAn\u0003\u0015\tg\r^3s)\u0011\t9Ga\u0002\t\u000f\u0005\u001dX\u00061\u0001\u0002\\\u0006\u0001rM]8va:\u000bW.Z:O_^\f'O\\\u0001\f]\u0006lW\rV8J]\u0012,\u00070\u0006\u0002\u0003\u0010AA\u00111\u0007B\t\u0003c\tY.\u0003\u0003\u0003\u0014\u0005}\"aA'baR!\u0011\u0011\u0007B\f\u0011\u001d\u0011I\u0002\ra\u0001\u0003c\t!!\u001b3\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\r\u0003\u000b5\u000bGo\u00195\u0014\tIB(1\u0005\t\u0004\u0005KiR\"A\u000e\u0002\u000fM|WO]2fA\u0005AQ.\u0019;dQ\u0016\u0014\b%A\u0006`OJ|W\u000f\u001d(b[\u0016\u001cH\u0003\u0003B\u0018\u0005c\u0011\u0019D!\u000e\u0011\u0007\t\u0015\"\u0007C\u0004\u00028b\u0002\r!a\u001a\t\u000f\u00055\u0006\b1\u0001\u00022\"9!1\u0006\u001dA\u0002\u0005u\u0006fC\u001d\u0002F\u0006-\u0017QZAi\u0003'\f1b\u001a:pkBt\u0015-\\3tA!Z!(!2\u0002L\u00065\u0017\u0011[Aj\u0003\u0019\u0019H/\u0019:uA\u0005!QM\u001c3!\u0003\u0019\u0019H/\u0019:ugV\u0011!Q\t\t\u0006s\n\u001d\u00131\\\u0005\u0004\u0005\u0013\"(!B!se\u0006L\u0018\u0001B3oIN$B!a7\u0003P!9\u0011q\u001d\"A\u0002\u0005mG\u0003BAn\u0005'Bq!a:D\u0001\u0004\tY.A\u0003g_J\u001cW-\u0006\u0002\u0003Z5\t!'A\u0003NCR\u001c\u0007\u000eE\u0002\u0003&\u0019\u001b\"A\u0012=\u0015\u0005\tu\u0013aB;oCB\u0004H.\u001f\u000b\u0005\u0005O\u0012i\u0007E\u0003z\u0005S\n\t$C\u0002\u0003lQ\u0014AaU8nK\"9\u0011q\u0011%A\u0002\t=\u0012AB$s_V\u00048\u000fE\u0002\u0003&)\u0013aa\u0012:pkB\u001c8C\u0001&y)\t\u0011\t\b\u0006\u0003\u0003|\tu\u0004#B=\u0002Z\u0005u\u0006bBAD\u0019\u0002\u0007!qF\u0001\u0017Kb$(/Y2u\u000fJ|W\u000f]:Ge>lW*\u0019;dQR!\u0011q\u000bBB\u0011\u001d\t9)\u0014a\u0001\u0005_A3!\u0014BD!\rI(\u0011R\u0005\u0004\u0005\u0017#(AB5oY&tWMA\u0007NCR\u001c\u0007.\u0013;fe\u0006$xN]\n\u0006\u001d\nE%1\u0005\t\u0007\u0005'\u0013I*!\r\u000e\u0005\tU%b\u0001BLi\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\tm%Q\u0013\u0002\u0011\u0003\n\u001cHO]1di&#XM]1u_J,\"!!\u0012\u0002\rI,w-\u001a=!\u00031yvM]8va:\u000bW.Z:!)!\u0011)Ka*\u0003*\n-\u0006c\u0001B\u0013\u001d\"9\u0011qW+A\u0002\u0005\u001d\u0004bBA\u000e+\u0002\u0007\u0011Q\t\u0005\b\u0005W)\u0006\u0019AA_Q-1\u0016QYAf\u0003\u001b\f\t.a5)\u0017]\u000b)-a3\u0002N\u0006E\u00171[\u0001\t]\u0016DHoU3f]\u00069\u0001.Y:OKb$XC\u0001B\\!\rI(\u0011X\u0005\u0004\u0005w#(a\u0002\"p_2,\u0017M\\\u0001\u0005]\u0016DH/\u0001\u0004f]N,(/\u001a\u000b\u0005\u00037\u0014\u0019\rC\u0004\u0002h\u0002\u0004\r!a7\u0015\t\u0005m'q\u0019\u0005\b\u0003O\u0014\u0007\u0019AAn\u0003%i\u0017\r^2i\t\u0006$\u0018-\u0006\u0002\u0003NB)QPa4\u00030%!!\u0011[A\b\u0005!IE/\u001a:bi>\u0014\u0018a\u0004:fa2\f7-Z7f]R$\u0015\r^1\u0016\u0005\t]'C\u0002Bm\u0005;\u0014yN\u0002\u0004\u0003\\\u0016\u0004!q\u001b\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0007\u0005'\u0013IJa\f\u0011\u0007\t\u0015bMA\u0006SKBd\u0017mY3nK:$8C\u00014y\u0003\t\u0019(-\u0006\u0002\u0003jB!\u0011\u0011\u000eBv\u0013\u0011\u0011i/a\u001b\u0003\u0019M#(/\u001b8h\u0005V4g-\u001a:\u0002\u0011I,\u0007\u000f\\1dK\u0012,\"Aa=\u0011\t\u0005%$Q_\u0005\u0005\u0003{\tY'A\u0004sKBd\u0017mY3\u0015\t\u0005E&1 \u0005\b\u0005{\\\u0007\u0019AA\u0019\u0003\t\u00118/A\u0003rk>$X\r\u0006\u0003\u00022\r\r\u0001bBB\u0003Y\u0002\u0007\u0011\u0011G\u0001\u0005i\u0016DH/\u0001\trk>$XMU3qY\u0006\u001cW-\\3oiR!\u0011\u0011GB\u0006\u0011\u001d\u0019)!\u001ca\u0001\u0003c\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"a!\u0005\u0011\t\u0005%41C\u0005\u0005\u0007+\tYG\u0001\u0004PE*,7\r^\u0001\u000beVtW*\u0019;dQ\u0016\u0014H\u0003\u0002B\\\u00077Aq!a\"\n\u0001\u0004\t\t,A\u0005gS:$\u0017\t\u001c7J]R!1\u0011EB\u0012!\r\tYI\u0014\u0005\b\u0003oS\u0001\u0019AA4\u000391\u0017N\u001c3BY2l\u0015\r^2i\u0013:$Ba!\u000b\u0004,A)QPa4\u0002\n\"9\u0011qW\u0006A\u0002\u0005\u001d\u0014a\u00034j]\u00124\u0015N]:u\u0013:$Ba!\r\u00044A)\u00110!\u0017\u00022!9\u0011q\u0017\u0007A\u0002\u0005\u001d\u0014\u0001\u00054j]\u00124\u0015N]:u\u001b\u0006$8\r[%o)\u0011\u0019Ida\u000f\u0011\u000be\fI&!#\t\u000f\u0005]V\u00021\u0001\u0002h\u0005aa-\u001b8e!J,g-\u001b=PMR!1\u0011GB!\u0011\u001d\t9L\u0004a\u0001\u0003O\n\u0011CZ5oIB\u0013XMZ5y\u001b\u0006$8\r[(g)\u0011\u0019Ida\u0012\t\u000f\u0005]v\u00021\u0001\u0002h\u00059Q.\u0019;dQ\u0016\u001cH\u0003\u0002B\\\u0007\u001bBq!a.\u0011\u0001\u0004\t9'\u0001\u0007sKBd\u0017mY3BY2Le\u000e\u0006\u0004\u00022\rM3q\u000b\u0005\b\u0007+\n\u0002\u0019AA4\u0003\u0019!\u0018M]4fi\"91\u0011L\tA\u0002\u0005E\u0012a\u0003:fa2\f7-Z7f]R$b!!\r\u0004^\r}\u0003bBB+%\u0001\u0007\u0011q\r\u0005\b\u0007C\u0012\u0002\u0019AB2\u0003!\u0011X\r\u001d7bG\u0016\u0014\bcB=\u0004f\u0005%\u0015\u0011G\u0005\u0004\u0007O\"(!\u0003$v]\u000e$\u0018n\u001c82\u00035\u0011X\r\u001d7bG\u0016\u001cv.\\3J]R1\u0011\u0011GB7\u0007_Bqa!\u0016\u0014\u0001\u0004\t9\u0007C\u0004\u0004bM\u0001\ra!\u001d\u0011\u000fe\u001c)'!#\u00042\u0005q!/\u001a9mC\u000e,g)\u001b:ti&sGCBA\u0019\u0007o\u001aI\bC\u0004\u0004VQ\u0001\r!a\u001a\t\u000f\reC\u00031\u0001\u00022\u0005)1\u000f\u001d7jiR!1qPBA!\u0015I(qIA\u0019\u0011\u001d\u0019\u0019)\u0006a\u0001\u0003O\nq\u0001^8Ta2LG/\u0001\u0006v]\u0006t7\r[8sK\u0012,\"a!#\u0011\t\u0005\u001d31R\u0005\u0004\u0007\u001b\u0003(aD+oC:\u001c\u0007n\u001c:fIJ+w-\u001a=\u0002\u0011\u0005t7\r[8sK\u0012Ds\u0001ABJ\u00073\u001bY\nE\u0002z\u0007+K1aa&u\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XM\b\u0005c\\XWg\u0014}\u0011@\u0002"
)
public class Regex implements Serializable {
   private static final long serialVersionUID = -2094783597747625537L;
   private final Pattern pattern;
   public final Seq scala$util$matching$Regex$$groupNames;

   public static String quoteReplacement(final String text) {
      Regex$ var10000 = Regex$.MODULE$;
      return Matcher.quoteReplacement(text);
   }

   public static String quote(final String text) {
      Regex$ var10000 = Regex$.MODULE$;
      return Pattern.quote(text);
   }

   public Pattern pattern() {
      return this.pattern;
   }

   public Option unapplySeq(final CharSequence s) {
      Matcher m = this.pattern().matcher(s);
      if (!this.runMatcher(m)) {
         return None$.MODULE$;
      } else {
         Some var10000 = new Some;
         List$ var10002 = package$.MODULE$.List();
         int tabulate_n = m.groupCount();
         if (var10002 == null) {
            throw null;
         } else {
            Builder tabulate_b = new ListBuffer();
            tabulate_b.sizeHint(tabulate_n);

            for(int tabulate_i = 0; tabulate_i < tabulate_n; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = m.group(tabulate_i + 1);
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            SeqOps var10 = (SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var9 = null;
            var10000.<init>(var10);
            return var10000;
         }
      }
   }

   public Option unapplySeq(final char c) {
      Matcher m = this.pattern().matcher(Character.toString(c));
      if (this.runMatcher(m)) {
         if (m.groupCount() > 0) {
            Some var10000 = new Some;
            WrappedString var10002 = Predef$.MODULE$.wrapString(m.group(1));
            if (var10002 == null) {
               throw null;
            } else {
               var10000.<init>(IterableOnceOps.toList$(var10002));
               return var10000;
            }
         } else {
            return new Some(Nil$.MODULE$);
         }
      } else {
         return None$.MODULE$;
      }
   }

   public Option unapplySeq(final Match m) {
      if (m.matched() == null) {
         return None$.MODULE$;
      } else {
         Pattern var10000 = m.matcher().pattern();
         Pattern var2 = this.pattern();
         if (var10000 == null) {
            if (var2 != null) {
               return this.unapplySeq((CharSequence)m.matched());
            }
         } else if (!var10000.equals(var2)) {
            return this.unapplySeq((CharSequence)m.matched());
         }

         Regex$ var10 = Regex$.MODULE$;
         Some var11 = new Some;
         List$ var10002 = package$.MODULE$.List();
         int scala$util$matching$Regex$$extractGroupsFromMatch_tabulate_n = m.groupCount();
         if (var10002 == null) {
            throw null;
         } else {
            Builder tabulate_b = new ListBuffer();
            tabulate_b.sizeHint(scala$util$matching$Regex$$extractGroupsFromMatch_tabulate_n);

            for(int tabulate_i = 0; tabulate_i < scala$util$matching$Regex$$extractGroupsFromMatch_tabulate_n; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = m.group(tabulate_i + 1);
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            SeqOps var12 = (SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var9 = null;
            var11.<init>(var12);
            return var11;
         }
      }
   }

   public boolean runMatcher(final Matcher m) {
      return m.matches();
   }

   public MatchIterator findAllIn(final CharSequence source) {
      return new MatchIterator(source, this, this.scala$util$matching$Regex$$groupNames);
   }

   public Iterator findAllMatchIn(final CharSequence source) {
      MatchIterator matchIterator = this.findAllIn(source);
      return new AbstractIterator(matchIterator) {
         private final MatchIterator matchIterator$1;

         public boolean hasNext() {
            return this.matchIterator$1.hasNext();
         }

         public Match next() {
            this.matchIterator$1.next();
            return (new Match(this.matchIterator$1.source(), this.matchIterator$1.matcher(), this.matchIterator$1._groupNames())).force();
         }

         public {
            this.matchIterator$1 = matchIterator$1;
         }
      };
   }

   public Option findFirstIn(final CharSequence source) {
      Matcher m = this.pattern().matcher(source);
      return (Option)(m.find() ? new Some(m.group()) : None$.MODULE$);
   }

   public Option findFirstMatchIn(final CharSequence source) {
      Matcher m = this.pattern().matcher(source);
      return (Option)(m.find() ? new Some(new Match(source, m, this.scala$util$matching$Regex$$groupNames)) : None$.MODULE$);
   }

   public Option findPrefixOf(final CharSequence source) {
      Matcher m = this.pattern().matcher(source);
      return (Option)(m.lookingAt() ? new Some(m.group()) : None$.MODULE$);
   }

   public Option findPrefixMatchOf(final CharSequence source) {
      Matcher m = this.pattern().matcher(source);
      return (Option)(m.lookingAt() ? new Some(new Match(source, m, this.scala$util$matching$Regex$$groupNames)) : None$.MODULE$);
   }

   public boolean matches(final CharSequence source) {
      return this.runMatcher(this.pattern().matcher(source));
   }

   public String replaceAllIn(final CharSequence target, final String replacement) {
      return this.pattern().matcher(target).replaceAll(replacement);
   }

   public String replaceAllIn(final CharSequence target, final Function1 replacer) {
      AbstractIterator it = (new MatchIterator(target, this, this.scala$util$matching$Regex$$groupNames)).replacementData();
      it.foreach((md) -> ((Replacement)it).replace((String)replacer.apply(md)));
      return ((Replacement)it).replaced();
   }

   public String replaceSomeIn(final CharSequence target, final Function1 replacer) {
      AbstractIterator it = (new MatchIterator(target, this, this.scala$util$matching$Regex$$groupNames)).replacementData();
      it.foreach((matchdata) -> {
         $anonfun$replaceSomeIn$1(replacer, it, matchdata);
         return BoxedUnit.UNIT;
      });
      return ((Replacement)it).replaced();
   }

   public String replaceFirstIn(final CharSequence target, final String replacement) {
      return this.pattern().matcher(target).replaceFirst(replacement);
   }

   public String[] split(final CharSequence toSplit) {
      return this.pattern().split(toSplit);
   }

   public UnanchoredRegex unanchored() {
      return new UnanchoredRegex() {
         // $FF: synthetic field
         private final Regex $outer;

         public boolean runMatcher(final Matcher m) {
            return UnanchoredRegex.runMatcher$(this, m);
         }

         public UnanchoredRegex unanchored() {
            return UnanchoredRegex.unanchored$(this);
         }

         public Regex anchored() {
            return this.$outer;
         }

         public {
            if (Regex.this == null) {
               throw null;
            } else {
               this.$outer = Regex.this;
            }
         }
      };
   }

   public Regex anchored() {
      return this;
   }

   public String regex() {
      return this.pattern().pattern();
   }

   public String toString() {
      return this.regex();
   }

   // $FF: synthetic method
   public static final String $anonfun$unapplySeq$1(final Matcher m$1, final int i) {
      return m$1.group(i + 1);
   }

   // $FF: synthetic method
   public static final Matcher $anonfun$replaceSomeIn$2(final AbstractIterator it$2, final String replacement) {
      return ((Replacement)it$2).replace(replacement);
   }

   // $FF: synthetic method
   public static final void $anonfun$replaceSomeIn$1(final Function1 replacer$2, final AbstractIterator it$2, final Match matchdata) {
      Option var10000 = (Option)replacer$2.apply(matchdata);
      if (var10000 == null) {
         throw null;
      } else {
         Option foreach_this = var10000;
         if (!foreach_this.isEmpty()) {
            String var4 = (String)foreach_this.get();
            ((Replacement)it$2).replace(var4);
         }
      }
   }

   public Regex(final Pattern pattern, final Seq groupNames) {
      this.pattern = pattern;
      this.scala$util$matching$Regex$$groupNames = groupNames;
   }

   public Regex(final String regex, final Seq groupNames) {
      this(Pattern.compile(regex), groupNames);
   }

   // $FF: synthetic method
   public static final String $anonfun$unapplySeq$1$adapted(final Matcher m$1, final Object i) {
      return $anonfun$unapplySeq$1(m$1, BoxesRunTime.unboxToInt(i));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface MatchData {
      Matcher matcher();

      CharSequence source();

      /** @deprecated */
      Seq groupNames();

      int groupCount();

      int start();

      int start(final int i);

      int end();

      int end(final int i);

      default String matched() {
         return this.start() >= 0 ? this.source().subSequence(this.start(), this.end()).toString() : null;
      }

      default String group(final int i) {
         return this.start(i) >= 0 ? this.source().subSequence(this.start(i), this.end(i)).toString() : null;
      }

      default List subgroups() {
         RichInt$ var10000 = RichInt$.MODULE$;
         byte var1 = 1;
         int to$extension_end = this.groupCount();
         Range$ var10 = Range$.MODULE$;
         List var11 = IterableOnceOps.toList$(new Range.Inclusive(var1, to$extension_end, 1));
         if (var11 == null) {
            throw null;
         } else {
            List map_this = var11;
            if (map_this == Nil$.MODULE$) {
               return Nil$.MODULE$;
            } else {
               int $anonfun$subgroups$1_i = BoxesRunTime.unboxToInt(map_this.head());
               $colon$colon map_h = new $colon$colon(this.group($anonfun$subgroups$1_i), Nil$.MODULE$);
               $colon$colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != Nil$.MODULE$; map_rest = (List)map_rest.tail()) {
                  int $anonfun$subgroups$1_i = BoxesRunTime.unboxToInt(map_rest.head());
                  $colon$colon map_nx = new $colon$colon(this.group($anonfun$subgroups$1_i), Nil$.MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      }

      default CharSequence before() {
         return this.start() >= 0 ? this.source().subSequence(0, this.start()) : null;
      }

      default CharSequence before(final int i) {
         return this.start(i) >= 0 ? this.source().subSequence(0, this.start(i)) : null;
      }

      default CharSequence after() {
         return this.end() >= 0 ? this.source().subSequence(this.end(), this.source().length()) : null;
      }

      default CharSequence after(final int i) {
         return this.end(i) >= 0 ? this.source().subSequence(this.end(i), this.source().length()) : null;
      }

      private Seq groupNamesNowarn() {
         return this.groupNames();
      }

      default Map scala$util$matching$Regex$MatchData$$nameToIndex() {
         Map$ var10000 = Predef$.MODULE$.Map();
         Nil$ apply_elems = Nil$.MODULE$;
         if (var10000 == null) {
            throw null;
         } else {
            Map var7 = var10000.from(apply_elems);
            apply_elems = null;
            List var10001 = this.groupNames().toList();
            String $colon$colon_elem = "";
            if (var10001 == null) {
               throw null;
            } else {
               List $colon$colon_this = var10001;
               $colon$colon var8 = new $colon$colon($colon$colon_elem, $colon$colon_this);
               $colon$colon_this = null;
               Object var6 = null;
               return (Map)var7.$plus$plus((IterableOnce)StrictOptimizedIterableOps.zipWithIndex$(var8));
            }
         }
      }

      default String group(final String id) {
         if (this.groupNames().isEmpty()) {
            return this.matcher().group(id);
         } else {
            Option var2 = this.scala$util$matching$Regex$MatchData$$nameToIndex().get(id);
            if (var2 instanceof Some) {
               int index = BoxesRunTime.unboxToInt(((Some)var2).value());
               return this.group(index);
            } else if (None$.MODULE$.equals(var2)) {
               return this.matcher().group(id);
            } else {
               throw new MatchError(var2);
            }
         }
      }

      default String toString() {
         return this.matched();
      }

      // $FF: synthetic method
      static String $anonfun$subgroups$1(final MatchData $this, final int i) {
         return $this.group(i);
      }

      static void $init$(final MatchData $this) {
      }

      // $FF: synthetic method
      static String $anonfun$subgroups$1$adapted(final MatchData $this, final Object i) {
         return $anonfun$subgroups$1($this, BoxesRunTime.unboxToInt(i));
      }
   }

   public static class Match implements MatchData {
      private int[] starts;
      private int[] ends;
      private final CharSequence source;
      private final Matcher matcher;
      /** @deprecated */
      private final Seq groupNames;
      private final int start;
      private final int end;
      private Map scala$util$matching$Regex$MatchData$$nameToIndex;
      private volatile byte bitmap$0;

      public String matched() {
         return Regex.MatchData.super.matched();
      }

      public String group(final int i) {
         return Regex.MatchData.super.group(i);
      }

      public List subgroups() {
         return Regex.MatchData.super.subgroups();
      }

      public CharSequence before() {
         return Regex.MatchData.super.before();
      }

      public CharSequence before(final int i) {
         return Regex.MatchData.super.before(i);
      }

      public CharSequence after() {
         return Regex.MatchData.super.after();
      }

      public CharSequence after(final int i) {
         return Regex.MatchData.super.after(i);
      }

      public String group(final String id) {
         return Regex.MatchData.super.group(id);
      }

      public String toString() {
         return Regex.MatchData.super.toString();
      }

      private Map scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 4) == 0) {
               this.scala$util$matching$Regex$MatchData$$nameToIndex = Regex.MatchData.super.scala$util$matching$Regex$MatchData$$nameToIndex();
               this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.scala$util$matching$Regex$MatchData$$nameToIndex;
      }

      public Map scala$util$matching$Regex$MatchData$$nameToIndex() {
         return (byte)(this.bitmap$0 & 4) == 0 ? this.scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() : this.scala$util$matching$Regex$MatchData$$nameToIndex;
      }

      public CharSequence source() {
         return this.source;
      }

      public Matcher matcher() {
         return this.matcher;
      }

      /** @deprecated */
      public Seq groupNames() {
         return this.groupNames;
      }

      public int start() {
         return this.start;
      }

      public int end() {
         return this.end;
      }

      public int groupCount() {
         return this.matcher().groupCount();
      }

      private int[] starts$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               int tabulate_n = this.groupCount() + 1;
               Object var10001;
               if (tabulate_n <= 0) {
                  var10001 = new int[0];
               } else {
                  Object tabulate_array = new int[tabulate_n];

                  for(int tabulate_i = 0; tabulate_i < tabulate_n; ++tabulate_i) {
                     Object var4 = $anonfun$starts$1(this, tabulate_i);
                     ((Object[])tabulate_array)[tabulate_i] = var4;
                  }

                  var10001 = tabulate_array;
               }

               Object var7 = null;
               this.starts = (int[])var10001;
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
         } catch (Throwable var6) {
            throw var6;
         }

         return this.starts;
      }

      private int[] starts() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.starts$lzycompute() : this.starts;
      }

      private int[] ends$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               int tabulate_n = this.groupCount() + 1;
               Object var10001;
               if (tabulate_n <= 0) {
                  var10001 = new int[0];
               } else {
                  Object tabulate_array = new int[tabulate_n];

                  for(int tabulate_i = 0; tabulate_i < tabulate_n; ++tabulate_i) {
                     Object var4 = $anonfun$ends$1(this, tabulate_i);
                     ((Object[])tabulate_array)[tabulate_i] = var4;
                  }

                  var10001 = tabulate_array;
               }

               Object var7 = null;
               this.ends = (int[])var10001;
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
         } catch (Throwable var6) {
            throw var6;
         }

         return this.ends;
      }

      private int[] ends() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.ends$lzycompute() : this.ends;
      }

      public int start(final int i) {
         return this.starts()[i];
      }

      public int end(final int i) {
         return this.ends()[i];
      }

      public Match force() {
         this.starts();
         this.ends();
         return this;
      }

      // $FF: synthetic method
      public static final int $anonfun$starts$1(final Match $this, final int x$1) {
         return $this.matcher().start(x$1);
      }

      // $FF: synthetic method
      public static final int $anonfun$ends$1(final Match $this, final int x$1) {
         return $this.matcher().end(x$1);
      }

      public Match(final CharSequence source, final Matcher matcher, final Seq _groupNames) {
         this.source = source;
         this.matcher = matcher;
         this.groupNames = _groupNames;
         this.start = matcher.start();
         this.end = matcher.end();
      }
   }

   public static class Match$ {
      public static final Match$ MODULE$ = new Match$();

      public Some unapply(final Match m) {
         return new Some(m.matched());
      }
   }

   public static class Groups$ {
      public static final Groups$ MODULE$ = new Groups$();

      public Option unapplySeq(final Match m) {
         if (m.groupCount() <= 0) {
            return None$.MODULE$;
         } else {
            Regex$ var10000 = Regex$.MODULE$;
            Some var9 = new Some;
            List$ var10002 = package$.MODULE$.List();
            int scala$util$matching$Regex$$extractGroupsFromMatch_tabulate_n = m.groupCount();
            if (var10002 == null) {
               throw null;
            } else {
               Builder tabulate_b = new ListBuffer();
               tabulate_b.sizeHint(scala$util$matching$Regex$$extractGroupsFromMatch_tabulate_n);

               for(int tabulate_i = 0; tabulate_i < scala$util$matching$Regex$$extractGroupsFromMatch_tabulate_n; ++tabulate_i) {
                  Object tabulate_$plus$eq_elem = m.group(tabulate_i + 1);
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               SeqOps var10 = (SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var8 = null;
               var9.<init>(var10);
               return var9;
            }
         }
      }
   }

   public static class MatchIterator extends AbstractIterator implements MatchData {
      private final CharSequence source;
      private final Regex regex;
      private final Seq _groupNames;
      /** @deprecated */
      private final Seq groupNames;
      private final Matcher matcher;
      private int nextSeen;
      private Map scala$util$matching$Regex$MatchData$$nameToIndex;
      private volatile boolean bitmap$0;

      public String matched() {
         return Regex.MatchData.super.matched();
      }

      public String group(final int i) {
         return Regex.MatchData.super.group(i);
      }

      public List subgroups() {
         return Regex.MatchData.super.subgroups();
      }

      public CharSequence before() {
         return Regex.MatchData.super.before();
      }

      public CharSequence before(final int i) {
         return Regex.MatchData.super.before(i);
      }

      public CharSequence after() {
         return Regex.MatchData.super.after();
      }

      public CharSequence after(final int i) {
         return Regex.MatchData.super.after(i);
      }

      public String group(final String id) {
         return Regex.MatchData.super.group(id);
      }

      private Map scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.scala$util$matching$Regex$MatchData$$nameToIndex = Regex.MatchData.super.scala$util$matching$Regex$MatchData$$nameToIndex();
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.scala$util$matching$Regex$MatchData$$nameToIndex;
      }

      public Map scala$util$matching$Regex$MatchData$$nameToIndex() {
         return !this.bitmap$0 ? this.scala$util$matching$Regex$MatchData$$nameToIndex$lzycompute() : this.scala$util$matching$Regex$MatchData$$nameToIndex;
      }

      public CharSequence source() {
         return this.source;
      }

      public Regex regex() {
         return this.regex;
      }

      public Seq _groupNames() {
         return this._groupNames;
      }

      /** @deprecated */
      public Seq groupNames() {
         return this.groupNames;
      }

      public Matcher matcher() {
         return this.matcher;
      }

      public boolean hasNext() {
         int var1 = this.nextSeen;
         switch (var1) {
            case 0:
               this.nextSeen = this.matcher().find() ? 1 : 3;
            case 1:
            case 3:
               break;
            case 2:
               this.nextSeen = 0;
               this.hasNext();
               break;
            default:
               throw new MatchError(var1);
         }

         return this.nextSeen == 1;
      }

      public String next() {
         int var1 = this.nextSeen;
         switch (var1) {
            case 0:
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               }

               this.next();
               break;
            case 1:
               this.nextSeen = 2;
               break;
            case 2:
               this.nextSeen = 0;
               this.next();
               break;
            case 3:
               throw new NoSuchElementException();
            default:
               throw new MatchError(var1);
         }

         return this.matcher().group();
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      private void ensure() {
         int var1 = this.nextSeen;
         switch (var1) {
            case 0:
               if (!this.hasNext()) {
                  throw new IllegalStateException();
               }

               return;
            case 1:
               return;
            case 2:
               return;
            case 3:
               throw new IllegalStateException();
            default:
               throw new MatchError(var1);
         }
      }

      public int start() {
         this.ensure();
         return this.matcher().start();
      }

      public int start(final int i) {
         this.ensure();
         return this.matcher().start(i);
      }

      public int end() {
         this.ensure();
         return this.matcher().end();
      }

      public int end(final int i) {
         this.ensure();
         return this.matcher().end(i);
      }

      public int groupCount() {
         this.ensure();
         return this.matcher().groupCount();
      }

      public Iterator matchData() {
         return new AbstractIterator() {
            // $FF: synthetic field
            private final MatchIterator $outer;

            public boolean hasNext() {
               return this.$outer.hasNext();
            }

            public Match next() {
               this.$outer.next();
               return (new Match(this.$outer.source(), this.$outer.matcher(), this.$outer._groupNames())).force();
            }

            public {
               if (MatchIterator.this == null) {
                  throw null;
               } else {
                  this.$outer = MatchIterator.this;
               }
            }
         };
      }

      public AbstractIterator replacementData() {
         return new Replacement() {
            private StringBuffer scala$util$matching$Regex$Replacement$$sb;
            // $FF: synthetic field
            private final MatchIterator $outer;

            public String replaced() {
               return Regex.Replacement.super.replaced();
            }

            public Matcher replace(final String rs) {
               return Regex.Replacement.super.replace(rs);
            }

            public StringBuffer scala$util$matching$Regex$Replacement$$sb() {
               return this.scala$util$matching$Regex$Replacement$$sb;
            }

            public final void scala$util$matching$Regex$Replacement$_setter_$scala$util$matching$Regex$Replacement$$sb_$eq(final StringBuffer x$1) {
               this.scala$util$matching$Regex$Replacement$$sb = x$1;
            }

            public Matcher matcher() {
               return this.$outer.matcher();
            }

            public boolean hasNext() {
               return this.$outer.hasNext();
            }

            public Match next() {
               this.$outer.next();
               return (new Match(this.$outer.source(), this.matcher(), this.$outer._groupNames())).force();
            }

            public {
               if (MatchIterator.this == null) {
                  throw null;
               } else {
                  this.$outer = MatchIterator.this;
                  Regex.Replacement.$init$(this);
                  Statics.releaseFence();
               }
            }
         };
      }

      public MatchIterator(final CharSequence source, final Regex regex, final Seq _groupNames) {
         this.source = source;
         this.regex = regex;
         this._groupNames = _groupNames;
         this.groupNames = _groupNames;
         this.matcher = regex.pattern().matcher(source);
         this.nextSeen = 0;
      }
   }

   public interface Replacement {
      void scala$util$matching$Regex$Replacement$_setter_$scala$util$matching$Regex$Replacement$$sb_$eq(final StringBuffer x$1);

      Matcher matcher();

      StringBuffer scala$util$matching$Regex$Replacement$$sb();

      default String replaced() {
         StringBuffer newsb = new StringBuffer(this.scala$util$matching$Regex$Replacement$$sb());
         this.matcher().appendTail(newsb);
         return newsb.toString();
      }

      default Matcher replace(final String rs) {
         return this.matcher().appendReplacement(this.scala$util$matching$Regex$Replacement$$sb(), rs);
      }

      static void $init$(final Replacement $this) {
         $this.scala$util$matching$Regex$Replacement$_setter_$scala$util$matching$Regex$Replacement$$sb_$eq(new StringBuffer());
      }
   }
}
