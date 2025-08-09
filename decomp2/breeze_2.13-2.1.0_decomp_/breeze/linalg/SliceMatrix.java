package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorValues;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.ArrowAssoc.;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r}a\u0001B\u0011#\u0001\u001dB\u0001b\u001b\u0001\u0003\u0006\u0004%\t\u0001\u001c\u0005\tg\u0002\u0011\t\u0011)A\u0005[\"AA\u000f\u0001BC\u0002\u0013\u0005Q\u000f\u0003\u0005~\u0001\t\u0005\t\u0015!\u0003w\u0011!q\bA!b\u0001\n\u0003y\bBCA\u0002\u0001\t\u0005\t\u0015!\u0003\u0002\u0002!Q\u0011Q\u0001\u0001\u0003\u0004\u0003\u0006Y!a\u0002\t\u0015\u0005M\u0001AaA!\u0002\u0017\t)\u0002C\u0004\u0002\"\u0001!\t!a\t\t\u000f\u0005E\u0002\u0001\"\u0001\u00024!9\u00111\t\u0001\u0005\u0002\u0005\u0015\u0003bBA+\u0001\u0011\u0005\u0011q\u000b\u0005\b\u00033\u0002A\u0011AA,\u0011\u001d\tY\u0006\u0001C\u0001\u0003;Bq!!\u001a\u0001\t\u0003\t9\u0007C\u0004\u0002p\u0001!\t!!\u001d\t\u000f\u0005U\u0004\u0001\"\u0001\u0002X!9\u0011q\u000f\u0001\u0005\u0002\u0005e\u0004bBA>\u0001\u0011\u0005\u0011Q\u0010\u0005\b\u0003\u007f\u0002A\u0011AAA\u0011%\t\u0019\nAI\u0001\n\u0003\t)jB\u0004\u0002,\nB\t!!,\u0007\r\u0005\u0012\u0003\u0012AAX\u0011\u001d\t\tc\u0006C\u0001\u0003{Cq!a0\u0018\t\u0007\t\t\rC\u0004\u0003\u0002]!\u0019Aa\u0001\t\u000f\t%s\u0003b\u0001\u0003L!9!\u0011O\f\u0005\u0004\tM\u0004b\u0002BF/\u0011\r!Q\u0012\u0005\b\u0005K;B1\u0001BT\u0011\u001d\u0011yl\u0006C\u0002\u0005\u0003DqA!?\u0018\t\u0007\u0011YPA\u0006TY&\u001cW-T1ue&D(BA\u0012%\u0003\u0019a\u0017N\\1mO*\tQ%\u0001\u0004ce\u0016,'0Z\u0002\u0001+\u0011A\u0003MZ\u001b\u0014\t\u0001Isf\u0017\t\u0003U5j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0007A\n4'D\u0001#\u0013\t\u0011$E\u0001\u0004NCR\u0014\u0018\u000e\u001f\t\u0003iUb\u0001\u0001B\u00057\u0001\u0001\u0006\t\u0011!b\u0001o\t\ta+\u0005\u00029wA\u0011!&O\u0005\u0003u-\u0012qAT8uQ&tw\r\u0005\u0002+y%\u0011Qh\u000b\u0002\u0004\u0003:L\bFB\u001b@\u00052\u000bf\u000b\u0005\u0002+\u0001&\u0011\u0011i\u000b\u0002\fgB,7-[1mSj,G-M\u0003$\u0007\u00123UI\u0004\u0002+\t&\u0011QiK\u0001\u0007\t>,(\r\\32\t\u0011:5\n\f\b\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015\u001a\na\u0001\u0010:p_Rt\u0014\"\u0001\u00172\u000b\rje\nU(\u000f\u0005)r\u0015BA(,\u0003\rIe\u000e^\u0019\u0005I\u001d[E&M\u0003$%N+FK\u0004\u0002+'&\u0011AkK\u0001\u0006\r2|\u0017\r^\u0019\u0005I\u001d[E&M\u0003$/bS\u0016L\u0004\u0002+1&\u0011\u0011lK\u0001\u0005\u0019>tw-\r\u0003%\u000f.c\u0003\u0003\u0002\u0019]gyK!!\u0018\u0012\u0003\u00155\u000bGO]5y\u0019&\\W\rE\u00031\u0001}+7\u0007\u0005\u00025A\u0012I\u0011\r\u0001Q\u0001\u0002\u0003\u0015\ra\u000e\u0002\u0003\u0017FB3\u0001Y dc\u0015\u0019SJ\u00143Pc\u0011!si\u0013\u0017\u0011\u0005Q2G!C4\u0001A\u0003\u0005\tQ1\u00018\u0005\tY%\u0007K\u0002g\u007f%\fTaI'OU>\u000bD\u0001J$LY\u00051A/\u001a8t_J,\u0012!\u001c\t\u0005a9\u00048'\u0003\u0002pE\t1A+\u001a8t_J\u0004BAK9`K&\u0011!o\u000b\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u000fQ,gn]8sA\u000511\u000f\\5dKF*\u0012A\u001e\t\u0004oj|fBA$y\u0013\tI8&A\u0004qC\u000e\\\u0017mZ3\n\u0005md(AC%oI\u0016DX\rZ*fc*\u0011\u0011pK\u0001\bg2L7-Z\u0019!\u0003\u0019\u0019H.[2feU\u0011\u0011\u0011\u0001\t\u0004oj,\u0017aB:mS\u000e,'\u0007I\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004#BA\u0005\u0003\u001f\u0019TBAA\u0006\u0015\r\ti\u0001J\u0001\u0005[\u0006$\b.\u0003\u0003\u0002\u0012\u0005-!\u0001C*f[&\u0014\u0018N\\4\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0003\u0002\u0018\u0005u1'\u0004\u0002\u0002\u001a)\u0019\u00111D\u0016\u0002\u000fI,g\r\\3di&!\u0011qDA\r\u0005!\u0019E.Y:t)\u0006<\u0017A\u0002\u001fj]&$h\b\u0006\u0005\u0002&\u0005-\u0012QFA\u0018)\u0015q\u0016qEA\u0015\u0011\u001d\t)!\u0003a\u0002\u0003\u000fAq!a\u0005\n\u0001\b\t)\u0002C\u0003l\u0013\u0001\u0007Q\u000eC\u0003u\u0013\u0001\u0007a\u000f\u0003\u0004\u007f\u0013\u0001\u0007\u0011\u0011A\u0001\u0006CB\u0004H.\u001f\u000b\u0006g\u0005U\u0012q\b\u0005\b\u0003oQ\u0001\u0019AA\u001d\u0003\u0005I\u0007c\u0001\u0016\u0002<%\u0019\u0011QH\u0016\u0003\u0007%sG\u000fC\u0004\u0002B)\u0001\r!!\u000f\u0002\u0003)\fa!\u001e9eCR,G\u0003CA$\u0003\u001b\ny%!\u0015\u0011\u0007)\nI%C\u0002\u0002L-\u0012A!\u00168ji\"9\u0011qG\u0006A\u0002\u0005e\u0002bBA!\u0017\u0001\u0007\u0011\u0011\b\u0005\u0007\u0003'Z\u0001\u0019A\u001a\u0002\u0003\u0015\fAA]8xgV\u0011\u0011\u0011H\u0001\u0005G>d7/\u0001\u000bbGRLg/\u001a,bYV,7/\u0013;fe\u0006$xN]\u000b\u0003\u0003?\u0002Ba^A1g%\u0019\u00111\r?\u0003\u0011%#XM]1u_J\fa\"Y2uSZ,\u0017\n^3sCR|'/\u0006\u0002\u0002jA)q/!\u0019\u0002lA)!&]A7gA1!&]A\u001d\u0003s\t!#Y2uSZ,7*Z=t\u0013R,'/\u0019;peV\u0011\u00111\u000f\t\u0006o\u0006\u0005\u0014QN\u0001\u000bC\u000e$\u0018N^3TSj,\u0017\u0001\u0002:faJ,\u0012AX\u0001\u0005G>\u0004\u00180F\u00010\u0003\u001d1G.\u0019;uK:$B!a!\u0002\nB!\u0001'!\"4\u0013\r\t9I\t\u0002\u0007-\u0016\u001cGo\u001c:\t\u0013\u0005-E\u0003%AA\u0002\u00055\u0015\u0001\u0002<jK^\u00042\u0001MAH\u0013\r\t\tJ\t\u0002\u0005-&,w/A\tgY\u0006$H/\u001a8%I\u00164\u0017-\u001e7uIE*\"!a&+\t\u00055\u0015\u0011T\u0016\u0003\u00037\u0003B!!(\u0002(6\u0011\u0011q\u0014\u0006\u0005\u0003C\u000b\u0019+A\u0005v]\u000eDWmY6fI*\u0019\u0011QU\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002*\u0006}%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006Y1\u000b\\5dK6\u000bGO]5y!\t\u0001tc\u0005\u0004\u0018S\u0005E\u0016q\u0017\t\u0004a\u0005M\u0016bAA[E\t1Bj\\<Qe&|'/\u001b;z'2L7-Z'biJL\u0007\u0010E\u00021\u0003sK1!a/#\u00059\u0019F.[2f\u001b\u0006$(/\u001b=PaN$\"!!,\u0002'\r\fg.T1q\u0017\u0016Lh+\u00197vKB\u000b\u0017N]:\u0016\u0015\u0005\r\u0017Q[Am\u0003;\f\t\u000f\u0006\u0004\u0002F\u0006-\u0018\u0011\u001f\t\u000f\u0003\u000f\fi-!5\u0002n\u0005m\u0017q\\As\u001b\t\tIMC\u0002\u0002L\n\nqa];qa>\u0014H/\u0003\u0003\u0002P\u0006%'aE\"b]6\u000b\u0007oS3z-\u0006dW/\u001a)bSJ\u001c\b\u0003\u0003\u0019\u0001\u0003'\f9.a7\u0011\u0007Q\n)\u000eB\u0003b3\t\u0007q\u0007E\u00025\u00033$QaZ\rC\u0002]\u00022\u0001NAo\t\u00151\u0014D1\u00018!\r!\u0014\u0011\u001d\u0003\u0007\u0003GL\"\u0019A\u001c\u0003\u0005Y\u0013\u0004#\u0002\u0019\u0002h\u0006}\u0017bAAuE\tYA)\u001a8tK6\u000bGO]5y\u0011%\ti/GA\u0001\u0002\b\ty/\u0001\u0006fm&$WM\\2fIM\u0002b!a\u0006\u0002\u001e\u0005}\u0007\"CAz3\u0005\u0005\t9AA{\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0007\u0003o\fi0a8\u000e\u0005\u0005e(bAA~I\u000591\u000f^8sC\u001e,\u0017\u0002BA\u0000\u0003s\u0014AAW3s_\u0006a1-\u00198NCB4\u0016\r\\;fgVQ!Q\u0001B\t\u0005+\u0011IBa\u000b\u0015\r\t\u001d!Q\bB\"!1\t9M!\u0003\u0003\u000e\t]!\u0011\u0006B\u001e\u0013\u0011\u0011Y!!3\u0003\u0019\r\u000bg.T1q-\u0006dW/Z:\u0011\u0011A\u0002!q\u0002B\n\u0005/\u00012\u0001\u000eB\t\t\u0015\t'D1\u00018!\r!$Q\u0003\u0003\u0006Oj\u0011\ra\u000e\t\u0004i\teA!\u0003\u001c\u001bA\u0003\u0005\tQ1\u00018Q%\u0011Ib\u0010B\u000f\u0005C\u0011)#\r\u0004$\u001b:\u0013ybT\u0019\u0005I\u001d[E&\r\u0004$%N\u0013\u0019\u0003V\u0019\u0005I\u001d[E&\r\u0004$\u0007\u0012\u00139#R\u0019\u0005I\u001d[E\u0006E\u00025\u0005W!!\"a9\u001bA\u0003\u0005\tQ1\u00018Q%\u0011Yc\u0010B\u0018\u0005g\u00119$\r\u0004$\u001b:\u0013\tdT\u0019\u0005I\u001d[E&\r\u0004$%N\u0013)\u0004V\u0019\u0005I\u001d[E&\r\u0004$\u0007\u0012\u0013I$R\u0019\u0005I\u001d[E\u0006E\u00031\u0003O\u0014I\u0003C\u0005\u0003@i\t\t\u0011q\u0001\u0003B\u0005QQM^5eK:\u001cW\rJ\u001b\u0011\r\u0005]\u0011Q\u0004B\u0015\u0011%\u0011)EGA\u0001\u0002\b\u00119%\u0001\u0006fm&$WM\\2fIY\u0002b!a>\u0002~\n%\u0012AE2b]\u000e\u0013X-\u0019;f5\u0016\u0014xn\u001d'jW\u0016,\u0002B!\u0014\u0003Z\tu#\u0011\r\u000b\u0007\u0005\u001f\u0012)Ga\u001b\u0011\u0011\u0005\u001d'\u0011\u000bB+\u0005GJAAa\u0015\u0002J\n\u00112)\u00198De\u0016\fG/\u001a.fe>\u001cH*[6f!!\u0001\u0004Aa\u0016\u0003\\\t}\u0003c\u0001\u001b\u0003Z\u0011)\u0011m\u0007b\u0001oA\u0019AG!\u0018\u0005\u000b\u001d\\\"\u0019A\u001c\u0011\u0007Q\u0012\t\u0007B\u000377\t\u0007q\u0007E\u00031\u0003O\u0014y\u0006C\u0005\u0003hm\t\t\u0011q\u0001\u0003j\u0005QQM^5eK:\u001cW\rJ\u001c\u0011\r\u0005]\u0011Q\u0004B0\u0011%\u0011igGA\u0001\u0002\b\u0011y'\u0001\u0006fm&$WM\\2fIa\u0002b!a>\u0002~\n}\u0013\u0001E2b]&#XM]1uKZ\u000bG.^3t+!\u0011)H!!\u0003\u0006\n%UC\u0001B<!!\t9M!\u001f\u0003~\t\u001d\u0015\u0002\u0002B>\u0003\u0013\u0014\u0011cQ1o)J\fg/\u001a:tKZ\u000bG.^3t!!\u0001\u0004Aa \u0003\u0004\n\u001d\u0005c\u0001\u001b\u0003\u0002\u0012)\u0011\r\bb\u0001oA\u0019AG!\"\u0005\u000b\u001dd\"\u0019A\u001c\u0011\u0007Q\u0012I\tB\u000379\t\u0007q'A\fdC:LE/\u001a:bi\u0016\\U-\u001f,bYV,\u0007+Y5sgVA!q\u0012BN\u0005?\u0013\u0019+\u0006\u0002\u0003\u0012BQ\u0011q\u0019BJ\u0005/\u000biG!)\n\t\tU\u0015\u0011\u001a\u0002\u0019\u0007\u0006tGK]1wKJ\u001cXmS3z-\u0006dW/\u001a)bSJ\u001c\b\u0003\u0003\u0019\u0001\u00053\u0013iJ!)\u0011\u0007Q\u0012Y\nB\u0003b;\t\u0007q\u0007E\u00025\u0005?#QaZ\u000fC\u0002]\u00022\u0001\u000eBR\t\u00151TD1\u00018\u0003I\u0019\u0017M\u001c+sC:\u001chm\u001c:n-\u0006dW/Z:\u0016\u0011\t%&Q\u0017B]\u0005{+\"Aa+\u0011\u0011\u0005\u001d'Q\u0016BY\u0005wKAAa,\u0002J\n\u00112)\u00198Ue\u0006t7OZ8s[Z\u000bG.^3t!!\u0001\u0004Aa-\u00038\nm\u0006c\u0001\u001b\u00036\u0012)\u0011M\bb\u0001oA\u0019AG!/\u0005\u000b\u001dt\"\u0019A\u001c\u0011\u0007Q\u0012i\fB\u00037=\t\u0007q'A\u0006dC:\u001cF.[2f%><X\u0003\u0003Bb\u0005\u001f\u0014\u0019Na6\u0015\r\t\u0015'Q\u001eBz!1\t9Ma2\u0003L\u0006e\"\u0011\u001cBp\u0013\u0011\u0011I-!3\u0003\u0013\r\u000bgn\u00157jG\u0016\u0014\u0004\u0003\u0003\u0019\u0001\u0005\u001b\u0014\tN!6\u0011\u0007Q\u0012y\rB\u0003b?\t\u0007q\u0007E\u00025\u0005'$QaZ\u0010C\u0002]\u00022\u0001\u000eBl\t\u00151tD1\u00018\u001d\r9(1\\\u0005\u0004\u0005;d\u0018\u0001\u0004\u0013d_2|g\u000eJ2pY>t\u0007#\u0002\u0019\u0003b\n\u0015\u0018b\u0001BrE\tIAK]1ogB|7/\u001a\t\ba\t\u001d(1\u001eBk\u0013\r\u0011IO\t\u0002\f'2L7-\u001a,fGR|'\u000f\u0005\u0004+c\n5'\u0011\u001b\u0005\n\u0005_|\u0012\u0011!a\u0002\u0005c\f!\"\u001a<jI\u0016t7-\u001a\u0013:!\u0019\tI!a\u0004\u0003V\"I!Q_\u0010\u0002\u0002\u0003\u000f!q_\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007\u0005\u0004\u0002\u0018\u0005u!Q[\u0001\fG\u0006t7\u000b\\5dK\u000e{G.\u0006\u0005\u0003~\u000e\u00151\u0011BB\u0007)\u0019\u0011ypa\u0005\u0004\u001aAa\u0011q\u0019Bd\u0007\u0003\u0011I.!\u000f\u0004\u0010AA\u0001\u0007AB\u0002\u0007\u000f\u0019Y\u0001E\u00025\u0007\u000b!Q!\u0019\u0011C\u0002]\u00022\u0001NB\u0005\t\u00159\u0007E1\u00018!\r!4Q\u0002\u0003\u0006m\u0001\u0012\ra\u000e\t\ba\t\u001d8\u0011CB\u0006!\u0019Q\u0013oa\u0001\u0004\b!I1Q\u0003\u0011\u0002\u0002\u0003\u000f1qC\u0001\fKZLG-\u001a8dK\u0012\n\u0014\u0007\u0005\u0004\u0002\n\u0005=11\u0002\u0005\n\u00077\u0001\u0013\u0011!a\u0002\u0007;\t1\"\u001a<jI\u0016t7-\u001a\u00132eA1\u0011qCA\u000f\u0007\u0017\u0001"
)
public class SliceMatrix implements Matrix {
   public final Tensor tensor;
   private final IndexedSeq slice1;
   private final IndexedSeq slice2;
   public final Semiring evidence$1;
   public final ClassTag breeze$linalg$SliceMatrix$$evidence$2;

