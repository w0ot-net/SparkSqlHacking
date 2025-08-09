package org.apache.spark.api.java;

import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.spark.ReadOnlySparkConf;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.input.PortableDataStream;
import org.apache.spark.rdd.EmptyRDD;
import org.apache.spark.rdd.HadoopRDD;
import org.apache.spark.rdd.NewHadoopRDD;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015Uf\u0001\u0002/^\u0001!D\u0001B\u001e\u0001\u0003\u0006\u0004%\ta\u001e\u0005\ty\u0002\u0011\t\u0011)A\u0005q\")Q\u0010\u0001C\u0001}\"1Q\u0010\u0001C\u0001\u0003\u000bAa! \u0001\u0005\u0002\u0005\u001d\u0001BB?\u0001\t\u0003\t\u0019\u0002\u0003\u0004~\u0001\u0011\u0005\u0011q\u0007\u0005\u0007{\u0002!\t!a\u0010\t\ru\u0004A\u0011AA'\u0011\u0019i\b\u0001\"\u0001\u0002b!Q\u00111\u0010\u0001C\u0002\u0013\u0005\u0011-! \t\u0011\u0005\u0015\u0005\u0001)A\u0005\u0003\u007fBq!a\"\u0001\t\u0003\tI\tC\u0004\u0002\u0012\u0002!\t!a%\t\u000f\u0005m\u0005\u0001\"\u0001\u0002\u001e\"9\u0011q\u0003\u0001\u0005\u0002\u0005u\u0005bBA\u001b\u0001\u0011\u0005\u0011Q\u0014\u0005\b\u0003?\u0003A\u0011AAQ\u0011\u001d\t9\u0006\u0001C\u0001\u0003cCq!!/\u0001\t\u0003\tY\fC\u0004\u0002D\u0002!\t!!(\t\u000f\u0005\u0015\u0007\u0001\"\u0001\u0002H\"9\u0011q\u001a\u0001\u0005\u0002\u0005\u001d\u0007bBAi\u0001\u0011\u0005\u00111\u001b\u0005\b\u0005\u0007\u0001A\u0011\u0001B\u0003\u0011\u001d\t\t\u000e\u0001C\u0001\u0005\u001fAqA!\b\u0001\t\u0003\u0011y\u0002C\u0004\u0003\u001e\u0001!\tA!\u0011\t\u000f\tU\u0003\u0001\"\u0001\u0003X!9!Q\u000b\u0001\u0005\u0002\t-\u0004b\u0002B8\u0001\u0011\u0005!\u0011\u000f\u0005\b\u0005_\u0002A\u0011\u0001B=\u0011\u001d\u0011\t\t\u0001C\u0001\u0005\u0007CqA!!\u0001\t\u0003\u0011Y\tC\u0004\u0003\u0010\u0002!\tA!%\t\u000f\t=\u0005\u0001\"\u0001\u0003&\"9!\u0011\u0016\u0001\u0005\u0002\t-\u0006b\u0002B_\u0001\u0011\u0005!q\u0018\u0005\b\u0005{\u0003A\u0011\u0001Bq\u0011\u001d\u0011I\u0010\u0001C\u0001\u0005wDqA!?\u0001\t\u0003\u0019I\u0001C\u0004\u0004\u0016\u0001!\taa\u0006\t\u000f\rU\u0001\u0001\"\u0001\u0004V!91\u0011\u0010\u0001\u0005\u0002\rm\u0004bBB=\u0001\u0011\u00051\u0011\u0015\u0005\b\u0007\u000b\u0004A\u0011ABd\u0011\u001d!)\u0001\u0001C\u0001\t\u000fAq\u0001b\u000b\u0001\t\u0003!i\u0003C\u0004\u0005,\u0001!\t\u0001b\u0014\t\u000f\u0011-\u0002\u0001\"\u0001\u0005d!9A1\u000e\u0001\u0005\u0002\u00115\u0004b\u0002CB\u0001\u0011\u0005AQ\u0011\u0005\b\t\u001b\u0003A\u0011\tCC\u0011\u001d!y\t\u0001C\u0001\t#Cq\u0001\"'\u0001\t\u0003!Y\nC\u0004\u0005\u001a\u0002!\t\u0001b(\t\u000f\u0011-\u0006\u0001\"\u0001\u0005.\"9A\u0011\u0017\u0001\u0005\u0002\u0011M\u0006b\u0002C[\u0001\u0011\u0005Aq\u0017\u0005\b\t{\u0003A\u0011\u0001C`\u0011\u001d!\t\r\u0001C\t\t\u0007Dq\u0001b4\u0001\t\u0003!\t\u000eC\u0004\u0005T\u0002!\t\u0001\"6\t\u000f\u0011u\u0007\u0001\"\u0001\u0005`\"9AQ\u001d\u0001\u0005\u0002\u0011\u0015\u0005b\u0002Ct\u0001\u0011\u0005A\u0011\u001e\u0005\b\tc\u0004A\u0011\u0001Cz\u0011\u001d!9\u0010\u0001C\u0001\tsDq\u0001\"@\u0001\t\u0003!y\u0010C\u0004\u0006\u0006\u0001!\t!b\u0002\t\u000f\u0015\u0015\u0001\u0001\"\u0001\u0006\u0016!9Q1\u0004\u0001\u0005\u0002\u0011\u0015\u0005bBC\u000f\u0001\u0011\u0005Qq\u0004\u0005\b\u000bG\u0001A\u0011AC\u0013\u0011\u001d)Y\u0003\u0001C\u0001\u000b[Aq!\"\r\u0001\t\u0003)\u0019\u0004C\u0004\u0006<\u0001!\t\u0001\"\"\t\u000f\u0015u\u0002\u0001\"\u0001\u0006@!9QQ\b\u0001\u0005\u0002\u0015\u001d\u0003bBC&\u0001\u0011\u0005QQ\n\u0005\b\u000b\u0017\u0002A\u0011AC*\u0011\u001d)9\u0006\u0001C\u0001\t\u000bCq!\"\u0017\u0001\t\u0003)YfB\u0004\u0006juC\t!b\u001b\u0007\rqk\u0006\u0012AC7\u0011\u0019iX\u000b\"\u0001\u0006v!9QqO+\u0005\u0004\u0015e\u0004bBC?+\u0012\rQq\u0010\u0005\b\u000b\u000b+F\u0011ACD\u0011\u001d)9*\u0016C\u0001\u000b3C\u0001\"b(V\t\u0003\tW\u0011\u0015\u0002\u0011\u0015\u00064\u0018m\u00159be.\u001cuN\u001c;fqRT!AX0\u0002\t)\fg/\u0019\u0006\u0003A\u0006\f1!\u00199j\u0015\t\u00117-A\u0003ta\u0006\u00148N\u0003\u0002eK\u00061\u0011\r]1dQ\u0016T\u0011AZ\u0001\u0004_J<7\u0001A\n\u0004\u0001%\u0004\bC\u00016o\u001b\u0005Y'B\u00017n\u0003\u0011a\u0017M\\4\u000b\u0003yK!a\\6\u0003\r=\u0013'.Z2u!\t\tH/D\u0001s\u0015\t\u0019X.\u0001\u0002j_&\u0011QO\u001d\u0002\n\u00072|7/Z1cY\u0016\f!a]2\u0016\u0003a\u0004\"!\u001f>\u000e\u0003\u0005L!a_1\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002\u0007M\u001c\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004\u007f\u0006\r\u0001cAA\u0001\u00015\tQ\fC\u0003w\u0007\u0001\u0007\u0001\u0010F\u0001\u0000)\ry\u0018\u0011\u0002\u0005\b\u0003\u0017)\u0001\u0019AA\u0007\u0003\u0011\u0019wN\u001c4\u0011\u0007e\fy!C\u0002\u0002\u0012\u0005\u0014\u0011b\u00159be.\u001cuN\u001c4\u0015\u000b}\f)\"a\r\t\u000f\u0005]a\u00011\u0001\u0002\u001a\u00051Q.Y:uKJ\u0004B!a\u0007\u0002.9!\u0011QDA\u0015!\u0011\ty\"!\n\u000e\u0005\u0005\u0005\"bAA\u0012O\u00061AH]8pizR!!a\n\u0002\u000bM\u001c\u0017\r\\1\n\t\u0005-\u0012QE\u0001\u0007!J,G-\u001a4\n\t\u0005=\u0012\u0011\u0007\u0002\u0007'R\u0014\u0018N\\4\u000b\t\u0005-\u0012Q\u0005\u0005\b\u0003k1\u0001\u0019AA\r\u0003\u001d\t\u0007\u000f\u001d(b[\u0016$ra`A\u001d\u0003w\ti\u0004C\u0004\u0002\u0018\u001d\u0001\r!!\u0007\t\u000f\u0005Ur\u00011\u0001\u0002\u001a!9\u00111B\u0004A\u0002\u00055A#C@\u0002B\u0005\r\u0013QIA%\u0011\u001d\t9\u0002\u0003a\u0001\u00033Aq!!\u000e\t\u0001\u0004\tI\u0002C\u0004\u0002H!\u0001\r!!\u0007\u0002\u0013M\u0004\u0018M]6I_6,\u0007bBA&\u0011\u0001\u0007\u0011\u0011D\u0001\bU\u0006\u0014h)\u001b7f)%y\u0018qJA)\u0003'\n)\u0006C\u0004\u0002\u0018%\u0001\r!!\u0007\t\u000f\u0005U\u0012\u00021\u0001\u0002\u001a!9\u0011qI\u0005A\u0002\u0005e\u0001bBA,\u0013\u0001\u0007\u0011\u0011L\u0001\u0005U\u0006\u00148\u000f\u0005\u0004\u0002\\\u0005u\u0013\u0011D\u0007\u0003\u0003KIA!a\u0018\u0002&\t)\u0011I\u001d:bsRYq0a\u0019\u0002f\u0005\u001d\u0014\u0011NA6\u0011\u001d\t9B\u0003a\u0001\u00033Aq!!\u000e\u000b\u0001\u0004\tI\u0002C\u0004\u0002H)\u0001\r!!\u0007\t\u000f\u0005]#\u00021\u0001\u0002Z!9\u0011Q\u000e\u0006A\u0002\u0005=\u0014aC3om&\u0014xN\\7f]R\u0004\u0002\"!\u001d\u0002x\u0005e\u0011\u0011D\u0007\u0003\u0003gR1!!\u001en\u0003\u0011)H/\u001b7\n\t\u0005e\u00141\u000f\u0002\u0004\u001b\u0006\u0004\u0018aA3omV\u0011\u0011q\u0010\t\u0004s\u0006\u0005\u0015bAABC\nA1\u000b]1sW\u0016sg/\u0001\u0003f]Z\u0004\u0013!D:uCR,8\u000f\u0016:bG.,'/\u0006\u0002\u0002\fB!\u0011\u0011AAG\u0013\r\ty)\u0018\u0002\u0017\u0015\u00064\u0018m\u00159be.\u001cF/\u0019;vgR\u0013\u0018mY6fe\u00069\u0011n\u001d'pG\u0006dWCAAK!\rQ\u0017qS\u0005\u0004\u00033['a\u0002\"p_2,\u0017M\\\u0001\ngB\f'o[+tKJ,\"!!\u0007\u0002\u0013I,7o\\;sG\u0016\u001cXCAAR!!\t\t(a\u001e\u0002\u001a\u0005\u0015\u0006\u0003BAT\u0003[k!!!+\u000b\u0007\u0005-\u0016-\u0001\u0005sKN|WO]2f\u0013\u0011\ty+!+\u0003'I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8\u0016\u0005\u0005M\u0006CBA9\u0003k\u000bI\"\u0003\u0003\u00028\u0006M$\u0001\u0002'jgR\f\u0011b\u001d;beR$\u0016.\\3\u0016\u0005\u0005u\u0006c\u00016\u0002@&\u0019\u0011\u0011Y6\u0003\t1{gnZ\u0001\bm\u0016\u00148/[8o\u0003I!WMZ1vYR\u0004\u0016M]1mY\u0016d\u0017n]7\u0016\u0005\u0005%\u0007c\u00016\u0002L&\u0019\u0011QZ6\u0003\u000f%sG/Z4fe\u0006!B-\u001a4bk2$X*\u001b8QCJ$\u0018\u000e^5p]N\f1\u0002]1sC2dW\r\\5{KV!\u0011Q[Aq)\u0019\t9.a=\u0002zB1\u0011\u0011AAm\u0003;L1!a7^\u0005\u001dQ\u0015M^1S\t\u0012\u0003B!a8\u0002b2\u0001AaBAr1\t\u0007\u0011Q\u001d\u0002\u0002)F!\u0011q]Aw!\u0011\tY&!;\n\t\u0005-\u0018Q\u0005\u0002\b\u001d>$\b.\u001b8h!\u0011\tY&a<\n\t\u0005E\u0018Q\u0005\u0002\u0004\u0003:L\bbBA{1\u0001\u0007\u0011q_\u0001\u0005Y&\u001cH\u000f\u0005\u0004\u0002r\u0005U\u0016Q\u001c\u0005\b\u0003wD\u0002\u0019AA\u007f\u0003%qW/\\*mS\u000e,7\u000f\u0005\u0003\u0002\\\u0005}\u0018\u0002\u0002B\u0001\u0003K\u00111!\u00138u\u0003!)W\u000e\u001d;z%\u0012#U\u0003\u0002B\u0004\u0005\u001b)\"A!\u0003\u0011\r\u0005\u0005\u0011\u0011\u001cB\u0006!\u0011\tyN!\u0004\u0005\u000f\u0005\r\u0018D1\u0001\u0002fV!!\u0011\u0003B\f)\u0011\u0011\u0019B!\u0007\u0011\r\u0005\u0005\u0011\u0011\u001cB\u000b!\u0011\tyNa\u0006\u0005\u000f\u0005\r(D1\u0001\u0002f\"9\u0011Q\u001f\u000eA\u0002\tm\u0001CBA9\u0003k\u0013)\"\u0001\tqCJ\fG\u000e\\3mSj,\u0007+Y5sgV1!\u0011\u0005B\u0016\u0005c!bAa\t\u00036\t}\u0002\u0003CA\u0001\u0005K\u0011ICa\f\n\u0007\t\u001dRLA\u0006KCZ\f\u0007+Y5s%\u0012#\u0005\u0003BAp\u0005W!qA!\f\u001c\u0005\u0004\t)OA\u0001L!\u0011\tyN!\r\u0005\u000f\tM2D1\u0001\u0002f\n\ta\u000bC\u0004\u0002vn\u0001\rAa\u000e\u0011\r\u0005E\u0014Q\u0017B\u001d!!\tYFa\u000f\u0003*\t=\u0012\u0002\u0002B\u001f\u0003K\u0011a\u0001V;qY\u0016\u0014\u0004bBA~7\u0001\u0007\u0011Q`\u000b\u0007\u0005\u0007\u0012IE!\u0014\u0015\t\t\u0015#q\n\t\t\u0003\u0003\u0011)Ca\u0012\u0003LA!\u0011q\u001cB%\t\u001d\u0011i\u0003\bb\u0001\u0003K\u0004B!a8\u0003N\u00119!1\u0007\u000fC\u0002\u0005\u0015\bbBA{9\u0001\u0007!\u0011\u000b\t\u0007\u0003c\n)La\u0015\u0011\u0011\u0005m#1\bB$\u0005\u0017\n!\u0003]1sC2dW\r\\5{K\u0012{WO\u00197fgR1!\u0011\fB0\u0005S\u0002B!!\u0001\u0003\\%\u0019!QL/\u0003\u001b)\u000bg/\u0019#pk\ndWM\u0015#E\u0011\u001d\t)0\ba\u0001\u0005C\u0002b!!\u001d\u00026\n\r\u0004c\u00016\u0003f%\u0019!qM6\u0003\r\u0011{WO\u00197f\u0011\u001d\tY0\ba\u0001\u0003{$BA!\u0017\u0003n!9\u0011Q\u001f\u0010A\u0002\t\u0005\u0014\u0001\u0003;fqR4\u0015\u000e\\3\u0015\t\tM$Q\u000f\t\u0007\u0003\u0003\tI.!\u0007\t\u000f\t]t\u00041\u0001\u0002\u001a\u0005!\u0001/\u0019;i)\u0019\u0011\u0019Ha\u001f\u0003~!9!q\u000f\u0011A\u0002\u0005e\u0001b\u0002B@A\u0001\u0007\u0011Q`\u0001\u000e[&t\u0007+\u0019:uSRLwN\\:\u0002\u001d]Dw\u000e\\3UKb$h)\u001b7fgR1!Q\u0011BD\u0005\u0013\u0003\u0002\"!\u0001\u0003&\u0005e\u0011\u0011\u0004\u0005\b\u0005o\n\u0003\u0019AA\r\u0011\u001d\u0011y(\ta\u0001\u0003{$BA!\"\u0003\u000e\"9!q\u000f\u0012A\u0002\u0005e\u0011a\u00032j]\u0006\u0014\u0018PR5mKN$bAa%\u0003\"\n\r\u0006\u0003CA\u0001\u0005K\tIB!&\u0011\t\t]%QT\u0007\u0003\u00053S1Aa'b\u0003\u0015Ig\u000e];u\u0013\u0011\u0011yJ!'\u0003%A{'\u000f^1cY\u0016$\u0015\r^1TiJ,\u0017-\u001c\u0005\b\u0005o\u001a\u0003\u0019AA\r\u0011\u001d\u0011yh\ta\u0001\u0003{$BAa%\u0003(\"9!q\u000f\u0013A\u0002\u0005e\u0011!\u00042j]\u0006\u0014\u0018PU3d_J$7\u000f\u0006\u0004\u0003.\n]&\u0011\u0018\t\u0007\u0003\u0003\tINa,\u0011\r\u0005m\u0013Q\fBY!\u0011\tYFa-\n\t\tU\u0016Q\u0005\u0002\u0005\u0005f$X\rC\u0004\u0003x\u0015\u0002\r!!\u0007\t\u000f\tmV\u00051\u0001\u0002~\u0006a!/Z2pe\u0012dUM\\4uQ\u0006a1/Z9vK:\u001cWMR5mKV1!\u0011\u0019Bd\u0005\u0017$\"Ba1\u0003N\n='\u0011\u001cBp!!\t\tA!\n\u0003F\n%\u0007\u0003BAp\u0005\u000f$qA!\f'\u0005\u0004\t)\u000f\u0005\u0003\u0002`\n-Ga\u0002B\u001aM\t\u0007\u0011Q\u001d\u0005\b\u0005o2\u0003\u0019AA\r\u0011\u001d\u0011\tN\na\u0001\u0005'\f\u0001b[3z\u00072\f7o\u001d\t\u0007\u00037\u0011)N!2\n\t\t]\u0017\u0011\u0007\u0002\u0006\u00072\f7o\u001d\u0005\b\u000574\u0003\u0019\u0001Bo\u0003)1\u0018\r\\;f\u00072\f7o\u001d\t\u0007\u00037\u0011)N!3\t\u000f\t}d\u00051\u0001\u0002~V1!1\u001dBu\u0005[$\u0002B!:\u0003p\nE(Q\u001f\t\t\u0003\u0003\u0011)Ca:\u0003lB!\u0011q\u001cBu\t\u001d\u0011ic\nb\u0001\u0003K\u0004B!a8\u0003n\u00129!1G\u0014C\u0002\u0005\u0015\bb\u0002B<O\u0001\u0007\u0011\u0011\u0004\u0005\b\u0005#<\u0003\u0019\u0001Bz!\u0019\tYB!6\u0003h\"9!1\\\u0014A\u0002\t]\bCBA\u000e\u0005+\u0014Y/\u0001\u0006pE*,7\r\u001e$jY\u0016,BA!@\u0004\u0004Q1!q`B\u0003\u0007\u000f\u0001b!!\u0001\u0002Z\u000e\u0005\u0001\u0003BAp\u0007\u0007!q!a9)\u0005\u0004\t)\u000fC\u0004\u0003x!\u0002\r!!\u0007\t\u000f\t}\u0004\u00061\u0001\u0002~V!11BB\t)\u0011\u0019iaa\u0005\u0011\r\u0005\u0005\u0011\u0011\\B\b!\u0011\tyn!\u0005\u0005\u000f\u0005\r\u0018F1\u0001\u0002f\"9!qO\u0015A\u0002\u0005e\u0011!\u00035bI>|\u0007O\u0015#E+!\u0019Iba\b\u0004$\r}B\u0003DB\u000e\u0007K\u00199da\u0013\u0004P\rM\u0003\u0003CA\u0001\u0005K\u0019ib!\t\u0011\t\u0005}7q\u0004\u0003\b\u0005[Q#\u0019AAs!\u0011\tyna\t\u0005\u000f\tM\"F1\u0001\u0002f\"9\u00111\u0002\u0016A\u0002\r\u001d\u0002\u0003BB\u0015\u0007gi!aa\u000b\u000b\t\r52qF\u0001\u0007[\u0006\u0004(/\u001a3\u000b\u0007\rE2-\u0001\u0004iC\u0012|w\u000e]\u0005\u0005\u0007k\u0019YCA\u0004K_\n\u001cuN\u001c4\t\u000f\re\"\u00061\u0001\u0004<\u0005\u0001\u0012N\u001c9vi\u001a{'/\\1u\u00072\f7o\u001d\t\u0007\u00037\u0011)n!\u0010\u0011\t\u0005}7q\b\u0003\b\u0007\u0003R#\u0019AB\"\u0005\u00051\u0015\u0003BAt\u0007\u000b\u0002\u0002b!\u000b\u0004H\ru1\u0011E\u0005\u0005\u0007\u0013\u001aYCA\u0006J]B,HOR8s[\u0006$\bb\u0002BiU\u0001\u00071Q\n\t\u0007\u00037\u0011)n!\b\t\u000f\tm'\u00061\u0001\u0004RA1\u00111\u0004Bk\u0007CAqAa +\u0001\u0004\ti0\u0006\u0005\u0004X\ru3\u0011MB6))\u0019Ifa\u0019\u0004f\rE4Q\u000f\t\t\u0003\u0003\u0011)ca\u0017\u0004`A!\u0011q\\B/\t\u001d\u0011ic\u000bb\u0001\u0003K\u0004B!a8\u0004b\u00119!1G\u0016C\u0002\u0005\u0015\bbBA\u0006W\u0001\u00071q\u0005\u0005\b\u0007sY\u0003\u0019AB4!\u0019\tYB!6\u0004jA!\u0011q\\B6\t\u001d\u0019\te\u000bb\u0001\u0007[\nB!a:\u0004pAA1\u0011FB$\u00077\u001ay\u0006C\u0004\u0003R.\u0002\raa\u001d\u0011\r\u0005m!Q[B.\u0011\u001d\u0011Yn\u000ba\u0001\u0007o\u0002b!a\u0007\u0003V\u000e}\u0013A\u00035bI>|\u0007OR5mKVA1QPBB\u0007\u000f\u001b\t\n\u0006\u0007\u0004\u0000\r%51RBL\u00077\u001by\n\u0005\u0005\u0002\u0002\t\u00152\u0011QBC!\u0011\tyna!\u0005\u000f\t5BF1\u0001\u0002fB!\u0011q\\BD\t\u001d\u0011\u0019\u0004\fb\u0001\u0003KDqAa\u001e-\u0001\u0004\tI\u0002C\u0004\u0004:1\u0002\ra!$\u0011\r\u0005m!Q[BH!\u0011\tyn!%\u0005\u000f\r\u0005CF1\u0001\u0004\u0014F!\u0011q]BK!!\u0019Ica\u0012\u0004\u0002\u000e\u0015\u0005b\u0002BiY\u0001\u00071\u0011\u0014\t\u0007\u00037\u0011)n!!\t\u000f\tmG\u00061\u0001\u0004\u001eB1\u00111\u0004Bk\u0007\u000bCqAa -\u0001\u0004\ti0\u0006\u0005\u0004$\u000e%6QVB\\))\u0019)ka,\u00042\u000eu6\u0011\u0019\t\t\u0003\u0003\u0011)ca*\u0004,B!\u0011q\\BU\t\u001d\u0011i#\fb\u0001\u0003K\u0004B!a8\u0004.\u00129!1G\u0017C\u0002\u0005\u0015\bb\u0002B<[\u0001\u0007\u0011\u0011\u0004\u0005\b\u0007si\u0003\u0019ABZ!\u0019\tYB!6\u00046B!\u0011q\\B\\\t\u001d\u0019\t%\fb\u0001\u0007s\u000bB!a:\u0004<BA1\u0011FB$\u0007O\u001bY\u000bC\u0004\u0003R6\u0002\raa0\u0011\r\u0005m!Q[BT\u0011\u001d\u0011Y.\fa\u0001\u0007\u0007\u0004b!a\u0007\u0003V\u000e-\u0016\u0001\u00058fo\u0006\u0003\u0016\nS1e_>\u0004h)\u001b7f+!\u0019Ima4\u0004T\u000e}G\u0003DBf\u0007+\u001c9n!<\u0004t\u000ee\b\u0003CA\u0001\u0005K\u0019im!5\u0011\t\u0005}7q\u001a\u0003\b\u0005[q#\u0019AAs!\u0011\tyna5\u0005\u000f\tMbF1\u0001\u0002f\"9!q\u000f\u0018A\u0002\u0005e\u0001bBBm]\u0001\u000711\\\u0001\u0007M\u000ec\u0017m]:\u0011\r\u0005m!Q[Bo!\u0011\tyna8\u0005\u000f\r\u0005cF1\u0001\u0004bF!\u0011q]Br!!\u0019)oa;\u0004N\u000eEWBABt\u0015\u0011\u0019Ioa\f\u0002\u00135\f\u0007O]3ek\u000e,\u0017\u0002BB%\u0007ODqaa</\u0001\u0004\u0019\t0\u0001\u0004l\u00072\f7o\u001d\t\u0007\u00037\u0011)n!4\t\u000f\rUh\u00061\u0001\u0004x\u00061ao\u00117bgN\u0004b!a\u0007\u0003V\u000eE\u0007bBA\u0006]\u0001\u000711 \t\u0005\u0007{$\t!\u0004\u0002\u0004\u0000*!\u00111BB\u0018\u0013\u0011!\u0019aa@\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003=qWm^!Q\u0013\"\u000bGm\\8q%\u0012#U\u0003\u0003C\u0005\t\u001f!\u0019\u0002\"\b\u0015\u0015\u0011-AQ\u0003C\f\tG!9\u0003\u0005\u0005\u0002\u0002\t\u0015BQ\u0002C\t!\u0011\ty\u000eb\u0004\u0005\u000f\t5rF1\u0001\u0002fB!\u0011q\u001cC\n\t\u001d\u0011\u0019d\fb\u0001\u0003KDq!a\u00030\u0001\u0004\u0019Y\u0010C\u0004\u0004Z>\u0002\r\u0001\"\u0007\u0011\r\u0005m!Q\u001bC\u000e!\u0011\ty\u000e\"\b\u0005\u000f\r\u0005sF1\u0001\u0005 E!\u0011q\u001dC\u0011!!\u0019)oa;\u0005\u000e\u0011E\u0001bBBx_\u0001\u0007AQ\u0005\t\u0007\u00037\u0011)\u000e\"\u0004\t\u000f\rUx\u00061\u0001\u0005*A1\u00111\u0004Bk\t#\tQ!\u001e8j_:,B\u0001b\f\u00056Q!A\u0011\u0007C\u001c!\u0019\t\t!!7\u00054A!\u0011q\u001cC\u001b\t\u001d\t\u0019\u000f\rb\u0001\u0003KDq\u0001\"\u000f1\u0001\u0004!Y$\u0001\u0003sI\u0012\u001c\bCBA.\t{!\t$\u0003\u0003\u0005@\u0005\u0015\"A\u0003\u001fsKB,\u0017\r^3e}!\u001a\u0001\u0007b\u0011\u0011\t\u0011\u0015C1J\u0007\u0003\t\u000fRA\u0001\"\u0013\u0002&\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u00115Cq\t\u0002\bm\u0006\u0014\u0018M]4t+\u0019!\t\u0006b\u0016\u0005\\Q!A1\u000bC/!!\t\tA!\n\u0005V\u0011e\u0003\u0003BAp\t/\"qA!\f2\u0005\u0004\t)\u000f\u0005\u0003\u0002`\u0012mCa\u0002B\u001ac\t\u0007\u0011Q\u001d\u0005\b\ts\t\u0004\u0019\u0001C0!\u0019\tY\u0006\"\u0010\u0005T!\u001a\u0011\u0007b\u0011\u0015\t\teCQ\r\u0005\b\ts\u0011\u0004\u0019\u0001C4!\u0019\tY\u0006\"\u0010\u0003Z!\u001a!\u0007b\u0011\u0002\u0013\t\u0014x.\u00193dCN$X\u0003\u0002C8\t{\"B\u0001\"\u001d\u0005\u0000A1A1\u000fC<\twj!\u0001\"\u001e\u000b\u0007\u0011-\u0014-\u0003\u0003\u0005z\u0011U$!\u0003\"s_\u0006$7-Y:u!\u0011\ty\u000e\" \u0005\u000f\u0005\r8G1\u0001\u0002f\"9A\u0011Q\u001aA\u0002\u0011m\u0014!\u0002<bYV,\u0017\u0001B:u_B$\"\u0001b\"\u0011\t\u0005mC\u0011R\u0005\u0005\t\u0017\u000b)C\u0001\u0003V]&$\u0018!B2m_N,\u0017\u0001D4fiN\u0003\u0018M]6I_6,GC\u0001CJ!\u0019\t\t\u0001\"&\u0002\u001a%\u0019AqS/\u0003\u0011=\u0003H/[8oC2\fq!\u00193e\r&dW\r\u0006\u0003\u0005\b\u0012u\u0005b\u0002B<o\u0001\u0007\u0011\u0011\u0004\u000b\u0007\t\u000f#\t\u000bb)\t\u000f\t]\u0004\b1\u0001\u0002\u001a!9AQ\u0015\u001dA\u0002\u0011\u001d\u0016!\u0003:fGV\u00148/\u001b<f!\u0011\tY\u0006\"+\n\t\u0005e\u0015QE\u0001\u0007C\u0012$'*\u0019:\u0015\t\u0011\u001dEq\u0016\u0005\b\u0005oJ\u0004\u0019AA\r\u0003MA\u0017\rZ8pa\u000e{gNZ5hkJ\fG/[8o)\t\u0019Y0\u0001\ttKR\u001c\u0005.Z2la>Lg\u000e\u001e#jeR!Aq\u0011C]\u0011\u001d!Yl\u000fa\u0001\u00033\t1\u0001Z5s\u0003A9W\r^\"iK\u000e\\\u0007o\\5oi\u0012K'/\u0006\u0002\u0005\u0014\u0006q1\r[3dWB|\u0017N\u001c;GS2,W\u0003\u0002Cc\t\u0017$B\u0001b2\u0005NB1\u0011\u0011AAm\t\u0013\u0004B!a8\u0005L\u00129\u00111]\u001fC\u0002\u0005\u0015\bb\u0002B<{\u0001\u0007\u0011\u0011D\u0001\bO\u0016$8i\u001c8g+\t\ti!A\bhKR\u0014V-\u00193P]2L8i\u001c8g+\t!9\u000eE\u0002z\t3L1\u0001b7b\u0005E\u0011V-\u00193P]2L8\u000b]1sW\u000e{gNZ\u0001\fg\u0016$8)\u00197m'&$X\r\u0006\u0003\u0005\b\u0012\u0005\bb\u0002Cr\u0001\u0002\u0007\u0011\u0011D\u0001\u0005g&$X-A\u0007dY\u0016\f'oQ1mYNKG/Z\u0001\u0011g\u0016$Hj\\2bYB\u0013x\u000e]3sif$b\u0001b\"\u0005l\u0012=\bb\u0002Cw\u0005\u0002\u0007\u0011\u0011D\u0001\u0004W\u0016L\bb\u0002CA\u0005\u0002\u0007\u0011\u0011D\u0001\u0011O\u0016$Hj\\2bYB\u0013x\u000e]3sif$B!!\u0007\u0005v\"9AQ^\"A\u0002\u0005e\u0011!E:fi*{'\rR3tGJL\u0007\u000f^5p]R!Aq\u0011C~\u0011\u001d!\t\t\u0012a\u0001\u00033\t1b]3u\u0019><G*\u001a<fYR!AqQC\u0001\u0011\u001d)\u0019!\u0012a\u0001\u00033\t\u0001\u0002\\8h\u0019\u00164X\r\\\u0001\fg\u0016$(j\u001c2He>,\b\u000f\u0006\u0005\u0005\b\u0016%QQBC\t\u0011\u001d)YA\u0012a\u0001\u00033\tqa\u001a:pkBLE\rC\u0004\u0006\u0010\u0019\u0003\r!!\u0007\u0002\u0017\u0011,7o\u0019:jaRLwN\u001c\u0005\b\u000b'1\u0005\u0019\u0001CT\u0003EIg\u000e^3seV\u0004Ho\u00148DC:\u001cW\r\u001c\u000b\u0007\t\u000f+9\"\"\u0007\t\u000f\u0015-q\t1\u0001\u0002\u001a!9QqB$A\u0002\u0005e\u0011!D2mK\u0006\u0014(j\u001c2He>,\b/\u0001\u000btKRLe\u000e^3seV\u0004Ho\u00148DC:\u001cW\r\u001c\u000b\u0005\t\u000f+\t\u0003C\u0004\u0006\u0014%\u0003\r\u0001b*\u0002\u0013\u0005$GMS8c)\u0006<G\u0003\u0002CD\u000bOAq!\"\u000bK\u0001\u0004\tI\"A\u0002uC\u001e\fAB]3n_Z,'j\u001c2UC\u001e$B\u0001b\"\u00060!9Q\u0011F&A\u0002\u0005e\u0011AC4fi*{'\rV1hgR\u0011QQ\u0007\t\u0007\u0003c*9$!\u0007\n\t\u0015e\u00121\u000f\u0002\u0004'\u0016$\u0018\u0001D2mK\u0006\u0014(j\u001c2UC\u001e\u001c\u0018AD2b]\u000e,GNS8c\u000fJ|W\u000f\u001d\u000b\u0007\t\u000f+\t%b\u0011\t\u000f\u0015-a\n1\u0001\u0002\u001a!9QQ\t(A\u0002\u0005e\u0011A\u0002:fCN|g\u000e\u0006\u0003\u0005\b\u0016%\u0003bBC\u0006\u001f\u0002\u0007\u0011\u0011D\u0001\u0012G\u0006t7-\u001a7K_\n\u001cx+\u001b;i)\u0006<GC\u0002CD\u000b\u001f*\t\u0006C\u0004\u0006*A\u0003\r!!\u0007\t\u000f\u0015\u0015\u0003\u000b1\u0001\u0002\u001aQ!AqQC+\u0011\u001d)I#\u0015a\u0001\u00033\tQbY1oG\u0016d\u0017\t\u001c7K_\n\u001c\u0018!E4fiB+'o]5ti\u0016tGO\u0015#EgV\u0011QQ\f\t\t\u0003c\n9(!3\u0006`A\"Q\u0011MC3!\u0019\t\t!!7\u0006dA!\u0011q\\C3\t-)9gUA\u0001\u0002\u0003\u0015\t!!:\u0003\u0007}#\u0013'\u0001\tKCZ\f7\u000b]1sW\u000e{g\u000e^3yiB\u0019\u0011\u0011A+\u0014\u0007U+y\u0007\u0005\u0003\u0002\\\u0015E\u0014\u0002BC:\u0003K\u0011a!\u00118z%\u00164GCAC6\u0003A1'o\\7Ta\u0006\u00148nQ8oi\u0016DH\u000fF\u0002\u0000\u000bwBQA^,A\u0002a\fa\u0002^8Ta\u0006\u00148nQ8oi\u0016DH\u000fF\u0002y\u000b\u0003Ca!b!Y\u0001\u0004y\u0018a\u00016tG\u0006Q!.\u0019:PM\u000ec\u0017m]:\u0015\t\u0005eS\u0011\u0012\u0005\b\u000b\u0017K\u0006\u0019ACG\u0003\r\u0019Gn\u001d\u0019\u0005\u000b\u001f+\u0019\n\u0005\u0004\u0002\u001c\tUW\u0011\u0013\t\u0005\u0003?,\u0019\n\u0002\u0007\u0006\u0016\u0016%\u0015\u0011!A\u0001\u0006\u0003\t)OA\u0002`IM\n1B[1s\u001f\u001a|%M[3diR!\u0011\u0011LCN\u0011\u001d)iJ\u0017a\u0001\u000b_\n1a\u001c2k\u000311\u0017m[3DY\u0006\u001c8\u000fV1h+\u0011)\u0019+b-\u0016\u0005\u0015\u0015\u0006CBCT\u000b[+\t,\u0004\u0002\u0006**!Q1VA\u0013\u0003\u001d\u0011XM\u001a7fGRLA!b,\u0006*\nA1\t\\1tgR\u000bw\r\u0005\u0003\u0002`\u0016MFaBAr7\n\u0007\u0011Q\u001d"
)
public class JavaSparkContext implements Closeable {
   private final SparkContext sc;
   private final SparkEnv env;

