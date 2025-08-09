package scala.reflect.internal;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.util.Position;
import scala.reflect.macros.Attachments;
import scala.reflect.macros.Universe;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019Ue!C8q!\u0003\r\ta\u001eDG\u0011\u001d\t\u0019\u0001\u0001C\u0001\u0003\u000b)a!!\u0004\u0001\u0001\u0005=\u0001\"C9\u0001\u0011\u000b\u0007I\u0011AA\u0010\u000b\u0019\t\u0019\u0003\u0001\u0001\u0002&!Q\u0011q\b\u0001\t\u0006\u0004%\t!!\u0011\u0007\u0013\u0005\u001d\u0003\u0001%A\u0002\u0002\u0005%\u0003bBA\u0002\r\u0011\u0005\u0011Q\u0001\u0005\u000b\u0003\u00172\u0001R1A\u0005\u0002\u00055\u0003bBA+\r\u0011\u0005\u0011q\u000b\u0005\b\u0003o2A\u0011AA=\u0011\u001d\tIJ\u0002C\u0001\u00037Cq!a*\u0007\t\u0003\tI\u000bC\u0004\u00022\u001a!\t!a-\t\u000f\u0005]g\u0001\"\u0001\u0002Z\"9\u0011Q\u001d\u0004\u0005\u0002\u0005\u001d\bbBAz\r\u0011\u0005\u0011Q\u001f\u0005\b\u0005\u00131A\u0011\u0001B\u0006\u0011\u001d\u0011YB\u0002C\u0001\u0005;AqAa\u000f\u0007\t\u0003\u0011i\u0004C\u0004\u0003l\u0019!\tA!\u001c\t\u000f\t\u0005e\u0001\"\u0001\u0003\u0004\"9!Q\u0012\u0004\u0005\u0002\t=\u0005b\u0002BM\r\u0011\u0005!1\u0014\u0005\b\u0005G3A\u0011\u0001BS\u0011\u001d\u0011iK\u0002C\u0001\u0005_CqA!0\u0007\t\u0003\u0011y\fC\u0004\u0003F\u001a!\tAa2\t\u000f\t-g\u0001\"\u0001\u0003N\"9!\u0011\u001b\u0004\u0005\u0002\tM\u0007b\u0002Bt\r\u0011\u0005!\u0011\u001e\u0005\b\u0005k4A\u0011\u0001B|\u0011\u001d\u0011)P\u0002C\u0001\u0007\u000bAqa!\u0003\u0007\t\u0003\u0019Y\u0001C\u0004\u0004\n\u0019!\ta!\u000b\t\u000f\r%a\u0001\"\u0001\u00042!91\u0011\u0002\u0004\u0005\u0002\re\u0002bBB\u0005\r\u0011\u00051q\b\u0005\b\u0007\u001b2A\u0011AB(\u0011\u001d\u0019iE\u0002C\u0001\u00077Bqaa\u0018\u0007\t\u0003\u0019\t\u0007C\u0004\u0004r\u0019!\taa\u001d\t\u0015\r\u0005e\u0001#b\u0001\n\u0003\u0019\u0019\tC\u0004\u0004\u0012\u001a!\taa%\t\u000f\r}e\u0001\"\u0001\u0004\"\"91Q\u0015\u0004\u0005\u0002\r\u001d\u0006bBBV\r\u0011\u00051Q\u0016\u0005\b\u0007c3A\u0011ABZ\u0011%\u0019iNBI\u0001\n\u0003\u0019y\u000eC\u0005\u0004v\u001a\t\n\u0011\"\u0001\u0004x\"911 \u0004\u0005\u0002\ru\b\"\u0003C\u0010\rE\u0005I\u0011ABp\u0011%!\tCBI\u0001\n\u0003\u00199\u0010C\u0004\u0005$\u0019!\t\u0001\"\n\t\u0013\u0011Ub!%A\u0005\u0002\r}\u0007\"\u0003C\u001c\rE\u0005I\u0011AB|\u0011\u001d!ID\u0002C\u0001\twA\u0011\u0002\"\u0015\u0007#\u0003%\taa8\t\u0013\u0011Mc!%A\u0005\u0002\r]\bb\u0002C+\r\u0011\u0005Aq\u000b\u0005\n\tC2\u0011\u0013!C\u0001\u0007?D\u0011\u0002b\u0019\u0007#\u0003%\taa>\t\u000f\u0011\u0015d\u0001\"\u0001\u0005h!IAQ\u0012\u0004\u0012\u0002\u0013\u00051q\u001f\u0005\n\t\u001f3\u0011\u0013!C\u0001\t#Cq\u0001\"&\u0007\t\u0003!9\nC\u0005\u0005 \u001a\t\n\u0011\"\u0001\u0004x\"IA\u0011\u0015\u0004\u0012\u0002\u0013\u0005A\u0011\u0013\u0005\b\tG3A\u0011\u0001CS\u0011\u001d!IK\u0002C\u0001\tWCq\u0001b,\u0007\t\u0003!\t\fC\u0004\u00056\u001a!\t\u0001b.\t\u000f\u0011uf\u0001\"\u0001\u0005@\"9AQ\u0018\u0004\u0005\u0002\u0011\u0015\u0007b\u0002C_\r\u0011\u0005A1\u001a\u0005\b\u0007#4A\u0011\u0001Ci\u0011\u001d\u0011YB\u0002C\u0001\t+DqAa\u000f\u0007\t\u0003!y\u000eC\u0004\u0003l\u0019!\t\u0001\">\t\u000f\u0015%a\u0001\"\u0001\u0006\f!9QQ\u0003\u0004\u0005\u0002\u0015]\u0001bBC\u0011\r\u0011\u0005Q1\u0005\u0005\b\u000bs1A\u0011AC\u001e\u0011\u001d)\u0019E\u0002C\u0001\u000b\u000bBq!\"\u0014\u0007\t\u0003)y\u0005C\u0004\u0006X\u0019!\t!\"\u0017\t\u000f\u0015\u0005d\u0001\"\u0001\u0006d!9Qq\r\u0004\u0005\u0002\u0015%\u0004bBC9\r\u0011\u0005Q1\u000f\u0005\b\u000b{2A\u0011AC@\u0011\u001d)\u0019J\u0002C\u0001\u000b+Cq!b(\u0007\t\u0003)\t\u000bC\u0004\u0006 \u001a!\t!\"-\t\u000f\u0015}e\u0001\"\u0001\u0006:\"9Qq\u0014\u0004\u0005\u0002\u0015\u0005\u0007bBCP\r\u0011\u0005Q\u0011\u001a\u0005\b\u000b'4A\u0011ACk\u0011\u001d)\u0019N\u0002C\u0001\u000b7Dq!\"9\u0007\t\u0003)\u0019\u000fC\u0004\u0006t\u001a!\t!\">\t\u000f\u0019\ra\u0001\"\u0001\u0007\u0006!9aq\u0002\u0004\u0005\u0002\u0019E\u0001b\u0002D\u0010\r\u0011\u0005a\u0011\u0005\u0005\b\rc1A\u0011\u0001D\u001a\u0011\u001d1iD\u0002C\u0001\r\u007fAqAb\u0014\u0007\t\u00031\t\u0006C\u0004\u0007b\u0019!\tAb\u0019\t\u000f\u0019=d\u0001\"\u0001\u0007r\u00151aQ\u0010\u0004\u0001\r\u007fB!Bb\"\u0007\u0011\u000b\u0007I\u0011\u0001DE\u0011)\u0019I\t\u0001EC\u0002\u0013\u000511\u0011\u0002\n\u0013:$XM\u001d8bYNT!!\u001d:\u0002\u0011%tG/\u001a:oC2T!a\u001d;\u0002\u000fI,g\r\\3di*\tQ/A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001AH\u0010\u0005\u0002zu6\tA/\u0003\u0002|i\n1\u0011I\\=SK\u001a\u00042!`A\u0001\u001b\u0005q(BA@s\u0003\r\t\u0007/[\u0005\u0003_z\fa\u0001J5oSR$CCAA\u0004!\rI\u0018\u0011B\u0005\u0004\u0003\u0017!(\u0001B+oSR\u0014\u0001\"\u00138uKJt\u0017\r\u001c\t\u0005\u0003#\t\u0019\"D\u0001\u0001\u0013\u0011\t)\"a\u0006\u0003!5\u000b7M]8J]R,'O\\1m\u0003BL\u0017\u0002BA\r\u00037\u0011\u0001\"\u00168jm\u0016\u00148/\u001a\u0006\u0004\u0003;\u0011\u0018AB7bGJ|7/\u0006\u0002\u0002\"A\u0019\u0011\u0011\u0003\u0002\u0003\r\r{W\u000e]1u!\u0011\t\t\"a\n\n\t\u0005%\u0012q\u0003\u0002\u000f\u001b\u0006\u001c'o\\\"p[B\fG/\u00119jQ-!\u0011QFA\u001a\u0003k\tI$a\u000f\u0011\u0007e\fy#C\u0002\u00022Q\u0014!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#!a\u000e\u0002C\r|W\u000e]1uS\nLG.\u001b;zA]LG\u000f\u001b\u0011TG\u0006d\u0017\r\t\u001a/cA\u0002Si\u0014'\u0002\u000bMLgnY3\"\u0005\u0005u\u0012A\u0002\u001a/cMr\u0003'\u0001\u0004d_6\u0004\u0018\r^\u000b\u0003\u0003\u0007\u00022!!\u0005\u0005Q-)\u0011QFA\u001a\u0003k\tI$a\u000f\u0003'MKXNY8m)\u0006\u0014G.Z%oi\u0016\u0014h.\u00197\u0014\t\u0019A\u0018qB\u0001\u0013e\u0016Lg-[2bi&|gnU;qa>\u0014H/\u0006\u0002\u0002PA!\u0011\u0011CA)\u0013\u0011\t\u0019&!\u0001\u0003+I+\u0017NZ5dCRLwN\\*vaB|'\u000f^!qS\u0006q1M]3bi\u0016LU\u000e]8si\u0016\u0014H\u0003BA-\u0003_\u0012B!a\u0017\u0002`\u00191\u0011Q\f\u0004\u0001\u00033\u0012A\u0002\u0010:fM&tW-\\3oiz\u0002B!!\u0005\u0002b%!\u00111MA\u0001\u0005!IU\u000e]8si\u0016\u0014\bBCA4\u00037\u0012\rQ\"\u0011\u0002j\u0005!aM]8n+\t\tYG\u0004\u0003\u0002n\u0005=D\u0002\u0001\u0005\b\u0003cJ\u0001\u0019AA:\u0003\u00151'o\\71!\ri\u0018QO\u0005\u0004\u00033q\u0018\u0001\u00048foN\u001bw\u000e]3XSRDG\u0003BA>\u0003\u000b\u0003B!!\u0005\u0002~%!\u0011qPAA\u0005\u0015\u00196m\u001c9f\u0013\r\t\u0019\t\u001d\u0002\u0007'\u000e|\u0007/Z:\t\u000f\u0005\u001d%\u00021\u0001\u0002\n\u0006)Q\r\\3ngB)\u00110a#\u0002\u0010&\u0019\u0011Q\u0012;\u0003\u0015q\u0012X\r]3bi\u0016$g\b\u0005\u0003\u0002\u0012\u0005E\u0015\u0002BAJ\u0003+\u0013aaU=nE>d\u0017bAALa\n91+_7c_2\u001c\u0018!B3oi\u0016\u0014HCBAO\u0003?\u000b\u0019K\u0004\u0003\u0002n\u0005}\u0005bBAQ\u0017\u0001\u0007\u00111P\u0001\u0006g\u000e|\u0007/\u001a\u0005\b\u0003K[\u0001\u0019AAH\u0003\r\u0019\u00180\\\u0001\u0007k:d\u0017N\\6\u0015\r\u0005-\u0016QVAX\u001d\u0011\ti'!,\t\u000f\u0005\u0005F\u00021\u0001\u0002|!9\u0011Q\u0015\u0007A\u0002\u0005=\u0015!\u00034sK\u0016$VM]7t)\u0011\t),!3\u0011\r\u0005]\u0016QXAb\u001d\rI\u0018\u0011X\u0005\u0004\u0003w#\u0018a\u00029bG.\fw-Z\u0005\u0005\u0003\u007f\u000b\tM\u0001\u0003MSN$(bAA^iB!\u0011\u0011CAc\u0013\u0011\t9-!&\u0003\u001d\u0019\u0013X-\u001a+fe6\u001c\u00160\u001c2pY\"9\u00111Z\u0007A\u0002\u00055\u0017\u0001\u0002;sK\u0016\u0004B!!\u0005\u0002P&!\u0011\u0011[Aj\u0005\u0011!&/Z3\n\u0007\u0005U\u0007OA\u0003Ue\u0016,7/A\u0005ge\u0016,G+\u001f9fgR!\u00111\\Ar!\u0019\t9,!0\u0002^B!\u0011\u0011CAp\u0013\u0011\t\t/!&\u0003\u001d\u0019\u0013X-\u001a+za\u0016\u001c\u00160\u001c2pY\"9\u00111\u001a\bA\u0002\u00055\u0017!E:vEN$\u0018\u000e^;uKNKXNY8mgRA\u0011QZAu\u0003W\fy\u000fC\u0004\u0002L>\u0001\r!!4\t\u000f\u0005\u001dt\u00021\u0001\u0002nB1\u0011qWA_\u0003\u001fCq!!=\u0010\u0001\u0004\ti/\u0001\u0002u_\u0006y1/\u001e2ti&$X\u000f^3UsB,7\u000f\u0006\u0005\u0002N\u0006]\u0018\u0011`A~\u0011\u001d\tY\r\u0005a\u0001\u0003\u001bDq!a\u001a\u0011\u0001\u0004\ti\u000fC\u0004\u0002rB\u0001\r!!@\u0011\r\u0005]\u0016QXA\u0000!\u0011\t\tB!\u0001\n\t\t\r!Q\u0001\u0002\u0005)f\u0004X-C\u0002\u0003\bA\u0014Q\u0001V=qKN\fab];cgRLG/\u001e;f)\"L7\u000f\u0006\u0005\u0002N\n5!q\u0002B\n\u0011\u001d\tY-\u0005a\u0001\u0003\u001bDqA!\u0005\u0012\u0001\u0004\ty)A\u0003dY\u0006T(\u0010\u0003\u0005\u0002rF!\t\u0019\u0001B\u000b!\u0015I(qCAg\u0013\r\u0011I\u0002\u001e\u0002\ty\tLh.Y7f}\u0005Y\u0011\r\u001e;bG\"lWM\u001c;t)\u0011\u0011yB!\u000f\u0013\t\t\u0005\"1\u0005\u0004\u0007\u0003;2\u0001Aa\b\u0011\t\t\u0015\"qE\u0007\u0003\u00037IAA!\u000b\u0002\u001c\tY\u0011\t\u001e;bG\"lWM\u001c;t\u000b\u001d\u0011iC!\t!\u0005_\u00111\u0001U8t!\u0011\t\tB!\r\n\t\tM\"Q\u0007\u0002\t!>\u001c\u0018\u000e^5p]&\u0019!q\u00079\u0003\u0013A{7/\u001b;j_:\u001c\bbBAf%\u0001\u0007\u0011QZ\u0001\u0011kB$\u0017\r^3BiR\f7\r[7f]R,BAa\u0010\u0003VQ1!\u0011\tB#\u0005O\"BAa\u0011\u0003H9!\u0011Q\u000eB#\u0011\u001d\tYm\u0005a\u0001\u0003\u001bD\u0011B!\u0013\u0014\u0003\u0003\u0005\u001dAa\u0013\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0003N\t=#1K\u0007\u0002e&\u0019!\u0011\u000b:\u0003\u0011\rc\u0017m]:UC\u001e\u0004B!!\u001c\u0003V\u00119!qK\nC\u0002\te#!\u0001+\u0012\t\tm#\u0011\r\t\u0004s\nu\u0013b\u0001B0i\n9aj\u001c;iS:<\u0007cA=\u0003d%\u0019!Q\r;\u0003\u0007\u0005s\u0017\u0010C\u0004\u0003jM\u0001\rAa\u0015\u0002\u0015\u0005$H/Y2i[\u0016tG/\u0001\tsK6|g/Z!ui\u0006\u001c\u0007.\\3oiV!!q\u000eB@)\u0011\u0011\tH!\u001e\u0015\t\tM$q\u000f\b\u0005\u0003[\u0012)\bC\u0004\u0002LR\u0001\r!!4\t\u0013\teD#!AA\u0004\tm\u0014AC3wS\u0012,gnY3%eA1!Q\nB(\u0005{\u0002B!!\u001c\u0003\u0000\u00119!q\u000b\u000bC\u0002\te\u0013AB:fiB{7\u000f\u0006\u0004\u0003\u0006\n\u001d%\u0011\u0012\b\u0005\u0003[\u00129\tC\u0004\u0002LV\u0001\r!!4\t\u000f\t-U\u00031\u0001\u00030\u00051a.Z<q_N\fqa]3u)f\u0004X\r\u0006\u0004\u0003\u0012\nM%Q\u0013\b\u0005\u0003[\u0012\u0019\nC\u0004\u0002LZ\u0001\r!!4\t\u000f\t]e\u00031\u0001\u0002\u0000\u0006\u0011A\u000f]\u0001\u000bI\u00164\u0017N\\3UsB,GC\u0002BO\u0005?\u0013\tK\u0004\u0003\u0002n\t}\u0005bBAf/\u0001\u0007\u0011Q\u001a\u0005\b\u0005/;\u0002\u0019AA\u0000\u0003%\u0019X\r^*z[\n|G\u000e\u0006\u0004\u0003(\n%&1\u0016\b\u0005\u0003[\u0012I\u000bC\u0004\u0002Lb\u0001\r!!4\t\u000f\u0005\u0015\u0006\u00041\u0001\u0002\u0010\u0006Y1/\u001a;Pe&<\u0017N\\1m)\u0019\u0011\tLa.\u0003<B!\u0011\u0011\u0003BZ\u0013\u0011\u0011),a5\u0003\u0011QK\b/\u001a+sK\u0016DqA!/\u001a\u0001\u0004\u0011\t,\u0001\u0002ui\"9\u00111Z\rA\u0002\u00055\u0017aD2baR,(/\u001a,be&\f'\r\\3\u0015\t\u0005\u001d!\u0011\u0019\u0005\b\u0005\u0007T\u0002\u0019AAH\u0003\u00111(\r\\3\u00023I,g-\u001a:f]\u000e,7)\u00199ukJ,GMV1sS\u0006\u0014G.\u001a\u000b\u0005\u0003\u001b\u0014I\rC\u0004\u0003Dn\u0001\r!a$\u0002)\r\f\u0007\u000f^;sK\u00124\u0016M]5bE2,G+\u001f9f)\u0011\tyPa4\t\u000f\t\rG\u00041\u0001\u0002\u0010\u0006A1\r\\1tg\u0012+g\r\u0006\u0004\u0003V\nm'Q\u001c\t\u0005\u0003#\u00119.\u0003\u0003\u0003Z\u0006M'\u0001C\"mCN\u001cH)\u001a4\t\u000f\u0005\u0015V\u00041\u0001\u0002\u0010\"9!q\\\u000fA\u0002\t\u0005\u0018\u0001B5na2\u0004B!!\u0005\u0003d&!!Q]Aj\u0005!!V-\u001c9mCR,\u0017!C7pIVdW\rR3g)\u0019\u0011YO!=\u0003tB!\u0011\u0011\u0003Bw\u0013\u0011\u0011y/a5\u0003\u00135{G-\u001e7f\t\u00164\u0007bBAS=\u0001\u0007\u0011q\u0012\u0005\b\u0005?t\u0002\u0019\u0001Bq\u0003\u00191\u0018\r\u001c#fMR1!\u0011 B\u0000\u0007\u0003\u0001B!!\u0005\u0003|&!!Q`Aj\u0005\u00191\u0016\r\u001c#fM\"9\u0011QU\u0010A\u0002\u0005=\u0005bBB\u0002?\u0001\u0007\u0011QZ\u0001\u0004e\"\u001cH\u0003\u0002B}\u0007\u000fAq!!*!\u0001\u0004\ty)\u0001\u0004eK\u001a$UM\u001a\u000b\u000b\u0007\u001b\u0019\u0019b!\u0006\u0004 \r\u001d\u0002\u0003BA\t\u0007\u001fIAa!\u0005\u0002T\n1A)\u001a4EK\u001aDq!!*\"\u0001\u0004\ty\tC\u0004\u0004\u0018\u0005\u0002\ra!\u0007\u0002\t5|Gm\u001d\t\u0005\u0003#\u0019Y\"\u0003\u0003\u0004\u001e\u0005M'!C'pI&4\u0017.\u001a:t\u0011\u001d\u0019\t#\ta\u0001\u0007G\t\u0001B\u001e9be\u0006l7o\u001d\t\u0007\u0003o\u000bil!\n\u0011\r\u0005]\u0016Q\u0018B}\u0011\u001d\u0019\u0019!\ta\u0001\u0003\u001b$\u0002b!\u0004\u0004,\r52q\u0006\u0005\b\u0003K\u0013\u0003\u0019AAH\u0011\u001d\u0019\tC\ta\u0001\u0007GAqaa\u0001#\u0001\u0004\ti\r\u0006\u0005\u0004\u000e\rM2QGB\u001c\u0011\u001d\t)k\ta\u0001\u0003\u001fCqaa\u0006$\u0001\u0004\u0019I\u0002C\u0004\u0004\u0004\r\u0002\r!!4\u0015\r\r511HB\u001f\u0011\u001d\t)\u000b\na\u0001\u0003\u001fCqaa\u0001%\u0001\u0004\ti\r\u0006\u0004\u0004\u000e\r\u000531\t\u0005\b\u0003K+\u0003\u0019AAH\u0011\u001d\u0019\u0019!\na\u0001\u0007\u000b\u0002r!_B$\u0007\u0017\ni-C\u0002\u0004JQ\u0014\u0011BR;oGRLwN\\\u0019\u0011\r\u0005]\u0016QXAw\u0003\u001d!\u0018\u0010]3EK\u001a$ba!\u0015\u0004X\re\u0003\u0003BA\t\u0007'JAa!\u0016\u0002T\n9A+\u001f9f\t\u00164\u0007bBASM\u0001\u0007\u0011q\u0012\u0005\b\u0007\u00071\u0003\u0019AAg)\u0011\u0019\tf!\u0018\t\u000f\u0005\u0015v\u00051\u0001\u0002\u0010\u0006AA.\u00192fY\u0012+g\r\u0006\u0005\u0004d\r%41NB8!\u0011\t\tb!\u001a\n\t\r\u001d\u00141\u001b\u0002\t\u0019\u0006\u0014W\r\u001c#fM\"9\u0011Q\u0015\u0015A\u0002\u0005=\u0005bBB7Q\u0001\u0007\u0011Q^\u0001\u0007a\u0006\u0014\u0018-\\:\t\u000f\r\r\u0001\u00061\u0001\u0002N\u0006Y1\r[1oO\u0016|uO\\3s)!\u0019)ha\u001e\u0004z\rud\u0002BA7\u0007oBq!a3*\u0001\u0004\ti\rC\u0004\u0004|%\u0002\r!a$\u0002\tA\u0014XM\u001e\u0005\b\u0007\u007fJ\u0003\u0019AAH\u0003\u0011qW\r\u001f;\u0002\u0007\u001d,g.\u0006\u0002\u0004\u0006J)1q\u0011=\u0004\f\u001a1\u0011Q\f8\u0001\u0007\u000b\u000b\u0011\u0002\u001e:fK\n+\u0018\u000e\u001c3\u0011\t\u0005E1QR\u0005\u0005\u0007\u001f\u000b9BA\u0004Ue\u0016,w)\u001a8\u0002\u0015%\u001chI]3f)\u0016\u0014X\u000e\u0006\u0003\u0004\u0016\u000em\u0005cA=\u0004\u0018&\u00191\u0011\u0014;\u0003\u000f\t{w\u000e\\3b]\"91QT\u0016A\u0002\u0005=\u0015AB:z[\n|G.\u0001\u0006bg\u001a\u0013X-\u001a+fe6$B!a1\u0004$\"91Q\u0014\u0017A\u0002\u0005=\u0015AC5t\rJ,W\rV=qKR!1QSBU\u0011\u001d\u0019i*\fa\u0001\u0003\u001f\u000b!\"Y:Ge\u0016,G+\u001f9f)\u0011\tina,\t\u000f\rue\u00061\u0001\u0002\u0010\u0006ia.Z<UKJl7+_7c_2$\"b!.\u0004<\u000eu61ZBh!\u0011\t\tba.\n\t\re\u0016Q\u0013\u0002\u000b)\u0016\u0014XnU=nE>d\u0007bBBO_\u0001\u0007\u0011q\u0012\u0005\b\u0007\u007f{\u0003\u0019ABa\u0003\u0011q\u0017-\\3\u0011\t\u0005E11Y\u0005\u0005\u0007\u000b\u001c9M\u0001\u0005UKJlg*Y7f\u0013\r\u0019I\r\u001d\u0002\u0006\u001d\u0006lWm\u001d\u0005\n\u0007\u001b|\u0003\u0013!a\u0001\u0005_\t1\u0001]8t\u0011%\u0019\tn\fI\u0001\u0002\u0004\u0019\u0019.A\u0003gY\u0006<7\u000f\u0005\u0003\u0002\u0012\rU\u0017\u0002BBl\u00073\u0014qA\u00127bON+G/C\u0002\u0004\\B\u0014\u0001B\u00127bON+Go]\u0001\u0018]\u0016<H+\u001a:n'fl'm\u001c7%I\u00164\u0017-\u001e7uIM*\"a!9+\t\t=21]\u0016\u0003\u0007K\u0004Baa:\u0004r6\u00111\u0011\u001e\u0006\u0005\u0007W\u001ci/A\u0005v]\u000eDWmY6fI*\u00191q\u001e;\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0004t\u000e%(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00069b.Z<UKJl7+_7c_2$C-\u001a4bk2$H\u0005N\u000b\u0003\u0007sTCaa5\u0004d\u00069b.Z<N_\u0012,H.Z!oI\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u000b\u000b\u0007\u007f$\t\u0002b\u0005\u0005\u001c\u0011u\u0001cB=\u0005\u0002\u0011\u0015A1B\u0005\u0004\t\u0007!(A\u0002+va2,'\u0007\u0005\u0003\u0002\u0012\u0011\u001d\u0011\u0002\u0002C\u0005\u0003+\u0013A\"T8ek2,7+_7c_2\u0004B!!\u0005\u0005\u000e%!AqBAK\u0005-\u0019E.Y:t'fl'm\u001c7\t\u000f\ru%\u00071\u0001\u0002\u0010\"91q\u0018\u001aA\u0002\u0011U\u0001\u0003BA\t\t/IA\u0001\"\u0007\u0004H\n!a*Y7f\u0011%\u0019iM\rI\u0001\u0002\u0004\u0011y\u0003C\u0005\u0004RJ\u0002\n\u00111\u0001\u0004T\u0006\tc.Z<N_\u0012,H.Z!oI\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%g\u0005\tc.Z<N_\u0012,H.Z!oI\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%i\u0005ya.Z<NKRDw\u000eZ*z[\n|G\u000e\u0006\u0006\u0005(\u00115Bq\u0006C\u0019\tg\u0001B!!\u0005\u0005*%!A1FAK\u00051iU\r\u001e5pINKXNY8m\u0011\u001d\u0019i*\u000ea\u0001\u0003\u001fCqaa06\u0001\u0004\u0019\t\rC\u0005\u0004NV\u0002\n\u00111\u0001\u00030!I1\u0011[\u001b\u0011\u0002\u0003\u000711[\u0001\u001a]\u0016<X*\u001a;i_\u0012\u001c\u00160\u001c2pY\u0012\"WMZ1vYR$3'A\roK^lU\r\u001e5pINKXNY8mI\u0011,g-Y;mi\u0012\"\u0014!\u00048foRK\b/Z*z[\n|G\u000e\u0006\u0006\u0005>\u0011\rCQ\tC'\t\u001f\u0002B!!\u0005\u0005@%!A\u0011IAK\u0005)!\u0016\u0010]3Ts6\u0014w\u000e\u001c\u0005\b\u0007;C\u0004\u0019AAH\u0011\u001d\u0019y\f\u000fa\u0001\t\u000f\u0002B!!\u0005\u0005J%!A1JBd\u0005!!\u0016\u0010]3OC6,\u0007\"CBgqA\u0005\t\u0019\u0001B\u0018\u0011%\u0019\t\u000e\u000fI\u0001\u0002\u0004\u0019\u0019.A\foK^$\u0016\u0010]3Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%g\u00059b.Z<UsB,7+_7c_2$C-\u001a4bk2$H\u0005N\u0001\u000f]\u0016<8\t\\1tgNKXNY8m))!Y\u0001\"\u0017\u0005\\\u0011uCq\f\u0005\b\u0007;[\u0004\u0019AAH\u0011\u001d\u0019yl\u000fa\u0001\t\u000fB\u0011b!4<!\u0003\u0005\rAa\f\t\u0013\rE7\b%AA\u0002\rM\u0017\u0001\u00078fo\u000ec\u0017m]:Ts6\u0014w\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%g\u0005Ab.Z<DY\u0006\u001c8oU=nE>dG\u0005Z3gCVdG\u000f\n\u001b\u0002\u00179,wO\u0012:fKR+'/\u001c\u000b\u000b\u0003\u0007$I\u0007\"!\u0005\b\u0012%\u0005bBB`}\u0001\u0007A1\u000e\t\u0005\t[\"YH\u0004\u0003\u0005p\u0011]\u0004c\u0001C9i6\u0011A1\u000f\u0006\u0004\tk2\u0018A\u0002\u001fs_>$h(C\u0002\u0005zQ\fa\u0001\u0015:fI\u00164\u0017\u0002\u0002C?\t\u007f\u0012aa\u0015;sS:<'b\u0001C=i\"AA1\u0011 \u0005\u0002\u0004!))A\u0003wC2,X\rE\u0003z\u0005/\u0011\t\u0007C\u0005\u0004Rz\u0002\n\u00111\u0001\u0004T\"IA1\u0012 \u0011\u0002\u0003\u0007A1N\u0001\u0007_JLw-\u001b8\u0002+9,wO\u0012:fKR+'/\u001c\u0013eK\u001a\fW\u000f\u001c;%g\u0005)b.Z<Ge\u0016,G+\u001a:nI\u0011,g-Y;mi\u0012\"TC\u0001CJU\u0011!Yga9\u0002\u00179,wO\u0012:fKRK\b/\u001a\u000b\t\u0003;$I\nb'\u0005\u001e\"91qX!A\u0002\u0011-\u0004\"CBi\u0003B\u0005\t\u0019ABj\u0011%!Y)\u0011I\u0001\u0002\u0004!Y'A\u000boK^4%/Z3UsB,G\u0005Z3gCVdG\u000f\n\u001a\u0002+9,wO\u0012:fKRK\b/\u001a\u0013eK\u001a\fW\u000f\u001c;%g\u0005Y\u0011n]#se>tWm\\;t)\u0011\u0019)\nb*\t\u000f\ruE\t1\u0001\u0002\u0010\u0006A\u0011n]*l_2,W\u000e\u0006\u0003\u0004\u0016\u00125\u0006bBBO\u000b\u0002\u0007\u0011qR\u0001\fI\u0016\u001c6n\u001c7f[&TX\r\u0006\u0003\u0002\u0010\u0012M\u0006bBBO\r\u0002\u0007\u0011qR\u0001\u000bS:LG/[1mSj,G\u0003\u0002C]\twsA!!\u001c\u0005<\"91QT$A\u0002\u0005=\u0015a\u00044vY2L\u0018J\\5uS\u0006d\u0017N_3\u0015\t\u0011\u0005G1\u0019\b\u0005\u0003[\"\u0019\rC\u0004\u0004\u001e\"\u0003\r!a$\u0015\t\u0011\u001dG\u0011\u001a\b\u0005\u0003[\"I\rC\u0004\u0003\u0018&\u0003\r!a@\u0015\t\u00115Gq\u001a\b\u0005\u0003[\"y\rC\u0004\u0002\"*\u0003\r!a\u001f\u0015\t\rMG1\u001b\u0005\b\u0007;[\u0005\u0019AAH)\u0011!9\u000e\"8\u0013\t\u0011e'1\u0005\u0004\u0007\u0003;2\u0001\u0001b6\u0006\u000f\t5B\u0011\u001c\u0011\u00030!91Q\u0014'A\u0002\u0005=U\u0003\u0002Cq\tc$b\u0001b9\u0005h\u0012MH\u0003\u0002Cs\tStA!!\u001c\u0005h\"91QT'A\u0002\u0005=\u0005\"\u0003Cv\u001b\u0006\u0005\t9\u0001Cw\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0005\u001b\u0012y\u0005b<\u0011\t\u00055D\u0011\u001f\u0003\b\u0005/j%\u0019\u0001B-\u0011\u001d\u0011I'\u0014a\u0001\t_,B\u0001b>\u0006\bQ!A\u0011 C\u007f)\u0011!Y\u0010b@\u000f\t\u00055DQ \u0005\b\u0007;s\u0005\u0019AAH\u0011%)\tATA\u0001\u0002\b)\u0019!\u0001\u0006fm&$WM\\2fIQ\u0002bA!\u0014\u0003P\u0015\u0015\u0001\u0003BA7\u000b\u000f!qAa\u0016O\u0005\u0004\u0011I&\u0001\u0005tKR|uO\\3s)\u0019)i!b\u0004\u0006\u00129!\u0011QNC\b\u0011\u001d\u0019ij\u0014a\u0001\u0003\u001fCq!b\u0005P\u0001\u0004\ty)\u0001\u0005oK^|wO\\3s\u0003\u001d\u0019X\r^%oM>$b!\"\u0007\u0006\u001c\u0015ua\u0002BA7\u000b7Aqa!(Q\u0001\u0004\ty\tC\u0004\u0006 A\u0003\r!a@\u0002\u0007Q\u0004X-\u0001\btKR\feN\\8uCRLwN\\:\u0015\r\u0015\u0015RqEC\u0015\u001d\u0011\ti'b\n\t\u000f\ru\u0015\u000b1\u0001\u0002\u0010\"9Q1F)A\u0002\u00155\u0012AB1o]>$8\u000fE\u0003z\u0003\u0017+y\u0003\u0005\u0003\u0002\u0012\u0015E\u0012\u0002BC\u001a\u000bk\u0011!\"\u00118o_R\fG/[8o\u0013\r)9\u0004\u001d\u0002\u0010\u0003:tw\u000e^1uS>t\u0017J\u001c4pg\u000691/\u001a;OC6,GCBC\u001f\u000b\u007f)\tE\u0004\u0003\u0002n\u0015}\u0002bBBO%\u0002\u0007\u0011q\u0012\u0005\b\u0007\u007f\u0013\u0006\u0019\u0001C\u000b\u0003A\u0019X\r\u001e)sSZ\fG/Z,ji\"Lg\u000e\u0006\u0004\u0006H\u0015%S1\n\b\u0005\u0003[*I\u0005C\u0004\u0004\u001eN\u0003\r!a$\t\u000f\u0005\u00156\u000b1\u0001\u0002\u0010\u000691/\u001a;GY\u0006<GCBC)\u000b'*)F\u0004\u0003\u0002n\u0015M\u0003bBBO)\u0002\u0007\u0011q\u0012\u0005\b\u0007#$\u0006\u0019ABj\u0003%\u0011Xm]3u\r2\fw\r\u0006\u0004\u0006\\\u0015uSq\f\b\u0005\u0003[*i\u0006C\u0004\u0004\u001eV\u0003\r!a$\t\u000f\rEW\u000b1\u0001\u0004T\u0006AA\u000f[5t)f\u0004X\r\u0006\u0003\u0002\u0000\u0016\u0015\u0004bBAS-\u0002\u0007\u0011qR\u0001\u000bg&tw\r\\3UsB,GCBA\u0000\u000bW*y\u0007C\u0004\u0006n]\u0003\r!a@\u0002\u0007A\u0014X\rC\u0004\u0002&^\u0003\r!a$\u0002\u0013M,\b/\u001a:UsB,GCBA\u0000\u000bk*I\bC\u0004\u0006xa\u0003\r!a@\u0002\u000fQD\u0017n\u001d;qK\"9Q1\u0010-A\u0002\u0005}\u0018\u0001C:va\u0016\u0014H\u000f]3\u0002\u0019\r|gn\u001d;b]R$\u0016\u0010]3\u0015\t\u0015\u0005Uq\u0011\t\u0005\u0003#)\u0019)\u0003\u0003\u0006\u0006\n\u0015!\u0001D\"p]N$\u0018M\u001c;UsB,\u0007b\u0002CB3\u0002\u0007Q\u0011\u0012\t\u0005\u0003#)Y)\u0003\u0003\u0006\u000e\u0016=%\u0001C\"p]N$\u0018M\u001c;\n\u0007\u0015E\u0005OA\u0005D_:\u001cH/\u00198ug\u00069A/\u001f9f%\u00164G\u0003CA\u0000\u000b/+I*b'\t\u000f\u00155$\f1\u0001\u0002\u0000\"9\u0011Q\u0015.A\u0002\u0005=\u0005bBCO5\u0002\u0007\u0011Q`\u0001\u0005CJ<7/A\u0006sK\u001aLg.\u001a3UsB,GCBCR\u000bS+i\u000b\u0005\u0003\u0002\u0012\u0015\u0015\u0016\u0002BCT\u0005\u000b\u00111BU3gS:,G\rV=qK\"9Q1V.A\u0002\u0005u\u0018a\u00029be\u0016tGo\u001d\u0005\b\u000b_[\u0006\u0019AA>\u0003\u0015!Wm\u00197t)!)\u0019+b-\u00066\u0016]\u0006bBCV9\u0002\u0007\u0011Q \u0005\b\u000b_c\u0006\u0019AA>\u0011\u001d\u0011\t\u0002\u0018a\u0001\u0003\u001f#b!a@\u0006<\u0016u\u0006bBCV;\u0002\u0007\u0011Q \u0005\b\u000b\u007fk\u0006\u0019AAH\u0003\u0015ywO\\3s)!\ty0b1\u0006F\u0016\u001d\u0007bBCV=\u0002\u0007\u0011Q \u0005\b\u000b\u007fs\u0006\u0019AAH\u0011\u001d)yK\u0018a\u0001\u0003w\"\"\"a@\u0006L\u00165WqZCi\u0011\u001d)Yk\u0018a\u0001\u0003{Dq!b0`\u0001\u0004\ty\tC\u0004\u00060~\u0003\r!a\u001f\t\u000f\r5w\f1\u0001\u00030\u0005\u0001\u0012N\u001c;feN,7\r^5p]RK\b/\u001a\u000b\u0005\u0003\u007f,9\u000eC\u0004\u0006Z\u0002\u0004\r!!@\u0002\u0007Q\u00048\u000f\u0006\u0004\u0002\u0000\u0016uWq\u001c\u0005\b\u000b3\f\u0007\u0019AA\u007f\u0011\u001d)y,\u0019a\u0001\u0003\u001f\u000bQb\u00197bgNLeNZ8UsB,G\u0003CCs\u000bW,i/b<\u0011\t\u0005EQq]\u0005\u0005\u000bS\u0014)AA\u0007DY\u0006\u001c8/\u00138g_RK\b/\u001a\u0005\b\u000bW\u0013\u0007\u0019AA\u007f\u0011\u001d)yK\u0019a\u0001\u0003wBq!\"=c\u0001\u0004\ty)\u0001\u0006usB,7+_7c_2\f!\"\\3uQ>$G+\u001f9f)\u0019)90\"@\u0006\u0000B!\u0011\u0011CC}\u0013\u0011)YP!\u0002\u0003\u00155+G\u000f[8e)f\u0004X\rC\u0004\u0004n\r\u0004\r!!<\t\u000f\u0019\u00051\r1\u0001\u0002\u0000\u0006Q!/Z:vYR$\u0016\u0010]3\u0002#9,H\u000e\\1ss6+G\u000f[8e)f\u0004X\r\u0006\u0003\u0007\b\u00195\u0001\u0003BA\t\r\u0013IAAb\u0003\u0003\u0006\t\tb*\u001e7mCJLX*\u001a;i_\u0012$\u0016\u0010]3\t\u000f\u0019\u0005A\r1\u0001\u0002\u0000\u0006A\u0001o\u001c7z)f\u0004X\r\u0006\u0004\u0007\u0014\u0019eaQ\u0004\t\u0005\u0003#1)\"\u0003\u0003\u0007\u0018\t\u0015!\u0001\u0003)pYf$\u0016\u0010]3\t\u000f\u0019mQ\r1\u0001\u0002n\u0006QA/\u001f9f!\u0006\u0014\u0018-\\:\t\u000f\u0019\u0005Q\r1\u0001\u0002\u0000\u0006yQ\r_5ti\u0016tG/[1m)f\u0004X\r\u0006\u0004\u0007$\u0019%bQ\u0006\t\u0005\u0003#1)#\u0003\u0003\u0007(\t\u0015!aD#ySN$XM\u001c;jC2$\u0016\u0010]3\t\u000f\u0019-b\r1\u0001\u0002n\u0006Q\u0011/^1oi&4\u0017.\u001a3\t\u000f\u0019=b\r1\u0001\u0002\u0000\u0006QQO\u001c3fe2L\u0018N\\4\u0002-\u0015D\u0018n\u001d;f]RL\u0017\r\\!cgR\u0014\u0018m\u0019;j_:$b!a@\u00076\u0019e\u0002b\u0002D\u001cO\u0002\u0007\u0011Q^\u0001\biB\f'/Y7t\u0011\u001d1Yd\u001aa\u0001\u0003\u007f\fA\u0001\u001e9fa\u0005i\u0011M\u001c8pi\u0006$X\r\u001a+za\u0016$bA\"\u0011\u0007H\u00195\u0003\u0003BA\t\r\u0007JAA\"\u0012\u0003\u0006\ti\u0011I\u001c8pi\u0006$X\r\u001a+za\u0016DqA\"\u0013i\u0001\u00041Y%A\u0006b]:|G/\u0019;j_:\u001c\bCBA\\\u0003{+y\u0003C\u0004\u00070!\u0004\r!a@\u0002\u0015QL\b/\u001a\"pk:$7\u000f\u0006\u0004\u0007T\u0019ecQ\f\t\u0005\u0003#1)&\u0003\u0003\u0007X\t\u0015!A\u0003+za\u0016\u0014u.\u001e8eg\"9a1L5A\u0002\u0005}\u0018A\u00017p\u0011\u001d1y&\u001ba\u0001\u0003\u007f\f!\u0001[5\u0002'\t|WO\u001c3fI^KG\u000eZ2be\u0012$\u0016\u0010]3\u0015\t\u0019\u0015d1\u000e\t\u0005\u0003#19'\u0003\u0003\u0007j\t\u0015!a\u0005\"pk:$W\rZ,jY\u0012\u001c\u0017M\u001d3UsB,\u0007b\u0002D7U\u0002\u0007a1K\u0001\u0007E>,h\u000eZ:\u0002\u0017M,(\r]1ui\u0016\u0014hn\u001d\u000b\u0005\rg2Y\bE\u0003z\rk2I(C\u0002\u0007xQ\u0014aa\u00149uS>t\u0007CBA\\\u0003{\u000bi\rC\u0004\u0002L.\u0004\r!!4\u0003\u0015\u0011+7m\u001c:bi>\u00148\u000f\u0005\u0003\u0007\u0002\u001a\rU\"\u0001\u0004\n\t\u0019\u0015\u00151\u0003\u0002\u0012\u001b\u0006\u001c'o\u001c#fG>\u0014\u0018\r^8s\u0003BL\u0017A\u00033fG>\u0014\u0018\r^8sgV\u0011a1\u0012\t\u0004\r\u0003c\u0007\u0003\u0002DH\r#k\u0011\u0001]\u0005\u0004\r'\u0003(aC*z[\n|G\u000eV1cY\u0016\u0004"
)
public interface Internals extends scala.reflect.api.Internals {
   // $FF: synthetic method
   static Universe.MacroInternalApi internal$(final Internals $this) {
      return $this.internal();
   }

