package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDDLike;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.Function3;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.dstream.DStream;
import scala.Function1;
import scala.Tuple2;
import scala.collection.Seq;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r-caB\u0013'!\u0003\r\ta\r\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\b\u0019\u0002\u0011\rQb\u0001N\u0011\u0015y\u0006A\"\u0001a\u0011\u00151\u0007A\"\u0001h\u0011\u0015Q\b\u0001b\u0001|\u0011\u0019\t9\u0002\u0001C\u0001\u0011\"9\u0011q\u0003\u0001\u0005\u0002\u0005e\u0001bBA\u0013\u0001\u0011\u0005\u0011q\u0005\u0005\b\u0003S\u0001A\u0011AA\u0016\u0011\u001d\tI\u0003\u0001C\u0001\u0003gAq!!\u000f\u0001\t\u0003\tY\u0004C\u0004\u0002N\u0001!\t!a\u0014\t\u000f\u00055\u0003\u0001\"\u0001\u0002V!9\u0011Q\f\u0001\u0005\u0002\u0005}\u0003bBA8\u0001\u0011\u0005\u0011\u0011\u000f\u0005\b\u0003s\u0002A\u0011AA>\u0011\u001d\t9\n\u0001C\u0001\u00033Cq!a-\u0001\t\u0003\t)\fC\u0004\u0002H\u0002!\t!!3\t\u000f\u0005}\u0007\u0001\"\u0001\u0002b\"9\u0011Q\u001f\u0001\u0005\u0002\u0005]\bb\u0002B\u0005\u0001\u0011\u0005!1\u0002\u0005\b\u0005/\u0001A\u0011\u0001B\r\u0011\u001d\u00119\u0002\u0001C\u0001\u0005GAqAa\f\u0001\t\u0003\u0011\t\u0004C\u0004\u0003D\u0001!\tA!\u0012\t\u000f\t\r\u0003\u0001\"\u0001\u0003R!9!1\f\u0001\u0005\u0002\tu\u0003b\u0002B.\u0001\u0011\u0005!1\u000f\u0005\b\u0005\u0007\u0003A\u0011\u0001BC\u0011\u001d\u0011\u0019\t\u0001C\u0001\u0005;CqA!-\u0001\t\u0003\u0011\u0019\fC\u0004\u0003V\u0002!\tAa6\t\u000f\tE\u0006\u0001\"\u0001\u0003v\"9!Q\u001b\u0001\u0005\u0002\rM\u0001bBB\u001d\u0001\u0011\u000511\b\u0002\u0010\u0015\u00064\u0018\rR*ue\u0016\fW\u000eT5lK*\u0011q\u0005K\u0001\u0005U\u00064\u0018M\u0003\u0002*U\u0005\u0019\u0011\r]5\u000b\u0005-b\u0013!C:ue\u0016\fW.\u001b8h\u0015\tic&A\u0003ta\u0006\u00148N\u0003\u00020a\u00051\u0011\r]1dQ\u0016T\u0011!M\u0001\u0004_J<7\u0001A\u000b\u0006iY\u001b\t%[\n\u0004\u0001UZ\u0004C\u0001\u001c:\u001b\u00059$\"\u0001\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005i:$AB!osJ+g\r\u0005\u0002=\t:\u0011QH\u0011\b\u0003}\u0005k\u0011a\u0010\u0006\u0003\u0001J\na\u0001\u0010:p_Rt\u0014\"\u0001\u001d\n\u0005\r;\u0014a\u00029bG.\fw-Z\u0005\u0003\u000b\u001a\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!aQ\u001c\u0002\r\u0011Jg.\u001b;%)\u0005I\u0005C\u0001\u001cK\u0013\tYuG\u0001\u0003V]&$\u0018\u0001C2mCN\u001cH+Y4\u0016\u00039\u00032a\u0014*U\u001b\u0005\u0001&BA)8\u0003\u001d\u0011XM\u001a7fGRL!a\u0015)\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"!\u0016,\r\u0001\u0011)q\u000b\u0001b\u00011\n\tA+\u0005\u0002Z9B\u0011aGW\u0005\u00037^\u0012qAT8uQ&tw\r\u0005\u00027;&\u0011al\u000e\u0002\u0004\u0003:L\u0018a\u00023tiJ,\u0017-\\\u000b\u0002CB\u0019!\r\u001a+\u000e\u0003\rT!a\u0018\u0016\n\u0005\u0015\u001c'a\u0002#TiJ,\u0017-\\\u0001\boJ\f\u0007O\u0015#E)\tA'\u000f\u0005\u0002VS\u0012)!\u000e\u0001b\u0001W\n\t!+\u0005\u0002ZYB!Q\u000e\u001d+i\u001b\u0005q'BA\u0014p\u0015\tIC&\u0003\u0002r]\nY!*\u0019<b%\u0012#E*[6f\u0011\u0015\u0019H\u00011\u0001u\u0003\tIg\u000eE\u0002vqRk\u0011A\u001e\u0006\u0003o2\n1A\u001d3e\u0013\tIhOA\u0002S\t\u0012\u000b!c]2bY\u0006Le\u000e\u001e+p\u0015\u00064\u0018\rT8oOR\u0019A0a\u0004\u0011\tut\u0018\u0011A\u0007\u0002M%\u0011qP\n\u0002\f\u0015\u00064\u0018\rR*ue\u0016\fW\u000e\u0005\u0003\u0002\u0004\u0005-QBAA\u0003\u0015\u0011\t9!!\u0003\u0002\t1\fgn\u001a\u0006\u0002O%!\u0011QBA\u0003\u0005\u0011auN\\4\t\rM,\u0001\u0019AA\t!\u0011\u0011G-a\u0005\u0011\u0007Y\n)\"C\u0002\u0002\u000e]\nQ\u0001\u001d:j]R$2!SA\u000e\u0011\u001d\tib\u0002a\u0001\u0003?\t1A\\;n!\r1\u0014\u0011E\u0005\u0004\u0003G9$aA%oi\u0006)1m\\;oiR\tA0\u0001\u0007d_VtGOQ=WC2,X\r\u0006\u0002\u0002.A1Q0a\fU\u0003\u0003I1!!\r'\u0005=Q\u0015M^1QC&\u0014Hi\u0015;sK\u0006lG\u0003BA\u0017\u0003kAq!a\u000e\u000b\u0001\u0004\ty\"A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u0001\u000eG>,h\u000e\u001e\"z/&tGm\\<\u0015\u000bq\fi$!\u0013\t\u000f\u0005}2\u00021\u0001\u0002B\u0005qq/\u001b8e_^$UO]1uS>t\u0007\u0003BA\"\u0003\u000bj\u0011AK\u0005\u0004\u0003\u000fR#\u0001\u0003#ve\u0006$\u0018n\u001c8\t\u000f\u0005-3\u00021\u0001\u0002B\u0005i1\u000f\\5eK\u0012+(/\u0019;j_:\fQcY8v]R\u0014\u0015PV1mk\u0016\fe\u000eZ,j]\u0012|w\u000f\u0006\u0004\u0002.\u0005E\u00131\u000b\u0005\b\u0003\u007fa\u0001\u0019AA!\u0011\u001d\tY\u0005\u0004a\u0001\u0003\u0003\"\u0002\"!\f\u0002X\u0005e\u00131\f\u0005\b\u0003\u007fi\u0001\u0019AA!\u0011\u001d\tY%\u0004a\u0001\u0003\u0003Bq!a\u000e\u000e\u0001\u0004\ty\"\u0001\u0003hY>lGCAA1!\u0011ih0a\u0019\u0011\u000b\u0005\u0015\u00141\u000e+\u000e\u0005\u0005\u001d$\u0002BA5\u0003\u0013\tA!\u001e;jY&!\u0011QNA4\u0005\u0011a\u0015n\u001d;\u0002\u000f\r|g\u000e^3yiR\u0011\u00111\u000f\t\u0005\u0003\u0007\n)(C\u0002\u0002x)\u0012\u0001c\u0015;sK\u0006l\u0017N\\4D_:$X\r\u001f;\u0002\u00075\f\u0007/\u0006\u0003\u0002~\u0005\rE\u0003BA@\u0003\u000f\u0003B! @\u0002\u0002B\u0019Q+a!\u0005\r\u0005\u0015\u0005C1\u0001Y\u0005\u0005)\u0006bBAE!\u0001\u0007\u00111R\u0001\u0002MB9\u0011QRAJ)\u0006\u0005UBAAH\u0015\r\t\tJ\\\u0001\tMVt7\r^5p]&!\u0011QSAH\u0005!1UO\\2uS>t\u0017!C7baR{\u0007+Y5s+\u0019\tY*!)\u0002(R!\u0011QTAV!\u001di\u0018qFAP\u0003K\u00032!VAQ\t\u0019\t\u0019+\u0005b\u00011\n\u00111J\r\t\u0004+\u0006\u001dFABAU#\t\u0007\u0001L\u0001\u0002We!9\u0011\u0011R\tA\u0002\u00055\u0006#CAG\u0003_#\u0016qTAS\u0013\u0011\t\t,a$\u0003\u0019A\u000b\u0017N\u001d$v]\u000e$\u0018n\u001c8\u0002\u000f\u0019d\u0017\r^'baV!\u0011qWA_)\u0011\tI,a0\u0011\tut\u00181\u0018\t\u0004+\u0006uFABAC%\t\u0007\u0001\fC\u0004\u0002\nJ\u0001\r!!1\u0011\u000f\u00055\u00151\u0019+\u0002<&!\u0011QYAH\u0005=1E.\u0019;NCB4UO\\2uS>t\u0017!\u00044mCRl\u0015\r\u001d+p!\u0006L'/\u0006\u0004\u0002L\u0006E\u0017Q\u001b\u000b\u0005\u0003\u001b\f9\u000eE\u0004~\u0003_\ty-a5\u0011\u0007U\u000b\t\u000e\u0002\u0004\u0002$N\u0011\r\u0001\u0017\t\u0004+\u0006UGABAU'\t\u0007\u0001\fC\u0004\u0002\nN\u0001\r!!7\u0011\u0013\u00055\u00151\u001c+\u0002P\u0006M\u0017\u0002BAo\u0003\u001f\u00131\u0003U1je\u001ac\u0017\r^'ba\u001a+hn\u0019;j_:\fQ\"\\1q!\u0006\u0014H/\u001b;j_:\u001cX\u0003BAr\u0003S$B!!:\u0002lB!QP`At!\r)\u0016\u0011\u001e\u0003\u0007\u0003\u000b#\"\u0019\u0001-\t\u000f\u0005%E\u00031\u0001\u0002nBA\u0011QRAb\u0003_\f9\u000fE\u0003\u0002f\u0005EH+\u0003\u0003\u0002t\u0006\u001d$\u0001C%uKJ\fGo\u001c:\u0002'5\f\u0007\u000fU1si&$\u0018n\u001c8t)>\u0004\u0016-\u001b:\u0016\r\u0005e\u0018q B\u0002)\u0011\tYP!\u0002\u0011\u000fu\fy#!@\u0003\u0002A\u0019Q+a@\u0005\r\u0005\rVC1\u0001Y!\r)&1\u0001\u0003\u0007\u0003S+\"\u0019\u0001-\t\u000f\u0005%U\u00031\u0001\u0003\bAQ\u0011QRAn\u0003_\fiP!\u0001\u0002\rI,G-^2f)\u0011\u0011iAa\u0004\u0011\u0007utH\u000bC\u0004\u0002\nZ\u0001\rA!\u0005\u0011\u000f\u00055%1\u0003+U)&!!QCAH\u0005%1UO\\2uS>t''\u0001\bsK\u0012,8-\u001a\"z/&tGm\\<\u0015\u0011\t5!1\u0004B\u0010\u0005CAqA!\b\u0018\u0001\u0004\u0011\t\"\u0001\u0006sK\u0012,8-\u001a$v]\u000eDq!a\u0010\u0018\u0001\u0004\t\t\u0005C\u0004\u0002L]\u0001\r!!\u0011\u0015\u0015\t5!Q\u0005B\u0014\u0005W\u0011i\u0003C\u0004\u0003\u001ea\u0001\rA!\u0005\t\u000f\t%\u0002\u00041\u0001\u0003\u0012\u0005i\u0011N\u001c<SK\u0012,8-\u001a$v]\u000eDq!a\u0010\u0019\u0001\u0004\t\t\u0005C\u0004\u0002La\u0001\r!!\u0011\u0002\u000bMd\u0017nY3\u0015\r\tM\"Q\u0007B !\u0015\t)'a\u001bi\u0011\u001d\u00119$\u0007a\u0001\u0005s\t\u0001B\u001a:p[RKW.\u001a\t\u0005\u0003\u0007\u0012Y$C\u0002\u0003>)\u0012A\u0001V5nK\"9!\u0011I\rA\u0002\te\u0012A\u0002;p)&lW-\u0001\u0006g_J,\u0017m\u00195S\t\u0012#2!\u0013B$\u0011\u001d\u0011IE\u0007a\u0001\u0005\u0017\n1BZ8sK\u0006\u001c\u0007NR;oGB)\u0011Q\u0012B'Q&!!qJAH\u000511v.\u001b3Gk:\u001cG/[8o)\rI%1\u000b\u0005\b\u0005\u0013Z\u0002\u0019\u0001B+!\u001d\tiIa\u0016i\u0005sIAA!\u0017\u0002\u0010\niak\\5e\rVt7\r^5p]J\n\u0011\u0002\u001e:b]N4wN]7\u0016\t\t}#Q\r\u000b\u0005\u0005C\u00129\u0007\u0005\u0003~}\n\r\u0004cA+\u0003f\u00111\u0011Q\u0011\u000fC\u0002aCqA!\u001b\u001d\u0001\u0004\u0011Y'A\u0007ue\u0006t7OZ8s[\u001a+hn\u0019\t\b\u0003\u001b\u000b\u0019\n\u001bB7!\u0015i'q\u000eB2\u0013\r\u0011\tH\u001c\u0002\b\u0015\u00064\u0018M\u0015#E+\u0011\u0011)Ha\u001f\u0015\t\t]$Q\u0010\t\u0005{z\u0014I\bE\u0002V\u0005w\"a!!\"\u001e\u0005\u0004A\u0006b\u0002B5;\u0001\u0007!q\u0010\t\n\u0003\u001b\u0013\u0019\u0002\u001bB\u001d\u0005\u0003\u0003R!\u001cB8\u0005s\nq\u0002\u001e:b]N4wN]7U_B\u000b\u0017N]\u000b\u0007\u0005\u000f\u0013iI!%\u0015\t\t%%1\u0013\t\b{\u0006=\"1\u0012BH!\r)&Q\u0012\u0003\u0007\u0003Gs\"\u0019\u0001-\u0011\u0007U\u0013\t\n\u0002\u0004\u0002*z\u0011\r\u0001\u0017\u0005\b\u0005Sr\u0002\u0019\u0001BK!\u001d\ti)a%i\u0005/\u0003r!\u001cBM\u0005\u0017\u0013y)C\u0002\u0003\u001c:\u00141BS1wCB\u000b\u0017N\u001d*E\tV1!q\u0014BS\u0005S#BA!)\u0003,B9Q0a\f\u0003$\n\u001d\u0006cA+\u0003&\u00121\u00111U\u0010C\u0002a\u00032!\u0016BU\t\u0019\tIk\bb\u00011\"9!\u0011N\u0010A\u0002\t5\u0006#CAG\u0005'A'\u0011\bBX!\u001di'\u0011\u0014BR\u0005O\u000bQ\u0002\u001e:b]N4wN]7XSRDWC\u0002B[\u0005\u000f\u0014Y\f\u0006\u0004\u00038\n}&\u0011\u001a\t\u0005{z\u0014I\fE\u0002V\u0005w#aA!0!\u0005\u0004A&!A,\t\u000f\t\u0005\u0007\u00051\u0001\u0003D\u0006)q\u000e\u001e5feB!QP Bc!\r)&q\u0019\u0003\u0007\u0003\u000b\u0003#\u0019\u0001-\t\u000f\t%\u0004\u00051\u0001\u0003LBY\u0011Q\u0012BgQ\nE'\u0011\bBj\u0013\u0011\u0011y-a$\u0003\u0013\u0019+hn\u0019;j_:\u001c\u0004#B7\u0003p\t\u0015\u0007#B7\u0003p\te\u0016a\u0005;sC:\u001chm\u001c:n/&$\b\u000eV8QC&\u0014X\u0003\u0003Bm\u0005W\u0014yNa9\u0015\r\tm'Q\u001dBw!\u001di\u0018q\u0006Bo\u0005C\u00042!\u0016Bp\t\u0019\t\u0019+\tb\u00011B\u0019QKa9\u0005\r\u0005%\u0016E1\u0001Y\u0011\u001d\u0011\t-\ta\u0001\u0005O\u0004B! @\u0003jB\u0019QKa;\u0005\r\u0005\u0015\u0015E1\u0001Y\u0011\u001d\u0011I'\ta\u0001\u0005_\u00042\"!$\u0003N\"\u0014\tP!\u000f\u0003tB)QNa\u001c\u0003jB9QN!'\u0003^\n\u0005X\u0003\u0003B|\u0007\u000b\u0019IA!@\u0015\r\te(q`B\u0006!\u0011ihPa?\u0011\u0007U\u0013i\u0010\u0002\u0004\u0003>\n\u0012\r\u0001\u0017\u0005\b\u0005\u0003\u0014\u0003\u0019AB\u0001!\u001di\u0018qFB\u0002\u0007\u000f\u00012!VB\u0003\t\u0019\t\u0019K\tb\u00011B\u0019Qk!\u0003\u0005\r\u0005%&E1\u0001Y\u0011\u001d\u0011IG\ta\u0001\u0007\u001b\u00012\"!$\u0003N\"\u001cyA!\u000f\u0004\u0012A9QN!'\u0004\u0004\r\u001d\u0001#B7\u0003p\tmXCCB\u000b\u0007W\u0019yca\u0007\u0004\"Q11qCB\u0013\u0007c\u0001r!`A\u0018\u00073\u0019y\u0002E\u0002V\u00077!aa!\b$\u0005\u0004A&AA&4!\r)6\u0011\u0005\u0003\u0007\u0007G\u0019#\u0019\u0001-\u0003\u0005Y\u001b\u0004b\u0002BaG\u0001\u00071q\u0005\t\b{\u0006=2\u0011FB\u0017!\r)61\u0006\u0003\u0007\u0003G\u001b#\u0019\u0001-\u0011\u0007U\u001by\u0003\u0002\u0004\u0002*\u000e\u0012\r\u0001\u0017\u0005\b\u0005S\u001a\u0003\u0019AB\u001a!-\tiI!4i\u0007k\u0011Ida\u000e\u0011\u000f5\u0014Ij!\u000b\u0004.A9QN!'\u0004\u001a\r}\u0011AC2iK\u000e\\\u0007o\\5oiR\u0019\u0011m!\u0010\t\u000f\r}B\u00051\u0001\u0002B\u0005A\u0011N\u001c;feZ\fG\u000eB\u0004\u0004D\u0001\u0011\ra!\u0012\u0003\tQC\u0017n]\t\u00043\u000e\u001d\u0003CB?\u0001)\u000e%\u0003\u000eE\u0002V\u0007\u0003\u0002"
)
public interface JavaDStreamLike extends Serializable {
   ClassTag classTag();

