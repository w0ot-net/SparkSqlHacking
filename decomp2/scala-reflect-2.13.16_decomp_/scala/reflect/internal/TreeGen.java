package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.BuildFromLowPriority2;
import scala.collection.IterableOps;
import scala.collection.LinearSeqOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedLinearSeqOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Statics;
import scala.util.ChainingOps;
import scala.util.package;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019Ub\u0001CA\u0004\u0003\u0013\t\t!a\u0006\t\u000f\u0005\u0005\u0002\u0001\"\u0001\u0002$!I\u0011\u0011\u0006\u0001C\u0002\u001b\u0005\u00111\u0006\u0005\b\u0003g\u0001A\u0011AA\u001b\u0011\u001d\t\u0019\u0006\u0001C\u0001\u0003+Bq!!\u0017\u0001\t\u0003\tY\u0006C\u0004\u0002`\u0001!\t!!\u0019\t\u000f\u0005\u0015\u0004\u0001\"\u0001\u0002h!9\u0011\u0011\u000e\u0001\u0005\u0002\u0005-\u0004\"CAJ\u0001E\u0005I\u0011AAK\u0011\u001d\tY\u000b\u0001C\u0001\u0003[Cq!a+\u0001\t\u0003\t)\u000eC\u0004\u0002,\u0002!\t!a8\t\u000f\u0005-\u0006\u0001\"\u0001\u0002f\"9\u00111\u0016\u0001\u0005\u0002\u00055\bbBAV\u0001\u0011\u0005\u0011Q\u001f\u0005\b\u0003W\u0003A\u0011AA\u0000\u0011\u001d\u00119\u0001\u0001C\u0001\u0005\u0013AqAa\u0004\u0001\t\u0003\u0011\t\u0002C\u0004\u0003\u0010\u0001!\tAa\u0006\t\u000f\t}\u0001\u0001\"\u0001\u0003\"!9!q\u0005\u0001\u0005\u0006\t%\u0002b\u0002B \u0001\u0011\u0005!\u0011\t\u0005\b\u0005\u007f\u0001A\u0011\u0001B)\u0011\u001d\u0011)\u0006\u0001C\u0001\u0005/BqA!\u0016\u0001\t\u0003\u0011Y\u0006C\u0004\u0003b\u0001!\tAa\u0019\t\u000f\t%\u0004\u0001\"\u0001\u0003l!9!q\u000e\u0001\u0005\u0002\tE\u0004b\u0002B8\u0001\u0011\u0005!q\u000f\u0005\b\u0005w\u0002A\u0011\u0001B?\u0011\u001d\u00119\t\u0001C\u0001\u0005\u0013CqA!$\u0001\t\u0003\u0011y\tC\u0004\u0003\u0016\u0002!\tAa&\t\u000f\t}\u0005\u0001\"\u0001\u0003\"\"9!q\u0015\u0001\u0005\u0002\t%\u0006b\u0002BY\u0001\u0011%!1\u0017\u0005\b\u0005\u0007\u0004A\u0011\u0002Bc\u0011\u001d\u0011\t\u000e\u0001C\u0005\u0005'DqAa6\u0001\t\u0003\u0011I\u000eC\u0005\u0003d\u0002\t\n\u0011\"\u0001\u0002\u0016\"I!Q\u001d\u0001\u0012\u0002\u0013\u0005\u0011Q\u0013\u0005\b\u0005O\u0004A\u0011\u0001Bu\u0011%\u0011\u0019\u0010AI\u0001\n\u0003\t)\nC\u0005\u0003v\u0002\t\n\u0011\"\u0001\u0002\u0016\"9!q\u001f\u0001\u0005\u0002\te\b\"CB\u0004\u0001E\u0005I\u0011AAK\u0011\u001d\u0019I\u0001\u0001C\u0001\u0007\u0017Aqa!\u0005\u0001\t\u0003\u0019\u0019\u0002C\u0004\u0004\u0016\u0001!\taa\u0006\t\u000f\rm\u0001\u0001\"\u0001\u0004\u001e!911\u0006\u0001\u0005\u0002\r5\u0002bBB\u0016\u0001\u0011\u000511\u0007\u0005\b\u0007{\u0001A\u0011AB \u0011%\u0019I\u0005AI\u0001\n\u0003\t)\nC\u0004\u0004L\u0001!\ta!\u0014\t\u000f\rU\u0003\u0001\"\u0001\u0004X!911\r\u0001\u0005\u0002\r\u0015\u0004\"CB6\u0001E\u0005I\u0011AAK\u0011\u001d\u0019i\u0007\u0001C\u0001\u0007_Bqa!\u001f\u0001\t\u0003\u0019Y\bC\u0004\u0004\u0002\u0002!\taa\u0005\t\u000f\r\r\u0005\u0001\"\u0001\u0004\u0006\"91\u0011\u0013\u0001\u0005\u0002\u0005\u001d\u0004bBBJ\u0001\u0011\u00051Q\u0013\u0005\n\u0007\u001f\u0004\u0011\u0013!C\u0001\u0007#Dqa!6\u0001\t\u0003\u00199\u000eC\u0005\u0004r\u0002\t\n\u0011\"\u0001\u0004R\"911\u001f\u0001\u0005\u0002\rU\bb\u0002C\r\u0001\u0011\u0005A1\u0004\u0005\b\t[\u0001A\u0011\u0001C\u0018\u0011\u001d!)\u0004\u0001C\u0001\toAq\u0001\"\u000f\u0001\t\u0003!Y\u0004C\u0005\u0005D\u0001\t\n\u0011\"\u0001\u0002\u0016\"9AQ\t\u0001\u0005\u0002\u0011\u001d\u0003b\u0002C&\u0001\u0011\u0005AQ\n\u0005\b\t'\u0002A\u0011\u0001C+\u0011%!y\u0007AI\u0001\n\u0003\u0019\t\u000eC\u0005\u0005r\u0001\t\n\u0011\"\u0001\u0004R\u001e9A1\u000f\u0001\t\u0002\u0011Uda\u0002C<\u0001!\u0005A\u0011\u0010\u0005\b\u0003C\u0001F\u0011\u0001C>\u0011\u001d!i\b\u0015C\u0001\t\u007fBq\u0001b\"Q\t\u0003!IiB\u0004\u0005\u0016\u0002A\t\u0001b&\u0007\u000f\u0011e\u0005\u0001#\u0001\u0005\u001c\"9\u0011\u0011E+\u0005\u0002\u0011u\u0005b\u0002C?+\u0012\u0005Aq\u0014\u0005\b\t\u000f+F\u0011\u0001CS\u000f\u001d!I\u000b\u0001E\u0001\tW3q\u0001\",\u0001\u0011\u0003!y\u000bC\u0004\u0002\"i#\t\u0001\"-\t\u000f\u0011u$\f\"\u0001\u00054\"9Aq\u0011.\u0005\u0002\u0011]va\u0002C^\u0001!\u0005AQ\u0018\u0004\b\t\u007f\u0003\u0001\u0012\u0001Ca\u0011\u001d\t\tc\u0018C\u0001\t\u0007Dq\u0001\" `\t\u0003!)\rC\u0004\u0005\b~#\t\u0001\"3\t\u000f\u00115\u0007\u0001\"\u0001\u0005P\"9A1\u001e\u0001\u0005\n\u00115\bb\u0002C~\u0001\u0011%AQ \u0005\b\u000b\u000b\u0001A\u0011AC\u0004\u0011\u001d))\u0001\u0001C\u0001\u000b#Aq!\"\u0002\u0001\t\u0003)i\u0002C\u0004\u0006\u0006\u0001!I!\"\f\t\u000f\u0015}\u0002\u0001\"\u0001\u0006B!9Q1\u000b\u0001\u0005\n\u0015U\u0003bBC.\u0001\u0011\u0005QQ\f\u0005\b\u000bO\u0002A\u0011BC5\u0011\u001d)\t\b\u0001C\u0001\u000bgB\u0011\"\")\u0001#\u0003%\t!b)\u0007\r\u0015\u001d\u0006\u0001ACU\u0011\u001d\t\t\u0003\u001dC\u0001\u000boC\u0011\"b/q\u0005\u0004%\t!\"0\t\u0011\u0015E\u0007\u000f)A\u0005\u000b\u007fCq!b5q\t\u0003))\u000eC\u0004\u0006\\B$\t%\"8\t\u000f\u0011u\u0004\u000f\"\u0001\u0006h\"9QQ\u001e\u0001\u0005\n\u0015=hABC{\u0001\u0001)9\u0010C\u0004\u0002\"a$\t!b@\t\u000f\u0019\r\u0001\u0010\"\u0011\u0007\u0006!9a\u0011\u0002\u0001\u0005\u0002\u0019-\u0001b\u0002D\u0007\u0001\u0011\u0005a1B\u0004\b\r\u001f\u0001\u0001\u0012\u0001D\t\r\u001d1\u0019\u0002\u0001E\u0001\r+Aq!!\t\u007f\t\u000319\u0002C\u0004\u0007\u001a\u0001!\tAb\u0007\t\u000f\u0019}\u0001\u0001\"\u0001\u0007\"!9aQ\u0006\u0001\u0005\u0002\u0019=\"a\u0002+sK\u0016<UM\u001c\u0006\u0005\u0003\u0017\ti!\u0001\u0005j]R,'O\\1m\u0015\u0011\ty!!\u0005\u0002\u000fI,g\r\\3di*\u0011\u00111C\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001\u0011\u0011\u0004\t\u0005\u00037\ti\"\u0004\u0002\u0002\u0012%!\u0011qDA\t\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\"!!\n\u0011\u0007\u0005\u001d\u0002!\u0004\u0002\u0002\n\u00051q\r\\8cC2,\"!!\f\u0011\t\u0005\u001d\u0012qF\u0005\u0005\u0003c\tIAA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0017A\u0002:p_RLE\r\u0006\u0003\u00028\u0005\u0015\u0003\u0003BA\u001d\u0003{q1!a\u000f\u0003\u001b\u0005\u0001\u0011\u0002BA \u0003\u0003\u0012aaU3mK\u000e$\u0018\u0002BA\"\u0003\u0013\u0011Q\u0001\u0016:fKNDq!a\u0012\u0004\u0001\u0004\tI%\u0001\u0003oC6,\u0007\u0003BA\u001d\u0003\u0017JA!!\u0014\u0002P\t!a*Y7f\u0013\u0011\t\t&!\u0003\u0003\u000b9\u000bW.Z:\u0002\u0019I|w\u000e^*dC2\fGi\u001c;\u0015\t\u0005]\u0012q\u000b\u0005\b\u0003\u000f\"\u0001\u0019AA%\u0003!\u00198-\u00197b\t>$H\u0003BA\u001c\u0003;Bq!a\u0012\u0006\u0001\u0004\tI%\u0001\ntG\u0006d\u0017-\u00118o_R\fG/[8o\t>$H\u0003BA\u001c\u0003GBq!a\u0012\u0007\u0001\u0004\tI%A\ttG\u0006d\u0017-\u00118z%\u001647i\u001c8tiJ,\"!a\u000e\u0002'M\u001c\u0017\r\\1Gk:\u001cG/[8o\u0007>t7\u000f\u001e:\u0015\u0011\u00055\u00141OAC\u0003\u0013\u0003B!!\u000f\u0002p%!\u0011\u0011OA!\u0005\u0011!&/Z3\t\u000f\u0005U\u0004\u00021\u0001\u0002x\u00059\u0011M]4ua\u0016\u001c\bCBA=\u0003\u007f\niG\u0004\u0003\u0002\u001c\u0005m\u0014\u0002BA?\u0003#\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0002\u0006\r%\u0001\u0002'jgRTA!! \u0002\u0012!9\u0011q\u0011\u0005A\u0002\u00055\u0014A\u0002:fgR\u0004X\rC\u0005\u0002\f\"\u0001\n\u00111\u0001\u0002\u000e\u0006Y\u0011MY:ue\u0006\u001cGOR;o!\u0011\tY\"a$\n\t\u0005E\u0015\u0011\u0003\u0002\b\u0005>|G.Z1o\u0003u\u00198-\u00197b\rVt7\r^5p]\u000e{gn\u001d;sI\u0011,g-Y;mi\u0012\u001aTCAALU\u0011\ti)!',\u0005\u0005m\u0005\u0003BAO\u0003Ok!!a(\u000b\t\u0005\u0005\u00161U\u0001\nk:\u001c\u0007.Z2lK\u0012TA!!*\u0002\u0012\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005%\u0016q\u0014\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001D7l\u001b\u0016$\bn\u001c3DC2dGCCA7\u0003_\u000bi,!1\u0002R\"9\u0011\u0011\u0017\u0006A\u0002\u0005M\u0016\u0001\u0003:fG\u0016Lg/\u001a:\u0011\t\u0005e\u0012QW\u0005\u0005\u0003o\u000bIL\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0005\u0003w\u000bIAA\u0004Ts6\u0014w\u000e\\:\t\u000f\u0005}&\u00021\u0001\u0002J\u0005QQ.\u001a;i_\u0012t\u0015-\\3\t\u000f\u0005\r'\u00021\u0001\u0002F\u0006)A/\u0019:hgB1\u0011\u0011PA@\u0003\u000f\u0004B!!\u000f\u0002J&!\u00111ZAg\u0005\u0011!\u0016\u0010]3\n\t\u0005=\u0017\u0011\u0002\u0002\u0006)f\u0004Xm\u001d\u0005\b\u0003'T\u0001\u0019AA<\u0003\u0011\t'oZ:\u0015\u0011\u00055\u0014q[An\u0003;Dq!!7\f\u0001\u0004\t\u0019,\u0001\u0004nKRDw\u000e\u001a\u0005\b\u0003\u0007\\\u0001\u0019AAc\u0011\u001d\t\u0019n\u0003a\u0001\u0003o\"b!!\u001c\u0002b\u0006\r\bbBAm\u0019\u0001\u0007\u00111\u0017\u0005\b\u0003'd\u0001\u0019AA<)\u0019\ti'a:\u0002l\"9\u0011\u0011^\u0007A\u0002\u00055\u0014A\u0002;be\u001e,G\u000fC\u0004\u0002T6\u0001\r!a\u001e\u0015\u0011\u00055\u0014q^Ay\u0003gDq!!-\u000f\u0001\u0004\t\u0019\fC\u0004\u0002@:\u0001\r!!\u0013\t\u000f\u0005Mg\u00021\u0001\u0002xQQ\u0011QNA|\u0003s\fY0!@\t\u000f\u0005Ev\u00021\u0001\u0002n!9\u0011\u0011\\\bA\u0002\u0005M\u0006bBAb\u001f\u0001\u0007\u0011Q\u0019\u0005\b\u0003'|\u0001\u0019AA<)!\tiG!\u0001\u0003\u0004\t\u0015\u0001bBAu!\u0001\u0007\u0011Q\u000e\u0005\b\u0003\u0007\u0004\u0002\u0019AAc\u0011\u001d\t\u0019\u000e\u0005a\u0001\u0003o\nQ\"\\6Ok2d\u0017M]=DC2dGCBA7\u0005\u0017\u0011i\u0001C\u0004\u0002ZF\u0001\r!a-\t\u000f\u0005\r\u0017\u00031\u0001\u0002F\u0006)Rn[!uiJL'-\u001e;fIF+\u0018\r\\5gS\u0016\u0014H\u0003BA7\u0005'AqA!\u0006\u0013\u0001\u0004\t9-A\u0002ua\u0016$b!!\u001c\u0003\u001a\tm\u0001b\u0002B\u000b'\u0001\u0007\u0011q\u0019\u0005\b\u0005;\u0019\u0002\u0019AAZ\u0003\u001d!XM]7Ts6\fq\"\\6BaBd\u00170\u00134OK\u0016$W\r\u001a\u000b\u0005\u0003[\u0012\u0019\u0003C\u0004\u0003&Q\u0001\r!!\u001c\u0002\tE,\u0018\r\\\u0001 [.\fE\u000f\u001e:jEV$X\rZ)vC2Lg-[3s\u0013\u001a\u0004vn]:jE2,G\u0003\u0002B\u0016\u0005c\u0001b!a\u0007\u0003.\u00055\u0014\u0002\u0002B\u0018\u0003#\u0011aa\u00149uS>t\u0007b\u0002B\u001a+\u0001\u0007\u0011qY\u0001\u0007aJ,g-\u001b=)\u0007U\u00119\u0004\u0005\u0003\u0003:\tmRBAAR\u0013\u0011\u0011i$a)\u0003\u000fQ\f\u0017\u000e\u001c:fG\u0006yQn[!uiJL'-\u001e;fIJ+g\r\u0006\u0004\u0003D\t%#Q\n\t\u0005\u0003s\u0011)%\u0003\u0003\u0003H\u0005\u0005#a\u0002*fMR\u0013X-\u001a\u0005\b\u0005\u00172\u0002\u0019AAd\u0003\r\u0001(/\u001a\u0005\b\u0005\u001f2\u0002\u0019AAZ\u0003\r\u0019\u00180\u001c\u000b\u0005\u0005\u0007\u0012\u0019\u0006C\u0004\u0003P]\u0001\r!a-\u0002#5\\WK\\1uiJL'-\u001e;fIJ+g\r\u0006\u0003\u0003D\te\u0003b\u0002B(1\u0001\u0007\u00111\u0017\u000b\u0005\u0005\u0007\u0012i\u0006C\u0004\u0003`e\u0001\r!!\u0013\u0002\u0011\u0019,H\u000e\u001c(b[\u0016\f\u0011b\u001d;bE&d\u0017N_3\u0015\t\u00055$Q\r\u0005\b\u0005OR\u0002\u0019AA7\u0003\u0011!(/Z3\u0002\u001bM$\u0018M\u00197f)f\u0004XMR8s)\u0011\t9M!\u001c\t\u000f\t\u001d4\u00041\u0001\u0002n\u0005)Rn[!uiJL'-\u001e;fIN#\u0018M\u00197f%\u00164GCBA7\u0005g\u0012)\bC\u0004\u0003Lq\u0001\r!a2\t\u000f\t=C\u00041\u0001\u00024R!\u0011Q\u000eB=\u0011\u001d\u0011y%\ba\u0001\u0003g\u000b\u0001#\\6BiR\u0014\u0018NY;uK\u0012$\u0006.[:\u0015\t\t}$Q\u0011\t\u0005\u0003s\u0011\t)\u0003\u0003\u0003\u0004\u0006\u0005#\u0001\u0002+iSNDqAa\u0014\u001f\u0001\u0004\t\u0019,A\tnW\u0006#HO]5ckR,G-\u00133f]R$BAa\u0011\u0003\f\"9!qJ\u0010A\u0002\u0005M\u0016AE7l\u0003R$(/\u001b2vi\u0016$7+\u001a7fGR$bAa\u0011\u0003\u0012\nM\u0005b\u0002B\u0013A\u0001\u0007\u0011Q\u000e\u0005\b\u0005\u001f\u0002\u0003\u0019AAZ\u0003-i7\u000eV=qK\u0006\u0003\b\u000f\\=\u0015\r\u00055$\u0011\u0014BO\u0011\u001d\u0011Y*\ta\u0001\u0003[\n1AZ;o\u0011\u001d\t\u0019-\ta\u0001\u0003o\n\u0011#\\6BaBd\u0017.\u001a3UsB,GK]3f)\u0019\tiGa)\u0003&\"9!1\u0014\u0012A\u0002\u00055\u0004bBAbE\u0001\u0007\u0011qO\u0001\u0016[.\fE\u000f\u001e:jEV$X\r\u001a+za\u0016\f\u0005\u000f\u001d7z)!\tiGa+\u0003.\n=\u0006bBAuG\u0001\u0007\u0011Q\u000e\u0005\b\u00033\u001c\u0003\u0019AAZ\u0011\u001d\t\u0019m\ta\u0001\u0003\u000b\f\u0011#\\6TS:<G.\u001a+za\u0016\f\u0005\u000f\u001d7z))\tiG!.\u0003:\nm&q\u0018\u0005\b\u0005o#\u0003\u0019AA7\u0003\u00151\u0018\r\\;f\u0011\u001d\u0011)\u0002\na\u0001\u0003\u000fDqA!0%\u0001\u0004\t\u0019,\u0001\u0003xQ\u0006$\bb\u0002BaI\u0001\u0007\u0011QR\u0001\foJ\f\u0007/\u00138BaBd\u00170\u0001\busB,G+Z:u'fl'm\u001c7\u0015\t\t\u001d'Q\u001a\t\u0005\u0003s\u0011I-\u0003\u0003\u0003L\u0006e&\u0001D'fi\"|GmU=nE>d\u0007b\u0002BhK\u0001\u0007\u0011QR\u0001\u0004C:L\u0018A\u0004;za\u0016\u001c\u0015m\u001d;Ts6\u0014w\u000e\u001c\u000b\u0005\u0005\u000f\u0014)\u000eC\u0004\u0003P\u001a\u0002\r!!$\u0002\u001d5\\\u0017j]%ogR\fgnY3PMRQ\u0011Q\u000eBn\u0005;\u0014yN!9\t\u000f\t]v\u00051\u0001\u0002n!9!QC\u0014A\u0002\u0005\u001d\u0007\"\u0003BhOA\u0005\t\u0019AAG\u0011%\u0011\tm\nI\u0001\u0002\u0004\ti)\u0001\rnW&\u001b\u0018J\\:uC:\u001cWm\u00144%I\u00164\u0017-\u001e7uIM\n\u0001$\\6Jg&s7\u000f^1oG\u0016|e\r\n3fM\u0006,H\u000e\u001e\u00135\u00039i7.Q:J]N$\u0018M\\2f\u001f\u001a$\"\"!\u001c\u0003l\n5(q\u001eBy\u0011\u001d\u00119L\u000ba\u0001\u0003[BqA!\u0006+\u0001\u0004\t9\rC\u0005\u0003P*\u0002\n\u00111\u0001\u0002\u000e\"I!\u0011\u0019\u0016\u0011\u0002\u0003\u0007\u0011QR\u0001\u0019[.\f5/\u00138ti\u0006t7-Z(gI\u0011,g-Y;mi\u0012\u001a\u0014\u0001G7l\u0003NLen\u001d;b]\u000e,wJ\u001a\u0013eK\u001a\fW\u000f\u001c;%i\u0005\u0019R.Y=cK6[\u0017i]%ogR\fgnY3PMRQ\u0011Q\u000eB~\u0005{\u001c\taa\u0001\t\u000f\t\u001dT\u00061\u0001\u0002n!9!q`\u0017A\u0002\u0005\u001d\u0017A\u00019u\u0011\u001d\u0011)\"\fa\u0001\u0003\u000fD\u0011b!\u0002.!\u0003\u0005\r!!$\u0002\u001f\t,gm\u001c:f%\u001647\t[3dWN\fQ$\\1zE\u0016l5.Q:J]N$\u0018M\\2f\u001f\u001a$C-\u001a4bk2$H\u0005N\u0001\n[.\u001cE.Y:t\u001f\u001a$B!!\u001c\u0004\u000e!91qB\u0018A\u0002\u0005\u001d\u0017A\u0001;q\u0003\u0015i7NT5m+\t\ti'\u0001\u0004nWj+'o\u001c\u000b\u0005\u0003[\u001aI\u0002C\u0004\u0004\u0010E\u0002\r!a2\u0002\u001d5\\7i\u001c8ti\u0006tGOW3s_R!1qDB\u0015!\u0011\tId!\t\n\t\r\r2Q\u0005\u0002\t\u0007>t7\u000f^1oi&!1qEA\u0005\u0005%\u0019uN\\:uC:$8\u000fC\u0004\u0004\u0010I\u0002\r!a2\u0002\u00155\\g*Y7fI\u0006\u0013x\r\u0006\u0004\u0002n\r=2\u0011\u0007\u0005\b\u0003\u000f\u001a\u0004\u0019AA%\u0011\u001d\u00119g\ra\u0001\u0003[\"b!!\u001c\u00046\re\u0002bBB\u001ci\u0001\u0007\u0011QN\u0001\u0004Y\"\u001c\bbBB\u001ei\u0001\u0007\u0011QN\u0001\u0004e\"\u001c\u0018aB7l)V\u0004H.\u001a\u000b\u0007\u0003[\u001a\te!\u0012\t\u000f\r\rS\u00071\u0001\u0002x\u0005)Q\r\\3ng\"I1qI\u001b\u0011\u0002\u0003\u0007\u0011QR\u0001\rM2\fG\u000f^3o+:\f'/_\u0001\u0012[.$V\u000f\u001d7fI\u0011,g-Y;mi\u0012\u0012\u0014!D7l\u0019&$XM]1m+:LG/\u0006\u0002\u0004PA!\u0011\u0011HB)\u0013\u0011\u0019\u0019&!\u0011\u0003\u000f1KG/\u001a:bY\u0006YQn[+oSR\u0014En\\2l)\u0011\u0019Ifa\u0018\u0011\t\u0005e21L\u0005\u0005\u0007;\n\tEA\u0003CY>\u001c7\u000eC\u0004\u0004ba\u0002\r!!\u001c\u0002\t\u0015D\bO]\u0001\f[.$V\u000f\u001d7f)f\u0004X\r\u0006\u0004\u0002n\r\u001d4\u0011\u000e\u0005\b\u0007\u0007J\u0004\u0019AA<\u0011%\u00199%\u000fI\u0001\u0002\u0004\ti)A\u000bnWR+\b\u000f\\3UsB,G\u0005Z3gCVdG\u000f\n\u001a\u0002\u000b5\\\u0017I\u001c3\u0015\r\u000554\u0011OB;\u0011\u001d\u0019\u0019h\u000fa\u0001\u0003[\nQ\u0001\u001e:fKFBqaa\u001e<\u0001\u0004\ti'A\u0003ue\u0016,''\u0001\u0003nW>\u0013HCBA7\u0007{\u001ay\bC\u0004\u0004tq\u0002\r!!\u001c\t\u000f\r]D\b1\u0001\u0002n\u0005!Rn\u001b*v]RLW.Z+oSZ,'o]3SK\u001a\f!\"\\6TKF\f\u0005\u000f\u001d7z)\u0011\u00199i!$\u0011\t\u0005e2\u0011R\u0005\u0005\u0007\u0017\u000b\tEA\u0003BaBd\u0017\u0010C\u0004\u0004\u0010z\u0002\r!!\u001c\u0002\u0007\u0005\u0014x-A\bnWN+\b/\u001a:J]&$8)\u00197m\u0003)i7\u000eV3na2\fG/\u001a\u000b\u000f\u0007/\u001bij!)\u0004,\u000eU6QXBa!\u0011\tId!'\n\t\rm\u0015\u0011\t\u0002\t)\u0016l\u0007\u000f\\1uK\"91q\u0014!A\u0002\u0005]\u0014a\u00029be\u0016tGo\u001d\u0005\b\u0007G\u0003\u0005\u0019ABS\u0003\u0011\u0019X\r\u001c4\u0011\t\u0005e2qU\u0005\u0005\u0007S\u000b\tE\u0001\u0004WC2$UM\u001a\u0005\b\u0007[\u0003\u0005\u0019ABX\u0003)\u0019wN\\:ue6{Gm\u001d\t\u0005\u0003s\u0019\t,\u0003\u0003\u00044\u0006\u0005#!C'pI&4\u0017.\u001a:t\u0011\u001d\u00199\f\u0011a\u0001\u0007s\u000b\u0001B\u001e9be\u0006l7o\u001d\t\u0007\u0003s\nyha/\u0011\r\u0005e\u0014qPBS\u0011\u001d\u0019y\f\u0011a\u0001\u0003o\nAAY8es\"I11\u0019!\u0011\u0002\u0003\u00071QY\u0001\tgV\u0004XM\u001d)pgB!\u0011\u0011HBd\u0013\u0011\u0019Ima3\u0003\u0011A{7/\u001b;j_:LAa!4\u0002\n\tI\u0001k\\:ji&|gn]\u0001\u0015[.$V-\u001c9mCR,G\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\rM'\u0006BBc\u00033\u000b\u0011\"\\6QCJ,g\u000e^:\u0015\u0011\re7q]Bv\u0007[\u0004baa7\u0004f\u00065TBABo\u0015\u0011\u0019yn!9\u0002\u0013%lW.\u001e;bE2,'\u0002BBr\u0003#\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\ti!8\t\u000f\r%(\t1\u0001\u00040\u0006Iqn\u001e8fe6{Gm\u001d\u0005\b\u0007?\u0013\u0005\u0019AA<\u0011%\u0019yO\u0011I\u0001\u0002\u0004\u0019)-A\u0005qCJ,g\u000e\u001e)pg\u0006\u0019Rn\u001b)be\u0016tGo\u001d\u0013eK\u001a\fW\u000f\u001c;%g\u0005QQn[\"mCN\u001cH)\u001a4\u0015\u0015\r]8Q C\u0001\t\u0013!)\u0002\u0005\u0003\u0002:\re\u0018\u0002BB~\u0003\u0003\u0012\u0001b\u00117bgN$UM\u001a\u0005\b\u0007\u007f$\u0005\u0019ABX\u0003\u0011iw\u000eZ:\t\u000f\u0005\u001dC\t1\u0001\u0005\u0004A!\u0011\u0011\bC\u0003\u0013\u0011!9!a\u0014\u0003\u0011QK\b/\u001a(b[\u0016Dq\u0001b\u0003E\u0001\u0004!i!A\u0004ua\u0006\u0014\u0018-\\:\u0011\r\u0005e\u0014q\u0010C\b!\u0011\tI\u0004\"\u0005\n\t\u0011M\u0011\u0011\t\u0002\b)f\u0004X\rR3g\u0011\u001d!9\u0002\u0012a\u0001\u0007/\u000bQ\u0001^3na2\fQ!\\6OK^$B\"!\u001c\u0005\u001e\u0011}A\u0011\u0005C\u0013\tSAqaa(F\u0001\u0004\t9\bC\u0004\u0004$\u0016\u0003\ra!*\t\u000f\u0011\rR\t1\u0001\u0002x\u0005)1\u000f^1ug\"9AqE#A\u0002\r\u0015\u0017\u0001\u00028q_NDq\u0001b\u000bF\u0001\u0004\u0019)-\u0001\u0003da>\u001c\u0018AE7l\rVt7\r^5p]RK\b/\u001a+sK\u0016$b!!\u001c\u00052\u0011M\u0002bBA;\r\u0002\u0007\u0011q\u000f\u0005\b\u0003\u000f3\u0005\u0019AA7\u0003=i7nU=oi\",G/[2V]&$HCAB(\u0003\u001di7N\u00117pG.$b!!\u001c\u0005>\u0011}\u0002b\u0002C\u0012\u0011\u0002\u0007\u0011q\u000f\u0005\n\t\u0003B\u0005\u0013!a\u0001\u0003\u001b\u000b\u0011\u0002Z8GY\u0006$H/\u001a8\u0002#5\\'\t\\8dW\u0012\"WMZ1vYR$#'A\u0007nWR\u0013X-Z(s\u00052|7m\u001b\u000b\u0005\u0003[\"I\u0005C\u0004\u0005$)\u0003\r!a\u001e\u0002\u00115\\\u0017i]:jO:$b!!\u001c\u0005P\u0011E\u0003bBB\u001c\u0017\u0002\u0007\u0011Q\u000e\u0005\b\u0007wY\u0005\u0019AA7\u0003=i7\u000eU1dW\u0006<Wm\u00142kK\u000e$H\u0003\u0003C,\t;\"9\u0007b\u001b\u0011\t\u0005eB\u0011L\u0005\u0005\t7\n\tE\u0001\u0006QC\u000e\\\u0017mZ3EK\u001aDq\u0001b\u0018M\u0001\u0004!\t'\u0001\u0003eK\u001at\u0007\u0003BA\u001d\tGJA\u0001\"\u001a\u0002B\tIQj\u001c3vY\u0016$UM\u001a\u0005\n\tSb\u0005\u0013!a\u0001\u0007\u000b\fa\u0001]5e!>\u001c\b\"\u0003C7\u0019B\u0005\t\u0019ABc\u0003\u0019\u00018n\u001a)pg\u0006IRn\u001b)bG.\fw-Z(cU\u0016\u001cG\u000f\n3fM\u0006,H\u000e\u001e\u00133\u0003ei7\u000eU1dW\u0006<Wm\u00142kK\u000e$H\u0005Z3gCVdG\u000fJ\u001a\u0002\u000fY\u000bGN\u0012:p[B\u0019\u00111\b)\u0003\u000fY\u000bGN\u0012:p[N\u0019\u0001+!\u0007\u0015\u0005\u0011U\u0014!B1qa2LHCBA7\t\u0003#)\tC\u0004\u0005\u0004J\u0003\r!!\u001c\u0002\u0007A\fG\u000fC\u0004\u0004<I\u0003\r!!\u001c\u0002\u000fUt\u0017\r\u001d9msR!A1\u0012CJ!\u0019\tYB!\f\u0005\u000eBA\u00111\u0004CH\u0003[\ni'\u0003\u0003\u0005\u0012\u0006E!A\u0002+va2,'\u0007C\u0004\u0003hM\u0003\r!!\u001c\u0002\u000bY\u000bG.R9\u0011\u0007\u0005mRKA\u0003WC2,\u0015oE\u0002V\u00033!\"\u0001b&\u0015\r\u00055D\u0011\u0015CR\u0011\u001d!\u0019i\u0016a\u0001\u0003[Bqaa\u000fX\u0001\u0004\ti\u0007\u0006\u0003\u0005\f\u0012\u001d\u0006b\u0002B41\u0002\u0007\u0011QN\u0001\u0007\r&dG/\u001a:\u0011\u0007\u0005m\"L\u0001\u0004GS2$XM]\n\u00045\u0006eAC\u0001CV)\u0011\u00199\t\".\t\u000f\t\u001dD\f1\u0001\u0002nQ!!1\u0006C]\u0011\u001d\u00119'\u0018a\u0001\u0003[\nQ!W5fY\u0012\u00042!a\u000f`\u0005\u0015I\u0016.\u001a7e'\ry\u0016\u0011\u0004\u000b\u0003\t{#B!!\u001c\u0005H\"9!qM1A\u0002\u00055D\u0003\u0002B\u0016\t\u0017DqAa\u001ac\u0001\u0004\ti'A\u0003nW\u001a{'\u000f\u0006\u0004\u0005R\u0012\rHq\u001d\u000b\u0005\u0003[\"\u0019\u000eC\u0004\u0005V\u000e\u0004\u001d\u0001b6\u0002\u000b\u0019\u0014Xm\u001d5\u0011\t\u0011eGq\\\u0007\u0003\t7TA\u0001\"8\u0002\n\u0005!Q\u000f^5m\u0013\u0011!\t\u000fb7\u0003!\u0019\u0013Xm\u001d5OC6,7I]3bi>\u0014\bb\u0002CsG\u0002\u0007\u0011qO\u0001\u0006K:,Xn\u001d\u0005\b\tS\u001c\u0007\u0019AA7\u0003%\u0019XoZ1s\u0005>$\u00170A\rqe>\u0004\u0018mZ1uK:{w+\u0019:o\u0003R$\u0018m\u00195nK:$HC\u0002Cx\to$\u0019P\u0004\u0003\u0005r\u0012MH\u0002\u0001\u0005\b\tk$\u0007\u0019ABS\u0003\t!x\u000eC\u0004\u0005z\u0012\u0004\r!!\u001c\u0002\t\u0019\u0014x.\\\u0001\u001eaJ|\u0007/Y4bi\u0016\u0004\u0016\r\u001e,be\u0012+g-\u0011;uC\u000eDW.\u001a8ugR1Aq`C\u0002\u000b\u0003qA\u0001\"=\u0006\u0002!9AQ_3A\u0002\r\u0015\u0006b\u0002C}K\u0002\u0007\u0011QN\u0001\t[.\u0004\u0016\r\u001e#fMR1Q\u0011BC\u0007\u000b\u001f!Baa/\u0006\f!9AQ\u001b4A\u0004\u0011]\u0007b\u0002CBM\u0002\u0007\u0011Q\u000e\u0005\b\u0007w1\u0007\u0019AA7)!)\u0019\"b\u0006\u0006\u001a\u0015mA\u0003BB^\u000b+Aq\u0001\"6h\u0001\b!9\u000eC\u0004\u0004\u0000\u001e\u0004\raa,\t\u000f\u0011\ru\r1\u0001\u0002n!911H4A\u0002\u00055DCCC\u0010\u000bG))#b\n\u0006*Q!11XC\u0011\u0011\u001d!)\u000e\u001ba\u0002\t/Dqaa@i\u0001\u0004\u0019y\u000bC\u0004\u0005\u0004\"\u0004\r!!\u001c\t\u000f\rm\u0002\u000e1\u0001\u0002n!9Q1\u00065A\u0002\r\u0015\u0017A\u0002:igB{7\u000f\u0006\u0007\u00060\u0015MRQGC\u001c\u000bs)Y\u0004\u0006\u0003\u0004<\u0016E\u0002b\u0002CkS\u0002\u000fAq\u001b\u0005\b\u0007\u007fL\u0007\u0019ABX\u0011\u001d!\u0019)\u001ba\u0001\u0003[Bqaa\u000fj\u0001\u0004\ti\u0007C\u0004\u0006,%\u0004\ra!2\t\u000f\u0015u\u0012\u000e1\u0001\u0002\u000e\u00061am\u001c:G_J\f1\"\\6HK:,'/\u0019;peRQQ1IC$\u000b\u0017*i%\"\u0015\u0015\t\u00055TQ\t\u0005\b\t+T\u00079\u0001Cl\u0011\u001d)IE\u001ba\u0001\u0007\u000b\f1\u0001]8t\u0011\u001d!\u0019I\u001ba\u0001\u0003[Bq!b\u0014k\u0001\u0004\ti)A\u0003wC2,\u0017\u000fC\u0004\u0004<)\u0004\r!!\u001c\u0002\u0015Utw/\u0019:oC\ndW\r\u0006\u0003\u0006X\u0015ec\u0002\u0002Cy\u000b3Bq\u0001b!l\u0001\u0004\ti'\u0001\nnW\u000eCWmY6JMJ+g-\u001e;bE2,GCBC0\u000bG*)\u0007\u0006\u0003\u0002n\u0015\u0005\u0004b\u0002CkY\u0002\u000fAq\u001b\u0005\b\t\u0007c\u0007\u0019AA7\u0011\u001d\u0019Y\u0004\u001ca\u0001\u0003[\nq\"\\1uG\"4\u0016M\u001d)biR,'O\u001c\u000b\u0005\u000bW*y\u0007\u0005\u0004\u0002\u001c\t5RQ\u000e\t\t\u00037!y)!\u0013\u0002n!9!qM7A\u0002\u00055\u0014!C7l-&\u001c\u0018\u000e^8s)!))(\"\u001f\u0006\u0006\u0016%E\u0003BA7\u000boBq\u0001\"6o\u0001\b!9\u000eC\u0004\u0006|9\u0004\r!\" \u0002\u000b\r\f7/Z:\u0011\r\u0005e\u0014qPC@!\u0011\tI$\"!\n\t\u0015\r\u0015\u0011\t\u0002\b\u0007\u0006\u001cX\rR3g\u0011\u001d)9I\u001ca\u0001\u0003\u001b\u000bqb\u00195fG.,\u0005\u0010[1vgRLg/\u001a\u0005\n\u0005gq\u0007\u0013!a\u0001\u000b\u0017\u0003B!\"$\u0006\u001c:!QqRCL!\u0011)\t*!\u0005\u000e\u0005\u0015M%\u0002BCK\u0003+\ta\u0001\u0010:p_Rt\u0014\u0002BCM\u0003#\ta\u0001\u0015:fI\u00164\u0017\u0002BCO\u000b?\u0013aa\u0015;sS:<'\u0002BCM\u0003#\t1#\\6WSNLGo\u001c:%I\u00164\u0017-\u001e7uIM*\"!\"*+\t\u0015-\u0015\u0011\u0014\u0002\u0010\u000f\u0016$h+\u0019:Ue\u00064XM]:feN\u0019\u0001/b+\u0011\t\u0005eRQV\u0005\u0005\u000b_+\tLA\u0005Ue\u00064XM]:fe&!\u00111ICZ\u0015\u0011)),!\u0004\u0002\u0007\u0005\u0004\u0018\u000e\u0006\u0002\u0006:B\u0019\u00111\b9\u0002\u0007\t,h-\u0006\u0002\u0006@B1Q\u0011YCd\u000b\u0017l!!b1\u000b\t\u0015\u00157\u0011]\u0001\b[V$\u0018M\u00197f\u0013\u0011)I-b1\u0003\u00151K7\u000f\u001e\"vM\u001a,'\u000f\u0005\u0007\u0002\u001c\u00155\u0017\u0011JA7\u0007\u000b\fi'\u0003\u0003\u0006P\u0006E!A\u0002+va2,G'\u0001\u0003ck\u001a\u0004\u0013a\u00028b[\u0016\u0004vn\u001d\u000b\u0007\u0007\u000b,9.\"7\t\u000f\t\u001dD\u000f1\u0001\u0002n!9\u0011q\t;A\u0002\u0005%\u0013\u0001\u0003;sCZ,'o]3\u0015\t\u0015}WQ\u001d\t\u0005\u00037)\t/\u0003\u0003\u0006d\u0006E!\u0001B+oSRDqAa\u001av\u0001\u0004\ti\u0007\u0006\u0003\u0006j\u0016-\bCBBn\u0007K,Y\rC\u0004\u0003hY\u0004\r!!\u001c\u0002\u0019\u001d,GOV1sS\u0006\u0014G.Z:\u0015\t\u0015EX1\u001f\t\u0007\u0003s\ny(b3\t\u000f\t\u001dt\u000f1\u0001\u0002n\t\t\u0002+\u0019;wCJ$&/\u00198tM>\u0014X.\u001a:\u0014\u0007a,I\u0010\u0005\u0003\u0002:\u0015m\u0018\u0002BC\u007f\u000bc\u00131\u0002\u0016:b]N4wN]7feR\u0011a\u0011\u0001\t\u0004\u0003wA\u0018!\u0003;sC:\u001chm\u001c:n)\u0011\tiGb\u0002\t\u000f\t\u001d$\u00101\u0001\u0002n\u0005\u0001\u0012n\u001d)biZ\u000b'oV1s]\u0006\u0014G.Z\u000b\u0003\u0003\u001b\u000b\u0001#[:WCJ$UMZ,be:\f'\r\\3\u0002#A\fGO^1s)J\fgn\u001d4pe6,'\u000fE\u0002\u0002<y\u0014\u0011\u0003]1um\u0006\u0014HK]1og\u001a|'/\\3s'\rqh\u0011\u0001\u000b\u0003\r#\t1\"\\6V]\u000eDWmY6fIR!\u0011Q\u000eD\u000f\u0011!\u0019\t'!\u0001A\u0002\u00055\u0014\u0001E7l'ftG\u000f[3uS\u000e\u0004\u0016M]1n)\u0011\u0019)Kb\t\t\u0011\u0019\u0015\u00121\u0001a\u0001\rO\tQ\u0001\u001d8b[\u0016\u0004B!!\u000f\u0007*%!a1FA(\u0005!!VM]7OC6,\u0017AB7l\u0007\u0006\u001cH\u000f\u0006\u0004\u0002n\u0019Eb1\u0007\u0005\t\u0005O\n)\u00011\u0001\u0002n!A!q`A\u0003\u0001\u0004\t9\r"
)
public abstract class TreeGen {
   private volatile ValFrom$ ValFrom$module;
   private volatile ValEq$ ValEq$module;
   private volatile Filter$ Filter$module;
   private volatile Yield$ Yield$module;
   private volatile patvarTransformer$ patvarTransformer$module;

