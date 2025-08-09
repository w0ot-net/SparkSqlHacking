package spire.macros.fpf;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Constants;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.AbstractFunction4;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;
import spire.math.package$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0005c\u0001C4i!\u0003\r\t\u0001\u001c8\t\u000bY\u0004A\u0011\u0001=\t\u000fq\u0004!\u0019!D\u0001{\"9\u0011\u0011\u0006\u0001\u0007\u0004\u0005-\u0002bBA(\u0001\u0011%\u0011\u0011\u000b\u0005\b\u0003[\u0002A\u0011BA)\u0011\u001d\ty\u0007\u0001C\u0005\u0003#Bq!!\u001d\u0001\t\u0013\t\u0019\bC\u0004\u0002~\u0001!I!a \t\u000f\u0005u\u0004\u0001\"\u0003\u0002\u000e\"9\u0011\u0011\u0013\u0001\u0005\n\u0005M\u0005bBAL\u0001\u0011\u0005\u0011\u0011\u0014\u0004\u0007\u0003K\u0003\u0001)a*\t\u0015\u0005\u0005GB!f\u0001\n\u0003\t\t\u0006\u0003\u0006\u0002D2\u0011\t\u0012)A\u0005\u0003'B!\"!2\r\u0005+\u0007I\u0011AA)\u0011)\t9\r\u0004B\tB\u0003%\u00111\u000b\u0005\u000b\u0003\u0013d!Q3A\u0005\u0002\u0005-\u0007BCAj\u0019\tE\t\u0015!\u0003\u0002N\"Q\u0011Q\u001b\u0007\u0003\u0016\u0004%\t!!\u0015\t\u0015\u0005]GB!E!\u0002\u0013\t\u0019\u0006C\u0004\u0002Z2!\t!a7\t\u000f\u0005\u001dH\u0002\"\u0001\u0002R!9\u0011\u0011\u001e\u0007\u0005\u0002\u0005-\b\"\u0003B\u000b\u0019\u0005\u0005I\u0011\u0001BP\u0011%\u0011\u0019\u0003DI\u0001\n\u0003\u0011I\u000bC\u0005\u0003<1\t\n\u0011\"\u0001\u0003*\"I!\u0011\t\u0007\u0012\u0002\u0013\u0005!Q\u0016\u0005\n\u0005\u0007b\u0011\u0013!C\u0001\u0005SC\u0011Ba\u0013\r\u0003\u0003%\tE!\u0014\t\u0013\t}C\"!A\u0005\u0002\t\u0005\u0004\"\u0003B2\u0019\u0005\u0005I\u0011\u0001BY\u0011%\u0011Y\u0007DA\u0001\n\u0003\u0012i\u0007C\u0005\u0003|1\t\t\u0011\"\u0001\u00036\"I!q\u0011\u0007\u0002\u0002\u0013\u0005#\u0011\u0018\u0005\n\u0005\u001bc\u0011\u0011!C!\u0005\u001fC\u0011B!%\r\u0003\u0003%\tEa%\t\u0013\tUE\"!A\u0005B\tuv!\u0003Ba\u0001\u0005\u0005\t\u0012\u0001Bb\r%\t)\u000bAA\u0001\u0012\u0003\u0011)\rC\u0004\u0002Z\u001e\"\tA!8\t\u0013\tEu%!A\u0005F\tM\u0005\"\u0003BpO\u0005\u0005I\u0011\u0011Bq\u0011%\u0011YoJA\u0001\n\u0003\u0013iO\u0002\u0004\u0002p\u0002\u0001\u0015\u0011\u001f\u0005\u000b\u0003gd#Q3A\u0005\u0002\u0005U\bBCA\u007fY\tE\t\u0015!\u0003\u0002x\"Q\u0011\u0011\u0019\u0017\u0003\u0016\u0004%\t!a@\t\u0015\u0005\rGF!E!\u0002\u0013\t\u0019\t\u0003\u0006\u0002F2\u0012)\u001a!C\u0001\u0003\u007fD!\"a2-\u0005#\u0005\u000b\u0011BAB\u0011)\tI\r\fBK\u0002\u0013\u0005!\u0011\u0001\u0005\u000b\u0003'd#\u0011#Q\u0001\n\t\r\u0001BCAkY\tU\r\u0011\"\u0001\u0002\u0000\"Q\u0011q\u001b\u0017\u0003\u0012\u0003\u0006I!a!\t\u000f\u0005eG\u0006\"\u0001\u0003\u0006!9!\u0011\u0003\u0017\u0005\u0002\tM\u0001bBAtY\u0011\u0005\u0011\u0011\u000b\u0005\n\u0005+a\u0013\u0011!C\u0001\u0005/A\u0011Ba\t-#\u0003%\tA!\n\t\u0013\tmB&%A\u0005\u0002\tu\u0002\"\u0003B!YE\u0005I\u0011\u0001B\u001f\u0011%\u0011\u0019\u0005LI\u0001\n\u0003\u0011)\u0005C\u0005\u0003J1\n\n\u0011\"\u0001\u0003>!I!1\n\u0017\u0002\u0002\u0013\u0005#Q\n\u0005\n\u0005?b\u0013\u0011!C\u0001\u0005CB\u0011Ba\u0019-\u0003\u0003%\tA!\u001a\t\u0013\t-D&!A\u0005B\t5\u0004\"\u0003B>Y\u0005\u0005I\u0011\u0001B?\u0011%\u00119\tLA\u0001\n\u0003\u0012I\tC\u0005\u0003\u000e2\n\t\u0011\"\u0011\u0003\u0010\"I!\u0011\u0013\u0017\u0002\u0002\u0013\u0005#1\u0013\u0005\n\u0005+c\u0013\u0011!C!\u0005/;\u0011Ba@\u0001\u0003\u0003E\ta!\u0001\u0007\u0013\u0005=\b!!A\t\u0002\r\r\u0001bBAm\u0015\u0012\u000511\u0002\u0005\n\u0005#S\u0015\u0011!C#\u0005'C\u0011Ba8K\u0003\u0003%\ti!\u0004\t\u0013\t-(*!A\u0005\u0002\u000ee\u0001bBB\u0013\u0001\u0011%1q\u0005\u0005\b\u0007W\u0001A\u0011BB\u0017\u0011\u001d\u0019\t\u0004\u0001C\u0005\u0007gAqa!\u000f\u0001\t\u0013\u0019Y\u0004C\u0004\u0004@\u0001!Ia!\u0011\t\u000f\r\u0015\u0003\u0001\"\u0003\u0004H!91Q\u000b\u0001\u0005\n\r]\u0003bBB.\u0001\u0011%1Q\f\u0005\b\u0007k\u0002A\u0011BB<\u0011\u001d\u00199\t\u0001C\u0005\u0007\u0013Cqaa'\u0001\t\u0003\u0019i\nC\u0004\u0002~\u0001!\taa*\t\u000f\u0005E\u0005\u0001\"\u0001\u0004.\"91Q\u0017\u0001\u0005\u0002\r]\u0006bBBa\u0001\u0011\u000511\u0019\u0005\b\u0007\u001b\u0004A\u0011ABh\u0011\u001d\u0019I\u000e\u0001C\u0001\u00077Dqa!:\u0001\t\u0003\u00199\u000fC\u0004\u0004r\u0002!Iaa=\t\u000f\u0011\u001d\u0001\u0001\"\u0001\u0005\n\u001dAAQ\u00045\t\u00021$yBB\u0004hQ\"\u0005A\u000e\"\t\t\u000f\u0005eG\r\"\u0001\u0005$!9!q\u001c3\u0005\u0002\u0011\u0015\"!\u0002$vg\u0016\u0014(BA5k\u0003\r1\u0007O\u001a\u0006\u0003W2\fa!\\1de>\u001c(\"A7\u0002\u000bM\u0004\u0018N]3\u0016\u000b=\f\t!a\u0011\u0014\u0005\u0001\u0001\bCA9u\u001b\u0005\u0011(\"A:\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0014(AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005I\bCA9{\u0013\tY(O\u0001\u0003V]&$\u0018!A2\u0016\u0003y\u00042a`A\u0001\u0019\u0001!q!a\u0001\u0001\u0005\u0004\t)AA\u0001D#\u0011\t9!!\u0004\u0011\u0007E\fI!C\u0002\u0002\fI\u0014qAT8uQ&tw\r\u0005\u0003\u0002\u0010\u0005\rb\u0002BA\t\u0003?qA!a\u0005\u0002\u001e9!\u0011QCA\u000e\u001b\t\t9BC\u0002\u0002\u001a]\fa\u0001\u0010:p_Rt\u0014\"A7\n\u0005-d\u0017bAA\u0011U\u000611m\\7qCRLA!!\n\u0002(\t91i\u001c8uKb$(bAA\u0011U\u0006\t\u0011)\u0006\u0002\u0002.A1\u0011qFA\u001a\u0003\u0003r1!!\r\u0003\u001b\u0005\u0001\u0011\u0002BA\u001b\u0003o\u00111bV3bWRK\b/\u001a+bO&!\u0011\u0011HA\u001e\u0005\u001d\tE.[1tKNT1a[A\u001f\u0015\r\tyD]\u0001\be\u00164G.Z2u!\ry\u00181\t\u0003\b\u0003\u000b\u0002!\u0019AA$\u0005\u0005\t\u0015\u0003BA\u0004\u0003\u0013\u00022!]A&\u0013\r\tiE\u001d\u0002\u0004\u0003:L\u0018aB#qg&dwN\\\u000b\u0003\u0003'\u0002B!!\u0016\u0002b9!\u0011qFA,\u0013\u0011\tI&a\u0017\u0002\u0011Ut\u0017N^3sg\u0016LA!!\n\u0002^)!\u0011qLA\u001e\u0003!\u0011G.Y2lE>D\u0018\u0002BA2\u0003K\u0012A\u0001\u0016:fK&!\u0011qMA5\u0005\u0015!&/Z3t\u0015\u0011\tY'!\u0010\u0002\u0007\u0005\u0004\u0018.\u0001\tQ_NLG/\u001b<f\u0013:4\u0017N\\5us\u0006\u0001b*Z4bi&4X-\u00138gS:LG/_\u0001\u0004[\u0006DHCBA*\u0003k\nI\bC\u0004\u0002x\u001d\u0001\r!a\u0015\u0002\u0003\u0005Dq!a\u001f\b\u0001\u0004\t\u0019&A\u0001c\u0003\r\t'm\u001d\u000b\u0005\u0003'\n\t\tC\u0004\u0002x!\u0001\r!a!\u0011\t\u0005U\u0013QQ\u0005\u0005\u0003\u000f\u000bII\u0001\u0005UKJlg*Y7f\u0013\u0011\tY)!\u001b\u0003\u000b9\u000bW.Z:\u0015\t\u0005M\u0013q\u0012\u0005\b\u0003oJ\u0001\u0019AA*\u0003\u0011\u0019\u0018O\u001d;\u0015\t\u0005M\u0013Q\u0013\u0005\b\u0003oR\u0001\u0019AAB\u0003\u0019Ig\u000e\u001e'jiR!\u00111KAN\u0011\u001d\tij\u0003a\u0001\u0003?\u000b\u0011A\u001c\t\u0004c\u0006\u0005\u0016bAARe\n\u0019\u0011J\u001c;\u0003\r\u0005\u0003\bO]8y'\u0019a\u0001/!+\u00020B\u0019\u0011/a+\n\u0007\u00055&OA\u0004Qe>$Wo\u0019;\u0011\t\u0005E\u00161\u0018\b\u0005\u0003g\u000b9L\u0004\u0003\u0002\u0016\u0005U\u0016\"A:\n\u0007\u0005e&/A\u0004qC\u000e\\\u0017mZ3\n\t\u0005u\u0016q\u0018\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003s\u0013\u0018aA1qq\u0006!\u0011\r\u001d=!\u0003\riWm]\u0001\u0005[\u0016\u001c\b%A\u0002j]\u0012,\"!!4\u0011\u0011\u0005E\u0016qZA*\u0003?KA!!5\u0002@\n1Q)\u001b;iKJ\fA!\u001b8eA\u0005)Q\r_1di\u00061Q\r_1di\u0002\na\u0001P5oSRtDCCAo\u0003?\f\t/a9\u0002fB\u0019\u0011\u0011\u0007\u0007\t\u000f\u0005\u0005W\u00031\u0001\u0002T!9\u0011QY\u000bA\u0002\u0005M\u0003bBAe+\u0001\u0007\u0011Q\u001a\u0005\b\u0003+,\u0002\u0019AA*\u0003\u0011)\u0007\u0010\u001d:\u0002\u000b\u0019,8/\u001a3\u0015\t\u00055(1\u0014\t\u0004\u0003ca#!\u0002$vg\u0016$7C\u0002\u0017q\u0003S\u000by+A\u0003ti\u0006$8/\u0006\u0002\u0002xB1\u0011\u0011WA}\u0003'JA!a?\u0002@\n!A*[:u\u0003\u0019\u0019H/\u0019;tAU\u0011\u00111Q\u000b\u0003\u0005\u0007\u0001\u0002\"!-\u0002P\u0006\r\u0015q\u0014\u000b\r\u0003[\u00149A!\u0003\u0003\f\t5!q\u0002\u0005\b\u0003g<\u0004\u0019AA|\u0011\u001d\t\tm\u000ea\u0001\u0003\u0007Cq!!28\u0001\u0004\t\u0019\tC\u0004\u0002J^\u0002\rAa\u0001\t\u000f\u0005Uw\u00071\u0001\u0002\u0004\u00061\u0011\r\u001d9s_b,\"!!8\u0002\t\r|\u0007/\u001f\u000b\r\u0003[\u0014IBa\u0007\u0003\u001e\t}!\u0011\u0005\u0005\n\u0003gT\u0004\u0013!a\u0001\u0003oD\u0011\"!1;!\u0003\u0005\r!a!\t\u0013\u0005\u0015'\b%AA\u0002\u0005\r\u0005\"CAeuA\u0005\t\u0019\u0001B\u0002\u0011%\t)N\u000fI\u0001\u0002\u0004\t\u0019)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\t\u001d\"\u0006BA|\u0005SY#Aa\u000b\u0011\t\t5\"qG\u0007\u0003\u0005_QAA!\r\u00034\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005k\u0011\u0018AC1o]>$\u0018\r^5p]&!!\u0011\bB\u0018\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011yD\u000b\u0003\u0002\u0004\n%\u0012AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\u00119E\u000b\u0003\u0003\u0004\t%\u0012AD2paf$C-\u001a4bk2$H%N\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t=\u0003\u0003\u0002B)\u00057j!Aa\u0015\u000b\t\tU#qK\u0001\u0005Y\u0006twM\u0003\u0002\u0003Z\u0005!!.\u0019<b\u0013\u0011\u0011iFa\u0015\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\ty*\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005%#q\r\u0005\n\u0005S\u0012\u0015\u0011!a\u0001\u0003?\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B8!\u0019\u0011\tHa\u001e\u0002J5\u0011!1\u000f\u0006\u0004\u0005k\u0012\u0018AC2pY2,7\r^5p]&!!\u0011\u0010B:\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\t}$Q\u0011\t\u0004c\n\u0005\u0015b\u0001BBe\n9!i\\8mK\u0006t\u0007\"\u0003B5\t\u0006\u0005\t\u0019AA%\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\t=#1\u0012\u0005\n\u0005S*\u0015\u0011!a\u0001\u0003?\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003?\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005\u001f\na!Z9vC2\u001cH\u0003\u0002B@\u00053C\u0011B!\u001bI\u0003\u0003\u0005\r!!\u0013\t\u000f\tuu\u00031\u0001\u0002x\u000611\u000f^1ugB\"\"\"!8\u0003\"\n\r&Q\u0015BT\u0011%\t\t\r\u0007I\u0001\u0002\u0004\t\u0019\u0006C\u0005\u0002Fb\u0001\n\u00111\u0001\u0002T!I\u0011\u0011\u001a\r\u0011\u0002\u0003\u0007\u0011Q\u001a\u0005\n\u0003+D\u0002\u0013!a\u0001\u0003'*\"Aa++\t\u0005M#\u0011F\u000b\u0003\u0005_SC!!4\u0003*Q!\u0011\u0011\nBZ\u0011%\u0011IgHA\u0001\u0002\u0004\ty\n\u0006\u0003\u0003\u0000\t]\u0006\"\u0003B5C\u0005\u0005\t\u0019AA%)\u0011\u0011yEa/\t\u0013\t%$%!AA\u0002\u0005}E\u0003\u0002B@\u0005\u007fC\u0011B!\u001b&\u0003\u0003\u0005\r!!\u0013\u0002\r\u0005\u0003\bO]8y!\r\t\tdJ\n\u0006O\t\u001d'1\u001b\t\u000f\u0005\u0013\u0014y-a\u0015\u0002T\u00055\u00171KAo\u001b\t\u0011YMC\u0002\u0003NJ\fqA];oi&lW-\u0003\u0003\u0003R\n-'!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oiA!!Q\u001bBn\u001b\t\u00119N\u0003\u0003\u0003Z\n]\u0013AA5p\u0013\u0011\tiLa6\u0015\u0005\t\r\u0017!B1qa2LHCCAo\u0005G\u0014)Oa:\u0003j\"9\u0011\u0011\u0019\u0016A\u0002\u0005M\u0003bBAcU\u0001\u0007\u00111\u000b\u0005\b\u0003\u0013T\u0003\u0019AAg\u0011\u001d\t)N\u000ba\u0001\u0003'\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003p\nm\b#B9\u0003r\nU\u0018b\u0001Bze\n1q\n\u001d;j_:\u00042\"\u001dB|\u0003'\n\u0019&!4\u0002T%\u0019!\u0011 :\u0003\rQ+\b\u000f\\35\u0011%\u0011ipKA\u0001\u0002\u0004\ti.A\u0002yIA\nQAR;tK\u0012\u00042!!\rK'\u0015Q5Q\u0001Bj!A\u0011Ima\u0002\u0002x\u0006\r\u00151\u0011B\u0002\u0003\u0007\u000bi/\u0003\u0003\u0004\n\t-'!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8okQ\u00111\u0011\u0001\u000b\r\u0003[\u001cya!\u0005\u0004\u0014\rU1q\u0003\u0005\b\u0003gl\u0005\u0019AA|\u0011\u001d\t\t-\u0014a\u0001\u0003\u0007Cq!!2N\u0001\u0004\t\u0019\tC\u0004\u0002J6\u0003\rAa\u0001\t\u000f\u0005UW\n1\u0001\u0002\u0004R!11DB\u0012!\u0015\t(\u0011_B\u000f!5\t8qDA|\u0003\u0007\u000b\u0019Ia\u0001\u0002\u0004&\u00191\u0011\u0005:\u0003\rQ+\b\u000f\\36\u0011%\u0011iPTA\u0001\u0002\u0004\ti/A\u0005mS\u001a$X\t_1diR!\u0011Q^B\u0015\u0011\u001d\t)n\u0014a\u0001\u0003'\n!\u0002\\5gi\u0006\u0003\bO]8y)\u0011\tioa\f\t\u000f\tE\u0001\u000b1\u0001\u0002T\u00059Q\r\u001f;sC\u000e$H\u0003BAw\u0007kAqaa\u000eR\u0001\u0004\t\u0019&\u0001\u0003ue\u0016,\u0017aC5t\u000bb\f7\r\u001e'jMR$BAa \u0004>!91q\u0007*A\u0002\u0005M\u0013\u0001D5t\u0003B\u0004(o\u001c=MS\u001a$H\u0003\u0002B@\u0007\u0007Bqaa\u000eT\u0001\u0004\t\u0019&A\u0004uKJl\u0017NZ=\u0015\u0015\r%3QJB(\u0007#\u001a\u0019\u0006E\u0003r\u0005c\u001cY\u0005E\u0006r\u0005o\f\u0019)a!\u0003\u0004\u0005\r\u0005bBAa)\u0002\u0007\u00111\u000b\u0005\b\u0003\u000b$\u0006\u0019AA*\u0011\u001d\tI\r\u0016a\u0001\u0003'Bq!!6U\u0001\u0004\t\u0019&\u0001\tge\u0016\u001c\b.\u00119qe>Dh*Y7fgR\u00111\u0011\f\t\fc\n]\u00181QAB\u0003\u0007\u000b\u0019)\u0001\u0004{SBLe\u000e\u001a\u000b\u0007\u0007?\u001a\tha\u001d\u0015\r\u000557\u0011MB6\u0011\u001d\u0019\u0019G\u0016a\u0001\u0007K\n\u0011A\u001a\t\nc\u000e\u001d\u00141KA*\u0003'J1a!\u001bs\u0005%1UO\\2uS>t'\u0007C\u0004\u0004nY\u0003\raa\u001c\u0002\u0003\u001d\u0004\u0012\"]B4\u0003?\u000by*a(\t\u000f\u0005]d\u000b1\u0001\u0002N\"9\u00111\u0010,A\u0002\u00055\u0017!\u00024vg\u0016\u0014DCBB=\u0007\u007f\u001a\u0019\t\u0006\u0003\u0002n\u000em\u0004bBB2/\u0002\u00071Q\u0010\t\nc\u000e\u001d\u0014Q\\Ao\u0003;Dqa!!X\u0001\u0004\t\u0019&A\u0002mQNDqa!\"X\u0001\u0004\t\u0019&A\u0002sQN\faA]3tS\u001etG\u0003BBF\u0007/#B!!<\u0004\u000e\"911\r-A\u0002\r=\u0005#C9\u0004h\u0005\r\u00151QBI!\u001d\t81SA*\u0003'J1a!&s\u0005\u0019!V\u000f\u001d7fe!91\u0011\u0014-A\u0002\u0005M\u0013aA:vE\u00061a.Z4bi\u0016$Baa(\u0004&R!\u0011Q^BQ\u0011\u001d\u0019\u0019+\u0017a\u0001\u0003'\n!!\u001a<\t\u000f\re\u0015\f1\u0001\u0002TQ1\u0011Q^BU\u0007WCqa!'[\u0001\u0004\t\u0019\u0006C\u0004\u0004$j\u0003\r!a\u0015\u0015\t\r=61\u0017\u000b\u0005\u0003[\u001c\t\fC\u0004\u0004$n\u0003\r!a\u0015\t\u000f\r]2\f1\u0001\u0002T\u0005!\u0001\u000f\\;t)\u0019\u0019Il!0\u0004@R!\u0011Q^B^\u0011\u001d\u0019\u0019\u000b\u0018a\u0001\u0003'Bqa!!]\u0001\u0004\t\u0019\u0006C\u0004\u0004\u0006r\u0003\r!a\u0015\u0002\u000b5Lg.^:\u0015\r\r\u00157\u0011ZBf)\u0011\tioa2\t\u000f\r\rV\f1\u0001\u0002T!91\u0011Q/A\u0002\u0005M\u0003bBBC;\u0002\u0007\u00111K\u0001\u0006i&lWm\u001d\u000b\u0007\u0007#\u001c)na6\u0015\t\u0005581\u001b\u0005\b\u0007Gs\u0006\u0019AA*\u0011\u001d\u0019\tI\u0018a\u0001\u0003'Bqa!\"_\u0001\u0004\t\u0019&\u0001\u0004eSZLG-\u001a\u000b\u0007\u0007;\u001c\toa9\u0015\t\u000558q\u001c\u0005\b\u0007G{\u0006\u0019AA*\u0011\u001d\u0019\ti\u0018a\u0001\u0003'Bqa!\"`\u0001\u0004\t\u0019&\u0001\u0003tS\u001etG\u0003BBu\u0007_$B!a\u0015\u0004l\"91Q\u001e1A\u0002\u0005M\u0013AB:jO:,G\rC\u0004\u00048\u0001\u0004\r!a\u0015\u0002\r5\\7i\\7q)\u0011\u0019)\u0010b\u0001\u0011\u000fE\u001c9pa?\u0002T%\u00191\u0011 :\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0003BB\u007f\u0007\u007fl\u0011\u0001[\u0005\u0004\t\u0003A'aA\"na\"9AQA1A\u0002\u0005M\u0013!\u0001;\u0002\t\r|W\u000e\u001d\u000b\u0007\t\u0017!I\u0002b\u0007\u0015\r\u00115A1\u0003C\f)\u0011\t\u0019\u0006b\u0004\t\u000f\u0011E!\r1\u0001\u0004|\u0006\u00191-\u001c9\t\u000f\u0011U!\r1\u0001\u0002T\u0005\u0019!O\\4\t\u000f\r5(\r1\u0001\u0002T!91\u0011\u00112A\u0002\u0005M\u0003bBBCE\u0002\u0007\u00111K\u0001\u0006\rV\u001cXM\u001d\t\u0004\u0007{$7C\u00013q)\t!y\"\u0006\u0004\u0005(\u0011=B1\u0007\u000b\u0005\tS!i\u0004\u0006\u0003\u0005,\u0011U\u0002cBB\u007f\u0001\u00115B\u0011\u0007\t\u0004\u007f\u0012=BaBA\u0002M\n\u0007\u0011Q\u0001\t\u0004\u007f\u0012MBaBA#M\n\u0007\u0011q\t\u0005\n\to1\u0017\u0011!a\u0002\ts\t!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019!Y$a\r\u000529\u0019q\u0010\"\u0010\t\u000f\u0011}b\r1\u0001\u0005.\u0005\u00191\r\u001e="
)
public interface Fuser {
   static Fuser apply(final Context ctx, final TypeTags.WeakTypeTag evidence$1) {
      return Fuser$.MODULE$.apply(ctx, evidence$1);
   }

