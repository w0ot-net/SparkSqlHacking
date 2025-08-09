package org.apache.spark.mllib.tree.configuration;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.tree.impurity.Entropy$;
import org.apache.spark.mllib.tree.impurity.Gini$;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.impurity.Variance$;
import scala.Enumeration;
import scala.MatchError;
import scala.collection.SetOps;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011Eg\u0001B4i\u0001UD!\"!\u0005\u0001\u0005\u0003\u0007I\u0011AA\n\u0011)\ti\u0005\u0001BA\u0002\u0013\u0005\u0011q\n\u0005\u000b\u0003;\u0002!\u0011!Q!\n\u0005U\u0001bBA8\u0001\u0011\u0005\u0011\u0011\u000f\u0005\b\u0003o\u0002A\u0011AA=\u0011)\t\t\t\u0001BA\u0002\u0013\u0005\u00111\u0011\u0005\u000b\u0003#\u0003!\u00111A\u0005\u0002\u0005M\u0005BCAM\u0001\t\u0005\t\u0015)\u0003\u0002\u0006\"9\u0011q\u0014\u0001\u0005\u0002\u0005\u0005\u0006bBAT\u0001\u0011\u0005\u0011\u0011\u0016\u0005\u000b\u0003c\u0003!\u00111A\u0005\u0002\u0005M\u0006BCA_\u0001\t\u0005\r\u0011\"\u0001\u0002@\"Q\u0011Q\u0019\u0001\u0003\u0002\u0003\u0006K!!.\t\u000f\u0005-\u0007\u0001\"\u0001\u0002N\"9\u00111\u001b\u0001\u0005\u0002\u0005U\u0007BCAo\u0001\t\u0005\r\u0011\"\u0001\u00024\"Q\u0011Q\u001d\u0001\u0003\u0002\u0004%\t!a:\t\u0015\u00055\bA!A!B\u0013\t)\fC\u0004\u0002t\u0002!\t!!4\t\u000f\u0005e\b\u0001\"\u0001\u0002|\"Q!1\u0001\u0001\u0003\u0002\u0004%\t!a-\t\u0015\t\u001d\u0001A!a\u0001\n\u0003\u0011I\u0001\u0003\u0006\u0003\u0010\u0001\u0011\t\u0011)Q\u0005\u0003kCqA!\u0006\u0001\t\u0003\ti\rC\u0004\u0003\u001c\u0001!\tA!\b\t\u0015\t\u0015\u0002A!a\u0001\n\u0003\u00119\u0003\u0003\u0006\u0003:\u0001\u0011\t\u0019!C\u0001\u0005wA!B!\u0011\u0001\u0005\u0003\u0005\u000b\u0015\u0002B\u0015\u0011\u001d\u00119\u0005\u0001C\u0001\u0005\u0013BqAa\u0014\u0001\t\u0003\u0011\t\u0006\u0003\u0006\u0003Z\u0001\u0011\t\u0019!C\u0001\u00057B!Ba\u001c\u0001\u0005\u0003\u0007I\u0011\u0001B9\u0011)\u00119\b\u0001B\u0001B\u0003&!Q\f\u0005\b\u0005{\u0002A\u0011\u0001B@\u0011\u001d\u0011)\t\u0001C\u0001\u0005\u000fC!Ba$\u0001\u0005\u0003\u0007I\u0011AAZ\u0011)\u0011\u0019\n\u0001BA\u0002\u0013\u0005!Q\u0013\u0005\u000b\u00057\u0003!\u0011!Q!\n\u0005U\u0006b\u0002BQ\u0001\u0011\u0005\u0011Q\u001a\u0005\b\u0005O\u0003A\u0011\u0001BU\u0011)\u0011\t\f\u0001BA\u0002\u0013\u0005!1\u0017\u0005\u000b\u0005{\u0003!\u00111A\u0005\u0002\t}\u0006B\u0003Bc\u0001\t\u0005\t\u0015)\u0003\u00036\"9!1\u001a\u0001\u0005\u0002\t5\u0007b\u0002Bj\u0001\u0011\u0005!Q\u001b\u0005\u000b\u0005;\u0004!\u00111A\u0005\u0002\u0005M\u0006B\u0003Bq\u0001\t\u0005\r\u0011\"\u0001\u0003d\"Q!\u0011\u001e\u0001\u0003\u0002\u0003\u0006K!!.\t\u000f\t=\b\u0001\"\u0001\u0002N\"9!Q\u001f\u0001\u0005\u0002\t]\bB\u0003B\u0000\u0001\t\u0005\r\u0011\"\u0001\u00034\"Q11\u0001\u0001\u0003\u0002\u0004%\ta!\u0002\t\u0015\r-\u0001A!A!B\u0013\u0011)\fC\u0004\u0004\u0012\u0001!\tA!4\t\u000f\r]\u0001\u0001\"\u0001\u0004\u001a!Q1\u0011\u0005\u0001\u0003\u0002\u0004%\taa\t\t\u0015\r5\u0002A!a\u0001\n\u0003\u0019y\u0003\u0003\u0006\u00046\u0001\u0011\t\u0011)Q\u0005\u0007KAqaa\u000f\u0001\t\u0003\u0019i\u0004C\u0004\u0004D\u0001!\ta!\u0012\t\u0015\r5\u0003A!a\u0001\n\u0003\t\u0019\f\u0003\u0006\u0004R\u0001\u0011\t\u0019!C\u0001\u0007'B!b!\u0017\u0001\u0005\u0003\u0005\u000b\u0015BA[\u0011\u001d\u0019y\u0006\u0001C\u0001\u0003\u001bDqa!\u001a\u0001\t\u0003\u00199\u0007\u0003\u0006\u0004p\u0001\u0011\t\u0019!C\u0001\u0005gC!ba\u001e\u0001\u0005\u0003\u0007I\u0011AB=\u0011)\u0019y\b\u0001B\u0001B\u0003&!Q\u0017\u0005\b\u0007\u000b\u0003A\u0011\u0001Bg\u0011\u001d\u0019Y\t\u0001C\u0001\u0007\u001bC1b!&\u0001\u0005\u0003\u0007I\u0011\u00018\u0004$!Y1q\u0013\u0001\u0003\u0002\u0004%\tA\\BM\u0011)\u0019i\n\u0001B\u0001B\u0003&1Q\u0005\u0005\t\u0007C\u0003A\u0011\u00018\u0004>!A1Q\u0015\u0001\u0005\u00029\u001c9\u000bC\u0004\u0004.\u0002!\taa,\t\u000f\rU\b\u0001\"\u0001\u0004$!91\u0011 \u0001\u0005\u0002\r\r\u0002bBBW\u0001\u0011\u00051Q \u0005\b\u0007[\u0003A\u0011\u0001C\u000e\u0011\u001d\t9\b\u0001C\u0001\t\u0013BqA!\"\u0001\t\u0003!)\u0006\u0003\u0005\u0005\\\u0001!\tA\u001cC/\u0011\u001d!y\u0006\u0001C\u0001\tC:q\u0001b\u001ai\u0011\u0003!IG\u0002\u0004hQ\"\u0005A1\u000e\u0005\b\u0007[3F\u0011\u0001C<\u0011\u001d!IH\u0016C\u0001\twBq\u0001\"\u001fW\t\u0003!\t\tC\u0005\u0005\bZ\u000b\n\u0011\"\u0001\u0005\n\"IAQ\u0014,\u0012\u0002\u0013\u0005A\u0011\u0012\u0005\n\t?3\u0016\u0013!C\u0001\tCC\u0011\u0002\"*W#\u0003%\t\u0001b*\t\u0013\u0011-f+%A\u0005\u0002\u0011%\u0005\"\u0003CW-F\u0005I\u0011\u0001CX\u0011%!\u0019LVI\u0001\n\u0003!I\tC\u0005\u00056Z\u000b\n\u0011\"\u0001\u00050\"IAq\u0017,\u0012\u0002\u0013\u0005A\u0011\u0018\u0005\n\t{3\u0016\u0013!C\u0001\t\u0013C\u0011\u0002b0W#\u0003%\t\u0001b,\t\u0013\u0011\u0005g+%A\u0005\u0002\u0011e\u0006\"\u0003Cb-\u0006\u0005I\u0011\u0002Cc\u0005!\u0019FO]1uK\u001eL(BA5k\u00035\u0019wN\u001c4jOV\u0014\u0018\r^5p]*\u00111\u000e\\\u0001\u0005iJ,WM\u0003\u0002n]\u0006)Q\u000e\u001c7jE*\u0011q\u000e]\u0001\u0006gB\f'o\u001b\u0006\u0003cJ\fa!\u00199bG\",'\"A:\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u00011H\u0010\u0005\u0002xu6\t\u0001PC\u0001z\u0003\u0015\u00198-\u00197b\u0013\tY\bP\u0001\u0004B]f\u0014VM\u001a\t\u0004{\u0006-ab\u0001@\u0002\b9\u0019q0!\u0002\u000e\u0005\u0005\u0005!bAA\u0002i\u00061AH]8pizJ\u0011!_\u0005\u0004\u0003\u0013A\u0018a\u00029bG.\fw-Z\u0005\u0005\u0003\u001b\tyA\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u0002\na\fA!\u00197h_V\u0011\u0011Q\u0003\t\u0005\u0003/\t)D\u0004\u0003\u0002\u001a\u0005Eb\u0002BA\u000e\u0003_qA!!\b\u0002.9!\u0011qDA\u0016\u001d\u0011\t\t#!\u000b\u000f\t\u0005\r\u0012q\u0005\b\u0004\u007f\u0006\u0015\u0012\"A:\n\u0005E\u0014\u0018BA8q\u0013\tig.\u0003\u0002lY&\u0011\u0011N[\u0005\u0004\u0003gA\u0017\u0001B!mO>LA!a\u000e\u0002:\t!\u0011\t\\4p\u0015\r\t\u0019\u0004\u001b\u0015\u0006\u0003\u0005u\u0012\u0011\n\t\u0005\u0003\u007f\t)%\u0004\u0002\u0002B)\u0019\u00111\t8\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002H\u0005\u0005#!B*j]\u000e,\u0017EAA&\u0003\u0015\td\u0006\r\u00181\u0003!\tGnZ8`I\u0015\fH\u0003BA)\u0003/\u00022a^A*\u0013\r\t)\u0006\u001f\u0002\u0005+:LG\u000fC\u0005\u0002Z\t\t\t\u00111\u0001\u0002\u0016\u0005\u0019\u0001\u0010J\u0019)\u000b\t\ti$!\u0013\u0002\u000b\u0005dwm\u001c\u0011)\u000b\r\ti$!\u0013)\u0007\r\t\u0019\u0007\u0005\u0003\u0002f\u0005-TBAA4\u0015\r\tI\u0007_\u0001\u0006E\u0016\fgn]\u0005\u0005\u0003[\n9G\u0001\u0007CK\u0006t\u0007K]8qKJ$\u00180A\u0004hKR\fEnZ8\u0015\u0005\u0005U\u0001&\u0002\u0003\u0002>\u0005%\u0003f\u0001\u0003\u0002d\u000591/\u001a;BY\u001e|G\u0003BA)\u0003wB\u0011\"!\u0017\u0006\u0003\u0003\u0005\r!!\u0006)\u000b\u0015\ti$!\u0013)\u0007\u0015\t\u0019'\u0001\u0005j[B,(/\u001b;z+\t\t)\t\u0005\u0003\u0002\b\u0006-UBAAE\u0015\r\t\tI[\u0005\u0005\u0003\u001b\u000bII\u0001\u0005J[B,(/\u001b;zQ\u00151\u0011QHA%\u00031IW\u000e];sSRLx\fJ3r)\u0011\t\t&!&\t\u0013\u0005es!!AA\u0002\u0005\u0015\u0005&B\u0004\u0002>\u0005%\u0013!C5naV\u0014\u0018\u000e^=!Q\u0015A\u0011QHA%Q\rA\u00111M\u0001\fO\u0016$\u0018*\u001c9ve&$\u0018\u0010\u0006\u0002\u0002\u0006\"*\u0011\"!\u0010\u0002J!\u001a\u0011\"a\u0019\u0002\u0017M,G/S7qkJLG/\u001f\u000b\u0005\u0003#\nY\u000bC\u0005\u0002Z)\t\t\u00111\u0001\u0002\u0006\"*!\"!\u0010\u0002J!\u001a!\"a\u0019\u0002\u00115\f\u0007\u0010R3qi\",\"!!.\u0011\u0007]\f9,C\u0002\u0002:b\u00141!\u00138uQ\u0015Y\u0011QHA%\u00031i\u0017\r\u001f#faRDw\fJ3r)\u0011\t\t&!1\t\u0013\u0005eC\"!AA\u0002\u0005U\u0006&\u0002\u0007\u0002>\u0005%\u0013!C7bq\u0012+\u0007\u000f\u001e5!Q\u0015i\u0011QHA%Q\ri\u00111M\u0001\fO\u0016$X*\u0019=EKB$\b\u000e\u0006\u0002\u00026\"*a\"!\u0010\u0002J!\u001aa\"a\u0019\u0002\u0017M,G/T1y\t\u0016\u0004H\u000f\u001b\u000b\u0005\u0003#\n9\u000eC\u0005\u0002Z=\t\t\u00111\u0001\u00026\"*q\"!\u0010\u0002J!\u001aq\"a\u0019\u0002\u00159,Xn\u00117bgN,7\u000fK\u0003\u0011\u0003{\t\t/\t\u0002\u0002d\u0006)\u0011G\f\u001a/a\u0005qa.^7DY\u0006\u001c8/Z:`I\u0015\fH\u0003BA)\u0003SD\u0011\"!\u0017\u0012\u0003\u0003\u0005\r!!.)\u000bE\ti$!9\u0002\u00179,Xn\u00117bgN,7\u000f\t\u0015\u0006%\u0005u\u0012\u0011\u001d\u0015\u0004%\u0005\r\u0014!D4fi:+Xn\u00117bgN,7\u000fK\u0003\u0014\u0003{\t\t\u000fK\u0002\u0014\u0003G\nQb]3u\u001dVl7\t\\1tg\u0016\u001cH\u0003BA)\u0003{D\u0011\"!\u0017\u0015\u0003\u0003\u0005\r!!.)\u000bQ\ti$!9)\u0007Q\t\u0019'A\u0004nCb\u0014\u0015N\\:)\u000bU\ti$!\u0013\u0002\u00175\f\u0007PQ5og~#S-\u001d\u000b\u0005\u0003#\u0012Y\u0001C\u0005\u0002ZY\t\t\u00111\u0001\u00026\"*a#!\u0010\u0002J\u0005AQ.\u0019=CS:\u001c\b\u0005K\u0003\u0018\u0003{\tI\u0005K\u0002\u0018\u0003G\n!bZ3u\u001b\u0006D()\u001b8tQ\u0015A\u0012QHA%Q\rA\u00121M\u0001\u000bg\u0016$X*\u0019=CS:\u001cH\u0003BA)\u0005?A\u0011\"!\u0017\u001a\u0003\u0003\u0005\r!!.)\u000be\ti$!\u0013)\u0007e\t\u0019'A\u000erk\u0006tG/\u001b7f\u0007\u0006d7-\u001e7bi&|gn\u0015;sCR,w-_\u000b\u0003\u0005S\u0001BAa\u000b\u000329!\u0011\u0011\u0004B\u0017\u0013\r\u0011y\u0003[\u0001\u0011#V\fg\u000e^5mKN#(/\u0019;fOfLAAa\r\u00036\t\u0001\u0012+^1oi&dWm\u0015;sCR,w-\u001f\u0006\u0004\u0005_A\u0007&\u0002\u000e\u0002>\u0005%\u0013aH9vC:$\u0018\u000e\\3DC2\u001cW\u000f\\1uS>t7\u000b\u001e:bi\u0016<\u0017p\u0018\u0013fcR!\u0011\u0011\u000bB\u001f\u0011%\tIfGA\u0001\u0002\u0004\u0011I\u0003K\u0003\u001c\u0003{\tI%\u0001\u000frk\u0006tG/\u001b7f\u0007\u0006d7-\u001e7bi&|gn\u0015;sCR,w-\u001f\u0011)\u000bq\ti$!\u0013)\u0007q\t\u0019'\u0001\u0010hKR\fV/\u00198uS2,7)\u00197dk2\fG/[8o'R\u0014\u0018\r^3hsR\u0011!\u0011\u0006\u0015\u0006;\u0005u\u0012\u0011\n\u0015\u0004;\u0005\r\u0014AH:fiF+\u0018M\u001c;jY\u0016\u001c\u0015\r\\2vY\u0006$\u0018n\u001c8TiJ\fG/Z4z)\u0011\t\tFa\u0015\t\u0013\u0005ec$!AA\u0002\t%\u0002&\u0002\u0010\u0002>\u0005%\u0003f\u0001\u0010\u0002d\u000592-\u0019;fO>\u0014\u0018nY1m\r\u0016\fG/\u001e:fg&sgm\\\u000b\u0003\u0005;\u0002\u0002Ba\u0018\u0003h\u0005U\u0016Q\u0017\b\u0005\u0005C\u0012\u0019\u0007\u0005\u0002\u0000q&\u0019!Q\r=\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011IGa\u001b\u0003\u00075\u000b\u0007OC\u0002\u0003faDSaHA\u001f\u0003\u0013\n1dY1uK\u001e|'/[2bY\u001a+\u0017\r^;sKNLeNZ8`I\u0015\fH\u0003BA)\u0005gB\u0011\"!\u0017!\u0003\u0003\u0005\rA!\u0018)\u000b\u0001\ni$!\u0013\u00021\r\fG/Z4pe&\u001c\u0017\r\u001c$fCR,(/Z:J]\u001a|\u0007\u0005K\u0003\"\u0003{\tI\u0005K\u0002\"\u0003G\n!dZ3u\u0007\u0006$XmZ8sS\u000e\fGNR3biV\u0014Xm]%oM>$\"A!\u0018)\u000b\t\ni$!\u0013)\u0007\t\n\u0019'\u0001\u000etKR\u001c\u0015\r^3h_JL7-\u00197GK\u0006$XO]3t\u0013:4w\u000e\u0006\u0003\u0002R\t%\u0005\"CA-G\u0005\u0005\t\u0019\u0001B/Q\u0015\u0019\u0013QHA%Q\r\u0019\u00131M\u0001\u0014[&t\u0017J\\:uC:\u001cWm\u001d)fe:{G-\u001a\u0015\u0006I\u0005u\u0012\u0011]\u0001\u0018[&t\u0017J\\:uC:\u001cWm\u001d)fe:{G-Z0%KF$B!!\u0015\u0003\u0018\"I\u0011\u0011L\u0013\u0002\u0002\u0003\u0007\u0011Q\u0017\u0015\u0006K\u0005u\u0012\u0011]\u0001\u0015[&t\u0017J\\:uC:\u001cWm\u001d)fe:{G-\u001a\u0011)\u000b\u0019\ni$!9)\u0007\u0019\n\u0019'\u0001\fhKRl\u0015N\\%ogR\fgnY3t!\u0016\u0014hj\u001c3fQ\u00159\u0013QHAqQ\r9\u00131M\u0001\u0017g\u0016$X*\u001b8J]N$\u0018M\\2fgB+'OT8eKR!\u0011\u0011\u000bBV\u0011%\tI\u0006KA\u0001\u0002\u0004\t)\fK\u0003)\u0003{\t\t\u000fK\u0002)\u0003G\n1\"\\5o\u0013:4wnR1j]V\u0011!Q\u0017\t\u0004o\n]\u0016b\u0001B]q\n1Ai\\;cY\u0016DS!KA\u001f\u0003C\fq\"\\5o\u0013:4wnR1j]~#S-\u001d\u000b\u0005\u0003#\u0012\t\rC\u0005\u0002Z)\n\t\u00111\u0001\u00036\"*!&!\u0010\u0002b\u0006aQ.\u001b8J]\u001a|w)Y5oA!*1&!\u0010\u0002b\"\u001a1&a\u0019\u0002\u001d\u001d,G/T5o\u0013:4wnR1j]R\u0011!Q\u0017\u0015\u0006Y\u0005u\u0012\u0011\u001d\u0015\u0004Y\u0005\r\u0014AD:fi6Kg.\u00138g_\u001e\u000b\u0017N\u001c\u000b\u0005\u0003#\u00129\u000eC\u0005\u0002Z5\n\t\u00111\u0001\u00036\"*Q&!\u0010\u0002b\"\u001aQ&a\u0019\u0002\u001b5\f\u00070T3n_JL\u0018J\\'CQ\u0015q\u0013QHA%\u0003Ei\u0017\r_'f[>\u0014\u00180\u00138N\u0005~#S-\u001d\u000b\u0005\u0003#\u0012)\u000fC\u0005\u0002Z=\n\t\u00111\u0001\u00026\"*q&!\u0010\u0002J\u0005qQ.\u0019=NK6|'/_%o\u001b\n\u0003\u0003&\u0002\u0019\u0002>\u0005%\u0003f\u0001\u0019\u0002d\u0005\u0001r-\u001a;NCblU-\\8ss&sWJ\u0011\u0015\u0006c\u0005u\u0012\u0011\n\u0015\u0004c\u0005\r\u0014\u0001E:fi6\u000b\u00070T3n_JL\u0018J\\'C)\u0011\t\tF!?\t\u0013\u0005e#'!AA\u0002\u0005U\u0006&\u0002\u001a\u0002>\u0005%\u0003f\u0001\u001a\u0002d\u0005y1/\u001e2tC6\u0004H.\u001b8h%\u0006$X\rK\u00034\u0003{\t\t/A\ntk\n\u001c\u0018-\u001c9mS:<'+\u0019;f?\u0012*\u0017\u000f\u0006\u0003\u0002R\r\u001d\u0001\"CA-i\u0005\u0005\t\u0019\u0001B[Q\u0015!\u0014QHAq\u0003A\u0019XOY:b[Bd\u0017N\\4SCR,\u0007\u0005K\u00036\u0003{\t\t\u000fK\u00026\u0003G\n!cZ3u'V\u00147/Y7qY&twMU1uK\"*a'!\u0010\u0002b\"\u001aa'a\u0019\u0002%M,GoU;cg\u0006l\u0007\u000f\\5oOJ\u000bG/\u001a\u000b\u0005\u0003#\u001aY\u0002C\u0005\u0002Z]\n\t\u00111\u0001\u00036\"*q'!\u0010\u0002b\"\u001aq'a\u0019\u0002\u001dU\u001cXMT8eK&#7)Y2iKV\u00111Q\u0005\t\u0004o\u000e\u001d\u0012bAB\u0015q\n9!i\\8mK\u0006t\u0007&\u0002\u001d\u0002>\u0005\u0005\u0018AE;tK:{G-Z%e\u0007\u0006\u001c\u0007.Z0%KF$B!!\u0015\u00042!I\u0011\u0011L\u001d\u0002\u0002\u0003\u00071Q\u0005\u0015\u0006s\u0005u\u0012\u0011]\u0001\u0010kN,gj\u001c3f\u0013\u0012\u001c\u0015m\u00195fA!*!(!\u0010\u0002b\"\u001a!(a\u0019\u0002#\u001d,G/V:f\u001d>$W-\u00133DC\u000eDW\r\u0006\u0002\u0004&!*1(!\u0010\u0002b\"\u001a1(a\u0019\u0002#M,G/V:f\u001d>$W-\u00133DC\u000eDW\r\u0006\u0003\u0002R\r\u001d\u0003\"CA-y\u0005\u0005\t\u0019AB\u0013Q\u0015a\u0014QHAqQ\ra\u00141M\u0001\u0013G\",7m\u001b9pS:$\u0018J\u001c;feZ\fG\u000eK\u0003>\u0003{\t\t/\u0001\fdQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006dw\fJ3r)\u0011\t\tf!\u0016\t\u0013\u0005ec(!AA\u0002\u0005U\u0006&\u0002 \u0002>\u0005\u0005\u0018aE2iK\u000e\\\u0007o\\5oi&sG/\u001a:wC2\u0004\u0003&B \u0002>\u0005\u0005\bfA \u0002d\u0005)r-\u001a;DQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006d\u0007&\u0002!\u0002>\u0005\u0005\bf\u0001!\u0002d\u0005)2/\u001a;DQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006dG\u0003BA)\u0007SB\u0011\"!\u0017B\u0003\u0003\u0005\r!!.)\u000b\u0005\u000bi$!9)\u0007\u0005\u000b\u0019'\u0001\rnS:<V-[4ii\u001a\u0013\u0018m\u0019;j_:\u0004VM\u001d(pI\u0016DSAQA\u001f\u0007g\n#a!\u001e\u0002\u000bMr\u0003G\f\u0019\u000295LgnV3jO\"$hI]1di&|g\u000eU3s\u001d>$Wm\u0018\u0013fcR!\u0011\u0011KB>\u0011%\tIfQA\u0001\u0002\u0004\u0011)\fK\u0003D\u0003{\u0019\u0019(A\rnS:<V-[4ii\u001a\u0013\u0018m\u0019;j_:\u0004VM\u001d(pI\u0016\u0004\u0003&\u0002#\u0002>\rM\u0004f\u0001#\u0002d\u0005Yr-\u001a;NS:<V-[4ii\u001a\u0013\u0018m\u0019;j_:\u0004VM\u001d(pI\u0016DS!RA\u001f\u0007gB3!RA2\u0003m\u0019X\r^'j]^+\u0017n\u001a5u\rJ\f7\r^5p]B+'OT8eKR!\u0011\u0011KBH\u0011%\tIFRA\u0001\u0002\u0004\u0011)\fK\u0003G\u0003{\u0019\u0019\bK\u0002G\u0003G\n\u0011BY8piN$(/\u00199\u0002\u001b\t|w\u000e^:ue\u0006\u0004x\fJ3r)\u0011\t\tfa'\t\u0013\u0005e\u0003*!AA\u0002\r\u0015\u0012A\u00032p_R\u001cHO]1qA!\u001a\u0011*a\u0019\u0002\u0019\u001d,GOQ8piN$(/\u00199)\u0007)\u000b\u0019'\u0001\u0007tKR\u0014un\u001c;tiJ\f\u0007\u000f\u0006\u0003\u0002R\r%\u0006\"CA-\u0017\u0006\u0005\t\u0019AB\u0013Q\rY\u00151M\u0001\u0007y%t\u0017\u000e\u001e \u0015A\rE6QWB]\u0007{\u001b\tm!2\u0004J\u000e57\u0011[Bk\u00073\u001cin!9\u0004f\u000e%8Q\u001e\t\u0004\u0007g\u0003Q\"\u00015\t\u000f\u0005EA\n1\u0001\u0002\u0016!21QWA\u001f\u0003\u0013Bq!!!M\u0001\u0004\t)\t\u000b\u0004\u0004:\u0006u\u0012\u0011\n\u0005\b\u0003cc\u0005\u0019AA[Q\u0019\u0019i,!\u0010\u0002J!I\u0011Q\u001c'\u0011\u0002\u0003\u0007\u0011Q\u0017\u0015\u0007\u0007\u0003\fi$!9\t\u0013\t\rA\n%AA\u0002\u0005U\u0006FBBc\u0003{\tI\u0005C\u0005\u0003&1\u0003\n\u00111\u0001\u0003*!21\u0011ZA\u001f\u0003\u0013B\u0011B!\u0017M!\u0003\u0005\rA!\u0018)\r\r5\u0017QHA%\u0011%\u0011y\t\u0014I\u0001\u0002\u0004\t)\f\u000b\u0004\u0004R\u0006u\u0012\u0011\u001d\u0005\n\u0005cc\u0005\u0013!a\u0001\u0005kCca!6\u0002>\u0005\u0005\b\"\u0003Bo\u0019B\u0005\t\u0019AA[Q\u0019\u0019I.!\u0010\u0002J!I!q '\u0011\u0002\u0003\u0007!Q\u0017\u0015\u0007\u0007;\fi$!9\t\u0013\r\u0005B\n%AA\u0002\r\u0015\u0002FBBq\u0003{\t\t\u000fC\u0005\u0004N1\u0003\n\u00111\u0001\u00026\"21Q]A\u001f\u0003CD\u0011ba\u001cM!\u0003\u0005\rA!.)\r\r%\u0018QHB:\u0011%\u0019)\n\u0014I\u0001\u0002\u0004\u0019)\u0003K\u0003M\u0003{\u0019\t0\t\u0002\u0004t\u0006)\u0011GL\u001a/a\u0005Q\u0012n]'vYRL7\r\\1tg\u000ec\u0017m]:jM&\u001c\u0017\r^5p]\"*Q*!\u0010\u0002b\u0006\u0019\u0013n]'vYRL7\r\\1tg^KG\u000f[\"bi\u0016<wN]5dC24U-\u0019;ve\u0016\u001c\b&\u0002(\u0002>\u0005\u0005H\u0003HBY\u0007\u007f$\t\u0001b\u0001\u0005\u0006\u0011\u001dA\u0011\u0002C\u0006\t\u001b!y\u0001\"\u0005\u0005\u0014\u0011UAq\u0003\u0005\b\u0003#y\u0005\u0019AA\u000b\u0011\u001d\t\ti\u0014a\u0001\u0003\u000bCq!!-P\u0001\u0004\t)\fC\u0004\u0002^>\u0003\r!!.\t\u000f\t\rq\n1\u0001\u00026\"9!QE(A\u0002\t%\u0002b\u0002B-\u001f\u0002\u0007!Q\f\u0005\b\u0005\u001f{\u0005\u0019AA[\u0011\u001d\u0011\tl\u0014a\u0001\u0005kCqA!8P\u0001\u0004\t)\fC\u0004\u0003\u0000>\u0003\rA!.\t\u000f\r\u0005r\n1\u0001\u0004&!91QJ(A\u0002\u0005U\u0006&B(\u0002>\u0005%CCDBY\t;!y\u0002\"\t\u0005$\u0011\u0015Bq\u0005\u0005\b\u0003#\u0001\u0006\u0019AA\u000b\u0011\u001d\t\t\t\u0015a\u0001\u0003\u000bCq!!-Q\u0001\u0004\t)\fC\u0004\u0002^B\u0003\r!!.\t\u000f\t\r\u0001\u000b1\u0001\u00026\"9!\u0011\f)A\u0002\u0011%\u0002\u0003\u0003C\u0016\tk!9\u0004b\u000e\u000e\u0005\u00115\"\u0002\u0002C\u0018\tc\tA!\u001e;jY*\u0011A1G\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003j\u00115\u0002\u0003\u0002C\u001d\t\u007fi!\u0001b\u000f\u000b\t\u0011uB\u0011G\u0001\u0005Y\u0006tw-\u0003\u0003\u0005B\u0011m\"aB%oi\u0016<WM\u001d\u0015\u0006!\u0006uBQI\u0011\u0003\t\u000f\nQ!\r\u00182]A\"B!!\u0015\u0005L!9\u0011\u0011C)A\u0002\u00115\u0003\u0003\u0002B0\t\u001fJA\u0001\"\u0015\u0003l\t11\u000b\u001e:j]\u001eDS!UA\u001f\u0003C$B!!\u0015\u0005X!9!\u0011\f*A\u0002\u0011%\u0002&\u0002*\u0002>\u0005\u0005\u0018aC1tg\u0016\u0014HOV1mS\u0012$\"!!\u0015\u0002\t\r|\u0007/_\u000b\u0003\u0007cCS\u0001VA\u001f\u0003CDS\u0001AA\u001f\u0003\u0013\n\u0001b\u0015;sCR,w-\u001f\t\u0004\u0007g36\u0003\u0002,w\t[\u0002B\u0001b\u001c\u0005v5\u0011A\u0011\u000f\u0006\u0005\tg\"\t$\u0001\u0002j_&!\u0011Q\u0002C9)\t!I'A\beK\u001a\fW\u000f\u001c;TiJ\fG/Z4z)\u0011\u0019\t\f\" \t\u000f\u0005E\u0001\f1\u0001\u0005N!*\u0001,!\u0010\u0002bR!1\u0011\u0017CB\u0011\u001d\t\t\"\u0017a\u0001\u0003+AS!WA\u001f\u0007c\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"TC\u0001CFU\u0011\t)\f\"$,\u0005\u0011=\u0005\u0003\u0002CI\t3k!\u0001b%\u000b\t\u0011UEqS\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u0011y\u0013\u0011!Y\nb%\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\u0011\r&\u0006\u0002B\u0015\t\u001b\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:TC\u0001CUU\u0011\u0011i\u0006\"$\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00139\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%sU\u0011A\u0011\u0017\u0016\u0005\u0005k#i)\u0001\u000f%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%\r\u0019\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132c\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u0012TC\u0001C^U\u0011\u0019)\u0003\"$\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132g\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\"\u0014\u0001\b\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'N\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\t\u000f\u0004B\u0001\"\u000f\u0005J&!A1\u001aC\u001e\u0005\u0019y%M[3di\"*a+!\u0010\u0002b\"*Q+!\u0010\u0002b\u0002"
)
public class Strategy implements Serializable {
   private Enumeration.Value algo;
   private Impurity impurity;
   private int maxDepth;
   private int numClasses;
   private int maxBins;
   private Enumeration.Value quantileCalculationStrategy;
   private Map categoricalFeaturesInfo;
   private int minInstancesPerNode;
   private double minInfoGain;
   private int maxMemoryInMB;
   private double subsamplingRate;
   private boolean useNodeIdCache;
   private int checkpointInterval;
   private double minWeightFractionPerNode;
   private boolean bootstrap;