   public ValFrom$ ValFrom() {
      if (this.ValFrom$module == null) {
         this.ValFrom$lzycompute$1();
      }

      return this.ValFrom$module;
   }

   public ValEq$ ValEq() {
      if (this.ValEq$module == null) {
         this.ValEq$lzycompute$1();
      }

      return this.ValEq$module;
   }

   public Filter$ Filter() {
      if (this.Filter$module == null) {
         this.Filter$lzycompute$1();
      }

      return this.Filter$module;
   }

   public Yield$ Yield() {
      if (this.Yield$module == null) {
         this.Yield$lzycompute$1();
      }

      return this.Yield$module;
   }

   public patvarTransformer$ patvarTransformer() {
      if (this.patvarTransformer$module == null) {
         this.patvarTransformer$lzycompute$1();
      }

      return this.patvarTransformer$module;
   }

   public abstract SymbolTable global();

   public Trees.Select rootId(final Names.Name name) {
      return this.global().new Select(this.global().new Ident(this.global().nme().ROOTPKG()), name);
   }

   public Trees.Select rootScalaDot(final Names.Name name) {
      return this.global().new Select(this.rootId(this.global().nme().scala_()).setSymbol(this.global().definitions().ScalaPackage()), name);
   }

   public Trees.Select scalaDot(final Names.Name name) {
      return this.global().new Select((this.global().new Ident(this.global().nme().scala_())).setSymbol(this.global().definitions().ScalaPackage()), name);
   }

