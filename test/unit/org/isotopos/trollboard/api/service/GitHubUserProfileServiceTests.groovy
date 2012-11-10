package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.UserProfile
import org.junit.Test

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class GitHubUserProfileServiceTests {

  @Test
  void testGetProfileInfo() {
    GitHubUserProfileService userProfileService = new GitHubUserProfileService()
    UserProfile userProfile = userProfileService.getUserProfile('2914695d4a19a7e458ffdc7e4eee08b2dfd2482e')

    assert userProfile
    assert userProfile.avatar
    assert userProfile.username
    assert userProfile.resourceUri
    assert !userProfile.isOrganization

    println userProfile.dump()
  }
}