   public static CanSlice2 canSliceCol(final Semiring evidence$11, final ClassTag evidence$12) {
      return SliceMatrix$.MODULE$.canSliceCol(evidence$11, evidence$12);
   }

   public static CanSlice2 canSliceRow(final Semiring evidence$9, final ClassTag evidence$10) {
      return SliceMatrix$.MODULE$.canSliceRow(evidence$9, evidence$10);
   }

   public static CanTransformValues canTransformValues() {
      return SliceMatrix$.MODULE$.canTransformValues();
   }

   public static CanTraverseKeyValuePairs canIterateKeyValuePairs() {
      return SliceMatrix$.MODULE$.canIterateKeyValuePairs();
   }

   public static CanTraverseValues canIterateValues() {
      return SliceMatrix$.MODULE$.canIterateValues();
   }

   public static CanCreateZerosLike canCreateZerosLike(final ClassTag evidence$7, final Zero evidence$8) {
      return SliceMatrix$.MODULE$.canCreateZerosLike(evidence$7, evidence$8);
   }

   public static CanMapValues canMapValues(final ClassTag evidence$5, final Zero evidence$6) {
      return SliceMatrix$.MODULE$.canMapValues(evidence$5, evidence$6);
   }

   public static CanMapKeyValuePairs canMapKeyValuePairs(final ClassTag evidence$3, final Zero evidence$4) {
      return SliceMatrix$.MODULE$.canMapKeyValuePairs(evidence$3, evidence$4);
   }

