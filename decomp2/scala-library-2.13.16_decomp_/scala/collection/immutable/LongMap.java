package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.AbstractIterator;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.generic.BitOperations;
import scala.collection.generic.DefaultSerializationProxy;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ImmutableBuilder;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015MxAB={\u0011\u0003\t\u0019AB\u0004\u0002\biD\t!!\u0003\t\u000f\u0005\r\u0012\u0001\"\u0001\u0002&!9\u0011qE\u0001\u0005\u0002\u0005%\u0002b\u0002CS\u0003\u0011\u0005Aq\u0015\u0005\b\u0005+\u000bA\u0011\u0001C[\u0011\u001d!Y-\u0001C\u0001\t\u001bDq\u0001b8\u0002\t\u0003!\to\u0002\u0005\u0005p\u0006A\tI\u001fC$\r!!y$\u0001EAu\u0012\u0005\u0003bBA\u0012\u0013\u0011\u0005AQ\t\u0005\b\t\u0013JA\u0011\tC&\u0011%!9#CA\u0001\n\u0003\u0012)\u0003C\u0005\u0005*%\t\t\u0011\"\u0001\u0003@!IA1F\u0005\u0002\u0002\u0013\u0005Aq\n\u0005\n\tgI\u0011\u0011!C!\tk1q\u0001b\u0015\u0002\u0001j$)\u0006\u0003\u0006\u0003pA\u0011)\u001a!C\u0001\u0007{A!\u0002b\u0018\u0011\u0005#\u0005\u000b\u0011BA\u001d\u0011)\u0011y\f\u0005BK\u0002\u0013\u0005A\u0011\r\u0005\u000b\tG\u0002\"\u0011#Q\u0001\n\u0011m\u0003bBA\u0012!\u0011\u0005AQ\r\u0005\b\t[\u0002B\u0011\u0001C8\u0011%\u0019\t\u0010EA\u0001\n\u0003!y\bC\u0005\u0005\bA\t\n\u0011\"\u0001\u0005\u000e\"IA\u0011\u0003\t\u0012\u0002\u0013\u0005A\u0011\u0013\u0005\n\tO\u0001\u0012\u0011!C!\u0005KA\u0011\u0002\"\u000b\u0011\u0003\u0003%\tAa\u0010\t\u0013\u0011-\u0002#!A\u0005\u0002\u0011e\u0005\"\u0003C\u001a!\u0005\u0005I\u0011\tC\u001b\u0011%!I\u0004EA\u0001\n\u0003\"ij\u0002\u0006\u0005r\u0006\t\t\u0011#\u0001{\tg4!\u0002b\u0015\u0002\u0003\u0003E\tA\u001fC{\u0011\u001d\t\u0019\u0003\tC\u0001\toD\u0011\u0002\"?!\u0003\u0003%)\u0005b?\t\u0013\tU\u0005%!A\u0005\u0002\u0012u\b\"CC\u0006A\u0005\u0005I\u0011QC\u0007\u0011%\u0019I\u000bIA\u0001\n\u0013)yBB\u0004\u00040\u0006\u0001%p!-\t\u0015\r\u0005gE!f\u0001\n\u0003\u0019i\u0004\u0003\u0006\u0004D\u001a\u0012\t\u0012)A\u0005\u0003sA!b!2'\u0005+\u0007I\u0011AB\u001f\u0011)\u00199M\nB\tB\u0003%\u0011\u0011\b\u0005\u000b\u0007\u00134#Q3A\u0005\u0002\r-\u0007BCBgM\tE\t\u0015!\u0003\u00046\"Q1q\u001a\u0014\u0003\u0016\u0004%\taa3\t\u0015\rEgE!E!\u0002\u0013\u0019)\fC\u0004\u0002$\u0019\"\taa5\t\u000f\r\u0005h\u0005\"\u0001\u0004d\"I1\u0011\u001f\u0014\u0002\u0002\u0013\u000511\u001f\u0005\n\t\u000f1\u0013\u0013!C\u0001\t\u0013A\u0011\u0002\"\u0005'#\u0003%\t\u0001b\u0005\t\u0013\u0011]a%%A\u0005\u0002\u0011e\u0001\"\u0003C\u0011ME\u0005I\u0011\u0001C\u0012\u0011%!9CJA\u0001\n\u0003\u0012)\u0003C\u0005\u0005*\u0019\n\t\u0011\"\u0001\u0003@!IA1\u0006\u0014\u0002\u0002\u0013\u0005AQ\u0006\u0005\n\tg1\u0013\u0011!C!\tkA\u0011\u0002\"\u000f'\u0003\u0003%\t\u0005b\u000f\b\u0015\u0015\u001d\u0012!!A\t\u0002i,IC\u0002\u0006\u00040\u0006\t\t\u0011#\u0001{\u000bWAq!a\t=\t\u0003)i\u0003C\u0005\u0005zr\n\t\u0011\"\u0012\u0005|\"I!Q\u0013\u001f\u0002\u0002\u0013\u0005Uq\u0006\u0005\n\u000b\u0017a\u0014\u0011!CA\u000b\u0007B\u0011b!+=\u0003\u0003%I!b\b\t\u000f\u0015e\u0013\u0001b\u0001\u0006\\\u001dAQ\u0011O\u0001!\u0012\u0013)\u0019H\u0002\u0005\u0006v\u0005\u0001\u000b\u0012BC<\u0011\u001d\t\u0019\u0003\u0012C\u0001\u000b\u007fBq!a\u001fE\t\u0003)\t\tC\u0004\u0005`\u0012#\t!\"#\t\u0013\r%F)!A\u0005\n\u0015}\u0001bBCM\u0003\u0011\rQ1T\u0004\t\u000bc\u000b\u0001\u0015#\u0003\u00064\u001aAQQW\u0001!\u0012\u0013)9\fC\u0004\u0002$-#\t!b/\t\u000f\u0005m4\n\"\u0001\u0006>\"9Aq\\&\u0005\u0002\u0015\u0015\u0007bBCe\u0003\u0011\rQ1\u001a\u0005\b\u000b3\fA1ACn\u0011%\u0019I+AA\u0001\n\u0013)yBB\u0004\u0002\bi\f\t#a\f\t\u000f\u0005\r\"\u000b\"\u0001\u0002z!9\u00111\u0010*\u0005R\u0005u\u0004bBAS%\u0012E\u0013q\u0015\u0005\b\u0003O\u0011F\u0011IA\\\u0011\u001d\tIL\u0015C!\u0003wCq!a1S\t\u0003\t)\rC\u0004\u0002NJ#)%a4\t\u000f\u0005%(\u000b\"\u0012\u0002l\"9\u00111 *\u0005B\u0005u\bb\u0002B\u0001%\u0012\u0015!1\u0001\u0005\b\u0005\u001f\u0011F\u0011\tB\t\u0011\u001d\u0011)B\u0015C\u0003\u0005/A\u0001Ba\tSA\u0013E#Q\u0005\u0005\b\u0005g\u0011F\u0011\tB\u001b\u0011\u001d\u0011iD\u0015C!\u0005\u007fAqAa\u0012S\t\u0003\u0012I\u0005C\u0004\u0003PI#\tE!\u0015\t\u000f\t\u0005$\u000b\"\u0012\u0003@!9!1\r*\u0005\u0006\t\u0015\u0004b\u0002B>%\u0012\u0015#Q\u0010\u0005\b\u0005+\u0013FQ\tBL\u0011\u001d\u0011iJ\u0015C!\u0005?CqAa,S\t\u0003\u0012\t\fC\u0004\u0003BJ#\tAa1\t\u000f\tU'\u000b\"\u0001\u0003X\"9!1\u001c*\u0005\u0002\tu\u0007b\u0002Bw%\u0012\u0005!q\u001e\u0005\b\u0007\u000b\u0011F\u0011AB\u0004\u0011\u001d\u0019yB\u0015C\u0001\u0007CAqa!\fS\t\u0003\u0019y\u0003C\u0004\u0004<I#)a!\u0010\t\u000f\r\u0005#\u000b\"\u0002\u0004>!91Q\t*\u0005\u0002\r\u001d\u0003bBB-%\u0012\u000511\f\u0005\b\u0007[\u0012F\u0011IB8\u0011\u001d\u0019iC\u0015C!\u0007\u0003Cqa!%S\t\u0003\u0019\u0019\n\u0003\u0005\u0004*J\u0003K\u0011CBV\u0003\u001dauN\\4NCBT!a\u001f?\u0002\u0013%lW.\u001e;bE2,'BA?\u007f\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u007f\u0006)1oY1mC\u000e\u0001\u0001cAA\u0003\u00035\t!PA\u0004M_:<W*\u00199\u0014\u000b\u0005\tY!a\u0005\u0011\t\u00055\u0011qB\u0007\u0002}&\u0019\u0011\u0011\u0003@\u0003\r\u0005s\u0017PU3g!\u0011\t)\"a\b\u000e\u0005\u0005]!\u0002BA\r\u00037\t!![8\u000b\u0005\u0005u\u0011\u0001\u00026bm\u0006LA!!\t\u0002\u0018\ta1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\"!a\u0001\u0002\u000b\u0015l\u0007\u000f^=\u0016\t\u0005-B1U\u000b\u0003\u0003[\u0001R!!\u0002S\tC+B!!\r\u0002DM9!+a\r\u0002V\u0005\r\u0004\u0003CA\u0003\u0003k\tI$a\u0010\n\u0007\u0005]\"PA\u0006BEN$(/Y2u\u001b\u0006\u0004\b\u0003BA\u0007\u0003wI1!!\u0010\u007f\u0005\u0011auN\\4\u0011\t\u0005\u0005\u00131\t\u0007\u0001\t!\t)E\u0015CC\u0002\u0005\u001d#!\u0001+\u0012\t\u0005%\u0013q\n\t\u0005\u0003\u001b\tY%C\u0002\u0002Ny\u0014qAT8uQ&tw\r\u0005\u0003\u0002\u000e\u0005E\u0013bAA*}\n\u0019\u0011I\\=\u0011\u0019\u0005\u0015\u0011qKA\u001d\u0003\u007f\tY&!\u0019\n\u0007\u0005e#PA\u000bTiJL7\r^(qi&l\u0017N_3e\u001b\u0006\u0004x\n]:\u0011\t\u0005\u0015\u0011QL\u0005\u0004\u0003?R(aA'baB)\u0011Q\u0001*\u0002@A!\u0011QMA;\u001d\u0011\t9'!\u001d\u000f\t\u0005%\u0014qN\u0007\u0003\u0003WRA!!\u001c\u0002\u0002\u00051AH]8pizJ\u0011a`\u0005\u0004\u0003gr\u0018a\u00029bG.\fw-Z\u0005\u0005\u0003C\t9HC\u0002\u0002ty$\"!!\u0019\u0002\u0019\u0019\u0014x.\\*qK\u000eLg-[2\u0015\t\u0005\u0005\u0014q\u0010\u0005\b\u0003\u0003#\u0006\u0019AAB\u0003\u0011\u0019w\u000e\u001c7+\t\u0005\u0015\u00151\u0013\t\u0007\u0003\u000f\u000bI)!$\u000e\u0003qL1!a#}\u00051IE/\u001a:bE2,wJ\\2f!!\ti!a$\u0002:\u0005}\u0012bAAI}\n1A+\u001e9mKJZ#!!&\u0011\t\u0005]\u0015\u0011U\u0007\u0003\u00033SA!a'\u0002\u001e\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003?s\u0018AC1o]>$\u0018\r^5p]&!\u00111UAM\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0013]\u0016<8\u000b]3dS\u001aL7MQ;jY\u0012,'/\u0006\u0002\u0002**\"\u00111VAJ!!\ti+a-\u0002\u000e\u0006\u0005TBAAX\u0015\r\t\t\f`\u0001\b[V$\u0018M\u00197f\u0013\u0011\t),a,\u0003\u000f\t+\u0018\u000e\u001c3feV\u0011\u0011\u0011M\u0001\u0007i>d\u0015n\u001d;\u0016\u0005\u0005u\u0006CBA\u0003\u0003\u007f\u000bi)C\u0002\u0002Bj\u0014A\u0001T5ti\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0002HB1\u0011qQAe\u0003\u001bK1!a3}\u0005!IE/\u001a:bi>\u0014\u0018a\u00024pe\u0016\f7\r[\u000b\u0005\u0003#\f)\u000f\u0006\u0003\u0002T\u0006e\u0007\u0003BA\u0007\u0003+L1!a6\u007f\u0005\u0011)f.\u001b;\t\u000f\u0005m\u0017\f1\u0001\u0002^\u0006\ta\r\u0005\u0005\u0002\u000e\u0005}\u0017QRAr\u0013\r\t\tO \u0002\n\rVt7\r^5p]F\u0002B!!\u0011\u0002f\u00129\u0011q]-C\u0002\u0005\u001d#!A+\u0002\u0019\u0019|'/Z1dQ\u0016sGO]=\u0016\t\u00055\u0018\u0011 \u000b\u0005\u0003'\fy\u000fC\u0004\u0002\\j\u0003\r!!=\u0011\u0015\u00055\u00111_A\u001d\u0003\u007f\t90C\u0002\u0002vz\u0014\u0011BR;oGRLwN\u001c\u001a\u0011\t\u0005\u0005\u0013\u0011 \u0003\b\u0003OT&\u0019AA$\u00031YW-_:Ji\u0016\u0014\u0018\r^8s+\t\ty\u0010\u0005\u0004\u0002\b\u0006%\u0017\u0011H\u0001\u000bM>\u0014X-Y2i\u0017\u0016LX\u0003\u0002B\u0003\u0005\u001b!B!a5\u0003\b!9\u00111\u001c/A\u0002\t%\u0001\u0003CA\u0007\u0003?\fIDa\u0003\u0011\t\u0005\u0005#Q\u0002\u0003\b\u0003Od&\u0019AA$\u000391\u0018\r\\;fg&#XM]1u_J,\"Aa\u0005\u0011\r\u0005\u001d\u0015\u0011ZA \u000311wN]3bG\"4\u0016\r\\;f+\u0011\u0011IB!\t\u0015\t\u0005M'1\u0004\u0005\b\u00037t\u0006\u0019\u0001B\u000f!!\ti!a8\u0002@\t}\u0001\u0003BA!\u0005C!q!a:_\u0005\u0004\t9%A\u0005dY\u0006\u001c8OT1nKV\u0011!q\u0005\t\u0005\u0005S\u0011y#\u0004\u0002\u0003,)!!QFA\u000e\u0003\u0011a\u0017M\\4\n\t\tE\"1\u0006\u0002\u0007'R\u0014\u0018N\\4\u0002\u000f%\u001cX)\u001c9usV\u0011!q\u0007\t\u0005\u0003\u001b\u0011I$C\u0002\u0003<y\u0014qAQ8pY\u0016\fg.A\u0005l]><hnU5{KV\u0011!\u0011\t\t\u0005\u0003\u001b\u0011\u0019%C\u0002\u0003Fy\u00141!\u00138u\u0003\u00191\u0017\u000e\u001c;feR!\u0011\u0011\rB&\u0011\u001d\tYN\u0019a\u0001\u0005\u001b\u0002\u0002\"!\u0004\u0002`\u00065%qG\u0001\niJ\fgn\u001d4pe6,BAa\u0015\u0003ZQ!!Q\u000bB/!\u0015\t)A\u0015B,!\u0011\t\tE!\u0017\u0005\u000f\tm3M1\u0001\u0002H\t\t1\u000bC\u0004\u0002\\\u000e\u0004\rAa\u0018\u0011\u0015\u00055\u00111_A\u001d\u0003\u007f\u00119&\u0001\u0003tSj,\u0017aA4fiR!!q\rB7!\u0019\tiA!\u001b\u0002@%\u0019!1\u000e@\u0003\r=\u0003H/[8o\u0011\u001d\u0011y'\u001aa\u0001\u0003s\t1a[3zQ\r)'1\u000f\t\u0005\u0005k\u00129(\u0004\u0002\u0002\u001e&!!\u0011PAO\u0005\u001d!\u0018-\u001b7sK\u000e\f\u0011bZ3u\u001fJ,En]3\u0016\t\t}$1\u0011\u000b\u0007\u0005\u0003\u00139I!#\u0011\t\u0005\u0005#1\u0011\u0003\b\u000572'\u0019\u0001BC#\u0011\ty$a\u0014\t\u000f\t=d\r1\u0001\u0002:!A!1\u00124\u0005\u0002\u0004\u0011i)A\u0004eK\u001a\fW\u000f\u001c;\u0011\r\u00055!q\u0012BA\u0013\r\u0011\tJ \u0002\ty\tLh.Y7f}!\u001aaMa\u001d\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005}\"\u0011\u0014\u0005\b\u0005_:\u0007\u0019AA\u001dQ\r9'1O\u0001\u0006IAdWo]\u000b\u0005\u0005C\u00139\u000b\u0006\u0003\u0003$\n%\u0006#BA\u0003%\n\u0015\u0006\u0003BA!\u0005O#qAa\u0017i\u0005\u0004\u0011)\tC\u0004\u0003,\"\u0004\rA!,\u0002\u0005-4\b\u0003CA\u0007\u0003\u001f\u000bID!*\u0002\u000fU\u0004H-\u0019;fIV!!1\u0017B])\u0019\u0011)La/\u0003>B)\u0011Q\u0001*\u00038B!\u0011\u0011\tB]\t\u001d\u0011Y&\u001bb\u0001\u0005\u000bCqAa\u001cj\u0001\u0004\tI\u0004C\u0004\u0003@&\u0004\rAa.\u0002\u000bY\fG.^3\u0002\u0015U\u0004H-\u0019;f/&$\b.\u0006\u0003\u0003F\n-G\u0003\u0003Bd\u0005\u001b\u0014yM!5\u0011\u000b\u0005\u0015!K!3\u0011\t\u0005\u0005#1\u001a\u0003\b\u00057R'\u0019\u0001BC\u0011\u001d\u0011yG\u001ba\u0001\u0003sAqAa0k\u0001\u0004\u0011I\rC\u0004\u0002\\*\u0004\rAa5\u0011\u0015\u00055\u00111_A \u0005\u0013\u0014I-A\u0004sK6|g/\u001a3\u0015\t\u0005\u0005$\u0011\u001c\u0005\b\u0005_Z\u0007\u0019AA\u001d\u00039iw\u000eZ5gs>\u0013(+Z7pm\u0016,BAa8\u0003fR!!\u0011\u001dBt!\u0015\t)A\u0015Br!\u0011\t\tE!:\u0005\u000f\tmCN1\u0001\u0002H!9\u00111\u001c7A\u0002\t%\bCCA\u0007\u0003g\fI$a\u0010\u0003lB1\u0011Q\u0002B5\u0005G\f\u0011\"\u001e8j_:<\u0016\u000e\u001e5\u0016\t\tE(q\u001f\u000b\u0007\u0005g\u0014IP!@\u0011\u000b\u0005\u0015!K!>\u0011\t\u0005\u0005#q\u001f\u0003\b\u00057j'\u0019\u0001BC\u0011\u001d\u0011Y0\u001ca\u0001\u0005g\fA\u0001\u001e5bi\"9\u00111\\7A\u0002\t}\b\u0003DA\u0007\u0007\u0003\tID!>\u0003v\nU\u0018bAB\u0002}\nIa)\u001e8di&|gnM\u0001\u0011S:$XM]:fGRLwN\\,ji\",ba!\u0003\u0004\u001a\r=ACBB\u0006\u0007'\u0019Y\u0002E\u0003\u0002\u0006I\u001bi\u0001\u0005\u0003\u0002B\r=AaBB\t]\n\u0007\u0011q\t\u0002\u0002%\"9!1 8A\u0002\rU\u0001#BA\u0003%\u000e]\u0001\u0003BA!\u00073!qAa\u0017o\u0005\u0004\t9\u0005C\u0004\u0002\\:\u0004\ra!\b\u0011\u0019\u000551\u0011AA\u001d\u0003\u007f\u00199b!\u0004\u0002\u0019%tG/\u001a:tK\u000e$\u0018n\u001c8\u0016\t\r\r21\u0006\u000b\u0005\u0003C\u001a)\u0003C\u0004\u0003|>\u0004\raa\n\u0011\u000b\u0005\u0015!k!\u000b\u0011\t\u0005\u000531\u0006\u0003\b\u0007#y'\u0019AA$\u0003)!\u0003\u000f\\;tIAdWo]\u000b\u0005\u0007c\u00199\u0004\u0006\u0003\u00044\re\u0002#BA\u0003%\u000eU\u0002\u0003BA!\u0007o!qAa\u0017q\u0005\u0004\u0011)\tC\u0004\u0003|B\u0004\raa\r\u0002\u0011\u0019L'o\u001d;LKf,\"!!\u000f)\u0007E\u0014\u0019(A\u0004mCN$8*Z=)\u0007I\u0014\u0019(A\u0002nCB,Ba!\u0013\u0004PQ!11JB*!\u0015\t)AUB'!\u0011\t\tea\u0014\u0005\u000f\rE3O1\u0001\u0002H\t\u0011aK\r\u0005\b\u00037\u001c\b\u0019AB+!!\ti!a8\u0002\u000e\u000e]\u0003\u0003CA\u0007\u0003\u001f\u000bId!\u0014\u0002\u000f\u0019d\u0017\r^'baV!1QLB2)\u0011\u0019yf!\u001a\u0011\u000b\u0005\u0015!k!\u0019\u0011\t\u0005\u000531\r\u0003\b\u0007#\"(\u0019AA$\u0011\u001d\tY\u000e\u001ea\u0001\u0007O\u0002\u0002\"!\u0004\u0002`\u000655\u0011\u000e\t\u0007\u0003\u000f\u000bIia\u001b\u0011\u0011\u00055\u0011qRA\u001d\u0007C\naaY8oG\u0006$X\u0003BB9\u0007o\"Baa\u001d\u0004|A)\u0011Q\u0001*\u0004vA!\u0011\u0011IB<\t\u001d\u0019I(\u001eb\u0001\u0005\u000b\u0013!AV\u0019\t\u000f\tmX\u000f1\u0001\u0004~A1\u0011qQAE\u0007\u007f\u0002\u0002\"!\u0004\u0002\u0010\u0006e2QO\u000b\u0005\u0007\u0007\u001bI\t\u0006\u0003\u0004\u0006\u000e-\u0005#BA\u0003%\u000e\u001d\u0005\u0003BA!\u0007\u0013#qa!\u001fw\u0005\u0004\u0011)\tC\u0004\u0003|Z\u0004\ra!$\u0011\r\u0005\u001d\u0015\u0011RBH!!\ti!a$\u0002:\r\u001d\u0015aB2pY2,7\r^\u000b\u0005\u0007+\u001bY\n\u0006\u0003\u0004\u0018\u000eu\u0005#BA\u0003%\u000ee\u0005\u0003BA!\u00077#qa!\u0015x\u0005\u0004\t9\u0005C\u0004\u0004 ^\u0004\ra!)\u0002\u0005A4\u0007\u0003CA\u0007\u0007G\u000biia*\n\u0007\r\u0015fPA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o!!\ti!a$\u0002:\re\u0015\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u0006S\u0011\u0011f%\u0003\t\u0003\u0007\tKg.\u0006\u0003\u00044\u000ee6c\u0002\u0014\u00046\u000em\u00161\r\t\u0006\u0003\u000b\u00116q\u0017\t\u0005\u0003\u0003\u001aI\f\u0002\u0005\u0002F\u0019\")\u0019AA$!\u0011\tia!0\n\u0007\r}fPA\u0004Qe>$Wo\u0019;\u0002\rA\u0014XMZ5y\u0003\u001d\u0001(/\u001a4jq\u0002\nA!\\1tW\u0006)Q.Y:lA\u0005!A.\u001a4u+\t\u0019),A\u0003mK\u001a$\b%A\u0003sS\u001eDG/\u0001\u0004sS\u001eDG\u000f\t\u000b\u000b\u0007+\u001cIna7\u0004^\u000e}\u0007#BBlM\r]V\"A\u0001\t\u000f\r\u0005w\u00061\u0001\u0002:!91QY\u0018A\u0002\u0005e\u0002bBBe_\u0001\u00071Q\u0017\u0005\b\u0007\u001f|\u0003\u0019AB[\u0003\r\u0011\u0017N\\\u000b\u0005\u0007K\u001cY\u000f\u0006\u0004\u0004h\u000e58q\u001e\t\u0006\u0003\u000b\u00116\u0011\u001e\t\u0005\u0003\u0003\u001aY\u000fB\u0004\u0003\\A\u0012\r!a\u0012\t\u000f\r%\u0007\u00071\u0001\u0004h\"91q\u001a\u0019A\u0002\r\u001d\u0018\u0001B2paf,Ba!>\u0004|RQ1q_B\u007f\u0007\u007f$\t\u0001\"\u0002\u0011\u000b\r]ge!?\u0011\t\u0005\u000531 \u0003\b\u0003\u000b\n$\u0019AA$\u0011%\u0019\t-\rI\u0001\u0002\u0004\tI\u0004C\u0005\u0004FF\u0002\n\u00111\u0001\u0002:!I1\u0011Z\u0019\u0011\u0002\u0003\u0007A1\u0001\t\u0006\u0003\u000b\u00116\u0011 \u0005\n\u0007\u001f\f\u0004\u0013!a\u0001\t\u0007\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0005\f\u0011=QC\u0001C\u0007U\u0011\tI$a%\u0005\u000f\u0005\u0015#G1\u0001\u0002H\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T\u0003\u0002C\u0006\t+!q!!\u00124\u0005\u0004\t9%\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0011mAqD\u000b\u0003\t;QCa!.\u0002\u0014\u00129\u0011Q\t\u001bC\u0002\u0005\u001d\u0013AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0005\t7!)\u0003B\u0004\u0002FU\u0012\r!a\u0012\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0014\u00050!IA\u0011\u0007\u001d\u0002\u0002\u0003\u0007!\u0011I\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0011]\u0002CBAD\u0003\u0013\fy%\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002B\u0014\t{A\u0011\u0002\"\r;\u0003\u0003\u0005\rA!\u0011\u0003\u00079KGnE\u0004\n\t\u0007\u001aY,a\u0019\u0011\u000b\u0005\u0015!+!\u0013\u0015\u0005\u0011\u001d\u0003cABl\u0013\u00051Q-];bYN$BAa\u000e\u0005N!9!1`\u0006A\u0002\u0005=C\u0003BA(\t#B\u0011\u0002\"\r\u000f\u0003\u0003\u0005\rA!\u0011\u0003\u0007QK\u0007/\u0006\u0003\u0005X\u0011u3c\u0002\t\u0005Z\rm\u00161\r\t\u0006\u0003\u000b\u0011F1\f\t\u0005\u0003\u0003\"i\u0006\u0002\u0005\u0002FA!)\u0019AA$\u0003\u0011YW-\u001f\u0011\u0016\u0005\u0011m\u0013A\u0002<bYV,\u0007\u0005\u0006\u0004\u0005h\u0011%D1\u000e\t\u0006\u0007/\u0004B1\f\u0005\b\u0005_*\u0002\u0019AA\u001d\u0011\u001d\u0011y,\u0006a\u0001\t7\n\u0011b^5uQZ\u000bG.^3\u0016\t\u0011ED\u0011\u0010\u000b\u0005\tg\"Y\bE\u0003\u0005vA!9HD\u0002\u0002\u0006\u0001\u0001B!!\u0011\u0005z\u00119!1\f\fC\u0002\u0005\u001d\u0003b\u0002C?-\u0001\u0007AqO\u0001\u0002gV!A\u0011\u0011CD)\u0019!\u0019\t\"#\u0005\fB)1q\u001b\t\u0005\u0006B!\u0011\u0011\tCD\t\u001d\t)e\u0006b\u0001\u0003\u000fB\u0011Ba\u001c\u0018!\u0003\u0005\r!!\u000f\t\u0013\t}v\u0003%AA\u0002\u0011\u0015U\u0003\u0002C\u0006\t\u001f#q!!\u0012\u0019\u0005\u0004\t9%\u0006\u0003\u0005\u0014\u0012]UC\u0001CKU\u0011!Y&a%\u0005\u000f\u0005\u0015\u0013D1\u0001\u0002HQ!\u0011q\nCN\u0011%!\t\u0004HA\u0001\u0002\u0004\u0011\t\u0005\u0006\u0003\u0003(\u0011}\u0005\"\u0003C\u0019=\u0005\u0005\t\u0019\u0001B!!\u0011\t\t\u0005b)\u0005\u000f\u0005\u00153A1\u0001\u0002H\u0005I1/\u001b8hY\u0016$xN\\\u000b\u0005\tS#y\u000b\u0006\u0004\u0005,\u0012EF1\u0017\t\u0006\u0003\u000b\u0011FQ\u0016\t\u0005\u0003\u0003\"y\u000bB\u0004\u0002F\u0011\u0011\r!a\u0012\t\u000f\t=D\u00011\u0001\u0002:!9!q\u0018\u0003A\u0002\u00115V\u0003\u0002C\\\t{#B\u0001\"/\u0005@B)\u0011Q\u0001*\u0005<B!\u0011\u0011\tC_\t\u001d\t)%\u0002b\u0001\u0003\u000fBq\u0001\"1\u0006\u0001\u0004!\u0019-A\u0003fY\u0016l7\u000f\u0005\u0004\u0002\u000e\u0011\u0015G\u0011Z\u0005\u0004\t\u000ft(A\u0003\u001fsKB,\u0017\r^3e}AA\u0011QBAH\u0003s!Y,\u0001\u0003ge>lW\u0003\u0002Ch\t+$B\u0001\"5\u0005ZB)\u0011Q\u0001*\u0005TB!\u0011\u0011\tCk\t\u001d!9N\u0002b\u0001\u0003\u000f\u0012\u0011A\u0016\u0005\b\u0003\u00033\u0001\u0019\u0001Cn!\u0019\t9)!#\u0005^BA\u0011QBAH\u0003s!\u0019.\u0001\u0006oK^\u0014U/\u001b7eKJ,B\u0001b9\u0005lV\u0011AQ\u001d\t\t\u0003[\u000b\u0019\fb:\u0005nBA\u0011QBAH\u0003s!I\u000f\u0005\u0003\u0002B\u0011-Ha\u0002Cl\u000f\t\u0007\u0011q\t\t\u0006\u0003\u000b\u0011F\u0011^\u0001\u0004\u001d&d\u0017a\u0001+jaB\u00191q\u001b\u0011\u0014\u000b\u0001\nY!a\u0005\u0015\u0005\u0011M\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\t\u001dR\u0003\u0002C\u0000\u000b\u000b!b!\"\u0001\u0006\b\u0015%\u0001#BBl!\u0015\r\u0001\u0003BA!\u000b\u000b!q!!\u0012$\u0005\u0004\t9\u0005C\u0004\u0003p\r\u0002\r!!\u000f\t\u000f\t}6\u00051\u0001\u0006\u0004\u00059QO\\1qa2LX\u0003BC\b\u000b/!B!\"\u0005\u0006\u001aA1\u0011Q\u0002B5\u000b'\u0001\u0002\"!\u0004\u0002\u0010\u0006eRQ\u0003\t\u0005\u0003\u0003*9\u0002B\u0004\u0002F\u0011\u0012\r!a\u0012\t\u0013\u0015mA%!AA\u0002\u0015u\u0011a\u0001=%aA)1q\u001b\t\u0006\u0016Q\u0011Q\u0011\u0005\t\u0005\u0005S)\u0019#\u0003\u0003\u0006&\t-\"AB(cU\u0016\u001cG/A\u0002CS:\u00042aa6='\u0015a\u00141BA\n)\t)I#\u0006\u0003\u00062\u0015]BCCC\u001a\u000bs)Y$\"\u0010\u0006BA)1q\u001b\u0014\u00066A!\u0011\u0011IC\u001c\t\u001d\t)e\u0010b\u0001\u0003\u000fBqa!1@\u0001\u0004\tI\u0004C\u0004\u0004F~\u0002\r!!\u000f\t\u000f\r%w\b1\u0001\u0006@A)\u0011Q\u0001*\u00066!91qZ A\u0002\u0015}R\u0003BC#\u000b'\"B!b\u0012\u0006VA1\u0011Q\u0002B5\u000b\u0013\u0002B\"!\u0004\u0006L\u0005e\u0012\u0011HC(\u000b\u001fJ1!\"\u0014\u007f\u0005\u0019!V\u000f\u001d7fiA)\u0011Q\u0001*\u0006RA!\u0011\u0011IC*\t\u001d\t)\u0005\u0011b\u0001\u0003\u000fB\u0011\"b\u0007A\u0003\u0003\u0005\r!b\u0016\u0011\u000b\r]g%\"\u0015\u0002\u0013Q|g)Y2u_JLX\u0003BC/\u000bS\"B!b\u0018\u0006nAA\u0011qQC1\u000bK*Y'C\u0002\u0006dq\u0014qAR1di>\u0014\u0018\u0010\u0005\u0005\u0002\u000e\u0005=\u0015\u0011HC4!\u0011\t\t%\"\u001b\u0005\u000f\u0011]'I1\u0001\u0002HA)\u0011Q\u0001*\u0006h!9Qq\u000e\"A\u0002\u0011U\u0014!\u00023v[6L\u0018!\u0003+p\r\u0006\u001cGo\u001c:z!\r\u00199\u000e\u0012\u0002\n)>4\u0015m\u0019;pef\u001cr\u0001RA\u0006\u000bs\n\u0019\u0007\u0005\u0005\u0002\b\u0016\u0005T1PC?!!\ti!a$\u0002:\u0005-\u0001#BA\u0003%\u0006-ACAC:)\u0011)i(b!\t\u000f\u0015\u0015e\t1\u0001\u0006\b\u0006\u0011\u0011\u000e\u001e\t\u0007\u0003\u000f\u000bI)b\u001f\u0016\u0005\u0015-\u0005\u0003CAW\u0003g+Y(\" )\u000f\u0011+yIa0\u0006\u0016B!\u0011QBCI\u0013\r)\u0019J \u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012s\u0012a\u0001\u0015\b\u0007\u0016=%qXCK\u0003-!xNQ;jY\u00124%o\\7\u0016\t\u0015uU\u0011\u0016\u000b\u0005\u000b?+i\u000b\u0005\u0006\u0002\b\u0016\u0005\u0016qJCS\u000bWK1!b)}\u0005%\u0011U/\u001b7e\rJ|W\u000e\u0005\u0005\u0002\u000e\u0005=\u0015\u0011HCT!\u0011\t\t%\"+\u0005\u000f\u0011]\u0017J1\u0001\u0002HA)\u0011Q\u0001*\u0006(\"9QqV%A\u0002\u0011U\u0014a\u00024bGR|'/_\u0001\f)>\u0014U/\u001b7e\rJ|W\u000eE\u0002\u0004X.\u00131\u0002V8Ck&dGM\u0012:p[N)1*a\u0003\u0006:BQ\u0011qQCQ\u0003\u001f*Y(\" \u0015\u0005\u0015MF\u0003BC`\u000b\u0007$B!\" \u0006B\"9QQQ'A\u0002\u0015\u001d\u0005b\u0002Cf\u001b\u0002\u0007\u0011q\n\u000b\u0005\u000b\u0017+9\rC\u0004\u0005L:\u0003\r!a\u0014\u0002\u001f%$XM]1cY\u00164\u0015m\u0019;pef,B!\"4\u0006VV\u0011Qq\u001a\t\t\u0003\u000f+\t'\"5\u0006XBA\u0011QBAH\u0003s)\u0019\u000e\u0005\u0003\u0002B\u0015UGa\u0002Cl\u001f\n\u0007\u0011q\t\t\u0006\u0003\u000b\u0011V1[\u0001\u0011EVLG\u000e\u001a$s_6duN\\4NCB,B!\"8\u0006pV\u0011Qq\u001c\t\u000b\u0003\u000f+\t+\"9\u0006l\u0016E\b\u0007BCr\u000bO\u0004R!!\u0002S\u000bK\u0004B!!\u0011\u0006h\u0012YQ\u0011\u001e)\u0002\u0002\u0003\u0005)\u0011AA$\u0005\ryF%\r\t\t\u0003\u001b\ty)!\u000f\u0006nB!\u0011\u0011ICx\t\u001d!9\u000e\u0015b\u0001\u0003\u000f\u0002R!!\u0002S\u000b[\u0004"
)
public abstract class LongMap extends AbstractMap implements StrictOptimizedMapOps, Serializable {
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

