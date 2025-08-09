package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.MapFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing$;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019mfACA+\u0003/\u0002\n1!\u0001\u0002f!9\u00111\u0017\u0001\u0005\u0002\u0005U\u0006bBA_\u0001\u0011\u0005\u0013q\u0018\u0005\b\u0003\u000f\u0004AQIAe\u0011\u001d\t9\u000f\u0001C\u0001\u0003SDqA!\u0001\u0001\t\u0003\u0011\u0019a\u0002\u0005\u0003\u0010\u0005]\u0003\u0012\u0001B\t\r!\t)&a\u0016\t\u0002\tM\u0001b\u0002B\u000b\u000f\u0011\u0005!q\u0003\u0004\u0007\u000539\u0001Aa\u0007\t\u0015\t\u0005\u0013B!b\u0001\n\u0003\u0011\u0019\u0005\u0003\u0006\u0003H%\u0011\t\u0011)A\u0005\u0005\u000bB!B!\u0013\n\u0005\u000b\u0007I\u0011\u0001B&\u0011)\u0011y%\u0003B\u0001B\u0003%!Q\n\u0005\b\u0005+IA\u0011\u0001B)\u0011\u001d\u00119&\u0003C\u0001\u00053BqA!\u001a\n\t\u0003\u00129\u0007C\u0004\u0003l%!\tE!\u001c\t\u000f\tU\u0014\u0002\"\u0001\u0003x!9!\u0011Q\u0005\u0005B\t\r\u0005bBA_\u0013\u0011\u0005\u0013q\u0018\u0005\b\u0005\u0017KA\u0011\tBG\u0011\u001d\u0011)+\u0003C\u0001\u0005OCqAa+\n\t\u0003\u0011i\u000bC\u0004\u0003>&!\tEa0\t\u000f\t\u0005\u0017\u0002\"\u0015\u0003D\"9!q\\\u0005\u0005R\t\u0005\bb\u0002B_\u000f\u0011\u0005!1 \u0005\b\u0007\u00139A\u0011AB\u0006\u0011\u001d\u0019\tc\u0002C\u0001\u0007G9qa!\u000e\b\u0011\u0013\u00199DB\u0004\u0004:\u001dAIaa\u000f\t\u000f\tUq\u0004\"\u0001\u0004@!91\u0011I\u0010\u0005B\r\r\u0003bBB&?\u0011\u000531\t\u0005\b\u0005\u0003{B\u0011\tBB\u0011\u001d\u0019ie\bC!\u0007\u001fBqaa\u0015 \t\u0003\u001a)\u0006C\u0004\u0003X}!\ta!\u0017\t\u000f\r}s\u0004\"\u0011\u0004b!9!QO\u0010\u0005\u0002\rM\u0004bBB=?\u0011\u000531\u0010\u0005\b\u0007\u007fzB\u0011IBA\u0011\u001d\u0011Yk\bC\u0001\u0007\u000bCqA!* \t\u0003\u0019\u0019\nC\u0004\u0003\f~!\te!'\t\u0013\r-v$!A\u0005\n\r5fABBb\u000f\t\u0019)\r\u0003\u0006\u0004^>\u0012\t\u0011)A\u0005\u0007\u0017D!ba80\u0005\u0003\u0005\u000b\u0011BBh\u0011\u001d\u0011)b\fC\u0001\u0007CDqa!\u00110\t\u0003\u001a\u0019\u0005C\u0004\u0004L=\"\tea\u0011\t\u000f\t\u0005u\u0006\"\u0011\u0003\u0004\"91QJ\u0018\u0005B\r%\bbBB*_\u0011\u00053Q\u001e\u0005\b\u0005/zC\u0011ABy\u0011\u001d\u0019yf\fC!\u0007oDqA!\u001e0\t\u0003!9\u0001C\u0004\u0004z=\"\t\u0005b\u0003\t\u000f\r}t\u0006\"\u0011\u0005\u0010!9!1V\u0018\u0005\u0002\u0011M\u0001b\u0002BS_\u0011\u0005A\u0011\u0005\u0005\b\tKyC\u0011\tC\u0014\u0011\u001d!9d\fC!\tsAq\u0001\"\u00110\t\u0003\"\u0019\u0005C\u0005\u0005H=\"\t&a\u0017\u0005J!9A1K\u0018\u0005B\u0011U\u0003b\u0002C5_\u0011\u0005C1\u000e\u0004\u0007\t_:!\u0001\"\u001d\t\u0015\ruWI!A!\u0002\u0013!9\b\u0003\u0006\u0004`\u0016\u0013\t\u0011)A\u0005\twB!\u0002\"\"F\u0005\u0003\u0005\u000b\u0011\u0002C<\u0011)!9)\u0012B\u0001B\u0003%A1\u0010\u0005\b\u0005+)E\u0011\u0001CE\u0011\u001d\u0019\t%\u0012C!\u0007\u0007Bqaa\u0013F\t\u0003\u001a\u0019\u0005C\u0004\u0003\u0002\u0016#\tEa!\t\u000f\r5S\t\"\u0011\u0005\u0016\"911K#\u0005B\u0011e\u0005b\u0002B,\u000b\u0012\u0005AQ\u0014\u0005\b\u0007?*E\u0011\tCR\u0011\u001d\u0011)(\u0012C\u0001\tgCqa!\u001fF\t\u0003\"9\fC\u0004\u0004\u0000\u0015#\t\u0005b/\u0007\u000f\u0011}V)!\u0003\u0005B\"9!QC+\u0005\u0002\u0011E\u0007\u0002\u0003Cl+\u0002\u0006Ka!\u0012\t\u000f\u0011eW\u000b\"\u0011\u0003\u0004\"9A1\\+\u0005B\u0011u\u0007b\u0002Cp+\u0012\u0005C\u0011\u001d\u0005\b\tS,f\u0011\u0003Cv\u0011\u001d\u0011Y+\u0012C\u0001\toDqA!*F\t\u0003))\u0001C\u0004\u0005&\u0015#\t%\"\u0003\t\u000f\u0011]R\t\"\u0011\u0006\u0016!9A\u0011I#\u0005B\u0015m\u0001\"\u0003C$\u000b\u0012E\u00131LC\u0010\u0011\u001d!\u0019&\u0012C!\u000bKAq\u0001\"\u001bF\t\u0003\"YG\u0002\u0004\u00066\u001d\u0001Qq\u0007\u0005\u000b\u0007;$'\u0011!Q\u0001\n\u0015u\u0002BCBpI\n\u0005\t\u0015!\u0003\u0006B!QAQ\u00113\u0003\u0002\u0003\u0006I!\"\u0010\t\u0015\u0011\u001dEM!A!\u0002\u0013)\t\u0005\u0003\u0006\u0006L\u0011\u0014\t\u0011)A\u0005\u000b{A!\"\"\u0014e\u0005\u0003\u0005\u000b\u0011BC!\u0011\u001d\u0011)\u0002\u001aC\u0001\u000b\u001fBqa!\u0011e\t\u0003\u001a\u0019\u0005C\u0004\u0004L\u0011$\tea\u0011\t\u000f\t\u0005E\r\"\u0011\u0003\u0004\"91Q\n3\u0005B\u0015}\u0003bBB*I\u0012\u0005S1\r\u0005\b\u0005/\"G\u0011AC4\u0011\u001d\u0019y\u0006\u001aC!\u000b[BqA!\u001ee\t\u0003)i\bC\u0004\u0004z\u0011$\t%\"!\t\u000f\r}D\r\"\u0011\u0006\u0006\u001a9Q\u0011\u00123\u0002\n\u0015-\u0005b\u0002B\u000bm\u0012\u0005QQ\u0013\u0005\t\t/4\b\u0015)\u0003\u0004F!9A\u0011\u001c<\u0005B\t\r\u0005b\u0002Cnm\u0012\u0005S1\u0014\u0005\b\t?4H\u0011ICO\u0011\u001d!IO\u001eD\t\u000bGCqAa+e\t\u0003)Y\u000bC\u0004\u0003&\u0012$\t!\"/\t\u000f\u0011\u0015B\r\"\u0011\u0006>\"9Aq\u00073\u0005B\u0015%\u0007b\u0002C!I\u0012\u0005Sq\u001a\u0005\n\t\u000f\"G\u0011KA.\u000b'Dq\u0001b\u0015e\t\u0003*I\u000eC\u0004\u0005j\u0011$\t\u0005b\u001b\u0007\r\u0015%xAACv\u0011-\u0019i.a\u0003\u0003\u0002\u0003\u0006I!\"=\t\u0017\r}\u00171\u0002B\u0001B\u0003%QQ\u001f\u0005\f\t\u000b\u000bYA!A!\u0002\u0013)\t\u0010C\u0006\u0005\b\u0006-!\u0011!Q\u0001\n\u0015U\bbCC&\u0003\u0017\u0011\t\u0011)A\u0005\u000bcD1\"\"\u0014\u0002\f\t\u0005\t\u0015!\u0003\u0006v\"YQq`A\u0006\u0005\u0003\u0005\u000b\u0011BCy\u0011-1\t!a\u0003\u0003\u0002\u0003\u0006I!\">\t\u0011\tU\u00111\u0002C\u0001\r\u0007A\u0001b!\u0011\u0002\f\u0011\u000531\t\u0005\t\u0007\u0017\nY\u0001\"\u0011\u0004D!A!\u0011QA\u0006\t\u0003\u0012\u0019\t\u0003\u0005\u0004N\u0005-A\u0011\tD\f\u0011!\u0019\u0019&a\u0003\u0005B\u0019m\u0001\u0002\u0003B,\u0003\u0017!\tAb\b\t\u0011\r}\u00131\u0002C!\rKA\u0001B!\u001e\u0002\f\u0011\u0005aQ\u0007\u0005\t\u0007s\nY\u0001\"\u0011\u0007:!A1qPA\u0006\t\u00032iD\u0002\u0005\u0007B\u0005-\u0011\u0011\u0002D\"\u0011!\u0011)\"a\r\u0005\u0002\u00195\u0003\"\u0003Cl\u0003g\u0001\u000b\u0015BB#\u0011!!I.a\r\u0005B\t\r\u0005\u0002\u0003Cn\u0003g!\tEb\u0015\t\u0011\u0011}\u00171\u0007C!\r+B\u0001\u0002\";\u00024\u0019Ea1\f\u0005\t\u0005W\u000bY\u0001\"\u0001\u0007d!A!QUA\u0006\t\u00031\t\b\u0003\u0005\u0005&\u0005-A\u0011\tD;\u0011!!9$a\u0003\u0005B\u0019\u0005\u0005\u0002\u0003C!\u0003\u0017!\tEb\"\t\u0015\u0011\u001d\u00131\u0002C)\u000372Y\t\u0003\u0005\u0005T\u0005-A\u0011\tDI\u0011)1y*a\u0003\u0005\u0002\u0005]c\u0011\u0015\u0005\t\tS\nY\u0001\"\u0011\u0005l!I11V\u0004\u0002\u0002\u0013%1Q\u0016\u0002\u0004\u001b\u0006\u0004(\u0002BA-\u00037\n\u0011\"[7nkR\f'\r\\3\u000b\t\u0005u\u0013qL\u0001\u000bG>dG.Z2uS>t'BAA1\u0003\u0015\u00198-\u00197b\u0007\u0001)b!a\u001a\u0002\u0004\u0006]5c\u0003\u0001\u0002j\u0005E\u00141TAQ\u0003W\u0003B!a\u001b\u0002n5\u0011\u0011qL\u0005\u0005\u0003_\nyF\u0001\u0004B]f\u0014VM\u001a\t\u0007\u0003g\n)(!\u001f\u000e\u0005\u0005]\u0013\u0002BA<\u0003/\u0012\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\t\u0003W\nY(a \u0002\u0016&!\u0011QPA0\u0005\u0019!V\u000f\u001d7feA!\u0011\u0011QAB\u0019\u0001!q!!\"\u0001\u0005\u0004\t9IA\u0001L#\u0011\tI)a$\u0011\t\u0005-\u00141R\u0005\u0005\u0003\u001b\u000byFA\u0004O_RD\u0017N\\4\u0011\t\u0005-\u0014\u0011S\u0005\u0005\u0003'\u000byFA\u0002B]f\u0004B!!!\u0002\u0018\u0012A\u0011\u0011\u0014\u0001\u0005\u0006\u0004\t9IA\u0001W!!\ti*a(\u0002\u0000\u0005UUBAA.\u0013\u0011\t)&a\u0017\u0011\u0019\u0005M\u00141UA@\u0003+\u000b9+!+\n\t\u0005\u0015\u0016q\u000b\u0002\u0007\u001b\u0006\u0004x\n]:\u0011\u0007\u0005M\u0004\u0001E\u0004\u0002t\u0001\ty(!&\u0011\u0019\u0005u\u0015QVA@\u0003+\u000b9+!-\n\t\u0005=\u00161\f\u0002\u0013\u001b\u0006\u0004h)Y2u_JLH)\u001a4bk2$8\u000f\u0005\u0003\u0002t\u0005U\u0014A\u0002\u0013j]&$H\u0005\u0006\u0002\u00028B!\u00111NA]\u0013\u0011\tY,a\u0018\u0003\tUs\u0017\u000e^\u0001\u000b[\u0006\u0004h)Y2u_JLXCAAa!\u0019\ti*a1\u0002(&!\u0011QYA.\u0005)i\u0015\r\u001d$bGR|'/_\u0001\u0006i>l\u0015\r]\u000b\u0007\u0003\u0017\f\t.a6\u0015\t\u00055\u00171\u001c\t\b\u0003g\u0002\u0011qZAk!\u0011\t\t)!5\u0005\u000f\u0005M7A1\u0001\u0002\b\n\u00111J\r\t\u0005\u0003\u0003\u000b9\u000eB\u0004\u0002Z\u000e\u0011\r!a\"\u0003\u0005Y\u0013\u0004bBAo\u0007\u0001\u000f\u0011q\\\u0001\u0003KZ\u0004\u0002\"a\u001b\u0002b\u0006e\u0014Q]\u0005\u0005\u0003G\fyF\u0001\t%Y\u0016\u001c8\u000fJ2pY>tG\u0005\\3tgBA\u00111NA>\u0003\u001f\f).A\u0006xSRDG)\u001a4bk2$X\u0003BAv\u0003c$B!!<\u0002xB9\u00111\u000f\u0001\u0002\u0000\u0005=\b\u0003BAA\u0003c$q!a=\u0005\u0005\u0004\t)P\u0001\u0002WcE!\u0011QSAH\u0011\u001d\tI\u0010\u0002a\u0001\u0003w\f\u0011\u0001\u001a\t\t\u0003W\ni0a \u0002p&!\u0011q`A0\u0005%1UO\\2uS>t\u0017'\u0001\txSRDG)\u001a4bk2$h+\u00197vKV!!Q\u0001B\u0006)\u0011\u00119A!\u0004\u0011\u000f\u0005M\u0004!a \u0003\nA!\u0011\u0011\u0011B\u0006\t\u001d\t\u00190\u0002b\u0001\u0003kDq!!?\u0006\u0001\u0004\u0011I!A\u0002NCB\u00042!a\u001d\b'\u00159\u0011\u0011NAa\u0003\u0019a\u0014N\\5u}Q\u0011!\u0011\u0003\u0002\f/&$\b\u000eR3gCVdG/\u0006\u0004\u0003\u001e\t\u001d\"1F\n\b\u0013\t}!Q\u0006B\u001a!!\t\u0019H!\t\u0003&\t%\u0012\u0002\u0002B\u0012\u0003/\u00121\"\u00112tiJ\f7\r^'baB!\u0011\u0011\u0011B\u0014\t\u001d\t))\u0003b\u0001\u0003\u000f\u0003B!!!\u0003,\u0011A\u0011\u0011T\u0005\u0005\u0006\u0004\t9\t\u0005\u0007\u0002t\u0005\r&Q\u0005B\u0015\u0003O\u0013y\u0003E\u0004\u00032%\u0011)C!\u000b\u000e\u0003\u001d\u0001BA!\u000e\u0003<9!\u00111\u000eB\u001c\u0013\u0011\u0011I$a\u0018\u0002\u000fA\f7m[1hK&!!Q\bB \u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\u0011\u0011I$a\u0018\u0002\u0015UtG-\u001a:ms&tw-\u0006\u0002\u0003FA9\u00111\u000f\u0001\u0003&\t%\u0012aC;oI\u0016\u0014H._5oO\u0002\nA\u0002Z3gCVdGOV1mk\u0016,\"A!\u0014\u0011\u0011\u0005-\u0014Q B\u0013\u0005S\tQ\u0002Z3gCVdGOV1mk\u0016\u0004CC\u0002B\u0018\u0005'\u0012)\u0006C\u0004\u0003B9\u0001\rA!\u0012\t\u000f\t%c\u00021\u0001\u0003N\u0005\u0019q-\u001a;\u0015\t\tm#\u0011\r\t\u0007\u0003W\u0012iF!\u000b\n\t\t}\u0013q\f\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\t\rt\u00021\u0001\u0003&\u0005\u00191.Z=\u0002\u000f\u0011,g-Y;miR!!\u0011\u0006B5\u0011\u001d\u0011\u0019\u0007\u0005a\u0001\u0005K\tq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0003\u0005_\u0002b!!(\u0003r\u0005E\u0016\u0002\u0002B:\u00037\u0012q\"\u0013;fe\u0006\u0014G.\u001a$bGR|'/_\u0001\tSR,'/\u0019;peV\u0011!\u0011\u0010\t\u0007\u0003;\u0013YHa \n\t\tu\u00141\f\u0002\t\u0013R,'/\u0019;peBA\u00111NA>\u0005K\u0011I#A\u0004jg\u0016k\u0007\u000f^=\u0016\u0005\t\u0015\u0005\u0003BA6\u0005\u000fKAA!#\u0002`\t9!i\\8mK\u0006t\u0017AB2p]\u000e\fG/\u0006\u0003\u0003\u0010\nUE\u0003\u0002BI\u00053\u0003rA!\r\n\u0005K\u0011\u0019\n\u0005\u0003\u0002\u0002\nUEaBAm+\t\u0007!qS\t\u0005\u0005S\ty\tC\u0004\u0003\u001cV\u0001\rA!(\u0002\u0005a\u001c\bCBAO\u0005?\u0013\u0019+\u0003\u0003\u0003\"\u0006m#\u0001D%uKJ\f'\r\\3P]\u000e,\u0007\u0003CA6\u0003w\u0012)Ca%\u0002\u000fI,Wn\u001c<fIR!!q\u0006BU\u0011\u001d\u0011\u0019G\u0006a\u0001\u0005K\tq!\u001e9eCR,G-\u0006\u0003\u00030\nUFC\u0002BY\u0005o\u0013I\fE\u0004\u00032%\u0011)Ca-\u0011\t\u0005\u0005%Q\u0017\u0003\b\u0003g<\"\u0019\u0001BL\u0011\u001d\u0011\u0019g\u0006a\u0001\u0005KAqAa/\u0018\u0001\u0004\u0011\u0019,A\u0003wC2,X-A\u0003f[B$\u00180\u0006\u0002\u00030\u0005aaM]8n'B,7-\u001b4jGR!!q\u0006Bc\u0011\u001d\u00119-\u0007a\u0001\u0005\u0013\fAaY8mY*\"!1\u001aBg!\u0019\tiJa(\u0003\u0000-\u0012!q\u001a\t\u0005\u0005#\u0014Y.\u0004\u0002\u0003T*!!Q\u001bBl\u0003%)hn\u00195fG.,GM\u0003\u0003\u0003Z\u0006}\u0013AC1o]>$\u0018\r^5p]&!!Q\u001cBj\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0013]\u0016<8\u000b]3dS\u001aL7MQ;jY\u0012,'/\u0006\u0002\u0003d*\"!Q\u001dBg!!\u00119O!<\u0003\u0000\t=RB\u0001Bu\u0015\u0011\u0011Y/a\u0017\u0002\u000f5,H/\u00192mK&!!q\u001eBu\u0005\u001d\u0011U/\u001b7eKJDs!\u0003Bz\u0005w\u0013I\u0010\u0005\u0003\u0002l\tU\u0018\u0002\u0002B|\u0003?\u0012\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u001f\u0003\r)bA!@\u0004\u0004\r\u001dQC\u0001B\u0000!\u001d\t\u0019\bAB\u0001\u0007\u000b\u0001B!!!\u0004\u0004\u00119\u0011QQ\u000eC\u0002\u0005\u001d\u0005\u0003BAA\u0007\u000f!q!!'\u001c\u0005\u0004\t9)\u0001\u0003ge>lWCBB\u0007\u0007'\u00199\u0002\u0006\u0003\u0004\u0010\re\u0001cBA:\u0001\rE1Q\u0003\t\u0005\u0003\u0003\u001b\u0019\u0002B\u0004\u0002\u0006r\u0011\r!a\"\u0011\t\u0005\u00055q\u0003\u0003\b\u00033c\"\u0019AAD\u0011\u001d\u0019Y\u0002\ba\u0001\u0007;\t!!\u001b;\u0011\r\u0005u%qTB\u0010!!\tY'a\u001f\u0004\u0012\rU\u0011A\u00038fo\n+\u0018\u000e\u001c3feV11QEB\u0017\u0007c)\"aa\n\u0011\u0011\t\u001d(Q^B\u0015\u0007g\u0001\u0002\"a\u001b\u0002|\r-2q\u0006\t\u0005\u0003\u0003\u001bi\u0003B\u0004\u0002\u0006v\u0011\r!a\"\u0011\t\u0005\u00055\u0011\u0007\u0003\b\u00033k\"\u0019AAD!\u001d\t\u0019\bAB\u0016\u0007_\t\u0001\"R7qifl\u0015\r\u001d\t\u0004\u0005cy\"\u0001C#naRLX*\u00199\u0014\u000b}\u0019iDa\r\u0011\u0011\u0005M$\u0011EAH\u0003\u0013#\"aa\u000e\u0002\tML'0Z\u000b\u0003\u0007\u000b\u0002B!a\u001b\u0004H%!1\u0011JA0\u0005\rIe\u000e^\u0001\nW:|wO\\*ju\u0016\fQ!\u00199qYf$B!!#\u0004R!9!1\r\u0013A\u0002\u0005=\u0015\u0001C2p]R\f\u0017N\\:\u0015\t\t\u00155q\u000b\u0005\b\u0005G*\u0003\u0019AAH)\u0011\u0019Yf!\u0018\u0011\r\u0005-$QLAE\u0011\u001d\u0011\u0019G\na\u0001\u0003\u001f\u000b\u0011bZ3u\u001fJ,En]3\u0016\t\r\r4q\r\u000b\u0007\u0007K\u001aIga\u001b\u0011\t\u0005\u00055q\r\u0003\b\u0003g<#\u0019AAD\u0011\u001d\u0011\u0019g\na\u0001\u0003\u001fC\u0001B!\u001a(\t\u0003\u00071Q\u000e\t\u0007\u0003W\u001ayg!\u001a\n\t\rE\u0014q\f\u0002\ty\tLh.Y7f}U\u00111Q\u000f\t\u0007\u0003;\u0013Yha\u001e\u0011\u0011\u0005-\u00141PAH\u0003\u0013\u000bAb[3zg&#XM]1u_J,\"a! \u0011\r\u0005u%1PAH\u000391\u0018\r\\;fg&#XM]1u_J,\"aa!\u0011\r\u0005u%1PAE+\u0011\u00199i!$\u0015\r\r%5qRBI!\u001d\t\u0019\bAAH\u0007\u0017\u0003B!!!\u0004\u000e\u00129\u00111_\u0016C\u0002\u0005\u001d\u0005b\u0002B2W\u0001\u0007\u0011q\u0012\u0005\b\u0005w[\u0003\u0019ABF)\u0011\u0019)ja&\u0011\u000f\u0005M\u0004!a$\u0002\n\"9!1\r\u0017A\u0002\u0005=U\u0003BBN\u0007C#Ba!(\u0004$B9\u00111\u000f\u0001\u0002\u0010\u000e}\u0005\u0003BAA\u0007C#q!!7.\u0005\u0004\t9\tC\u0004\u0004&6\u0002\raa*\u0002\rM,hMZ5y!\u0019\tiJa(\u0004*BA\u00111NA>\u0003\u001f\u001by*\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00040B!1\u0011WB^\u001b\t\u0019\u0019L\u0003\u0003\u00046\u000e]\u0016\u0001\u00027b]\u001eT!a!/\u0002\t)\fg/Y\u0005\u0005\u0007{\u001b\u0019L\u0001\u0004PE*,7\r\u001e\u0015\b?\tM(1\u0018B}Q\u001dq\"1\u001fB^\u0005s\u0014A!T1qcU11qYBg\u0007#\u001craLBe\u0007'\u0014\u0019\u0004\u0005\u0005\u0002t\t\u000521ZBh!\u0011\t\ti!4\u0005\u000f\u0005\u0015uF1\u0001\u0002\bB!\u0011\u0011QBi\t!\tIj\fCC\u0002\u0005\u001d\u0005CCAO\u0007+\u001cI.!-\u0004\\&!1q[A.\u0005i\u0019FO]5di>\u0003H/[7ju\u0016$\u0017\n^3sC\ndWm\u00149t!!\tY'a\u001f\u0004L\u000e=\u0007cBA:\u0001\r-7qZ\u0001\u0005W\u0016L\u0018'\u0001\u0004wC2,X-\r\u000b\u0007\u0007G\u001c)oa:\u0011\u000f\tErfa3\u0004P\"91Q\u001c\u001aA\u0002\r-\u0007bBBpe\u0001\u00071q\u001a\u000b\u0005\u0007\u001f\u001cY\u000fC\u0004\u0003dY\u0002\raa3\u0015\t\t\u00155q\u001e\u0005\b\u0005G:\u0004\u0019ABf)\u0011\u0019\u0019p!>\u0011\r\u0005-$QLBh\u0011\u001d\u0011\u0019\u0007\u000fa\u0001\u0007\u0017,Ba!?\u0004~R111 C\u0001\t\u0007\u0001B!!!\u0004~\u00129\u00111_\u001dC\u0002\r}\u0018\u0003BBh\u0003\u001fCqAa\u0019:\u0001\u0004\u0019Y\r\u0003\u0005\u0003fe\"\t\u0019\u0001C\u0003!\u0019\tYga\u001c\u0004|V\u0011A\u0011\u0002\t\u0007\u0003;\u0013Yh!7\u0016\u0005\u00115\u0001CBAO\u0005w\u001aY-\u0006\u0002\u0005\u0012A1\u0011Q\u0014B>\u0007\u001f,B\u0001\"\u0006\u0005\u001cQ1Aq\u0003C\u000f\t?\u0001r!a\u001d\u0001\u0007\u0017$I\u0002\u0005\u0003\u0002\u0002\u0012mAaBAz{\t\u00071q \u0005\b\u0005Gj\u0004\u0019ABf\u0011\u001d\u0011Y,\u0010a\u0001\t3!Baa7\u0005$!9!1\r A\u0002\r-\u0017a\u00024pe\u0016\f7\r[\u000b\u0005\tS!\u0019\u0004\u0006\u0003\u00028\u0012-\u0002b\u0002C\u0017\u007f\u0001\u0007AqF\u0001\u0002MBA\u00111NA\u007f\u00073$\t\u0004\u0005\u0003\u0002\u0002\u0012MBa\u0002C\u001b\u007f\t\u0007\u0011q\u0011\u0002\u0002+\u00061Q\r_5tiN$BA!\"\u0005<!9AQ\b!A\u0002\u0011}\u0012!\u00019\u0011\u0011\u0005-\u0014Q`Bm\u0005\u000b\u000baAZ8sC2dG\u0003\u0002BC\t\u000bBq\u0001\"\u0010B\u0001\u0004!y$\u0001\u0006gS2$XM]%na2$baa7\u0005L\u0011=\u0003b\u0002C'\u0005\u0002\u0007AqH\u0001\u0005aJ,G\rC\u0004\u0005R\t\u0003\rA!\"\u0002\u0013%\u001ch\t\\5qa\u0016$\u0017!\u0003;sC:\u001chm\u001c:n+\u0011!9\u0006\"\u0018\u0015\t\u0011eC\u0011\r\t\b\u0003g\u000211\u001aC.!\u0011\t\t\t\"\u0018\u0005\u000f\u0011}3I1\u0001\u0002\b\n\tq\u000bC\u0004\u0005.\r\u0003\r\u0001b\u0019\u0011\u0015\u0005-DQMBf\u0007\u001f$Y&\u0003\u0003\u0005h\u0005}#!\u0003$v]\u000e$\u0018n\u001c83\u0003!A\u0017m\u001d5D_\u0012,GCAB#Q\u001dy#1\u001fB^\u0005s\u0014A!T1qeU1A1\u000fC=\t{\u001ar!\u0012C;\t\u007f\u0012\u0019\u0004\u0005\u0005\u0002t\t\u0005Bq\u000fC>!\u0011\t\t\t\"\u001f\u0005\u000f\u0005\u0015UI1\u0001\u0002\bB!\u0011\u0011\u0011C?\t!\tI*\u0012CC\u0002\u0005\u001d\u0005CCAO\u0007+$\t)!-\u0005\u0004BA\u00111NA>\to\"Y\bE\u0004\u0002t\u0001!9\bb\u001f\u0002\t-,\u0017PM\u0001\u0007m\u0006dW/\u001a\u001a\u0015\u0015\u0011-EQ\u0012CH\t##\u0019\nE\u0004\u00032\u0015#9\bb\u001f\t\u000f\ru'\n1\u0001\u0005x!91q\u001c&A\u0002\u0011m\u0004b\u0002CC\u0015\u0002\u0007Aq\u000f\u0005\b\t\u000fS\u0005\u0019\u0001C>)\u0011!Y\bb&\t\u000f\t\rd\n1\u0001\u0005xQ!!Q\u0011CN\u0011\u001d\u0011\u0019g\u0014a\u0001\to\"B\u0001b(\u0005\"B1\u00111\u000eB/\twBqAa\u0019Q\u0001\u0004!9(\u0006\u0003\u0005&\u0012%FC\u0002CT\t[#y\u000b\u0005\u0003\u0002\u0002\u0012%FaBAz#\n\u0007A1V\t\u0005\tw\ny\tC\u0004\u0003dE\u0003\r\u0001b\u001e\t\u0011\t\u0015\u0014\u000b\"a\u0001\tc\u0003b!a\u001b\u0004p\u0011\u001dVC\u0001C[!\u0019\tiJa\u001f\u0005\u0002V\u0011A\u0011\u0018\t\u0007\u0003;\u0013Y\bb\u001e\u0016\u0005\u0011u\u0006CBAO\u0005w\"YH\u0001\u0007NCB\u0014\u0014\n^3sCR|'/\u0006\u0003\u0005D\u001257cA+\u0005FB1\u0011Q\u0014Cd\t\u0017LA\u0001\"3\u0002\\\t\u0001\u0012IY:ue\u0006\u001cG/\u0013;fe\u0006$xN\u001d\t\u0005\u0003\u0003#i\rB\u0004\u0005PV\u0013\r!a\"\u0003\u0003\u0005#\"\u0001b5\u0011\u000b\u0011UW\u000bb3\u000e\u0003\u0015\u000b\u0011![\u0001\bQ\u0006\u001ch*\u001a=u\u0003\u0011qW\r\u001f;\u0015\u0005\u0011-\u0017\u0001\u00023s_B$B\u0001b9\u0005fB1\u0011Q\u0014B>\t\u0017Dq\u0001b:[\u0001\u0004\u0019)%A\u0001o\u0003)qW\r\u001f;SKN,H\u000e\u001e\u000b\u0007\t\u0017$i\u000f\"=\t\u000f\u0011=8\f1\u0001\u0005x\u0005\t1\u000eC\u0004\u0005tn\u0003\r\u0001\">\u0002\u0003YTC\u0001b\u001f\u0003NV!A\u0011 C\u0000)\u0019!Y0\"\u0001\u0006\u0004A9\u00111\u000f\u0001\u0005x\u0011u\b\u0003BAA\t\u007f$q!a=]\u0005\u0004!Y\u000bC\u0004\u0003dq\u0003\r\u0001b\u001e\t\u000f\tmF\f1\u0001\u0005~R!A1QC\u0004\u0011\u001d\u0011\u0019'\u0018a\u0001\to*B!b\u0003\u0006\u0014Q!\u0011qWC\u0007\u0011\u001d!iC\u0018a\u0001\u000b\u001f\u0001\u0002\"a\u001b\u0002~\u0012\u0005U\u0011\u0003\t\u0005\u0003\u0003+\u0019\u0002B\u0004\u00056y\u0013\r!a\"\u0015\t\t\u0015Uq\u0003\u0005\b\t{y\u0006\u0019AC\r!!\tY'!@\u0005\u0002\n\u0015E\u0003\u0002BC\u000b;Aq\u0001\"\u0010a\u0001\u0004)I\u0002\u0006\u0004\u0005\u0004\u0016\u0005R1\u0005\u0005\b\t\u001b\n\u0007\u0019AC\r\u0011\u001d!\t&\u0019a\u0001\u0005\u000b+B!b\n\u0006.Q!Q\u0011FC\u0018!\u001d\t\u0019\b\u0001C<\u000bW\u0001B!!!\u0006.\u00119Aq\f2C\u0002\u0005\u001d\u0005b\u0002C\u0017E\u0002\u0007Q\u0011\u0007\t\u000b\u0003W\")\u0007b\u001e\u0005|\u0015-\u0002fB#\u0003t\nm&\u0011 \u0002\u0005\u001b\u0006\u00048'\u0006\u0004\u0006:\u0015}R1I\n\bI\u0016mRQ\tB\u001a!!\t\u0019H!\t\u0006>\u0015\u0005\u0003\u0003BAA\u000b\u007f!q!!\"e\u0005\u0004\t9\t\u0005\u0003\u0002\u0002\u0016\rC\u0001CAMI\u0012\u0015\r!a\"\u0011\u0015\u0005u5Q[C$\u0003c+I\u0005\u0005\u0005\u0002l\u0005mTQHC!!\u001d\t\u0019\bAC\u001f\u000b\u0003\nAa[3zg\u00051a/\u00197vKN\"b\"\"\u0015\u0006T\u0015USqKC-\u000b7*i\u0006E\u0004\u00032\u0011,i$\"\u0011\t\u000f\ru7\u000e1\u0001\u0006>!91q\\6A\u0002\u0015\u0005\u0003b\u0002CCW\u0002\u0007QQ\b\u0005\b\t\u000f[\u0007\u0019AC!\u0011\u001d)Ye\u001ba\u0001\u000b{Aq!\"\u0014l\u0001\u0004)\t\u0005\u0006\u0003\u0006B\u0015\u0005\u0004b\u0002B2_\u0002\u0007QQ\b\u000b\u0005\u0005\u000b+)\u0007C\u0004\u0003dA\u0004\r!\"\u0010\u0015\t\u0015%T1\u000e\t\u0007\u0003W\u0012i&\"\u0011\t\u000f\t\r\u0014\u000f1\u0001\u0006>U!QqNC:)\u0019)\t(b\u001e\u0006zA!\u0011\u0011QC:\t\u001d\t\u0019P\u001db\u0001\u000bk\nB!\"\u0011\u0002\u0010\"9!1\r:A\u0002\u0015u\u0002\u0002\u0003B3e\u0012\u0005\r!b\u001f\u0011\r\u0005-4qNC9+\t)y\b\u0005\u0004\u0002\u001e\nmTqI\u000b\u0003\u000b\u0007\u0003b!!(\u0003|\u0015uRCACD!\u0019\tiJa\u001f\u0006B\taQ*\u001994\u0013R,'/\u0019;peV!QQRCJ'\r1Xq\u0012\t\u0007\u0003;#9-\"%\u0011\t\u0005\u0005U1\u0013\u0003\b\t\u001f4(\u0019AAD)\t)9\nE\u0003\u0006\u001aZ,\t*D\u0001e)\t)\t\n\u0006\u0003\u0006 \u0016\u0005\u0006CBAO\u0005w*\t\nC\u0004\u0005hn\u0004\ra!\u0012\u0015\r\u0015EUQUCT\u0011\u001d!y\u000f a\u0001\u000b{Aq\u0001b=}\u0001\u0004)IK\u000b\u0003\u0006B\t5W\u0003BCW\u000bg#b!b,\u00066\u0016]\u0006cBA:\u0001\u0015uR\u0011\u0017\t\u0005\u0003\u0003+\u0019\fB\u0004\u0002tv\u0014\r!\"\u001e\t\u000f\t\rT\u00101\u0001\u0006>!9!1X?A\u0002\u0015EF\u0003BC%\u000bwCqAa\u0019\u007f\u0001\u0004)i$\u0006\u0003\u0006@\u0016\u001dG\u0003BA\\\u000b\u0003Dq\u0001\"\f\u0000\u0001\u0004)\u0019\r\u0005\u0005\u0002l\u0005uXqICc!\u0011\t\t)b2\u0005\u000f\u0011UrP1\u0001\u0002\bR!!QQCf\u0011!!i$!\u0001A\u0002\u00155\u0007\u0003CA6\u0003{,9E!\"\u0015\t\t\u0015U\u0011\u001b\u0005\t\t{\t\u0019\u00011\u0001\u0006NR1Q\u0011JCk\u000b/D\u0001\u0002\"\u0014\u0002\u0006\u0001\u0007QQ\u001a\u0005\t\t#\n)\u00011\u0001\u0003\u0006V!Q1\\Cq)\u0011)i.b9\u0011\u000f\u0005M\u0004!\"\u0010\u0006`B!\u0011\u0011QCq\t!!y&a\u0002C\u0002\u0005\u001d\u0005\u0002\u0003C\u0017\u0003\u000f\u0001\r!\":\u0011\u0015\u0005-DQMC\u001f\u000b\u0003*y\u000eK\u0004e\u0005g\u0014YL!?\u0003\t5\u000b\u0007\u000fN\u000b\u0007\u000b[,\u00190b>\u0014\u0011\u0005-Qq^C}\u0005g\u0001\u0002\"a\u001d\u0003\"\u0015EXQ\u001f\t\u0005\u0003\u0003+\u0019\u0010\u0002\u0005\u0002\u0006\u0006-!\u0019AAD!\u0011\t\t)b>\u0005\u0013\u0005e\u00151\u0002CC\u0002\u0005\u001d\u0005CCAO\u0007+,Y0!-\u0006~BA\u00111NA>\u000bc,)\u0010E\u0004\u0002t\u0001)\t0\">\u0002\t-,\u0017\u0010N\u0001\u0007m\u0006dW/\u001a\u001b\u0015%\u0019\u0015aq\u0001D\u0005\r\u00171iAb\u0004\u0007\u0012\u0019MaQ\u0003\t\t\u0005c\tY!\"=\u0006v\"A1Q\\A\u000f\u0001\u0004)\t\u0010\u0003\u0005\u0004`\u0006u\u0001\u0019AC{\u0011!!))!\bA\u0002\u0015E\b\u0002\u0003CD\u0003;\u0001\r!\">\t\u0011\u0015-\u0013Q\u0004a\u0001\u000bcD\u0001\"\"\u0014\u0002\u001e\u0001\u0007QQ\u001f\u0005\t\u000b\u007f\fi\u00021\u0001\u0006r\"Aa\u0011AA\u000f\u0001\u0004))\u0010\u0006\u0003\u0006v\u001ae\u0001\u0002\u0003B2\u0003K\u0001\r!\"=\u0015\t\t\u0015eQ\u0004\u0005\t\u0005G\n9\u00031\u0001\u0006rR!a\u0011\u0005D\u0012!\u0019\tYG!\u0018\u0006v\"A!1MA\u0015\u0001\u0004)\t0\u0006\u0003\u0007(\u0019-BC\u0002D\u0015\r_1\t\u0004\u0005\u0003\u0002\u0002\u001a-B\u0001CAz\u0003W\u0011\rA\"\f\u0012\t\u0015U\u0018q\u0012\u0005\t\u0005G\nY\u00031\u0001\u0006r\"I!QMA\u0016\t\u0003\u0007a1\u0007\t\u0007\u0003W\u001ayG\"\u000b\u0016\u0005\u0019]\u0002CBAO\u0005w*Y0\u0006\u0002\u0007<A1\u0011Q\u0014B>\u000bc,\"Ab\u0010\u0011\r\u0005u%1PC{\u00051i\u0015\r\u001d\u001bJi\u0016\u0014\u0018\r^8s+\u00111)Eb\u0013\u0014\t\u0005Mbq\t\t\u0007\u0003;#9M\"\u0013\u0011\t\u0005\u0005e1\n\u0003\t\t\u001f\f\u0019D1\u0001\u0002\bR\u0011aq\n\t\u0007\r#\n\u0019D\"\u0013\u000e\u0005\u0005-AC\u0001D%)\u001119F\"\u0017\u0011\r\u0005u%1\u0010D%\u0011!!9/!\u0010A\u0002\r\u0015CC\u0002D%\r;2y\u0006\u0003\u0005\u0005p\u0006}\u0002\u0019ACy\u0011!!\u00190a\u0010A\u0002\u0019\u0005$\u0006BC{\u0005\u001b,BA\"\u001a\u0007lQ1aq\rD7\r_\u0002r!a\u001d\u0001\u000bc4I\u0007\u0005\u0003\u0002\u0002\u001a-D\u0001CAz\u0003\u0003\u0012\rA\"\f\t\u0011\t\r\u0014\u0011\ta\u0001\u000bcD\u0001Ba/\u0002B\u0001\u0007a\u0011\u000e\u000b\u0005\u000b{4\u0019\b\u0003\u0005\u0003d\u0005\r\u0003\u0019ACy+\u001119Hb \u0015\t\u0005]f\u0011\u0010\u0005\t\t[\t)\u00051\u0001\u0007|AA\u00111NA\u007f\u000bw4i\b\u0005\u0003\u0002\u0002\u001a}D\u0001\u0003C\u001b\u0003\u000b\u0012\r!a\"\u0015\t\t\u0015e1\u0011\u0005\t\t{\t9\u00051\u0001\u0007\u0006BA\u00111NA\u007f\u000bw\u0014)\t\u0006\u0003\u0003\u0006\u001a%\u0005\u0002\u0003C\u001f\u0003\u0013\u0002\rA\"\"\u0015\r\u0015uhQ\u0012DH\u0011!!i%a\u0013A\u0002\u0019\u0015\u0005\u0002\u0003C)\u0003\u0017\u0002\rA!\"\u0016\t\u0019Me\u0011\u0014\u000b\u0005\r+3Y\nE\u0004\u0002t\u0001)\tPb&\u0011\t\u0005\u0005e\u0011\u0014\u0003\t\t?\niE1\u0001\u0002\b\"AAQFA'\u0001\u00041i\n\u0005\u0006\u0002l\u0011\u0015T\u0011_C{\r/\u000bqAY;jY\u0012$v.\u0006\u0003\u0007$\u001aMF\u0003\u0002DS\rOsA!!!\u0007(\"Aa\u0011VA(\u0001\u00041Y+A\u0004ck&dG-\u001a:\u0011\u0011\u0005MdQVCy\rcKAAb,\u0002X\tq\u0001*Y:i\u001b\u0006\u0004()^5mI\u0016\u0014\b\u0003BAA\rg#\u0001\"a=\u0002P\t\u0007aQ\u0006\u0015\t\u0003\u0017\u0011\u0019Pa/\u0003z\":qAa=\u0003<\ne\bf\u0002\u0004\u0003t\nm&\u0011 "
)
public interface Map extends Iterable, scala.collection.Map, MapOps {
   static Builder newBuilder() {
      Map$ var10000 = Map$.MODULE$;
      return new MapBuilderImpl();
   }

