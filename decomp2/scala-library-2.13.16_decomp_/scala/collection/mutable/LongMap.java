package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.generic.DefaultSerializationProxy;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015}f\u0001B9s\u0005eD!\"a\u0016\u0001\u0005\u0003\u0005\u000b\u0011BA-\u0011)\ty\u0006\u0001B\u0001B\u0003%\u0011\u0011\r\u0005\u000b\u0003O\u0002!\u0011!Q\u0001\n\u0005%\u0004\u0002CA8\u0001\u0011\u0005A/!\u001d\t\u000f\u0005=\u0004\u0001\"\u0001\u0002z!9\u00111\u0010\u0001\u0005R\u0005u\u0004bBAE\u0001\u0011E\u00131\u0012\u0005\b\u0003_\u0002A\u0011AAJ\u0011\u001d\ty\u0007\u0001C\u0001\u0003/Cq!a\u001c\u0001\t\u0003\tY\n\u0003\u0005\u0002\"\u0002\u0001\u000b\u0015BA1\u0011!\t\u0019\u000b\u0001Q!\n\u0005\u0005\u0004\u0002CAS\u0001\u0001\u0006K!a*\t\u0011\u00055\u0006\u0001)Q\u0005\u0003OC\u0001\"a,\u0001A\u0003&\u0011\u0011\r\u0005\t\u0003c\u0003\u0001\u0015)\u0003\u0002b!A\u00111\u0017\u0001!B\u0013\t)\f\u0003\u0005\u0002<\u0002\u0001\u000b\u0015BA_\u0011!\ty\f\u0001Q\u0005\n\u0005\u0005\u0007\u0002CAg\u0001\u0011\u0005A/a4\t\u000f\u0005E\b\u0001\"\u0011\u0002t\"9\u0011Q\u001f\u0001\u0005B\u0005M\bbBA|\u0001\u0011\u0005\u0013\u0011 \u0005\b\u0003w\u0004A\u0011IA\u007f\u0011\u001d\ty\u0010\u0001C\u0005\u0003sDqA!\u0001\u0001\t\u0013\u0011\u0019\u0001C\u0004\u0003\n\u0001!IAa\u0003\t\u000f\t=\u0001\u0001\"\u0003\u0003\u0012!9!Q\u0003\u0001\u0005\n\t]\u0001b\u0002B\u000e\u0001\u0011\u0005#Q\u0004\u0005\b\u0005G\u0001A\u0011\tB\u0013\u0011\u001d\u0011y\u0003\u0001C!\u0005cAqA!\u0013\u0001\t\u0003\u0012Y\u0005C\u0004\u0003V\u0001!\tAa\u0016\t\u000f\tm\u0003\u0001\"\u0011\u0003^!9!\u0011\t\u0001\u0005B\t\u0005\u0004b\u0002B3\u0001\u0011%!q\r\u0005\b\u0005K\u0002A\u0011\u0001B7\u0011\u001d\u0011y\u0007\u0001C!\u0005cBqA!\u001f\u0001\t\u0003\u0012Y\bC\u0004\u0003\u0002\u0002!\tAa!\t\u000f\t}\u0005\u0001\"\u0002\u0003\"\"9!q\u0014\u0001\u0005F\t=\u0006b\u0002B\\\u0001\u0011\u0005!\u0011\u0018\u0005\b\u0005{\u0003A\u0011\u0001B`\u0011\u001d\u00119\r\u0001C!\u0005\u0013DqA!4\u0001\t\u0003\u0012y\rC\u0004\u0003T\u0002!\tE!6\t\u000f\t\u0015\b\u0001\"\u0011\u0003h\"9!q\u001f\u0001\u0005B\u0005e\u0004b\u0002B}\u0001\u0011\u0005#1 \u0005\b\u0005s\u0004A\u0011IB\n\u0011\u001d\u00199\u0004\u0001C!\u0007sAqaa\u0013\u0001\t\u0003\u001ai\u0005C\u0004\u0004^\u0001!\tea\u0018\t\u000f\rM\u0004\u0001\"\u0001\u0004v!911\u0011\u0001\u0005\u0002\r\u0015\u0005bBBI\u0001\u0011\u000511\u0013\u0005\b\u0007C\u0003AQABR\u0011\u001d\u0019\t\f\u0001C\u0001\u0007gCqaa.\u0001\t\u0003\u0019I\fC\u0004\u0004L\u0002!\ta!4\t\u000f\r}\u0007\u0001\"\u0001\u0004b\"A1q\u001f\u0001!\n#\u0019I\u0010\u0003\u0005\u0004|\u0002\u0001K\u0011KB\u007f\u000f\u001d!yA\u001dE\u0001\t#1a!\u001d:\t\u0002\u0011M\u0001bBA8\u0007\u0012\u0005Aq\u0004\u0005\n\tC\u0019%\u0019!C\u0007\tGA\u0001\u0002\"\u000bDA\u00035AQ\u0005\u0005\n\tW\u0019%\u0019!C\u0007\t[A\u0001\u0002b\rDA\u00035Aq\u0006\u0005\n\tk\u0019%\u0019!C\u0007\toA\u0001\u0002\"\u0010DA\u00035A\u0011\b\u0005\n\t\u007f\u0019%\u0019!C\u0007\t\u0003B\u0001\u0002b\u0012DA\u00035A1\t\u0005\n\t\u0013\u001a%\u0019!C\u0005\t\u0017B\u0001\u0002b\u0014DA\u0003%AQ\n\u0004\u0007\t#\u001a%\u0001b\u0015\t\u000f\u0005=t\n\"\u0001\u0005f!Q1\u0011F(A\u0002\u0013\u0005A\u000fb\u001b\t\u0015\u00115t\n1A\u0005\u0002Q$y\u0007\u0003\u0005\u0005v=\u0003\u000b\u0015\u0002C2\u0011\u001d\u0011yj\u0014C!\toBq\u0001b P\t\u0003\u0011i\u0007C\u0004\u0005\u0002>#\t\u0001b!\t\u000f\u0005Ux\n\"\u0011\u0002t\"9!1L\"\u0005\u0002\u0011\u0015\u0005b\u0002CK\u0007\u0012%Aq\u0013\u0005\b\u0003w\u001cE\u0011\u0001CT\u0011\u001d!\tl\u0011C\u0001\tgCq\u0001\"1D\t\u0003!\u0019\rC\u0004\u0005V\u000e#\t\u0001b6\t\u000f\u0011\u00158\t\"\u0001\u0005h\"9AQ]\"\u0005\u0002\u0011m\bbBC\b\u0007\u0012\rQ\u0011C\u0004\t\u000bS\u0019\u0005\u0015#\u0003\u0006,\u0019AQQF\"!\u0012\u0013)y\u0003C\u0004\u0002p\t$\t!b\u000e\t\u000f\u0005m$\r\"\u0001\u0006:!9AQ\u001b2\u0005\u0002\u0015\u0005\u0003\"CB|E\u0006\u0005I\u0011BC#\u0011\u001d)If\u0011C\u0002\u000b7:q!\"\u001dD\u0011\u0013)\u0019HB\u0004\u0006v\rCI!b\u001e\t\u000f\u0005=\u0014\u000e\"\u0001\u0006|!9\u00111P5\u0005\u0002\u0015u\u0004b\u0002CkS\u0012\u0005QQ\u0011\u0005\b\u000b\u0017\u001bE1ACG\u0011\u001d)Yj\u0011C\u0002\u000b;Cq!\".D\t\u0013)9\fC\u0005\u0004x\u000e\u000b\t\u0011\"\u0003\u0006F\t9Aj\u001c8h\u001b\u0006\u0004(BA:u\u0003\u001diW\u000f^1cY\u0016T!!\u001e<\u0002\u0015\r|G\u000e\\3di&|gNC\u0001x\u0003\u0015\u00198-\u00197b\u0007\u0001)2A_A\u0006'!\u000110!\b\u0002,\u0005}\u0002#\u0002?~\u007f\u0006\u001dQ\"\u0001:\n\u0005y\u0014(aC!cgR\u0014\u0018m\u0019;NCB\u0004B!!\u0001\u0002\u00045\ta/C\u0002\u0002\u0006Y\u0014A\u0001T8oOB!\u0011\u0011BA\u0006\u0019\u0001!q!!\u0004\u0001\u0005\u0004\tyAA\u0001W#\u0011\t\t\"a\u0006\u0011\t\u0005\u0005\u00111C\u0005\u0004\u0003+1(a\u0002(pi\"Lgn\u001a\t\u0005\u0003\u0003\tI\"C\u0002\u0002\u001cY\u00141!\u00118z!)a\u0018qD@\u0002\b\u0005\r\u0012\u0011F\u0005\u0004\u0003C\u0011(AB'ba>\u00038\u000fE\u0002}\u0003KI1!a\ns\u0005\ri\u0015\r\u001d\t\u0005y\u0002\t9\u0001\u0005\u0006\u0002.\u0005=\u00121GA\u001d\u0003Si\u0011\u0001^\u0005\u0004\u0003c!(AG*ue&\u001cGo\u00149uS6L'0\u001a3Ji\u0016\u0014\u0018M\u00197f\u001fB\u001c\bcBA\u0001\u0003ky\u0018qA\u0005\u0004\u0003o1(A\u0002+va2,'\u0007E\u0002}\u0003wI1!!\u0010s\u0005!IE/\u001a:bE2,\u0007\u0003BA!\u0003#rA!a\u0011\u0002N9!\u0011QIA&\u001b\t\t9EC\u0002\u0002Ja\fa\u0001\u0010:p_Rt\u0014\"A<\n\u0007\u0005=c/A\u0004qC\u000e\\\u0017mZ3\n\t\u0005M\u0013Q\u000b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003\u001f2\u0018\u0001\u00043fM\u0006,H\u000e^#oiJL\bcBA\u0001\u00037z\u0018qA\u0005\u0004\u0003;2(!\u0003$v]\u000e$\u0018n\u001c82\u0003EIg.\u001b;jC2\u0014UO\u001a4feNK'0\u001a\t\u0005\u0003\u0003\t\u0019'C\u0002\u0002fY\u00141!\u00138u\u0003%Ig.\u001b;CY\u0006t7\u000e\u0005\u0003\u0002\u0002\u0005-\u0014bAA7m\n9!i\\8mK\u0006t\u0017A\u0002\u001fj]&$h\b\u0006\u0005\u0002*\u0005M\u0014QOA<\u0011\u001d\t9\u0006\u0002a\u0001\u00033Bq!a\u0018\u0005\u0001\u0004\t\t\u0007C\u0004\u0002h\u0011\u0001\r!!\u001b\u0015\u0005\u0005%\u0012\u0001\u00044s_6\u001c\u0006/Z2jM&\u001cG\u0003BA\u0015\u0003\u007fBq!!!\u0007\u0001\u0004\t\u0019)\u0001\u0003d_2d\u0007CBA\u0017\u0003\u000b\u000b\u0019$C\u0002\u0002\bR\u0014A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\f!C\\3x'B,7-\u001b4jG\n+\u0018\u000e\u001c3feV\u0011\u0011Q\u0012\t\by\u0006=\u00151GA\u0015\u0013\r\t\tJ\u001d\u0002\b\u0005VLG\u000eZ3s)\u0011\tI#!&\t\u000f\u0005]\u0003\u00021\u0001\u0002ZQ!\u0011\u0011FAM\u0011\u001d\ty&\u0003a\u0001\u0003C\"b!!\u000b\u0002\u001e\u0006}\u0005bBA,\u0015\u0001\u0007\u0011\u0011\f\u0005\b\u0003?R\u0001\u0019AA1\u0003\u0011i\u0017m]6\u0002\u0013\u0015DHO]1LKf\u001c\u0018!\u0003>fe>4\u0016\r\\;f!\u0011\t\t!!+\n\u0007\u0005-fO\u0001\u0004B]f\u0014VMZ\u0001\t[&tg+\u00197vK\u0006)ql]5{K\u00069qL^1dC:$\u0018!B0lKf\u001c\b#BA\u0001\u0003o{\u0018bAA]m\n)\u0011I\u001d:bs\u00069qL^1mk\u0016\u001c\bCBA\u0001\u0003o\u000b9+A\teK\u001a\fW\u000f\u001c;J]&$\u0018.\u00197ju\u0016$B!a1\u0002JB!\u0011\u0011AAc\u0013\r\t9M\u001e\u0002\u0005+:LG\u000fC\u0004\u0002LN\u0001\r!!\u0019\u0002\u00039\fA\"\u001b8ji&\fG.\u001b>f)>$\"#a1\u0002R\u0006U\u0017\u0011\\Ao\u0003C\f)/!;\u0002n\"9\u00111\u001b\u000bA\u0002\u0005\u0005\u0014!A7\t\u000f\u0005]G\u00031\u0001\u0002b\u0005\u0011Qm\u001b\u0005\b\u00037$\u0002\u0019AAT\u0003\tQh\u000fC\u0004\u0002`R\u0001\r!a*\u0002\u000554\bbBAr)\u0001\u0007\u0011\u0011M\u0001\u0003gjDq!a:\u0015\u0001\u0004\t\t'\u0001\u0002wG\"9\u00111\u001e\u000bA\u0002\u0005U\u0016AA6{\u0011\u001d\ty\u000f\u0006a\u0001\u0003{\u000b!A\u001e>\u0002\tML'0Z\u000b\u0003\u0003C\n\u0011b\u001b8po:\u001c\u0016N_3\u0002\u000f%\u001cX)\u001c9usV\u0011\u0011\u0011N\u0001\u0006K6\u0004H/_\u000b\u0003\u0003S\t!\"[7cC2\fgnY3e\u0003\u001d!x.\u00138eKb$B!!\u0019\u0003\u0006!1!q\u0001\u000eA\u0002}\f\u0011a[\u0001\ng\u0016,7.R7qif$B!!\u0019\u0003\u000e!1!qA\u000eA\u0002}\f\u0011b]3fW\u0016sGO]=\u0015\t\u0005\u0005$1\u0003\u0005\u0007\u0005\u000fa\u0002\u0019A@\u0002\u001fM,Wm[#oiJLxJ](qK:$B!!\u0019\u0003\u001a!1!qA\u000fA\u0002}\f\u0001bY8oi\u0006Lgn\u001d\u000b\u0005\u0003S\u0012y\u0002\u0003\u0004\u0003\"y\u0001\ra`\u0001\u0004W\u0016L\u0018aA4fiR!!q\u0005B\u0017!\u0019\t\tA!\u000b\u0002\b%\u0019!1\u0006<\u0003\r=\u0003H/[8o\u0011\u0019\u0011\tc\ba\u0001\u007f\u0006Iq-\u001a;Pe\u0016c7/Z\u000b\u0005\u0005g\u00119\u0004\u0006\u0004\u00036\tu\"q\b\t\u0005\u0003\u0013\u00119\u0004B\u0004\u0003:\u0001\u0012\rAa\u000f\u0003\u0005Y\u000b\u0014\u0003BA\u0004\u0003/AaA!\t!\u0001\u0004y\b\u0002\u0003B!A\u0011\u0005\rAa\u0011\u0002\u000f\u0011,g-Y;miB1\u0011\u0011\u0001B#\u0005kI1Aa\u0012w\u0005!a$-\u001f8b[\u0016t\u0014aD4fi>\u0013X\t\\:f+B$\u0017\r^3\u0015\r\u0005\u001d!Q\nB(\u0011\u0019\u0011\t#\ta\u0001\u007f\"A!\u0011K\u0011\u0005\u0002\u0004\u0011\u0019&\u0001\u0007eK\u001a\fW\u000f\u001c;WC2,X\r\u0005\u0004\u0002\u0002\t\u0015\u0013qA\u0001\nO\u0016$xJ\u001d(vY2$B!a\u0002\u0003Z!1!\u0011\u0005\u0012A\u0002}\fQ!\u00199qYf$B!a\u0002\u0003`!1!\u0011E\u0012A\u0002}$B!a\u0002\u0003d!1!\u0011\u0005\u0013A\u0002}\faA]3qC\u000e\\G\u0003BAb\u0005SBqAa\u001b&\u0001\u0004\t\t'A\u0004oK^l\u0015m]6\u0015\u0005\u0005\r\u0017a\u00019viR1!q\u0005B:\u0005kBaA!\t(\u0001\u0004y\bb\u0002B<O\u0001\u0007\u0011qA\u0001\u0006m\u0006dW/Z\u0001\u0007kB$\u0017\r^3\u0015\r\u0005\r'Q\u0010B@\u0011\u0019\u0011\t\u0003\u000ba\u0001\u007f\"9!q\u000f\u0015A\u0002\u0005\u001d\u0011\u0001\u0003\u0013qYV\u001cH%Z9\u0015\r\t\u0015%q\u0011BE\u001b\u0005\u0001\u0001B\u0002B\u0011S\u0001\u0007q\u0010C\u0004\u0003x%\u0002\r!a\u0002)\u0017%\u0012iIa%\u0003\u0016\ne%1\u0014\t\u0005\u0003\u0003\u0011y)C\u0002\u0003\u0012Z\u0014!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#Aa&\u0002MV\u001bX\r\t1bI\u0012|e.\u001a1!_J\u0004\u0003-\u001e9eCR,\u0007\rI5ogR,\u0017\rZ\u001e!S:4\u0017\u000e\u001f\u0011pa\u0016\u0014\u0018\r^5p]N\u0004s/\u001b;iA\u0005t\u0007e\u001c9fe\u0006tG\rI8gA5,H\u000e^5qY\u0016\u0004\u0013M]4tA]LG\u000e\u001c\u0011cK\u0002\"W\r\u001d:fG\u0006$X\rZ\u0001\u0006g&t7-Z\u0011\u0003\u0005;\u000baA\r\u00182g9\u001a\u0014AB1eI>sW\r\u0006\u0004\u0003\u0006\n\r&Q\u0015\u0005\u0007\u0005CQ\u0003\u0019A@\t\u000f\t]$\u00061\u0001\u0002\b!\u001a!F!+\u0011\t\u0005\u0005!1V\u0005\u0004\u0005[3(AB5oY&tW\r\u0006\u0003\u0003\u0006\nE\u0006b\u0002BZW\u0001\u0007\u00111G\u0001\u0003WZD3a\u000bBU\u0003-\u0019XO\u0019;sC\u000e$xJ\\3\u0015\t\t\u0015%1\u0018\u0005\u0007\u0005Ca\u0003\u0019A@\u0002\u0011%$XM]1u_J,\"A!1\u0011\r\u00055\"1YA\u001a\u0013\r\u0011)\r\u001e\u0002\t\u0013R,'/\u0019;pe\u0006a1.Z=t\u0013R,'/\u0019;peV\u0011!1\u001a\t\u0006\u0003[\u0011\u0019m`\u0001\u000fm\u0006dW/Z:Ji\u0016\u0014\u0018\r^8s+\t\u0011\t\u000e\u0005\u0004\u0002.\t\r\u0017qA\u0001\bM>\u0014X-Y2i+\u0011\u00119N!9\u0015\t\u0005\r'\u0011\u001c\u0005\b\u00057\u0004\u0004\u0019\u0001Bo\u0003\u00051\u0007\u0003CA\u0001\u00037\n\u0019Da8\u0011\t\u0005%!\u0011\u001d\u0003\b\u0005G\u0004$\u0019AA\b\u0005\u0005)\u0016\u0001\u00044pe\u0016\f7\r[#oiJLX\u0003\u0002Bu\u0005k$B!a1\u0003l\"9!1\\\u0019A\u0002\t5\b#CA\u0001\u0005_|\u0018q\u0001Bz\u0013\r\u0011\tP\u001e\u0002\n\rVt7\r^5p]J\u0002B!!\u0003\u0003v\u00129!1]\u0019C\u0002\u0005=\u0011!B2m_:,\u0017!\u0002\u0013qYV\u001cX\u0003\u0002B\u007f\u0007\u0007!BAa@\u0004\u0006A!A\u0010AB\u0001!\u0011\tIaa\u0001\u0005\u000f\te2G1\u0001\u0003<!9!1W\u001aA\u0002\r\u001d\u0001cBA\u0001\u0003ky8\u0011\u0001\u0015\fg\t5%1SB\u0006\u00053\u001by!\t\u0002\u0004\u000e\u0005q4i\u001c8tS\u0012,'\u000f\t:fcVL'/\u001b8hA\u0005t\u0007%[7nkR\f'\r\\3!\u001b\u0006\u0004\be\u001c:!M\u0006dG\u000e\t2bG.\u0004Co\u001c\u0011NCBt3m\u001c8dCR\f#a!\u0005\u0002\rIr\u0013g\r\u00181+\u0011\u0019)ba\u0007\u0015\u0011\r]1QDB\u0012\u0007O\u0001B\u0001 \u0001\u0004\u001aA!\u0011\u0011BB\u000e\t\u001d\u0011I\u0004\u000eb\u0001\u0005wAqaa\b5\u0001\u0004\u0019\t#A\u0003fY\u0016l\u0017\u0007E\u0004\u0002\u0002\u0005Urp!\u0007\t\u000f\r\u0015B\u00071\u0001\u0004\"\u0005)Q\r\\3ne!91\u0011\u0006\u001bA\u0002\r-\u0012!B3mK6\u001c\bCBA\u0001\u0007[\u0019\t#C\u0002\u00040Y\u0014!\u0002\u0010:fa\u0016\fG/\u001a3?Q-!$Q\u0012BJ\u0007g\u0011Ija\u0004\"\u0005\rU\u0012!R+tK\u0002Z3\u0006I<ji\"\u0004\u0013M\u001c\u0011fqBd\u0017nY5uA\r|G\u000e\\3di&|g\u000eI1sOVlWM\u001c;!S:\u001cH/Z1eA=4\u0007e\u000b\u0011xSRD\u0007E^1sCJ<7/\u0001\u0004d_:\u001c\u0017\r^\u000b\u0005\u0007w\u0019\t\u0005\u0006\u0003\u0004>\r\r\u0003\u0003\u0002?\u0001\u0007\u007f\u0001B!!\u0003\u0004B\u00119!\u0011H\u001bC\u0002\tm\u0002bBB#k\u0001\u00071qI\u0001\u0003qN\u0004b!!\f\u0002\u0006\u000e%\u0003cBA\u0001\u0003ky8qH\u0001\u000bIAdWo\u001d\u0013qYV\u001cX\u0003BB(\u0007+\"Ba!\u0015\u0004XA!A\u0010AB*!\u0011\tIa!\u0016\u0005\u000f\tebG1\u0001\u0003<!91Q\t\u001cA\u0002\re\u0003CBA\u0017\u0003\u000b\u001bY\u0006E\u0004\u0002\u0002\u0005Urpa\u0015\u0002\u000fU\u0004H-\u0019;fIV!1\u0011MB4)\u0019\u0019\u0019g!\u001b\u0004lA!A\u0010AB3!\u0011\tIaa\u001a\u0005\u000f\terG1\u0001\u0003<!1!\u0011E\u001cA\u0002}DqAa\u001e8\u0001\u0004\u0019)\u0007K\u00068\u0005\u001b\u0013\u0019ja\u001c\u0003\u001a\u000e=\u0011EAB9\u0003Q*6/\u001a\u0011n]\rdwN\\3)S9\nG\rZ(oK\"ZGF^\u0015!S:\u001cH/Z1eA=4\u0007%\u001c\u0018va\u0012\fG/\u001a3)W2\u0002c/K\u0001\u000bM>\u0014X-Y2i\u0017\u0016LX\u0003BB<\u0007\u007f\"B!a1\u0004z!9!1\u001c\u001dA\u0002\rm\u0004cBA\u0001\u00037z8Q\u0010\t\u0005\u0003\u0013\u0019y\bB\u0004\u0004\u0002b\u0012\r!a\u0004\u0003\u0003\u0005\u000bABZ8sK\u0006\u001c\u0007NV1mk\u0016,Baa\"\u0004\u0010R!\u00111YBE\u0011\u001d\u0011Y.\u000fa\u0001\u0007\u0017\u0003\u0002\"!\u0001\u0002\\\u0005\u001d1Q\u0012\t\u0005\u0003\u0013\u0019y\tB\u0004\u0004\u0002f\u0012\r!a\u0004\u0002\u00195\f\u0007OV1mk\u0016\u001chj\\<\u0016\t\rU51\u0014\u000b\u0005\u0007/\u001bi\n\u0005\u0003}\u0001\re\u0005\u0003BA\u0005\u00077#qA!\u000f;\u0005\u0004\ty\u0001C\u0004\u0003\\j\u0002\raa(\u0011\u0011\u0005\u0005\u00111LA\u0004\u00073\u000bq\u0002\u001e:b]N4wN]7WC2,Xm\u001d\u000b\u0005\u0005\u000b\u001b)\u000bC\u0004\u0003\\n\u0002\raa*\u0011\u0011\u0005\u0005\u00111LA\u0004\u0003\u000fA3b\u000fBG\u0005'\u001bYK!'\u0004\u0010\u0005\u00121QV\u00016+N,\u0007\u0005\u001e:b]N4wN]7WC2,Xm]%o!2\f7-\u001a\u0011j]N$X-\u00193!_\u001a\u0004CO]1og\u001a|'/\u001c,bYV,7\u000fK\u0002<\u0005S\u000ba\u0003\u001e:b]N4wN]7WC2,Xm]%o!2\f7-\u001a\u000b\u0005\u0005\u000b\u001b)\fC\u0004\u0003\\r\u0002\raa*\u0002\u00075\f\u0007/\u0006\u0003\u0004<\u000e\u0005G\u0003BB_\u0007\u000b\u0004B\u0001 \u0001\u0004@B!\u0011\u0011BBa\t\u001d\u0019\u0019-\u0010b\u0001\u0003\u001f\u0011!A\u0016\u001a\t\u000f\tmW\b1\u0001\u0004HBA\u0011\u0011AA.\u0003g\u0019I\rE\u0004\u0002\u0002\u0005Urpa0\u0002\u000f\u0019d\u0017\r^'baV!1qZBk)\u0011\u0019\tna6\u0011\tq\u000411\u001b\t\u0005\u0003\u0013\u0019)\u000eB\u0004\u0004Dz\u0012\r!a\u0004\t\u000f\tmg\b1\u0001\u0004ZBA\u0011\u0011AA.\u0003g\u0019Y\u000e\u0005\u0004\u0002.\u0005\u00155Q\u001c\t\b\u0003\u0003\t)d`Bj\u0003\u001d\u0019w\u000e\u001c7fGR,Baa9\u0004jR!1Q]Bv!\u0011a\baa:\u0011\t\u0005%1\u0011\u001e\u0003\b\u0007\u0007|$\u0019AA\b\u0011\u001d\u0019io\u0010a\u0001\u0007_\f!\u0001\u001d4\u0011\u0011\u0005\u00051\u0011_A\u001a\u0007kL1aa=w\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007cBA\u0001\u0003ky8q]\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003O\u000b\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\r}\b\u0003\u0002C\u0001\t\u0017i!\u0001b\u0001\u000b\t\u0011\u0015AqA\u0001\u0005Y\u0006twM\u0003\u0002\u0005\n\u0005!!.\u0019<b\u0013\u0011!i\u0001b\u0001\u0003\rM#(/\u001b8h\u0003\u001dauN\\4NCB\u0004\"\u0001`\"\u0014\u000b\r\u000b9\u000b\"\u0006\u0011\t\u0011]AQD\u0007\u0003\t3QA\u0001b\u0007\u0005\b\u0005\u0011\u0011n\\\u0005\u0005\u0003'\"I\u0002\u0006\u0002\u0005\u0012\u0005I\u0011J\u001c3fq6\u000b7o[\u000b\u0003\tKy!\u0001b\n\u001e\t}z\u0000\u0000\u0000\u0001\u000b\u0013:$W\r_'bg.\u0004\u0013AC'jgNLgn\u001a\"jiV\u0011AqF\b\u0003\tciB\u0001!\u0001\u0001\u0001\u0005YQ*[:tS:<')\u001b;!\u0003%1\u0016mY1oi\nKG/\u0006\u0002\u0005:=\u0011A1H\u000f\u0005\u0001\u0002\u0001\u0001!\u0001\u0006WC\u000e\fg\u000e\u001e\"ji\u0002\n!\"T5tgZ\u000b7-\u00198u+\t!\u0019e\u0004\u0002\u0005Fu!\u0001\u0019\u0001\u0001\u0001\u0003-i\u0015n]:WC\u000e\fg\u000e\u001e\u0011\u0002!\u0015D8-\u001a9uS>tG)\u001a4bk2$XC\u0001C'!\u001d\t\t!a\u0017\u0000\u0003#\t\u0011#\u001a=dKB$\u0018n\u001c8EK\u001a\fW\u000f\u001c;!\u00059auN\\4NCB\u0014U/\u001b7eKJ,B\u0001\"\u0016\u0005bM)q*a*\u0005XA9A\u0010\"\u0017\u0005^\u0011\r\u0014b\u0001C.e\ny!+Z;tC\ndWMQ;jY\u0012,'\u000fE\u0004\u0002\u0002\u0005Ur\u0010b\u0018\u0011\t\u0005%A\u0011\r\u0003\b\u0003\u001by%\u0019AA\b!\u0011a\b\u0001b\u0018\u0015\u0005\u0011\u001d\u0004#\u0002C5\u001f\u0012}S\"A\"\u0016\u0005\u0011\r\u0014!C3mK6\u001cx\fJ3r)\u0011\t\u0019\r\"\u001d\t\u0013\u0011M$+!AA\u0002\u0011\r\u0014a\u0001=%c\u00051Q\r\\3ng\u0002\"B\u0001\"\u001f\u0005|5\tq\nC\u0004\u0005~Q\u0003\r\u0001\"\u0018\u0002\u000b\u0015tGO]=\u0002\u000b\rdW-\u0019:\u0002\rI,7/\u001e7u)\t!\u0019'\u0006\u0003\u0005\b\u00125E\u0003\u0002CE\t\u001f\u0003B\u0001 \u0001\u0005\fB!\u0011\u0011\u0002CG\t\u001d\ti\u0001\u0017b\u0001\u0003\u001fAqa!\u000bY\u0001\u0004!\t\n\u0005\u0004\u0002\u0002\r5B1\u0013\t\b\u0003\u0003\t)d CF\u0003U\u0011W/\u001b7e\rJ|W.\u0013;fe\u0006\u0014G.Z(oG\u0016,B\u0001\"'\u0005 R!A1\u0014CQ!\u0011a\b\u0001\"(\u0011\t\u0005%Aq\u0014\u0003\b\u0003\u001bI&\u0019AA\b\u0011\u001d\u0019I#\u0017a\u0001\tG\u0003b!!\f\u0002\u0006\u0012\u0015\u0006cBA\u0001\u0003kyHQT\u000b\u0005\tS#y+\u0006\u0002\u0005,B!A\u0010\u0001CW!\u0011\tI\u0001b,\u0005\u000f\u00055!L1\u0001\u0002\u0010\u0005Yq/\u001b;i\t\u00164\u0017-\u001e7u+\u0011!)\fb/\u0015\t\u0011]FQ\u0018\t\u0005y\u0002!I\f\u0005\u0003\u0002\n\u0011mFaBA\u00077\n\u0007\u0011q\u0002\u0005\b\u0005\u0003Z\u0006\u0019\u0001C`!\u001d\t\t!a\u0017\u0000\ts\u000bAA\u001a:p[V!AQ\u0019Cf)\u0011!9\r\"4\u0011\tq\u0004A\u0011\u001a\t\u0005\u0003\u0013!Y\rB\u0004\u0002\u000eq\u0013\r!a\u0004\t\u000f\u0011=G\f1\u0001\u0005R\u000611o\\;sG\u0016\u0004b!!\f\u0002\u0006\u0012M\u0007cBA\u0001\u0003kyH\u0011Z\u0001\u000b]\u0016<()^5mI\u0016\u0014X\u0003\u0002Cm\tC,\"\u0001b7\u0011\u000fq$I\u0006\"8\u0005dB9\u0011\u0011AA\u001b\u007f\u0012}\u0007\u0003BA\u0005\tC$q!!\u0004^\u0005\u0004\ty\u0001\u0005\u0003}\u0001\u0011}\u0017a\u00024s_6T\u0016\u000e]\u000b\u0005\tS$y\u000f\u0006\u0004\u0005l\u0012EHQ\u001f\t\u0005y\u0002!i\u000f\u0005\u0003\u0002\n\u0011=HaBA\u0007=\n\u0007\u0011q\u0002\u0005\b\tgt\u0006\u0019AA[\u0003\u0011YW-_:\t\u000f\u0011]h\f1\u0001\u0005z\u00061a/\u00197vKN\u0004b!!\u0001\u00028\u00125X\u0003\u0002C\u007f\u000b\u0007!b\u0001b@\u0006\u0006\u0015-\u0001\u0003\u0002?\u0001\u000b\u0003\u0001B!!\u0003\u0006\u0004\u00119\u0011QB0C\u0002\u0005=\u0001b\u0002Cz?\u0002\u0007Qq\u0001\t\u0006\u0003[)Ia`\u0005\u0004\u0003{!\bb\u0002C|?\u0002\u0007QQ\u0002\t\u0007\u0003[)I!\"\u0001\u0002\u0013Q|g)Y2u_JLX\u0003BC\n\u000b?!B!\"\u0006\u0006$AA\u0011QFC\f\u000b7)\t#C\u0002\u0006\u001aQ\u0014qAR1di>\u0014\u0018\u0010E\u0004\u0002\u0002\u0005Ur0\"\b\u0011\t\u0005%Qq\u0004\u0003\b\u0003\u001b\u0001'\u0019AA\b!\u0011a\b!\"\b\t\u000f\u0015\u0015\u0002\r1\u0001\u0006(\u0005)A-^7ns:\u0011APQ\u0001\n)>4\u0015m\u0019;pef\u00042\u0001\"\u001bc\u0005%!vNR1di>\u0014\u0018pE\u0004c\u0003O+\t$a\u0010\u0011\u0011\u00055RqCC\u001a\u000bk\u0001r!!\u0001\u00026}\f9\u000b\u0005\u0003}\u0001\u0005\u001dFCAC\u0016)\u0011))$b\u000f\t\u000f\u0015uB\r1\u0001\u0006@\u0005\u0011\u0011\u000e\u001e\t\u0007\u0003[\t))b\r\u0016\u0005\u0015\r\u0003c\u0002?\u0002\u0010\u0016MRQ\u0007\u000b\u0003\u000b\u000f\u0002B\u0001\"\u0001\u0006J%!Q1\nC\u0002\u0005\u0019y%M[3di\":!-b\u0014\u0003x\u0015U\u0003\u0003BA\u0001\u000b#J1!b\u0015w\u0005A\u0019VM]5bYZ+'o]5p]VKEIH\u0001\u0004Q\u001d\tWq\nB<\u000b+\n1\u0002^8Ck&dGM\u0012:p[V!QQLC5)\u0011)y&\"\u001c\u0011\u0015\u00055R\u0011MA\f\u000bK*Y'C\u0002\u0006dQ\u0014\u0011BQ;jY\u00124%o\\7\u0011\u000f\u0005\u0005\u0011QG@\u0006hA!\u0011\u0011BC5\t\u001d\tia\u001ab\u0001\u0003\u001f\u0001B\u0001 \u0001\u0006h!9QqN4A\u0002\u0015\u001d\u0012a\u00024bGR|'/_\u0001\f)>\u0014U/\u001b7e\rJ|W\u000eE\u0002\u0005j%\u00141\u0002V8Ck&dGM\u0012:p[N)\u0011.a*\u0006zAQ\u0011QFC1\u0003/)\u0019$\"\u000e\u0015\u0005\u0015MD\u0003BC@\u000b\u0007#B!\"\u000e\u0006\u0002\"9QQH6A\u0002\u0015}\u0002b\u0002CaW\u0002\u0007\u0011q\u0003\u000b\u0005\u000b\u000f+I\tE\u0004}\t3*\u0019$\"\u000e\t\u000f\u0011\u0005G\u000e1\u0001\u0002\u0018\u0005y\u0011\u000e^3sC\ndWMR1di>\u0014\u00180\u0006\u0003\u0006\u0010\u0016]UCACI!!\ti#b\u0006\u0006\u0014\u0016e\u0005cBA\u0001\u0003kyXQ\u0013\t\u0005\u0003\u0013)9\nB\u0004\u0002\u000e5\u0014\r!a\u0004\u0011\tq\u0004QQS\u0001\u0011EVLG\u000e\u001a$s_6duN\\4NCB,B!b(\u00062V\u0011Q\u0011\u0015\t\u000b\u0003[)\t'b)\u0006.\u0016M\u0006\u0007BCS\u000bS\u0003B\u0001 \u0001\u0006(B!\u0011\u0011BCU\t-)YK\\A\u0001\u0002\u0003\u0015\t!a\u0004\u0003\u0007}#\u0013\u0007E\u0004\u0002\u0002\u0005Ur0b,\u0011\t\u0005%Q\u0011\u0017\u0003\b\u0003\u001bq'\u0019AA\b!\u0011a\b!b,\u0002\u0015I,\u0007/Y2l\u001b\u0006\u001c8\u000e\u0006\u0005\u0002b\u0015eV1XC_\u0011\u001d\t\tk\u001ca\u0001\u0003CBq!a,p\u0001\u0004\t\t\u0007C\u0004\u00022>\u0004\r!!\u0019"
)
public final class LongMap extends AbstractMap implements StrictOptimizedIterableOps, Serializable {
   private final Function1 defaultEntry;
   private int mask;
   public int scala$collection$mutable$LongMap$$extraKeys;
   public Object scala$collection$mutable$LongMap$$zeroValue;
   public Object scala$collection$mutable$LongMap$$minValue;
   private int _size;
   private int _vacant;
   public long[] scala$collection$mutable$LongMap$$_keys;
   public Object[] scala$collection$mutable$LongMap$$_values;

