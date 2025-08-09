package org.apache.spark.sql.hive;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.Serializable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.exec.SerializationUtilities;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.plan.ExprNodeDesc;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFMacro;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveDecimalObjectInspector;
import org.apache.hadoop.io.Writable;
import org.apache.spark.sql.types.Decimal;
import org.apache.spark.util.Utils.;
import org.sparkproject.guava.base.Objects;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r}qA\u0002\u001b6\u0011\u0003)tH\u0002\u0004Bk!\u0005QG\u0011\u0005\u0006\u0013\u0006!\ta\u0013\u0005\b\u0019\u0006\u0011\r\u0011\"\u0001N\u0011\u00191\u0016\u0001)A\u0005\u001d\")q+\u0001C\u00051\")!0\u0001C\u0001w\"9\u00111B\u0001\u0005\u0002\u00055\u0001bBA\u0016\u0003\u0011\u0005\u0011Q\u0006\u0004\b\u0003?\n\u0001)NA1\u0011)\ty(\u0003BI\u0002\u0013\u0005\u0011\u0011\u0011\u0005\u000b\u0003\u0007K!\u00111A\u0005\u0002\u0005\u0015\u0005\"CAF\u0013\tE\t\u0015)\u0003t\u0011)\ti)\u0003BA\u0002\u0013%\u0011q\u0012\u0005\u000b\u0003#K!\u00111A\u0005\n\u0005M\u0005\"CAL\u0013\tE\t\u0015)\u0003D\u0011)\tI*\u0003BA\u0002\u0013%\u00111\u0014\u0005\u000b\u0003oK!\u00111A\u0005\n\u0005e\u0006BCAW\u0013\tE\t\u0015)\u0003\u0002\u001e\"1\u0011*\u0003C\u0001\u0003{Ca!S\u0005\u0005\u0002\u0005E\u0007bBAj\u0013\u0011\u0005\u0013Q\u001b\u0005\b\u0003;LA\u0011IAp\u0011\u001d\tY/\u0003C\u0001\u0003[DqAa\u0004\n\t\u0003\u0011\t\u0002C\u0004\u0003\"%!\tAa\t\t\u000f\t5\u0012\u0002\"\u0001\u00030!9!1H\u0005\u0005\u0002\tu\u0002\"\u0003B#\u0013\u0005\u0005I\u0011\u0001B$\u0011%\u0011y%CI\u0001\n\u0003\u0011\t\u0006C\u0005\u0003h%\t\n\u0011\"\u0001\u0003j!I!QN\u0005\u0012\u0002\u0013\u0005!q\u000e\u0005\n\u0005{J\u0011\u0012!C\u0001\u0003\u001fC\u0011Ba \n\u0013\u0003%\t!a'\t\u0011\t\u0005\u0015\"!A\u0005B5C\u0011Ba!\n\u0003\u0003%\tA!\"\t\u0013\t\u001d\u0015\"!A\u0005\u0002\t%\u0005\"\u0003BG\u0013\u0005\u0005I\u0011\tBH\u0011%\u0011i*CA\u0001\n\u0003\u0011y\nC\u0005\u0003$&\t\t\u0011\"\u0011\u0003&\"I!\u0011V\u0005\u0002\u0002\u0013\u0005#1V\u0004\u000b\u0005[\u000b\u0011\u0011!E\u0001k\t=fACA0\u0003\u0005\u0005\t\u0012A\u001b\u00032\"1\u0011J\u000bC\u0001\u0005\u0017D\u0011B!++\u0003\u0003%)Ea+\t\u0013\t5'&!A\u0005\u0002\n=\u0007\"\u0003BpUE\u0005I\u0011\u0001B5\u0011%\u0011\tOKI\u0001\n\u0003\u0011\u0019\u000fC\u0005\u0003p*\n\t\u0011\"!\u0003r\"I11\u0002\u0016\u0012\u0002\u0013\u0005!\u0011\u000e\u0005\n\u0007\u001bQ\u0013\u0013!C\u0001\u0007\u001fA\u0011ba\u0007+\u0003\u0003%Ia!\b\u0002\u0011!Kg/Z*iS6T!AN\u001c\u0002\t!Lg/\u001a\u0006\u0003qe\n1a]9m\u0015\tQ4(A\u0003ta\u0006\u00148N\u0003\u0002={\u00051\u0011\r]1dQ\u0016T\u0011AP\u0001\u0004_J<\u0007C\u0001!\u0002\u001b\u0005)$\u0001\u0003%jm\u0016\u001c\u0006.[7\u0014\u0005\u0005\u0019\u0005C\u0001#H\u001b\u0005)%\"\u0001$\u0002\u000bM\u001c\u0017\r\\1\n\u0005!+%AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005y\u0014A\u0007%J-\u0016{v)\u0012(F%&\u001bu,\u0016#G?6\u000b5IU(`\u00072\u001bV#\u0001(\u0011\u0005=#V\"\u0001)\u000b\u0005E\u0013\u0016\u0001\u00027b]\u001eT\u0011aU\u0001\u0005U\u00064\u0018-\u0003\u0002V!\n11\u000b\u001e:j]\u001e\f1\u0004S%W\u000b~;UIT#S\u0013\u000e{V\u000b\u0012$`\u001b\u0006\u001b%kT0D\u0019N\u0003\u0013!F1qa\u0016tGMU3bI\u000e{G.^7o\u001d\u0006lWm\u001d\u000b\u00043r+\u0007C\u0001#[\u0013\tYVI\u0001\u0003V]&$\b\"B/\u0006\u0001\u0004q\u0016\u0001B2p]\u001a\u0004\"aX2\u000e\u0003\u0001T!!X1\u000b\u0005\t\\\u0014A\u00025bI>|\u0007/\u0003\u0002eA\ni1i\u001c8gS\u001e,(/\u0019;j_:DQAZ\u0003A\u0002\u001d\fAaY8mgB\u0019\u0001\u000e]:\u000f\u0005%tgB\u00016n\u001b\u0005Y'B\u00017K\u0003\u0019a$o\\8u}%\ta)\u0003\u0002p\u000b\u00069\u0001/Y2lC\u001e,\u0017BA9s\u0005\r\u0019V-\u001d\u0006\u0003_\u0016\u0003\"\u0001\u001e=\u000f\u0005U4\bC\u00016F\u0013\t9X)\u0001\u0004Qe\u0016$WMZ\u0005\u0003+fT!a^#\u0002#\u0005\u0004\b/\u001a8e%\u0016\fGmQ8mk6t7\u000fF\u0003Zyv\f9\u0001C\u0003^\r\u0001\u0007a\fC\u0003\u007f\r\u0001\u0007q0A\u0002jIN\u0004B\u0001\u001b9\u0002\u0002A\u0019q*a\u0001\n\u0007\u0005\u0015\u0001KA\u0004J]R,w-\u001a:\t\r\u0005%a\u00011\u0001h\u0003\u0015q\u0017-\\3t\u0003=\u0001(/\u001a9be\u0016<&/\u001b;bE2,GCBA\b\u00037\ty\u0002\u0005\u0003\u0002\u0012\u0005]QBAA\n\u0015\r\t)\"Y\u0001\u0003S>LA!!\u0007\u0002\u0014\tAqK]5uC\ndW\rC\u0004\u0002\u001e\u001d\u0001\r!a\u0004\u0002\u0003]Dq!!\t\b\u0001\u0004\t\u0019#\u0001\u0006tKJ$U\r\u0015:paN\u0004B\u0001\u001b9\u0002&A)A)a\ntg&\u0019\u0011\u0011F#\u0003\rQ+\b\u000f\\33\u0003E!xnQ1uC2L8\u000f\u001e#fG&l\u0017\r\u001c\u000b\u0007\u0003_\tY$!\u0016\u0011\t\u0005E\u0012qG\u0007\u0003\u0003gQ1!!\u000e8\u0003\u0015!\u0018\u0010]3t\u0013\u0011\tI$a\r\u0003\u000f\u0011+7-[7bY\"9\u0011Q\b\u0005A\u0002\u0005}\u0012\u0001\u00025e_&\u0004B!!\u0011\u0002R5\u0011\u00111\t\u0006\u0005\u0003\u000b\n9%A\u0005qe&l\u0017\u000e^5wK*!\u0011\u0011JA&\u0003=y'M[3di&t7\u000f]3di>\u0014(\u0002BA'\u0003\u001f\naa]3sI\u0016\u0014$B\u0001\u001cb\u0013\u0011\t\u0019&a\u0011\u00035!Kg/\u001a#fG&l\u0017\r\\(cU\u0016\u001cG/\u00138ta\u0016\u001cGo\u001c:\t\u000f\u0005]\u0003\u00021\u0001\u0002Z\u0005!A-\u0019;b!\r!\u00151L\u0005\u0004\u0003;*%aA!os\n\u0019\u0002*\u001b<f\rVt7\r^5p]^\u0013\u0018\r\u001d9feNI\u0011\"a\u0019\u0002j\u0005M\u0014\u0011\u0010\t\u0004\u001f\u0006\u0015\u0014bAA4!\n1qJ\u00196fGR\u0004B!a\u001b\u0002p5\u0011\u0011Q\u000e\u0006\u0004\u0003+\u0011\u0016\u0002BA9\u0003[\u0012a\"\u0012=uKJt\u0017\r\\5{C\ndW\rE\u0002E\u0003kJ1!a\u001eF\u0005\u001d\u0001&o\u001c3vGR\u00042\u0001[A>\u0013\r\tiH\u001d\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0012MVt7\r^5p]\u000ec\u0017m]:OC6,W#A:\u0002+\u0019,hn\u0019;j_:\u001cE.Y:t\u001d\u0006lWm\u0018\u0013fcR\u0019\u0011,a\"\t\u0011\u0005%5\"!AA\u0002M\f1\u0001\u001f\u00132\u0003I1WO\\2uS>t7\t\\1tg:\u000bW.\u001a\u0011\u0002\u0011%t7\u000f^1oG\u0016,\u0012aQ\u0001\rS:\u001cH/\u00198dK~#S-\u001d\u000b\u00043\u0006U\u0005\u0002CAE\u001d\u0005\u0005\t\u0019A\"\u0002\u0013%t7\u000f^1oG\u0016\u0004\u0013!B2mCjTXCAAOa\u0011\ty*!+\u0011\u000bQ\f\t+!*\n\u0007\u0005\r\u0016PA\u0003DY\u0006\u001c8\u000f\u0005\u0003\u0002(\u0006%F\u0002\u0001\u0003\f\u0003W\u0013\u0012\u0011!A\u0001\u0006\u0003\tyKA\u0002`IE\naa\u00197buj\u0004\u0013cAAY\u0007B\u0019A)a-\n\u0007\u0005UVIA\u0004O_RD\u0017N\\4\u0002\u0013\rd\u0017M\u001f>`I\u0015\fHcA-\u0002<\"I\u0011\u0011R\t\u0002\u0002\u0003\u0007\u0011Q\u0014\u000b\t\u0003\u007f\u000b\u0019-!2\u0002HB\u0019\u0011\u0011Y\u0005\u000e\u0003\u0005Aa!a \u0014\u0001\u0004\u0019\b\u0002CAG'A\u0005\t\u0019A\"\t\u0013\u0005e5\u0003%AA\u0002\u0005%\u0007\u0007BAf\u0003\u001f\u0004R\u0001^AQ\u0003\u001b\u0004B!a*\u0002P\u0012a\u00111VAd\u0003\u0003\u0005\tQ!\u0001\u00020R\u0011\u0011qX\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011q\u001b\t\u0004\t\u0006e\u0017bAAn\u000b\n\u0019\u0011J\u001c;\u0002\r\u0015\fX/\u00197t)\u0011\t\t/a:\u0011\u0007\u0011\u000b\u0019/C\u0002\u0002f\u0016\u0013qAQ8pY\u0016\fg\u000eC\u0004\u0002jZ\u0001\r!!\u0017\u0002\u000b=$\b.\u001a:\u0002\u001f\u0011,7/\u001a:jC2L'0\u001a)mC:,B!a<\u0002tR1\u0011\u0011_A}\u0005\u0007\u0001B!a*\u0002t\u00129\u0011Q_\fC\u0002\u0005](aB+E\rRK\b/Z\t\u0005\u0003c\u000bI\u0006C\u0004\u0002|^\u0001\r!!@\u0002\u0005%\u001c\b\u0003BA6\u0003\u007fLAA!\u0001\u0002n\tY\u0011J\u001c9viN#(/Z1n\u0011\u001d\tIj\u0006a\u0001\u0005\u000b\u0001DAa\u0002\u0003\fA)A/!)\u0003\nA!\u0011q\u0015B\u0006\t1\u0011iAa\u0001\u0002\u0002\u0003\u0005)\u0011AA|\u0005\ryFEM\u0001\u000eg\u0016\u0014\u0018.\u00197ju\u0016\u0004F.\u00198\u0015\u000be\u0013\u0019Ba\u0006\t\r\tU\u0001\u00041\u0001D\u0003!1WO\\2uS>t\u0007b\u0002B\r1\u0001\u0007!1D\u0001\u0004_V$\b\u0003BA6\u0005;IAAa\b\u0002n\taq*\u001e;qkR\u001cFO]3b[\u0006iqO]5uK\u0016CH/\u001a:oC2$2!\u0017B\u0013\u0011\u001d\u0011I\"\u0007a\u0001\u0005O\u0001B!a\u001b\u0003*%!!1FA7\u00051y%M[3di>+H\u000f];u\u00031\u0011X-\u00193FqR,'O\\1m)\rI&\u0011\u0007\u0005\b\u0005gQ\u0002\u0019\u0001B\u001b\u0003\tIg\u000e\u0005\u0003\u0002l\t]\u0012\u0002\u0002B\u001d\u0003[\u00121b\u00142kK\u000e$\u0018J\u001c9vi\u0006q1M]3bi\u00164UO\\2uS>tW\u0003\u0002B \u0005\u0007\"\"A!\u0011\u0011\t\u0005\u001d&1\t\u0003\b\u0003k\\\"\u0019AAX\u0003\u0011\u0019w\u000e]=\u0015\u0011\u0005}&\u0011\nB&\u0005\u001bB\u0001\"a \u001d!\u0003\u0005\ra\u001d\u0005\t\u0003\u001bc\u0002\u0013!a\u0001\u0007\"I\u0011\u0011\u0014\u000f\u0011\u0002\u0003\u0007\u0011\u0011Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011\u0019FK\u0002t\u0005+Z#Aa\u0016\u0011\t\te#1M\u0007\u0003\u00057RAA!\u0018\u0003`\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005C*\u0015AC1o]>$\u0018\r^5p]&!!Q\rB.\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011YGK\u0002D\u0005+\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0003rA\"!1\u000fB>U\u0011\u0011)H!\u0016\u0011\u000b=\u00139H!\u001f\n\u0007\u0005\r\u0006\u000b\u0005\u0003\u0002(\nmDaCAV?\u0005\u0005\t\u0011!B\u0001\u0003_\u000b\u0011#\u001b8ti\u0006t7-\u001a\u0013bG\u000e,7o\u001d\u00132\u00039\u0019G.\u0019>{I\u0005\u001c7-Z:tII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&D\u0018\u0001\u00049s_\u0012,8\r^!sSRLXCAAl\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0017\u0003\f\"I\u0011\u0011\u0012\u0013\u0002\u0002\u0003\u0007\u0011q[\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!\u0011\u0013\t\u0007\u0005'\u0013I*!\u0017\u000e\u0005\tU%b\u0001BL\u000b\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\tm%Q\u0013\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002b\n\u0005\u0006\"CAEM\u0005\u0005\t\u0019AA-\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u00079\u00139\u000bC\u0005\u0002\n\u001e\n\t\u00111\u0001\u0002X\u0006AAo\\*ue&tw\rF\u0001O\u0003MA\u0015N^3Gk:\u001cG/[8o/J\f\u0007\u000f]3s!\r\t\tMK\n\u0006U\tM&q\u0019\t\u000b\u0005k\u0013Yl]\"\u0003@\u0006}VB\u0001B\\\u0015\r\u0011I,R\u0001\beVtG/[7f\u0013\u0011\u0011iLa.\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\r\u0003\u0003B\n\u0015\u0007#\u0002;\u0002\"\n\r\u0007\u0003BAT\u0005\u000b$1\"a++\u0003\u0003\u0005\tQ!\u0001\u00020B!\u00111\u000eBe\u0013\u0011\ti(!\u001c\u0015\u0005\t=\u0016!B1qa2LH\u0003CA`\u0005#\u0014\u0019N!6\t\r\u0005}T\u00061\u0001t\u0011!\ti)\fI\u0001\u0002\u0004\u0019\u0005\"CAM[A\u0005\t\u0019\u0001Bla\u0011\u0011IN!8\u0011\u000bQ\f\tKa7\u0011\t\u0005\u001d&Q\u001c\u0003\r\u0003W\u0013).!A\u0001\u0002\u000b\u0005\u0011qV\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$3'\u0006\u0002\u0003fB\"!q\u001dBwU\u0011\u0011IO!\u0016\u0011\u000b=\u00139Ha;\u0011\t\u0005\u001d&Q\u001e\u0003\f\u0003W{\u0013\u0011!A\u0001\u0006\u0003\ty+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\tM8q\u0001\t\u0006\t\nU(\u0011`\u0005\u0004\u0005o,%AB(qi&|g\u000eE\u0004E\u0005w\u001c8Ia@\n\u0007\tuXI\u0001\u0004UkBdWm\r\u0019\u0005\u0007\u0003\u0019)\u0001E\u0003u\u0003C\u001b\u0019\u0001\u0005\u0003\u0002(\u000e\u0015AaCAVa\u0005\u0005\t\u0011!B\u0001\u0003_C\u0011b!\u00031\u0003\u0003\u0005\r!a0\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\rE\u0001\u0007BB\n\u00073QCa!\u0006\u0003VA)qJa\u001e\u0004\u0018A!\u0011qUB\r\t-\tYKMA\u0001\u0002\u0003\u0015\t!a,\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\r\u0004"
)
public final class HiveShim {
   public static Decimal toCatalystDecimal(final HiveDecimalObjectInspector hdoi, final Object data) {
      return HiveShim$.MODULE$.toCatalystDecimal(hdoi, data);
   }

