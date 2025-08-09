package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.Counter2Ops;
import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.Field;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Set;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\rmeaB\u0010!!\u0003\r\n!J\u0004\u0006\u0019\u0002B\t!\u0014\u0004\u0006?\u0001B\tA\u0014\u0005\u00061\n!\t!\u0017\u0004\u00055\n\u00011\f\u0003\u0005q\t\t\u0015\r\u0011\"\u0011r\u0011!YHA!A!\u0002\u0013\u0011\b\u0002\u0003?\u0005\u0005\u0003\u0005\u000b1B?\t\ra#A\u0011AA\u0004\u0011\u001d\t\u0019\u0002\u0002C\u0001\u0003+Aq!a\u0006\u0005\t\u0003\tI\u0002C\u0004\u00022\t!\t!a\r\u0007\r\u00055#\u0001BA(\u0011)\t9\u0007\u0004B\u0002B\u0003-\u0011\u0011\u000e\u0005\u000712!\t!a\u001b\t\u000f\u0005MA\u0002\"\u0011\u0002t!9\u0011\u0011\u0007\u0002\u0005\u0002\u0005m\u0004bBA\u0019\u0005\u0011\u0005\u0011Q\u0017\u0005\b\u0003?\u0014A\u0011AAq\u0011\u001d\tYP\u0001C\u0002\u0003{DqAa\f\u0003\t\u0007\u0011\t\u0004C\u0004\u0003J\t!\u0019Aa\u0013\t\u000f\t\u0015$\u0001b\u0001\u0003h!9!q\u0011\u0002\u0005\u0004\t%\u0005b\u0002BP\u0005\u0011\r!\u0011\u0015\u0005\b\u0005?\u0014A1\u0001Bq\u0011\u001d\u0019\u0019A\u0001C\u0002\u0007\u000bAqa!\u0012\u0003\t\u0007\u00199EB\u0005\u0004^\t\u0001\n1%\u0001\u0004`\u0015111\r\u000f\u0001\u0007KBqa!!\u0003\t\u0007\u0019\u0019I\u0001\u0005D_VtG/\u001a:3\u0015\t\t#%\u0001\u0004mS:\fGn\u001a\u0006\u0002G\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0003'm\u0001\u001b5\u0003\u0002\u0001([\u0015\u0003\"\u0001K\u0016\u000e\u0003%R\u0011AK\u0001\u0006g\u000e\fG.Y\u0005\u0003Y%\u0012a!\u00118z%\u00164\u0007\u0003\u0002\u00180c\tk\u0011\u0001I\u0005\u0003a\u0001\u0012a\u0001V3og>\u0014\b\u0003\u0002\u00153i}J!aM\u0015\u0003\rQ+\b\u000f\\33!\t)d\u0007\u0004\u0001\u0005\u000b]\u0002!\u0019\u0001\u001d\u0003\u0005-\u000b\u0014CA\u001d=!\tA#(\u0003\u0002<S\t9aj\u001c;iS:<\u0007C\u0001\u0015>\u0013\tq\u0014FA\u0002B]f\u0004\"!\u000e!\u0005\u000b\u0005\u0003!\u0019\u0001\u001d\u0003\u0005-\u0013\u0004CA\u001bD\t\u0015!\u0005A1\u00019\u0005\u00051\u0006c\u0002\u0018Gi}\u0012\u0005jS\u0005\u0003\u000f\u0002\u0012AbQ8v]R,'O\r'jW\u0016\u0004BAL%@\u0005&\u0011!\n\t\u0002\b\u0007>,h\u000e^3s!\u0015q\u0003\u0001N C\u0003!\u0019u.\u001e8uKJ\u0014\u0004C\u0001\u0018\u0003'\u0011\u0011qe\u0014*\u0011\u00059\u0002\u0016BA)!\u0005Maun\u001e)sS>\u0014\u0018\u000e^=D_VtG/\u001a:3!\t\u0019f+D\u0001U\u0015\t)\u0006%A\u0005pa\u0016\u0014\u0018\r^8sg&\u0011q\u000b\u0016\u0002\f\u0007>,h\u000e^3se=\u00038/\u0001\u0004=S:LGO\u0010\u000b\u0002\u001b\n!\u0011*\u001c9m+\u0011av,Y2\u0014\t\u00119S\f\u001a\t\u0006]\u0001q\u0006M\u0019\t\u0003k}#Qa\u000e\u0003C\u0002a\u0002\"!N1\u0005\u000b\u0005#!\u0019\u0001\u001d\u0011\u0005U\u001aG!\u0002#\u0005\u0005\u0004A\u0004CA3n\u001d\t17N\u0004\u0002hU6\t\u0001N\u0003\u0002jI\u00051AH]8pizJ\u0011AK\u0005\u0003Y&\nq\u0001]1dW\u0006<W-\u0003\u0002o_\na1+\u001a:jC2L'0\u00192mK*\u0011A.K\u0001\u0005I\u0006$\u0018-F\u0001s!\u0011\u0019\bP\u0018>\u000e\u0003QT!!\u001e<\u0002\u000f5,H/\u00192mK*\u0011q/K\u0001\u000bG>dG.Z2uS>t\u0017BA=u\u0005\ri\u0015\r\u001d\t\u0005]%\u0003'-A\u0003eCR\f\u0007%\u0001\u0004tG\u0006d\u0017M\u001d\t\u0005}\u0006\r!-D\u0001\u0000\u0015\r\t\tAI\u0001\bgR|'/Y4f\u0013\r\t)a \u0002\u00055\u0016\u0014x\u000e\u0006\u0003\u0002\n\u0005EA\u0003BA\u0006\u0003\u001f\u0001b!!\u0004\u0005=\u0002\u0014W\"\u0001\u0002\t\u000bqD\u00019A?\t\u000bAD\u0001\u0019\u0001:\u0002\u000f\u0011,g-Y;miV\t!-\u0001\u0004lKf\u001cV\r^\u000b\u0003\u00037\u0001b!!\b\u0002 \u0005\rR\"\u0001<\n\u0007\u0005\u0005bOA\u0002TKR\u0004B\u0001\u000b\u001a_A\":A!a\n\u0002.\u0005=\u0002c\u0001\u0015\u0002*%\u0019\u00111F\u0015\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0011\u0005U\u0012QHA!\u0003\u000b\"\"!a\u000e\u0015\t\u0005e\u0012q\t\t\t]\u0001\tY$a\u0010\u0002DA\u0019Q'!\u0010\u0005\u000b]Z!\u0019\u0001\u001d\u0011\u0007U\n\t\u0005B\u0003B\u0017\t\u0007\u0001\bE\u00026\u0003\u000b\"Q\u0001R\u0006C\u0002aB\u0011\"!\u0013\f\u0003\u0003\u0005\u001d!a\u0013\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0003\u007f\u0003\u0007\t\u0019E\u0001\bD_VtG/\u001a:ICNDW*\u00199\u0016\u0011\u0005E\u00131LA1\u0003K\u001aB\u0001DA*IB91/!\u0016\u0002Z\u0005u\u0013bAA,i\n9\u0001*Y:i\u001b\u0006\u0004\bcA\u001b\u0002\\\u0011)q\u0007\u0004b\u0001qA1a&SA0\u0003G\u00022!NA1\t\u0015\tEB1\u00019!\r)\u0014Q\r\u0003\u0006\t2\u0011\r\u0001O\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004#\u0002@\u0002\u0004\u0005\rDCAA7)\u0011\ty'!\u001d\u0011\u0013\u00055A\"!\u0017\u0002`\u0005\r\u0004bBA4\u001d\u0001\u000f\u0011\u0011\u000e\u000b\u0005\u0003;\n)\bC\u0004\u0002x=\u0001\r!!\u0017\u0002\u0003-Ds\u0001DA\u0014\u0003[\ty#\u0006\u0005\u0002~\u0005\u0015\u0015\u0011RAG)\u0011\ty(!*\u0015\r\u0005\u0005\u0015qRAP!!q\u0003!a!\u0002\b\u0006-\u0005cA\u001b\u0002\u0006\u0012)q\u0007\u0005b\u0001qA\u0019Q'!#\u0005\u000b\u0005\u0003\"\u0019\u0001\u001d\u0011\u0007U\ni\tB\u0003E!\t\u0007\u0001\bC\u0005\u0002\u0012B\t\t\u0011q\u0001\u0002\u0014\u0006QQM^5eK:\u001cW\rJ\u001a\u0011\r\u0005U\u00151TAF\u001b\t\t9JC\u0002\u0002\u001a\n\nA!\\1uQ&!\u0011QTAL\u0005!\u0019V-\\5sS:<\u0007\"CAQ!\u0005\u0005\t9AAR\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0006}\u0006\r\u00111\u0012\u0005\b\u0003O\u0003\u0002\u0019AAU\u0003\u00191\u0018\r\\;fgB)\u0001&a+\u00020&\u0019\u0011QV\u0015\u0003\u0015q\u0012X\r]3bi\u0016$g\bE\u0005)\u0003c\u000b\u0019)a\"\u0002\f&\u0019\u00111W\u0015\u0003\rQ+\b\u000f\\34+!\t9,a0\u0002D\u0006\u001dG\u0003BA]\u0003+$b!a/\u0002J\u0006=\u0007\u0003\u0003\u0018\u0001\u0003{\u000b\t-!2\u0011\u0007U\ny\fB\u00038#\t\u0007\u0001\bE\u00026\u0003\u0007$Q!Q\tC\u0002a\u00022!NAd\t\u0015!\u0015C1\u00019\u0011%\tY-EA\u0001\u0002\b\ti-\u0001\u0006fm&$WM\\2fIU\u0002b!!&\u0002\u001c\u0006\u0015\u0007\"CAi#\u0005\u0005\t9AAj\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0006}\u0006\r\u0011Q\u0019\u0005\b\u0003O\u000b\u0002\u0019AAl!\u0015)\u0017\u0011\\Ao\u0013\r\tYn\u001c\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dKBI\u0001&!-\u0002>\u0006\u0005\u0017QY\u0001\u0006G>,h\u000e^\u000b\u0007\u0003G\fI/!<\u0015\t\u0005\u0015\u0018Q\u001f\t\t]\u0001\t9/a;\u0002pB\u0019Q'!;\u0005\u000b]\u0012\"\u0019\u0001\u001d\u0011\u0007U\ni\u000fB\u0003B%\t\u0007\u0001\bE\u0002)\u0003cL1!a=*\u0005\rIe\u000e\u001e\u0005\b\u0003O\u0013\u0002\u0019AA|!\u0015)\u0017\u0011\\A}!\u0019A#'a:\u0002l\u0006\u00192)\u00198NCB4\u0016\r\\;fg\u000e{WO\u001c;feVQ\u0011q B\t\u0005+\u0011IB!\b\u0015\r\t\u0005!1\u0005B\u0015!1\u0011\u0019A!\u0003\u0003\u000e\t]!1\u0004B\u0011\u001b\t\u0011)AC\u0002\u0003\b\u0001\nqa];qa>\u0014H/\u0003\u0003\u0003\f\t\u0015!\u0001D\"b]6\u000b\u0007OV1mk\u0016\u001c\b\u0003\u0003\u0018\u0001\u0005\u001f\u0011\u0019Ba\u0006\u0011\u0007U\u0012\t\u0002B\u00038'\t\u0007\u0001\bE\u00026\u0005+!Q!Q\nC\u0002a\u00022!\u000eB\r\t\u0015!5C1\u00019!\r)$Q\u0004\u0003\u0007\u0005?\u0019\"\u0019\u0001\u001d\u0003\u0005I3\u0006\u0003\u0003\u0018\u0001\u0005\u001f\u0011\u0019Ba\u0007\t\u0013\t\u00152#!AA\u0004\t\u001d\u0012AC3wS\u0012,gnY3%oA1\u0011QSAN\u00057A\u0011Ba\u000b\u0014\u0003\u0003\u0005\u001dA!\f\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\bE\u0003\u007f\u0003\u0007\u0011Y\"\u0001\tdC:LE/\u001a:bi\u00164\u0016\r\\;fgVA!1\u0007B \u0005\u0007\u00129%\u0006\u0002\u00036AA!1\u0001B\u001c\u0005w\u0011)%\u0003\u0003\u0003:\t\u0015!!E\"b]R\u0013\u0018M^3sg\u00164\u0016\r\\;fgBAa\u0006\u0001B\u001f\u0005\u0003\u0012)\u0005E\u00026\u0005\u007f!Qa\u000e\u000bC\u0002a\u00022!\u000eB\"\t\u0015\tEC1\u00019!\r)$q\t\u0003\u0006\tR\u0011\r\u0001O\u0001\u0019G\u0006tGK]1wKJ\u001cXmS3z-\u0006dW/\u001a)bSJ\u001cX\u0003\u0003B'\u00053\u0012iF!\u0019\u0016\u0005\t=\u0003C\u0003B\u0002\u0005#\u0012)Fa\u0019\u0003`%!!1\u000bB\u0003\u0005a\u0019\u0015M\u001c+sCZ,'o]3LKf4\u0016\r\\;f!\u0006L'o\u001d\t\t]\u0001\u00119Fa\u0017\u0003`A\u0019QG!\u0017\u0005\u000b]*\"\u0019\u0001\u001d\u0011\u0007U\u0012i\u0006B\u0003B+\t\u0007\u0001\bE\u00026\u0005C\"Q\u0001R\u000bC\u0002a\u0002b\u0001\u000b\u001a\u0003X\tm\u0013aC2b]Nc\u0017nY3S_^,\u0002B!\u001b\u0003v\te$QP\u000b\u0003\u0005W\u0002BBa\u0001\u0003n\tE$1\u000fB@\u0005\u000bKAAa\u001c\u0003\u0006\tI1)\u00198TY&\u001cWM\r\t\t]\u0001\u0011\u0019Ha\u001e\u0003|A\u0019QG!\u001e\u0005\u000b]2\"\u0019\u0001\u001d\u0011\u0007U\u0012I\bB\u0003B-\t\u0007\u0001\bE\u00026\u0005{\"Q\u0001\u0012\fC\u0002ar1!\u001aBA\u0013\r\u0011\u0019i\\\u0001\rI\r|Gn\u001c8%G>dwN\u001c\t\u0007]%\u00139Ha\u001f\u0002\u0017\r\fgn\u00157jG\u0016\u001cu\u000e\\\u000b\t\u0005\u0017\u0013\u0019Ja&\u0003\u001cV\u0011!Q\u0012\t\r\u0005\u0007\u0011iGa$\u0003\u0000\tU%Q\u0014\t\t]\u0001\u0011\tJ!&\u0003\u001aB\u0019QGa%\u0005\u000b]:\"\u0019\u0001\u001d\u0011\u0007U\u00129\nB\u0003B/\t\u0007\u0001\bE\u00026\u00057#Q\u0001R\fC\u0002a\u0002bAL%\u0003\u0012\ne\u0015AC2b]6\u000b\u0007OU8xgVQ!1\u0015BX\u0005g\u00139L!4\u0015\r\t\u0015&1\u001bBm!9\u0011\u0019Aa*\u0003,\ne&q\u0019Be\u0005#LAA!+\u0003\u0006\ty1)\u00198D_2d\u0017\r]:f\u0003bL7\u000f\u0005\u0005/\u0001\t5&\u0011\u0017B[!\r)$q\u0016\u0003\u0006oa\u0011\r\u0001\u000f\t\u0004k\tMF!B!\u0019\u0005\u0004A\u0004cA\u001b\u00038\u0012)A\t\u0007b\u0001q9!!1\u0018Ba\u001d\rq#QX\u0005\u0004\u0005\u007f\u0003\u0013\u0001B!ySNLAAa1\u0003F\u0006\u0011q\f\r\u0006\u0004\u0005\u007f\u0003\u0003C\u0002\u0018J\u0005[\u0013)\f\u0005\u0004/\u0013\n5&1\u001a\t\u0004k\t5GA\u0002Bh1\t\u0007\u0001HA\u0001S!!q\u0003A!,\u00032\n-\u0007\"\u0003Bk1\u0005\u0005\t9\u0001Bl\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0006}\u0006\r!1\u001a\u0005\n\u00057D\u0012\u0011!a\u0002\u0005;\f1\"\u001a<jI\u0016t7-\u001a\u00132aA1\u0011QSAN\u0005\u0017\f!\u0003[1oI\"|G\u000eZ\"b]6\u000b\u0007OU8xgVA!1\u001dB|\u0005w\u0014y0\u0006\u0002\u0003fBQ!q\u001dBw\u0005g\u0014Il!\u0001\u000f\t\t\r!\u0011^\u0005\u0005\u0005W\u0014)!A\bDC:\u001cu\u000e\u001c7baN,\u0017\t_5t\u0013\u0011\u0011yO!=\u0003\u0011!\u000bg\u000e\u001a%pY\u0012TAAa;\u0003\u0006AAa\u0006\u0001B{\u0005s\u0014i\u0010E\u00026\u0005o$QaN\rC\u0002a\u00022!\u000eB~\t\u0015\t\u0015D1\u00019!\r)$q \u0003\u0006\tf\u0011\r\u0001\u000f\t\u0007]%\u0013)P!@\u0002\u0015\r\fg.T1q\u0007>d7/\u0006\u0006\u0004\b\r=11CB\f\u0007K!\u0002b!\u0003\u0004*\re2q\b\t\u000f\u0005\u0007\u00119ka\u0003\u0004\u001a\r}1\u0011EB\u0014!!q\u0003a!\u0004\u0004\u0012\rU\u0001cA\u001b\u0004\u0010\u0011)qG\u0007b\u0001qA\u0019Qga\u0005\u0005\u000b\u0005S\"\u0019\u0001\u001d\u0011\u0007U\u001a9\u0002B\u0003E5\t\u0007\u0001H\u0004\u0003\u0003<\u000em\u0011\u0002BB\u000f\u0005\u000b\f!aX\u0019\u0011\r9J5\u0011CB\u000b!\u0019q\u0013j!\u0005\u0004$A\u0019Qg!\n\u0005\r\t='D1\u00019!!q\u0003a!\u0004\u0004\u0012\r\r\u0002\"CB\u00165\u0005\u0005\t9AB\u0017\u0003-)g/\u001b3f]\u000e,G%M\u0019\u0011\r\r=2QGB\u0012\u001b\t\u0019\tDC\u0002\u00044%\nqA]3gY\u0016\u001cG/\u0003\u0003\u00048\rE\"\u0001C\"mCN\u001cH+Y4\t\u0013\rm\"$!AA\u0004\ru\u0012aC3wS\u0012,gnY3%cI\u0002RA`A\u0002\u0007GA\u0011b!\u0011\u001b\u0003\u0003\u0005\u001daa\u0011\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\r\t\u0007\u0003+\u000bYja\t\u0002%!\fg\u000e\u001a5pY\u0012\u001c\u0015M\\'ba\u000e{Gn]\u000b\t\u0007\u0013\u001a\tf!\u0016\u0004ZU\u001111\n\t\u000b\u0005O\u0014io!\u0014\u0004\u001a\rm\u0003\u0003\u0003\u0018\u0001\u0007\u001f\u001a\u0019fa\u0016\u0011\u0007U\u001a\t\u0006B\u000387\t\u0007\u0001\bE\u00026\u0007+\"Q!Q\u000eC\u0002a\u00022!NB-\t\u0015!5D1\u00019!\u0019q\u0013ja\u0015\u0004X\t91)\u001e:sS\u0016$WCBB1\u0007S\u001aIh\u0005\u0002\u001dO\t1!+Z:vYR,Baa\u001a\u0004\u0000A9Qg!\u001b\u0004x\ruDaBB69\t\u00071Q\u000e\u0002\u0002\u001bV)\u0001ha\u001c\u0004t\u001191\u0011OB5\u0005\u0004A$\u0001B0%IE\"qa!\u001e\u0004j\t\u0007\u0001H\u0001\u0003`I\u0011\u0012\u0004cA\u001b\u0004z\u0011111\u0010\u000fC\u0002a\u0012\u0011a\u0013\t\u0004k\r}D!\u0002#\u001e\u0005\u0004A\u0014\u0001C:dC2\f'o\u00144\u0016\u0011\r\u00155\u0011SBK\u00073+\"aa\"\u0011\u0011\t\r1\u0011RBG\u0007/KAaa#\u0003\u0006\tA1kY1mCJ|e\r\u0005\u0005/\u0001\r=51SBL!\r)4\u0011\u0013\u0003\u0006oy\u0011\r\u0001\u000f\t\u0004k\rUE!B!\u001f\u0005\u0004A\u0004cA\u001b\u0004\u001a\u0012)AI\bb\u0001q\u0001"
)
public interface Counter2 extends Tensor, Counter2Like {
   static ScalarOf scalarOf() {
      return Counter2$.MODULE$.scalarOf();
   }

