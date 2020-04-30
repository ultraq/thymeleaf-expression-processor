
Changelog
=========

### 2.0.0
 - Minimum required Java version is now 8
 - Upgrade to Groovy 3.0.3

### 1.2.0
 - Upgrade Groovy dependency to 2.5.11 and utilize some 2.5.x features
 - Swap out JUnit for Spock

### 1.1.3
 - `null` check before attempting to check a fragment expression, potential fix
   for this issue over in the layout dialect:
   https://github.com/ultraq/thymeleaf-layout-dialect/issues/151

### 1.1.2
 - Fix fragment expression wrapping on multiline inputs
   ([#1](https://github.com/ultraq/thymeleaf-expression-processor/issues/1))

### 1.1.1
 - Don't log a warning if the expression to wrap has already been encountered

### 1.1.0
 - Added ability to process fragment expressions in a backwards compatible (with
   Thymeleaf 2) manner.

### 1.0.0
 - Initial release