   public static boolean $lessinit$greater$default$15() {
      return Strategy$.MODULE$.$lessinit$greater$default$15();
   }

   public static double $lessinit$greater$default$14() {
      return Strategy$.MODULE$.$lessinit$greater$default$14();
   }

   public static int $lessinit$greater$default$13() {
      return Strategy$.MODULE$.$lessinit$greater$default$13();
   }

   public static boolean $lessinit$greater$default$12() {
      return Strategy$.MODULE$.$lessinit$greater$default$12();
   }

   public static double $lessinit$greater$default$11() {
      return Strategy$.MODULE$.$lessinit$greater$default$11();
   }

   public static int $lessinit$greater$default$10() {
      return Strategy$.MODULE$.$lessinit$greater$default$10();
   }

   public static double $lessinit$greater$default$9() {
      return Strategy$.MODULE$.$lessinit$greater$default$9();
   }

   public static int $lessinit$greater$default$8() {
      return Strategy$.MODULE$.$lessinit$greater$default$8();
   }

   public static Map $lessinit$greater$default$7() {
      return Strategy$.MODULE$.$lessinit$greater$default$7();
   }

   public static Enumeration.Value $lessinit$greater$default$6() {
      return Strategy$.MODULE$.$lessinit$greater$default$6();
   }

