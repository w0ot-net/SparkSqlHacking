package scala.reflect;

import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArraySeq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005!Mr\u0001CA:\u0003kB\t!a \u0007\u0011\u0005\r\u0015Q\u000fE\u0001\u0003\u000bCq!a$\u0002\t\u0003\t\t\nC\u0004\u0002\u0014\u0006!\t!!&\u0007\u0011\u0005\r\u0017AAA;\u0003\u000bDq!a$\u0005\t\u0003\ty\rC\u0004\u0002V\u0012!\t!a6\t\u000f\u0005uH\u0001\"\u0011\u0002\u0000\"9!\u0011\u0004\u0003\u0005B\tm\u0001b\u0002B\u0018\t\u0011\u0005#\u0011\u0007\u0005\b\u0005s!A\u0011\tB\u001e\u0011\u001d\u00119\u0005\u0002C\u0005\u0005\u0013B\u0011Ba\u0016\u0002\u0005\u0004%\tA!\u0017\t\u0011\tm\u0013\u0001)A\u0005\u0003#4\u0001B!\u0018\u0002\u0005\u0005U$q\f\u0005\b\u0003\u001fsA\u0011\u0001B5\u0011\u001d\t)N\u0004C\u0001\u0005[Bq!!@\u000f\t\u0003\u0012)\bC\u0004\u0003\u001a9!\tE! \t\u000f\t=b\u0002\"\u0011\u0003\u0004\"9!\u0011\b\b\u0005B\t\u001d\u0005b\u0002B$\u001d\u0011%!\u0011\n\u0005\n\u0005\u001f\u000b!\u0019!C\u0001\u0005#C\u0001Ba%\u0002A\u0003%!1\u000e\u0004\t\u0005+\u000b!!!\u001e\u0003\u0018\"9\u0011q\u0012\r\u0005\u0002\t\u0005\u0006bBAk1\u0011\u0005!Q\u0015\u0005\b\u0003{DB\u0011\tBX\u0011\u001d\u0011I\u0002\u0007C!\u0005oCqAa\f\u0019\t\u0003\u0012i\fC\u0004\u0003:a!\tE!1\t\u000f\t\u001d\u0003\u0004\"\u0003\u0003J!I!\u0011Z\u0001C\u0002\u0013\u0005!1\u001a\u0005\t\u0005\u001b\f\u0001\u0015!\u0003\u0003$\u001aA!qZ\u0001\u0003\u0003k\u0012\t\u000eC\u0004\u0002\u0010\n\"\tA!6\t\u000f\u0005U'\u0005\"\u0001\u0003Z\"9\u0011Q \u0012\u0005B\t\r\bb\u0002B\rE\u0011\u0005#1\u001e\u0005\b\u0005_\u0011C\u0011\tBy\u0011\u001d\u0011ID\tC!\u0005kDqAa\u0012#\t\u0013\u0011I\u0005C\u0005\u0003~\u0006\u0011\r\u0011\"\u0001\u0003\u0000\"A1\u0011A\u0001!\u0002\u0013\u00119N\u0002\u0005\u0004\u0004\u0005\u0011\u0011QOB\u0003\u0011\u001d\ty\t\fC\u0001\u0007\u001fAq!!6-\t\u0003\u0019\u0019\u0002C\u0004\u0002~2\"\tea\u0007\t\u000f\teA\u0006\"\u0011\u0004$!9!q\u0006\u0017\u0005B\r%\u0002b\u0002B\u001dY\u0011\u00053Q\u0006\u0005\b\u0005\u000fbC\u0011\u0002B%\u0011%\u0019)$\u0001b\u0001\n\u0003\u00199\u0004\u0003\u0005\u0004:\u0005\u0001\u000b\u0011BB\t\r!\u0019Y$\u0001\u0002\u0002v\ru\u0002bBAHm\u0011\u00051q\t\u0005\b\u0003+4D\u0011AB&\u0011\u001d\tiP\u000eC!\u0007'BqA!\u00077\t\u0003\u001aY\u0006C\u0004\u00030Y\"\te!\u0019\t\u000f\teb\u0007\"\u0011\u0004f!9!q\t\u001c\u0005\n\t%\u0003\"CB7\u0003\t\u0007I\u0011AB8\u0011!\u0019\t(\u0001Q\u0001\n\r%c\u0001CB:\u0003\t\t)h!\u001e\t\u000f\u0005=\u0005\t\"\u0001\u0004\u0000!9\u0011Q\u001b!\u0005\u0002\r\r\u0005bBA\u007f\u0001\u0012\u000531\u0012\u0005\b\u00053\u0001E\u0011IBJ\u0011\u001d\u0011y\u0003\u0011C!\u00073CqA!\u000fA\t\u0003\u001ai\nC\u0004\u0003H\u0001#IA!\u0013\t\u0013\r\u0015\u0016A1A\u0005\u0002\r\u001d\u0006\u0002CBU\u0003\u0001\u0006Ia!!\u0007\u0011\r-\u0016AAA;\u0007[Cq!a$K\t\u0003\u00199\fC\u0004\u0002V*#\taa/\t\u000f\u0005u(\n\"\u0011\u0004D\"9!\u0011\u0004&\u0005B\r-\u0007b\u0002B\u0018\u0015\u0012\u00053\u0011\u001b\u0005\b\u0005sQE\u0011IBk\u0011\u001d\u00119E\u0013C\u0005\u0005\u0013B\u0011b!8\u0002\u0005\u0004%\taa8\t\u0011\r\u0005\u0018\u0001)A\u0005\u0007s3\u0001ba9\u0002\u0005\u0005U4Q\u001d\u0005\b\u0003\u001f#F\u0011ABx\u0011\u001d\t)\u000e\u0016C\u0001\u0007gDq!!@U\t\u0003\u001ai\u0010C\u0004\u0003\u001aQ#\t\u0005\"\u0002\t\u000f\t=B\u000b\"\u0011\u0005\f!9Aq\u0002+\u0005R\u0011E\u0001b\u0002B\u001d)\u0012\u0005CQ\u0006\u0005\b\u0005\u000f\"F\u0011\u0002B%\u0011%!)$\u0001b\u0001\n\u0003!9\u0004\u0003\u0005\u0005:\u0005\u0001\u000b\u0011BBy\u0011!!Y$\u0001Q\u0001\n\u0011u\u0002\u0002\u0003C$\u0003\u0001\u0006I\u0001\"\u0013\t\u0011\u0011]\u0013\u0001)A\u0005\t32a\u0001\"\u0019\u0002\r\u0011\r\u0004bBAHE\u0012\u0005Q\u0011\u0005\u0005\b\u0003{\u0014G\u0011IC\u0013\u0011\u001d)YC\u0019C!\u000b[AqAa\u0012c\t\u0013\u0011I\u0005C\u0005\u0006H\u0005\u0011\r\u0011\"\u0001\u0006J!AQQJ\u0001!\u0002\u0013)YE\u0002\u0004\u0006P\u00051Q\u0011\u000b\u0005\b\u0003\u001fKG\u0011AC+\u0011\u001d\ti0\u001bC!\u000b3Bq!b\u000bj\t\u0003*y\u0006C\u0004\u0003H%$IA!\u0013\t\u0013\u0015=\u0014A1A\u0005\u0002\u0015E\u0004\u0002CC;\u0003\u0001\u0006I!b\u001d\t\u0013\u0015]\u0014A1A\u0005\u0002\u0015e\u0004\u0002CC?\u0003\u0001\u0006I!b\u001f\u0007\r\u0015}\u0014ABCA\u0011\u001d\tyI\u001dC\u0001\u000b\u0017Cq!!@s\t\u0003*y\tC\u0004\u0006,I$\t%\"&\t\u000f\t\u001d#\u000f\"\u0003\u0003J!IQQU\u0001C\u0002\u0013\u0005Qq\u0015\u0005\t\u000bW\u000b\u0001\u0015!\u0003\u0006*\u001a1QQV\u0001\u0007\u000b_Cq!a$z\t\u0003)I\fC\u0004\u0002~f$\t%\"0\t\u000f\u0015-\u0012\u0010\"\u0011\u0006D\"9!qI=\u0005\n\t%\u0003\"CCj\u0003\t\u0007I\u0011ACk\u0011!)I.\u0001Q\u0001\n\u0015]gABCn\u0003\u0019)i\u000e\u0003\u0005\u0002\u0010\u0006\u0005A\u0011ACq\u0011!\ti0!\u0001\u0005B\u0015\u0015\b\u0002CC\u0016\u0003\u0003!\t%b;\t\u0011\t\u001d\u0013\u0011\u0001C\u0005\u0005\u0013B\u0011\"b?\u0002\u0005\u0004%\t!\"@\t\u0011\u0019\u0005\u0011\u0001)A\u0005\u000b\u007f4aAb\u0001\u0002\r\u0019\u0015\u0001b\u0003B*\u0003\u001f\u0011\t\u0011)A\u0005\u0003\u000fC\u0001\"a$\u0002\u0010\u0011\u0005a\u0011\u0003\u0005\f\u0003+\fy\u0001#b\u0001\n\u000319\u0002C\u0006\u0005V\u0006=\u0001R1A\u0005B\u0019\r\u0002b\u0002D\u0014\u0003\u0011\u0005a\u0011\u0006\u0005\b\rk\tA\u0011\u0001D\u001c\u0011\u001d1)$\u0001C\u0001\r\u001fBqA\"\u000e\u0002\t\u00031\tIB\u0004\u0005h\u0005\tI\u0001\"\u001b\t\u001b\u0011\u0015\u0018\u0011\u0005B\u0001B\u0003%Aq]A\u001b\u0011-!).!\t\u0003\u0006\u0004%\t\u0005\"=\t\u0017\u0011]\u0018\u0011\u0005B\u0001B\u0003%A1\u001f\u0005\t\u0003\u001f\u000b\t\u0003\"\u0001\u0005z\"AQ\u0011BA\u0011\t\u0003*Y\u0001\u0003\u0006\u0006\u0012\u0005\u0005\"\u0019!C!\u000b'A\u0011\"\"\u0006\u0002\"\u0001\u0006IAa\u0003\u0007\r\u0011=\u0014\u0001\u0002C9\u0011-!y(!\r\u0003\u0002\u0003\u0006I\u0001\"!\t\u0017\u0005U\u0017\u0011\u0007BC\u0002\u0013\u0005AQ\u0012\u0005\f\t7\u000b\tD!A!\u0002\u0013!y\tC\u0006\u0005\u001e\u0006E\"Q1A\u0005B\u0011}\u0005b\u0003CW\u0003c\u0011\t\u0011)A\u0005\tCC\u0001\"a$\u00022\u0011\u0005Aq\u0016\u0005\t\t+\f\t\u0004\"\u0011\u0005X\"9a\u0011W\u0001\u0005\u0002\u0019MfA\u0002Dg\u0003\u00111y\rC\u0006\u0005\u0000\u0005\r#\u0011!Q\u0001\n\u0019e\u0007b\u0003Dr\u0003\u0007\u0012\t\u0011)A\u0005\tgD1B\":\u0002D\t\u0005\t\u0015!\u0003\u0007h\"YaQNA\"\u0005\u0003\u0005\u000b\u0011\u0002Dy\u0011!\ty)a\u0011\u0005\u0002\u001d\r\u0001\u0002CAk\u0003\u0007\"\ta\"\u000b\t\u0015\u0011u\u00151\tb\u0001\n\u0003:\u0019\u0004C\u0005\u0005.\u0006\r\u0003\u0015!\u0003\b6!AAQ[A\"\t\u0003\"9\u000eC\u0004\bB\u0005!\tab\u0011\u0007\r\u001dU\u0014\u0001BD<\u0011-9\t)!\u0017\u0003\u0002\u0003\u0006Iab!\t\u0017\u0019\u0015\u0018\u0011\fB\u0001B\u0003%qQ\u0012\u0005\t\u0003\u001f\u000bI\u0006\"\u0001\b\u0018\"A\u0011Q[A-\t\u00039y\u000b\u0003\u0005\u0005V\u0006eC\u0011\tCl\u0011\u001d9i,\u0001C\u0001\u000f\u007f3aa\"9\u0002\t\u001d\r\bbCDw\u0003O\u0012\t\u0011)A\u0005\u000f_D\u0001\"a$\u0002h\u0011\u0005q1 \u0005\t\u0003+\f9\u0007\"\u0001\t\f!AAQ[A4\t\u0003B)\u0002C\u0004\t\u001a\u0005!\t\u0001c\u0007\u0002\u001f5\u000bg.\u001b4fgR4\u0015m\u0019;pefTA!a\u001e\u0002z\u00059!/\u001a4mK\u000e$(BAA>\u0003\u0015\u00198-\u00197b\u0007\u0001\u00012!!!\u0002\u001b\t\t)HA\bNC:Lg-Z:u\r\u0006\u001cGo\u001c:z'\r\t\u0011q\u0011\t\u0005\u0003\u0013\u000bY)\u0004\u0002\u0002z%!\u0011QRA=\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\"!a \u0002\u001dY\fG.^3NC:Lg-Z:ugV\u0011\u0011q\u0013\t\u0007\u00033\u000by*!*\u000f\t\u0005%\u00151T\u0005\u0005\u0003;\u000bI(A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0005\u00161\u0015\u0002\u0005\u0019&\u001cHO\u0003\u0003\u0002\u001e\u0006e\u0004\u0007BAT\u0003c\u0003b!!!\u0002*\u00065\u0016\u0002BAV\u0003k\u0012a\"\u00118z-\u0006dW*\u00198jM\u0016\u001cH\u000f\u0005\u0003\u00020\u0006EF\u0002\u0001\u0003\f\u0003g\u001b\u0011\u0011!A\u0001\u0006\u0003\t)L\u0001\u0003`IE:\u0014\u0003BA\\\u0003{\u0003B!!#\u0002:&!\u00111XA=\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!!#\u0002@&!\u0011\u0011YA=\u0005\r\te.\u001f\u0002\r\u0005f$X-T1oS\u001a,7\u000f^\n\u0004\t\u0005\u001d\u0007CBAA\u0003S\u000bI\r\u0005\u0003\u0002\n\u0006-\u0017\u0002BAg\u0003s\u0012AAQ=uKR\u0011\u0011\u0011\u001b\t\u0004\u0003'$Q\"A\u0001\u0002\u0019I,h\u000e^5nK\u000ec\u0017m]:\u0016\u0005\u0005e\u0007CBAn\u0003S\fyO\u0004\u0003\u0002^\u0006\u0015\b\u0003BAp\u0003sj!!!9\u000b\t\u0005\r\u0018QP\u0001\u0007yI|w\u000e\u001e \n\t\u0005\u001d\u0018\u0011P\u0001\u0007!J,G-\u001a4\n\t\u0005-\u0018Q\u001e\u0002\u0006\u00072\f7o\u001d\u0006\u0005\u0003O\fI\b\u0005\u0003\u0002r\u0006mXBAAz\u0015\u0011\t)0a>\u0002\t1\fgn\u001a\u0006\u0003\u0003s\fAA[1wC&!\u0011QZAz\u0003!qWm^!se\u0006LH\u0003\u0002B\u0001\u0005\u000f\u0001b!!#\u0003\u0004\u0005%\u0017\u0002\u0002B\u0003\u0003s\u0012Q!\u0011:sCfDqA!\u0003\b\u0001\u0004\u0011Y!A\u0002mK:\u0004B!!#\u0003\u000e%!!qBA=\u0005\rIe\u000e\u001e\u0015\u0004\u000f\tM\u0001\u0003BAE\u0005+IAAa\u0006\u0002z\t1\u0011N\u001c7j]\u0016\fqB\\3x/J\f\u0007\u000f]3e\u0003J\u0014\u0018-\u001f\u000b\u0005\u0005;\u0011i\u0003\u0005\u0004\u0003 \t%\u0012\u0011Z\u0007\u0003\u0005CQAAa\t\u0003&\u00059Q.\u001e;bE2,'\u0002\u0002B\u0014\u0003s\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011YC!\t\u0003\u0011\u0005\u0013(/Y=TKFDqA!\u0003\t\u0001\u0004\u0011Y!A\boK^\f%O]1z\u0005VLG\u000eZ3s)\t\u0011\u0019\u0004\u0005\u0004\u0003 \tU\u0012\u0011Z\u0005\u0005\u0005o\u0011\tC\u0001\u0007BeJ\f\u0017PQ;jY\u0012,'/A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\tu\"1\t\t\u0007\u0003\u0013\u0013y$!3\n\t\t\u0005\u0013\u0011\u0010\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\t\u0015#\u00021\u0001\u0002>\u0006\t\u00010A\u0006sK\u0006$'+Z:pYZ,GCAA_Q\u001d!!Q\nB*\u0005+\u0002B!!#\u0003P%!!\u0011KA=\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u0003\u0011\u0011\u0015\u0010^3\u0016\u0005\u0005E\u0017!\u0002\"zi\u0016\u0004#!D*i_J$X*\u00198jM\u0016\u001cHoE\u0002\u000f\u0005C\u0002b!!!\u0002*\n\r\u0004\u0003BAE\u0005KJAAa\u001a\u0002z\t)1\u000b[8siR\u0011!1\u000e\t\u0004\u0003'tQC\u0001B8!\u0019\tY.!;\u0003rA!\u0011\u0011\u001fB:\u0013\u0011\u00119'a=\u0015\t\t]$\u0011\u0010\t\u0007\u0003\u0013\u0013\u0019Aa\u0019\t\u000f\t%\u0011\u00031\u0001\u0003\f!\u001a\u0011Ca\u0005\u0015\t\t}$\u0011\u0011\t\u0007\u0005?\u0011ICa\u0019\t\u000f\t%!\u00031\u0001\u0003\fQ\u0011!Q\u0011\t\u0007\u0005?\u0011)Da\u0019\u0015\t\t%%1\u0012\t\u0007\u0003\u0013\u0013yDa\u0019\t\u000f\t\u0015C\u00031\u0001\u0002>\":aB!\u0014\u0003T\tU\u0013!B*i_J$XC\u0001B6\u0003\u0019\u0019\u0006n\u001c:uA\ta1\t[1s\u001b\u0006t\u0017NZ3tiN\u0019\u0001D!'\u0011\r\u0005\u0005\u0015\u0011\u0016BN!\u0011\tII!(\n\t\t}\u0015\u0011\u0010\u0002\u0005\u0007\"\f'\u000f\u0006\u0002\u0003$B\u0019\u00111\u001b\r\u0016\u0005\t\u001d\u0006CBAn\u0003S\u0014I\u000b\u0005\u0003\u0002r\n-\u0016\u0002\u0002BW\u0003g\u0014\u0011b\u00115be\u0006\u001cG/\u001a:\u0015\t\tE&1\u0017\t\u0007\u0003\u0013\u0013\u0019Aa'\t\u000f\t%1\u00041\u0001\u0003\f!\u001a1Da\u0005\u0015\t\te&1\u0018\t\u0007\u0005?\u0011ICa'\t\u000f\t%A\u00041\u0001\u0003\fQ\u0011!q\u0018\t\u0007\u0005?\u0011)Da'\u0015\t\t\r'Q\u0019\t\u0007\u0003\u0013\u0013yDa'\t\u000f\t\u0015c\u00041\u0001\u0002>\":\u0001D!\u0014\u0003T\tU\u0013\u0001B\"iCJ,\"Aa)\u0002\u000b\rC\u0017M\u001d\u0011\u0003\u0017%sG/T1oS\u001a,7\u000f^\n\u0004E\tM\u0007CBAA\u0003S\u0013Y\u0001\u0006\u0002\u0003XB\u0019\u00111\u001b\u0012\u0016\u0005\tm\u0007CBAn\u0003S\u0014i\u000e\u0005\u0003\u0002r\n}\u0017\u0002\u0002Bq\u0003g\u0014q!\u00138uK\u001e,'\u000f\u0006\u0003\u0003f\n\u001d\bCBAE\u0005\u0007\u0011Y\u0001C\u0004\u0003\n\u0015\u0002\rAa\u0003)\u0007\u0015\u0012\u0019\u0002\u0006\u0003\u0003n\n=\bC\u0002B\u0010\u0005S\u0011Y\u0001C\u0004\u0003\n\u0019\u0002\rAa\u0003\u0015\u0005\tM\bC\u0002B\u0010\u0005k\u0011Y\u0001\u0006\u0003\u0003x\ne\bCBAE\u0005\u007f\u0011Y\u0001C\u0004\u0003F!\u0002\r!!0)\u000f\t\u0012iEa\u0015\u0003V\u0005\u0019\u0011J\u001c;\u0016\u0005\t]\u0017\u0001B%oi\u0002\u0012A\u0002T8oO6\u000bg.\u001b4fgR\u001c2\u0001LB\u0004!\u0019\t\t)!+\u0004\nA!\u0011\u0011RB\u0006\u0013\u0011\u0019i!!\u001f\u0003\t1{gn\u001a\u000b\u0003\u0007#\u00012!a5-+\t\u0019)\u0002\u0005\u0004\u0002\\\u0006%8q\u0003\t\u0005\u0003c\u001cI\"\u0003\u0003\u0004\u000e\u0005MH\u0003BB\u000f\u0007?\u0001b!!#\u0003\u0004\r%\u0001b\u0002B\u0005_\u0001\u0007!1\u0002\u0015\u0004_\tMA\u0003BB\u0013\u0007O\u0001bAa\b\u0003*\r%\u0001b\u0002B\u0005a\u0001\u0007!1\u0002\u000b\u0003\u0007W\u0001bAa\b\u00036\r%A\u0003BB\u0018\u0007c\u0001b!!#\u0003@\r%\u0001b\u0002B#e\u0001\u0007\u0011Q\u0018\u0015\bY\t5#1\u000bB+\u0003\u0011auN\\4\u0016\u0005\rE\u0011!\u0002'p]\u001e\u0004#!\u0004$m_\u0006$X*\u00198jM\u0016\u001cHoE\u00027\u0007\u007f\u0001b!!!\u0002*\u000e\u0005\u0003\u0003BAE\u0007\u0007JAa!\u0012\u0002z\t)a\t\\8biR\u00111\u0011\n\t\u0004\u0003'4TCAB'!\u0019\tY.!;\u0004PA!\u0011\u0011_B)\u0013\u0011\u0019)%a=\u0015\t\rU3q\u000b\t\u0007\u0003\u0013\u0013\u0019a!\u0011\t\u000f\t%\u0011\b1\u0001\u0003\f!\u001a\u0011Ha\u0005\u0015\t\ru3q\f\t\u0007\u0005?\u0011Ic!\u0011\t\u000f\t%!\b1\u0001\u0003\fQ\u001111\r\t\u0007\u0005?\u0011)d!\u0011\u0015\t\r\u001d4\u0011\u000e\t\u0007\u0003\u0013\u0013yd!\u0011\t\u000f\t\u0015C\b1\u0001\u0002>\":aG!\u0014\u0003T\tU\u0013!\u0002$m_\u0006$XCAB%\u0003\u00191En\\1uA\tqAi\\;cY\u0016l\u0015M\\5gKN$8c\u0001!\u0004xA1\u0011\u0011QAU\u0007s\u0002B!!#\u0004|%!1QPA=\u0005\u0019!u.\u001e2mKR\u00111\u0011\u0011\t\u0004\u0003'\u0004UCABC!\u0019\tY.!;\u0004\bB!\u0011\u0011_BE\u0013\u0011\u0019i(a=\u0015\t\r55q\u0012\t\u0007\u0003\u0013\u0013\u0019a!\u001f\t\u000f\t%1\t1\u0001\u0003\f!\u001a1Ia\u0005\u0015\t\rU5q\u0013\t\u0007\u0005?\u0011Ic!\u001f\t\u000f\t%A\t1\u0001\u0003\fQ\u001111\u0014\t\u0007\u0005?\u0011)d!\u001f\u0015\t\r}5\u0011\u0015\t\u0007\u0003\u0013\u0013yd!\u001f\t\u000f\t\u0015c\t1\u0001\u0002>\":\u0001I!\u0014\u0003T\tU\u0013A\u0002#pk\ndW-\u0006\u0002\u0004\u0002\u00069Ai\\;cY\u0016\u0004#a\u0004\"p_2,\u0017M\\'b]&4Wm\u001d;\u0014\u0007)\u001by\u000b\u0005\u0004\u0002\u0002\u0006%6\u0011\u0017\t\u0005\u0003\u0013\u001b\u0019,\u0003\u0003\u00046\u0006e$a\u0002\"p_2,\u0017M\u001c\u000b\u0003\u0007s\u00032!a5K+\t\u0019i\f\u0005\u0004\u0002\\\u0006%8q\u0018\t\u0005\u0003c\u001c\t-\u0003\u0003\u00046\u0006MH\u0003BBc\u0007\u000f\u0004b!!#\u0003\u0004\rE\u0006b\u0002B\u0005\u001b\u0002\u0007!1\u0002\u0015\u0004\u001b\nMA\u0003BBg\u0007\u001f\u0004bAa\b\u0003*\rE\u0006b\u0002B\u0005\u001d\u0002\u0007!1\u0002\u000b\u0003\u0007'\u0004bAa\b\u00036\rEF\u0003BBl\u00073\u0004b!!#\u0003@\rE\u0006b\u0002B#!\u0002\u0007\u0011Q\u0018\u0015\b\u0015\n5#1\u000bB+\u0003\u001d\u0011un\u001c7fC:,\"a!/\u0002\u0011\t{w\u000e\\3b]\u0002\u0012A\"\u00168ji6\u000bg.\u001b4fgR\u001c2\u0001VBt!\u0019\t\t)!+\u0004jB!\u0011\u0011RBv\u0013\u0011\u0019i/!\u001f\u0003\tUs\u0017\u000e\u001e\u000b\u0003\u0007c\u00042!a5U+\t\u0019)\u0010\u0005\u0004\u0002\\\u0006%8q\u001f\t\u0005\u0003c\u001cI0\u0003\u0003\u0004|\u0006M(\u0001\u0002,pS\u0012$Baa@\u0005\u0002A1\u0011\u0011\u0012B\u0002\u0007SDqA!\u0003X\u0001\u0004\u0011Y\u0001K\u0002X\u0005'!B\u0001b\u0002\u0005\nA1!q\u0004B\u0015\u0007SDqA!\u0003Y\u0001\u0004\u0011Y\u0001\u0006\u0002\u0005\u000eA1!q\u0004B\u001b\u0007S\f!\"\u0019:sCf\u001cE.Y:t+\u0011!\u0019\u0002b\u0007\u0015\t\u0011UAq\u0004\t\u0007\u00037\fI\u000fb\u0006\u0011\r\u0005%%1\u0001C\r!\u0011\ty\u000bb\u0007\u0005\u000f\u0011u!L1\u0001\u00026\n\tA\u000bC\u0004\u0005\"i\u0003\r\u0001b\t\u0002\u0005Q\u0004\b\u0007\u0002C\u0013\tS\u0001b!a7\u0002j\u0012\u001d\u0002\u0003BAX\tS!A\u0002b\u000b\u0005 \u0005\u0005\t\u0011!B\u0001\u0003k\u0013Aa\u0018\u00132qQ!Aq\u0006C\u0019!\u0019\tIIa\u0010\u0004j\"9!QI.A\u0002\u0005u\u0006f\u0002+\u0003N\tM#QK\u0001\u0005+:LG/\u0006\u0002\u0004r\u0006)QK\\5uA\u0005QqJ\u00196fGR$\u0016\fU#\u0011\r\u0005EHq\bC!\u0013\u0011\tY/a=\u0011\t\u0005EH1I\u0005\u0005\t\u000b\n\u0019P\u0001\u0004PE*,7\r^\u0001\f\u001d>$\b.\u001b8h)f\u0003V\t\u0005\u0004\u0002r\u0012}B1\n\t\u0005\t\u001b\"\u0019&\u0004\u0002\u0005P)!A\u0011KA=\u0003\u001d\u0011XO\u001c;j[\u0016LA\u0001\"\u0016\u0005P\tAaj\u001c;iS:<G%\u0001\u0005Ok2dG+\u0017)F!\u0019\t\t\u0010b\u0010\u0005\\A!AQ\nC/\u0013\u0011!y\u0006b\u0014\u0003\u000b9+H\u000e\u001c\u0013\u0003\u0017\u0005s\u00170T1oS\u001a,7\u000f^\n\u0004E\u0012\u0015\u0004CBAj\u0003C\tiLA\bQQ\u0006tGo\\7NC:Lg-Z:u+\u0011!Y\u0007b9\u0014\t\u0005\u0005BQ\u000e\t\u0007\u0003'\f\t\u0004\"9\u0003#\rc\u0017m]:UsB,W*\u00198jM\u0016\u001cH/\u0006\u0003\u0005t\u0011u4CBA\u0019\u0003\u000f#)\b\u0005\u0004\u0002\u0002\u0012]D1P\u0005\u0005\ts\n)H\u0001\u0005NC:Lg-Z:u!\u0011\ty\u000b\" \u0005\u0011\u0011u\u0011\u0011\u0007b\u0001\u0003k\u000ba\u0001\u001d:fM&D\bCBAE\u0005\u007f!\u0019\t\r\u0003\u0005\u0006\u0012%\u0005CBAA\to\"9\t\u0005\u0003\u00020\u0012%E\u0001\u0004CF\u0003g\t\t\u0011!A\u0003\u0002\u0005U&\u0001B0%gI*\"\u0001b$1\t\u0011EEq\u0013\t\u0007\t'\u000bI\u000f\"&\u000f\t\u0005%\u0015Q\u001d\t\u0005\u0003_#9\n\u0002\u0007\u0005\u001a\u0006]\u0012\u0011!A\u0001\u0006\u0003\t)L\u0001\u0003`IM\u001a\u0014!\u0004:v]RLW.Z\"mCN\u001c\b%A\u0007usB,\u0017I]4v[\u0016tGo]\u000b\u0003\tC\u0003b!!'\u0002 \u0012\r\u0006\u0007\u0002CS\tS\u0003b!!!\u0005x\u0011\u001d\u0006\u0003BAX\tS#A\u0002b+\u0002<\u0005\u0005\t\u0011!B\u0001\u0003k\u0013Aa\u0018\u00134i\u0005qA/\u001f9f\u0003J<W/\\3oiN\u0004C\u0003\u0003CY\tg#y\f\"3\u0011\r\u0005M\u0017\u0011\u0007C>\u0011!!y(!\u0010A\u0002\u0011U\u0006CBAE\u0005\u007f!9\f\r\u0003\u0005:\u0012u\u0006CBAA\to\"Y\f\u0005\u0003\u00020\u0012uF\u0001\u0004CF\tg\u000b\t\u0011!A\u0003\u0002\u0005U\u0006\u0002CAk\u0003{\u0001\r\u0001\"11\t\u0011\rGq\u0019\t\u0007\t'\u000bI\u000f\"2\u0011\t\u0005=Fq\u0019\u0003\r\t3#y,!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0005\t\t;\u000bi\u00041\u0001\u0005LB1\u0011\u0011TAP\t\u001b\u0004D\u0001b4\u0005TB1\u0011\u0011\u0011C<\t#\u0004B!a,\u0005T\u0012aA1\u0016Ce\u0003\u0003\u0005\tQ!\u0001\u00026\u0006AAo\\*ue&tw\r\u0006\u0002\u0005ZB!\u0011\u0011\u001fCn\u0013\u0011!i.a=\u0003\rM#(/\u001b8hQ!\t\tD!\u0014\u0003T\tU\u0003\u0003BAX\tG$\u0001\u0002\"\b\u0002\"\t\u0007\u0011QW\u0001\u000e?J,h\u000e^5nK\u000ec\u0017m]:1\t\u0011%HQ\u001e\t\u0007\t'\u000bI\u000fb;\u0011\t\u0005=FQ\u001e\u0003\r\t_\f\u0019#!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0002\u0005?\u0012\u001a\u0014'\u0006\u0002\u0005tB!\u00111\u001cC{\u0013\u0011!i.!<\u0002\u0013Q|7\u000b\u001e:j]\u001e\u0004CC\u0002C~\t{,9\u0001\u0005\u0004\u0002T\u0006\u0005B\u0011\u001d\u0005\t\tK\fI\u00031\u0001\u0005\u0000B\"Q\u0011AC\u0003!\u0019!\u0019*!;\u0006\u0004A!\u0011qVC\u0003\t1!y\u000f\"@\u0002\u0002\u0003\u0005)\u0011AA[\u0011!!).!\u000bA\u0002\u0011M\u0018AB3rk\u0006d7\u000f\u0006\u0003\u00042\u00165\u0001\u0002CC\b\u0003W\u0001\r!!0\u0002\tQD\u0017\r^\u0001\tQ\u0006\u001c\bnQ8eKV\u0011!1B\u0001\nQ\u0006\u001c\bnQ8eK\u0002BC!a\f\u0006\u001aA!\u0011\u0011RC\u000e\u0013\u0011)i\"!\u001f\u0003\u0013Q\u0014\u0018M\\:jK:$\b\u0006CA\u0011\u0005\u001b\u0012\u0019F!\u0016\u0015\u0005\u0015\r\u0002cAAjER!QqEC\u0015!\u0019\tIIa\u0001\u0002>\"9!\u0011\u00023A\u0002\t-\u0011\u0001\u0005\u0013mKN\u001cHeY8m_:$C.Z:t)\u0011\u0019\t,b\f\t\u000f\u0015=Q\r1\u0001\u00062A\"Q1GC!!\u0019))$\"\u000f\u0006@9!\u0011\u0011QC\u001c\u0013\u0011\ti*!\u001e\n\t\u0015mRQ\b\u0002\u000e\u00072\f7o]'b]&4Wm\u001d;\u000b\t\u0005u\u0015Q\u000f\t\u0005\u0003_+\t\u0005\u0002\u0007\u0006D\u0015=\u0012\u0011!A\u0001\u0006\u0003\t)L\u0001\u0003`IEJ\u0004f\u00022\u0003N\tM#QK\u0001\u0004\u0003:LXCAC&!\u0019\t\t\tb\u001e\u0002>\u0006!\u0011I\\=!\u00059y%M[3di6\u000bg.\u001b4fgR\u001c2![C*!\u0019\t\u0019.!\t\u0005BQ\u0011Qq\u000b\t\u0004\u0003'LG\u0003BC.\u000b;\u0002b!!#\u0003\u0004\u0011\u0005\u0003b\u0002B\u0005W\u0002\u0007!1\u0002\u000b\u0005\u0007c+\t\u0007C\u0004\u0006\u00101\u0004\r!b\u00191\t\u0015\u0015T\u0011\u000e\t\u0007\u000bk)I$b\u001a\u0011\t\u0005=V\u0011\u000e\u0003\r\u000bW*\t'!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0002\u0005?\u0012\u0012\u0004\u0007K\u0004j\u0005\u001b\u0012\u0019F!\u0016\u0002\r=\u0013'.Z2u+\t)\u0019\b\u0005\u0004\u0002\u0002\u0012]D\u0011I\u0001\b\u001f\nTWm\u0019;!\u0003\u0019\te.\u001f*fMV\u0011Q1\u0010\t\u0007\u0003\u0003#9(a\"\u0002\u000f\u0005s\u0017PU3gA\t)\u0012I\\=WC2\u0004\u0006.\u00198u_6l\u0015M\\5gKN$8c\u0001:\u0006\u0004B1\u00111[A\u0011\u000b\u000b\u0003B!!#\u0006\b&!Q\u0011RA=\u0005\u0019\te.\u001f,bYR\u0011QQ\u0012\t\u0004\u0003'\u0014H\u0003BCI\u000b'\u0003b!!#\u0003\u0004\u0015\u0015\u0005b\u0002B\u0005i\u0002\u0007!1\u0002\u000b\u0005\u0007c+9\nC\u0004\u0006\u0010U\u0004\r!\"'1\t\u0015mUq\u0014\t\u0007\u000bk)I$\"(\u0011\t\u0005=Vq\u0014\u0003\r\u000bC+9*!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0002\u0005?\u0012\u0012\u0014\u0007K\u0004s\u0005\u001b\u0012\u0019F!\u0016\u0002\r\u0005s\u0017PV1m+\t)I\u000b\u0005\u0004\u0002\u0002\u0012]TQQ\u0001\b\u0003:Lh+\u00197!\u00051qU\u000f\u001c7NC:Lg-Z:u'\rIX\u0011\u0017\t\u0007\u0003'\f\t#b-\u0011\t\u0005%UQW\u0005\u0005\u000bo\u000bIH\u0001\u0003Ok2dGCAC^!\r\t\u0019.\u001f\u000b\u0005\u000b\u007f+\t\r\u0005\u0004\u0002\n\n\rQ1\u0017\u0005\b\u0005\u0013Y\b\u0019\u0001B\u0006)\u0011\u0019\t,\"2\t\u000f\u0015=A\u00101\u0001\u0006HB\"Q\u0011ZCg!\u0019))$\"\u000f\u0006LB!\u0011qVCg\t1)y-\"2\u0002\u0002\u0003\u0005)\u0011AA[\u0005\u0011yFE\r\u001a)\u000fe\u0014iEa\u0015\u0003V\u0005!a*\u001e7m+\t)9\u000e\u0005\u0004\u0002\u0002\u0012]T1W\u0001\u0006\u001dVdG\u000e\t\u0002\u0010\u001d>$\b.\u001b8h\u001b\u0006t\u0017NZ3tiN!\u0011\u0011ACp!\u0019\t\u0019.!\t\u00028R\u0011Q1\u001d\t\u0005\u0003'\f\t\u0001\u0006\u0003\u0006h\u0016%\bCBAE\u0005\u0007\t9\f\u0003\u0005\u0003\n\u0005\u0015\u0001\u0019\u0001B\u0006)\u0011\u0019\t,\"<\t\u0011\u0015=\u0011q\u0001a\u0001\u000b_\u0004D!\"=\u0006vB1QQGC\u001d\u000bg\u0004B!a,\u0006v\u0012aQq_Cw\u0003\u0003\u0005\tQ!\u0001\u00026\n!q\f\n\u001a4Q!\t\tA!\u0014\u0003T\tU\u0013a\u0002(pi\"LgnZ\u000b\u0003\u000b\u007f\u0004b!!!\u0005x\u0005]\u0016\u0001\u0003(pi\"Lgn\u001a\u0011\u0003+MKgn\u001a7fi>tG+\u001f9f\u001b\u0006t\u0017NZ3tiV!aq\u0001D\u0007'\u0019\ty!a\"\u0007\nA1\u0011\u0011\u0011C<\r\u0017\u0001B!a,\u0007\u000e\u0011AAQDA\b\u0005\u00041y!\u0005\u0003\u00028\u0006\u001dE\u0003\u0002D\n\r+\u0001b!a5\u0002\u0010\u0019-\u0001\u0002\u0003B*\u0003'\u0001\r!a\"\u0016\u0005\u0019e\u0001\u0007\u0002D\u000e\r?\u0001b!a7\u0002j\u001au\u0001\u0003BAX\r?!AB\"\t\u0002\u0016\u0005\u0005\t\u0011!B\u0001\r\u001f\u0011Aa\u0018\u00133iU\u0011A\u0011\u001c\u0015\t\u0003\u001f\u0011iEa\u0015\u0003V\u0005Q1/\u001b8hY\u0016$\u0016\u0010]3\u0016\t\u0019-b\u0011\u0007\u000b\u0005\r[1\u0019\u0004\u0005\u0004\u0002\u0002\u0012]dq\u0006\t\u0005\u0003_3\t\u0004\u0002\u0005\u0005\u001e\u0005e!\u0019\u0001D\b\u0011!\u0011\u0019&!\u0007A\u0002\u0005\u001d\u0015!C2mCN\u001cH+\u001f9f+\u00111IDb\u0010\u0015\t\u0019mb\u0011\t\t\u0007\u0003\u0003#9H\"\u0010\u0011\t\u0005=fq\b\u0003\t\t;\tYB1\u0001\u00026\"Aa1IA\u000e\u0001\u00041)%A\u0003dY\u0006T(\u0010\r\u0003\u0007H\u0019-\u0003C\u0002CJ\u0003S4I\u0005\u0005\u0003\u00020\u001a-C\u0001\u0004D'\r\u0003\n\t\u0011!A\u0003\u0002\u0005U&\u0001B0%eU*BA\"\u0015\u0007XQAa1\u000bD-\r;2Y\u0007\u0005\u0004\u0002\u0002\u0012]dQ\u000b\t\u0005\u0003_39\u0006\u0002\u0005\u0005\u001e\u0005u!\u0019AA[\u0011!1\u0019%!\bA\u0002\u0019m\u0003C\u0002CJ\u0003S4)\u0006\u0003\u0005\u0007`\u0005u\u0001\u0019\u0001D1\u0003\u0011\t'oZ\u00191\t\u0019\rdq\r\t\u0007\u0003\u0003#9H\"\u001a\u0011\t\u0005=fq\r\u0003\r\rS2i&!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0002\u0005?\u0012\u0012d\u0007\u0003\u0005\u0007n\u0005u\u0001\u0019\u0001D8\u0003\u0011\t'oZ:\u0011\r\u0005%e\u0011\u000fD;\u0013\u00111\u0019(!\u001f\u0003\u0015q\u0012X\r]3bi\u0016$g\b\r\u0003\u0007x\u0019m\u0004CBAA\to2I\b\u0005\u0003\u00020\u001amD\u0001\u0004D?\r\u007f\n\t\u0011!A\u0003\u0002\u0005U&\u0001B0%e]B\u0001B\"\u001c\u0002\u001e\u0001\u0007aqN\u000b\u0005\r\u00073I\t\u0006\u0005\u0007\u0006\u001a-eq\u0013DR!\u0019\t\t\tb\u001e\u0007\bB!\u0011q\u0016DE\t!!i\"a\bC\u0002\u0005U\u0006\u0002\u0003C@\u0003?\u0001\rA\"$1\t\u0019=e1\u0013\t\u0007\u0003\u0003#9H\"%\u0011\t\u0005=f1\u0013\u0003\r\r+3Y)!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0002\u0005?\u0012\u0012\u0004\b\u0003\u0005\u0007D\u0005}\u0001\u0019\u0001DMa\u00111YJb(\u0011\r\u0011M\u0015\u0011\u001eDO!\u0011\tyKb(\u0005\u0019\u0019\u0005fqSA\u0001\u0002\u0003\u0015\t!!.\u0003\t}##'\u000f\u0005\t\r[\ny\u00021\u0001\u0007&B1\u0011\u0011\u0012D9\rO\u0003DA\"+\u0007.B1\u0011\u0011\u0011C<\rW\u0003B!a,\u0007.\u0012aaq\u0016DR\u0003\u0003\u0005\tQ!\u0001\u00026\n!q\fJ\u001a1\u0003%\t'O]1z)f\u0004X-\u0006\u0003\u00076\u001auF\u0003\u0002D\\\r\u007f\u0003b!!!\u0005x\u0019e\u0006CBAE\u0005\u00071Y\f\u0005\u0003\u00020\u001auF\u0001\u0003C\u000f\u0003\u0003\u0012\r!!.\t\u0011\u0019\u0005\u0017\u0011\ta\u0001\r\u0007\f1!\u0019:ha\u00111)M\"3\u0011\r\u0005\u0005Eq\u000fDd!\u0011\tyK\"3\u0005\u0019\u0019-gqXA\u0001\u0002\u0003\u0015\t!!.\u0003\t}#3'\u000e\u0002\u0015\u0003\n\u001cHO]1diRK\b/Z'b]&4Wm\u001d;\u0016\t\u0019Egq[\n\u0007\u0003\u0007\n9Ib5\u0011\r\u0005\u0005Eq\u000fDk!\u0011\tyKb6\u0005\u0011\u0011u\u00111\tb\u0001\u0003k\u0003DAb7\u0007`B1\u0011\u0011\u0011C<\r;\u0004B!a,\u0007`\u0012aa\u0011]A#\u0003\u0003\u0005\tQ!\u0001\u00026\n!q\fJ\u001a7\u0003\u0011q\u0017-\\3\u0002\u0015U\u0004\b/\u001a:C_VtG\r\r\u0003\u0007j\u001a5\bC\u0002CJ\u0003S4Y\u000f\u0005\u0003\u00020\u001a5H\u0001\u0004Dx\u0003\u0013\n\t\u0011!A\u0003\u0002\u0005U&\u0001B0%g]\u0002bAb=\u0007v\u001aeXB\u0001B\u0013\u0013\u001119P!\n\u0003\u0007M+\u0017\u000f\r\u0003\u0007|\u001a}\bCBAA\to2i\u0010\u0005\u0003\u00020\u001a}H\u0001DD\u0001\u0003\u0017\n\t\u0011!A\u0003\u0002\u0005U&\u0001B0%ga\"\"b\"\u0002\b\b\u001dEq1CD\u000f!\u0019\t\u0019.a\u0011\u0007V\"AAqPA'\u0001\u00049I\u0001\r\u0003\b\f\u001d=\u0001CBAA\to:i\u0001\u0005\u0003\u00020\u001e=A\u0001\u0004Dq\u000f\u000f\t\t\u0011!A\u0003\u0002\u0005U\u0006\u0002\u0003Dr\u0003\u001b\u0002\r\u0001b=\t\u0011\u0019\u0015\u0018Q\na\u0001\u000f+\u0001Dab\u0006\b\u001cA1A1SAu\u000f3\u0001B!a,\b\u001c\u0011aaq^D\n\u0003\u0003\u0005\tQ!\u0001\u00026\"AaQNA'\u0001\u00049y\u0002\u0005\u0004\u0007t\u001aUx\u0011\u0005\u0019\u0005\u000fG99\u0003\u0005\u0004\u0002\u0002\u0012]tQ\u0005\t\u0005\u0003_;9\u0003\u0002\u0007\b\u0002\u001du\u0011\u0011!A\u0001\u0006\u0003\t),\u0006\u0002\b,A\"qQFD\u0019!\u0019\t\t\u0010b\u0010\b0A!\u0011qVD\u0019\t11y/a\u0014\u0002\u0002\u0003\u0005)\u0011AA[+\t9)\u0004\u0005\u0004\b8\u001dub\u0011`\u0007\u0003\u000fsQAab\u000f\u0003&\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003C;I\u0004\u000b\u0005\u0002D\t5#1\u000bB+\u00031\t'm\u001d;sC\u000e$H+\u001f9f+\u00119)eb\u0013\u0015\u0015\u001d\u001dsQJD-\u000f7:9\u0007\u0005\u0004\u0002\u0002\u0012]t\u0011\n\t\u0005\u0003_;Y\u0005\u0002\u0005\u0005\u001e\u0005]#\u0019AA[\u0011!!y(a\u0016A\u0002\u001d=\u0003\u0007BD)\u000f+\u0002b!!!\u0005x\u001dM\u0003\u0003BAX\u000f+\"Abb\u0016\bN\u0005\u0005\t\u0011!B\u0001\u0003k\u0013Aa\u0018\u00134s!Aa1]A,\u0001\u0004!\u0019\u0010\u0003\u0005\u0007f\u0006]\u0003\u0019AD/a\u00119yfb\u0019\u0011\r\u0011M\u0015\u0011^D1!\u0011\tykb\u0019\u0005\u0019\u001d\u0015t1LA\u0001\u0002\u0003\u0015\t!!.\u0003\t}#C\u0007\r\u0005\t\r[\n9\u00061\u0001\bjA1\u0011\u0011\u0012D9\u000fW\u0002Da\"\u001c\brA1\u0011\u0011\u0011C<\u000f_\u0002B!a,\br\u0011aq1OD4\u0003\u0003\u0005\tQ!\u0001\u00026\n!q\f\n\u001b2\u0005A9\u0016\u000e\u001c3dCJ$W*\u00198jM\u0016\u001cH/\u0006\u0003\bz\u001d}4CBA-\u0003\u000f;Y\b\u0005\u0004\u0002\u0002\u0012]tQ\u0010\t\u0005\u0003_;y\b\u0002\u0005\u0005\u001e\u0005e#\u0019AA[\u0003)awn^3s\u0005>,h\u000e\u001a\u0019\u0005\u000f\u000b;I\t\u0005\u0004\u0002\u0002\u0012]tq\u0011\t\u0005\u0003_;I\t\u0002\u0007\b\f\u0006m\u0013\u0011!A\u0001\u0006\u0003\t)L\u0001\u0003`IQ\u0012\u0004\u0007BDH\u000f'\u0003b!!!\u0005x\u001dE\u0005\u0003BAX\u000f'#Ab\"&\u0002^\u0005\u0005\t\u0011!B\u0001\u0003k\u0013Aa\u0018\u00135gQ1q\u0011TDN\u000fK\u0003b!a5\u0002Z\u001du\u0004\u0002CDA\u0003?\u0002\ra\"(1\t\u001d}u1\u0015\t\u0007\u0003\u0003#9h\")\u0011\t\u0005=v1\u0015\u0003\r\u000f\u0017;Y*!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0005\t\rK\fy\u00061\u0001\b(B\"q\u0011VDW!\u0019\t\t\tb\u001e\b,B!\u0011qVDW\t19)j\"*\u0002\u0002\u0003\u0005)\u0011AA[+\t9\t\f\r\u0003\b4\u001e]\u0006CBAy\t\u007f9)\f\u0005\u0003\u00020\u001e]F\u0001DD]\u0003C\n\t\u0011!A\u0003\u0002\u0005U&aA0%c!B\u0011\u0011\fB'\u0005'\u0012)&\u0001\u0007xS2$7-\u0019:e)f\u0004X-\u0006\u0003\bB\u001e\u001dGCBDb\u000f\u0013<)\u000e\u0005\u0004\u0002\u0002\u0012]tQ\u0019\t\u0005\u0003_;9\r\u0002\u0005\u0005\u001e\u0005\u0015$\u0019AA[\u0011!9\t)!\u001aA\u0002\u001d-\u0007\u0007BDg\u000f#\u0004b!!!\u0005x\u001d=\u0007\u0003BAX\u000f#$Abb5\bJ\u0006\u0005\t\u0011!B\u0001\u0003k\u0013Aa\u0018\u00135i!AaQ]A3\u0001\u000499\u000e\r\u0003\bZ\u001eu\u0007CBAA\to:Y\u000e\u0005\u0003\u00020\u001euG\u0001DDp\u000f+\f\t\u0011!A\u0003\u0002\u0005U&\u0001B0%iU\u0012\u0001$\u00138uKJ\u001cXm\u0019;j_:$\u0016\u0010]3NC:Lg-Z:u+\u00119)ob;\u0014\r\u0005\u001d\u0014qQDt!\u0019\t\t\tb\u001e\bjB!\u0011qVDv\t!!i\"a\u001aC\u0002\u0005U\u0016a\u00029be\u0016tGo\u001d\t\u0007\u0003\u0013\u0013\u0019a\"=1\t\u001dMxq\u001f\t\u0007\u0003\u0003#9h\">\u0011\t\u0005=vq\u001f\u0003\r\u000fs\fI'!A\u0001\u0002\u000b\u0005\u0011Q\u0017\u0002\u0005?\u0012\"d\u0007\u0006\u0003\b~\u001e}\bCBAj\u0003O:I\u000f\u0003\u0005\bn\u0006-\u0004\u0019\u0001E\u0001!\u0019\tIIa\u0001\t\u0004A\"\u0001R\u0001E\u0005!\u0019\t\t\tb\u001e\t\bA!\u0011q\u0016E\u0005\t19Ipb@\u0002\u0002\u0003\u0005)\u0011AA[+\tAi\u0001\r\u0003\t\u0010!M\u0001CBAy\t\u007fA\t\u0002\u0005\u0003\u00020\"MA\u0001DD]\u0003[\n\t\u0011!A\u0003\u0002\u0005UFC\u0001CzQ!\t9G!\u0014\u0003T\tU\u0013\u0001E5oi\u0016\u00148/Z2uS>tG+\u001f9f+\u0011Ai\u0002c\t\u0015\t!}\u0001R\u0005\t\u0007\u0003\u0003#9\b#\t\u0011\t\u0005=\u00062\u0005\u0003\t\t;\t\tH1\u0001\u00026\"AqQ^A9\u0001\u0004A9\u0003\u0005\u0004\u0002\n\u001aE\u0004\u0012\u0006\u0019\u0005\u0011WAy\u0003\u0005\u0004\u0002\u0002\u0012]\u0004R\u0006\t\u0005\u0003_Cy\u0003\u0002\u0007\t2!\u0015\u0012\u0011!A\u0001\u0006\u0003\t)L\u0001\u0003`IQ:\u0004"
)
public final class ManifestFactory {
   public static Manifest intersectionType(final Seq parents) {
      return ManifestFactory$.MODULE$.intersectionType(parents);
   }