   public static Writable prepareWritable(final Writable w, final Seq serDeProps) {
      return HiveShim$.MODULE$.prepareWritable(w, serDeProps);
   }

   public static void appendReadColumns(final Configuration conf, final Seq ids, final Seq names) {
      HiveShim$.MODULE$.appendReadColumns(conf, ids, names);
   }

   public static String HIVE_GENERIC_UDF_MACRO_CLS() {
      return HiveShim$.MODULE$.HIVE_GENERIC_UDF_MACRO_CLS();
   }

   public static class HiveFunctionWrapper implements Externalizable, Product {
      private String functionClassName;
      private Object org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance;
      private Class org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object instance$access$1() {
         return this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance;
      }

      public Class clazz$access$2() {
         return this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz;
      }

      public String functionClassName() {
         return this.functionClassName;
      }

      public void functionClassName_$eq(final String x$1) {
         this.functionClassName = x$1;
      }

      public Object org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance() {
         return this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance;
      }

      private void instance_$eq(final Object x$1) {
         this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance = x$1;
      }

      public Class org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz() {
         return this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz;
      }

      private void clazz_$eq(final Class x$1) {
         this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz = x$1;
      }

      public int hashCode() {
         String var10000 = this.functionClassName();
         String var1 = HiveShim$.MODULE$.HIVE_GENERIC_UDF_MACRO_CLS();
         if (var10000 == null) {
            if (var1 == null) {
               return Objects.hashCode(new Object[]{this.functionClassName(), ((GenericUDFMacro)this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance()).getBody()});
            }
         } else if (var10000.equals(var1)) {
            return Objects.hashCode(new Object[]{this.functionClassName(), ((GenericUDFMacro)this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance()).getBody()});
         }

         return this.functionClassName().hashCode();
      }

