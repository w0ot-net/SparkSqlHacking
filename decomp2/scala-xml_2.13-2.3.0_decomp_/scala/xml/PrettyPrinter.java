package scala.xml;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Enumeration;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\reb\u0001B/_\u0001\rD\u0001\u0002\u001b\u0001\u0003\u0002\u0003\u0006I!\u001b\u0005\tY\u0002\u0011\t\u0011)A\u0005S\"AQ\u000e\u0001B\u0001B\u0003%a\u000eC\u0003r\u0001\u0011\u0005!\u000fC\u0003r\u0001\u0011\u0005\u0001\u0010C\u0004|\u0001\t\u0007I\u0011\u0001?\t\u000f\u0005-\u0001\u0001)A\u0005{\u001a1\u0011Q\u0002\u0001\u0001\u0003\u001fAa!\u001d\u0005\u0005\u0002\u0005\u0005bABA\u0014\u0001\u0001\tI\u0003\u0003\u0004r\u0015\u0011\u0005\u00111F\u0004\b\u0003_\u0001\u0001\u0012QA\u0019\r\u001d\t\u0019\u0004\u0001EA\u0003kAa!]\u0007\u0005\u0002\u0005U\u0003bBA,\u001b\u0011\u0005\u0013\u0011\f\u0005\n\u0003Wj\u0011\u0011!C!\u0003[B\u0011\"a\u001d\u000e\u0003\u0003%\t!!\u001e\t\u0013\u0005]T\"!A\u0005\u0002\u0005e\u0004\"CAC\u001b\u0005\u0005I\u0011IAD\u0011%\t)*DA\u0001\n\u0003\t9\nC\u0005\u0002\u001c6\t\t\u0011\"\u0011\u0002\u001e\u001a1\u0011q\u0014\u0001A\u0003CC!\"a)\u0017\u0005+\u0007I\u0011AA;\u0011%\t)K\u0006B\tB\u0003%\u0011\u000e\u0003\u0006\u0002(Z\u0011)\u001a!C\u0001\u0003SC!\"a+\u0017\u0005#\u0005\u000b\u0011BA.\u0011\u0019\th\u0003\"\u0001\u0002.\"I\u0011Q\u0017\f\u0002\u0002\u0013\u0005\u0011q\u0017\u0005\n\u0003{3\u0012\u0013!C\u0001\u0003\u007fC\u0011\"!6\u0017#\u0003%\t!a6\t\u0013\u0005-d#!A\u0005B\u00055\u0004\"CA:-\u0005\u0005I\u0011AA;\u0011%\t9HFA\u0001\n\u0003\tY\u000eC\u0005\u0002\u0006Z\t\t\u0011\"\u0011\u0002\b\"I\u0011Q\u0013\f\u0002\u0002\u0013\u0005\u0011q\u001c\u0005\n\u0003G4\u0012\u0011!C!\u0003KD\u0011\"a'\u0017\u0003\u0003%\t%!(\t\u0013\u0005]c#!A\u0005B\u0005%\b\"CAv-\u0005\u0005I\u0011IAw\u000f%\t\t\u0010AA\u0001\u0012\u0003\t\u0019PB\u0005\u0002 \u0002\t\t\u0011#\u0001\u0002v\"1\u0011/\u000bC\u0001\u0005\u001bA\u0011\"a\u0016*\u0003\u0003%)%!;\t\u0013\t=\u0011&!A\u0005\u0002\nE\u0001\"\u0003B\fS\u0005\u0005I\u0011\u0011B\r\r\u0019\u0011Y\u0003\u0001!\u0003.!Q\u0011q\u0015\u0018\u0003\u0016\u0004%\t!!+\t\u0015\u0005-fF!E!\u0002\u0013\tY\u0006\u0003\u0004r]\u0011\u0005!q\u0006\u0005\n\u0003ks\u0013\u0011!C\u0001\u0005kA\u0011\"!0/#\u0003%\t!a6\t\u0013\u0005-d&!A\u0005B\u00055\u0004\"CA:]\u0005\u0005I\u0011AA;\u0011%\t9HLA\u0001\n\u0003\u0011I\u0004C\u0005\u0002\u0006:\n\t\u0011\"\u0011\u0002\b\"I\u0011Q\u0013\u0018\u0002\u0002\u0013\u0005!Q\b\u0005\n\u0003Gt\u0013\u0011!C!\u0005\u0003B\u0011\"a'/\u0003\u0003%\t%!(\t\u0013\u0005]c&!A\u0005B\u0005%\b\"CAv]\u0005\u0005I\u0011\tB#\u000f%\u0011I\u0005AA\u0001\u0012\u0003\u0011YEB\u0005\u0003,\u0001\t\t\u0011#\u0001\u0003N!1\u0011O\u0010C\u0001\u0005+B\u0011\"a\u0016?\u0003\u0003%)%!;\t\u0013\t=a(!A\u0005\u0002\n]\u0003\"\u0003B\f}\u0005\u0005I\u0011\u0011B.\u0011%\u0011\t\u0007\u0001a\u0001\n#\u0011\u0019\u0007C\u0005\u0003n\u0001\u0001\r\u0011\"\u0005\u0003p!A!\u0011\u0010\u0001!B\u0013\u0011)\u0007C\u0005\u0003|\u0001\u0001\r\u0011\"\u0005\u0002v!I!Q\u0010\u0001A\u0002\u0013E!q\u0010\u0005\b\u0005\u0007\u0003\u0001\u0015)\u0003j\u0011\u001d\u0011)\t\u0001C\t\u0005\u000fCqA!#\u0001\t#\u0011Y\tC\u0004\u0003\u0014\u0002!\tB!&\t\u000f\tm\u0005\u0001\"\u0005\u0003\u001e\"9!1\u0015\u0001\u0005\u0012\t\u001d\u0005b\u0002BS\u0001\u0011E!q\u0015\u0005\b\u0005g\u0003A\u0011\u0003B[\u0011\u001d\u0011)\r\u0001C\t\u0005\u000fDqAa3\u0001\t#\u0011i\rC\u0004\u0003R\u0002!\tBa5\t\u000f\te\u0007\u0001\"\u0003\u0003\\\"9!\u0011\u001d\u0001\u0005\u0012\t\r\bb\u0002Bq\u0001\u0011E!1\u001e\u0005\b\u0005w\u0004A\u0011\u0001B\u007f\u0011\u001d\u0011Y\u0010\u0001C\u0001\u0007\u0017AqAa?\u0001\t\u0003\u0019\u0019\u0002C\u0005\u0004\u001a\u0001\t\n\u0011\"\u0001\u0004\u001c!91q\u0004\u0001\u0005\u0002\r\u0005\u0002\"CB\u0018\u0001E\u0005I\u0011AB\u000e\u0011\u001d\u0019y\u0002\u0001C\u0001\u0007c\u0011Q\u0002\u0015:fiRL\bK]5oi\u0016\u0014(BA0a\u0003\rAX\u000e\u001c\u0006\u0002C\u0006)1oY1mC\u000e\u00011C\u0001\u0001e!\t)g-D\u0001a\u0013\t9\u0007M\u0001\u0004B]f\u0014VMZ\u0001\u0006o&$G\u000f\u001b\t\u0003K*L!a\u001b1\u0003\u0007%sG/\u0001\u0003ti\u0016\u0004\u0018!D7j]&l\u0017N_3F[B$\u0018\u0010\u0005\u0002f_&\u0011\u0001\u000f\u0019\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q!1/\u001e<x!\t!\b!D\u0001_\u0011\u0015AG\u00011\u0001j\u0011\u0015aG\u00011\u0001j\u0011\u0015iG\u00011\u0001o)\r\u0019\u0018P\u001f\u0005\u0006Q\u0016\u0001\r!\u001b\u0005\u0006Y\u0016\u0001\r![\u0001\r[&t\u0017.\\5{K6{G-Z\u000b\u0002{B\u0019a0a\u0001\u000f\u0005Q|\u0018bAA\u0001=\u0006aQ*\u001b8j[&TX-T8eK&!\u0011QAA\u0004\u0005\u00151\u0016\r\\;f\u0013\r\tI\u0001\u0019\u0002\f\u000b:,X.\u001a:bi&|g.A\u0007nS:LW.\u001b>f\u001b>$W\r\t\u0002\u0010\u0005J|7.\u001a8Fq\u000e,\u0007\u000f^5p]N\u0019\u0001\"!\u0005\u0011\t\u0005M\u0011QD\u0007\u0003\u0003+QA!a\u0006\u0002\u001a\u0005!A.\u00198h\u0015\t\tY\"\u0001\u0003kCZ\f\u0017\u0002BA\u0010\u0003+\u0011\u0011\"\u0012=dKB$\u0018n\u001c8\u0015\u0005\u0005\r\u0002cAA\u0013\u00115\t\u0001A\u0001\u0003Ji\u0016l7C\u0001\u0006e)\t\ti\u0003E\u0002\u0002&)\tQA\u0011:fC.\u00042!!\n\u000e\u0005\u0015\u0011%/Z1l'\u001di\u0011QFA\u001c\u0003{\u00012!ZA\u001d\u0013\r\tY\u0004\u0019\u0002\b!J|G-^2u!\u0011\ty$a\u0014\u000f\t\u0005\u0005\u00131\n\b\u0005\u0003\u0007\nI%\u0004\u0002\u0002F)\u0019\u0011q\t2\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0017bAA'A\u00069\u0001/Y2lC\u001e,\u0017\u0002BA)\u0003'\u0012AbU3sS\u0006d\u0017N_1cY\u0016T1!!\u0014a)\t\t\t$\u0001\u0005u_N#(/\u001b8h)\t\tY\u0006\u0005\u0003\u0002^\u0005\u0015d\u0002BA0\u0003C\u00022!a\u0011a\u0013\r\t\u0019\u0007Y\u0001\u0007!J,G-\u001a4\n\t\u0005\u001d\u0014\u0011\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\r\u0004-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003_\u0002B!a\u0005\u0002r%!\u0011qMA\u000b\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005I\u0017A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003w\n\t\tE\u0002f\u0003{J1!a a\u0005\r\te.\u001f\u0005\t\u0003\u0007\u0013\u0012\u0011!a\u0001S\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!#\u0011\r\u0005-\u0015\u0011SA>\u001b\t\tiIC\u0002\u0002\u0010\u0002\f!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019*!$\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004]\u0006e\u0005\"CAB)\u0005\u0005\t\u0019AA>\u0003!A\u0017m\u001d5D_\u0012,G#A5\u0003\u0007\t{\u0007pE\u0004\u0017\u0003[\t9$!\u0010\u0002\u0007\r|G.\u0001\u0003d_2\u0004\u0013!A:\u0016\u0005\u0005m\u0013AA:!)\u0019\ty+!-\u00024B\u0019\u0011Q\u0005\f\t\r\u0005\r6\u00041\u0001j\u0011\u001d\t9k\u0007a\u0001\u00037\nAaY8qsR1\u0011qVA]\u0003wC\u0001\"a)\u001d!\u0003\u0005\r!\u001b\u0005\n\u0003Oc\u0002\u0013!a\u0001\u00037\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002B*\u001a\u0011.a1,\u0005\u0005\u0015\u0007\u0003BAd\u0003#l!!!3\u000b\t\u0005-\u0017QZ\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a4a\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003'\fIMA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002Z*\"\u00111LAb)\u0011\tY(!8\t\u0011\u0005\r\u0015%!AA\u0002%$2A\\Aq\u0011%\t\u0019iIA\u0001\u0002\u0004\tY(\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA8\u0003OD\u0001\"a!%\u0003\u0003\u0005\r!\u001b\u000b\u0003\u0003_\na!Z9vC2\u001cHc\u00018\u0002p\"I\u00111Q\u0014\u0002\u0002\u0003\u0007\u00111P\u0001\u0004\u0005>D\bcAA\u0013SM)\u0011&a>\u0003\u0004AI\u0011\u0011`A\u0000S\u0006m\u0013qV\u0007\u0003\u0003wT1!!@a\u0003\u001d\u0011XO\u001c;j[\u0016LAA!\u0001\u0002|\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\t\u0015!1B\u0007\u0003\u0005\u000fQAA!\u0003\u0002\u001a\u0005\u0011\u0011n\\\u0005\u0005\u0003#\u00129\u0001\u0006\u0002\u0002t\u0006)\u0011\r\u001d9msR1\u0011q\u0016B\n\u0005+Aa!a)-\u0001\u0004I\u0007bBATY\u0001\u0007\u00111L\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011YBa\n\u0011\u000b\u0015\u0014iB!\t\n\u0007\t}\u0001M\u0001\u0004PaRLwN\u001c\t\u0007K\n\r\u0012.a\u0017\n\u0007\t\u0015\u0002M\u0001\u0004UkBdWM\r\u0005\n\u0005Si\u0013\u0011!a\u0001\u0003_\u000b1\u0001\u001f\u00131\u0005\u0011\u0001\u0016M]1\u0014\u000f9\ni#a\u000e\u0002>Q!!\u0011\u0007B\u001a!\r\t)C\f\u0005\b\u0003O\u000b\u0004\u0019AA.)\u0011\u0011\tDa\u000e\t\u0013\u0005\u001d&\u0007%AA\u0002\u0005mC\u0003BA>\u0005wA\u0001\"a!7\u0003\u0003\u0005\r!\u001b\u000b\u0004]\n}\u0002\"CABq\u0005\u0005\t\u0019AA>)\u0011\tyGa\u0011\t\u0011\u0005\r\u0015(!AA\u0002%$2A\u001cB$\u0011%\t\u0019\tPA\u0001\u0002\u0004\tY(\u0001\u0003QCJ\f\u0007cAA\u0013}M)aHa\u0014\u0003\u0004AA\u0011\u0011 B)\u00037\u0012\t$\u0003\u0003\u0003T\u0005m(!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocQ\u0011!1\n\u000b\u0005\u0005c\u0011I\u0006C\u0004\u0002(\u0006\u0003\r!a\u0017\u0015\t\tu#q\f\t\u0006K\nu\u00111\f\u0005\n\u0005S\u0011\u0015\u0011!a\u0001\u0005c\tQ!\u001b;f[N,\"A!\u001a\u0011\r\t\u001d$\u0011NA\u0017\u001d\r)\u00171J\u0005\u0005\u0005W\n\u0019F\u0001\u0003MSN$\u0018!C5uK6\u001cx\fJ3r)\u0011\u0011\tHa\u001e\u0011\u0007\u0015\u0014\u0019(C\u0002\u0003v\u0001\u0014A!\u00168ji\"I\u00111\u0011#\u0002\u0002\u0003\u0007!QM\u0001\u0007SR,Wn\u001d\u0011\u0002\u0007\r,(/A\u0004dkJ|F%Z9\u0015\t\tE$\u0011\u0011\u0005\t\u0003\u0007;\u0015\u0011!a\u0001S\u0006!1-\u001e:!\u0003\u0015\u0011Xm]3u)\t\u0011\t(A\u0002dkR$bA!\u001a\u0003\u000e\n=\u0005bBAT\u0015\u0002\u0007\u00111\f\u0005\u0007\u0005#S\u0005\u0019A5\u0002\u0007%tG-A\u0004nC.,'i\u001c=\u0015\r\tE$q\u0013BM\u0011\u0019\u0011\tj\u0013a\u0001S\"9\u0011qU&A\u0002\u0005m\u0013\u0001C7bW\u0016\u0004\u0016M]1\u0015\r\tE$q\u0014BQ\u0011\u0019\u0011\t\n\u0014a\u0001S\"9\u0011q\u0015'A\u0002\u0005m\u0013!C7bW\u0016\u0014%/Z1l\u0003\u001daW-\u00194UC\u001e$B!a\u0017\u0003*\"9!1\u0016(A\u0002\t5\u0016!\u00018\u0011\u0007Q\u0014y+C\u0002\u00032z\u0013AAT8eK\u0006A1\u000f^1siR\u000bw\r\u0006\u0004\u00038\ne&1\u0018\t\u0007K\n\r\u00121L5\t\u000f\t-v\n1\u0001\u0003.\"9!QX(A\u0002\t}\u0016A\u00029tG>\u0004X\rE\u0002u\u0005\u0003L1Aa1_\u0005Aq\u0015-\\3ta\u0006\u001cWMQ5oI&tw-\u0001\u0004f]\u0012$\u0016m\u001a\u000b\u0005\u00037\u0012I\rC\u0004\u0003,B\u0003\rA!,\u0002#\rD\u0017\u000e\u001c3sK:\f%/\u001a'fCZ,7\u000fF\u0002o\u0005\u001fDqAa+R\u0001\u0004\u0011i+\u0001\u0003gSR\u001cHc\u00018\u0003V\"9!q\u001b*A\u0002\u0005m\u0013\u0001\u0002;fgR\f!\u0002Z8Qe\u0016\u001cXM\u001d<f)\rq'Q\u001c\u0005\b\u0005?\u001c\u0006\u0019\u0001BW\u0003\u0011qw\u000eZ3\u0002\u0011Q\u0014\u0018M^3sg\u0016$\u0002B!\u001d\u0003f\n\u001d(\u0011\u001e\u0005\b\u0005?$\u0006\u0019\u0001BW\u0011\u001d\u0011i\f\u0016a\u0001\u0005\u007fCaA!%U\u0001\u0004IG\u0003\u0003B9\u0005[\u0014)P!?\t\u000f\t=X\u000b1\u0001\u0003r\u0006\u0011\u0011\u000e\u001e\t\u0007\u0005O\u0012\u0019P!,\n\t\u0005M\u00151\u000b\u0005\b\u0005o,\u0006\u0019\u0001B`\u0003\u0015\u00198m\u001c9f\u0011\u0019\u0011\t*\u0016a\u0001S\u00061am\u001c:nCR$bA!\u001d\u0003\u0000\u000e\u0005\u0001b\u0002BV-\u0002\u0007!Q\u0016\u0005\b\u0007\u00071\u0006\u0019AB\u0003\u0003\t\u0019(\r\u0005\u0003\u0003h\r\u001d\u0011\u0002BB\u0005\u0003'\u0012Qb\u0015;sS:<')^5mI\u0016\u0014H\u0003\u0003B9\u0007\u001b\u0019ya!\u0005\t\u000f\t-v\u000b1\u0001\u0003.\"9!QX,A\u0002\t}\u0006bBB\u0002/\u0002\u00071Q\u0001\u000b\u0007\u00037\u001a)ba\u0006\t\u000f\t-\u0006\f1\u0001\u0003.\"I!Q\u0018-\u0011\u0002\u0003\u0007!qX\u0001\u0011M>\u0014X.\u0019;%I\u00164\u0017-\u001e7uII*\"a!\b+\t\t}\u00161Y\u0001\fM>\u0014X.\u0019;O_\u0012,7\u000f\u0006\u0004\u0002\\\r\r2Q\u0006\u0005\b\u0007KQ\u0006\u0019AB\u0014\u0003\u0015qw\u000eZ3t!\u0019\tYi!\u000b\u0003.&!11FAG\u0005\r\u0019V-\u001d\u0005\n\u0005{S\u0006\u0013!a\u0001\u0005\u007f\u000bQCZ8s[\u0006$hj\u001c3fg\u0012\"WMZ1vYR$#\u0007\u0006\u0005\u0003r\rM2QGB\u001c\u0011\u001d\u0019)\u0003\u0018a\u0001\u0007OAqA!0]\u0001\u0004\u0011y\fC\u0004\u0004\u0004q\u0003\ra!\u0002"
)
public class PrettyPrinter {
   private volatile Break$ Break$module;
   private volatile Box$ Box$module;
   private volatile Para$ Para$module;
   private final int width;
   private final int step;
   private final boolean minimizeEmpty;
   private final Enumeration.Value minimizeMode;
   private List items;
   private int cur;