   public static Manifest wildcardType(final Manifest lowerBound, final Manifest upperBound) {
      return ManifestFactory$.MODULE$.wildcardType(lowerBound, upperBound);
   }

   public static Manifest abstractType(final Manifest prefix, final String name, final Class upperBound, final Seq args) {
      return ManifestFactory$.MODULE$.abstractType(prefix, name, upperBound, args);
   }

   public static Manifest arrayType(final Manifest arg) {
      return ManifestFactory$.MODULE$.arrayType(arg);
   }

   public static Manifest classType(final Manifest prefix, final Class clazz, final Seq args) {
      return ManifestFactory$.MODULE$.classType(prefix, clazz, args);
   }

   public static Manifest classType(final Class clazz, final Manifest arg1, final Seq args) {
      return ManifestFactory$.MODULE$.classType(clazz, arg1, args);
   }

   public static Manifest classType(final Class clazz) {
      return ManifestFactory$.MODULE$.classType(clazz);
   }

   public static Manifest singleType(final Object value) {
      return ManifestFactory$.MODULE$.singleType(value);
   }

   public static Manifest Nothing() {
      return ManifestFactory$.MODULE$.Nothing();
   }

   public static Manifest Null() {
      return ManifestFactory$.MODULE$.Null();
   }

   public static Manifest AnyVal() {
      return ManifestFactory$.MODULE$.AnyVal();
   }

