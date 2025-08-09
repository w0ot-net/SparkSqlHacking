package org.apache.spark.mllib.random;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.RDD;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\rs!\u0002-Z\u0011\u0003!g!\u00024Z\u0011\u00039\u0007\"\u00028\u0002\t\u0003y\u0007\"\u00029\u0002\t\u0003\t\b\"CA\u0017\u0003E\u0005I\u0011AA\u0018\u0011%\t\u0019%AI\u0001\n\u0003\t)\u0005C\u0004\u0002J\u0005!\t!a\u0013\t\u000f\u0005%\u0013\u0001\"\u0001\u0002p!9\u0011\u0011J\u0001\u0005\u0002\u0005e\u0004bBAA\u0003\u0011\u0005\u00111\u0011\u0005\n\u0003\u001f\u000b\u0011\u0013!C\u0001\u0003_A\u0011\"!%\u0002#\u0003%\t!!\u0012\t\u000f\u0005M\u0015\u0001\"\u0001\u0002\u0016\"9\u00111S\u0001\u0005\u0002\u0005\u0005\u0006bBAJ\u0003\u0011\u0005\u00111\u0016\u0005\b\u0003g\u000bA\u0011AA[\u0011%\t)-AI\u0001\n\u0003\ty\u0003C\u0005\u0002H\u0006\t\n\u0011\"\u0001\u0002F!9\u0011\u0011Z\u0001\u0005\u0002\u0005-\u0007bBAe\u0003\u0011\u0005\u0011\u0011\u001c\u0005\b\u0003\u0013\fA\u0011AAs\u0011\u001d\ty/\u0001C\u0001\u0003cD\u0011Ba\u0001\u0002#\u0003%\t!a\f\t\u0013\t\u0015\u0011!%A\u0005\u0002\u0005\u0015\u0003b\u0002B\u0004\u0003\u0011\u0005!\u0011\u0002\u0005\b\u0005\u000f\tA\u0011\u0001B\f\u0011\u001d\u00119!\u0001C\u0001\u0005GAqA!\f\u0002\t\u0003\u0011y\u0003C\u0005\u0003D\u0005\t\n\u0011\"\u0001\u00020!I!QI\u0001\u0012\u0002\u0013\u0005\u0011Q\t\u0005\b\u0005\u000f\nA\u0011\u0001B%\u0011\u001d\u00119%\u0001C\u0001\u00053BqAa\u0012\u0002\t\u0003\u00119\u0007C\u0004\u0003t\u0005!\tA!\u001e\t\u0013\t\u001d\u0015!%A\u0005\u0002\u0005=\u0002\"\u0003BE\u0003E\u0005I\u0011AA#\u0011\u001d\u0011Y)\u0001C\u0001\u0005\u001bCqAa#\u0002\t\u0003\u0011i\nC\u0004\u0003\f\u0006!\tAa+\t\u000f\t]\u0016\u0001\"\u0001\u0003:\"I!1`\u0001\u0012\u0002\u0013\u0005!Q \u0005\n\u0007\u0003\t\u0011\u0013!C\u0001\u0007\u0007Aqaa\u0002\u0002\t\u0003\u0019I\u0001C\u0004\u0004\b\u0005!\ta!\u000b\t\u000f\r\u001d\u0011\u0001\"\u0001\u0004@!911K\u0001\u0005\u0002\rU\u0003\"CB;\u0003E\u0005I\u0011AA\u0018\u0011%\u00199(AI\u0001\n\u0003\t)\u0005C\u0004\u0004z\u0005!\taa\u001f\t\u000f\re\u0014\u0001\"\u0001\u0004\f\"91\u0011P\u0001\u0005\u0002\r]\u0005bBBQ\u0003\u0011\u000511\u0015\u0005\n\u0007c\u000b\u0011\u0013!C\u0001\u0003_A\u0011ba-\u0002#\u0003%\t!!\u0012\t\u000f\rU\u0016\u0001\"\u0001\u00048\"91QW\u0001\u0005\u0002\r\u0015\u0007bBB[\u0003\u0011\u00051\u0011\u001b\u0005\b\u00077\fA\u0011ABo\u0011%\u0019y/AI\u0001\n\u0003\ty\u0003C\u0005\u0004r\u0006\t\n\u0011\"\u0001\u0002F!911_\u0001\u0005\u0002\rU\bbBBz\u0003\u0011\u0005Aq\u0001\u0005\b\u0007g\fA\u0011\u0001C\f\u0011\u001d!)#\u0001C\u0001\tOA\u0011\u0002b\u000e\u0002#\u0003%\t!a\f\t\u0013\u0011e\u0012!%A\u0005\u0002\u0005\u0015\u0003b\u0002C\u001e\u0003\u0011\u0005AQ\b\u0005\b\tw\tA\u0011\u0001C'\u0011\u001d!Y$\u0001C\u0001\t7Bq\u0001b\u001a\u0002\t\u0003!I\u0007C\u0005\u0005z\u0005\t\n\u0011\"\u0001\u00020!IA1P\u0001\u0012\u0002\u0013\u0005\u0011Q\t\u0005\b\t{\nA\u0011\u0001C@\u0011\u001d!i(\u0001C\u0001\t\u001fCq\u0001\" \u0002\t\u0003!i\nC\u0004\u0005*\u0006!\t\u0001b+\t\u0013\u0011u\u0016!%A\u0005\u0002\u0005=\u0002\"\u0003C`\u0003E\u0005I\u0011AA#\u0011\u001d!\t-\u0001C\u0001\t\u0007Dq\u0001\"1\u0002\t\u0003!)\u000eC\u0004\u0005B\u0006!\t\u0001\":\t\u000f\u0011M\u0018\u0001\"\u0001\u0005v\"IQqA\u0001\u0012\u0002\u0013\u0005\u0011q\u0006\u0005\n\u000b\u0013\t\u0011\u0013!C\u0001\u0003\u000bBq!b\u0003\u0002\t\u0003)i\u0001C\u0004\u0006\f\u0005!\t!\"\b\t\u000f\u0015-\u0011\u0001\"\u0001\u0006,!9QqG\u0001\u0005\n\u0015e\u0012A\u0003*b]\u0012|WN\u0015#Eg*\u0011!lW\u0001\u0007e\u0006tGm\\7\u000b\u0005qk\u0016!B7mY&\u0014'B\u00010`\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0017-\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002E\u0006\u0019qN]4\u0004\u0001A\u0011Q-A\u0007\u00023\nQ!+\u00198e_6\u0014F\tR:\u0014\u0005\u0005A\u0007CA5m\u001b\u0005Q'\"A6\u0002\u000bM\u001c\u0017\r\\1\n\u00055T'AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002I\u0006QQO\\5g_Jl'\u000b\u0012#\u0015\u0011I\\\u00181AA\u0007\u0003/\u00012a\u001d<y\u001b\u0005!(BA;^\u0003\r\u0011H\rZ\u0005\u0003oR\u00141A\u0015#E!\tI\u00170\u0003\u0002{U\n1Ai\\;cY\u0016DQ\u0001`\u0002A\u0002u\f!a]2\u0011\u0005y|X\"A/\n\u0007\u0005\u0005QL\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fC\u0004\u0002\u0006\r\u0001\r!a\u0002\u0002\tML'0\u001a\t\u0004S\u0006%\u0011bAA\u0006U\n!Aj\u001c8h\u0011%\tya\u0001I\u0001\u0002\u0004\t\t\"A\u0007ok6\u0004\u0016M\u001d;ji&|gn\u001d\t\u0004S\u0006M\u0011bAA\u000bU\n\u0019\u0011J\u001c;\t\u0013\u0005e1\u0001%AA\u0002\u0005\u001d\u0011\u0001B:fK\u0012DSaAA\u000f\u0003S\u0001B!a\b\u0002&5\u0011\u0011\u0011\u0005\u0006\u0004\u0003Gi\u0016AC1o]>$\u0018\r^5p]&!\u0011qEA\u0011\u0005\u0015\u0019\u0016N\\2fC\t\tY#A\u00032]Er\u0003'\u0001\u000bv]&4wN]7S\t\u0012#C-\u001a4bk2$HeM\u000b\u0003\u0003cQC!!\u0005\u00024-\u0012\u0011Q\u0007\t\u0005\u0003o\ty$\u0004\u0002\u0002:)!\u00111HA\u001f\u0003%)hn\u00195fG.,GMC\u0002\u0002$)LA!!\u0011\u0002:\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002)Ut\u0017NZ8s[J#E\t\n3fM\u0006,H\u000e\u001e\u00135+\t\t9E\u000b\u0003\u0002\b\u0005M\u0012AD;oS\u001a|'/\u001c&bm\u0006\u0014F\t\u0012\u000b\u000b\u0003\u001b\ni&a\u001a\u0002j\u0005-\u0004\u0003BA(\u00033j!!!\u0015\u000b\t\u0005M\u0013QK\u0001\u0005U\u00064\u0018MC\u0002\u0002Xu\u000b1!\u00199j\u0013\u0011\tY&!\u0015\u0003\u001b)\u000bg/\u0019#pk\ndWM\u0015#E\u0011\u001d\tyF\u0002a\u0001\u0003C\n1A[:d!\u0011\ty%a\u0019\n\t\u0005\u0015\u0014\u0011\u000b\u0002\u0011\u0015\u00064\u0018m\u00159be.\u001cuN\u001c;fqRDq!!\u0002\u0007\u0001\u0004\t9\u0001C\u0004\u0002\u0010\u0019\u0001\r!!\u0005\t\u000f\u0005ea\u00011\u0001\u0002\b!*a!!\b\u0002*QA\u0011QJA9\u0003g\n)\bC\u0004\u0002`\u001d\u0001\r!!\u0019\t\u000f\u0005\u0015q\u00011\u0001\u0002\b!9\u0011qB\u0004A\u0002\u0005E\u0001&B\u0004\u0002\u001e\u0005%BCBA'\u0003w\ni\bC\u0004\u0002`!\u0001\r!!\u0019\t\u000f\u0005\u0015\u0001\u00021\u0001\u0002\b!*\u0001\"!\b\u0002*\u0005Ian\u001c:nC2\u0014F\t\u0012\u000b\ne\u0006\u0015\u0015qQAE\u0003\u0017CQ\u0001`\u0005A\u0002uDq!!\u0002\n\u0001\u0004\t9\u0001C\u0005\u0002\u0010%\u0001\n\u00111\u0001\u0002\u0012!I\u0011\u0011D\u0005\u0011\u0002\u0003\u0007\u0011q\u0001\u0015\u0006\u0013\u0005u\u0011\u0011F\u0001\u0014]>\u0014X.\u00197S\t\u0012#C-\u001a4bk2$HeM\u0001\u0014]>\u0014X.\u00197S\t\u0012#C-\u001a4bk2$H\u0005N\u0001\u000e]>\u0014X.\u00197KCZ\f'\u000b\u0012#\u0015\u0015\u00055\u0013qSAM\u00037\u000bi\nC\u0004\u0002`1\u0001\r!!\u0019\t\u000f\u0005\u0015A\u00021\u0001\u0002\b!9\u0011q\u0002\u0007A\u0002\u0005E\u0001bBA\r\u0019\u0001\u0007\u0011q\u0001\u0015\u0006\u0019\u0005u\u0011\u0011\u0006\u000b\t\u0003\u001b\n\u0019+!*\u0002(\"9\u0011qL\u0007A\u0002\u0005\u0005\u0004bBA\u0003\u001b\u0001\u0007\u0011q\u0001\u0005\b\u0003\u001fi\u0001\u0019AA\tQ\u0015i\u0011QDA\u0015)\u0019\ti%!,\u00020\"9\u0011q\f\bA\u0002\u0005\u0005\u0004bBA\u0003\u001d\u0001\u0007\u0011q\u0001\u0015\u0006\u001d\u0005u\u0011\u0011F\u0001\u000ba>L7o]8o%\u0012#Ec\u0003:\u00028\u0006e\u0016QXA`\u0003\u0003DQ\u0001`\bA\u0002uDa!a/\u0010\u0001\u0004A\u0018\u0001B7fC:Dq!!\u0002\u0010\u0001\u0004\t9\u0001C\u0005\u0002\u0010=\u0001\n\u00111\u0001\u0002\u0012!I\u0011\u0011D\b\u0011\u0002\u0003\u0007\u0011q\u0001\u0015\u0006\u001f\u0005u\u0011\u0011F\u0001\u0015a>L7o]8o%\u0012#E\u0005Z3gCVdG\u000f\n\u001b\u0002)A|\u0017n]:p]J#E\t\n3fM\u0006,H\u000e\u001e\u00136\u00039\u0001x.[:t_:T\u0015M^1S\t\u0012#B\"!\u0014\u0002N\u0006=\u0017\u0011[Aj\u0003+Dq!a\u0018\u0013\u0001\u0004\t\t\u0007\u0003\u0004\u0002<J\u0001\r\u0001\u001f\u0005\b\u0003\u000b\u0011\u0002\u0019AA\u0004\u0011\u001d\tyA\u0005a\u0001\u0003#Aq!!\u0007\u0013\u0001\u0004\t9\u0001K\u0003\u0013\u0003;\tI\u0003\u0006\u0006\u0002N\u0005m\u0017Q\\Ap\u0003CDq!a\u0018\u0014\u0001\u0004\t\t\u0007\u0003\u0004\u0002<N\u0001\r\u0001\u001f\u0005\b\u0003\u000b\u0019\u0002\u0019AA\u0004\u0011\u001d\tya\u0005a\u0001\u0003#ASaEA\u000f\u0003S!\u0002\"!\u0014\u0002h\u0006%\u00181\u001e\u0005\b\u0003?\"\u0002\u0019AA1\u0011\u0019\tY\f\u0006a\u0001q\"9\u0011Q\u0001\u000bA\u0002\u0005\u001d\u0001&\u0002\u000b\u0002\u001e\u0005%\u0012AD3ya>tWM\u001c;jC2\u0014F\t\u0012\u000b\fe\u0006M\u0018Q_A|\u0003s\fY\u0010C\u0003}+\u0001\u0007Q\u0010\u0003\u0004\u0002<V\u0001\r\u0001\u001f\u0005\b\u0003\u000b)\u0002\u0019AA\u0004\u0011%\ty!\u0006I\u0001\u0002\u0004\t\t\u0002C\u0005\u0002\u001aU\u0001\n\u00111\u0001\u0002\b!*Q#!\b\u0002\u0000\u0006\u0012!\u0011A\u0001\u0006c9\u001ad\u0006M\u0001\u0019Kb\u0004xN\\3oi&\fGN\u0015#EI\u0011,g-Y;mi\u0012\"\u0014\u0001G3ya>tWM\u001c;jC2\u0014F\t\u0012\u0013eK\u001a\fW\u000f\u001c;%k\u0005\u0011R\r\u001f9p]\u0016tG/[1m\u0015\u00064\u0018M\u0015#E)1\tiEa\u0003\u0003\u000e\t=!\u0011\u0003B\n\u0011\u001d\ty\u0006\u0007a\u0001\u0003CBa!a/\u0019\u0001\u0004A\bbBA\u00031\u0001\u0007\u0011q\u0001\u0005\b\u0003\u001fA\u0002\u0019AA\t\u0011\u001d\tI\u0002\u0007a\u0001\u0003\u000fAS\u0001GA\u000f\u0003\u007f$\"\"!\u0014\u0003\u001a\tm!Q\u0004B\u0010\u0011\u001d\ty&\u0007a\u0001\u0003CBa!a/\u001a\u0001\u0004A\bbBA\u00033\u0001\u0007\u0011q\u0001\u0005\b\u0003\u001fI\u0002\u0019AA\tQ\u0015I\u0012QDA\u0000)!\tiE!\n\u0003(\t%\u0002bBA05\u0001\u0007\u0011\u0011\r\u0005\u0007\u0003wS\u0002\u0019\u0001=\t\u000f\u0005\u0015!\u00041\u0001\u0002\b!*!$!\b\u0002\u0000\u0006Aq-Y7nCJ#E\tF\u0007s\u0005c\u0011\u0019Da\u000e\u0003<\tu\"q\b\u0005\u0006yn\u0001\r! \u0005\u0007\u0005kY\u0002\u0019\u0001=\u0002\u000bMD\u0017\r]3\t\r\te2\u00041\u0001y\u0003\u0015\u00198-\u00197f\u0011\u001d\t)a\u0007a\u0001\u0003\u000fA\u0011\"a\u0004\u001c!\u0003\u0005\r!!\u0005\t\u0013\u0005e1\u0004%AA\u0002\u0005\u001d\u0001&B\u000e\u0002\u001e\u0005}\u0018AE4b[6\f'\u000b\u0012#%I\u00164\u0017-\u001e7uIU\n!cZ1n[\u0006\u0014F\t\u0012\u0013eK\u001a\fW\u000f\u001c;%m\u0005aq-Y7nC*\u000bg/\u0019*E\tRq\u0011Q\nB&\u0005\u001b\u0012yE!\u0015\u0003T\tU\u0003bBA0=\u0001\u0007\u0011\u0011\r\u0005\u0007\u0005kq\u0002\u0019\u0001=\t\r\teb\u00041\u0001y\u0011\u001d\t)A\ba\u0001\u0003\u000fAq!a\u0004\u001f\u0001\u0004\t\t\u0002C\u0004\u0002\u001ay\u0001\r!a\u0002)\u000by\ti\"a@\u0015\u0019\u00055#1\fB/\u0005?\u0012\tGa\u0019\t\u000f\u0005}s\u00041\u0001\u0002b!1!QG\u0010A\u0002aDaA!\u000f \u0001\u0004A\bbBA\u0003?\u0001\u0007\u0011q\u0001\u0005\b\u0003\u001fy\u0002\u0019AA\tQ\u0015y\u0012QDA\u0000))\tiE!\u001b\u0003l\t5$q\u000e\u0005\b\u0003?\u0002\u0003\u0019AA1\u0011\u0019\u0011)\u0004\ta\u0001q\"1!\u0011\b\u0011A\u0002aDq!!\u0002!\u0001\u0004\t9\u0001K\u0003!\u0003;\ty0\u0001\u0007m_\u001etuN]7bYJ#E\tF\u0007s\u0005o\u0012IHa\u001f\u0003\u0000\t\u0005%1\u0011\u0005\u0006y\u0006\u0002\r! \u0005\u0007\u0003w\u000b\u0003\u0019\u0001=\t\r\tu\u0014\u00051\u0001y\u0003\r\u0019H\u000f\u001a\u0005\b\u0003\u000b\t\u0003\u0019AA\u0004\u0011%\ty!\tI\u0001\u0002\u0004\t\t\u0002C\u0005\u0002\u001a\u0005\u0002\n\u00111\u0001\u0002\b!*\u0011%!\b\u0002\u0000\u00061Bn\\4O_Jl\u0017\r\u001c*E\t\u0012\"WMZ1vYR$S'\u0001\fm_\u001etuN]7bYJ#E\t\n3fM\u0006,H\u000e\u001e\u00137\u0003Aawn\u001a(pe6\fGNS1wCJ#E\t\u0006\b\u0002N\t=%\u0011\u0013BJ\u0005+\u00139J!'\t\u000f\u0005}C\u00051\u0001\u0002b!1\u00111\u0018\u0013A\u0002aDaA! %\u0001\u0004A\bbBA\u0003I\u0001\u0007\u0011q\u0001\u0005\b\u0003\u001f!\u0003\u0019AA\t\u0011\u001d\tI\u0002\na\u0001\u0003\u000fAS\u0001JA\u000f\u0003\u007f$B\"!\u0014\u0003 \n\u0005&1\u0015BS\u0005OCq!a\u0018&\u0001\u0004\t\t\u0007\u0003\u0004\u0002<\u0016\u0002\r\u0001\u001f\u0005\u0007\u0005{*\u0003\u0019\u0001=\t\u000f\u0005\u0015Q\u00051\u0001\u0002\b!9\u0011qB\u0013A\u0002\u0005E\u0001&B\u0013\u0002\u001e\u0005}HCCA'\u0005[\u0013yK!-\u00034\"9\u0011q\f\u0014A\u0002\u0005\u0005\u0004BBA^M\u0001\u0007\u0001\u0010\u0003\u0004\u0003~\u0019\u0002\r\u0001\u001f\u0005\b\u0003\u000b1\u0003\u0019AA\u0004Q\u00151\u0013QDA\u0000\u0003%\u0011\u0018M\u001c3p[J#E)\u0006\u0003\u0003<\n\u0015G\u0003\u0004B_\u0005O\u0014IOa=\u0003v\n]H\u0003\u0002B`\u0005/\u0004Ba\u001d<\u0003BB!!1\u0019Bc\u0019\u0001!qAa2(\u0005\u0004\u0011IMA\u0001U#\u0011\u0011YM!5\u0011\u0007%\u0014i-C\u0002\u0003P*\u0014qAT8uQ&tw\rE\u0002j\u0005'L1A!6k\u0005\r\te.\u001f\u0005\n\u00053<\u0013\u0011!a\u0002\u00057\f!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\u0011iNa9\u0003B6\u0011!q\u001c\u0006\u0004\u0005CT\u0017a\u0002:fM2,7\r^\u0005\u0005\u0005K\u0014yN\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u0015ax\u00051\u0001~\u0011\u001d\u0011Yo\na\u0001\u0005[\f\u0011bZ3oKJ\fGo\u001c:\u0011\u000b\u0015\u0014yO!1\n\u0007\tE\u0018LA\nSC:$w.\u001c#bi\u0006<UM\\3sCR|'\u000fC\u0004\u0002\u0006\u001d\u0002\r!a\u0002\t\u0013\u0005=q\u0005%AA\u0002\u0005E\u0001\"CA\rOA\u0005\t\u0019AA\u0004Q\u00159\u0013QDA\u0015\u0003M\u0011\u0018M\u001c3p[J#E\t\n3fM\u0006,H\u000e\u001e\u00135+\u0011\tyCa@\u0005\u000f\t\u001d\u0007F1\u0001\u0003J\u0006\u0019\"/\u00198e_6\u0014F\t\u0012\u0013eK\u001a\fW\u000f\u001c;%kU!\u0011QIB\u0003\t\u001d\u00119-\u000bb\u0001\u0005\u0013\fQB]1oI>l'*\u0019<b%\u0012#U\u0003BB\u0006\u0007+!Bb!\u0004\u0004\u0018\re1QDB\u0010\u0007C\u0001b!a\u0014\u0004\u0010\rM\u0011\u0002BB\t\u0003#\u0012qAS1wCJ#E\t\u0005\u0003\u0003D\u000eUAa\u0002BdU\t\u0007!\u0011\u001a\u0005\b\u0003?R\u0003\u0019AA1\u0011\u001d\u0011YO\u000ba\u0001\u00077\u0001R!\u001aBx\u0007'Aq!!\u0002+\u0001\u0004\t9\u0001C\u0004\u0002\u0010)\u0002\r!!\u0005\t\u000f\u0005e!\u00061\u0001\u0002\b!*!&!\b\u0004&\u0005\u00121qE\u0001\u0006c92d\u0006M\u000b\u0005\u0007W\u0019\t\u0004\u0006\u0006\u0004.\rM2QGB\u001d\u0007w\u0001b!a\u0014\u0004\u0010\r=\u0002\u0003\u0002Bb\u0007c!qAa2,\u0005\u0004\u0011I\rC\u0004\u0002`-\u0002\r!!\u0019\t\u000f\t-8\u00061\u0001\u00048A)QMa<\u00040!9\u0011QA\u0016A\u0002\u0005\u001d\u0001bBA\bW\u0001\u0007\u0011\u0011\u0003\u0015\u0006W\u0005u1QE\u000b\u0005\u0007\u0003\u001a9\u0005\u0006\u0005\u0004D\r%31JB(!\u0019\tyea\u0004\u0004FA!!1YB$\t\u001d\u00119\r\fb\u0001\u0005\u0013Dq!a\u0018-\u0001\u0004\t\t\u0007C\u0004\u0003l2\u0002\ra!\u0014\u0011\u000b\u0015\u0014yo!\u0012\t\u000f\u0005\u0015A\u00061\u0001\u0002\b!*A&!\b\u0004&\u0005\u0001RO\\5g_Jlg+Z2u_J\u0014F\t\u0012\u000b\r\u0007/\u001a)ga\u001a\u0004l\r=4\u0011\u000f\t\u0005gZ\u001cI\u0006\u0005\u0003\u0004\\\r\u0005TBAB/\u0015\r\u0019yfW\u0001\u0007Y&t\u0017\r\\4\n\t\r\r4Q\f\u0002\u0007-\u0016\u001cGo\u001c:\t\u000bql\u0003\u0019A?\t\u000f\r%T\u00061\u0001\u0002\b\u00059a.^7S_^\u001c\bbBB7[\u0001\u0007\u0011\u0011C\u0001\b]Vl7i\u001c7t\u0011%\ty!\fI\u0001\u0002\u0004\t\t\u0002C\u0005\u0002\u001a5\u0002\n\u00111\u0001\u0002\b!*Q&!\b\u0002*\u0005QRO\\5g_Jlg+Z2u_J\u0014F\t\u0012\u0013eK\u001a\fW\u000f\u001c;%i\u0005QRO\\5g_Jlg+Z2u_J\u0014F\t\u0012\u0013eK\u001a\fW\u000f\u001c;%k\u0005!RO\\5g_Jl'*\u0019<b-\u0016\u001cGo\u001c:S\t\u0012#Bb! \u0004\u0000\r\u000551QBC\u0007\u000f\u0003b!a\u0014\u0004\u0010\re\u0003bBA0a\u0001\u0007\u0011\u0011\r\u0005\b\u0007S\u0002\u0004\u0019AA\u0004\u0011\u001d\u0019i\u0007\ra\u0001\u0003#Aq!a\u00041\u0001\u0004\t\t\u0002C\u0004\u0002\u001aA\u0002\r!a\u0002)\u000bA\ni\"!\u000b\u0015\u0015\ru4QRBH\u0007#\u001b\u0019\nC\u0004\u0002`E\u0002\r!!\u0019\t\u000f\r%\u0014\u00071\u0001\u0002\b!91QN\u0019A\u0002\u0005E\u0001bBA\bc\u0001\u0007\u0011\u0011\u0003\u0015\u0006c\u0005u\u0011\u0011\u0006\u000b\t\u0007{\u001aIja'\u0004\u001e\"9\u0011q\f\u001aA\u0002\u0005\u0005\u0004bBB5e\u0001\u0007\u0011q\u0001\u0005\b\u0007[\u0012\u0004\u0019AA\tQ\u0015\u0011\u0014QDA\u0015\u0003=qwN]7bYZ+7\r^8s%\u0012#E\u0003DB,\u0007K\u001b9k!+\u0004,\u000e5\u0006\"\u0002?4\u0001\u0004i\bbBB5g\u0001\u0007\u0011q\u0001\u0005\b\u0007[\u001a\u0004\u0019AA\t\u0011%\tya\rI\u0001\u0002\u0004\t\t\u0002C\u0005\u0002\u001aM\u0002\n\u00111\u0001\u0002\b!*1'!\b\u0002*\u0005Ibn\u001c:nC24Vm\u0019;peJ#E\t\n3fM\u0006,H\u000e\u001e\u00135\u0003eqwN]7bYZ+7\r^8s%\u0012#E\u0005Z3gCVdG\u000fJ\u001b\u0002'9|'/\\1m\u0015\u00064\u0018MV3di>\u0014(\u000b\u0012#\u0015\u0019\ru4\u0011XB^\u0007{\u001byl!1\t\u000f\u0005}c\u00071\u0001\u0002b!91\u0011\u000e\u001cA\u0002\u0005\u001d\u0001bBB7m\u0001\u0007\u0011\u0011\u0003\u0005\b\u0003\u001f1\u0004\u0019AA\t\u0011\u001d\tIB\u000ea\u0001\u0003\u000fASANA\u000f\u0003S!\"b! \u0004H\u000e%71ZBg\u0011\u001d\tyf\u000ea\u0001\u0003CBqa!\u001b8\u0001\u0004\t9\u0001C\u0004\u0004n]\u0002\r!!\u0005\t\u000f\u0005=q\u00071\u0001\u0002\u0012!*q'!\b\u0002*QA1QPBj\u0007+\u001c9\u000eC\u0004\u0002`a\u0002\r!!\u0019\t\u000f\r%\u0004\b1\u0001\u0002\b!91Q\u000e\u001dA\u0002\u0005E\u0001&\u0002\u001d\u0002\u001e\u0005%\u0012A\u00057pO:{'/\\1m-\u0016\u001cGo\u001c:S\t\u0012#\u0002ca\u0016\u0004`\u000e\u000581]Bs\u0007O\u001cIoa;\t\u000bqL\u0004\u0019A?\t\r\u0005m\u0016\b1\u0001y\u0011\u0019\u0011i(\u000fa\u0001q\"91\u0011N\u001dA\u0002\u0005\u001d\u0001bBB7s\u0001\u0007\u0011\u0011\u0003\u0005\n\u0003\u001fI\u0004\u0013!a\u0001\u0003#A\u0011\"!\u0007:!\u0003\u0005\r!a\u0002)\u000be\ni\"a@\u000291|wMT8s[\u0006dg+Z2u_J\u0014F\t\u0012\u0013eK\u001a\fW\u000f\u001c;%m\u0005aBn\\4O_Jl\u0017\r\u001c,fGR|'O\u0015#EI\u0011,g-Y;mi\u0012:\u0014A\u00067pO:{'/\\1m\u0015\u00064\u0018MV3di>\u0014(\u000b\u0012#\u0015!\ru4q_B}\u0007w\u001cipa@\u0005\u0002\u0011\r\u0001bBA0y\u0001\u0007\u0011\u0011\r\u0005\u0007\u0003wc\u0004\u0019\u0001=\t\r\tuD\b1\u0001y\u0011\u001d\u0019I\u0007\u0010a\u0001\u0003\u000fAqa!\u001c=\u0001\u0004\t\t\u0002C\u0004\u0002\u0010q\u0002\r!!\u0005\t\u000f\u0005eA\b1\u0001\u0002\b!*A(!\b\u0002\u0000Rq1Q\u0010C\u0005\t\u0017!i\u0001b\u0004\u0005\u0012\u0011M\u0001bBA0{\u0001\u0007\u0011\u0011\r\u0005\u0007\u0003wk\u0004\u0019\u0001=\t\r\tuT\b1\u0001y\u0011\u001d\u0019I'\u0010a\u0001\u0003\u000fAqa!\u001c>\u0001\u0004\t\t\u0002C\u0004\u0002\u0010u\u0002\r!!\u0005)\u000bu\ni\"a@\u0015\u0019\ruD\u0011\u0004C\u000e\t;!y\u0002\"\t\t\u000f\u0005}c\b1\u0001\u0002b!1\u00111\u0018 A\u0002aDaA! ?\u0001\u0004A\bbBB5}\u0001\u0007\u0011q\u0001\u0005\b\u0007[r\u0004\u0019AA\tQ\u0015q\u0014QDA\u0000\u0003A\u0001x.[:t_:4Vm\u0019;peJ#E\t\u0006\b\u0004X\u0011%B1\u0006C\u0017\t_!\t\u0004b\r\t\u000bq|\u0004\u0019A?\t\r\u0005mv\b1\u0001y\u0011\u001d\u0019Ig\u0010a\u0001\u0003\u000fAqa!\u001c@\u0001\u0004\t\t\u0002C\u0005\u0002\u0010}\u0002\n\u00111\u0001\u0002\u0012!I\u0011\u0011D \u0011\u0002\u0003\u0007\u0011q\u0001\u0015\u0006\u007f\u0005u\u0011\u0011F\u0001\u001ba>L7o]8o-\u0016\u001cGo\u001c:S\t\u0012#C-\u001a4bk2$H%N\u0001\u001ba>L7o]8o-\u0016\u001cGo\u001c:S\t\u0012#C-\u001a4bk2$HEN\u0001\u0015a>L7o]8o\u0015\u00064\u0018MV3di>\u0014(\u000b\u0012#\u0015\u001d\ruDq\bC!\t\u0007\")\u0005b\u0012\u0005J!9\u0011q\f\"A\u0002\u0005\u0005\u0004BBA^\u0005\u0002\u0007\u0001\u0010C\u0004\u0004j\t\u0003\r!a\u0002\t\u000f\r5$\t1\u0001\u0002\u0012!9\u0011q\u0002\"A\u0002\u0005E\u0001bBA\r\u0005\u0002\u0007\u0011q\u0001\u0015\u0006\u0005\u0006u\u0011\u0011\u0006\u000b\r\u0007{\"y\u0005\"\u0015\u0005T\u0011UCq\u000b\u0005\b\u0003?\u001a\u0005\u0019AA1\u0011\u0019\tYl\u0011a\u0001q\"91\u0011N\"A\u0002\u0005\u001d\u0001bBB7\u0007\u0002\u0007\u0011\u0011\u0003\u0005\b\u0003\u001f\u0019\u0005\u0019AA\tQ\u0015\u0019\u0015QDA\u0015))\u0019i\b\"\u0018\u0005`\u0011\u0005D1\r\u0005\b\u0003?\"\u0005\u0019AA1\u0011\u0019\tY\f\u0012a\u0001q\"91\u0011\u000e#A\u0002\u0005\u001d\u0001bBB7\t\u0002\u0007\u0011\u0011\u0003\u0015\u0006\t\u0006u\u0011\u0011F\u0001\u0015Kb\u0004xN\\3oi&\fGNV3di>\u0014(\u000b\u0012#\u0015\u001d\r]C1\u000eC7\t_\"\t\bb\u001d\u0005v!)A0\u0012a\u0001{\"1\u00111X#A\u0002aDqa!\u001bF\u0001\u0004\t9\u0001C\u0004\u0004n\u0015\u0003\r!!\u0005\t\u0013\u0005=Q\t%AA\u0002\u0005E\u0001\"CA\r\u000bB\u0005\t\u0019AA\u0004Q\u0015)\u0015QDA\u0000\u0003y)\u0007\u0010]8oK:$\u0018.\u00197WK\u000e$xN\u001d*E\t\u0012\"WMZ1vYR$S'\u0001\u0010fqB|g.\u001a8uS\u0006dg+Z2u_J\u0014F\t\u0012\u0013eK\u001a\fW\u000f\u001c;%m\u0005AR\r\u001f9p]\u0016tG/[1m\u0015\u00064\u0018MV3di>\u0014(\u000b\u0012#\u0015\u001d\ruD\u0011\u0011CB\t\u000b#9\t\"#\u0005\f\"9\u0011q\f%A\u0002\u0005\u0005\u0004BBA^\u0011\u0002\u0007\u0001\u0010C\u0004\u0004j!\u0003\r!a\u0002\t\u000f\r5\u0004\n1\u0001\u0002\u0012!9\u0011q\u0002%A\u0002\u0005E\u0001bBA\r\u0011\u0002\u0007\u0011q\u0001\u0015\u0006\u0011\u0006u\u0011q \u000b\r\u0007{\"\t\nb%\u0005\u0016\u0012]E\u0011\u0014\u0005\b\u0003?J\u0005\u0019AA1\u0011\u0019\tY,\u0013a\u0001q\"91\u0011N%A\u0002\u0005\u001d\u0001bBB7\u0013\u0002\u0007\u0011\u0011\u0003\u0005\b\u0003\u001fI\u0005\u0019AA\tQ\u0015I\u0015QDA\u0000))\u0019i\bb(\u0005\"\u0012\rFQ\u0015\u0005\b\u0003?R\u0005\u0019AA1\u0011\u0019\tYL\u0013a\u0001q\"91\u0011\u000e&A\u0002\u0005\u001d\u0001bBB7\u0015\u0002\u0007\u0011\u0011\u0003\u0015\u0006\u0015\u0006u\u0011q`\u0001\u000fO\u0006lW.\u0019,fGR|'O\u0015#E)A\u00199\u0006\",\u00050\u0012EF1\u0017C[\to#I\fC\u0003}\u0017\u0002\u0007Q\u0010\u0003\u0004\u00036-\u0003\r\u0001\u001f\u0005\u0007\u0005sY\u0005\u0019\u0001=\t\u000f\r%4\n1\u0001\u0002\b!91QN&A\u0002\u0005E\u0001\"CA\b\u0017B\u0005\t\u0019AA\t\u0011%\tIb\u0013I\u0001\u0002\u0004\t9\u0001K\u0003L\u0003;\ty0\u0001\rhC6l\u0017MV3di>\u0014(\u000b\u0012#%I\u00164\u0017-\u001e7uIY\n\u0001dZ1n[\u00064Vm\u0019;peJ#E\t\n3fM\u0006,H\u000e\u001e\u00138\u0003I9\u0017-\\7b\u0015\u00064\u0018MV3di>\u0014(\u000b\u0012#\u0015!\ruDQ\u0019Cd\t\u0013$Y\r\"4\u0005P\u0012E\u0007bBA0\u001d\u0002\u0007\u0011\u0011\r\u0005\u0007\u0005kq\u0005\u0019\u0001=\t\r\teb\n1\u0001y\u0011\u001d\u0019IG\u0014a\u0001\u0003\u000fAqa!\u001cO\u0001\u0004\t\t\u0002C\u0004\u0002\u00109\u0003\r!!\u0005\t\u000f\u0005ea\n1\u0001\u0002\b!*a*!\b\u0002\u0000Rq1Q\u0010Cl\t3$Y\u000e\"8\u0005`\u0012\u0005\bbBA0\u001f\u0002\u0007\u0011\u0011\r\u0005\u0007\u0005ky\u0005\u0019\u0001=\t\r\ter\n1\u0001y\u0011\u001d\u0019Ig\u0014a\u0001\u0003\u000fAqa!\u001cP\u0001\u0004\t\t\u0002C\u0004\u0002\u0010=\u0003\r!!\u0005)\u000b=\u000bi\"a@\u0015\u0019\ruDq\u001dCu\tW$i\u000fb<\t\u000f\u0005}\u0003\u000b1\u0001\u0002b!1!Q\u0007)A\u0002aDaA!\u000fQ\u0001\u0004A\bbBB5!\u0002\u0007\u0011q\u0001\u0005\b\u0007[\u0002\u0006\u0019AA\tQ\u0015\u0001\u0016QDA\u0000\u0003=\u0011\u0018M\u001c3p[Z+7\r^8s%\u0012#ECDB,\to$I\u0010\"@\u0005\u0000\u0016\u0005Q1\u0001\u0005\u0006yF\u0003\r! \u0005\b\u0005W\f\u0006\u0019\u0001C~!\u0011)'q\u001e=\t\u000f\r%\u0014\u000b1\u0001\u0002\b!91QN)A\u0002\u0005E\u0001\"CA\b#B\u0005\t\u0019AA\t\u0011%\tI\"\u0015I\u0001\u0002\u0004\t9\u0001K\u0003R\u0003;\tI#A\rsC:$w.\u001c,fGR|'O\u0015#EI\u0011,g-Y;mi\u0012*\u0014!\u0007:b]\u0012|WNV3di>\u0014(\u000b\u0012#%I\u00164\u0017-\u001e7uIY\n1C]1oI>l'*\u0019<b-\u0016\u001cGo\u001c:S\t\u0012#bb! \u0006\u0010\u0015EQ1CC\u000b\u000b/)I\u0002C\u0004\u0002`Q\u0003\r!!\u0019\t\u000f\t-H\u000b1\u0001\u0005|\"91\u0011\u000e+A\u0002\u0005\u001d\u0001bBB7)\u0002\u0007\u0011\u0011\u0003\u0005\b\u0003\u001f!\u0006\u0019AA\t\u0011\u001d\tI\u0002\u0016a\u0001\u0003\u000fAS\u0001VA\u000f\u0007K!Bb! \u0006 \u0015\u0005R1EC\u0013\u000bOAq!a\u0018V\u0001\u0004\t\t\u0007C\u0004\u0003lV\u0003\r\u0001b?\t\u000f\r%T\u000b1\u0001\u0002\b!91QN+A\u0002\u0005E\u0001bBA\b+\u0002\u0007\u0011\u0011\u0003\u0015\u0006+\u0006u1Q\u0005\u000b\u000b\u0007{*i#b\f\u00062\u0015M\u0002bBA0-\u0002\u0007\u0011\u0011\r\u0005\b\u0005W4\u0006\u0019\u0001C~\u0011\u001d\u0019IG\u0016a\u0001\u0003\u000fAqa!\u001cW\u0001\u0004\t\t\u0002K\u0003W\u0003;\u0019)#\u0001\fok6\u0004\u0016M\u001d;ji&|gn](s\t\u00164\u0017-\u001e7u)\u0019\t\t\"b\u000f\u0006>!)Ap\u0016a\u0001{\"9\u0011qB,A\u0002\u0005E\u0001&B\u0001\u0002\u001e\u0005%\u0002&\u0002\u0001\u0002\u001e\u0005%\u0002"
)
public final class RandomRDDs {
   public static JavaRDD randomJavaVectorRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long numRows, final int numCols) {
      return RandomRDDs$.MODULE$.randomJavaVectorRDD(jsc, generator, numRows, numCols);
   }

   public static JavaRDD randomJavaVectorRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long numRows, final int numCols, final int numPartitions) {
      return RandomRDDs$.MODULE$.randomJavaVectorRDD(jsc, generator, numRows, numCols, numPartitions);
   }

   public static JavaRDD randomJavaVectorRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.randomJavaVectorRDD(jsc, generator, numRows, numCols, numPartitions, seed);
   }

   public static long randomVectorRDD$default$6() {
      return RandomRDDs$.MODULE$.randomVectorRDD$default$6();
   }

   public static int randomVectorRDD$default$5() {
      return RandomRDDs$.MODULE$.randomVectorRDD$default$5();
   }

   public static RDD randomVectorRDD(final SparkContext sc, final RandomDataGenerator generator, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.randomVectorRDD(sc, generator, numRows, numCols, numPartitions, seed);
   }

   public static JavaRDD gammaJavaVectorRDD(final JavaSparkContext jsc, final double shape, final double scale, final long numRows, final int numCols) {
      return RandomRDDs$.MODULE$.gammaJavaVectorRDD(jsc, shape, scale, numRows, numCols);
   }

   public static JavaRDD gammaJavaVectorRDD(final JavaSparkContext jsc, final double shape, final double scale, final long numRows, final int numCols, final int numPartitions) {
      return RandomRDDs$.MODULE$.gammaJavaVectorRDD(jsc, shape, scale, numRows, numCols, numPartitions);
   }

   public static JavaRDD gammaJavaVectorRDD(final JavaSparkContext jsc, final double shape, final double scale, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.gammaJavaVectorRDD(jsc, shape, scale, numRows, numCols, numPartitions, seed);
   }

   public static long gammaVectorRDD$default$7() {
      return RandomRDDs$.MODULE$.gammaVectorRDD$default$7();
   }

   public static int gammaVectorRDD$default$6() {
      return RandomRDDs$.MODULE$.gammaVectorRDD$default$6();
   }

   public static RDD gammaVectorRDD(final SparkContext sc, final double shape, final double scale, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.gammaVectorRDD(sc, shape, scale, numRows, numCols, numPartitions, seed);
   }

   public static JavaRDD exponentialJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols) {
      return RandomRDDs$.MODULE$.exponentialJavaVectorRDD(jsc, mean, numRows, numCols);
   }

   public static JavaRDD exponentialJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final int numPartitions) {
      return RandomRDDs$.MODULE$.exponentialJavaVectorRDD(jsc, mean, numRows, numCols, numPartitions);
   }

   public static JavaRDD exponentialJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.exponentialJavaVectorRDD(jsc, mean, numRows, numCols, numPartitions, seed);
   }

   public static long exponentialVectorRDD$default$6() {
      return RandomRDDs$.MODULE$.exponentialVectorRDD$default$6();
   }

   public static int exponentialVectorRDD$default$5() {
      return RandomRDDs$.MODULE$.exponentialVectorRDD$default$5();
   }

   public static RDD exponentialVectorRDD(final SparkContext sc, final double mean, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.exponentialVectorRDD(sc, mean, numRows, numCols, numPartitions, seed);
   }

   public static JavaRDD poissonJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols) {
      return RandomRDDs$.MODULE$.poissonJavaVectorRDD(jsc, mean, numRows, numCols);
   }

   public static JavaRDD poissonJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final int numPartitions) {
      return RandomRDDs$.MODULE$.poissonJavaVectorRDD(jsc, mean, numRows, numCols, numPartitions);
   }

   public static JavaRDD poissonJavaVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.poissonJavaVectorRDD(jsc, mean, numRows, numCols, numPartitions, seed);
   }

   public static long poissonVectorRDD$default$6() {
      return RandomRDDs$.MODULE$.poissonVectorRDD$default$6();
   }

   public static int poissonVectorRDD$default$5() {
      return RandomRDDs$.MODULE$.poissonVectorRDD$default$5();
   }

   public static RDD poissonVectorRDD(final SparkContext sc, final double mean, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.poissonVectorRDD(sc, mean, numRows, numCols, numPartitions, seed);
   }

   public static JavaRDD logNormalJavaVectorRDD(final JavaSparkContext jsc, final double mean, final double std, final long numRows, final int numCols) {
      return RandomRDDs$.MODULE$.logNormalJavaVectorRDD(jsc, mean, std, numRows, numCols);
   }

   public static JavaRDD logNormalJavaVectorRDD(final JavaSparkContext jsc, final double mean, final double std, final long numRows, final int numCols, final int numPartitions) {
      return RandomRDDs$.MODULE$.logNormalJavaVectorRDD(jsc, mean, std, numRows, numCols, numPartitions);
   }

   public static JavaRDD logNormalJavaVectorRDD(final JavaSparkContext jsc, final double mean, final double std, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.logNormalJavaVectorRDD(jsc, mean, std, numRows, numCols, numPartitions, seed);
   }

   public static long logNormalVectorRDD$default$7() {
      return RandomRDDs$.MODULE$.logNormalVectorRDD$default$7();
   }

   public static int logNormalVectorRDD$default$6() {
      return RandomRDDs$.MODULE$.logNormalVectorRDD$default$6();
   }

   public static RDD logNormalVectorRDD(final SparkContext sc, final double mean, final double std, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.logNormalVectorRDD(sc, mean, std, numRows, numCols, numPartitions, seed);
   }

   public static JavaRDD normalJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols) {
      return RandomRDDs$.MODULE$.normalJavaVectorRDD(jsc, numRows, numCols);
   }

   public static JavaRDD normalJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final int numPartitions) {
      return RandomRDDs$.MODULE$.normalJavaVectorRDD(jsc, numRows, numCols, numPartitions);
   }

   public static JavaRDD normalJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.normalJavaVectorRDD(jsc, numRows, numCols, numPartitions, seed);
   }

   public static long normalVectorRDD$default$5() {
      return RandomRDDs$.MODULE$.normalVectorRDD$default$5();
   }

   public static int normalVectorRDD$default$4() {
      return RandomRDDs$.MODULE$.normalVectorRDD$default$4();
   }

   public static RDD normalVectorRDD(final SparkContext sc, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.normalVectorRDD(sc, numRows, numCols, numPartitions, seed);
   }

   public static JavaRDD uniformJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols) {
      return RandomRDDs$.MODULE$.uniformJavaVectorRDD(jsc, numRows, numCols);
   }

   public static JavaRDD uniformJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final int numPartitions) {
      return RandomRDDs$.MODULE$.uniformJavaVectorRDD(jsc, numRows, numCols, numPartitions);
   }

   public static JavaRDD uniformJavaVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.uniformJavaVectorRDD(jsc, numRows, numCols, numPartitions, seed);
   }

   public static long uniformVectorRDD$default$5() {
      return RandomRDDs$.MODULE$.uniformVectorRDD$default$5();
   }

   public static int uniformVectorRDD$default$4() {
      return RandomRDDs$.MODULE$.uniformVectorRDD$default$4();
   }

   public static RDD uniformVectorRDD(final SparkContext sc, final long numRows, final int numCols, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.uniformVectorRDD(sc, numRows, numCols, numPartitions, seed);
   }

   public static JavaRDD randomJavaRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long size) {
      return RandomRDDs$.MODULE$.randomJavaRDD(jsc, generator, size);
   }

   public static JavaRDD randomJavaRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long size, final int numPartitions) {
      return RandomRDDs$.MODULE$.randomJavaRDD(jsc, generator, size, numPartitions);
   }

   public static JavaRDD randomJavaRDD(final JavaSparkContext jsc, final RandomDataGenerator generator, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.randomJavaRDD(jsc, generator, size, numPartitions, seed);
   }

   public static long randomRDD$default$5() {
      return RandomRDDs$.MODULE$.randomRDD$default$5();
   }

   public static int randomRDD$default$4() {
      return RandomRDDs$.MODULE$.randomRDD$default$4();
   }

   public static RDD randomRDD(final SparkContext sc, final RandomDataGenerator generator, final long size, final int numPartitions, final long seed, final ClassTag evidence$1) {
      return RandomRDDs$.MODULE$.randomRDD(sc, generator, size, numPartitions, seed, evidence$1);
   }

   public static JavaDoubleRDD logNormalJavaRDD(final JavaSparkContext jsc, final double mean, final double std, final long size) {
      return RandomRDDs$.MODULE$.logNormalJavaRDD(jsc, mean, std, size);
   }

   public static JavaDoubleRDD logNormalJavaRDD(final JavaSparkContext jsc, final double mean, final double std, final long size, final int numPartitions) {
      return RandomRDDs$.MODULE$.logNormalJavaRDD(jsc, mean, std, size, numPartitions);
   }

   public static JavaDoubleRDD logNormalJavaRDD(final JavaSparkContext jsc, final double mean, final double std, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.logNormalJavaRDD(jsc, mean, std, size, numPartitions, seed);
   }

   public static long logNormalRDD$default$6() {
      return RandomRDDs$.MODULE$.logNormalRDD$default$6();
   }

   public static int logNormalRDD$default$5() {
      return RandomRDDs$.MODULE$.logNormalRDD$default$5();
   }

   public static RDD logNormalRDD(final SparkContext sc, final double mean, final double std, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.logNormalRDD(sc, mean, std, size, numPartitions, seed);
   }

   public static JavaDoubleRDD gammaJavaRDD(final JavaSparkContext jsc, final double shape, final double scale, final long size) {
      return RandomRDDs$.MODULE$.gammaJavaRDD(jsc, shape, scale, size);
   }

   public static JavaDoubleRDD gammaJavaRDD(final JavaSparkContext jsc, final double shape, final double scale, final long size, final int numPartitions) {
      return RandomRDDs$.MODULE$.gammaJavaRDD(jsc, shape, scale, size, numPartitions);
   }

   public static JavaDoubleRDD gammaJavaRDD(final JavaSparkContext jsc, final double shape, final double scale, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.gammaJavaRDD(jsc, shape, scale, size, numPartitions, seed);
   }

   public static long gammaRDD$default$6() {
      return RandomRDDs$.MODULE$.gammaRDD$default$6();
   }

   public static int gammaRDD$default$5() {
      return RandomRDDs$.MODULE$.gammaRDD$default$5();
   }

   public static RDD gammaRDD(final SparkContext sc, final double shape, final double scale, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.gammaRDD(sc, shape, scale, size, numPartitions, seed);
   }

   public static JavaDoubleRDD exponentialJavaRDD(final JavaSparkContext jsc, final double mean, final long size) {
      return RandomRDDs$.MODULE$.exponentialJavaRDD(jsc, mean, size);
   }

   public static JavaDoubleRDD exponentialJavaRDD(final JavaSparkContext jsc, final double mean, final long size, final int numPartitions) {
      return RandomRDDs$.MODULE$.exponentialJavaRDD(jsc, mean, size, numPartitions);
   }

   public static JavaDoubleRDD exponentialJavaRDD(final JavaSparkContext jsc, final double mean, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.exponentialJavaRDD(jsc, mean, size, numPartitions, seed);
   }

   public static long exponentialRDD$default$5() {
      return RandomRDDs$.MODULE$.exponentialRDD$default$5();
   }

   public static int exponentialRDD$default$4() {
      return RandomRDDs$.MODULE$.exponentialRDD$default$4();
   }

   public static RDD exponentialRDD(final SparkContext sc, final double mean, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.exponentialRDD(sc, mean, size, numPartitions, seed);
   }

   public static JavaDoubleRDD poissonJavaRDD(final JavaSparkContext jsc, final double mean, final long size) {
      return RandomRDDs$.MODULE$.poissonJavaRDD(jsc, mean, size);
   }

   public static JavaDoubleRDD poissonJavaRDD(final JavaSparkContext jsc, final double mean, final long size, final int numPartitions) {
      return RandomRDDs$.MODULE$.poissonJavaRDD(jsc, mean, size, numPartitions);
   }

   public static JavaDoubleRDD poissonJavaRDD(final JavaSparkContext jsc, final double mean, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.poissonJavaRDD(jsc, mean, size, numPartitions, seed);
   }

   public static long poissonRDD$default$5() {
      return RandomRDDs$.MODULE$.poissonRDD$default$5();
   }

   public static int poissonRDD$default$4() {
      return RandomRDDs$.MODULE$.poissonRDD$default$4();
   }

   public static RDD poissonRDD(final SparkContext sc, final double mean, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.poissonRDD(sc, mean, size, numPartitions, seed);
   }

   public static JavaDoubleRDD normalJavaRDD(final JavaSparkContext jsc, final long size) {
      return RandomRDDs$.MODULE$.normalJavaRDD(jsc, size);
   }

   public static JavaDoubleRDD normalJavaRDD(final JavaSparkContext jsc, final long size, final int numPartitions) {
      return RandomRDDs$.MODULE$.normalJavaRDD(jsc, size, numPartitions);
   }

   public static JavaDoubleRDD normalJavaRDD(final JavaSparkContext jsc, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.normalJavaRDD(jsc, size, numPartitions, seed);
   }

   public static long normalRDD$default$4() {
      return RandomRDDs$.MODULE$.normalRDD$default$4();
   }

   public static int normalRDD$default$3() {
      return RandomRDDs$.MODULE$.normalRDD$default$3();
   }

   public static RDD normalRDD(final SparkContext sc, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.normalRDD(sc, size, numPartitions, seed);
   }

   public static JavaDoubleRDD uniformJavaRDD(final JavaSparkContext jsc, final long size) {
      return RandomRDDs$.MODULE$.uniformJavaRDD(jsc, size);
   }

   public static JavaDoubleRDD uniformJavaRDD(final JavaSparkContext jsc, final long size, final int numPartitions) {
      return RandomRDDs$.MODULE$.uniformJavaRDD(jsc, size, numPartitions);
   }

   public static JavaDoubleRDD uniformJavaRDD(final JavaSparkContext jsc, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.uniformJavaRDD(jsc, size, numPartitions, seed);
   }

   public static long uniformRDD$default$4() {
      return RandomRDDs$.MODULE$.uniformRDD$default$4();
   }

   public static int uniformRDD$default$3() {
      return RandomRDDs$.MODULE$.uniformRDD$default$3();
   }

   public static RDD uniformRDD(final SparkContext sc, final long size, final int numPartitions, final long seed) {
      return RandomRDDs$.MODULE$.uniformRDD(sc, size, numPartitions, seed);
   }
}
