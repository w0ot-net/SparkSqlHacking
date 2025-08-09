package spire.math;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Stream;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.math.ScalaNumericConversions;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011]hACA\u0016\u0003[\u0001\n1!\t\u00028!9\u0011Q\n\u0001\u0005\u0002\u0005=\u0003bBA-\u0001\u0019\u0005\u00111\f\u0005\b\u0003_\u0002A\u0011AA9\u0011\u001d\ty\u0007\u0001C\u0001\u0003wBq!! \u0001\t\u0003\ny\bC\u0004\u0002\b\u0002!\t%!#\t\u000f\u0005E\u0005\u0001\"\u0011\u0002\u0014\"9\u0011Q\u0013\u0001\u0005B\u0005]\u0005bBAP\u0001\u0011\u0005\u0013\u0011\u0015\u0005\b\u0003g\u0003A\u0011IA[\u0011\u001d\ti\f\u0001C!\u0003kCq!a0\u0001\t\u0003\n)\fC\u0004\u0002B\u0002!\t%!.\t\u000f\u0005\r\u0007\u0001\"\u0001\u00026\"9\u0011Q\u0019\u0001\u0005B\u0005\u001d\u0007bBAe\u0001\u0011\u0005\u00131\u001a\u0005\b\u0003/\u0004A\u0011AAm\u0011\u001d\ty\u000e\u0001C\u0001\u0003CDq!!:\u0001\t\u0003\t9\u000fC\u0004\u0002l\u0002!\t!!<\t\u000f\u0005E\b\u0001\"\u0001\u0002t\"9\u0011q\u001f\u0001\u0005\u0002\u0005e\bbBA~\u0001\u0011\u0005\u00111\u0013\u0005\b\u0003{\u0004A\u0011AA}\u0011\u001d\ty\u0010\u0001C\u0001\u0003sDqA!\u0001\u0001\t\u0003\u0011\u0019\u0001C\u0004\u0003\b\u0001!\tA!\u0003\t\u000f\t5\u0001\u0001\"\u0001\u0003\u0010!9!1\u0003\u0001\u0005\u0002\tU\u0001b\u0002B\u000e\u0001\u0011\u0005!Q\u0004\u0005\b\u0005C\u0001A\u0011\u0001B\u0012\u0011\u001d\u00119\u0003\u0001C\u0001\u0005SAqA!\f\u0001\t\u0003\u0011y\u0003C\u0004\u00034\u0001!\t!!?\t\u000f\tU\u0002\u0001\"\u0001\u0002z\"9!q\u0007\u0001\u0005\u0002\u0005e\bb\u0002B\u001d\u0001\u0011\u0005\u0011Q\u0017\u0005\b\u0005w\u0001A\u0011AA}\u0011\u001d\u0011i\u0004\u0001C\u0001\u0005\u007fAqAa\u0011\u0001\t\u0003\u0011)\u0005C\u0004\u0003D\u0001!\tAa\u0013\t\u000f\t=\u0003\u0001\"\u0011\u0003R!9!\u0011\u000e\u0001\u0005\u0002\t-\u0004b\u0002B7\u0001\u0011\u0005!qN\u0004\t\tk\fi\u0003#\u0001\u0003\u0018\u001aA\u00111FA\u0017\u0011\u0003\u0011I\bC\u0004\u0003\u0014:\"\tA!&\t\u0013\teeF1A\u0005\u0002\u0005e\b\u0002\u0003BN]\u0001\u0006I!!8\t\u0013\tueF1A\u0005\u0002\u0005e\b\u0002\u0003BP]\u0001\u0006I!!8\t\u0013\t\u0005fF1A\u0005\u0002\u0005e\b\u0002\u0003BR]\u0001\u0006I!!8\t\u0013\t\u0015fF1A\u0005\u0002\u0005e\b\u0002\u0003BT]\u0001\u0006I!!8\t\u000f\u0005ec\u0006\"\u0001\u0003*\"9\u0011\u0011\f\u0018\u0005\u0004\tU\u0006bBA-]\u0011\r!1\u0018\u0005\b\u00033rC1\u0001B`\u0011\u001d\tIF\fC\u0002\u0005+Dq!!\u0017/\t\u0007\u0011I\u000eC\u0004\u0002Z9\"\u0019A!8\t\u000f\u0005ec\u0006b\u0001\u0003b\"9\u0011\u0011\f\u0018\u0005\u0002\t-\bB\u0003By]!\u0015\r\u0011\"\u0001\u0002z\"Q!1\u001f\u0018\t\u0006\u0004%\t!!?\t\u0015\tUh\u0006#b\u0001\n\u0003\tI\u0010C\u0004\u0003x:\"\tA!?\t\u000f\t}h\u0006\"\u0001\u0004\u0002!91Q\u0001\u0018\u0005\u0002\r\u001d\u0001bBB\u0006]\u0011\u00051Q\u0002\u0005\b\u0007#qC\u0011AB\n\u0011\u001d\u00199B\fC\u0001\u00073Aqa!\b/\t\u0003\u0019y\u0002C\u0004\u0004&9\"\taa\n\t\u000f\r-b\u0006\"\u0001\u0004.!91\u0011\u0007\u0018\u0005\u0002\rM\u0002bBB\u001c]\u0011\u00051\u0011\b\u0005\b\u0007{qC\u0011AB \u0011\u001d\u0019\u0019E\fC\u0001\u0007\u000bBqa!\u0013/\t\u0003\u0019Y\u0005C\u0004\u0004P9\"\ta!\u0015\t\u000f\rUc\u0006\"\u0001\u0002\u0014\"91q\u000b\u0018\u0005\u0002\u0005M\u0005bBB-]\u0011\u000511\f\u0005\b\u0007?rC\u0011AB1\u0011\u001d\u0019IG\fC\u0001\u0007WBqaa\u001c/\t\u0003\u0019\t\bC\u0004\u0004x9\"\ta!\u001f\t\u0015\r}d\u0006#b\u0001\n\u0003\tI\u0010\u0003\u0006\u0004\u0002:B)\u0019!C\u0001\u0003sD!ba!/\u0011\u000b\u0007I\u0011AA}\u0011)\u0019)I\fEC\u0002\u0013\u0005\u0011\u0011 \u0005\b\u0007\u000fsC\u0011ABE\u0011\u001d\u00199I\fC\u0001\u0007SC\u0011b!4/\t\u0003\t\tda4\t\u0013\rug\u0006\"\u0001\u00022\r}\u0007bBBu]\u0011\u000511\u001e\u0005\b\u0007_tC\u0011ABy\u0011\u001d\u0019)P\fC\u0001\u0007oDqaa?/\t\u0003\u0019i\u0010C\u0004\u0005\u00029\"\t\u0001b\u0001\t\u000f\u0011\u001da\u0006\"\u0001\u0005\n!9AQ\u0002\u0018\u0005\u0002\u0011=aA\u0002B<]\u0001#)\u000e\u0003\u0006\u0003:&\u0014)\u001a!C\u0001\u0003wB!\u0002b6j\u0005#\u0005\u000b\u0011BA:\u0011\u001d\u0011\u0019*\u001bC\u0001\t3Dq!!\u0017j\t\u0003!i\u000eC\u0005\u0005\u0000%\f\t\u0011\"\u0001\u0005b\"IAQQ5\u0012\u0002\u0013\u0005AQ\u001d\u0005\n\t;K\u0017\u0011!C!\t?C\u0011\u0002\")j\u0003\u0003%\t!a%\t\u0013\u0011\r\u0016.!A\u0005\u0002\u0011%\b\"\u0003CUS\u0006\u0005I\u0011\tCV\u0011%!),[A\u0001\n\u0003!i\u000fC\u0005\u0005<&\f\t\u0011\"\u0011\u0005r\u001eIA1\u0003\u0018\u0002\u0002#\u0005AQ\u0003\u0004\n\u0005or\u0013\u0011!E\u0001\t3AqAa%x\t\u0003!I\u0003C\u0005\u0003P]\f\t\u0011\"\u0012\u0005,!I\u0011\u0011L<\u0002\u0002\u0013\u0005E\u0011\u0007\u0005\n\tk9\u0018\u0011!CA\toA\u0011\u0002b\u0011x\u0003\u0003%I!!)\u0007\r\u0011\u0015c\u0006\u0011C$\u0011)\u0011i+ BK\u0002\u0013\u0005A1\u000b\u0005\u000b\t+j(\u0011#Q\u0001\n\t=\u0006b\u0002BJ{\u0012\u0005Aq\u000b\u0005\f\t;j\b\u0019!C\u0001\u0003c!y\u0006C\u0006\u0005ju\u0004\r\u0011\"\u0001\u00022\u0011-\u0004\u0002\u0003C9{\u0002\u0006K\u0001\"\u0019\t\u000f\u0005eS\u0010\"\u0001\u0005|!IAqP?\u0002\u0002\u0013\u0005A\u0011\u0011\u0005\n\t\u000bk\u0018\u0013!C\u0001\t\u000fC\u0011\u0002\"(~\u0003\u0003%\t\u0005b(\t\u0013\u0011\u0005V0!A\u0005\u0002\u0005M\u0005\"\u0003CR{\u0006\u0005I\u0011\u0001CS\u0011%!I+`A\u0001\n\u0003\"Y\u000bC\u0005\u00056v\f\t\u0011\"\u0001\u00058\"IA1X?\u0002\u0002\u0013\u0005CQX\u0004\n\t\u0003t\u0013\u0011!E\u0001\t\u00074\u0011\u0002\"\u0012/\u0003\u0003E\t\u0001\"2\t\u0011\tM\u0015Q\u0004C\u0001\t\u0013D!Ba\u0014\u0002\u001e\u0005\u0005IQ\tC\u0016\u0011)\tI&!\b\u0002\u0002\u0013\u0005E1\u001a\u0005\u000b\tk\ti\"!A\u0005\u0002\u0012=\u0007B\u0003C\"\u0003;\t\t\u0011\"\u0003\u0002\"\"IA1\t\u0018\u0002\u0002\u0013%\u0011\u0011\u0015\u0002\u0005%\u0016\fGN\u0003\u0003\u00020\u0005E\u0012\u0001B7bi\"T!!a\r\u0002\u000bM\u0004\u0018N]3\u0004\u0001M)\u0001!!\u000f\u0002HA!\u00111HA\"\u001b\t\tiD\u0003\u0003\u00020\u0005}\"BAA!\u0003\u0015\u00198-\u00197b\u0013\u0011\t)%!\u0010\u0003\u0017M\u001b\u0017\r\\1Ok6\u0014WM\u001d\t\u0005\u0003w\tI%\u0003\u0003\u0002L\u0005u\"aF*dC2\fg*^7fe&\u001c7i\u001c8wKJ\u001c\u0018n\u001c8t\u0003\u0019!\u0013N\\5uIQ\u0011\u0011\u0011\u000b\t\u0005\u0003'\n)&\u0004\u0002\u0002@%!\u0011qKA \u0005\u0011)f.\u001b;\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005u\u0013Q\r\t\u0005\u0003?\n\t'\u0004\u0002\u0002.%!\u00111MA\u0017\u0005!\u0019\u0016MZ3M_:<\u0007bBA4\u0005\u0001\u0007\u0011\u0011N\u0001\u0002aB!\u00111KA6\u0013\u0011\ti'a\u0010\u0003\u0007%sG/\u0001\u0006u_J\u000bG/[8oC2$B!a\u001d\u0002zA!\u0011qLA;\u0013\u0011\t9(!\f\u0003\u0011I\u000bG/[8oC2Dq!a\u001a\u0004\u0001\u0004\tI'\u0006\u0002\u0002t\u0005YAm\\;cY\u00164\u0016\r\\;f+\t\t\t\t\u0005\u0003\u0002T\u0005\r\u0015\u0002BAC\u0003\u007f\u0011a\u0001R8vE2,\u0017A\u00034m_\u0006$h+\u00197vKV\u0011\u00111\u0012\t\u0005\u0003'\ni)\u0003\u0003\u0002\u0010\u0006}\"!\u0002$m_\u0006$\u0018\u0001C5oiZ\u000bG.^3\u0016\u0005\u0005%\u0014!\u00037p]\u001e4\u0016\r\\;f+\t\tI\n\u0005\u0003\u0002T\u0005m\u0015\u0002BAO\u0003\u007f\u0011A\u0001T8oO\u0006QQO\u001c3fe2L\u0018N\\4\u0015\u0005\u0005\r\u0006\u0003BAS\u0003_k!!a*\u000b\t\u0005%\u00161V\u0001\u0005Y\u0006twM\u0003\u0002\u0002.\u0006!!.\u0019<b\u0013\u0011\t\t,a*\u0003\r=\u0013'.Z2u\u0003-I7OV1mS\u0012\u001c\u0005.\u0019:\u0016\u0005\u0005]\u0006\u0003BA*\u0003sKA!a/\u0002@\t9!i\\8mK\u0006t\u0017aC5t-\u0006d\u0017\u000e\u001a\"zi\u0016\fA\"[:WC2LGm\u00155peR\f!\"[:WC2LG-\u00138u\u0003-I7OV1mS\u0012duN\\4\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u001b\u0002\r\u0015\fX/\u00197t)\u0011\t9,!4\t\u000f\u0005=\u0007\u00031\u0001\u0002R\u0006\t\u0011\u0010\u0005\u0003\u0002T\u0005M\u0017\u0002BAk\u0003\u007f\u00111!\u00118z\u0003%!S-\u001d\u0013fc\u0012*\u0017\u000f\u0006\u0003\u00028\u0006m\u0007bBAh#\u0001\u0007\u0011Q\u001c\t\u0004\u0003?\u0002\u0011a\u0003\u0013fc\u0012\u0012\u0017M\\4%KF$B!a.\u0002d\"9\u0011q\u001a\nA\u0002\u0005u\u0017aB2p[B\f'/\u001a\u000b\u0005\u0003S\nI\u000fC\u0004\u0002PN\u0001\r!!8\u0002\u00075Lg\u000e\u0006\u0003\u0002^\u0006=\bbBAh)\u0001\u0007\u0011Q\\\u0001\u0004[\u0006DH\u0003BAo\u0003kDq!a4\u0016\u0001\u0004\ti.A\u0002bEN,\"!!8\u0002\rMLwM\\;n\u00031)h.\u0019:z?\u0012j\u0017N\\;t\u0003)\u0011XmY5qe>\u001c\u0017\r\\\u0001\u0006IAdWo\u001d\u000b\u0005\u0003;\u0014)\u0001C\u0004\u0002Pj\u0001\r!!8\u0002\r\u0011j\u0017N\\;t)\u0011\tiNa\u0003\t\u000f\u0005=7\u00041\u0001\u0002^\u00061A\u0005^5nKN$B!!8\u0003\u0012!9\u0011q\u001a\u000fA\u0002\u0005u\u0017\u0001\u0004\u0013uS6,7\u000f\n;j[\u0016\u001cH\u0003BAo\u0005/AqA!\u0007\u001e\u0001\u0004\tI'A\u0001l\u0003\r\u0001xn\u001e\u000b\u0005\u0003;\u0014y\u0002C\u0004\u0003\u001ay\u0001\r!!\u001b\u0002\t\u0011\"\u0017N\u001e\u000b\u0005\u0003;\u0014)\u0003C\u0004\u0002P~\u0001\r!!8\u0002\tQlw\u000e\u001a\u000b\u0005\u0003;\u0014Y\u0003C\u0004\u0002P\u0002\u0002\r!!8\u0002\u000bQ\fXo\u001c;\u0015\t\u0005u'\u0011\u0007\u0005\b\u0003\u001f\f\u0003\u0019AAo\u0003\u0011\u0019W-\u001b7\u0002\u000b\u0019dwn\u001c:\u0002\u000bI|WO\u001c3\u0002\u000f%\u001cx\u000b[8mK\u0006!1/\u001d:u\u0003\u0015q'o\\8u)\u0011\tiN!\u0011\t\u000f\teq\u00051\u0001\u0002j\u0005!a\r]8x)\u0011\tiNa\u0012\t\u000f\t%\u0003\u00061\u0001\u0002t\u0005\t!\u000f\u0006\u0003\u0002^\n5\u0003bBAhS\u0001\u0007\u0011Q\\\u0001\ti>\u001cFO]5oOR\u0011!1\u000b\t\u0005\u0005+\u0012\u0019G\u0004\u0003\u0003X\t}\u0003\u0003\u0002B-\u0003\u007fi!Aa\u0017\u000b\t\tu\u0013QG\u0001\u0007yI|w\u000e\u001e \n\t\t\u0005\u0014qH\u0001\u0007!J,G-\u001a4\n\t\t\u0015$q\r\u0002\u0007'R\u0014\u0018N\\4\u000b\t\t\u0005\u0014qH\u0001\u0005e\u0016\u0004(/\u0006\u0002\u0003T\u0005Iq-\u001a;TiJLgn\u001a\u000b\u0005\u0005'\u0012\t\bC\u0004\u0003t1\u0002\r!!\u001b\u0002\u0003\u0011L3\u0001A5~\u0005\u0015)\u00050Y2u'\u001dq#1\u0010BA\u0005\u000f\u0003B!a\u0015\u0003~%!!qPA \u0005\u0019\te.\u001f*fMB!\u0011q\fBB\u0013\u0011\u0011))!\f\u0003\u001bI+\u0017\r\\%ogR\fgnY3t!\u0011\u0011IIa$\u000e\u0005\t-%\u0002\u0002BG\u0003W\u000b!![8\n\t\tE%1\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t]\u0005cAA0]\u0005!!0\u001a:p\u0003\u0015QXM]8!\u0003\ryg.Z\u0001\u0005_:,\u0007%A\u0002uo>\fA\u0001^<pA\u0005!am\\;s\u0003\u00151w.\u001e:!)\u0011\tiNa+\t\u000f\t5\u0006\b1\u0001\u00030\u0006\ta\r\u0005\u0005\u0002T\tE\u0016\u0011NA/\u0013\u0011\u0011\u0019,a\u0010\u0003\u0013\u0019+hn\u0019;j_:\fD\u0003BAo\u0005oCqA!/:\u0001\u0004\tI'A\u0001o)\u0011\tiN!0\t\u000f\te&\b1\u0001\u0002\u001aR!\u0011Q\u001cBa\u0011\u001d\u0011Il\u000fa\u0001\u0005\u0007\u0004BA!2\u0003P:!!q\u0019Bf\u001d\u0011\u0011IF!3\n\u0005\u0005\u0005\u0013\u0002\u0002Bg\u0003\u007f\tq\u0001]1dW\u0006<W-\u0003\u0003\u0003R\nM'A\u0002\"jO&sGO\u0003\u0003\u0003N\u0006}B\u0003BAo\u0005/DqA!/=\u0001\u0004\ti\u0006\u0006\u0003\u0002^\nm\u0007b\u0002B]{\u0001\u0007\u00111\u000f\u000b\u0005\u0003;\u0014y\u000eC\u0004\u0003:z\u0002\r!!!\u0015\t\u0005u'1\u001d\u0005\b\u0005s{\u0004\u0019\u0001Bs!\u0011\u0011)Ma:\n\t\t%(1\u001b\u0002\u000b\u0005&<G)Z2j[\u0006dG\u0003BAo\u0005[DqAa<A\u0001\u0004\u0011\u0019&A\u0001t\u0003\t\u0001\u0018.A\u0001f\u0003\r\u0001\b.[\u0001\u0004Y><G\u0003BAo\u0005wDqA!@E\u0001\u0004\ti.A\u0001y\u0003\r)\u0007\u0010\u001d\u000b\u0005\u0003;\u001c\u0019\u0001C\u0004\u0003~\u0016\u0003\r!!8\u0002\u0007MLg\u000e\u0006\u0003\u0002^\u000e%\u0001b\u0002B\u007f\r\u0002\u0007\u0011Q\\\u0001\u0004G>\u001cH\u0003BAo\u0007\u001fAqA!@H\u0001\u0004\ti.A\u0002uC:$B!!8\u0004\u0016!9!Q %A\u0002\u0005u\u0017\u0001B1uC:$B!!8\u0004\u001c!9!Q`%A\u0002\u0005u\u0017!B1uC:\u0014DCBAo\u0007C\u0019\u0019\u0003C\u0004\u0002P*\u0003\r!!8\t\u000f\tu(\n1\u0001\u0002^\u0006!\u0011m]5o)\u0011\tin!\u000b\t\u000f\tu8\n1\u0001\u0002^\u0006!\u0011mY8t)\u0011\tina\f\t\u000f\tuH\n1\u0001\u0002^\u0006!1/\u001b8i)\u0011\tin!\u000e\t\u000f\tuX\n1\u0001\u0002^\u0006!1m\\:i)\u0011\tina\u000f\t\u000f\tuh\n1\u0001\u0002^\u0006!A/\u00198i)\u0011\tin!\u0011\t\u000f\tux\n1\u0001\u0002^\u0006)\u0011m]5oQR!\u0011Q\\B$\u0011\u001d\u0011i\u0010\u0015a\u0001\u0003;\fQ!Y2pg\"$B!!8\u0004N!9!Q`)A\u0002\u0005u\u0017!B1uC:DG\u0003BAo\u0007'BqA!@S\u0001\u0004\ti.\u0001\u0004eS\u001eLGo]\u0001\u0005E&$8/\u0001\u0007eS\u001eLGo\u001d+p\u0005&$8\u000f\u0006\u0003\u0002j\ru\u0003b\u0002B]+\u0002\u0007\u0011\u0011N\u0001\u000bg&TX-\u00138CCN,GCBA5\u0007G\u001a)\u0007C\u0004\u0003:Z\u0003\r!!\u0018\t\u000f\r\u001dd\u000b1\u0001\u0002j\u0005!!-Y:f\u0003\u001d\u0011x.\u001e8e+B$B!!\u0018\u0004n!9!\u0011J,A\u0002\u0005M\u0014!\u00023jmJrGCBAo\u0007g\u001a)\bC\u0004\u0003~b\u0003\r!!8\t\u000f\te\u0006\f1\u0001\u0002j\u0005)Q.\u001e73]R1\u0011Q\\B>\u0007{BqA!@Z\u0001\u0004\ti\u000eC\u0004\u0003:f\u0003\r!!\u001b\u0002\u000bAL')\u001f\u001a\u0002\u000bAL')\u001f\u001b\u0002\t1|wMM\u0001\tgF\u0014H/\r\"ze\u0005Q\u0011mY2v[Vd\u0017\r^3\u0015\u0011\u0005u31RBH\u0007GCqa!$_\u0001\u0004\ti&A\u0003u_R\fG\u000eC\u0004\u0004\u0012z\u0003\raa%\u0002\u0005a\u001c\bCBBK\u0007?\u000bi&\u0004\u0002\u0004\u0018*!1\u0011TBN\u0003%IW.\\;uC\ndWM\u0003\u0003\u0004\u001e\u0006}\u0012AC2pY2,7\r^5p]&!1\u0011UBL\u0005!a\u0015M_=MSN$\bbBBS=\u0002\u00071qU\u0001\u0003GN\u0004ba!&\u0004 \u0006MD\u0003CA/\u0007W\u001bik!.\t\u000f\r5u\f1\u0001\u0002^!91\u0011S0A\u0002\r=\u0006C\u0002Bc\u0007c\u000bi&\u0003\u0003\u00044\nM'AB*ue\u0016\fW\u000eC\u0004\u0004&~\u0003\raa.\u0011\r\t\u00157\u0011WA:Q-y61XBa\u0007\u0007\u001c9m!3\u0011\t\u0005M3QX\u0005\u0005\u0007\u007f\u000byD\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u0004F\u00069\u0002O]3gKJ\u0004C*\u0019>z\u0019&\u001cH\u000fI5ogR,\u0017\rZ\u0001\u0006g&t7-Z\u0011\u0003\u0007\u0017\fa\u0001\r\u00182o9\u0002\u0014a\u00039po\u0016\u00148+\u001a:jKN$\u0002\"!8\u0004R\u000eU71\u001c\u0005\b\u0007'\u0004\u0007\u0019ABT\u0003\t\u00018\u000fC\u0004\u0004X\u0002\u0004\ra!7\u0002\u000bQ,'/\\:\u0011\u0011\u0005M#\u0011WA5\u0003SBqA!@a\u0001\u0004\ti.\u0001\u0004bG\u000e\u001cV-\u001d\u000b\u0005\u0007O\u001b\t\u000fC\u0004\u0003.\u0006\u0004\raa9\u0011\u0015\u0005M3Q]A:\u0003;\n\u0019(\u0003\u0003\u0004h\u0006}\"!\u0003$v]\u000e$\u0018n\u001c83\u0003\u0015)\u0007\u0010\u001d#s)\u0011\tin!<\t\u000f\tu(\r1\u0001\u0002^\u0006)An\\4EeR!\u0011Q\\Bz\u0011\u001d\u0011ip\u0019a\u0001\u0003;\fa\u0001\\8h\tJDH\u0003BAo\u0007sDqA!@e\u0001\u0004\ti.A\u0003tS:$%\u000f\u0006\u0003\u0002^\u000e}\bb\u0002B\u007fK\u0002\u0007\u0011Q\\\u0001\u0006G>\u001cHI\u001d\u000b\u0005\u0003;$)\u0001C\u0004\u0003~\u001a\u0004\r!!8\u0002\r\u0005$\u0018M\u001c#s)\u0011\ti\u000eb\u0003\t\u000f\tux\r1\u0001\u0002^\u00069\u0011\r^1o\tJDH\u0003BAo\t#AqA!@i\u0001\u0004\ti.A\u0003Fq\u0006\u001cG\u000fE\u0002\u0005\u0018]l\u0011AL\n\u0006o\u0012m!q\u0011\t\t\t;!\u0019#a\u001d\u0005(5\u0011Aq\u0004\u0006\u0005\tC\ty$A\u0004sk:$\u0018.\\3\n\t\u0011\u0015Bq\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004c\u0001C\fSR\u0011AQ\u0003\u000b\u0003\t[\u0001B!!*\u00050%!!QMAT)\u0011!9\u0003b\r\t\u000f\te&\u00101\u0001\u0002t\u00059QO\\1qa2LH\u0003\u0002C\u001d\t\u007f\u0001b!a\u0015\u0005<\u0005M\u0014\u0002\u0002C\u001f\u0003\u007f\u0011aa\u00149uS>t\u0007\"\u0003C!w\u0006\u0005\t\u0019\u0001C\u0014\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u0002\b\u0013:,\u00070Y2u'%i\u0018\u0011HAo\t\u0013\"y\u0005\u0005\u0003\u0002T\u0011-\u0013\u0002\u0002C'\u0003\u007f\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0003F\u0012E\u0013\u0002\u0002BI\u0005',\"Aa,\u0002\u0005\u0019\u0004C\u0003\u0002C-\t7\u00022\u0001b\u0006~\u0011!\u0011i+!\u0001A\u0002\t=\u0016\u0001B7f[>,\"\u0001\"\u0019\u0011\r\u0005MC1\bC2!!\t\u0019\u0006\"\u001a\u0002j\u0005u\u0013\u0002\u0002C4\u0003\u007f\u0011a\u0001V;qY\u0016\u0014\u0014\u0001C7f[>|F%Z9\u0015\t\u0005ECQ\u000e\u0005\u000b\t_\n)!!AA\u0002\u0011\u0005\u0014a\u0001=%c\u0005)Q.Z7pA!\"\u0011q\u0001C;!\u0011\t\u0019\u0006b\u001e\n\t\u0011e\u0014q\b\u0002\tm>d\u0017\r^5mKR!\u0011Q\fC?\u0011!\t9'!\u0003A\u0002\u0005%\u0014\u0001B2paf$B\u0001\"\u0017\u0005\u0004\"Q!QVA\u0006!\u0003\u0005\rAa,\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011A\u0011\u0012\u0016\u0005\u0005_#Yi\u000b\u0002\u0005\u000eB!Aq\u0012CM\u001b\t!\tJ\u0003\u0003\u0005\u0014\u0012U\u0015!C;oG\",7m[3e\u0015\u0011!9*a\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0005\u001c\u0012E%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"\u0001\"\f\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011\u001bCT\u0011)!y'a\u0005\u0002\u0002\u0003\u0007\u0011\u0011N\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011AQ\u0016\t\u0007\t_#\t,!5\u000e\u0005\rm\u0015\u0002\u0002CZ\u00077\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011q\u0017C]\u0011)!y'a\u0006\u0002\u0002\u0003\u0007\u0011\u0011[\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0005.\u0011}\u0006B\u0003C8\u00033\t\t\u00111\u0001\u0002j\u00059\u0011J\\3yC\u000e$\b\u0003\u0002C\f\u0003;\u0019b!!\b\u0005H\n\u001d\u0005\u0003\u0003C\u000f\tG\u0011y\u000b\"\u0017\u0015\u0005\u0011\rG\u0003\u0002C-\t\u001bD\u0001B!,\u0002$\u0001\u0007!q\u0016\u000b\u0005\t#$\u0019\u000e\u0005\u0004\u0002T\u0011m\"q\u0016\u0005\u000b\t\u0003\n)#!AA\u0002\u0011e3#C5\u0002:\u0005uG\u0011\nC(\u0003\tq\u0007\u0005\u0006\u0003\u0005(\u0011m\u0007b\u0002B]Y\u0002\u0007\u00111\u000f\u000b\u0005\u0003;\"y\u000eC\u0004\u0002h5\u0004\r!!\u001b\u0015\t\u0011\u001dB1\u001d\u0005\n\u0005ss\u0007\u0013!a\u0001\u0003g*\"\u0001b:+\t\u0005MD1\u0012\u000b\u0005\u0003#$Y\u000fC\u0005\u0005pI\f\t\u00111\u0001\u0002jQ!\u0011q\u0017Cx\u0011%!y\u0007^A\u0001\u0002\u0004\t\t\u000e\u0006\u0003\u0005.\u0011M\b\"\u0003C8k\u0006\u0005\t\u0019AA5\u0003\u0011\u0011V-\u00197"
)
public interface Real extends ScalaNumericConversions {
   static Real atanDrx(final Real x) {
      return Real$.MODULE$.atanDrx(x);
   }