   default Universe.MacroInternalApi internal() {
      return (SymbolTable)this.new SymbolTableInternal() {
         private scala.reflect.api.Internals.ReificationSupportApi reificationSupport;
         private Universe.TreeGen gen;
         private Universe.MacroInternalApi.MacroDecoratorApi decorators;
         private volatile byte bitmap$0;
         // $FF: synthetic field
         private final SymbolTable $outer;

         public scala.reflect.api.Internals.Importer createImporter(final scala.reflect.api.Universe from0) {
            return Internals.SymbolTableInternal.super.createImporter(from0);
         }

         public Scopes.Scope newScopeWith(final Seq elems) {
            return Internals.SymbolTableInternal.super.newScopeWith(elems);
         }

         public Scopes.Scope enter(final Scopes.Scope scope, final Symbols.Symbol sym) {
            return Internals.SymbolTableInternal.super.enter(scope, sym);
         }

         public Scopes.Scope unlink(final Scopes.Scope scope, final Symbols.Symbol sym) {
            return Internals.SymbolTableInternal.super.unlink(scope, sym);
         }

         public List freeTerms(final Trees.Tree tree) {
            return Internals.SymbolTableInternal.super.freeTerms(tree);
         }

         public List freeTypes(final Trees.Tree tree) {
            return Internals.SymbolTableInternal.super.freeTypes(tree);
         }

         public Trees.Tree substituteSymbols(final Trees.Tree tree, final List from, final List to) {
            return Internals.SymbolTableInternal.super.substituteSymbols(tree, from, to);
         }

         public Trees.Tree substituteTypes(final Trees.Tree tree, final List from, final List to) {
            return Internals.SymbolTableInternal.super.substituteTypes(tree, from, to);
         }

         public Trees.Tree substituteThis(final Trees.Tree tree, final Symbols.Symbol clazz, final Function0 to) {
            return Internals.SymbolTableInternal.super.substituteThis(tree, clazz, to);
         }

         public Attachments attachments(final Trees.Tree tree) {
            return Internals.SymbolTableInternal.super.attachments(tree);
         }

         public Trees.Tree updateAttachment(final Trees.Tree tree, final Object attachment, final ClassTag evidence$1) {
            return Internals.SymbolTableInternal.super.updateAttachment(tree, attachment, evidence$1);
         }

         public Trees.Tree removeAttachment(final Trees.Tree tree, final ClassTag evidence$2) {
            return Internals.SymbolTableInternal.super.removeAttachment(tree, evidence$2);
         }

         public Trees.Tree setPos(final Trees.Tree tree, final Position newpos) {
            return Internals.SymbolTableInternal.super.setPos(tree, newpos);
         }

         public Trees.Tree setType(final Trees.Tree tree, final Types.Type tp) {
            return Internals.SymbolTableInternal.super.setType(tree, tp);
         }

         public Trees.Tree defineType(final Trees.Tree tree, final Types.Type tp) {
            return Internals.SymbolTableInternal.super.defineType(tree, tp);
         }

         public Trees.Tree setSymbol(final Trees.Tree tree, final Symbols.Symbol sym) {
            return Internals.SymbolTableInternal.super.setSymbol(tree, sym);
         }

         public Trees.TypeTree setOriginal(final Trees.TypeTree tt, final Trees.Tree tree) {
            return Internals.SymbolTableInternal.super.setOriginal(tt, tree);
         }

         public void captureVariable(final Symbols.Symbol vble) {
            Internals.SymbolTableInternal.super.captureVariable(vble);
         }

         public Trees.Tree referenceCapturedVariable(final Symbols.Symbol vble) {
            return Internals.SymbolTableInternal.super.referenceCapturedVariable(vble);
         }

         public Types.Type capturedVariableType(final Symbols.Symbol vble) {
            return Internals.SymbolTableInternal.super.capturedVariableType(vble);
         }

         public Trees.ClassDef classDef(final Symbols.Symbol sym, final Trees.Template impl) {
            return Internals.SymbolTableInternal.super.classDef(sym, impl);
         }

         public Trees.ModuleDef moduleDef(final Symbols.Symbol sym, final Trees.Template impl) {
            return Internals.SymbolTableInternal.super.moduleDef(sym, impl);
         }

         public Trees.ValDef valDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
            return Internals.SymbolTableInternal.super.valDef(sym, rhs);
         }

         public Trees.ValDef valDef(final Symbols.Symbol sym) {
            return Internals.SymbolTableInternal.super.valDef(sym);
         }

         public Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Modifiers mods, final List vparamss, final Trees.Tree rhs) {
            return Internals.SymbolTableInternal.super.defDef(sym, mods, vparamss, rhs);
         }

