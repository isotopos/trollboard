package org.isotopos.trollboard

class CommitService {

  def receiveAndProcessMessage(String message){
    def issueStatus = []

    if(message) {
      // "Fix #1 commit message"
      def rawIssueLabels = message =~ /\w+\D.[?=\s#]/
      def issueLabels = []
      rawIssueLabels.each { it -> 
        issueLabels << it.replace(" #",'') 
      }

      def labelsAndIssues = [:]
      int index = 0;
      issueLabels.each { label ->
        def start = message.indexOf(label)
        def end = message.length()
        if( issueLabels.size() > (index+1) ){
          end = message.indexOf(issueLabels.get(++index))
        }

        def issueNumbers = message.substring(start, end) =~ /#\d+/
        def issuesNumbers = []
        issueNumbers.each{ issue ->
          issuesNumbers << [issue]
        }
        labelsAndIssues."$label" = issuesNumbers
      }

      issueStatus 
    }
  }
}