   static CanCollapseAxis.HandHold handholdCanMapCols() {
      return Counter2$.MODULE$.handholdCanMapCols();
   }

   static CanCollapseAxis canMapCols(final ClassTag evidence$11, final Zero evidence$12, final Semiring evidence$13) {
      return Counter2$.MODULE$.canMapCols(evidence$11, evidence$12, evidence$13);
   }

   static CanCollapseAxis.HandHold handholdCanMapRows() {
      return Counter2$.MODULE$.handholdCanMapRows();
   }

   static CanCollapseAxis canMapRows(final Zero evidence$9, final Semiring evidence$10) {
      return Counter2$.MODULE$.canMapRows(evidence$9, evidence$10);
   }

   static CanSlice2 canSliceCol() {
      return Counter2$.MODULE$.canSliceCol();
   }

   static CanSlice2 canSliceRow() {
      return Counter2$.MODULE$.canSliceRow();
   }

   static CanTraverseKeyValuePairs canTraverseKeyValuePairs() {
      return Counter2$.MODULE$.canTraverseKeyValuePairs();
   }

   static CanTraverseValues canIterateValues() {
      return Counter2$.MODULE$.canIterateValues();
   }

   static CanMapValues CanMapValuesCounter(final Semiring evidence$7, final Zero evidence$8) {
      return Counter2$.MODULE$.CanMapValuesCounter(evidence$7, evidence$8);
   }