   static Real atanDr(final Real x) {
      return Real$.MODULE$.atanDr(x);
   }

   static Real cosDr(final Real x) {
      return Real$.MODULE$.cosDr(x);
   }

   static Real sinDr(final Real x) {
      return Real$.MODULE$.sinDr(x);
   }

   static Real logDrx(final Real x) {
      return Real$.MODULE$.logDrx(x);
   }

   static Real logDr(final Real x) {
      return Real$.MODULE$.logDr(x);
   }

   static Real expDr(final Real x) {
      return Real$.MODULE$.expDr(x);
   }

   /** @deprecated */
   static SafeLong accumulate(final SafeLong total, final Stream xs, final Stream cs) {
      return Real$.MODULE$.accumulate(total, xs, cs);
   }

   static SafeLong accumulate(final SafeLong total, final LazyList xs, final LazyList cs) {
      return Real$.MODULE$.accumulate(total, xs, cs);
   }

   static Real sqrt1By2() {
      return Real$.MODULE$.sqrt1By2();
   }

   static Real log2() {
      return Real$.MODULE$.log2();
   }

   static Real piBy4() {
      return Real$.MODULE$.piBy4();
   }

   static Real piBy2() {
      return Real$.MODULE$.piBy2();
   }

   static Real mul2n(final Real x, final int n) {
      return Real$.MODULE$.mul2n(x, n);
   }