      public boolean equals(final Object other) {
         if (other instanceof HiveFunctionWrapper var4) {
            String var10000 = this.functionClassName();
            String var5 = var4.functionClassName();
            if (var10000 == null) {
               if (var5 != null) {
                  return false;
               }
            } else if (!var10000.equals(var5)) {
               return false;
            }

            var10000 = this.functionClassName();
            String var6 = HiveShim$.MODULE$.HIVE_GENERIC_UDF_MACRO_CLS();
            if (var10000 == null) {
               if (var6 != null) {
                  return true;
               }
            } else if (!var10000.equals(var6)) {
               return true;
            }

            label63: {
               ExprNodeDesc var9 = ((GenericUDFMacro)var4.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance()).getBody();
               ExprNodeDesc var7 = ((GenericUDFMacro)this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance()).getBody();
               if (var9 == null) {
                  if (var7 == null) {
                     break label63;
                  }
               } else if (var9.equals(var7)) {
                  break label63;
               }

               var10 = false;
               return var10;
            }

            var10 = true;
            return var10;
         } else {
            return false;
         }
      }

      public Object deserializePlan(final InputStream is, final Class clazz) {
         return SerializationUtilities.deserializePlan(is, clazz);
      }

      public void serializePlan(final Object function, final OutputStream out) {
         SerializationUtilities.serializePlan(function, out);
      }

