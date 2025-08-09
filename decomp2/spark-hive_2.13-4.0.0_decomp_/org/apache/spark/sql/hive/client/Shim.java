package org.apache.spark.sql.hive.client;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.processors.CommandProcessor;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.spark.sql.catalyst.catalog.CatalogFunction;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011ubA\u0002\u001a4\u0003C\u0019t\bC\u0003G\u0001\u0011\u0005\u0001\nC\u0003L\u0001\u0019\u0005A\nC\u0003^\u0001\u0019\u0005a\fC\u0003v\u0001\u0019\u0005a\u000fC\u0004\u0002\u0016\u00011\t!a\u0006\t\u000f\u0005-\u0002A\"\u0001\u0002.!9\u0011q\u0007\u0001\u0007\u0002\u0005e\u0002bBA \u0001\u0019\u0005\u0011\u0011\t\u0005\b\u0003/\u0002a\u0011AA-\u0011\u001d\t\t\u0007\u0001D\u0001\u0003GBq!!\u001b\u0001\r\u0003\tY\u0007C\u0004\u0002t\u00011\t!!\u001e\t\u000f\u0005\r\u0005A\"\u0001\u0002\u0006\"9\u0011\u0011\u0017\u0001\u0007\u0002\u0005M\u0006bBAj\u0001\u0019\u0005\u0011Q\u001b\u0005\b\u0003G\u0004a\u0011AAs\u0011\u001d\ty\u000f\u0001D\u0001\u0003cDq!a?\u0001\r\u0003\ti\u0010C\u0004\u0003\u0018\u00011\tA!\u0007\t\u000f\t\r\u0002A\"\u0001\u0003&!I!\u0011\u0007\u0001\u0012\u0002\u0013\u0005!1\u0007\u0005\b\u0005\u0013\u0002a\u0011\u0001B&\u0011\u001d\u0011y\u0006\u0001D\u0001\u0005CBqA!\u001b\u0001\r\u0003\u0011Y\u0007C\u0004\u0003r\u00011\tAa\u001d\t\u000f\tm\u0004A\"\u0001\u0003~!9!\u0011\u0013\u0001\u0007\u0002\tM\u0005b\u0002BN\u0001\u0019\u0005!Q\u0014\u0005\b\u00057\u0003a\u0011\u0001BX\u0011\u001d\u0011Y\f\u0001D\u0001\u0005{CqA!5\u0001\r\u0003\u0011\u0019\u000eC\u0004\u0003|\u00021\tA!@\t\u000f\r-\u0001A\"\u0001\u0004\u000e!91\u0011\u0004\u0001\u0007\u0002\rm\u0001bBB\u001b\u0001\u0019\u00051q\u0007\u0005\b\u0007\u000f\u0002a\u0011AB%\u0011\u001d\u0019\u0019\u0006\u0001D\u0001\u0007+Bqaa\u0019\u0001\r\u0003\u0019)\u0007C\u0004\u0004n\u00011\taa\u001c\t\u000f\re\u0004A\"\u0001\u0004|!911\u0011\u0001\u0007\u0002\r\u0015\u0005b\u0002B9\u0001\u0019\u00051\u0011\u0013\u0005\b\u0007G\u0003a\u0011ABS\u0011\u001d\u00199\f\u0001D\u0001\u0007sCqa!0\u0001\r\u0003\u0019y\fC\u0004\u0004H\u00021\ta!3\t\u000f\rM\u0007A\"\u0001\u0004V\"91q\u001d\u0001\u0005\u0012\r%\bb\u0002C\u001a\u0001\u0011\u0005AQ\u0007\u0002\u0005'\"LWN\u0003\u00025k\u000511\r\\5f]RT!AN\u001c\u0002\t!Lg/\u001a\u0006\u0003qe\n1a]9m\u0015\tQ4(A\u0003ta\u0006\u00148N\u0003\u0002={\u00051\u0011\r]1dQ\u0016T\u0011AP\u0001\u0004_J<7C\u0001\u0001A!\t\tE)D\u0001C\u0015\u0005\u0019\u0015!B:dC2\f\u0017BA#C\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001J!\tQ\u0005!D\u00014\u0003Y\u0019X\r^\"veJ,g\u000e^*fgNLwN\\*uCR,GCA'Q!\t\te*\u0003\u0002P\u0005\n!QK\\5u\u0011\u0015\t&\u00011\u0001S\u0003\u0015\u0019H/\u0019;f!\t\u00196,D\u0001U\u0015\t)f+A\u0004tKN\u001c\u0018n\u001c8\u000b\u0005]C\u0016AA9m\u0015\t1\u0014L\u0003\u0002[w\u00051\u0001.\u00193p_BL!\u0001\u0018+\u0003\u0019M+7o]5p]N#\u0018\r^3\u0002\u001f\u001d,G\u000fR1uC2{7-\u0019;j_:$\"aX7\u0011\u0007\u0005\u0003'-\u0003\u0002b\u0005\n1q\n\u001d;j_:\u0004\"a\u00196\u000f\u0005\u0011D\u0007CA3C\u001b\u00051'BA4H\u0003\u0019a$o\\8u}%\u0011\u0011NQ\u0001\u0007!J,G-\u001a4\n\u0005-d'AB*ue&twM\u0003\u0002j\u0005\")an\u0001a\u0001_\u0006)A/\u00192mKB\u0011\u0001o]\u0007\u0002c*\u0011!OV\u0001\t[\u0016$\u0018\rZ1uC&\u0011A/\u001d\u0002\u0006)\u0006\u0014G.Z\u0001\u000fGJ,\u0017\r^3ECR\f'-Y:f)\u0015iuo_A\u0006\u0011\u00151D\u00011\u0001y!\t\u0001\u00180\u0003\u0002{c\n!\u0001*\u001b<f\u0011\u0015aH\u00011\u0001~\u0003\t!'\rE\u0002\u007f\u0003\u000fi\u0011a \u0006\u0005\u0003\u0003\t\u0019!A\u0002ba&T1!!\u0002Y\u0003%iW\r^1ti>\u0014X-C\u0002\u0002\n}\u0014\u0001\u0002R1uC\n\f7/\u001a\u0005\b\u0003\u001b!\u0001\u0019AA\b\u00039IwM\\8sK&3W\t_5tiN\u00042!QA\t\u0013\r\t\u0019B\u0011\u0002\b\u0005>|G.Z1o\u00031!'o\u001c9ECR\f'-Y:f)-i\u0015\u0011DA\u000e\u0003?\t\u0019#a\n\t\u000bY*\u0001\u0019\u0001=\t\r\u0005uQ\u00011\u0001c\u0003\u0019!'MT1nK\"9\u0011\u0011E\u0003A\u0002\u0005=\u0011A\u00033fY\u0016$X\rR1uC\"9\u0011QE\u0003A\u0002\u0005=\u0011aD5h]>\u0014X-\u00168l]><h\u000e\u00122\t\u000f\u0005%R\u00011\u0001\u0002\u0010\u000591-Y:dC\u0012,\u0017!D1mi\u0016\u0014H)\u0019;bE\u0006\u001cX\rF\u0004N\u0003_\t\t$a\r\t\u000bY2\u0001\u0019\u0001=\t\r\u0005ua\u00011\u0001c\u0011\u0019\t)D\u0002a\u0001{\u0006\tA-A\u0006hKR$\u0015\r^1cCN,G#B?\u0002<\u0005u\u0002\"\u0002\u001c\b\u0001\u0004A\bBBA\u000f\u000f\u0001\u0007!-A\bhKR\fE\u000e\u001c#bi\u0006\u0014\u0017m]3t)\u0011\t\u0019%!\u0016\u0011\u000b\u0005\u0015\u0013q\n2\u000f\t\u0005\u001d\u00131\n\b\u0004K\u0006%\u0013\"A\"\n\u0007\u00055#)A\u0004qC\u000e\\\u0017mZ3\n\t\u0005E\u00131\u000b\u0002\u0004'\u0016\f(bAA'\u0005\")a\u0007\u0003a\u0001q\u0006)r-\u001a;ECR\f'-Y:fg\nK\b+\u0019;uKJtGCBA\"\u00037\ni\u0006C\u00037\u0013\u0001\u0007\u0001\u0010\u0003\u0004\u0002`%\u0001\rAY\u0001\ba\u0006$H/\u001a:o\u00039!\u0017\r^1cCN,W\t_5tiN$b!a\u0004\u0002f\u0005\u001d\u0004\"\u0002\u001c\u000b\u0001\u0004A\bBBA\u000f\u0015\u0001\u0007!-A\btKR$\u0015\r^1M_\u000e\fG/[8o)\u0015i\u0015QNA8\u0011\u0015q7\u00021\u0001p\u0011\u0019\t\th\u0003a\u0001E\u0006\u0019An\\2\u0002!\u001d,G/\u00117m!\u0006\u0014H/\u001b;j_:\u001cHCBA<\u0003\u007f\n\t\t\u0005\u0004\u0002F\u0005=\u0013\u0011\u0010\t\u0004a\u0006m\u0014bAA?c\nI\u0001+\u0019:uSRLwN\u001c\u0005\u0006m1\u0001\r\u0001\u001f\u0005\u0006]2\u0001\ra\\\u0001\u0016O\u0016$\b+\u0019:uSRLwN\\:Cs\u001aKG\u000e^3s))\t9(a\"\u0002\n\u0006-\u0015\u0011\u0015\u0005\u0006m5\u0001\r\u0001\u001f\u0005\u0006]6\u0001\ra\u001c\u0005\b\u0003\u001bk\u0001\u0019AAH\u0003)\u0001(/\u001a3jG\u0006$Xm\u001d\t\u0007\u0003\u000b\ny%!%\u0011\t\u0005M\u0015QT\u0007\u0003\u0003+SA!a&\u0002\u001a\u0006YQ\r\u001f9sKN\u001c\u0018n\u001c8t\u0015\r\tYjN\u0001\tG\u0006$\u0018\r\\=ti&!\u0011qTAK\u0005))\u0005\u0010\u001d:fgNLwN\u001c\u0005\b\u0003Gk\u0001\u0019AAS\u00031\u0019\u0017\r^1m_\u001e$\u0016M\u00197f!\u0011\t9+!,\u000e\u0005\u0005%&\u0002BAV\u00033\u000bqaY1uC2|w-\u0003\u0003\u00020\u0006%&\u0001D\"bi\u0006dwn\u001a+bE2,\u0017aE4fi\u000e{W.\\1oIB\u0013xnY3tg>\u0014HCBA[\u0003\u0003\f)\r\u0005\u0003\u00028\u0006uVBAA]\u0015\r\tYLV\u0001\u000baJ|7-Z:t_J\u001c\u0018\u0002BA`\u0003s\u0013\u0001cQ8n[\u0006tG\r\u0015:pG\u0016\u001c8o\u001c:\t\r\u0005\rg\u00021\u0001c\u0003\u0015!xn[3o\u0011\u001d\t9M\u0004a\u0001\u0003\u0013\fAaY8oMB!\u00111ZAh\u001b\t\tiMC\u0002\u0002HbKA!!5\u0002N\nA\u0001*\u001b<f\u0007>tg-\u0001\thKR$%/\u001b<feJ+7/\u001e7ugR!\u00111IAl\u0011\u001d\tIn\u0004a\u0001\u00037\fa\u0001\u001a:jm\u0016\u0014\b\u0003BAo\u0003?l\u0011AV\u0005\u0004\u0003C4&A\u0002#sSZ,'/A\u0015hKRlU\r^1ti>\u0014Xm\u00117jK:$8i\u001c8oK\u000e$(+\u001a;ss\u0012+G.Y=NS2d\u0017n\u001d\u000b\u0005\u0003O\fi\u000fE\u0002B\u0003SL1!a;C\u0005\u0011auN\\4\t\u000f\u0005\u001d\u0007\u00031\u0001\u0002J\u0006Q\u0011\r\u001c;feR\u000b'\r\\3\u0015\u000f5\u000b\u00190!>\u0002z\")a'\u0005a\u0001q\"1\u0011q_\tA\u0002\t\f\u0011\u0002^1cY\u0016t\u0015-\\3\t\u000b9\f\u0002\u0019A8\u0002\u001f\u0005dG/\u001a:QCJ$\u0018\u000e^5p]N$r!TA\u0000\u0005\u0003\u0011\u0019\u0001C\u00037%\u0001\u0007\u0001\u0010\u0003\u0004\u0002xJ\u0001\rA\u0019\u0005\b\u0005\u000b\u0011\u0002\u0019\u0001B\u0004\u0003!qWm\u001e)beR\u001c\bC\u0002B\u0005\u0005'\tI(\u0004\u0002\u0003\f)!!Q\u0002B\b\u0003\u0011)H/\u001b7\u000b\u0005\tE\u0011\u0001\u00026bm\u0006LAA!\u0006\u0003\f\t!A*[:u\u0003-\u0019'/Z1uKR\u000b'\r\\3\u0015\u000f5\u0013YB!\b\u0003 !)ag\u0005a\u0001q\")an\u0005a\u0001_\"9!\u0011E\nA\u0002\u0005=\u0011aC5g\u001d>$X\t_5tiN\f\u0001bZ3u)\u0006\u0014G.\u001a\u000b\n_\n\u001d\"\u0011\u0006B\u0016\u0005[AQA\u000e\u000bA\u0002aDa!!\b\u0015\u0001\u0004\u0011\u0007BBA|)\u0001\u0007!\rC\u0005\u00030Q\u0001\n\u00111\u0001\u0002\u0010\u0005qA\u000f\u001b:po\u0016C8-\u001a9uS>t\u0017AE4fiR\u000b'\r\\3%I\u00164\u0017-\u001e7uIQ*\"A!\u000e+\t\u0005=!qG\u0016\u0003\u0005s\u0001BAa\u000f\u0003F5\u0011!Q\b\u0006\u0005\u0005\u007f\u0011\t%A\u0005v]\u000eDWmY6fI*\u0019!1\t\"\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003H\tu\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006yq-\u001a;UC\ndWm\u001d\"z)f\u0004X\r\u0006\u0006\u0002D\t5#q\nB)\u0005'BQA\u000e\fA\u0002aDa!!\b\u0017\u0001\u0004\u0011\u0007BBA0-\u0001\u0007!\rC\u0004\u0003VY\u0001\rAa\u0016\u0002\u0013Q\f'\r\\3UsB,\u0007\u0003\u0002B-\u00057j!!a\u0001\n\t\tu\u00131\u0001\u0002\n)\u0006\u0014G.\u001a+za\u0016\f!cZ3u)\u0006\u0014G.Z:CsB\u000bG\u000f^3s]RA\u00111\tB2\u0005K\u00129\u0007C\u00037/\u0001\u0007\u0001\u0010\u0003\u0004\u0002\u001e]\u0001\rA\u0019\u0005\u0007\u0003?:\u0002\u0019\u00012\u0002\u0019\u001d,G/\u00117m)\u0006\u0014G.Z:\u0015\r\u0005\r#Q\u000eB8\u0011\u00151\u0004\u00041\u0001y\u0011\u0019\ti\u0002\u0007a\u0001E\u0006IAM]8q)\u0006\u0014G.\u001a\u000b\b\u001b\nU$q\u000fB=\u0011\u00151\u0014\u00041\u0001y\u0011\u0019\ti\"\u0007a\u0001E\"1\u0011q_\rA\u0002\t\fAbZ3u!\u0006\u0014H/\u001b;j_:$\"\"!\u001f\u0003\u0000\t\u0005%1\u0011BG\u0011\u00151$\u00041\u0001y\u0011\u0015q'\u00041\u0001p\u0011\u001d\u0011)I\u0007a\u0001\u0005\u000f\u000b\u0001\u0002]1siN\u0003Xm\u0019\t\u0007\u0005\u0013\u0011II\u00192\n\t\t-%1\u0002\u0002\u0004\u001b\u0006\u0004\bb\u0002BH5\u0001\u0007\u0011qB\u0001\fM>\u00148-Z\"sK\u0006$X-A\u0007hKR\u0004\u0016M\u001d;ji&|gn\u001d\u000b\t\u0003o\u0012)Ja&\u0003\u001a\")ag\u0007a\u0001q\")an\u0007a\u0001_\"9!QQ\u000eA\u0002\t\u001d\u0015!E4fiB\u000b'\u000f^5uS>tg*Y7fgRQ\u00111\tBP\u0005C\u0013\u0019K!*\t\u000bYb\u0002\u0019\u0001=\t\r\u0005uA\u00041\u0001c\u0011\u0019\t9\u0010\ba\u0001E\"9!q\u0015\u000fA\u0002\t%\u0016aA7bqB\u0019\u0011Ia+\n\u0007\t5&IA\u0003TQ>\u0014H\u000f\u0006\u0007\u0002D\tE&1\u0017B[\u0005o\u0013I\fC\u00037;\u0001\u0007\u0001\u0010\u0003\u0004\u0002\u001eu\u0001\rA\u0019\u0005\u0007\u0003ol\u0002\u0019\u00012\t\u000f\t\u0015U\u00041\u0001\u0003\b\"9!qU\u000fA\u0002\t%\u0016\u0001E2sK\u0006$X\rU1si&$\u0018n\u001c8t)%i%q\u0018Ba\u0005\u0007\u0014y\rC\u00037=\u0001\u0007\u0001\u0010C\u0003o=\u0001\u0007q\u000eC\u0004\u0003Fz\u0001\rAa2\u0002\u000bA\f'\u000f^:\u0011\r\u0005\u0015\u0013q\nBe!\u0011\t9Ka3\n\t\t5\u0017\u0011\u0016\u0002\u0016\u0007\u0006$\u0018\r\\8h)\u0006\u0014G.\u001a)beRLG/[8o\u0011\u001d\tiA\ba\u0001\u0003\u001f\tQ\u0002\\8bIB\u000b'\u000f^5uS>tG#E'\u0003V\n]'q\u001dBu\u0005W\u0014yOa=\u0003x\")ag\ba\u0001q\"9!\u0011\\\u0010A\u0002\tm\u0017\u0001\u00037pC\u0012\u0004\u0016\r\u001e5\u0011\t\tu'1]\u0007\u0003\u0005?T1A!9Z\u0003\t17/\u0003\u0003\u0003f\n}'\u0001\u0002)bi\"Da!a> \u0001\u0004\u0011\u0007b\u0002BC?\u0001\u0007!q\u0011\u0005\b\u0005[|\u0002\u0019AA\b\u0003\u001d\u0011X\r\u001d7bG\u0016DqA!= \u0001\u0004\ty!A\tj]\",'/\u001b;UC\ndWm\u00159fGNDqA!> \u0001\u0004\ty!A\u000bjgN[Wm^3e'R|'/Z!t'V\u0014G-\u001b:\t\u000f\tex\u00041\u0001\u0002\u0010\u0005Q\u0011n]*sG2{7-\u00197\u0002\u001fI,g.Y7f!\u0006\u0014H/\u001b;j_:$\u0012\"\u0014B\u0000\u0007\u0003\u0019\u0019aa\u0002\t\u000bY\u0002\u0003\u0019\u0001=\t\u000b9\u0004\u0003\u0019A8\t\u000f\r\u0015\u0001\u00051\u0001\u0003\b\u0006Yq\u000e\u001c3QCJ$8\u000b]3d\u0011\u001d\u0019I\u0001\ta\u0001\u0003s\nqA\\3x!\u0006\u0014H/A\u0005m_\u0006$G+\u00192mKRYQja\u0004\u0004\u0012\rM1QCB\f\u0011\u00151\u0014\u00051\u0001y\u0011\u001d\u0011I.\ta\u0001\u00057Da!a>\"\u0001\u0004\u0011\u0007b\u0002BwC\u0001\u0007\u0011q\u0002\u0005\b\u0005s\f\u0003\u0019AA\b\u0003Uaw.\u00193Es:\fW.[2QCJ$\u0018\u000e^5p]N$r\"TB\u000f\u0007?\u0019\tca\t\u0004&\r\u001d2\u0011\u0007\u0005\u0006m\t\u0002\r\u0001\u001f\u0005\b\u00053\u0014\u0003\u0019\u0001Bn\u0011\u0019\t9P\ta\u0001E\"9!Q\u0011\u0012A\u0002\t\u001d\u0005b\u0002BwE\u0001\u0007\u0011q\u0002\u0005\b\u0007S\u0011\u0003\u0019AB\u0016\u0003\u0015qW/\u001c#Q!\r\t5QF\u0005\u0004\u0007_\u0011%aA%oi\"111\u0007\u0012A\u0002=\f\u0011\u0002[5wKR\u000b'\r\\3\u0002\u001d\r\u0014X-\u0019;f\rVt7\r^5p]R9Qj!\u000f\u0004<\ru\u0002\"\u0002\u001c$\u0001\u0004A\b\"\u0002?$\u0001\u0004\u0011\u0007bBB G\u0001\u00071\u0011I\u0001\u0005MVt7\r\u0005\u0003\u0002(\u000e\r\u0013\u0002BB#\u0003S\u0013qbQ1uC2|wMR;oGRLwN\\\u0001\rIJ|\u0007OR;oGRLwN\u001c\u000b\b\u001b\u000e-3QJB(\u0011\u00151D\u00051\u0001y\u0011\u0015aH\u00051\u0001c\u0011\u0019\u0019\t\u0006\na\u0001E\u0006!a.Y7f\u00039\u0011XM\\1nK\u001a+hn\u0019;j_:$\u0012\"TB,\u00073\u001aYfa\u0018\t\u000bY*\u0003\u0019\u0001=\t\u000bq,\u0003\u0019\u00012\t\r\ruS\u00051\u0001c\u0003\u001dyG\u000e\u001a(b[\u0016Daa!\u0019&\u0001\u0004\u0011\u0017a\u00028fo:\u000bW.Z\u0001\u000eC2$XM\u001d$v]\u000e$\u0018n\u001c8\u0015\u000f5\u001b9g!\u001b\u0004l!)aG\na\u0001q\")AP\na\u0001E\"91q\b\u0014A\u0002\r\u0005\u0013!E4fi\u001a+hn\u0019;j_:|\u0005\u000f^5p]RA1\u0011OB:\u0007k\u001a9\b\u0005\u0003BA\u000e\u0005\u0003\"\u0002\u001c(\u0001\u0004A\b\"\u0002?(\u0001\u0004\u0011\u0007BBB)O\u0001\u0007!-A\u0007mSN$h)\u001e8di&|gn\u001d\u000b\t\u0003\u0007\u001aiha \u0004\u0002\")a\u0007\u000ba\u0001q\")A\u0010\u000ba\u0001E\"1\u0011q\f\u0015A\u0002\t\f\u0011\u0002\u001a:pa&sG-\u001a=\u0015\u00135\u001b9i!#\u0004\f\u000e5\u0005\"\u0002\u001c*\u0001\u0004A\bBBA\u000fS\u0001\u0007!\r\u0003\u0004\u0002x&\u0002\rA\u0019\u0005\u0007\u0007\u001fK\u0003\u0019\u00012\u0002\u0013%tG-\u001a=OC6,G#D'\u0004\u0014\u000eU5qSBM\u00077\u001by\nC\u00037U\u0001\u0007\u0001\u0010\u0003\u0004\u0002\u001e)\u0002\rA\u0019\u0005\u0007\u0003oT\u0003\u0019\u00012\t\u000f\u0005\u0005\"\u00061\u0001\u0002\u0010!91Q\u0014\u0016A\u0002\u0005=\u0011!E5h]>\u0014X-\u00134O_R,\u00050[:ug\"91\u0011\u0015\u0016A\u0002\u0005=\u0011!\u00029ve\u001e,\u0017!\u00043s_B\u0004\u0016M\u001d;ji&|g\u000eF\u0007N\u0007O\u001bIka+\u0004.\u000eM6Q\u0017\u0005\u0006m-\u0002\r\u0001\u001f\u0005\u0007\u0003;Y\u0003\u0019\u00012\t\r\u0005]8\u00061\u0001c\u0011\u001d\u0019yk\u000ba\u0001\u0007c\u000bA\u0001]1siB)!\u0011\u0002B\nE\"9\u0011\u0011E\u0016A\u0002\u0005=\u0001bBBQW\u0001\u0007\u0011qB\u0001\u0015O\u0016$H)\u0019;bE\u0006\u001cXmT<oKJt\u0015-\\3\u0015\u0007\t\u001cY\fC\u0003}Y\u0001\u0007Q0\u0001\u000btKR$\u0015\r^1cCN,wj\u001e8fe:\u000bW.\u001a\u000b\u0006\u001b\u000e\u000571\u0019\u0005\u0006y6\u0002\r! \u0005\u0007\u0007\u000bl\u0003\u0019\u00012\u0002\u000b=<h.\u001a:\u0002\r\u001d,G/T*D)\u0011\u0019Ym!5\u0011\t\te3QZ\u0005\u0005\u0007\u001f\f\u0019A\u0001\tJ\u001b\u0016$\u0018m\u0015;pe\u0016\u001cE.[3oi\")aG\fa\u0001q\u0006Qq-\u001a;J]\u0012,\u00070Z:\u0015\u0015\r]7q\\Bq\u0007G\u001c)\u000f\u0005\u0004\u0002F\u0005=3\u0011\u001c\t\u0004}\u000em\u0017bABo\u007f\n)\u0011J\u001c3fq\")ag\fa\u0001q\"1\u0011QD\u0018A\u0002\tDa!a>0\u0001\u0004\u0011\u0007b\u0002BT_\u0001\u0007!\u0011V\u0001\u000bM&tG-T3uQ>$G\u0003CBv\u0007w$i\u0002b\b\u0011\t\r58q_\u0007\u0003\u0007_TAa!=\u0004t\u00069!/\u001a4mK\u000e$(\u0002BB{\u0005\u001f\tA\u0001\\1oO&!1\u0011`Bx\u0005\u0019iU\r\u001e5pI\"91Q \u0019A\u0002\r}\u0018!B6mCN\u001c\b\u0007\u0002C\u0001\t\u0017\u0001Ra\u0019C\u0002\t\u000fI1\u0001\"\u0002m\u0005\u0015\u0019E.Y:t!\u0011!I\u0001b\u0003\r\u0001\u0011aAQBB~\u0003\u0003\u0005\tQ!\u0001\u0005\u0010\t\u0019q\fJ\u0019\u0012\t\u0011EAq\u0003\t\u0004\u0003\u0012M\u0011b\u0001C\u000b\u0005\n9aj\u001c;iS:<\u0007cA!\u0005\u001a%\u0019A1\u0004\"\u0003\u0007\u0005s\u0017\u0010\u0003\u0004\u0004RA\u0002\rA\u0019\u0005\b\tC\u0001\u0004\u0019\u0001C\u0012\u0003\u0011\t'oZ:\u0011\u000b\u0005#)\u0003\"\u000b\n\u0007\u0011\u001d\"I\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002D\u0001b\u000b\u00050A)1\rb\u0001\u0005.A!A\u0011\u0002C\u0018\t1!\t\u0004b\b\u0002\u0002\u0003\u0005)\u0011\u0001C\b\u0005\ryFEM\u0001\u000fe\u0016\u001cwN\u001d3ISZ,7)\u00197m)\u0005i\u0015f\u0001\u0001\u0005:%\u0019A1H\u001a\u0003\u0013MC\u0017.\\0we}\u0003\u0004"
)
public abstract class Shim {
   public abstract void setCurrentSessionState(final SessionState state);