   static Map from(final IterableOnce it) {
      return Map$.MODULE$.from(it);
   }

   // $FF: synthetic method
   static MapFactory mapFactory$(final Map $this) {
      return $this.mapFactory();
   }

   default MapFactory mapFactory() {
      return Map$.MODULE$;
   }

   // $FF: synthetic method
   static Map toMap$(final Map $this, final $less$colon$less ev) {
      return $this.toMap(ev);
   }

   default Map toMap(final $less$colon$less ev) {
      return Map$.MODULE$.from(this);
   }

   // $FF: synthetic method
   static Map withDefault$(final Map $this, final Function1 d) {
      return $this.withDefault(d);
   }

   default Map withDefault(final Function1 d) {
      return new WithDefault(this, d);
   }

   // $FF: synthetic method
   static Map withDefaultValue$(final Map $this, final Object d) {
      return $this.withDefaultValue(d);
   }

   default Map withDefaultValue(final Object d) {
      return new WithDefault(this, (x$1) -> d);
   }

   static void $init$(final Map $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class WithDefault extends AbstractMap implements Serializable {
      private static final long serialVersionUID = 3L;
      private final Map underlying;
      private final Function1 defaultValue;

      public Map underlying() {
         return this.underlying;
      }

      public Function1 defaultValue() {
         return this.defaultValue;
      }

      public Option get(final Object key) {
         return this.underlying().get(key);
      }

      public Object default(final Object key) {
         return this.defaultValue().apply(key);
      }

      public IterableFactory iterableFactory() {
         return this.underlying().iterableFactory();
      }

      public Iterator iterator() {
         return this.underlying().iterator();
      }

      public boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      public MapFactory mapFactory() {
         return this.underlying().mapFactory();
      }

      public WithDefault concat(final IterableOnce xs) {
         return new WithDefault((Map)this.underlying().concat(xs), this.defaultValue());
      }

      public WithDefault removed(final Object key) {
         return new WithDefault((Map)this.underlying().removed(key), this.defaultValue());
      }

      public WithDefault updated(final Object key, final Object value) {
         return new WithDefault((Map)this.underlying().updated(key, value), this.defaultValue());
      }

      public WithDefault empty() {
         return new WithDefault((Map)this.underlying().empty(), this.defaultValue());
      }

      public WithDefault fromSpecific(final IterableOnce coll) {
         return new WithDefault((Map)this.mapFactory().from(coll), this.defaultValue());
      }

      public Builder newSpecificBuilder() {
         Map$ var10000 = Map$.MODULE$;
         MapBuilderImpl var3 = new MapBuilderImpl();
         Function1 mapResult_f = (p) -> new WithDefault(p, this.defaultValue());
         MapBuilderImpl mapResult_this = var3;
         return new Builder(mapResult_f) {
            // $FF: synthetic field
            private final Builder $outer;
            private final Function1 f$1;

            public final void sizeHint(final IterableOnce coll, final int delta) {
               Builder.sizeHint$(this, coll, delta);
            }

            public final int sizeHint$default$2() {
               return Builder.sizeHint$default$2$(this);
            }

            public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
               Builder.sizeHintBounded$(this, size, boundingColl);
            }

            public Builder mapResult(final Function1 f) {
               return Builder.mapResult$(this, f);
            }

            public final Growable $plus$eq(final Object elem) {
               return Growable.$plus$eq$(this, elem);
            }

            /** @deprecated */
            public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
               return Growable.$plus$eq$(this, elem1, elem2, elems);
            }

            public final Growable $plus$plus$eq(final IterableOnce elems) {
               return Growable.$plus$plus$eq$(this, elems);
            }

            public <undefinedtype> addOne(final Object x) {
               Builder var10000 = this.$outer;
               if (var10000 == null) {
                  throw null;
               } else {
                  var10000.addOne(x);
                  return this;
               }
            }

            public void clear() {
               this.$outer.clear();
            }

            public <undefinedtype> addAll(final IterableOnce xs) {
               Builder var10000 = this.$outer;
               if (var10000 == null) {
                  throw null;
               } else {
                  var10000.addAll(xs);
                  return this;
               }
            }

            public void sizeHint(final int size) {
               this.$outer.sizeHint(size);
            }

            public Object result() {
               return this.f$1.apply(this.$outer.result());
            }

            public int knownSize() {
               return this.$outer.knownSize();
            }

            public {
               if (WithDefault.this == null) {
                  throw null;
               } else {
                  this.$outer = WithDefault.this;
                  this.f$1 = f$1;
               }
            }
         };
      }