   static Counter2 count(final IterableOnce values) {
      return Counter2$.MODULE$.count(values);
   }

   static Counter2Ops.CanZipMapValuesCounter2 zipMap(final Zero evidence$23, final Semiring evidence$24) {
      return Counter2$.MODULE$.zipMap(evidence$23, evidence$24);
   }

   static UFunc.UImpl2 canMultiplyC2C2(final Semiring semiring) {
      return Counter2$.MODULE$.canMultiplyC2C2(semiring);
   }

   static UFunc.UImpl2 canMultiplyC2C1(final Semiring semiring) {
      return Counter2$.MODULE$.canMultiplyC2C1(semiring);
   }

   static UFunc.UImpl canNegate(final Ring ring) {
      return Counter2$.MODULE$.canNegate(ring);
   }

   static UFunc.InPlaceImpl2 canSetIntoVS() {
      return Counter2$.MODULE$.canSetIntoVS();
   }

   static UFunc.InPlaceImpl2 canSetIntoVV() {
      return Counter2$.MODULE$.canSetIntoVV();
   }

   static UFunc.InPlaceImpl2 canDivIntoVS(final Field evidence$20) {
      return Counter2$.MODULE$.canDivIntoVS(evidence$20);
   }

   static UFunc.UImpl2 canDivVS(final CanCopy copy, final Field semiring) {
      return Counter2$.MODULE$.canDivVS(copy, semiring);
   }

