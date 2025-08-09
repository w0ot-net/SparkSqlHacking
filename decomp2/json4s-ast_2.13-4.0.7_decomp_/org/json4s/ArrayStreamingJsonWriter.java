package org.json4s;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E4A!\u0006\f\u00077!AA\u0007\u0001BCB\u0013EQ\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u0003\"\u0011!9\u0004A!b!\n#A\u0004\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011u\u0002!\u0011!Q\u0001\nuA\u0001B\u0010\u0001\u0003\u0006\u0004&\tb\u0010\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005\u0001\"AA\t\u0001BCB\u0013E\u0001\b\u0003\u0005F\u0001\t\u0005\t\u0015!\u0003:\u0011!1\u0005A!b!\n#y\u0004\u0002C$\u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u000b!\u0003A\u0011A%\t\rE\u0003\u0001\u0015)\u0003A\u0011\u0015\u0011\u0006\u0001\"\u00016\u0011\u0015\u0019\u0006\u0001\"\u0011U\u0011\u0019A\u0006\u0001)C\u00053\")Q\f\u0001C!)\")a\f\u0001C!)\")q\f\u0001C\u0001A\")a\u000e\u0001C\u0001_\nA\u0012I\u001d:bsN#(/Z1nS:<'j]8o/JLG/\u001a:\u000b\u0005]A\u0012A\u00026t_:$4OC\u0001\u001a\u0003\ry'oZ\u0002\u0001+\ta2e\u0005\u0002\u0001;A\u0019adH\u0011\u000e\u0003YI!\u0001\t\f\u0003'M#(/Z1nS:<'j]8o/JLG/\u001a:\u0011\u0005\t\u001aC\u0002\u0001\u0003\u0006I\u0001\u0011\r!\n\u0002\u0002)F\u0011a\u0005\f\t\u0003O)j\u0011\u0001\u000b\u0006\u0002S\u0005)1oY1mC&\u00111\u0006\u000b\u0002\b\u001d>$\b.\u001b8h!\ti#'D\u0001/\u0015\ty\u0003'\u0001\u0002j_*\t\u0011'\u0001\u0003kCZ\f\u0017BA\u001a/\u0005\u00199&/\u001b;fe\u0006)an\u001c3fgV\t\u0011%\u0001\u0004o_\u0012,7\u000fI\u0001\u0006Y\u00164X\r\\\u000b\u0002sA\u0011qEO\u0005\u0003w!\u00121!\u00138u\u0003\u0019aWM^3mA\u00051\u0001/\u0019:f]R\fa\u0001\u001d:fiRLX#\u0001!\u0011\u0005\u001d\n\u0015B\u0001\")\u0005\u001d\u0011un\u001c7fC:\fq\u0001\u001d:fiRL\b%\u0001\u0004ta\u0006\u001cWm]\u0001\bgB\f7-Z:!\u0003M\tGn^1zg\u0016\u001b8-\u00199f+:L7m\u001c3f\u0003Q\tGn^1zg\u0016\u001b8-\u00199f+:L7m\u001c3fA\u00051A(\u001b8jiz\"rAS&M\u001b:{\u0005\u000bE\u0002\u001f\u0001\u0005BQ\u0001\u000e\u0007A\u0002\u0005BQa\u000e\u0007A\u0002eBQ!\u0010\u0007A\u0002uAQA\u0010\u0007A\u0002\u0001CQ\u0001\u0012\u0007A\u0002eBQA\u0012\u0007A\u0002\u0001\u000bq![:GSJ\u001cH/\u0001\u0004sKN,H\u000e^\u0001\tK:$\u0017I\u001d:bsR\tQ\u000bE\u0002\u001f-\u0006J!a\u0016\f\u0003\u0015)\u001bxN\\,sSR,'/\u0001\u0006xe&$XmQ8n[\u0006$\u0012A\u0017\t\u0003OmK!\u0001\u0018\u0015\u0003\tUs\u0017\u000e^\u0001\u000bgR\f'\u000f^!se\u0006L\u0018aC:uCJ$xJ\u00196fGR\fq!\u00193e\u001d>$W\r\u0006\u0002VC\")!m\u0005a\u0001G\u0006!an\u001c3f!\t!7N\u0004\u0002fSB\u0011a\rK\u0007\u0002O*\u0011\u0001NG\u0001\u0007yI|w\u000e\u001e \n\u0005)D\u0013A\u0002)sK\u0012,g-\u0003\u0002m[\n11\u000b\u001e:j]\u001eT!A\u001b\u0015\u0002\u001f\u0005$G-\u00118e#V|G/\u001a(pI\u0016$\"!\u00169\t\u000b\t$\u0002\u0019A2"
)
public final class ArrayStreamingJsonWriter extends StreamingJsonWriter {
   private final java.io.Writer nodes;
   private final int level;
   private final StreamingJsonWriter parent;
   private final boolean pretty;
   private final int spaces;
   private final boolean alwaysEscapeUnicode;
   private boolean isFirst;

   public java.io.Writer nodes() {
      return this.nodes;
   }

   public int level() {
      return this.level;
   }

   public boolean pretty() {
      return this.pretty;
   }

   public int spaces() {
      return this.spaces;
   }

   public boolean alwaysEscapeUnicode() {
      return this.alwaysEscapeUnicode;
   }

   public java.io.Writer result() {
      return this.nodes();
   }

   public JsonWriter endArray() {
      this.writePretty(2);
      this.nodes().write(93);
      return this.parent;
   }

   private void writeComma() {
      if (!this.isFirst) {
         this.nodes().write(44);
         this.writePretty(this.writePretty$default$1());
      } else {
         this.isFirst = false;
      }

   }

   public JsonWriter startArray() {
      this.writeComma();
      return super.startArray();
   }

   public JsonWriter startObject() {
      this.writeComma();
      return super.startObject();
   }

   public JsonWriter addNode(final String node) {
      this.writeComma();
      this.nodes().write(node);
      return this;
   }

   public JsonWriter addAndQuoteNode(final String node) {
      this.writeComma();
      this.nodes().append("\"");
      ParserUtil$.MODULE$.quote(node, this.nodes(), this.alwaysEscapeUnicode());
      this.nodes().append("\"");
      return this;
   }

   public ArrayStreamingJsonWriter(final java.io.Writer nodes, final int level, final StreamingJsonWriter parent, final boolean pretty, final int spaces, final boolean alwaysEscapeUnicode) {
      this.nodes = nodes;
      this.level = level;
      this.parent = parent;
      this.pretty = pretty;
      this.spaces = spaces;
      this.alwaysEscapeUnicode = alwaysEscapeUnicode;
      nodes.write(91);
      this.writePretty(this.writePretty$default$1());
      this.isFirst = true;
   }
}
