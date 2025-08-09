package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import scala.collection.Map;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3Aa\u0003\u0007\u00053!)1\u0005\u0001C\u0001I!Iq\u0005\u0001a\u0001\u0002\u0004%I\u0001\u000b\u0005\ne\u0001\u0001\r\u00111A\u0005\nMB\u0011\"\u000f\u0001A\u0002\u0003\u0005\u000b\u0015B\u0015\t\u0013i\u0002\u0001\u0019!a\u0001\n\u0013A\u0003\"C\u001e\u0001\u0001\u0004\u0005\r\u0011\"\u0003=\u0011%q\u0004\u00011A\u0001B\u0003&\u0011\u0006C\u0003@\u0001\u0011\u0005\u0003\tC\u0003G\u0001\u0011\u0005s\tC\u0003R\u0001\u0011\u0005#K\u0001\u0010V]RL\b/\u001a3TG\u0006d\u0017m\u00142kK\u000e$H)Z:fe&\fG.\u001b>fe*\u0011QBD\u0001\u0006I\u0016\u001cXM\u001d\u0006\u0003\u001fA\tQa]2bY\u0006T!!\u0005\n\u0002\r5|G-\u001e7f\u0015\t\u0019B#A\u0004kC\u000e\\7o\u001c8\u000b\u0005U1\u0012!\u00034bgR,'\u000f_7m\u0015\u00059\u0012aA2p[\u000e\u00011C\u0001\u0001\u001b!\tY\u0012%D\u0001\u001d\u0015\tib$A\u0002ti\u0012T!!D\u0010\u000b\u0005\u0001\u0012\u0012\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005\tb\"!G+oif\u0004X\rZ(cU\u0016\u001cG\u000fR3tKJL\u0017\r\\5{KJ\fa\u0001P5oSRtD#A\u0013\u0011\u0005\u0019\u0002Q\"\u0001\u0007\u0002\u0013}k\u0017\r\u001d#fg\u0016\u0014X#A\u0015\u0011\u0007)ZS&D\u0001 \u0013\tasD\u0001\tKg>tG)Z:fe&\fG.\u001b>feB\u0011a\u0006M\u0007\u0002_)\tq\"\u0003\u00022_\t1\u0011I\\=SK\u001a\fQbX7ba\u0012+7/\u001a:`I\u0015\fHC\u0001\u001b8!\tqS'\u0003\u00027_\t!QK\\5u\u0011\u001dA4!!AA\u0002%\n1\u0001\u001f\u00132\u0003)yV.\u00199EKN,'\u000fI\u0001\u000b?2L7\u000f\u001e#fg\u0016\u0014\u0018AD0mSN$H)Z:fe~#S-\u001d\u000b\u0003iuBq\u0001\u000f\u0004\u0002\u0002\u0003\u0007\u0011&A\u0006`Y&\u001cH\u000fR3tKJ\u0004\u0013a\u0002:fg>dg/\u001a\u000b\u0003i\u0005CQA\u0011\u0005A\u0002\r\u000bAa\u0019;yiB\u0011!\u0006R\u0005\u0003\u000b~\u0011a\u0003R3tKJL\u0017\r\\5{CRLwN\\\"p]R,\u0007\u0010^\u0001\t[\u0006\u0004\u0018I\u001d:bsR\u0019Q\u0006\u0013)\t\u000b%K\u0001\u0019\u0001&\u0002\u0005)\u0004\bCA&O\u001b\u0005a%BA'\u0013\u0003\u0011\u0019wN]3\n\u0005=c%A\u0003&t_:\u0004\u0016M]:fe\")!)\u0003a\u0001\u0007\u0006IQ.\u00199PE*,7\r\u001e\u000b\u0004[M#\u0006\"B%\u000b\u0001\u0004Q\u0005\"\u0002\"\u000b\u0001\u0004\u0019\u0005"
)
public class UntypedScalaObjectDeserializer extends UntypedObjectDeserializer {
   private JsonDeserializer _mapDeser;
   private JsonDeserializer _listDeser;

   private JsonDeserializer _mapDeser() {
      return this._mapDeser;
   }

   private void _mapDeser_$eq(final JsonDeserializer x$1) {
      this._mapDeser = x$1;
   }

   private JsonDeserializer _listDeser() {
      return this._listDeser;
   }

   private void _listDeser_$eq(final JsonDeserializer x$1) {
      this._listDeser = x$1;
   }

   public void resolve(final DeserializationContext ctxt) {
      super.resolve(ctxt);
      JavaType anyRef = ctxt.constructType(Object.class);
      JavaType string = ctxt.constructType(String.class);
      DeserializerFactory factory = ctxt.getFactory();
      TypeFactory tf = ctxt.getTypeFactory();
      this._mapDeser_$eq(ctxt.findRootValueDeserializer(factory.mapAbstractType(ctxt.getConfig(), tf.constructMapLikeType(Map.class, string, anyRef))));
      this._listDeser_$eq(ctxt.findRootValueDeserializer(factory.mapAbstractType(ctxt.getConfig(), tf.constructCollectionLikeType(Seq.class, anyRef))));
   }

   public Object mapArray(final JsonParser jp, final DeserializationContext ctxt) {
      return ctxt.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY) ? this.mapArrayToArray(jp, ctxt) : this._listDeser().deserialize(jp, ctxt);
   }

   public Object mapObject(final JsonParser jp, final DeserializationContext ctxt) {
      return this._mapDeser().deserialize(jp, ctxt);
   }

   public UntypedScalaObjectDeserializer() {
      super((JavaType)null, (JavaType)null);
   }
}