   static UFunc.UImpl2 canDivVV(final CanCopy copy, final Field semiring) {
      return Counter2$.MODULE$.canDivVV(copy, semiring);
   }

   static UFunc.InPlaceImpl2 canDivIntoVV(final Field evidence$19) {
      return Counter2$.MODULE$.canDivIntoVV(evidence$19);
   }

   static UFunc.UImpl2 canMulVS_M(final Semiring semiring) {
      return Counter2$.MODULE$.canMulVS_M(semiring);
   }

   static UFunc.UImpl2 canMulVS(final Semiring semiring) {
      return Counter2$.MODULE$.canMulVS(semiring);
   }

   static UFunc.InPlaceImpl2 canMulIntoVS_M(final Semiring evidence$18) {
      return Counter2$.MODULE$.canMulIntoVS_M(evidence$18);
   }

   static UFunc.InPlaceImpl2 canMulIntoVS(final Semiring evidence$17) {
      return Counter2$.MODULE$.canMulIntoVS(evidence$17);
   }

   static UFunc.UImpl2 canMulVV(final Semiring semiring) {
      return Counter2$.MODULE$.canMulVV(semiring);
   }

   static UFunc.InPlaceImpl2 canMulIntoVV(final Semiring evidence$16) {
      return Counter2$.MODULE$.canMulIntoVV(evidence$16);
   }