   public Trees.Select scalaAnnotationDot(final Names.Name name) {
      return this.global().new Select(this.scalaDot(this.global().nme().annotation()), name);
   }

   public Trees.Select scalaAnyRefConstr() {
      return this.scalaDot(this.global().tpnme().AnyRef());
   }

   public Trees.Tree scalaFunctionConstr(final List argtpes, final Trees.Tree restpe, final boolean abstractFun) {
      Trees.RefTree cls = abstractFun ? this.mkAttributedRef(this.global().definitions().AbstractFunctionClass().apply(argtpes.length())) : this.mkAttributedRef(this.global().definitions().FunctionClass().apply(argtpes.length()));
      return this.global().new AppliedTypeTree((Trees.Tree)cls, (List)StrictOptimizedSeqOps.appended$(argtpes, restpe));
   }

   public boolean scalaFunctionConstr$default$3() {
      return false;
   }

   public Trees.Tree mkMethodCall(final Symbols.Symbol receiver, final Names.Name methodName, final List targs, final List args) {
      return this.mkMethodCall((Trees.Tree)(this.global().new Select((Trees.Tree)this.mkAttributedRef(receiver), methodName)), (List)targs, args);
   }

   public Trees.Tree mkMethodCall(final Symbols.Symbol method, final List targs, final List args) {
      return this.mkMethodCall((Trees.Tree)this.mkAttributedRef(method), targs, args);
   }

   public Trees.Tree mkMethodCall(final Symbols.Symbol method, final List args) {
      return this.mkMethodCall((Symbols.Symbol)method, (List).MODULE$, args);
   }

   public Trees.Tree mkMethodCall(final Trees.Tree target, final List args) {
      return this.mkMethodCall((Trees.Tree)target, (List).MODULE$, args);
   }

   public Trees.Tree mkMethodCall(final Symbols.Symbol receiver, final Names.Name methodName, final List args) {
      return this.mkMethodCall((Symbols.Symbol)receiver, (Names.Name)methodName, .MODULE$, args);
   }

   public Trees.Tree mkMethodCall(final Trees.Tree receiver, final Symbols.Symbol method, final List targs, final List args) {
      return this.mkMethodCall((Trees.Tree)this.global().Select(receiver, method), (List)targs, args);
   }

   public Trees.Tree mkMethodCall(final Trees.Tree target, final List targs, final List args) {
      Trees.Apply var10000 = new Trees.Apply;
      SymbolTable var10002 = this.global();
      if (this.global() == null) {
         throw null;
      } else {
         Object var10005;
         if (targs == .MODULE$) {
            var10005 = .MODULE$;
         } else {
            Types.Type var8 = (Types.Type)targs.head();
            scala.collection.immutable..colon.colon mapList_head = new scala.collection.immutable..colon.colon($anonfun$mkMethodCall$1(this, var8), .MODULE$);
            scala.collection.immutable..colon.colon mapList_tail = mapList_head;

            for(List mapList_rest = (List)targs.tail(); mapList_rest != .MODULE$; mapList_rest = (List)mapList_rest.tail()) {
               var8 = (Types.Type)mapList_rest.head();
               scala.collection.immutable..colon.colon mapList_next = new scala.collection.immutable..colon.colon($anonfun$mkMethodCall$1(this, var8), .MODULE$);
               mapList_tail.next_$eq(mapList_next);
               mapList_tail = mapList_next;
            }

            Statics.releaseFence();
            var10005 = mapList_head;
         }

         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         Object var12 = null;
         var10000.<init>(this.mkTypeApply(target, (List)var10005), args);
         return var10000;
      }
   }

   public Trees.Tree mkNullaryCall(final Symbols.Symbol method, final List targs) {
      Trees.Tree var10001 = (Trees.Tree)this.mkAttributedRef(method);
      if (this.global() == null) {
         throw null;
      } else {
         Object var10002;
         if (targs == .MODULE$) {
            var10002 = .MODULE$;
         } else {
            Types.Type var7 = (Types.Type)targs.head();
            scala.collection.immutable..colon.colon mapList_head = new scala.collection.immutable..colon.colon($anonfun$mkNullaryCall$1(this, var7), .MODULE$);
            scala.collection.immutable..colon.colon mapList_tail = mapList_head;

            for(List mapList_rest = (List)targs.tail(); mapList_rest != .MODULE$; mapList_rest = (List)mapList_rest.tail()) {
               var7 = (Types.Type)mapList_rest.head();
               scala.collection.immutable..colon.colon mapList_next = new scala.collection.immutable..colon.colon($anonfun$mkNullaryCall$1(this, var7), .MODULE$);
               mapList_tail.next_$eq(mapList_next);
               mapList_tail = mapList_next;
            }

            Statics.releaseFence();
            var10002 = mapList_head;
         }

         Object var8 = null;
         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         return this.mkTypeApply(var10001, (List)var10002);
      }
   }

   public Trees.Tree mkAttributedQualifier(final Types.Type tpe) {
      return this.mkAttributedQualifier(tpe, this.global().NoSymbol());
   }

   public Trees.Tree mkAttributedQualifier(final Types.Type tpe, final Symbols.Symbol termSym) {
      if (this.global().NoPrefix().equals(tpe)) {
         return this.global().EmptyTree();
      } else if (tpe instanceof Types.ThisType) {
         Symbols.Symbol clazz = ((Types.ThisType)tpe).sym();
         return (Trees.Tree)(clazz.isEffectiveRoot() ? this.global().EmptyTree() : this.mkAttributedThis(clazz));
      } else if (tpe instanceof Types.SingleType) {
         Types.SingleType var4 = (Types.SingleType)tpe;
         Types.Type pre = var4.pre();
         Symbols.Symbol sym = var4.sym();
         return this.mkApplyIfNeeded(this.mkAttributedStableRef(pre, sym));
      } else if (tpe instanceof Types.TypeRef) {
         Types.TypeRef var7 = (Types.TypeRef)tpe;
         Types.Type pre = var7.pre();
         Symbols.Symbol sym = var7.sym();
         if (sym.isRoot()) {
            return this.mkAttributedThis(sym);
         } else if (sym.isModuleClass()) {
            return this.mkApplyIfNeeded((Trees.Tree)this.mkAttributedRef(pre, sym.sourceModule()));
         } else if (!sym.isModule() && !sym.isClass()) {
            if (!sym.isType()) {
               return (Trees.Tree)this.mkAttributedRef(pre, sym);
            } else {
               SymbolTable var30;
               boolean var10001;
               label95: {
                  label94: {
                     var30 = this.global();
                     Symbols.NoSymbol var10 = this.global().NoSymbol();
                     if (termSym == null) {
                        if (var10 != null) {
                           break label94;
                        }
                     } else if (!termSym.equals(var10)) {
                        break label94;
                     }

                     var10001 = false;
                     break label95;
                  }

                  var10001 = true;
               }

               boolean assert_assertion = var10001;
               if (var30 == null) {
                  throw null;
               } else {
                  SymbolTable assert_this = var30;
                  if (!assert_assertion) {
                     throw assert_this.throwAssertionError(failMessage$1(tpe, termSym));
                  } else {
                     assert_this = null;
                     return ((Trees.Tree)this.mkAttributedIdent(termSym)).setType(tpe);
                  }
               }
            }
         } else {
            SymbolTable var29 = this.global();
            boolean assert_assertion = this.global().phase().erasedTypes();
            if (var29 == null) {
               throw null;
            } else {
               SymbolTable assert_this = var29;
               if (!assert_assertion) {
                  throw assert_this.throwAssertionError(failMessage$1(tpe, termSym));
               } else {
                  assert_this = null;
                  return this.mkAttributedThis(sym);
               }
            }
         }
      } else {
         if (tpe instanceof Types.ConstantType) {
            Types.ConstantType var11 = (Types.ConstantType)tpe;
            Some var12 = this.global().ConstantType().unapply(var11);
            if (!var12.isEmpty()) {
               Constants.Constant value = (Constants.Constant)var12.value();
               return (this.global().new Literal(value)).setType(tpe);
            }
         }

         if (tpe instanceof Types.AnnotatedType) {
            Types.Type atp = ((Types.AnnotatedType)tpe).underlying();
            return this.mkAttributedQualifier(atp);
         } else if (!(tpe instanceof Types.RefinedType)) {
            throw this.global().abort((new StringBuilder(24)).append("bad qualifier received: ").append(failMessage$1(tpe, termSym)).toString());
         } else {
            List parents = ((Types.RefinedType)tpe).parents();
            if (parents == null) {
               throw null;
            } else {
               List find_these = parents;

               Object var10000;
               while(true) {
                  if (find_these.isEmpty()) {
                     var10000 = scala.None..MODULE$;
                     break;
                  }

                  if (((Types.Type)find_these.head()).isStable()) {
                     var10000 = new Some(find_these.head());
                     break;
                  }

                  find_these = (List)find_these.tail();
               }

               Object var27 = null;
               Option firstStable = (Option)var10000;
               SymbolTable var28 = this.global();
               boolean assert_assertion = !firstStable.isEmpty();
               if (var28 == null) {
                  throw null;
               } else {
                  SymbolTable assert_this = var28;
                  if (!assert_assertion) {
                     throw assert_this.throwAssertionError($anonfun$mkAttributedQualifier$4(parents, tpe, termSym));
                  } else {
                     assert_this = null;
                     return this.mkAttributedQualifier((Types.Type)firstStable.get());
                  }
               }
            }
         }
      }
   }

   public Trees.Tree mkApplyIfNeeded(final Trees.Tree qual) {
      Types.Type var2 = qual.tpe();
      if (var2 instanceof Types.MethodType) {
         Types.MethodType var3 = (Types.MethodType)var2;
         List var4 = var3.params();
         Types.Type restpe = var3.resultType();
         if (.MODULE$.equals(var4)) {
            return this.global().atPos(qual.pos(), (this.global().new Apply(qual, .MODULE$)).setType(restpe));
         }
      }

      return qual;
   }

