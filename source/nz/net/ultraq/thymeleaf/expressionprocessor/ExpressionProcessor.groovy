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

package nz.net.ultraq.thymeleaf.expressionprocessor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.thymeleaf.context.IExpressionContext
import org.thymeleaf.standard.expression.FragmentExpression
import org.thymeleaf.standard.expression.IStandardExpression
import org.thymeleaf.standard.expression.StandardExpressions

import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor
import groovy.transform.stc.POJO

/**
 * A simplified API for working with Thymeleaf expressions.
 * 
 * @author Emanuel Rabina
 */
@CompileStatic
@POJO
@TupleConstructor(defaults = false)
class ExpressionProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ExpressionProcessor)

	@SuppressWarnings('FieldName')
	private static final HashSet<String> oldFragmentExpressions = []

	final IExpressionContext context

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
	 * a backwards compatibility measure for those migrating their web apps to
	 * Thymeleaf 3.  (This is because Thymeleaf 3 currently does the same, but
	 * expect this method to go away when Thymeleaf starts enforcing the new
	 * fragment expression syntax itself.)
	 * 
	 * @param expression
	 * @return A fragment expression.
	 */
	@SuppressWarnings('ParameterReassignment')
	FragmentExpression parseFragmentExpression(String expression) {

		if (expression && !(expression ==~ /(?s)^~\{.+\}$/)) {
			if (!oldFragmentExpressions.contains(expression)) {
				logger.warn(
					'Fragment expression "{}" is being wrapped as a Thymeleaf 3 fragment expression (~{...}) for backwards compatibility purposes.  ' +
					'This wrapping will be dropped in the next major version of the expression processor, so please rewrite as a Thymeleaf 3 fragment expression to future-proof your code.  ' +
					'See https://github.com/thymeleaf/thymeleaf/issues/451 for more information.',
					expression)
				oldFragmentExpressions << expression
			}
			expression = "~{${expression}}"
		}

		return parse(expression) as FragmentExpression
	}

	/**
	 * Parse and executes an expression, returning whatever the type of the
	 * expression result is.
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
