package org.apache.spark.sql.hive;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.FunctionIdentifier;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.analysis.TableAlreadyExistsException;
import org.apache.spark.sql.catalyst.catalog.BucketSpec;
import org.apache.spark.sql.catalyst.catalog.CatalogColumnStat;
import org.apache.spark.sql.catalyst.catalog.CatalogDatabase;
import org.apache.spark.sql.catalyst.catalog.CatalogFunction;
import org.apache.spark.sql.catalyst.catalog.CatalogStatistics;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTablePartition;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.ExternalCatalog;
import org.apache.spark.sql.execution.datasources.SourceOptions;
import org.apache.spark.sql.hive.client.HiveClient;
import org.apache.spark.sql.hive.client.RawHiveTable;
import org.apache.spark.sql.internal.HiveSerDe;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.MapOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015-g!CA\u0003\u0003\u000f\u0001\u0011qBA\u000e\u0011)\t)\u0005\u0001B\u0001B\u0003%\u0011\u0011\n\u0005\u000b\u0003#\u0002!\u0011!Q\u0001\n\u0005M\u0003bBA1\u0001\u0011\u0005\u00111\r\u0005\u000b\u0003[\u0002\u0001R1A\u0005\u0002\u0005=\u0004\"CA>\u0001\t\u0007I\u0011BA?\u0011!\ty\n\u0001Q\u0001\n\u0005}\u0004bBAQ\u0001\u0011%\u00111\u0015\u0005\b\u0003+\u0004A\u0011BAl\u0011%\tY\u0010\u0001C\u0001\u0003\u000f\ti\u0010C\u0005\u0003\u001c\u0001!\t!a\u0002\u0003\u001e!9!Q\u0006\u0001\u0005\n\t=\u0002b\u0002B\u001d\u0001\u0011\u0005#1\b\u0005\b\u0005\u0017\u0002A\u0011\tB'\u0011\u001d\u0011I\u0006\u0001C!\u00057BqAa\u0018\u0001\t\u0003\u0012\t\u0007C\u0004\u0003f\u0001!\tEa\u001a\t\u000f\t-\u0004\u0001\"\u0011\u0003n!9!1\u000e\u0001\u0005B\t=\u0004b\u0002B;\u0001\u0011\u0005#q\u000f\u0005\b\u0005w\u0002A\u0011\tB?\u0011\u001d\u0011)\t\u0001C\u0005\u0005\u000fCqA!$\u0001\t\u0013\u0011y\tC\u0004\u0003\u000e\u0002!IAa(\t\u000f\tM\u0006\u0001\"\u0003\u00036\"9!1\u0019\u0001\u0005\n\t\u0015\u0007b\u0002Bf\u0001\u0011\u0005#Q\u001a\u0005\b\u00053\u0004A\u0011\tBn\u0011\u001d\u00119\u000f\u0001C\u0005\u0005SDqAa=\u0001\t\u0013\u0011)\u0010C\u0004\u0004\u0004\u0001!\te!\u0002\t\u000f\r%\u0001\u0001\"\u0011\u0004\f!91Q\u0003\u0001\u0005\n\r]\u0001bBB\u000e\u0001\u0011\u00053Q\u0004\u0005\b\u0007_\u0001A\u0011IB\u0019\u0011\u001d\u00199\u0004\u0001C!\u0007sAqaa\u0010\u0001\t\u0013\u0019\t\u0005C\u0004\u0004H\u0001!Ia!\u0013\t\u000f\rE\u0003\u0001\"\u0003\u0004T!91q\u000b\u0001\u0005\n\re\u0003bBB3\u0001\u0011%1q\r\u0005\b\u0007_\u0002A\u0011IB9\u0011\u001d\u00199\b\u0001C!\u0007sBqaa\u001e\u0001\t\u0003\u001ai\bC\u0004\u0004\u0004\u0002!\te!\"\t\u000f\r-\u0005\u0001\"\u0011\u0004\u000e\"91q\u0014\u0001\u0005B\r\u0005\u0006bBBb\u0001\u0011\u00053Q\u0019\u0005\b\u0007;\u0004A\u0011BBp\u0011\u001d\u0019)\u000f\u0001C\u0005\u0007ODqaa;\u0001\t\u0013\u0019i\u000fC\u0004\u0004l\u0002!Ia!>\t\u000f\ru\b\u0001\"\u0011\u0004\u0000\"9A1\u0003\u0001\u0005B\u0011U\u0001b\u0002C\u0014\u0001\u0011\u0005C\u0011\u0006\u0005\b\to\u0001A\u0011\u0002C\u001d\u0011\u001d!I\u0006\u0001C\u0005\t7Bq\u0001b\u0018\u0001\t\u0013!\t\u0007C\u0004\u0005j\u0001!\t\u0005b\u001b\t\u000f\u0011U\u0004\u0001\"\u0011\u0005x!9Aq\u0010\u0001\u0005\n\u0011\u0005\u0005b\u0002CD\u0001\u0011\u0005C\u0011\u0012\u0005\b\t'\u0003A\u0011\tCK\u0011%!\t\u000bAI\u0001\n\u0003!\u0019\u000bC\u0004\u00056\u0002!\t\u0005b.\t\u0013\u0011}\u0006!%A\u0005\u0002\u0011\r\u0006b\u0002Ca\u0001\u0011\u0005C1\u0019\u0005\b\t?\u0004A\u0011\tCq\u0011\u001d!y\u000f\u0001C!\tcDq\u0001\"?\u0001\t\u0003\"Y\u0010C\u0004\u0006\u0002\u0001!\t%b\u0001\t\u000f\u0015-\u0001\u0001\"\u0011\u0006\u000e!9QQ\u0003\u0001\u0005B\u0015]\u0001bBC\u000f\u0001\u0011\u0005SqD\u0004\t\u000bK\t9\u0001#\u0001\u0006(\u0019A\u0011QAA\u0004\u0011\u0003)I\u0003C\u0004\u0002b-#\t!b\u000b\t\u0013\u001552J1A\u0005\u0002\u0015=\u0002\u0002CC\u0019\u0017\u0002\u0006I!a$\t\u0013\u0015M2J1A\u0005\u0002\u0015=\u0002\u0002CC\u001b\u0017\u0002\u0006I!a$\t\u0013\u0015]2J1A\u0005\u0002\u0015=\u0002\u0002CC\u001d\u0017\u0002\u0006I!a$\t\u0013\u0015m2J1A\u0005\u0002\u0015=\u0002\u0002CC\u001f\u0017\u0002\u0006I!a$\t\u0013\u0015}2J1A\u0005\u0002\u0015=\u0002\u0002CC!\u0017\u0002\u0006I!a$\t\u0013\u0015\r3J1A\u0005\u0002\u0015=\u0002\u0002CC#\u0017\u0002\u0006I!a$\t\u0013\u0015\u001d3J1A\u0005\u0002\u0015=\u0002\u0002CC%\u0017\u0002\u0006I!a$\t\u0013\u0015-3J1A\u0005\u0002\u0015=\u0002\u0002CC'\u0017\u0002\u0006I!a$\t\u0013\u0015=3J1A\u0005\u0002\u0015=\u0002\u0002CC)\u0017\u0002\u0006I!a$\t\u0013\u0015M3J1A\u0005\u0002\u0015=\u0002\u0002CC+\u0017\u0002\u0006I!a$\t\u0013\u0015]3J1A\u0005\u0002\u0015=\u0002\u0002CC-\u0017\u0002\u0006I!a$\t\u0013\u0015m3J1A\u0005\u0002\u0015=\u0002\u0002CC/\u0017\u0002\u0006I!a$\t\u0013\u0015}3J1A\u0005\u0002\u0015=\u0002\u0002CC1\u0017\u0002\u0006I!a$\t\u0013\u0015\r4J1A\u0005\u0002\u0015=\u0002\u0002CC3\u0017\u0002\u0006I!a$\t\u0013\u0015\u001d4J1A\u0005\u0002\u0015=\u0002\u0002CC5\u0017\u0002\u0006I!a$\t\u0013\u0015-4J1A\u0005\u0002\u0015=\u0002\u0002CC7\u0017\u0002\u0006I!a$\t\u0013\u0015=4J1A\u0005\u0002\u0015=\u0002\u0002CC9\u0017\u0002\u0006I!a$\t\u0013\u0015M4J1A\u0005\u0002\u0015=\u0002\u0002CC;\u0017\u0002\u0006I!a$\t\u0013\u0015]4J1A\u0005\u0002\u0015=\u0002\u0002CC=\u0017\u0002\u0006I!a$\t\u0013\u0015m4J1A\u0005\u0002\u0015=\u0002\u0002CC?\u0017\u0002\u0006I!a$\t\u0013\u0015}4J1A\u0005\u0002\u0015=\u0002\u0002CCA\u0017\u0002\u0006I!a$\t\u0013\u0015\r5J1A\u0005\u0002\u0005u\u0004\u0002CCC\u0017\u0002\u0006I!a \t\u0013\u0015\u001d5J1A\u0005\u0002\u0005u\u0004\u0002CCE\u0017\u0002\u0006I!a \t\u0013\u0015-5J1A\u0005\u0002\u00155\u0005\u0002CCH\u0017\u0002\u0006IAa*\t\u000f\u0015E5\n\"\u0003\u0006\u0014\"9Q\u0011U&\u0005\n\u0015\r\u0006bBCU\u0017\u0012%Q1\u0016\u0005\n\u000bo[E\u0011AA\b\u000bsC\u0011\"\"0L\t\u0003\ty!b0\u0003'!Kg/Z#yi\u0016\u0014h.\u00197DCR\fGn\\4\u000b\t\u0005%\u00111B\u0001\u0005Q&4XM\u0003\u0003\u0002\u000e\u0005=\u0011aA:rY*!\u0011\u0011CA\n\u0003\u0015\u0019\b/\u0019:l\u0015\u0011\t)\"a\u0006\u0002\r\u0005\u0004\u0018m\u00195f\u0015\t\tI\"A\u0002pe\u001e\u001cr\u0001AA\u000f\u0003S\tI\u0004\u0005\u0003\u0002 \u0005\u0015RBAA\u0011\u0015\t\t\u0019#A\u0003tG\u0006d\u0017-\u0003\u0003\u0002(\u0005\u0005\"AB!osJ+g\r\u0005\u0003\u0002,\u0005URBAA\u0017\u0015\u0011\ty#!\r\u0002\u000f\r\fG/\u00197pO*!\u00111GA\u0006\u0003!\u0019\u0017\r^1msN$\u0018\u0002BA\u001c\u0003[\u0011q\"\u0012=uKJt\u0017\r\\\"bi\u0006dwn\u001a\t\u0005\u0003w\t\t%\u0004\u0002\u0002>)!\u0011qHA\b\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA\"\u0003{\u0011q\u0001T8hO&tw-\u0001\u0003d_:47\u0001\u0001\t\u0005\u0003\u0017\ni%\u0004\u0002\u0002\u0010%!\u0011qJA\b\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0006iC\u0012|w\u000e]\"p]\u001a\u0004B!!\u0016\u0002^5\u0011\u0011q\u000b\u0006\u0005\u0003\u000b\nIF\u0003\u0003\u0002\\\u0005M\u0011A\u00025bI>|\u0007/\u0003\u0003\u0002`\u0005]#!D\"p]\u001aLw-\u001e:bi&|g.\u0001\u0004=S:LGO\u0010\u000b\u0007\u0003K\nI'a\u001b\u0011\u0007\u0005\u001d\u0004!\u0004\u0002\u0002\b!9\u0011QI\u0002A\u0002\u0005%\u0003bBA)\u0007\u0001\u0007\u00111K\u0001\u0007G2LWM\u001c;\u0016\u0005\u0005E\u0004\u0003BA:\u0003oj!!!\u001e\u000b\t\u00055\u0014qA\u0005\u0005\u0003s\n)H\u0001\u0006ISZ,7\t\\5f]R\f\u0001c\u00197jK:$X\t_2faRLwN\\:\u0016\u0005\u0005}\u0004CBAA\u0003\u0017\u000by)\u0004\u0002\u0002\u0004*!\u0011QQAD\u0003%IW.\\;uC\ndWM\u0003\u0003\u0002\n\u0006\u0005\u0012AC2pY2,7\r^5p]&!\u0011QRAB\u0005\r\u0019V\r\u001e\t\u0005\u0003#\u000bY*\u0004\u0002\u0002\u0014*!\u0011QSAL\u0003\u0011a\u0017M\\4\u000b\u0005\u0005e\u0015\u0001\u00026bm\u0006LA!!(\u0002\u0014\n11\u000b\u001e:j]\u001e\f\u0011c\u00197jK:$X\t_2faRLwN\\:!\u0003EI7o\u00117jK:$X\t_2faRLwN\u001c\u000b\u0005\u0003K\u000bY\u000b\u0005\u0003\u0002 \u0005\u001d\u0016\u0002BAU\u0003C\u0011qAQ8pY\u0016\fg\u000eC\u0004\u0002.\u001e\u0001\r!a,\u0002\u0003\u0015\u0004B!!-\u0002B:!\u00111WA_\u001d\u0011\t),a/\u000e\u0005\u0005]&\u0002BA]\u0003\u000f\na\u0001\u0010:p_Rt\u0014BAA\u0012\u0013\u0011\ty,!\t\u0002\u000fA\f7m[1hK&!\u00111YAc\u0005%!\u0006N]8xC\ndWM\u0003\u0003\u0002@\u0006\u0005\u0002fA\u0004\u0002JB!\u00111ZAi\u001b\t\tiM\u0003\u0003\u0002P\u0006\u0005\u0012AC1o]>$\u0018\r^5p]&!\u00111[Ag\u0005\u001d!\u0018-\u001b7sK\u000e\f!b^5uQ\u000ec\u0017.\u001a8u+\u0011\tI.a8\u0015\t\u0005m\u0017\u0011\u001f\t\u0005\u0003;\fy\u000e\u0004\u0001\u0005\u000f\u0005\u0005\bB1\u0001\u0002d\n\tA+\u0005\u0003\u0002f\u0006-\b\u0003BA\u0010\u0003OLA!!;\u0002\"\t9aj\u001c;iS:<\u0007\u0003BA\u0010\u0003[LA!a<\u0002\"\t\u0019\u0011I\\=\t\u0011\u0005M\b\u0002\"a\u0001\u0003k\fAAY8esB1\u0011qDA|\u00037LA!!?\u0002\"\tAAHY=oC6,g(A\u0006hKR\u0014\u0016m\u001e+bE2,GCBA\u0000\u0005\u000b\u00119\u0002\u0005\u0003\u0002,\t\u0005\u0011\u0002\u0002B\u0002\u0003[\u0011AbQ1uC2|w\rV1cY\u0016DqAa\u0002\n\u0001\u0004\u0011I!\u0001\u0002eEB!!1\u0002B\n\u001d\u0011\u0011iAa\u0004\u0011\t\u0005U\u0016\u0011E\u0005\u0005\u0005#\t\t#\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003;\u0013)B\u0003\u0003\u0003\u0012\u0005\u0005\u0002b\u0002B\r\u0013\u0001\u0007!\u0011B\u0001\u0006i\u0006\u0014G.Z\u0001\u0014O\u0016$(+Y<UC\ndWm\u001d\"z\u001d\u0006lWm\u001d\u000b\u0007\u0005?\u0011)Ca\n\u0011\r\u0005E&\u0011EA\u0000\u0013\u0011\u0011\u0019#!2\u0003\u0007M+\u0017\u000fC\u0004\u0003\b)\u0001\rA!\u0003\t\u000f\t%\"\u00021\u0001\u0003,\u00051A/\u00192mKN\u0004b!!-\u0003\"\t%\u0011!\u0006<fe&4\u0017\u0010V1cY\u0016\u0004&o\u001c9feRLWm\u001d\u000b\u0005\u0005c\u00119\u0004\u0005\u0003\u0002 \tM\u0012\u0002\u0002B\u001b\u0003C\u0011A!\u00168ji\"9!\u0011D\u0006A\u0002\u0005}\u0018AD2sK\u0006$X\rR1uC\n\f7/\u001a\u000b\u0007\u0005c\u0011iDa\u0012\t\u000f\t}B\u00021\u0001\u0003B\u0005aAM\u0019#fM&t\u0017\u000e^5p]B!\u00111\u0006B\"\u0013\u0011\u0011)%!\f\u0003\u001f\r\u000bG/\u00197pO\u0012\u000bG/\u00192bg\u0016DqA!\u0013\r\u0001\u0004\t)+\u0001\bjO:|'/Z%g\u000bbL7\u000f^:\u0002\u0019\u0011\u0014x\u000e\u001d#bi\u0006\u0014\u0017m]3\u0015\u0011\tE\"q\nB)\u0005+BqAa\u0002\u000e\u0001\u0004\u0011I\u0001C\u0004\u0003T5\u0001\r!!*\u0002#%<gn\u001c:f\u0013\u001atu\u000e^#ySN$8\u000fC\u0004\u0003X5\u0001\r!!*\u0002\u000f\r\f7oY1eK\u0006i\u0011\r\u001c;fe\u0012\u000bG/\u00192bg\u0016$BA!\r\u0003^!9!q\b\bA\u0002\t\u0005\u0013aC4fi\u0012\u000bG/\u00192bg\u0016$BA!\u0011\u0003d!9!qA\bA\u0002\t%\u0011A\u00043bi\u0006\u0014\u0017m]3Fq&\u001cHo\u001d\u000b\u0005\u0003K\u0013I\u0007C\u0004\u0003\bA\u0001\rA!\u0003\u0002\u001b1L7\u000f\u001e#bi\u0006\u0014\u0017m]3t)\t\u0011Y\u0003\u0006\u0003\u0003,\tE\u0004b\u0002B:%\u0001\u0007!\u0011B\u0001\ba\u0006$H/\u001a:o\u0003I\u0019X\r^\"veJ,g\u000e\u001e#bi\u0006\u0014\u0017m]3\u0015\t\tE\"\u0011\u0010\u0005\b\u0005\u000f\u0019\u0002\u0019\u0001B\u0005\u0003-\u0019'/Z1uKR\u000b'\r\\3\u0015\r\tE\"q\u0010BB\u0011\u001d\u0011\t\t\u0006a\u0001\u0003\u007f\fq\u0002^1cY\u0016$UMZ5oSRLwN\u001c\u0005\b\u0005\u0013\"\u0002\u0019AAS\u0003U\u0019'/Z1uK\u0012\u000bG/Y*pkJ\u001cW\rV1cY\u0016$bA!\r\u0003\n\n-\u0005b\u0002B\r+\u0001\u0007\u0011q \u0005\b\u0005\u0013*\u0002\u0019AAS\u0003U!\u0018M\u00197f\u001b\u0016$\u0018\rV8UC\ndW\r\u0015:paN$BA!%\u0003\u001eBA!1\u0013BM\u0005\u0013\u0011I!\u0004\u0002\u0003\u0016*!!qSAD\u0003\u001diW\u000f^1cY\u0016LAAa'\u0003\u0016\n\u0019Q*\u00199\t\u000f\tea\u00031\u0001\u0002\u0000R1!\u0011\u0013BQ\u0005GCqA!\u0007\u0018\u0001\u0004\ty\u0010C\u0004\u0003&^\u0001\rAa*\u0002\rM\u001c\u0007.Z7b!\u0011\u0011IKa,\u000e\u0005\t-&\u0002\u0002BW\u0003\u0017\tQ\u0001^=qKNLAA!-\u0003,\nQ1\u000b\u001e:vGR$\u0016\u0010]3\u0002!\u0011,g-Y;miR\u000b'\r\\3QCRDG\u0003\u0002B\u0005\u0005oCqA!/\u0019\u0001\u0004\u0011Y,\u0001\u0006uC\ndW-\u00133f]R\u0004BA!0\u0003@6\u0011\u0011\u0011G\u0005\u0005\u0005\u0003\f\tDA\bUC\ndW-\u00133f]RLg-[3s\u0003E\u0019\u0018M^3UC\ndW-\u00138u_\"Kg/\u001a\u000b\u0007\u0005c\u00119M!3\t\u000f\t\u0005\u0015\u00041\u0001\u0002\u0000\"9!\u0011J\rA\u0002\u0005\u0015\u0016!\u00033s_B$\u0016M\u00197f))\u0011\tDa4\u0003R\nM'Q\u001b\u0005\b\u0005\u000fQ\u0002\u0019\u0001B\u0005\u0011\u001d\u0011IB\u0007a\u0001\u0005\u0013AqAa\u0015\u001b\u0001\u0004\t)\u000bC\u0004\u0003Xj\u0001\r!!*\u0002\u000bA,(oZ3\u0002\u0017I,g.Y7f)\u0006\u0014G.\u001a\u000b\t\u0005c\u0011iNa8\u0003d\"9!qA\u000eA\u0002\t%\u0001b\u0002Bq7\u0001\u0007!\u0011B\u0001\b_2$g*Y7f\u0011\u001d\u0011)o\u0007a\u0001\u0005\u0013\tqA\\3x\u001d\u0006lW-A\u000ehKRdunY1uS>tgI]8n'R|'/Y4f!J|\u0007o\u001d\u000b\u0005\u0005W\u0014\t\u0010\u0005\u0004\u0002 \t5(\u0011B\u0005\u0005\u0005_\f\tC\u0001\u0004PaRLwN\u001c\u0005\b\u00053a\u0002\u0019AA\u0000\u0003q)\b\u000fZ1uK2{7-\u0019;j_:Len\u0015;pe\u0006<W\r\u0015:paN$bAa>\u0003~\n}\b\u0003BA\u0016\u0005sLAAa?\u0002.\t!2)\u0019;bY><7\u000b^8sC\u001e,gi\u001c:nCRDqA!\u0007\u001e\u0001\u0004\ty\u0010C\u0004\u0004\u0002u\u0001\rAa;\u0002\u000f9,w\u000fU1uQ\u0006Q\u0011\r\u001c;feR\u000b'\r\\3\u0015\t\tE2q\u0001\u0005\b\u0005\u0003s\u0002\u0019AA\u0000\u0003Q\tG\u000e^3s)\u0006\u0014G.\u001a#bi\u0006\u001c6\r[3nCRA!\u0011GB\u0007\u0007\u001f\u0019\t\u0002C\u0004\u0003\b}\u0001\rA!\u0003\t\u000f\teq\u00041\u0001\u0003\n!911C\u0010A\u0002\t\u001d\u0016!\u00048fo\u0012\u000bG/Y*dQ\u0016l\u0017-A\bsK6|g/Z\"pY2\fG/[8o)\u0011\u00119k!\u0007\t\u000f\t\u0015\u0006\u00051\u0001\u0003(\u0006y\u0011\r\u001c;feR\u000b'\r\\3Ti\u0006$8\u000f\u0006\u0005\u00032\r}1\u0011EB\u0012\u0011\u001d\u00119!\ta\u0001\u0005\u0013AqA!\u0007\"\u0001\u0004\u0011I\u0001C\u0004\u0004&\u0005\u0002\raa\n\u0002\u000bM$\u0018\r^:\u0011\r\u0005}!Q^B\u0015!\u0011\tYca\u000b\n\t\r5\u0012Q\u0006\u0002\u0012\u0007\u0006$\u0018\r\\8h'R\fG/[:uS\u000e\u001c\u0018\u0001C4fiR\u000b'\r\\3\u0015\r\u0005}81GB\u001b\u0011\u001d\u00119A\ta\u0001\u0005\u0013AqA!\u0007#\u0001\u0004\u0011I!A\bhKR$\u0016M\u00197fg\nKh*Y7f)\u0019\u0011yba\u000f\u0004>!9!qA\u0012A\u0002\t%\u0001b\u0002B\u0015G\u0001\u0007!1F\u0001\u0015e\u0016\u001cHo\u001c:f)\u0006\u0014G.Z'fi\u0006$\u0017\r^1\u0015\t\u0005}81\t\u0005\b\u0007\u000b\"\u0003\u0019AA\u0000\u0003)Ig\u000e];u)\u0006\u0014G.Z\u0001\u000ee\u0016|'\u000fZ3s'\u000eDW-\\1\u0015\r\t\u001d61JB'\u0011\u001d\u0011)+\na\u0001\u0005OCqaa\u0014&\u0001\u0004\u0011Y#A\bqCJ$8i\u001c7v[:t\u0015-\\3t\u0003U\u0011Xm\u001d;pe\u0016D\u0015N^3TKJ$W\rV1cY\u0016$B!a@\u0004V!9!\u0011\u0004\u0014A\u0002\u0005}\u0018\u0001H4fiN\u001b\u0007.Z7b\rJ|W\u000eV1cY\u0016\u0004&o\u001c9feRLWm\u001d\u000b\u0005\u00077\u001ai\u0006\u0005\u0004\u0002 \t5(q\u0015\u0005\b\u0007?:\u0003\u0019AB1\u0003=!\u0018M\u00197f!J|\u0007/\u001a:uS\u0016\u001c\b\u0003\u0003B\u0006\u0007G\u0012IA!\u0003\n\t\tm%QC\u0001\u0017e\u0016\u001cHo\u001c:f\t\u0006$\u0018mU8ve\u000e,G+\u00192mKR1\u0011q`B5\u0007WBqA!\u0007)\u0001\u0004\ty\u0010C\u0004\u0004n!\u0002\rA!\u0003\u0002\u0011A\u0014xN^5eKJ\f1\u0002^1cY\u0016,\u00050[:ugR1\u0011QUB:\u0007kBqAa\u0002*\u0001\u0004\u0011I\u0001C\u0004\u0003\u001a%\u0002\rA!\u0003\u0002\u00151L7\u000f\u001e+bE2,7\u000f\u0006\u0003\u0003,\rm\u0004b\u0002B\u0004U\u0001\u0007!\u0011\u0002\u000b\u0007\u0005W\u0019yh!!\t\u000f\t\u001d1\u00061\u0001\u0003\n!9!1O\u0016A\u0002\t%\u0011!\u00037jgR4\u0016.Z<t)\u0019\u0011Yca\"\u0004\n\"9!q\u0001\u0017A\u0002\t%\u0001b\u0002B:Y\u0001\u0007!\u0011B\u0001\nY>\fG\rV1cY\u0016$BB!\r\u0004\u0010\u000eE51SBL\u00077CqAa\u0002.\u0001\u0004\u0011I\u0001C\u0004\u0003\u001a5\u0002\rA!\u0003\t\u000f\rUU\u00061\u0001\u0003\n\u0005AAn\\1e!\u0006$\b\u000eC\u0004\u0004\u001a6\u0002\r!!*\u0002\u0017%\u001cxJ^3soJLG/\u001a\u0005\b\u0007;k\u0003\u0019AAS\u0003)I7o\u0015:d\u0019>\u001c\u0017\r\\\u0001\u000eY>\fG\rU1si&$\u0018n\u001c8\u0015!\tE21UBS\u0007O\u001bIka/\u0004>\u000e\u0005\u0007b\u0002B\u0004]\u0001\u0007!\u0011\u0002\u0005\b\u00053q\u0003\u0019\u0001B\u0005\u0011\u001d\u0019)J\fa\u0001\u0005\u0013Aqaa+/\u0001\u0004\u0019i+A\u0005qCJ$\u0018\u000e^5p]B!1qVB[\u001d\u0011\tYc!-\n\t\rM\u0016QF\u0001\r\u0007\u0006$\u0018\r\\8h)f\u0004Xm]\u0005\u0005\u0007o\u001bIL\u0001\nUC\ndW\rU1si&$\u0018n\u001c8Ta\u0016\u001c'\u0002BBZ\u0003[Aqa!'/\u0001\u0004\t)\u000bC\u0004\u0004@:\u0002\r!!*\u0002#%t\u0007.\u001a:jiR\u000b'\r\\3Ta\u0016\u001c7\u000fC\u0004\u0004\u001e:\u0002\r!!*\u0002+1|\u0017\r\u001a#z]\u0006l\u0017n\u0019)beRLG/[8ogRq!\u0011GBd\u0007\u0013\u001cYm!4\u0004P\u000eM\u0007b\u0002B\u0004_\u0001\u0007!\u0011\u0002\u0005\b\u00053y\u0003\u0019\u0001B\u0005\u0011\u001d\u0019)j\fa\u0001\u0005\u0013Aqaa+0\u0001\u0004\u0019i\u000bC\u0004\u0004R>\u0002\r!!*\u0002\u000fI,\u0007\u000f\\1dK\"91Q[\u0018A\u0002\r]\u0017!\u00028v[\u0012\u0003\u0006\u0003BA\u0010\u00073LAaa7\u0002\"\t\u0019\u0011J\u001c;\u00021Q|W*\u001a;b'R|'/\u001a)beRLG/[8o'B,7\r\u0006\u0003\u0004.\u000e\u0005\bbBBra\u0001\u00071QV\u0001\u0005gB,7-\u0001\u000fck&dG\rT8xKJ\u001c\u0015m]3QCJ$8i\u001c7OC6,W*\u00199\u0015\t\r\u00054\u0011\u001e\u0005\b\u00053\t\u0004\u0019AA\u0000\u0003Q\u0011Xm\u001d;pe\u0016\u0004\u0016M\u001d;ji&|gn\u00159fGR11QVBx\u0007cDqaa93\u0001\u0004\u0019i\u000bC\u0004\u0004tJ\u0002\ra!\u0019\u0002\u0015A\f'\u000f^\"pY6\u000b\u0007\u000f\u0006\u0004\u0004.\u000e]8\u0011 \u0005\b\u0007G\u001c\u0004\u0019ABW\u0011\u001d\u0019Yp\ra\u0001\u0005W\t\u0001\u0002]1si\u000e{Gn]\u0001\u0011GJ,\u0017\r^3QCJ$\u0018\u000e^5p]N$\"B!\r\u0005\u0002\u0011\rAQ\u0001C\t\u0011\u001d\u00119\u0001\u000ea\u0001\u0005\u0013AqA!\u00075\u0001\u0004\u0011I\u0001C\u0004\u0005\bQ\u0002\r\u0001\"\u0003\u0002\u000bA\f'\u000f^:\u0011\r\u0005E&\u0011\u0005C\u0006!\u0011\tY\u0003\"\u0004\n\t\u0011=\u0011Q\u0006\u0002\u0016\u0007\u0006$\u0018\r\\8h)\u0006\u0014G.\u001a)beRLG/[8o\u0011\u001d\u0011I\u0005\u000ea\u0001\u0003K\u000ba\u0002\u001a:paB\u000b'\u000f^5uS>t7\u000f\u0006\b\u00032\u0011]A\u0011\u0004C\u000e\t?!\t\u0003b\t\t\u000f\t\u001dQ\u00071\u0001\u0003\n!9!\u0011D\u001bA\u0002\t%\u0001b\u0002C\u0004k\u0001\u0007AQ\u0004\t\u0007\u0003c\u0013\tc!,\t\u000f\tMS\u00071\u0001\u0002&\"9!q[\u001bA\u0002\u0005\u0015\u0006b\u0002C\u0013k\u0001\u0007\u0011QU\u0001\u000be\u0016$\u0018-\u001b8ECR\f\u0017\u0001\u0005:f]\u0006lW\rU1si&$\u0018n\u001c8t))\u0011\t\u0004b\u000b\u0005.\u0011=B1\u0007\u0005\b\u0005\u000f1\u0004\u0019\u0001B\u0005\u0011\u001d\u0011IB\u000ea\u0001\u0005\u0013Aq\u0001\"\r7\u0001\u0004!i\"A\u0003ta\u0016\u001c7\u000fC\u0004\u00056Y\u0002\r\u0001\"\b\u0002\u00119,wo\u00159fGN\f\u0001D]3oC6,\u0007+\u0019:uSRLwN\u001c#je\u0016\u001cGo\u001c:z))!Y\u0004b\u0012\u0005P\u0011MCQ\u000b\t\u0005\t{!\u0019%\u0004\u0002\u0005@)!A\u0011IA-\u0003\t17/\u0003\u0003\u0005F\u0011}\"\u0001\u0002)bi\"Dq\u0001\"\u00118\u0001\u0004!I\u0005\u0005\u0003\u0005>\u0011-\u0013\u0002\u0002C'\t\u007f\u0011!BR5mKNK8\u000f^3n\u0011\u001d!\tf\u000ea\u0001\tw\t\u0011\u0002^1cY\u0016\u0004\u0016\r\u001e5\t\u000f\rmx\u00071\u0001\u0003,!9AqK\u001cA\u0002\r5\u0016a\u00028foN\u0003XmY\u0001\u0012gR\fGo\u001d+p!J|\u0007/\u001a:uS\u0016\u001cH\u0003BB1\t;Bqa!\n9\u0001\u0004\u0019I#A\nti\u0006$8O\u0012:p[B\u0013x\u000e]3si&,7\u000f\u0006\u0004\u0004(\u0011\rDq\r\u0005\b\tKJ\u0004\u0019AB1\u0003)\u0001(o\u001c9feRLWm\u001d\u0005\b\u00053I\u0004\u0019\u0001B\u0005\u0003=\tG\u000e^3s!\u0006\u0014H/\u001b;j_:\u001cH\u0003\u0003B\u0019\t[\"y\u0007\"\u001d\t\u000f\t\u001d!\b1\u0001\u0003\n!9!\u0011\u0004\u001eA\u0002\t%\u0001b\u0002C:u\u0001\u0007A\u0011B\u0001\t]\u0016<\b+\u0019:ug\u0006aq-\u001a;QCJ$\u0018\u000e^5p]RAA1\u0002C=\tw\"i\bC\u0004\u0003\bm\u0002\rA!\u0003\t\u000f\te1\b1\u0001\u0003\n!911]\u001eA\u0002\r5\u0016\u0001\u0007:fgR|'/\u001a)beRLG/[8o\u001b\u0016$\u0018\rZ1uCR1A1\u0002CB\t\u000bCqaa+=\u0001\u0004!Y\u0001C\u0004\u0003\u001aq\u0002\r!a@\u0002%\u001d,G\u000fU1si&$\u0018n\u001c8PaRLwN\u001c\u000b\t\t\u0017#i\tb$\u0005\u0012B1\u0011q\u0004Bw\t\u0017AqAa\u0002>\u0001\u0004\u0011I\u0001C\u0004\u0003\u001au\u0002\rA!\u0003\t\u000f\r\rX\b1\u0001\u0004.\u0006\u0011B.[:u!\u0006\u0014H/\u001b;j_:t\u0015-\\3t)!\u0011Y\u0003b&\u0005\u001a\u0012m\u0005b\u0002B\u0004}\u0001\u0007!\u0011\u0002\u0005\b\u00053q\u0004\u0019\u0001B\u0005\u0011%!iJ\u0010I\u0001\u0002\u0004!y*A\u0006qCJ$\u0018.\u00197Ta\u0016\u001c\u0007CBA\u0010\u0005[\u001ci+\u0001\u000fmSN$\b+\u0019:uSRLwN\u001c(b[\u0016\u001cH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0011\u0015&\u0006\u0002CP\tO[#\u0001\"+\u0011\t\u0011-F\u0011W\u0007\u0003\t[SA\u0001b,\u0002N\u0006IQO\\2iK\u000e\\W\rZ\u0005\u0005\tg#iKA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fa\u0002\\5tiB\u000b'\u000f^5uS>t7\u000f\u0006\u0005\u0005\n\u0011eF1\u0018C_\u0011\u001d\u00119\u0001\u0011a\u0001\u0005\u0013AqA!\u0007A\u0001\u0004\u0011I\u0001C\u0005\u0005\u001e\u0002\u0003\n\u00111\u0001\u0005 \u0006AB.[:u!\u0006\u0014H/\u001b;j_:\u001cH\u0005Z3gCVdG\u000fJ\u001a\u0002-1L7\u000f\u001e)beRLG/[8og\nKh)\u001b7uKJ$\"\u0002\"\u0003\u0005F\u0012\u001dG\u0011\u001aCn\u0011\u001d\u00119A\u0011a\u0001\u0005\u0013AqA!\u0007C\u0001\u0004\u0011I\u0001C\u0004\u0005L\n\u0003\r\u0001\"4\u0002\u0015A\u0014X\rZ5dCR,7\u000f\u0005\u0004\u00022\n\u0005Bq\u001a\t\u0005\t#$9.\u0004\u0002\u0005T*!AQ[A\u0019\u0003-)\u0007\u0010\u001d:fgNLwN\\:\n\t\u0011eG1\u001b\u0002\u000b\u000bb\u0004(/Z:tS>t\u0007b\u0002Co\u0005\u0002\u0007!\u0011B\u0001\u0012I\u00164\u0017-\u001e7u)&lWMW8oK&#\u0017AD2sK\u0006$XMR;oGRLwN\u001c\u000b\u0007\u0005c!\u0019\u000f\":\t\u000f\t\u001d1\t1\u0001\u0003\n!9Aq]\"A\u0002\u0011%\u0018A\u00044v]\u000e$UMZ5oSRLwN\u001c\t\u0005\u0003W!Y/\u0003\u0003\u0005n\u00065\"aD\"bi\u0006dwn\u001a$v]\u000e$\u0018n\u001c8\u0002\u0019\u0011\u0014x\u000e\u001d$v]\u000e$\u0018n\u001c8\u0015\r\tEB1\u001fC{\u0011\u001d\u00119\u0001\u0012a\u0001\u0005\u0013Aq\u0001b>E\u0001\u0004\u0011I!\u0001\u0003oC6,\u0017!D1mi\u0016\u0014h)\u001e8di&|g\u000e\u0006\u0004\u00032\u0011uHq \u0005\b\u0005\u000f)\u0005\u0019\u0001B\u0005\u0011\u001d!9/\u0012a\u0001\tS\faB]3oC6,g)\u001e8di&|g\u000e\u0006\u0005\u00032\u0015\u0015QqAC\u0005\u0011\u001d\u00119A\u0012a\u0001\u0005\u0013AqA!9G\u0001\u0004\u0011I\u0001C\u0004\u0003f\u001a\u0003\rA!\u0003\u0002\u0017\u001d,GOR;oGRLwN\u001c\u000b\u0007\tS,y!\"\u0005\t\u000f\t\u001dq\t1\u0001\u0003\n!9Q1C$A\u0002\t%\u0011\u0001\u00034v]\u000et\u0015-\\3\u0002\u001d\u0019,hn\u0019;j_:,\u00050[:ugR1\u0011QUC\r\u000b7AqAa\u0002I\u0001\u0004\u0011I\u0001C\u0004\u0006\u0014!\u0003\rA!\u0003\u0002\u001b1L7\u000f\u001e$v]\u000e$\u0018n\u001c8t)\u0019\u0011Y#\"\t\u0006$!9!qA%A\u0002\t%\u0001b\u0002B:\u0013\u0002\u0007!\u0011B\u0001\u0014\u0011&4X-\u0012=uKJt\u0017\r\\\"bi\u0006dwn\u001a\t\u0004\u0003OZ5cA&\u0002\u001eQ\u0011QqE\u0001\u0011'B\u000b%kS0T#2{\u0006KU#G\u0013b+\"!a$\u0002#M\u0003\u0016IU&`'Fcu\f\u0015*F\r&C\u0006%A\tE\u0003R\u000b5kT+S\u0007\u0016{\u0006KU#G\u0013b\u000b!\u0003R!U\u0003N{UKU\"F?B\u0013VIR%YA\u0005\u0019B)\u0011+B'>+&kQ#`!J{e+\u0013#F%\u0006!B)\u0011+B'>+&kQ#`!J{e+\u0013#F%\u0002\n\u0011\u0003R!U\u0003N{UKU\"F?N\u001b\u0005*R'B\u0003I!\u0015\tV!T\u001fV\u00136)R0T\u0007\"+U*\u0011\u0011\u00021\u0011\u000bE+Q*P+J\u001bUiX*D\u0011\u0016k\u0015i\u0018)S\u000b\u001aK\u0005,A\rE\u0003R\u000b5kT+S\u0007\u0016{6k\u0011%F\u001b\u0006{\u0006KU#G\u0013b\u0003\u0013!\b#B)\u0006\u001bv*\u0016*D\u000b~\u001b6\tS#N\u0003~sU+\u0014)B%R\u001bu\nT*\u0002=\u0011\u000bE+Q*P+J\u001bUiX*D\u0011\u0016k\u0015i\u0018(V\u001bB\u000b%\u000bV\"P\u0019N\u0003\u0013!\b#B)\u0006\u001bv*\u0016*D\u000b~\u001b6\tS#N\u0003~sU+T*P%R\u001bu\nT*\u0002=\u0011\u000bE+Q*P+J\u001bUiX*D\u0011\u0016k\u0015i\u0018(V\u001bN{%\u000bV\"P\u0019N\u0003\u0013\u0001\b#B)\u0006\u001bv*\u0016*D\u000b~\u001b6\tS#N\u0003~sU+\u0014\"V\u0007.+EkU\u0001\u001e\t\u0006#\u0016iU(V%\u000e+ulU\"I\u000b6\u000buLT+N\u0005V\u001b5*\u0012+TA\u0005yB)\u0011+B'>+&kQ#`'\u000eCU)T!`\u001dVk%)V\"L\u000bR\u001bu\nT*\u0002A\u0011\u000bE+Q*P+J\u001bUiX*D\u0011\u0016k\u0015i\u0018(V\u001b\n+6iS#U\u0007>c5\u000bI\u0001\u001e\t\u0006#\u0016iU(V%\u000e+ulU\"I\u000b6\u000bu\fU!S)~\u0003&+\u0012$J1\u0006qB)\u0011+B'>+&kQ#`'\u000eCU)T!`!\u0006\u0013Fk\u0018)S\u000b\u001aK\u0005\fI\u0001!\t\u0006#\u0016iU(V%\u000e+ulU\"I\u000b6\u000bu\fU!S)\u000e{Ej\u0018)S\u000b\u001aK\u0005,A\u0011E\u0003R\u000b5kT+S\u0007\u0016{6k\u0011%F\u001b\u0006{\u0006+\u0011*U\u0007>cu\f\u0015*F\r&C\u0006%\u0001\u0012E\u0003R\u000b5kT+S\u0007\u0016{6k\u0011%F\u001b\u0006{&)V\"L\u000bR\u001bu\nT0Q%\u00163\u0015\nW\u0001$\t\u0006#\u0016iU(V%\u000e+ulU\"I\u000b6\u000buLQ+D\u0017\u0016#6i\u0014'`!J+e)\u0013-!\u0003\u0001\"\u0015\tV!T\u001fV\u00136)R0T\u0007\"+U*Q0T\u001fJ#6i\u0014'`!J+e)\u0013-\u0002C\u0011\u000bE+Q*P+J\u001bUiX*D\u0011\u0016k\u0015iX*P%R\u001bu\nT0Q%\u00163\u0015\n\u0017\u0011\u0002#M#\u0016\tV%T)&\u001b5k\u0018)S\u000b\u001aK\u0005,\u0001\nT)\u0006#\u0016j\u0015+J\u0007N{\u0006KU#G\u0013b\u0003\u0013!F*U\u0003RK5\u000bV%D'~#v\nV!M?NK%,R\u0001\u0017'R\u000bE+S*U\u0013\u000e\u001bv\fV(U\u00032{6+\u0013.FA\u0005\u00192\u000bV!U\u0013N#\u0016jQ*`\u001dVkuLU(X'\u0006!2\u000bV!U\u0013N#\u0016jQ*`\u001dVkuLU(X'\u0002\n1d\u0015+B)&\u001bF+S\"T?\u000e{EjX*U\u0003R\u001bv\f\u0015*F\r&C\u0016\u0001H*U\u0003RK5\u000bV%D'~\u001bu\nT0T)\u0006#6k\u0018)S\u000b\u001aK\u0005\fI\u0001\u0019)\u0006\u0013E*R0Q\u0003J#\u0016\nV%P\u001d~\u0003&k\u0014,J\t\u0016\u0013\u0016!\u0007+B\u00052+u\fU!S)&#\u0016j\u0014(`!J{e+\u0013#F%\u0002\n\u0001\u0005V!C\u0019\u0016{\u0006+\u0011*U\u0013RKuJT0Q%>3\u0016\nR#S?\u000e\u000bE+\u0011'P\u000f\u0006\tC+\u0011\"M\u000b~\u0003\u0016I\u0015+J)&{ej\u0018)S\u001fZKE)\u0012*`\u0007\u0006#\u0016\tT(HA\u0005\u0019C+\u0011\"M\u000b~\u0003\u0016I\u0015+J)&{ej\u0018)S\u001fZKE)\u0012*`\r&cUiU-T)\u0016k\u0015\u0001\n+B\u00052+u\fU!S)&#\u0016j\u0014(`!J{e+\u0013#F%~3\u0015\nT#T3N#V)\u0014\u0011\u0002+\r\u0013V)\u0011+F\t~\u001b\u0006+\u0011*L?Z+%kU%P\u001d\u000612IU#B)\u0016#ul\u0015)B%.{f+\u0012*T\u0013>s\u0005%A\u0010I\u0013Z+ulR#O\u000bJ\u000bE+\u0012#`)\u0006\u0013E*R0Q%>\u0003VI\u0015+J\u000bN\u000b\u0001\u0005S%W\u000b~;UIT#S\u0003R+Ei\u0018+B\u00052+u\f\u0015*P!\u0016\u0013F+S#TA\u0005\t\u0003*\u0013,F?\u001e+e*\u0012*B)\u0016#ul\u0015+P%\u0006;Ui\u0018)S\u001fB+%\u000bV%F'\u0006\u0011\u0003*\u0013,F?\u001e+e*\u0012*B)\u0016#ul\u0015+P%\u0006;Ui\u0018)S\u001fB+%\u000bV%F'\u0002\n\u0011#R'Q)f{F)\u0011+B?N\u001b\u0005*R'B+\t\u00119+\u0001\nF\u001bB#\u0016l\u0018#B)\u0006{6k\u0011%F\u001b\u0006\u0003\u0013\u0001F4fi\u000e{G.^7o\u001d\u0006lWm\u001d\"z)f\u0004X\r\u0006\u0005\u0003,\u0015UU\u0011TCO\u0011\u001d)9* a\u0001\u0007C\nQ\u0001\u001d:paNDq!b'~\u0001\u0004\u0011I!A\u0004d_2$\u0016\u0010]3\t\u000f\u0015}U\u00101\u0001\u0003\n\u0005AA/\u001f9f\u001d\u0006lW-\u0001\u0014hKR\u0004\u0016M\u001d;ji&|gnQ8mk6t7O\u0012:p[R\u000b'\r\\3Qe>\u0004XM\u001d;jKN$BAa\u000b\u0006&\"9Qq\u0015@A\u0002\u0005}\u0018\u0001C7fi\u0006$\u0017\r^1\u0002A\u001d,GOQ;dW\u0016$8\u000b]3d\rJ|W\u000eV1cY\u0016\u0004&o\u001c9feRLWm\u001d\u000b\u0005\u000b[+)\f\u0005\u0004\u0002 \t5Xq\u0016\t\u0005\u0003W)\t,\u0003\u0003\u00064\u00065\"A\u0003\"vG.,Go\u00159fG\"9QqU@A\u0002\u0005}\u0018!E5t\t\u0006$\u0018m]8ve\u000e,G+\u00192mKR!\u0011QUC^\u0011!\u0011I\"!\u0001A\u0002\u0005}\u0018\u0001G5t\u0011&4XmQ8na\u0006$\u0018N\u00197f\t\u0006$\u0018\rV=qKR!\u0011QUCa\u0011!)\u0019-a\u0001A\u0002\u0015\u0015\u0017A\u00013u!\u0011\u0011I+b2\n\t\u0015%'1\u0016\u0002\t\t\u0006$\u0018\rV=qK\u0002"
)
public class HiveExternalCatalog implements ExternalCatalog, Logging {
   private HiveClient client;
   private final SparkConf conf;
   private final Configuration hadoopConf;
   private final Set clientExceptions;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static StructType EMPTY_DATA_SCHEMA() {
      return HiveExternalCatalog$.MODULE$.EMPTY_DATA_SCHEMA();
   }

