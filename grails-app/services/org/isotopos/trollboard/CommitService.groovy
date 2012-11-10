package org.isotopos.trollboard

class CommitService {

  def receiveAndProcessMessage(String message){
    def issueStatus = []

    if(message) {
      // "Fix #1 commit message"
      def issueNumber = message =~ /\w\s*#+\d/
      issueNumber.each { println it }

    }

    issueStatus 
  }

}