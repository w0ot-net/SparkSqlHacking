package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.minlog.Log;

public class TaggedFieldSerializerConfig extends FieldSerializerConfig {
   private boolean skipUnknownTags = false;

   public void setSkipUnknownTags(boolean skipUnknownTags) {
      this.skipUnknownTags = skipUnknownTags;
      if (Log.TRACE) {
         Log.trace("kryo.TaggedFieldSerializerConfig", "setSkipUnknownTags: " + skipUnknownTags);
      }

   }

   public boolean isSkipUnknownTags() {
      return this.skipUnknownTags;
   }

   /** @deprecated */
   @Deprecated
   public void setIgnoreUnknownTags(boolean ignoreUnknownTags) {
   }

   /** @deprecated */
   @Deprecated
   public boolean isIgnoreUnknownTags() {
      return false;
   }

   protected TaggedFieldSerializerConfig clone() {
      return (TaggedFieldSerializerConfig)super.clone();
   }
}
