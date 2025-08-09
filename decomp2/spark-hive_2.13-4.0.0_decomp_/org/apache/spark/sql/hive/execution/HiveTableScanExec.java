package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.io.DelegateSymlinkTextInputFormat;
import org.apache.hadoop.hive.ql.io.SymlinkTextInputFormat;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.serde2.Deserializer;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils.ObjectInspectorCopyOption;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.analysis.CastSupport;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import org.apache.spark.sql.catalyst.catalog.SessionCatalog;
import org.apache.spark.sql.catalyst.expressions.Attribute;
import org.apache.spark.sql.catalyst.expressions.AttributeMap;
import org.apache.spark.sql.catalyst.expressions.AttributeReference;
import org.apache.spark.sql.catalyst.expressions.AttributeSet;
import org.apache.spark.sql.catalyst.expressions.Cast;
import org.apache.spark.sql.catalyst.expressions.DynamicPruningExpression;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.GenericInternalRow;
import org.apache.spark.sql.catalyst.expressions.UnsafeProjection;
import org.apache.spark.sql.catalyst.expressions.package;
import org.apache.spark.sql.catalyst.trees.LeafLike;
import org.apache.spark.sql.catalyst.trees.TreeNode;
import org.apache.spark.sql.execution.LeafExecNode;
import org.apache.spark.sql.execution.SparkPlan;
import org.apache.spark.sql.execution.metric.SQLMetric;
import org.apache.spark.sql.hive.HadoopTableReader;
import org.apache.spark.sql.hive.HiveShim$;
import org.apache.spark.sql.hive.HiveUtils$;
import org.apache.spark.sql.hive.client.HiveClientImpl$;
import org.apache.spark.sql.internal.SQLConf;
import org.apache.spark.sql.types.BooleanType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r=d!B\u001a5\u0001Z\u0002\u0005\u0002\u00033\u0001\u0005+\u0007I\u0011A3\t\u0011=\u0004!\u0011#Q\u0001\n\u0019D\u0001\u0002\u001d\u0001\u0003\u0016\u0004%\t!\u001d\u0005\tq\u0002\u0011\t\u0012)A\u0005e\"A\u0011\u0010\u0001BK\u0002\u0013\u0005!\u0010\u0003\u0005\u0000\u0001\tE\t\u0015!\u0003|\u0011)\t\t\u0001\u0001BC\u0002\u0013%\u00111\u0001\u0005\u000b\u0003\u001b\u0001!\u0011!Q\u0001\n\u0005\u0015\u0001bBA\f\u0001\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003S\u0001A\u0011IA\u0016\u0011\u001d\tI\u0004\u0001C!\u0003wA!\"!\u0014\u0001\u0011\u000b\u0007I\u0011IA(\u0011\u001d\tY\b\u0001C!\u0003{B\u0011\"!\"\u0001\u0005\u0004%I!a\"\t\u0011\u0005U\u0005\u0001)A\u0005\u0003\u0013C\u0001\"a&\u0001\u0005\u0004%\t%\u001a\u0005\b\u00033\u0003\u0001\u0015!\u0003g\u0011)\tY\n\u0001EC\u0002\u0013%\u0011Q\u0014\u0005\u000b\u0003K\u0003\u0001R1A\u0005\n\u0005\u001d\u0006BCAa\u0001!\u0015\r\u0011\"\u0003\u0002D\"Q\u00111\u001b\u0001\t\u0006\u0004%I!!6\t\u0015\u0005\r\b\u0001#b\u0001\n\u0013\t)\u000fC\u0004\u0002r\u0002!I!a=\t\u000f\t=\u0001\u0001\"\u0003\u0003\u0012!A!Q\u0004\u0001\u0005\u0002Y\u0012y\u0002\u0003\u0006\u0003.\u0001A)\u0019!C\u0001\u0005_A!Ba\r\u0001\u0011\u000b\u0007I\u0011\u0001B\u0018\u0011\u001d\u00119\u0004\u0001C)\u0005sAqAa\u0014\u0001\t\u0013\u0011\t\u0006C\u0004\u0003X\u0001!IA!\u0017\t\u000f\tU\u0006\u0001\"\u0011\u00038\"9!\u0011\u0018\u0001\u0005B\tm\u0006\"\u0003Bc\u0001\u0005\u0005I\u0011\u0001Bd\u0011%\u0011\u0019\u000eAI\u0001\n\u0003\u0011)\u000eC\u0005\u0003l\u0002\t\n\u0011\"\u0001\u0003n\"I!\u0011\u001f\u0001\u0012\u0002\u0013\u0005!1\u001f\u0005\n\u0005o\u0004\u0011\u0011!C!\u0005sD\u0011Ba?\u0001\u0003\u0003%\tA!@\t\u0013\r\u0015\u0001!!A\u0005\u0002\r\u001d\u0001\"CB\u0007\u0001\u0005\u0005I\u0011IB\b\u0011%\u0019I\u0002AA\u0001\n\u0003\u0019Y\u0002C\u0005\u0004&\u0001\t\t\u0011\"\u0011\u0004(!I11\u0006\u0001\u0002\u0002\u0013\u00053QF\u0004\u000b\u0007c!\u0014\u0011!E\u0001m\rMb!C\u001a5\u0003\u0003E\tANB\u001b\u0011\u001d\t9\"\fC\u0001\u0007\u0003B\u0011ba\u0011.\u0003\u0003%)e!\u0012\t\u0013\r\u001dS&!A\u0005\u0002\u000e%\u0003\"CB+[\u0005\u0005I\u0011QB,\u0011%\u0019)'LA\u0001\n\u0013\u00199GA\tISZ,G+\u00192mKN\u001b\u0017M\\#yK\u000eT!!\u000e\u001c\u0002\u0013\u0015DXmY;uS>t'BA\u001c9\u0003\u0011A\u0017N^3\u000b\u0005eR\u0014aA:rY*\u00111\bP\u0001\u0006gB\f'o\u001b\u0006\u0003{y\na!\u00199bG\",'\"A \u0002\u0007=\u0014xm\u0005\u0004\u0001\u0003\u001aK\u0015k\u0016\t\u0003\u0005\u0012k\u0011a\u0011\u0006\u0003kaJ!!R\"\u0003\u0013M\u0003\u0018M]6QY\u0006t\u0007C\u0001\"H\u0013\tA5I\u0001\u0007MK\u00064W\t_3d\u001d>$W\r\u0005\u0002K\u001f6\t1J\u0003\u0002M\u001b\u0006A\u0011M\\1msNL7O\u0003\u0002Oq\u0005A1-\u0019;bYf\u001cH/\u0003\u0002Q\u0017\nY1)Y:u'V\u0004\bo\u001c:u!\t\u0011V+D\u0001T\u0015\u0005!\u0016!B:dC2\f\u0017B\u0001,T\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001W1\u000f\u0005e{fB\u0001._\u001b\u0005Y&B\u0001/^\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001+\n\u0005\u0001\u001c\u0016a\u00029bG.\fw-Z\u0005\u0003E\u000e\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001Y*\u0002'I,\u0017/^3ti\u0016$\u0017\t\u001e;sS\n,H/Z:\u0016\u0003\u0019\u00042\u0001W4j\u0013\tA7MA\u0002TKF\u0004\"A[7\u000e\u0003-T!\u0001\\'\u0002\u0017\u0015D\bO]3tg&|gn]\u0005\u0003].\u0014\u0011\"\u0011;ue&\u0014W\u000f^3\u0002)I,\u0017/^3ti\u0016$\u0017\t\u001e;sS\n,H/Z:!\u0003!\u0011X\r\\1uS>tW#\u0001:\u0011\u0005M4X\"\u0001;\u000b\u0005Ul\u0015aB2bi\u0006dwnZ\u0005\u0003oR\u0014\u0011\u0003S5wKR\u000b'\r\\3SK2\fG/[8o\u0003%\u0011X\r\\1uS>t\u0007%\u0001\u000bqCJ$\u0018\u000e^5p]B\u0013XO\\5oOB\u0013X\rZ\u000b\u0002wB\u0019\u0001l\u001a?\u0011\u0005)l\u0018B\u0001@l\u0005))\u0005\u0010\u001d:fgNLwN\\\u0001\u0016a\u0006\u0014H/\u001b;j_:\u0004&/\u001e8j]\u001e\u0004&/\u001a3!\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o+\t\t)\u0001\u0005\u0003\u0002\b\u0005%Q\"\u0001\u001d\n\u0007\u0005-\u0001H\u0001\u0007Ta\u0006\u00148nU3tg&|g.A\u0007ta\u0006\u00148nU3tg&|g\u000e\t\u0015\u0004\u0011\u0005E\u0001c\u0001*\u0002\u0014%\u0019\u0011QC*\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018A\u0002\u001fj]&$h\b\u0006\u0005\u0002\u001c\u0005\r\u0012QEA\u0014)\u0011\ti\"!\t\u0011\u0007\u0005}\u0001!D\u00015\u0011\u001d\t\t!\u0003a\u0001\u0003\u000bAQ\u0001Z\u0005A\u0002\u0019DQ\u0001]\u0005A\u0002IDQ!_\u0005A\u0002m\fAaY8oMV\u0011\u0011Q\u0006\t\u0005\u0003_\t)$\u0004\u0002\u00022)\u0019\u00111\u0007\u001d\u0002\u0011%tG/\u001a:oC2LA!a\u000e\u00022\t91+\u0015'D_:4\u0017\u0001\u00038pI\u0016t\u0015-\\3\u0016\u0005\u0005u\u0002\u0003BA \u0003\u000frA!!\u0011\u0002DA\u0011!lU\u0005\u0004\u0003\u000b\u001a\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002J\u0005-#AB*ue&twMC\u0002\u0002FM\u000bq!\\3ue&\u001c7/\u0006\u0002\u0002RAA\u00111KA/\u0003C\ny'\u0004\u0002\u0002V)!\u0011qKA-\u0003%IW.\\;uC\ndWMC\u0002\u0002\\M\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty&!\u0016\u0003\u00075\u000b\u0007\u000f\u0005\u0003\u0002d\u00055TBAA3\u0015\u0011\t9'!\u001b\u0002\t1\fgn\u001a\u0006\u0003\u0003W\nAA[1wC&!\u0011\u0011JA3!\u0011\t\t(a\u001e\u000e\u0005\u0005M$bAA;\u0007\u00061Q.\u001a;sS\u000eLA!!\u001f\u0002t\tI1+\u0015'NKR\u0014\u0018nY\u0001\u0013aJ|G-^2fI\u0006#HO]5ckR,7/\u0006\u0002\u0002\u0000A\u0019!.!!\n\u0007\u0005\r5N\u0001\u0007BiR\u0014\u0018NY;uKN+G/\u0001\npe&<\u0017N\\1m\u0003R$(/\u001b2vi\u0016\u001cXCAAE!\u0015Q\u00171RAH\u0013\r\tii\u001b\u0002\r\u0003R$(/\u001b2vi\u0016l\u0015\r\u001d\t\u0004U\u0006E\u0015bAAJW\n\u0011\u0012\t\u001e;sS\n,H/\u001a*fM\u0016\u0014XM\\2f\u0003My'/[4j]\u0006d\u0017\t\u001e;sS\n,H/Z:!\u0003\u0019yW\u000f\u001e9vi\u00069q.\u001e;qkR\u0004\u0013\u0001\u00052pk:$\u0007K];oS:<\u0007K]3e+\t\ty\n\u0005\u0003S\u0003Cc\u0018bAAR'\n1q\n\u001d;j_:\f1\u0002[5wKFcG+\u00192mKV\u0011\u0011\u0011\u0016\t\u0005\u0003W\u000bY,\u0004\u0002\u0002.*!\u0011qVAY\u0003!iW\r^1eCR\f'\u0002BAZ\u0003k\u000b!!\u001d7\u000b\u0007]\n9LC\u0002\u0002:r\na\u0001[1e_>\u0004\u0018\u0002BA_\u0003[\u0013Q\u0001V1cY\u0016D3aEA\t\u0003%!\u0018M\u00197f\t\u0016\u001c8-\u0006\u0002\u0002FB!\u0011qYAg\u001b\t\tIM\u0003\u0003\u0002L\u0006E\u0016\u0001\u00029mC:LA!a4\u0002J\nIA+\u00192mK\u0012+7o\u0019\u0015\u0004)\u0005E\u0011A\u00035bI>|\u0007oQ8oMV\u0011\u0011q\u001b\t\u0005\u00033\fi.\u0004\u0002\u0002\\*!\u0011\u0011FA\\\u0013\u0011\ty.a7\u0003\u001b\r{gNZ5hkJ\fG/[8oQ\r)\u0012\u0011C\u0001\rQ\u0006$wn\u001c9SK\u0006$WM]\u000b\u0003\u0003O\u0004B!!;\u0002l6\ta'C\u0002\u0002nZ\u0012\u0011\u0003S1e_>\u0004H+\u00192mKJ+\u0017\rZ3sQ\r1\u0012\u0011C\u0001\u000fG\u0006\u001cHO\u0012:p[N#(/\u001b8h)\u0019\t)0a?\u0002\u0000B\u0019!+a>\n\u0007\u0005e8KA\u0002B]fDq!!@\u0018\u0001\u0004\ti$A\u0003wC2,X\rC\u0004\u0003\u0002]\u0001\rAa\u0001\u0002\u0011\u0011\fG/\u0019+za\u0016\u0004BA!\u0002\u0003\f5\u0011!q\u0001\u0006\u0004\u0005\u0013A\u0014!\u0002;za\u0016\u001c\u0018\u0002\u0002B\u0007\u0005\u000f\u0011\u0001\u0002R1uCRK\b/Z\u0001\u0018C\u0012$7i\u001c7v[:lU\r^1eCR\fGk\\\"p]\u001a$BAa\u0005\u0003\u001aA\u0019!K!\u0006\n\u0007\t]1K\u0001\u0003V]&$\bb\u0002B\u000e1\u0001\u0007\u0011q[\u0001\tQ&4XmQ8oM\u0006y\u0001O];oKB\u000b'\u000f^5uS>t7\u000f\u0006\u0003\u0003\"\t%\u0002\u0003\u0002-h\u0005G\u0001B!a+\u0003&%!!qEAW\u0005%\u0001\u0016M\u001d;ji&|g\u000eC\u0004\u0003,e\u0001\rA!\t\u0002\u0015A\f'\u000f^5uS>t7/\u0001\tqeVtW\r\u001a)beRLG/[8ogV\u0011!\u0011\u0005\u0015\u00045\u0005E\u0011!\u0004:boB\u000b'\u000f^5uS>t7\u000fK\u0002\u001c\u0003#\t\u0011\u0002Z8Fq\u0016\u001cW\u000f^3\u0015\u0005\tm\u0002C\u0002B\u001f\u0005\u0007\u00129%\u0004\u0002\u0003@)\u0019!\u0011\t\u001e\u0002\u0007I$G-\u0003\u0003\u0003F\t}\"a\u0001*E\tB!!\u0011\nB&\u001b\u0005i\u0015b\u0001B'\u001b\nY\u0011J\u001c;fe:\fGNU8x\u0003\u00152\u0017\u000e\u001c;feVsWo]3e\tft\u0017-\\5d!J,h.\u001b8h\u000bb\u0004(/Z:tS>t7\u000fF\u0002|\u0005'BaA!\u0016\u001e\u0001\u0004Y\u0018A\u00039sK\u0012L7-\u0019;fg\u0006qq-\u001a;J]B,HOR8s[\u0006$HC\u0002B.\u0005#\u0013\u0019\f\r\u0003\u0003^\t\u001d\u0004CBA \u0005?\u0012\u0019'\u0003\u0003\u0003b\u0005-#!B\"mCN\u001c\b\u0003\u0002B3\u0005Ob\u0001\u0001B\u0006\u0003jy\t\t\u0011!A\u0003\u0002\t-$aA0%iE!!Q\u000eB:!\r\u0011&qN\u0005\u0004\u0005c\u001a&a\u0002(pi\"Lgn\u001a\u0019\u0007\u0005k\u0012\u0019I!$\u0011\u0011\t]$Q\u0010BA\u0005\u0017k!A!\u001f\u000b\t\tm\u0014qW\u0001\u0007[\u0006\u0004(/\u001a3\n\t\t}$\u0011\u0010\u0002\f\u0013:\u0004X\u000f\u001e$pe6\fG\u000f\u0005\u0003\u0003f\t\rE\u0001\u0004BC\u0005\u000f\u000b\t\u0011!A\u0003\u0002\t%%aA0%k\u0011Y!\u0011\u000e\u0010\u0002\u0002\u0007\u0005)\u0011\u0001B6#\u0011\u0011i'!>\u0011\t\t\u0015$Q\u0012\u0003\r\u0005\u001f\u00139)!A\u0001\u0002\u000b\u0005!\u0011\u0012\u0002\u0004?\u00122\u0004b\u0002BJ=\u0001\u0007!QS\u0001\u0011S:\u0004X\u000f\u001e$pe6\fGo\u00117bgN\u0004DAa&\u0003\u001cB1\u0011q\bB0\u00053\u0003BA!\u001a\u0003\u001c\u0012a!Q\u0014BI\u0003\u0003\u0005\tQ!\u0001\u0003 \n\u0019q\fJ\u0019\u0012\t\t5$\u0011\u0015\u0019\u0007\u0005G\u00139Ka,\u0011\u0011\t]$Q\u0010BS\u0005[\u0003BA!\u001a\u0003(\u0012a!\u0011\u0016BV\u0003\u0003\u0005\tQ!\u0001\u0003\n\n\u0019q\f\n\u001a\u0005\u0019\tu%\u0011SA\u0001\u0004\u0003\u0015\tAa(\u0011\t\t\u0015$q\u0016\u0003\r\u0005c\u0013Y+!A\u0001\u0002\u000b\u0005!\u0011\u0012\u0002\u0004?\u0012\u001a\u0004bBA\u0015=\u0001\u0007\u0011QF\u0001\u000fI>\u001c\u0015M\\8oS\u000e\fG.\u001b>f)\t\ti\"A\u0007pi\",'oQ8qs\u0006\u0013xm]\u000b\u0003\u0005{\u0003B\u0001W4\u0003@B\u0019!K!1\n\u0007\t\r7K\u0001\u0004B]f\u0014VMZ\u0001\u0005G>\u0004\u0018\u0010\u0006\u0005\u0003J\n5'q\u001aBi)\u0011\tiBa3\t\u000f\u0005\u0005\u0011\u00051\u0001\u0002\u0006!9A-\tI\u0001\u0002\u00041\u0007b\u00029\"!\u0003\u0005\rA\u001d\u0005\bs\u0006\u0002\n\u00111\u0001|\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"Aa6+\u0007\u0019\u0014In\u000b\u0002\u0003\\B!!Q\u001cBt\u001b\t\u0011yN\u0003\u0003\u0003b\n\r\u0018!C;oG\",7m[3e\u0015\r\u0011)oU\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002Bu\u0005?\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"Aa<+\u0007I\u0014I.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\tU(fA>\u0003Z\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0019\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\t}\bc\u0001*\u0004\u0002%\u001911A*\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005U8\u0011\u0002\u0005\n\u0007\u00179\u0013\u0011!a\u0001\u0005\u007f\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAB\t!\u0019\u0019\u0019b!\u0006\u0002v6\u0011\u0011\u0011L\u0005\u0005\u0007/\tIF\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BB\u000f\u0007G\u00012AUB\u0010\u0013\r\u0019\tc\u0015\u0002\b\u0005>|G.Z1o\u0011%\u0019Y!KA\u0001\u0002\u0004\t)0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA1\u0007SA\u0011ba\u0003+\u0003\u0003\u0005\rAa@\u0002\r\u0015\fX/\u00197t)\u0011\u0019iba\f\t\u0013\r-1&!AA\u0002\u0005U\u0018!\u0005%jm\u0016$\u0016M\u00197f'\u000e\fg.\u0012=fGB\u0019\u0011qD\u0017\u0014\u000b5\u0012yla\u000e\u0011\t\re2qH\u0007\u0003\u0007wQAa!\u0010\u0002j\u0005\u0011\u0011n\\\u0005\u0004E\u000emBCAB\u001a\u0003!!xn\u0015;sS:<GCAA1\u0003\u0015\t\u0007\u000f\u001d7z)!\u0019Yea\u0014\u0004R\rMC\u0003BA\u000f\u0007\u001bBq!!\u00011\u0001\u0004\t)\u0001C\u0003ea\u0001\u0007a\rC\u0003qa\u0001\u0007!\u000fC\u0003za\u0001\u000710A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\re3\u0011\r\t\u0006%\u0006\u000561\f\t\u0007%\u000eucM]>\n\u0007\r}3K\u0001\u0004UkBdWm\r\u0005\n\u0007G\n\u0014\u0011!a\u0001\u0003;\t1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019I\u0007\u0005\u0003\u0002d\r-\u0014\u0002BB7\u0003K\u0012aa\u00142kK\u000e$\b"
)
public class HiveTableScanExec extends SparkPlan implements LeafExecNode, CastSupport {
   private Map metrics;
   private Option boundPruningPred;
   private transient Table hiveQlTable;
   private transient TableDesc tableDesc;
   private transient Configuration hadoopConf;
   private transient HadoopTableReader hadoopReader;
   private transient Seq prunedPartitions;
   private transient Seq rawPartitions;
   private final Seq requestedAttributes;
   private final HiveTableRelation relation;
   private final Seq partitionPruningPred;
   private final transient SparkSession sparkSession;
   private final AttributeMap org$apache$spark$sql$hive$execution$HiveTableScanExec$$originalAttributes;
   private final Seq output;
   private volatile byte bitmap$0;
   private transient volatile byte bitmap$trans$0;

