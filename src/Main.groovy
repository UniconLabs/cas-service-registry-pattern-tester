import groovy.json.JsonSlurper
import groovy.util.logging.Log
import org.springframework.util.AntPathMatcher

@Log
class Main {
    static void main(String[] args) {
        def cli = new CliBuilder(usage: 'Main.groovy [--style <ant|regex>] [--help] --pattern <value|path> --url <value>')
        cli.width = 100

        cli.with {
            s longOpt: 'style', args:1, argName: 'value', 'Specify the service pattern style. Default is Ant'
            h longOpt: 'help', 'Show usage information'
            p longOpt: 'pattern', args:1, argName: 'value',
                    'The ant/regex pattern to test against the service; Could also be a path to the JSON service registry file' +
                    'in which case all service patterns in the registry are matches against the given url'
            u longOpt: 'url', args:1, argName: 'value', 'The url that is to be evaluated against the pattern'
        }

        def options = cli.parse(args)
        if (!options || args.size() == 0 || options.help || !options.u || !options.p) {
            cli.usage()
            return
        }
        println()

        def m = new Main()

        def url = new File(options.p)
        if (url.isFile() && url.exists()) {
            log.info("Using [" + url.getCanonicalPath() + "] to evaluate matches against the specified url [" + options.u + "]")
            def jsonParser = new JsonSlurper()
            def result = jsonParser.parseText(url.getText())
            result.services.each() {
                def pattern = it.serviceId;
                if (pattern.startsWith("^")) {
                    m.testPatternRegex(pattern, options.u)
                } else {
                    m.testPatternAnt(pattern, options.u)
                }
            }
        } else {
            def styleName = "Ant"
            if (options.s) {
                styleName = options.s[0].toUpperCase() + options.s[1..-1]
            }
            m."testPattern$styleName"(options.p, options.u)
        }
    }

    def testPatternRegex(pattern, url) {
        if (url.matches(pattern)) {
            log.info("Specified url [" + url + "] matches the given Regex pattern [" + pattern + "]")
        } else {
            log.info("Specified url [" + url + "] does not match the Regex given pattern [" + pattern + "]")
        }
        println()
    }

    def testPatternAnt(pattern, url) {
        def ant = new AntPathMatcher()
        if (ant.match(pattern, url)) {
            log.info("Specified url [" + url + "] matches the given Ant pattern [" + pattern + "]")
        } else {
            log.info("Specified url [" + url + "] does not match the Ant given pattern [" + pattern + "]")
        }
        println()
    }
}