   public static Set HIVE_GENERATED_STORAGE_PROPERTIES() {
      return HiveExternalCatalog$.MODULE$.HIVE_GENERATED_STORAGE_PROPERTIES();
   }

   public static Set HIVE_GENERATED_TABLE_PROPERTIES() {
      return HiveExternalCatalog$.MODULE$.HIVE_GENERATED_TABLE_PROPERTIES();
   }

   public static String CREATED_SPARK_VERSION() {
      return HiveExternalCatalog$.MODULE$.CREATED_SPARK_VERSION();
   }

   public static String TABLE_PARTITION_PROVIDER_FILESYSTEM() {
      return HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER_FILESYSTEM();
   }

   public static String TABLE_PARTITION_PROVIDER_CATALOG() {
      return HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER_CATALOG();
   }

   public static String TABLE_PARTITION_PROVIDER() {
      return HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER();
   }

   public static String STATISTICS_COL_STATS_PREFIX() {
      return HiveExternalCatalog$.MODULE$.STATISTICS_COL_STATS_PREFIX();
   }

   public static String STATISTICS_NUM_ROWS() {
      return HiveExternalCatalog$.MODULE$.STATISTICS_NUM_ROWS();
   }

   public static String STATISTICS_TOTAL_SIZE() {
      return HiveExternalCatalog$.MODULE$.STATISTICS_TOTAL_SIZE();
   }