   static UFunc.UImpl2 subVS(final Ring evidence$14, final Zero evidence$15) {
      return Counter2$.MODULE$.subVS(evidence$14, evidence$15);
   }

   static UFunc.InPlaceImpl2 subIntoVS(final Ring evidence$13) {
      return Counter2$.MODULE$.subIntoVS(evidence$13);
   }

   static UFunc.UImpl2 subVV(final Ring evidence$11, final Zero evidence$12) {
      return Counter2$.MODULE$.subVV(evidence$11, evidence$12);
   }

   static UFunc.InPlaceImpl2 subIntoVV(final Ring evidence$10) {
      return Counter2$.MODULE$.subIntoVV(evidence$10);
   }

   static UFunc.UImpl2 addVS(final Semiring evidence$8, final Zero evidence$9) {
      return Counter2$.MODULE$.addVS(evidence$8, evidence$9);
   }

   static UFunc.InPlaceImpl2 addIntoVS(final Semiring evidence$7) {
      return Counter2$.MODULE$.addIntoVS(evidence$7);
   }

   static UFunc.UImpl2 addVV(final Semiring evidence$5, final Zero evidence$6) {
      return Counter2$.MODULE$.addVV(evidence$5, evidence$6);
   }

   static UFunc.InPlaceImpl3 canAxpy(final Semiring evidence$4) {
      return Counter2$.MODULE$.canAxpy(evidence$4);
   }