   DStream dstream();

   JavaRDDLike wrapRDD(final RDD in);

   // $FF: synthetic method
   static JavaDStream scalaIntToJavaLong$(final JavaDStreamLike $this, final DStream in) {
      return $this.scalaIntToJavaLong(in);
   }

   default JavaDStream scalaIntToJavaLong(final DStream in) {
      return JavaDStream$.MODULE$.fromDStream(in.map((x$1) -> $anonfun$scalaIntToJavaLong$1(BoxesRunTime.unboxToLong(x$1)), .MODULE$.apply(Long.class)), .MODULE$.apply(Long.class));
   }

   // $FF: synthetic method
   static void print$(final JavaDStreamLike $this) {
      $this.print();
   }

   default void print() {
      this.print(10);
   }

   // $FF: synthetic method
   static void print$(final JavaDStreamLike $this, final int num) {
      $this.print(num);
   }

   default void print(final int num) {
      this.dstream().print(num);
   }

   // $FF: synthetic method
   static JavaDStream count$(final JavaDStreamLike $this) {
      return $this.count();
   }

   default JavaDStream count() {
      return this.scalaIntToJavaLong(this.dstream().count());
   }

   // $FF: synthetic method
   static JavaPairDStream countByValue$(final JavaDStreamLike $this) {
      return $this.countByValue();
   }

