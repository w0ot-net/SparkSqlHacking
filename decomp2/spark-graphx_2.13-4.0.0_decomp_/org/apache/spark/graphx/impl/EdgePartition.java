package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.Edge$;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.TripletFields;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function4;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r]c!\u0002\u0014(\u0001%\n\u0004\u0002\u0004$\u0001\t\u0003\u0005)Q!A!\u0002\u00139\u0005\u0002D'\u0001\t\u0003\u0005)Q!A!\u0002\u00139\u0005\u0002\u0004(\u0001\t\u0003\u0005)Q!A!\u0002\u0013y\u0005BCA\u0003\u0001\t\u0005\t\u0015!\u0003\u0002\b!Q\u00111\u0007\u0001\u0003\u0002\u0003\u0006I!a\u0002\t\u001d\u0005U\u0002\u0001\"A\u0001\u0006\u000b\u0005\t\u0015!\u0003\u00028!Q\u0011\u0011\b\u0001\u0003\u0002\u0003\u0006I!a\u000f\t\u0015\u0005\r\u0003A!A!\u0002\u0013\t)\u0005\u0003\u0006\u0002R\u0001\u0011\u0019\u0011)A\u0006\u0003'B!\"a\u0018\u0001\u0005\u0007\u0005\u000b1BA1\u0011\u001d\t\u0019\u0007\u0001C\u0001\u0003KBq!a\u0019\u0001\t\u0013\tI\tC\u0004\u0002\u0016\u0002!\t!a&\t\u000f\u0005=\u0006\u0001\"\u0001\u00022\"9\u0011Q\u0018\u0001\u0005\u0002\u0005}\u0006bBAf\u0001\u0011\u0005\u0011Q\u001a\u0005\b\u0003C\u0004A\u0011BAr\u0011\u001d\t\t\u0010\u0001C\u0005\u0003gDq!!?\u0001\t\u0013\tY\u0010C\u0004\u0003\u0002\u0001!\tAa\u0001\t\u000f\t=\u0001\u0001\"\u0001\u0003\u0012!9!Q\u0003\u0001\u0005\u0002\t]\u0001b\u0002B\r\u0001\u0011\u0005!1\u0004\u0005\b\u00053\u0001A\u0011\u0001B \u0011\u001d\u0011)\u0006\u0001C\u0001\u0005/BqAa\u001c\u0001\t\u0003\u0011\t\bC\u0004\u0003~\u0001!\tAa \t\u000f\t\u001d\u0005\u0001\"\u0001\u0003\n\"I!q\u0018\u0001C\u0002\u0013\u0005!\u0011\u0019\u0005\b\u0005\u0007\u0004\u0001\u0015!\u0003K\u0011\u001d\u0011)\r\u0001C\u0001\u0005\u0003DqAa2\u0001\t\u0003\u0011I\rC\u0004\u0003N\u0002!\tAa4\t\u0013\tm\u0007!%A\u0005\u0002\tu\u0007\"\u0003Bz\u0001E\u0005I\u0011\u0001Bo\u0011\u001d\u0011)\u0010\u0001C\u0001\u0005oDqaa\r\u0001\t\u0003\u0019)DA\u0007FI\u001e,\u0007+\u0019:uSRLwN\u001c\u0006\u0003Q%\nA![7qY*\u0011!fK\u0001\u0007OJ\f\u0007\u000f\u001b=\u000b\u00051j\u0013!B:qCJ\\'B\u0001\u00180\u0003\u0019\t\u0007/Y2iK*\t\u0001'A\u0002pe\u001e,BA\r*\u0002@M\u0019\u0001aM\u001d\u0011\u0005Q:T\"A\u001b\u000b\u0003Y\nQa]2bY\u0006L!\u0001O\u001b\u0003\r\u0005s\u0017PU3g!\tQ4I\u0004\u0002<\u0003:\u0011A\bQ\u0007\u0002{)\u0011ahP\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\ta'\u0003\u0002Ck\u00059\u0001/Y2lC\u001e,\u0017B\u0001#F\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0011U'A\u001cpe\u001e$\u0013\r]1dQ\u0016$3\u000f]1sW\u0012:'/\u00199iq\u0012JW\u000e\u001d7%\u000b\u0012<W\rU1si&$\u0018n\u001c8%I1|7-\u00197Te\u000eLEm\u001d\t\u0004i!S\u0015BA%6\u0005\u0015\t%O]1z!\t!4*\u0003\u0002Mk\t\u0019\u0011J\u001c;\u0002o=\u0014x\rJ1qC\u000eDW\rJ:qCJ\\Ge\u001a:ba\"DH%[7qY\u0012*EmZ3QCJ$\u0018\u000e^5p]\u0012\"Cn\\2bY\u0012\u001bH/\u00133t\u0003Az'o\u001a\u0013ba\u0006\u001c\u0007.\u001a\u0013ta\u0006\u00148\u000eJ4sCBD\u0007\u0010J5na2$S\tZ4f!\u0006\u0014H/\u001b;j_:$C\u0005Z1uCB\u0019A\u0007\u0013)\u0011\u0005E\u0013F\u0002\u0001\u0003\n'\u0002\u0001\u000b\u0011!AC\u0002Q\u0013!!\u0012#\u0012\u0005UC\u0006C\u0001\u001bW\u0013\t9VGA\u0004O_RD\u0017N\\4\u0011\u0005QJ\u0016B\u0001.6\u0005\r\te.\u001f\u0015\n%r{F-\u001b8tqv\u0004\"\u0001N/\n\u0005y+$aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t1bG\nt!\u0001N1\n\u0005\t,\u0014\u0001B\"iCJ\fD\u0001J\u001eAmE*1%\u001a4iO:\u0011AGZ\u0005\u0003OV\n1!\u00138uc\u0011!3\b\u0011\u001c2\u000b\rR7.\u001c7\u000f\u0005QZ\u0017B\u000176\u0003\u001d\u0011un\u001c7fC:\fD\u0001J\u001eAmE*1e\u001c9sc:\u0011A\u0007]\u0005\u0003cV\nAAQ=uKF\"Ae\u000f!7c\u0015\u0019C/^<w\u001d\t!T/\u0003\u0002wk\u0005!Aj\u001c8hc\u0011!3\b\u0011\u001c2\u000b\rJ(\u0010`>\u000f\u0005QR\u0018BA>6\u0003\u00151En\\1uc\u0011!3\b\u0011\u001c2\u000f\rrx0a\u0001\u0002\u00029\u0011Ag`\u0005\u0004\u0003\u0003)\u0014A\u0002#pk\ndW-\r\u0003%w\u00013\u0014!B5oI\u0016D\bcBA\u0005\u0003'\t9BS\u0007\u0003\u0003\u0017QA!!\u0004\u0002\u0010\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0007\u0005E\u0011&\u0001\u0003vi&d\u0017\u0002BA\u000b\u0003\u0017\u0011Qd\u0012:ba\"D\u0006K]5nSRLg/Z&fs>\u0003XM\u001c%bg\"l\u0015\r\u001d\t\u0005\u00033\tiC\u0004\u0003\u0002\u001c\u0005-b\u0002BA\u000f\u0003SqA!a\b\u0002(9!\u0011\u0011EA\u0013\u001d\ra\u00141E\u0005\u0002a%\u0011afL\u0005\u0003Y5J!AK\u0016\n\u0005\tK\u0013\u0002BA\u0018\u0003c\u0011\u0001BV3si\u0016D\u0018\n\u001a\u0006\u0003\u0005&\nAb\u001a7pE\u0006d'\u0007\\8dC2\f\u0001h\u001c:hI\u0005\u0004\u0018m\u00195fIM\u0004\u0018M]6%OJ\f\u0007\u000f\u001b=%S6\u0004H\u000eJ#eO\u0016\u0004\u0016M\u001d;ji&|g\u000e\n\u0013m_\u000e\fGNM4m_\n\fG\u000e\u0005\u00035\u0011\u0006]\u0011a\u0003<feR,\u00070\u0011;ueN\u0004B\u0001\u000e%\u0002>A\u0019\u0011+a\u0010\u0005\r\u0005\u0005\u0003A1\u0001U\u0005\t1F)A\u0005bGRLg/Z*fiB)A'a\u0012\u0002L%\u0019\u0011\u0011J\u001b\u0003\r=\u0003H/[8o!\u0011\tI\"!\u0014\n\t\u0005=\u0013\u0011\u0007\u0002\n-\u0016\u0014H/\u001a=TKR\f!\"\u001a<jI\u0016t7-\u001a\u00132!\u0015\t)&a\u0017Q\u001b\t\t9FC\u0002\u0002ZU\nqA]3gY\u0016\u001cG/\u0003\u0003\u0002^\u0005]#\u0001C\"mCN\u001cH+Y4\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u0004\u0002V\u0005m\u0013QH\u0001\u0007y%t\u0017\u000e\u001e \u0015%\u0005\u001d\u0014\u0011OA;\u0003s\ni(a \u0002\u0002\u0006\u0015\u0015q\u0011\u000b\u0007\u0003S\ni'a\u001c\u0011\r\u0005-\u0004\u0001UA\u001f\u001b\u00059\u0003bBA)\u0017\u0001\u000f\u00111\u000b\u0005\b\u0003?Z\u00019AA1\u0011\u0019\t\u0019h\u0003a\u0001\u000f\u0006YAn\\2bYN\u00138-\u00133t\u0011\u0019\t9h\u0003a\u0001\u000f\u0006YAn\\2bY\u0012\u001bH/\u00133t\u0011\u0019\tYh\u0003a\u0001\u001f\u0006!A-\u0019;b\u0011\u001d\t)a\u0003a\u0001\u0003\u000fAq!a\r\f\u0001\u0004\t9\u0001C\u0004\u0002\u0004.\u0001\r!a\u000e\u0002\u00191|7-\u001973O2|'-\u00197\t\u000f\u0005e2\u00021\u0001\u0002<!9\u00111I\u0006A\u0002\u0005\u0015CCAAF)\u0019\tI'!$\u0002\u0012\"I\u0011q\u0012\u0007\u0002\u0002\u0003\u000f\u00111K\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\"CAJ\u0019\u0005\u0005\t9AA1\u0003))g/\u001b3f]\u000e,G\u0005N\u0001\to&$\b\u000eR1uCV!\u0011\u0011TAQ)\u0011\tY*a+\u0015\t\u0005u\u0015Q\u0015\t\b\u0003W\u0002\u0011qTA\u001f!\r\t\u0016\u0011\u0015\u0003\u0007\u0003Gk!\u0019\u0001+\u0003\u0007\u0015#%\u0007C\u0005\u0002(6\t\t\u0011q\u0001\u0002*\u0006QQM^5eK:\u001cW\rJ\u001b\u0011\r\u0005U\u00131LAP\u0011\u001d\tY(\u0004a\u0001\u0003[\u0003B\u0001\u000e%\u0002 \u0006iq/\u001b;i\u0003\u000e$\u0018N^3TKR$B!!\u001b\u00024\"9\u0011Q\u0017\bA\u0002\u0005]\u0016\u0001B5uKJ\u0004RAOA]\u0003/I1!a/F\u0005!IE/\u001a:bi>\u0014\u0018AD;qI\u0006$XMV3si&\u001cWm\u001d\u000b\u0005\u0003S\n\t\rC\u0004\u00026>\u0001\r!a1\u0011\u000bi\nI,!2\u0011\u000fQ\n9-a\u0006\u0002>%\u0019\u0011\u0011Z\u001b\u0003\rQ+\b\u000f\\33\u0003]9\u0018\u000e\u001e5pkR4VM\u001d;fq\u0006#HO]5ckR,7/\u0006\u0003\u0002P\u0006]GCAAi)\u0011\t\u0019.a7\u0011\r\u0005-\u0004\u0001UAk!\r\t\u0016q\u001b\u0003\u0007\u00033\u0004\"\u0019\u0001+\u0003\u0007Y#%\u0007C\u0005\u0002^B\t\t\u0011q\u0001\u0002`\u0006QQM^5eK:\u001cW\r\n\u001c\u0011\r\u0005U\u00131LAk\u0003\u0019\u0019(oY%egR!\u0011qCAs\u0011\u0019\t9/\u0005a\u0001\u0015\u0006\u0019\u0001o\\:)\u0007E\tY\u000fE\u00025\u0003[L1!a<6\u0005\u0019Ig\u000e\\5oK\u00061Am\u001d;JIN$B!a\u0006\u0002v\"1\u0011q\u001d\nA\u0002)C3AEAv\u0003\u0015\tG\u000f\u001e:t)\r\u0001\u0016Q \u0005\u0007\u0003O\u001c\u0002\u0019\u0001&)\u0007M\tY/\u0001\u0005jg\u0006\u001bG/\u001b<f)\u0011\u0011)Aa\u0003\u0011\u0007Q\u00129!C\u0002\u0003\nU\u0012qAQ8pY\u0016\fg\u000eC\u0004\u0003\u000eQ\u0001\r!a\u0006\u0002\u0007YLG-\u0001\u0006ok6\f5\r^5wKN,\"Aa\u0005\u0011\tQ\n9ES\u0001\be\u00164XM]:f+\t\tI'A\u0002nCB,BA!\b\u0003&Q!!q\u0004B\u0017)\u0011\u0011\tCa\n\u0011\u000f\u0005-\u0004Aa\t\u0002>A\u0019\u0011K!\n\u0005\r\u0005\rvC1\u0001U\u0011%\u0011IcFA\u0001\u0002\b\u0011Y#\u0001\u0006fm&$WM\\2fI]\u0002b!!\u0016\u0002\\\t\r\u0002b\u0002B\u0018/\u0001\u0007!\u0011G\u0001\u0002MB9AGa\r\u00038\t\r\u0012b\u0001B\u001bk\tIa)\u001e8di&|g.\r\t\u0006\u0005s\u0011Y\u0004U\u0007\u0002S%\u0019!QH\u0015\u0003\t\u0015#w-Z\u000b\u0005\u0005\u0003\u0012I\u0005\u0006\u0003\u0003D\tEC\u0003\u0002B#\u0005\u0017\u0002r!a\u001b\u0001\u0005\u000f\ni\u0004E\u0002R\u0005\u0013\"a!a)\u0019\u0005\u0004!\u0006\"\u0003B'1\u0005\u0005\t9\u0001B(\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0007\u0003+\nYFa\u0012\t\u000f\u0005U\u0006\u00041\u0001\u0003TA)!(!/\u0003H\u00051a-\u001b7uKJ$b!!\u001b\u0003Z\t\u0015\u0004b\u0002B.3\u0001\u0007!QL\u0001\u0006KB\u0014X\r\u001a\t\bi\tM\"q\fB\u0003!\u001d\u0011ID!\u0019\u0002>AK1Aa\u0019*\u0005-)EmZ3Ue&\u0004H.\u001a;\t\u000f\t\u001d\u0014\u00041\u0001\u0003j\u0005)a\u000f\u001d:fIBIAGa\u001b\u0002\u0018\u0005u\"QA\u0005\u0004\u0005[*$!\u0003$v]\u000e$\u0018n\u001c83\u0003\u001d1wN]3bG\"$BAa\u001d\u0003zA\u0019AG!\u001e\n\u0007\t]TG\u0001\u0003V]&$\bb\u0002B\u00185\u0001\u0007!1\u0010\t\bi\tM\"q\u0007B:\u0003)9'o\\;q\u000b\u0012<Wm\u001d\u000b\u0005\u0003S\u0012\t\tC\u0004\u0003\u0004n\u0001\rA!\"\u0002\u000b5,'oZ3\u0011\rQ\u0012Y\u0007\u0015)Q\u0003%IgN\\3s\u0015>Lg.\u0006\u0004\u0003\f\n\u0005&Q\u0013\u000b\u0005\u0005\u001b\u0013\t\f\u0006\u0003\u0003\u0010\n%FC\u0002BI\u00053\u0013\u0019\u000bE\u0004\u0002l\u0001\u0011\u0019*!\u0010\u0011\u0007E\u0013)\n\u0002\u0004\u0003\u0018r\u0011\r\u0001\u0016\u0002\u0004\u000b\u0012\u001b\u0004\"\u0003BN9\u0005\u0005\t9\u0001BO\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0007\u0003+\nYFa(\u0011\u0007E\u0013\t\u000b\u0002\u0004\u0002$r\u0011\r\u0001\u0016\u0005\n\u0005Kc\u0012\u0011!a\u0002\u0005O\u000b1\"\u001a<jI\u0016t7-\u001a\u00132aA1\u0011QKA.\u0005'CqAa\f\u001d\u0001\u0004\u0011Y\u000b\u0005\u00075\u0005[\u000b9\"a\u0006Q\u0005?\u0013\u0019*C\u0002\u00030V\u0012\u0011BR;oGRLwN\u001c\u001b\t\u000f\tMF\u00041\u0001\u00036\u0006)q\u000e\u001e5feB\"!q\u0017B^!\u001d\tY\u0007\u0001BP\u0005s\u00032!\u0015B^\t-\u0011iL!-\u0002\u0002\u0003\u0005)\u0011\u0001+\u0003\u0007}#\u0013'\u0001\u0003tSj,W#\u0001&\u0002\u000bML'0\u001a\u0011\u0002\u0013%tG-\u001a=TSj,\u0017\u0001C5uKJ\fGo\u001c:\u0016\u0005\t-\u0007#\u0002\u001e\u0002:\n]\u0012a\u0004;sSBdW\r^%uKJ\fGo\u001c:\u0015\r\tE'1\u001bBl!\u0015Q\u0014\u0011\u0018B0\u0011%\u0011).\tI\u0001\u0002\u0004\u0011)!\u0001\u0006j]\u000edW\u000fZ3Te\u000eD\u0011B!7\"!\u0003\u0005\rA!\u0002\u0002\u0015%t7\r\\;eK\u0012\u001bH/A\rue&\u0004H.\u001a;Ji\u0016\u0014\u0018\r^8sI\u0011,g-Y;mi\u0012\nTC\u0001BpU\u0011\u0011)A!9,\u0005\t\r\b\u0003\u0002Bs\u0005_l!Aa:\u000b\t\t%(1^\u0001\nk:\u001c\u0007.Z2lK\u0012T1A!<6\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005c\u00149OA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0011\u0004\u001e:ja2,G/\u0013;fe\u0006$xN\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005I\u0012mZ4sK\u001e\fG/Z'fgN\fw-Z:FI\u001e,7kY1o+\u0011\u0011Ipa\u0001\u0015\u0015\tm8QBB\r\u0007?\u0019I\u0003\u0006\u0003\u0003~\u000e\u001d\u0001#\u0002\u001e\u0002:\n}\bc\u0002\u001b\u0002H\u0006]1\u0011\u0001\t\u0004#\u000e\rAABB\u0003I\t\u0007AKA\u0001B\u0011%\u0019I\u0001JA\u0001\u0002\b\u0019Y!A\u0006fm&$WM\\2fIE\n\u0004CBA+\u00037\u001a\t\u0001C\u0004\u0004\u0010\u0011\u0002\ra!\u0005\u0002\u000fM,g\u000eZ'tOB9AGa\r\u0004\u0014\tM\u0004#\u0003B\u001d\u0007+\ti\u0004UB\u0001\u0013\r\u00199\"\u000b\u0002\f\u000b\u0012<WmQ8oi\u0016DH\u000fC\u0004\u0004\u001c\u0011\u0002\ra!\b\u0002\u00115,'oZ3Ng\u001e\u0004\u0012\u0002\u000eB6\u0007\u0003\u0019\ta!\u0001\t\u000f\r\u0005B\u00051\u0001\u0004$\u0005iAO]5qY\u0016$h)[3mIN\u0004BA!\u000f\u0004&%\u00191qE\u0015\u0003\u001bQ\u0013\u0018\u000e\u001d7fi\u001aKW\r\u001c3t\u0011\u001d\u0019Y\u0003\na\u0001\u0007[\t!\"Y2uSZ,g.Z:t!\u0011\tYga\f\n\u0007\rErE\u0001\bFI\u001e,\u0017i\u0019;jm\u0016tWm]:\u00025\u0005<wM]3hCR,W*Z:tC\u001e,7/\u00138eKb\u001c6-\u00198\u0016\t\r]2\u0011\t\u000b\u000b\u0007s\u0019Iea\u0014\u0004T\rUC\u0003BB\u001e\u0007\u0007\u0002RAOA]\u0007{\u0001r\u0001NAd\u0003/\u0019y\u0004E\u0002R\u0007\u0003\"aa!\u0002&\u0005\u0004!\u0006\"CB#K\u0005\u0005\t9AB$\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\r\u0005U\u00131LB \u0011\u001d\u0019y!\na\u0001\u0007\u0017\u0002r\u0001\u000eB\u001a\u0007\u001b\u0012\u0019\bE\u0005\u0003:\rU\u0011Q\b)\u0004@!911D\u0013A\u0002\rE\u0003#\u0003\u001b\u0003l\r}2qHB \u0011\u001d\u0019\t#\na\u0001\u0007GAqaa\u000b&\u0001\u0004\u0019i\u0003"
)
public class EdgePartition implements Serializable {
   public final int[] org$apache$spark$graphx$impl$EdgePartition$$localSrcIds;
   public final int[] org$apache$spark$graphx$impl$EdgePartition$$localDstIds;
   public final Object org$apache$spark$graphx$impl$EdgePartition$$data;
   public final GraphXPrimitiveKeyOpenHashMap org$apache$spark$graphx$impl$EdgePartition$$index;
   public final GraphXPrimitiveKeyOpenHashMap org$apache$spark$graphx$impl$EdgePartition$$global2local;
   public final long[] org$apache$spark$graphx$impl$EdgePartition$$local2global;
   public final Object org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs;
   public final Option org$apache$spark$graphx$impl$EdgePartition$$activeSet;
   public final ClassTag org$apache$spark$graphx$impl$EdgePartition$$evidence$1;
   public final ClassTag org$apache$spark$graphx$impl$EdgePartition$$evidence$2;
   private final int size;