      public void writeExternal(final ObjectOutput out) {
         out.writeUTF(this.functionClassName());
         out.writeBoolean(this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance() != null);
         if (this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance() != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            this.serializePlan(this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance(), baos);
            byte[] functionInBytes = baos.toByteArray();
            out.writeInt(functionInBytes.length);
            out.write(functionInBytes, 0, functionInBytes.length);
         }
      }

      public void readExternal(final ObjectInput in) {
         this.functionClassName_$eq(in.readUTF());
         if (in.readBoolean()) {
            int functionInBytesLength = in.readInt();
            byte[] functionInBytes = new byte[functionInBytesLength];
            in.readFully(functionInBytes);
            this.clazz_$eq(.MODULE$.getContextOrSparkClassLoader().loadClass(this.functionClassName()));
            this.instance_$eq(this.deserializePlan(new ByteArrayInputStream(functionInBytes), this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz()));
         }
      }

      public Object createFunction() {
         if (this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance() != null) {
            return this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance();
         } else {
            if (this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz() == null) {
               this.clazz_$eq(.MODULE$.getContextOrSparkClassLoader().loadClass(this.functionClassName()));
            }

            Object func = this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz().getConstructor().newInstance();
            if (!(func instanceof UDF)) {
               this.instance_$eq(func);
            }

            return func;
         }
      }