   static Real div2n(final Real x, final int n) {
      return Real$.MODULE$.div2n(x, n);
   }

   static SafeLong roundUp(final Rational r) {
      return Real$.MODULE$.roundUp(r);
   }

   static int sizeInBase(final SafeLong n, final int base) {
      return Real$.MODULE$.sizeInBase(n, base);
   }

   static int digitsToBits(final int n) {
      return Real$.MODULE$.digitsToBits(n);
   }

   static int bits() {
      return Real$.MODULE$.bits();
   }

   static int digits() {
      return Real$.MODULE$.digits();
   }

   static Real atanh(final Real x) {
      return Real$.MODULE$.atanh(x);
   }

   static Real acosh(final Real x) {
      return Real$.MODULE$.acosh(x);
   }

   static Real asinh(final Real x) {
      return Real$.MODULE$.asinh(x);
   }

   static Real tanh(final Real x) {
      return Real$.MODULE$.tanh(x);
   }

   static Real cosh(final Real x) {
      return Real$.MODULE$.cosh(x);
   }

   static Real sinh(final Real x) {
      return Real$.MODULE$.sinh(x);
   }

   static Real acos(final Real x) {
      return Real$.MODULE$.acos(x);
   }

   static Real asin(final Real x) {
      return Real$.MODULE$.asin(x);
   }

