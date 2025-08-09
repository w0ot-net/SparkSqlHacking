package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.plan.FileSinkDesc;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.ExternalCatalog;
import org.apache.spark.sql.catalyst.catalog.ExternalCatalogWithListener;
import org.apache.spark.sql.catalyst.expressions.AttributeSet;
import org.apache.spark.sql.catalyst.plans.logical.CTEInChildren;
import org.apache.spark.sql.catalyst.plans.logical.Command;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.plans.logical.Statistics;
import org.apache.spark.sql.catalyst.trees.TreeNode;
import org.apache.spark.sql.catalyst.trees.TreeNodeTag;
import org.apache.spark.sql.catalyst.trees.UnaryLike;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap;
import org.apache.spark.sql.execution.SparkPlan;
import org.apache.spark.sql.execution.command.DataWritingCommand;
import org.apache.spark.sql.execution.datasources.BasicWriteJobStatsTracker;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.execution.datasources.V1WriteCommand;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u001dd\u0001B\u001d;\u0001\u001eC\u0001B\u001d\u0001\u0003\u0016\u0004%\ta\u001d\u0005\tu\u0002\u0011\t\u0012)A\u0005i\"A1\u0010\u0001BK\u0002\u0013\u0005A\u0010C\u0005\u0002\u0018\u0001\u0011\t\u0012)A\u0005{\"Q\u0011\u0011\u0004\u0001\u0003\u0016\u0004%\t!a\u0007\t\u0013\u0005u\u0001A!E!\u0002\u0013A\u0005BCA\u0010\u0001\tU\r\u0011\"\u0001\u0002\"!Q\u0011\u0011\u0006\u0001\u0003\u0012\u0003\u0006I!a\t\t\u0015\u0005-\u0002A!f\u0001\n\u0003\t\t\u0003\u0003\u0006\u0002.\u0001\u0011\t\u0012)A\u0005\u0003GA!\"a\f\u0001\u0005+\u0007I\u0011AA\u0019\u0011)\tI\u0004\u0001B\tB\u0003%\u00111\u0007\u0005\u000b\u0003w\u0001!Q3A\u0005\u0002\u0005u\u0002BCA'\u0001\tE\t\u0015!\u0003\u0002@!Q\u0011q\n\u0001\u0003\u0016\u0004%\t!!\u0015\t\u0015\u0005m\u0003A!E!\u0002\u0013\t\u0019\u0006\u0003\u0006\u0002^\u0001\u0011)\u001a!C\u0001\u0003?B!\"a\u0019\u0001\u0005#\u0005\u000b\u0011BA1\u0011)\t)\u0007\u0001BK\u0002\u0013\u0005\u0011q\r\u0005\u000b\u0003_\u0002!\u0011#Q\u0001\n\u0005%\u0004BCA9\u0001\tU\r\u0011\"\u0001\u0002t!Q\u00111\u0010\u0001\u0003\u0012\u0003\u0006I!!\u001e\t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\b\"9\u0011\u0011\u0015\u0001\u0005B\u0005\r\u0006bBAf\u0001\u0011\u0005\u0013Q\u001a\u0005\b\u0003/\u0004A\u0011IAm\u0011\u001d\u0011\t\u0001\u0001C\u0005\u0005\u0007AqA!\u0010\u0001\t#\u0012y\u0004C\u0005\u0003F\u0001\t\t\u0011\"\u0001\u0003H!I!q\f\u0001\u0012\u0002\u0013\u0005!\u0011\r\u0005\n\u0005o\u0002\u0011\u0013!C\u0001\u0005sB\u0011B! \u0001#\u0003%\tAa \t\u0013\t\r\u0005!%A\u0005\u0002\t\u0015\u0005\"\u0003BE\u0001E\u0005I\u0011\u0001BC\u0011%\u0011Y\tAI\u0001\n\u0003\u0011i\tC\u0005\u0003\u0012\u0002\t\n\u0011\"\u0001\u0003\u0014\"I!q\u0013\u0001\u0012\u0002\u0013\u0005!\u0011\u0014\u0005\n\u0005;\u0003\u0011\u0013!C\u0001\u0005?C\u0011Ba)\u0001#\u0003%\tA!*\t\u0013\t%\u0006!%A\u0005\u0002\t-\u0006\"\u0003BX\u0001\u0005\u0005I\u0011\tBY\u0011%\u0011\t\rAA\u0001\n\u0003\u0011\u0019\rC\u0005\u0003L\u0002\t\t\u0011\"\u0001\u0003N\"I!\u0011\u001c\u0001\u0002\u0002\u0013\u0005#1\u001c\u0005\n\u0005S\u0004\u0011\u0011!C\u0001\u0005WD\u0011Ba<\u0001\u0003\u0003%\tE!=\t\u0013\tU\b!!A\u0005B\t]xa\u0002B~u!\u0005!Q \u0004\u0007siB\tAa@\t\u000f\u0005\u0015\u0015\u0007\"\u0001\u0004\u0012!I11C\u0019C\u0002\u0013\u00051Q\u0003\u0005\t\u0007G\t\u0004\u0015!\u0003\u0004\u0018!91QE\u0019\u0005\u0002\r\u001d\u0002\"CB\u0013c\u0005\u0005I\u0011QB\u001b\u0011%\u0019i%MA\u0001\n\u0003\u001by\u0005C\u0005\u0004^E\n\t\u0011\"\u0003\u0004`\t\u0019\u0012J\\:feRLe\u000e^8ISZ,G+\u00192mK*\u00111\bP\u0001\nKb,7-\u001e;j_:T!!\u0010 \u0002\t!Lg/\u001a\u0006\u0003\u007f\u0001\u000b1a]9m\u0015\t\t%)A\u0003ta\u0006\u00148N\u0003\u0002D\t\u00061\u0011\r]1dQ\u0016T\u0011!R\u0001\u0004_J<7\u0001A\n\b\u0001!\u0013f+\u00181g!\tI\u0005+D\u0001K\u0015\tYE*A\u0004m_\u001eL7-\u00197\u000b\u00055s\u0015!\u00029mC:\u001c(BA(?\u0003!\u0019\u0017\r^1msN$\u0018BA)K\u0005-aunZ5dC2\u0004F.\u00198\u0011\u0005M#V\"\u0001\u001e\n\u0005US$AD*bm\u0016\f5\u000fS5wK\u001aKG.\u001a\t\u0003/nk\u0011\u0001\u0017\u0006\u00033j\u000b1\u0002Z1uCN|WO]2fg*\u00111HP\u0005\u00039b\u0013aBV\u0019Xe&$XmQ8n[\u0006tG\r\u0005\u0002T=&\u0011qL\u000f\u0002\u0012-F:&/\u001b;fg\"Kg/Z+uS2\u001c\bCA1e\u001b\u0005\u0011'\"A2\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0014'a\u0002)s_\u0012,8\r\u001e\t\u0003O>t!\u0001[7\u000f\u0005%dW\"\u00016\u000b\u0005-4\u0015A\u0002\u001fs_>$h(C\u0001d\u0013\tq'-A\u0004qC\u000e\\\u0017mZ3\n\u0005A\f(\u0001D*fe&\fG.\u001b>bE2,'B\u00018c\u0003\u0015!\u0018M\u00197f+\u0005!\bCA;y\u001b\u00051(BA<O\u0003\u001d\u0019\u0017\r^1m_\u001eL!!\u001f<\u0003\u0019\r\u000bG/\u00197pOR\u000b'\r\\3\u0002\rQ\f'\r\\3!\u0003%\u0001\u0018M\u001d;ji&|g.F\u0001~!\u001dq\u0018QAA\u0006\u0003#q1a`A\u0001!\tI'-C\u0002\u0002\u0004\t\fa\u0001\u0015:fI\u00164\u0017\u0002BA\u0004\u0003\u0013\u00111!T1q\u0015\r\t\u0019A\u0019\t\u0004}\u00065\u0011\u0002BA\b\u0003\u0013\u0011aa\u0015;sS:<\u0007#B1\u0002\u0014\u0005-\u0011bAA\u000bE\n1q\n\u001d;j_:\f!\u0002]1si&$\u0018n\u001c8!\u0003\u0015\tX/\u001a:z+\u0005A\u0015AB9vKJL\b%A\u0005pm\u0016\u0014xO]5uKV\u0011\u00111\u0005\t\u0004C\u0006\u0015\u0012bAA\u0014E\n9!i\\8mK\u0006t\u0017AC8wKJ<(/\u001b;fA\u0005!\u0012N\u001a)beRLG/[8o\u001d>$X\t_5tiN\fQ#\u001b4QCJ$\u0018\u000e^5p]:{G/\u0012=jgR\u001c\b%A\tpkR\u0004X\u000f^\"pYVlgNT1nKN,\"!a\r\u0011\u000b\u001d\f)$a\u0003\n\u0007\u0005]\u0012OA\u0002TKF\f!c\\;uaV$8i\u001c7v[:t\u0015-\\3tA\u0005\u0001\u0002/\u0019:uSRLwN\\\"pYVlgn]\u000b\u0003\u0003\u007f\u0001RaZA\u001b\u0003\u0003\u0002B!a\u0011\u0002J5\u0011\u0011Q\t\u0006\u0004\u0003\u000fr\u0015aC3yaJ,7o]5p]NLA!a\u0013\u0002F\tI\u0011\t\u001e;sS\n,H/Z\u0001\u0012a\u0006\u0014H/\u001b;j_:\u001cu\u000e\\;n]N\u0004\u0013A\u00032vG.,Go\u00159fGV\u0011\u00111\u000b\t\u0006C\u0006M\u0011Q\u000b\t\u0004k\u0006]\u0013bAA-m\nQ!)^2lKR\u001c\u0006/Z2\u0002\u0017\t,8m[3u'B,7\rI\u0001\b_B$\u0018n\u001c8t+\t\t\t\u0007E\u0004\u007f\u0003\u000b\tY!a\u0003\u0002\u0011=\u0004H/[8og\u0002\n!BZ5mK\u001a{'/\\1u+\t\tI\u0007E\u0002X\u0003WJ1!!\u001cY\u0005)1\u0015\u000e\\3G_Jl\u0017\r^\u0001\fM&dWMR8s[\u0006$\b%A\u0006iSZ,G+\u001c9QCRDWCAA;!\r\u0019\u0016qO\u0005\u0004\u0003sR$\u0001\u0004%jm\u0016$V-\u001c9QCRD\u0017\u0001\u00045jm\u0016$V\u000e\u001d)bi\"\u0004\u0003f\u0001\f\u0002\u0000A\u0019\u0011-!!\n\u0007\u0005\r%MA\u0005ue\u0006t7/[3oi\u00061A(\u001b8jiz\"\u0002$!#\u0002\f\u00065\u0015qRAI\u0003'\u000b)*a&\u0002\u001a\u0006m\u0015QTAP!\t\u0019\u0006\u0001C\u0003s/\u0001\u0007A\u000fC\u0003|/\u0001\u0007Q\u0010\u0003\u0004\u0002\u001a]\u0001\r\u0001\u0013\u0005\b\u0003?9\u0002\u0019AA\u0012\u0011\u001d\tYc\u0006a\u0001\u0003GAq!a\f\u0018\u0001\u0004\t\u0019\u0004C\u0004\u0002<]\u0001\r!a\u0010\t\u000f\u0005=s\u00031\u0001\u0002T!9\u0011QL\fA\u0002\u0005\u0005\u0004bBA3/\u0001\u0007\u0011\u0011\u000e\u0005\b\u0003c:\u0002\u0019AA;\u0003A\u0019H/\u0019;jGB\u000b'\u000f^5uS>t7/\u0006\u0002\u0002&B!\u0011qUAc\u001d\u0011\tI+!1\u000f\t\u0005-\u0016q\u0018\b\u0005\u0003[\u000biL\u0004\u0003\u00020\u0006mf\u0002BAY\u0003ssA!a-\u00028:\u0019\u0011.!.\n\u0003\u0015K!a\u0011#\n\u0005\u0005\u0013\u0015BA A\u0013\tye(\u0003\u0002x\u001d&\u0019\u00111\u0019<\u0002\u0019\r\u000bG/\u00197pORK\b/Z:\n\t\u0005\u001d\u0017\u0011\u001a\u0002\u0013)\u0006\u0014G.\u001a)beRLG/[8o'B,7MC\u0002\u0002DZ\f\u0001C]3rk&\u0014X\rZ(sI\u0016\u0014\u0018N\\4\u0016\u0005\u0005=\u0007#B4\u00026\u0005E\u0007\u0003BA\"\u0003'LA!!6\u0002F\tI1k\u001c:u\u001fJ$WM]\u0001\u0004eVtGCBAn\u0003K\f)\u0010E\u0003h\u0003k\ti\u000e\u0005\u0003\u0002`\u0006\u0005X\"\u0001 \n\u0007\u0005\rhHA\u0002S_^Dq!a:\u001b\u0001\u0004\tI/\u0001\u0007ta\u0006\u00148nU3tg&|g\u000e\u0005\u0003\u0002l\u0006EXBAAw\u0015\r\tyOP\u0001\bG2\f7o]5d\u0013\u0011\t\u00190!<\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\t\u000f\u0005](\u00041\u0001\u0002z\u0006)1\r[5mIB!\u00111`A\u007f\u001b\u0005Q\u0016bAA\u00005\nI1\u000b]1sWBc\u0017M\\\u0001\u000eaJ|7-Z:t\u0013:\u001cXM\u001d;\u0015\u0019\t\u0015!1\u0002B\u0007\u0005/\u0011YCa\u000f\u0011\u0007\u0005\u00149!C\u0002\u0003\n\t\u0014A!\u00168ji\"9\u0011q]\u000eA\u0002\u0005%\bb\u0002B\b7\u0001\u0007!\u0011C\u0001\u0010Kb$XM\u001d8bY\u000e\u000bG/\u00197pOB\u0019QOa\u0005\n\u0007\tUaOA\bFqR,'O\\1m\u0007\u0006$\u0018\r\\8h\u0011\u001d\u0011Ib\u0007a\u0001\u00057\t!\u0002[1e_>\u00048i\u001c8g!\u0011\u0011iBa\n\u000e\u0005\t}!\u0002\u0002B\u0011\u0005G\tAaY8oM*\u0019!Q\u0005\"\u0002\r!\fGm\\8q\u0013\u0011\u0011ICa\b\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0011\u001d\u0011ic\u0007a\u0001\u0005_\t1\u0002^7q\u0019>\u001c\u0017\r^5p]B!!\u0011\u0007B\u001c\u001b\t\u0011\u0019D\u0003\u0003\u00036\t\r\u0012A\u00014t\u0013\u0011\u0011IDa\r\u0003\tA\u000bG\u000f\u001b\u0005\b\u0003o\\\u0002\u0019AA}\u0003Q9\u0018\u000e\u001e5OK^\u001c\u0005.\u001b7e\u0013:$XM\u001d8bYR!\u0011\u0011\u0012B!\u0011\u0019\u0011\u0019\u0005\ba\u0001\u0011\u0006Aa.Z<DQ&dG-\u0001\u0003d_BLH\u0003GAE\u0005\u0013\u0012YE!\u0014\u0003P\tE#1\u000bB+\u0005/\u0012IFa\u0017\u0003^!9!/\bI\u0001\u0002\u0004!\bbB>\u001e!\u0003\u0005\r! \u0005\t\u00033i\u0002\u0013!a\u0001\u0011\"I\u0011qD\u000f\u0011\u0002\u0003\u0007\u00111\u0005\u0005\n\u0003Wi\u0002\u0013!a\u0001\u0003GA\u0011\"a\f\u001e!\u0003\u0005\r!a\r\t\u0013\u0005mR\u0004%AA\u0002\u0005}\u0002\"CA(;A\u0005\t\u0019AA*\u0011%\ti&\bI\u0001\u0002\u0004\t\t\u0007C\u0005\u0002fu\u0001\n\u00111\u0001\u0002j!I\u0011\u0011O\u000f\u0011\u0002\u0003\u0007\u0011QO\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011\u0019GK\u0002u\u0005KZ#Aa\u001a\u0011\t\t%$1O\u0007\u0003\u0005WRAA!\u001c\u0003p\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005c\u0012\u0017AC1o]>$\u0018\r^5p]&!!Q\u000fB6\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011YHK\u0002~\u0005K\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0003\u0002*\u001a\u0001J!\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011!q\u0011\u0016\u0005\u0003G\u0011)'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%mU\u0011!q\u0012\u0016\u0005\u0003g\u0011)'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\tU%\u0006BA \u0005K\nabY8qs\u0012\"WMZ1vYR$\u0003(\u0006\u0002\u0003\u001c*\"\u00111\u000bB3\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIe*\"A!)+\t\u0005\u0005$QM\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132aU\u0011!q\u0015\u0016\u0005\u0003S\u0012)'A\bd_BLH\u0005Z3gCVdG\u000fJ\u00192+\t\u0011iK\u000b\u0003\u0002v\t\u0015\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u00034B!!Q\u0017B`\u001b\t\u00119L\u0003\u0003\u0003:\nm\u0016\u0001\u00027b]\u001eT!A!0\u0002\t)\fg/Y\u0005\u0005\u0003\u001f\u00119,\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0003FB\u0019\u0011Ma2\n\u0007\t%'MA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0003P\nU\u0007cA1\u0003R&\u0019!1\u001b2\u0003\u0007\u0005s\u0017\u0010C\u0005\u0003X.\n\t\u00111\u0001\u0003F\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!8\u0011\r\t}'Q\u001dBh\u001b\t\u0011\tOC\u0002\u0003d\n\f!bY8mY\u0016\u001cG/[8o\u0013\u0011\u00119O!9\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003G\u0011i\u000fC\u0005\u0003X6\n\t\u00111\u0001\u0003P\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011\u0019La=\t\u0013\t]g&!AA\u0002\t\u0015\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002$\te\b\"\u0003Bl_\u0005\u0005\t\u0019\u0001Bh\u0003MIen]3si&sGo\u001c%jm\u0016$\u0016M\u00197f!\t\u0019\u0016g\u0005\u00042\u0007\u0003i6q\u0001\t\u0004C\u000e\r\u0011bAB\u0003E\n1\u0011I\\=SK\u001a\u0004Ba!\u0003\u0004\u00105\u001111\u0002\u0006\u0005\u0007\u001b\u0011Y,\u0001\u0002j_&\u0019\u0001oa\u0003\u0015\u0005\tu\u0018a\u0002\"Z?\u000e#\u0016iU\u000b\u0003\u0007/\u0001ba!\u0007\u0004 \t\u0015QBAB\u000e\u0015\r\u0019iBT\u0001\u0006iJ,Wm]\u0005\u0005\u0007C\u0019YBA\u0006Ue\u0016,gj\u001c3f)\u0006<\u0017\u0001\u0003\"Z?\u000e#\u0016i\u0015\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u001d\u0005%5\u0011FB\u0016\u0007[\u0019yc!\r\u00044!)!/\u000ea\u0001i\")10\u000ea\u0001{\"1\u0011\u0011D\u001bA\u0002!Cq!a\b6\u0001\u0004\t\u0019\u0003C\u0004\u0002,U\u0002\r!a\t\t\u000f\u0005=R\u00071\u0001\u00024QA\u0012\u0011RB\u001c\u0007s\u0019Yd!\u0010\u0004@\r\u000531IB#\u0007\u000f\u001aIea\u0013\t\u000bI4\u0004\u0019\u0001;\t\u000bm4\u0004\u0019A?\t\r\u0005ea\u00071\u0001I\u0011\u001d\tyB\u000ea\u0001\u0003GAq!a\u000b7\u0001\u0004\t\u0019\u0003C\u0004\u00020Y\u0002\r!a\r\t\u000f\u0005mb\u00071\u0001\u0002@!9\u0011q\n\u001cA\u0002\u0005M\u0003bBA/m\u0001\u0007\u0011\u0011\r\u0005\b\u0003K2\u0004\u0019AA5\u0011\u001d\t\tH\u000ea\u0001\u0003k\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0004R\re\u0003#B1\u0002\u0014\rM\u0003CF1\u0004VQl\b*a\t\u0002$\u0005M\u0012qHA*\u0003C\nI'!\u001e\n\u0007\r]#MA\u0004UkBdW-M\u0019\t\u0013\rms'!AA\u0002\u0005%\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u00111\u0011\r\t\u0005\u0005k\u001b\u0019'\u0003\u0003\u0004f\t]&AB(cU\u0016\u001cG\u000f"
)
public class InsertIntoHiveTable extends LogicalPlan implements SaveAsHiveFile, V1WriteCommand, V1WritesHiveUtils, Serializable {
   private final CatalogTable table;
   private final Map partition;
   private final LogicalPlan query;
   private final boolean overwrite;
   private final boolean ifPartitionNotExists;
   private final Seq outputColumnNames;
   private final Seq partitionColumns;
   private final Option bucketSpec;
   private final Map options;
   private final FileFormat fileFormat;
   private final transient HiveTempPath hiveTmpPath;
   private Map metrics;
   private transient Seq children;
   private Seq nodePatterns;
   private volatile boolean bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public static Option unapply(final InsertIntoHiveTable x$0) {
      return InsertIntoHiveTable$.MODULE$.unapply(x$0);
   }

