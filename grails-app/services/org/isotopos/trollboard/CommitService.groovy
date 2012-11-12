package org.isotopos.trollboard

class CommitService {

  def receiveAndProcessMessage(String message){
    def issueStatus = []

    if(message) {
      def rawIssueLabels = message =~ /\w+\D.[?=#]/
      def issueLabels = []
      rawIssueLabels.each { label ->
        issueLabels << (label?.replace(" #",'')?.trim())?.toUpperCase()
      }

      int index = 0;
      issueLabels.each { label ->
        def start = message.toUpperCase().indexOf(label)
        def end = message.toUpperCase().length()
        if( issueLabels.size() > (index+1) ){
          end = message.toUpperCase().indexOf(issueLabels.get(++index))
        }

        def issueNumbers = message.toUpperCase().substring(start, end) =~ /#\d+/
        def issuesNumbers = []
        issueNumbers.each{ issue ->
          issuesNumbers << issue
        }

        def labelsAndIssues = [:]
        labelsAndIssues."${label}" = issuesNumbers
        issueStatus << labelsAndIssues
      }
    }

    issueStatus
  }
}