   static Real atan2(final Real y, final Real x) {
      return Real$.MODULE$.atan2(y, x);
   }

   static Real atan(final Real x) {
      return Real$.MODULE$.atan(x);
   }

   static Real tan(final Real x) {
      return Real$.MODULE$.tan(x);
   }

   static Real cos(final Real x) {
      return Real$.MODULE$.cos(x);
   }

   static Real sin(final Real x) {
      return Real$.MODULE$.sin(x);
   }

   static Real exp(final Real x) {
      return Real$.MODULE$.exp(x);
   }

   static Real log(final Real x) {
      return Real$.MODULE$.log(x);
   }

   static Real phi() {
      return Real$.MODULE$.phi();
   }

   static Real e() {
      return Real$.MODULE$.e();
   }

   static Real pi() {
      return Real$.MODULE$.pi();
   }

   static Real four() {
      return Real$.MODULE$.four();
   }

   static Real two() {
      return Real$.MODULE$.two();
   }

   static Real one() {
      return Real$.MODULE$.one();
   }

   static Real zero() {
      return Real$.MODULE$.zero();
   }

   static NumberTag RealTag() {
      return Real$.MODULE$.RealTag();
   }

   static Fractional algebra() {
      return Real$.MODULE$.algebra();
   }

   SafeLong apply(final int p);

