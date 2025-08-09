package org.apache.spark.ml.attribute;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.MetadataBuilder;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.Metadata.;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c!\u0002\r\u001a\u0003C!\u0003\"B\u001c\u0001\t\u0003A\u0004\"B\u001e\u0001\r\u0003a\u0004\"\u0002!\u0001\r\u0003\t\u0005\"B'\u0001\r\u0003q\u0005\"\u0002)\u0001\r\u0003\t\u0006\"\u0002*\u0001\r\u0003\u0019\u0006\"\u0002-\u0001\r\u0003I\u0006\"B.\u0001\r\u0003\t\u0006\"\u0002/\u0001\r\u0003i\u0006\"B1\u0001\r\u0003i\u0006B\u00022\u0001\r\u0003I2\r\u0003\u0004c\u0001\u0011\u0005\u0011D\u001c\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006_\u0002!\tA\u001c\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0006g\u0002!\t!\u001f\u0005\u0006u\u0002!\te_\u0004\b\u0003\u0017I\u0002\u0012AA\u0007\r\u0019A\u0012\u0004#\u0001\u0002\u0010!1qg\u0005C\u0001\u0003KA\u0001\"a\n\u0014\t\u0003J\u0012\u0011\u0006\u0005\b\u0003_\u0019B\u0011BA\u0019\u0011%\t)dEA\u0001\n\u0013\t9DA\u0005BiR\u0014\u0018NY;uK*\u0011!dG\u0001\nCR$(/\u001b2vi\u0016T!\u0001H\u000f\u0002\u00055d'B\u0001\u0010 \u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0013%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002E\u0005\u0019qN]4\u0004\u0001M\u0019\u0001!J\u0016\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g!\taCG\u0004\u0002.e9\u0011a&M\u0007\u0002_)\u0011\u0001gI\u0001\u0007yI|w\u000e\u001e \n\u0003!J!aM\u0014\u0002\u000fA\f7m[1hK&\u0011QG\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003g\u001d\na\u0001P5oSRtD#A\u001d\u0011\u0005i\u0002Q\"A\r\u0002\u0011\u0005$HO\u001d+za\u0016,\u0012!\u0010\t\u0003uyJ!aP\r\u0003\u001b\u0005#HO]5ckR,G+\u001f9f\u0003\u0011q\u0017-\\3\u0016\u0003\t\u00032AJ\"F\u0013\t!uE\u0001\u0004PaRLwN\u001c\t\u0003\r*s!a\u0012%\u0011\u00059:\u0013BA%(\u0003\u0019\u0001&/\u001a3fM&\u00111\n\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005%;\u0013\u0001C<ji\"t\u0015-\\3\u0015\u0005ez\u0005\"\u0002!\u0005\u0001\u0004)\u0015aC<ji\"|W\u000f\u001e(b[\u0016,\u0012!O\u0001\u0006S:$W\r_\u000b\u0002)B\u0019aeQ+\u0011\u0005\u00192\u0016BA,(\u0005\rIe\u000e^\u0001\no&$\b.\u00138eKb$\"!\u000f.\t\u000bI;\u0001\u0019A+\u0002\u0019]LG\u000f[8vi&sG-\u001a=\u0002\u0013%\u001ch*^7fe&\u001cW#\u00010\u0011\u0005\u0019z\u0016B\u00011(\u0005\u001d\u0011un\u001c7fC:\f\u0011\"[:O_6Lg.\u00197\u0002\u001dQ|W*\u001a;bI\u0006$\u0018-S7qYR\u0011A\r\u001c\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\fQ\u0001^=qKNT!![\u000f\u0002\u0007M\fH.\u0003\u0002lM\nAQ*\u001a;bI\u0006$\u0018\rC\u0003n\u0017\u0001\u0007a,\u0001\u0005xSRDG+\u001f9f)\u0005!\u0017A\u0003;p\u001b\u0016$\u0018\rZ1uCR\u0011A-\u001d\u0005\u0006e6\u0001\r\u0001Z\u0001\u0011KbL7\u000f^5oO6+G/\u00193bi\u0006\fQ\u0002^8TiJ,8\r\u001e$jK2$GCA;y!\t)g/\u0003\u0002xM\nY1\u000b\u001e:vGR4\u0015.\u001a7e\u0011\u0015\u0011x\u00021\u0001e)\u0005)\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0015Ks\u0001A?\u0000\u0003\u0007\t9!\u0003\u0002\u007f3\ty!)\u001b8bef\fE\u000f\u001e:jEV$X-C\u0002\u0002\u0002e\u0011\u0001CT8nS:\fG.\u0011;ue&\u0014W\u000f^3\n\u0007\u0005\u0015\u0011D\u0001\tOk6,'/[2BiR\u0014\u0018NY;uK*\u0019\u0011\u0011B\r\u0002'Us'/Z:pYZ,G-\u0011;ue&\u0014W\u000f^3\u0002\u0013\u0005#HO]5ckR,\u0007C\u0001\u001e\u0014'\u0019\u0019R%!\u0005\u0002\u0018A\u0019!(a\u0005\n\u0007\u0005U\u0011D\u0001\tBiR\u0014\u0018NY;uK\u001a\u000b7\r^8ssB!\u0011\u0011DA\u0012\u001b\t\tYB\u0003\u0003\u0002\u001e\u0005}\u0011AA5p\u0015\t\t\t#\u0001\u0003kCZ\f\u0017bA\u001b\u0002\u001cQ\u0011\u0011QB\u0001\rMJ|W.T3uC\u0012\fG/\u0019\u000b\u0004s\u0005-\u0002BBA\u0017+\u0001\u0007A-\u0001\u0005nKR\fG-\u0019;b\u0003)9W\r\u001e$bGR|'/\u001f\u000b\u0005\u0003#\t\u0019\u0004C\u0003<-\u0001\u0007Q)\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002:A!\u00111HA!\u001b\t\tiD\u0003\u0003\u0002@\u0005}\u0011\u0001\u00027b]\u001eLA!a\u0011\u0002>\t1qJ\u00196fGR\u0004"
)
public abstract class Attribute implements Serializable {
   public static Attribute fromStructField(final StructField field) {
      return Attribute$.MODULE$.fromStructField(field);
   }