   public static String STATISTICS_PREFIX() {
      return HiveExternalCatalog$.MODULE$.STATISTICS_PREFIX();
   }

   public static String DATASOURCE_SCHEMA_SORTCOL_PREFIX() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_SORTCOL_PREFIX();
   }

   public static String DATASOURCE_SCHEMA_BUCKETCOL_PREFIX() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_BUCKETCOL_PREFIX();
   }

   public static String DATASOURCE_SCHEMA_PARTCOL_PREFIX() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_PARTCOL_PREFIX();
   }

   public static String DATASOURCE_SCHEMA_PART_PREFIX() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_PART_PREFIX();
   }

   public static String DATASOURCE_SCHEMA_NUMBUCKETCOLS() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_NUMBUCKETCOLS();
   }

   public static String DATASOURCE_SCHEMA_NUMBUCKETS() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_NUMBUCKETS();
   }

   public static String DATASOURCE_SCHEMA_NUMSORTCOLS() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_NUMSORTCOLS();
   }

   public static String DATASOURCE_SCHEMA_NUMPARTCOLS() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_NUMPARTCOLS();
   }

   public static String DATASOURCE_SCHEMA_PREFIX() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_PREFIX();
   }

   public static String DATASOURCE_SCHEMA() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA();
   }

   public static String DATASOURCE_PROVIDER() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_PROVIDER();
   }

   public static String DATASOURCE_PREFIX() {
      return HiveExternalCatalog$.MODULE$.DATASOURCE_PREFIX();
   }

   public static String SPARK_SQL_PREFIX() {
      return HiveExternalCatalog$.MODULE$.SPARK_SQL_PREFIX();
   }

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

   public void requireDbExists(final String db) {
      ExternalCatalog.requireDbExists$(this, db);
   }

   public void requireTableExists(final String db, final String table) {
      ExternalCatalog.requireTableExists$(this, db, table);
   }

   public void requireFunctionExists(final String db, final String funcName) {
      ExternalCatalog.requireFunctionExists$(this, db, funcName);
   }

   public void requireFunctionNotExists(final String db, final String funcName) {
      ExternalCatalog.requireFunctionNotExists$(this, db, funcName);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private HiveClient client$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.client = HiveUtils$.MODULE$.newClientForMetadata(this.conf, this.hadoopConf, HiveUtils$.MODULE$.newClientForMetadata$default$3());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.client;
   }

   public HiveClient client() {
      return !this.bitmap$0 ? this.client$lzycompute() : this.client;
   }

   private Set clientExceptions() {
      return this.clientExceptions;
   }

   private boolean isClientException(final Throwable e) {
      while(true) {
         if (e instanceof RuntimeException var5) {
            if (var5.getCause() != null) {
               e = var5.getCause();
               continue;
            }
         }

         Class temp = e.getClass();

         boolean found;
         for(found = false; temp != null && !found; temp = temp.getSuperclass()) {
            found = this.clientExceptions().contains(temp.getCanonicalName());
         }

         return found;
      }
   }

   private synchronized Object withClient(final Function0 body) {
      try {
         return body.apply();
      } catch (Throwable var11) {
         if (var11 != null) {
            Option var6 = .MODULE$.unapply(var11);
            if (!var6.isEmpty()) {
               Throwable exception = (Throwable)var6.get();
               if (this.isClientException(exception)) {
                  Throwable var10000;
                  if (exception instanceof InvocationTargetException) {
                     InvocationTargetException var10 = (InvocationTargetException)exception;
                     var10000 = var10.getCause();
                  } else {
                     var10000 = exception;
                  }

                  Throwable e = var10000;
                  throw new AnalysisException("_LEGACY_ERROR_TEMP_3065", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("clazz"), e.getClass().getCanonicalName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("msg"), scala.Option..MODULE$.apply(e.getMessage()).getOrElse(() -> ""))}))), new Some(e));
               }
            }
         }

         throw var11;
      }
   }

   public CatalogTable getRawTable(final String db, final String table) {
      return this.client().getTable(db, table);
   }

   public Seq getRawTablesByNames(final String db, final Seq tables) {
      return this.client().getTablesByName(db, tables);
   }

   private void verifyTableProperties(final CatalogTable table) {
      Iterable invalidKeys = (Iterable)table.properties().keys().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$verifyTableProperties$1(x$1)));
      if (invalidKeys.nonEmpty()) {
         throw new AnalysisException("_LEGACY_ERROR_TEMP_3086", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("tableName"), table.qualifiedName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("invalidKeys"), invalidKeys.mkString("[", ", ", "]"))}))));
      } else if (table.properties().contains("EXTERNAL")) {
         throw new AnalysisException("_LEGACY_ERROR_TEMP_3087", scala.Predef..MODULE$.Map().empty());
      }
   }

   public void createDatabase(final CatalogDatabase dbDefinition, final boolean ignoreIfExists) {
      this.withClient((JFunction0.mcV.sp)() -> this.client().createDatabase(dbDefinition, ignoreIfExists));
   }

   public void dropDatabase(final String db, final boolean ignoreIfNotExists, final boolean cascade) {
      this.withClient((JFunction0.mcV.sp)() -> this.client().dropDatabase(db, ignoreIfNotExists, cascade));
   }

   public void alterDatabase(final CatalogDatabase dbDefinition) {
      this.withClient((JFunction0.mcV.sp)() -> {
         label14: {
            CatalogDatabase existingDb = this.getDatabase(dbDefinition.name());
            scala.collection.immutable.Map var10000 = existingDb.properties();
            scala.collection.immutable.Map var3 = dbDefinition.properties();
            if (var10000 == null) {
               if (var3 != null) {
                  break label14;
               }
            } else if (!var10000.equals(var3)) {
               break label14;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Request to alter database ", " is a "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DATABASE_NAME..MODULE$, dbDefinition.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"no-op because the provided database properties are the same as the old ones. Hive "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"does not currently support altering other database fields."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         this.client().alterDatabase(dbDefinition);
      });
   }

   public CatalogDatabase getDatabase(final String db) {
      return (CatalogDatabase)this.withClient(() -> this.client().getDatabase(db));
   }

   public boolean databaseExists(final String db) {
      return BoxesRunTime.unboxToBoolean(this.withClient((JFunction0.mcZ.sp)() -> this.client().databaseExists(db)));
   }

   public Seq listDatabases() {
      return (Seq)this.withClient(() -> this.client().listDatabases("*"));
   }

   public Seq listDatabases(final String pattern) {
      return (Seq)this.withClient(() -> this.client().listDatabases(pattern));
   }

   public void setCurrentDatabase(final String db) {
      this.withClient((JFunction0.mcV.sp)() -> this.client().setCurrentDatabase(db));
   }

   public void createTable(final CatalogTable tableDefinition, final boolean ignoreIfExists) {
      this.withClient((JFunction0.mcV.sp)() -> {
         scala.Predef..MODULE$.assert(tableDefinition.identifier().database().isDefined());
         String db = (String)tableDefinition.identifier().database().get();
         String table = tableDefinition.identifier().table();
         this.requireDbExists(db);
         this.verifyTableProperties(tableDefinition);
         if (this.tableExists(db, table) && !ignoreIfExists) {
            throw new TableAlreadyExistsException(db, table);
         } else {
            boolean var92;
            label95: {
               label94: {
                  CatalogTableType var10000 = tableDefinition.tableType();
                  CatalogTableType var8 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.MANAGED();
                  if (var10000 == null) {
                     if (var8 != null) {
                        break label94;
                     }
                  } else if (!var10000.equals(var8)) {
                     break label94;
                  }

                  if (tableDefinition.storage().locationUri().isEmpty()) {
                     var92 = true;
                     break label95;
                  }
               }

               var92 = false;
            }

            boolean needDefaultTableLocation = var92;
            Option tableLocation = (Option)(needDefaultTableLocation ? new Some(org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.stringToURI(this.defaultTablePath(tableDefinition.identifier()))) : tableDefinition.storage().locationUri());
            StructType schemaWithNoCollation = this.removeCollation(tableDefinition.schema());
            if (org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isDatasourceTable(tableDefinition)) {
               scala.collection.mutable.Map tableProperties = this.tableMetaToTableProps(tableDefinition);
               tableProperties.put(HiveExternalCatalog$.MODULE$.DATASOURCE_PROVIDER(), tableDefinition.provider().get());
               if (tableDefinition.tracksPartitionsInCatalog()) {
                  tableProperties.put(HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER(), HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER_CATALOG());
               } else {
                  BoxedUnit var98 = BoxedUnit.UNIT;
               }

               scala.collection.immutable.Map x$2 = (scala.collection.immutable.Map)tableDefinition.properties().$plus$plus(tableProperties);
               TableIdentifier x$3 = tableDefinition.copy$default$1();
               CatalogTableType x$4 = tableDefinition.copy$default$2();
               CatalogStorageFormat x$5 = tableDefinition.copy$default$3();
               Option x$6 = tableDefinition.copy$default$5();
               Seq x$7 = tableDefinition.copy$default$6();
               Option x$8 = tableDefinition.copy$default$7();
               String x$9 = tableDefinition.copy$default$8();
               long x$10 = tableDefinition.copy$default$9();
               long x$11 = tableDefinition.copy$default$10();
               String x$12 = tableDefinition.copy$default$11();
               Option x$13 = tableDefinition.copy$default$13();
               Option x$14 = tableDefinition.copy$default$14();
               Option x$15 = tableDefinition.copy$default$15();
               Option x$16 = tableDefinition.copy$default$16();
               Seq x$17 = tableDefinition.copy$default$17();
               boolean x$18 = tableDefinition.copy$default$18();
               boolean x$19 = tableDefinition.copy$default$19();
               scala.collection.immutable.Map x$20 = tableDefinition.copy$default$20();
               Option x$21 = tableDefinition.copy$default$21();
               CatalogTable hiveTable = tableDefinition.copy(x$3, x$4, x$5, schemaWithNoCollation, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$2, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21);
               this.createDataSourceTable(hiveTable.withNewStorage(tableLocation, hiveTable.withNewStorage$default$2(), hiveTable.withNewStorage$default$3(), hiveTable.withNewStorage$default$4(), hiveTable.withNewStorage$default$5(), hiveTable.withNewStorage$default$6()), ignoreIfExists);
            } else {
               label85: {
                  label84: {
                     CatalogTableType var93 = tableDefinition.tableType();
                     CatalogTableType var38 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW();
                     if (var93 == null) {
                        if (var38 != null) {
                           break label84;
                        }
                     } else if (!var93.equals(var38)) {
                        break label84;
                     }

                     if (schemaWithNoCollation.exists((f) -> BoxesRunTime.boxToBoolean($anonfun$createTable$2(f)))) {
                        var94 = HiveExternalCatalog$.MODULE$.EMPTY_DATA_SCHEMA();
                        break label85;
                     }
                  }

                  var94 = schemaWithNoCollation;
               }

               StructType hiveCompatibleSchema = var94;
               CatalogStorageFormat x$23 = tableDefinition.storage().copy(tableLocation, tableDefinition.storage().copy$default$2(), tableDefinition.storage().copy$default$3(), tableDefinition.storage().copy$default$4(), tableDefinition.storage().copy$default$5(), tableDefinition.storage().copy$default$6());
               scala.collection.immutable.Map x$24 = (scala.collection.immutable.Map)tableDefinition.properties().$plus$plus(this.tableMetaToTableProps(tableDefinition));
               TableIdentifier x$25 = tableDefinition.copy$default$1();
               CatalogTableType x$26 = tableDefinition.copy$default$2();
               Option x$27 = tableDefinition.copy$default$5();
               Seq x$28 = tableDefinition.copy$default$6();
               Option x$29 = tableDefinition.copy$default$7();
               String x$30 = tableDefinition.copy$default$8();
               long x$31 = tableDefinition.copy$default$9();
               long x$32 = tableDefinition.copy$default$10();
               String x$33 = tableDefinition.copy$default$11();
               Option x$34 = tableDefinition.copy$default$13();
               Option x$35 = tableDefinition.copy$default$14();
               Option x$36 = tableDefinition.copy$default$15();
               Option x$37 = tableDefinition.copy$default$16();
               Seq x$38 = tableDefinition.copy$default$17();
               boolean x$39 = tableDefinition.copy$default$18();
               boolean x$40 = tableDefinition.copy$default$19();
               scala.collection.immutable.Map x$41 = tableDefinition.copy$default$20();
               Option x$42 = tableDefinition.copy$default$21();
               CatalogTable tableWithDataSourceProps = tableDefinition.copy(x$25, x$26, x$23, hiveCompatibleSchema, x$27, x$28, x$29, x$30, x$31, x$32, x$33, x$24, x$34, x$35, x$36, x$37, x$38, x$39, x$40, x$41, x$42);

               try {
                  this.client().createTable(tableWithDataSourceProps, ignoreIfExists);
               } catch (Throwable var91) {
                  label103: {
                     if (var91 != null) {
                        Option var65 = .MODULE$.unapply(var91);
                        if (!var65.isEmpty()) {
                           CatalogTableType var95 = tableDefinition.tableType();
                           CatalogTableType var66 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW();
                           if (var95 == null) {
                              if (var66 != null) {
                                 throw var91;
                              }
                           } else if (!var95.equals(var66)) {
                              throw var91;
                           }

                           StructType var67 = HiveExternalCatalog$.MODULE$.EMPTY_DATA_SCHEMA();
                           if (hiveCompatibleSchema == null) {
                              if (var67 != null) {
                                 break label103;
                              }
                           } else if (!hiveCompatibleSchema.equals(var67)) {
                              break label103;
                           }
                        }
                     }

                     throw var91;
                  }

                  HiveClient var96 = this.client();
                  StructType x$43 = HiveExternalCatalog$.MODULE$.EMPTY_DATA_SCHEMA();
                  TableIdentifier x$44 = tableWithDataSourceProps.copy$default$1();
                  CatalogTableType x$45 = tableWithDataSourceProps.copy$default$2();
                  CatalogStorageFormat x$46 = tableWithDataSourceProps.copy$default$3();
                  Option x$47 = tableWithDataSourceProps.copy$default$5();
                  Seq x$48 = tableWithDataSourceProps.copy$default$6();
                  Option x$49 = tableWithDataSourceProps.copy$default$7();
                  String x$50 = tableWithDataSourceProps.copy$default$8();
                  long x$51 = tableWithDataSourceProps.copy$default$9();
                  long x$52 = tableWithDataSourceProps.copy$default$10();
                  String x$53 = tableWithDataSourceProps.copy$default$11();
                  scala.collection.immutable.Map x$54 = tableWithDataSourceProps.copy$default$12();
                  Option x$55 = tableWithDataSourceProps.copy$default$13();
                  Option x$56 = tableWithDataSourceProps.copy$default$14();
                  Option x$57 = tableWithDataSourceProps.copy$default$15();
                  Option x$58 = tableWithDataSourceProps.copy$default$16();
                  Seq x$59 = tableWithDataSourceProps.copy$default$17();
                  boolean x$60 = tableWithDataSourceProps.copy$default$18();
                  boolean x$61 = tableWithDataSourceProps.copy$default$19();
                  scala.collection.immutable.Map x$62 = tableWithDataSourceProps.copy$default$20();
                  Option x$63 = tableWithDataSourceProps.copy$default$21();
                  var96.createTable(tableWithDataSourceProps.copy(x$44, x$45, x$46, x$43, x$47, x$48, x$49, x$50, x$51, x$52, x$53, x$54, x$55, x$56, x$57, x$58, x$59, x$60, x$61, x$62, x$63), ignoreIfExists);
                  BoxedUnit var97 = BoxedUnit.UNIT;
               }

            }
         }
      });
   }

   private void createDataSourceTable(final CatalogTable table, final boolean ignoreIfExists) {
      String provider = (String)table.provider().get();
      SourceOptions options = new SourceOptions(table.storage().properties());
      scala.collection.immutable.Map storagePropsWithLocation = (scala.collection.immutable.Map)table.storage().properties().$plus$plus(table.storage().locationUri().map((x$2) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("path"), org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.URIToString(x$2))));
      String qualifiedTableName = table.identifier().quotedString();
      Option maybeSerde = org.apache.spark.sql.internal.HiveSerDe..MODULE$.sourceToSerDe(provider);
      Seq incompatibleTypes = (Seq)((IterableOps)table.schema().filter((f) -> BoxesRunTime.boxToBoolean($anonfun$createDataSourceTable$3(f)))).map((x$3) -> x$3.dataType().simpleString());
      boolean var15 = false;
      Some var16 = null;
      Tuple2 var10000;
      if (options.skipHiveMetadata()) {
         MessageWithContext message = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting data source table ", " into Hive "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, qualifiedTableName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"metastore in Spark SQL specific format, which is NOT compatible with Hive."})))).log(scala.collection.immutable.Nil..MODULE$));
         var10000 = new Tuple2(scala.None..MODULE$, message);
      } else if (incompatibleTypes.nonEmpty()) {
         String incompatibleTypesStr = incompatibleTypes.mkString(", ");
         MessageWithContext message = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Hive incompatible types found: ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INCOMPATIBLE_TYPES..MODULE$, incompatibleTypesStr)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting data source table ", " into Hive "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, qualifiedTableName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"metastore in Spark SQL specific format, which is NOT compatible with Hive."})))).log(scala.collection.immutable.Nil..MODULE$));
         var10000 = new Tuple2(scala.None..MODULE$, message);
      } else {
         label67: {
            if (maybeSerde instanceof Some) {
               var15 = true;
               var16 = (Some)maybeSerde;
               HiveSerDe serde = (HiveSerDe)var16.value();
               if (table.bucketSpec().nonEmpty()) {
                  MessageWithContext message = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting bucketed data source table ", " into "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, qualifiedTableName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Hive metastore in Spark SQL specific format, which is NOT compatible with "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Hive bucketed table. But Hive can read this table as a non-bucketed table."})))).log(scala.collection.immutable.Nil..MODULE$));
                  var10000 = new Tuple2(new Some(newHiveCompatibleMetastoreTable$1(serde, table, storagePropsWithLocation)), message);
                  break label67;
               }
            }

            if (var15) {
               HiveSerDe serde = (HiveSerDe)var16.value();
               MessageWithContext message = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting file based data source table ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, qualifiedTableName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"into Hive metastore in Hive compatible format."})))).log(scala.collection.immutable.Nil..MODULE$));
               var10000 = new Tuple2(new Some(newHiveCompatibleMetastoreTable$1(serde, table, storagePropsWithLocation)), message);
            } else {
               MessageWithContext message = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Couldn't find corresponding Hive SerDe for data source provider "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Persisting data source table "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PROVIDER..MODULE$, provider)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " into Hive metastore in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, qualifiedTableName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Spark SQL specific format, which is NOT compatible with Hive."})))).log(scala.collection.immutable.Nil..MODULE$));
               var10000 = new Tuple2(scala.None..MODULE$, message);
            }
         }
      }

      Tuple2 var14 = var10000;
      if (var14 == null) {
         throw new MatchError(var14);
      } else {
         Option hiveCompatibleTable = (Option)var14._1();
         MessageWithContext logMessage = (MessageWithContext)var14._2();
         Tuple2 var13 = new Tuple2(hiveCompatibleTable, logMessage);
         Option hiveCompatibleTable = (Option)var13._1();
         MessageWithContext logMessage = (MessageWithContext)var13._2();
         Tuple2 var30 = new Tuple2(hiveCompatibleTable, logMessage);
         if (var30 != null) {
            Option var31 = (Option)var30._1();
            MessageWithContext message = (MessageWithContext)var30._2();
            if (var31 instanceof Some) {
               Some var33 = (Some)var31;
               CatalogTable table = (CatalogTable)var33.value();

               try {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> message));
                  this.saveTableIntoHive(table, ignoreIfExists);
                  BoxedUnit var45 = BoxedUnit.UNIT;
               } catch (Throwable var41) {
                  if (var41 == null || !.MODULE$.apply(var41)) {
                     throw var41;
                  }

                  MessageWithContext warningMessage = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not persist ", " in a Hive "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, table.identifier().quotedString())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"compatible way. Persisting it into Hive metastore in Spark SQL specific format."})))).log(scala.collection.immutable.Nil..MODULE$));
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> warningMessage), var41);
                  this.saveTableIntoHive(newSparkSQLSpecificMetastoreTable$1(storagePropsWithLocation, table), ignoreIfExists);
                  BoxedUnit var43 = BoxedUnit.UNIT;
                  var43 = BoxedUnit.UNIT;
               }

               return;
            }
         }

         if (var30 != null) {
            Option var39 = (Option)var30._1();
            MessageWithContext message = (MessageWithContext)var30._2();
            if (scala.None..MODULE$.equals(var39)) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> message));
               this.saveTableIntoHive(newSparkSQLSpecificMetastoreTable$1(storagePropsWithLocation, table), ignoreIfExists);
               BoxedUnit var42 = BoxedUnit.UNIT;
               return;
            }
         }

         throw new MatchError(var30);
      }
   }

   private scala.collection.mutable.Map tableMetaToTableProps(final CatalogTable table) {
      return this.tableMetaToTableProps(table, table.schema());
   }

   private scala.collection.mutable.Map tableMetaToTableProps(final CatalogTable table, final StructType schema) {
      Seq partitionColumns = table.partitionColumnNames();
      Option bucketSpec = table.bucketSpec();
      HashMap properties = new HashMap();
      properties.put(HiveExternalCatalog$.MODULE$.CREATED_SPARK_VERSION(), table.createVersion());
      StructType newSchema = org.apache.spark.sql.catalyst.util.CharVarcharUtils..MODULE$.replaceCharVarcharWithStringInSchema(schema);
      org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.splitLargeTableProp(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA(), newSchema.json(), (key, value) -> {
         $anonfun$tableMetaToTableProps$1(properties, key, value);
         return BoxedUnit.UNIT;
      }, BoxesRunTime.unboxToInt(this.conf.get(org.apache.spark.sql.internal.StaticSQLConf..MODULE$.SCHEMA_STRING_LENGTH_THRESHOLD())));
      if (partitionColumns.nonEmpty()) {
         properties.put(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_NUMPARTCOLS(), Integer.toString(partitionColumns.length()));
         ((IterableOnceOps)partitionColumns.zipWithIndex()).foreach((x0$1) -> {
            if (x0$1 != null) {
               String partCol = (String)x0$1._1();
               int index = x0$1._2$mcI$sp();
               return properties.put(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_PARTCOL_PREFIX() + index, partCol);
            } else {
               throw new MatchError(x0$1);
            }
         });
      }

      if (bucketSpec.isDefined()) {
         BucketSpec var9 = (BucketSpec)bucketSpec.get();
         if (var9 == null) {
            throw new MatchError(var9);
         }

         int numBuckets = var9.numBuckets();
         Seq bucketColumnNames = var9.bucketColumnNames();
         Seq sortColumnNames = var9.sortColumnNames();
         Tuple3 var8 = new Tuple3(BoxesRunTime.boxToInteger(numBuckets), bucketColumnNames, sortColumnNames);
         int numBuckets = BoxesRunTime.unboxToInt(var8._1());
         Seq bucketColumnNames = (Seq)var8._2();
         Seq sortColumnNames = (Seq)var8._3();
         properties.put(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_NUMBUCKETS(), Integer.toString(numBuckets));
         properties.put(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_NUMBUCKETCOLS(), Integer.toString(bucketColumnNames.length()));
         ((IterableOnceOps)bucketColumnNames.zipWithIndex()).foreach((x0$2) -> {
            if (x0$2 != null) {
               String bucketCol = (String)x0$2._1();
               int index = x0$2._2$mcI$sp();
               return properties.put(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_BUCKETCOL_PREFIX() + index, bucketCol);
            } else {
               throw new MatchError(x0$2);
            }
         });
         if (sortColumnNames.nonEmpty()) {
            properties.put(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_NUMSORTCOLS(), Integer.toString(sortColumnNames.length()));
            ((IterableOnceOps)sortColumnNames.zipWithIndex()).foreach((x0$3) -> {
               if (x0$3 != null) {
                  String sortCol = (String)x0$3._1();
                  int index = x0$3._2$mcI$sp();
                  return properties.put(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA_SORTCOL_PREFIX() + index, sortCol);
               } else {
                  throw new MatchError(x0$3);
               }
            });
         }
      }

      return properties;
   }

   private String defaultTablePath(final TableIdentifier tableIdent) {
      URI dbLocation = this.getDatabase((String)tableIdent.database().get()).locationUri();
      return (new Path(new Path(dbLocation), tableIdent.table())).toString();
   }

   private void saveTableIntoHive(final CatalogTable tableDefinition, final boolean ignoreIfExists) {
      label47: {
         scala.Predef..MODULE$.assert(org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isDatasourceTable(tableDefinition), () -> "saveTableIntoHive only takes data source table.");
         CatalogTableType var10000 = tableDefinition.tableType();
         CatalogTableType var3 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.EXTERNAL();
         if (var10000 == null) {
            if (var3 != null) {
               break label47;
            }
         } else if (!var10000.equals(var3)) {
            break label47;
         }

         if (tableDefinition.storage().locationUri().isEmpty()) {
            Path dbLocation = new Path(this.getDatabase(tableDefinition.database()).locationUri());
            Path tempPath = new Path(dbLocation, tableDefinition.identifier().table() + "-__PLACEHOLDER__");

            try {
               this.client().createTable(tableDefinition.withNewStorage(new Some(tempPath.toUri()), tableDefinition.withNewStorage$default$2(), tableDefinition.withNewStorage$default$3(), tableDefinition.withNewStorage$default$4(), tableDefinition.withNewStorage$default$5(), tableDefinition.withNewStorage$default$6()), ignoreIfExists);
            } finally {
               FileSystem.get(tempPath.toUri(), this.hadoopConf).delete(tempPath, true);
            }

            return;
         }
      }

      this.client().createTable(tableDefinition, ignoreIfExists);
   }

   public void dropTable(final String db, final String table, final boolean ignoreIfNotExists, final boolean purge) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireDbExists(db);
         this.client().dropTable(db, table, ignoreIfNotExists, purge);
      });
   }

   public void renameTable(final String db, final String oldName, final String newName) {
      this.withClient((JFunction0.mcV.sp)() -> {
         CatalogTable rawTable;
         CatalogStorageFormat var33;
         label19: {
            label18: {
               rawTable = this.getRawTable(db, oldName);
               boolean hasPathOption = org.apache.spark.sql.catalyst.util.CaseInsensitiveMap..MODULE$.apply(rawTable.storage().properties()).contains("path");
               CatalogTableType var10000 = rawTable.tableType();
               CatalogTableType var7 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.MANAGED();
               if (var10000 == null) {
                  if (var7 != null) {
                     break label18;
                  }
               } else if (!var10000.equals(var7)) {
                  break label18;
               }

               if (hasPathOption) {
                  String newTablePath = this.defaultTablePath(org.apache.spark.sql.catalyst.TableIdentifier..MODULE$.apply(newName, new Some(db)));
                  var33 = this.updateLocationInStorageProps(rawTable, new Some(newTablePath));
                  break label19;
               }
            }

            var33 = rawTable.storage();
         }

         CatalogStorageFormat storageWithNewPath = var33;
         TableIdentifier x$1 = org.apache.spark.sql.catalyst.TableIdentifier..MODULE$.apply(newName, new Some(db));
         CatalogTableType x$3 = rawTable.copy$default$2();
         StructType x$4 = rawTable.copy$default$4();
         Option x$5 = rawTable.copy$default$5();
         Seq x$6 = rawTable.copy$default$6();
         Option x$7 = rawTable.copy$default$7();
         String x$8 = rawTable.copy$default$8();
         long x$9 = rawTable.copy$default$9();
         long x$10 = rawTable.copy$default$10();
         String x$11 = rawTable.copy$default$11();
         scala.collection.immutable.Map x$12 = rawTable.copy$default$12();
         Option x$13 = rawTable.copy$default$13();
         Option x$14 = rawTable.copy$default$14();
         Option x$15 = rawTable.copy$default$15();
         Option x$16 = rawTable.copy$default$16();
         Seq x$17 = rawTable.copy$default$17();
         boolean x$18 = rawTable.copy$default$18();
         boolean x$19 = rawTable.copy$default$19();
         scala.collection.immutable.Map x$20 = rawTable.copy$default$20();
         Option x$21 = rawTable.copy$default$21();
         CatalogTable newTable = rawTable.copy(x$1, x$3, storageWithNewPath, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21);
         this.client().alterTable(db, oldName, newTable);
      });
   }

   private Option getLocationFromStorageProps(final CatalogTable table) {
      return org.apache.spark.sql.catalyst.util.CaseInsensitiveMap..MODULE$.apply(table.storage().properties()).get("path");
   }

   private CatalogStorageFormat updateLocationInStorageProps(final CatalogTable table, final Option newPath) {
      scala.collection.immutable.Map propsWithoutPath = (scala.collection.immutable.Map)table.storage().properties().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$updateLocationInStorageProps$1(x0$1)));
      scala.collection.immutable.Map x$1 = (scala.collection.immutable.Map)propsWithoutPath.$plus$plus(newPath.map((x$6x) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("path"), x$6x)));
      Option x$2 = table.storage().copy$default$1();
      Option x$3 = table.storage().copy$default$2();
      Option x$4 = table.storage().copy$default$3();
      Option x$5 = table.storage().copy$default$4();
      boolean x$6 = table.storage().copy$default$5();
      return table.storage().copy(x$2, x$3, x$4, x$5, x$6, x$1);
   }

   public void alterTable(final CatalogTable tableDefinition) {
      this.withClient((JFunction0.mcV.sp)() -> {
         label60: {
            scala.Predef..MODULE$.assert(tableDefinition.identifier().database().isDefined());
            String db = (String)tableDefinition.identifier().database().get();
            this.requireTableExists(db, tableDefinition.identifier().table());
            this.verifyTableProperties(tableDefinition);
            CatalogTableType var10000 = tableDefinition.tableType();
            CatalogTableType var4 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW();
            if (var10000 == null) {
               if (var4 == null) {
                  break label60;
               }
            } else if (var10000.equals(var4)) {
               break label60;
            }

            CatalogTable oldTableDef = this.getRawTable(db, tableDefinition.identifier().table());
            CatalogStorageFormat var98;
            if (org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isHiveTable(tableDefinition)) {
               var98 = tableDefinition.storage();
            } else {
               label50: {
                  CatalogStorageFormat storageWithPathOption;
                  label49: {
                     Option newLocation = tableDefinition.storage().locationUri().map((x$7x) -> org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.URIToString(x$7x));
                     scala.collection.immutable.Map x$43 = (scala.collection.immutable.Map)tableDefinition.storage().properties().$plus$plus(newLocation.map((x$8x) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("path"), x$8x)));
                     Option x$44 = tableDefinition.storage().copy$default$1();
                     Option x$45 = tableDefinition.storage().copy$default$2();
                     Option x$46 = tableDefinition.storage().copy$default$3();
                     Option x$47 = tableDefinition.storage().copy$default$4();
                     boolean x$48 = tableDefinition.storage().copy$default$5();
                     storageWithPathOption = tableDefinition.storage().copy(x$44, x$45, x$46, x$47, x$48, x$43);
                     Option oldLocation = this.getLocationFromStorageProps(oldTableDef);
                     if (oldLocation == null) {
                        if (newLocation == null) {
                           break label49;
                        }
                     } else if (oldLocation.equals(newLocation)) {
                        break label49;
                     }

                     var98 = storageWithPathOption;
                     break label50;
                  }

                  var98 = storageWithPathOption.copy(oldTableDef.storage().locationUri(), storageWithPathOption.copy$default$2(), storageWithPathOption.copy$default$3(), storageWithPathOption.copy$default$4(), storageWithPathOption.copy$default$5(), storageWithPathOption.copy$default$6());
               }
            }

            CatalogStorageFormat newStorage = var98;
            Tuple2 partitionProviderProp = tableDefinition.tracksPartitionsInCatalog() ? scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER()), HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER_CATALOG()) : scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER()), HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER_FILESYSTEM());
            scala.collection.immutable.Map propsFromOldTable = (scala.collection.immutable.Map)oldTableDef.properties().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$alterTable$4(x0$1)));
            Option newFormatIfExists = tableDefinition.provider().flatMap((p) -> (Option)(org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isDatasourceTable(tableDefinition) ? new Some(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(HiveExternalCatalog$.MODULE$.DATASOURCE_PROVIDER()), p)) : scala.None..MODULE$));
            scala.collection.immutable.Map newTableProps = (scala.collection.immutable.Map)((MapOps)propsFromOldTable.$plus$plus(tableDefinition.properties())).$plus(partitionProviderProp).$plus$plus(newFormatIfExists);
            String owner = (String)scala.Option..MODULE$.apply(tableDefinition.owner()).filter((x$9x) -> BoxesRunTime.boxToBoolean($anonfun$alterTable$6(x$9x))).getOrElse(() -> oldTableDef.owner());
            StructType x$50 = oldTableDef.schema();
            Seq x$51 = oldTableDef.partitionColumnNames();
            Option x$52 = oldTableDef.bucketSpec();
            TableIdentifier x$55 = tableDefinition.copy$default$1();
            CatalogTableType x$56 = tableDefinition.copy$default$2();
            Option x$57 = tableDefinition.copy$default$5();
            long x$58 = tableDefinition.copy$default$9();
            long x$59 = tableDefinition.copy$default$10();
            String x$60 = tableDefinition.copy$default$11();
            Option x$61 = tableDefinition.copy$default$13();
            Option x$62 = tableDefinition.copy$default$14();
            Option x$63 = tableDefinition.copy$default$15();
            Option x$64 = tableDefinition.copy$default$16();
            Seq x$65 = tableDefinition.copy$default$17();
            boolean x$66 = tableDefinition.copy$default$18();
            boolean x$67 = tableDefinition.copy$default$19();
            scala.collection.immutable.Map x$68 = tableDefinition.copy$default$20();
            Option x$69 = tableDefinition.copy$default$21();
            CatalogTable newDef = tableDefinition.copy(x$55, x$56, newStorage, x$50, x$57, x$51, x$52, owner, x$58, x$59, x$60, newTableProps, x$61, x$62, x$63, x$64, x$65, x$66, x$67, x$68, x$69);
            this.client().alterTable(newDef);
            return;
         }

         scala.collection.immutable.Map newTableProps = (scala.collection.immutable.Map)tableDefinition.properties().$plus$plus(this.tableMetaToTableProps(tableDefinition).toMap(scala..less.colon.less..MODULE$.refl()));
         TableIdentifier x$2 = tableDefinition.copy$default$1();
         CatalogTableType x$3 = tableDefinition.copy$default$2();
         CatalogStorageFormat x$4 = tableDefinition.copy$default$3();
         StructType x$5 = tableDefinition.copy$default$4();
         Option x$6 = tableDefinition.copy$default$5();
         Seq x$7 = tableDefinition.copy$default$6();
         Option x$8 = tableDefinition.copy$default$7();
         String x$9 = tableDefinition.copy$default$8();
         long x$10 = tableDefinition.copy$default$9();
         long x$11 = tableDefinition.copy$default$10();
         String x$12 = tableDefinition.copy$default$11();
         Option x$13 = tableDefinition.copy$default$13();
         Option x$14 = tableDefinition.copy$default$14();
         Option x$15 = tableDefinition.copy$default$15();
         Option x$16 = tableDefinition.copy$default$16();
         Seq x$17 = tableDefinition.copy$default$17();
         boolean x$18 = tableDefinition.copy$default$18();
         boolean x$19 = tableDefinition.copy$default$19();
         scala.collection.immutable.Map x$20 = tableDefinition.copy$default$20();
         Option x$21 = tableDefinition.copy$default$21();
         CatalogTable newTable = tableDefinition.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, newTableProps, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21);

         try {
            this.client().alterTable(newTable);
         } catch (Throwable var97) {
            if (var97 == null || !.MODULE$.apply(var97)) {
               throw var97;
            }

            HiveClient var99 = this.client();
            StructType x$22 = HiveExternalCatalog$.MODULE$.EMPTY_DATA_SCHEMA();
            TableIdentifier x$23 = newTable.copy$default$1();
            CatalogTableType x$24 = newTable.copy$default$2();
            CatalogStorageFormat x$25 = newTable.copy$default$3();
            Option x$26 = newTable.copy$default$5();
            Seq x$27 = newTable.copy$default$6();
            Option x$28 = newTable.copy$default$7();
            String x$29 = newTable.copy$default$8();
            long x$30 = newTable.copy$default$9();
            long x$31 = newTable.copy$default$10();
            String x$32 = newTable.copy$default$11();
            scala.collection.immutable.Map x$33 = newTable.copy$default$12();
            Option x$34 = newTable.copy$default$13();
            Option x$35 = newTable.copy$default$14();
            Option x$36 = newTable.copy$default$15();
            Option x$37 = newTable.copy$default$16();
            Seq x$38 = newTable.copy$default$17();
            boolean x$39 = newTable.copy$default$18();
            boolean x$40 = newTable.copy$default$19();
            scala.collection.immutable.Map x$41 = newTable.copy$default$20();
            Option x$42 = newTable.copy$default$21();
            var99.alterTable(newTable.copy(x$23, x$24, x$25, x$22, x$26, x$27, x$28, x$29, x$30, x$31, x$32, x$33, x$34, x$35, x$36, x$37, x$38, x$39, x$40, x$41, x$42));
            BoxedUnit var100 = BoxedUnit.UNIT;
         }

      });
   }

   public void alterTableDataSchema(final String db, final String table, final StructType newDataSchema) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireTableExists(db, table);
         CatalogTable oldTable = this.getTable(db, table);
         scala.collection.immutable.Map schemaProps = this.tableMetaToTableProps(oldTable, org.apache.spark.sql.types.StructType..MODULE$.apply((Seq)newDataSchema.$plus$plus(oldTable.partitionSchema()))).toMap(scala..less.colon.less..MODULE$.refl());
         StructType hiveSchema = this.removeCollation(newDataSchema);
         if (HiveExternalCatalog$.MODULE$.isDatasourceTable(oldTable)) {
            try {
               this.client().alterTableDataSchema(db, table, hiveSchema, schemaProps);
            } catch (Throwable var12) {
               if (var12 == null || !.MODULE$.apply(var12)) {
                  throw var12;
               }

               MessageWithContext warningMessage = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not alter schema of table "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " in a Hive compatible way. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, oldTable.identifier().quotedString())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Updating Hive metastore in Spark SQL specific format."})))).log(scala.collection.immutable.Nil..MODULE$));
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> warningMessage), var12);
               this.client().alterTableDataSchema(db, table, HiveExternalCatalog$.MODULE$.EMPTY_DATA_SCHEMA(), schemaProps);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

         } else {
            this.client().alterTableDataSchema(db, table, hiveSchema, schemaProps);
         }
      });
   }

   private StructType removeCollation(final StructType schema) {
      return (StructType)org.apache.spark.sql.util.SchemaUtils..MODULE$.replaceCollatedStringWithString(schema);
   }

   public void alterTableStats(final String db, final String table, final Option stats) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireTableExists(db, table);
         RawHiveTable rawHiveTable = this.client().getRawHiveTable(db, table);
         scala.collection.immutable.Map oldProps = (scala.collection.immutable.Map)rawHiveTable.hiveTableProps().filterNot((x$10) -> BoxesRunTime.boxToBoolean($anonfun$alterTableStats$2(x$10)));
         scala.collection.immutable.Map newProps = stats.isDefined() ? (scala.collection.immutable.Map)oldProps.$plus$plus(this.statsToProperties((CatalogStatistics)stats.get())) : oldProps;
         this.client().alterTableProps(rawHiveTable, newProps);
      });
   }

   public CatalogTable getTable(final String db, final String table) {
      return (CatalogTable)this.withClient(() -> this.restoreTableMetadata(this.getRawTable(db, table)));
   }

   public Seq getTablesByName(final String db, final Seq tables) {
      return (Seq)this.withClient(() -> (Seq)this.getRawTablesByNames(db, tables).map((inputTable) -> this.restoreTableMetadata(inputTable)));
   }

   private CatalogTable restoreTableMetadata(final CatalogTable inputTable) {
      if (BoxesRunTime.unboxToBoolean(this.conf.get(org.apache.spark.sql.internal.StaticSQLConf..MODULE$.DEBUG_MODE()))) {
         return inputTable;
      } else {
         ObjectRef table;
         label37: {
            label36: {
               table = ObjectRef.create(inputTable);
               boolean var4 = false;
               Object var5 = null;
               Option var6 = ((CatalogTable)table.elem).properties().get(HiveExternalCatalog$.MODULE$.DATASOURCE_PROVIDER());
               if (scala.None..MODULE$.equals(var6)) {
                  var4 = true;
                  CatalogTableType var10000 = ((CatalogTable)table.elem).tableType();
                  CatalogTableType var7 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW();
                  if (var10000 == null) {
                     if (var7 == null) {
                        break label36;
                     }
                  } else if (var10000.equals(var7)) {
                     break label36;
                  }
               }

               if (var4) {
                  table.elem = this.restoreHiveSerdeTable((CatalogTable)table.elem);
                  BoxedUnit var60 = BoxedUnit.UNIT;
               } else {
                  if (!(var6 instanceof Some)) {
                     throw new MatchError(var6);
                  }

                  Some var8 = (Some)var6;
                  String provider = (String)var8.value();
                  table.elem = this.restoreDataSourceTable((CatalogTable)table.elem, provider);
                  BoxedUnit var61 = BoxedUnit.UNIT;
               }
               break label37;
            }

            this.getSchemaFromTableProperties(((CatalogTable)table.elem).properties()).foreach((schemaFromTableProps) -> {
               $anonfun$restoreTableMetadata$1(table, schemaFromTableProps);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var62 = BoxedUnit.UNIT;
         }

         String version = (String)((CatalogTable)table.elem).properties().getOrElse(HiveExternalCatalog$.MODULE$.CREATED_SPARK_VERSION(), () -> "2.2 or prior");
         Option restoredStats = this.statsFromProperties(((CatalogTable)table.elem).properties(), ((CatalogTable)table.elem).identifier().table());
         if (restoredStats.isDefined()) {
            CatalogTable qual$2 = (CatalogTable)table.elem;
            TableIdentifier x$23 = qual$2.copy$default$1();
            CatalogTableType x$24 = qual$2.copy$default$2();
            CatalogStorageFormat x$25 = qual$2.copy$default$3();
            StructType x$26 = qual$2.copy$default$4();
            Option x$27 = qual$2.copy$default$5();
            Seq x$28 = qual$2.copy$default$6();
            Option x$29 = qual$2.copy$default$7();
            String x$30 = qual$2.copy$default$8();
            long x$31 = qual$2.copy$default$9();
            long x$32 = qual$2.copy$default$10();
            String x$33 = qual$2.copy$default$11();
            scala.collection.immutable.Map x$34 = qual$2.copy$default$12();
            Option x$35 = qual$2.copy$default$14();
            Option x$36 = qual$2.copy$default$15();
            Option x$37 = qual$2.copy$default$16();
            Seq x$38 = qual$2.copy$default$17();
            boolean x$39 = qual$2.copy$default$18();
            boolean x$40 = qual$2.copy$default$19();
            scala.collection.immutable.Map x$41 = qual$2.copy$default$20();
            Option x$42 = qual$2.copy$default$21();
            table.elem = qual$2.copy(x$23, x$24, x$25, x$26, x$27, x$28, x$29, x$30, x$31, x$32, x$33, x$34, restoredStats, x$35, x$36, x$37, x$38, x$39, x$40, x$41, x$42);
         }

         CatalogTable qual$3 = (CatalogTable)table.elem;
         scala.collection.immutable.Map x$44 = (scala.collection.immutable.Map)((CatalogTable)table.elem).properties().filterNot((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$restoreTableMetadata$3(x0$1)));
         TableIdentifier x$45 = qual$3.copy$default$1();
         CatalogTableType x$46 = qual$3.copy$default$2();
         CatalogStorageFormat x$47 = qual$3.copy$default$3();
         StructType x$48 = qual$3.copy$default$4();
         Option x$49 = qual$3.copy$default$5();
         Seq x$50 = qual$3.copy$default$6();
         Option x$51 = qual$3.copy$default$7();
         String x$52 = qual$3.copy$default$8();
         long x$53 = qual$3.copy$default$9();
         long x$54 = qual$3.copy$default$10();
         Option x$55 = qual$3.copy$default$13();
         Option x$56 = qual$3.copy$default$14();
         Option x$57 = qual$3.copy$default$15();
         Option x$58 = qual$3.copy$default$16();
         Seq x$59 = qual$3.copy$default$17();
         boolean x$60 = qual$3.copy$default$18();
         boolean x$61 = qual$3.copy$default$19();
         scala.collection.immutable.Map x$62 = qual$3.copy$default$20();
         Option x$63 = qual$3.copy$default$21();
         return qual$3.copy(x$45, x$46, x$47, x$48, x$49, x$50, x$51, x$52, x$53, x$54, version, x$44, x$55, x$56, x$57, x$58, x$59, x$60, x$61, x$62, x$63);
      }
   }

   private StructType reorderSchema(final StructType schema, final Seq partColumnNames) {
      Seq partitionFields = (Seq)partColumnNames.map((partCol) -> (StructField)schema.find((x$11) -> BoxesRunTime.boxToBoolean($anonfun$reorderSchema$2(partCol, x$11))).getOrElse(() -> {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3088", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("schema"), schema.catalogString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("partColumnNames"), partColumnNames.mkString("[", ", ", "]"))}))));
         }));
      return org.apache.spark.sql.types.StructType..MODULE$.apply((Seq)((IterableOps)schema.filterNot((elem) -> BoxesRunTime.boxToBoolean($anonfun$reorderSchema$4(partitionFields, elem)))).$plus$plus(partitionFields));
   }

   private CatalogTable restoreHiveSerdeTable(final CatalogTable table) {
      SourceOptions options = new SourceOptions(table.storage().properties());
      Some x$1 = new Some(org.apache.spark.sql.execution.command.DDLUtils..MODULE$.HIVE_PROVIDER());
      boolean x$2 = true;
      TableIdentifier x$3 = table.copy$default$1();
      CatalogTableType x$4 = table.copy$default$2();
      CatalogStorageFormat x$5 = table.copy$default$3();
      StructType x$6 = table.copy$default$4();
      Seq x$7 = table.copy$default$6();
      Option x$8 = table.copy$default$7();
      String x$9 = table.copy$default$8();
      long x$10 = table.copy$default$9();
      long x$11 = table.copy$default$10();
      String x$12 = table.copy$default$11();
      scala.collection.immutable.Map x$13 = table.copy$default$12();
      Option x$14 = table.copy$default$13();
      Option x$15 = table.copy$default$14();
      Option x$16 = table.copy$default$15();
      Option x$17 = table.copy$default$16();
      Seq x$18 = table.copy$default$17();
      boolean x$19 = table.copy$default$19();
      scala.collection.immutable.Map x$20 = table.copy$default$20();
      Option x$21 = table.copy$default$21();
      CatalogTable hiveTable = table.copy(x$3, x$4, x$5, x$6, x$1, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, true, x$19, x$20, x$21);
      Option maybeSchemaFromTableProps = this.getSchemaFromTableProperties(table.properties());
      if (maybeSchemaFromTableProps.isDefined()) {
         StructType schemaFromTableProps = (StructType)maybeSchemaFromTableProps.get();
         Seq partColumnNames = HiveExternalCatalog$.MODULE$.org$apache$spark$sql$hive$HiveExternalCatalog$$getPartitionColumnsFromTableProperties(table);
         StructType reorderedSchema = this.reorderSchema(schemaFromTableProps, partColumnNames);
         if (!org.apache.spark.sql.catalyst.types.DataTypeUtils..MODULE$.equalsIgnoreCaseNullabilityAndCollation(reorderedSchema, table.schema()) && !options.respectSparkSchema()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The table schema given by Hive metastore"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") is different from the schema when "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEMA..MODULE$, table.schema().catalogString())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"this table was created by Spark SQL"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "). We have to fall back to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEMA2..MODULE$, schemaFromTableProps.catalogString())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the table schema from Hive metastore which is not case preserving."})))).log(scala.collection.immutable.Nil..MODULE$))));
            boolean x$43 = false;
            TableIdentifier x$44 = hiveTable.copy$default$1();
            CatalogTableType x$45 = hiveTable.copy$default$2();
            CatalogStorageFormat x$46 = hiveTable.copy$default$3();
            StructType x$47 = hiveTable.copy$default$4();
            Option x$48 = hiveTable.copy$default$5();
            Seq x$49 = hiveTable.copy$default$6();
            Option x$50 = hiveTable.copy$default$7();
            String x$51 = hiveTable.copy$default$8();
            long x$52 = hiveTable.copy$default$9();
            long x$53 = hiveTable.copy$default$10();
            String x$54 = hiveTable.copy$default$11();
            scala.collection.immutable.Map x$55 = hiveTable.copy$default$12();
            Option x$56 = hiveTable.copy$default$13();
            Option x$57 = hiveTable.copy$default$14();
            Option x$58 = hiveTable.copy$default$15();
            Option x$59 = hiveTable.copy$default$16();
            Seq x$60 = hiveTable.copy$default$17();
            boolean x$61 = hiveTable.copy$default$18();
            scala.collection.immutable.Map x$62 = hiveTable.copy$default$20();
            Option x$63 = hiveTable.copy$default$21();
            return hiveTable.copy(x$44, x$45, x$46, x$47, x$48, x$49, x$50, x$51, x$52, x$53, x$54, x$55, x$56, x$57, x$58, x$59, x$60, x$61, false, x$62, x$63);
         } else {
            Option x$24 = HiveExternalCatalog$.MODULE$.org$apache$spark$sql$hive$HiveExternalCatalog$$getBucketSpecFromTableProperties(table);
            TableIdentifier x$25 = hiveTable.copy$default$1();
            CatalogTableType x$26 = hiveTable.copy$default$2();
            CatalogStorageFormat x$27 = hiveTable.copy$default$3();
            Option x$28 = hiveTable.copy$default$5();
            String x$29 = hiveTable.copy$default$8();
            long x$30 = hiveTable.copy$default$9();
            long x$31 = hiveTable.copy$default$10();
            String x$32 = hiveTable.copy$default$11();
            scala.collection.immutable.Map x$33 = hiveTable.copy$default$12();
            Option x$34 = hiveTable.copy$default$13();
            Option x$35 = hiveTable.copy$default$14();
            Option x$36 = hiveTable.copy$default$15();
            Option x$37 = hiveTable.copy$default$16();
            Seq x$38 = hiveTable.copy$default$17();
            boolean x$39 = hiveTable.copy$default$18();
            boolean x$40 = hiveTable.copy$default$19();
            scala.collection.immutable.Map x$41 = hiveTable.copy$default$20();
            Option x$42 = hiveTable.copy$default$21();
            return hiveTable.copy(x$25, x$26, x$27, reorderedSchema, x$28, partColumnNames, x$24, x$29, x$30, x$31, x$32, x$33, x$34, x$35, x$36, x$37, x$38, x$39, x$40, x$41, x$42);
         }
      } else {
         boolean x$64 = false;
         TableIdentifier x$65 = hiveTable.copy$default$1();
         CatalogTableType x$66 = hiveTable.copy$default$2();
         CatalogStorageFormat x$67 = hiveTable.copy$default$3();
         StructType x$68 = hiveTable.copy$default$4();
         Option x$69 = hiveTable.copy$default$5();
         Seq x$70 = hiveTable.copy$default$6();
         Option x$71 = hiveTable.copy$default$7();
         String x$72 = hiveTable.copy$default$8();
         long x$73 = hiveTable.copy$default$9();
         long x$74 = hiveTable.copy$default$10();
         String x$75 = hiveTable.copy$default$11();
         scala.collection.immutable.Map x$76 = hiveTable.copy$default$12();
         Option x$77 = hiveTable.copy$default$13();
         Option x$78 = hiveTable.copy$default$14();
         Option x$79 = hiveTable.copy$default$15();
         Option x$80 = hiveTable.copy$default$16();
         Seq x$81 = hiveTable.copy$default$17();
         boolean x$82 = hiveTable.copy$default$18();
         scala.collection.immutable.Map x$83 = hiveTable.copy$default$20();
         Option x$84 = hiveTable.copy$default$21();
         return hiveTable.copy(x$65, x$66, x$67, x$68, x$69, x$70, x$71, x$72, x$73, x$74, x$75, x$76, x$77, x$78, x$79, x$80, x$81, x$82, false, x$83, x$84);
      }
   }

   private Option getSchemaFromTableProperties(final scala.collection.immutable.Map tableProperties) {
      return org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.readLargeTableProp(tableProperties, HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA()).map((schemaJson) -> {
         StructType parsed = (StructType)org.apache.spark.sql.types.DataType..MODULE$.fromJson(schemaJson);
         return org.apache.spark.sql.catalyst.util.CharVarcharUtils..MODULE$.getRawSchema(parsed);
      });
   }

   private CatalogTable restoreDataSourceTable(final CatalogTable table, final String provider) {
      CatalogStorageFormat storageWithoutHiveGeneratedProperties;
      Seq partColumnNames;
      StructType reorderedSchema;
      Some x$13;
      Option x$17;
      boolean var10000;
      label17: {
         label16: {
            Option tableLocation = this.getLocationFromStorageProps(table);
            CatalogStorageFormat qual$1 = this.updateLocationInStorageProps(table, scala.None..MODULE$);
            Option x$1 = tableLocation.map((x$12x) -> org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.stringToURI(x$12x));
            Option x$2 = qual$1.copy$default$2();
            Option x$3 = qual$1.copy$default$3();
            Option x$4 = qual$1.copy$default$4();
            boolean x$5 = qual$1.copy$default$5();
            scala.collection.immutable.Map x$6 = qual$1.copy$default$6();
            CatalogStorageFormat storageWithLocation = qual$1.copy(x$1, x$2, x$3, x$4, x$5, x$6);
            scala.collection.immutable.Map x$7 = (scala.collection.immutable.Map)storageWithLocation.properties().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$restoreDataSourceTable$2(x0$1)));
            Option x$8 = storageWithLocation.copy$default$1();
            Option x$9 = storageWithLocation.copy$default$2();
            Option x$10 = storageWithLocation.copy$default$3();
            Option x$11 = storageWithLocation.copy$default$4();
            boolean x$12 = storageWithLocation.copy$default$5();
            storageWithoutHiveGeneratedProperties = storageWithLocation.copy(x$8, x$9, x$10, x$11, x$12, x$7);
            Option partitionProvider = table.properties().get(HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER());
            StructType schemaFromTableProps = (StructType)this.getSchemaFromTableProperties(table.properties()).getOrElse(() -> new StructType());
            partColumnNames = HiveExternalCatalog$.MODULE$.org$apache$spark$sql$hive$HiveExternalCatalog$$getPartitionColumnsFromTableProperties(table);
            reorderedSchema = this.reorderSchema(schemaFromTableProps, partColumnNames);
            x$13 = new Some(provider);
            x$17 = HiveExternalCatalog$.MODULE$.org$apache$spark$sql$hive$HiveExternalCatalog$$getBucketSpecFromTableProperties(table);
            Some var29 = new Some(HiveExternalCatalog$.MODULE$.TABLE_PARTITION_PROVIDER_CATALOG());
            if (partitionProvider == null) {
               if (var29 == null) {
                  break label16;
               }
            } else if (partitionProvider.equals(var29)) {
               break label16;
            }

            var10000 = false;
            break label17;
         }

         var10000 = true;
      }

      boolean x$18 = var10000;
      scala.collection.immutable.Map x$19 = (scala.collection.immutable.Map)table.properties().filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$restoreDataSourceTable$4(x0$2)));
      TableIdentifier x$20 = table.copy$default$1();
      CatalogTableType x$21 = table.copy$default$2();
      String x$22 = table.copy$default$8();
      long x$23 = table.copy$default$9();
      long x$24 = table.copy$default$10();
      String x$25 = table.copy$default$11();
      Option x$26 = table.copy$default$13();
      Option x$27 = table.copy$default$14();
      Option x$28 = table.copy$default$15();
      Option x$29 = table.copy$default$16();
      Seq x$30 = table.copy$default$17();
      boolean x$31 = table.copy$default$19();
      scala.collection.immutable.Map x$32 = table.copy$default$20();
      Option x$33 = table.copy$default$21();
      return table.copy(x$20, x$21, storageWithoutHiveGeneratedProperties, reorderedSchema, x$13, partColumnNames, x$17, x$22, x$23, x$24, x$25, x$19, x$26, x$27, x$28, x$29, x$30, x$18, x$31, x$32, x$33);
   }

   public boolean tableExists(final String db, final String table) {
      return BoxesRunTime.unboxToBoolean(this.withClient((JFunction0.mcZ.sp)() -> this.client().tableExists(db, table)));
   }

   public Seq listTables(final String db) {
      return (Seq)this.withClient(() -> {
         this.requireDbExists(db);
         return this.client().listTables(db);
      });
   }

   public Seq listTables(final String db, final String pattern) {
      return (Seq)this.withClient(() -> {
         this.requireDbExists(db);
         return this.client().listTables(db, pattern);
      });
   }

   public Seq listViews(final String db, final String pattern) {
      return (Seq)this.withClient(() -> {
         this.requireDbExists(db);
         return this.client().listTablesByType(db, pattern, org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW());
      });
   }

   public void loadTable(final String db, final String table, final String loadPath, final boolean isOverwrite, final boolean isSrcLocal) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireTableExists(db, table);
         this.client().loadTable(loadPath, db + "." + table, isOverwrite, isSrcLocal);
      });
   }

   public void loadPartition(final String db, final String table, final String loadPath, final scala.collection.immutable.Map partition, final boolean isOverwrite, final boolean inheritTableSpecs, final boolean isSrcLocal) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireTableExists(db, table);
         LinkedHashMap orderedPartitionSpec = new LinkedHashMap();
         this.getTable(db, table).partitionColumnNames().foreach((colName) -> (String)orderedPartitionSpec.put(colName.toLowerCase(), partition.apply(colName)));
         this.client().loadPartition(loadPath, db, table, orderedPartitionSpec, isOverwrite, inheritTableSpecs, isSrcLocal);
      });
   }

   public void loadDynamicPartitions(final String db, final String table, final String loadPath, final scala.collection.immutable.Map partition, final boolean replace, final int numDP) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireTableExists(db, table);
         LinkedHashMap orderedPartitionSpec = new LinkedHashMap();
         this.getTable(db, table).partitionColumnNames().foreach((colName) -> (String)orderedPartitionSpec.put(colName.toLowerCase(), partition.apply(colName)));
         this.client().loadDynamicPartitions(loadPath, db, table, orderedPartitionSpec, replace, numDP);
      });
   }

   private scala.collection.immutable.Map toMetaStorePartitionSpec(final scala.collection.immutable.Map spec) {
      scala.collection.immutable.Map lowNames = (scala.collection.immutable.Map)spec.map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k.toLowerCase()), v);
         } else {
            throw new MatchError(x0$1);
         }
      });
      return org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.convertNullPartitionValues(lowNames);
   }

   private scala.collection.immutable.Map buildLowerCasePartColNameMap(final CatalogTable table) {
      Seq actualPartColNames = table.partitionColumnNames();
      return ((IterableOnceOps)actualPartColNames.map((colName) -> new Tuple2(colName.toLowerCase(), colName))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   private scala.collection.immutable.Map restorePartitionSpec(final scala.collection.immutable.Map spec, final scala.collection.immutable.Map partColMap) {
      return (scala.collection.immutable.Map)spec.map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(partColMap.apply(k.toLowerCase())), v);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   private scala.collection.immutable.Map restorePartitionSpec(final scala.collection.immutable.Map spec, final Seq partCols) {
      return (scala.collection.immutable.Map)spec.map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(partCols.find((x$13) -> BoxesRunTime.boxToBoolean($anonfun$restorePartitionSpec$3(k, x$13))).get()), v);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public void createPartitions(final String db, final String table, final Seq parts, final boolean ignoreIfExists) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireTableExists(db, table);
         CatalogTable tableMeta = this.getTable(db, table);
         Seq partitionColumnNames = tableMeta.partitionColumnNames();
         Path tablePath = new Path(tableMeta.location());
         Seq partsWithLocation = (Seq)parts.map((p) -> {
            Path partitionPath = (Path)p.storage().locationUri().map((uri) -> new Path(uri)).getOrElse(() -> org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.generatePartitionPath(p.spec(), partitionColumnNames, tablePath));
            CatalogStorageFormat x$1 = p.storage().copy(new Some(partitionPath.toUri()), p.storage().copy$default$2(), p.storage().copy$default$3(), p.storage().copy$default$4(), p.storage().copy$default$5(), p.storage().copy$default$6());
            scala.collection.immutable.Map x$2 = p.copy$default$1();
            scala.collection.immutable.Map x$3 = p.copy$default$3();
            long x$4 = p.copy$default$4();
            long x$5 = p.copy$default$5();
            Option x$6 = p.copy$default$6();
            return p.copy(x$2, x$1, x$3, x$4, x$5, x$6);
         });
         Seq metaStoreParts = (Seq)partsWithLocation.map((p) -> p.copy(this.toMetaStorePartitionSpec(p.spec()), p.copy$default$2(), p.copy$default$3(), p.copy$default$4(), p.copy$default$5(), p.copy$default$6()));
         this.client().createPartitions(tableMeta, metaStoreParts, ignoreIfExists);
      });
   }

   public void dropPartitions(final String db, final String table, final Seq parts, final boolean ignoreIfNotExists, final boolean purge, final boolean retainData) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireTableExists(db, table);
         this.client().dropPartitions(db, table, (Seq)parts.map((spec) -> this.toMetaStorePartitionSpec(spec)), ignoreIfNotExists, purge, retainData);
      });
   }

   public void renamePartitions(final String db, final String table, final Seq specs, final Seq newSpecs) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.client().renamePartitions(db, table, (Seq)specs.map((spec) -> this.toMetaStorePartitionSpec(spec)), (Seq)newSpecs.map((spec) -> this.toMetaStorePartitionSpec(spec)));
         CatalogTable tableMeta = this.getTable(db, table);
         Seq partitionColumnNames = tableMeta.partitionColumnNames();
         boolean hasUpperCasePartitionColumn = partitionColumnNames.exists((col) -> BoxesRunTime.boxToBoolean($anonfun$renamePartitions$4(col)));
         CatalogTableType var10000 = tableMeta.tableType();
         CatalogTableType var8 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.MANAGED();
         if (var10000 == null) {
            if (var8 != null) {
               return;
            }
         } else if (!var10000.equals(var8)) {
            return;
         }

         if (hasUpperCasePartitionColumn) {
            Path tablePath = new Path(tableMeta.location());
            FileSystem fs = tablePath.getFileSystem(this.hadoopConf);
            Seq newParts = (Seq)newSpecs.map((spec) -> {
               Path rightPath = this.renamePartitionDirectory(fs, tablePath, partitionColumnNames, spec);
               CatalogTablePartition partition = this.client().getPartition(db, table, this.toMetaStorePartitionSpec(spec));
               CatalogStorageFormat x$1 = partition.storage().copy(new Some(rightPath.toUri()), partition.storage().copy$default$2(), partition.storage().copy$default$3(), partition.storage().copy$default$4(), partition.storage().copy$default$5(), partition.storage().copy$default$6());
               scala.collection.immutable.Map x$2 = partition.copy$default$1();
               scala.collection.immutable.Map x$3 = partition.copy$default$3();
               long x$4 = partition.copy$default$4();
               long x$5 = partition.copy$default$5();
               Option x$6 = partition.copy$default$6();
               return partition.copy(x$2, x$1, x$3, x$4, x$5, x$6);
            });
            this.alterPartitions(db, table, newParts);
         }
      });
   }

   private Path renamePartitionDirectory(final FileSystem fs, final Path tablePath, final Seq partCols, final scala.collection.immutable.Map newSpec) {
      ObjectRef currentFullPath = ObjectRef.create(tablePath);
      partCols.foreach((col) -> {
         $anonfun$renamePartitionDirectory$1(newSpec, currentFullPath, fs, col);
         return BoxedUnit.UNIT;
      });
      return (Path)currentFullPath.elem;
   }

   private scala.collection.immutable.Map statsToProperties(final CatalogStatistics stats) {
      HashMap statsProperties = new HashMap();
      statsProperties.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(HiveExternalCatalog$.MODULE$.STATISTICS_TOTAL_SIZE()), stats.sizeInBytes().toString()));
      if (stats.rowCount().isDefined()) {
         statsProperties.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(HiveExternalCatalog$.MODULE$.STATISTICS_NUM_ROWS()), ((BigInt)stats.rowCount().get()).toString()));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      stats.colStats().foreach((x0$1) -> {
         $anonfun$statsToProperties$1(statsProperties, x0$1);
         return BoxedUnit.UNIT;
      });
      return statsProperties.toMap(scala..less.colon.less..MODULE$.refl());
   }

   private Option statsFromProperties(final scala.collection.immutable.Map properties, final String table) {
      scala.collection.immutable.Map statsProps = (scala.collection.immutable.Map)properties.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$statsFromProperties$1(x0$1)));
      if (statsProps.isEmpty()) {
         return scala.None..MODULE$;
      } else {
         HashMap colStats = new HashMap();
         scala.collection.immutable.Map colStatsProps = (scala.collection.immutable.Map)((scala.collection.MapOps)properties.filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$statsFromProperties$2(x0$2)))).map((x0$3) -> {
            if (x0$3 != null) {
               String k = (String)x0$3._1();
               String v = (String)x0$3._2();
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(scala.collection.StringOps..MODULE$.drop$extension(scala.Predef..MODULE$.augmentString(k), HiveExternalCatalog$.MODULE$.STATISTICS_COL_STATS_PREFIX().length())), v);
            } else {
               throw new MatchError(x0$3);
            }
         });
         ((IterableOnceOps)((IterableOps)colStatsProps.keys().filter((k) -> BoxesRunTime.boxToBoolean($anonfun$statsFromProperties$4(k)))).map((k) -> scala.collection.StringOps..MODULE$.dropRight$extension(scala.Predef..MODULE$.augmentString(k), org.apache.spark.sql.catalyst.catalog.CatalogColumnStat..MODULE$.KEY_VERSION().length() + 1))).foreach((fieldName) -> {
            $anonfun$statsFromProperties$6(table, colStatsProps, colStats, fieldName);
            return BoxedUnit.UNIT;
         });
         return new Some(new CatalogStatistics(scala.package..MODULE$.BigInt().apply((String)statsProps.apply(HiveExternalCatalog$.MODULE$.STATISTICS_TOTAL_SIZE())), statsProps.get(HiveExternalCatalog$.MODULE$.STATISTICS_NUM_ROWS()).map((x$14) -> scala.package..MODULE$.BigInt().apply(x$14)), colStats.toMap(scala..less.colon.less..MODULE$.refl())));
      }
   }

   public void alterPartitions(final String db, final String table, final Seq newParts) {
      this.withClient((JFunction0.mcV.sp)() -> {
         Seq metaStoreParts = (Seq)newParts.map((p) -> p.copy(this.toMetaStorePartitionSpec(p.spec()), p.copy$default$2(), p.copy$default$3(), p.copy$default$4(), p.copy$default$5(), p.copy$default$6()));
         Seq withStatsProps = (Seq)metaStoreParts.map((p) -> {
            if (p.stats().isDefined()) {
               scala.collection.immutable.Map statsProperties = this.statsToProperties((CatalogStatistics)p.stats().get());
               scala.collection.immutable.Map x$1 = (scala.collection.immutable.Map)p.parameters().$plus$plus(statsProperties);
               scala.collection.immutable.Map x$2 = p.copy$default$1();
               CatalogStorageFormat x$3 = p.copy$default$2();
               long x$4 = p.copy$default$4();
               long x$5 = p.copy$default$5();
               Option x$6 = p.copy$default$6();
               return p.copy(x$2, x$3, x$1, x$4, x$5, x$6);
            } else {
               return p;
            }
         });
         this.client().alterPartitions(db, table, withStatsProps);
      });
   }

   public CatalogTablePartition getPartition(final String db, final String table, final scala.collection.immutable.Map spec) {
      return (CatalogTablePartition)this.withClient(() -> {
         CatalogTablePartition part = this.client().getPartition(db, table, this.toMetaStorePartitionSpec(spec));
         return this.restorePartitionMetadata(part, this.getTable(db, table));
      });
   }

   private CatalogTablePartition restorePartitionMetadata(final CatalogTablePartition partition, final CatalogTable table) {
      scala.collection.immutable.Map restoredSpec = this.restorePartitionSpec(partition.spec(), table.partitionColumnNames());
      Option restoredStats = this.statsFromProperties(partition.parameters(), table.identifier().table());
      if (restoredStats.isDefined()) {
         scala.collection.immutable.Map x$3 = (scala.collection.immutable.Map)partition.parameters().filterNot((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$restorePartitionMetadata$1(x0$1)));
         CatalogStorageFormat x$4 = partition.copy$default$2();
         long x$5 = partition.copy$default$4();
         long x$6 = partition.copy$default$5();
         return partition.copy(restoredSpec, x$4, x$3, x$5, x$6, restoredStats);
      } else {
         return partition.copy(restoredSpec, partition.copy$default$2(), partition.copy$default$3(), partition.copy$default$4(), partition.copy$default$5(), partition.copy$default$6());
      }
   }

   public Option getPartitionOption(final String db, final String table, final scala.collection.immutable.Map spec) {
      return (Option)this.withClient(() -> this.client().getPartitionOption(db, table, this.toMetaStorePartitionSpec(spec)).map((part) -> this.restorePartitionMetadata(part, this.getTable(db, table))));
   }

   public Seq listPartitionNames(final String db, final String table, final Option partialSpec) {
      return (Seq)this.withClient(() -> {
         CatalogTable catalogTable = this.getTable(db, table);
         scala.collection.immutable.Map partColNameMap = (scala.collection.immutable.Map)this.buildLowerCasePartColNameMap(catalogTable).transform((x$15, v) -> org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.escapePathName(v));
         Seq clientPartitionNames = this.client().getPartitionNames(catalogTable, partialSpec.map((spec) -> this.toMetaStorePartitionSpec(spec)));
         return (Seq)clientPartitionNames.map((partitionPath) -> {
            Seq partSpec = org.apache.spark.sql.execution.datasources.PartitioningUtils..MODULE$.parsePathFragmentAsSeq(partitionPath);
            return ((IterableOnceOps)partSpec.map((x0$1) -> {
               if (x0$1 != null) {
                  String partName = (String)x0$1._1();
                  String partValue = (String)x0$1._2();
                  String var10000 = (String)partColNameMap.apply(partName.toLowerCase());
                  return var10000 + "=" + org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.escapePathName(partValue);
               } else {
                  throw new MatchError(x0$1);
               }
            })).mkString("/");
         });
      });
   }

   public Option listPartitionNames$default$3() {
      return scala.None..MODULE$;
   }

   public Seq listPartitions(final String db, final String table, final Option partialSpec) {
      return (Seq)this.withClient(() -> {
         CatalogTable catalogTable;
         Seq var10000;
         label14: {
            catalogTable = this.getTable(db, table);
            scala.collection.immutable.Map partColNameMap = this.buildLowerCasePartColNameMap(catalogTable);
            Option metaStoreSpec = partialSpec.map((specx) -> this.toMetaStorePartitionSpec(specx));
            Seq res = (Seq)this.client().getPartitions(db, table, metaStoreSpec).map((part) -> part.copy(this.restorePartitionSpec(part.spec(), partColNameMap), part.copy$default$2(), part.copy$default$3(), part.copy$default$4(), part.copy$default$5(), part.copy$default$6()));
            if (metaStoreSpec instanceof Some var11) {
               scala.collection.immutable.Map spec = (scala.collection.immutable.Map)var11.value();
               if (spec.exists((x$16) -> BoxesRunTime.boxToBoolean($anonfun$listPartitions$4(x$16)))) {
                  var10000 = (Seq)res.filter((p) -> BoxesRunTime.boxToBoolean($anonfun$listPartitions$5(this, spec, p)));
                  break label14;
               }
            }

            var10000 = res;
         }

         Seq parts = var10000;
         return (Seq)parts.map((x$17) -> this.restorePartitionMetadata(x$17, catalogTable));
      });
   }

   public Option listPartitions$default$3() {
      return scala.None..MODULE$;
   }

   public Seq listPartitionsByFilter(final String db, final String table, final Seq predicates, final String defaultTimeZoneId) {
      return (Seq)this.withClient(() -> {
         RawHiveTable rawHiveTable = this.client().getRawHiveTable(db, table);
         CatalogTable catalogTable = this.restoreTableMetadata(rawHiveTable.toCatalogTable());
         scala.collection.immutable.Map partColNameMap = this.buildLowerCasePartColNameMap(catalogTable);
         Seq clientPrunedPartitions = (Seq)this.client().getPartitionsByFilter(rawHiveTable, predicates).map((part) -> {
            part.copy(this.restorePartitionSpec(part.spec(), partColNameMap), part.copy$default$2(), part.copy$default$3(), part.copy$default$4(), part.copy$default$5(), part.copy$default$6());
            return this.restorePartitionMetadata(part, catalogTable);
         });
         return org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.prunePartitionsByFilter(catalogTable, clientPrunedPartitions, predicates, defaultTimeZoneId);
      });
   }

   public void createFunction(final String db, final CatalogFunction funcDefinition) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireDbExists(db);
         String functionName = funcDefinition.identifier().funcName().toLowerCase(Locale.ROOT);
         this.requireFunctionNotExists(db, functionName);
         FunctionIdentifier functionIdentifier = funcDefinition.identifier().copy(functionName, funcDefinition.identifier().copy$default$2(), funcDefinition.identifier().copy$default$3());
         this.client().createFunction(db, funcDefinition.copy(functionIdentifier, funcDefinition.copy$default$2(), funcDefinition.copy$default$3()));
      });
   }

   public void dropFunction(final String db, final String name) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireFunctionExists(db, name);
         this.client().dropFunction(db, name);
      });
   }

   public void alterFunction(final String db, final CatalogFunction funcDefinition) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireDbExists(db);
         String functionName = funcDefinition.identifier().funcName().toLowerCase(Locale.ROOT);
         this.requireFunctionExists(db, functionName);
         FunctionIdentifier functionIdentifier = funcDefinition.identifier().copy(functionName, funcDefinition.identifier().copy$default$2(), funcDefinition.identifier().copy$default$3());
         this.client().alterFunction(db, funcDefinition.copy(functionIdentifier, funcDefinition.copy$default$2(), funcDefinition.copy$default$3()));
      });
   }

   public void renameFunction(final String db, final String oldName, final String newName) {
      this.withClient((JFunction0.mcV.sp)() -> {
         this.requireFunctionExists(db, oldName);
         this.requireFunctionNotExists(db, newName);
         this.client().renameFunction(db, oldName, newName);
      });
   }

   public CatalogFunction getFunction(final String db, final String funcName) {
      return (CatalogFunction)this.withClient(() -> {
         this.requireFunctionExists(db, funcName);
         return this.client().getFunction(db, funcName);
      });
   }

   public boolean functionExists(final String db, final String funcName) {
      return BoxesRunTime.unboxToBoolean(this.withClient((JFunction0.mcZ.sp)() -> {
         this.requireDbExists(db);
         return this.client().functionExists(db, funcName);
      }));
   }

   public Seq listFunctions(final String db, final String pattern) {
      return (Seq)this.withClient(() -> {
         this.requireDbExists(db);
         return this.client().listFunctions(db, pattern);
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$verifyTableProperties$1(final String x$1) {
      return x$1.startsWith(HiveExternalCatalog$.MODULE$.SPARK_SQL_PREFIX());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createTable$2(final StructField f) {
      return !HiveExternalCatalog$.MODULE$.isHiveCompatibleDataType(f.dataType());
   }

   private static final CatalogTable newSparkSQLSpecificMetastoreTable$1(final scala.collection.immutable.Map storagePropsWithLocation$1, final CatalogTable table$1) {
      None x$1 = scala.None..MODULE$;
      Option x$3 = table$1.storage().copy$default$2();
      Option x$4 = table$1.storage().copy$default$3();
      Option x$5 = table$1.storage().copy$default$4();
      boolean x$6 = table$1.storage().copy$default$5();
      CatalogStorageFormat x$7 = table$1.storage().copy(x$1, x$3, x$4, x$5, x$6, storagePropsWithLocation$1);
      StructType x$8 = org.apache.spark.sql.types.StructType..MODULE$.apply((Seq)HiveExternalCatalog$.MODULE$.EMPTY_DATA_SCHEMA().$plus$plus(table$1.partitionSchema()));
      None x$9 = scala.None..MODULE$;
      TableIdentifier x$10 = table$1.copy$default$1();
      CatalogTableType x$11 = table$1.copy$default$2();
      Option x$12 = table$1.copy$default$5();
      Seq x$13 = table$1.copy$default$6();
      String x$14 = table$1.copy$default$8();
      long x$15 = table$1.copy$default$9();
      long x$16 = table$1.copy$default$10();
      String x$17 = table$1.copy$default$11();
      scala.collection.immutable.Map x$18 = table$1.copy$default$12();
      Option x$19 = table$1.copy$default$13();
      Option x$20 = table$1.copy$default$14();
      Option x$21 = table$1.copy$default$15();
      Option x$22 = table$1.copy$default$16();
      Seq x$23 = table$1.copy$default$17();
      boolean x$24 = table$1.copy$default$18();
      boolean x$25 = table$1.copy$default$19();
      scala.collection.immutable.Map x$26 = table$1.copy$default$20();
      Option x$27 = table$1.copy$default$21();
      return table$1.copy(x$10, x$11, x$7, x$8, x$12, x$13, x$9, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21, x$22, x$23, x$24, x$25, x$26, x$27);
   }

   private static final CatalogTable newHiveCompatibleMetastoreTable$1(final HiveSerDe serde, final CatalogTable table$1, final scala.collection.immutable.Map storagePropsWithLocation$1) {
      Object var34;
      label17: {
         label16: {
            CatalogTableType var10000 = table$1.tableType();
            CatalogTableType var4 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.EXTERNAL();
            if (var10000 == null) {
               if (var4 == null) {
                  break label16;
               }
            } else if (var10000.equals(var4)) {
               break label16;
            }

            var34 = scala.None..MODULE$;
            break label17;
         }

         scala.Predef..MODULE$.require(table$1.storage().locationUri().isDefined(), () -> "External file-based data source table must have a `path` entry in storage properties.");
         var34 = new Some(table$1.location());
      }

      Option location = (Option)var34;
      Option x$2 = serde.inputFormat();
      Option x$3 = serde.outputFormat();
      Option x$4 = serde.serde();
      boolean x$6 = table$1.storage().copy$default$5();
      CatalogStorageFormat x$7 = table$1.storage().copy(location, x$2, x$3, x$4, x$6, storagePropsWithLocation$1);
      TableIdentifier x$8 = table$1.copy$default$1();
      CatalogTableType x$9 = table$1.copy$default$2();
      StructType x$10 = table$1.copy$default$4();
      Option x$11 = table$1.copy$default$5();
      Seq x$12 = table$1.copy$default$6();
      Option x$13 = table$1.copy$default$7();
      String x$14 = table$1.copy$default$8();
      long x$15 = table$1.copy$default$9();
      long x$16 = table$1.copy$default$10();
      String x$17 = table$1.copy$default$11();
      scala.collection.immutable.Map x$18 = table$1.copy$default$12();
      Option x$19 = table$1.copy$default$13();
      Option x$20 = table$1.copy$default$14();
      Option x$21 = table$1.copy$default$15();
      Option x$22 = table$1.copy$default$16();
      Seq x$23 = table$1.copy$default$17();
      boolean x$24 = table$1.copy$default$18();
      boolean x$25 = table$1.copy$default$19();
      scala.collection.immutable.Map x$26 = table$1.copy$default$20();
      Option x$27 = table$1.copy$default$21();
      return table$1.copy(x$8, x$9, x$7, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21, x$22, x$23, x$24, x$25, x$26, x$27);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createDataSourceTable$3(final StructField f) {
      return !HiveExternalCatalog$.MODULE$.isHiveCompatibleDataType(f.dataType());
   }

   // $FF: synthetic method
   public static final void $anonfun$tableMetaToTableProps$1(final HashMap properties$1, final String key, final String value) {
      properties$1.put(key, value);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateLocationInStorageProps$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var5;
         label30: {
            String k = (String)x0$1._1();
            String var10000 = k.toLowerCase(Locale.ROOT);
            String var4 = "path";
            if (var10000 == null) {
               if (var4 != null) {
                  break label30;
               }
            } else if (!var10000.equals(var4)) {
               break label30;
            }

            var5 = false;
            return var5;
         }

         var5 = true;
         return var5;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$alterTable$4(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         String k = (String)x0$1._1();
         return k.startsWith(HiveExternalCatalog$.MODULE$.DATASOURCE_PREFIX()) || k.startsWith(HiveExternalCatalog$.MODULE$.STATISTICS_PREFIX()) || k.startsWith(HiveExternalCatalog$.MODULE$.CREATED_SPARK_VERSION());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$alterTable$6(final String x$9) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$9));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$alterTableStats$2(final Tuple2 x$10) {
      return ((String)x$10._1()).startsWith(HiveExternalCatalog$.MODULE$.STATISTICS_PREFIX());
   }

   // $FF: synthetic method
   public static final void $anonfun$restoreTableMetadata$1(final ObjectRef table$6, final StructType schemaFromTableProps) {
      CatalogTable qual$1 = (CatalogTable)table$6.elem;
      TableIdentifier x$2 = qual$1.copy$default$1();
      CatalogTableType x$3 = qual$1.copy$default$2();
      CatalogStorageFormat x$4 = qual$1.copy$default$3();
      Option x$5 = qual$1.copy$default$5();
      Seq x$6 = qual$1.copy$default$6();
      Option x$7 = qual$1.copy$default$7();
      String x$8 = qual$1.copy$default$8();
      long x$9 = qual$1.copy$default$9();
      long x$10 = qual$1.copy$default$10();
      String x$11 = qual$1.copy$default$11();
      scala.collection.immutable.Map x$12 = qual$1.copy$default$12();
      Option x$13 = qual$1.copy$default$13();
      Option x$14 = qual$1.copy$default$14();
      Option x$15 = qual$1.copy$default$15();
      Option x$16 = qual$1.copy$default$16();
      Seq x$17 = qual$1.copy$default$17();
      boolean x$18 = qual$1.copy$default$18();
      boolean x$19 = qual$1.copy$default$19();
      scala.collection.immutable.Map x$20 = qual$1.copy$default$20();
      Option x$21 = qual$1.copy$default$21();
      table$6.elem = qual$1.copy(x$2, x$3, x$4, schemaFromTableProps, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$restoreTableMetadata$3(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String key = (String)x0$1._1();
         return key.startsWith(HiveExternalCatalog$.MODULE$.SPARK_SQL_PREFIX());
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reorderSchema$2(final String partCol$1, final StructField x$11) {
      boolean var3;
      label23: {
         String var10000 = x$11.name();
         if (var10000 == null) {
            if (partCol$1 == null) {
               break label23;
            }
         } else if (var10000.equals(partCol$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reorderSchema$4(final Seq partitionFields$1, final Object elem) {
      return partitionFields$1.contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$restoreDataSourceTable$2(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return !HiveExternalCatalog$.MODULE$.HIVE_GENERATED_STORAGE_PROPERTIES().apply(k);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$restoreDataSourceTable$4(final Tuple2 x0$2) {
      if (x0$2 != null) {
         String k = (String)x0$2._1();
         return !HiveExternalCatalog$.MODULE$.HIVE_GENERATED_TABLE_PROPERTIES().apply(k);
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$restorePartitionSpec$3(final String k$1, final String x$13) {
      return x$13.equalsIgnoreCase(k$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$renamePartitions$4(final String col) {
      boolean var2;
      label23: {
         String var10000 = col.toLowerCase();
         if (var10000 == null) {
            if (col != null) {
               break label23;
            }
         } else if (!var10000.equals(col)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final void $anonfun$renamePartitionDirectory$1(final scala.collection.immutable.Map newSpec$1, final ObjectRef currentFullPath$1, final FileSystem fs$2, final String col) {
      String partValue = (String)newSpec$1.apply(col);
      String expectedPartitionString = org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.getPartitionPathString(col, partValue);
      Path expectedPartitionPath = new Path((Path)currentFullPath$1.elem, expectedPartitionString);
      if (!fs$2.exists(expectedPartitionPath)) {
         String actualPartitionString = org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.getPartitionPathString(col.toLowerCase(), partValue);
         Path actualPartitionPath = new Path((Path)currentFullPath$1.elem, actualPartitionString);

         try {
            fs$2.mkdirs(expectedPartitionPath);
            if (!fs$2.rename(actualPartitionPath, expectedPartitionPath)) {
               throw new IOException("Renaming partition path from " + actualPartitionPath + " to " + expectedPartitionPath + " returned false");
            }
         } catch (IOException var10) {
            throw new SparkException("Unable to rename partition path from " + actualPartitionPath + " to " + expectedPartitionPath, var10);
         }
      }

      currentFullPath$1.elem = expectedPartitionPath;
   }

   // $FF: synthetic method
   public static final void $anonfun$statsToProperties$1(final HashMap statsProperties$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String colName = (String)x0$1._1();
         CatalogColumnStat colStat = (CatalogColumnStat)x0$1._2();
         colStat.toMap(colName).foreach((x0$2) -> {
            if (x0$2 != null) {
               String k = (String)x0$2._1();
               String v = (String)x0$2._2();
               Predef.ArrowAssoc var10001 = scala.Predef.ArrowAssoc..MODULE$;
               Predef var10002 = scala.Predef..MODULE$;
               String var10003 = HiveExternalCatalog$.MODULE$.STATISTICS_COL_STATS_PREFIX();
               return (HashMap)statsProperties$1.$plus$eq(var10001.$minus$greater$extension(var10002.ArrowAssoc(var10003 + k), v));
            } else {
               throw new MatchError(x0$2);
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$statsFromProperties$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith(HiveExternalCatalog$.MODULE$.STATISTICS_PREFIX());
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$statsFromProperties$2(final Tuple2 x0$2) {
      if (x0$2 != null) {
         String k = (String)x0$2._1();
         return k.startsWith(HiveExternalCatalog$.MODULE$.STATISTICS_COL_STATS_PREFIX());
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$statsFromProperties$4(final String k) {
      return k.endsWith(org.apache.spark.sql.catalyst.catalog.CatalogColumnStat..MODULE$.KEY_VERSION());
   }

   // $FF: synthetic method
   public static final void $anonfun$statsFromProperties$6(final String table$15, final scala.collection.immutable.Map colStatsProps$1, final HashMap colStats$1, final String fieldName) {
      org.apache.spark.sql.catalyst.catalog.CatalogColumnStat..MODULE$.fromMap(table$15, fieldName, colStatsProps$1).foreach((cs) -> (HashMap)colStats$1.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(fieldName), cs)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$restorePartitionMetadata$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String key = (String)x0$1._1();
         return key.startsWith(HiveExternalCatalog$.MODULE$.SPARK_SQL_PREFIX());
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listPartitions$4(final Tuple2 x$16) {
      return ((String)x$16._2()).contains(".");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listPartitions$5(final HiveExternalCatalog $this, final scala.collection.immutable.Map spec$3, final CatalogTablePartition p) {
      return org.apache.spark.sql.catalyst.catalog.ExternalCatalogUtils..MODULE$.isPartialPartitionSpec(spec$3, $this.toMetaStorePartitionSpec(p.spec()));
   }

   public HiveExternalCatalog(final SparkConf conf, final Configuration hadoopConf) {
      this.conf = conf;
      this.hadoopConf = hadoopConf;
      ExternalCatalog.$init$(this);
      Logging.$init$(this);
      this.clientExceptions = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{HiveException.class.getCanonicalName(), TException.class.getCanonicalName(), InvocationTargetException.class.getCanonicalName()})));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
