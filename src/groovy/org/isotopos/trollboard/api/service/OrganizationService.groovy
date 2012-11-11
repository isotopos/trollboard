package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.UserProfile
import org.isotopos.trollboard.api.Organization

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
interface OrganizationService {
  List<Organization> getOrganizations(String token)
}