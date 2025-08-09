package org.apache.spark.sql.hive.client;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.api.AlreadyExistsException;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.processors.CommandProcessor;
import org.apache.hadoop.hive.ql.processors.CommandProcessorFactory;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.SparkThrowable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.analysis.DatabaseAlreadyExistsException;
import org.apache.spark.sql.catalyst.analysis.NoSuchDatabaseException;
import org.apache.spark.sql.catalyst.analysis.NoSuchPartitionException;
import org.apache.spark.sql.catalyst.analysis.NoSuchPartitionsException;
import org.apache.spark.sql.catalyst.analysis.NoSuchTableException;
import org.apache.spark.sql.catalyst.analysis.PartitionsAlreadyExistException;
import org.apache.spark.sql.catalyst.catalog.BucketSpec;
import org.apache.spark.sql.catalyst.catalog.CatalogDatabase;
import org.apache.spark.sql.catalyst.catalog.CatalogFunction;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTablePartition;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.errors.QueryExecutionErrors.;
import org.apache.spark.sql.execution.QueryExecutionException;
import org.apache.spark.sql.hive.HiveExternalCatalog$;
import org.apache.spark.sql.hive.HiveUtils$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.CircularBuffer;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u00195gA\u0002;v\u0001]\f\u0019\u0001\u0003\u0006\u0002&\u0001\u0011)\u0019!C!\u0003SA!\"!\u000f\u0001\u0005\u0003\u0005\u000b\u0011BA\u0016\u0011)\tY\u0004\u0001B\u0001B\u0003%\u0011Q\b\u0005\u000b\u00033\u0002!\u0011!Q\u0001\n\u0005m\u0003BCA2\u0001\t\u0005\t\u0015!\u0003\u0002f!Q\u0011\u0011\u0012\u0001\u0003\u0002\u0003\u0006I!a#\t\u0015\u0005E\u0005A!A!\u0002\u0013\t\u0019\n\u0003\u0006\u0002\u001a\u0002\u0011)\u0019!C\u0001\u00037C!\"a)\u0001\u0005\u0003\u0005\u000b\u0011BAO\u0011\u001d\t)\u000b\u0001C\u0001\u0003O3a!!/\u0001\t\u0005m\u0006BCAb\u0017\t\u0015\r\u0011\"\u0011\u0002F\"Q\u0011Q\\\u0006\u0003\u0002\u0003\u0006I!a2\t\u000f\u0005\u00156\u0002\"\u0001\u0002`\"Q\u0011q]\u0006\t\u0006\u0004%\t%!;\t\u000f\u0005m8\u0002\"\u0011\u0002~\"I\u0011q \u0001C\u0002\u0013%!\u0011\u0001\u0005\t\u0005\u001b\u0001\u0001\u0015!\u0003\u0003\u0004!I!q\u0002\u0001C\u0002\u0013%!\u0011\u0003\u0005\t\u00053\u0001\u0001\u0015!\u0003\u0003\u0014!I!1\u0004\u0001C\u0002\u0013\u0005!Q\u0004\u0005\t\u0005W\u0001\u0001\u0015!\u0003\u0003 !9!Q\u0006\u0001\u0005\n\t=\u0002b\u0002B\u0019\u0001\u0011\u0005!1\u0007\u0005\n\u0005\u007f\u0001!\u0019!C!\u0005\u0003B\u0001Ba\u0012\u0001A\u0003%!1\t\u0005\b\u0005\u0013\u0002A\u0011\tB&\u0011%\u0011)\u0006\u0001b\u0001\n\u0013\u00119\u0006\u0003\u0005\u0003`\u0001\u0001\u000b\u0011\u0002B-\u0011%\u0011\t\u0007\u0001b\u0001\n\u0013\u0011\u0019\u0007\u0003\u0005\u0003l\u0001\u0001\u000b\u0011\u0002B3\u0011\u001d\u0011i\u0007\u0001C\u0005\u0005_BqAa%\u0001\t\u0013\u0011)\n\u0003\u0004w\u0001\u0011%!\u0011\u0017\u0005\b\u0005s\u0003A\u0011\u0002B^\u0011\u001d\u0011I\r\u0001C!\u0005;AqAa3\u0001\t\u0003\u0011i\rC\u0004\u0003Z\u0002!\tAa7\t\u000f\tM\b\u0001\"\u0001\u0003v\"9!\u0011 \u0001\u0005\u0002\tm\bb\u0002B\u0000\u0001\u0011%1\u0011\u0001\u0005\b\u0007\u000f\u0001A\u0011IB\u0005\u0011\u001d\u0019y\u0001\u0001C!\u0007#Aqa!\t\u0001\t\u0003\u001a\u0019\u0003C\u0004\u00042\u0001!\tea\r\t\u000f\r]\u0002\u0001\"\u0003\u0004:!I11\n\u0001\u0012\u0002\u0013%1Q\n\u0005\b\u0007G\u0002A\u0011IB3\u0011\u001d\u0019Y\u0007\u0001C!\u0007[Bqa!\u001d\u0001\t\u0003\u001a\u0019\bC\u0004\u0004\u0000\u0001!Ia!!\t\u000f\r-\u0005\u0001\"\u0003\u0004\u000e\"91q\u0013\u0001\u0005B\re\u0005bBBP\u0001\u0011\u00053\u0011\u0015\u0005\b\u0007S\u0003A\u0011IBV\u0011\u001d\u0019\u0019\f\u0001C!\u0007kCqa!0\u0001\t\u0013\u0019y\fC\u0004\u0004F\u0002!\tea2\t\u000f\r=\u0007\u0001\"\u0011\u0004R\"91Q\u001c\u0001\u0005B\r}\u0007bBBt\u0001\u0011\u00053\u0011\u001e\u0005\b\u0007g\u0004A\u0011IB{\u0011\u001d!y\u0001\u0001C!\t#Aq\u0001b\t\u0001\t\u0003\")\u0003C\u0004\u0005`\u0001!\t\u0005\"\u0019\t\u000f\u00115\u0004\u0001\"\u0011\u0005p!9A\u0011\u0010\u0001\u0005B\u0011m\u0004\"\u0003CC\u0001E\u0005I\u0011\u0001CD\u0011\u001d!Y\t\u0001C!\t\u001bCq\u0001b&\u0001\t\u0003\"I\nC\u0004\u0005\u0018\u0002!I\u0001\")\t\u000f\u0011%\u0006\u0001\"\u0011\u0005,\"9A\u0011\u0019\u0001\u0005B\u0011\r\u0007b\u0002Ca\u0001\u0011\u0005Cq\u0019\u0005\b\t\u001b\u0004A\u0011\tCh\u0011\u001d!y\u000e\u0001C!\tCDq\u0001\":\u0001\t#!9\u000fC\u0005\u0005r\u0002\t\n\u0011\"\u0005\u0005t\"9Aq\u001f\u0001\u0005\u0002\u0011e\bbBC\r\u0001\u0011\u0005Q1\u0004\u0005\b\u000bK\u0001A\u0011AC\u0014\u0011\u001d)9\u0004\u0001C!\u000bsAq!b\u0012\u0001\t\u0003*I\u0005C\u0004\u0006P\u0001!\t%\"\u0015\t\u000f\u0015u\u0003\u0001\"\u0011\u0006`!9QQ\r\u0001\u0005B\u0015\u001d\u0004bBC8\u0001\u0011\u0005S\u0011\u000f\u0005\b\u000bo\u0002A\u0011AC=\u0011\u001d)y\b\u0001C\u0001\u000b\u0003Cq!b!\u0001\t\u0003))i\u0002\u0005\u0006\bVD\ta^CE\r\u001d!X\u000f#\u0001x\u000b\u0017Cq!!*]\t\u0003)i\t\u0003\u0006\u0006\u0010rC)\u0019!C\u0001\u0005\u0003Bq!\"%]\t\u0003)\u0019\nC\u0004\u0006&r#I!b*\t\u000f\u0015MF\f\"\u0001\u00066\"9Q\u0011\u0018/\u0005\n\u0015m\u0006bBCp9\u0012%Q\u0011\u001d\u0005\b\u000b\u007fdF\u0011\u0001D\u0001\u0011\u001d1i\u0001\u0018C\u0001\r\u001fA\u0011B\"\u0006]#\u0003%\ta!\u0014\t\u000f\u0019]A\f\"\u0001\u0007\u001a!9a\u0011\u0006/\u0005\u0002\u0019-\u0002b\u0002D\u00199\u0012\u0005a1\u0007\u0005\b\r{aF\u0011\u0002D \u0011%1i\u0005\u0018b\u0001\n\u00131y\u0005\u0003\u0005\u0007bq\u0003\u000b\u0011\u0002D)\u0011\u001d1\u0019\u0007\u0018C\u0001\rKB\u0011Bb\u001d]#\u0003%\tA\"\u001e\t\u0013\u0019eD,%A\u0005\u0002\u0019m\u0004b\u0002D@9\u0012\u0005a\u0011\u0011\u0005\b\r\u001fcF\u0011\u0002DI\u0011\u001d1y\t\u0018C\u0005\rGCqAb0]\t\u00131\tM\u0001\bISZ,7\t\\5f]RLU\u000e\u001d7\u000b\u0005Y<\u0018AB2mS\u0016tGO\u0003\u0002ys\u0006!\u0001.\u001b<f\u0015\tQ80A\u0002tc2T!\u0001`?\u0002\u000bM\u0004\u0018M]6\u000b\u0005y|\u0018AB1qC\u000eDWM\u0003\u0002\u0002\u0002\u0005\u0019qN]4\u0014\u000f\u0001\t)!!\u0005\u0002\u001aA!\u0011qAA\u0007\u001b\t\tIA\u0003\u0002\u0002\f\u0005)1oY1mC&!\u0011qBA\u0005\u0005\u0019\te.\u001f*fMB!\u00111CA\u000b\u001b\u0005)\u0018bAA\fk\nQ\u0001*\u001b<f\u00072LWM\u001c;\u0011\t\u0005m\u0011\u0011E\u0007\u0003\u0003;Q1!a\b|\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA\u0012\u0003;\u0011q\u0001T8hO&tw-A\u0004wKJ\u001c\u0018n\u001c8\u0004\u0001U\u0011\u00111\u0006\t\u0005\u0003[\t\u0019D\u0004\u0003\u0002\u0014\u0005=\u0012bAA\u0019k\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\u001b\u0003o\u00111\u0002S5wKZ+'o]5p]*\u0019\u0011\u0011G;\u0002\u0011Y,'o]5p]\u0002\nAb^1sK\"|Wo]3ESJ\u0004b!a\u0002\u0002@\u0005\r\u0013\u0002BA!\u0003\u0013\u0011aa\u00149uS>t\u0007\u0003BA#\u0003'rA!a\u0012\u0002PA!\u0011\u0011JA\u0005\u001b\t\tYE\u0003\u0003\u0002N\u0005\u001d\u0012A\u0002\u001fs_>$h(\u0003\u0003\u0002R\u0005%\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0002V\u0005]#AB*ue&twM\u0003\u0003\u0002R\u0005%\u0011!C:qCJ\\7i\u001c8g!\u0011\ti&a\u0018\u000e\u0003mL1!!\u0019|\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0006iC\u0012|w\u000e]\"p]\u001a\u0004b!a\u001a\u0002r\u0005UTBAA5\u0015\u0011\tY'!\u001c\u0002\t1\fgn\u001a\u0006\u0003\u0003_\nAA[1wC&!\u00111OA5\u0005!IE/\u001a:bE2,\u0007\u0003CA<\u0003\u0007\u000b\u0019%a\u0011\u000f\t\u0005e\u0014qP\u0007\u0003\u0003wRA!! \u0002n\u0005!Q\u000f^5m\u0013\u0011\t\t)a\u001f\u0002\u00075\u000b\u0007/\u0003\u0003\u0002\u0006\u0006\u001d%!B#oiJL(\u0002BAA\u0003w\n1\"\u001a=ue\u0006\u001cuN\u001c4jOBA\u0011QIAG\u0003\u0007\n\u0019%\u0003\u0003\u0002\u0010\u0006]#aA'ba\u0006y\u0011N\\5u\u00072\f7o\u001d'pC\u0012,'\u000f\u0005\u0003\u0002h\u0005U\u0015\u0002BAL\u0003S\u00121b\u00117bgNdu.\u00193fe\u0006a1\r\\5f]Rdu.\u00193feV\u0011\u0011Q\u0014\t\u0005\u0003'\ty*C\u0002\u0002\"V\u0014A#S:pY\u0006$X\rZ\"mS\u0016tG\u000fT8bI\u0016\u0014\u0018!D2mS\u0016tG\u000fT8bI\u0016\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0011\u0003S\u000bY+!,\u00020\u0006E\u00161WA[\u0003o\u00032!a\u0005\u0001\u0011\u001d\t)C\u0003a\u0001\u0003WAq!a\u000f\u000b\u0001\u0004\ti\u0004C\u0004\u0002Z)\u0001\r!a\u0017\t\u000f\u0005\r$\u00021\u0001\u0002f!9\u0011\u0011\u0012\u0006A\u0002\u0005-\u0005bBAI\u0015\u0001\u0007\u00111\u0013\u0005\b\u00033S\u0001\u0019AAO\u0005A\u0011\u0016m\u001e%jm\u0016$\u0016M\u00197f\u00136\u0004HnE\u0003\f\u0003\u000b\ti\f\u0005\u0003\u0002\u0014\u0005}\u0016bAAak\na!+Y<ISZ,G+\u00192mK\u0006A!/Y<UC\ndW-\u0006\u0002\u0002HB!\u0011\u0011ZAm\u001b\t\tYM\u0003\u0003\u0002N\u0006=\u0017\u0001C7fi\u0006$\u0017\r^1\u000b\t\u0005E\u00171[\u0001\u0003c2T1\u0001_Ak\u0015\r\t9.`\u0001\u0007Q\u0006$wn\u001c9\n\t\u0005m\u00171\u001a\u0002\u0006)\u0006\u0014G.Z\u0001\ne\u0006<H+\u00192mK\u0002\"B!!9\u0002fB\u0019\u00111]\u0006\u000e\u0003\u0001Aq!a1\u000f\u0001\u0004\t9-\u0001\bu_\u000e\u000bG/\u00197pOR\u000b'\r\\3\u0016\u0005\u0005-\b\u0003BAw\u0003ol!!a<\u000b\t\u0005E\u00181_\u0001\bG\u0006$\u0018\r\\8h\u0015\r\t)0_\u0001\tG\u0006$\u0018\r\\=ti&!\u0011\u0011`Ax\u00051\u0019\u0015\r^1m_\u001e$\u0016M\u00197f\u00039A\u0017N^3UC\ndW\r\u0015:paN$\"!a#\u0002\u0019=,H\u000f];u\u0005V4g-\u001a:\u0016\u0005\t\r\u0001\u0003\u0002B\u0003\u0005\u0013i!Aa\u0002\u000b\u0007\u0005u40\u0003\u0003\u0003\f\t\u001d!AD\"je\u000e,H.\u0019:Ck\u001a4WM]\u0001\u000e_V$\b/\u001e;Ck\u001a4WM\u001d\u0011\u0002\tMD\u0017.\\\u000b\u0003\u0005'\u0001B!a\u0005\u0003\u0016%\u0019!qC;\u0003\u0013MC\u0017.\\0we}\u0003\u0014!B:iS6\u0004\u0013!B:uCR,WC\u0001B\u0010!\u0011\u0011\tCa\n\u000e\u0005\t\r\"\u0002\u0002B\u0013\u0003\u001f\fqa]3tg&|g.\u0003\u0003\u0003*\t\r\"\u0001D*fgNLwN\\*uCR,\u0017AB:uCR,\u0007%\u0001\u0005oK^\u001cF/\u0019;f)\t\u0011y\"\u0001\u0003d_:4WC\u0001B\u001b!\u0011\u00119Da\u000f\u000e\u0005\te\"\u0002\u0002B\u0019\u0003'LAA!\u0010\u0003:\tA\u0001*\u001b<f\u0007>tg-\u0001\u0005vg\u0016\u0014h*Y7f+\t\u0011\u0019\u0005\u0005\u0003\u0002h\t\u0015\u0013\u0002BA+\u0003S\n\u0011\"^:fe:\u000bW.\u001a\u0011\u0002\u000f\u001d,GoQ8oMR1\u00111\tB'\u0005#BqAa\u0014\u001c\u0001\u0004\t\u0019%A\u0002lKfDqAa\u0015\u001c\u0001\u0004\t\u0019%\u0001\u0007eK\u001a\fW\u000f\u001c;WC2,X-\u0001\u0006sKR\u0014\u0018\u0010T5nSR,\"A!\u0017\u0011\t\u0005\u001d!1L\u0005\u0005\u0005;\nIAA\u0002J]R\f1B]3uefd\u0015.\\5uA\u0005\u0001\"/\u001a;ss\u0012+G.Y=NS2d\u0017n]\u000b\u0003\u0005K\u0002B!a\u0002\u0003h%!!\u0011NA\u0005\u0005\u0011auN\\4\u0002#I,GO]=EK2\f\u00170T5mY&\u001c\b%A\u0006sKR\u0014\u0018\u0010T8dW\u0016$W\u0003\u0002B9\u0005o\"BAa\u001d\u0003\nB!!Q\u000fB<\u0019\u0001!qA!\u001f!\u0005\u0004\u0011YHA\u0001B#\u0011\u0011iHa!\u0011\t\u0005\u001d!qP\u0005\u0005\u0005\u0003\u000bIAA\u0004O_RD\u0017N\\4\u0011\t\u0005\u001d!QQ\u0005\u0005\u0005\u000f\u000bIAA\u0002B]fD\u0001Ba#!\t\u0003\u0007!QR\u0001\u0002MB1\u0011q\u0001BH\u0005gJAA!%\u0002\n\tAAHY=oC6,g(\u0001\bdCV\u001cX\r\u001a\"z)\"\u0014\u0018N\u001a;\u0015\t\t]%Q\u0014\t\u0005\u0003\u000f\u0011I*\u0003\u0003\u0003\u001c\u0006%!a\u0002\"p_2,\u0017M\u001c\u0005\b\u0005?\u000b\u0003\u0019\u0001BQ\u0003\u0005)\u0007\u0003\u0002BR\u0005WsAA!*\u0003*:!\u0011\u0011\nBT\u0013\t\tY!\u0003\u0003\u00022\u0005%\u0011\u0002\u0002BW\u0005_\u0013\u0011\u0002\u00165s_^\f'\r\\3\u000b\t\u0005E\u0012\u0011B\u000b\u0003\u0005g\u0003B!!3\u00036&!!qWAf\u0005\u0011A\u0015N^3\u0002\u00115\u001c8\t\\5f]R,\"A!0\u0011\t\t}&QY\u0007\u0003\u0005\u0003TAAa1\u0002T\u0006IQ.\u001a;bgR|'/Z\u0005\u0005\u0005\u000f\u0014\tM\u0001\tJ\u001b\u0016$\u0018m\u0015;pe\u0016\u001cE.[3oi\u0006Aq-\u001a;Ti\u0006$X-A\u0007xSRD\u0007*\u001b<f'R\fG/Z\u000b\u0005\u0005\u001f\u0014\u0019\u000e\u0006\u0003\u0003R\nU\u0007\u0003\u0002B;\u0005'$qA!\u001f&\u0005\u0004\u0011Y\b\u0003\u0005\u0003\f\u0016\"\t\u0019\u0001Bl!\u0019\t9Aa$\u0003R\u000611/\u001a;PkR$BA!8\u0003dB!\u0011q\u0001Bp\u0013\u0011\u0011\t/!\u0003\u0003\tUs\u0017\u000e\u001e\u0005\b\u0005K4\u0003\u0019\u0001Bt\u0003\u0019\u0019HO]3b[B!!\u0011\u001eBx\u001b\t\u0011YO\u0003\u0003\u0003n\u00065\u0014AA5p\u0013\u0011\u0011\tPa;\u0003\u0017A\u0013\u0018N\u001c;TiJ,\u0017-\\\u0001\bg\u0016$\u0018J\u001c4p)\u0011\u0011iNa>\t\u000f\t\u0015x\u00051\u0001\u0003h\u0006A1/\u001a;FeJ|'\u000f\u0006\u0003\u0003^\nu\bb\u0002BsQ\u0001\u0007!q]\u0001\u0016g\u0016$8)\u001e:sK:$H)\u0019;bE\u0006\u001cXMU1x)\u0011\u0011ina\u0001\t\u000f\r\u0015\u0011\u00061\u0001\u0002D\u0005\u0011AMY\u0001\u0013g\u0016$8)\u001e:sK:$H)\u0019;bE\u0006\u001cX\r\u0006\u0003\u0003^\u000e-\u0001bBB\u0007U\u0001\u0007\u00111I\u0001\rI\u0006$\u0018MY1tK:\u000bW.Z\u0001\u000fGJ,\u0017\r^3ECR\f'-Y:f)\u0019\u0011ina\u0005\u0004\u001e!91QC\u0016A\u0002\r]\u0011\u0001\u00033bi\u0006\u0014\u0017m]3\u0011\t\u000558\u0011D\u0005\u0005\u00077\tyOA\bDCR\fGn\\4ECR\f'-Y:f\u0011\u001d\u0019yb\u000ba\u0001\u0005/\u000ba\"[4o_J,\u0017JZ#ySN$8/\u0001\u0007ee>\u0004H)\u0019;bE\u0006\u001cX\r\u0006\u0005\u0003^\u000e\u00152\u0011FB\u0017\u0011\u001d\u00199\u0003\fa\u0001\u0003\u0007\nAA\\1nK\"911\u0006\u0017A\u0002\t]\u0015!E5h]>\u0014X-\u00134O_R,\u00050[:ug\"91q\u0006\u0017A\u0002\t]\u0015aB2bg\u000e\fG-Z\u0001\u000eC2$XM\u001d#bi\u0006\u0014\u0017m]3\u0015\t\tu7Q\u0007\u0005\b\u0007+i\u0003\u0019AB\f\u00039!x\u000eS5wK\u0012\u000bG/\u00192bg\u0016$baa\u000f\u0004H\r%\u0003\u0003BB\u001f\u0007\u0007j!aa\u0010\u000b\t\r\u0005#\u0011Y\u0001\u0004CBL\u0017\u0002BB#\u0007\u007f\u0011\u0001\u0002R1uC\n\f7/\u001a\u0005\b\u0007+q\u0003\u0019AB\f\u0011%\u0011yD\fI\u0001\u0002\u0004\ti$\u0001\ru_\"Kg/\u001a#bi\u0006\u0014\u0017m]3%I\u00164\u0017-\u001e7uII*\"aa\u0014+\t\u0005u2\u0011K\u0016\u0003\u0007'\u0002Ba!\u0016\u0004`5\u00111q\u000b\u0006\u0005\u00073\u001aY&A\u0005v]\u000eDWmY6fI*!1QLA\u0005\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0007C\u001a9FA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1bZ3u\t\u0006$\u0018MY1tKR!1qCB4\u0011\u001d\u0019I\u0007\ra\u0001\u0003\u0007\na\u0001\u001a2OC6,\u0017A\u00043bi\u0006\u0014\u0017m]3Fq&\u001cHo\u001d\u000b\u0005\u0005/\u001by\u0007C\u0004\u0004jE\u0002\r!a\u0011\u0002\u001b1L7\u000f\u001e#bi\u0006\u0014\u0017m]3t)\u0011\u0019)ha\u001f\u0011\r\t\r6qOA\"\u0013\u0011\u0019IHa,\u0003\u0007M+\u0017\u000fC\u0004\u0004~I\u0002\r!a\u0011\u0002\u000fA\fG\u000f^3s]\u0006\tr-\u001a;SC^$\u0016M\u00197f\u001fB$\u0018n\u001c8\u0015\r\r\r5QQBD!\u0019\t9!a\u0010\u0002H\"91\u0011N\u001aA\u0002\u0005\r\u0003bBBEg\u0001\u0007\u00111I\u0001\ni\u0006\u0014G.\u001a(b[\u0016\f!cZ3u%\u0006<H+\u00192mKN\u0014\u0015PT1nKR11qRBI\u0007'\u0003bAa)\u0004x\u0005\u001d\u0007bBB5i\u0001\u0007\u00111\t\u0005\b\u0007+#\u0004\u0019AB;\u0003)!\u0018M\u00197f\u001d\u0006lWm]\u0001\fi\u0006\u0014G.Z#ySN$8\u000f\u0006\u0004\u0003\u0018\u000em5Q\u0014\u0005\b\u0007S*\u0004\u0019AA\"\u0011\u001d\u0019I)\u000ea\u0001\u0003\u0007\nqbZ3u)\u0006\u0014G.Z:Cs:\u000bW.\u001a\u000b\u0007\u0007G\u001b)ka*\u0011\r\t\r6qOAv\u0011\u001d\u0019IG\u000ea\u0001\u0003\u0007Bqa!&7\u0001\u0004\u0019)(\u0001\bhKR$\u0016M\u00197f\u001fB$\u0018n\u001c8\u0015\r\r56qVBY!\u0019\t9!a\u0010\u0002l\"91\u0011N\u001cA\u0002\u0005\r\u0003bBBEo\u0001\u0007\u00111I\u0001\u0016O\u0016$(+Y<ISZ,G+\u00192mK>\u0003H/[8o)\u0019\u00199l!/\u0004<B1\u0011qAA \u0003{Cqa!\u001b9\u0001\u0004\t\u0019\u0005C\u0004\u0004\nb\u0002\r!a\u0011\u0002=\r|gN^3si\"Kg/\u001a+bE2,Gk\\\"bi\u0006dwn\u001a+bE2,G\u0003BAv\u0007\u0003Dqaa1:\u0001\u0004\t9-A\u0001i\u0003-\u0019'/Z1uKR\u000b'\r\\3\u0015\r\tu7\u0011ZBg\u0011\u001d\u0019YM\u000fa\u0001\u0003W\fQ\u0001^1cY\u0016Dqaa\b;\u0001\u0004\u00119*A\u0005ee>\u0004H+\u00192mKRQ!Q\\Bj\u0007+\u001c9n!7\t\u000f\r%4\b1\u0001\u0002D!91\u0011R\u001eA\u0002\u0005\r\u0003bBB\u0016w\u0001\u0007!q\u0013\u0005\b\u00077\\\u0004\u0019\u0001BL\u0003\u0015\u0001XO]4f\u0003)\tG\u000e^3s)\u0006\u0014G.\u001a\u000b\t\u0005;\u001c\toa9\u0004f\"91\u0011\u000e\u001fA\u0002\u0005\r\u0003bBBEy\u0001\u0007\u00111\t\u0005\b\u0007\u0017d\u0004\u0019AAv\u0003=\tG\u000e^3s)\u0006\u0014G.\u001a)s_B\u001cHC\u0002Bo\u0007W\u001cy\u000fC\u0004\u0004nv\u0002\r!!0\u0002\u0019I\fw\u000fS5wKR\u000b'\r\\3\t\u000f\rEX\b1\u0001\u0002\f\u0006Aa.Z<Qe>\u00048/\u0001\u000bbYR,'\u000fV1cY\u0016$\u0015\r^1TG\",W.\u0019\u000b\u000b\u0005;\u001c9p!?\u0004|\u0012-\u0001bBB5}\u0001\u0007\u00111\t\u0005\b\u0007\u0013s\u0004\u0019AA\"\u0011\u001d\u0019iP\u0010a\u0001\u0007\u007f\fQB\\3x\t\u0006$\u0018mU2iK6\f\u0007\u0003\u0002C\u0001\t\u000fi!\u0001b\u0001\u000b\u0007\u0011\u0015\u00110A\u0003usB,7/\u0003\u0003\u0005\n\u0011\r!AC*ueV\u001cG\u000fV=qK\"9AQ\u0002 A\u0002\u0005-\u0015aC:dQ\u0016l\u0017\r\u0015:paN\f\u0001c\u0019:fCR,\u0007+\u0019:uSRLwN\\:\u0015\u0011\tuG1\u0003C\u000b\tCAqaa3@\u0001\u0004\tY\u000fC\u0004\u0005\u0018}\u0002\r\u0001\"\u0007\u0002\u000bA\f'\u000f^:\u0011\r\t\r6q\u000fC\u000e!\u0011\ti\u000f\"\b\n\t\u0011}\u0011q\u001e\u0002\u0016\u0007\u0006$\u0018\r\\8h)\u0006\u0014G.\u001a)beRLG/[8o\u0011\u001d\u0019yb\u0010a\u0001\u0005/\u000ba\u0002\u001a:paB\u000b'\u000f^5uS>t7\u000f\u0006\b\u0003^\u0012\u001dB\u0011\u0006C\u0016\t/\"I\u0006b\u0017\t\u000f\r\u0015\u0001\t1\u0001\u0002D!911\u001a!A\u0002\u0005\r\u0003b\u0002C\u0017\u0001\u0002\u0007AqF\u0001\u0006gB,7m\u001d\t\u0007\u0005G\u001b9\b\"\r\u0011\t\u0011MB\u0011\u000b\b\u0005\tk!iE\u0004\u0003\u00058\u0011-c\u0002\u0002C\u001d\t\u0013rA\u0001b\u000f\u0005H9!AQ\bC#\u001d\u0011!y\u0004b\u0011\u000f\t\u0005%C\u0011I\u0005\u0003\u0003\u0003I!A`@\n\u0005ql\u0018B\u0001>|\u0013\r\t)0_\u0005\u0005\u0003c\f\u00190\u0003\u0003\u0005P\u0005=\u0018\u0001D\"bi\u0006dwn\u001a+za\u0016\u001c\u0018\u0002\u0002C*\t+\u0012!\u0003V1cY\u0016\u0004\u0016M\u001d;ji&|gn\u00159fG*!AqJAx\u0011\u001d\u0019Y\u0003\u0011a\u0001\u0005/Cqaa7A\u0001\u0004\u00119\nC\u0004\u0005^\u0001\u0003\rAa&\u0002\u0015I,G/Y5o\t\u0006$\u0018-\u0001\tsK:\fW.\u001a)beRLG/[8ogRQ!Q\u001cC2\tK\"9\u0007\"\u001b\t\u000f\r\u0015\u0011\t1\u0001\u0002D!911Z!A\u0002\u0005\r\u0003b\u0002C\u0017\u0003\u0002\u0007Aq\u0006\u0005\b\tW\n\u0005\u0019\u0001C\u0018\u0003!qWm^*qK\u000e\u001c\u0018aD1mi\u0016\u0014\b+\u0019:uSRLwN\\:\u0015\u0011\tuG\u0011\u000fC:\tkBqa!\u0002C\u0001\u0004\t\u0019\u0005C\u0004\u0004L\n\u0003\r!a\u0011\t\u000f\u0011]$\t1\u0001\u0005\u001a\u0005Aa.Z<QCJ$8/A\thKR\u0004\u0016M\u001d;ji&|gNT1nKN$ba!\u001e\u0005~\u0011}\u0004bBBf\u0007\u0002\u0007\u00111\u001e\u0005\n\t\u0003\u001b\u0005\u0013!a\u0001\t\u0007\u000b1\u0002]1si&\fGn\u00159fGB1\u0011qAA \tc\t1dZ3u!\u0006\u0014H/\u001b;j_:t\u0015-\\3tI\u0011,g-Y;mi\u0012\u0012TC\u0001CEU\u0011!\u0019i!\u0015\u0002%\u001d,G\u000fU1si&$\u0018n\u001c8PaRLwN\u001c\u000b\u0007\t\u001f#\t\nb%\u0011\r\u0005\u001d\u0011q\bC\u000e\u0011\u001d\u0019i/\u0012a\u0001\u0003{Cq\u0001\"&F\u0001\u0004!\t$\u0001\u0003ta\u0016\u001c\u0017!D4fiB\u000b'\u000f^5uS>t7\u000f\u0006\u0005\u0005\u001a\u0011mEQ\u0014CP\u0011\u001d\u0019)A\u0012a\u0001\u0003\u0007Bqaa3G\u0001\u0004\t\u0019\u0005C\u0004\u0005\u0016\u001a\u0003\r\u0001b!\u0015\r\u0011eA1\u0015CT\u0011\u001d!)k\u0012a\u0001\u0003\u000f\f\u0011\u0002[5wKR\u000b'\r\\3\t\u000f\u0011Uu\t1\u0001\u0005\u0004\u0006)r-\u001a;QCJ$\u0018\u000e^5p]N\u0014\u0015PR5mi\u0016\u0014HC\u0002C\r\t[#y\u000bC\u0004\u0004n\"\u0003\r!!0\t\u000f\u0011E\u0006\n1\u0001\u00054\u0006Q\u0001O]3eS\u000e\fG/Z:\u0011\r\t\r6q\u000fC[!\u0011!9\f\"0\u000e\u0005\u0011e&\u0002\u0002C^\u0003g\f1\"\u001a=qe\u0016\u001c8/[8og&!Aq\u0018C]\u0005))\u0005\u0010\u001d:fgNLwN\\\u0001\u000bY&\u001cH\u000fV1cY\u0016\u001cH\u0003BB;\t\u000bDqa!\u001bJ\u0001\u0004\t\u0019\u0005\u0006\u0004\u0004v\u0011%G1\u001a\u0005\b\u0007SR\u0005\u0019AA\"\u0011\u001d\u0019iH\u0013a\u0001\u0003\u0007\n\u0001\u0003\\5tiR\u000b'\r\\3t\u0005f$\u0016\u0010]3\u0015\u0011\rUD\u0011\u001bCj\t+Dqa!\u001bL\u0001\u0004\t\u0019\u0005C\u0004\u0004~-\u0003\r!a\u0011\t\u000f\u0011]7\n1\u0001\u0005Z\u0006IA/\u00192mKRK\b/\u001a\t\u0005\u0003[$Y.\u0003\u0003\u0005^\u0006=(\u0001E\"bi\u0006dwn\u001a+bE2,G+\u001f9f\u0003)\u0011XO\\*rY\"Kg/\u001a\u000b\u0005\u0007k\"\u0019\u000f\u0003\u0004{\u0019\u0002\u0007\u00111I\u0001\beVt\u0007*\u001b<f)\u0019\u0019)\b\";\u0005n\"9A1^'A\u0002\u0005\r\u0013aA2nI\"IAq^'\u0011\u0002\u0003\u0007!\u0011L\u0001\b[\u0006D(k\\<t\u0003E\u0011XO\u001c%jm\u0016$C-\u001a4bk2$HEM\u000b\u0003\tkTCA!\u0017\u0004R\u0005iAn\\1e!\u0006\u0014H/\u001b;j_:$\u0002C!8\u0005|\u0012}X\u0011AC\u0002\u000b\u001b)\t\"\"\u0006\t\u000f\u0011ux\n1\u0001\u0002D\u0005AAn\\1e!\u0006$\b\u000eC\u0004\u0004j=\u0003\r!a\u0011\t\u000f\r%u\n1\u0001\u0002D!9QQA(A\u0002\u0015\u001d\u0011\u0001\u00039beR\u001c\u0006/Z2\u0011\u0011\u0005eT\u0011BA\"\u0003\u0007JA!b\u0003\u0002|\tiA*\u001b8lK\u0012D\u0015m\u001d5NCBDq!b\u0004P\u0001\u0004\u00119*A\u0004sKBd\u0017mY3\t\u000f\u0015Mq\n1\u0001\u0003\u0018\u0006\t\u0012N\u001c5fe&$H+\u00192mKN\u0003XmY:\t\u000f\u0015]q\n1\u0001\u0003\u0018\u0006Q\u0011n]*sG2{7-\u00197\u0002\u00131|\u0017\r\u001a+bE2,GC\u0003Bo\u000b;)y\"\"\t\u0006$!9AQ )A\u0002\u0005\r\u0003bBBE!\u0002\u0007\u00111\t\u0005\b\u000b\u001f\u0001\u0006\u0019\u0001BL\u0011\u001d)9\u0002\u0015a\u0001\u0005/\u000bQ\u0003\\8bI\u0012Kh.Y7jGB\u000b'\u000f^5uS>t7\u000f\u0006\b\u0003^\u0016%R1FC\u0017\u000b_)\t$b\r\t\u000f\u0011u\u0018\u000b1\u0001\u0002D!91\u0011N)A\u0002\u0005\r\u0003bBBE#\u0002\u0007\u00111\t\u0005\b\u000b\u000b\t\u0006\u0019AC\u0004\u0011\u001d)y!\u0015a\u0001\u0005/Cq!\"\u000eR\u0001\u0004\u0011I&A\u0003ok6$\u0005+\u0001\bde\u0016\fG/\u001a$v]\u000e$\u0018n\u001c8\u0015\r\tuW1HC\u001f\u0011\u001d\u0019)A\u0015a\u0001\u0003\u0007Bq!b\u0010S\u0001\u0004)\t%\u0001\u0003gk:\u001c\u0007\u0003BAw\u000b\u0007JA!\"\u0012\u0002p\ny1)\u0019;bY><g)\u001e8di&|g.\u0001\u0007ee>\u0004h)\u001e8di&|g\u000e\u0006\u0004\u0003^\u0016-SQ\n\u0005\b\u0007\u000b\u0019\u0006\u0019AA\"\u0011\u001d\u00199c\u0015a\u0001\u0003\u0007\naB]3oC6,g)\u001e8di&|g\u000e\u0006\u0005\u0003^\u0016MSQKC-\u0011\u001d\u0019)\u0001\u0016a\u0001\u0003\u0007Bq!b\u0016U\u0001\u0004\t\u0019%A\u0004pY\u0012t\u0015-\\3\t\u000f\u0015mC\u000b1\u0001\u0002D\u00059a.Z<OC6,\u0017!D1mi\u0016\u0014h)\u001e8di&|g\u000e\u0006\u0004\u0003^\u0016\u0005T1\r\u0005\b\u0007\u000b)\u0006\u0019AA\"\u0011\u001d)y$\u0016a\u0001\u000b\u0003\n\u0011cZ3u\rVt7\r^5p]>\u0003H/[8o)\u0019)I'b\u001b\u0006nA1\u0011qAA \u000b\u0003Bqa!\u0002W\u0001\u0004\t\u0019\u0005C\u0004\u0004(Y\u0003\r!a\u0011\u0002\u001b1L7\u000f\u001e$v]\u000e$\u0018n\u001c8t)\u0019\u0019)(b\u001d\u0006v!91QA,A\u0002\u0005\r\u0003bBB?/\u0002\u0007\u00111I\u0001\u0007C\u0012$'*\u0019:\u0015\t\tuW1\u0010\u0005\b\u000b{B\u0006\u0019AA\"\u0003\u0011\u0001\u0018\r\u001e5\u0002\u00159,woU3tg&|g\u000e\u0006\u0002\u0002*\u0006)!/Z:fiR\u0011!Q\\\u0001\u000f\u0011&4Xm\u00117jK:$\u0018*\u001c9m!\r\t\u0019\u0002X\n\u00069\u0006\u0015\u0011\u0011\u0004\u000b\u0003\u000b\u0013\u000bq%\u0013(D\u001f6\u0003\u0016\tV%C\u0019\u0016{\u0006+\u0011*U\u0013RKuJT0U3B+u\f\u0015'B\u0007\u0016Cu\n\u0014#F%\u0006aAo\u001c%jm\u0016\u001cu\u000e\\;n]R!QQSCN!\u0011\u0019i$b&\n\t\u0015e5q\b\u0002\f\r&,G\u000eZ*dQ\u0016l\u0017\rC\u0004\u0006\u001e~\u0003\r!b(\u0002\u0003\r\u0004B\u0001\"\u0001\u0006\"&!Q1\u0015C\u0002\u0005-\u0019FO];di\u001aKW\r\u001c3\u0002'\u001d,Go\u00159be.\u001c\u0016\u000b\u0014#bi\u0006$\u0016\u0010]3\u0015\t\u0015%Vq\u0016\t\u0005\t\u0003)Y+\u0003\u0003\u0006.\u0012\r!\u0001\u0003#bi\u0006$\u0016\u0010]3\t\u000f\u0015E\u0006\r1\u0001\u0006\u0016\u0006\u0011\u0001nY\u0001\u000fMJ|W\u000eS5wK\u000e{G.^7o)\u0011)y*b.\t\u000f\u0015E\u0016\r1\u0001\u0006\u0016\u0006iAo\\%oaV$hi\u001c:nCR$B!\"0\u0006^B1\u0011qMC`\u000b\u0007LA!\"1\u0002j\t)1\t\\1tgB2QQYCj\u000b3\u0004\u0002\"b2\u0006N\u0016EWq[\u0007\u0003\u000b\u0013TA!b3\u0002V\u00061Q.\u00199sK\u0012LA!b4\u0006J\nY\u0011J\u001c9vi\u001a{'/\\1u!\u0011\u0011)(b5\u0005\u0017\u0015U'-!A\u0001\u0002\u000b\u0005!1\u0010\u0002\u0004?\u0012*\u0004\u0003\u0002B;\u000b3$1\"b7c\u0003\u0003\u0005\tQ!\u0001\u0003|\t\u0019q\f\n\u001c\t\u000f\r\u001d\"\r1\u0001\u0002D\u0005qAo\\(viB,HOR8s[\u0006$H\u0003BCr\u000b{\u0004b!a\u001a\u0006@\u0016\u0015\bGBCt\u000bg,I\u0010\u0005\u0005\u0006j\u00165X\u0011_C|\u001b\t)YO\u0003\u0003\u0003n\u0006=\u0017\u0002BCx\u000bW\u0014\u0001\u0003S5wK>+H\u000f];u\r>\u0014X.\u0019;\u0011\t\tUT1\u001f\u0003\f\u000bk\u001c\u0017\u0011!A\u0001\u0006\u0003\u0011YHA\u0002`I]\u0002BA!\u001e\u0006z\u0012YQ1`2\u0002\u0002\u0003\u0005)\u0011\u0001B>\u0005\ryF\u0005\u000f\u0005\b\u0007O\u0019\u0007\u0019AA\"\u0003=!x\u000eS5wKR\u000b'\r\\3UsB,G\u0003\u0002D\u0002\r\u0013\u0001BAa0\u0007\u0006%!aq\u0001Ba\u0005%!\u0016M\u00197f)f\u0004X\rC\u0004\u0007\f\u0011\u0004\r\u0001\"7\u0002!\r\fG/\u00197pOR\u000b'\r\\3UsB,\u0017a\u0003;p\u0011&4X\rV1cY\u0016$b!a2\u0007\u0012\u0019M\u0001bBBfK\u0002\u0007\u00111\u001e\u0005\n\u0005\u007f)\u0007\u0013!a\u0001\u0003{\tQ\u0003^8ISZ,G+\u00192mK\u0012\"WMZ1vYR$#'A\bu_\"Kg/\u001a)beRLG/[8o)\u00191YB\"\t\u0007&A!\u0011\u0011\u001aD\u000f\u0013\u00111y\"a3\u0003\u0013A\u000b'\u000f^5uS>t\u0007b\u0002D\u0012O\u0002\u0007A1D\u0001\u0002a\"9aqE4A\u0002\u0005\u001d\u0017A\u00015u\u0003E1'o\\7ISZ,\u0007+\u0019:uSRLwN\u001c\u000b\u0005\t71i\u0003C\u0004\u00070!\u0004\rAb\u0007\u0002\u0005!\u0004\u0018\u0001F3yiJ\fg)\u001b=fg\u001a{'OT8o-&,w\u000f\u0006\u0003\u00076\u0019e\u0002\u0003BB\u001f\roIA!a7\u0004@!9a1H5A\u0002\u0019U\u0012A\u0002;UC\ndW-A\u0007sK\u0006$\u0007*\u001b<f'R\fGo\u001d\u000b\u0005\r\u00032I\u0005\u0005\u0004\u0002\b\u0005}b1\t\t\u0005\u0003[4)%\u0003\u0003\u0007H\u0005=(!E\"bi\u0006dwnZ*uCRL7\u000f^5dg\"9a1\n6A\u0002\u0005-\u0015A\u00039s_B,'\u000f^5fg\u0006A\u0002*\u001b<f'R\fG/[:uS\u000e\u001c\bK]8qKJ$\u0018.Z:\u0016\u0005\u0019E\u0003C\u0002D*\r;\u0012\u0019%\u0004\u0002\u0007V)!aq\u000bD-\u0003%IW.\\;uC\ndWM\u0003\u0003\u0007\\\u0005%\u0011AC2pY2,7\r^5p]&!aq\fD+\u0005\r\u0019V\r^\u0001\u001a\u0011&4Xm\u0015;bi&\u001cH/[2t!J|\u0007/\u001a:uS\u0016\u001c\b%A\u0006oK^D\u0015N^3D_:4GC\u0003B\u001b\rO2IGb\u001b\u0007n!9\u0011\u0011L7A\u0002\u0005m\u0003bBA2[\u0002\u0007\u0011Q\r\u0005\n\u0003\u0013k\u0007\u0013!a\u0001\u0003\u0017C\u0011Bb\u001cn!\u0003\u0005\rA\"\u001d\u0002\u0017\rd\u0017m]:M_\u0006$WM\u001d\t\u0007\u0003\u000f\ty$a%\u0002+9,w\u000fS5wK\u000e{gN\u001a\u0013eK\u001a\fW\u000f\u001c;%gU\u0011aq\u000f\u0016\u0005\u0003\u0017\u001b\t&A\u000boK^D\u0015N^3D_:4G\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0019u$\u0006\u0002D9\u0007#\nqaZ3u\u0011&4X\r\u0006\u0003\u00034\u001a\r\u0005b\u0002B\u0019a\u0002\u0007aQ\u0011\t\u0005\r\u000f3Y)\u0004\u0002\u0007\n*!!\u0011GAk\u0013\u00111iI\"#\u0003\u001b\r{gNZ5hkJ\fG/[8o\u000359W\r\u001e$jK2$g+\u00197vKV!a1\u0013DL)\u00191)Jb'\u0007 B!!Q\u000fDL\t\u001d1I*\u001db\u0001\u0005w\u0012\u0011\u0001\u0016\u0005\b\r;\u000b\b\u0019\u0001BB\u0003\ry'M\u001b\u0005\b\rC\u000b\b\u0019AA\"\u0003%1\u0017.\u001a7e\u001d\u0006lW-\u0006\u0003\u0007&\u001a%F\u0003\u0003DT\rW3iK\"0\u0011\t\tUd\u0011\u0016\u0003\b\r3\u0013(\u0019\u0001B>\u0011\u001d1iJ\u001da\u0001\u0005\u0007CqAb,s\u0001\u00041\t,A\u0003dY\u0006T(\u0010\r\u0003\u00074\u001ae\u0006CBA#\rk39,\u0003\u0003\u0006B\u0006]\u0003\u0003\u0002B;\rs#ABb/\u0007.\u0006\u0005\t\u0011!B\u0001\u0005w\u00121a\u0018\u0013:\u0011\u001d1\tK\u001da\u0001\u0003\u0007\nQdY8oM&<WO]3NCb$\u0006N]5gi6+7o]1hKNK'0\u001a\u000b\t\u0005;4\u0019Mb2\u0007J\"9aQY:A\u0002\tU\u0012\u0001\u00035jm\u0016\u001cuN\u001c4\t\u000f\te6\u000f1\u0001\u0003>\"9a1Z:A\u0002\te\u0013AD7bq6+7o]1hKNK'0\u001a"
)
public class HiveClientImpl implements HiveClient, Logging {
   private final package.HiveVersion version;
   private final SparkConf sparkConf;
   private final Iterable hadoopConf;
   private final Map extraConfig;
   private final ClassLoader initClassLoader;
   private final IsolatedClientLoader clientLoader;
   private final CircularBuffer outputBuffer;
   private final Shim_v2_0 shim;
   private final SessionState state;
   private final String userName;
   private final int retryLimit;
   private final long retryDelayMillis;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Hive getHive(final Configuration conf) {
      return HiveClientImpl$.MODULE$.getHive(conf);
   }