      public HiveFunctionWrapper copy(final String functionClassName, final Object instance, final Class clazz) {
         return new HiveFunctionWrapper(functionClassName, instance, clazz);
      }

      public String copy$default$1() {
         return this.functionClassName();
      }

      public Object copy$default$2() {
         return this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance();
      }

      public Class copy$default$3() {
         return this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz();
      }

      public String productPrefix() {
         return "HiveFunctionWrapper";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.functionClassName();
            }
            case 1 -> {
               return this.instance$access$1();
            }
            case 2 -> {
               return this.clazz$access$2();
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
         return x$1 instanceof HiveFunctionWrapper;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "functionClassName";
            }
            case 1 -> {
               return "instance";
            }
            case 2 -> {
               return "clazz";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public HiveFunctionWrapper(final String functionClassName, final Object instance, final Class clazz) {
         this.functionClassName = functionClassName;
         this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance = instance;
         this.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz = clazz;
         super();
         Product.$init$(this);
      }

      public HiveFunctionWrapper() {
         this((String)null, HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$2(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$3());
      }
   }

   public static class HiveFunctionWrapper$ extends AbstractFunction3 implements Serializable {
      public static final HiveFunctionWrapper$ MODULE$ = new HiveFunctionWrapper$();

      public Object $lessinit$greater$default$2() {
         return null;
      }

      public Class $lessinit$greater$default$3() {
         return null;
      }

      public final String toString() {
         return "HiveFunctionWrapper";
      }

      public HiveFunctionWrapper apply(final String functionClassName, final Object instance, final Class clazz) {
         return new HiveFunctionWrapper(functionClassName, instance, clazz);
      }

      public Object apply$default$2() {
         return null;
      }

      public Class apply$default$3() {
         return null;
      }

      public Option unapply(final HiveFunctionWrapper x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.functionClassName(), x$0.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$instance(), x$0.org$apache$spark$sql$hive$HiveShim$HiveFunctionWrapper$$clazz())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(HiveFunctionWrapper$.class);
      }
   }
}
