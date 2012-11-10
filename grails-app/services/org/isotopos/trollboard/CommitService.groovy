package org.isotopos.trollboard

class CommitService {

  def receiveAndProcessMessage(String message){
    def issueStatus = []

    if(message) {
      // "Fix #1 commit message"
      def issueNumber = message =~ /\w+\s*#+\d+/
      def issues = issueNumber.collect { it }
      println issueNumber.collect { it }
      println issues
      issues.each{ i ->
        
        def statusAndNumber = i.split(' ')
        def status = statusAndNumber[0].trim().toUpperCase()
        println status
        if(!issueStatus."$status"){
          issueStatus."$status" << ["${statusAndNumber[1].trim()}"]
          println issueStatus."$status"
        }
        else{
          def lista = issueStatus."$status"
          lista << "${statusAndNumber[1].trim()}"
          issueStatus."$status" = lista
        }
        println statusAndNumber
      }

    }

    issueStatus 
  }

}