package org.apache.spark.sql.streaming;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.annotation.Evolving;
import org.json4s.JInt;
import org.json4s.JString;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\u000f\u001e\u0001!B\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005{!Aa\t\u0001BC\u0002\u0013\u0005A\b\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003>\u0011!A\u0005A!b\u0001\n\u0003a\u0004\u0002C%\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u0011)\u0003!Q1A\u0005\u0002qB\u0001b\u0013\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\t\u0019\u0002\u0011)\u0019!C\u0001\u001b\"A\u0011\u000b\u0001B\u0001B\u0003%a\n\u0003\u0005S\u0001\t\u0015\r\u0011\"\u0001T\u0011!9\u0006A!A!\u0002\u0013!\u0006\u0002\u0003-\u0001\u0005\u000b\u0007I\u0011A*\t\u0011e\u0003!\u0011!Q\u0001\nQC\u0001B\u0017\u0001\u0003\u0006\u0004%\ta\u0017\u0005\tI\u0002\u0011\t\u0011)A\u00059\"1Q\r\u0001C\tC\u0019DQ!\u001d\u0001\u0005\u0002qBQA\u001d\u0001\u0005\u0002qBQa\u001d\u0001\u0005BQDa!\u001e\u0001\u0005\u0002}1\bbBA\u0005\u0001\u0011%\u00111B\u0004\n\u0003Gi\u0012\u0011!E\u0001\u0003K1\u0001\u0002H\u000f\u0002\u0002#\u0005\u0011q\u0005\u0005\u0007Kb!\t!a\r\t\u0015\u0005U\u0002$%A\u0005\u0012\u0005\n9\u0004C\u0005\u0002La\t\t\u0011\"\u0003\u0002N\tq1k\\;sG\u0016\u0004&o\\4sKN\u001c(B\u0001\u0010 \u0003%\u0019HO]3b[&twM\u0003\u0002!C\u0005\u00191/\u001d7\u000b\u0005\t\u001a\u0013!B:qCJ\\'B\u0001\u0013&\u0003\u0019\t\u0007/Y2iK*\ta%A\u0002pe\u001e\u001c\u0001aE\u0002\u0001S=\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012a!\u00118z%\u00164\u0007C\u0001\u00199\u001d\t\tdG\u0004\u00023k5\t1G\u0003\u00025O\u00051AH]8pizJ\u0011\u0001L\u0005\u0003o-\nq\u0001]1dW\u0006<W-\u0003\u0002:u\ta1+\u001a:jC2L'0\u00192mK*\u0011qgK\u0001\fI\u0016\u001c8M]5qi&|g.F\u0001>!\tq$I\u0004\u0002@\u0001B\u0011!gK\u0005\u0003\u0003.\na\u0001\u0015:fI\u00164\u0017BA\"E\u0005\u0019\u0019FO]5oO*\u0011\u0011iK\u0001\rI\u0016\u001c8M]5qi&|g\u000eI\u0001\fgR\f'\u000f^(gMN,G/\u0001\u0007ti\u0006\u0014Ho\u00144gg\u0016$\b%A\u0005f]\u0012|eMZ:fi\u0006QQM\u001c3PM\u001a\u001cX\r\u001e\u0011\u0002\u00191\fG/Z:u\u001f\u001a47/\u001a;\u0002\u001b1\fG/Z:u\u001f\u001a47/\u001a;!\u00031qW/\\%oaV$(k\\<t+\u0005q\u0005C\u0001\u0016P\u0013\t\u00016F\u0001\u0003M_:<\u0017!\u00048v[&s\u0007/\u001e;S_^\u001c\b%\u0001\nj]B,HOU8xgB+'oU3d_:$W#\u0001+\u0011\u0005)*\u0016B\u0001,,\u0005\u0019!u.\u001e2mK\u0006\u0019\u0012N\u001c9viJ{wo\u001d)feN+7m\u001c8eA\u00051\u0002O]8dKN\u001cX\r\u001a*poN\u0004VM]*fG>tG-A\fqe>\u001cWm]:fIJ{wo\u001d)feN+7m\u001c8eA\u00059Q.\u001a;sS\u000e\u001cX#\u0001/\u0011\tu\u0013W(P\u0007\u0002=*\u0011q\fY\u0001\u0005kRLGNC\u0001b\u0003\u0011Q\u0017M^1\n\u0005\rt&aA'ba\u0006AQ.\u001a;sS\u000e\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\nO&T7\u000e\\7o_B\u0004\"\u0001\u001b\u0001\u000e\u0003uAQaO\tA\u0002uBQAR\tA\u0002uBQ\u0001S\tA\u0002uBQAS\tA\u0002uBQ\u0001T\tA\u00029CQAU\tA\u0002QCQ\u0001W\tA\u0002QCqAW\t\u0011\u0002\u0003\u0007A,\u0001\u0003kg>t\u0017A\u00039sKR$\u0018PS:p]\u0006AAo\\*ue&tw\rF\u0001>\u0003%Q7o\u001c8WC2,X-F\u0001x!\rA\u00181\u0001\b\u0003szt!A\u001f?\u000f\u0005IZ\u0018\"\u0001\u0014\n\u0005u,\u0013A\u00026t_:$4/C\u0002\u0000\u0003\u0003\tqAS:p]\u0006\u001bFK\u0003\u0002~K%!\u0011QAA\u0004\u0005\u0019Qe+\u00197vK*\u0019q0!\u0001\u0002\u0011Q\u0014\u0018\u0010U1sg\u0016$B!!\u0004\u0002\u0014A!\u0011qBA\t\u001b\t\t\t!\u0003\u0003\u0002\u0006\u0005\u0005\u0001\"B9\u0017\u0001\u0004i\u0004f\u0001\u0001\u0002\u0018A!\u0011\u0011DA\u0010\u001b\t\tYBC\u0002\u0002\u001e\u0005\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t#a\u0007\u0003\u0011\u00153x\u000e\u001c<j]\u001e\fabU8ve\u000e,\u0007K]8he\u0016\u001c8\u000f\u0005\u0002i1M!\u0001$KA\u0015!\u0011\tY#!\r\u000e\u0005\u00055\"bAA\u0018A\u0006\u0011\u0011n\\\u0005\u0004s\u00055BCAA\u0013\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%qU\u0011\u0011\u0011\b\u0016\u00049\u0006m2FAA\u001f!\u0011\ty$a\u0012\u000e\u0005\u0005\u0005#\u0002BA\"\u0003\u000b\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005u1&\u0003\u0003\u0002J\u0005\u0005#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\n\t\u0005\u0003#\n9&\u0004\u0002\u0002T)\u0019\u0011Q\u000b1\u0002\t1\fgnZ\u0005\u0005\u00033\n\u0019F\u0001\u0004PE*,7\r\u001e"
)
public class SourceProgress implements Serializable {
   private final String description;
   private final String startOffset;
   private final String endOffset;
   private final String latestOffset;
   private final long numInputRows;
   private final double inputRowsPerSecond;
   private final double processedRowsPerSecond;
   private final Map metrics;