   public abstract Option getDataLocation(final Table table);

   public abstract void createDatabase(final Hive hive, final Database db, final boolean ignoreIfExists);

   public abstract void dropDatabase(final Hive hive, final String dbName, final boolean deleteData, final boolean ignoreUnknownDb, final boolean cascade);

   public abstract void alterDatabase(final Hive hive, final String dbName, final Database d);

   public abstract Database getDatabase(final Hive hive, final String dbName);

   public abstract Seq getAllDatabases(final Hive hive);

   public abstract Seq getDatabasesByPattern(final Hive hive, final String pattern);

   public abstract boolean databaseExists(final Hive hive, final String dbName);

   public abstract void setDataLocation(final Table table, final String loc);

   public abstract Seq getAllPartitions(final Hive hive, final Table table);

   public abstract Seq getPartitionsByFilter(final Hive hive, final Table table, final Seq predicates, final CatalogTable catalogTable);

   public abstract CommandProcessor getCommandProcessor(final String token, final HiveConf conf);

   public abstract Seq getDriverResults(final Driver driver);

   public abstract long getMetastoreClientConnectRetryDelayMillis(final HiveConf conf);

   public abstract void alterTable(final Hive hive, final String tableName, final Table table);

   public abstract void alterPartitions(final Hive hive, final String tableName, final List newParts);