   public EdgePartition withData(final Object data, final ClassTag evidence$5) {
      return new EdgePartition(this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds, this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds, data, this.org$apache$spark$graphx$impl$EdgePartition$$index, this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, evidence$5, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);
   }

   public EdgePartition withActiveSet(final Iterator iter) {
      OpenHashSet activeSet = new OpenHashSet.mcJ.sp(.MODULE$.apply(Long.TYPE));

      while(iter.hasNext()) {
         activeSet.add$mcJ$sp(BoxesRunTime.unboxToLong(iter.next()));
      }

      return new EdgePartition(this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds, this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds, this.org$apache$spark$graphx$impl$EdgePartition$$data, this.org$apache$spark$graphx$impl$EdgePartition$$index, this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, new Some(activeSet), this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);
   }

   public EdgePartition updateVertices(final Iterator iter) {
      Object newVertexAttrs = this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));
      System.arraycopy(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, 0, newVertexAttrs, 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));

      while(iter.hasNext()) {
         Tuple2 kv = (Tuple2)iter.next();
         scala.runtime.ScalaRunTime..MODULE$.array_update(newVertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$global2local.apply$mcJI$sp(kv._1$mcJ$sp()), kv._2());
      }

      return new EdgePartition(this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds, this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds, this.org$apache$spark$graphx$impl$EdgePartition$$data, this.org$apache$spark$graphx$impl$EdgePartition$$index, this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, newVertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);
   }

   public EdgePartition withoutVertexAttributes(final ClassTag evidence$6) {
      Object newVertexAttrs = evidence$6.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));
      return new EdgePartition(this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds, this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds, this.org$apache$spark$graphx$impl$EdgePartition$$data, this.org$apache$spark$graphx$impl$EdgePartition$$index, this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, newVertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, evidence$6);
   }

   public long org$apache$spark$graphx$impl$EdgePartition$$srcIds(final int pos) {
      return this.org$apache$spark$graphx$impl$EdgePartition$$local2global[this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[pos]];
   }

   public long org$apache$spark$graphx$impl$EdgePartition$$dstIds(final int pos) {
      return this.org$apache$spark$graphx$impl$EdgePartition$$local2global[this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[pos]];
   }

   public Object attrs(final int pos) {
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$data, pos);
   }

   public boolean isActive(final long vid) {
      return ((OpenHashSet)this.org$apache$spark$graphx$impl$EdgePartition$$activeSet.get()).contains$mcJ$sp(vid);
   }

   public Option numActives() {
      return this.org$apache$spark$graphx$impl$EdgePartition$$activeSet.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$numActives$1(x$1)));
   }

   public EdgePartition reverse() {
      ExistingEdgePartitionBuilder builder = new ExistingEdgePartitionBuilder(this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, this.size(), this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);

      for(int i = 0; i < this.size(); ++i) {
         int localSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[i];
         int localDstId = this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[i];
         long srcId = this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localSrcId];
         long dstId = this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localDstId];
         Object attr = scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$data, i);
         builder.add(dstId, srcId, localDstId, localSrcId, attr);
      }

      return builder.toEdgePartition();
   }

   public EdgePartition map(final Function1 f, final ClassTag evidence$7) {
      Object newData = evidence$7.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$data));
      Edge edge = new Edge(Edge$.MODULE$.$lessinit$greater$default$1(), Edge$.MODULE$.$lessinit$greater$default$2(), Edge$.MODULE$.$lessinit$greater$default$3());
      int size = scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$data);

      for(int i = 0; i < size; ++i) {
         edge.srcId_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$srcIds(i));
         edge.dstId_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$dstIds(i));
         edge.attr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$data, i));
         scala.runtime.ScalaRunTime..MODULE$.array_update(newData, i, f.apply(edge));
      }

      return this.withData(newData, evidence$7);
   }

   public EdgePartition map(final Iterator iter, final ClassTag evidence$8) {
      Object newData = evidence$8.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$data));

      int i;
      for(i = 0; iter.hasNext(); ++i) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(newData, i, iter.next());
      }

      scala.Predef..MODULE$.assert(scala.runtime.ScalaRunTime..MODULE$.array_length(newData) == i);
      return this.withData(newData, evidence$8);
   }

   public EdgePartition filter(final Function1 epred, final Function2 vpred) {
      ExistingEdgePartitionBuilder builder = new ExistingEdgePartitionBuilder(this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, ExistingEdgePartitionBuilder$.MODULE$.$lessinit$greater$default$5(), this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);

      for(int i = 0; i < this.size(); ++i) {
         int localSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[i];
         int localDstId = this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[i];
         EdgeTriplet et = new EdgeTriplet();
         et.srcId_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localSrcId]);
         et.dstId_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localDstId]);
         et.srcAttr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localSrcId));
         et.dstAttr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localDstId));
         et.attr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$data, i));
         if (BoxesRunTime.unboxToBoolean(vpred.apply(BoxesRunTime.boxToLong(et.srcId()), et.srcAttr())) && BoxesRunTime.unboxToBoolean(vpred.apply(BoxesRunTime.boxToLong(et.dstId()), et.dstAttr())) && BoxesRunTime.unboxToBoolean(epred.apply(et))) {
            builder.add(et.srcId(), et.dstId(), localSrcId, localDstId, et.attr());
         }
      }

      return builder.toEdgePartition();
   }

   public void foreach(final Function1 f) {
      this.iterator().foreach(f);
   }

   public EdgePartition groupEdges(final Function2 merge) {
      ExistingEdgePartitionBuilder builder = new ExistingEdgePartitionBuilder(this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, ExistingEdgePartitionBuilder$.MODULE$.$lessinit$greater$default$5(), this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);
      long currSrcId = BoxesRunTime.unboxToLong((Object)null);
      long currDstId = BoxesRunTime.unboxToLong((Object)null);
      int currLocalSrcId = -1;
      int currLocalDstId = -1;
      Object currAttr = null;

      for(int i = 0; i < this.size(); ++i) {
         if (i > 0 && currSrcId == this.org$apache$spark$graphx$impl$EdgePartition$$srcIds(i) && currDstId == this.org$apache$spark$graphx$impl$EdgePartition$$dstIds(i)) {
            currAttr = merge.apply(currAttr, scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$data, i));
         } else {
            if (i > 0) {
               builder.add(currSrcId, currDstId, currLocalSrcId, currLocalDstId, currAttr);
            }

            currSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$srcIds(i);
            currDstId = this.org$apache$spark$graphx$impl$EdgePartition$$dstIds(i);
            currLocalSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[i];
            currLocalDstId = this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[i];
            currAttr = scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$data, i);
         }
      }

      if (this.size() > 0) {
         builder.add(currSrcId, currDstId, currLocalSrcId, currLocalDstId, currAttr);
      }

      return builder.toEdgePartition();
   }

   public EdgePartition innerJoin(final EdgePartition other, final Function4 f, final ClassTag evidence$9, final ClassTag evidence$10) {
      ExistingEdgePartitionBuilder builder = new ExistingEdgePartitionBuilder(this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, ExistingEdgePartitionBuilder$.MODULE$.$lessinit$greater$default$5(), evidence$10, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);
      int i = 0;

      for(int j = 0; i < this.size() && j < other.size(); ++i) {
         long srcId = this.org$apache$spark$graphx$impl$EdgePartition$$srcIds(i);

         long dstId;
         for(dstId = this.org$apache$spark$graphx$impl$EdgePartition$$dstIds(i); j < other.size() && other.org$apache$spark$graphx$impl$EdgePartition$$srcIds(j) < srcId; ++j) {
         }

         if (j < other.size() && other.org$apache$spark$graphx$impl$EdgePartition$$srcIds(j) == srcId) {
            while(j < other.size() && other.org$apache$spark$graphx$impl$EdgePartition$$srcIds(j) == srcId && other.org$apache$spark$graphx$impl$EdgePartition$$dstIds(j) < dstId) {
               ++j;
            }

            if (j < other.size() && other.org$apache$spark$graphx$impl$EdgePartition$$srcIds(j) == srcId && other.org$apache$spark$graphx$impl$EdgePartition$$dstIds(j) == dstId) {
               builder.add(srcId, dstId, this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[i], this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[i], f.apply(BoxesRunTime.boxToLong(srcId), BoxesRunTime.boxToLong(dstId), scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$data, i), other.attrs(j)));
            }
         }
      }

      return builder.toEdgePartition();
   }

   public int size() {
      return this.size;
   }

   public int indexSize() {
      return this.org$apache$spark$graphx$impl$EdgePartition$$index.size();
   }

   public Iterator iterator() {
      return new Iterator() {
         private final Edge edge;
         private int pos;
         // $FF: synthetic field
         private final EdgePartition $outer;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         public boolean hasNext() {
            return this.pos < this.$outer.size();
         }

         public Edge next() {
            this.edge.srcId_$eq(this.$outer.org$apache$spark$graphx$impl$EdgePartition$$srcIds(this.pos));
            this.edge.dstId_$eq(this.$outer.org$apache$spark$graphx$impl$EdgePartition$$dstIds(this.pos));
            this.edge.attr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.$outer.org$apache$spark$graphx$impl$EdgePartition$$data, this.pos));
            ++this.pos;
            return this.edge;
         }

         public {
            if (EdgePartition.this == null) {
               throw null;
            } else {
               this.$outer = EdgePartition.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.edge = new Edge(Edge$.MODULE$.$lessinit$greater$default$1(), Edge$.MODULE$.$lessinit$greater$default$2(), Edge$.MODULE$.$lessinit$greater$default$3());
               this.pos = 0;
            }
         }
      };
   }

   public Iterator tripletIterator(final boolean includeSrc, final boolean includeDst) {
      return new Iterator(includeSrc, includeDst) {
         private int pos;
         // $FF: synthetic field
         private final EdgePartition $outer;
         private final boolean includeSrc$1;
         private final boolean includeDst$1;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         public boolean hasNext() {
            return this.pos < this.$outer.size();
         }

         public EdgeTriplet next() {
            EdgeTriplet triplet = new EdgeTriplet();
            int localSrcId = this.$outer.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[this.pos];
            int localDstId = this.$outer.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[this.pos];
            triplet.srcId_$eq(this.$outer.org$apache$spark$graphx$impl$EdgePartition$$local2global[localSrcId]);
            triplet.dstId_$eq(this.$outer.org$apache$spark$graphx$impl$EdgePartition$$local2global[localDstId]);
            if (this.includeSrc$1) {
               triplet.srcAttr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.$outer.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localSrcId));
            }

            if (this.includeDst$1) {
               triplet.dstAttr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.$outer.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localDstId));
            }

            triplet.attr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.$outer.org$apache$spark$graphx$impl$EdgePartition$$data, this.pos));
            ++this.pos;
            return triplet;
         }

         public {
            if (EdgePartition.this == null) {
               throw null;
            } else {
               this.$outer = EdgePartition.this;
               this.includeSrc$1 = includeSrc$1;
               this.includeDst$1 = includeDst$1;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               this.pos = 0;
            }
         }
      };
   }

   public boolean tripletIterator$default$1() {
      return true;
   }

   public boolean tripletIterator$default$2() {
      return true;
   }

   public Iterator aggregateMessagesEdgeScan(final Function1 sendMsg, final Function2 mergeMsg, final TripletFields tripletFields, final EdgeActiveness activeness, final ClassTag evidence$11) {
      Object aggregates = evidence$11.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));
      BitSet bitset = new BitSet(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));
      AggregatingEdgeContext ctx = new AggregatingEdgeContext(mergeMsg, aggregates, bitset);
      int i = 0;

      while(true) {
         if (i >= this.size()) {
            return bitset.iterator().map((localId) -> $anonfun$aggregateMessagesEdgeScan$1(this, aggregates, BoxesRunTime.unboxToInt(localId)));
         }

         int localSrcId;
         long srcId;
         int localDstId;
         long dstId;
         boolean var10000;
         label98: {
            label106: {
               localSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[i];
               srcId = this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localSrcId];
               localDstId = this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[i];
               dstId = this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localDstId];
               EdgeActiveness var17 = EdgeActiveness.Neither;
               if (activeness == null) {
                  if (var17 == null) {
                     break label106;
                  }
               } else if (activeness.equals(var17)) {
                  break label106;
               }

               label107: {
                  EdgeActiveness var18 = EdgeActiveness.SrcOnly;
                  if (activeness == null) {
                     if (var18 == null) {
                        break label107;
                     }
                  } else if (activeness.equals(var18)) {
                     break label107;
                  }

                  label108: {
                     EdgeActiveness var19 = EdgeActiveness.DstOnly;
                     if (activeness == null) {
                        if (var19 == null) {
                           break label108;
                        }
                     } else if (activeness.equals(var19)) {
                        break label108;
                     }

                     label109: {
                        EdgeActiveness var20 = EdgeActiveness.Both;
                        if (activeness == null) {
                           if (var20 != null) {
                              break label109;
                           }
                        } else if (!activeness.equals(var20)) {
                           break label109;
                        }

                        if (this.isActive(srcId) && this.isActive(dstId)) {
                           var10000 = true;
                           break label98;
                        }

                        var10000 = false;
                        break label98;
                     }

                     EdgeActiveness var21 = EdgeActiveness.Either;
                     if (activeness == null) {
                        if (var21 != null) {
                           break;
                        }
                     } else if (!activeness.equals(var21)) {
                        break;
                     }

                     if (!this.isActive(srcId) && !this.isActive(dstId)) {
                        var10000 = false;
                        break label98;
                     }

                     var10000 = true;
                     break label98;
                  }

                  var10000 = this.isActive(dstId);
                  break label98;
               }

               var10000 = this.isActive(srcId);
               break label98;
            }

            var10000 = true;
         }

         boolean edgeIsActive = var10000;
         if (edgeIsActive) {
            Object srcAttr = tripletFields.useSrc ? scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localSrcId) : null;
            Object dstAttr = tripletFields.useDst ? scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localDstId) : null;
            ctx.set(srcId, dstId, localSrcId, localDstId, srcAttr, dstAttr, scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$data, i));
            sendMsg.apply(ctx);
         } else {
            BoxedUnit var24 = BoxedUnit.UNIT;
         }

         ++i;
      }

      throw new Exception("unreachable");
   }

   public Iterator aggregateMessagesIndexScan(final Function1 sendMsg, final Function2 mergeMsg, final TripletFields tripletFields, final EdgeActiveness activeness, final ClassTag evidence$12) {
      Object aggregates = evidence$12.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));
      BitSet bitset = new BitSet(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));
      AggregatingEdgeContext ctx = new AggregatingEdgeContext(mergeMsg, aggregates, bitset);
      this.org$apache$spark$graphx$impl$EdgePartition$$index.iterator().foreach((cluster) -> {
         $anonfun$aggregateMessagesIndexScan$1(this, activeness, tripletFields, ctx, sendMsg, cluster);
         return BoxedUnit.UNIT;
      });
      return bitset.iterator().map((localId) -> $anonfun$aggregateMessagesIndexScan$2(this, aggregates, BoxesRunTime.unboxToInt(localId)));
   }

   public EdgePartition withActiveSet$mcZ$sp(final Iterator iter) {
      return this.withActiveSet(iter);
   }

   public EdgePartition withActiveSet$mcB$sp(final Iterator iter) {
      return this.withActiveSet(iter);
   }

   public EdgePartition withActiveSet$mcC$sp(final Iterator iter) {
      return this.withActiveSet(iter);
   }

   public EdgePartition withActiveSet$mcD$sp(final Iterator iter) {
      return this.withActiveSet(iter);
   }

   public EdgePartition withActiveSet$mcF$sp(final Iterator iter) {
      return this.withActiveSet(iter);
   }

   public EdgePartition withActiveSet$mcI$sp(final Iterator iter) {
      return this.withActiveSet(iter);
   }

   public EdgePartition withActiveSet$mcJ$sp(final Iterator iter) {
      return this.withActiveSet(iter);
   }

   public EdgePartition updateVertices$mcZ$sp(final Iterator iter) {
      return this.updateVertices(iter);
   }

   public EdgePartition updateVertices$mcB$sp(final Iterator iter) {
      return this.updateVertices(iter);
   }

   public EdgePartition updateVertices$mcC$sp(final Iterator iter) {
      return this.updateVertices(iter);
   }

   public EdgePartition updateVertices$mcD$sp(final Iterator iter) {
      return this.updateVertices(iter);
   }

   public EdgePartition updateVertices$mcF$sp(final Iterator iter) {
      return this.updateVertices(iter);
   }

   public EdgePartition updateVertices$mcI$sp(final Iterator iter) {
      return this.updateVertices(iter);
   }

   public EdgePartition updateVertices$mcJ$sp(final Iterator iter) {
      return this.updateVertices(iter);
   }

   public EdgePartition withoutVertexAttributes$mcZ$sp(final ClassTag evidence$6) {
      return this.withoutVertexAttributes(evidence$6);
   }

   public EdgePartition withoutVertexAttributes$mcB$sp(final ClassTag evidence$6) {
      return this.withoutVertexAttributes(evidence$6);
   }

   public EdgePartition withoutVertexAttributes$mcC$sp(final ClassTag evidence$6) {
      return this.withoutVertexAttributes(evidence$6);
   }

   public EdgePartition withoutVertexAttributes$mcD$sp(final ClassTag evidence$6) {
      return this.withoutVertexAttributes(evidence$6);
   }

   public EdgePartition withoutVertexAttributes$mcF$sp(final ClassTag evidence$6) {
      return this.withoutVertexAttributes(evidence$6);
   }

   public EdgePartition withoutVertexAttributes$mcI$sp(final ClassTag evidence$6) {
      return this.withoutVertexAttributes(evidence$6);
   }

   public EdgePartition withoutVertexAttributes$mcJ$sp(final ClassTag evidence$6) {
      return this.withoutVertexAttributes(evidence$6);
   }

   public boolean attrs$mcZ$sp(final int pos) {
      return BoxesRunTime.unboxToBoolean(this.attrs(pos));
   }

   public byte attrs$mcB$sp(final int pos) {
      return BoxesRunTime.unboxToByte(this.attrs(pos));
   }

   public char attrs$mcC$sp(final int pos) {
      return BoxesRunTime.unboxToChar(this.attrs(pos));
   }

   public double attrs$mcD$sp(final int pos) {
      return BoxesRunTime.unboxToDouble(this.attrs(pos));
   }

   public float attrs$mcF$sp(final int pos) {
      return BoxesRunTime.unboxToFloat(this.attrs(pos));
   }

   public int attrs$mcI$sp(final int pos) {
      return BoxesRunTime.unboxToInt(this.attrs(pos));
   }

   public long attrs$mcJ$sp(final int pos) {
      return BoxesRunTime.unboxToLong(this.attrs(pos));
   }

   public EdgePartition reverse$mcZ$sp() {
      return this.reverse();
   }

   public EdgePartition reverse$mcB$sp() {
      return this.reverse();
   }

   public EdgePartition reverse$mcC$sp() {
      return this.reverse();
   }

   public EdgePartition reverse$mcD$sp() {
      return this.reverse();
   }

   public EdgePartition reverse$mcF$sp() {
      return this.reverse();
   }

   public EdgePartition reverse$mcI$sp() {
      return this.reverse();
   }

   public EdgePartition reverse$mcJ$sp() {
      return this.reverse();
   }

   public EdgePartition map$mcZ$sp(final Function1 f, final ClassTag evidence$7) {
      return this.map(f, evidence$7);
   }

   public EdgePartition map$mcB$sp(final Function1 f, final ClassTag evidence$7) {
      return this.map(f, evidence$7);
   }

   public EdgePartition map$mcC$sp(final Function1 f, final ClassTag evidence$7) {
      return this.map(f, evidence$7);
   }

   public EdgePartition map$mcD$sp(final Function1 f, final ClassTag evidence$7) {
      return this.map(f, evidence$7);
   }

   public EdgePartition map$mcF$sp(final Function1 f, final ClassTag evidence$7) {
      return this.map(f, evidence$7);
   }

   public EdgePartition map$mcI$sp(final Function1 f, final ClassTag evidence$7) {
      return this.map(f, evidence$7);
   }

   public EdgePartition map$mcJ$sp(final Function1 f, final ClassTag evidence$7) {
      return this.map(f, evidence$7);
   }

   public EdgePartition filter$mcZ$sp(final Function1 epred, final Function2 vpred) {
      return this.filter(epred, vpred);
   }

   public EdgePartition filter$mcB$sp(final Function1 epred, final Function2 vpred) {
      return this.filter(epred, vpred);
   }

   public EdgePartition filter$mcC$sp(final Function1 epred, final Function2 vpred) {
      return this.filter(epred, vpred);
   }

   public EdgePartition filter$mcD$sp(final Function1 epred, final Function2 vpred) {
      return this.filter(epred, vpred);
   }

   public EdgePartition filter$mcF$sp(final Function1 epred, final Function2 vpred) {
      return this.filter(epred, vpred);
   }

   public EdgePartition filter$mcI$sp(final Function1 epred, final Function2 vpred) {
      return this.filter(epred, vpred);
   }

   public EdgePartition filter$mcJ$sp(final Function1 epred, final Function2 vpred) {
      return this.filter(epred, vpred);
   }

   public void foreach$mcZ$sp(final Function1 f) {
      this.foreach(f);
   }

   public void foreach$mcB$sp(final Function1 f) {
      this.foreach(f);
   }

   public void foreach$mcC$sp(final Function1 f) {
      this.foreach(f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      this.foreach(f);
   }

   public void foreach$mcF$sp(final Function1 f) {
      this.foreach(f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      this.foreach(f);
   }

   public void foreach$mcJ$sp(final Function1 f) {
      this.foreach(f);
   }

   public EdgePartition groupEdges$mcZ$sp(final Function2 merge) {
      return this.groupEdges(merge);
   }

   public EdgePartition groupEdges$mcB$sp(final Function2 merge) {
      return this.groupEdges(merge);
   }

   public EdgePartition groupEdges$mcC$sp(final Function2 merge) {
      return this.groupEdges(merge);
   }

   public EdgePartition groupEdges$mcD$sp(final Function2 merge) {
      return this.groupEdges(merge);
   }

   public EdgePartition groupEdges$mcF$sp(final Function2 merge) {
      return this.groupEdges(merge);
   }

   public EdgePartition groupEdges$mcI$sp(final Function2 merge) {
      return this.groupEdges(merge);
   }

   public EdgePartition groupEdges$mcJ$sp(final Function2 merge) {
      return this.groupEdges(merge);
   }

   // $FF: synthetic method
   public static final int $anonfun$numActives$1(final OpenHashSet x$1) {
      return x$1.size();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$aggregateMessagesEdgeScan$1(final EdgePartition $this, final Object aggregates$1, final int localId) {
      return new Tuple2(BoxesRunTime.boxToLong($this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localId]), scala.runtime.ScalaRunTime..MODULE$.array_apply(aggregates$1, localId));
   }

   // $FF: synthetic method
   public static final void $anonfun$aggregateMessagesIndexScan$1(final EdgePartition $this, final EdgeActiveness activeness$1, final TripletFields tripletFields$1, final AggregatingEdgeContext ctx$1, final Function1 sendMsg$1, final Tuple2 cluster) {
      long clusterSrcId;
      int clusterPos;
      int clusterLocalSrcId;
      boolean var10000;
      label162: {
         label166: {
            clusterSrcId = cluster._1$mcJ$sp();
            clusterPos = cluster._2$mcI$sp();
            clusterLocalSrcId = $this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[clusterPos];
            EdgeActiveness var11 = EdgeActiveness.Neither;
            if (activeness$1 == null) {
               if (var11 == null) {
                  break label166;
               }
            } else if (activeness$1.equals(var11)) {
               break label166;
            }

            label167: {
               EdgeActiveness var12 = EdgeActiveness.SrcOnly;
               if (activeness$1 == null) {
                  if (var12 == null) {
                     break label167;
                  }
               } else if (activeness$1.equals(var12)) {
                  break label167;
               }

               label168: {
                  EdgeActiveness var13 = EdgeActiveness.DstOnly;
                  if (activeness$1 == null) {
                     if (var13 == null) {
                        break label168;
                     }
                  } else if (activeness$1.equals(var13)) {
                     break label168;
                  }

                  label169: {
                     EdgeActiveness var14 = EdgeActiveness.Both;
                     if (activeness$1 == null) {
                        if (var14 == null) {
                           break label169;
                        }
                     } else if (activeness$1.equals(var14)) {
                        break label169;
                     }

                     EdgeActiveness var15 = EdgeActiveness.Either;
                     if (activeness$1 == null) {
                        if (var15 != null) {
                           throw new Exception("unreachable");
                        }
                     } else if (!activeness$1.equals(var15)) {
                        throw new Exception("unreachable");
                     }

                     var10000 = true;
                     break label162;
                  }

                  var10000 = $this.isActive(clusterSrcId);
                  break label162;
               }

               var10000 = true;
               break label162;
            }

            var10000 = $this.isActive(clusterSrcId);
            break label162;
         }

         var10000 = true;
      }

      boolean scanCluster = var10000;
      if (scanCluster) {
         int pos = clusterPos;
         Object srcAttr = tripletFields$1.useSrc ? scala.runtime.ScalaRunTime..MODULE$.array_apply($this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, clusterLocalSrcId) : null;
         ctx$1.setSrcOnly(clusterSrcId, clusterLocalSrcId, srcAttr);

         for(; pos < $this.size() && $this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[pos] == clusterLocalSrcId; ++pos) {
            int localDstId;
            long dstId;
            label122: {
               label171: {
                  localDstId = $this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[pos];
                  dstId = $this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localDstId];
                  EdgeActiveness var22 = EdgeActiveness.Neither;
                  if (activeness$1 == null) {
                     if (var22 == null) {
                        break label171;
                     }
                  } else if (activeness$1.equals(var22)) {
                     break label171;
                  }

                  label172: {
                     EdgeActiveness var23 = EdgeActiveness.SrcOnly;
                     if (activeness$1 == null) {
                        if (var23 == null) {
                           break label172;
                        }
                     } else if (activeness$1.equals(var23)) {
                        break label172;
                     }

                     label173: {
                        EdgeActiveness var24 = EdgeActiveness.DstOnly;
                        if (activeness$1 == null) {
                           if (var24 == null) {
                              break label173;
                           }
                        } else if (activeness$1.equals(var24)) {
                           break label173;
                        }

                        label174: {
                           EdgeActiveness var25 = EdgeActiveness.Both;
                           if (activeness$1 == null) {
                              if (var25 == null) {
                                 break label174;
                              }
                           } else if (activeness$1.equals(var25)) {
                              break label174;
                           }

                           EdgeActiveness var26 = EdgeActiveness.Either;
                           if (activeness$1 == null) {
                              if (var26 != null) {
                                 throw new Exception("unreachable");
                              }
                           } else if (!activeness$1.equals(var26)) {
                              throw new Exception("unreachable");
                           }

                           if (!$this.isActive(clusterSrcId) && !$this.isActive(dstId)) {
                              var10000 = false;
                              break label122;
                           }

                           var10000 = true;
                           break label122;
                        }

                        var10000 = $this.isActive(dstId);
                        break label122;
                     }

                     var10000 = $this.isActive(dstId);
                     break label122;
                  }

                  var10000 = true;
                  break label122;
               }

               var10000 = true;
            }

            boolean edgeIsActive = var10000;
            if (edgeIsActive) {
               Object dstAttr = tripletFields$1.useDst ? scala.runtime.ScalaRunTime..MODULE$.array_apply($this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localDstId) : null;
               ctx$1.setDest(dstId, localDstId, dstAttr, scala.runtime.ScalaRunTime..MODULE$.array_apply($this.org$apache$spark$graphx$impl$EdgePartition$$data, pos));
               sendMsg$1.apply(ctx$1);
            } else {
               BoxedUnit var29 = BoxedUnit.UNIT;
            }
         }

      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$aggregateMessagesIndexScan$2(final EdgePartition $this, final Object aggregates$2, final int localId) {
      return new Tuple2(BoxesRunTime.boxToLong($this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localId]), scala.runtime.ScalaRunTime..MODULE$.array_apply(aggregates$2, localId));
   }

   public EdgePartition(final int[] localSrcIds, final int[] localDstIds, final Object data, final GraphXPrimitiveKeyOpenHashMap index, final GraphXPrimitiveKeyOpenHashMap global2local, final long[] local2global, final Object vertexAttrs, final Option activeSet, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds = localSrcIds;
      this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds = localDstIds;
      this.org$apache$spark$graphx$impl$EdgePartition$$data = data;
      this.org$apache$spark$graphx$impl$EdgePartition$$index = index;
      this.org$apache$spark$graphx$impl$EdgePartition$$global2local = global2local;
      this.org$apache$spark$graphx$impl$EdgePartition$$local2global = local2global;
      this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs = vertexAttrs;
      this.org$apache$spark$graphx$impl$EdgePartition$$activeSet = activeSet;
      this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1 = evidence$1;
      this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2 = evidence$2;
      this.size = localSrcIds.length;
      Statics.releaseFence();
   }

   public EdgePartition(final ClassTag evidence$3, final ClassTag evidence$4) {
      this((int[])null, (int[])null, (Object)null, (GraphXPrimitiveKeyOpenHashMap)null, (GraphXPrimitiveKeyOpenHashMap)null, (long[])null, (Object)null, (Option)null, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
