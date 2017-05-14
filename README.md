
Thymeleaf Expression Processor
==============================

[![Build Status](https://travis-ci.org/ultraq/thymeleaf-expression-processor.svg?branch=master)](https://travis-ci.org/ultraq/thymeleaf-expression-processor)
[![Coverage Status](https://coveralls.io/repos/github/ultraq/thymeleaf-expression-processor/badge.svg?branch=master)](https://coveralls.io/github/ultraq/thymeleaf-expression-processor?branch=master)
[![GitHub Tag](https://img.shields.io/github/tag/ultraq/thymeleaf-expression-processor.svg?maxAge=3600)](https://github.com/ultraq/thymeleaf-expression-processor/tags)
[![Maven Central](https://img.shields.io/maven-central/v/nz.net.ultraq.thymeleaf/thymeleaf-expression-processor.svg?maxAge=3600)](http://search.maven.org/#search|ga|1|g%3A%22nz.net.ultraq.thymeleaf%22%20AND%20a%3A%22thymeleaf-expression-processor%22)
[![License](https://img.shields.io/github/license/ultraq/thymeleaf-expression-processor.svg?maxAge=2592000)](https://github.com/ultraq/thymeleaf-expression-processor/blob/master/LICENSE.txt)

A simplified API for working with Thymeleaf expressions.


Installation
------------

Minimum of Java 7 and Thymeleaf 3.0 required.

### For Maven and Maven-compatible dependency managers
Add a dependency to your project with the following co-ordinates:

 - GroupId: `nz.net.ultraq.thymeleaf`
 - ArtifactId: `thymeleaf-expression-processor`
 - Version: (as per the badges above)


Usage
-----

Create an instance of `ExpressionProcessor` using the current expression context
(an `ITemplateContext`, one of the parameters passed to processors, extends the
expression context, so can be used), and then parse or process any string that
is a Thymeleaf expression, using any of the instance methods.
