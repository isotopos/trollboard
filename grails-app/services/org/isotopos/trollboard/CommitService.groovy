package org.isotopos.trollboard

class CommitService {

  def receiveAndProcessMessage(String message){
    def issueStatus = []

    if(message) {
      // "Fix #1 commit message"
      def issueNumber = message =~ /\w+\s*#+\d+/
      def issues = issueNumber.collect { it }

      issues.each{ i ->
        def statusAndNumber = i.split(' ')
        println statusAndNumber
        def label = statusAndNumber[0].trim().toUpperCase()
        def issue = statusAndNumber[1].trim()
        def mapa = [:]
        mapa["$label"] = [issue]
        issueStatus << mapa
      }

    }

    issueStatus 
  }

}