   public abstract AttributeType attrType();

   public abstract Option name();

   public abstract Attribute withName(final String name);

   public abstract Attribute withoutName();

   public abstract Option index();

   public abstract Attribute withIndex(final int index);

   public abstract Attribute withoutIndex();

   public abstract boolean isNumeric();

   public abstract boolean isNominal();

   public abstract Metadata toMetadataImpl(final boolean withType);

   public Metadata toMetadataImpl() {
      AttributeType var10000 = this.attrType();
      AttributeType var1 = AttributeType$.MODULE$.Numeric();
      if (var10000 == null) {
         if (var1 == null) {
            return this.toMetadataImpl(false);
         }
      } else if (var10000.equals(var1)) {
         return this.toMetadataImpl(false);
      }

      return this.toMetadataImpl(true);
   }

   public Metadata toMetadata(final Metadata existingMetadata) {
      return (new MetadataBuilder()).withMetadata(existingMetadata).putMetadata(AttributeKeys$.MODULE$.ML_ATTR(), this.toMetadataImpl()).build();
   }

   public Metadata toMetadata() {
      return this.toMetadata(.MODULE$.empty());
   }

   public StructField toStructField(final Metadata existingMetadata) {
      Metadata newMetadata = (new MetadataBuilder()).withMetadata(existingMetadata).putMetadata(AttributeKeys$.MODULE$.ML_ATTR(), this.withoutName().withoutIndex().toMetadataImpl()).build();
      return new StructField((String)this.name().get(), org.apache.spark.sql.types.DoubleType..MODULE$, false, newMetadata);
   }

   public StructField toStructField() {
      return this.toStructField(.MODULE$.empty());
   }

   public String toString() {
      return this.toMetadataImpl(true).toString();
   }

   // $FF: synthetic method
   public static final void $anonfun$new$1(final String n) {
      scala.Predef..MODULE$.require(scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(n)), () -> "Cannot have an empty string for name.");
   }

   public Attribute() {
      this.name().foreach((n) -> {
         $anonfun$new$1(n);
         return BoxedUnit.UNIT;
      });
      this.index().foreach((JFunction1.mcVI.sp)(i) -> scala.Predef..MODULE$.require(i >= 0, () -> "Index cannot be negative but got " + i));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
