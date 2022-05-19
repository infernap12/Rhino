package dev.latvian.mods.unit.operator;

import dev.latvian.mods.unit.Unit;
import dev.latvian.mods.unit.UnitVariables;
import dev.latvian.mods.unit.token.UnitSymbol;

public class GteOpUnit extends BooleanOpUnit {
	public GteOpUnit(Unit left, Unit right) {
		super(UnitSymbol.GTE, left, right);
	}

	@Override
	public boolean getBoolean(UnitVariables variables) {
		return left.get(variables) >= right.get(variables);
	}
}
