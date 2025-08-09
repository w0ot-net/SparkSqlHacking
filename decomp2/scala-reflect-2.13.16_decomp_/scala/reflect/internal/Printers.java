package scala.reflect.internal;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple7;
import scala.Tuple9;
import scala.collection.AbstractIterable;
import scala.collection.IterableFactory;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.WithFilter;
import scala.collection.StringOps.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map;
import scala.collection.mutable.SortedSet;
import scala.collection.mutable.Stack;
import scala.collection.mutable.WeakHashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.package;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.StatisticsStatics;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d\u0015g\u0001DA'\u0003\u001f\u0002\n1!\u0001\u0002^\u001du\u0006bBA9\u0001\u0011\u0005\u00111\u000f\u0005\b\u0003w\u0002A\u0011AA?\u0011\u001d\tY\b\u0001C\u0001\u0003_Cq!a\u001f\u0001\t\u0003\t\u0019\fC\u0004\u00028\u0002!I!!/\t\u000f\u0005=\u0007\u0001\"\u0001\u0002R\"9\u0011q\u001b\u0001\u0005\u0002\u0005e\u0007bBAp\u0001\u0011\u0005\u0011\u0011]\u0003\u0007\u0003O\u0004!!!;\u0007\r\u0005-\b\u0001AAw\u0011)\t\u0019P\u0003B\u0001B\u0003%\u0011Q\u001f\u0005\b\u0005\u000bQA\u0011\u0001B\u0004\u0011%\u0011YA\u0003a\u0001\n#\u0011i\u0001C\u0005\u0003\u0016)\u0001\r\u0011\"\u0005\u0003\u0018!A!Q\u0004\u0006!B\u0013\u0011y\u0001C\u0005\u0003 )\u0011\r\u0011\"\u0005\u0003\u000e!A!\u0011\u0005\u0006!\u0002\u0013\u0011y\u0001C\u0005\u0003$)\u0001\r\u0011\"\u0005\u0003&!I!\u0011\u0007\u0006A\u0002\u0013E!1\u0007\u0005\t\u0005oQ\u0001\u0015)\u0003\u0003(!9!\u0011\b\u0006\u0005\u0002\u0005M\u0004b\u0002B\u001e\u0015\u0011\u0005\u00111\u000f\u0005\b\u0005{QA\u0011\u0003B \u0011\u001d\u0011)E\u0003C\t\u0005\u000fBqAa\u0013\u000b\t#\u0011i\u0005C\u0004\u0003R)!\tAa\u0015\t\u000f\t]#\u0002\"\u0005\u0003Z!9!Q\f\u0006\u0005\u0002\u0005M\u0004b\u0002B0\u0015\u0011\u0015!\u0011\r\u0005\b\u0005gSA\u0011\u0001B[\u0011\u001d\u0011IM\u0003C\u0001\u0005\u0017DqA!3\u000b\t\u0003\u0011)\u000eC\u0004\u0003\\*!\tA!8\t\u000f\t%(\u0002\"\u0001\u0003l\"9!\u0011 \u0006\u0005\u0002\tm\bbBB\u0001\u0015\u0011E11\u0001\u0005\n\u0007/Q\u0011\u0013!C\t\u00073A\u0011ba\u000b\u000b#\u0003%\tb!\f\t\u0013\rE\"\"%A\u0005\u0012\r5\u0002\"CB\u001a\u0015\t\u0007I\u0011CB\u001b\u0011!\u00199D\u0003Q\u0001\n\u0005%\u0006bBB\u001d\u0015\u0011E11\b\u0005\b\u0007\u007fQA\u0011CB!\u0011\u001d\u0019yE\u0003C\u0001\u0007#B\u0011b!\u0017\u000b#\u0003%\ta!\u0007\t\u000f\rm#\u0002\"\u0001\u0004^!911\r\u0006\u0005\u0002\r\u0015\u0004bBB6\u0015\u0011\u00051Q\u000e\u0005\b\u0007cRA\u0011BB:\u0011\u001d\u0019)J\u0003C\u0005\u0007/Cqaa(\u000b\t\u0003\u0019\t\u000bC\u0004\u0004**!\taa+\t\u000f\re&\u0002\"\u0001\u0004<\"911\u001a\u0006\u0005\u0002\r5\u0007\u0002CBl\u0015\u0001\u0006Ka!\"\t\u0011\re'\u0002)Q\u0005\u00077Dqa!:\u000b\t#\u00199\u000fC\u0004\u0004v*!\tba>\t\u000f\u00115!\u0002\"\u0005\u0005\u0010!9A1\u0005\u0006\u0005\u0012\u0011\u0015\u0002b\u0002C\u0016\u0015\u0011EAQ\u0006\u0005\b\twQA\u0011\u0003C\u001f\u0011\u001d!9E\u0003C\t\t\u0013Bq\u0001b\u0016\u000b\t#!I\u0006C\u0005\u0005j)\t\n\u0011\"\u0005\u0004\u001a!9A1\u000e\u0006\u0005\u0012\u00115\u0004bBB6\u0015\u0011EA\u0011\u0010\u0005\b\t\u0007SA\u0011\u0001CC\u0011\u001d!II\u0003C\u0001\t\u00173a\u0001b+\u0001\u0001\u00115\u0006BCAz\r\n\u0005\t\u0015!\u0003\u0002v\"QA\u0011\u0017$\u0003\u0002\u0003\u0006I!!+\t\u000f\t\u0015a\t\"\u0001\u00054\"IA1\u0018$C\u0002\u0013EAQ\u0018\u0005\t\t\u001f4\u0005\u0015!\u0003\u0005@\"9A\u0011\u001b$\u0005\u0012\u0011M\u0007b\u0002Cn\r\u0012EA1\u001b\u0005\b\t;4E\u0011\u0003Cp\u0011%!)ORI\u0001\n#\u0019I\u0002C\u0004\u0005h\u001a#\t\u0002\";\t\u0013\rMbI1A\u0005R\rU\u0002\u0002CB\u001c\r\u0002\u0006I!!+\t\u000f\u0011Eh\t\"\u0005\u0005t\"IQq\u0003$\u0012\u0002\u0013EQ\u0011\u0004\u0005\n\u000b;1\u0015\u0013!C\t\u000b?A\u0011\"b\tG#\u0003%\t\"\"\n\t\u0013\u0015%b)%A\u0005\u0012\u0015-\u0002\"CC\u0018\rF\u0005I\u0011CC\u0019\u0011%))DRI\u0001\n#)9\u0004C\u0005\u0006<\u0019\u000b\n\u0011\"\u0005\u0006>!9Q\u0011\t$\u0005\u0012\u0015\rsaBC$\r\"\u0005Q\u0011\n\u0004\b\u000b\u001b2\u0005\u0012AC(\u0011\u001d\u0011)!\u0018C\u0001\u000b#Bq!b\u0015^\t\u0003))\u0006C\u0004\u0006b\u0019#\t\"b\u0019\t\u000f\u0015\u001dd\t\"\u0005\u0006j!IQ\u0011\u0010$C\u0002\u0013\u0005Q1\u0010\u0005\t\u000b\u000b3\u0005\u0015!\u0003\u0006~!IQq\u0011$C\u0002\u0013\u0005Q1\u0010\u0005\t\u000b\u00133\u0005\u0015!\u0003\u0006~!9Q1\u0012$\u0005\u0012\u00155\u0005\"CCP\rF\u0005I\u0011CCQ\u0011\u001d)9K\u0012C\t\u000bSC\u0011\"b,G#\u0003%\t\"\"-\t\u000f\u0015Mf\t\"\u0005\u00066\"91q\u0014$\u0005B\u0015e\u0006b\u0002BZ\r\u0012\u0005Sq\u0018\u0005\b\u0007s3E\u0011ACe\u0011%)\tNRI\u0001\n\u0003\u0019I\u0002C\u0004\u0004*\u001a#\t%b5\t\u000f\r%f\t\"\u0001\u0006Z\"911\f$\u0005\u0002\u0015}\u0007bBB2\r\u0012\u0005QQ\u001d\u0005\b\u000772E\u0011ICv\u0011\u001d\u0019\u0019G\u0012C!\u000b_Dq!b=G\t#))\u0010C\u0004\u0004L\u001a#\t%\"@\t\u000f\u0019\u0005a\t\"\u0005\u0007\u0004!9A1\u0011$\u0005B\u0019\u001d\u0001b\u0002D\u0006\r\u0012\u0005aQ\u0002\u0005\b\r#\u0001A\u0011\u0001D\n\u0011\u001d1Y\u0002\u0001C\u0001\r;AqAb\n\u0001\t\u00031I\u0003C\u0004\u0007(\u0001!\tA\"\f\t\u000f\u0019\u001d\u0002\u0001\"\u0001\u0007:\u001d9a1\b\u0001\t\u0002\u0019uba\u0002D \u0001!\u0005a\u0011\t\u0005\t\u0005\u000b\t\t\u0001\"\u0001\u0007J!Aa1JA\u0001\t\u00032i\u0005\u0003\u0005\u0007L\u0005\u0005A\u0011\u0001D*\u0011!\u0019)\"!\u0001\u0005\u0002\u0005M\u0004\u0002\u0003D7\u0003\u0003!\t!a\u001d\t\u000f\u0019=\u0004\u0001\"\u0001\u0007r\u00191aq\u0011\u0001\u0005\r\u0013C\u0001B!\u0002\u0002\u0010\u0011\u0005a1\u0012\u0005\n\r\u001b\u000by\u0001)A\u0005\r\u001fC\u0001B\"+\u0002\u0010\u0011%a1\u0016\u0005\n\r\u007f\u000by\u0001)A\u0005\r\u0003D\u0001B\"4\u0002\u0010\u0011%aq\u001a\u0005\n\r\u0007\u000by\u0001)A\u0005\r;D\u0001Bb<\u0002\u0010\u0011%a\u0011\u001f\u0005\t\r\u007f\fy\u0001\"\u0001\b\u0002!AqQCA\b\t\u000399\u0002\u0003\u0005\u0005\n\u0006=A\u0011AD\u0017\r\u00191)\b\u0001\u0001\u0007x!Y\u00111_A\u0013\u0005\u0003\u0005\u000b\u0011BA{\u0011!\u0011)!!\n\u0005\u0002\u0019e\u0004\"\u0003D?\u0003K\u0001\u000b\u0015\u0002B\b\u0011%1y(!\n!B\u0013\tI\u000bC\u0005\u0007\u0002\u0006\u0015\u0002\u0015)\u0003\u0002*\"Ia1QA\u0013A\u0003%aQ\u0011\u0005\t\t\u0013\u000b)\u0003\"\u0001\bB!AqQIA\u0013\t\u000399\u0005\u0003\u0006\b`\u0005\u0015\u0012\u0013!C\u0001\u000fCB!b\"\u001a\u0002&E\u0005I\u0011AD4\u0011)9Y'!\n\u0012\u0002\u0013\u0005q\u0011\r\u0005\t\u000f[\n)\u0003\"\u0001\bp!QqQQA\u0013#\u0003%\tab\"\t\u0015\u001d-\u0015QEI\u0001\n\u000399\u0007\u0003\u0006\b\u000e\u0006\u0015\u0012\u0013!C\u0001\u000f\u000fCqa\"%\u0001\t\u00039\u0019\nC\u0004\b\u0012\u0002!\tab&\t\u000f\u001dE\u0005\u0001\"\u0001\b&\"9qQ\u0017\u0001\u0005\u0002\u001d]&\u0001\u0003)sS:$XM]:\u000b\t\u0005E\u00131K\u0001\tS:$XM\u001d8bY*!\u0011QKA,\u0003\u001d\u0011XM\u001a7fGRT!!!\u0017\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M)\u0001!a\u0018\u0002hA!\u0011\u0011MA2\u001b\t\t9&\u0003\u0003\u0002f\u0005]#AB!osJ+g\r\u0005\u0003\u0002j\u0005=TBAA6\u0015\u0011\ti'a\u0015\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002N\u0005-\u0014A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002vA!\u0011\u0011MA<\u0013\u0011\tI(a\u0016\u0003\tUs\u0017\u000e^\u0001\u000bcV|G/\u001a3OC6,GCBA@\u0003+\u000b)\u000b\u0005\u0003\u0002\u0002\u0006=e\u0002BAB\u0003\u0017\u0003B!!\"\u0002X5\u0011\u0011q\u0011\u0006\u0005\u0003\u0013\u000bY&\u0001\u0004=e>|GOP\u0005\u0005\u0003\u001b\u000b9&\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003#\u000b\u0019J\u0001\u0004TiJLgn\u001a\u0006\u0005\u0003\u001b\u000b9\u0006C\u0004\u0002\u0018\n\u0001\r!!'\u0002\t9\fW.\u001a\t\u0005\u00037\u000bi*D\u0001\u0001\u0013\u0011\ty*!)\u0003\t9\u000bW.Z\u0005\u0005\u0003G\u000byEA\u0003OC6,7\u000fC\u0004\u0002(\n\u0001\r!!+\u0002\r\u0011,7m\u001c3f!\u0011\t\t'a+\n\t\u00055\u0016q\u000b\u0002\b\u0005>|G.Z1o)\u0011\ty(!-\t\u000f\u0005]5\u00011\u0001\u0002\u001aR!\u0011qPA[\u0011\u001d\t9\n\u0002a\u0001\u0003\u007f\nqb]=n\u001d\u0006lW-\u00138uKJt\u0017\r\u001c\u000b\t\u0003\u007f\nY,!3\u0002L\"9\u0011QX\u0003A\u0002\u0005}\u0016\u0001\u0002;sK\u0016\u0004B!a'\u0002B&!\u00111YAc\u0005\u0011!&/Z3\n\t\u0005\u001d\u0017q\n\u0002\u0006)J,Wm\u001d\u0005\b\u0003/+\u0001\u0019AAM\u0011\u001d\ti-\u0002a\u0001\u0003S\u000bq\u0001Z3d_\u0012,G-\u0001\beK\u000e|G-\u001a3Ts6t\u0015-\\3\u0015\r\u0005}\u00141[Ak\u0011\u001d\tiL\u0002a\u0001\u0003\u007fCq!a&\u0007\u0001\u0004\tI*A\u0004ts6t\u0015-\\3\u0015\r\u0005}\u00141\\Ao\u0011\u001d\til\u0002a\u0001\u0003\u007fCq!a&\b\u0001\u0004\tI*\u0001\bcC\u000e\\\u0017/^8uK\u0012\u0004\u0016\r\u001e5\u0015\t\u0005}\u00141\u001d\u0005\b\u0003KD\u0001\u0019AA`\u0003\u0005!(aE%oi\u0016\u0014h.\u00197Ue\u0016,\u0007K]5oi\u0016\u0014\bcAAN\u0015\tYAK]3f!JLg\u000e^3s'\u0015Q\u0011qLAx!\u0011\tY*!=\n\t\u0005-\u0018qN\u0001\u0004_V$\b\u0003BA|\u0005\u0003i!!!?\u000b\t\u0005m\u0018Q`\u0001\u0003S>T!!a@\u0002\t)\fg/Y\u0005\u0005\u0005\u0007\tIPA\u0006Qe&tGo\u0016:ji\u0016\u0014\u0018A\u0002\u001fj]&$h\b\u0006\u0003\u0002j\n%\u0001bBAz\u0019\u0001\u0007\u0011Q_\u0001\rS:$WM\u001c;NCJ<\u0017N\\\u000b\u0003\u0005\u001f\u0001B!!\u0019\u0003\u0012%!!1CA,\u0005\rIe\u000e^\u0001\u0011S:$WM\u001c;NCJ<\u0017N\\0%KF$B!!\u001e\u0003\u001a!I!1\u0004\b\u0002\u0002\u0003\u0007!qB\u0001\u0004q\u0012\n\u0014!D5oI\u0016tG/T1sO&t\u0007%\u0001\u0006j]\u0012,g\u000e^*uKB\f1\"\u001b8eK:$8\u000b^3qA\u0005a\u0011N\u001c3f]R\u001cFO]5oOV\u0011!q\u0005\t\u0005\u0005S\u0011y#\u0004\u0002\u0003,)!!QFA\u007f\u0003\u0011a\u0017M\\4\n\t\u0005E%1F\u0001\u0011S:$WM\u001c;TiJLgnZ0%KF$B!!\u001e\u00036!I!1D\n\u0002\u0002\u0003\u0007!qE\u0001\u000eS:$WM\u001c;TiJLgn\u001a\u0011\u0002\r%tG-\u001a8u\u0003\u0019)h\u000eZ3oi\u0006i1\r[3dW\u001a{'O\u00117b].$BAa\n\u0003B!9!1I\fA\u0002\u0005%\u0016\u0001B2p]\u0012\fAC\u00197b].4uN](qKJ\fGo\u001c:OC6,G\u0003\u0002B\u0014\u0005\u0013Bq!a&\u0019\u0001\u0004\tI*\u0001\u0007cY\u0006t7NR8s\u001d\u0006lW\r\u0006\u0003\u0003(\t=\u0003bBAL3\u0001\u0007\u0011\u0011T\u0001\u000eaJLg\u000e\u001e)pg&$\u0018n\u001c8\u0015\t\u0005U$Q\u000b\u0005\b\u0003{S\u0002\u0019AA`\u00039\u0001(/\u001b8u)f\u0004Xm]%oM>$B!!\u001e\u0003\\!9\u0011QX\u000eA\u0002\u0005}\u0016a\u00029sS:$HN\\\u0001\taJLg\u000e^*fcV!!1\rBA)\u0011\u0011)Ga%\u0015\t\t\u001d$1\u000f\u000b\u0005\u0003k\u0012I\u0007\u0003\u0005\u0003lu!\t\u0019\u0001B7\u0003!\u0001(/\u001b8ug\u0016\u0004\bCBA1\u0005_\n)(\u0003\u0003\u0003r\u0005]#\u0001\u0003\u001fcs:\fW.\u001a \t\u000f\tUT\u00041\u0001\u0003x\u0005I\u0001O]5oi\u0016dW-\u001c\t\t\u0003C\u0012IH! \u0002v%!!1PA,\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0003\u0003\u0000\t\u0005E\u0002\u0001\u0003\b\u0005\u0007k\"\u0019\u0001BC\u0005\u0005\t\u0015\u0003\u0002BD\u0005\u001b\u0003B!!\u0019\u0003\n&!!1RA,\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!!\u0019\u0003\u0010&!!\u0011SA,\u0005\r\te.\u001f\u0005\b\u0005+k\u0002\u0019\u0001BL\u0003\ta7\u000f\u0005\u0004\u0003\u001a\n}%Q\u0010\b\u0005\u0003C\u0012Y*\u0003\u0003\u0003\u001e\u0006]\u0013a\u00029bG.\fw-Z\u0005\u0005\u0005C\u0013\u0019K\u0001\u0003MSN$(\u0002\u0002BO\u0003/B3!\bBT!\u0011\u0011IKa,\u000e\u0005\t-&\u0002\u0002BW\u0003/\n!\"\u00198o_R\fG/[8o\u0013\u0011\u0011\tLa+\u0003\u000fQ\f\u0017\u000e\u001c:fG\u0006Y\u0001O]5oi\u000e{G.^7o))\t)Ha.\u0003>\n\u0005'Q\u0019\u0005\b\u0005ss\u0002\u0019\u0001B^\u0003\t!8\u000f\u0005\u0004\u0003\u001a\n}\u0015q\u0018\u0005\b\u0005\u007fs\u0002\u0019AA@\u0003\u0015\u0019H/\u0019:u\u0011\u001d\u0011\u0019M\ba\u0001\u0003\u007f\n1a]3q\u0011\u001d\u00119M\ba\u0001\u0003\u007f\n1!\u001a8e\u0003!\u0001(/\u001b8u%><HCCA;\u0005\u001b\u0014yM!5\u0003T\"9!\u0011X\u0010A\u0002\tm\u0006b\u0002B`?\u0001\u0007\u0011q\u0010\u0005\b\u0005\u0007|\u0002\u0019AA@\u0011\u001d\u00119m\ba\u0001\u0003\u007f\"b!!\u001e\u0003X\ne\u0007b\u0002B]A\u0001\u0007!1\u0018\u0005\b\u0005\u0007\u0004\u0003\u0019AA@\u0003=\u0001(/\u001b8u)f\u0004X\rU1sC6\u001cH\u0003BA;\u0005?DqA!/\"\u0001\u0004\u0011\t\u000f\u0005\u0004\u0003\u001a\n}%1\u001d\t\u0005\u00037\u0013)/\u0003\u0003\u0003h\u0006\u0015'a\u0002+za\u0016$UMZ\u0001\u0011aJLg\u000e\u001e'bE\u0016d\u0007+\u0019:b[N$B!!\u001e\u0003n\"9!q\u001e\u0012A\u0002\tE\u0018A\u00019t!\u0019\u0011IJa(\u0003tB!\u00111\u0014B{\u0013\u0011\u001190!2\u0003\u000b%#WM\u001c;\u0002\u001fA\u0014\u0018N\u001c;MC\n,G\u000eU1sC6$B!!\u001e\u0003~\"9!q`\u0012A\u0002\tM\u0018!\u00019\u0002\u0019A\f'/\u001a8uQ\u0016\u001c\u0018N_3\u0015\u0011\r\u001511BB\b\u0007'!B!!\u001e\u0004\b!A1\u0011\u0002\u0013\u0005\u0002\u0004\u0011i'\u0001\u0003c_\u0012L\b\"CB\u0007IA\u0005\t\u0019AAU\u0003%\u0019wN\u001c3ji&|g\u000eC\u0005\u0004\u0012\u0011\u0002\n\u00111\u0001\u0002\u0000\u0005!q\u000e]3o\u0011%\u0019)\u0002\nI\u0001\u0002\u0004\ty(A\u0003dY>\u001cX-\u0001\fqCJ,g\u000e\u001e5fg&TX\r\n3fM\u0006,H\u000e\u001e\u00132+\t\u0019YB\u000b\u0003\u0002*\u000eu1FAB\u0010!\u0011\u0019\tca\n\u000e\u0005\r\r\"\u0002BB\u0013\u0005W\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\n\t\r%21\u0005\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017A\u00069be\u0016tG\u000f[3tSj,G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\r=\"\u0006BA@\u0007;\ta\u0003]1sK:$\b.Z:ju\u0016$C-\u001a4bk2$HeM\u0001\u0011G>lW.\u001a8ugJ+\u0017/^5sK\u0012,\"!!+\u0002#\r|W.\\3oiN\u0014V-];je\u0016$\u0007%A\u0004d_6lWM\u001c;\u0015\t\u0005U4Q\b\u0005\t\u0007\u0013QC\u00111\u0001\u0003n\u0005I\u0002O]5oi&k\u0007\u000f\\5dSRLe\u000eU1sC6\u001cH*[:u)\u0011\t)ha\u0011\t\u000f\r\u00153\u00061\u0001\u0004H\u0005\u0019a\u000fZ:\u0011\r\te%qTB%!\u0011\tYja\u0013\n\t\r5\u0013Q\u0019\u0002\u0007-\u0006dG)\u001a4\u0002!A\u0014\u0018N\u001c;WC2,X\rU1sC6\u001cHCBA;\u0007'\u001a)\u0006C\u0004\u0003:2\u0002\raa\u0012\t\u0013\r]C\u0006%AA\u0002\u0005%\u0016!D5o!\u0006\u0014XM\u001c;iKN,7/\u0001\u000eqe&tGOV1mk\u0016\u0004\u0016M]1ng\u0012\"WMZ1vYR$#'A\u0006qe&tGO\u0016)be\u0006lG\u0003BA;\u0007?Bqa!\u0019/\u0001\u0004\u0019I%\u0001\u0002wI\u0006Y\u0001O]5oiR\u0003\u0016M]1n)\u0011\t)ha\u001a\t\u000f\r%t\u00061\u0001\u0003d\u0006\u0011A\u000fZ\u0001\u000baJLg\u000e\u001e\"m_\u000e\\G\u0003BA;\u0007_Bq!!01\u0001\u0004\ty,A\u0003ts64e.\u0006\u0003\u0004v\reD\u0003CB<\u0007{\u001ayha$\u0011\t\t}4\u0011\u0010\u0003\b\u0007w\n$\u0019\u0001BC\u0005\u0005!\u0006bBA_c\u0001\u0007\u0011q\u0018\u0005\b\u0007\u0003\u000b\u0004\u0019ABB\u0003\u00051\u0007\u0003CA1\u0005s\u001a)ia\u001e\u0011\t\u0005m5qQ\u0005\u0005\u0007\u0013\u001bYI\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0005\u0007\u001b\u000byEA\u0004Ts6\u0014w\u000e\\:\t\u0011\rE\u0015\u0007\"a\u0001\u0007'\u000baa\u001c:FYN,\u0007CBA1\u0005_\u001a9(A\u0003jMNKX\u000e\u0006\u0004\u0002*\u000ee51\u0014\u0005\b\u0003{\u0013\u0004\u0019AA`\u0011\u001d\u0011yP\ra\u0001\u0007;\u0003\u0002\"!\u0019\u0003z\r\u0015\u0015\u0011V\u0001\taJLg\u000e^(qiR1\u0011QOBR\u0007OCqa!*4\u0001\u0004\ty(\u0001\u0004qe\u00164\u0017\u000e\u001f\u0005\b\u0003{\u001b\u0004\u0019AA`\u00039\u0001(/\u001b8u\u001b>$\u0017NZ5feN$b!!\u001e\u0004.\u000e=\u0006bBA_i\u0001\u0007\u0011q\u0018\u0005\b\u0007c#\u0004\u0019ABZ\u0003\u0011iw\u000eZ:\u0011\t\u0005m5QW\u0005\u0005\u0007o\u000b)MA\u0005N_\u0012Lg-[3sg\u0006Q\u0001O]5oi\u001ac\u0017mZ:\u0015\r\u0005U4QXBd\u0011\u001d\u0019y,\u000ea\u0001\u0007\u0003\fQA\u001a7bON\u0004B!!\u0019\u0004D&!1QYA,\u0005\u0011auN\\4\t\u000f\r%W\u00071\u0001\u0002\u0000\u0005i\u0001O]5wCR,w+\u001b;iS:\f\u0001\u0003\u001d:j]R\feN\\8uCRLwN\\:\u0015\t\u0005U4q\u001a\u0005\b\u0003{3\u0004\u0019ABi!\u0011\tYja5\n\t\rU\u0017Q\u0019\u0002\n\u001b\u0016l'-\u001a:EK\u001a\fAbY;se\u0016tGoT<oKJ\fAb]3mK\u000e$xN\u001d+za\u0016\u0004B!a'\u0004^&!1q\\Bq\u0005\u0011!\u0016\u0010]3\n\t\r\r\u0018q\n\u0002\u0006)f\u0004Xm]\u0001\u0010aJLg\u000e\u001e)bG.\fw-\u001a#fMR1\u0011QOBu\u0007cDq!!0:\u0001\u0004\u0019Y\u000f\u0005\u0003\u0002\u001c\u000e5\u0018\u0002BBx\u0003\u000b\u0014!\u0002U1dW\u0006<W\rR3g\u0011\u001d\u0019\u00190\u000fa\u0001\u0003\u007f\n\u0011b]3qCJ\fGo\u001c:\u0002\u0017A\u0014\u0018N\u001c;WC2$UM\u001a\u000b\u0007\u0007s$)\u0001b\u0002\u0015\t\rmH\u0011\u0001\u000b\u0005\u0003k\u001ai\u0010\u0003\u0005\u0004\u0000j\"\t\u0019\u0001B7\u0003!\u0001(/\u001b8u%\"\u001c\b\u0002\u0003C\u0002u\u0011\u0005\rA!\u001c\u0002%A\u0014\u0018N\u001c;UsB,7+[4oCR,(/\u001a\u0005\b\u0003{S\u0004\u0019AB%\u0011!!IA\u000fCA\u0002\u0011-\u0011A\u0003:fgVdGOT1nKB1\u0011\u0011\rB8\u0003\u007f\n1\u0002\u001d:j]R$UM\u001a#fMR1A\u0011\u0003C\r\tC!B\u0001b\u0005\u0005\u0018Q!\u0011Q\u000fC\u000b\u0011!\u0019yp\u000fCA\u0002\t5\u0004\u0002\u0003C\u0002w\u0011\u0005\rA!\u001c\t\u000f\u0005u6\b1\u0001\u0005\u001cA!\u00111\u0014C\u000f\u0013\u0011!y\"!2\u0003\r\u0011+g\rR3g\u0011!!Ia\u000fCA\u0002\u0011-\u0011\u0001\u00049sS:$H+\u001f9f\t\u00164GCBA;\tO!I\u0003C\u0004\u0002>r\u0002\rAa9\t\u0011\u0011%A\b\"a\u0001\t\u0017\t1\u0002\u001d:j]RLU\u000e]8siR1\u0011Q\u000fC\u0018\toAq!!0>\u0001\u0004!\t\u0004\u0005\u0003\u0002\u001c\u0012M\u0012\u0002\u0002C\u001b\u0003\u000b\u0014a!S7q_J$\b\u0002\u0003C\u001d{\u0011\u0005\r\u0001b\u0003\u0002\u0013I,7oU3mK\u000e$\u0018\u0001\u00049sS:$8)Y:f\t\u00164G\u0003BA;\t\u007fAq!!0?\u0001\u0004!\t\u0005\u0005\u0003\u0002\u001c\u0012\r\u0013\u0002\u0002C#\u0003\u000b\u0014qaQ1tK\u0012+g-A\u0007qe&tGOR;oGRLwN\u001c\u000b\u0005\t\u0017\"y\u0005\u0006\u0003\u0002v\u00115\u0003\u0002CB(\u007f\u0011\u0005\rA!\u001c\t\u000f\u0005uv\b1\u0001\u0005RA!\u00111\u0014C*\u0013\u0011!)&!2\u0003\u0011\u0019+hn\u0019;j_:\f!\u0002\u001d:j]R\u001cV\u000f]3s)!\t)\bb\u0017\u0005d\u0011\u0015\u0004bBA_\u0001\u0002\u0007AQ\f\t\u0005\u00037#y&\u0003\u0003\u0005b\u0005\u0015'!B*va\u0016\u0014\b\u0002\u0003C\u0005\u0001\u0012\u0005\r\u0001b\u0003\t\u0013\u0011\u001d\u0004\t%AA\u0002\u0005%\u0016aC2iK\u000e\\7+_7c_2\fA\u0003\u001d:j]R\u001cV\u000f]3sI\u0011,g-Y;mi\u0012\u001a\u0014!\u00039sS:$H\u000b[5t)\u0019\t)\bb\u001c\u0005x!9\u0011Q\u0018\"A\u0002\u0011E\u0004\u0003BAN\tgJA\u0001\"\u001e\u0002F\n!A\u000b[5t\u0011!!IA\u0011CA\u0002\u0011-ACBA;\tw\"y\bC\u0004\u0005~\r\u0003\rAa/\u0002\u000bM$\u0018\r^:\t\u000f\u0011\u00055\t1\u0001\u0002@\u0006!Q\r\u001f9s\u0003%\u0001(/\u001b8u)J,W\r\u0006\u0003\u0002v\u0011\u001d\u0005bBA_\t\u0002\u0007\u0011qX\u0001\u0006aJLg\u000e\u001e\u000b\u0005\u0003k\"i\tC\u0004\u0005\u0010\u0016\u0003\r\u0001\"%\u0002\t\u0005\u0014xm\u001d\t\u0007\u0003C\"\u0019J!$\n\t\u0011U\u0015q\u000b\u0002\u000byI,\u0007/Z1uK\u0012t\u0004f\u0003\u0006\u0005\u001a\u0012}E\u0011\u0015CS\tO\u0003B!!\u0019\u0005\u001c&!AQTA,\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t!\u0019+A\u0010vg\u0016\u0004\u0013J\u001c;fe:\fG\u000e\u0016:fKB\u0013\u0018N\u001c;fe\u0002Jgn\u001d;fC\u0012\fQa]5oG\u0016\f#\u0001\"+\u0002\rIr\u0013g\r\u00185\u0005-\u0019u\u000eZ3Qe&tG/\u001a:\u0014\u0007\u0019#y\u000bE\u0002\u0002\u001c&\tA\u0002\u001d:j]R\u0014vn\u001c;QW\u001e$b\u0001\".\u00058\u0012e\u0006cAAN\r\"9\u00111_%A\u0002\u0005U\bb\u0002CY\u0013\u0002\u0007\u0011\u0011V\u0001\ra\u0006\u0014XM\u001c;t'R\f7m[\u000b\u0003\t\u007f\u0003b\u0001\"1\u0005L\u0006}VB\u0001Cb\u0015\u0011!)\rb2\u0002\u000f5,H/\u00192mK*!A\u0011ZA,\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\t\u001b$\u0019MA\u0003Ti\u0006\u001c7.A\u0007qCJ,g\u000e^:Ti\u0006\u001c7\u000eI\u0001\fGV\u0014(/\u001a8u)J,W-\u0006\u0002\u0005VB1\u0011\u0011\rCl\u0003\u007fKA\u0001\"7\u0002X\t1q\n\u001d;j_:\fQbY;se\u0016tG\u000fU1sK:$\u0018a\u00039sS:$X\r\u001a(b[\u0016$bAa\n\u0005b\u0012\r\bbBAL\u001d\u0002\u0007\u0011\u0011\u0014\u0005\n\u0003\u001bt\u0005\u0013!a\u0001\u0003S\u000bQ\u0003\u001d:j]R,GMT1nK\u0012\"WMZ1vYR$#'A\u000bjg&sG\u000fT5u/&$\b\u000eR3d_\u0012,Gm\u00149\u0015\r\u0005%F1\u001eCx\u0011\u001d!i\u000f\u0015a\u0001\u0003\u007f\u000bA!];bY\"9\u0011q\u0013)A\u0002\u0005e\u0015\u0001\u00058fK\u0012\u001c\b+\u0019:f]RDWm]3t)\u0011!)0b\u0005\u0015!\u0005%Fq\u001fC~\t\u007f,\u0019!b\u0002\u0006\f\u0015=\u0001\"\u0003C}'B\u0005\t\u0019AAU\u0003!Ign]5eK&3\u0007\"\u0003C\u007f'B\u0005\t\u0019AAU\u0003-Ign]5eK6\u000bGo\u00195\t\u0013\u0015\u00051\u000b%AA\u0002\u0005%\u0016!C5og&$W\r\u0016:z\u0011%))a\u0015I\u0001\u0002\u0004\tI+A\bj]NLG-Z!o]>$\u0018\r^3e\u0011%)Ia\u0015I\u0001\u0002\u0004\tI+A\u0006j]NLG-\u001a\"m_\u000e\\\u0007\"CC\u0007'B\u0005\t\u0019AAU\u00039Ign]5eK2\u000b'-\u001a7EK\u001aD\u0011\"\"\u0005T!\u0003\u0005\r!!+\u0002\u0019%t7/\u001b3f\u0003N\u001c\u0018n\u001a8\t\u000f\u0015U1\u000b1\u0001\u0002@\u00061\u0001/\u0019:f]R\f!D\\3fIN\u0004\u0016M]3oi\",7/Z:%I\u00164\u0017-\u001e7uII\"Baa\u0007\u0006\u001c!9QQ\u0003+A\u0002\u0005}\u0016A\u00078fK\u0012\u001c\b+\u0019:f]RDWm]3tI\u0011,g-Y;mi\u0012\u001aD\u0003BB\u000e\u000bCAq!\"\u0006V\u0001\u0004\ty,\u0001\u000eoK\u0016$7\u000fU1sK:$\b.Z:fg\u0012\"WMZ1vYR$C\u0007\u0006\u0003\u0004\u001c\u0015\u001d\u0002bBC\u000b-\u0002\u0007\u0011qX\u0001\u001b]\u0016,Gm\u001d)be\u0016tG\u000f[3tKN$C-\u001a4bk2$H%\u000e\u000b\u0005\u00077)i\u0003C\u0004\u0006\u0016]\u0003\r!a0\u000259,W\rZ:QCJ,g\u000e\u001e5fg\u0016\u001cH\u0005Z3gCVdG\u000f\n\u001c\u0015\t\rmQ1\u0007\u0005\b\u000b+A\u0006\u0019AA`\u0003iqW-\u001a3t!\u0006\u0014XM\u001c;iKN,7\u000f\n3fM\u0006,H\u000e\u001e\u00138)\u0011\u0019Y\"\"\u000f\t\u000f\u0015U\u0011\f1\u0001\u0002@\u0006Qb.Z3egB\u000b'/\u001a8uQ\u0016\u001cXm\u001d\u0013eK\u001a\fW\u000f\u001c;%qQ!11DC \u0011\u001d))B\u0017a\u0001\u0003\u007f\u000bQB]3t_24XmU3mK\u000e$H\u0003BA@\u000b\u000bBq!!:\\\u0001\u0004\ty,A\u0007F[B$\u0018\u0010V=qKR\u0013X-\u001a\t\u0004\u000b\u0017jV\"\u0001$\u0003\u001b\u0015k\u0007\u000f^=UsB,GK]3f'\ri\u0016q\f\u000b\u0003\u000b\u0013\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002*\u0016]\u0003bBC-?\u0002\u0007Q1L\u0001\u0003iR\u0004B!a'\u0006^%!QqLAc\u0005!!\u0016\u0010]3Ue\u0016,\u0017aC5t\u000b6\u0004H/\u001f+sK\u0016$B!!+\u0006f!9\u0011Q\u00181A\u0002\u0005}\u0016!E8sS\u001eLg.\u00197UsB,GK]3fgR!Q1NC;!\u0019)i'b\u001d\u0002@6\u0011Qq\u000e\u0006\u0005\u000bc\"9-A\u0005j[6,H/\u00192mK&!!\u0011UC8\u0011\u001d)9(\u0019a\u0001\u0005w\u000bQ\u0001\u001e:fKN\fa\u0002Z3gCVdGo\u00117bgN,7/\u0006\u0002\u0006~A1QQNC:\u000b\u007f\u0002B!a'\u0006\u0002&!Q1QAQ\u0005!!\u0016\u0010]3OC6,\u0017a\u00043fM\u0006,H\u000e^\"mCN\u001cXm\u001d\u0011\u0002)\u0011,g-Y;miR\u0013\u0018-\u001b;t\r>\u00148)Y:f\u0003U!WMZ1vYR$&/Y5ug\u001a{'oQ1tK\u0002\n!D]3n_Z,G)\u001a4bk2$H+\u001f9fg\u001a\u0013x.\u001c'jgR$B!b$\u0006\u001eR!Q\u0011SCM)\u0011\u0011Y,b%\t\u000f\u0015Ue\r1\u0001\u0006\u0018\u0006qAO]1jiN$vNU3n_Z,\u0007C\u0002BM\u0005?\u000bI\nC\u0005\u0006\u001c\u001a\u0004\n\u00111\u0001\u0006\u0018\u0006y1\r\\1tg\u0016\u001cHk\u001c*f[>4X\rC\u0004\u0006x\u0019\u0004\rAa/\u0002II,Wn\u001c<f\t\u00164\u0017-\u001e7u)f\u0004Xm\u001d$s_6d\u0015n\u001d;%I\u00164\u0017-\u001e7uII\"B!b)\u0006&*\"QqSB\u000f\u0011\u001d)9h\u001aa\u0001\u0005w\u000bAD]3n_Z,G)\u001a4bk2$8\t\\1tg\u0016\u001chI]8n\u0019&\u001cH\u000f\u0006\u0004\u0006l\u0015-VQ\u0016\u0005\b\u000boB\u0007\u0019\u0001B^\u0011%)Y\n\u001bI\u0001\u0002\u0004)9*\u0001\u0014sK6|g/\u001a#fM\u0006,H\u000e^\"mCN\u001cXm\u001d$s_6d\u0015n\u001d;%I\u00164\u0017-\u001e7uII*\"!b)\u0002#MLh\u000e\u001e5fi&\u001cGk\u001c*f[>4X\r\u0006\u0003\u0002*\u0016]\u0006bBA_U\u0002\u0007\u0011q\u0018\u000b\u0007\u0003k*Y,\"0\t\u000f\r\u00156\u000e1\u0001\u0002\u0000!9\u0011QX6A\u0002\u0005}FCCA;\u000b\u0003,\u0019-\"2\u0006H\"9!\u0011\u00187A\u0002\tm\u0006b\u0002B`Y\u0002\u0007\u0011q\u0010\u0005\b\u0005\u0007d\u0007\u0019AA@\u0011\u001d\u00119\r\u001ca\u0001\u0003\u007f\"b!!\u001e\u0006L\u00165\u0007bBBY[\u0002\u000711\u0017\u0005\n\u000b\u001fl\u0007\u0013!a\u0001\u0003S\u000b\u0001\u0003\u001d:j[\u0006\u0014\u0018p\u0011;peB\u000b'/Y7\u0002)A\u0014\u0018N\u001c;GY\u0006<7\u000f\n3fM\u0006,H\u000e\u001e\u00133)\u0019\t)(\"6\u0006X\"9\u0011QX8A\u0002\u0005}\u0006bBBY_\u0002\u000711\u0017\u000b\u0007\u0003k*Y.\"8\t\u000f\rE\u0006\u000f1\u0001\u00044\"9Qq\u001a9A\u0002\u0005%FCBA;\u000bC,\u0019\u000fC\u0004\u0004bE\u0004\ra!\u0013\t\u000f\u0015=\u0017\u000f1\u0001\u0002*R1\u0011QOCt\u000bSDqa!\u001bs\u0001\u0004\u0011\u0019\u000fC\u0004\u0006PJ\u0004\r!!+\u0015\t\u0005UTQ\u001e\u0005\b\u0007C\u001a\b\u0019AB%)\u0011\t)(\"=\t\u000f\r%D\u000f1\u0001\u0003d\u0006Q\u0001O]5oi\u0006\u0013xm]:\u0015\t\u0005UTq\u001f\u0005\b\u000bs,\b\u0019AC~\u0003\u0015\t'oZ:t!\u0019\u0011IJa(\u0003<R!\u0011QOC\u0000\u0011\u001d\tiL\u001ea\u0001\u0007#\f!\u0002\u001d:j]R\feN\\8u)\u0011\t)H\"\u0002\t\u000f\u0005uv\u000f1\u0001\u0002@R!\u0011Q\u000fD\u0005\u0011\u001d\ti\f\u001fa\u0001\u0003\u007f\u000b1\u0003\u001d:pG\u0016\u001c8\u000f\u0016:fKB\u0013\u0018N\u001c;j]\u001e$B!!\u001e\u0007\u0010!9\u0011QX=A\u0002\u0005}\u0016A\u0003=qe&tG\u000f\u0016:fKR1\u0011Q\u000fD\u000b\r3AqAb\u0006{\u0001\u0004!y+A\u0006ue\u0016,\u0007K]5oi\u0016\u0014\bbBA_u\u0002\u0007\u0011qX\u0001\u000f]\u0016<8i\u001c3f!JLg\u000e^3s)!!yKb\b\u0007$\u0019\u0015\u0002b\u0002D\u0011w\u0002\u0007\u0011Q_\u0001\u0007oJLG/\u001a:\t\u000f\u0005u6\u00101\u0001\u0002@\"9A\u0011W>A\u0002\u0005%\u0016A\u00048foR\u0013X-\u001a)sS:$XM\u001d\u000b\u0005\t_3Y\u0003C\u0004\u0007\"q\u0004\r!!>\u0015\t\u0011=fq\u0006\u0005\b\rci\b\u0019\u0001D\u001a\u0003\u0019\u0019HO]3b[B!\u0011q\u001fD\u001b\u0013\u001119$!?\u0003\u0019=+H\u000f];u'R\u0014X-Y7\u0015\u0005\u0011=\u0016!D\"p]N|G.Z,sSR,'\u000f\u0005\u0003\u0002\u001c\u0006\u0005!!D\"p]N|G.Z,sSR,'o\u0005\u0003\u0002\u0002\u0019\r\u0003\u0003BA|\r\u000bJAAb\u0012\u0002z\n1qK]5uKJ$\"A\"\u0010\u0002\u000b]\u0014\u0018\u000e^3\u0015\t\u0005Udq\n\u0005\t\r#\n)\u00011\u0001\u0002\u0000\u0005\u00191\u000f\u001e:\u0015\u0011\u0005UdQ\u000bD3\rSB\u0001Bb\u0016\u0002\b\u0001\u0007a\u0011L\u0001\u0005G\n,h\r\u0005\u0004\u0002b\u0019mcqL\u0005\u0005\r;\n9FA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0002b\u0019\u0005\u0014\u0002\u0002D2\u0003/\u0012Aa\u00115be\"AaqMA\u0004\u0001\u0004\u0011y!A\u0002pM\u001aD\u0001Bb\u001b\u0002\b\u0001\u0007!qB\u0001\u0004Y\u0016t\u0017!\u00024mkND\u0017!\u00058foJ\u000bw\u000f\u0016:fKB\u0013\u0018N\u001c;feR!a1ODH!\u0011\tY*!\n\u0003\u001dI\u000bw\u000f\u0016:fKB\u0013\u0018N\u001c;feN1\u0011QEA0\u0003_$BAb\u001d\u0007|!A\u00111_A\u0015\u0001\u0004\t)0A\u0003eKB$\b.A\u000bqe&tG\u000fV=qKNLeNR8pi:|G/Z:\u0002#A\u0014\u0018N\u001c;j]\u001e4un\u001c;o_R,7/A\u0005g_>$hn\u001c;fgB!\u00111TA\b\u0005%1un\u001c;o_R,7o\u0005\u0003\u0002\u0010\u0005}CC\u0001DC\u0003\u0015Ig\u000eZ3y!!!\tM\"%\u0007\u0016\u001a\r\u0016\u0002\u0002DJ\t\u0007\u00141!T1qa\u001119Jb(\u0011\r\t%b\u0011\u0014DO\u0013\u00111YJa\u000b\u0003\u000b\rc\u0017m]:\u0011\t\t}dq\u0014\u0003\r\rC\u000b\u0019\"!A\u0001\u0002\u000b\u0005!Q\u0011\u0002\u0004?\u0012\n\u0004\u0003\u0003Ca\rK\u0013iIa\u0004\n\t\u0019\u001dF1\u0019\u0002\f/\u0016\f7\u000eS1tQ6\u000b\u0007/\u0001\u0006dY\u0006\u001c8/\u00138eKb,BA\",\u0007>R!a1\u0015DX\u0011)1\t,!\u0006\u0002\u0002\u0003\u000fa1W\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004C\u0002D[\ro3Y,\u0004\u0002\u0002T%!a\u0011XA*\u0005!\u0019E.Y:t)\u0006<\u0007\u0003\u0002B@\r{#\u0001ba\u001f\u0002\u0016\t\u0007!QQ\u0001\tG>,h\u000e^3sgBAA\u0011\u0019DI\r\u0007\u0014y\u0001\r\u0003\u0007F\u001a%\u0007C\u0002B\u0015\r339\r\u0005\u0003\u0003\u0000\u0019%G\u0001\u0004Df\u0003/\t\t\u0011!A\u0003\u0002\t\u0015%aA0%e\u0005Ya.\u001a=u\u0007>,h\u000e^3s+\u00111\tNb7\u0015\t\t=a1\u001b\u0005\u000b\r+\fI\"!AA\u0004\u0019]\u0017AC3wS\u0012,gnY3%eA1aQ\u0017D\\\r3\u0004BAa \u0007\\\u0012A11PA\r\u0005\u0004\u0011)\t\u0005\u0005\u0005B\u001aEeq\u001cDua\u00111\tO\":\u0011\r\t%b\u0011\u0014Dr!\u0011\u0011yH\":\u0005\u0019\u0019\u001d\u00181DA\u0001\u0002\u0003\u0015\tA!\"\u0003\u0007}#3\u0007\u0005\u0004\u0005B\u001a-(qB\u0005\u0005\r[$\u0019MA\u0005T_J$X\rZ*fi\u0006q1\r\\1tg\u001a{w\u000e\u001e8pi\u0016\u001cX\u0003\u0002Dz\r{$BA\";\u0007v\"Qaq_A\u000f\u0003\u0003\u0005\u001dA\"?\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\u00076\u001a]f1 \t\u0005\u0005\u007f2i\u0010\u0002\u0005\u0004|\u0005u!\u0019\u0001BC\u0003\r\u0001X\u000f^\u000b\u0005\u000f\u00079y\u0001\u0006\u0003\b\u0006\u001dEA\u0003\u0002B\b\u000f\u000fA!b\"\u0003\u0002 \u0005\u0005\t9AD\u0006\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0007\rk39l\"\u0004\u0011\t\t}tq\u0002\u0003\t\u0007w\nyB1\u0001\u0003\u0006\"Aq1CA\u0010\u0001\u00049i!A\u0002b]f\f1aZ3u+\u00119Ibb\u000b\u0015\t\u001dmq1\u0005\t\u0007\u00053\u0013yj\"\b\u0011\u0011\u0005\u0005tq\u0004B\b\u0005\u001bKAa\"\t\u0002X\t1A+\u001e9mKJB!b\"\n\u0002\"\u0005\u0005\t9AD\u0014\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0007\rk39l\"\u000b\u0011\t\t}t1\u0006\u0003\t\u0007w\n\tC1\u0001\u0003\u0006V!qqFD\u001e)\u00119\td\"\u0010\u0015\t\u0005Ut1\u0007\u0005\u000b\u000fk\t\u0019#!AA\u0004\u001d]\u0012AC3wS\u0012,gnY3%mA1aQ\u0017D\\\u000fs\u0001BAa \b<\u0011A11PA\u0012\u0005\u0004\u0011)\t\u0003\u0005\b@\u0005\r\u0002\u0019AAx\u0003\u001d\u0001(/\u001b8uKJ$B!!\u001e\bD!AAqRA\u001a\u0001\u0004!\t*\u0001\u0007qe&tG\u000f\u0015:pIV\u001cG\u000f\u0006\u0006\u0002v\u001d%s\u0011KD,\u000f7B\u0001Ba@\u00026\u0001\u0007q1\n\t\u0005\u0003C:i%\u0003\u0003\bP\u0005]#a\u0002)s_\u0012,8\r\u001e\u0005\u000b\u000f'\n)\u0004%AA\u0002\u001dU\u0013\u0001\u00039sK\u0006l'\r\\3\u0011\u0011\u0005\u0005$\u0011PD&\u0003kB!b!\u0003\u00026A\u0005\t\u0019AD-!!\t\tG!\u001f\u0003\u000e\u0006U\u0004BCD/\u0003k\u0001\n\u00111\u0001\bV\u0005I\u0001o\\:uC6\u0014G.Z\u0001\u0017aJLg\u000e\u001e)s_\u0012,8\r\u001e\u0013eK\u001a\fW\u000f\u001c;%eU\u0011q1\r\u0016\u0005\u000f+\u001ai\"\u0001\fqe&tG\u000f\u0015:pIV\u001cG\u000f\n3fM\u0006,H\u000e\u001e\u00134+\t9IG\u000b\u0003\bZ\ru\u0011A\u00069sS:$\bK]8ek\u000e$H\u0005Z3gCVdG\u000f\n\u001b\u0002\u001bA\u0014\u0018N\u001c;Ji\u0016\u0014\u0018M\u00197f))\t)h\"\u001d\b\u0000\u001d\u0005u1\u0011\u0005\t\u000fg\ni\u00041\u0001\bv\u0005A\u0011\u000e^3sC\ndW\r\r\u0003\bx\u001dm\u0004C\u0002BM\u0005?;I\b\u0005\u0003\u0003\u0000\u001dmD\u0001DD?\u000fc\n\t\u0011!A\u0003\u0002\t\u0015%aA0%m!Qq1KA\u001f!\u0013\u0005\rA!\u001c\t\u0015\r%\u0011Q\bI\u0001\u0002\u00049I\u0006\u0003\u0006\b^\u0005u\u0002\u0013\"a\u0001\u0005[\nq\u0003\u001d:j]RLE/\u001a:bE2,G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u001d%%\u0006BA;\u0007;\tq\u0003\u001d:j]RLE/\u001a:bE2,G\u0005Z3gCVdG\u000fJ\u001a\u0002/A\u0014\u0018N\u001c;Ji\u0016\u0014\u0018M\u00197fI\u0011,g-Y;mi\u0012\"\u0004\u0002\u0003D\u0011\u0003\u001b\u0001\r!!>\u0002\tMDwn\u001e\u000b\u0005\u0003\u007f:)\n\u0003\u0005\u0002\u0018\u0006\u0015\u0003\u0019AAM)\u0011\tyh\"'\t\u0011\r}\u0016q\ta\u0001\u000f7\u0003B!a'\b\u001e&!qqTDQ\u0005\u001d1E.Y4TKRLAab)\u0002P\tAa\t\\1h'\u0016$8\u000f\u0006\u0003\u0002\u0000\u001d\u001d\u0006\u0002CDU\u0003\u0013\u0002\rab+\u0002\u0011A|7/\u001b;j_:\u0004B!a'\b.&!qqVDY\u0005!\u0001vn]5uS>t\u0017\u0002BDZ\u0003\u001f\u0012\u0011\u0002U8tSRLwN\\:\u0002\u0011MDwn\u001e#fG2$B!a \b:\"Aq1XA&\u0001\u0004\u0019))A\u0002ts6\u0004Bab0\bB6\u0011\u0011qJ\u0005\u0005\u000f\u0007\fyEA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007"
)
public interface Printers extends scala.reflect.api.Printers {
   ConsoleWriter$ ConsoleWriter();