   public static Option newHiveConf$default$4() {
      return HiveClientImpl$.MODULE$.newHiveConf$default$4();
   }

   public static Map newHiveConf$default$3() {
      return HiveClientImpl$.MODULE$.newHiveConf$default$3();
   }

   public static HiveConf newHiveConf(final SparkConf sparkConf, final Iterable hadoopConf, final Map extraConfig, final Option classLoader) {
      return HiveClientImpl$.MODULE$.newHiveConf(sparkConf, hadoopConf, extraConfig, classLoader);
   }

   public static Table extraFixesForNonView(final Table tTable) {
      return HiveClientImpl$.MODULE$.extraFixesForNonView(tTable);
   }

   public static CatalogTablePartition fromHivePartition(final Partition hp) {
      return HiveClientImpl$.MODULE$.fromHivePartition(hp);
   }

   public static Partition toHivePartition(final CatalogTablePartition p, final org.apache.hadoop.hive.ql.metadata.Table ht) {
      return HiveClientImpl$.MODULE$.toHivePartition(p, ht);
   }

   public static Option toHiveTable$default$2() {
      return HiveClientImpl$.MODULE$.toHiveTable$default$2();
   }

   public static org.apache.hadoop.hive.ql.metadata.Table toHiveTable(final CatalogTable table, final Option userName) {
      return HiveClientImpl$.MODULE$.toHiveTable(table, userName);
   }