   static UFunc.InPlaceImpl2 addIntoVV(final Semiring evidence$3) {
      return Counter2$.MODULE$.addIntoVV(evidence$3);
   }

   static CanCopy canCopy(final Zero evidence$1, final Semiring evidence$2) {
      return Counter2$.MODULE$.canCopy(evidence$1, evidence$2);
   }

   static CanCollapseAxis canCollapseCols(final ClassTag evidence$17, final Zero evidence$18, final Semiring evidence$19) {
      return Counter2$.MODULE$.canCollapseCols(evidence$17, evidence$18, evidence$19);
   }

   static CanCollapseAxis canCollapseRows(final ClassTag evidence$14, final Zero evidence$15, final Semiring evidence$16) {
      return Counter2$.MODULE$.canCollapseRows(evidence$14, evidence$15, evidence$16);
   }

   public static class Impl implements Counter2, Serializable {
      private static final long serialVersionUID = 1L;
      private final Map data;
      private final Zero scalar;

      public int size() {
         return Counter2Like.size$(this);
      }

      public Object apply(final Tuple2 i) {
         return Counter2Like.apply$(this, i);
      }

      public Object apply(final Object k, final Object k2) {
         return Counter2Like.apply$(this, k, k2);
      }

      public boolean contains(final Object k) {
         return Counter2Like.contains$(this, k);
      }

