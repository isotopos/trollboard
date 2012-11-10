package org.isotopos.trollboard

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class ProcessingCommitSpec extends Specification {

  @Unroll("When commit is #commit the result is #result")
  def "Processing commit"(){
    setup : "Initializing service"
      def commitService = new CommitService()

    when : "Sending message to process"
      def issueStatusResult = commitService.receiveAndProcessMessage(commit)

    then :
      assert result == issueStatusResult

    where :
      commit                                 || result
      null                                   || []
      ""                                     || []
      "nothing"                              || []
      "#4 message commit"                    || []
      "Fix 1"                                || []
      "Fix #1 commit message"                || [ ["FIX" : ["#1"] ] ]
      "Fix #2 wip #3 commit message"         || [ ["FIX" : ["#2"], "WIP" : ["#3"] ] ]
      "close #3 message"                     || [ ["CLOSE" : ["#3"] ] ]
      "test #5 message"                      || [ ["TEST" : ["#5"] ] ]
      "test #6 #7"                           || [ ["TEST" : ["#6", "#7"] ] ]
      "test #6 #7, fix #11"                  || [ ["TEST" : ["#6", "#7"], "FIX" : ["#11"] ] ]
      "fixed some fucking error qa #25"      || [ ["QA" : ["#25"] ] ]
      // $50,000 For implement it (^^,)
      // "Correct, I fix issue #8"              || [ ["FIX" : ["#8"] ] ]
      // "Correct, I fix issue #8 and #10"      || [ ["FIX" : ["#8", "#10"] ] ]
      // "Correct, I fix issue #8 and test #9"  || [ ["FIX" : ["#8", "#10"], "TEST" : ["#9"] ] ]
      // "Correct, I fix issue #8, test #9"     || [ ["FIX" : ["#8"], "TEST" : ["#9"] ] ]
      // "Correct, I fix issue #8 #10, test #9" || [ ["FIX" : ["#8", "#10"], "TEST" : ["#9"] ] ]
      "test #4, close #4"                    || [ ["TEST": ["#4"] ] ]
  }
}