   public static TableType toHiveTableType(final CatalogTableType catalogTableType) {
      return HiveClientImpl$.MODULE$.toHiveTableType(catalogTableType);
   }

   public static StructField fromHiveColumn(final FieldSchema hc) {
      return HiveClientImpl$.MODULE$.fromHiveColumn(hc);
   }

   public static FieldSchema toHiveColumn(final StructField c) {
      return HiveClientImpl$.MODULE$.toHiveColumn(c);
   }

   public static String INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER() {
      return HiveClientImpl$.MODULE$.INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER();
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public final CatalogTable getTable(final String dbName, final String tableName) {
      return HiveClient.getTable$(this, dbName, tableName);
   }

   public final RawHiveTable getRawHiveTable(final String dbName, final String tableName) {
      return HiveClient.getRawHiveTable$(this, dbName, tableName);
   }

   public final void alterTable(final CatalogTable table) {
      HiveClient.alterTable$(this, table);
   }

   public final CatalogTablePartition getPartition(final String dbName, final String tableName, final Map spec) {
      return HiveClient.getPartition$(this, dbName, tableName, spec);
   }

   public final Option getPartitionOption(final String db, final String table, final Map spec) {
      return HiveClient.getPartitionOption$(this, db, table, spec);
   }

   public final CatalogFunction getFunction(final String db, final String name) {
      return HiveClient.getFunction$(this, db, name);
   }

   public final boolean functionExists(final String db, final String name) {
      return HiveClient.functionExists$(this, db, name);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public package.HiveVersion version() {
      return this.version;
   }

   public IsolatedClientLoader clientLoader() {
      return this.clientLoader;
   }

   private CircularBuffer outputBuffer() {
      return this.outputBuffer;
   }

   private Shim_v2_0 shim() {
      return this.shim;
   }

   public SessionState state() {
      return this.state;
   }

   private SessionState newState() {
      HiveConf hiveConf = HiveClientImpl$.MODULE$.newHiveConf(this.sparkConf, this.hadoopConf, this.extraConfig, new Some(this.initClassLoader));
      SessionState state = new SessionState(hiveConf);
      if (this.clientLoader().cachedHive() != null) {
         Hive.set((Hive)this.clientLoader().cachedHive());
      }

      state.getConf().setClassLoader(this.clientLoader().classLoader());
      this.shim().setCurrentSessionState(state);
      Class clz = state.getClass().getField("out").getType();
      Constructor ctor = clz.getConstructor(OutputStream.class, Boolean.TYPE, String.class);
      state.getClass().getField("out").set(state, ctor.newInstance(this.outputBuffer(), BoxesRunTime.boxToBoolean(true), StandardCharsets.UTF_8.name()));
      state.getClass().getField("err").set(state, ctor.newInstance(this.outputBuffer(), BoxesRunTime.boxToBoolean(true), StandardCharsets.UTF_8.name()));
      return state;
   }

   public HiveConf conf() {
      HiveConf hiveConf = this.state().getConf();
      String msUri = hiveConf.getVar(HiveConf.getConfVars("hive.metastore.uris"));
      String msConnUrl = hiveConf.getVar(HiveConf.getConfVars("javax.jdo.option.ConnectionURL"));
      boolean isEmbeddedMetaStore = (msUri == null || msUri.trim().isEmpty()) && msConnUrl != null && msConnUrl.startsWith("jdbc:derby");
      if (isEmbeddedMetaStore) {
         hiveConf.setBoolean("hive.metastore.schema.verification", false);
         hiveConf.setBoolean("datanucleus.schema.autoCreateAll", true);
      }

      return hiveConf;
   }

   public String userName() {
      return this.userName;
   }

   public String getConf(final String key, final String defaultValue) {
      return this.conf().get(key, defaultValue);
   }

   private int retryLimit() {
      return this.retryLimit;
   }

   private long retryDelayMillis() {
      return this.retryDelayMillis;
   }

   private Object retryLocked(final Function0 f) {
      Object var2 = new Object();

      try {
         synchronized(this.clientLoader()){}

         try {
            long deadline = System.nanoTime() + (long)((double)((long)this.retryLimit() * this.retryDelayMillis()) * (double)1000000.0F);
            IntRef numTries = IntRef.create(0);
            ObjectRef caughtException = ObjectRef.create((Object)null);

            do {
               ++numTries.elem;
               this.liftedTree1$1(var2, f, caughtException, numTries);
            } while(numTries.elem <= this.retryLimit() && System.nanoTime() < deadline);

            if (System.nanoTime() > deadline) {
               this.logWarning((Function0)(() -> "Deadline exceeded"));
            }

            throw (Exception)caughtException.elem;
         } catch (Throwable var12) {
            throw var12;
         }
      } catch (NonLocalReturnControl var13) {
         if (var13.key() == var2) {
            return var13.value();
         } else {
            throw var13;
         }
      }
   }

   private boolean causedByThrift(final Throwable e) {
      for(Throwable target = e; target != null; target = target.getCause()) {
         String msg = target.getMessage();
         if (msg != null && msg.matches("(?s).*(TApplication|TProtocol|TTransport)Exception.*")) {
            return true;
         }
      }

      return false;
   }

   private Hive client() {
      if (this.clientLoader().cachedHive() != null) {
         return (Hive)this.clientLoader().cachedHive();
      } else {
         Hive c = HiveClientImpl$.MODULE$.getHive(this.conf());
         this.clientLoader().cachedHive_$eq(c);
         return c;
      }
   }

   private IMetaStoreClient msClient() {
      return this.shim().getMSC(this.client());
   }

   public SessionState getState() {
      return (SessionState)this.withHiveState(() -> this.state());
   }

   public Object withHiveState(final Function0 f) {
      return this.retryLocked(() -> {
         ClassLoader original = Thread.currentThread().getContextClassLoader();
         ClassLoader originalConfLoader = this.state().getConf().getClassLoader();
         Thread.currentThread().setContextClassLoader(this.clientLoader().classLoader());
         this.state().getConf().setClassLoader(this.clientLoader().classLoader());
         Hive.set(this.client());
         HiveClientImpl$.MODULE$.getHive(this.conf());
         this.shim().setCurrentSessionState(this.state());

         Thread var10000;
         try {
            f.apply();
         } catch (Throwable var12) {
            if (var12 instanceof NoClassDefFoundError var8) {
               if (var8.getMessage().contains("apache/hadoop/hive/serde2/SerDe")) {
                  throw .MODULE$.serDeInterfaceNotFoundError(var8);
               }
            }

            throw var12;
         } finally {
            this.state().getConf().setClassLoader(originalConfLoader);
            var10000 = Thread.currentThread();
            var10000.setContextClassLoader(original);
         }

         Object ret = var10000;
         return ret;
      });
   }

   public void setOut(final PrintStream stream) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         Constructor ctor = this.state().getClass().getField("out").getType().getConstructor(OutputStream.class);
         this.state().getClass().getField("out").set(this.state(), ctor.newInstance(stream));
      });
   }

   public void setInfo(final PrintStream stream) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         Constructor ctor = this.state().getClass().getField("info").getType().getConstructor(OutputStream.class);
         this.state().getClass().getField("info").set(this.state(), ctor.newInstance(stream));
      });
   }

   public void setError(final PrintStream stream) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         Constructor ctor = this.state().getClass().getField("err").getType().getConstructor(OutputStream.class);
         this.state().getClass().getField("err").set(this.state(), ctor.newInstance(stream));
      });
   }

   private void setCurrentDatabaseRaw(final String db) {
      String var10000 = this.state().getCurrentDatabase();
      if (var10000 == null) {
         if (db == null) {
            return;
         }
      } else if (var10000.equals(db)) {
         return;
      }

      if (this.databaseExists(db)) {
         this.state().setCurrentDatabase(db);
      } else {
         throw new NoSuchDatabaseException(db);
      }
   }

   public void setCurrentDatabase(final String databaseName) {
      this.withHiveState((JFunction0.mcV.sp)() -> this.setCurrentDatabaseRaw(databaseName));
   }

   public void createDatabase(final CatalogDatabase database, final boolean ignoreIfExists) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         Database hiveDb = this.toHiveDatabase(database, new Some(this.userName()));

         try {
            this.shim().createDatabase(this.client(), hiveDb, ignoreIfExists);
         } catch (AlreadyExistsException var4) {
            throw new DatabaseAlreadyExistsException(database.name());
         }
      });
   }

   public void dropDatabase(final String name, final boolean ignoreIfNotExists, final boolean cascade) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         try {
            this.shim().dropDatabase(this.client(), name, true, ignoreIfNotExists, cascade);
         } catch (Throwable var8) {
            if (var8 instanceof HiveException var7) {
               if (var7.getMessage().contains("Database " + name + " is not empty")) {
                  throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.cannotDropNonemptyDatabaseError(name);
               }
            }

            throw var8;
         }
      });
   }

   public void alterDatabase(final CatalogDatabase database) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         URI loc = this.getDatabase(database.name()).locationUri();
         boolean changeLoc = !database.locationUri().equals(loc);
         Database hiveDb = this.toHiveDatabase(database, this.toHiveDatabase$default$2());
         this.shim().alterDatabase(this.client(), database.name(), hiveDb);
         if (changeLoc && this.getDatabase(database.name()).locationUri().equals(loc)) {
            throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.alterDatabaseLocationUnsupportedError();
         }
      });
   }

   private Database toHiveDatabase(final CatalogDatabase database, final Option userName) {
      Map props = database.properties();
      Database hiveDb = new Database(database.name(), database.description(), org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.URIToString(database.locationUri()), scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map)props.$minus$minus(new scala.collection.immutable..colon.colon("owner", scala.collection.immutable.Nil..MODULE$))).asJava());
      props.get("owner").orElse(() -> userName).foreach((ownerName) -> {
         $anonfun$toHiveDatabase$2(this, hiveDb, ownerName);
         return BoxedUnit.UNIT;
      });
      return hiveDb;
   }

   private Option toHiveDatabase$default$2() {
      return scala.None..MODULE$;
   }

   public CatalogDatabase getDatabase(final String dbName) {
      return (CatalogDatabase)this.withHiveState(() -> (CatalogDatabase)scala.Option..MODULE$.apply(this.shim().getDatabase(this.client(), dbName)).map((d) -> {
            Map params = (Map)((MapOps)scala.Option..MODULE$.apply(d.getParameters()).map((x$1) -> scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(x$1).asScala().toMap(scala..less.colon.less..MODULE$.refl())).getOrElse(() -> (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$))).$plus$plus((IterableOnce)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("owner"), this.shim().getDatabaseOwnerName(d))}))));
            return new CatalogDatabase(d.getName(), (String)scala.Option..MODULE$.apply(d.getDescription()).getOrElse(() -> ""), org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.stringToURI(d.getLocationUri()), params);
         }).getOrElse(() -> {
            throw new NoSuchDatabaseException(dbName);
         }));
   }

   public boolean databaseExists(final String dbName) {
      return BoxesRunTime.unboxToBoolean(this.withHiveState((JFunction0.mcZ.sp)() -> this.shim().databaseExists(this.client(), dbName)));
   }

   public Seq listDatabases(final String pattern) {
      return (Seq)this.withHiveState(() -> this.shim().getDatabasesByPattern(this.client(), pattern));
   }

   private Option getRawTableOption(final String dbName, final String tableName) {
      return scala.Option..MODULE$.apply(this.shim().getTable(this.client(), dbName, tableName, false));
   }

   private Seq getRawTablesByName(final String dbName, final Seq tableNames) {
      try {
         this.shim().recordHiveCall();
         return ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.msClient().getTableObjectsByName(dbName, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(tableNames).asJava())).asScala().map((tTable) -> HiveClientImpl$.MODULE$.extraFixesForNonView(tTable))).map((x$2) -> new org.apache.hadoop.hive.ql.metadata.Table(x$2))).toSeq();
      } catch (Exception var4) {
         throw .MODULE$.cannotFetchTablesOfDatabaseError(dbName, var4);
      }
   }

   public boolean tableExists(final String dbName, final String tableName) {
      return BoxesRunTime.unboxToBoolean(this.withHiveState((JFunction0.mcZ.sp)() -> this.getRawTableOption(dbName, tableName).nonEmpty()));
   }

   public Seq getTablesByName(final String dbName, final Seq tableNames) {
      return (Seq)this.withHiveState(() -> (Seq)this.getRawTablesByName(dbName, tableNames).map((h) -> this.org$apache$spark$sql$hive$client$HiveClientImpl$$convertHiveTableToCatalogTable(h)));
   }

   public Option getTableOption(final String dbName, final String tableName) {
      return (Option)this.withHiveState(() -> {
         this.logDebug((Function0)(() -> "Looking up " + dbName + "." + tableName));
         return this.getRawTableOption(dbName, tableName).map((h) -> this.org$apache$spark$sql$hive$client$HiveClientImpl$$convertHiveTableToCatalogTable(h));
      });
   }

   public Option getRawHiveTableOption(final String dbName, final String tableName) {
      return (Option)this.withHiveState(() -> {
         this.logDebug((Function0)(() -> "Looking up " + dbName + "." + tableName));
         return this.getRawTableOption(dbName, tableName).map((x$3) -> this.new RawHiveTableImpl(x$3));
      });
   }

   public CatalogTable org$apache$spark$sql$hive$client$HiveClientImpl$$convertHiveTableToCatalogTable(final org.apache.hadoop.hive.ql.metadata.Table h) {
      Tuple2 var5 = liftedTree2$1(h);
      if (var5 != null) {
         Buffer cols = (Buffer)var5._1();
         Buffer partCols = (Buffer)var5._2();
         Tuple2 var4 = new Tuple2(cols, partCols);
         Buffer cols = (Buffer)var4._1();
         Buffer partCols = (Buffer)var4._2();
         StructType schema = new StructType((StructField[])((IterableOnceOps)cols.$plus$plus(partCols)).toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
         Object var10000;
         if (h.getNumBuckets() > 0) {
            Buffer sortColumnOrders = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(h.getSortCols()).asScala();
            boolean allAscendingSorted = sortColumnOrders.forall((x$6x) -> BoxesRunTime.boxToBoolean($anonfun$convertHiveTableToCatalogTable$4(x$6x)));
            scala.collection.Seq sortColumnNames = allAscendingSorted ? (scala.collection.Seq)sortColumnOrders.map((x$7x) -> x$7x.getCol()) : (scala.collection.Seq)scala.package..MODULE$.Seq().empty();
            var10000 = scala.Option..MODULE$.apply(new BucketSpec(h.getNumBuckets(), scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(h.getBucketCols()).asScala().toSeq(), sortColumnNames.toSeq()));
         } else {
            var10000 = scala.None..MODULE$;
         }

         Option bucketSpec = (Option)var10000;
         ArrayBuffer unsupportedFeatures = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
         if (!h.getSkewedColNames().isEmpty()) {
            unsupportedFeatures.$plus$eq("skewed columns");
         } else {
            BoxedUnit var48 = BoxedUnit.UNIT;
         }

         if (h.getStorageHandler() != null) {
            unsupportedFeatures.$plus$eq("storage handler");
         } else {
            BoxedUnit var49 = BoxedUnit.UNIT;
         }

         label57: {
            label56: {
               TableType var50 = h.getTableType();
               TableType var16 = TableType.VIRTUAL_VIEW;
               if (var50 == null) {
                  if (var16 != null) {
                     break label56;
                  }
               } else if (!var50.equals(var16)) {
                  break label56;
               }

               if (partCols.nonEmpty()) {
                  unsupportedFeatures.$plus$eq("partitioned view");
                  break label57;
               }
            }

            BoxedUnit var51 = BoxedUnit.UNIT;
         }

         Map properties = (Map)scala.Option..MODULE$.apply(h.getParameters()).map((x$8x) -> scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(x$8x).asScala().toMap(scala..less.colon.less..MODULE$.refl())).orNull(scala..less.colon.less..MODULE$.refl());
         scala.collection.mutable.Map ignoredProperties = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
         HiveClientImpl$.MODULE$.org$apache$spark$sql$hive$client$HiveClientImpl$$HiveStatisticsProperties().foreach((key) -> {
            $anonfun$convertHiveTableToCatalogTable$7(properties, ignoredProperties, key);
            return BoxedUnit.UNIT;
         });
         Set excludedTableProperties = (Set)HiveClientImpl$.MODULE$.org$apache$spark$sql$hive$client$HiveClientImpl$$HiveStatisticsProperties().$plus$plus((IterableOnce)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"comment", "collation", "EXTERNAL"}))));
         Map filteredProperties = (Map)properties.filterNot((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$convertHiveTableToCatalogTable$9(excludedTableProperties, x0$1)));
         Option comment = properties.get("comment");
         Option collation = properties.get("collation");
         TableIdentifier x$1 = org.apache.spark.sql.catalyst.TableIdentifier..MODULE$.apply(h.getTableName(), scala.Option..MODULE$.apply(h.getDbName()));
         TableType var25 = h.getTableType();
         CatalogTableType var52;
         if (TableType.EXTERNAL_TABLE.equals(var25)) {
            var52 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.EXTERNAL();
         } else if (TableType.MANAGED_TABLE.equals(var25)) {
            var52 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.MANAGED();
         } else {
            if (!TableType.VIRTUAL_VIEW.equals(var25)) {
               String tableTypeStr = var25.toString().toLowerCase(Locale.ROOT).replace("_", " ");
               throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.hiveTableTypeUnsupportedError(h.getTableName(), tableTypeStr);
            }

            var52 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW();
         }

         CatalogTableType x$2 = var52;
         Seq x$4 = ((IterableOnceOps)partCols.map((x$9x) -> x$9x.name())).toSeq();
         String x$6 = (String)scala.Option..MODULE$.apply(h.getOwner()).getOrElse(() -> "");
         long x$7 = (long)h.getTTable().getCreateTime() * 1000L;
         long x$8 = (long)h.getLastAccessTime() * 1000L;
         CatalogStorageFormat x$9 = new CatalogStorageFormat(this.shim().getDataLocation(h).map((str) -> org.apache.spark.sql.catalyst.catalog.CatalogUtils..MODULE$.stringToURI(str)), scala.Option..MODULE$.apply(h.getTTable().getSd().getInputFormat()).orElse(() -> scala.Option..MODULE$.apply(h.getStorageHandler()).map((x$10) -> x$10.getInputFormatClass().getName())), scala.Option..MODULE$.apply(h.getTTable().getSd().getOutputFormat()).orElse(() -> scala.Option..MODULE$.apply(h.getStorageHandler()).map((x$11) -> x$11.getOutputFormatClass().getName())), scala.Option..MODULE$.apply(h.getSerializationLib()), h.getTTable().getSd().isCompressed(), (Map)scala.Option..MODULE$.apply(h.getTTable().getSd().getSerdeInfo().getParameters()).map((x$12) -> scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(x$12).asScala().toMap(scala..less.colon.less..MODULE$.refl())).orNull(scala..less.colon.less..MODULE$.refl()));
         Option x$11 = HiveClientImpl$.MODULE$.org$apache$spark$sql$hive$client$HiveClientImpl$$readHiveStats(properties);
         Option x$14 = scala.Option..MODULE$.apply(h.getViewOriginalText());
         Option x$15 = scala.Option..MODULE$.apply(h.getViewExpandedText());
         Seq x$16 = unsupportedFeatures.toSeq();
         Map x$17 = ignoredProperties.toMap(scala..less.colon.less..MODULE$.refl());
         Option x$18 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$5();
         String x$19 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$11();
         boolean x$20 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$18();
         boolean x$21 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$19();
         return new CatalogTable(x$1, x$2, x$9, schema, x$18, x$4, bucketSpec, x$6, x$7, x$8, x$19, filteredProperties, x$11, x$15, comment, collation, x$16, x$20, x$21, x$17, x$14);
      } else {
         throw new MatchError(var5);
      }
   }

   public void createTable(final CatalogTable table, final boolean ignoreIfExists) {
      this.withHiveState((JFunction0.mcV.sp)() -> this.shim().createTable(this.client(), HiveClientImpl$.MODULE$.toHiveTable(table, new Some(this.userName())), ignoreIfExists));
   }

   public void dropTable(final String dbName, final String tableName, final boolean ignoreIfNotExists, final boolean purge) {
      this.withHiveState((JFunction0.mcV.sp)() -> this.shim().dropTable(this.client(), dbName, tableName, true, ignoreIfNotExists, purge));
   }

   public void alterTable(final String dbName, final String tableName, final CatalogTable table) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         HiveClientImpl$ var10000 = HiveClientImpl$.MODULE$;
         Map x$1 = (Map)table.ignoredProperties().$plus$plus(table.properties());
         TableIdentifier x$2 = table.copy$default$1();
         CatalogTableType x$3 = table.copy$default$2();
         CatalogStorageFormat x$4 = table.copy$default$3();
         StructType x$5 = table.copy$default$4();
         Option x$6 = table.copy$default$5();
         Seq x$7 = table.copy$default$6();
         Option x$8 = table.copy$default$7();
         String x$9 = table.copy$default$8();
         long x$10 = table.copy$default$9();
         long x$11 = table.copy$default$10();
         String x$12 = table.copy$default$11();
         Option x$13 = table.copy$default$13();
         Option x$14 = table.copy$default$14();
         Option x$15 = table.copy$default$15();
         Option x$16 = table.copy$default$16();
         Seq x$17 = table.copy$default$17();
         boolean x$18 = table.copy$default$18();
         boolean x$19 = table.copy$default$19();
         Map x$20 = table.copy$default$20();
         Option x$21 = table.copy$default$21();
         org.apache.hadoop.hive.ql.metadata.Table hiveTable = var10000.toHiveTable(table.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$1, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21), new Some(this.userName()));
         String qualifiedTableName = dbName + "." + tableName;
         this.shim().alterTable(this.client(), qualifiedTableName, hiveTable);
      });
   }

   public void alterTableProps(final RawHiveTable rawHiveTable, final Map newProps) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         org.apache.hadoop.hive.ql.metadata.Table hiveTable = (org.apache.hadoop.hive.ql.metadata.Table)rawHiveTable.rawTable();
         HashMap newPropsMap = new HashMap();
         newPropsMap.putAll(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(newProps).asJava());
         hiveTable.getTTable().setParameters(newPropsMap);
         this.shim().alterTable(this.client(), hiveTable.getDbName() + "." + hiveTable.getTableName(), hiveTable);
      });
   }

   public void alterTableDataSchema(final String dbName, final String tableName, final StructType newDataSchema, final Map schemaProps) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         org.apache.hadoop.hive.ql.metadata.Table oldTable = this.shim().getTable(this.client(), dbName, tableName, this.shim().getTable$default$4());
         Seq hiveCols = (Seq)newDataSchema.map((c) -> HiveClientImpl$.MODULE$.toHiveColumn(c));
         oldTable.setFields(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(hiveCols).asJava());
         Iterator it = oldTable.getParameters().entrySet().iterator();

         while(it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
            if (org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.isLargeTableProp(HiveExternalCatalog$.MODULE$.DATASOURCE_SCHEMA(), (String)entry.getKey())) {
               it.remove();
            }
         }

         schemaProps.foreach((x0$1) -> {
            $anonfun$alterTableDataSchema$3(oldTable, x0$1);
            return BoxedUnit.UNIT;
         });
         String qualifiedTableName = dbName + "." + tableName;
         this.shim().alterTable(this.client(), qualifiedTableName, oldTable);
      });
   }

   public void createPartitions(final CatalogTable table, final Seq parts, final boolean ignoreIfExists) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         try {
            this.shim().createPartitions(this.client(), HiveClientImpl$.MODULE$.toHiveTable(table, HiveClientImpl$.MODULE$.toHiveTable$default$2()), parts, ignoreIfExists);
         } catch (InvocationTargetException var6) {
            this.replaceExistException$1(var6.getCause(), table, parts);
         } catch (Throwable var7) {
            this.replaceExistException$1(var7, table, parts);
         }

      });
   }

   public void dropPartitions(final String db, final String table, final Seq specs, final boolean ignoreIfNotExists, final boolean purge, final boolean retainData) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         Seq matchingParts = (Seq)((SeqOps)specs.flatMap((s) -> {
            scala.Predef..MODULE$.assert(s.values().forall((x$14) -> BoxesRunTime.boxToBoolean($anonfun$dropPartitions$3(x$14))), () -> "partition spec '" + s + "' is invalid");
            boolean dropPartitionByName = org.apache.spark.sql.internal.SQLConf..MODULE$.get().metastoreDropPartitionsByName();
            if (dropPartitionByName) {
               Seq partitionNames = this.shim().getPartitionNames(this.client(), db, table, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(s).asJava(), (short)-1);
               if (partitionNames.isEmpty() && !ignoreIfNotExists) {
                  throw new NoSuchPartitionsException(db, table, new scala.collection.immutable..colon.colon(s, scala.collection.immutable.Nil..MODULE$));
               } else {
                  return (Seq)partitionNames.map((x$15) -> scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.Predef..MODULE$.wrapRefArray((Object[])HiveUtils$.MODULE$.partitionNameToValues(x$15)).toList()).asJava());
               }
            } else {
               org.apache.hadoop.hive.ql.metadata.Table hiveTable = this.shim().getTable(this.client(), db, table, true);
               Seq parts = this.shim().getPartitions(this.client(), hiveTable, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(s).asJava());
               if (parts.isEmpty() && !ignoreIfNotExists) {
                  throw new NoSuchPartitionsException(db, table, new scala.collection.immutable..colon.colon(s, scala.collection.immutable.Nil..MODULE$));
               } else {
                  return (Seq)parts.map((x$16) -> x$16.getValues());
               }
            }
         })).distinct();
         ArrayBuffer droppedParts = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
         matchingParts.foreach((partition) -> {
            try {
               this.shim().dropPartition(this.client(), db, table, partition, !retainData, purge);
            } catch (Exception var11) {
               Buffer remainingParts = (Buffer)matchingParts.toBuffer().$minus$minus$eq(droppedParts);
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n               |======================\n               |Attempt to drop the partition specs in table '", "' database '", "':\n               |", "\n               |In this attempt, the following partitions have been dropped successfully:\n               |", "\n               |The remaining partitions have not been dropped:\n               |", "\n               |======================\n             "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, table), new MDC(org.apache.spark.internal.LogKeys.DATABASE_NAME..MODULE$, db), new MDC(org.apache.spark.internal.LogKeys.PARTITION_SPECS..MODULE$, specs.mkString("\n")), new MDC(org.apache.spark.internal.LogKeys.DROPPED_PARTITIONS..MODULE$, droppedParts.mkString("\n")), new MDC(org.apache.spark.internal.LogKeys.REMAINING_PARTITIONS..MODULE$, remainingParts.mkString("\n"))}))).stripMargin()));
               throw var11;
            }

            return (ArrayBuffer)droppedParts.$plus$eq(partition);
         });
      });
   }

   public void renamePartitions(final String db, final String table, final Seq specs, final Seq newSpecs) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         scala.Predef..MODULE$.require(specs.size() == newSpecs.size(), () -> "number of old and new partition specs differ");
         RawHiveTable rawHiveTable = this.getRawHiveTable(db, table);
         org.apache.hadoop.hive.ql.metadata.Table hiveTable = (org.apache.hadoop.hive.ql.metadata.Table)rawHiveTable.rawTable();
         hiveTable.setOwner(this.userName());
         ((IterableOnceOps)specs.zip(newSpecs)).foreach((x0$1) -> {
            $anonfun$renamePartitions$3(this, hiveTable, db, table, rawHiveTable, x0$1);
            return BoxedUnit.UNIT;
         });
      });
   }

   public void alterPartitions(final String db, final String table, final Seq newParts) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         String original = this.state().getCurrentDatabase();

         try {
            this.setCurrentDatabaseRaw(db);
            org.apache.hadoop.hive.ql.metadata.Table hiveTable = (org.apache.hadoop.hive.ql.metadata.Table)this.withHiveState(() -> (org.apache.hadoop.hive.ql.metadata.Table)this.getRawTableOption(db, table).getOrElse(() -> {
                  throw new NoSuchTableException(db, table);
               }));
            hiveTable.setOwner(this.userName());
            this.shim().alterPartitions(this.client(), table, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((scala.collection.Seq)newParts.map((x$17) -> HiveClientImpl$.MODULE$.toHivePartition(x$17, hiveTable))).asJava());
         } finally {
            this.state().setCurrentDatabase(original);
         }

      });
   }

   public Seq getPartitionNames(final CatalogTable table, final Option partialSpec) {
      return (Seq)this.withHiveState(() -> {
         Seq var10000;
         if (scala.None..MODULE$.equals(partialSpec)) {
            var10000 = this.shim().getPartitionNames(this.client(), table.database(), table.identifier().table(), (short)-1);
         } else {
            if (!(partialSpec instanceof Some)) {
               throw new MatchError(partialSpec);
            }

            Some var6 = (Some)partialSpec;
            Map s = (Map)var6.value();
            scala.Predef..MODULE$.assert(s.values().forall((x$18) -> BoxesRunTime.boxToBoolean($anonfun$getPartitionNames$2(x$18))), () -> "partition spec '" + s + "' is invalid");
            var10000 = this.shim().getPartitionNames(this.client(), table.database(), table.identifier().table(), scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(s).asJava(), (short)-1);
         }

         Seq hivePartitionNames = var10000;
         return (Seq)hivePartitionNames.sorted(scala.math.Ordering.String..MODULE$);
      });
   }

   public Option getPartitionNames$default$2() {
      return scala.None..MODULE$;
   }

   public Option getPartitionOption(final RawHiveTable rawHiveTable, final Map spec) {
      return (Option)this.withHiveState(() -> {
         org.apache.hadoop.hive.ql.metadata.Table hiveTable = (org.apache.hadoop.hive.ql.metadata.Table)rawHiveTable.rawTable();
         Partition hivePartition = this.shim().getPartition(this.client(), hiveTable, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(spec).asJava(), false);
         return scala.Option..MODULE$.apply(hivePartition).map((hp) -> HiveClientImpl$.MODULE$.fromHivePartition(hp));
      });
   }

   public Seq getPartitions(final String db, final String table, final Option spec) {
      org.apache.hadoop.hive.ql.metadata.Table hiveTable = (org.apache.hadoop.hive.ql.metadata.Table)this.withHiveState(() -> (org.apache.hadoop.hive.ql.metadata.Table)this.getRawTableOption(db, table).getOrElse(() -> {
            throw new NoSuchTableException(db, table);
         }));
      return this.getPartitions(hiveTable, spec);
   }

   private Seq getPartitions(final org.apache.hadoop.hive.ql.metadata.Table hiveTable, final Option spec) {
      return (Seq)this.withHiveState(() -> {
         Map var10000;
         if (scala.None..MODULE$.equals(spec)) {
            var10000 = org.apache.spark.sql.catalyst.catalog.CatalogTypes..MODULE$.emptyTablePartitionSpec();
         } else {
            if (!(spec instanceof Some)) {
               throw new MatchError(spec);
            }

            Some var6 = (Some)spec;
            Map s = (Map)var6.value();
            scala.Predef..MODULE$.assert(s.values().forall((x$19) -> BoxesRunTime.boxToBoolean($anonfun$getPartitions$4(x$19))), () -> "partition spec '" + s + "' is invalid");
            var10000 = s;
         }

         Map partSpec = var10000;
         Seq parts = (Seq)this.shim().getPartitions(this.client(), hiveTable, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(partSpec).asJava()).map((hp) -> HiveClientImpl$.MODULE$.fromHivePartition(hp));
         org.apache.spark.metrics.source.HiveCatalogMetrics..MODULE$.incrementFetchedPartitions(parts.length());
         return parts;
      });
   }

   public Seq getPartitionsByFilter(final RawHiveTable rawHiveTable, final Seq predicates) {
      return (Seq)this.withHiveState(() -> {
         org.apache.hadoop.hive.ql.metadata.Table hiveTable = (org.apache.hadoop.hive.ql.metadata.Table)rawHiveTable.rawTable();
         hiveTable.setOwner(this.userName());
         Seq parts = (Seq)this.shim().getPartitionsByFilter(this.client(), hiveTable, predicates, rawHiveTable.toCatalogTable()).map((hp) -> HiveClientImpl$.MODULE$.fromHivePartition(hp));
         org.apache.spark.metrics.source.HiveCatalogMetrics..MODULE$.incrementFetchedPartitions(parts.length());
         return parts;
      });
   }

   public Seq listTables(final String dbName) {
      return (Seq)this.withHiveState(() -> this.shim().getAllTables(this.client(), dbName));
   }

   public Seq listTables(final String dbName, final String pattern) {
      return (Seq)this.withHiveState(() -> this.shim().getTablesByPattern(this.client(), dbName, pattern));
   }

   public Seq listTablesByType(final String dbName, final String pattern, final CatalogTableType tableType) {
      return (Seq)this.withHiveState(() -> {
         TableType hiveTableType = HiveClientImpl$.MODULE$.toHiveTableType(tableType);

         Seq var10000;
         try {
            var10000 = this.shim().getTablesByType(this.client(), dbName, pattern, hiveTableType);
         } catch (UnsupportedOperationException var6) {
            Seq tableNames = this.shim().getTablesByPattern(this.client(), dbName, pattern);
            var10000 = (Seq)((IterableOps)this.getRawTablesByName(dbName, tableNames).filter((x$20) -> BoxesRunTime.boxToBoolean($anonfun$listTablesByType$2(hiveTableType, x$20)))).map((x$21) -> x$21.getTableName());
         }

         return var10000;
      });
   }

   public Seq runSqlHive(final String sql) {
      scala.Predef..MODULE$.assert(org.apache.spark.util.Utils..MODULE$.isTesting(), () -> org.apache.spark.internal.config.Tests..MODULE$.IS_TESTING().key() + " is not set to true");
      int maxResults = 100000;
      Seq results = this.runHive(sql, maxResults);
      if (results.size() == maxResults) {
         throw org.apache.spark.SparkException..MODULE$.internalError("RESULTS POSSIBLY TRUNCATED");
      } else {
         return results;
      }
   }

   public Seq runHive(final String cmd, final int maxRows) {
      return (Seq)this.withHiveState(() -> {
         SessionState.start(this.state());
         this.logDebug((Function0)(() -> "Running hiveql '" + cmd + "'"));
         if (cmd.toLowerCase(Locale.ROOT).startsWith("set")) {
            this.logDebug((Function0)(() -> "Changing config: " + cmd));
         }

         SessionState var10000;
         try {
            String cmd_trimmed = cmd.trim();
            String[] tokens = cmd_trimmed.split("\\s+");
            String cmd_1 = cmd_trimmed.substring(tokens[0].length()).trim();
            CommandProcessor proc = this.shim().getCommandProcessor(tokens[0], this.conf());
            if (proc instanceof Driver var11) {
               try {
                  CommandProcessorResponse response = var11.run(cmd);
                  if (this.getResponseCode$1(response) != 0) {
                     throw new QueryExecutionException(response.getErrorMessage(), org.apache.spark.sql.execution.QueryExecutionException..MODULE$.$lessinit$greater$default$2());
                  }

                  var11.setMaxRows(maxRows);
                  Seq results = this.shim().getDriverResults(var11);
               } catch (Throwable var31) {
                  if (var31 instanceof QueryExecutionException ? true : var31 instanceof SparkThrowable) {
                     throw var31;
                  }

                  if (var31 instanceof Exception var16) {
                     throw new QueryExecutionException(ExceptionUtils.getStackTrace(var16), org.apache.spark.sql.execution.QueryExecutionException..MODULE$.$lessinit$greater$default$2());
                  }

                  throw var31;
               } finally {
                  this.closeDriver$1(var11);
               }
            } else {
               Object out = this.state().getClass().getField("out").get(this.state());
               if (out != null) {
                  ((PrintStream)out).println(tokens[0] + " " + cmd_1);
               }

               CommandProcessorResponse response = proc.run(cmd_1);
               int responseCode = this.getResponseCode$1(response);
               if (responseCode != 0) {
                  throw new QueryExecutionException(response.getErrorMessage(), org.apache.spark.sql.execution.QueryExecutionException..MODULE$.$lessinit$greater$default$2());
               }

               new scala.collection.immutable..colon.colon(Integer.toString(responseCode), scala.collection.immutable.Nil..MODULE$);
            }
         } catch (Exception var33) {
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n            |======================\n            |HIVE FAILURE OUTPUT\n            |======================\n            |", "\n            |======================\n            |END HIVE FAILURE OUTPUT\n            |======================\n          "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.OUTPUT_BUFFER..MODULE$, this.outputBuffer().toString())}))).stripMargin()), var33);
            throw var33;
         } finally {
            var10000 = this.state();
            if (var10000 != null) {
               var10000 = this.state();
               var10000.close();
            }

         }

         return var10000;
      });
   }

   public int runHive$default$2() {
      return 1000;
   }

   public void loadPartition(final String loadPath, final String dbName, final String tableName, final LinkedHashMap partSpec, final boolean replace, final boolean inheritTableSpecs, final boolean isSrcLocal) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         org.apache.hadoop.hive.ql.metadata.Table hiveTable = this.shim().getTable(this.client(), dbName, tableName, true);
         this.shim().loadPartition(this.client(), new Path(loadPath), dbName + "." + tableName, partSpec, replace, inheritTableSpecs, hiveTable.isStoredAsSubDirectories(), isSrcLocal);
      });
   }

   public void loadTable(final String loadPath, final String tableName, final boolean replace, final boolean isSrcLocal) {
      this.withHiveState((JFunction0.mcV.sp)() -> this.shim().loadTable(this.client(), new Path(loadPath), tableName, replace, isSrcLocal));
   }

   public void loadDynamicPartitions(final String loadPath, final String dbName, final String tableName, final LinkedHashMap partSpec, final boolean replace, final int numDP) {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         org.apache.hadoop.hive.ql.metadata.Table hiveTable = this.shim().getTable(this.client(), dbName, tableName, true);
         this.shim().loadDynamicPartitions(this.client(), new Path(loadPath), dbName + "." + tableName, partSpec, replace, numDP, hiveTable);
      });
   }

   public void createFunction(final String db, final CatalogFunction func) {
      this.withHiveState((JFunction0.mcV.sp)() -> this.shim().createFunction(this.client(), db, func));
   }

   public void dropFunction(final String db, final String name) {
      this.withHiveState((JFunction0.mcV.sp)() -> this.shim().dropFunction(this.client(), db, name));
   }

   public void renameFunction(final String db, final String oldName, final String newName) {
      this.withHiveState((JFunction0.mcV.sp)() -> this.shim().renameFunction(this.client(), db, oldName, newName));
   }

   public void alterFunction(final String db, final CatalogFunction func) {
      this.withHiveState((JFunction0.mcV.sp)() -> this.shim().alterFunction(this.client(), db, func));
   }

   public Option getFunctionOption(final String db, final String name) {
      return (Option)this.withHiveState(() -> this.shim().getFunctionOption(this.client(), db, name));
   }

   public Seq listFunctions(final String db, final String pattern) {
      return (Seq)this.withHiveState(() -> this.shim().listFunctions(this.client(), db, pattern));
   }

   public void addJar(final String path) {
      URI jarURI = org.apache.spark.util.Utils..MODULE$.resolveURI(path);
      this.clientLoader().addJar(jarURI.toURL());
   }

   public HiveClientImpl newSession() {
      return (HiveClientImpl)this.clientLoader().createClient();
   }

   public void reset() {
      this.withHiveState((JFunction0.mcV.sp)() -> {
         Seq allTables = this.shim().getAllTables(this.client(), "default");
         Tuple2 var4 = ((IterableOps)allTables.map((t) -> this.shim().getTable(this.client(), "default", t, this.shim().getTable$default$4()))).partition((x$22) -> BoxesRunTime.boxToBoolean($anonfun$reset$3(x$22)));
         if (var4 != null) {
            Seq mvs = (Seq)var4._1();
            Seq others = (Seq)var4._2();
            Tuple2 var3 = new Tuple2(mvs, others);
            Seq mvs = (Seq)var3._1();
            Seq othersx = (Seq)var3._2();
            mvs.foreach((table) -> {
               $anonfun$reset$4(this, table);
               return BoxedUnit.UNIT;
            });
            othersx.foreach((table) -> {
               $anonfun$reset$6(this, table);
               return BoxedUnit.UNIT;
            });
            ((IterableOnceOps)this.shim().getAllDatabases(this.client()).filterNot((x$24) -> BoxesRunTime.boxToBoolean($anonfun$reset$9(x$24)))).foreach((db) -> {
               $anonfun$reset$10(this, db);
               return BoxedUnit.UNIT;
            });
         } else {
            throw new MatchError(var4);
         }
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$state$1(final SessionState ret$1, final String dir) {
      ret$1.getConf().setVar(HiveConf.getConfVars("hive.metastore.warehouse.dir"), dir);
   }

   // $FF: synthetic method
   private final void liftedTree1$1(final Object nonLocalReturnKey1$1, final Function0 f$1, final ObjectRef caughtException$1, final IntRef numTries$1) {
      try {
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, f$1.apply());
      } catch (Throwable var9) {
         if (var9 instanceof Exception var8) {
            if (this.causedByThrift(var8)) {
               caughtException$1.elem = var8;
               this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"HiveClient got thrift exception, destroying client and retrying "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " times"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RETRY..MODULE$, BoxesRunTime.boxToInteger(numTries$1.elem))}))))), var8);
               this.clientLoader().cachedHive_$eq((Object)null);
               Thread.sleep(this.retryDelayMillis());
               BoxedUnit var10000 = BoxedUnit.UNIT;
               return;
            }
         }

         throw var9;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$toHiveDatabase$2(final HiveClientImpl $this, final Database hiveDb$1, final String ownerName) {
      $this.shim().setDatabaseOwnerName(hiveDb$1, ownerName);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertHiveTableToCatalogTable$2(final FieldSchema x$4) {
      boolean var2;
      label23: {
         String var10000 = x$4.getType();
         String var1 = HiveClientImpl$.MODULE$.INCOMPATIBLE_PARTITION_TYPE_PLACEHOLDER();
         if (var10000 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   private static final Tuple2 liftedTree2$1(final org.apache.hadoop.hive.ql.metadata.Table h$1) {
      try {
         return new Tuple2(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(h$1.getCols()).asScala().map((hc) -> HiveClientImpl$.MODULE$.fromHiveColumn(hc)), ((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(h$1.getPartCols()).asScala().filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$convertHiveTableToCatalogTable$2(x$4)))).map((hc) -> HiveClientImpl$.MODULE$.fromHiveColumn(hc)));
      } catch (SparkException var2) {
         throw .MODULE$.convertHiveTableToCatalogTableError(var2, h$1.getDbName(), h$1.getTableName());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertHiveTableToCatalogTable$4(final Order x$6) {
      return x$6.getOrder() == 1;
   }

   // $FF: synthetic method
   public static final void $anonfun$convertHiveTableToCatalogTable$7(final Map properties$1, final scala.collection.mutable.Map ignoredProperties$1, final String key) {
      properties$1.get(key).foreach((value) -> (scala.collection.mutable.Map)ignoredProperties$1.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), value)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertHiveTableToCatalogTable$9(final Set excludedTableProperties$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String key = (String)x0$1._1();
         return excludedTableProperties$1.contains(key);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$alterTableDataSchema$3(final org.apache.hadoop.hive.ql.metadata.Table oldTable$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         oldTable$1.setProperty(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createPartitions$3(final HiveClientImpl $this, final org.apache.hadoop.hive.ql.metadata.Table hiveTable$1, final CatalogTablePartition p) {
      return $this.shim().getPartitions($this.client(), hiveTable$1, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(p.spec()).asJava()).nonEmpty();
   }

   private final void replaceExistException$1(final Throwable e, final CatalogTable table$3, final Seq parts$1) {
      if (e instanceof HiveException && e.getCause() instanceof AlreadyExistsException) {
         String db = (String)table$3.identifier().database().getOrElse(() -> this.state().getCurrentDatabase());
         String tableName = table$3.identifier().table();
         org.apache.hadoop.hive.ql.metadata.Table hiveTable = this.client().getTable(db, tableName);
         Seq existingParts = (Seq)parts$1.filter((p) -> BoxesRunTime.boxToBoolean($anonfun$createPartitions$3(this, hiveTable, p)));
         throw new PartitionsAlreadyExistException(db, tableName, (Seq)existingParts.map((x$13) -> x$13.spec()));
      } else {
         throw e;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$dropPartitions$3(final String x$14) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$14));
   }

   // $FF: synthetic method
   public static final void $anonfun$renamePartitions$3(final HiveClientImpl $this, final org.apache.hadoop.hive.ql.metadata.Table hiveTable$2, final String db$2, final String table$5, final RawHiveTable rawHiveTable$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Map oldSpec = (Map)x0$1._1();
         Map newSpec = (Map)x0$1._2();
         if ($this.shim().getPartition($this.client(), hiveTable$2, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(newSpec).asJava(), false) != null) {
            throw new PartitionsAlreadyExistException(db$2, table$5, newSpec);
         } else {
            Partition hivePart = (Partition)$this.getPartitionOption(rawHiveTable$2, oldSpec).map((p) -> HiveClientImpl$.MODULE$.toHivePartition(p.copy(newSpec, p.copy$default$2(), p.copy$default$3(), p.copy$default$4(), p.copy$default$5(), p.copy$default$6()), hiveTable$2)).getOrElse(() -> {
               throw new NoSuchPartitionException(db$2, table$5, oldSpec);
            });
            $this.shim().renamePartition($this.client(), hiveTable$2, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(oldSpec).asJava(), hivePart);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitionNames$2(final String x$18) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$18));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitions$4(final String x$19) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$19));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listTablesByType$2(final TableType hiveTableType$1, final org.apache.hadoop.hive.ql.metadata.Table x$20) {
      boolean var3;
      label23: {
         TableType var10000 = x$20.getTableType();
         if (var10000 == null) {
            if (hiveTableType$1 == null) {
               break label23;
            }
         } else if (var10000.equals(hiveTableType$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private final void closeDriver$1(final Driver driver) {
      label42: {
         driver.getClass().getMethod("close").invoke(driver);
         package.HiveVersion var10000 = this.version();
         package$hive$v3_0$ var2 = package$hive$v3_0$.MODULE$;
         if (var10000 == null) {
            if (var2 == null) {
               return;
            }
         } else if (var10000.equals(var2)) {
            return;
         }

         var10000 = this.version();
         package$hive$v3_1$ var3 = package$hive$v3_1$.MODULE$;
         if (var10000 == null) {
            if (var3 == null) {
               return;
            }
         } else if (var10000.equals(var3)) {
            return;
         }

         var10000 = this.version();
         package$hive$v4_0$ var4 = package$hive$v4_0$.MODULE$;
         if (var10000 == null) {
            if (var4 != null) {
               break label42;
            }
         } else if (!var10000.equals(var4)) {
            break label42;
         }

         return;
      }

      CommandProcessorFactory.clean(this.conf());
   }

   private final int getResponseCode$1(final CommandProcessorResponse response) {
      return this.version().$less(package$hive$v4_0$.MODULE$) ? response.getResponseCode() : 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reset$3(final org.apache.hadoop.hive.ql.metadata.Table x$22) {
      return x$22.getTableType().toString().equals("MATERIALIZED_VIEW");
   }

   // $FF: synthetic method
   public static final void $anonfun$reset$4(final HiveClientImpl $this, final org.apache.hadoop.hive.ql.metadata.Table table) {
      String t = table.getTableName();
      $this.logDebug((Function0)(() -> "Deleting materialized view " + t));
      $this.shim().dropTable($this.client(), "default", t);
   }

   // $FF: synthetic method
   public static final void $anonfun$reset$8(final HiveClientImpl $this, final String t$2, final Index index) {
      $this.shim().dropIndex($this.client(), "default", t$2, index.getIndexName());
   }

   // $FF: synthetic method
   public static final void $anonfun$reset$6(final HiveClientImpl $this, final org.apache.hadoop.hive.ql.metadata.Table table) {
      String t = table.getTableName();
      $this.logDebug((Function0)(() -> "Deleting table " + t));

      try {
         $this.shim().getIndexes($this.client(), "default", t, (short)255).foreach((index) -> {
            $anonfun$reset$8($this, t, index);
            return BoxedUnit.UNIT;
         });
         if (!table.isIndexTable()) {
            $this.shim().dropTable($this.client(), "default", t);
         }
      } catch (NoSuchMethodError var3) {
         $this.shim().dropTable($this.client(), "default", t);
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$reset$9(final String x$24) {
      boolean var10000;
      label23: {
         String var1 = "default";
         if (x$24 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$24.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$reset$10(final HiveClientImpl $this, final String db) {
      $this.logDebug((Function0)(() -> "Dropping Database: " + db));
      $this.shim().dropDatabase($this.client(), db, true, false, true);
   }

   public HiveClientImpl(final package.HiveVersion version, final Option warehouseDir, final SparkConf sparkConf, final Iterable hadoopConf, final Map extraConfig, final ClassLoader initClassLoader, final IsolatedClientLoader clientLoader) {
      this.version = version;
      this.sparkConf = sparkConf;
      this.hadoopConf = hadoopConf;
      this.extraConfig = extraConfig;
      this.initClassLoader = initClassLoader;
      this.clientLoader = clientLoader;
      HiveClient.$init$(this);
      Logging.$init$(this);
      this.outputBuffer = new CircularBuffer(org.apache.spark.util.CircularBuffer..MODULE$.$lessinit$greater$default$1());
      Object var10001;
      if (package$hive$v2_0$.MODULE$.equals(version)) {
         var10001 = new Shim_v2_0();
      } else if (package$hive$v2_1$.MODULE$.equals(version)) {
         var10001 = new Shim_v2_1();
      } else if (package$hive$v2_2$.MODULE$.equals(version)) {
         var10001 = new Shim_v2_2();
      } else if (package$hive$v2_3$.MODULE$.equals(version)) {
         var10001 = new Shim_v2_3();
      } else if (package$hive$v3_0$.MODULE$.equals(version)) {
         var10001 = new Shim_v3_0();
      } else if (package$hive$v3_1$.MODULE$.equals(version)) {
         var10001 = new Shim_v3_1();
      } else {
         if (!package$hive$v4_0$.MODULE$.equals(version)) {
            throw new MatchError(version);
         }

         var10001 = new Shim_v4_0();
      }

      this.shim = (Shim_v2_0)var10001;
      ClassLoader original = Thread.currentThread().getContextClassLoader();
      SessionState var15;
      if (clientLoader.sessionStateIsolationOn()) {
         Thread.currentThread().setContextClassLoader(initClassLoader);

         try {
            var15 = this.newState();
         } finally {
            Thread.currentThread().setContextClassLoader(original);
         }
      } else {
         SessionState ret = SessionState.get();
         if (ret != null) {
            warehouseDir.foreach((dir) -> {
               $anonfun$state$1(ret, dir);
               return BoxedUnit.UNIT;
            });
            var15 = ret;
         } else {
            var15 = this.newState();
         }
      }

      this.state = var15;
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Warehouse location for Hive client (version "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") is "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HIVE_CLIENT_VERSION..MODULE$, this.version().fullVersion())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.conf().getVar(HiveConf.getConfVars("hive.metastore.warehouse.dir")))}))))));
      this.userName = UserGroupInformation.getCurrentUser().getShortUserName();
      this.retryLimit = this.conf().getIntVar(HiveConf.getConfVars("hive.metastore.failure.retries"));
      this.retryDelayMillis = this.shim().getMetastoreClientConnectRetryDelayMillis(this.conf());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class RawHiveTableImpl implements RawHiveTable {
      private CatalogTable toCatalogTable;
      private final org.apache.hadoop.hive.ql.metadata.Table rawTable;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final HiveClientImpl $outer;

      public org.apache.hadoop.hive.ql.metadata.Table rawTable() {
         return this.rawTable;
      }

      private CatalogTable toCatalogTable$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.toCatalogTable = this.org$apache$spark$sql$hive$client$HiveClientImpl$RawHiveTableImpl$$$outer().org$apache$spark$sql$hive$client$HiveClientImpl$$convertHiveTableToCatalogTable(this.rawTable());
               this.bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.toCatalogTable;
      }

      public CatalogTable toCatalogTable() {
         return !this.bitmap$0 ? this.toCatalogTable$lzycompute() : this.toCatalogTable;
      }

      public Map hiveTableProps() {
         return scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this.rawTable().getParameters()).asScala().toMap(scala..less.colon.less..MODULE$.refl());
      }

      // $FF: synthetic method
      public HiveClientImpl org$apache$spark$sql$hive$client$HiveClientImpl$RawHiveTableImpl$$$outer() {
         return this.$outer;
      }

      public RawHiveTableImpl(final org.apache.hadoop.hive.ql.metadata.Table rawTable) {
         this.rawTable = rawTable;
         if (HiveClientImpl.this == null) {
            throw null;
         } else {
            this.$outer = HiveClientImpl.this;
            super();
         }
      }
   }
}
