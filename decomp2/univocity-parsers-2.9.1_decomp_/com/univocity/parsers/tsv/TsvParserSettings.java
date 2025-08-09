package com.univocity.parsers.tsv;

import com.univocity.parsers.common.CommonParserSettings;
import java.util.Map;

public class TsvParserSettings extends CommonParserSettings {
   private boolean lineJoiningEnabled = false;

   public boolean isLineJoiningEnabled() {
      return this.lineJoiningEnabled;
   }

   public void setLineJoiningEnabled(boolean lineJoiningEnabled) {
      this.lineJoiningEnabled = lineJoiningEnabled;
   }

   protected TsvFormat createDefaultFormat() {
      return new TsvFormat();
   }

   protected void addConfiguration(Map out) {
      super.addConfiguration(out);
   }

   public final TsvParserSettings clone() {
      return (TsvParserSettings)super.clone();
   }

   public final TsvParserSettings clone(boolean clearInputSpecificSettings) {
      return (TsvParserSettings)super.clone(clearInputSpecificSettings);
   }
}
