package scala.xml.parsing;

import java.io.File;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.io.Source;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;
import scala.runtime.Statics;
import scala.xml.Document;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.dtd.DTD;
import scala.xml.dtd.ExternalID;

@ScalaSignature(
   bytes = "\u0006\u0005);Qa\u0003\u0007\t\u0002M1Q!\u0006\u0007\t\u0002YAQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0002yAQAR\u0001\u0005\u0002\u001d3A!\u0006\u0007\u0001A!A!&\u0002BC\u0002\u0013\u00053\u0006\u0003\u00053\u000b\t\u0005\t\u0015!\u0003-\u0011!\u0019TA!b\u0001\n\u0003\"\u0004\u0002\u0003\u001d\u0006\u0005\u0003\u0005\u000b\u0011B\u001b\t\u000bm)A\u0011A\u001d\u0002%\r{gn\u001d;sk\u000e$\u0018N\\4QCJ\u001cXM\u001d\u0006\u0003\u001b9\tq\u0001]1sg&twM\u0003\u0002\u0010!\u0005\u0019\u00010\u001c7\u000b\u0003E\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\u0015\u00035\tAB\u0001\nD_:\u001cHO];di&tw\rU1sg\u0016\u00148CA\u0001\u0018!\tA\u0012$D\u0001\u0011\u0013\tQ\u0002C\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\t\u0001B\u001a:p[\u001aKG.\u001a\u000b\u0004?q*\u0005C\u0001\u000b\u0006'\u0011)\u0011\u0005J\u0014\u0011\u0005Q\u0011\u0013BA\u0012\r\u0005M\u0019uN\\:ueV\u001cG/\u001b8h\u0011\u0006tG\r\\3s!\t!R%\u0003\u0002'\u0019\tyQ\t\u001f;fe:\fGnU8ve\u000e,7\u000f\u0005\u0002\u0015Q%\u0011\u0011\u0006\u0004\u0002\r\u001b\u0006\u00148.\u001e9QCJ\u001cXM]\u0001\u0006S:\u0004X\u000f^\u000b\u0002YA\u0011Q\u0006M\u0007\u0002])\u0011q\u0006E\u0001\u0003S>L!!\r\u0018\u0003\rM{WO]2f\u0003\u0019Ig\u000e];uA\u0005Q\u0001O]3tKJ4XmV*\u0016\u0003U\u0002\"\u0001\u0007\u001c\n\u0005]\u0002\"a\u0002\"p_2,\u0017M\\\u0001\faJ,7/\u001a:wK^\u001b\u0006\u0005F\u0002 umBQA\u000b\u0006A\u00021BQa\r\u0006A\u0002UBQ!P\u0002A\u0002y\n1!\u001b8q!\ty4)D\u0001A\u0015\ty\u0013IC\u0001C\u0003\u0011Q\u0017M^1\n\u0005\u0011\u0003%\u0001\u0002$jY\u0016DQaM\u0002A\u0002U\n!B\u001a:p[N{WO]2f)\ry\u0002*\u0013\u0005\u0006{\u0011\u0001\r\u0001\f\u0005\u0006g\u0011\u0001\r!\u000e"
)
public class ConstructingParser extends ConstructingHandler implements ExternalSources, MarkupParser {
   private final Source input;
   private final boolean preserveWS;
   private Source curInput;
   private MarkupHandler scala$xml$parsing$MarkupParser$$handle;
   private List inpStack;
   private int pos;
   private int extIndex;
   private int tmppos;
   private boolean nextChNeeded;
   private boolean reachedEof;
   private char lastChRead;
   private StringBuilder cbuf;
   private DTD dtd;
   private Document doc;

   public static ConstructingParser fromSource(final Source inp, final boolean preserveWS) {
      return ConstructingParser$.MODULE$.fromSource(inp, preserveWS);
   }

   public static ConstructingParser fromFile(final File inp, final boolean preserveWS) {
      return ConstructingParser$.MODULE$.fromFile(inp, preserveWS);
   }

   public Nothing truncatedError(final String msg) {
      return MarkupParser.truncatedError$(this, msg);
   }

   public Nothing errorNoEnd(final String tag) {
      return MarkupParser.errorNoEnd$(this, tag);
   }

   public void xHandleError(final char that, final String msg) {
      MarkupParser.xHandleError$(this, that, msg);
   }

   public BufferedIterator lookahead() {
      return MarkupParser.lookahead$(this);
   }

   public char ch() {
      return MarkupParser.ch$(this);
   }

   public boolean eof() {
      return MarkupParser.eof$(this);
   }

   public MetaData xmlProcInstr() {
      return MarkupParser.xmlProcInstr$(this);
   }

   public Tuple3 prolog() {
      return MarkupParser.prolog$(this);
   }

