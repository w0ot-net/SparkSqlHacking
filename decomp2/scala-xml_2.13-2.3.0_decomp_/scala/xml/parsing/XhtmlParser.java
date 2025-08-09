package scala.xml.parsing;

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
   bytes = "\u0006\u0005\u00013AAC\u0006\u0001%!AQ\u0004\u0001BC\u0002\u0013\u0005c\u0004\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003 \u0011\u00151\u0003\u0001\"\u0001(\u0011\u001dQ\u0003A1A\u0005B-Ba\u0001\r\u0001!\u0002\u0013as!B\u0019\f\u0011\u0003\u0011d!\u0002\u0006\f\u0011\u0003\u0019\u0004\"\u0002\u0014\b\t\u00039\u0004\"\u0002\u001d\b\t\u0003I$a\u0003-ii6d\u0007+\u0019:tKJT!\u0001D\u0007\u0002\u000fA\f'o]5oO*\u0011abD\u0001\u0004q6d'\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001aE\f\u001b!\t!R#D\u0001\f\u0013\t12BA\nD_:\u001cHO];di&tw\rS1oI2,'\u000f\u0005\u0002\u00151%\u0011\u0011d\u0003\u0002\r\u001b\u0006\u00148.\u001e9QCJ\u001cXM\u001d\t\u0003)mI!\u0001H\u0006\u0003\u001f\u0015CH/\u001a:oC2\u001cv.\u001e:dKN\fQ!\u001b8qkR,\u0012a\b\t\u0003A\rj\u0011!\t\u0006\u0003E=\t!![8\n\u0005\u0011\n#AB*pkJ\u001cW-\u0001\u0004j]B,H\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005!J\u0003C\u0001\u000b\u0001\u0011\u0015i2\u00011\u0001 \u0003)\u0001(/Z:feZ,wkU\u000b\u0002YA\u0011QFL\u0007\u0002\u001f%\u0011qf\u0004\u0002\b\u0005>|G.Z1o\u0003-\u0001(/Z:feZ,wk\u0015\u0011\u0002\u0017aCG/\u001c7QCJ\u001cXM\u001d\t\u0003)\u001d\u0019\"a\u0002\u001b\u0011\u00055*\u0014B\u0001\u001c\u0010\u0005\u0019\te.\u001f*fMR\t!'A\u0003baBd\u0017\u0010\u0006\u0002;}A\u00111\bP\u0007\u0002\u001b%\u0011Q(\u0004\u0002\b\u001d>$WmU3r\u0011\u0015y\u0014\u00021\u0001 \u0003\u0019\u0019x.\u001e:dK\u0002"
)
public class XhtmlParser extends ConstructingHandler implements MarkupParser, ExternalSources {
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

   public static NodeSeq apply(final Source source) {
      return XhtmlParser$.MODULE$.apply(source);
   }

   public Source externalSource(final String systemId) {
      return ExternalSources.externalSource$(this, systemId);
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

   public XhtmlParser(final Source input) {
      this.input = input;
      TokenTests.$init$(this);
      MarkupParserCommon.$init$(this);
      MarkupParser.$init$(this);
      ExternalSources.$init$(this);
      this.preserveWS = true;
      this.ent().$plus$plus$eq(XhtmlEntities$.MODULE$.apply());
      Statics.releaseFence();
   }
}
