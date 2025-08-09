package org.apache.spark.io;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.util.Locale;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;

public final class CompressionCodec$ {
   public static final CompressionCodec$ MODULE$ = new CompressionCodec$();
   private static final String LZ4 = "lz4";
   private static final String LZF = "lzf";
   private static final String SNAPPY = "snappy";
   private static final String ZSTD = "zstd";
   private static final Map shortCompressionCodecNames;
   private static final String FALLBACK_COMPRESSION_CODEC;
   private static final Seq ALL_COMPRESSION_CODECS;

   static {
      shortCompressionCodecNames = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.LZ4()), LZ4CompressionCodec.class.getName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.LZF()), LZFCompressionCodec.class.getName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.SNAPPY()), SnappyCompressionCodec.class.getName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.ZSTD()), ZStdCompressionCodec.class.getName())})));
      FALLBACK_COMPRESSION_CODEC = MODULE$.SNAPPY();
      ALL_COMPRESSION_CODECS = MODULE$.shortCompressionCodecNames().values().toSeq();
   }

   public boolean supportsConcatenationOfSerializedStreams(final CompressionCodec codec) {
      return codec instanceof SnappyCompressionCodec || codec instanceof LZFCompressionCodec || codec instanceof LZ4CompressionCodec || codec instanceof ZStdCompressionCodec;
   }

   public String LZ4() {
      return LZ4;
   }

   public String LZF() {
      return LZF;
   }

   public String SNAPPY() {
      return SNAPPY;
   }

   public String ZSTD() {
      return ZSTD;
   }

   public Map shortCompressionCodecNames() {
      return shortCompressionCodecNames;
   }

   public String getCodecName(final SparkConf conf) {
      return (String)conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_COMPRESSION_CODEC());
   }

   public CompressionCodec createCodec(final SparkConf conf) {
      return this.createCodec(conf, this.getCodecName(conf));
   }

   public CompressionCodec createCodec(final SparkConf conf, final String codecName) {
      String codecClass = (String)this.shortCompressionCodecNames().getOrElse(codecName.toLowerCase(Locale.ROOT), () -> codecName);

      Object var10000;
      try {
         Constructor ctor = Utils$.MODULE$.classForName(codecClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()).getConstructor(SparkConf.class);
         var10000 = new Some(ctor.newInstance(conf));
      } catch (Throwable var10) {
         if (!(var10 instanceof ClassNotFoundException ? true : var10 instanceof IllegalArgumentException)) {
            throw var10;
         }

         var10000 = scala.None..MODULE$;
      }

      Option codec = (Option)var10000;
      return (CompressionCodec)codec.getOrElse(() -> {
         throw SparkCoreErrors$.MODULE$.codecNotAvailableError(codecName);
      });
   }

   public String getShortName(final String codecName) {
      String lowercasedCodec = codecName.toLowerCase(Locale.ROOT);
      return this.shortCompressionCodecNames().contains(lowercasedCodec) ? lowercasedCodec : (String)this.shortCompressionCodecNames().collectFirst(new Serializable(codecName) {
         private static final long serialVersionUID = 0L;
         private final String codecName$2;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            if (x1 != null) {
               String k = (String)x1._1();
               String v = (String)x1._2();
               String var7 = this.codecName$2;
               if (v == null) {
                  if (var7 == null) {
                     return k;
                  }
               } else if (v.equals(var7)) {
                  return k;
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            if (x1 != null) {
               String v = (String)x1._2();
               String var5 = this.codecName$2;
               if (v == null) {
                  if (var5 == null) {
                     return true;
                  }
               } else if (v.equals(var5)) {
                  return true;
               }
            }

            return false;
         }

         public {
            this.codecName$2 = codecName$2;
         }
      }).getOrElse(() -> {
         throw new SparkIllegalArgumentException("CODEC_SHORT_NAME_NOT_FOUND", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("codecName"), codecName)}))));
      });
   }

   public String FALLBACK_COMPRESSION_CODEC() {
      return FALLBACK_COMPRESSION_CODEC;
   }

   public Seq ALL_COMPRESSION_CODECS() {
      return ALL_COMPRESSION_CODECS;
   }

   private CompressionCodec$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