   default Rational toRational(final int p) {
      Rational var2;
      if (this instanceof Exact) {
         Exact var4 = (Exact)this;
         Rational n = var4.n();
         var2 = n;
      } else {
         var2 = Rational$.MODULE$.apply(this.apply(p), SafeLong$.MODULE$.two().pow(p));
      }

      return var2;
   }

   default Rational toRational() {
      return this.toRational(Real$.MODULE$.bits());
   }

   default double doubleValue() {
      return this.toRational().toDouble();
   }

   default float floatValue() {
      return this.toRational().toFloat();
   }

   default int intValue() {
      return this.toRational().toInt();
   }

   default long longValue() {
      return this.toRational().toLong();
   }

   default Object underlying() {
      return this;
   }

   default boolean isValidChar() {
      Rational r = this.toRational();
      return r.isWhole() && r.isValidChar();
   }

   default boolean isValidByte() {
      Rational r = this.toRational();
      return r.isWhole() && r.isValidByte();
   }

   default boolean isValidShort() {
      Rational r = this.toRational();
      return r.isWhole() && r.isValidShort();
   }

   default boolean isValidInt() {
      Rational r = this.toRational();
      return r.isWhole() && r.isValidInt();
   }

   default boolean isValidLong() {
      Rational r = this.toRational();
      return r.isWhole() && r.isValidLong();
   }

   default int hashCode() {
      return this.toRational().hashCode();
   }

   default boolean equals(final Object y) {
      boolean var2;
      if (y instanceof Real) {
         Real var4 = (Real)y;
         var2 = this.$eq$eq$eq(var4);
      } else {
         var2 = this.toRational().equals(y);
      }

      return var2;
   }

   default boolean $eq$eq$eq(final Real y) {
      return this.compare(y) == 0;
   }

   default boolean $eq$bang$eq(final Real y) {
      return !this.$eq$eq$eq(y);
   }

   default int compare(final Real y) {
      Tuple2 var3 = new Tuple2(this, y);
      int var2;
      if (var3 != null) {
         Real var4 = (Real)var3._1();
         Real var5 = (Real)var3._2();
         if (var4 instanceof Exact) {
            Exact var6 = (Exact)var4;
            Rational nx = var6.n();
            if (var5 instanceof Exact) {
               Exact var8 = (Exact)var5;
               Rational ny = var8.n();
               var2 = nx.compare(ny);
               return var2;
            }
         }
      }

      var2 = this.$minus(y).signum();
      return var2;
   }

