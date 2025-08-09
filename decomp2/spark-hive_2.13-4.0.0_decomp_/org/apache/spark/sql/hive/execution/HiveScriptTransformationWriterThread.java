package org.apache.spark.sql.hive.execution;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.exec.RecordWriter;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.io.Writable;
import org.apache.spark.TaskContext;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.execution.BaseScriptTransformationWriterThread;
import org.apache.spark.sql.execution.ScriptTransformationIOSchema;
import org.apache.spark.sql.hive.HiveInspectors;
import org.apache.spark.sql.hive.HiveShim$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.util.CircularBuffer;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala..less.colon.less.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t}e!B\u00193\u0001Rr\u0004\u0002C.\u0001\u0005+\u0007I\u0011\u0001/\t\u0011\u0019\u0004!\u0011#Q\u0001\nuC\u0001b\u001a\u0001\u0003\u0016\u0004%\t\u0001\u001b\u0005\te\u0002\u0011\t\u0012)A\u0005S\"A1\u000f\u0001BK\u0002\u0013\u0005A\u000f\u0003\u0005\u007f\u0001\tE\t\u0015!\u0003v\u0011%y\bA!f\u0001\n\u0003\t\t\u0001\u0003\u0006\u0002\u0010\u0001\u0011\t\u0012)A\u0005\u0003\u0007A!\"!\u0005\u0001\u0005+\u0007I\u0011AA\n\u0011)\tY\u0002\u0001B\tB\u0003%\u0011Q\u0003\u0005\u000b\u0003;\u0001!Q3A\u0005\u0002\u0005}\u0001BCA\u0019\u0001\tE\t\u0015!\u0003\u0002\"!Q\u00111\u0007\u0001\u0003\u0016\u0004%\t!!\u000e\t\u0015\u0005\r\u0003A!E!\u0002\u0013\t9\u0004\u0003\u0006\u0002F\u0001\u0011)\u001a!C\u0001\u0003\u000fB!\"!\u0016\u0001\u0005#\u0005\u000b\u0011BA%\u0011)\t9\u0006\u0001BK\u0002\u0013\u0005\u0011\u0011\f\u0005\u000b\u0003G\u0002!\u0011#Q\u0001\n\u0005m\u0003BCA3\u0001\tU\r\u0011\"\u0001\u0002h!Q\u00111\u000f\u0001\u0003\u0012\u0003\u0006I!!\u001b\t\u000f\u0005U\u0004\u0001\"\u0001\u0002x!9\u0011\u0011\u0013\u0001\u0005B\u0005M\u0005\"CAN\u0001\u0005\u0005I\u0011AAO\u0011%\t\u0019\fAI\u0001\n\u0003\t)\fC\u0005\u0002L\u0002\t\n\u0011\"\u0001\u0002N\"I\u0011\u0011\u001b\u0001\u0012\u0002\u0013\u0005\u00111\u001b\u0005\n\u0003/\u0004\u0011\u0013!C\u0001\u00033D\u0011\"!8\u0001#\u0003%\t!a8\t\u0013\u0005\r\b!%A\u0005\u0002\u0005\u0015\b\"CAu\u0001E\u0005I\u0011AAv\u0011%\ty\u000fAI\u0001\n\u0003\t\t\u0010C\u0005\u0002v\u0002\t\n\u0011\"\u0001\u0002x\"I\u00111 \u0001\u0012\u0002\u0013\u0005\u0011Q \u0005\n\u0005\u0003\u0001\u0011\u0011!C!\u0005\u0007A\u0011Ba\u0003\u0001\u0003\u0003%\tA!\u0004\t\u0013\tU\u0001!!A\u0005\u0002\t]\u0001\"\u0003B\u0012\u0001\u0005\u0005I\u0011\tB\u0013\u0011%\u0011\t\u0004AA\u0001\n\u0003\u0011\u0019\u0004C\u0005\u0003>\u0001\t\t\u0011\"\u0011\u0003@!I!1\t\u0001\u0002\u0002\u0013\u0005#Q\t\u0005\n\u0005\u000f\u0002\u0011\u0011!C!\u0005\u0013:!B!\u00143\u0003\u0003E\t\u0001\u000eB(\r%\t$'!A\t\u0002Q\u0012\t\u0006C\u0004\u0002v-\"\tAa\u0019\t\u0013\t\u00154&!A\u0005F\t\u001d\u0004\"\u0003B5W\u0005\u0005I\u0011\u0011B6\u0011%\u0011\tiKA\u0001\n\u0003\u0013\u0019\tC\u0005\u0003\u0016.\n\t\u0011\"\u0003\u0003\u0018\n!\u0003*\u001b<f'\u000e\u0014\u0018\u000e\u001d;Ue\u0006t7OZ8s[\u0006$\u0018n\u001c8Xe&$XM\u001d+ie\u0016\fGM\u0003\u00024i\u0005IQ\r_3dkRLwN\u001c\u0006\u0003kY\nA\u0001[5wK*\u0011q\u0007O\u0001\u0004gFd'BA\u001d;\u0003\u0015\u0019\b/\u0019:l\u0015\tYD(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002{\u0005\u0019qN]4\u0014\u000b\u0001yD\t\u0013(\u0011\u0005\u0001\u0013U\"A!\u000b\u0005M2\u0014BA\"B\u0005\u0011\u0012\u0015m]3TGJL\u0007\u000f\u001e+sC:\u001chm\u001c:nCRLwN\\,sSR,'\u000f\u00165sK\u0006$\u0007CA#G\u001b\u0005!\u0014BA$5\u00059A\u0015N^3J]N\u0004Xm\u0019;peN\u0004\"!\u0013'\u000e\u0003)S\u0011aS\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001b*\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002P1:\u0011\u0001K\u0016\b\u0003#Vk\u0011A\u0015\u0006\u0003'R\u000ba\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u0017&\u0011qKS\u0001\ba\u0006\u001c7.Y4f\u0013\tI&L\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002X\u0015\u0006!\u0011\u000e^3s+\u0005i\u0006cA(_A&\u0011qL\u0017\u0002\t\u0013R,'/\u0019;peB\u0011\u0011\rZ\u0007\u0002E*\u00111MN\u0001\tG\u0006$\u0018\r\\=ti&\u0011QM\u0019\u0002\f\u0013:$XM\u001d8bYJ{w/A\u0003ji\u0016\u0014\b%A\u0006j]B,HoU2iK6\fW#A5\u0011\u0007=SG.\u0003\u0002l5\n\u00191+Z9\u0011\u00055\u0004X\"\u00018\u000b\u0005=4\u0014!\u0002;za\u0016\u001c\u0018BA9o\u0005!!\u0015\r^1UsB,\u0017\u0001D5oaV$8k\u00195f[\u0006\u0004\u0013AC5oaV$8+\u001a:eKV\tQ\u000f\u0005\u0002wy6\tqO\u0003\u0002ys\u000611/\u001a:eKJR!!\u000e>\u000b\u0005mT\u0014A\u00025bI>|\u0007/\u0003\u0002~o\ni\u0011IY:ue\u0006\u001cGoU3s\t\u0016\f1\"\u001b8qkR\u001cVM\u001d3fA\u0005A\u0011N\u001c9viN{\u0017.\u0006\u0002\u0002\u0004A!\u0011QAA\u0006\u001b\t\t9AC\u0002\u0002\n]\fqb\u001c2kK\u000e$\u0018N\\:qK\u000e$xN]\u0005\u0005\u0003\u001b\t9AA\u000bTiJ,8\r^(cU\u0016\u001cG/\u00138ta\u0016\u001cGo\u001c:\u0002\u0013%t\u0007/\u001e;T_&\u0004\u0013\u0001C5p'\u000eDW-\\1\u0016\u0005\u0005U\u0001c\u0001!\u0002\u0018%\u0019\u0011\u0011D!\u00039M\u001b'/\u001b9u)J\fgn\u001d4pe6\fG/[8o\u0013>\u001b6\r[3nC\u0006I\u0011n\\*dQ\u0016l\u0017\rI\u0001\r_V$\b/\u001e;TiJ,\u0017-\\\u000b\u0003\u0003C\u0001B!a\t\u0002.5\u0011\u0011Q\u0005\u0006\u0005\u0003O\tI#\u0001\u0002j_*\u0011\u00111F\u0001\u0005U\u00064\u0018-\u0003\u0003\u00020\u0005\u0015\"\u0001D(viB,Ho\u0015;sK\u0006l\u0017!D8viB,Ho\u0015;sK\u0006l\u0007%\u0001\u0003qe>\u001cWCAA\u001c!\u0011\tI$a\u0010\u000e\u0005\u0005m\"\u0002BA\u001f\u0003S\tA\u0001\\1oO&!\u0011\u0011IA\u001e\u0005\u001d\u0001&o\\2fgN\fQ\u0001\u001d:pG\u0002\nAb\u001d;eKJ\u0014()\u001e4gKJ,\"!!\u0013\u0011\t\u0005-\u0013\u0011K\u0007\u0003\u0003\u001bR1!a\u00149\u0003\u0011)H/\u001b7\n\t\u0005M\u0013Q\n\u0002\u000f\u0007&\u00148-\u001e7be\n+hMZ3s\u00035\u0019H\u000fZ3se\n+hMZ3sA\u0005YA/Y:l\u0007>tG/\u001a=u+\t\tY\u0006\u0005\u0003\u0002^\u0005}S\"\u0001\u001d\n\u0007\u0005\u0005\u0004HA\u0006UCN\\7i\u001c8uKb$\u0018\u0001\u0004;bg.\u001cuN\u001c;fqR\u0004\u0013\u0001B2p]\u001a,\"!!\u001b\u0011\t\u0005-\u0014qN\u0007\u0003\u0003[R1!!\u001a{\u0013\u0011\t\t(!\u001c\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003\u0015\u0019wN\u001c4!\u0003\u0019a\u0014N\\5u}Q1\u0012\u0011PA?\u0003\u007f\n\t)a!\u0002\u0006\u0006\u001d\u0015\u0011RAF\u0003\u001b\u000by\tE\u0002\u0002|\u0001i\u0011A\r\u0005\u00067V\u0001\r!\u0018\u0005\u0006OV\u0001\r!\u001b\u0005\u0006gV\u0001\r!\u001e\u0005\u0007\u007fV\u0001\r!a\u0001\t\u000f\u0005EQ\u00031\u0001\u0002\u0016!9\u0011QD\u000bA\u0002\u0005\u0005\u0002bBA\u001a+\u0001\u0007\u0011q\u0007\u0005\b\u0003\u000b*\u0002\u0019AA%\u0011\u001d\t9&\u0006a\u0001\u00037Bq!!\u001a\u0016\u0001\u0004\tI'A\u0006qe>\u001cWm]:S_^\u001cHCAAK!\rI\u0015qS\u0005\u0004\u00033S%\u0001B+oSR\fAaY8qsR1\u0012\u0011PAP\u0003C\u000b\u0019+!*\u0002(\u0006%\u00161VAW\u0003_\u000b\t\fC\u0004\\/A\u0005\t\u0019A/\t\u000f\u001d<\u0002\u0013!a\u0001S\"91o\u0006I\u0001\u0002\u0004)\b\u0002C@\u0018!\u0003\u0005\r!a\u0001\t\u0013\u0005Eq\u0003%AA\u0002\u0005U\u0001\"CA\u000f/A\u0005\t\u0019AA\u0011\u0011%\t\u0019d\u0006I\u0001\u0002\u0004\t9\u0004C\u0005\u0002F]\u0001\n\u00111\u0001\u0002J!I\u0011qK\f\u0011\u0002\u0003\u0007\u00111\f\u0005\n\u0003K:\u0002\u0013!a\u0001\u0003S\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u00028*\u001aQ,!/,\u0005\u0005m\u0006\u0003BA_\u0003\u000fl!!a0\u000b\t\u0005\u0005\u00171Y\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!2K\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u0013\fyLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002P*\u001a\u0011.!/\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011Q\u001b\u0016\u0004k\u0006e\u0016AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u00037TC!a\u0001\u0002:\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012*TCAAqU\u0011\t)\"!/\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%mU\u0011\u0011q\u001d\u0016\u0005\u0003C\tI,\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\u00055(\u0006BA\u001c\u0003s\u000babY8qs\u0012\"WMZ1vYR$\u0003(\u0006\u0002\u0002t*\"\u0011\u0011JA]\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIe*\"!!?+\t\u0005m\u0013\u0011X\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132aU\u0011\u0011q \u0016\u0005\u0003S\nI,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005\u000b\u0001B!!\u000f\u0003\b%!!\u0011BA\u001e\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011!q\u0002\t\u0004\u0013\nE\u0011b\u0001B\n\u0015\n\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!!\u0011\u0004B\u0010!\rI%1D\u0005\u0004\u0005;Q%aA!os\"I!\u0011\u0005\u0013\u0002\u0002\u0003\u0007!qB\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t\u001d\u0002C\u0002B\u0015\u0005_\u0011I\"\u0004\u0002\u0003,)\u0019!Q\u0006&\u0002\u0015\r|G\u000e\\3di&|g.C\u0002`\u0005W\t\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005k\u0011Y\u0004E\u0002J\u0005oI1A!\u000fK\u0005\u001d\u0011un\u001c7fC:D\u0011B!\t'\u0003\u0003\u0005\rA!\u0007\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005\u000b\u0011\t\u0005C\u0005\u0003\"\u001d\n\t\u00111\u0001\u0003\u0010\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0003\u0010\u00051Q-];bYN$BA!\u000e\u0003L!I!\u0011E\u0015\u0002\u0002\u0003\u0007!\u0011D\u0001%\u0011&4XmU2sSB$HK]1og\u001a|'/\\1uS>twK]5uKJ$\u0006N]3bIB\u0019\u00111P\u0016\u0014\u000b-\u0012\u0019Fa\u0018\u0011/\tU#1L/jk\u0006\r\u0011QCA\u0011\u0003o\tI%a\u0017\u0002j\u0005eTB\u0001B,\u0015\r\u0011IFS\u0001\beVtG/[7f\u0013\u0011\u0011iFa\u0016\u0003%\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\r\t\u0005\u0003G\u0011\t'C\u0002Z\u0003K!\"Aa\u0014\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"A!\u0002\u0002\u000b\u0005\u0004\b\u000f\\=\u0015-\u0005e$Q\u000eB8\u0005c\u0012\u0019H!\u001e\u0003x\te$1\u0010B?\u0005\u007fBQa\u0017\u0018A\u0002uCQa\u001a\u0018A\u0002%DQa\u001d\u0018A\u0002UDaa \u0018A\u0002\u0005\r\u0001bBA\t]\u0001\u0007\u0011Q\u0003\u0005\b\u0003;q\u0003\u0019AA\u0011\u0011\u001d\t\u0019D\fa\u0001\u0003oAq!!\u0012/\u0001\u0004\tI\u0005C\u0004\u0002X9\u0002\r!a\u0017\t\u000f\u0005\u0015d\u00061\u0001\u0002j\u00059QO\\1qa2LH\u0003\u0002BC\u0005#\u0003R!\u0013BD\u0005\u0017K1A!#K\u0005\u0019y\u0005\u000f^5p]B!\u0012J!$^SV\f\u0019!!\u0006\u0002\"\u0005]\u0012\u0011JA.\u0003SJ1Aa$K\u0005\u001d!V\u000f\u001d7fcAB\u0011Ba%0\u0003\u0003\u0005\r!!\u001f\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u001aB!\u0011\u0011\bBN\u0013\u0011\u0011i*a\u000f\u0003\r=\u0013'.Z2u\u0001"
)
public class HiveScriptTransformationWriterThread extends BaseScriptTransformationWriterThread implements HiveInspectors, Product, Serializable {
   private final Iterator iter;
   private final Seq inputSchema;
   private final AbstractSerDe inputSerde;
   private final StructObjectInspector inputSoi;
   private final ScriptTransformationIOSchema ioSchema;
   private final OutputStream outputStream;
   private final Process proc;
   private final CircularBuffer stderrBuffer;
   private final TaskContext taskContext;
   private final Configuration conf;

