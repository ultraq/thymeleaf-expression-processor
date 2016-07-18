/* 
 * Copyright 2016, Emanuel Rabina (http://www.ultraq.net.nz/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.net.ultraq.thymeleaf.expressions

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.thymeleaf.context.IExpressionContext
import org.thymeleaf.standard.expression.FragmentExpression
import org.thymeleaf.standard.expression.IStandardExpression
import org.thymeleaf.standard.expression.StandardExpressions

import java.util.regex.Pattern

/**
 * A simplified API for working with Thymeleaf expressions.
 * 
 * @author Emanuel Rabina
 */
class ExpressionProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ExpressionProcessor)
	private static final Pattern THYMELEAF_3_FRAGMENT_EXPRESSION = ~/^~\{.+\}$/
	private static final HashSet<String> oldFragmentExpressions = []

	private final IExpressionContext context

	/**
	 * Constructor, sets the execution context.
	 * 
	 * @param context
	 */
	ExpressionProcessor(IExpressionContext context) {

		this.context = context
	}

	/**
	 * Parses an expression, returning the matching expression type.
	 * 
	 * @param expression
	 * @return Matching expression type.
	 */
	IStandardExpression parse(String expression) {

		return StandardExpressions.getExpressionParser(context.configuration)
			.parseExpression(context, expression)
	}

	/**
	 * Parses an expression under the assumption it is a fragment expression.
	 * This method will wrap fragment expressions written in Thymeleaf 2 syntax as
	 * a temporary backwards compatibility measure for those migrating their web
	 * apps to Thymeleaf 3.
	 * 
	 * @param expression
	 * @return A fragment expression.
	 */
	FragmentExpression parseFragmentExpression(String expression) {

		if (!expression.matches(THYMELEAF_3_FRAGMENT_EXPRESSION)) {
			if (!oldFragmentExpressions.contains(expression)) {
				logger.warn(
					'Fragment expression "{}" is being wrapped as a Thymeleaf 3 fragment expression (~{...}) for backwards compatibility purposes.  ' +
					'This wrapping will be dropped in the next major version of the expression processor, so please rewrite as a Thymeleaf 3 fragment expression to future-proof your code.  ' +
					'See https://github.com/thymeleaf/thymeleaf/issues/451 for more information.',
					expression)
				oldFragmentExpressions << expression
			}
			return parse("~{${expression}}")
		}

		return parse(expression)
	}

	/**
	 * Parses and executes an expression, returning the result of the expression
	 * having been parsed and executed.
	 * 
	 * @param expression
	 * @return The result of the expression being executed.
	 */
	Object process(String expression) {

		return parse(expression).execute(context)
	}

	/**
	 * Parse and execute an expression, returning the result as a string.  Useful
	 * for expressions that expect a simple result.
	 * 
	 * @param expression
	 * @return The expression as a string.
	 */
	String processAsString(String expression) {

		return process(expression).toString()
	}
}