   public static TreeNodeTag BY_CTAS() {
      return InsertIntoHiveTable$.MODULE$.BY_CTAS();
   }

   public Map getPartitionSpec(final Map partition) {
      return V1WritesHiveUtils.getPartitionSpec$(this, partition);
   }

   public Seq getDynamicPartitionColumns(final CatalogTable table, final Map partition, final LogicalPlan query) {
      return V1WritesHiveUtils.getDynamicPartitionColumns$(this, table, partition, query);
   }

   public Map getOptionsWithHiveBucketWrite(final Option bucketSpec) {
      return V1WritesHiveUtils.getOptionsWithHiveBucketWrite$(this, bucketSpec);
   }

   public void setupHadoopConfForCompression(final FileSinkDesc fileSinkConf, final Configuration hadoopConf, final SparkSession sparkSession) {
      V1WritesHiveUtils.setupHadoopConfForCompression$(this, fileSinkConf, hadoopConf, sparkSession);
   }

   public Set saveAsHiveFile(final org.apache.spark.sql.classic.SparkSession sparkSession, final SparkPlan plan, final Configuration hadoopConf, final FileFormat fileFormat, final String outputLocation, final Map customPartitionLocations, final Seq partitionAttributes, final Option bucketSpec, final Map options) {
      return SaveAsHiveFile.saveAsHiveFile$(this, sparkSession, plan, hadoopConf, fileFormat, outputLocation, customPartitionLocations, partitionAttributes, bucketSpec, options);
   }