      public WithDefault(final Map underlying, final Function1 defaultValue) {
         this.underlying = underlying;
         this.defaultValue = defaultValue;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class EmptyMap$ extends AbstractMap implements Serializable {
      public static final EmptyMap$ MODULE$ = new EmptyMap$();
      private static final long serialVersionUID = 3L;

      public int size() {
         return 0;
      }

      public int knownSize() {
         return 0;
      }

      public boolean isEmpty() {
         return true;
      }

      public Nothing$ apply(final Object key) {
         throw new NoSuchElementException((new StringBuilder(15)).append("key not found: ").append(key).toString());
      }

      public boolean contains(final Object key) {
         return false;
      }

      public Option get(final Object key) {
         return None$.MODULE$;
      }

      public Object getOrElse(final Object key, final Function0 default) {
         return default.apply();
      }

      public Iterator iterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      }

      public Iterator keysIterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      }

      public Iterator valuesIterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      }

      public Map updated(final Object key, final Object value) {
         return new Map1(key, value);
      }

      public Map removed(final Object key) {
         return this;
      }

      public Map concat(final IterableOnce suffix) {
         return suffix instanceof Map ? (Map)suffix : (Map)scala.collection.MapOps.concat$(this, suffix);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EmptyMap$.class);
      }

      public EmptyMap$() {
      }
   }

   public static final class Map1 extends AbstractMap implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object key1;
      private final Object value1;

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

      public int size() {
         return 1;
      }

      public int knownSize() {
         return 1;
      }

      public boolean isEmpty() {
         return false;
      }

      public Object apply(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return this.value1;
         } else {
            throw new NoSuchElementException((new StringBuilder(15)).append("key not found: ").append(key).toString());
         }
      }

      public boolean contains(final Object key) {
         return BoxesRunTime.equals(key, this.key1);
      }

      public Option get(final Object key) {
         return (Option)(BoxesRunTime.equals(key, this.key1) ? new Some(this.value1) : None$.MODULE$);
      }

      public Object getOrElse(final Object key, final Function0 default) {
         return BoxesRunTime.equals(key, this.key1) ? this.value1 : default.apply();
      }

      public Iterator iterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = new Tuple2(this.key1, this.value1);
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      }

      public Iterator keysIterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = this.key1;
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      }

      public Iterator valuesIterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = this.value1;
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      }

      public Map updated(final Object key, final Object value) {
         return (Map)(BoxesRunTime.equals(key, this.key1) ? new Map1(this.key1, value) : new Map2(this.key1, this.value1, key, value));
      }

      public Map removed(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            Map$ var10000 = Map$.MODULE$;
            return Map.EmptyMap$.MODULE$;
         } else {
            return this;
         }
      }

      public void foreach(final Function1 f) {
         f.apply(new Tuple2(this.key1, this.value1));
      }

      public boolean exists(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.key1, this.value1)));
      }

      public boolean forall(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.key1, this.value1)));
      }

      public Map filterImpl(final Function1 pred, final boolean isFlipped) {
         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.key1, this.value1))) != isFlipped) {
            return this;
         } else {
            Map$ var10000 = Map$.MODULE$;
            return Map.EmptyMap$.MODULE$;
         }
      }

      public Map transform(final Function2 f) {
         Object walue1 = f.apply(this.key1, this.value1);
         return walue1 == this.value1 ? this : new Map1(this.key1, walue1);
      }

      public int hashCode() {
         int a = 0;
         int b = 0;
         int N = 1;
         int c = 1;
         int h = MurmurHash3$.MODULE$.tuple2Hash(this.key1, this.value1);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.mapSeed();
         h = MurmurHash3$.MODULE$.mix(h, a);
         h = MurmurHash3$.MODULE$.mix(h, b);
         h = MurmurHash3$.MODULE$.mixLast(h, c);
         return MurmurHash3$.MODULE$.scala$util$hashing$MurmurHash3$$avalanche(h ^ N);
      }

      public Map1(final Object key1, final Object value1) {
         this.key1 = key1;
         this.value1 = value1;
      }
   }

   public static final class Map2 extends AbstractMap implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      public final Object scala$collection$immutable$Map$Map2$$key1;
      public final Object scala$collection$immutable$Map$Map2$$value1;
      public final Object scala$collection$immutable$Map$Map2$$key2;
      public final Object scala$collection$immutable$Map$Map2$$value2;

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

      public int size() {
         return 2;
      }

      public int knownSize() {
         return 2;
      }

      public boolean isEmpty() {
         return false;
      }

      public Object apply(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key1)) {
            return this.scala$collection$immutable$Map$Map2$$value1;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key2)) {
            return this.scala$collection$immutable$Map$Map2$$value2;
         } else {
            throw new NoSuchElementException((new StringBuilder(15)).append("key not found: ").append(key).toString());
         }
      }

      public boolean contains(final Object key) {
         return BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key1) || BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key2);
      }

      public Option get(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key1)) {
            return new Some(this.scala$collection$immutable$Map$Map2$$value1);
         } else {
            return (Option)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key2) ? new Some(this.scala$collection$immutable$Map$Map2$$value2) : None$.MODULE$);
         }
      }

      public Object getOrElse(final Object key, final Function0 default) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key1)) {
            return this.scala$collection$immutable$Map$Map2$$value1;
         } else {
            return BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key2) ? this.scala$collection$immutable$Map$Map2$$value2 : default.apply();
         }
      }

      public Iterator iterator() {
         return new Map2Iterator() {
            public Tuple2 nextResult(final Object k, final Object v) {
               return new Tuple2(k, v);
            }
         };
      }

      public Iterator keysIterator() {
         return new Map2Iterator() {
            public Object nextResult(final Object k, final Object v) {
               return k;
            }
         };
      }

      public Iterator valuesIterator() {
         return new Map2Iterator() {
            public Object nextResult(final Object k, final Object v) {
               return v;
            }
         };
      }

      public Map updated(final Object key, final Object value) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key1)) {
            return new Map2(this.scala$collection$immutable$Map$Map2$$key1, value, this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2);
         } else {
            return (Map)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key2) ? new Map2(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1, this.scala$collection$immutable$Map$Map2$$key2, value) : new Map3(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1, this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2, key, value));
         }
      }

      public Map removed(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key1)) {
            return new Map1(this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2);
         } else {
            return (Map)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map2$$key2) ? new Map1(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1) : this);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1));
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2));
      }

      public boolean exists(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1))) || BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2)));
      }

      public boolean forall(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1))) && BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2)));
      }

      public Map filterImpl(final Function1 pred, final boolean isFlipped) {
         Object k1 = null;
         Object v1 = null;
         int n = 0;
         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1))) != isFlipped) {
            k1 = this.scala$collection$immutable$Map$Map2$$key1;
            v1 = this.scala$collection$immutable$Map$Map2$$value1;
            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2))) != isFlipped) {
            if (n == 0) {
               k1 = this.scala$collection$immutable$Map$Map2$$key2;
               v1 = this.scala$collection$immutable$Map$Map2$$value2;
            }

            ++n;
         }

         switch (n) {
            case 0:
               Map$ var10000 = Map$.MODULE$;
               return Map.EmptyMap$.MODULE$;
            case 1:
               return new Map1(k1, v1);
            case 2:
               return this;
            default:
               throw new MatchError(n);
         }
      }

      public Map transform(final Function2 f) {
         Object walue1 = f.apply(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1);
         Object walue2 = f.apply(this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2);
         return walue1 == this.scala$collection$immutable$Map$Map2$$value1 && walue2 == this.scala$collection$immutable$Map$Map2$$value2 ? this : new Map2(this.scala$collection$immutable$Map$Map2$$key1, walue1, this.scala$collection$immutable$Map$Map2$$key2, walue2);
      }

      public int hashCode() {
         int a = 0;
         int b = 0;
         int N = 2;
         int c = 1;
         int h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$$value1);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$$value2);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.mapSeed();
         h = MurmurHash3$.MODULE$.mix(h, a);
         h = MurmurHash3$.MODULE$.mix(h, b);
         h = MurmurHash3$.MODULE$.mixLast(h, c);
         return MurmurHash3$.MODULE$.scala$util$hashing$MurmurHash3$$avalanche(h ^ N);
      }

      public Map2(final Object key1, final Object value1, final Object key2, final Object value2) {
         this.scala$collection$immutable$Map$Map2$$key1 = key1;
         this.scala$collection$immutable$Map$Map2$$value1 = value1;
         this.scala$collection$immutable$Map$Map2$$key2 = key2;
         this.scala$collection$immutable$Map$Map2$$value2 = value2;
      }

      private abstract class Map2Iterator extends AbstractIterator {
         private int i;
         // $FF: synthetic field
         public final Map2 $outer;

         public boolean hasNext() {
            return this.i < 2;
         }

         public Object next() {
            Object var10000;
            switch (this.i) {
               case 0:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map2$Map2Iterator$$$outer().scala$collection$immutable$Map$Map2$$key1, this.scala$collection$immutable$Map$Map2$Map2Iterator$$$outer().scala$collection$immutable$Map$Map2$$value1);
                  break;
               case 1:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map2$Map2Iterator$$$outer().scala$collection$immutable$Map$Map2$$key2, this.scala$collection$immutable$Map$Map2$Map2Iterator$$$outer().scala$collection$immutable$Map$Map2$$value2);
                  break;
               default:
                  Iterator$ var2 = Iterator$.MODULE$;
                  var10000 = Iterator$.scala$collection$Iterator$$_empty.next();
            }

            Object result = var10000;
            ++this.i;
            return result;
         }

         public Iterator drop(final int n) {
            this.i += n;
            return this;
         }

         public abstract Object nextResult(final Object k, final Object v);

         // $FF: synthetic method
         public Map2 scala$collection$immutable$Map$Map2$Map2Iterator$$$outer() {
            return this.$outer;
         }

         public Map2Iterator() {
            if (Map2.this == null) {
               throw null;
            } else {
               this.$outer = Map2.this;
               super();
               this.i = 0;
            }
         }
      }
   }

   public static class Map3 extends AbstractMap implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      public final Object scala$collection$immutable$Map$Map3$$key1;
      public final Object scala$collection$immutable$Map$Map3$$value1;
      public final Object scala$collection$immutable$Map$Map3$$key2;
      public final Object scala$collection$immutable$Map$Map3$$value2;
      public final Object scala$collection$immutable$Map$Map3$$key3;
      public final Object scala$collection$immutable$Map$Map3$$value3;

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

      public int size() {
         return 3;
      }

      public int knownSize() {
         return 3;
      }

      public boolean isEmpty() {
         return false;
      }

      public Object apply(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key1)) {
            return this.scala$collection$immutable$Map$Map3$$value1;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key2)) {
            return this.scala$collection$immutable$Map$Map3$$value2;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key3)) {
            return this.scala$collection$immutable$Map$Map3$$value3;
         } else {
            throw new NoSuchElementException((new StringBuilder(15)).append("key not found: ").append(key).toString());
         }
      }

      public boolean contains(final Object key) {
         return BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key1) || BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key2) || BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key3);
      }

      public Option get(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key1)) {
            return new Some(this.scala$collection$immutable$Map$Map3$$value1);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key2)) {
            return new Some(this.scala$collection$immutable$Map$Map3$$value2);
         } else {
            return (Option)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key3) ? new Some(this.scala$collection$immutable$Map$Map3$$value3) : None$.MODULE$);
         }
      }

      public Object getOrElse(final Object key, final Function0 default) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key1)) {
            return this.scala$collection$immutable$Map$Map3$$value1;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key2)) {
            return this.scala$collection$immutable$Map$Map3$$value2;
         } else {
            return BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key3) ? this.scala$collection$immutable$Map$Map3$$value3 : default.apply();
         }
      }

      public Iterator iterator() {
         return new Map3Iterator() {
            public Tuple2 nextResult(final Object k, final Object v) {
               return new Tuple2(k, v);
            }
         };
      }

      public Iterator keysIterator() {
         return new Map3Iterator() {
            public Object nextResult(final Object k, final Object v) {
               return k;
            }
         };
      }

      public Iterator valuesIterator() {
         return new Map3Iterator() {
            public Object nextResult(final Object k, final Object v) {
               return v;
            }
         };
      }

      public Map updated(final Object key, final Object value) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key1)) {
            return new Map3(this.scala$collection$immutable$Map$Map3$$key1, value, this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2, this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key2)) {
            return new Map3(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1, this.scala$collection$immutable$Map$Map3$$key2, value, this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3);
         } else {
            return (Map)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key3) ? new Map3(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1, this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2, this.scala$collection$immutable$Map$Map3$$key3, value) : new Map4(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1, this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2, this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3, key, value));
         }
      }

      public Map removed(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key1)) {
            return new Map2(this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2, this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key2)) {
            return new Map2(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1, this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3);
         } else {
            return (Map)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map3$$key3) ? new Map2(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1, this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2) : this);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1));
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2));
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3));
      }

      public boolean exists(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1))) || BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2))) || BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3)));
      }

      public boolean forall(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1))) && BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2))) && BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3)));
      }

      public Map filterImpl(final Function1 pred, final boolean isFlipped) {
         Object k1 = null;
         Object k2 = null;
         Object v1 = null;
         Object v2 = null;
         int n = 0;
         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1))) != isFlipped) {
            k1 = this.scala$collection$immutable$Map$Map3$$key1;
            v1 = this.scala$collection$immutable$Map$Map3$$value1;
            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2))) != isFlipped) {
            if (n == 0) {
               k1 = this.scala$collection$immutable$Map$Map3$$key2;
               v1 = this.scala$collection$immutable$Map$Map3$$value2;
            } else {
               k2 = this.scala$collection$immutable$Map$Map3$$key2;
               v2 = this.scala$collection$immutable$Map$Map3$$value2;
            }

            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3))) != isFlipped) {
            if (n == 0) {
               k1 = this.scala$collection$immutable$Map$Map3$$key3;
               v1 = this.scala$collection$immutable$Map$Map3$$value3;
            } else if (n == 1) {
               k2 = this.scala$collection$immutable$Map$Map3$$key3;
               v2 = this.scala$collection$immutable$Map$Map3$$value3;
            }

            ++n;
         }

         switch (n) {
            case 0:
               Map$ var10000 = Map$.MODULE$;
               return Map.EmptyMap$.MODULE$;
            case 1:
               return new Map1(k1, v1);
            case 2:
               return new Map2(k1, v1, k2, v2);
            case 3:
               return this;
            default:
               throw new MatchError(n);
         }
      }

      public Map transform(final Function2 f) {
         Object walue1 = f.apply(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1);
         Object walue2 = f.apply(this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2);
         Object walue3 = f.apply(this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3);
         return walue1 == this.scala$collection$immutable$Map$Map3$$value1 && walue2 == this.scala$collection$immutable$Map$Map3$$value2 && walue3 == this.scala$collection$immutable$Map$Map3$$value3 ? this : new Map3(this.scala$collection$immutable$Map$Map3$$key1, walue1, this.scala$collection$immutable$Map$Map3$$key2, walue2, this.scala$collection$immutable$Map$Map3$$key3, walue3);
      }

      public int hashCode() {
         int a = 0;
         int b = 0;
         int N = 3;
         int c = 1;
         int h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$$value1);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$$value2);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$$value3);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.mapSeed();
         h = MurmurHash3$.MODULE$.mix(h, a);
         h = MurmurHash3$.MODULE$.mix(h, b);
         h = MurmurHash3$.MODULE$.mixLast(h, c);
         return MurmurHash3$.MODULE$.scala$util$hashing$MurmurHash3$$avalanche(h ^ N);
      }

      public Map3(final Object key1, final Object value1, final Object key2, final Object value2, final Object key3, final Object value3) {
         this.scala$collection$immutable$Map$Map3$$key1 = key1;
         this.scala$collection$immutable$Map$Map3$$value1 = value1;
         this.scala$collection$immutable$Map$Map3$$key2 = key2;
         this.scala$collection$immutable$Map$Map3$$value2 = value2;
         this.scala$collection$immutable$Map$Map3$$key3 = key3;
         this.scala$collection$immutable$Map$Map3$$value3 = value3;
      }

      private abstract class Map3Iterator extends AbstractIterator {
         private int i;
         // $FF: synthetic field
         public final Map3 $outer;

         public boolean hasNext() {
            return this.i < 3;
         }

         public Object next() {
            Object var10000;
            switch (this.i) {
               case 0:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map3$Map3Iterator$$$outer().scala$collection$immutable$Map$Map3$$key1, this.scala$collection$immutable$Map$Map3$Map3Iterator$$$outer().scala$collection$immutable$Map$Map3$$value1);
                  break;
               case 1:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map3$Map3Iterator$$$outer().scala$collection$immutable$Map$Map3$$key2, this.scala$collection$immutable$Map$Map3$Map3Iterator$$$outer().scala$collection$immutable$Map$Map3$$value2);
                  break;
               case 2:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map3$Map3Iterator$$$outer().scala$collection$immutable$Map$Map3$$key3, this.scala$collection$immutable$Map$Map3$Map3Iterator$$$outer().scala$collection$immutable$Map$Map3$$value3);
                  break;
               default:
                  Iterator$ var2 = Iterator$.MODULE$;
                  var10000 = Iterator$.scala$collection$Iterator$$_empty.next();
            }

            Object result = var10000;
            ++this.i;
            return result;
         }

         public Iterator drop(final int n) {
            this.i += n;
            return this;
         }

         public abstract Object nextResult(final Object k, final Object v);

         // $FF: synthetic method
         public Map3 scala$collection$immutable$Map$Map3$Map3Iterator$$$outer() {
            return this.$outer;
         }

         public Map3Iterator() {
            if (Map3.this == null) {
               throw null;
            } else {
               this.$outer = Map3.this;
               super();
               this.i = 0;
            }
         }
      }
   }

   public static final class Map4 extends AbstractMap implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      public final Object scala$collection$immutable$Map$Map4$$key1;
      public final Object scala$collection$immutable$Map$Map4$$value1;
      public final Object scala$collection$immutable$Map$Map4$$key2;
      public final Object scala$collection$immutable$Map$Map4$$value2;
      public final Object scala$collection$immutable$Map$Map4$$key3;
      public final Object scala$collection$immutable$Map$Map4$$value3;
      public final Object scala$collection$immutable$Map$Map4$$key4;
      public final Object scala$collection$immutable$Map$Map4$$value4;

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

      public int size() {
         return 4;
      }

      public int knownSize() {
         return 4;
      }

      public boolean isEmpty() {
         return false;
      }

      public Object apply(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key1)) {
            return this.scala$collection$immutable$Map$Map4$$value1;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key2)) {
            return this.scala$collection$immutable$Map$Map4$$value2;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key3)) {
            return this.scala$collection$immutable$Map$Map4$$value3;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key4)) {
            return this.scala$collection$immutable$Map$Map4$$value4;
         } else {
            throw new NoSuchElementException((new StringBuilder(15)).append("key not found: ").append(key).toString());
         }
      }

      public boolean contains(final Object key) {
         return BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key1) || BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key2) || BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key3) || BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key4);
      }

      public Option get(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key1)) {
            return new Some(this.scala$collection$immutable$Map$Map4$$value1);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key2)) {
            return new Some(this.scala$collection$immutable$Map$Map4$$value2);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key3)) {
            return new Some(this.scala$collection$immutable$Map$Map4$$value3);
         } else {
            return (Option)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key4) ? new Some(this.scala$collection$immutable$Map$Map4$$value4) : None$.MODULE$);
         }
      }

      public Object getOrElse(final Object key, final Function0 default) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key1)) {
            return this.scala$collection$immutable$Map$Map4$$value1;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key2)) {
            return this.scala$collection$immutable$Map$Map4$$value2;
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key3)) {
            return this.scala$collection$immutable$Map$Map4$$value3;
         } else {
            return BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key4) ? this.scala$collection$immutable$Map$Map4$$value4 : default.apply();
         }
      }

      public Iterator iterator() {
         return new Map4Iterator() {
            public Tuple2 nextResult(final Object k, final Object v) {
               return new Tuple2(k, v);
            }
         };
      }

      public Iterator keysIterator() {
         return new Map4Iterator() {
            public Object nextResult(final Object k, final Object v) {
               return k;
            }
         };
      }

      public Iterator valuesIterator() {
         return new Map4Iterator() {
            public Object nextResult(final Object k, final Object v) {
               return v;
            }
         };
      }

      public Map updated(final Object key, final Object value) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key1)) {
            return new Map4(this.scala$collection$immutable$Map$Map4$$key1, value, this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2, this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3, this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key2)) {
            return new Map4(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1, this.scala$collection$immutable$Map$Map4$$key2, value, this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3, this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key3)) {
            return new Map4(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1, this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2, this.scala$collection$immutable$Map$Map4$$key3, value, this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
         } else {
            return (Map)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key4) ? new Map4(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1, this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2, this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3, this.scala$collection$immutable$Map$Map4$$key4, value) : HashMap$.MODULE$.empty().updated(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1).updated(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2).updated(this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3).updated(this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4).updated(key, value));
         }
      }

      public Map removed(final Object key) {
         if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key1)) {
            return new Map3(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2, this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3, this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key2)) {
            return new Map3(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1, this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3, this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
         } else if (BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key3)) {
            return new Map3(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1, this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2, this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
         } else {
            return (Map)(BoxesRunTime.equals(key, this.scala$collection$immutable$Map$Map4$$key4) ? new Map3(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1, this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2, this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3) : this);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1));
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2));
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3));
         f.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4));
      }

      public boolean exists(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1))) || BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2))) || BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3))) || BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4)));
      }

      public boolean forall(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1))) && BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2))) && BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3))) && BoxesRunTime.unboxToBoolean(p.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4)));
      }

      public Map filterImpl(final Function1 pred, final boolean isFlipped) {
         Object k1 = null;
         Object k2 = null;
         Object k3 = null;
         Object v1 = null;
         Object v2 = null;
         Object v3 = null;
         int n = 0;
         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1))) != isFlipped) {
            k1 = this.scala$collection$immutable$Map$Map4$$key1;
            v1 = this.scala$collection$immutable$Map$Map4$$value1;
            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2))) != isFlipped) {
            if (n == 0) {
               k1 = this.scala$collection$immutable$Map$Map4$$key2;
               v1 = this.scala$collection$immutable$Map$Map4$$value2;
            } else {
               k2 = this.scala$collection$immutable$Map$Map4$$key2;
               v2 = this.scala$collection$immutable$Map$Map4$$value2;
            }

            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3))) != isFlipped) {
            if (n == 0) {
               k1 = this.scala$collection$immutable$Map$Map4$$key3;
               v1 = this.scala$collection$immutable$Map$Map4$$value3;
            } else if (n == 1) {
               k2 = this.scala$collection$immutable$Map$Map4$$key3;
               v2 = this.scala$collection$immutable$Map$Map4$$value3;
            } else {
               k3 = this.scala$collection$immutable$Map$Map4$$key3;
               v3 = this.scala$collection$immutable$Map$Map4$$value3;
            }

            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(new Tuple2(this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4))) != isFlipped) {
            if (n == 0) {
               k1 = this.scala$collection$immutable$Map$Map4$$key4;
               v1 = this.scala$collection$immutable$Map$Map4$$value4;
            } else if (n == 1) {
               k2 = this.scala$collection$immutable$Map$Map4$$key4;
               v2 = this.scala$collection$immutable$Map$Map4$$value4;
            } else if (n == 2) {
               k3 = this.scala$collection$immutable$Map$Map4$$key4;
               v3 = this.scala$collection$immutable$Map$Map4$$value4;
            }

            ++n;
         }

         switch (n) {
            case 0:
               Map$ var10000 = Map$.MODULE$;
               return Map.EmptyMap$.MODULE$;
            case 1:
               return new Map1(k1, v1);
            case 2:
               return new Map2(k1, v1, k2, v2);
            case 3:
               return new Map3(k1, v1, k2, v2, k3, v3);
            case 4:
               return this;
            default:
               throw new MatchError(n);
         }
      }

      public Map transform(final Function2 f) {
         Object walue1 = f.apply(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1);
         Object walue2 = f.apply(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2);
         Object walue3 = f.apply(this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3);
         Object walue4 = f.apply(this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
         return walue1 == this.scala$collection$immutable$Map$Map4$$value1 && walue2 == this.scala$collection$immutable$Map$Map4$$value2 && walue3 == this.scala$collection$immutable$Map$Map4$$value3 && walue4 == this.scala$collection$immutable$Map$Map4$$value4 ? this : new Map4(this.scala$collection$immutable$Map$Map4$$key1, walue1, this.scala$collection$immutable$Map$Map4$$key2, walue2, this.scala$collection$immutable$Map$Map4$$key3, walue3, this.scala$collection$immutable$Map$Map4$$key4, walue4);
      }

      public HashMapBuilder buildTo(final HashMapBuilder builder) {
         return builder.addOne(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1).addOne(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2).addOne(this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3).addOne(this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
      }

      public int hashCode() {
         int a = 0;
         int b = 0;
         int N = 4;
         int c = 1;
         int h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$$value1);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$$value2);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$$value3);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.tuple2Hash(this.scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$$value4);
         a += h;
         b ^= h;
         c *= h | 1;
         h = MurmurHash3$.MODULE$.mapSeed();
         h = MurmurHash3$.MODULE$.mix(h, a);
         h = MurmurHash3$.MODULE$.mix(h, b);
         h = MurmurHash3$.MODULE$.mixLast(h, c);
         return MurmurHash3$.MODULE$.scala$util$hashing$MurmurHash3$$avalanche(h ^ N);
      }

      public Map4(final Object key1, final Object value1, final Object key2, final Object value2, final Object key3, final Object value3, final Object key4, final Object value4) {
         this.scala$collection$immutable$Map$Map4$$key1 = key1;
         this.scala$collection$immutable$Map$Map4$$value1 = value1;
         this.scala$collection$immutable$Map$Map4$$key2 = key2;
         this.scala$collection$immutable$Map$Map4$$value2 = value2;
         this.scala$collection$immutable$Map$Map4$$key3 = key3;
         this.scala$collection$immutable$Map$Map4$$value3 = value3;
         this.scala$collection$immutable$Map$Map4$$key4 = key4;
         this.scala$collection$immutable$Map$Map4$$value4 = value4;
      }

      private abstract class Map4Iterator extends AbstractIterator {
         private int i;
         // $FF: synthetic field
         public final Map4 $outer;

         public boolean hasNext() {
            return this.i < 4;
         }

         public Object next() {
            Object var10000;
            switch (this.i) {
               case 0:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map4$Map4Iterator$$$outer().scala$collection$immutable$Map$Map4$$key1, this.scala$collection$immutable$Map$Map4$Map4Iterator$$$outer().scala$collection$immutable$Map$Map4$$value1);
                  break;
               case 1:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map4$Map4Iterator$$$outer().scala$collection$immutable$Map$Map4$$key2, this.scala$collection$immutable$Map$Map4$Map4Iterator$$$outer().scala$collection$immutable$Map$Map4$$value2);
                  break;
               case 2:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map4$Map4Iterator$$$outer().scala$collection$immutable$Map$Map4$$key3, this.scala$collection$immutable$Map$Map4$Map4Iterator$$$outer().scala$collection$immutable$Map$Map4$$value3);
                  break;
               case 3:
                  var10000 = this.nextResult(this.scala$collection$immutable$Map$Map4$Map4Iterator$$$outer().scala$collection$immutable$Map$Map4$$key4, this.scala$collection$immutable$Map$Map4$Map4Iterator$$$outer().scala$collection$immutable$Map$Map4$$value4);
                  break;
               default:
                  Iterator$ var2 = Iterator$.MODULE$;
                  var10000 = Iterator$.scala$collection$Iterator$$_empty.next();
            }

            Object result = var10000;
            ++this.i;
            return result;
         }

         public Iterator drop(final int n) {
            this.i += n;
            return this;
         }

         public abstract Object nextResult(final Object k, final Object v);

         // $FF: synthetic method
         public Map4 scala$collection$immutable$Map$Map4$Map4Iterator$$$outer() {
            return this.$outer;
         }

         public Map4Iterator() {
            if (Map4.this == null) {
               throw null;
            } else {
               this.$outer = Map4.this;
               super();
               this.i = 0;
            }
         }
      }
   }
}
