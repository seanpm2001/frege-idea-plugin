package org.fregelang.plugin.idea;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.jcraft.jsch.HASH;
import org.fregelang.plugin.idea.psi.FregeTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FregeSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey END_OF_LINE_COMMENT = createTextAttributesKey("SIMPLE_COMMENT", SyntaxHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT       = createTextAttributesKey("SIMPLE_BLOCK_COMMENT", SyntaxHighlighterColors.JAVA_BLOCK_COMMENT);


    /*
    AS_KW |
    CASE_KW |
    CLASS_KW |
    DATA_KW |
    DEFAULT_KW |
    DO_KW |
    ELSE_KW |
    EXPORT |
    HIDING_KW |
    IF_KW |
    IMPORT_KW |
    IN_KW |
    INFIX_KW |
    INFIXL_KW |
    INFIXR_KW |
    INSTANCE_KW |
    FORALL_KW |
    FOREIGN_KW |
    LET_KW |
    MODULE_KW |
    OF_KW |
    THEN_KW |
    QUALIFIED_KW |
    TYPE_KW |
    WHERE_KW |
    PRAGMA |
    NUMBER |
    CHARACTER |
    STRING |
*/




    static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("SIMPLE_BAD_CHARACTER",
        new TextAttributes(Color.RED, null, null, null, Font.BOLD));

    private static final TextAttributesKey[] BAD_CHAR_KEYS      = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] COMMENT_KEYS       = new TextAttributesKey[]{END_OF_LINE_COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS         = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new FregeLexer((Reader) null));
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(FregeTypes.BLOCK_COMMENT)) {
            return BLOCK_COMMENT_KEYS;
        } else if (tokenType.equals(FregeTypes.PRAGMA)) {
            return BLOCK_COMMENT_KEYS;
        } else if (tokenType.equals(FregeTypes.END_OF_LINE_COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}