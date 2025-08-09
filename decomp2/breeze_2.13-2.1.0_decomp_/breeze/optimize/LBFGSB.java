package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.LU$primitive$LU_DM_Impl_Double$;
import breeze.linalg.NumericOps;
import breeze.linalg.SliceVector;
import breeze.linalg.SliceVector$;
import breeze.linalg.Vector;
import breeze.linalg.Vector$;
import breeze.linalg.diag$;
import breeze.linalg.inv$;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.Semiring$;
import breeze.storage.Zero$;
import breeze.util.Isomorphism;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple5;
import scala.Predef.ArrowAssoc.;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\r}b\u0001B#G\u0001-C\u0001\"\u001a\u0001\u0003\u0002\u0003\u0006I\u0001\u0015\u0005\tM\u0002\u0011\t\u0011)A\u0005!\"Aq\r\u0001B\u0001B\u0003%\u0001\u000e\u0003\u0005l\u0001\t\u0005\t\u0015!\u0003i\u0011!a\u0007A!A!\u0002\u00131\u0006\u0002C7\u0001\u0005\u0003\u0005\u000b\u0011\u00025\t\u00119\u0004!\u0011!Q\u0001\n!DQa\u001c\u0001\u0005\u0002ADq!\u001f\u0001C\u0002\u0013E!\u0010\u0003\u0004|\u0001\u0001\u0006IA\u0016\u0004\u0005y\u0002\u0001U\u0010C\u0005\u0002\"-\u0011)\u001a!C\u0001u\"I\u00111E\u0006\u0003\u0012\u0003\u0006IA\u0016\u0005\u000b\u0003KY!Q3A\u0005\u0002\u0005\u001d\u0002BCA\u0018\u0017\tE\t\u0015!\u0003\u0002*!Q\u0011\u0011G\u0006\u0003\u0016\u0004%\t!a\n\t\u0015\u0005M2B!E!\u0002\u0013\tI\u0003\u0003\u0006\u00026-\u0011)\u001a!C\u0001\u0003OA!\"a\u000e\f\u0005#\u0005\u000b\u0011BA\u0015\u0011)\tId\u0003BK\u0002\u0013\u0005\u0011q\u0005\u0005\u000b\u0003wY!\u0011#Q\u0001\n\u0005%\u0002BB8\f\t\u0003\ti\u0004C\u0005\u0002N-\t\t\u0011\"\u0001\u0002P!I\u00111L\u0006\u0012\u0002\u0013\u0005\u0011Q\f\u0005\n\u0003gZ\u0011\u0013!C\u0001\u0003kB\u0011\"!\u001f\f#\u0003%\t!!\u001e\t\u0013\u0005m4\"%A\u0005\u0002\u0005U\u0004\"CA?\u0017E\u0005I\u0011AA;\u0011%\tyhCA\u0001\n\u0003\n\t\tC\u0005\u0002\u0014.\t\t\u0011\"\u0001\u0002\u0016\"I\u0011qS\u0006\u0002\u0002\u0013\u0005\u0011\u0011\u0014\u0005\n\u0003K[\u0011\u0011!C!\u0003OC\u0011\"!.\f\u0003\u0003%\t!a.\t\u0013\u0005\u00057\"!A\u0005B\u0005\r\u0007\"CAd\u0017\u0005\u0005I\u0011IAe\u0011%\tYmCA\u0001\n\u0003\ni\rC\u0005\u0002P.\t\t\u0011\"\u0011\u0002R\u001eI\u0011Q\u001b\u0001\u0002\u0002#\u0005\u0011q\u001b\u0004\ty\u0002\t\t\u0011#\u0001\u0002Z\"1qn\nC\u0001\u0003cD\u0011\"a3(\u0003\u0003%)%!4\t\u0013\u0005Mx%!A\u0005\u0002\u0006U\b\"\u0003B\u0001O\u0005\u0005I\u0011\u0011B\u0002\u0011\u001d\u0011)\u0002\u0001C)\u0005/AqA!\t\u0001\t#\u0012\u0019\u0003C\u0004\u0003>\u0001!\tFa\u0010\t\u000f\t\u001d\u0003\u0001\"\u0015\u0003J!9!1\u000b\u0001\u0005R\tU\u0003b\u0002B1\u0001\u0011\u0005!1\r\u0005\b\u0005_\u0002A\u0011\u0002B9\u0011\u001d\u0011I\b\u0001C\t\u0005wBqAa$\u0001\t#\u0011\t\nC\u0004\u0003,\u0002!\tB!,\t\u000f\tm\u0006\u0001\"\u0005\u0003>\u001e9!\u0011\u001a$\t\u0002\t-gAB#G\u0011\u0003\u0011i\r\u0003\u0004pq\u0011\u0005!q\u001a\u0005\b\u0005#DD\u0011\u0001Bj\u0011!\u0011\u0019\u0010\u000fb\u0001\n#Q\bb\u0002B{q\u0001\u0006IA\u0016\u0005\b\u0005oDD\u0011\u0003B}\u0011\u001d\u0011y\u0010\u000fC\u0005\u0007\u0003A\u0011ba\n9#\u0003%\ta!\u000b\t\u0013\r5\u0002(%A\u0005\u0002\r%\u0002\"CB\u0018qE\u0005I\u0011AA/\u0011%\u0019\t\u0004OI\u0001\n\u0003\u0019I\u0003C\u0005\u00044a\n\n\u0011\"\u0001\u0004*!I1Q\u0007\u001d\u0002\u0002\u0013%1q\u0007\u0002\u0007\u0019\n3ui\u0015\"\u000b\u0005\u001dC\u0015\u0001C8qi&l\u0017N_3\u000b\u0003%\u000baA\u0019:fKj,7\u0001A\n\u0004\u00011{\u0006\u0003B'O!rk\u0011AR\u0005\u0003\u001f\u001a\u00131CR5sgR|%\u000fZ3s\u001b&t\u0017.\\5{KJ\u00042!\u0015+W\u001b\u0005\u0011&BA*I\u0003\u0019a\u0017N\\1mO&\u0011QK\u0015\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002X56\t\u0001LC\u0001Z\u0003\u0015\u00198-\u00197b\u0013\tY\u0006L\u0001\u0004E_V\u0014G.\u001a\t\u0004\u001bv\u0003\u0016B\u00010G\u00051!\u0015N\u001a4Gk:\u001cG/[8o!\t\u00017-D\u0001b\u0015\t\u0011\u0007*\u0001\u0003vi&d\u0017B\u00013b\u0005M\u0019VM]5bY&T\u0018M\u00197f\u0019><w-\u001b8h\u0003-awn^3s\u0005>,h\u000eZ:\u0002\u0017U\u0004\b/\u001a:C_VtGm]\u0001\b[\u0006D\u0018\n^3s!\t9\u0016.\u0003\u0002k1\n\u0019\u0011J\u001c;\u0002\u00035\f\u0011\u0002^8mKJ\fgnY3\u0002\u00175\f\u0007PW8p[&#XM]\u0001\u0012[\u0006DH*\u001b8f'\u0016\f'o\u00195Ji\u0016\u0014\u0018A\u0002\u001fj]&$h\b\u0006\u0005reN$XO^<y!\ti\u0005\u0001C\u0003f\u0011\u0001\u0007\u0001\u000bC\u0003g\u0011\u0001\u0007\u0001\u000bC\u0004h\u0011A\u0005\t\u0019\u00015\t\u000f-D\u0001\u0013!a\u0001Q\"9A\u000e\u0003I\u0001\u0002\u00041\u0006bB7\t!\u0003\u0005\r\u0001\u001b\u0005\b]\"\u0001\n\u00111\u0001i\u0003\r)\u0005kU\u000b\u0002-\u0006!Q\tU*!\u0005\u001dA\u0015n\u001d;pef\u001cba\u0003@\u0002\u0004\u0005%\u0001CA,\u0000\u0013\r\t\t\u0001\u0017\u0002\u0007\u0003:L(+\u001a4\u0011\u0007]\u000b)!C\u0002\u0002\ba\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002\f\u0005ma\u0002BA\u0007\u0003/qA!a\u0004\u0002\u00165\u0011\u0011\u0011\u0003\u0006\u0004\u0003'Q\u0015A\u0002\u001fs_>$h(C\u0001Z\u0013\r\tI\u0002W\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ti\"a\b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0007\u0005e\u0001,A\u0003uQ\u0016$\u0018-\u0001\u0004uQ\u0016$\u0018\rI\u0001\u0002/V\u0011\u0011\u0011\u0006\t\u0005#\u0006-b+C\u0002\u0002.I\u00131\u0002R3og\u0016l\u0015\r\u001e:jq\u0006\u0011q\u000bI\u0001\u0002\u001b\u0006\u0011Q\nI\u0001\ts\"K7\u000f^8ss\u0006I\u0011\u0010S5ti>\u0014\u0018\u0010I\u0001\tg\"K7\u000f^8ss\u0006I1\u000fS5ti>\u0014\u0018\u0010\t\u000b\r\u0003\u007f\t\u0019%!\u0012\u0002H\u0005%\u00131\n\t\u0004\u0003\u0003ZQ\"\u0001\u0001\t\r\u0005\u0005b\u00031\u0001W\u0011\u001d\t)C\u0006a\u0001\u0003SAq!!\r\u0017\u0001\u0004\tI\u0003C\u0004\u00026Y\u0001\r!!\u000b\t\u000f\u0005eb\u00031\u0001\u0002*\u0005!1m\u001c9z)1\ty$!\u0015\u0002T\u0005U\u0013qKA-\u0011!\t\tc\u0006I\u0001\u0002\u00041\u0006\"CA\u0013/A\u0005\t\u0019AA\u0015\u0011%\t\td\u0006I\u0001\u0002\u0004\tI\u0003C\u0005\u00026]\u0001\n\u00111\u0001\u0002*!I\u0011\u0011H\f\u0011\u0002\u0003\u0007\u0011\u0011F\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyFK\u0002W\u0003CZ#!a\u0019\u0011\t\u0005\u0015\u0014qN\u0007\u0003\u0003ORA!!\u001b\u0002l\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003[B\u0016AC1o]>$\u0018\r^5p]&!\u0011\u0011OA4\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t9H\u000b\u0003\u0002*\u0005\u0005\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAB!\u0011\t))a$\u000e\u0005\u0005\u001d%\u0002BAE\u0003\u0017\u000bA\u0001\\1oO*\u0011\u0011QR\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0012\u0006\u001d%AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001i\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a'\u0002\"B\u0019q+!(\n\u0007\u0005}\u0005LA\u0002B]fD\u0001\"a) \u0003\u0003\u0005\r\u0001[\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005%\u0006CBAV\u0003c\u000bY*\u0004\u0002\u0002.*\u0019\u0011q\u0016-\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00024\u00065&\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!/\u0002@B\u0019q+a/\n\u0007\u0005u\u0006LA\u0004C_>dW-\u00198\t\u0013\u0005\r\u0016%!AA\u0002\u0005m\u0015A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a!\u0002F\"A\u00111\u0015\u0012\u0002\u0002\u0003\u0007\u0001.\u0001\u0005iCND7i\u001c3f)\u0005A\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\r\u0015AB3rk\u0006d7\u000f\u0006\u0003\u0002:\u0006M\u0007\"CARK\u0005\u0005\t\u0019AAN\u0003\u001dA\u0015n\u001d;pef\u00042!!\u0011('\u00159\u00131\\At!=\ti.a9W\u0003S\tI#!\u000b\u0002*\u0005}RBAAp\u0015\r\t\t\u000fW\u0001\beVtG/[7f\u0013\u0011\t)/a8\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tW\u0007\u0005\u0003\u0002j\u0006=XBAAv\u0015\u0011\ti/a#\u0002\u0005%|\u0017\u0002BA\u000f\u0003W$\"!a6\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0019\u0005}\u0012q_A}\u0003w\fi0a@\t\r\u0005\u0005\"\u00061\u0001W\u0011\u001d\t)C\u000ba\u0001\u0003SAq!!\r+\u0001\u0004\tI\u0003C\u0004\u00026)\u0002\r!!\u000b\t\u000f\u0005e\"\u00061\u0001\u0002*\u00059QO\\1qa2LH\u0003\u0002B\u0003\u0005#\u0001Ra\u0016B\u0004\u0005\u0017I1A!\u0003Y\u0005\u0019y\u0005\u000f^5p]BaqK!\u0004W\u0003S\tI#!\u000b\u0002*%\u0019!q\u0002-\u0003\rQ+\b\u000f\\36\u0011%\u0011\u0019bKA\u0001\u0002\u0004\ty$A\u0002yIA\na\"\u001b8ji&\fG\u000eS5ti>\u0014\u0018\u0010\u0006\u0004\u0002@\te!Q\u0004\u0005\u0007\u00057a\u0003\u0019\u0001/\u0002\u0003\u0019DaAa\b-\u0001\u0004\u0001\u0016\u0001B5oSR\fQ\"\u001e9eCR,\u0007*[:u_JLH\u0003DA \u0005K\u0011IC!\f\u00032\tM\u0002B\u0002B\u0014[\u0001\u0007\u0001+\u0001\u0003oK^D\u0006B\u0002B\u0016[\u0001\u0007\u0001+A\u0004oK^<%/\u00193\t\r\t=R\u00061\u0001W\u0003\u0019qWm\u001e,bY\"1!1D\u0017A\u0002qCqA!\u000e.\u0001\u0004\u00119$\u0001\u0005pY\u0012\u001cF/\u0019;f!\u0011\t\tE!\u000f\n\u0007\tmbJA\u0003Ti\u0006$X-\u0001\fdQ>|7/\u001a#fg\u000e,g\u000e\u001e#je\u0016\u001cG/[8o)\u0015\u0001&\u0011\tB#\u0011\u001d\u0011\u0019E\fa\u0001\u0005o\tQa\u001d;bi\u0016DaAa\u0007/\u0001\u0004a\u0016!\u00053fi\u0016\u0014X.\u001b8f'R,\u0007oU5{KR9aKa\u0013\u0003N\t=\u0003b\u0002B\"_\u0001\u0007!q\u0007\u0005\u0007\u00057y\u0003\u0019\u0001/\t\r\tEs\u00061\u0001Q\u0003%!\u0017N]3di&|g.\u0001\u0005uC.,7\u000b^3q)\u001d\u0001&q\u000bB-\u0005;BqAa\u00111\u0001\u0004\u00119\u0004\u0003\u0004\u0003\\A\u0002\r\u0001U\u0001\u0004I&\u0014\bB\u0002B0a\u0001\u0007a+\u0001\u0005ti\u0016\u00048+\u001b>f\u0003E\tGM[;ti^KG\u000f[5o\u0005>,h\u000e\u001a\u000b\u0005\u0005K\u0012Y\u0007E\u0002X\u0005OJ1A!\u001bY\u0005\u0011)f.\u001b;\t\r\t5\u0014\u00071\u0001Q\u0003\u0015\u0001x.\u001b8u\u0003)Ig.\u001b;jC2L'0\u001a\u000b\u0007\u0003\u007f\u0011\u0019H!\u001e\t\r\tm!\u00071\u0001]\u0011\u0019\u00119H\ra\u0001!\u0006\u0011\u0001\u0010M\u0001\u001aO\u0016$x)\u001a8fe\u0006d\u0017N_3e\u0007\u0006,8\r[=Q_&tG\u000f\u0006\u0005\u0003~\t\r%q\u0011BF!\u00159&q\u0010)Q\u0013\r\u0011\t\t\u0017\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\t\u00155\u00071\u0001\u0002@\u00059\u0001.[:u_JL\bB\u0002BEg\u0001\u0007\u0001+A\u0001y\u0011\u0019\u0011ii\ra\u0001!\u0006\tq-A\u0005gS:$\u0017\t\u001c9iCR9aKa%\u0003\u0018\n\u0005\u0006B\u0002BKi\u0001\u0007\u0001+A\u0004y\u0007\u0006,8\r[=\t\u000f\teE\u00071\u0001\u0003\u001c\u0006\u0011A-\u001e\t\u0005#\nue+C\u0002\u0003 J\u0013aAV3di>\u0014\bb\u0002BRi\u0001\u0007!QU\u0001\rMJ,WMV1s\u0013:$W\r\u001f\t\u0005/\n\u001d\u0006.C\u0002\u0003*b\u0013Q!\u0011:sCf\fAc];cgB\f7-Z'j]&l\u0017N_1uS>tGc\u0003)\u00030\nE&1\u0017B[\u0005sCqA!\"6\u0001\u0004\ty\u0004\u0003\u0004\u0003\u0016V\u0002\r\u0001\u0015\u0005\u0007\u0005\u0013+\u0004\u0019\u0001)\t\r\t]V\u00071\u0001Q\u0003\u0005\u0019\u0007B\u0002BGk\u0001\u0007\u0001+\u0001\u000eva\u0012\fG/Z*l3.DUm]:jC:\f\u0005\u000f\u001d:pq6\u000bG\u000f\u0006\u0005\u0002@\t}&\u0011\u0019Bc\u0011\u001d\u0011)I\u000ea\u0001\u0003\u007fAaAa17\u0001\u0004\u0001\u0016\u0001\u00028foNCaAa27\u0001\u0004\u0001\u0016\u0001\u00028fof\u000ba\u0001\u0014\"G\u000fN\u0013\u0005CA'9'\u0011Ad0a:\u0015\u0005\t-\u0017a\u00063fM\u0006,H\u000e^\"p]Z,'oZ3oG\u0016\u001c\u0005.Z2l))\u0011)Na;\u0003n\n=(\u0011\u001f\t\u0006\u0005/\u0014)\u000f\u0015\b\u0005\u00053\u0014\tO\u0004\u0003\u0003\\\n}g\u0002BA\b\u0005;L\u0011!S\u0005\u0003\u000f\"K1Aa9G\u0003M1\u0015N]:u\u001fJ$WM]'j]&l\u0017N_3s\u0013\u0011\u00119O!;\u0003!\r{gN^3sO\u0016t7-Z\"iK\u000e\\'b\u0001Br\r\")QM\u000fa\u0001!\")aM\u000fa\u0001!\")AN\u000fa\u0001-\")qM\u000fa\u0001Q\u0006\t\u0002KU(K?\u001e\u0013\u0016\tR%F\u001dR{V\tU*\u0002%A\u0013vJS0H%\u0006#\u0015*\u0012(U?\u0016\u00036\u000bI\u0001\u0015E\u001a<7OY\"p]Z,'oZ3oG\u0016$Vm\u001d;\u0015\r\tU'1 B\u007f\u0011\u0015)W\b1\u0001Q\u0011\u00151W\b1\u0001Q\u0003A\u0011w.\u001e8eK\u0012\u001cuN\u001c<DQ\u0016\u001c7.\u0006\u0003\u0004\u0004\r}A\u0003CA]\u0007\u000b\u0019\u0019c!\n\t\u000f\t\rc\b1\u0001\u0004\bA\"1\u0011BB\t!%\u00119na\u0003Q\u0007\u001b\u0019i\"\u0003\u0003\u0003<\t%\b\u0003BB\b\u0007#a\u0001\u0001\u0002\u0007\u0004\u0014\r\u0015\u0011\u0011!A\u0001\u0006\u0003\u0019)BA\u0002`IE\nBaa\u0006\u0002\u001cB\u0019qk!\u0007\n\u0007\rm\u0001LA\u0004O_RD\u0017N\\4\u0011\t\r=1q\u0004\u0003\b\u0007Cq$\u0019AB\u000b\u0005\u0005A\u0005\"B3?\u0001\u0004\u0001\u0006\"\u00024?\u0001\u0004\u0001\u0016a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'\u0006\u0002\u0004,)\u001a\u0001.!\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%k\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAB\u001d!\u0011\t)ia\u000f\n\t\ru\u0012q\u0011\u0002\u0007\u001f\nTWm\u0019;"
)
public class LBFGSB extends FirstOrderMinimizer {
   private volatile History$ History$module;
   public final DenseVector breeze$optimize$LBFGSB$$lowerBounds;
   public final DenseVector breeze$optimize$LBFGSB$$upperBounds;
   private final int m;
   private final int maxZoomIter;
   private final int maxLineSearchIter;
   private final double EPS;

