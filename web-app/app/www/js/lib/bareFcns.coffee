define [
  'jquery'
], ($) ->
  'use strict'
  fcn = {}

  #Make tabs first hide  all from parent selector, then show of apply
  #a class to a child_id_show
  fcn.tabs = (parent_selector, child_selector, child_id_show, add_class) ->
    $elements = $(parent_selector).find(child_selector)
    unless add_class
      $elements.hide()
      $(child_id_show).show()
    else
      $elements.removeClass add_class
      $(child_id_show).addClass add_class

  #Format a date to string YYYYmmdd  20120931
  fcn.formatDate = (date)->
    fullyear = date.getFullYear()
    month = "" + (date.getMonth()+1)
    day = "" + date.getDate()
    # add leading zero if the length equals 1
    month = "0" + month  if month.length is 1
    day = "0" + day  if day.length is 1
    dateStr = "" + fullyear + month + day
    dateStr

  #Format a number to currency
  fcn.formatCurrency = (val)->
    val =  parseFloat(val) or 0.0
    result = Math.round(val * 100) / 100
    result = result.toFixed(2)
    decimals = 2
    decimal_sep = "."
    thousands_sep = ","
    
    n = result
    c = (if isNaN(decimals) then 2 else Math.abs(decimals)) #if decimal is zero we must take it, it means user does not want to show any decimal
    d = decimal_sep or "." #if no decimal separator is passed we use the dot as default decimal separator (we MUST use a decimal separator)
    
    #
    #   according to [http://stackoverflow.com/questions/411352/how-best-to-determine-if-an-argument-is-not-sent-to-the-javascript-function]
    #   the fastest way to check for not defined parameter is to use typeof value === 'undefined' 
    #   rather than doing value === undefined.
    #   
    t = (if (typeof thousands_sep is "undefined") then "," else thousands_sep) #if you don't want to use a thousands separator you can pass empty string as thousands_sep value
    sign = (if (n < 0) then "-" else "")
    
    #extracting the absolute value of the integer part of the number and converting to string
    i = parseInt(n = Math.abs(n).toFixed(c)) + ""
    j = (if ((j = i.length) > 3) then j % 3 else 0)
    sign + ((if j then i.substr(0, j) + t else "")) + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + ((if c then d + Math.abs(n - i).toFixed(c).slice(2) else ""))
    

  fcn
