package org.apache.spark.ui;

import jakarta.servlet.http.HttpServletRequest;
import scala.Predef;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da!\u0002\n\u0014\u0001UY\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0013\t\u000b9\u0002A\u0011A\u0018\t\u000fM\u0002\u0001\u0019!C\u0005i!9\u0001\b\u0001a\u0001\n\u0013I\u0004BB \u0001A\u0003&Q\u0007C\u0003A\u0001\u0011\u0005\u0011\tC\u0004N\u0001\t\u0007I\u0011\u0002(\t\r]\u0003\u0001\u0015!\u0003P\u0011\u001dA\u0006A1A\u0005\n9Ca!\u0017\u0001!\u0002\u0013y\u0005b\u0002.\u0001\u0005\u0004%Ia\u0017\u0005\u0007?\u0002\u0001\u000b\u0011\u0002/\t\u000b\u0001\u0004A\u0011A1\t\u000b\u0011\u0004A\u0011A3\t\u000b\u001d\u0004A\u0011\u00015\t\u000b\u001d\u0004A\u0011\u00019\t\u000bI\u0004A\u0011A:\u0003\u0017)\u001b8i\u001c7mK\u000e$xN\u001d\u0006\u0003)U\t!!^5\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e\u001c\"\u0001\u0001\u000f\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\r\u0005s\u0017PU3g\u0003\r\u0011X-]\u0002\u0001!\t)C&D\u0001'\u0015\t9\u0003&\u0001\u0003iiR\u0004(BA\u0015+\u0003\u001d\u0019XM\u001d<mKRT\u0011aK\u0001\bU\u0006\\\u0017M\u001d;b\u0013\ticE\u0001\nIiR\u00048+\u001a:wY\u0016$(+Z9vKN$\u0018A\u0002\u001fj]&$h\b\u0006\u00021eA\u0011\u0011\u0007A\u0007\u0002'!)!E\u0001a\u0001I\u0005Qa/\u0019:jC\ndW-\u00133\u0016\u0003U\u0002\"!\b\u001c\n\u0005]r\"aA%oi\u0006qa/\u0019:jC\ndW-\u00133`I\u0015\fHC\u0001\u001e>!\ti2(\u0003\u0002==\t!QK\\5u\u0011\u001dqD!!AA\u0002U\n1\u0001\u001f\u00132\u0003-1\u0018M]5bE2,\u0017\n\u001a\u0011\u0002!9,\u0007\u0010\u001e,be&\f'\r\\3OC6,W#\u0001\"\u0011\u0005\rSeB\u0001#I!\t)e$D\u0001G\u0015\t95%\u0001\u0004=e>|GOP\u0005\u0003\u0013z\ta\u0001\u0015:fI\u00164\u0017BA&M\u0005\u0019\u0019FO]5oO*\u0011\u0011JH\u0001\u0013aJ,\u0007/\u0019:fIN#\u0018\r^3nK:$8/F\u0001P!\r\u0001VKQ\u0007\u0002#*\u0011!kU\u0001\b[V$\u0018M\u00197f\u0015\t!f$\u0001\u0006d_2dWm\u0019;j_:L!AV)\u0003\u0017\u0005\u0013(/Y=Ck\u001a4WM]\u0001\u0014aJ,\u0007/\u0019:fIN#\u0018\r^3nK:$8\u000fI\u0001\u000bgR\fG/Z7f]R\u001c\u0018aC:uCR,W.\u001a8ug\u0002\nq![7q_J$8/F\u0001]!\r\u0001VLQ\u0005\u0003=F\u00131aU3u\u0003!IW\u000e]8siN\u0004\u0013\u0001F1eIB\u0013X\r]1sK\u0012\u001cF/\u0019;f[\u0016tG\u000f\u0006\u0002;E\")1-\u0004a\u0001\u0005\u0006\u0011!n]\u0001\rC\u0012$7\u000b^1uK6,g\u000e\u001e\u000b\u0003u\u0019DQa\u0019\bA\u0002\t\u000b!\"\u00193e\u00136\u0004xN\u001d;t)\rQ\u0014n\u001b\u0005\u0006U>\u0001\rAQ\u0001\u000bg>,(oY3GS2,\u0007\"\u00027\u0010\u0001\u0004i\u0017!\u00034v]\u000e$\u0018n\u001c8t!\ribNQ\u0005\u0003_z\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?)\tQ\u0014\u000fC\u0003d!\u0001\u0007!)\u0001\u0004u_\"#X\u000e\\\u000b\u0002iB\u0019QO_?\u000f\u0005YDhBA#x\u0013\u0005y\u0012BA=\u001f\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u001f?\u0003\u0007M+\u0017O\u0003\u0002z=A\u0019a0a\u0001\u000e\u0003}T1!!\u0001\u001f\u0003\rAX\u000e\\\u0005\u0004\u0003\u000by(\u0001\u0002(pI\u0016\u0004"
)
public class JsCollector {
   private final HttpServletRequest req;
   private int variableId;
   private final ArrayBuffer preparedStatements;
   private final ArrayBuffer statements;
   private final Set imports;

   private int variableId() {
      return this.variableId;
   }

   private void variableId_$eq(final int x$1) {
      this.variableId = x$1;
   }

   public String nextVariableName() {
      this.variableId_$eq(this.variableId() + 1);
      return "v" + this.variableId();
   }

   private ArrayBuffer preparedStatements() {
      return this.preparedStatements;
   }

   private ArrayBuffer statements() {
      return this.statements;
   }

   private Set imports() {
      return this.imports;
   }

   public void addPreparedStatement(final String js) {
      this.preparedStatements().$plus$eq(js);
   }

   public void addStatement(final String js) {
      this.statements().$plus$eq(js);
   }

   public void addImports(final String sourceFile, final Seq functions) {
      this.imports().add(UIUtils$.MODULE$.formatImportJavaScript(this.req, sourceFile, functions));
   }

   public void addImports(final String js) {
      this.imports().add(js);
   }

   public Seq toHtml() {
      StringOps var10000 = .MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      String var10002 = this.imports().mkString("\n");
      String js = var10000.stripMargin$extension(var10001.augmentString("\n         |" + var10002 + "\n         |\n         |$(document).ready(function() {\n         |    " + this.preparedStatements().mkString("\n") + "\n         |    " + this.statements().mkString("\n") + "\n         |});"));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var4 = new UnprefixedAttribute("type", new Text("module"), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(js));
      return new Elem((String)null, "script", var4, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public JsCollector(final HttpServletRequest req) {
      this.req = req;
      this.variableId = 0;
      this.preparedStatements = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.statements = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.imports = (Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }
}