   public static int $lessinit$greater$default$5() {
      return Strategy$.MODULE$.$lessinit$greater$default$5();
   }

   public static int $lessinit$greater$default$4() {
      return Strategy$.MODULE$.$lessinit$greater$default$4();
   }

   public static Strategy defaultStrategy(final Enumeration.Value algo) {
      return Strategy$.MODULE$.defaultStrategy(algo);
   }

   public static Strategy defaultStrategy(final String algo) {
      return Strategy$.MODULE$.defaultStrategy(algo);
   }

   public Enumeration.Value algo() {
      return this.algo;
   }

   public void algo_$eq(final Enumeration.Value x$1) {
      this.algo = x$1;
   }

   public Impurity impurity() {
      return this.impurity;
   }

   public void impurity_$eq(final Impurity x$1) {
      this.impurity = x$1;
   }

   public int maxDepth() {
      return this.maxDepth;
   }

   public void maxDepth_$eq(final int x$1) {
      this.maxDepth = x$1;
   }

   public int numClasses() {
      return this.numClasses;
   }

   public void numClasses_$eq(final int x$1) {
      this.numClasses = x$1;
   }

   public int maxBins() {
      return this.maxBins;
   }

   public void maxBins_$eq(final int x$1) {
      this.maxBins = x$1;
   }