   public final Option mkAttributedQualifierIfPossible(final Types.Type prefix) {
      while(!(this.global().NoType().equals(prefix) ? true : (this.global().NoPrefix().equals(prefix) ? true : this.global().ErrorType().equals(prefix)))) {
         if (prefix instanceof Types.TypeRef) {
            Symbols.Symbol sym = ((Types.TypeRef)prefix).sym();
            if (sym.isModule() || sym.isClass() || sym.isType()) {
               return scala.None..MODULE$;
            }
         }

         if (prefix instanceof Types.RefinedType) {
            List parents = ((Types.RefinedType)prefix).parents();
            if (parents == null) {
               throw null;
            }

            List exists_these = parents;

            boolean var10000;
            while(true) {
               if (exists_these.isEmpty()) {
                  var10000 = false;
                  break;
               }

               if (((Types.Type)exists_these.head()).isStable()) {
                  var10000 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var5 = null;
            if (!var10000) {
               return scala.None..MODULE$;
            }
         }

         if (!(prefix instanceof Types.AnnotatedType)) {
            return new Some(this.mkAttributedQualifier(prefix));
         }

         prefix = ((Types.AnnotatedType)prefix).underlying();
      }

      return scala.None..MODULE$;
   }

   public Trees.RefTree mkAttributedRef(final Types.Type pre, final Symbols.Symbol sym) {
      Trees.Tree qual = this.mkAttributedQualifier(pre);
      if (this.global().EmptyTree().equals(qual)) {
         return this.mkAttributedIdent(sym);
      } else {
         return qual instanceof Trees.This && qual.symbol().isEffectiveRoot() ? this.mkAttributedIdent(sym) : this.mkAttributedSelect(qual, sym);
      }
   }

   public Trees.RefTree mkAttributedRef(final Symbols.Symbol sym) {
      if (sym.owner().isStaticOwner()) {
         if (sym.owner().isRoot()) {
            return this.mkAttributedIdent(sym);
         } else {
            SymbolTable var10000;
            boolean var10001;
            label34: {
               label33: {
                  Symbols.Symbol ownerModule = sym.owner().sourceModule();
                  var10000 = this.global();
                  Symbols.NoSymbol var3 = this.global().NoSymbol();
                  if (ownerModule == null) {
                     if (var3 != null) {
                        break label33;
                     }
                  } else if (!ownerModule.equals(var3)) {
                     break label33;
                  }

                  var10001 = false;
                  break label34;
               }

               var10001 = true;
            }

            boolean assert_assertion = var10001;
            if (var10000 == null) {
               throw null;
            } else {
               SymbolTable assert_this = var10000;
               if (!assert_assertion) {
                  throw assert_this.throwAssertionError(sym.owner());
               } else {
                  assert_this = null;
                  return this.mkAttributedSelect((Trees.Tree)this.mkAttributedRef(sym.owner().sourceModule()), sym);
               }
            }
         }
      } else {
         return sym.owner().isClass() ? this.mkAttributedRef(sym.owner().thisType(), sym) : this.mkAttributedIdent(sym);
      }
   }

   public Trees.RefTree mkUnattributedRef(final Symbols.Symbol sym) {
      return this.mkUnattributedRef(sym.fullNameAsName('.'));
   }

   public Trees.RefTree mkUnattributedRef(final Names.Name fullName) {
      List var2 = this.global().nme().segments(fullName.toString(), fullName.isTermName());
      if (var2 instanceof scala.collection.immutable..colon.colon) {
         scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)var2;
         Names.Name hd = (Names.Name)var3.head();
         List tl = var3.next$access$1();
         Trees.Ident var10001 = this.global().new Ident(hd);
         Function2 foldLeft_op = (x$4, x$5) -> this.global().new Select((Trees.Tree)x$4, x$5);
         Trees.Ident foldLeft_z = var10001;
         if (tl == null) {
            throw null;
         } else {
            return (Trees.RefTree)LinearSeqOps.foldLeft$(tl, foldLeft_z, foldLeft_op);
         }
      } else {
         throw new MatchError(var2);
      }
   }

   public Trees.Tree stabilize(final Trees.Tree tree) {
      Types.Type var2 = this.stableTypeFor(tree);
      return this.global().NoType().equals(var2) ? tree : tree.setType(var2);
   }

   public Types.Type stableTypeFor(final Trees.Tree tree) {
      if (!this.global().treeInfo().admitsTypeSelection(tree)) {
         return this.global().NoType();
      } else if (tree instanceof Trees.This) {
         return this.global().ThisType().apply(tree.symbol());
      } else if (tree instanceof Trees.Ident) {
         return this.global().singleType(tree.symbol().owner().thisType(), tree.symbol());
      } else if (tree instanceof Trees.Select) {
         Trees.Tree qual = ((Trees.Select)tree).qualifier();
         return this.global().singleType(qual.tpe(), tree.symbol());
      } else {
         return this.global().NoType();
      }
   }

   public Trees.Tree mkAttributedStableRef(final Types.Type pre, final Symbols.Symbol sym) {
      return this.stabilize((Trees.Tree)this.mkAttributedRef(pre, sym));
   }

   public Trees.Tree mkAttributedStableRef(final Symbols.Symbol sym) {
      return this.stabilize((Trees.Tree)this.mkAttributedRef(sym));
   }

   public Trees.This mkAttributedThis(final Symbols.Symbol sym) {
      return (Trees.This)(this.global().new This(sym.name().toTypeName())).setSymbol(sym).setType(sym.thisType());
   }

   public Trees.RefTree mkAttributedIdent(final Symbols.Symbol sym) {
      return (Trees.RefTree)(this.global().new Ident(sym.name())).setSymbol(sym).setType(sym.tpeHK());
   }

   public Trees.RefTree mkAttributedSelect(final Trees.Tree qual, final Symbols.Symbol sym) {
      if (qual.symbol() == null || !qual.symbol().isEffectiveRoot() && !qual.symbol().isEmptyPackage()) {
         Symbols.Symbol qualsym = (Symbols.Symbol)(qual.tpe() != null ? qual.tpe().typeSymbol() : (qual.symbol() != null ? qual.symbol() : this.global().NoSymbol()));
         Trees.Tree var10000;
         if (sym != null && qualsym.hasPackageFlag() && !sym.isDefinedInPackage() && !sym.moduleClass().isDefinedInPackage()) {
            Symbols.Symbol packageObject = qualsym.packageObject();
            var10000 = (this.global().new Select(qual, this.global().nme().PACKAGE())).setSymbol(packageObject).setType(packageObject.typeOfThis());
         } else {
            var10000 = qual;
         }

         Trees.Tree pkgQualifier = var10000;
         Trees.Select tree = this.global().Select(pkgQualifier, sym);
         if (pkgQualifier.tpe() == null) {
            return tree;
         } else {
            Types.Type var10;
            label84: {
               Symbols.Symbol var10001 = sym.rawowner();
               Symbols.ClassSymbol var7 = this.global().definitions().ObjectClass();
               if (var10001 == null) {
                  if (var7 == null) {
                     break label84;
                  }
               } else if (var10001.equals(var7)) {
                  break label84;
               }

               var10001 = sym.rawowner();
               Symbols.ClassSymbol var8 = this.global().definitions().AnyClass();
               if (var10001 == null) {
                  if (var8 == null) {
                     break label84;
                  }
               } else if (var10001.equals(var8)) {
                  break label84;
               }

               var10 = qual.tpe().memberType(sym);
               return (Trees.RefTree)tree.setType(var10);
            }

            var10 = sym.tpeHK().normalize();
            return (Trees.RefTree)tree.setType(var10);
         }
      } else {
         return this.mkAttributedIdent(sym);
      }
   }

   public Trees.Tree mkTypeApply(final Trees.Tree fun, final List targs) {
      return (Trees.Tree)(targs.isEmpty() ? fun : this.global().new TypeApply(fun, targs));
   }

   public Trees.Tree mkAppliedTypeTree(final Trees.Tree fun, final List targs) {
      return (Trees.Tree)(targs.isEmpty() ? fun : this.global().new AppliedTypeTree(fun, targs));
   }

   public Trees.Tree mkAttributedTypeApply(final Trees.Tree target, final Symbols.Symbol method, final List targs) {
      Trees.Tree var10001 = (Trees.Tree)this.mkAttributedSelect(target, method);
      if (targs == null) {
         throw null;
      } else {
         Object var10002;
         if (targs == .MODULE$) {
            var10002 = .MODULE$;
         } else {
            Types.Type var8 = (Types.Type)targs.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mkAttributedTypeApply$1(this, var8), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)targs.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var8 = (Types.Type)map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mkAttributedTypeApply$1(this, var8), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10002 = map_h;
         }

         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         Object var12 = null;
         return this.mkTypeApply(var10001, (List)var10002);
      }
   }

   private Trees.Tree mkSingleTypeApply(final Trees.Tree value, final Types.Type tpe, final Symbols.Symbol what, final boolean wrapInApply) {
      Types.Type var6 = tpe.dealias();
      List $colon$colon_this = .MODULE$;
      scala.collection.immutable..colon.colon var10003 = new scala.collection.immutable..colon.colon(var6, $colon$colon_this);
      $colon$colon_this = null;
      Trees.Tree tapp = this.mkAttributedTypeApply(value, what, var10003);
      return (Trees.Tree)(wrapInApply ? this.global().new Apply(tapp, .MODULE$) : tapp);
   }

   private Symbols.MethodSymbol typeTestSymbol(final boolean any) {
      return any ? this.global().definitions().Any_isInstanceOf() : this.global().definitions().Object_isInstanceOf();
   }

   private Symbols.MethodSymbol typeCastSymbol(final boolean any) {
      return any ? this.global().definitions().Any_asInstanceOf() : this.global().definitions().Object_asInstanceOf();
   }

   public Trees.Tree mkIsInstanceOf(final Trees.Tree value, final Types.Type tpe, final boolean any, final boolean wrapInApply) {
      return this.mkSingleTypeApply(value, tpe, this.typeTestSymbol(any), wrapInApply);
   }

   public boolean mkIsInstanceOf$default$3() {
      return true;
   }

   public boolean mkIsInstanceOf$default$4() {
      return true;
   }

   public Trees.Tree mkAsInstanceOf(final Trees.Tree value, final Types.Type tpe, final boolean any, final boolean wrapInApply) {
      return this.mkSingleTypeApply(value, tpe, this.typeCastSymbol(any), wrapInApply);
   }

   public boolean mkAsInstanceOf$default$3() {
      return true;
   }

   public boolean mkAsInstanceOf$default$4() {
      return true;
   }

   public Trees.Tree maybeMkAsInstanceOf(final Trees.Tree tree, final Types.Type pt, final Types.Type tpe, final boolean beforeRefChecks) {
      Types.Type var5 = this.global().definitions().UnitTpe();
      if (pt == null) {
         if (var5 == null) {
            return tree;
         }
      } else if (pt.equals(var5)) {
         return tree;
      }

      if (!tpe.$less$colon$less(pt)) {
         return this.global().atPos(tree.pos(), this.mkAsInstanceOf(tree, pt, true, !beforeRefChecks));
      } else {
         return tree;
      }
   }

   public boolean maybeMkAsInstanceOf$default$4() {
      return false;
   }

   public Trees.Tree mkClassOf(final Types.Type tp) {
      return (this.global().new Literal(this.global().new Constant(tp))).setType(this.global().ConstantType().apply(this.global().new Constant(tp)));
   }

   public Trees.Tree mkNil() {
      return (Trees.Tree)this.mkAttributedRef(this.global().definitions().NilModule());
   }

   public Trees.Tree mkZero(final Types.Type tp) {
      Symbols.Symbol var2 = tp.typeSymbol();
      return this.global().definitions().NothingClass().equals(var2) ? this.mkMethodCall((Symbols.Symbol)this.global().definitions().Predef_$qmark$qmark$qmark(), .MODULE$).setType(this.global().definitions().NothingTpe()) : (this.global().new Literal(this.mkConstantZero(tp))).setType(tp);
   }

   public Constants.Constant mkConstantZero(final Types.Type tp) {
      Symbols.Symbol var2 = tp.typeSymbol();
      Symbols.ClassSymbol var10000 = this.global().definitions().UnitClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant(BoxedUnit.UNIT);
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant(BoxedUnit.UNIT);
      }

