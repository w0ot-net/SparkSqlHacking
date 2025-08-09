package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.attribute.BinaryAttribute;
import org.apache.spark.ml.attribute.BinaryAttribute$;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.ml.attribute.NumericAttribute$;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction2;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r]c\u0001B\u001f?\u0001%C\u0001\"\u0017\u0001\u0003\u0006\u0004%\tE\u0017\u0005\tc\u0002\u0011\t\u0011)A\u00057\"A1\u000f\u0001BC\u0002\u0013\u0005A\u000f\u0003\u0005{\u0001\t\u0005\t\u0015!\u0003v\u0011!a\bA!b\u0001\n\u0003i\b\"CA\u0007\u0001\t\u0005\t\u0015!\u0003\u007f\u0011!\t\t\u0002\u0001C\u0001\u0001\u0006M\u0001\u0002CA\t\u0001\u0011\u0005\u0001)!\t\t\u000f\u0005\r\u0002\u0001\"\u0001\u0002&!A\u0011q\t\u0001\u0005\u0002\t\u000bI\u0005C\u0005\u0002n\u0001\u0011\r\u0011\"\u0003\u0002p!A\u00111\u0011\u0001!\u0002\u0013\t\t\b\u0003\u0006\u0002\u0006\u0002A)\u0019!C\u0005\u0003\u000fCq!a'\u0001\t\u0003\ti\nC\u0004\u0002(\u0002!\t!!+\t\u000f\u0005=\u0006\u0001\"\u0011\u00022\"9\u0011Q\u001c\u0001\u0005B\u0005}\u0007bBAz\u0001\u0011%\u0011Q\u001f\u0005\b\u0003\u007f\u0004A\u0011\tB\u0001\u0011\u001d\u0011I\u0002\u0001C!\u00057AqA!\u000b\u0001\t\u0003\u0012YcB\u0004\u00036yB\tAa\u000e\u0007\rur\u0004\u0012\u0001B\u001d\u0011\u001d\t\tb\u0006C\u0001\u0005'2qA!\u0016\u0018\u0001]\u00119\u0006C\u0005\u0003Ze\u0011\t\u0011)A\u0005\u001d\"9\u0011\u0011C\r\u0005\u0002\tmcA\u0002B23\u0011\u0013)\u0007\u0003\u0005t9\tU\r\u0011\"\u0001u\u0011!QHD!E!\u0002\u0013)\b\u0002\u0003?\u001d\u0005+\u0007I\u0011A?\t\u0013\u00055AD!E!\u0002\u0013q\bbBA\t9\u0011\u0005!1\u0010\u0005\n\u0003\u007fd\u0012\u0011!C\u0001\u0005\u000bC\u0011Ba#\u001d#\u0003%\tA!$\t\u0013\t\u0005F$%A\u0005\u0002\t\r\u0006\"\u0003BT9\u0005\u0005I\u0011\tBU\u0011!\u0011y\u000bHA\u0001\n\u0003!\b\"\u0003BY9\u0005\u0005I\u0011\u0001BZ\u0011%\u0011I\fHA\u0001\n\u0003\u0012Y\fC\u0005\u0003Jr\t\t\u0011\"\u0001\u0003L\"I!Q\u001b\u000f\u0002\u0002\u0013\u0005#q\u001b\u0005\n\u00057d\u0012\u0011!C!\u0005;D\u0011B!\u000b\u001d\u0003\u0003%\tEa8\t\u0013\t\u0005H$!A\u0005B\t\rx!\u0003Bt3\u0005\u0005\t\u0012\u0002Bu\r%\u0011\u0019'GA\u0001\u0012\u0013\u0011Y\u000fC\u0004\u0002\u0012=\"\tA!?\t\u0013\t%r&!A\u0005F\t}\u0007\"\u0003B~_\u0005\u0005I\u0011\u0011B\u007f\u0011%\u0019\u0019aLA\u0001\n\u0003\u001b)\u0001C\u0004\u0004\u0018e!\tf!\u0007\u0007\r\r\u0015r\u0003BB\u0014\u0011\u001d\t\t\"\u000eC\u0001\u0007_A\u0011ba\r6\u0005\u0004%IA!+\t\u0011\rUR\u0007)A\u0005\u0005WCqaa\u000e6\t\u0003\u001aI\u0004C\u0004\u0004>]!\tea\u0010\t\u000f\r]r\u0003\"\u0011\u0004D!I1\u0011J\f\u0002\u0002\u0013%11\n\u0002\u0013-\u0016\u001cGo\u001c:J]\u0012,\u00070\u001a:N_\u0012,GN\u0003\u0002@\u0001\u00069a-Z1ukJ,'BA!C\u0003\tiGN\u0003\u0002D\t\u0006)1\u000f]1sW*\u0011QIR\u0001\u0007CB\f7\r[3\u000b\u0003\u001d\u000b1a\u001c:h\u0007\u0001\u0019B\u0001\u0001&Q'B\u00191\n\u0014(\u000e\u0003\u0001K!!\u0014!\u0003\u000b5{G-\u001a7\u0011\u0005=\u0003Q\"\u0001 \u0011\u0005=\u000b\u0016B\u0001*?\u0005M1Vm\u0019;pe&sG-\u001a=feB\u000b'/Y7t!\t!v+D\u0001V\u0015\t1\u0006)\u0001\u0003vi&d\u0017B\u0001-V\u0005)iEj\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#A.\u0011\u0005q+gBA/d!\tq\u0016-D\u0001`\u0015\t\u0001\u0007*\u0001\u0004=e>|GO\u0010\u0006\u0002E\u0006)1oY1mC&\u0011A-Y\u0001\u0007!J,G-\u001a4\n\u0005\u0019<'AB*ue&twM\u0003\u0002eC\"\u001a\u0011![8\u0011\u0005)lW\"A6\u000b\u00051\u0014\u0015AC1o]>$\u0018\r^5p]&\u0011an\u001b\u0002\u0006'&t7-Z\u0011\u0002a\u0006)\u0011G\f\u001b/a\u0005!Q/\u001b3!Q\r\u0011\u0011n\\\u0001\f]Vlg)Z1ukJ,7/F\u0001v!\t1x/D\u0001b\u0013\tA\u0018MA\u0002J]RD3aA5p\u00031qW/\u001c$fCR,(/Z:!Q\r!\u0011n\\\u0001\rG\u0006$XmZ8ss6\u000b\u0007o]\u000b\u0002}B)Al`;\u0002\u0004%\u0019\u0011\u0011A4\u0003\u00075\u000b\u0007\u000fE\u0003]\u007f\u0006\u0015Q\u000fE\u0002w\u0003\u000fI1!!\u0003b\u0005\u0019!u.\u001e2mK\"\u001aQ![8\u0002\u001b\r\fG/Z4pefl\u0015\r]:!Q\r1\u0011n\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000f9\u000b)\"!\u0007\u0002\u001e!)\u0011l\u0002a\u00017\"\"\u0011QC5p\u0011\u0015\u0019x\u00011\u0001vQ\u0011\tI\"[8\t\u000bq<\u0001\u0019\u0001@)\t\u0005u\u0011n\u001c\u000b\u0002\u001d\u0006\u0001\".\u0019<b\u0007\u0006$XmZ8ss6\u000b\u0007o]\u000b\u0003\u0003O\u0001\u0002\"!\u000b\u00022\u0005M\u0012qH\u0007\u0003\u0003WQ1AVA\u0017\u0015\t\ty#\u0001\u0003kCZ\f\u0017\u0002BA\u0001\u0003W\u0001B!!\u000e\u0002<5\u0011\u0011q\u0007\u0006\u0005\u0003s\ti#\u0001\u0003mC:<\u0017\u0002BA\u001f\u0003o\u0011q!\u00138uK\u001e,'\u000f\u0005\u0005\u0002*\u0005E\u0012\u0011IA\u001a!\u0011\t)$a\u0011\n\t\u0005%\u0011q\u0007\u0015\u0004\u0013%|\u0017AD2bi\u0016<wN]=NCB\u001cHIR\u000b\u0003\u0003\u0017\u0002B!!\u0014\u0002h9!\u0011qJA1\u001d\u0011\t\t&!\u0018\u000f\t\u0005M\u00131\f\b\u0005\u0003+\nIFD\u0002_\u0003/J\u0011aR\u0005\u0003\u000b\u001aK!a\u0011#\n\u0007\u0005}#)A\u0002tc2LA!a\u0019\u0002f\u00059\u0001/Y2lC\u001e,'bAA0\u0005&!\u0011\u0011NA6\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0003\u0002d\u0005\u0015\u0014\u0001\u00079beRL\u0017\r\u001c$fCR,(/Z!uiJL'-\u001e;fgV\u0011\u0011\u0011\u000f\t\u0006m\u0006M\u0014qO\u0005\u0004\u0003k\n'!B!se\u0006L\b\u0003BA=\u0003\u007fj!!a\u001f\u000b\u0007\u0005u\u0004)A\u0005biR\u0014\u0018NY;uK&!\u0011\u0011QA>\u0005%\tE\u000f\u001e:jEV$X-A\rqCJ$\u0018.\u00197GK\u0006$XO]3BiR\u0014\u0018NY;uKN\u0004\u0013!\u0004;sC:\u001chm\u001c:n\rVt7-\u0006\u0002\u0002\nB9a/a#\u0002\u0010\u0006=\u0015bAAGC\nIa)\u001e8di&|g.\r\t\u0005\u0003#\u000b9*\u0004\u0002\u0002\u0014*\u0019\u0011Q\u0013!\u0002\r1Lg.\u00197h\u0013\u0011\tI*a%\u0003\rY+7\r^8s\u0003-\u0019X\r^%oaV$8i\u001c7\u0015\t\u0005}\u0015\u0011U\u0007\u0002\u0001!1\u00111\u0015\bA\u0002m\u000bQA^1mk\u0016D3AD5p\u00031\u0019X\r^(viB,HoQ8m)\u0011\ty*a+\t\r\u0005\rv\u00021\u0001\\Q\ry\u0011n\\\u0001\niJ\fgn\u001d4pe6$B!a\u0013\u00024\"9\u0011Q\u0017\tA\u0002\u0005]\u0016a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003s\u000b)\r\u0005\u0004\u0002<\u0006u\u0016\u0011Y\u0007\u0003\u0003KJA!a0\u0002f\t9A)\u0019;bg\u0016$\b\u0003BAb\u0003\u000bd\u0001\u0001\u0002\u0007\u0002H\u0006M\u0016\u0011!A\u0001\u0006\u0003\tIMA\u0002`II\nB!a3\u0002RB\u0019a/!4\n\u0007\u0005=\u0017MA\u0004O_RD\u0017N\\4\u0011\u0007Y\f\u0019.C\u0002\u0002V\u0006\u00141!\u00118zQ\u0011\u0001\u0012.!7\"\u0005\u0005m\u0017!\u0002\u001a/a9\u0002\u0014a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005\u0005\u0018Q\u001e\t\u0005\u0003G\fI/\u0004\u0002\u0002f*!\u0011q]A3\u0003\u0015!\u0018\u0010]3t\u0013\u0011\tY/!:\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002pF\u0001\r!!9\u0002\rM\u001c\u0007.Z7bQ\r\t\u0012n\\\u0001\u0010aJ,\u0007oT;uaV$h)[3mIR!\u0011q_A\u007f!\u0011\t\u0019/!?\n\t\u0005m\u0018Q\u001d\u0002\f'R\u0014Xo\u0019;GS\u0016dG\rC\u0004\u0002pJ\u0001\r!!9\u0002\t\r|\u0007/\u001f\u000b\u0004\u001d\n\r\u0001b\u0002B\u0003'\u0001\u0007!qA\u0001\u0006Kb$(/\u0019\t\u0005\u0005\u0013\u0011y!\u0004\u0002\u0003\f)\u0019!Q\u0002!\u0002\u000bA\f'/Y7\n\t\tE!1\u0002\u0002\t!\u0006\u0014\u0018-\\'ba\"\"1#\u001bB\u000bC\t\u00119\"A\u00032]Qr\u0013'A\u0003xe&$X-\u0006\u0002\u0003\u001eA\u0019AKa\b\n\u0007\t\u0005RK\u0001\u0005N\u0019^\u0013\u0018\u000e^3sQ\u0011!\u0012N!\n\"\u0005\t\u001d\u0012!B\u0019/m9\u0002\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003mCC!F5\u00030\u0005\u0012!\u0011G\u0001\u0006g9\u0002d\u0006\r\u0015\u0004\u0001%|\u0017A\u0005,fGR|'/\u00138eKb,'/T8eK2\u0004\"aT\f\u0014\u000f]\u0011YD!\u0011\u0003HA\u0019aO!\u0010\n\u0007\t}\u0012M\u0001\u0004B]f\u0014VM\u001a\t\u0005)\n\rc*C\u0002\u0003FU\u0013!\"\u0014'SK\u0006$\u0017M\u00197f!\u0011\u0011IEa\u0014\u000e\u0005\t-#\u0002\u0002B'\u0003[\t!![8\n\t\tE#1\n\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0005o\u0011\u0001DV3di>\u0014\u0018J\u001c3fq\u0016\u0014Xj\u001c3fY^\u0013\u0018\u000e^3s'\rI\"QD\u0001\tS:\u001cH/\u00198dKR!!Q\fB1!\r\u0011y&G\u0007\u0002/!1!\u0011L\u000eA\u00029\u0013A\u0001R1uCN9ADa\u000f\u0003h\t5\u0004c\u0001<\u0003j%\u0019!1N1\u0003\u000fA\u0013x\u000eZ;diB!!q\u000eB<\u001d\u0011\u0011\tH!\u001e\u000f\u0007y\u0013\u0019(C\u0001c\u0013\r\t\u0019'Y\u0005\u0005\u0005#\u0012IHC\u0002\u0002d\u0005$bA! \u0003\u0002\n\r\u0005c\u0001B@95\t\u0011\u0004C\u0003tC\u0001\u0007Q\u000fC\u0003}C\u0001\u0007a\u0010\u0006\u0004\u0003~\t\u001d%\u0011\u0012\u0005\bg\n\u0002\n\u00111\u0001v\u0011\u001da(\u0005%AA\u0002y\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003\u0010*\u001aQO!%,\u0005\tM\u0005\u0003\u0002BK\u0005;k!Aa&\u000b\t\te%1T\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\\1\n\t\t}%q\u0013\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0005KS3A BI\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!1\u0016\t\u0005\u0003k\u0011i+C\u0002g\u0003o\tA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002R\nU\u0006\u0002\u0003B\\O\u0005\u0005\t\u0019A;\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011i\f\u0005\u0004\u0003@\n\u0015\u0017\u0011[\u0007\u0003\u0005\u0003T1Aa1b\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005\u000f\u0014\tM\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002Bg\u0005'\u00042A\u001eBh\u0013\r\u0011\t.\u0019\u0002\b\u0005>|G.Z1o\u0011%\u00119,KA\u0001\u0002\u0004\t\t.\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002BV\u00053D\u0001Ba.+\u0003\u0003\u0005\r!^\u0001\tQ\u0006\u001c\bnQ8eKR\tQ\u000f\u0006\u0002\u0003,\u00061Q-];bYN$BA!4\u0003f\"I!qW\u0017\u0002\u0002\u0003\u0007\u0011\u0011[\u0001\u0005\t\u0006$\u0018\rE\u0002\u0003\u0000=\u001aRa\fBw\u0005\u000f\u0002\u0002Ba<\u0003vVt(QP\u0007\u0003\u0005cT1Aa=b\u0003\u001d\u0011XO\u001c;j[\u0016LAAa>\u0003r\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0015\u0005\t%\u0018!B1qa2LHC\u0002B?\u0005\u007f\u001c\t\u0001C\u0003te\u0001\u0007Q\u000fC\u0003}e\u0001\u0007a0A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\r\u001d11\u0003\t\u0006m\u000e%1QB\u0005\u0004\u0007\u0017\t'AB(qi&|g\u000eE\u0003w\u0007\u001f)h0C\u0002\u0004\u0012\u0005\u0014a\u0001V;qY\u0016\u0014\u0004\"CB\u000bg\u0005\u0005\t\u0019\u0001B?\u0003\rAH\u0005M\u0001\tg\u00064X-S7qYR!11DB\u0011!\r18QD\u0005\u0004\u0007?\t'\u0001B+oSRDaaa\t5\u0001\u0004Y\u0016\u0001\u00029bi\"\u0014\u0001DV3di>\u0014\u0018J\u001c3fq\u0016\u0014Xj\u001c3fYJ+\u0017\rZ3s'\r)4\u0011\u0006\t\u0005)\u000e-b*C\u0002\u0004.U\u0013\u0001\"\u0014'SK\u0006$WM\u001d\u000b\u0003\u0007c\u00012Aa\u00186\u0003%\u0019G.Y:t\u001d\u0006lW-\u0001\u0006dY\u0006\u001c8OT1nK\u0002\nA\u0001\\8bIR\u0019aja\u000f\t\r\r\r\u0012\b1\u0001\\\u0003\u0011\u0011X-\u00193\u0016\u0005\r%\u0002\u0006\u0002\u001ej\u0005K!2ATB#\u0011\u0019\u0019\u0019c\u000fa\u00017\"\"1(\u001bB\u0013\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019i\u0005\u0005\u0003\u00026\r=\u0013\u0002BB)\u0003o\u0011aa\u00142kK\u000e$\b\u0006B\fj\u0005KACAF5\u0003&\u0001"
)
public class VectorIndexerModel extends Model implements VectorIndexerParams, MLWritable {
   private Function1 transformFunc;
   private final String uid;
   private final int numFeatures;
   private final Map categoryMaps;
   private final Attribute[] partialFeatureAttributes;
   private Param handleInvalid;
   private IntParam maxCategories;
   private Param outputCol;
   private Param inputCol;
   private volatile boolean bitmap$0;