   public Break$ Break() {
      if (this.Break$module == null) {
         this.Break$lzycompute$1();
      }

      return this.Break$module;
   }

   public Box$ Box() {
      if (this.Box$module == null) {
         this.Box$lzycompute$1();
      }

      return this.Box$module;
   }

   public Para$ Para() {
      if (this.Para$module == null) {
         this.Para$lzycompute$1();
      }

      return this.Para$module;
   }

   public Enumeration.Value minimizeMode() {
      return this.minimizeMode;
   }

   public List items() {
      return this.items;
   }

   public void items_$eq(final List x$1) {
      this.items = x$1;
   }

   public int cur() {
      return this.cur;
   }

   public void cur_$eq(final int x$1) {
      this.cur = x$1;
   }

   public void reset() {
      this.cur_$eq(0);
      this.items_$eq(.MODULE$);
   }

   public List cut(final String s, final int ind) {
      int tmp = this.width - this.cur();
      if (s.length() <= tmp) {
         return new scala.collection.immutable..colon.colon(new Box(ind, s), .MODULE$);
      } else {
         int i = s.indexOf(32);
         if (i <= tmp && i != -1) {
            List last;
            for(last = .MODULE$; i != -1 && i < tmp; i = s.indexOf(32, i + 1)) {
               last = last.$colon$colon(BoxesRunTime.boxToInteger(i));
            }

            List res = .MODULE$;

            while(!.MODULE$.equals(last)) {
               try {
                  Box b = new Box(ind, s.substring(0, BoxesRunTime.unboxToInt(last.head())));
                  this.cur_$eq(ind);
                  Break$ var10 = this.Break();
                  List var12 = this.cut(s.substring(BoxesRunTime.unboxToInt(last.head()), s.length()), ind).$colon$colon(var10).$colon$colon(b);
                  last = (List)last.tail();
               } catch (BrokenException var11) {
                  last = (List)last.tail();
               }
            }

            throw new BrokenException();
         } else {
            throw new BrokenException();
         }
      }
   }

