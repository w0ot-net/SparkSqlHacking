package org.apache.spark.sql.streaming;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import java.io.Serializable;
import java.util.UUID;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.json4s.JString;
import org.json4s.JValue;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\r\rd!B3g\u0003\u0003\t\bbBA\u0005\u0001\u0011\u0005\u00111\u0002\u0005\b\u0003#\u0001a\u0011AA\n\u0011\u001d\u0019I\u0005\u0001D\u0001\u0007\u0017Bqa!\u0015\u0001\t\u0003\u0019\u0019\u0006C\u0004\u0004Z\u00011\taa\u0017\b\u000f\u0005\rb\r#\u0001\u0002&\u00191QM\u001aE\u0001\u0003OAq!!\u0003\b\t\u0003\tI\u0003C\u0005\u0002,\u001d\u0011\r\u0011\"\u0003\u0002.!A\u0011\u0011H\u0004!\u0002\u0013\tyC\u0002\u0004\u0002b\u001d!\u00151\r\u0005\u000b\u0003WZ!Q3A\u0005\u0002\u00055\u0004BCA@\u0017\tE\t\u0015!\u0003\u0002p!9\u0011\u0011B\u0006\u0005\u0002\u0005\u0005\u0005\"CAE\u0017\t\u0007I\u0011BAF\u0011!\t\u0019j\u0003Q\u0001\n\u00055\u0005bBAK\u0017\u0011\u0005\u0011q\u0013\u0005\b\u0003;[A\u0011AAP\u0011\u001d\t\u0019l\u0003C\u0001\u0003kC\u0011\"a0\f\u0003\u0003%\t!!1\t\u0013\u0005\u00157\"%A\u0005\u0002\u0005\u001d\u0007\"CAo\u0017\u0005\u0005I\u0011IAp\u0011%\tYoCA\u0001\n\u0003\ti\u000fC\u0005\u0002v.\t\t\u0011\"\u0001\u0002x\"I!1A\u0006\u0002\u0002\u0013\u0005#Q\u0001\u0005\n\u0005'Y\u0011\u0011!C\u0001\u0005+A\u0011Ba\b\f\u0003\u0003%\tE!\t\t\u0013\t\u00152\"!A\u0005B\t\u001d\u0002\"\u0003B\u0015\u0017\u0005\u0005I\u0011\tB\u0016\u0011%\u0011icCA\u0001\n\u0003\u0012ycB\u0005\u00034\u001d\t\t\u0011#\u0003\u00036\u0019I\u0011\u0011M\u0004\u0002\u0002#%!q\u0007\u0005\b\u0003\u0013\u0001C\u0011\u0001B(\u0011%\u0011I\u0003IA\u0001\n\u000b\u0012Y\u0003C\u0005\u0003R\u0001\n\t\u0011\"!\u0003T!I!q\u000b\u0011\u0002\u0002\u0013\u0005%\u0011\f\u0005\n\u0005K\u0002\u0013\u0011!C\u0005\u0005O2\u0011Ba\u001c\b!\u0003\r\nA!\u001d\u0007\r\t-u\u0001\u0001BG\u0011)\u0011\tj\nBC\u0002\u0013\u0005!1\u0013\u0005\u000b\u0005+;#\u0011!Q\u0001\n\u0005\u0005\u0006B\u0003BLO\t\u0015\r\u0011\"\u0001\u0003\u0014\"Q!\u0011T\u0014\u0003\u0002\u0003\u0006I!!)\t\u0015\u0005muE!b\u0001\n\u0003\ti\u0007\u0003\u0006\u0003\u001c\u001e\u0012\t\u0011)A\u0005\u0003_B!B!((\u0005\u000b\u0007I\u0011AA7\u0011)\u0011yj\nB\u0001B\u0003%\u0011q\u000e\u0005\t\u0003\u00139C\u0011\u00015\u0003\"\"9\u00111N\u0014\u0005\u0002\u00055\u0004b\u0002BWO\u0011%!qV\u0004\t\u0005\u001b<\u0001\u0012\u00016\u0003P\u001aA!1R\u0004\t\u0002)\u0014\t\u000eC\u0004\u0002\nQ\"\tAa5\t\u0011\tUG\u0007\"\u0001k\u0005/D\u0011B!\u001a5\u0003\u0003%IAa\u001a\u0007\r\tmw\u0001\u0001Bo\u0011)\u0011y\u000e\u000fBC\u0002\u0013\u0005!\u0011\u001d\u0005\u000b\u0005GD$\u0011!Q\u0001\n\u0005]\u0006\u0002CA\u0005q\u0011\u0005\u0001N!:\t\u000f\u0005-\u0004\b\"\u0001\u0002n!9!Q\u0016\u001d\u0005\n\t=v\u0001\u0003Bw\u000f!\u0005!Na<\u0007\u0011\tmw\u0001#\u0001k\u0005cDq!!\u0003@\t\u0003\u0011\u0019\u0010\u0003\u0005\u0003V~\"\tA\u001bB{\u0011%\u0011)gPA\u0001\n\u0013\u00119G\u0002\u0004\u0003z\u001e\u0001!1 \u0005\u000b\u0005#\u001b%Q1A\u0005\u0002\tM\u0005B\u0003BK\u0007\n\u0005\t\u0015!\u0003\u0002\"\"Q!qS\"\u0003\u0006\u0004%\tAa%\t\u0015\te5I!A!\u0002\u0013\t\t\u000b\u0003\u0006\u0003\u001e\u000e\u0013)\u0019!C\u0001\u0003[B!Ba(D\u0005\u0003\u0005\u000b\u0011BA8\u0011!\tIa\u0011C\u0001Q\nu\bbBA6\u0007\u0012\u0005\u0011Q\u000e\u0005\b\u0005[\u001bE\u0011\u0002BX\u000f!\u0019Ia\u0002E\u0001U\u000e-a\u0001\u0003B}\u000f!\u0005!n!\u0004\t\u000f\u0005%a\n\"\u0001\u0004\u0010!A!Q\u001b(\u0005\u0002)\u001c\t\u0002C\u0005\u0003f9\u000b\t\u0011\"\u0003\u0003h\u001911QC\u0004\u0001\u0007/A!B!%S\u0005\u000b\u0007I\u0011\u0001BJ\u0011)\u0011)J\u0015B\u0001B\u0003%\u0011\u0011\u0015\u0005\u000b\u0005/\u0013&Q1A\u0005\u0002\tM\u0005B\u0003BM%\n\u0005\t\u0015!\u0003\u0002\"\"Q1\u0011\u0004*\u0003\u0006\u0004%\taa\u0007\t\u0015\ru!K!A!\u0002\u0013\u0011Y\u0006\u0003\u0006\u0004 I\u0013)\u0019!C\u0001\u00077A!b!\tS\u0005\u0003\u0005\u000b\u0011\u0002B.\u0011!\tIA\u0015C\u0001Q\u000e\r\u0002bBA\u0005%\u0012\u00051q\u0006\u0005\b\u0003W\u0012F\u0011AA7\u0011\u001d\u0011iK\u0015C\u0005\u0005_;\u0001b!\u000f\b\u0011\u0003Q71\b\u0004\t\u0007+9\u0001\u0012\u00016\u0004>!9\u0011\u0011\u00021\u0005\u0002\r}\u0002\u0002\u0003BkA\u0012\u0005!n!\u0011\t\u0013\t\u0015\u0004-!A\u0005\n\t\u001d\u0004\"\u0003B3\u000f\u0005\u0005I\u0011\u0002B4\u0005Y\u0019FO]3b[&tw-U;fefd\u0015n\u001d;f]\u0016\u0014(BA4i\u0003%\u0019HO]3b[&twM\u0003\u0002jU\u0006\u00191/\u001d7\u000b\u0005-d\u0017!B:qCJ\\'BA7o\u0003\u0019\t\u0007/Y2iK*\tq.A\u0002pe\u001e\u001c\u0001aE\u0002\u0001eb\u0004\"a\u001d<\u000e\u0003QT\u0011!^\u0001\u0006g\u000e\fG.Y\u0005\u0003oR\u0014a!\u00118z%\u00164\u0007cA=\u0002\u00049\u0011!p \b\u0003wzl\u0011\u0001 \u0006\u0003{B\fa\u0001\u0010:p_Rt\u0014\"A;\n\u0007\u0005\u0005A/A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0015\u0011q\u0001\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003\u0003!\u0018A\u0002\u001fj]&$h\b\u0006\u0002\u0002\u000eA\u0019\u0011q\u0002\u0001\u000e\u0003\u0019\fab\u001c8Rk\u0016\u0014\u0018p\u0015;beR,G\r\u0006\u0003\u0002\u0016\u0005m\u0001cA:\u0002\u0018%\u0019\u0011\u0011\u0004;\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003;\u0011\u0001\u0019AA\u0010\u0003\u0015)g/\u001a8u!\r\t\tc\n\b\u0004\u0003\u001f1\u0011AF*ue\u0016\fW.\u001b8h#V,'/\u001f'jgR,g.\u001a:\u0011\u0007\u0005=qaE\u0002\beb$\"!!\n\u0002\r5\f\u0007\u000f]3s+\t\tyC\u0005\u0004\u00022\u0005m\u00121\u000b\u0004\b\u0003g\t)\u0004AA\u0018\u00051a$/\u001a4j]\u0016lWM\u001c;?\u0011\u0019\t9D\u0003\u0001\u00020\u0005\u0019!/\u001a;\u0002\u000f5\f\u0007\u000f]3sAA!\u0011QHA(\u001b\t\tyD\u0003\u0003\u0002B\u0005\r\u0013\u0001\u00033bi\u0006\u0014\u0017N\u001c3\u000b\t\u0005\u0015\u0013qI\u0001\bU\u0006\u001c7n]8o\u0015\u0011\tI%a\u0013\u0002\u0013\u0019\f7\u000f^3sq6d'BAA'\u0003\r\u0019w.\\\u0005\u0005\u0003#\nyD\u0001\u0007PE*,7\r^'baB,'\u000f\u0005\u0003\u0002V\u0005uSBAA,\u0015\r)\u0018\u0011\f\u0006\u0005\u00037\n\u0019%\u0001\u0004n_\u0012,H.Z\u0005\u0005\u0003?\n9F\u0001\nDY\u0006\u001c8\u000fV1h\u000bb$XM\\:j_:\u001c(aC#wK:$\b+\u0019:tKJ\u001cRa\u0003:\u0002fa\u00042a]A4\u0013\r\tI\u0007\u001e\u0002\b!J|G-^2u\u0003\u0011Q7o\u001c8\u0016\u0005\u0005=\u0004\u0003BA9\u0003srA!a\u001d\u0002vA\u00111\u0010^\u0005\u0004\u0003o\"\u0018A\u0002)sK\u0012,g-\u0003\u0003\u0002|\u0005u$AB*ue&twMC\u0002\u0002xQ\fQA[:p]\u0002\"B!a!\u0002\bB\u0019\u0011QQ\u0006\u000e\u0003\u001dAq!a\u001b\u000f\u0001\u0004\ty'\u0001\u0003ue\u0016,WCAAG!\u0011\ti$a$\n\t\u0005E\u0015q\b\u0002\t\u0015N|gNT8eK\u0006)AO]3fA\u0005Iq-\u001a;TiJLgn\u001a\u000b\u0005\u0003_\nI\nC\u0004\u0002\u001cF\u0001\r!a\u001c\u0002\t9\fW.Z\u0001\bO\u0016$X+V%E)\u0011\t\t+!-\u0011\t\u0005\r\u0016QV\u0007\u0003\u0003KSA!a*\u0002*\u0006!Q\u000f^5m\u0015\t\tY+\u0001\u0003kCZ\f\u0017\u0002BAX\u0003K\u0013A!V+J\t\"9\u00111\u0014\nA\u0002\u0005=\u0014aC4fiB\u0013xn\u001a:fgN$B!a.\u0002>B!\u0011qBA]\u0013\r\tYL\u001a\u0002\u0017'R\u0014X-Y7j]\u001e\fV/\u001a:z!J|wM]3tg\"9\u00111T\nA\u0002\u0005=\u0014\u0001B2paf$B!a!\u0002D\"I\u00111\u000e\u000b\u0011\u0002\u0003\u0007\u0011qN\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tIM\u000b\u0003\u0002p\u0005-7FAAg!\u0011\ty-!7\u000e\u0005\u0005E'\u0002BAj\u0003+\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005]G/\u0001\u0006b]:|G/\u0019;j_:LA!a7\u0002R\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\t\u000f\u0005\u0003\u0002d\u0006%XBAAs\u0015\u0011\t9/!+\u0002\t1\fgnZ\u0005\u0005\u0003w\n)/\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002pB\u00191/!=\n\u0007\u0005MHOA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002z\u0006}\bcA:\u0002|&\u0019\u0011Q ;\u0003\u0007\u0005s\u0017\u0010C\u0005\u0003\u0002a\t\t\u00111\u0001\u0002p\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"Aa\u0002\u0011\r\t%!qBA}\u001b\t\u0011YAC\u0002\u0003\u000eQ\f!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011\tBa\u0003\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005/\u0011i\u0002E\u0002t\u00053I1Aa\u0007u\u0005\u001d\u0011un\u001c7fC:D\u0011B!\u0001\u001b\u0003\u0003\u0005\r!!?\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003C\u0014\u0019\u0003C\u0005\u0003\u0002m\t\t\u00111\u0001\u0002p\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002p\u0006AAo\\*ue&tw\r\u0006\u0002\u0002b\u00061Q-];bYN$BAa\u0006\u00032!I!\u0011\u0001\u0010\u0002\u0002\u0003\u0007\u0011\u0011`\u0001\f\u000bZ,g\u000e\u001e)beN,'\u000fE\u0002\u0002\u0006\u0002\u001aR\u0001\tB\u001d\u0005\u000b\u0002\u0002Ba\u000f\u0003B\u0005=\u00141Q\u0007\u0003\u0005{Q1Aa\u0010u\u0003\u001d\u0011XO\u001c;j[\u0016LAAa\u0011\u0003>\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\t\u001d#QJ\u0007\u0003\u0005\u0013RAAa\u0013\u0002*\u0006\u0011\u0011n\\\u0005\u0005\u0003\u000b\u0011I\u0005\u0006\u0002\u00036\u0005)\u0011\r\u001d9msR!\u00111\u0011B+\u0011\u001d\tYg\ta\u0001\u0003_\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003\\\t\u0005\u0004#B:\u0003^\u0005=\u0014b\u0001B0i\n1q\n\u001d;j_:D\u0011Ba\u0019%\u0003\u0003\u0005\r!a!\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003jA!\u00111\u001dB6\u0013\u0011\u0011i'!:\u0003\r=\u0013'.Z2u\u0005\u0015)e/\u001a8u'\u00111#Oa\u001d\u0011\t\tU$1P\u0007\u0003\u0005oR1A!\u001fk\u0003%\u00198\r[3ek2,'/\u0003\u0003\u0003~\t]$AE*qCJ\\G*[:uK:,'/\u0012<f]RD3A\nBA!\u0011\u0011\u0019Ia\"\u000e\u0005\t\u0015%bAAlU&!!\u0011\u0012BC\u0005!)eo\u001c7wS:<'!E)vKJL8\u000b^1si\u0016$WI^3oiN)qE\u001dBHqB\u0019\u0011Q\u0011\u0014\u0002\u0005%$WCAAQ\u0003\rIG\rI\u0001\u0006eVt\u0017\nZ\u0001\u0007eVt\u0017\n\u001a\u0011\u0002\u000b9\fW.\u001a\u0011\u0002\u0013QLW.Z:uC6\u0004\u0018A\u0003;j[\u0016\u001cH/Y7qAQQ!1\u0015BS\u0005O\u0013IKa+\u0011\u0007\u0005\u0015u\u0005C\u0004\u0003\u0012B\u0002\r!!)\t\u000f\t]\u0005\u00071\u0001\u0002\"\"9\u00111\u0014\u0019A\u0002\u0005=\u0004b\u0002BOa\u0001\u0007\u0011qN\u0001\nUN|gNV1mk\u0016,\"A!-\u0011\t\tM&Q\u0019\b\u0005\u0005k\u0013yL\u0004\u0003\u00038\nmfbA>\u0003:&\tq.C\u0002\u0003>:\faA[:p]R\u001a\u0018\u0002\u0002Ba\u0005\u0007\fqAS:p]\u0006\u001bFKC\u0002\u0003>:LAAa2\u0003J\n1!JV1mk\u0016TAA!1\u0003D\"\u001aqE!!\u0002#E+XM]=Ti\u0006\u0014H/\u001a3Fm\u0016tG\u000fE\u0002\u0002\u0006R\u001aB\u0001\u000e:\u0003FQ\u0011!qZ\u0001\tMJ|WNS:p]R!!1\u0015Bm\u0011\u001d\tYG\u000ea\u0001\u0003_\u0012!#U;fef\u0004&o\\4sKN\u001cXI^3oiN)\u0001H\u001dBHq\u0006A\u0001O]8he\u0016\u001c8/\u0006\u0002\u00028\u0006I\u0001O]8he\u0016\u001c8\u000f\t\u000b\u0005\u0005O\u0014I\u000fE\u0002\u0002\u0006bBqAa8<\u0001\u0004\t9\fK\u00029\u0005\u0003\u000b!#U;fef\u0004&o\\4sKN\u001cXI^3oiB\u0019\u0011QQ \u0014\t}\u0012(Q\t\u000b\u0003\u0005_$BAa:\u0003x\"9\u00111N!A\u0002\u0005=$AD)vKJL\u0018\n\u001a7f\u000bZ,g\u000e^\n\u0006\u0007J\u0014y\t\u001f\u000b\t\u0005\u007f\u001c\taa\u0001\u0004\u0006A\u0019\u0011QQ\"\t\u000f\tE%\n1\u0001\u0002\"\"9!q\u0013&A\u0002\u0005\u0005\u0006b\u0002BO\u0015\u0002\u0007\u0011q\u000e\u0015\u0004\u0007\n\u0005\u0015AD)vKJL\u0018\n\u001a7f\u000bZ,g\u000e\u001e\t\u0004\u0003\u000bs5\u0003\u0002(s\u0005\u000b\"\"aa\u0003\u0015\t\t}81\u0003\u0005\b\u0003W\u0002\u0006\u0019AA8\u0005Q\tV/\u001a:z)\u0016\u0014X.\u001b8bi\u0016$WI^3oiN)!K\u001dBHq\u0006IQ\r_2faRLwN\\\u000b\u0003\u00057\n!\"\u001a=dKB$\u0018n\u001c8!\u0003U)'O]8s\u00072\f7o](o\u000bb\u001cW\r\u001d;j_:\fa#\u001a:s_J\u001cE.Y:t\u001f:,\u0005pY3qi&|g\u000e\t\u000b\u000b\u0007K\u00199c!\u000b\u0004,\r5\u0002cAAC%\"9!\u0011S.A\u0002\u0005\u0005\u0006b\u0002BL7\u0002\u0007\u0011\u0011\u0015\u0005\b\u00073Y\u0006\u0019\u0001B.\u0011\u001d\u0019yb\u0017a\u0001\u00057\"\u0002b!\n\u00042\rM2Q\u0007\u0005\b\u0005#c\u0006\u0019AAQ\u0011\u001d\u00119\n\u0018a\u0001\u0003CCqa!\u0007]\u0001\u0004\u0011Y\u0006K\u0002S\u0005\u0003\u000bA#U;fef$VM]7j]\u0006$X\rZ#wK:$\bcAACAN!\u0001M\u001dB#)\t\u0019Y\u0004\u0006\u0003\u0004&\r\r\u0003bBA6E\u0002\u0007\u0011q\u000e\u0015\u0004\u000f\t\u0005\u0005f\u0001\u0004\u0003\u0002\u0006yqN\\)vKJL\bK]8he\u0016\u001c8\u000f\u0006\u0003\u0002\u0016\r5\u0003bBA\u000f\u0007\u0001\u00071q\n\t\u0004\u0003CA\u0014aC8o#V,'/_%eY\u0016$B!!\u0006\u0004V!9\u0011Q\u0004\u0003A\u0002\r]\u0003cAA\u0011\u0007\u0006\trN\\)vKJLH+\u001a:nS:\fG/\u001a3\u0015\t\u0005U1Q\f\u0005\b\u0003;)\u0001\u0019AB0!\r\t\tC\u0015\u0015\u0004\u0001\t\u0005\u0005"
)
public abstract class StreamingQueryListener implements Serializable {
   public abstract void onQueryStarted(final QueryStartedEvent event);

