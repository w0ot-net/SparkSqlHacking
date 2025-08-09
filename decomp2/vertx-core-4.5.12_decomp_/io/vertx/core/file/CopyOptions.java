package io.vertx.core.file;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class CopyOptions {
   public static final boolean DEFAULT_REPLACE_EXISTING = false;
   public static final boolean DEFAULT_COPY_ATTRIBUTES = false;
   public static final boolean DEFAULT_ATOMIC_MOVE = false;
   public static final boolean DEFAULT_NOFOLLOW_LINKS = false;
   private boolean replaceExisting;
   private boolean copyAttributes;
   private boolean atomicMove;
   private boolean nofollowLinks;

   public CopyOptions() {
      this.replaceExisting = false;
      this.copyAttributes = false;
      this.atomicMove = false;
      this.nofollowLinks = false;
   }

   public CopyOptions(CopyOptions other) {
      this.replaceExisting = false;
      this.copyAttributes = false;
      this.atomicMove = false;
      this.nofollowLinks = false;
      this.replaceExisting = other.replaceExisting;
      this.copyAttributes = other.copyAttributes;
      this.atomicMove = other.atomicMove;
      this.nofollowLinks = other.nofollowLinks;
   }

   public CopyOptions(JsonObject json) {
      this();
      CopyOptionsConverter.fromJson(json, this);
   }

   public boolean isReplaceExisting() {
      return this.replaceExisting;
   }

   public CopyOptions setReplaceExisting(boolean replaceExisting) {
      this.replaceExisting = replaceExisting;
      return this;
   }

   public boolean isCopyAttributes() {
      return this.copyAttributes;
   }

   public CopyOptions setCopyAttributes(boolean copyAttributes) {
      this.copyAttributes = copyAttributes;
      return this;
   }

   public boolean isAtomicMove() {
      return this.atomicMove;
   }

   public CopyOptions setAtomicMove(boolean atomicMove) {
      this.atomicMove = atomicMove;
      return this;
   }

   public boolean isNofollowLinks() {
      return this.nofollowLinks;
   }

   public CopyOptions setNofollowLinks(boolean nofollowLinks) {
      this.nofollowLinks = nofollowLinks;
      return this;
   }
}
