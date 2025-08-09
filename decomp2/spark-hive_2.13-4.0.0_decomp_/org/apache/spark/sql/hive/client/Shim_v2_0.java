package org.apache.spark.sql.hive.client;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.PartitionDropOptions;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.FunctionType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.ResourceType;
import org.apache.hadoop.hive.metastore.api.ResourceUri;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.AddPartitionDesc;
import org.apache.hadoop.hive.ql.processors.CommandProcessor;
import org.apache.hadoop.hive.ql.processors.CommandProcessorFactory;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.catalyst.FunctionIdentifier;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.analysis.NoSuchPermanentFunctionException;
import org.apache.spark.sql.catalyst.catalog.CatalogFunction;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTablePartition;
import org.apache.spark.sql.catalyst.catalog.FunctionResource;
import org.apache.spark.sql.catalyst.expressions.And;
import org.apache.spark.sql.catalyst.expressions.Attribute;
import org.apache.spark.sql.catalyst.expressions.AttributeReference;
import org.apache.spark.sql.catalyst.expressions.BasePredicate;
import org.apache.spark.sql.catalyst.expressions.BinaryComparison;
import org.apache.spark.sql.catalyst.expressions.Cast;
import org.apache.spark.sql.catalyst.expressions.Contains;
import org.apache.spark.sql.catalyst.expressions.EndsWith;
import org.apache.spark.sql.catalyst.expressions.EqualNullSafe;
import org.apache.spark.sql.catalyst.expressions.EqualTo;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.GreaterThanOrEqual;
import org.apache.spark.sql.catalyst.expressions.In;
import org.apache.spark.sql.catalyst.expressions.InSet;
import org.apache.spark.sql.catalyst.expressions.LessThanOrEqual;
import org.apache.spark.sql.catalyst.expressions.Literal;
import org.apache.spark.sql.catalyst.expressions.Not;
import org.apache.spark.sql.catalyst.expressions.Or;
import org.apache.spark.sql.catalyst.expressions.StartsWith;
import org.apache.spark.sql.catalyst.expressions.TimeZoneAwareExpression;
import org.apache.spark.sql.catalyst.util.DateFormatter;
import org.apache.spark.sql.types.AtomicType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DateType;
import org.apache.spark.sql.types.IntegralType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.unsafe.types.UTF8String;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.StringOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011%e!\u0002!B\u0001\u0005k\u0005\"\u0002-\u0001\t\u0003Q\u0006\u0002\u0003/\u0001\u0011\u000b\u0007I\u0011C/\t\u0011\u0019\u0004\u0001R1A\u0005\u0012uC\u0001b\u001a\u0001\t\u0006\u0004%\t\"\u0018\u0005\tQ\u0002A)\u0019!C\t;\"A\u0011\u000e\u0001EC\u0002\u0013E!\u000e\u0003\u0005o\u0001!\u0015\r\u0011\"\u0005p\u0011\u0015i\b\u0001\"\u0011\u007f\u0011)\t\u0019\u0003\u0001EC\u0002\u0013%\u0011Q\u0005\u0005\u000b\u0003g\u0001\u0001R1A\u0005\n\u0005\u0015\u0002BCA\u001b\u0001!\u0015\r\u0011\"\u0003\u0002&!Q\u0011q\u0007\u0001\t\u0006\u0004%I!!\n\t\u0015\u0005e\u0002\u0001#b\u0001\n\u0013\t)\u0003C\u0004\u0002<\u0001!\t%!\u0010\t\u000f\u0005]\u0003\u0001\"\u0011\u0002Z!9\u00111\u000e\u0001\u0005B\u00055\u0004bBA;\u0001\u0011\u0005\u0013q\u000f\u0005\b\u0003W\u0003A\u0011IAW\u0011\u001d\tY\f\u0001C!\u0003{Cq!a8\u0001\t\u0013\t\t\u000fC\u0004\u0002x\u0002!\t%!?\t\u000f\te\u0001\u0001\"\u0011\u0003\u001c!9!1\u0006\u0001\u0005B\t5\u0002b\u0002B\u001b\u0001\u0011\u0005#q\u0007\u0005\b\u0005\u001b\u0002A\u0011\tB(\u0011\u001d\u0011y\b\u0001C!\u0005\u0003CqA!$\u0001\t\u0003\u0012y\tC\u0004\u0003*\u0002!\tEa+\t\u000f\t]\u0006\u0001\"\u0011\u0003:\"9!Q\u001a\u0001\u0005B\t=\u0007b\u0002Bl\u0001\u0011\u0005#\u0011\u001c\u0005\b\u0005S\u0004A\u0011\tBv\u0011\u001d\u0011i\u0010\u0001C\u0005\u0005\u007fDqaa\u0007\u0001\t\u0003\u001ai\u0002C\u0004\u0004(\u0001!\te!\u000b\t\u000f\rM\u0002\u0001\"\u0011\u00046!911\t\u0001\u0005B\r\u0015\u0003bBB'\u0001\u0011%1q\n\u0005\b\u0007+\u0002A\u0011IB,\u0011\u001d\u0019\t\u0007\u0001C\u0005\u0007GBqa!!\u0001\t\u0003\u001a\u0019\tC\u0004\u0004\f\u0002!\ta!$\t\u000f\rU\u0005\u0001\"\u0003\u0004\u0018\"91Q\u0014\u0001\u0005B\r}\u0005bBBU\u0001\u0011\u000531\u0016\u0005\b\u0007g\u0003A\u0011IB[\u0011\u001d\u0019i\f\u0001C!\u0007\u007fCqaa4\u0001\t\u0003\u001a\t\u000eC\u0004\u0004\\\u0002!\te!8\t\u000f\r\r\b\u0001\"\u0011\u0004f\"91\u0011\u001e\u0001\u0005B\r-\bbBBy\u0001\u0011\u000531\u001f\u0005\b\u0007s\u0004A\u0011IB~\u0011\u001d!)\u0001\u0001C!\t\u000fAq\u0001b\u0005\u0001\t\u0003\")\u0002C\u0004\u0005\u001e\u0001!\t\u0005b\b\t\u000f\t]\u0006\u0001\"\u0011\u0005&!9AQ\u0006\u0001\u0005B\u0011=\u0002b\u0002C\u001e\u0001\u0011\u0005CQ\b\u0005\b\t\u000b\u0002A\u0011\tC$\u0011\u001d!)\u0005\u0001C!\t3Bq\u0001\"\u001a\u0001\t\u0003\"9\u0007C\u0004\u0005v\u0001!\t\u0005b\u001e\u0003\u0013MC\u0017.\\0we}\u0003$B\u0001\"D\u0003\u0019\u0019G.[3oi*\u0011A)R\u0001\u0005Q&4XM\u0003\u0002G\u000f\u0006\u00191/\u001d7\u000b\u0005!K\u0015!B:qCJ\\'B\u0001&L\u0003\u0019\t\u0007/Y2iK*\tA*A\u0002pe\u001e\u001c2\u0001\u0001(S!\ty\u0005+D\u0001B\u0013\t\t\u0016I\u0001\u0003TQ&l\u0007CA*W\u001b\u0005!&BA+H\u0003!Ig\u000e^3s]\u0006d\u0017BA,U\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u00027B\u0011q\nA\u0001\u0016I\u0016dW\r^3ECR\f\u0017J\u001c#s_BLe\u000eZ3y+\u0005q\u0006CA0e\u001b\u0005\u0001'BA1c\u0003\u0011a\u0017M\\4\u000b\u0003\r\fAA[1wC&\u0011Q\r\u0019\u0002\b\u0005>|G.Z1o\u0003\u0019I7/Q2jI\u0006)\u0012n]*lK^,Gm\u0015;pe\u0016\f5oU;cI&\u0014\u0018!\u0007;ie><X\t_2faRLwN\\%o\tJ|\u0007/\u00138eKb\fA\u0004\u001e=o\u0013\u0012Le\u000eT8bI\u0012Kh.Y7jGB\u000b'\u000f^5uS>t7/F\u0001l!\tyF.\u0003\u0002nA\n!Aj\u001c8h\u0003!9\u0018\u000e\u001c3dCJ$W#\u00019\u0011\u0005EThB\u0001:y!\t\u0019h/D\u0001u\u0015\t)\u0018,\u0001\u0004=e>|GO\u0010\u0006\u0002o\u0006)1oY1mC&\u0011\u0011P^\u0001\u0007!J,G-\u001a4\n\u0005md(AB*ue&twM\u0003\u0002zm\u00061q-\u001a;N'\u000e#2a`A\t!\u0011\t\t!!\u0004\u000e\u0005\u0005\r!\u0002BA\u0003\u0003\u000f\t\u0011\"\\3uCN$xN]3\u000b\u0007\u0011\u000bIAC\u0002\u0002\f%\u000ba\u0001[1e_>\u0004\u0018\u0002BA\b\u0003\u0007\u0011\u0001#S'fi\u0006\u001cFo\u001c:f\u00072LWM\u001c;\t\r\u0011C\u0001\u0019AA\n!\u0011\t)\"a\b\u000e\u0005\u0005]!\u0002BA\r\u00037\t\u0001\"\\3uC\u0012\fG/\u0019\u0006\u0005\u0003;\t9!\u0001\u0002rY&!\u0011\u0011EA\f\u0005\u0011A\u0015N^3\u0002'1|\u0017\r\u001a)beRLG/[8o\u001b\u0016$\bn\u001c3\u0016\u0005\u0005\u001d\u0002\u0003BA\u0015\u0003_i!!a\u000b\u000b\u0007\u00055\u0002-A\u0004sK\u001adWm\u0019;\n\t\u0005E\u00121\u0006\u0002\u0007\u001b\u0016$\bn\u001c3\u0002\u001f1|\u0017\r\u001a+bE2,W*\u001a;i_\u0012\f1\u0004\\8bI\u0012Kh.Y7jGB\u000b'\u000f^5uS>t7/T3uQ>$\u0017\u0001E1mi\u0016\u0014H+\u00192mK6+G\u000f[8e\u0003U\tG\u000e^3s!\u0006\u0014H/\u001b;j_:\u001cX*\u001a;i_\u0012\fac]3u\u0007V\u0014(/\u001a8u'\u0016\u001c8/[8o'R\fG/\u001a\u000b\u0005\u0003\u007f\t9\u0005\u0005\u0003\u0002B\u0005\rS\"\u0001<\n\u0007\u0005\u0015cO\u0001\u0003V]&$\bbBA%\u001d\u0001\u0007\u00111J\u0001\u0006gR\fG/\u001a\t\u0005\u0003\u001b\n\u0019&\u0004\u0002\u0002P)!\u0011\u0011KA\u000e\u0003\u001d\u0019Xm]:j_:LA!!\u0016\u0002P\ta1+Z:tS>t7\u000b^1uK\u0006yq-\u001a;ECR\fGj\\2bi&|g\u000e\u0006\u0003\u0002\\\u0005\u0005\u0004#BA!\u0003;\u0002\u0018bAA0m\n1q\n\u001d;j_:Dq!a\u0019\u0010\u0001\u0004\t)'A\u0003uC\ndW\r\u0005\u0003\u0002\u0016\u0005\u001d\u0014\u0002BA5\u0003/\u0011Q\u0001V1cY\u0016\fqb]3u\t\u0006$\u0018\rT8dCRLwN\u001c\u000b\u0007\u0003\u007f\ty'!\u001d\t\u000f\u0005\r\u0004\u00031\u0001\u0002f!1\u00111\u000f\tA\u0002A\f1\u0001\\8d\u0003A\u0019'/Z1uKB\u000b'\u000f^5uS>t7\u000f\u0006\u0006\u0002@\u0005e\u00141PA?\u0003GCa\u0001R\tA\u0002\u0005M\u0001bBA2#\u0001\u0007\u0011Q\r\u0005\b\u0003\u007f\n\u0002\u0019AAA\u0003\u0015\u0001\u0018M\u001d;t!\u0019\t\u0019)!$\u0002\u0014:!\u0011QQAE\u001d\r\u0019\u0018qQ\u0005\u0002o&\u0019\u00111\u0012<\u0002\u000fA\f7m[1hK&!\u0011qRAI\u0005\r\u0019V-\u001d\u0006\u0004\u0003\u00173\b\u0003BAK\u0003?k!!a&\u000b\t\u0005e\u00151T\u0001\bG\u0006$\u0018\r\\8h\u0015\r\ti*R\u0001\tG\u0006$\u0018\r\\=ti&!\u0011\u0011UAL\u0005U\u0019\u0015\r^1m_\u001e$\u0016M\u00197f!\u0006\u0014H/\u001b;j_:Dq!!*\u0012\u0001\u0004\t9+\u0001\bjO:|'/Z%g\u000bbL7\u000f^:\u0011\t\u0005\u0005\u0013\u0011V\u0005\u0003KZ\f\u0001cZ3u\u00032d\u0007+\u0019:uSRLwN\\:\u0015\r\u0005=\u0016qWA]!\u0019\t\u0019)!$\u00022B!\u0011QCAZ\u0013\u0011\t),a\u0006\u0003\u0013A\u000b'\u000f^5uS>t\u0007B\u0002#\u0013\u0001\u0004\t\u0019\u0002C\u0004\u0002dI\u0001\r!!\u001a\u0002+\u001d,G\u000fU1si&$\u0018n\u001c8t\u0005f4\u0015\u000e\u001c;feRQ\u0011qVA`\u0003\u0003\f\u0019-!6\t\r\u0011\u001b\u0002\u0019AA\n\u0011\u001d\t\u0019g\u0005a\u0001\u0003KBq!!2\u0014\u0001\u0004\t9-\u0001\u0006qe\u0016$\u0017nY1uKN\u0004b!a!\u0002\u000e\u0006%\u0007\u0003BAf\u0003#l!!!4\u000b\t\u0005=\u00171T\u0001\fKb\u0004(/Z:tS>t7/\u0003\u0003\u0002T\u00065'AC#yaJ,7o]5p]\"9\u0011q[\nA\u0002\u0005e\u0017\u0001D2bi\u0006dwn\u001a+bE2,\u0007\u0003BAK\u00037LA!!8\u0002\u0018\na1)\u0019;bY><G+\u00192mK\u0006Y\u0002O];oKB\u000b'\u000f^5uS>t7OR1ti\u001a\u000bG\u000e\u001c2bG.$\"\"a9\u0002p\u0006E\u00181_A{!\u0019\t)/a;\u000226\u0011\u0011q\u001d\u0006\u0004\u0003S\u0014\u0017\u0001B;uS2LA!!<\u0002h\nQ1i\u001c7mK\u000e$\u0018n\u001c8\t\r\u0011#\u0002\u0019AA\n\u0011\u001d\t\u0019\u0007\u0006a\u0001\u0003KBq!a6\u0015\u0001\u0004\tI\u000eC\u0004\u0002FR\u0001\r!a2\u0002'\u001d,GoQ8n[\u0006tG\r\u0015:pG\u0016\u001c8o\u001c:\u0015\r\u0005m(q\u0001B\u0006!\u0011\tiPa\u0001\u000e\u0005\u0005}(\u0002\u0002B\u0001\u00037\t!\u0002\u001d:pG\u0016\u001c8o\u001c:t\u0013\u0011\u0011)!a@\u0003!\r{W.\\1oIB\u0013xnY3tg>\u0014\bB\u0002B\u0005+\u0001\u0007\u0001/A\u0003u_.,g\u000eC\u0004\u0003\u000eU\u0001\rAa\u0004\u0002\t\r|gN\u001a\t\u0005\u0005#\u0011)\"\u0004\u0002\u0003\u0014)!!QBA\u0004\u0013\u0011\u00119Ba\u0005\u0003\u0011!Kg/Z\"p]\u001a\f\u0001cZ3u\tJLg/\u001a:SKN,H\u000e^:\u0015\t\tu!q\u0004\t\u0006\u0003\u0007\u000bi\t\u001d\u0005\b\u0005C1\u0002\u0019\u0001B\u0012\u0003\u0019!'/\u001b<feB!!Q\u0005B\u0014\u001b\t\tY\"\u0003\u0003\u0003*\u0005m!A\u0002#sSZ,'/A\u0015hKRlU\r^1ti>\u0014Xm\u00117jK:$8i\u001c8oK\u000e$(+\u001a;ss\u0012+G.Y=NS2d\u0017n\u001d\u000b\u0005\u0005_\u0011\u0019\u0004\u0005\u0003\u0002B\tE\u0012BA7w\u0011\u001d\u0011ia\u0006a\u0001\u0005\u001f\tqbZ3u)\u0006\u0014G.Z:CsRK\b/\u001a\u000b\u000b\u0005;\u0011IDa\u000f\u0003@\t\r\u0003B\u0002#\u0019\u0001\u0004\t\u0019\u0002\u0003\u0004\u0003>a\u0001\r\u0001]\u0001\u0007I\nt\u0015-\\3\t\r\t\u0005\u0003\u00041\u0001q\u0003\u001d\u0001\u0018\r\u001e;fe:DqA!\u0012\u0019\u0001\u0004\u00119%A\u0005uC\ndW\rV=qKB!\u0011\u0011\u0001B%\u0013\u0011\u0011Y%a\u0001\u0003\u0013Q\u000b'\r\\3UsB,\u0017!\u00047pC\u0012\u0004\u0016M\u001d;ji&|g\u000e\u0006\n\u0002@\tE#1\u000bB2\u0005O\u0012\tH!\u001e\u0003z\tm\u0004B\u0002#\u001a\u0001\u0004\t\u0019\u0002C\u0004\u0003Ve\u0001\rAa\u0016\u0002\u00111|\u0017\r\u001a)bi\"\u0004BA!\u0017\u0003`5\u0011!1\f\u0006\u0005\u0005;\nI!\u0001\u0002gg&!!\u0011\rB.\u0005\u0011\u0001\u0016\r\u001e5\t\r\t\u0015\u0014\u00041\u0001q\u0003%!\u0018M\u00197f\u001d\u0006lW\rC\u0004\u0003je\u0001\rAa\u001b\u0002\u0011A\f'\u000f^*qK\u000e\u0004b!!:\u0003nA\u0004\u0018\u0002\u0002B8\u0003O\u00141!T1q\u0011\u001d\u0011\u0019(\u0007a\u0001\u0003O\u000bqA]3qY\u0006\u001cW\rC\u0004\u0003xe\u0001\r!a*\u0002#%t\u0007.\u001a:jiR\u000b'\r\\3Ta\u0016\u001c7\u000f\u0003\u0004h3\u0001\u0007\u0011q\u0015\u0005\b\u0005{J\u0002\u0019AAT\u0003)I7o\u0015:d\u0019>\u001c\u0017\r\\\u0001\nY>\fG\rV1cY\u0016$B\"a\u0010\u0003\u0004\n\u0015%q\u0011BE\u0005\u0017Ca\u0001\u0012\u000eA\u0002\u0005M\u0001b\u0002B+5\u0001\u0007!q\u000b\u0005\u0007\u0005KR\u0002\u0019\u00019\t\u000f\tM$\u00041\u0001\u0002(\"9!Q\u0010\u000eA\u0002\u0005\u001d\u0016!\u00067pC\u0012$\u0015P\\1nS\u000e\u0004\u0016M\u001d;ji&|gn\u001d\u000b\u0011\u0003\u007f\u0011\tJa%\u0003\u0016\n]%\u0011\u0014BN\u0005KCa\u0001R\u000eA\u0002\u0005M\u0001b\u0002B+7\u0001\u0007!q\u000b\u0005\u0007\u0005KZ\u0002\u0019\u00019\t\u000f\t%4\u00041\u0001\u0003l!9!1O\u000eA\u0002\u0005\u001d\u0006b\u0002BO7\u0001\u0007!qT\u0001\u0006]VlG\t\u0015\t\u0005\u0003\u0003\u0012\t+C\u0002\u0003$Z\u00141!\u00138u\u0011\u001d\u00119k\u0007a\u0001\u0003K\n\u0011\u0002[5wKR\u000b'\r\\3\u0002\u0013\u0011\u0014x\u000e]%oI\u0016DHCCA \u0005[\u0013yK!-\u00034\"1A\t\ba\u0001\u0003'AaA!\u0010\u001d\u0001\u0004\u0001\bB\u0002B39\u0001\u0007\u0001\u000f\u0003\u0004\u00036r\u0001\r\u0001]\u0001\nS:$W\r\u001f(b[\u0016\f\u0011\u0002\u001a:paR\u000b'\r\\3\u0015\u001d\u0005}\"1\u0018B_\u0005\u007f\u0013\tM!2\u0003J\"1A)\ba\u0001\u0003'AaA!\u0010\u001e\u0001\u0004\u0001\bB\u0002B3;\u0001\u0007\u0001\u000fC\u0004\u0003Dv\u0001\r!a*\u0002\u0015\u0011,G.\u001a;f\t\u0006$\u0018\rC\u0004\u0003Hv\u0001\r!a*\u0002#%<gn\u001c:f\u0013\u001atu\u000e^#ySN$8\u000fC\u0004\u0003Lv\u0001\r!a*\u0002\u000bA,(oZ3\u0002\u0015\u0005dG/\u001a:UC\ndW\r\u0006\u0005\u0002@\tE'1\u001bBk\u0011\u0019!e\u00041\u0001\u0002\u0014!1!Q\r\u0010A\u0002ADq!a\u0019\u001f\u0001\u0004\t)'A\bbYR,'\u000fU1si&$\u0018n\u001c8t)!\tyDa7\u0003^\n}\u0007B\u0002# \u0001\u0004\t\u0019\u0002\u0003\u0004\u0003f}\u0001\r\u0001\u001d\u0005\b\u0005C|\u0002\u0019\u0001Br\u0003!qWm\u001e)beR\u001c\bCBAs\u0005K\f\t,\u0003\u0003\u0003h\u0006\u001d(\u0001\u0002'jgR\fQ\u0002\u001a:paB\u000b'\u000f^5uS>tGCDA \u0005[\u0014yO!=\u0003t\ne(1 \u0005\u0007\t\u0002\u0002\r!a\u0005\t\r\tu\u0002\u00051\u0001q\u0011\u0019\u0011)\u0007\ta\u0001a\"9!Q\u001f\u0011A\u0002\t]\u0018\u0001\u00029beR\u0004R!!:\u0003fBDqAa1!\u0001\u0004\t9\u000bC\u0004\u0003L\u0002\u0002\r!a*\u0002\u001dQ|\u0007*\u001b<f\rVt7\r^5p]R11\u0011AB\u0007\u0007/\u0001Baa\u0001\u0004\n5\u00111Q\u0001\u0006\u0005\u0007\u000f\t\u0019!A\u0002ba&LAaa\u0003\u0004\u0006\tAa)\u001e8di&|g\u000eC\u0004\u0004\u0010\u0005\u0002\ra!\u0005\u0002\u0003\u0019\u0004B!!&\u0004\u0014%!1QCAL\u0005=\u0019\u0015\r^1m_\u001e4UO\\2uS>t\u0007BBB\rC\u0001\u0007\u0001/\u0001\u0002eE\u0006q1M]3bi\u00164UO\\2uS>tG\u0003CA \u0007?\u0019\tca\t\t\r\u0011\u0013\u0003\u0019AA\n\u0011\u0019\u0019IB\ta\u0001a\"91Q\u0005\u0012A\u0002\rE\u0011\u0001\u00024v]\u000e\fA\u0002\u001a:pa\u001a+hn\u0019;j_:$\u0002\"a\u0010\u0004,\r52q\u0006\u0005\u0007\t\u000e\u0002\r!a\u0005\t\r\re1\u00051\u0001q\u0011\u0019\u0019\td\ta\u0001a\u0006!a.Y7f\u00039\u0011XM\\1nK\u001a+hn\u0019;j_:$\"\"a\u0010\u00048\re21HB \u0011\u0019!E\u00051\u0001\u0002\u0014!11\u0011\u0004\u0013A\u0002ADaa!\u0010%\u0001\u0004\u0001\u0018aB8mI:\u000bW.\u001a\u0005\u0007\u0007\u0003\"\u0003\u0019\u00019\u0002\u000f9,wOT1nK\u0006i\u0011\r\u001c;fe\u001a+hn\u0019;j_:$\u0002\"a\u0010\u0004H\r%31\n\u0005\u0007\t\u0016\u0002\r!a\u0005\t\r\reQ\u00051\u0001q\u0011\u001d\u0019)#\na\u0001\u0007#\t\u0001C\u001a:p[\"Kg/\u001a$v]\u000e$\u0018n\u001c8\u0015\t\rE1\u0011\u000b\u0005\b\u0007'2\u0003\u0019AB\u0001\u0003\tAg-A\thKR4UO\\2uS>tw\n\u001d;j_:$\u0002b!\u0017\u0004\\\ru3q\f\t\u0007\u0003\u0003\nif!\u0005\t\r\u0011;\u0003\u0019AA\n\u0011\u0019\u0019Ib\na\u0001a\"11\u0011G\u0014A\u0002A\f!\"[:DCV\u001cX\r\u001a\"z)\u0019\t9k!\u001a\u0004p!91q\r\u0015A\u0002\r%\u0014!A3\u0011\t\u0005\r51N\u0005\u0005\u0007[\n\tJA\u0005UQJ|w/\u00192mK\"11\u0011\u000f\u0015A\u0002A\fA\"\\1uG\"l\u0015m]:bO\u0016D3\u0001KB;!\u0011\u00199h! \u000e\u0005\re$bAB>m\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\r}4\u0011\u0010\u0002\bi\u0006LGN]3d\u00035a\u0017n\u001d;Gk:\u001cG/[8ogRA!QDBC\u0007\u000f\u001bI\t\u0003\u0004ES\u0001\u0007\u00111\u0003\u0005\u0007\u00073I\u0003\u0019\u00019\t\r\t\u0005\u0013\u00061\u0001q\u00039\u0019wN\u001c<feR4\u0015\u000e\u001c;feN$R\u0001]BH\u0007#Cq!a\u0019+\u0001\u0004\t)\u0007C\u0004\u0004\u0014*\u0002\r!a2\u0002\u000f\u0019LG\u000e^3sg\u0006\u0011\u0012/^8uKN#(/\u001b8h\u0019&$XM]1m)\r\u00018\u0011\u0014\u0005\u0007\u00077[\u0003\u0019\u00019\u0002\u0007M$(/\u0001\u000bhKR$\u0015\r^1cCN,wj\u001e8fe:\u000bW.\u001a\u000b\u0004a\u000e\u0005\u0006bBB\rY\u0001\u000711\u0015\t\u0005\u0007\u0007\u0019)+\u0003\u0003\u0004(\u000e\u0015!\u0001\u0003#bi\u0006\u0014\u0017m]3\u0002)M,G\u000fR1uC\n\f7/Z(x]\u0016\u0014h*Y7f)\u0019\tyd!,\u00040\"91\u0011D\u0017A\u0002\r\r\u0006BBBY[\u0001\u0007\u0001/A\u0003po:,'/\u0001\bde\u0016\fG/\u001a#bi\u0006\u0014\u0017m]3\u0015\u0011\u0005}2qWB]\u0007wCa\u0001\u0012\u0018A\u0002\u0005M\u0001bBB\r]\u0001\u000711\u0015\u0005\b\u0003Ks\u0003\u0019AAT\u00031!'o\u001c9ECR\f'-Y:f)1\tyd!1\u0004D\u000e\u00157qYBf\u0011\u0019!u\u00061\u0001\u0002\u0014!1!QH\u0018A\u0002ADqAa10\u0001\u0004\t9\u000bC\u0004\u0004J>\u0002\r!a*\u0002\u001f%<gn\u001c:f+:\\gn\\<o\t\nDqa!40\u0001\u0004\t9+A\u0004dCN\u001c\u0017\rZ3\u0002\u001b\u0005dG/\u001a:ECR\f'-Y:f)!\tyda5\u0004V\u000e]\u0007B\u0002#1\u0001\u0004\t\u0019\u0002\u0003\u0004\u0003>A\u0002\r\u0001\u001d\u0005\b\u00073\u0004\u0004\u0019ABR\u0003\u0005!\u0017aC4fi\u0012\u000bG/\u00192bg\u0016$baa)\u0004`\u000e\u0005\bB\u0002#2\u0001\u0004\t\u0019\u0002\u0003\u0004\u0003>E\u0002\r\u0001]\u0001\u0010O\u0016$\u0018\t\u001c7ECR\f'-Y:fgR!!QDBt\u0011\u0019!%\u00071\u0001\u0002\u0014\u0005)r-\u001a;ECR\f'-Y:fg\nK\b+\u0019;uKJtGC\u0002B\u000f\u0007[\u001cy\u000f\u0003\u0004Eg\u0001\u0007\u00111\u0003\u0005\u0007\u0005\u0003\u001a\u0004\u0019\u00019\u0002\u001d\u0011\fG/\u00192bg\u0016,\u00050[:ugR1\u0011qUB{\u0007oDa\u0001\u0012\u001bA\u0002\u0005M\u0001B\u0002B\u001fi\u0001\u0007\u0001/A\u0006de\u0016\fG/\u001a+bE2,G\u0003CA \u0007{\u001cy\u0010\"\u0001\t\r\u0011+\u0004\u0019AA\n\u0011\u001d\t\u0019'\u000ea\u0001\u0003KBq\u0001b\u00016\u0001\u0004\t9+A\u0006jM:{G/\u0012=jgR\u001c\u0018\u0001C4fiR\u000b'\r\\3\u0015\u0015\u0005\u0015D\u0011\u0002C\u0006\t\u001b!y\u0001\u0003\u0004Em\u0001\u0007\u00111\u0003\u0005\u0007\u0005{1\u0004\u0019\u00019\t\r\t\u0015d\u00071\u0001q\u0011%!\tB\u000eI\u0001\u0002\u0004\t9+\u0001\buQJ|w/\u0012=dKB$\u0018n\u001c8\u0002%\u001d,G\u000fV1cY\u0016\u001c()\u001f)biR,'O\u001c\u000b\t\u0005;!9\u0002\"\u0007\u0005\u001c!1Ai\u000ea\u0001\u0003'AaA!\u00108\u0001\u0004\u0001\bB\u0002B!o\u0001\u0007\u0001/\u0001\u0007hKR\fE\u000e\u001c+bE2,7\u000f\u0006\u0004\u0003\u001e\u0011\u0005B1\u0005\u0005\u0007\tb\u0002\r!a\u0005\t\r\tu\u0002\b1\u0001q)!\ty\u0004b\n\u0005*\u0011-\u0002B\u0002#:\u0001\u0004\t\u0019\u0002\u0003\u0004\u0003>e\u0002\r\u0001\u001d\u0005\u0007\u0005KJ\u0004\u0019\u00019\u0002\u0019\u001d,G\u000fU1si&$\u0018n\u001c8\u0015\u0015\u0005EF\u0011\u0007C\u001a\tk!9\u0004\u0003\u0004Eu\u0001\u0007\u00111\u0003\u0005\b\u0003GR\u0004\u0019AA3\u0011\u001d\u0011IG\u000fa\u0001\u0005WBq\u0001\"\u000f;\u0001\u0004\t9+A\u0006g_J\u001cWm\u0011:fCR,\u0017!D4fiB\u000b'\u000f^5uS>t7\u000f\u0006\u0005\u00020\u0012}B\u0011\tC\"\u0011\u0019!5\b1\u0001\u0002\u0014!9\u00111M\u001eA\u0002\u0005\u0015\u0004b\u0002B5w\u0001\u0007!1N\u0001\u0012O\u0016$\b+\u0019:uSRLwN\u001c(b[\u0016\u001cHC\u0003B\u000f\t\u0013\"Y\u0005\"\u0014\u0005P!1A\t\u0010a\u0001\u0003'AaA!\u0010=\u0001\u0004\u0001\bB\u0002B3y\u0001\u0007\u0001\u000fC\u0004\u0005Rq\u0002\r\u0001b\u0015\u0002\u00075\f\u0007\u0010\u0005\u0003\u0002B\u0011U\u0013b\u0001C,m\n)1\u000b[8siRa!Q\u0004C.\t;\"y\u0006\"\u0019\u0005d!1A)\u0010a\u0001\u0003'AaA!\u0010>\u0001\u0004\u0001\bB\u0002B3{\u0001\u0007\u0001\u000fC\u0004\u0003ju\u0002\rAa\u001b\t\u000f\u0011ES\b1\u0001\u0005T\u0005y!/\u001a8b[\u0016\u0004\u0016M\u001d;ji&|g\u000e\u0006\u0006\u0002@\u0011%D1\u000eC7\tcBa\u0001\u0012 A\u0002\u0005M\u0001bBA2}\u0001\u0007\u0011Q\r\u0005\b\t_r\u0004\u0019\u0001B6\u0003-yG\u000e\u001a)beR\u001c\u0006/Z2\t\u000f\u0011Md\b1\u0001\u00022\u00069a.Z<QCJ$\u0018AC4fi&sG-\u001a=fgRQA\u0011\u0010CA\t\u0007#)\tb\"\u0011\r\u0005\r\u0015Q\u0012C>!\u0011\u0019\u0019\u0001\" \n\t\u0011}4Q\u0001\u0002\u0006\u0013:$W\r\u001f\u0005\u0007\t~\u0002\r!a\u0005\t\r\tur\b1\u0001q\u0011\u0019\u0011)g\u0010a\u0001a\"9A\u0011K A\u0002\u0011M\u0003"
)
public class Shim_v2_0 extends Shim implements Logging {
   private Boolean deleteDataInDropIndex;
   private Boolean isAcid;
   private Boolean isSkewedStoreAsSubdir;
   private Boolean throwExceptionInDropIndex;
   private Long txnIdInLoadDynamicPartitions;
   private String wildcard;
   private Method loadPartitionMethod;
   private Method loadTableMethod;
   private Method loadDynamicPartitionsMethod;
   private Method alterTableMethod;
   private Method alterPartitionsMethod;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile int bitmap$0;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Boolean deleteDataInDropIndex$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1) == 0) {
            this.deleteDataInDropIndex = Boolean.TRUE;
            this.bitmap$0 |= 1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.deleteDataInDropIndex;
   }

   public Boolean deleteDataInDropIndex() {
      return (this.bitmap$0 & 1) == 0 ? this.deleteDataInDropIndex$lzycompute() : this.deleteDataInDropIndex;
   }

   private Boolean isAcid$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2) == 0) {
            this.isAcid = Boolean.FALSE;
            this.bitmap$0 |= 2;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.isAcid;
   }

   public Boolean isAcid() {
      return (this.bitmap$0 & 2) == 0 ? this.isAcid$lzycompute() : this.isAcid;
   }

   private Boolean isSkewedStoreAsSubdir$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4) == 0) {
            this.isSkewedStoreAsSubdir = Boolean.FALSE;
            this.bitmap$0 |= 4;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.isSkewedStoreAsSubdir;
   }

   public Boolean isSkewedStoreAsSubdir() {
      return (this.bitmap$0 & 4) == 0 ? this.isSkewedStoreAsSubdir$lzycompute() : this.isSkewedStoreAsSubdir;
   }

   private Boolean throwExceptionInDropIndex$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8) == 0) {
            this.throwExceptionInDropIndex = Boolean.TRUE;
            this.bitmap$0 |= 8;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.throwExceptionInDropIndex;
   }

   public Boolean throwExceptionInDropIndex() {
      return (this.bitmap$0 & 8) == 0 ? this.throwExceptionInDropIndex$lzycompute() : this.throwExceptionInDropIndex;
   }

   private Long txnIdInLoadDynamicPartitions$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16) == 0) {
            this.txnIdInLoadDynamicPartitions = .MODULE$.long2Long(0L);
            this.bitmap$0 |= 16;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.txnIdInLoadDynamicPartitions;
   }

   public Long txnIdInLoadDynamicPartitions() {
      return (this.bitmap$0 & 16) == 0 ? this.txnIdInLoadDynamicPartitions$lzycompute() : this.txnIdInLoadDynamicPartitions;
   }

   private String wildcard$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 32) == 0) {
            this.wildcard = ".*";
            this.bitmap$0 |= 32;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.wildcard;
   }

   public String wildcard() {
      return (this.bitmap$0 & 32) == 0 ? this.wildcard$lzycompute() : this.wildcard;
   }

   public IMetaStoreClient getMSC(final Hive hive) {
      return hive.getMSC();
   }

   private Method loadPartitionMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 64) == 0) {
            this.loadPartitionMethod = this.findMethod(Hive.class, "loadPartition", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, Map.class, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE})));
            this.bitmap$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadPartitionMethod;
   }

   private Method loadPartitionMethod() {
      return (this.bitmap$0 & 64) == 0 ? this.loadPartitionMethod$lzycompute() : this.loadPartitionMethod;
   }

   private Method loadTableMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 128) == 0) {
            this.loadTableMethod = this.findMethod(Hive.class, "loadTable", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE})));
            this.bitmap$0 |= 128;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadTableMethod;
   }

   private Method loadTableMethod() {
      return (this.bitmap$0 & 128) == 0 ? this.loadTableMethod$lzycompute() : this.loadTableMethod;
   }

   private Method loadDynamicPartitionsMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 256) == 0) {
            this.loadDynamicPartitionsMethod = this.findMethod(Hive.class, "loadDynamicPartitions", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, Map.class, Boolean.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Long.TYPE})));
            this.bitmap$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadDynamicPartitionsMethod;
   }

   private Method loadDynamicPartitionsMethod() {
      return (this.bitmap$0 & 256) == 0 ? this.loadDynamicPartitionsMethod$lzycompute() : this.loadDynamicPartitionsMethod;
   }

   private Method alterTableMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 512) == 0) {
            this.alterTableMethod = this.findMethod(Hive.class, "alterTable", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{String.class, Table.class})));
            this.bitmap$0 |= 512;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.alterTableMethod;
   }

   private Method alterTableMethod() {
      return (this.bitmap$0 & 512) == 0 ? this.alterTableMethod$lzycompute() : this.alterTableMethod;
   }

   private Method alterPartitionsMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1024) == 0) {
            this.alterPartitionsMethod = this.findMethod(Hive.class, "alterPartitions", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{String.class, List.class})));
            this.bitmap$0 |= 1024;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.alterPartitionsMethod;
   }

   private Method alterPartitionsMethod() {
      return (this.bitmap$0 & 1024) == 0 ? this.alterPartitionsMethod$lzycompute() : this.alterPartitionsMethod;
   }

   public void setCurrentSessionState(final SessionState state) {
      SessionState.setCurrentSessionState(state);
   }

   public Option getDataLocation(final Table table) {
      return scala.Option..MODULE$.apply(table.getDataLocation()).map((x$1) -> x$1.toString());
   }

   public void setDataLocation(final Table table, final String loc) {
      table.setDataLocation(new Path(loc));
   }

   public void createPartitions(final Hive hive, final Table table, final Seq parts, final boolean ignoreIfExists) {
      AddPartitionDesc addPartitionDesc = new AddPartitionDesc(table.getDbName(), table.getTableName(), ignoreIfExists);
      ((IterableOnceOps)parts.zipWithIndex()).foreach((x0$1) -> {
         $anonfun$createPartitions$1(addPartitionDesc, x0$1);
         return BoxedUnit.UNIT;
      });
      this.recordHiveCall();
      hive.createPartitions(addPartitionDesc);
   }

   public Seq getAllPartitions(final Hive hive, final Table table) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(hive.getAllPartitionsOf(table)).asScala().toSeq();
   }

   public Seq getPartitionsByFilter(final Hive hive, final Table table, final Seq predicates, final CatalogTable catalogTable) {
      String filter = this.convertFilters(table, predicates);
      Object var10000;
      if (filter.isEmpty()) {
         var10000 = this.prunePartitionsFastFallback(hive, table, catalogTable, predicates);
      } else {
         this.logDebug((Function0)(() -> "Hive metastore filter is '" + filter + "'."));
         HiveConf.ConfVars tryDirectSqlConfVar = ConfVars.METASTORE_TRY_DIRECT_SQL;
         boolean shouldFallback = org.apache.spark.sql.internal.SQLConf..MODULE$.get().metastorePartitionPruningFallbackOnException();

         try {
            this.recordHiveCall();
            var10000 = hive.getPartitionsByFilter(table, filter);
         } catch (MetaException var11) {
            if (!shouldFallback) {
               throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.getPartitionMetadataByFilterError(var11);
            }

            this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Caught Hive MetaException attempting to get partition metadata by "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"filter from Hive. Falling back to fetching all partition metadata, which will "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"degrade performance. Modifying your Hive metastore configuration to set "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " to true "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, tryDirectSqlConfVar.varname)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(if it is not true already) may resolve this problem. Or you can enable "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, org.apache.spark.sql.internal.SQLConf..MODULE$.HIVE_METASTORE_PARTITION_PRUNING_FAST_FALLBACK().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to alleviate performance downgrade. Otherwise, to avoid degraded performance "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"you can set ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG3..MODULE$, org.apache.spark.sql.internal.SQLConf..MODULE$.HIVE_METASTORE_PARTITION_PRUNING_FALLBACK_ON_EXCEPTION().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to false and let the query fail instead."})))).log(scala.collection.immutable.Nil..MODULE$))), var11);
            var10000 = this.prunePartitionsFastFallback(hive, table, catalogTable, predicates);
         } catch (Throwable var12) {
            throw var12;
         }
      }

      Collection partitions = (Collection)var10000;
      return scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(partitions).asScala().toSeq();
   }

   private Collection prunePartitionsFastFallback(final Hive hive, final Table table, final CatalogTable catalogTable, final Seq predicates) {
      String timeZoneId = org.apache.spark.sql.internal.SQLConf..MODULE$.get().sessionLocalTimeZone();
      if (org.apache.spark.sql.internal.SQLConf..MODULE$.get().metastorePartitionPruningFastFallback() && !predicates.isEmpty() && !predicates.exists((e) -> BoxesRunTime.boxToBoolean($anonfun$prunePartitionsFastFallback$2(e)))) {
         Object var10000;
         try {
            StructType partitionSchema = org.apache.spark.sql.catalyst.util.CharVarcharUtils..MODULE$.replaceCharVarcharWithStringInSchema(catalogTable.partitionSchema());
            Seq lowerCasePredicates = (Seq)predicates.map((x$2) -> (Expression)x$2.transform(new Serializable() {
                  private static final long serialVersionUID = 0L;

                  public final Object applyOrElse(final Expression x1, final Function1 default) {
                     if (x1 instanceof AttributeReference var5) {
                        return var5.withName(var5.name().toLowerCase(Locale.ROOT));
                     } else {
                        return default.apply(x1);
                     }
                  }

                  public final boolean isDefinedAt(final Expression x1) {
                     return x1 instanceof AttributeReference;
                  }
               }));
            BasePredicate boundPredicate = org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.generatePartitionPredicateByFilter(catalogTable, partitionSchema, lowerCasePredicates);
            this.recordHiveCall();
            Buffer allPartitionNames = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getPartitionNames(table.getDbName(), table.getTableName(), (short)-1)).asScala();
            Buffer partNames = (Buffer)allPartitionNames.filter((p) -> BoxesRunTime.boxToBoolean($anonfun$prunePartitionsFastFallback$5(boundPredicate, partitionSchema, timeZoneId, p)));
            this.recordHiveCall();
            var10000 = hive.getPartitionsByNames(table, scala.jdk.CollectionConverters..MODULE$.BufferHasAsJava(partNames).asJava());
         } catch (Throwable var15) {
            if (var15 instanceof HiveException) {
               HiveException var14 = (HiveException)var15;
               if (var14.getCause() instanceof MetaException) {
                  this.logWarning((Function0)(() -> "Caught Hive MetaException attempting to get partition metadata by filter from client side. Falling back to fetching all partition metadata"), var14);
                  this.recordHiveCall();
                  var10000 = hive.getAllPartitionsOf(table);
                  return (Collection)var10000;
               }
            }

            throw var15;
         }

         return (Collection)var10000;
      } else {
         this.recordHiveCall();
         return hive.getAllPartitionsOf(table);
      }
   }

   public CommandProcessor getCommandProcessor(final String token, final HiveConf conf) {
      return CommandProcessorFactory.get((String[])((Object[])(new String[]{token})), conf);
   }

   public Seq getDriverResults(final Driver driver) {
      ArrayList res = new ArrayList();
      driver.getResults(res);
      return ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(res).asScala().map((x0$1) -> {
         if (x0$1 instanceof String var3) {
            return var3;
         } else if (x0$1 instanceof Object[] var4) {
            return (String)var4[0];
         } else {
            throw new MatchError(x0$1);
         }
      })).toSeq();
   }

   public long getMetastoreClientConnectRetryDelayMillis(final HiveConf conf) {
      return conf.getTimeVar(ConfVars.METASTORE_CLIENT_CONNECT_RETRY_DELAY, TimeUnit.MILLISECONDS);
   }

   public Seq getTablesByType(final Hive hive, final String dbName, final String pattern, final TableType tableType) {
      throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.getTablesByTypeUnsupportedByHiveVersionError();
   }

   public void loadPartition(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final boolean inheritTableSpecs, final boolean isSkewedStoreAsSubdir, final boolean isSrcLocal) {
      this.recordHiveCall();
      this.loadPartitionMethod().invoke(hive, loadPath, tableName, partSpec, .MODULE$.boolean2Boolean(replace), .MODULE$.boolean2Boolean(inheritTableSpecs), .MODULE$.boolean2Boolean(isSkewedStoreAsSubdir), .MODULE$.boolean2Boolean(isSrcLocal), this.isAcid());
   }

   public void loadTable(final Hive hive, final Path loadPath, final String tableName, final boolean replace, final boolean isSrcLocal) {
      this.recordHiveCall();
      this.loadTableMethod().invoke(hive, loadPath, tableName, .MODULE$.boolean2Boolean(replace), .MODULE$.boolean2Boolean(isSrcLocal), this.isSkewedStoreAsSubdir(), this.isAcid());
   }

   public void loadDynamicPartitions(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final int numDP, final Table hiveTable) {
      this.recordHiveCall();
      boolean listBucketingEnabled = hiveTable.isStoredAsSubDirectories();
      this.loadDynamicPartitionsMethod().invoke(hive, loadPath, tableName, partSpec, .MODULE$.boolean2Boolean(replace), .MODULE$.int2Integer(numDP), .MODULE$.boolean2Boolean(listBucketingEnabled), this.isAcid(), this.txnIdInLoadDynamicPartitions());
   }

   public void dropIndex(final Hive hive, final String dbName, final String tableName, final String indexName) {
      this.recordHiveCall();
      hive.dropIndex(dbName, tableName, indexName, .MODULE$.Boolean2boolean(this.throwExceptionInDropIndex()), .MODULE$.Boolean2boolean(this.deleteDataInDropIndex()));
   }

   public void dropTable(final Hive hive, final String dbName, final String tableName, final boolean deleteData, final boolean ignoreIfNotExists, final boolean purge) {
      this.recordHiveCall();
      hive.dropTable(dbName, tableName, deleteData, ignoreIfNotExists, purge);
   }

   public void alterTable(final Hive hive, final String tableName, final Table table) {
      this.recordHiveCall();
      this.alterTableMethod().invoke(hive, tableName, table);
   }

   public void alterPartitions(final Hive hive, final String tableName, final List newParts) {
      this.recordHiveCall();
      this.alterPartitionsMethod().invoke(hive, tableName, newParts);
   }

   public void dropPartition(final Hive hive, final String dbName, final String tableName, final List part, final boolean deleteData, final boolean purge) {
      PartitionDropOptions dropOptions = new PartitionDropOptions();
      dropOptions.deleteData(deleteData);
      dropOptions.purgeData(purge);
      this.recordHiveCall();
      hive.dropPartition(dbName, tableName, part, dropOptions);
   }

   private Function toHiveFunction(final CatalogFunction f, final String db) {
      Seq resourceUris = (Seq)f.resources().map((resource) -> new ResourceUri(ResourceType.valueOf(resource.resourceType().resourceType().toUpperCase(Locale.ROOT)), resource.uri()));
      return new Function(f.identifier().funcName(), db, f.className(), (String)null, PrincipalType.USER, (int)TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), FunctionType.JAVA, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(resourceUris).asJava());
   }

   public void createFunction(final Hive hive, final String db, final CatalogFunction func) {
      this.recordHiveCall();
      hive.createFunction(this.toHiveFunction(func, db));
   }

   public void dropFunction(final Hive hive, final String db, final String name) {
      this.recordHiveCall();
      hive.dropFunction(db, name);
   }

   public void renameFunction(final Hive hive, final String db, final String oldName, final String newName) {
      CatalogFunction qual$1 = (CatalogFunction)this.getFunctionOption(hive, db, oldName).getOrElse(() -> {
         throw new NoSuchPermanentFunctionException(db, oldName);
      });
      FunctionIdentifier x$1 = org.apache.spark.sql.catalyst.FunctionIdentifier..MODULE$.apply(newName, new Some(db));
      String x$2 = qual$1.copy$default$2();
      Seq x$3 = qual$1.copy$default$3();
      CatalogFunction catalogFunc = qual$1.copy(x$1, x$2, x$3);
      Function hiveFunc = this.toHiveFunction(catalogFunc, db);
      this.recordHiveCall();
      hive.alterFunction(db, oldName, hiveFunc);
   }

   public void alterFunction(final Hive hive, final String db, final CatalogFunction func) {
      this.recordHiveCall();
      hive.alterFunction(db, func.identifier().funcName(), this.toHiveFunction(func, db));
   }

   private CatalogFunction fromHiveFunction(final Function hf) {
      FunctionIdentifier name = org.apache.spark.sql.catalyst.FunctionIdentifier..MODULE$.apply(hf.getFunctionName(), scala.Option..MODULE$.apply(hf.getDbName()));
      Buffer resources = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hf.getResourceUris()).asScala().map((uri) -> {
         ResourceType var3 = uri.getResourceType();
         String var10000;
         if (ResourceType.ARCHIVE.equals(var3)) {
            var10000 = "archive";
         } else if (ResourceType.FILE.equals(var3)) {
            var10000 = "file";
         } else {
            if (!ResourceType.JAR.equals(var3)) {
               throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.unknownHiveResourceTypeError(var3.toString());
            }

            var10000 = "jar";
         }

         String resourceType = var10000;
         return new FunctionResource(org.apache.spark.sql.catalyst.catalog.FunctionResourceType..MODULE$.fromString(resourceType), uri.getUri());
      });
      return new CatalogFunction(name, hf.getClassName(), resources.toSeq());
   }

   public Option getFunctionOption(final Hive hive, final String db, final String name) {
      Object var10000;
      try {
         this.recordHiveCall();
         var10000 = scala.Option..MODULE$.apply(hive.getFunction(db, name)).map((hf) -> this.fromHiveFunction(hf));
      } catch (Throwable var9) {
         if (var9 != null) {
            Option var7 = scala.util.control.NonFatal..MODULE$.unapply(var9);
            if (!var7.isEmpty()) {
               Throwable e = (Throwable)var7.get();
               if (this.isCausedBy(e, name + " does not exist")) {
                  var10000 = scala.None..MODULE$;
                  return (Option)var10000;
               }
            }
         }

         throw var9;
      }

      return (Option)var10000;
   }

   private boolean isCausedBy(final Throwable e, final String matchMassage) {
      while(!e.getMessage().contains(matchMassage)) {
         if (e.getCause() == null) {
            return false;
         }

         Throwable var10000 = e.getCause();
         matchMassage = matchMassage;
         e = var10000;
      }

      return true;
   }

   public Seq listFunctions(final Hive hive, final String db, final String pattern) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getFunctions(db, pattern)).asScala().toSeq();
   }

   public String convertFilters(final Table table, final Seq filters) {
      LazyRef dateFormatter$lzy = new LazyRef();
      LazyRef SpecialBinaryComparison$module = new LazyRef();
      LazyRef ExtractableLiteral$module = new LazyRef();
      LazyRef ExtractableLiterals$module = new LazyRef();
      LazyRef ExtractableValues$module = new LazyRef();
      LazyRef ExtractableDateValues$module = new LazyRef();
      LazyRef SupportedAttribute$module = new LazyRef();
      LazyRef ExtractAttribute$module = new LazyRef();
      boolean useAdvanced = org.apache.spark.sql.internal.SQLConf..MODULE$.get().advancedPartitionPredicatePushdownEnabled();
      int inSetThreshold = org.apache.spark.sql.internal.SQLConf..MODULE$.get().metastorePartitionPruningInSetThreshold();
      return ((IterableOnceOps)filters.flatMap((expr) -> this.convert$1(expr, inSetThreshold, useAdvanced, ExtractAttribute$module, SupportedAttribute$module, table, ExtractableLiterals$module, ExtractableDateValues$module, ExtractableValues$module, SpecialBinaryComparison$module, ExtractableLiteral$module, dateFormatter$lzy))).mkString(" and ");
   }

   public String org$apache$spark$sql$hive$client$Shim_v2_0$$quoteStringLiteral(final String str) {
      if (!str.contains("\"")) {
         return "\"" + str + "\"";
      } else if (!str.contains("'")) {
         return "'" + str + "'";
      } else {
         throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.invalidPartitionFilterError();
      }
   }

   public String getDatabaseOwnerName(final Database db) {
      return (String)scala.Option..MODULE$.apply(db.getOwnerName()).getOrElse(() -> "");
   }

   public void setDatabaseOwnerName(final Database db, final String owner) {
      db.setOwnerName(owner);
   }

   public void createDatabase(final Hive hive, final Database db, final boolean ignoreIfExists) {
      this.recordHiveCall();
      hive.createDatabase(db, ignoreIfExists);
   }

   public void dropDatabase(final Hive hive, final String dbName, final boolean deleteData, final boolean ignoreUnknownDb, final boolean cascade) {
      this.recordHiveCall();
      hive.dropDatabase(dbName, deleteData, ignoreUnknownDb, cascade);
   }

   public void alterDatabase(final Hive hive, final String dbName, final Database d) {
      this.recordHiveCall();
      hive.alterDatabase(dbName, d);
   }

   public Database getDatabase(final Hive hive, final String dbName) {
      this.recordHiveCall();
      return hive.getDatabase(dbName);
   }

   public Seq getAllDatabases(final Hive hive) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getAllDatabases()).asScala().toSeq();
   }

   public Seq getDatabasesByPattern(final Hive hive, final String pattern) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getDatabasesByPattern(pattern)).asScala().toSeq();
   }

   public boolean databaseExists(final Hive hive, final String dbName) {
      this.recordHiveCall();
      return hive.databaseExists(dbName);
   }

   public void createTable(final Hive hive, final Table table, final boolean ifNotExists) {
      this.recordHiveCall();
      hive.createTable(table, ifNotExists);
   }

   public Table getTable(final Hive hive, final String dbName, final String tableName, final boolean throwException) {
      this.recordHiveCall();
      Table table = hive.getTable(dbName, tableName, throwException);
      if (table != null) {
         table.getTTable().setTableName(tableName);
         table.getTTable().setDbName(dbName);
      }

      return table;
   }

   public Seq getTablesByPattern(final Hive hive, final String dbName, final String pattern) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getTablesByPattern(dbName, pattern)).asScala().toSeq();
   }

   public Seq getAllTables(final Hive hive, final String dbName) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getAllTables(dbName)).asScala().toSeq();
   }

   public void dropTable(final Hive hive, final String dbName, final String tableName) {
      this.recordHiveCall();
      hive.dropTable(dbName, tableName);
   }

   public Partition getPartition(final Hive hive, final Table table, final Map partSpec, final boolean forceCreate) {
      this.recordHiveCall();
      return hive.getPartition(table, partSpec, forceCreate);
   }

   public Seq getPartitions(final Hive hive, final Table table, final Map partSpec) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getPartitions(table, partSpec)).asScala().toSeq();
   }

   public Seq getPartitionNames(final Hive hive, final String dbName, final String tableName, final short max) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getPartitionNames(dbName, tableName, max)).asScala().toSeq();
   }

   public Seq getPartitionNames(final Hive hive, final String dbName, final String tableName, final Map partSpec, final short max) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getPartitionNames(dbName, tableName, partSpec, max)).asScala().toSeq();
   }

   public void renamePartition(final Hive hive, final Table table, final Map oldPartSpec, final Partition newPart) {
      this.recordHiveCall();
      hive.renamePartition(table, oldPartSpec, newPart);
   }

   public Seq getIndexes(final Hive hive, final String dbName, final String tableName, final short max) {
      this.recordHiveCall();
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hive.getIndexes(dbName, tableName, max)).asScala().toSeq();
   }

   // $FF: synthetic method
   public static final void $anonfun$createPartitions$1(final AddPartitionDesc addPartitionDesc$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         CatalogTablePartition s = (CatalogTablePartition)x0$1._1();
         int i = x0$1._2$mcI$sp();
         addPartitionDesc$1.addPartition(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(s.spec()).asJava(), (String)s.storage().locationUri().map((uri) -> org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.URIToString(uri)).orNull(scala..less.colon.less..MODULE$.refl()));
         if (s.parameters().nonEmpty()) {
            addPartitionDesc$1.getPartition(i).setPartParams(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(s.parameters()).asJava());
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prunePartitionsFastFallback$1(final Expression x0$1) {
      if (x0$1 instanceof Cast var3) {
         return var3.needsTimeZone();
      } else if (x0$1 instanceof TimeZoneAwareExpression var4) {
         return !(var4 instanceof Cast);
      } else {
         return false;
      }
   }

   private static final boolean hasTimeZoneAwareExpression$1(final Expression e) {
      return e.exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$prunePartitionsFastFallback$1(x0$1)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prunePartitionsFastFallback$2(final Expression e) {
      return hasTimeZoneAwareExpression$1(e);
   }

   private static final InternalRow toRow$1(final scala.collection.immutable.Map spec, final StructType partitionSchema$1, final String timeZoneId$1) {
      return org.apache.spark.sql.catalyst.InternalRow..MODULE$.fromSeq((Seq)partitionSchema$1.map((field) -> {
         String var7;
         label17: {
            label16: {
               Object var10000 = spec.apply(field.name());
               String var4 = org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.DEFAULT_PARTITION_NAME();
               if (var10000 == null) {
                  if (var4 == null) {
                     break label16;
                  }
               } else if (var10000.equals(var4)) {
                  break label16;
               }

               var7 = (String)spec.apply(field.name());
               break label17;
            }

            var7 = null;
         }

         String partValue = var7;
         Cast qual$1 = new Cast(org.apache.spark.sql.catalyst.expressions.Literal..MODULE$.apply(partValue), field.dataType(), scala.Option..MODULE$.apply(timeZoneId$1), org.apache.spark.sql.catalyst.expressions.Cast..MODULE$.apply$default$4());
         InternalRow x$1 = qual$1.eval$default$1();
         return qual$1.eval(x$1);
      }));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prunePartitionsFastFallback$5(final BasePredicate boundPredicate$1, final StructType partitionSchema$1, final String timeZoneId$1, final String p) {
      scala.collection.immutable.Map spec = org.apache.spark.sql.execution.datasources.PartitioningUtils..MODULE$.parsePathFragment(p);
      return boundPredicate$1.eval(toRow$1(spec, partitionSchema$1, timeZoneId$1));
   }

   // $FF: synthetic method
   private static final DateFormatter dateFormatter$lzycompute$1(final LazyRef dateFormatter$lzy$1) {
      synchronized(dateFormatter$lzy$1){}

      DateFormatter var2;
      try {
         var2 = dateFormatter$lzy$1.initialized() ? (DateFormatter)dateFormatter$lzy$1.value() : (DateFormatter)dateFormatter$lzy$1.initialize(org.apache.spark.sql.catalyst.util.DateFormatter..MODULE$.apply());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public static final DateFormatter org$apache$spark$sql$hive$client$Shim_v2_0$$dateFormatter$1(final LazyRef dateFormatter$lzy$1) {
      return dateFormatter$lzy$1.initialized() ? (DateFormatter)dateFormatter$lzy$1.value() : dateFormatter$lzycompute$1(dateFormatter$lzy$1);
   }

   // $FF: synthetic method
   private static final SpecialBinaryComparison$1$ SpecialBinaryComparison$lzycompute$1(final LazyRef SpecialBinaryComparison$module$1) {
      synchronized(SpecialBinaryComparison$module$1){}

      SpecialBinaryComparison$1$ var2;
      try {
         class SpecialBinaryComparison$1$ {
            public Option unapply(final BinaryComparison e) {
               return (Option)(e instanceof EqualNullSafe ? scala.None..MODULE$ : new Some(new Tuple2(e.left(), e.right())));
            }

            public SpecialBinaryComparison$1$() {
            }
         }

         var2 = SpecialBinaryComparison$module$1.initialized() ? (SpecialBinaryComparison$1$)SpecialBinaryComparison$module$1.value() : (SpecialBinaryComparison$1$)SpecialBinaryComparison$module$1.initialize(new SpecialBinaryComparison$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final SpecialBinaryComparison$1$ SpecialBinaryComparison$2(final LazyRef SpecialBinaryComparison$module$1) {
      return SpecialBinaryComparison$module$1.initialized() ? (SpecialBinaryComparison$1$)SpecialBinaryComparison$module$1.value() : SpecialBinaryComparison$lzycompute$1(SpecialBinaryComparison$module$1);
   }

   // $FF: synthetic method
   private final ExtractableLiteral$1$ ExtractableLiteral$lzycompute$1(final LazyRef ExtractableLiteral$module$1, final LazyRef dateFormatter$lzy$1) {
      synchronized(ExtractableLiteral$module$1){}

      ExtractableLiteral$1$ var4;
      try {
         class ExtractableLiteral$1$ {
            // $FF: synthetic field
            private final Shim_v2_0 $outer;
            private final LazyRef dateFormatter$lzy$1;

            public Option unapply(final Expression expr) {
               boolean var3 = false;
               Literal var4 = null;
               if (expr instanceof Literal) {
                  var3 = true;
                  var4 = (Literal)expr;
                  Object var6 = var4.value();
                  if (var6 == null) {
                     return scala.None..MODULE$;
                  }
               }

               if (var3) {
                  Object value = var4.value();
                  if (var4.dataType() instanceof IntegralType) {
                     return new Some(value.toString());
                  }
               }

               if (var3) {
                  Object value = var4.value();
                  if (var4.dataType() instanceof StringType) {
                     return new Some(this.$outer.org$apache$spark$sql$hive$client$Shim_v2_0$$quoteStringLiteral(value.toString()));
                  }
               }

               if (var3) {
                  Object value = var4.value();
                  if (var4.dataType() instanceof DateType) {
                     return new Some(this.$outer.org$apache$spark$sql$hive$client$Shim_v2_0$$quoteStringLiteral(Shim_v2_0.org$apache$spark$sql$hive$client$Shim_v2_0$$dateFormatter$1(this.dateFormatter$lzy$1).format(BoxesRunTime.unboxToInt(value))));
                  }
               }

               return scala.None..MODULE$;
            }

            public ExtractableLiteral$1$(final LazyRef dateFormatter$lzy$1) {
               if (Shim_v2_0.this == null) {
                  throw null;
               } else {
                  this.$outer = Shim_v2_0.this;
                  this.dateFormatter$lzy$1 = dateFormatter$lzy$1;
                  super();
               }
            }
         }

         var4 = ExtractableLiteral$module$1.initialized() ? (ExtractableLiteral$1$)ExtractableLiteral$module$1.value() : (ExtractableLiteral$1$)ExtractableLiteral$module$1.initialize(new ExtractableLiteral$1$(dateFormatter$lzy$1));
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   public final ExtractableLiteral$1$ org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(final LazyRef ExtractableLiteral$module$1, final LazyRef dateFormatter$lzy$1) {
      return ExtractableLiteral$module$1.initialized() ? (ExtractableLiteral$1$)ExtractableLiteral$module$1.value() : this.ExtractableLiteral$lzycompute$1(ExtractableLiteral$module$1, dateFormatter$lzy$1);
   }

   // $FF: synthetic method
   private final ExtractableLiterals$1$ ExtractableLiterals$lzycompute$1(final LazyRef ExtractableLiterals$module$1, final LazyRef ExtractableLiteral$module$1, final LazyRef dateFormatter$lzy$1) {
      synchronized(ExtractableLiterals$module$1){}

      ExtractableLiterals$1$ var5;
      try {
         class ExtractableLiterals$1$ {
            // $FF: synthetic field
            private final Shim_v2_0 $outer;
            private final LazyRef ExtractableLiteral$module$1;
            private final LazyRef dateFormatter$lzy$1;

            public Option unapply(final Seq exprs) {
               Seq extractables = (Seq)((IterableOps)exprs.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$unapply$1(x0$1)))).map((expr) -> this.$outer.org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(this.ExtractableLiteral$module$1, this.dateFormatter$lzy$1).unapply(expr));
               return (Option)(extractables.nonEmpty() && extractables.forall((x$3) -> BoxesRunTime.boxToBoolean($anonfun$unapply$3(x$3))) ? new Some(extractables.map((x$4) -> (String)x$4.get())) : scala.None..MODULE$);
            }

            // $FF: synthetic method
            public static final boolean $anonfun$unapply$1(final Expression x0$1) {
               if (x0$1 instanceof Literal var3) {
                  Object var4 = var3.value();
                  if (var4 == null) {
                     return false;
                  }
               }

               return true;
            }

            // $FF: synthetic method
            public static final boolean $anonfun$unapply$3(final Option x$3) {
               return x$3.isDefined();
            }

            public ExtractableLiterals$1$(final LazyRef ExtractableLiteral$module$1, final LazyRef dateFormatter$lzy$1) {
               if (Shim_v2_0.this == null) {
                  throw null;
               } else {
                  this.$outer = Shim_v2_0.this;
                  this.ExtractableLiteral$module$1 = ExtractableLiteral$module$1;
                  this.dateFormatter$lzy$1 = dateFormatter$lzy$1;
                  super();
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }

         var5 = ExtractableLiterals$module$1.initialized() ? (ExtractableLiterals$1$)ExtractableLiterals$module$1.value() : (ExtractableLiterals$1$)ExtractableLiterals$module$1.initialize(new ExtractableLiterals$1$(ExtractableLiteral$module$1, dateFormatter$lzy$1));
      } catch (Throwable var7) {
         throw var7;
      }

      return var5;
   }

   private final ExtractableLiterals$1$ ExtractableLiterals$2(final LazyRef ExtractableLiterals$module$1, final LazyRef ExtractableLiteral$module$1, final LazyRef dateFormatter$lzy$1) {
      return ExtractableLiterals$module$1.initialized() ? (ExtractableLiterals$1$)ExtractableLiterals$module$1.value() : this.ExtractableLiterals$lzycompute$1(ExtractableLiterals$module$1, ExtractableLiteral$module$1, dateFormatter$lzy$1);
   }

   // $FF: synthetic method
   private final ExtractableValues$1$ ExtractableValues$lzycompute$1(final LazyRef ExtractableValues$module$1) {
      synchronized(ExtractableValues$module$1){}

      ExtractableValues$1$ var3;
      try {
         class ExtractableValues$1$ {
            private PartialFunction valueToLiteralString;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final Shim_v2_0 $outer;

            private PartialFunction valueToLiteralString$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.valueToLiteralString = new Serializable() {
                        private static final long serialVersionUID = 0L;
                        // $FF: synthetic field
                        private final ExtractableValues$1$ $outer;

                        public final Object applyOrElse(final Object x1, final Function1 default) {
                           if (x1 instanceof Byte) {
                              byte var5 = BoxesRunTime.unboxToByte(x1);
                              return Byte.toString(var5);
                           } else if (x1 instanceof Short) {
                              short var6 = BoxesRunTime.unboxToShort(x1);
                              return Short.toString(var6);
                           } else if (x1 instanceof Integer) {
                              int var7 = BoxesRunTime.unboxToInt(x1);
                              return Integer.toString(var7);
                           } else if (x1 instanceof Long) {
                              long var8 = BoxesRunTime.unboxToLong(x1);
                              return Long.toString(var8);
                           } else if (x1 instanceof UTF8String) {
                              UTF8String var10 = (UTF8String)x1;
                              return this.$outer.org$apache$spark$sql$hive$client$Shim_v2_0$ExtractableValues$$$outer().org$apache$spark$sql$hive$client$Shim_v2_0$$quoteStringLiteral(var10.toString());
                           } else {
                              return default.apply(x1);
                           }
                        }

                        public final boolean isDefinedAt(final Object x1) {
                           if (x1 instanceof Byte) {
                              return true;
                           } else if (x1 instanceof Short) {
                              return true;
                           } else if (x1 instanceof Integer) {
                              return true;
                           } else if (x1 instanceof Long) {
                              return true;
                           } else {
                              return x1 instanceof UTF8String;
                           }
                        }

                        public {
                           if (ExtractableValues$1$.this == null) {
                              throw null;
                           } else {
                              this.$outer = ExtractableValues$1$.this;
                           }
                        }
                     };
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var3) {
                  throw var3;
               }

               return this.valueToLiteralString;
            }

            private PartialFunction valueToLiteralString() {
               return !this.bitmap$0 ? this.valueToLiteralString$lzycompute() : this.valueToLiteralString;
            }

            public Option unapply(final Set values) {
               Seq extractables = (Seq)((IterableOnceOps)values.filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$unapply$5(x$5)))).toSeq().map(this.valueToLiteralString().lift());
               return (Option)(extractables.nonEmpty() && extractables.forall((x$6) -> BoxesRunTime.boxToBoolean($anonfun$unapply$6(x$6))) ? new Some(extractables.map((x$7) -> (String)x$7.get())) : scala.None..MODULE$);
            }

            // $FF: synthetic method
            public Shim_v2_0 org$apache$spark$sql$hive$client$Shim_v2_0$ExtractableValues$$$outer() {
               return this.$outer;
            }

            // $FF: synthetic method
            public static final boolean $anonfun$unapply$5(final Object x$5) {
               return x$5 != null;
            }

            // $FF: synthetic method
            public static final boolean $anonfun$unapply$6(final Option x$6) {
               return x$6.isDefined();
            }

            public ExtractableValues$1$() {
               if (Shim_v2_0.this == null) {
                  throw null;
               } else {
                  this.$outer = Shim_v2_0.this;
                  super();
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }

         var3 = ExtractableValues$module$1.initialized() ? (ExtractableValues$1$)ExtractableValues$module$1.value() : (ExtractableValues$1$)ExtractableValues$module$1.initialize(new ExtractableValues$1$());
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private final ExtractableValues$1$ ExtractableValues$2(final LazyRef ExtractableValues$module$1) {
      return ExtractableValues$module$1.initialized() ? (ExtractableValues$1$)ExtractableValues$module$1.value() : this.ExtractableValues$lzycompute$1(ExtractableValues$module$1);
   }

   // $FF: synthetic method
   private final ExtractableDateValues$1$ ExtractableDateValues$lzycompute$1(final LazyRef ExtractableDateValues$module$1, final LazyRef dateFormatter$lzy$1) {
      synchronized(ExtractableDateValues$module$1){}

      ExtractableDateValues$1$ var4;
      try {
         class ExtractableDateValues$1$ {
            private PartialFunction valueToLiteralString;
            private volatile boolean bitmap$0;
            // $FF: synthetic field
            private final Shim_v2_0 $outer;
            public final LazyRef dateFormatter$lzy$1;

            private PartialFunction valueToLiteralString$lzycompute() {
               synchronized(this){}

               try {
                  if (!this.bitmap$0) {
                     this.valueToLiteralString = new Serializable() {
                        private static final long serialVersionUID = 0L;
                        // $FF: synthetic field
                        private final ExtractableDateValues$1$ $outer;

                        public final Object applyOrElse(final Object x1, final Function1 default) {
                           if (x1 instanceof Integer) {
                              int var5 = BoxesRunTime.unboxToInt(x1);
                              return this.$outer.org$apache$spark$sql$hive$client$Shim_v2_0$ExtractableDateValues$$$outer().org$apache$spark$sql$hive$client$Shim_v2_0$$quoteStringLiteral(Shim_v2_0.org$apache$spark$sql$hive$client$Shim_v2_0$$dateFormatter$1(this.$outer.dateFormatter$lzy$1).format(var5));
                           } else {
                              return default.apply(x1);
                           }
                        }

                        public final boolean isDefinedAt(final Object x1) {
                           return x1 instanceof Integer;
                        }

                        public {
                           if (ExtractableDateValues$1$.this == null) {
                              throw null;
                           } else {
                              this.$outer = ExtractableDateValues$1$.this;
                           }
                        }
                     };
                     this.bitmap$0 = true;
                  }
               } catch (Throwable var3) {
                  throw var3;
               }

               return this.valueToLiteralString;
            }

            private PartialFunction valueToLiteralString() {
               return !this.bitmap$0 ? this.valueToLiteralString$lzycompute() : this.valueToLiteralString;
            }

            public Option unapply(final Set values) {
               Seq extractables = (Seq)((IterableOnceOps)values.filter((x$8) -> BoxesRunTime.boxToBoolean($anonfun$unapply$8(x$8)))).toSeq().map(this.valueToLiteralString().lift());
               return (Option)(extractables.nonEmpty() && extractables.forall((x$9) -> BoxesRunTime.boxToBoolean($anonfun$unapply$9(x$9))) ? new Some(extractables.map((x$10) -> (String)x$10.get())) : scala.None..MODULE$);
            }

            // $FF: synthetic method
            public Shim_v2_0 org$apache$spark$sql$hive$client$Shim_v2_0$ExtractableDateValues$$$outer() {
               return this.$outer;
            }

            // $FF: synthetic method
            public static final boolean $anonfun$unapply$8(final Object x$8) {
               return x$8 != null;
            }

            // $FF: synthetic method
            public static final boolean $anonfun$unapply$9(final Option x$9) {
               return x$9.isDefined();
            }

            public ExtractableDateValues$1$(final LazyRef dateFormatter$lzy$1) {
               if (Shim_v2_0.this == null) {
                  throw null;
               } else {
                  this.$outer = Shim_v2_0.this;
                  this.dateFormatter$lzy$1 = dateFormatter$lzy$1;
                  super();
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }

         var4 = ExtractableDateValues$module$1.initialized() ? (ExtractableDateValues$1$)ExtractableDateValues$module$1.value() : (ExtractableDateValues$1$)ExtractableDateValues$module$1.initialize(new ExtractableDateValues$1$(dateFormatter$lzy$1));
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   private final ExtractableDateValues$1$ ExtractableDateValues$2(final LazyRef ExtractableDateValues$module$1, final LazyRef dateFormatter$lzy$1) {
      return ExtractableDateValues$module$1.initialized() ? (ExtractableDateValues$1$)ExtractableDateValues$module$1.value() : this.ExtractableDateValues$lzycompute$1(ExtractableDateValues$module$1, dateFormatter$lzy$1);
   }

   // $FF: synthetic method
   private static final SupportedAttribute$1$ SupportedAttribute$lzycompute$1(final LazyRef SupportedAttribute$module$1, final Table table$1) {
      synchronized(SupportedAttribute$module$1){}

      SupportedAttribute$1$ var3;
      try {
         class SupportedAttribute$1$ {
            private final Set varcharKeys;

            private Set varcharKeys() {
               return this.varcharKeys;
            }

            public Option unapply(final Attribute attr) {
               Function2 resolver = org.apache.spark.sql.internal.SQLConf..MODULE$.get().resolver();
               if (this.varcharKeys().exists((c) -> BoxesRunTime.boxToBoolean($anonfun$unapply$11(resolver, attr, c)))) {
                  return scala.None..MODULE$;
               } else if (!(attr.dataType() instanceof IntegralType)) {
                  DataType var10000 = attr.dataType();
                  StringType var3 = org.apache.spark.sql.types.StringType..MODULE$;
                  if (var10000 == null) {
                     if (var3 == null) {
                        return new Some(attr.name());
                     }
                  } else if (var10000.equals(var3)) {
                     return new Some(attr.name());
                  }

                  var10000 = attr.dataType();
                  DateType var4 = org.apache.spark.sql.types.DateType..MODULE$;
                  if (var10000 == null) {
                     if (var4 == null) {
                        return new Some(attr.name());
                     }
                  } else if (var10000.equals(var4)) {
                     return new Some(attr.name());
                  }

                  return scala.None..MODULE$;
               } else {
                  return new Some(attr.name());
               }
            }

            // $FF: synthetic method
            public static final boolean $anonfun$varcharKeys$1(final FieldSchema col) {
               return col.getType().startsWith("varchar") || col.getType().startsWith("char");
            }

            // $FF: synthetic method
            public static final boolean $anonfun$unapply$11(final Function2 resolver$1, final Attribute attr$1, final String c) {
               return BoxesRunTime.unboxToBoolean(resolver$1.apply(c, attr$1.name()));
            }

            public SupportedAttribute$1$(final Table table$1) {
               this.varcharKeys = ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(table$1.getPartitionKeys()).asScala().filter((col) -> BoxesRunTime.boxToBoolean($anonfun$varcharKeys$1(col)))).map((col) -> col.getName())).toSet();
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }

         var3 = SupportedAttribute$module$1.initialized() ? (SupportedAttribute$1$)SupportedAttribute$module$1.value() : (SupportedAttribute$1$)SupportedAttribute$module$1.initialize(new SupportedAttribute$1$(table$1));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private final SupportedAttribute$1$ SupportedAttribute$2(final LazyRef SupportedAttribute$module$1, final Table table$1) {
      return SupportedAttribute$module$1.initialized() ? (SupportedAttribute$1$)SupportedAttribute$module$1.value() : SupportedAttribute$lzycompute$1(SupportedAttribute$module$1, table$1);
   }

   private static final String convertInToOr$1(final String name, final Seq values) {
      return ((IterableOnceOps)values.map((value) -> name + " = " + value)).mkString("(", " or ", ")");
   }

   private static final String convertNotInToAnd$1(final String name, final Seq values) {
      return ((IterableOnceOps)values.map((value) -> name + " != " + value)).mkString("(", " and ", ")");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertFilters$3(final Expression x0$1) {
      if (x0$1 instanceof Literal var3) {
         Object var4 = var3.value();
         if (var4 == null) {
            return true;
         }
      }

      return false;
   }

   private static final boolean hasNullLiteral$1(final Seq list) {
      return list.exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$convertFilters$3(x0$1)));
   }

   // $FF: synthetic method
   private static final ExtractAttribute$1$ ExtractAttribute$lzycompute$1(final LazyRef ExtractAttribute$module$1) {
      synchronized(ExtractAttribute$module$1){}

      ExtractAttribute$1$ var2;
      try {
         class ExtractAttribute$1$ {
            public Option unapply(final Expression expr) {
               while(!(expr instanceof Attribute)) {
                  if (expr instanceof Cast var6) {
                     Expression child = var6.child();
                     DataType dt = var6.dataType();
                     if (child != null && org.apache.spark.sql.types.IntegralTypeExpression..MODULE$.unapply(child) && dt instanceof IntegralType var9) {
                        if (org.apache.spark.sql.catalyst.expressions.Cast..MODULE$.canUpCast((AtomicType)child.dataType(), var9)) {
                           expr = child;
                           continue;
                        }
                     }
                  }

                  return scala.None..MODULE$;
               }

               Attribute var5 = (Attribute)expr;
               return new Some(var5);
            }

            public ExtractAttribute$1$() {
            }
         }

         var2 = ExtractAttribute$module$1.initialized() ? (ExtractAttribute$1$)ExtractAttribute$module$1.value() : (ExtractAttribute$1$)ExtractAttribute$module$1.initialize(new ExtractAttribute$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final ExtractAttribute$1$ ExtractAttribute$2(final LazyRef ExtractAttribute$module$1) {
      return ExtractAttribute$module$1.initialized() ? (ExtractAttribute$1$)ExtractAttribute$module$1.value() : ExtractAttribute$lzycompute$1(ExtractAttribute$module$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertFilters$4(final Object x$11) {
      return x$11 != null;
   }

   private final Option convert$1(final Expression expr, final int inSetThreshold$1, final boolean useAdvanced$1, final LazyRef ExtractAttribute$module$1, final LazyRef SupportedAttribute$module$1, final Table table$1, final LazyRef ExtractableLiterals$module$1, final LazyRef ExtractableDateValues$module$1, final LazyRef ExtractableValues$module$1, final LazyRef SpecialBinaryComparison$module$1, final LazyRef ExtractableLiteral$module$1, final LazyRef dateFormatter$lzy$1) {
      while(true) {
         boolean var15 = false;
         Not var16 = null;
         boolean var17 = false;
         InSet var18 = null;
         boolean var19 = false;
         BinaryComparison var20 = null;
         if (expr instanceof Not) {
            var15 = true;
            var16 = (Not)expr;
            Expression var22 = var16.child();
            if (var22 instanceof InSet) {
               InSet var23 = (InSet)var22;
               Set values = var23.hset();
               if (values.size() > inSetThreshold$1) {
                  return scala.None..MODULE$;
               }
            }
         }

         if (var15) {
            Expression var25 = var16.child();
            if (var25 instanceof In) {
               In var26 = (In)var25;
               Seq list = var26.list();
               if (hasNullLiteral$1(list)) {
                  return scala.None..MODULE$;
               }
            }
         }

         if (var15) {
            Expression var28 = var16.child();
            if (var28 instanceof InSet) {
               InSet var29 = (InSet)var28;
               Set list = var29.hset();
               if (list.contains((Object)null)) {
                  return scala.None..MODULE$;
               }
            }
         }

         if (expr instanceof In var31) {
            Expression var32 = var31.value();
            Seq var33 = var31.list();
            if (var32 != null) {
               Option var34 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var32);
               if (!var34.isEmpty()) {
                  Attribute var35 = (Attribute)var34.get();
                  if (var35 != null) {
                     Option var36 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var35);
                     if (!var36.isEmpty()) {
                        String name = (String)var36.get();
                        if (var33 != null) {
                           Option var38 = this.ExtractableLiterals$2(ExtractableLiterals$module$1, ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var33);
                           if (!var38.isEmpty()) {
                              Seq values = (Seq)var38.get();
                              if (useAdvanced$1) {
                                 return new Some(convertInToOr$1(name, values));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var15) {
            Expression var40 = var16.child();
            if (var40 instanceof In) {
               In var41 = (In)var40;
               Expression var42 = var41.value();
               Seq var43 = var41.list();
               if (var42 != null) {
                  Option var44 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var42);
                  if (!var44.isEmpty()) {
                     Attribute var45 = (Attribute)var44.get();
                     if (var45 != null) {
                        Option var46 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var45);
                        if (!var46.isEmpty()) {
                           String name = (String)var46.get();
                           if (var43 != null) {
                              Option var48 = this.ExtractableLiterals$2(ExtractableLiterals$module$1, ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var43);
                              if (!var48.isEmpty()) {
                                 Seq values = (Seq)var48.get();
                                 if (useAdvanced$1) {
                                    return new Some(convertNotInToAnd$1(name, values));
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (expr instanceof InSet) {
            var17 = true;
            var18 = (InSet)expr;
            Expression child = var18.child();
            Set values = var18.hset();
            if (useAdvanced$1 && values.size() > inSetThreshold$1) {
               DataType dataType = child.dataType();
               Seq sortedValues = (Seq)((IterableOnceOps)values.filter((x$11) -> BoxesRunTime.boxToBoolean($anonfun$convertFilters$4(x$11)))).toSeq().sorted(org.apache.spark.sql.catalyst.util.TypeUtils..MODULE$.getInterpretedOrdering(dataType));
               expr = new And(new GreaterThanOrEqual(child, new Literal(sortedValues.head(), dataType)), new LessThanOrEqual(child, new Literal(sortedValues.last(), dataType)));
               continue;
            }
         }

         if (var17) {
            Expression child = var18.child();
            Set var55 = var18.hset();
            if (child != null) {
               Option var56 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(child);
               if (!var56.isEmpty()) {
                  Attribute var57 = (Attribute)var56.get();
                  if (var57 != null) {
                     Option var58 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var57);
                     if (!var58.isEmpty()) {
                        String name = (String)var58.get();
                        if (var55 != null) {
                           Option var60 = this.ExtractableDateValues$2(ExtractableDateValues$module$1, dateFormatter$lzy$1).unapply(var55);
                           if (!var60.isEmpty()) {
                              Seq values = (Seq)var60.get();
                              if (useAdvanced$1) {
                                 DataType var10000 = child.dataType();
                                 DateType var62 = org.apache.spark.sql.types.DateType..MODULE$;
                                 if (var10000 == null) {
                                    if (var62 == null) {
                                       return new Some(convertInToOr$1(name, values));
                                    }
                                 } else if (var10000.equals(var62)) {
                                    return new Some(convertInToOr$1(name, values));
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var15) {
            Expression var63 = var16.child();
            if (var63 instanceof InSet) {
               InSet var64 = (InSet)var63;
               Expression child = var64.child();
               Set var66 = var64.hset();
               if (child != null) {
                  Option var67 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(child);
                  if (!var67.isEmpty()) {
                     Attribute var68 = (Attribute)var67.get();
                     if (var68 != null) {
                        Option var69 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var68);
                        if (!var69.isEmpty()) {
                           String name = (String)var69.get();
                           if (var66 != null) {
                              Option var71 = this.ExtractableDateValues$2(ExtractableDateValues$module$1, dateFormatter$lzy$1).unapply(var66);
                              if (!var71.isEmpty()) {
                                 Seq values = (Seq)var71.get();
                                 if (useAdvanced$1) {
                                    DataType var164 = child.dataType();
                                    DateType var73 = org.apache.spark.sql.types.DateType..MODULE$;
                                    if (var164 == null) {
                                       if (var73 == null) {
                                          return new Some(convertNotInToAnd$1(name, values));
                                       }
                                    } else if (var164.equals(var73)) {
                                       return new Some(convertNotInToAnd$1(name, values));
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var17) {
            Expression var74 = var18.child();
            Set var75 = var18.hset();
            if (var74 != null) {
               Option var76 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var74);
               if (!var76.isEmpty()) {
                  Attribute var77 = (Attribute)var76.get();
                  if (var77 != null) {
                     Option var78 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var77);
                     if (!var78.isEmpty()) {
                        String name = (String)var78.get();
                        if (var75 != null) {
                           Option var80 = this.ExtractableValues$2(ExtractableValues$module$1).unapply(var75);
                           if (!var80.isEmpty()) {
                              Seq values = (Seq)var80.get();
                              if (useAdvanced$1) {
                                 return new Some(convertInToOr$1(name, values));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var15) {
            Expression var82 = var16.child();
            if (var82 instanceof InSet) {
               InSet var83 = (InSet)var82;
               Expression var84 = var83.child();
               Set var85 = var83.hset();
               if (var84 != null) {
                  Option var86 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var84);
                  if (!var86.isEmpty()) {
                     Attribute var87 = (Attribute)var86.get();
                     if (var87 != null) {
                        Option var88 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var87);
                        if (!var88.isEmpty()) {
                           String name = (String)var88.get();
                           if (var85 != null) {
                              Option var90 = this.ExtractableValues$2(ExtractableValues$module$1).unapply(var85);
                              if (!var90.isEmpty()) {
                                 Seq values = (Seq)var90.get();
                                 if (useAdvanced$1) {
                                    return new Some(convertNotInToAnd$1(name, values));
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (expr instanceof BinaryComparison) {
            var19 = true;
            var20 = (BinaryComparison)expr;
            Option var92 = this.SpecialBinaryComparison$2(SpecialBinaryComparison$module$1).unapply(var20);
            if (!var92.isEmpty()) {
               Expression var93 = (Expression)((Tuple2)var92.get())._1();
               Expression var94 = (Expression)((Tuple2)var92.get())._2();
               if (var93 != null) {
                  Option var95 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var93);
                  if (!var95.isEmpty()) {
                     Attribute var96 = (Attribute)var95.get();
                     if (var96 != null) {
                        Option var97 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var96);
                        if (!var97.isEmpty()) {
                           String name = (String)var97.get();
                           if (var94 != null) {
                              Option var99 = this.org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var94);
                              if (!var99.isEmpty()) {
                                 String value = (String)var99.get();
                                 return new Some(name + " " + var20.symbol() + " " + value);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var19) {
            Option var101 = this.SpecialBinaryComparison$2(SpecialBinaryComparison$module$1).unapply(var20);
            if (!var101.isEmpty()) {
               Expression var102 = (Expression)((Tuple2)var101.get())._1();
               Expression var103 = (Expression)((Tuple2)var101.get())._2();
               if (var102 != null) {
                  Option var104 = this.org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var102);
                  if (!var104.isEmpty()) {
                     String value = (String)var104.get();
                     if (var103 != null) {
                        Option var106 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var103);
                        if (!var106.isEmpty()) {
                           Attribute var107 = (Attribute)var106.get();
                           if (var107 != null) {
                              Option var108 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var107);
                              if (!var108.isEmpty()) {
                                 String name = (String)var108.get();
                                 return new Some(value + " " + var20.symbol() + " " + name);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (expr instanceof Contains var110) {
            Expression var111 = var110.left();
            Expression var112 = var110.right();
            if (var111 != null) {
               Option var113 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var111);
               if (!var113.isEmpty()) {
                  Attribute var114 = (Attribute)var113.get();
                  if (var114 != null) {
                     Option var115 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var114);
                     if (!var115.isEmpty()) {
                        String name = (String)var115.get();
                        if (var112 != null) {
                           Option var117 = this.org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var112);
                           if (!var117.isEmpty()) {
                              String value = (String)var117.get();
                              StringOps var10003 = scala.collection.StringOps..MODULE$;
                              Predef var10004 = .MODULE$;
                              String var10005 = this.wildcard();
                              return new Some(name + " like " + var10003.dropRight$extension(var10004.augmentString("\"" + var10005 + scala.collection.StringOps..MODULE$.drop$extension(.MODULE$.augmentString(value), 1)), 1) + this.wildcard() + "\"");
                           }
                        }
                     }
                  }
               }
            }
         }

         if (expr instanceof StartsWith var119) {
            Expression var120 = var119.left();
            Expression var121 = var119.right();
            if (var120 != null) {
               Option var122 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var120);
               if (!var122.isEmpty()) {
                  Attribute var123 = (Attribute)var122.get();
                  if (var123 != null) {
                     Option var124 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var123);
                     if (!var124.isEmpty()) {
                        String name = (String)var124.get();
                        if (var121 != null) {
                           Option var126 = this.org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var121);
                           if (!var126.isEmpty()) {
                              String value = (String)var126.get();
                              return new Some(name + " like " + scala.collection.StringOps..MODULE$.dropRight$extension(.MODULE$.augmentString(value), 1) + this.wildcard() + "\"");
                           }
                        }
                     }
                  }
               }
            }
         }

         if (expr instanceof EndsWith var128) {
            Expression var129 = var128.left();
            Expression var130 = var128.right();
            if (var129 != null) {
               Option var131 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var129);
               if (!var131.isEmpty()) {
                  Attribute var132 = (Attribute)var131.get();
                  if (var132 != null) {
                     Option var133 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var132);
                     if (!var133.isEmpty()) {
                        String name = (String)var133.get();
                        if (var130 != null) {
                           Option var135 = this.org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var130);
                           if (!var135.isEmpty()) {
                              String value = (String)var135.get();
                              return new Some(name + " like \"" + this.wildcard() + scala.collection.StringOps..MODULE$.drop$extension(.MODULE$.augmentString(value), 1));
                           }
                        }
                     }
                  }
               }
            }
         }

         if (expr instanceof And var137) {
            Expression expr1 = var137.left();
            Expression expr2 = var137.right();
            if (useAdvanced$1) {
               Iterable converted = (Iterable)scala.Option..MODULE$.option2Iterable(this.convert$1(expr1, inSetThreshold$1, useAdvanced$1, ExtractAttribute$module$1, SupportedAttribute$module$1, table$1, ExtractableLiterals$module$1, ExtractableDateValues$module$1, ExtractableValues$module$1, SpecialBinaryComparison$module$1, ExtractableLiteral$module$1, dateFormatter$lzy$1)).$plus$plus(this.convert$1(expr2, inSetThreshold$1, useAdvanced$1, ExtractAttribute$module$1, SupportedAttribute$module$1, table$1, ExtractableLiterals$module$1, ExtractableDateValues$module$1, ExtractableValues$module$1, SpecialBinaryComparison$module$1, ExtractableLiteral$module$1, dateFormatter$lzy$1));
               if (converted.isEmpty()) {
                  return scala.None..MODULE$;
               }

               return new Some(converted.mkString("(", " and ", ")"));
            }
         }

         if (expr instanceof Or var141) {
            Expression expr1 = var141.left();
            Expression expr2 = var141.right();
            if (useAdvanced$1) {
               return this.convert$1(expr1, inSetThreshold$1, useAdvanced$1, ExtractAttribute$module$1, SupportedAttribute$module$1, table$1, ExtractableLiterals$module$1, ExtractableDateValues$module$1, ExtractableValues$module$1, SpecialBinaryComparison$module$1, ExtractableLiteral$module$1, dateFormatter$lzy$1).flatMap((left) -> this.convert$1(expr2, inSetThreshold$1, useAdvanced$1, ExtractAttribute$module$1, SupportedAttribute$module$1, table$1, ExtractableLiterals$module$1, ExtractableDateValues$module$1, ExtractableValues$module$1, SpecialBinaryComparison$module$1, ExtractableLiteral$module$1, dateFormatter$lzy$1).map((right) -> "(" + left + " or " + right + ")"));
            }
         }

         if (var15) {
            Expression var144 = var16.child();
            if (var144 instanceof EqualTo) {
               EqualTo var145 = (EqualTo)var144;
               Expression var146 = var145.left();
               Expression var147 = var145.right();
               if (var146 != null) {
                  Option var148 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var146);
                  if (!var148.isEmpty()) {
                     Attribute var149 = (Attribute)var148.get();
                     if (var149 != null) {
                        Option var150 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var149);
                        if (!var150.isEmpty()) {
                           String name = (String)var150.get();
                           if (var147 != null) {
                              Option var152 = this.org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var147);
                              if (!var152.isEmpty()) {
                                 String value = (String)var152.get();
                                 if (useAdvanced$1) {
                                    return new Some(name + " != " + value);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var15) {
            Expression var154 = var16.child();
            if (var154 instanceof EqualTo) {
               EqualTo var155 = (EqualTo)var154;
               Expression var156 = var155.left();
               Expression var157 = var155.right();
               if (var156 != null) {
                  Option var158 = this.org$apache$spark$sql$hive$client$Shim_v2_0$$ExtractableLiteral$2(ExtractableLiteral$module$1, dateFormatter$lzy$1).unapply(var156);
                  if (!var158.isEmpty()) {
                     String value = (String)var158.get();
                     if (var157 != null) {
                        Option var160 = this.ExtractAttribute$2(ExtractAttribute$module$1).unapply(var157);
                        if (!var160.isEmpty()) {
                           Attribute var161 = (Attribute)var160.get();
                           if (var161 != null) {
                              Option var162 = this.SupportedAttribute$2(SupportedAttribute$module$1, table$1).unapply(var161);
                              if (!var162.isEmpty()) {
                                 String name = (String)var162.get();
                                 if (useAdvanced$1) {
                                    return new Some(value + " != " + name);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return scala.None..MODULE$;
      }
   }

   public Shim_v2_0() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
