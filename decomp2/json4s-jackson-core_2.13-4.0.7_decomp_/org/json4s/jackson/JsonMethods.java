package org.json4s.jackson;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.lang.invoke.SerializedLambda;
import org.json4s.AsJsonInput;
import org.json4s.FileInput;
import org.json4s.JValue;
import org.json4s.JsonInput;
import org.json4s.MappingException;
import org.json4s.ReaderInput;
import org.json4s.StreamInput;
import org.json4s.StringInput;
import org.json4s.Writer;
import org.json4s.AsJsonInput.;
import org.json4s.prefs.EmptyValueStrategy;
import scala.MatchError;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015fa\u0002\f\u0018!\u0003\r\tA\b\u0005\u0006W\u0001!\t\u0001\f\u0005\ta\u0001A)\u0019)C\u0005c!)Q\b\u0001C\u0001c!)a\b\u0001C\u0001\u007f!91\fAI\u0001\n\u0003a\u0006bB5\u0001#\u0003%\tA\u001b\u0005\u0006Y\u0002!\t!\u001c\u0005\bw\u0002\t\n\u0011\"\u0001}\u0011\u001dq\b!%A\u0005\u0002}Dq!a\u0001\u0001\t\u0003\t)\u0001\u0003\u0005\u0002 \u0001\t\n\u0011\"\u0001^\u0011%\t\t\u0003AI\u0001\n\u0003\t\u0019\u0003C\u0004\u0002(\u0001!\t!!\u000b\t\u000f\u0005\u0015\u0003\u0001\"\u0001\u0002H!9\u00111\n\u0001\u0005\u0002\u00055\u0003bBA4\u0001\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003\u0003\u0003A\u0011AAB\u0011\u001d\ty\t\u0001C\u0001\u0003#;q!a&\u0018\u0011\u0003\tIJ\u0002\u0004\u0017/!\u0005\u0011Q\u0014\u0005\b\u0003C#B\u0011AAR\u0005-Q5o\u001c8NKRDw\u000eZ:\u000b\u0005aI\u0012a\u00026bG.\u001cxN\u001c\u0006\u00035m\taA[:p]R\u001a(\"\u0001\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001yR\u0005\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VM\u001a\t\u0004M\u001dBS\"A\r\n\u0005YI\u0002C\u0001\u0014*\u0013\tQ\u0013D\u0001\u0004K-\u0006dW/Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00035\u0002\"\u0001\t\u0018\n\u0005=\n#\u0001B+oSR\fab\u00183fM\u0006,H\u000e^'baB,'/F\u00013!\t\u00194(D\u00015\u0015\t)d'\u0001\u0005eCR\f'-\u001b8e\u0015\tArG\u0003\u00029s\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002u\u0005\u00191m\\7\n\u0005q\"$\u0001D(cU\u0016\u001cG/T1qa\u0016\u0014\u0018AB7baB,'/A\u0003qCJ\u001cX-\u0006\u0002A\u0013R!\u0011I\u0015+Z)\tA#\tC\u0004D\t\u0005\u0005\t9\u0001#\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002'\u000b\u001eK!AR\r\u0003\u0017\u0005\u001b(j]8o\u0013:\u0004X\u000f\u001e\t\u0003\u0011&c\u0001\u0001B\u0003K\t\t\u00071JA\u0001B#\tau\n\u0005\u0002!\u001b&\u0011a*\t\u0002\b\u001d>$\b.\u001b8h!\t\u0001\u0003+\u0003\u0002RC\t\u0019\u0011I\\=\t\u000bM#\u0001\u0019A$\u0002\u0005%t\u0007bB+\u0005!\u0003\u0005\rAV\u0001\u0017kN,')[4EK\u000eLW.\u00197G_J$u.\u001e2mKB\u0011\u0001eV\u0005\u00031\u0006\u0012qAQ8pY\u0016\fg\u000eC\u0004[\tA\u0005\t\u0019\u0001,\u0002!U\u001cXMQ5h\u0013:$hi\u001c:M_:<\u0017a\u00049beN,G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005uCW#\u00010+\u0005Y{6&\u00011\u0011\u0005\u00054W\"\u00012\u000b\u0005\r$\u0017!C;oG\",7m[3e\u0015\t)\u0017%\u0001\u0006b]:|G/\u0019;j_:L!a\u001a2\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003K\u000b\t\u00071*A\bqCJ\u001cX\r\n3fM\u0006,H\u000e\u001e\u00134+\ti6\u000eB\u0003K\r\t\u00071*\u0001\u0005qCJ\u001cXm\u00149u+\tqw\u000f\u0006\u0003pqfTHC\u00019t!\r\u0001\u0013\u000fK\u0005\u0003e\u0006\u0012aa\u00149uS>t\u0007b\u0002;\b\u0003\u0003\u0005\u001d!^\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u0001\u0014FmB\u0011\u0001j\u001e\u0003\u0006\u0015\u001e\u0011\ra\u0013\u0005\u0006'\u001e\u0001\rA\u001e\u0005\b+\u001e\u0001\n\u00111\u0001W\u0011\u001dQv\u0001%AA\u0002Y\u000b!\u0003]1sg\u0016|\u0005\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%eU\u0011Q, \u0003\u0006\u0015\"\u0011\raS\u0001\u0013a\u0006\u00148/Z(qi\u0012\"WMZ1vYR$3'F\u0002^\u0003\u0003!QAS\u0005C\u0002-\u000baA]3oI\u0016\u0014Hc\u0002\u0015\u0002\b\u0005-\u0011q\u0002\u0005\u0007\u0003\u0013Q\u0001\u0019\u0001\u0015\u0002\u000bY\fG.^3\t\u0011\u00055!\u0002%AA\u0002Y\u000b1#\u00197xCf\u001cXi]2ba\u0016,f.[2pI\u0016D\u0011\"!\u0005\u000b!\u0003\u0005\r!a\u0005\u0002%\u0015l\u0007\u000f^=WC2,Xm\u0015;sCR,w-\u001f\t\u0005\u0003+\tY\"\u0004\u0002\u0002\u0018)\u0019\u0011\u0011D\r\u0002\u000bA\u0014XMZ:\n\t\u0005u\u0011q\u0003\u0002\u0013\u000b6\u0004H/\u001f,bYV,7\u000b\u001e:bi\u0016<\u00170\u0001\tsK:$WM\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005\u0001\"/\u001a8eKJ$C-\u001a4bk2$HeM\u000b\u0003\u0003KQ3!a\u0005`\u0003\u001d\u0019w.\u001c9bGR$B!a\u000b\u0002BA!\u0011QFA\u001e\u001d\u0011\ty#a\u000e\u0011\u0007\u0005E\u0012%\u0004\u0002\u00024)\u0019\u0011QG\u000f\u0002\rq\u0012xn\u001c;?\u0013\r\tI$I\u0001\u0007!J,G-\u001a4\n\t\u0005u\u0012q\b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005e\u0012\u0005\u0003\u0004\u0002D5\u0001\r\u0001K\u0001\u0002I\u00061\u0001O]3uif$B!a\u000b\u0002J!1\u00111\t\bA\u0002!\n\u0001\"Y:K-\u0006dW/Z\u000b\u0005\u0003\u001f\ny\u0006\u0006\u0003\u0002R\u0005\rDc\u0001\u0015\u0002T!9\u0011QK\bA\u0004\u0005]\u0013AB<sSR,'\u000fE\u0003'\u00033\ni&C\u0002\u0002\\e\u0011aa\u0016:ji\u0016\u0014\bc\u0001%\u0002`\u00111\u0011\u0011M\bC\u0002-\u0013\u0011\u0001\u0016\u0005\b\u0003Kz\u0001\u0019AA/\u0003\ry'M[\u0001\u000bMJ|WN\u0013,bYV,W\u0003BA6\u0003c\"B!!\u001c\u0002~Q!\u0011qNA:!\rA\u0015\u0011\u000f\u0003\u0007\u0003C\u0002\"\u0019A&\t\u000f\u0005U\u0004\u0003q\u0001\u0002x\u00051!/Z1eKJ\u0004RAJA=\u0003_J1!a\u001f\u001a\u0005\u0019\u0011V-\u00193fe\"1\u0011q\u0010\tA\u0002!\nAA[:p]\u0006Q\u0011m\u001d&t_:tu\u000eZ3\u0015\t\u0005\u0015\u00151\u0012\t\u0004g\u0005\u001d\u0015bAAEi\tA!j]8o\u001d>$W\r\u0003\u0004\u0002\u000eF\u0001\r\u0001K\u0001\u0003UZ\fAB\u001a:p[*\u001bxN\u001c(pI\u0016$2\u0001KAJ\u0011\u001d\t)J\u0005a\u0001\u0003\u000b\u000b!A\u001b8\u0002\u0017)\u001bxN\\'fi\"|Gm\u001d\t\u0004\u00037#R\"A\f\u0014\tQy\u0012q\u0014\t\u0004\u00037\u0003\u0011A\u0002\u001fj]&$h\b\u0006\u0002\u0002\u001a\u0002"
)
public interface JsonMethods extends org.json4s.JsonMethods {
   // $FF: synthetic method
   static ObjectMapper org$json4s$jackson$JsonMethods$$_defaultMapper$(final JsonMethods $this) {
      return $this.org$json4s$jackson$JsonMethods$$_defaultMapper();
   }