      var10000 = this.global().definitions().BooleanClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant(false);
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant(false);
      }

      var10000 = this.global().definitions().FloatClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant(0.0F);
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant(0.0F);
      }

      var10000 = this.global().definitions().DoubleClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant((double)0.0F);
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant((double)0.0F);
      }

      var10000 = this.global().definitions().ByteClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant((byte)0);
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant((byte)0);
      }

      var10000 = this.global().definitions().ShortClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant((short)0);
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant((short)0);
      }

      var10000 = this.global().definitions().IntClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant(0);
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant(0);
      }

      var10000 = this.global().definitions().LongClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant(0L);
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant(0L);
      }

      var10000 = this.global().definitions().CharClass();
      if (var10000 == null) {
         if (var2 == null) {
            return this.global().new Constant('\u0000');
         }
      } else if (var10000.equals(var2)) {
         return this.global().new Constant('\u0000');
      }

      return this.global().new Constant((Object)null);
   }

   public Trees.Tree mkNamedArg(final Names.Name name, final Trees.Tree tree) {
      return this.mkNamedArg((Trees.Tree)(this.global().new Ident(name)), tree);
   }

   public Trees.Tree mkNamedArg(final Trees.Tree lhs, final Trees.Tree rhs) {
      return this.global().atPos((Position)rhs.pos(), (Trees.Tree)(this.global().new NamedArg(lhs, rhs)));
   }

   public Trees.Tree mkTuple(final List elems, final boolean flattenUnary) {
      if (.MODULE$.equals(elems)) {
         return this.mkLiteralUnit();
      } else {
         if (elems instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)elems;
            Trees.Tree tree = (Trees.Tree)var3.head();
            List var5 = var3.next$access$1();
            if (.MODULE$.equals(var5) && flattenUnary) {
               return tree;
            }
         }

         return this.global().new Apply(this.scalaDot(this.global().definitions().TupleClass().apply(elems.length()).name().toTermName()), elems);
      }
   }

   public boolean mkTuple$default$2() {
      return true;
   }

   public Trees.Literal mkLiteralUnit() {
      return this.global().new Literal(this.global().new Constant(BoxedUnit.UNIT));
   }

   public Trees.Block mkUnitBlock(final Trees.Tree expr) {
      return this.global().new Block(new scala.collection.immutable..colon.colon(expr, .MODULE$), this.mkLiteralUnit());
   }

   public Trees.Tree mkTupleType(final List elems, final boolean flattenUnary) {
      if (.MODULE$.equals(elems)) {
         return this.scalaDot(this.global().tpnme().Unit());
      } else {
         if (elems != null) {
            List var10000 = scala.package..MODULE$.List();
            if (var10000 == null) {
               throw null;
            }

            List unapplySeq_this = var10000;
            SeqOps var9 = SeqFactory.unapplySeq$(unapplySeq_this, elems);
            Object var8 = null;
            SeqOps var3 = var9;
            SeqFactory.UnapplySeqWrapper var10 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            new SeqFactory.UnapplySeqWrapper(var3);
            var10 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            var10 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            int lengthCompare$extension_len = 1;
            if (var3.lengthCompare(lengthCompare$extension_len) == 0) {
               var10 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var10 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int apply$extension_i = 0;
               Trees.Tree tree = (Trees.Tree)var3.apply(apply$extension_i);
               if (flattenUnary) {
                  return tree;
               }
            }
         }

         return this.global().new AppliedTypeTree(this.scalaDot(this.global().definitions().TupleClass().apply(elems.length()).name()), elems);
      }
   }

   public boolean mkTupleType$default$2() {
      return true;
   }

   public Trees.Tree mkAnd(final Trees.Tree tree1, final Trees.Tree tree2) {
      Trees.Apply var10000 = new Trees.Apply;
      SymbolTable var10002 = this.global();
      SymbolTable var10003 = this.global();
      Definitions.definitions$ var10005 = this.global().definitions();
      if (var10005 == null) {
         throw null;
      } else {
         var10000.<init>(var10003.Select((Trees.Tree)tree1, (Symbols.Symbol)Definitions.ValueClassDefinitions.Boolean_and$(var10005)), new scala.collection.immutable..colon.colon(tree2, .MODULE$));
         return var10000;
      }
   }

   public Trees.Tree mkOr(final Trees.Tree tree1, final Trees.Tree tree2) {
      Trees.Apply var10000 = new Trees.Apply;
      SymbolTable var10002 = this.global();
      SymbolTable var10003 = this.global();
      Definitions.definitions$ var10005 = this.global().definitions();
      if (var10005 == null) {
         throw null;
      } else {
         var10000.<init>(var10003.Select((Trees.Tree)tree1, (Symbols.Symbol)Definitions.ValueClassDefinitions.Boolean_or$(var10005)), new scala.collection.immutable..colon.colon(tree2, .MODULE$));
         return var10000;
      }
   }

   public Trees.Tree mkRuntimeUniverseRef() {
      boolean var5;
      SymbolTable var10000;
      label25: {
         label24: {
            var10000 = this.global();
            Symbols.Symbol var10001 = this.global().definitions().ReflectRuntimeUniverse();
            Symbols.NoSymbol var1 = this.global().NoSymbol();
            if (var10001 == null) {
               if (var1 != null) {
                  break label24;
               }
            } else if (!var10001.equals(var1)) {
               break label24;
            }

            var5 = false;
            break label25;
         }

         var5 = true;
      }

      boolean assert_assertion = var5;
      if (var10000 == null) {
         throw null;
      } else {
         SymbolTable assert_this = var10000;
         if (!assert_assertion) {
            throw assert_this.throwAssertionError("Missing ReflectRuntimeUniverse");
         } else {
            assert_this = null;
            return ((Trees.Tree)this.mkAttributedRef(this.global().definitions().ReflectRuntimeUniverse())).setType(this.global().singleType(this.global().definitions().ReflectRuntimeUniverse().owner().thisPrefix(), this.global().definitions().ReflectRuntimeUniverse()));
         }
      }
   }

   public Trees.Apply mkSeqApply(final Trees.Tree arg) {
      Trees.Select factory = this.global().new Select((Trees.Tree)this.mkAttributedRef(this.global().definitions().SeqModule()), this.global().nme().apply());
      return this.global().new Apply(factory, new scala.collection.immutable..colon.colon(arg, .MODULE$));
   }

   public Trees.Select mkSuperInitCall() {
      return this.global().new Select(this.global().new Super(this.global().new This((Names.TypeName)this.global().tpnme().EMPTY()), (Names.TypeName)this.global().tpnme().EMPTY()), this.global().nme().CONSTRUCTOR());
   }

   public Trees.Template mkTemplate(final List parents, final Trees.ValDef self, final Trees.Modifiers constrMods, final List vparamss, final List body, final Position superPos) {
      if (this.global() == null) {
         throw null;
      } else if (vparamss == null) {
         throw null;
      } else {
         Object var10000;
         if (vparamss == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon var95 = new scala.collection.immutable..colon.colon;
            List var42 = (List)vparamss.head();
            if (var42 == null) {
               throw null;
            }

            Object var10002;
            if (var42 == .MODULE$) {
               var10002 = .MODULE$;
            } else {
               Trees.ValDef var54 = (Trees.ValDef)var42.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mkTemplate$1(this, var54), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)var42.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var54 = (Trees.ValDef)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mkTemplate$1(this, var54), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10002 = map_h;
            }

            Object var84 = null;
            Object var85 = null;
            Object var86 = null;
            Object var87 = null;
            var95.<init>(var10002, .MODULE$);
            scala.collection.immutable..colon.colon mmap_map_h = var95;
            scala.collection.immutable..colon.colon mmap_map_t = mmap_map_h;

            for(List mmap_map_rest = (List)vparamss.tail(); mmap_map_rest != .MODULE$; mmap_map_rest = (List)mmap_map_rest.tail()) {
               var95 = new scala.collection.immutable..colon.colon;
               var42 = (List)mmap_map_rest.head();
               if (var42 == null) {
                  throw null;
               }

               if (var42 == .MODULE$) {
                  var10002 = .MODULE$;
               } else {
                  Trees.ValDef var93 = (Trees.ValDef)var42.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mkTemplate$1(this, var93), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)var42.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     var93 = (Trees.ValDef)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mkTemplate$1(this, var93), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var10002 = map_h;
               }

               Object var88 = null;
               Object var89 = null;
               Object var90 = null;
               Object var91 = null;
               var95.<init>(var10002, .MODULE$);
               scala.collection.immutable..colon.colon mmap_map_nx = var95;
               mmap_map_t.next_$eq(mmap_map_nx);
               mmap_map_t = mmap_map_nx;
            }

            Statics.releaseFence();
            var10000 = mmap_map_h;
         }

         Object var78 = null;
         Object var79 = null;
         Object var80 = null;
         Object var81 = null;
         List vparamss1 = (List)var10000;
         if (body == null) {
            throw null;
         } else {
            ListBuffer span_b = new ListBuffer();

            List span_these;
            for(span_these = body; !span_these.isEmpty(); span_these = (List)span_these.tail()) {
               Trees.Tree var43 = (Trees.Tree)span_these.head();
               if (!$anonfun$mkTemplate$2(this, var43)) {
                  break;
               }

               Object span_$plus$eq_elem = span_these.head();
               span_b.addOne(span_$plus$eq_elem);
               span_$plus$eq_elem = null;
            }

            List var97 = span_b.toList();
            List var10001 = span_these;
            Object var56 = null;
            Object var57 = null;
            Object var59 = null;
            List var55 = var10001;
            List edefs = var97;
            Function1 partition_p = (tree) -> BoxesRunTime.boxToBoolean($anonfun$mkTemplate$3(this, tree));
            if (edefs == null) {
               throw null;
            } else {
               Tuple2 var98;
               if (edefs.isEmpty()) {
                  var98 = scala.collection.immutable.List..MODULE$.scala$collection$immutable$List$$TupleOfNil();
               } else {
                  Builder partition_partition_l = edefs.newSpecificBuilder();
                  Builder partition_partition_r = edefs.newSpecificBuilder();
                  edefs.iterator().foreach(StrictOptimizedIterableOps::$anonfun$partition$1);
                  Object var107 = partition_partition_l.result();
                  Object var31 = partition_partition_r.result();
                  Object var30 = var107;
                  var98 = new Tuple2(var30, var31);
                  Object var66 = null;
                  Object var68 = null;
                  Tuple2 var25 = var98;
                  List var26 = (List)var30;
                  if (.MODULE$.equals(var26)) {
                     var98 = new Tuple2(.MODULE$, edefs);
                  } else {
                     List var27 = (List)var31;
                     var98 = .MODULE$.equals(var27) ? new Tuple2(edefs, .MODULE$) : var25;
                  }
               }

               Object var62 = null;
               Object var63 = null;
               Object var64 = null;
               Object var65 = null;
               Object var67 = null;
               Object var69 = null;
               Object var70 = null;
               Object var71 = null;
               Tuple2 var9 = var98;
               if (var9 == null) {
                  throw new MatchError((Object)null);
               } else {
                  List evdefs = (List)var9._1();
                  List etdefs = (List)var9._2();
                  List gvdefs = evdefs.collect(new Serializable() {
                     private static final long serialVersionUID = 0L;
                     // $FF: synthetic field
                     private final TreeGen $outer;

                     public final Object applyOrElse(final Trees.Tree x1, final Function1 default) {
                        if (x1 instanceof Trees.ValDef) {
                           Trees.ValDef var3 = (Trees.ValDef)x1;
                           Trees.Tree tpt = var3.tpt();
                           Trees.TypeTree x$2 = (Trees.TypeTree)this.$outer.global().atPos(var3.pos().focus(), (Trees.Tree)(this.$outer.global().new TypeTree()).setOriginal(tpt).setPos(tpt.pos().focus()));
                           Trees.EmptyTree$ x$3 = this.$outer.global().EmptyTree();
                           Trees.Modifiers x$4 = this.$outer.global().copyValDef$default$2(var3);
                           Names.Name x$5 = this.$outer.global().copyValDef$default$3(var3);
                           return this.$outer.global().copyValDef(var3, x$4, x$5, x$2, x$3);
                        } else {
                           return default.apply(x1);
                        }
                     }

                     public final boolean isDefinedAt(final Trees.Tree x1) {
                        return x1 instanceof Trees.ValDef;
                     }

                     public {
                        if (TreeGen.this == null) {
                           throw null;
                        } else {
                           this.$outer = TreeGen.this;
                        }
                     }
                  });
                  List lvdefs = evdefs.collect(new Serializable() {
                     private static final long serialVersionUID = 0L;
                     // $FF: synthetic field
                     private final TreeGen $outer;

                     public final Object applyOrElse(final Trees.Tree x2, final Function1 default) {
                        if (x2 instanceof Trees.ValDef) {
                           Trees.ValDef var3 = (Trees.ValDef)x2;
                           Trees.Modifiers x$2 = var3.mods().$bar(137438953472L);
                           Names.Name x$3 = this.$outer.global().copyValDef$default$3(var3);
                           Trees.Tree x$4 = this.$outer.global().copyValDef$default$4(var3);
                           Trees.Tree x$5 = this.$outer.global().copyValDef$default$5(var3);
                           return this.$outer.global().copyValDef(var3, x$2, x$3, x$4, x$5);
                        } else {
                           return default.apply(x2);
                        }
                     }

                     public final boolean isDefinedAt(final Trees.Tree x2) {
                        return x2 instanceof Trees.ValDef;
                     }

                     public {
                        if (TreeGen.this == null) {
                           throw null;
                        } else {
                           this.$outer = TreeGen.this;
                        }
                     }
                  });
                  Object var101;
                  if (constrMods.isTrait()) {
                     List forall_these = body;

                     while(true) {
                        if (forall_these.isEmpty()) {
                           var100 = true;
                           break;
                        }

                        Trees.Tree var44 = (Trees.Tree)forall_these.head();
                        if (!$anonfun$mkTemplate$4(this, var44)) {
                           var100 = false;
                           break;
                        }

                        forall_these = (List)forall_these.tail();
                     }

                     Object var72 = null;
                     var101 = var100 ? scala.None..MODULE$ : new Some(this.global().atPos((Position)this.global().wrappingPos(superPos, lvdefs), (Trees.Tree)(this.global().new DefDef((Trees.Modifiers)this.global().NoMods(), this.global().nme().MIXIN_CONSTRUCTOR(), .MODULE$, package$.MODULE$.ListOfNil(), this.global().new TypeTree(), this.global().new Block(lvdefs, this.mkLiteralUnit())))));
                  } else {
                     if (vparamss1.isEmpty() || !((List)vparamss1.head()).isEmpty() && ((Trees.ValDef)((IterableOps)vparamss1.head()).head()).mods().isImplicit()) {
                        Nil var15 = .MODULE$;
                        vparamss1 = new scala.collection.immutable..colon.colon(var15, vparamss1);
                     }

                     Trees.pendingSuperCall$ superCall = this.global().pendingSuperCall();
                     SymbolTable var108 = this.global();
                     SymbolTable var10003 = this.global();
                     Function1 flatten_toIterableOnce = scala..less.colon.less..MODULE$.refl();
                     Object var10005 = StrictOptimizedIterableOps.flatten$(vparamss1, flatten_toIterableOnce);
                     flatten_toIterableOnce = null;
                     var101 = new Some(var108.atPos((Position)var10003.wrappingPos(superPos, ((List)var10005).$colon$colon$colon(lvdefs)).makeTransparent(), (Trees.Tree)(this.global().new DefDef(constrMods, this.global().nme().CONSTRUCTOR(), .MODULE$, vparamss1, this.global().new TypeTree(), this.global().new Block((new scala.collection.immutable..colon.colon(superCall, .MODULE$)).$colon$colon$colon(lvdefs), this.mkLiteralUnit())))));
                  }

                  Option constr = (Option)var101;
                  constr.foreach((x$8) -> {
                     $anonfun$mkTemplate$5(this, parents, gvdefs, x$8);
                     return BoxedUnit.UNIT;
                  });
                  Function1 flatten_toIterableOnce = scala..less.colon.less..MODULE$.refl();
                  var101 = StrictOptimizedIterableOps.flatten$(vparamss, flatten_toIterableOnce);
                  flatten_toIterableOnce = null;
                  List var103 = (List)var101;
                  if (var103 == null) {
                     throw null;
                  } else {
                     List map_this = var103;
                     Object var104;
                     if (map_this == .MODULE$) {
                        var104 = .MODULE$;
                     } else {
                        Trees.ValDef var45 = (Trees.ValDef)map_this.head();
                        scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mkTemplate$6(this, var45), .MODULE$);
                        scala.collection.immutable..colon.colon map_t = map_h;

                        for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                           var45 = (Trees.ValDef)map_rest.head();
                           scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mkTemplate$6(this, var45), .MODULE$);
                           map_t.next_$eq(map_nx);
                           map_t = map_nx;
                        }

                        Statics.releaseFence();
                        var104 = map_h;
                     }

                     Object var73 = null;
                     Object var74 = null;
                     Object var75 = null;
                     Object var76 = null;
                     Object var77 = null;
                     List fieldDefs = var104;
                     var104 = new Trees.Template;
                     SymbolTable var109 = this.global();
                     if (etdefs == null) {
                        throw null;
                     } else {
                        List var18 = etdefs.prependedAll(constr);
                        var104.<init>(parents, self, var55.$colon$colon$colon(var18).$colon$colon$colon(fieldDefs).$colon$colon$colon(gvdefs));
                        return var104;
                     }
                  }
               }
            }
         }
      }
   }

   public Position mkTemplate$default$6() {
      return this.global().NoPosition();
   }

   public List mkParents(final Trees.Modifiers ownerMods, final List parents, final Position parentPos) {
      if (ownerMods.isCase()) {
         return (new scala.collection.immutable..colon.colon(this.scalaDot(this.global().tpnme().Product()), new scala.collection.immutable..colon.colon(this.scalaDot(this.global().tpnme().Serializable()), .MODULE$))).$colon$colon$colon(parents);
      } else if (parents.isEmpty()) {
         Trees.Select var4 = (Trees.Select)this.global().atPos((Position)parentPos, (Trees.Tree)this.scalaAnyRefConstr());
         List $colon$colon_this = .MODULE$;
         return new scala.collection.immutable..colon.colon(var4, $colon$colon_this);
      } else {
         return parents;
      }
   }

   public Position mkParents$default$3() {
      return this.global().NoPosition();
   }

   public Trees.ClassDef mkClassDef(final Trees.Modifiers mods, final Names.TypeName name, final List tparams, final Trees.Template templ) {
      boolean var10;
      label35: {
         if (mods.isTrait()) {
            List var10000 = templ.body();
            if (var10000 == null) {
               throw null;
            }

            List forall_these = var10000;

            while(true) {
               if (forall_these.isEmpty()) {
                  var9 = true;
                  break;
               }

               Trees.Tree var7 = (Trees.Tree)forall_these.head();
               if (!$anonfun$mkClassDef$1(this, var7)) {
                  var9 = false;
                  break;
               }

               forall_these = (List)forall_these.tail();
            }

            Object var8 = null;
            if (var9) {
               var10 = true;
               break label35;
            }
         }

         var10 = false;
      }

      Trees.Modifiers mods1 = var10 ? mods.$bar(128L) : mods;
      return this.global().new ClassDef(mods1, name, tparams, templ);
   }

   public Trees.Tree mkNew(final List parents, final Trees.ValDef self, final List stats, final Position npos, final Position cpos) {
      if (parents.isEmpty()) {
         return this.mkNew(new scala.collection.immutable..colon.colon(this.scalaAnyRefConstr(), .MODULE$), self, stats, npos, cpos);
      } else if (((List)parents.tail()).isEmpty() && stats.isEmpty()) {
         TreeInfo var10000 = this.global().treeInfo();
         Trees.Tree dissectApplied_tree = (Trees.Tree)parents.head();
         if (var10000 == null) {
            throw null;
         } else {
            TreeInfo dissectApplied_this = var10000;
            TreeInfo.Applied var12 = dissectApplied_this.new Applied(dissectApplied_tree);
            dissectApplied_this = null;
            dissectApplied_tree = null;
            TreeInfo.Applied app = var12;
            return this.global().atPos(npos.union(cpos), this.global().New(app.callee(), app.argss()));
         }
      } else {
         Names.TypeName x = (Names.TypeName)this.global().tpnme().ANON_CLASS_NAME();
         return this.global().atPos((Position)npos.union(cpos), (Trees.Tree)(this.global().new Block(new scala.collection.immutable..colon.colon((Trees.ClassDef)this.global().atPos((Position)cpos, (Trees.Tree)(this.global().new ClassDef((Trees.Modifiers)this.global().Modifiers(32L), x, .MODULE$, this.mkTemplate(parents, self, (Trees.Modifiers)this.global().NoMods(), package$.MODULE$.ListOfNil(), stats, cpos.focus())))), .MODULE$), this.global().atPos(npos, this.global().New((Trees.Tree)((Trees.Tree)(this.global().new Ident(x)).setPos(npos.focus())), (List).MODULE$)))));
      }
   }

   public Trees.Tree mkFunctionTypeTree(final List argtpes, final Trees.Tree restpe) {
      return this.global().new AppliedTypeTree(this.rootScalaDot(this.global().newTypeName((new StringBuilder(8)).append("Function").append(argtpes.length()).toString())), (new scala.collection.immutable..colon.colon(restpe, .MODULE$)).$colon$colon$colon(argtpes));
   }

   public Trees.Literal mkSyntheticUnit() {
      return (Trees.Literal)this.mkLiteralUnit().updateAttachment(this.global().SyntheticUnitAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.SyntheticUnitAttachment$.class));
   }

   public Trees.Tree mkBlock(final List stats, final boolean doFlatten) {
      if (stats.isEmpty()) {
         return this.mkSyntheticUnit();
      } else if (!((Trees.Tree)stats.last()).isTerm()) {
         return this.global().new Block(stats, this.mkSyntheticUnit());
      } else {
         return (Trees.Tree)(stats.length() == 1 && doFlatten ? (Trees.Tree)stats.head() : this.global().new Block((List)stats.init(), (Trees.Tree)stats.last()));
      }
   }

   public boolean mkBlock$default$2() {
      return true;
   }

   public Trees.Tree mkTreeOrBlock(final List stats) {
      if (.MODULE$.equals(stats)) {
         return this.global().EmptyTree();
      } else {
         if (stats instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var2 = (scala.collection.immutable..colon.colon)stats;
            Trees.Tree head = (Trees.Tree)var2.head();
            List var4 = var2.next$access$1();
            if (.MODULE$.equals(var4)) {
               return head;
            }
         }

         return this.mkBlock(stats, this.mkBlock$default$2());
      }
   }

   public Trees.Tree mkAssign(final Trees.Tree lhs, final Trees.Tree rhs) {
      if (lhs instanceof Trees.Apply) {
         Trees.Apply var3 = (Trees.Apply)lhs;
         Trees.Tree fn = var3.fun();
         List args = var3.args();
         Trees.Apply var10000 = new Trees.Apply;
         SymbolTable var10002 = this.global();
         Trees.Tree var10003 = this.global().atPos((Position)fn.pos(), (Trees.Tree)(this.global().new Select(fn, this.global().nme().update())));
         if (args == null) {
            throw null;
         } else {
            var10000.<init>(var10003, (List)StrictOptimizedSeqOps.appended$(args, rhs));
            return var10000;
         }
      } else {
         return this.global().new Assign(lhs, rhs);
      }
   }

   public Trees.PackageDef mkPackageObject(final Trees.ModuleDef defn, final Position pidPos, final Position pkgPos) {
      Names.TermName x$2 = this.global().nme().PACKAGEkw();
      Trees.Modifiers x$3 = this.global().copyModuleDef$default$2(defn);
      Trees.Template x$4 = this.global().copyModuleDef$default$4(defn);
      Trees.ModuleDef module = this.global().copyModuleDef(defn, x$3, x$2, x$4);
      Trees.Ident pid = (Trees.Ident)this.global().atPos((Position)pidPos, (Trees.Tree)(this.global().new Ident(defn.name())));
      SymbolTable var10000 = this.global();
      SymbolTable var10004 = this.global();
      List $colon$colon_this = .MODULE$;
      scala.collection.immutable..colon.colon var10006 = new scala.collection.immutable..colon.colon(module, $colon$colon_this);
      $colon$colon_this = null;
      return (Trees.PackageDef)var10000.atPos((Position)pkgPos, (Trees.Tree)(var10004.new PackageDef(pid, var10006)));
   }

   public Position mkPackageObject$default$2() {
      return this.global().NoPosition();
   }

   public Position mkPackageObject$default$3() {
      return this.global().NoPosition();
   }

   public Trees.Tree mkFor(final List enums, final Trees.Tree sugarBody, final FreshNameCreator fresh) {
      Names.TermName var10000;
      Names.TermName var10001;
      Trees.Tree var10002;
      label174: {
         if (sugarBody != null) {
            Option var4 = this.Yield().unapply(sugarBody);
            if (!var4.isEmpty()) {
               Trees.Tree tree = (Trees.Tree)var4.get();
               var10000 = this.global().nme().map();
               var10001 = this.global().nme().flatMap();
               var10002 = tree;
               break label174;
            }
         }

         var10000 = this.global().nme().foreach();
         var10001 = this.global().nme().foreach();
         var10002 = sugarBody;
      }

      Trees.Tree var83 = var10002;
      Names.TermName var82 = var10001;
      Names.TermName mapName = var10000;
      boolean var7 = false;
      scala.collection.immutable..colon.colon var8 = null;
      if (enums instanceof scala.collection.immutable..colon.colon) {
         var7 = true;
         var8 = (scala.collection.immutable..colon.colon)enums;
         Trees.Tree t = (Trees.Tree)var8.head();
         List var10 = var8.next$access$1();
         if (t != null) {
            Option var11 = this.ValFrom().unapply(t);
            if (!var11.isEmpty()) {
               Trees.Tree pat = (Trees.Tree)((Tuple2)var11.get())._1();
               Trees.Tree rhs = (Trees.Tree)((Tuple2)var11.get())._2();
               if (.MODULE$.equals(var10)) {
                  return this.makeCombination$1(this.closurePos$1(t.pos(), var83), mapName, rhs, pat, var83, fresh);
               }
            }
         }
      }

      if (var7) {
         Trees.Tree t = (Trees.Tree)var8.head();
         List rest = var8.next$access$1();
         if (t != null) {
            Option var16 = this.ValFrom().unapply(t);
            if (!var16.isEmpty()) {
               Trees.Tree pat = (Trees.Tree)((Tuple2)var16.get())._1();
               Trees.Tree rhs = (Trees.Tree)((Tuple2)var16.get())._2();
               if (rest instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var19 = (scala.collection.immutable..colon.colon)rest;
                  Trees.Tree var20 = (Trees.Tree)var19.head();
                  if (var20 != null && !this.ValFrom().unapply(var20).isEmpty()) {
                     return this.makeCombination$1(this.closurePos$1(t.pos(), var83), var82, rhs, pat, this.mkFor(var19, sugarBody, fresh), fresh);
                  }
               }
            }
         }
      }

      if (var7) {
         Trees.Tree t = (Trees.Tree)var8.head();
         List var22 = var8.next$access$1();
         if (t != null) {
            Option var23 = this.ValFrom().unapply(t);
            if (!var23.isEmpty()) {
               Trees.Tree pat = (Trees.Tree)((Tuple2)var23.get())._1();
               Trees.Tree rhs = (Trees.Tree)((Tuple2)var23.get())._2();
               if (var22 instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var26 = (scala.collection.immutable..colon.colon)var22;
                  Trees.Tree var27 = (Trees.Tree)var26.head();
                  List rest = var26.next$access$1();
                  if (var27 != null) {
                     Option var29 = this.Filter().unapply(var27);
                     if (!var29.isEmpty()) {
                        Trees.Tree test = (Trees.Tree)var29.get();
                        Trees.Tree var31 = (Trees.Tree)this.ValFrom().apply(pat, this.makeCombination$1(rhs.pos().$bar(test.pos()), this.global().nme().withFilter(), rhs, pat.duplicate(), test, fresh)).setPos(t.pos());
                        if (rest == null) {
                           throw null;
                        }

                        return this.mkFor(new scala.collection.immutable..colon.colon(var31, rest), sugarBody, fresh);
                     }
                  }
               }
            }
         }
      }

      if (var7) {
         Trees.Tree t = (Trees.Tree)var8.head();
         List rest = var8.next$access$1();
         if (t != null) {
            Option var34 = this.ValFrom().unapply(t);
            if (!var34.isEmpty()) {
               Trees.Tree pat = (Trees.Tree)((Tuple2)var34.get())._1();
               Trees.Tree rhs = (Trees.Tree)((Tuple2)var34.get())._2();
               List var111 = rest.take(this.global().definitions().MaxTupleArity() - 1);
               if (var111 == null) {
                  throw null;
               }

               List takeWhile_this = var111;
               ListBuffer takeWhile_b = new ListBuffer();

               for(List takeWhile_these = takeWhile_this; !takeWhile_these.isEmpty(); takeWhile_these = (List)takeWhile_these.tail()) {
                  Trees.Tree var78 = (Trees.Tree)takeWhile_these.head();
                  if (!$anonfun$mkFor$2(this, var78)) {
                     break;
                  }

                  Object takeWhile_$plus$eq_elem = takeWhile_these.head();
                  takeWhile_b.addOne(takeWhile_$plus$eq_elem);
                  takeWhile_$plus$eq_elem = null;
               }

               var111 = takeWhile_b.toList();
               Object var84 = null;
               Object var85 = null;
               Object var86 = null;
               Object var88 = null;
               List valeqs = var111;
               SymbolTable var113 = this.global();
               boolean assert_assertion = !valeqs.isEmpty();
               if (var113 == null) {
                  throw null;
               }

               SymbolTable assert_this = var113;
               if (!assert_assertion) {
                  throw assert_this.throwAssertionError("Missing ValEq");
               }

               assert_this = null;
               int drop_n = valeqs.length();
               List rest1 = (List)StrictOptimizedLinearSeqOps.drop$(rest, drop_n);
               Object var114;
               if (valeqs == .MODULE$) {
                  var114 = .MODULE$;
               } else {
                  Trees.Tree var79 = (Trees.Tree)valeqs.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mkFor$4(this, var79), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)valeqs.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     var79 = (Trees.Tree)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mkFor$4(this, var79), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var114 = map_h;
               }

               Object var90 = null;
               Object var91 = null;
               Object var92 = null;
               Object var93 = null;
               Tuple2 var39 = ((List)var114).unzip(scala.Predef..MODULE$.$conforms());
               if (var39 != null) {
                  List pats = (List)var39._1();
                  List rhss = (List)var39._2();
                  Trees.Bind defpat1 = this.makeBind$1(pat, fresh);
                  if (pats == null) {
                     throw null;
                  }

                  if (pats == .MODULE$) {
                     var114 = .MODULE$;
                  } else {
                     Trees.Tree var80 = (Trees.Tree)pats.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mkFor$5(this, fresh, var80), .MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)pats.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                        var80 = (Trees.Tree)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mkFor$5(this, fresh, var80), .MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var114 = map_h;
                  }

                  Object var94 = null;
                  Object var95 = null;
                  Object var96 = null;
                  Object var97 = null;
                  List defpats = (List)var114;
                  List pdefs = (List)defpats.lazyZip(rhss).flatMap((p, r) -> this.mkPatDef((Trees.Modifiers)this.global().Modifiers(0L), p, r, r.pos(), true, fresh), BuildFromLowPriority2.buildFromIterableOps$(scala.collection.BuildFrom..MODULE$));
                  List map_this = new scala.collection.immutable..colon.colon(defpat1, defpats);
                  if (map_this == .MODULE$) {
                     var114 = .MODULE$;
                  } else {
                     Trees.Tree var81 = (Trees.Tree)map_this.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mkFor$7(this, var81), .MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = ((scala.collection.immutable..colon.colon)map_this).tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                        var81 = (Trees.Tree)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mkFor$7(this, var81), .MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var114 = map_h;
                  }

                  map_this = null;
                  Object var99 = null;
                  Object var100 = null;
                  Object var101 = null;
                  Object var102 = null;
                  List ids = (List)var114;
                  Trees.Tree tupled = this.global().atPos(this.global().wrappingPos(ids), (Trees.Tree)this.mkTuple(ids, this.mkTuple$default$2()).updateAttachment(this.global().ForAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class)));
                  Trees.Tree rhs1 = this.mkFor(new scala.collection.immutable..colon.colon((Trees.Tree)this.ValFrom().apply(defpat1, rhs).setPos(t.pos()), .MODULE$), this.Yield().apply((Trees.Tree)(this.global().new Block(pdefs, tupled)).setPos(this.global().wrappingPos(pdefs))), fresh);
                  List map_this = new scala.collection.immutable..colon.colon(pat, pats);
                  if (map_this == .MODULE$) {
                     var114 = .MODULE$;
                  } else {
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Trees.Tree)map_this.head()).duplicate(), .MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = ((scala.collection.immutable..colon.colon)map_this).tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Trees.Tree)map_rest.head()).duplicate(), .MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var114 = map_h;
                  }

                  Trees.Tree untupled;
                  label114: {
                     label113: {
                        map_this = null;
                        Object var104 = null;
                        Object var105 = null;
                        Object var106 = null;
                        Object var107 = null;
                        List allpats = (List)var114;
                        untupled = this.global().atPos(this.global().wrappingPos(allpats), this.mkTuple(allpats, this.mkTuple$default$2()));
                        Position var118 = t.pos();
                        NoPosition$ var51 = this.global().NoPosition();
                        if (var118 == null) {
                           if (var51 == null) {
                              break label113;
                           }
                        } else if (var118.equals(var51)) {
                           break label113;
                        }

                        var114 = this.global().rangePos(t.pos().source(), t.pos().start(), t.pos().point(), rhs1.pos().end());
                        break label114;
                     }

                     var114 = this.global().NoPosition();
                  }

                  Position pos1 = (Position)var114;
                  Trees.Tree vfrom1 = (Trees.Tree)this.ValFrom().apply(untupled, rhs1).setPos(pos1);
                  if (rest1 == null) {
                     throw null;
                  }

                  return this.mkFor(new scala.collection.immutable..colon.colon(vfrom1, rest1), sugarBody, fresh);
               }

               throw new MatchError((Object)null);
            }
         }
      }

      return this.global().EmptyTree();
   }

   private Trees.ValDef propagateNoWarnAttachment(final Trees.Tree from, final Trees.ValDef to) {
      return this.isPatVarWarnable() && from.hasAttachment(scala.reflect.ClassTag..MODULE$.apply(StdAttachments.NoWarnAttachment$.class)) ? (Trees.ValDef)to.updateAttachment(this.global().NoWarnAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.NoWarnAttachment$.class)) : to;
   }

   private Trees.ValDef propagatePatVarDefAttachments(final Trees.Tree from, final Trees.ValDef to) {
      return (Trees.ValDef)this.propagateNoWarnAttachment(from, to).updateAttachment(this.global().PatVarDefAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.PatVarDefAttachment$.class));
   }

   public List mkPatDef(final Trees.Tree pat, final Trees.Tree rhs, final FreshNameCreator fresh) {
      return this.mkPatDef((Trees.Modifiers)this.global().Modifiers(0L), pat, rhs, rhs.pos(), fresh);
   }

   public List mkPatDef(final Trees.Modifiers mods, final Trees.Tree pat, final Trees.Tree rhs, final FreshNameCreator fresh) {
      return this.mkPatDef(mods, pat, rhs, rhs.pos(), fresh);
   }

   public List mkPatDef(final Trees.Modifiers mods, final Trees.Tree pat, final Trees.Tree rhs, final Position rhsPos, final FreshNameCreator fresh) {
      return this.mkPatDef(mods, pat, rhs, rhsPos, false, fresh);
   }

   private List mkPatDef(final Trees.Modifiers mods, final Trees.Tree pat, final Trees.Tree rhs, final Position rhsPos, final boolean forFor, final FreshNameCreator fresh) {
      Option var7 = this.matchVarPattern(pat);
      if (var7 instanceof Some) {
         Tuple2 var8 = (Tuple2)((Some)var7).value();
         if (var8 != null) {
            Names.Name name = (Names.Name)var8._1();
            Trees.Tree tpt = (Trees.Tree)var8._2();
            SymbolTable var82 = this.global();
            Position var87 = pat.pos().union(rhsPos);
            ChainingOps var92 = scala.util.ChainingOps..MODULE$;
            package.chaining var93 = scala.util.package.chaining..MODULE$;
            Object scalaUtilChainingOps_a = this.global().new ValDef(mods, name.toTermName(), tpt, rhs);
            Object var94 = scalaUtilChainingOps_a;
            scalaUtilChainingOps_a = null;
            Object tap$extension_$this = var94;
            $anonfun$mkPatDef$1(this, forFor, pat, (Trees.ValDef)tap$extension_$this);
            var94 = tap$extension_$this;
            tap$extension_$this = null;
            Trees.ValDef var11 = (Trees.ValDef)var82.atPos((Position)var87, (Trees.Tree)var94);
            List $colon$colon_this = .MODULE$;
            return new scala.collection.immutable..colon.colon(var11, $colon$colon_this);
         }
      }

      if (scala.None..MODULE$.equals(var7)) {
         Trees.Tree var10000;
         Trees.Tree var10001;
         label80: {
            Trees.Tree rhsUnchecked = this.isVarDefWarnable() ? rhs : this.mkUnchecked(rhs);
            Trees.Tree var13 = this.patvarTransformer().transform(pat);
            if (var13 instanceof Trees.Typed) {
               Trees.Typed var14 = (Trees.Typed)var13;
               Trees.Tree expr = var14.expr();
               Trees.Tree tpt = var14.tpt();
               if (!(expr instanceof Trees.Ident)) {
                  Trees.Tree rhsTypedUnchecked = tpt.isEmpty() ? rhsUnchecked : (Trees.Tree)(this.global().new Typed(rhsUnchecked, tpt)).setPos(rhsPos.union(tpt.pos()));
                  var10000 = expr;
                  var10001 = rhsTypedUnchecked;
                  break label80;
               }
            }

            var10000 = var13;
            var10001 = rhsUnchecked;
         }

         Trees.Tree var56 = var10001;
         Trees.Tree pat1 = var10000;
         List vars = this.getVariables(pat1);
         SymbolTable var72 = this.global();
         Position var83 = pat1.pos().union(rhsPos).makeTransparent();
         Trees.Match var10002 = new Trees.Match;
         SymbolTable var10004 = this.global();
         scala.collection.immutable..colon.colon var10006 = new scala.collection.immutable..colon.colon;
         SymbolTable var10008 = this.global();
         Position var10009 = pat1.pos();
         Trees.CaseDef var10010 = new Trees.CaseDef;
         SymbolTable var10012 = this.global();
         Trees.EmptyTree$ var10014 = this.global().EmptyTree();
         if (vars == null) {
            throw null;
         } else {
            Object var10016;
            if (vars == .MODULE$) {
               var10016 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon((Names.Name)((Tuple4)vars.head())._1(), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)vars.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon((Names.Name)((Tuple4)map_rest.head())._1(), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10016 = map_h;
            }

            Object var61 = null;
            Object var62 = null;
            Object var63 = null;
            Object var64 = null;
            List map_this = (List)var10016;
            if (map_this == .MODULE$) {
               var10016 = .MODULE$;
            } else {
               Names.Name var55 = (Names.Name)map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mkPatDef$3(this, var55), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var55 = (Names.Name)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mkPatDef$3(this, var55), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10016 = map_h;
            }

            map_this = null;
            Object var66 = null;
            Object var67 = null;
            Object var68 = null;
            Object var69 = null;
            var10010.<init>(pat1, var10014, this.mkTuple((List)var10016, this.mkTuple$default$2()));
            var10006.<init>((Trees.CaseDef)var10008.atPos((Position)var10009, (Trees.Tree)var10010), .MODULE$);
            var10002.<init>(var56, var10006);
            Trees.Match matchExpr = (Trees.Match)var72.atPos((Position)var83, (Trees.Tree)var10002);
            List var73 = scala.package..MODULE$.List();
            if (var73 == null) {
               throw null;
            } else {
               List unapplySeq_this = var73;
               SeqOps var74 = SeqFactory.unapplySeq$(unapplySeq_this, vars);
               Object var59 = null;
               SeqOps var21 = var74;
               SeqFactory.UnapplySeqWrapper var75 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               SeqFactory.UnapplySeqWrapper var84 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               new SeqFactory.UnapplySeqWrapper(var21);
               var75 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               var75 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
               int lengthCompare$extension_len = 1;
               if (var21.lengthCompare(lengthCompare$extension_len) == 0) {
                  var75 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var75 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int apply$extension_i = 0;
                  Tuple4 var22 = (Tuple4)var21.apply(apply$extension_i);
                  if (var22 != null) {
                     Names.Name vname = (Names.Name)var22._1();
                     Trees.Tree tpt = (Trees.Tree)var22._2();
                     Position pos = (Position)var22._3();
                     Trees.Tree original = (Trees.Tree)var22._4();
                     SymbolTable var81 = this.global();
                     Position var86 = pat.pos().union(pos).union(rhsPos);
                     ChainingOps var88 = scala.util.ChainingOps..MODULE$;
                     package.chaining var89 = scala.util.package.chaining..MODULE$;
                     Object scalaUtilChainingOps_a = this.global().new ValDef(mods, vname.toTermName(), tpt, matchExpr);
                     Object var90 = scalaUtilChainingOps_a;
                     scalaUtilChainingOps_a = null;
                     Object tap$extension_$this = var90;
                     $anonfun$mkPatDef$4(this, original, (Trees.ValDef)tap$extension_$this);
                     var90 = tap$extension_$this;
                     tap$extension_$this = null;
                     Trees.ValDef var27 = (Trees.ValDef)var81.atPos((Position)var86, (Trees.Tree)var90);
                     List $colon$colon_this = .MODULE$;
                     return new scala.collection.immutable..colon.colon(var27, $colon$colon_this);
                  }
               }

               Names.TermName tmp = this.global().freshTermName(this.global().freshTermName$default$1(), fresh);
               SymbolTable var80 = this.global();
               Position var85 = matchExpr.pos();
               Trees.ValDef v = this.global().new ValDef((Trees.Modifiers)this.global().Modifiers(70368746799108L | mods.flags() & 2147483648L), tmp, this.global().new TypeTree(), matchExpr);
               if (vars.isEmpty()) {
                  v.updateAttachment(this.global().PatVarDefAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.PatVarDefAttachment$.class));
                  if (mods.isImplicit()) {
                     Reporting.PerRunReportingBase qual$1 = this.global().currentRun().reporting();
                     Position x$1 = matchExpr.pos();
                     List x$6 = qual$1.deprecationWarning$default$6();
                     qual$1.deprecationWarning(x$1, "Implicit pattern definition binds no variables", "2.13", "", "", x$6);
                  }
               }

               Trees.ValDef firstDef = (Trees.ValDef)var80.atPos((Position)var85, (Trees.Tree)v);
               int create_e = 0;
               IntRef cnt = new IntRef(create_e);
               List restDefs = (List)vars.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$mkPatDef$5(check$ifrefutable$1))).map((x$19) -> {
                  if (x$19 != null) {
                     Names.Name vname = (Names.Name)x$19._1();
                     Trees.Tree tpt = (Trees.Tree)x$19._2();
                     Position pos = (Position)x$19._3();
                     Trees.Tree original = (Trees.Tree)x$19._4();
                     SymbolTable var10000 = this.global();
                     ++cnt.elem;
                     ChainingOps var10002 = scala.util.ChainingOps..MODULE$;
                     package.chaining var13 = scala.util.package.chaining..MODULE$;
                     Object scalaUtilChainingOps_a = this.global().new ValDef(mods, vname.toTermName(), tpt, this.global().new Select(this.global().new Ident(tmp), this.global().TermName().apply((new StringBuilder(1)).append("_").append(cnt.elem).toString())));
                     Object var14 = scalaUtilChainingOps_a;
                     scalaUtilChainingOps_a = null;
                     Object tap$extension_$this = var14;
                     $anonfun$mkPatDef$7(this, original, (Trees.ValDef)tap$extension_$this);
                     var14 = tap$extension_$this;
                     tap$extension_$this = null;
                     return (Trees.ValDef)var10000.atPos((Position)pos, (Trees.Tree)var14);
                  } else {
                     throw new MatchError((Object)null);
                  }
               });
               if (restDefs == null) {
                  throw null;
               } else {
                  return new scala.collection.immutable..colon.colon(firstDef, restDefs);
               }
            }
         }
      } else {
         throw new MatchError(var7);
      }
   }

   public Trees.Tree mkGenerator(final Position pos, final Trees.Tree pat, final boolean valeq, final Trees.Tree rhs, final FreshNameCreator fresh) {
      Trees.Tree pat1 = this.patvarTransformer().transform(pat);
      return valeq ? (Trees.Tree)this.ValEq().apply(pat1, rhs).setPos(pos) : (Trees.Tree)this.ValFrom().apply(pat1, this.mkCheckIfRefutable(pat1, rhs, fresh)).setPos(pos);
   }

   private Trees.Tree unwarnable(final Trees.Tree pat) {
      pat.foreach((x0$1) -> {
         $anonfun$unwarnable$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
      return pat;
   }

   public Trees.Tree mkCheckIfRefutable(final Trees.Tree pat, final Trees.Tree rhs, final FreshNameCreator fresh) {
      if (this.global().treeInfo().isVarPatternDeep(pat)) {
         return rhs;
      } else {
         List cases = new scala.collection.immutable..colon.colon(this.global().new CaseDef(this.unwarnable(pat.duplicate()), this.global().EmptyTree(), this.global().new Literal(this.global().new Constant(true))), new scala.collection.immutable..colon.colon(this.global().new CaseDef(this.global().new Ident(this.global().nme().WILDCARD()), this.global().EmptyTree(), this.global().new Literal(this.global().new Constant(false))), .MODULE$));
         Trees.Tree visitor = this.mkVisitor(cases, false, this.global().nme().CHECK_IF_REFUTABLE_STRING(), fresh);
         SymbolTable var10000 = this.global();
         Position var10001 = rhs.pos();
         SymbolTable var10004 = this.global();
         Trees.Select var10005 = this.global().new Select(rhs, this.global().nme().withFilter());
         List $colon$colon_this = .MODULE$;
         scala.collection.immutable..colon.colon var10006 = new scala.collection.immutable..colon.colon(visitor, $colon$colon_this);
         $colon$colon_this = null;
         return var10000.atPos((Position)var10001, (Trees.Tree)(var10004.new Apply(var10005, var10006)));
      }
   }

   private Option matchVarPattern(final Trees.Tree tree) {
      boolean var2 = false;
      Trees.Bind var3 = null;
      if (tree instanceof Trees.Ident) {
         Trees.Ident var4 = (Trees.Ident)tree;
         Names.Name name = var4.name();
         Names.TermName var10000 = name.toTermName();
         Names.Name var6 = this.global().nme().WILDCARD();
         if (var10000 == null) {
            if (var6 != null) {
               return new Some(new Tuple2(name, this.global().new TypeTree()));
            }
         } else if (!var10000.equals(var6)) {
            return new Some(new Tuple2(name, this.global().new TypeTree()));
         }

         if (var4.isBackquoted()) {
            return new Some(new Tuple2(name, this.global().new TypeTree()));
         }
      }

      if (tree instanceof Trees.Bind) {
         var2 = true;
         var3 = (Trees.Bind)tree;
         Names.Name name = var3.name();
         Trees.Tree var8 = var3.body();
         if (var8 instanceof Trees.Ident) {
            Names.TermName var22 = ((Trees.Ident)var8).name().toTermName();
            Names.Name var9 = this.global().nme().WILDCARD();
            if (var22 == null) {
               if (var9 == null) {
                  return new Some(new Tuple2(name, this.global().new TypeTree()));
               }
            } else if (var22.equals(var9)) {
               return new Some(new Tuple2(name, this.global().new TypeTree()));
            }
         }
      }

      if (var2) {
         Names.Name name = var3.name();
         Trees.Tree var11 = var3.body();
         if (var11 instanceof Trees.Typed) {
            Trees.Typed var12 = (Trees.Typed)var11;
            Trees.Tree var13 = var12.expr();
            Trees.Tree tpt = var12.tpt();
            if (var13 instanceof Trees.Ident) {
               Names.TermName var23 = ((Trees.Ident)var13).name().toTermName();
               Names.Name var15 = this.global().nme().WILDCARD();
               if (var23 == null) {
                  if (var15 == null) {
                     return new Some(new Tuple2(name, tpt));
                  }
               } else if (var23.equals(var15)) {
                  return new Some(new Tuple2(name, tpt));
               }
            }
         }
      }

      if (tree instanceof Trees.Typed) {
         Trees.Typed var16 = (Trees.Typed)tree;
         Trees.Tree id = var16.expr();
         Trees.Tree tpt = var16.tpt();
         if (id instanceof Trees.Ident) {
            Trees.Ident var19 = (Trees.Ident)id;
            Names.Name name = var19.name();
            Names.TermName var24 = name.toTermName();
            Names.Name var21 = this.global().nme().WILDCARD();
            if (var24 == null) {
               if (var21 != null) {
                  return new Some(new Tuple2(name, tpt));
               }
            } else if (!var24.equals(var21)) {
               return new Some(new Tuple2(name, tpt));
            }

            if (var19.isBackquoted()) {
               return new Some(new Tuple2(name, tpt));
            }
         }
      }

      return scala.None..MODULE$;
   }

   public Trees.Tree mkVisitor(final List cases, final boolean checkExhaustive, final String prefix, final FreshNameCreator fresh) {
      Names.TermName x = this.global().freshTermName(prefix, fresh);
      Trees.Ident id = this.global().new Ident(x);
      Trees.Tree sel = (Trees.Tree)(checkExhaustive ? id : this.mkUnchecked(id));
      return this.global().new Function(new scala.collection.immutable..colon.colon(this.mkSyntheticParam(x), .MODULE$), this.global().new Match(sel, cases));
   }

   public String mkVisitor$default$3() {
      return "x$";
   }

   private List getVariables(final Trees.Tree tree) {
      return (new GetVarTraverser()).apply(tree);
   }

   public boolean isPatVarWarnable() {
      return true;
   }

   public boolean isVarDefWarnable() {
      return false;
   }

   public Trees.Tree mkUnchecked(final Trees.Tree expr) {
      return this.global().atPos((Position)expr.pos(), (Trees.Tree)(this.global().new Annotated(this.global().New((Trees.Tree)this.scalaDot(this.global().tpnme().unchecked()), (List).MODULE$), expr)));
   }

   public Trees.ValDef mkSyntheticParam(final Names.TermName pname) {
      return this.global().new ValDef((Trees.Modifiers)this.global().Modifiers(2105344L), pname, this.global().new TypeTree(), this.global().EmptyTree());
   }

   public Trees.Tree mkCast(final Trees.Tree tree, final Types.Type pt) {
      return this.global().atPos(tree.pos(), this.mkAsInstanceOf(tree, pt, true, false));
   }

   private final void ValFrom$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ValFrom$module == null) {
            this.ValFrom$module = new ValFrom$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ValEq$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ValEq$module == null) {
            this.ValEq$module = new ValEq$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Filter$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Filter$module == null) {
            this.Filter$module = new Filter$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Yield$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Yield$module == null) {
            this.Yield$module = new Yield$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void patvarTransformer$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.patvarTransformer$module == null) {
            this.patvarTransformer$module = new patvarTransformer$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   // $FF: synthetic method
   public static final Trees.TypeTree $anonfun$mkMethodCall$1(final TreeGen $this, final Types.Type tp) {
      return $this.global().TypeTree(tp);
   }

   // $FF: synthetic method
   public static final Trees.TypeTree $anonfun$mkNullaryCall$1(final TreeGen $this, final Types.Type tp) {
      return $this.global().TypeTree(tp);
   }

   private static final String failMessage$1(final Types.Type tpe$1, final Symbols.Symbol termSym$1) {
      return (new StringBuilder(25)).append("mkAttributedQualifier(").append(tpe$1).append(", ").append(termSym$1).append(")").toString();
   }

   // $FF: synthetic method
   public static final String $anonfun$mkAttributedQualifier$1(final Types.Type tpe$1, final Symbols.Symbol termSym$1) {
      return failMessage$1(tpe$1, termSym$1);
   }

   // $FF: synthetic method
   public static final String $anonfun$mkAttributedQualifier$2(final Types.Type tpe$1, final Symbols.Symbol termSym$1) {
      return failMessage$1(tpe$1, termSym$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mkAttributedQualifier$3(final Types.Type x$1) {
      return x$1.isStable();
   }

   // $FF: synthetic method
   public static final String $anonfun$mkAttributedQualifier$4(final List parents$1, final Types.Type tpe$1, final Symbols.Symbol termSym$1) {
      return (new StringBuilder(11)).append(failMessage$1(tpe$1, termSym$1)).append(" parents = ").append(parents$1).toString();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mkAttributedQualifierIfPossible$1(final Types.Type x$2) {
      return x$2.isStable();
   }

   // $FF: synthetic method
   public static final Symbols.Symbol $anonfun$mkAttributedRef$1(final Symbols.Symbol sym$1) {
      return sym$1.owner();
   }

   // $FF: synthetic method
   public static final Trees.TypeTree $anonfun$mkAttributedTypeApply$1(final TreeGen $this, final Types.Type tp) {
      return $this.global().TypeTree(tp);
   }

   // $FF: synthetic method
   public static final String $anonfun$mkRuntimeUniverseRef$1() {
      return "Missing ReflectRuntimeUniverse";
   }

   // $FF: synthetic method
   public static final Trees.ValDef $anonfun$mkTemplate$1(final TreeGen $this, final Trees.ValDef vd) {
      SymbolTable var10000 = $this.global();
      Position var10001 = vd.pos().makeTransparent();
      Trees.Modifiers mods = (Trees.Modifiers)$this.global().Modifiers(vd.mods().flags() & 33620480L | 8192L | 536870912L);
      return (Trees.ValDef)var10000.atPos((Position)var10001, (Trees.Tree)($this.global().new ValDef(mods.withAnnotations(vd.mods().annotations()), vd.name(), vd.tpt().duplicate(), $this.global().duplicateAndKeepPositions(vd.rhs()))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mkTemplate$2(final TreeGen $this, final Trees.Tree tree) {
      return $this.global().treeInfo().isEarlyDef(tree);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mkTemplate$3(final TreeGen $this, final Trees.Tree tree) {
      return $this.global().treeInfo().isEarlyValDef(tree);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mkTemplate$4(final TreeGen $this, final Trees.Tree tree) {
      return $this.global().treeInfo().isInterfaceMember(tree);
   }

   // $FF: synthetic method
   public static final void $anonfun$mkTemplate$5(final TreeGen $this, final List parents$2, final List gvdefs$1, final Trees.DefDef x$8) {
      $this.global().ensureNonOverlapping(x$8, gvdefs$1.$colon$colon$colon(parents$2), false);
   }

   // $FF: synthetic method
   public static final Trees.ValDef $anonfun$mkTemplate$6(final TreeGen $this, final Trees.ValDef vd) {
      Trees.Modifiers x$2 = vd.mods().$amp$tilde(33554432L);
      Trees.EmptyTree$ x$3 = $this.global().EmptyTree();
      Names.Name x$4 = $this.global().copyValDef$default$3(vd);
      Trees.Tree x$5 = $this.global().copyValDef$default$4(vd);
      Trees.ValDef field = $this.global().copyValDef(vd, x$2, x$4, x$5, x$3);
      if (field.pos().isRange() && vd.rhs().pos().isRange()) {
         field.pos_$eq(field.pos().withEnd(vd.rhs().pos().start() - 1));
      }

      return field;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mkClassDef$1(final TreeGen $this, final Trees.Tree tree) {
      return $this.global().treeInfo().isInterfaceMember(tree);
   }

   // $FF: synthetic method
   public static final Trees.ValDef $anonfun$mkFor$1(final TreeGen $this, final Trees.Tree pat$1, final Trees.ValDef x$10) {
      return $this.propagatePatVarDefAttachments(pat$1, x$10);
   }

   private final Trees.Tree makeClosure$1(final Position pos, final Trees.Tree pat, final Trees.Tree body, final FreshNameCreator fresh$1) {
      Position var10000;
      label31: {
         label30: {
            Position wrapped = this.global().wrappingPos(new scala.collection.immutable..colon.colon(pat, new scala.collection.immutable..colon.colon(body, .MODULE$)));
            NoPosition$ var7 = this.global().NoPosition();
            if (pos == null) {
               if (var7 == null) {
                  break label30;
               }
            } else if (pos.equals(var7)) {
               break label30;
            }

            if (wrapped.start() <= pos.point() && pos.point() < wrapped.end()) {
               var10000 = wrapped.withPoint(pos.point());
               break label31;
            }
         }

         var10000 = pos;
      }

      Position splitpos = var10000.makeTransparent();
      Option var8 = this.matchVarPattern(pat);
      if (var8 instanceof Some) {
         Tuple2 var9 = (Tuple2)((Some)var8).value();
         if (var9 != null) {
            Names.Name name = (Names.Name)var9._1();
            Trees.Tree tpt = (Trees.Tree)var9._2();
            ChainingOps var16 = scala.util.ChainingOps..MODULE$;
            package.chaining var17 = scala.util.package.chaining..MODULE$;
            Object tap$extension_$this = this.global().atPos((Position)pat.pos(), (Trees.Tree)(this.global().new ValDef((Trees.Modifiers)this.global().Modifiers(8192L), name.toTermName(), tpt, this.global().EmptyTree())));
            Trees.ValDef var14 = (Trees.ValDef)tap$extension_$this;
            $anonfun$mkFor$1(this, pat, var14);
            Object var18 = tap$extension_$this;
            tap$extension_$this = null;
            Trees.ValDef p = (Trees.ValDef)var18;
            return (Trees.Tree)(this.global().new Function(new scala.collection.immutable..colon.colon(p, .MODULE$), body)).setPos(splitpos);
         }
      }

      if (scala.None..MODULE$.equals(var8)) {
         return this.global().atPos(splitpos, this.mkVisitor(new scala.collection.immutable..colon.colon(this.global().new CaseDef(pat, this.global().EmptyTree(), body), .MODULE$), false, this.mkVisitor$default$3(), fresh$1));
      } else {
         throw new MatchError(var8);
      }
   }

   private final Trees.Tree makeCombination$1(final Position pos, final Names.TermName meth, final Trees.Tree qual, final Trees.Tree pat, final Trees.Tree body, final FreshNameCreator fresh$1) {
      return (Trees.Tree)(this.global().new Apply((Trees.Tree)(this.global().new Select(qual, meth)).setPos(qual.pos()).updateAttachment(this.global().ForAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class)), new scala.collection.immutable..colon.colon(this.makeClosure$1(pos, pat, body, fresh$1), .MODULE$))).setPos(pos);
   }

   private final Trees.Bind makeBind$1(final Trees.Tree pat, final FreshNameCreator fresh$1) {
      return pat instanceof Trees.Bind ? (Trees.Bind)pat : (Trees.Bind)(this.global().new Bind(this.global().freshTermName(this.global().freshTermName$default$1(), fresh$1), pat)).setPos(pat.pos()).updateAttachment(this.global().NoWarnAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.NoWarnAttachment$.class));
   }

   private final Trees.Ident makeValue$1(final Trees.Tree pat) {
      if (pat instanceof Trees.Bind) {
         Names.Name name = ((Trees.Bind)pat).name();
         return (Trees.Ident)(this.global().new Ident(name)).setPos(pat.pos().focus());
      } else {
         throw new MatchError(pat);
      }
   }

   private final Position closurePos$1(final Position genpos, final Trees.Tree body$1) {
      NoPosition$ var3 = this.global().NoPosition();
      if (genpos == null) {
         if (var3 == null) {
            return this.global().NoPosition();
         }
      } else if (genpos.equals(var3)) {
         return this.global().NoPosition();
      }

      int var6;
      label22: {
         label21: {
            Position var5 = body$1.pos();
            NoPosition$ var10000 = this.global().NoPosition();
            if (var10000 == null) {
               if (var5 == null) {
                  break label21;
               }
            } else if (var10000.equals(var5)) {
               break label21;
            }

            var6 = var5.end();
            break label22;
         }

         var6 = genpos.point();
      }

      int end = var6;
      return this.global().rangePos(genpos.source(), genpos.start(), genpos.point(), end);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mkFor$2(final TreeGen $this, final Trees.Tree x$11) {
      Option var10000 = $this.ValEq().unapply(x$11);
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.isDefined();
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$mkFor$3() {
      return "Missing ValEq";
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$mkFor$4(final TreeGen $this, final Trees.Tree x$12) {
      return (Tuple2)$this.ValEq().unapply(x$12).get();
   }

   // $FF: synthetic method
   public static final Trees.Bind $anonfun$mkFor$5(final TreeGen $this, final FreshNameCreator fresh$1, final Trees.Tree pat) {
      return $this.makeBind$1(pat, fresh$1);
   }

   // $FF: synthetic method
   public static final Trees.Ident $anonfun$mkFor$7(final TreeGen $this, final Trees.Tree pat) {
      return $this.makeValue$1(pat);
   }

   // $FF: synthetic method
   public static final Trees.Tree $anonfun$mkFor$8(final Trees.Tree x$14) {
      return x$14.duplicate();
   }

   // $FF: synthetic method
   public static final Trees.ValDef $anonfun$mkPatDef$1(final TreeGen $this, final boolean forFor$1, final Trees.Tree pat$2, final Trees.ValDef vd) {
      return forFor$1 ? $this.propagatePatVarDefAttachments(pat$2, vd) : $this.propagateNoWarnAttachment(pat$2, vd);
   }

   // $FF: synthetic method
   public static final Names.Name $anonfun$mkPatDef$2(final Tuple4 x$16) {
      return (Names.Name)x$16._1();
   }

   // $FF: synthetic method
   public static final Trees.Ident $anonfun$mkPatDef$3(final TreeGen $this, final Names.Name name) {
      return $this.global().new Ident(name);
   }

   // $FF: synthetic method
   public static final Trees.ValDef $anonfun$mkPatDef$4(final TreeGen $this, final Trees.Tree original$1, final Trees.ValDef x$17) {
      return $this.propagatePatVarDefAttachments(original$1, x$17);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mkPatDef$5(final Tuple4 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final Trees.ValDef $anonfun$mkPatDef$7(final TreeGen $this, final Trees.Tree original$2, final Trees.ValDef x$18) {
      return $this.propagatePatVarDefAttachments(original$2, x$18);
   }

   // $FF: synthetic method
   public static final void $anonfun$unwarnable$1(final TreeGen $this, final Trees.Tree x0$1) {
      if (x0$1 instanceof Trees.Bind) {
         ((Trees.Bind)x0$1).updateAttachment($this.global().NoWarnAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.NoWarnAttachment$.class));
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$mkAttributedQualifier$3$adapted(final Types.Type x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$mkAttributedQualifier$3(x$1));
   }

   // $FF: synthetic method
   public static final Object $anonfun$mkAttributedQualifierIfPossible$1$adapted(final Types.Type x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$mkAttributedQualifierIfPossible$1(x$2));
   }

   // $FF: synthetic method
   public static final Object $anonfun$mkTemplate$2$adapted(final TreeGen $this, final Trees.Tree tree) {
      return BoxesRunTime.boxToBoolean($anonfun$mkTemplate$2($this, tree));
   }

   // $FF: synthetic method
   public static final Object $anonfun$mkTemplate$4$adapted(final TreeGen $this, final Trees.Tree tree) {
      return BoxesRunTime.boxToBoolean($anonfun$mkTemplate$4($this, tree));
   }

   // $FF: synthetic method
   public static final Object $anonfun$mkClassDef$1$adapted(final TreeGen $this, final Trees.Tree tree) {
      return BoxesRunTime.boxToBoolean($anonfun$mkClassDef$1($this, tree));
   }

   // $FF: synthetic method
   public static final Object $anonfun$mkFor$2$adapted(final TreeGen $this, final Trees.Tree x$11) {
      return BoxesRunTime.boxToBoolean($anonfun$mkFor$2($this, x$11));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class ValFrom$ {
      // $FF: synthetic field
      private final TreeGen $outer;

      public Trees.Tree apply(final Trees.Tree pat, final Trees.Tree rhs) {
         return this.$outer.global().new Apply((Trees.Tree)(this.$outer.global().new Ident(this.$outer.global().nme().LARROWkw())).updateAttachment(this.$outer.global().ForAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class)), new scala.collection.immutable..colon.colon(pat, new scala.collection.immutable..colon.colon(rhs, .MODULE$)));
      }

      public Option unapply(final Trees.Tree tree) {
         if (tree instanceof Trees.Apply) {
            Trees.Apply var2 = (Trees.Apply)tree;
            Trees.Tree id = var2.fun();
            List var4 = var2.args();
            if (id instanceof Trees.Ident) {
               Trees.Ident var5 = (Trees.Ident)id;
               Names.Name var6 = var5.name();
               Names.TermName var10000 = this.$outer.global().nme().LARROWkw();
               if (var10000 == null) {
                  if (var6 != null) {
                     return scala.None..MODULE$;
                  }
               } else if (!var10000.equals(var6)) {
                  return scala.None..MODULE$;
               }

               if (var4 != null) {
                  List var15 = scala.package..MODULE$.List();
                  if (var15 == null) {
                     throw null;
                  }

                  List unapplySeq_this = var15;
                  SeqOps var16 = SeqFactory.unapplySeq$(unapplySeq_this, var4);
                  Object var14 = null;
                  SeqOps var7 = var16;
                  SeqFactory.UnapplySeqWrapper var17 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var7);
                  var17 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var17 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int lengthCompare$extension_len = 2;
                  if (var7.lengthCompare(lengthCompare$extension_len) == 0) {
                     var17 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var17 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int apply$extension_i = 0;
                     Trees.Tree pat = (Trees.Tree)var7.apply(apply$extension_i);
                     var17 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var17 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int apply$extension_i = 1;
                     Trees.Tree rhs = (Trees.Tree)var7.apply(apply$extension_i);
                     if (var5.hasAttachment(scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class))) {
                        return new Some(new Tuple2(pat, rhs));
                     }
                  }
               }
            }
         }

         return scala.None..MODULE$;
      }

      public ValFrom$() {
         if (TreeGen.this == null) {
            throw null;
         } else {
            this.$outer = TreeGen.this;
            super();
         }
      }
   }

   public class ValEq$ {
      // $FF: synthetic field
      private final TreeGen $outer;

      public Trees.Tree apply(final Trees.Tree pat, final Trees.Tree rhs) {
         return (Trees.Tree)(this.$outer.global().new Assign(pat, rhs)).updateAttachment(this.$outer.global().ForAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class));
      }

      public Option unapply(final Trees.Tree tree) {
         if (tree instanceof Trees.Assign) {
            Trees.Assign var2 = (Trees.Assign)tree;
            Trees.Tree pat = var2.lhs();
            Trees.Tree rhs = var2.rhs();
            if (tree.hasAttachment(scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class))) {
               return new Some(new Tuple2(pat, rhs));
            }
         }

         return scala.None..MODULE$;
      }

      public ValEq$() {
         if (TreeGen.this == null) {
            throw null;
         } else {
            this.$outer = TreeGen.this;
            super();
         }
      }
   }

   public class Filter$ {
      // $FF: synthetic field
      private final TreeGen $outer;

      public Trees.Apply apply(final Trees.Tree tree) {
         return this.$outer.global().new Apply((Trees.Tree)(this.$outer.global().new Ident(this.$outer.global().nme().IFkw())).updateAttachment(this.$outer.global().ForAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class)), new scala.collection.immutable..colon.colon(tree, .MODULE$));
      }

      public Option unapply(final Trees.Tree tree) {
         if (tree instanceof Trees.Apply) {
            Trees.Apply var2 = (Trees.Apply)tree;
            Trees.Tree id = var2.fun();
            List var4 = var2.args();
            if (id instanceof Trees.Ident) {
               Trees.Ident var5 = (Trees.Ident)id;
               Names.Name var6 = var5.name();
               Names.TermName var10000 = this.$outer.global().nme().IFkw();
               if (var10000 == null) {
                  if (var6 != null) {
                     return scala.None..MODULE$;
                  }
               } else if (!var10000.equals(var6)) {
                  return scala.None..MODULE$;
               }

               if (var4 != null) {
                  List var13 = scala.package..MODULE$.List();
                  if (var13 == null) {
                     throw null;
                  }

                  List unapplySeq_this = var13;
                  SeqOps var14 = SeqFactory.unapplySeq$(unapplySeq_this, var4);
                  Object var12 = null;
                  SeqOps var7 = var14;
                  SeqFactory.UnapplySeqWrapper var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var7);
                  var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int lengthCompare$extension_len = 1;
                  if (var7.lengthCompare(lengthCompare$extension_len) == 0) {
                     var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int apply$extension_i = 0;
                     Trees.Tree cond = (Trees.Tree)var7.apply(apply$extension_i);
                     if (var5.hasAttachment(scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class))) {
                        return new Some(cond);
                     }
                  }
               }
            }
         }

         return scala.None..MODULE$;
      }

      public Filter$() {
         if (TreeGen.this == null) {
            throw null;
         } else {
            this.$outer = TreeGen.this;
            super();
         }
      }
   }

   public class Yield$ {
      // $FF: synthetic field
      private final TreeGen $outer;

      public Trees.Tree apply(final Trees.Tree tree) {
         return this.$outer.global().new Apply((Trees.Tree)(this.$outer.global().new Ident(this.$outer.global().nme().YIELDkw())).updateAttachment(this.$outer.global().ForAttachment(), scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class)), new scala.collection.immutable..colon.colon(tree, .MODULE$));
      }

      public Option unapply(final Trees.Tree tree) {
         if (tree instanceof Trees.Apply) {
            Trees.Apply var2 = (Trees.Apply)tree;
            Trees.Tree id = var2.fun();
            List var4 = var2.args();
            if (id instanceof Trees.Ident) {
               Trees.Ident var5 = (Trees.Ident)id;
               Names.Name var6 = var5.name();
               Names.TermName var10000 = this.$outer.global().nme().YIELDkw();
               if (var10000 == null) {
                  if (var6 != null) {
                     return scala.None..MODULE$;
                  }
               } else if (!var10000.equals(var6)) {
                  return scala.None..MODULE$;
               }

               if (var4 != null) {
                  List var13 = scala.package..MODULE$.List();
                  if (var13 == null) {
                     throw null;
                  }

                  List unapplySeq_this = var13;
                  SeqOps var14 = SeqFactory.unapplySeq$(unapplySeq_this, var4);
                  Object var12 = null;
                  SeqOps var7 = var14;
                  SeqFactory.UnapplySeqWrapper var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var7);
                  var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int lengthCompare$extension_len = 1;
                  if (var7.lengthCompare(lengthCompare$extension_len) == 0) {
                     var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var15 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int apply$extension_i = 0;
                     Trees.Tree tree = (Trees.Tree)var7.apply(apply$extension_i);
                     if (var5.hasAttachment(scala.reflect.ClassTag..MODULE$.apply(StdAttachments.ForAttachment$.class))) {
                        return new Some(tree);
                     }
                  }
               }
            }
         }

         return scala.None..MODULE$;
      }

      public Yield$() {
         if (TreeGen.this == null) {
            throw null;
         } else {
            this.$outer = TreeGen.this;
            super();
         }
      }
   }

   public class GetVarTraverser extends scala.reflect.api.Trees.Traverser {
      private final ListBuffer buf;
      // $FF: synthetic field
      public final TreeGen $outer;

      public ListBuffer buf() {
         return this.buf;
      }

      public Position namePos(final Trees.Tree tree, final Names.Name name) {
         if (tree.pos().isRange() && !name.containsName((Names.Name)this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().nme().raw().DOLLAR())) {
            int start = tree.pos().start();
            int end = start + name.decode().length();
            return this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().rangePos(tree.pos().source(), start, start, end);
         } else {
            return tree.pos().focus();
         }
      }

      public void traverse(final Trees.Tree tree) {
         int bl;
         label38: {
            label41: {
               bl = this.buf().length();
               boolean var3 = false;
               Trees.Bind var4 = null;
               if (tree instanceof Trees.Bind) {
                  var3 = true;
                  var4 = (Trees.Bind)tree;
                  Names.Name var5 = var4.name();
                  Names.Name var10000 = this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().nme().WILDCARD();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label41;
                     }
                  } else if (var10000.equals(var5)) {
                     break label41;
                  }
               }

               if (var3) {
                  Names.Name name = var4.name();
                  Trees.Tree var7 = var4.body();
                  if (var7 instanceof Trees.Typed) {
                     Trees.Typed var8 = (Trees.Typed)var7;
                     Trees.Tree tree1 = var8.expr();
                     Trees.Tree tpt = var8.tpt();
                     Trees.Tree newTree = (Trees.Tree)(this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().treeInfo().mayBeTypePat(tpt) ? this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().new TypeTree() : tpt.duplicate());
                     this.add$1(name, newTree, tree);
                     this.traverse(tree1);
                     break label38;
                  }
               }

               if (var3) {
                  Names.Name name = var4.name();
                  Trees.Tree tree1 = var4.body();
                  this.add$1(name, this.scala$reflect$internal$TreeGen$GetVarTraverser$$$outer().global().new TypeTree(), tree);
                  this.traverse(tree1);
               } else {
                  super.traverse(tree);
               }
               break label38;
            }

            super.traverse(tree);
         }

         if (this.buf().length() > bl) {
            tree.setPos(tree.pos().makeTransparent());
         }
      }

      public List apply(final Trees.Tree tree) {
         this.traverse(tree);
         return this.buf().toList();
      }

      // $FF: synthetic method
      public TreeGen scala$reflect$internal$TreeGen$GetVarTraverser$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$traverse$1(final Names.Name name$1, final Tuple4 x$20) {
         Object var10000 = x$20._1();
         if (var10000 == null) {
            if (name$1 == null) {
               return true;
            }
         } else if (var10000.equals(name$1)) {
            return true;
         }

         return false;
      }

      private final boolean seenName$1(final Names.Name name) {
         return this.buf().exists((x$20) -> BoxesRunTime.boxToBoolean($anonfun$traverse$1(name, x$20)));
      }

      private final Object add$1(final Names.Name name, final Trees.Tree t, final Trees.Tree tree$1) {
         if (!this.seenName$1(name)) {
            ListBuffer var10000 = this.buf();
            Position var5 = this.namePos(tree$1, name);
            Tuple4 $plus$eq_elem = new Tuple4(name, t, var5, tree$1);
            if (var10000 == null) {
               throw null;
            } else {
               return var10000.addOne($plus$eq_elem);
            }
         } else {
            return BoxedUnit.UNIT;
         }
      }

      public GetVarTraverser() {
         if (TreeGen.this == null) {
            throw null;
         } else {
            this.$outer = TreeGen.this;
            super();
            this.buf = new ListBuffer();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class PatvarTransformer extends scala.reflect.api.Trees.Transformer {
      // $FF: synthetic field
      public final TreeGen $outer;

      public Trees.Tree transform(final Trees.Tree tree) {
         boolean var2 = false;
         Trees.Typed var3 = null;
         boolean var4 = false;
         Trees.Apply var5 = null;
         if (tree instanceof Trees.Ident) {
            Names.Name name = ((Trees.Ident)tree).name();
            if (this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().treeInfo().isVarPattern(tree)) {
               Names.Name var7 = this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().nme().WILDCARD();
               if (name == null) {
                  if (var7 != null) {
                     return this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().atPos((Position)tree.pos(), (Trees.Tree)(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Bind(name, this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().atPos((Position)tree.pos().focus(), (Trees.Tree)(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Ident(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().nme().WILDCARD()))))));
                  }
               } else if (!name.equals(var7)) {
                  return this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().atPos((Position)tree.pos(), (Trees.Tree)(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Bind(name, this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().atPos((Position)tree.pos().focus(), (Trees.Tree)(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Ident(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().nme().WILDCARD()))))));
               }
            }
         }

         if (tree instanceof Trees.Typed) {
            var2 = true;
            var3 = (Trees.Typed)tree;
            Trees.Tree id = var3.expr();
            Trees.Tree tpt = var3.tpt();
            if (id instanceof Trees.Ident) {
               Trees.Ident var10 = (Trees.Ident)id;
               Names.Name name = var10.name();
               if (this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().treeInfo().isVarPattern(var10)) {
                  Names.Name var12 = this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().nme().WILDCARD();
                  if (name == null) {
                     if (var12 != null) {
                        return this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().atPos((Position)tree.pos().withPoint(var10.pos().point()), (Trees.Tree)(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Bind(name, this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().atPos((Position)tree.pos().withStart(tree.pos().point()), (Trees.Tree)(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Typed(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Ident(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().nme().WILDCARD()), tpt))))));
                     }
                  } else if (!name.equals(var12)) {
                     return this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().atPos((Position)tree.pos().withPoint(var10.pos().point()), (Trees.Tree)(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Bind(name, this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().atPos((Position)tree.pos().withStart(tree.pos().point()), (Trees.Tree)(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Typed(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().new Ident(this.scala$reflect$internal$TreeGen$PatvarTransformer$$$outer().global().nme().WILDCARD()), tpt))))));
                  }
               }
            }
         }

         if (tree instanceof Trees.Apply) {
            var4 = true;
            var5 = (Trees.Apply)tree;
            Trees.Tree fn = var5.fun();
            List args = var5.args();
            if (fn instanceof Trees.Apply) {
               Trees.Apply var15 = (Trees.Apply)fn;
               return (Trees.Tree)this.treeCopy().Apply(tree, this.transform((Trees.Tree)var15), this.transformTrees(args));
            }
         }

         if (var4) {
            Trees.Tree fn = var5.fun();
            List args = var5.args();
            return (Trees.Tree)this.treeCopy().Apply(tree, fn, this.transformTrees(args));
         } else if (var2) {
            Trees.Tree expr = var3.expr();
            Trees.Tree tpt = var3.tpt();
            return (Trees.Tree)this.treeCopy().Typed(tree, this.transform(expr), tpt);
         } else if (tree instanceof Trees.Bind) {
            Trees.Bind var20 = (Trees.Bind)tree;
            Names.Name name = var20.name();
            Trees.Tree body = var20.body();
            return (Trees.Tree)this.treeCopy().Bind(tree, name, this.transform(body));
         } else if (tree instanceof Trees.Alternative ? true : tree instanceof Trees.Star) {
            return (Trees.Tree)super.transform(tree);
         } else {
            return tree;
         }
      }

      // $FF: synthetic method
      public TreeGen scala$reflect$internal$TreeGen$PatvarTransformer$$$outer() {
         return this.$outer;
      }

      public PatvarTransformer() {
         if (TreeGen.this == null) {
            throw null;
         } else {
            this.$outer = TreeGen.this;
            super();
         }
      }
   }

   public class patvarTransformer$ extends PatvarTransformer {
   }
}
