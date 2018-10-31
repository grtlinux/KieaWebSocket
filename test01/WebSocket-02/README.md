WebSocket-02
============
HelloWorld program of WebSocket!

Introduction
------------

Coordinates for core library (see all on [Maven Central]):

```xml
<dependency>
    <groupId>com.atlassian.commonmark</groupId>
    <artifactId>commonmark</artifactId>
    <version>0.11.0</version>
</dependency>
```

The module names to use in Java 9 are `org.commonmark`,
`org.commonmark.ext.autolink`, etc, corresponding to package names.

See the [spec.txt](commonmark-test-util/src/main/resources/spec.txt)
file if you're wondering which version of the spec is currently
implemented. Also check out the [CommonMark dingus] for getting familiar
with the syntax or trying out edge cases.

Usage
-----

#### Parse and render to HTML


```java
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

Parser parser = Parser.builder().build();
Node document = parser.parse("This is *Sparta*");
HtmlRenderer renderer = HtmlRenderer.builder().build();
renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
```

This uses the parser and renderer with default options. Both builders have
methods for configuring their behavior, e.g. calling `escapeHtml(true)` on
`HtmlRenderer` will escape raw HTML tags and blocks. For all available
options, see methods on the builders.

See also
--------

* [Markwon](https://github.com/noties/Markwon): Android library for rendering markdown as system-native Spannables
* [flexmark-java](https://github.com/vsch/flexmark-java): Fork that added support for a lot more syntax and flexibility


License
-------

Copyright (c) 2015-2018 Atlassian and others.

BSD (2-clause) licensed, see LICENSE.txt file.

[CommonMark]: http://commonmark.org/
[Markdown]: https://daringfireball.net/projects/markdown/
[commonmark.js]: https://github.com/jgm/commonmark.js
[CommonMark Dingus]: http://spec.commonmark.org/dingus/
[Maven Central]: https://search.maven.org/#search|ga|1|g%3A%22com.atlassian.commonmark%22
[Semantic Versioning]: http://semver.org/
[autolink-java]: https://github.com/robinst/autolink-java
[gfm-tables]: https://help.github.com/articles/organizing-information-with-tables/