   public static int $lessinit$greater$default$7() {
      return LBFGSB$.MODULE$.$lessinit$greater$default$7();
   }

   public static int $lessinit$greater$default$6() {
      return LBFGSB$.MODULE$.$lessinit$greater$default$6();
   }

   public static double $lessinit$greater$default$5() {
      return LBFGSB$.MODULE$.$lessinit$greater$default$5();
   }

   public static int $lessinit$greater$default$4() {
      return LBFGSB$.MODULE$.$lessinit$greater$default$4();
   }

   public static int $lessinit$greater$default$3() {
      return LBFGSB$.MODULE$.$lessinit$greater$default$3();
   }

   public static FirstOrderMinimizer.ConvergenceCheck defaultConvergenceCheck(final DenseVector lowerBounds, final DenseVector upperBounds, final double tolerance, final int maxIter) {
      return LBFGSB$.MODULE$.defaultConvergenceCheck(lowerBounds, upperBounds, tolerance, maxIter);
   }

   public History$ History() {
      if (this.History$module == null) {
         this.History$lzycompute$1();
      }

      return this.History$module;
   }

   public double EPS() {
      return this.EPS;
   }

   public History initialHistory(final DiffFunction f, final DenseVector init) {
      return this.initialize(f, init);
   }

   public History updateHistory(final DenseVector newX, final DenseVector newGrad, final double newVal, final DiffFunction f, final FirstOrderMinimizer.State oldState) {
      return this.updateSkYkHessianApproxMat((History)oldState.history(), (DenseVector)newX.$minus(oldState.x(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()), (DenseVector)newGrad.$minus$colon$minus(oldState.grad(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double()));
   }

   public DenseVector chooseDescentDirection(final FirstOrderMinimizer.State state, final DiffFunction f) {
      DenseVector x = (DenseVector)state.x();
      DenseVector g = (DenseVector)state.grad();
      Tuple2 var7 = this.getGeneralizedCauchyPoint((History)state.history(), x, g);
      if (var7 != null) {
         DenseVector cauchyPoint = (DenseVector)var7._1();
         DenseVector c = (DenseVector)var7._2();
         Tuple2 var3 = new Tuple2(cauchyPoint, c);
         DenseVector cauchyPoint = (DenseVector)var3._1();
         DenseVector c = (DenseVector)var3._2();
         this.adjustWithinBound(cauchyPoint);
         DenseVector var10000;
         if (0 == state.iter()) {
            var10000 = (DenseVector)cauchyPoint.$minus(x, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
         } else {
            DenseVector subspaceMin = this.subspaceMinimization((History)state.history(), cauchyPoint, x, c, g);
            this.adjustWithinBound(subspaceMin);
            var10000 = (DenseVector)subspaceMin.$minus(x, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
         }

         DenseVector dirk = var10000;
         return dirk;
      } else {
         throw new MatchError(var7);
      }
   }

   public double determineStepSize(final FirstOrderMinimizer.State state, final DiffFunction f, final DenseVector direction) {
      DiffFunction ff = new DiffFunction(state, direction, f) {
         // $FF: synthetic field
         private final LBFGSB $outer;
         private final FirstOrderMinimizer.State state$1;
         private final DenseVector direction$1;
         private final DiffFunction f$1;

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

         public Tuple2 calculate(final double alpha) {
            DenseVector newX = this.$outer.takeStep(this.state$1, this.direction$1, alpha);
            Tuple2 var6 = this.f$1.calculate(newX);
            if (var6 != null) {
               double ff = var6._1$mcD$sp();
               DenseVector grad = (DenseVector)var6._2();
               Tuple2 var3 = new Tuple2(BoxesRunTime.boxToDouble(ff), grad);
               double ffx = var3._1$mcD$sp();
               DenseVector grad = (DenseVector)var3._2();
               return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(ffx)), grad.dot(this.direction$1, HasOps$.MODULE$.canDotD()));
            } else {
               throw new MatchError(var6);
            }
         }

         public {
            if (LBFGSB.this == null) {
               throw null;
            } else {
               this.$outer = LBFGSB.this;
               this.state$1 = state$1;
               this.direction$1 = direction$1;
               this.f$1 = f$1;
               Function1.$init$(this);
               ImmutableNumericOps.$init$(this);
               NumericOps.$init$(this);
               StochasticDiffFunction.$init$(this);
               DiffFunction.$init$(this);
            }
         }
      };
      StrongWolfeLineSearch wolfeRuleSearch = new StrongWolfeLineSearch(this.maxZoomIter, this.maxLineSearchIter);
      double minStepBound = Double.POSITIVE_INFINITY;

      for(int i = 0; i < this.breeze$optimize$LBFGSB$$lowerBounds.length(); ++i) {
         double dir = direction.apply$mcD$sp(i);
         if (dir != (double)0.0F) {
            double bound = dir < (double)0.0F ? this.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(i) : this.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(i);
            double stepBound = (bound - ((DenseVector)state.x()).apply$mcD$sp(i)) / dir;
            boolean cond$macro$1 = stepBound > (double)0.0F;
            if (!cond$macro$1) {
               throw new AssertionError("assertion failed: stepBound.>(0.0)");
            }

            if (stepBound < minStepBound) {
               minStepBound = stepBound;
            }
         }
      }

      return wolfeRuleSearch.minimizeWithBound(ff, (double)1.0F, minStepBound);
   }

   public DenseVector takeStep(final FirstOrderMinimizer.State state, final DenseVector dir, final double stepSize) {
      DenseVector newX = (DenseVector)((NumericOps)state.x()).$plus(dir.$times$colon$times(BoxesRunTime.boxToDouble(stepSize), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double());
      this.adjustWithinBound(newX);
      return newX;
   }

   public void adjustWithinBound(final DenseVector point) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = point.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         if (point.apply$mcD$sp(index$macro$2) > this.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(index$macro$2)) {
            point.update$mcD$sp(index$macro$2, this.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(index$macro$2));
         }

         if (point.apply$mcD$sp(index$macro$2) < this.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(index$macro$2)) {
            point.update$mcD$sp(index$macro$2, this.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(index$macro$2));
         }
      }

   }

   private History initialize(final DiffFunction f, final DenseVector x0) {
      int DIM = x0.length();
      int left$macro$1 = this.breeze$optimize$LBFGSB$$lowerBounds.length();
      int right$macro$2 = x0.length();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(72)).append("requirement failed: ").append((new StringBuilder(53)).append("Mismatch between x0 length (").append(x0.length()).append(") and lowerBounds length ").append(this.breeze$optimize$LBFGSB$$lowerBounds.length()).toString()).append(": ").append("LBFGSB.this.lowerBounds.length == x0.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         int left$macro$3 = this.breeze$optimize$LBFGSB$$upperBounds.length();
         int right$macro$4 = x0.length();
         if (left$macro$3 != right$macro$4) {
            throw new IllegalArgumentException((new StringBuilder(72)).append("requirement failed: ").append((new StringBuilder(53)).append("Mismatch between x0 length (").append(x0.length()).append(") and upperBounds length ").append(this.breeze$optimize$LBFGSB$$upperBounds.length()).toString()).append(": ").append("LBFGSB.this.upperBounds.length == x0.length (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
         } else {
            boolean cond$macro$5 = x0.forall$mcID$sp((JFunction2.mcZID.sp)(i, v) -> this.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(i) <= v && v <= this.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(i));
            if (!cond$macro$5) {
               throw new IllegalArgumentException("requirement failed: seed is not feasible (violates lower bound or upperBounds): x0.forall(((i: Int, v: Double) => LBFGSB.this.lowerBounds.apply(i).<=(v).&&(v.<=(LBFGSB.this.upperBounds.apply(i)))))");
            } else {
               return new History((double)1.0F, DenseMatrix$.MODULE$.zeros$mDc$sp(DIM, 2 * this.m, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()), DenseMatrix$.MODULE$.zeros$mDc$sp(2 * this.m, 2 * this.m, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()), DenseMatrix$.MODULE$.zeros$mDc$sp(0, 0, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()), DenseMatrix$.MODULE$.zeros$mDc$sp(0, 0, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()));
            }
         }
      }
   }

   public Tuple2 getGeneralizedCauchyPoint(final History history, final DenseVector x, final DenseVector g) {
      int n = x.length();
      DenseVector d = DenseVector$.MODULE$.zeros$mDc$sp(n, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      DenseVector t = (DenseVector)g.mapPairs$mcID$sp((ix, gi) -> $anonfun$getGeneralizedCauchyPoint$1(this, x, d, BoxesRunTime.unboxToInt(ix), BoxesRunTime.unboxToDouble(gi)), HasOps$.MODULE$.canMapPairs(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
      DenseVector xCauchy = x.copy$mcD$sp();
      DenseVector p = (DenseVector)((ImmutableNumericOps)history.W().t(HasOps$.MODULE$.canTranspose_DM())).$times(d, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD());
      DenseVector c = DenseVector$.MODULE$.zeros$mDc$sp(history.M().rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      double fDerivative = BoxesRunTime.unboxToDouble(g.dot(d, HasOps$.MODULE$.canDotD()));
      double fSecondDerivative = (double)-1.0F * history.theta() * fDerivative - BoxesRunTime.unboxToDouble(p.dot(history.M().$times(p, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.canDotD()));
      double dtMin = -(fDerivative / fSecondDerivative);
      DoubleRef oldT = DoubleRef.create((double)0.0F);
      int[] sortedIndeces = (int[])scala.collection.ArrayOps..MODULE$.sortWith$extension(scala.Predef..MODULE$.intArrayOps(((DenseVector)t.map((xx) -> BoxesRunTime.boxToInteger($anonfun$getGeneralizedCauchyPoint$2(xx)), DenseVector$.MODULE$.DV_canMapValues(scala.reflect.ClassTag..MODULE$.Int()))).toArray$mcI$sp(scala.reflect.ClassTag..MODULE$.Int())), (JFunction2.mcZII.sp)(ia, ib) -> ((Tuple2)t.apply(ia))._2$mcD$sp() < ((Tuple2)t.apply(ib))._2$mcD$sp());
      Object qual$1 = scala.Predef..MODULE$.intArrayOps(sortedIndeces);
      Function1 x$1 = (idx) -> (double)0 != ((Tuple2)t.apply(idx))._2$mcD$sp();
      int x$2 = scala.collection.ArrayOps..MODULE$.indexWhere$default$2$extension(qual$1);
      int i = scala.collection.ArrayOps..MODULE$.indexWhere$extension(qual$1, x$1, x$2);
      int b = sortedIndeces[i];
      double minT = ((Tuple2)t.apply(b))._2$mcD$sp();
      double deltaT = minT - oldT.elem;

      while(deltaT <= dtMin && i < n) {
         xCauchy.update$mcD$sp(b, (double)0 < d.apply$mcD$sp(b) ? this.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(b) : this.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(b));
         double zb = xCauchy.apply$mcD$sp(b) - x.apply$mcD$sp(b);
         c = (DenseVector)c.$plus(p.$times$colon$times(BoxesRunTime.boxToDouble(deltaT), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double());
         DenseVector bRowOfW = (DenseVector)((ImmutableNumericOps)history.W().apply(BoxesRunTime.boxToInteger(b), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRow())).t(HasOps$.MODULE$.canUntranspose());
         fDerivative += deltaT * fSecondDerivative + g.apply$mcD$sp(b) * g.apply$mcD$sp(b) + history.theta() * g.apply$mcD$sp(b) * zb - BoxesRunTime.unboxToDouble(((ImmutableNumericOps)((ImmutableNumericOps)bRowOfW.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()))).$times$colon$times(BoxesRunTime.boxToDouble(g.apply$mcD$sp(b)), HasOps$.MODULE$.impl_Op_Tt_S_eq_RT_from_T_S(DenseVector$.MODULE$.DV_scalarOf(), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar(), HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl())))).$times(history.M().$times(c, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.transTimesNormalFromDot(HasOps$.MODULE$.canDotD())));
         fSecondDerivative += (double)-1.0F * history.theta() * g.apply$mcD$sp(b) * g.apply$mcD$sp(b) - (double)2.0F * g.apply$mcD$sp(b) * BoxesRunTime.unboxToDouble(bRowOfW.dot(history.M().$times(p, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.canDotD())) - g.apply$mcD$sp(b) * g.apply$mcD$sp(b) * BoxesRunTime.unboxToDouble(((ImmutableNumericOps)bRowOfW.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()))).$times(history.M().$times(bRowOfW, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.transTimesNormalFromDot(HasOps$.MODULE$.canDotD())));
         p.$plus$eq(bRowOfW.$times$colon$times(BoxesRunTime.boxToDouble(g.apply$mcD$sp(b)), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
         d.update$mcD$sp(b, (double)0.0F);
         dtMin = -fDerivative / fSecondDerivative;
         oldT.elem = minT;
         ++i;
         if (i < n) {
            b = sortedIndeces[i];
            minT = ((Tuple2)t.apply(b))._2$mcD$sp();
            deltaT = minT - oldT.elem;
         }
      }

      dtMin = scala.math.package..MODULE$.max(dtMin, (double)0.0F);
      oldT.elem += dtMin;
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(i), n).foreach$mVc$sp((JFunction1.mcVI.sp)(sortIdx) -> xCauchy.update$mcD$sp(sortedIndeces[sortIdx], x.apply$mcD$sp(sortedIndeces[sortIdx]) + oldT.elem * d.apply$mcD$sp(sortedIndeces[sortIdx])));
      c.$plus$eq(p.$times$colon$times(BoxesRunTime.boxToDouble(dtMin), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
      return new Tuple2(xCauchy, c);
   }

   public double findAlpha(final DenseVector xCauchy, final Vector du, final int[] freeVarIndex) {
      DoubleRef starAlpha = DoubleRef.create((double)1.0F);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.intArrayOps(freeVarIndex))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$findAlpha$1(check$ifrefutable$1))).foreach((x$3) -> {
         $anonfun$findAlpha$2(this, du, starAlpha, xCauchy, x$3);
         return BoxedUnit.UNIT;
      });
      boolean cond$macro$1 = starAlpha.elem >= (double)0.0F && starAlpha.elem <= (double)1.0F;
      if (!cond$macro$1) {
         throw new AssertionError("assertion failed: starAlpha.>=(0.0).&&(starAlpha.<=(1.0))");
      } else {
         return starAlpha.elem;
      }
   }

   public DenseVector subspaceMinimization(final History history, final DenseVector xCauchy, final DenseVector x, final DenseVector c, final DenseVector g) {
      double invTheta = (double)1.0F / history.theta();
      IndexedSeq freeVariableIndexes = xCauchy.iterator().collect(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final LBFGSB $outer;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            Object var3;
            if (x1 != null) {
               int i = x1._1$mcI$sp();
               double v = x1._2$mcD$sp();
               if (v != this.$outer.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(i) && v != this.$outer.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(i)) {
                  var3 = BoxesRunTime.boxToInteger(i);
                  return var3;
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            boolean var2;
            if (x1 != null) {
               int i = x1._1$mcI$sp();
               double v = x1._2$mcD$sp();
               if (v != this.$outer.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(i) && v != this.$outer.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(i)) {
                  var2 = true;
                  return var2;
               }
            }

            var2 = false;
            return var2;
         }

         public {
            if (LBFGSB.this == null) {
               throw null;
            } else {
               this.$outer = LBFGSB.this;
            }
         }
      }).toIndexedSeq();
      int freeVarCount = freeVariableIndexes.length();
      DenseMatrix WZ = DenseMatrix$.MODULE$.zeros$mDc$sp(history.W().cols(), freeVarCount, scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), freeVarCount).foreach((i) -> $anonfun$subspaceMinimization$1(WZ, history, freeVariableIndexes, BoxesRunTime.unboxToInt(i)));
      DenseVector dirTheta = (DenseVector)((ImmutableNumericOps)xCauchy.$minus(x, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double())).$times$colon$times(BoxesRunTime.boxToDouble(history.theta()), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar());
      DenseVector fullR = (DenseVector)((ImmutableNumericOps)g.$plus(dirTheta, HasOps$.MODULE$.impl_OpAdd_DV_DV_eq_DV_Double())).$minus(history.W().$times(history.M().$times(c, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD()), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      SliceVector rc = (SliceVector)fullR.apply(freeVariableIndexes, HasOps$.MODULE$.canSliceTensor(scala.reflect.ClassTag..MODULE$.Double()));
      DenseVector v = (DenseVector)history.M().$times(WZ.$times(rc, HasOps$.MODULE$.castOps_M_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.op_M_V_Double())), HasOps$.MODULE$.impl_OpMulMatrix_DM_V_eq_DV_Double());
      DenseMatrix N = (DenseMatrix)WZ.$times(WZ.t(HasOps$.MODULE$.canTranspose_DM()), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
      N = (DenseMatrix)N.$times$colon$times(BoxesRunTime.boxToDouble(invTheta), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar());
      N = (DenseMatrix)DenseMatrix$.MODULE$.eye$mDc$sp(N.rows(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero(), Semiring$.MODULE$.semiringD()).$minus(history.M().$times(N, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()), HasOps$.MODULE$.op_DM_DM_Double_OpSub());
      DenseMatrix invN = (DenseMatrix)inv$.MODULE$.apply(N, inv$.MODULE$.canInvUsingLU_Double(LU$primitive$LU_DM_Impl_Double$.MODULE$));
      DenseVector invNv = (DenseVector)invN.$times(v, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD());
      v = (DenseVector)N.$bslash(v, HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD());
      DenseVector wzv = (DenseVector)((ImmutableNumericOps)((ImmutableNumericOps)WZ.t(HasOps$.MODULE$.canTranspose_DM())).$times(v, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$times$colon$times(BoxesRunTime.boxToDouble(invTheta * invTheta), HasOps$.MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar());
      Vector thetaRC = (Vector)rc.$times$colon$times(BoxesRunTime.boxToDouble(invTheta), HasOps$.MODULE$.castOps_V_S(SliceVector$.MODULE$.scalarOf(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulScalar()));
      Vector du = (Vector)((ImmutableNumericOps)thetaRC.$plus(wzv, HasOps$.MODULE$.pureFromUpdate(HasOps$.MODULE$.castUpdateOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd()), Vector$.MODULE$.canCopy()))).$times$colon$times(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulScalar());
      double starAlpha = this.findAlpha(xCauchy, du, (int[])freeVariableIndexes.toArray(scala.reflect.ClassTag..MODULE$.Int()));
      Vector dStar = (Vector)du.$times$colon$times(BoxesRunTime.boxToDouble(starAlpha), HasOps$.MODULE$.impl_Op_V_S_eq_V_Double_OpMulScalar());
      DenseVector subspaceMinX = xCauchy.copy$mcD$sp();
      ((IterableOps)freeVariableIndexes.zipWithIndex()).withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$subspaceMinimization$2(check$ifrefutable$2))).foreach((x$4) -> {
         $anonfun$subspaceMinimization$3(subspaceMinX, dStar, x$4);
         return BoxedUnit.UNIT;
      });
      return subspaceMinX;
   }

   public History updateSkYkHessianApproxMat(final History history, final DenseVector newS, final DenseVector newY) {
      History var10000;
      if (0 == history.yHistory().cols()) {
         DenseMatrix x$1 = (DenseMatrix)newY.toDenseMatrix$mcD$sp().t(HasOps$.MODULE$.canTranspose_DM());
         DenseMatrix x$2 = (DenseMatrix)newS.toDenseMatrix$mcD$sp().t(HasOps$.MODULE$.canTranspose_DM());
         double x$3 = history.copy$default$1();
         DenseMatrix x$4 = history.copy$default$2();
         DenseMatrix x$5 = history.copy$default$3();
         var10000 = history.copy(x$3, x$4, x$5, x$1, x$2);
      } else if (history.yHistory().cols() < this.m) {
         DenseMatrix x$6 = DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{history.yHistory(), (DenseMatrix)newY.toDenseMatrix$mcD$sp().t(HasOps$.MODULE$.canTranspose_DM())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseMatrix x$7 = DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{history.sHistory(), (DenseMatrix)newS.toDenseMatrix$mcD$sp().t(HasOps$.MODULE$.canTranspose_DM())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         double x$8 = history.copy$default$1();
         DenseMatrix x$9 = history.copy$default$2();
         DenseMatrix x$10 = history.copy$default$3();
         var10000 = history.copy(x$8, x$9, x$10, x$6, x$7);
      } else {
         DenseMatrix x$11 = DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)history.yHistory().apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.m), HasOps$.MODULE$.canSliceCols()), (DenseMatrix)newY.toDenseMatrix$mcD$sp().t(HasOps$.MODULE$.canTranspose_DM())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseMatrix x$12 = DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{(DenseMatrix)history.sHistory().apply(scala.package..MODULE$.$colon$colon(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), this.m), HasOps$.MODULE$.canSliceCols()), (DenseMatrix)newS.toDenseMatrix$mcD$sp().t(HasOps$.MODULE$.canTranspose_DM())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         double x$13 = history.copy$default$1();
         DenseMatrix x$14 = history.copy$default$2();
         DenseMatrix x$15 = history.copy$default$3();
         var10000 = history.copy(x$13, x$14, x$15, x$11, x$12);
      }

      History newHistory = var10000;
      double curvatureTest = scala.math.package..MODULE$.abs(BoxesRunTime.unboxToDouble(newS.dot(newY, HasOps$.MODULE$.canDotD())));
      if (this.EPS() * BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(newY, BoxesRunTime.boxToInteger(2), norm$.MODULE$.fromCanNormInt(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double())))) < curvatureTest) {
         double newTheta = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)newY.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()))).$times(newY, HasOps$.MODULE$.transTimesNormalFromDot(HasOps$.MODULE$.canDotD()))) / BoxesRunTime.unboxToDouble(((ImmutableNumericOps)newY.t(HasOps$.MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()))).$times(newS, HasOps$.MODULE$.transTimesNormalFromDot(HasOps$.MODULE$.canDotD())));
         DenseMatrix newW = DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{newHistory.yHistory(), (DenseMatrix)newHistory.sHistory().$times$colon$times(BoxesRunTime.boxToDouble(newTheta), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseMatrix A = (DenseMatrix)((ImmutableNumericOps)newHistory.sHistory().t(HasOps$.MODULE$.canTranspose_DM())).$times(newHistory.yHistory(), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
         DenseMatrix L = breeze.linalg.package$.MODULE$.strictlyLowerTriangular(A, Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseMatrix D = (DenseMatrix)((ImmutableNumericOps)diag$.MODULE$.apply(diag$.MODULE$.apply(A, diag$.MODULE$.diagDMDVImpl()), diag$.MODULE$.diagDVDMImpl(scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()))).$times$colon$times(BoxesRunTime.boxToDouble((double)-1.0F), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar());
         DenseMatrix STS = (DenseMatrix)((ImmutableNumericOps)newHistory.sHistory().t(HasOps$.MODULE$.canTranspose_DM())).$times(newHistory.sHistory(), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD());
         DenseMatrix MM = DenseMatrix$.MODULE$.vertcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{D, (DenseMatrix)L.t(HasOps$.MODULE$.canTranspose_DM())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero()), DenseMatrix$.MODULE$.horzcat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new DenseMatrix[]{L, (DenseMatrix)STS.$times$colon$times(BoxesRunTime.boxToDouble(newTheta), HasOps$.MODULE$.op_DM_S_Double_OpMulScalar())}), scala..less.colon.less..MODULE$.refl(), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero())}), HasOps$.MODULE$.dm_dm_UpdateOp_Double_OpSet(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         DenseMatrix newM = (DenseMatrix)inv$.MODULE$.apply(MM, inv$.MODULE$.canInvUsingLU_Double(LU$primitive$LU_DM_Impl_Double$.MODULE$));
         var10000 = newHistory.copy(newTheta, newW, newM, newHistory.copy$default$4(), newHistory.copy$default$5());
      } else {
         var10000 = history;
      }

      return var10000;
   }