         public Trees.DefDef defDef(final Symbols.Symbol sym, final List vparamss, final Trees.Tree rhs) {
            return Internals.SymbolTableInternal.super.defDef(sym, vparamss, rhs);
         }

         public Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Modifiers mods, final Trees.Tree rhs) {
            return Internals.SymbolTableInternal.super.defDef(sym, mods, rhs);
         }

         public Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
            return Internals.SymbolTableInternal.super.defDef(sym, rhs);
         }

         public Trees.DefDef defDef(final Symbols.Symbol sym, final Function1 rhs) {
            return Internals.SymbolTableInternal.super.defDef(sym, rhs);
         }

         public Trees.TypeDef typeDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
            return Internals.SymbolTableInternal.super.typeDef(sym, rhs);
         }

         public Trees.TypeDef typeDef(final Symbols.Symbol sym) {
            return Internals.SymbolTableInternal.super.typeDef(sym);
         }

         public Trees.LabelDef labelDef(final Symbols.Symbol sym, final List params, final Trees.Tree rhs) {
            return Internals.SymbolTableInternal.super.labelDef(sym, params, rhs);
         }

         public Trees.Tree changeOwner(final Trees.Tree tree, final Symbols.Symbol prev, final Symbols.Symbol next) {
            return Internals.SymbolTableInternal.super.changeOwner(tree, prev, next);
         }

         public boolean isFreeTerm(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.isFreeTerm(symbol);
         }

         public Symbols.FreeTermSymbol asFreeTerm(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.asFreeTerm(symbol);
         }

         public boolean isFreeType(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.isFreeType(symbol);
         }

         public Symbols.FreeTypeSymbol asFreeType(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.asFreeType(symbol);
         }

         public Symbols.TermSymbol newTermSymbol(final Symbols.Symbol symbol, final Names.TermName name, final Position pos, final long flags) {
            return Internals.SymbolTableInternal.super.newTermSymbol(symbol, name, pos, flags);
         }

         public Position newTermSymbol$default$3() {
            return Internals.SymbolTableInternal.super.newTermSymbol$default$3();
         }

         public long newTermSymbol$default$4() {
            return Internals.SymbolTableInternal.super.newTermSymbol$default$4();
         }

         public Tuple2 newModuleAndClassSymbol(final Symbols.Symbol symbol, final Names.Name name, final Position pos, final long flags) {
            return Internals.SymbolTableInternal.super.newModuleAndClassSymbol(symbol, name, pos, flags);
         }

         public Position newModuleAndClassSymbol$default$3() {
            return Internals.SymbolTableInternal.super.newModuleAndClassSymbol$default$3();
         }

         public long newModuleAndClassSymbol$default$4() {
            return Internals.SymbolTableInternal.super.newModuleAndClassSymbol$default$4();
         }

         public Symbols.MethodSymbol newMethodSymbol(final Symbols.Symbol symbol, final Names.TermName name, final Position pos, final long flags) {
            return Internals.SymbolTableInternal.super.newMethodSymbol(symbol, name, pos, flags);
         }

         public Position newMethodSymbol$default$3() {
            return Internals.SymbolTableInternal.super.newMethodSymbol$default$3();
         }

         public long newMethodSymbol$default$4() {
            return Internals.SymbolTableInternal.super.newMethodSymbol$default$4();
         }

         public Symbols.TypeSymbol newTypeSymbol(final Symbols.Symbol symbol, final Names.TypeName name, final Position pos, final long flags) {
            return Internals.SymbolTableInternal.super.newTypeSymbol(symbol, name, pos, flags);
         }

         public Position newTypeSymbol$default$3() {
            return Internals.SymbolTableInternal.super.newTypeSymbol$default$3();
         }

         public long newTypeSymbol$default$4() {
            return Internals.SymbolTableInternal.super.newTypeSymbol$default$4();
         }

         public Symbols.ClassSymbol newClassSymbol(final Symbols.Symbol symbol, final Names.TypeName name, final Position pos, final long flags) {
            return Internals.SymbolTableInternal.super.newClassSymbol(symbol, name, pos, flags);
         }

         public Position newClassSymbol$default$3() {
            return Internals.SymbolTableInternal.super.newClassSymbol$default$3();
         }

         public long newClassSymbol$default$4() {
            return Internals.SymbolTableInternal.super.newClassSymbol$default$4();
         }

         public Symbols.FreeTermSymbol newFreeTerm(final String name, final Function0 value, final long flags, final String origin) {
            return Internals.SymbolTableInternal.super.newFreeTerm(name, value, flags, origin);
         }

         public long newFreeTerm$default$3() {
            return Internals.SymbolTableInternal.super.newFreeTerm$default$3();
         }

         public String newFreeTerm$default$4() {
            return Internals.SymbolTableInternal.super.newFreeTerm$default$4();
         }

         public Symbols.FreeTypeSymbol newFreeType(final String name, final long flags, final String origin) {
            return Internals.SymbolTableInternal.super.newFreeType(name, flags, origin);
         }

         public long newFreeType$default$2() {
            return Internals.SymbolTableInternal.super.newFreeType$default$2();
         }

         public String newFreeType$default$3() {
            return Internals.SymbolTableInternal.super.newFreeType$default$3();
         }

         public boolean isErroneous(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.isErroneous(symbol);
         }

         public boolean isSkolem(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.isSkolem(symbol);
         }

         public Symbols.Symbol deSkolemize(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.deSkolemize(symbol);
         }

         public Symbols.Symbol initialize(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.initialize(symbol);
         }

         public Symbols.Symbol fullyInitialize(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.fullyInitialize(symbol);
         }

         public Types.Type fullyInitialize(final Types.Type tp) {
            return Internals.SymbolTableInternal.super.fullyInitialize(tp);
         }

         public Scopes.Scope fullyInitialize(final Scopes.Scope scope) {
            return Internals.SymbolTableInternal.super.fullyInitialize(scope);
         }

         public long flags(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.flags(symbol);
         }

         public Attachments attachments(final Symbols.Symbol symbol) {
            return Internals.SymbolTableInternal.super.attachments(symbol);
         }

         public Symbols.Symbol updateAttachment(final Symbols.Symbol symbol, final Object attachment, final ClassTag evidence$3) {
            return Internals.SymbolTableInternal.super.updateAttachment(symbol, attachment, evidence$3);
         }

         public Symbols.Symbol removeAttachment(final Symbols.Symbol symbol, final ClassTag evidence$4) {
            return Internals.SymbolTableInternal.super.removeAttachment(symbol, evidence$4);
         }

         public Symbols.Symbol setOwner(final Symbols.Symbol symbol, final Symbols.Symbol newowner) {
            return Internals.SymbolTableInternal.super.setOwner(symbol, newowner);
         }

         public Symbols.Symbol setInfo(final Symbols.Symbol symbol, final Types.Type tpe) {
            return Internals.SymbolTableInternal.super.setInfo(symbol, tpe);
         }

         public Symbols.Symbol setAnnotations(final Symbols.Symbol symbol, final Seq annots) {
            return Internals.SymbolTableInternal.super.setAnnotations(symbol, annots);
         }

         public Symbols.Symbol setName(final Symbols.Symbol symbol, final Names.Name name) {
            return Internals.SymbolTableInternal.super.setName(symbol, name);
         }

         public Symbols.Symbol setPrivateWithin(final Symbols.Symbol symbol, final Symbols.Symbol sym) {
            return Internals.SymbolTableInternal.super.setPrivateWithin(symbol, sym);
         }

         public Symbols.Symbol setFlag(final Symbols.Symbol symbol, final long flags) {
            return Internals.SymbolTableInternal.super.setFlag(symbol, flags);
         }

         public Symbols.Symbol resetFlag(final Symbols.Symbol symbol, final long flags) {
            return Internals.SymbolTableInternal.super.resetFlag(symbol, flags);
         }

         public Types.Type thisType(final Symbols.Symbol sym) {
            return Internals.SymbolTableInternal.super.thisType(sym);
         }

         public Types.Type singleType(final Types.Type pre, final Symbols.Symbol sym) {
            return Internals.SymbolTableInternal.super.singleType(pre, sym);
         }

         public Types.Type superType(final Types.Type thistpe, final Types.Type supertpe) {
            return Internals.SymbolTableInternal.super.superType(thistpe, supertpe);
         }

         public Types.ConstantType constantType(final Constants.Constant value) {
            return Internals.SymbolTableInternal.super.constantType(value);
         }

         public Types.Type typeRef(final Types.Type pre, final Symbols.Symbol sym, final List args) {
            return Internals.SymbolTableInternal.super.typeRef(pre, sym, args);
         }

         public Types.RefinedType refinedType(final List parents, final Scopes.Scope decls) {
            return Internals.SymbolTableInternal.super.refinedType(parents, decls);
         }

         public Types.RefinedType refinedType(final List parents, final Scopes.Scope decls, final Symbols.Symbol clazz) {
            return Internals.SymbolTableInternal.super.refinedType(parents, decls, clazz);
         }

         public Types.Type refinedType(final List parents, final Symbols.Symbol owner) {
            return Internals.SymbolTableInternal.super.refinedType(parents, owner);
         }

         public Types.Type refinedType(final List parents, final Symbols.Symbol owner, final Scopes.Scope decls) {
            return Internals.SymbolTableInternal.super.refinedType(parents, owner, decls);
         }

         public Types.Type refinedType(final List parents, final Symbols.Symbol owner, final Scopes.Scope decls, final Position pos) {
            return Internals.SymbolTableInternal.super.refinedType(parents, owner, decls, pos);
         }

         public Types.Type intersectionType(final List tps) {
            return Internals.SymbolTableInternal.super.intersectionType(tps);
         }

         public Types.Type intersectionType(final List tps, final Symbols.Symbol owner) {
            return Internals.SymbolTableInternal.super.intersectionType(tps, owner);
         }

         public Types.ClassInfoType classInfoType(final List parents, final Scopes.Scope decls, final Symbols.Symbol typeSymbol) {
            return Internals.SymbolTableInternal.super.classInfoType(parents, decls, typeSymbol);
         }

         public Types.MethodType methodType(final List params, final Types.Type resultType) {
            return Internals.SymbolTableInternal.super.methodType(params, resultType);
         }

         public Types.NullaryMethodType nullaryMethodType(final Types.Type resultType) {
            return Internals.SymbolTableInternal.super.nullaryMethodType(resultType);
         }

         public Types.PolyType polyType(final List typeParams, final Types.Type resultType) {
            return Internals.SymbolTableInternal.super.polyType(typeParams, resultType);
         }

         public Types.ExistentialType existentialType(final List quantified, final Types.Type underlying) {
            return Internals.SymbolTableInternal.super.existentialType(quantified, underlying);
         }

         public Types.Type existentialAbstraction(final List tparams, final Types.Type tpe0) {
            return Internals.SymbolTableInternal.super.existentialAbstraction(tparams, tpe0);
         }

         public Types.AnnotatedType annotatedType(final List annotations, final Types.Type underlying) {
            return Internals.SymbolTableInternal.super.annotatedType(annotations, underlying);
         }

         public Types.TypeBounds typeBounds(final Types.Type lo, final Types.Type hi) {
            return Internals.SymbolTableInternal.super.typeBounds(lo, hi);
         }

         public Types.BoundedWildcardType boundedWildcardType(final Types.TypeBounds bounds) {
            return Internals.SymbolTableInternal.super.boundedWildcardType(bounds);
         }

         public Option subpatterns(final Trees.Tree tree) {
            return Internals.SymbolTableInternal.super.subpatterns(tree);
         }

         public Manifest typeTagToManifest(final Object mirror, final TypeTags.TypeTag tag, final ClassTag evidence$1) {
            return scala.reflect.api.Internals.InternalApi.typeTagToManifest$(this, mirror, tag, evidence$1);
         }

         public TypeTags.TypeTag manifestToTypeTag(final Object mirror, final Manifest manifest) {
            return scala.reflect.api.Internals.InternalApi.manifestToTypeTag$(this, mirror, manifest);
         }

         public scala.reflect.api.Trees.DefDefApi markForAsyncTransform(final scala.reflect.api.Symbols.SymbolApi owner, final scala.reflect.api.Trees.DefDefApi method, final scala.reflect.api.Symbols.SymbolApi awaitSymbol, final Map config) {
            return scala.reflect.api.Internals.InternalApi.markForAsyncTransform$(this, owner, method, awaitSymbol, config);
         }

         private scala.reflect.api.Internals.ReificationSupportApi reificationSupport$lzycompute() {
            synchronized(this){}

            try {
               if ((byte)(this.bitmap$0 & 1) == 0) {
                  this.reificationSupport = Internals.SymbolTableInternal.super.reificationSupport();
                  this.bitmap$0 = (byte)(this.bitmap$0 | 1);
               }
            } catch (Throwable var2) {
               throw var2;
            }

            return this.reificationSupport;
         }

         public scala.reflect.api.Internals.ReificationSupportApi reificationSupport() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.reificationSupport$lzycompute() : this.reificationSupport;
         }

         private Universe.TreeGen gen$lzycompute() {
            synchronized(this){}

            try {
               if ((byte)(this.bitmap$0 & 2) == 0) {
                  this.gen = Internals.SymbolTableInternal.super.gen();
                  this.bitmap$0 = (byte)(this.bitmap$0 | 2);
               }
            } catch (Throwable var2) {
               throw var2;
            }

            return this.gen;
         }

         public Universe.TreeGen gen() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.gen$lzycompute() : this.gen;
         }

         private Universe.MacroInternalApi.MacroDecoratorApi decorators$lzycompute() {
            synchronized(this){}

            try {
               if ((byte)(this.bitmap$0 & 4) == 0) {
                  this.decorators = new Universe.MacroInternalApi.MacroDecoratorApi() {
                     // $FF: synthetic field
                     private final SymbolTableInternal $outer;

                     public scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi TypeDecoratorApi(final scala.reflect.api.Types.TypeApi tp) {
                        return scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi$(this, tp);
                     }

                     public Universe.MacroInternalApi.MacroDecoratorApi.MacroScopeDecoratorApi scopeDecorator(final Scopes.Scope scope) {
                        return new Universe.MacroInternalApi.MacroDecoratorApi.MacroScopeDecoratorApi(scope);
                     }

                     public Universe.MacroInternalApi.MacroDecoratorApi.MacroTreeDecoratorApi treeDecorator(final Trees.Tree tree) {
                        return new Universe.MacroInternalApi.MacroDecoratorApi.MacroTreeDecoratorApi(tree);
                     }

                     public Universe.MacroInternalApi.MacroDecoratorApi.MacroTypeTreeDecoratorApi typeTreeDecorator(final Trees.TypeTree tt) {
                        return new Universe.MacroInternalApi.MacroDecoratorApi.MacroTypeTreeDecoratorApi(tt);
                     }

                     public Universe.MacroInternalApi.MacroDecoratorApi.MacroSymbolDecoratorApi symbolDecorator(final Symbols.Symbol symbol) {
                        return new Universe.MacroInternalApi.MacroDecoratorApi.MacroSymbolDecoratorApi(symbol);
                     }

                     public scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi typeDecorator(final Types.Type tp) {
                        return new scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi(tp);
                     }

                     // $FF: synthetic method
                     public Universe.MacroInternalApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer() {
                        return this.$outer;
                     }

                     // $FF: synthetic method
                     public scala.reflect.api.Internals.InternalApi scala$reflect$api$Internals$InternalApi$DecoratorApi$$$outer() {
                        return this.$outer;
                     }

                     public {
                        if (<VAR_NAMELESS_ENCLOSURE> == null) {
                           throw null;
                        } else {
                           this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        }
                     }
                  };
                  this.bitmap$0 = (byte)(this.bitmap$0 | 4);
               }
            } catch (Throwable var2) {
               throw var2;
            }

            return this.decorators;
         }

         public Universe.MacroInternalApi.MacroDecoratorApi decorators() {
            return (byte)(this.bitmap$0 & 4) == 0 ? this.decorators$lzycompute() : this.decorators;
         }

         // $FF: synthetic method
         public Internals scala$reflect$internal$Internals$SymbolTableInternal$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public Universe scala$reflect$macros$Universe$MacroInternalApi$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public scala.reflect.api.Internals scala$reflect$api$Internals$InternalApi$$$outer() {
            return this.$outer;
         }

         public {
            if (Internals.this == null) {
               throw null;
            } else {
               this.$outer = Internals.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static Universe.MacroCompatApi compat$(final Internals $this) {
      return $this.compat();
   }

   /** @deprecated */
   default Universe.MacroCompatApi compat() {
      return (SymbolTable)this.new MacroCompatApi() {
         /** @deprecated */
         private scala.reflect.api.Internals.CompatToken token;
         // $FF: synthetic field
         private final SymbolTable $outer;

         public Universe.MacroCompatApi.MacroCompatibleSymbol MacroCompatibleSymbol(final scala.reflect.api.Symbols.SymbolApi symbol) {
            return Universe.MacroCompatApi.MacroCompatibleSymbol$(this, symbol);
         }

         public Universe.MacroCompatApi.MacroCompatibleTree MacroCompatibleTree(final scala.reflect.api.Trees.TreeApi tree) {
            return Universe.MacroCompatApi.MacroCompatibleTree$(this, tree);
         }

         public Universe.MacroCompatApi.CompatibleTypeTree CompatibleTypeTree(final scala.reflect.api.Trees.TypeTreeApi tt) {
            return Universe.MacroCompatApi.CompatibleTypeTree$(this, tt);
         }

         public scala.reflect.api.Internals.CompatApi.CompatibleBuildApi CompatibleBuildApi(final scala.reflect.api.Internals.ReificationSupportApi api) {
            return scala.reflect.api.Internals.CompatApi.CompatibleBuildApi$(this, api);
         }

         public scala.reflect.api.Internals.CompatApi.CompatibleTree CompatibleTree(final scala.reflect.api.Trees.TreeApi tree) {
            return scala.reflect.api.Internals.CompatApi.CompatibleTree$(this, tree);
         }

         public scala.reflect.api.Internals.CompatApi.CompatibleSymbol CompatibleSymbol(final scala.reflect.api.Symbols.SymbolApi symbol) {
            return scala.reflect.api.Internals.CompatApi.CompatibleSymbol$(this, symbol);
         }

         /** @deprecated */
         public scala.reflect.api.Internals.CompatToken token() {
            return this.token;
         }

         public void scala$reflect$api$Internals$CompatApi$_setter_$token_$eq(final scala.reflect.api.Internals.CompatToken x$1) {
            this.token = x$1;
         }

         // $FF: synthetic method
         public Universe scala$reflect$macros$Universe$MacroCompatApi$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public scala.reflect.api.Internals scala$reflect$api$Internals$CompatApi$$$outer() {
            return this.$outer;
         }

         public {
            if (Internals.this == null) {
               throw null;
            } else {
               this.$outer = Internals.this;
               scala.reflect.api.Internals.CompatApi.$init$(this);
               Statics.releaseFence();
            }
         }
      };
   }

   // $FF: synthetic method
   static Universe.TreeGen treeBuild$(final Internals $this) {
      return $this.treeBuild();
   }

   default Universe.TreeGen treeBuild() {
      return (SymbolTable)this.new TreeGen() {
         // $FF: synthetic field
         private final SymbolTable $outer;

         public Trees.Tree mkAttributedQualifier(final Types.Type tpe) {
            return this.$outer.gen().mkAttributedQualifier(tpe);
         }

         public Trees.Tree mkAttributedQualifier(final Types.Type tpe, final Symbols.Symbol termSym) {
            return this.$outer.gen().mkAttributedQualifier(tpe, termSym);
         }

         public Trees.RefTree mkAttributedRef(final Types.Type pre, final Symbols.Symbol sym) {
            return this.$outer.gen().mkAttributedRef(pre, sym);
         }

         public Trees.RefTree mkAttributedRef(final Symbols.Symbol sym) {
            return this.$outer.gen().mkAttributedRef(sym);
         }

         public Trees.Tree stabilize(final Trees.Tree tree) {
            return this.$outer.gen().stabilize(tree);
         }

         public Trees.Tree mkAttributedStableRef(final Types.Type pre, final Symbols.Symbol sym) {
            return this.$outer.gen().mkAttributedStableRef(pre, sym);
         }

         public Trees.Tree mkAttributedStableRef(final Symbols.Symbol sym) {
            return this.$outer.gen().mkAttributedStableRef(sym);
         }

         public Trees.RefTree mkUnattributedRef(final Symbols.Symbol sym) {
            return this.$outer.gen().mkUnattributedRef(sym);
         }

         public Trees.RefTree mkUnattributedRef(final Names.Name fullName) {
            return this.$outer.gen().mkUnattributedRef(fullName);
         }

         public Trees.This mkAttributedThis(final Symbols.Symbol sym) {
            return this.$outer.gen().mkAttributedThis(sym);
         }

         public Trees.RefTree mkAttributedIdent(final Symbols.Symbol sym) {
            return this.$outer.gen().mkAttributedIdent(sym);
         }

         public Trees.RefTree mkAttributedSelect(final Trees.Tree qual, final Symbols.Symbol sym) {
            return this.$outer.gen().mkAttributedSelect(qual, sym);
         }

         public Trees.Tree mkMethodCall(final Symbols.Symbol receiver, final Names.Name methodName, final List targs, final List args) {
            return this.$outer.gen().mkMethodCall(receiver, methodName, targs, args);
         }

         public Trees.Tree mkMethodCall(final Symbols.Symbol method, final List targs, final List args) {
            return this.$outer.gen().mkMethodCall(method, targs, args);
         }

         public Trees.Tree mkMethodCall(final Symbols.Symbol method, final List args) {
            return this.$outer.gen().mkMethodCall(method, args);
         }

         public Trees.Tree mkMethodCall(final Trees.Tree target, final List args) {
            return this.$outer.gen().mkMethodCall(target, args);
         }

         public Trees.Tree mkMethodCall(final Symbols.Symbol receiver, final Names.Name methodName, final List args) {
            return this.$outer.gen().mkMethodCall(receiver, methodName, args);
         }

         public Trees.Tree mkMethodCall(final Trees.Tree receiver, final Symbols.Symbol method, final List targs, final List args) {
            return this.$outer.gen().mkMethodCall(receiver, method, targs, args);
         }

         public Trees.Tree mkMethodCall(final Trees.Tree target, final List targs, final List args) {
            return this.$outer.gen().mkMethodCall(target, targs, args);
         }

         public Trees.Tree mkNullaryCall(final Symbols.Symbol method, final List targs) {
            return this.$outer.gen().mkNullaryCall(method, targs);
         }

         public Trees.Tree mkRuntimeUniverseRef() {
            return this.$outer.gen().mkRuntimeUniverseRef();
         }

         public Trees.Tree mkZero(final Types.Type tp) {
            return this.$outer.gen().mkZero(tp);
         }

         public Trees.Tree mkCast(final Trees.Tree tree, final Types.Type pt) {
            return this.$outer.gen().mkCast(tree, pt);
         }

         public {
            if (Internals.this == null) {
               throw null;
            } else {
               this.$outer = Internals.this;
            }
         }
      };
   }

   static void $init$(final Internals $this) {
   }

   public interface SymbolTableInternal extends Universe.MacroInternalApi {
      default scala.reflect.api.Internals.ReificationSupportApi reificationSupport() {
         return ((ReificationSupport)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).build();
      }

      default scala.reflect.api.Internals.Importer createImporter(final scala.reflect.api.Universe from0) {
         return ((Importers)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).mkImporter(from0);
      }

      default Scopes.Scope newScopeWith(final Seq elems) {
         return ((Scopes)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).newScopeWith(elems);
      }

      default Scopes.Scope enter(final Scopes.Scope scope, final Symbols.Symbol sym) {
         scope.enter(sym);
         return scope;
      }

      default Scopes.Scope unlink(final Scopes.Scope scope, final Symbols.Symbol sym) {
         scope.unlink(sym);
         return scope;
      }

      default List freeTerms(final Trees.Tree tree) {
         return tree.freeTerms();
      }

      default List freeTypes(final Trees.Tree tree) {
         return tree.freeTypes();
      }

      default Trees.Tree substituteSymbols(final Trees.Tree tree, final List from, final List to) {
         return tree.substituteSymbols(from, to);
      }

      default Trees.Tree substituteTypes(final Trees.Tree tree, final List from, final List to) {
         return tree.substituteTypes(from, to);
      }

      default Trees.Tree substituteThis(final Trees.Tree tree, final Symbols.Symbol clazz, final Function0 to) {
         return tree.substituteThis(clazz, to);
      }

      default Attachments attachments(final Trees.Tree tree) {
         return tree.attachments();
      }

      default Trees.Tree updateAttachment(final Trees.Tree tree, final Object attachment, final ClassTag evidence$1) {
         return (Trees.Tree)tree.updateAttachment(attachment, evidence$1);
      }

      default Trees.Tree removeAttachment(final Trees.Tree tree, final ClassTag evidence$2) {
         return (Trees.Tree)tree.removeAttachment(evidence$2);
      }

      default Trees.Tree setPos(final Trees.Tree tree, final Position newpos) {
         return (Trees.Tree)tree.setPos(newpos);
      }

      default Trees.Tree setType(final Trees.Tree tree, final Types.Type tp) {
         return tree.setType(tp);
      }

      default Trees.Tree defineType(final Trees.Tree tree, final Types.Type tp) {
         return tree.defineType(tp);
      }

      default Trees.Tree setSymbol(final Trees.Tree tree, final Symbols.Symbol sym) {
         return tree.setSymbol(sym);
      }

      default Trees.TypeTree setOriginal(final Trees.TypeTree tt, final Trees.Tree tree) {
         return tt.setOriginal(tree);
      }

      default void captureVariable(final Symbols.Symbol vble) {
         ((CapturedVariables)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).captureVariable(vble);
      }

      default Trees.Tree referenceCapturedVariable(final Symbols.Symbol vble) {
         return ((CapturedVariables)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).referenceCapturedVariable(vble);
      }

      default Types.Type capturedVariableType(final Symbols.Symbol vble) {
         return ((CapturedVariables)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).capturedVariableType(vble);
      }

      default Trees.ClassDef classDef(final Symbols.Symbol sym, final Trees.Template impl) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).ClassDef().apply(sym, impl);
      }

      default Trees.ModuleDef moduleDef(final Symbols.Symbol sym, final Trees.Template impl) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).ModuleDef().apply(sym, impl);
      }

      default Trees.ValDef valDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).ValDef().apply(sym, rhs);
      }

      default Trees.ValDef valDef(final Symbols.Symbol sym) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).ValDef().apply(sym);
      }

      default Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Modifiers mods, final List vparamss, final Trees.Tree rhs) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).DefDef().apply(sym, mods, vparamss, rhs);
      }

      default Trees.DefDef defDef(final Symbols.Symbol sym, final List vparamss, final Trees.Tree rhs) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).DefDef().apply(sym, vparamss, rhs);
      }

      default Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Modifiers mods, final Trees.Tree rhs) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).DefDef().apply(sym, mods, rhs);
      }

      default Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).DefDef().apply(sym, rhs);
      }

      default Trees.DefDef defDef(final Symbols.Symbol sym, final Function1 rhs) {
         Trees.DefDef$ var10000 = ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).DefDef();
         if (var10000 == null) {
            throw null;
         } else {
            Trees.DefDef$ apply_this = var10000;
            Trees.Tree apply_x$2 = (Trees.Tree)rhs.apply(sym.info().paramss());
            Trees.Modifiers apply_x$3 = apply_this.scala$reflect$internal$Trees$DefDef$$$outer().newDefDef$default$3(sym, apply_x$2);
            Names.TermName apply_x$4 = apply_this.scala$reflect$internal$Trees$DefDef$$$outer().newDefDef$default$4(sym, apply_x$2);
            List apply_x$5 = apply_this.scala$reflect$internal$Trees$DefDef$$$outer().newDefDef$default$5(sym, apply_x$2);
            List apply_x$6 = apply_this.scala$reflect$internal$Trees$DefDef$$$outer().newDefDef$default$6(sym, apply_x$2);
            Trees.Tree apply_x$7 = apply_this.scala$reflect$internal$Trees$DefDef$$$outer().newDefDef$default$7(sym, apply_x$2);
            return apply_this.scala$reflect$internal$Trees$DefDef$$$outer().newDefDef(sym, apply_x$2, apply_x$3, apply_x$4, apply_x$5, apply_x$6, apply_x$7);
         }
      }

      default Trees.TypeDef typeDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).TypeDef().apply(sym, rhs);
      }

      default Trees.TypeDef typeDef(final Symbols.Symbol sym) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).TypeDef().apply(sym);
      }

      default Trees.LabelDef labelDef(final Symbols.Symbol sym, final List params, final Trees.Tree rhs) {
         return ((Trees)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).LabelDef().apply(sym, params, rhs);
      }

      default Trees.Tree changeOwner(final Trees.Tree tree, final Symbols.Symbol prev, final Symbols.Symbol next) {
         ((SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new ChangeOwnerTraverser(prev, next)).traverse(tree);
         return tree;
      }

      default Universe.TreeGen gen() {
         return this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().treeBuild();
      }

      default boolean isFreeTerm(final Symbols.Symbol symbol) {
         return symbol.isFreeTerm();
      }

      default Symbols.FreeTermSymbol asFreeTerm(final Symbols.Symbol symbol) {
         return symbol.asFreeTerm();
      }

      default boolean isFreeType(final Symbols.Symbol symbol) {
         return symbol.isFreeType();
      }

      default Symbols.FreeTypeSymbol asFreeType(final Symbols.Symbol symbol) {
         return symbol.asFreeType();
      }

      default Symbols.TermSymbol newTermSymbol(final Symbols.Symbol symbol, final Names.TermName name, final Position pos, final long flags) {
         return symbol.newTermSymbol(name, pos, flags);
      }

      default Position newTermSymbol$default$3() {
         return ((Positions)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoPosition();
      }

      default long newTermSymbol$default$4() {
         return ((FlagSets)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoFlags();
      }

      default Tuple2 newModuleAndClassSymbol(final Symbols.Symbol symbol, final Names.Name name, final Position pos, final long flags) {
         return symbol.newModuleAndClassSymbol(name, pos, flags);
      }

      default Position newModuleAndClassSymbol$default$3() {
         return ((Positions)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoPosition();
      }

      default long newModuleAndClassSymbol$default$4() {
         return ((FlagSets)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoFlags();
      }

      default Symbols.MethodSymbol newMethodSymbol(final Symbols.Symbol symbol, final Names.TermName name, final Position pos, final long flags) {
         if (symbol == null) {
            throw null;
         } else {
            return symbol.createMethodSymbol(name, pos, 64L | flags);
         }
      }

      default Position newMethodSymbol$default$3() {
         return ((Positions)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoPosition();
      }

      default long newMethodSymbol$default$4() {
         return ((FlagSets)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoFlags();
      }

      default Symbols.TypeSymbol newTypeSymbol(final Symbols.Symbol symbol, final Names.TypeName name, final Position pos, final long flags) {
         return symbol.newTypeSymbol(name, pos, flags);
      }

      default Position newTypeSymbol$default$3() {
         return ((Positions)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoPosition();
      }

      default long newTypeSymbol$default$4() {
         return ((FlagSets)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoFlags();
      }

      default Symbols.ClassSymbol newClassSymbol(final Symbols.Symbol symbol, final Names.TypeName name, final Position pos, final long flags) {
         return symbol.newClassSymbol(name, pos, flags);
      }

      default Position newClassSymbol$default$3() {
         return ((Positions)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoPosition();
      }

      default long newClassSymbol$default$4() {
         return ((FlagSets)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoFlags();
      }

      default Symbols.FreeTermSymbol newFreeTerm(final String name, final Function0 value, final long flags, final String origin) {
         return (Symbols.FreeTermSymbol)this.reificationSupport().newFreeTerm(name, value, flags, origin);
      }

      default long newFreeTerm$default$3() {
         return ((FlagSets)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoFlags();
      }

      default String newFreeTerm$default$4() {
         return null;
      }

      default Symbols.FreeTypeSymbol newFreeType(final String name, final long flags, final String origin) {
         return (Symbols.FreeTypeSymbol)this.reificationSupport().newFreeType(name, flags, origin);
      }

      default long newFreeType$default$2() {
         return ((FlagSets)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).NoFlags();
      }

      default String newFreeType$default$3() {
         return null;
      }

      default boolean isErroneous(final Symbols.Symbol symbol) {
         return symbol.isErroneous();
      }

      default boolean isSkolem(final Symbols.Symbol symbol) {
         return symbol.isSkolem();
      }

      default Symbols.Symbol deSkolemize(final Symbols.Symbol symbol) {
         return symbol.deSkolemize();
      }

      default Symbols.Symbol initialize(final Symbols.Symbol symbol) {
         return symbol.initialize();
      }

      default Symbols.Symbol fullyInitialize(final Symbols.Symbol symbol) {
         return ((Definitions)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).definitions().fullyInitializeSymbol(symbol);
      }

      default Types.Type fullyInitialize(final Types.Type tp) {
         return ((Definitions)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).definitions().fullyInitializeType(tp);
      }

      default Scopes.Scope fullyInitialize(final Scopes.Scope scope) {
         return ((Definitions)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).definitions().fullyInitializeScope(scope);
      }

      default long flags(final Symbols.Symbol symbol) {
         return symbol.flags();
      }

      default Attachments attachments(final Symbols.Symbol symbol) {
         return symbol.attachments();
      }

      default Symbols.Symbol updateAttachment(final Symbols.Symbol symbol, final Object attachment, final ClassTag evidence$3) {
         return (Symbols.Symbol)symbol.updateAttachment(attachment, evidence$3);
      }

      default Symbols.Symbol removeAttachment(final Symbols.Symbol symbol, final ClassTag evidence$4) {
         return (Symbols.Symbol)symbol.removeAttachment(evidence$4);
      }

      default Symbols.Symbol setOwner(final Symbols.Symbol symbol, final Symbols.Symbol newowner) {
         symbol.owner_$eq(newowner);
         return symbol;
      }

      default Symbols.Symbol setInfo(final Symbols.Symbol symbol, final Types.Type tpe) {
         return symbol.setInfo(tpe);
      }

      default Symbols.Symbol setAnnotations(final Symbols.Symbol symbol, final Seq annots) {
         return symbol.setAnnotations((Seq)annots);
      }

      default Symbols.Symbol setName(final Symbols.Symbol symbol, final Names.Name name) {
         return symbol.setName(name);
      }

      default Symbols.Symbol setPrivateWithin(final Symbols.Symbol symbol, final Symbols.Symbol sym) {
         return symbol.setPrivateWithin(sym);
      }

      default Symbols.Symbol setFlag(final Symbols.Symbol symbol, final long flags) {
         return symbol.setFlag(flags);
      }

      default Symbols.Symbol resetFlag(final Symbols.Symbol symbol, final long flags) {
         return symbol.resetFlag(flags);
      }

      default Types.Type thisType(final Symbols.Symbol sym) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).ThisType().apply(sym);
      }

      default Types.Type singleType(final Types.Type pre, final Symbols.Symbol sym) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).SingleType().apply(pre, sym);
      }

      default Types.Type superType(final Types.Type thistpe, final Types.Type supertpe) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).SuperType().apply(thistpe, supertpe);
      }

      default Types.ConstantType constantType(final Constants.Constant value) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).ConstantType().apply(value);
      }

      default Types.Type typeRef(final Types.Type pre, final Symbols.Symbol sym, final List args) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).TypeRef().apply(pre, sym, args);
      }

      default Types.RefinedType refinedType(final List parents, final Scopes.Scope decls) {
         return (SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new RefinedType(parents, decls);
      }

      default Types.RefinedType refinedType(final List parents, final Scopes.Scope decls, final Symbols.Symbol clazz) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).RefinedType().apply(parents, decls, clazz);
      }

      default Types.Type refinedType(final List parents, final Symbols.Symbol owner) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).refinedType(parents, owner);
      }

      default Types.Type refinedType(final List parents, final Symbols.Symbol owner, final Scopes.Scope decls) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).RefinedType().apply(parents, decls, owner);
      }

      default Types.Type refinedType(final List parents, final Symbols.Symbol owner, final Scopes.Scope decls, final Position pos) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).refinedType(parents, owner, decls, pos);
      }

      default Types.Type intersectionType(final List tps) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).intersectionType(tps);
      }

      default Types.Type intersectionType(final List tps, final Symbols.Symbol owner) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).intersectionType(tps, owner);
      }

      default Types.ClassInfoType classInfoType(final List parents, final Scopes.Scope decls, final Symbols.Symbol typeSymbol) {
         return (SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new ClassInfoType(parents, decls, typeSymbol);
      }

      default Types.MethodType methodType(final List params, final Types.Type resultType) {
         return (SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new MethodType(params, resultType);
      }

      default Types.NullaryMethodType nullaryMethodType(final Types.Type resultType) {
         return (SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new NullaryMethodType(resultType);
      }

      default Types.PolyType polyType(final List typeParams, final Types.Type resultType) {
         return (SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new PolyType(typeParams, resultType);
      }

      default Types.ExistentialType existentialType(final List quantified, final Types.Type underlying) {
         return (SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new ExistentialType(quantified, underlying);
      }

      default Types.Type existentialAbstraction(final List tparams, final Types.Type tpe0) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).existentialAbstraction(tparams, tpe0, ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).existentialAbstraction$default$3());
      }

      default Types.AnnotatedType annotatedType(final List annotations, final Types.Type underlying) {
         return (SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new AnnotatedType(annotations, underlying);
      }

      default Types.TypeBounds typeBounds(final Types.Type lo, final Types.Type hi) {
         return ((Types)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).TypeBounds().apply(lo, hi);
      }

      default Types.BoundedWildcardType boundedWildcardType(final Types.TypeBounds bounds) {
         return (SymbolTable)this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer().new BoundedWildcardType(bounds);
      }

      default Option subpatterns(final Trees.Tree tree) {
         Option var10000 = tree.attachments().get(.MODULE$.apply(StdAttachments.SubpatternsAttachment.class));
         if (var10000 == null) {
            throw null;
         } else {
            Option map_this = var10000;
            if (map_this.isEmpty()) {
               return scala.None..MODULE$;
            } else {
               StdAttachments.SubpatternsAttachment var3 = (StdAttachments.SubpatternsAttachment)map_this.get();
               return new Some($anonfun$subpatterns$1(this, var3));
            }
         }
      }

      // $FF: synthetic method
      static Universe.MacroInternalApi.MacroDecoratorApi decorators$(final SymbolTableInternal $this) {
         return $this.decorators();
      }

      default Universe.MacroInternalApi.MacroDecoratorApi decorators() {
         return new Universe.MacroInternalApi.MacroDecoratorApi() {
            // $FF: synthetic field
            private final SymbolTableInternal $outer;

            public scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi TypeDecoratorApi(final scala.reflect.api.Types.TypeApi tp) {
               return scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi$(this, tp);
            }

            public Universe.MacroInternalApi.MacroDecoratorApi.MacroScopeDecoratorApi scopeDecorator(final Scopes.Scope scope) {
               return new Universe.MacroInternalApi.MacroDecoratorApi.MacroScopeDecoratorApi(scope);
            }

            public Universe.MacroInternalApi.MacroDecoratorApi.MacroTreeDecoratorApi treeDecorator(final Trees.Tree tree) {
               return new Universe.MacroInternalApi.MacroDecoratorApi.MacroTreeDecoratorApi(tree);
            }

            public Universe.MacroInternalApi.MacroDecoratorApi.MacroTypeTreeDecoratorApi typeTreeDecorator(final Trees.TypeTree tt) {
               return new Universe.MacroInternalApi.MacroDecoratorApi.MacroTypeTreeDecoratorApi(tt);
            }

            public Universe.MacroInternalApi.MacroDecoratorApi.MacroSymbolDecoratorApi symbolDecorator(final Symbols.Symbol symbol) {
               return new Universe.MacroInternalApi.MacroDecoratorApi.MacroSymbolDecoratorApi(symbol);
            }

            public scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi typeDecorator(final Types.Type tp) {
               return new scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi(tp);
            }

            // $FF: synthetic method
            public Universe.MacroInternalApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer() {
               return this.$outer;
            }

            // $FF: synthetic method
            public scala.reflect.api.Internals.InternalApi scala$reflect$api$Internals$InternalApi$DecoratorApi$$$outer() {
               return this.$outer;
            }

            public {
               if (<VAR_NAMELESS_ENCLOSURE> == null) {
                  throw null;
               } else {
                  this.$outer = <VAR_NAMELESS_ENCLOSURE>;
               }
            }
         };
      }

      // $FF: synthetic method
      Internals scala$reflect$internal$Internals$SymbolTableInternal$$$outer();

      // $FF: synthetic method
      static Trees.Tree $anonfun$subpatterns$2(final SymbolTableInternal $this, final Trees.Tree tree) {
         return ((Trees)$this.scala$reflect$internal$Internals$SymbolTableInternal$$$outer()).duplicateAndKeepPositions(tree);
      }

      // $FF: synthetic method
      static List $anonfun$subpatterns$1(final SymbolTableInternal $this, final StdAttachments.SubpatternsAttachment x$1) {
         List var10000 = x$1.patterns();
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               return scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var7 = (Trees.Tree)map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$subpatterns$2($this, var7), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var7 = (Trees.Tree)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$subpatterns$2($this, var7), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      }

      static void $init$(final SymbolTableInternal $this) {
      }
   }
}
