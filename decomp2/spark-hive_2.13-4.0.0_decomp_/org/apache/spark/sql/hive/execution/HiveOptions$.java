package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.orc.OrcConf;
import org.apache.spark.sql.execution.datasources.orc.OrcOptions;
import org.apache.spark.sql.execution.datasources.parquet.ParquetOptions;
import org.apache.spark.sql.internal.SQLConf;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.mutable.Set;
import scala.collection.mutable.Set.;
import scala.runtime.ModuleSerializationProxy;

public final class HiveOptions$ implements Serializable {
   public static final HiveOptions$ MODULE$ = new HiveOptions$();
   private static final Set org$apache$spark$sql$hive$execution$HiveOptions$$lowerCasedOptionNames;
   private static final String FILE_FORMAT;
   private static final String INPUT_FORMAT;
   private static final String OUTPUT_FORMAT;
   private static final String SERDE;
   private static final Map delimiterOptions;

   static {
      org$apache$spark$sql$hive$execution$HiveOptions$$lowerCasedOptionNames = (Set).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      FILE_FORMAT = MODULE$.newOption("fileFormat");
      INPUT_FORMAT = MODULE$.newOption("inputFormat");
      OUTPUT_FORMAT = MODULE$.newOption("outputFormat");
      SERDE = MODULE$.newOption("serde");
      delimiterOptions = (Map)((MapOps)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fieldDelim"), "field.delim"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("escapeDelim"), "escape.delim"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("collectionDelim"), "colelction.delim"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("mapkeyDelim"), "mapkey.delim"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("lineDelim"), "line.delim")})))).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k.toLowerCase(Locale.ROOT)), v);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Set org$apache$spark$sql$hive$execution$HiveOptions$$lowerCasedOptionNames() {
      return org$apache$spark$sql$hive$execution$HiveOptions$$lowerCasedOptionNames;
   }

   private String newOption(final String name) {
      this.org$apache$spark$sql$hive$execution$HiveOptions$$lowerCasedOptionNames().$plus$eq(name.toLowerCase(Locale.ROOT));
      return name;
   }

   public String FILE_FORMAT() {
      return FILE_FORMAT;
   }

   public String INPUT_FORMAT() {
      return INPUT_FORMAT;
   }

   public String OUTPUT_FORMAT() {
      return OUTPUT_FORMAT;
   }

   public String SERDE() {
      return SERDE;
   }

   public Map delimiterOptions() {
      return delimiterOptions;
   }

   public Option getHiveWriteCompression(final TableDesc tableInfo, final SQLConf sqlConf) {
      Map tableProps = scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(tableInfo.getProperties()).asScala().toMap(scala..less.colon.less..MODULE$.refl());
      String var5 = tableInfo.getOutputFileFormatClassName().toLowerCase(Locale.ROOT);
      switch (var5 == null ? 0 : var5.hashCode()) {
         default:
            if (var5.endsWith("parquetoutputformat")) {
               String compressionCodec = (new ParquetOptions(tableProps, sqlConf)).compressionCodecClassName();
               return scala.Option..MODULE$.apply(new Tuple2("parquet.compression", compressionCodec));
            } else if (var5.endsWith("orcoutputformat")) {
               String compressionCodec = (new OrcOptions(tableProps, sqlConf)).compressionCodec();
               return scala.Option..MODULE$.apply(new Tuple2(OrcConf.COMPRESS.getAttribute(), compressionCodec));
            } else {
               return scala.None..MODULE$;
            }
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HiveOptions$.class);
   }

   private HiveOptions$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