   public void makeBox(final int ind, final String s) {
      if (this.cur() + s.length() > this.width) {
         this.items_$eq(this.items().$colon$colon(new Box(ind, s)));
         this.cur_$eq(this.cur() + s.length());
      } else {
         try {
            this.cut(s, ind).foreach((x$1) -> {
               $anonfun$makeBox$1(this, x$1);
               return BoxedUnit.UNIT;
            });
         } catch (BrokenException var3) {
            this.makePara(ind, s);
         }

      }
   }

   public void makePara(final int ind, final String s) {
      Break$ var3 = this.Break();
      Para var4 = new Para(s);
      Break$ var5 = this.Break();
      this.items_$eq(this.items().$colon$colon(var5).$colon$colon(var4).$colon$colon(var3));
      this.cur_$eq(ind);
   }

   public void makeBreak() {
      Break$ var1 = this.Break();
      this.items_$eq(this.items().$colon$colon(var1));
      this.cur_$eq(0);
   }

   public String leafTag(final Node n) {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$leafTag$1(n, sb);
         return BoxedUnit.UNIT;
      });
   }

   public Tuple2 startTag(final Node n, final NamespaceBinding pscope) {
      IntRef i = IntRef.create(0);
      return new Tuple2(Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$startTag$1(n, i, pscope, sb);
         return BoxedUnit.UNIT;
      }), BoxesRunTime.boxToInteger(i.elem));
   }

   public String endTag(final Node n) {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$endTag$1(n, sb);
         return BoxedUnit.UNIT;
      });
   }

   public boolean childrenAreLeaves(final Node n) {
      return n.child().forall((l) -> BoxesRunTime.boxToBoolean($anonfun$childrenAreLeaves$1(l)));
   }

   public boolean fits(final String test) {
      return test.length() < this.width - this.cur();
   }

   private boolean doPreserve(final Node node) {
      return node.attribute(XML$.MODULE$.namespace(), XML$.MODULE$.space()).exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$doPreserve$1(x$2)));
   }

   public void traverse(final Node node, final NamespaceBinding pscope, final int ind) {
      if (node != null) {
         Option var8 = Text$.MODULE$.unapply(node);
         if (!var8.isEmpty()) {
            String s = (String)var8.get();
            if (s.trim().isEmpty()) {
               BoxedUnit var41 = BoxedUnit.UNIT;
               return;
            }
         }
      }

      if (node instanceof Atom ? true : (node instanceof Comment ? true : (node instanceof EntityRef ? true : node instanceof ProcInstr))) {
         this.makeBox(ind, node.toString().trim());
         BoxedUnit var40 = BoxedUnit.UNIT;
      } else if (node instanceof Group) {
         Group var10 = (Group)node;
         Seq xs = var10.nodes();
         this.traverse(xs.iterator(), pscope, ind);
         BoxedUnit var39 = BoxedUnit.UNIT;
      } else {
         StringBuilder sb = new StringBuilder();
         boolean x$4 = false;
         Enumeration.Value x$5 = this.minimizeMode();
         boolean x$6 = Utility$.MODULE$.serialize$default$5();
         boolean x$7 = Utility$.MODULE$.serialize$default$6();
         Utility$.MODULE$.serialize(node, pscope, sb, false, x$6, x$7, x$5);
         String test = this.doPreserve(node) ? sb.toString() : (String)((Atom)TextBuffer$.MODULE$.fromString(sb.toString()).toText().apply(0)).data();
         if (this.childrenAreLeaves(node) && this.fits(test)) {
            this.makeBox(ind, test);
            BoxedUnit var38 = BoxedUnit.UNIT;
         } else {
            Tuple2 var10000;
            if (node.child().isEmpty() && this.minimizeEmpty) {
               int firstAttribute = test.indexOf(32);
               int firstBreak = firstAttribute != -1 ? firstAttribute : test.lastIndexOf(47);
               var10000 = new Tuple2(new Tuple2(test, BoxesRunTime.boxToInteger(firstBreak)), "");
            } else {
               var10000 = new Tuple2(this.startTag(node, pscope), this.endTag(node));
            }

            Tuple2 var22 = var10000;
            if (var22 != null) {
               Tuple2 var25 = (Tuple2)var22._1();
               String etg = (String)var22._2();
               if (var25 != null) {
                  String stg = (String)var25._1();
                  int len2 = var25._2$mcI$sp();
                  if (stg != null && true && etg != null) {
                     Tuple3 var21 = new Tuple3(stg, BoxesRunTime.boxToInteger(len2), etg);
                     String stg = (String)var21._1();
                     int len2 = BoxesRunTime.unboxToInt(var21._2());
                     String etg = (String)var21._3();
                     if (stg.length() < this.width - this.cur()) {
                        this.makeBox(ind, stg);
                        this.makeBreak();
                        this.traverse(node.child().iterator(), node.scope(), ind + this.step);
                        this.makeBox(ind, etg);
                        BoxedUnit var37 = BoxedUnit.UNIT;
                        return;
                     }

                     if (len2 < this.width - this.cur()) {
                        this.makeBox(ind, stg.substring(0, len2));
                        this.makeBreak();
                        this.makeBox(ind, stg.substring(len2, stg.length()).trim());
                        if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(etg))) {
                           this.makeBreak();
                           this.traverse(node.child().iterator(), node.scope(), ind + this.step);
                           this.makeBox(this.cur(), etg);
                        }

                        this.makeBreak();
                        BoxedUnit var36 = BoxedUnit.UNIT;
                        return;
                     }

                     this.makeBox(ind, test);
                     this.makeBreak();
                     BoxedUnit var35 = BoxedUnit.UNIT;
                     return;
                  }
               }
            }

            throw new MatchError(var22);
         }
      }
   }

   public void traverse(final Iterator it, final NamespaceBinding scope, final int ind) {
      it.foreach((c) -> {
         $anonfun$traverse$1(this, scope, ind, c);
         return BoxedUnit.UNIT;
      });
   }

   public void format(final Node n, final StringBuilder sb) {
      this.format(n, TopScope$.MODULE$, sb);
   }

   public void format(final Node n, final NamespaceBinding pscope, final StringBuilder sb) {
      BooleanRef lastwasbreak = BooleanRef.create(false);
      this.reset();
      this.traverse((Node)n, pscope, 0);
      IntRef cur = IntRef.create(0);
      this.items().reverse().foreach((b) -> {
         if (this.Break().equals(b)) {
            if (!lastwasbreak.elem) {
               sb.append('\n');
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            lastwasbreak.elem = true;
            cur.elem = 0;
            return BoxedUnit.UNIT;
         } else if (!(b instanceof Box)) {
            if (b instanceof Para) {
               Para var10 = (Para)b;
               String s = var10.s();
               lastwasbreak.elem = false;
               return sb.append(s);
            } else {
               throw new MatchError(b);
            }
         } else {
            Box var7 = (Box)b;
            int i = var7.col();
            String s = var7.s();

            for(lastwasbreak.elem = false; cur.elem < i; ++cur.elem) {
               sb.append(' ');
            }

            return sb.append(s);
         }
      });
   }

   public String format(final Node n, final NamespaceBinding pscope) {
      return Utility$.MODULE$.sbToString((x$4) -> {
         $anonfun$format$2(this, n, pscope, x$4);
         return BoxedUnit.UNIT;
      });
   }

   public NamespaceBinding format$default$2() {
      return TopScope$.MODULE$;
   }

   public String formatNodes(final Seq nodes, final NamespaceBinding pscope) {
      return Utility$.MODULE$.sbToString((x$5) -> {
         $anonfun$formatNodes$1(this, nodes, pscope, x$5);
         return BoxedUnit.UNIT;
      });
   }

   public void formatNodes(final Seq nodes, final NamespaceBinding pscope, final StringBuilder sb) {
      nodes.foreach((n) -> sb.append(this.format(n, pscope)));
   }

   public NamespaceBinding formatNodes$default$2() {
      return TopScope$.MODULE$;
   }

   private final void Break$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Break$module == null) {
            this.Break$module = new Break$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Box$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Box$module == null) {
            this.Box$module = new Box$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void Para$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Para$module == null) {
            this.Para$module = new Para$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$makeBox$1(final PrettyPrinter $this, final Item x$1) {
      $this.items_$eq($this.items().$colon$colon(x$1));
   }

   private static final void mkLeaf$1(final StringBuilder sb, final Node n$1) {
      sb.append('<');
      n$1.nameToString(sb);
      n$1.attributes().buildString(sb);
      sb.append("/>");
   }

   // $FF: synthetic method
   public static final void $anonfun$leafTag$1(final Node n$1, final StringBuilder sb) {
      mkLeaf$1(sb, n$1);
   }

   private static final void mkStart$1(final StringBuilder sb, final Node n$2, final IntRef i$1, final NamespaceBinding pscope$1) {
      sb.append('<');
      n$2.nameToString(sb);
      i$1.elem = sb.length() + 1;
      n$2.attributes().buildString(sb);
      n$2.scope().buildString(sb, pscope$1);
      sb.append('>');
   }

   // $FF: synthetic method
   public static final void $anonfun$startTag$1(final Node n$2, final IntRef i$1, final NamespaceBinding pscope$1, final StringBuilder sb) {
      mkStart$1(sb, n$2, i$1, pscope$1);
   }

   private static final void mkEnd$1(final StringBuilder sb, final Node n$3) {
      sb.append("</");
      n$3.nameToString(sb);
      sb.append('>');
   }

   // $FF: synthetic method
   public static final void $anonfun$endTag$1(final Node n$3, final StringBuilder sb) {
      mkEnd$1(sb, n$3);
   }

   private static final boolean isLeaf$1(final Node l) {
      return l instanceof Atom ? true : (l instanceof Comment ? true : (l instanceof EntityRef ? true : l instanceof ProcInstr));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$childrenAreLeaves$1(final Node l) {
      return isLeaf$1(l);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doPreserve$1(final Seq x$2) {
      boolean var2;
      label23: {
         String var10000 = x$2.toString();
         String var1 = XML$.MODULE$.preserve();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final void $anonfun$traverse$1(final PrettyPrinter $this, final NamespaceBinding scope$1, final int ind$1, final Node c) {
      $this.traverse(c, scope$1, ind$1);
      $this.makeBreak();
   }

   // $FF: synthetic method
   public static final void $anonfun$format$2(final PrettyPrinter $this, final Node n$4, final NamespaceBinding pscope$2, final StringBuilder x$4) {
      $this.format(n$4, pscope$2, x$4);
   }

   // $FF: synthetic method
   public static final void $anonfun$formatNodes$1(final PrettyPrinter $this, final Seq nodes$1, final NamespaceBinding pscope$3, final StringBuilder x$5) {
      $this.formatNodes(nodes$1, pscope$3, x$5);
   }

   public PrettyPrinter(final int width, final int step, final boolean minimizeEmpty) {
      this.width = width;
      this.step = step;
      this.minimizeEmpty = minimizeEmpty;
      this.minimizeMode = minimizeEmpty ? MinimizeMode$.MODULE$.Always() : MinimizeMode$.MODULE$.Default();
      this.items = .MODULE$;
      this.cur = 0;
   }

   public PrettyPrinter(final int width, final int step) {
      this(width, step, false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class BrokenException extends Exception {
      // $FF: synthetic field
      public final PrettyPrinter $outer;

      // $FF: synthetic method
      public PrettyPrinter scala$xml$PrettyPrinter$BrokenException$$$outer() {
         return this.$outer;
      }

      public BrokenException() {
         if (PrettyPrinter.this == null) {
            throw null;
         } else {
            this.$outer = PrettyPrinter.this;
            super();
         }
      }
   }

   public class Item {
      // $FF: synthetic field
      public final PrettyPrinter $outer;

      // $FF: synthetic method
      public PrettyPrinter scala$xml$PrettyPrinter$Item$$$outer() {
         return this.$outer;
      }

      public Item() {
         if (PrettyPrinter.this == null) {
            throw null;
         } else {
            this.$outer = PrettyPrinter.this;
            super();
         }
      }
   }

   public class Break$ extends Item implements Product, Serializable {
      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String toString() {
         return "\\";
      }

      public String productPrefix() {
         return "Break";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Break$;
      }

      public int hashCode() {
         return 64448735;
      }

      public Break$() {
         Product.$init$(this);
      }
   }

   public class Box extends Item implements Product, Serializable {
      private final int col;
      private final String s;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int col() {
         return this.col;
      }

      public String s() {
         return this.s;
      }

      public Box copy(final int col, final String s) {
         return this.scala$xml$PrettyPrinter$Box$$$outer().new Box(col, s);
      }

      public int copy$default$1() {
         return this.col();
      }

      public String copy$default$2() {
         return this.s();
      }

      public String productPrefix() {
         return "Box";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return BoxesRunTime.boxToInteger(this.col());
            case 1:
               return this.s();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Box;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "col";
            case 1:
               return "s";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.col());
         var1 = Statics.mix(var1, Statics.anyHash(this.s()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label56: {
               if (x$1 instanceof Box && ((Box)x$1).scala$xml$PrettyPrinter$Box$$$outer() == this.scala$xml$PrettyPrinter$Box$$$outer()) {
                  Box var4 = (Box)x$1;
                  if (this.col() == var4.col()) {
                     label46: {
                        String var10000 = this.s();
                        String var5 = var4.s();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label46;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label46;
                        }

                        if (var4.canEqual(this)) {
                           break label56;
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
      public PrettyPrinter scala$xml$PrettyPrinter$Box$$$outer() {
         return this.$outer;
      }

      public Box(final int col, final String s) {
         this.col = col;
         this.s = s;
         Product.$init$(this);
      }
   }

   public class Box$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final PrettyPrinter $outer;

      public final String toString() {
         return "Box";
      }

      public Box apply(final int col, final String s) {
         return this.$outer.new Box(col, s);
      }

      public Option unapply(final Box x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.col()), x$0.s())));
      }

      public Box$() {
         if (PrettyPrinter.this == null) {
            throw null;
         } else {
            this.$outer = PrettyPrinter.this;
            super();
         }
      }
   }

   public class Para extends Item implements Product, Serializable {
      private final String s;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String s() {
         return this.s;
      }

      public Para copy(final String s) {
         return this.scala$xml$PrettyPrinter$Para$$$outer().new Para(s);
      }

      public String copy$default$1() {
         return this.s();
      }

      public String productPrefix() {
         return "Para";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.s();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Para;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "s";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof Para && ((Para)x$1).scala$xml$PrettyPrinter$Para$$$outer() == this.scala$xml$PrettyPrinter$Para$$$outer()) {
                  label42: {
                     Para var4 = (Para)x$1;
                     String var10000 = this.s();
                     String var5 = var4.s();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label42;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label42;
                     }

                     if (var4.canEqual(this)) {
                        break label52;
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
      public PrettyPrinter scala$xml$PrettyPrinter$Para$$$outer() {
         return this.$outer;
      }

      public Para(final String s) {
         this.s = s;
         Product.$init$(this);
      }
   }

   public class Para$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final PrettyPrinter $outer;

      public final String toString() {
         return "Para";
      }

      public Para apply(final String s) {
         return this.$outer.new Para(s);
      }

      public Option unapply(final Para x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.s()));
      }

      public Para$() {
         if (PrettyPrinter.this == null) {
            throw null;
         } else {
            this.$outer = PrettyPrinter.this;
            super();
         }
      }
   }
}
