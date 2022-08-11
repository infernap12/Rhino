package dev.latvian.mods.rhino.js;

import dev.latvian.mods.rhino.ContextJS;

public class BooleanJS extends ObjectJS {
	public static PrototypeJS PROTOTYPE = PrototypeJS.create("Boolean")
			.constructor(BooleanJS::construct);

	public static final BooleanJS TRUE = new BooleanJS(true);

	public static final BooleanJS FALSE = new BooleanJS(false);
	public final boolean value;

	public static BooleanJS of(boolean number) {
		return number ? TRUE : FALSE;
	}

	private static BooleanJS construct(ContextJS cx, Object[] args) {
		if (args.length == 0) {
			return FALSE;
		} else if (args[0] instanceof BooleanJS b) {
			return b;
		} else if (args[0] instanceof Boolean b) {
			return of(b);
		} else if (args[0] instanceof NumberJS b) {
			return of(b.asNumber() != 0.0);
		} else if (args[0] instanceof Number n) {
			return of(n.doubleValue() != 0.0);
		} else {
			return TRUE;
		}
	}

	private BooleanJS(boolean value) {
		super(PROTOTYPE);
		this.value = value;
	}

	@Override
	public Object unwrap() {
		return value;
	}

	@Override
	public String asString() {
		return value ? "true" : "false";
	}

	@Override
	public double asNumber() {
		return value ? 1.0 : 0.0;
	}
}
