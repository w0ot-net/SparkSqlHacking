package com.github.difflib.text;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.ChangeDelta;
import com.github.difflib.patch.Chunk;
import com.github.difflib.patch.DeleteDelta;
import com.github.difflib.patch.DeltaType;
import com.github.difflib.patch.InsertDelta;
import com.github.difflib.patch.Patch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DiffRowGenerator {
   public static final BiPredicate DEFAULT_EQUALIZER = Object::equals;
   public static final BiPredicate IGNORE_WHITESPACE_EQUALIZER = (original, revised) -> adjustWhitespace(original).equals(adjustWhitespace(revised));
   public static final Function LINE_NORMALIZER_FOR_HTML = StringUtils::normalize;
   public static final Function SPLITTER_BY_CHARACTER = (line) -> {
      List<String> list = new ArrayList(line.length());
      char[] var2 = line.toCharArray();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Character character = var2[var4];
         list.add(character.toString());
      }

      return list;
   };
   public static final Pattern SPLIT_BY_WORD_PATTERN = Pattern.compile("\\s+|[,.\\[\\](){}/\\\\*+\\-#<>;:&\\']+");
   public static final Function SPLITTER_BY_WORD = (line) -> splitStringPreserveDelimiter(line, SPLIT_BY_WORD_PATTERN);
   public static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
   private final int columnWidth;
   private final BiPredicate equalizer;
   private final boolean ignoreWhiteSpaces;
   private final Function inlineDiffSplitter;
   private final boolean mergeOriginalRevised;
   private final BiFunction newTag;
   private final BiFunction oldTag;
   private final boolean reportLinesUnchanged;
   private final Function lineNormalizer;
   private final Function processDiffs;
   private final boolean showInlineDiffs;
   private final boolean replaceOriginalLinefeedInChangesWithSpaces;
   private final boolean decompressDeltas;

   public static Builder create() {
      return new Builder();
   }

   private static String adjustWhitespace(String raw) {
      return WHITESPACE_PATTERN.matcher(raw.trim()).replaceAll(" ");
   }

   protected static final List splitStringPreserveDelimiter(String str, Pattern SPLIT_PATTERN) {
      List<String> list = new ArrayList();
      if (str != null) {
         Matcher matcher = SPLIT_PATTERN.matcher(str);

         int pos;
         for(pos = 0; matcher.find(); pos = matcher.end()) {
            if (pos < matcher.start()) {
               list.add(str.substring(pos, matcher.start()));
            }

            list.add(matcher.group());
         }

         if (pos < str.length()) {
            list.add(str.substring(pos));
         }
      }

      return list;
   }

   static void wrapInTag(List sequence, int startPosition, int endPosition, DiffRow.Tag tag, BiFunction tagGenerator, Function processDiffs, boolean replaceLinefeedWithSpace) {
      for(int endPos = endPosition; endPos >= startPosition; --endPos) {
         while(endPos > startPosition && "\n".equals(sequence.get(endPos - 1))) {
            if (replaceLinefeedWithSpace) {
               sequence.set(endPos - 1, " ");
               break;
            }

            --endPos;
         }

         if (endPos == startPosition) {
            break;
         }

         sequence.add(endPos, tagGenerator.apply(tag, false));
         if (processDiffs != null) {
            sequence.set(endPos - 1, processDiffs.apply(sequence.get(endPos - 1)));
         }

         --endPos;

         for(; endPos > startPosition; --endPos) {
            if ("\n".equals(sequence.get(endPos - 1))) {
               if (!replaceLinefeedWithSpace) {
                  break;
               }

               sequence.set(endPos - 1, " ");
            }

            if (processDiffs != null) {
               sequence.set(endPos - 1, processDiffs.apply(sequence.get(endPos - 1)));
            }
         }

         sequence.add(endPos, tagGenerator.apply(tag, true));
      }

   }

   private DiffRowGenerator(Builder builder) {
      this.showInlineDiffs = builder.showInlineDiffs;
      this.ignoreWhiteSpaces = builder.ignoreWhiteSpaces;
      this.oldTag = builder.oldTag;
      this.newTag = builder.newTag;
      this.columnWidth = builder.columnWidth;
      this.mergeOriginalRevised = builder.mergeOriginalRevised;
      this.inlineDiffSplitter = builder.inlineDiffSplitter;
      this.decompressDeltas = builder.decompressDeltas;
      if (builder.equalizer != null) {
         this.equalizer = builder.equalizer;
      } else {
         this.equalizer = this.ignoreWhiteSpaces ? IGNORE_WHITESPACE_EQUALIZER : DEFAULT_EQUALIZER;
      }

      this.reportLinesUnchanged = builder.reportLinesUnchanged;
      this.lineNormalizer = builder.lineNormalizer;
      this.processDiffs = builder.processDiffs;
      this.replaceOriginalLinefeedInChangesWithSpaces = builder.replaceOriginalLinefeedInChangesWithSpaces;
      Objects.requireNonNull(this.inlineDiffSplitter);
      Objects.requireNonNull(this.lineNormalizer);
   }

   public List generateDiffRows(List original, List revised) {
      return this.generateDiffRows(original, DiffUtils.diff(original, revised, this.equalizer));
   }

   public List generateDiffRows(List original, Patch patch) {
      List<DiffRow> diffRows = new ArrayList();
      int endPos = 0;
      List<AbstractDelta<String>> deltaList = patch.getDeltas();
      if (this.decompressDeltas) {
         for(AbstractDelta originalDelta : deltaList) {
            for(AbstractDelta delta : this.decompressDeltas(originalDelta)) {
               endPos = this.transformDeltaIntoDiffRow(original, endPos, diffRows, delta);
            }
         }
      } else {
         for(AbstractDelta delta : deltaList) {
            endPos = this.transformDeltaIntoDiffRow(original, endPos, diffRows, delta);
         }
      }

      for(String line : original.subList(endPos, original.size())) {
         diffRows.add(this.buildDiffRow(DiffRow.Tag.EQUAL, line, line));
      }

      return diffRows;
   }

   private int transformDeltaIntoDiffRow(List original, int endPos, List diffRows, AbstractDelta delta) {
      Chunk<String> orig = delta.getSource();
      Chunk<String> rev = delta.getTarget();

      for(String line : original.subList(endPos, orig.getPosition())) {
         diffRows.add(this.buildDiffRow(DiffRow.Tag.EQUAL, line, line));
      }

      switch (delta.getType()) {
         case INSERT:
            for(String line : rev.getLines()) {
               diffRows.add(this.buildDiffRow(DiffRow.Tag.INSERT, "", line));
            }
            break;
         case DELETE:
            for(String line : orig.getLines()) {
               diffRows.add(this.buildDiffRow(DiffRow.Tag.DELETE, line, ""));
            }
            break;
         default:
            if (this.showInlineDiffs) {
               diffRows.addAll(this.generateInlineDiffs(delta));
            } else {
               for(int j = 0; j < Math.max(orig.size(), rev.size()); ++j) {
                  diffRows.add(this.buildDiffRow(DiffRow.Tag.CHANGE, orig.getLines().size() > j ? (String)orig.getLines().get(j) : "", rev.getLines().size() > j ? (String)rev.getLines().get(j) : ""));
               }
            }
      }

      return orig.last() + 1;
   }

   private List decompressDeltas(AbstractDelta delta) {
      if (delta.getType() == DeltaType.CHANGE && delta.getSource().size() != delta.getTarget().size()) {
         List<AbstractDelta<String>> deltas = new ArrayList();
         int minSize = Math.min(delta.getSource().size(), delta.getTarget().size());
         Chunk<String> orig = delta.getSource();
         Chunk<String> rev = delta.getTarget();
         deltas.add(new ChangeDelta(new Chunk(orig.getPosition(), orig.getLines().subList(0, minSize)), new Chunk(rev.getPosition(), rev.getLines().subList(0, minSize))));
         if (orig.getLines().size() < rev.getLines().size()) {
            deltas.add(new InsertDelta(new Chunk(orig.getPosition() + minSize, Collections.emptyList()), new Chunk(rev.getPosition() + minSize, rev.getLines().subList(minSize, rev.getLines().size()))));
         } else {
            deltas.add(new DeleteDelta(new Chunk(orig.getPosition() + minSize, orig.getLines().subList(minSize, orig.getLines().size())), new Chunk(rev.getPosition() + minSize, Collections.emptyList())));
         }

         return deltas;
      } else {
         return Collections.singletonList(delta);
      }
   }

   private DiffRow buildDiffRow(DiffRow.Tag type, String orgline, String newline) {
      if (this.reportLinesUnchanged) {
         return new DiffRow(type, orgline, newline);
      } else {
         String wrapOrg = this.preprocessLine(orgline);
         if (DiffRow.Tag.DELETE == type && (this.mergeOriginalRevised || this.showInlineDiffs)) {
            wrapOrg = (String)this.oldTag.apply(type, true) + wrapOrg + (String)this.oldTag.apply(type, false);
         }

         String wrapNew = this.preprocessLine(newline);
         if (DiffRow.Tag.INSERT == type) {
            if (this.mergeOriginalRevised) {
               wrapOrg = (String)this.newTag.apply(type, true) + wrapNew + (String)this.newTag.apply(type, false);
            } else if (this.showInlineDiffs) {
               wrapNew = (String)this.newTag.apply(type, true) + wrapNew + (String)this.newTag.apply(type, false);
            }
         }

         return new DiffRow(type, wrapOrg, wrapNew);
      }
   }

   private DiffRow buildDiffRowWithoutNormalizing(DiffRow.Tag type, String orgline, String newline) {
      return new DiffRow(type, StringUtils.wrapText(orgline, this.columnWidth), StringUtils.wrapText(newline, this.columnWidth));
   }

   List normalizeLines(List list) {
      List var10000;
      if (this.reportLinesUnchanged) {
         var10000 = list;
      } else {
         Stream var2 = list.stream();
         Function var10001 = this.lineNormalizer;
         var10001.getClass();
         var10000 = (List)var2.map(var10001::apply).collect(Collectors.toList());
      }

      return var10000;
   }

   private List generateInlineDiffs(AbstractDelta delta) {
      List<String> orig = this.normalizeLines(delta.getSource().getLines());
      List<String> rev = this.normalizeLines(delta.getTarget().getLines());
      String joinedOrig = String.join("\n", orig);
      String joinedRev = String.join("\n", rev);
      List<String> origList = (List)this.inlineDiffSplitter.apply(joinedOrig);
      List<String> revList = (List)this.inlineDiffSplitter.apply(joinedRev);
      List<AbstractDelta<String>> inlineDeltas = DiffUtils.diff(origList, revList, this.equalizer).getDeltas();
      Collections.reverse(inlineDeltas);

      for(AbstractDelta inlineDelta : inlineDeltas) {
         Chunk<String> inlineOrig = inlineDelta.getSource();
         Chunk<String> inlineRev = inlineDelta.getTarget();
         if (inlineDelta.getType() == DeltaType.DELETE) {
            wrapInTag(origList, inlineOrig.getPosition(), inlineOrig.getPosition() + inlineOrig.size(), DiffRow.Tag.DELETE, this.oldTag, this.processDiffs, this.replaceOriginalLinefeedInChangesWithSpaces && this.mergeOriginalRevised);
         } else if (inlineDelta.getType() == DeltaType.INSERT) {
            if (this.mergeOriginalRevised) {
               origList.addAll(inlineOrig.getPosition(), revList.subList(inlineRev.getPosition(), inlineRev.getPosition() + inlineRev.size()));
               wrapInTag(origList, inlineOrig.getPosition(), inlineOrig.getPosition() + inlineRev.size(), DiffRow.Tag.INSERT, this.newTag, this.processDiffs, false);
            } else {
               wrapInTag(revList, inlineRev.getPosition(), inlineRev.getPosition() + inlineRev.size(), DiffRow.Tag.INSERT, this.newTag, this.processDiffs, false);
            }
         } else if (inlineDelta.getType() == DeltaType.CHANGE) {
            if (this.mergeOriginalRevised) {
               origList.addAll(inlineOrig.getPosition() + inlineOrig.size(), revList.subList(inlineRev.getPosition(), inlineRev.getPosition() + inlineRev.size()));
               wrapInTag(origList, inlineOrig.getPosition() + inlineOrig.size(), inlineOrig.getPosition() + inlineOrig.size() + inlineRev.size(), DiffRow.Tag.CHANGE, this.newTag, this.processDiffs, false);
            } else {
               wrapInTag(revList, inlineRev.getPosition(), inlineRev.getPosition() + inlineRev.size(), DiffRow.Tag.CHANGE, this.newTag, this.processDiffs, false);
            }

            wrapInTag(origList, inlineOrig.getPosition(), inlineOrig.getPosition() + inlineOrig.size(), DiffRow.Tag.CHANGE, this.oldTag, this.processDiffs, this.replaceOriginalLinefeedInChangesWithSpaces && this.mergeOriginalRevised);
         }
      }

      StringBuilder origResult = new StringBuilder();
      StringBuilder revResult = new StringBuilder();

      for(String character : origList) {
         origResult.append(character);
      }

      for(String character : revList) {
         revResult.append(character);
      }

      List<String> original = Arrays.asList(origResult.toString().split("\n"));
      List<String> revised = Arrays.asList(revResult.toString().split("\n"));
      List<DiffRow> diffRows = new ArrayList();

      for(int j = 0; j < Math.max(original.size(), revised.size()); ++j) {
         diffRows.add(this.buildDiffRowWithoutNormalizing(DiffRow.Tag.CHANGE, original.size() > j ? (String)original.get(j) : "", revised.size() > j ? (String)revised.get(j) : ""));
      }

      return diffRows;
   }

   private String preprocessLine(String line) {
      return this.columnWidth == 0 ? (String)this.lineNormalizer.apply(line) : StringUtils.wrapText((String)this.lineNormalizer.apply(line), this.columnWidth);
   }

   public static class Builder {
      private boolean showInlineDiffs;
      private boolean ignoreWhiteSpaces;
      private boolean decompressDeltas;
      private BiFunction oldTag;
      private BiFunction newTag;
      private int columnWidth;
      private boolean mergeOriginalRevised;
      private boolean reportLinesUnchanged;
      private Function inlineDiffSplitter;
      private Function lineNormalizer;
      private Function processDiffs;
      private BiPredicate equalizer;
      private boolean replaceOriginalLinefeedInChangesWithSpaces;

      private Builder() {
         this.showInlineDiffs = false;
         this.ignoreWhiteSpaces = false;
         this.decompressDeltas = true;
         this.oldTag = (tag, f) -> f ? "<span class=\"editOldInline\">" : "</span>";
         this.newTag = (tag, f) -> f ? "<span class=\"editNewInline\">" : "</span>";
         this.columnWidth = 0;
         this.mergeOriginalRevised = false;
         this.reportLinesUnchanged = false;
         this.inlineDiffSplitter = DiffRowGenerator.SPLITTER_BY_CHARACTER;
         this.lineNormalizer = DiffRowGenerator.LINE_NORMALIZER_FOR_HTML;
         this.processDiffs = null;
         this.equalizer = null;
         this.replaceOriginalLinefeedInChangesWithSpaces = false;
      }

      public Builder showInlineDiffs(boolean val) {
         this.showInlineDiffs = val;
         return this;
      }

      public Builder ignoreWhiteSpaces(boolean val) {
         this.ignoreWhiteSpaces = val;
         return this;
      }

      public Builder reportLinesUnchanged(boolean val) {
         this.reportLinesUnchanged = val;
         return this;
      }

      public Builder oldTag(BiFunction generator) {
         this.oldTag = generator;
         return this;
      }

      public Builder oldTag(Function generator) {
         this.oldTag = (tag, f) -> (String)generator.apply(f);
         return this;
      }

      public Builder newTag(BiFunction generator) {
         this.newTag = generator;
         return this;
      }

      public Builder newTag(Function generator) {
         this.newTag = (tag, f) -> (String)generator.apply(f);
         return this;
      }

      public Builder processDiffs(Function processDiffs) {
         this.processDiffs = processDiffs;
         return this;
      }

      public Builder columnWidth(int width) {
         if (width >= 0) {
            this.columnWidth = width;
         }

         return this;
      }

      public DiffRowGenerator build() {
         return new DiffRowGenerator(this);
      }

      public Builder mergeOriginalRevised(boolean mergeOriginalRevised) {
         this.mergeOriginalRevised = mergeOriginalRevised;
         return this;
      }

      public Builder decompressDeltas(boolean decompressDeltas) {
         this.decompressDeltas = decompressDeltas;
         return this;
      }

      public Builder inlineDiffByWord(boolean inlineDiffByWord) {
         this.inlineDiffSplitter = inlineDiffByWord ? DiffRowGenerator.SPLITTER_BY_WORD : DiffRowGenerator.SPLITTER_BY_CHARACTER;
         return this;
      }

      public Builder inlineDiffBySplitter(Function inlineDiffSplitter) {
         this.inlineDiffSplitter = inlineDiffSplitter;
         return this;
      }

      public Builder lineNormalizer(Function lineNormalizer) {
         this.lineNormalizer = lineNormalizer;
         return this;
      }

      public Builder equalizer(BiPredicate equalizer) {
         this.equalizer = equalizer;
         return this;
      }

      public Builder replaceOriginalLinefeedInChangesWithSpaces(boolean replace) {
         this.replaceOriginalLinefeedInChangesWithSpaces = replace;
         return this;
      }
   }
}
