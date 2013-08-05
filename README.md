cas-service-registry-pattern-tester
===================================

A Groovy CLI utility to test service registry definitions. Ant pattern matching is based on the `spring-core-3.2.3` library.

## Usage

```
$ groovy -cp .groovy/lib/* src/Main.groovy
usage: Main.groovy [--style <ant|regex>] [--help] --pattern <value> --url <value>
 -h,--help              Show usage information
 -p,--pattern <value>   The ant/regex pattern to test against the service; Could also be a path to
                        the JSON service registry filein which case all service patterns in the
                        registry are matches against the given url
 -s,--style <value>     Specify the service pattern style. Default is Ant
 -u,--url <value>       The url that is to be evaluated against the pattern
```

## Examples

Single url:
```
$ groovy -cp .groovy/lib/* src/Main.groovy -p http?://** -u https://www.google.com

Aug 04, 2013 4:46:37 PM java_util_logging_Logger$info call
INFO: Specified url [https://www.google.com] matches the given Ant pattern [http?://**]
```

[JSON service registry](https://github.com/Unicon/cas-addons/wiki/Configuring%20JSON%20Service%20Registry):
```
$ groovy -cp .groovy/lib/* src/Main.groovy -u http://www.yahoo.com -p c:\\etc\\cas\\servicesRegistry.conf

Aug 04, 2013 7:22:06 PM java_util_logging_Logger$info call
INFO: Using [C:\etc\cas\servicesRegistry.conf] to evaluate matches against the specified url [http://www.yahoo.com]
Aug 04, 2013 7:22:06 PM java_util_logging_Logger$info call
INFO: Specified url [http://www.yahoo.com] matches the given Regex pattern [^(https?|imaps?)://.*]
```
