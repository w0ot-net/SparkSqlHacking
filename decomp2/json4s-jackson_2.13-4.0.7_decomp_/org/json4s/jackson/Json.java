package org.json4s.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Writer;
import org.json4s.AsJsonInput;
import org.json4s.Formats;
import org.json4s.JValue;
import org.json4s.JsonUtil;
import org.json4s.Reader;
import org.json4s.prefs.EmptyValueStrategy;
import scala.Option;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=u!\u0002\f\u0018\u0011\u0003qb!\u0002\u0011\u0018\u0011\u0003\t\u0003\"\u0002\u0015\u0002\t\u0003Ic\u0001\u0002\u0016\u0002\t-B\u0001bL\u0002\u0003\u0006\u0004%\t\u0005\r\u0005\ty\r\u0011\t\u0011)A\u0005c!)\u0001f\u0001C\u0001{!)\u0011)\u0001C\u0001\u0005\"I\u0011QO\u0001\u0012\u0002\u0013\u0005\u0011q\u000f\u0005\n\u0003\u001b\u000b\u0011\u0013!C\u0001\u0003o2A\u0001I\f\u0001\t\"A\u0011J\u0003B\u0001B\u0003%!\n\u0003\u00050\u0015\t\u0005\t\u0015!\u00032\u0011\u0015A#\u0002\"\u0001N\u0011\u0019\u0001&\u0002)A\u0005Y!)\u0011K\u0003C\u0001%\")\u0011K\u0003C\u0001_\"9\u0011Q\u0002\u0006\u0005\u0002\u0005=\u0001bBA\u0007\u0015\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003SQA\u0011AA\u0016\u0011\u001d\t\tF\u0003C\u0001\u0003'Bq!a\u001b\u000b\t\u0003\ti'\u0001\u0003Kg>t'B\u0001\r\u001a\u0003\u001dQ\u0017mY6t_:T!AG\u000e\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005a\u0012aA8sO\u000e\u0001\u0001CA\u0010\u0002\u001b\u00059\"\u0001\u0002&t_:\u001c\"!\u0001\u0012\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\taDA\u0006Vi&dW*\u001a;i_\u0012\u001c8cA\u0002#YA\u0011q$L\u0005\u0003]]\u00111BS:p]6+G\u000f[8eg\u00061Q.\u00199qKJ,\u0012!\r\t\u0003eij\u0011a\r\u0006\u0003iU\n\u0001\u0002Z1uC\nLg\u000e\u001a\u0006\u00031YR!a\u000e\u001d\u0002\u0013\u0019\f7\u000f^3sq6d'\"A\u001d\u0002\u0007\r|W.\u0003\u0002<g\taqJ\u00196fGRl\u0015\r\u001d9fe\u00069Q.\u00199qKJ\u0004CC\u0001 A!\ty4!D\u0001\u0002\u0011\u0015yc\u00011\u00012\u0003\u0015\t\u0007\u000f\u001d7z)\u0015\u0019\u0015\u0011OA:!\ty\"b\u0005\u0002\u000b\u000bB\u0011aiR\u0007\u00023%\u0011\u0001*\u0007\u0002\t\u0015N|g.\u0016;jY\u0006!a-\u001c;t!\t15*\u0003\u0002M3\t9ai\u001c:nCR\u001cHcA\"O\u001f\")\u0011*\u0004a\u0001\u0015\"9q&\u0004I\u0001\u0002\u0004\t\u0014\u0001B7fi\"\fQa\u001e:ji\u0016,\"aU4\u0015\u0005QkGCA+a!\t1VL\u0004\u0002X7B\u0011\u0001\fJ\u0007\u00023*\u0011!,H\u0001\u0007yI|w\u000e\u001e \n\u0005q#\u0013A\u0002)sK\u0012,g-\u0003\u0002_?\n11\u000b\u001e:j]\u001eT!\u0001\u0018\u0013\t\u000f\u0005|\u0011\u0011!a\u0002E\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007Y\u001bW-\u0003\u0002e?\nAQ*\u00198jM\u0016\u001cH\u000f\u0005\u0002gO2\u0001A!\u00025\u0010\u0005\u0004I'!A!\u0012\u0005)\u0014\u0003CA\u0012l\u0013\taGEA\u0004O_RD\u0017N\\4\t\u000b9|\u0001\u0019A3\u0002\u0003\u0005,B\u0001]A\u0003gR)\u0011/a\u0002\u0002\nQ\u0011!O \t\u0003MN$Q\u0001\u001e\tC\u0002U\u0014\u0011aV\t\u0003UZ\u0004\"a\u001e?\u000e\u0003aT!!\u001f>\u0002\u0005%|'\"A>\u0002\t)\fg/Y\u0005\u0003{b\u0014aa\u0016:ji\u0016\u0014\b\u0002C@\u0011\u0003\u0003\u0005\u001d!!\u0001\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u0003WG\u0006\r\u0001c\u00014\u0002\u0006\u0011)\u0001\u000e\u0005b\u0001S\"1a\u000e\u0005a\u0001\u0003\u0007Aa!a\u0003\u0011\u0001\u0004\u0011\u0018aA8vi\u0006YqO]5uKB\u0013X\r\u001e;z+\u0011\t\t\"a\u0006\u0015\u0007U\u000b\u0019\u0002\u0003\u0004o#\u0001\u0007\u0011Q\u0003\t\u0004M\u0006]A!\u00025\u0012\u0005\u0004IWCBA\u000e\u0003K\ty\u0002\u0006\u0004\u0002\u001e\u0005\u0005\u0012q\u0005\t\u0004M\u0006}A!\u0002;\u0013\u0005\u0004)\bB\u00028\u0013\u0001\u0004\t\u0019\u0003E\u0002g\u0003K!Q\u0001\u001b\nC\u0002%Dq!a\u0003\u0013\u0001\u0004\ti\"A\u0003qCJ\u001cX-\u0006\u0003\u0002.\u0005\rC\u0003BA\u0018\u0003\u001b\"B!!\r\u00028A\u0019a)a\r\n\u0007\u0005U\u0012D\u0001\u0004K-\u0006dW/\u001a\u0005\n\u0003s\u0019\u0012\u0011!a\u0002\u0003w\t!\"\u001a<jI\u0016t7-\u001a\u00134!\u00151\u0015QHA!\u0013\r\ty$\u0007\u0002\f\u0003NT5o\u001c8J]B,H\u000fE\u0002g\u0003\u0007\"a\u0001[\nC\u0002\u0005\u0015\u0013c\u00016\u0002HA\u00191%!\u0013\n\u0007\u0005-CEA\u0002B]fDq!a\u0014\u0014\u0001\u0004\t\t%\u0001\u0003kg>t\u0017\u0001\u00039beN,w\n\u001d;\u0016\t\u0005U\u0013q\r\u000b\u0005\u0003/\nI\u0007\u0006\u0003\u0002Z\u0005}\u0003#B\u0012\u0002\\\u0005E\u0012bAA/I\t1q\n\u001d;j_:D\u0011\"!\u0019\u0015\u0003\u0003\u0005\u001d!a\u0019\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007E\u0003G\u0003{\t)\u0007E\u0002g\u0003O\"a\u0001\u001b\u000bC\u0002\u0005\u0015\u0003bBA()\u0001\u0007\u0011QM\u0001\fo&$\bNR8s[\u0006$8\u000fF\u0002F\u0003_BQ!S\u000bA\u0002)CQ!S\u0004A\u0002)CqaL\u0004\u0011\u0002\u0003\u0007\u0011'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tIHK\u00022\u0003wZ#!! \u0011\t\u0005}\u0014\u0011R\u0007\u0003\u0003\u0003SA!a!\u0002\u0006\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u000f#\u0013AC1o]>$\u0018\r^5p]&!\u00111RAA\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a"
)
public class Json extends JsonUtil {
   private final Formats fmts;
   private final ObjectMapper mapper;
   private final JsonMethods meth;