   // $FF: synthetic method
   static String quotedName$(final Printers $this, final Names.Name name, final boolean decode) {
      return $this.quotedName(name, decode);
   }

   default String quotedName(final Names.Name name, final boolean decode) {
      String s = decode ? name.decode() : name.toString();
      Names.TermName term = name.toTermName();
      Set var10000 = ((StdNames)this).nme().keywords();
      if (var10000 == null) {
         throw null;
      } else {
         if (var10000.contains(term)) {
            Names.TermName var5 = ((StdNames)this).nme().USCOREkw();
            if (term == null) {
               if (var5 != null) {
                  return .MODULE$.format$extension("`%s`", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{s}));
               }
            } else if (!term.equals(var5)) {
               return .MODULE$.format$extension("`%s`", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{s}));
            }
         }

         return s;
      }
   }

   // $FF: synthetic method
   static String quotedName$(final Printers $this, final Names.Name name) {
      return $this.quotedName(name);
   }

   default String quotedName(final Names.Name name) {
      return this.quotedName(name, false);
   }

   // $FF: synthetic method
   static String quotedName$(final Printers $this, final String name) {
      return $this.quotedName(name);
   }

   default String quotedName(final String name) {
      return this.quotedName(((Names)this).newTermName(name), false);
   }

   private String symNameInternal(final Trees.Tree tree, final Names.Name name, final boolean decoded) {
      Symbols.Symbol sym = tree.symbol();
      if (sym != null && !sym.equals(((Symbols)this).NoSymbol())) {
         if (sym.isErroneous()) {
            return (new StringBuilder(9)).append("<").append(this.qname$1(name, decoded)).append(": error>").toString();
         } else {
            return sym.isMixinConstructor() ? (new StringBuilder(4)).append("/*").append(this.qowner$1(sym, decoded)).append("*/").append(this.qsymbol$1(sym)).toString() : this.qsymbol$1(sym);
         }
      } else {
         return this.qname$1(name, decoded);
      }
   }

   // $FF: synthetic method
   static String decodedSymName$(final Printers $this, final Trees.Tree tree, final Names.Name name) {
      return $this.decodedSymName(tree, name);
   }

   default String decodedSymName(final Trees.Tree tree, final Names.Name name) {
      return this.symNameInternal(tree, name, true);
   }

   // $FF: synthetic method
   static String symName$(final Printers $this, final Trees.Tree tree, final Names.Name name) {
      return $this.symName(tree, name);
   }

   default String symName(final Trees.Tree tree, final Names.Name name) {
      return this.symNameInternal(tree, name, false);
   }

   // $FF: synthetic method
   static String backquotedPath$(final Printers $this, final Trees.Tree t) {
      return $this.backquotedPath(t);
   }

   default String backquotedPath(final Trees.Tree t) {
      boolean var2 = false;
      Trees.Select var3 = null;
      if (t instanceof Trees.Select) {
         var2 = true;
         var3 = (Trees.Select)t;
         Trees.Tree qual = var3.qualifier();
         Names.Name name = var3.name();
         if (name.isTermName()) {
            return (new StringBuilder(1)).append(this.backquotedPath(qual)).append(".").append(this.symName(t, name)).toString();
         }
      }

      if (var2) {
         Trees.Tree qual = var3.qualifier();
         Names.Name name = var3.name();
         if (name.isTypeName()) {
            return (new StringBuilder(1)).append(this.backquotedPath(qual)).append("#").append(this.symName(t, name)).toString();
         }
      }

      if (t instanceof Trees.Ident) {
         Names.Name name = ((Trees.Ident)t).name();
         return this.symName(t, name);
      } else {
         return t.toString();
      }
   }

   // $FF: synthetic method
   static void xprintTree$(final Printers $this, final TreePrinter treePrinter, final Trees.Tree tree) {
      $this.xprintTree(treePrinter, tree);
   }

   default void xprintTree(final TreePrinter treePrinter, final Trees.Tree tree) {
      treePrinter.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(0)).append(tree.productPrefix()).append(tree.productIterator().mkString("(", ", ", ")")).toString()}));
   }

   // $FF: synthetic method
   static TreePrinter newCodePrinter$(final Printers $this, final PrintWriter writer, final Trees.Tree tree, final boolean printRootPkg) {
      return $this.newCodePrinter(writer, tree, printRootPkg);
   }

   default TreePrinter newCodePrinter(final PrintWriter writer, final Trees.Tree tree, final boolean printRootPkg) {
      return (SymbolTable)this.new CodePrinter(writer, printRootPkg);
   }

   // $FF: synthetic method
   static TreePrinter newTreePrinter$(final Printers $this, final PrintWriter writer) {
      return $this.newTreePrinter(writer);
   }

   default TreePrinter newTreePrinter(final PrintWriter writer) {
      return (SymbolTable)this.new TreePrinter(writer);
   }

   // $FF: synthetic method
   static TreePrinter newTreePrinter$(final Printers $this, final OutputStream stream) {
      return $this.newTreePrinter(stream);
   }

   default TreePrinter newTreePrinter(final OutputStream stream) {
      return this.newTreePrinter(new PrintWriter(stream));
   }

   // $FF: synthetic method
   static TreePrinter newTreePrinter$(final Printers $this) {
      return $this.newTreePrinter();
   }

   default TreePrinter newTreePrinter() {
      return this.newTreePrinter(new PrintWriter(this.ConsoleWriter()));
   }

   // $FF: synthetic method
   static RawTreePrinter newRawTreePrinter$(final Printers $this, final PrintWriter writer) {
      return $this.newRawTreePrinter(writer);
   }

   default RawTreePrinter newRawTreePrinter(final PrintWriter writer) {
      return (SymbolTable)this.new RawTreePrinter(writer);
   }

   // $FF: synthetic method
   static String show$(final Printers $this, final Names.Name name) {
      return $this.show(name);
   }

   default String show(final Names.Name name) {
      Names.Name var10000 = ((StdNames)this).tpnme().WILDCARD();
      if (var10000 == null) {
         if (name == null) {
            return "typeNames.WILDCARD";
         }
      } else if (var10000.equals(name)) {
         return "typeNames.WILDCARD";
      }

      var10000 = ((StdNames)this).tpnme().EMPTY();
      if (var10000 == null) {
         if (name == null) {
            return "typeNames.EMPTY";
         }
      } else if (var10000.equals(name)) {
         return "typeNames.EMPTY";
      }

      var10000 = ((StdNames)this).tpnme().ERROR();
      if (var10000 == null) {
         if (name == null) {
            return "typeNames.ERROR";
         }
      } else if (var10000.equals(name)) {
         return "typeNames.ERROR";
      }

      var10000 = ((StdNames)this).tpnme().PACKAGE();
      if (var10000 == null) {
         if (name == null) {
            return "typeNames.PACKAGE";
         }
      } else if (var10000.equals(name)) {
         return "typeNames.PACKAGE";
      }

      Names.TypeName var6 = ((StdNames)this).tpnme().WILDCARD_STAR();
      if (var6 == null) {
         if (name == null) {
            return "typeNames.WILDCARD_STAR";
         }
      } else if (var6.equals(name)) {
         return "typeNames.WILDCARD_STAR";
      }

      var6 = ((StdNames)this).nme().WILDCARD();
      if (var6 == null) {
         if (name == null) {
            return "termNames.WILDCARD";
         }
      } else if (var6.equals(name)) {
         return "termNames.WILDCARD";
      }

      var6 = ((StdNames)this).nme().EMPTY();
      if (var6 == null) {
         if (name == null) {
            return "termNames.EMPTY";
         }
      } else if (var6.equals(name)) {
         return "termNames.EMPTY";
      }

      var6 = ((StdNames)this).nme().ERROR();
      if (var6 == null) {
         if (name == null) {
            return "termNames.ERROR";
         }
      } else if (var6.equals(name)) {
         return "termNames.ERROR";
      }

      var6 = ((StdNames)this).nme().PACKAGE();
      if (var6 == null) {
         if (name == null) {
            return "termNames.PACKAGE";
         }
      } else if (var6.equals(name)) {
         return "termNames.PACKAGE";
      }

      Names.TermName var11 = ((StdNames)this).nme().CONSTRUCTOR();
      if (var11 == null) {
         if (name == null) {
            return "termNames.CONSTRUCTOR";
         }
      } else if (var11.equals(name)) {
         return "termNames.CONSTRUCTOR";
      }

      var11 = ((StdNames)this).nme().ROOTPKG();
      if (var11 == null) {
         if (name == null) {
            return "termNames.ROOTPKG";
         }
      } else if (var11.equals(name)) {
         return "termNames.ROOTPKG";
      }

      String prefix = name.isTermName() ? "TermName(\"" : "TypeName(\"";
      return (new StringBuilder(2)).append(prefix).append(name.toString()).append("\")").toString();
   }

   // $FF: synthetic method
   static String show$(final Printers $this, final long flags) {
      return $this.show(flags);
   }

   default String show(final long flags) {
      if (flags == ((FlagSets)this).NoFlags()) {
         return ((StdNames)this).nme().NoFlags().toString();
      } else {
         ListBuffer s_flags = new ListBuffer();
         RichInt var10000 = scala.runtime.RichInt..MODULE$;
         byte var4 = 0;
         int to$extension_end = 63;
         Range var13 = scala.collection.immutable.Range..MODULE$;
         Range.Inclusive var14 = new Range.Inclusive(var4, to$extension_end, 1);
         JFunction1.mcZI.sp withFilter_p = (i) -> hasFlag$1(flags, 1L << i);
         AbstractIterable withFilter_this = var14;
         IterableOps.WithFilter var15 = new IterableOps.WithFilter(withFilter_this, withFilter_p);
         withFilter_this = null;
         Object var12 = null;
         ((WithFilter)var15).foreach((i) -> $anonfun$show$2(s_flags, BoxesRunTime.unboxToInt(i)));
         String mkString_sep = " | ";
         String mkString_end = "";
         String mkString_start = "";
         return IterableOnceOps.mkString$(s_flags, mkString_start, mkString_sep, mkString_end);
      }
   }

   // $FF: synthetic method
   static String show$(final Printers $this, final Position position) {
      return $this.show(position);
   }

   default String show(final Position position) {
      return position.show();
   }

   // $FF: synthetic method
   static String showDecl$(final Printers $this, final Symbols.Symbol sym) {
      return $this.showDecl(sym);
   }

   default String showDecl(final Symbols.Symbol sym) {
      if (!((SymbolTable)this).isCompilerUniverse()) {
         ((Definitions)this).definitions().fullyInitializeSymbol(sym);
      }

      return sym.defString();
   }

   private String qname$1(final Names.Name name$1, final boolean decoded$1) {
      return this.quotedName(name$1.dropLocal(), decoded$1);
   }

   private String qowner$1(final Symbols.Symbol sym$1, final boolean decoded$1) {
      return this.quotedName(sym$1.owner().name().dropLocal(), decoded$1);
   }

   private String qsymbol$1(final Symbols.Symbol sym$1) {
      return this.quotedName(sym$1.nameString());
   }

   private static boolean hasFlag$1(final long left, final long right) {
      return (left & right) != 0L;
   }

   // $FF: synthetic method
   static ListBuffer $anonfun$show$2(final ListBuffer s_flags$1, final int i) {
      Object $plus$eq_elem = Flags$.MODULE$.flagToString(1L << i).replace("<", "").replace(">", "").toUpperCase();
      return (ListBuffer)s_flags$1.addOne($plus$eq_elem);
   }

   static void $init$(final Printers $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   /** @deprecated */
   public class TreePrinter implements scala.reflect.api.Printers.TreePrinter {
      private final PrintWriter out;
      private int indentMargin;
      private final int indentStep;
      private String indentString;
      private final boolean commentsRequired;
      private Symbols.Symbol currentOwner;
      private Types.Type selectorType;
      private boolean printTypes;
      private boolean printIds;
      private boolean printOwners;
      private boolean printKinds;
      private boolean printMirrors;
      private boolean printPositions;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public scala.reflect.api.Printers.TreePrinter withTypes() {
         return scala.reflect.api.Printers.TreePrinter.withTypes$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutTypes() {
         return scala.reflect.api.Printers.TreePrinter.withoutTypes$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withIds() {
         return scala.reflect.api.Printers.TreePrinter.withIds$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutIds() {
         return scala.reflect.api.Printers.TreePrinter.withoutIds$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withOwners() {
         return scala.reflect.api.Printers.TreePrinter.withOwners$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutOwners() {
         return scala.reflect.api.Printers.TreePrinter.withoutOwners$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withKinds() {
         return scala.reflect.api.Printers.TreePrinter.withKinds$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutKinds() {
         return scala.reflect.api.Printers.TreePrinter.withoutKinds$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withMirrors() {
         return scala.reflect.api.Printers.TreePrinter.withMirrors$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutMirrors() {
         return scala.reflect.api.Printers.TreePrinter.withoutMirrors$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withPositions() {
         return scala.reflect.api.Printers.TreePrinter.withPositions$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutPositions() {
         return scala.reflect.api.Printers.TreePrinter.withoutPositions$(this);
      }

      public boolean printTypes() {
         return this.printTypes;
      }

      public void printTypes_$eq(final boolean x$1) {
         this.printTypes = x$1;
      }

      public boolean printIds() {
         return this.printIds;
      }

      public void printIds_$eq(final boolean x$1) {
         this.printIds = x$1;
      }

      public boolean printOwners() {
         return this.printOwners;
      }

      public void printOwners_$eq(final boolean x$1) {
         this.printOwners = x$1;
      }

      public boolean printKinds() {
         return this.printKinds;
      }

      public void printKinds_$eq(final boolean x$1) {
         this.printKinds = x$1;
      }

      public boolean printMirrors() {
         return this.printMirrors;
      }

      public void printMirrors_$eq(final boolean x$1) {
         this.printMirrors = x$1;
      }

      public boolean printPositions() {
         return this.printPositions;
      }

      public void printPositions_$eq(final boolean x$1) {
         this.printPositions = x$1;
      }

      public int indentMargin() {
         return this.indentMargin;
      }

      public void indentMargin_$eq(final int x$1) {
         this.indentMargin = x$1;
      }

      public int indentStep() {
         return this.indentStep;
      }

      public String indentString() {
         return this.indentString;
      }

      public void indentString_$eq(final String x$1) {
         this.indentString = x$1;
      }

      public void indent() {
         this.indentMargin_$eq(this.indentMargin() + this.indentStep());
      }

      public void undent() {
         this.indentMargin_$eq(this.indentMargin() - this.indentStep());
      }

      public String checkForBlank(final boolean cond) {
         return cond ? " " : "";
      }

      public String blankForOperatorName(final Names.Name name) {
         return this.checkForBlank(name.isOperatorName());
      }

      public String blankForName(final Names.Name name) {
         return this.checkForBlank(name.isOperatorName() || name.endsWith("_"));
      }

      public void printPosition(final Trees.Tree tree) {
         if (this.printPositions()) {
            this.comment((JFunction0.mcV.sp)() -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tree.pos().show()})));
         }
      }

      public void printTypesInfo(final Trees.Tree tree) {
         if (this.printTypes() && tree.isTerm() && tree.canHaveAttrs()) {
            this.comment((JFunction0.mcV.sp)() -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"{", tree.tpe() == null ? "<null>" : tree.tpe().toString(), "}"})));
         }
      }

      public void println() {
         this.out.println();

         while(this.indentMargin() > this.indentString().length()) {
            this.indentString_$eq((new StringBuilder(0)).append(this.indentString()).append(this.indentString()).toString());
         }

         if (this.indentMargin() > 0) {
            this.out.write(this.indentString(), 0, this.indentMargin());
         }
      }

      public final void printSeq(final List ls, final Function1 printelem, final Function0 printsep) {
         while(true) {
            if (ls != null) {
               List var10000 = scala.package..MODULE$.List();
               if (var10000 == null) {
                  throw null;
               }

               List unapplySeq_this = var10000;
               SeqOps var17 = SeqFactory.unapplySeq$(unapplySeq_this, ls);
               Object var15 = null;
               SeqOps var4 = var17;
               SeqFactory.UnapplySeqWrapper var18 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var4);
               var18 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var18 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int lengthCompare$extension_len = 0;
               if (var4.lengthCompare(lengthCompare$extension_len) == 0) {
                  return;
               }
            }

            if (ls != null) {
               List var21 = scala.package..MODULE$.List();
               if (var21 == null) {
                  throw null;
               }

               List unapplySeq_this = var21;
               SeqOps var22 = SeqFactory.unapplySeq$(unapplySeq_this, ls);
               Object var16 = null;
               SeqOps var5 = var22;
               SeqFactory.UnapplySeqWrapper var23 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var5);
               var23 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var23 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int lengthCompare$extension_len = 1;
               if (var5.lengthCompare(lengthCompare$extension_len) == 0) {
                  var23 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var23 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int apply$extension_i = 0;
                  Object x = var5.apply(apply$extension_i);
                  BoxedUnit var28 = (BoxedUnit)printelem.apply(x);
                  return;
               }
            }

            if (!(ls instanceof scala.collection.immutable..colon.colon)) {
               throw new MatchError(ls);
            }

            scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)ls;
            Object x = var7.head();
            List rest = var7.next$access$1();
            printelem.apply(x);
            printsep.apply$mcV$sp();
            printsep = printsep;
            printelem = printelem;
            ls = rest;
         }
      }

      public void printColumn(final List ts, final String start, final String sep, final String end) {
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{start}));
         this.indent();
         this.println();
         Function1 var10001 = (x$1) -> {
            $anonfun$printColumn$1(this, x$1);
            return BoxedUnit.UNIT;
         };
         JFunction0.mcV.sp printSeq_printsep = () -> {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{sep}));
            this.println();
         };
         Function1 printSeq_printelem = var10001;
         List printSeq_ls = ts;

         while(true) {
            if (printSeq_ls != null) {
               List var10000 = scala.package..MODULE$.List();
               if (var10000 == null) {
                  throw null;
               }

               List printSeq_unapplySeq_this = var10000;
               SeqOps var32 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
               Object var27 = null;
               SeqOps var8 = var32;
               SeqFactory.UnapplySeqWrapper var33 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var43 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var8);
               var33 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var33 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int printSeq_lengthCompare$extension_len = 0;
               if (var8.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                  break;
               }
            }

            if (printSeq_ls != null) {
               List var36 = scala.package..MODULE$.List();
               if (var36 == null) {
                  throw null;
               }

               List printSeq_unapplySeq_this = var36;
               SeqOps var37 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
               Object var29 = null;
               SeqOps var9 = var37;
               SeqFactory.UnapplySeqWrapper var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var44 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var9);
               var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int printSeq_lengthCompare$extension_len = 1;
               if (var9.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                  var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_apply$extension_i = 0;
                  Trees.Tree var31 = (Trees.Tree)var9.apply(printSeq_apply$extension_i);
                  $anonfun$printColumn$1(this, var31);
                  break;
               }
            }

            if (!(printSeq_ls instanceof scala.collection.immutable..colon.colon)) {
               throw new MatchError(printSeq_ls);
            }

            scala.collection.immutable..colon.colon var10 = (scala.collection.immutable..colon.colon)printSeq_ls;
            Object printSeq_x = var10.head();
            List printSeq_rest = var10.next$access$1();
            Trees.Tree var18 = (Trees.Tree)printSeq_x;
            $anonfun$printColumn$1(this, var18);
            $anonfun$printColumn$2(this, sep);
            printSeq_printsep = printSeq_printsep;
            printSeq_printelem = printSeq_printelem;
            printSeq_ls = printSeq_rest;
         }

         Object var19 = null;
         Object var20 = null;
         Object var21 = null;
         Object var22 = null;
         Object var23 = null;
         Object var24 = null;
         Object var25 = null;
         Object var26 = null;
         Object var28 = null;
         Object var30 = null;
         this.undent();
         this.println();
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{end}));
      }

      public void printRow(final List ts, final String start, final String sep, final String end) {
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{start}));
         Function1 var10001 = (x$2) -> {
            $anonfun$printRow$1(this, x$2);
            return BoxedUnit.UNIT;
         };
         JFunction0.mcV.sp printSeq_printsep = () -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{sep}));
         Function1 printSeq_printelem = var10001;
         List printSeq_ls = ts;

         while(true) {
            if (printSeq_ls != null) {
               List var10000 = scala.package..MODULE$.List();
               if (var10000 == null) {
                  throw null;
               }

               List printSeq_unapplySeq_this = var10000;
               SeqOps var32 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
               Object var27 = null;
               SeqOps var8 = var32;
               SeqFactory.UnapplySeqWrapper var33 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var43 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var8);
               var33 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var33 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int printSeq_lengthCompare$extension_len = 0;
               if (var8.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                  break;
               }
            }

            if (printSeq_ls != null) {
               List var36 = scala.package..MODULE$.List();
               if (var36 == null) {
                  throw null;
               }

               List printSeq_unapplySeq_this = var36;
               SeqOps var37 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
               Object var29 = null;
               SeqOps var9 = var37;
               SeqFactory.UnapplySeqWrapper var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var44 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var9);
               var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int printSeq_lengthCompare$extension_len = 1;
               if (var9.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                  var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var38 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_apply$extension_i = 0;
                  Trees.Tree var31 = (Trees.Tree)var9.apply(printSeq_apply$extension_i);
                  $anonfun$printRow$1(this, var31);
                  break;
               }
            }

            if (!(printSeq_ls instanceof scala.collection.immutable..colon.colon)) {
               throw new MatchError(printSeq_ls);
            }

            scala.collection.immutable..colon.colon var10 = (scala.collection.immutable..colon.colon)printSeq_ls;
            Object printSeq_x = var10.head();
            List printSeq_rest = var10.next$access$1();
            Trees.Tree var18 = (Trees.Tree)printSeq_x;
            $anonfun$printRow$1(this, var18);
            $anonfun$printRow$2(this, sep);
            printSeq_printsep = printSeq_printsep;
            printSeq_printelem = printSeq_printelem;
            printSeq_ls = printSeq_rest;
         }

         Object var19 = null;
         Object var20 = null;
         Object var21 = null;
         Object var22 = null;
         Object var23 = null;
         Object var24 = null;
         Object var25 = null;
         Object var26 = null;
         Object var28 = null;
         Object var30 = null;
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{end}));
      }

      public void printRow(final List ts, final String sep) {
         this.printRow(ts, "", sep, "");
      }

      public void printTypeParams(final List ts) {
         if (ts.nonEmpty()) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"["}));
            Function1 var10001 = (t) -> {
               $anonfun$printTypeParams$1(this, t);
               return BoxedUnit.UNIT;
            };
            JFunction0.mcV.sp printSeq_printsep = () -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{", "}));
            Function1 printSeq_printelem = var10001;
            List printSeq_ls = ts;

            while(true) {
               if (printSeq_ls != null) {
                  List var10000 = scala.package..MODULE$.List();
                  if (var10000 == null) {
                     throw null;
                  }

                  List printSeq_unapplySeq_this = var10000;
                  SeqOps var29 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
                  Object var24 = null;
                  SeqOps var5 = var29;
                  SeqFactory.UnapplySeqWrapper var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var40 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var5);
                  var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_lengthCompare$extension_len = 0;
                  if (var5.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                     break;
                  }
               }

               if (printSeq_ls != null) {
                  List var33 = scala.package..MODULE$.List();
                  if (var33 == null) {
                     throw null;
                  }

                  List printSeq_unapplySeq_this = var33;
                  SeqOps var34 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
                  Object var26 = null;
                  SeqOps var6 = var34;
                  SeqFactory.UnapplySeqWrapper var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var41 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var6);
                  var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_lengthCompare$extension_len = 1;
                  if (var6.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                     var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int printSeq_apply$extension_i = 0;
                     Trees.TypeDef var28 = (Trees.TypeDef)var6.apply(printSeq_apply$extension_i);
                     $anonfun$printTypeParams$1(this, var28);
                     break;
                  }
               }

               if (!(printSeq_ls instanceof scala.collection.immutable..colon.colon)) {
                  throw new MatchError(printSeq_ls);
               }

               scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)printSeq_ls;
               Object printSeq_x = var7.head();
               List printSeq_rest = var7.next$access$1();
               Trees.TypeDef var15 = (Trees.TypeDef)printSeq_x;
               $anonfun$printTypeParams$1(this, var15);
               $anonfun$printTypeParams$2(this);
               printSeq_printsep = printSeq_printsep;
               printSeq_printelem = printSeq_printelem;
               printSeq_ls = printSeq_rest;
            }

            Object var16 = null;
            Object var17 = null;
            Object var18 = null;
            Object var19 = null;
            Object var20 = null;
            Object var21 = null;
            Object var22 = null;
            Object var23 = null;
            Object var25 = null;
            Object var27 = null;
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"]"}));
         }
      }

      public void printLabelParams(final List ps) {
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"("}));
         Function1 var10001 = (p) -> {
            $anonfun$printLabelParams$1(this, p);
            return BoxedUnit.UNIT;
         };
         JFunction0.mcV.sp printSeq_printsep = () -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{", "}));
         Function1 printSeq_printelem = var10001;
         List printSeq_ls = ps;

         while(true) {
            if (printSeq_ls != null) {
               List var10000 = scala.package..MODULE$.List();
               if (var10000 == null) {
                  throw null;
               }

               List printSeq_unapplySeq_this = var10000;
               SeqOps var29 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
               Object var24 = null;
               SeqOps var5 = var29;
               SeqFactory.UnapplySeqWrapper var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var40 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var5);
               var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int printSeq_lengthCompare$extension_len = 0;
               if (var5.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                  break;
               }
            }

            if (printSeq_ls != null) {
               List var33 = scala.package..MODULE$.List();
               if (var33 == null) {
                  throw null;
               }

               List printSeq_unapplySeq_this = var33;
               SeqOps var34 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
               Object var26 = null;
               SeqOps var6 = var34;
               SeqFactory.UnapplySeqWrapper var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var41 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var6);
               var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int printSeq_lengthCompare$extension_len = 1;
               if (var6.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                  var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_apply$extension_i = 0;
                  Trees.Ident var28 = (Trees.Ident)var6.apply(printSeq_apply$extension_i);
                  this.printLabelParam(var28);
                  break;
               }
            }

            if (!(printSeq_ls instanceof scala.collection.immutable..colon.colon)) {
               throw new MatchError(printSeq_ls);
            }

            scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)printSeq_ls;
            Object printSeq_x = var7.head();
            List printSeq_rest = var7.next$access$1();
            Trees.Ident var15 = (Trees.Ident)printSeq_x;
            this.printLabelParam(var15);
            $anonfun$printLabelParams$2(this);
            printSeq_printsep = printSeq_printsep;
            printSeq_printelem = printSeq_printelem;
            printSeq_ls = printSeq_rest;
         }

         Object var16 = null;
         Object var17 = null;
         Object var18 = null;
         Object var19 = null;
         Object var20 = null;
         Object var21 = null;
         Object var22 = null;
         Object var23 = null;
         Object var25 = null;
         Object var27 = null;
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{")"}));
      }

      public void printLabelParam(final Trees.Ident p) {
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(p, p.name())}));
         this.printOpt(": ", (this.scala$reflect$internal$Printers$TreePrinter$$$outer().new TypeTree()).setType(p.tpe()));
      }

      public void parenthesize(final boolean condition, final String open, final String close, final Function0 body) {
         if (condition) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{open}));
         }

         body.apply$mcV$sp();
         if (condition) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{close}));
         }
      }

      public boolean parenthesize$default$1() {
         return true;
      }

      public String parenthesize$default$2() {
         return "(";
      }

      public String parenthesize$default$3() {
         return ")";
      }

      public boolean commentsRequired() {
         return this.commentsRequired;
      }

      public void comment(final Function0 body) {
         this.parenthesize(this.commentsRequired(), "/*", "*/", body);
      }

      public void printImplicitInParamsList(final List vds) {
         if (vds.nonEmpty()) {
            this.printFlags(((Trees.ValDef)vds.head()).mods().flags() & 512L, "");
         }
      }

      public void printValueParams(final List ts, final boolean inParentheses) {
         this.parenthesize(inParentheses, this.parenthesize$default$2(), this.parenthesize$default$3(), (JFunction0.mcV.sp)() -> {
            this.printImplicitInParamsList(ts);
            Function1 var10001 = (vd) -> {
               $anonfun$printValueParams$2(this, vd);
               return BoxedUnit.UNIT;
            };
            JFunction0.mcV.sp printSeq_printsep = () -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{", "}));
            Function1 printSeq_printelem = var10001;
            List printSeq_ls = ts;

            while(true) {
               if (printSeq_ls != null) {
                  List var10000 = scala.package..MODULE$.List();
                  if (var10000 == null) {
                     throw null;
                  }

                  List printSeq_unapplySeq_this = var10000;
                  SeqOps var19 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
                  Object var16 = null;
                  SeqOps var5 = var19;
                  SeqFactory.UnapplySeqWrapper var20 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var5);
                  var20 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var20 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_lengthCompare$extension_len = 0;
                  if (var5.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                     return;
                  }
               }

               if (printSeq_ls != null) {
                  List var23 = scala.package..MODULE$.List();
                  if (var23 == null) {
                     throw null;
                  }

                  List printSeq_unapplySeq_this = var23;
                  SeqOps var24 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
                  Object var17 = null;
                  SeqOps var6 = var24;
                  SeqFactory.UnapplySeqWrapper var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var31 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var6);
                  var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_lengthCompare$extension_len = 1;
                  if (var6.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                     var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int printSeq_apply$extension_i = 0;
                     Trees.ValDef var18 = (Trees.ValDef)var6.apply(printSeq_apply$extension_i);
                     this.printVParam(var18);
                     return;
                  }
               }

               if (!(printSeq_ls instanceof scala.collection.immutable..colon.colon)) {
                  throw new MatchError(printSeq_ls);
               }

               scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)printSeq_ls;
               Object printSeq_x = var7.head();
               List printSeq_rest = var7.next$access$1();
               Trees.ValDef var15 = (Trees.ValDef)printSeq_x;
               this.printVParam(var15);
               $anonfun$printValueParams$3(this);
               printSeq_printsep = printSeq_printsep;
               printSeq_printelem = printSeq_printelem;
               printSeq_ls = printSeq_rest;
            }
         });
      }

      public void printVParam(final Trees.ValDef vd) {
         this.printPosition(vd);
         this.printAnnotations(vd);
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(vd, vd.name())}));
         this.printOpt(": ", vd.tpt());
         this.printOpt(" = ", vd.rhs());
      }

      public void printTParam(final Trees.TypeDef td) {
         this.printPosition(td);
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(td, td.name())}));
         this.printTypeParams(td.tparams());
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{td.rhs()}));
      }

      public void printBlock(final Trees.Tree tree) {
         if (tree instanceof Trees.Block) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tree}));
         } else {
            this.printColumn(new scala.collection.immutable..colon.colon(tree, scala.collection.immutable.Nil..MODULE$), "{", ";", "}");
         }
      }

      private Object symFn(final Trees.Tree tree, final Function1 f, final Function0 orElse) {
         Symbols.Symbol var4 = tree.symbol();
         boolean var10000;
         if (var4 == null) {
            var10000 = true;
         } else {
            Symbols.NoSymbol var5 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
            if (var5 != null) {
               if (var5.equals(var4)) {
                  var10000 = true;
                  return var10000 ? orElse.apply() : f.apply(var4);
               }
            }

            var10000 = false;
         }

         return var10000 ? orElse.apply() : f.apply(var4);
      }

      private boolean ifSym(final Trees.Tree tree, final Function1 p) {
         Symbols.Symbol var3 = tree.symbol();
         boolean var10000;
         if (var3 == null) {
            var10000 = true;
         } else {
            label20: {
               Symbols.NoSymbol var5 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
               if (var5 != null) {
                  if (var5.equals(var3)) {
                     var10000 = true;
                     break label20;
                  }
               }

               var10000 = false;
            }
         }

         Object var6 = var10000 ? false : p.apply(var3);
         var3 = null;
         return BoxesRunTime.unboxToBoolean(var6);
      }

      public void printOpt(final String prefix, final Trees.Tree tree) {
         if (tree.nonEmpty()) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{prefix, tree}));
         }
      }

      public void printModifiers(final Trees.Tree tree, final Trees.Modifiers mods) {
         long var5;
         label34: {
            label33: {
               Symbols.Symbol var10001 = tree.symbol();
               Symbols.NoSymbol var3 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
               if (var10001 == null) {
                  if (var3 == null) {
                     break label33;
                  }
               } else if (var10001.equals(var3)) {
                  break label33;
               }

               var5 = tree.symbol().flags();
               break label34;
            }

            var5 = mods.flags();
         }

         Object var6;
         label26: {
            label25: {
               Symbols.Symbol var10002 = tree.symbol();
               Symbols.NoSymbol var4 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
               if (var10002 == null) {
                  if (var4 == null) {
                     break label25;
                  }
               } else if (var10002.equals(var4)) {
                  break label25;
               }

               var6 = tree.symbol().hasAccessBoundary() ? tree.symbol().privateWithin().name() : "";
               break label26;
            }

            var6 = mods.privateWithin();
         }

         this.printFlags(var5, String.valueOf(var6));
      }

      public void printFlags(final long flags, final String privateWithin) {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var12 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = this.scala$reflect$internal$Printers$TreePrinter$$$outer().settings();
         MutableSettings var13 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDebug$extension_$this = var13;
         boolean var14 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
         isDebug$extension_$this = null;
         long mask = var14 ? -1L : 577838443559423535L;
         String s = Flags$.MODULE$.flagsToString(flags & mask, privateWithin);
         String var7 = "";
         if (s != null) {
            if (s.equals(var7)) {
               return;
            }
         }

         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append(s).append(" ").toString()}));
      }

      public void printAnnotations(final Trees.MemberDef tree) {
         List var3 = tree.symbol().annotations();
         List annots = scala.collection.immutable.Nil..MODULE$.equals(var3) ? tree.mods().annotations() : var3;
         if (annots == null) {
            throw null;
         } else {
            for(List foreach_these = annots; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               Object var5 = foreach_these.head();
               $anonfun$printAnnotations$1(this, var5);
            }

         }
      }

      public void printPackageDef(final Trees.PackageDef tree, final String separator) {
         if (tree != null) {
            Trees.RefTree packaged = tree.pid();
            List stats = tree.stats();
            this.printAnnotations(tree);
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"package ", packaged}));
            this.printColumn(stats, " {", separator, "}");
         } else {
            throw new MatchError((Object)null);
         }
      }

      public void printValDef(final Trees.ValDef tree, final Function0 resultName, final Function0 printTypeSignature, final Function0 printRhs) {
         if (tree != null) {
            Trees.Modifiers mods = tree.mods();
            this.printAnnotations(tree);
            this.printModifiers(tree, mods);
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{mods.isMutable() ? "var " : "val ", resultName.apply()}));
            printTypeSignature.apply$mcV$sp();
            printRhs.apply$mcV$sp();
         } else {
            throw new MatchError((Object)null);
         }
      }

      public void printDefDef(final Trees.DefDef tree, final Function0 resultName, final Function0 printTypeSignature, final Function0 printRhs) {
         if (tree == null) {
            throw new MatchError((Object)null);
         } else {
            Trees.Modifiers mods = tree.mods();
            List tparams = tree.tparams();
            List vparamss = tree.vparamss();
            this.printAnnotations(tree);
            this.printModifiers(tree, mods);
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(4)).append("def ").append(resultName.apply()).toString()}));
            this.printTypeParams(tparams);
            if (vparamss == null) {
               throw null;
            } else {
               for(List foreach_these = vparamss; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                  List var9 = (List)foreach_these.head();
                  $anonfun$printDefDef$1(this, var9);
               }

               Object var10 = null;
               printTypeSignature.apply$mcV$sp();
               printRhs.apply$mcV$sp();
            }
         }
      }

      public void printTypeDef(final Trees.TypeDef tree, final Function0 resultName) {
         if (tree != null) {
            Trees.Modifiers mods = tree.mods();
            List tparams = tree.tparams();
            Trees.Tree rhs = tree.rhs();
            if (mods.hasFlag(8208L)) {
               this.printAnnotations(tree);
               this.printModifiers(tree, mods);
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"type "}));
               this.printTParam(tree);
            } else {
               this.printAnnotations(tree);
               this.printModifiers(tree, mods);
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(5)).append("type ").append(resultName.apply()).toString()}));
               this.printTypeParams(tparams);
               this.printOpt(" = ", rhs);
            }
         } else {
            throw new MatchError((Object)null);
         }
      }

      public void printImport(final Trees.Import tree, final Function0 resSelect) {
         if (tree == null) {
            throw new MatchError((Object)null);
         } else {
            List selectors = tree.selectors();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"import ", resSelect.apply(), "."}));
            if (selectors != null) {
               List var10000 = scala.package..MODULE$.List();
               if (var10000 == null) {
                  throw null;
               }

               List unapplySeq_this = var10000;
               SeqOps var20 = SeqFactory.unapplySeq$(unapplySeq_this, selectors);
               Object var14 = null;
               SeqOps var4 = var20;
               SeqFactory.UnapplySeqWrapper var21 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var4);
               var21 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var21 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int lengthCompare$extension_len = 1;
               if (var4.lengthCompare(lengthCompare$extension_len) == 0) {
                  var21 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var21 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int apply$extension_i = 0;
                  Trees.ImportSelector s = (Trees.ImportSelector)var4.apply(apply$extension_i);
                  if (!s.isRename() && !s.isMask()) {
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.selectorToString$1(s)}));
                     return;
                  }

                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"{", this.selectorToString$1(s), "}"}));
                  return;
               }
            }

            ScalaRunTime var26 = scala.runtime.ScalaRunTime..MODULE$;
            Object[] var10002 = new Object[1];
            if (selectors == null) {
               throw null;
            } else {
               Object var10005;
               if (selectors == scala.collection.immutable.Nil..MODULE$) {
                  var10005 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.ImportSelector var13 = (Trees.ImportSelector)selectors.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$printImport$1(this, var13), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)selectors.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var13 = (Trees.ImportSelector)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$printImport$1(this, var13), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var10005 = map_h;
               }

               Object var15 = null;
               Object var16 = null;
               Object var17 = null;
               Object var18 = null;
               var10002[0] = IterableOnceOps.mkString$((IterableOnceOps)var10005, "{", ", ", "}");
               this.print(var26.genericWrapArray(var10002));
            }
         }
      }

      public void printCaseDef(final Trees.CaseDef tree) {
         if (tree != null) {
            Trees.Tree pat = tree.pat();
            Trees.Tree guard = tree.guard();
            Trees.Tree body = tree.body();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"case "}));
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{pat}));
            this.printOpt(" if ", guard);
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" => ", body}));
         } else {
            throw new MatchError((Object)null);
         }
      }

      public void printFunction(final Trees.Function tree, final Function0 printValueParams) {
         if (tree != null) {
            Trees.Tree body = tree.body();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"("}));
            printValueParams.apply$mcV$sp();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" => ", body, ")"}));
            if (this.printIds() && tree.symbol() != null) {
               this.comment((JFunction0.mcV.sp)() -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append("#").append(tree.symbol().id()).toString()})));
            }

            if (this.printOwners() && tree.symbol() != null) {
               this.comment((JFunction0.mcV.sp)() -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append("@").append(tree.symbol().owner().id()).toString()})));
            }
         } else {
            throw new MatchError((Object)null);
         }
      }

      public void printSuper(final Trees.Super tree, final Function0 resultName, final boolean checkSymbol) {
         if (tree != null) {
            Trees.Tree var4 = tree.qual();
            Names.TypeName mix = tree.mix();
            if (var4 instanceof Trees.This) {
               label48: {
                  Names.TypeName qual = ((Trees.This)var4).qual();
                  if (!qual.nonEmpty()) {
                     if (!checkSymbol) {
                        break label48;
                     }

                     Symbols.Symbol var10000 = tree.symbol();
                     Symbols.NoSymbol var7 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
                     if (var10000 == null) {
                        if (var7 == null) {
                           break label48;
                        }
                     } else if (var10000.equals(var7)) {
                        break label48;
                     }
                  }

                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append((String)resultName.apply()).append(".").toString()}));
               }

               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"super"}));
               if (mix.nonEmpty()) {
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(2)).append("[").append(mix).append("]").toString()}));
                  return;
               }

               MutableSettings.SettingsOps$ var14 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var15 = MutableSettings$.MODULE$;
               MutableSettings SettingsOps_settings = this.scala$reflect$internal$Printers$TreePrinter$$$outer().settings();
               MutableSettings var16 = SettingsOps_settings;
               SettingsOps_settings = null;
               MutableSettings isDebug$extension_$this = var16;
               boolean var17 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
               isDebug$extension_$this = null;
               if (var17) {
                  Types.Type var8 = tree.tpe();
                  if (var8 instanceof Types.SuperType) {
                     Types.SuperType var9 = (Types.SuperType)var8;
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(2)).append("[").append(var9.supertpe()).append("]").toString()}));
                     return;
                  }

                  if (var8 != null) {
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(2)).append("[").append(var8).append("]").toString()}));
                     return;
                  }

                  return;
               }

               return;
            }
         }

         throw new MatchError(tree);
      }

      public void printThis(final Trees.This tree, final Function0 resultName) {
         if (tree != null) {
            Names.TypeName qual = tree.qual();
            if (qual.nonEmpty()) {
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append((String)resultName.apply()).append(".").toString()}));
            }

            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"this"}));
         } else {
            throw new MatchError((Object)null);
         }
      }

      public void printBlock(final List stats, final Trees.Tree expr) {
         this.printColumn((new scala.collection.immutable..colon.colon(expr, scala.collection.immutable.Nil..MODULE$)).$colon$colon$colon(stats), "{", ";", "}");
      }

      public void printTree(final Trees.Tree tree) {
         boolean var2 = false;
         Trees.Super var3 = null;
         boolean var4 = false;
         Trees.Select var5 = null;
         if (this.scala$reflect$internal$Printers$TreePrinter$$$outer().EmptyTree().equals(tree)) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"<empty>"}));
         } else if (tree instanceof Trees.ClassDef) {
            Trees.ClassDef var6 = (Trees.ClassDef)tree;
            Trees.Modifiers mods = var6.mods();
            Names.TypeName name = var6.name();
            List tparams = var6.tparams();
            Trees.Template impl = var6.impl();
            this.printAnnotations(var6);
            this.printModifiers(tree, mods);
            String var138;
            if (mods.isTrait()) {
               var138 = "trait";
            } else {
               Symbols.Symbol var133 = tree.symbol();
               boolean var10000;
               if (var133 == null) {
                  var10000 = true;
               } else {
                  label188: {
                     Symbols.NoSymbol var136 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
                     if (var136 != null) {
                        if (var136.equals(var133)) {
                           var10000 = true;
                           break label188;
                        }
                     }

                     var10000 = false;
                  }
               }

               var10000 = var10000 ? false : var133.isModuleClass();
               Object var135 = null;
               var138 = var10000 ? "object" : "class";
            }

            String word = var138;
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{word, " ", this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name)}));
            this.printTypeParams(tparams);
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{mods.isDeferred() ? " <: " : " extends ", impl}));
         } else if (tree instanceof Trees.PackageDef) {
            Trees.PackageDef var12 = (Trees.PackageDef)tree;
            this.printPackageDef(var12, ";");
         } else if (tree instanceof Trees.ModuleDef) {
            Trees.ModuleDef var13 = (Trees.ModuleDef)tree;
            Trees.Modifiers mods = var13.mods();
            Names.TermName name = var13.name();
            Trees.Template impl = var13.impl();
            this.printAnnotations(var13);
            this.printModifiers(tree, mods);
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(7)).append("object ").append(this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name)).toString(), " extends ", impl}));
         } else if (tree instanceof Trees.ValDef) {
            Trees.ValDef var17 = (Trees.ValDef)tree;
            Trees.Modifiers mods = var17.mods();
            Names.TermName name = var17.name();
            Trees.Tree tp = var17.tpt();
            Trees.Tree rhs = var17.rhs();
            this.printValDef(var17, () -> this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name), (JFunction0.mcV.sp)() -> this.printOpt(": ", tp), (JFunction0.mcV.sp)() -> {
               if (!mods.isDeferred()) {
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" = ", rhs.isEmpty() ? "_" : rhs}));
               }
            });
         } else if (tree instanceof Trees.DefDef) {
            Trees.DefDef var22 = (Trees.DefDef)tree;
            Names.TermName name = var22.name();
            List tparams = var22.tparams();
            List vparamss = var22.vparamss();
            Trees.Tree tp = var22.tpt();
            Trees.Tree rhs = var22.rhs();
            this.printDefDef(var22, () -> this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name), (JFunction0.mcV.sp)() -> {
               if (tparams.isEmpty() && vparamss.isEmpty()) {
                  StringBuilder var10001 = new StringBuilder(2);
                  if (name == null) {
                     throw null;
                  } else {
                     this.printOpt(var10001.append(this.blankForName(((Names.Name)name).encode())).append(": ").toString(), tp);
                  }
               } else {
                  this.printOpt(": ", tp);
               }
            }, (JFunction0.mcV.sp)() -> this.printOpt(" = ", rhs));
         } else if (tree instanceof Trees.TypeDef) {
            Trees.TypeDef var28 = (Trees.TypeDef)tree;
            Names.TypeName name = var28.name();
            this.printTypeDef(var28, () -> this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name));
         } else if (tree instanceof Trees.LabelDef) {
            Trees.LabelDef var30 = (Trees.LabelDef)tree;
            Names.TermName name = var30.name();
            List params = var30.params();
            Trees.Tree rhs = var30.rhs();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name)}));
            this.printLabelParams(params);
            this.printBlock(rhs);
         } else if (tree instanceof Trees.Import) {
            Trees.Import var34 = (Trees.Import)tree;
            Trees.Tree expr = var34.expr();
            this.printImport(var34, () -> this.scala$reflect$internal$Printers$TreePrinter$$$outer().backquotedPath(expr));
         } else if (tree instanceof Trees.Template) {
            List parents;
            Trees.ValDef self;
            List body;
            Symbols.Symbol currentOwner1;
            label286: {
               Trees.Template var36 = (Trees.Template)tree;
               parents = var36.parents();
               self = var36.self();
               body = var36.body();
               currentOwner1 = this.currentOwner;
               Symbols.Symbol var139 = tree.symbol();
               Symbols.NoSymbol var41 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().NoSymbol();
               if (var139 == null) {
                  if (var41 == null) {
                     break label286;
                  }
               } else if (var139.equals(var41)) {
                  break label286;
               }

               this.currentOwner = tree.symbol().owner();
            }

            this.printRow(parents, " with ");
            if (body.nonEmpty()) {
               label278: {
                  label277: {
                     Names.TermName var140 = self.name();
                     Names.Name var42 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().nme().WILDCARD();
                     if (var140 == null) {
                        if (var42 != null) {
                           break label277;
                        }
                     } else if (!var140.equals(var42)) {
                        break label277;
                     }

                     if (self.tpt().nonEmpty()) {
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" { _ : ", self.tpt(), " => "}));
                     } else {
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" {"}));
                     }
                     break label278;
                  }

                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" { ", self.name()}));
                  this.printOpt(": ", self.tpt());
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" => "}));
               }

               this.printColumn(body, "", ";", "}");
            }

            this.currentOwner = currentOwner1;
         } else if (tree instanceof Trees.Block) {
            Trees.Block var43 = (Trees.Block)tree;
            List stats = var43.stats();
            Trees.Tree expr = var43.expr();
            this.printBlock(stats, expr);
         } else if (tree instanceof Trees.Match) {
            Trees.Match var46 = (Trees.Match)tree;
            Trees.Tree selector = var46.selector();
            List cases = var46.cases();
            Types.Type selectorType1 = this.selectorType;
            this.selectorType = selector.tpe();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{selector}));
            this.printColumn(cases, " match {", "", "}");
            this.selectorType = selectorType1;
         } else if (tree instanceof Trees.CaseDef) {
            Trees.CaseDef var50 = (Trees.CaseDef)tree;
            this.printCaseDef(var50);
         } else if (tree instanceof Trees.Alternative) {
            List trees = ((Trees.Alternative)tree).trees();
            this.printRow(trees, "(", "| ", ")");
         } else if (tree instanceof Trees.Star) {
            Trees.Tree elem = ((Trees.Star)tree).elem();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", elem, ")*"}));
         } else if (tree instanceof Trees.Bind) {
            Trees.Bind var53 = (Trees.Bind)tree;
            Names.Name name = var53.name();
            Trees.Tree t = var53.body();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name), " @ ", t, ")"}));
         } else if (tree instanceof Trees.UnApply) {
            Trees.UnApply var56 = (Trees.UnApply)tree;
            Trees.Tree fun = var56.fun();
            List args = var56.args();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{fun, " <unapply> "}));
            this.printRow(args, "(", ", ", ")");
         } else if (tree instanceof Trees.ArrayValue) {
            Trees.ArrayValue var59 = (Trees.ArrayValue)tree;
            Trees.Tree elemtpt = var59.elemtpt();
            List trees = var59.elems();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"Array[", elemtpt}));
            this.printRow(trees, "]{", ", ", "}");
         } else if (tree instanceof Trees.Function) {
            Trees.Function var62 = (Trees.Function)tree;
            List vparams = var62.vparams();
            this.printFunction(var62, (JFunction0.mcV.sp)() -> this.printValueParams(vparams, this.printValueParams$default$2()));
         } else if (tree instanceof Trees.Assign) {
            Trees.Assign var64 = (Trees.Assign)tree;
            Trees.Tree lhs = var64.lhs();
            Trees.Tree rhs = var64.rhs();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{lhs, " = ", rhs}));
         } else if (tree instanceof Trees.NamedArg) {
            Trees.NamedArg var67 = (Trees.NamedArg)tree;
            Trees.Tree lhs = var67.lhs();
            Trees.Tree rhs = var67.rhs();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{lhs, " = ", rhs}));
         } else if (tree instanceof Trees.If) {
            Trees.If var70 = (Trees.If)tree;
            Trees.Tree cond = var70.cond();
            Trees.Tree thenp = var70.thenp();
            Trees.Tree elsep = var70.elsep();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"if (", cond, ")"}));
            this.indent();
            this.println();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{thenp}));
            this.undent();
            if (elsep.nonEmpty()) {
               this.println();
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"else"}));
               this.indent();
               this.println();
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{elsep}));
               this.undent();
            }
         } else if (tree instanceof Trees.Return) {
            Trees.Tree expr = ((Trees.Return)tree).expr();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"return ", expr}));
         } else if (tree instanceof Trees.Try) {
            Trees.Try var75 = (Trees.Try)tree;
            Trees.Tree block = var75.block();
            List catches = var75.catches();
            Trees.Tree finalizer = var75.finalizer();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"try "}));
            this.printBlock(block);
            if (catches.nonEmpty()) {
               this.printColumn(catches, " catch {", "", "}");
            }

            this.printOpt(" finally ", finalizer);
         } else if (tree instanceof Trees.Throw) {
            Trees.Tree expr = ((Trees.Throw)tree).expr();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"throw ", expr}));
         } else if (tree instanceof Trees.New) {
            Trees.Tree tpe = ((Trees.New)tree).tpt();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"new ", tpe}));
         } else if (tree instanceof Trees.Typed) {
            Trees.Typed var81 = (Trees.Typed)tree;
            Trees.Tree expr = var81.expr();
            Trees.Tree tp = var81.tpt();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", expr, ": ", tp, ")"}));
         } else if (tree instanceof Trees.TypeApply) {
            Trees.TypeApply var84 = (Trees.TypeApply)tree;
            Trees.Tree fun = var84.fun();
            List targs = var84.args();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{fun}));
            this.printRow(targs, "[", ", ", "]");
         } else if (tree instanceof Trees.Apply) {
            Trees.Apply var87 = (Trees.Apply)tree;
            Trees.Tree fun = var87.fun();
            List vargs = var87.args();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{fun}));
            this.printRow(vargs, "(", ", ", ")");
         } else if (tree instanceof Trees.ApplyDynamic) {
            Trees.ApplyDynamic var90 = (Trees.ApplyDynamic)tree;
            Trees.Tree qual = var90.qual();
            List vargs = var90.args();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"<apply-dynamic>(", qual, "#", tree.symbol().nameString()}));
            this.printRow(vargs, ", (", ", ", "))");
         } else {
            label249: {
               if (tree instanceof Trees.Super) {
                  var2 = true;
                  var3 = (Trees.Super)tree;
                  Trees.Tree var93 = var3.qual();
                  if (var93 instanceof Trees.This) {
                     Names.TypeName qual = ((Trees.This)var93).qual();
                     this.printSuper(var3, () -> this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, qual), this.printSuper$default$3());
                     break label249;
                  }
               }

               if (var2) {
                  Trees.Tree qual = var3.qual();
                  Names.TypeName mix = var3.mix();
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{qual, ".super"}));
                  if (mix.nonEmpty()) {
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(2)).append("[").append(mix).append("]").toString()}));
                  }
               } else if (tree instanceof Trees.This) {
                  Trees.This var97 = (Trees.This)tree;
                  Names.TypeName qual = var97.qual();
                  this.printThis(var97, () -> this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, qual));
               } else {
                  label243: {
                     if (tree instanceof Trees.Select) {
                        var4 = true;
                        var5 = (Trees.Select)tree;
                        Trees.Tree qual = var5.qualifier();
                        if (qual instanceof Trees.New) {
                           Trees.New var100 = (Trees.New)qual;
                           MutableSettings.SettingsOps$ var141 = MutableSettings.SettingsOps$.MODULE$;
                           MutableSettings isDebug$extension_$this = MutableSettings$.MODULE$.SettingsOps(this.scala$reflect$internal$Printers$TreePrinter$$$outer().settings());
                           boolean var142 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
                           isDebug$extension_$this = null;
                           if (!var142) {
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var100}));
                              break label243;
                           }
                        }
                     }

                     if (var4) {
                        Trees.Tree qualifier = var5.qualifier();
                        Names.Name name = var5.name();
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.scala$reflect$internal$Printers$TreePrinter$$$outer().backquotedPath(qualifier), ".", this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name)}));
                     } else if (tree instanceof Trees.Ident) {
                        Trees.Ident var103 = (Trees.Ident)tree;
                        Names.Name name = var103.name();
                        String str = this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, name);
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var103.isBackquoted() ? (new StringBuilder(2)).append("`").append(str).append("`").toString() : str}));
                     } else if (tree instanceof Trees.Literal) {
                        Constants.Constant x = ((Trees.Literal)tree).value();
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x.escapedStringValue()}));
                     } else if (tree instanceof Trees.TypeTree) {
                        Trees.TypeTree var107 = (Trees.TypeTree)tree;
                        if (tree.tpe() != null && (!this.printPositions() || var107.original() == null)) {
                           if (tree.tpe().typeSymbol() != null && tree.tpe().typeSymbol().isAnonymousClass()) {
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tree.tpe().typeSymbol().toString()}));
                           } else {
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tree.tpe().toString()}));
                           }
                        } else if (var107.original() != null) {
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"<type: ", var107.original(), ">"}));
                        } else {
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"<type ?>"}));
                        }
                     } else {
                        label304: {
                           Trees.Tree tree;
                           List args;
                           Trees.Tree tpt;
                           label216: {
                              if (tree instanceof Trees.Annotated) {
                                 Trees.Annotated var108 = (Trees.Annotated)tree;
                                 Trees.Tree var109 = var108.annot();
                                 tree = var108.arg();
                                 if (var109 instanceof Trees.Apply) {
                                    Trees.Apply var111 = (Trees.Apply)var109;
                                    Trees.Tree var112 = var111.fun();
                                    args = var111.args();
                                    if (var112 instanceof Trees.Select) {
                                       Trees.Select var114 = (Trees.Select)var112;
                                       Trees.Tree var115 = var114.qualifier();
                                       Names.Name var116 = var114.name();
                                       if (var115 instanceof Trees.New) {
                                          tpt = ((Trees.New)var115).tpt();
                                          Names.TermName var143 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().nme().CONSTRUCTOR();
                                          if (var143 == null) {
                                             if (var116 == null) {
                                                break label216;
                                             }
                                          } else if (var143.equals(var116)) {
                                             break label216;
                                          }
                                       }
                                    }
                                 }
                              }

                              if (tree instanceof Trees.SingletonTypeTree) {
                                 Trees.Tree ref = ((Trees.SingletonTypeTree)tree).ref();
                                 this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{ref, ".type"}));
                              } else if (tree instanceof Trees.SelectFromTypeTree) {
                                 Trees.SelectFromTypeTree var119 = (Trees.SelectFromTypeTree)tree;
                                 Trees.Tree qualifier = var119.qualifier();
                                 Names.TypeName selector = var119.name();
                                 this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{qualifier, "#", this.scala$reflect$internal$Printers$TreePrinter$$$outer().symName(tree, selector)}));
                              } else if (tree instanceof Trees.CompoundTypeTree) {
                                 Trees.Template templ = ((Trees.CompoundTypeTree)tree).templ();
                                 this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{templ}));
                              } else if (tree instanceof Trees.AppliedTypeTree) {
                                 Trees.AppliedTypeTree var123 = (Trees.AppliedTypeTree)tree;
                                 Trees.Tree tp = var123.tpt();
                                 List args = var123.args();
                                 this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tp}));
                                 this.printRow(args, "[", ", ", "]");
                              } else if (tree instanceof Trees.TypeBoundsTree) {
                                 Trees.TypeBoundsTree var126 = (Trees.TypeBoundsTree)tree;
                                 Trees.Tree lo = var126.lo();
                                 Trees.Tree hi = var126.hi();
                                 if (lo.tpe() == null || !lo.tpe().$eq$colon$eq(this.scala$reflect$internal$Printers$TreePrinter$$$outer().definitions().NothingTpe())) {
                                    this.printOpt(" >: ", lo);
                                 }

                                 if (hi.tpe() == null || !hi.tpe().$eq$colon$eq(this.scala$reflect$internal$Printers$TreePrinter$$$outer().definitions().AnyTpe())) {
                                    this.printOpt(" <: ", hi);
                                 }
                              } else if (tree instanceof Trees.ExistentialTypeTree) {
                                 Trees.ExistentialTypeTree var129 = (Trees.ExistentialTypeTree)tree;
                                 Trees.Tree tpt = var129.tpt();
                                 List whereClauses = var129.whereClauses();
                                 this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tpt}));
                                 this.printColumn(whereClauses, " forSome { ", ";", "}");
                              } else {
                                 this.scala$reflect$internal$Printers$TreePrinter$$$outer().xprintTree(this, tree);
                              }
                              break label304;
                           }

                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tree, tree.isType() ? " " : ": "}));
                           this.printAnnot$1(tpt, args);
                        }
                     }
                  }
               }
            }
         }

         this.printTypesInfo(tree);
      }

      public void print(final Seq args) {
         args.foreach((x0$1) -> {
            $anonfun$print$1(this, x0$1);
            return BoxedUnit.UNIT;
         });
      }

      public boolean printValueParams$default$2() {
         return true;
      }

      public boolean printSuper$default$3() {
         return true;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Printers$TreePrinter$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public scala.reflect.api.Printers scala$reflect$api$Printers$TreePrinter$$$outer() {
         return this.scala$reflect$internal$Printers$TreePrinter$$$outer();
      }

      // $FF: synthetic method
      public static final void $anonfun$printColumn$1(final TreePrinter $this, final Trees.Tree x$1) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x$1}));
      }

      // $FF: synthetic method
      public static final void $anonfun$printRow$1(final TreePrinter $this, final Trees.Tree x$2) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x$2}));
      }

      // $FF: synthetic method
      public static final void $anonfun$printTypeParams$1(final TreePrinter $this, final Trees.TypeDef t) {
         $this.printAnnotations(t);
         if (t.mods().hasFlag(131072L)) {
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"-"}));
         } else if (t.mods().hasFlag(65536L)) {
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"+"}));
         }

         $this.printTParam(t);
      }

      // $FF: synthetic method
      public static final void $anonfun$printLabelParams$1(final TreePrinter $this, final Trees.Ident p) {
         $this.printLabelParam(p);
      }

      // $FF: synthetic method
      public static final void $anonfun$printValueParams$2(final TreePrinter $this, final Trees.ValDef vd) {
         $this.printVParam(vd);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$ifSym$1() {
         return false;
      }

      // $FF: synthetic method
      public static final void $anonfun$printAnnotations$1(final TreePrinter $this, final Object annot) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(2)).append("@").append(annot).append(" ").toString()}));
      }

      // $FF: synthetic method
      public static final void $anonfun$printDefDef$1(final TreePrinter $this, final List x$5) {
         $this.printValueParams(x$5, $this.printValueParams$default$2());
      }

      private final String selectorName$1(final Names.Name n, final Trees.ImportSelector s$1) {
         if (s$1.isWildcard()) {
            Names.Name var10000 = this.scala$reflect$internal$Printers$TreePrinter$$$outer().nme().WILDCARD();
            if (var10000 == null) {
               throw null;
            } else {
               return var10000.decode();
            }
         } else {
            return this.scala$reflect$internal$Printers$TreePrinter$$$outer().quotedName(n);
         }
      }

      private final String selectorToString$1(final Trees.ImportSelector s) {
         String from = this.selectorName$1(s.name(), s);
         return !s.isRename() && !s.isMask() ? from : (new StringBuilder(2)).append(from).append("=>").append(this.selectorName$1(s.rename(), s)).toString();
      }

      // $FF: synthetic method
      public static final String $anonfun$printImport$1(final TreePrinter $this, final Trees.ImportSelector s) {
         return $this.selectorToString$1(s);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$printTree$1(final Symbols.Symbol x$9) {
         return x$9.isModuleClass();
      }

      private final void printAnnot$1(final Trees.Tree tpt$1, final List args$1) {
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"@", tpt$1}));
         if (args$1.nonEmpty()) {
            this.printRow(args$1, "(", ",", ")");
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$print$1(final TreePrinter $this, final Object x0$1) {
         if (x0$1 instanceof Trees.Tree && ((Trees.Tree)x0$1).scala$reflect$internal$Trees$Tree$$$outer() == $this.scala$reflect$internal$Printers$TreePrinter$$$outer()) {
            Trees.Tree var2 = (Trees.Tree)x0$1;
            $this.printPosition(var2);
            $this.printTree(var2);
         } else if (x0$1 instanceof Names.Name && ((Names.Name)x0$1).scala$reflect$internal$Names$Name$$$outer() == $this.scala$reflect$internal$Printers$TreePrinter$$$outer()) {
            Names.Name var3 = (Names.Name)x0$1;
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{$this.scala$reflect$internal$Printers$TreePrinter$$$outer().quotedName(var3)}));
         } else {
            $this.out.print(x0$1 == null ? "null" : x0$1.toString());
         }
      }

      public TreePrinter(final PrintWriter out) {
         this.out = out;
         if (Printers.this == null) {
            throw null;
         } else {
            this.$outer = Printers.this;
            super();
            scala.reflect.api.Printers.TreePrinter.$init$(this);
            this.indentMargin = 0;
            this.indentStep = 2;
            this.indentString = "                                        ";
            this.printTypes_$eq(BoxesRunTime.unboxToBoolean(Printers.this.settings().printtypes().value()));
            this.printIds_$eq(BoxesRunTime.unboxToBoolean(Printers.this.settings().uniqid().value()));
            this.printOwners_$eq(BoxesRunTime.unboxToBoolean(Printers.this.settings().Yshowsymowners().value()));
            this.printKinds_$eq(BoxesRunTime.unboxToBoolean(Printers.this.settings().Yshowsymkinds().value()));
            this.printMirrors_$eq(false);
            this.printPositions_$eq(BoxesRunTime.unboxToBoolean(Printers.this.settings().Xprintpos().value()));
            this.commentsRequired = false;
            this.currentOwner = Printers.this.NoSymbol();
            this.selectorType = Printers.this.NoType();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$printAnnotations$1$adapted(final TreePrinter $this, final Object annot) {
         $anonfun$printAnnotations$1($this, annot);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$printDefDef$1$adapted(final TreePrinter $this, final List x$5) {
         $anonfun$printDefDef$1($this, x$5);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$printTree$1$adapted(final Symbols.Symbol x$9) {
         return BoxesRunTime.boxToBoolean($anonfun$printTree$1(x$9));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class CodePrinter extends TreePrinter {
      private volatile EmptyTypeTree$ EmptyTypeTree$module;
      private final boolean printRootPkg;
      private final Stack parentsStack;
      private final boolean commentsRequired;
      private final List defaultClasses;
      private final List defaultTraitsForCase;

      public EmptyTypeTree$ EmptyTypeTree() {
         if (this.EmptyTypeTree$module == null) {
            this.EmptyTypeTree$lzycompute$1();
         }

         return this.EmptyTypeTree$module;
      }

      public Stack parentsStack() {
         return this.parentsStack;
      }

      public Option currentTree() {
         if (this.parentsStack().nonEmpty()) {
            Some var10000 = new Some;
            Stack var10002 = this.parentsStack();
            if (var10002 == null) {
               throw null;
            } else {
               var10000.<init>(var10002.head());
               return var10000;
            }
         } else {
            return scala.None..MODULE$;
         }
      }

      public Option currentParent() {
         return (Option)(this.parentsStack().length() > 1 ? new Some(this.parentsStack().apply(1)) : scala.None..MODULE$);
      }

      public String printedName(final Names.Name name, final boolean decoded) {
         if (name == null) {
            throw null;
         } else {
            String decName = name.decode();
            char bslash = '\\';
            List var10000 = scala.package..MODULE$.List();
            ArraySeq apply_elems = scala.runtime.ScalaRunTime..MODULE$.wrapCharArray(new char[]{'[', ']', '(', ')', '{', '}'});
            if (var10000 == null) {
               throw null;
            } else {
               Object var21 = IterableFactory.apply$(var10000, apply_elems);
               Object var20 = null;
               List brackets = (List)var21;
               if (name.equals(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().CONSTRUCTOR())) {
                  return "this";
               } else {
                  String addBackquotes$1_s = this.scala$reflect$internal$Printers$CodePrinter$$$outer().quotedName(name, decoded);
                  if (decoded) {
                     int addBackquotes$1_exists$extension_indexWhere$extension_from = 0;
                     int addBackquotes$1_exists$extension_indexWhere$extension_len = decName.length();
                     int addBackquotes$1_exists$extension_indexWhere$extension_i = addBackquotes$1_exists$extension_indexWhere$extension_from;

                     while(true) {
                        if (addBackquotes$1_exists$extension_indexWhere$extension_i >= addBackquotes$1_exists$extension_indexWhere$extension_len) {
                           var22 = -1;
                           break;
                        }

                        char var17 = decName.charAt(addBackquotes$1_exists$extension_indexWhere$extension_i);
                        if (brackets.contains(var17) || Chars.isWhitespace$(Chars$.MODULE$, var17) || $anonfun$printedName$1(var17)) {
                           var22 = addBackquotes$1_exists$extension_indexWhere$extension_i;
                           break;
                        }

                        ++addBackquotes$1_exists$extension_indexWhere$extension_i;
                     }

                     if (var22 != -1) {
                        return (new StringBuilder(2)).append("`").append(addBackquotes$1_s).append("`").toString();
                     }

                     if (!name.isOperatorName()) {
                        int addBackquotes$1_exists$extension_indexWhere$extension_from = 0;
                        int addBackquotes$1_exists$extension_indexWhere$extension_len = decName.length();
                        int addBackquotes$1_exists$extension_indexWhere$extension_i = addBackquotes$1_exists$extension_indexWhere$extension_from;

                        while(true) {
                           if (addBackquotes$1_exists$extension_indexWhere$extension_i >= addBackquotes$1_exists$extension_indexWhere$extension_len) {
                              var23 = -1;
                              break;
                           }

                           char var18 = decName.charAt(addBackquotes$1_exists$extension_indexWhere$extension_i);
                           if (Chars.isOperatorPart$(Chars$.MODULE$, (char)var18)) {
                              var23 = addBackquotes$1_exists$extension_indexWhere$extension_i;
                              break;
                           }

                           ++addBackquotes$1_exists$extension_indexWhere$extension_i;
                        }

                        if (var23 == -1) {
                           return addBackquotes$1_s;
                        }
                     }

                     int addBackquotes$1_exists$extension_indexWhere$extension_from = 0;
                     int addBackquotes$1_exists$extension_indexWhere$extension_len = decName.length();
                     int addBackquotes$1_exists$extension_indexWhere$extension_i = addBackquotes$1_exists$extension_indexWhere$extension_from;

                     while(true) {
                        int var24;
                        if (addBackquotes$1_exists$extension_indexWhere$extension_i < addBackquotes$1_exists$extension_indexWhere$extension_len) {
                           char var19 = decName.charAt(addBackquotes$1_exists$extension_indexWhere$extension_i);
                           if (!Chars.isScalaLetter$(Chars$.MODULE$, (char)var19)) {
                              ++addBackquotes$1_exists$extension_indexWhere$extension_i;
                              continue;
                           }

                           var24 = addBackquotes$1_exists$extension_indexWhere$extension_i;
                        } else {
                           var24 = -1;
                        }

                        if (var24 != -1 && !.MODULE$.contains$extension(decName, bslash)) {
                           return (new StringBuilder(2)).append("`").append(addBackquotes$1_s).append("`").toString();
                        }
                        break;
                     }
                  }

                  return addBackquotes$1_s;
               }
            }
         }
      }

      public boolean printedName$default$2() {
         return true;
      }

      public boolean isIntLitWithDecodedOp(final Trees.Tree qual, final Names.Name name) {
         boolean var10000;
         if (qual instanceof Trees.Literal) {
            Constants.Constant var3 = ((Trees.Literal)qual).value();
            if (var3 != null && var3.value() instanceof Integer) {
               var10000 = true;
               return var10000 && name.isOperatorName();
            }
         }

         var10000 = false;
         return var10000 && name.isOperatorName();
      }

      public boolean commentsRequired() {
         return this.commentsRequired;
      }

      public boolean needsParentheses(final Trees.Tree parent, final boolean insideIf, final boolean insideMatch, final boolean insideTry, final boolean insideAnnotated, final boolean insideBlock, final boolean insideLabelDef, final boolean insideAssign) {
         if (parent instanceof Trees.If) {
            return insideIf;
         } else if (parent instanceof Trees.Match) {
            return insideMatch;
         } else if (parent instanceof Trees.Try) {
            return insideTry;
         } else if (parent instanceof Trees.Annotated) {
            return insideAnnotated;
         } else if (parent instanceof Trees.Block) {
            return insideBlock;
         } else if (parent instanceof Trees.LabelDef) {
            return insideLabelDef;
         } else {
            return parent instanceof Trees.Assign ? insideAssign : false;
         }
      }

      public boolean needsParentheses$default$2(final Trees.Tree parent) {
         return true;
      }

      public boolean needsParentheses$default$3(final Trees.Tree parent) {
         return true;
      }

      public boolean needsParentheses$default$4(final Trees.Tree parent) {
         return true;
      }

      public boolean needsParentheses$default$5(final Trees.Tree parent) {
         return true;
      }

      public boolean needsParentheses$default$6(final Trees.Tree parent) {
         return true;
      }

      public boolean needsParentheses$default$7(final Trees.Tree parent) {
         return true;
      }

      public boolean needsParentheses$default$8(final Trees.Tree parent) {
         return true;
      }

      public String resolveSelect(final Trees.Tree t) {
         boolean var2 = false;
         Trees.Select var3 = null;
         if (t instanceof Trees.Select) {
            var2 = true;
            var3 = (Trees.Select)t;
            Trees.Tree qual = var3.qualifier();
            Names.Name name = var3.name();
            if (name.isTermName()) {
               boolean x$3 = this.needsParentheses$default$2(qual);
               boolean x$4 = this.needsParentheses$default$3(qual);
               boolean x$5 = this.needsParentheses$default$4(qual);
               boolean x$6 = this.needsParentheses$default$5(qual);
               boolean x$7 = this.needsParentheses$default$6(qual);
               boolean x$8 = this.needsParentheses$default$8(qual);
               if (this.needsParentheses(qual, x$3, x$4, x$5, x$6, x$7, false, x$8)) {
                  return (new StringBuilder(3)).append("(").append(this.resolveSelect(qual)).append(").").append(this.printedName(name, this.printedName$default$2())).toString();
               }
            }

            if (this.isIntLitWithDecodedOp(qual, name)) {
               return (new StringBuilder(3)).append("(").append(this.resolveSelect(qual)).append(").").append(this.printedName(name, this.printedName$default$2())).toString();
            }
         }

         if (var2) {
            Trees.Tree qual = var3.qualifier();
            Names.Name name = var3.name();
            if (name.isTermName()) {
               return (new StringBuilder(1)).append(this.resolveSelect(qual)).append(".").append(this.printedName(name, this.printedName$default$2())).toString();
            }
         }

         if (var2) {
            Trees.Tree qual = var3.qualifier();
            Names.Name name = var3.name();
            if (name.isTypeName()) {
               return (new StringBuilder(2)).append(this.resolveSelect(qual)).append("#").append(this.blankForOperatorName(name)).append("%").append(this.printedName(name, this.printedName$default$2())).toString();
            }
         }

         if (t instanceof Trees.Ident) {
            Names.Name name = ((Trees.Ident)t).name();
            return this.printedName(name, this.printedName$default$2());
         } else {
            return this.scala$reflect$internal$Printers$CodePrinter$$$outer().render(t, (x$10) -> this.scala$reflect$internal$Printers$CodePrinter$$$outer().new CodePrinter(x$10, this.printRootPkg), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$3(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$4(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$5(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$6(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$7(), this.scala$reflect$internal$Printers$CodePrinter$$$outer().render$default$8());
         }
      }

      public boolean isEmptyTree(final Trees.Tree tree) {
         boolean var10000;
         if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(tree)) {
            var10000 = true;
         } else {
            if (tree instanceof Trees.TypeTree) {
               Trees.TypeTree var2 = (Trees.TypeTree)tree;
               if (this.EmptyTypeTree().unapply(var2)) {
                  var10000 = true;
                  return var10000;
               }
            }

            var10000 = false;
         }

         return var10000;
      }

      public List originalTypeTrees(final List trees) {
         if (trees == null) {
            throw null;
         } else {
            boolean filter_filterCommon_isFlipped = false;
            List filter_filterCommon_noneIn$1_l = trees;

            Object var10000;
            while(true) {
               if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                  var10000 = scala.collection.immutable.Nil..MODULE$;
                  break;
               }

               Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
               List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
               Trees.Tree var22 = (Trees.Tree)filter_filterCommon_noneIn$1_h;
               if ($anonfun$originalTypeTrees$1(this, var22) != filter_filterCommon_isFlipped) {
                  List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var10000 = filter_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                     var22 = (Trees.Tree)filter_filterCommon_noneIn$1_allIn$1_x;
                     if ($anonfun$originalTypeTrees$1(this, var22) == filter_filterCommon_isFlipped) {
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
                           var22 = (Trees.Tree)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                           if ($anonfun$originalTypeTrees$1(this, var22) != filter_filterCommon_isFlipped) {
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
                        Object var31 = null;
                        Object var34 = null;
                        Object var37 = null;
                        Object var40 = null;
                        Object var43 = null;
                        Object var46 = null;
                        Object var49 = null;
                        Object var52 = null;
                        break;
                     }

                     filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var27 = null;
                  Object var29 = null;
                  Object var32 = null;
                  Object var35 = null;
                  Object var38 = null;
                  Object var41 = null;
                  Object var44 = null;
                  Object var47 = null;
                  Object var50 = null;
                  Object var53 = null;
                  break;
               }

               filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
            }

            Object var24 = null;
            Object var25 = null;
            Object var26 = null;
            Object var28 = null;
            Object var30 = null;
            Object var33 = null;
            Object var36 = null;
            Object var39 = null;
            Object var42 = null;
            Object var45 = null;
            Object var48 = null;
            Object var51 = null;
            Object var54 = null;
            List filter_filterCommon_result = (List)var10000;
            Statics.releaseFence();
            var10000 = filter_filterCommon_result;
            filter_filterCommon_result = null;
            List map_this = (List)var10000;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               return scala.collection.immutable.Nil..MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$originalTypeTrees$2((Trees.Tree)map_this.head()), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$originalTypeTrees$2((Trees.Tree)map_rest.head()), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      }

      public List defaultClasses() {
         return this.defaultClasses;
      }

      public List defaultTraitsForCase() {
         return this.defaultTraitsForCase;
      }

      public List removeDefaultTypesFromList(final List trees, final List classesToRemove, final List traitsToRemove) {
         return this.removeDefaultTraitsFromList$1(this.removeDefaultClassesFromList(trees, classesToRemove), traitsToRemove);
      }

      public List removeDefaultTypesFromList$default$2(final List trees) {
         return this.defaultClasses();
      }

      public List removeDefaultClassesFromList(final List trees, final List classesToRemove) {
         List var10000 = this.originalTypeTrees(trees);
         if (var10000 == null) {
            throw null;
         } else {
            List filter_this = var10000;
            boolean filter_filterCommon_isFlipped = false;
            List filter_filterCommon_noneIn$1_l = filter_this;

            while(true) {
               if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                  var53 = scala.collection.immutable.Nil..MODULE$;
                  break;
               }

               Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
               List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
               Trees.Tree var19 = (Trees.Tree)filter_filterCommon_noneIn$1_h;
               if ($anonfun$removeDefaultClassesFromList$1(this, classesToRemove, var19) != filter_filterCommon_isFlipped) {
                  List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var53 = filter_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                     var19 = (Trees.Tree)filter_filterCommon_noneIn$1_allIn$1_x;
                     if ($anonfun$removeDefaultClassesFromList$1(this, classesToRemove, var19) == filter_filterCommon_isFlipped) {
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
                           var19 = (Trees.Tree)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                           if ($anonfun$removeDefaultClassesFromList$1(this, classesToRemove, var19) != filter_filterCommon_isFlipped) {
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

                        var53 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
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

                     filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

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
                  break;
               }

               filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
            }

            Object var20 = null;
            Object var21 = null;
            Object var22 = null;
            Object var24 = null;
            Object var26 = null;
            Object var29 = null;
            Object var32 = null;
            Object var35 = null;
            Object var38 = null;
            Object var41 = null;
            Object var44 = null;
            Object var47 = null;
            Object var50 = null;
            List filter_filterCommon_result = (List)var53;
            Statics.releaseFence();
            return filter_filterCommon_result;
         }
      }

      public List removeDefaultClassesFromList$default$2() {
         return this.defaultClasses();
      }

      public boolean syntheticToRemove(final Trees.Tree tree) {
         if (tree instanceof Trees.ValDef ? true : tree instanceof Trees.TypeDef) {
            return false;
         } else {
            return tree instanceof Trees.MemberDef && ((Trees.MemberDef)tree).mods().isSynthetic();
         }
      }

      public void printOpt(final String prefix, final Trees.Tree tree) {
         if (!this.isEmptyTree(tree)) {
            super.printOpt(prefix, tree);
         }
      }

      public void printColumn(final List ts, final String start, final String sep, final String end) {
         if (ts == null) {
            throw null;
         } else {
            boolean filter_filterCommon_isFlipped = false;
            List filter_filterCommon_noneIn$1_l = ts;

            Object var10001;
            while(true) {
               if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                  var10001 = scala.collection.immutable.Nil..MODULE$;
                  break;
               }

               Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
               List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
               Trees.Tree var20 = (Trees.Tree)filter_filterCommon_noneIn$1_h;
               if ($anonfun$printColumn$3(this, var20) != filter_filterCommon_isFlipped) {
                  List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var10001 = filter_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                     var20 = (Trees.Tree)filter_filterCommon_noneIn$1_allIn$1_x;
                     if ($anonfun$printColumn$3(this, var20) == filter_filterCommon_isFlipped) {
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
                           var20 = (Trees.Tree)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                           if ($anonfun$printColumn$3(this, var20) != filter_filterCommon_isFlipped) {
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

                        var10001 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var29 = null;
                        Object var32 = null;
                        Object var35 = null;
                        Object var38 = null;
                        Object var41 = null;
                        Object var44 = null;
                        Object var47 = null;
                        Object var50 = null;
                        break;
                     }

                     filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var25 = null;
                  Object var27 = null;
                  Object var30 = null;
                  Object var33 = null;
                  Object var36 = null;
                  Object var39 = null;
                  Object var42 = null;
                  Object var45 = null;
                  Object var48 = null;
                  Object var51 = null;
                  break;
               }

               filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
            }

            Object var22 = null;
            Object var23 = null;
            Object var24 = null;
            Object var26 = null;
            Object var28 = null;
            Object var31 = null;
            Object var34 = null;
            Object var37 = null;
            Object var40 = null;
            Object var43 = null;
            Object var46 = null;
            Object var49 = null;
            Object var52 = null;
            List filter_filterCommon_result = (List)var10001;
            Statics.releaseFence();
            var10001 = filter_filterCommon_result;
            filter_filterCommon_result = null;
            super.printColumn((List)var10001, start, sep, end);
         }
      }

      public void printFlags(final Trees.Modifiers mods, final boolean primaryCtorParam) {
         label24: {
            long base = 2148009007L;
            long mask = primaryCtorParam ? base : base | 512L;
            String s = mods.flagString(mask);
            String var8 = "";
            if (s != null) {
               if (s.equals(var8)) {
                  break label24;
               }
            }

            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append(s).append(" ").toString()}));
         }

         if (mods.isCase()) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append(mods.flagBitsToString(2048L)).append(" ").toString()}));
         }

         if (mods.isAbstractOverride()) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"abstract override "}));
         }
      }

      public boolean printFlags$default$2() {
         return false;
      }

      public void printModifiers(final Trees.Tree tree, final Trees.Modifiers mods) {
         this.printModifiers(mods, false);
      }

      public void printModifiers(final Trees.Modifiers mods, final boolean primaryCtorParam) {
         if (!this.currentParent().isEmpty() && !this.modsAccepted$1()) {
            List var10000 = scala.package..MODULE$.List();
            ArraySeq apply_elems = scala.runtime.ScalaRunTime..MODULE$.wrapLongArray(new long[]{512L, 2048L, 2147483648L, 1024L});
            if (var10000 == null) {
               throw null;
            } else {
               Object var8 = IterableFactory.apply$(var10000, apply_elems);
               Object var7 = null;
               List var9 = (List)var8;
               if (var9 == null) {
                  throw null;
               } else {
                  for(List foreach_these = var9; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                     long var5 = BoxesRunTime.unboxToLong(foreach_these.head());
                     $anonfun$printModifiers$4(this, mods, var5);
                  }

               }
            }
         } else {
            this.printFlags(mods, primaryCtorParam);
         }
      }

      public void printVParam(final Trees.ValDef vd, final boolean primaryCtorParam) {
         this.printPosition(vd);
         this.printAnnotations(vd);
         boolean mutableOrOverride = vd.mods().isOverride() || vd.mods().isMutable();
         boolean hideCtorMods = vd.mods().isParamAccessor() && vd.mods().isPrivateLocal() && !mutableOrOverride;
         boolean hideCaseCtorMods = vd.mods().isCaseAccessor() && vd.mods().isPublic() && !mutableOrOverride;
         if (primaryCtorParam && !hideCtorMods && !hideCaseCtorMods) {
            this.printModifiers(vd.mods(), primaryCtorParam);
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{vd.mods().isMutable() ? "var " : "val "}));
         }

         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.printedName(vd.name(), this.printedName$default$2()), this.blankForName(vd.name())}));
         this.printOpt(": ", vd.tpt());
         this.printOpt(" = ", vd.rhs());
      }

      public void printTParam(final Trees.TypeDef td, final boolean primaryCtorParam) {
         this.printPosition(td);
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.printedName(td.name(), this.printedName$default$2())}));
         this.printTypeParams(td.tparams());
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{td.rhs()}));
      }

      public void printVParam(final Trees.ValDef vd) {
         this.printVParam(vd, false);
      }

      public void printTParam(final Trees.TypeDef td) {
         this.printTParam(td, false);
      }

      public void printArgss(final List argss) {
         if (argss == null) {
            throw null;
         } else {
            for(List foreach_these = argss; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               List var3 = (List)foreach_these.head();
               $anonfun$printArgss$1(this, argss, var3);
            }

         }
      }

      public void printAnnotations(final Trees.MemberDef tree) {
         List annots = tree.mods().annotations();
         if (annots == null) {
            throw null;
         } else {
            for(List foreach_these = annots; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               Trees.Tree var4 = (Trees.Tree)foreach_these.head();
               $anonfun$printAnnotations$2(this, var4);
            }

         }
      }

      public void printAnnot(final Trees.Tree tree) {
         if (tree != null) {
            Some var2 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().Applied().unapply(tree);
            if (!var2.isEmpty()) {
               Trees.Tree core = (Trees.Tree)((Tuple3)var2.value())._1();
               List argss = (List)((Tuple3)var2.value())._3();
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"@"}));
               if (core instanceof Trees.Select) {
                  Trees.Tree var5 = ((Trees.Select)core).qualifier();
                  if (var5 instanceof Trees.New) {
                     Trees.Tree ann = ((Trees.New)var5).tpt();
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{ann}));
                  }
               }

               this.printArgss(argss);
               return;
            }
         }

         super.printTree(tree);
      }

      public void printTree(final Trees.Tree tree) {
         this.parentsStack().push(tree);

         try {
            this.processTreePrinting(tree);
            this.printTypesInfo(tree);
         } finally {
            this.parentsStack().pop();
         }

      }

      public void processTreePrinting(final Trees.Tree tree) {
         boolean var2 = false;
         Trees.Select var3 = null;
         boolean var4 = false;
         Trees.Literal var5 = null;
         if (!this.syntheticToRemove(tree)) {
            if (tree instanceof Trees.ClassDef) {
               Trees.ClassDef var6 = (Trees.ClassDef)tree;
               Trees.Modifiers mods = var6.mods();
               Names.TypeName name = var6.name();
               List tparams = var6.tparams();
               Trees.Template impl = var6.impl();
               if (mods.isJavaDefined()) {
                  super.printTree(var6);
               }

               this.printAnnotations(var6);
               Object var218;
               if (mods.isTrait()) {
                  this.printModifiers(tree, mods.$amp$tilde(8L));
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"trait ", this.printedName(name, this.printedName$default$2())}));
                  this.printTypeParams(tparams);
                  if (tree == null) {
                     throw new MatchError(tree);
                  }

                  Option var12 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().build().SyntacticTraitDef().unapply(tree);
                  if (var12.isEmpty()) {
                     throw new MatchError(tree);
                  }

                  List parents = (List)((Tuple7)var12.get())._5();
                  var218 = parents;
               } else {
                  this.printModifiers(tree, mods);
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"class ", this.printedName(name, this.printedName$default$2())}));
                  this.printTypeParams(tparams);
                  Option var14 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().build().SyntacticClassDef().unapply((Trees.Tree)var6);
                  if (!var14.isEmpty()) {
                     Trees.Modifiers ctorMods = (Trees.Modifiers)((Tuple9)var14.get())._4();
                     List vparamss = (List)((Tuple9)var14.get())._5();
                     List parents = (List)((Tuple9)var14.get())._7();
                     if (ctorMods.hasFlag(524293L) || ctorMods.hasAccessBoundary()) {
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" "}));
                        this.printModifiers(ctorMods, false);
                     }

                     boolean var219;
                     if (scala.collection.immutable.Nil..MODULE$.equals(vparamss)) {
                        var219 = true;
                     } else {
                        label653: {
                           if (vparamss != null) {
                              SeqOps var18 = scala.package..MODULE$.List().unapplySeq(vparamss);
                              if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var18)) {
                                 new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var18));
                                 if (scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var18), 1) == 0) {
                                    List var19 = (List)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var18), 0);
                                    if (scala.collection.immutable.Nil..MODULE$.equals(var19)) {
                                       var219 = true;
                                       break label653;
                                    }
                                 }
                              }
                           }

                           var219 = false;
                        }
                     }

                     if (!var219 || mods.isCase() || ctorMods.hasFlag(524293L)) {
                        vparamss.foreach((ts) -> {
                           $anonfun$processTreePrinting$4(this, ts);
                           return BoxedUnit.UNIT;
                        });
                     }

                     var218 = parents;
                  } else {
                     var218 = scala.collection.immutable.Nil..MODULE$;
                  }
               }

               List clParents = (List)var218;
               List x$2 = this.removeDefaultTypesFromList$default$2(clParents);
               List x$3 = (List)(mods.hasFlag(2048L) ? this.defaultTraitsForCase() : scala.collection.immutable.Nil..MODULE$);
               List printedParents = this.removeDefaultTypesFromList(clParents, x$2, x$3);
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{mods.isDeferred() ? "<: " : (printedParents.nonEmpty() ? " extends " : ""), impl}));
            } else if (tree instanceof Trees.PackageDef) {
               List stats;
               label469: {
                  Trees.PackageDef var23 = (Trees.PackageDef)tree;
                  Trees.RefTree packaged = var23.pid();
                  stats = var23.stats();
                  if (packaged instanceof Trees.Ident) {
                     Names.Name var217 = ((Trees.Ident)packaged).name();
                     Names.Name var26 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().EMPTY_PACKAGE_NAME();
                     if (var217 == null) {
                        if (var26 == null) {
                           break label469;
                        }
                     } else if (var217.equals(var26)) {
                        break label469;
                     }
                  }

                  this.printPackageDef(var23, scala.util.Properties..MODULE$.lineSeparator());
                  return;
               }

               this.printSeq(stats, (x$15) -> {
                  $anonfun$processTreePrinting$5(this, x$15);
                  return BoxedUnit.UNIT;
               }, (JFunction0.mcV.sp)() -> {
                  this.println();
                  this.println();
               });
            } else if (tree instanceof Trees.ModuleDef) {
               Trees.ModuleDef var27 = (Trees.ModuleDef)tree;
               Trees.Modifiers mods = var27.mods();
               Names.TermName name = var27.name();
               Trees.Template impl = var27.impl();
               this.printAnnotations(var27);
               this.printModifiers(tree, mods);
               if (impl != null) {
                  List parents = impl.parents();
                  List parWithoutAnyRef = this.removeDefaultClassesFromList(parents, this.removeDefaultClassesFromList$default$2());
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(7)).append("object ").append(this.printedName(name, this.printedName$default$2())).toString(), parWithoutAnyRef.nonEmpty() ? " extends " : "", impl}));
               } else {
                  throw new MatchError((Object)null);
               }
            } else if (tree instanceof Trees.ValDef) {
               Trees.ValDef var33 = (Trees.ValDef)tree;
               Trees.Modifiers mods = var33.mods();
               Names.TermName name = var33.name();
               Trees.Tree tp = var33.tpt();
               Trees.Tree rhs = var33.rhs();
               this.printValDef(var33, () -> this.printedName(name, this.printedName$default$2()), (JFunction0.mcV.sp)() -> this.printOpt((new StringBuilder(2)).append(this.blankForName(name)).append(": ").toString(), tp), (JFunction0.mcV.sp)() -> {
                  if (!mods.isDeferred()) {
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" = ", rhs.isEmpty() ? "_" : rhs}));
                  }
               });
            } else if (tree instanceof Trees.DefDef) {
               Trees.DefDef var38 = (Trees.DefDef)tree;
               Trees.Modifiers mods = var38.mods();
               Names.TermName name = var38.name();
               List tparams = var38.tparams();
               List vparamss = var38.vparamss();
               Trees.Tree tp = var38.tpt();
               Trees.Tree rhs = var38.rhs();
               this.printDefDef(var38, () -> this.printedName(name, this.printedName$default$2()), (JFunction0.mcV.sp)() -> {
                  if (tparams.isEmpty()) {
                     label12: {
                        if (!vparamss.isEmpty()) {
                           int apply_n = 0;
                           if (!((List)LinearSeqOps.apply$(vparamss, apply_n)).isEmpty()) {
                              break label12;
                           }
                        }

                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.blankForName(name)}));
                     }
                  }

                  this.printOpt(": ", tp);
               }, (JFunction0.mcV.sp)() -> this.printOpt((new StringBuilder(3)).append(" = ").append(mods.isMacro() ? "macro " : "").toString(), rhs));
            } else if (tree instanceof Trees.TypeDef) {
               Trees.TypeDef var45 = (Trees.TypeDef)tree;
               Names.TypeName name = var45.name();
               this.printTypeDef(var45, () -> this.printedName(name, this.printedName$default$2()));
            } else if (tree instanceof Trees.LabelDef) {
               Trees.LabelDef var47 = (Trees.LabelDef)tree;
               Names.TermName name = var47.name();
               List params = var47.params();
               Trees.Tree rhs = var47.rhs();
               if (name.startsWith(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().WHILE_PREFIX())) {
                  if (rhs instanceof Trees.If) {
                     Trees.If var51 = (Trees.If)rhs;
                     Trees.Tree cond = var51.cond();
                     Trees.Tree thenp = var51.thenp();
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"while (", cond, ") "}));
                     if (thenp instanceof Trees.Block) {
                        Trees.Block var54 = (Trees.Block)thenp;
                        List list = var54.stats();
                        var54.expr();
                        this.printColumn(list, "", ";", "");
                     } else {
                        throw new MatchError(thenp);
                     }
                  } else {
                     throw new MatchError(rhs);
                  }
               } else if (name.startsWith(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().DO_WHILE_PREFIX())) {
                  if (rhs instanceof Trees.Block) {
                     Trees.Block var56 = (Trees.Block)rhs;
                     List bodyList = var56.stats();
                     Trees.Tree var58 = var56.expr();
                     if (var58 instanceof Trees.If) {
                        Trees.Tree cond = ((Trees.If)var58).cond();
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"do "}));
                        this.printColumn(bodyList, "", ";", "");
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" while (", cond, ") "}));
                        return;
                     }
                  }

                  throw new MatchError(rhs);
               } else {
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.printedName(name, this.printedName$default$2())}));
                  this.printLabelParams(params);
                  this.printBlock(rhs);
               }
            } else if (tree instanceof Trees.Import) {
               Trees.Import var60 = (Trees.Import)tree;
               Trees.Tree expr = var60.expr();
               this.printImport(var60, () -> this.resolveSelect(expr));
            } else if (tree instanceof Trees.Template) {
               Trees.ValDef self;
               List body;
               List printedParents;
               Object var213;
               label508: {
                  Trees.Template var62 = (Trees.Template)tree;
                  List parents = var62.parents();
                  self = var62.self();
                  body = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().untypecheckedTemplBody(var62);
                  printedParents = (List)this.currentParent().map((x0$1) -> {
                     if (x0$1 instanceof Trees.CompoundTypeTree) {
                        return parents;
                     } else if (x0$1 instanceof Trees.ClassDef && ((Trees.ClassDef)x0$1).mods().isCase()) {
                        List x$5 = this.removeDefaultTypesFromList$default$2(parents);
                        List x$6 = this.defaultTraitsForCase();
                        return this.removeDefaultTypesFromList(parents, x$5, x$6);
                     } else {
                        return this.removeDefaultClassesFromList(parents, this.removeDefaultClassesFromList$default$2());
                     }
                  }).getOrElse(() -> parents);
                  Trees.Tree primaryCtr = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().firstConstructor(body);
                  if (primaryCtr instanceof Trees.DefDef) {
                     Trees.Tree var69 = ((Trees.DefDef)primaryCtr).rhs();
                     if (var69 instanceof Trees.Block) {
                        List ctBody = ((Trees.Block)var69).stats();
                        List var72 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().preSuperFields(ctBody);
                        List earlyDefs = body.filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$processTreePrinting$17(this, x0$2))).$colon$colon$colon(var72);
                        if (earlyDefs.nonEmpty()) {
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"{"}));
                           this.printColumn(earlyDefs, "", ";", "");
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(2)).append("} ").append(printedParents.nonEmpty() ? "with " : "").toString()}));
                        }

                        var213 = ctBody.collectFirst(new Serializable() {
                           private static final long serialVersionUID = 0L;

                           public final Object applyOrElse(final Trees.Tree x1, final Function1 default) {
                              return x1 instanceof Trees.Apply ? (Trees.Apply)x1 : default.apply(x1);
                           }

                           public final boolean isDefinedAt(final Trees.Tree x1) {
                              return x1 instanceof Trees.Apply;
                           }
                        });
                        break label508;
                     }
                  }

                  var213 = scala.None..MODULE$;
               }

               Option ap = (Option)var213;
               if (printedParents.nonEmpty()) {
                  if (!(printedParents instanceof scala.collection.immutable..colon.colon)) {
                     throw new MatchError(printedParents);
                  }

                  List traits;
                  label500: {
                     scala.collection.immutable..colon.colon var73 = (scala.collection.immutable..colon.colon)printedParents;
                     Trees.Tree clParent = (Trees.Tree)var73.head();
                     traits = var73.next$access$1();
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{clParent}));
                     if (ap instanceof Some) {
                        Trees.Apply var77 = (Trees.Apply)((Some)ap).value();
                        if (var77 != null) {
                           Some var78 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().Applied().unapply((Trees.Tree)var77);
                           if (!var78.isEmpty()) {
                              var213 = (List)((Tuple3)var78.get())._3();
                              break label500;
                           }
                        }
                     }

                     var213 = scala.collection.immutable.Nil..MODULE$;
                  }

                  List constrArgss = (List)var213;
                  this.printArgss(constrArgss);
                  if (traits.nonEmpty()) {
                     this.printRow(traits, " with ", " with ", "");
                  }
               }

               Tuple2 var79 = body.filter((x0$3) -> BoxesRunTime.boxToBoolean($anonfun$processTreePrinting$18(this, x0$3))).span((x0$4) -> BoxesRunTime.boxToBoolean($anonfun$processTreePrinting$19(this, x0$4)));
               if (var79 == null) {
                  throw new MatchError((Object)null);
               } else {
                  List modBody;
                  label692: {
                     List left = (List)var79._1();
                     List right = (List)var79._2();
                     modBody = ((List)right.drop(1)).$colon$colon$colon(left);
                     if (modBody.isEmpty()) {
                        label669: {
                           label490: {
                              Trees.noSelfType$ var83 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().noSelfType();
                              if (self == null) {
                                 if (var83 == null) {
                                    break label490;
                                 }
                              } else if (self.equals(var83)) {
                                 break label490;
                              }

                              if (!self.isEmpty()) {
                                 break label669;
                              }
                           }

                           var215 = false;
                           break label692;
                        }
                     }

                     var215 = true;
                  }

                  if (var215) {
                     label479: {
                        label478: {
                           Names.TermName var216 = self.name();
                           Names.Name var84 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().WILDCARD();
                           if (var216 == null) {
                              if (var84 != null) {
                                 break label478;
                              }
                           } else if (!var216.equals(var84)) {
                              break label478;
                           }

                           if (self.tpt().nonEmpty()) {
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" { _ : ", self.tpt(), " =>"}));
                           } else {
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" {"}));
                           }
                           break label479;
                        }

                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" { ", self.name()}));
                        this.printOpt(": ", self.tpt());
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" =>"}));
                     }

                     this.printColumn(modBody, "", ";", "}");
                  }
               }
            } else if (tree instanceof Trees.Block) {
               Trees.Block var85 = (Trees.Block)tree;
               Trees.Tree expr = var85.expr();
               this.printBlock(this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().untypecheckedBlockBody(var85), expr);
            } else if (tree instanceof Trees.Match) {
               Trees.Match var87 = (Trees.Match)tree;
               Trees.Tree selector = var87.selector();
               List cases = var87.cases();
               boolean x$9 = this.needsParentheses$default$2(selector);
               boolean x$10 = this.needsParentheses$default$3(selector);
               boolean x$11 = this.needsParentheses$default$4(selector);
               boolean x$12 = this.needsParentheses$default$5(selector);
               boolean x$13 = this.needsParentheses$default$6(selector);
               boolean x$14 = this.needsParentheses$default$8(selector);
               boolean printParentheses = this.needsParentheses(selector, x$9, x$10, x$11, x$12, x$13, false, x$14);
               if (tree instanceof Trees.Match) {
                  Trees.Tree var97 = ((Trees.Match)tree).selector();
                  if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(var97)) {
                     this.printColumn(cases, "{", "", "}");
                     return;
                  }
               }

               this.insertBraces$1((JFunction0.mcV.sp)() -> {
                  this.parenthesize(printParentheses, this.parenthesize$default$2(), this.parenthesize$default$3(), (JFunction0.mcV.sp)() -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{selector})));
                  this.printColumn(cases, " match {", "", "}");
               });
            } else if (tree instanceof Trees.CaseDef) {
               Trees.CaseDef var98 = (Trees.CaseDef)tree;
               this.printCaseDef(var98);
            } else if (tree instanceof Trees.Star) {
               Trees.Tree elem = ((Trees.Star)tree).elem();
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{elem, "*"}));
            } else if (tree instanceof Trees.Bind) {
               Names.Name name;
               label643: {
                  Trees.Bind var100 = (Trees.Bind)tree;
                  name = var100.name();
                  Trees.Tree t = var100.body();
                  Trees.EmptyTree$ var103 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree();
                  if (t == null) {
                     if (var103 == null) {
                        break label643;
                     }
                  } else if (t.equals(var103)) {
                     break label643;
                  }

                  if (t.exists((x$22x) -> BoxesRunTime.boxToBoolean($anonfun$processTreePrinting$23(x$22x)))) {
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.printedName(name, this.printedName$default$2()), " @ ", t}));
                     return;
                  }

                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", this.printedName(name, this.printedName$default$2()), " @ ", t, ")"}));
                  return;
               }

               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", this.printedName(name, this.printedName$default$2()), ")"}));
            } else if (tree instanceof Trees.Function) {
               Trees.Function var104 = (Trees.Function)tree;
               List vparams = var104.vparams();
               boolean printParentheses = vparams instanceof scala.collection.immutable..colon.colon ? !((Trees.ValDef)((scala.collection.immutable..colon.colon)vparams).head()).mods().isImplicit() : true;
               this.printFunction(var104, (JFunction0.mcV.sp)() -> this.printValueParams(vparams, printParentheses));
            } else if (tree instanceof Trees.Typed) {
               Trees.Typed var107 = (Trees.Typed)tree;
               Trees.Tree expr = var107.expr();
               Trees.Tree tp = var107.tpt();
               boolean var212;
               if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(tp)) {
                  var212 = true;
               } else {
                  label522: {
                     if (tp instanceof Trees.TypeTree) {
                        Trees.TypeTree var110 = (Trees.TypeTree)tp;
                        if (this.EmptyTypeTree().unapply(var110)) {
                           var212 = true;
                           break label522;
                        }
                     }

                     var212 = false;
                  }
               }

               if (var212) {
                  this.printTp$1(tp);
               } else {
                  if (tp instanceof Trees.Annotated) {
                     Trees.Tree arg = ((Trees.Annotated)tp).arg();
                     if (expr != null && arg != null && expr.equalsStructure(arg)) {
                        this.printTp$1(tp);
                        return;
                     }
                  }

                  if (tp instanceof Trees.TypeTree && ((Trees.TypeTree)tp).original() instanceof Trees.Annotated) {
                     this.printTp$1(tp);
                  } else {
                     if (tp instanceof Trees.Function) {
                        Trees.Function var112 = (Trees.Function)tp;
                        List var113 = var112.vparams();
                        Trees.Tree var114 = var112.body();
                        if (var113 != null) {
                           SeqOps var115 = scala.package..MODULE$.List().unapplySeq(var113);
                           if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var115)) {
                              new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var115));
                              if (scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var115), 0) == 0 && this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(var114)) {
                                 this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", expr, " _)"}));
                                 return;
                              }
                           }
                        }
                     }

                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"((", expr, "): ", tp, ")"}));
                  }
               }
            } else if (tree instanceof Trees.TypeApply) {
               Trees.TypeApply var116 = (Trees.TypeApply)tree;
               Trees.Tree fun = var116.fun();
               if (var116.args().exists((x$23) -> BoxesRunTime.boxToBoolean($anonfun$processTreePrinting$25(this, x$23)))) {
                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{fun}));
               } else {
                  super.printTree(tree);
               }
            } else if (tree instanceof Trees.Apply) {
               List l3;
               List l1;
               Trees.Apply var131;
               label645: {
                  Trees.Apply var118 = (Trees.Apply)tree;
                  Trees.Tree fun = var118.fun();
                  List vargs = var118.args();
                  boolean var121 = false;
                  Trees.Apply var122 = null;
                  if (tree instanceof Trees.Apply) {
                     var121 = true;
                     var122 = (Trees.Apply)tree;
                     Trees.Tree var123 = var122.fun();
                     l3 = var122.args();
                     if (var123 instanceof Trees.Block) {
                        Trees.Block var125 = (Trees.Block)var123;
                        l1 = var125.stats();
                        Trees.Tree a1 = var125.expr();
                        if (l1 != null) {
                           SeqOps var128 = scala.package..MODULE$.List().unapplySeq(l1);
                           if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var128)) {
                              new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var128));
                              if (scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var128), 1) == 0) {
                                 Trees.Tree sVD = (Trees.Tree)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var128), 0);
                                 if (sVD instanceof Trees.ValDef) {
                                    Trees.ValDef var130 = (Trees.ValDef)sVD;
                                    if (a1 instanceof Trees.Apply) {
                                       var131 = (Trees.Apply)a1;
                                       Trees.Tree var132 = var131.fun();
                                       List l2 = var131.args();
                                       if (var132 instanceof Trees.Select) {
                                          Names.Name methodName = ((Trees.Select)var132).name();
                                          if (l2 != null) {
                                             SeqOps var135 = scala.package..MODULE$.List().unapplySeq(l2);
                                             if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var135)) {
                                                new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var135));
                                                if (scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var135), 1) == 0) {
                                                   Trees.Tree var136 = (Trees.Tree)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var135), 0);
                                                   if (var136 instanceof Trees.Ident) {
                                                      Names.Name iVDName = ((Trees.Ident)var136).name();
                                                      if (var130.mods().isSynthetic() && this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().isLeftAssoc(methodName)) {
                                                         Names.TermName var211 = var130.name();
                                                         if (var211 == null) {
                                                            if (iVDName == null) {
                                                               break label645;
                                                            }
                                                         } else if (var211.equals(iVDName)) {
                                                            break label645;
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (var121) {
                     Trees.Tree tree1 = var122.fun();
                     boolean x$17 = this.needsParentheses$default$2(tree1);
                     boolean x$18 = this.needsParentheses$default$3(tree1);
                     boolean x$19 = this.needsParentheses$default$4(tree1);
                     boolean x$20 = this.needsParentheses$default$6(tree1);
                     boolean x$21 = this.needsParentheses$default$7(tree1);
                     boolean x$22 = this.needsParentheses$default$8(tree1);
                     if (this.needsParentheses(tree1, x$17, x$18, x$19, false, x$20, x$21, x$22)) {
                        this.parenthesize(this.parenthesize$default$1(), this.parenthesize$default$2(), this.parenthesize$default$3(), (JFunction0.mcV.sp)() -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{fun})));
                        this.printRow(vargs, "(", ", ", ")");
                        return;
                     }
                  }

                  super.printTree(tree);
                  return;
               }

               Trees.Block printBlock = this.scala$reflect$internal$Printers$CodePrinter$$$outer().new Block(l1, this.scala$reflect$internal$Printers$CodePrinter$$$outer().new Apply(var131, l3));
               this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{printBlock}));
            } else if (tree instanceof Trees.UnApply) {
               List args;
               label572: {
                  Trees.UnApply var146 = (Trees.UnApply)tree;
                  Trees.Tree fun = var146.fun();
                  args = var146.args();
                  if (fun != null) {
                     Option var149 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().Unapplied().unapply(fun);
                     if (!var149.isEmpty()) {
                        Trees.Tree qual;
                        label646: {
                           Trees.Tree body = (Trees.Tree)var149.get();
                           if (body instanceof Trees.Select) {
                              Trees.Select var151 = (Trees.Select)body;
                              qual = var151.qualifier();
                              Names.Name var210 = var151.name();
                              Names.TermName var153 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().unapply();
                              if (var210 == null) {
                                 if (var153 == null) {
                                    break label646;
                                 }
                              } else if (var210.equals(var153)) {
                                 break label646;
                              }
                           }

                           Trees.Tree qual;
                           label559: {
                              if (body instanceof Trees.TypeApply) {
                                 Trees.Tree var154 = ((Trees.TypeApply)body).fun();
                                 if (var154 instanceof Trees.Select) {
                                    Trees.Select var155 = (Trees.Select)var154;
                                    qual = var155.qualifier();
                                    Names.Name name = var155.name();
                                    Names.TermName var158 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().unapply();
                                    if (name == null) {
                                       if (var158 == null) {
                                          break label559;
                                       }
                                    } else if (name.equals(var158)) {
                                       break label559;
                                    }

                                    Names.TermName var159 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().unapplySeq();
                                    if (name == null) {
                                       if (var159 == null) {
                                          break label559;
                                       }
                                    } else if (name.equals(var159)) {
                                       break label559;
                                    }
                                 }
                              }

                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{body}));
                              break label572;
                           }

                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{qual}));
                           break label572;
                        }

                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{qual}));
                        break label572;
                     }
                  }

                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{fun}));
               }

               this.printRow(args, "(", ", ", ")");
            } else {
               if (tree instanceof Trees.Super) {
                  Trees.Super var160 = (Trees.Super)tree;
                  Trees.Tree var161 = var160.qual();
                  if (var161 instanceof Trees.This) {
                     Names.TypeName qual = ((Trees.This)var161).qual();
                     this.printSuper(var160, () -> this.printedName(qual, this.printedName$default$2()), false);
                     return;
                  }
               }

               if (tree instanceof Trees.This) {
                  Trees.This var163 = (Trees.This)tree;
                  Names.TypeName qual = var163.qual();
                  if (tree.hasExistingSymbol() && tree.symbol().hasPackageFlag()) {
                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tree.symbol().fullName()}));
                  } else {
                     this.printThis(var163, () -> this.printedName(qual, this.printedName$default$2()));
                  }
               } else {
                  Names.Name name;
                  label647: {
                     if (tree instanceof Trees.Select) {
                        var2 = true;
                        var3 = (Trees.Select)tree;
                        Trees.Tree var165 = var3.qualifier();
                        name = var3.name();
                        if (var165 instanceof Trees.This) {
                           Names.TermName var10000 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().CONSTRUCTOR();
                           if (var10000 == null) {
                              if (name == null) {
                                 break label647;
                              }
                           } else if (var10000.equals(name)) {
                              break label647;
                           }
                        }
                     }

                     if (var2) {
                        Trees.Tree qual = var3.qualifier();
                        if (qual instanceof Trees.New) {
                           Trees.New var168 = (Trees.New)qual;
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var168}));
                           return;
                        }
                     }

                     if (var2) {
                        Trees.Tree qual = var3.qualifier();
                        Names.Name name = var3.name();
                        if (this.printRootPkg && this.checkRootPackage$1(tree)) {
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append(this.printedName(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().ROOTPKG(), this.printedName$default$2())).append(".").toString()}));
                        }

                        boolean x$25 = this.needsParentheses$default$2(qual);
                        boolean x$26 = this.needsParentheses$default$3(qual);
                        boolean x$27 = this.needsParentheses$default$4(qual);
                        boolean x$28 = this.needsParentheses$default$6(qual);
                        boolean x$29 = this.needsParentheses$default$7(qual);
                        boolean x$30 = this.needsParentheses$default$8(qual);
                        if (this.needsParentheses(qual, x$25, x$26, x$27, false, x$28, x$29, x$30) || this.isIntLitWithDecodedOp(qual, name)) {
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", this.resolveSelect(qual), ").", this.printedName(name, this.printedName$default$2())}));
                           return;
                        }

                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.resolveSelect(qual), ".", this.printedName(name, this.printedName$default$2())}));
                        return;
                     }

                     if (!(tree instanceof Trees.Ident)) {
                        if (tree instanceof Trees.Literal) {
                           var4 = true;
                           var5 = (Trees.Literal)tree;
                           Constants.Constant k = var5.value();
                           if (k != null) {
                              Object s = k.value();
                              if (s instanceof String) {
                                 String var183 = (String)s;
                                 if (.MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(var183), '\n')) {
                                    String tq = .MODULE$.$times$extension(scala.Predef..MODULE$.augmentString("\""), 3);
                                    List lines = .MODULE$.linesIterator$extension(scala.Predef..MODULE$.augmentString(var183)).toList();
                                    if (lines.lengthCompare(1) <= 0) {
                                       this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{k.escapedStringValue()}));
                                       return;
                                    }

                                    Regex tqp = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("[\"]{3}"));
                                    String tqq = "\"\"\\\\\"";
                                    this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tq}));
                                    this.printSeq(lines.map((xx) -> tqp.replaceAllIn(xx, tqq)), (x$24) -> {
                                       $anonfun$processTreePrinting$30(this, x$24);
                                       return BoxedUnit.UNIT;
                                    }, (JFunction0.mcV.sp)() -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{'\n'})));
                                    this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tq}));
                                    return;
                                 }
                              }
                           }
                        }

                        if (var4) {
                           Constants.Constant x = var5.value();
                           String suffix = x.value() instanceof Float ? "F" : "";
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(0)).append(x.escapedStringValue()).append(suffix).toString()}));
                           return;
                        }

                        if (tree instanceof Trees.Annotated) {
                           Trees.Annotated var190 = (Trees.Annotated)tree;
                           Trees.Tree ap = var190.annot();
                           Trees.Tree tree = var190.arg();
                           boolean x$32 = this.needsParentheses$default$2(tree);
                           boolean x$33 = this.needsParentheses$default$3(tree);
                           boolean x$34 = this.needsParentheses$default$4(tree);
                           boolean x$35 = this.needsParentheses$default$5(tree);
                           boolean x$36 = this.needsParentheses$default$6(tree);
                           boolean x$37 = this.needsParentheses$default$7(tree);
                           boolean x$38 = this.needsParentheses$default$8(tree);
                           boolean printParentheses = this.needsParentheses(tree, x$32, x$33, x$34, x$35, x$36, x$37, x$38);
                           this.parenthesize(printParentheses, this.parenthesize$default$2(), this.parenthesize$default$3(), (JFunction0.mcV.sp)() -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tree})));
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{tree.isType() ? " " : ": "}));
                           this.printAnnot(ap);
                           return;
                        }

                        if (tree instanceof Trees.SelectFromTypeTree) {
                           Trees.SelectFromTypeTree var201 = (Trees.SelectFromTypeTree)tree;
                           Trees.Tree qualifier = var201.qualifier();
                           Names.TypeName selector = var201.name();
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", qualifier, ")#", this.blankForOperatorName(selector), this.printedName(selector, this.printedName$default$2())}));
                           return;
                        }

                        if (tree instanceof Trees.TypeTree) {
                           Trees.TypeTree var204 = (Trees.TypeTree)tree;
                           if (!this.isEmptyTree(var204)) {
                              Trees.Tree original = var204.original();
                              if (original != null) {
                                 this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{original}));
                                 return;
                              }

                              super.printTree(tree);
                              return;
                           }

                           return;
                        }

                        if (tree instanceof Trees.AppliedTypeTree) {
                           List args = ((Trees.AppliedTypeTree)tree).args();
                           if (args.exists((tptx) -> BoxesRunTime.boxToBoolean($anonfun$processTreePrinting$33(this, tptx)))) {
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"("}));
                              this.printRow((List)args.init(), "(", ", ", ")");
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" => ", args.last(), ")"}));
                              return;
                           }

                           if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isRepeatedParamType(tree) && args.nonEmpty()) {
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{args.apply(0), "*"}));
                              return;
                           }

                           if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isByNameParamType(tree)) {
                              this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"=> ", args.isEmpty() ? "()" : args.apply(0)}));
                              return;
                           }

                           super.printTree(tree);
                           return;
                        }

                        if (tree instanceof Trees.ExistentialTypeTree) {
                           Trees.ExistentialTypeTree var207 = (Trees.ExistentialTypeTree)tree;
                           Trees.Tree tpt = var207.tpt();
                           List whereClauses = var207.whereClauses();
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", tpt}));
                           this.printColumn(whereClauses, " forSome { ", ";", "})");
                           return;
                        }

                        if (this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(tree)) {
                           return;
                        }

                        super.printTree(tree);
                        return;
                     }

                     Trees.Ident var177 = (Trees.Ident)tree;
                     Names.Name name = var177.name();
                     if (name.nonEmpty()) {
                        if (name.equals(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().dollarScope())) {
                           this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(10)).append("scala.xml.").append(this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().TopScope()).toString()}));
                           return;
                        }

                        String str = this.printedName(name, this.printedName$default$2());
                        boolean strIsBackquoted = str.startsWith("`") && str.endsWith("`");
                        this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var177.isBackquoted() && !strIsBackquoted ? (new StringBuilder(2)).append("`").append(str).append("`").toString() : str}));
                        return;
                     }

                     this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{""}));
                     return;
                  }

                  this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.printedName(name, this.printedName$default$2())}));
               }
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Printers$CodePrinter$$$outer() {
         return this.$outer;
      }

      private final void EmptyTypeTree$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.EmptyTypeTree$module == null) {
               this.EmptyTypeTree$module = new EmptyTypeTree$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      // $FF: synthetic method
      public static final boolean $anonfun$printedName$1(final char x) {
         return x == '.';
      }

      // $FF: synthetic method
      public static final boolean $anonfun$printedName$2(final List brackets$1, final Function1 isDot$1, final char ch) {
         return brackets$1.contains(ch) || Chars.isWhitespace$(Chars$.MODULE$, ch) || BoxesRunTime.unboxToBoolean(isDot$1.apply(ch));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$printedName$3(final char c) {
         return Chars$.MODULE$.isOperatorPart(c);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$printedName$4(final char c) {
         return Chars$.MODULE$.isScalaLetter(c);
      }

      private static final String addBackquotes$1(final String s, final boolean decoded$2, final String decName$1, final List brackets$1, final Function1 isDot$1, final Names.Name name$5, final char bslash$1) {
         if (decoded$2) {
            int exists$extension_indexWhere$extension_from = 0;
            int exists$extension_indexWhere$extension_len = decName$1.length();
            int exists$extension_indexWhere$extension_i = exists$extension_indexWhere$extension_from;

            int var10000;
            while(true) {
               if (exists$extension_indexWhere$extension_i >= exists$extension_indexWhere$extension_len) {
                  var10000 = -1;
                  break;
               }

               char var16 = decName$1.charAt(exists$extension_indexWhere$extension_i);
               if (brackets$1.contains(var16) || Chars.isWhitespace$(Chars$.MODULE$, var16) || BoxesRunTime.unboxToBoolean(isDot$1.apply(var16))) {
                  var10000 = exists$extension_indexWhere$extension_i;
                  break;
               }

               ++exists$extension_indexWhere$extension_i;
            }

            if (var10000 != -1) {
               return (new StringBuilder(2)).append("`").append(s).append("`").toString();
            }

            if (!name$5.isOperatorName()) {
               int exists$extension_indexWhere$extension_from = 0;
               int exists$extension_indexWhere$extension_len = decName$1.length();
               int exists$extension_indexWhere$extension_i = exists$extension_indexWhere$extension_from;

               while(true) {
                  if (exists$extension_indexWhere$extension_i >= exists$extension_indexWhere$extension_len) {
                     var10000 = -1;
                     break;
                  }

                  char var17 = decName$1.charAt(exists$extension_indexWhere$extension_i);
                  if (Chars.isOperatorPart$(Chars$.MODULE$, (char)var17)) {
                     var10000 = exists$extension_indexWhere$extension_i;
                     break;
                  }

                  ++exists$extension_indexWhere$extension_i;
               }

               if (var10000 == -1) {
                  return s;
               }
            }

            int exists$extension_indexWhere$extension_from = 0;
            int exists$extension_indexWhere$extension_len = decName$1.length();
            int exists$extension_indexWhere$extension_i = exists$extension_indexWhere$extension_from;

            while(true) {
               if (exists$extension_indexWhere$extension_i < exists$extension_indexWhere$extension_len) {
                  char var18 = decName$1.charAt(exists$extension_indexWhere$extension_i);
                  if (!Chars.isScalaLetter$(Chars$.MODULE$, (char)var18)) {
                     ++exists$extension_indexWhere$extension_i;
                     continue;
                  }

                  var10000 = exists$extension_indexWhere$extension_i;
               } else {
                  var10000 = -1;
               }

               if (var10000 != -1 && !.MODULE$.contains$extension(decName$1, bslash$1)) {
                  return (new StringBuilder(2)).append("`").append(s).append("`").toString();
               }
               break;
            }
         }

         return s;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$originalTypeTrees$1(final CodePrinter $this, final Trees.Tree x$11) {
         return !$this.isEmptyTree(x$11);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$originalTypeTrees$2(final Trees.Tree x0$1) {
         if (x0$1 instanceof Trees.TypeTree) {
            Trees.TypeTree var1 = (Trees.TypeTree)x0$1;
            if (var1.original() != null) {
               return var1.original();
            }
         }

         return x0$1;
      }

      private final List removeDefaultTraitsFromList$1(final List trees, final List traitsToRemove) {
         while(true) {
            if (trees != null) {
               Option var3 = scala.package..MODULE$.$colon$plus().unapply(trees);
               if (!var3.isEmpty()) {
                  List init = (List)((Tuple2)var3.get())._1();
                  Trees.Tree last = (Trees.Tree)((Tuple2)var3.get())._2();
                  boolean var6 = false;
                  Trees.Select var7 = null;
                  if (last instanceof Trees.Select) {
                     var6 = true;
                     var7 = (Trees.Select)last;
                     Trees.Tree var8 = var7.qualifier();
                     Names.Name name = var7.name();
                     if (var8 instanceof Trees.Select) {
                        Trees.Select var10 = (Trees.Select)var8;
                        Trees.Tree var11 = var10.qualifier();
                        Names.Name var12 = var10.name();
                        if (var11 instanceof Trees.Ident) {
                           label58: {
                              Names.Name var13 = ((Trees.Ident)var11).name();
                              Names.TermName var10000 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().scala_();
                              if (var10000 == null) {
                                 if (var13 != null) {
                                    break label58;
                                 }
                              } else if (!var10000.equals(var13)) {
                                 break label58;
                              }

                              var10000 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().PACKAGE();
                              if (var10000 == null) {
                                 if (var12 != null) {
                                    break label58;
                                 }
                              } else if (!var10000.equals(var12)) {
                                 break label58;
                              }

                              if (traitsToRemove.contains(name)) {
                                 traitsToRemove = traitsToRemove;
                                 trees = init;
                                 continue;
                              }
                           }
                        }
                     }
                  }

                  if (var6) {
                     Trees.Tree var14 = var7.qualifier();
                     Names.Name name = var7.name();
                     if (var14 instanceof Trees.Ident) {
                        Names.Name var16 = ((Trees.Ident)var14).name();
                        Names.TermName var18 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().scala_();
                        if (var18 == null) {
                           if (var16 != null) {
                              return trees;
                           }
                        } else if (!var18.equals(var16)) {
                           return trees;
                        }

                        if (traitsToRemove.contains(name)) {
                           traitsToRemove = traitsToRemove;
                           trees = init;
                           continue;
                        }
                     }
                  }

                  return trees;
               }
            }

            return trees;
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$removeDefaultClassesFromList$1(final CodePrinter $this, final List classesToRemove$1, final Trees.Tree x0$1) {
         if (x0$1 instanceof Trees.Select) {
            Trees.Select var3 = (Trees.Select)x0$1;
            Trees.Tree var4 = var3.qualifier();
            Names.Name name = var3.name();
            if (var4 instanceof Trees.Ident) {
               Names.Name sc = ((Trees.Ident)var4).name();
               if (classesToRemove$1.contains(name)) {
                  Names.TermName var7 = $this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().scala_();
                  if (sc == null) {
                     if (var7 != null) {
                        return true;
                     }
                  } else if (!sc.equals(var7)) {
                     return true;
                  }

                  return false;
               }

               return true;
            }
         }

         if (x0$1 instanceof Trees.TypeTree) {
            Trees.TypeTree var8 = (Trees.TypeTree)x0$1;
            if (var8.tpe() != null) {
               if (!classesToRemove$1.contains($this.scala$reflect$internal$Printers$CodePrinter$$$outer().newTypeName(var8.tpe().toString()))) {
                  return true;
               }

               return false;
            }
         }

         return true;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$printColumn$3(final CodePrinter $this, final Trees.Tree x$12) {
         return !$this.syntheticToRemove(x$12);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$printModifiers$2(final Trees.Tree x0$1) {
         return x0$1 instanceof Trees.ClassDef ? true : (x0$1 instanceof Trees.ModuleDef ? true : (x0$1 instanceof Trees.Template ? true : x0$1 instanceof Trees.PackageDef));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$printModifiers$3() {
         return false;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$printModifiers$1(final Option x$13) {
         Option getOrElse_this = (Option)(x$13.isEmpty() ? scala.None..MODULE$ : new Some($anonfun$printModifiers$2((Trees.Tree)x$13.get())));
         Object var10000 = getOrElse_this.isEmpty() ? false : getOrElse_this.get();
         getOrElse_this = null;
         return BoxesRunTime.unboxToBoolean(var10000);
      }

      private final boolean modsAccepted$1() {
         for(List exists_these = new scala.collection.immutable..colon.colon(this.currentTree(), new scala.collection.immutable..colon.colon(this.currentParent(), scala.collection.immutable.Nil..MODULE$)); !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
            if ($anonfun$printModifiers$1((Option)exists_these.head())) {
               return true;
            }
         }

         return false;
      }

      // $FF: synthetic method
      public static final void $anonfun$printModifiers$4(final CodePrinter $this, final Trees.Modifiers mods$2, final long flag) {
         if (mods$2.hasFlag(flag)) {
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(1)).append(mods$2.flagBitsToString(flag)).append(" ").toString()}));
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$printArgss$1(final CodePrinter $this, final List argss$1, final List x) {
         if (x.isEmpty()) {
            if (argss$1 == null) {
               throw null;
            }

            if (SeqOps.size$(argss$1) == 1) {
               return;
            }
         }

         $this.printRow(x, "(", ", ", ")");
      }

      // $FF: synthetic method
      public static final void $anonfun$printAnnotations$2(final CodePrinter $this, final Trees.Tree annot) {
         $this.printAnnot(annot);
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{" "}));
      }

      // $FF: synthetic method
      public static final void $anonfun$processTreePrinting$2(final CodePrinter $this, final Trees.ValDef x$14) {
         $this.printVParam(x$14, true);
      }

      private final void printConstrParams$1(final List ts) {
         this.parenthesize(this.parenthesize$default$1(), this.parenthesize$default$2(), this.parenthesize$default$3(), (JFunction0.mcV.sp)() -> {
            this.printImplicitInParamsList(ts);
            Function1 var10001 = (x$14) -> {
               $anonfun$processTreePrinting$2(this, x$14);
               return BoxedUnit.UNIT;
            };
            JFunction0.mcV.sp printSeq_printsep = () -> this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{", "}));
            Function1 printSeq_printelem = var10001;
            List printSeq_ls = ts;

            while(true) {
               if (printSeq_ls != null) {
                  List var10000 = scala.package..MODULE$.List();
                  if (var10000 == null) {
                     throw null;
                  }

                  List printSeq_unapplySeq_this = var10000;
                  SeqOps var19 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
                  Object var16 = null;
                  SeqOps var5 = var19;
                  SeqFactory.UnapplySeqWrapper var20 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var30 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var5);
                  var20 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var20 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_lengthCompare$extension_len = 0;
                  if (var5.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                     return;
                  }
               }

               if (printSeq_ls != null) {
                  List var23 = scala.package..MODULE$.List();
                  if (var23 == null) {
                     throw null;
                  }

                  List printSeq_unapplySeq_this = var23;
                  SeqOps var24 = SeqFactory.unapplySeq$(printSeq_unapplySeq_this, printSeq_ls);
                  Object var17 = null;
                  SeqOps var6 = var24;
                  SeqFactory.UnapplySeqWrapper var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var31 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var6);
                  var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int printSeq_lengthCompare$extension_len = 1;
                  if (var6.lengthCompare(printSeq_lengthCompare$extension_len) == 0) {
                     var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var25 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int printSeq_apply$extension_i = 0;
                     Trees.ValDef var18 = (Trees.ValDef)var6.apply(printSeq_apply$extension_i);
                     this.printVParam(var18, true);
                     return;
                  }
               }

               if (!(printSeq_ls instanceof scala.collection.immutable..colon.colon)) {
                  throw new MatchError(printSeq_ls);
               }

               scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)printSeq_ls;
               Object printSeq_x = var7.head();
               List printSeq_rest = var7.next$access$1();
               Trees.ValDef var15 = (Trees.ValDef)printSeq_x;
               this.printVParam(var15, true);
               $anonfun$processTreePrinting$3(this);
               printSeq_printsep = printSeq_printsep;
               printSeq_printelem = printSeq_printelem;
               printSeq_ls = printSeq_rest;
            }
         });
      }

      // $FF: synthetic method
      public static final void $anonfun$processTreePrinting$4(final CodePrinter $this, final List ts) {
         $this.printConstrParams$1(ts);
      }

      // $FF: synthetic method
      public static final void $anonfun$processTreePrinting$5(final CodePrinter $this, final Trees.Tree x$15) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x$15}));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$processTreePrinting$17(final CodePrinter $this, final Trees.Tree x0$2) {
         if (x0$2 instanceof Trees.TypeDef) {
            Trees.TypeDef var2 = (Trees.TypeDef)x0$2;
            return $this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isEarlyDef(var2);
         } else {
            return false;
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$processTreePrinting$18(final CodePrinter $this, final Trees.Tree x0$3) {
         if (x0$3 instanceof Trees.ValDef) {
            Trees.ValDef var2 = (Trees.ValDef)x0$3;
            return !var2.mods().isParamAccessor() && !$this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isEarlyValDef(var2);
         } else if (!(x0$3 instanceof Trees.DefDef)) {
            if (x0$3 instanceof Trees.TypeDef) {
               Trees.TypeDef var4 = (Trees.TypeDef)x0$3;
               return !$this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isEarlyDef(var4);
            } else {
               return !$this.scala$reflect$internal$Printers$CodePrinter$$$outer().EmptyTree().equals(x0$3);
            }
         } else {
            Names.TermName var10000 = ((Trees.DefDef)x0$3).name();
            Names.TermName var3 = $this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().MIXIN_CONSTRUCTOR();
            if (var10000 == null) {
               if (var3 != null) {
                  return true;
               }
            } else if (!var10000.equals(var3)) {
               return true;
            }

            return false;
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$processTreePrinting$19(final CodePrinter $this, final Trees.Tree x0$4) {
         if (!(x0$4 instanceof Trees.DefDef)) {
            return true;
         } else {
            Names.TermName var10000 = ((Trees.DefDef)x0$4).name();
            Names.TermName var2 = $this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().CONSTRUCTOR();
            if (var10000 == null) {
               if (var2 != null) {
                  return true;
               }
            } else if (!var10000.equals(var2)) {
               return true;
            }

            return false;
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$processTreePrinting$20(final Trees.Tree x$21) {
         return x$21 instanceof Trees.Match;
      }

      private final void insertBraces$1(final Function0 body) {
         if (this.parentsStack().nonEmpty() && ((IterableOnceOps)this.parentsStack().tail()).exists((x$21) -> BoxesRunTime.boxToBoolean($anonfun$processTreePrinting$20(x$21)))) {
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"("}));
            body.apply$mcV$sp();
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{")"}));
         } else {
            body.apply$mcV$sp();
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$processTreePrinting$23(final Trees.Tree x$22) {
         return x$22 instanceof Trees.Star;
      }

      private final void printTp$1(final Trees.Tree tp$5) {
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"(", tp$5, ")"}));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$processTreePrinting$25(final CodePrinter $this, final Trees.Tree x$23) {
         return $this.isEmptyTree(x$23);
      }

      private final boolean checkRootPackage$1(final Trees.Tree tr) {
         while(true) {
            Option var2 = this.currentParent();
            if (!(var2 instanceof Some) || !(((Some)var2).value() instanceof Trees.PackageDef)) {
               if (tr instanceof Trees.Select) {
                  tr = ((Trees.Select)tr).qualifier();
                  continue;
               }

               boolean var5;
               if (!(tr instanceof Trees.Ident ? true : tr instanceof Trees.This)) {
                  var5 = false;
               } else {
                  label61: {
                     label41: {
                        Symbols.Symbol sym = tr.symbol();
                        if (tr.hasExistingSymbol() && sym.hasPackageFlag()) {
                           Names.Name var10000 = sym.name();
                           Names.TermName var4 = this.scala$reflect$internal$Printers$CodePrinter$$$outer().nme().ROOTPKG();
                           if (var10000 == null) {
                              if (var4 != null) {
                                 break label41;
                              }
                           } else if (!var10000.equals(var4)) {
                              break label41;
                           }
                        }

                        var5 = false;
                        break label61;
                     }

                     var5 = true;
                  }
               }

               if (var5) {
                  return true;
               }
            }

            return false;
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$processTreePrinting$30(final CodePrinter $this, final String x$24) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x$24}));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$processTreePrinting$33(final CodePrinter $this, final Trees.Tree tpt) {
         return $this.scala$reflect$internal$Printers$CodePrinter$$$outer().treeInfo().isByNameParamType(tpt);
      }

      public CodePrinter(final PrintWriter out, final boolean printRootPkg) {
         super(out);
         this.printRootPkg = printRootPkg;
         this.parentsStack = (Stack)IterableFactory.apply$(scala.collection.mutable.Stack..MODULE$, scala.collection.immutable.Nil..MODULE$);
         this.commentsRequired = true;
         this.defaultClasses = new scala.collection.immutable..colon.colon((Names.TypeName)Printers.this.tpnme().AnyRef(), new scala.collection.immutable..colon.colon(Printers.this.tpnme().Object(), scala.collection.immutable.Nil..MODULE$));
         this.defaultTraitsForCase = new scala.collection.immutable..colon.colon(Printers.this.tpnme().Product(), new scala.collection.immutable..colon.colon(Printers.this.tpnme().Serializable(), scala.collection.immutable.Nil..MODULE$));
      }

      // $FF: synthetic method
      public static final Object $anonfun$printedName$1$adapted(final Object x) {
         return BoxesRunTime.boxToBoolean($anonfun$printedName$1(BoxesRunTime.unboxToChar(x)));
      }

      // $FF: synthetic method
      public static final Object $anonfun$originalTypeTrees$1$adapted(final CodePrinter $this, final Trees.Tree x$11) {
         return BoxesRunTime.boxToBoolean($anonfun$originalTypeTrees$1($this, x$11));
      }

      // $FF: synthetic method
      public static final Object $anonfun$removeDefaultClassesFromList$1$adapted(final CodePrinter $this, final List classesToRemove$1, final Trees.Tree x0$1) {
         return BoxesRunTime.boxToBoolean($anonfun$removeDefaultClassesFromList$1($this, classesToRemove$1, x0$1));
      }

      // $FF: synthetic method
      public static final Object $anonfun$printColumn$3$adapted(final CodePrinter $this, final Trees.Tree x$12) {
         return BoxesRunTime.boxToBoolean($anonfun$printColumn$3($this, x$12));
      }

      // $FF: synthetic method
      public static final Object $anonfun$printArgss$1$adapted(final CodePrinter $this, final List argss$1, final List x) {
         $anonfun$printArgss$1($this, argss$1, x);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$printAnnotations$2$adapted(final CodePrinter $this, final Trees.Tree annot) {
         $anonfun$printAnnotations$2($this, annot);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$printedName$2$adapted(final List brackets$1, final Function1 isDot$1, final Object ch) {
         return BoxesRunTime.boxToBoolean($anonfun$printedName$2(brackets$1, isDot$1, BoxesRunTime.unboxToChar(ch)));
      }

      // $FF: synthetic method
      public static final Object $anonfun$printedName$3$adapted(final Object c) {
         return BoxesRunTime.boxToBoolean($anonfun$printedName$3(BoxesRunTime.unboxToChar(c)));
      }

      // $FF: synthetic method
      public static final Object $anonfun$printedName$4$adapted(final Object c) {
         return BoxesRunTime.boxToBoolean($anonfun$printedName$4(BoxesRunTime.unboxToChar(c)));
      }

      // $FF: synthetic method
      public static final Object $anonfun$printModifiers$2$adapted(final Trees.Tree x0$1) {
         return BoxesRunTime.boxToBoolean($anonfun$printModifiers$2(x0$1));
      }

      // $FF: synthetic method
      public static final Object $anonfun$printModifiers$1$adapted(final Option x$13) {
         return BoxesRunTime.boxToBoolean($anonfun$printModifiers$1(x$13));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      public class EmptyTypeTree$ {
         // $FF: synthetic field
         private final CodePrinter $outer;

         public boolean unapply(final Trees.TypeTree tt) {
            return tt != null && this.$outer.scala$reflect$internal$Printers$CodePrinter$$$outer().build().SyntacticEmptyTypeTree().unapply(tt) && (tt.wasEmpty() || tt.isEmpty());
         }

         public EmptyTypeTree$() {
            if (CodePrinter.this == null) {
               throw null;
            } else {
               this.$outer = CodePrinter.this;
               super();
            }
         }
      }
   }

   public class ConsoleWriter$ extends Writer {
      public void write(final String str) {
         scala.Console..MODULE$.print(str);
      }

      public void write(final char[] cbuf, final int off, final int len) {
         String write_str = new String(cbuf, off, len);
         scala.Console..MODULE$.print(write_str);
      }

      public void close() {
      }

      public void flush() {
      }
   }

   private class Footnotes {
      private final Map index;
      private final Map counters;
      private final Map footnotes;
      // $FF: synthetic field
      public final SymbolTable $outer;

      private WeakHashMap classIndex(final ClassTag evidence$1) {
         package var10001 = scala.reflect.package..MODULE$;
         return (WeakHashMap)this.index.getOrElseUpdate(evidence$1.runtimeClass(), () -> (WeakHashMap)scala.collection.mutable.WeakHashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      }

      private int nextCounter(final ClassTag evidence$2) {
         package var10000 = scala.reflect.package..MODULE$;
         Class clazz = evidence$2.runtimeClass();
         this.counters.getOrElseUpdate(clazz, (JFunction0.mcI.sp)() -> 0);
         this.counters.update(clazz, BoxesRunTime.unboxToInt(this.counters.apply(clazz)) + 1);
         return BoxesRunTime.unboxToInt(this.counters.apply(clazz));
      }

      private SortedSet classFootnotes(final ClassTag evidence$3) {
         package var10001 = scala.reflect.package..MODULE$;
         return (SortedSet)this.footnotes.getOrElseUpdate(evidence$3.runtimeClass(), () -> (SortedSet)scala.collection.mutable.SortedSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.math.Ordering.Int..MODULE$));
      }

      public int put(final Object any, final ClassTag evidence$4) {
         int index = BoxesRunTime.unboxToInt(this.classIndex(evidence$4).getOrElseUpdate(any, (JFunction0.mcI.sp)() -> this.nextCounter(evidence$4)));
         SortedSet var10000 = this.classFootnotes(evidence$4);
         Integer $plus$eq_elem = index;
         if (var10000 == null) {
            throw null;
         } else {
            var10000.addOne($plus$eq_elem);
            return index;
         }
      }

      public List get(final ClassTag evidence$5) {
         List var10000 = this.classFootnotes(evidence$5).toList();
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               return scala.collection.immutable.Nil..MODULE$;
            } else {
               Object var7 = map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$get$1(this, evidence$5, BoxesRunTime.unboxToInt(var7)), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var7 = map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$get$1(this, evidence$5, BoxesRunTime.unboxToInt(var7)), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      }

      public void print(final scala.reflect.api.Printers.TreePrinter printer, final ClassTag evidence$6) {
         List footnotes = this.get(evidence$6);
         if (footnotes.nonEmpty()) {
            printer.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{System.lineSeparator()}));
            List var10000 = (List)StrictOptimizedIterableOps.zipWithIndex$(footnotes);
            if (var10000 == null) {
               throw null;
            } else {
               for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                  Tuple2 var5 = (Tuple2)foreach_these.head();
                  $anonfun$print$2(printer, footnotes, var5);
               }

            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Printers$Footnotes$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$get$2(final int fi$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            return x0$1._2$mcI$sp() == fi$1;
         } else {
            throw new MatchError((Object)null);
         }
      }

      // $FF: synthetic method
      public static final Tuple2 $anonfun$get$1(final Footnotes $this, final ClassTag evidence$5$1, final int fi) {
         return new Tuple2(fi, ((Tuple2)$this.classIndex(evidence$5$1).find((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$get$2(fi, x0$1))).get())._1());
      }

      // $FF: synthetic method
      public static final void $anonfun$print$2(final scala.reflect.api.Printers.TreePrinter printer$1, final List footnotes$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            Tuple2 var3 = (Tuple2)x0$1._1();
            int ii = x0$1._2$mcI$sp();
            if (var3 != null) {
               int fi = var3._1$mcI$sp();
               Object any = var3._2();
               printer$1.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"[", fi, "] ", any}));
               if (ii < footnotes$1.length() - 1) {
                  printer$1.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{System.lineSeparator()}));
                  return;
               }

               return;
            }
         }

         throw new MatchError(x0$1);
      }

      public Footnotes() {
         if (Printers.this == null) {
            throw null;
         } else {
            this.$outer = Printers.this;
            super();
            this.index = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            this.counters = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            this.footnotes = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         }
      }

      // $FF: synthetic method
      public static final Tuple2 $anonfun$get$1$adapted(final Footnotes $this, final ClassTag evidence$5$1, final Object fi) {
         return $anonfun$get$1($this, evidence$5$1, BoxesRunTime.unboxToInt(fi));
      }

      // $FF: synthetic method
      public static final Object $anonfun$print$2$adapted(final scala.reflect.api.Printers.TreePrinter printer$1, final List footnotes$1, final Tuple2 x0$1) {
         $anonfun$print$2(printer$1, footnotes$1, x0$1);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class RawTreePrinter implements scala.reflect.api.Printers.TreePrinter {
      private final PrintWriter out;
      private int depth;
      private boolean printTypesInFootnotes;
      private boolean printingFootnotes;
      private final Footnotes footnotes;
      private boolean printTypes;
      private boolean printIds;
      private boolean printOwners;
      private boolean printKinds;
      private boolean printMirrors;
      private boolean printPositions;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public scala.reflect.api.Printers.TreePrinter withTypes() {
         return scala.reflect.api.Printers.TreePrinter.withTypes$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutTypes() {
         return scala.reflect.api.Printers.TreePrinter.withoutTypes$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withIds() {
         return scala.reflect.api.Printers.TreePrinter.withIds$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutIds() {
         return scala.reflect.api.Printers.TreePrinter.withoutIds$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withOwners() {
         return scala.reflect.api.Printers.TreePrinter.withOwners$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutOwners() {
         return scala.reflect.api.Printers.TreePrinter.withoutOwners$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withKinds() {
         return scala.reflect.api.Printers.TreePrinter.withKinds$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutKinds() {
         return scala.reflect.api.Printers.TreePrinter.withoutKinds$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withMirrors() {
         return scala.reflect.api.Printers.TreePrinter.withMirrors$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutMirrors() {
         return scala.reflect.api.Printers.TreePrinter.withoutMirrors$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withPositions() {
         return scala.reflect.api.Printers.TreePrinter.withPositions$(this);
      }

      public scala.reflect.api.Printers.TreePrinter withoutPositions() {
         return scala.reflect.api.Printers.TreePrinter.withoutPositions$(this);
      }

      public boolean printTypes() {
         return this.printTypes;
      }

      public void printTypes_$eq(final boolean x$1) {
         this.printTypes = x$1;
      }

      public boolean printIds() {
         return this.printIds;
      }

      public void printIds_$eq(final boolean x$1) {
         this.printIds = x$1;
      }

      public boolean printOwners() {
         return this.printOwners;
      }

      public void printOwners_$eq(final boolean x$1) {
         this.printOwners = x$1;
      }

      public boolean printKinds() {
         return this.printKinds;
      }

      public void printKinds_$eq(final boolean x$1) {
         this.printKinds = x$1;
      }

      public boolean printMirrors() {
         return this.printMirrors;
      }

      public void printMirrors_$eq(final boolean x$1) {
         this.printMirrors = x$1;
      }

      public boolean printPositions() {
         return this.printPositions;
      }

      public void printPositions_$eq(final boolean x$1) {
         this.printPositions = x$1;
      }

      public void print(final Seq args) {
         if (this.depth == 0 && args.length() == 1 && args.apply(0) != null && args.apply(0) instanceof Types.Type) {
            this.printTypesInFootnotes = false;
         }

         ++this.depth;
         args.foreach((x0$1) -> {
            $anonfun$print$3(this, x0$1);
            return BoxedUnit.UNIT;
         });
         --this.depth;
         if (this.depth == 0 && !this.printingFootnotes) {
            this.printingFootnotes = true;
            this.footnotes.print(this, this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().TypeTagg());
            this.footnotes.print(this, scala.reflect.ClassTag..MODULE$.apply(Mirror.class));
            this.printingFootnotes = false;
         }
      }

      public void printProduct(final Product p, final Function1 preamble, final Function1 body, final Function1 postamble) {
         preamble.apply(p);
         List x$1 = p.productIterator().toList();
         Function0 x$3 = () -> this.printIterable$default$2();
         Function0 x$4 = () -> this.printIterable$default$4();
         this.printIterable(x$1, x$3, body, x$4);
         postamble.apply(p);
      }

      public Function1 printProduct$default$2() {
         return (p) -> {
            $anonfun$printProduct$default$2$1(this, p);
            return BoxedUnit.UNIT;
         };
      }

      public Function1 printProduct$default$3() {
         return (x$26) -> {
            $anonfun$printProduct$default$3$1(this, x$26);
            return BoxedUnit.UNIT;
         };
      }

      public Function1 printProduct$default$4() {
         return (p) -> {
            $anonfun$printProduct$default$4$1(this, p);
            return BoxedUnit.UNIT;
         };
      }

      public void printIterable(final List iterable, final Function0 preamble, final Function1 body, final Function0 postamble) {
         preamble.apply$mcV$sp();
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"("}));
         Iterator it = iterable.iterator();

         while(it.hasNext()) {
            body.apply(it.next());
            this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{it.hasNext() ? ", " : ""}));
         }

         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{")"}));
         postamble.apply$mcV$sp();
      }

      public void printIterable$default$2() {
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{""}));
      }

      public Function1 printIterable$default$3() {
         return (x$27) -> {
            $anonfun$printIterable$default$3$1(this, x$27);
            return BoxedUnit.UNIT;
         };
      }

      public void printIterable$default$4() {
         this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{""}));
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Printers$RawTreePrinter$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public scala.reflect.api.Printers scala$reflect$api$Printers$TreePrinter$$$outer() {
         return this.scala$reflect$internal$Printers$RawTreePrinter$$$outer();
      }

      private final boolean hasSymbolField$1(final Trees.Tree x6$1) {
         if (x6$1.hasSymbolField()) {
            Symbols.Symbol var10000 = x6$1.symbol();
            Symbols.NoSymbol var2 = this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoSymbol();
            if (var10000 == null) {
               if (var2 != null) {
                  return true;
               }
            } else if (!var10000.equals(var2)) {
               return true;
            }
         }

         return false;
      }

      // $FF: synthetic method
      public static final void $anonfun$print$4(final RawTreePrinter $this, final Trees.Tree x6$1, final Product x$25) {
         if ($this.printPositions()) {
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x6$1.pos().show()}));
         }

         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x6$1.productPrefix()}));
         if ($this.printTypes() && x6$1.tpe() != null) {
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x6$1.tpe()}));
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$print$5(final RawTreePrinter $this, final boolean isError$1, final Trees.Tree x6$1, final Object x0$2) {
         boolean var4 = false;
         Constants.Constant var5 = null;
         if (x0$2 instanceof Names.Name && ((Names.Name)x0$2).scala$reflect$internal$Names$Name$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Names.Name var6 = (Names.Name)x0$2;
            if (isError$1) {
               if (isError$1) {
                  $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"<"}));
               }

               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var6}));
               if (isError$1) {
                  $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{": error>"}));
               }
            } else if ($this.hasSymbolField$1(x6$1)) {
               if (!(x6$1 instanceof Trees.RefTree)) {
                  if (x6$1 instanceof Trees.DefTree) {
                     $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x6$1.symbol()}));
                  } else {
                     $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x6$1.symbol().name()}));
                  }
               } else {
                  Trees.RefTree var7;
                  label56: {
                     var7 = (Trees.RefTree)x6$1;
                     Names.Name var10000 = x6$1.symbol().name();
                     Names.Name var8 = var7.name();
                     if (var10000 == null) {
                        if (var8 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var8)) {
                        break label56;
                     }

                     $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x6$1.symbol()}));
                     return;
                  }

                  $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"[", x6$1.symbol(), " aka ", var7.name(), "]"}));
               }
            } else {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var6}));
            }
         } else {
            if (x0$2 instanceof Constants.Constant && ((Constants.Constant)x0$2).scala$reflect$internal$Constants$Constant$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
               var4 = true;
               var5 = (Constants.Constant)x0$2;
               Object s = var5.value();
               if (s instanceof String) {
                  String var10 = (String)s;
                  $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(12)).append("Constant(\"").append(var10).append("\")").toString()}));
                  return;
               }
            }

            if (var4 && var5.value() == null) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"Constant(null)"}));
            } else if (var4) {
               Object value = var5.value();
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{(new StringBuilder(10)).append("Constant(").append(value).append(")").toString()}));
            } else {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x0$2}));
            }
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$print$6(final RawTreePrinter $this, final Product x0$3) {
         if (x0$3 instanceof Trees.TypeTree && ((Trees.TypeTree)x0$3).scala$reflect$internal$Trees$TypeTree$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Trees.TypeTree var2 = (Trees.TypeTree)x0$3;
            if (var2.original() != null) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{".setOriginal(", var2.original(), ")"}));
               return;
            }
         }

      }

      // $FF: synthetic method
      public static final void $anonfun$print$3(final RawTreePrinter $this, final Object x0$1) {
         if (x0$1 instanceof Exprs.Expr && ((Exprs.Expr)x0$1).scala$reflect$api$Exprs$Expr$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Exprs.Expr var2 = (Exprs.Expr)x0$1;
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"Expr"}));
            if ($this.printTypes()) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var2.staticType()}));
            }

            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"("}));
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var2.tree()}));
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{")"}));
         } else if ($this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().EmptyTree().equals(x0$1)) {
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"EmptyTree"}));
         } else if ($this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().noSelfType().equals(x0$1)) {
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"noSelfType"}));
         } else if ($this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().pendingSuperCall().equals(x0$1)) {
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"pendingSuperCall"}));
         } else if (x0$1 instanceof Trees.Tree && ((Trees.Tree)x0$1).scala$reflect$internal$Trees$Tree$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Trees.Tree var3 = (Trees.Tree)x0$1;
            boolean isError = $this.hasSymbolField$1(var3) && var3.symbol().name().string_$eq$eq($this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().nme().ERROR());
            $this.printProduct(var3, (x$25) -> {
               $anonfun$print$4($this, var3, x$25);
               return BoxedUnit.UNIT;
            }, (x0$2) -> {
               $anonfun$print$5($this, isError, var3, x0$2);
               return BoxedUnit.UNIT;
            }, (x0$3) -> {
               $anonfun$print$6($this, x0$3);
               return BoxedUnit.UNIT;
            });
         } else if (x0$1 instanceof Symbols.Symbol && ((Symbols.Symbol)x0$1).scala$reflect$internal$Symbols$Symbol$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Symbols.Symbol var5;
            label147: {
               label215: {
                  var5 = (Symbols.Symbol)x0$1;
                  Symbols.NoSymbol var6 = $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoSymbol();
                  if (var5 == null) {
                     if (var6 == null) {
                        break label215;
                     }
                  } else if (var5.equals(var6)) {
                     break label215;
                  }

                  if (!var5.isStatic() || !var5.isClass() && !var5.isModule()) {
                     $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var5.name()}));
                     break label147;
                  }

                  $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var5.fullName()}));
                  break label147;
               }

               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"NoSymbol"}));
            }

            if ($this.printIds()) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"#", var5.id()}));
            }

            if ($this.printOwners()) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"@", var5.owner().id()}));
            }

            if ($this.printKinds()) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"#", var5.abbreviatedKindString()}));
            }

            if ($this.printMirrors()) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"%M", $this.footnotes.put($this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().mirrorThatLoaded(var5), scala.reflect.ClassTag..MODULE$.apply(Mirror.class))}));
            }
         } else if (x0$1 instanceof TypeTags.TypeTag && ((TypeTags.TypeTag)x0$1).scala$reflect$api$TypeTags$TypeTag$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            TypeTags.TypeTag var7 = (TypeTags.TypeTag)x0$1;
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"TypeTag(", var7.tpe(), ")"}));
         } else if (x0$1 instanceof TypeTags.WeakTypeTag && ((TypeTags.WeakTypeTag)x0$1).scala$reflect$api$TypeTags$WeakTypeTag$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            TypeTags.WeakTypeTag var8 = (TypeTags.WeakTypeTag)x0$1;
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"WeakTypeTag(", var8.tpe(), ")"}));
         } else if (x0$1 instanceof Types.Type && ((Types.Type)x0$1).scala$reflect$internal$Types$Type$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Types.Type var9 = (Types.Type)x0$1;
            if ($this.printTypesInFootnotes && !$this.printingFootnotes) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"[", $this.footnotes.put(var9, $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().TypeTagg()), "]"}));
            } else if ($this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoType().equals(var9)) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"NoType"}));
            } else if ($this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoPrefix().equals(var9)) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"NoPrefix"}));
            } else {
               $this.printProduct((Product)var9, $this.printProduct$default$2(), $this.printProduct$default$3(), $this.printProduct$default$4());
            }
         } else if (x0$1 instanceof Trees.Modifiers && ((Trees.Modifiers)x0$1).scala$reflect$internal$Trees$Modifiers$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Trees.Modifiers var10;
            label177: {
               var10 = (Trees.Modifiers)x0$1;
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"Modifiers("}));
               if (var10.flags() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().NoFlags()) {
                  label175: {
                     Names.Name var10000 = var10.privateWithin();
                     Names.Name var11 = $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().tpnme().EMPTY();
                     if (var10000 == null) {
                        if (var11 != null) {
                           break label175;
                        }
                     } else if (!var10000.equals(var11)) {
                        break label175;
                     }

                     if (!var10.annotations().nonEmpty()) {
                        break label177;
                     }
                  }
               }

               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{$this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().show(var10.flags())}));
            }

            label167: {
               label166: {
                  Names.Name var17 = var10.privateWithin();
                  Names.Name var12 = $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().tpnme().EMPTY();
                  if (var17 == null) {
                     if (var12 != null) {
                        break label166;
                     }
                  } else if (!var17.equals(var12)) {
                     break label166;
                  }

                  if (!var10.annotations().nonEmpty()) {
                     break label167;
                  }
               }

               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{", "}));
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var10.privateWithin()}));
            }

            if (var10.annotations().nonEmpty()) {
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{", "}));
               $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var10.annotations()}));
            }

            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{")"}));
         } else if (x0$1 instanceof Names.Name && ((Names.Name)x0$1).scala$reflect$internal$Names$Name$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Names.Name var13 = (Names.Name)x0$1;
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{$this.scala$reflect$internal$Printers$RawTreePrinter$$$outer().show(var13)}));
         } else if (x0$1 instanceof Scopes.Scope && ((Scopes.Scope)x0$1).scala$reflect$internal$Scopes$Scope$$$outer() == $this.scala$reflect$internal$Printers$RawTreePrinter$$$outer()) {
            Scopes.Scope var14 = (Scopes.Scope)x0$1;
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"Scope"}));
            $this.printIterable(var14.toList(), (JFunction0.mcV.sp)() -> $this.printIterable$default$2(), $this.printIterable$default$3(), (JFunction0.mcV.sp)() -> $this.printIterable$default$4());
         } else if (x0$1 instanceof List) {
            List var15 = (List)x0$1;
            $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"List"}));
            $this.printIterable(var15, (JFunction0.mcV.sp)() -> $this.printIterable$default$2(), $this.printIterable$default$3(), (JFunction0.mcV.sp)() -> $this.printIterable$default$4());
         } else if (x0$1 instanceof Product) {
            Product var16 = (Product)x0$1;
            $this.printProduct(var16, $this.printProduct$default$2(), $this.printProduct$default$3(), $this.printProduct$default$4());
         } else {
            $this.out.print(x0$1);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$printProduct$default$2$1(final RawTreePrinter $this, final Product p) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{p.productPrefix()}));
      }

      // $FF: synthetic method
      public static final void $anonfun$printProduct$default$3$1(final RawTreePrinter $this, final Object x$26) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x$26}));
      }

      // $FF: synthetic method
      public static final void $anonfun$printProduct$default$4$1(final RawTreePrinter $this, final Product p) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{""}));
      }

      // $FF: synthetic method
      public static final void $anonfun$printIterable$default$3$1(final RawTreePrinter $this, final Object x$27) {
         $this.print(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x$27}));
      }

      public RawTreePrinter(final PrintWriter out) {
         this.out = out;
         if (Printers.this == null) {
            throw null;
         } else {
            this.$outer = Printers.this;
            super();
            scala.reflect.api.Printers.TreePrinter.$init$(this);
            this.depth = 0;
            this.printTypesInFootnotes = true;
            this.printingFootnotes = false;
            this.footnotes = Printers.this.new Footnotes();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
