package org.apache.spark.ml.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.lang.invoke.SerializedLambda;
import javax.imageio.ImageIO;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;

public final class ImageSchema$ {
   public static final ImageSchema$ MODULE$ = new ImageSchema$();
   private static final String undefinedImageType = "Undefined";
   private static final Map ocvTypes;
   private static final java.util.Map javaOcvTypes;
   private static final StructType columnSchema;
   private static final String[] imageFields;
   private static final StructType imageSchema;

   static {
      ocvTypes = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.undefinedImageType()), BoxesRunTime.boxToInteger(-1)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("CV_8U"), BoxesRunTime.boxToInteger(0)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("CV_8UC1"), BoxesRunTime.boxToInteger(0)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("CV_8UC3"), BoxesRunTime.boxToInteger(16)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("CV_8UC4"), BoxesRunTime.boxToInteger(24))})));
      javaOcvTypes = scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(MODULE$.ocvTypes()).asJava();
      columnSchema = new StructType((StructField[])((Object[])(new StructField[]{new StructField("origin", org.apache.spark.sql.types.StringType..MODULE$, true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("height", org.apache.spark.sql.types.IntegerType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("width", org.apache.spark.sql.types.IntegerType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("nChannels", org.apache.spark.sql.types.IntegerType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("mode", org.apache.spark.sql.types.IntegerType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("data", org.apache.spark.sql.types.BinaryType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
      imageFields = MODULE$.columnSchema().fieldNames();
      imageSchema = new StructType((StructField[])((Object[])(new StructField[]{new StructField("image", MODULE$.columnSchema(), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
   }

   public String undefinedImageType() {
      return undefinedImageType;
   }

   public Map ocvTypes() {
      return ocvTypes;
   }

   public java.util.Map javaOcvTypes() {
      return javaOcvTypes;
   }

   public StructType columnSchema() {
      return columnSchema;
   }

   public String[] imageFields() {
      return imageFields;
   }

   public StructType imageSchema() {
      return imageSchema;
   }

   public String getOrigin(final Row row) {
      return row.getString(0);
   }

   public int getHeight(final Row row) {
      return row.getInt(1);
   }

   public int getWidth(final Row row) {
      return row.getInt(2);
   }

   public int getNChannels(final Row row) {
      return row.getInt(3);
   }

   public int getMode(final Row row) {
      return row.getInt(4);
   }

   public byte[] getData(final Row row) {
      return (byte[])row.getAs(5);
   }

   public Row invalidImageRow(final String origin) {
      return org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{origin, BoxesRunTime.boxToInteger(-1), BoxesRunTime.boxToInteger(-1), BoxesRunTime.boxToInteger(-1), this.ocvTypes().apply(this.undefinedImageType()), scala.Array..MODULE$.ofDim(0, scala.reflect.ClassTag..MODULE$.Byte())}))}));
   }

   public Option decode(final String origin, final byte[] bytes) {
      BufferedImage var10000;
      try {
         var10000 = ImageIO.read(new ByteArrayInputStream(bytes));
      } catch (Throwable var20) {
         var10000 = null;
      }

      BufferedImage img = var10000;
      if (img == null) {
         return scala.None..MODULE$;
      } else {
         boolean isGray = img.getColorModel().getColorSpace().getType() == 6;
         boolean hasAlpha = img.getColorModel().hasAlpha();
         int height = img.getHeight();
         int width = img.getWidth();
         Tuple2.mcII.sp var10 = isGray ? new Tuple2.mcII.sp(1, BoxesRunTime.unboxToInt(this.ocvTypes().apply("CV_8UC1"))) : (hasAlpha ? new Tuple2.mcII.sp(4, BoxesRunTime.unboxToInt(this.ocvTypes().apply("CV_8UC4"))) : new Tuple2.mcII.sp(3, BoxesRunTime.unboxToInt(this.ocvTypes().apply("CV_8UC3"))));
         if (var10 != null) {
            int nChannels = ((Tuple2)var10)._1$mcI$sp();
            int mode = ((Tuple2)var10)._2$mcI$sp();
            Tuple2.mcII.sp var9 = new Tuple2.mcII.sp(nChannels, mode);
            int nChannels = ((Tuple2)var9)._1$mcI$sp();
            int mode = ((Tuple2)var9)._2$mcI$sp();
            int imageSize = height * width * nChannels;
            .MODULE$.assert((double)imageSize < (double)1.0E9F, () -> "image is too large");
            byte[] decoded = (byte[])scala.Array..MODULE$.ofDim(imageSize, scala.reflect.ClassTag..MODULE$.Byte());
            if (isGray) {
               IntRef offset = IntRef.create(0);
               WritableRaster raster = img.getRaster();
               scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), height).foreach$mVc$sp((JFunction1.mcVI.sp)(h) -> scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), width).foreach$mVc$sp((JFunction1.mcVI.sp)(w) -> {
                     decoded[offset.elem] = (byte)raster.getSample(w, h, 0);
                     ++offset.elem;
                  }));
            } else {
               IntRef offset = IntRef.create(0);
               scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), height).foreach$mVc$sp((JFunction1.mcVI.sp)(h) -> scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), width).foreach$mVc$sp((JFunction1.mcVI.sp)(w) -> {
                     Color color = new Color(img.getRGB(w, h), hasAlpha);
                     decoded[offset.elem] = (byte)color.getBlue();
                     decoded[offset.elem + 1] = (byte)color.getGreen();
                     decoded[offset.elem + 2] = (byte)color.getRed();
                     if (hasAlpha) {
                        decoded[offset.elem + 3] = (byte)color.getAlpha();
                     }

                     offset.elem += nChannels;
                  }));
            }

            return new Some(org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{origin, BoxesRunTime.boxToInteger(height), BoxesRunTime.boxToInteger(width), BoxesRunTime.boxToInteger(nChannels), BoxesRunTime.boxToInteger(mode), decoded}))})));
         } else {
            throw new MatchError(var10);
         }
      }
   }

   private ImageSchema$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