   public Enumeration.Value quantileCalculationStrategy() {
      return this.quantileCalculationStrategy;
   }

   public void quantileCalculationStrategy_$eq(final Enumeration.Value x$1) {
      this.quantileCalculationStrategy = x$1;
   }

   public Map categoricalFeaturesInfo() {
      return this.categoricalFeaturesInfo;
   }

   public void categoricalFeaturesInfo_$eq(final Map x$1) {
      this.categoricalFeaturesInfo = x$1;
   }

   public int minInstancesPerNode() {
      return this.minInstancesPerNode;
   }

   public void minInstancesPerNode_$eq(final int x$1) {
      this.minInstancesPerNode = x$1;
   }

   public double minInfoGain() {
      return this.minInfoGain;
   }

   public void minInfoGain_$eq(final double x$1) {
      this.minInfoGain = x$1;
   }

   public int maxMemoryInMB() {
      return this.maxMemoryInMB;
   }

   public void maxMemoryInMB_$eq(final int x$1) {
      this.maxMemoryInMB = x$1;
   }

   public double subsamplingRate() {
      return this.subsamplingRate;
   }

   public void subsamplingRate_$eq(final double x$1) {
      this.subsamplingRate = x$1;
   }

   public boolean useNodeIdCache() {
      return this.useNodeIdCache;
   }