   public static Option unapply(final HiveTableScanExec x$0) {
      return HiveTableScanExec$.MODULE$.unapply(x$0);
   }

   public Cast cast(final Expression child, final DataType dataType) {
      return CastSupport.cast$(this, child, dataType);
   }

   public String verboseStringWithOperatorId() {
      return LeafExecNode.verboseStringWithOperatorId$(this);
   }

   public final Seq children() {
      return LeafLike.children$(this);
   }

   public final TreeNode mapChildren(final Function1 f) {
      return LeafLike.mapChildren$(this, f);
   }

   public TreeNode withNewChildrenInternal(final IndexedSeq newChildren) {
      return LeafLike.withNewChildrenInternal$(this, newChildren);
   }

   public Seq requestedAttributes() {
      return this.requestedAttributes;
   }

   public HiveTableRelation relation() {
      return this.relation;
   }

   public Seq partitionPruningPred() {
      return this.partitionPruningPred;
   }

   private SparkSession sparkSession() {
      return this.sparkSession;
   }

   public SQLConf conf() {
      return this.sparkSession().sessionState().conf();
   }

   public String nodeName() {
      return "Scan hive " + this.relation().tableMeta().qualifiedName();
   }

   private Map metrics$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.metrics = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("numOutputRows"), org.apache.spark.sql.execution.metric.SQLMetrics..MODULE$.createMetric(this.sparkContext(), "number of output rows"))})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.metrics;
   }

   public Map metrics() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.metrics$lzycompute() : this.metrics;
   }

   public AttributeSet producedAttributes() {
      return this.outputSet().$plus$plus(org.apache.spark.sql.catalyst.expressions.AttributeSet..MODULE$.apply((Iterable)this.partitionPruningPred().flatMap((x$1) -> x$1.references())));
   }

   public AttributeMap org$apache$spark$sql$hive$execution$HiveTableScanExec$$originalAttributes() {
      return this.org$apache$spark$sql$hive$execution$HiveTableScanExec$$originalAttributes;
   }

   public Seq output() {
      return this.output;
   }

   private Option boundPruningPred$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.boundPruningPred = this.partitionPruningPred().reduceLeftOption(org.apache.spark.sql.catalyst.expressions.And..MODULE$).map((pred) -> {
               boolean var3;
               Predef var10000;
               label17: {
                  label16: {
                     var10000 = .MODULE$;
                     DataType var10001 = pred.dataType();
                     BooleanType var2 = org.apache.spark.sql.types.BooleanType..MODULE$;
                     if (var10001 == null) {
                        if (var2 == null) {
                           break label16;
                        }
                     } else if (var10001.equals(var2)) {
                        break label16;
                     }

                     var3 = false;
                     break label17;
                  }

                  var3 = true;
               }

               var10000.require(var3, () -> "Data type of predicate " + pred + " must be " + org.apache.spark.sql.types.BooleanType..MODULE$.catalogString() + " rather than " + pred.dataType().catalogString() + ".");
               return org.apache.spark.sql.catalyst.expressions.BindReferences..MODULE$.bindReference(pred, org.apache.spark.sql.catalyst.expressions.package..MODULE$.AttributeSeq(this.relation().partitionCols()), org.apache.spark.sql.catalyst.expressions.BindReferences..MODULE$.bindReference$default$3());
            });
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.boundPruningPred;
   }

   private Option boundPruningPred() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.boundPruningPred$lzycompute() : this.boundPruningPred;
   }

   private Table hiveQlTable$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.hiveQlTable = HiveClientImpl$.MODULE$.toHiveTable(this.relation().tableMeta(), HiveClientImpl$.MODULE$.toHiveTable$default$2());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.hiveQlTable;
   }

   private Table hiveQlTable() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.hiveQlTable$lzycompute() : this.hiveQlTable;
   }

   private TableDesc tableDesc$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.tableDesc = new TableDesc(this.getInputFormat(this.hiveQlTable().getInputFormatClass(), this.conf()), this.hiveQlTable().getOutputFormatClass(), this.hiveQlTable().getMetadata());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.tableDesc;
   }

   private TableDesc tableDesc() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.tableDesc$lzycompute() : this.tableDesc;
   }

   private Configuration hadoopConf$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            Configuration c = this.sparkSession().sessionState().newHadoopConf();
            this.addColumnMetadataToConf(c);
            this.hadoopConf = c;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.hadoopConf;
   }

   private Configuration hadoopConf() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.hadoopConf$lzycompute() : this.hadoopConf;
   }

   private HadoopTableReader hadoopReader$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            this.hadoopReader = new HadoopTableReader(this.output(), this.relation().partitionCols(), this.tableDesc(), this.sparkSession(), this.hadoopConf());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.hadoopReader;
   }

   private HadoopTableReader hadoopReader() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.hadoopReader$lzycompute() : this.hadoopReader;
   }

   private Object castFromString(final String value, final DataType dataType) {
      return this.cast(org.apache.spark.sql.catalyst.expressions.Literal..MODULE$.apply(value), dataType).eval((InternalRow)null);
   }

   private void addColumnMetadataToConf(final Configuration hiveConf) {
      AttributeMap columnOrdinals = org.apache.spark.sql.catalyst.expressions.AttributeMap..MODULE$.apply((Seq)this.relation().dataCols().zipWithIndex());
      Seq neededColumnIDs = (Seq)((IterableOps)this.output().flatMap((k) -> columnOrdinals.get(k))).map((o) -> $anonfun$addColumnMetadataToConf$2(BoxesRunTime.unboxToInt(o)));
      Seq neededColumnNames = (Seq)((IterableOps)this.output().filter((k) -> BoxesRunTime.boxToBoolean($anonfun$addColumnMetadataToConf$3(columnOrdinals, k)))).map((x$2) -> x$2.name());
      HiveShim$.MODULE$.appendReadColumns(hiveConf, neededColumnIDs, neededColumnNames);
      Deserializer deserializer = (Deserializer)this.tableDesc().getDeserializerClass().getConstructor().newInstance();
      deserializer.initialize(hiveConf, this.tableDesc().getProperties());
      StructObjectInspector structOI = (StructObjectInspector)ObjectInspectorUtils.getStandardObjectInspector(deserializer.getObjectInspector(), ObjectInspectorCopyOption.JAVA);
      String columnTypeNames = ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(structOI.getAllStructFieldRefs()).asScala().map((x$3) -> x$3.getFieldObjectInspector())).map((x$4) -> TypeInfoUtils.getTypeInfoFromObjectInspector(x$4).getTypeName())).mkString(",");
      hiveConf.set("columns.types", columnTypeNames);
      hiveConf.set("columns", ((IterableOnceOps)this.relation().dataCols().map((x$5) -> x$5.name())).mkString(","));
   }

   public Seq prunePartitions(final Seq partitions) {
      Option var3 = this.boundPruningPred();
      if (scala.None..MODULE$.equals(var3)) {
         return partitions;
      } else if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         Expression shouldKeep = (Expression)var4.value();
         return (Seq)partitions.filter((part) -> BoxesRunTime.boxToBoolean($anonfun$prunePartitions$1(this, shouldKeep, part)));
      } else {
         throw new MatchError(var3);
      }
   }

   private Seq prunedPartitions$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 16) == 0) {
            Seq var10001;
            if (this.relation().prunedPartitions().nonEmpty()) {
               Seq hivePartitions = (Seq)((IterableOps)this.relation().prunedPartitions().get()).map((x$7) -> HiveClientImpl$.MODULE$.toHivePartition(x$7, this.hiveQlTable()));
               var10001 = this.partitionPruningPred().forall((x$8) -> BoxesRunTime.boxToBoolean($anonfun$prunedPartitions$2(x$8))) ? hivePartitions : this.prunePartitions(hivePartitions);
            } else {
               var10001 = this.sparkSession().sessionState().conf().metastorePartitionPruning() && this.partitionPruningPred().nonEmpty() ? this.rawPartitions() : this.prunePartitions(this.rawPartitions());
            }

            this.prunedPartitions = var10001;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 16);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.prunedPartitions;
   }

   public Seq prunedPartitions() {
      return (byte)(this.bitmap$trans$0 & 16) == 0 ? this.prunedPartitions$lzycompute() : this.prunedPartitions;
   }

   private Seq rawPartitions$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 32) == 0) {
            Seq var10001;
            if (this.sparkSession().sessionState().conf().metastorePartitionPruning() && this.partitionPruningPred().nonEmpty()) {
               Seq normalizedFilters = (Seq)this.partitionPruningPred().map((x$9) -> (Expression)x$9.transform(new Serializable() {
                     private static final long serialVersionUID = 0L;
                     // $FF: synthetic field
                     private final HiveTableScanExec $outer;

                     public final Object applyOrElse(final Expression x1, final Function1 default) {
                        if (x1 instanceof AttributeReference var5) {
                           return this.$outer.org$apache$spark$sql$hive$execution$HiveTableScanExec$$originalAttributes().apply(var5);
                        } else {
                           return default.apply(x1);
                        }
                     }

                     public final boolean isDefinedAt(final Expression x1) {
                        return x1 instanceof AttributeReference;
                     }

                     public {
                        if (HiveTableScanExec.this == null) {
                           throw null;
                        } else {
                           this.$outer = HiveTableScanExec.this;
                        }
                     }
                  }));
               var10001 = this.sparkSession().sessionState().catalog().listPartitionsByFilter(this.relation().tableMeta().identifier(), normalizedFilters);
            } else {
               SessionCatalog qual$1 = this.sparkSession().sessionState().catalog();
               TableIdentifier x$1 = this.relation().tableMeta().identifier();
               Option x$2 = qual$1.listPartitions$default$2();
               var10001 = qual$1.listPartitions(x$1, x$2);
            }

            Seq prunedPartitions = var10001;
            this.rawPartitions = (Seq)prunedPartitions.map((x$10) -> HiveClientImpl$.MODULE$.toHivePartition(x$10, this.hiveQlTable()));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 32);
         }
      } catch (Throwable var8) {
         throw var8;
      }

      return this.rawPartitions;
   }

   public Seq rawPartitions() {
      return (byte)(this.bitmap$trans$0 & 32) == 0 ? this.rawPartitions$lzycompute() : this.rawPartitions;
   }

   public RDD doExecute() {
      RDD rdd = !this.relation().isPartitioned() ? (RDD)org.apache.spark.util.Utils..MODULE$.withDummyCallSite(this.sparkContext(), () -> this.hadoopReader().makeRDDForTable(this.hiveQlTable())) : (RDD)org.apache.spark.util.Utils..MODULE$.withDummyCallSite(this.sparkContext(), () -> this.hadoopReader().makeRDDForPartitionedTable(this.prunedPartitions()));
      SQLMetric numOutputRows = this.longMetric("numOutputRows");
      StructType outputSchema = this.schema();
      return rdd.mapPartitionsWithIndexInternal((index, iter) -> $anonfun$doExecute$3(outputSchema, numOutputRows, BoxesRunTime.unboxToInt(index), iter), rdd.mapPartitionsWithIndexInternal$default$2(), rdd.mapPartitionsWithIndexInternal$default$3(), scala.reflect.ClassTag..MODULE$.apply(InternalRow.class));
   }

   private Seq filterUnusedDynamicPruningExpressions(final Seq predicates) {
      return (Seq)predicates.filterNot((x$11) -> BoxesRunTime.boxToBoolean($anonfun$filterUnusedDynamicPruningExpressions$1(x$11)));
   }

   private Class getInputFormat(final Class inputFormatClass, final SQLConf conf) {
      Class var3 = SymlinkTextInputFormat.class;
      if (inputFormatClass == null) {
         if (var3 != null) {
            return inputFormatClass;
         }
      } else if (!inputFormatClass.equals(var3)) {
         return inputFormatClass;
      }

      if (conf != null && BoxesRunTime.unboxToBoolean(conf.getConf(HiveUtils$.MODULE$.USE_DELEGATE_FOR_SYMLINK_TEXT_INPUT_FORMAT()))) {
         return DelegateSymlinkTextInputFormat.class;
      } else {
         return inputFormatClass;
      }
   }

   public HiveTableScanExec doCanonicalize() {
      package.AttributeSeq input = org.apache.spark.sql.catalyst.expressions.package..MODULE$.AttributeSeq(this.relation().output());
      return new HiveTableScanExec((Seq)this.requestedAttributes().map((x$12) -> (Attribute)org.apache.spark.sql.catalyst.plans.QueryPlan..MODULE$.normalizeExpressions(x$12, input)), (HiveTableRelation)this.relation().canonicalized(), org.apache.spark.sql.catalyst.plans.QueryPlan..MODULE$.normalizePredicates(this.filterUnusedDynamicPruningExpressions(this.partitionPruningPred()), input), this.sparkSession());
   }

   public Seq otherCopyArgs() {
      return new scala.collection.immutable..colon.colon(this.sparkSession(), scala.collection.immutable.Nil..MODULE$);
   }

   public HiveTableScanExec copy(final Seq requestedAttributes, final HiveTableRelation relation, final Seq partitionPruningPred, final SparkSession sparkSession) {
      return new HiveTableScanExec(requestedAttributes, relation, partitionPruningPred, sparkSession);
   }

   public Seq copy$default$1() {
      return this.requestedAttributes();
   }

   public HiveTableRelation copy$default$2() {
      return this.relation();
   }

   public Seq copy$default$3() {
      return this.partitionPruningPred();
   }

   public String productPrefix() {
      return "HiveTableScanExec";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.requestedAttributes();
         }
         case 1 -> {
            return this.relation();
         }
         case 2 -> {
            return this.partitionPruningPred();
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
      return x$1 instanceof HiveTableScanExec;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "requestedAttributes";
         }
         case 1 -> {
            return "relation";
         }
         case 2 -> {
            return "partitionPruningPred";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof HiveTableScanExec) {
               label56: {
                  HiveTableScanExec var4 = (HiveTableScanExec)x$1;
                  Seq var10000 = this.requestedAttributes();
                  Seq var5 = var4.requestedAttributes();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  HiveTableRelation var8 = this.relation();
                  HiveTableRelation var6 = var4.relation();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Seq var9 = this.partitionPruningPred();
                  Seq var7 = var4.partitionPruningPred();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   // $FF: synthetic method
   public static final Integer $anonfun$addColumnMetadataToConf$2(final int o) {
      return .MODULE$.int2Integer(o);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$addColumnMetadataToConf$3(final AttributeMap columnOrdinals$1, final Attribute k) {
      return columnOrdinals$1.contains(k);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prunePartitions$1(final HiveTableScanExec $this, final Expression shouldKeep$1, final Partition part) {
      Seq dataTypes = (Seq)$this.relation().partitionCols().map((x$6) -> x$6.dataType());
      Buffer castedValues = (Buffer)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(part.getValues()).asScala().zip(dataTypes)).map((x0$1) -> {
         if (x0$1 != null) {
            String value = (String)x0$1._1();
            DataType dataType = (DataType)x0$1._2();
            return $this.castFromString(value, dataType);
         } else {
            throw new MatchError(x0$1);
         }
      });
      GenericInternalRow row = new GenericInternalRow(castedValues.toArray(scala.reflect.ClassTag..MODULE$.Any()));
      return BoxesRunTime.unboxToBoolean(shouldKeep$1.eval(row));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prunedPartitions$2(final Expression x$8) {
      return !org.apache.spark.sql.execution.ExecSubqueryExpression..MODULE$.hasSubquery(x$8);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$doExecute$3(final StructType outputSchema$1, final SQLMetric numOutputRows$1, final int index, final Iterator iter) {
      UnsafeProjection proj = org.apache.spark.sql.catalyst.expressions.UnsafeProjection..MODULE$.create(outputSchema$1);
      proj.initialize(index);
      return iter.map((r) -> {
         numOutputRows$1.$plus$eq(1L);
         return proj.apply(r);
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterUnusedDynamicPruningExpressions$1(final Expression x$11) {
      boolean var10000;
      label23: {
         DynamicPruningExpression var1 = new DynamicPruningExpression(org.apache.spark.sql.catalyst.expressions.Literal..MODULE$.TrueLiteral());
         if (x$11 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$11.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public HiveTableScanExec(final Seq requestedAttributes, final HiveTableRelation relation, final Seq partitionPruningPred, final SparkSession sparkSession) {
      this.requestedAttributes = requestedAttributes;
      this.relation = relation;
      this.partitionPruningPred = partitionPruningPred;
      this.sparkSession = sparkSession;
      LeafLike.$init$(this);
      LeafExecNode.$init$(this);
      CastSupport.$init$(this);
      .MODULE$.require(partitionPruningPred.isEmpty() || relation.isPartitioned(), () -> "Partition pruning predicates only supported for partitioned tables.");
      this.org$apache$spark$sql$hive$execution$HiveTableScanExec$$originalAttributes = org.apache.spark.sql.catalyst.expressions.AttributeMap..MODULE$.apply((Seq)relation.output().map((a) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(a), a)));
      this.output = (Seq)requestedAttributes.map(this.org$apache$spark$sql$hive$execution$HiveTableScanExec$$originalAttributes());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