   Approx$ Approx();

   Fused$ Fused();

   Context c();

   TypeTags.WeakTypeTag A();

   private Trees.TreeApi Epsilon() {
      return this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToDouble((double)2.220446E-16F)));
   }

   private Trees.TreeApi PositiveInfinity() {
      return this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("java")), this.c().universe().TermName().apply("lang")), this.c().universe().TermName().apply("Double")), this.c().universe().TermName().apply("POSITIVE_INFINITY"));
   }

   private Trees.TreeApi NegativeInfinity() {
      return this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("java")), this.c().universe().TermName().apply("lang")), this.c().universe().TermName().apply("Double")), this.c().universe().TermName().apply("NEGATIVE_INFINITY"));
   }

   private Trees.TreeApi max(final Trees.TreeApi a, final Trees.TreeApi b) {
      return this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("java")), this.c().universe().TermName().apply("lang")), this.c().universe().TermName().apply("Math")), this.c().universe().TermName().apply("max")), (List)(new .colon.colon((List)(new .colon.colon(a, new .colon.colon(b, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)));
   }

   private Trees.TreeApi abs(final Names.TermNameApi a) {
      return this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("java")), this.c().universe().TermName().apply("lang")), this.c().universe().TermName().apply("Math")), this.c().universe().TermName().apply("abs")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(a, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
   }

   private Trees.TreeApi abs(final Trees.TreeApi a) {
      return this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("java")), this.c().universe().TermName().apply("lang")), this.c().universe().TermName().apply("Math")), this.c().universe().TermName().apply("abs")), (List)(new .colon.colon((List)(new .colon.colon(a, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
   }

   private Trees.TreeApi sqrt(final Names.TermNameApi a) {
      return this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("java")), this.c().universe().TermName().apply("lang")), this.c().universe().TermName().apply("Math")), this.c().universe().TermName().apply("sqrt")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(a, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
   }

   // $FF: synthetic method
   static Trees.TreeApi intLit$(final Fuser $this, final int n) {
      return $this.intLit(n);
   }

   default Trees.TreeApi intLit(final int n) {
      return this.c().universe().Liftable().liftInt().apply(BoxesRunTime.boxToInteger(n));
   }

   private Fused liftExact(final Trees.TreeApi exact) {
      Names.TermNameApi tmp = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$tmp$");
      Approx var10000 = new Approx(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.abs(tmp), scala.package..MODULE$.Right().apply(BoxesRunTime.boxToInteger(0)), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTypeApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("spire")), this.c().universe().TermName().apply("algebra")), this.c().universe().TermName().apply("Field")), (List)(new .colon.colon(this.c().universe().Liftable().liftTypeTag().apply(this.A()), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("fromDouble")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))));
      Trees.ValDefApi var3 = this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), tmp, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(exact, this.c().universe().TermName().apply("value")));
      return var10000.fused(scala.package..MODULE$.Nil().$colon$colon(var3));
   }

   private Fused liftApprox(final Trees.TreeApi approx) {
      Names.TermNameApi tmp = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$tmp$");
      Approx var10000 = new Approx(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.abs(tmp), scala.package..MODULE$.Right().apply(BoxesRunTime.boxToInteger(1)), this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(approx, this.c().universe().TermName().apply("exact")));
      Trees.ValDefApi var3 = this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), tmp, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTypeApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.c().universe().TermName().apply("_root_"), false), this.c().universe().TermName().apply("spire")), this.c().universe().TermName().apply("algebra")), this.c().universe().TermName().apply("IsReal")), (List)(new .colon.colon(this.c().universe().Liftable().liftTypeTag().apply(this.A()), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("toDouble")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(approx, this.c().universe().TermName().apply("exact")), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))));
      return var10000.fused(scala.package..MODULE$.Nil().$colon$colon(var3));
   }

   private Fused extract(final Trees.TreeApi tree) {
      Trees.TreeApi var4 = spire.macros.compat..MODULE$.resetLocalAttrs(this.c(), tree);
      Fused var2;
      if (var4 != null) {
         Option var5 = this.c().universe().BlockTag().unapply(var4);
         if (!var5.isEmpty()) {
            Trees.BlockApi var6 = (Trees.BlockApi)var5.get();
            if (var6 != null) {
               Option var7 = this.c().universe().Block().unapply(var6);
               if (!var7.isEmpty()) {
                  Fused var3;
                  label72: {
                     List stats;
                     Names.TermNameApi apx;
                     Names.TermNameApi mes;
                     Either ind;
                     Names.TermNameApi exact;
                     label71: {
                        stats = (List)((Tuple2)var7.get())._1();
                        Trees.TreeApi expr = (Trees.TreeApi)((Tuple2)var7.get())._2();
                        Fused var10 = this.extract(expr);
                        if (var10 != null) {
                           List var11 = var10.stats();
                           apx = var10.apx();
                           mes = var10.mes();
                           ind = var10.ind();
                           exact = var10.exact();
                           Nil var36 = scala.package..MODULE$.Nil();
                           if (var36 == null) {
                              if (var11 == null) {
                                 break label71;
                              }
                           } else if (var36.equals(var11)) {
                              break label71;
                           }
                        }

                        Names.TermNameApi tmp = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$tmp$");
                        List stats0 = (List)stats.$colon$plus(this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), tmp, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), var10.expr()));
                        var3 = (new Approx(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("apx")), this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("mes")), scala.package..MODULE$.Left().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("ind"))), this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("exact")))).fused(stats0);
                        break label72;
                     }

                     var3 = new Fused(stats, apx, mes, ind, exact);
                  }

                  var2 = var3;
                  return var2;
               }
            }
         }
      }

      if (var4 != null) {
         Option var19 = ((<undefinedtype>)(new Object() {
            // $FF: synthetic field
            private final Fuser $outer;

            public Option unapply(final Object tree) {
               Object var2;
               if (tree != null) {
                  Option var4 = this.$outer.c().universe().TreeTag().unapply(tree);
                  if (!var4.isEmpty()) {
                     Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                     if (var5 != null) {
                        Some var6 = this.$outer.c().universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                        if (!var6.isEmpty()) {
                           Trees.TreeApi qq$macro$7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                           List var8 = (List)((Tuple2)var6.get())._2();
                           if (var8 instanceof .colon.colon) {
                              .colon.colon var9 = (.colon.colon)var8;
                              List var10 = (List)var9.head();
                              List var11 = var9.next$access$1();
                              if (var10 instanceof .colon.colon) {
                                 .colon.colon var12 = (.colon.colon)var10;
                                 Trees.TreeApi qq$macro$8 = (Trees.TreeApi)var12.head();
                                 List var14 = var12.next$access$1();
                                 if (var14 instanceof .colon.colon) {
                                    .colon.colon var15 = (.colon.colon)var14;
                                    Trees.TreeApi qq$macro$9 = (Trees.TreeApi)var15.head();
                                    List var17 = var15.next$access$1();
                                    if (var17 instanceof .colon.colon) {
                                       .colon.colon var18 = (.colon.colon)var17;
                                       Trees.TreeApi qq$macro$10 = (Trees.TreeApi)var18.head();
                                       List var20 = var18.next$access$1();
                                       if (var20 instanceof .colon.colon) {
                                          .colon.colon var21 = (.colon.colon)var20;
                                          Trees.TreeApi qq$macro$11 = (Trees.TreeApi)var21.head();
                                          List var23 = var21.next$access$1();
                                          if (scala.collection.immutable.Nil..MODULE$.equals(var23) && scala.collection.immutable.Nil..MODULE$.equals(var11)) {
                                             var2 = new Some(new Tuple5(qq$macro$7, qq$macro$8, qq$macro$9, qq$macro$10, qq$macro$11));
                                             return (Option)var2;
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               var2 = scala.None..MODULE$;
               return (Option)var2;
            }

            public {
               if (Fuser.this == null) {
                  throw null;
               } else {
                  this.$outer = Fuser.this;
               }
            }
         })).unapply(var4);
         if (!var19.isEmpty()) {
            Trees.TreeApi apx = (Trees.TreeApi)((Tuple5)var19.get())._2();
            Trees.TreeApi mes = (Trees.TreeApi)((Tuple5)var19.get())._3();
            Trees.TreeApi ind = (Trees.TreeApi)((Tuple5)var19.get())._4();
            Trees.TreeApi exact = (Trees.TreeApi)((Tuple5)var19.get())._5();
            var2 = (Fused)this.termify(apx, mes, ind, exact).map((x0$1) -> {
               if (x0$1 != null) {
                  Names.TermNameApi apx = (Names.TermNameApi)x0$1._1();
                  Names.TermNameApi mes = (Names.TermNameApi)x0$1._2();
                  Either ind = (Either)x0$1._3();
                  Names.TermNameApi exact = (Names.TermNameApi)x0$1._4();
                  Fused var2 = this.new Fused(scala.package..MODULE$.Nil(), apx, mes, ind, exact);
                  return var2;
               } else {
                  throw new MatchError(x0$1);
               }
            }).getOrElse(() -> (this.new Approx(apx, mes, scala.package..MODULE$.Left().apply(ind), exact)).fused(scala.package..MODULE$.Nil()));
            return var2;
         }
      }

      Types.TypeApi var10000 = spire.macros.compat..MODULE$.typeCheck(this.c(), tree).tpe();
      Context var10001 = this.c();
      Universe $u = this.c().universe();
      Mirror $m = this.c().universe().rootMirror();

      final class $typecreator1$1 extends TypeCreator {
         // $FF: synthetic field
         private final Fuser $outer;

         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilterExact"), (List)(new .colon.colon(this.$outer.A().in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$1() {
            if (Fuser.this == null) {
               throw null;
            } else {
               this.$outer = Fuser.this;
               super();
            }
         }
      }

      if (var10000.$less$colon$less(var10001.weakTypeOf($u.WeakTypeTag().apply($m, new $typecreator1$1())))) {
         var2 = this.liftExact(tree);
      } else {
         var10000 = spire.macros.compat..MODULE$.typeCheck(this.c(), tree).tpe();
         var10001 = this.c();
         Universe $u = this.c().universe();
         Mirror $m = this.c().universe().rootMirror();

         final class $typecreator2$1 extends TypeCreator {
            // $FF: synthetic field
            private final Fuser $outer;

            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilterApprox"), (List)(new .colon.colon(this.$outer.A().in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
            }

            public $typecreator2$1() {
               if (Fuser.this == null) {
                  throw null;
               } else {
                  this.$outer = Fuser.this;
                  super();
               }
            }
         }

         if (var10000.$less$colon$less(var10001.weakTypeOf($u.WeakTypeTag().apply($m, new $typecreator2$1())))) {
            var2 = this.liftApprox(tree);
         } else {
            if (var4 != null) {
               Option var28 = ((<undefinedtype>)(new Object() {
                  // $FF: synthetic field
                  private final Fuser $outer;

                  public Option unapply(final Object tree) {
                     Object var2;
                     if (tree != null) {
                        Option var4 = this.$outer.c().universe().TreeTag().unapply(tree);
                        if (!var4.isEmpty()) {
                           Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                           if (var5 != null) {
                              Some var6 = this.$outer.c().universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                              if (!var6.isEmpty()) {
                                 Trees.TreeApi qq$macro$12 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                                 List var8 = (List)((Tuple2)var6.get())._2();
                                 if (var8 instanceof .colon.colon) {
                                    .colon.colon var9 = (.colon.colon)var8;
                                    List var10 = (List)var9.head();
                                    List var11 = var9.next$access$1();
                                    if (var10 instanceof .colon.colon) {
                                       .colon.colon var12 = (.colon.colon)var10;
                                       Trees.TreeApi qq$macro$13 = (Trees.TreeApi)var12.head();
                                       List var14 = var12.next$access$1();
                                       if (scala.collection.immutable.Nil..MODULE$.equals(var14) && var11 instanceof .colon.colon) {
                                          .colon.colon var15 = (.colon.colon)var11;
                                          List var16 = (List)var15.head();
                                          List var17 = var15.next$access$1();
                                          if (var16 instanceof .colon.colon) {
                                             .colon.colon var18 = (.colon.colon)var16;
                                             Trees.TreeApi qq$macro$14 = (Trees.TreeApi)var18.head();
                                             List var20 = var18.next$access$1();
                                             if (scala.collection.immutable.Nil..MODULE$.equals(var20) && scala.collection.immutable.Nil..MODULE$.equals(var17)) {
                                                var2 = new Some(new Tuple3(qq$macro$12, qq$macro$13, qq$macro$14));
                                                return (Option)var2;
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }

                     var2 = scala.None..MODULE$;
                     return (Option)var2;
                  }

                  public {
                     if (Fuser.this == null) {
                        throw null;
                     } else {
                        this.$outer = Fuser.this;
                     }
                  }
               })).unapply(var4);
               if (!var28.isEmpty()) {
                  Trees.TreeApi exact = (Trees.TreeApi)((Tuple3)var28.get())._2();
                  if (this.isExactLift(tree)) {
                     var2 = this.liftExact(exact);
                     return var2;
                  }
               }
            }

            if (var4 != null) {
               Option var30 = ((<undefinedtype>)(new Object() {
                  // $FF: synthetic field
                  private final Fuser $outer;

                  public Option unapply(final Object tree) {
                     Object var2;
                     if (tree != null) {
                        Option var4 = this.$outer.c().universe().TreeTag().unapply(tree);
                        if (!var4.isEmpty()) {
                           Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                           if (var5 != null) {
                              Some var6 = this.$outer.c().universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                              if (!var6.isEmpty()) {
                                 Trees.TreeApi qq$macro$15 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                                 List var8 = (List)((Tuple2)var6.get())._2();
                                 if (var8 instanceof .colon.colon) {
                                    .colon.colon var9 = (.colon.colon)var8;
                                    List var10 = (List)var9.head();
                                    List var11 = var9.next$access$1();
                                    if (var10 instanceof .colon.colon) {
                                       .colon.colon var12 = (.colon.colon)var10;
                                       Trees.TreeApi qq$macro$16 = (Trees.TreeApi)var12.head();
                                       List var14 = var12.next$access$1();
                                       if (scala.collection.immutable.Nil..MODULE$.equals(var14) && var11 instanceof .colon.colon) {
                                          .colon.colon var15 = (.colon.colon)var11;
                                          List var16 = (List)var15.head();
                                          List var17 = var15.next$access$1();
                                          if (var16 instanceof .colon.colon) {
                                             .colon.colon var18 = (.colon.colon)var16;
                                             Trees.TreeApi qq$macro$17 = (Trees.TreeApi)var18.head();
                                             List var20 = var18.next$access$1();
                                             if (scala.collection.immutable.Nil..MODULE$.equals(var20) && scala.collection.immutable.Nil..MODULE$.equals(var17)) {
                                                var2 = new Some(new Tuple3(qq$macro$15, qq$macro$16, qq$macro$17));
                                                return (Option)var2;
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }

                     var2 = scala.None..MODULE$;
                     return (Option)var2;
                  }

                  public {
                     if (Fuser.this == null) {
                        throw null;
                     } else {
                        this.$outer = Fuser.this;
                     }
                  }
               })).unapply(var4);
               if (!var30.isEmpty()) {
                  Trees.TreeApi approx = (Trees.TreeApi)((Tuple3)var30.get())._2();
                  if (this.isApproxLift(tree)) {
                     var2 = this.liftApprox(approx);
                     return var2;
                  }
               }
            }

            Names.TermNameApi tmp = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$tmp$");
            Trees.ValDefApi assign = this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), tmp, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), tree);
            var2 = (new Approx(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("apx")), this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("mes")), scala.package..MODULE$.Left().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("ind"))), this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("exact")))).fused(scala.package..MODULE$.Nil().$colon$colon(assign));
         }
      }

      return var2;
   }

   private boolean isExactLift(final Trees.TreeApi tree) {
      boolean var2;
      if (tree != null) {
         Option var4 = ((<undefinedtype>)(new Object() {
            // $FF: synthetic field
            private final Fuser $outer;

            public Option unapply(final Object tree) {
               Object var2;
               if (tree != null) {
                  Option var4 = this.$outer.c().universe().TreeTag().unapply(tree);
                  if (!var4.isEmpty()) {
                     Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                     if (var5 != null) {
                        Some var6 = this.$outer.c().universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                        if (!var6.isEmpty()) {
                           Trees.TreeApi qq$macro$1 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                           List var8 = (List)((Tuple2)var6.get())._2();
                           if (var8 instanceof .colon.colon) {
                              .colon.colon var9 = (.colon.colon)var8;
                              List var10 = (List)var9.head();
                              List var11 = var9.next$access$1();
                              if (var10 instanceof .colon.colon) {
                                 .colon.colon var12 = (.colon.colon)var10;
                                 Trees.TreeApi qq$macro$2 = (Trees.TreeApi)var12.head();
                                 List var14 = var12.next$access$1();
                                 if (scala.collection.immutable.Nil..MODULE$.equals(var14) && var11 instanceof .colon.colon) {
                                    .colon.colon var15 = (.colon.colon)var11;
                                    List var16 = (List)var15.head();
                                    List var17 = var15.next$access$1();
                                    if (var16 instanceof .colon.colon) {
                                       .colon.colon var18 = (.colon.colon)var16;
                                       Trees.TreeApi qq$macro$3 = (Trees.TreeApi)var18.head();
                                       List var20 = var18.next$access$1();
                                       if (scala.collection.immutable.Nil..MODULE$.equals(var20) && scala.collection.immutable.Nil..MODULE$.equals(var17)) {
                                          var2 = new Some(new Tuple3(qq$macro$1, qq$macro$2, qq$macro$3));
                                          return (Option)var2;
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               var2 = scala.None..MODULE$;
               return (Option)var2;
            }

            public {
               if (Fuser.this == null) {
                  throw null;
               } else {
                  this.$outer = Fuser.this;
               }
            }
         })).unapply(tree);
         if (!var4.isEmpty()) {
            boolean var11;
            label18: {
               Trees.TreeApi exact = (Trees.TreeApi)((Tuple3)var4.get())._2();
               Types.TypeApi var10000 = spire.macros.compat..MODULE$.typeCheck(this.c(), tree).tpe();
               Context var10001 = this.c();
               Universe $u = this.c().universe();
               Mirror $m = this.c().universe().rootMirror();

               final class $typecreator1$2 extends TypeCreator {
                  // $FF: synthetic field
                  private final Fuser $outer;

                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     scala.reflect.api.Universe $u = $m$untyped.universe();
                     return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.$outer.A().in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
                  }

                  public $typecreator1$2() {
                     if (Fuser.this == null) {
                        throw null;
                     } else {
                        this.$outer = Fuser.this;
                        super();
                     }
                  }
               }

               if (var10000.$less$colon$less(var10001.weakTypeOf($u.WeakTypeTag().apply($m, new $typecreator1$2())))) {
                  var10000 = spire.macros.compat..MODULE$.typeCheck(this.c(), exact).tpe();
                  var10001 = this.c();
                  Universe $u = this.c().universe();
                  Mirror $m = this.c().universe().rootMirror();

                  final class $typecreator2$2 extends TypeCreator {
                     // $FF: synthetic field
                     private final Fuser $outer;

                     public Types.TypeApi apply(final Mirror $m$untyped) {
                        scala.reflect.api.Universe $u = $m$untyped.universe();
                        return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilterExact"), (List)(new .colon.colon(this.$outer.A().in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
                     }

                     public $typecreator2$2() {
                        if (Fuser.this == null) {
                           throw null;
                        } else {
                           this.$outer = Fuser.this;
                           super();
                        }
                     }
                  }

                  if (var10000.$less$colon$less(var10001.weakTypeOf($u.WeakTypeTag().apply($m, new $typecreator2$2())))) {
                     var11 = true;
                     break label18;
                  }
               }

               var11 = false;
            }

            var2 = var11;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   private boolean isApproxLift(final Trees.TreeApi tree) {
      boolean var2;
      if (tree != null) {
         Option var4 = ((<undefinedtype>)(new Object() {
            // $FF: synthetic field
            private final Fuser $outer;

            public Option unapply(final Object tree) {
               Object var2;
               if (tree != null) {
                  Option var4 = this.$outer.c().universe().TreeTag().unapply(tree);
                  if (!var4.isEmpty()) {
                     Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                     if (var5 != null) {
                        Some var6 = this.$outer.c().universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                        if (!var6.isEmpty()) {
                           Trees.TreeApi qq$macro$1 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                           List var8 = (List)((Tuple2)var6.get())._2();
                           if (var8 instanceof .colon.colon) {
                              .colon.colon var9 = (.colon.colon)var8;
                              List var10 = (List)var9.head();
                              List var11 = var9.next$access$1();
                              if (var10 instanceof .colon.colon) {
                                 .colon.colon var12 = (.colon.colon)var10;
                                 Trees.TreeApi qq$macro$2 = (Trees.TreeApi)var12.head();
                                 List var14 = var12.next$access$1();
                                 if (scala.collection.immutable.Nil..MODULE$.equals(var14) && var11 instanceof .colon.colon) {
                                    .colon.colon var15 = (.colon.colon)var11;
                                    List var16 = (List)var15.head();
                                    List var17 = var15.next$access$1();
                                    if (var16 instanceof .colon.colon) {
                                       .colon.colon var18 = (.colon.colon)var16;
                                       Trees.TreeApi qq$macro$3 = (Trees.TreeApi)var18.head();
                                       List var20 = var18.next$access$1();
                                       if (scala.collection.immutable.Nil..MODULE$.equals(var20) && scala.collection.immutable.Nil..MODULE$.equals(var17)) {
                                          var2 = new Some(new Tuple3(qq$macro$1, qq$macro$2, qq$macro$3));
                                          return (Option)var2;
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               var2 = scala.None..MODULE$;
               return (Option)var2;
            }

            public {
               if (Fuser.this == null) {
                  throw null;
               } else {
                  this.$outer = Fuser.this;
               }
            }
         })).unapply(tree);
         if (!var4.isEmpty()) {
            boolean var11;
            label18: {
               Trees.TreeApi approx = (Trees.TreeApi)((Tuple3)var4.get())._2();
               Types.TypeApi var10000 = spire.macros.compat..MODULE$.typeCheck(this.c(), tree).tpe();
               Context var10001 = this.c();
               Universe $u = this.c().universe();
               Mirror $m = this.c().universe().rootMirror();

               final class $typecreator1$3 extends TypeCreator {
                  // $FF: synthetic field
                  private final Fuser $outer;

                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     scala.reflect.api.Universe $u = $m$untyped.universe();
                     return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilter"), (List)(new .colon.colon(this.$outer.A().in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
                  }

                  public $typecreator1$3() {
                     if (Fuser.this == null) {
                        throw null;
                     } else {
                        this.$outer = Fuser.this;
                        super();
                     }
                  }
               }

               if (var10000.$less$colon$less(var10001.weakTypeOf($u.WeakTypeTag().apply($m, new $typecreator1$3())))) {
                  var10000 = spire.macros.compat..MODULE$.typeCheck(this.c(), approx).tpe();
                  var10001 = this.c();
                  Universe $u = this.c().universe();
                  Mirror $m = this.c().universe().rootMirror();

                  final class $typecreator2$3 extends TypeCreator {
                     // $FF: synthetic field
                     private final Fuser $outer;

                     public Types.TypeApi apply(final Mirror $m$untyped) {
                        scala.reflect.api.Universe $u = $m$untyped.universe();
                        return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("spire.math").asModule().moduleClass()), $m$untyped.staticClass("spire.math.FpFilterApprox"), (List)(new .colon.colon(this.$outer.A().in($m$untyped).tpe(), scala.collection.immutable.Nil..MODULE$)));
                     }

                     public $typecreator2$3() {
                        if (Fuser.this == null) {
                           throw null;
                        } else {
                           this.$outer = Fuser.this;
                           super();
                        }
                     }
                  }

                  if (var10000.$less$colon$less(var10001.weakTypeOf($u.WeakTypeTag().apply($m, new $typecreator2$3())))) {
                     var11 = true;
                     break label18;
                  }
               }

               var11 = false;
            }

            var2 = var11;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   private Option termify(final Trees.TreeApi apx, final Trees.TreeApi mes, final Trees.TreeApi ind, final Trees.TreeApi exact) {
      Option ind0 = this.t$1(ind).map((x$4) -> scala.package..MODULE$.Left().apply(x$4)).orElse(() -> this.l$1(ind).map((x$5) -> $anonfun$termify$3(BoxesRunTime.unboxToInt(x$5))));
      return this.t$1(apx).flatMap((a) -> this.t$1(mes).flatMap((b) -> ind0.flatMap((c) -> this.t$1(exact).map((d) -> new Tuple4(a, b, c, d)))));
   }

   // $FF: synthetic method
   static Tuple4 spire$macros$fpf$Fuser$$freshApproxNames$(final Fuser $this) {
      return $this.spire$macros$fpf$Fuser$$freshApproxNames();
   }

   default Tuple4 spire$macros$fpf$Fuser$$freshApproxNames() {
      Names.TermNameApi apx = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$apx$");
      Names.TermNameApi mes = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$mes$");
      Names.TermNameApi ind = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$ind$");
      Names.TermNameApi exact = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$exact$");
      return new Tuple4(apx, mes, ind, exact);
   }

   private Either zipInd(final Either a, final Either b, final Function2 f, final Function2 g) {
      Tuple2 var6 = new Tuple2(a, b);
      Object var5;
      if (var6 != null) {
         Either var7 = (Either)var6._1();
         Either var8 = (Either)var6._2();
         if (var7 instanceof Right) {
            Right var9 = (Right)var7;
            int n = BoxesRunTime.unboxToInt(var9.value());
            if (var8 instanceof Right) {
               Right var11 = (Right)var8;
               int m = BoxesRunTime.unboxToInt(var11.value());
               var5 = scala.package..MODULE$.Right().apply(BoxesRunTime.boxToInteger(g.apply$mcIII$sp(n, m)));
               return (Either)var5;
            }
         }
      }

      if (var6 != null) {
         Either var13 = (Either)var6._1();
         Either var14 = (Either)var6._2();
         if (var13 instanceof Right) {
            Right var15 = (Right)var13;
            int n = BoxesRunTime.unboxToInt(var15.value());
            if (var14 instanceof Left) {
               Left var17 = (Left)var14;
               Trees.TreeApi t = (Trees.TreeApi)var17.value();
               var5 = scala.package..MODULE$.Left().apply(f.apply(this.intLit(n), t));
               return (Either)var5;
            }
         }
      }

      if (var6 != null) {
         Either var19 = (Either)var6._1();
         Either var20 = (Either)var6._2();
         if (var19 instanceof Left) {
            Left var21 = (Left)var19;
            Trees.TreeApi t = (Trees.TreeApi)var21.value();
            if (var20 instanceof Right) {
               Right var23 = (Right)var20;
               int n = BoxesRunTime.unboxToInt(var23.value());
               var5 = scala.package..MODULE$.Left().apply(f.apply(t, this.intLit(n)));
               return (Either)var5;
            }
         }
      }

      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         Either var25 = (Either)var6._1();
         Either var26 = (Either)var6._2();
         if (!(var25 instanceof Left)) {
            throw new MatchError(var6);
         } else {
            Left var27 = (Left)var25;
            Trees.TreeApi t = (Trees.TreeApi)var27.value();
            if (!(var26 instanceof Left)) {
               throw new MatchError(var6);
            } else {
               Left var29 = (Left)var26;
               Trees.TreeApi u = (Trees.TreeApi)var29.value();
               var5 = scala.package..MODULE$.Left().apply(f.apply(t, u));
               return (Either)var5;
            }
         }
      }
   }

   private Fused fuse2(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Function2 f) {
      Fused lfused = this.extract(lhs);
      Fused rfused = this.extract(rhs);
      return ((Approx)f.apply(lfused.approx(), rfused.approx())).fused((List)lfused.stats().$plus$plus(rfused.stats()));
   }

   private Fused resign(final Trees.TreeApi sub, final Function2 f) {
      Fused fused = this.extract(sub);
      Tuple4 var7 = this.spire$macros$fpf$Fuser$$freshApproxNames();
      if (var7 != null) {
         Names.TermNameApi apx = (Names.TermNameApi)var7._1();
         Names.TermNameApi exact = (Names.TermNameApi)var7._4();
         Tuple2 var4 = new Tuple2(apx, exact);
         Names.TermNameApi apx = (Names.TermNameApi)var4._1();
         Names.TermNameApi exact = (Names.TermNameApi)var4._2();
         Tuple2 var13 = (Tuple2)f.apply(fused.apx(), fused.exact());
         if (var13 != null) {
            Trees.TreeApi apx0 = (Trees.TreeApi)var13._1();
            Trees.TreeApi exact0 = (Trees.TreeApi)var13._2();
            Tuple2 var3 = new Tuple2(apx0, exact0);
            Trees.TreeApi apx0 = (Trees.TreeApi)var3._1();
            Trees.TreeApi exact0 = (Trees.TreeApi)var3._2();
            List stats = (List)((SeqOps)fused.stats().$colon$plus(this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), apx, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), apx0))).$colon$plus(this.c().universe().internal().reificationSupport().SyntacticDefDef().apply(this.c().universe().NoMods(), exact, scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), exact0));
            Names.TermNameApi x$4 = fused.copy$default$3();
            Either x$5 = fused.copy$default$4();
            return fused.copy(stats, apx, x$4, x$5, exact);
         } else {
            throw new MatchError(var13);
         }
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   static Fused negate$(final Fuser $this, final Trees.TreeApi sub, final Trees.TreeApi ev) {
      return $this.negate(sub, ev);
   }

   default Fused negate(final Trees.TreeApi sub, final Trees.TreeApi ev) {
      return this.resign(sub, (apx, exact) -> new Tuple2(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(apx, false), this.c().universe().TermName().apply("unary_$minus")), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(ev, this.c().universe().TermName().apply("negate")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(exact, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))));
   }

   // $FF: synthetic method
   static Fused abs$(final Fuser $this, final Trees.TreeApi sub, final Trees.TreeApi ev) {
      return $this.abs(sub, ev);
   }

   default Fused abs(final Trees.TreeApi sub, final Trees.TreeApi ev) {
      return this.resign(sub, (apx, exact) -> new Tuple2(this.abs(apx), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(ev, this.c().universe().TermName().apply("abs")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(exact, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))));
   }

   // $FF: synthetic method
   static Fused sqrt$(final Fuser $this, final Trees.TreeApi tree, final Trees.TreeApi ev) {
      return $this.sqrt(tree, ev);
   }

   default Fused sqrt(final Trees.TreeApi tree, final Trees.TreeApi ev) {
      Fused fused = this.extract(tree);
      Tuple4 var6 = this.spire$macros$fpf$Fuser$$freshApproxNames();
      if (var6 != null) {
         Names.TermNameApi apx = (Names.TermNameApi)var6._1();
         Names.TermNameApi mes = (Names.TermNameApi)var6._2();
         Names.TermNameApi ind = (Names.TermNameApi)var6._3();
         Names.TermNameApi exact = (Names.TermNameApi)var6._4();
         Tuple4 var3 = new Tuple4(apx, mes, ind, exact);
         Names.TermNameApi apx = (Names.TermNameApi)var3._1();
         Names.TermNameApi mes = (Names.TermNameApi)var3._2();
         Names.TermNameApi ind = (Names.TermNameApi)var3._3();
         Names.TermNameApi exact = (Names.TermNameApi)var3._4();
         List indValDef = (List)fused.ind().fold((n) -> {
            Trees.ValDefApi var3 = this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), ind, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(n, false), this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))));
            return scala.package..MODULE$.Nil().$colon$colon(var3);
         }, (x$9) -> $anonfun$sqrt$2(BoxesRunTime.unboxToInt(x$9)));
         List stats = (List)((IterableOps)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.ValOrDefDefApi[]{this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), apx, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.sqrt(fused.apx())), this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), mes, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.c().universe().If().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(fused.apx(), false), this.c().universe().TermName().apply("$less")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.sqrt(fused.mes()), this.c().universe().TermName().apply("$times")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), this.c().universe().TermName().apply("$less$less")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(26))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(fused.mes(), false), this.c().universe().TermName().apply("$div")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(fused.apx(), false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("$times")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(apx, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))))), this.c().universe().internal().reificationSupport().SyntacticDefDef().apply(this.c().universe().NoMods(), exact, scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(ev, this.c().universe().TermName().apply("sqrt")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(fused.exact(), false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))))})))).$plus$plus(indValDef);
         Either ind0 = (Either)fused.ind().fold((x$10) -> scala.package..MODULE$.Left().apply(ind), (n) -> $anonfun$sqrt$4(BoxesRunTime.unboxToInt(n)));
         Fused result = new Fused((List)fused.stats().$plus$plus(stats), apx, mes, ind0, exact);
         return result;
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   static Fused plus$(final Fuser $this, final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
      return $this.plus(lhs, rhs, ev);
   }

   default Fused plus(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
      return this.fuse2(lhs, rhs, (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            Approx var6 = (Approx)var5._1();
            Approx var7 = (Approx)var5._2();
            if (var6 != null) {
               Trees.TreeApi lapx = var6.apx();
               Trees.TreeApi lmes = var6.mes();
               Either lind = var6.ind();
               Trees.TreeApi lexact = var6.exact();
               if (var7 != null) {
                  Trees.TreeApi rapx = var7.apx();
                  Trees.TreeApi rmes = var7.mes();
                  Either rind = var7.ind();
                  Trees.TreeApi rexact = var7.exact();
                  Either ind = this.zipInd(lind, rind, (l, r) -> this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.max(l, r), this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), (JFunction2.mcIII.sp)(l, r) -> package$.MODULE$.max(l, r) + 1);
                  Approx var4 = this.new Approx(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(lapx, this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(rapx, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(lmes, this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(rmes, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), ind, this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(ev, this.c().universe().TermName().apply("plus")), (List)(new .colon.colon((List)(new .colon.colon(lexact, new .colon.colon(rexact, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))));
                  return var4;
               }
            }
         }

         throw new MatchError(var5);
      });
   }

   // $FF: synthetic method
   static Fused minus$(final Fuser $this, final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
      return $this.minus(lhs, rhs, ev);
   }

   default Fused minus(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
      return this.fuse2(lhs, rhs, (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            Approx var6 = (Approx)var5._1();
            Approx var7 = (Approx)var5._2();
            if (var6 != null) {
               Trees.TreeApi lapx = var6.apx();
               Trees.TreeApi lmes = var6.mes();
               Either lind = var6.ind();
               Trees.TreeApi lexact = var6.exact();
               if (var7 != null) {
                  Trees.TreeApi rapx = var7.apx();
                  Trees.TreeApi rmes = var7.mes();
                  Either rind = var7.ind();
                  Trees.TreeApi rexact = var7.exact();
                  Either ind = this.zipInd(lind, rind, (l, r) -> this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.max(l, r), this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), (JFunction2.mcIII.sp)(l, r) -> package$.MODULE$.max(l, r) + 1);
                  Approx var4 = this.new Approx(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(lapx, this.c().universe().TermName().apply("$minus")), (List)(new .colon.colon((List)(new .colon.colon(rapx, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(lmes, this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(rmes, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), ind, this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(ev, this.c().universe().TermName().apply("minus")), (List)(new .colon.colon((List)(new .colon.colon(lexact, new .colon.colon(rexact, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))));
                  return var4;
               }
            }
         }

         throw new MatchError(var5);
      });
   }

   // $FF: synthetic method
   static Fused times$(final Fuser $this, final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
      return $this.times(lhs, rhs, ev);
   }

   default Fused times(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
      return this.fuse2(lhs, rhs, (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            Approx var6 = (Approx)var5._1();
            Approx var7 = (Approx)var5._2();
            if (var6 != null) {
               Trees.TreeApi lapx = var6.apx();
               Trees.TreeApi lmes = var6.mes();
               Either lind = var6.ind();
               Trees.TreeApi lexact = var6.exact();
               if (var7 != null) {
                  Trees.TreeApi rapx = var7.apx();
                  Trees.TreeApi rmes = var7.mes();
                  Either rind = var7.ind();
                  Trees.TreeApi rexact = var7.exact();
                  Either ind = this.zipInd(lind, rind, (l, r) -> this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(l, this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(r, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), (JFunction2.mcIII.sp)(l, r) -> l + r + 1);
                  Approx var4 = this.new Approx(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(lapx, this.c().universe().TermName().apply("$times")), (List)(new .colon.colon((List)(new .colon.colon(rapx, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(lmes, this.c().universe().TermName().apply("$times")), (List)(new .colon.colon((List)(new .colon.colon(rmes, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), ind, this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(ev, this.c().universe().TermName().apply("times")), (List)(new .colon.colon((List)(new .colon.colon(lexact, new .colon.colon(rexact, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))));
                  return var4;
               }
            }
         }

         throw new MatchError(var5);
      });
   }

   // $FF: synthetic method
   static Fused divide$(final Fuser $this, final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
      return $this.divide(lhs, rhs, ev);
   }

   default Fused divide(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi ev) {
      return this.fuse2(lhs, rhs, (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            Approx var6 = (Approx)var5._1();
            Approx var7 = (Approx)var5._2();
            if (var6 != null) {
               Trees.TreeApi lapx = var6.apx();
               Trees.TreeApi lmes = var6.mes();
               Either lind = var6.ind();
               Trees.TreeApi lexact = var6.exact();
               if (var7 != null) {
                  Trees.TreeApi rapx = var7.apx();
                  Trees.TreeApi rmes = var7.mes();
                  Either rind = var7.ind();
                  Trees.TreeApi rexact = var7.exact();
                  Names.TermNameApi tmp = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$tmp$");
                  Trees.TreeApi rindp1 = (Trees.TreeApi)rind.fold((rind0) -> this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(rind0, this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), (n) -> $anonfun$divide$3(this, BoxesRunTime.unboxToInt(n)));
                  Approx var4 = this.new Approx(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(lapx, this.c().universe().TermName().apply("$div")), (List)(new .colon.colon((List)(new .colon.colon(rapx, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().internal().reificationSupport().SyntacticBlock().apply((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), tmp, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.abs(rapx)), new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.abs(lapx), this.c().universe().TermName().apply("$div")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(lmes, this.c().universe().TermName().apply("$div")), (List)(new .colon.colon((List)(new .colon.colon(rmes, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("$div")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(tmp, false), this.c().universe().TermName().apply("$div")), (List)(new .colon.colon((List)(new .colon.colon(rmes, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("$minus")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(rindp1, this.c().universe().TermName().apply("$times")), (List)(new .colon.colon((List)scala.collection.immutable.List..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{this.Epsilon()}))), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), this.zipInd(lind, rind, (l, x$11) -> this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.max(l, rindp1), this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), (JFunction2.mcIII.sp)(l, r) -> package$.MODULE$.max(l, r + 1) + 1), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(ev, this.c().universe().TermName().apply("div")), (List)(new .colon.colon((List)(new .colon.colon(lexact, new .colon.colon(rexact, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))));
                  return var4;
               }
            }
         }

         throw new MatchError(var5);
      });
   }

   // $FF: synthetic method
   static Trees.TreeApi sign$(final Fuser $this, final Trees.TreeApi tree, final Trees.TreeApi signed) {
      return $this.sign(tree, signed);
   }

   default Trees.TreeApi sign(final Trees.TreeApi tree, final Trees.TreeApi signed) {
      Fused var5 = this.extract(tree);
      if (var5 != null) {
         List stats = var5.stats();
         Names.TermNameApi apx = var5.apx();
         Names.TermNameApi mes = var5.mes();
         Either ind = var5.ind();
         Names.TermNameApi exact = var5.exact();
         Tuple5 var3 = new Tuple5(stats, apx, mes, ind, exact);
         List stats = (List)var3._1();
         Names.TermNameApi apx = (Names.TermNameApi)var3._2();
         Names.TermNameApi mes = (Names.TermNameApi)var3._3();
         Either ind = (Either)var3._4();
         Names.TermNameApi exact = (Names.TermNameApi)var3._5();
         Names.TermNameApi err = spire.macros.compat..MODULE$.freshTermName(this.c(), "fpf$err$");
         Trees.TreeApi ind0 = (Trees.TreeApi)ind.fold((name) -> this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(name, false), (n) -> $anonfun$sign$2(this, BoxesRunTime.unboxToInt(n)));
         Trees.BlockApi block = this.c().universe().Block().apply((List)stats.$colon$plus(this.c().universe().internal().reificationSupport().SyntacticValDef().apply(this.c().universe().NoMods(), err, this.c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(mes, false), this.c().universe().TermName().apply("$times")), (List)(new .colon.colon((List)(new .colon.colon(ind0, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("$times")), (List)(new .colon.colon((List)(new .colon.colon(this.Epsilon(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))))), this.c().universe().If().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(apx, false), this.c().universe().TermName().apply("$greater")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(err, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("$amp$amp")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(apx, false), this.c().universe().TermName().apply("$less")), (List)(new .colon.colon((List)(new .colon.colon(this.PositiveInfinity(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), this.c().universe().If().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(apx, false), this.c().universe().TermName().apply("$less")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(err, false), this.c().universe().TermName().apply("unary_$minus")), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().TermName().apply("$amp$amp")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(apx, false), this.c().universe().TermName().apply("$greater")), (List)(new .colon.colon((List)(new .colon.colon(this.NegativeInfinity(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(-1))), this.c().universe().If().apply(this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(err, false), this.c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToDouble((double)0.0F))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(signed, this.c().universe().TermName().apply("signum")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().internal().reificationSupport().SyntacticTermIdent().apply(exact, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))))));
         return block;
      } else {
         throw new MatchError(var5);
      }
   }

   private Function1 mkComp(final Trees.TreeApi t) {
      return (x0$1) -> {
         Trees.TreeApi var3;
         if (Cmp.Lt$.MODULE$.equals(x0$1)) {
            var3 = this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(t, this.c().universe().TermName().apply("$less")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         } else if (Cmp.Gt$.MODULE$.equals(x0$1)) {
            var3 = this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(t, this.c().universe().TermName().apply("$greater")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         } else if (Cmp.LtEq$.MODULE$.equals(x0$1)) {
            var3 = this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(t, this.c().universe().TermName().apply("$less$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         } else if (Cmp.GtEq$.MODULE$.equals(x0$1)) {
            var3 = this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(t, this.c().universe().TermName().apply("$greater$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         } else {
            if (!Cmp.Eq$.MODULE$.equals(x0$1)) {
               throw new MatchError(x0$1);
            }

            var3 = this.c().universe().internal().reificationSupport().SyntacticApplied().apply(this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(t, this.c().universe().TermName().apply("$eq$eq")), (List)(new .colon.colon((List)(new .colon.colon(this.c().universe().Literal().apply(this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(0))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
         }

         return var3;
      };
   }

   // $FF: synthetic method
   static Trees.TreeApi comp$(final Fuser $this, final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi rng, final Trees.TreeApi signed, final Cmp cmp) {
      return $this.comp(lhs, rhs, rng, signed, cmp);
   }

   default Trees.TreeApi comp(final Trees.TreeApi lhs, final Trees.TreeApi rhs, final Trees.TreeApi rng, final Trees.TreeApi signed, final Cmp cmp) {
      Trees.TreeApi result = this.sign(this.minus(lhs, rhs, rng).expr(), signed);
      return (Trees.TreeApi)this.mkComp(result).apply(cmp);
   }

   private Option t$1(final Trees.TreeApi tree) {
      Object var2;
      if (tree != null) {
         Option var4 = this.c().universe().IdentTag().unapply(tree);
         if (!var4.isEmpty()) {
            Trees.IdentApi var5 = (Trees.IdentApi)var4.get();
            if (var5 != null) {
               Option var6 = this.c().universe().Ident().unapply(var5);
               if (!var6.isEmpty()) {
                  Names.NameApi name = (Names.NameApi)var6.get();
                  if (name != null) {
                     Option var8 = this.c().universe().TermNameTag().unapply(name);
                     if (!var8.isEmpty() && var8.get() != null) {
                        var2 = new Some((Names.TermNameApi)name);
                        return (Option)var2;
                     }
                  }
               }
            }
         }
      }

      var2 = scala.None..MODULE$;
      return (Option)var2;
   }

   private Option l$1(final Trees.TreeApi tree) {
      Object var2;
      if (tree != null) {
         Option var4 = this.c().universe().LiteralTag().unapply(tree);
         if (!var4.isEmpty()) {
            Trees.LiteralApi var5 = (Trees.LiteralApi)var4.get();
            if (var5 != null) {
               Option var6 = this.c().universe().Literal().unapply(var5);
               if (!var6.isEmpty()) {
                  Constants.ConstantApi var7 = (Constants.ConstantApi)var6.get();
                  if (var7 != null) {
                     Option var8 = this.c().universe().ConstantTag().unapply(var7);
                     if (!var8.isEmpty()) {
                        Constants.ConstantApi var9 = (Constants.ConstantApi)var8.get();
                        if (var9 != null) {
                           Option var10 = this.c().universe().Constant().unapply(var9);
                           if (!var10.isEmpty()) {
                              Object n = var10.get();
                              if (n instanceof Integer) {
                                 int var12 = BoxesRunTime.unboxToInt(n);
                                 var2 = new Some(BoxesRunTime.boxToInteger(var12));
                                 return (Option)var2;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      var2 = scala.None..MODULE$;
      return (Option)var2;
   }

   // $FF: synthetic method
   static Right $anonfun$termify$3(final int x$5) {
      return scala.package..MODULE$.Right().apply(BoxesRunTime.boxToInteger(x$5));
   }

   // $FF: synthetic method
   static Nil $anonfun$sqrt$2(final int x$9) {
      return scala.package..MODULE$.Nil();
   }

   // $FF: synthetic method
   static Right $anonfun$sqrt$4(final int n) {
      return scala.package..MODULE$.Right().apply(BoxesRunTime.boxToInteger(n + 1));
   }

   // $FF: synthetic method
   static Trees.TreeApi $anonfun$divide$3(final Fuser $this, final int n) {
      return $this.c().universe().internal().reificationSupport().SyntacticApplied().apply($this.c().universe().internal().reificationSupport().SyntacticSelectTerm().apply($this.intLit(n), $this.c().universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon($this.c().universe().Literal().apply($this.c().universe().Constant().apply(BoxesRunTime.boxToInteger(1))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
   }

   // $FF: synthetic method
   static Trees.TreeApi $anonfun$sign$2(final Fuser $this, final int n) {
      return $this.intLit(n);
   }

   static void $init$(final Fuser $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class Approx implements Product, Serializable {
      private final Trees.TreeApi apx;
      private final Trees.TreeApi mes;
      private final Either ind;
      private final Trees.TreeApi exact;
      // $FF: synthetic field
      public final Fuser $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Trees.TreeApi apx() {
         return this.apx;
      }

      public Trees.TreeApi mes() {
         return this.mes;
      }

      public Either ind() {
         return this.ind;
      }

      public Trees.TreeApi exact() {
         return this.exact;
      }

      public Trees.TreeApi expr() {
         Trees.TreeApi ind0 = (Trees.TreeApi)this.ind().fold((t) -> t, (n) -> $anonfun$expr$2(this, BoxesRunTime.unboxToInt(n)));
         return this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticApplied().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticTypeApplied().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticSelectTerm().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().TermName().apply("_root_"), false), this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().TermName().apply("spire")), this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().TermName().apply("math")), this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().TermName().apply("FpFilter")), (List)(new .colon.colon(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().Liftable().liftTypeTag().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().A()), scala.collection.immutable.Nil..MODULE$))), (List)(new .colon.colon((List)(new .colon.colon(this.apx(), new .colon.colon(this.mes(), new .colon.colon(ind0, new .colon.colon(this.exact(), scala.collection.immutable.Nil..MODULE$))))), scala.collection.immutable.Nil..MODULE$)));
      }

      public Fused fused(final List stats0) {
         Tuple4 var4 = this.spire$macros$fpf$Fuser$Approx$$$outer().spire$macros$fpf$Fuser$$freshApproxNames();
         if (var4 != null) {
            Names.TermNameApi apx0 = (Names.TermNameApi)var4._1();
            Names.TermNameApi mes0 = (Names.TermNameApi)var4._2();
            Names.TermNameApi ind0 = (Names.TermNameApi)var4._3();
            Names.TermNameApi exact0 = (Names.TermNameApi)var4._4();
            Tuple4 var2 = new Tuple4(apx0, mes0, ind0, exact0);
            Names.TermNameApi apx0x = (Names.TermNameApi)var2._1();
            Names.TermNameApi mes0x = (Names.TermNameApi)var2._2();
            Names.TermNameApi ind0 = (Names.TermNameApi)var2._3();
            Names.TermNameApi exact0 = (Names.TermNameApi)var2._4();
            List indValDef = (List)this.ind().fold((t) -> {
               Trees.ValDefApi var3 = this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticValDef().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().NoMods(), ind0, this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), t);
               return scala.package..MODULE$.Nil().$colon$colon(var3);
            }, (x$2) -> $anonfun$fused$2(BoxesRunTime.unboxToInt(x$2)));
            List stats1 = (List)((IterableOps)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.ValOrDefDefApi[]{this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticValDef().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().NoMods(), apx0x, this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.apx()), this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticValDef().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().NoMods(), mes0x, this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.mes()), this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticDefDef().apply(this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().NoMods(), exact0, scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, this.spire$macros$fpf$Fuser$Approx$$$outer().c().universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), this.exact())})))).$plus$plus(indValDef);
            return this.spire$macros$fpf$Fuser$Approx$$$outer().new Fused((List)stats0.$plus$plus(stats1), apx0x, mes0x, this.ind().left().map((x$3) -> ind0), exact0);
         } else {
            throw new MatchError(var4);
         }
      }

      public Approx copy(final Trees.TreeApi apx, final Trees.TreeApi mes, final Either ind, final Trees.TreeApi exact) {
         return this.spire$macros$fpf$Fuser$Approx$$$outer().new Approx(apx, mes, ind, exact);
      }

      public Trees.TreeApi copy$default$1() {
         return this.apx();
      }

      public Trees.TreeApi copy$default$2() {
         return this.mes();
      }

      public Either copy$default$3() {
         return this.ind();
      }

      public Trees.TreeApi copy$default$4() {
         return this.exact();
      }

      public String productPrefix() {
         return "Approx";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.apx();
               break;
            case 1:
               var10000 = this.mes();
               break;
            case 2:
               var10000 = this.ind();
               break;
            case 3:
               var10000 = this.exact();
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
         return x$1 instanceof Approx;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "apx";
               break;
            case 1:
               var10000 = "mes";
               break;
            case 2:
               var10000 = "ind";
               break;
            case 3:
               var10000 = "exact";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var13;
         if (this != x$1) {
            label86: {
               boolean var2;
               if (x$1 instanceof Approx && ((Approx)x$1).spire$macros$fpf$Fuser$Approx$$$outer() == this.spire$macros$fpf$Fuser$Approx$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label62: {
                     label76: {
                        Approx var4 = (Approx)x$1;
                        Trees.TreeApi var10000 = this.apx();
                        Trees.TreeApi var5 = var4.apx();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label76;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label76;
                        }

                        var10000 = this.mes();
                        Trees.TreeApi var6 = var4.mes();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label76;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label76;
                        }

                        Either var10 = this.ind();
                        Either var7 = var4.ind();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label76;
                           }
                        } else if (!var10.equals(var7)) {
                           break label76;
                        }

                        Trees.TreeApi var11 = this.exact();
                        Trees.TreeApi var8 = var4.exact();
                        if (var11 == null) {
                           if (var8 != null) {
                              break label76;
                           }
                        } else if (!var11.equals(var8)) {
                           break label76;
                        }

                        if (var4.canEqual(this)) {
                           var13 = true;
                           break label62;
                        }
                     }

                     var13 = false;
                  }

                  if (var13) {
                     break label86;
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
      public Fuser spire$macros$fpf$Fuser$Approx$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Trees.TreeApi $anonfun$expr$2(final Approx $this, final int n) {
         return $this.spire$macros$fpf$Fuser$Approx$$$outer().intLit(n);
      }

      // $FF: synthetic method
      public static final Nil $anonfun$fused$2(final int x$2) {
         return scala.package..MODULE$.Nil();
      }

      public Approx(final Trees.TreeApi apx, final Trees.TreeApi mes, final Either ind, final Trees.TreeApi exact) {
         this.apx = apx;
         this.mes = mes;
         this.ind = ind;
         this.exact = exact;
         if (Fuser.this == null) {
            throw null;
         } else {
            this.$outer = Fuser.this;
            super();
            Product.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Approx$ extends AbstractFunction4 implements Serializable {
      // $FF: synthetic field
      private final Fuser $outer;

      public final String toString() {
         return "Approx";
      }

      public Approx apply(final Trees.TreeApi apx, final Trees.TreeApi mes, final Either ind, final Trees.TreeApi exact) {
         return this.$outer.new Approx(apx, mes, ind, exact);
      }

      public Option unapply(final Approx x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(x$0.apx(), x$0.mes(), x$0.ind(), x$0.exact())));
      }

      public Approx$() {
         if (Fuser.this == null) {
            throw null;
         } else {
            this.$outer = Fuser.this;
            super();
         }
      }
   }

   public class Fused implements Product, Serializable {
      private final List stats;
      private final Names.TermNameApi apx;
      private final Names.TermNameApi mes;
      private final Either ind;
      private final Names.TermNameApi exact;
      // $FF: synthetic field
      public final Fuser $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public List stats() {
         return this.stats;
      }

      public Names.TermNameApi apx() {
         return this.apx;
      }

      public Names.TermNameApi mes() {
         return this.mes;
      }

      public Either ind() {
         return this.ind;
      }

      public Names.TermNameApi exact() {
         return this.exact;
      }

      public Approx approx() {
         return this.spire$macros$fpf$Fuser$Fused$$$outer().new Approx(this.spire$macros$fpf$Fuser$Fused$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.apx(), false), this.spire$macros$fpf$Fuser$Fused$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.mes(), false), this.ind().left().map((ind0) -> this.spire$macros$fpf$Fuser$Fused$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(ind0, false)), this.spire$macros$fpf$Fuser$Fused$$$outer().c().universe().internal().reificationSupport().SyntacticTermIdent().apply(this.exact(), false));
      }

      public Trees.TreeApi expr() {
         return spire.macros.compat..MODULE$.resetLocalAttrs(this.spire$macros$fpf$Fuser$Fused$$$outer().c(), this.spire$macros$fpf$Fuser$Fused$$$outer().c().universe().Block().apply(this.stats(), this.approx().expr()));
      }

      public Fused copy(final List stats, final Names.TermNameApi apx, final Names.TermNameApi mes, final Either ind, final Names.TermNameApi exact) {
         return this.spire$macros$fpf$Fuser$Fused$$$outer().new Fused(stats, apx, mes, ind, exact);
      }

      public List copy$default$1() {
         return this.stats();
      }

      public Names.TermNameApi copy$default$2() {
         return this.apx();
      }

      public Names.TermNameApi copy$default$3() {
         return this.mes();
      }

      public Either copy$default$4() {
         return this.ind();
      }

      public Names.TermNameApi copy$default$5() {
         return this.exact();
      }

      public String productPrefix() {
         return "Fused";
      }

      public int productArity() {
         return 5;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.stats();
               break;
            case 1:
               var10000 = this.apx();
               break;
            case 2:
               var10000 = this.mes();
               break;
            case 3:
               var10000 = this.ind();
               break;
            case 4:
               var10000 = this.exact();
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
         return x$1 instanceof Fused;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "stats";
               break;
            case 1:
               var10000 = "apx";
               break;
            case 2:
               var10000 = "mes";
               break;
            case 3:
               var10000 = "ind";
               break;
            case 4:
               var10000 = "exact";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var15;
         if (this != x$1) {
            label95: {
               boolean var2;
               if (x$1 instanceof Fused && ((Fused)x$1).spire$macros$fpf$Fuser$Fused$$$outer() == this.spire$macros$fpf$Fuser$Fused$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label71: {
                     label85: {
                        Fused var4 = (Fused)x$1;
                        List var10000 = this.stats();
                        List var5 = var4.stats();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label85;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label85;
                        }

                        Names.TermNameApi var10 = this.apx();
                        Names.TermNameApi var6 = var4.apx();
                        if (var10 == null) {
                           if (var6 != null) {
                              break label85;
                           }
                        } else if (!var10.equals(var6)) {
                           break label85;
                        }

                        var10 = this.mes();
                        Names.TermNameApi var7 = var4.mes();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label85;
                           }
                        } else if (!var10.equals(var7)) {
                           break label85;
                        }

                        Either var12 = this.ind();
                        Either var8 = var4.ind();
                        if (var12 == null) {
                           if (var8 != null) {
                              break label85;
                           }
                        } else if (!var12.equals(var8)) {
                           break label85;
                        }

                        Names.TermNameApi var13 = this.exact();
                        Names.TermNameApi var9 = var4.exact();
                        if (var13 == null) {
                           if (var9 != null) {
                              break label85;
                           }
                        } else if (!var13.equals(var9)) {
                           break label85;
                        }

                        if (var4.canEqual(this)) {
                           var15 = true;
                           break label71;
                        }
                     }

                     var15 = false;
                  }

                  if (var15) {
                     break label95;
                  }
               }

               var15 = false;
               return var15;
            }
         }

         var15 = true;
         return var15;
      }

      // $FF: synthetic method
      public Fuser spire$macros$fpf$Fuser$Fused$$$outer() {
         return this.$outer;
      }

      public Fused(final List stats, final Names.TermNameApi apx, final Names.TermNameApi mes, final Either ind, final Names.TermNameApi exact) {
         this.stats = stats;
         this.apx = apx;
         this.mes = mes;
         this.ind = ind;
         this.exact = exact;
         if (Fuser.this == null) {
            throw null;
         } else {
            this.$outer = Fuser.this;
            super();
            Product.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Fused$ extends AbstractFunction5 implements Serializable {
      // $FF: synthetic field
      private final Fuser $outer;

      public final String toString() {
         return "Fused";
      }

      public Fused apply(final List stats, final Names.TermNameApi apx, final Names.TermNameApi mes, final Either ind, final Names.TermNameApi exact) {
         return this.$outer.new Fused(stats, apx, mes, ind, exact);
      }

      public Option unapply(final Fused x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(x$0.stats(), x$0.apx(), x$0.mes(), x$0.ind(), x$0.exact())));
      }

      public Fused$() {
         if (Fuser.this == null) {
            throw null;
         } else {
            this.$outer = Fuser.this;
            super();
         }
      }
   }
}
