define [
  'handlebars'
  'underscore'
  'chaplin'
  'cs!lib/utils'
  'conf/config'
  'moment'
  'moment.es'
], (Handlebars, _, Chaplin, utils, fcn, conf, moment) ->
  'use strict'

  # View helpers (Handlebars in this case)
  # --------------------------------------

  # Shortcut to the mediator
  mediator = Chaplin.mediator

  # Add application-specific Handlebars helpers
  # -------------------------------------------

  # Handlebars.registerHelper 'helper_name', (options) ->
  #   'foo'
  #
  #


  # fmt currency
  # usage: {{fmtCurrency 12.36}}
  Handlebars.registerHelper 'fmtCurrency', (val, options) ->
     fcn.formatCurrency(val)

  # fmt currency
  # usage: {{fmtCurrency 12.36}}
  Handlebars.registerHelper 'escape', (val, options) ->
    val = "" if val is undefined
    val = val.replace RegExp(" ", "g"), "_"
    val = val.replace RegExp("\/", "g"), "_"
    val

  # select helper
  # usage: {{amountSelectBox max selecte id_sufix name ="" classSelector="" id="" }}
  Handlebars.registerHelper 'amountSelectBox', (max, selected, id_sufix, block) ->
    cl = ( if block.hash.classSelector != undefined then block.hash.classSelector else ""  )
    id = ( if block.hash.id != undefined then block.hash.id else ""  )
    id +=id_sufix
    name = ( if block.hash.name != undefined then block.hash.name else ""  )
    ret = "<select class='" + cl + "' id=" + id + " name='"+ name + "'>"
    i=1
    while i<(max+1)
      if i is selected
        ret += "<option selected " +  "value='" + i  + "' >" + i +  "</option>"
      else
        ret += "<option " +  "value='" + i  + "' >" + i +  "</option>"
      i++
    ret += "</select>"
    ret

  # select helper
  # usage: {{selectDateBox min max dateInMillis format="" name="" classSelector="" id="" defaultTxt="" defaultVal="" }}
  Handlebars.registerHelper 'selectDateBox', (min, max, dateInMillis, block) ->
    cl = ( if block.hash.classSelector != undefined then block.hash.classSelector else ""  )
    id = ( if block.hash.id != undefined then block.hash.id else ""  )
    name = ( if block.hash.name != undefined then block.hash.name else ""  )
    selected = (if dateInMillis > 0 then parseInt moment( new Date dateInMillis ).format block.hash.format else dateInMillis)
    ret = "<select class='" + cl + "' id=" + id + " name='"+ name + "'>"

    if block.hash.defaultTxt != undefined
      defaultTxt = block.hash.defaultTxt
      defaultVal = if block.hash.defaultVal != undefined then block.hash.defaultVal else defaultTxt
      ret += "<option value='" + defaultVal  + "' >" + defaultTxt +  "</option>"

    i=min
    while i <= max
      if i == selected
        ret += "<option selected " +  "value='" + i  + "' >" + i +  "</option>"
      else
        ret += "<option " +  "value='" + i  + "' >" + i +  "</option>"
      i++
    ret += "</select>"
    ret

  # compare helper just for primitives
  # usage: {{compare value1 "someValue"}}
  Handlebars.registerHelper 'curretDateGreaterThan', (var1, options) ->
    date1 = new Date(parseInt(var1))
    date2 = new Date()
    if date2.getTime() > date1.getTime()
      options.fn(this)
    else
      options.inverse(this)

  # compare helper just for primitives
  # usage: {{compare value1 "someValue"}}
  Handlebars.registerHelper 'compare', (var1, var2, options) ->
    if String(var1) is String(var2)
      options.fn(this)
    else
      options.inverse(this)

  # Show sections if has valid campaigns
  # usage: {{#showCampaignsValid campaigns}}
  Handlebars.registerHelper 'showCampaignsValid', (campaigns, options) ->
    subList = _.filter campaigns, (campaign) -> campaign.visibility isnt 'HIDDEN'

    if subList.length > 0
      options.fn(this)

  # Filtra items por el valor de alguna propiedad
  # usage: {{#filter items property="name_propery" conditional="is" value="value_property offset="0" limit="10"}}
  # defaults are offset=0, limit=20, conditional=is
  # conditonal suported: is, isnt
  Handlebars.registerHelper 'filter', (context, block) ->
    ret = ""
    inverse = block.inverse
    property = block.hash.property
    value = block.hash.value
    conditonal = block.hash.conditional
    offset = parseInt(block.hash.offset) or 0
    limit = parseInt(block.hash.limit) or 20

    if conditonal is undefined
      conditonal = "is"

    ia = 0
    tempa = []
    if context.length > 0
      while ia < context.length
        temp = context[ia]
        if conditonal is 'is'
          if temp[property] is value
            tempa.push temp
        else if conditonal is "contains"
          if _.contains(value, temp[property])
            tempa.push temp
        else
          if temp[property] isnt value
            tempa.push temp

        ia++
    if tempa.length >0
      i = (if (offset < tempa.length) then offset else 0)
      j = (if ((limit + offset) < tempa.length) then (limit + offset) else tempa.length)
      while i < j
          item = tempa[i]
          item.index = i
          ret += block(tempa[i])
          i++
    else
      ret = inverse(this)
    ret

  #! ******************************
  #Handlebars helpers https://gist.github.com/1468937#comments
  #******************************

  # debug helper
  # usage: {{debug}} or {{debug someValue}}
  # from: @commondream (http://thinkvitamin.com/code/handlebars-js-part-3-tips-and-tricks/)
  Handlebars.registerHelper "debug", (optionalValue) ->
    console.log "Current Context"
    console.log "===================="
    console.log this
    if optionalValue
      console.log "Value"
      console.log "===================="
      console.log optionalValue


  # return the first item of a list only
  # usage: {{#first items}}{{name}}{{/first}}
  Handlebars.registerHelper "first", (context, block) ->
    if context.length > 0
      block context[0]


  # a iterate over a specific portion of a list.
  # usage: {{#slice items offset="1" limit="5"}}{{name}}{{/slice}} : items 1 thru 6
  # usage: {{#slice items limit="10"}}{{name}}{{/slice}} : items 0 thru 9
  # usage: {{#slice items offset="3"}}{{name}}{{/slice}} : items 3 thru context.length
  # defaults are offset=0, limit=5
  # todo: combine parameters into single string like python or ruby slice ("start:length" or "start,length")
  Handlebars.registerHelper "slice", (context, block) ->
    ret = ""
    offset = parseInt(block.hash.offset) or 0
    limit = parseInt(block.hash.limit) or 5
    i = (if (offset < context.length) then offset else 0)
    j = (if ((limit + offset) < context.length) then (limit + offset) else context.length)

    if context.length > 0
      while i < j
        ret += block(context[i])
        i++

    ret


  # return a comma-serperated list from an iterable object
  # usage: {{#toSentance tags}}{{name}}{{/toSentance}}
  Handlebars.registerHelper "toSentance", (context, block) ->
    ret = ""
    i = 0
    j = context.length

    while i < j
      ret = ret + block(context[i])
      ret = ret + ", "  if i < j - 1
      i++
    ret


  # format an ISO date using Moment.js
  # http://momentjs.com/
  # moment syntax example: moment(Date("2011-07-18T15:50:52")).format("MMMM YYYY")
  # usage: {{dateFormat creation_date format="MMMM YYYY"}}
  Handlebars.registerHelper "dateFormat", (context, block) ->
    if moment
      f = block.hash.format or "MMM Do, YYYY"
      return moment(new Date(context)).format(f)
    else
      return context # moment plugin not available. return data as is.

  Handlebars.registerHelper "deliveryDateFormat", (context, block) ->
    if moment
      f = block.hash.format or "DD/MM/YYYY"
      days = parseInt(block.hash.days or 15)
      return moment(new Date(context)).add('days', days).format(f)
    else
      return context # moment plugin not available. return data as is.

  Handlebars.registerHelper "sliceHalfLeft", (context, block) ->
    ret = ""
    limit = Math.round context.length / 2
    i = 0

    while i < limit
      ret += block context[i++]

    ret

  Handlebars.registerHelper "sliceHalfRight", (context, block) ->
    ret = ""
    limit = context.length
    i = Math.round context.length / 2

    while i < limit
      ret += block context[i++]

    ret

  Handlebars.registerHelper "sliceBlock", (context, block) ->
    ret = ""
    part = parseInt(block.hash.part)
    offset = Math.round context.length / 4
    limit = (if offset * part is context.length-1 then context.length else offset * part)
    i = (part - 1) * offset
    if context.length > i and limit <= context.length
      while i < limit
        ret += block context[i++]

    ret

  null
