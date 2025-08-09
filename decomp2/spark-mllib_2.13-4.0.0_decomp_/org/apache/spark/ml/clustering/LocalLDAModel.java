package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.linalg.MatrixImplicits$;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple5;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tEg\u0001B\u001f?\u0001%C\u0011B\u0014\u0001\u0003\u0002\u0003\u0006Ia\u0014/\t\u0013u\u0003!\u0011!Q\u0001\ny\u0013\u0007\"C2\u0001\u0005\u000b\u0007I\u0011\u0001 e\u0011!Y\u0007A!A!\u0002\u0013)\u0007\"\u00037\u0001\u0005\u0003\u0005\u000b\u0011B7t\u0011\u0019!\b\u0001\"\u0001Ak\"1A\u000f\u0001C\u0001\u0001nDQ\u0001 \u0001\u0005BuD\u0001\"a\b\u0001\t\u0003r\u0014\u0011\u0005\u0005\b\u0003O\u0001A\u0011IA\u0015\u0011\u001d\t\u0019\u0004\u0001C!\u0003kAq!!\u0012\u0001\t\u0003\n9eB\u0004\u0002RyB\t!a\u0015\u0007\rur\u0004\u0012AA+\u0011\u0019!h\u0002\"\u0001\u0002t\u00199\u0011Q\u000f\b\u0001\u001d\u0005]\u0004\"CA=!\t\u0005\t\u0015!\u0003w\u0011\u0019!\b\u0003\"\u0001\u0002|\u00191\u00111\u0011\tE\u0003\u000bC\u0011\"X\n\u0003\u0016\u0004%\t!!(\t\u0013\u0005}5C!E!\u0002\u0013q\u0006BCAQ'\tU\r\u0011\"\u0001\u0002$\"Q\u0011\u0011W\n\u0003\u0012\u0003\u0006I!!*\t\u0015\u0005M6C!f\u0001\n\u0003\t)\f\u0003\u0006\u0002>N\u0011\t\u0012)A\u0005\u0003oC!\"a0\u0014\u0005+\u0007I\u0011AAa\u0011)\tIm\u0005B\tB\u0003%\u00111\u0019\u0005\u000b\u0003\u0017\u001c\"Q3A\u0005\u0002\u0005\u0005\u0007BCAg'\tE\t\u0015!\u0003\u0002D\"1Ao\u0005C\u0001\u0003\u001fD\u0001\u0002`\n\u0002\u0002\u0013\u0005\u0011q\u001c\u0005\n\u0003W\u001c\u0012\u0013!C\u0001\u0003[D\u0011B!\u0001\u0014#\u0003%\tAa\u0001\t\u0013\t\u001d1#%A\u0005\u0002\t%\u0001\"\u0003B\u0007'E\u0005I\u0011\u0001B\b\u0011%\u0011\u0019bEI\u0001\n\u0003\u0011y\u0001C\u0005\u0003\u0016M\t\t\u0011\"\u0011\u0003\u0018!I!1E\n\u0002\u0002\u0013\u0005\u0011Q\u0014\u0005\n\u0005K\u0019\u0012\u0011!C\u0001\u0005OA\u0011Ba\r\u0014\u0003\u0003%\tE!\u000e\t\u0013\t\r3#!A\u0005\u0002\t\u0015\u0003\"\u0003B%'\u0005\u0005I\u0011\tB&\u0011%\u0011yeEA\u0001\n\u0003\u0012\t\u0006C\u0005\u0002FM\t\t\u0011\"\u0011\u0003T!I!QK\n\u0002\u0002\u0013\u0005#qK\u0004\n\u00057\u0002\u0012\u0011!E\u0005\u0005;2\u0011\"a!\u0011\u0003\u0003EIAa\u0018\t\rQ|C\u0011\u0001B7\u0011%\t)eLA\u0001\n\u000b\u0012\u0019\u0006C\u0005\u0003p=\n\t\u0011\"!\u0003r!I!QP\u0018\u0002\u0002\u0013\u0005%q\u0010\u0005\b\u0005#\u0003B\u0011\u000bBJ\r\u0019\u0011yJ\u0004\u0003\u0003\"\"1A/\u000eC\u0001\u0005SC\u0011B!,6\u0005\u0004%IAa\u0006\t\u0011\t=V\u0007)A\u0005\u00053AqA!-6\t\u0003\u0012\u0019\fC\u0004\u00038:!\tE!/\t\u000f\tEf\u0002\"\u0011\u0003>\"I!1\u0019\b\u0002\u0002\u0013%!Q\u0019\u0002\u000e\u0019>\u001c\u0017\r\u001c'E\u00036{G-\u001a7\u000b\u0005}\u0002\u0015AC2mkN$XM]5oO*\u0011\u0011IQ\u0001\u0003[2T!a\u0011#\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u00153\u0015AB1qC\u000eDWMC\u0001H\u0003\ry'oZ\u0002\u0001'\t\u0001!\n\u0005\u0002L\u00196\ta(\u0003\u0002N}\tAA\nR!N_\u0012,G.A\u0002vS\u0012\u0004\"\u0001U-\u000f\u0005E;\u0006C\u0001*V\u001b\u0005\u0019&B\u0001+I\u0003\u0019a$o\\8u})\ta+A\u0003tG\u0006d\u0017-\u0003\u0002Y+\u00061\u0001K]3eK\u001aL!AW.\u0003\rM#(/\u001b8h\u0015\tAV+\u0003\u0002O\u0019\u0006Iao\\2bENK'0\u001a\t\u0003?\u0002l\u0011!V\u0005\u0003CV\u00131!\u00138u\u0013\tiF*A\u0007pY\u0012dunY1m\u001b>$W\r\\\u000b\u0002KB\u0011aM[\u0007\u0002O*\u0011q\b\u001b\u0006\u0003S\n\u000bQ!\u001c7mS\nL!!P4\u0002\u001d=dG\rT8dC2lu\u000eZ3mA\u0005a1\u000f]1sWN+7o]5p]B\u0011a.]\u0007\u0002_*\u0011\u0001OQ\u0001\u0004gFd\u0017B\u0001:p\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u0013\taG*\u0001\u0004=S:LGO\u0010\u000b\u0006m^D\u0018P\u001f\t\u0003\u0017\u0002AQA\u0014\u0004A\u0002=CQ!\u0018\u0004A\u0002yCQa\u0019\u0004A\u0002\u0015DQ\u0001\u001c\u0004A\u00025$\u0012A^\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002w}\"1q\u0010\u0003a\u0001\u0003\u0003\tQ!\u001a=ue\u0006\u0004B!a\u0001\u0002\n5\u0011\u0011Q\u0001\u0006\u0004\u0003\u000f\u0001\u0015!\u00029be\u0006l\u0017\u0002BA\u0006\u0003\u000b\u0011\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0006\u0011\u0005=\u00111\u0004\t\u0005\u0003#\t9\"\u0004\u0002\u0002\u0014)\u0019\u0011Q\u0003\"\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u001a\u0005M!!B*j]\u000e,\u0017EAA\u000f\u0003\u0015\tdF\u000e\u00181\u0003!9W\r^'pI\u0016dWCAA\u0012!\r1\u0017QE\u0005\u0003\u001b\u001e\fQ\"[:ESN$(/\u001b2vi\u0016$WCAA\u0016!\ry\u0016QF\u0005\u0004\u0003_)&a\u0002\"p_2,\u0017M\u001c\u0015\u0006\u0015\u0005=\u00111D\u0001\u0006oJLG/Z\u000b\u0003\u0003o\u0001B!!\u000f\u0002@5\u0011\u00111\b\u0006\u0004\u0003{\u0001\u0015\u0001B;uS2LA!!\u0011\u0002<\tAQ\nT,sSR,'\u000fK\u0003\f\u0003\u001f\tY\"\u0001\u0005u_N#(/\u001b8h)\u0005y\u0005&\u0002\u0007\u0002\u0010\u0005-\u0013EAA'\u0003\u0015\u0019d\u0006\r\u00181Q\u0015\u0001\u0011qBA\u000e\u00035aunY1m\u0019\u0012\u000bUj\u001c3fYB\u00111JD\n\b\u001d\u0005]\u0013QLA2!\ry\u0016\u0011L\u0005\u0004\u00037*&AB!osJ+g\rE\u0003\u0002:\u0005}c/\u0003\u0003\u0002b\u0005m\"AC'M%\u0016\fG-\u00192mKB!\u0011QMA8\u001b\t\t9G\u0003\u0003\u0002j\u0005-\u0014AA5p\u0015\t\ti'\u0001\u0003kCZ\f\u0017\u0002BA9\u0003O\u0012AbU3sS\u0006d\u0017N_1cY\u0016$\"!a\u0015\u0003'1{7-\u00197M\t\u0006ku\u000eZ3m/JLG/\u001a:\u0014\u0007A\t9$\u0001\u0005j]N$\u0018M\\2f)\u0011\ti(!!\u0011\u0007\u0005}\u0004#D\u0001\u000f\u0011\u0019\tIH\u0005a\u0001m\n!A)\u0019;b'\u001d\u0019\u0012qKAD\u0003\u001b\u00032aXAE\u0013\r\tY)\u0016\u0002\b!J|G-^2u!\u0011\ty)!'\u000f\t\u0005E\u0015Q\u0013\b\u0004%\u0006M\u0015\"\u0001,\n\u0007\u0005]U+A\u0004qC\u000e\\\u0017mZ3\n\t\u0005E\u00141\u0014\u0006\u0004\u0003/+V#\u00010\u0002\u0015Y|7-\u00192TSj,\u0007%\u0001\u0007u_BL7m]'biJL\u00070\u0006\u0002\u0002&B!\u0011qUAW\u001b\t\tIKC\u0002\u0002,\u0002\u000ba\u0001\\5oC2<\u0017\u0002BAX\u0003S\u0013a!T1ue&D\u0018!\u0004;pa&\u001c7/T1ue&D\b%\u0001\te_\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]V\u0011\u0011q\u0017\t\u0005\u0003O\u000bI,\u0003\u0003\u0002<\u0006%&A\u0002,fGR|'/A\te_\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]\u0002\n!\u0003^8qS\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]V\u0011\u00111\u0019\t\u0004?\u0006\u0015\u0017bAAd+\n1Ai\\;cY\u0016\f1\u0003^8qS\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]\u0002\n!bZ1n[\u0006\u001c\u0006.\u00199f\u0003-9\u0017-\\7b'\"\f\u0007/\u001a\u0011\u0015\u0019\u0005E\u0017Q[Al\u00033\fY.!8\u0011\u0007\u0005M7#D\u0001\u0011\u0011\u0015if\u00041\u0001_\u0011\u001d\t\tK\ba\u0001\u0003KCq!a-\u001f\u0001\u0004\t9\fC\u0004\u0002@z\u0001\r!a1\t\u000f\u0005-g\u00041\u0001\u0002DRa\u0011\u0011[Aq\u0003G\f)/a:\u0002j\"9Ql\bI\u0001\u0002\u0004q\u0006\"CAQ?A\u0005\t\u0019AAS\u0011%\t\u0019l\bI\u0001\u0002\u0004\t9\fC\u0005\u0002@~\u0001\n\u00111\u0001\u0002D\"I\u00111Z\u0010\u0011\u0002\u0003\u0007\u00111Y\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyOK\u0002_\u0003c\\#!a=\u0011\t\u0005U\u0018Q`\u0007\u0003\u0003oTA!!?\u0002|\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003+)\u0016\u0002BA\u0000\u0003o\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"A!\u0002+\t\u0005\u0015\u0016\u0011_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\u0011YA\u000b\u0003\u00028\u0006E\u0018AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0005#QC!a1\u0002r\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012*\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0003\u001aA!!1\u0004B\u0011\u001b\t\u0011iB\u0003\u0003\u0003 \u0005-\u0014\u0001\u00027b]\u001eL1A\u0017B\u000f\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$BA!\u000b\u00030A\u0019qLa\u000b\n\u0007\t5RKA\u0002B]fD\u0001B!\r(\u0003\u0003\u0005\rAX\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t]\u0002C\u0002B\u001d\u0005\u007f\u0011I#\u0004\u0002\u0003<)\u0019!QH+\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003B\tm\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u000b\u0003H!I!\u0011G\u0015\u0002\u0002\u0003\u0007!\u0011F\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003\u001a\t5\u0003\u0002\u0003B\u0019U\u0005\u0005\t\u0019\u00010\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A\u0018\u000b\u0003\u00053\ta!Z9vC2\u001cH\u0003BA\u0016\u00053B\u0011B!\r.\u0003\u0003\u0005\rA!\u000b\u0002\t\u0011\u000bG/\u0019\t\u0004\u0003'|3#B\u0018\u0003b\u0005\r\u0004c\u0004B2\u0005Sr\u0016QUA\\\u0003\u0007\f\u0019-!5\u000e\u0005\t\u0015$b\u0001B4+\u00069!/\u001e8uS6,\u0017\u0002\u0002B6\u0005K\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c86)\t\u0011i&A\u0003baBd\u0017\u0010\u0006\u0007\u0002R\nM$Q\u000fB<\u0005s\u0012Y\bC\u0003^e\u0001\u0007a\fC\u0004\u0002\"J\u0002\r!!*\t\u000f\u0005M&\u00071\u0001\u00028\"9\u0011q\u0018\u001aA\u0002\u0005\r\u0007bBAfe\u0001\u0007\u00111Y\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\tI!$\u0011\u000b}\u0013\u0019Ia\"\n\u0007\t\u0015UK\u0001\u0004PaRLwN\u001c\t\r?\n%e,!*\u00028\u0006\r\u00171Y\u0005\u0004\u0005\u0017+&A\u0002+va2,W\u0007C\u0005\u0003\u0010N\n\t\u00111\u0001\u0002R\u0006\u0019\u0001\u0010\n\u0019\u0002\u0011M\fg/Z%na2$BA!&\u0003\u001cB\u0019qLa&\n\u0007\teUK\u0001\u0003V]&$\bB\u0002BOi\u0001\u0007q*\u0001\u0003qCRD'a\u0005'pG\u0006dG\nR!N_\u0012,GNU3bI\u0016\u00148cA\u001b\u0003$B)\u0011\u0011\bBSm&!!qUA\u001e\u0005!iEJU3bI\u0016\u0014HC\u0001BV!\r\ty(N\u0001\nG2\f7o\u001d(b[\u0016\f!b\u00197bgNt\u0015-\\3!\u0003\u0011aw.\u00193\u0015\u0007Y\u0014)\f\u0003\u0004\u0003\u001ef\u0002\raT\u0001\u0005e\u0016\fG-\u0006\u0002\u0003$\"*!(a\u0004\u0002\u001cQ\u0019aOa0\t\r\tu5\b1\u0001PQ\u0015Y\u0014qBA\u000e\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u00119\r\u0005\u0003\u0003\u001c\t%\u0017\u0002\u0002Bf\u0005;\u0011aa\u00142kK\u000e$\b&\u0002\b\u0002\u0010\u0005m\u0001&B\u0007\u0002\u0010\u0005m\u0001"
)
public class LocalLDAModel extends LDAModel {
   private final org.apache.spark.mllib.clustering.LocalLDAModel oldLocalModel;

