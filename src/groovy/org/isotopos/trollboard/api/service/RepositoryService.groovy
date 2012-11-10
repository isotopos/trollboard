package org.isotopos.trollboard.api.service

import org.isotopos.trollboard.api.Project

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/11/12
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */
interface RepositoryService {
  List<Project> getProjects(String token)
}
