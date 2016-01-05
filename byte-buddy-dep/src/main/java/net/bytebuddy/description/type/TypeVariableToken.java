package net.bytebuddy.description.type;

import net.bytebuddy.description.ByteCodeElement;

import java.util.Collections;
import java.util.List;

/**
 * A tokenized representation of a type variable.
 */
public class TypeVariableToken implements ByteCodeElement.Token<TypeVariableToken> {

    /**
     * The type variable's symbol.
     */
    private final String symbol;

    /**
     * The type variable's upper bounds.
     */
    private final List<? extends TypeDescription.Generic> upperBounds;

    public TypeVariableToken(String symbol, TypeDescription.Generic upperBound) {
        this(symbol, Collections.singletonList(upperBound));
    }

    /**
     * Creates a new type variable token.
     *
     * @param symbol      The type variable's symbol.
     * @param upperBounds The type variable's upper bounds.
     */
    public TypeVariableToken(String symbol, List<? extends TypeDescription.Generic> upperBounds) {
        this.symbol = symbol;
        this.upperBounds = upperBounds;
    }

    public static TypeVariableToken of(TypeDescription.Generic typeVariable, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
        return new TypeVariableToken(typeVariable.getSymbol(), typeVariable.getUpperBounds().accept(visitor));
    }

    /**
     * Returns the type variable's symbol.
     *
     * @return The type variable's symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the type variable's upper bounds.
     *
     * @return The type variable's upper bounds.
     */
    public TypeList.Generic getUpperBounds() {
        return new TypeList.Generic.Explicit(upperBounds);
    }

    @Override
    public TypeVariableToken accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
        return new TypeVariableToken(getSymbol(), getUpperBounds().accept(visitor));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TypeVariableToken that = (TypeVariableToken) other;
        return symbol.equals(that.symbol) && upperBounds.equals(that.upperBounds);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + upperBounds.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TypeVariableToken{" +
                "symbol='" + symbol + '\'' +
                ", upperBounds=" + upperBounds +
                '}';
    }
}