   public static String[] jarOfObject(final Object obj) {
      return JavaSparkContext$.MODULE$.jarOfObject(obj);
   }

   public static String[] jarOfClass(final Class cls) {
      return JavaSparkContext$.MODULE$.jarOfClass(cls);
   }

   public static SparkContext toSparkContext(final JavaSparkContext jsc) {
      return JavaSparkContext$.MODULE$.toSparkContext(jsc);
   }

   public static JavaSparkContext fromSparkContext(final SparkContext sc) {
      return JavaSparkContext$.MODULE$.fromSparkContext(sc);
   }

   public JavaRDD union(final JavaRDD... rdds) {
      return this.union((Seq).MODULE$.wrapRefArray(rdds));
   }

   public JavaPairRDD union(final JavaPairRDD... rdds) {
      return this.union((Seq).MODULE$.wrapRefArray(rdds));
   }

   public JavaDoubleRDD union(final JavaDoubleRDD... rdds) {
      return this.union((Seq).MODULE$.wrapRefArray(rdds));
   }

   public SparkContext sc() {
      return this.sc;
   }

   public SparkEnv env() {
      return this.env;
   }

   public JavaSparkStatusTracker statusTracker() {
      return new JavaSparkStatusTracker(this.sc());
   }

   public Boolean isLocal() {
      return scala.Predef..MODULE$.boolean2Boolean(this.sc().isLocal());
   }

