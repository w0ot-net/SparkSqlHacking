package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Vector;
import breeze.linalg.ZippedValues;
import breeze.math.Field;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0005ea\u0002\u0016,!\u0003\r\tA\r\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u000b\u0002!\u0019A\u0012\u0004\u0005s\u0002\u0001%\u0010\u0003\u0006\u0002d\r\u0011)\u001a!C\u0001\u0003KB!\"!\u001b\u0004\u0005#\u0005\u000b\u0011BA4\u0011)\tYg\u0001BK\u0002\u0013\u0005\u0011Q\u000e\u0005\u000b\u0003c\u001a!\u0011#Q\u0001\n\u0005=\u0004bBA:\u0007\u0011\u0005\u0011Q\u000f\u0005\b\u0003\u007f\u001aA\u0011AAA\u0011%\tiiAA\u0001\n\u0003\ty\tC\u0005\u0002J\u000e\t\n\u0011\"\u0001\u0002L\"I!1B\u0002\u0012\u0002\u0013\u0005!Q\u0002\u0005\n\u0005w\u0019\u0011\u0011!C!\u0005{A\u0011Ba\u0014\u0004\u0003\u0003%\tA!\u0015\t\u0013\te3!!A\u0005\u0002\tm\u0003\"\u0003B1\u0007\u0005\u0005I\u0011\tB2\u0011%\u0011\thAA\u0001\n\u0003\u0011\u0019\bC\u0005\u0003~\r\t\t\u0011\"\u0011\u0003\u0000!I!1Q\u0002\u0002\u0002\u0013\u0005#Q\u0011\u0005\n\u0005\u000f\u001b\u0011\u0011!C!\u0005\u0013C\u0011Ba#\u0004\u0003\u0003%\tE!$\b\u0013\tE\u0005!!A\t\u0002\tMe\u0001C=\u0001\u0003\u0003E\tA!&\t\u000f\u0005Mt\u0003\"\u0001\u0003\"\"I!qQ\f\u0002\u0002\u0013\u0015#\u0011\u0012\u0005\n\u0005G;\u0012\u0011!CA\u0005KC\u0011Ba8\u0018\u0003\u0003%\tI!9\t\u000f\r\u001d\u0002\u0001b\u0001\u0004*!91q\n\u0001\u0005\u0004\rE\u0003bBB6\u0001\u0011\r1Q\u000e\u0005\b\u0007\u000b\u0003A1ABD\u0011\u001d\u0019i\n\u0001C\u0002\u0007?Cqa!/\u0001\t\u0007\u0019Y\fC\u0004\u0004L\u0002!\u0019a!4\t\u000f\ru\u0007\u0001b\u0001\u0004`\"91q \u0001\u0005\u0004\u0011\u0005\u0001b\u0002C\f\u0001\u0011\rA\u0011\u0004\u0005\b\t[\u0001A1\u0001C\u0018\u0011\u001d!Y\u0004\u0001C\u0002\t{Aq\u0001\"\u0017\u0001\t\u0007!Y\u0006C\u0004\u0005n\u0001!\u0019\u0001b\u001c\u0003#Y+7\r^8s?\u001e+g.\u001a:jG>\u00038O\u0003\u0002-[\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0003]=\na\u0001\\5oC2<'\"\u0001\u0019\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019B\u0001A\u001a:{A\u0011AgN\u0007\u0002k)\ta'A\u0003tG\u0006d\u0017-\u0003\u00029k\t1\u0011I\\=SK\u001a\u0004\"AO\u001e\u000e\u0003-J!\u0001P\u0016\u0003\u0015\u001d+g.\u001a:jG>\u00038\u000f\u0005\u0002;}%\u0011qh\u000b\u0002\u0014-\u0016\u001cGo\u001c:`)J\fg/\u001a:tC2|\u0005o]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\t\u0003\"\u0001N\"\n\u0005\u0011+$\u0001B+oSR\f\u0011C_5q-\u0006dW/Z:Tk\n\u001cG.Y:t+\u00159UkX3i)\u0011A%N\u001d<\u0011\u000b%k5KX1\u000f\u0005)[U\"A\u0017\n\u00051k\u0013!\u0003>jaZ\u000bG.^3t\u0013\tquJA\u0003J[Bd''\u0003\u0002Q#\n)QKR;oG*\u0011!kL\u0001\bO\u0016tWM]5d!\t!V\u000b\u0004\u0001\u0005\u000bY\u0013!\u0019A,\u0003\tY+7-M\t\u00031n\u0003\"\u0001N-\n\u0005i+$a\u0002(pi\"Lgn\u001a\t\u0003iqK!!X\u001b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002U?\u0012)\u0001M\u0001b\u0001/\n!a+Z23!\u0011Q%\rZ4\n\u0005\rl#\u0001\u0004.jaB,GMV1mk\u0016\u001c\bC\u0001+f\t\u00151'A1\u0001X\u0005\u0005!\u0006C\u0001+i\t\u0015I'A1\u0001X\u0005\u0005)\u0006\"B6\u0003\u0001\ba\u0017!\u0002<jK^\f\u0004\u0003\u0002\u001bn'>L!A\\\u001b\u0003!\u0011bWm]:%G>dwN\u001c\u0013mKN\u001c\bc\u0001&qI&\u0011\u0011/\f\u0002\u0007-\u0016\u001cGo\u001c:\t\u000bM\u0014\u00019\u0001;\u0002\u000bYLWm\u001e\u001a\u0011\tQjg,\u001e\t\u0004\u0015B<\u0007\"B<\u0003\u0001\bA\u0018AA8q!\u0015IUj\\;b\u0005IQ\u0016\u000e\u001d9fIZ+7\r^8s-\u0006dW/Z:\u0016\tmt\u00181H\n\b\u0007Mb\u0018qJA+!\u0015Q%-`A\u001d!\t!f\u0010B\u0005g\u0007\u0001\u0006\t\u0011!b\u0001/\"Za0!\u0001\u0002\b\u0005m\u0011QEA\u0018!\r!\u00141A\u0005\u0004\u0003\u000b)$aC:qK\u000eL\u0017\r\\5{K\u0012\f\u0014bIA\u0005\u0003\u0017\ty!!\u0004\u000f\u0007Q\nY!C\u0002\u0002\u000eU\na\u0001R8vE2,\u0017G\u0002\u0013\u0002\u0012\u0005eaG\u0004\u0003\u0002\u0014\u0005eQBAA\u000b\u0015\r\t9\"M\u0001\u0007yI|w\u000e\u001e \n\u0003Y\n\u0014bIA\u000f\u0003?\t\u0019#!\t\u000f\u0007Q\ny\"C\u0002\u0002\"U\n1!\u00138uc\u0019!\u0013\u0011CA\rmEJ1%a\n\u0002*\u00055\u00121\u0006\b\u0004i\u0005%\u0012bAA\u0016k\u0005)a\t\\8biF2A%!\u0005\u0002\u001aY\n\u0014bIA\u0019\u0003g\t9$!\u000e\u000f\u0007Q\n\u0019$C\u0002\u00026U\nA\u0001T8oOF2A%!\u0005\u0002\u001aY\u00022\u0001VA\u001e\t%I7\u0001)A\u0001\u0002\u000b\u0007q\u000b\u000b\u0007\u0002<\u0005\u0005\u0011qHA\"\u0003\u000f\nY%M\u0005$\u0003\u0013\tY!!\u0011\u0002\u000eE2A%!\u0005\u0002\u001aY\n\u0014bIA\u000f\u0003?\t)%!\t2\r\u0011\n\t\"!\u00077c%\u0019\u0013qEA\u0015\u0003\u0013\nY#\r\u0004%\u0003#\tIBN\u0019\nG\u0005E\u00121GA'\u0003k\td\u0001JA\t\u000331\u0004c\u0001\u001b\u0002R%\u0019\u00111K\u001b\u0003\u000fA\u0013x\u000eZ;diB!\u0011qKA/\u001d\u0011\t\t\"!\u0017\n\u0007\u0005mS'A\u0004qC\u000e\\\u0017mZ3\n\t\u0005}\u0013\u0011\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u00037*\u0014!A1\u0016\u0005\u0005\u001d\u0004c\u0001&q{\u0006\u0011\u0011\rI\u0001\u0002EV\u0011\u0011q\u000e\t\u0005\u0015B\fI$\u0001\u0002cA\u00051A(\u001b8jiz\"b!a\u001e\u0002|\u0005u\u0004CBA=\u0007u\fI$D\u0001\u0001\u0011\u001d\t\u0019\u0007\u0003a\u0001\u0003OBq!a\u001b\t\u0001\u0004\ty'A\u0004g_J,\u0017m\u00195\u0015\u0007\t\u000b\u0019\tC\u0004\u0002\u0006&\u0001\r!a\"\u0002\u0003\u0019\u0004r\u0001NAE{\u0006e\")C\u0002\u0002\fV\u0012\u0011BR;oGRLwN\u001c\u001a\u0002\t\r|\u0007/_\u000b\u0007\u0003#\u000b9*!,\u0015\r\u0005M\u0015\u0011YAc!\u001d\tIhAAK\u0003W\u00032\u0001VAL\t%1'\u0002)A\u0001\u0002\u000b\u0007q\u000b\u000b\u0007\u0002\u0018\u0006\u0005\u00111TAP\u0003G\u000b9+M\u0005$\u0003\u0013\tY!!(\u0002\u000eE2A%!\u0005\u0002\u001aY\n\u0014bIA\u000f\u0003?\t\t+!\t2\r\u0011\n\t\"!\u00077c%\u0019\u0013qEA\u0015\u0003K\u000bY#\r\u0004%\u0003#\tIBN\u0019\nG\u0005E\u00121GAU\u0003k\td\u0001JA\t\u000331\u0004c\u0001+\u0002.\u0012I\u0011N\u0003Q\u0001\u0002\u0003\u0015\ra\u0016\u0015\r\u0003[\u000b\t!!-\u00026\u0006e\u0016QX\u0019\nG\u0005%\u00111BAZ\u0003\u001b\td\u0001JA\t\u000331\u0014'C\u0012\u0002\u001e\u0005}\u0011qWA\u0011c\u0019!\u0013\u0011CA\rmEJ1%a\n\u0002*\u0005m\u00161F\u0019\u0007I\u0005E\u0011\u0011\u0004\u001c2\u0013\r\n\t$a\r\u0002@\u0006U\u0012G\u0002\u0013\u0002\u0012\u0005ea\u0007C\u0005\u0002d)\u0001\n\u00111\u0001\u0002DB!!\n]AK\u0011%\tYG\u0003I\u0001\u0002\u0004\t9\r\u0005\u0003Ka\u0006-\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0007\u0003\u001b\f\u0019/a>\u0016\u0005\u0005='\u0006BA4\u0003#\\#!a5\u0011\t\u0005U\u0017q\\\u0007\u0003\u0003/TA!!7\u0002\\\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003;,\u0014AC1o]>$\u0018\r^5p]&!\u0011\u0011]Al\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\nM.\u0001\u000b\u0011!AC\u0002]CC\"a9\u0002\u0002\u0005\u001d\u00181^Ax\u0003g\f\u0014bIA\u0005\u0003\u0017\tI/!\u00042\r\u0011\n\t\"!\u00077c%\u0019\u0013QDA\u0010\u0003[\f\t#\r\u0004%\u0003#\tIBN\u0019\nG\u0005\u001d\u0012\u0011FAy\u0003W\td\u0001JA\t\u000331\u0014'C\u0012\u00022\u0005M\u0012Q_A\u001bc\u0019!\u0013\u0011CA\rm\u0011I\u0011n\u0003Q\u0001\u0002\u0003\u0015\ra\u0016\u0015\r\u0003o\f\t!a?\u0002\u0000\n\r!qA\u0019\nG\u0005%\u00111BA\u007f\u0003\u001b\td\u0001JA\t\u000331\u0014'C\u0012\u0002\u001e\u0005}!\u0011AA\u0011c\u0019!\u0013\u0011CA\rmEJ1%a\n\u0002*\t\u0015\u00111F\u0019\u0007I\u0005E\u0011\u0011\u0004\u001c2\u0013\r\n\t$a\r\u0003\n\u0005U\u0012G\u0002\u0013\u0002\u0012\u0005ea'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\r\t=!1\u0003B\u0014+\t\u0011\tB\u000b\u0003\u0002p\u0005EG!\u00034\rA\u0003\u0005\tQ1\u0001XQ1\u0011\u0019\"!\u0001\u0003\u0018\tm!q\u0004B\u0012c%\u0019\u0013\u0011BA\u0006\u00053\ti!\r\u0004%\u0003#\tIBN\u0019\nG\u0005u\u0011q\u0004B\u000f\u0003C\td\u0001JA\t\u000331\u0014'C\u0012\u0002(\u0005%\"\u0011EA\u0016c\u0019!\u0013\u0011CA\rmEJ1%!\r\u00024\t\u0015\u0012QG\u0019\u0007I\u0005E\u0011\u0011\u0004\u001c\u0005\u0013%d\u0001\u0015!A\u0001\u0006\u00049\u0006\u0006\u0004B\u0014\u0003\u0003\u0011YCa\f\u00034\t]\u0012'C\u0012\u0002\n\u0005-!QFA\u0007c\u0019!\u0013\u0011CA\rmEJ1%!\b\u0002 \tE\u0012\u0011E\u0019\u0007I\u0005E\u0011\u0011\u0004\u001c2\u0013\r\n9#!\u000b\u00036\u0005-\u0012G\u0002\u0013\u0002\u0012\u0005ea'M\u0005$\u0003c\t\u0019D!\u000f\u00026E2A%!\u0005\u0002\u001aY\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001B !\u0011\u0011\tEa\u0013\u000e\u0005\t\r#\u0002\u0002B#\u0005\u000f\nA\u0001\\1oO*\u0011!\u0011J\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003N\t\r#AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0003TA\u0019AG!\u0016\n\u0007\t]SGA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002\\\u0005;B\u0011Ba\u0018\u0010\u0003\u0003\u0005\rAa\u0015\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011)\u0007E\u0003\u0003h\t54,\u0004\u0002\u0003j)\u0019!1N\u001b\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003p\t%$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BA!\u001e\u0003|A\u0019AGa\u001e\n\u0007\teTGA\u0004C_>dW-\u00198\t\u0011\t}\u0013#!AA\u0002m\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!q\bBA\u0011%\u0011yFEA\u0001\u0002\u0004\u0011\u0019&\u0001\u0005iCND7i\u001c3f)\t\u0011\u0019&\u0001\u0005u_N#(/\u001b8h)\t\u0011y$\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005k\u0012y\t\u0003\u0005\u0003`U\t\t\u00111\u0001\\\u0003IQ\u0016\u000e\u001d9fIZ+7\r^8s-\u0006dW/Z:\u0011\u0007\u0005etc\u0005\u0003\u0018g\t]\u0005\u0003\u0002BM\u0005?k!Aa'\u000b\t\tu%qI\u0001\u0003S>LA!a\u0018\u0003\u001cR\u0011!1S\u0001\u0006CB\u0004H._\u000b\u0007\u0005O\u0013iKa1\u0015\r\t%&q\u001bBn!\u001d\tIh\u0001BV\u0005\u0003\u00042\u0001\u0016BW\t%1'\u0004)A\u0001\u0002\u000b\u0007q\u000b\u000b\u0007\u0003.\u0006\u0005!\u0011\u0017B[\u0005s\u0013i,M\u0005$\u0003\u0013\tYAa-\u0002\u000eE2A%!\u0005\u0002\u001aY\n\u0014bIA\u000f\u0003?\u00119,!\t2\r\u0011\n\t\"!\u00077c%\u0019\u0013qEA\u0015\u0005w\u000bY#\r\u0004%\u0003#\tIBN\u0019\nG\u0005E\u00121\u0007B`\u0003k\td\u0001JA\t\u000331\u0004c\u0001+\u0003D\u0012I\u0011N\u0007Q\u0001\u0002\u0003\u0015\ra\u0016\u0015\r\u0005\u0007\f\tAa2\u0003L\n='1[\u0019\nG\u0005%\u00111\u0002Be\u0003\u001b\td\u0001JA\t\u000331\u0014'C\u0012\u0002\u001e\u0005}!QZA\u0011c\u0019!\u0013\u0011CA\rmEJ1%a\n\u0002*\tE\u00171F\u0019\u0007I\u0005E\u0011\u0011\u0004\u001c2\u0013\r\n\t$a\r\u0003V\u0006U\u0012G\u0002\u0013\u0002\u0012\u0005ea\u0007C\u0004\u0002di\u0001\rA!7\u0011\t)\u0003(1\u0016\u0005\b\u0003WR\u0002\u0019\u0001Bo!\u0011Q\u0005O!1\u0002\u000fUt\u0017\r\u001d9msV1!1\u001dB{\u0007\u001b!BA!:\u0004\"A)AGa:\u0003l&\u0019!\u0011^\u001b\u0003\r=\u0003H/[8o!\u001d!$Q\u001eBy\u0007\u0013I1Aa<6\u0005\u0019!V\u000f\u001d7feA!!\n\u001dBz!\r!&Q\u001f\u0003\nMn\u0001\u000b\u0011!AC\u0002]CCB!>\u0002\u0002\te(Q`B\u0001\u0007\u000b\t\u0014bIA\u0005\u0003\u0017\u0011Y0!\u00042\r\u0011\n\t\"!\u00077c%\u0019\u0013QDA\u0010\u0005\u007f\f\t#\r\u0004%\u0003#\tIBN\u0019\nG\u0005\u001d\u0012\u0011FB\u0002\u0003W\td\u0001JA\t\u000331\u0014'C\u0012\u00022\u0005M2qAA\u001bc\u0019!\u0013\u0011CA\rmA!!\n]B\u0006!\r!6Q\u0002\u0003\nSn\u0001\u000b\u0011!AC\u0002]CCb!\u0004\u0002\u0002\rE1QCB\r\u0007;\t\u0014bIA\u0005\u0003\u0017\u0019\u0019\"!\u00042\r\u0011\n\t\"!\u00077c%\u0019\u0013QDA\u0010\u0007/\t\t#\r\u0004%\u0003#\tIBN\u0019\nG\u0005\u001d\u0012\u0011FB\u000e\u0003W\td\u0001JA\t\u000331\u0014'C\u0012\u00022\u0005M2qDA\u001bc\u0019!\u0013\u0011CA\rm!I11E\u000e\u0002\u0002\u0003\u00071QE\u0001\u0004q\u0012\u0002\u0004cBA=\u0007\tM81B\u0001%S6\u0004HnX(q\u001bVd7kY1mCJ|\u0016J\u001c)mC\u000e,wLV0W?\u001e+g.\u001a:jGV!11FB\u001f)\u0011\u0019ica\u0010\u0011\u0011\r=2QGB\u001d\u0007sq1AOB\u0019\u0013\r\u0019\u0019dK\u0001\f\u001fBlU\u000f\\*dC2\f'/C\u0002\u00048=\u0013A\"\u00138QY\u0006\u001cW-S7qYJ\u0002BA\u00139\u0004<A\u0019Ak!\u0010\u0005\u000b\u0019d\"\u0019A,\t\u000f\r\u0005C\u0004q\u0001\u0004D\u0005)a-[3mIB11QIB&\u0007wi!aa\u0012\u000b\u0007\r%s&\u0001\u0003nCRD\u0017\u0002BB'\u0007\u000f\u0012\u0001bU3nSJLgnZ\u0001\u001fS6\u0004HnX(q\t&4x,\u00138QY\u0006\u001cWm\u0018,`-~;UM\\3sS\u000e,Baa\u0015\u0004bQ!1QKB2!!\u00199f!\u000e\u0004^\rucb\u0001\u001e\u0004Z%\u001911L\u0016\u0002\u000b=\u0003H)\u001b<\u0011\t)\u00038q\f\t\u0004)\u000e\u0005D!\u00024\u001e\u0005\u00049\u0006bBB!;\u0001\u000f1Q\r\t\u0007\u0007\u000b\u001a9ga\u0018\n\t\r%4q\t\u0002\u0006\r&,G\u000eZ\u0001\u001fS6\u0004HnX(q!><x,\u00138QY\u0006\u001cWm\u0018,`-~;UM\\3sS\u000e,Baa\u001c\u0004~Q!1\u0011OB@!!\u0019\u0019h!\u000e\u0004z\redb\u0001\u001e\u0004v%\u00191qO\u0016\u0002\u000b=\u0003\bk\\<\u0011\t)\u000381\u0010\t\u0004)\u000euD!\u00024\u001f\u0005\u00049\u0006bBBA=\u0001\u000f11Q\u0001\u0004a><\b#CB:\u001b\u000em41PB>\u0003yIW\u000e\u001d7`\u001fB\fE\rZ0J]Bc\u0017mY3`-~\u001bvlR3oKJL7-\u0006\u0003\u0004\n\u000e]E\u0003BBF\u00073\u0003\u0002b!$\u00046\rM5Q\u0013\b\u0004u\r=\u0015bABIW\u0005)q\n]!eIB!!\n]BK!\r!6q\u0013\u0003\u0006M~\u0011\ra\u0016\u0005\b\u0007\u0003z\u00029ABN!\u0019\u0019)ea\u0013\u0004\u0016\u0006q\u0012.\u001c9m?>\u00038+\u001e2`\u0013:\u0004F.Y2f?Z{6kX$f]\u0016\u0014\u0018nY\u000b\u0005\u0007C\u001by\u000b\u0006\u0003\u0004$\u000eE\u0006\u0003CBS\u0007k\u0019Yk!,\u000f\u0007i\u001a9+C\u0002\u0004*.\nQa\u00149Tk\n\u0004BA\u00139\u0004.B\u0019Aka,\u0005\u000b\u0019\u0004#\u0019A,\t\u000f\r\u0005\u0003\u0005q\u0001\u00044B11QIB[\u0007[KAaa.\u0004H\t!!+\u001b8h\u0003\u0011JW\u000e\u001d7`\u001fBlU\u000f\\*dC2\f'oX%o!2\f7-Z0W?N{v)\u001a8fe&\u001cW\u0003BB_\u0007\u000b$Baa0\u0004HBA1qFB\u001b\u0007\u0003\u001c\u0019\r\u0005\u0003Ka\u000e\r\u0007c\u0001+\u0004F\u0012)a-\tb\u0001/\"91\u0011I\u0011A\u0004\r%\u0007CBB#\u0007\u0017\u001a\u0019-\u0001\u0010j[Bdwl\u00149ESZ|\u0016J\u001c)mC\u000e,wLV0T?\u001e+g.\u001a:jGV!1qZBl)\u0011\u0019\tn!7\u0011\u0011\r]3QGBj\u0007+\u0004BA\u00139\u0004VB\u0019Aka6\u0005\u000b\u0019\u0014#\u0019A,\t\u000f\r\u0005#\u0005q\u0001\u0004\\B11QIB4\u0007+\fa$[7qY~{\u0005\u000fU8x?&s\u0007\u000b\\1dK~3vlU0HK:,'/[2\u0016\t\r\u00058\u0011\u001e\u000b\u0007\u0007G\u001cYoa<\u0011\u0011\rM4QGBs\u0007O\u0004BA\u00139\u0004hB\u0019Ak!;\u0005\u000b\u0019\u001c#\u0019A,\t\u000f\r\u00055\u0005q\u0001\u0004nBI11O'\u0004h\u000e\u001d8q\u001d\u0005\b\u0007c\u001c\u00039ABz\u0003\u0011QXM]8\u0011\r\rU81`Bt\u001b\t\u00199PC\u0002\u0004z>\nqa\u001d;pe\u0006<W-\u0003\u0003\u0004~\u000e](\u0001\u0002.fe>\f\u0001$[7qY~{\u0005/T;m\u0013:tWM]0W?Z{V-]0T+\u0011!\u0019\u0001\"\u0005\u0015\t\u0011\u0015A1\u0003\t\n\t\u000fiEQ\u0002C\u0007\t\u001fq1A\u000fC\u0005\u0013\r!YaK\u0001\u000b\u001fBlU\u000f\\%o]\u0016\u0014\b\u0003\u0002&q\t\u001f\u00012\u0001\u0016C\t\t\u00151GE1\u0001X\u0011\u001d\u0019\t\u0005\na\u0002\t+\u0001ba!\u0012\u0004L\u0011=\u0011AF5na2|v\n]*fi~3vLV0J]Bc\u0017mY3\u0016\t\u0011mA\u0011F\u000b\u0003\t;\u0001\u0002\u0002b\b\u00046\u0011\u0015BQ\u0005\b\u0004u\u0011\u0005\u0012b\u0001C\u0012W\u0005)q\n]*fiB!!\n\u001dC\u0014!\r!F\u0011\u0006\u0003\u0007\tW)#\u0019A,\u0003\u0003Y\u000ba#[7qY~{\u0005oU3u?Z{6kX%o!2\f7-Z\u000b\u0005\tc!I$\u0006\u0002\u00054AAAqDB\u001b\tk!9\u0004\u0005\u0003Ka\u0012]\u0002c\u0001+\u0005:\u00111A1\u0006\u0014C\u0002]\u000b1%[7qY~\u001b8-\u00197f\u0003\u0012$w,\u00138QY\u0006\u001cWm\u0018,`)~3vlR3oKJL7-\u0006\u0003\u0005@\u0011EC\u0003\u0002C!\t'\u0002\"\u0002b\u0011\u0005J\u00115Cq\nC'\u001d\rQEQI\u0005\u0004\t\u000fj\u0013\u0001C:dC2,\u0017\t\u001a3\n\u0007\u0011-sJ\u0001\u0007J]Bc\u0017mY3J[Bd7\u0007\u0005\u0003Ka\u0012=\u0003c\u0001+\u0005R\u0011)am\nb\u0001/\"IAQK\u0014\u0002\u0002\u0003\u000fAqK\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004CBB#\u0007\u0017\"y%A\u000ej[Bdwl\u00149Tk\n|fk\u0018,`KF|fkX$f]\u0016\u0014\u0018nY\u000b\u0005\t;\")\u0007\u0006\u0003\u0005`\u0011\u001d\u0004#CBS\u001b\u0012\u0005D\u0011\rC1!\u0011Q\u0005\u000fb\u0019\u0011\u0007Q#)\u0007B\u0003gQ\t\u0007q\u000bC\u0005\u0005j!\n\t\u0011q\u0001\u0005l\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\r\r\u00153Q\u0017C2\u0003mIW\u000e\u001d7`\u001fB\fE\rZ0W?Z{V-]0W?\u001e+g.\u001a:jGV!A\u0011\u000fC=)\u0011!\u0019\bb\u001f\u0011\u0013\r5U\n\"\u001e\u0005v\u0011U\u0004\u0003\u0002&q\to\u00022\u0001\u0016C=\t\u00151\u0017F1\u0001X\u0011%!i(KA\u0001\u0002\b!y(\u0001\u0006fm&$WM\\2fIM\u0002ba!\u0012\u0004L\u0011]\u0004"
)
public interface Vector_GenericOps extends GenericOps, Vector_TraversalOps {
   ZippedVectorValues$ ZippedVectorValues();