   public static LocalLDAModel load(final String path) {
      return LocalLDAModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LocalLDAModel$.MODULE$.read();
   }

   public org.apache.spark.mllib.clustering.LocalLDAModel oldLocalModel() {
      return this.oldLocalModel;
   }

   public LocalLDAModel copy(final ParamMap extra) {
      LocalLDAModel copied = new LocalLDAModel(super.uid(), super.vocabSize(), this.oldLocalModel(), super.sparkSession());
      return (LocalLDAModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public org.apache.spark.mllib.clustering.LDAModel getModel() {
      return this.oldLocalModel();
   }

   public boolean isDistributed() {
      return false;
   }

   public MLWriter write() {
      return new LocalLDAModelWriter(this);
   }

   public String toString() {
      String var10000 = super.uid();
      return "LocalLDAModel: uid=" + var10000 + ", k=" + this.$(this.k()) + ", numFeatures=" + super.vocabSize();
   }

   public LocalLDAModel(final String uid, final int vocabSize, final org.apache.spark.mllib.clustering.LocalLDAModel oldLocalModel, final SparkSession sparkSession) {
      super(uid, vocabSize, sparkSession);
      this.oldLocalModel = oldLocalModel;
      oldLocalModel.setSeed(this.getSeed());
   }

   public LocalLDAModel() {
      this("", -1, (org.apache.spark.mllib.clustering.LocalLDAModel)null, (SparkSession)null);
   }

   public static class LocalLDAModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final LocalLDAModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         org.apache.spark.mllib.clustering.LocalLDAModel oldModel = this.instance.oldLocalModel();
         Data data = new Data(this.instance.vocabSize(), MatrixImplicits$.MODULE$.mllibMatrixToMLMatrix(oldModel.topicsMatrix()), VectorImplicits$.MODULE$.mllibVectorToMLVector(oldModel.docConcentration()), oldModel.topicConcentration(), oldModel.gammaShape());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         .colon.colon var10001 = new .colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LocalLDAModelWriter.class.getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.clustering.LocalLDAModel.LocalLDAModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.clustering.LocalLDAModel.LocalLDAModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public LocalLDAModelWriter(final LocalLDAModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final int vocabSize;
         private final Matrix topicsMatrix;
         private final Vector docConcentration;
         private final double topicConcentration;
         private final double gammaShape;
         // $FF: synthetic field
         public final LocalLDAModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public int vocabSize() {
            return this.vocabSize;
         }

         public Matrix topicsMatrix() {
            return this.topicsMatrix;
         }

         public Vector docConcentration() {
            return this.docConcentration;
         }

         public double topicConcentration() {
            return this.topicConcentration;
         }

         public double gammaShape() {
            return this.gammaShape;
         }

         public Data copy(final int vocabSize, final Matrix topicsMatrix, final Vector docConcentration, final double topicConcentration, final double gammaShape) {
            return this.org$apache$spark$ml$clustering$LocalLDAModel$LocalLDAModelWriter$Data$$$outer().new Data(vocabSize, topicsMatrix, docConcentration, topicConcentration, gammaShape);
         }

         public int copy$default$1() {
            return this.vocabSize();
         }

         public Matrix copy$default$2() {
            return this.topicsMatrix();
         }

         public Vector copy$default$3() {
            return this.docConcentration();
         }

         public double copy$default$4() {
            return this.topicConcentration();
         }

         public double copy$default$5() {
            return this.gammaShape();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 5;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return BoxesRunTime.boxToInteger(this.vocabSize());
               }
               case 1 -> {
                  return this.topicsMatrix();
               }
               case 2 -> {
                  return this.docConcentration();
               }
               case 3 -> {
                  return BoxesRunTime.boxToDouble(this.topicConcentration());
               }
               case 4 -> {
                  return BoxesRunTime.boxToDouble(this.gammaShape());
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
                  return "vocabSize";
               }
               case 1 -> {
                  return "topicsMatrix";
               }
               case 2 -> {
                  return "docConcentration";
               }
               case 3 -> {
                  return "topicConcentration";
               }
               case 4 -> {
                  return "gammaShape";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, this.vocabSize());
            var1 = Statics.mix(var1, Statics.anyHash(this.topicsMatrix()));
            var1 = Statics.mix(var1, Statics.anyHash(this.docConcentration()));
            var1 = Statics.mix(var1, Statics.doubleHash(this.topicConcentration()));
            var1 = Statics.mix(var1, Statics.doubleHash(this.gammaShape()));
            return Statics.finalizeHash(var1, 5);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var8;
            if (this != x$1) {
               label72: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$clustering$LocalLDAModel$LocalLDAModelWriter$Data$$$outer() == this.org$apache$spark$ml$clustering$LocalLDAModel$LocalLDAModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.vocabSize() == var4.vocabSize() && this.topicConcentration() == var4.topicConcentration() && this.gammaShape() == var4.gammaShape()) {
                        label62: {
                           Matrix var10000 = this.topicsMatrix();
                           Matrix var5 = var4.topicsMatrix();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label62;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label62;
                           }

                           Vector var7 = this.docConcentration();
                           Vector var6 = var4.docConcentration();
                           if (var7 == null) {
                              if (var6 != null) {
                                 break label62;
                              }
                           } else if (!var7.equals(var6)) {
                              break label62;
                           }

                           if (var4.canEqual(this)) {
                              break label72;
                           }
                        }
                     }
                  }

                  var8 = false;
                  return var8;
               }
            }

            var8 = true;
            return var8;
         }

         // $FF: synthetic method
         public LocalLDAModelWriter org$apache$spark$ml$clustering$LocalLDAModel$LocalLDAModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final int vocabSize, final Matrix topicsMatrix, final Vector docConcentration, final double topicConcentration, final double gammaShape) {
            this.vocabSize = vocabSize;
            this.topicsMatrix = topicsMatrix;
            this.docConcentration = docConcentration;
            this.topicConcentration = topicConcentration;
            this.gammaShape = gammaShape;
            if (LocalLDAModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = LocalLDAModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction5 implements Serializable {
         // $FF: synthetic field
         private final LocalLDAModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final int vocabSize, final Matrix topicsMatrix, final Vector docConcentration, final double topicConcentration, final double gammaShape) {
            return this.$outer.new Data(vocabSize, topicsMatrix, docConcentration, topicConcentration, gammaShape);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToInteger(x$0.vocabSize()), x$0.topicsMatrix(), x$0.docConcentration(), BoxesRunTime.boxToDouble(x$0.topicConcentration()), BoxesRunTime.boxToDouble(x$0.gammaShape()))));
         }

         public Data$() {
            if (LocalLDAModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = LocalLDAModelWriter.this;
               super();
            }
         }
      }
   }

   private static class LocalLDAModelReader extends MLReader {
      private final String className = LocalLDAModel.class.getName();

      private String className() {
         return this.className;
      }

      public LocalLDAModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().parquet(dataPath);
         Dataset vectorConverted = MLUtils$.MODULE$.convertVectorColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"docConcentration"})));
         Dataset matrixConverted = MLUtils$.MODULE$.convertMatrixColumnsToML(vectorConverted, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"topicsMatrix"})));
         Row var9 = (Row)matrixConverted.select("vocabSize", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"topicsMatrix", "docConcentration", "topicConcentration", "gammaShape"}))).head();
         if (var9 != null) {
            Some var10 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var9);
            if (!var10.isEmpty() && var10.get() != null && ((SeqOps)var10.get()).lengthCompare(5) == 0) {
               Object vocabSize = ((SeqOps)var10.get()).apply(0);
               Object topicsMatrix = ((SeqOps)var10.get()).apply(1);
               Object docConcentration = ((SeqOps)var10.get()).apply(2);
               Object topicConcentration = ((SeqOps)var10.get()).apply(3);
               Object gammaShape = ((SeqOps)var10.get()).apply(4);
               if (vocabSize instanceof Integer) {
                  int var16 = BoxesRunTime.unboxToInt(vocabSize);
                  if (topicsMatrix instanceof Matrix) {
                     Matrix var17 = (Matrix)topicsMatrix;
                     if (docConcentration instanceof Vector) {
                        Vector var18 = (Vector)docConcentration;
                        if (topicConcentration instanceof Double) {
                           double var19 = BoxesRunTime.unboxToDouble(topicConcentration);
                           if (gammaShape instanceof Double) {
                              double var21 = BoxesRunTime.unboxToDouble(gammaShape);
                              Tuple5 var8 = new Tuple5(BoxesRunTime.boxToInteger(var16), var17, var18, BoxesRunTime.boxToDouble(var19), BoxesRunTime.boxToDouble(var21));
                              int vocabSize = BoxesRunTime.unboxToInt(var8._1());
                              Matrix topicsMatrix = (Matrix)var8._2();
                              Vector docConcentration = (Vector)var8._3();
                              double topicConcentration = BoxesRunTime.unboxToDouble(var8._4());
                              double gammaShape = BoxesRunTime.unboxToDouble(var8._5());
                              org.apache.spark.mllib.clustering.LocalLDAModel oldModel = new org.apache.spark.mllib.clustering.LocalLDAModel(MatrixImplicits$.MODULE$.mlMatrixToMLlibMatrix(topicsMatrix), VectorImplicits$.MODULE$.mlVectorToMLlibVector(docConcentration), topicConcentration, gammaShape);
                              LocalLDAModel model = new LocalLDAModel(metadata.uid(), vocabSize, oldModel, this.sparkSession());
                              LDAParams$.MODULE$.getAndSetParams(model, metadata);
                              return model;
                           }
                        }
                     }
                  }
               }
            }
         }

         throw new MatchError(var9);
      }

      public LocalLDAModelReader() {
      }
   }
}