   public Map saveAsHiveFile$default$6() {
      return SaveAsHiveFile.saveAsHiveFile$default$6$(this);
   }

   public Seq saveAsHiveFile$default$7() {
      return SaveAsHiveFile.saveAsHiveFile$default$7$(this);
   }

   public Option saveAsHiveFile$default$8() {
      return SaveAsHiveFile.saveAsHiveFile$default$8$(this);
   }

   public Map saveAsHiveFile$default$9() {
      return SaveAsHiveFile.saveAsHiveFile$default$9$(this);
   }

   public final LogicalPlan child() {
      return DataWritingCommand.child$(this);
   }

   public Seq outputColumns() {
      return DataWritingCommand.outputColumns$(this);
   }

   public BasicWriteJobStatsTracker basicWriteJobStatsTracker(final Configuration hadoopConf) {
      return DataWritingCommand.basicWriteJobStatsTracker$(this, hadoopConf);
   }

   public LogicalPlan withCTEDefs(final Seq cteDefs) {
      return CTEInChildren.withCTEDefs$(this, cteDefs);
   }

   public final TreeNode mapChildren(final Function1 f) {
      return UnaryLike.mapChildren$(this, f);
   }

   public final TreeNode withNewChildrenInternal(final IndexedSeq newChildren) {
      return UnaryLike.withNewChildrenInternal$(this, newChildren);
   }