   public static Builder newBuilder() {
      LongMap$ var10000 = LongMap$.MODULE$;
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((LongMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            LongMap$ var10001 = LongMap$.MODULE$;
         }
      };
   }

   public static LongMap from(final IterableOnce coll) {
      return LongMap$.MODULE$.from(coll);
   }

   public static LongMap singleton(final long key, final Object value) {
      LongMap$ var10000 = LongMap$.MODULE$;
      return new Tip(key, value);
   }

   public IterableOps map(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return scala.collection.StrictOptimizedMapOps.collect$(this, pf);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
      return scala.collection.StrictOptimizedMapOps.$plus$(this, elem1, elem2, elems);
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
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((LongMap)this.elems()).$plus(elem));
            return this;
         }
      };
   }

   public LongMap empty() {
      return LongMap.Nil$.MODULE$;
   }

   public List toList() {
      ListBuffer buffer = new ListBuffer();
      Function1 foreach_f = (x$1) -> (ListBuffer)buffer.$plus$eq(x$1);

      LongMap foreach_this;
      LongMap foreach_right;
      for(foreach_this = this; foreach_this instanceof Bin; foreach_this = foreach_right) {
         Bin var4 = (Bin)foreach_this;
         LongMap foreach_left = var4.left();
         foreach_right = var4.right();
         foreach_left.foreach(foreach_f);
         foreach_f = foreach_f;
      }

      if (foreach_this instanceof Tip) {
         Tip var7 = (Tip)foreach_this;
         long foreach_key = var7.key();
         Object foreach_value = var7.value();
         Tuple2 var11 = new Tuple2(foreach_key, foreach_value);
         ListBuffer var10000 = (ListBuffer)buffer.addOne(var11);
      } else if (!LongMap.Nil$.MODULE$.equals(foreach_this)) {
         throw new MatchError(foreach_this);
      }

      foreach_this = null;
      foreach_f = null;
      Object var14 = null;
      Object var15 = null;
      foreach_right = null;
      Object var17 = null;
      Object var18 = null;
      return buffer.toList();
   }

   public Iterator iterator() {
      if (LongMap.Nil$.MODULE$.equals(this)) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new LongMapEntryIterator(this);
      }
   }

   public final void foreach(final Function1 f) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         LongMap left = var2.left();
         LongMap right = var2.right();
         left.foreach(f);
         f = f;
         this = right;
      }

      if (this instanceof Tip) {
         Tip var5 = (Tip)this;
         long key = var5.key();
         Object value = var5.value();
         f.apply(new Tuple2(key, value));
      } else if (!LongMap.Nil$.MODULE$.equals(this)) {
         throw new MatchError(this);
      }
   }

   public final void foreachEntry(final Function2 f) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         LongMap left = var2.left();
         LongMap right = var2.right();
         left.foreachEntry(f);
         f = f;
         this = right;
      }

      if (this instanceof Tip) {
         Tip var5 = (Tip)this;
         long key = var5.key();
         Object value = var5.value();
         f.apply(key, value);
      } else if (!LongMap.Nil$.MODULE$.equals(this)) {
         throw new MatchError(this);
      }
   }

   public Iterator keysIterator() {
      if (LongMap.Nil$.MODULE$.equals(this)) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new LongMapKeyIterator(this);
      }
   }

   public final void foreachKey(final Function1 f) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         LongMap left = var2.left();
         LongMap right = var2.right();
         left.foreachKey(f);
         f = f;
         this = right;
      }

      if (this instanceof Tip) {
         long key = ((Tip)this).key();
         f.apply(key);
      } else if (!LongMap.Nil$.MODULE$.equals(this)) {
         throw new MatchError(this);
      }
   }

   public Iterator valuesIterator() {
      if (LongMap.Nil$.MODULE$.equals(this)) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new LongMapValueIterator(this);
      }
   }

   public final void foreachValue(final Function1 f) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         LongMap left = var2.left();
         LongMap right = var2.right();
         left.foreachValue(f);
         f = f;
         this = right;
      }

      if (this instanceof Tip) {
         Object value = ((Tip)this).value();
         f.apply(value);
      } else if (!LongMap.Nil$.MODULE$.equals(this)) {
         throw new MatchError(this);
      }
   }

   public String className() {
      return "LongMap";
   }

   public boolean isEmpty() {
      return this == LongMap.Nil$.MODULE$;
   }

   public int knownSize() {
      return this.isEmpty() ? 0 : -1;
   }

   public LongMap filter(final Function1 f) {
      if (this instanceof Bin) {
         Bin var2 = (Bin)this;
         long prefix = var2.prefix();
         long mask = var2.mask();
         LongMap left = var2.left();
         LongMap right = var2.right();
         LongMap var10000 = left.filter(f);
         LongMap var14 = right.filter(f);
         LongMap newleft = var10000;
         return left == newleft && right == var14 ? this : LongMapUtils$.MODULE$.bin(prefix, mask, newleft, var14);
      } else if (this instanceof Tip) {
         Tip var10 = (Tip)this;
         long key = var10.key();
         Object value = var10.value();
         return (LongMap)(BoxesRunTime.unboxToBoolean(f.apply(new Tuple2(key, value))) ? this : LongMap.Nil$.MODULE$);
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         return LongMap.Nil$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public LongMap transform(final Function2 f) {
      if (this instanceof Bin) {
         Bin var2 = (Bin)this;
         LongMap left = var2.left();
         LongMap right = var2.right();
         return var2.bin(left.transform(f), right.transform(f));
      } else if (this instanceof Tip) {
         Tip var5 = (Tip)this;
         long key = var5.key();
         Object value = var5.value();
         return var5.withValue(f.apply(key, value));
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         return LongMap.Nil$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public final int size() {
      if (LongMap.Nil$.MODULE$.equals(this)) {
         return 0;
      } else if (this instanceof Tip) {
         return 1;
      } else if (this instanceof Bin) {
         Bin var1 = (Bin)this;
         LongMap left = var1.left();
         LongMap right = var1.right();
         return left.size() + right.size();
      } else {
         throw new MatchError(this);
      }
   }

   public final Option get(final long key) {
      while(this instanceof Bin) {
         Bin var3 = (Bin)this;
         long mask = var3.mask();
         LongMap left = var3.left();
         LongMap right = var3.right();
         if (BitOperations.Long.zero$(LongMapUtils$.MODULE$, key, mask)) {
            key = key;
            this = left;
         } else {
            key = key;
            this = right;
         }
      }

      if (this instanceof Tip) {
         Tip var8 = (Tip)this;
         long key2 = var8.key();
         Object value = var8.value();
         if (key == key2) {
            return new Some(value);
         } else {
            return None$.MODULE$;
         }
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         return None$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public final Object getOrElse(final long key, final Function0 default) {
      while(!LongMap.Nil$.MODULE$.equals(this)) {
         if (this instanceof Tip) {
            Tip var4 = (Tip)this;
            long key2 = var4.key();
            Object value = var4.value();
            if (key == key2) {
               return value;
            }

            return default.apply();
         }

         if (!(this instanceof Bin)) {
            throw new MatchError(this);
         }

         Bin var8 = (Bin)this;
         long mask = var8.mask();
         LongMap left = var8.left();
         LongMap right = var8.right();
         if (BitOperations.Long.zero$(LongMapUtils$.MODULE$, key, mask)) {
            default = default;
            key = key;
            this = left;
         } else {
            default = default;
            key = key;
            this = right;
         }
      }

      return default.apply();
   }

   public final Object apply(final long key) {
      while(this instanceof Bin) {
         Bin var3 = (Bin)this;
         long mask = var3.mask();
         LongMap left = var3.left();
         LongMap right = var3.right();
         if (BitOperations.Long.zero$(LongMapUtils$.MODULE$, key, mask)) {
            key = key;
            this = left;
         } else {
            key = key;
            this = right;
         }
      }

      if (this instanceof Tip) {
         Tip var8 = (Tip)this;
         long key2 = var8.key();
         Object value = var8.value();
         if (key == key2) {
            return value;
         } else {
            throw new IllegalArgumentException("Key not found");
         }
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         throw new IllegalArgumentException("key not found");
      } else {
         throw new MatchError(this);
      }
   }

   public LongMap $plus(final Tuple2 kv) {
      return this.updated(kv._1$mcJ$sp(), kv._2());
   }

   public LongMap updated(final long key, final Object value) {
      if (this instanceof Bin) {
         Bin var4 = (Bin)this;
         long prefix = var4.prefix();
         long mask = var4.mask();
         LongMap left = var4.left();
         LongMap right = var4.right();
         if (!BitOperations.Long.hasMatch$(LongMapUtils$.MODULE$, key, prefix, mask)) {
            return LongMapUtils$.MODULE$.join(key, new Tip(key, value), prefix, this);
         } else {
            return BitOperations.Long.zero$(LongMapUtils$.MODULE$, key, mask) ? new Bin(prefix, mask, left.updated(key, value), right) : new Bin(prefix, mask, left, right.updated(key, value));
         }
      } else if (this instanceof Tip) {
         long key2 = ((Tip)this).key();
         return (LongMap)(key == key2 ? new Tip(key, value) : LongMapUtils$.MODULE$.join(key, new Tip(key, value), key2, this));
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         return new Tip(key, value);
      } else {
         throw new MatchError(this);
      }
   }

   public LongMap updateWith(final long key, final Object value, final Function2 f) {
      if (this instanceof Bin) {
         Bin var5 = (Bin)this;
         long prefix = var5.prefix();
         long mask = var5.mask();
         LongMap left = var5.left();
         LongMap right = var5.right();
         if (!BitOperations.Long.hasMatch$(LongMapUtils$.MODULE$, key, prefix, mask)) {
            return LongMapUtils$.MODULE$.join(key, new Tip(key, value), prefix, this);
         } else {
            return BitOperations.Long.zero$(LongMapUtils$.MODULE$, key, mask) ? new Bin(prefix, mask, left.updateWith(key, value, f), right) : new Bin(prefix, mask, left, right.updateWith(key, value, f));
         }
      } else if (this instanceof Tip) {
         Tip var12 = (Tip)this;
         long key2 = var12.key();
         Object value2 = var12.value();
         return (LongMap)(key == key2 ? new Tip(key, f.apply(value2, value)) : LongMapUtils$.MODULE$.join(key, new Tip(key, value), key2, this));
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         return new Tip(key, value);
      } else {
         throw new MatchError(this);
      }
   }

   public LongMap removed(final long key) {
      if (this instanceof Bin) {
         Bin var3 = (Bin)this;
         long prefix = var3.prefix();
         long mask = var3.mask();
         LongMap left = var3.left();
         LongMap right = var3.right();
         if (!BitOperations.Long.hasMatch$(LongMapUtils$.MODULE$, key, prefix, mask)) {
            return this;
         } else if (BitOperations.Long.zero$(LongMapUtils$.MODULE$, key, mask)) {
            LongMapUtils$ var16 = LongMapUtils$.MODULE$;
            Long $minus_key = key;
            if (left == null) {
               throw null;
            } else {
               MapOps var10003 = left.removed($minus_key);
               Object var14 = null;
               return var16.bin(prefix, mask, (LongMap)var10003, right);
            }
         } else {
            LongMapUtils$ var10000 = LongMapUtils$.MODULE$;
            Long $minus_key = key;
            if (right == null) {
               throw null;
            } else {
               MapOps var10004 = right.removed($minus_key);
               Object var15 = null;
               return var10000.bin(prefix, mask, left, (LongMap)var10004);
            }
         }
      } else if (this instanceof Tip) {
         long key2 = ((Tip)this).key();
         return (LongMap)(key == key2 ? LongMap.Nil$.MODULE$ : this);
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         return LongMap.Nil$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public LongMap modifyOrRemove(final Function2 f) {
      if (this instanceof Bin) {
         Bin var2 = (Bin)this;
         long prefix = var2.prefix();
         long mask = var2.mask();
         LongMap left = var2.left();
         LongMap right = var2.right();
         LongMap newleft = left.modifyOrRemove(f);
         LongMap newright = right.modifyOrRemove(f);
         return left == newleft && right == newright ? this : LongMapUtils$.MODULE$.bin(prefix, mask, newleft, newright);
      } else if (this instanceof Tip) {
         Tip var11 = (Tip)this;
         long key = var11.key();
         Object value = var11.value();
         Option var15 = (Option)f.apply(key, value);
         if (None$.MODULE$.equals(var15)) {
            return LongMap.Nil$.MODULE$;
         } else if (var15 instanceof Some) {
            Object value2 = ((Some)var15).value();
            return (LongMap)(value == value2 ? this : new Tip(key, value2));
         } else {
            throw new MatchError(var15);
         }
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         return LongMap.Nil$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public LongMap unionWith(final LongMap that, final Function3 f) {
      Tuple2 var3 = new Tuple2(this, that);
      if (this instanceof Bin) {
         Bin var4 = (Bin)this;
         long p1 = var4.prefix();
         long m1 = var4.mask();
         LongMap l1 = var4.left();
         LongMap r1 = var4.right();
         if (that instanceof Bin) {
            Bin var11 = (Bin)that;
            long p2 = var11.prefix();
            long m2 = var11.mask();
            LongMap l2 = var11.left();
            LongMap r2 = var11.right();
            if (BitOperations.Long.unsignedCompare$(LongMapUtils$.MODULE$, m2, m1)) {
               if (!BitOperations.Long.hasMatch$(LongMapUtils$.MODULE$, p2, p1, m1)) {
                  return LongMapUtils$.MODULE$.join(p1, this, p2, var11);
               }

               if (BitOperations.Long.zero$(LongMapUtils$.MODULE$, p2, m1)) {
                  return new Bin(p1, m1, l1.unionWith(var11, f), r1);
               }

               return new Bin(p1, m1, l1, r1.unionWith(var11, f));
            }

            if (BitOperations.Long.unsignedCompare$(LongMapUtils$.MODULE$, m1, m2)) {
               if (!BitOperations.Long.hasMatch$(LongMapUtils$.MODULE$, p1, p2, m2)) {
                  return LongMapUtils$.MODULE$.join(p1, this, p2, var11);
               }

               if (BitOperations.Long.zero$(LongMapUtils$.MODULE$, p1, m2)) {
                  return new Bin(p2, m2, this.unionWith(l2, f), r2);
               }

               return new Bin(p2, m2, l2, this.unionWith(r2, f));
            }

            if (p1 == p2) {
               return new Bin(p1, m1, l1.unionWith(l2, f), r1.unionWith(r2, f));
            }

            return LongMapUtils$.MODULE$.join(p1, this, p2, var11);
         }
      }

      if (this instanceof Tip) {
         Tip var18 = (Tip)this;
         long key = var18.key();
         Object value = var18.value();
         return that.updateWith(key, value, (x, y) -> f.apply(BoxesRunTime.boxToLong(key), y, x));
      } else if (that instanceof Tip) {
         Tip var22 = (Tip)that;
         long key = var22.key();
         Object value = var22.value();
         return this.updateWith(key, value, (x, y) -> f.apply(BoxesRunTime.boxToLong(key), x, y));
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         return that;
      } else if (LongMap.Nil$.MODULE$.equals(that)) {
         return this;
      } else {
         throw new MatchError(var3);
      }
   }

   public LongMap intersectionWith(final LongMap that, final Function3 f) {
      if (this instanceof Bin) {
         Bin var3 = (Bin)this;
         long p1 = var3.prefix();
         long m1 = var3.mask();
         LongMap l1 = var3.left();
         LongMap r1 = var3.right();
         if (that instanceof Bin) {
            Bin var10 = (Bin)that;
            long p2 = var10.prefix();
            long m2 = var10.mask();
            LongMap l2 = var10.left();
            LongMap r2 = var10.right();
            if (BitOperations.Long.unsignedCompare$(LongMapUtils$.MODULE$, m2, m1)) {
               if (!BitOperations.Long.hasMatch$(LongMapUtils$.MODULE$, p2, p1, m1)) {
                  return LongMap.Nil$.MODULE$;
               }

               if (BitOperations.Long.zero$(LongMapUtils$.MODULE$, p2, m1)) {
                  return l1.intersectionWith(var10, f);
               }

               return r1.intersectionWith(var10, f);
            }

            if (m1 == m2) {
               return LongMapUtils$.MODULE$.bin(p1, m1, l1.intersectionWith(l2, f), r1.intersectionWith(r2, f));
            }

            if (!BitOperations.Long.hasMatch$(LongMapUtils$.MODULE$, p1, p2, m2)) {
               return LongMap.Nil$.MODULE$;
            }

            if (BitOperations.Long.zero$(LongMapUtils$.MODULE$, p1, m2)) {
               return this.intersectionWith(l2, f);
            }

            return this.intersectionWith(r2, f);
         }
      }

      if (this instanceof Tip) {
         Tip var17 = (Tip)this;
         long key = var17.key();
         Object value = var17.value();
         Option var21 = that.get(key);
         if (None$.MODULE$.equals(var21)) {
            return LongMap.Nil$.MODULE$;
         } else if (var21 instanceof Some) {
            Object value2 = ((Some)var21).value();
            return new Tip(key, f.apply(key, value, value2));
         } else {
            throw new MatchError(var21);
         }
      } else if (that instanceof Tip) {
         Tip var23 = (Tip)that;
         long key = var23.key();
         Object value = var23.value();
         Option var27 = this.get(key);
         if (None$.MODULE$.equals(var27)) {
            return LongMap.Nil$.MODULE$;
         } else if (var27 instanceof Some) {
            Object value2 = ((Some)var27).value();
            return new Tip(key, f.apply(key, value2, value));
         } else {
            throw new MatchError(var27);
         }
      } else {
         return LongMap.Nil$.MODULE$;
      }
   }

   public LongMap intersection(final LongMap that) {
      return this.intersectionWith(that, (key, value, value2) -> $anonfun$intersection$1(BoxesRunTime.unboxToLong(key), value, value2));
   }

   public LongMap $plus$plus(final LongMap that) {
      return this.unionWith(that, (key, x, y) -> $anonfun$$plus$plus$1(BoxesRunTime.unboxToLong(key), x, y));
   }

   public final long firstKey() {
      while(this instanceof Bin) {
         this = ((Bin)this).left();
      }

      if (this instanceof Tip) {
         return ((Tip)this).key();
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         throw new IllegalStateException("Empty set");
      } else {
         throw new MatchError(this);
      }
   }

   public final long lastKey() {
      while(this instanceof Bin) {
         this = ((Bin)this).right();
      }

      if (this instanceof Tip) {
         return ((Tip)this).key();
      } else if (LongMap.Nil$.MODULE$.equals(this)) {
         throw new IllegalStateException("Empty set");
      } else {
         throw new MatchError(this);
      }
   }

   public LongMap map(final Function1 f) {
      return LongMap$.MODULE$.from(new View.Map(this, f));
   }

   public LongMap flatMap(final Function1 f) {
      return LongMap$.MODULE$.from(new View.FlatMap(this, f));
   }

   public LongMap concat(final IterableOnce that) {
      return (LongMap)StrictOptimizedMapOps.concat$(this, that);
   }

   public LongMap $plus$plus(final IterableOnce that) {
      return this.concat(that);
   }

   public LongMap collect(final PartialFunction pf) {
      LongMap$ var10000 = LongMap$.MODULE$;
      Builder strictOptimizedCollect_b = new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((LongMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            LongMap$ var10001 = LongMap$.MODULE$;
         }
      };
      Object strictOptimizedCollect_marker = Statics.pfMarker;
      Iterator strictOptimizedCollect_it = this.iterator();

      while(strictOptimizedCollect_it.hasNext()) {
         Object strictOptimizedCollect_elem = strictOptimizedCollect_it.next();
         Object strictOptimizedCollect_v = pf.applyOrElse(strictOptimizedCollect_elem, StrictOptimizedIterableOps::$anonfun$strictOptimizedCollect$1);
         if (strictOptimizedCollect_marker != strictOptimizedCollect_v) {
            ((<undefinedtype>)strictOptimizedCollect_b).addOne((Tuple2)strictOptimizedCollect_v);
         }
      }

      return (LongMap)strictOptimizedCollect_b.result();
   }

   public Object writeReplace() {
      LongMap$ var10002 = LongMap$.MODULE$;
      var10002 = LongMap$.MODULE$;
      return new DefaultSerializationProxy(LongMap.ToFactory$.MODULE$, this);
   }

   // $FF: synthetic method
   public static final Object $anonfun$intersection$1(final long key, final Object value, final Object value2) {
      return value;
   }

   // $FF: synthetic method
   public static final Object $anonfun$$plus$plus$1(final long key, final Object x, final Object y) {
      return y;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Nil$ extends LongMap implements Product {
      public static final Nil$ MODULE$ = new Nil$();

      static {
         Nil$ var10000 = MODULE$;
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean equals(final Object that) {
         if (that == this) {
            return true;
         } else {
            return that instanceof LongMap ? false : scala.collection.Map.equals$(this, that);
         }
      }

      public String productPrefix() {
         return "Nil";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }
   }

   public static class Tip extends LongMap implements Product {
      private final long key;
      private final Object value;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long key() {
         return this.key;
      }

      public Object value() {
         return this.value;
      }

      public Tip withValue(final Object s) {
         return s == this.value() ? this : new Tip(this.key(), s);
      }

      public Tip copy(final long key, final Object value) {
         return new Tip(key, value);
      }

      public long copy$default$1() {
         return this.key();
      }

      public Object copy$default$2() {
         return this.value();
      }

      public String productPrefix() {
         return "Tip";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.key();
            case 1:
               return this.value();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "key";
            case 1:
               return "value";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public Tip(final long key, final Object value) {
         this.key = key;
         this.value = value;
      }
   }

   public static class Tip$ implements Serializable {
      public static final Tip$ MODULE$ = new Tip$();

      public final String toString() {
         return "Tip";
      }

      public Tip apply(final long key, final Object value) {
         return new Tip(key, value);
      }

      public Option unapply(final Tip x$0) {
         return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2(x$0.key(), x$0.value())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Tip$.class);
      }
   }

   public static class Bin extends LongMap implements Product {
      private final long prefix;
      private final long mask;
      private final LongMap left;
      private final LongMap right;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long prefix() {
         return this.prefix;
      }

      public long mask() {
         return this.mask;
      }

      public LongMap left() {
         return this.left;
      }

      public LongMap right() {
         return this.right;
      }

      public LongMap bin(final LongMap left, final LongMap right) {
         return this.left() == left && this.right() == right ? this : new Bin(this.prefix(), this.mask(), left, right);
      }

      public Bin copy(final long prefix, final long mask, final LongMap left, final LongMap right) {
         return new Bin(prefix, mask, left, right);
      }

      public long copy$default$1() {
         return this.prefix();
      }

      public long copy$default$2() {
         return this.mask();
      }

      public LongMap copy$default$3() {
         return this.left();
      }

      public LongMap copy$default$4() {
         return this.right();
      }

      public String productPrefix() {
         return "Bin";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.prefix();
            case 1:
               return this.mask();
            case 2:
               return this.left();
            case 3:
               return this.right();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "prefix";
            case 1:
               return "mask";
            case 2:
               return "left";
            case 3:
               return "right";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public Bin(final long prefix, final long mask, final LongMap left, final LongMap right) {
         this.prefix = prefix;
         this.mask = mask;
         this.left = left;
         this.right = right;
      }
   }

   public static class Bin$ implements Serializable {
      public static final Bin$ MODULE$ = new Bin$();

      public final String toString() {
         return "Bin";
      }

      public Bin apply(final long prefix, final long mask, final LongMap left, final LongMap right) {
         return new Bin(prefix, mask, left, right);
      }

      public Option unapply(final Bin x$0) {
         return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple4(x$0.prefix(), x$0.mask(), x$0.left(), x$0.right())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Bin$.class);
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
         return new ImmutableBuilder() {
            public <undefinedtype> addOne(final Tuple2 elem) {
               this.elems_$eq(((LongMap)this.elems()).$plus(elem));
               return this;
            }

            public {
               LongMap$ var10001 = LongMap$.MODULE$;
            }
         };
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

      public Builder newBuilder(final Object from) {
         LongMap$ var10000 = LongMap$.MODULE$;
         return new ImmutableBuilder() {
            public <undefinedtype> addOne(final Tuple2 elem) {
               this.elems_$eq(((LongMap)this.elems()).$plus(elem));
               return this;
            }

            public {
               LongMap$ var10001 = LongMap$.MODULE$;
            }
         };
      }

      public ToBuildFrom$() {
      }
   }
}