   public static UFunc.InPlaceImpl2 opSetInPlace() {
      return SliceMatrix$.MODULE$.opSetInPlace();
   }

   public static CanCollapseAxis canCollapseCols_SliceMatrix(final Semiring evidence$21, final ClassTag evidence$22, final ClassTag evidence$23, final Zero evidence$24) {
      return SliceMatrix$.MODULE$.canCollapseCols_SliceMatrix(evidence$21, evidence$22, evidence$23, evidence$24);
   }

   public static CanCollapseAxis canCollapseRows_SliceMatrix(final Semiring evidence$17, final ClassTag evidence$18, final ClassTag evidence$19, final Zero evidence$20) {
      return SliceMatrix$.MODULE$.canCollapseRows_SliceMatrix(evidence$17, evidence$18, evidence$19, evidence$20);
   }

   public static CanCollapseAxis.HandHold handholdCanMapCols_SM() {
      return SliceMatrix$.MODULE$.handholdCanMapCols_SM();
   }

   public static CanCollapseAxis.HandHold handholdCanMapRows_SM() {
      return SliceMatrix$.MODULE$.handholdCanMapRows_SM();
   }

   public static CanSlice2 canSliceWeirdCols_SM(final Semiring evidence$15, final ClassTag evidence$16) {
      return SliceMatrix$.MODULE$.canSliceWeirdCols_SM(evidence$15, evidence$16);
   }

