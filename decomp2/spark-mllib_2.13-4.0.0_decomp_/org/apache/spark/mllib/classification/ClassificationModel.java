package org.apache.spark.mllib.classification;

import java.io.Serializable;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import org.json4s.JValue;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001daa\u0002\u0006\f!\u0003\r\tA\u0006\u0005\u0006S\u0001!\tA\u000b\u0005\u0006]\u00011\ta\f\u0005\u0006]\u00011\ta\u0013\u0005\u0006]\u0001!\tAT\u0004\u0007G.A\t!\u00043\u0007\r)Y\u0001\u0012A\u0007g\u0011\u0015ag\u0001\"\u0001n\u0011\u0015qg\u0001\"\u0001p\u0011\u001dqh!!A\u0005\n}\u00141c\u00117bgNLg-[2bi&|g.T8eK2T!\u0001D\u0007\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]*\u0011abD\u0001\u0006[2d\u0017N\u0019\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sO\u000e\u00011c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"A\b\u0014\u000f\u0005}!cB\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u0016\u0003\u0019a$o\\8u}%\t!$\u0003\u0002&3\u00059\u0001/Y2lC\u001e,\u0017BA\u0014)\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t)\u0013$\u0001\u0004%S:LG\u000f\n\u000b\u0002WA\u0011\u0001\u0004L\u0005\u0003[e\u0011A!\u00168ji\u00069\u0001O]3eS\u000e$HC\u0001\u0019:!\r\tDGN\u0007\u0002e)\u00111gD\u0001\u0004e\u0012$\u0017BA\u001b3\u0005\r\u0011F\t\u0012\t\u00031]J!\u0001O\r\u0003\r\u0011{WO\u00197f\u0011\u0015Q$\u00011\u0001<\u0003!!Xm\u001d;ECR\f\u0007cA\u00195yA\u0011Q\bQ\u0007\u0002})\u0011q(D\u0001\u0007Y&t\u0017\r\\4\n\u0005\u0005s$A\u0002,fGR|'\u000fK\u0002\u0003\u0007&\u0003\"\u0001R$\u000e\u0003\u0015S!AR\b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002I\u000b\n)1+\u001b8dK\u0006\n!*A\u00032]Ar\u0003\u0007\u0006\u00027\u0019\")!h\u0001a\u0001y!\u001a1aQ%\u0015\u0005=k\u0006c\u0001)V/6\t\u0011K\u0003\u0002S'\u0006!!.\u0019<b\u0015\t!v\"A\u0002ba&L!AV)\u0003\u000f)\u000bg/\u0019*E\tB\u0011\u0001\fX\u0007\u00023*\u0011!lW\u0001\u0005Y\u0006twMC\u0001S\u0013\tA\u0014\fC\u0003;\t\u0001\u0007a\fE\u0002Q+rB3\u0001B\"JQ\r\u00011)Y\u0011\u0002E\u0006)\u0001G\f\u001d/a\u0005\u00192\t\\1tg&4\u0017nY1uS>tWj\u001c3fYB\u0011QMB\u0007\u0002\u0017M\u0019aaF4\u0011\u0005!\\W\"A5\u000b\u0005)\\\u0016AA5p\u0013\t9\u0013.\u0001\u0004=S:LGO\u0010\u000b\u0002I\u0006)r-\u001a;Ok64U-\u0019;ve\u0016\u001c8\t\\1tg\u0016\u001cHC\u00019w!\u0011A\u0012o]:\n\u0005IL\"A\u0002+va2,'\u0007\u0005\u0002\u0019i&\u0011Q/\u0007\u0002\u0004\u0013:$\b\"B<\t\u0001\u0004A\u0018\u0001C7fi\u0006$\u0017\r^1\u0011\u0005edX\"\u0001>\u000b\u0005m\u001c\u0012A\u00026t_:$4/\u0003\u0002~u\n1!JV1mk\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0001\u0011\u0007a\u000b\u0019!C\u0002\u0002\u0006e\u0013aa\u00142kK\u000e$\b"
)
public interface ClassificationModel extends Serializable {
   static Tuple2 getNumFeaturesClasses(final JValue metadata) {
      return ClassificationModel$.MODULE$.getNumFeaturesClasses(metadata);
   }

   RDD predict(final RDD testData);

   double predict(final Vector testData);

   // $FF: synthetic method
   static JavaRDD predict$(final ClassificationModel $this, final JavaRDD testData) {
      return $this.predict(testData);
   }

   default JavaRDD predict(final JavaRDD testData) {
      return this.predict(testData.rdd()).toJavaRDD();
   }

   static void $init$(final ClassificationModel $this) {
   }
}