   public Tuple2 textDecl() {
      return MarkupParser.textDecl$(this);
   }

   public Document document() {
      return MarkupParser.document$(this);
   }

   public StringBuilder putChar(final char c) {
      return MarkupParser.putChar$(this, c);
   }

   public MarkupHandler initialize() {
      return MarkupParser.initialize$(this);
   }

   public char ch_returning_nextch() {
      return MarkupParser.ch_returning_nextch$(this);
   }

   public Tuple2 mkAttributes(final String name, final NamespaceBinding pscope) {
      return MarkupParser.mkAttributes$(this, name, pscope);
   }

   public NodeSeq mkProcInstr(final int position, final String name, final String text) {
      return MarkupParser.mkProcInstr$(this, position, name, text);
   }

   public void nextch() {
      MarkupParser.nextch$(this);
   }

   public Tuple2 xAttributes(final NamespaceBinding pscope) {
      return MarkupParser.xAttributes$(this, pscope);
   }

   public String xEntityValue() {
      return MarkupParser.xEntityValue$(this);
   }

   public NodeSeq xCharData() {
      return MarkupParser.xCharData$(this);
   }

   public NodeSeq xComment() {
      return MarkupParser.xComment$(this);
   }

   public void appendText(final int pos, final NodeBuffer ts, final String txt) {
      MarkupParser.appendText$(this, pos, ts, txt);
   }

   public void content1(final NamespaceBinding pscope, final NodeBuffer ts) {
      MarkupParser.content1$(this, pscope, ts);
   }

   public NodeSeq content(final NamespaceBinding pscope) {
      return MarkupParser.content$(this, pscope);
   }

   public ExternalID externalID() {
      return MarkupParser.externalID$(this);
   }

   public void parseDTD() {
      MarkupParser.parseDTD$(this);
   }

   public NodeSeq element(final NamespaceBinding pscope) {
      return MarkupParser.element$(this, pscope);
   }

   public NodeSeq element1(final NamespaceBinding pscope) {
      return MarkupParser.element1$(this, pscope);
   }

   public String systemLiteral() {
      return MarkupParser.systemLiteral$(this);
   }

   public String pubidLiteral() {
      return MarkupParser.pubidLiteral$(this);
   }

   public void extSubset() {
      MarkupParser.extSubset$(this);
   }

   public Object markupDecl1() {
      return MarkupParser.markupDecl1$(this);
   }

   public void markupDecl() {
      MarkupParser.markupDecl$(this);
   }

   public void intSubset() {
      MarkupParser.intSubset$(this);
   }

   public void elementDecl() {
      MarkupParser.elementDecl$(this);
   }

   public void attrDecl() {
      MarkupParser.attrDecl$(this);
   }

   public void entityDecl() {
      MarkupParser.entityDecl$(this);
   }

   public void notationDecl() {
      MarkupParser.notationDecl$(this);
   }

   public void reportSyntaxError(final int pos, final String str) {
      MarkupParser.reportSyntaxError$(this, pos, str);
   }

   public void reportSyntaxError(final String str) {
      MarkupParser.reportSyntaxError$(this, str);
   }

   public void reportValidationError(final int pos, final String str) {
      MarkupParser.reportValidationError$(this, pos, str);
   }

   public void push(final String entityName) {
      MarkupParser.push$(this, entityName);
   }

   public void pushExternal(final String systemId) {
      MarkupParser.pushExternal$(this, systemId);
   }

   public void pop() {
      MarkupParser.pop$(this);
   }

   public Nothing unreachable() {
      return MarkupParserCommon.unreachable$(this);
   }

   public Tuple2 xTag(final Object pscope) {
      return MarkupParserCommon.xTag$(this, pscope);
   }

   public Object xProcInstr() {
      return MarkupParserCommon.xProcInstr$(this);
   }

   public String xAttributeValue(final char endCh) {
      return MarkupParserCommon.xAttributeValue$(this, endCh);
   }

   public String xAttributeValue() {
      return MarkupParserCommon.xAttributeValue$(this);
   }

   public void xEndTag(final String startName) {
      MarkupParserCommon.xEndTag$(this, startName);
   }

   public String xName() {
      return MarkupParserCommon.xName$(this);
   }

   public String xCharRef(final Function0 ch, final Function0 nextch) {
      return MarkupParserCommon.xCharRef$(this, ch, nextch);
   }

   public String xCharRef(final Iterator it) {
      return MarkupParserCommon.xCharRef$(this, it);
   }

   public String xCharRef() {
      return MarkupParserCommon.xCharRef$(this);
   }

   public Object errorAndResult(final String msg, final Object x) {
      return MarkupParserCommon.errorAndResult$(this, msg, x);
   }

