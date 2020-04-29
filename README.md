
Thymeleaf Expression Processor
==============================

[![Build Status](https://travis-ci.com/ultraq/thymeleaf-expression-processor.svg?branch=master)](https://travis-ci.com/ultraq/thymeleaf-expression-processor)
[![Coverage Status](https://coveralls.io/repos/github/ultraq/thymeleaf-expression-processor/badge.svg?branch=master)](https://coveralls.io/github/ultraq/thymeleaf-expression-processor?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/nz.net.ultraq.thymeleaf/thymeleaf-expression-processor.svg?maxAge=3600)](http://search.maven.org/#search|ga|1|g%3A%22nz.net.ultraq.thymeleaf%22%20AND%20a%3A%22thymeleaf-expression-processor%22)

A simplified API for working with Thymeleaf expressions.


Installation
------------

Minimum of Java 8 and Thymeleaf 3.0 required.

### For Maven and Maven-compatible dependency managers
Add a dependency to your project with the following co-ordinates:

 - GroupId: `nz.net.ultraq.thymeleaf`
 - ArtifactId: `thymeleaf-expression-processor`
 - Version: `2.0.0`

Check the [project releases](https://github.com/ultraq/thymeleaf-expression-processor/releases)
for a list of available versions.  Each release page also includes a
downloadable JAR if you want to manually add it to your project classpath.


Usage
-----

Create an instance of `ExpressionProcessor` using the current expression context
(the context object passed to Thymeleaf processors is a type of `IExpressionContext`),
and then parse/process any string that is a Thymeleaf expression, using any of
the instance methods described below:

#### parse(String expression) -> IStandardExpression

Parses an expression, returning the matching expression type.

#### parseFragmentExpression(String fragmentExpression) -> FragmentExpression

Parses an expression under the assumption it is a fragment expression.  This
method will wrap fragment expressions written in Thymeleaf 2 syntax as a
backwards compatibility measure for those migrating their web apps to Thymeleaf
3.  (This is because Thymeleaf 3 currently does the same, but expect this method
to go away when Thymeleaf starts enforcing the new fragment expression syntax
itself.)

#### process(String expression) -> Object

Parse and executes an expression, returning whatever the type of the expression
result is.

#### pocessAsString(String expression) -> String

Parse and execute an expression, returning the result as a string.  Useful for
expressions that expect a simple result.
