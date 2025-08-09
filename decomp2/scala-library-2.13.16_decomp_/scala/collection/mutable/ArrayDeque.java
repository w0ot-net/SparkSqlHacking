package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IndexedSeqView;
import scala.collection.IterableFactory;
import scala.collection.IterableFactory$;
import scala.collection.IterableOnce;
import scala.collection.IterableOnce$;
import scala.collection.Iterator;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.View;
import scala.collection.generic.CommonErrors$;
import scala.collection.generic.DefaultSerializable;
import scala.math.Integral;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\ruh\u0001B*U\u0001mC!\"!\u0005\u0001\u0005\u0003\u0007I\u0011CA\n\u0011)\t\t\u0003\u0001BA\u0002\u0013E\u00111\u0005\u0005\u000b\u0003_\u0001!\u0011!Q!\n\u0005U\u0001bCA\u0019\u0001\t\u0015\t\u0011)Q\u0005\u0003gA1\"!\u000f\u0001\u0005\u000b\u0005\t\u0015)\u0003\u00024!9\u00111\b\u0001\u0005\u0012\u0005u\u0002\u0002CA%\u0001\u0001&I!a\u0013\t\u000f\u0005m\u0002\u0001\"\u0001\u0002T!9\u0011\u0011\f\u0001\u0005B\u0005m\u0003bBA/\u0001\u0011\u0005\u0013q\f\u0005\b\u0003K\u0003A\u0011AAT\u0011\u001d\ti\u000b\u0001C\u0001\u0003_Cq!a.\u0001\t\u0003\tI\fC\u0004\u0002@\u0002!\t!!1\t\u000f\u0005\u0015\u0007\u0001\"\u0011\u0002H\"9\u00111\u001b\u0001\u0005B\u0005U\u0007bBAm\u0001\u0011\u0005\u00111\u001c\u0005\b\u0003C\u0004A\u0011AAr\u0011\u001d\tI\u000f\u0001C\u0001\u0003WDq!!;\u0001\t\u0003\t\u0019\u0010C\u0004\u0002x\u0002!\t%!?\t\u000f\u0005u\b\u0001\"\u0001\u0002\u0000\"I!\u0011\u0003\u0001\u0012\u0002\u0013\u0005!1\u0003\u0005\b\u0005S\u0001A\u0011\u0001B\u0016\u0011%\u0011y\u0003AI\u0001\n\u0003\u0011\u0019\u0002\u0003\u0005\u00032\u0001\u0001K\u0011\u0002B\u001a\u0011%\u0011y\u0004AI\u0001\n\u0013\u0011\u0019\u0002C\u0004\u0003B\u0001!\tAa\u0011\t\u0013\t\u001d\u0003!%A\u0005\u0002\tM\u0001b\u0002B%\u0001\u0011\u0005!1\n\u0005\n\u0005\u001f\u0002\u0011\u0013!C\u0001\u0005'A\u0001B!\u0015\u0001A\u0013%!1\u000b\u0005\n\u00053\u0002\u0011\u0013!C\u0005\u0005'AqAa\u0017\u0001\t\u0003\u0011i\u0006C\u0004\u0003l\u0001!\tA!\u0018\t\u000f\t5\u0004\u0001\"\u0001\u0003p!9!1\u0010\u0001\u0005\u0002\tu\u0004b\u0002BA\u0001\u0011\u0005!1\u0011\u0005\n\u0005\u001b\u0003\u0011\u0013!C\u0001\u0005\u001fCqAa\u0017\u0001\t\u0003\u0011\u0019\nC\u0004\u0003\u0018\u0002!\tA!'\t\u000f\t\u0005\u0006\u0001\"\u0001\u0002\\!9!1\u0015\u0001\u0005B\t\u0015\u0006b\u0002BT\u0001\u0011E#\u0011\u0016\u0005\b\u0005W\u0003A\u0011\tBW\u0011\u001d\u0011)\f\u0001C\u0001\u0005oCqA!/\u0001\t\u0003\u0011Y\fC\u0005\u0003B\u0002\t\n\u0011\"\u0001\u0003\u0010\"9!1\u0019\u0001\u0005\u0012\t\u0015\u0007b\u0002Bf\u0001\u0011\u0005#Q\u001a\u0005\b\u0005O\u0004A\u0011\tBu\u0011\u001d\u0019\u0019\u0001\u0001C\u0001\u0005oCqa!\u0002\u0001\t#\u00199\u0001\u0003\b\u0004\u000e\u0001!\t\u0011!B\u0001\u0002\u0003&Iaa\u0004\t\u001d\rU\u0001\u0001\"A\u0001\u0006\u0003\u0005\t\u0015\"\u0003\u0004\u0018!q1Q\u0004\u0001\u0005\u0002\u0003\u0015\t\u0011!Q\u0005\n\r}\u0001BDB\u0013\u0001\u0011\u0005\tQ!A\u0001B\u0013%1q\u0005\u0005\t\u0007[\u0001\u0001\u0015\"\u0003\u00040!A1Q\u0007\u0001!\n\u0013\u00199\u0004\u0003\u0005\u0004>\u0001\u0001K\u0011BB \u0011!\u0019)\u0005\u0001Q\u0005\n\r\u001d\u0003BDB(\u0001\u0011\u0005\tQ!A\u0001B\u0013%1\u0011\u000b\u0005\t\u0007+\u0002\u0001\u0015\"\u0015\u0004X!a1\u0011\u000e\u0001\u0003\u0006\u0003\u0007I\u0011\u0001\u0001\u0002\\!a11\u000e\u0001\u0003\u0006\u0003\u0007I\u0011\u0001\u0001\u0004n!a1\u0011\u000f\u0001\u0003\u0006\u0003\u0007I\u0011\u0001\u0001\u0002\\!a11\u000f\u0001\u0003\u0006\u0003\u0007I\u0011\u0001\u0001\u0004v!a1\u0011\u0010\u0001\u0003\u0002\u0003\u0005I\u0011\u0001\u0001\u0004|!a1\u0011\u0011\u0001\u0003\u0002\u0003\u0005I\u0011\u0001\u0001\u0004\u0004\u001e91\u0011\u0012+\t\u0002\r-eAB*U\u0011\u0003\u0019i\tC\u0004\u0002<\u001d#\ta!&\t\u000f\t-u\t\"\u0001\u0004\u0018\"91qU$\u0005\u0002\r%\u0006bBB]\u000f\u0012\u000511\u0018\u0005\n\u0007\u000b<%\u0019!C\u0003\u0007\u000fD\u0001b!4HA\u000351\u0011\u001a\u0005\u000b\u0007\u001f<%\u0019!C\u0003\u000f\u000eE\u0007\u0002CBl\u000f\u0002\u0006iaa5\t\u0011\rew\t\"\u0001U\u00077D\u0011ba8H#\u0003%\ta!9\t\u0013\r\u0015x)!A\u0005\n\r\u001d(AC!se\u0006LH)Z9vK*\u0011QKV\u0001\b[V$\u0018M\u00197f\u0015\t9\u0006,\u0001\u0006d_2dWm\u0019;j_:T\u0011!W\u0001\u0006g\u000e\fG.Y\u0002\u0001+\ta6m\u0005\u0006\u0001;6\u0004X/\u001f?\u0000\u0003\u000b\u00012AX0b\u001b\u0005!\u0016B\u00011U\u00059\t%m\u001d;sC\u000e$()\u001e4gKJ\u0004\"AY2\r\u0001\u0011)A\r\u0001b\u0001K\n\t\u0011)\u0005\u0002gUB\u0011q\r[\u0007\u00021&\u0011\u0011\u000e\u0017\u0002\b\u001d>$\b.\u001b8h!\t97.\u0003\u0002m1\n\u0019\u0011I\\=\u0011\u0007ys\u0017-\u0003\u0002p)\ni\u0011J\u001c3fq\u0016$')\u001e4gKJ\u0004RAX9bgRL!A\u001d+\u0003\u001b%sG-\u001a=fIN+\u0017o\u00149t!\tq\u0006\u0001E\u0002_\u0001\u0005\u0004RA^<bgRl\u0011AV\u0005\u0003qZ\u0013Qc\u0015;sS\u000e$x\n\u001d;j[&TX\rZ*fc>\u00038\u000f\u0005\u0003wu\u0006\u001c\u0018BA>W\u0005]IE/\u001a:bE2,g)Y2u_JLH)\u001a4bk2$8\u000fE\u0003_{\u0006\u001cH/\u0003\u0002\u007f)\ni\u0011I\u001d:bs\u0012+\u0017/^3PaN\u0004BAXA\u0001i&\u0019\u00111\u0001+\u0003\u0013\rcwN\\3bE2,\u0007\u0003BA\u0004\u0003\u001bi!!!\u0003\u000b\u0007\u0005-a+A\u0004hK:,'/[2\n\t\u0005=\u0011\u0011\u0002\u0002\u0014\t\u00164\u0017-\u001e7u'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0006CJ\u0014\u0018-_\u000b\u0003\u0003+\u0001RaZA\f\u00037I1!!\u0007Y\u0005\u0015\t%O]1z!\r9\u0017QD\u0005\u0004\u0003?A&AB!osJ+g-A\u0005beJ\f\u0017p\u0018\u0013fcR!\u0011QEA\u0016!\r9\u0017qE\u0005\u0004\u0003SA&\u0001B+oSRD\u0011\"!\f\u0003\u0003\u0003\u0005\r!!\u0006\u0002\u0007a$\u0013'\u0001\u0004beJ\f\u0017\u0010I\u0001,g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$S.\u001e;bE2,G%\u0011:sCf$U-];fI\u0011\u001aH/\u0019:uAA\u0019q-!\u000e\n\u0007\u0005]\u0002LA\u0002J]R\f\u0011f]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI\u0005\u0013(/Y=EKF,X\r\n\u0013f]\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0004u\u0003\u007f\t\t%!\u0012\t\u000f\u0005Ea\u00011\u0001\u0002\u0016!9\u00111\t\u0004A\u0002\u0005M\u0012!B:uCJ$\bbBA$\r\u0001\u0007\u00111G\u0001\u0004K:$\u0017!\u0002:fg\u0016$H\u0003CA\u0013\u0003\u001b\ny%!\u0015\t\u000f\u0005Eq\u00011\u0001\u0002\u0016!9\u00111I\u0004A\u0002\u0005M\u0002bBA$\u000f\u0001\u0007\u00111\u0007\u000b\u0004i\u0006U\u0003\"CA,\u0011A\u0005\t\u0019AA\u001a\u0003-Ig.\u001b;jC2\u001c\u0016N_3\u0002\u0013-twn\u001e8TSj,WCAA\u001a\u0003\u001d\u0019H/\u001a9qKJ,B!!\u0019\u0002lQ!\u00111MAN%\u0019\t)'!\u001b\u0002\u0000\u00191\u0011q\r\u0001\u0001\u0003G\u0012A\u0002\u0010:fM&tW-\\3oiz\u00022AYA6\t\u001d\tiG\u0003b\u0001\u0003_\u0012\u0011aU\t\u0004M\u0006E\u0004\u0007BA:\u0003w\u0002RA^A;\u0003sJ1!a\u001eW\u0005\u001d\u0019F/\u001a9qKJ\u00042AYA>\t-\ti(a\u001b\u0002\u0002\u0003\u0005)\u0011A3\u0003\u0007}#\u0013\u0007\u0005\u0003\u0002\u0002\u0006Ue\u0002BAB\u0003#sA!!\"\u0002\u0010:!\u0011qQAG\u001b\t\tIIC\u0002\u0002\fj\u000ba\u0001\u0010:p_Rt\u0014\"A-\n\u0005]C\u0016bAAJ-\u000691\u000b^3qa\u0016\u0014\u0018\u0002BAL\u00033\u0013a\"\u00124gS\u000eLWM\u001c;Ta2LGOC\u0002\u0002\u0014ZCq!!(\u000b\u0001\b\ty*A\u0003tQ\u0006\u0004X\r\u0005\u0004w\u0003C\u000b\u0017\u0011N\u0005\u0004\u0003G3&\u0001D*uKB\u0004XM]*iCB,\u0017!B1qa2LHcA1\u0002*\"9\u00111V\u0006A\u0002\u0005M\u0012aA5eq\u00061Q\u000f\u001d3bi\u0016$b!!\n\u00022\u0006M\u0006bBAV\u0019\u0001\u0007\u00111\u0007\u0005\u0007\u0003kc\u0001\u0019A1\u0002\t\u0015dW-\\\u0001\u0007C\u0012$wJ\\3\u0015\t\u0005m\u0016QX\u0007\u0002\u0001!1\u0011QW\u0007A\u0002\u0005\fq\u0001\u001d:fa\u0016tG\r\u0006\u0003\u0002<\u0006\r\u0007BBA[\u001d\u0001\u0007\u0011-\u0001\u0006qe\u0016\u0004XM\u001c3BY2$B!a/\u0002J\"9\u00111Z\bA\u0002\u00055\u0017!B3mK6\u001c\b\u0003\u0002<\u0002P\u0006L1!!5W\u00051IE/\u001a:bE2,wJ\\2f\u0003\u0019\tG\rZ!mYR!\u00111XAl\u0011\u001d\tY\r\u0005a\u0001\u0003\u001b\fa!\u001b8tKJ$HCBA\u0013\u0003;\fy\u000eC\u0004\u0002,F\u0001\r!a\r\t\r\u0005U\u0016\u00031\u0001b\u0003%Ign]3si\u0006cG\u000e\u0006\u0004\u0002&\u0005\u0015\u0018q\u001d\u0005\b\u0003W\u0013\u0002\u0019AA\u001a\u0011\u001d\tYM\u0005a\u0001\u0003\u001b\faA]3n_Z,GCBA\u0013\u0003[\fy\u000fC\u0004\u0002,N\u0001\r!a\r\t\u000f\u0005E8\u00031\u0001\u00024\u0005)1m\\;oiR\u0019\u0011-!>\t\u000f\u0005-F\u00031\u0001\u00024\u0005Y1/\u001e2ue\u0006\u001cGo\u00148f)\u0011\tY,a?\t\r\u0005UV\u00031\u0001b\u0003A\u0011X-\\8wK\"+\u0017\rZ(qi&|g\u000e\u0006\u0003\u0003\u0002\t\u001d\u0001\u0003B4\u0003\u0004\u0005L1A!\u0002Y\u0005\u0019y\u0005\u000f^5p]\"I!\u0011\u0002\f\u0011\u0002\u0003\u0007!1B\u0001\u0013e\u0016\u001c\u0018N_3J]R,'O\\1m%\u0016\u0004(\u000fE\u0002h\u0005\u001bI1Aa\u0004Y\u0005\u001d\u0011un\u001c7fC:\f!D]3n_Z,\u0007*Z1e\u001fB$\u0018n\u001c8%I\u00164\u0017-\u001e7uIE*\"A!\u0006+\t\t-!qC\u0016\u0003\u00053\u0001BAa\u0007\u0003&5\u0011!Q\u0004\u0006\u0005\u0005?\u0011\t#A\u0005v]\u000eDWmY6fI*\u0019!1\u0005-\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003(\tu!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006Q!/Z7pm\u0016DU-\u00193\u0015\u0007\u0005\u0014i\u0003C\u0005\u0003\na\u0001\n\u00111\u0001\u0003\f\u0005!\"/Z7pm\u0016DU-\u00193%I\u00164\u0017-\u001e7uIE\n!D]3n_Z,\u0007*Z1e\u0003N\u001cX/\\5oO:{g.R7qif$2!\u0019B\u001b\u0011%\u0011IA\u0007I\u0001\u0002\u0004\u0011Y\u0001K\u0002\u001b\u0005s\u00012a\u001aB\u001e\u0013\r\u0011i\u0004\u0017\u0002\u0007S:d\u0017N\\3\u0002II,Wn\u001c<f\u0011\u0016\fG-Q:tk6Lgn\u001a(p]\u0016k\u0007\u000f^=%I\u00164\u0017-\u001e7uIE\n\u0001C]3n_Z,G*Y:u\u001fB$\u0018n\u001c8\u0015\t\t\u0005!Q\t\u0005\n\u0005\u0013a\u0002\u0013!a\u0001\u0005\u0017\t!D]3n_Z,G*Y:u\u001fB$\u0018n\u001c8%I\u00164\u0017-\u001e7uIE\n!B]3n_Z,G*Y:u)\r\t'Q\n\u0005\n\u0005\u0013q\u0002\u0013!a\u0001\u0005\u0017\tAC]3n_Z,G*Y:uI\u0011,g-Y;mi\u0012\n\u0014A\u0007:f[>4X\rT1ti\u0006\u001b8/^7j]\u001etuN\\#naRLHcA1\u0003V!I!\u0011\u0002\u0011\u0011\u0002\u0003\u0007!1\u0002\u0015\u0004A\te\u0012\u0001\n:f[>4X\rT1ti\u0006\u001b8/^7j]\u001etuN\\#naRLH\u0005Z3gCVdG\u000fJ\u0019\u0002\u0013I,Wn\u001c<f\u00032dGC\u0001B0!\u0015\u0011\tGa\u001ab\u001b\t\u0011\u0019GC\u0002\u0003fY\u000b\u0011\"[7nkR\f'\r\\3\n\t\t%$1\r\u0002\u0004'\u0016\f\u0018\u0001\u0005:f[>4X-\u00117m%\u00164XM]:f\u0003=\u0011X-\\8wK\"+\u0017\rZ,iS2,G\u0003\u0002B0\u0005cBqAa\u001d%\u0001\u0004\u0011)(A\u0001g!\u00199'qO1\u0003\f%\u0019!\u0011\u0010-\u0003\u0013\u0019+hn\u0019;j_:\f\u0014a\u0004:f[>4X\rT1ti^C\u0017\u000e\\3\u0015\t\t}#q\u0010\u0005\b\u0005g*\u0003\u0019\u0001B;\u0003-\u0011X-\\8wK\u001aK'o\u001d;\u0015\r\t\u0005!Q\u0011BE\u0011\u001d\u00119I\na\u0001\u0005k\n\u0011\u0001\u001d\u0005\n\u0005\u00173\u0003\u0013!a\u0001\u0003g\tAA\u001a:p[\u0006)\"/Z7pm\u00164\u0015N]:uI\u0011,g-Y;mi\u0012\u0012TC\u0001BIU\u0011\t\u0019Da\u0006\u0015\t\t}#Q\u0013\u0005\b\u0005\u000fC\u0003\u0019\u0001B;\u0003))gn];sKNK'0\u001a\u000b\u0005\u0003K\u0011Y\nC\u0004\u0003\u001e&\u0002\r!a\r\u0002\t!Lg\u000e\u001e\u0015\u0004S\te\u0012A\u00027f]\u001e$\b.A\u0004jg\u0016k\u0007\u000f^=\u0016\u0005\t-\u0011!B6m_:,G#\u0001;\u0002\u001f%$XM]1cY\u00164\u0015m\u0019;pef,\"Aa,\u0011\tY\u0014\tl]\u0005\u0004\u0005g3&AC*fc\u001a\u000b7\r^8ss\u0006)1\r\\3beR\u0011\u0011QE\u0001\u000fG2,\u0017M]!oINC'/\u001b8l)\u0011\tYL!0\t\u0013\t}v\u0006%AA\u0002\u0005M\u0012\u0001B:ju\u0016\f\u0001d\u00197fCJ\fe\u000eZ*ie&t7\u000e\n3fM\u0006,H\u000e\u001e\u00132\u0003\u001dyg-\u0011:sCf$R\u0001\u001eBd\u0005\u0013Dq!!\u00052\u0001\u0004\t)\u0002C\u0004\u0002HE\u0002\r!a\r\u0002\u0017\r|\u0007/\u001f+p\u0003J\u0014\u0018-_\u000b\u0005\u0005\u001f\u0014I\u000e\u0006\u0005\u00024\tE'q\u001cBr\u0011\u001d\u0011\u0019N\ra\u0001\u0005+\fA\u0001Z3tiB)q-a\u0006\u0003XB\u0019!M!7\u0005\u000f\tm'G1\u0001\u0003^\n\t!)\u0005\u0002bU\"9!\u0011\u001d\u001aA\u0002\u0005M\u0012!\u00033fgR\u001cF/\u0019:u\u0011\u001d\u0011)O\ra\u0001\u0003g\t1\u0001\\3o\u0003\u001d!x.\u0011:sCf,BAa;\u0003rR!!Q\u001eBz!\u00159\u0017q\u0003Bx!\r\u0011'\u0011\u001f\u0003\b\u00057\u001c$\u0019\u0001Bo\u0011%\u0011)pMA\u0001\u0002\b\u001190\u0001\u0006fm&$WM\\2fIE\u0002bA!?\u0003\u0000\n=XB\u0001B~\u0015\r\u0011i\u0010W\u0001\be\u00164G.Z2u\u0013\u0011\u0019\tAa?\u0003\u0011\rc\u0017m]:UC\u001e\f!\u0002\u001e:j[R{7+\u001b>f\u0003-\u0019H/\u0019:u?\u0012\u0002H.^:\u0015\t\u0005M2\u0011\u0002\u0005\b\u0003W+\u0004\u0019AA\u001aQ\r)$\u0011H\u00012g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$S.\u001e;bE2,G%\u0011:sCf$U-];fI\u0011\u001aH/\u0019:u?\u0012j\u0017N\\;t)\u0011\t\u0019d!\u0005\t\u000f\u0005-f\u00071\u0001\u00024!\u001aaG!\u000f\u0002]M\u001c\u0017\r\\1%G>dG.Z2uS>tG%\\;uC\ndW\rJ!se\u0006LH)Z9vK\u0012\"SM\u001c3`IAdWo\u001d\u000b\u0005\u0003g\u0019I\u0002C\u0004\u0002,^\u0002\r!a\r)\u0007]\u0012I$A\u0018tG\u0006d\u0017\rJ2pY2,7\r^5p]\u0012jW\u000f^1cY\u0016$\u0013I\u001d:bs\u0012+\u0017/^3%I\u0015tGm\u0018\u0013nS:,8\u000f\u0006\u0003\u00024\r\u0005\u0002bBAVq\u0001\u0007\u00111\u0007\u0015\u0004q\te\u0012!L:dC2\fGeY8mY\u0016\u001cG/[8oI5,H/\u00192mK\u0012\n%O]1z\t\u0016\fX/\u001a\u0013%[V\u001cHo\u0012:poR!!1BB\u0015\u0011\u001d\u0011)/\u000fa\u0001\u0003gA3!\u000fB\u001d\u00031\u0019\bn\\;mINC'/\u001b8l)\u0011\u0011Ya!\r\t\u000f\t\u0015(\b1\u0001\u00024!\u001a!H!\u000f\u0002\u0013\r\fgn\u00155sS:\\G\u0003\u0002B\u0006\u0007sAqA!:<\u0001\u0004\t\u0019\u0004K\u0002<\u0005s\tAaX4fiR\u0019\u0011m!\u0011\t\u000f\u0005-F\b1\u0001\u00024!\u001aAH!\u000f\u0002\t}\u001bX\r\u001e\u000b\u0007\u0003K\u0019Iea\u0013\t\u000f\u0005-V\b1\u0001\u00024!1\u0011QW\u001fA\u0002\u0005D3!\u0010B\u001d\u0003-\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013BeJ\f\u0017\u0010R3rk\u0016$CE]3tSj,G\u0003BA\u0013\u0007'BqA!:?\u0001\u0004\t\u0019$\u0001\u0007tiJLgn\u001a)sK\u001aL\u00070\u0006\u0002\u0004ZA!11LB3\u001b\t\u0019iF\u0003\u0003\u0004`\r\u0005\u0014\u0001\u00027b]\u001eT!aa\u0019\u0002\t)\fg/Y\u0005\u0005\u0007O\u001aiF\u0001\u0004TiJLgnZ\u0001+g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$S.\u001e;bE2,G%\u0011:sCf$U-];fI\u0011\u001aH/\u0019:u\u00039\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013BeJ\f\u0017\u0010R3rk\u0016$Ce\u001d;beR|F%Z9\u0015\t\u0005\u00152q\u000e\u0005\n\u0003[\t\u0015\u0011!a\u0001\u0003g\t\u0001f]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI\u0005\u0013(/Y=EKF,X\r\n\u0013f]\u0012\fAf]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI\u0005\u0013(/Y=EKF,X\r\n\u0013f]\u0012|F%Z9\u0015\t\u0005\u00152q\u000f\u0005\n\u0003[\u0019\u0015\u0011!a\u0001\u0003g\t1h]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI\u0005\u0013(/Y=EKF,X\r\n\u0013baB,g\u000eZ!tgVl\u0017N\\4DCB\f7-\u001b;z)\u0011\tYl! \t\r\u0005UF\t1\u0001bQ\r!%\u0011H\u0001=g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$S.\u001e;bE2,G%\u0011:sCf$U-];fI\u0011\u0002(/\u001a9f]\u0012\f5o];nS:<7)\u00199bG&$\u0018\u0010\u0006\u0003\u0002<\u000e\u0015\u0005BBA[\u000b\u0002\u0007\u0011\rK\u0002F\u0005s\t!\"\u0011:sCf$U-];f!\tqviE\u0003H\u00037\u0019y\t\u0005\u0003w\u0007#\u001b\u0018bABJ-\nI2\u000b\u001e:jGR|\u0005\u000f^5nSj,GmU3r\r\u0006\u001cGo\u001c:z)\t\u0019Y)\u0006\u0003\u0004\u001a\u000e}E\u0003BBN\u0007C\u0003BA\u0018\u0001\u0004\u001eB\u0019!ma(\u0005\r\tm\u0017J1\u0001f\u0011\u001d\u0019\u0019+\u0013a\u0001\u0007K\u000bAaY8mYB)a/a4\u0004\u001e\u0006Qa.Z<Ck&dG-\u001a:\u0016\t\r-6QW\u000b\u0003\u0007[\u0003rAXBX\u0007g\u001b9,C\u0002\u00042R\u0013qAQ;jY\u0012,'\u000fE\u0002c\u0007k#Q\u0001\u001a&C\u0002\u0015\u0004BA\u0018\u0001\u00044\u0006)Q-\u001c9usV!1QXBb+\t\u0019y\f\u0005\u0003_\u0001\r\u0005\u0007c\u00012\u0004D\u0012)Am\u0013b\u0001K\u0006\u0011B)\u001a4bk2$\u0018J\\5uS\u0006d7+\u001b>f+\t\u0019Im\u0004\u0002\u0004Lv\t\u0001#A\nEK\u001a\fW\u000f\u001c;J]&$\u0018.\u00197TSj,\u0007%\u0001\u0006Ti\u0006\u0014G.Z*ju\u0016,\"aa5\u0010\u0005\rUWD\u0001\u0001\u0001\u0004-\u0019F/\u00192mKNK'0\u001a\u0011\u0002\u000b\u0005dGn\\2\u0015\t\u0005U1Q\u001c\u0005\b\u0005K\u0004\u0006\u0019AA\u001a\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU!!qRBr\t\u0015!\u0017K1\u0001f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019I\u000f\u0005\u0003\u0004\\\r-\u0018\u0002BBw\u0007;\u0012aa\u00142kK\u000e$\bfB$\u0004r\u000e]8\u0011 \t\u0004O\u000eM\u0018bAB{1\n\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0007!:ai!=\u0004x\u000ee\b"
)
public class ArrayDeque extends AbstractBuffer implements IndexedBuffer, ArrayDequeOps, DefaultSerializable {
   private Object[] array;
   private int scala$collection$mutable$ArrayDeque$$start;
   private int scala$collection$mutable$ArrayDeque$$end;