   public static VectorIndexerModel load(final String path) {
      return VectorIndexerModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return VectorIndexerModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getMaxCategories() {
      return VectorIndexerParams.getMaxCategories$(this);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public IntParam maxCategories() {
      return this.maxCategories;
   }

   public void org$apache$spark$ml$feature$VectorIndexerParams$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public void org$apache$spark$ml$feature$VectorIndexerParams$_setter_$maxCategories_$eq(final IntParam x$1) {
      this.maxCategories = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public Map categoryMaps() {
      return this.categoryMaps;
   }

   public java.util.Map javaCategoryMaps() {
      return .MODULE$.MapHasAsJava((scala.collection.Map)this.categoryMaps().map((x0$1) -> {
         if (x0$1 != null) {
            int k = x0$1._1$mcI$sp();
            Map v = (Map)x0$1._2();
            return new Tuple2(BoxesRunTime.boxToInteger(k), .MODULE$.MapHasAsJava(v).asJava());
         } else {
            throw new MatchError(x0$1);
         }
      })).asJava();
   }

   public Dataset categoryMapsDF() {
      ArraySeq data = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.categoryMaps().iterator().flatMap((x0$1) -> {
         if (x0$1 != null) {
            int idx = x0$1._1$mcI$sp();
            Map map = (Map)x0$1._2();
            return map.iterator().map((t) -> new Tuple3(BoxesRunTime.boxToInteger(idx), BoxesRunTime.boxToDouble(t._1$mcD$sp()), BoxesRunTime.boxToInteger(t._2$mcI$sp())));
         } else {
            throw new MatchError(x0$1);
         }
      }).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple3.class))).toImmutableArraySeq();
      SparkSession var10000 = org.apache.spark.sql.SparkSession..MODULE$.builder().getOrCreate();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorIndexerModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple3"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))));
         }