   public static Option unapply(final HiveScriptTransformationWriterThread x$0) {
      return HiveScriptTransformationWriterThread$.MODULE$.unapply(x$0);
   }

   public static HiveScriptTransformationWriterThread apply(final Iterator iter, final Seq inputSchema, final AbstractSerDe inputSerde, final StructObjectInspector inputSoi, final ScriptTransformationIOSchema ioSchema, final OutputStream outputStream, final Process proc, final CircularBuffer stderrBuffer, final TaskContext taskContext, final Configuration conf) {
      return HiveScriptTransformationWriterThread$.MODULE$.apply(iter, inputSchema, inputSerde, inputSoi, ioSchema, outputStream, proc, stderrBuffer, taskContext, conf);
   }

   public static Function1 tupled() {
      return HiveScriptTransformationWriterThread$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return HiveScriptTransformationWriterThread$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public DataType javaTypeToDataType(final Type clz) {
      return HiveInspectors.javaTypeToDataType$(this, clz);
   }

   public Function1 wrapperFor(final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrapperFor$(this, oi, dataType);
   }

   public Function1 unwrapperFor(final ObjectInspector objectInspector) {
      return HiveInspectors.unwrapperFor$(this, (ObjectInspector)objectInspector);
   }

   public Function3 unwrapperFor(final StructField field) {
      return HiveInspectors.unwrapperFor$(this, (StructField)field);
   }

   public Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrap$(this, (Object)a, (ObjectInspector)oi, (DataType)dataType);
   }