   public static int $lessinit$greater$default$1() {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      return 16;
   }

   public static int DefaultInitialSize() {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      return 16;
   }

   public static Builder newBuilder() {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      return new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
   }

   public static ArrayDeque from(final IterableOnce coll) {
      return ArrayDeque$.MODULE$.from(coll);
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      tabulate_b.sizeHint(n);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      fill_b.sizeHint(n);

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayDeque)this.elems()).ensureSize(size);
            }
         };
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayDeque)this.elems()).ensureSize(size);
               }
            };
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new GrowableBuilder() {
                  public void sizeHint(final int size) {
                     ((ArrayDeque)this.elems()).ensureSize(size);
                  }
               };
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Builder tabulate_b = new GrowableBuilder() {
                     public void sizeHint(final int size) {
                        ((ArrayDeque)this.elems()).ensureSize(size);
                     }
                  };
                  tabulate_b.sizeHint(n5);

                  for(int tabulate_i = 0; tabulate_i < n5; ++tabulate_i) {
                     Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                     tabulate_b.addOne(tabulate_$plus$eq_elem);
                     tabulate_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var34 = (scala.collection.SeqOps)tabulate_b.result();
                  tabulate_b = null;
                  Object var33 = null;
                  Object tabulate_$plus$eq_elem = var34;
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var35 = (scala.collection.SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var30 = null;
               Object tabulate_$plus$eq_elem = var35;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var36 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var27 = null;
            Object tabulate_$plus$eq_elem = var36;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var37 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var24 = null;
         Object tabulate_$plus$eq_elem = var37;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayDeque)this.elems()).ensureSize(size);
            }
         };
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayDeque)this.elems()).ensureSize(size);
               }
            };
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new GrowableBuilder() {
                  public void sizeHint(final int size) {
                     ((ArrayDeque)this.elems()).ensureSize(size);
                  }
               };
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var27 = (scala.collection.SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var26 = null;
               Object tabulate_$plus$eq_elem = var27;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var28 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var23 = null;
            Object tabulate_$plus$eq_elem = var28;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var29 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var20 = null;
         Object tabulate_$plus$eq_elem = var29;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayDeque)this.elems()).ensureSize(size);
            }
         };
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayDeque)this.elems()).ensureSize(size);
               }
            };
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i);
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var20 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var19 = null;
            Object tabulate_$plus$eq_elem = var20;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var21 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var16 = null;
         Object tabulate_$plus$eq_elem = var21;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayDeque)this.elems()).ensureSize(size);
            }
         };
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i);
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var13 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var12 = null;
         Object tabulate_$plus$eq_elem = var13;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayDeque)this.elems()).ensureSize(size);
            }
         };
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayDeque)this.elems()).ensureSize(size);
               }
            };
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new GrowableBuilder() {
                  public void sizeHint(final int size) {
                     ((ArrayDeque)this.elems()).ensureSize(size);
                  }
               };
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Builder fill_b = new GrowableBuilder() {
                     public void sizeHint(final int size) {
                        ((ArrayDeque)this.elems()).ensureSize(size);
                     }
                  };
                  fill_b.sizeHint(n5);

                  for(int fill_i = 0; fill_i < n5; ++fill_i) {
                     Object fill_$plus$eq_elem = elem.apply();
                     fill_b.addOne(fill_$plus$eq_elem);
                     fill_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var34 = (scala.collection.SeqOps)fill_b.result();
                  fill_b = null;
                  Object var33 = null;
                  Object fill_$plus$eq_elem = var34;
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var35 = (scala.collection.SeqOps)fill_b.result();
               fill_b = null;
               Object var30 = null;
               Object fill_$plus$eq_elem = var35;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var36 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var27 = null;
            Object fill_$plus$eq_elem = var36;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var37 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var24 = null;
         Object fill_$plus$eq_elem = var37;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayDeque)this.elems()).ensureSize(size);
            }
         };
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayDeque)this.elems()).ensureSize(size);
               }
            };
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new GrowableBuilder() {
                  public void sizeHint(final int size) {
                     ((ArrayDeque)this.elems()).ensureSize(size);
                  }
               };
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Object fill_$plus$eq_elem = elem.apply();
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var27 = (scala.collection.SeqOps)fill_b.result();
               fill_b = null;
               Object var26 = null;
               Object fill_$plus$eq_elem = var27;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var28 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var23 = null;
            Object fill_$plus$eq_elem = var28;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var29 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var20 = null;
         Object fill_$plus$eq_elem = var29;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayDeque)this.elems()).ensureSize(size);
            }
         };
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayDeque)this.elems()).ensureSize(size);
               }
            };
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_$plus$eq_elem = elem.apply();
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var20 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var19 = null;
            Object fill_$plus$eq_elem = var20;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var21 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var16 = null;
         Object fill_$plus$eq_elem = var21;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      ArrayDeque$ var10000 = ArrayDeque$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayDeque)this.elems()).ensureSize(size);
         }
      };
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayDeque)this.elems()).ensureSize(size);
            }
         };
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_$plus$eq_elem = elem.apply();
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var13 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var12 = null;
         Object fill_$plus$eq_elem = var13;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(ArrayDeque$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(ArrayDeque$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      ArrayDeque$ unfold_this = ArrayDeque$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      ArrayDeque$ iterate_this = ArrayDeque$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public final Object clone() {
      return ArrayDequeOps.clone$(this);
   }

   public final void requireBounds(final int idx, final int until) {
      ArrayDequeOps.requireBounds$(this, idx, until);
   }

   public final int requireBounds$default$2() {
      return ArrayDequeOps.requireBounds$default$2$(this);
   }

   public Object copySliceToArray(final int srcStart, final Object dest, final int destStart, final int maxItems) {
      return ArrayDequeOps.copySliceToArray$(this, srcStart, dest, destStart, maxItems);
   }

   public Object reverse() {
      return ArrayDequeOps.reverse$(this);
   }

   public Object slice(final int from, final int until) {
      return ArrayDequeOps.slice$(this, from, until);
   }

   public Iterator sliding(final int window, final int step) {
      return ArrayDequeOps.sliding$(this, window, step);
   }

   public Iterator grouped(final int n) {
      return ArrayDequeOps.grouped$(this, n);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object prepended(final Object elem) {
      return StrictOptimizedSeqOps.prepended$(this, elem);
   }

   public Object appended(final Object elem) {
      return StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return StrictOptimizedSeqOps.appendedAll$(this, suffix);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return StrictOptimizedSeqOps.prependedAll$(this, prefix);
   }

   public Object padTo(final int len, final Object elem) {
      return StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return StrictOptimizedSeqOps.intersect$(this, that);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public IndexedBuffer flatMapInPlace(final Function1 f) {
      return IndexedBuffer.flatMapInPlace$(this, f);
   }

   public IndexedBuffer filterInPlace(final Function1 p) {
      return IndexedBuffer.filterInPlace$(this, p);
   }

   public IndexedBuffer patchInPlace(final int from, final IterableOnce patch, final int replaced) {
      return IndexedBuffer.patchInPlace$(this, from, patch, replaced);
   }

   public IndexedSeqOps mapInPlace(final Function1 f) {
      return IndexedSeqOps.mapInPlace$(this, f);
   }

   public IndexedSeqOps sortInPlace(final Ordering ord) {
      return IndexedSeqOps.sortInPlace$(this, ord);
   }

   public IndexedSeqOps sortInPlaceWith(final Function2 lt) {
      return IndexedSeqOps.sortInPlaceWith$(this, lt);
   }

   public IndexedSeqOps sortInPlaceBy(final Function1 f, final Ordering ord) {
      return IndexedSeqOps.sortInPlaceBy$(this, f, ord);
   }

   public Iterator iterator() {
      return scala.collection.IndexedSeqOps.iterator$(this);
   }

   public Iterator reverseIterator() {
      return scala.collection.IndexedSeqOps.reverseIterator$(this);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return scala.collection.IndexedSeqOps.foldRight$(this, z, op);
   }

   public IndexedSeqView view() {
      return scala.collection.IndexedSeqOps.view$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return scala.collection.IndexedSeqOps.view$(this, from, until);
   }

   public scala.collection.Iterable reversed() {
      return scala.collection.IndexedSeqOps.reversed$(this);
   }

   public Object take(final int n) {
      return scala.collection.IndexedSeqOps.take$(this, n);
   }

   public Object drop(final int n) {
      return scala.collection.IndexedSeqOps.drop$(this, n);
   }

   public Object head() {
      return scala.collection.IndexedSeqOps.head$(this);
   }

   public Option headOption() {
      return scala.collection.IndexedSeqOps.headOption$(this);
   }

   public Object last() {
      return scala.collection.IndexedSeqOps.last$(this);
   }

   public final int lengthCompare(final int len) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, len);
   }

   public final int lengthCompare(final scala.collection.Iterable that) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, from, to, ord);
   }

   public Object[] array() {
      return this.array;
   }

   public void array_$eq(final Object[] x$1) {
      this.array = x$1;
   }

   public int scala$collection$mutable$ArrayDeque$$start() {
      return this.scala$collection$mutable$ArrayDeque$$start;
   }

   public void scala$collection$mutable$ArrayDeque$$start_$eq(final int x$1) {
      this.scala$collection$mutable$ArrayDeque$$start = x$1;
   }

   public int scala$collection$mutable$ArrayDeque$$end() {
      return this.scala$collection$mutable$ArrayDeque$$end;
   }

   public void scala$collection$mutable$ArrayDeque$$end_$eq(final int x$1) {
      this.scala$collection$mutable$ArrayDeque$$end = x$1;
   }

   private void reset(final Object[] array, final int start, final int end) {
      if ((array.length & array.length - 1) != 0) {
         throw new AssertionError((new java.lang.StringBuilder(18)).append("assertion failed: ").append("Array.length must be power of 2").toString());
      } else {
         int requireBounds_until = array.length;
         if (start >= 0 && start < requireBounds_until) {
            int requireBounds_until = array.length;
            if (end >= 0 && end < requireBounds_until) {
               this.array_$eq(array);
               this.scala$collection$mutable$ArrayDeque$$start_$eq(start);
               this.scala$collection$mutable$ArrayDeque$$end_$eq(end);
            } else {
               throw CommonErrors$.MODULE$.indexOutOfBounds(end, requireBounds_until - 1);
            }
         } else {
            throw CommonErrors$.MODULE$.indexOutOfBounds(start, requireBounds_until - 1);
         }
      }
   }

   public int knownSize() {
      return scala.collection.IndexedSeqOps.knownSize$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return scala.collection.IndexedSeqOps.stepper$(this, shape);
   }

   public Object apply(final int idx) {
      int requireBounds_until = this.length();
      if (idx >= 0 && idx < requireBounds_until) {
         return this.array()[this.start_$plus(idx)];
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, requireBounds_until - 1);
      }
   }

   public void update(final int idx, final Object elem) {
      int requireBounds_until = this.length();
      if (idx >= 0 && idx < requireBounds_until) {
         this.array()[this.start_$plus(idx)] = elem;
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, requireBounds_until - 1);
      }
   }

   public ArrayDeque addOne(final Object elem) {
      this.ensureSize(this.length() + 1);
      return this.scala$collection$mutable$ArrayDeque$$appendAssumingCapacity(elem);
   }

   public ArrayDeque prepend(final Object elem) {
      this.ensureSize(this.length() + 1);
      return this.scala$collection$mutable$ArrayDeque$$prependAssumingCapacity(elem);
   }

   public ArrayDeque scala$collection$mutable$ArrayDeque$$appendAssumingCapacity(final Object elem) {
      this.array()[this.scala$collection$mutable$ArrayDeque$$end()] = elem;
      int scala$collection$mutable$ArrayDeque$$end_$plus_idx = 1;
      this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() + scala$collection$mutable$ArrayDeque$$end_$plus_idx & this.array().length - 1);
      return this;
   }

   public ArrayDeque scala$collection$mutable$ArrayDeque$$prependAssumingCapacity(final Object elem) {
      int scala$collection$mutable$ArrayDeque$$start_$minus_idx = 1;
      this.scala$collection$mutable$ArrayDeque$$start_$eq(this.scala$collection$mutable$ArrayDeque$$start() - scala$collection$mutable$ArrayDeque$$start_$minus_idx & this.array().length - 1);
      this.array()[this.scala$collection$mutable$ArrayDeque$$start()] = elem;
      return this;
   }

   public ArrayDeque prependAll(final IterableOnce elems) {
      Iterator it = elems.iterator();
      if (it.nonEmpty()) {
         int n = this.length();
         int var4 = elems.knownSize();
         switch (var4) {
         }

         if (var4 < 0) {
            IterableFactory$ var10002 = IterableFactory$.MODULE$;
            IterableFactory toFactory_factory = IndexedSeq$.MODULE$;
            IterableFactory.ToFactory var13 = new IterableFactory.ToFactory(toFactory_factory);
            toFactory_factory = null;
            this.prependAll((IterableOnce)it.to(var13));
         } else if (var4 + n >= this.array().length) {
            int finalLength = var4 + n;
            Object[] array2 = ArrayDeque$.MODULE$.alloc(finalLength);
            it.copyToArray(array2);
            this.copySliceToArray(0, array2, var4, n);
            this.reset(array2, 0, finalLength);
         } else {
            for(int i = 0; i < var4; ++i) {
               int var10000 = i - var4;
               Object _set_elem = it.next();
               int _set_idx = var10000;
               this.array()[this.start_$plus(_set_idx)] = _set_elem;
               _set_elem = null;
            }

            this.scala$collection$mutable$ArrayDeque$$start_$eq(this.scala$collection$mutable$ArrayDeque$$start() - var4 & this.array().length - 1);
         }
      }

      return this;
   }

   public ArrayDeque addAll(final IterableOnce elems) {
      int var2 = elems.knownSize();
      switch (var2) {
         default:
            if (var2 > 0) {
               this.ensureSize(var2 + this.length());
               elems.iterator().foreach((elem) -> this.scala$collection$mutable$ArrayDeque$$appendAssumingCapacity(elem));
            } else {
               elems.iterator().foreach((elem) -> (ArrayDeque)this.$plus$eq(elem));
            }

            return this;
      }
   }

   public void insert(final int idx, final Object elem) {
      int requireBounds_until = this.length() + 1;
      if (idx >= 0 && idx < requireBounds_until) {
         int n = this.length();
         if (idx == 0) {
            this.prepend(elem);
         } else if (idx == n) {
            this.addOne(elem);
         } else {
            int finalLength = n + 1;
            if (finalLength >= this.array().length) {
               Object[] array2 = ArrayDeque$.MODULE$.alloc(finalLength);
               this.copySliceToArray(0, array2, 0, idx);
               array2[idx] = elem;
               this.copySliceToArray(idx, array2, idx + 1, n);
               this.reset(array2, 0, finalLength);
            } else if (n <= idx * 2) {
               int i;
               for(i = n - 1; i >= idx; --i) {
                  int var18 = i + 1;
                  Object _set_elem = this.array()[this.start_$plus(i)];
                  int _set_idx = var18;
                  this.array()[this.start_$plus(_set_idx)] = _set_elem;
                  _set_elem = null;
               }

               int scala$collection$mutable$ArrayDeque$$end_$plus_idx = 1;
               this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() + scala$collection$mutable$ArrayDeque$$end_$plus_idx & this.array().length - 1);
               ++i;
               this.array()[this.start_$plus(i)] = elem;
            } else {
               int i;
               for(i = 0; i < idx; ++i) {
                  int var10000 = i - 1;
                  Object _set_elem = this.array()[this.start_$plus(i)];
                  int _set_idx = var10000;
                  this.array()[this.start_$plus(_set_idx)] = _set_elem;
                  _set_elem = null;
               }

               int scala$collection$mutable$ArrayDeque$$start_$minus_idx = 1;
               this.scala$collection$mutable$ArrayDeque$$start_$eq(this.scala$collection$mutable$ArrayDeque$$start() - scala$collection$mutable$ArrayDeque$$start_$minus_idx & this.array().length - 1);
               this.array()[this.start_$plus(i)] = elem;
            }
         }
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, requireBounds_until - 1);
      }
   }

   public void insertAll(final int idx, final IterableOnce elems) {
      int requireBounds_until = this.length() + 1;
      if (idx >= 0 && idx < requireBounds_until) {
         int n = this.length();
         if (idx == 0) {
            this.prependAll(elems);
         } else if (idx == n) {
            this.addAll(elems);
         } else {
            int _srcLength = elems.knownSize();
            Iterator var10000;
            int var10001;
            if (_srcLength >= 0) {
               var10000 = elems.iterator();
               var10001 = _srcLength;
            } else {
               IndexedSeq indexed = (IndexedSeq)IndexedSeq$.MODULE$.from(elems);
               var10000 = indexed.iterator();
               var10001 = indexed.length();
            }

            int var18 = var10001;
            Iterator it = var10000;
            if (it.nonEmpty()) {
               int finalLength = var18 + n;
               if (finalLength >= this.array().length) {
                  Object[] array2 = ArrayDeque$.MODULE$.alloc(finalLength);
                  this.copySliceToArray(0, array2, 0, idx);
                  it.copyToArray(array2, idx);
                  this.copySliceToArray(idx, array2, idx + var18, n);
                  this.reset(array2, 0, finalLength);
               } else if (2 * idx >= n) {
                  int i;
                  for(i = n - 1; i >= idx; --i) {
                     int var24 = i + var18;
                     Object _set_elem = this.array()[this.start_$plus(i)];
                     int _set_idx = var24;
                     this.array()[this.start_$plus(_set_idx)] = _set_elem;
                     _set_elem = null;
                  }

                  this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() + var18 & this.array().length - 1);

                  while(it.hasNext()) {
                     ++i;
                     Object _set_elem = it.next();
                     this.array()[this.start_$plus(i)] = _set_elem;
                     _set_elem = null;
                  }

               } else {
                  int i;
                  for(i = 0; i < idx; ++i) {
                     int var23 = i - var18;
                     Object _set_elem = this.array()[this.start_$plus(i)];
                     int _set_idx = var23;
                     this.array()[this.start_$plus(_set_idx)] = _set_elem;
                     _set_elem = null;
                  }

                  this.scala$collection$mutable$ArrayDeque$$start_$eq(this.scala$collection$mutable$ArrayDeque$$start() - var18 & this.array().length - 1);

                  while(it.hasNext()) {
                     Object _set_elem = it.next();
                     this.array()[this.start_$plus(i)] = _set_elem;
                     _set_elem = null;
                     ++i;
                  }

               }
            }
         }
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, requireBounds_until - 1);
      }
   }

   public void remove(final int idx, final int count) {
      if (count <= 0) {
         if (count != 0) {
            throw new IllegalArgumentException((new java.lang.StringBuilder(20)).append("requirement failed: ").append($anonfun$remove$1(count)).toString());
         }
      } else {
         int requireBounds_until = this.length();
         if (idx >= 0 && idx < requireBounds_until) {
            int n = this.length();
            int removals = Math.min(n - idx, count);
            int finalLength = n - removals;
            int suffixStart = idx + removals;
            if (this.array().length > 128 && this.array().length - finalLength - (finalLength >> 1) > finalLength) {
               Object[] array2 = ArrayDeque$.MODULE$.alloc(finalLength);
               this.copySliceToArray(0, array2, 0, idx);
               this.copySliceToArray(suffixStart, array2, idx, n);
               this.reset(array2, 0, finalLength);
            } else if (2 * idx <= finalLength) {
               int i;
               for(i = suffixStart - 1; i >= removals; --i) {
                  int _get_idx = i - removals;
                  Object _set_elem = this.array()[this.start_$plus(_get_idx)];
                  this.array()[this.start_$plus(i)] = _set_elem;
                  _set_elem = null;
               }

               while(i >= 0) {
                  this.array()[this.start_$plus(i)] = null;
                  --i;
               }

               this.scala$collection$mutable$ArrayDeque$$start_$eq(this.start_$plus(removals));
            } else {
               int i;
               for(i = idx; i < finalLength; ++i) {
                  int _get_idx = i + removals;
                  Object _set_elem = this.array()[this.start_$plus(_get_idx)];
                  this.array()[this.start_$plus(i)] = _set_elem;
                  _set_elem = null;
               }

               while(i < n) {
                  this.array()[this.start_$plus(i)] = null;
                  ++i;
               }

               this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() - removals & this.array().length - 1);
            }
         } else {
            throw CommonErrors$.MODULE$.indexOutOfBounds(idx, requireBounds_until - 1);
         }
      }
   }

   public Object remove(final int idx) {
      Object elem = this.apply(idx);
      this.remove(idx, 1);
      return elem;
   }

   public ArrayDeque subtractOne(final Object elem) {
      int idx = this.indexOf(elem);
      if (idx >= 0) {
         this.remove(idx, 1);
      }

      return this;
   }

   public Option removeHeadOption(final boolean resizeInternalRepr) {
      if (this.isEmpty()) {
         return None$.MODULE$;
      } else {
         Some var10000 = new Some;
         Object removeHeadAssumingNonEmpty_elem = this.array()[this.scala$collection$mutable$ArrayDeque$$start()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$start()] = null;
         this.scala$collection$mutable$ArrayDeque$$start_$eq(this.start_$plus(1));
         if (resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }

         Object var10002 = removeHeadAssumingNonEmpty_elem;
         removeHeadAssumingNonEmpty_elem = null;
         var10000.<init>(var10002);
         return var10000;
      }
   }

   public Object removeHead(final boolean resizeInternalRepr) {
      if (this.isEmpty()) {
         throw new NoSuchElementException("empty collection");
      } else {
         Object removeHeadAssumingNonEmpty_elem = this.array()[this.scala$collection$mutable$ArrayDeque$$start()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$start()] = null;
         this.scala$collection$mutable$ArrayDeque$$start_$eq(this.start_$plus(1));
         if (resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }

         return removeHeadAssumingNonEmpty_elem;
      }
   }

   public boolean removeHeadOption$default$1() {
      return false;
   }

   public boolean removeHead$default$1() {
      return false;
   }

   private Object removeHeadAssumingNonEmpty(final boolean resizeInternalRepr) {
      Object elem = this.array()[this.scala$collection$mutable$ArrayDeque$$start()];
      this.array()[this.scala$collection$mutable$ArrayDeque$$start()] = null;
      this.scala$collection$mutable$ArrayDeque$$start_$eq(this.start_$plus(1));
      if (resizeInternalRepr) {
         this.scala$collection$mutable$ArrayDeque$$resize(this.length());
      }

      return elem;
   }

   private boolean removeHeadAssumingNonEmpty$default$1() {
      return false;
   }

   public Option removeLastOption(final boolean resizeInternalRepr) {
      if (this.isEmpty()) {
         return None$.MODULE$;
      } else {
         Some var10000 = new Some;
         int removeLastAssumingNonEmpty_scala$collection$mutable$ArrayDeque$$end_$minus_idx = 1;
         this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() - removeLastAssumingNonEmpty_scala$collection$mutable$ArrayDeque$$end_$minus_idx & this.array().length - 1);
         Object removeLastAssumingNonEmpty_elem = this.array()[this.scala$collection$mutable$ArrayDeque$$end()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$end()] = null;
         if (resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }

         Object var10002 = removeLastAssumingNonEmpty_elem;
         removeLastAssumingNonEmpty_elem = null;
         var10000.<init>(var10002);
         return var10000;
      }
   }

   public Object removeLast(final boolean resizeInternalRepr) {
      if (this.isEmpty()) {
         throw new NoSuchElementException("empty collection");
      } else {
         int removeLastAssumingNonEmpty_scala$collection$mutable$ArrayDeque$$end_$minus_idx = 1;
         this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() - removeLastAssumingNonEmpty_scala$collection$mutable$ArrayDeque$$end_$minus_idx & this.array().length - 1);
         Object removeLastAssumingNonEmpty_elem = this.array()[this.scala$collection$mutable$ArrayDeque$$end()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$end()] = null;
         if (resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }

         return removeLastAssumingNonEmpty_elem;
      }
   }

   public boolean removeLastOption$default$1() {
      return false;
   }

   public boolean removeLast$default$1() {
      return false;
   }

   private Object removeLastAssumingNonEmpty(final boolean resizeInternalRepr) {
      int scala$collection$mutable$ArrayDeque$$end_$minus_idx = 1;
      this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() - scala$collection$mutable$ArrayDeque$$end_$minus_idx & this.array().length - 1);
      Object elem = this.array()[this.scala$collection$mutable$ArrayDeque$$end()];
      this.array()[this.scala$collection$mutable$ArrayDeque$$end()] = null;
      if (resizeInternalRepr) {
         this.scala$collection$mutable$ArrayDeque$$resize(this.length());
      }

      return elem;
   }

   private boolean removeLastAssumingNonEmpty$default$1() {
      return false;
   }

   public scala.collection.immutable.Seq removeAll() {
      Builder elems = scala.collection.immutable.Seq$.MODULE$.newBuilder();
      elems.sizeHint(this.length());

      while(this.nonEmpty()) {
         boolean removeHeadAssumingNonEmpty_resizeInternalRepr = false;
         Object removeHeadAssumingNonEmpty_elem = this.array()[this.scala$collection$mutable$ArrayDeque$$start()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$start()] = null;
         this.scala$collection$mutable$ArrayDeque$$start_$eq(this.start_$plus(1));
         if (removeHeadAssumingNonEmpty_resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }

         Object var10000 = removeHeadAssumingNonEmpty_elem;
         removeHeadAssumingNonEmpty_elem = null;
         Object $plus$eq_elem = var10000;
         elems.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return (scala.collection.immutable.Seq)elems.result();
   }

   public scala.collection.immutable.Seq removeAllReverse() {
      Builder elems = scala.collection.immutable.Seq$.MODULE$.newBuilder();
      elems.sizeHint(this.length());

      while(this.nonEmpty()) {
         boolean removeLastAssumingNonEmpty_resizeInternalRepr = false;
         int removeLastAssumingNonEmpty_scala$collection$mutable$ArrayDeque$$end_$minus_idx = 1;
         this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() - removeLastAssumingNonEmpty_scala$collection$mutable$ArrayDeque$$end_$minus_idx & this.array().length - 1);
         Object removeLastAssumingNonEmpty_elem = this.array()[this.scala$collection$mutable$ArrayDeque$$end()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$end()] = null;
         if (removeLastAssumingNonEmpty_resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }

         Object var10000 = removeLastAssumingNonEmpty_elem;
         removeLastAssumingNonEmpty_elem = null;
         Object $plus$eq_elem = var10000;
         elems.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return (scala.collection.immutable.Seq)elems.result();
   }

   public scala.collection.immutable.Seq removeHeadWhile(final Function1 f) {
      Builder elems = scala.collection.immutable.Seq$.MODULE$.newBuilder();

      while(true) {
         Option var10000 = this.headOption();
         if (var10000 == null) {
            throw null;
         }

         Option exists_this = var10000;
         boolean var10 = !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(f.apply(exists_this.get()));
         Object var7 = null;
         if (!var10) {
            return (scala.collection.immutable.Seq)elems.result();
         }

         boolean removeHeadAssumingNonEmpty_resizeInternalRepr = false;
         Object removeHeadAssumingNonEmpty_elem = this.array()[this.scala$collection$mutable$ArrayDeque$$start()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$start()] = null;
         this.scala$collection$mutable$ArrayDeque$$start_$eq(this.start_$plus(1));
         if (removeHeadAssumingNonEmpty_resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }

         Object var10001 = removeHeadAssumingNonEmpty_elem;
         removeHeadAssumingNonEmpty_elem = null;
         Object $plus$eq_elem = var10001;
         if (elems == null) {
            throw null;
         }

         elems.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }
   }

   public scala.collection.immutable.Seq removeLastWhile(final Function1 f) {
      Builder elems = scala.collection.immutable.Seq$.MODULE$.newBuilder();

      while(true) {
         Option var10000 = this.lastOption();
         if (var10000 == null) {
            throw null;
         }

         Option exists_this = var10000;
         boolean var11 = !exists_this.isEmpty() && BoxesRunTime.unboxToBoolean(f.apply(exists_this.get()));
         Object var8 = null;
         if (!var11) {
            return (scala.collection.immutable.Seq)elems.result();
         }

         boolean removeLastAssumingNonEmpty_resizeInternalRepr = false;
         int removeLastAssumingNonEmpty_scala$collection$mutable$ArrayDeque$$end_$minus_idx = 1;
         this.scala$collection$mutable$ArrayDeque$$end_$eq(this.scala$collection$mutable$ArrayDeque$$end() - removeLastAssumingNonEmpty_scala$collection$mutable$ArrayDeque$$end_$minus_idx & this.array().length - 1);
         Object removeLastAssumingNonEmpty_elem = this.array()[this.scala$collection$mutable$ArrayDeque$$end()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$end()] = null;
         if (removeLastAssumingNonEmpty_resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }

         Object var10001 = removeLastAssumingNonEmpty_elem;
         removeLastAssumingNonEmpty_elem = null;
         Object $plus$eq_elem = var10001;
         if (elems == null) {
            throw null;
         }

         elems.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }
   }

   public Option removeFirst(final Function1 p, final int from) {
      int i = this.indexWhere(p, from);
      return (Option)(i < 0 ? None$.MODULE$ : new Some(this.remove(i)));
   }

   public int removeFirst$default$2() {
      return 0;
   }

   public scala.collection.immutable.Seq removeAll(final Function1 p) {
      Builder res = scala.collection.immutable.Seq$.MODULE$.newBuilder();
      int i = 0;

      int j;
      for(j = 0; i < this.length(); ++i) {
         if (BoxesRunTime.unboxToBoolean(p.apply(this.apply(i)))) {
            Object $plus$eq_elem = this.apply(i);
            if (res == null) {
               throw null;
            }

            res.addOne($plus$eq_elem);
            $plus$eq_elem = null;
         } else {
            if (i != j) {
               this.update(j, this.apply(i));
            }

            ++j;
         }
      }

      if (i != j) {
         this.takeInPlace(j);
      }

      return (scala.collection.immutable.Seq)res.result();
   }

   public void ensureSize(final int hint) {
      if (hint > this.length() && hint >= this.array().length) {
         this.scala$collection$mutable$ArrayDeque$$resize(hint);
      }
   }

   public int length() {
      int scala$collection$mutable$ArrayDeque$$end_$minus_idx = this.scala$collection$mutable$ArrayDeque$$start();
      return this.scala$collection$mutable$ArrayDeque$$end() - scala$collection$mutable$ArrayDeque$$end_$minus_idx & this.array().length - 1;
   }

   public boolean isEmpty() {
      return this.scala$collection$mutable$ArrayDeque$$start() == this.scala$collection$mutable$ArrayDeque$$end();
   }

   public ArrayDeque klone() {
      return new ArrayDeque(this.array().clone(), this.scala$collection$mutable$ArrayDeque$$start(), this.scala$collection$mutable$ArrayDeque$$end());
   }

   public SeqFactory iterableFactory() {
      return ArrayDeque$.MODULE$;
   }

   public void clear() {
      while(this.nonEmpty()) {
         boolean removeHeadAssumingNonEmpty_resizeInternalRepr = false;
         Object var10000 = this.array()[this.scala$collection$mutable$ArrayDeque$$start()];
         this.array()[this.scala$collection$mutable$ArrayDeque$$start()] = null;
         this.scala$collection$mutable$ArrayDeque$$start_$eq(this.start_$plus(1));
         if (removeHeadAssumingNonEmpty_resizeInternalRepr) {
            this.scala$collection$mutable$ArrayDeque$$resize(this.length());
         }
      }

   }

   public ArrayDeque clearAndShrink(final int size) {
      this.reset(ArrayDeque$.MODULE$.alloc(size), 0, 0);
      return this;
   }

   public int clearAndShrink$default$1() {
      return 16;
   }

   public ArrayDeque ofArray(final Object[] array, final int end) {
      return new ArrayDeque(array, 0, end);
   }

   public int copyToArray(final Object dest, final int destStart, final int len) {
      IterableOnce$ var10000 = IterableOnce$.MODULE$;
      int var7 = this.length();
      int elemsToCopyToArray_destLen = Array.getLength(dest);
      int elemsToCopyToArray_srcLen = var7;
      scala.math.package$ var8 = scala.math.package$.MODULE$;
      var8 = scala.math.package$.MODULE$;
      var8 = scala.math.package$.MODULE$;
      int copied = Math.max(Math.min(Math.min(len, elemsToCopyToArray_srcLen), elemsToCopyToArray_destLen - destStart), 0);
      if (copied > 0) {
         this.copySliceToArray(0, dest, destStart, len);
      }

      return copied;
   }

   public Object toArray(final ClassTag evidence$1) {
      return this.copySliceToArray(0, evidence$1.newArray(this.length()), 0, this.length());
   }

   public void trimToSize() {
      this.scala$collection$mutable$ArrayDeque$$resize(this.length());
   }

   public int start_$plus(final int idx) {
      return this.scala$collection$mutable$ArrayDeque$$start() + idx & this.array().length - 1;
   }

   public int scala$collection$mutable$ArrayDeque$$start_$minus(final int idx) {
      return this.scala$collection$mutable$ArrayDeque$$start() - idx & this.array().length - 1;
   }

   public int scala$collection$mutable$ArrayDeque$$end_$plus(final int idx) {
      return this.scala$collection$mutable$ArrayDeque$$end() + idx & this.array().length - 1;
   }

   public int scala$collection$mutable$ArrayDeque$$end_$minus(final int idx) {
      return this.scala$collection$mutable$ArrayDeque$$end() - idx & this.array().length - 1;
   }

   public boolean scala$collection$mutable$ArrayDeque$$mustGrow(final int len) {
      return len >= this.array().length;
   }

   private boolean shouldShrink(final int len) {
      return this.array().length > 128 && this.array().length - len - (len >> 1) > len;
   }

   private boolean canShrink(final int len) {
      return this.array().length > 16 && this.array().length - len > len;
   }

   private Object _get(final int idx) {
      return this.array()[this.start_$plus(idx)];
   }

   private void _set(final int idx, final Object elem) {
      this.array()[this.start_$plus(idx)] = elem;
   }

   public void scala$collection$mutable$ArrayDeque$$resize(final int len) {
      if (len >= this.array().length || this.array().length > 16 && this.array().length - len > len) {
         int n = this.length();
         Object[] array2 = this.copySliceToArray(0, ArrayDeque$.MODULE$.alloc(len), 0, n);
         this.reset(array2, 0, n);
      }
   }

   public String stringPrefix() {
      return "ArrayDeque";
   }

   // $FF: synthetic method
   public static final String $anonfun$reset$1() {
      return "Array.length must be power of 2";
   }

   // $FF: synthetic method
   public static final String $anonfun$remove$1(final int count$1) {
      return (new java.lang.StringBuilder(38)).append("removing negative number of elements: ").append(count$1).toString();
   }

   public ArrayDeque(final Object[] array, final int start, final int end) {
      this.array = array;
      this.scala$collection$mutable$ArrayDeque$$start = start;
      this.scala$collection$mutable$ArrayDeque$$end = end;
      super();
      this.reset(this.array(), this.scala$collection$mutable$ArrayDeque$$start(), this.scala$collection$mutable$ArrayDeque$$end());
   }

   public ArrayDeque(final int initialSize) {
      this(ArrayDeque$.MODULE$.alloc(initialSize), 0, 0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