   public static Manifest AnyRef() {
      return ManifestFactory$.MODULE$.AnyRef();
   }

   public static Manifest Object() {
      return ManifestFactory$.MODULE$.Object();
   }

   public static Manifest Any() {
      return ManifestFactory$.MODULE$.Any();
   }

   public static UnitManifest Unit() {
      return ManifestFactory$.MODULE$.Unit();
   }

   public static BooleanManifest Boolean() {
      return ManifestFactory$.MODULE$.Boolean();
   }

   public static DoubleManifest Double() {
      return ManifestFactory$.MODULE$.Double();
   }

   public static FloatManifest Float() {
      return ManifestFactory$.MODULE$.Float();
   }

   public static LongManifest Long() {
      return ManifestFactory$.MODULE$.Long();
   }

   public static IntManifest Int() {
      return ManifestFactory$.MODULE$.Int();
   }

   public static CharManifest Char() {
      return ManifestFactory$.MODULE$.Char();
   }

   public static ShortManifest Short() {
      return ManifestFactory$.MODULE$.Short();
   }

   public static ByteManifest Byte() {
      return ManifestFactory$.MODULE$.Byte();
   }

   public static List valueManifests() {
      return ManifestFactory$.MODULE$.valueManifests();
   }

   public static final class ByteManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Byte.TYPE;
      }

      public byte[] newArray(final int len) {
         return new byte[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofByte(new byte[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofByte();
      }

      public Option unapply(final Object x) {
         if (x instanceof Byte) {
            byte var2 = BoxesRunTime.unboxToByte(x);
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Byte();
      }

      public ByteManifest() {
         super("Byte");
      }
   }

   public static final class ShortManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Short.TYPE;
      }

      public short[] newArray(final int len) {
         return new short[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofShort(new short[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofShort();
      }

      public Option unapply(final Object x) {
         if (x instanceof Short) {
            short var2 = BoxesRunTime.unboxToShort(x);
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Short();
      }

      public ShortManifest() {
         super("Short");
      }
   }

   public static final class CharManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Character.TYPE;
      }

      public char[] newArray(final int len) {
         return new char[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofChar(new char[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofChar();
      }

      public Option unapply(final Object x) {
         if (x instanceof Character) {
            char var2 = BoxesRunTime.unboxToChar(x);
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Char();
      }

      public CharManifest() {
         super("Char");
      }
   }

   public static final class IntManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Integer.TYPE;
      }

      public int[] newArray(final int len) {
         return new int[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofInt(new int[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofInt();
      }

      public Option unapply(final Object x) {
         if (x instanceof Integer) {
            int var2 = BoxesRunTime.unboxToInt(x);
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Int();
      }

      public IntManifest() {
         super("Int");
      }
   }

   public static final class LongManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Long.TYPE;
      }

      public long[] newArray(final int len) {
         return new long[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofLong(new long[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofLong();
      }

      public Option unapply(final Object x) {
         if (x instanceof Long) {
            long var2 = BoxesRunTime.unboxToLong(x);
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Long();
      }

      public LongManifest() {
         super("Long");
      }
   }

   public static final class FloatManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Float.TYPE;
      }

      public float[] newArray(final int len) {
         return new float[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofFloat(new float[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofFloat();
      }

      public Option unapply(final Object x) {
         if (x instanceof Float) {
            float var2 = BoxesRunTime.unboxToFloat(x);
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Float();
      }

      public FloatManifest() {
         super("Float");
      }
   }

   public static final class DoubleManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Double.TYPE;
      }

      public double[] newArray(final int len) {
         return new double[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofDouble(new double[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofDouble();
      }

      public Option unapply(final Object x) {
         if (x instanceof Double) {
            double var2 = BoxesRunTime.unboxToDouble(x);
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Double();
      }

      public DoubleManifest() {
         super("Double");
      }
   }

   public static final class BooleanManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Boolean.TYPE;
      }

      public boolean[] newArray(final int len) {
         return new boolean[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofBoolean(new boolean[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofBoolean();
      }

      public Option unapply(final Object x) {
         if (x instanceof Boolean) {
            boolean var2 = BoxesRunTime.unboxToBoolean(x);
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Boolean();
      }

      public BooleanManifest() {
         super("Boolean");
      }
   }

   public static final class UnitManifest extends AnyValManifest {
      private static final long serialVersionUID = 1L;

      public Class runtimeClass() {
         return Void.TYPE;
      }

      public BoxedUnit[] newArray(final int len) {
         return new BoxedUnit[len];
      }

      public ArraySeq newWrappedArray(final int len) {
         return new ArraySeq.ofUnit(new BoxedUnit[len]);
      }

      public ArrayBuilder newArrayBuilder() {
         return new ArrayBuilder.ofUnit();
      }

      public Class arrayClass(final Class tp) {
         return tp == Void.TYPE ? BoxedUnit[].class : ClassManifestDeprecatedApis.arrayClass$(this, tp);
      }

      public Option unapply(final Object x) {
         if (x instanceof BoxedUnit) {
            BoxedUnit var2 = BoxedUnit.UNIT;
            return new Some(var2);
         } else {
            return None$.MODULE$;
         }
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Unit();
      }

      public UnitManifest() {
         super("Unit");
      }
   }

   private static final class AnyManifest extends PhantomManifest {
      private static final long serialVersionUID = 1L;

      public Object[] newArray(final int len) {
         return new Object[len];
      }

      public boolean $less$colon$less(final ClassTag that) {
         return that == this;
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Any();
      }

      public AnyManifest() {
         super(ManifestFactory$.scala$reflect$ManifestFactory$$ObjectTYPE, "Any");
      }
   }

   private static final class ObjectManifest extends PhantomManifest {
      private static final long serialVersionUID = 1L;

      public Object[] newArray(final int len) {
         return new Object[len];
      }

      public boolean $less$colon$less(final ClassTag that) {
         return that == this || that == ManifestFactory$.MODULE$.Any();
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Object();
      }

      public ObjectManifest() {
         super(ManifestFactory$.scala$reflect$ManifestFactory$$ObjectTYPE, "Object");
      }
   }

   private static final class AnyValPhantomManifest extends PhantomManifest {
      private static final long serialVersionUID = 1L;

      public Object[] newArray(final int len) {
         return new Object[len];
      }

      public boolean $less$colon$less(final ClassTag that) {
         return that == this || that == ManifestFactory$.MODULE$.Any();
      }

      private Object readResolve() {
         return Manifest$.MODULE$.AnyVal();
      }

      public AnyValPhantomManifest() {
         super(ManifestFactory$.scala$reflect$ManifestFactory$$ObjectTYPE, "AnyVal");
      }
   }

   private static final class NullManifest extends PhantomManifest {
      private static final long serialVersionUID = 1L;

      public Object[] newArray(final int len) {
         return new Object[len];
      }

      public boolean $less$colon$less(final ClassTag that) {
         return that != null && that != ManifestFactory$.MODULE$.Nothing() && !that.$less$colon$less(ManifestFactory$.MODULE$.AnyVal());
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Null();
      }

      public NullManifest() {
         super(ManifestFactory$.scala$reflect$ManifestFactory$$NullTYPE, "Null");
      }
   }

   private static final class NothingManifest extends PhantomManifest {
      private static final long serialVersionUID = 1L;

      public Object[] newArray(final int len) {
         return new Object[len];
      }

      public boolean $less$colon$less(final ClassTag that) {
         return that != null;
      }

      private Object readResolve() {
         return Manifest$.MODULE$.Nothing();
      }

      public NothingManifest() {
         super(ManifestFactory$.scala$reflect$ManifestFactory$$NothingTYPE, "Nothing");
      }
   }

   private static final class SingletonTypeManifest implements Manifest {
      private static final long serialVersionUID = 1L;
      private Class runtimeClass;
      private String toString;
      private final Object value;
      private volatile byte bitmap$0;

      public List typeArguments() {
         return Manifest.typeArguments$(this);
      }

      public Manifest arrayManifest() {
         return Manifest.arrayManifest$(this);
      }

      public boolean canEqual(final Object that) {
         return Manifest.canEqual$(this, that);
      }

      public boolean equals(final Object that) {
         return Manifest.equals$(this, that);
      }

      public int hashCode() {
         return Manifest.hashCode$(this);
      }

      public ClassTag wrap() {
         return ClassTag.wrap$(this);
      }

      public Object newArray(final int len) {
         return ClassTag.newArray$(this, len);
      }

      public Option unapply(final Object x) {
         return ClassTag.unapply$(this, x);
      }

      /** @deprecated */
      public Class erasure() {
         return ClassManifestDeprecatedApis.erasure$(this);
      }

      /** @deprecated */
      public boolean $less$colon$less(final ClassTag that) {
         return ClassManifestDeprecatedApis.$less$colon$less$(this, that);
      }

      /** @deprecated */
      public boolean $greater$colon$greater(final ClassTag that) {
         return ClassManifestDeprecatedApis.$greater$colon$greater$(this, that);
      }

      public Class arrayClass(final Class tp) {
         return ClassManifestDeprecatedApis.arrayClass$(this, tp);
      }

      /** @deprecated */
      public Object[] newArray2(final int len) {
         return ClassManifestDeprecatedApis.newArray2$(this, len);
      }

      /** @deprecated */
      public Object[][] newArray3(final int len) {
         return ClassManifestDeprecatedApis.newArray3$(this, len);
      }

      /** @deprecated */
      public Object[][][] newArray4(final int len) {
         return ClassManifestDeprecatedApis.newArray4$(this, len);
      }

      /** @deprecated */
      public Object[][][][] newArray5(final int len) {
         return ClassManifestDeprecatedApis.newArray5$(this, len);
      }

      /** @deprecated */
      public ArraySeq newWrappedArray(final int len) {
         return ClassManifestDeprecatedApis.newWrappedArray$(this, len);
      }

      /** @deprecated */
      public ArrayBuilder newArrayBuilder() {
         return ClassManifestDeprecatedApis.newArrayBuilder$(this);
      }

      public String argString() {
         return ClassManifestDeprecatedApis.argString$(this);
      }

      private Class runtimeClass$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               this.runtimeClass = this.value.getClass();
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.runtimeClass;
      }

      public Class runtimeClass() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.runtimeClass$lzycompute() : this.runtimeClass;
      }

      private String toString$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.toString = (new StringBuilder(5)).append(this.value.toString()).append(".type").toString();
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.toString;
      }

      public String toString() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.toString$lzycompute() : this.toString;
      }

      public SingletonTypeManifest(final Object value) {
         this.value = value;
      }
   }

   private abstract static class PhantomManifest extends ClassTypeManifest {
      private static final long serialVersionUID = 1L;
      private final String toString;
      private final transient int hashCode;

      public String toString() {
         return this.toString;
      }

      public boolean equals(final Object that) {
         return this == that;
      }

      public int hashCode() {
         return this.hashCode;
      }

      public PhantomManifest(final Class _runtimeClass, final String toString) {
         super(None$.MODULE$, _runtimeClass, Nil$.MODULE$);
         this.toString = toString;
         this.hashCode = System.identityHashCode(this);
      }
   }

   private static class ClassTypeManifest implements Manifest {
      private static final long serialVersionUID = 1L;
      private final Option prefix;
      private final Class runtimeClass;
      private final List typeArguments;

      public Manifest arrayManifest() {
         return Manifest.arrayManifest$(this);
      }

      public boolean canEqual(final Object that) {
         return Manifest.canEqual$(this, that);
      }

      public boolean equals(final Object that) {
         return Manifest.equals$(this, that);
      }

      public int hashCode() {
         return Manifest.hashCode$(this);
      }

      public ClassTag wrap() {
         return ClassTag.wrap$(this);
      }

      public Object newArray(final int len) {
         return ClassTag.newArray$(this, len);
      }

      public Option unapply(final Object x) {
         return ClassTag.unapply$(this, x);
      }

      /** @deprecated */
      public Class erasure() {
         return ClassManifestDeprecatedApis.erasure$(this);
      }

      /** @deprecated */
      public boolean $less$colon$less(final ClassTag that) {
         return ClassManifestDeprecatedApis.$less$colon$less$(this, that);
      }

      /** @deprecated */
      public boolean $greater$colon$greater(final ClassTag that) {
         return ClassManifestDeprecatedApis.$greater$colon$greater$(this, that);
      }

      public Class arrayClass(final Class tp) {
         return ClassManifestDeprecatedApis.arrayClass$(this, tp);
      }

      /** @deprecated */
      public Object[] newArray2(final int len) {
         return ClassManifestDeprecatedApis.newArray2$(this, len);
      }

      /** @deprecated */
      public Object[][] newArray3(final int len) {
         return ClassManifestDeprecatedApis.newArray3$(this, len);
      }

      /** @deprecated */
      public Object[][][] newArray4(final int len) {
         return ClassManifestDeprecatedApis.newArray4$(this, len);
      }

      /** @deprecated */
      public Object[][][][] newArray5(final int len) {
         return ClassManifestDeprecatedApis.newArray5$(this, len);
      }

      /** @deprecated */
      public ArraySeq newWrappedArray(final int len) {
         return ClassManifestDeprecatedApis.newWrappedArray$(this, len);
      }

      /** @deprecated */
      public ArrayBuilder newArrayBuilder() {
         return ClassManifestDeprecatedApis.newArrayBuilder$(this);
      }

      public String argString() {
         return ClassManifestDeprecatedApis.argString$(this);
      }

      public Class runtimeClass() {
         return this.runtimeClass;
      }

      public List typeArguments() {
         return this.typeArguments;
      }

      public String toString() {
         return (new StringBuilder(0)).append(this.prefix.isEmpty() ? "" : (new StringBuilder(1)).append(((ClassTag)this.prefix.get()).toString()).append("#").toString()).append(this.runtimeClass().isArray() ? "Array" : this.runtimeClass().getName()).append(this.argString()).toString();
      }

      public ClassTypeManifest(final Option prefix, final Class runtimeClass, final List typeArguments) {
         this.prefix = prefix;
         this.runtimeClass = runtimeClass;
         this.typeArguments = typeArguments;
      }
   }

   private static class AbstractTypeManifest implements Manifest {
      private static final long serialVersionUID = 1L;
      private final Manifest prefix;
      private final String name;
      private final Class upperBound;
      private final List typeArguments;

      public Manifest arrayManifest() {
         return Manifest.arrayManifest$(this);
      }

      public boolean canEqual(final Object that) {
         return Manifest.canEqual$(this, that);
      }

      public boolean equals(final Object that) {
         return Manifest.equals$(this, that);
      }

      public int hashCode() {
         return Manifest.hashCode$(this);
      }

      public ClassTag wrap() {
         return ClassTag.wrap$(this);
      }

      public Object newArray(final int len) {
         return ClassTag.newArray$(this, len);
      }

      public Option unapply(final Object x) {
         return ClassTag.unapply$(this, x);
      }

      /** @deprecated */
      public Class erasure() {
         return ClassManifestDeprecatedApis.erasure$(this);
      }

      /** @deprecated */
      public boolean $less$colon$less(final ClassTag that) {
         return ClassManifestDeprecatedApis.$less$colon$less$(this, that);
      }

      /** @deprecated */
      public boolean $greater$colon$greater(final ClassTag that) {
         return ClassManifestDeprecatedApis.$greater$colon$greater$(this, that);
      }

      public Class arrayClass(final Class tp) {
         return ClassManifestDeprecatedApis.arrayClass$(this, tp);
      }

      /** @deprecated */
      public Object[] newArray2(final int len) {
         return ClassManifestDeprecatedApis.newArray2$(this, len);
      }

      /** @deprecated */
      public Object[][] newArray3(final int len) {
         return ClassManifestDeprecatedApis.newArray3$(this, len);
      }

      /** @deprecated */
      public Object[][][] newArray4(final int len) {
         return ClassManifestDeprecatedApis.newArray4$(this, len);
      }

      /** @deprecated */
      public Object[][][][] newArray5(final int len) {
         return ClassManifestDeprecatedApis.newArray5$(this, len);
      }

      /** @deprecated */
      public ArraySeq newWrappedArray(final int len) {
         return ClassManifestDeprecatedApis.newWrappedArray$(this, len);
      }

      /** @deprecated */
      public ArrayBuilder newArrayBuilder() {
         return ClassManifestDeprecatedApis.newArrayBuilder$(this);
      }

      public String argString() {
         return ClassManifestDeprecatedApis.argString$(this);
      }

      public Class runtimeClass() {
         return this.upperBound;
      }

      public List typeArguments() {
         return this.typeArguments;
      }

      public String toString() {
         return (new StringBuilder(1)).append(this.prefix.toString()).append("#").append(this.name).append(this.argString()).toString();
      }

      public AbstractTypeManifest(final Manifest prefix, final String name, final Class upperBound, final scala.collection.Seq args) {
         this.prefix = prefix;
         this.name = name;
         this.upperBound = upperBound;
         this.typeArguments = args.toList();
      }
   }

   private static class WildcardManifest implements Manifest {
      private static final long serialVersionUID = 1L;
      private final Manifest lowerBound;
      private final Manifest upperBound;

      public List typeArguments() {
         return Manifest.typeArguments$(this);
      }

      public Manifest arrayManifest() {
         return Manifest.arrayManifest$(this);
      }

      public boolean canEqual(final Object that) {
         return Manifest.canEqual$(this, that);
      }

      public boolean equals(final Object that) {
         return Manifest.equals$(this, that);
      }

      public int hashCode() {
         return Manifest.hashCode$(this);
      }

      public ClassTag wrap() {
         return ClassTag.wrap$(this);
      }

      public Object newArray(final int len) {
         return ClassTag.newArray$(this, len);
      }

      public Option unapply(final Object x) {
         return ClassTag.unapply$(this, x);
      }

      /** @deprecated */
      public Class erasure() {
         return ClassManifestDeprecatedApis.erasure$(this);
      }

      /** @deprecated */
      public boolean $less$colon$less(final ClassTag that) {
         return ClassManifestDeprecatedApis.$less$colon$less$(this, that);
      }

      /** @deprecated */
      public boolean $greater$colon$greater(final ClassTag that) {
         return ClassManifestDeprecatedApis.$greater$colon$greater$(this, that);
      }

      public Class arrayClass(final Class tp) {
         return ClassManifestDeprecatedApis.arrayClass$(this, tp);
      }

      /** @deprecated */
      public Object[] newArray2(final int len) {
         return ClassManifestDeprecatedApis.newArray2$(this, len);
      }

      /** @deprecated */
      public Object[][] newArray3(final int len) {
         return ClassManifestDeprecatedApis.newArray3$(this, len);
      }

      /** @deprecated */
      public Object[][][] newArray4(final int len) {
         return ClassManifestDeprecatedApis.newArray4$(this, len);
      }

      /** @deprecated */
      public Object[][][][] newArray5(final int len) {
         return ClassManifestDeprecatedApis.newArray5$(this, len);
      }

      /** @deprecated */
      public ArraySeq newWrappedArray(final int len) {
         return ClassManifestDeprecatedApis.newWrappedArray$(this, len);
      }

      /** @deprecated */
      public ArrayBuilder newArrayBuilder() {
         return ClassManifestDeprecatedApis.newArrayBuilder$(this);
      }

      public String argString() {
         return ClassManifestDeprecatedApis.argString$(this);
      }

      public Class runtimeClass() {
         return this.upperBound.runtimeClass();
      }

      public String toString() {
         return (new StringBuilder(1)).append("_").append(this.lowerBound == ManifestFactory$.MODULE$.Nothing() ? "" : (new StringBuilder(4)).append(" >: ").append(this.lowerBound).toString()).append(this.upperBound == ManifestFactory$.MODULE$.Nothing() ? "" : (new StringBuilder(4)).append(" <: ").append(this.upperBound).toString()).toString();
      }

      public WildcardManifest(final Manifest lowerBound, final Manifest upperBound) {
         this.lowerBound = lowerBound;
         this.upperBound = upperBound;
      }
   }

   private static class IntersectionTypeManifest implements Manifest {
      private static final long serialVersionUID = 1L;
      private final Manifest[] parents;

      public List typeArguments() {
         return Manifest.typeArguments$(this);
      }

      public Manifest arrayManifest() {
         return Manifest.arrayManifest$(this);
      }

      public boolean canEqual(final Object that) {
         return Manifest.canEqual$(this, that);
      }

      public boolean equals(final Object that) {
         return Manifest.equals$(this, that);
      }

      public int hashCode() {
         return Manifest.hashCode$(this);
      }

      public ClassTag wrap() {
         return ClassTag.wrap$(this);
      }

      public Object newArray(final int len) {
         return ClassTag.newArray$(this, len);
      }

      public Option unapply(final Object x) {
         return ClassTag.unapply$(this, x);
      }

      /** @deprecated */
      public Class erasure() {
         return ClassManifestDeprecatedApis.erasure$(this);
      }

      /** @deprecated */
      public boolean $less$colon$less(final ClassTag that) {
         return ClassManifestDeprecatedApis.$less$colon$less$(this, that);
      }

      /** @deprecated */
      public boolean $greater$colon$greater(final ClassTag that) {
         return ClassManifestDeprecatedApis.$greater$colon$greater$(this, that);
      }

      public Class arrayClass(final Class tp) {
         return ClassManifestDeprecatedApis.arrayClass$(this, tp);
      }

      /** @deprecated */
      public Object[] newArray2(final int len) {
         return ClassManifestDeprecatedApis.newArray2$(this, len);
      }

      /** @deprecated */
      public Object[][] newArray3(final int len) {
         return ClassManifestDeprecatedApis.newArray3$(this, len);
      }

      /** @deprecated */
      public Object[][][] newArray4(final int len) {
         return ClassManifestDeprecatedApis.newArray4$(this, len);
      }

      /** @deprecated */
      public Object[][][][] newArray5(final int len) {
         return ClassManifestDeprecatedApis.newArray5$(this, len);
      }

      /** @deprecated */
      public ArraySeq newWrappedArray(final int len) {
         return ClassManifestDeprecatedApis.newWrappedArray$(this, len);
      }

      /** @deprecated */
      public ArrayBuilder newArrayBuilder() {
         return ClassManifestDeprecatedApis.newArrayBuilder$(this);
      }

      public String argString() {
         return ClassManifestDeprecatedApis.argString$(this);
      }

      public Class runtimeClass() {
         return this.parents[0].runtimeClass();
      }

      public String toString() {
         ArraySeq.ofRef var10000 = Predef$.MODULE$.wrapRefArray(this.parents);
         String mkString_sep = " with ";
         if (var10000 == null) {
            throw null;
         } else {
            AbstractIterable mkString_this = var10000;
            String mkString_end = "";
            String mkString_start = "";
            return IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
         }
      }

      public IntersectionTypeManifest(final Manifest[] parents) {
         this.parents = parents;
      }
   }
}
