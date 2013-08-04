cas-service-registry-pattern-tester
===================================

A Groovy CLI utility to test service registry definitions

## Usage

```
$ groovy -cp .groovy/lib/* src/Main.groovy -h
usage: Main.groovy [style=<ant|regex>] [help] [pattern <value>] [url <value>]
 -h,--help              Show usage information
 -p,--pattern <value>   The ant/regex pattern to test against the service
 -s,--style <value>     Specify the service pattern style
 -u,--url <value>       The url that is to be evaluated against the pattern

```

## Examples

```
$ groovy -cp .groovy/lib/* src/Main.groovy -p http?://** -u https://www.google.com -s ant

Aug 04, 2013 3:11:36 PM java_util_logging_Logger$info call
INFO: Specified url [https://www.google.com] matches the given pattern [http?://**]
```