         public $typecreator1$1() {
         }
      }

      return var10000.createDataFrame(data, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"featureIndex", "originalValue", "categoryIndex"})));
   }

   private Attribute[] partialFeatureAttributes() {
      return this.partialFeatureAttributes;
   }

   private Function1 transformFunc$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            int[] sortedCatFeatureIndices = (int[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.intArrayOps((int[])this.categoryMaps().keys().toArray(scala.reflect.ClassTag..MODULE$.Int())), scala.math.Ordering.Int..MODULE$);
            Map localVectorMap = this.categoryMaps();
            int localNumFeatures = this.numFeatures();
            String localHandleInvalid = this.getHandleInvalid();
            Function1 f = (v) -> {
               scala.Predef..MODULE$.assert(v.size() == localNumFeatures, () -> {
                  int var10000 = this.numFeatures();
                  return "VectorIndexerModel expected vector of length " + var10000 + " but found length " + v.size();
               });
               if (v instanceof DenseVector var9) {
                  BooleanRef hasInvalid = BooleanRef.create(false);
                  DenseVector tmpv = var9.copy();
                  localVectorMap.foreach((x0$1) -> {
                     $anonfun$transformFunc$3(tmpv, localHandleInvalid, hasInvalid, x0$1);
                     return BoxedUnit.UNIT;
                  });
                  return hasInvalid.elem ? null : tmpv;
               } else if (!(v instanceof SparseVector var12)) {
                  throw new MatchError(v);
               } else {
                  boolean hasInvalid = false;
                  SparseVector tmpv = var12.copy();
                  int catFeatureIdx = 0;
                  int k = 0;

                  while(catFeatureIdx < sortedCatFeatureIndices.length && k < tmpv.indices().length) {
                     int featureIndex = sortedCatFeatureIndices[catFeatureIdx];
                     if (featureIndex < tmpv.indices()[k]) {
                        ++catFeatureIdx;
                     } else if (featureIndex > tmpv.indices()[k]) {
                        ++k;
                     } else {
                        try {
                           tmpv.values()[k] = (double)BoxesRunTime.unboxToInt(((MapOps)localVectorMap.apply(BoxesRunTime.boxToInteger(featureIndex))).apply(BoxesRunTime.boxToDouble(tmpv.values()[k])));
                        } catch (NoSuchElementException var22) {
                           label88: {
                              label99: {
                                 String var10000 = VectorIndexer$.MODULE$.ERROR_INVALID();
                                 if (var10000 == null) {
                                    if (localHandleInvalid == null) {
                                       break label99;
                                    }
                                 } else if (var10000.equals(localHandleInvalid)) {
                                    break label99;
                                 }

                                 label100: {
                                    var10000 = VectorIndexer$.MODULE$.KEEP_INVALID();
                                    if (var10000 == null) {
                                       if (localHandleInvalid == null) {
                                          break label100;
                                       }
                                    } else if (var10000.equals(localHandleInvalid)) {
                                       break label100;
                                    }

                                    label73: {
                                       var10000 = VectorIndexer$.MODULE$.SKIP_INVALID();
                                       if (var10000 == null) {
                                          if (localHandleInvalid == null) {
                                             break label73;
                                          }
                                       } else if (var10000.equals(localHandleInvalid)) {
                                          break label73;
                                       }

                                       throw new MatchError(localHandleInvalid);
                                    }

                                    hasInvalid = true;
                                    BoxedUnit var25 = BoxedUnit.UNIT;
                                    break label88;
                                 }

                                 tmpv.values()[k] = (double)((IterableOnceOps)localVectorMap.apply(BoxesRunTime.boxToInteger(featureIndex))).size();
                                 BoxedUnit var26 = BoxedUnit.UNIT;
                                 break label88;
                              }

                              double var10002 = tmpv.values()[k];
                              throw new SparkException("VectorIndexer encountered invalid value " + var10002 + " on feature index " + featureIndex + ". To handle or skip invalid value, try setting VectorIndexer.handleInvalid.");
                           }
                        }

                        ++catFeatureIdx;
                        ++k;
                     }
                  }

                  return hasInvalid ? null : tmpv;
               }
            };
            this.transformFunc = f;
            this.bitmap$0 = true;
         }
      } catch (Throwable var8) {
         throw var8;
      }

      return this.transformFunc;
   }

   private Function1 transformFunc() {
      return !this.bitmap$0 ? this.transformFunc$lzycompute() : this.transformFunc;
   }

   public VectorIndexerModel setInputCol(final String value) {
      return (VectorIndexerModel)this.set(this.inputCol(), value);
   }

   public VectorIndexerModel setOutputCol(final String value) {
      return (VectorIndexerModel)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      StructField newField = this.prepOutputField(dataset.schema());
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (vector) -> (Vector)this.transformFunc().apply(vector);
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorIndexerModel.class.getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$2() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorIndexerModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      Column newCol = transformUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{dataset.apply((String)this.$(this.inputCol()))})));
      Dataset ds = dataset.withColumn((String)this.$(this.outputCol()), newCol, newField.metadata());
      String var11 = this.getHandleInvalid();
      String var10 = VectorIndexer$.MODULE$.SKIP_INVALID();
      if (var11 == null) {
         if (var10 == null) {
            return ds.na().drop((String[])((Object[])(new String[]{(String)this.$(this.outputCol())})));
         }
      } else if (var11.equals(var10)) {
         return ds.na().drop((String[])((Object[])(new String[]{(String)this.$(this.outputCol())})));
      }

      return ds;
   }

   public StructType transformSchema(final StructType schema) {
      VectorUDT dataType = new VectorUDT();
      scala.Predef..MODULE$.require(this.isDefined(this.inputCol()), () -> "VectorIndexerModel requires input column parameter: " + this.inputCol());
      scala.Predef..MODULE$.require(this.isDefined(this.outputCol()), () -> "VectorIndexerModel requires output column parameter: " + this.outputCol());
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), dataType, SchemaUtils$.MODULE$.checkColumnType$default$4());
      AttributeGroup origAttrGroup = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(schema, (String)this.$(this.inputCol())));
      Option origNumFeatures = (Option)(origAttrGroup.attributes().nonEmpty() ? new Some(BoxesRunTime.boxToInteger(((Attribute[])origAttrGroup.attributes().get()).length)) : origAttrGroup.numAttributes());
      scala.Predef..MODULE$.require(origNumFeatures.forall((JFunction1.mcZI.sp)(x$6) -> x$6 == this.numFeatures()), () -> {
         int var10000 = this.numFeatures();
         return "VectorIndexerModel expected " + var10000 + " features, but input column " + this.$(this.inputCol()) + " had metadata specifying " + origAttrGroup.numAttributes().get() + " features.";
      });
      StructField newField = this.prepOutputField(schema);
      StructField[] outputFields = (StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), newField, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
      return new StructType(outputFields);
   }

   private StructField prepOutputField(final StructType schema) {
      AttributeGroup origAttrGroup = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(schema, (String)this.$(this.inputCol())));
      Attribute[] var10000;
      if (origAttrGroup.attributes().nonEmpty()) {
         Attribute[] origAttrs = (Attribute[])origAttrGroup.attributes().get();
         var10000 = (Attribute[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(origAttrs), scala.Predef..MODULE$.wrapRefArray(this.partialFeatureAttributes()))), (x0$1) -> {
            if (x0$1 != null) {
               Attribute origAttr = (Attribute)x0$1._1();
               Attribute featAttr = (Attribute)x0$1._2();
               if (origAttr != null && featAttr instanceof BinaryAttribute) {
                  BinaryAttribute var6 = (BinaryAttribute)featAttr;
                  if (origAttr.name().nonEmpty()) {
                     return var6.withName((String)origAttr.name().get());
                  }

                  return var6;
               }
            }

            if (x0$1 != null) {
               Attribute origAttr = (Attribute)x0$1._1();
               Attribute featAttr = (Attribute)x0$1._2();
               if (origAttr != null && featAttr instanceof NominalAttribute) {
                  NominalAttribute var10 = (NominalAttribute)featAttr;
                  if (origAttr.name().nonEmpty()) {
                     return var10.withName((String)origAttr.name().get());
                  }

                  return var10;
               }
            }

            if (x0$1 != null) {
               Attribute origAttr = (Attribute)x0$1._1();
               Attribute featAttr = (Attribute)x0$1._2();
               if (origAttr != null && featAttr instanceof NumericAttribute) {
                  NumericAttribute var14 = (NumericAttribute)featAttr;
                  return origAttr.withIndex(BoxesRunTime.unboxToInt(var14.index().get()));
               }
            }

            if (x0$1 != null) {
               Attribute origAttr = (Attribute)x0$1._1();
               if (origAttr != null) {
                  return origAttr;
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Attribute.class));
      } else {
         var10000 = this.partialFeatureAttributes();
      }

      Attribute[] featureAttributes = var10000;
      AttributeGroup newAttributeGroup = new AttributeGroup((String)this.$(this.outputCol()), featureAttributes);
      return newAttributeGroup.toStructField();
   }

   public VectorIndexerModel copy(final ParamMap extra) {
      VectorIndexerModel copied = new VectorIndexerModel(this.uid(), this.numFeatures(), this.categoryMaps());
      return (VectorIndexerModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new VectorIndexerModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "VectorIndexerModel: uid=" + var10000 + ", numFeatures=" + this.numFeatures() + ", handleInvalid=" + this.$(this.handleInvalid());
   }

   // $FF: synthetic method
   public static final double $anonfun$partialFeatureAttributes$1(final Tuple2 x$3) {
      return x$3._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$partialFeatureAttributes$2(final Tuple2 x$4) {
      return x$4._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final String $anonfun$partialFeatureAttributes$3(final double x$5) {
      return Double.toString(x$5);
   }

   // $FF: synthetic method
   public static final void $anonfun$transformFunc$3(final DenseVector tmpv$1, final String localHandleInvalid$1, final BooleanRef hasInvalid$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int featureIndex = x0$1._1$mcI$sp();
         Map categoryMap = (Map)x0$1._2();
         if (true) {
            int var9 = featureIndex;
            if (categoryMap != null) {
               Map var10 = categoryMap;

               try {
                  tmpv$1.values()[var9] = (double)BoxesRunTime.unboxToInt(var10.apply(BoxesRunTime.boxToDouble(tmpv$1.apply(var9))));
                  BoxedUnit var21 = BoxedUnit.UNIT;
               } catch (NoSuchElementException var15) {
                  label77: {
                     String var10000 = VectorIndexer$.MODULE$.ERROR_INVALID();
                     if (var10000 == null) {
                        if (localHandleInvalid$1 == null) {
                           break label77;
                        }
                     } else if (var10000.equals(localHandleInvalid$1)) {
                        break label77;
                     }

                     label50: {
                        label69: {
                           var10000 = VectorIndexer$.MODULE$.KEEP_INVALID();
                           if (var10000 == null) {
                              if (localHandleInvalid$1 == null) {
                                 break label69;
                              }
                           } else if (var10000.equals(localHandleInvalid$1)) {
                              break label69;
                           }

                           var10000 = VectorIndexer$.MODULE$.SKIP_INVALID();
                           if (var10000 == null) {
                              if (localHandleInvalid$1 != null) {
                                 throw new MatchError(localHandleInvalid$1);
                              }
                           } else if (!var10000.equals(localHandleInvalid$1)) {
                              throw new MatchError(localHandleInvalid$1);
                           }

                           hasInvalid$1.elem = true;
                           BoxedUnit var18 = BoxedUnit.UNIT;
                           break label50;
                        }

                        tmpv$1.values()[featureIndex] = (double)categoryMap.size();
                        BoxedUnit var19 = BoxedUnit.UNIT;
                     }

                     BoxedUnit var20 = BoxedUnit.UNIT;
                     return;
                  }

                  double var10002 = tmpv$1.apply(featureIndex);
                  throw new SparkException("VectorIndexer encountered invalid value " + var10002 + " on feature index " + featureIndex + ". To handle or skip invalid value, try setting VectorIndexer.handleInvalid.");
               }

               return;
            }
         }
      }

      throw new MatchError(x0$1);
   }

   public VectorIndexerModel(final String uid, final int numFeatures, final Map categoryMaps) {
      this.uid = uid;
      this.numFeatures = numFeatures;
      this.categoryMaps = categoryMaps;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasHandleInvalid.$init$(this);
      VectorIndexerParams.$init$(this);
      MLWritable.$init$(this);
      Attribute[] attrs = new Attribute[numFeatures];
      int categoricalFeatureCount = 0;

      for(int featureIndex = 0; featureIndex < numFeatures; ++featureIndex) {
         if (categoryMaps.contains(BoxesRunTime.boxToInteger(featureIndex))) {
            String[] var25;
            label46: {
               String[] rawFeatureValues;
               label45: {
                  rawFeatureValues = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(((IterableOnceOps)categoryMaps.apply(BoxesRunTime.boxToInteger(featureIndex))).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), (x$3x) -> BoxesRunTime.boxToDouble($anonfun$partialFeatureAttributes$1(x$3x)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)), (x$4x) -> BoxesRunTime.boxToDouble($anonfun$partialFeatureAttributes$2(x$4x)), scala.reflect.ClassTag..MODULE$.Double())), (x$5x) -> $anonfun$partialFeatureAttributes$3(BoxesRunTime.unboxToDouble(x$5x)), scala.reflect.ClassTag..MODULE$.apply(String.class));
                  var25 = this.getHandleInvalid();
                  String var9 = VectorIndexer$.MODULE$.KEEP_INVALID();
                  if (var25 == null) {
                     if (var9 == null) {
                        break label45;
                     }
                  } else if (var25.equals(var9)) {
                     break label45;
                  }

                  var25 = rawFeatureValues;
                  break label46;
               }

               var25 = (String[])((IterableOnceOps)scala.Predef..MODULE$.wrapRefArray((Object[])rawFeatureValues).toList().$colon$plus("__unknown")).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
            }

            label38: {
               String[] featureValues;
               label37: {
                  featureValues = var25;
                  if (featureValues.length == 2) {
                     var25 = this.getHandleInvalid();
                     String var10 = VectorIndexer$.MODULE$.KEEP_INVALID();
                     if (var25 == null) {
                        if (var10 != null) {
                           break label37;
                        }
                     } else if (!var25.equals(var10)) {
                        break label37;
                     }
                  }

                  Some x$4 = new Some(BoxesRunTime.boxToInteger(featureIndex));
                  Some x$5 = new Some(BoxesRunTime.boxToBoolean(false));
                  Some x$6 = new Some(featureValues);
                  Option x$7 = NominalAttribute$.MODULE$.$lessinit$greater$default$1();
                  Option x$8 = NominalAttribute$.MODULE$.$lessinit$greater$default$4();
                  attrs[featureIndex] = new NominalAttribute(x$7, x$4, x$5, x$8, x$6);
                  break label38;
               }

               Some x$1 = new Some(BoxesRunTime.boxToInteger(featureIndex));
               Some x$2 = new Some(featureValues);
               Option x$3 = BinaryAttribute$.MODULE$.$lessinit$greater$default$1();
               attrs[featureIndex] = new BinaryAttribute(x$3, x$1, x$2);
            }

            ++categoricalFeatureCount;
         } else {
            Some x$9 = new Some(BoxesRunTime.boxToInteger(featureIndex));
            Option x$10 = NumericAttribute$.MODULE$.$lessinit$greater$default$1();
            Option x$11 = NumericAttribute$.MODULE$.$lessinit$greater$default$3();
            Option x$12 = NumericAttribute$.MODULE$.$lessinit$greater$default$4();
            Option x$13 = NumericAttribute$.MODULE$.$lessinit$greater$default$5();
            Option x$14 = NumericAttribute$.MODULE$.$lessinit$greater$default$6();
            attrs[featureIndex] = new NumericAttribute(x$10, x$9, x$11, x$12, x$13, x$14);
         }
      }

      scala.Predef..MODULE$.require(categoricalFeatureCount == categoryMaps.size(), () -> "VectorIndexerModel given categoryMaps with keys outside expected range [0,...,numFeatures), where numFeatures=" + this.numFeatures());
      this.partialFeatureAttributes = attrs;
      Statics.releaseFence();
   }

   public VectorIndexerModel() {
      this("", -1, scala.Predef..MODULE$.Map().empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class VectorIndexerModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final VectorIndexerModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.numFeatures(), this.instance.categoryMaps());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorIndexerModelWriter.class.getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.VectorIndexerModel.VectorIndexerModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.VectorIndexerModel.VectorIndexerModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$3() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public VectorIndexerModelWriter(final VectorIndexerModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final int numFeatures;
         private final Map categoryMaps;
         // $FF: synthetic field
         public final VectorIndexerModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public int numFeatures() {
            return this.numFeatures;
         }

         public Map categoryMaps() {
            return this.categoryMaps;
         }

         public Data copy(final int numFeatures, final Map categoryMaps) {
            return this.org$apache$spark$ml$feature$VectorIndexerModel$VectorIndexerModelWriter$Data$$$outer().new Data(numFeatures, categoryMaps);
         }

         public int copy$default$1() {
            return this.numFeatures();
         }

         public Map copy$default$2() {
            return this.categoryMaps();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 2;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return BoxesRunTime.boxToInteger(this.numFeatures());
               }
               case 1 -> {
                  return this.categoryMaps();
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
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "numFeatures";
               }
               case 1 -> {
                  return "categoryMaps";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, this.numFeatures());
            var1 = Statics.mix(var1, Statics.anyHash(this.categoryMaps()));
            return Statics.finalizeHash(var1, 2);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label56: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$VectorIndexerModel$VectorIndexerModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$VectorIndexerModel$VectorIndexerModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.numFeatures() == var4.numFeatures()) {
                        label46: {
                           Map var10000 = this.categoryMaps();
                           Map var5 = var4.categoryMaps();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label46;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label46;
                           }

                           if (var4.canEqual(this)) {
                              break label56;
                           }
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

         // $FF: synthetic method
         public VectorIndexerModelWriter org$apache$spark$ml$feature$VectorIndexerModel$VectorIndexerModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final int numFeatures, final Map categoryMaps) {
            this.numFeatures = numFeatures;
            this.categoryMaps = categoryMaps;
            if (VectorIndexerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = VectorIndexerModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final VectorIndexerModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final int numFeatures, final Map categoryMaps) {
            return this.$outer.new Data(numFeatures, categoryMaps);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.numFeatures()), x$0.categoryMaps())));
         }

         public Data$() {
            if (VectorIndexerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = VectorIndexerModelWriter.this;
               super();
            }
         }
      }
   }

   private static class VectorIndexerModelReader extends MLReader {
      private final String className = VectorIndexerModel.class.getName();

      private String className() {
         return this.className;
      }

      public VectorIndexerModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("numFeatures", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"categoryMaps"}))).head();
         int numFeatures = BoxesRunTime.unboxToInt(data.getAs(0));
         Map categoryMaps = (Map)data.getAs(1);
         VectorIndexerModel model = new VectorIndexerModel(metadata.uid(), numFeatures, categoryMaps);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public VectorIndexerModelReader() {
      }
   }
}
