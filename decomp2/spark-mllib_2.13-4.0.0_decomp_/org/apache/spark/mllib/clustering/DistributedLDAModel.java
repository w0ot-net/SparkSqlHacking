package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.NumericOps;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.EdgeContext;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.BoundedPriorityQueue;
import org.json4s.JValue;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011ee!B?\u007f\u0001\u0005M\u0001bCA\u000f\u0001\t\u0015\r\u0011\"\u0001\u007f\u0003?A!\"!\u0011\u0001\u0005\u0003\u0005\u000b\u0011BA\u0011\u0011-\t\u0019\u0005\u0001BC\u0002\u0013\u0005a0!\u0012\t\u0015\u0005\u001d\u0003A!A!\u0002\u0013\ti\u0003\u0003\u0006\u0002J\u0001\u0011)\u0019!C\u0001\u0003\u0017B!\"a\u001b\u0001\u0005\u0003\u0005\u000b\u0011BA'\u0011)\ty\u0007\u0001BC\u0002\u0013\u0005\u00111\n\u0005\u000b\u0003g\u0002!\u0011!Q\u0001\n\u00055\u0003BCA<\u0001\t\u0015\r\u0011\"\u0011\u0002z!Q\u0011Q\u0012\u0001\u0003\u0002\u0003\u0006I!a\u001f\t\u0015\u0005E\u0005A!b\u0001\n\u0003\n\u0019\n\u0003\u0006\u0002\u001e\u0002\u0011\t\u0011)A\u0005\u0003+CA\"!)\u0001\u0005\u000b\u0007I\u0011AA\u0003\u0003GC!\"a+\u0001\u0005\u0003\u0005\u000b\u0011BAS\u0011-\ti\u000b\u0001BC\u0002\u0013Ec0a%\t\u0015\u0005=\u0006A!A!\u0002\u0013\t)\n\u0003\u0007\u00022\u0002\u0011)\u0019!C\u0001\u0003\u000b\t\u0019\f\u0003\u0006\u0002N\u0002\u0011\t\u0011)A\u0005\u0003kC\u0001\"a4\u0001\t\u0003q\u0018\u0011\u001b\u0005\b\u0003_\u0004A\u0011AAy\u0011)\tY\u0010\u0001EC\u0002\u0013\u0005\u0013Q \u0005\b\u0005\u000f\u0001A\u0011\tB\u0005\u0011\u001d\u0011Y\u0002\u0001C\u0001\u0005;A!B!\r\u0001\u0011\u000b\u0007I\u0011\u0001B\u001a\u0011)\u0011I\u0005\u0001EC\u0002\u0013\u0005!1\n\u0005\u000b\u0005[\u0002\u0001R1A\u0005\u0002\u0005M\u0005B\u0003B9\u0001!\u0015\r\u0011\"\u0001\u0002\u0014\"9!Q\u000f\u0001\u0005\u0002\t]\u0004b\u0002B@\u0001\u0011\u0005!\u0011\u0011\u0005\b\u0005\u001f\u0003A\u0011\u0001BI\u0011\u001d\u0011Y\n\u0001C\u0001\u0005;CqAa*\u0001\t\u0003\u0012IkB\u0004\u0003FzD\tAa2\u0007\rut\b\u0012\u0001Be\u0011\u001d\tyM\tC\u0001\u0005;D!Ba8#\u0005\u0004%\tA`AJ\u0011!\u0011\tO\tQ\u0001\n\u0005Uua\u0002BrE!%!Q\u001d\u0004\b\u0005S\u0014\u0003\u0012\u0002Bv\u0011\u001d\tym\nC\u0001\u0005[D\u0011Ba<(\u0005\u0004%\tA!=\t\u0011\t]x\u0005)A\u0005\u0005gD\u0011B!?(\u0005\u0004%\tA!=\t\u0011\tmx\u0005)A\u0005\u0005g4aA!@(\u0001\n}\bBCA\"[\tU\r\u0011\"\u0001\u0002z!Q\u0011qI\u0017\u0003\u0012\u0003\u0006I!a\u001f\t\u000f\u0005=W\u0006\"\u0001\u0004\u001a!I1\u0011E\u0017\u0002\u0002\u0013\u000511\u0005\u0005\n\u0007Oi\u0013\u0013!C\u0001\u0007SA\u0011b!\u0010.\u0003\u0003%\tE!=\t\u0013\r}R&!A\u0005\u0002\u0005-\u0003\"CB![\u0005\u0005I\u0011AB\"\u0011%\u0019y%LA\u0001\n\u0003\u001a\t\u0006C\u0005\u0004`5\n\t\u0011\"\u0001\u0004b!I11N\u0017\u0002\u0002\u0013\u00053Q\u000e\u0005\n\u0007cj\u0013\u0011!C!\u0007gB\u0011b!\u001e.\u0003\u0003%\tea\u001e\t\u0013\reT&!A\u0005B\rmt!CB@O\u0005\u0005\t\u0012ABA\r%\u0011ipJA\u0001\u0012\u0003\u0019\u0019\tC\u0004\u0002Pv\"\taa'\t\u0013\rUT(!A\u0005F\r]\u0004\"CBO{\u0005\u0005I\u0011QBP\u0011%\u0019\u0019+PA\u0001\n\u0003\u001b)\u000bC\u0005\u00042v\n\t\u0011\"\u0003\u00044\u001a111X\u0014A\u0007{C!ba0D\u0005+\u0007I\u0011ABa\u0011)\u0019\u0019m\u0011B\tB\u0003%!Q\u0005\u0005\u000b\u0007\u000b\u001c%Q3A\u0005\u0002\u0005e\u0004BCBd\u0007\nE\t\u0015!\u0003\u0002|!9\u0011qZ\"\u0005\u0002\r%\u0007\"CB\u0011\u0007\u0006\u0005I\u0011ABi\u0011%\u00199cQI\u0001\n\u0003\u00199\u000eC\u0005\u0004\\\u000e\u000b\n\u0011\"\u0001\u0004*!I1QH\"\u0002\u0002\u0013\u0005#\u0011\u001f\u0005\n\u0007\u007f\u0019\u0015\u0011!C\u0001\u0003\u0017B\u0011b!\u0011D\u0003\u0003%\ta!8\t\u0013\r=3)!A\u0005B\rE\u0003\"CB0\u0007\u0006\u0005I\u0011ABq\u0011%\u0019YgQA\u0001\n\u0003\u001a)\u000fC\u0005\u0004r\r\u000b\t\u0011\"\u0011\u0004t!I1QO\"\u0002\u0002\u0013\u00053q\u000f\u0005\n\u0007s\u001a\u0015\u0011!C!\u0007S<\u0011b!<(\u0003\u0003E\taa<\u0007\u0013\rmv%!A\t\u0002\rE\bbBAh-\u0012\u00051\u0011 \u0005\n\u0007k2\u0016\u0011!C#\u0007oB\u0011b!(W\u0003\u0003%\tia?\t\u0013\r\rf+!A\u0005\u0002\u0012\u0005\u0001\"CBY-\u0006\u0005I\u0011BBZ\r\u0019!9a\n!\u0005\n!QA1\u0002/\u0003\u0016\u0004%\ta!1\t\u0015\u00115AL!E!\u0002\u0013\u0011)\u0003\u0003\u0006\u0005\u0010q\u0013)\u001a!C\u0001\u0007\u0003D!\u0002\"\u0005]\u0005#\u0005\u000b\u0011\u0002B\u0013\u0011)!\u0019\u0002\u0018BK\u0002\u0013\u0005\u00111\u0013\u0005\u000b\t+a&\u0011#Q\u0001\n\u0005U\u0005bBAh9\u0012\u0005Aq\u0003\u0005\n\u0007Ca\u0016\u0011!C\u0001\tCA\u0011ba\n]#\u0003%\taa6\t\u0013\rmG,%A\u0005\u0002\r]\u0007\"\u0003C\u00159F\u0005I\u0011\u0001C\u0016\u0011%\u0019i\u0004XA\u0001\n\u0003\u0012\t\u0010C\u0005\u0004@q\u000b\t\u0011\"\u0001\u0002L!I1\u0011\t/\u0002\u0002\u0013\u0005Aq\u0006\u0005\n\u0007\u001fb\u0016\u0011!C!\u0007#B\u0011ba\u0018]\u0003\u0003%\t\u0001b\r\t\u0013\r-D,!A\u0005B\u0011]\u0002\"CB99\u0006\u0005I\u0011IB:\u0011%\u0019)\bXA\u0001\n\u0003\u001a9\bC\u0005\u0004zq\u000b\t\u0011\"\u0011\u0005<\u001dIAqH\u0014\u0002\u0002#\u0005A\u0011\t\u0004\n\t\u000f9\u0013\u0011!E\u0001\t\u0007Bq!a4s\t\u0003!Y\u0005C\u0005\u0004vI\f\t\u0011\"\u0012\u0004x!I1Q\u0014:\u0002\u0002\u0013\u0005EQ\n\u0005\n\u0007G\u0013\u0018\u0011!CA\t+B\u0011b!-s\u0003\u0003%Iaa-\t\u000f\t\u001dv\u0005\"\u0001\u0005^!9A1O\u0014\u0005\u0002\u0011U\u0004b\u0002C:E\u0011\u0005CQ\u0011\u0005\u000b\t\u001b\u0013\u0013\u0013!C\u0001}\u0012-\u0002B\u0003CHEE\u0005I\u0011\u0001@\u0005\u0012\n\u0019B)[:ue&\u0014W\u000f^3e\u0019\u0012\u000bUj\u001c3fY*\u0019q0!\u0001\u0002\u0015\rdWo\u001d;fe&twM\u0003\u0003\u0002\u0004\u0005\u0015\u0011!B7mY&\u0014'\u0002BA\u0004\u0003\u0013\tQa\u001d9be.TA!a\u0003\u0002\u000e\u00051\u0011\r]1dQ\u0016T!!a\u0004\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\t)\u0002\u0005\u0003\u0002\u0018\u0005eQ\"\u0001@\n\u0007\u0005maP\u0001\u0005M\t\u0006ku\u000eZ3m\u0003\u00159'/\u00199i+\t\t\t\u0003\u0005\u0005\u0002$\u0005%\u0012QFA\u001e\u001b\t\t)C\u0003\u0003\u0002(\u0005\u0015\u0011AB4sCBD\u00070\u0003\u0003\u0002,\u0005\u0015\"!B$sCBD\u0007\u0003BA\u0018\u0003kqA!a\u0006\u00022%\u0019\u00111\u0007@\u0002\u00071#\u0015)\u0003\u0003\u00028\u0005e\"a\u0003+pa&\u001c7i\\;oiNT1!a\r\u007f!\u0011\ty#!\u0010\n\t\u0005}\u0012\u0011\b\u0002\u000b)>\\WM\\\"pk:$\u0018AB4sCBD\u0007%A\thY>\u0014\u0017\r\u001c+pa&\u001cGk\u001c;bYN,\"!!\f\u0002%\u001ddwNY1m)>\u0004\u0018n\u0019+pi\u0006d7\u000fI\u0001\u0002WV\u0011\u0011Q\n\t\u0005\u0003\u001f\n)&\u0004\u0002\u0002R)\u0011\u00111K\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003/\n\tFA\u0002J]RDS!BA.\u0003O\u0002B!!\u0018\u0002d5\u0011\u0011q\f\u0006\u0005\u0003C\n)!\u0001\u0006b]:|G/\u0019;j_:LA!!\u001a\u0002`\t)1+\u001b8dK\u0006\u0012\u0011\u0011N\u0001\u0006c9\u001ad\u0006M\u0001\u0003W\u0002BSABA.\u0003O\n\u0011B^8dC\n\u001c\u0016N_3)\u000b\u001d\tY&a\u001a\u0002\u0015Y|7-\u00192TSj,\u0007\u0005K\u0003\t\u00037\n9'\u0001\te_\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]V\u0011\u00111\u0010\t\u0005\u0003{\n\u0019)\u0004\u0002\u0002\u0000)!\u0011\u0011QA\u0001\u0003\u0019a\u0017N\\1mO&!\u0011QQA@\u0005\u00191Vm\u0019;pe\"*\u0011\"a\u0017\u0002\n\u0006\u0012\u00111R\u0001\u0006c9*d\u0006M\u0001\u0012I>\u001c7i\u001c8dK:$(/\u0019;j_:\u0004\u0003&\u0002\u0006\u0002\\\u0005%\u0015A\u0005;pa&\u001c7i\u001c8dK:$(/\u0019;j_:,\"!!&\u0011\t\u0005=\u0013qS\u0005\u0005\u00033\u000b\tF\u0001\u0004E_V\u0014G.\u001a\u0015\u0006\u0017\u0005m\u0013\u0011R\u0001\u0014i>\u0004\u0018nY\"p]\u000e,g\u000e\u001e:bi&|g\u000e\t\u0015\u0006\u0019\u0005m\u0013\u0011R\u0001\u000fSR,'/\u0019;j_:$\u0016.\\3t+\t\t)\u000b\u0005\u0004\u0002P\u0005\u001d\u0016QS\u0005\u0005\u0003S\u000b\tFA\u0003BeJ\f\u00170A\bji\u0016\u0014\u0018\r^5p]RKW.Z:!\u0003)9\u0017-\\7b'\"\f\u0007/Z\u0001\fO\u0006lW.Y*iCB,\u0007%A\bdQ\u0016\u001c7\u000e]8j]R4\u0015\u000e\\3t+\t\t)\f\u0005\u0004\u0002P\u0005\u001d\u0016q\u0017\t\u0005\u0003s\u000b9M\u0004\u0003\u0002<\u0006\r\u0007\u0003BA_\u0003#j!!a0\u000b\t\u0005\u0005\u0017\u0011C\u0001\u0007yI|w\u000e\u001e \n\t\u0005\u0015\u0017\u0011K\u0001\u0007!J,G-\u001a4\n\t\u0005%\u00171\u001a\u0002\u0007'R\u0014\u0018N\\4\u000b\t\u0005\u0015\u0017\u0011K\u0001\u0011G\",7m\u001b9pS:$h)\u001b7fg\u0002\na\u0001P5oSRtD\u0003FAj\u0003+\f9.!7\u0002^\u0006\u0005\u0018Q]Au\u0003W\fi\u000fE\u0002\u0002\u0018\u0001Aq!!\b\u0014\u0001\u0004\t\t\u0003C\u0004\u0002DM\u0001\r!!\f\t\u000f\u0005%3\u00031\u0001\u0002N!2\u0011\u0011\\A.\u0003OBq!a\u001c\u0014\u0001\u0004\ti\u0005\u000b\u0004\u0002^\u0006m\u0013q\r\u0005\b\u0003o\u001a\u0002\u0019AA>Q\u0019\t\t/a\u0017\u0002\n\"9\u0011\u0011S\nA\u0002\u0005U\u0005FBAs\u00037\nI\tC\u0004\u0002\"N\u0001\r!!*\t\u0013\u000556\u0003%AA\u0002\u0005U\u0005\"CAY'A\u0005\t\u0019AA[\u0003\u001d!x\u000eT8dC2,\"!a=\u0011\t\u0005]\u0011Q_\u0005\u0004\u0003ot(!\u0004'pG\u0006dG\nR!N_\u0012,G\u000eK\u0003\u0015\u00037\n9'\u0001\u0007u_BL7m]'biJL\u00070\u0006\u0002\u0002\u0000B!\u0011Q\u0010B\u0001\u0013\u0011\u0011\u0019!a \u0003\r5\u000bGO]5yQ\u0015)\u00121LA4\u00039!Wm]2sS\n,Gk\u001c9jGN$BAa\u0003\u0003\u0016A1\u0011qJAT\u0005\u001b\u0001\u0002\"a\u0014\u0003\u0010\tM\u0011QU\u0005\u0005\u0005#\t\tF\u0001\u0004UkBdWM\r\t\u0007\u0003\u001f\n9+!\u0014\t\u000f\t]a\u00031\u0001\u0002N\u0005\u0001R.\u0019=UKJl7\u000fU3s)>\u0004\u0018n\u0019\u0015\u0006-\u0005m\u0013qM\u0001\u0015i>\u0004Hi\\2v[\u0016tGo\u001d)feR{\u0007/[2\u0015\t\t}!1\u0006\t\u0007\u0003\u001f\n9K!\t\u0011\u0011\u0005=#q\u0002B\u0012\u0003K\u0003b!a\u0014\u0002(\n\u0015\u0002\u0003BA(\u0005OIAA!\u000b\u0002R\t!Aj\u001c8h\u0011\u001d\u0011ic\u0006a\u0001\u0003\u001b\nA#\\1y\t>\u001cW/\\3oiN\u0004VM\u001d+pa&\u001c\u0007&B\f\u0002\\\u0005%\u0015\u0001\u0005;pa&\u001c\u0017i]:jO:lWM\u001c;t+\t\u0011)\u0004\u0005\u0004\u00038\tu\"\u0011I\u0007\u0003\u0005sQAAa\u000f\u0002\u0006\u0005\u0019!\u000f\u001a3\n\t\t}\"\u0011\b\u0002\u0004%\u0012#\u0005CCA(\u0005\u0007\u0012)Ca\u0005\u0003\u0014%!!QIA)\u0005\u0019!V\u000f\u001d7fg!*\u0001$a\u0017\u0002\n\u0006!\".\u0019<b)>\u0004\u0018nY!tg&<g.\\3oiN,\"A!\u0014\u0011\r\t=#\u0011\fB/\u001b\t\u0011\tF\u0003\u0003\u0003T\tU\u0013\u0001\u00026bm\u0006TAAa\u0016\u0002\u0006\u0005\u0019\u0011\r]5\n\t\tm#\u0011\u000b\u0002\b\u0015\u00064\u0018M\u0015#E!)\tyEa\u0011\u0003`\tM!1\u0003\t\u0005\u0005C\u0012I'\u0004\u0002\u0003d)!!Q\rB4\u0003\u0011a\u0017M\\4\u000b\u0005\tM\u0013\u0002\u0002B\u0015\u0005GBS!GA.\u0003\u0013\u000bQ\u0002\\8h\u0019&\\W\r\\5i_>$\u0007&\u0002\u000e\u0002\\\u0005\u001d\u0014\u0001\u00037pOB\u0013\u0018n\u001c:)\u000bm\tY&a\u001a\u0002%Q|\u0007/[2ESN$(/\u001b2vi&|gn]\u000b\u0003\u0005s\u0002bAa\u000e\u0003>\tm\u0004\u0003CA(\u0005\u001f\u0011)#a\u001f)\u000bq\tY&a\u001a\u0002-)\fg/\u0019+pa&\u001cG)[:ue&\u0014W\u000f^5p]N,\"Aa!\u0011\u0011\t=#Q\u0011B0\u0003wJAAa\"\u0003R\tY!*\u0019<b!\u0006L'O\u0015#EQ\u0015i\u00121\fBFC\t\u0011i)A\u00032]Qr\u0013'\u0001\u000bu_B$v\u000e]5dgB+'\u000fR8dk6,g\u000e\u001e\u000b\u0005\u0005'\u00139\n\u0005\u0004\u00038\tu\"Q\u0013\t\u000b\u0003\u001f\u0012\u0019E!\n\u0003\u0014\u0005\u0015\u0006bBA%=\u0001\u0007\u0011Q\n\u0015\u0006=\u0005m\u0013\u0011R\u0001\u0019U\u00064\u0018\rV8q)>\u0004\u0018nY:QKJ$unY;nK:$H\u0003\u0002BP\u0005G\u0003bAa\u0014\u0003Z\t\u0005\u0006CCA(\u0005\u0007\u0012yFa\u0005\u0002&\"9\u0011\u0011J\u0010A\u0002\u00055\u0003&B\u0010\u0002\\\u0005%\u0015\u0001B:bm\u0016$bAa+\u00032\nu\u0006\u0003BA(\u0005[KAAa,\u0002R\t!QK\\5u\u0011\u001d\u0011\u0019\f\ta\u0001\u0005k\u000b!a]2\u0011\t\t]&\u0011X\u0007\u0003\u0003\u000bIAAa/\u0002\u0006\ta1\u000b]1sW\u000e{g\u000e^3yi\"9!q\u0018\u0011A\u0002\u0005]\u0016\u0001\u00029bi\"DS\u0001IA.\u0003\u0013CS\u0001AA.\u0003O\n1\u0003R5tiJL'-\u001e;fI2#\u0015)T8eK2\u00042!a\u0006#'\u0015\u0011#1\u001aBi!\u0011\tyE!4\n\t\t=\u0017\u0011\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\r\tM'\u0011\\Aj\u001b\t\u0011)N\u0003\u0003\u0003X\u0006\u0005\u0011\u0001B;uS2LAAa7\u0003V\n1Aj\\1eKJ$\"Aa2\u0002#\u0011,g-Y;mi\u001e\u000bW.\\1TQ\u0006\u0004X-\u0001\neK\u001a\fW\u000f\u001c;HC6l\u0017m\u00155ba\u0016\u0004\u0013\u0001D*bm\u0016du.\u00193Wc}\u0003\u0004c\u0001BtO5\t!E\u0001\u0007TCZ,Gj\\1e-Fz\u0006gE\u0002(\u0005\u0017$\"A!:\u0002#QD\u0017n\u001d$pe6\fGOV3sg&|g.\u0006\u0002\u0003tB!!\u0011\rB{\u0013\u0011\tIMa\u0019\u0002%QD\u0017n\u001d$pe6\fGOV3sg&|g\u000eI\u0001\u000ei\"L7o\u00117bgNt\u0015-\\3\u0002\u001dQD\u0017n]\"mCN\u001ch*Y7fA\t!A)\u0019;b'\u001di#1ZB\u0001\u0007\u000f\u0001B!a\u0014\u0004\u0004%!1QAA)\u0005\u001d\u0001&o\u001c3vGR\u0004Ba!\u0003\u0004\u00149!11BB\b\u001d\u0011\til!\u0004\n\u0005\u0005M\u0013\u0002BB\t\u0003#\nq\u0001]1dW\u0006<W-\u0003\u0003\u0004\u0016\r]!\u0001D*fe&\fG.\u001b>bE2,'\u0002BB\t\u0003#\"Baa\u0007\u0004 A\u00191QD\u0017\u000e\u0003\u001dBq!a\u00111\u0001\u0004\tY(\u0001\u0003d_BLH\u0003BB\u000e\u0007KA\u0011\"a\u00112!\u0003\u0005\r!a\u001f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u001111\u0006\u0016\u0005\u0003w\u001aic\u000b\u0002\u00040A!1\u0011GB\u001d\u001b\t\u0019\u0019D\u0003\u0003\u00046\r]\u0012!C;oG\",7m[3e\u0015\u0011\t\t'!\u0015\n\t\rm21\u0007\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\r\u001531\n\t\u0005\u0003\u001f\u001a9%\u0003\u0003\u0004J\u0005E#aA!os\"I1QJ\u001b\u0002\u0002\u0003\u0007\u0011QJ\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\rM\u0003CBB+\u00077\u001a)%\u0004\u0002\u0004X)!1\u0011LA)\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0007;\u001a9F\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BB2\u0007S\u0002B!a\u0014\u0004f%!1qMA)\u0005\u001d\u0011un\u001c7fC:D\u0011b!\u00148\u0003\u0003\u0005\ra!\u0012\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005g\u001cy\u0007C\u0005\u0004Na\n\t\u00111\u0001\u0002N\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002N\u0005AAo\\*ue&tw\r\u0006\u0002\u0003t\u00061Q-];bYN$Baa\u0019\u0004~!I1QJ\u001e\u0002\u0002\u0003\u00071QI\u0001\u0005\t\u0006$\u0018\rE\u0002\u0004\u001eu\u001aR!PBC\u0007#\u0003\u0002ba\"\u0004\u000e\u0006m41D\u0007\u0003\u0007\u0013SAaa#\u0002R\u00059!/\u001e8uS6,\u0017\u0002BBH\u0007\u0013\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\u0019\u0019j!'\u000e\u0005\rU%\u0002BBL\u0005O\n!![8\n\t\rU1Q\u0013\u000b\u0003\u0007\u0003\u000bQ!\u00199qYf$Baa\u0007\u0004\"\"9\u00111\t!A\u0002\u0005m\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0007O\u001bi\u000b\u0005\u0004\u0002P\r%\u00161P\u0005\u0005\u0007W\u000b\tF\u0001\u0004PaRLwN\u001c\u0005\n\u0007_\u000b\u0015\u0011!a\u0001\u00077\t1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019)\f\u0005\u0003\u0003b\r]\u0016\u0002BB]\u0005G\u0012aa\u00142kK\u000e$(A\u0003,feR,\u0007\u0010R1uCN91Ia3\u0004\u0002\r\u001d\u0011AA5e+\t\u0011)#A\u0002jI\u0002\nA\u0002^8qS\u000e<V-[4iiN\fQ\u0002^8qS\u000e<V-[4iiN\u0004CCBBf\u0007\u001b\u001cy\rE\u0002\u0004\u001e\rCqaa0I\u0001\u0004\u0011)\u0003C\u0004\u0004F\"\u0003\r!a\u001f\u0015\r\r-71[Bk\u0011%\u0019y,\u0013I\u0001\u0002\u0004\u0011)\u0003C\u0005\u0004F&\u0003\n\u00111\u0001\u0002|U\u00111\u0011\u001c\u0016\u0005\u0005K\u0019i#\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0015\t\r\u00153q\u001c\u0005\n\u0007\u001br\u0015\u0011!a\u0001\u0003\u001b\"Baa\u0019\u0004d\"I1Q\n)\u0002\u0002\u0003\u00071Q\t\u000b\u0005\u0005g\u001c9\u000fC\u0005\u0004NE\u000b\t\u00111\u0001\u0002NQ!11MBv\u0011%\u0019i\u0005VA\u0001\u0002\u0004\u0019)%\u0001\u0006WKJ$X\r\u001f#bi\u0006\u00042a!\bW'\u0015161_BI!)\u00199i!>\u0003&\u0005m41Z\u0005\u0005\u0007o\u001cIIA\tBEN$(/Y2u\rVt7\r^5p]J\"\"aa<\u0015\r\r-7Q`B\u0000\u0011\u001d\u0019y,\u0017a\u0001\u0005KAqa!2Z\u0001\u0004\tY\b\u0006\u0003\u0005\u0004\u0011\u0015\u0001CBA(\u0007S\u0013Y\bC\u0005\u00040j\u000b\t\u00111\u0001\u0004L\nAQ\tZ4f\t\u0006$\u0018mE\u0004]\u0005\u0017\u001c\taa\u0002\u0002\u000bM\u00148-\u00133\u0002\rM\u00148-\u00133!\u0003\u0015!7\u000f^%e\u0003\u0019!7\u000f^%eA\u0005YAo\\6f]\u000e{WO\u001c;t\u00031!xn[3o\u0007>,h\u000e^:!)!!I\u0002b\u0007\u0005\u001e\u0011}\u0001cAB\u000f9\"9A1B2A\u0002\t\u0015\u0002b\u0002C\bG\u0002\u0007!Q\u0005\u0005\b\t'\u0019\u0007\u0019AAK)!!I\u0002b\t\u0005&\u0011\u001d\u0002\"\u0003C\u0006IB\u0005\t\u0019\u0001B\u0013\u0011%!y\u0001\u001aI\u0001\u0002\u0004\u0011)\u0003C\u0005\u0005\u0014\u0011\u0004\n\u00111\u0001\u0002\u0016\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTC\u0001C\u0017U\u0011\t)j!\f\u0015\t\r\u0015C\u0011\u0007\u0005\n\u0007\u001bR\u0017\u0011!a\u0001\u0003\u001b\"Baa\u0019\u00056!I1Q\n7\u0002\u0002\u0003\u00071Q\t\u000b\u0005\u0005g$I\u0004C\u0005\u0004N5\f\t\u00111\u0001\u0002NQ!11\rC\u001f\u0011%\u0019i\u0005]A\u0001\u0002\u0004\u0019)%\u0001\u0005FI\u001e,G)\u0019;b!\r\u0019iB]\n\u0006e\u0012\u00153\u0011\u0013\t\r\u0007\u000f#9E!\n\u0003&\u0005UE\u0011D\u0005\u0005\t\u0013\u001aIIA\tBEN$(/Y2u\rVt7\r^5p]N\"\"\u0001\"\u0011\u0015\u0011\u0011eAq\nC)\t'Bq\u0001b\u0003v\u0001\u0004\u0011)\u0003C\u0004\u0005\u0010U\u0004\rA!\n\t\u000f\u0011MQ\u000f1\u0001\u0002\u0016R!Aq\u000bC.!\u0019\tye!+\u0005ZAQ\u0011q\nB\"\u0005K\u0011)#!&\t\u0013\r=f/!AA\u0002\u0011eAC\u0006BV\t?\"\t\u0007b\u0019\u0005f\u0011\u001dD\u0011\u000eC6\t[\"y\u0007\"\u001d\t\u000f\tM\u0006\u00101\u0001\u00036\"9!q\u0018=A\u0002\u0005]\u0006bBA\u000fq\u0002\u0007\u0011\u0011\u0005\u0005\b\u0003\u0007B\b\u0019AA\u0017\u0011\u001d\tI\u0005\u001fa\u0001\u0003\u001bBq!a\u001cy\u0001\u0004\ti\u0005C\u0004\u0002xa\u0004\r!a\u001f\t\u000f\u0005E\u0005\u00101\u0001\u0002\u0016\"9\u0011\u0011\u0015=A\u0002\u0005\u0015\u0006bBAWq\u0002\u0007\u0011QS\u0001\u0005Y>\fG\r\u0006\t\u0002T\u0012]D\u0011\u0010C>\t{\"y\b\"!\u0005\u0004\"9!1W=A\u0002\tU\u0006b\u0002B`s\u0002\u0007\u0011q\u0017\u0005\b\u0003_J\b\u0019AA'\u0011\u001d\t9(\u001fa\u0001\u0003wBq!!%z\u0001\u0004\t)\nC\u0004\u0002\"f\u0004\r!!*\t\u000f\u00055\u0016\u00101\u0001\u0002\u0016R1\u00111\u001bCD\t\u0013CqAa-{\u0001\u0004\u0011)\fC\u0004\u0003@j\u0004\r!a.)\u000bi\fY&!#\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00139\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%sU\u0011A1\u0013\u0016\u0005\u0003k\u001bi\u0003K\u0003#\u00037\nI\tK\u0003\"\u00037\nI\t"
)
public class DistributedLDAModel extends LDAModel {
   private Matrix topicsMatrix;
   private RDD topicAssignments;
   private JavaRDD javaTopicAssignments;
   private double logLikelihood;
   private double logPrior;
   private final Graph graph;
   private final DenseVector globalTopicTotals;
   private final int k;
   private final int vocabSize;
   private final Vector docConcentration;
   private final double topicConcentration;
   private final double[] iterationTimes;
   private final double gammaShape;
   private final String[] checkpointFiles;
   private volatile byte bitmap$0;