   public static CanSlice2 canSliceWeirdRows_SM(final Semiring evidence$13, final ClassTag evidence$14) {
      return SliceMatrix$.MODULE$.canSliceWeirdRows_SM(evidence$13, evidence$14);
   }

   public Object apply(final Tuple2 i) {
      return Matrix.apply$(this, i);
   }

   public double apply$mcD$sp(final Tuple2 i) {
      return Matrix.apply$mcD$sp$(this, i);
   }

   public float apply$mcF$sp(final Tuple2 i) {
      return Matrix.apply$mcF$sp$(this, i);
   }

   public int apply$mcI$sp(final Tuple2 i) {
      return Matrix.apply$mcI$sp$(this, i);
   }

   public long apply$mcJ$sp(final Tuple2 i) {
      return Matrix.apply$mcJ$sp$(this, i);
   }

   public void update(final Tuple2 i, final Object e) {
      Matrix.update$(this, i, e);
   }

   public void update$mcD$sp(final Tuple2 i, final double e) {
      Matrix.update$mcD$sp$(this, i, e);
   }

   public void update$mcF$sp(final Tuple2 i, final float e) {
      Matrix.update$mcF$sp$(this, i, e);
   }

   public void update$mcI$sp(final Tuple2 i, final int e) {
      Matrix.update$mcI$sp$(this, i, e);
   }

