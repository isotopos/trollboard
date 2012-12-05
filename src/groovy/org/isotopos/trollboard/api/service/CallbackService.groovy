package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.Project

interface CallbackService {
  Project processPayload(payload)
}