      public boolean contains(final Object k1, final Object k2) {
         return Counter2Like.contains$(this, k1, k2);
      }

      public void update(final Tuple2 i, final Object v) {
         Counter2Like.update$(this, i, v);
      }

      public void update(final Object k1, final Object k2, final Object v) {
         Counter2Like.update$(this, k1, k2, v);
      }

      public Object innerGetOrElseUpdate(final Object k, final Map m) {
         return Counter2Like.innerGetOrElseUpdate$(this, k, m);
      }

      public Iterator keysIterator() {
         return Counter2Like.keysIterator$(this);
      }

      public Iterator valuesIterator() {
         return Counter2Like.valuesIterator$(this);
      }

      public Iterator iterator() {
         return Counter2Like.iterator$(this);
      }

      public int activeSize() {
         return Counter2Like.activeSize$(this);
      }

      public Iterator activeIterator() {
         return Counter2Like.activeIterator$(this);
      }

      public Iterator activeKeysIterator() {
         return Counter2Like.activeKeysIterator$(this);
      }

      public Iterator activeValuesIterator() {
         return Counter2Like.activeValuesIterator$(this);
      }

      public Counter2 repr() {
         return Counter2Like.repr$(this);
      }

      public String toString() {
         return Counter2Like.toString$(this);
      }

      public boolean equals(final Object p1) {
         return Counter2Like.equals$(this, p1);
      }

      public double apply$mcID$sp(final int i) {
         return TensorLike.apply$mcID$sp$(this, i);
      }

      public float apply$mcIF$sp(final int i) {
         return TensorLike.apply$mcIF$sp$(this, i);
      }

      public int apply$mcII$sp(final int i) {
         return TensorLike.apply$mcII$sp$(this, i);
      }

      public long apply$mcIJ$sp(final int i) {
         return TensorLike.apply$mcIJ$sp$(this, i);
      }

      public void update$mcID$sp(final int i, final double v) {
         TensorLike.update$mcID$sp$(this, i, v);
      }

      public void update$mcIF$sp(final int i, final float v) {
         TensorLike.update$mcIF$sp$(this, i, v);
      }

      public void update$mcII$sp(final int i, final int v) {
         TensorLike.update$mcII$sp$(this, i, v);
      }

      public void update$mcIJ$sp(final int i, final long v) {
         TensorLike.update$mcIJ$sp$(this, i, v);
      }

      public TensorKeys keys() {
         return TensorLike.keys$(this);
      }

      public TensorValues values() {
         return TensorLike.values$(this);
      }

      public TensorPairs pairs() {
         return TensorLike.pairs$(this);
      }

      public TensorActive active() {
         return TensorLike.active$(this);
      }

      public Object apply(final Object slice, final CanSlice canSlice) {
         return TensorLike.apply$(this, slice, canSlice);
      }

      public Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
         return TensorLike.apply$(this, a, b, c, slice, canSlice);
      }