   public void update$mcJ$sp(final Tuple2 i, final long e) {
      Matrix.update$mcJ$sp$(this, i, e);
   }

   public int size() {
      return Matrix.size$(this);
   }

   public Set keySet() {
      return Matrix.keySet$(this);
   }

   public Iterator iterator() {
      return Matrix.iterator$(this);
   }

   public Iterator valuesIterator() {
      return Matrix.valuesIterator$(this);
   }

   public Iterator keysIterator() {
      return Matrix.keysIterator$(this);
   }

   public String toString(final int maxLines, final int maxWidth) {
      return Matrix.toString$(this, maxLines, maxWidth);
   }

   public int toString$default$1() {
      return Matrix.toString$default$1$(this);
   }

   public int toString$default$2() {
      return Matrix.toString$default$2$(this);
   }

   public String toString() {
      return Matrix.toString$(this);
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return Matrix.toDenseMatrix$(this, cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcD$sp(final ClassTag cm, final Zero zero) {
      return Matrix.toDenseMatrix$mcD$sp$(this, cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcF$sp(final ClassTag cm, final Zero zero) {
      return Matrix.toDenseMatrix$mcF$sp$(this, cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
      return Matrix.toDenseMatrix$mcI$sp$(this, cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
      return Matrix.toDenseMatrix$mcJ$sp$(this, cm, zero);
   }

   public boolean equals(final Object p1) {
      return Matrix.equals$(this, p1);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcD$sp$(this, fn, canMapValues);
   }

   public Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcF$sp$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcI$sp$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike.map$mcJ$sp$(this, fn, canMapValues);
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

   public Tensor tensor() {
      return this.tensor;
   }

   public IndexedSeq slice1() {
      return this.slice1;
   }

   public IndexedSeq slice2() {
      return this.slice2;
   }

   public Object apply(final int i, final int j) {
      return this.tensor().apply(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.slice1().apply(i)), this.slice2().apply(j)));
   }

   public void update(final int i, final int j, final Object e) {
      this.tensor().update(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.slice1().apply(i)), this.slice2().apply(j)), e);
   }

   public int rows() {
      return this.slice1().length();
   }

   public int cols() {
      return this.slice2().length();
   }

   public Iterator activeValuesIterator() {
      return this.valuesIterator();
   }

   public Iterator activeIterator() {
      return this.iterator();
   }

   public Iterator activeKeysIterator() {
      return this.keysIterator();
   }

   public int activeSize() {
      return this.size();
   }

   public SliceMatrix repr() {
      return this;
   }

   public Matrix copy() {
      Object var10000;
      if (this.rows() == 0) {
         var10000 = Matrix$.MODULE$.zeroRows(this.cols(), this.breeze$linalg$SliceMatrix$$evidence$2);
      } else if (this.cols() == 0) {
         var10000 = Matrix$.MODULE$.zeroCols(this.rows(), this.breeze$linalg$SliceMatrix$$evidence$2);
      } else {
         DenseMatrix result = new DenseMatrix(this.rows(), this.cols(), this.breeze$linalg$SliceMatrix$$evidence$2.newArray(this.size()));
         result.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DM_M());
         var10000 = result;
      }

      return (Matrix)var10000;
   }

   public Vector flatten(final View view) {
      if (View.Require$.MODULE$.equals(view)) {
         throw new UnsupportedOperationException("Cannot make Vector as view of SliceMatrix.");
      } else {
         Vector var2;
         if (!View.Copy$.MODULE$.equals(view)) {
            if (!View.Prefer$.MODULE$.equals(view)) {
               throw new MatchError(view);
            }

            var2 = this.flatten(View.Copy$.MODULE$);
         } else {
            VectorBuilder vb = new VectorBuilder(this.rows() * this.cols(), this.activeSize(), this.evidence$1, this.breeze$linalg$SliceMatrix$$evidence$2);
            Iterator ai = this.activeIterator();

            while(ai.hasNext()) {
               Tuple2 var8 = (Tuple2)ai.next();
               if (var8 == null) {
                  throw new MatchError(var8);
               }

               Tuple2 var9 = (Tuple2)var8._1();
               Object v = var8._2();
               if (var9 == null) {
                  throw new MatchError(var8);
               }

               int r = var9._1$mcI$sp();
               int c = var9._2$mcI$sp();
               Tuple3 var3 = new Tuple3(BoxesRunTime.boxToInteger(r), BoxesRunTime.boxToInteger(c), v);
               int r = BoxesRunTime.unboxToInt(var3._1());
               int c = BoxesRunTime.unboxToInt(var3._2());
               Object v = var3._3();
               vb.add(c * this.rows() + r, v);
            }

            var2 = vb.toVector();
         }

         return var2;
      }
   }

   public View flatten$default$1() {
      return View.Copy$.MODULE$;
   }

   public Tensor tensor$mcIID$sp() {
      return this.tensor();
   }

   public Tensor tensor$mcIIF$sp() {
      return this.tensor();
   }

   public Tensor tensor$mcIII$sp() {
      return this.tensor();
   }

   public Tensor tensor$mcIIJ$sp() {
      return this.tensor();
   }

   public double apply$mcD$sp(final int i, final int j) {
      return BoxesRunTime.unboxToDouble(this.apply(i, j));
   }

   public float apply$mcF$sp(final int i, final int j) {
      return BoxesRunTime.unboxToFloat(this.apply(i, j));
   }

   public int apply$mcI$sp(final int i, final int j) {
      return BoxesRunTime.unboxToInt(this.apply(i, j));
   }

   public long apply$mcJ$sp(final int i, final int j) {
      return BoxesRunTime.unboxToLong(this.apply(i, j));
   }

   public void update$mcD$sp(final int i, final int j, final double e) {
      this.update(i, j, BoxesRunTime.boxToDouble(e));
   }

   public void update$mcF$sp(final int i, final int j, final float e) {
      this.update(i, j, BoxesRunTime.boxToFloat(e));
   }

   public void update$mcI$sp(final int i, final int j, final int e) {
      this.update(i, j, BoxesRunTime.boxToInteger(e));
   }

   public void update$mcJ$sp(final int i, final int j, final long e) {
      this.update(i, j, BoxesRunTime.boxToLong(e));
   }

   public SliceMatrix repr$mcIID$sp() {
      return this.repr();
   }

   public SliceMatrix repr$mcIIF$sp() {
      return this.repr();
   }

   public SliceMatrix repr$mcIII$sp() {
      return this.repr();
   }

   public SliceMatrix repr$mcIIJ$sp() {
      return this.repr();
   }

   public Matrix copy$mcD$sp() {
      return this.copy();
   }

   public Matrix copy$mcF$sp() {
      return this.copy();
   }

   public Matrix copy$mcI$sp() {
      return this.copy();
   }

   public Matrix copy$mcJ$sp() {
      return this.copy();
   }

   public Vector flatten$mcD$sp(final View view) {
      return this.flatten(view);
   }

   public Vector flatten$mcF$sp(final View view) {
      return this.flatten(view);
   }

   public Vector flatten$mcI$sp(final View view) {
      return this.flatten(view);
   }

   public Vector flatten$mcJ$sp(final View view) {
      return this.flatten(view);
   }

   public boolean specInstance$() {
      return false;
   }

   public SliceMatrix(final Tensor tensor, final IndexedSeq slice1, final IndexedSeq slice2, final Semiring evidence$1, final ClassTag evidence$2) {
      this.tensor = tensor;
      this.slice1 = slice1;
      this.slice2 = slice2;
      this.evidence$1 = evidence$1;
      this.breeze$linalg$SliceMatrix$$evidence$2 = evidence$2;
      QuasiTensor.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      TensorLike.$init$(this);
      MatrixLike.$init$(this);
      Matrix.$init$(this);
   }
}