   public static BuildFrom buildFromLongMap() {
      LongMap$ var10000 = LongMap$.MODULE$;
      return LongMap.ToBuildFrom$.MODULE$;
   }

   public static BuildFrom toBuildFrom(final LongMap$ factory) {
      LongMap$ var10000 = LongMap$.MODULE$;
      return LongMap.ToBuildFrom$.MODULE$;
   }

   public static Factory toFactory(final LongMap$ dummy) {
      LongMap$ var10000 = LongMap$.MODULE$;
      return LongMap.ToFactory$.MODULE$;
   }

   public static LongMap fromZip(final scala.collection.Iterable keys, final scala.collection.Iterable values) {
      return LongMap$.MODULE$.fromZip(keys, values);
   }

   public static LongMap fromZip(final long[] keys, final Object values) {
      return LongMap$.MODULE$.fromZip(keys, values);
   }

   public static ReusableBuilder newBuilder() {
      LongMap$ var10000 = LongMap$.MODULE$;
      return new LongMapBuilder();
   }

   public static LongMap from(final IterableOnce source) {
      return LongMap$.MODULE$.from(source);
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

   public LongMap fromSpecific(final IterableOnce coll) {
      Builder b = this.newSpecificBuilder();
      if (b == null) {
         throw null;
      } else {
         b.sizeHint(coll, 0);
         b.addAll(coll);
         return (LongMap)b.result();
      }
   }

   public Builder newSpecificBuilder() {
      LongMap$ var10002 = LongMap$.MODULE$;
      return new GrowableBuilder(new LongMap());
   }

   private void defaultInitialize(final int n) {
      this.mask = n < 0 ? 7 : (1 << 32 - Integer.numberOfLeadingZeros(n - 1)) - 1 & 1073741823 | 7;
      this.scala$collection$mutable$LongMap$$_keys = new long[this.mask + 1];
      this.scala$collection$mutable$LongMap$$_values = new Object[this.mask + 1];
   }

   public void initializeTo(final int m, final int ek, final Object zv, final Object mv, final int sz, final int vc, final long[] kz, final Object[] vz) {
      this.mask = m;
      this.scala$collection$mutable$LongMap$$extraKeys = ek;
      this.scala$collection$mutable$LongMap$$zeroValue = zv;
      this.scala$collection$mutable$LongMap$$minValue = mv;
      this._size = sz;
      this._vacant = vc;
      this.scala$collection$mutable$LongMap$$_keys = kz;
      this.scala$collection$mutable$LongMap$$_values = vz;
   }

   public int size() {
      return this._size + (this.scala$collection$mutable$LongMap$$extraKeys + 1) / 2;
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public LongMap empty() {
      return new LongMap();
   }

   private boolean imbalanced() {
      return (double)(this._size + this._vacant) > (double)0.5F * (double)this.mask || this._vacant > this._size;
   }

   private int toIndex(final long k) {
      int h = (int)((k ^ k >>> 32) & 4294967295L);
      int x = (h ^ h >>> 16) * -2048144789;
      return (x ^ x >>> 13) & this.mask;
   }

   private int seekEmpty(final long k) {
      int e = this.toIndex(k);

      for(int x = 0; this.scala$collection$mutable$LongMap$$_keys[e] != 0L; e = e + 2 * (x + 1) * x - 3 & this.mask) {
         ++x;
      }

      return e;
   }

   private int seekEntry(final long k) {
      int e = this.toIndex(k);
      int x = 0;

      while(true) {
         long q = this.scala$collection$mutable$LongMap$$_keys[e];
         if (q == k) {
            return e;
         }

         if (q == 0L) {
            return e | Integer.MIN_VALUE;
         }

         ++x;
         e = e + 2 * (x + 1) * x - 3 & this.mask;
      }
   }

   private int seekEntryOrOpen(final long k) {
      int e = this.toIndex(k);
      int x = 0;

      while(true) {
         long q = this.scala$collection$mutable$LongMap$$_keys[e];
         if (q == k) {
            return e;
         }

         if (q + q == 0L) {
            if (q == 0L) {
               return e | Integer.MIN_VALUE;
            }

            int o = e | -1073741824;

            while(true) {
               q = this.scala$collection$mutable$LongMap$$_keys[e];
               if (q == k) {
                  return e;
               }

               if (q == 0L) {
                  return o;
               }

               ++x;
               e = e + 2 * (x + 1) * x - 3 & this.mask;
            }
         }

         ++x;
         e = e + 2 * (x + 1) * x - 3 & this.mask;
      }
   }

   public boolean contains(final long key) {
      if (key == -key) {
         return ((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) != 0;
      } else {
         return this.seekEntry(key) >= 0;
      }
   }

   public Option get(final long key) {
      if (key == -key) {
         if (((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) == 0) {
            return None$.MODULE$;
         } else {
            return key == 0L ? new Some(this.scala$collection$mutable$LongMap$$zeroValue) : new Some(this.scala$collection$mutable$LongMap$$minValue);
         }
      } else {
         int i = this.seekEntry(key);
         return (Option)(i < 0 ? None$.MODULE$ : new Some(this.scala$collection$mutable$LongMap$$_values[i]));
      }
   }

   public Object getOrElse(final long key, final Function0 default) {
      if (key == -key) {
         if (((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) == 0) {
            return default.apply();
         } else {
            return key == 0L ? this.scala$collection$mutable$LongMap$$zeroValue : this.scala$collection$mutable$LongMap$$minValue;
         }
      } else {
         int i = this.seekEntry(key);
         return i < 0 ? default.apply() : this.scala$collection$mutable$LongMap$$_values[i];
      }
   }

   public Object getOrElseUpdate(final long key, final Function0 defaultValue) {
      if (key == -key) {
         int kbits = (int)(key >>> 63) + 1;
         if ((kbits & this.scala$collection$mutable$LongMap$$extraKeys) == 0) {
            Object value = defaultValue.apply();
            this.scala$collection$mutable$LongMap$$extraKeys |= kbits;
            if (key == 0L) {
               this.scala$collection$mutable$LongMap$$zeroValue = value;
            } else {
               this.scala$collection$mutable$LongMap$$minValue = value;
            }

            return value;
         } else {
            return key == 0L ? this.scala$collection$mutable$LongMap$$zeroValue : this.scala$collection$mutable$LongMap$$minValue;
         }
      } else {
         int i = this.seekEntryOrOpen(key);
         if (i >= 0) {
            return this.scala$collection$mutable$LongMap$$_values[i];
         } else {
            long[] oks = this.scala$collection$mutable$LongMap$$_keys;
            int j = i & 1073741823;
            long ok = oks[j];
            Object ans = defaultValue.apply();
            if (oks != this.scala$collection$mutable$LongMap$$_keys || ok != this.scala$collection$mutable$LongMap$$_keys[j]) {
               i = this.seekEntryOrOpen(key);
               if (i >= 0) {
                  --this._size;
               }
            }

            ++this._size;
            int j = i & 1073741823;
            this.scala$collection$mutable$LongMap$$_keys[j] = key;
            this.scala$collection$mutable$LongMap$$_values[j] = ans;
            if ((i & 1073741824) != 0) {
               --this._vacant;
            } else if (this.imbalanced()) {
               this.repack();
            }

            return ans;
         }
      }
   }

   public Object getOrNull(final long key) {
      if (key == -key) {
         if (((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) == 0) {
            return null;
         } else {
            return key == 0L ? this.scala$collection$mutable$LongMap$$zeroValue : this.scala$collection$mutable$LongMap$$minValue;
         }
      } else {
         int i = this.seekEntry(key);
         return i < 0 ? null : this.scala$collection$mutable$LongMap$$_values[i];
      }
   }

   public Object apply(final long key) {
      if (key == -key) {
         if (((int)(key >>> 63) + 1 & this.scala$collection$mutable$LongMap$$extraKeys) == 0) {
            return this.defaultEntry.apply(key);
         } else {
            return key == 0L ? this.scala$collection$mutable$LongMap$$zeroValue : this.scala$collection$mutable$LongMap$$minValue;
         }
      } else {
         int i = this.seekEntry(key);
         return i < 0 ? this.defaultEntry.apply(key) : this.scala$collection$mutable$LongMap$$_values[i];
      }
   }

   public Object default(final long key) {
      return this.defaultEntry.apply(key);
   }

   private void repack(final int newMask) {
      long[] ok = this.scala$collection$mutable$LongMap$$_keys;
      Object[] ov = this.scala$collection$mutable$LongMap$$_values;
      this.mask = newMask;
      this.scala$collection$mutable$LongMap$$_keys = new long[this.mask + 1];
      this.scala$collection$mutable$LongMap$$_values = new Object[this.mask + 1];
      this._vacant = 0;

      for(int i = 0; i < ok.length; ++i) {
         long k = ok[i];
         if (k != -k) {
            int j = this.seekEmpty(k);
            this.scala$collection$mutable$LongMap$$_keys[j] = k;
            this.scala$collection$mutable$LongMap$$_values[j] = ov[i];
         }
      }

   }

   public void repack() {
      this.repack(LongMap$.MODULE$.scala$collection$mutable$LongMap$$repackMask(this.mask, this._size, this._vacant));
   }

   public Option put(final long key, final Object value) {
      if (key == -key) {
         if (key == 0L) {
            Option ans = (Option)((this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? new Some(this.scala$collection$mutable$LongMap$$zeroValue) : None$.MODULE$);
            this.scala$collection$mutable$LongMap$$zeroValue = value;
            this.scala$collection$mutable$LongMap$$extraKeys |= 1;
            return ans;
         } else {
            Option ans = (Option)((this.scala$collection$mutable$LongMap$$extraKeys & 2) == 1 ? new Some(this.scala$collection$mutable$LongMap$$minValue) : None$.MODULE$);
            this.scala$collection$mutable$LongMap$$minValue = value;
            this.scala$collection$mutable$LongMap$$extraKeys |= 2;
            return ans;
         }
      } else {
         int i = this.seekEntryOrOpen(key);
         if (i < 0) {
            int j = i & 1073741823;
            this.scala$collection$mutable$LongMap$$_keys[j] = key;
            this.scala$collection$mutable$LongMap$$_values[j] = value;
            ++this._size;
            if ((i & 1073741824) != 0) {
               --this._vacant;
            } else if (this.imbalanced()) {
               this.repack();
            }

            return None$.MODULE$;
         } else {
            Some ans = new Some(this.scala$collection$mutable$LongMap$$_values[i]);
            this.scala$collection$mutable$LongMap$$_keys[i] = key;
            this.scala$collection$mutable$LongMap$$_values[i] = value;
            return ans;
         }
      }
   }

   public void update(final long key, final Object value) {
      if (key == -key) {
         if (key == 0L) {
            this.scala$collection$mutable$LongMap$$zeroValue = value;
            this.scala$collection$mutable$LongMap$$extraKeys |= 1;
         } else {
            this.scala$collection$mutable$LongMap$$minValue = value;
            this.scala$collection$mutable$LongMap$$extraKeys |= 2;
         }
      } else {
         int i = this.seekEntryOrOpen(key);
         if (i < 0) {
            int j = i & 1073741823;
            this.scala$collection$mutable$LongMap$$_keys[j] = key;
            this.scala$collection$mutable$LongMap$$_values[j] = value;
            ++this._size;
            if ((i & 1073741824) != 0) {
               --this._vacant;
            } else if (this.imbalanced()) {
               this.repack();
            }
         } else {
            this.scala$collection$mutable$LongMap$$_keys[i] = key;
            this.scala$collection$mutable$LongMap$$_values[i] = value;
         }
      }
   }

   /** @deprecated */
   public LongMap $plus$eq(final long key, final Object value) {
      this.update(key, value);
      return this;
   }

   public final LongMap addOne(final long key, final Object value) {
      this.update(key, value);
      return this;
   }

   public final LongMap addOne(final Tuple2 kv) {
      this.update(kv._1$mcJ$sp(), kv._2());
      return this;
   }

   public LongMap subtractOne(final long key) {
      if (key == -key) {
         if (key == 0L) {
            this.scala$collection$mutable$LongMap$$extraKeys &= 2;
            this.scala$collection$mutable$LongMap$$zeroValue = null;
         } else {
            this.scala$collection$mutable$LongMap$$extraKeys &= 1;
            this.scala$collection$mutable$LongMap$$minValue = null;
         }
      } else {
         int i = this.seekEntry(key);
         if (i >= 0) {
            --this._size;
            ++this._vacant;
            this.scala$collection$mutable$LongMap$$_keys[i] = Long.MIN_VALUE;
            this.scala$collection$mutable$LongMap$$_values[i] = null;
         }
      }

      return this;
   }

   public Iterator iterator() {
      return new AbstractIterator() {
         private final long[] kz;
         private final Object[] vz;
         private Tuple2 nextPair;
         private Tuple2 anotherPair;
         private int index;

         public boolean hasNext() {
            if (this.nextPair == null) {
               if (this.index >= this.kz.length) {
                  return false;
               }

               for(long q = this.kz[this.index]; q == -q; q = this.kz[this.index]) {
                  ++this.index;
                  if (this.index >= this.kz.length) {
                     return false;
                  }
               }

               this.nextPair = new Tuple2(this.kz[this.index], this.vz[this.index]);
               ++this.index;
            }

            return true;
         }

         public Tuple2 next() {
            if (this.nextPair == null && !this.hasNext()) {
               throw new NoSuchElementException("next");
            } else {
               Tuple2 ans = this.nextPair;
               if (this.anotherPair != null) {
                  this.nextPair = this.anotherPair;
                  this.anotherPair = null;
               } else {
                  this.nextPair = null;
               }

               return ans;
            }
         }

         public {
            this.kz = LongMap.this.scala$collection$mutable$LongMap$$_keys;
            this.vz = LongMap.this.scala$collection$mutable$LongMap$$_values;
            this.nextPair = LongMap.this.scala$collection$mutable$LongMap$$extraKeys == 0 ? null : ((LongMap.this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? new Tuple2(0L, LongMap.this.scala$collection$mutable$LongMap$$zeroValue) : new Tuple2(Long.MIN_VALUE, LongMap.this.scala$collection$mutable$LongMap$$minValue));
            this.anotherPair = LongMap.this.scala$collection$mutable$LongMap$$extraKeys == 3 ? new Tuple2(Long.MIN_VALUE, LongMap.this.scala$collection$mutable$LongMap$$minValue) : null;
            this.index = 0;
         }
      };
   }

   public Iterator keysIterator() {
      return scala.collection.MapOps.keysIterator$(this);
   }

   public Iterator valuesIterator() {
      return scala.collection.MapOps.valuesIterator$(this);
   }

   public void foreach(final Function1 f) {
      if ((this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1) {
         f.apply(new Tuple2(0L, this.scala$collection$mutable$LongMap$$zeroValue));
      }

      if ((this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2) {
         f.apply(new Tuple2(Long.MIN_VALUE, this.scala$collection$mutable$LongMap$$minValue));
      }

      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size; ++i) {
         long k = this.scala$collection$mutable$LongMap$$_keys[i];
         if (k != -k) {
            ++j;
            f.apply(new Tuple2(k, this.scala$collection$mutable$LongMap$$_values[i]));
         }
      }

   }

   public void foreachEntry(final Function2 f) {
      if ((this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1) {
         f.apply(0L, this.scala$collection$mutable$LongMap$$zeroValue);
      }

      if ((this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2) {
         f.apply(Long.MIN_VALUE, this.scala$collection$mutable$LongMap$$minValue);
      }

      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size; ++i) {
         long k = this.scala$collection$mutable$LongMap$$_keys[i];
         if (k != -k) {
            ++j;
            f.apply(k, this.scala$collection$mutable$LongMap$$_values[i]);
         }
      }

   }

   public LongMap clone() {
      long[] kz = Arrays.copyOf(this.scala$collection$mutable$LongMap$$_keys, this.scala$collection$mutable$LongMap$$_keys.length);
      Object[] vz = Arrays.copyOf(this.scala$collection$mutable$LongMap$$_values, this.scala$collection$mutable$LongMap$$_values.length);
      LongMap lm = new LongMap(this.defaultEntry, 1, false);
      lm.initializeTo(this.mask, this.scala$collection$mutable$LongMap$$extraKeys, this.scala$collection$mutable$LongMap$$zeroValue, this.scala$collection$mutable$LongMap$$minValue, this._size, this._vacant, kz, vz);
      return lm;
   }

   /** @deprecated */
   public LongMap $plus(final Tuple2 kv) {
      LongMap lm = this.clone();
      if (lm == null) {
         throw null;
      } else {
         lm.update(kv._1$mcJ$sp(), kv._2());
         return lm;
      }
   }

   /** @deprecated */
   public LongMap $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      LongMap m = this.$plus(elem1).$plus(elem2);
      return elems.isEmpty() ? m : m.concat(elems);
   }

   public LongMap concat(final IterableOnce xs) {
      LongMap lm = this.clone();
      xs.iterator().foreach((kv) -> (LongMap)lm.$plus$eq(kv));
      return lm;
   }

   public LongMap $plus$plus(final IterableOnce xs) {
      return this.concat(xs);
   }

   /** @deprecated */
   public LongMap updated(final long key, final Object value) {
      LongMap var10000 = this.clone();
      if (var10000 == null) {
         throw null;
      } else {
         LongMap addOne_this = var10000;
         addOne_this.update(key, value);
         return addOne_this;
      }
   }

   public void foreachKey(final Function1 f) {
      if ((this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1) {
         f.apply(0L);
      }

      if ((this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2) {
         f.apply(Long.MIN_VALUE);
      }

      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size; ++i) {
         long k = this.scala$collection$mutable$LongMap$$_keys[i];
         if (k != -k) {
            ++j;
            f.apply(k);
         }
      }

   }

   public void foreachValue(final Function1 f) {
      if ((this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1) {
         f.apply(this.scala$collection$mutable$LongMap$$zeroValue);
      }

      if ((this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2) {
         f.apply(this.scala$collection$mutable$LongMap$$minValue);
      }

      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size; ++i) {
         long k = this.scala$collection$mutable$LongMap$$_keys[i];
         if (k != -k) {
            ++j;
            f.apply(this.scala$collection$mutable$LongMap$$_values[i]);
         }
      }

   }

   public LongMap mapValuesNow(final Function1 f) {
      Object zv = (this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? f.apply(this.scala$collection$mutable$LongMap$$zeroValue) : null;
      Object mv = (this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2 ? f.apply(this.scala$collection$mutable$LongMap$$minValue) : null;
      LongMap lm = new LongMap(LongMap$.MODULE$.scala$collection$mutable$LongMap$$exceptionDefault(), 1, false);
      long[] kz = Arrays.copyOf(this.scala$collection$mutable$LongMap$$_keys, this.scala$collection$mutable$LongMap$$_keys.length);
      Object[] vz = new Object[this.scala$collection$mutable$LongMap$$_values.length];
      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size; ++i) {
         long k = this.scala$collection$mutable$LongMap$$_keys[i];
         if (k != -k) {
            ++j;
            vz[i] = f.apply(this.scala$collection$mutable$LongMap$$_values[i]);
         }
      }

      lm.initializeTo(this.mask, this.scala$collection$mutable$LongMap$$extraKeys, zv, mv, this._size, this._vacant, kz, vz);
      return lm;
   }

   /** @deprecated */
   public final LongMap transformValues(final Function1 f) {
      if ((this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1) {
         this.scala$collection$mutable$LongMap$$zeroValue = f.apply(this.scala$collection$mutable$LongMap$$zeroValue);
      }

      if ((this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2) {
         this.scala$collection$mutable$LongMap$$minValue = f.apply(this.scala$collection$mutable$LongMap$$minValue);
      }

      int transformValuesInPlace_i = 0;

      for(int transformValuesInPlace_j = 0; transformValuesInPlace_i < this.scala$collection$mutable$LongMap$$_keys.length & transformValuesInPlace_j < this._size; ++transformValuesInPlace_i) {
         long transformValuesInPlace_k = this.scala$collection$mutable$LongMap$$_keys[transformValuesInPlace_i];
         if (transformValuesInPlace_k != -transformValuesInPlace_k) {
            ++transformValuesInPlace_j;
            this.scala$collection$mutable$LongMap$$_values[transformValuesInPlace_i] = f.apply(this.scala$collection$mutable$LongMap$$_values[transformValuesInPlace_i]);
         }
      }

      return this;
   }

   public LongMap transformValuesInPlace(final Function1 f) {
      if ((this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1) {
         this.scala$collection$mutable$LongMap$$zeroValue = f.apply(this.scala$collection$mutable$LongMap$$zeroValue);
      }

      if ((this.scala$collection$mutable$LongMap$$extraKeys & 2) == 2) {
         this.scala$collection$mutable$LongMap$$minValue = f.apply(this.scala$collection$mutable$LongMap$$minValue);
      }

      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$LongMap$$_keys.length & j < this._size; ++i) {
         long k = this.scala$collection$mutable$LongMap$$_keys[i];
         if (k != -k) {
            ++j;
            this.scala$collection$mutable$LongMap$$_values[i] = f.apply(this.scala$collection$mutable$LongMap$$_values[i]);
         }
      }

      return this;
   }

   public LongMap map(final Function1 f) {
      return LongMap$.MODULE$.from(new View.Map(this, f));
   }

   public LongMap flatMap(final Function1 f) {
      return LongMap$.MODULE$.from(new View.FlatMap(this, f));
   }

   public LongMap collect(final PartialFunction pf) {
      LongMap$ var10000 = LongMap$.MODULE$;
      Builder strictOptimizedCollect_b = new LongMapBuilder();
      Object strictOptimizedCollect_marker = Statics.pfMarker;
      Iterator strictOptimizedCollect_it = new AbstractIterator() {
         private final long[] kz;
         private final Object[] vz;
         private Tuple2 nextPair;
         private Tuple2 anotherPair;
         private int index;

         public boolean hasNext() {
            if (this.nextPair == null) {
               if (this.index >= this.kz.length) {
                  return false;
               }

               for(long q = this.kz[this.index]; q == -q; q = this.kz[this.index]) {
                  ++this.index;
                  if (this.index >= this.kz.length) {
                     return false;
                  }
               }

               this.nextPair = new Tuple2(this.kz[this.index], this.vz[this.index]);
               ++this.index;
            }

            return true;
         }

         public Tuple2 next() {
            if (this.nextPair == null && !this.hasNext()) {
               throw new NoSuchElementException("next");
            } else {
               Tuple2 ans = this.nextPair;
               if (this.anotherPair != null) {
                  this.nextPair = this.anotherPair;
                  this.anotherPair = null;
               } else {
                  this.nextPair = null;
               }

               return ans;
            }
         }

         public {
            this.kz = LongMap.this.scala$collection$mutable$LongMap$$_keys;
            this.vz = LongMap.this.scala$collection$mutable$LongMap$$_values;
            this.nextPair = LongMap.this.scala$collection$mutable$LongMap$$extraKeys == 0 ? null : ((LongMap.this.scala$collection$mutable$LongMap$$extraKeys & 1) == 1 ? new Tuple2(0L, LongMap.this.scala$collection$mutable$LongMap$$zeroValue) : new Tuple2(Long.MIN_VALUE, LongMap.this.scala$collection$mutable$LongMap$$minValue));
            this.anotherPair = LongMap.this.scala$collection$mutable$LongMap$$extraKeys == 3 ? new Tuple2(Long.MIN_VALUE, LongMap.this.scala$collection$mutable$LongMap$$minValue) : null;
            this.index = 0;
         }
      };

      while(strictOptimizedCollect_it.hasNext()) {
         Object strictOptimizedCollect_elem = ((<undefinedtype>)strictOptimizedCollect_it).next();
         Object strictOptimizedCollect_v = pf.applyOrElse(strictOptimizedCollect_elem, StrictOptimizedIterableOps::$anonfun$strictOptimizedCollect$1);
         if (strictOptimizedCollect_marker != strictOptimizedCollect_v) {
            ((LongMapBuilder)strictOptimizedCollect_b).addOne((Tuple2)strictOptimizedCollect_v);
         }
      }

      return ((LongMapBuilder)strictOptimizedCollect_b).elems();
   }

   public Object writeReplace() {
      LongMap$ var10002 = LongMap$.MODULE$;
      var10002 = LongMap$.MODULE$;
      return new DefaultSerializationProxy(LongMap.ToFactory$.MODULE$, this);
   }

   public String className() {
      return "LongMap";
   }

   public LongMap(final Function1 defaultEntry, final int initialBufferSize, final boolean initBlank) {
      this.defaultEntry = defaultEntry;
      this.mask = 0;
      this.scala$collection$mutable$LongMap$$extraKeys = 0;
      this.scala$collection$mutable$LongMap$$zeroValue = null;
      this.scala$collection$mutable$LongMap$$minValue = null;
      this._size = 0;
      this._vacant = 0;
      this.scala$collection$mutable$LongMap$$_keys = null;
      this.scala$collection$mutable$LongMap$$_values = null;
      if (initBlank) {
         this.defaultInitialize(initialBufferSize);
      }

   }

   public LongMap() {
      this(LongMap$.MODULE$.scala$collection$mutable$LongMap$$exceptionDefault(), 16, true);
   }

   public LongMap(final Function1 defaultEntry) {
      this(defaultEntry, 16, true);
   }

   public LongMap(final int initialBufferSize) {
      this(LongMap$.MODULE$.scala$collection$mutable$LongMap$$exceptionDefault(), initialBufferSize, true);
   }

   public LongMap(final Function1 defaultEntry, final int initialBufferSize) {
      this(defaultEntry, initialBufferSize, true);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static final class LongMapBuilder implements ReusableBuilder {
      private LongMap elems = new LongMap();

      public void sizeHint(final int size) {
         Builder.sizeHint$(this, size);
      }

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
      public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
         return Growable.$plus$eq$(this, elem1, elem2, elems);
      }

      public Growable addAll(final IterableOnce elems) {
         return Growable.addAll$(this, elems);
      }

      public final Growable $plus$plus$eq(final IterableOnce elems) {
         return Growable.$plus$plus$eq$(this, elems);
      }

      public LongMap elems() {
         return this.elems;
      }

      public void elems_$eq(final LongMap x$1) {
         this.elems = x$1;
      }

      public LongMapBuilder addOne(final Tuple2 entry) {
         LongMap var10000 = this.elems();
         if (var10000 == null) {
            throw null;
         } else {
            var10000.update(entry._1$mcJ$sp(), entry._2());
            return this;
         }
      }

      public void clear() {
         this.elems_$eq(new LongMap());
      }

      public LongMap result() {
         return this.elems();
      }

      public int knownSize() {
         LongMap var10000 = this.elems();
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.size();
         }
      }
   }

   private static class ToFactory$ implements Factory, Serializable {
      public static final ToFactory$ MODULE$ = new ToFactory$();
      private static final long serialVersionUID = 3L;

      public LongMap fromSpecific(final IterableOnce it) {
         return LongMap$.MODULE$.from(it);
      }

      public Builder newBuilder() {
         LongMap$ var10000 = LongMap$.MODULE$;
         return new LongMapBuilder();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ToFactory$.class);
      }

      public ToFactory$() {
      }
   }

   private static class ToBuildFrom$ implements BuildFrom {
      public static final ToBuildFrom$ MODULE$ = new ToBuildFrom$();

      static {
         ToBuildFrom$ var10000 = MODULE$;
      }

      /** @deprecated */
      public Builder apply(final Object from) {
         return BuildFrom.apply$(this, from);
      }

      public Factory toFactory(final Object from) {
         return BuildFrom.toFactory$(this, from);
      }

      public LongMap fromSpecific(final Object from, final IterableOnce it) {
         return LongMap$.MODULE$.from(it);
      }

      public ReusableBuilder newBuilder(final Object from) {
         LongMap$ var10000 = LongMap$.MODULE$;
         return new LongMapBuilder();
      }

      public ToBuildFrom$() {
      }
   }
}