   default JavaPairDStream countByValue() {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream qual$1 = this.dstream();
      int x$1 = qual$1.countByValue$default$1();
      JavaPairDStream$ var10001 = JavaPairDStream$.MODULE$;
      Ordering x$3 = qual$1.countByValue$default$2(x$1);
      return var10000.scalaToJavaLong(var10001.fromPairDStream(qual$1.countByValue(x$1, x$3), this.classTag(), .MODULE$.Long()), this.classTag());
   }

   // $FF: synthetic method
   static JavaPairDStream countByValue$(final JavaDStreamLike $this, final int numPartitions) {
      return $this.countByValue(numPartitions);
   }

   default JavaPairDStream countByValue(final int numPartitions) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      JavaPairDStream$ var10001 = JavaPairDStream$.MODULE$;
      DStream qual$1 = this.dstream();
      Ordering x$2 = qual$1.countByValue$default$2(numPartitions);
      return var10000.scalaToJavaLong(var10001.fromPairDStream(qual$1.countByValue(numPartitions, x$2), this.classTag(), .MODULE$.Long()), this.classTag());
   }

   // $FF: synthetic method
   static JavaDStream countByWindow$(final JavaDStreamLike $this, final Duration windowDuration, final Duration slideDuration) {
      return $this.countByWindow(windowDuration, slideDuration);
   }

   default JavaDStream countByWindow(final Duration windowDuration, final Duration slideDuration) {
      return this.scalaIntToJavaLong(this.dstream().countByWindow(windowDuration, slideDuration));
   }

   // $FF: synthetic method
   static JavaPairDStream countByValueAndWindow$(final JavaDStreamLike $this, final Duration windowDuration, final Duration slideDuration) {
      return $this.countByValueAndWindow(windowDuration, slideDuration);
   }

   default JavaPairDStream countByValueAndWindow(final Duration windowDuration, final Duration slideDuration) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream qual$1 = this.dstream();
      int x$3 = qual$1.countByValueAndWindow$default$3();
      JavaPairDStream$ var10001 = JavaPairDStream$.MODULE$;
      Ordering x$7 = qual$1.countByValueAndWindow$default$4(windowDuration, slideDuration, x$3);
      return var10000.scalaToJavaLong(var10001.fromPairDStream(qual$1.countByValueAndWindow(windowDuration, slideDuration, x$3, x$7), this.classTag(), .MODULE$.Long()), this.classTag());
   }

   // $FF: synthetic method
   static JavaPairDStream countByValueAndWindow$(final JavaDStreamLike $this, final Duration windowDuration, final Duration slideDuration, final int numPartitions) {
      return $this.countByValueAndWindow(windowDuration, slideDuration, numPartitions);
   }

   default JavaPairDStream countByValueAndWindow(final Duration windowDuration, final Duration slideDuration, final int numPartitions) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      JavaPairDStream$ var10001 = JavaPairDStream$.MODULE$;
      DStream qual$1 = this.dstream();
      Ordering x$4 = qual$1.countByValueAndWindow$default$4(windowDuration, slideDuration, numPartitions);
      return var10000.scalaToJavaLong(var10001.fromPairDStream(qual$1.countByValueAndWindow(windowDuration, slideDuration, numPartitions, x$4), this.classTag(), .MODULE$.Long()), this.classTag());
   }

   // $FF: synthetic method
   static JavaDStream glom$(final JavaDStreamLike $this) {
      return $this.glom();
   }

   default JavaDStream glom() {
      return new JavaDStream(this.dstream().glom().map((x$1) -> scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$1).toImmutableArraySeq()).asJava(), .MODULE$.apply(List.class)), .MODULE$.apply(List.class));
   }

   // $FF: synthetic method
   static StreamingContext context$(final JavaDStreamLike $this) {
      return $this.context();
   }

   default StreamingContext context() {
      return this.dstream().context();
   }

   // $FF: synthetic method
   static JavaDStream map$(final JavaDStreamLike $this, final Function f) {
      return $this.map(f);
   }

   default JavaDStream map(final Function f) {
      return new JavaDStream(this.dstream().map(org.apache.spark.api.java.JavaPairRDD..MODULE$.toScalaFunction(f), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag()), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaPairDStream mapToPair$(final JavaDStreamLike $this, final PairFunction f) {
      return $this.mapToPair(f);
   }

   default JavaPairDStream mapToPair(final PairFunction f) {
      return new JavaPairDStream(this.dstream().map(org.apache.spark.api.java.JavaPairRDD..MODULE$.pairFunToScalaFun(f), cm$1()), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag(), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaDStream flatMap$(final JavaDStreamLike $this, final FlatMapFunction f) {
      return $this.flatMap(f);
   }

   default JavaDStream flatMap(final FlatMapFunction f) {
      return new JavaDStream(this.dstream().flatMap(fn$1(f), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag()), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaPairDStream flatMapToPair$(final JavaDStreamLike $this, final PairFlatMapFunction f) {
      return $this.flatMapToPair(f);
   }

   default JavaPairDStream flatMapToPair(final PairFlatMapFunction f) {
      return new JavaPairDStream(this.dstream().flatMap(fn$2(f), cm$2()), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag(), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaDStream mapPartitions$(final JavaDStreamLike $this, final FlatMapFunction f) {
      return $this.mapPartitions(f);
   }

   default JavaDStream mapPartitions(final FlatMapFunction f) {
      DStream qual$1 = this.dstream();
      Function1 x$1 = fn$3(f);
      boolean x$2 = qual$1.mapPartitions$default$2();
      ClassTag x$3 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return new JavaDStream(qual$1.mapPartitions(x$1, x$2, x$3), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaPairDStream mapPartitionsToPair$(final JavaDStreamLike $this, final PairFlatMapFunction f) {
      return $this.mapPartitionsToPair(f);
   }

   default JavaPairDStream mapPartitionsToPair(final PairFlatMapFunction f) {
      DStream qual$1 = this.dstream();
      Function1 x$1 = fn$4(f);
      boolean x$2 = qual$1.mapPartitions$default$2();
      return new JavaPairDStream(qual$1.mapPartitions(x$1, x$2, .MODULE$.apply(Tuple2.class)), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag(), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaDStream reduce$(final JavaDStreamLike $this, final Function2 f) {
      return $this.reduce(f);
   }

   default JavaDStream reduce(final Function2 f) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().reduce(org.apache.spark.api.java.JavaPairRDD..MODULE$.toScalaFunction2(f)), this.classTag());
   }

   // $FF: synthetic method
   static JavaDStream reduceByWindow$(final JavaDStreamLike $this, final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return $this.reduceByWindow(reduceFunc, windowDuration, slideDuration);
   }

   default JavaDStream reduceByWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().reduceByWindow(org.apache.spark.api.java.JavaPairRDD..MODULE$.toScalaFunction2(reduceFunc), windowDuration, slideDuration), this.classTag());
   }

   // $FF: synthetic method
   static JavaDStream reduceByWindow$(final JavaDStreamLike $this, final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return $this.reduceByWindow(reduceFunc, invReduceFunc, windowDuration, slideDuration);
   }

   default JavaDStream reduceByWindow(final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return JavaDStream$.MODULE$.fromDStream(this.dstream().reduceByWindow(org.apache.spark.api.java.JavaPairRDD..MODULE$.toScalaFunction2(reduceFunc), org.apache.spark.api.java.JavaPairRDD..MODULE$.toScalaFunction2(invReduceFunc), windowDuration, slideDuration), this.classTag());
   }

   // $FF: synthetic method
   static List slice$(final JavaDStreamLike $this, final Time fromTime, final Time toTime) {
      return $this.slice(fromTime, toTime);
   }

   default List slice(final Time fromTime, final Time toTime) {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((Seq)this.dstream().slice(fromTime, toTime).map((in) -> this.wrapRDD(in))).asJava();
   }

   // $FF: synthetic method
   static void foreachRDD$(final JavaDStreamLike $this, final VoidFunction foreachFunc) {
      $this.foreachRDD(foreachFunc);
   }

   default void foreachRDD(final VoidFunction foreachFunc) {
      this.dstream().foreachRDD((Function1)((rdd) -> {
         $anonfun$foreachRDD$1(this, foreachFunc, rdd);
         return BoxedUnit.UNIT;
      }));
   }

   // $FF: synthetic method
   static void foreachRDD$(final JavaDStreamLike $this, final VoidFunction2 foreachFunc) {
      $this.foreachRDD(foreachFunc);
   }

   default void foreachRDD(final VoidFunction2 foreachFunc) {
      this.dstream().foreachRDD((scala.Function2)((rdd, time) -> {
         $anonfun$foreachRDD$2(this, foreachFunc, rdd, time);
         return BoxedUnit.UNIT;
      }));
   }

   // $FF: synthetic method
   static JavaDStream transform$(final JavaDStreamLike $this, final Function transformFunc) {
      return $this.transform(transformFunc);
   }

   default JavaDStream transform(final Function transformFunc) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return JavaDStream$.MODULE$.fromDStream(this.dstream().transform((Function1)((x$2) -> this.scalaTransform$1(x$2, transformFunc)), cm), cm);
   }

   // $FF: synthetic method
   static JavaDStream transform$(final JavaDStreamLike $this, final Function2 transformFunc) {
      return $this.transform(transformFunc);
   }

   default JavaDStream transform(final Function2 transformFunc) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return JavaDStream$.MODULE$.fromDStream(this.dstream().transform((scala.Function2)((x$3, x$4) -> this.scalaTransform$2(x$3, x$4, transformFunc)), cm), cm);
   }

   // $FF: synthetic method
   static JavaPairDStream transformToPair$(final JavaDStreamLike $this, final Function transformFunc) {
      return $this.transformToPair(transformFunc);
   }

   default JavaPairDStream transformToPair(final Function transformFunc) {
      ClassTag cmk = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmv = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().transform((Function1)((x$5) -> this.scalaTransform$3(x$5, transformFunc)), .MODULE$.apply(Tuple2.class)), cmk, cmv);
   }

   // $FF: synthetic method
   static JavaPairDStream transformToPair$(final JavaDStreamLike $this, final Function2 transformFunc) {
      return $this.transformToPair(transformFunc);
   }

   default JavaPairDStream transformToPair(final Function2 transformFunc) {
      ClassTag cmk = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmv = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().transform((scala.Function2)((x$6, x$7) -> this.scalaTransform$4(x$6, x$7, transformFunc)), .MODULE$.apply(Tuple2.class)), cmk, cmv);
   }

   // $FF: synthetic method
   static JavaDStream transformWith$(final JavaDStreamLike $this, final JavaDStream other, final Function3 transformFunc) {
      return $this.transformWith(other, transformFunc);
   }

   default JavaDStream transformWith(final JavaDStream other, final Function3 transformFunc) {
      ClassTag cmu = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmv = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return JavaDStream$.MODULE$.fromDStream(this.dstream().transformWith(other.dstream(), (scala.Function3)((x$8, x$9, x$10) -> this.scalaTransform$5(x$8, x$9, x$10, transformFunc, other)), cmu, cmv), cmv);
   }

   // $FF: synthetic method
   static JavaPairDStream transformWithToPair$(final JavaDStreamLike $this, final JavaDStream other, final Function3 transformFunc) {
      return $this.transformWithToPair(other, transformFunc);
   }

   default JavaPairDStream transformWithToPair(final JavaDStream other, final Function3 transformFunc) {
      ClassTag cmu = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmk2 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmv2 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().transformWith(other.dstream(), (scala.Function3)((x$11, x$12, x$13) -> this.scalaTransform$6(x$11, x$12, x$13, transformFunc, other)), cmu, .MODULE$.apply(Tuple2.class)), cmk2, cmv2);
   }

   // $FF: synthetic method
   static JavaDStream transformWith$(final JavaDStreamLike $this, final JavaPairDStream other, final Function3 transformFunc) {
      return $this.transformWith(other, transformFunc);
   }

   default JavaDStream transformWith(final JavaPairDStream other, final Function3 transformFunc) {
      ClassTag cmk2 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmv2 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmw = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return JavaDStream$.MODULE$.fromDStream(this.dstream().transformWith(other.dstream(), (scala.Function3)((x$14, x$15, x$16) -> this.scalaTransform$7(x$14, x$15, x$16, transformFunc, other)), .MODULE$.apply(Tuple2.class), cmw), cmw);
   }

   // $FF: synthetic method
   static JavaPairDStream transformWithToPair$(final JavaDStreamLike $this, final JavaPairDStream other, final Function3 transformFunc) {
      return $this.transformWithToPair(other, transformFunc);
   }

   default JavaPairDStream transformWithToPair(final JavaPairDStream other, final Function3 transformFunc) {
      ClassTag cmk2 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmv2 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmk3 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      ClassTag cmv3 = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().transformWith(other.dstream(), (scala.Function3)((x$17, x$18, x$19) -> this.scalaTransform$8(x$17, x$18, x$19, transformFunc, other)), .MODULE$.apply(Tuple2.class), .MODULE$.apply(Tuple2.class)), cmk3, cmv3);
   }

   // $FF: synthetic method
   static DStream checkpoint$(final JavaDStreamLike $this, final Duration interval) {
      return $this.checkpoint(interval);
   }

   default DStream checkpoint(final Duration interval) {
      return this.dstream().checkpoint(interval);
   }

   // $FF: synthetic method
   static Long $anonfun$scalaIntToJavaLong$1(final long x$1) {
      return x$1;
   }

   private static ClassTag cm$1() {
      return org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
   }

   private static Function1 fn$1(final FlatMapFunction f$1) {
      return (x) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(f$1.call(x)).asScala();
   }

   private static Function1 fn$2(final PairFlatMapFunction f$2) {
      return (x) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(f$2.call(x)).asScala();
   }

   private static ClassTag cm$2() {
      return org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
   }

   private static Function1 fn$3(final FlatMapFunction f$3) {
      return (x) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(f$3.call(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(x).asJava())).asScala();
   }

   private static Function1 fn$4(final PairFlatMapFunction f$4) {
      return (x) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(f$4.call(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(x).asJava())).asScala();
   }

   // $FF: synthetic method
   static void $anonfun$foreachRDD$1(final JavaDStreamLike $this, final VoidFunction foreachFunc$1, final RDD rdd) {
      foreachFunc$1.call($this.wrapRDD(rdd));
   }

   // $FF: synthetic method
   static void $anonfun$foreachRDD$2(final JavaDStreamLike $this, final VoidFunction2 foreachFunc$2, final RDD rdd, final Time time) {
      foreachFunc$2.call($this.wrapRDD(rdd), time);
   }

   private RDD scalaTransform$1(final RDD in, final Function transformFunc$1) {
      return ((JavaRDD)transformFunc$1.call(this.wrapRDD(in))).rdd();
   }

   private RDD scalaTransform$2(final RDD in, final Time time, final Function2 transformFunc$2) {
      return ((JavaRDD)transformFunc$2.call(this.wrapRDD(in), time)).rdd();
   }

   private RDD scalaTransform$3(final RDD in, final Function transformFunc$3) {
      return ((JavaPairRDD)transformFunc$3.call(this.wrapRDD(in))).rdd();
   }

   private RDD scalaTransform$4(final RDD in, final Time time, final Function2 transformFunc$4) {
      return ((JavaPairRDD)transformFunc$4.call(this.wrapRDD(in), time)).rdd();
   }

   private RDD scalaTransform$5(final RDD inThis, final RDD inThat, final Time time, final Function3 transformFunc$5, final JavaDStream other$1) {
      return ((JavaRDD)transformFunc$5.call(this.wrapRDD(inThis), other$1.wrapRDD(inThat), time)).rdd();
   }

   private RDD scalaTransform$6(final RDD inThis, final RDD inThat, final Time time, final Function3 transformFunc$6, final JavaDStream other$2) {
      return ((JavaPairRDD)transformFunc$6.call(this.wrapRDD(inThis), other$2.wrapRDD(inThat), time)).rdd();
   }

   private RDD scalaTransform$7(final RDD inThis, final RDD inThat, final Time time, final Function3 transformFunc$7, final JavaPairDStream other$3) {
      return ((JavaRDD)transformFunc$7.call(this.wrapRDD(inThis), other$3.wrapRDD(inThat), time)).rdd();
   }

   private RDD scalaTransform$8(final RDD inThis, final RDD inThat, final Time time, final Function3 transformFunc$8, final JavaPairDStream other$4) {
      return ((JavaPairRDD)transformFunc$8.call(this.wrapRDD(inThis), other$4.wrapRDD(inThat), time)).rdd();
   }

   static void $init$(final JavaDStreamLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
