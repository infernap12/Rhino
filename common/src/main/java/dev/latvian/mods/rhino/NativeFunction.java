/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.latvian.mods.rhino;

import java.io.Serial;

/**
 * This class implements the Function native object.
 * See ECMA 15.3.
 *
 * @author Norris Boyd
 */
public abstract class NativeFunction extends BaseFunction {

	@Serial
	private static final long serialVersionUID = 8713897114082216401L;

	public final void initScriptFunction(Context cx, Scriptable scope) {
		initScriptFunction(cx, scope, isGeneratorFunction());
	}

	public final void initScriptFunction(Context cx, Scriptable scope, boolean es6GeneratorFunction) {
		ScriptRuntime.setFunctionProtoAndParent(cx, this, scope, es6GeneratorFunction);
	}

	@Override
	public int getLength() {
		return getParamCount();
	}

	@Override
	public int getArity() {
		return getParamCount();
	}

	/**
	 * Resume execution of a suspended generator.
	 *
	 * @param cx        The current context
	 * @param scope     Scope for the parent generator function
	 * @param operation The resumption operation (next, send, etc.. )
	 * @param state     The generator state (has locals, stack, etc.)
	 * @param value     The return value of yield (if required).
	 * @return The next yielded value (if any)
	 */
	public Object resumeGenerator(Context cx, Scriptable scope, int operation, Object state, Object value) {
		throw new EvaluatorException("resumeGenerator() not implemented");
	}


	/**
	 * Get number of declared parameters. It should be 0 for scripts.
	 */
	protected abstract int getParamCount();

	/**
	 * Get number of declared parameters and variables defined through var
	 * statements.
	 */
	protected abstract int getParamAndVarCount();

	/**
	 * Get parameter or variable name.
	 * If <code>index &lt; {@link #getParamCount()}</code>, then return the name of the
	 * corresponding parameter. Otherwise return the name of variable.
	 */
	protected abstract String getParamOrVarName(int index);

	/**
	 * Get parameter or variable const-ness.
	 * If <code>index &lt; {@link #getParamCount()}</code>, then return the const-ness
	 * of the corresponding parameter. Otherwise return whether the variable is
	 * const.
	 */
	protected boolean getParamOrVarConst(int index) {
		// By default return false to preserve compatibility with existing
		// classes subclassing this class, which are mostly generated by jsc
		// from earlier Rhino versions. See Bugzilla #396117.
		return false;
	}
}