   public Seq output() {
      return Command.output$(this);
   }

   public AttributeSet producedAttributes() {
      return Command.producedAttributes$(this);
   }

   public Statistics stats() {
      return Command.stats$(this);
   }

   private Map metrics$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.metrics = DataWritingCommand.metrics$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.metrics;
   }

   public Map metrics() {
      return !this.bitmap$0 ? this.metrics$lzycompute() : this.metrics;
   }

   private Seq children$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.children = UnaryLike.children$(this);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.children;
   }

   public final Seq children() {
      return !this.bitmap$trans$0 ? this.children$lzycompute() : this.children;
   }

   public final Seq nodePatterns() {
      return this.nodePatterns;
   }

   public final void org$apache$spark$sql$catalyst$plans$logical$Command$_setter_$nodePatterns_$eq(final Seq x$1) {
      this.nodePatterns = x$1;
   }

   public CatalogTable table() {
      return this.table;
   }

   public Map partition() {
      return this.partition;
   }

   public LogicalPlan query() {
      return this.query;
   }

   public boolean overwrite() {
      return this.overwrite;
   }

   public boolean ifPartitionNotExists() {
      return this.ifPartitionNotExists;
   }

   public Seq outputColumnNames() {
      return this.outputColumnNames;
   }

   public Seq partitionColumns() {
      return this.partitionColumns;
   }

   public Option bucketSpec() {
      return this.bucketSpec;
   }

   public Map options() {
      return this.options;
   }

   public FileFormat fileFormat() {
      return this.fileFormat;
   }

   public HiveTempPath hiveTmpPath() {
      return this.hiveTmpPath;
   }

   public Map staticPartitions() {
      return (Map)((MapOps)this.partition().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$staticPartitions$1(x$1)))).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            Option v = (Option)x0$1._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k), v.get());
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Seq requiredOrdering() {
      return org.apache.spark.sql.execution.datasources.V1WritesUtils..MODULE$.getSortOrder(this.outputColumns(), this.partitionColumns(), this.bucketSpec(), this.options(), org.apache.spark.sql.execution.datasources.V1WritesUtils..MODULE$.getSortOrder$default$5());
   }

   public Seq run(final org.apache.spark.sql.classic.SparkSession sparkSession, final SparkPlan child) {
      ExternalCatalogWithListener externalCatalog = sparkSession.sharedState().externalCatalog();
      Configuration hadoopConf = this.hiveTmpPath().hadoopConf();
      Path tmpLocation = this.hiveTmpPath().externalTempPath();
      this.hiveTmpPath().createTmpPath();

      try {
         this.processInsert(sparkSession, externalCatalog, hadoopConf, tmpLocation, child);
      } finally {
         this.hiveTmpPath().deleteTmpPath();
      }

      org.apache.spark.sql.execution.command.CommandUtils..MODULE$.uncacheTableOrView(sparkSession, this.table().identifier());
      sparkSession.sessionState().catalog().refreshTable(this.table().identifier());
      org.apache.spark.sql.execution.command.CommandUtils..MODULE$.updateTableStats(sparkSession, this.table());
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   private void processInsert(final org.apache.spark.sql.classic.SparkSession sparkSession, final ExternalCatalog externalCatalog, final Configuration hadoopConf, final Path tmpLocation, final SparkPlan child) {
      int numDynamicPartitions = this.partition().values().count((x$2) -> BoxesRunTime.boxToBoolean($anonfun$processInsert$1(x$2)));
      Map partitionSpec = this.getPartitionSpec(this.partition());
      FileFormat x$4 = this.fileFormat();
      String x$5 = tmpLocation.toString();
      Seq x$6 = this.partitionColumns();
      Option x$7 = this.bucketSpec();
      Map x$8 = this.options();
      Map x$9 = this.saveAsHiveFile$default$6();
      Set writtenParts = this.saveAsHiveFile(sparkSession, child, hadoopConf, x$4, x$5, x$9, x$6, x$7, x$8);
      if (this.partition().nonEmpty()) {
         if (numDynamicPartitions <= 0) {
            Option oldPart = externalCatalog.getPartitionOption(this.table().database(), this.table().identifier().table(), partitionSpec);
            if (oldPart.isEmpty() || !this.ifPartitionNotExists()) {
               boolean inheritTableSpecs = true;
               externalCatalog.loadPartition(this.table().database(), this.table().identifier().table(), tmpLocation.toString(), partitionSpec, this.overwrite(), inheritTableSpecs, false);
            }
         } else {
            if (this.overwrite()) {
               label37: {
                  CatalogTableType var10000 = this.table().tableType();
                  CatalogTableType var18 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.EXTERNAL();
                  if (var10000 == null) {
                     if (var18 != null) {
                        break label37;
                     }
                  } else if (!var10000.equals(var18)) {
                     break label37;
                  }

                  int numWrittenParts = writtenParts.size();
                  String maxDynamicPartitionsKey = ConfVars.DYNAMICPARTITIONMAXPARTS.varname;
                  int maxDynamicPartitions = hadoopConf.getInt(maxDynamicPartitionsKey, ConfVars.DYNAMICPARTITIONMAXPARTS.defaultIntVal);
                  if (numWrittenParts > maxDynamicPartitions) {
                     throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.writePartitionExceedConfigSizeWhenDynamicPartitionError(numWrittenParts, maxDynamicPartitions, maxDynamicPartitionsKey);
                  }

                  writtenParts.foreach((partPath) -> {
                     $anonfun$processInsert$2(this, hadoopConf, partPath);
                     return BoxedUnit.UNIT;
                  });
               }
            }

            externalCatalog.loadDynamicPartitions(this.table().database(), this.table().identifier().table(), tmpLocation.toString(), partitionSpec, this.overwrite(), numDynamicPartitions);
         }
      } else {
         externalCatalog.loadTable(this.table().database(), this.table().identifier().table(), tmpLocation.toString(), this.overwrite(), false);
      }
   }

   public InsertIntoHiveTable withNewChildInternal(final LogicalPlan newChild) {
      CatalogTable x$2 = this.copy$default$1();
      Map x$3 = this.copy$default$2();
      boolean x$4 = this.copy$default$4();
      boolean x$5 = this.copy$default$5();
      Seq x$6 = this.copy$default$6();
      Seq x$7 = this.copy$default$7();
      Option x$8 = this.copy$default$8();
      Map x$9 = this.copy$default$9();
      FileFormat x$10 = this.copy$default$10();
      HiveTempPath x$11 = this.copy$default$11();
      return this.copy(x$2, x$3, newChild, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11);
   }

   public InsertIntoHiveTable copy(final CatalogTable table, final Map partition, final LogicalPlan query, final boolean overwrite, final boolean ifPartitionNotExists, final Seq outputColumnNames, final Seq partitionColumns, final Option bucketSpec, final Map options, final FileFormat fileFormat, final HiveTempPath hiveTmpPath) {
      return new InsertIntoHiveTable(table, partition, query, overwrite, ifPartitionNotExists, outputColumnNames, partitionColumns, bucketSpec, options, fileFormat, hiveTmpPath);
   }

   public CatalogTable copy$default$1() {
      return this.table();
   }

   public FileFormat copy$default$10() {
      return this.fileFormat();
   }

   public HiveTempPath copy$default$11() {
      return this.hiveTmpPath();
   }

   public Map copy$default$2() {
      return this.partition();
   }

   public LogicalPlan copy$default$3() {
      return this.query();
   }

   public boolean copy$default$4() {
      return this.overwrite();
   }

   public boolean copy$default$5() {
      return this.ifPartitionNotExists();
   }

   public Seq copy$default$6() {
      return this.outputColumnNames();
   }

   public Seq copy$default$7() {
      return this.partitionColumns();
   }

   public Option copy$default$8() {
      return this.bucketSpec();
   }

   public Map copy$default$9() {
      return this.options();
   }

   public String productPrefix() {
      return "InsertIntoHiveTable";
   }

   public int productArity() {
      return 11;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.table();
         }
         case 1 -> {
            return this.partition();
         }
         case 2 -> {
            return this.query();
         }
         case 3 -> {
            return BoxesRunTime.boxToBoolean(this.overwrite());
         }
         case 4 -> {
            return BoxesRunTime.boxToBoolean(this.ifPartitionNotExists());
         }
         case 5 -> {
            return this.outputColumnNames();
         }
         case 6 -> {
            return this.partitionColumns();
         }
         case 7 -> {
            return this.bucketSpec();
         }
         case 8 -> {
            return this.options();
         }
         case 9 -> {
            return this.fileFormat();
         }
         case 10 -> {
            return this.hiveTmpPath();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof InsertIntoHiveTable;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "table";
         }
         case 1 -> {
            return "partition";
         }
         case 2 -> {
            return "query";
         }
         case 3 -> {
            return "overwrite";
         }
         case 4 -> {
            return "ifPartitionNotExists";
         }
         case 5 -> {
            return "outputColumnNames";
         }
         case 6 -> {
            return "partitionColumns";
         }
         case 7 -> {
            return "bucketSpec";
         }
         case 8 -> {
            return "options";
         }
         case 9 -> {
            return "fileFormat";
         }
         case 10 -> {
            return "hiveTmpPath";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public boolean equals(final Object x$1) {
      boolean var22;
      if (this != x$1) {
         label119: {
            if (x$1 instanceof InsertIntoHiveTable) {
               InsertIntoHiveTable var4 = (InsertIntoHiveTable)x$1;
               if (this.overwrite() == var4.overwrite() && this.ifPartitionNotExists() == var4.ifPartitionNotExists()) {
                  label112: {
                     CatalogTable var10000 = this.table();
                     CatalogTable var5 = var4.table();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label112;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label112;
                     }

                     Map var14 = this.partition();
                     Map var6 = var4.partition();
                     if (var14 == null) {
                        if (var6 != null) {
                           break label112;
                        }
                     } else if (!var14.equals(var6)) {
                        break label112;
                     }

                     LogicalPlan var15 = this.query();
                     LogicalPlan var7 = var4.query();
                     if (var15 == null) {
                        if (var7 != null) {
                           break label112;
                        }
                     } else if (!var15.equals(var7)) {
                        break label112;
                     }

                     Seq var16 = this.outputColumnNames();
                     Seq var8 = var4.outputColumnNames();
                     if (var16 == null) {
                        if (var8 != null) {
                           break label112;
                        }
                     } else if (!var16.equals(var8)) {
                        break label112;
                     }

                     var16 = this.partitionColumns();
                     Seq var9 = var4.partitionColumns();
                     if (var16 == null) {
                        if (var9 != null) {
                           break label112;
                        }
                     } else if (!var16.equals(var9)) {
                        break label112;
                     }

                     Option var18 = this.bucketSpec();
                     Option var10 = var4.bucketSpec();
                     if (var18 == null) {
                        if (var10 != null) {
                           break label112;
                        }
                     } else if (!var18.equals(var10)) {
                        break label112;
                     }

                     Map var19 = this.options();
                     Map var11 = var4.options();
                     if (var19 == null) {
                        if (var11 != null) {
                           break label112;
                        }
                     } else if (!var19.equals(var11)) {
                        break label112;
                     }

                     FileFormat var20 = this.fileFormat();
                     FileFormat var12 = var4.fileFormat();
                     if (var20 == null) {
                        if (var12 != null) {
                           break label112;
                        }
                     } else if (!var20.equals(var12)) {
                        break label112;
                     }

                     HiveTempPath var21 = this.hiveTmpPath();
                     HiveTempPath var13 = var4.hiveTmpPath();
                     if (var21 == null) {
                        if (var13 != null) {
                           break label112;
                        }
                     } else if (!var21.equals(var13)) {
                        break label112;
                     }

                     if (var4.canEqual(this)) {
                        break label119;
                     }
                  }
               }
            }

            var22 = false;
            return var22;
         }
      }

      var22 = true;
      return var22;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$staticPartitions$1(final Tuple2 x$1) {
      return ((Option)x$1._2()).nonEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$processInsert$1(final Option x$2) {
      return x$2.isEmpty();
   }

   // $FF: synthetic method
   public static final void $anonfun$processInsert$2(final InsertIntoHiveTable $this, final Configuration hadoopConf$1, final String partPath) {
      Map dpMap = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])partPath.split("/")), (part) -> {
         String[] splitPart = part.split("=");
         scala.Predef..MODULE$.assert(scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])splitPart)) == 2, () -> "Invalid written partition path: " + part);
         return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.unescapePathName(splitPart[0])), org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.unescapePathName(splitPart[1]));
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      CaseInsensitiveMap caseInsensitiveDpMap = org.apache.spark.sql.catalyst.util.CaseInsensitiveMap..MODULE$.apply(dpMap);
      Map updatedPartitionSpec = (Map)$this.partition().map((x0$1) -> {
         if (x0$1 != null) {
            String key = (String)x0$1._1();
            Option var5 = (Option)x0$1._2();
            if (var5 instanceof Some) {
               Some var6 = (Some)var5;
               String var7 = (String)var6.value();
               if (var7 == null) {
                  return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.DEFAULT_PARTITION_NAME());
               }
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            Option var9 = (Option)x0$1._2();
            if (var9 instanceof Some) {
               Some var10 = (Some)var9;
               String value = (String)var10.value();
               return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), value);
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            Option var13 = (Option)x0$1._2();
            if (scala.None..MODULE$.equals(var13) && caseInsensitiveDpMap.contains(key)) {
               return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), caseInsensitiveDpMap.apply(key));
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.dynamicPartitionKeyNotAmongWrittenPartitionPathsError(key);
         } else {
            throw new MatchError(x0$1);
         }
      });
      Seq partitionColumnNames = $this.table().partitionColumnNames();
      Path tablePath = new Path($this.table().location());
      Path partitionPath = org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.generatePartitionPath(updatedPartitionSpec, partitionColumnNames, tablePath);
      FileSystem fs = partitionPath.getFileSystem(hadoopConf$1);
      if (fs.exists(partitionPath)) {
         if (!fs.delete(partitionPath, true)) {
            throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.cannotRemovePartitionDirError(partitionPath);
         }
      }
   }

   public InsertIntoHiveTable(final CatalogTable table, final Map partition, final LogicalPlan query, final boolean overwrite, final boolean ifPartitionNotExists, final Seq outputColumnNames, final Seq partitionColumns, final Option bucketSpec, final Map options, final FileFormat fileFormat, final HiveTempPath hiveTmpPath) {
      this.table = table;
      this.partition = partition;
      this.query = query;
      this.overwrite = overwrite;
      this.ifPartitionNotExists = ifPartitionNotExists;
      this.outputColumnNames = outputColumnNames;
      this.partitionColumns = partitionColumns;
      this.bucketSpec = bucketSpec;
      this.options = options;
      this.fileFormat = fileFormat;
      this.hiveTmpPath = hiveTmpPath;
      Command.$init$(this);
      UnaryLike.$init$(this);
      CTEInChildren.$init$(this);
      DataWritingCommand.$init$(this);
      SaveAsHiveFile.$init$(this);
      V1WritesHiveUtils.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