   public String description() {
      return this.description;
   }

   public String startOffset() {
      return this.startOffset;
   }

   public String endOffset() {
      return this.endOffset;
   }

   public String latestOffset() {
      return this.latestOffset;
   }

   public long numInputRows() {
      return this.numInputRows;
   }

   public double inputRowsPerSecond() {
      return this.inputRowsPerSecond;
   }

   public double processedRowsPerSecond() {
      return this.processedRowsPerSecond;
   }

   public Map metrics() {
      return this.metrics;
   }

   public String json() {
      return .MODULE$.compact(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public String prettyJson() {
      return .MODULE$.pretty(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public String toString() {
      return this.prettyJson();
   }

   public JValue jsonValue() {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("description"), new JString(this.description())), scala.Predef..MODULE$.$conforms()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("startOffset"), this.tryParse(this.startOffset())), scala.Predef..MODULE$.$conforms(), scala.Predef..MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("endOffset"), this.tryParse(this.endOffset())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("latestOffset"), this.tryParse(this.latestOffset())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numInputRows"), new JInt(scala.math.BigInt..MODULE$.long2bigInt(this.numInputRows()))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("inputRowsPerSecond"), SafeJsonSerializer$.MODULE$.safeDoubleToJValue(this.inputRowsPerSecond())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("processedRowsPerSecond"), SafeJsonSerializer$.MODULE$.safeDoubleToJValue(this.processedRowsPerSecond())))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("metrics"), SafeJsonSerializer$.MODULE$.safeMapToJValue(this.metrics(), (s) -> new JString(s))));
   }

   private JValue tryParse(final String json) {
      Object var10000;
      try {
         var10000 = .MODULE$.parse(json, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      } catch (Throwable var6) {
         if (var6 == null || !scala.util.control.NonFatal..MODULE$.apply(var6)) {
            throw var6;
         }

         var10000 = new JString(json);
      }

      return (JValue)var10000;
   }

   public SourceProgress(final String description, final String startOffset, final String endOffset, final String latestOffset, final long numInputRows, final double inputRowsPerSecond, final double processedRowsPerSecond, final Map metrics) {
      this.description = description;
      this.startOffset = startOffset;
      this.endOffset = endOffset;
      this.latestOffset = latestOffset;
      this.numInputRows = numInputRows;
      this.inputRowsPerSecond = inputRowsPerSecond;
      this.processedRowsPerSecond = processedRowsPerSecond;
      this.metrics = metrics;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