   // $FF: synthetic method
   static UFunc.UImpl2 zipValuesSubclass$(final Vector_GenericOps $this, final .less.colon.less view1, final .less.colon.less view2, final UFunc.UImpl2 op) {
      return $this.zipValuesSubclass(view1, view2, op);
   }

   default UFunc.UImpl2 zipValuesSubclass(final .less.colon.less view1, final .less.colon.less view2, final UFunc.UImpl2 op) {
      return op;
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_V_V_Generic$(final Vector_GenericOps $this, final Semiring field) {
      return $this.impl_OpMulScalar_InPlace_V_V_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_V_V_Generic(final Semiring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Semiring field$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector v, final Vector v2) {
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), v.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> v.update(BoxesRunTime.boxToInteger(i), this.field$1.$times(v.apply(BoxesRunTime.boxToInteger(i)), v2.apply(BoxesRunTime.boxToInteger(i)))));
         }

         public {
            this.field$1 = field$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_V_V_Generic$(final Vector_GenericOps $this, final Field field) {
      return $this.impl_OpDiv_InPlace_V_V_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpDiv_InPlace_V_V_Generic(final Field field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Field field$2;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector v, final Vector v2) {
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), v.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> v.update(BoxesRunTime.boxToInteger(i), this.field$2.$div(v.apply(BoxesRunTime.boxToInteger(i)), v2.apply(BoxesRunTime.boxToInteger(i)))));
         }

