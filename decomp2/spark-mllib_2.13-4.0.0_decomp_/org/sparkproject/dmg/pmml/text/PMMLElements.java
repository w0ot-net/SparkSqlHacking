package org.sparkproject.dmg.pmml.text;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field DOCUMENTTERMMATRIX_EXTENSIONS = ReflectionUtil.getField(DocumentTermMatrix.class, "extensions");
   Field DOCUMENTTERMMATRIX_MATRIX = ReflectionUtil.getField(DocumentTermMatrix.class, "matrix");
   Field TEXTCORPUS_EXTENSIONS = ReflectionUtil.getField(TextCorpus.class, "extensions");
   Field TEXTCORPUS_TEXTDOCUMENTS = ReflectionUtil.getField(TextCorpus.class, "textDocuments");
   Field TEXTDICTIONARY_EXTENSIONS = ReflectionUtil.getField(TextDictionary.class, "extensions");
   Field TEXTDICTIONARY_TAXONOMY = ReflectionUtil.getField(TextDictionary.class, "taxonomy");
   Field TEXTDICTIONARY_ARRAY = ReflectionUtil.getField(TextDictionary.class, "array");
   Field TEXTDOCUMENT_EXTENSIONS = ReflectionUtil.getField(TextDocument.class, "extensions");
   Field TEXTMODEL_EXTENSIONS = ReflectionUtil.getField(TextModel.class, "extensions");
   Field TEXTMODEL_MININGSCHEMA = ReflectionUtil.getField(TextModel.class, "miningSchema");
   Field TEXTMODEL_OUTPUT = ReflectionUtil.getField(TextModel.class, "output");
   Field TEXTMODEL_MODELSTATS = ReflectionUtil.getField(TextModel.class, "modelStats");
   Field TEXTMODEL_MODELEXPLANATION = ReflectionUtil.getField(TextModel.class, "modelExplanation");
   Field TEXTMODEL_TARGETS = ReflectionUtil.getField(TextModel.class, "targets");
   Field TEXTMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(TextModel.class, "localTransformations");
   Field TEXTMODEL_TEXTDICTIONARY = ReflectionUtil.getField(TextModel.class, "textDictionary");
   Field TEXTMODEL_TEXTCORPUS = ReflectionUtil.getField(TextModel.class, "textCorpus");
   Field TEXTMODEL_DOCUMENTTERMMATRIX = ReflectionUtil.getField(TextModel.class, "documentTermMatrix");
   Field TEXTMODEL_TEXTMODELNORMALIZATION = ReflectionUtil.getField(TextModel.class, "textModelNormalization");
   Field TEXTMODEL_TEXTMODELSIMILIARITY = ReflectionUtil.getField(TextModel.class, "textModelSimiliarity");
   Field TEXTMODEL_MODELVERIFICATION = ReflectionUtil.getField(TextModel.class, "modelVerification");
   Field TEXTMODELNORMALIZATION_EXTENSIONS = ReflectionUtil.getField(TextModelNormalization.class, "extensions");
   Field TEXTMODELSIMILIARITY_EXTENSIONS = ReflectionUtil.getField(TextModelSimiliarity.class, "extensions");
}