   public String sparkUser() {
      return this.sc().sparkUser();
   }

   public String master() {
      return this.sc().master();
   }

   public String appName() {
      return this.sc().appName();
   }

   public Map resources() {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.sc().resources()).asJava();
   }

   public List jars() {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(this.sc().jars()).asJava();
   }

   public Long startTime() {
      return scala.Predef..MODULE$.long2Long(this.sc().startTime());
   }

   public String version() {
      return this.sc().version();
   }

   public Integer defaultParallelism() {
      return scala.Predef..MODULE$.int2Integer(this.sc().defaultParallelism());
   }

   public Integer defaultMinPartitions() {
      return scala.Predef..MODULE$.int2Integer(this.sc().defaultMinPartitions());
   }

   public JavaRDD parallelize(final List list, final int numSlices) {
      ClassTag ctag = JavaSparkContext$.MODULE$.fakeClassTag();
      return JavaRDD$.MODULE$.fromRDD(this.sc().parallelize(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(list).asScala().toSeq(), numSlices, ctag), ctag);
   }

   public JavaRDD emptyRDD() {
      ClassTag ctag = JavaSparkContext$.MODULE$.fakeClassTag();
      return JavaRDD$.MODULE$.fromRDD(new EmptyRDD(this.sc(), ctag), ctag);
   }

   public JavaRDD parallelize(final List list) {
      return this.parallelize(list, this.sc().defaultParallelism());
   }

   public JavaPairRDD parallelizePairs(final List list, final int numSlices) {
      ClassTag ctagK = JavaSparkContext$.MODULE$.fakeClassTag();
      ClassTag ctagV = JavaSparkContext$.MODULE$.fakeClassTag();
      return JavaPairRDD$.MODULE$.fromRDD(this.sc().parallelize(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(list).asScala().toSeq(), numSlices, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), ctagK, ctagV);
   }

   public JavaPairRDD parallelizePairs(final List list) {
      return this.parallelizePairs(list, this.sc().defaultParallelism());
   }

   public JavaDoubleRDD parallelizeDoubles(final List list, final int numSlices) {
      return JavaDoubleRDD$.MODULE$.fromRDD(this.sc().parallelize(((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(list).asScala().map((x$1) -> BoxesRunTime.boxToDouble($anonfun$parallelizeDoubles$1(x$1)))).toSeq(), numSlices, scala.reflect.ClassTag..MODULE$.Double()));
   }

   public JavaDoubleRDD parallelizeDoubles(final List list) {
      return this.parallelizeDoubles(list, this.sc().defaultParallelism());
   }

   public JavaRDD textFile(final String path) {
      return JavaRDD$.MODULE$.fromRDD(this.sc().textFile(path, this.sc().textFile$default$2()), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public JavaRDD textFile(final String path, final int minPartitions) {
      return JavaRDD$.MODULE$.fromRDD(this.sc().textFile(path, minPartitions), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public JavaPairRDD wholeTextFiles(final String path, final int minPartitions) {
      return new JavaPairRDD(this.sc().wholeTextFiles(path, minPartitions), scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public JavaPairRDD wholeTextFiles(final String path) {
      return new JavaPairRDD(this.sc().wholeTextFiles(path, this.sc().wholeTextFiles$default$2()), scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public JavaPairRDD binaryFiles(final String path, final int minPartitions) {
      return new JavaPairRDD(this.sc().binaryFiles(path, minPartitions), scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.apply(PortableDataStream.class));
   }

   public JavaPairRDD binaryFiles(final String path) {
      return new JavaPairRDD(this.sc().binaryFiles(path, scala.Predef..MODULE$.Integer2int(this.defaultMinPartitions())), scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.apply(PortableDataStream.class));
   }

   public JavaRDD binaryRecords(final String path, final int recordLength) {
      return new JavaRDD(this.sc().binaryRecords(path, recordLength, this.sc().binaryRecords$default$3()), scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Byte.TYPE)));
   }

   public JavaPairRDD sequenceFile(final String path, final Class keyClass, final Class valueClass, final int minPartitions) {
      ClassTag ctagK = scala.reflect.ClassTag..MODULE$.apply(keyClass);
      ClassTag ctagV = scala.reflect.ClassTag..MODULE$.apply(valueClass);
      return new JavaPairRDD(this.sc().sequenceFile(path, keyClass, valueClass, minPartitions), ctagK, ctagV);
   }

   public JavaPairRDD sequenceFile(final String path, final Class keyClass, final Class valueClass) {
      ClassTag ctagK = scala.reflect.ClassTag..MODULE$.apply(keyClass);
      ClassTag ctagV = scala.reflect.ClassTag..MODULE$.apply(valueClass);
      return new JavaPairRDD(this.sc().sequenceFile(path, keyClass, valueClass), ctagK, ctagV);
   }

   public JavaRDD objectFile(final String path, final int minPartitions) {
      ClassTag ctag = JavaSparkContext$.MODULE$.fakeClassTag();
      return JavaRDD$.MODULE$.fromRDD(this.sc().objectFile(path, minPartitions, ctag), ctag);
   }

   public JavaRDD objectFile(final String path) {
      ClassTag ctag = JavaSparkContext$.MODULE$.fakeClassTag();
      return JavaRDD$.MODULE$.fromRDD(this.sc().objectFile(path, this.sc().objectFile$default$2(), ctag), ctag);
   }

   public JavaPairRDD hadoopRDD(final JobConf conf, final Class inputFormatClass, final Class keyClass, final Class valueClass, final int minPartitions) {
      ClassTag ctagK = scala.reflect.ClassTag..MODULE$.apply(keyClass);
      ClassTag ctagV = scala.reflect.ClassTag..MODULE$.apply(valueClass);
      RDD rdd = this.sc().hadoopRDD(conf, inputFormatClass, keyClass, valueClass, minPartitions);
      return new JavaHadoopRDD((HadoopRDD)rdd, ctagK, ctagV);
   }

   public JavaPairRDD hadoopRDD(final JobConf conf, final Class inputFormatClass, final Class keyClass, final Class valueClass) {
      ClassTag ctagK = scala.reflect.ClassTag..MODULE$.apply(keyClass);
      ClassTag ctagV = scala.reflect.ClassTag..MODULE$.apply(valueClass);
      RDD rdd = this.sc().hadoopRDD(conf, inputFormatClass, keyClass, valueClass, this.sc().hadoopRDD$default$5());
      return new JavaHadoopRDD((HadoopRDD)rdd, ctagK, ctagV);
   }

   public JavaPairRDD hadoopFile(final String path, final Class inputFormatClass, final Class keyClass, final Class valueClass, final int minPartitions) {
      ClassTag ctagK = scala.reflect.ClassTag..MODULE$.apply(keyClass);
      ClassTag ctagV = scala.reflect.ClassTag..MODULE$.apply(valueClass);
      RDD rdd = this.sc().hadoopFile(path, inputFormatClass, keyClass, valueClass, minPartitions);
      return new JavaHadoopRDD((HadoopRDD)rdd, ctagK, ctagV);
   }

   public JavaPairRDD hadoopFile(final String path, final Class inputFormatClass, final Class keyClass, final Class valueClass) {
      ClassTag ctagK = scala.reflect.ClassTag..MODULE$.apply(keyClass);
      ClassTag ctagV = scala.reflect.ClassTag..MODULE$.apply(valueClass);
      RDD rdd = this.sc().hadoopFile(path, inputFormatClass, keyClass, valueClass, this.sc().hadoopFile$default$5());
      return new JavaHadoopRDD((HadoopRDD)rdd, ctagK, ctagV);
   }

   public JavaPairRDD newAPIHadoopFile(final String path, final Class fClass, final Class kClass, final Class vClass, final Configuration conf) {
      ClassTag ctagK = scala.reflect.ClassTag..MODULE$.apply(kClass);
      ClassTag ctagV = scala.reflect.ClassTag..MODULE$.apply(vClass);
      RDD rdd = this.sc().newAPIHadoopFile(path, fClass, kClass, vClass, conf);
      return new JavaNewHadoopRDD((NewHadoopRDD)rdd, ctagK, ctagV);
   }

   public JavaPairRDD newAPIHadoopRDD(final Configuration conf, final Class fClass, final Class kClass, final Class vClass) {
      ClassTag ctagK = scala.reflect.ClassTag..MODULE$.apply(kClass);
      ClassTag ctagV = scala.reflect.ClassTag..MODULE$.apply(vClass);
      RDD rdd = this.sc().newAPIHadoopRDD(conf, fClass, kClass, vClass);
      return new JavaNewHadoopRDD((NewHadoopRDD)rdd, ctagK, ctagV);
   }

   public JavaRDD union(final Seq rdds) {
      scala.Predef..MODULE$.require(rdds.nonEmpty(), () -> "Union called on no RDDs");
      ClassTag ctag = ((JavaRDD)rdds.head()).classTag();
      return JavaRDD$.MODULE$.fromRDD(this.sc().union((Seq)rdds.map((x$2) -> x$2.rdd()), ctag), ctag);
   }

   public JavaPairRDD union(final Seq rdds) {
      scala.Predef..MODULE$.require(rdds.nonEmpty(), () -> "Union called on no RDDs");
      ClassTag ctag = ((JavaPairRDD)rdds.head()).classTag();
      ClassTag ctagK = ((JavaPairRDD)rdds.head()).kClassTag();
      ClassTag ctagV = ((JavaPairRDD)rdds.head()).vClassTag();
      return new JavaPairRDD(this.sc().union((Seq)rdds.map((x$3) -> x$3.rdd()), ctag), ctagK, ctagV);
   }

   public JavaDoubleRDD union(final Seq rdds) {
      scala.Predef..MODULE$.require(rdds.nonEmpty(), () -> "Union called on no RDDs");
      return new JavaDoubleRDD(this.sc().union((Seq)rdds.map((x$4) -> x$4.srdd()), scala.reflect.ClassTag..MODULE$.Double()));
   }

   public Broadcast broadcast(final Object value) {
      return this.sc().broadcast(value, JavaSparkContext$.MODULE$.fakeClassTag());
   }

   public void stop() {
      this.sc().stop();
   }

   public void close() {
      this.stop();
   }

   public Optional getSparkHome() {
      return JavaUtils$.MODULE$.optionToOptional(this.sc().getSparkHome());
   }

   public void addFile(final String path) {
      this.sc().addFile(path);
   }

   public void addFile(final String path, final boolean recursive) {
      this.sc().addFile(path, recursive);
   }

   public void addJar(final String path) {
      this.sc().addJar(path);
   }

   public Configuration hadoopConfiguration() {
      return this.sc().hadoopConfiguration();
   }

   public void setCheckpointDir(final String dir) {
      this.sc().setCheckpointDir(dir);
   }

   public Optional getCheckpointDir() {
      return JavaUtils$.MODULE$.optionToOptional(this.sc().getCheckpointDir());
   }

   public JavaRDD checkpointFile(final String path) {
      ClassTag ctag = JavaSparkContext$.MODULE$.fakeClassTag();
      return new JavaRDD(this.sc().checkpointFile(path, ctag), ctag);
   }

   public SparkConf getConf() {
      return this.sc().getConf();
   }

   public ReadOnlySparkConf getReadOnlyConf() {
      return this.sc().getReadOnlyConf();
   }

   public void setCallSite(final String site) {
      this.sc().setCallSite(site);
   }

   public void clearCallSite() {
      this.sc().clearCallSite();
   }

   public void setLocalProperty(final String key, final String value) {
      this.sc().setLocalProperty(key, value);
   }

   public String getLocalProperty(final String key) {
      return this.sc().getLocalProperty(key);
   }

   public void setJobDescription(final String value) {
      this.sc().setJobDescription(value);
   }

   public void setLogLevel(final String logLevel) {
      this.sc().setLogLevel(logLevel);
   }

   public void setJobGroup(final String groupId, final String description, final boolean interruptOnCancel) {
      this.sc().setJobGroup(groupId, description, interruptOnCancel);
   }

   public void setJobGroup(final String groupId, final String description) {
      this.sc().setJobGroup(groupId, description, this.sc().setJobGroup$default$3());
   }

   public void clearJobGroup() {
      this.sc().clearJobGroup();
   }

   public void setInterruptOnCancel(final boolean interruptOnCancel) {
      this.sc().setInterruptOnCancel(interruptOnCancel);
   }

   public void addJobTag(final String tag) {
      this.sc().addJobTag(tag);
   }

   public void removeJobTag(final String tag) {
      this.sc().removeJobTag(tag);
   }

   public Set getJobTags() {
      return scala.jdk.CollectionConverters..MODULE$.SetHasAsJava(this.sc().getJobTags()).asJava();
   }

   public void clearJobTags() {
      this.sc().clearJobTags();
   }

   public void cancelJobGroup(final String groupId, final String reason) {
      this.sc().cancelJobGroup(groupId, reason);
   }

   public void cancelJobGroup(final String groupId) {
      this.sc().cancelJobGroup(groupId);
   }

   public void cancelJobsWithTag(final String tag, final String reason) {
      this.sc().cancelJobsWithTag(tag, reason);
   }

   public void cancelJobsWithTag(final String tag) {
      this.sc().cancelJobsWithTag(tag);
   }

   public void cancelAllJobs() {
      this.sc().cancelAllJobs();
   }

   public Map getPersistentRDDs() {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map)this.sc().getPersistentRDDs().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$5, s) -> $anonfun$getPersistentRDDs$1(BoxesRunTime.unboxToInt(x$5), s))).asJava();
   }

   // $FF: synthetic method
   public static final double $anonfun$parallelizeDoubles$1(final Double x$1) {
      return x$1;
   }

   // $FF: synthetic method
   public static final JavaRDD $anonfun$getPersistentRDDs$1(final int x$5, final RDD s) {
      return JavaRDD$.MODULE$.fromRDD(s, scala.reflect.ClassTag..MODULE$.apply(Object.class));
   }

   public JavaSparkContext(final SparkContext sc) {
      this.sc = sc;
      this.env = sc.env();
   }

   public JavaSparkContext() {
      this(new SparkContext());
   }

   public JavaSparkContext(final SparkConf conf) {
      this(new SparkContext(conf));
   }

   public JavaSparkContext(final String master, final String appName) {
      this(new SparkContext(master, appName));
   }

   public JavaSparkContext(final String master, final String appName, final SparkConf conf) {
      this(conf.setMaster(master).setAppName(appName));
   }

   public JavaSparkContext(final String master, final String appName, final String sparkHome, final String jarFile) {
      this(new SparkContext(master, appName, sparkHome, new scala.collection.immutable..colon.colon(jarFile, scala.collection.immutable.Nil..MODULE$)));
   }

   public JavaSparkContext(final String master, final String appName, final String sparkHome, final String[] jars) {
      this(new SparkContext(master, appName, sparkHome, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(jars).toImmutableArraySeq()));
   }

   public JavaSparkContext(final String master, final String appName, final String sparkHome, final String[] jars, final Map environment) {
      this(new SparkContext(master, appName, sparkHome, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(jars).toImmutableArraySeq(), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(environment).asScala()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