   public static ObjectMapper $lessinit$greater$default$2() {
      return Json$.MODULE$.$lessinit$greater$default$2();
   }

   public static ObjectMapper apply$default$2() {
      return Json$.MODULE$.apply$default$2();
   }

   public static Json apply(final Formats fmts, final ObjectMapper mapper) {
      return Json$.MODULE$.apply(fmts, mapper);
   }

   public String write(final Object a, final Manifest evidence$1) {
      return this.mapper.writeValueAsString(this.decompose(a));
   }

   public Writer write(final Object a, final Writer out, final Manifest evidence$2) {
      this.mapper.writeValue(out, this.decompose(a));
      return out;
   }

   public String writePretty(final Object a) {
      return this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.decompose(a));
   }

   public Writer writePretty(final Object a, final Writer out) {
      this.mapper.writerWithDefaultPrettyPrinter().writeValue(out, this.decompose(a));
      return out;
   }

   public JValue parse(final Object json, final AsJsonInput evidence$3) {
      return this.meth.parse(json, this.fmts.wantsBigDecimal(), this.fmts.wantsBigInt(), evidence$3);
   }

   public Option parseOpt(final Object json, final AsJsonInput evidence$4) {
      return this.meth.parseOpt(json, this.fmts.wantsBigDecimal(), this.fmts.wantsBigInt(), evidence$4);
   }

   public JsonUtil withFormats(final Formats fmts) {
      return new Json(fmts, this.mapper);
   }

   public Json(final Formats fmts, final ObjectMapper mapper) {
      super(fmts);
      this.fmts = fmts;
      this.mapper = mapper;
      this.meth = new UtilMethods(mapper);
   }

   private static class UtilMethods implements JsonMethods {
      private final ObjectMapper mapper;
      private ObjectMapper org$json4s$jackson$JsonMethods$$_defaultMapper;
      private volatile boolean bitmap$0;

      public JValue parse(final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$1) {
         return JsonMethods.parse$(this, in, useBigDecimalForDouble, useBigIntForLong, evidence$1);
      }

      public boolean parse$default$2() {
         return JsonMethods.parse$default$2$(this);
      }

      public boolean parse$default$3() {
         return JsonMethods.parse$default$3$(this);
      }

      public Option parseOpt(final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$2) {
         return JsonMethods.parseOpt$(this, in, useBigDecimalForDouble, useBigIntForLong, evidence$2);
      }

      public boolean parseOpt$default$2() {
         return JsonMethods.parseOpt$default$2$(this);
      }

      public boolean parseOpt$default$3() {
         return JsonMethods.parseOpt$default$3$(this);
      }

      public JValue render(final JValue value, final boolean alwaysEscapeUnicode, final EmptyValueStrategy emptyValueStrategy) {
         return JsonMethods.render$(this, value, alwaysEscapeUnicode, emptyValueStrategy);
      }

      public boolean render$default$2() {
         return JsonMethods.render$default$2$(this);
      }

      public EmptyValueStrategy render$default$3() {
         return JsonMethods.render$default$3$(this);
      }

      public String compact(final JValue d) {
         return JsonMethods.compact$(this, d);
      }

      public String pretty(final JValue d) {
         return JsonMethods.pretty$(this, d);
      }

      public JValue asJValue(final Object obj, final org.json4s.Writer writer) {
         return JsonMethods.asJValue$(this, obj, writer);
      }

      public Object fromJValue(final JValue json, final Reader reader) {
         return JsonMethods.fromJValue$(this, json, reader);
      }

      public JsonNode asJsonNode(final JValue jv) {
         return JsonMethods.asJsonNode$(this, jv);
      }

      public JValue fromJsonNode(final JsonNode jn) {
         return JsonMethods.fromJsonNode$(this, jn);
      }

      private ObjectMapper org$json4s$jackson$JsonMethods$$_defaultMapper$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.org$json4s$jackson$JsonMethods$$_defaultMapper = JsonMethods.org$json4s$jackson$JsonMethods$$_defaultMapper$(this);
               this.bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.org$json4s$jackson$JsonMethods$$_defaultMapper;
      }

      public ObjectMapper org$json4s$jackson$JsonMethods$$_defaultMapper() {
         return !this.bitmap$0 ? this.org$json4s$jackson$JsonMethods$$_defaultMapper$lzycompute() : this.org$json4s$jackson$JsonMethods$$_defaultMapper;
      }

      public ObjectMapper mapper() {
         return this.mapper;
      }

      public UtilMethods(final ObjectMapper mapper) {
         this.mapper = mapper;
         JsonMethods.$init$(this);
      }
   }
}
