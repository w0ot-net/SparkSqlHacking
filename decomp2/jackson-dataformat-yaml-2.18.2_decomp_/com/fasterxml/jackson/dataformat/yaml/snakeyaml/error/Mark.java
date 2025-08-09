package com.fasterxml.jackson.dataformat.yaml.snakeyaml.error;

/** @deprecated */
@Deprecated
public class Mark {
   protected final org.yaml.snakeyaml.error.Mark _source;

   protected Mark(org.yaml.snakeyaml.error.Mark src) {
      this._source = src;
   }

   public static Mark from(org.yaml.snakeyaml.error.Mark src) {
      return src == null ? null : new Mark(src);
   }

   public String getName() {
      return this._source.getName();
   }

   public String get_snippet() {
      return this._source.get_snippet();
   }

   public String get_snippet(int indent, int max_length) {
      return this._source.get_snippet(indent, max_length);
   }

   public int getColumn() {
      return this._source.getColumn();
   }

   public int getLine() {
      return this._source.getLine();
   }

   public int getIndex() {
      return this._source.getIndex();
   }
}