         public {
            this.field$2 = field$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_V_V_Generic$(final Vector_GenericOps $this, final UFunc.UImpl2 pow) {
      return $this.impl_OpPow_InPlace_V_V_Generic(pow);
   }

   default UFunc.InPlaceImpl2 impl_OpPow_InPlace_V_V_Generic(final UFunc.UImpl2 pow) {
      return new UFunc.InPlaceImpl2(pow) {
         private final UFunc.UImpl2 pow$1;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector v, final Vector v2) {
            v.activeIterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$apply$3(check$ifrefutable$1))).foreach((x$1) -> {
               $anonfun$apply$4(this, v, v2, x$1);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$3(final Tuple2 check$ifrefutable$1) {
            boolean var1;
            if (check$ifrefutable$1 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$4(final Object $this, final Vector v$3, final Vector v2$3, final Tuple2 x$1) {
            if (x$1 != null) {
               int i = x$1._1$mcI$sp();
               Object vv = x$1._2();
               v$3.update(BoxesRunTime.boxToInteger(i), $this.pow$1.apply(vv, v2$3.apply(BoxesRunTime.boxToInteger(i))));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$1);
            }
         }

         public {
            this.pow$1 = pow$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_V_S_Generic$(final Vector_GenericOps $this, final Semiring field) {
      return $this.impl_OpAdd_InPlace_V_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpAdd_InPlace_V_S_Generic(final Semiring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Semiring field$3;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector v, final Object v2) {
            if (!BoxesRunTime.equals(v2, this.field$3.zero())) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  v.update(BoxesRunTime.boxToInteger(index$macro$2), this.field$3.$plus(v.apply(BoxesRunTime.boxToInteger(index$macro$2)), v2));
               }
            }

         }

         public {
            this.field$3 = field$3;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_V_S_Generic$(final Vector_GenericOps $this, final Ring field) {
      return $this.impl_OpSub_InPlace_V_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpSub_InPlace_V_S_Generic(final Ring field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Ring field$4;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector v, final Object v2) {
            if (!BoxesRunTime.equals(v2, this.field$4.zero())) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  v.update(BoxesRunTime.boxToInteger(index$macro$2), this.field$4.$minus(v.apply(BoxesRunTime.boxToInteger(index$macro$2)), v2));
               }
            }

         }

         public {
            this.field$4 = field$4;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_V_S_Generic$(final Vector_GenericOps $this, final Semiring field) {
      return $this.impl_OpMulScalar_InPlace_V_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpMulScalar_InPlace_V_S_Generic(final Semiring field) {
      return new UFunc.InPlaceImpl2(field) {
         // $FF: synthetic field
         private final Vector_GenericOps $outer;
         private final Semiring field$5;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector v, final Object v2) {
            if (BoxesRunTime.equals(v2, this.field$5.zero())) {
               v.$colon$eq(v2, this.$outer.impl_OpSet_V_S_InPlace());
            } else if (GenericOps$.MODULE$.sparseEnoughForActiveIterator(v)) {
               v.activeIterator().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$apply$5(check$ifrefutable$2))).foreach((x$2) -> {
                  $anonfun$apply$6(this, v, v2, x$2);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  v.update(BoxesRunTime.boxToInteger(index$macro$2), this.field$5.$times(v.apply(BoxesRunTime.boxToInteger(index$macro$2)), v2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$5(final Tuple2 check$ifrefutable$2) {
            boolean var1;
            if (check$ifrefutable$2 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$6(final Object $this, final Vector v$4, final Object v2$4, final Tuple2 x$2) {
            if (x$2 != null) {
               int i = x$2._1$mcI$sp();
               Object vv = x$2._2();
               v$4.update(BoxesRunTime.boxToInteger(i), $this.field$5.$times(vv, v2$4));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$2);
            }
         }

         public {
            if (Vector_GenericOps.this == null) {
               throw null;
            } else {
               this.$outer = Vector_GenericOps.this;
               this.field$5 = field$5;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpDiv_InPlace_V_S_Generic$(final Vector_GenericOps $this, final Field field) {
      return $this.impl_OpDiv_InPlace_V_S_Generic(field);
   }

   default UFunc.InPlaceImpl2 impl_OpDiv_InPlace_V_S_Generic(final Field field) {
      return new UFunc.InPlaceImpl2(field) {
         private final Field field$6;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector v, final Object v2) {
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), v.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> v.update(BoxesRunTime.boxToInteger(i), this.field$6.$div(v.apply(BoxesRunTime.boxToInteger(i)), v2)));
         }

         public {
            this.field$6 = field$6;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpPow_InPlace_V_S_Generic$(final Vector_GenericOps $this, final UFunc.UImpl2 pow, final Zero zero) {
      return $this.impl_OpPow_InPlace_V_S_Generic(pow, zero);
   }

   default UFunc.InPlaceImpl2 impl_OpPow_InPlace_V_S_Generic(final UFunc.UImpl2 pow, final Zero zero) {
      return new UFunc.InPlaceImpl2(zero, pow) {
         private final Zero zero$1;
         private final UFunc.UImpl2 pow$2;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector v, final Object v2) {
            if (!BoxesRunTime.equals(v2, this.zero$1.zero()) && GenericOps$.MODULE$.sparseEnoughForActiveIterator(v)) {
               v.activeIterator().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$apply$8(check$ifrefutable$3))).foreach((x$3) -> {
                  $anonfun$apply$9(this, v, v2, x$3);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  v.update(BoxesRunTime.boxToInteger(index$macro$2), this.pow$2.apply(v.apply(BoxesRunTime.boxToInteger(index$macro$2)), v2));
               }
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$8(final Tuple2 check$ifrefutable$3) {
            boolean var1;
            if (check$ifrefutable$3 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$9(final Object $this, final Vector v$6, final Object v2$6, final Tuple2 x$3) {
            if (x$3 != null) {
               int i = x$3._1$mcI$sp();
               Object vv = x$3._2();
               v$6.update(BoxesRunTime.boxToInteger(i), $this.pow$2.apply(vv, v2$6));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$3);
            }
         }

         public {
            this.zero$1 = zero$1;
            this.pow$2 = pow$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulInner_V_V_eq_S$(final Vector_GenericOps $this, final Semiring field) {
      return $this.impl_OpMulInner_V_V_eq_S(field);
   }

   default UFunc.UImpl2 impl_OpMulInner_V_V_eq_S(final Semiring field) {
      return new UFunc.UImpl2(field) {
         private final Semiring field$7;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public Object apply(final Vector v, final Vector v2) {
            ObjectRef acc = ObjectRef.create(this.field$7.zero());
            if (v.activeSize() < v2.activeSize() && GenericOps$.MODULE$.sparseEnoughForActiveIterator(v)) {
               v.activeIterator().withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$apply$10(check$ifrefutable$4))).foreach((x$4) -> {
                  $anonfun$apply$11(this, acc, v2, x$4);
                  return BoxedUnit.UNIT;
               });
            } else if (v.activeSize() < v2.activeSize() && GenericOps$.MODULE$.sparseEnoughForActiveIterator(v)) {
               v2.activeIterator().withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$apply$12(check$ifrefutable$5))).foreach((x$5) -> {
                  $anonfun$apply$13(this, acc, v, x$5);
                  return BoxedUnit.UNIT;
               });
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = v.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  acc.elem = this.field$7.$plus(acc.elem, this.field$7.$times(v.apply(BoxesRunTime.boxToInteger(index$macro$2)), v2.apply(BoxesRunTime.boxToInteger(index$macro$2))));
               }
            }

            return acc.elem;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$10(final Tuple2 check$ifrefutable$4) {
            boolean var1;
            if (check$ifrefutable$4 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$11(final Object $this, final ObjectRef acc$1, final Vector v2$7, final Tuple2 x$4) {
            if (x$4 != null) {
               int i = x$4._1$mcI$sp();
               Object vv = x$4._2();
               acc$1.elem = $this.field$7.$plus(acc$1.elem, $this.field$7.$times(vv, v2$7.apply(BoxesRunTime.boxToInteger(i))));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$4);
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$12(final Tuple2 check$ifrefutable$5) {
            boolean var1;
            if (check$ifrefutable$5 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$13(final Object $this, final ObjectRef acc$1, final Vector v$7, final Tuple2 x$5) {
            if (x$5 != null) {
               int i = x$5._1$mcI$sp();
               Object v2v = x$5._2();
               acc$1.elem = $this.field$7.$plus(acc$1.elem, $this.field$7.$times(v$7.apply(BoxesRunTime.boxToInteger(i)), v2v));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$5);
            }
         }

         public {
            this.field$7 = field$7;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_V_V_InPlace$(final Vector_GenericOps $this) {
      return $this.impl_OpSet_V_V_InPlace();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_V_V_InPlace() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int index$macro$4 = 0;

               for(int limit$macro$6 = a.length(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  a.update(BoxesRunTime.boxToInteger(index$macro$4), b.apply(BoxesRunTime.boxToInteger(index$macro$4)));
               }

            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_V_S_InPlace$(final Vector_GenericOps $this) {
      return $this.impl_OpSet_V_S_InPlace();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_V_S_InPlace() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Vector a, final Object b) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               a.update(BoxesRunTime.boxToInteger(index$macro$2), b);
            }

         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_V_T_V_Generic$(final Vector_GenericOps $this, final Semiring evidence$1) {
      return $this.impl_scaleAdd_InPlace_V_T_V_Generic(evidence$1);
   }

   default UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_V_T_V_Generic(final Semiring evidence$1) {
      return (a, s, b) -> {
         Semiring sr = (Semiring)scala.Predef..MODULE$.implicitly(evidence$1);
         int left$macro$1 = b.length();
         int right$macro$2 = a.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            if (!BoxesRunTime.equals(s, BoxesRunTime.boxToInteger(0))) {
               b.activeIterator().withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$impl_scaleAdd_InPlace_V_T_V_Generic$2(check$ifrefutable$6))).foreach((x$6) -> {
                  $anonfun$impl_scaleAdd_InPlace_V_T_V_Generic$3(a, sr, s, x$6);
                  return BoxedUnit.UNIT;
               });
            }

         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpSub_V_V_eq_V_Generic$(final Vector_GenericOps $this, final Ring evidence$2) {
      return $this.impl_OpSub_V_V_eq_V_Generic(evidence$2);
   }

   default UFunc.UImpl2 impl_OpSub_V_V_eq_V_Generic(final Ring evidence$2) {
      return new UFunc.UImpl2(evidence$2) {
         private final Ring r;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         private Ring r() {
            return this.r;
         }

         public Vector apply(final Vector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector result = a.copy();
               b.activeIterator().withFilter((check$ifrefutable$7) -> BoxesRunTime.boxToBoolean($anonfun$apply$14(check$ifrefutable$7))).foreach((x$7) -> {
                  $anonfun$apply$15(this, result, a, x$7);
                  return BoxedUnit.UNIT;
               });
               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$14(final Tuple2 check$ifrefutable$7) {
            boolean var1;
            if (check$ifrefutable$7 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$15(final Object $this, final Vector result$1, final Vector a$2, final Tuple2 x$7) {
            if (x$7 != null) {
               int k = x$7._1$mcI$sp();
               Object v = x$7._2();
               result$1.update(BoxesRunTime.boxToInteger(k), $this.r().$minus(a$2.apply(BoxesRunTime.boxToInteger(k)), v));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$7);
            }
         }

         public {
            this.r = (Ring)scala.Predef..MODULE$.implicitly(evidence$2$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpAdd_V_V_eq_V_Generic$(final Vector_GenericOps $this, final Semiring evidence$3) {
      return $this.impl_OpAdd_V_V_eq_V_Generic(evidence$3);
   }

   default UFunc.UImpl2 impl_OpAdd_V_V_eq_V_Generic(final Semiring evidence$3) {
      return new UFunc.UImpl2(evidence$3) {
         private final Semiring r;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         private Semiring r() {
            return this.r;
         }

         public Vector apply(final Vector a, final Vector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               Vector result = a.copy();
               b.activeIterator().withFilter((check$ifrefutable$8) -> BoxesRunTime.boxToBoolean($anonfun$apply$16(check$ifrefutable$8))).foreach((x$8) -> {
                  $anonfun$apply$17(this, result, a, x$8);
                  return BoxedUnit.UNIT;
               });
               return result;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$apply$16(final Tuple2 check$ifrefutable$8) {
            boolean var1;
            if (check$ifrefutable$8 != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$17(final Object $this, final Vector result$2, final Vector a$3, final Tuple2 x$8) {
            if (x$8 != null) {
               int k = x$8._1$mcI$sp();
               Object v = x$8._2();
               result$2.update(BoxesRunTime.boxToInteger(k), $this.r().$plus(a$3.apply(BoxesRunTime.boxToInteger(k)), v));
               BoxedUnit var4 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(x$8);
            }
         }

         public {
            this.r = (Semiring)scala.Predef..MODULE$.implicitly(evidence$3$1);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static boolean $anonfun$impl_scaleAdd_InPlace_V_T_V_Generic$2(final Tuple2 check$ifrefutable$6) {
      boolean var1;
      if (check$ifrefutable$6 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static void $anonfun$impl_scaleAdd_InPlace_V_T_V_Generic$3(final Vector a$1, final Semiring sr$1, final Object s$1, final Tuple2 x$6) {
      if (x$6 != null) {
         int k = x$6._1$mcI$sp();
         Object v = x$6._2();
         a$1.update(BoxesRunTime.boxToInteger(k), sr$1.$plus(a$1.apply(BoxesRunTime.boxToInteger(k)), sr$1.$times(s$1, v)));
         BoxedUnit var4 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$6);
      }
   }

   static void $init$(final Vector_GenericOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class ZippedVectorValues implements ZippedValues, Product, Serializable {
      public final Vector a;
      public final Vector b;
      // $FF: synthetic field
      public final Vector_GenericOps $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean exists(final Function2 f) {
         return ZippedValues.exists$(this, f);
      }

      public boolean exists$mcDD$sp(final Function2 f) {
         return ZippedValues.exists$mcDD$sp$(this, f);
      }

      public boolean forall(final Function2 f) {
         return ZippedValues.forall$(this, f);
      }

      public boolean forall$mcDD$sp(final Function2 f) {
         return ZippedValues.forall$mcDD$sp$(this, f);
      }

      public Vector a() {
         return this.a;
      }

      public Vector b() {
         return this.b;
      }

      public void foreach(final Function2 f) {
         int index$macro$2 = 0;

         for(int limit$macro$4 = this.a().length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            f.apply(this.a().apply(BoxesRunTime.boxToInteger(index$macro$2)), this.b().apply(BoxesRunTime.boxToInteger(index$macro$2)));
         }

      }

      public ZippedVectorValues copy(final Vector a, final Vector b) {
         return this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer().new ZippedVectorValues(a, b);
      }

      public Vector copy$default$1() {
         return this.a();
      }

      public Vector copy$default$2() {
         return this.b();
      }

      public String productPrefix() {
         return "ZippedVectorValues";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.a();
               break;
            case 1:
               var10000 = this.b();
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
         return x$1 instanceof ZippedVectorValues;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "a";
               break;
            case 1:
               var10000 = "b";
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
         boolean var9;
         if (this != x$1) {
            label68: {
               boolean var2;
               if (x$1 instanceof ZippedVectorValues && ((ZippedVectorValues)x$1).breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer() == this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label44: {
                     label58: {
                        ZippedVectorValues var4 = (ZippedVectorValues)x$1;
                        Vector var10000 = this.a();
                        Vector var5 = var4.a();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label58;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label58;
                        }

                        var10000 = this.b();
                        Vector var6 = var4.b();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label58;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label58;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label44;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label68;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      public Vector a$mcD$sp() {
         return this.a();
      }

      public Vector a$mcF$sp() {
         return this.a();
      }

      public Vector a$mcI$sp() {
         return this.a();
      }

      public Vector a$mcJ$sp() {
         return this.a();
      }

      public Vector b$mcD$sp() {
         return this.b();
      }

      public Vector b$mcF$sp() {
         return this.b();
      }

      public Vector b$mcI$sp() {
         return this.b();
      }

      public Vector b$mcJ$sp() {
         return this.b();
      }

      public void foreach$mcDD$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcDF$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcDI$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcDJ$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcFD$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcFF$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcFI$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcFJ$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcID$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcIF$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcII$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcIJ$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcJD$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcJF$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcJI$sp(final Function2 f) {
         this.foreach(f);
      }

      public void foreach$mcJJ$sp(final Function2 f) {
         this.foreach(f);
      }

      public ZippedVectorValues copy$mDDc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcDD$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mDFc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcDF$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mDIc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcDI$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mDJc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcDJ$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mFDc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcFD$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mFFc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcFF$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mFIc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcFI$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mFJc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcFJ$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mIDc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcID$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mIFc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcIF$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mIIc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcII$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mIJc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcIJ$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mJDc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcJD$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mJFc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcJF$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mJIc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcJI$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public ZippedVectorValues copy$mJJc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcJJ$sp(this.breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer(), a, b);
      }

      public Vector copy$default$1$mcD$sp() {
         return this.copy$default$1();
      }

      public Vector copy$default$1$mcF$sp() {
         return this.copy$default$1();
      }

      public Vector copy$default$1$mcI$sp() {
         return this.copy$default$1();
      }

      public Vector copy$default$1$mcJ$sp() {
         return this.copy$default$1();
      }

      public Vector copy$default$2$mcD$sp() {
         return this.copy$default$2();
      }

      public Vector copy$default$2$mcF$sp() {
         return this.copy$default$2();
      }

      public Vector copy$default$2$mcI$sp() {
         return this.copy$default$2();
      }

      public Vector copy$default$2$mcJ$sp() {
         return this.copy$default$2();
      }

      public boolean specInstance$() {
         return false;
      }

      // $FF: synthetic method
      public Vector_GenericOps breeze$linalg$operators$Vector_GenericOps$ZippedVectorValues$$$outer() {
         return this.$outer;
      }

      public ZippedVectorValues(final Vector a, final Vector b) {
         this.a = a;
         this.b = b;
         if (Vector_GenericOps.this == null) {
            throw null;
         } else {
            this.$outer = Vector_GenericOps.this;
            super();
            ZippedValues.$init$(this);
            Product.$init$(this);
         }
      }
   }

   public class ZippedVectorValues$ implements Serializable {
      // $FF: synthetic field
      private final Vector_GenericOps $outer;

      public final String toString() {
         return "ZippedVectorValues";
      }

      public ZippedVectorValues apply(final Vector a, final Vector b) {
         return this.$outer.new ZippedVectorValues(a, b);
      }

      public Option unapply(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a(), x$0.b())));
      }

      public ZippedVectorValues apply$mDDc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcDD$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mDFc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcDF$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mDIc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcDI$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mDJc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcDJ$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mFDc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcFD$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mFFc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcFF$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mFIc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcFI$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mFJc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcFJ$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mIDc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcID$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mIFc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcIF$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mIIc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcII$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mIJc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcIJ$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mJDc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcJD$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mJFc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcJF$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mJIc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcJI$sp(this.$outer, a, b);
      }

      public ZippedVectorValues apply$mJJc$sp(final Vector a, final Vector b) {
         return new Vector_GenericOps$ZippedVectorValues$mcJJ$sp(this.$outer, a, b);
      }

      public Option unapply$mDDc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcD$sp(), x$0.b$mcD$sp())));
      }

      public Option unapply$mDFc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcD$sp(), x$0.b$mcF$sp())));
      }

      public Option unapply$mDIc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcD$sp(), x$0.b$mcI$sp())));
      }

      public Option unapply$mDJc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcD$sp(), x$0.b$mcJ$sp())));
      }

      public Option unapply$mFDc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcF$sp(), x$0.b$mcD$sp())));
      }

      public Option unapply$mFFc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcF$sp(), x$0.b$mcF$sp())));
      }

      public Option unapply$mFIc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcF$sp(), x$0.b$mcI$sp())));
      }

      public Option unapply$mFJc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcF$sp(), x$0.b$mcJ$sp())));
      }

      public Option unapply$mIDc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcI$sp(), x$0.b$mcD$sp())));
      }

      public Option unapply$mIFc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcI$sp(), x$0.b$mcF$sp())));
      }

      public Option unapply$mIIc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcI$sp(), x$0.b$mcI$sp())));
      }

      public Option unapply$mIJc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcI$sp(), x$0.b$mcJ$sp())));
      }

      public Option unapply$mJDc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcJ$sp(), x$0.b$mcD$sp())));
      }

      public Option unapply$mJFc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcJ$sp(), x$0.b$mcF$sp())));
      }

      public Option unapply$mJIc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcJ$sp(), x$0.b$mcI$sp())));
      }

      public Option unapply$mJJc$sp(final ZippedVectorValues x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.a$mcJ$sp(), x$0.b$mcJ$sp())));
      }

      public ZippedVectorValues$() {
         if (Vector_GenericOps.this == null) {
            throw null;
         } else {
            this.$outer = Vector_GenericOps.this;
            super();
         }
      }
   }
}