   public void xToken(final char that) {
      MarkupParserCommon.xToken$(this, that);
   }

   public void xToken(final Seq that) {
      MarkupParserCommon.xToken$(this, that);
   }

   public void xEQ() {
      MarkupParserCommon.xEQ$(this);
   }

   public void xSpaceOpt() {
      MarkupParserCommon.xSpaceOpt$(this);
   }

   public void xSpace() {
      MarkupParserCommon.xSpace$(this);
   }

   public Object returning(final Object x, final Function1 f) {
      return MarkupParserCommon.returning$(this, x, f);
   }

   public Object saving(final Object getter, final Function1 setter, final Function0 body) {
      return MarkupParserCommon.saving$(this, getter, setter, body);
   }

   public Object xTakeUntil(final Function2 handler, final Function0 positioner, final String until) {
      return MarkupParserCommon.xTakeUntil$(this, handler, positioner, until);
   }

   public final boolean isSpace(final char ch) {
      return TokenTests.isSpace$(this, ch);
   }

   public final boolean isSpace(final Seq cs) {
      return TokenTests.isSpace$(this, cs);
   }

   public boolean isAlpha(final char c) {
      return TokenTests.isAlpha$(this, c);
   }

   public boolean isAlphaDigit(final char c) {
      return TokenTests.isAlphaDigit$(this, c);
   }

   public boolean isNameChar(final char ch) {
      return TokenTests.isNameChar$(this, ch);
   }

   public boolean isNameStart(final char ch) {
      return TokenTests.isNameStart$(this, ch);
   }

   public boolean isName(final String s) {
      return TokenTests.isName$(this, s);
   }

   public boolean isPubIDChar(final char ch) {
      return TokenTests.isPubIDChar$(this, ch);
   }

   public boolean isValidIANAEncoding(final Seq ianaEncoding) {
      return TokenTests.isValidIANAEncoding$(this, ianaEncoding);
   }

   public boolean checkSysID(final String s) {
      return TokenTests.checkSysID$(this, s);
   }

   public boolean checkPubID(final String s) {
      return TokenTests.checkPubID$(this, s);
   }

   public Source externalSource(final String systemId) {
      return ExternalSources.externalSource$(this, systemId);
   }

   public Source curInput() {
      return this.curInput;
   }

   public void curInput_$eq(final Source x$1) {
      this.curInput = x$1;
   }

   public MarkupHandler scala$xml$parsing$MarkupParser$$handle() {
      return this.scala$xml$parsing$MarkupParser$$handle;
   }

   public List inpStack() {
      return this.inpStack;
   }

   public void inpStack_$eq(final List x$1) {
      this.inpStack = x$1;
   }

   public int pos() {
      return this.pos;
   }

   public void pos_$eq(final int x$1) {
      this.pos = x$1;
   }

   public int extIndex() {
      return this.extIndex;
   }

   public void extIndex_$eq(final int x$1) {
      this.extIndex = x$1;
   }

   public int tmppos() {
      return this.tmppos;
   }

   public void tmppos_$eq(final int x$1) {
      this.tmppos = x$1;
   }

   public boolean nextChNeeded() {
      return this.nextChNeeded;
   }

   public void nextChNeeded_$eq(final boolean x$1) {
      this.nextChNeeded = x$1;
   }

   public boolean reachedEof() {
      return this.reachedEof;
   }

   public void reachedEof_$eq(final boolean x$1) {
      this.reachedEof = x$1;
   }

   public char lastChRead() {
      return this.lastChRead;
   }

   public void lastChRead_$eq(final char x$1) {
      this.lastChRead = x$1;
   }

   public StringBuilder cbuf() {
      return this.cbuf;
   }

   public DTD dtd() {
      return this.dtd;
   }

   public void dtd_$eq(final DTD x$1) {
      this.dtd = x$1;
   }

   public Document doc() {
      return this.doc;
   }

   public void doc_$eq(final Document x$1) {
      this.doc = x$1;
   }

   public final void scala$xml$parsing$MarkupParser$_setter_$scala$xml$parsing$MarkupParser$$handle_$eq(final MarkupHandler x$1) {
      this.scala$xml$parsing$MarkupParser$$handle = x$1;
   }

   public void scala$xml$parsing$MarkupParser$_setter_$cbuf_$eq(final StringBuilder x$1) {
      this.cbuf = x$1;
   }

   public Source input() {
      return this.input;
   }

   public boolean preserveWS() {
      return this.preserveWS;
   }

   public ConstructingParser(final Source input, final boolean preserveWS) {
      this.input = input;
      this.preserveWS = preserveWS;
      ExternalSources.$init$(this);
      TokenTests.$init$(this);
      MarkupParserCommon.$init$(this);
      MarkupParser.$init$(this);
      Statics.releaseFence();
   }
}