   public abstract void onQueryProgress(final QueryProgressEvent event);

   public void onQueryIdle(final QueryIdleEvent event) {
   }

   public abstract void onQueryTerminated(final QueryTerminatedEvent event);

   private static class EventParser implements Product, Serializable {
      private final String json;
      private final JsonNode tree;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String json() {
         return this.json;
      }

      private JsonNode tree() {
         return this.tree;
      }

      public String getString(final String name) {
         return this.tree().get(name).asText();
      }

      public UUID getUUID(final String name) {
         return UUID.fromString(this.getString(name));
      }

      public StreamingQueryProgress getProgress(final String name) {
         TreeTraversingParser parser = new TreeTraversingParser(this.tree().get(name), StreamingQueryListener$.MODULE$.org$apache$spark$sql$streaming$StreamingQueryListener$$mapper());
         return (StreamingQueryProgress)parser.readValueAs(StreamingQueryProgress.class);
      }

      public EventParser copy(final String json) {
         return new EventParser(json);
      }

      public String copy$default$1() {
         return this.json();
      }

      public String productPrefix() {
         return "EventParser";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.json();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof EventParser;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "json";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label47: {
               if (x$1 instanceof EventParser) {
                  label40: {
                     EventParser var4 = (EventParser)x$1;
                     String var10000 = this.json();
                     String var5 = var4.json();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label40;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label40;
                     }

                     if (var4.canEqual(this)) {
                        break label47;
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public EventParser(final String json) {
         this.json = json;
         Product.$init$(this);
         this.tree = StreamingQueryListener$.MODULE$.org$apache$spark$sql$streaming$StreamingQueryListener$$mapper().readTree(json);
      }
   }

   private static class EventParser$ extends AbstractFunction1 implements Serializable {
      public static final EventParser$ MODULE$ = new EventParser$();

      public final String toString() {
         return "EventParser";
      }

      public EventParser apply(final String json) {
         return new EventParser(json);
      }

      public Option unapply(final EventParser x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.json()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EventParser$.class);
      }

      public EventParser$() {
      }
   }

   @Evolving
   public static class QueryStartedEvent implements Event, Serializable {
      private final UUID id;
      private final UUID runId;
      private final String name;
      private final String timestamp;

      public boolean logEvent() {
         return SparkListenerEvent.logEvent$(this);
      }

      public UUID id() {
         return this.id;
      }

      public UUID runId() {
         return this.runId;
      }

      public String name() {
         return this.name;
      }

      public String timestamp() {
         return this.timestamp;
      }

      public String json() {
         return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
      }

      private JValue jsonValue() {
         return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("id"), new JString(this.id().toString())), scala.Predef..MODULE$.$conforms()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("runId"), new JString(this.runId().toString())), scala.Predef..MODULE$.$conforms(), scala.Predef..MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), new JString(this.name())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("timestamp"), new JString(this.timestamp())));
      }

      public QueryStartedEvent(final UUID id, final UUID runId, final String name, final String timestamp) {
         this.id = id;
         this.runId = runId;
         this.name = name;
         this.timestamp = timestamp;
         SparkListenerEvent.$init$(this);
      }
   }

   public static class QueryStartedEvent$ implements Serializable {
      public static final QueryStartedEvent$ MODULE$ = new QueryStartedEvent$();

      public QueryStartedEvent fromJson(final String json) {
         EventParser parser = new EventParser(json);
         return new QueryStartedEvent(parser.getUUID("id"), parser.getUUID("runId"), parser.getString("name"), parser.getString("name"));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(QueryStartedEvent$.class);
      }
   }

   @Evolving
   public static class QueryProgressEvent implements Event, Serializable {
      private final StreamingQueryProgress progress;

      public boolean logEvent() {
         return SparkListenerEvent.logEvent$(this);
      }

      public StreamingQueryProgress progress() {
         return this.progress;
      }

      public String json() {
         return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
      }

      private JValue jsonValue() {
         return org.json4s.JObject..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("progress"), this.progress().jsonValue())})));
      }

      public QueryProgressEvent(final StreamingQueryProgress progress) {
         this.progress = progress;
         SparkListenerEvent.$init$(this);
      }
   }

   public static class QueryProgressEvent$ implements Serializable {
      public static final QueryProgressEvent$ MODULE$ = new QueryProgressEvent$();

      public QueryProgressEvent fromJson(final String json) {
         EventParser parser = new EventParser(json);
         return new QueryProgressEvent(parser.getProgress("progress"));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(QueryProgressEvent$.class);
      }
   }

   @Evolving
   public static class QueryIdleEvent implements Event, Serializable {
      private final UUID id;
      private final UUID runId;
      private final String timestamp;

      public boolean logEvent() {
         return SparkListenerEvent.logEvent$(this);
      }

      public UUID id() {
         return this.id;
      }

      public UUID runId() {
         return this.runId;
      }

      public String timestamp() {
         return this.timestamp;
      }

      public String json() {
         return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
      }

      private JValue jsonValue() {
         return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("id"), new JString(this.id().toString())), scala.Predef..MODULE$.$conforms()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("runId"), new JString(this.runId().toString())), scala.Predef..MODULE$.$conforms(), scala.Predef..MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("timestamp"), new JString(this.timestamp())));
      }

      public QueryIdleEvent(final UUID id, final UUID runId, final String timestamp) {
         this.id = id;
         this.runId = runId;
         this.timestamp = timestamp;
         SparkListenerEvent.$init$(this);
      }
   }

   public static class QueryIdleEvent$ implements Serializable {
      public static final QueryIdleEvent$ MODULE$ = new QueryIdleEvent$();

      public QueryIdleEvent fromJson(final String json) {
         EventParser parser = new EventParser(json);
         return new QueryIdleEvent(parser.getUUID("id"), parser.getUUID("runId"), parser.getString("timestamp"));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(QueryIdleEvent$.class);
      }
   }

   @Evolving
   public static class QueryTerminatedEvent implements Event, Serializable {
      private final UUID id;
      private final UUID runId;
      private final Option exception;
      private final Option errorClassOnException;

      public boolean logEvent() {
         return SparkListenerEvent.logEvent$(this);
      }

      public UUID id() {
         return this.id;
      }

      public UUID runId() {
         return this.runId;
      }

      public Option exception() {
         return this.exception;
      }

      public Option errorClassOnException() {
         return this.errorClassOnException;
      }

      public String json() {
         return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
      }

      private JValue jsonValue() {
         return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("id"), new JString(this.id().toString())), scala.Predef..MODULE$.$conforms()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("runId"), new JString(this.runId().toString())), scala.Predef..MODULE$.$conforms(), scala.Predef..MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("exception"), new JString((String)this.exception().orNull(scala..less.colon.less..MODULE$.refl()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("errorClassOnException"), new JString((String)this.errorClassOnException().orNull(scala..less.colon.less..MODULE$.refl()))));
      }

      public QueryTerminatedEvent(final UUID id, final UUID runId, final Option exception, final Option errorClassOnException) {
         this.id = id;
         this.runId = runId;
         this.exception = exception;
         this.errorClassOnException = errorClassOnException;
         SparkListenerEvent.$init$(this);
      }

      public QueryTerminatedEvent(final UUID id, final UUID runId, final Option exception) {
         this(id, runId, exception, scala.None..MODULE$);
      }
   }

   public static class QueryTerminatedEvent$ implements Serializable {
      public static final QueryTerminatedEvent$ MODULE$ = new QueryTerminatedEvent$();

      public QueryTerminatedEvent fromJson(final String json) {
         EventParser parser = new EventParser(json);
         return new QueryTerminatedEvent(parser.getUUID("id"), parser.getUUID("runId"), scala.Option..MODULE$.apply(parser.getString("exception")), scala.Option..MODULE$.apply(parser.getString("errorClassOnException")));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(QueryTerminatedEvent$.class);
      }
   }

   @Evolving
   public interface Event extends SparkListenerEvent {
   }
}