   public void useNodeIdCache_$eq(final boolean x$1) {
      this.useNodeIdCache = x$1;
   }

   public int checkpointInterval() {
      return this.checkpointInterval;
   }

   public void checkpointInterval_$eq(final int x$1) {
      this.checkpointInterval = x$1;
   }

   public double minWeightFractionPerNode() {
      return this.minWeightFractionPerNode;
   }

   public void minWeightFractionPerNode_$eq(final double x$1) {
      this.minWeightFractionPerNode = x$1;
   }

   public boolean bootstrap() {
      return this.bootstrap;
   }

   public void bootstrap_$eq(final boolean x$1) {
      this.bootstrap = x$1;
   }

   public boolean isMulticlassClassification() {
      boolean var2;
      label18: {
         Enumeration.Value var10000 = this.algo();
         Enumeration.Value var1 = Algo$.MODULE$.Classification();
         if (var10000 == null) {
            if (var1 != null) {
               break label18;
            }
         } else if (!var10000.equals(var1)) {
            break label18;
         }

         if (this.numClasses() > 2) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   public boolean isMulticlassWithCategoricalFeatures() {
      return this.isMulticlassClassification() && this.categoricalFeaturesInfo().size() > 0;
   }

   public void setAlgo(final String algo) {
      switch (algo == null ? 0 : algo.hashCode()) {
         case -880190367:
            if (!"Regression".equals(algo)) {
               throw new MatchError(algo);
            }

            this.setAlgo(Algo$.MODULE$.Regression());
            break;
         case -619642874:
            if ("Classification".equals(algo)) {
               this.setAlgo(Algo$.MODULE$.Classification());
               break;
            }

            throw new MatchError(algo);
         default:
            throw new MatchError(algo);
      }

   }

   public void setCategoricalFeaturesInfo(final java.util.Map categoricalFeaturesInfo) {
      this.categoricalFeaturesInfo_$eq(.MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public void assertValid() {
      label81: {
         label82: {
            Enumeration.Value var2 = this.algo();
            Enumeration.Value var10000 = Algo$.MODULE$.Classification();
            if (var10000 == null) {
               if (var2 == null) {
                  break label82;
               }
            } else if (var10000.equals(var2)) {
               break label82;
            }

            var10000 = Algo$.MODULE$.Regression();
            if (var10000 == null) {
               if (var2 != null) {
                  throw new IllegalArgumentException("DecisionTree Strategy given invalid algo parameter: " + this.algo() + ".  Valid settings are: Classification, Regression.");
               }
            } else if (!var10000.equals(var2)) {
               throw new IllegalArgumentException("DecisionTree Strategy given invalid algo parameter: " + this.algo() + ".  Valid settings are: Classification, Regression.");
            }

            boolean var10;
            label64: {
               label63: {
                  var7 = scala.Predef..MODULE$;
                  Impurity var10001 = this.impurity();
                  Variance$ var5 = Variance$.MODULE$;
                  if (var10001 == null) {
                     if (var5 == null) {
                        break label63;
                     }
                  } else if (var10001.equals(var5)) {
                     break label63;
                  }

                  var10 = false;
                  break label64;
               }

               var10 = true;
            }

            var7.require(var10, () -> "DecisionTree Strategy given invalid impurity for Regression: " + this.impurity() + ".  Valid settings: Variance");
            BoxedUnit var8 = BoxedUnit.UNIT;
            break label81;
         }

         scala.Predef..MODULE$.require(this.numClasses() >= 2, () -> "DecisionTree Strategy for Classification must have numClasses >= 2, but numClasses = " + this.numClasses() + ".");
         scala.Predef..MODULE$.require(((SetOps)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Impurity[]{Gini$.MODULE$, Entropy$.MODULE$}))).contains(this.impurity()), () -> "DecisionTree Strategy given invalid impurity for Classification: " + this.impurity() + ".  Valid settings: Gini, Entropy");
         BoxedUnit var9 = BoxedUnit.UNIT;
      }

      scala.Predef..MODULE$.require(this.maxDepth() >= 0, () -> "DecisionTree Strategy given invalid maxDepth parameter: " + this.maxDepth() + ".  Valid values are integers >= 0.");
      scala.Predef..MODULE$.require(this.maxBins() >= 2, () -> "DecisionTree Strategy given invalid maxBins parameter: " + this.maxBins() + ".  Valid values are integers >= 2.");
      scala.Predef..MODULE$.require(this.minInstancesPerNode() >= 1, () -> "DecisionTree Strategy requires minInstancesPerNode >= 1 but was given " + this.minInstancesPerNode());
      scala.Predef..MODULE$.require(this.maxMemoryInMB() <= 10240, () -> "DecisionTree Strategy requires maxMemoryInMB <= 10240, but was given " + this.maxMemoryInMB());
      scala.Predef..MODULE$.require(this.subsamplingRate() > (double)0 && this.subsamplingRate() <= (double)1, () -> "DecisionTree Strategy requires subsamplingRate <=1 and >0, but was given " + this.subsamplingRate());
   }

   public Strategy copy() {
      return new Strategy(this.algo(), this.impurity(), this.maxDepth(), this.numClasses(), this.maxBins(), this.quantileCalculationStrategy(), this.categoricalFeaturesInfo(), this.minInstancesPerNode(), this.minInfoGain(), this.maxMemoryInMB(), this.subsamplingRate(), this.useNodeIdCache(), this.checkpointInterval(), this.minWeightFractionPerNode(), Strategy$.MODULE$.$lessinit$greater$default$15());
   }

   public Enumeration.Value getAlgo() {
      return this.algo();
   }

   public boolean getBootstrap() {
      return this.bootstrap();
   }

   public Map getCategoricalFeaturesInfo() {
      return this.categoricalFeaturesInfo();
   }

   public int getCheckpointInterval() {
      return this.checkpointInterval();
   }

   public Impurity getImpurity() {
      return this.impurity();
   }

   public int getMaxBins() {
      return this.maxBins();
   }

   public int getMaxDepth() {
      return this.maxDepth();
   }

   public int getMaxMemoryInMB() {
      return this.maxMemoryInMB();
   }

   public double getMinInfoGain() {
      return this.minInfoGain();
   }

   public int getMinInstancesPerNode() {
      return this.minInstancesPerNode();
   }

   public double getMinWeightFractionPerNode() {
      return this.minWeightFractionPerNode();
   }

   public int getNumClasses() {
      return this.numClasses();
   }

   public Enumeration.Value getQuantileCalculationStrategy() {
      return this.quantileCalculationStrategy();
   }

   public double getSubsamplingRate() {
      return this.subsamplingRate();
   }

   public boolean getUseNodeIdCache() {
      return this.useNodeIdCache();
   }

   public void setAlgo(final Enumeration.Value x$1) {
      this.algo_$eq(x$1);
   }

   public void setBootstrap(final boolean x$1) {
      this.bootstrap_$eq(x$1);
   }

   public void setCategoricalFeaturesInfo(final Map x$1) {
      this.categoricalFeaturesInfo_$eq(x$1);
   }

   public void setCheckpointInterval(final int x$1) {
      this.checkpointInterval_$eq(x$1);
   }

   public void setImpurity(final Impurity x$1) {
      this.impurity_$eq(x$1);
   }

   public void setMaxBins(final int x$1) {
      this.maxBins_$eq(x$1);
   }

   public void setMaxDepth(final int x$1) {
      this.maxDepth_$eq(x$1);
   }

   public void setMaxMemoryInMB(final int x$1) {
      this.maxMemoryInMB_$eq(x$1);
   }

   public void setMinInfoGain(final double x$1) {
      this.minInfoGain_$eq(x$1);
   }

   public void setMinInstancesPerNode(final int x$1) {
      this.minInstancesPerNode_$eq(x$1);
   }

   public void setMinWeightFractionPerNode(final double x$1) {
      this.minWeightFractionPerNode_$eq(x$1);
   }

   public void setNumClasses(final int x$1) {
      this.numClasses_$eq(x$1);
   }

   public void setQuantileCalculationStrategy(final Enumeration.Value x$1) {
      this.quantileCalculationStrategy_$eq(x$1);
   }

   public void setSubsamplingRate(final double x$1) {
      this.subsamplingRate_$eq(x$1);
   }

   public void setUseNodeIdCache(final boolean x$1) {
      this.useNodeIdCache_$eq(x$1);
   }

   public Strategy(final Enumeration.Value algo, final Impurity impurity, final int maxDepth, final int numClasses, final int maxBins, final Enumeration.Value quantileCalculationStrategy, final Map categoricalFeaturesInfo, final int minInstancesPerNode, final double minInfoGain, final int maxMemoryInMB, final double subsamplingRate, final boolean useNodeIdCache, final int checkpointInterval, final double minWeightFractionPerNode, final boolean bootstrap) {
      this.algo = algo;
      this.impurity = impurity;
      this.maxDepth = maxDepth;
      this.numClasses = numClasses;
      this.maxBins = maxBins;
      this.quantileCalculationStrategy = quantileCalculationStrategy;
      this.categoricalFeaturesInfo = categoricalFeaturesInfo;
      this.minInstancesPerNode = minInstancesPerNode;
      this.minInfoGain = minInfoGain;
      this.maxMemoryInMB = maxMemoryInMB;
      this.subsamplingRate = subsamplingRate;
      this.useNodeIdCache = useNodeIdCache;
      this.checkpointInterval = checkpointInterval;
      this.minWeightFractionPerNode = minWeightFractionPerNode;
      this.bootstrap = bootstrap;
      super();
   }

   public Strategy(final Enumeration.Value algo, final Impurity impurity, final int maxDepth, final int numClasses, final int maxBins, final Enumeration.Value quantileCalculationStrategy, final Map categoricalFeaturesInfo, final int minInstancesPerNode, final double minInfoGain, final int maxMemoryInMB, final double subsamplingRate, final boolean useNodeIdCache, final int checkpointInterval) {
      this(algo, impurity, maxDepth, numClasses, maxBins, quantileCalculationStrategy, categoricalFeaturesInfo, minInstancesPerNode, minInfoGain, maxMemoryInMB, subsamplingRate, useNodeIdCache, checkpointInterval, (double)0.0F, Strategy$.MODULE$.$lessinit$greater$default$15());
   }

   public Strategy(final Enumeration.Value algo, final Impurity impurity, final int maxDepth, final int numClasses, final int maxBins, final java.util.Map categoricalFeaturesInfo) {
      Enumeration.Value x$6 = QuantileStrategy$.MODULE$.Sort();
      Map x$7 = .MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl());
      double x$8 = (double)0.0F;
      int x$9 = Strategy$.MODULE$.$lessinit$greater$default$8();
      double x$10 = Strategy$.MODULE$.$lessinit$greater$default$9();
      int x$11 = Strategy$.MODULE$.$lessinit$greater$default$10();
      double x$12 = Strategy$.MODULE$.$lessinit$greater$default$11();
      boolean x$13 = Strategy$.MODULE$.$lessinit$greater$default$12();
      int x$14 = Strategy$.MODULE$.$lessinit$greater$default$13();
      boolean x$15 = Strategy$.MODULE$.$lessinit$greater$default$15();
      this(algo, impurity, maxDepth, numClasses, maxBins, x$6, x$7, x$9, x$10, x$11, x$12, x$13, x$14, (double)0.0F, x$15);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
