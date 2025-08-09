package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class StringIndexer$ implements DefaultParamsReadable, Serializable {
   public static final StringIndexer$ MODULE$ = new StringIndexer$();
   private static final String SKIP_INVALID;
   private static final String ERROR_INVALID;
   private static final String KEEP_INVALID;
   private static final String[] supportedHandleInvalids;
   private static final String frequencyDesc;
   private static final String frequencyAsc;
   private static final String alphabetDesc;
   private static final String alphabetAsc;
   private static final String[] supportedStringOrderType;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      SKIP_INVALID = "skip";
      ERROR_INVALID = "error";
      KEEP_INVALID = "keep";
      supportedHandleInvalids = (String[])((Object[])(new String[]{MODULE$.SKIP_INVALID(), MODULE$.ERROR_INVALID(), MODULE$.KEEP_INVALID()}));
      frequencyDesc = "frequencyDesc";
      frequencyAsc = "frequencyAsc";
      alphabetDesc = "alphabetDesc";
      alphabetAsc = "alphabetAsc";
      supportedStringOrderType = (String[])((Object[])(new String[]{MODULE$.frequencyDesc(), MODULE$.frequencyAsc(), MODULE$.alphabetDesc(), MODULE$.alphabetAsc()}));
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public String SKIP_INVALID() {
      return SKIP_INVALID;
   }

   public String ERROR_INVALID() {
      return ERROR_INVALID;
   }

   public String KEEP_INVALID() {
      return KEEP_INVALID;
   }

   public String[] supportedHandleInvalids() {
      return supportedHandleInvalids;
   }

   public String frequencyDesc() {
      return frequencyDesc;
   }

   public String frequencyAsc() {
      return frequencyAsc;
   }

   public String alphabetDesc() {
      return alphabetDesc;
   }

   public String alphabetAsc() {
      return alphabetAsc;
   }

   public String[] supportedStringOrderType() {
      return supportedStringOrderType;
   }

   public StringIndexer load(final String path) {
      return (StringIndexer)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringIndexer$.class);
   }

   private StringIndexer$() {
   }
}