   public abstract void createTable(final Hive hive, final Table table, final boolean ifNotExists);

   public abstract Table getTable(final Hive hive, final String dbName, final String tableName, final boolean throwException);

   public boolean getTable$default$4() {
      return true;
   }

   public abstract Seq getTablesByType(final Hive hive, final String dbName, final String pattern, final TableType tableType);

   public abstract Seq getTablesByPattern(final Hive hive, final String dbName, final String pattern);

   public abstract Seq getAllTables(final Hive hive, final String dbName);

   public abstract void dropTable(final Hive hive, final String dbName, final String tableName);

   public abstract Partition getPartition(final Hive hive, final Table table, final Map partSpec, final boolean forceCreate);

   public abstract Seq getPartitions(final Hive hive, final Table table, final Map partSpec);

   public abstract Seq getPartitionNames(final Hive hive, final String dbName, final String tableName, final short max);

   public abstract Seq getPartitionNames(final Hive hive, final String dbName, final String tableName, final Map partSpec, final short max);

   public abstract void createPartitions(final Hive hive, final Table table, final Seq parts, final boolean ignoreIfExists);

   public abstract void loadPartition(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final boolean inheritTableSpecs, final boolean isSkewedStoreAsSubdir, final boolean isSrcLocal);

   public abstract void renamePartition(final Hive hive, final Table table, final Map oldPartSpec, final Partition newPart);

