package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.UserProfile

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
interface UserProfileService {
  UserProfile getUserProfile(String token)
}
