package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.scala.DefaultScalaModule.;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import scala.reflect.ScalaSignature;

@Provider
@Produces({"application/json"})
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ee!B\u0006\r\u00011A\u0002\"B\u0017\u0001\t\u0003y\u0003b\u0002\u001a\u0001\u0005\u0004%\ta\r\u0005\u0007\u0001\u0002\u0001\u000b\u0011\u0002\u001b\t\u000b\u0005\u0003A\u0011\t\"\t\u000bu\u0004A\u0011\t@\t\u000f\u0005\u0005\u0003\u0001\"\u0011\u0002D\u001dA\u0011\u0011\u0010\u0007\t\u0002I\tYHB\u0004\f\u0019!\u0005!#! \t\r5BA\u0011AA@\u0011\u001d\t\t\t\u0003C\u0001\u0003\u0007\u0013ACS1dWN|g.T3tg\u0006<Wm\u0016:ji\u0016\u0014(BA\u0007\u000f\u0003\t1\u0018G\u0003\u0002\u0010!\u0005\u0019\u0011\r]5\u000b\u0005E\u0011\u0012AB:uCR,8O\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h'\r\u0001\u0011$\t\t\u00035}i\u0011a\u0007\u0006\u00039u\tA\u0001\\1oO*\ta$\u0001\u0003kCZ\f\u0017B\u0001\u0011\u001c\u0005\u0019y%M[3diB\u0019!eK\r\u000e\u0003\rR!\u0001J\u0013\u0002\u0007\u0015DHO\u0003\u0002'O\u0005\u0011!o\u001d\u0006\u0003Q%\n!a^:\u000b\u0003)\nqA[1lCJ$\u0018-\u0003\u0002-G\t\tR*Z:tC\u001e,'i\u001c3z/JLG/\u001a:\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001\r\t\u0003c\u0001i\u0011\u0001D\u0001\u0007[\u0006\u0004\b/\u001a:\u0016\u0003Q\u0002\"!\u000e \u000e\u0003YR!a\u000e\u001d\u0002\u0011\u0011\fG/\u00192j]\u0012T!!\u000f\u001e\u0002\u000f)\f7m[:p]*\u00111\bP\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011!P\u0001\u0004G>l\u0017BA 7\u00051y%M[3di6\u000b\u0007\u000f]3s\u0003\u001di\u0017\r\u001d9fe\u0002\n1\"[:Xe&$X-\u00192mKR)1)\u00132kkB\u0011AiR\u0007\u0002\u000b*\ta)A\u0003tG\u0006d\u0017-\u0003\u0002I\u000b\n9!i\\8mK\u0006t\u0007\"\u0002&\u0005\u0001\u0004Y\u0015AB1DY\u0006\u001c8\u000f\r\u0002M3B\u0019Q\nV,\u000f\u00059\u0013\u0006CA(F\u001b\u0005\u0001&BA)/\u0003\u0019a$o\\8u}%\u00111+R\u0001\u0007!J,G-\u001a4\n\u0005U3&!B\"mCN\u001c(BA*F!\tA\u0016\f\u0004\u0001\u0005\u0013iK\u0015\u0011!A\u0001\u0006\u0003Y&aA0%cE\u0011Al\u0018\t\u0003\tvK!AX#\u0003\u000f9{G\u000f[5oOB\u0011A\tY\u0005\u0003C\u0016\u00131!\u00118z\u0011\u0015\u0019G\u00011\u0001e\u0003\u0011!\u0018\u0010]3\u0011\u0005\u0015DW\"\u00014\u000b\u0005\u001d\\\u0012a\u0002:fM2,7\r^\u0005\u0003S\u001a\u0014A\u0001V=qK\")1\u000e\u0002a\u0001Y\u0006Y\u0011M\u001c8pi\u0006$\u0018n\u001c8t!\r!Un\\\u0005\u0003]\u0016\u0013Q!\u0011:sCf\u0004\"\u0001]:\u000e\u0003ET!A]\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002uc\nQ\u0011I\u001c8pi\u0006$\u0018n\u001c8\t\u000bY$\u0001\u0019A<\u0002\u00135,G-[1UsB,\u0007C\u0001=|\u001b\u0005I(B\u0001>&\u0003\u0011\u0019wN]3\n\u0005qL(!C'fI&\fG+\u001f9f\u0003\u001d9(/\u001b;f)>$rb`A\u0003\u0003\u0013\t)\"a\u0006\u0002\u001a\u0005m\u0011\u0011\u0007\t\u0004\t\u0006\u0005\u0011bAA\u0002\u000b\n!QK\\5u\u0011\u0019\t9!\u0002a\u00013\u0005\tA\u000f\u0003\u0004K\u000b\u0001\u0007\u00111\u0002\u0019\u0005\u0003\u001b\t\t\u0002\u0005\u0003N)\u0006=\u0001c\u0001-\u0002\u0012\u0011Y\u00111CA\u0005\u0003\u0003\u0005\tQ!\u0001\\\u0005\ryFE\r\u0005\u0006G\u0016\u0001\r\u0001\u001a\u0005\u0006W\u0016\u0001\r\u0001\u001c\u0005\u0006m\u0016\u0001\ra\u001e\u0005\b\u0003;)\u0001\u0019AA\u0010\u00039iW\u000f\u001c;jm\u0006dW/\u001a3NCB\u0004r\u0001_A\u0011\u0003K\tY#C\u0002\u0002$e\u0014a\"T;mi&4\u0018\r\\;fI6\u000b\u0007\u000fE\u0002N\u0003OI1!!\u000bW\u0005\u0019\u0019FO]5oOB\u0019A)!\f\n\u0007\u0005=RI\u0001\u0004B]f\u0014VM\u001a\u0005\b\u0003g)\u0001\u0019AA\u001b\u00031yW\u000f\u001e9viN#(/Z1n!\u0011\t9$!\u0010\u000e\u0005\u0005e\"bAA\u001e;\u0005\u0011\u0011n\\\u0005\u0005\u0003\u007f\tID\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW.A\u0004hKR\u001c\u0016N_3\u0015\u0019\u0005\u0015\u00131JA'\u00033\nY&!\u0018\u0011\u0007\u0011\u000b9%C\u0002\u0002J\u0015\u0013A\u0001T8oO\"1\u0011q\u0001\u0004A\u0002eAaA\u0013\u0004A\u0002\u0005=\u0003\u0007BA)\u0003+\u0002B!\u0014+\u0002TA\u0019\u0001,!\u0016\u0005\u0017\u0005]\u0013QJA\u0001\u0002\u0003\u0015\ta\u0017\u0002\u0004?\u0012\u001a\u0004\"B2\u0007\u0001\u0004!\u0007\"B6\u0007\u0001\u0004a\u0007\"\u0002<\u0007\u0001\u00049\bf\u0001\u0001\u0002bA\u0019!%a\u0019\n\u0007\u0005\u00154E\u0001\u0005Qe>4\u0018\u000eZ3sQ\u001d\u0001\u0011\u0011NA9\u0003g\u0002B!a\u001b\u0002n5\tQ%C\u0002\u0002p\u0015\u0012\u0001\u0002\u0015:pIV\u001cWm]\u0001\u0006m\u0006dW/\u001a\u0017\u0003\u0003k\n#!a\u001e\u0002!\u0005\u0004\b\u000f\\5dCRLwN\\\u0018kg>t\u0017\u0001\u0006&bG.\u001cxN\\'fgN\fw-Z,sSR,'\u000f\u0005\u00022\u0011M\u0019\u0001\"a\u000b\u0015\u0005\u0005m\u0014!E7bW\u0016L5k\u0014#bi\u00164uN]7biV\u0011\u0011Q\u0011\t\u0005\u0003\u000f\u000bi)\u0004\u0002\u0002\n*\u0019\u00111R\u000f\u0002\tQ,\u0007\u0010^\u0005\u0005\u0003\u001f\u000bII\u0001\tTS6\u0004H.\u001a#bi\u00164uN]7bi\u0002"
)
public class JacksonMessageWriter implements MessageBodyWriter {
   private final ObjectMapper mapper = new ObjectMapper() {
      public String writeValueAsString(final Object t) {
         return super.writeValueAsString(t);
      }
   };

   public static SimpleDateFormat makeISODateFormat() {
      return JacksonMessageWriter$.MODULE$.makeISODateFormat();
   }

   public ObjectMapper mapper() {
      return this.mapper;
   }

   public boolean isWriteable(final Class aClass, final Type type, final Annotation[] annotations, final MediaType mediaType) {
      return true;
   }

   public void writeTo(final Object t, final Class aClass, final Type type, final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap multivaluedMap, final OutputStream outputStream) {
      this.mapper().writeValue(outputStream, t);
   }

   public long getSize(final Object t, final Class aClass, final Type type, final Annotation[] annotations, final MediaType mediaType) {
      return -1L;
   }

   public JacksonMessageWriter() {
      this.mapper().registerModule(.MODULE$);
      this.mapper().enable(SerializationFeature.INDENT_OUTPUT);
      this.mapper().setSerializationInclusion(Include.NON_ABSENT);
      this.mapper().setDateFormat(JacksonMessageWriter$.MODULE$.makeISODateFormat());
   }
}