   public abstract void loadTable(final Hive hive, final Path loadPath, final String tableName, final boolean replace, final boolean isSrcLocal);

   public abstract void loadDynamicPartitions(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final int numDP, final Table hiveTable);

   public abstract void createFunction(final Hive hive, final String db, final CatalogFunction func);

   public abstract void dropFunction(final Hive hive, final String db, final String name);

   public abstract void renameFunction(final Hive hive, final String db, final String oldName, final String newName);

   public abstract void alterFunction(final Hive hive, final String db, final CatalogFunction func);

   public abstract Option getFunctionOption(final Hive hive, final String db, final String name);

   public abstract Seq listFunctions(final Hive hive, final String db, final String pattern);

   public abstract void dropIndex(final Hive hive, final String dbName, final String tableName, final String indexName);

   public abstract void dropTable(final Hive hive, final String dbName, final String tableName, final boolean deleteData, final boolean ignoreIfNotExists, final boolean purge);

   public abstract void dropPartition(final Hive hive, final String dbName, final String tableName, final List part, final boolean deleteData, final boolean purge);

   public abstract String getDatabaseOwnerName(final Database db);

   public abstract void setDatabaseOwnerName(final Database db, final String owner);

   public abstract IMetaStoreClient getMSC(final Hive hive);

   public abstract Seq getIndexes(final Hive hive, final String dbName, final String tableName, final short max);

   public Method findMethod(final Class klass, final String name, final Seq args) {
      return klass.getMethod(name, (Class[])args.toArray(.MODULE$.apply(Class.class)));
   }

   public void recordHiveCall() {
      org.apache.spark.metrics.source.HiveCatalogMetrics..MODULE$.incrementHiveClientCalls(1);
   }
}