   default Real min(final Real y) {
      Tuple2 var3 = new Tuple2(this, y);
      Object var2;
      if (var3 != null) {
         Real var4 = (Real)var3._1();
         Real var5 = (Real)var3._2();
         if (var4 instanceof Exact) {
            Exact var6 = (Exact)var4;
            Rational nx = var6.n();
            if (var5 instanceof Exact) {
               Exact var8 = (Exact)var5;
               Rational ny = var8.n();
               var2 = new Exact(nx.min(ny));
               return (Real)var2;
            }
         }
      }

      var2 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$min$1(this, y, BoxesRunTime.unboxToInt(p))));
      return (Real)var2;
   }

   default Real max(final Real y) {
      Tuple2 var3 = new Tuple2(this, y);
      Object var2;
      if (var3 != null) {
         Real var4 = (Real)var3._1();
         Real var5 = (Real)var3._2();
         if (var4 instanceof Exact) {
            Exact var6 = (Exact)var4;
            Rational nx = var6.n();
            if (var5 instanceof Exact) {
               Exact var8 = (Exact)var5;
               Rational ny = var8.n();
               var2 = new Exact(nx.max(ny));
               return (Real)var2;
            }
         }
      }

      var2 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$max$1(this, y, BoxesRunTime.unboxToInt(p))));
      return (Real)var2;
   }

   default Real abs() {
      Object var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = new Exact(n.abs());
      } else {
         var1 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$abs$1(this, BoxesRunTime.unboxToInt(p))));
      }

      return (Real)var1;
   }

   default int signum() {
      int var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = n.signum();
      } else {
         var1 = this.apply(Real$.MODULE$.bits()).signum();
      }

      return var1;
   }

   default Real unary_$minus() {
      Object var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = new Exact(n.unary_$minus());
      } else {
         var1 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$unary_$minus$1(this, BoxesRunTime.unboxToInt(p))));
      }

      return (Real)var1;
   }

   default Real reciprocal() {
      Object var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = new Exact(n.reciprocal());
      } else {
         var1 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$reciprocal$1(this, BoxesRunTime.unboxToInt(p))));
      }

      return (Real)var1;
   }

   default Real $plus(final Real y) {
      Tuple2 var3 = new Tuple2(this, y);
      Object var2;
      if (var3 != null) {
         Real var4 = (Real)var3._1();
         Real var5 = (Real)var3._2();
         if (var4 instanceof Exact) {
            Exact var6 = (Exact)var4;
            Rational nx = var6.n();
            if (var5 instanceof Exact) {
               Exact var8 = (Exact)var5;
               Rational ny = var8.n();
               var2 = new Exact(nx.$plus(ny));
               return (Real)var2;
            }
         }
      }

      if (var3 != null) {
         Real var10 = (Real)var3._1();
         if (var10 instanceof Exact) {
            Exact var11 = (Exact)var10;
            Rational var12 = var11.n();
            if (BoxesRunTime.equalsNumNum(Rational$.MODULE$.zero(), var12)) {
               var2 = y;
               return (Real)var2;
            }
         }
      }

      if (var3 != null) {
         Real var13 = (Real)var3._2();
         if (var13 instanceof Exact) {
            Exact var14 = (Exact)var13;
            Rational var15 = var14.n();
            if (BoxesRunTime.equalsNumNum(Rational$.MODULE$.zero(), var15)) {
               var2 = this;
               return (Real)var2;
            }
         }
      }

      var2 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$$plus$1(this, y, BoxesRunTime.unboxToInt(p))));
      return (Real)var2;
   }

   default Real $minus(final Real y) {
      return this.$plus(y.unary_$minus());
   }

   default Real $times(final Real y) {
      Tuple2 var3 = new Tuple2(this, y);
      Object var2;
      if (var3 != null) {
         Real var4 = (Real)var3._1();
         Real var5 = (Real)var3._2();
         if (var4 instanceof Exact) {
            Exact var6 = (Exact)var4;
            Rational nx = var6.n();
            if (var5 instanceof Exact) {
               Exact var8 = (Exact)var5;
               Rational ny = var8.n();
               var2 = new Exact(nx.$times(ny));
               return (Real)var2;
            }
         }
      }

      if (var3 != null) {
         Real var10 = (Real)var3._1();
         if (var10 instanceof Exact) {
            Exact var11 = (Exact)var10;
            Rational var12 = var11.n();
            if (BoxesRunTime.equalsNumNum(Rational$.MODULE$.zero(), var12)) {
               var2 = Real$.MODULE$.zero();
               return (Real)var2;
            }
         }
      }

      if (var3 != null) {
         Real var13 = (Real)var3._2();
         if (var13 instanceof Exact) {
            Exact var14 = (Exact)var13;
            Rational var15 = var14.n();
            if (BoxesRunTime.equalsNumNum(Rational$.MODULE$.zero(), var15)) {
               var2 = Real$.MODULE$.zero();
               return (Real)var2;
            }
         }
      }

      if (var3 != null) {
         Real var16 = (Real)var3._1();
         if (var16 instanceof Exact) {
            Exact var17 = (Exact)var16;
            Rational var18 = var17.n();
            if (BoxesRunTime.equalsNumNum(Rational$.MODULE$.one(), var18)) {
               var2 = y;
               return (Real)var2;
            }
         }
      }

      if (var3 != null) {
         Real var19 = (Real)var3._2();
         if (var19 instanceof Exact) {
            Exact var20 = (Exact)var19;
            Rational var21 = var20.n();
            if (BoxesRunTime.equalsNumNum(Rational$.MODULE$.one(), var21)) {
               var2 = this;
               return (Real)var2;
            }
         }
      }

      var2 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$$times$1(this, y, BoxesRunTime.unboxToInt(p))));
      return (Real)var2;
   }

   default Real $times$times(final int k) {
      return this.pow(k);
   }

   default Real pow(final int k) {
      Object var2;
      if (this instanceof Exact) {
         Exact var4 = (Exact)this;
         Rational n = var4.n();
         var2 = new Exact(n.pow(k));
      } else {
         var2 = k < 0 ? this.reciprocal().pow(-k) : (k == 0 ? Real$.MODULE$.one() : (k == 1 ? this : this.loop$1(this, k - 1, this)));
      }

      return (Real)var2;
   }

   default Real $div(final Real y) {
      return this.$times(y.reciprocal());
   }

   default Real tmod(final Real y) {
      Tuple2 var3 = new Tuple2(this, y);
      Object var2;
      if (var3 != null) {
         Real var4 = (Real)var3._1();
         Real var5 = (Real)var3._2();
         if (var4 instanceof Exact) {
            Exact var6 = (Exact)var4;
            Rational nx = var6.n();
            if (var5 instanceof Exact) {
               Exact var8 = (Exact)var5;
               Rational ny = var8.n();
               var2 = new Exact(nx.tmod(ny));
               return (Real)var2;
            }
         }
      }

      var2 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$tmod$1(this, y, BoxesRunTime.unboxToInt(p))));
      return (Real)var2;
   }

   default Real tquot(final Real y) {
      Tuple2 var3 = new Tuple2(this, y);
      Object var2;
      if (var3 != null) {
         Real var4 = (Real)var3._1();
         Real var5 = (Real)var3._2();
         if (var4 instanceof Exact) {
            Exact var6 = (Exact)var4;
            Rational nx = var6.n();
            if (var5 instanceof Exact) {
               Exact var8 = (Exact)var5;
               Rational ny = var8.n();
               var2 = new Exact(nx.tquot(ny));
               return (Real)var2;
            }
         }
      }

      var2 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$tquot$1(this, y, BoxesRunTime.unboxToInt(p))));
      return (Real)var2;
   }

   default Real ceil() {
      Object var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = new Exact(n.ceil());
      } else {
         var1 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$ceil$1(this, BoxesRunTime.unboxToInt(p))));
      }

      return (Real)var1;
   }

   default Real floor() {
      Object var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = new Exact(n.floor());
      } else {
         var1 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$floor$1(this, BoxesRunTime.unboxToInt(p))));
      }

      return (Real)var1;
   }

   default Real round() {
      Object var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = new Exact(n.round());
      } else {
         var1 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$round$1(this, BoxesRunTime.unboxToInt(p))));
      }

      return (Real)var1;
   }

   default boolean isWhole() {
      boolean var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = n.isWhole();
      } else {
         SafeLong n = this.apply(Real$.MODULE$.bits());
         SafeLong t = SafeLong$.MODULE$.two().pow(Real$.MODULE$.bits());
         var1 = BoxesRunTime.equalsNumObject(n.$percent(t), BoxesRunTime.boxToInteger(0));
      }

      return var1;
   }

   default Real sqrt() {
      return Real$.MODULE$.apply((Function1)((p) -> $anonfun$sqrt$1(this, BoxesRunTime.unboxToInt(p))));
   }

   default Real nroot(final int k) {
      return k >= 0 ? Real$.MODULE$.apply((Function1)((p) -> $anonfun$nroot$1(this, k, BoxesRunTime.unboxToInt(p)))) : Real$.MODULE$.apply((Function1)((p) -> $anonfun$nroot$2(this, k, BoxesRunTime.unboxToInt(p))));
   }

   default Real fpow(final Rational r) {
      return Real$.MODULE$.apply((Function1)((p) -> $anonfun$fpow$1(this, r, BoxesRunTime.unboxToInt(p))));
   }

   default Real fpow(final Real y) {
      Real var2;
      if (y instanceof Exact) {
         Exact var4 = (Exact)y;
         Rational n = var4.n();
         var2 = this.fpow(n);
      } else {
         var2 = Real$.MODULE$.apply((Function1)((p) -> $anonfun$fpow$2(this, y, BoxesRunTime.unboxToInt(p))));
      }

      return var2;
   }

   default String toString() {
      String var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = n.toString();
      } else {
         var1 = this.getString(Real$.MODULE$.digits());
      }

      return var1;
   }

   default String repr() {
      String var1;
      if (this instanceof Exact) {
         Exact var3 = (Exact)this;
         Rational n = var3.n();
         var1 = (new StringBuilder(7)).append("Exact(").append(n.toString()).append(")").toString();
      } else {
         var1 = (new StringBuilder(9)).append("Inexact(").append(this.toRational()).append(")").toString();
      }

      return var1;
   }

   default String getString(final int d) {
      int b = Real$.MODULE$.digitsToBits(d);
      Rational r = Rational$.MODULE$.apply(this.apply(b).$times(SafeLong$.MODULE$.ten().pow(d)), SafeLong$.MODULE$.two().pow(b));
      SafeLong m = Real$.MODULE$.roundUp(r);
      int var8 = m.signum();
      Tuple2 var10000;
      switch (var8) {
         case -1:
            var10000 = new Tuple2("-", m.abs().toString());
            break;
         case 0:
            var10000 = new Tuple2("", "0");
            break;
         case 1:
            var10000 = new Tuple2("", m.toString());
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(var8));
      }

      Tuple2 var7 = var10000;
      if (var7 != null) {
         String sign = (String)var7._1();
         String str = (String)var7._2();
         Tuple2 var2 = new Tuple2(sign, str);
         String sign = (String)var2._1();
         String str = (String)var2._2();
         int i = str.length() - d;
         String s = i > 0 ? (new StringBuilder(1)).append(sign).append(str.substring(0, i)).append(".").append(str.substring(i)).toString() : (new StringBuilder(2)).append(sign).append("0.").append(.MODULE$.$times$extension(scala.Predef..MODULE$.augmentString("0"), -i)).append(str).toString();
         return s.replaceAll("0+$", "").replaceAll("\\.$", "");
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   static SafeLong $anonfun$min$1(final Real $this, final Real y$1, final int p) {
      return $this.apply(p).min(y$1.apply(p));
   }

   // $FF: synthetic method
   static SafeLong $anonfun$max$1(final Real $this, final Real y$2, final int p) {
      return $this.apply(p).max(y$2.apply(p));
   }

   // $FF: synthetic method
   static SafeLong $anonfun$abs$1(final Real $this, final int p) {
      return $this.apply(p).abs();
   }

   // $FF: synthetic method
   static SafeLong $anonfun$unary_$minus$1(final Real $this, final int p) {
      return $this.apply(p).unary_$minus();
   }

   private int findNonzero$1(final int i) {
      while(!SafeLong$.MODULE$.three().$less$eq(this.apply(i).abs())) {
         ++i;
      }

      return i;
   }

   // $FF: synthetic method
   static SafeLong $anonfun$reciprocal$1(final Real $this, final int p) {
      int s = $this.findNonzero$1(0);
      return Real$.MODULE$.roundUp(Rational$.MODULE$.apply(SafeLong$.MODULE$.two().pow(2 * p + 2 * s + 2), $this.apply(p + 2 * s + 2)));
   }

   // $FF: synthetic method
   static SafeLong $anonfun$$plus$1(final Real $this, final Real y$3, final int p) {
      return Real$.MODULE$.roundUp(Rational$.MODULE$.apply($this.apply(p + 2).$plus(y$3.apply(p + 2)), SafeLong$.MODULE$.apply(4)));
   }

   // $FF: synthetic method
   static SafeLong $anonfun$$times$1(final Real $this, final Real y$4, final int p) {
      SafeLong x0 = $this.apply(0).abs().$plus(2L);
      SafeLong y0 = y$4.apply(0).abs().$plus(2L);
      int sx = Real$.MODULE$.sizeInBase(x0, 2) + 3;
      int sy = Real$.MODULE$.sizeInBase(y0, 2) + 3;
      return Real$.MODULE$.roundUp(Rational$.MODULE$.apply($this.apply(p + sy).$times(y$4.apply(p + sx)), SafeLong$.MODULE$.two().pow(p + sx + sy)));
   }

   private Real loop$1(final Real b, final int k, final Real extra) {
      while(k != 1) {
         Real var10000 = b.$times(b);
         int var10001 = k >>> 1;
         extra = (k & 1) == 1 ? b.$times(extra) : extra;
         k = var10001;
         b = var10000;
      }

      return b.$times(extra);
   }

   // $FF: synthetic method
   static SafeLong $anonfun$tmod$1(final Real $this, final Real y$5, final int p) {
      Real d = $this.$div(y$5);
      SafeLong s = d.apply(2);
      Real d2 = s.$greater$eq(SafeLong$.MODULE$.apply(0)) ? d.floor() : d.ceil();
      return $this.$minus(d2.$times(y$5)).apply(p);
   }

   // $FF: synthetic method
   static SafeLong $anonfun$tquot$1(final Real $this, final Real y$6, final int p) {
      Real d = $this.$div(y$6);
      SafeLong s = d.apply(2);
      Real d2 = s.$greater$eq(SafeLong$.MODULE$.apply(0)) ? d.floor() : d.ceil();
      return d2.apply(p);
   }

   // $FF: synthetic method
   static SafeLong $anonfun$ceil$1(final Real $this, final int p) {
      SafeLong n = $this.apply(p);
      SafeLong t = SafeLong$.MODULE$.two().pow(p);
      SafeLong m = n.$percent(t);
      return BoxesRunTime.equalsNumObject(m, BoxesRunTime.boxToInteger(0)) ? n : (n.signum() >= 0 ? n.$plus(t).$minus(m) : n.$minus(m));
   }

   // $FF: synthetic method
   static SafeLong $anonfun$floor$1(final Real $this, final int p) {
      SafeLong n = $this.apply(p);
      SafeLong t = SafeLong$.MODULE$.two().pow(p);
      SafeLong m = n.$percent(t);
      return n.signum() >= 0 ? n.$minus(m) : n.$minus(t).$minus(m);
   }

   // $FF: synthetic method
   static SafeLong $anonfun$round$1(final Real $this, final int p) {
      SafeLong n = $this.apply(p);
      SafeLong t = SafeLong$.MODULE$.two().pow(p);
      SafeLong h = t.$div(2L);
      SafeLong m = n.$percent(t);
      return m.$less(h) ? n.$minus(m) : n.$minus(m).$plus(t);
   }

   // $FF: synthetic method
   static SafeLong $anonfun$sqrt$1(final Real $this, final int p) {
      return (SafeLong)SafeLong$.MODULE$.SafeLongAlgebra().sqrt($this.apply(p * 2));
   }

   // $FF: synthetic method
   static SafeLong $anonfun$nroot$1(final Real $this, final int k$1, final int p) {
      return SafeLong$.MODULE$.SafeLongAlgebra().nroot($this.apply(p * k$1), k$1);
   }

   // $FF: synthetic method
   static SafeLong $anonfun$nroot$2(final Real $this, final int k$1, final int p) {
      return $this.reciprocal().nroot(package$.MODULE$.abs(k$1)).apply(p);
   }

   // $FF: synthetic method
   static SafeLong $anonfun$fpow$1(final Real $this, final Rational r$1, final int p) {
      Rational r2 = r$1.limitToInt();
      SafeLong n = r2.numerator();
      SafeLong d = r2.denominator();
      return $this.pow(n.toInt()).nroot(d.toInt()).apply(p);
   }

   // $FF: synthetic method
   static SafeLong $anonfun$fpow$2(final Real $this, final Real y$7, final int p) {
      return $this.fpow(Rational$.MODULE$.apply(y$7.apply(p), SafeLong$.MODULE$.two().pow(p))).apply(p);
   }

   static void $init$(final Real $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Exact extends ScalaNumber implements Real, Product {
      private final Rational n;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Rational toRational(final int p) {
         return Real.super.toRational(p);
      }

      public Rational toRational() {
         return Real.super.toRational();
      }

      public double doubleValue() {
         return Real.super.doubleValue();
      }

      public float floatValue() {
         return Real.super.floatValue();
      }

      public int intValue() {
         return Real.super.intValue();
      }

      public long longValue() {
         return Real.super.longValue();
      }

      public Object underlying() {
         return Real.super.underlying();
      }

      public boolean isValidChar() {
         return Real.super.isValidChar();
      }

      public boolean isValidByte() {
         return Real.super.isValidByte();
      }

      public boolean isValidShort() {
         return Real.super.isValidShort();
      }

      public boolean isValidInt() {
         return Real.super.isValidInt();
      }

      public boolean isValidLong() {
         return Real.super.isValidLong();
      }

      public int hashCode() {
         return Real.super.hashCode();
      }

      public boolean equals(final Object y) {
         return Real.super.equals(y);
      }

      public boolean $eq$eq$eq(final Real y) {
         return Real.super.$eq$eq$eq(y);
      }

      public boolean $eq$bang$eq(final Real y) {
         return Real.super.$eq$bang$eq(y);
      }

      public int compare(final Real y) {
         return Real.super.compare(y);
      }

      public Real min(final Real y) {
         return Real.super.min(y);
      }

      public Real max(final Real y) {
         return Real.super.max(y);
      }

      public Real abs() {
         return Real.super.abs();
      }

      public int signum() {
         return Real.super.signum();
      }

      public Real unary_$minus() {
         return Real.super.unary_$minus();
      }

      public Real reciprocal() {
         return Real.super.reciprocal();
      }

      public Real $plus(final Real y) {
         return Real.super.$plus(y);
      }

      public Real $minus(final Real y) {
         return Real.super.$minus(y);
      }

      public Real $times(final Real y) {
         return Real.super.$times(y);
      }

      public Real $times$times(final int k) {
         return Real.super.$times$times(k);
      }

      public Real pow(final int k) {
         return Real.super.pow(k);
      }

      public Real $div(final Real y) {
         return Real.super.$div(y);
      }

      public Real tmod(final Real y) {
         return Real.super.tmod(y);
      }

      public Real tquot(final Real y) {
         return Real.super.tquot(y);
      }

      public Real ceil() {
         return Real.super.ceil();
      }

      public Real floor() {
         return Real.super.floor();
      }

      public Real round() {
         return Real.super.round();
      }

      public boolean isWhole() {
         return Real.super.isWhole();
      }

      public Real sqrt() {
         return Real.super.sqrt();
      }

      public Real nroot(final int k) {
         return Real.super.nroot(k);
      }

      public Real fpow(final Rational r) {
         return Real.super.fpow(r);
      }

      public Real fpow(final Real y) {
         return Real.super.fpow(y);
      }

      public String toString() {
         return Real.super.toString();
      }

      public String repr() {
         return Real.super.repr();
      }

      public String getString(final int d) {
         return Real.super.getString(d);
      }

      public char toChar() {
         return ScalaNumericAnyConversions.toChar$(this);
      }

      public byte toByte() {
         return ScalaNumericAnyConversions.toByte$(this);
      }

      public short toShort() {
         return ScalaNumericAnyConversions.toShort$(this);
      }

      public int toInt() {
         return ScalaNumericAnyConversions.toInt$(this);
      }

      public long toLong() {
         return ScalaNumericAnyConversions.toLong$(this);
      }

      public float toFloat() {
         return ScalaNumericAnyConversions.toFloat$(this);
      }

      public double toDouble() {
         return ScalaNumericAnyConversions.toDouble$(this);
      }

      public int unifiedPrimitiveHashcode() {
         return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
      }

      public boolean unifiedPrimitiveEquals(final Object x) {
         return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
      }

      public Rational n() {
         return this.n;
      }

      public SafeLong apply(final int p) {
         return Real$.MODULE$.roundUp(Rational$.MODULE$.apply(2).pow(p).$times(this.n()));
      }

      public Exact copy(final Rational n) {
         return new Exact(n);
      }

      public Rational copy$default$1() {
         return this.n();
      }

      public String productPrefix() {
         return "Exact";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.n();
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
         return x$1 instanceof Exact;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Exact(final Rational n) {
         this.n = n;
         ScalaNumericAnyConversions.$init$(this);
         Real.$init$(this);
         Product.$init$(this);
      }
   }

   public static class Exact$ extends AbstractFunction1 implements Serializable {
      public static final Exact$ MODULE$ = new Exact$();

      public final String toString() {
         return "Exact";
      }

      public Exact apply(final Rational n) {
         return new Exact(n);
      }

      public Option unapply(final Exact x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.n()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Exact$.class);
      }
   }

   public static class Inexact extends ScalaNumber implements Real, Product {
      private final Function1 f;
      private volatile Option memo;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Rational toRational(final int p) {
         return Real.super.toRational(p);
      }

      public Rational toRational() {
         return Real.super.toRational();
      }

      public double doubleValue() {
         return Real.super.doubleValue();
      }

      public float floatValue() {
         return Real.super.floatValue();
      }

      public int intValue() {
         return Real.super.intValue();
      }

      public long longValue() {
         return Real.super.longValue();
      }

      public Object underlying() {
         return Real.super.underlying();
      }

      public boolean isValidChar() {
         return Real.super.isValidChar();
      }

      public boolean isValidByte() {
         return Real.super.isValidByte();
      }

      public boolean isValidShort() {
         return Real.super.isValidShort();
      }

      public boolean isValidInt() {
         return Real.super.isValidInt();
      }

      public boolean isValidLong() {
         return Real.super.isValidLong();
      }

      public int hashCode() {
         return Real.super.hashCode();
      }

      public boolean equals(final Object y) {
         return Real.super.equals(y);
      }

      public boolean $eq$eq$eq(final Real y) {
         return Real.super.$eq$eq$eq(y);
      }

      public boolean $eq$bang$eq(final Real y) {
         return Real.super.$eq$bang$eq(y);
      }

      public int compare(final Real y) {
         return Real.super.compare(y);
      }

      public Real min(final Real y) {
         return Real.super.min(y);
      }

      public Real max(final Real y) {
         return Real.super.max(y);
      }

      public Real abs() {
         return Real.super.abs();
      }

      public int signum() {
         return Real.super.signum();
      }

      public Real unary_$minus() {
         return Real.super.unary_$minus();
      }

      public Real reciprocal() {
         return Real.super.reciprocal();
      }

      public Real $plus(final Real y) {
         return Real.super.$plus(y);
      }

      public Real $minus(final Real y) {
         return Real.super.$minus(y);
      }

      public Real $times(final Real y) {
         return Real.super.$times(y);
      }

      public Real $times$times(final int k) {
         return Real.super.$times$times(k);
      }

      public Real pow(final int k) {
         return Real.super.pow(k);
      }

      public Real $div(final Real y) {
         return Real.super.$div(y);
      }

      public Real tmod(final Real y) {
         return Real.super.tmod(y);
      }

      public Real tquot(final Real y) {
         return Real.super.tquot(y);
      }

      public Real ceil() {
         return Real.super.ceil();
      }

      public Real floor() {
         return Real.super.floor();
      }

      public Real round() {
         return Real.super.round();
      }

      public boolean isWhole() {
         return Real.super.isWhole();
      }

      public Real sqrt() {
         return Real.super.sqrt();
      }

      public Real nroot(final int k) {
         return Real.super.nroot(k);
      }

      public Real fpow(final Rational r) {
         return Real.super.fpow(r);
      }

      public Real fpow(final Real y) {
         return Real.super.fpow(y);
      }

      public String toString() {
         return Real.super.toString();
      }

      public String repr() {
         return Real.super.repr();
      }

      public String getString(final int d) {
         return Real.super.getString(d);
      }

      public char toChar() {
         return ScalaNumericAnyConversions.toChar$(this);
      }

      public byte toByte() {
         return ScalaNumericAnyConversions.toByte$(this);
      }

      public short toShort() {
         return ScalaNumericAnyConversions.toShort$(this);
      }

      public int toInt() {
         return ScalaNumericAnyConversions.toInt$(this);
      }

      public long toLong() {
         return ScalaNumericAnyConversions.toLong$(this);
      }

      public float toFloat() {
         return ScalaNumericAnyConversions.toFloat$(this);
      }

      public double toDouble() {
         return ScalaNumericAnyConversions.toDouble$(this);
      }

      public int unifiedPrimitiveHashcode() {
         return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
      }

      public boolean unifiedPrimitiveEquals(final Object x) {
         return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
      }

      public Function1 f() {
         return this.f;
      }

      public Option memo() {
         return this.memo;
      }

      public void memo_$eq(final Option x$1) {
         this.memo = x$1;
      }

      public SafeLong apply(final int p) {
         Option var3 = this.memo();
         SafeLong var2;
         if (var3 instanceof Some) {
            Some var4 = (Some)var3;
            Tuple2 var5 = (Tuple2)var4.value();
            if (var5 != null) {
               int bits = var5._1$mcI$sp();
               SafeLong value = (SafeLong)var5._2();
               if (bits >= p) {
                  var2 = Real$.MODULE$.roundUp(Rational$.MODULE$.apply(value, SafeLong$.MODULE$.apply(2).pow(bits - p)));
                  return var2;
               }
            }
         }

         SafeLong result = (SafeLong)this.f().apply(BoxesRunTime.boxToInteger(p));
         this.memo_$eq(new Some(new Tuple2(BoxesRunTime.boxToInteger(p), result)));
         var2 = result;
         return var2;
      }

      public Inexact copy(final Function1 f) {
         return new Inexact(f);
      }

      public Function1 copy$default$1() {
         return this.f();
      }

      public String productPrefix() {
         return "Inexact";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.f();
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
         return x$1 instanceof Inexact;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "f";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Inexact(final Function1 f) {
         this.f = f;
         ScalaNumericAnyConversions.$init$(this);
         Real.$init$(this);
         Product.$init$(this);
         this.memo = scala.None..MODULE$;
      }
   }

   public static class Inexact$ extends AbstractFunction1 implements Serializable {
      public static final Inexact$ MODULE$ = new Inexact$();

      public final String toString() {
         return "Inexact";
      }

      public Inexact apply(final Function1 f) {
         return new Inexact(f);
      }

      public Option unapply(final Inexact x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.f()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Inexact$.class);
      }
   }
}