   private final void History$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.History$module == null) {
            this.History$module = new History$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$getGeneralizedCauchyPoint$1(final LBFGSB $this, final DenseVector x$5, final DenseVector d$1, final int i, final double gi) {
      Tuple2.mcID.sp var10000;
      if ((double)0 == gi) {
         var10000 = new Tuple2.mcID.sp(i, Double.MAX_VALUE);
      } else {
         double ti = gi < (double)0 ? (x$5.apply$mcD$sp(i) - $this.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(i)) / gi : (x$5.apply$mcD$sp(i) - $this.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(i)) / gi;
         d$1.update$mcD$sp(i, (double)0 == ti ? (double)0.0F : -gi);
         var10000 = new Tuple2.mcID.sp(i, ti);
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final int $anonfun$getGeneralizedCauchyPoint$2(final Tuple2 x) {
      return x._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findAlpha$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$findAlpha$2(final LBFGSB $this, final Vector du$1, final DoubleRef starAlpha$1, final DenseVector xCauchy$2, final Tuple2 x$3) {
      if (x$3 != null) {
         int vIdx = x$3._1$mcI$sp();
         int i = x$3._2$mcI$sp();
         if ((double)0 < du$1.apply$mcID$sp(i)) {
            starAlpha$1.elem = scala.math.package..MODULE$.min(starAlpha$1.elem, ($this.breeze$optimize$LBFGSB$$upperBounds.apply$mcD$sp(vIdx) - xCauchy$2.apply$mcD$sp(vIdx)) / du$1.apply$mcID$sp(i));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else if ((double)0 > du$1.apply$mcID$sp(i)) {
            starAlpha$1.elem = scala.math.package..MODULE$.min(starAlpha$1.elem, ($this.breeze$optimize$LBFGSB$$lowerBounds.apply$mcD$sp(vIdx) - xCauchy$2.apply$mcD$sp(vIdx)) / du$1.apply$mcID$sp(i));
            BoxedUnit var9 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x$3);
      }
   }

   // $FF: synthetic method
   public static final DenseVector $anonfun$subspaceMinimization$1(final DenseMatrix WZ$1, final History history$1, final IndexedSeq freeVariableIndexes$1, final int i) {
      return (DenseVector)((NumericOps)WZ$1.apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(i), HasOps$.MODULE$.canSliceCol())).$colon$eq(((ImmutableNumericOps)history$1.W().apply(freeVariableIndexes$1.apply(i), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRow())).t(HasOps$.MODULE$.canUntranspose()), HasOps$.MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$subspaceMinimization$2(final Tuple2 check$ifrefutable$2) {
      boolean var1;
      if (check$ifrefutable$2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   public static final void $anonfun$subspaceMinimization$3(final DenseVector subspaceMinX$1, final Vector dStar$1, final Tuple2 x$4) {
      if (x$4 != null) {
         int freeVarIdx = x$4._1$mcI$sp();
         int i = x$4._2$mcI$sp();
         subspaceMinX$1.update$mcD$sp(freeVarIdx, subspaceMinX$1.apply$mcD$sp(freeVarIdx) + dStar$1.apply$mcID$sp(i));
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$4);
      }
   }

   public LBFGSB(final DenseVector lowerBounds, final DenseVector upperBounds, final int maxIter, final int m, final double tolerance, final int maxZoomIter, final int maxLineSearchIter) {
      super(LBFGSB$.MODULE$.defaultConvergenceCheck(lowerBounds, upperBounds, tolerance, maxIter), DenseVector$.MODULE$.space_Double());
      this.breeze$optimize$LBFGSB$$lowerBounds = lowerBounds;
      this.breeze$optimize$LBFGSB$$upperBounds = upperBounds;
      this.m = m;
      this.maxZoomIter = maxZoomIter;
      this.maxLineSearchIter = maxLineSearchIter;
      this.EPS = 2.2E-16;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class History implements Product, Serializable {
      private final double theta;
      private final DenseMatrix W;
      private final DenseMatrix M;
      private final DenseMatrix yHistory;
      private final DenseMatrix sHistory;
      // $FF: synthetic field
      public final LBFGSB $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double theta() {
         return this.theta;
      }

      public DenseMatrix W() {
         return this.W;
      }

      public DenseMatrix M() {
         return this.M;
      }

      public DenseMatrix yHistory() {
         return this.yHistory;
      }

      public DenseMatrix sHistory() {
         return this.sHistory;
      }

      public History copy(final double theta, final DenseMatrix W, final DenseMatrix M, final DenseMatrix yHistory, final DenseMatrix sHistory) {
         return this.breeze$optimize$LBFGSB$History$$$outer().new History(theta, W, M, yHistory, sHistory);
      }

      public double copy$default$1() {
         return this.theta();
      }

      public DenseMatrix copy$default$2() {
         return this.W();
      }

      public DenseMatrix copy$default$3() {
         return this.M();
      }

      public DenseMatrix copy$default$4() {
         return this.yHistory();
      }

      public DenseMatrix copy$default$5() {
         return this.sHistory();
      }

      public String productPrefix() {
         return "History";
      }

      public int productArity() {
         return 5;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.theta());
               break;
            case 1:
               var10000 = this.W();
               break;
            case 2:
               var10000 = this.M();
               break;
            case 3:
               var10000 = this.yHistory();
               break;
            case 4:
               var10000 = this.sHistory();
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
         return x$1 instanceof History;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "theta";
               break;
            case 1:
               var10000 = "W";
               break;
            case 2:
               var10000 = "M";
               break;
            case 3:
               var10000 = "yHistory";
               break;
            case 4:
               var10000 = "sHistory";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.theta()));
         var1 = Statics.mix(var1, Statics.anyHash(this.W()));
         var1 = Statics.mix(var1, Statics.anyHash(this.M()));
         var1 = Statics.mix(var1, Statics.anyHash(this.yHistory()));
         var1 = Statics.mix(var1, Statics.anyHash(this.sHistory()));
         return Statics.finalizeHash(var1, 5);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var13;
         if (this != x$1) {
            label88: {
               boolean var2;
               if (x$1 instanceof History && ((History)x$1).breeze$optimize$LBFGSB$History$$$outer() == this.breeze$optimize$LBFGSB$History$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label64: {
                     History var4 = (History)x$1;
                     if (this.theta() == var4.theta()) {
                        label78: {
                           DenseMatrix var10000 = this.W();
                           DenseMatrix var5 = var4.W();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label78;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label78;
                           }

                           var10000 = this.M();
                           DenseMatrix var6 = var4.M();
                           if (var10000 == null) {
                              if (var6 != null) {
                                 break label78;
                              }
                           } else if (!var10000.equals(var6)) {
                              break label78;
                           }

                           var10000 = this.yHistory();
                           DenseMatrix var7 = var4.yHistory();
                           if (var10000 == null) {
                              if (var7 != null) {
                                 break label78;
                              }
                           } else if (!var10000.equals(var7)) {
                              break label78;
                           }

                           var10000 = this.sHistory();
                           DenseMatrix var8 = var4.sHistory();
                           if (var10000 == null) {
                              if (var8 != null) {
                                 break label78;
                              }
                           } else if (!var10000.equals(var8)) {
                              break label78;
                           }

                           if (var4.canEqual(this)) {
                              var13 = true;
                              break label64;
                           }
                        }
                     }

                     var13 = false;
                  }

                  if (var13) {
                     break label88;
                  }
               }

               var13 = false;
               return var13;
            }
         }

         var13 = true;
         return var13;
      }

      // $FF: synthetic method
      public LBFGSB breeze$optimize$LBFGSB$History$$$outer() {
         return this.$outer;
      }

      public History(final double theta, final DenseMatrix W, final DenseMatrix M, final DenseMatrix yHistory, final DenseMatrix sHistory) {
         this.theta = theta;
         this.W = W;
         this.M = M;
         this.yHistory = yHistory;
         this.sHistory = sHistory;
         if (LBFGSB.this == null) {
            throw null;
         } else {
            this.$outer = LBFGSB.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class History$ extends AbstractFunction5 implements Serializable {
      // $FF: synthetic field
      private final LBFGSB $outer;

      public final String toString() {
         return "History";
      }

      public History apply(final double theta, final DenseMatrix W, final DenseMatrix M, final DenseMatrix yHistory, final DenseMatrix sHistory) {
         return this.$outer.new History(theta, W, M, yHistory, sHistory);
      }

      public Option unapply(final History x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToDouble(x$0.theta()), x$0.W(), x$0.M(), x$0.yHistory(), x$0.sHistory())));
      }

      public History$() {
         if (LBFGSB.this == null) {
            throw null;
         } else {
            this.$outer = LBFGSB.this;
            super();
         }
      }
   }
}