   public static DistributedLDAModel load(final SparkContext sc, final String path) {
      return DistributedLDAModel$.MODULE$.load(sc, path);
   }

   public Graph graph() {
      return this.graph;
   }

   public DenseVector globalTopicTotals() {
      return this.globalTopicTotals;
   }

   public int k() {
      return this.k;
   }

   public int vocabSize() {
      return this.vocabSize;
   }

   public Vector docConcentration() {
      return this.docConcentration;
   }

   public double topicConcentration() {
      return this.topicConcentration;
   }

   public double[] iterationTimes() {
      return this.iterationTimes;
   }

   public double gammaShape() {
      return this.gammaShape;
   }

   public String[] checkpointFiles() {
      return this.checkpointFiles;
   }

   public LocalLDAModel toLocal() {
      return new LocalLDAModel(this.topicsMatrix(), this.docConcentration(), this.topicConcentration(), this.gammaShape());
   }

   private Matrix topicsMatrix$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            Tuple2[] termTopicCounts = (Tuple2[])this.graph().vertices().filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$topicsMatrix$1(x$5))).map((x0$1) -> {
               if (x0$1 != null) {
                  long termIndex = x0$1._1$mcJ$sp();
                  DenseVector cnts = (DenseVector)x0$1._2();
                  return new Tuple2(BoxesRunTime.boxToInteger(LDA$.MODULE$.index2term(termIndex)), cnts);
               } else {
                  throw new MatchError(x0$1);
               }
            }, .MODULE$.apply(Tuple2.class)).collect();
            DenseMatrix brzTopics = breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(this.vocabSize(), this.k(), .MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])termTopicCounts), (x0$2) -> {
               $anonfun$topicsMatrix$3(this, brzTopics, x0$2);
               return BoxedUnit.UNIT;
            });
            this.topicsMatrix = Matrices$.MODULE$.fromBreeze(brzTopics);
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.topicsMatrix;
   }

   public Matrix topicsMatrix() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.topicsMatrix$lzycompute() : this.topicsMatrix;
   }

   public Tuple2[] describeTopics(final int maxTermsPerTopic) {
      int numTopics = this.k();
      DenseVector N_k = this.globalTopicTotals();
      VertexRDD qual$1 = this.graph().vertices().filter((v) -> BoxesRunTime.boxToBoolean($anonfun$describeTopics$3(v)));
      Function1 x$1 = (termVertices) -> {
         BoundedPriorityQueue[] queues = (BoundedPriorityQueue[])scala.Array..MODULE$.fill(numTopics, () -> new BoundedPriorityQueue(maxTermsPerTopic, scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$, scala.math.Ordering.Int..MODULE$)), .MODULE$.apply(BoundedPriorityQueue.class));
         termVertices.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$describeTopics$6(check$ifrefutable$1))).foreach((x$6) -> {
            $anonfun$describeTopics$7(numTopics, queues, N_k, x$6);
            return BoxedUnit.UNIT;
         });
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new BoundedPriorityQueue[][]{queues})));
      };
      boolean x$2 = qual$1.mapPartitions$default$2();
      BoundedPriorityQueue[] topicsInQueues = (BoundedPriorityQueue[])qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(BoundedPriorityQueue.class))).reduce((q1, q2) -> {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])q1), scala.Predef..MODULE$.wrapRefArray((Object[])q2))), (x0$1) -> {
            if (x0$1 != null) {
               BoundedPriorityQueue a = (BoundedPriorityQueue)x0$1._1();
               BoundedPriorityQueue b = (BoundedPriorityQueue)x0$1._2();
               return (BoundedPriorityQueue)a.$plus$plus$eq(b);
            } else {
               throw new MatchError(x0$1);
            }
         });
         return q1;
      });
      return (Tuple2[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])topicsInQueues), (q) -> {
         Tuple2 var3 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(q.toArray(.MODULE$.apply(Tuple2.class))), (x$7) -> BoxesRunTime.boxToDouble($anonfun$describeTopics$11(x$7)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)), scala.Predef..MODULE$.$conforms(), .MODULE$.Double(), .MODULE$.Int());
         if (var3 != null) {
            double[] termWeights = (double[])var3._1();
            int[] terms = (int[])var3._2();
            Tuple2 var2 = new Tuple2(termWeights, terms);
            double[] termWeights = (double[])var2._1();
            int[] terms = (int[])var2._2();
            return new Tuple2(terms, termWeights);
         } else {
            throw new MatchError(var3);
         }
      }, .MODULE$.apply(Tuple2.class));
   }

   public Tuple2[] topDocumentsPerTopic(final int maxDocumentsPerTopic) {
      int numTopics = this.k();
      RDD qual$1 = this.topicDistributions();
      Function1 x$1 = (docVertices) -> {
         BoundedPriorityQueue[] queues = (BoundedPriorityQueue[])scala.Array..MODULE$.fill(numTopics, () -> new BoundedPriorityQueue(maxDocumentsPerTopic, scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$, scala.math.Ordering.Long..MODULE$)), .MODULE$.apply(BoundedPriorityQueue.class));
         docVertices.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$topDocumentsPerTopic$3(check$ifrefutable$2))).foreach((x$9) -> {
            $anonfun$topDocumentsPerTopic$4(numTopics, queues, x$9);
            return BoxedUnit.UNIT;
         });
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new BoundedPriorityQueue[][]{queues})));
      };
      boolean x$2 = qual$1.mapPartitions$default$2();
      RDD qual$2 = qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(BoundedPriorityQueue.class)));
      Function2 x$3 = (q1, q2) -> {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])q1), scala.Predef..MODULE$.wrapRefArray((Object[])q2))), (x0$1) -> {
            if (x0$1 != null) {
               BoundedPriorityQueue a = (BoundedPriorityQueue)x0$1._1();
               BoundedPriorityQueue b = (BoundedPriorityQueue)x0$1._2();
               return (BoundedPriorityQueue)a.$plus$plus$eq(b);
            } else {
               throw new MatchError(x0$1);
            }
         });
         return q1;
      };
      int x$4 = qual$2.treeReduce$default$2();
      BoundedPriorityQueue[] topicsInQueues = (BoundedPriorityQueue[])qual$2.treeReduce(x$3, x$4);
      return (Tuple2[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])topicsInQueues), (q) -> {
         Tuple2 var3 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(q.toArray(.MODULE$.apply(Tuple2.class))), (x$10) -> BoxesRunTime.boxToDouble($anonfun$topDocumentsPerTopic$8(x$10)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)), scala.Predef..MODULE$.$conforms(), .MODULE$.Double(), .MODULE$.Long());
         if (var3 != null) {
            double[] docTopics = (double[])var3._1();
            long[] docs = (long[])var3._2();
            Tuple2 var2 = new Tuple2(docTopics, docs);
            double[] docTopics = (double[])var2._1();
            long[] docs = (long[])var2._2();
            return new Tuple2(docs, docTopics);
         } else {
            throw new MatchError(var3);
         }
      }, .MODULE$.apply(Tuple2.class));
   }

   private RDD topicAssignments$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            double eta = this.topicConcentration();
            int W = this.vocabSize();
            double alpha = this.docConcentration().apply(0);
            DenseVector N_k = this.globalTopicTotals();
            Function1 sendMsg = (edgeContext) -> {
               $anonfun$topicAssignments$1(N_k, W, eta, alpha, edgeContext);
               return BoxedUnit.UNIT;
            };
            Function2 mergeMsg = (terms_topics0, terms_topics1) -> new Tuple2(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.intArrayOps((int[])terms_topics0._1()), terms_topics1._1(), .MODULE$.Int()), scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.intArrayOps((int[])terms_topics0._2()), terms_topics1._2(), .MODULE$.Int()));
            VertexRDD perDocAssignments = this.graph().aggregateMessages(sendMsg, mergeMsg, this.graph().aggregateMessages$default$3(), .MODULE$.apply(Tuple2.class)).filter((v) -> BoxesRunTime.boxToBoolean($anonfun$topicAssignments$3(v)));
            this.topicAssignments = perDocAssignments.map((x0$1) -> {
               if (x0$1 != null) {
                  long docID = x0$1._1$mcJ$sp();
                  Tuple2 var6 = (Tuple2)x0$1._2();
                  if (true && var6 != null) {
                     int[] terms = (int[])var6._1();
                     int[] topics = (int[])var6._2();
                     if (terms != null && topics != null) {
                        Tuple2 var14 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.intArrayOps(terms), scala.Predef..MODULE$.wrapIntArray(topics))), (x$12) -> BoxesRunTime.boxToInteger($anonfun$topicAssignments$5(x$12)), scala.math.Ordering.Int..MODULE$)), scala.Predef..MODULE$.$conforms(), .MODULE$.Int(), .MODULE$.Int());
                        if (var14 != null) {
                           int[] sortedTerms = (int[])var14._1();
                           int[] sortedTopics = (int[])var14._2();
                           Tuple2 var13 = new Tuple2(sortedTerms, sortedTopics);
                           int[] sortedTerms = (int[])var13._1();
                           int[] sortedTopicsx = (int[])var13._2();
                           return new Tuple3(BoxesRunTime.boxToLong(docID), sortedTerms, sortedTopicsx);
                        }

                        throw new MatchError(var14);
                     }
                  }
               }

               throw new MatchError(x0$1);
            }, .MODULE$.apply(Tuple3.class));
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var12) {
         throw var12;
      }

      return this.topicAssignments;
   }

   public RDD topicAssignments() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.topicAssignments$lzycompute() : this.topicAssignments;
   }

   private JavaRDD javaTopicAssignments$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.javaTopicAssignments = this.topicAssignments().toJavaRDD();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.javaTopicAssignments;
   }

   public JavaRDD javaTopicAssignments() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.javaTopicAssignments$lzycompute() : this.javaTopicAssignments;
   }

   private double logLikelihood$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            double alpha = this.docConcentration().apply(0);
            double eta = this.topicConcentration();
            scala.Predef..MODULE$.assert(eta > (double)1.0F);
            scala.Predef..MODULE$.assert(alpha > (double)1.0F);
            DenseVector N_k = this.globalTopicTotals();
            DenseVector smoothed_N_k = (DenseVector)N_k.$plus(BoxesRunTime.boxToDouble((double)this.vocabSize() * (eta - (double)1.0F)), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd());
            Function1 sendMsg = (edgeContext) -> {
               $anonfun$logLikelihood$1(eta, alpha, smoothed_N_k, edgeContext);
               return BoxedUnit.UNIT;
            };
            this.logLikelihood = BoxesRunTime.unboxToDouble(this.graph().aggregateMessages(sendMsg, (JFunction2.mcDDD.sp)(x$14, x$15) -> x$14 + x$15, this.graph().aggregateMessages$default$3(), .MODULE$.Double()).map((x$16) -> BoxesRunTime.boxToDouble($anonfun$logLikelihood$3(x$16)), .MODULE$.Double()).fold(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(x$17, x$18) -> x$17 + x$18));
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var10) {
         throw var10;
      }

      return this.logLikelihood;
   }

   public double logLikelihood() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.logLikelihood$lzycompute() : this.logLikelihood;
   }

   private double logPrior$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            double alpha = this.docConcentration().apply(0);
            double eta = this.topicConcentration();
            DenseVector N_k = this.globalTopicTotals();
            DenseVector smoothed_N_k = (DenseVector)N_k.$plus(BoxesRunTime.boxToDouble((double)this.vocabSize() * (eta - (double)1.0F)), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd());
            Function2 seqOp = (x0$1, x1$1) -> BoxesRunTime.boxToDouble($anonfun$logPrior$1(eta, smoothed_N_k, alpha, BoxesRunTime.unboxToDouble(x0$1), x1$1));
            this.logPrior = BoxesRunTime.unboxToDouble(this.graph().vertices().aggregate(BoxesRunTime.boxToDouble((double)0.0F), seqOp, (JFunction2.mcDDD.sp)(x$19, x$20) -> x$19 + x$20, .MODULE$.Double()));
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var10) {
         throw var10;
      }

      return this.logPrior;
   }

   public double logPrior() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.logPrior$lzycompute() : this.logPrior;
   }

   public RDD topicDistributions() {
      return this.graph().vertices().filter((v) -> BoxesRunTime.boxToBoolean($anonfun$topicDistributions$2(v))).map((x0$1) -> {
         if (x0$1 != null) {
            long docID = x0$1._1$mcJ$sp();
            DenseVector topicCounts = (DenseVector)x0$1._2();
            return new Tuple2(BoxesRunTime.boxToLong(docID), Vectors$.MODULE$.fromBreeze((breeze.linalg.Vector)breeze.linalg.normalize..MODULE$.apply(topicCounts, BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.normalize..MODULE$.normalizeDoubleImpl(breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())))));
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class));
   }

   public JavaPairRDD javaTopicDistributions() {
      return org.apache.spark.api.java.JavaPairRDD..MODULE$.fromRDD(this.topicDistributions(), .MODULE$.apply(Long.class), .MODULE$.apply(Vector.class));
   }

   public RDD topTopicsPerDocument(final int k) {
      return this.graph().vertices().filter((v) -> BoxesRunTime.boxToBoolean($anonfun$topTopicsPerDocument$1(v))).map((x0$1) -> {
         if (x0$1 != null) {
            long docID = x0$1._1$mcJ$sp();
            DenseVector topicCounts = (DenseVector)x0$1._2();
            IndexedSeq topIndices = (IndexedSeq)breeze.linalg.argtopk..MODULE$.apply(topicCounts, BoxesRunTime.boxToInteger(k), breeze.linalg.argtopk..MODULE$.argtopkDenseVector(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
            double sumCounts = BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(topicCounts, breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues())));
            double[] weights = sumCounts != (double)0 ? (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(((breeze.linalg.Vector)topicCounts.apply(topIndices, breeze.linalg.operators.HasOps..MODULE$.canSliceTensor(.MODULE$.Double()))).toArray$mcD$sp(.MODULE$.Double())), (JFunction1.mcDD.sp)(x$21) -> x$21 / sumCounts, .MODULE$.Double()) : ((breeze.linalg.Vector)topicCounts.apply(topIndices, breeze.linalg.operators.HasOps..MODULE$.canSliceTensor(.MODULE$.Double()))).toArray$mcD$sp(.MODULE$.Double());
            return new Tuple3(BoxesRunTime.boxToLong(docID), topIndices.toArray(.MODULE$.Int()), weights);
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple3.class));
   }

   public JavaRDD javaTopTopicsPerDocument(final int k) {
      RDD topics = this.topTopicsPerDocument(k);
      return topics.toJavaRDD();
   }

   public void save(final SparkContext sc, final String path) {
      DistributedLDAModel.SaveLoadV1_0$.MODULE$.save(sc, path, this.graph(), this.globalTopicTotals(), this.k(), this.vocabSize(), this.docConcentration(), this.topicConcentration(), this.iterationTimes(), this.gammaShape());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$topicsMatrix$1(final Tuple2 x$5) {
      return x$5._1$mcJ$sp() < 0L;
   }

   // $FF: synthetic method
   public static final void $anonfun$topicsMatrix$3(final DistributedLDAModel $this, final DenseMatrix brzTopics$3, final Tuple2 x0$2) {
      if (x0$2 == null) {
         throw new MatchError(x0$2);
      } else {
         int term = x0$2._1$mcI$sp();
         DenseVector cnts = (DenseVector)x0$2._2();

         for(int j = 0; j < $this.k(); ++j) {
            brzTopics$3.update$mcD$sp(term, j, cnts.apply$mcD$sp(j));
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$describeTopics$3(final Tuple2 v) {
      return LDA$.MODULE$.isTermVertex(v);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$describeTopics$6(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$describeTopics$7(final int numTopics$1, final BoundedPriorityQueue[] queues$1, final DenseVector N_k$1, final Tuple2 x$6) {
      if (x$6 == null) {
         throw new MatchError(x$6);
      } else {
         long termId = x$6._1$mcJ$sp();
         DenseVector n_wk = (DenseVector)x$6._2();

         for(int topic = 0; topic < numTopics$1; ++topic) {
            queues$1[topic].$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(n_wk.apply$mcD$sp(topic) / N_k$1.apply$mcD$sp(topic))), BoxesRunTime.boxToInteger(LDA$.MODULE$.index2term((long)((int)termId)))));
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$describeTopics$11(final Tuple2 x$7) {
      return -x$7._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$topDocumentsPerTopic$3(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$topDocumentsPerTopic$4(final int numTopics$2, final BoundedPriorityQueue[] queues$2, final Tuple2 x$9) {
      if (x$9 == null) {
         throw new MatchError(x$9);
      } else {
         long docId = x$9._1$mcJ$sp();
         Vector docTopics = (Vector)x$9._2();

         for(int topic = 0; topic < numTopics$2; ++topic) {
            queues$2[topic].$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(docTopics.apply(topic))), BoxesRunTime.boxToLong(docId)));
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$topDocumentsPerTopic$8(final Tuple2 x$10) {
      return -x$10._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$topicAssignments$1(final DenseVector N_k$2, final int W$1, final double eta$1, final double alpha$1, final EdgeContext edgeContext) {
      DenseVector scaledTopicDistribution = LDA$.MODULE$.computePTopic((DenseVector)edgeContext.srcAttr(), (DenseVector)edgeContext.dstAttr(), N_k$2, W$1, eta$1, alpha$1);
      int topTopic = BoxesRunTime.unboxToInt(breeze.linalg.argmax..MODULE$.apply(scaledTopicDistribution, breeze.linalg.argmax..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canTraverseKeyValuePairs())));
      int term = LDA$.MODULE$.index2term(edgeContext.dstId());
      edgeContext.sendToSrc(new Tuple2(new int[]{term}, new int[]{topTopic}));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$topicAssignments$3(final Tuple2 v) {
      return LDA$.MODULE$.isDocumentVertex(v);
   }

   // $FF: synthetic method
   public static final int $anonfun$topicAssignments$5(final Tuple2 x$12) {
      return x$12._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$logLikelihood$1(final double eta$2, final double alpha$2, final DenseVector smoothed_N_k$1, final EdgeContext edgeContext) {
      double N_wj = BoxesRunTime.unboxToDouble(edgeContext.attr());
      DenseVector smoothed_N_wk = (DenseVector)((NumericOps)edgeContext.dstAttr()).$plus(BoxesRunTime.boxToDouble(eta$2 - (double)1.0F), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd());
      DenseVector smoothed_N_kj = (DenseVector)((NumericOps)edgeContext.srcAttr()).$plus(BoxesRunTime.boxToDouble(alpha$2 - (double)1.0F), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd());
      DenseVector phi_wk = (DenseVector)smoothed_N_wk.$div$colon$div(smoothed_N_k$1, breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv());
      DenseVector theta_kj = (DenseVector)breeze.linalg.normalize..MODULE$.apply(smoothed_N_kj, BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.normalize..MODULE$.normalizeDoubleImpl(breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())));
      double tokenLogLikelihood = N_wj * scala.math.package..MODULE$.log(BoxesRunTime.unboxToDouble(phi_wk.dot(theta_kj, breeze.linalg.operators.HasOps..MODULE$.canDotD())));
      edgeContext.sendToDst(BoxesRunTime.boxToDouble(tokenLogLikelihood));
   }

   // $FF: synthetic method
   public static final double $anonfun$logLikelihood$3(final Tuple2 x$16) {
      return x$16._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$logPrior$1(final double eta$3, final DenseVector smoothed_N_k$2, final double alpha$3, final double x0$1, final Tuple2 x1$1) {
      Tuple2 var10 = new Tuple2(BoxesRunTime.boxToDouble(x0$1), x1$1);
      if (var10 != null) {
         double sumPrior = var10._1$mcD$sp();
         Tuple2 vertex = (Tuple2)var10._2();
         if (true && vertex != null) {
            if (LDA$.MODULE$.isTermVertex(vertex)) {
               DenseVector N_wk = (DenseVector)vertex._2();
               DenseVector smoothed_N_wk = (DenseVector)N_wk.$plus(BoxesRunTime.boxToDouble(eta$3 - (double)1.0F), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd());
               DenseVector phi_wk = (DenseVector)smoothed_N_wk.$div$colon$div(smoothed_N_k$2, breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv());
               return sumPrior + (eta$3 - (double)1.0F) * BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(phi_wk.map$mcD$sp((JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.log(x), breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double())), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues())));
            }

            DenseVector N_kj = (DenseVector)vertex._2();
            DenseVector smoothed_N_kj = (DenseVector)N_kj.$plus(BoxesRunTime.boxToDouble(alpha$3 - (double)1.0F), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpAdd());
            DenseVector theta_kj = (DenseVector)breeze.linalg.normalize..MODULE$.apply(smoothed_N_kj, BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.normalize..MODULE$.normalizeDoubleImpl(breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())));
            return sumPrior + (alpha$3 - (double)1.0F) * BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(theta_kj.map$mcD$sp((JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.log(x), breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double())), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues())));
         }
      }

      throw new MatchError(var10);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$topicDistributions$2(final Tuple2 v) {
      return LDA$.MODULE$.isDocumentVertex(v);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$topTopicsPerDocument$1(final Tuple2 v) {
      return LDA$.MODULE$.isDocumentVertex(v);
   }

   public DistributedLDAModel(final Graph graph, final DenseVector globalTopicTotals, final int k, final int vocabSize, final Vector docConcentration, final double topicConcentration, final double[] iterationTimes, final double gammaShape, final String[] checkpointFiles) {
      this.graph = graph;
      this.globalTopicTotals = globalTopicTotals;
      this.k = k;
      this.vocabSize = vocabSize;
      this.docConcentration = docConcentration;
      this.topicConcentration = topicConcentration;
      this.iterationTimes = iterationTimes;
      this.gammaShape = gammaShape;
      this.checkpointFiles = checkpointFiles;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.clustering.DistributedLDAModel";

      public String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final SparkContext sc, final String path, final Graph graph, final DenseVector globalTopicTotals, final int k, final int vocabSize, final Vector docConcentration, final double topicConcentration, final double[] iterationTimes, final double gammaShape) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("k"), BoxesRunTime.boxToInteger(k)), (x) -> $anonfun$save$14(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("vocabSize"), BoxesRunTime.boxToInteger(vocabSize)), (x) -> $anonfun$save$15(BoxesRunTime.unboxToInt(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("docConcentration"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(docConcentration.toArray()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$save$17(BoxesRunTime.unboxToDouble(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("topicConcentration"), BoxesRunTime.boxToDouble(topicConcentration)), (x) -> $anonfun$save$18(BoxesRunTime.unboxToDouble(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("iterationTimes"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(iterationTimes).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$save$20(BoxesRunTime.unboxToDouble(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("gammaShape"), BoxesRunTime.boxToDouble(gammaShape)), (x) -> $anonfun$save$21(BoxesRunTime.unboxToDouble(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$3() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().text(Loader$.MODULE$.metadataPath(path));
         String newPath = (new Path(Loader$.MODULE$.dataPath(path), "globalTopicTotals")).toUri().toString();
         var10001 = new scala.collection.immutable..colon.colon(new DistributedLDAModel$SaveLoadV1_0$Data(Vectors$.MODULE$.fromBreeze(globalTopicTotals)), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.DistributedLDAModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$2() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2())).write().parquet(newPath);
         String verticesPath = (new Path(Loader$.MODULE$.dataPath(path), "topicCounts")).toUri().toString();
         RDD var27 = graph.vertices().map((x0$1) -> {
            if (x0$1 != null) {
               long ind = x0$1._1$mcJ$sp();
               DenseVector vertex = (DenseVector)x0$1._2();
               return new DistributedLDAModel$SaveLoadV1_0$VertexData(ind, Vectors$.MODULE$.fromBreeze(vertex));
            } else {
               throw new MatchError(x0$1);
            }
         }, .MODULE$.apply(DistributedLDAModel$SaveLoadV1_0$VertexData.class));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator3$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.DistributedLDAModel.SaveLoadV1_0.VertexData").asType().toTypeConstructor();
            }

            public $typecreator3$1() {
            }
         }

         spark.createDataFrame(var27, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1())).write().parquet(verticesPath);
         String edgesPath = (new Path(Loader$.MODULE$.dataPath(path), "tokenCounts")).toUri().toString();
         var27 = graph.edges().map((x0$2) -> {
            if (x0$2 != null) {
               long srcId = x0$2.srcId();
               long dstId = x0$2.dstId();
               double prop = x0$2.attr$mcD$sp();
               return new DistributedLDAModel$SaveLoadV1_0$EdgeData(srcId, dstId, prop);
            } else {
               throw new MatchError(x0$2);
            }
         }, .MODULE$.apply(DistributedLDAModel$SaveLoadV1_0$EdgeData.class));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator4$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.DistributedLDAModel.SaveLoadV1_0.EdgeData").asType().toTypeConstructor();
            }

            public $typecreator4$1() {
            }
         }

         spark.createDataFrame(var27, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1())).write().parquet(edgesPath);
      }

      public DistributedLDAModel load(final SparkContext sc, final String path, final int vocabSize, final Vector docConcentration, final double topicConcentration, final double[] iterationTimes, final double gammaShape) {
         String dataPath = (new Path(Loader$.MODULE$.dataPath(path), "globalTopicTotals")).toUri().toString();
         String vertexDataPath = (new Path(Loader$.MODULE$.dataPath(path), "topicCounts")).toUri().toString();
         String edgeDataPath = (new Path(Loader$.MODULE$.dataPath(path), "tokenCounts")).toUri().toString();
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataFrame = spark.read().parquet(dataPath);
         Dataset vertexDataFrame = spark.read().parquet(vertexDataPath);
         Dataset edgeDataFrame = spark.read().parquet(edgeDataPath);
         Loader$ var10000 = Loader$.MODULE$;
         StructType var10001 = dataFrame.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$4 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.DistributedLDAModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator1$4() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$4()));
         var10000 = Loader$.MODULE$;
         var10001 = vertexDataFrame.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.DistributedLDAModel.SaveLoadV1_0.VertexData").asType().toTypeConstructor();
            }

            public $typecreator2$3() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$3()));
         var10000 = Loader$.MODULE$;
         var10001 = edgeDataFrame.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator3$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.DistributedLDAModel.SaveLoadV1_0.EdgeData").asType().toTypeConstructor();
            }

            public $typecreator3$2() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$2()));
         DenseVector globalTopicTotals = ((Vector)((Row)dataFrame.first()).getAs(0)).asBreeze().toDenseVector$mcD$sp(.MODULE$.Double());
         RDD vertices = vertexDataFrame.rdd().map((x0$1) -> {
            if (x0$1 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                  Object ind = ((SeqOps)var3.get()).apply(0);
                  Object vec = ((SeqOps)var3.get()).apply(1);
                  if (ind instanceof Long) {
                     long var6 = BoxesRunTime.unboxToLong(ind);
                     if (vec instanceof Vector) {
                        Vector var8 = (Vector)vec;
                        return new Tuple2(BoxesRunTime.boxToLong(var6), var8.asBreeze().toDenseVector$mcD$sp(.MODULE$.Double()));
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         }, .MODULE$.apply(Tuple2.class));
         RDD edges = edgeDataFrame.rdd().map((x0$2) -> {
            if (x0$2 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$2);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
                  Object srcId = ((SeqOps)var3.get()).apply(0);
                  Object dstId = ((SeqOps)var3.get()).apply(1);
                  Object prop = ((SeqOps)var3.get()).apply(2);
                  if (srcId instanceof Long) {
                     long var7 = BoxesRunTime.unboxToLong(srcId);
                     if (dstId instanceof Long) {
                        long var9 = BoxesRunTime.unboxToLong(dstId);
                        if (prop instanceof Double) {
                           double var11 = BoxesRunTime.unboxToDouble(prop);
                           return new Edge.mcD.sp(var7, var9, var11);
                        }
                     }
                  }
               }
            }

            throw new MatchError(x0$2);
         }, .MODULE$.apply(Edge.class));
         Graph graph = org.apache.spark.graphx.Graph..MODULE$.apply(vertices, edges, org.apache.spark.graphx.Graph..MODULE$.apply$default$3(), org.apache.spark.graphx.Graph..MODULE$.apply$default$4(), org.apache.spark.graphx.Graph..MODULE$.apply$default$5(), .MODULE$.apply(DenseVector.class), .MODULE$.apply(Double.TYPE));
         return new DistributedLDAModel(graph, globalTopicTotals, globalTopicTotals.length(), vocabSize, docConcentration, topicConcentration, iterationTimes, gammaShape, DistributedLDAModel$.MODULE$.$lessinit$greater$default$9());
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$14(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$15(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$17(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$18(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$20(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$21(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      public SaveLoadV1_0$() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