   public Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return HiveInspectors.wrap$(this, row, wrappers, cache, dataTypes);
   }

   public Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      return HiveInspectors.wrap$(this, (Seq)row, (Function1[])wrappers, (Object[])cache);
   }

   public ObjectInspector toInspector(final DataType dataType) {
      return HiveInspectors.toInspector$(this, (DataType)dataType);
   }

   public ObjectInspector toInspector(final Expression expr) {
      return HiveInspectors.toInspector$(this, (Expression)expr);
   }

   public DataType inspectorToDataType(final ObjectInspector inspector) {
      return HiveInspectors.inspectorToDataType$(this, inspector);
   }

   public HiveInspectors.typeInfoConversions typeInfoConversions(final DataType dt) {
      return HiveInspectors.typeInfoConversions$(this, dt);
   }

   public Iterator iter() {
      return this.iter;
   }

   public Seq inputSchema() {
      return this.inputSchema;
   }

   public AbstractSerDe inputSerde() {
      return this.inputSerde;
   }

   public StructObjectInspector inputSoi() {
      return this.inputSoi;
   }

   public ScriptTransformationIOSchema ioSchema() {
      return this.ioSchema;
   }

   public OutputStream outputStream() {
      return this.outputStream;
   }

   public Process proc() {
      return this.proc;
   }

   public CircularBuffer stderrBuffer() {
      return this.stderrBuffer;
   }

   public TaskContext taskContext() {
      return this.taskContext;
   }

   public Configuration conf() {
      return this.conf;
   }

   public void processRows() {
      DataOutputStream dataOutputStream = new DataOutputStream(this.outputStream());
      RecordWriter scriptInputWriter = (RecordWriter)HiveScriptIOSchema$.MODULE$.recordWriter(this.ioSchema(), dataOutputStream, this.conf()).orNull(.MODULE$.refl());
      if (this.inputSerde() == null) {
         this.processRowsWithoutSerde();
      } else {
         Object[] hiveData = new Object[this.inputSchema().length()];
         ObjectInspector[] fieldOIs = (ObjectInspector[])((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.inputSoi().getAllStructFieldRefs()).asScala().map((x$6) -> x$6.getFieldObjectInspector())).toArray(scala.reflect.ClassTag..MODULE$.apply(ObjectInspector.class));
         Function1[] wrappers = (Function1[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])fieldOIs), this.inputSchema())), (x0$1) -> {
            if (x0$1 != null) {
               ObjectInspector f = (ObjectInspector)x0$1._1();
               DataType dt = (DataType)x0$1._2();
               return this.wrapperFor(f, dt);
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Function1.class));
         this.iter().foreach((row) -> {
            $anonfun$processRows$3(this, fieldOIs, hiveData, wrappers, scriptInputWriter, dataOutputStream, row);
            return BoxedUnit.UNIT;
         });
      }
   }

   public HiveScriptTransformationWriterThread copy(final Iterator iter, final Seq inputSchema, final AbstractSerDe inputSerde, final StructObjectInspector inputSoi, final ScriptTransformationIOSchema ioSchema, final OutputStream outputStream, final Process proc, final CircularBuffer stderrBuffer, final TaskContext taskContext, final Configuration conf) {
      return new HiveScriptTransformationWriterThread(iter, inputSchema, inputSerde, inputSoi, ioSchema, outputStream, proc, stderrBuffer, taskContext, conf);
   }

   public Iterator copy$default$1() {
      return this.iter();
   }

   public Configuration copy$default$10() {
      return this.conf();
   }

   public Seq copy$default$2() {
      return this.inputSchema();
   }

   public AbstractSerDe copy$default$3() {
      return this.inputSerde();
   }

   public StructObjectInspector copy$default$4() {
      return this.inputSoi();
   }

   public ScriptTransformationIOSchema copy$default$5() {
      return this.ioSchema();
   }

   public OutputStream copy$default$6() {
      return this.outputStream();
   }

   public Process copy$default$7() {
      return this.proc();
   }

   public CircularBuffer copy$default$8() {
      return this.stderrBuffer();
   }

   public TaskContext copy$default$9() {
      return this.taskContext();
   }

   public String productPrefix() {
      return "HiveScriptTransformationWriterThread";
   }

   public int productArity() {
      return 10;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.iter();
         }
         case 1 -> {
            return this.inputSchema();
         }
         case 2 -> {
            return this.inputSerde();
         }
         case 3 -> {
            return this.inputSoi();
         }
         case 4 -> {
            return this.ioSchema();
         }
         case 5 -> {
            return this.outputStream();
         }
         case 6 -> {
            return this.proc();
         }
         case 7 -> {
            return this.stderrBuffer();
         }
         case 8 -> {
            return this.taskContext();
         }
         case 9 -> {
            return this.conf();
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
      return x$1 instanceof HiveScriptTransformationWriterThread;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "iter";
         }
         case 1 -> {
            return "inputSchema";
         }
         case 2 -> {
            return "inputSerde";
         }
         case 3 -> {
            return "inputSoi";
         }
         case 4 -> {
            return "ioSchema";
         }
         case 5 -> {
            return "outputStream";
         }
         case 6 -> {
            return "proc";
         }
         case 7 -> {
            return "stderrBuffer";
         }
         case 8 -> {
            return "taskContext";
         }
         case 9 -> {
            return "conf";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var24;
      if (this != x$1) {
         label119: {
            if (x$1 instanceof HiveScriptTransformationWriterThread) {
               label112: {
                  HiveScriptTransformationWriterThread var4 = (HiveScriptTransformationWriterThread)x$1;
                  Iterator var10000 = this.iter();
                  Iterator var5 = var4.iter();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label112;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label112;
                  }

                  Seq var15 = this.inputSchema();
                  Seq var6 = var4.inputSchema();
                  if (var15 == null) {
                     if (var6 != null) {
                        break label112;
                     }
                  } else if (!var15.equals(var6)) {
                     break label112;
                  }

                  AbstractSerDe var16 = this.inputSerde();
                  AbstractSerDe var7 = var4.inputSerde();
                  if (var16 == null) {
                     if (var7 != null) {
                        break label112;
                     }
                  } else if (!var16.equals(var7)) {
                     break label112;
                  }

                  StructObjectInspector var17 = this.inputSoi();
                  StructObjectInspector var8 = var4.inputSoi();
                  if (var17 == null) {
                     if (var8 != null) {
                        break label112;
                     }
                  } else if (!var17.equals(var8)) {
                     break label112;
                  }

                  ScriptTransformationIOSchema var18 = this.ioSchema();
                  ScriptTransformationIOSchema var9 = var4.ioSchema();
                  if (var18 == null) {
                     if (var9 != null) {
                        break label112;
                     }
                  } else if (!var18.equals(var9)) {
                     break label112;
                  }

                  OutputStream var19 = this.outputStream();
                  OutputStream var10 = var4.outputStream();
                  if (var19 == null) {
                     if (var10 != null) {
                        break label112;
                     }
                  } else if (!var19.equals(var10)) {
                     break label112;
                  }

                  Process var20 = this.proc();
                  Process var11 = var4.proc();
                  if (var20 == null) {
                     if (var11 != null) {
                        break label112;
                     }
                  } else if (!var20.equals(var11)) {
                     break label112;
                  }

                  CircularBuffer var21 = this.stderrBuffer();
                  CircularBuffer var12 = var4.stderrBuffer();
                  if (var21 == null) {
                     if (var12 != null) {
                        break label112;
                     }
                  } else if (!var21.equals(var12)) {
                     break label112;
                  }

                  TaskContext var22 = this.taskContext();
                  TaskContext var13 = var4.taskContext();
                  if (var22 == null) {
                     if (var13 != null) {
                        break label112;
                     }
                  } else if (!var22.equals(var13)) {
                     break label112;
                  }

                  Configuration var23 = this.conf();
                  Configuration var14 = var4.conf();
                  if (var23 == null) {
                     if (var14 != null) {
                        break label112;
                     }
                  } else if (!var23.equals(var14)) {
                     break label112;
                  }

                  if (var4.canEqual(this)) {
                     break label119;
                  }
               }
            }

            var24 = false;
            return var24;
         }
      }

      var24 = true;
      return var24;
   }

   // $FF: synthetic method
   public static final void $anonfun$processRows$3(final HiveScriptTransformationWriterThread $this, final ObjectInspector[] fieldOIs$1, final Object[] hiveData$1, final Function1[] wrappers$1, final RecordWriter scriptInputWriter$1, final DataOutputStream dataOutputStream$1, final InternalRow row) {
      for(int i = 0; i < fieldOIs$1.length; ++i) {
         hiveData$1[i] = row.isNullAt(i) ? null : wrappers$1[i].apply(row.get(i, (DataType)$this.inputSchema().apply(i)));
      }

      Writable writable = $this.inputSerde().serialize(hiveData$1, $this.inputSoi());
      if (scriptInputWriter$1 != null) {
         scriptInputWriter$1.write(writable);
      } else {
         HiveShim$.MODULE$.prepareWritable(writable, $this.ioSchema().outputSerdeProps()).write(dataOutputStream$1);
      }
   }

   public HiveScriptTransformationWriterThread(final Iterator iter, final Seq inputSchema, final AbstractSerDe inputSerde, final StructObjectInspector inputSoi, final ScriptTransformationIOSchema ioSchema, final OutputStream outputStream, final Process proc, final CircularBuffer stderrBuffer, final TaskContext taskContext, final Configuration conf) {
      this.iter = iter;
      this.inputSchema = inputSchema;
      this.inputSerde = inputSerde;
      this.inputSoi = inputSoi;
      this.ioSchema = ioSchema;
      this.outputStream = outputStream;
      this.proc = proc;
      this.stderrBuffer = stderrBuffer;
      this.taskContext = taskContext;
      this.conf = conf;
      HiveInspectors.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