      public Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
         return TensorLike.apply$mcI$sp$(this, a, b, c, slice, canSlice);
      }

      public Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
         return TensorLike.apply$(this, slice1, slice2, canSlice);
      }

      public Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$(this, f, bf);
      }

      public Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$mcID$sp$(this, f, bf);
      }

      public Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$mcIF$sp$(this, f, bf);
      }

      public Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$mcII$sp$(this, f, bf);
      }

      public Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapPairs$mcIJ$sp$(this, f, bf);
      }

      public Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$(this, f, bf);
      }

      public Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$mcID$sp$(this, f, bf);
      }

      public Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$mcIF$sp$(this, f, bf);
      }

      public Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$mcII$sp$(this, f, bf);
      }

      public Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
         return TensorLike.mapActivePairs$mcIJ$sp$(this, f, bf);
      }

      public Object mapValues(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$(this, f, bf);
      }

      public Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$mcD$sp$(this, f, bf);
      }

      public Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$mcF$sp$(this, f, bf);
      }

      public Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$mcI$sp$(this, f, bf);
      }

      public Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapValues$mcJ$sp$(this, f, bf);
      }

      public Object mapActiveValues(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$(this, f, bf);
      }

      public Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$mcD$sp$(this, f, bf);
      }

      public Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$mcF$sp$(this, f, bf);
      }

      public Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$mcI$sp$(this, f, bf);
      }

      public Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
         return TensorLike.mapActiveValues$mcJ$sp$(this, f, bf);
      }

      public void foreachKey(final Function1 fn) {
         TensorLike.foreachKey$(this, fn);
      }

      public void foreachKey$mcI$sp(final Function1 fn) {
         TensorLike.foreachKey$mcI$sp$(this, fn);
      }

      public void foreachPair(final Function2 fn) {
         TensorLike.foreachPair$(this, fn);
      }

      public void foreachPair$mcID$sp(final Function2 fn) {
         TensorLike.foreachPair$mcID$sp$(this, fn);
      }

      public void foreachPair$mcIF$sp(final Function2 fn) {
         TensorLike.foreachPair$mcIF$sp$(this, fn);
      }

      public void foreachPair$mcII$sp(final Function2 fn) {
         TensorLike.foreachPair$mcII$sp$(this, fn);
      }

      public void foreachPair$mcIJ$sp(final Function2 fn) {
         TensorLike.foreachPair$mcIJ$sp$(this, fn);
      }

      public void foreachValue(final Function1 fn) {
         TensorLike.foreachValue$(this, fn);
      }

      public void foreachValue$mcD$sp(final Function1 fn) {
         TensorLike.foreachValue$mcD$sp$(this, fn);
      }

      public void foreachValue$mcF$sp(final Function1 fn) {
         TensorLike.foreachValue$mcF$sp$(this, fn);
      }

      public void foreachValue$mcI$sp(final Function1 fn) {
         TensorLike.foreachValue$mcI$sp$(this, fn);
      }

      public void foreachValue$mcJ$sp(final Function1 fn) {
         TensorLike.foreachValue$mcJ$sp$(this, fn);
      }

      public boolean forall(final Function2 fn) {
         return TensorLike.forall$(this, (Function2)fn);
      }

      public boolean forall$mcID$sp(final Function2 fn) {
         return TensorLike.forall$mcID$sp$(this, fn);
      }

      public boolean forall$mcIF$sp(final Function2 fn) {
         return TensorLike.forall$mcIF$sp$(this, fn);
      }

      public boolean forall$mcII$sp(final Function2 fn) {
         return TensorLike.forall$mcII$sp$(this, fn);
      }

      public boolean forall$mcIJ$sp(final Function2 fn) {
         return TensorLike.forall$mcIJ$sp$(this, fn);
      }

      public boolean forall(final Function1 fn) {
         return TensorLike.forall$(this, (Function1)fn);
      }

      public boolean forall$mcD$sp(final Function1 fn) {
         return TensorLike.forall$mcD$sp$(this, fn);
      }

      public boolean forall$mcF$sp(final Function1 fn) {
         return TensorLike.forall$mcF$sp$(this, fn);
      }

      public boolean forall$mcI$sp(final Function1 fn) {
         return TensorLike.forall$mcI$sp$(this, fn);
      }

      public boolean forall$mcJ$sp(final Function1 fn) {
         return TensorLike.forall$mcJ$sp$(this, fn);
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

      public IndexedSeq findAll(final Function1 f) {
         return QuasiTensor.findAll$(this, f);
      }

      public IndexedSeq findAll$mcD$sp(final Function1 f) {
         return QuasiTensor.findAll$mcD$sp$(this, f);
      }

      public IndexedSeq findAll$mcF$sp(final Function1 f) {
         return QuasiTensor.findAll$mcF$sp$(this, f);
      }

      public IndexedSeq findAll$mcI$sp(final Function1 f) {
         return QuasiTensor.findAll$mcI$sp$(this, f);
      }

      public IndexedSeq findAll$mcJ$sp(final Function1 f) {
         return QuasiTensor.findAll$mcJ$sp$(this, f);
      }

      public int hashCode() {
         return QuasiTensor.hashCode$(this);
      }

      public Map data() {
         return this.data;
      }

      public Object default() {
         return this.scalar.zero();
      }

      public Set keySet() {
         return new Counter2KeySet(this.data());
      }

      public Impl(final Map data, final Zero scalar) {
         this.data = data;
         this.scalar = scalar;
         QuasiTensor.$init$(this);
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         TensorLike.$init$(this);
         Counter2Like.$init$(this);
      }
   }

   private static class CounterHashMap extends HashMap {
      private static final long serialVersionUID = 1L;
      private final Zero evidence$2;

      public Counter default(final Object k) {
         return Counter$.MODULE$.apply(this.evidence$2);
      }

      public CounterHashMap(final Zero evidence$2) {
         this.evidence$2 = evidence$2;
      }
   }

   public interface Curried {
   }
}