   default ObjectMapper org$json4s$jackson$JsonMethods$$_defaultMapper() {
      ObjectMapper m = new ObjectMapper();
      m.registerModule(new Json4sScalaModule());
      m.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true);
      return m;
   }

   // $FF: synthetic method
   static ObjectMapper mapper$(final JsonMethods $this) {
      return $this.mapper();
   }

   default ObjectMapper mapper() {
      return this.org$json4s$jackson$JsonMethods$$_defaultMapper();
   }

   // $FF: synthetic method
   static JValue parse$(final JsonMethods $this, final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$1) {
      return $this.parse(in, useBigDecimalForDouble, useBigIntForLong, evidence$1);
   }

   default JValue parse(final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$1) {
      ObjectReader reader = this.mapper().readerFor(JValue.class);
      if (useBigDecimalForDouble) {
         reader = reader.with(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
      } else {
         reader = reader.without(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
      }

      if (useBigIntForLong) {
         reader = reader.with(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS);
      } else {
         reader = reader.without(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS);
      }

      JsonInput var7 = .MODULE$.asJsonInput(in, evidence$1);
      JValue var5;
      if (var7 instanceof StringInput) {
         StringInput var8 = (StringInput)var7;
         String s = var8.string();
         var5 = (JValue)reader.readValue(s);
      } else if (var7 instanceof ReaderInput) {
         ReaderInput var10 = (ReaderInput)var7;
         Reader rdr = var10.reader();
         var5 = (JValue)reader.readValue(rdr);
      } else if (var7 instanceof StreamInput) {
         StreamInput var12 = (StreamInput)var7;
         InputStream stream = var12.stream();
         var5 = (JValue)reader.readValue(stream);
      } else {
         if (!(var7 instanceof FileInput)) {
            throw new MatchError(var7);
         }

         FileInput var14 = (FileInput)var7;
         File file = var14.file();
         var5 = (JValue)reader.readValue(file);
      }

      return var5;
   }

   // $FF: synthetic method
   static boolean parse$default$2$(final JsonMethods $this) {
      return $this.parse$default$2();
   }

   default boolean parse$default$2() {
      return false;
   }

   // $FF: synthetic method
   static boolean parse$default$3$(final JsonMethods $this) {
      return $this.parse$default$3();
   }

   default boolean parse$default$3() {
      return true;
   }

   // $FF: synthetic method
   static Option parseOpt$(final JsonMethods $this, final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$2) {
      return $this.parseOpt(in, useBigDecimalForDouble, useBigIntForLong, evidence$2);
   }

   default Option parseOpt(final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$2) {
      return scala.util.control.Exception..MODULE$.allCatch().opt(() -> this.parse(in, useBigDecimalForDouble, useBigIntForLong, evidence$2));
   }

   // $FF: synthetic method
   static boolean parseOpt$default$2$(final JsonMethods $this) {
      return $this.parseOpt$default$2();
   }

   default boolean parseOpt$default$2() {
      return false;
   }

   // $FF: synthetic method
   static boolean parseOpt$default$3$(final JsonMethods $this) {
      return $this.parseOpt$default$3();
   }

   default boolean parseOpt$default$3() {
      return true;
   }

   // $FF: synthetic method
   static JValue render$(final JsonMethods $this, final JValue value, final boolean alwaysEscapeUnicode, final EmptyValueStrategy emptyValueStrategy) {
      return $this.render(value, alwaysEscapeUnicode, emptyValueStrategy);
   }

   default JValue render(final JValue value, final boolean alwaysEscapeUnicode, final EmptyValueStrategy emptyValueStrategy) {
      if (this.mapper().isEnabled(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature()) != alwaysEscapeUnicode) {
         this.mapper().getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), alwaysEscapeUnicode);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return emptyValueStrategy.replaceEmpty(value);
   }

   // $FF: synthetic method
   static boolean render$default$2$(final JsonMethods $this) {
      return $this.render$default$2();
   }

   default boolean render$default$2() {
      return false;
   }

   // $FF: synthetic method
   static EmptyValueStrategy render$default$3$(final JsonMethods $this) {
      return $this.render$default$3();
   }

   default EmptyValueStrategy render$default$3() {
      return org.json4s.prefs.EmptyValueStrategy..MODULE$.default();
   }

   // $FF: synthetic method
   static String compact$(final JsonMethods $this, final JValue d) {
      return $this.compact(d);
   }

   default String compact(final JValue d) {
      return this.mapper().writeValueAsString(d);
   }

   // $FF: synthetic method
   static String pretty$(final JsonMethods $this, final JValue d) {
      return $this.pretty(d);
   }

   default String pretty(final JValue d) {
      ObjectWriter writer = this.mapper().writerWithDefaultPrettyPrinter();
      return writer.writeValueAsString(d);
   }

   // $FF: synthetic method
   static JValue asJValue$(final JsonMethods $this, final Object obj, final Writer writer) {
      return $this.asJValue(obj, writer);
   }

   default JValue asJValue(final Object obj, final Writer writer) {
      return writer.write(obj);
   }

   // $FF: synthetic method
   static Object fromJValue$(final JsonMethods $this, final JValue json, final org.json4s.Reader reader) {
      return $this.fromJValue(json, reader);
   }

   default Object fromJValue(final JValue json, final org.json4s.Reader reader) {
      Either var4 = reader.readEither(json);
      if (var4 instanceof Right) {
         Right var5 = (Right)var4;
         Object x = var5.value();
         return x;
      } else if (var4 instanceof Left) {
         Left var7 = (Left)var4;
         MappingException x = (MappingException)var7.value();
         throw x;
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   static JsonNode asJsonNode$(final JsonMethods $this, final JValue jv) {
      return $this.asJsonNode(jv);
   }

   default JsonNode asJsonNode(final JValue jv) {
      return this.mapper().valueToTree(jv);
   }

   // $FF: synthetic method
   static JValue fromJsonNode$(final JsonMethods $this, final JsonNode jn) {
      return $this.fromJsonNode(jn);
   }

   default JValue fromJsonNode(final JsonNode jn) {
      return (JValue)this.mapper().treeToValue(jn, JValue.class);
   }

   static void $init$(final JsonMethods $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
